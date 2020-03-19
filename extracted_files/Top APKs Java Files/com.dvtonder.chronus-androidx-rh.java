package androidx;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class rh
{
  private static boolean bN(Context paramContext)
  {
    List localList = Arrays.asList(new String[] { "com.noshufou.android.su", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.zachspong.temprootremovejb", "com.ramdroid.appquarantine", "de.robv.android.xposed.installer", "com.saurik.substrate", "com.noshufou.android.su.elite", "com.yellowes.su", "com.koushikdutta.rommanager", "com.koushikdutta.rommanager.license", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine", "com.ramdroid.appquarantinepro", "com.devadvance.rootcloak", "com.devadvance.rootcloakplus", "de.robv.android.xposed.installer", "com.saurik.substrate", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot", "com.amphoras.hidemyrootadfree", "com.formyhm.hiderootPremium", "com.formyhm.hideroot" });
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext()) {
        if (localList.contains(((ApplicationInfo)paramContext.next()).packageName))
        {
          if (qu.amA) {
            Log.i("Rooted state:", "Detected by Method 2");
          }
          return true;
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean p(Context paramContext, boolean paramBoolean)
  {
    boolean bool = true;
    if ((paramBoolean) && (rf.bG(paramContext)))
    {
      if (qu.amA) {
        Log.i("Rooted state:", "Detected by cache");
      }
      return true;
    }
    paramBoolean = bool;
    if (!rE())
    {
      paramBoolean = bool;
      if (!bN(paramContext))
      {
        paramBoolean = bool;
        if (!rF())
        {
          paramBoolean = bool;
          if (!rG())
          {
            paramBoolean = bool;
            if (!rH())
            {
              paramBoolean = bool;
              if (!rI()) {
                if (rJ()) {
                  paramBoolean = bool;
                } else {
                  paramBoolean = false;
                }
              }
            }
          }
        }
      }
    }
    rf.l(paramContext, paramBoolean);
    return paramBoolean;
  }
  
  private static boolean rE()
  {
    String str = Build.TAGS;
    boolean bool;
    if ((str != null) && (str.contains("test-keys"))) {
      bool = true;
    } else {
      bool = false;
    }
    if ((bool) && (qu.amA)) {
      Log.i("Rooted state:", "Detected by Method 1");
    }
    return bool;
  }
  
  private static boolean rF()
  {
    for (;;)
    {
      int i;
      try
      {
        StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
        int j = arrayOfStackTraceElement.length;
        i = 0;
        if (i < j)
        {
          String str = arrayOfStackTraceElement[i].toString().toLowerCase();
          if (!str.contains("de.robv.android.xposed")) {
            if (!str.contains("com.saurik.substrate")) {
              break label69;
            }
          }
          if (qu.amA) {
            Log.i("Rooted state:", "Detected by Method 3");
          }
          return true;
        }
        else
        {
          return false;
        }
      }
      catch (Throwable localThrowable)
      {
        return false;
      }
      label69:
      i += 1;
    }
  }
  
  private static boolean rG()
  {
    try
    {
      String[] arrayOfString = new String[9];
      arrayOfString[0] = "/system/app/Superuser.apk";
      arrayOfString[1] = "/sbin/su";
      arrayOfString[2] = "/system/bin/su";
      arrayOfString[3] = "/system/xbin/su";
      arrayOfString[4] = "/data/local/xbin/su";
      arrayOfString[5] = "/data/local/bin/su";
      arrayOfString[6] = "/system/sd/xbin/su";
      arrayOfString[7] = "/system/bin/failsafe/su";
      arrayOfString[8] = "/data/local/su";
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (new File(arrayOfString[i]).exists())
        {
          if (qu.amA) {
            Log.i("Rooted state:", "Detected by Method 4");
          }
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  private static boolean rH()
  {
    try
    {
      String[] arrayOfString = System.getenv("PATH").split(":");
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (new File(arrayOfString[i], "su").exists())
        {
          if (qu.amA) {
            Log.i("Rooted state:", "Detected by Method 5");
          }
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  /* Error */
  private static boolean rI()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: invokestatic 237	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   7: iconst_2
    //   8: anewarray 10	java/lang/String
    //   11: dup
    //   12: iconst_0
    //   13: ldc -17
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: ldc -30
    //   20: aastore
    //   21: invokevirtual 243	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   24: astore_3
    //   25: aload_3
    //   26: astore_1
    //   27: aload_3
    //   28: astore_2
    //   29: new 245	java/io/BufferedReader
    //   32: dup
    //   33: new 247	java/io/InputStreamReader
    //   36: dup
    //   37: aload_3
    //   38: invokevirtual 253	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   41: invokespecial 256	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   44: invokespecial 259	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   47: invokevirtual 262	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   50: ifnull +70 -> 120
    //   53: iconst_1
    //   54: istore_0
    //   55: goto +3 -> 58
    //   58: iload_0
    //   59: ifeq +26 -> 85
    //   62: aload_3
    //   63: astore_1
    //   64: aload_3
    //   65: astore_2
    //   66: getstatic 102	androidx/qu:amA	Z
    //   69: ifeq +16 -> 85
    //   72: aload_3
    //   73: astore_1
    //   74: aload_3
    //   75: astore_2
    //   76: ldc 104
    //   78: ldc_w 264
    //   81: invokestatic 112	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   84: pop
    //   85: aload_3
    //   86: ifnull +7 -> 93
    //   89: aload_3
    //   90: invokevirtual 268	java/lang/Process:destroy	()V
    //   93: iload_0
    //   94: ireturn
    //   95: astore_2
    //   96: aload_1
    //   97: ifnull +7 -> 104
    //   100: aload_1
    //   101: invokevirtual 268	java/lang/Process:destroy	()V
    //   104: aload_2
    //   105: athrow
    //   106: aload_2
    //   107: ifnull +7 -> 114
    //   110: aload_2
    //   111: invokevirtual 268	java/lang/Process:destroy	()V
    //   114: iconst_0
    //   115: ireturn
    //   116: astore_1
    //   117: goto -11 -> 106
    //   120: iconst_0
    //   121: istore_0
    //   122: goto -64 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   54	68	0	bool	boolean
    //   3	98	1	localObject1	Object
    //   116	1	1	localThrowable	Throwable
    //   1	75	2	localObject2	Object
    //   95	16	2	localObject3	Object
    //   24	66	3	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   4	25	95	finally
    //   29	53	95	finally
    //   66	72	95	finally
    //   76	85	95	finally
    //   4	25	116	java/lang/Throwable
    //   29	53	116	java/lang/Throwable
    //   66	72	116	java/lang/Throwable
    //   76	85	116	java/lang/Throwable
  }
  
  /* Error */
  private static boolean rJ()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: invokestatic 237	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   7: iconst_2
    //   8: anewarray 10	java/lang/String
    //   11: dup
    //   12: iconst_0
    //   13: ldc_w 270
    //   16: aastore
    //   17: dup
    //   18: iconst_1
    //   19: ldc_w 272
    //   22: aastore
    //   23: invokevirtual 243	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   26: astore_3
    //   27: aload_3
    //   28: astore_1
    //   29: aload_3
    //   30: astore_2
    //   31: new 245	java/io/BufferedReader
    //   34: dup
    //   35: new 247	java/io/InputStreamReader
    //   38: dup
    //   39: aload_3
    //   40: invokevirtual 253	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   43: invokespecial 256	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   46: invokespecial 259	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   49: invokevirtual 262	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   52: ifnull +70 -> 122
    //   55: iconst_1
    //   56: istore_0
    //   57: goto +3 -> 60
    //   60: iload_0
    //   61: ifeq +26 -> 87
    //   64: aload_3
    //   65: astore_1
    //   66: aload_3
    //   67: astore_2
    //   68: getstatic 102	androidx/qu:amA	Z
    //   71: ifeq +16 -> 87
    //   74: aload_3
    //   75: astore_1
    //   76: aload_3
    //   77: astore_2
    //   78: ldc 104
    //   80: ldc_w 274
    //   83: invokestatic 112	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   86: pop
    //   87: aload_3
    //   88: ifnull +7 -> 95
    //   91: aload_3
    //   92: invokevirtual 268	java/lang/Process:destroy	()V
    //   95: iload_0
    //   96: ireturn
    //   97: astore_2
    //   98: aload_1
    //   99: ifnull +7 -> 106
    //   102: aload_1
    //   103: invokevirtual 268	java/lang/Process:destroy	()V
    //   106: aload_2
    //   107: athrow
    //   108: aload_2
    //   109: ifnull +7 -> 116
    //   112: aload_2
    //   113: invokevirtual 268	java/lang/Process:destroy	()V
    //   116: iconst_0
    //   117: ireturn
    //   118: astore_1
    //   119: goto -11 -> 108
    //   122: iconst_0
    //   123: istore_0
    //   124: goto -64 -> 60
    // Local variable table:
    //   start	length	slot	name	signature
    //   56	68	0	bool	boolean
    //   3	100	1	localObject1	Object
    //   118	1	1	localThrowable	Throwable
    //   1	77	2	localObject2	Object
    //   97	16	2	localObject3	Object
    //   26	66	3	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   4	27	97	finally
    //   31	55	97	finally
    //   68	74	97	finally
    //   78	87	97	finally
    //   4	27	118	java/lang/Throwable
    //   31	55	118	java/lang/Throwable
    //   68	74	118	java/lang/Throwable
    //   78	87	118	java/lang/Throwable
  }
}
