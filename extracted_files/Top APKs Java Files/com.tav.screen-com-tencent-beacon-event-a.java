package com.tencent.beacon.event;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import com.tencent.beacon.a.c;
import com.tencent.beacon.a.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static String g = "INSTALL_UPLOADED_DENGTA";
  private static String h = "USEAPP_UPLOADED_DENGTA";
  private Context a;
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;
  private int e = 180;
  private String f = "N";
  private List<com.tencent.beacon.a.a.b> i = null;
  private Runnable j = new Runnable()
  {
    public final void run()
    {
      a.a(a.this);
    }
  };
  private Runnable k = new Runnable()
  {
    public final void run()
    {
      a.b(a.this);
    }
  };
  private Runnable l = new Runnable()
  {
    public final void run()
    {
      a.c(a.this);
    }
  };
  
  public a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt)
  {
    this.a = paramContext;
    this.c = paramBoolean1;
    this.b = paramBoolean2;
    this.d = paramBoolean3;
    this.e = paramInt;
    paramContext = n.a();
    if (paramContext != null) {
      this.f = paramContext.r();
    }
    if (this.c)
    {
      paramContext = com.tencent.beacon.a.b.b(this.a, g, "");
      if (("".equals(paramContext)) || (!f.p().equals(paramContext))) {
        c.a().a(this.j, 60000L);
      }
    }
    if (this.b)
    {
      c.a().a(this.k, 60000L);
      a(true);
    }
  }
  
  private List<com.tencent.beacon.a.a.b> b(boolean paramBoolean)
  {
    if (this.a == null) {
      return null;
    }
    for (;;)
    {
      try
      {
        localObject1 = (ActivityManager)this.a.getSystemService("activity");
        if (localObject1 == null) {
          break;
        }
        Object localObject3 = ((ActivityManager)localObject1).getRunningAppProcesses();
        if ((localObject3 == null) || (((List)localObject3).size() <= 0)) {
          continue;
        }
        localObject1 = new ArrayList();
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
        try
        {
          localObject3 = ((List)localObject3).iterator();
          if (!((Iterator)localObject3).hasNext()) {
            continue;
          }
          localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject3).next();
          if ((localRunningAppProcessInfo.processName.startsWith("android")) || (localRunningAppProcessInfo.processName.startsWith("com.android."))) {
            continue;
          }
          if (localRunningAppProcessInfo.uid >= 10000) {
            continue;
          }
        }
        catch (Exception localException2) {}
        com.tencent.beacon.a.a.b localB = new com.tencent.beacon.a.a.b();
        localB.b = localRunningAppProcessInfo.processName;
        localB.c = localRunningAppProcessInfo.pid;
        localB.d = bool;
        ((List)localObject1).add(localB);
        continue;
      }
      catch (Exception localException1)
      {
        Object localObject1;
        Object localObject2 = null;
        continue;
        continue;
        localObject2 = null;
        continue;
        bool = true;
      }
      return localObject1;
      boolean bool = false;
      if (bool) {
        if (!paramBoolean) {}
      }
    }
  }
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  private List<String> c(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 71	com/tencent/beacon/event/a:a	Landroid/content/Context;
    //   4: ifnonnull +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: getfield 71	com/tencent/beacon/event/a:a	Landroid/content/Context;
    //   13: invokevirtual 277	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   16: astore_3
    //   17: aload_3
    //   18: ifnull -11 -> 7
    //   21: aload_3
    //   22: iconst_0
    //   23: invokevirtual 283	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   26: astore 4
    //   28: aload 4
    //   30: ifnull -23 -> 7
    //   33: aload 4
    //   35: invokeinterface 120 1 0
    //   40: ifle -33 -> 7
    //   43: new 195	java/util/ArrayList
    //   46: dup
    //   47: invokespecial 196	java/util/ArrayList:<init>	()V
    //   50: astore_3
    //   51: aload 4
    //   53: invokeinterface 200 1 0
    //   58: astore 4
    //   60: aload 4
    //   62: invokeinterface 206 1 0
    //   67: ifeq +201 -> 268
    //   70: aload 4
    //   72: invokeinterface 210 1 0
    //   77: checkcast 285	android/content/pm/PackageInfo
    //   80: astore 5
    //   82: aload 5
    //   84: getfield 289	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   87: getfield 294	android/content/pm/ApplicationInfo:flags	I
    //   90: iconst_1
    //   91: iand
    //   92: ifne +193 -> 285
    //   95: iconst_1
    //   96: istore_2
    //   97: goto +173 -> 270
    //   100: new 143	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   107: astore 6
    //   109: aload 6
    //   111: ldc -3
    //   113: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload 6
    //   119: aload 5
    //   121: getfield 297	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   124: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: ldc -3
    //   129: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload 6
    //   135: aload 5
    //   137: getfield 300	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   140: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc -3
    //   145: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: aload 6
    //   151: aload 5
    //   153: getfield 303	android/content/pm/PackageInfo:versionCode	I
    //   156: invokevirtual 256	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   159: ldc -3
    //   161: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: getstatic 308	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   168: invokestatic 314	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   171: bipush 9
    //   173: if_icmpge +46 -> 219
    //   176: aload 6
    //   178: ldc -3
    //   180: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: aload 6
    //   186: ldc -3
    //   188: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: pop
    //   192: iload_2
    //   193: ifeq +61 -> 254
    //   196: aload 6
    //   198: ldc 54
    //   200: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: pop
    //   204: aload_3
    //   205: aload 6
    //   207: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   210: invokeinterface 240 2 0
    //   215: pop
    //   216: goto -156 -> 60
    //   219: aload 6
    //   221: aload 5
    //   223: getfield 318	android/content/pm/PackageInfo:firstInstallTime	J
    //   226: invokevirtual 321	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   229: ldc -3
    //   231: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload 6
    //   237: aload 5
    //   239: getfield 324	android/content/pm/PackageInfo:lastUpdateTime	J
    //   242: invokevirtual 321	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   245: ldc -3
    //   247: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: goto -59 -> 192
    //   254: aload 6
    //   256: ldc -5
    //   258: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: goto -58 -> 204
    //   265: astore_3
    //   266: aconst_null
    //   267: areturn
    //   268: aload_3
    //   269: areturn
    //   270: iload_1
    //   271: ifne -171 -> 100
    //   274: iload_2
    //   275: ifeq -215 -> 60
    //   278: goto -178 -> 100
    //   281: astore 4
    //   283: aload_3
    //   284: areturn
    //   285: iconst_0
    //   286: istore_2
    //   287: goto -17 -> 270
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	290	0	this	a
    //   0	290	1	paramBoolean	boolean
    //   96	191	2	m	int
    //   16	189	3	localObject1	Object
    //   265	19	3	localException1	Exception
    //   26	45	4	localObject2	Object
    //   281	1	4	localException2	Exception
    //   80	158	5	localPackageInfo	android.content.pm.PackageInfo
    //   107	148	6	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   9	17	265	java/lang/Exception
    //   21	28	265	java/lang/Exception
    //   33	51	265	java/lang/Exception
    //   51	60	281	java/lang/Exception
    //   60	95	281	java/lang/Exception
    //   100	192	281	java/lang/Exception
    //   196	204	281	java/lang/Exception
    //   204	216	281	java/lang/Exception
    //   219	251	281	java/lang/Exception
    //   254	262	281	java/lang/Exception
  }
  
  public final void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      c.a().a(110, this.l, this.e * 1000, this.e * 1000);
      return;
    }
    c.a().a(110);
  }
}
