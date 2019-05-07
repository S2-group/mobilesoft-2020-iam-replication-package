package com.accells.f;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import com.accells.PingIdApplication;
import com.accells.access.g;
import com.stericson.RootShell.RootShell;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootShell.execution.Shell;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import org.apache.log4j.Logger;

public class d
{
  private static final Logger a = Logger.getLogger(d.class);
  
  public d() {}
  
  public static String a()
  {
    if (Build.VERSION.RELEASE.equalsIgnoreCase("O")) {
      return "8.0";
    }
    return Build.VERSION.RELEASE;
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      a.error("Version name not found in package", paramContext);
    }
    return "?";
  }
  
  public static void a(Runnable paramRunnable)
    throws IOException, TimeoutException, RootDeniedException
  {
    paramRunnable = new Command(0, new String[] { "cd /data/", "ls" })
    {
      private Runnable a;
      private boolean b = false;
      
      public Command a(Runnable paramAnonymousRunnable)
      {
        this.a = paramAnonymousRunnable;
        return this;
      }
      
      public void commandCompleted(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        d.c().info(String.format("Root Detection. Command Completed [id=%d] [exitcode=%d]", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2) }));
        if (this.b)
        {
          d.c().info("Device is Rooted. [reason=ls]");
          this.a.run();
        }
        super.commandCompleted(paramAnonymousInt1, paramAnonymousInt2);
      }
      
      public void commandOutput(int paramAnonymousInt, String paramAnonymousString)
      {
        if ((!this.b) && (!paramAnonymousString.toLowerCase(Locale.getDefault()).contains("permission denied"))) {
          this.b = true;
        }
        super.commandOutput(paramAnonymousInt, paramAnonymousString);
      }
      
      public void commandTerminated(int paramAnonymousInt, String paramAnonymousString)
      {
        d.c().info(String.format("Root Detection. Command Terminated [id=%d] [reason=%s]", new Object[] { Integer.valueOf(paramAnonymousInt), paramAnonymousString }));
        if (this.b)
        {
          d.c().info("Device is Rooted. [reason=ls]");
          this.a.run();
        }
        super.commandTerminated(paramAnonymousInt, paramAnonymousString);
      }
    }.a(paramRunnable);
    RootShell.getShell(true).add(paramRunnable);
  }
  
  public static void a(Locale paramLocale)
  {
    Locale.setDefault(paramLocale);
    Resources localResources = PingIdApplication.c().getResources();
    Configuration localConfiguration = localResources.getConfiguration();
    localConfiguration.locale = paramLocale;
    localResources.updateConfiguration(localConfiguration, localResources.getDisplayMetrics());
  }
  
  public static boolean a(Activity paramActivity)
  {
    if (c(paramActivity))
    {
      PingIdApplication.c().f().c(paramActivity, true);
      android.support.v4.app.d.a(paramActivity, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 103);
      return true;
    }
    return false;
  }
  
  public static void b()
  {
    a(Locale.ENGLISH);
  }
  
  /* Error */
  public static boolean b(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore_2
    //   4: iload_3
    //   5: istore_1
    //   6: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   9: ldc -88
    //   11: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   14: iload_3
    //   15: istore_1
    //   16: invokestatic 176	com/stericson/RootShell/RootShell:isRootAvailable	()Z
    //   19: ifeq +25 -> 44
    //   22: iload_3
    //   23: istore_1
    //   24: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   27: ldc -78
    //   29: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   32: iload_3
    //   33: istore_1
    //   34: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   37: ldc -76
    //   39: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   42: iconst_1
    //   43: istore_2
    //   44: iload_2
    //   45: istore_1
    //   46: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   49: ldc -74
    //   51: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   54: iload_2
    //   55: istore_1
    //   56: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   59: ldc -72
    //   61: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   64: iload_2
    //   65: istore_3
    //   66: iload_2
    //   67: istore_1
    //   68: invokestatic 187	com/stericson/RootShell/RootShell:isAccessGiven	()Z
    //   71: ifeq +25 -> 96
    //   74: iload_2
    //   75: istore_1
    //   76: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   79: ldc -67
    //   81: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   84: iload_2
    //   85: istore_1
    //   86: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   89: ldc -65
    //   91: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   94: iconst_1
    //   95: istore_3
    //   96: iload_3
    //   97: istore_1
    //   98: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   101: ldc -67
    //   103: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   106: iload_3
    //   107: istore_1
    //   108: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   111: ldc -63
    //   113: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   116: iload_3
    //   117: istore_1
    //   118: invokestatic 196	com/stericson/RootShell/RootShell:isBusyboxAvailable	()Z
    //   121: ifeq +119 -> 240
    //   124: iload_3
    //   125: istore_1
    //   126: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   129: ldc -58
    //   131: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   134: iload_3
    //   135: istore_1
    //   136: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   139: ldc -56
    //   141: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   144: iconst_1
    //   145: istore_1
    //   146: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   149: ldc -58
    //   151: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   154: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   157: ldc -54
    //   159: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   162: invokestatic 205	com/accells/f/d:d	()Z
    //   165: ifeq +59 -> 224
    //   168: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   171: ldc -49
    //   173: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   176: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   179: ldc -47
    //   181: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   184: iconst_1
    //   185: istore_1
    //   186: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   189: ldc -45
    //   191: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   194: aload_0
    //   195: invokestatic 214	com/accells/f/d:e	(Landroid/content/Context;)Z
    //   198: pop
    //   199: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   202: ldc -40
    //   204: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   207: iload_1
    //   208: ireturn
    //   209: astore 4
    //   211: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   214: ldc -38
    //   216: aload 4
    //   218: invokevirtual 70	org/apache/log4j/Logger:error	(Ljava/lang/Object;Ljava/lang/Throwable;)V
    //   221: goto -67 -> 154
    //   224: getstatic 20	com/accells/f/d:a	Lorg/apache/log4j/Logger;
    //   227: ldc -49
    //   229: invokevirtual 172	org/apache/log4j/Logger:info	(Ljava/lang/Object;)V
    //   232: goto -46 -> 186
    //   235: astore 4
    //   237: goto -26 -> 211
    //   240: iload_3
    //   241: istore_1
    //   242: goto -96 -> 146
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	245	0	paramContext	Context
    //   5	237	1	bool1	boolean
    //   3	82	2	bool2	boolean
    //   1	240	3	bool3	boolean
    //   209	8	4	localThrowable1	Throwable
    //   235	1	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   6	14	209	java/lang/Throwable
    //   16	22	209	java/lang/Throwable
    //   24	32	209	java/lang/Throwable
    //   34	42	209	java/lang/Throwable
    //   46	54	209	java/lang/Throwable
    //   56	64	209	java/lang/Throwable
    //   68	74	209	java/lang/Throwable
    //   76	84	209	java/lang/Throwable
    //   86	94	209	java/lang/Throwable
    //   98	106	209	java/lang/Throwable
    //   108	116	209	java/lang/Throwable
    //   118	124	209	java/lang/Throwable
    //   126	134	209	java/lang/Throwable
    //   136	144	209	java/lang/Throwable
    //   146	154	235	java/lang/Throwable
  }
  
  public static boolean c(Context paramContext)
  {
    return (Build.VERSION.SDK_INT >= 23) && (!PingIdApplication.c().f().o(paramContext)) && (!PingIdApplication.c().f().q(paramContext));
  }
  
  private static boolean d()
  {
    String str = Build.TAGS;
    return (str == null) || (!str.contains("release-keys"));
  }
  
  public static boolean d(Context paramContext)
  {
    for (boolean bool1 = true;; bool1 = false) {
      try
      {
        if (Build.VERSION.SDK_INT >= 23)
        {
          if (((AppOpsManager)paramContext.getSystemService("appops")).checkOp("android:mock_location", Process.myUid(), "android.support.v4") == 0) {
            bool1 = true;
          }
        }
        else
        {
          while (bool1)
          {
            a.info("device is mocking location");
            break;
            boolean bool2 = Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0");
            if (bool2) {
              for (;;)
              {
                bool1 = false;
              }
            }
          }
          return bool1;
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
  }
  
  private static boolean e(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("com.noshufou.android.su");
    localHashSet.add("com.thirdparty.superuser");
    localHashSet.add("eu.chainfire.supersu");
    localHashSet.add("com.koushikdutta.superuser");
    localHashSet.add("com.zachspong.temprootremovejb");
    localHashSet.add("com.ramdroid.appquarantine");
    localHashSet.add("com.apps9rkb.kingrootsupersu");
    localHashSet.add("com.gorserapp.superuser");
    localHashSet.add("org.masteraxe.superuser");
    localHashSet.add("com.mueskor.superuser.su");
    localHashSet.add("com.bitcubate.android.su.installer");
    localHashSet.add("com.yellowes.su");
    localHashSet.add("appinventor.ai_ewertonfonseca1.ROOT_ALL");
    localHashSet.add("com.alephzain.framaroot");
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext())
    {
      String str = ((ApplicationInfo)paramContext.next()).packageName;
      if (localHashSet.contains(str))
      {
        a.info(String.format("Super User package found. [name=%s]", new Object[] { str }));
        return true;
      }
    }
    return false;
  }
  
  public static enum a
  {
    private a() {}
  }
}
