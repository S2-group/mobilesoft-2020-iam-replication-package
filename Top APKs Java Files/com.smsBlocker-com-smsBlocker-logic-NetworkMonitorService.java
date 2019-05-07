package com.smsBlocker.logic;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NetworkMonitorService
  extends Service
{
  private Handler a = new e(this);
  
  public NetworkMonitorService() {}
  
  private static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(i)).packageName.equals("com.smsBlockerUnlocker")) {
        return true;
      }
      i += 1;
    }
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.fileList();
    int j = paramContext.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (paramContext[i].equals(paramString)) {
        return true;
      }
      i += 1;
    }
  }
  
  private String b()
  {
    try
    {
      String str = ((TelephonyManager)getSystemService("phone")).getDeviceId();
      return str;
    }
    catch (Exception localException) {}
    return "error";
  }
  
  private static String b(Context paramContext, String paramString)
  {
    paramString = new File(paramContext.getFilesDir().getAbsolutePath(), paramString);
    paramContext = new StringBuilder();
    for (;;)
    {
      try
      {
        paramString = new BufferedReader(new FileReader(paramString));
        str = paramString.readLine();
        if (str != null) {
          continue;
        }
      }
      catch (IOException paramString)
      {
        String str;
        continue;
      }
      return paramContext.toString();
      paramContext.append(str);
    }
  }
  
  private static String c()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException) {}
    return "error";
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  /* Error */
  public void onStart(Intent paramIntent, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: iload_2
    //   3: invokespecial 133	android/app/Service:onStart	(Landroid/content/Intent;I)V
    //   6: aload_0
    //   7: ldc -121
    //   9: invokestatic 137	com/smsBlocker/logic/NetworkMonitorService:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   12: astore_1
    //   13: aload_1
    //   14: ldc -117
    //   16: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   19: ifne +478 -> 497
    //   22: aload_1
    //   23: invokestatic 145	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   26: istore_2
    //   27: aload_0
    //   28: ldc -109
    //   30: invokestatic 149	com/smsBlocker/logic/NetworkMonitorService:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   33: ifne +189 -> 222
    //   36: iload_2
    //   37: bipush 25
    //   39: if_icmplt +183 -> 222
    //   42: invokestatic 155	java/lang/System:currentTimeMillis	()J
    //   45: lstore_3
    //   46: aload_0
    //   47: ldc -121
    //   49: invokestatic 137	com/smsBlocker/logic/NetworkMonitorService:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   52: astore_1
    //   53: aload_1
    //   54: ldc -117
    //   56: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   59: ifne +414 -> 473
    //   62: aload_1
    //   63: invokestatic 145	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   66: istore_2
    //   67: new 101	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   74: iload_2
    //   75: invokevirtual 158	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   78: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: astore_1
    //   82: new 101	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   89: ldc -96
    //   91: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   94: aload_1
    //   95: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: ldc -91
    //   100: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: astore_1
    //   107: aload_0
    //   108: ldc -89
    //   110: invokevirtual 168	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   113: checkcast 170	android/app/NotificationManager
    //   116: astore 5
    //   118: new 172	android/content/Intent
    //   121: dup
    //   122: aload_0
    //   123: ldc -82
    //   125: invokespecial 177	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   128: astore 6
    //   130: aload 6
    //   132: ldc -78
    //   134: invokevirtual 182	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   137: pop
    //   138: aload_0
    //   139: iconst_0
    //   140: aload 6
    //   142: iconst_0
    //   143: invokestatic 188	android/app/PendingIntent:getActivity	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   146: astore 6
    //   148: new 190	android/app/Notification
    //   151: dup
    //   152: ldc -65
    //   154: ldc -63
    //   156: lload_3
    //   157: invokespecial 196	android/app/Notification:<init>	(ILjava/lang/CharSequence;J)V
    //   160: astore 7
    //   162: aload 7
    //   164: aload_0
    //   165: ldc -63
    //   167: aload_1
    //   168: aload 6
    //   170: invokevirtual 200	android/app/Notification:setLatestEventInfo	(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V
    //   173: aload 7
    //   175: aload 7
    //   177: getfield 204	android/app/Notification:flags	I
    //   180: iconst_2
    //   181: ior
    //   182: putfield 204	android/app/Notification:flags	I
    //   185: aload 5
    //   187: iconst_2
    //   188: aload 7
    //   190: invokevirtual 208	android/app/NotificationManager:notify	(ILandroid/app/Notification;)V
    //   193: new 210	java/io/OutputStreamWriter
    //   196: dup
    //   197: aload_0
    //   198: ldc -109
    //   200: iconst_0
    //   201: invokevirtual 214	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   204: invokespecial 217	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   207: astore_1
    //   208: aload_1
    //   209: ldc -37
    //   211: invokevirtual 223	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   214: aload_1
    //   215: invokevirtual 226	java/io/OutputStreamWriter:flush	()V
    //   218: aload_1
    //   219: invokevirtual 229	java/io/OutputStreamWriter:close	()V
    //   222: aload_0
    //   223: ldc -25
    //   225: invokestatic 149	com/smsBlocker/logic/NetworkMonitorService:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   228: ifne +209 -> 437
    //   231: aload_0
    //   232: ldc -23
    //   234: invokestatic 137	com/smsBlocker/logic/NetworkMonitorService:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   237: astore_1
    //   238: aload_1
    //   239: ldc -117
    //   241: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   244: ifne +193 -> 437
    //   247: aload_1
    //   248: invokestatic 145	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   251: bipush 15
    //   253: if_icmple +184 -> 437
    //   256: invokestatic 155	java/lang/System:currentTimeMillis	()J
    //   259: lstore_3
    //   260: aload_0
    //   261: ldc -121
    //   263: invokestatic 137	com/smsBlocker/logic/NetworkMonitorService:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   266: astore_1
    //   267: aload_1
    //   268: ldc -117
    //   270: invokevirtual 63	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   273: ifne +206 -> 479
    //   276: aload_1
    //   277: invokestatic 145	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   280: istore_2
    //   281: new 101	java/lang/StringBuilder
    //   284: dup
    //   285: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   288: iload_2
    //   289: invokevirtual 158	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   292: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   295: astore_1
    //   296: new 101	java/lang/StringBuilder
    //   299: dup
    //   300: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   303: ldc -96
    //   305: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   308: aload_1
    //   309: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: ldc -91
    //   314: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   320: astore_1
    //   321: aload_0
    //   322: ldc -89
    //   324: invokevirtual 168	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   327: checkcast 170	android/app/NotificationManager
    //   330: astore 5
    //   332: new 172	android/content/Intent
    //   335: dup
    //   336: aload_0
    //   337: ldc -21
    //   339: invokespecial 177	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   342: astore 6
    //   344: aload 6
    //   346: ldc -78
    //   348: invokevirtual 182	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   351: pop
    //   352: aload_0
    //   353: iconst_0
    //   354: aload 6
    //   356: iconst_0
    //   357: invokestatic 188	android/app/PendingIntent:getActivity	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   360: astore 6
    //   362: new 190	android/app/Notification
    //   365: dup
    //   366: ldc -65
    //   368: ldc -63
    //   370: lload_3
    //   371: invokespecial 196	android/app/Notification:<init>	(ILjava/lang/CharSequence;J)V
    //   374: astore 7
    //   376: aload 7
    //   378: aload_0
    //   379: ldc -63
    //   381: aload_1
    //   382: aload 6
    //   384: invokevirtual 200	android/app/Notification:setLatestEventInfo	(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V
    //   387: aload 7
    //   389: aload 7
    //   391: getfield 204	android/app/Notification:flags	I
    //   394: iconst_2
    //   395: ior
    //   396: putfield 204	android/app/Notification:flags	I
    //   399: aload 5
    //   401: bipush 6
    //   403: aload 7
    //   405: invokevirtual 208	android/app/NotificationManager:notify	(ILandroid/app/Notification;)V
    //   408: new 210	java/io/OutputStreamWriter
    //   411: dup
    //   412: aload_0
    //   413: ldc -25
    //   415: iconst_0
    //   416: invokevirtual 236	com/smsBlocker/logic/NetworkMonitorService:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   419: invokespecial 217	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   422: astore_1
    //   423: aload_1
    //   424: ldc -37
    //   426: invokevirtual 223	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   429: aload_1
    //   430: invokevirtual 226	java/io/OutputStreamWriter:flush	()V
    //   433: aload_1
    //   434: invokevirtual 229	java/io/OutputStreamWriter:close	()V
    //   437: aload_0
    //   438: ldc -18
    //   440: invokestatic 149	com/smsBlocker/logic/NetworkMonitorService:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   443: ifne +41 -> 484
    //   446: aload_0
    //   447: invokestatic 240	com/smsBlocker/logic/NetworkMonitorService:a	(Landroid/content/Context;)Z
    //   450: ifeq +21 -> 471
    //   453: new 242	java/lang/Thread
    //   456: dup
    //   457: new 244	com/smsBlocker/logic/f
    //   460: dup
    //   461: aload_0
    //   462: invokespecial 245	com/smsBlocker/logic/f:<init>	(Lcom/smsBlocker/logic/NetworkMonitorService;)V
    //   465: invokespecial 248	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   468: invokevirtual 251	java/lang/Thread:start	()V
    //   471: return
    //   472: astore_1
    //   473: iconst_0
    //   474: istore_2
    //   475: goto -408 -> 67
    //   478: astore_1
    //   479: iconst_0
    //   480: istore_2
    //   481: goto -200 -> 281
    //   484: aload_0
    //   485: invokevirtual 254	com/smsBlocker/logic/NetworkMonitorService:stopSelf	()V
    //   488: return
    //   489: astore_1
    //   490: goto -53 -> 437
    //   493: astore_1
    //   494: goto -272 -> 222
    //   497: iconst_0
    //   498: istore_2
    //   499: goto -472 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	502	0	this	NetworkMonitorService
    //   0	502	1	paramIntent	Intent
    //   0	502	2	paramInt	int
    //   45	326	3	l	long
    //   116	284	5	localNotificationManager	android.app.NotificationManager
    //   128	255	6	localObject	Object
    //   160	244	7	localNotification	android.app.Notification
    // Exception table:
    //   from	to	target	type
    //   46	67	472	java/lang/Exception
    //   260	281	478	java/lang/Exception
    //   408	437	489	java/io/IOException
    //   193	222	493	java/io/IOException
  }
}
