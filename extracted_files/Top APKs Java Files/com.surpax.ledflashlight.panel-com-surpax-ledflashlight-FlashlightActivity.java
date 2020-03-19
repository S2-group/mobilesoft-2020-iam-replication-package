package com.surpax.ledflashlight;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.ihandysoft.ad.HSAdBannerView;
import com.ihs.a.e.j;
import com.ihs.app.framework.HSApplication;
import com.ihs.app.framework.activity.HSActivity;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FlashlightActivity
  extends HSActivity
{
  private static FlashlightActivity p;
  public boolean a = false;
  public int b = 0;
  public boolean c = false;
  public boolean d;
  public int e;
  public SurfaceView f;
  public float g;
  public float h;
  private TextView i;
  private ImageButton j;
  private ImageButton k;
  private boolean l = false;
  private boolean m = false;
  private AlertDialog n = null;
  private HSAdBannerView o;
  private com.surpax.a.c q;
  private com.surpax.a.b r;
  private com.surpax.c.a.c s;
  private boolean t;
  private View u;
  private com.surpax.d.a v;
  private PowerManager.WakeLock w;
  
  public FlashlightActivity() {}
  
  private int a(String paramString)
  {
    int i1 = -1;
    SharedPreferences localSharedPreferences = getSharedPreferences(paramString, 0);
    if (localSharedPreferences != null) {
      i1 = localSharedPreferences.getInt(paramString, -1);
    }
    return i1;
  }
  
  public static FlashlightActivity a()
  {
    return p;
  }
  
  private void a(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences(paramString, 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  private void b(String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(paramString).setCancelable(false).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousDialogInterface != null) {
          paramAnonymousDialogInterface.cancel();
        }
        FlashlightActivity.a(FlashlightActivity.this, null);
      }
    });
    paramString = localBuilder.create();
    paramString.setTitle(17039380);
    paramString.setIcon(17301659);
    paramString.show();
    this.n = paramString;
  }
  
  public final void a(int paramInt)
  {
    if (this.q != null) {
      this.q.a(paramInt);
    }
  }
  
  public final void a(boolean paramBoolean)
  {
    if ((this.t) && (this.v != null))
    {
      if (paramBoolean)
      {
        this.v.setVisibility(0);
        if ((this.j != null) && (this.l))
        {
          this.j.setVisibility(0);
          this.l = false;
        }
        this.k.setVisibility(0);
      }
    }
    else {
      return;
    }
    this.v.setVisibility(4);
  }
  
  public final com.surpax.a.b b()
  {
    return this.r;
  }
  
  public final void c()
  {
    boolean bool = j.a(HSApplication.a(), "honeycomb_had_clicked").a("honeycomb_last_session_appear", false);
    Object localObject1 = j.a(HSApplication.a(), "honeycomb_had_clicked");
    j localJ = j.a(HSApplication.a(), "honeycomb_had_clicked");
    if ((com.surpax.b.a.a().g()) && (!com.surpax.b.a.c))
    {
      this.a = true;
      this.i.setText(com.surpax.b.a.a().f());
      switch (com.surpax.b.a.a().b())
      {
      }
      for (;;)
      {
        Object localObject2 = this.j.getDrawable();
        localObject2 = new RelativeLayout.LayoutParams(((Drawable)localObject2).getIntrinsicWidth(), ((Drawable)localObject2).getIntrinsicHeight());
        ((RelativeLayout.LayoutParams)localObject2).leftMargin = ((int)this.r.N);
        ((RelativeLayout.LayoutParams)localObject2).topMargin = ((int)this.r.O - 5);
        this.j.setLayoutParams((ViewGroup.LayoutParams)localObject2);
        this.j.setVisibility(0);
        this.j.bringToFront();
        this.j.postInvalidate();
        localJ.b("honeycomb_last_session_appear", true);
        if (!((j)localObject1).a("honeycomb_had_clicked", false))
        {
          localObject1 = this.j.getDrawable();
          if ((localObject1 instanceof AnimationDrawable)) {
            this.j.post(new Runnable()
            {
              public final void run()
              {
                ((AnimationDrawable)this.b).start();
              }
            });
          }
        }
        if (!bool) {
          com.ihs.app.a.b.a("HC_Icon_Appear");
        }
        com.ihs.app.a.b.a("HC_Icon_Viewed");
        return;
        this.j.setImageResource(2130837598);
        continue;
        this.j.setImageResource(2130837599);
        continue;
        this.j.setImageResource(2130837597);
      }
    }
    this.a = false;
    this.j.setVisibility(4);
    ((j)localObject1).b("honeycomb_last_session_appear", false);
    if (bool)
    {
      if (!com.surpax.b.a.c) {
        break label353;
      }
      com.ihs.app.a.b.a("HC_Icon_Disappear", new String[] { "Reason", "AlreadyInstallApp" });
    }
    for (;;)
    {
      this.i.setVisibility(4);
      return;
      label353:
      com.ihs.app.a.b.a("HC_Icon_Disappear", new String[] { "Reason", "RemoteConfig" });
    }
  }
  
  public final void d()
  {
    if (this.s != null) {
      this.s.d();
    }
  }
  
  public final void e()
  {
    if (this.s != null) {
      this.s.e();
    }
  }
  
  public final void f()
  {
    if (this.s != null) {
      this.s.f();
    }
  }
  
  public final boolean g()
  {
    return this.t;
  }
  
  public final View h()
  {
    return this.u;
  }
  
  public final void i()
  {
    if ((this.j != null) && (this.j.getVisibility() == 0))
    {
      this.j.setVisibility(4);
      this.l = true;
    }
  }
  
  public final View j()
  {
    return findViewById(2131492894);
  }
  
  public final boolean k()
  {
    int i1;
    if (this.t) {
      i1 = 0;
    }
    while ((i1 != 0) && (this.c))
    {
      return true;
      if (("samsung".equalsIgnoreCase(Build.MANUFACTURER)) && ("SPH-M820-BST".equals(Build.MODEL))) {
        i1 = 0;
      } else if (("samsung".equalsIgnoreCase(Build.MANUFACTURER)) && ("SGH-T679".equals(Build.MODEL))) {
        i1 = 0;
      } else if (("samsung".equalsIgnoreCase(Build.MANUFACTURER)) && ("SPH-D710".equals(Build.MODEL))) {
        i1 = 0;
      } else if (("ZTE".equalsIgnoreCase(Build.MANUFACTURER)) && ("N860".equals(Build.MODEL))) {
        i1 = 0;
      } else if (("ZTE".equalsIgnoreCase(Build.MANUFACTURER)) && ("ZTE-SKATE".equals(Build.MODEL))) {
        i1 = 0;
      } else if (("LGE".equalsIgnoreCase(Build.MANUFACTURER)) && ("LG-LS855".equals(Build.MODEL))) {
        i1 = 0;
      } else if (("Motorola".equalsIgnoreCase(Build.MANUFACTURER)) && ("A854".equals(Build.MODEL))) {
        i1 = 0;
      } else {
        i1 = 1;
      }
    }
    return false;
  }
  
  public final void l()
  {
    try
    {
      if (this.i.getVisibility() == 0) {
        this.i.setVisibility(4);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final void m()
  {
    int i1 = 0;
    if (TextUtils.isEmpty(com.surpax.b.a.a)) {}
    for (int i2 = 3;; i2 = 4)
    {
      com.surpax.b.a.c = false;
      List localList = getPackageManager().getInstalledApplications(8192);
      Iterator localIterator = localList.iterator();
      if (!localIterator.hasNext()) {}
      label105:
      for (;;)
      {
        localList.clear();
        return;
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((!TextUtils.isEmpty(com.surpax.b.a.a)) && (com.surpax.b.a.a.equalsIgnoreCase(localApplicationInfo.packageName)))
        {
          com.surpax.b.a.c = true;
          i1 += 1;
        }
        for (;;)
        {
          if (i1 >= i2) {
            break label105;
          }
          break;
        }
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  @SuppressLint({"CommitPrefEdits", "InlinedApi"})
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    paramBundle = getWindow();
    paramBundle.setFlags(1024, 1024);
    paramBundle.addFlags(2097280);
    setContentView(2130903044);
    p = this;
    this.l = false;
    this.d = false;
    this.m = false;
    com.surpax.a.a.l = false;
    this.c = false;
    this.g = 1.0F;
    this.h = 1.0F;
    int i1 = a("surpax_sound_state");
    com.surpax.a.a.h = i1;
    if (i1 == -1) {
      com.surpax.a.a.h = 1;
    }
    this.q = new com.surpax.a.c(getApplicationContext());
    Object localObject1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
    this.e = ((DisplayMetrics)localObject1).widthPixels;
    Log.d("Surpax App", "screen height is:" + ((DisplayMetrics)localObject1).heightPixels + ", width is:" + ((DisplayMetrics)localObject1).widthPixels);
    paramBundle = new BitmapFactory.Options();
    paramBundle.inTargetDensity = ((DisplayMetrics)localObject1).densityDpi;
    paramBundle.inDensity = ((DisplayMetrics)localObject1).densityDpi;
    this.h = 1.0F;
    this.g = 1.0F;
    this.r = new com.surpax.a.b();
    int i2;
    if ((800 == ((DisplayMetrics)localObject1).heightPixels) && (480 == ((DisplayMetrics)localObject1).widthPixels))
    {
      this.r.b(getResources(), paramBundle);
      paramBundle = this.r;
      localObject1 = new DisplayMetrics();
      p.getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
      i1 = ((DisplayMetrics)localObject1).widthPixels;
      i2 = ((DisplayMetrics)localObject1).heightPixels;
      int i3 = ((DisplayMetrics)localObject1).densityDpi * 78 / 240;
      paramBundle.w = i3;
      paramBundle.x = (((DisplayMetrics)localObject1).densityDpi * 82 / 240);
      paramBundle.u = ((int)(i1 * 0.05F));
      paramBundle.v = (i2 - (int)(((DisplayMetrics)localObject1).densityDpi * 370 / 480 * 1.0F) - 15);
      paramBundle.N = (i1 - paramBundle.u - i3);
      paramBundle.O = paramBundle.v;
      this.i = ((TextView)findViewById(2131492897));
      this.i.setVisibility(4);
      this.j = ((ImageButton)findViewById(2131492896));
      this.j.setVisibility(4);
      this.j.setSoundEffectsEnabled(false);
      this.j.setOnClickListener(new View.OnClickListener()
      {
        @SuppressLint({"NewApi"})
        public final void onClick(View paramAnonymousView)
        {
          com.ihs.app.a.b.a("HC_Icon_Clicked");
          j.a(HSApplication.a(), "honeycomb_had_clicked").b("honeycomb_had_clicked", true);
          FlashlightActivity.b(FlashlightActivity.this).setVisibility(4);
          paramAnonymousView = FlashlightActivity.c(FlashlightActivity.this).getDrawable();
          if ((paramAnonymousView instanceof AnimationDrawable))
          {
            ((AnimationDrawable)paramAnonymousView).stop();
            ((AnimationDrawable)paramAnonymousView).selectDrawable(0);
          }
          HoneyCombActivity.a = 0;
          paramAnonymousView = new Intent(FlashlightActivity.this, HoneyCombInnerActivity.class);
          FlashlightActivity.this.startActivity(paramAnonymousView);
        }
      });
      this.k = ((ImageButton)findViewById(2131492895));
      if (com.surpax.a.a.h != 1) {
        break label1747;
      }
      this.k.setBackgroundResource(2130837701);
    }
    for (;;)
    {
      this.k.setSoundEffectsEnabled(false);
      this.k.setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          if (com.surpax.a.a.h == 1)
          {
            com.surpax.a.a.h = 0;
            FlashlightActivity.a(FlashlightActivity.this).setBackgroundResource(2130837700);
            return;
          }
          com.surpax.a.a.h = 1;
          FlashlightActivity.a(FlashlightActivity.this).setBackgroundResource(2130837701);
          FlashlightActivity.a().a(1);
        }
      });
      paramBundle = new RelativeLayout.LayoutParams(this.r.w, this.r.x);
      paramBundle.leftMargin = ((int)this.r.u);
      paramBundle.topMargin = ((int)this.r.v);
      this.k.setLayoutParams(paramBundle);
      setVolumeControlStream(3);
      if (Build.VERSION.SDK_INT >= 8)
      {
        paramBundle = getWindow().getAttributes();
        paramBundle.buttonBrightness = 1.0F;
        getWindow().setAttributes(paramBundle);
      }
      return;
      if ((854 == ((DisplayMetrics)localObject1).heightPixels) && (480 == ((DisplayMetrics)localObject1).widthPixels))
      {
        this.r.a(getResources(), paramBundle);
        break;
      }
      if ((480 == ((DisplayMetrics)localObject1).heightPixels) && (320 == ((DisplayMetrics)localObject1).widthPixels))
      {
        this.r.d(getResources(), paramBundle);
        break;
      }
      if ((640 == ((DisplayMetrics)localObject1).heightPixels) && (480 == ((DisplayMetrics)localObject1).widthPixels))
      {
        this.r.c(getResources(), paramBundle);
        break;
      }
      if ((1280 == ((DisplayMetrics)localObject1).heightPixels) && (720 == ((DisplayMetrics)localObject1).widthPixels))
      {
        this.r.e(getResources(), paramBundle);
        break;
      }
      Object localObject2;
      if ((1920 == ((DisplayMetrics)localObject1).heightPixels) && (1080 == ((DisplayMetrics)localObject1).widthPixels))
      {
        localObject1 = this.r;
        localObject2 = getResources();
        ((com.surpax.a.b)localObject1).a = BitmapFactory.decodeResource((Resources)localObject2, 2130837607, paramBundle);
        Log.d("FlashLightActivity_Test", "background bmps's width is:" + ((com.surpax.a.b)localObject1).a.getWidth() + ", height is:" + ((com.surpax.a.b)localObject1).a.getHeight());
        ((com.surpax.a.b)localObject1).y = BitmapFactory.decodeResource((Resources)localObject2, 2130837613, paramBundle);
        ((com.surpax.a.b)localObject1).z = 481.0F;
        ((com.surpax.a.b)localObject1).A = 1152.0F;
        ((com.surpax.a.b)localObject1).B = 124;
        ((com.surpax.a.b)localObject1).C = 124;
        ((com.surpax.a.b)localObject1).g = BitmapFactory.decodeResource((Resources)localObject2, 2130837628, paramBundle);
        ((com.surpax.a.b)localObject1).h = BitmapFactory.decodeResource((Resources)localObject2, 2130837633, paramBundle);
        ((com.surpax.a.b)localObject1).i = BitmapFactory.decodeResource((Resources)localObject2, 2130837638, paramBundle);
        ((com.surpax.a.b)localObject1).j = BitmapFactory.decodeResource((Resources)localObject2, 2130837643, paramBundle);
        ((com.surpax.a.b)localObject1).k = BitmapFactory.decodeResource((Resources)localObject2, 2130837648, paramBundle);
        ((com.surpax.a.b)localObject1).l = BitmapFactory.decodeResource((Resources)localObject2, 2130837653, paramBundle);
        ((com.surpax.a.b)localObject1).m = BitmapFactory.decodeResource((Resources)localObject2, 2130837658, paramBundle);
        ((com.surpax.a.b)localObject1).n = BitmapFactory.decodeResource((Resources)localObject2, 2130837663, paramBundle);
        ((com.surpax.a.b)localObject1).o = BitmapFactory.decodeResource((Resources)localObject2, 2130837668, paramBundle);
        ((com.surpax.a.b)localObject1).p = BitmapFactory.decodeResource((Resources)localObject2, 2130837673, paramBundle);
        ((com.surpax.a.b)localObject1).q = 513.0F;
        ((com.surpax.a.b)localObject1).r = 557.0F;
        ((com.surpax.a.b)localObject1).s = 51;
        ((com.surpax.a.b)localObject1).t = 69;
        ((com.surpax.a.b)localObject1).b = BitmapFactory.decodeResource((Resources)localObject2, 2130837682, paramBundle);
        ((com.surpax.a.b)localObject1).c = 380.0F;
        ((com.surpax.a.b)localObject1).d = 1122.0F;
        ((com.surpax.a.b)localObject1).e = 316;
        ((com.surpax.a.b)localObject1).f = 495;
        ((com.surpax.a.b)localObject1).I = BitmapFactory.decodeResource((Resources)localObject2, 2130837618, paramBundle);
        ((com.surpax.a.b)localObject1).D = BitmapFactory.decodeResource((Resources)localObject2, 2130837623, paramBundle);
        ((com.surpax.a.b)localObject1).J = -2130.0F;
        ((com.surpax.a.b)localObject1).K = 0.0F;
        ((com.surpax.a.b)localObject1).L = 1080.0F;
        ((com.surpax.a.b)localObject1).M = 329.0F;
        ((com.surpax.a.b)localObject1).H = 761.0F;
        ((com.surpax.a.b)localObject1).G = 1080.0F;
        ((com.surpax.a.b)localObject1).E = 0.0F;
        ((com.surpax.a.b)localObject1).F = 0.0F;
        com.surpax.a.a.o = 126;
        com.surpax.a.a.n = 1107;
        com.surpax.a.a.m = 65190;
        break;
      }
      if ((1184 == ((DisplayMetrics)localObject1).heightPixels) && (720 == ((DisplayMetrics)localObject1).widthPixels))
      {
        this.r.f(getResources(), paramBundle);
        break;
      }
      if ((1184 == ((DisplayMetrics)localObject1).heightPixels) && (768 == ((DisplayMetrics)localObject1).widthPixels)) {
        this.r.f(getResources(), paramBundle);
      }
      for (;;)
      {
        label1308:
        this.h = (((DisplayMetrics)localObject1).heightPixels * 1.0F / this.r.a.getHeight());
        this.g = (((DisplayMetrics)localObject1).widthPixels * 1.0F / this.r.a.getWidth());
        break;
        float f4 = ((DisplayMetrics)localObject1).heightPixels * 1.0F / ((DisplayMetrics)localObject1).widthPixels;
        Log.d("Surpax App", "height / width = " + f4);
        localObject2 = new float[6];
        localObject2[0] = com.surpax.a.a.d;
        localObject2[1] = com.surpax.a.a.a;
        localObject2[2] = com.surpax.a.a.b;
        localObject2[3] = com.surpax.a.a.c;
        localObject2[4] = com.surpax.a.a.e;
        localObject2[5] = com.surpax.a.a.f;
        Log.d("Surpax App", "array length is: " + localObject2.length);
        float f1 = 100.0F;
        i2 = 0;
        i1 = 0;
        for (;;)
        {
          if (i1 >= localObject2.length) {}
          switch (i2)
          {
          default: 
            Log.d("Surpax App", "default invoke load480t0800");
            this.r.b(getResources(), paramBundle);
            break label1308;
            float f3 = Math.abs(localObject2[i1] - f4);
            float f2 = f1;
            if (f3 < f1)
            {
              f2 = f3;
              i2 = i1;
            }
            i1 += 1;
            f1 = f2;
          }
        }
        Log.d("Surpax App", "invoke load320to480");
        this.r.d(getResources(), paramBundle);
        continue;
        Log.d("Surpax App", "invoke 480to640");
        this.r.c(getResources(), paramBundle);
        continue;
        Log.d("Surpax App", "invoke load480t0800");
        this.r.b(getResources(), paramBundle);
        continue;
        Log.d("Surpax App", "invoke load480to854");
        this.r.a(getResources(), paramBundle);
        continue;
        Log.d("Surpax App", "invoke load720to1280");
        this.r.e(getResources(), paramBundle);
        continue;
        Log.d("Surpax App", "invoke load720to1184");
        this.r.f(getResources(), paramBundle);
      }
      label1747:
      this.k.setBackgroundResource(2130837700);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.o != null) {
      this.o.e();
    }
    this.o = null;
    this.d = true;
    this.v.setVisibility(4);
    this.v.f();
    this.m = false;
    com.surpax.a.a.l = false;
    if (this.s != null)
    {
      this.s.f();
      this.s.b();
      this.s = null;
    }
    a("surpax_lighting_frequency", 0);
    com.surpax.a.b localB = this.r;
    localB.a.recycle();
    localB.a = null;
    localB.y.recycle();
    localB.y = null;
    localB.g.recycle();
    localB.h.recycle();
    localB.i.recycle();
    localB.j.recycle();
    localB.k.recycle();
    localB.l.recycle();
    localB.m.recycle();
    localB.n.recycle();
    localB.o.recycle();
    localB.p.recycle();
    localB.g = null;
    localB.h = null;
    localB.i = null;
    localB.j = null;
    localB.k = null;
    localB.l = null;
    localB.m = null;
    localB.n = null;
    localB.o = null;
    localB.p = null;
    localB.I.recycle();
    localB.I = null;
    localB.D.recycle();
    localB.D = null;
    localB.b.recycle();
    localB.b = null;
    this.q.a();
    System.gc();
    try
    {
      Thread.sleep(350L);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
  }
  
  protected void onPause()
  {
    if (com.surpax.a.a.i == 1) {
      com.surpax.a.a.l = true;
    }
    for (;;)
    {
      if ((this.t) && (this.v != null) && (this.v.c())) {
        this.v.e();
      }
      com.surpax.a.a.g = 0;
      a("surpax_lighting_frequency", com.surpax.a.a.g);
      this.v.f();
      this.v.b();
      if (this.s != null)
      {
        this.s.f();
        this.s.b();
      }
      this.s = null;
      Intent localIntent = new Intent();
      localIntent.setAction("com.surpax.action.RESTORE_WIDGET");
      sendBroadcast(localIntent);
      try
      {
        if (this.n != null)
        {
          this.n.dismiss();
          this.n = null;
        }
        com.surpax.a.a.i = 0;
        com.surpax.a.a.H = 0;
        com.surpax.a.a.u = false;
        com.surpax.a.a.B = false;
        a("surpax_light_state", com.surpax.a.a.j);
        a("surpax_sound_state", com.surpax.a.a.h);
        a("surpax_light_state_exit", com.surpax.a.a.k);
        com.surpax.a.a.z = 1;
        a(com.surpax.a.a.A, com.surpax.a.a.z);
        super.onPause();
        return;
        com.surpax.a.a.l = false;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
  }
  
  protected void onRestart()
  {
    super.onRestart();
    this.c = false;
    this.m = true;
  }
  
  @TargetApi(11)
  protected void onResume()
  {
    int i1 = a("surpax_lighting_frequency");
    com.surpax.a.a.g = i1;
    if (i1 == -1) {
      com.surpax.a.a.g = 0;
    }
    com.surpax.a.a.j = a("surpax_light_state");
    if (-1 == com.surpax.a.a.j)
    {
      com.surpax.a.a.j = 0;
      com.surpax.a.a.u = true;
    }
    com.surpax.a.a.i = com.surpax.a.a.j;
    i1 = a("surpax_sound_state");
    com.surpax.a.a.h = i1;
    if (i1 == -1) {
      com.surpax.a.a.h = 1;
    }
    i1 = a("surpax_light_state_exit");
    com.surpax.a.a.k = i1;
    if (i1 == -1) {
      com.surpax.a.a.k = 1;
    }
    com.surpax.a.a.z = a(com.surpax.a.a.A);
    com.surpax.a.a.r = false;
    Object localObject = getApplicationContext();
    if (this.v == null)
    {
      this.v = new com.surpax.d.a((Context)localObject);
      ((RelativeLayout)findViewById(2131492894)).addView(this.v);
    }
    this.v.a();
    super.onResume();
    this.v.setVisibility(0);
    localObject = (RelativeLayout)findViewById(2131492898);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(1, 1);
    this.f = null;
    this.f = new SurfaceView(this);
    ((RelativeLayout)localObject).removeAllViews();
    ((RelativeLayout)localObject).addView(this.f, localLayoutParams);
    this.s = new com.surpax.c.a.c();
    this.s.a();
    if (com.surpax.a.a.H != 0)
    {
      com.surpax.a.a.i = 1;
      if (com.surpax.a.a.h == 1) {
        a(1);
      }
    }
    if ((this.m) && (com.surpax.a.a.l)) {
      com.surpax.a.a.i = 1;
    }
    localObject = getIntent();
    if (localObject != null)
    {
      boolean bool = ((Intent)localObject).getBooleanExtra("intent.extra.led.on", false);
      if (bool) {
        com.surpax.a.a.i = 1;
      }
      new StringBuilder("open is ").append(bool).toString();
      ((Intent)localObject).putExtra("intent.extra.led.on", false);
    }
    this.t = this.s.c();
    if (this.t) {
      com.surpax.a.a.i = 0;
    }
    if (this.t)
    {
      this.f.setVisibility(4);
      localObject = (RelativeLayout)findViewById(2131492894);
      localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
      localLayoutParams.addRule(2);
      View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903041, null);
      ((RelativeLayout)localObject).addView(localView, localLayoutParams);
      this.u = localView.findViewById(2131492885);
      this.u.setVisibility(4);
    }
    if (com.surpax.a.a.r) {
      b(getResources().getString(2131099692));
    }
    if ((-1 == com.surpax.a.a.z) && (com.surpax.a.a.B))
    {
      com.surpax.a.a.z = 1;
      com.ihs.app.a.b.a("No_LED_Alert_Viewed");
      b(getResources().getString(2131099698));
    }
    if ((this.v != null) && (!this.s.c())) {
      this.v.g();
    }
    this.o.a();
    new Thread(new c.1(this)).start();
  }
  
  protected void onStart()
  {
    super.onStart();
    this.w = ((PowerManager)getSystemService("power")).newWakeLock(268435457, getClass().getCanonicalName());
    this.w.acquire();
    Map localMap = com.ihs.a.b.b.d();
    com.surpax.b.a.a().a(localMap);
    new Thread()
    {
      public final void run()
      {
        FlashlightActivity.a().m();
        if (com.ihs.app.framework.a.b.a().d() != null) {
          com.ihs.app.framework.a.b.a().d().runOnUiThread(new Runnable()
          {
            public final void run()
            {
              FlashlightActivity.a().c();
            }
          });
        }
        super.run();
      }
    }.start();
    if (this.o == null) {
      this.o = ((HSAdBannerView)findViewById(2131492900));
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    new StringBuilder("flashlightactivity stop entrance, time = ").append(System.currentTimeMillis()).toString();
    this.v.setVisibility(4);
    System.gc();
    this.w.release();
    this.w = null;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return super.onTouchEvent(paramMotionEvent);
      try
      {
        if ((this.t) && (this.v != null) && (this.v.c())) {
          this.v.d();
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
}
