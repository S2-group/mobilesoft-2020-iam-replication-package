package com.netspark.android.d.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import com.netspark.android.apps.al;
import com.netspark.android.netsvpn.dx;
import com.scottyab.rootbeer.b;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class f
{
  private static String[] a = { "/data" };
  private static String[] b = { "/etc", "/system/xbin", "/system/bin", "/system/sbin", "/system", "/vendor/bin", "/data", "/", "/sbin" };
  private static String[] c = { "echo -BOC-", "id" };
  private static long d;
  private static List<String> e;
  
  static int a(Context paramContext, a paramA)
  {
    Object localObject = "";
    boolean bool = true;
    for (;;)
    {
      int i;
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(al.a(0)).iterator();
        i = 0;
        paramContext = (Context)localObject;
        j = i;
        localObject = paramContext;
        k = i;
        try
        {
          if (!localIterator.hasNext()) {
            break label305;
          }
          j = i;
          localPackageInfo = (PackageInfo)localIterator.next();
          j = i;
          if (localPackageInfo.applicationInfo == null)
          {
            localObject = "";
          }
          else
          {
            j = i;
            localObject = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
          }
          j = i;
          if (((String)localObject).toLowerCase().contains("hidabroot")) {
            continue;
          }
          j = i;
          if (localPackageInfo.packageName.contains("hidabroot")) {
            continue;
          }
          j = i;
          if (((String)localObject).toLowerCase().contains("root")) {
            break label394;
          }
          j = i;
          if (!localPackageInfo.packageName.contains("root")) {
            break label389;
          }
        }
        catch (Throwable localThrowable1)
        {
          PackageInfo localPackageInfo;
          StringBuilder localStringBuilder;
          break label266;
        }
        if (j == 0) {
          continue;
        }
        i += 1;
        j = i;
        localStringBuilder = new StringBuilder();
        j = i;
        localStringBuilder.append(paramContext);
        j = i;
        localStringBuilder.append(localPackageInfo.packageName);
        j = i;
        localStringBuilder.append(" - '");
        j = i;
        localStringBuilder.append((String)localObject);
        j = i;
        localStringBuilder.append("'-#-");
        j = i;
        localObject = localStringBuilder.toString();
        paramContext = (Context)localObject;
        continue;
        localObject = new StringBuilder();
      }
      catch (Throwable localThrowable2)
      {
        j = 0;
        paramContext = (Context)localObject;
      }
      label266:
      ((StringBuilder)localObject).append("TestForSuperSuPermissionOnManifest err: ");
      ((StringBuilder)localObject).append(localThrowable2);
      dx.k(((StringBuilder)localObject).toString());
      int k = j;
      localObject = paramContext;
      label305:
      if (k > 3) {
        i = 2;
      } else if (k > 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (k <= 0) {
        bool = false;
      }
      paramContext = new StringBuilder();
      paramContext.append("got root strings for ");
      paramContext.append(k);
      paramContext.append(" times, packages:");
      paramContext.append((String)localObject);
      paramA.a(bool, paramContext.toString(), i);
      return k;
      label389:
      int j = 0;
      continue;
      label394:
      j = 1;
    }
  }
  
  static Boolean a(a paramA)
  {
    Object localObject1 = "";
    Object localObject3;
    int j;
    boolean bool1;
    boolean bool2;
    try
    {
      localObject3 = a;
      j = localObject3.length;
      i = 0;
      while (i < j)
      {
        String str1 = localObject3[i];
        bool1 = new File(str1).canRead();
        if (bool1) {
          try
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("");
            ((StringBuilder)localObject1).append("R-");
            ((StringBuilder)localObject1).append(str1);
            ((StringBuilder)localObject1).append(";-#-");
            localObject1 = ((StringBuilder)localObject1).toString();
            bool2 = true;
          }
          catch (Throwable localThrowable1)
          {
            localObject1 = "";
            bool1 = false;
            bool2 = true;
            break label264;
          }
        }
        i += 1;
      }
      bool2 = false;
      try
      {
        localObject3 = b;
        j = localObject3.length;
        i = 0;
        while (i < j)
        {
          String str2 = localObject3[i];
          bool1 = new File(str2).canWrite();
          if (bool1) {
            try
            {
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append((String)localObject1);
              ((StringBuilder)localObject3).append("W-");
              ((StringBuilder)localObject3).append(str2);
              ((StringBuilder)localObject3).append(";-#-");
              str2 = ((StringBuilder)localObject3).toString();
              localObject1 = str2;
              bool3 = true;
            }
            catch (Throwable localThrowable2)
            {
              bool1 = true;
              break label264;
            }
          }
          i += 1;
        }
        bool3 = false;
      }
      catch (Throwable localThrowable3)
      {
        bool1 = false;
      }
      localObject3 = new StringBuilder();
    }
    catch (Throwable localThrowable4)
    {
      localObject1 = "";
      bool1 = false;
      bool2 = false;
    }
    label264:
    ((StringBuilder)localObject3).append("AreFilesMoreAccessibleThanNeeded: ");
    ((StringBuilder)localObject3).append(localThrowable4);
    dx.k(((StringBuilder)localObject3).toString());
    boolean bool3 = bool1;
    if ((!bool2) && (!bool3)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    Object localObject2 = localObject1;
    boolean bool4 = bool1;
    if (!bool1)
    {
      localObject3 = b();
      localObject2 = localObject1;
      bool4 = bool1;
      if (localObject3 != null)
      {
        int n = localObject3.length;
        localObject2 = localObject1;
        i = 0;
        localObject1 = localObject3;
        while (i < n)
        {
          localObject3 = localObject1[i].split(" ");
          if (localObject3.length >= 4)
          {
            Object localObject4 = localObject3[1];
            Object localObject5 = localObject3[3];
            String[] arrayOfString1 = com.scottyab.rootbeer.a.e;
            int i1 = arrayOfString1.length;
            j = 0;
            while (j < i1)
            {
              String str3 = arrayOfString1[j];
              localObject3 = localObject1;
              if (localObject4.equalsIgnoreCase(str3))
              {
                String[] arrayOfString2 = localObject5.split(",");
                int k = arrayOfString2.length;
                int m = 0;
                for (;;)
                {
                  localObject3 = localObject1;
                  if (m >= k) {
                    break;
                  }
                  localObject3 = arrayOfString2[m];
                  if (((String)localObject3).equalsIgnoreCase("rw"))
                  {
                    localObject3 = new StringBuilder();
                    ((StringBuilder)localObject3).append((String)localObject2);
                    ((StringBuilder)localObject3).append("RW-");
                    ((StringBuilder)localObject3).append(str3);
                    ((StringBuilder)localObject3).append(";-#-");
                    localObject2 = ((StringBuilder)localObject3).toString();
                    bool1 = true;
                    break label556;
                  }
                  m += 1;
                }
              }
              localObject1 = localObject3;
              label556:
              j += 1;
            }
          }
          i += 1;
        }
        bool4 = bool1;
      }
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("readable=");
    ((StringBuilder)localObject1).append(bool2);
    ((StringBuilder)localObject1).append("-#-writeable=");
    ((StringBuilder)localObject1).append(bool3);
    ((StringBuilder)localObject1).append("-#-paths=");
    ((StringBuilder)localObject1).append((String)localObject2);
    localObject1 = ((StringBuilder)localObject1).toString();
    if (bool4) {}
    for (int i = 2;; i = 0) {
      break;
    }
    paramA.a(bool4, (String)localObject1, i);
    return Boolean.valueOf(bool4);
  }
  
  static boolean a(a paramA, b paramB)
  {
    boolean bool5 = true;
    int i = 0;
    try
    {
      bool1 = System.getProperty("ro.secure", "1").equals("1");
    }
    catch (Exception localException1)
    {
      boolean bool1;
      for (;;) {}
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    bool1 = true;
    bool2 = false;
    break label65;
    bool1 = true;
    for (;;)
    {
      boolean bool3;
      boolean bool4;
      label65:
      try
      {
        bool3 = System.getProperty("ro.debuggable", "0").equals("1");
      }
      catch (Exception|Throwable localException2) {}
      try
      {
        bool4 = System.getProperty("“service.adb.root", "0").equals("1");
        bool2 = bool1;
      }
      catch (Exception|Throwable localException3)
      {
        bool2 = bool3;
      }
    }
    bool4 = false;
    bool3 = bool2;
    bool2 = bool1;
    bool1 = bool5;
    if (bool2) {
      if ((bool3) && (bool4)) {
        bool1 = bool5;
      } else {
        bool1 = false;
      }
    }
    if (!bool1) {}
    try
    {
      bool5 = paramB.a();
      bool1 = bool5;
    }
    catch (Throwable paramB)
    {
      for (;;) {}
    }
    paramB = new StringBuilder();
    paramB.append("got-#-secure=");
    paramB.append(bool2);
    paramB.append("-#-debuggable=");
    paramB.append(bool3);
    paramB.append("-#-adb_root=");
    paramB.append(bool4);
    paramB = paramB.toString();
    if (bool1) {
      i = 3;
    }
    paramA.a(bool1, paramB, i);
    return bool1;
  }
  
  private static boolean a(ArrayList<String> paramArrayList, String paramString)
  {
    if (paramArrayList == null) {
      return false;
    }
    return paramArrayList.contains(paramString);
  }
  
  private static boolean a(List<String> paramList, boolean paramBoolean)
  {
    boolean bool2 = false;
    if (paramList == null) {
      return false;
    }
    paramList = paramList.iterator();
    boolean bool1 = false;
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (str.contains("uid="))
      {
        if (paramBoolean)
        {
          paramBoolean = bool2;
          if (!str.contains("uid=0")) {}
        }
        else
        {
          paramBoolean = true;
        }
        return paramBoolean;
      }
      if (str.contains("-BOC-")) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  static int b(a paramA)
  {
    int m = 0;
    boolean bool = true;
    for (;;)
    {
      try
      {
        localObject = com.netspark.android.d.b.a.b(new String[] { "ps" });
        if (localObject == null) {
          break label151;
        }
        localObject = ((List)localObject).iterator();
        i = 0;
        k = i;
        j = i;
      }
      catch (Throwable paramA)
      {
        Object localObject;
        int i;
        continue;
      }
      try
      {
        if (!((Iterator)localObject).hasNext()) {
          break label153;
        }
        k = i;
        if (!((String)((Iterator)localObject).next()).contains("daemonsu")) {
          continue;
        }
        i += 1;
      }
      catch (Throwable paramA)
      {
        return k;
      }
      int k = j;
      localObject = new StringBuilder();
      k = j;
      ((StringBuilder)localObject).append("got NumberDaemonsuRunning= ");
      k = j;
      ((StringBuilder)localObject).append(j);
      k = j;
      localObject = ((StringBuilder)localObject).toString();
      i = m;
      if (bool) {
        i = 3;
      }
      k = j;
      paramA.a(bool, (String)localObject, i);
      return j;
      return 0;
      label151:
      int j = 0;
      label153:
      if (j <= 0) {
        bool = false;
      }
    }
  }
  
  /* Error */
  public static boolean b(a paramA, b paramB)
  {
    // Byte code:
    //   0: invokestatic 274	com/netspark/android/d/a/f:c	()V
    //   3: getstatic 212	com/netspark/android/d/a/f:e	Ljava/util/List;
    //   6: astore 5
    //   8: iconst_0
    //   9: istore_2
    //   10: aload 5
    //   12: iconst_0
    //   13: invokestatic 276	com/netspark/android/d/a/f:a	(Ljava/util/List;Z)Z
    //   16: istore 4
    //   18: iload 4
    //   20: istore_3
    //   21: iload 4
    //   23: ifne +122 -> 145
    //   26: aconst_null
    //   27: astore 7
    //   29: aconst_null
    //   30: astore 6
    //   32: invokestatic 282	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   35: astore 5
    //   37: iconst_1
    //   38: istore_3
    //   39: aload 5
    //   41: iconst_2
    //   42: anewarray 17	java/lang/String
    //   45: dup
    //   46: iconst_0
    //   47: ldc_w 284
    //   50: aastore
    //   51: dup
    //   52: iconst_1
    //   53: ldc_w 286
    //   56: aastore
    //   57: invokevirtual 290	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   60: astore 5
    //   62: new 292	java/io/BufferedReader
    //   65: dup
    //   66: new 294	java/io/InputStreamReader
    //   69: dup
    //   70: aload 5
    //   72: invokevirtual 300	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   75: invokespecial 303	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   78: invokespecial 306	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   81: invokevirtual 309	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   84: astore 6
    //   86: aload 6
    //   88: ifnull +6 -> 94
    //   91: goto +5 -> 96
    //   94: iconst_0
    //   95: istore_3
    //   96: aload 5
    //   98: ifnull +8 -> 106
    //   101: aload 5
    //   103: invokevirtual 312	java/lang/Process:destroy	()V
    //   106: goto +39 -> 145
    //   109: astore_1
    //   110: aload 5
    //   112: astore_0
    //   113: goto +10 -> 123
    //   116: goto +17 -> 133
    //   119: astore_1
    //   120: aload 6
    //   122: astore_0
    //   123: aload_0
    //   124: ifnull +7 -> 131
    //   127: aload_0
    //   128: invokevirtual 312	java/lang/Process:destroy	()V
    //   131: aload_1
    //   132: athrow
    //   133: aload 5
    //   135: ifnull +8 -> 143
    //   138: aload 5
    //   140: invokevirtual 312	java/lang/Process:destroy	()V
    //   143: iconst_0
    //   144: istore_3
    //   145: iload_3
    //   146: ifne +49 -> 195
    //   149: aload_1
    //   150: invokevirtual 314	com/scottyab/rootbeer/b:c	()Z
    //   153: istore 4
    //   155: iload 4
    //   157: istore_3
    //   158: goto +37 -> 195
    //   161: astore_1
    //   162: new 117	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   169: astore 5
    //   171: aload 5
    //   173: ldc_w 316
    //   176: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: pop
    //   180: aload 5
    //   182: aload_1
    //   183: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload 5
    //   189: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: invokestatic 140	com/netspark/android/netsvpn/dx:k	(Ljava/lang/String;)V
    //   195: iload_3
    //   196: ifeq +5 -> 201
    //   199: iconst_2
    //   200: istore_2
    //   201: aload_0
    //   202: iload_3
    //   203: ldc 51
    //   205: iload_2
    //   206: invokevirtual 152	com/netspark/android/d/a/a:a	(ZLjava/lang/String;I)V
    //   209: iload_3
    //   210: ireturn
    //   211: astore 5
    //   213: aload 7
    //   215: astore 5
    //   217: goto -84 -> 133
    //   220: astore 6
    //   222: goto -106 -> 116
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	paramA	a
    //   0	225	1	paramB	b
    //   9	197	2	i	int
    //   20	190	3	bool1	boolean
    //   16	140	4	bool2	boolean
    //   6	182	5	localObject1	Object
    //   211	1	5	localThrowable1	Throwable
    //   215	1	5	localObject2	Object
    //   30	91	6	str	String
    //   220	1	6	localThrowable2	Throwable
    //   27	187	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   62	86	109	finally
    //   32	37	119	finally
    //   39	62	119	finally
    //   149	155	161	java/lang/Throwable
    //   32	37	211	java/lang/Throwable
    //   39	62	211	java/lang/Throwable
    //   62	86	220	java/lang/Throwable
  }
  
  private static String[] b()
  {
    Object localObject1;
    try
    {
      InputStream localInputStream = Runtime.getRuntime().exec("mount").getInputStream();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      localObject1 = null;
    }
    if (localObject1 == null) {
      return null;
    }
    String str = "";
    Object localObject2;
    try
    {
      localObject1 = new Scanner((InputStream)localObject1).useDelimiter("\\A").next();
    }
    catch (NoSuchElementException localNoSuchElementException)
    {
      localNoSuchElementException.printStackTrace();
      localObject2 = str;
    }
    return localObject2.split("\n");
  }
  
  private static void c()
  {
    if (SystemClock.elapsedRealtime() - d < 300000L) {
      return;
    }
    f(new a("TimeToGetAnswer"));
  }
  
  static boolean c(a paramA)
  {
    for (;;)
    {
      try
      {
        bool = Build.TAGS.equals("release-keys");
        int i = 0;
        if (!bool)
        {
          bool = true;
          Object localObject = new StringBuilder();
          ((StringBuilder)localObject).append("got build-tags: '");
          ((StringBuilder)localObject).append(Build.TAGS);
          ((StringBuilder)localObject).append("'");
          localObject = ((StringBuilder)localObject).toString();
          if (bool) {
            i = 1;
          }
          paramA.a(bool, (String)localObject, i);
          return bool;
        }
      }
      catch (Throwable paramA)
      {
        return true;
      }
      boolean bool = false;
    }
  }
  
  private static void d()
  {
    e = null;
    d = SystemClock.elapsedRealtime();
  }
  
  static boolean d(a paramA)
  {
    int i;
    boolean bool;
    for (;;)
    {
      int i1;
      int i2;
      int i5;
      int i6;
      try
      {
        Object localObject3 = new ArrayList();
        localObject1 = com.netspark.android.d.b.a.b(new String[] { "pm list packages" });
        if (localObject1 != null)
        {
          localObject1 = ((List)localObject1).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            ((ArrayList)localObject3).add(((String)((Iterator)localObject1).next()).trim().replace("package:", ""));
            continue;
          }
        }
        if (((ArrayList)localObject3).size() < 10)
        {
          ((ArrayList)localObject3).clear();
          localObject1 = dx.j().getInstalledPackages(al.a(0)).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            ((ArrayList)localObject3).add(((PackageInfo)((Iterator)localObject1).next()).packageName);
            continue;
          }
        }
        localObject2 = new ArrayList();
        ((ArrayList)localObject2).add("com.kingoapp.apk");
        ((ArrayList)localObject2).add("com.smedialink.oneclickroot");
        ((ArrayList)localObject2).add("com.hexamob.howtoroot");
        ArrayList localArrayList1 = new ArrayList();
        localArrayList1.add("eu.chainfire.supersu");
        localArrayList1.add("com.noshufou.android.su");
        localArrayList1.add("com.koushikdutta.superuser");
        localArrayList1.add("me.phh.superuser");
        localArrayList1.add("com.yellowes.su");
        localArrayList1.add("com.noshufou.android.su");
        localArrayList1.add("com.noshufou.android.su.elite");
        localArrayList1.add("com.thirdparty.superuser");
        localArrayList1.add("com.koushikdutta.rommanager");
        localArrayList1.add("com.koushikdutta.rommanager.license");
        localArrayList1.add("com.jrummy.apps.rom.installer");
        localArrayList1.add("com.cyanogenmod1.rom");
        localArrayList1.add("org.elegosproject.romupdater");
        localArrayList1.add("com.ramdroid.appquarantine");
        localArrayList1.add("com.ramdroid.appquarantinepro");
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add("com.ghisler.android.TotalCommander");
        localArrayList2.add("com.jumobile.manager.systemapp");
        ArrayList localArrayList3 = new ArrayList();
        localArrayList3.add("com.devadvance.rootcloak");
        localArrayList3.add("com.devadvance.rootcloakplus");
        localArrayList3.add("com.amphoras.hidemyroot");
        localArrayList3.add("com.amphoras.hidemyrootadfree");
        localArrayList3.add("com.formyhm.hideroot");
        localArrayList3.add("com.formyhm.hiderootPremium");
        localArrayList3.add("de.robv.android.xposed.installer");
        localArrayList3.add("com.saurik.substrate");
        localArrayList3.add("com.zachspong.temprootremovejb");
        ArrayList localArrayList4 = new ArrayList();
        localArrayList4.add("com.joeykrim.rootcheck");
        localArrayList4.add("com.jrummyapps.rootchecker");
        localArrayList4.add("org.freeandroidtools.root_checker");
        localArrayList4.add("com.dimonvideo.luckypatcher");
        localArrayList4.add("com.chelpus.lackypatch");
        localObject1 = new HashSet();
        localObject3 = ((ArrayList)localObject3).iterator();
        i4 = 0;
        i3 = 0;
        k = 0;
        j = 0;
        i = 0;
        m = 0;
        if (!((Iterator)localObject3).hasNext()) {
          break label1101;
        }
        String str = (String)((Iterator)localObject3).next();
        n = i4;
        if (a((ArrayList)localObject2, str))
        {
          n = i4 + 1;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("exploit:");
          localStringBuilder.append(str);
          ((HashSet)localObject1).add(localStringBuilder.toString());
        }
        i1 = i3;
        i2 = m;
        if (a(localArrayList1, str))
        {
          i1 = i3 + 1;
          if (com.netspark.android.d.b.a.b(str))
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("managSys:");
            localStringBuilder.append(str);
            ((HashSet)localObject1).add(localStringBuilder.toString());
            i2 = 1;
          }
          else
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("manage:");
            localStringBuilder.append(str);
            ((HashSet)localObject1).add(localStringBuilder.toString());
            i2 = m;
          }
        }
        m = k;
        if (a(localArrayList2, str))
        {
          m = k + 1;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("using:");
          localStringBuilder.append(str);
          ((HashSet)localObject1).add(localStringBuilder.toString());
        }
        i5 = j;
        if (a(localArrayList3, str))
        {
          i5 = j + 1;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("cheating:");
          localStringBuilder.append(str);
          ((HashSet)localObject1).add(localStringBuilder.toString());
        }
        i6 = i;
        if (!a(localArrayList4, str)) {
          break label1077;
        }
        i6 = i + 1;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("misc:");
        localStringBuilder.append(str);
        ((HashSet)localObject1).add(localStringBuilder.toString());
      }
      catch (Throwable paramA)
      {
        Object localObject2;
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("DetectAndroidRootiByPackages ");
        ((StringBuilder)localObject1).append(paramA);
        dx.k(((StringBuilder)localObject1).toString());
        return false;
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("got DetectAndroidRootiByPackages counters:-#-exploit=");
      ((StringBuilder)localObject2).append(i4);
      ((StringBuilder)localObject2).append("-#-manage=");
      ((StringBuilder)localObject2).append(i3);
      ((StringBuilder)localObject2).append("-#-using root=");
      ((StringBuilder)localObject2).append(k);
      ((StringBuilder)localObject2).append("-#-cheating=");
      ((StringBuilder)localObject2).append(j);
      ((StringBuilder)localObject2).append("-#-other=");
      ((StringBuilder)localObject2).append(i);
      ((StringBuilder)localObject2).append("-#--#-total score=");
      ((StringBuilder)localObject2).append(n);
      ((StringBuilder)localObject2).append("-#-result:");
      ((StringBuilder)localObject2).append(localObject1);
      localObject1 = ((StringBuilder)localObject2).toString();
      if (m == 0) {
        break label1149;
      }
      i = 3;
      break;
      paramA.a(bool, (String)localObject1, i);
      return bool;
      label1077:
      int i4 = n;
      int i3 = i1;
      int k = m;
      int j = i5;
      i = i6;
      int m = i2;
      continue;
      label1101:
      int n = i4 * 10 + i3 * 10 + k * 4 + j * 10 + i * 2;
      if (n >= 10) {
        bool = true;
      } else {
        bool = false;
      }
    }
    for (;;)
    {
      break;
      label1149:
      if (bool) {
        i = 2;
      } else {
        i = 0;
      }
    }
  }
  
  public static boolean e(a paramA)
  {
    c();
    boolean bool = a(e, true);
    int i;
    if (bool) {
      i = 4;
    } else {
      i = 0;
    }
    paramA.a(bool, "", i);
    return bool;
  }
  
  /* Error */
  static boolean f(a paramA)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: new 500	java/util/concurrent/Semaphore
    //   5: dup
    //   6: iconst_1
    //   7: invokespecial 503	java/util/concurrent/Semaphore:<init>	(I)V
    //   10: astore 6
    //   12: invokestatic 505	com/netspark/android/d/a/f:d	()V
    //   15: new 507	java/util/Timer
    //   18: dup
    //   19: invokespecial 508	java/util/Timer:<init>	()V
    //   22: astore 7
    //   24: aload 7
    //   26: new 510	com/netspark/android/d/a/g
    //   29: dup
    //   30: aload 6
    //   32: aload_0
    //   33: invokespecial 513	com/netspark/android/d/a/g:<init>	(Ljava/util/concurrent/Semaphore;Lcom/netspark/android/d/a/a;)V
    //   36: ldc2_w 514
    //   39: invokevirtual 519	java/util/Timer:schedule	(Ljava/util/TimerTask;J)V
    //   42: invokestatic 348	android/os/SystemClock:elapsedRealtime	()J
    //   45: lstore 4
    //   47: new 521	java/lang/Thread
    //   50: dup
    //   51: new 523	com/netspark/android/d/a/h
    //   54: dup
    //   55: invokespecial 524	com/netspark/android/d/a/h:<init>	()V
    //   58: invokespecial 527	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   61: astore 8
    //   63: aload 8
    //   65: invokevirtual 530	java/lang/Thread:start	()V
    //   68: aload 8
    //   70: ldc2_w 531
    //   73: invokevirtual 536	java/lang/Thread:join	(J)V
    //   76: aload 6
    //   78: invokevirtual 539	java/util/concurrent/Semaphore:acquire	()V
    //   81: aload 7
    //   83: invokevirtual 542	java/util/Timer:cancel	()V
    //   86: invokestatic 348	android/os/SystemClock:elapsedRealtime	()J
    //   89: lload 4
    //   91: lsub
    //   92: lstore 4
    //   94: lload 4
    //   96: ldc2_w 531
    //   99: lcmp
    //   100: iflt +132 -> 232
    //   103: getstatic 212	com/netspark/android/d/a/f:e	Ljava/util/List;
    //   106: ifnonnull +126 -> 232
    //   109: iconst_0
    //   110: putstatic 545	com/netspark/android/d/b/a:a	Z
    //   113: goto +119 -> 232
    //   116: new 117	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   123: astore 7
    //   125: aload 7
    //   127: ldc_w 547
    //   130: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: pop
    //   134: aload 7
    //   136: lload 4
    //   138: invokevirtual 550	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   141: pop
    //   142: aload 7
    //   144: ldc_w 552
    //   147: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: aload 7
    //   153: getstatic 212	com/netspark/android/d/a/f:e	Ljava/util/List;
    //   156: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload 7
    //   162: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   165: astore 7
    //   167: aload_0
    //   168: getfield 555	com/netspark/android/d/a/a:a	I
    //   171: iconst_1
    //   172: if_icmpge +24 -> 196
    //   175: lload 4
    //   177: lconst_0
    //   178: lcmp
    //   179: ifle +72 -> 251
    //   182: goto +71 -> 253
    //   185: aload_0
    //   186: iload_3
    //   187: aload 7
    //   189: iload_1
    //   190: invokevirtual 152	com/netspark/android/d/a/a:a	(ZLjava/lang/String;I)V
    //   193: goto +9 -> 202
    //   196: aload_0
    //   197: aload 7
    //   199: putfield 557	com/netspark/android/d/a/a:e	Ljava/lang/String;
    //   202: aload 6
    //   204: invokevirtual 560	java/util/concurrent/Semaphore:release	()V
    //   207: iload_2
    //   208: ireturn
    //   209: astore_0
    //   210: goto +15 -> 225
    //   213: astore_0
    //   214: aload_0
    //   215: invokevirtual 561	java/lang/Throwable:printStackTrace	()V
    //   218: aload 6
    //   220: invokevirtual 560	java/util/concurrent/Semaphore:release	()V
    //   223: iconst_0
    //   224: ireturn
    //   225: aload 6
    //   227: invokevirtual 560	java/util/concurrent/Semaphore:release	()V
    //   230: aload_0
    //   231: athrow
    //   232: lload 4
    //   234: ldc2_w 562
    //   237: lcmp
    //   238: ifle +8 -> 246
    //   241: iconst_1
    //   242: istore_2
    //   243: goto -127 -> 116
    //   246: iconst_0
    //   247: istore_2
    //   248: goto -132 -> 116
    //   251: iconst_0
    //   252: istore_3
    //   253: iload_2
    //   254: ifeq +8 -> 262
    //   257: iconst_2
    //   258: istore_1
    //   259: goto -74 -> 185
    //   262: iconst_0
    //   263: istore_1
    //   264: goto -79 -> 185
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	267	0	paramA	a
    //   189	75	1	i	int
    //   207	47	2	bool1	boolean
    //   1	252	3	bool2	boolean
    //   45	188	4	l	long
    //   10	216	6	localSemaphore	java.util.concurrent.Semaphore
    //   22	176	7	localObject	Object
    //   61	8	8	localThread	Thread
    // Exception table:
    //   from	to	target	type
    //   12	94	209	finally
    //   103	113	209	finally
    //   116	175	209	finally
    //   185	193	209	finally
    //   196	202	209	finally
    //   214	218	209	finally
    //   12	94	213	java/lang/Throwable
    //   103	113	213	java/lang/Throwable
    //   116	175	213	java/lang/Throwable
    //   185	193	213	java/lang/Throwable
    //   196	202	213	java/lang/Throwable
  }
}
