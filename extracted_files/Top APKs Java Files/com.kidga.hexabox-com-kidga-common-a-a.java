package com.kidga.common.a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;
import com.google.android.gms.ads.e;
import com.kidga.common.KidgaActivity;
import com.kidga.common.d.a;
import com.kidga.common.d.b;
import com.kidga.common.tracking.KidgaCBAndTrackActivity;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class a
  implements c.a, f
{
  private static String E = "menuadshowed:";
  private static String F = "menuadclicked:";
  boolean A = true;
  private ViewFlipper B;
  private final Handler C = new Handler();
  private String D = "";
  private String G;
  Activity a;
  String b;
  String c = "ca-app-pub-7197873272201969/3975371337";
  String d = null;
  e e;
  com.kidga.common.i.a f;
  ImageView g;
  public int h = -16711936;
  boolean i = false;
  ImageView j;
  ImageView k;
  ImageView l;
  ImageView m;
  int n = -1;
  boolean o = false;
  Timer p;
  boolean q = false;
  boolean r = false;
  boolean s = false;
  boolean t = false;
  boolean u = false;
  String v = null;
  boolean w = true;
  Runnable x = null;
  int y = 2;
  c z = new c();
  
  public a(Activity paramActivity, String paramString1, String paramString2)
  {
    this(paramActivity, paramString1, paramString2, "", -1);
  }
  
  public a(Activity paramActivity, String paramString1, String paramString2, String paramString3, int paramInt)
  {
    this.a = paramActivity;
    this.n = paramInt;
    if ((paramString3 == null) || ("".equals(paramString3))) {}
    for (paramString3 = "";; paramString3 = "&custom=" + paramString3)
    {
      this.D = paramString3;
      this.b = paramString1;
      this.d = paramString2;
      this.f = new com.kidga.common.i.a(paramActivity, com.kidga.common.activity.a.a().b());
      D();
      try
      {
        A();
        return;
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
      }
    }
  }
  
  private void A()
  {
    this.B = new ViewFlipper(this.a);
    if ((KidgaActivity.A) || (this.f.q()))
    {
      f();
      return;
    }
    this.e = new e(this.a);
    if (!this.A) {
      this.B.setVisibility(4);
    }
    this.e.setAdUnitId(this.b);
    this.e.setAdSize(com.google.android.gms.ads.d.g);
    this.e.setAdListener(new com.google.android.gms.ads.a()
    {
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        if (!a.this.i) {
          a.this.f();
        }
      }
      
      public void onAdLeftApplication()
      {
        super.onAdLeftApplication();
        a.a(a.this, true);
      }
      
      public void onAdLoaded()
      {
        if (!a.this.A) {
          a.a(a.this).setVisibility(4);
        }
        for (;;)
        {
          if ((!KidgaActivity.A) && (!a.this.f.q()))
          {
            a.this.i = true;
            a.a(a.this).removeAllViews();
            a.a(a.this).addView(a.this.e);
          }
          return;
          a.a(a.this).setVisibility(0);
        }
      }
    });
    this.B.setInAnimation(AnimationUtils.loadAnimation(this.a, d.a.push_up_in));
    this.B.setOutAnimation(AnimationUtils.loadAnimation(this.a, d.a.push_up_out));
    this.B.addView(this.e);
    TimerTask local3 = new TimerTask()
    {
      public void run()
      {
        if (!a.this.f.q()) {
          a.b(a.this).post(new Runnable()
          {
            public void run()
            {
              if (((a.a(a.this).getChildAt(0) instanceof e)) && (a.this.i) && (a.this.r))
              {
                if (a.this.j.getParent() == null) {
                  a.a(a.this).addView(a.this.j);
                }
                a.a(a.this).showNext();
              }
              if ((!a.this.i) && (a.this.r))
              {
                if (a.this.j.getParent() == null) {
                  a.a(a.this).addView(a.this.j);
                }
                a.a(a.this).showNext();
              }
            }
          });
        }
      }
    };
    this.p = new Timer();
    this.p.schedule(local3, 65000L, 60000L);
  }
  
  private void B()
  {
    new a(null).execute(new Object[0]);
  }
  
  private int C()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    if (localDisplayMetrics.widthPixels >= 480) {
      return 60;
    }
    if (localDisplayMetrics.widthPixels >= 320) {
      return 50;
    }
    return 37;
  }
  
  private void D()
  {
    this.j = new b(this.a)
    {
      protected void onAnimationEnd()
      {
        if ((a.a(a.this).getCurrentView() instanceof b))
        {
          if (a.this.x == null) {
            a.this.x = new Runnable()
            {
              public void run()
              {
                a.a(a.this).showNext();
                a.d(a.this);
              }
            };
          }
          a.b(a.this).postDelayed(a.this.x, 10000L);
        }
      }
    };
    this.g = new ImageView(this.a);
    int i2 = c();
    this.g.setImageResource(i2);
    if ((!KidgaActivity.A) && (!this.f.q()) && (this.n == -1)) {
      this.g.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          a.a(a.this, true);
          a.this.r = false;
          paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/dev?id=6924435167672710117"));
          a.this.a.startActivity(paramAnonymousView);
        }
      });
    }
    ImageView localImageView = this.g;
    int i1;
    if (i2 == d.b.banner_480x75)
    {
      i1 = 480;
      if (i2 != d.b.banner_480x75) {
        break label146;
      }
      i2 = 75;
    }
    for (;;)
    {
      localImageView.setLayoutParams(new FrameLayout.LayoutParams(i1, i2, 80));
      return;
      if (i2 == d.b.banner_320x50)
      {
        i1 = 320;
        break;
      }
      i1 = 240;
      break;
      label146:
      if (i2 == d.b.banner_320x50) {
        i2 = 50;
      } else {
        i2 = 37;
      }
    }
  }
  
  private void E()
  {
    this.C.removeCallbacks(this.x);
    this.x = null;
  }
  
  private static String F()
  {
    if ("ru".equalsIgnoreCase(Locale.getDefault().getLanguage())) {
      return "ru";
    }
    return "en";
  }
  
  public static int a(Activity paramActivity, DisplayMetrics paramDisplayMetrics)
  {
    if (!KidgaCBAndTrackActivity.A) {
      return com.google.android.gms.ads.d.g.a(paramActivity);
    }
    if (com.kidga.common.l.d.a(paramActivity, paramDisplayMetrics) >= 480) {
      return 60;
    }
    if (com.kidga.common.l.d.a(paramActivity, paramDisplayMetrics) >= 320) {
      return 50;
    }
    return 37;
  }
  
  public static String a(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkCountryIso();
  }
  
  /* Error */
  private String a(f paramF)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   4: invokestatic 404	com/google/android/gms/ads/b/a:a	(Landroid/content/Context;)Lcom/google/android/gms/ads/b/a$a;
    //   7: astore_2
    //   8: aload_0
    //   9: aload_2
    //   10: invokevirtual 408	com/google/android/gms/ads/b/a$a:a	()Ljava/lang/String;
    //   13: putfield 410	com/kidga/common/a/a:G	Ljava/lang/String;
    //   16: aload_1
    //   17: invokeinterface 412 1 0
    //   22: aload_0
    //   23: getfield 410	com/kidga/common/a/a:G	Ljava/lang/String;
    //   26: areturn
    //   27: astore_2
    //   28: aload_2
    //   29: invokevirtual 413	com/google/android/gms/common/c:printStackTrace	()V
    //   32: aconst_null
    //   33: astore_2
    //   34: goto -26 -> 8
    //   37: astore_2
    //   38: aload_2
    //   39: invokevirtual 414	com/google/android/gms/common/d:printStackTrace	()V
    //   42: aconst_null
    //   43: astore_2
    //   44: goto -36 -> 8
    //   47: astore_2
    //   48: aload_2
    //   49: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   52: aconst_null
    //   53: astore_2
    //   54: goto -46 -> 8
    //   57: astore_2
    //   58: aload_2
    //   59: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   62: aload_0
    //   63: aload_0
    //   64: invokevirtual 416	com/kidga/common/a/a:x	()Ljava/lang/String;
    //   67: putfield 410	com/kidga/common/a/a:G	Ljava/lang/String;
    //   70: goto -54 -> 16
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	a
    //   0	73	1	paramF	f
    //   7	3	2	localA	com.google.android.gms.ads.b.a.a
    //   27	2	2	localC	com.google.android.gms.common.c
    //   33	1	2	localObject1	Object
    //   37	2	2	localD	com.google.android.gms.common.d
    //   43	1	2	localObject2	Object
    //   47	2	2	localException1	Exception
    //   53	1	2	localObject3	Object
    //   57	2	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	8	27	com/google/android/gms/common/c
    //   0	8	37	com/google/android/gms/common/d
    //   0	8	47	java/lang/Exception
    //   8	16	57	java/lang/Exception
  }
  
  private void a(boolean paramBoolean)
  {
    this.f.o();
    if (this.f.q()) {
      this.B.setVisibility(4);
    }
    while (!paramBoolean) {
      return;
    }
    this.e.a(new com.google.android.gms.ads.c.a().a());
  }
  
  private void a(final boolean paramBoolean1, final boolean paramBoolean2, final boolean paramBoolean3)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        if (paramBoolean1) {
          a.this.h();
        }
        if (paramBoolean2) {
          a.this.i();
        }
        if (paramBoolean3) {
          a.this.j();
        }
      }
    }).start();
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
    return "market";
  }
  
  public View a(Dialog paramDialog)
  {
    if ((k()) && (this.m != null))
    {
      if ((m()) && (this.z.a())) {
        return this.z.a(this.f, paramDialog);
      }
      return this.m;
    }
    return this.z.a(this.f, paramDialog);
  }
  
  public String a(InputStream paramInputStream)
  {
    try
    {
      paramInputStream = new Scanner(paramInputStream).useDelimiter("\\A").next();
      return paramInputStream;
    }
    catch (Exception paramInputStream) {}
    return "";
  }
  
  public void a()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        if (a.this.d != null)
        {
          if (a.c(a.this) == null) {
            a.a(a.this, a.this);
          }
        }
        else {
          return;
        }
        a.this.s();
      }
    }).start();
  }
  
  public void a(RelativeLayout paramRelativeLayout)
  {
    if ((this.e != null) || (((KidgaActivity.A) || (this.f.q())) && (!KidgaActivity.B)))
    {
      if (this.B.getParent() != null) {
        ((RelativeLayout)this.B.getParent()).removeView(this.B);
      }
      paramRelativeLayout.addView(this.B);
      paramRelativeLayout.setVisibility(0);
    }
  }
  
  public void a(String paramString)
  {
    this.c = paramString;
  }
  
  protected String b()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    if ((localDisplayMetrics.widthPixels == 480) || ((localDisplayMetrics.widthPixels == 800) && (localDisplayMetrics.heightPixels == 480))) {
      return "banner3.png";
    }
    if (localDisplayMetrics.widthPixels >= 320) {
      return "banner2.png";
    }
    return "banner.png";
  }
  
  public int c()
  {
    if (this.n != -1) {
      return this.n;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    if ((KidgaActivity.A) || (this.f.q()))
    {
      if (localDisplayMetrics.widthPixels >= 480) {
        return d.b.kidga_480_75;
      }
      if (localDisplayMetrics.widthPixels >= 320) {
        return d.b.kidga_320_50;
      }
      return d.b.kidga_240_37;
    }
    if (localDisplayMetrics.widthPixels >= 480) {
      return d.b.banner_480x75;
    }
    if (localDisplayMetrics.widthPixels >= 320) {
      return d.b.banner_320x50;
    }
    return d.b.banner_240x37;
  }
  
  public int d()
  {
    return this.e.getHeight();
  }
  
  public void e()
  {
    boolean bool3 = true;
    for (;;)
    {
      try
      {
        if ((!this.f.q()) && (!KidgaActivity.A))
        {
          if (this.e != null) {
            this.e.a(new com.google.android.gms.ads.c.a().a());
          }
          if (!this.r) {
            B();
          }
          if ((!this.s) || (!this.t) || (!this.u))
          {
            if (this.s) {
              break label137;
            }
            bool1 = true;
            if (this.t) {
              break label142;
            }
            bool2 = true;
            if (this.u) {
              break label147;
            }
            a(bool1, bool2, bool3);
          }
          if (this.z.b(this.f)) {
            a();
          }
        }
        else
        {
          D();
          f();
          return;
        }
      }
      catch (Exception localException) {}
      return;
      label137:
      boolean bool1 = false;
      continue;
      label142:
      boolean bool2 = false;
      continue;
      label147:
      bool3 = false;
    }
  }
  
  protected void f()
  {
    this.B.removeAllViews();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.B.addView(this.g, localDisplayMetrics.widthPixels, C());
  }
  
  /* Error */
  void g()
  {
    // Byte code:
    //   0: new 565	java/net/URL
    //   3: dup
    //   4: new 179	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   11: ldc_w 567
    //   14: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: aload_0
    //   18: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   21: invokestatic 569	com/kidga/common/a/a:b	(Landroid/content/Context;)Ljava/lang/String;
    //   24: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc_w 571
    //   30: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   37: invokestatic 573	com/kidga/common/a/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   40: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: ldc_w 575
    //   46: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: aload_0
    //   50: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   53: invokevirtual 578	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   56: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload_0
    //   60: getfield 119	com/kidga/common/a/a:D	Ljava/lang/String;
    //   63: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   72: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   75: checkcast 586	java/net/HttpURLConnection
    //   78: astore 4
    //   80: aload 4
    //   82: iconst_1
    //   83: invokevirtual 589	java/net/HttpURLConnection:setDoInput	(Z)V
    //   86: aload 4
    //   88: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   91: aload 4
    //   93: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   96: astore 5
    //   98: aload_0
    //   99: aload 5
    //   101: invokevirtual 598	com/kidga/common/a/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   104: astore_3
    //   105: aload_3
    //   106: ldc_w 600
    //   109: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   112: iflt +173 -> 285
    //   115: aload_3
    //   116: aload_3
    //   117: ldc_w 600
    //   120: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   123: bipush 9
    //   125: iadd
    //   126: invokevirtual 608	java/lang/String:substring	(I)Ljava/lang/String;
    //   129: astore_2
    //   130: aload_3
    //   131: ldc_w 600
    //   134: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   137: iflt +153 -> 290
    //   140: aload_0
    //   141: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   144: aload_2
    //   145: invokestatic 610	com/kidga/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   148: istore_1
    //   149: aload 5
    //   151: invokevirtual 615	java/io/InputStream:close	()V
    //   154: aload 4
    //   156: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   159: iload_1
    //   160: ifne +124 -> 284
    //   163: aload_2
    //   164: ifnull +120 -> 284
    //   167: aload_3
    //   168: invokestatic 165	com/kidga/common/activity/a:a	()Lcom/kidga/common/activity/a;
    //   171: invokevirtual 168	com/kidga/common/activity/a:b	()Ljava/lang/String;
    //   174: invokevirtual 621	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   177: iconst_m1
    //   178: if_icmpne +106 -> 284
    //   181: new 565	java/net/URL
    //   184: dup
    //   185: new 179	java/lang/StringBuilder
    //   188: dup
    //   189: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   192: ldc_w 623
    //   195: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: aload_2
    //   199: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: ldc_w 625
    //   205: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload_0
    //   209: invokevirtual 626	com/kidga/common/a/a:b	()Ljava/lang/String;
    //   212: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   221: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   224: checkcast 586	java/net/HttpURLConnection
    //   227: astore_2
    //   228: aload_2
    //   229: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   232: aload_2
    //   233: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   236: astore 4
    //   238: aload 4
    //   240: invokestatic 632	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   243: astore 5
    //   245: aload_0
    //   246: getfield 312	com/kidga/common/a/a:j	Landroid/widget/ImageView;
    //   249: aload 5
    //   251: invokevirtual 636	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   254: aload_0
    //   255: getfield 312	com/kidga/common/a/a:j	Landroid/widget/ImageView;
    //   258: new 30	com/kidga/common/a/a$8
    //   261: dup
    //   262: aload_0
    //   263: aload_3
    //   264: invokespecial 639	com/kidga/common/a/a$8:<init>	(Lcom/kidga/common/a/a;Ljava/lang/String;)V
    //   267: invokevirtual 327	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   270: aload_0
    //   271: iconst_1
    //   272: putfield 127	com/kidga/common/a/a:r	Z
    //   275: aload 4
    //   277: invokevirtual 615	java/io/InputStream:close	()V
    //   280: aload_2
    //   281: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   284: return
    //   285: aconst_null
    //   286: astore_2
    //   287: goto -157 -> 130
    //   290: iconst_0
    //   291: istore_1
    //   292: goto -143 -> 149
    //   295: astore_2
    //   296: aload_2
    //   297: invokevirtual 640	java/lang/OutOfMemoryError:printStackTrace	()V
    //   300: return
    //   301: astore_2
    //   302: return
    //   303: astore 4
    //   305: goto -146 -> 159
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	308	0	this	a
    //   148	144	1	bool	boolean
    //   129	158	2	localObject1	Object
    //   295	2	2	localOutOfMemoryError	OutOfMemoryError
    //   301	1	2	localException1	Exception
    //   104	160	3	str	String
    //   78	198	4	localObject2	Object
    //   303	1	4	localException2	Exception
    //   96	154	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	130	295	java/lang/OutOfMemoryError
    //   130	149	295	java/lang/OutOfMemoryError
    //   149	159	295	java/lang/OutOfMemoryError
    //   167	284	295	java/lang/OutOfMemoryError
    //   0	130	301	java/lang/Exception
    //   130	149	301	java/lang/Exception
    //   167	284	301	java/lang/Exception
    //   149	159	303	java/lang/Exception
  }
  
  /* Error */
  void h()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield 129	com/kidga/common/a/a:s	Z
    //   7: new 565	java/net/URL
    //   10: dup
    //   11: new 179	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   18: ldc_w 642
    //   21: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: aload_0
    //   25: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   28: invokestatic 569	com/kidga/common/a/a:b	(Landroid/content/Context;)Ljava/lang/String;
    //   31: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: ldc_w 571
    //   37: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   44: invokestatic 573	com/kidga/common/a/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   47: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: ldc_w 575
    //   53: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: aload_0
    //   57: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   60: invokevirtual 578	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   63: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: aload_0
    //   67: getfield 119	com/kidga/common/a/a:D	Ljava/lang/String;
    //   70: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   79: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   82: checkcast 586	java/net/HttpURLConnection
    //   85: astore 4
    //   87: aload 4
    //   89: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   92: aload 4
    //   94: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   97: astore 5
    //   99: aload_0
    //   100: aload 5
    //   102: invokevirtual 598	com/kidga/common/a/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   105: astore_3
    //   106: aload_3
    //   107: ldc_w 600
    //   110: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   113: iflt +184 -> 297
    //   116: aload_3
    //   117: aload_3
    //   118: ldc_w 600
    //   121: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   124: bipush 9
    //   126: iadd
    //   127: invokevirtual 608	java/lang/String:substring	(I)Ljava/lang/String;
    //   130: astore_2
    //   131: aload_3
    //   132: ldc_w 600
    //   135: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   138: iflt +12 -> 150
    //   141: aload_0
    //   142: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   145: aload_2
    //   146: invokestatic 610	com/kidga/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   149: istore_1
    //   150: aload 5
    //   152: invokevirtual 615	java/io/InputStream:close	()V
    //   155: aload 4
    //   157: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   160: iload_1
    //   161: ifne +135 -> 296
    //   164: aload_2
    //   165: ifnull +131 -> 296
    //   168: new 565	java/net/URL
    //   171: dup
    //   172: new 179	java/lang/StringBuilder
    //   175: dup
    //   176: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   179: ldc_w 644
    //   182: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokestatic 646	com/kidga/common/a/a:F	()Ljava/lang/String;
    //   188: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: ldc_w 625
    //   194: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: aload_2
    //   198: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: ldc_w 648
    //   204: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   210: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   213: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   216: checkcast 586	java/net/HttpURLConnection
    //   219: astore_2
    //   220: aload_2
    //   221: iconst_1
    //   222: invokevirtual 589	java/net/HttpURLConnection:setDoInput	(Z)V
    //   225: aload_2
    //   226: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   229: aload_2
    //   230: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   233: astore 4
    //   235: aload 4
    //   237: invokestatic 632	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   240: astore 5
    //   242: aload_0
    //   243: new 314	android/widget/ImageView
    //   246: dup
    //   247: aload_0
    //   248: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   251: invokespecial 315	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   254: putfield 650	com/kidga/common/a/a:k	Landroid/widget/ImageView;
    //   257: aload_0
    //   258: getfield 650	com/kidga/common/a/a:k	Landroid/widget/ImageView;
    //   261: aload 5
    //   263: invokevirtual 636	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   266: aload_0
    //   267: getfield 650	com/kidga/common/a/a:k	Landroid/widget/ImageView;
    //   270: new 32	com/kidga/common/a/a$9
    //   273: dup
    //   274: aload_0
    //   275: aload_3
    //   276: invokespecial 651	com/kidga/common/a/a$9:<init>	(Lcom/kidga/common/a/a;Ljava/lang/String;)V
    //   279: invokevirtual 327	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   282: aload_0
    //   283: iconst_1
    //   284: putfield 129	com/kidga/common/a/a:s	Z
    //   287: aload 4
    //   289: invokevirtual 615	java/io/InputStream:close	()V
    //   292: aload_2
    //   293: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   296: return
    //   297: aconst_null
    //   298: astore_2
    //   299: goto -168 -> 131
    //   302: astore_2
    //   303: aload_2
    //   304: invokevirtual 640	java/lang/OutOfMemoryError:printStackTrace	()V
    //   307: return
    //   308: astore_2
    //   309: aload_2
    //   310: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   313: return
    //   314: astore 4
    //   316: goto -156 -> 160
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	319	0	this	a
    //   1	160	1	bool	boolean
    //   130	169	2	localObject1	Object
    //   302	2	2	localOutOfMemoryError	OutOfMemoryError
    //   308	2	2	localException1	Exception
    //   105	171	3	str	String
    //   85	203	4	localObject2	Object
    //   314	1	4	localException2	Exception
    //   97	165	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	131	302	java/lang/OutOfMemoryError
    //   131	150	302	java/lang/OutOfMemoryError
    //   150	160	302	java/lang/OutOfMemoryError
    //   168	296	302	java/lang/OutOfMemoryError
    //   2	131	308	java/lang/Exception
    //   131	150	308	java/lang/Exception
    //   168	296	308	java/lang/Exception
    //   150	160	314	java/lang/Exception
  }
  
  /* Error */
  void i()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield 131	com/kidga/common/a/a:t	Z
    //   5: new 565	java/net/URL
    //   8: dup
    //   9: new 179	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   16: ldc_w 653
    //   19: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: aload_0
    //   23: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   26: invokestatic 569	com/kidga/common/a/a:b	(Landroid/content/Context;)Ljava/lang/String;
    //   29: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: ldc_w 571
    //   35: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_0
    //   39: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   42: invokestatic 573	com/kidga/common/a/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   45: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc_w 575
    //   51: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: aload_0
    //   55: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   58: invokevirtual 578	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   61: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: aload_0
    //   65: getfield 119	com/kidga/common/a/a:D	Ljava/lang/String;
    //   68: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   77: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   80: checkcast 586	java/net/HttpURLConnection
    //   83: astore 4
    //   85: aload 4
    //   87: iconst_1
    //   88: invokevirtual 589	java/net/HttpURLConnection:setDoInput	(Z)V
    //   91: aload 4
    //   93: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   96: aload 4
    //   98: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   101: astore 5
    //   103: aload_0
    //   104: aload 5
    //   106: invokevirtual 598	com/kidga/common/a/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   109: astore_2
    //   110: aload_2
    //   111: astore_3
    //   112: aload_2
    //   113: ldc_w 655
    //   116: invokevirtual 621	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   119: ifle +39 -> 158
    //   122: aload_0
    //   123: aload_2
    //   124: aload_2
    //   125: ldc_w 655
    //   128: invokevirtual 621	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   131: iconst_1
    //   132: iadd
    //   133: invokevirtual 608	java/lang/String:substring	(I)Ljava/lang/String;
    //   136: invokestatic 661	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   139: invokevirtual 664	java/lang/Integer:intValue	()I
    //   142: putfield 141	com/kidga/common/a/a:y	I
    //   145: aload_2
    //   146: iconst_0
    //   147: aload_2
    //   148: ldc_w 655
    //   151: invokevirtual 621	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   154: invokevirtual 667	java/lang/String:substring	(II)Ljava/lang/String;
    //   157: astore_3
    //   158: aload_3
    //   159: astore_2
    //   160: aload_2
    //   161: ldc_w 600
    //   164: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   167: iflt +183 -> 350
    //   170: aload_2
    //   171: aload_2
    //   172: ldc_w 600
    //   175: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   178: bipush 9
    //   180: iadd
    //   181: invokevirtual 608	java/lang/String:substring	(I)Ljava/lang/String;
    //   184: astore_3
    //   185: aload_2
    //   186: ldc_w 600
    //   189: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   192: iflt +163 -> 355
    //   195: aload_0
    //   196: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   199: aload_3
    //   200: invokestatic 610	com/kidga/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   203: istore_1
    //   204: aload 5
    //   206: invokevirtual 615	java/io/InputStream:close	()V
    //   209: aload 4
    //   211: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   214: iload_1
    //   215: ifne +130 -> 345
    //   218: aload_3
    //   219: ifnull +126 -> 345
    //   222: new 565	java/net/URL
    //   225: dup
    //   226: new 179	java/lang/StringBuilder
    //   229: dup
    //   230: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   233: ldc_w 644
    //   236: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: invokestatic 646	com/kidga/common/a/a:F	()Ljava/lang/String;
    //   242: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: ldc_w 625
    //   248: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload_3
    //   252: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: ldc_w 648
    //   258: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   264: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   267: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   270: checkcast 586	java/net/HttpURLConnection
    //   273: astore_3
    //   274: aload_3
    //   275: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   278: aload_3
    //   279: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   282: astore 4
    //   284: aload 4
    //   286: invokestatic 632	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   289: astore 5
    //   291: aload_0
    //   292: new 314	android/widget/ImageView
    //   295: dup
    //   296: aload_0
    //   297: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   300: invokespecial 315	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   303: putfield 669	com/kidga/common/a/a:l	Landroid/widget/ImageView;
    //   306: aload_0
    //   307: getfield 669	com/kidga/common/a/a:l	Landroid/widget/ImageView;
    //   310: aload 5
    //   312: invokevirtual 636	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   315: aload_0
    //   316: getfield 669	com/kidga/common/a/a:l	Landroid/widget/ImageView;
    //   319: new 12	com/kidga/common/a/a$10
    //   322: dup
    //   323: aload_0
    //   324: aload_2
    //   325: invokespecial 670	com/kidga/common/a/a$10:<init>	(Lcom/kidga/common/a/a;Ljava/lang/String;)V
    //   328: invokevirtual 327	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   331: aload_0
    //   332: iconst_1
    //   333: putfield 131	com/kidga/common/a/a:t	Z
    //   336: aload 4
    //   338: invokevirtual 615	java/io/InputStream:close	()V
    //   341: aload_3
    //   342: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   345: return
    //   346: astore_3
    //   347: goto -187 -> 160
    //   350: aconst_null
    //   351: astore_3
    //   352: goto -167 -> 185
    //   355: iconst_0
    //   356: istore_1
    //   357: goto -153 -> 204
    //   360: astore_2
    //   361: aload_2
    //   362: invokevirtual 640	java/lang/OutOfMemoryError:printStackTrace	()V
    //   365: return
    //   366: astore_2
    //   367: aload_2
    //   368: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   371: return
    //   372: astore 4
    //   374: goto -160 -> 214
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	377	0	this	a
    //   203	154	1	bool	boolean
    //   109	216	2	localObject1	Object
    //   360	2	2	localOutOfMemoryError	OutOfMemoryError
    //   366	2	2	localException1	Exception
    //   111	231	3	localObject2	Object
    //   346	1	3	localException2	Exception
    //   351	1	3	localObject3	Object
    //   83	254	4	localObject4	Object
    //   372	1	4	localException3	Exception
    //   101	210	5	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   112	158	346	java/lang/Exception
    //   0	110	360	java/lang/OutOfMemoryError
    //   112	158	360	java/lang/OutOfMemoryError
    //   160	185	360	java/lang/OutOfMemoryError
    //   185	204	360	java/lang/OutOfMemoryError
    //   204	214	360	java/lang/OutOfMemoryError
    //   222	345	360	java/lang/OutOfMemoryError
    //   0	110	366	java/lang/Exception
    //   160	185	366	java/lang/Exception
    //   185	204	366	java/lang/Exception
    //   222	345	366	java/lang/Exception
    //   204	214	372	java/lang/Exception
  }
  
  /* Error */
  void j()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield 133	com/kidga/common/a/a:u	Z
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 135	com/kidga/common/a/a:v	Ljava/lang/String;
    //   14: new 565	java/net/URL
    //   17: dup
    //   18: new 179	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 672
    //   28: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_0
    //   32: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   35: invokestatic 569	com/kidga/common/a/a:b	(Landroid/content/Context;)Ljava/lang/String;
    //   38: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: ldc_w 571
    //   44: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_0
    //   48: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   51: invokestatic 573	com/kidga/common/a/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   54: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 575
    //   60: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_0
    //   64: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   67: invokevirtual 578	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   70: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: aload_0
    //   74: getfield 119	com/kidga/common/a/a:D	Ljava/lang/String;
    //   77: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   86: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   89: checkcast 586	java/net/HttpURLConnection
    //   92: astore 4
    //   94: aload 4
    //   96: iconst_1
    //   97: invokevirtual 589	java/net/HttpURLConnection:setDoInput	(Z)V
    //   100: aload 4
    //   102: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   105: aload 4
    //   107: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   110: astore 5
    //   112: aload_0
    //   113: aload 5
    //   115: invokevirtual 598	com/kidga/common/a/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   118: astore_3
    //   119: aload_3
    //   120: ldc_w 600
    //   123: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   126: iflt +18 -> 144
    //   129: aload_3
    //   130: aload_3
    //   131: ldc_w 600
    //   134: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   137: bipush 9
    //   139: iadd
    //   140: invokevirtual 608	java/lang/String:substring	(I)Ljava/lang/String;
    //   143: astore_2
    //   144: aload_3
    //   145: ldc_w 600
    //   148: invokevirtual 604	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   151: iflt +12 -> 163
    //   154: aload_0
    //   155: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   158: aload_2
    //   159: invokestatic 610	com/kidga/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   162: istore_1
    //   163: aload 5
    //   165: invokevirtual 615	java/io/InputStream:close	()V
    //   168: aload 4
    //   170: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   173: aload_0
    //   174: getfield 173	com/kidga/common/a/a:f	Lcom/kidga/common/i/a;
    //   177: new 179	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   184: getstatic 89	com/kidga/common/a/a:F	Ljava/lang/String;
    //   187: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: aload_2
    //   191: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: aconst_null
    //   198: invokevirtual 675	com/kidga/common/i/a:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   201: ifnonnull +144 -> 345
    //   204: iload_1
    //   205: ifne +140 -> 345
    //   208: aload_2
    //   209: ifnull +136 -> 345
    //   212: aload_0
    //   213: aload_2
    //   214: putfield 135	com/kidga/common/a/a:v	Ljava/lang/String;
    //   217: new 565	java/net/URL
    //   220: dup
    //   221: new 179	java/lang/StringBuilder
    //   224: dup
    //   225: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   228: ldc_w 677
    //   231: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: invokestatic 646	com/kidga/common/a/a:F	()Ljava/lang/String;
    //   237: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: ldc_w 625
    //   243: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: aload_2
    //   247: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: ldc_w 679
    //   253: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   259: invokespecial 580	java/net/URL:<init>	(Ljava/lang/String;)V
    //   262: invokevirtual 584	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   265: checkcast 586	java/net/HttpURLConnection
    //   268: astore 4
    //   270: aload 4
    //   272: invokevirtual 592	java/net/HttpURLConnection:connect	()V
    //   275: aload 4
    //   277: invokevirtual 596	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   280: astore 5
    //   282: aload 5
    //   284: invokestatic 632	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   287: astore 6
    //   289: aload_0
    //   290: new 314	android/widget/ImageView
    //   293: dup
    //   294: aload_0
    //   295: getfield 150	com/kidga/common/a/a:a	Landroid/app/Activity;
    //   298: invokespecial 315	android/widget/ImageView:<init>	(Landroid/content/Context;)V
    //   301: putfield 486	com/kidga/common/a/a:m	Landroid/widget/ImageView;
    //   304: aload_0
    //   305: getfield 486	com/kidga/common/a/a:m	Landroid/widget/ImageView;
    //   308: aload 6
    //   310: invokevirtual 636	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   313: aload_0
    //   314: getfield 486	com/kidga/common/a/a:m	Landroid/widget/ImageView;
    //   317: new 14	com/kidga/common/a/a$2
    //   320: dup
    //   321: aload_0
    //   322: aload_2
    //   323: aload_3
    //   324: invokespecial 682	com/kidga/common/a/a$2:<init>	(Lcom/kidga/common/a/a;Ljava/lang/String;Ljava/lang/String;)V
    //   327: invokevirtual 327	android/widget/ImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   330: aload_0
    //   331: iconst_1
    //   332: putfield 133	com/kidga/common/a/a:u	Z
    //   335: aload 5
    //   337: invokevirtual 615	java/io/InputStream:close	()V
    //   340: aload 4
    //   342: invokevirtual 618	java/net/HttpURLConnection:disconnect	()V
    //   345: return
    //   346: astore_2
    //   347: aload_2
    //   348: invokevirtual 640	java/lang/OutOfMemoryError:printStackTrace	()V
    //   351: return
    //   352: astore_2
    //   353: aload_2
    //   354: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   357: return
    //   358: astore 4
    //   360: goto -187 -> 173
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	363	0	this	a
    //   1	204	1	bool	boolean
    //   3	320	2	str1	String
    //   346	2	2	localOutOfMemoryError	OutOfMemoryError
    //   352	2	2	localException1	Exception
    //   118	206	3	str2	String
    //   92	249	4	localHttpURLConnection	java.net.HttpURLConnection
    //   358	1	4	localException2	Exception
    //   110	226	5	localInputStream	InputStream
    //   287	22	6	localBitmap	android.graphics.Bitmap
    // Exception table:
    //   from	to	target	type
    //   4	119	346	java/lang/OutOfMemoryError
    //   119	144	346	java/lang/OutOfMemoryError
    //   144	163	346	java/lang/OutOfMemoryError
    //   163	173	346	java/lang/OutOfMemoryError
    //   173	204	346	java/lang/OutOfMemoryError
    //   212	345	346	java/lang/OutOfMemoryError
    //   4	119	352	java/lang/Exception
    //   119	144	352	java/lang/Exception
    //   144	163	352	java/lang/Exception
    //   173	204	352	java/lang/Exception
    //   212	345	352	java/lang/Exception
    //   163	173	358	java/lang/Exception
  }
  
  public boolean k()
  {
    return ((!KidgaActivity.A) && (this.m != null) && (this.u)) || (this.z.a());
  }
  
  public void l()
  {
    if (this.v != null) {
      this.f.b(E + this.v, "true");
    }
  }
  
  public boolean m()
  {
    if (this.v == null) {}
    while (this.f.a(E + this.v, null) != null) {
      return true;
    }
    return false;
  }
  
  public ImageView n()
  {
    return this.l;
  }
  
  public int o()
  {
    return this.y;
  }
  
  public void p()
  {
    try
    {
      if (this.e != null) {
        this.e.c();
      }
      if (this.l != null) {
        KidgaActivity.unbindDrawables(this.l);
      }
      if (this.k != null) {
        KidgaActivity.unbindDrawables(this.k);
      }
      if (this.j != null) {
        KidgaActivity.unbindDrawables(this.j);
      }
      if (this.g != null) {
        KidgaActivity.unbindDrawables(this.g);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public String q()
  {
    return this.c;
  }
  
  public boolean r()
  {
    return this.c != null;
  }
  
  void s()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    Log.v("Ads", localDisplayMetrics.widthPixels + "");
    this.z = new c();
    this.z.a(this.a, this, localDisplayMetrics.widthPixels, this.d, t());
  }
  
  public boolean t()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.a.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f1 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
    float f2 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
    return Math.sqrt(f2 * f2 + f1 * f1) >= 6.5D;
  }
  
  public void u() {}
  
  public void v()
  {
    try
    {
      if ((this.e != null) && (this.B != null)) {
        this.B.setVisibility(4);
      }
      this.A = false;
      return;
    }
    catch (Exception localException) {}
  }
  
  public void w()
  {
    try
    {
      if ((this.e != null) && (this.B != null)) {
        this.B.setVisibility(0);
      }
      this.A = true;
      return;
    }
    catch (Exception localException) {}
  }
  
  public String x()
  {
    if (this.G != null) {
      return this.G;
    }
    return new com.kidga.common.i.a(this.a, "").a();
  }
  
  public void y()
  {
    s();
  }
  
  private class a
    extends AsyncTask<Object, Object, Object>
  {
    private a() {}
    
    protected Object doInBackground(Object... paramVarArgs)
    {
      a.this.g();
      return null;
    }
  }
}
