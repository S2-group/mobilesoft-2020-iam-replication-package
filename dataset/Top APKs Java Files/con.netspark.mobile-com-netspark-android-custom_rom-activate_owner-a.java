package com.netspark.android.custom_rom.activate_owner;

import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.netspark.android.apps.al;
import com.netspark.android.e.d;
import com.netspark.android.netsvpn.LogSender;
import com.netspark.android.netsvpn.NetSparkApplication;
import com.netspark.android.netsvpn.SetAdmin.MyAdmin;
import com.netspark.android.netsvpn.be;
import com.netspark.android.netsvpn.bh;
import com.netspark.android.netsvpn.dx;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;

public class a
  extends com.netspark.android.custom_rom.manufacturers.a
{
  private static int g = -1;
  private final Object h = new Object();
  
  public a() {}
  
  private IntentSender C()
  {
    return PendingIntent.getBroadcast(NetSparkApplication.a, 1337111117, new Intent(NetSparkApplication.a, LogSender.class), 134217728).getIntentSender();
  }
  
  private static boolean D()
  {
    return d.a(com.netspark.android.e.b.F);
  }
  
  private static void E()
  {
    DevicePolicyManager localDevicePolicyManager = SetAdmin.MyAdmin.k();
    Set localSet = com.netspark.android.custom_rom.manufacturers.a.p();
    a(localSet, false);
    SystemClock.sleep(100L);
    Iterator localIterator = localSet.iterator();
    for (;;)
    {
      String str;
      if (localIterator.hasNext()) {
        str = (String)localIterator.next();
      }
      try
      {
        if (localDevicePolicyManager.isUninstallBlocked(SetAdmin.MyAdmin.j(), str)) {
          continue;
        }
        localSet.remove(str);
      }
      catch (Throwable localThrowable) {}
      com.netspark.android.custom_rom.manufacturers.a.o();
      return;
    }
  }
  
  private List<PackageInfo> F()
  {
    return dx.j().getInstalledPackages(al.a(0));
  }
  
  private ArrayList<String> G()
  {
    ArrayList localArrayList = new ArrayList();
    if (NetSparkApplication.v)
    {
      Iterator localIterator = F().iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (SetAdmin.MyAdmin.k().isApplicationHidden(SetAdmin.MyAdmin.j(), localPackageInfo.packageName)) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
    }
    return localArrayList;
  }
  
  private ArrayList<String> H()
  {
    ArrayList localArrayList = new ArrayList();
    if (NetSparkApplication.t)
    {
      Iterator localIterator = F().iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        try
        {
          if (SetAdmin.MyAdmin.k().isPackageSuspended(SetAdmin.MyAdmin.j(), localPackageInfo.packageName)) {
            localArrayList.add(localPackageInfo.packageName);
          }
        }
        catch (Throwable localThrowable)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("On 'getCurrnetlySuspendedPackages' ");
          localStringBuilder.append(localThrowable);
          dx.k(localStringBuilder.toString());
        }
      }
    }
    return localArrayList;
  }
  
  /* Error */
  private void I()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 145	com/netspark/android/netsvpn/NetSparkApplication:v	Z
    //   5: ifeq +336 -> 341
    //   8: aload_0
    //   9: getstatic 54	com/netspark/android/e/b:F	Lcom/netspark/android/e/c;
    //   12: getstatic 192	com/netspark/android/e/b:J	Lcom/netspark/android/e/c;
    //   15: invokevirtual 196	com/netspark/android/custom_rom/activate_owner/a:b	(Lcom/netspark/android/e/c;Lcom/netspark/android/e/c;)I
    //   18: istore_2
    //   19: iload_2
    //   20: ifle +14 -> 34
    //   23: getstatic 202	com/netspark/android/apps/AppsDetector:f	Lcom/netspark/android/apps/al;
    //   26: invokevirtual 205	com/netspark/android/apps/al:c	()Ljava/util/ArrayList;
    //   29: astore 4
    //   31: goto +12 -> 43
    //   34: new 140	java/util/ArrayList
    //   37: dup
    //   38: invokespecial 141	java/util/ArrayList:<init>	()V
    //   41: astore 4
    //   43: new 172	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   50: astore 5
    //   52: aload 5
    //   54: ldc -49
    //   56: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload 5
    //   62: aload 4
    //   64: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload 5
    //   70: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: invokestatic 189	com/netspark/android/netsvpn/dx:k	(Ljava/lang/String;)V
    //   76: iload_2
    //   77: istore_1
    //   78: iload_2
    //   79: iconst_1
    //   80: if_icmpne +16 -> 96
    //   83: iload_2
    //   84: istore_1
    //   85: getstatic 167	com/netspark/android/netsvpn/NetSparkApplication:t	Z
    //   88: ifne +8 -> 96
    //   91: iconst_2
    //   92: istore_1
    //   93: goto +3 -> 96
    //   96: iload_1
    //   97: istore_2
    //   98: aload_0
    //   99: invokespecial 209	com/netspark/android/custom_rom/activate_owner/a:G	()Ljava/util/ArrayList;
    //   102: astore 6
    //   104: iload_1
    //   105: iconst_2
    //   106: if_icmpne +46 -> 152
    //   109: iload_1
    //   110: istore_2
    //   111: aload 4
    //   113: aload 6
    //   115: invokestatic 212	com/netspark/android/netsvpn/dx:a	(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;
    //   118: astore 5
    //   120: iload_1
    //   121: istore_2
    //   122: aload 6
    //   124: aload 4
    //   126: invokestatic 212	com/netspark/android/netsvpn/dx:a	(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;
    //   129: astore 6
    //   131: iload_1
    //   132: istore_2
    //   133: aload_0
    //   134: aload 5
    //   136: iconst_1
    //   137: invokespecial 215	com/netspark/android/custom_rom/activate_owner/a:a	(Ljava/util/ArrayList;Z)V
    //   140: iload_1
    //   141: istore_2
    //   142: aload_0
    //   143: aload 6
    //   145: iconst_0
    //   146: invokespecial 215	com/netspark/android/custom_rom/activate_owner/a:a	(Ljava/util/ArrayList;Z)V
    //   149: goto +50 -> 199
    //   152: iload_1
    //   153: istore_2
    //   154: aload_0
    //   155: aload 6
    //   157: iconst_0
    //   158: invokespecial 215	com/netspark/android/custom_rom/activate_owner/a:a	(Ljava/util/ArrayList;Z)V
    //   161: goto +38 -> 199
    //   164: new 172	java/lang/StringBuilder
    //   167: dup
    //   168: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   171: astore 5
    //   173: aload 5
    //   175: ldc -39
    //   177: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: aload 5
    //   183: aload 6
    //   185: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   188: pop
    //   189: aload 5
    //   191: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: invokestatic 189	com/netspark/android/netsvpn/dx:k	(Ljava/lang/String;)V
    //   197: iload_2
    //   198: istore_1
    //   199: getstatic 167	com/netspark/android/netsvpn/NetSparkApplication:t	Z
    //   202: istore_3
    //   203: iload_3
    //   204: ifeq +137 -> 341
    //   207: aload_0
    //   208: invokespecial 219	com/netspark/android/custom_rom/activate_owner/a:H	()Ljava/util/ArrayList;
    //   211: astore 6
    //   213: iload_1
    //   214: iconst_1
    //   215: if_icmpne +38 -> 253
    //   218: aload 4
    //   220: aload 6
    //   222: invokestatic 212	com/netspark/android/netsvpn/dx:a	(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;
    //   225: astore 5
    //   227: aload 6
    //   229: aload 4
    //   231: invokestatic 212	com/netspark/android/netsvpn/dx:a	(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;
    //   234: astore 4
    //   236: aload_0
    //   237: aload 5
    //   239: iconst_1
    //   240: invokespecial 221	com/netspark/android/custom_rom/activate_owner/a:b	(Ljava/util/ArrayList;Z)V
    //   243: aload_0
    //   244: aload 4
    //   246: iconst_0
    //   247: invokespecial 221	com/netspark/android/custom_rom/activate_owner/a:b	(Ljava/util/ArrayList;Z)V
    //   250: goto +91 -> 341
    //   253: aload_0
    //   254: aload 6
    //   256: iconst_0
    //   257: invokespecial 221	com/netspark/android/custom_rom/activate_owner/a:b	(Ljava/util/ArrayList;Z)V
    //   260: goto +81 -> 341
    //   263: astore 4
    //   265: new 172	java/lang/StringBuilder
    //   268: dup
    //   269: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   272: astore 5
    //   274: aload 5
    //   276: ldc -39
    //   278: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: pop
    //   282: aload 5
    //   284: aload 4
    //   286: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   289: pop
    //   290: aload 5
    //   292: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   295: invokestatic 189	com/netspark/android/netsvpn/dx:k	(Ljava/lang/String;)V
    //   298: goto +43 -> 341
    //   301: astore 4
    //   303: goto +41 -> 344
    //   306: astore 4
    //   308: new 172	java/lang/StringBuilder
    //   311: dup
    //   312: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   315: astore 5
    //   317: aload 5
    //   319: ldc -39
    //   321: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: pop
    //   325: aload 5
    //   327: aload 4
    //   329: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   332: pop
    //   333: aload 5
    //   335: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   338: invokestatic 189	com/netspark/android/netsvpn/dx:k	(Ljava/lang/String;)V
    //   341: aload_0
    //   342: monitorexit
    //   343: return
    //   344: aload_0
    //   345: monitorexit
    //   346: aload 4
    //   348: athrow
    //   349: astore 6
    //   351: goto -187 -> 164
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	354	0	this	a
    //   77	139	1	i	int
    //   18	180	2	j	int
    //   202	2	3	bool	boolean
    //   29	216	4	localArrayList1	ArrayList
    //   263	22	4	localThrowable1	Throwable
    //   301	1	4	localObject1	Object
    //   306	41	4	localThrowable2	Throwable
    //   50	284	5	localObject2	Object
    //   102	153	6	localArrayList2	ArrayList
    //   349	1	6	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   207	213	263	java/lang/Throwable
    //   218	250	263	java/lang/Throwable
    //   253	260	263	java/lang/Throwable
    //   2	19	301	finally
    //   23	31	301	finally
    //   34	43	301	finally
    //   43	76	301	finally
    //   85	91	301	finally
    //   98	104	301	finally
    //   111	120	301	finally
    //   122	131	301	finally
    //   133	140	301	finally
    //   142	149	301	finally
    //   154	161	301	finally
    //   164	197	301	finally
    //   199	203	301	finally
    //   207	213	301	finally
    //   218	250	301	finally
    //   253	260	301	finally
    //   265	298	301	finally
    //   308	341	301	finally
    //   2	19	306	java/lang/Throwable
    //   23	31	306	java/lang/Throwable
    //   34	43	306	java/lang/Throwable
    //   43	76	306	java/lang/Throwable
    //   164	197	306	java/lang/Throwable
    //   199	203	306	java/lang/Throwable
    //   265	298	306	java/lang/Throwable
    //   85	91	349	java/lang/Throwable
    //   98	104	349	java/lang/Throwable
    //   111	120	349	java/lang/Throwable
    //   122	131	349	java/lang/Throwable
    //   133	140	349	java/lang/Throwable
    //   142	149	349	java/lang/Throwable
    //   154	161	349	java/lang/Throwable
  }
  
  private void J()
  {
    dx.e("OwnerFeaturesManager", "ApllyAllRuntimePermissions");
    try
    {
      a(NetSparkApplication.e(), true, Arrays.asList(n.a()));
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private void a(ArrayList<String> paramArrayList, boolean paramBoolean)
  {
    if ((NetSparkApplication.v) && (paramArrayList != null) && (!paramArrayList.isEmpty()))
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        String str = (String)paramArrayList.next();
        SetAdmin.MyAdmin.k().setApplicationHidden(SetAdmin.MyAdmin.j(), str, paramBoolean);
      }
    }
  }
  
  private static void a(Collection<String> paramCollection, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("applyUninstallationBlock - on apps list: ");
        ((StringBuilder)localObject).append(paramCollection);
        ((StringBuilder)localObject).append(", uninstallationBlock: ");
        ((StringBuilder)localObject).append(paramBoolean);
        dx.a("OwnerFeaturesManager", ((StringBuilder)localObject).toString(), false);
        localObject = SetAdmin.MyAdmin.k();
        paramCollection = paramCollection.iterator();
        if (paramCollection.hasNext()) {
          str = (String)paramCollection.next();
        }
      }
      catch (Throwable paramCollection)
      {
        Object localObject;
        String str;
        return;
      }
      try
      {
        ((DevicePolicyManager)localObject).setUninstallBlocked(SetAdmin.MyAdmin.j(), str, paramBoolean);
      }
      catch (Throwable localThrowable) {}
      return;
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        a(m(), paramBoolean);
        E();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error on SetUninstallationBlocked: ");
      localStringBuilder.append(localThrowable);
      dx.f("OwnerFeaturesManager", localStringBuilder.toString());
    }
  }
  
  private void b(ArrayList<String> paramArrayList, boolean paramBoolean)
  {
    if ((NetSparkApplication.t) && (paramArrayList != null) && (!paramArrayList.isEmpty())) {
      SetAdmin.MyAdmin.k().setPackagesSuspended(SetAdmin.MyAdmin.j(), (String[])paramArrayList.toArray(new String[paramArrayList.size()]), paramBoolean);
    }
  }
  
  public static void c()
  {
    List localList = dx.j().getInstalledApplications(8192);
    int j = localList.size();
    int i = 0;
    for (;;)
    {
      if (i < j) {}
      try
      {
        SetAdmin.MyAdmin.k().enableSystemApp(SetAdmin.MyAdmin.j(), ((ApplicationInfo)localList.get(i)).packageName);
        i += 1;
        continue;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  private static void c(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      SetAdmin.MyAdmin.k().addUserRestriction(SetAdmin.MyAdmin.j(), paramString);
      return;
    }
    catch (Exception paramString) {}
    SetAdmin.MyAdmin.k().clearUserRestriction(SetAdmin.MyAdmin.j(), paramString);
    return;
  }
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  private void e(String paramString)
  {
    // Byte code:
    //   0: invokestatic 123	com/netspark/android/netsvpn/dx:j	()Landroid/content/pm/PackageManager;
    //   3: invokevirtual 345	android/content/pm/PackageManager:getPackageInstaller	()Landroid/content/pm/PackageInstaller;
    //   6: astore 5
    //   8: aload 5
    //   10: aload 5
    //   12: new 347	android/content/pm/PackageInstaller$SessionParams
    //   15: dup
    //   16: iconst_1
    //   17: invokespecial 350	android/content/pm/PackageInstaller$SessionParams:<init>	(I)V
    //   20: invokevirtual 356	android/content/pm/PackageInstaller:createSession	(Landroid/content/pm/PackageInstaller$SessionParams;)I
    //   23: invokevirtual 360	android/content/pm/PackageInstaller:openSession	(I)Landroid/content/pm/PackageInstaller$Session;
    //   26: astore 9
    //   28: new 362	java/io/File
    //   31: dup
    //   32: aload_1
    //   33: invokespecial 364	java/io/File:<init>	(Ljava/lang/String;)V
    //   36: astore 5
    //   38: aload 5
    //   40: invokevirtual 367	java/io/File:isFile	()Z
    //   43: ifeq +229 -> 272
    //   46: aload 5
    //   48: invokevirtual 370	java/io/File:exists	()Z
    //   51: ifeq +221 -> 272
    //   54: aload 5
    //   56: invokevirtual 374	java/io/File:length	()J
    //   59: lstore_3
    //   60: aconst_null
    //   61: astore 8
    //   63: aconst_null
    //   64: astore 7
    //   66: new 376	java/io/FileInputStream
    //   69: dup
    //   70: aload_1
    //   71: invokespecial 377	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   74: astore 5
    //   76: aload 9
    //   78: ldc_w 379
    //   81: lconst_0
    //   82: lload_3
    //   83: invokevirtual 385	android/content/pm/PackageInstaller$Session:openWrite	(Ljava/lang/String;JJ)Ljava/io/OutputStream;
    //   86: astore_1
    //   87: ldc_w 386
    //   90: newarray byte
    //   92: astore 6
    //   94: aload 5
    //   96: aload 6
    //   98: invokevirtual 392	java/io/InputStream:read	([B)I
    //   101: istore_2
    //   102: iload_2
    //   103: iconst_m1
    //   104: if_icmpeq +14 -> 118
    //   107: aload_1
    //   108: aload 6
    //   110: iconst_0
    //   111: iload_2
    //   112: invokevirtual 398	java/io/OutputStream:write	([BII)V
    //   115: goto -21 -> 94
    //   118: aload 9
    //   120: aload_1
    //   121: invokevirtual 402	android/content/pm/PackageInstaller$Session:fsync	(Ljava/io/OutputStream;)V
    //   124: aload 5
    //   126: invokevirtual 405	java/io/InputStream:close	()V
    //   129: aload_1
    //   130: ifnull +106 -> 236
    //   133: aload_1
    //   134: invokevirtual 406	java/io/OutputStream:close	()V
    //   137: goto +99 -> 236
    //   140: astore 6
    //   142: goto +12 -> 154
    //   145: astore 6
    //   147: goto +14 -> 161
    //   150: astore 6
    //   152: aconst_null
    //   153: astore_1
    //   154: goto +97 -> 251
    //   157: astore 6
    //   159: aconst_null
    //   160: astore_1
    //   161: goto +22 -> 183
    //   164: astore 6
    //   166: aconst_null
    //   167: astore_1
    //   168: aload 8
    //   170: astore 5
    //   172: goto +79 -> 251
    //   175: astore 6
    //   177: aconst_null
    //   178: astore_1
    //   179: aload 7
    //   181: astore 5
    //   183: new 172	java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   190: astore 7
    //   192: aload 7
    //   194: ldc_w 408
    //   197: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: pop
    //   201: aload 7
    //   203: aload 6
    //   205: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: iconst_1
    //   210: aload 7
    //   212: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: invokestatic 413	com/netspark/android/netsvpn/bh:a	(ZLjava/lang/String;)V
    //   218: aload 5
    //   220: ifnull +8 -> 228
    //   223: aload 5
    //   225: invokevirtual 405	java/io/InputStream:close	()V
    //   228: aload_1
    //   229: ifnull +7 -> 236
    //   232: aload_1
    //   233: invokevirtual 406	java/io/OutputStream:close	()V
    //   236: aload 9
    //   238: aload_0
    //   239: invokespecial 415	com/netspark/android/custom_rom/activate_owner/a:C	()Landroid/content/IntentSender;
    //   242: invokevirtual 419	android/content/pm/PackageInstaller$Session:commit	(Landroid/content/IntentSender;)V
    //   245: aload 9
    //   247: invokevirtual 420	android/content/pm/PackageInstaller$Session:close	()V
    //   250: return
    //   251: aload 5
    //   253: ifnull +8 -> 261
    //   256: aload 5
    //   258: invokevirtual 405	java/io/InputStream:close	()V
    //   261: aload_1
    //   262: ifnull +7 -> 269
    //   265: aload_1
    //   266: invokevirtual 406	java/io/OutputStream:close	()V
    //   269: aload 6
    //   271: athrow
    //   272: new 172	java/lang/StringBuilder
    //   275: dup
    //   276: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   279: astore_1
    //   280: aload_1
    //   281: ldc_w 422
    //   284: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: pop
    //   288: aload_1
    //   289: aload 5
    //   291: invokevirtual 425	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   294: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: iconst_1
    //   299: aload_1
    //   300: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: invokestatic 413	com/netspark/android/netsvpn/bh:a	(ZLjava/lang/String;)V
    //   306: return
    //   307: astore_1
    //   308: new 172	java/lang/StringBuilder
    //   311: dup
    //   312: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   315: astore 5
    //   317: aload 5
    //   319: ldc_w 427
    //   322: invokevirtual 179	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: pop
    //   326: aload 5
    //   328: aload_1
    //   329: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   332: pop
    //   333: aload 5
    //   335: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   338: invokestatic 429	com/netspark/android/netsvpn/bh:b	(Ljava/lang/String;)V
    //   341: return
    //   342: astore 6
    //   344: goto -93 -> 251
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	347	0	this	a
    //   0	347	1	paramString	String
    //   101	11	2	i	int
    //   59	24	3	l	long
    //   6	328	5	localObject1	Object
    //   92	17	6	arrayOfByte	byte[]
    //   140	1	6	localObject2	Object
    //   145	1	6	localIOException1	java.io.IOException
    //   150	1	6	localObject3	Object
    //   157	1	6	localIOException2	java.io.IOException
    //   164	1	6	localObject4	Object
    //   175	95	6	localIOException3	java.io.IOException
    //   342	1	6	localObject5	Object
    //   64	147	7	localStringBuilder	StringBuilder
    //   61	108	8	localObject6	Object
    //   26	220	9	localSession	android.content.pm.PackageInstaller.Session
    // Exception table:
    //   from	to	target	type
    //   87	94	140	finally
    //   94	102	140	finally
    //   107	115	140	finally
    //   118	124	140	finally
    //   87	94	145	java/io/IOException
    //   94	102	145	java/io/IOException
    //   107	115	145	java/io/IOException
    //   118	124	145	java/io/IOException
    //   76	87	150	finally
    //   76	87	157	java/io/IOException
    //   66	76	164	finally
    //   66	76	175	java/io/IOException
    //   0	60	307	java/lang/Throwable
    //   124	129	307	java/lang/Throwable
    //   133	137	307	java/lang/Throwable
    //   223	228	307	java/lang/Throwable
    //   232	236	307	java/lang/Throwable
    //   236	250	307	java/lang/Throwable
    //   256	261	307	java/lang/Throwable
    //   265	269	307	java/lang/Throwable
    //   269	272	307	java/lang/Throwable
    //   272	306	307	java/lang/Throwable
    //   183	218	342	finally
  }
  
  public static boolean e()
  {
    return d.a(com.netspark.android.e.b.S);
  }
  
  public void a(int paramInt)
  {
    try
    {
      dx.e("OwnerFeaturesManager", "startGPS");
      if (paramInt == 0) {
        SetAdmin.MyAdmin.k().setSecureSetting(SetAdmin.MyAdmin.j(), "location_mode", String.valueOf(1));
      } else if (paramInt == 2) {
        SetAdmin.MyAdmin.k().setSecureSetting(SetAdmin.MyAdmin.j(), "location_mode", String.valueOf(3));
      }
      new Timer().schedule(new c(this, paramInt), be.e());
      return;
    }
    catch (Throwable localThrowable)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ERROR: ");
      localStringBuilder.append(localThrowable);
      dx.e("OwnerFeaturesManager", localStringBuilder.toString());
    }
  }
  
  public void a(String paramString, boolean paramBoolean, List<String> paramList)
  {
    try
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        SetAdmin.MyAdmin.k().setPermissionGrantState(SetAdmin.MyAdmin.j(), paramString, str, 1);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public boolean a()
  {
    return SetAdmin.MyAdmin.b();
  }
  
  public boolean a(File paramFile)
  {
    try
    {
      if (!paramFile.exists())
      {
        a(true, "file for install not exist ");
        return false;
      }
      if (a())
      {
        e(paramFile.getAbsolutePath());
        return true;
      }
      a(true, "manager not active so cant install");
      return false;
    }
    catch (Exception paramFile)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("installPackage ");
      localStringBuilder.append(paramFile);
      a(true, localStringBuilder.toString());
    }
    return false;
  }
  
  public boolean a(String paramString)
  {
    try
    {
      dx.j().getPackageInstaller().uninstall(paramString, C());
      return true;
    }
    catch (Throwable paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("UninstallAppSilently ");
      localStringBuilder.append(paramString);
      bh.b(localStringBuilder.toString());
    }
    return false;
  }
  
  public void b()
  {
    try
    {
      
    }
    catch (Throwable localThrowable)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("fail to call to  RunTasks.treatRoot: ");
      localStringBuilder.append(localThrowable);
      dx.f("OwnerFeaturesManager", localStringBuilder.toString());
    }
    if (!D()) {
      SetAdmin.MyAdmin.c();
    }
    if (!a())
    {
      t();
      return;
    }
    b.a(false);
    new b(this).start();
  }
  
  public void b(boolean paramBoolean) {}
  
  public String d()
  {
    if (SetAdmin.MyAdmin.b()) {
      return "owner";
    }
    return "not_owner";
  }
  
  public void f()
  {
    try
    {
      c("no_config_tethering", a(com.netspark.android.e.b.F, com.netspark.android.e.b.K));
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  /* Error */
  public void g()
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 54	com/netspark/android/e/b:F	Lcom/netspark/android/e/c;
    //   4: getstatic 532	com/netspark/android/e/b:H	Lcom/netspark/android/e/c;
    //   7: invokevirtual 528	com/netspark/android/custom_rom/activate_owner/a:a	(Lcom/netspark/android/e/c;Lcom/netspark/android/e/c;)Z
    //   10: istore_3
    //   11: getstatic 536	com/netspark/android/netsvpn/dq:c	Z
    //   14: istore_1
    //   15: iconst_0
    //   16: istore_2
    //   17: iload_1
    //   18: ifne +181 -> 199
    //   21: iload_3
    //   22: ifeq +172 -> 194
    //   25: goto +174 -> 199
    //   28: ldc_w 538
    //   31: iload_1
    //   32: invokestatic 530	com/netspark/android/custom_rom/activate_owner/a:c	(Ljava/lang/String;Z)V
    //   35: getstatic 540	com/netspark/android/custom_rom/manufacturers/a:e	Lcom/netspark/android/custom_rom/manufacturers/c;
    //   38: iconst_1
    //   39: invokevirtual 509	com/netspark/android/custom_rom/manufacturers/c:a	(Z)V
    //   42: ldc_w 542
    //   45: iload_3
    //   46: invokestatic 530	com/netspark/android/custom_rom/activate_owner/a:c	(Ljava/lang/String;Z)V
    //   49: ldc_w 544
    //   52: aload_0
    //   53: getstatic 54	com/netspark/android/e/b:F	Lcom/netspark/android/e/c;
    //   56: getstatic 546	com/netspark/android/e/b:I	Lcom/netspark/android/e/c;
    //   59: invokevirtual 528	com/netspark/android/custom_rom/activate_owner/a:a	(Lcom/netspark/android/e/c;Lcom/netspark/android/e/c;)Z
    //   62: invokestatic 530	com/netspark/android/custom_rom/activate_owner/a:c	(Ljava/lang/String;Z)V
    //   65: ldc_w 548
    //   68: iload_3
    //   69: invokestatic 530	com/netspark/android/custom_rom/activate_owner/a:c	(Ljava/lang/String;Z)V
    //   72: getstatic 550	com/netspark/android/custom_rom/manufacturers/a:f	Lcom/netspark/android/custom_rom/manufacturers/c;
    //   75: astore 4
    //   77: iload_2
    //   78: istore_1
    //   79: iload_3
    //   80: ifne +5 -> 85
    //   83: iconst_1
    //   84: istore_1
    //   85: aload 4
    //   87: iload_1
    //   88: invokevirtual 509	com/netspark/android/custom_rom/manufacturers/c:a	(Z)V
    //   91: getstatic 555	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   94: invokevirtual 558	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   97: ldc_w 560
    //   100: invokevirtual 563	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   103: ifne +73 -> 176
    //   106: ldc_w 565
    //   109: iload_3
    //   110: invokestatic 530	com/netspark/android/custom_rom/activate_owner/a:c	(Ljava/lang/String;Z)V
    //   113: iload_3
    //   114: ifeq +90 -> 204
    //   117: ldc_w 567
    //   120: astore 4
    //   122: goto +3 -> 125
    //   125: aload 4
    //   127: ldc_w 569
    //   130: invokevirtual 563	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   133: istore_1
    //   134: invokestatic 68	com/netspark/android/netsvpn/SetAdmin$MyAdmin:k	()Landroid/app/admin/DevicePolicyManager;
    //   137: invokestatic 104	com/netspark/android/netsvpn/SetAdmin$MyAdmin:j	()Landroid/content/ComponentName;
    //   140: ldc_w 571
    //   143: aload 4
    //   145: invokevirtual 574	android/app/admin/DevicePolicyManager:setGlobalSetting	(Landroid/content/ComponentName;Ljava/lang/String;Ljava/lang/String;)V
    //   148: getstatic 576	com/netspark/android/custom_rom/manufacturers/a:c	Lcom/netspark/android/custom_rom/manufacturers/c;
    //   151: iload_1
    //   152: invokevirtual 509	com/netspark/android/custom_rom/manufacturers/c:a	(Z)V
    //   155: invokestatic 68	com/netspark/android/netsvpn/SetAdmin$MyAdmin:k	()Landroid/app/admin/DevicePolicyManager;
    //   158: invokestatic 104	com/netspark/android/netsvpn/SetAdmin$MyAdmin:j	()Landroid/content/ComponentName;
    //   161: ldc_w 578
    //   164: aload 4
    //   166: invokevirtual 574	android/app/admin/DevicePolicyManager:setGlobalSetting	(Landroid/content/ComponentName;Ljava/lang/String;Ljava/lang/String;)V
    //   169: getstatic 580	com/netspark/android/custom_rom/manufacturers/a:d	Lcom/netspark/android/custom_rom/manufacturers/c;
    //   172: iload_1
    //   173: invokevirtual 509	com/netspark/android/custom_rom/manufacturers/c:a	(Z)V
    //   176: iload_3
    //   177: invokestatic 581	com/netspark/android/custom_rom/activate_owner/a:a	(Z)V
    //   180: return
    //   181: astore 4
    //   183: return
    //   184: astore 5
    //   186: goto -31 -> 155
    //   189: astore 4
    //   191: goto -15 -> 176
    //   194: iconst_0
    //   195: istore_1
    //   196: goto -168 -> 28
    //   199: iconst_1
    //   200: istore_1
    //   201: goto -173 -> 28
    //   204: ldc_w 569
    //   207: astore 4
    //   209: goto -84 -> 125
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	212	0	this	a
    //   14	187	1	bool1	boolean
    //   16	62	2	bool2	boolean
    //   10	167	3	bool3	boolean
    //   75	90	4	localObject	Object
    //   181	1	4	localThrowable	Throwable
    //   189	1	4	localException1	Exception
    //   207	1	4	str	String
    //   184	1	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	15	181	java/lang/Throwable
    //   28	77	181	java/lang/Throwable
    //   85	113	181	java/lang/Throwable
    //   125	134	181	java/lang/Throwable
    //   134	155	181	java/lang/Throwable
    //   155	176	181	java/lang/Throwable
    //   176	180	181	java/lang/Throwable
    //   134	155	184	java/lang/Exception
    //   155	176	189	java/lang/Exception
  }
  
  public void h()
  {
    for (;;)
    {
      try
      {
        boolean bool = a(com.netspark.android.e.b.F, com.netspark.android.e.b.G);
        DevicePolicyManager localDevicePolicyManager = SetAdmin.MyAdmin.k();
        ComponentName localComponentName = SetAdmin.MyAdmin.j();
        if (bool)
        {
          String str1 = "1";
          localDevicePolicyManager.setGlobalSetting(localComponentName, "auto_time_zone", str1);
          SetAdmin.MyAdmin.k().setAutoTimeRequired(SetAdmin.MyAdmin.j(), bool);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        return;
      }
      String str2 = "0";
    }
  }
  
  public void i()
  {
    try
    {
      boolean bool = a(com.netspark.android.e.b.F, com.netspark.android.e.b.L);
      DevicePolicyManager localDevicePolicyManager = SetAdmin.MyAdmin.k();
      ComponentName localComponentName = SetAdmin.MyAdmin.j();
      String[] arrayOfString;
      if (bool)
      {
        arrayOfString = new String[1];
        arrayOfString[0] = NetSparkApplication.e();
      }
      else
      {
        arrayOfString = new String[0];
      }
      localDevicePolicyManager.setLockTaskPackages(localComponentName, arrayOfString);
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  /* Error */
  public void j()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc -33
    //   4: ldc_w 599
    //   7: invokestatic 229	com/netspark/android/netsvpn/dx:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   10: getstatic 293	com/netspark/android/custom_rom/activate_owner/a:g	I
    //   13: istore_1
    //   14: iload_1
    //   15: iconst_m1
    //   16: if_icmpne +6 -> 22
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: getstatic 293	com/netspark/android/custom_rom/activate_owner/a:g	I
    //   25: istore_1
    //   26: iconst_m1
    //   27: putstatic 293	com/netspark/android/custom_rom/activate_owner/a:g	I
    //   30: getstatic 282	android/os/Build$VERSION:SDK_INT	I
    //   33: bipush 21
    //   35: if_icmplt +31 -> 66
    //   38: invokestatic 68	com/netspark/android/netsvpn/SetAdmin$MyAdmin:k	()Landroid/app/admin/DevicePolicyManager;
    //   41: invokestatic 104	com/netspark/android/netsvpn/SetAdmin$MyAdmin:j	()Landroid/content/ComponentName;
    //   44: ldc_w 437
    //   47: iload_1
    //   48: invokestatic 441	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   51: invokevirtual 445	android/app/admin/DevicePolicyManager:setSecureSetting	(Landroid/content/ComponentName;Ljava/lang/String;Ljava/lang/String;)V
    //   54: goto +12 -> 66
    //   57: astore_2
    //   58: goto +11 -> 69
    //   61: astore_2
    //   62: aload_2
    //   63: invokevirtual 248	java/lang/Throwable:printStackTrace	()V
    //   66: aload_0
    //   67: monitorexit
    //   68: return
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_2
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	a
    //   13	35	1	i	int
    //   57	1	2	localObject	Object
    //   61	11	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	14	57	finally
    //   22	54	57	finally
    //   62	66	57	finally
    //   2	14	61	java/lang/Throwable
    //   22	54	61	java/lang/Throwable
  }
}
