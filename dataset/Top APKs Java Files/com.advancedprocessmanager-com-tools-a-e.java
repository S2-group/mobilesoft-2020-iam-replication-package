package com.tools.a;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.tools.tools.r;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class e
{
  public List a = new ArrayList();
  PackageManager b;
  LayoutInflater c;
  p d;
  boolean e;
  List f = new ArrayList();
  a g;
  int h;
  GridView i;
  LinearLayout j;
  View k;
  ProgressDialog l;
  Handler m = new k(this);
  
  public e(a paramA)
  {
    this.g = paramA;
    this.h = paramA.i().getDimensionPixelSize(2131296273);
  }
  
  public static void a(Context paramContext, android.support.v4.c.a paramA1, android.support.v4.c.a paramA2)
  {
    if (paramA2 != null) {
      try
      {
        paramA2 = paramContext.getContentResolver().openOutputStream(paramA2.a());
        paramContext = paramContext.getContentResolver().openInputStream(paramA1.a());
        paramA1 = new byte['᐀'];
        for (;;)
        {
          int n = paramContext.read(paramA1);
          if (n == -1) {
            break;
          }
          paramA2.write(paramA1, 0, n);
        }
        return;
      }
      catch (Exception paramContext)
      {
        System.out.println("ssssssssss: " + paramContext.getMessage());
      }
    }
    paramA2.close();
    paramContext.close();
  }
  
  /* Error */
  public void a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: getfield 41	com/tools/a/e:f	Ljava/util/List;
    //   6: invokeinterface 138 1 0
    //   11: new 140	java/io/File
    //   14: dup
    //   15: aload_0
    //   16: getfield 50	com/tools/a/e:g	Lcom/tools/a/a;
    //   19: getfield 143	com/tools/a/a:g	Ljava/lang/String;
    //   22: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;)V
    //   25: astore_3
    //   26: aload_3
    //   27: invokevirtual 149	java/io/File:exists	()Z
    //   30: ifeq +145 -> 175
    //   33: aload_3
    //   34: invokevirtual 152	java/io/File:isDirectory	()Z
    //   37: ifeq +138 -> 175
    //   40: aload_3
    //   41: invokevirtual 156	java/io/File:listFiles	()[Ljava/io/File;
    //   44: astore_3
    //   45: aload_3
    //   46: ifnull +210 -> 256
    //   49: aload_3
    //   50: arraylength
    //   51: istore_2
    //   52: iload_1
    //   53: iload_2
    //   54: if_icmpge +202 -> 256
    //   57: aload_3
    //   58: iload_1
    //   59: aaload
    //   60: astore 4
    //   62: aload 4
    //   64: ifnull +104 -> 168
    //   67: aload 4
    //   69: invokevirtual 159	java/io/File:isFile	()Z
    //   72: ifeq +96 -> 168
    //   75: aload 4
    //   77: invokevirtual 162	java/io/File:getName	()Ljava/lang/String;
    //   80: ldc -92
    //   82: invokevirtual 170	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   85: ifeq +83 -> 168
    //   88: aload 4
    //   90: invokevirtual 173	java/io/File:getPath	()Ljava/lang/String;
    //   93: astore 4
    //   95: aload_0
    //   96: getfield 175	com/tools/a/e:b	Landroid/content/pm/PackageManager;
    //   99: aload 4
    //   101: sipush 8192
    //   104: invokevirtual 181	android/content/pm/PackageManager:getPackageArchiveInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   107: astore 5
    //   109: aload 5
    //   111: getfield 187	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   114: aload 4
    //   116: putfield 192	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   119: aload 5
    //   121: getfield 187	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   124: aload 4
    //   126: putfield 195	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   129: aload_0
    //   130: getfield 41	com/tools/a/e:f	Ljava/util/List;
    //   133: new 109	java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   140: aload 5
    //   142: getfield 187	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   145: getfield 198	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   148: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 5
    //   153: getfield 201	android/content/pm/PackageInfo:versionCode	I
    //   156: invokevirtual 204	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   159: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokeinterface 208 2 0
    //   167: pop
    //   168: iload_1
    //   169: iconst_1
    //   170: iadd
    //   171: istore_1
    //   172: goto -120 -> 52
    //   175: aload_3
    //   176: invokevirtual 211	java/io/File:mkdirs	()Z
    //   179: pop
    //   180: aload_0
    //   181: getfield 50	com/tools/a/e:g	Lcom/tools/a/a;
    //   184: getfield 143	com/tools/a/a:g	Ljava/lang/String;
    //   187: aload_0
    //   188: getfield 50	com/tools/a/e:g	Lcom/tools/a/a;
    //   191: getfield 143	com/tools/a/a:g	Ljava/lang/String;
    //   194: bipush 47
    //   196: invokevirtual 214	java/lang/String:lastIndexOf	(I)I
    //   199: iconst_1
    //   200: iadd
    //   201: invokevirtual 218	java/lang/String:substring	(I)Ljava/lang/String;
    //   204: astore_3
    //   205: aload_0
    //   206: getfield 50	com/tools/a/e:g	Lcom/tools/a/a;
    //   209: getfield 143	com/tools/a/a:g	Ljava/lang/String;
    //   212: iconst_0
    //   213: aload_0
    //   214: getfield 50	com/tools/a/e:g	Lcom/tools/a/a;
    //   217: getfield 143	com/tools/a/a:g	Ljava/lang/String;
    //   220: bipush 47
    //   222: invokevirtual 214	java/lang/String:lastIndexOf	(I)I
    //   225: invokevirtual 221	java/lang/String:substring	(II)Ljava/lang/String;
    //   228: astore 4
    //   230: aload_0
    //   231: getfield 50	com/tools/a/e:g	Lcom/tools/a/a;
    //   234: invokevirtual 224	com/tools/a/a:h	()Landroid/support/v4/app/FragmentActivity;
    //   237: aload 4
    //   239: invokestatic 229	com/tools/tools/n:a	(Landroid/app/Activity;Ljava/lang/String;)Landroid/support/v4/c/a;
    //   242: astore 4
    //   244: aload 4
    //   246: ifnull +10 -> 256
    //   249: aload 4
    //   251: aload_3
    //   252: invokevirtual 232	android/support/v4/c/a:a	(Ljava/lang/String;)Landroid/support/v4/c/a;
    //   255: pop
    //   256: return
    //   257: astore_3
    //   258: return
    //   259: astore 4
    //   261: goto -93 -> 168
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	264	0	this	e
    //   1	171	1	n	int
    //   51	4	2	i1	int
    //   25	227	3	localObject1	Object
    //   257	1	3	localException1	Exception
    //   60	190	4	localObject2	Object
    //   259	1	4	localException2	Exception
    //   107	45	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   2	45	257	java/lang/Exception
    //   49	52	257	java/lang/Exception
    //   175	244	257	java/lang/Exception
    //   249	256	257	java/lang/Exception
    //   67	168	259	java/lang/Exception
  }
  
  public View b()
  {
    this.b = this.g.h().getPackageManager();
    this.c = LayoutInflater.from(this.g.h());
    this.k = this.c.inflate(2130968580, null);
    this.j = ((LinearLayout)this.k.findViewById(2131427334));
    this.i = ((GridView)this.k.findViewById(2131427352));
    this.d = new p(this, this.g.h());
    this.i.setAdapter(this.d);
    int i1 = r.b(this.g.h()) / 350;
    int n = i1;
    if (i1 < 1) {
      n = 1;
    }
    this.i.setNumColumns(n);
    this.i.setOnItemClickListener(new f(this));
    c();
    return this.k;
  }
  
  public void c()
  {
    this.k.findViewById(2131427354).setVisibility(0);
    this.k.findViewById(2131427354).setBackgroundColor(com.tools.tools.m.b(this.g.h(), 2130771971));
    Button[] arrayOfButton = new Button[3];
    arrayOfButton[0] = ((Button)this.k.findViewById(2131427355));
    arrayOfButton[1] = ((Button)this.k.findViewById(2131427356));
    arrayOfButton[2] = ((Button)this.k.findViewById(2131427357));
    arrayOfButton[0].setText(2131099655);
    arrayOfButton[0].setOnClickListener(new g(this));
    arrayOfButton[1].setText(2131099693);
    arrayOfButton[1].setOnClickListener(new h(this));
    this.e = false;
    arrayOfButton[2].setText(2131099666);
    arrayOfButton[2].setOnClickListener(new j(this, arrayOfButton));
  }
  
  public void d()
  {
    this.g.a();
  }
  
  public void e()
  {
    new m(this).start();
  }
  
  public void f()
  {
    try
    {
      Object localObject = this.b.getInstalledPackages(0);
      this.a.clear();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (!localPackageInfo.applicationInfo.sourceDir.startsWith("/system")) {
          this.a.add(new n(this, localPackageInfo));
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
}
