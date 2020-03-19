package com.keyboard.common.yantextmodule.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class YanTextDataManager
{
  private static final int MSG_NOTIFY_BASE = 9999;
  public static final int MSG_NOTIFY_SCAN_YANTEXT_DONE = 10002;
  public static final int MSG_NOTIFY_SCAN_YANTEXT_PKG = 10001;
  public static final int MSG_NOTIFY_SCAN_YANTEXT_START = 10000;
  private static final int MSG_SCAN_YANTEXT = 100;
  private static final String TAG = YanTextDataManager.class.getSimpleName();
  public static final int TYPE_ALL = 3;
  public static final int TYPE_EXTERNAL = 2;
  public static final int TYPE_INTERNAL = 1;
  private int mContentWidth = 0;
  private Context mContext = null;
  private boolean mCreateYanTextListFlag = true;
  private int mInternalResId = 0;
  private ArrayList<Handler> mListener = null;
  private String mPkgPrefix = null;
  private ArrayList<String> mRecentList;
  private Paint mTextPaint;
  private Handler mWorkHandler = null;
  private HandlerThread mWorkThread = null;
  
  public YanTextDataManager(Context paramContext, int paramInt1, int paramInt2, String paramString, Paint paramPaint)
  {
    this.mContext = paramContext;
    this.mInternalResId = paramInt2;
    this.mPkgPrefix = paramString;
    this.mTextPaint = paramPaint;
    this.mContentWidth = paramInt1;
    this.mListener = new ArrayList();
    initWorkThread();
  }
  
  private String[] getFileNameArray(Context paramContext, int paramInt)
  {
    Object localObject2 = null;
    localObject1 = localObject2;
    try
    {
      TypedArray localTypedArray = paramContext.getResources().obtainTypedArray(paramInt);
      localObject1 = localObject2;
      int i = localTypedArray.length();
      localObject1 = localObject2;
      paramContext = new String[i / 3];
      localObject1 = paramContext;
      if (i % 3 == 0)
      {
        paramInt = 0;
        for (;;)
        {
          localObject1 = paramContext;
          if (paramInt >= i) {
            break;
          }
          localObject1 = paramContext;
          paramContext[(paramInt / 3)] = localTypedArray.getString(paramInt + 1);
          paramInt += 3;
        }
      }
      return localObject1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private String[] getNameArray(Context paramContext, int paramInt)
  {
    Object localObject2 = null;
    localObject1 = localObject2;
    try
    {
      TypedArray localTypedArray = paramContext.getResources().obtainTypedArray(paramInt);
      localObject1 = localObject2;
      int i = localTypedArray.length();
      localObject1 = localObject2;
      paramContext = new String[i / 3];
      localObject1 = paramContext;
      if (i % 3 == 0)
      {
        paramInt = 0;
        for (;;)
        {
          localObject1 = paramContext;
          if (paramInt >= i) {
            break;
          }
          localObject1 = paramContext;
          paramContext[(paramInt / 3)] = localTypedArray.getString(paramInt + 2);
          paramInt += 3;
        }
      }
      return localObject1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private String[] getPkgNameArray(Context paramContext, int paramInt)
  {
    Object localObject2 = null;
    localObject1 = localObject2;
    try
    {
      TypedArray localTypedArray = paramContext.getResources().obtainTypedArray(paramInt);
      localObject1 = localObject2;
      int i = localTypedArray.length();
      localObject1 = localObject2;
      paramContext = new String[i / 3];
      localObject1 = paramContext;
      if (i % 3 == 0)
      {
        paramInt = 0;
        for (;;)
        {
          localObject1 = paramContext;
          if (paramInt >= i) {
            break;
          }
          localObject1 = paramContext;
          paramContext[(paramInt / 3)] = localTypedArray.getString(paramInt);
          paramInt += 3;
        }
      }
      return localObject1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void initWorkThread()
  {
    if (this.mWorkThread != null) {
      return;
    }
    this.mWorkThread = new HandlerThread(TAG + hashCode());
    this.mWorkThread.start();
    this.mWorkHandler = new Handler(this.mWorkThread.getLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          return false;
        }
        YanTextDataManager.this.onWorkLoadYanTextEmoji(paramAnonymousMessage.arg1, paramAnonymousMessage.arg2, (String)paramAnonymousMessage.obj);
        return true;
      }
    });
  }
  
  private void notifyListener(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
  {
    Iterator localIterator = this.mListener.iterator();
    while (localIterator.hasNext())
    {
      Handler localHandler = (Handler)localIterator.next();
      localHandler.sendMessage(localHandler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject));
    }
  }
  
  private void onWorkLoadYanTextEmoji(int paramInt1, int paramInt2, String paramString)
  {
    notifyListener(10000, 0, 0, null);
    if ((paramInt1 & 0x1) != 0) {
      scanInternalPkg(paramInt2);
    }
    if ((paramInt1 & 0x2) != 0) {
      scanExternalPkg(paramString);
    }
    notifyListener(10002, 0, 0, null);
  }
  
  private String readAsSetsJsonFile(Context paramContext, String paramString)
  {
    try
    {
      paramString = paramContext.getAssets().open(paramString, 3);
      InputStreamReader localInputStreamReader = new InputStreamReader(paramString, "UTF-8");
      char[] arrayOfChar = new char[4];
      for (paramContext = new String(""); localInputStreamReader.read(arrayOfChar) > 0; paramContext = paramContext + String.valueOf(arrayOfChar)) {}
      paramString.close();
      localInputStreamReader.close();
      paramContext = paramContext.toString();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  /* Error */
  private String readYanTextData(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_1
    //   7: invokevirtual 233	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 7
    //   12: aload 7
    //   14: ifnonnull +5 -> 19
    //   17: aconst_null
    //   18: areturn
    //   19: aload 6
    //   21: astore_1
    //   22: aload 7
    //   24: aload_2
    //   25: invokevirtual 239	android/content/pm/PackageManager:getResourcesForApplication	(Ljava/lang/String;)Landroid/content/res/Resources;
    //   28: invokevirtual 240	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   31: aload_3
    //   32: invokevirtual 243	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   35: astore_2
    //   36: aload_2
    //   37: astore_1
    //   38: aload_2
    //   39: astore 5
    //   41: aload_2
    //   42: invokevirtual 246	java/io/InputStream:available	()I
    //   45: istore 4
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: astore 5
    //   52: iload 4
    //   54: newarray byte
    //   56: astore_3
    //   57: aload_2
    //   58: astore_1
    //   59: aload_2
    //   60: astore 5
    //   62: aload_2
    //   63: aload_3
    //   64: iconst_0
    //   65: iload 4
    //   67: invokevirtual 249	java/io/InputStream:read	([BII)I
    //   70: pop
    //   71: aload_2
    //   72: astore_1
    //   73: aload_2
    //   74: astore 5
    //   76: new 112	java/lang/String
    //   79: dup
    //   80: aload_3
    //   81: ldc -50
    //   83: invokespecial 252	java/lang/String:<init>	([BLjava/lang/String;)V
    //   86: astore_3
    //   87: aload_2
    //   88: ifnull +7 -> 95
    //   91: aload_2
    //   92: invokevirtual 225	java/io/InputStream:close	()V
    //   95: aload_3
    //   96: areturn
    //   97: astore_2
    //   98: aload_1
    //   99: astore 5
    //   101: aload_2
    //   102: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   105: aload_1
    //   106: ifnull +7 -> 113
    //   109: aload_1
    //   110: invokevirtual 225	java/io/InputStream:close	()V
    //   113: aconst_null
    //   114: areturn
    //   115: astore_1
    //   116: aload 5
    //   118: ifnull +8 -> 126
    //   121: aload 5
    //   123: invokevirtual 225	java/io/InputStream:close	()V
    //   126: aload_1
    //   127: athrow
    //   128: astore_1
    //   129: goto -34 -> 95
    //   132: astore_1
    //   133: goto -20 -> 113
    //   136: astore_2
    //   137: goto -11 -> 126
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	this	YanTextDataManager
    //   0	140	1	paramContext	Context
    //   0	140	2	paramString1	String
    //   0	140	3	paramString2	String
    //   45	21	4	i	int
    //   1	121	5	localObject1	Object
    //   4	16	6	localObject2	Object
    //   10	13	7	localPackageManager	android.content.pm.PackageManager
    // Exception table:
    //   from	to	target	type
    //   22	36	97	java/lang/Exception
    //   41	47	97	java/lang/Exception
    //   52	57	97	java/lang/Exception
    //   62	71	97	java/lang/Exception
    //   76	87	97	java/lang/Exception
    //   22	36	115	finally
    //   41	47	115	finally
    //   52	57	115	finally
    //   62	71	115	finally
    //   76	87	115	finally
    //   101	105	115	finally
    //   91	95	128	java/lang/Exception
    //   109	113	132	java/lang/Exception
    //   121	126	136	java/lang/Exception
  }
  
  /* Error */
  private void scanExternalPkg(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 60	com/keyboard/common/yantextmodule/data/YanTextDataManager:mContext	Landroid/content/Context;
    //   4: invokevirtual 233	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: sipush 8192
    //   10: invokevirtual 256	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   13: astore_3
    //   14: aload_3
    //   15: invokeinterface 259 1 0
    //   20: astore 4
    //   22: aload 4
    //   24: invokeinterface 169 1 0
    //   29: ifeq +158 -> 187
    //   32: aload 4
    //   34: invokeinterface 173 1 0
    //   39: checkcast 261	android/content/pm/ApplicationInfo
    //   42: getfield 264	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   45: astore 5
    //   47: new 266	com/keyboard/common/yantextmodule/data/YanTextPkg
    //   50: dup
    //   51: invokespecial 267	com/keyboard/common/yantextmodule/data/YanTextPkg:<init>	()V
    //   54: astore_2
    //   55: aload 5
    //   57: aload_1
    //   58: invokevirtual 271	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   61: ifeq -39 -> 22
    //   64: aload_2
    //   65: aload 5
    //   67: putfield 274	com/keyboard/common/yantextmodule/data/YanTextPkg:mPkgName	Ljava/lang/String;
    //   70: aload_0
    //   71: getfield 60	com/keyboard/common/yantextmodule/data/YanTextDataManager:mContext	Landroid/content/Context;
    //   74: ldc_w 276
    //   77: aload 5
    //   79: invokestatic 280	com/keyboard/common/utilsmodule/AccessResUtils:getString	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   82: astore 6
    //   84: aload 6
    //   86: ifnull -64 -> 22
    //   89: aload_2
    //   90: aload 6
    //   92: putfield 283	com/keyboard/common/yantextmodule/data/YanTextPkg:mName	Ljava/lang/String;
    //   95: aload_0
    //   96: aload_0
    //   97: getfield 60	com/keyboard/common/yantextmodule/data/YanTextDataManager:mContext	Landroid/content/Context;
    //   100: aload 5
    //   102: ldc_w 285
    //   105: invokespecial 287	com/keyboard/common/yantextmodule/data/YanTextDataManager:readYanTextData	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   108: astore 5
    //   110: aload 5
    //   112: ifnull -90 -> 22
    //   115: aload_2
    //   116: aload 5
    //   118: invokestatic 293	com/keyboard/common/yantextmodule/data/JsonDecoder:getItemDataArray	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   121: putfield 296	com/keyboard/common/yantextmodule/data/YanTextPkg:mOriginalYanTextList	Ljava/util/ArrayList;
    //   124: aload_0
    //   125: getfield 64	com/keyboard/common/yantextmodule/data/YanTextDataManager:mCreateYanTextListFlag	Z
    //   128: ifeq +22 -> 150
    //   131: aload_2
    //   132: aload_0
    //   133: getfield 62	com/keyboard/common/yantextmodule/data/YanTextDataManager:mContentWidth	I
    //   136: aload_0
    //   137: getfield 76	com/keyboard/common/yantextmodule/data/YanTextDataManager:mTextPaint	Landroid/graphics/Paint;
    //   140: aload_2
    //   141: getfield 296	com/keyboard/common/yantextmodule/data/YanTextPkg:mOriginalYanTextList	Ljava/util/ArrayList;
    //   144: invokestatic 302	com/keyboard/common/yantextmodule/data/ListConvert:getConvertDataArrayList	(ILandroid/graphics/Paint;Ljava/util/ArrayList;)Ljava/util/ArrayList;
    //   147: putfield 305	com/keyboard/common/yantextmodule/data/YanTextPkg:mYanTextList	Ljava/util/ArrayList;
    //   150: new 307	com/keyboard/common/yantextmodule/data/YanTextPagerItem
    //   153: dup
    //   154: invokespecial 308	com/keyboard/common/yantextmodule/data/YanTextPagerItem:<init>	()V
    //   157: astore 5
    //   159: aload 5
    //   161: aload_2
    //   162: putfield 312	com/keyboard/common/yantextmodule/data/YanTextPagerItem:mYanTextPkg	Lcom/keyboard/common/yantextmodule/data/YanTextPkg;
    //   165: aload_0
    //   166: sipush 10001
    //   169: iconst_0
    //   170: iconst_0
    //   171: aload 5
    //   173: invokespecial 183	com/keyboard/common/yantextmodule/data/YanTextDataManager:notifyListener	(IIILjava/lang/Object;)V
    //   176: goto -154 -> 22
    //   179: astore_2
    //   180: aload_2
    //   181: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   184: goto -162 -> 22
    //   187: aload_3
    //   188: invokeinterface 315 1 0
    //   193: return
    //   194: astore_2
    //   195: goto -15 -> 180
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	198	0	this	YanTextDataManager
    //   0	198	1	paramString	String
    //   54	108	2	localYanTextPkg	YanTextPkg
    //   179	2	2	localException1	Exception
    //   194	1	2	localException2	Exception
    //   13	175	3	localList	java.util.List
    //   20	13	4	localIterator	Iterator
    //   45	127	5	localObject	Object
    //   82	9	6	str	String
    // Exception table:
    //   from	to	target	type
    //   55	84	179	java/lang/Exception
    //   89	110	179	java/lang/Exception
    //   115	150	179	java/lang/Exception
    //   150	159	179	java/lang/Exception
    //   159	176	194	java/lang/Exception
  }
  
  private void scanInternalPkg(int paramInt)
  {
    String[] arrayOfString1 = getFileNameArray(this.mContext, paramInt);
    String[] arrayOfString2 = getNameArray(this.mContext, paramInt);
    String[] arrayOfString3 = getPkgNameArray(this.mContext, paramInt);
    if ((arrayOfString1 != null) && (arrayOfString2 != null) && (arrayOfString3 != null))
    {
      paramInt = 0;
      if (paramInt < arrayOfString1.length)
      {
        YanTextPagerItem localYanTextPagerItem = new YanTextPagerItem();
        localYanTextPagerItem.mYanTextPkg = new YanTextPkg();
        localYanTextPagerItem.mYanTextPkg.mFileName = arrayOfString1[paramInt];
        localYanTextPagerItem.mYanTextPkg.mPkgName = arrayOfString3[paramInt];
        localYanTextPagerItem.mYanTextPkg.mName = arrayOfString2[paramInt];
        if (paramInt == 0)
        {
          this.mRecentList = JsonDecoder.getItemDataArray(JsonDecoder.getRecentFileData(this.mContext.getApplicationContext().getFilesDir() + "/" + arrayOfString1[0]));
          localYanTextPagerItem.mYanTextPkg.mOriginalYanTextList = this.mRecentList;
          if (this.mCreateYanTextListFlag) {
            localYanTextPagerItem.mYanTextPkg.mYanTextList = ListConvert.getConvertDataArrayList(this.mContentWidth, this.mTextPaint, localYanTextPagerItem.mYanTextPkg.mOriginalYanTextList);
          }
        }
        for (;;)
        {
          notifyListener(10001, 0, 0, localYanTextPagerItem);
          paramInt += 1;
          break;
          localYanTextPagerItem.mYanTextPkg.mOriginalYanTextList = JsonDecoder.getItemDataArray(readAsSetsJsonFile(this.mContext, localYanTextPagerItem.mYanTextPkg.mFileName));
          if (this.mCreateYanTextListFlag) {
            localYanTextPagerItem.mYanTextPkg.mYanTextList = ListConvert.getConvertDataArrayList(this.mContentWidth, this.mTextPaint, localYanTextPagerItem.mYanTextPkg.mOriginalYanTextList);
          }
        }
      }
    }
  }
  
  public void addListener(Handler paramHandler)
  {
    if (!this.mListener.contains(paramHandler)) {
      this.mListener.add(paramHandler);
    }
  }
  
  public void destroy()
  {
    this.mWorkThread.quit();
    this.mListener.clear();
    this.mWorkThread = null;
    this.mWorkHandler = null;
  }
  
  public ArrayList<String> getRecentList()
  {
    if (this.mRecentList != null) {
      return this.mRecentList;
    }
    return null;
  }
  
  public void postScanYanTextPkgTask(int paramInt)
  {
    Message localMessage = this.mWorkHandler.obtainMessage(100, paramInt, this.mInternalResId, this.mPkgPrefix);
    this.mWorkHandler.sendMessage(localMessage);
  }
  
  public void removelistener(Handler paramHandler)
  {
    if (!this.mListener.contains(paramHandler)) {
      this.mListener.remove(paramHandler);
    }
  }
  
  public void setContentWidth(int paramInt)
  {
    this.mContentWidth = paramInt;
  }
  
  public void setCreateYanTextListFlag(boolean paramBoolean)
  {
    this.mCreateYanTextListFlag = paramBoolean;
  }
}
