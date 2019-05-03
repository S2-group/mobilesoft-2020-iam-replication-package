package com.kidga.common.a;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;
import com.google.android.gms.ads.d;
import com.kidga.common.KidgaActivity;
import com.kidga.common.q;
import com.kidga.common.r;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Timer;

public final class a
{
  Activity a;
  String b;
  String c = "ca-app-pub-7197873272201969/3975371337";
  com.google.android.gms.ads.e d;
  com.kidga.common.h.a e;
  ImageView f;
  public int g = -16711936;
  boolean h = false;
  ImageView i;
  ImageView j;
  ImageView k;
  int l = -1;
  boolean m = false;
  Timer n;
  boolean o = false;
  boolean p = false;
  boolean q = false;
  boolean r = false;
  boolean s = true;
  Runnable t = null;
  int u = 2;
  ImageView v;
  private ViewFlipper w;
  private final Handler x = new Handler();
  private String y = "";
  
  public a(Activity paramActivity, String paramString)
  {
    this(paramActivity, paramString, "");
  }
  
  private a(Activity paramActivity, String paramString1, String paramString2)
  {
    this.a = paramActivity;
    this.l = -1;
    if ((paramString2 == null) || ("".equals(paramString2))) {
      paramString2 = "";
    }
    for (;;)
    {
      this.y = paramString2;
      this.b = paramString1;
      this.e = new com.kidga.common.h.a(paramActivity, com.kidga.common.activity.a.a().b());
      s.a(paramActivity);
      m();
      try
      {
        this.w = new ViewFlipper(this.a);
        if ((KidgaActivity.u) || (this.e.o()))
        {
          b();
          return;
          paramString2 = "&custom=" + paramString2;
        }
        else
        {
          this.d = new com.google.android.gms.ads.e(this.a);
          this.d.a(this.b);
          this.d.a(d.g);
          this.d.a(new com.google.android.gms.ads.c().a());
          this.d.a(new b(this));
          this.w.setInAnimation(AnimationUtils.loadAnimation(this.a, q.a));
          this.w.setOutAnimation(AnimationUtils.loadAnimation(this.a, q.b));
          this.w.addView(this.d);
          paramActivity = new c(this);
          this.n = new Timer();
          this.n.schedule(paramActivity, 65000L, 60000L);
          l();
          a(true, true);
          return;
        }
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
      }
    }
  }
  
  public static String a(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    System.err.println("tm.getNetworkCountryIso()=" + paramContext.getNetworkCountryIso());
    return paramContext.getNetworkCountryIso();
  }
  
  private static String a(InputStream paramInputStream)
  {
    return new Scanner(paramInputStream).useDelimiter("\\A").next();
  }
  
  private void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    new Thread(new e(this, paramBoolean1, paramBoolean2)).start();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if ((paramContext != null) && (paramString != null))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          boolean bool = ((PackageInfo)paramContext.next()).packageName.equals(paramString);
          if (bool) {
            return true;
          }
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String b(Context paramContext)
  {
    if (a(paramContext, "com.slideme.sam.manager")) {
      return "slideme";
    }
    if (a(paramContext, "com.sec.android.app.samsungapps")) {
      return "samsung";
    }
    if (a(paramContext, "com.amazon.venezia")) {
      return "amazon";
    }
    return "market";
  }
  
  private void l()
  {
    new m(this, (byte)0).execute(new Object[0]);
  }
  
  private void m()
  {
    int i2 = 480;
    this.i = new f(this, this.a);
    this.f = new ImageView(this.a);
    int i1;
    Object localObject;
    if (this.l != -1)
    {
      i1 = this.l;
      this.f.setImageResource(i1);
      if ((!KidgaActivity.u) && (!this.e.o()) && (this.l == -1)) {
        this.f.setOnClickListener(new h(this));
      }
      if (!KidgaActivity.u)
      {
        localObject = this.f;
        if (i1 != r.c) {
          break label301;
        }
        label113:
        if (i1 != r.c) {
          break label322;
        }
        i1 = 75;
      }
    }
    for (;;)
    {
      ((ImageView)localObject).setLayoutParams(new FrameLayout.LayoutParams(i2, i1, 80));
      return;
      localObject = new DisplayMetrics();
      this.a.getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      if ((KidgaActivity.u) || (this.e.o()))
      {
        if ((((DisplayMetrics)localObject).widthPixels == 480) || ((((DisplayMetrics)localObject).widthPixels == 800) && (((DisplayMetrics)localObject).heightPixels == 480)))
        {
          i1 = r.l;
          break;
        }
        if (((DisplayMetrics)localObject).widthPixels >= 320)
        {
          i1 = r.k;
          break;
        }
        i1 = r.j;
        break;
      }
      if ((((DisplayMetrics)localObject).widthPixels == 480) || ((((DisplayMetrics)localObject).widthPixels == 800) && (((DisplayMetrics)localObject).heightPixels == 480)))
      {
        i1 = r.c;
        break;
      }
      if (((DisplayMetrics)localObject).widthPixels >= 320)
      {
        i1 = r.b;
        break;
      }
      i1 = r.a;
      break;
      label301:
      if (i1 == r.b)
      {
        i2 = 320;
        break label113;
      }
      i2 = 240;
      break label113;
      label322:
      if (i1 == r.b) {
        i1 = 50;
      } else {
        i1 = 37;
      }
    }
  }
  
  private static String n()
  {
    if ("ru".equalsIgnoreCase(Locale.getDefault().getCountry())) {
      return "ru";
    }
    return "en";
  }
  
  public final void a()
  {
    if ((!this.e.o()) && (!KidgaActivity.u))
    {
      if (this.d != null) {
        this.d.a(new com.google.android.gms.ads.c().a());
      }
      if (!this.p) {
        l();
      }
      if ((!this.q) || (!this.r)) {
        a(this.q, this.r);
      }
      return;
    }
    m();
    b();
  }
  
  public final void a(RelativeLayout paramRelativeLayout)
  {
    if ((this.d != null) || (((KidgaActivity.u) || (this.e.o())) && (!KidgaActivity.v)))
    {
      if (this.w.getParent() != null) {
        ((RelativeLayout)this.w.getParent()).removeView(this.w);
      }
      paramRelativeLayout.addView(this.w);
      paramRelativeLayout.setVisibility(0);
    }
  }
  
  public final void a(String paramString)
  {
    this.c = paramString;
  }
  
  protected final void b()
  {
    this.w.removeAllViews();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    ViewFlipper localViewFlipper = this.w;
    ImageView localImageView = this.f;
    int i2 = localDisplayMetrics.widthPixels;
    localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i1;
    if ((localDisplayMetrics.widthPixels == 480) || ((localDisplayMetrics.widthPixels == 800) && (localDisplayMetrics.heightPixels == 480))) {
      i1 = 75;
    }
    for (;;)
    {
      localViewFlipper.addView(localImageView, i2, i1);
      return;
      if (localDisplayMetrics.widthPixels >= 320) {
        i1 = 50;
      } else {
        i1 = 37;
      }
    }
  }
  
  /* Error */
  final void c()
  {
    // Byte code:
    //   0: new 459	java/net/URL
    //   3: dup
    //   4: new 136	java/lang/StringBuilder
    //   7: dup
    //   8: ldc_w 461
    //   11: invokespecial 141	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   14: aload_0
    //   15: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   18: invokestatic 463	com/kidga/common/a/a:b	(Landroid/content/Context;)Ljava/lang/String;
    //   21: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: ldc_w 465
    //   27: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: aload_0
    //   31: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   34: invokestatic 467	com/kidga/common/a/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   37: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: ldc_w 469
    //   43: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_0
    //   47: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   50: invokevirtual 472	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   53: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: aload_0
    //   57: getfield 69	com/kidga/common/a/a:y	Ljava/lang/String;
    //   60: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokespecial 473	java/net/URL:<init>	(Ljava/lang/String;)V
    //   69: invokevirtual 477	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   72: checkcast 479	java/net/HttpURLConnection
    //   75: astore 4
    //   77: aload 4
    //   79: iconst_1
    //   80: invokevirtual 483	java/net/HttpURLConnection:setDoInput	(Z)V
    //   83: aload 4
    //   85: invokevirtual 486	java/net/HttpURLConnection:connect	()V
    //   88: aload 4
    //   90: invokevirtual 490	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   93: astore 5
    //   95: aload 5
    //   97: invokestatic 492	com/kidga/common/a/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   100: astore_3
    //   101: aload_3
    //   102: ldc_w 494
    //   105: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   108: iflt +278 -> 386
    //   111: aload_3
    //   112: aload_3
    //   113: ldc_w 494
    //   116: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   119: bipush 9
    //   121: iadd
    //   122: invokevirtual 502	java/lang/String:substring	(I)Ljava/lang/String;
    //   125: astore_2
    //   126: aload_3
    //   127: ldc_w 494
    //   130: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   133: iflt +258 -> 391
    //   136: aload_0
    //   137: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   140: aload_2
    //   141: invokestatic 325	com/kidga/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   144: istore_1
    //   145: aload 5
    //   147: invokevirtual 507	java/io/InputStream:close	()V
    //   150: aload 4
    //   152: invokevirtual 510	java/net/HttpURLConnection:disconnect	()V
    //   155: iload_1
    //   156: ifne +229 -> 385
    //   159: aload_2
    //   160: ifnull +225 -> 385
    //   163: aload_3
    //   164: invokestatic 104	com/kidga/common/activity/a:a	()Lcom/kidga/common/activity/a;
    //   167: invokevirtual 107	com/kidga/common/activity/a:b	()Ljava/lang/String;
    //   170: invokevirtual 513	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   173: iconst_m1
    //   174: if_icmpne +211 -> 385
    //   177: new 136	java/lang/StringBuilder
    //   180: dup
    //   181: ldc_w 515
    //   184: invokespecial 141	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   187: aload_2
    //   188: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: ldc_w 517
    //   194: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: astore 4
    //   199: new 386	android/util/DisplayMetrics
    //   202: dup
    //   203: invokespecial 387	android/util/DisplayMetrics:<init>	()V
    //   206: astore_2
    //   207: aload_0
    //   208: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   211: invokevirtual 393	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   214: invokeinterface 399 1 0
    //   219: aload_2
    //   220: invokevirtual 405	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   223: aload_2
    //   224: getfield 408	android/util/DisplayMetrics:widthPixels	I
    //   227: sipush 480
    //   230: if_icmpeq +148 -> 378
    //   233: aload_2
    //   234: getfield 408	android/util/DisplayMetrics:widthPixels	I
    //   237: sipush 800
    //   240: if_icmpne +101 -> 341
    //   243: aload_2
    //   244: getfield 411	android/util/DisplayMetrics:heightPixels	I
    //   247: sipush 480
    //   250: if_icmpne +91 -> 341
    //   253: goto +125 -> 378
    //   256: new 459	java/net/URL
    //   259: dup
    //   260: aload 4
    //   262: aload_2
    //   263: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   269: invokespecial 473	java/net/URL:<init>	(Ljava/lang/String;)V
    //   272: invokevirtual 477	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   275: checkcast 479	java/net/HttpURLConnection
    //   278: astore_2
    //   279: aload_2
    //   280: iconst_1
    //   281: invokevirtual 483	java/net/HttpURLConnection:setDoInput	(Z)V
    //   284: aload_2
    //   285: invokevirtual 486	java/net/HttpURLConnection:connect	()V
    //   288: aload_2
    //   289: invokevirtual 490	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   292: astore 4
    //   294: aload 4
    //   296: invokestatic 523	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   299: astore 5
    //   301: aload_0
    //   302: getfield 356	com/kidga/common/a/a:i	Landroid/widget/ImageView;
    //   305: aload 5
    //   307: invokevirtual 527	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   310: aload_0
    //   311: getfield 356	com/kidga/common/a/a:i	Landroid/widget/ImageView;
    //   314: new 529	com/kidga/common/a/i
    //   317: dup
    //   318: aload_0
    //   319: aload_3
    //   320: invokespecial 532	com/kidga/common/a/i:<init>	(Lcom/kidga/common/a/a;Ljava/lang/String;)V
    //   323: invokevirtual 371	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   326: aload_0
    //   327: iconst_1
    //   328: putfield 77	com/kidga/common/a/a:p	Z
    //   331: aload 4
    //   333: invokevirtual 507	java/io/InputStream:close	()V
    //   336: aload_2
    //   337: invokevirtual 510	java/net/HttpURLConnection:disconnect	()V
    //   340: return
    //   341: aload_2
    //   342: getfield 408	android/util/DisplayMetrics:widthPixels	I
    //   345: sipush 320
    //   348: if_icmplt +10 -> 358
    //   351: ldc_w 534
    //   354: astore_2
    //   355: goto -99 -> 256
    //   358: ldc_w 536
    //   361: astore_2
    //   362: goto -106 -> 256
    //   365: astore_2
    //   366: aload_2
    //   367: invokevirtual 537	java/lang/OutOfMemoryError:printStackTrace	()V
    //   370: return
    //   371: astore_2
    //   372: return
    //   373: astore 4
    //   375: goto -220 -> 155
    //   378: ldc_w 539
    //   381: astore_2
    //   382: goto -126 -> 256
    //   385: return
    //   386: aconst_null
    //   387: astore_2
    //   388: goto -262 -> 126
    //   391: iconst_0
    //   392: istore_1
    //   393: goto -248 -> 145
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	396	0	this	a
    //   144	249	1	bool	boolean
    //   125	237	2	localObject1	Object
    //   365	2	2	localOutOfMemoryError	OutOfMemoryError
    //   371	1	2	localException1	Exception
    //   381	7	2	str1	String
    //   100	220	3	str2	String
    //   75	257	4	localObject2	Object
    //   373	1	4	localException2	Exception
    //   93	213	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	126	365	java/lang/OutOfMemoryError
    //   126	145	365	java/lang/OutOfMemoryError
    //   145	155	365	java/lang/OutOfMemoryError
    //   163	253	365	java/lang/OutOfMemoryError
    //   256	340	365	java/lang/OutOfMemoryError
    //   341	351	365	java/lang/OutOfMemoryError
    //   0	126	371	java/lang/Exception
    //   126	145	371	java/lang/Exception
    //   163	253	371	java/lang/Exception
    //   256	340	371	java/lang/Exception
    //   341	351	371	java/lang/Exception
    //   145	155	373	java/lang/Exception
  }
  
  /* Error */
  final void d()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield 79	com/kidga/common/a/a:q	Z
    //   7: new 459	java/net/URL
    //   10: dup
    //   11: new 136	java/lang/StringBuilder
    //   14: dup
    //   15: ldc_w 541
    //   18: invokespecial 141	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   21: aload_0
    //   22: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   25: invokestatic 463	com/kidga/common/a/a:b	(Landroid/content/Context;)Ljava/lang/String;
    //   28: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: ldc_w 465
    //   34: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_0
    //   38: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   41: invokestatic 467	com/kidga/common/a/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   44: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc_w 469
    //   50: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_0
    //   54: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   57: invokevirtual 472	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   60: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_0
    //   64: getfield 69	com/kidga/common/a/a:y	Ljava/lang/String;
    //   67: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: invokespecial 473	java/net/URL:<init>	(Ljava/lang/String;)V
    //   76: invokevirtual 477	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   79: checkcast 479	java/net/HttpURLConnection
    //   82: astore 4
    //   84: aload 4
    //   86: iconst_1
    //   87: invokevirtual 483	java/net/HttpURLConnection:setDoInput	(Z)V
    //   90: aload 4
    //   92: invokevirtual 486	java/net/HttpURLConnection:connect	()V
    //   95: aload 4
    //   97: invokevirtual 490	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   100: astore 5
    //   102: aload 5
    //   104: invokestatic 492	com/kidga/common/a/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   107: astore_3
    //   108: aload_3
    //   109: ldc_w 494
    //   112: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   115: iflt +181 -> 296
    //   118: aload_3
    //   119: aload_3
    //   120: ldc_w 494
    //   123: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   126: bipush 9
    //   128: iadd
    //   129: invokevirtual 502	java/lang/String:substring	(I)Ljava/lang/String;
    //   132: astore_2
    //   133: aload_3
    //   134: ldc_w 494
    //   137: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   140: iflt +12 -> 152
    //   143: aload_0
    //   144: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   147: aload_2
    //   148: invokestatic 325	com/kidga/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   151: istore_1
    //   152: aload 5
    //   154: invokevirtual 507	java/io/InputStream:close	()V
    //   157: aload 4
    //   159: invokevirtual 510	java/net/HttpURLConnection:disconnect	()V
    //   162: iload_1
    //   163: ifne +132 -> 295
    //   166: aload_2
    //   167: ifnull +128 -> 295
    //   170: new 459	java/net/URL
    //   173: dup
    //   174: new 136	java/lang/StringBuilder
    //   177: dup
    //   178: ldc_w 543
    //   181: invokespecial 141	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   184: invokestatic 545	com/kidga/common/a/a:n	()Ljava/lang/String;
    //   187: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc_w 517
    //   193: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: aload_2
    //   197: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: ldc_w 547
    //   203: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: invokespecial 473	java/net/URL:<init>	(Ljava/lang/String;)V
    //   212: invokevirtual 477	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   215: checkcast 479	java/net/HttpURLConnection
    //   218: astore_2
    //   219: aload_2
    //   220: iconst_1
    //   221: invokevirtual 483	java/net/HttpURLConnection:setDoInput	(Z)V
    //   224: aload_2
    //   225: invokevirtual 486	java/net/HttpURLConnection:connect	()V
    //   228: aload_2
    //   229: invokevirtual 490	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   232: astore 4
    //   234: aload 4
    //   236: invokestatic 523	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   239: astore 5
    //   241: aload_0
    //   242: new 358	android/widget/ImageView
    //   245: dup
    //   246: aload_0
    //   247: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   250: invokespecial 359	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   253: putfield 549	com/kidga/common/a/a:j	Landroid/widget/ImageView;
    //   256: aload_0
    //   257: getfield 549	com/kidga/common/a/a:j	Landroid/widget/ImageView;
    //   260: aload 5
    //   262: invokevirtual 527	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   265: aload_0
    //   266: getfield 549	com/kidga/common/a/a:j	Landroid/widget/ImageView;
    //   269: new 551	com/kidga/common/a/j
    //   272: dup
    //   273: aload_0
    //   274: aload_3
    //   275: invokespecial 552	com/kidga/common/a/j:<init>	(Lcom/kidga/common/a/a;Ljava/lang/String;)V
    //   278: invokevirtual 371	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   281: aload_0
    //   282: iconst_1
    //   283: putfield 79	com/kidga/common/a/a:q	Z
    //   286: aload 4
    //   288: invokevirtual 507	java/io/InputStream:close	()V
    //   291: aload_2
    //   292: invokevirtual 510	java/net/HttpURLConnection:disconnect	()V
    //   295: return
    //   296: aconst_null
    //   297: astore_2
    //   298: goto -165 -> 133
    //   301: astore_2
    //   302: aload_2
    //   303: invokevirtual 537	java/lang/OutOfMemoryError:printStackTrace	()V
    //   306: return
    //   307: astore_2
    //   308: aload_2
    //   309: invokevirtual 227	java/lang/Exception:printStackTrace	()V
    //   312: return
    //   313: astore 4
    //   315: goto -153 -> 162
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	this	a
    //   1	162	1	bool	boolean
    //   132	166	2	localObject1	Object
    //   301	2	2	localOutOfMemoryError	OutOfMemoryError
    //   307	2	2	localException1	Exception
    //   107	168	3	str	String
    //   82	205	4	localObject2	Object
    //   313	1	4	localException2	Exception
    //   100	161	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	133	301	java/lang/OutOfMemoryError
    //   133	152	301	java/lang/OutOfMemoryError
    //   152	162	301	java/lang/OutOfMemoryError
    //   170	295	301	java/lang/OutOfMemoryError
    //   2	133	307	java/lang/Exception
    //   133	152	307	java/lang/Exception
    //   170	295	307	java/lang/Exception
    //   152	162	313	java/lang/Exception
  }
  
  /* Error */
  final void e()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield 81	com/kidga/common/a/a:r	Z
    //   7: new 459	java/net/URL
    //   10: dup
    //   11: new 136	java/lang/StringBuilder
    //   14: dup
    //   15: ldc_w 554
    //   18: invokespecial 141	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   21: aload_0
    //   22: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   25: invokestatic 463	com/kidga/common/a/a:b	(Landroid/content/Context;)Ljava/lang/String;
    //   28: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: ldc_w 465
    //   34: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_0
    //   38: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   41: invokestatic 467	com/kidga/common/a/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   44: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc_w 469
    //   50: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_0
    //   54: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   57: invokevirtual 472	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   60: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_0
    //   64: getfield 69	com/kidga/common/a/a:y	Ljava/lang/String;
    //   67: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: invokespecial 473	java/net/URL:<init>	(Ljava/lang/String;)V
    //   76: invokevirtual 477	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   79: checkcast 479	java/net/HttpURLConnection
    //   82: astore 4
    //   84: aload 4
    //   86: iconst_1
    //   87: invokevirtual 483	java/net/HttpURLConnection:setDoInput	(Z)V
    //   90: aload 4
    //   92: invokevirtual 486	java/net/HttpURLConnection:connect	()V
    //   95: aload 4
    //   97: invokevirtual 490	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   100: astore 5
    //   102: aload 5
    //   104: invokestatic 492	com/kidga/common/a/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   107: astore_2
    //   108: aload_2
    //   109: ldc_w 556
    //   112: invokevirtual 513	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   115: ifle +230 -> 345
    //   118: aload_0
    //   119: aload_2
    //   120: aload_2
    //   121: ldc_w 556
    //   124: invokevirtual 513	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   127: iconst_1
    //   128: iadd
    //   129: invokevirtual 502	java/lang/String:substring	(I)Ljava/lang/String;
    //   132: invokestatic 562	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   135: invokevirtual 566	java/lang/Integer:intValue	()I
    //   138: putfield 87	com/kidga/common/a/a:u	I
    //   141: aload_2
    //   142: iconst_0
    //   143: aload_2
    //   144: ldc_w 556
    //   147: invokevirtual 513	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   150: invokevirtual 569	java/lang/String:substring	(II)Ljava/lang/String;
    //   153: astore_3
    //   154: aload_3
    //   155: astore_2
    //   156: aload_2
    //   157: ldc_w 494
    //   160: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   163: iflt +185 -> 348
    //   166: aload_2
    //   167: aload_2
    //   168: ldc_w 494
    //   171: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   174: bipush 9
    //   176: iadd
    //   177: invokevirtual 502	java/lang/String:substring	(I)Ljava/lang/String;
    //   180: astore_3
    //   181: aload_2
    //   182: ldc_w 494
    //   185: invokevirtual 498	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   188: iflt +12 -> 200
    //   191: aload_0
    //   192: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   195: aload_3
    //   196: invokestatic 325	com/kidga/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   199: istore_1
    //   200: aload 5
    //   202: invokevirtual 507	java/io/InputStream:close	()V
    //   205: aload 4
    //   207: invokevirtual 510	java/net/HttpURLConnection:disconnect	()V
    //   210: iload_1
    //   211: ifne +132 -> 343
    //   214: aload_3
    //   215: ifnull +128 -> 343
    //   218: new 459	java/net/URL
    //   221: dup
    //   222: new 136	java/lang/StringBuilder
    //   225: dup
    //   226: ldc_w 543
    //   229: invokespecial 141	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   232: invokestatic 545	com/kidga/common/a/a:n	()Ljava/lang/String;
    //   235: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc_w 517
    //   241: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload_3
    //   245: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: ldc_w 547
    //   251: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: invokespecial 473	java/net/URL:<init>	(Ljava/lang/String;)V
    //   260: invokevirtual 477	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   263: checkcast 479	java/net/HttpURLConnection
    //   266: astore_3
    //   267: aload_3
    //   268: iconst_1
    //   269: invokevirtual 483	java/net/HttpURLConnection:setDoInput	(Z)V
    //   272: aload_3
    //   273: invokevirtual 486	java/net/HttpURLConnection:connect	()V
    //   276: aload_3
    //   277: invokevirtual 490	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   280: astore 4
    //   282: aload 4
    //   284: invokestatic 523	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   287: astore 5
    //   289: aload_0
    //   290: new 358	android/widget/ImageView
    //   293: dup
    //   294: aload_0
    //   295: getfield 89	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   298: invokespecial 359	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   301: putfield 571	com/kidga/common/a/a:k	Landroid/widget/ImageView;
    //   304: aload_0
    //   305: getfield 571	com/kidga/common/a/a:k	Landroid/widget/ImageView;
    //   308: aload 5
    //   310: invokevirtual 527	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   313: aload_0
    //   314: getfield 571	com/kidga/common/a/a:k	Landroid/widget/ImageView;
    //   317: new 573	com/kidga/common/a/k
    //   320: dup
    //   321: aload_0
    //   322: aload_2
    //   323: invokespecial 574	com/kidga/common/a/k:<init>	(Lcom/kidga/common/a/a;Ljava/lang/String;)V
    //   326: invokevirtual 371	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   329: aload_0
    //   330: iconst_1
    //   331: putfield 81	com/kidga/common/a/a:r	Z
    //   334: aload 4
    //   336: invokevirtual 507	java/io/InputStream:close	()V
    //   339: aload_3
    //   340: invokevirtual 510	java/net/HttpURLConnection:disconnect	()V
    //   343: return
    //   344: astore_3
    //   345: goto -189 -> 156
    //   348: aconst_null
    //   349: astore_3
    //   350: goto -169 -> 181
    //   353: astore_2
    //   354: aload_2
    //   355: invokevirtual 537	java/lang/OutOfMemoryError:printStackTrace	()V
    //   358: return
    //   359: astore_2
    //   360: aload_2
    //   361: invokevirtual 227	java/lang/Exception:printStackTrace	()V
    //   364: return
    //   365: astore 4
    //   367: goto -157 -> 210
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	370	0	this	a
    //   1	210	1	bool	boolean
    //   107	216	2	localObject1	Object
    //   353	2	2	localOutOfMemoryError	OutOfMemoryError
    //   359	2	2	localException1	Exception
    //   153	187	3	localObject2	Object
    //   344	1	3	localException2	Exception
    //   349	1	3	localObject3	Object
    //   82	253	4	localObject4	Object
    //   365	1	4	localException3	Exception
    //   100	209	5	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   108	154	344	java/lang/Exception
    //   2	108	353	java/lang/OutOfMemoryError
    //   108	154	353	java/lang/OutOfMemoryError
    //   156	181	353	java/lang/OutOfMemoryError
    //   181	200	353	java/lang/OutOfMemoryError
    //   200	210	353	java/lang/OutOfMemoryError
    //   218	343	353	java/lang/OutOfMemoryError
    //   2	108	359	java/lang/Exception
    //   156	181	359	java/lang/Exception
    //   181	200	359	java/lang/Exception
    //   218	343	359	java/lang/Exception
    //   200	210	365	java/lang/Exception
  }
  
  public final ImageView f()
  {
    if (this.j == null)
    {
      if (this.l != -1) {
        return null;
      }
      if (this.v == null)
      {
        this.v = new ImageView(this.a);
        this.v.setImageDrawable(this.a.getResources().getDrawable(r.g));
        this.v.setOnClickListener(new l(this));
      }
      return this.v;
    }
    return this.j;
  }
  
  public final ImageView g()
  {
    return this.k;
  }
  
  public final int h()
  {
    return this.u;
  }
  
  public final void i()
  {
    try
    {
      if (this.d != null) {
        this.d.a();
      }
      if (this.k != null) {
        KidgaActivity.a(this.k);
      }
      if (this.j != null) {
        KidgaActivity.a(this.j);
      }
      if (this.i != null) {
        KidgaActivity.a(this.i);
      }
      if (this.f != null) {
        KidgaActivity.a(this.f);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final String j()
  {
    return this.c;
  }
  
  public final boolean k()
  {
    return this.c != null;
  }
}
