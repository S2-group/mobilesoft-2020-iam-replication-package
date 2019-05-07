package com.airwatch.agent.enterprise;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import com.airwatch.agent.AWService;
import com.airwatch.agent.AirWatchApp;
import com.airwatch.agent.ad;
import com.airwatch.agent.appmanagement.q;
import com.airwatch.agent.ay;
import com.airwatch.agent.f.e;
import com.airwatch.agent.profile.f;
import com.airwatch.agent.profile.g;
import com.airwatch.agent.profile.r;
import com.airwatch.agent.profile.s;
import com.airwatch.agent.utility.ao;
import com.airwatch.bizlib.command.CommandType;
import com.airwatch.bizlib.model.CertificateDefinition;
import com.airwatch.core.AirWatchDevice;
import com.airwatch.core.AirWatchEnum.InstallStatus;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

public class MotorolaMXManager
  extends d
{
  public static boolean a = false;
  private static MotorolaMXManager b = null;
  private static com.airwatch.a.h.a c = null;
  private static String d = "";
  private static AirWatchApp e = null;
  private static final Lock f = new ReentrantLock();
  private static boolean g = false;
  private BroadcastReceiver h = new v(this);
  private ServiceConnection i = new w(this);
  
  public MotorolaMXManager() {}
  
  public static MotorolaMXManager a()
  {
    try
    {
      if (b == null) {
        b = new MotorolaMXManager();
      }
      b.e();
      MotorolaMXManager localMotorolaMXManager = b;
      return localMotorolaMXManager;
    }
    finally {}
  }
  
  private static boolean a(File paramFile1, File paramFile2)
  {
    Object localObject2;
    for (;;)
    {
      try
      {
        if (paramFile1.isDirectory())
        {
          if ((paramFile1.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/files") == 0) || (paramFile1.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/lib") == 0) || (paramFile1.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/cache") == 0) || (paramFile1.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/code_cache") == 0)) {
            break label306;
          }
          if (!paramFile2.exists()) {
            paramFile2.mkdirs();
          }
          localObject1 = paramFile1.list();
          int j = 0;
          if (j >= localObject1.length) {
            break label306;
          }
          localObject2 = new File(paramFile2, localObject1[j]);
          localObject3 = new File(paramFile1, localObject1[j]);
          com.airwatch.util.n.c("Copying... src: " + paramFile1 + " dest: " + paramFile2);
          a((File)localObject3, (File)localObject2);
          j += 1;
          continue;
        }
      }
      catch (Exception paramFile1)
      {
        Object localObject3;
        com.airwatch.util.n.d("Exception occurred while copying directory. " + paramFile1);
        return false;
      }
      catch (Error paramFile1)
      {
        com.airwatch.util.n.d("Error occurred while copying directory. " + paramFile1);
        return false;
      }
      for (;;)
      {
        try
        {
          localObject1 = new FileOutputStream(paramFile2);
        }
        catch (Exception paramFile1)
        {
          paramFile1 = null;
          localObject1 = null;
          break;
        }
        try
        {
          localObject3 = new byte[(int)paramFile1.length()];
          localObject2 = new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile1)));
        }
        catch (Exception paramFile1)
        {
          paramFile1 = null;
          break;
        }
      }
      try
      {
        ((DataInputStream)localObject2).readFully((byte[])localObject3);
        ((FileOutputStream)localObject1).write((byte[])localObject3, 0, (int)paramFile1.length());
        paramFile1 = (File)localObject2;
        if (paramFile1 != null) {
          paramFile1.close();
        }
        if (localObject1 == null) {
          break label306;
        }
        ((FileOutputStream)localObject1).close();
        return true;
      }
      catch (Exception paramFile1)
      {
        paramFile1 = (File)localObject2;
        continue;
      }
      if (localObject1 != null) {
        ((FileOutputStream)localObject1).close();
      }
      paramFile2.delete();
      localObject1 = null;
    }
    label306:
    return true;
  }
  
  private static boolean a(File paramFile, boolean paramBoolean)
  {
    try
    {
      f.lock();
      paramBoolean = b(paramFile, paramBoolean);
      return false;
    }
    catch (Exception paramFile)
    {
      try
      {
        f.unlock();
        return paramBoolean;
      }
      catch (Exception paramFile)
      {
        com.airwatch.util.n.d("Exception occurred while unlocking backup data", paramFile);
      }
      paramFile = paramFile;
      com.airwatch.util.n.d("Exception occurred while locking backup data", paramFile);
      return false;
    }
  }
  
  public static boolean a(String paramString)
  {
    try
    {
      boolean bool = c.e(paramString + ".whitelist", paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    int k = -1;
    int m;
    int j;
    try
    {
      m = AirWatchApp.f().getPackageManager().getPackageInfo(paramString1, 0).versionCode;
      j = k;
      if (paramString2 != null) {
        if (paramString2.length() == 0)
        {
          j = k;
        }
        else
        {
          paramString1 = AirWatchApp.f().getPackageManager();
          paramString2 = new File(paramString2);
          j = k;
          if (paramString2.exists())
          {
            paramString1 = paramString1.getPackageArchiveInfo(paramString2.getAbsolutePath(), 0);
            j = k;
            if (paramString1 != null) {
              j = paramString1.versionCode;
            }
          }
        }
      }
    }
    catch (Exception paramString1)
    {
      com.airwatch.util.n.d("Exception occurred while listing application packages from the device", paramString1);
    }
    do
    {
      return false;
    } while (m <= j);
    return true;
  }
  
  public static boolean a(boolean paramBoolean)
  {
    boolean bool = d.checkAndEnableServiceAsAdministrator("com.airwatch.admin.motorolamx", "com.airwatch.admin.motorolamx.MotorolaMXActivity", paramBoolean);
    paramBoolean = bool;
    if (!bool)
    {
      paramBoolean = bool;
      if (b != null)
      {
        paramBoolean = bool;
        if (c != null)
        {
          if (b.getApiVersion() > 0) {
            break label44;
          }
          paramBoolean = true;
        }
      }
    }
    return paramBoolean;
    label44:
    return false;
  }
  
  public static void b()
  {
    try
    {
      if (b != null)
      {
        c = null;
        d = "";
      }
      b = null;
      q.i();
      return;
    }
    finally {}
  }
  
  private boolean b(File paramFile1, File paramFile2)
  {
    boolean bool3 = true;
    boolean bool2;
    for (boolean bool1 = true;; bool1 = false)
    {
      for (;;)
      {
        DataInputStream localDataInputStream;
        try
        {
          paramFile2.setReadable(true, false);
          paramFile2.setWritable(true, false);
          paramFile2.setExecutable(true, false);
          if (paramFile1.isDirectory())
          {
            bool2 = bool3;
            if (paramFile2.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/files") == 0) {
              break label360;
            }
            bool2 = bool3;
            if (paramFile2.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/lib") == 0) {
              break label360;
            }
            bool2 = bool3;
            if (paramFile2.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/cache") == 0) {
              break label360;
            }
            bool2 = bool3;
            if (paramFile2.getAbsolutePath().compareTo("/data/data/com.airwatch.androidagent/code_cache") == 0) {
              break label360;
            }
            if (!paramFile2.exists()) {
              paramFile2.mkdirs();
            }
            localObject1 = paramFile1.list();
            int k = localObject1.length;
            int j = 0;
            bool2 = bool3;
            if (j >= k) {
              break label360;
            }
            localObject2 = localObject1[j];
            b(new File(paramFile1, (String)localObject2), new File(paramFile2, (String)localObject2));
            j += 1;
            continue;
          }
          if (paramFile2.exists())
          {
            long l1 = paramFile1.length();
            long l2 = paramFile2.length();
            bool2 = bool3;
            if (l1 == l2) {
              break label360;
            }
          }
        }
        catch (Exception paramFile1)
        {
          Object localObject1;
          com.airwatch.util.n.d("Exception while restoring agent data", paramFile1);
          return false;
        }
        catch (Error paramFile1)
        {
          com.airwatch.util.n.d("Error while restoring agent data", paramFile1);
          return false;
        }
        for (;;)
        {
          try
          {
            localObject2 = new FileOutputStream(paramFile2);
          }
          catch (Exception localException3)
          {
            paramFile1 = null;
            localObject2 = null;
            break;
          }
          try
          {
            localObject1 = new byte[(int)paramFile1.length()];
            localDataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile1)));
          }
          catch (Exception localException1)
          {
            paramFile1 = null;
            break;
          }
        }
        try
        {
          localDataInputStream.readFully((byte[])localObject1);
          ((FileOutputStream)localObject2).write((byte[])localObject1, 0, (int)paramFile1.length());
          paramFile1 = localDataInputStream;
          if (paramFile1 != null) {
            paramFile1.close();
          }
          bool2 = bool1;
          if (localObject2 == null) {
            break label360;
          }
          ((FileOutputStream)localObject2).close();
          return bool1;
        }
        catch (Exception localException2)
        {
          paramFile1 = localDataInputStream;
        }
      }
      com.airwatch.util.n.d("Exception while restoring agent data", (Throwable)localObject1);
      if (localObject2 != null) {
        ((FileOutputStream)localObject2).close();
      }
      paramFile2.delete();
      localObject2 = null;
    }
    label360:
    return bool2;
  }
  
  private static boolean b(File paramFile, boolean paramBoolean)
  {
    boolean bool = true;
    try
    {
      com.airwatch.util.n.c("Deleting existing backup directory: " + paramFile);
      paramFile.setReadable(true, false);
      paramFile.setWritable(true, false);
      paramFile.setExecutable(true, false);
      if (paramFile.isDirectory())
      {
        String[] arrayOfString = paramFile.list();
        int k = arrayOfString.length;
        int j = 0;
        while (j < k)
        {
          b(new File(paramFile, arrayOfString[j]), true);
          j += 1;
        }
        if (paramBoolean)
        {
          paramFile.delete();
          return true;
        }
      }
      else
      {
        paramFile.delete();
        return true;
      }
    }
    catch (Exception paramFile)
    {
      com.airwatch.util.n.d("Exception occurred while deleting backup", paramFile);
      return false;
    }
    catch (Error paramFile)
    {
      com.airwatch.util.n.d("Error occurred while deleting backup", paramFile);
      bool = false;
    }
    return bool;
  }
  
  public static boolean c()
  {
    try
    {
      boolean bool = c.b("sdcardformat");
      return bool;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Error while formatting SD Card");
    }
    return false;
  }
  
  private boolean c(File paramFile1, File paramFile2)
  {
    try
    {
      f.lock();
      boolean bool = b(paramFile1, paramFile2);
      return bool;
    }
    catch (Exception paramFile1)
    {
      try
      {
        f.unlock();
        return bool;
      }
      catch (Exception paramFile1)
      {
        com.airwatch.util.n.d("Exception occurred while unlocking backup data", paramFile1);
      }
      paramFile1 = paramFile1;
      com.airwatch.util.n.d("Exception occurred while locking backup data", paramFile1);
      return false;
    }
  }
  
  private static boolean d(String paramString)
  {
    PackageManager localPackageManager = AirWatchApp.f().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      com.airwatch.util.n.a("package not found " + paramString);
      return false;
    }
    catch (Error localError)
    {
      com.airwatch.util.n.a("package error " + paramString, localError);
    }
    return false;
  }
  
  private void e()
  {
    if (c == null) {
      try
      {
        if (!AirWatchApp.a(this.i, "com.airwatch.admin.motorolamx.IMotorolaMXAdminService"))
        {
          com.airwatch.util.n.b("Motorola MX service is not available.");
          return;
        }
        ay localAy = ay.a();
        if (!localAy.p()) {
          localAy.g();
        }
        com.airwatch.util.n.d("Motorola MX service is available.");
        return;
      }
      catch (Exception localException)
      {
        com.airwatch.util.n.b("Motorola MX service bind exception: ", localException);
      }
    }
  }
  
  private boolean e(String paramString)
  {
    Object localObject4 = null;
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (localJSONObject.has("ComponentPkgName"))
      {
        localObject1 = localJSONObject.getString("ComponentPkgName");
        if (localJSONObject.has("ComponentClass"))
        {
          localObject2 = localJSONObject.getString("ComponentClass");
          if (localJSONObject.has("IntentAction"))
          {
            localObject3 = localJSONObject.getString("IntentAction");
            if (localJSONObject.has("IntentKeyExtra"))
            {
              str1 = localJSONObject.getString("IntentKeyExtra");
              if (localJSONObject.has("IntentValueExtra"))
              {
                str2 = localJSONObject.getString("IntentValueExtra");
                paramString = localObject4;
                if (!localJSONObject.has("ReceiverPermission")) {
                  break label277;
                }
                paramString = localJSONObject.getString("ReceiverPermission");
                break label277;
                localObject1 = new ComponentName((String)localObject1, (String)localObject2);
                localObject2 = new Intent();
                ((Intent)localObject2).setComponent((ComponentName)localObject1);
                if (localObject3 != null) {
                  ((Intent)localObject2).setAction((String)localObject3);
                }
                if (str1 != null) {
                  ((Intent)localObject2).putExtra(str1, str2);
                }
                localObject3 = AirWatchApp.f();
                localObject1 = paramString;
                if (paramString != null) {
                  localObject1 = "com.motorolasolutions.fusion.FUSION_COMMAND_RECEIVER_PERMISSION";
                }
                ((AirWatchApp)localObject3).sendOrderedBroadcast((Intent)localObject2, (String)localObject1, new x(this), null, 0, null, null);
                return true;
              }
            }
          }
        }
      }
    }
    catch (Exception paramString)
    {
      com.airwatch.util.n.d("Exception while handling Fusion intent", paramString);
      return false;
    }
    catch (Error paramString)
    {
      Object localObject2;
      label277:
      do
      {
        Object localObject1;
        for (;;)
        {
          com.airwatch.util.n.d("Error while handling Fusion intent", paramString);
          continue;
          String str2 = null;
          continue;
          String str1 = null;
          continue;
          Object localObject3 = null;
          continue;
          localObject2 = null;
          continue;
          localObject1 = null;
        }
        if (localObject1 == null) {
          break;
        }
      } while (localObject2 != null);
    }
    return false;
  }
  
  /* Error */
  public final boolean a(com.airwatch.bizlib.appmanagement.ApplicationInformation paramApplicationInformation)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_1
    //   3: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   6: invokestatic 385	com/airwatch/core/f:a	(Ljava/lang/String;)V
    //   9: ldc_w 387
    //   12: invokestatic 294	com/airwatch/util/n:a	(Ljava/lang/String;)V
    //   15: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   18: ifnonnull +5 -> 23
    //   21: iconst_0
    //   22: ireturn
    //   23: ldc_w 387
    //   26: invokestatic 294	com/airwatch/util/n:a	(Ljava/lang/String;)V
    //   29: new 70	java/io/File
    //   32: dup
    //   33: new 107	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   40: aload_0
    //   41: invokevirtual 390	com/airwatch/agent/enterprise/MotorolaMXManager:getUserDirectory	()Ljava/lang/String;
    //   44: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc_w 392
    //   50: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokespecial 223	java/io/File:<init>	(Ljava/lang/String;)V
    //   59: astore 4
    //   61: aload 4
    //   63: astore_3
    //   64: aload 4
    //   66: iconst_1
    //   67: iconst_0
    //   68: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   71: pop
    //   72: aload 4
    //   74: astore_3
    //   75: aload 4
    //   77: iconst_1
    //   78: iconst_0
    //   79: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   82: pop
    //   83: aload 4
    //   85: astore_3
    //   86: aload 4
    //   88: iconst_1
    //   89: iconst_0
    //   90: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   93: pop
    //   94: aload 4
    //   96: astore_3
    //   97: aload_1
    //   98: invokevirtual 395	com/airwatch/bizlib/appmanagement/ApplicationInformation:k	()Z
    //   101: ifne +35 -> 136
    //   104: aload 4
    //   106: astore_3
    //   107: invokestatic 400	com/airwatch/agent/ad:c	()Lcom/airwatch/agent/ad;
    //   110: invokevirtual 403	com/airwatch/agent/ad:ae	()I
    //   113: tableswitch	default:+548->661, 1:+168->281, 2:+452->565
    //   136: aload 4
    //   138: astore_3
    //   139: new 70	java/io/File
    //   142: dup
    //   143: aload_1
    //   144: invokevirtual 405	com/airwatch/bizlib/appmanagement/ApplicationInformation:b	()Ljava/lang/String;
    //   147: invokespecial 223	java/io/File:<init>	(Ljava/lang/String;)V
    //   150: astore 5
    //   152: aload 4
    //   154: astore_3
    //   155: aload 5
    //   157: invokevirtual 95	java/io/File:exists	()Z
    //   160: ifeq +452 -> 612
    //   163: aload 4
    //   165: astore_3
    //   166: aload 5
    //   168: iconst_1
    //   169: iconst_0
    //   170: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   173: pop
    //   174: aload 4
    //   176: astore_3
    //   177: getstatic 410	android/os/Build$VERSION:SDK_INT	I
    //   180: bipush 19
    //   182: if_icmplt +49 -> 231
    //   185: aload 4
    //   187: astore_3
    //   188: aload_1
    //   189: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   192: invokestatic 412	com/airwatch/agent/enterprise/MotorolaMXManager:d	(Ljava/lang/String;)Z
    //   195: ifeq +36 -> 231
    //   198: aload 4
    //   200: astore_3
    //   201: aload_1
    //   202: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   205: aload_1
    //   206: invokevirtual 405	com/airwatch/bizlib/appmanagement/ApplicationInformation:b	()Ljava/lang/String;
    //   209: invokestatic 414	com/airwatch/agent/enterprise/MotorolaMXManager:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   212: ifeq +19 -> 231
    //   215: aload 4
    //   217: astore_3
    //   218: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   221: aload_1
    //   222: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   225: invokeinterface 417 2 0
    //   230: pop
    //   231: aload 4
    //   233: astore_3
    //   234: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   237: aload_1
    //   238: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   241: aload_1
    //   242: invokevirtual 405	com/airwatch/bizlib/appmanagement/ApplicationInformation:b	()Ljava/lang/String;
    //   245: aload_1
    //   246: invokevirtual 420	com/airwatch/bizlib/appmanagement/ApplicationInformation:j	()Z
    //   249: invokeinterface 422 4 0
    //   254: istore_2
    //   255: aload 4
    //   257: iconst_1
    //   258: iconst_1
    //   259: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   262: pop
    //   263: aload 4
    //   265: iconst_1
    //   266: iconst_1
    //   267: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   270: pop
    //   271: aload 4
    //   273: iconst_1
    //   274: iconst_1
    //   275: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   278: pop
    //   279: iload_2
    //   280: ireturn
    //   281: aload 4
    //   283: astore_3
    //   284: aload_1
    //   285: invokevirtual 425	com/airwatch/bizlib/appmanagement/ApplicationInformation:a	()Lcom/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState;
    //   288: getstatic 430	com/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState:d	Lcom/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState;
    //   291: invokevirtual 434	com/airwatch/bizlib/appmanagement/ApplicationInformation$ApplicationState:equals	(Ljava/lang/Object;)Z
    //   294: ifne +227 -> 521
    //   297: aload 4
    //   299: astore_3
    //   300: ldc_w 436
    //   303: invokestatic 438	com/airwatch/util/n:f	(Ljava/lang/String;)V
    //   306: aload 4
    //   308: astore_3
    //   309: new 351	android/content/Intent
    //   312: dup
    //   313: invokestatic 203	com/airwatch/agent/AirWatchApp:f	()Lcom/airwatch/agent/AirWatchApp;
    //   316: ldc_w 440
    //   319: invokespecial 443	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   322: astore 5
    //   324: aload 4
    //   326: astore_3
    //   327: aload 5
    //   329: ldc_w 445
    //   332: aload_1
    //   333: invokevirtual 405	com/airwatch/bizlib/appmanagement/ApplicationInformation:b	()Ljava/lang/String;
    //   336: invokevirtual 364	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   339: pop
    //   340: aload 4
    //   342: astore_3
    //   343: aload 5
    //   345: ldc_w 447
    //   348: aload_1
    //   349: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   352: invokevirtual 364	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   355: pop
    //   356: aload 4
    //   358: astore_3
    //   359: aload_1
    //   360: invokevirtual 449	com/airwatch/bizlib/appmanagement/ApplicationInformation:d	()Z
    //   363: ifne +67 -> 430
    //   366: aload 4
    //   368: astore_3
    //   369: aload 5
    //   371: ldc_w 451
    //   374: ldc_w 453
    //   377: invokevirtual 364	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   380: pop
    //   381: aload 4
    //   383: astore_3
    //   384: aload 5
    //   386: ldc_w 454
    //   389: invokevirtual 458	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   392: pop
    //   393: aload 4
    //   395: astore_3
    //   396: invokestatic 203	com/airwatch/agent/AirWatchApp:f	()Lcom/airwatch/agent/AirWatchApp;
    //   399: aload 5
    //   401: invokevirtual 462	com/airwatch/agent/AirWatchApp:startActivity	(Landroid/content/Intent;)V
    //   404: aload 4
    //   406: iconst_1
    //   407: iconst_1
    //   408: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   411: pop
    //   412: aload 4
    //   414: iconst_1
    //   415: iconst_1
    //   416: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   419: pop
    //   420: aload 4
    //   422: iconst_1
    //   423: iconst_1
    //   424: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   427: pop
    //   428: iconst_1
    //   429: ireturn
    //   430: aload 4
    //   432: astore_3
    //   433: aload 5
    //   435: ldc_w 451
    //   438: ldc_w 464
    //   441: invokevirtual 364	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   444: pop
    //   445: goto -64 -> 381
    //   448: astore 5
    //   450: aload 4
    //   452: astore_3
    //   453: new 107	java/lang/StringBuilder
    //   456: dup
    //   457: ldc_w 466
    //   460: invokespecial 112	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   463: aload_1
    //   464: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   467: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: ldc_w 468
    //   473: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: aload 5
    //   478: invokevirtual 471	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   481: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   487: invokestatic 169	com/airwatch/util/n:d	(Ljava/lang/String;)V
    //   490: aload 4
    //   492: ifnull -471 -> 21
    //   495: aload 4
    //   497: iconst_1
    //   498: iconst_1
    //   499: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   502: pop
    //   503: aload 4
    //   505: iconst_1
    //   506: iconst_1
    //   507: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   510: pop
    //   511: aload 4
    //   513: iconst_1
    //   514: iconst_1
    //   515: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   518: pop
    //   519: iconst_0
    //   520: ireturn
    //   521: aload 4
    //   523: astore_3
    //   524: invokestatic 203	com/airwatch/agent/AirWatchApp:f	()Lcom/airwatch/agent/AirWatchApp;
    //   527: aload_1
    //   528: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   531: invokestatic 476	com/airwatch/sdk/g:a	(Landroid/content/Context;Ljava/lang/String;)V
    //   534: goto -130 -> 404
    //   537: astore_1
    //   538: aload_3
    //   539: ifnull +24 -> 563
    //   542: aload_3
    //   543: iconst_1
    //   544: iconst_1
    //   545: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   548: pop
    //   549: aload_3
    //   550: iconst_1
    //   551: iconst_1
    //   552: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   555: pop
    //   556: aload_3
    //   557: iconst_1
    //   558: iconst_1
    //   559: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   562: pop
    //   563: aload_1
    //   564: athrow
    //   565: aload 4
    //   567: astore_3
    //   568: new 478	com/airwatch/agent/appmanagement/f
    //   571: dup
    //   572: invokespecial 479	com/airwatch/agent/appmanagement/f:<init>	()V
    //   575: aload_1
    //   576: invokevirtual 481	com/airwatch/bizlib/appmanagement/ApplicationInformation:c	()Ljava/lang/String;
    //   579: aload_1
    //   580: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   583: invokevirtual 483	com/airwatch/agent/appmanagement/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   586: aload 4
    //   588: iconst_1
    //   589: iconst_1
    //   590: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   593: pop
    //   594: aload 4
    //   596: iconst_1
    //   597: iconst_1
    //   598: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   601: pop
    //   602: aload 4
    //   604: iconst_1
    //   605: iconst_1
    //   606: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   609: pop
    //   610: iconst_1
    //   611: ireturn
    //   612: aload 4
    //   614: astore_3
    //   615: new 107	java/lang/StringBuilder
    //   618: dup
    //   619: ldc_w 485
    //   622: invokespecial 112	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   625: aload_1
    //   626: invokevirtual 382	com/airwatch/bizlib/appmanagement/ApplicationInformation:e	()Ljava/lang/String;
    //   629: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   632: ldc_w 487
    //   635: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   641: invokestatic 294	com/airwatch/util/n:a	(Ljava/lang/String;)V
    //   644: goto -389 -> 255
    //   647: astore_1
    //   648: aconst_null
    //   649: astore_3
    //   650: goto -112 -> 538
    //   653: astore 5
    //   655: aconst_null
    //   656: astore 4
    //   658: goto -208 -> 450
    //   661: goto -525 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	664	0	this	MotorolaMXManager
    //   0	664	1	paramApplicationInformation	com.airwatch.bizlib.appmanagement.ApplicationInformation
    //   1	279	2	bool	boolean
    //   63	587	3	localFile1	File
    //   59	598	4	localFile2	File
    //   150	284	5	localObject	Object
    //   448	29	5	localException1	Exception
    //   653	1	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   64	72	448	java/lang/Exception
    //   75	83	448	java/lang/Exception
    //   86	94	448	java/lang/Exception
    //   97	104	448	java/lang/Exception
    //   107	136	448	java/lang/Exception
    //   139	152	448	java/lang/Exception
    //   155	163	448	java/lang/Exception
    //   166	174	448	java/lang/Exception
    //   177	185	448	java/lang/Exception
    //   188	198	448	java/lang/Exception
    //   201	215	448	java/lang/Exception
    //   218	231	448	java/lang/Exception
    //   234	255	448	java/lang/Exception
    //   284	297	448	java/lang/Exception
    //   300	306	448	java/lang/Exception
    //   309	324	448	java/lang/Exception
    //   327	340	448	java/lang/Exception
    //   343	356	448	java/lang/Exception
    //   359	366	448	java/lang/Exception
    //   369	381	448	java/lang/Exception
    //   384	393	448	java/lang/Exception
    //   396	404	448	java/lang/Exception
    //   433	445	448	java/lang/Exception
    //   524	534	448	java/lang/Exception
    //   568	586	448	java/lang/Exception
    //   615	644	448	java/lang/Exception
    //   64	72	537	finally
    //   75	83	537	finally
    //   86	94	537	finally
    //   97	104	537	finally
    //   107	136	537	finally
    //   139	152	537	finally
    //   155	163	537	finally
    //   166	174	537	finally
    //   177	185	537	finally
    //   188	198	537	finally
    //   201	215	537	finally
    //   218	231	537	finally
    //   234	255	537	finally
    //   284	297	537	finally
    //   300	306	537	finally
    //   309	324	537	finally
    //   327	340	537	finally
    //   343	356	537	finally
    //   359	366	537	finally
    //   369	381	537	finally
    //   384	393	537	finally
    //   396	404	537	finally
    //   433	445	537	finally
    //   453	490	537	finally
    //   524	534	537	finally
    //   568	586	537	finally
    //   615	644	537	finally
    //   29	61	647	finally
    //   29	61	653	java/lang/Exception
  }
  
  public boolean activateAgentAsAdministrator()
  {
    try
    {
      if (!com.airwatch.agent.f.c.a().g())
      {
        if (c == null) {
          e();
        }
        c.n(AirWatchApp.f().getPackageName(), "com.airwatch.agent.DeviceAdministratorReceiver");
      }
      return true;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Exception occurred while reaching MX Service", localException);
    }
    return false;
  }
  
  public boolean addDefaultLauncher(String paramString)
  {
    if (!isSupportedDevice()) {
      return false;
    }
    try
    {
      c.d("DefaultHome" + com.airwatch.agent.a.a.b(), paramString);
      return true;
    }
    catch (Exception paramString)
    {
      com.airwatch.util.n.d("Exception while setting default launcher " + paramString.toString());
    }
    return false;
  }
  
  public boolean addProtectedProcess(String paramString)
  {
    try
    {
      boolean bool = c.s(paramString);
      return bool;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Exception while addProtectedProcess " + paramString);
      return false;
    }
    catch (Error localError)
    {
      for (;;)
      {
        com.airwatch.util.n.d("Error while addProtectedProcess " + paramString);
      }
    }
  }
  
  public boolean agentUpdate()
  {
    for (;;)
    {
      try
      {
        backupAirwatchData(true);
      }
      catch (Exception localException)
      {
        com.airwatch.util.n.d("An exception was encountered during agent update command. " + localException.getMessage());
        continue;
      }
      try
      {
        com.airwatch.agent.provisioning.t.a(c.D());
        return false;
      }
      catch (RemoteException localRemoteException)
      {
        c.x();
      }
    }
  }
  
  /* Error */
  public boolean applyDateTimeSettings(com.airwatch.agent.profile.group.t paramT)
  {
    // Byte code:
    //   0: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   3: ifnonnull +5 -> 8
    //   6: iconst_0
    //   7: ireturn
    //   8: aload_1
    //   9: getfield 558	com/airwatch/agent/profile/group/t:b	Z
    //   12: ifeq +31 -> 43
    //   15: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   18: aload_1
    //   19: getfield 560	com/airwatch/agent/profile/group/t:f	Ljava/lang/String;
    //   22: aload_1
    //   23: getfield 558	com/airwatch/agent/profile/group/t:b	Z
    //   26: aload_1
    //   27: getfield 562	com/airwatch/agent/profile/group/t:a	Ljava/lang/String;
    //   30: aload_1
    //   31: getfield 563	com/airwatch/agent/profile/group/t:d	Ljava/lang/String;
    //   34: invokeinterface 566 5 0
    //   39: istore_2
    //   40: goto +201 -> 241
    //   43: invokestatic 572	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   46: astore 5
    //   48: aload_1
    //   49: getfield 574	com/airwatch/agent/profile/group/t:e	Ljava/lang/String;
    //   52: ifnull +15 -> 67
    //   55: aload 5
    //   57: aload_1
    //   58: getfield 574	com/airwatch/agent/profile/group/t:e	Ljava/lang/String;
    //   61: invokestatic 580	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   64: invokevirtual 584	java/util/Calendar:setTimeZone	(Ljava/util/TimeZone;)V
    //   67: aload_1
    //   68: getfield 586	com/airwatch/agent/profile/group/t:g	Ljava/lang/String;
    //   71: ldc_w 588
    //   74: invokevirtual 591	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   77: istore_2
    //   78: iload_2
    //   79: ifeq +91 -> 170
    //   82: new 593	java/net/URL
    //   85: dup
    //   86: aload_1
    //   87: getfield 595	com/airwatch/agent/profile/group/t:h	Ljava/lang/String;
    //   90: invokespecial 596	java/net/URL:<init>	(Ljava/lang/String;)V
    //   93: invokevirtual 600	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   96: checkcast 602	java/net/HttpURLConnection
    //   99: astore 6
    //   101: aload 6
    //   103: invokevirtual 605	java/net/HttpURLConnection:getResponseCode	()I
    //   106: sipush 200
    //   109: if_icmpne +125 -> 234
    //   112: aload 6
    //   114: ldc_w 607
    //   117: lconst_0
    //   118: invokevirtual 611	java/net/HttpURLConnection:getHeaderFieldDate	(Ljava/lang/String;J)J
    //   121: lstore_3
    //   122: lload_3
    //   123: ldc2_w 612
    //   126: lcmp
    //   127: ifeq +95 -> 222
    //   130: aload 5
    //   132: lload_3
    //   133: invokevirtual 616	java/util/Calendar:setTimeInMillis	(J)V
    //   136: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   139: aload_1
    //   140: getfield 560	com/airwatch/agent/profile/group/t:f	Ljava/lang/String;
    //   143: aload_1
    //   144: getfield 558	com/airwatch/agent/profile/group/t:b	Z
    //   147: aload_1
    //   148: getfield 562	com/airwatch/agent/profile/group/t:a	Ljava/lang/String;
    //   151: iconst_1
    //   152: aload_1
    //   153: getfield 563	com/airwatch/agent/profile/group/t:d	Ljava/lang/String;
    //   156: aload_1
    //   157: getfield 574	com/airwatch/agent/profile/group/t:e	Ljava/lang/String;
    //   160: lload_3
    //   161: invokeinterface 619 9 0
    //   166: istore_2
    //   167: goto +74 -> 241
    //   170: aload_1
    //   171: getfield 586	com/airwatch/agent/profile/group/t:g	Ljava/lang/String;
    //   174: ldc_w 621
    //   177: invokevirtual 591	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   180: ifeq +14 -> 194
    //   183: aload_1
    //   184: getfield 595	com/airwatch/agent/profile/group/t:h	Ljava/lang/String;
    //   187: invokestatic 625	com/airwatch/agent/enterprise/MotorolaMXManager:getSntpTime	(Ljava/lang/String;)J
    //   190: lstore_3
    //   191: goto -69 -> 122
    //   194: aload_1
    //   195: getfield 586	com/airwatch/agent/profile/group/t:g	Ljava/lang/String;
    //   198: ldc_w 627
    //   201: invokevirtual 591	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   204: ifeq +23 -> 227
    //   207: aload_1
    //   208: getfield 630	com/airwatch/agent/profile/group/t:c	J
    //   211: lstore_3
    //   212: goto -90 -> 122
    //   215: astore_1
    //   216: ldc_w 632
    //   219: invokestatic 169	com/airwatch/util/n:d	(Ljava/lang/String;)V
    //   222: iconst_0
    //   223: istore_2
    //   224: goto +17 -> 241
    //   227: ldc2_w 612
    //   230: lstore_3
    //   231: goto -109 -> 122
    //   234: ldc2_w 612
    //   237: lstore_3
    //   238: goto -116 -> 122
    //   241: iload_2
    //   242: ireturn
    //   243: astore 6
    //   245: ldc2_w 612
    //   248: lstore_3
    //   249: goto -127 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	this	MotorolaMXManager
    //   0	252	1	paramT	com.airwatch.agent.profile.group.t
    //   39	203	2	bool	boolean
    //   121	128	3	l	long
    //   46	85	5	localCalendar	java.util.Calendar
    //   99	14	6	localHttpURLConnection	java.net.HttpURLConnection
    //   243	1	6	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   8	40	215	java/lang/Exception
    //   43	67	215	java/lang/Exception
    //   67	78	215	java/lang/Exception
    //   130	167	215	java/lang/Exception
    //   170	191	215	java/lang/Exception
    //   194	212	215	java/lang/Exception
    //   82	122	243	java/lang/Exception
  }
  
  /* Error */
  public boolean backupAirwatchData(boolean paramBoolean)
  {
    // Byte code:
    //   0: getstatic 42	com/airwatch/agent/enterprise/MotorolaMXManager:f	Ljava/util/concurrent/locks/Lock;
    //   3: invokeinterface 177 1 0
    //   8: iconst_0
    //   9: istore_2
    //   10: invokestatic 636	com/airwatch/bizlib/c/a:a	()Z
    //   13: ifne +19 -> 32
    //   16: invokestatic 640	com/airwatch/agent/c:b	()Z
    //   19: ifne +13 -> 32
    //   22: getstatic 643	com/airwatch/agent/provisioning/q:b	Z
    //   25: ifne +7 -> 32
    //   28: iload_1
    //   29: ifeq +133 -> 162
    //   32: getstatic 44	com/airwatch/agent/enterprise/MotorolaMXManager:g	Z
    //   35: iconst_1
    //   36: if_icmpne +126 -> 162
    //   39: invokestatic 645	com/airwatch/bizlib/c/a:b	()V
    //   42: invokestatic 647	com/airwatch/agent/c:c	()V
    //   45: iconst_0
    //   46: putstatic 643	com/airwatch/agent/provisioning/q:b	Z
    //   49: aload_0
    //   50: invokevirtual 390	com/airwatch/agent/enterprise/MotorolaMXManager:getUserDirectory	()Ljava/lang/String;
    //   53: astore_3
    //   54: new 70	java/io/File
    //   57: dup
    //   58: new 107	java/lang/StringBuilder
    //   61: dup
    //   62: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   65: aload_3
    //   66: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 649
    //   72: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokestatic 203	com/airwatch/agent/AirWatchApp:f	()Lcom/airwatch/agent/AirWatchApp;
    //   78: invokevirtual 500	com/airwatch/agent/AirWatchApp:getPackageName	()Ljava/lang/String;
    //   81: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokespecial 223	java/io/File:<init>	(Ljava/lang/String;)V
    //   90: astore_3
    //   91: aload_3
    //   92: iconst_1
    //   93: invokestatic 179	com/airwatch/agent/enterprise/MotorolaMXManager:b	(Ljava/io/File;Z)Z
    //   96: ifeq +24 -> 120
    //   99: new 70	java/io/File
    //   102: dup
    //   103: invokestatic 203	com/airwatch/agent/AirWatchApp:f	()Lcom/airwatch/agent/AirWatchApp;
    //   106: invokevirtual 653	com/airwatch/agent/AirWatchApp:getFilesDir	()Ljava/io/File;
    //   109: invokevirtual 656	java/io/File:getParent	()Ljava/lang/String;
    //   112: invokespecial 223	java/io/File:<init>	(Ljava/lang/String;)V
    //   115: aload_3
    //   116: invokestatic 130	com/airwatch/agent/enterprise/MotorolaMXManager:a	(Ljava/io/File;Ljava/io/File;)Z
    //   119: pop
    //   120: iconst_1
    //   121: istore_2
    //   122: iconst_0
    //   123: istore_1
    //   124: goto -114 -> 10
    //   127: astore_3
    //   128: ldc -72
    //   130: aload_3
    //   131: invokestatic 187	com/airwatch/util/n:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   134: iconst_0
    //   135: ireturn
    //   136: astore_3
    //   137: ldc_w 658
    //   140: aload_3
    //   141: invokestatic 187	com/airwatch/util/n:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   144: iconst_0
    //   145: istore_1
    //   146: goto -136 -> 10
    //   149: astore_3
    //   150: ldc_w 660
    //   153: aload_3
    //   154: invokestatic 187	com/airwatch/util/n:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   157: iconst_0
    //   158: istore_1
    //   159: goto -149 -> 10
    //   162: getstatic 42	com/airwatch/agent/enterprise/MotorolaMXManager:f	Ljava/util/concurrent/locks/Lock;
    //   165: invokeinterface 182 1 0
    //   170: iload_2
    //   171: ireturn
    //   172: astore_3
    //   173: ldc -67
    //   175: aload_3
    //   176: invokestatic 187	com/airwatch/util/n:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   179: iconst_0
    //   180: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	this	MotorolaMXManager
    //   0	181	1	paramBoolean	boolean
    //   9	162	2	bool	boolean
    //   53	63	3	localObject	Object
    //   127	4	3	localException1	Exception
    //   136	5	3	localException2	Exception
    //   149	5	3	localError	Error
    //   172	4	3	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   0	8	127	java/lang/Exception
    //   49	120	136	java/lang/Exception
    //   49	120	149	java/lang/Error
    //   162	170	172	java/lang/Exception
  }
  
  public boolean backupExists()
  {
    return new File("/enterprise/usr/airwatch/backup/" + AirWatchApp.f().getPackageName()).exists();
  }
  
  public boolean canInstallVPNCert()
  {
    return true;
  }
  
  public boolean clearAppData(String paramString)
  {
    try
    {
      boolean bool = c.c(paramString + ".clear" + com.airwatch.agent.a.a.b(), paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public void deleteAirwatchBackup()
  {
    try
    {
      a(new File(getUserDirectory() + "airwatch/backup/" + AirWatchApp.f().getPackageName()), true);
      a(new File(getUserDirectory() + "airwatch/apps/"), false);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void disableBackup()
  {
    g = false;
  }
  
  public boolean disableServiceDeviceAdministration()
  {
    try
    {
      if (c != null) {
        c.f();
      }
      AirWatchApp.f().unbindService(this.i);
      d = "";
      this.i = null;
      System.gc();
      System.runFinalization();
      System.gc();
      b();
      return true;
    }
    catch (RemoteException localRemoteException)
    {
      com.airwatch.util.n.d("Error while disabling device Motorola MX admin " + localRemoteException.toString(), localRemoteException);
      return false;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        com.airwatch.util.n.d("An exception occurred while disabling device administration on the Motorola MX service.");
      }
    }
    catch (Error localError)
    {
      for (;;)
      {
        com.airwatch.util.n.d("An error occurred while disabling device administration on the Motorola MX  service.");
      }
    }
  }
  
  public void enableBackup()
  {
    g = true;
  }
  
  public boolean enableDeviceAdmin()
  {
    return activateAgentAsAdministrator();
  }
  
  public boolean enableKioskMode(String paramString)
  {
    if (c != null) {
      try
      {
        boolean bool = setDefaultHomeScreen(paramString);
        return bool;
      }
      catch (Exception localException)
      {
        com.airwatch.util.n.d("Error while enabling kiosk mode" + localException.toString());
      }
    }
    return super.enableKioskMode(paramString);
  }
  
  public boolean enterpriseReset()
  {
    if (AWService.b() < 50)
    {
      com.airwatch.agent.provisioning.t.b(100);
      com.airwatch.util.n.d("Insufficient battery level for enterprise reset");
      return false;
    }
    try
    {
      backupAirwatchData(true);
      c.w();
      return false;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("An exception was encountered during enterprise reset command. " + localException.getMessage());
    }
    return false;
  }
  
  public boolean fireIntent(Intent paramIntent, boolean paramBoolean)
  {
    boolean bool = false;
    if (c != null) {}
    try
    {
      bool = c.a(paramIntent, paramBoolean);
      return bool;
    }
    catch (Exception paramIntent)
    {
      com.airwatch.util.n.d("fireIntent Exception ");
    }
    return false;
  }
  
  public int getApiVersion()
  {
    if (c == null) {
      return 0;
    }
    try
    {
      int j = getNumericVersion(c.a());
      return j;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Motorola MX Manager : Error while fetch EDM version.");
    }
    return 0;
  }
  
  public String getCertificateAlias(CertificateDefinition paramCertificateDefinition)
  {
    String str2 = paramCertificateDefinition.getName();
    String str1 = str2;
    if (paramCertificateDefinition.getPassword() != null)
    {
      str1 = str2;
      if (paramCertificateDefinition.getPassword().trim().length() > 0)
      {
        str1 = d.extractPKCS12AliasName(paramCertificateDefinition.getCertificateData(), paramCertificateDefinition.getPassword());
        com.airwatch.util.n.a("Motorola MX certificate removing alias " + str1);
      }
    }
    return str1;
  }
  
  public String getDeviceModel()
  {
    if (Build.MODEL.toLowerCase().contains("mc40")) {
      return Build.MODEL;
    }
    return Build.DEVICE;
  }
  
  public String getDeviceUID()
  {
    try
    {
      Object localObject = new RandomAccessFile("/sys/hardware_id/uuid", "r");
      byte[] arrayOfByte = new byte[32];
      ((RandomAccessFile)localObject).seek(0L);
      ((RandomAccessFile)localObject).read(arrayOfByte, 0, 32);
      ((RandomAccessFile)localObject).close();
      localObject = new String(arrayOfByte);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public String getEnterpriseManagerString()
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    try
    {
      int j = getIsCompliant();
      if (j == 2) {
        return "";
      }
      localObject1 = localObject2;
      String str = c.e();
      localObject1 = "Zebra Mobility Extensions Version";
      localObject2 = localObject1;
      if (j == 0) {
        localObject2 = "Zebra Mobility Extensions Version" + "... pending";
      }
      localObject1 = localObject2;
      localObject2 = (String)localObject2 + " " + str;
      return localObject2;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Motorola MX Manager : Error while fetching version info " + localException.toString(), localException);
    }
    return localObject1;
  }
  
  public int getIsCompliant()
  {
    try
    {
      int j = c.g();
      return j;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return 0;
  }
  
  public boolean getServiceComplianceStatus()
  {
    try
    {
      int j = c.g();
      return j == 1;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Motorola MX Manager : Error while fetching compliance status " + localException.toString(), localException);
    }
    return false;
  }
  
  public String getUserDirectory()
  {
    return "/enterprise/usr/";
  }
  
  public boolean hasWifiEAPInstall()
  {
    return true;
  }
  
  public AirWatchEnum.InstallStatus installCert(CertificateDefinition paramCertificateDefinition)
  {
    if (c == null) {
      return AirWatchEnum.InstallStatus.a;
    }
    try
    {
      String str2 = paramCertificateDefinition.getName();
      String str1;
      if (str2 != null)
      {
        str1 = str2;
        if (str2.trim().length() != 0) {}
      }
      else
      {
        str1 = "EnterpriseCert" + com.airwatch.agent.a.a.b();
      }
      if ((paramCertificateDefinition.getPassword() != null) && (!paramCertificateDefinition.getPassword().trim().equals("")))
      {
        str1 = d.extractPKCS12AliasName(paramCertificateDefinition.getCertificateData(), paramCertificateDefinition.getPassword());
        com.airwatch.util.n.a("Motorola MX certificate alias " + str1);
      }
      for (boolean bool = c.c(str1, paramCertificateDefinition.getCertificateData(), paramCertificateDefinition.getPassword()); bool; bool = c.a("cacrt" + com.airwatch.agent.a.a.b(), paramCertificateDefinition.getCertificateData(), str1)) {
        return AirWatchEnum.InstallStatus.c;
      }
      paramCertificateDefinition = AirWatchEnum.InstallStatus.a;
      return paramCertificateDefinition;
    }
    catch (Exception paramCertificateDefinition)
    {
      com.airwatch.util.n.d("Motorola MX Manager : Unable to install Certificate.");
    }
    return AirWatchEnum.InstallStatus.a;
  }
  
  public boolean installEAPNetwork(CertificateDefinition paramCertificateDefinition1, CertificateDefinition paramCertificateDefinition2, com.airwatch.agent.profile.t paramT)
  {
    WifiManager localWifiManager;
    WifiConfiguration localWifiConfiguration;
    try
    {
      localWifiManager = (WifiManager)AirWatchApp.f().getSystemService("wifi");
      if (!localWifiManager.isWifiEnabled())
      {
        if (!localWifiManager.setWifiEnabled(true)) {
          break label109;
        }
        Log.v("wifi", "WiFi radio enabled.");
      }
      for (;;)
      {
        localWifiConfiguration = new WifiConfiguration();
        localWifiConfiguration.SSID = paramT.a();
        localWifiConfiguration.hiddenSSID = paramT.d();
        localWifiConfiguration.priority = 1;
        if ((paramCertificateDefinition1 == null) || (paramCertificateDefinition1.getCertificateData().length <= 0)) {
          break label158;
        }
        if (installCert(paramCertificateDefinition1) != AirWatchEnum.InstallStatus.a) {
          break;
        }
        Log.e("MOTO Wifi CA Cert ", "Failed to install CA Cert.");
        return false;
        label109:
        Log.v("wifi", "Could not turn WiFi radio on.");
      }
      Log.i("MOTO Wifi CA Cert ", "Successfully installed CA Cert.");
    }
    catch (Exception paramCertificateDefinition1)
    {
      com.airwatch.util.n.d("Exception while installing EAP network " + paramT.a());
      return false;
    }
    label158:
    if ((paramCertificateDefinition2 != null) && (paramCertificateDefinition2.getCertificateData().length > 0))
    {
      if (installCert(paramCertificateDefinition2) == AirWatchEnum.InstallStatus.a)
      {
        Log.e("Wifi Client Cert", "Failed to install Client Cert.");
        return false;
      }
      Log.i("MOTO Wifi CA Cert ", "Successfully installed client Cert.");
    }
    String str = d.extractPKCS12AliasName(paramCertificateDefinition1.getCertificateData(), paramCertificateDefinition1.getPassword());
    com.airwatch.util.n.a("Motorola MX wifi certificate installed alias " + str);
    paramT.a(paramT.a(), paramT.b(), paramT.c(), paramT.d(), paramT.g(), paramT.e(), paramT.h(), paramT.k(), paramT.l(), "USRCERT_" + str, "USRPKEY_" + str, "CACERT_" + paramCertificateDefinition2.getName(), paramT.n(), paramT.o(), paramT.p(), paramT.r(), paramT.s(), paramT.t(), paramT.u(), paramT.v(), paramT.w(), paramT.x(), paramT.y(), paramT.z(), paramT.A(), paramT.B(), paramT.C(), paramT.q(), paramT.D(), paramT.E(), paramT.F());
    if (str != null) {
      paramT.a("USRPKEY_" + str);
    }
    for (;;)
    {
      if ((paramCertificateDefinition2.getName().equals(null)) || (paramCertificateDefinition2.getName().equals("null")) || (paramCertificateDefinition2.getName().equals(""))) {
        paramT.P();
      }
      if ((paramCertificateDefinition1.getName().equals(null)) || (paramCertificateDefinition1.getName().equals("null")) || (paramCertificateDefinition1.getName().equals(""))) {
        paramT.O();
      }
      if (paramT.h().equals("TLS")) {
        paramT.b("None");
      }
      ao.b(localWifiConfiguration, paramT);
      com.airwatch.util.n.a("wifiConfig before adding the network:\n" + localWifiConfiguration.toString());
      ao.a(localWifiConfiguration, paramT);
      ao.a(localWifiManager.addNetwork(localWifiConfiguration), paramT.q());
      return true;
      paramT.a("");
    }
  }
  
  public boolean isAutoEnrollmentSupported()
  {
    return true;
  }
  
  public boolean isCredStoreOpen()
  {
    try
    {
      if (getIsCompliant() == 2)
      {
        boolean bool = super.isCredStoreOpen();
        return bool;
      }
      return true;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public boolean isCredStoreSupported()
  {
    return false;
  }
  
  public boolean isDeviceWipeSupported()
  {
    return true;
  }
  
  public boolean isEnterpriseResetSupported()
  {
    return true;
  }
  
  public boolean isExternalStorageEncrypted()
  {
    if (Build.VERSION.SDK_INT > 15) {
      try
      {
        boolean bool = c.m();
        return bool;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }
    return false;
  }
  
  public boolean isExternalStoragePresent()
  {
    try
    {
      boolean bool = new File("/mnt/sdcard").canWrite();
      if (bool) {
        return true;
      }
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Exception while checking SD card present", localException);
    }
    return false;
  }
  
  public boolean isSupportedDevice()
  {
    if (c == null) {}
    for (;;)
    {
      return false;
      try
      {
        boolean bool = c.d();
        if (bool) {
          return true;
        }
      }
      catch (Exception localException)
      {
        com.airwatch.util.n.d("Motorola MX Manager : Error while fetching support info.");
      }
    }
    return false;
  }
  
  public boolean isXMLConfigSupported()
  {
    return true;
  }
  
  public boolean osUpgrade(String paramString, ArrayList paramArrayList)
  {
    if (AWService.b() < 50)
    {
      com.airwatch.agent.provisioning.t.b(100);
      com.airwatch.util.n.d("Insufficient battery level for osupdate");
      return false;
    }
    backupAirwatchData(true);
    label149:
    for (;;)
    {
      Object localObject;
      try
      {
        localObject = "/data/tmp/" + paramString;
        Iterator localIterator = paramArrayList.iterator();
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (!str.endsWith(paramString)) {
            break label149;
          }
          localObject = str;
          break label149;
        }
        if (paramArrayList.size() > 1)
        {
          paramString = (String[])paramArrayList.toArray(new String[0]);
          c.a((String)localObject, paramString);
          com.airwatch.util.n.d("initiateOSUpgrade failed");
          return false;
        }
      }
      catch (Exception paramString)
      {
        com.airwatch.util.n.d("Exception occurred in initiateOSUpgrade()");
        return false;
      }
      c.v((String)localObject);
    }
  }
  
  public boolean processFusionSettings(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, String paramString2, boolean paramBoolean7, boolean paramBoolean8, String paramString3)
  {
    AirWatchApp localAirWatchApp = AirWatchApp.f();
    Object localObject = localAirWatchApp.getPackageManager().getInstalledApplications(128).iterator();
    int j;
    String str2;
    String str1;
    for (;;)
    {
      if (((Iterator)localObject).hasNext()) {
        if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.motorolasolutions.wificonfig"))
        {
          j = 1;
          if (j == 0)
          {
            str2 = "com.symbol.wificonfig";
            str1 = "com.symbol.wificonfig.WiFiConfig";
            localObject = "action.symbol.config.set";
          }
        }
      }
    }
    for (;;)
    {
      StringBuilder localStringBuilder;
      String str3;
      try
      {
        File localFile = new File("/enterprise/usr/fusionSettings.xml");
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile.getAbsoluteFile(), "rw");
        localRandomAccessFile.setLength(0L);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("<wap-provisioningdoc><characteristic type=\"Wi-Fi\">");
        if (!paramBoolean1) {
          break label697;
        }
        if (paramBoolean2)
        {
          localStringBuilder.append("<characteristic type=\"Regulatory\"><parm name=\"Country\" value=\"\" /></characteristic>");
          break label697;
          localStringBuilder.append("<characteristic type=\"Radio\"><parm name=\"BandSelection\" value=\"");
          if ((paramBoolean5) && (paramBoolean6) && (paramBoolean7) && (paramBoolean8))
          {
            localStringBuilder.append("Auto");
            localStringBuilder.append("\" />");
            if (((paramString2 != null) && (!paramString2.isEmpty())) || ((paramString3 != null) && (!paramString3.isEmpty())))
            {
              localStringBuilder.append("<characteristic type=\"ChannelSelection\">");
              if ((paramString2 != null) && (!paramString2.isEmpty()))
              {
                localStringBuilder.append("<parm name=\"2.4GHzChannels\" value=\"");
                if (!paramString2.equalsIgnoreCase("all")) {
                  break label649;
                }
                localStringBuilder.append("1-11");
                localStringBuilder.append("\" />");
              }
              if ((paramString3 != null) && (!paramString3.isEmpty()))
              {
                localStringBuilder.append("<parm name=\"5.0GHzChannels\" value=\"");
                if (!paramString3.equalsIgnoreCase("all")) {
                  break label660;
                }
                localStringBuilder.append("36-165");
                localStringBuilder.append("\" />");
              }
              localStringBuilder.append("</characteristic>");
            }
            localStringBuilder.append("</characteristic>");
            localStringBuilder.append("</characteristic></wap-provisioningdoc>");
            localRandomAccessFile.writeBytes(localStringBuilder.toString());
            localRandomAccessFile.close();
            localFile.setReadable(true, false);
            localFile.setWritable(true, false);
            paramString1 = new Intent();
            paramString1.setClassName(str2, str1);
            paramString1.setAction((String)localObject);
            paramString1.setType("text/config.WiFiConfig");
            paramString1.putExtra("ConfigXML", "/enterprise/usr/fusionSettings.xml");
            paramString1.putExtra("ResultXML", "/enterprise/usr/fusionSettingsResult.xml");
            paramString2 = new File("/enterprise/usr/fusionSettingsResult.xml");
            if (paramString2.exists()) {
              paramString2.delete();
            }
            paramString1 = PendingIntent.getActivity(localAirWatchApp, 0, paramString1, 0);
            ((AlarmManager)localAirWatchApp.getSystemService("alarm")).set(1, System.currentTimeMillis(), paramString1);
            j = 0;
            if ((j >= 100L) || (paramString2.exists())) {
              break label671;
            }
            Thread.sleep(100L);
            j += 1;
            continue;
          }
        }
        else
        {
          if ((!paramBoolean3) || (paramString1 == null)) {
            break label725;
          }
          str3 = paramString1;
          if (paramString1.isEmpty()) {
            break label725;
          }
          localStringBuilder.append("<characteristic type=\"Regulatory\"><parm name=\"Country\" value=\"" + str3 + "\" /></characteristic>");
        }
      }
      catch (Exception paramString1)
      {
        com.airwatch.util.n.d("Exception processing fusion options", paramString1);
        return false;
      }
      if ((paramBoolean5) && (paramBoolean6))
      {
        localStringBuilder.append("2.4GHZ");
      }
      else
      {
        localStringBuilder.append("5.0GHZ");
        continue;
        label649:
        localStringBuilder.append(paramString2);
        continue;
        label660:
        localStringBuilder.append(paramString3);
        continue;
        label671:
        return true;
        localObject = "action.motorolasolutions.config.set";
        str1 = "com.motorolasolutions.wificonfig.WiFiConfig";
        str2 = "com.motorolasolutions.wificonfig";
        continue;
        j = 0;
        break;
        label697:
        if (paramBoolean4) {
          if ((!paramBoolean5) || (!paramBoolean6)) {
            if ((paramBoolean7) && (paramBoolean8))
            {
              continue;
              label725:
              str3 = "US";
            }
          }
        }
      }
    }
  }
  
  public void reboot(String paramString)
  {
    try
    {
      c.w(paramString);
      return;
    }
    catch (Exception paramString)
    {
      com.airwatch.util.n.d("An exception was encountered during reboot command. " + paramString.getMessage());
    }
  }
  
  public void removeCert(String paramString1, String paramString2, String paramString3)
  {
    if (paramString2 != null) {}
    try
    {
      if (paramString2.trim().length() > 0)
      {
        c.e(paramString1);
        c.d(paramString1);
      }
      c.k(paramString1, "KeyStore");
      c.k(paramString1, "TrustedStore");
      return;
    }
    catch (Exception paramString1)
    {
      com.airwatch.util.n.d("Motorola MX Manager : Unable to un-install Certificate.");
    }
  }
  
  public boolean removeEAPNetwork(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    paramString2 = ao.a();
    int j = -1;
    paramString2 = paramString2.iterator();
    if (paramString2.hasNext())
    {
      paramString3 = (WifiConfiguration)paramString2.next();
      if (!paramString1.contentEquals(paramString3.SSID)) {
        break label63;
      }
      j = paramString3.networkId;
    }
    label63:
    for (;;)
    {
      break;
      return ao.a(j);
    }
  }
  
  public boolean removeProtectedProcess(String paramString)
  {
    try
    {
      boolean bool = c.t(paramString);
      return bool;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Exception while removeProtectedProcess " + paramString);
      return false;
    }
    catch (Error localError)
    {
      for (;;)
      {
        com.airwatch.util.n.d("Error while removeProtectedProcess " + paramString);
      }
    }
  }
  
  public boolean requiresGsfAndroidId()
  {
    return false;
  }
  
  /* Error */
  public boolean resetCredentialStorage()
  {
    // Byte code:
    //   0: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   3: ldc_w 1206
    //   6: invokeinterface 1236 2 0
    //   11: pop
    //   12: iconst_0
    //   13: istore_1
    //   14: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   17: ldc_w 1202
    //   20: invokeinterface 1236 2 0
    //   25: pop
    //   26: iload_1
    //   27: ifeq +69 -> 96
    //   30: aload_0
    //   31: invokespecial 1238	com/airwatch/agent/enterprise/d:resetCredentialStorage	()Z
    //   34: pop
    //   35: iconst_0
    //   36: ireturn
    //   37: astore_2
    //   38: new 107	java/lang/StringBuilder
    //   41: dup
    //   42: ldc_w 1240
    //   45: invokespecial 112	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: aload_2
    //   49: invokevirtual 1241	java/lang/Error:toString	()Ljava/lang/String;
    //   52: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 1243	com/airwatch/util/n:e	(Ljava/lang/String;)V
    //   61: iconst_1
    //   62: istore_1
    //   63: goto -49 -> 14
    //   66: astore_2
    //   67: new 107	java/lang/StringBuilder
    //   70: dup
    //   71: ldc_w 1245
    //   74: invokespecial 112	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   77: aload_2
    //   78: invokevirtual 1241	java/lang/Error:toString	()Ljava/lang/String;
    //   81: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 1243	com/airwatch/util/n:e	(Ljava/lang/String;)V
    //   90: iconst_1
    //   91: istore_1
    //   92: goto -66 -> 26
    //   95: astore_2
    //   96: iconst_0
    //   97: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	MotorolaMXManager
    //   13	79	1	j	int
    //   37	12	2	localError1	Error
    //   66	12	2	localError2	Error
    //   95	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   0	12	37	java/lang/Error
    //   14	26	66	java/lang/Error
    //   0	12	95	java/lang/Exception
    //   14	26	95	java/lang/Exception
    //   30	35	95	java/lang/Exception
    //   38	61	95	java/lang/Exception
    //   67	90	95	java/lang/Exception
  }
  
  public boolean restoreAirwatchData()
  {
    boolean bool2 = false;
    try
    {
      String str = getUserDirectory() + "airwatch/backup/" + AirWatchApp.f().getPackageName();
      boolean bool1 = bool2;
      if (str != null)
      {
        bool1 = bool2;
        if (c(new File(str), new File(AirWatchApp.f().getFilesDir().getParent())))
        {
          com.airwatch.util.n.c("Copying restore directory successful");
          ad.d();
          ad.c();
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("failed to restore Airwatch data", localException);
    }
    return false;
  }
  
  /* Error */
  public boolean restoreBackUpApps()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aconst_null
    //   3: astore_3
    //   4: new 70	java/io/File
    //   7: dup
    //   8: new 107	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   15: aload_0
    //   16: invokevirtual 390	com/airwatch/agent/enterprise/MotorolaMXManager:getUserDirectory	()Ljava/lang/String;
    //   19: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: ldc_w 392
    //   25: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   31: invokespecial 223	java/io/File:<init>	(Ljava/lang/String;)V
    //   34: astore 4
    //   36: aload 4
    //   38: astore_3
    //   39: aload 4
    //   41: iconst_1
    //   42: iconst_0
    //   43: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   46: pop
    //   47: aload 4
    //   49: astore_3
    //   50: aload 4
    //   52: iconst_1
    //   53: iconst_0
    //   54: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   57: pop
    //   58: aload 4
    //   60: astore_3
    //   61: aload 4
    //   63: iconst_1
    //   64: iconst_0
    //   65: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   68: pop
    //   69: aload 4
    //   71: astore_3
    //   72: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   75: invokeinterface 1257 1 0
    //   80: istore_2
    //   81: iload_2
    //   82: istore_1
    //   83: aload 4
    //   85: iconst_1
    //   86: iconst_1
    //   87: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   90: pop
    //   91: aload 4
    //   93: iconst_1
    //   94: iconst_1
    //   95: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   98: pop
    //   99: aload 4
    //   101: iconst_1
    //   102: iconst_1
    //   103: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   106: pop
    //   107: iload_1
    //   108: ireturn
    //   109: astore 5
    //   111: aconst_null
    //   112: astore 4
    //   114: aload 4
    //   116: astore_3
    //   117: ldc_w 1259
    //   120: aload 5
    //   122: invokestatic 187	com/airwatch/util/n:d	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   125: aload 4
    //   127: ifnull -20 -> 107
    //   130: aload 4
    //   132: iconst_1
    //   133: iconst_1
    //   134: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   137: pop
    //   138: aload 4
    //   140: iconst_1
    //   141: iconst_1
    //   142: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   145: pop
    //   146: aload 4
    //   148: iconst_1
    //   149: iconst_1
    //   150: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   153: pop
    //   154: iconst_0
    //   155: ireturn
    //   156: astore 5
    //   158: aload_3
    //   159: astore 4
    //   161: aload 5
    //   163: astore_3
    //   164: aload 4
    //   166: ifnull +27 -> 193
    //   169: aload 4
    //   171: iconst_1
    //   172: iconst_1
    //   173: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   176: pop
    //   177: aload 4
    //   179: iconst_1
    //   180: iconst_1
    //   181: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   184: pop
    //   185: aload 4
    //   187: iconst_1
    //   188: iconst_1
    //   189: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   192: pop
    //   193: aload_3
    //   194: athrow
    //   195: astore 5
    //   197: aload_3
    //   198: astore 4
    //   200: aload 5
    //   202: astore_3
    //   203: goto -39 -> 164
    //   206: astore 5
    //   208: goto -94 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	211	0	this	MotorolaMXManager
    //   1	107	1	bool1	boolean
    //   80	2	2	bool2	boolean
    //   3	200	3	localObject1	Object
    //   34	165	4	localObject2	Object
    //   109	12	5	localRemoteException1	RemoteException
    //   156	6	5	localObject3	Object
    //   195	6	5	localObject4	Object
    //   206	1	5	localRemoteException2	RemoteException
    // Exception table:
    //   from	to	target	type
    //   4	36	109	android/os/RemoteException
    //   4	36	156	finally
    //   39	47	195	finally
    //   50	58	195	finally
    //   61	69	195	finally
    //   72	81	195	finally
    //   117	125	195	finally
    //   39	47	206	android/os/RemoteException
    //   50	58	206	android/os/RemoteException
    //   61	69	206	android/os/RemoteException
    //   72	81	206	android/os/RemoteException
  }
  
  public boolean runPrivCmd(String paramString)
  {
    if (c == null) {
      return false;
    }
    try
    {
      if (!c.x(paramString))
      {
        com.airwatch.util.n.d("Unable to run priviledge commands");
        return false;
      }
    }
    catch (Exception paramString)
    {
      com.airwatch.util.n.d("Exception occurred while trying to call privilige command service from agent");
      return false;
    }
    return true;
  }
  
  public void setBluetoothPolicy(com.airwatch.agent.profile.c paramC) {}
  
  public boolean setDefaultHomeScreen(String paramString)
  {
    try
    {
      boolean bool = c.d(paramString + ".home" + com.airwatch.agent.a.a.b(), paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public void setDisplayPolicy(f paramF)
  {
    try
    {
      c.i("displaybright" + com.airwatch.agent.a.a.b(), paramF.a());
      c.v("autorotate" + com.airwatch.agent.a.a.b(), paramF.b());
      c.a("sleeptime" + com.airwatch.agent.a.a.b(), paramF.c());
      c.w("stayawake" + com.airwatch.agent.a.a.b(), paramF.d());
      return;
    }
    catch (Exception paramF)
    {
      com.airwatch.util.n.d("Motorola MX Manager: Failed to set Display policy", paramF);
    }
  }
  
  public void setEncryptionPolicy(g paramG)
  {
    try
    {
      if ((paramG.b()) && (!c.m()))
      {
        paramG = String.format("%064x", new Object[] { new BigInteger((AirWatchDevice.b(AirWatchApp.f()) + "Cccce123cccccc654ccccccdddddddddddddddd163").getBytes("US-ASCII")) });
        c.b("sdcardencrypt", paramG.substring(0, 64));
      }
      return;
    }
    catch (RemoteException paramG)
    {
      com.airwatch.util.n.d("Motorola MX Manager: Failed to set encryption policy", paramG);
      return;
    }
    catch (UnsupportedEncodingException paramG)
    {
      com.airwatch.util.n.d("Motorola MX Manager: Failed to set encryption policy", paramG);
      return;
    }
    catch (Exception paramG)
    {
      com.airwatch.util.n.d("Motorola MX Manager: Failed to set encryption policy", paramG);
    }
  }
  
  public void setPasswordPolicy(com.airwatch.agent.profile.n paramN)
  {
    try
    {
      if (paramN.b > 0) {
        c.c("mxlogin" + com.airwatch.agent.a.a.b(), paramN.b);
      }
      if (paramN.d > 0L) {
        c.d("mxlogin" + com.airwatch.agent.a.a.b(), (int)paramN.d);
      }
      return;
    }
    catch (Exception paramN)
    {
      com.airwatch.util.n.d("Motorola MX Manager: Failed to set password policy", paramN);
    }
  }
  
  public void setRestrictionPolicy(r paramR)
  {
    boolean bool2 = true;
    if (c != null) {}
    for (;;)
    {
      try
      {
        com.airwatch.a.h.a localA = c;
        String str = "devres" + com.airwatch.agent.a.a.b();
        boolean bool3 = paramR.m();
        if ((!paramR.X()) || (!paramR.R())) {
          continue;
        }
        bool1 = true;
        if ((!paramR.V()) || (!paramR.R())) {
          continue;
        }
        if (!localA.a(str, bool3, bool1, bool2)) {
          com.airwatch.util.n.a("Moto MX failed to set Device Restrictions");
        }
        if (!c.a("sysres" + com.airwatch.agent.a.a.b(), paramR.af(), paramR.aj(), paramR.aa(), paramR.au(), paramR.av(), paramR.ac(), paramR.an(), paramR.e(), paramR.R())) {
          com.airwatch.util.n.a("Moto MX failed to set System Restrictions");
        }
      }
      catch (Exception localException)
      {
        boolean bool1;
        com.airwatch.util.n.d("Motorola : An exception occurred while setting restrictions. " + localException.getMessage());
        continue;
      }
      super.setCameraEnable(paramR.P());
      return;
      bool1 = false;
      continue;
      bool2 = false;
    }
  }
  
  public void setSoundPolicy(s paramS)
  {
    try
    {
      c.f("sysvolume" + com.airwatch.agent.a.a.b(), paramS.a());
      c.g("mediavolume" + com.airwatch.agent.a.a.b(), paramS.b());
      c.h("phonevolume" + com.airwatch.agent.a.a.b(), paramS.c());
      c.q("defaultnoti" + com.airwatch.agent.a.a.b(), paramS.d());
      c.r("dialpadtone" + com.airwatch.agent.a.a.b(), paramS.e());
      c.s("touchsound" + com.airwatch.agent.a.a.b(), paramS.f());
      c.t("mediavolume" + com.airwatch.agent.a.a.b(), paramS.g());
      c.u("vibrateontouch" + com.airwatch.agent.a.a.b(), paramS.h());
      return;
    }
    catch (Exception paramS)
    {
      com.airwatch.util.n.d("Motorola MX Manager: Failed to set Sound policy", paramS);
    }
  }
  
  public void setWifiSleepPolicy(r paramR)
  {
    if (c == null) {}
    int j;
    do
    {
      return;
      j = paramR.bW();
    } while (j == -1);
    try
    {
      c.a(j);
      return;
    }
    catch (Exception paramR)
    {
      paramR.printStackTrace();
    }
  }
  
  public boolean setXMLConfig(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    for (;;)
    {
      String str;
      try
      {
        str = paramString1 + com.airwatch.agent.a.a.b();
        if (paramString1.equalsIgnoreCase("XMLConfigCertConfig"))
        {
          c.h(str, paramString3);
          if (!paramString1.equalsIgnoreCase("XMLIntentBroadcastConfig")) {
            break;
          }
          e(paramString4);
          return true;
        }
        boolean bool = paramString1.equalsIgnoreCase("XMLCertConfig");
        if (bool)
        {
          try
          {
            paramString2 = Base64.decode(paramString4, 0);
            c.a(str, paramString3, paramString5, paramString2);
          }
          catch (Exception paramString2)
          {
            com.airwatch.util.n.d("Failed to configure XML profile", paramString2);
          }
          continue;
        }
        if (!paramString1.equalsIgnoreCase("XMLMdmConfig")) {
          break label162;
        }
      }
      catch (Exception paramString1)
      {
        com.airwatch.util.n.d("Exception while setting custom profile ", paramString1);
        return false;
      }
      com.airwatch.util.n.a("Trying to apply Moto mdmConfig");
      if (c.i(str, paramString3))
      {
        com.airwatch.util.n.a("Successfully applied Moto mdmConfig");
        continue;
        label162:
        if (paramString1.equalsIgnoreCase("XMLProxyConfig")) {
          c.j(str, paramString3);
        } else if (paramString1.equalsIgnoreCase("XMLForceRunCommand")) {
          c.a(true);
        } else if (paramString1.equalsIgnoreCase("XMLForceStopCommand")) {
          c.a(false);
        } else if (paramString1.equalsIgnoreCase("SystemSettingsConfig")) {
          super.setXMLConfig(paramString1, paramString2, paramString3, paramString4, paramString5);
        }
      }
    }
    com.airwatch.util.n.d("Motorola MX unsupported xml config type " + paramString1);
    return true;
  }
  
  public String substituteMacros(String paramString)
  {
    Object localObject = paramString;
    if (paramString.startsWith("/data/tmp/"))
    {
      localObject = paramString;
      if (!new File("/data/tmp/").canWrite())
      {
        localObject = new File("/data/tmp/airwatch");
        if (!((File)localObject).exists())
        {
          runPrivCmd("mkdir /data/tmp/airwatch");
          SystemClock.sleep(1000L);
        }
        if (!((File)localObject).canWrite())
        {
          runPrivCmd("chmod 777 /data/tmp/airwatch");
          SystemClock.sleep(1000L);
        }
        localObject = paramString.replaceFirst("/data/tmp", "/data/tmp/airwatch");
      }
    }
    return localObject;
  }
  
  public boolean supportsApplicationControl()
  {
    return false;
  }
  
  public boolean supportsEas()
  {
    return false;
  }
  
  public boolean supportsEmailSettings()
  {
    return false;
  }
  
  public boolean supportsRestrictions()
  {
    return true;
  }
  
  public boolean supportsSdCardEncryption()
  {
    return true;
  }
  
  public boolean takeServiceComplianceAction()
  {
    com.airwatch.util.n.a("Motorola MX Manager status : " + getServiceComplianceStatus() + ", " + getIsCompliant());
    if ((getServiceComplianceStatus()) || (getIsCompliant() == 2)) {
      return true;
    }
    try
    {
      ad localAd = ad.c();
      localAd.aV();
      localAd.o(localAd.aV() + 1);
      boolean bool = c.c();
      return bool;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Motorola MX Manager : Error while taking service compliance action " + localException.toString(), localException);
    }
    return false;
  }
  
  /* Error */
  public boolean uninstallApp(String paramString)
  {
    // Byte code:
    //   0: new 70	java/io/File
    //   3: dup
    //   4: new 107	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   11: aload_0
    //   12: invokevirtual 390	com/airwatch/agent/enterprise/MotorolaMXManager:getUserDirectory	()Ljava/lang/String;
    //   15: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: ldc_w 392
    //   21: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: invokespecial 223	java/io/File:<init>	(Ljava/lang/String;)V
    //   30: astore 5
    //   32: aload 5
    //   34: astore 4
    //   36: aload 5
    //   38: iconst_1
    //   39: iconst_0
    //   40: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   43: pop
    //   44: aload 5
    //   46: astore 4
    //   48: aload 5
    //   50: iconst_1
    //   51: iconst_0
    //   52: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   55: pop
    //   56: aload 5
    //   58: astore 4
    //   60: aload 5
    //   62: iconst_1
    //   63: iconst_0
    //   64: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   67: pop
    //   68: aload 5
    //   70: astore 4
    //   72: getstatic 27	com/airwatch/agent/enterprise/MotorolaMXManager:c	Lcom/airwatch/a/h/a;
    //   75: aload_1
    //   76: invokeinterface 417 2 0
    //   81: istore_2
    //   82: aload 5
    //   84: iconst_1
    //   85: iconst_1
    //   86: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   89: pop
    //   90: aload 5
    //   92: iconst_1
    //   93: iconst_1
    //   94: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   97: pop
    //   98: aload 5
    //   100: iconst_1
    //   101: iconst_1
    //   102: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   105: pop
    //   106: iload_2
    //   107: ireturn
    //   108: astore 4
    //   110: aconst_null
    //   111: astore 5
    //   113: aload 5
    //   115: astore 4
    //   117: new 107	java/lang/StringBuilder
    //   120: dup
    //   121: ldc_w 1566
    //   124: invokespecial 112	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   127: aload_1
    //   128: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokestatic 169	com/airwatch/util/n:d	(Ljava/lang/String;)V
    //   137: aload 5
    //   139: astore 4
    //   141: invokestatic 1571	com/airwatch/agent/appmanagement/i:a	()Lcom/airwatch/agent/appmanagement/i;
    //   144: aload_1
    //   145: invokevirtual 1573	com/airwatch/agent/appmanagement/i:a	(Ljava/lang/String;)Z
    //   148: istore_3
    //   149: iload_3
    //   150: istore_2
    //   151: aload 5
    //   153: ifnull -47 -> 106
    //   156: aload 5
    //   158: iconst_1
    //   159: iconst_1
    //   160: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   163: pop
    //   164: aload 5
    //   166: iconst_1
    //   167: iconst_1
    //   168: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   171: pop
    //   172: aload 5
    //   174: iconst_1
    //   175: iconst_1
    //   176: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   179: pop
    //   180: iload_3
    //   181: ireturn
    //   182: astore 4
    //   184: aconst_null
    //   185: astore 5
    //   187: aload 5
    //   189: astore 4
    //   191: new 107	java/lang/StringBuilder
    //   194: dup
    //   195: ldc_w 1575
    //   198: invokespecial 112	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   201: aload_1
    //   202: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: invokestatic 169	com/airwatch/util/n:d	(Ljava/lang/String;)V
    //   211: aload 5
    //   213: astore 4
    //   215: invokestatic 1571	com/airwatch/agent/appmanagement/i:a	()Lcom/airwatch/agent/appmanagement/i;
    //   218: aload_1
    //   219: invokevirtual 1573	com/airwatch/agent/appmanagement/i:a	(Ljava/lang/String;)Z
    //   222: istore_3
    //   223: iload_3
    //   224: istore_2
    //   225: aload 5
    //   227: ifnull -121 -> 106
    //   230: aload 5
    //   232: iconst_1
    //   233: iconst_1
    //   234: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   237: pop
    //   238: aload 5
    //   240: iconst_1
    //   241: iconst_1
    //   242: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   245: pop
    //   246: aload 5
    //   248: iconst_1
    //   249: iconst_1
    //   250: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   253: pop
    //   254: iload_3
    //   255: ireturn
    //   256: astore_1
    //   257: aconst_null
    //   258: astore 4
    //   260: aload 4
    //   262: ifnull +27 -> 289
    //   265: aload 4
    //   267: iconst_1
    //   268: iconst_1
    //   269: invokevirtual 263	java/io/File:setReadable	(ZZ)Z
    //   272: pop
    //   273: aload 4
    //   275: iconst_1
    //   276: iconst_1
    //   277: invokevirtual 266	java/io/File:setWritable	(ZZ)Z
    //   280: pop
    //   281: aload 4
    //   283: iconst_1
    //   284: iconst_1
    //   285: invokevirtual 269	java/io/File:setExecutable	(ZZ)Z
    //   288: pop
    //   289: aload_1
    //   290: athrow
    //   291: astore_1
    //   292: goto -32 -> 260
    //   295: astore 4
    //   297: goto -110 -> 187
    //   300: astore 4
    //   302: goto -189 -> 113
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	305	0	this	MotorolaMXManager
    //   0	305	1	paramString	String
    //   81	144	2	bool1	boolean
    //   148	107	3	bool2	boolean
    //   34	37	4	localFile1	File
    //   108	1	4	localException1	Exception
    //   115	25	4	localFile2	File
    //   182	1	4	localError1	Error
    //   189	93	4	localFile3	File
    //   295	1	4	localError2	Error
    //   300	1	4	localException2	Exception
    //   30	217	5	localFile4	File
    // Exception table:
    //   from	to	target	type
    //   0	32	108	java/lang/Exception
    //   0	32	182	java/lang/Error
    //   0	32	256	finally
    //   36	44	291	finally
    //   48	56	291	finally
    //   60	68	291	finally
    //   72	82	291	finally
    //   117	137	291	finally
    //   141	149	291	finally
    //   191	211	291	finally
    //   215	223	291	finally
    //   36	44	295	java/lang/Error
    //   48	56	295	java/lang/Error
    //   60	68	295	java/lang/Error
    //   72	82	295	java/lang/Error
    //   36	44	300	java/lang/Exception
    //   48	56	300	java/lang/Exception
    //   60	68	300	java/lang/Exception
    //   72	82	300	java/lang/Exception
  }
  
  public void updateInstalledState(String paramString1, String paramString2) {}
  
  public void wipeDevice(int paramInt)
  {
    if (paramInt == 2) {}
    try
    {
      com.airwatch.agent.f.c.a().a(CommandType.WIPE_EXTERNAL_STORAGE.value);
      if (AWService.b() < 50)
      {
        com.airwatch.util.n.d("Insufficient battery level for recovery mode device wipe");
        return;
      }
      c.C();
      return;
    }
    catch (Exception localException)
    {
      com.airwatch.util.n.d("Exception occurred while performing device wipe from Agent", localException);
    }
  }
}
