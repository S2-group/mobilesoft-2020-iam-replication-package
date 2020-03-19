package com.tools.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tools.tools.m;
import java.io.File;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class r
  extends Fragment
{
  public static DecimalFormat as = new DecimalFormat("#.##");
  PackageManager a;
  long aj = 0L;
  int ak = 0;
  TextView al;
  public LinearLayout am;
  Handler an = new s(this);
  LinearLayout ao;
  FrameLayout ap;
  TextView aq;
  SharedPreferences ar;
  int at = 0;
  public long au = 0L;
  public int av = 0;
  int aw = 0;
  GridView b;
  ad c;
  List d;
  Resources e;
  int f;
  int g;
  LinearLayout.LayoutParams h;
  Button i;
  
  public r() {}
  
  public static String a(double paramDouble)
  {
    paramDouble /= 1024.0D;
    if (paramDouble > 1024.0D) {
      paramDouble /= 1024.0D;
    }
    for (String str = " MB";; str = " KB") {
      return as.format(paramDouble) + str;
    }
  }
  
  /* Error */
  public void M()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 102	com/tools/c/r:d	Ljava/util/List;
    //   6: invokeinterface 107 1 0
    //   11: new 109	java/util/concurrent/CountDownLatch
    //   14: dup
    //   15: iconst_1
    //   16: invokespecial 112	java/util/concurrent/CountDownLatch:<init>	(I)V
    //   19: astore_1
    //   20: aload_0
    //   21: invokevirtual 115	com/tools/c/r:h	()Landroid/support/v4/app/FragmentActivity;
    //   24: invokevirtual 121	android/support/v4/app/FragmentActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   27: invokevirtual 127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   30: ldc -127
    //   32: iconst_2
    //   33: anewarray 131	java/lang/Class
    //   36: dup
    //   37: iconst_0
    //   38: ldc -123
    //   40: aastore
    //   41: dup
    //   42: iconst_1
    //   43: ldc -121
    //   45: aastore
    //   46: invokevirtual 139	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   49: astore_2
    //   50: aload_0
    //   51: getfield 141	com/tools/c/r:a	Landroid/content/pm/PackageManager;
    //   54: sipush 8704
    //   57: invokevirtual 147	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   60: astore 4
    //   62: aload 4
    //   64: aload 4
    //   66: invokeinterface 151 1 0
    //   71: iconst_1
    //   72: isub
    //   73: invokeinterface 155 2 0
    //   78: checkcast 157	android/content/pm/PackageInfo
    //   81: getfield 161	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   84: astore_3
    //   85: aload 4
    //   87: invokeinterface 165 1 0
    //   92: astore 4
    //   94: aload 4
    //   96: invokeinterface 171 1 0
    //   101: ifeq +63 -> 164
    //   104: aload 4
    //   106: invokeinterface 175 1 0
    //   111: checkcast 157	android/content/pm/PackageInfo
    //   114: astore 5
    //   116: aload_2
    //   117: aload_0
    //   118: invokevirtual 115	com/tools/c/r:h	()Landroid/support/v4/app/FragmentActivity;
    //   121: invokevirtual 121	android/support/v4/app/FragmentActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   124: iconst_2
    //   125: anewarray 123	java/lang/Object
    //   128: dup
    //   129: iconst_0
    //   130: aload 5
    //   132: getfield 161	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   135: aastore
    //   136: dup
    //   137: iconst_1
    //   138: new 177	com/tools/c/z
    //   141: dup
    //   142: aload_0
    //   143: aload_3
    //   144: aload_1
    //   145: invokespecial 180	com/tools/c/z:<init>	(Lcom/tools/c/r;Ljava/lang/String;Ljava/util/concurrent/CountDownLatch;)V
    //   148: aastore
    //   149: invokevirtual 186	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   152: pop
    //   153: goto -59 -> 94
    //   156: astore_1
    //   157: aload_1
    //   158: invokevirtual 189	java/lang/Exception:printStackTrace	()V
    //   161: aload_0
    //   162: monitorexit
    //   163: return
    //   164: aload_1
    //   165: invokevirtual 192	java/util/concurrent/CountDownLatch:await	()V
    //   168: goto -7 -> 161
    //   171: astore_1
    //   172: aload_0
    //   173: monitorexit
    //   174: aload_1
    //   175: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	176	0	this	r
    //   19	126	1	localCountDownLatch	java.util.concurrent.CountDownLatch
    //   156	9	1	localException	Exception
    //   171	4	1	localObject1	Object
    //   49	68	2	localMethod	Method
    //   84	60	3	str	String
    //   60	45	4	localObject2	Object
    //   114	17	5	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   2	94	156	java/lang/Exception
    //   94	153	156	java/lang/Exception
    //   164	168	156	java/lang/Exception
    //   2	94	171	finally
    //   94	153	171	finally
    //   157	161	171	finally
    //   164	168	171	finally
  }
  
  public View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.am = ((LinearLayout)paramLayoutInflater.inflate(2130968586, paramViewGroup, false));
    this.am.setBackgroundColor(m.b(h(), 2130771969));
    this.am.findViewById(2131427354).setBackgroundColor(m.b(h(), 2130771971));
    this.a = h().getPackageManager();
    this.c = new ad(this, h());
    this.ao = ((LinearLayout)this.am.findViewById(2131427334));
    this.b = ((GridView)this.am.findViewById(2131427369));
    this.ap = ((FrameLayout)this.am.findViewById(2131427368));
    this.aq = ((TextView)this.am.findViewById(2131427367));
    int k = com.tools.tools.r.b(h()) / 350;
    int j = k;
    if (k < 1) {
      j = 1;
    }
    this.b.setNumColumns(j);
    this.b.setAdapter(this.c);
    this.e = i();
    this.al = ((TextView)this.am.findViewById(2131427370));
    this.f = this.e.getDimensionPixelSize(2131296274);
    this.g = this.e.getDimensionPixelSize(2131296279);
    this.h = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    this.d = new ArrayList();
    this.b.setOnItemClickListener(new t(this));
    this.i = ((Button)this.am.findViewById(2131427355));
    this.i.setOnClickListener(new u(this));
    ((Button)this.am.findViewById(2131427356)).setOnClickListener(new v(this));
    ((Button)this.am.findViewById(2131427357)).setOnClickListener(new w(this));
    return this.am;
  }
  
  public void a()
  {
    this.an.sendEmptyMessage(-11);
    new y(this).start();
  }
  
  public void b(Context paramContext)
  {
    try
    {
      if (this.au != 0L)
      {
        this.aw = 0;
        paramContext = new StatFs(Environment.getDataDirectory().getPath());
        long l1 = paramContext.getBlockSize();
        long l2 = paramContext.getBlockCount();
        paramContext = new long[5];
        paramContext[0] = Long.valueOf(Long.valueOf(2147483647L).longValue()).longValue();
        paramContext[1] = Long.valueOf(Long.valueOf(2147483647L).longValue() * 20L).longValue();
        paramContext[2] = Long.valueOf(Long.valueOf(2147483647L).longValue() * 200L).longValue();
        paramContext[3] = Long.valueOf(l2 * l1 - 1L).longValue();
        paramContext[4] = Long.valueOf(Long.MAX_VALUE).longValue();
        Object localObject = Long.TYPE;
        localObject = this.a.getClass().getMethod("freeStorageAndNotify", new Class[] { localObject, IPackageDataObserver.class });
        ((Method)localObject).setAccessible(true);
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Long.valueOf(paramContext[0]);
        arrayOfObject[1] = new aa(this, arrayOfObject, paramContext, (Method)localObject);
        ((Method)localObject).invoke(this.a, arrayOfObject);
        return;
      }
      this.an.sendEmptyMessage(-2);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public void d(Bundle paramBundle)
  {
    super.d(paramBundle);
    this.ar = h().getSharedPreferences(h().getPackageName(), 0);
    a();
  }
}
