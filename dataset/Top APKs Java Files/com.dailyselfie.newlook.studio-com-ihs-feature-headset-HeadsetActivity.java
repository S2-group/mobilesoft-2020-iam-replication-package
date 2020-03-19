package com.ihs.feature.headset;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.ihs.app.framework.a.b;
import com.ihs.commons.g.f;
import com.ihs.keyboardutils.a.i;
import com.ihs.keyboardutils.a.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.appcloudbox.ads.expressad.c;
import net.appcloudbox.ads.expressad.c.a;

public class HeadsetActivity
  extends b
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener
{
  private SeekBar m;
  private AudioManager n;
  private BroadcastReceiver o;
  private PopupWindow p;
  private c q;
  private LinearLayout r;
  private TextView s;
  private TextView t;
  private View u;
  private LinearLayout v;
  private FrameLayout w;
  private b.a.a.a.a.c.a<Void, Void, Map<String, Drawable>> x = new b.a.a.a.a.c.a()
  {
    protected Map<String, Drawable> a(Void... paramAnonymousVarArgs)
    {
      return HeadsetActivity.a(HeadsetActivity.this);
    }
    
    protected void a(Map<String, Drawable> paramAnonymousMap)
    {
      HeadsetActivity.a(HeadsetActivity.this, paramAnonymousMap);
    }
  };
  private final int y = 5;
  
  public HeadsetActivity() {}
  
  private void a(Map<String, Drawable> paramMap)
  {
    if (!paramMap.isEmpty())
    {
      this.r.setVisibility(0);
      Iterator localIterator = paramMap.entrySet().iterator();
      int i = 0;
      while ((i < paramMap.size()) && (localIterator.hasNext()))
      {
        Object localObject = (Map.Entry)localIterator.next();
        Drawable localDrawable = (Drawable)((Map.Entry)localObject).getValue();
        localObject = (String)((Map.Entry)localObject).getKey();
        ImageView localImageView = (ImageView)this.r.getChildAt(i);
        localImageView.setVisibility(0);
        localImageView.setImageDrawable(localDrawable);
        localImageView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = HeadsetActivity.this.getPackageManager().getLaunchIntentForPackage(this.a);
            HeadsetActivity.this.startActivity(paramAnonymousView);
          }
        });
        i += 1;
      }
    }
    this.r.setVisibility(8);
  }
  
  private void k()
  {
    Object localObject = (LinearLayout)findViewById(a.i.headset_activity);
    this.m = ((SeekBar)findViewById(a.i.volume_seek_bar));
    if (Build.VERSION.SDK_INT >= 16)
    {
      Drawable localDrawable = android.support.v4.b.a.a.f(this.m.getThumb());
      android.support.v4.b.a.a.a(localDrawable, android.support.v4.a.a.c(this, 17170443));
      this.m.setThumb(android.support.v4.b.a.a.g(localDrawable));
    }
    this.n = ((AudioManager)getSystemService("audio"));
    int i = this.n.getStreamMaxVolume(3);
    this.m.setMax(i);
    i = this.n.getStreamVolume(3);
    this.m.setProgress(i);
    this.m.setOnSeekBarChangeListener(this);
    ((LinearLayout)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView) {}
    });
    findViewById(a.i.headset_menu).setOnClickListener(this);
    findViewById(a.i.close).setOnClickListener(this);
    this.r = ((LinearLayout)findViewById(a.i.installed_app));
    this.w = ((FrameLayout)findViewById(a.i.ad_container));
    this.v = ((LinearLayout)findViewById(a.i.no_adView));
    this.s = ((TextView)findViewById(a.i.musicRemain));
    this.t = ((TextView)findViewById(a.i.movieRemain));
    this.u = LayoutInflater.from(this).inflate(a.k.headset_layout, null);
    localObject = (BatteryManager)getSystemService("batterymanager");
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.s.setText(String.valueOf(((BatteryManager)localObject).getIntProperty(4) * 5));
      this.t.setText(String.valueOf(((BatteryManager)localObject).getIntProperty(4) * 3));
    }
  }
  
  private void l()
  {
    if (((com.ihs.keyboardutils.c.a.a().b() ^ true) & (TextUtils.isEmpty(a.a().b()) ^ true)))
    {
      this.q = new c(this, a.a().b());
      this.q.setAutoSwitchAd(3);
      this.q.setGravity(17);
      this.q.setExpressAdViewListener(new c.a()
      {
        public void a(c paramAnonymousC)
        {
          com.f.b.a.a.c(a.a().b());
        }
        
        public void b(c paramAnonymousC)
        {
          HeadsetActivity.b(HeadsetActivity.this).setVisibility(8);
          com.f.b.a.a.b(a.a().b());
        }
      });
      this.w.addView(this.q, -1, -1);
    }
  }
  
  private List<String> m()
  {
    return (ArrayList)com.ihs.commons.config.a.d(new String[] { "Application", "RemoteAppPackageName" });
  }
  
  private Map<String, Drawable> n()
  {
    Object localObject = new ArrayList();
    HashMap localHashMap = new HashMap();
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      ((List)localObject).add(((PackageInfo)localList.get(i)).packageName);
      i += 1;
    }
    if ((!((List)localObject).isEmpty()) && (!m().isEmpty()))
    {
      localObject = ((List)localObject).iterator();
      localList = m();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        String str = (String)((Iterator)localObject).next();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
          if (str.contains((String)localIterator.next())) {
            try
            {
              if (localPackageManager.getLaunchIntentForPackage(str) != null)
              {
                PackageInfo localPackageInfo = localPackageManager.getPackageInfo(str, 0);
                if (localPackageInfo != null) {
                  localHashMap.put(str, localPackageInfo.applicationInfo.loadIcon(localPackageManager));
                }
              }
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              localNameNotFoundException.printStackTrace();
            }
          }
        }
      } while (localHashMap.size() != 5);
    }
    return localHashMap;
  }
  
  private void p()
  {
    if ((this.p.isShowing() & (isFinishing() ^ true))) {
      this.p.dismiss();
    }
  }
  
  private void q()
  {
    if (this.p == null)
    {
      View localView = LayoutInflater.from(this).inflate(a.k.headset_disable_suggestion_pop, null);
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      this.p = new PopupWindow(localView, (int)(localDisplayMetrics.widthPixels * 0.8D), -2);
      this.p.setFocusable(true);
      this.p.setContentView(localView);
      this.p.setBackgroundDrawable(new BitmapDrawable());
      this.p.setOutsideTouchable(true);
      this.p.setTouchable(true);
      localView.findViewById(a.i.headset_disable).setOnClickListener(this);
      localView.findViewById(a.i.close_pop_window).setOnClickListener(this);
    }
    if ((!isFinishing()) && (!this.p.isShowing())) {
      this.p.showAtLocation(this.u, 17, 0, 0);
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getKeyCode() == 25)
    {
      this.n.adjustStreamVolume(3, -1, 0);
      return true;
    }
    if (paramKeyEvent.getKeyCode() == 24)
    {
      this.n.adjustStreamVolume(3, 1, 0);
      return true;
    }
    if (paramKeyEvent.getKeyCode() == 4)
    {
      if ((this.p != null) && (this.p.isShowing())) {
        this.p.dismiss();
      }
      return super.dispatchKeyEvent(paramKeyEvent);
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i == a.i.headset_menu)
    {
      q();
      return;
    }
    if (i == a.i.close)
    {
      finish();
      return;
    }
    if (i == a.i.headset_disable)
    {
      a.a().a(false);
      p();
      return;
    }
    if (i == a.i.close_pop_window) {
      p();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(a.k.headset_layout);
    this.o = new a(null);
    paramBundle = new IntentFilter();
    paramBundle.addAction("android.media.VOLUME_CHANGED_ACTION");
    paramBundle.addAction("android.intent.action.BATTERY_CHANGED");
    paramBundle.addAction("android.media.AUDIO_BECOMING_NOISY");
    registerReceiver(this.o, paramBundle);
    k();
    l();
    com.f.b.a.a.a(a.a().b(), "Headset");
    this.x.a(b.a.a.a.a.c.a.b, new Void[0]);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.o);
    if (this.q != null)
    {
      this.q.b();
      this.q = null;
    }
    this.x.a(true);
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.n.setStreamVolume(3, paramInt, 0);
      paramSeekBar.setProgress(this.n.getStreamVolume(3));
    }
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar) {}
  
  public void onStopTrackingTouch(SeekBar paramSeekBar) {}
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    finish();
    return true;
  }
  
  private class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = new StringBuilder();
      paramContext.append("HeadsetReceiver  onReceive  headset==========");
      paramContext.append(paramIntent.getAction());
      f.b("HeadsetActivity", paramContext.toString());
      paramContext = paramIntent.getAction();
      int i;
      if (paramContext.equals("android.media.VOLUME_CHANGED_ACTION"))
      {
        i = HeadsetActivity.c(HeadsetActivity.this).getStreamVolume(3);
        HeadsetActivity.d(HeadsetActivity.this).setProgress(i);
        return;
      }
      if (paramContext.equals("android.intent.action.BATTERY_CHANGED"))
      {
        i = paramIntent.getExtras().getInt("level") * 100 / paramIntent.getExtras().getInt("scale");
        HeadsetActivity.e(HeadsetActivity.this).setText(String.valueOf(i * 5));
        HeadsetActivity.f(HeadsetActivity.this).setText(String.valueOf(i * 3));
        return;
      }
      if (paramContext.equals("android.media.AUDIO_BECOMING_NOISY")) {
        HeadsetActivity.this.finish();
      }
    }
  }
}
