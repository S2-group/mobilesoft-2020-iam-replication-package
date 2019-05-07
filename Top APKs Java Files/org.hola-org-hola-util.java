package org.hola;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class util
{
  public static Boolean a = new Boolean(false);
  public static util b = null;
  static int c = -1;
  public static File d;
  private static SharedPreferences e;
  private static int f = 0;
  private static final Object g = new Object();
  private static String h;
  private static Boolean i;
  private static Boolean j;
  private static SimpleDateFormat k;
  private static SimpleDateFormat l;
  private static int m = 5;
  private static final String[] n = { "EMERGENCY", "ALERT", "CRITICAL", "ERROR", "WARNING", "NOTICE", "INFO", "DEBUG" };
  private static FileWriter o;
  private static String p;
  private static Object q = new Object();
  private static String r = "";
  private static Handler s;
  private static HandlerThread t;
  private static boolean u = false;
  
  public util() {}
  
  public static int a(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    return a(paramContext, paramInt, paramString1, paramString2, null);
  }
  
  public static int a(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = "uijava_" + paramString1;
    b(paramInt, "perr " + paramString1 + " " + paramString2);
    h(g(u(), paramString1), a(e(paramContext), e.getString("network", "").equals("dev"), paramString1, paramString2, paramString3));
    s.sendEmptyMessage(0);
    m();
    return -1;
  }
  
  public static int a(Context paramContext, String paramString)
  {
    synchronized (g)
    {
      f += 1;
      int i1;
      if (f > 1)
      {
        i1 = c;
        return i1;
      }
      e = f(paramContext);
      s();
      a(paramContext, paramString, e);
      o(r());
      v();
      k(paramContext);
      try
      {
        System.loadLibrary("jni_util");
        a().jni_init();
        c = 0;
        return c;
      }
      catch (UnsatisfiedLinkError paramString)
      {
        i1 = a(paramContext, "apk_install_no_lib", "cant load jni_util " + paramString);
        return i1;
      }
    }
  }
  
  public static int a(Context paramContext, String paramString1, String paramString2)
  {
    return a(paramContext, 3, paramString1, paramString2);
  }
  
  private static int a(File paramFile1, File paramFile2)
  {
    try
    {
      paramFile1 = new FileInputStream(paramFile1);
      paramFile2 = new FileOutputStream(paramFile2);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i1 = paramFile1.read(arrayOfByte, 0, 1024);
        if (i1 <= 0) {
          break;
        }
        paramFile2.write(arrayOfByte, 0, i1);
      }
      paramFile2.close();
      paramFile1.close();
      return 0;
    }
    catch (Exception paramFile1) {}
    return -1;
  }
  
  public static int a(File paramFile, String paramString)
  {
    return b(paramFile, new File(paramString));
  }
  
  public static int a(String paramString)
  {
    return a(null, paramString, null, false);
  }
  
  public static int a(String paramString1, int paramInt, String paramString2)
  {
    if (!d(paramInt)) {}
    do
    {
      return -1;
      o(t() + " " + paramString1 + " " + c(paramInt) + ": " + paramString2 + "\n");
    } while ((e(paramInt) & 0x200) == 0);
    throw new RuntimeException("zexit");
  }
  
  public static int a(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    return a(paramString1, paramString2, paramString3, paramBoolean, false, null, null, "", -1);
  }
  
  public static int a(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, String paramString4, int paramInt)
  {
    int i1;
    if ((paramStringBuilder1 != null) || (paramStringBuilder2 != null))
    {
      i1 = 1;
      paramString3 = paramString2;
      if (paramString1 != null) {
        paramString3 = paramString1 + " " + paramString2;
      }
      if ((paramBoolean2) || (a.booleanValue())) {
        break label619;
      }
      paramString2 = paramString3 + "\nexit\n";
      paramString1 = "su";
      if (paramString4 == null) {
        break label1070;
      }
      paramString1 = paramString4 + " " + paramString1;
    }
    label152:
    label276:
    label319:
    label362:
    label619:
    label673:
    label681:
    label689:
    label1070:
    for (;;)
    {
      int i2;
      int i4;
      int i5;
      int i3;
      if (i1 != 0) {
        if (paramStringBuilder1 != null)
        {
          paramString4 = a().jni_pipe();
          i1 = paramString4[0];
          i2 = paramString4[1];
          a().fcntl_cloexec(i1, 1);
          if (paramStringBuilder2 != null)
          {
            paramString4 = a().jni_pipe();
            i4 = paramString4[0];
            i5 = paramString4[1];
            a().fcntl_cloexec(i4, 1);
            i3 = i1;
            i1 = i5;
          }
        }
      }
      for (;;)
      {
        int i6;
        if ((paramBoolean1) || (paramString2 != null))
        {
          paramString4 = a().jni_pipe();
          i5 = paramString4[0];
          i6 = paramString4[1];
          a().fcntl_cloexec(i6, 1);
        }
        for (;;)
        {
          StringBuilder localStringBuilder = new StringBuilder().append(" 0>");
          int i8;
          if (i5 >= 0)
          {
            paramString4 = "&" + i5;
            localStringBuilder = localStringBuilder.append(paramString4).append(" 1>");
            if (paramStringBuilder1 == null) {
              break label673;
            }
            paramString4 = "&" + i2;
            localStringBuilder = localStringBuilder.append(paramString4).append(" 2>");
            if (paramStringBuilder2 == null) {
              break label681;
            }
            paramString4 = "&" + i1;
            paramString4 = paramString4;
            paramString1 = paramString1 + paramString4;
            b(6, "sys_exec " + paramString1);
            i8 = a().jni_sys_exec_bg("|u " + paramString1);
            if (i8 >= 0) {
              break label689;
            }
            b(3, "exec failed " + paramString1);
            paramInt = i1;
            i1 = -1;
          }
          for (;;)
          {
            if (i4 != -1) {
              a().pipe_close(i4);
            }
            if (paramInt != -1) {
              a().pipe_close(paramInt);
            }
            if (i3 != -1) {
              a().pipe_close(i3);
            }
            if (i2 != -1) {
              a().pipe_close(i2);
            }
            if (i5 != -1) {
              a().pipe_close(i5);
            }
            if (i6 != -1) {
              a().pipe_close(i6);
            }
            b(5, "exec ret " + i1 + " " + paramString3);
            return i1;
            i1 = 0;
            break;
            paramString2 = new StringBuilder();
            if (!paramBoolean2) {}
            for (paramString1 = "/system/bin/hola_svc_rt";; paramString1 = "")
            {
              paramString1 = paramString1 + " " + paramString3;
              paramString2 = null;
              break;
            }
            paramString4 = "/dev/null";
            break label276;
            paramString4 = "/dev/null";
            break label319;
            paramString4 = "/dev/null";
            break label362;
            if (paramString2 != null) {
              a().jni_write_str(i6, paramString2);
            }
            if (paramStringBuilder1 != null) {
              paramStringBuilder1.setLength(0);
            }
            if (paramStringBuilder2 != null) {
              paramStringBuilder2.setLength(0);
            }
            int i7 = a().jni_sys_waitpid(i8, paramInt);
            if ((i7 == 65280) && (paramInt >= 0))
            {
              b(5, "sys_exec timeout send SIGTERM");
              a().jni_kill(i8, 15);
              if (a().jni_sys_waitpid(i8, 500) == 65280)
              {
                b(5, "sys_exec timeout send SIGKILL");
                a().jni_kill(i8, 9);
                if (a().jni_sys_waitpid(i8, 500) == 65280) {
                  b(3, "failed to kill timed out exec");
                }
              }
            }
            paramInt = i2;
            if (i7 >= 0)
            {
              paramInt = i2;
              if (paramStringBuilder1 != null)
              {
                if (i2 != -1) {
                  a().pipe_close(i2);
                }
                i2 = -1;
                for (;;)
                {
                  paramString1 = a().jni_read(i3);
                  paramInt = i2;
                  if (paramString1 == null) {
                    break;
                  }
                  if (paramStringBuilder1 != null) {
                    paramStringBuilder1.append(paramString1 + "\n");
                  }
                }
              }
            }
            if ((i7 >= 0) && (paramStringBuilder2 != null))
            {
              if (i1 != -1) {
                a().pipe_close(i1);
              }
              i8 = -1;
              for (;;)
              {
                paramString1 = a().jni_read(i4);
                if (paramString1 == null) {
                  break;
                }
                if (paramStringBuilder2 != null) {
                  paramStringBuilder2.append(paramString1 + "\n");
                }
              }
              i1 = i7;
              i2 = paramInt;
              paramInt = i8;
            }
            else
            {
              i2 = paramInt;
              paramInt = i1;
              i1 = i7;
            }
          }
          i5 = -1;
          i6 = -1;
        }
        i5 = -1;
        i4 = -1;
        i3 = i1;
        i1 = i5;
        continue;
        i1 = -1;
        i2 = -1;
        break label152;
        i2 = -1;
        i1 = -1;
        i4 = -1;
        i3 = -1;
      }
    }
  }
  
  public static int a(String paramString, StringBuilder paramStringBuilder)
  {
    return a(null, paramString, null, false, true, null, paramStringBuilder, null, -1);
  }
  
  public static int a(String paramString, StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, int paramInt)
  {
    return a(null, paramString, null, false, false, paramStringBuilder1, paramStringBuilder2, null, paramInt);
  }
  
  public static Bitmap a(Bitmap paramBitmap)
  {
    return a(paramBitmap, "grayscale");
  }
  
  private static Bitmap a(Bitmap paramBitmap, String paramString)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), paramBitmap.getConfig());
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColorFilter(new ColorMatrixColorFilter(p(paramString)));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }
  
  /* Error */
  public static Object a(Object paramObject, String paramString, StringBuilder paramStringBuilder)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokevirtual 384	java/lang/Object:getClass	()Ljava/lang/Class;
    //   6: astore 4
    //   8: aload 4
    //   10: aload_1
    //   11: invokevirtual 390	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   14: astore 5
    //   16: aload 5
    //   18: iconst_1
    //   19: invokevirtual 395	java/lang/reflect/Field:setAccessible	(Z)V
    //   22: aload 5
    //   24: aload_0
    //   25: invokevirtual 399	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   28: astore_0
    //   29: aload_0
    //   30: areturn
    //   31: astore 5
    //   33: aload_3
    //   34: astore_0
    //   35: aload_2
    //   36: ifnull -7 -> 29
    //   39: aload_2
    //   40: iconst_0
    //   41: invokevirtual 311	java/lang/StringBuilder:setLength	(I)V
    //   44: aload_2
    //   45: new 98	java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   52: ldc_w 401
    //   55: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: aload_1
    //   59: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: ldc_w 403
    //   65: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: aload 4
    //   70: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   73: ldc 113
    //   75: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload 5
    //   80: invokevirtual 406	java/lang/NoSuchFieldException:getMessage	()Ljava/lang/String;
    //   83: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aconst_null
    //   94: areturn
    //   95: astore 5
    //   97: aload_3
    //   98: astore_0
    //   99: aload_2
    //   100: ifnull -71 -> 29
    //   103: aload_2
    //   104: iconst_0
    //   105: invokevirtual 311	java/lang/StringBuilder:setLength	(I)V
    //   108: aload_2
    //   109: new 98	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   116: ldc_w 408
    //   119: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aload_1
    //   123: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 403
    //   129: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload 4
    //   134: invokevirtual 195	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   137: ldc 113
    //   139: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload 5
    //   144: invokevirtual 409	java/lang/IllegalAccessException:getMessage	()Ljava/lang/String;
    //   147: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: aconst_null
    //   158: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	paramObject	Object
    //   0	159	1	paramString	String
    //   0	159	2	paramStringBuilder	StringBuilder
    //   1	97	3	localObject	Object
    //   6	127	4	localClass	Class
    //   14	9	5	localField	java.lang.reflect.Field
    //   31	48	5	localNoSuchFieldException	NoSuchFieldException
    //   95	48	5	localIllegalAccessException	IllegalAccessException
    // Exception table:
    //   from	to	target	type
    //   8	22	31	java/lang/NoSuchFieldException
    //   22	29	95	java/lang/IllegalAccessException
  }
  
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = URLEncoder.encode(paramString1, "UTF-8") + "=" + URLEncoder.encode(paramString2, "UTF-8");
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      b(3, "unsupported encoding");
    }
    return null;
  }
  
  private static String a(String paramString1, boolean paramBoolean, String paramString2, String paramString3, String paramString4)
  {
    paramString2 = new StringBuilder().append("perr_").append(paramString2).append(" ").append(paramString3).append("\n").append(d()).append(q());
    if (paramString1 == null)
    {
      paramString1 = "";
      paramString2 = paramString2.append(paramString1);
      if ((h == null) || (!u)) {
        break label182;
      }
    }
    label182:
    for (paramString1 = "Imei: " + h + "\n";; paramString1 = "")
    {
      paramString2 = paramString2.append(paramString1).append("\n");
      paramString1 = paramString4;
      if (paramString4 == null) {
        paramString1 = "";
      }
      return paramString1;
      paramString3 = new StringBuilder().append("Cid: ").append(paramString1).append(" ");
      if (paramBoolean) {}
      for (paramString1 = "dev";; paramString1 = "release")
      {
        paramString1 = paramString1 + "\n";
        break;
      }
    }
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, "UTF-8");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      b(3, "cant convert byte2str " + paramArrayOfByte.toString());
    }
    return null;
  }
  
  public static util a()
  {
    if (b == null) {
      b = new util();
    }
    return b;
  }
  
  public static void a(int paramInt)
  {
    try
    {
      Thread.currentThread();
      Thread.sleep(paramInt);
      return;
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  public static void a(Context paramContext)
  {
    l(paramContext);
  }
  
  private static void a(Context paramContext, String paramString, SharedPreferences paramSharedPreferences)
  {
    String str = paramContext.getCacheDir().toString() + "/log";
    p = paramString + "/log";
    h(str);
    h(p);
    d = new File(str + "/app.log");
    int i1 = paramSharedPreferences.getInt("zerr_level", -1);
    m = i1;
    if (i1 == -1) {
      if (!e("/data/zerr")) {
        break label169;
      }
    }
    for (i1 = 7;; i1 = 5)
    {
      m = i1;
      try
      {
        o = new FileWriter(d, true);
        if (!r.equals(""))
        {
          o(r);
          r = "";
        }
        return;
      }
      catch (IOException paramString)
      {
        label169:
        b(paramContext, "zerr_init failed: " + paramString.toString());
      }
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    a(paramContext, paramString1, paramString2, paramString3, paramInt1, paramInt2, null, null, null);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4, String paramString5, String paramString6)
  {
    b(7, "show notification " + paramString1 + " " + paramString2 + ": " + paramString3);
    paramString2 = new NotificationCompat.Builder(paramContext).setSmallIcon(paramInt1).setContentTitle(paramString2).setContentText(paramString3).setLargeIcon(BitmapFactory.decodeResource(paramContext.getResources(), paramInt2));
    paramString3 = new Intent(paramContext, app.class);
    if (paramString4 != null) {
      paramString3.setAction(paramString4);
    }
    if ((paramString5 != null) && (paramString6 != null)) {
      paramString3.putExtra(paramString5, paramString6);
    }
    paramString4 = TaskStackBuilder.create(paramContext);
    paramString4.addParentStack(app.class);
    paramString4.addNextIntent(paramString3);
    paramString2.setContentIntent(paramString4.getPendingIntent(0, 134217728));
    paramContext = (NotificationManager)paramContext.getSystemService("notification");
    paramString2 = paramString2.build();
    paramString2.flags |= 0x10;
    paramContext.notify(paramString1, 0, paramString2);
  }
  
  public static void a(SharedPreferences paramSharedPreferences, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.remove(paramString);
    paramSharedPreferences.commit();
    b(7, "conf del " + paramString);
  }
  
  public static void a(SharedPreferences paramSharedPreferences, String paramString, int paramInt)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putInt(paramString, paramInt);
    paramSharedPreferences.commit();
    b(7, "conf set " + paramString + "=" + paramInt);
  }
  
  public static void a(SharedPreferences paramSharedPreferences, String paramString, Boolean paramBoolean)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putBoolean(paramString, paramBoolean.booleanValue());
    paramSharedPreferences.commit();
    b(7, "conf set " + paramString + "=" + paramBoolean);
  }
  
  public static void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString1, paramString2);
    paramSharedPreferences.commit();
    b(7, "conf set " + paramString1 + "=" + paramString2);
  }
  
  public static void a(String paramString, int paramInt, boolean paramBoolean)
  {
    dj localDj = new dj();
    dk localDk = new dk(paramBoolean);
    localDj.a = paramString;
    localDj.b = paramInt;
    a("/proc", localDk, localDj);
  }
  
  public static void a(String paramString, dr paramDr, Object paramObject)
  {
    File[] arrayOfFile = new File(paramString).listFiles();
    if (arrayOfFile == null) {}
    label64:
    for (;;)
    {
      return;
      int i2 = arrayOfFile.length;
      int i1 = 0;
      for (;;)
      {
        if (i1 >= i2) {
          break label64;
        }
        File localFile = arrayOfFile[i1];
        if (paramDr.a(paramString, localFile.getName(), localFile.getAbsolutePath(), paramObject) != 0) {
          break;
        }
        i1 += 1;
      }
    }
  }
  
  public static void a(String paramString, boolean paramBoolean)
  {
    a(paramString, 15, paramBoolean);
  }
  
  public static boolean a(File paramFile)
  {
    try
    {
      if (paramFile.getParent() == null) {}
      while (!paramFile.getCanonicalFile().equals(paramFile.getAbsoluteFile()))
      {
        return true;
        paramFile = new File(paramFile.getParentFile().getCanonicalFile(), paramFile.getName());
      }
      return false;
    }
    catch (IOException paramFile) {}
  }
  
  private static int b(int paramInt)
  {
    return paramInt & 0xF;
  }
  
  private static int b(int paramInt, String paramString)
  {
    return a("util", paramInt, paramString);
  }
  
  public static int b(Context paramContext)
  {
    return paramContext.getApplicationInfo().uid;
  }
  
  public static int b(File paramFile)
  {
    if (!paramFile.exists()) {
      b(7, "file " + paramFile + " does not exist");
    }
    do
    {
      return 0;
      File[] arrayOfFile = paramFile.listFiles();
      int i1 = 0;
      if ((arrayOfFile != null) && (i1 < arrayOfFile.length))
      {
        if (arrayOfFile[i1].isDirectory()) {
          b(arrayOfFile[i1]);
        }
        for (;;)
        {
          i1 += 1;
          break;
          arrayOfFile[i1].delete();
        }
      }
    } while (paramFile.delete());
    return -1;
  }
  
  private static int b(File paramFile1, File paramFile2)
  {
    if ((a(paramFile1, paramFile2) < 0) || (d(paramFile1) < 0)) {
      return -1;
    }
    return 0;
  }
  
  public static int b(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new BufferedWriter(new FileWriter(paramString1));
      paramString1.write(paramString2);
      paramString1.close();
      return 0;
    }
    catch (IOException paramString1) {}
    return -1;
  }
  
  private static int b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 1000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 10000);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient((HttpParams)localObject);
    paramString2 = l() + "perr?" + a("id", paramString2) + "&" + a("file", paramString4) + "&" + a("ver", "1.2.406");
    if (u)
    {
      int i1 = new Random().nextInt();
      localObject = new dq(ByteBuffer.allocate(4).putInt(i1).array());
      paramString2 = paramString2 + "&" + a("key", Integer.toHexString(i1));
    }
    for (;;)
    {
      paramString4 = paramString2;
      if (paramString1 != null) {
        paramString4 = paramString2 + "&" + a("cid", paramString1);
      }
      paramString1 = paramString4;
      if (paramString3 != null) {
        paramString1 = paramString4 + "&" + a("timestamp", paramString3);
      }
      paramString1 = new HttpPost(paramString1);
      try
      {
        paramString1.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        if (localObject == null) {}
        for (;;)
        {
          paramString1.setEntity(new ByteArrayEntity(l(a("filehead", paramString5))));
          paramString1 = localDefaultHttpClient.execute(paramString1);
          if (paramString1.getStatusLine().getStatusCode() == 200) {
            break;
          }
          paramString1 = EntityUtils.toByteArray(paramString1.getEntity());
          return b(3, "zconsole_send failed invalid response " + new String(paramString1, "UTF-8"));
          paramString5 = Base64.encodeToString(((dq)localObject).a(paramString5), 0);
        }
        return 0;
      }
      catch (ClientProtocolException paramString1)
      {
        return b(3, "zconsole_send_failed_client " + paramString1.toString());
      }
      catch (IOException paramString1)
      {
        return b(3, "zconsole_send_failed " + paramString1.toString());
      }
      localObject = null;
    }
  }
  
  public static int b(String paramString, StringBuilder paramStringBuilder)
  {
    return a(null, paramString, null, false, false, null, paramStringBuilder, null, -1);
  }
  
  public static String b()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  public static String b(String paramString)
  {
    return c(paramString, null);
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
  
  public static int c(String paramString1, String paramString2)
  {
    return b(paramString1, paramString2 + "\n");
  }
  
  public static long c(File paramFile)
  {
    long l1 = 0L;
    long l2;
    if (!paramFile.exists()) {
      l2 = l1;
    }
    int i1;
    do
    {
      do
      {
        return l2;
        paramFile = paramFile.listFiles();
        i1 = 0;
        l2 = l1;
      } while (paramFile == null);
      l2 = l1;
    } while (i1 >= paramFile.length);
    if (a(paramFile[i1])) {}
    for (;;)
    {
      i1 += 1;
      break;
      if (paramFile[i1].isDirectory()) {
        l1 += c(paramFile[i1]);
      } else {
        l1 += paramFile[i1].length();
      }
    }
  }
  
  public static String c()
  {
    return Build.MANUFACTURER + " " + Build.BRAND + " " + Build.MODEL;
  }
  
  private static String c(int paramInt)
  {
    return n[b(paramInt)];
  }
  
  public static String c(Context paramContext)
  {
    return paramContext.getApplicationInfo().nativeLibraryDir;
  }
  
  public static String c(String paramString, StringBuilder paramStringBuilder)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (a(null, paramString, null, false, false, localStringBuilder, paramStringBuilder, null, -1) == -1) {
      return null;
    }
    return localStringBuilder.toString();
  }
  
  public static void c(Context paramContext, String paramString)
  {
    b(7, "notification cancel " + paramString);
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramString, 0);
  }
  
  /* Error */
  public static byte[] c(String paramString)
  {
    // Byte code:
    //   0: new 909	java/io/RandomAccessFile
    //   3: dup
    //   4: aload_0
    //   5: ldc_w 910
    //   8: invokespecial 912	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   11: astore_3
    //   12: aload_3
    //   13: invokevirtual 913	java/io/RandomAccessFile:length	()J
    //   16: lstore_1
    //   17: lload_1
    //   18: ldc2_w 914
    //   21: invokestatic 921	java/lang/Math:min	(JJ)J
    //   24: l2i
    //   25: newarray byte
    //   27: astore 4
    //   29: lload_1
    //   30: ldc2_w 914
    //   33: lcmp
    //   34: ifle +11 -> 45
    //   37: iconst_3
    //   38: ldc_w 923
    //   41: invokestatic 92	org/hola/util:b	(ILjava/lang/String;)I
    //   44: pop
    //   45: aload_3
    //   46: aload 4
    //   48: invokevirtual 926	java/io/RandomAccessFile:readFully	([B)V
    //   51: aload_3
    //   52: invokevirtual 927	java/io/RandomAccessFile:close	()V
    //   55: aload 4
    //   57: areturn
    //   58: astore_0
    //   59: aconst_null
    //   60: areturn
    //   61: astore_3
    //   62: iconst_3
    //   63: new 98	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   70: ldc_w 929
    //   73: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: aload_0
    //   77: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: ldc 113
    //   82: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: aload_3
    //   86: invokevirtual 498	java/io/IOException:toString	()Ljava/lang/String;
    //   89: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokestatic 92	org/hola/util:b	(ILjava/lang/String;)I
    //   98: pop
    //   99: aconst_null
    //   100: areturn
    //   101: astore 5
    //   103: iconst_3
    //   104: new 98	java/lang/StringBuilder
    //   107: dup
    //   108: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   111: ldc_w 931
    //   114: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: aload 5
    //   119: invokevirtual 443	java/io/UnsupportedEncodingException:toString	()Ljava/lang/String;
    //   122: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 92	org/hola/util:b	(ILjava/lang/String;)I
    //   131: pop
    //   132: goto -81 -> 51
    //   135: astore 5
    //   137: iconst_3
    //   138: new 98	java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   145: ldc_w 931
    //   148: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 5
    //   153: invokevirtual 498	java/io/IOException:toString	()Ljava/lang/String;
    //   156: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokestatic 92	org/hola/util:b	(ILjava/lang/String;)I
    //   165: pop
    //   166: goto -115 -> 51
    //   169: astore_3
    //   170: iconst_3
    //   171: new 98	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   178: ldc_w 933
    //   181: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: aload_0
    //   185: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   191: invokestatic 92	org/hola/util:b	(ILjava/lang/String;)I
    //   194: pop
    //   195: aconst_null
    //   196: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	197	0	paramString	String
    //   16	14	1	l1	long
    //   11	41	3	localRandomAccessFile	java.io.RandomAccessFile
    //   61	25	3	localIOException1	IOException
    //   169	1	3	localIOException2	IOException
    //   27	29	4	arrayOfByte	byte[]
    //   101	17	5	localUnsupportedEncodingException	UnsupportedEncodingException
    //   135	17	5	localIOException3	IOException
    // Exception table:
    //   from	to	target	type
    //   0	12	58	java/io/IOException
    //   12	17	61	java/io/IOException
    //   45	51	101	java/io/UnsupportedEncodingException
    //   45	51	135	java/io/IOException
    //   51	55	169	java/io/IOException
  }
  
  private static int d(File paramFile)
  {
    if (paramFile.delete()) {
      return 0;
    }
    return -1;
  }
  
  public static int d(String paramString1, String paramString2)
  {
    return a(new File(paramString1), new File(paramString2));
  }
  
  public static String d()
  {
    return "Version: 1.2.406 android arm\nTag: Ntag-1_2_406\nBuild date: 14-Jan-14 10:48:37\nMakeflags: CONFIG_BAT_CYCLE=y CONFIG_BATREQ=y AUTO_SIGN=y RELEASE=y ARCH=ANDROID DIST=APP\nOS Version: " + b() + "\nDevice: " + c() + "\nCPU ABI: " + Build.CPU_ABI + "\nCPU ABI2: " + Build.CPU_ABI2 + "\n";
  }
  
  public static String d(Context paramContext)
  {
    return paramContext.getFilesDir().toString();
  }
  
  public static String d(String paramString)
  {
    try
    {
      paramString = new BufferedReader(new FileReader(paramString));
      String str = paramString.readLine();
      paramString.close();
      return str;
    }
    catch (IOException paramString) {}
    return null;
  }
  
  public static void d(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }
  
  private static boolean d(int paramInt)
  {
    return b(paramInt) <= m;
  }
  
  private static int e(int paramInt)
  {
    return 0xFF00 & paramInt;
  }
  
  public static int e(String paramString1, String paramString2)
  {
    return b(new File(paramString1), new File(paramString2));
  }
  
  public static String e()
  {
    return h;
  }
  
  public static String e(Context paramContext)
  {
    return d(d(paramContext) + "/" + "hola_svc" + ".cid");
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean e(String paramString)
  {
    return new File(paramString).exists();
  }
  
  public static int f(Context paramContext, String paramString)
  {
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString == null) {
      return -1;
    }
    paramContext.startActivity(paramString);
    return 0;
  }
  
  public static int f(String paramString)
  {
    return b(new File(paramString));
  }
  
  public static int f(String paramString1, String paramString2)
  {
    paramString1 = paramString1.split("\\.");
    paramString2 = paramString2.split("\\.");
    int i1 = 0;
    while ((i1 < paramString1.length) && (i1 < paramString2.length) && (k(paramString1[i1]) == k(paramString2[i1]))) {
      i1 += 1;
    }
    if (i1 == 3) {
      return 0;
    }
    return k(paramString1[i1]) - k(paramString2[i1]);
  }
  
  public static SharedPreferences f(Context paramContext)
  {
    return paramContext.getSharedPreferences("conf", 0);
  }
  
  public static void f()
  {
    synchronized (g)
    {
      f -= 1;
      if (f != 0) {
        return;
      }
      w();
      i();
      return;
    }
  }
  
  public static long g(String paramString)
  {
    return c(new File(paramString));
  }
  
  public static String g(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramString = paramContext.getPackageInfo(paramString, 0);
      return paramString.applicationInfo.loadLabel(paramContext).toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      b(3, "cant find package name " + paramContext.getMessage());
    }
    return null;
  }
  
  private static String g(String paramString1, String paramString2)
  {
    return paramString1 + "_perr_" + paramString2;
  }
  
  public static boolean g()
  {
    if (i != null) {
      return i.booleanValue();
    }
    Boolean localBoolean = Boolean.valueOf(e.getBoolean("dbg_hola_svc", false));
    i = localBoolean;
    return localBoolean.booleanValue();
  }
  
  public static boolean g(Context paramContext)
  {
    try
    {
      int i1 = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps");
      return i1 == 1;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      a(paramContext, "apk_non_play_setting_failed", localSettingNotFoundException.toString());
    }
    return true;
  }
  
  private static int h(String paramString1, String paramString2)
  {
    if (b(p + "/" + paramString1 + ".log", paramString2) != 0) {
      return b(3, "perr failed creating file " + paramString1);
    }
    return -1;
  }
  
  public static Drawable h(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationIcon(paramString);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static void h(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.settings.SECURITY_SETTINGS"));
  }
  
  public static boolean h()
  {
    if (j != null) {
      return j.booleanValue();
    }
    Boolean localBoolean = Boolean.valueOf(e.getBoolean("is_debug_layout", false));
    j = localBoolean;
    return localBoolean.booleanValue();
  }
  
  public static boolean h(String paramString)
  {
    return new File(paramString).mkdirs();
  }
  
  public static int i(String paramString)
  {
    dl localDl = new dl();
    dm localDm = new dm();
    localDl.a = paramString;
    localDl.b = 0;
    a("/proc", localDm, localDl);
    return localDl.b;
  }
  
  public static Drawable i(Context paramContext, String paramString)
  {
    Drawable localDrawable = h(paramContext, paramString);
    paramString = localDrawable;
    if (localDrawable == null) {
      paramString = paramContext.getResources().getDrawable(2130837661);
    }
    return paramString;
  }
  
  public static String i(Context paramContext)
  {
    return paramContext.getPackageManager().getInstallerPackageName("org.hola");
  }
  
  public static void i()
  {
    if (o == null) {
      return;
    }
    try
    {
      o.flush();
      o.close();
      o = null;
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public static int j()
  {
    if (e.getBoolean("dbg_2_3", false)) {
      return 9;
    }
    return Build.VERSION.SDK_INT;
  }
  
  public static long j(String paramString)
  {
    return new File(paramString).length();
  }
  
  public static String j(Context paramContext)
  {
    return ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
  }
  
  public static int k(String paramString)
  {
    try
    {
      int i1 = Integer.parseInt(paramString);
      return i1;
    }
    catch (NumberFormatException paramString) {}
    return 0;
  }
  
  public static String k()
  {
    String str2 = e.getString("dbg_client_cgi_ip_port", "");
    String str1 = str2;
    if (str2.equals("")) {
      str1 = "client.hola.org";
    }
    return str1;
  }
  
  private static void k(Context paramContext)
  {
    if (paramContext.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
      h = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    }
  }
  
  public static String l()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("https://").append(k()).append("/client_cgi");
    if (e.getString("network", "").equals("dev")) {}
    for (String str = "_dev";; str = "") {
      return str + "/";
    }
  }
  
  private static void l(Context paramContext)
  {
    h(paramContext.getCacheDir().toString() + "/log");
    h(p);
  }
  
  public static byte[] l(String paramString)
  {
    return paramString.getBytes();
  }
  
  public static void m()
  {
    s.sendEmptyMessage(0);
  }
  
  public static long n()
  {
    return System.nanoTime() / 1000000L;
  }
  
  private static String n(String paramString)
  {
    
    try
    {
      paramString = k.format(l.parse(paramString));
      return paramString;
    }
    catch (ParseException paramString) {}
    return null;
  }
  
  public static int o()
  {
    return a().jni_getgid();
  }
  
  private static int o(String paramString)
  {
    synchronized (q)
    {
      if (f == 0)
      {
        r += paramString;
        return -1;
      }
      if (o == null) {
        return -1;
      }
    }
    try
    {
      o.write(paramString);
      o.flush();
      if (d.length() >= 131072L)
      {
        i();
        if ((p == null) || (!e(p))) {
          break label145;
        }
        a(d, p + "/app.log.1");
      }
      try
      {
        for (;;)
        {
          o = new FileWriter(d, true);
          return -1;
          label145:
          d(d);
        }
      }
      catch (IOException paramString)
      {
        for (;;) {}
      }
    }
    catch (IOException paramString)
    {
      for (;;) {}
    }
  }
  
  private static float[] p(String paramString)
  {
    if (paramString.equals("grayscale")) {
      return new float[] { 0.21F, 0.71F, 0.07F, 0.0F, 0.0F, 0.21F, 0.71F, 0.07F, 0.0F, 0.0F, 0.21F, 0.71F, 0.07F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F };
    }
    if (paramString.equals("dim")) {
      return new float[] { 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F };
    }
    b(3, "invalid color matrix " + paramString);
    return null;
  }
  
  private static String q()
  {
    String str = System.getProperty("net.hostname");
    if (str != null) {
      return "Hostname: " + str + "\n";
    }
    return "";
  }
  
  private static String r()
  {
    return "Hola app logger\n" + d() + q();
  }
  
  private static void s()
  {
    if (k != null) {
      return;
    }
    k = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.US);
    k.setTimeZone(TimeZone.getTimeZone("UTC"));
    l = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
    l.setTimeZone(TimeZone.getTimeZone("UTC"));
  }
  
  private static String t()
  {
    s();
    return k.format(new Date());
  }
  
  private static String u()
  {
    s();
    return l.format(new Date());
  }
  
  private static void v()
  {
    t = new HandlerThread("perr", -2);
    t.start();
    s = new dh(t.getLooper());
  }
  
  private static void w()
  {
    if (!t.quit())
    {
      b(3, "perr thread quit failed");
      return;
    }
    try
    {
      t.join();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      b(3, "perr thread interrupt failed " + localInterruptedException);
    }
  }
  
  public native int fcntl_cloexec(int paramInt1, int paramInt2);
  
  public native int ipc(int paramInt, String[] paramArrayOfString, util.ipc_result paramIpc_result);
  
  public native int ipc_fd_pass(String[] paramArrayOfString, int paramInt, util.ipc_result paramIpc_result);
  
  public native int jni_getgid();
  
  public native void jni_init();
  
  public native int jni_kill(int paramInt1, int paramInt2);
  
  public native int[] jni_pipe();
  
  public native String jni_read(int paramInt);
  
  public native int jni_sys_exec_bg(String paramString);
  
  public native int jni_sys_waitpid(int paramInt1, int paramInt2);
  
  public native int jni_write_str(int paramInt, String paramString);
  
  public native int pipe_close(int paramInt);
  
  public native void pipe_fds_to_protect_ack(int paramInt1, int paramInt2);
  
  public native int[] pipe_fds_to_protect_get(int paramInt);
  
  public native int pipe_server_accept(int paramInt1, int paramInt2);
  
  public native int pipe_server_listen(byte[] paramArrayOfByte);
  
  public native void pipe_wake(int paramInt);
}
