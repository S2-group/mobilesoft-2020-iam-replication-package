package com.lionmobi.battery.activity.a;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.MediaView;
import com.facebook.ads.b;
import com.facebook.ads.k;
import com.facebook.ads.k.a;
import com.facebook.ads.k.b;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.formats.c.a;
import com.lionmobi.battery.PBApplication;
import com.lionmobi.battery.activity.QuickChargingActivity;
import com.lionmobi.battery.model.a.aa;
import com.lionmobi.battery.util.o;
import com.lionmobi.battery.util.r;
import com.lionmobi.battery.util.v;
import com.lionmobi.battery.view.a.g;
import com.lionmobi.battery.view.a.g.b;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
public final class d
  extends Fragment
  implements g.b
{
  View a;
  private List<com.lionmobi.battery.bean.c> aj;
  private boolean ak = false;
  private Context al;
  private List<k> am = null;
  private int an = 0;
  private View ao;
  private List<com.lionmobi.battery.bean.n> ap;
  private List<com.lionmobi.battery.bean.n> aq;
  private AdChoicesView ar;
  private FrameLayout as;
  private List<String> at;
  private int au = 0;
  private long av = 0L;
  private RelativeLayout aw;
  private View ax;
  private k ay;
  Handler b = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        if (d.n(d.this).size() != 0) {
          break;
        }
        if ((d.this.getActivity() != null) && (!d.this.getActivity().isFinishing())) {
          d.o(d.this).setVisibility(8);
        }
      }
      int i = 0;
      for (;;)
      {
        try
        {
          if (i >= d.n(d.this).size()) {
            break label189;
          }
          if ((d.n(d.this).get(i) == null) || (!((com.lionmobi.battery.bean.n)d.n(d.this).get(i)).isClearable())) {
            break label167;
          }
          i = 1;
          if (i == 0) {
            break label174;
          }
          d.o(d.this).setVisibility(0);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        break;
        label167:
        i += 1;
        continue;
        label174:
        d.o(d.this).setVisibility(8);
        break;
        label189:
        i = 0;
      }
    }
  };
  private aa c;
  private c d = new c();
  private ListView e;
  private long f = 0L;
  private b g;
  private View h;
  private List<com.lionmobi.battery.bean.c> i;
  
  public d() {}
  
  private static com.lionmobi.battery.bean.n a(StatusBarNotification paramStatusBarNotification)
  {
    com.lionmobi.battery.bean.n localN = new com.lionmobi.battery.bean.n();
    localN.setNotificationTitle(paramStatusBarNotification.getNotification().extras.getString("android.title"));
    localN.setNotificationContent(String.valueOf(paramStatusBarNotification.getNotification().extras.getCharSequence("android.text")));
    try
    {
      Bitmap localBitmap = (Bitmap)paramStatusBarNotification.getNotification().extras.getParcelable("android.largeIcon");
      if (localBitmap != null)
      {
        localN.setNotificationIcon((Bitmap)paramStatusBarNotification.getNotification().extras.getParcelable("android.largeIcon"));
        localN.setIsClearable(paramStatusBarNotification.isClearable());
        localN.setPendingIntent(paramStatusBarNotification.getNotification().contentIntent);
        localN.setNotification(paramStatusBarNotification);
        return localN;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject = null;
        continue;
        localN.setPackageName(paramStatusBarNotification.getPackageName());
      }
    }
  }
  
  /* Error */
  private void a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 195	com/lionmobi/battery/activity/a/d:at	Ljava/util/List;
    //   4: invokeinterface 201 1 0
    //   9: istore_2
    //   10: iload_1
    //   11: iload_2
    //   12: if_icmplt +4 -> 16
    //   15: return
    //   16: aload_0
    //   17: getfield 195	com/lionmobi/battery/activity/a/d:at	Ljava/util/List;
    //   20: iload_1
    //   21: invokeinterface 205 2 0
    //   26: checkcast 142	java/lang/String
    //   29: astore_3
    //   30: ldc -49
    //   32: aload_3
    //   33: invokevirtual 211	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   36: ifeq +30 -> 66
    //   39: invokestatic 217	java/lang/System:currentTimeMillis	()J
    //   42: aload_0
    //   43: getfield 90	com/lionmobi/battery/activity/a/d:f	J
    //   46: lsub
    //   47: ldc2_w 218
    //   50: lcmp
    //   51: ifle -36 -> 15
    //   54: aload_0
    //   55: invokespecial 221	com/lionmobi/battery/activity/a/d:b	()V
    //   58: aload_0
    //   59: invokestatic 217	java/lang/System:currentTimeMillis	()J
    //   62: putfield 90	com/lionmobi/battery/activity/a/d:f	J
    //   65: return
    //   66: ldc -33
    //   68: aload_3
    //   69: invokevirtual 211	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   72: ifeq +97 -> 169
    //   75: invokestatic 217	java/lang/System:currentTimeMillis	()J
    //   78: aload_0
    //   79: getfield 103	com/lionmobi/battery/activity/a/d:av	J
    //   82: lsub
    //   83: ldc2_w 224
    //   86: lcmp
    //   87: ifle -72 -> 15
    //   90: aload_0
    //   91: invokevirtual 229	com/lionmobi/battery/activity/a/d:getActivity	()Landroid/support/v4/app/n;
    //   94: ifnull +67 -> 161
    //   97: new 231	com/google/android/gms/ads/b$a
    //   100: dup
    //   101: aload_0
    //   102: invokevirtual 229	com/lionmobi/battery/activity/a/d:getActivity	()Landroid/support/v4/app/n;
    //   105: ldc -23
    //   107: invokespecial 236	com/google/android/gms/ads/b$a:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   110: astore_3
    //   111: aload_3
    //   112: new 20	com/lionmobi/battery/activity/a/d$7
    //   115: dup
    //   116: aload_0
    //   117: invokespecial 237	com/lionmobi/battery/activity/a/d$7:<init>	(Lcom/lionmobi/battery/activity/a/d;)V
    //   120: invokevirtual 241	com/google/android/gms/ads/b$a:forAppInstallAd	(Lcom/google/android/gms/ads/formats/c$a;)Lcom/google/android/gms/ads/b$a;
    //   123: pop
    //   124: aload_3
    //   125: new 22	com/lionmobi/battery/activity/a/d$8
    //   128: dup
    //   129: aload_0
    //   130: invokespecial 242	com/lionmobi/battery/activity/a/d$8:<init>	(Lcom/lionmobi/battery/activity/a/d;)V
    //   133: invokevirtual 246	com/google/android/gms/ads/b$a:forContentAd	(Lcom/google/android/gms/ads/formats/d$a;)Lcom/google/android/gms/ads/b$a;
    //   136: pop
    //   137: aload_3
    //   138: new 24	com/lionmobi/battery/activity/a/d$9
    //   141: dup
    //   142: aload_0
    //   143: invokespecial 247	com/lionmobi/battery/activity/a/d$9:<init>	(Lcom/lionmobi/battery/activity/a/d;)V
    //   146: invokevirtual 251	com/google/android/gms/ads/b$a:withAdListener	(Lcom/google/android/gms/ads/a;)Lcom/google/android/gms/ads/b$a;
    //   149: invokevirtual 255	com/google/android/gms/ads/b$a:build	()Lcom/google/android/gms/ads/b;
    //   152: invokestatic 261	com/lionmobi/battery/util/x:getAdRequestBuilder	()Lcom/google/android/gms/ads/c$a;
    //   155: invokevirtual 266	com/google/android/gms/ads/c$a:build	()Lcom/google/android/gms/ads/c;
    //   158: invokevirtual 272	com/google/android/gms/ads/b:loadAd	(Lcom/google/android/gms/ads/c;)V
    //   161: aload_0
    //   162: invokestatic 217	java/lang/System:currentTimeMillis	()J
    //   165: putfield 103	com/lionmobi/battery/activity/a/d:av	J
    //   168: return
    //   169: ldc_w 274
    //   172: aload_3
    //   173: invokevirtual 211	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   176: ifeq +36 -> 212
    //   179: aload_0
    //   180: getfield 276	com/lionmobi/battery/activity/a/d:aw	Landroid/widget/RelativeLayout;
    //   183: ifnull +12 -> 195
    //   186: aload_0
    //   187: getfield 276	com/lionmobi/battery/activity/a/d:aw	Landroid/widget/RelativeLayout;
    //   190: bipush 8
    //   192: invokevirtual 281	android/widget/RelativeLayout:setVisibility	(I)V
    //   195: aload_0
    //   196: getfield 283	com/lionmobi/battery/activity/a/d:as	Landroid/widget/FrameLayout;
    //   199: ifnull -184 -> 15
    //   202: aload_0
    //   203: getfield 283	com/lionmobi/battery/activity/a/d:as	Landroid/widget/FrameLayout;
    //   206: bipush 8
    //   208: invokevirtual 286	android/widget/FrameLayout:setVisibility	(I)V
    //   211: return
    //   212: invokestatic 217	java/lang/System:currentTimeMillis	()J
    //   215: aload_0
    //   216: getfield 90	com/lionmobi/battery/activity/a/d:f	J
    //   219: lsub
    //   220: ldc2_w 218
    //   223: lcmp
    //   224: ifle -209 -> 15
    //   227: aload_0
    //   228: invokespecial 221	com/lionmobi/battery/activity/a/d:b	()V
    //   231: aload_0
    //   232: invokestatic 217	java/lang/System:currentTimeMillis	()J
    //   235: putfield 90	com/lionmobi/battery/activity/a/d:f	J
    //   238: return
    //   239: astore_3
    //   240: return
    //   241: astore_3
    //   242: ldc -49
    //   244: astore_3
    //   245: goto -215 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	248	0	this	d
    //   0	248	1	paramInt	int
    //   9	4	2	j	int
    //   29	144	3	localObject	Object
    //   239	1	3	localException1	Exception
    //   241	1	3	localException2	Exception
    //   244	1	3	str	String
    // Exception table:
    //   from	to	target	type
    //   0	10	239	java/lang/Exception
    //   30	65	239	java/lang/Exception
    //   66	161	239	java/lang/Exception
    //   161	168	239	java/lang/Exception
    //   169	195	239	java/lang/Exception
    //   195	211	239	java/lang/Exception
    //   212	238	239	java/lang/Exception
    //   16	30	241	java/lang/Exception
  }
  
  private void b()
  {
    this.ay = new k(getActivity(), "505866779563272_588796867936929");
    this.ay.setAdListener(new a());
    this.ay.loadAd(k.b.e);
  }
  
  public static boolean isEnabled(Context paramContext)
  {
    boolean bool2 = false;
    String str = paramContext.getPackageName();
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    boolean bool1 = bool2;
    int j;
    if (!TextUtils.isEmpty(paramContext))
    {
      paramContext = paramContext.split(":");
      j = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (j < paramContext.length)
      {
        ComponentName localComponentName = ComponentName.unflattenFromString(paramContext[j]);
        if ((localComponentName != null) && (TextUtils.equals(str, localComponentName.getPackageName()))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      j += 1;
    }
  }
  
  public final void animationSettingsIcon()
  {
    if ((this.c != null) && (!isEnabled(getActivity()))) {}
    try
    {
      this.c.iconAnimation();
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void cancelBroadcastReceiver()
  {
    if (getActivity() != null) {}
    try
    {
      getActivity().unregisterReceiver(this.d);
      return;
    }
    catch (Exception localException) {}
  }
  
  public final List<com.lionmobi.battery.bean.c> getInstalledApp()
  {
    this.i = new ArrayList();
    this.aj = new ArrayList();
    String str = r.getLocalSettingShared(this.al).getString("notification_filter_app", "");
    List localList = this.al.getPackageManager().getInstalledApplications(8192);
    int j = 0;
    for (;;)
    {
      if (j < localList.size())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(j);
        com.lionmobi.battery.bean.c localC;
        if (((localApplicationInfo.flags & 0x1) == 0) || ("com.tencent.mobileqq".equalsIgnoreCase(localApplicationInfo.packageName)))
        {
          localC = new com.lionmobi.battery.bean.c();
          localC.a = localApplicationInfo.loadLabel(this.al.getPackageManager()).toString();
          localC.b = localApplicationInfo.packageName;
        }
        try
        {
          localC.c = localApplicationInfo.loadIcon(this.al.getPackageManager());
          if (str.contains(localApplicationInfo.packageName))
          {
            localC.d = true;
            this.aj.add(localC);
            this.i.add(0, localC);
            j += 1;
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            localC.c = null;
            continue;
            this.i.add(localC);
          }
        }
      }
    }
    return this.i;
  }
  
  public final void getPriorityAd()
  {
    this.au = 0;
    a(this.au);
  }
  
  public final void inflateAd(k paramK, View paramView)
  {
    try
    {
      Object localObject2 = (TextView)paramView.findViewById(2131362103);
      Object localObject3 = (TextView)paramView.findViewById(2131362104);
      Object localObject1 = (MediaView)paramView.findViewById(2131362101);
      Button localButton = (Button)paramView.findViewById(2131362105);
      FrameLayout localFrameLayout = (FrameLayout)paramView.findViewById(2131362100);
      localButton.setText(paramK.getAdCallToAction());
      localButton.setVisibility(0);
      ((TextView)localObject2).setText(paramK.getAdTitle());
      ((TextView)localObject3).setText(paramK.getAdBody());
      localObject2 = paramK.getAdCoverImage();
      int j = ((k.a)localObject2).getWidth();
      int k = ((k.a)localObject2).getHeight();
      localObject2 = ((WindowManager)getActivity().getSystemService("window")).getDefaultDisplay();
      localObject3 = new DisplayMetrics();
      ((Display)localObject2).getMetrics((DisplayMetrics)localObject3);
      int m = ((DisplayMetrics)localObject3).widthPixels - v.dpToPx(getActivity(), 16);
      int n = ((DisplayMetrics)localObject3).heightPixels;
      ((MediaView)localObject1).setLayoutParams(new FrameLayout.LayoutParams(m, Math.min((int)(m / j * k), n / 3)));
      ((MediaView)localObject1).setNativeAd(paramK);
      if (this.ar == null)
      {
        this.ar = new AdChoicesView(getActivity(), paramK, true);
        localObject1 = new FrameLayout.LayoutParams(v.dpToPx(getActivity(), 24), v.dpToPx(getActivity(), 24));
        ((FrameLayout.LayoutParams)localObject1).gravity = 53;
        localFrameLayout.addView(this.ar, (ViewGroup.LayoutParams)localObject1);
      }
      paramK.registerViewForInteraction(paramView);
      return;
    }
    catch (Exception paramK) {}
  }
  
  public final void init(Context paramContext, b paramB)
  {
    this.g = paramB;
    this.al = paramContext;
    new Thread(new Runnable()
    {
      public final void run()
      {
        d.a(d.this, d.this.getInstalledApp());
        if ((d.a(d.this) != null) && (d.a(d.this).size() > 0)) {
          d.b(d.this);
        }
      }
    }).start();
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = new IntentFilter();
    paramBundle.addAction("com.lionmobi.battery.ACTION_GET_NOTIFICATION_COMPLETE");
    paramBundle.addAction("notification_open");
    getActivity().registerReceiver(this.d, paramBundle);
  }
  
  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.ap = new ArrayList();
    this.a = paramLayoutInflater.inflate(2130903294, paramViewGroup, false);
    this.ao = this.a.findViewById(2131363026);
    this.ao.setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        d.this.getActivity().sendBroadcast(new Intent("com.lionmobi.battery.ACTION_DELETE_ALL_NOTIFICATION"));
      }
    });
    this.a.findViewById(2131363022).setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (d.c(d.this) != null) {
          d.c(d.this).goBack();
        }
      }
    });
    this.h = this.a.findViewById(2131363023);
    if (!isEnabled(getActivity())) {
      this.h.setVisibility(8);
    }
    this.h.setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (d.d(d.this)) {
          new g(d.this.getActivity(), d.a(d.this), d.e(d.this), d.this).show();
        }
      }
    });
    this.e = ((ListView)this.a.findViewById(2131363027));
    this.c = new aa(getActivity(), this.ap);
    this.e.setAdapter(this.c);
    if (getActivity() != null) {}
    try
    {
      this.at = o.initInstance(getActivity(), (PBApplication)getActivity().getApplication()).getPriorityList(getActivity(), "QUICK_CHARGING_NOTIFICATION");
      if ((this.at == null) || (this.at.size() == 0))
      {
        this.at = new ArrayList();
        this.at.add("facebook");
        this.at.add("admob");
      }
      this.aw = ((RelativeLayout)this.a.findViewById(2131361916));
      this.ax = ((LayoutInflater)getActivity().getSystemService("layout_inflater")).inflate(2130903250, this.aw);
      return this.a;
    }
    catch (Exception paramLayoutInflater)
    {
      for (;;)
      {
        paramLayoutInflater.printStackTrace();
      }
    }
  }
  
  public final void onDestroy()
  {
    if ((getActivity() != null) && (!getActivity().isFinishing())) {}
    try
    {
      getActivity().unregisterReceiver(this.d);
      super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public final void onResume()
  {
    super.onResume();
    FlurryAgent.logEvent("QuichChargePage_Notification");
    if (isEnabled(getActivity()))
    {
      FlurryAgent.logEvent("充电消息通知-已打开授权展示");
      this.h.setVisibility(0);
      if ((this.aq == null) || (this.aq.size() == 0))
      {
        this.ao.setVisibility(8);
        if ((this.ap != null) && (this.ap.size() > 0) && (((com.lionmobi.battery.bean.n)this.ap.get(0)).isSettings())) {
          this.ap.remove(0);
        }
        this.c.notifyDataSetChanged();
        getActivity().sendBroadcast(new Intent("com.lionmobi.battery.ACTION_LIST_NOTIFICATION"));
        label137:
        return;
      }
      j = 0;
      label140:
      if (j >= this.aq.size()) {
        break label259;
      }
      if (!((com.lionmobi.battery.bean.n)this.aq.get(j)).isClearable()) {}
    }
    label259:
    for (int j = 1;; j = 0)
    {
      if (j != 0)
      {
        this.ao.setVisibility(0);
        break;
        j += 1;
        break label140;
      }
      this.ao.setVisibility(8);
      break;
      FlurryAgent.logEvent("充电消息通知-未打开授权展示");
      com.lionmobi.battery.bean.n localN = new com.lionmobi.battery.bean.n();
      localN.setIsSettings(true);
      if (this.ap.size() != 0) {
        break label137;
      }
      this.ap.add(0, localN);
      this.c.notifyDataSetChanged();
      return;
    }
  }
  
  public final void sortApp()
  {
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    if (j < this.i.size())
    {
      if (((com.lionmobi.battery.bean.c)this.i.get(j)).d) {
        localArrayList.add(0, this.i.get(j));
      }
      for (;;)
      {
        j += 1;
        break;
        localArrayList.add(this.i.get(j));
      }
    }
    this.i = localArrayList;
    getActivity().sendBroadcast(new Intent("com.lionmobi.battery.ACTION_LIST_NOTIFICATION"));
  }
  
  final class a
    implements com.facebook.ads.c
  {
    a() {}
    
    public final void onAdClicked(com.facebook.ads.a paramA)
    {
      try
      {
        ((QuickChargingActivity)d.this.getActivity()).p.updateUnLockTime(1);
        return;
      }
      catch (Exception paramA) {}
    }
    
    public final void onAdLoaded(com.facebook.ads.a paramA)
    {
      if ((d.h(d.this) == null) || (d.h(d.this) != paramA)) {}
      while (d.i(d.this) == null) {
        return;
      }
      d.i(d.this).setVisibility(0);
      if (d.j(d.this) != null) {
        d.j(d.this).setVisibility(8);
      }
      d.h(d.this).unregisterView();
      d.this.inflateAd(d.h(d.this), d.k(d.this));
      d.h(d.this).setOnTouchListener(new View.OnTouchListener()
      {
        public final boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            paramAnonymousView.getId();
          }
          return false;
        }
      });
    }
    
    public final void onError(com.facebook.ads.a paramA, b paramB)
    {
      try
      {
        if (!d.this.getActivity().isFinishing())
        {
          d.l(d.this);
          d.a(d.this, d.m(d.this));
        }
        return;
      }
      catch (Exception paramA) {}
    }
  }
  
  public static abstract interface b
  {
    public abstract void goBack();
  }
  
  final class c
    extends BroadcastReceiver
  {
    c() {}
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("notification_open".equals(paramIntent.getAction()))
      {
        if (d.f(d.this) != null) {
          d.f(d.this).closeSettingPage();
        }
        return;
      }
      d.g(d.this);
    }
  }
}
