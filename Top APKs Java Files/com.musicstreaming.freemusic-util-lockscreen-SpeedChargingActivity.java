package util.lockscreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.NativeAd;
import com.john.waveview.WaveView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SpeedChargingActivity
  extends FragmentActivity
{
  public static String[] a = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
  public static String[] b = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "January" };
  private BroadcastReceiver c;
  private WaveView d;
  private TextView e;
  private LinearLayout f;
  private TextView g;
  private TextView h;
  private BroadcastReceiver i;
  private ImageView j;
  private ImageView k;
  private ImageView l;
  private ImageView m;
  private SurfaceHolder n;
  private ImageView o;
  private ImageView p;
  private int q;
  
  public SpeedChargingActivity() {}
  
  private void a(NativeAd paramNativeAd, View paramView)
  {
    View localView = paramView;
    if (paramView == null)
    {
      localView = getLayoutInflater().inflate(R.layout.facebook_ad_big, null);
      c.a(this, paramNativeAd, localView);
    }
    this.f.removeAllViews();
    this.f.addView(localView);
    paramNativeAd.setAdListener(new AdListener()
    {
      public void onAdClicked(Ad paramAnonymousAd)
      {
        SpeedChargingActivity.this.finish();
      }
      
      public void onAdLoaded(Ad paramAnonymousAd) {}
      
      public void onError(Ad paramAnonymousAd, AdError paramAnonymousAdError) {}
      
      public void onLoggingImpression(Ad paramAnonymousAd) {}
    });
    c.a(this);
  }
  
  private static void a(StringBuilder paramStringBuilder, int paramInt)
  {
    if (paramInt < 10) {
      paramStringBuilder.append('0');
    }
    paramStringBuilder.append(paramInt);
  }
  
  private void a(boolean paramBoolean)
  {
    this.e.setText(this.q + "");
    this.d.setProgress(this.q);
  }
  
  private void d()
  {
    DrawerLayout localDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
    View localView = findViewById(R.id.drawer);
    int i1 = getResources().getDisplayMetrics().widthPixels;
    DrawerLayout.LayoutParams localLayoutParams = (DrawerLayout.LayoutParams)localView.getLayoutParams();
    localLayoutParams.width = i1;
    localView.setLayoutParams(localLayoutParams);
    localDrawerLayout.openDrawer(8388613);
    localDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener()
    {
      public void onDrawerClosed(View paramAnonymousView)
      {
        SpeedChargingActivity.this.finish();
      }
      
      public void onDrawerOpened(View paramAnonymousView) {}
      
      public void onDrawerSlide(View paramAnonymousView, float paramAnonymousFloat) {}
      
      public void onDrawerStateChanged(int paramAnonymousInt) {}
    });
  }
  
  private void e()
  {
    this.d = ((WaveView)findViewById(R.id.wave_view));
    this.e = ((TextView)findViewById(R.id.battery_percent));
    this.f = ((LinearLayout)findViewById(R.id.ad_container));
    this.g = ((TextView)findViewById(R.id.time));
    this.h = ((TextView)findViewById(R.id.date));
    this.j = ((ImageView)findViewById(R.id.step1_dot));
    this.k = ((ImageView)findViewById(R.id.step2));
    this.l = ((ImageView)findViewById(R.id.step2_dot));
    this.m = ((ImageView)findViewById(R.id.step3));
    this.o = ((ImageView)findViewById(R.id.flashlight_off));
    if (b.a != null) {
      this.o.setVisibility(0);
    }
    this.o.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SpeedChargingActivity.a(SpeedChargingActivity.this);
      }
    });
    this.p = ((ImageView)findViewById(R.id.flashlight_on));
    this.p.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SpeedChargingActivity.b(SpeedChargingActivity.this).setVisibility(4);
        SpeedChargingActivity.c(SpeedChargingActivity.this).setVisibility(0);
        SpeedChargingActivity.d(SpeedChargingActivity.this);
      }
    });
    a();
    if (b.a != null) {
      b.a.a();
    }
  }
  
  private void f()
  {
    if (b.a.c()) {
      b.a.e();
    }
  }
  
  private void g()
  {
    if (b.a.c())
    {
      this.o.setVisibility(4);
      this.p.setVisibility(0);
      if (Build.VERSION.SDK_INT >= 23) {}
      try
      {
        if (this.n == null)
        {
          this.n = ((SurfaceView)findViewById(R.id.preview)).getHolder();
          this.n.addCallback(new SurfaceHolder.Callback()
          {
            public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
            
            public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder) {}
            
            public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder) {}
          });
        }
        Camera localCamera = b.a.b();
        localCamera.setPreviewDisplay(this.n);
        localCamera.startPreview();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      b.a.d();
      return;
    }
    Toast.makeText(this, "Your camera flashlight is occupied by another app, please close that app and try again.", 1).show();
  }
  
  private void h()
  {
    NativeAd localNativeAd = c.c(this);
    if (localNativeAd != null)
    {
      a(localNativeAd, c.b());
      return;
    }
    localNativeAd = new NativeAd(this, c.a);
    localNativeAd.setAdListener(new AdListener()
    {
      public void onAdClicked(Ad paramAnonymousAd) {}
      
      public void onAdLoaded(Ad paramAnonymousAd)
      {
        if (paramAnonymousAd == null) {
          return;
        }
        SpeedChargingActivity.a(SpeedChargingActivity.this, (NativeAd)paramAnonymousAd, null);
      }
      
      public void onError(Ad paramAnonymousAd, AdError paramAnonymousAdError) {}
      
      public void onLoggingImpression(Ad paramAnonymousAd) {}
    });
    localNativeAd.loadAd();
  }
  
  public void a()
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getDefault(), Locale.US);
    localGregorianCalendar.setTimeInMillis(System.currentTimeMillis());
    StringBuilder localStringBuilder = new StringBuilder();
    a(localStringBuilder, localGregorianCalendar.get(11));
    localStringBuilder.append(':');
    a(localStringBuilder, localGregorianCalendar.get(12));
    this.g.setText(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(a[(localGregorianCalendar.get(7) - 1)]);
    localStringBuilder.append(',');
    localStringBuilder.append(b[localGregorianCalendar.get(2)]);
    localStringBuilder.append(' ');
    a(localStringBuilder, localGregorianCalendar.get(5));
    localStringBuilder.append(' ');
    this.h.setText(localStringBuilder.toString());
  }
  
  public void b()
  {
    this.i = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        SpeedChargingActivity.this.a();
      }
    };
    registerReceiver(this.i, new IntentFilter("android.intent.action.TIME_TICK"));
  }
  
  public void c()
  {
    this.c = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        boolean bool = true;
        if ("android.intent.action.BATTERY_CHANGED".equals(paramAnonymousIntent.getAction())) {
          if (2 != paramAnonymousIntent.getIntExtra("status", 1)) {
            break label49;
          }
        }
        for (;;)
        {
          SpeedChargingActivity.a(SpeedChargingActivity.this, paramAnonymousIntent.getIntExtra("level", 0));
          SpeedChargingActivity.a(SpeedChargingActivity.this, bool);
          return;
          label49:
          bool = false;
        }
      }
    };
    registerReceiver(this.c, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.setting)
    {
      startActivity(new Intent(this, SettingActivity.class));
      finish();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().addFlags(4719616);
    setContentView(R.layout.lock);
    e();
    try
    {
      d();
      if (c.b) {
        return;
      }
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
      finish();
      return;
    }
    h();
  }
  
  protected void onDestroy()
  {
    if (b.a != null)
    {
      b.a.e();
      b.a.f();
    }
    super.onDestroy();
  }
  
  protected void onStart()
  {
    super.onStart();
    c();
    b();
  }
  
  public void onStop()
  {
    unregisterReceiver(this.c);
    unregisterReceiver(this.i);
    sendBroadcast(new Intent("LOCK_SERVICE_SPEEDCHARGING_LASTTIME"));
    super.onStop();
  }
  
  public static class a
  {
    public static final String a = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806a0", "SpeedChargingActivity");
    public static final String b = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806a035", "SpeedChargingActivity");
    public static final String c = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3544c1822ca797780b2c164d128149bf7cdcaa778ed04b74e2f4f9db3cf1e09f44063279d5bba0a3d4a154d891a5e03", "SpeedChargingActivity");
    public static final String d = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3544c1822ca797780b2c164d128149bf7cdcaa778ed04b74e2f4f9db3cf1e09f44063279d5bba0a3d4a154d891a5e09", "SpeedChargingActivity");
    public static final String e = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba32", "SpeedChargingActivity");
    public static final String f = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba3288", "SpeedChargingActivity");
    public static final String g = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba37", "SpeedChargingActivity");
    public static final String h = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba3788", "SpeedChargingActivity");
    public static final String i = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba3b", "SpeedChargingActivity");
    public static final String j = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba3b88", "SpeedChargingActivity");
    public static final String k = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba22", "SpeedChargingActivity");
    public static final String l = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba2288", "SpeedChargingActivity");
    public static final String m = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3574c0234cc7a6cc0bdcc79d13b0d8ab496c2a124e902aa17235edda9d94210ba675806ba2280", "SpeedChargingActivity");
    public static final String n = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3544c1822ca797780b2c164d128149bf7cdcaa778ed04b74e2f4f9db3cf1e09f44063279d5bba0a3d4a154d891a5108d0", "SpeedChargingActivity");
    public static final String o = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3544c1822ca797780b2c164d128149bf7cdcaa778ed04b74e2f4f9db3cf1e09f44063279d5bba0a3d4a154d891a5108cc", "SpeedChargingActivity");
    public static final String p = a("30c467334fd274d4e985e0b07626f6e48e4d8b8089666118190d7c21284bd58f91d3544c1822ca797780b2c164d128149bf7cdcaa778ed04b74e2f4f9db3cf1e09f44063279d5bba0a3d4a154d891a5108c6", "SpeedChargingActivity");
    private static String q = "";
    private static HashMap<String, String> r = new HashMap();
    private static volatile boolean s;
    private static ArrayList<String> t = new ArrayList();
    
    private static byte a(byte paramByte1, byte paramByte2)
    {
      return (byte)((char)((char)Byte.decode("0x" + new String(new byte[] { paramByte1 })).byteValue() << '\004') ^ (char)Byte.decode("0x" + new String(new byte[] { paramByte2 })).byteValue());
    }
    
    public static View a(Context paramContext, ViewGroup paramViewGroup, String paramString)
    {
      return a(paramContext, null, paramViewGroup, null, false, false, 54, true, paramString);
    }
    
    public static View a(Context paramContext, ViewGroup paramViewGroup, boolean paramBoolean1, boolean paramBoolean2, String paramString)
    {
      return a(paramContext, null, paramViewGroup, null, false, paramBoolean1, 54, paramBoolean2, paramString);
    }
    
    public static View a(Context paramContext, String paramString)
    {
      return a(paramContext, "SpeedChargingActivity", null, null, false, false, 54, true, paramString);
    }
    
    public static View a(Context paramContext, final String paramString1, final ViewGroup paramViewGroup, final View paramView, final boolean paramBoolean1, final boolean paramBoolean2, final int paramInt, final boolean paramBoolean3, final String paramString2)
    {
      String str = (String)r.get(paramString2);
      if (!r.containsKey(paramString2))
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            if (SpeedChargingActivity.a.a()) {}
            Activity localActivity;
            do
            {
              return;
              SpeedChargingActivity.a.a(true);
              localActivity = null;
              if ((this.a instanceof Activity)) {
                localActivity = (Activity)this.a;
              }
              SpeedChargingActivity.a.a(paramString2);
              SpeedChargingActivity.a.a(false);
            } while ((localActivity == null) || (localActivity.isFinishing()) || ((Build.VERSION.SDK_INT >= 17) && (localActivity.isDestroyed())));
            localActivity.runOnUiThread(new Runnable()
            {
              public void run()
              {
                SpeedChargingActivity.a.a((String)SpeedChargingActivity.a.b().get(SpeedChargingActivity.a.1.this.b), SpeedChargingActivity.a.1.this.a, SpeedChargingActivity.a.1.this.c, SpeedChargingActivity.a.1.this.d, SpeedChargingActivity.a.1.this.e, SpeedChargingActivity.a.1.this.f, SpeedChargingActivity.a.1.this.g, SpeedChargingActivity.a.1.this.h, SpeedChargingActivity.a.1.this.i);
              }
            });
          }
        }).start();
        return null;
      }
      return b(str, paramContext, paramString1, paramViewGroup, paramView, paramBoolean1, paramBoolean2, paramInt, paramBoolean3);
    }
    
    public static String a(InputStream paramInputStream, String paramString)
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i1 = paramInputStream.read(arrayOfByte);
        if (i1 == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i1);
      }
      arrayOfByte = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      paramInputStream.close();
      return new String(arrayOfByte, paramString);
    }
    
    public static String a(String paramString1, String paramString2)
    {
      if ((paramString1 == null) || (paramString2 == null)) {
        return null;
      }
      return new String(a(d(paramString1), paramString2));
    }
    
    private static byte[] a(byte[] paramArrayOfByte, String paramString)
    {
      int i2 = 0;
      paramString = e(paramString);
      byte[] arrayOfByte = new byte[paramArrayOfByte.length];
      int i3 = 0;
      int i4 = 0;
      while (i2 < paramArrayOfByte.length)
      {
        i4 = i4 + 1 & 0xFF;
        i3 = i3 + (paramString[i4] & 0xFF) & 0xFF;
        int i1 = paramString[i4];
        paramString[i4] = paramString[i3];
        paramString[i3] = i1;
        int i5 = paramString[i4];
        int i6 = paramString[i3];
        int i7 = paramArrayOfByte[i2];
        arrayOfByte[i2] = ((byte)(paramString[((i5 & 0xFF) + (i6 & 0xFF) & 0xFF)] ^ i7));
        i2 += 1;
      }
      return arrayOfByte;
    }
    
    private static View b(String paramString1, final Context paramContext, String paramString2, ViewGroup paramViewGroup, View paramView, boolean paramBoolean1, final boolean paramBoolean2, int paramInt, final boolean paramBoolean3)
    {
      if ((paramString1 == null) || (paramString1.equals(""))) {
        return null;
      }
      Object localObject3;
      Object localObject4;
      Object localObject1;
      Object localObject2;
      Object localObject5;
      try
      {
        paramString2 = paramString1.split("\n\n");
        paramString1 = paramString2[0].split(",");
        localObject3 = paramString2[1].split(",");
        localObject4 = paramString2[2].split(",");
        paramString2 = paramString2[3].split(",");
        localObject1 = new String[1];
        localObject1[0] = "3bdf7e6d5d863f89f48df3b0672aece8924199";
        if (Build.VERSION.SDK_INT < 16) {
          return null;
        }
        localObject2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        if (("+0800".equals(new SimpleDateFormat("Z").format(((Calendar)localObject2).getTime()))) && (!paramBoolean1)) {
          return null;
        }
        if (TextUtils.isEmpty(q))
        {
          localObject5 = paramContext.getPackageManager().getInstalledPackages(0);
          localObject2 = new StringBuilder();
          localObject5 = ((List)localObject5).iterator();
          while (((Iterator)localObject5).hasNext())
          {
            ((StringBuilder)localObject2).append(((PackageInfo)((Iterator)localObject5).next()).packageName);
            ((StringBuilder)localObject2).append(",");
            continue;
            return null;
          }
        }
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
      label266:
      label286:
      label312:
      label705:
      label727:
      label745:
      label749:
      for (;;)
      {
        q = ((StringBuilder)localObject2).toString();
        int i1;
        int i2;
        int i3;
        int i4;
        if (!paramBoolean1)
        {
          i1 = localObject1.length;
          paramInt = 0;
          if (paramInt < i1)
          {
            localObject2 = localObject1[paramInt];
            if (!q.contains(a((String)localObject2, "SpeedChargingActivity")))
            {
              return null;
              paramInt = 0;
              localObject1 = q.split(",");
              int i5 = paramString2.length;
              i2 = 0;
              if (i2 >= i5) {
                break label745;
              }
              localObject2 = a(paramString2[i2], "SpeedChargingActivity");
              int i6 = localObject1.length;
              i3 = 0;
              if (i3 >= i6) {
                break label727;
              }
              i4 = paramInt;
              if (!localObject1[i3].contains((CharSequence)localObject2)) {
                break label705;
              }
              i4 = paramInt + 1;
              break label705;
            }
          }
        }
        for (;;)
        {
          if (paramInt >= paramString1.length) {
            break label749;
          }
          i1 = (int)(Math.random() * 100.0D % paramString1.length);
          localObject1 = a(paramString1[i1], "SpeedChargingActivity");
          localObject2 = a(localObject3[i1], "SpeedChargingActivity");
          paramString2 = a(localObject4[i1], "SpeedChargingActivity");
          try
          {
            paramContext.getPackageManager().getApplicationInfo((String)localObject1, 0);
            paramInt += 1;
          }
          catch (Exception paramString1)
          {
            if (!paramBoolean3) {}
          }
          for (;;)
          {
            try
            {
              paramString1 = LayoutInflater.from(paramContext).inflate(R.layout.flow_facebook, null);
            }
            catch (Exception paramString1)
            {
              final ImageView localImageView;
              paramString1.printStackTrace();
              return paramView;
            }
            try
            {
              localObject4 = (TextView)paramString1.findViewById(R.id.nativeAdTitle);
              localObject3 = (Button)paramString1.findViewById(R.id.nativeAdCallToAction);
              localObject5 = (ImageView)paramString1.findViewById(R.id.nativeAdIcon);
              localImageView = (ImageView)paramString1.findViewById(R.id.nativeAdImage);
              paramView = paramString2;
              if (!paramString2.startsWith("http")) {
                paramView = a("30c467334fd274d4f78ca4b07620edeb974a8b8089666118190d7c21284bd58f91d3", "SpeedChargingActivity") + paramString2;
              }
              new SpeedChargingActivity.b(paramView)
              {
                public void a(Drawable paramAnonymousDrawable)
                {
                  try
                  {
                    this.a.setImageDrawable(paramAnonymousDrawable);
                    if (paramBoolean3) {
                      return;
                    }
                    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
                    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
                    localDisplay.getMetrics(localDisplayMetrics);
                    int i = localDisplayMetrics.widthPixels;
                    i = localDisplayMetrics.heightPixels;
                    if (Build.VERSION.SDK_INT > 15) {}
                    localImageView.setImageDrawable(paramAnonymousDrawable);
                    localImageView.setVisibility(0);
                    return;
                  }
                  catch (Exception paramAnonymousDrawable) {}
                }
              }.execute(new Void[0]);
              ((TextView)localObject4).setText((CharSequence)localObject2);
              ((Button)localObject3).setText(a("11fe40177da417", "SpeedChargingActivity"));
              if (paramViewGroup != null)
              {
                paramViewGroup.removeAllViews();
                paramViewGroup.addView(paramString1);
              }
              paramString2 = a("35d16128599c61d4b480f2ea7026eeffc4469ace", "SpeedChargingActivity") + (String)localObject1;
              if (localObject3 != null) {
                ((Button)localObject3).setOnClickListener(new View.OnClickListener()
                {
                  public void onClick(View paramAnonymousView)
                  {
                    paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(this.a));
                    paramAnonymousView.setFlags(268435456);
                    try
                    {
                      paramContext.startActivity(paramAnonymousView);
                      SpeedChargingActivity.a.b("");
                      SpeedChargingActivity.a.c().add(this.c);
                      if ((paramBoolean2) && ((paramContext instanceof Activity))) {
                        ((Activity)paramContext).finish();
                      }
                      return;
                    }
                    catch (Exception paramAnonymousView)
                    {
                      for (;;)
                      {
                        paramAnonymousView.printStackTrace();
                      }
                    }
                  }
                });
              }
              return paramString1;
            }
            catch (Exception paramContext)
            {
              paramView = paramString1;
              paramString1 = paramContext;
              continue;
              paramString1 = paramView;
            }
            if (paramView == null) {
              paramString1 = LayoutInflater.from(paramContext).inflate(R.layout.facebook_ad_big, null);
            }
          }
          do
          {
            i1 = 2;
            break label266;
            paramInt += 1;
            break;
          } while (!paramBoolean1);
          i1 = 102;
          break label266;
          if (i4 > i1) {
            return null;
          }
          i3 += 1;
          paramInt = i4;
          break label312;
          if (paramInt > i1) {
            return null;
          }
          i2 += 1;
          break label286;
          paramInt = 0;
        }
      }
    }
    
    /* Error */
    private static void c(String paramString)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_2
      //   2: aconst_null
      //   3: astore 4
      //   5: new 428	java/net/URL
      //   8: dup
      //   9: aload_0
      //   10: invokespecial 429	java/net/URL:<init>	(Ljava/lang/String;)V
      //   13: invokevirtual 433	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   16: checkcast 435	java/net/HttpURLConnection
      //   19: astore_3
      //   20: aload_3
      //   21: ldc_w 437
      //   24: invokevirtual 440	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   27: aload_3
      //   28: sipush 15000
      //   31: invokevirtual 444	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   34: aload_3
      //   35: sipush 15000
      //   38: invokevirtual 447	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   41: aload_3
      //   42: invokevirtual 451	java/net/HttpURLConnection:getResponseCode	()I
      //   45: istore_1
      //   46: getstatic 122	util/lockscreen/SpeedChargingActivity$a:r	Ljava/util/HashMap;
      //   49: aload_0
      //   50: aconst_null
      //   51: invokevirtual 455	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   54: pop
      //   55: iload_1
      //   56: sipush 200
      //   59: if_icmpne +36 -> 95
      //   62: aload_3
      //   63: invokevirtual 459	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   66: ldc_w 461
      //   69: invokestatic 463	util/lockscreen/SpeedChargingActivity$a:a	(Ljava/io/InputStream;Ljava/lang/String;)Ljava/lang/String;
      //   72: astore_2
      //   73: aload_2
      //   74: ifnull +21 -> 95
      //   77: aload_2
      //   78: ldc 113
      //   80: invokevirtual 230	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   83: ifne +12 -> 95
      //   86: getstatic 122	util/lockscreen/SpeedChargingActivity$a:r	Ljava/util/HashMap;
      //   89: aload_0
      //   90: aload_2
      //   91: invokevirtual 455	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   94: pop
      //   95: aload_3
      //   96: ifnull +7 -> 103
      //   99: aload_3
      //   100: invokevirtual 466	java/net/HttpURLConnection:disconnect	()V
      //   103: return
      //   104: astore_2
      //   105: aload 4
      //   107: astore_3
      //   108: aload_2
      //   109: astore 4
      //   111: aload_3
      //   112: astore_2
      //   113: getstatic 122	util/lockscreen/SpeedChargingActivity$a:r	Ljava/util/HashMap;
      //   116: aload_0
      //   117: aconst_null
      //   118: invokevirtual 455	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   121: pop
      //   122: aload_3
      //   123: astore_2
      //   124: aload 4
      //   126: invokevirtual 322	java/lang/Exception:printStackTrace	()V
      //   129: aload_3
      //   130: ifnull -27 -> 103
      //   133: aload_3
      //   134: invokevirtual 466	java/net/HttpURLConnection:disconnect	()V
      //   137: return
      //   138: astore_0
      //   139: aload_2
      //   140: ifnull +7 -> 147
      //   143: aload_2
      //   144: invokevirtual 466	java/net/HttpURLConnection:disconnect	()V
      //   147: aload_0
      //   148: athrow
      //   149: astore_0
      //   150: aload_3
      //   151: astore_2
      //   152: goto -13 -> 139
      //   155: astore 4
      //   157: goto -46 -> 111
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	160	0	paramString	String
      //   45	15	1	i1	int
      //   1	90	2	str	String
      //   104	5	2	localException1	Exception
      //   112	40	2	localObject1	Object
      //   19	132	3	localObject2	Object
      //   3	122	4	localException2	Exception
      //   155	1	4	localException3	Exception
      // Exception table:
      //   from	to	target	type
      //   5	20	104	java/lang/Exception
      //   5	20	138	finally
      //   113	122	138	finally
      //   124	129	138	finally
      //   20	55	149	finally
      //   62	73	149	finally
      //   77	95	149	finally
      //   20	55	155	java/lang/Exception
      //   62	73	155	java/lang/Exception
      //   77	95	155	java/lang/Exception
    }
    
    private static byte[] d(String paramString)
    {
      int i2 = paramString.length();
      byte[] arrayOfByte = new byte[i2 / 2];
      paramString = paramString.getBytes();
      int i1 = 0;
      while (i1 < i2 / 2)
      {
        arrayOfByte[i1] = a(paramString[(i1 * 2)], paramString[(i1 * 2 + 1)]);
        i1 += 1;
      }
      return arrayOfByte;
    }
    
    private static byte[] e(String paramString)
    {
      int i3 = 0;
      byte[] arrayOfByte2 = paramString.getBytes();
      byte[] arrayOfByte1 = new byte['Ā'];
      int i2 = 0;
      while (i2 < 256)
      {
        arrayOfByte1[i2] = ((byte)i2);
        i2 += 1;
      }
      if ((arrayOfByte2 == null) || (arrayOfByte2.length == 0))
      {
        paramString = null;
        return paramString;
      }
      int i4 = 0;
      i2 = 0;
      for (;;)
      {
        paramString = arrayOfByte1;
        if (i3 >= 256) {
          break;
        }
        i4 = i4 + ((arrayOfByte2[i2] & 0xFF) + (arrayOfByte1[i3] & 0xFF)) & 0xFF;
        int i1 = arrayOfByte1[i3];
        arrayOfByte1[i3] = arrayOfByte1[i4];
        arrayOfByte1[i4] = i1;
        i2 = (i2 + 1) % arrayOfByte2.length;
        i3 += 1;
      }
    }
  }
  
  private static abstract class b
    extends AsyncTask<Void, Void, Drawable>
  {
    private String a;
    
    public b(String paramString)
    {
      this.a = paramString;
    }
    
    protected Drawable a(Void... paramVarArgs)
    {
      try
      {
        paramVarArgs = (InputStream)new URL(this.a).openConnection().getContent();
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        Object localObject = new byte['က'];
        for (;;)
        {
          int i = paramVarArgs.read((byte[])localObject);
          if (i == -1) {
            break;
          }
          localByteArrayOutputStream.write((byte[])localObject, 0, i);
        }
        localObject = localByteArrayOutputStream.toByteArray();
        localObject = new BitmapDrawable(BitmapFactory.decodeByteArray((byte[])localObject, 0, localObject.length));
        try
        {
          paramVarArgs.close();
          localByteArrayOutputStream.close();
          return localObject;
        }
        catch (Exception paramVarArgs)
        {
          return localObject;
        }
        return null;
      }
      catch (Exception paramVarArgs) {}
    }
    
    public abstract void a(Drawable paramDrawable);
    
    protected void b(Drawable paramDrawable)
    {
      super.onPostExecute(paramDrawable);
      a(paramDrawable);
    }
  }
}
