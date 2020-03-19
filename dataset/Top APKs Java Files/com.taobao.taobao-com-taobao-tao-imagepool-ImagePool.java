package com.taobao.tao.imagepool;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Process;
import android.taobao.util.MemoryManager;
import android.taobao.util.MemoryManager.MemoryManagerListener;
import android.taobao.util.TaoLog;
import android.text.TextUtils;
import com.taobao.android.task.Coordinator;
import com.taobao.phenix.intf.IImageMemCache;
import com.taobao.tao.imagepool.utility.BitmapHelper;
import com.taobao.tao.imagepool.utility.BitmapHelperFactory;
import com.taobao.tao.imagepool.utility.TBDrawable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.regex.Pattern;

public class ImagePool
  implements Runnable
{
  public static final float MAX_COMPRESSION_RATIO_JPG = 0.55F;
  public static final float MAX_COMPRESSION_RATIO_WEBP = 0.4F;
  public static final String PERF_IMAGE_LEAK = "PerfImageLeak";
  private static final int PROTOCOL_CREATOR = 3;
  private static final int PROTOCOL_HTTP = 1;
  private static final int PROTOCOL_PACKAGE = 2;
  private static final int PROTOCOL_UNKNOWN = 0;
  public static final String SCHEME_TYPE_FILE = "file";
  public static final String SCHEME_TYPE_RESOURCE = "resource";
  private static boolean m_b_exit;
  static Pattern m_picPattern;
  private static Thread m_scheduleThread = null;
  private static boolean m_scheduleThreadStarted = false;
  static String m_userAgent;
  public final int LEAK_ALERT_THRESHOLD = 25;
  public Object groupLock = new Object();
  boolean insideMemoryCacheFlag = false;
  private IImageQualityStrategy mStragery;
  private final ConcurrentHashMap<String, WeakReference<ImageHandler>> m_HandlerMap = new ConcurrentHashMap(128);
  ImageCache m_IC;
  private boolean m_b_needSchedule;
  private int m_concurrentDownloadCount;
  Application m_context;
  private ArrayList<ImageGroup> m_dormantGroups;
  private ArrayList<ImageExecutor> m_downloaders;
  private ImageMemCache m_memCahce = new ImageMemCache();
  private ArrayList<ImageGroup> m_normalGroups;
  private ImageGroup m_topGroup;
  BitmapStatics stat;
  
  private ImagePool()
  {
    for (;;)
    {
      try
      {
        this.stat = new BitmapStatics();
        m_b_exit = false;
        int i = Runtime.getRuntime().availableProcessors();
        if ((i > 2) && (i <= 8))
        {
          this.m_concurrentDownloadCount = i;
          this.m_b_needSchedule = false;
          this.m_topGroup = null;
          this.m_normalGroups = new ArrayList();
          this.m_dormantGroups = new ArrayList();
          this.m_downloaders = new ArrayList();
          m_scheduleThread = new Thread(this, "image_pool_thread");
          m_scheduleThread.setPriority(1);
          m_scheduleThreadStarted = false;
          return;
        }
        if (i > 8) {
          this.m_concurrentDownloadCount = 8;
        } else {
          this.m_concurrentDownloadCount = 2;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
    }
  }
  
  private static String UUIDUrl(String paramString)
  {
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() != 0) {}
    }
    else
    {
      TaoLog.Logw("TaoSdk.ImgPool", "bad format url:" + paramString);
      str = "";
    }
    return str;
  }
  
  static TBDrawable _createTBDrawable(byte[] paramArrayOfByte, String paramString)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0)) {}
    label361:
    label366:
    for (;;)
    {
      try
      {
        long l = System.currentTimeMillis();
        Bitmap localBitmap2 = BitmapHelperFactory.Bytes2Bimap(paramArrayOfByte, paramString);
        ImageFullLinkStatistics.statisticDecode(paramString, System.currentTimeMillis() - l, paramArrayOfByte.length);
        if (localBitmap2 != null)
        {
          ImagePoolPrinter localImagePoolPrinter = instance().getDebugPrinter();
          Bitmap localBitmap1 = localBitmap2;
          if (localImagePoolPrinter != null)
          {
            int i = localBitmap2.getHeight();
            int j = localBitmap2.getWidth();
            if (i * j == 0) {
              break label361;
            }
            f1 = paramArrayOfByte.length / (i * j);
            float f2 = 0.55F;
            if (paramString.contains(".webp")) {
              f2 = 0.4F;
            }
            localBitmap1 = localBitmap2;
            if (f1 > f2)
            {
              new StringBuilder().append("图片压缩比过低提示 url:").append(paramString).append("  压缩比(解压前字节/(长*宽)：").append(f1).toString();
              localImagePoolPrinter.printExt(new String[] { "Image_Compression", "图片压缩比过低(解压前字节/(长*宽))", paramString, "压缩率:" + f1 });
              paramArrayOfByte = String.format("压缩比(%.2f)", new Object[] { Float.valueOf(f1) });
              if (j <= 150) {
                paramArrayOfByte = String.format("(%.2f)", new Object[] { Float.valueOf(f1) });
              }
              localBitmap1 = BitmapHelper.toGrayscaleAndMark(instance().m_context, localBitmap2, paramArrayOfByte);
              if ((localBitmap2 == null) || (localBitmap2.isRecycled())) {
                break label366;
              }
              localBitmap2.recycle();
              break label366;
            }
          }
          paramArrayOfByte = new TBDrawable(localBitmap1);
          break;
        }
        new StringBuilder().append("can't create bitmap from url:").append(paramString).toString();
        return null;
      }
      catch (OutOfMemoryError paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
        TaoLog.Loge("TaoSdk.ImgPool", "out of memory dump image pool stat:");
        instance().dumpMemory();
        paramArrayOfByte = null;
      }
      catch (Exception paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
        paramArrayOfByte = null;
      }
      new StringBuilder().append("_createTBDrawable failed ").append(paramString).toString();
      return null;
      float f1 = 0.0F;
    }
    return paramArrayOfByte;
  }
  
  private void _createTBDrawable(ImageHandler paramImageHandler, String paramString, int paramInt)
  {
    if (this.m_IC == null) {
      break label7;
    }
    label7:
    while (paramImageHandler == null) {
      return;
    }
    Object localObject = Uri.parse(paramString);
    if ((localObject != null) && (((Uri)localObject).getScheme() != null) && (TextUtils.equals(((Uri)localObject).getScheme().toLowerCase(), "file")))
    {
      new StringBuilder().append("load image from local file :").append(((Uri)localObject).toString()).toString();
      paramString = new TBDrawable(BitmapHelper.URI2Bimap(((Uri)localObject).getPath()));
    }
    for (;;)
    {
      if (paramString == null) {
        break label313;
      }
      boolean bool = paramImageHandler.setDrawable(paramString, false);
      if ((!this.m_memCahce.add(paramImageHandler)) || (!bool)) {
        break;
      }
      paramImageHandler = this.stat;
      paramImageHandler.createNum += 1;
      paramImageHandler = this.stat;
      paramInt = paramImageHandler.createSize;
      paramImageHandler.createSize = (paramString.bitmapSize() + paramInt);
      return;
      if ((localObject != null) && (((Uri)localObject).getScheme() != null) && (TextUtils.equals(((Uri)localObject).getScheme().toLowerCase(), "resource")))
      {
        new StringBuilder().append("load image from resouce file :").append(((Uri)localObject).toString()).toString();
        paramInt = paramString.lastIndexOf('\\');
        if ((paramInt != -1) && (paramInt + 1 < paramString.length())) {
          try
          {
            paramInt = Integer.parseInt(paramString.substring(paramInt + 1));
            paramString = new TBDrawable(((BitmapDrawable)this.m_context.getResources().getDrawable(paramInt)).getBitmap());
          }
          catch (NumberFormatException paramString)
          {
            paramString.printStackTrace();
          }
        } else {
          paramString = null;
        }
      }
      else
      {
        localObject = paramString;
        if (this.mStragery != null) {
          localObject = this.mStragery.decideUrl(paramString);
        }
        paramString = this.m_IC.getDrawalbe((String)localObject, paramInt);
      }
    }
    label313:
    paramImageHandler.setState(0);
  }
  
  private final boolean _findGroup(ImageGroup paramImageGroup)
  {
    if (this.m_topGroup == paramImageGroup) {}
    while (this.m_normalGroups.contains(paramImageGroup)) {
      return true;
    }
    return this.m_dormantGroups.contains(paramImageGroup);
  }
  
  private ImageHandler _getImageHandler(String paramString, int paramInt, BitmapCreator paramBitmapCreator)
  {
    ImageHandler localImageHandler = _getImageHandlerInMemory(paramString);
    if (localImageHandler == null) {
      if (paramBitmapCreator != null)
      {
        localImageHandler = new ImageHandler(paramString, paramInt, paramBitmapCreator);
        paramBitmapCreator = localImageHandler;
        if (this.insideMemoryCacheFlag)
        {
          this.m_HandlerMap.put(UUIDUrl(paramString), new WeakReference(localImageHandler));
          paramBitmapCreator = localImageHandler;
        }
      }
    }
    for (;;)
    {
      if ((paramBitmapCreator.isRecyceled()) || (paramBitmapCreator.m_dr == null))
      {
        new StringBuilder().append("_createTBDrawable!!! URL:").append(paramString).toString();
        _createTBDrawable(paramBitmapCreator, paramString, paramInt);
      }
      return paramBitmapCreator;
      localImageHandler = new ImageHandler(paramString, paramInt);
      paramBitmapCreator = localImageHandler;
      if (this.insideMemoryCacheFlag)
      {
        this.m_HandlerMap.put(UUIDUrl(paramString), new WeakReference(localImageHandler));
        paramBitmapCreator = localImageHandler;
        continue;
        new StringBuilder().append("_getImageHandlerInMemory got in memory!!! URL:").append(paramString).toString();
        paramBitmapCreator = localImageHandler;
      }
    }
  }
  
  private ImageHandler _getImageHandlerInMemory(String paramString)
  {
    TaoLog.Logw("TaoSdk.ImgPool", "_getImageHandlerInMemory url:" + paramString);
    if (this.insideMemoryCacheFlag) {
      return _insidegetImageHandlerInMemory(paramString);
    }
    return _outgetImageHandlerInMemory(paramString);
  }
  
  private ImageHandler _insidegetImageHandlerInMemory(String paramString)
  {
    String str = UUIDUrl(paramString);
    paramString = (WeakReference)this.m_HandlerMap.get(str);
    Object localObject;
    if (paramString == null) {
      localObject = null;
    }
    do
    {
      return localObject;
      localObject = (ImageHandler)paramString.get();
      paramString = (String)localObject;
      if (localObject != null)
      {
        paramString = (String)localObject;
        if (((ImageHandler)localObject).isRecyceled()) {
          paramString = null;
        }
      }
      localObject = paramString;
    } while (paramString != null);
    this.m_HandlerMap.remove(str);
    return paramString;
  }
  
  private ImageHandler _outgetImageHandlerInMemory(String paramString)
  {
    paramString = UUIDUrl(paramString);
    return this.m_memCahce.outGet(paramString);
  }
  
  private int _parseProtocol(String paramString)
  {
    if (paramString.startsWith("http")) {
      return 1;
    }
    if (paramString.startsWith("package")) {
      return 2;
    }
    if (paramString.startsWith("creator")) {
      return 3;
    }
    return 0;
  }
  
  /* Error */
  private void doSchedule()
  {
    // Byte code:
    //   0: invokestatic 501	java/lang/System:nanoTime	()J
    //   3: lstore_3
    //   4: aconst_null
    //   5: astore 6
    //   7: aload_0
    //   8: getfield 102	com/taobao/tao/imagepool/ImagePool:groupLock	Ljava/lang/Object;
    //   11: astore 7
    //   13: aload 7
    //   15: monitorenter
    //   16: aload 6
    //   18: astore 5
    //   20: aload_0
    //   21: getfield 137	com/taobao/tao/imagepool/ImagePool:m_topGroup	Lcom/taobao/tao/imagepool/ImageGroup;
    //   24: ifnull +30 -> 54
    //   27: aload 6
    //   29: astore 5
    //   31: aload_0
    //   32: getfield 137	com/taobao/tao/imagepool/ImagePool:m_topGroup	Lcom/taobao/tao/imagepool/ImageGroup;
    //   35: invokeinterface 506 1 0
    //   40: ifne +14 -> 54
    //   43: aload_0
    //   44: getfield 137	com/taobao/tao/imagepool/ImagePool:m_topGroup	Lcom/taobao/tao/imagepool/ImageGroup;
    //   47: invokeinterface 510 1 0
    //   52: astore 5
    //   54: aload 7
    //   56: monitorexit
    //   57: aload 5
    //   59: ifnull +22 -> 81
    //   62: aload_0
    //   63: aload 5
    //   65: getfield 516	com/taobao/tao/imagepool/ScheduleInfo:ih	Lcom/taobao/tao/imagepool/ImageHandler;
    //   68: aload_0
    //   69: getfield 137	com/taobao/tao/imagepool/ImagePool:m_topGroup	Lcom/taobao/tao/imagepool/ImageGroup;
    //   72: aload 5
    //   74: getfield 519	com/taobao/tao/imagepool/ScheduleInfo:index	I
    //   77: invokespecial 523	com/taobao/tao/imagepool/ImagePool:processImageHandler	(Lcom/taobao/tao/imagepool/ImageHandler;Lcom/taobao/tao/imagepool/ImageGroup;I)Z
    //   80: pop
    //   81: aload_0
    //   82: getfield 146	com/taobao/tao/imagepool/ImagePool:m_downloaders	Ljava/util/ArrayList;
    //   85: invokevirtual 526	java/util/ArrayList:size	()I
    //   88: aload_0
    //   89: getfield 133	com/taobao/tao/imagepool/ImagePool:m_concurrentDownloadCount	I
    //   92: if_icmplt +10 -> 102
    //   95: aload_0
    //   96: invokespecial 530	com/taobao/tao/imagepool/ImagePool:findIdleExecutor	()Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;
    //   99: ifnull +22 -> 121
    //   102: aload_0
    //   103: getfield 102	com/taobao/tao/imagepool/ImagePool:groupLock	Ljava/lang/Object;
    //   106: astore 5
    //   108: aload 5
    //   110: monitorenter
    //   111: aload_0
    //   112: getfield 137	com/taobao/tao/imagepool/ImagePool:m_topGroup	Lcom/taobao/tao/imagepool/ImageGroup;
    //   115: ifnonnull +62 -> 177
    //   118: aload 5
    //   120: monitorexit
    //   121: aload_0
    //   122: getfield 142	com/taobao/tao/imagepool/ImagePool:m_normalGroups	Ljava/util/ArrayList;
    //   125: invokevirtual 526	java/util/ArrayList:size	()I
    //   128: istore_2
    //   129: iload_2
    //   130: ifne +98 -> 228
    //   133: new 172	java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   140: ldc_w 532
    //   143: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokestatic 501	java/lang/System:nanoTime	()J
    //   149: lload_3
    //   150: lsub
    //   151: ldc2_w 533
    //   154: ldiv
    //   155: invokevirtual 537	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   158: ldc_w 539
    //   161: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: pop
    //   168: return
    //   169: astore 5
    //   171: aload 7
    //   173: monitorexit
    //   174: aload 5
    //   176: athrow
    //   177: aload_0
    //   178: getfield 137	com/taobao/tao/imagepool/ImagePool:m_topGroup	Lcom/taobao/tao/imagepool/ImageGroup;
    //   181: invokeinterface 510 1 0
    //   186: astore 6
    //   188: aload 5
    //   190: monitorexit
    //   191: aload 6
    //   193: ifnull -72 -> 121
    //   196: aload_0
    //   197: aload 6
    //   199: getfield 516	com/taobao/tao/imagepool/ScheduleInfo:ih	Lcom/taobao/tao/imagepool/ImageHandler;
    //   202: aload_0
    //   203: getfield 137	com/taobao/tao/imagepool/ImagePool:m_topGroup	Lcom/taobao/tao/imagepool/ImageGroup;
    //   206: aload 6
    //   208: getfield 519	com/taobao/tao/imagepool/ScheduleInfo:index	I
    //   211: invokespecial 523	com/taobao/tao/imagepool/ImagePool:processImageHandler	(Lcom/taobao/tao/imagepool/ImageHandler;Lcom/taobao/tao/imagepool/ImageGroup;I)Z
    //   214: ifne -133 -> 81
    //   217: goto -136 -> 81
    //   220: astore 6
    //   222: aload 5
    //   224: monitorexit
    //   225: aload 6
    //   227: athrow
    //   228: iconst_0
    //   229: istore_1
    //   230: iload_1
    //   231: iload_2
    //   232: if_icmpge +26 -> 258
    //   235: aload_0
    //   236: getfield 102	com/taobao/tao/imagepool/ImagePool:groupLock	Ljava/lang/Object;
    //   239: astore 5
    //   241: aload 5
    //   243: monitorenter
    //   244: iload_1
    //   245: aload_0
    //   246: getfield 142	com/taobao/tao/imagepool/ImagePool:m_normalGroups	Ljava/util/ArrayList;
    //   249: invokevirtual 526	java/util/ArrayList:size	()I
    //   252: if_icmplt +42 -> 294
    //   255: aload 5
    //   257: monitorexit
    //   258: new 172	java/lang/StringBuilder
    //   261: dup
    //   262: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   265: ldc_w 532
    //   268: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokestatic 501	java/lang/System:nanoTime	()J
    //   274: lload_3
    //   275: lsub
    //   276: ldc2_w 533
    //   279: ldiv
    //   280: invokevirtual 537	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   283: ldc_w 539
    //   286: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: pop
    //   293: return
    //   294: aload_0
    //   295: getfield 142	com/taobao/tao/imagepool/ImagePool:m_normalGroups	Ljava/util/ArrayList;
    //   298: iload_1
    //   299: invokevirtual 542	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   302: checkcast 503	com/taobao/tao/imagepool/ImageGroup
    //   305: astore 6
    //   307: aload 6
    //   309: invokeinterface 510 1 0
    //   314: astore 7
    //   316: aload 5
    //   318: monitorexit
    //   319: aload 7
    //   321: ifnull +25 -> 346
    //   324: aload 6
    //   326: ifnull +20 -> 346
    //   329: aload_0
    //   330: aload 7
    //   332: getfield 516	com/taobao/tao/imagepool/ScheduleInfo:ih	Lcom/taobao/tao/imagepool/ImageHandler;
    //   335: aload 6
    //   337: aload 7
    //   339: getfield 519	com/taobao/tao/imagepool/ScheduleInfo:index	I
    //   342: invokespecial 523	com/taobao/tao/imagepool/ImagePool:processImageHandler	(Lcom/taobao/tao/imagepool/ImageHandler;Lcom/taobao/tao/imagepool/ImageGroup;I)Z
    //   345: pop
    //   346: iload_1
    //   347: iconst_1
    //   348: iadd
    //   349: istore_1
    //   350: goto -120 -> 230
    //   353: astore 6
    //   355: aload 5
    //   357: monitorexit
    //   358: aload 6
    //   360: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	361	0	this	ImagePool
    //   229	121	1	i	int
    //   128	105	2	j	int
    //   3	272	3	l	long
    //   169	54	5	localObject2	Object
    //   5	202	6	localScheduleInfo	ScheduleInfo
    //   220	6	6	localObject4	Object
    //   305	31	6	localImageGroup	ImageGroup
    //   353	6	6	localObject5	Object
    //   11	327	7	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   20	27	169	finally
    //   31	54	169	finally
    //   54	57	169	finally
    //   111	121	220	finally
    //   177	191	220	finally
    //   244	258	353	finally
    //   294	319	353	finally
  }
  
  private final ImageExecutor findEqualLoadingExecutor(String paramString)
  {
    synchronized (this.m_downloaders)
    {
      Iterator localIterator = this.m_downloaders.iterator();
      while (localIterator.hasNext())
      {
        ImageExecutor localImageExecutor = (ImageExecutor)localIterator.next();
        if ((localImageExecutor.m_b_executing) && (localImageExecutor.m_image != null) && (localImageExecutor.m_image.URI() == paramString)) {
          return localImageExecutor;
        }
      }
      return null;
    }
  }
  
  private final ImageExecutor findIdleExecutor()
  {
    synchronized (this.m_downloaders)
    {
      Iterator localIterator = this.m_downloaders.iterator();
      while (localIterator.hasNext())
      {
        ImageExecutor localImageExecutor = (ImageExecutor)localIterator.next();
        if (!localImageExecutor.m_b_executing) {
          return localImageExecutor;
        }
      }
      return null;
    }
  }
  
  public static ImagePool instance()
  {
    return SingletonHolder.instance;
  }
  
  private boolean processDownload(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
  {
    new StringBuilder().append("processDownload:").append(paramImageHandler.URI()).toString();
    if ((paramImageHandler == null) || (paramImageHandler.URI() == null)) {
      return false;
    }
    paramImageHandler.setState(2);
    switch (_parseProtocol(paramImageHandler.URI()))
    {
    default: 
      TaoLog.Loge("TaoSdk.ImgPool", "unknown protocol url:" + paramImageHandler.URI());
      return false;
    case 1: 
      loadImage(paramImageHandler, paramImageGroup, paramInt);
      return false;
    case 2: 
      loadPackageIcon(paramImageHandler, paramImageGroup, paramInt);
      return false;
    }
    loadImageFromCreator(paramImageHandler, paramImageGroup, paramInt);
    return false;
  }
  
  private boolean processImageHandler(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
  {
    if (paramImageHandler == null)
    {
      groupChanged(paramImageGroup);
      return false;
    }
    new StringBuilder().append("processImageHandler:").append(paramImageHandler.URI()).append(",index:").append(paramInt).append("state:").append(paramImageHandler.getState()).append(",ih=").append(paramImageHandler).toString();
    boolean bool1;
    switch (paramImageHandler.getState())
    {
    case 1: 
    default: 
      bool1 = false;
    }
    for (;;)
    {
      if (bool1) {
        paramImageGroup.doSendMsg(0, paramImageHandler.URI(), paramInt);
      }
      groupChanged(paramImageGroup);
      return true;
      new StringBuilder().append("ih is already loading url:").append(paramImageHandler.URI()).toString();
      processDownload(paramImageHandler, paramImageGroup, paramInt);
      bool1 = false;
      continue;
      if (paramImageHandler.isRecyceled()) {
        TaoLog.Logw("TaoSdk.ImgPool", "loaded with recycle bitmap! url:" + paramImageHandler.URI());
      }
      bool1 = true;
      continue;
      boolean bool2 = processRecycle(paramImageHandler);
      bool1 = bool2;
      if (!bool2)
      {
        paramImageHandler.setState(0);
        return processImageHandler(paramImageHandler, paramImageGroup, paramInt);
        TaoLog.Logw("TaoSdk.ImgPool", "ih is failed! url:" + paramImageHandler.URI());
        bool1 = false;
        continue;
        bool1 = processLoaded(paramImageHandler, paramImageGroup, paramInt);
      }
    }
  }
  
  private boolean processLoaded(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
  {
    if (paramImageHandler != null)
    {
      if (_loadDrawable(paramImageHandler, paramImageHandler.URI(), paramImageHandler.getCachePolicy(), paramImageHandler.getBitmapCreator())) {
        try
        {
          Thread.sleep(2L);
          return true;
        }
        catch (Exception paramImageHandler)
        {
          for (;;)
          {
            paramImageHandler.printStackTrace();
          }
        }
      }
      return processDownload(paramImageHandler, paramImageGroup, paramInt);
    }
    return false;
  }
  
  private boolean processRecycle(ImageHandler paramImageHandler)
  {
    if (paramImageHandler == null) {}
    while ((!paramImageHandler.isRecyceled()) || (!paramImageHandler._ReloadRecyceledIfNeed())) {
      return false;
    }
    return true;
  }
  
  private void reSchedule()
  {
    if (m_scheduleThread == null) {
      return;
    }
    synchronized (m_scheduleThread)
    {
      this.m_b_needSchedule = true;
      if (!m_scheduleThreadStarted)
      {
        m_scheduleThread.start();
        m_scheduleThreadStarted = true;
      }
    }
    try
    {
      m_scheduleThread.notify();
      return;
      localObject = finally;
      throw localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        new StringBuilder().append("ImagePool::reSchedule failed , resume thread exception: ").append(localException.getMessage()).toString();
      }
    }
  }
  
  public void BitmapRecycle()
  {
    if (this.m_memCahce != null) {
      this.m_memCahce.ForceBitmapRecycle(12);
    }
  }
  
  public void ForceBitmapRecycleAll()
  {
    if (this.m_memCahce != null) {
      this.m_memCahce.ForceBitmapRecycleAll();
    }
  }
  
  public void Init(Application paramApplication, String paramString1, String paramString2)
  {
    try
    {
      this.m_context = paramApplication;
      m_userAgent = paramString1;
      m_picPattern = Pattern.compile(paramString2);
      if (this.m_IC == null)
      {
        this.m_IC = new ImageCache(paramApplication);
        this.m_IC.setImageQualityStrategy(this.mStragery);
      }
      return;
    }
    finally
    {
      paramApplication = finally;
      throw paramApplication;
    }
  }
  
  public String URLtoPersistPath(String paramString, int paramInt)
  {
    String str = paramString;
    if (this.mStragery != null) {
      str = this.mStragery.decideUrl(paramString);
    }
    if (this.m_IC != null) {
      return this.m_IC.URLtoPersistPath(str, paramInt);
    }
    return "";
  }
  
  public ImageHandler _createImageHandler(String paramString, int paramInt)
  {
    return _createImageHandler(paramString, paramInt, null);
  }
  
  public ImageHandler _createImageHandler(String paramString, int paramInt, BitmapCreator paramBitmapCreator)
  {
    ImageHandler localImageHandler = _getImageHandlerInMemory(paramString);
    Object localObject = localImageHandler;
    if (localImageHandler == null) {
      if (paramBitmapCreator != null) {
        break label66;
      }
    }
    label66:
    for (paramBitmapCreator = new ImageHandler(paramString, paramInt);; paramBitmapCreator = new ImageHandler(paramString, paramInt, paramBitmapCreator))
    {
      localObject = paramBitmapCreator;
      if (this.insideMemoryCacheFlag)
      {
        this.m_HandlerMap.put(UUIDUrl(paramString), new WeakReference(paramBitmapCreator));
        localObject = paramBitmapCreator;
      }
      return localObject;
    }
  }
  
  boolean _loadDrawable(ImageHandler paramImageHandler, String paramString, int paramInt, BitmapCreator paramBitmapCreator)
  {
    paramBitmapCreator = _getImageHandler(paramString, paramInt, paramBitmapCreator);
    if (paramBitmapCreator != null)
    {
      if (paramImageHandler != paramBitmapCreator)
      {
        TaoLog.Logw("TaoSdk.ImgPool", "different handler for one url:" + paramString);
        paramImageHandler.isRecyclable();
      }
      if (paramBitmapCreator.getState() == 3) {
        return true;
      }
    }
    return false;
  }
  
  public Future<String> addBitmap(Bitmap paramBitmap, String paramString, int paramInt, boolean paramBoolean)
  {
    ExecutorService localExecutorService = Executors.newSingleThreadExecutor();
    paramBitmap = new FutureTask(new ImagePool.1(this, paramString, paramInt, paramBitmap, paramBoolean));
    localExecutorService.execute(paramBitmap);
    return paramBitmap;
  }
  
  final void addGroup(ImageGroup paramImageGroup)
  {
    for (;;)
    {
      synchronized (this.groupLock)
      {
        if (_findGroup(paramImageGroup)) {
          return;
        }
        switch (paramImageGroup.getPriority())
        {
        case 0: 
          new StringBuilder().append("ImagePool::addGroup() done, group priority ").append(paramImageGroup.getPriority()).toString();
          return;
          if (this.m_topGroup != null) {
            this.m_topGroup.setPriority(1);
          }
          this.m_topGroup = paramImageGroup;
          reSchedule();
        }
      }
      this.m_normalGroups.add(paramImageGroup);
      reSchedule();
      continue;
      this.m_dormantGroups.add(paramImageGroup);
    }
  }
  
  public void cancelLoad(String paramString)
  {
    new StringBuilder().append("ImagePool.cancelLoad() ").append(paramString).toString();
    synchronized (this.m_downloaders)
    {
      Iterator localIterator = this.m_downloaders.iterator();
      while (localIterator.hasNext())
      {
        ImageExecutor localImageExecutor = (ImageExecutor)localIterator.next();
        if ((localImageExecutor.m_image != null) && (localImageExecutor.m_image.URI().equals(paramString)))
        {
          localImageExecutor.stop();
          if (this.m_downloaders.size() > this.m_concurrentDownloadCount)
          {
            localImageExecutor.releaseIDL();
            this.m_downloaders.remove(localImageExecutor);
          }
          new StringBuilder().append("image download cancelled() ").append(paramString).toString();
        }
      }
      return;
    }
  }
  
  public void clearCache(int paramInt)
  {
    if (this.m_IC != null) {
      this.m_IC.clearCache(paramInt);
    }
  }
  
  public void delImage(String paramString, int paramInt)
  {
    if (this.m_IC != null) {
      this.m_IC.deleteFile(paramString, paramInt);
    }
  }
  
  public void dumpMemory()
  {
    if (this.m_memCahce != null) {
      this.m_memCahce.dumpMemory(false);
    }
  }
  
  public ImagePoolPrinter getDebugPrinter()
  {
    if (this.m_memCahce != null) {
      return this.m_memCahce.getDebugPrinter();
    }
    return null;
  }
  
  public ImageHandler getImageHandler(String paramString, int paramInt)
  {
    return getImageHandler(paramString, paramInt, null);
  }
  
  public ImageHandler getImageHandler(String paramString, int paramInt, BitmapCreator paramBitmapCreator)
  {
    paramBitmapCreator = _getImageHandler(paramString, paramInt, paramBitmapCreator);
    paramString = paramBitmapCreator;
    if (paramBitmapCreator != null)
    {
      paramString = paramBitmapCreator;
      if (!paramBitmapCreator.isValideDrawable()) {
        paramString = null;
      }
    }
    return paramString;
  }
  
  public ImageHandler getImageHandlerInMemory(String paramString)
  {
    ImageHandler localImageHandler2 = _getImageHandlerInMemory(paramString);
    ImageHandler localImageHandler1 = localImageHandler2;
    if (localImageHandler2 != null)
    {
      localImageHandler1 = localImageHandler2;
      if (!localImageHandler2.isValideDrawable())
      {
        new StringBuilder().append("getImageHandlerInMemory ih is not contians bitmap url:").append(paramString).toString();
        localImageHandler2.setState(0);
        localImageHandler1 = null;
      }
    }
    return localImageHandler1;
  }
  
  public IImageQualityStrategy getImageQualityStrategy()
  {
    return this.mStragery;
  }
  
  public final void groupChanged(ImageGroup paramImageGroup)
  {
    synchronized (this.groupLock)
    {
      if ((_findGroup(paramImageGroup)) && (!this.m_dormantGroups.contains(paramImageGroup))) {
        reSchedule();
      }
      return;
    }
  }
  
  public void groupPriorityChanged(ImageGroup paramImageGroup, int paramInt1, int paramInt2)
  {
    Object localObject = this.groupLock;
    if ((paramInt1 == paramInt2) || (paramImageGroup == null)) {
      return;
    }
    do
    {
      try
      {
        if (this.m_topGroup == paramImageGroup)
        {
          this.m_topGroup = null;
          if (paramInt2 != 2) {
            break;
          }
          this.m_dormantGroups.add(paramImageGroup);
          new StringBuilder().append("ImagePool::groupPriorityChanged() from ").append(paramInt1).append(" to ").append(paramInt2).toString();
          return;
        }
      }
      finally {}
    } while ((this.m_normalGroups.remove(paramImageGroup)) || (this.m_dormantGroups.remove(paramImageGroup)));
    return;
    if (paramInt2 == 1) {
      this.m_normalGroups.add(paramImageGroup);
    }
    for (;;)
    {
      reSchedule();
      break;
      this.m_topGroup = paramImageGroup;
    }
  }
  
  /* Error */
  void loadImage(ImageHandler paramImageHandler, ImageGroup arg2, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 567	com/taobao/tao/imagepool/ImageHandler:URI	()Ljava/lang/String;
    //   5: invokespecial 780	com/taobao/tao/imagepool/ImagePool:findEqualLoadingExecutor	(Ljava/lang/String;)Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;
    //   8: astore 4
    //   10: aload 4
    //   12: ifnull +12 -> 24
    //   15: aload 4
    //   17: aload_1
    //   18: aload_2
    //   19: iload_3
    //   20: invokestatic 784	com/taobao/tao/imagepool/ImagePool$ImageExecutor:access$800	(Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;Lcom/taobao/tao/imagepool/ImageHandler;Lcom/taobao/tao/imagepool/ImageGroup;I)V
    //   23: return
    //   24: aload_0
    //   25: invokespecial 530	com/taobao/tao/imagepool/ImagePool:findIdleExecutor	()Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;
    //   28: astore 4
    //   30: aload 4
    //   32: ifnonnull +104 -> 136
    //   35: new 17	com/taobao/tao/imagepool/ImagePool$ImageExecutor
    //   38: dup
    //   39: aload_0
    //   40: aload_1
    //   41: aload_2
    //   42: iload_3
    //   43: invokespecial 787	com/taobao/tao/imagepool/ImagePool$ImageExecutor:<init>	(Lcom/taobao/tao/imagepool/ImagePool;Lcom/taobao/tao/imagepool/ImageHandler;Lcom/taobao/tao/imagepool/ImageGroup;I)V
    //   46: astore_1
    //   47: aload_1
    //   48: invokestatic 790	com/taobao/tao/imagepool/ImagePool$ImageExecutor:access$900	(Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;)V
    //   51: aload_0
    //   52: getfield 146	com/taobao/tao/imagepool/ImagePool:m_downloaders	Ljava/util/ArrayList;
    //   55: astore_2
    //   56: aload_2
    //   57: monitorenter
    //   58: aload_0
    //   59: getfield 146	com/taobao/tao/imagepool/ImagePool:m_downloaders	Ljava/util/ArrayList;
    //   62: aload_1
    //   63: invokevirtual 733	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   66: pop
    //   67: aload_2
    //   68: monitorexit
    //   69: aload_1
    //   70: invokestatic 793	com/taobao/tao/imagepool/ImagePool$ImageExecutor:access$1200	(Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;)V
    //   73: new 172	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   80: ldc_w 795
    //   83: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload_0
    //   87: getfield 146	com/taobao/tao/imagepool/ImagePool:m_downloaders	Ljava/util/ArrayList;
    //   90: invokevirtual 526	java/util/ArrayList:size	()I
    //   93: invokevirtual 598	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   96: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: pop
    //   100: return
    //   101: astore_1
    //   102: new 172	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   109: ldc_w 797
    //   112: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload_1
    //   116: invokevirtual 657	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   119: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   125: pop
    //   126: aload_1
    //   127: invokevirtual 159	java/lang/Exception:printStackTrace	()V
    //   130: return
    //   131: astore_1
    //   132: aload_2
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    //   136: aload 4
    //   138: invokestatic 790	com/taobao/tao/imagepool/ImagePool$ImageExecutor:access$900	(Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;)V
    //   141: aload 4
    //   143: aload_1
    //   144: iload_3
    //   145: invokestatic 801	com/taobao/tao/imagepool/ImagePool$ImageExecutor:access$1000	(Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;Lcom/taobao/tao/imagepool/ImageHandler;I)V
    //   148: aload 4
    //   150: aload_2
    //   151: invokestatic 805	com/taobao/tao/imagepool/ImagePool$ImageExecutor:access$1100	(Lcom/taobao/tao/imagepool/ImagePool$ImageExecutor;Lcom/taobao/tao/imagepool/ImageGroup;)V
    //   154: aload 4
    //   156: astore_1
    //   157: goto -88 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	this	ImagePool
    //   0	160	1	paramImageHandler	ImageHandler
    //   0	160	3	paramInt	int
    //   8	147	4	localImageExecutor	ImageExecutor
    // Exception table:
    //   from	to	target	type
    //   35	58	101	java/lang/Exception
    //   69	100	101	java/lang/Exception
    //   132	136	101	java/lang/Exception
    //   136	154	101	java/lang/Exception
    //   58	69	131	finally
  }
  
  void loadImageFromCreator(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
  {
    new StringBuilder().append("ImagePool::loadImageFromCreator url: ").append(paramImageHandler.URI()).toString();
    new BitmapCreatorExecutor(paramImageHandler, paramImageGroup, paramInt).start();
  }
  
  void loadPackageIcon(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
  {
    new StringBuilder().append("ImagePool::loadPackageIcon url: ").append(paramImageHandler.URI()).toString();
    new IconExecutor(paramImageHandler, paramImageGroup, paramInt).start();
  }
  
  public void releaseImageHandler(ImageHandler paramImageHandler)
  {
    if (paramImageHandler != null) {
      paramImageHandler.subRef();
    }
  }
  
  public boolean remove(ImageHandler paramImageHandler)
  {
    return this.m_memCahce.remove(paramImageHandler);
  }
  
  public final boolean removeGroup(ImageGroup paramImageGroup)
  {
    synchronized (this.groupLock)
    {
      if (this.m_topGroup == paramImageGroup)
      {
        this.m_topGroup = null;
        reSchedule();
        return true;
      }
      if (this.m_dormantGroups.remove(paramImageGroup)) {
        return true;
      }
    }
    if (this.m_normalGroups.remove(paramImageGroup))
    {
      reSchedule();
      return true;
    }
    return false;
  }
  
  public void run()
  {
    Process.setThreadPriority(19);
    do
    {
      for (;;)
      {
        if (this.m_b_needSchedule)
        {
          this.m_b_needSchedule = false;
          try
          {
            doSchedule();
            Thread.sleep(2L);
            if (!this.m_b_needSchedule) {
              synchronized (m_scheduleThread)
              {
                m_scheduleThread.wait();
              }
            }
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            new StringBuilder().append("ImagePool::run() thread wait exception: ").append(localException.getMessage()).toString();
          }
        }
      }
    } while (!m_b_exit);
  }
  
  public void setConcurrentDownloadCount(int paramInt)
  {
    try
    {
      this.m_concurrentDownloadCount = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void setDebugPrinter(ImagePoolPrinter paramImagePoolPrinter)
  {
    if (this.m_memCahce != null) {
      this.m_memCahce.setDebugPrinter(paramImagePoolPrinter);
    }
  }
  
  public void setImageMemCache(IImageMemCache paramIImageMemCache)
  {
    this.m_memCahce.setiMemCache(paramIImageMemCache);
  }
  
  public void setImageQualityStrategy(IImageQualityStrategy paramIImageQualityStrategy)
  {
    this.mStragery = paramIImageQualityStrategy;
    if (this.m_IC != null) {
      this.m_IC.setImageQualityStrategy(paramIImageQualityStrategy);
    }
  }
  
  public void setMaxMemory(int paramInt)
  {
    if (this.m_memCahce != null) {
      this.m_memCahce.onSetMaxMemory(paramInt);
    }
  }
  
  private class BitmapCreatorExecutor
    implements Runnable
  {
    private ImageGroup m_ig;
    private ImageHandler m_ih;
    private int m_index;
    
    public BitmapCreatorExecutor(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
    {
      this.m_ih = paramImageHandler;
      this.m_ig = paramImageGroup;
      this.m_index = paramInt;
    }
    
    public void run()
    {
      int j = 0;
      if ((ImagePool.this.m_context == null) || (this.m_ih == null) || (this.m_ig == null))
      {
        TaoLog.Logw("TaoSdk.ImgPool", "BitmapCreatorExecutor m_context is null!");
        return;
      }
      new StringBuilder().append("BitmapCreatorExecutor run url: ").append(this.m_ih.URI()).toString();
      String str = this.m_ih.URI();
      if ((str == null) || (str.length() == 0))
      {
        this.m_ig.feedImage(-2, this.m_ih.URI(), this.m_index);
        return;
      }
      Object localObject1 = null;
      if (ImagePool.this.m_IC != null) {
        localObject1 = ImagePool.this.m_IC.getDrawalbe(str, this.m_ih.getCachePolicy());
      }
      Object localObject2 = localObject1;
      if (localObject1 == null)
      {
        Object localObject3 = this.m_ih.getBitmapCreator();
        localObject2 = localObject1;
        if (localObject3 != null)
        {
          localObject3 = ((BitmapCreator)localObject3).createBitmap(str);
          localObject2 = localObject1;
          if (localObject3 != null)
          {
            localObject1 = BitmapHelper.Bitmap2BytesPng((Bitmap)localObject3);
            if (ImagePool.this.m_IC != null)
            {
              new StringBuilder().append("write to file cache url:").append(str).append(" data len=").append(localObject1.length).append(" type=").append(this.m_ih.getCachePolicy()).toString();
              ImagePool.this.m_IC.saveData(str, (byte[])localObject1, this.m_ih.getCachePolicy());
            }
            localObject2 = new TBDrawable((Bitmap)localObject3);
          }
        }
      }
      if (localObject2 != null)
      {
        boolean bool = this.m_ih.setDrawable((Drawable)localObject2, false);
        i = j;
        if (ImagePool.this.m_memCahce.add(this.m_ih))
        {
          i = j;
          if (bool)
          {
            localObject1 = ImagePool.this.stat;
            ((ImagePool.BitmapStatics)localObject1).createNum += 1;
            localObject1 = ImagePool.this.stat;
            i = ((ImagePool.BitmapStatics)localObject1).createSize;
            ((ImagePool.BitmapStatics)localObject1).createSize = (((TBDrawable)localObject2).bitmapSize() + i);
          }
        }
      }
      for (int i = j;; i = -2)
      {
        this.m_ig.feedImage(i, this.m_ih.URI(), this.m_index);
        return;
      }
    }
    
    public void start()
    {
      Coordinator.postTask(new ImagePool.BitmapCreatorExecutor.1(this, "BitmapCreatorExecutor" + hashCode()));
    }
  }
  
  class BitmapStatics
  {
    int createNum = 0;
    int createSize = 0;
    int destroyNum = 0;
    int destroySize = 0;
    
    BitmapStatics() {}
    
    void report(String paramString)
    {
      new StringBuilder().append(" bitmap stat ").append(paramString).append("  created:").append(this.createNum).append(" , destroy: ").append(this.destroyNum).toString();
      new StringBuilder().append(" bitmap stat ").append(paramString).append("  created size :").append(this.createSize).append(" , destroySize: ").append(this.destroySize).append(" memory occupied: ").append(this.createSize - this.destroySize).toString();
    }
  }
  
  private class IconExecutor
    implements Runnable
  {
    private ImageGroup m_ig;
    private ImageHandler m_ih;
    private int m_index;
    
    public IconExecutor(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
    {
      this.m_ih = paramImageHandler;
      this.m_ig = paramImageGroup;
      this.m_index = paramInt;
    }
    
    public void run()
    {
      if ((ImagePool.this.m_context == null) || (this.m_ih == null) || (this.m_ig == null))
      {
        TaoLog.Logw("TaoSdk.ImgPool", "m_context is null!");
        return;
      }
      new StringBuilder().append("IconExecutor run url: ").append(this.m_ih.URI()).toString();
      int i = this.m_ih.URI().indexOf(':');
      if (i >= 0) {}
      Object localObject1;
      for (String str = this.m_ih.URI().substring(i + 3);; localObject1 = "")
      {
        if (str.length() > 0)
        {
          Object localObject2 = ImagePool.this.m_context.getPackageManager();
          if (localObject2 == null)
          {
            this.m_ig.feedImage(-2, this.m_ih.URI(), this.m_index);
            return;
          }
          try
          {
            Object localObject3 = ((PackageManager)localObject2).getInstalledPackages(0);
            localObject3 = ((List)localObject3).iterator();
            Object localObject4;
            for (;;)
            {
              if (!((Iterator)localObject3).hasNext()) {
                break label393;
              }
              localObject4 = (PackageInfo)((Iterator)localObject3).next();
              if (((PackageInfo)localObject4).applicationInfo.packageName.equalsIgnoreCase(str))
              {
                localObject4 = ((PackageInfo)localObject4).applicationInfo.loadIcon((PackageManager)localObject2);
                if ((localObject4 instanceof BitmapDrawable)) {
                  break;
                }
                TaoLog.Logw("TaoSdk.ImgPool", "icon isn't a bitmap drawalbe url:" + this.m_ih.URI());
              }
            }
            localObject1 = new TBDrawable(((BitmapDrawable)localObject4).getBitmap());
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            this.m_ig.feedImage(-2, this.m_ih.URI(), this.m_index);
            return;
          }
          if (localObject1 != null)
          {
            boolean bool = this.m_ih.setDrawable((Drawable)localObject1, false);
            if ((ImagePool.this.m_memCahce.add(this.m_ih)) && (bool))
            {
              localObject2 = ImagePool.this.stat;
              ((ImagePool.BitmapStatics)localObject2).createNum += 1;
              localObject2 = ImagePool.this.stat;
              i = ((ImagePool.BitmapStatics)localObject2).createSize;
              ((ImagePool.BitmapStatics)localObject2).createSize = (((TBDrawable)localObject1).bitmapSize() + i);
            }
          }
        }
        label393:
        for (i = 0;; i = -2)
        {
          this.m_ig.feedImage(i, this.m_ih.URI(), this.m_index);
          return;
        }
      }
    }
    
    public void start()
    {
      Coordinator.postTask(new ImagePool.IconExecutor.1(this, "IconExecutor" + hashCode()));
    }
  }
  
  private class ImageExecutor
    implements IImageDownloader.DownloadNotifier
  {
    private boolean m_b_executing;
    private ArrayList<FeedImageListener> m_feedQueue;
    private ImageGroup m_group;
    private IImageDownloader m_idl;
    private ImageHandler m_image;
    private int m_indexInGroup;
    
    public ImageExecutor(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
      throws Exception
    {
      if (paramImageGroup == null) {
        throw new Exception("ImagePool::ImageExecutor new exception: null group param");
      }
      this.m_group = paramImageGroup;
      this.m_image = paramImageHandler;
      this.m_image.setCachePolicy(this.m_group.getCachePolicy());
      this.m_b_executing = false;
      this.m_indexInGroup = paramInt;
    }
    
    private boolean _handleDownloadFinish(byte[] paramArrayOfByte, String paramString)
    {
      if (ImagePool.this.mStragery != null) {}
      for (Object localObject = ImagePool.this.mStragery.decideUrl(paramString);; localObject = paramString)
      {
        if ((this.m_group != null) && (paramArrayOfByte != null)) {
          new StringBuilder().append(this.m_group.getGroupName()).append("|").append(paramArrayOfByte.length).append("|").append((String)localObject).toString();
        }
        if (this.m_image == null)
        {
          TaoLog.Logw("TaoSdk.ImgPool", "null pointer m_image in _handleDownloadFinish url:" + paramString);
          allFeedImage(0, paramString);
          return false;
        }
        if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
        {
          allFeedImage(-1, paramString);
          return false;
        }
        if (ImagePool.this.m_IC != null) {
          new StringBuilder().append("write to file cache url:").append((String)localObject).append(" data len=").append(paramArrayOfByte.length).append(" type=").append(this.m_image.getCachePolicy()).toString();
        }
        for (boolean bool = ImagePool.this.m_IC.saveData((String)localObject, paramArrayOfByte, this.m_image.getCachePolicy());; bool = false)
        {
          if (!bool)
          {
            ImageHandler localImageHandler = ImagePool.this._getImageHandler(paramString, this.m_image.getCachePolicy(), this.m_image.getBitmapCreator());
            if ((localImageHandler.isRecyceled()) || (localImageHandler.getDrawable() == null))
            {
              paramArrayOfByte = ImagePool._createTBDrawable(paramArrayOfByte, (String)localObject);
              if (paramArrayOfByte == null) {
                break label353;
              }
              bool = localImageHandler.setDrawable(paramArrayOfByte, false);
              if ((ImagePool.this.m_memCahce.add(localImageHandler)) && (bool))
              {
                localObject = ImagePool.this.stat;
                ((ImagePool.BitmapStatics)localObject).createNum += 1;
                localObject = ImagePool.this.stat;
                int i = ((ImagePool.BitmapStatics)localObject).createSize;
                ((ImagePool.BitmapStatics)localObject).createSize = (paramArrayOfByte.bitmapSize() + i);
              }
            }
            allFeedImage(0, paramString);
            return true;
            label353:
            allFeedImage(-2, paramString);
            return false;
          }
          allFeedImage(0, paramString);
          return true;
        }
      }
    }
    
    private void allFeedImage(int paramInt, String paramString)
    {
      if (this.m_group != null) {
        this.m_group.feedImage(paramInt, paramString, this.m_indexInGroup);
      }
      if (this.m_feedQueue != null) {
        synchronized (this.m_feedQueue)
        {
          Object[] arrayOfObject = this.m_feedQueue.toArray();
          int j = arrayOfObject.length;
          int i = 0;
          if (i < j)
          {
            ??? = (FeedImageListener)arrayOfObject[i];
            ((FeedImageListener)???).m_group.feedImage(paramInt, paramString, ((FeedImageListener)???).m_index);
            i += 1;
          }
        }
      }
    }
    
    private void joinExecutor(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
    {
      if (this.m_feedQueue == null) {
        this.m_feedQueue = new ArrayList();
      }
      synchronized (this.m_feedQueue)
      {
        this.m_feedQueue.add(new FeedImageListener(paramImageHandler, paramImageGroup, paramInt));
        return;
      }
    }
    
    private final void lockExecutor()
    {
      try
      {
        this.m_b_executing = true;
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    
    private final void releaseIDL()
    {
      try
      {
        if (this.m_idl != null)
        {
          this.m_idl.destroy();
          this.m_idl = null;
        }
        this.m_b_executing = false;
        this.m_image = null;
        if (this.m_group != null)
        {
          this.m_group.subLoadingCount();
          this.m_group = null;
        }
        this.m_feedQueue = null;
        return;
      }
      finally {}
    }
    
    private void setGroup(ImageGroup paramImageGroup)
    {
      try
      {
        this.m_group = paramImageGroup;
        return;
      }
      finally
      {
        paramImageGroup = finally;
        throw paramImageGroup;
      }
    }
    
    private final void setImageHandler(ImageHandler paramImageHandler, int paramInt)
    {
      try
      {
        this.m_image = paramImageHandler;
        this.m_indexInGroup = paramInt;
        return;
      }
      finally
      {
        paramImageHandler = finally;
        throw paramImageHandler;
      }
    }
    
    private final void start()
    {
      try
      {
        if (this.m_idl == null) {
          this.m_idl = new ImageDownloader(this, ImagePool.this.m_context);
        }
        String str2 = this.m_image.URI();
        String str1 = str2;
        if (ImagePool.this.mStragery != null) {
          str1 = ImagePool.this.mStragery.decideUrl(str2);
        }
        this.m_idl.setURL(this.m_image.URI(), str1, this.m_group.getCachePolicy());
        this.m_idl.startDownload();
        this.m_b_executing = true;
        if (this.m_group != null) {
          this.m_group.addLoadingCount();
        }
        return;
      }
      finally {}
    }
    
    private final void stop()
    {
      if (this.m_idl != null) {
        this.m_idl.stop();
      }
    }
    
    public void notify(int paramInt, byte[] arg2, String arg3)
    {
      switch (paramInt)
      {
      case 1019: 
      default: 
      case 1016: 
        try
        {
          new StringBuilder().append("ImagePool.ImageExecutor.notify() msg ").append(paramInt).append(" not handled").toString();
        }
        finally
        {
          synchronized (ImagePool.this.m_downloaders)
          {
            for (;;)
            {
              new StringBuilder().append("ImageExecute m_b_executing false url  ").append(???).toString();
              this.m_b_executing = false;
              if (this.m_feedQueue != null) {}
              synchronized (this.m_feedQueue)
              {
                this.m_feedQueue = null;
                this.m_image = null;
                if (ImagePool.this.m_downloaders.size() > ImagePool.this.m_concurrentDownloadCount)
                {
                  releaseIDL();
                  ImagePool.this.m_downloaders.remove(this);
                  ImagePool.this.reSchedule();
                  return;
                  ImageFullLinkStatistics.statisticEndDownload(???, System.currentTimeMillis());
                  _handleDownloadFinish(???, ???);
                  continue;
                  localObject1 = finally;
                }
              }
            }
          }
        }
      }
      for (;;)
      {
        synchronized (ImagePool.this.m_downloaders)
        {
          new StringBuilder().append("ImageExecute m_b_executing false url  ").append(???).toString();
          this.m_b_executing = false;
          if (this.m_feedQueue == null) {}
        }
        if (this.m_group != null)
        {
          this.m_group.subLoadingCount();
          this.m_group = null;
        }
      }
    }
    
    public void onProgress(String paramString, int paramInt1, int paramInt2)
    {
      if ((this.m_group != null) && (this.m_image != null))
      {
        this.m_group.onProgress(paramString, paramInt1, paramInt2, this.m_image.URI(), this.m_indexInGroup);
        return;
      }
      TaoLog.Logw("TaoSdk.ImgPool", "!!!null point in onProgress");
    }
    
    class FeedImageListener
    {
      ImageGroup m_group;
      int m_index;
      
      FeedImageListener(ImageHandler paramImageHandler, ImageGroup paramImageGroup, int paramInt)
      {
        this.m_group = paramImageGroup;
        this.m_index = paramInt;
      }
    }
  }
  
  private class ImageMemCache
    implements MemoryManager.MemoryManagerListener
  {
    private static final int MEM_KEEP_SIZE = 12;
    private final int MAX_CACHE_SIZE = 2097152;
    IImageMemCache iMemCache;
    private final HashMap<String, ImageHandler> m_imagesHash = new HashMap();
    private final ArrayList<ImageHandler> m_imagesList = new ArrayList();
    private int m_memCacheMaxSize = 2097152;
    private ImagePoolPrinter m_printer;
    
    public ImageMemCache()
    {
      MemoryManager.getInstance().addListener("ImagePool", this);
    }
    
    private boolean _insideAdd(ImageHandler paramImageHandler)
    {
      if ((paramImageHandler == null) || (paramImageHandler.m_dr == null)) {
        return false;
      }
      String str = paramImageHandler.URI();
      LRUBitmapRecycle(paramImageHandler.m_dr.bitmapSize());
      Object localObject = ImagePool.UUIDUrl(str);
      try
      {
        if (this.m_imagesHash.containsKey(localObject))
        {
          localObject = (ImageHandler)this.m_imagesHash.get(localObject);
          if ((localObject != null) && (localObject != paramImageHandler))
          {
            new StringBuilder().append("potential memory leak a different image handler already in mem url:").append(str).toString();
            ((ImageHandler)localObject).printState(false, this.m_printer);
          }
          return false;
        }
        this.m_imagesList.add(0, paramImageHandler);
        this.m_imagesHash.put(localObject, paramImageHandler);
        new StringBuilder().append("!!! ImageMemCache.add() added ").append(str).append(" to mem cache").toString();
        return true;
      }
      finally {}
    }
    
    private void _insideForceBitmapRecycle(int paramInt)
    {
      for (;;)
      {
        try
        {
          ImagePool.this.stat.report("before recycle");
          int m = this.m_imagesList.size();
          int i = 0;
          int j = m - 1;
          int k;
          if (j >= 0)
          {
            Object localObject1 = (ImageHandler)this.m_imagesList.get(j);
            if ((localObject1 != null) && (((ImageHandler)localObject1).tryRecycle()))
            {
              localObject1 = ImagePool.UUIDUrl(((ImageHandler)localObject1).URI());
              this.m_imagesHash.remove(localObject1);
              this.m_imagesList.remove(j);
              ImagePool.this.m_HandlerMap.remove(localObject1);
              TaoLog.Logw("TaoSdk.ImgPool", "!!! ForceBitmapRecycle remove:" + (String)localObject1);
              k = i + 1;
              i = k;
              if (paramInt + k >= m)
              {
                ImagePool.this.stat.report("after recycle recycle count" + k);
                if (m - k > 25) {
                  dumpMemory(true);
                }
                return;
              }
            }
            j -= 1;
          }
          else
          {
            k = i;
          }
        }
        finally {}
      }
    }
    
    /* Error */
    private boolean _insideRemove(ImageHandler paramImageHandler)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +5 -> 6
      //   4: iconst_0
      //   5: ireturn
      //   6: aload_0
      //   7: monitorenter
      //   8: aload_0
      //   9: monitorexit
      //   10: iconst_0
      //   11: ireturn
      //   12: astore_1
      //   13: aload_0
      //   14: monitorexit
      //   15: aload_1
      //   16: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	17	0	this	ImageMemCache
      //   0	17	1	paramImageHandler	ImageHandler
      // Exception table:
      //   from	to	target	type
      //   8	10	12	finally
    }
    
    private boolean _outAdd(ImageHandler paramImageHandler)
    {
      if (this.iMemCache == null) {
        return false;
      }
      this.iMemCache.set(paramImageHandler.URI(), (BitmapDrawable)paramImageHandler.getDrawable());
      return true;
    }
    
    private void _outForceBitmapRecycle(int paramInt) {}
    
    private boolean _outRemove(ImageHandler paramImageHandler)
    {
      return this.iMemCache != null;
    }
    
    private ImageHandler outGet(String paramString)
    {
      if (this.iMemCache == null) {
        TaoLog.Loge("TaoSdk.ImgPool", "not set the memory cache!!!!");
      }
      ImageHandler localImageHandler;
      do
      {
        return null;
        localImageHandler = new ImageHandler(paramString, 2);
        this.iMemCache.get(paramString, new ImagePool.ImageMemCache.1GotCb(this, localImageHandler));
      } while (localImageHandler.getDrawable() == null);
      return localImageHandler;
    }
    
    private void printerDebugInfo(String paramString)
    {
      if (this.m_printer != null) {
        this.m_printer.printState(paramString);
      }
    }
    
    protected void ForceBitmapRecycle(int paramInt)
    {
      if (ImagePool.this.insideMemoryCacheFlag)
      {
        _insideForceBitmapRecycle(paramInt);
        return;
      }
      _outForceBitmapRecycle(paramInt);
    }
    
    protected void ForceBitmapRecycleAll()
    {
      ForceBitmapRecycle(0);
    }
    
    protected void LRUBitmapRecycle(int paramInt)
    {
      if (ImagePool.this.stat.createSize - ImagePool.this.stat.destroySize >= this.m_memCacheMaxSize) {
        _LRUBitmapRecycle(paramInt);
      }
    }
    
    protected void _LRUBitmapRecycle(int paramInt)
    {
      int j = 0;
      if (!ImagePool.this.insideMemoryCacheFlag) {
        return;
      }
      int m = ImagePool.this.stat.destroySize;
      int k;
      int i;
      try
      {
        ImagePool.this.stat.report("before recycle");
        k = this.m_imagesList.size() - 1;
        i = 0;
        if (k >= 0)
        {
          Object localObject1 = (ImageHandler)this.m_imagesList.get(k);
          if ((localObject1 == null) || (!((ImageHandler)localObject1).isRecyclable())) {
            break label244;
          }
          if ((j == 0) && (((ImageHandler)localObject1).tryRecycle()))
          {
            localObject1 = ImagePool.UUIDUrl(((ImageHandler)localObject1).URI());
            this.m_imagesHash.remove(localObject1);
            this.m_imagesList.remove(k);
            ImagePool.this.m_HandlerMap.remove(localObject1);
            new StringBuilder().append("!!! _LRUBitmapRecycle remove:").append((String)localObject1).toString();
            if (ImagePool.this.stat.destroySize - m <= paramInt) {
              break label235;
            }
            j = 1;
            break label235;
          }
        }
        else
        {
          ImagePool.this.stat.report("after recycle recycle count" + i);
          if (i > 25) {
            dumpMemory(true);
          }
          return;
        }
      }
      finally {}
      for (;;)
      {
        label232:
        label235:
        k -= 1;
        break;
        label244:
        if (localObject2 == null) {
          break label232;
        }
        i += 1;
      }
    }
    
    protected boolean add(ImageHandler paramImageHandler)
    {
      if (ImagePool.this.insideMemoryCacheFlag) {
        return _insideAdd(paramImageHandler);
      }
      return _outAdd(paramImageHandler);
    }
    
    public void dumpMemory(boolean paramBoolean)
    {
      if (!ImagePool.this.insideMemoryCacheFlag) {
        return;
      }
      ImagePool.this.stat.report("dumpmemory");
      new StringBuilder().append("---------------dump image  in memCache:").append(this.m_imagesList.size()).append(",hash size").append(this.m_imagesHash.size()).toString();
      printerDebugInfo("start dump image in image pool memory");
      int i = 0;
      for (;;)
      {
        int j;
        try
        {
          j = this.m_imagesList.size() - 1;
          if (j >= 0)
          {
            ImageHandler localImageHandler = (ImageHandler)this.m_imagesList.get(j);
            if (localImageHandler != null)
            {
              if (!localImageHandler.isRecyceled())
              {
                localImageHandler.printState(paramBoolean, this.m_printer);
                i = localImageHandler.bitmapSize() + i;
              }
              else
              {
                new StringBuilder().append("drawable is recycled in memory").append(localImageHandler).toString();
              }
            }
            else {
              new StringBuilder().append("drawable is null in memory").append(localImageHandler).toString();
            }
          }
          else
          {
            printerDebugInfo("handler size in map:" + ImagePool.this.m_HandlerMap.size());
            printerDebugInfo("end dump image in image pool memory");
            new StringBuilder().append("---------------dump image end totalsize:").append(i).toString();
            return;
          }
        }
        finally {}
        j -= 1;
      }
    }
    
    public ImagePoolPrinter getDebugPrinter()
    {
      return this.m_printer;
    }
    
    public IImageMemCache getiMemCache()
    {
      return this.iMemCache;
    }
    
    public int onGetMemory()
    {
      if (!ImagePool.this.insideMemoryCacheFlag) {
        return 0;
      }
      return ImagePool.this.stat.createSize - ImagePool.this.stat.destroySize;
    }
    
    public void onLowMemory()
    {
      if (!ImagePool.this.insideMemoryCacheFlag) {
        return;
      }
      ForceBitmapRecycle(12);
    }
    
    public void onSetMaxMemory(int paramInt)
    {
      this.m_memCacheMaxSize = paramInt;
      new StringBuilder().append("imagepool memory limit set:").append(this.m_memCacheMaxSize).toString();
    }
    
    protected boolean remove(ImageHandler paramImageHandler)
    {
      if (ImagePool.this.insideMemoryCacheFlag) {
        return _insideRemove(paramImageHandler);
      }
      return _outRemove(paramImageHandler);
    }
    
    public void setDebugPrinter(ImagePoolPrinter paramImagePoolPrinter)
    {
      this.m_printer = paramImagePoolPrinter;
    }
    
    public void setiMemCache(IImageMemCache paramIImageMemCache)
    {
      this.iMemCache = paramIImageMemCache;
    }
  }
  
  private static class SingletonHolder
  {
    private static ImagePool instance = new ImagePool(null);
    
    private SingletonHolder() {}
  }
}
