package com.lionmobi.powerclean.d;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.i;
import com.facebook.ads.k;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.formats.f;
import com.google.android.gms.ads.formats.h;
import com.lionmobi.powerclean.ApplicationEx;
import com.lionmobi.powerclean.activity.QuickChargingActivity;
import com.lionmobi.powerclean.e.x;
import com.lionmobi.powerclean.model.adapter.aj;
import com.lionmobi.powerclean.model.b.dm;
import com.lionmobi.powerclean.model.bean.w;
import com.lionmobi.powerclean.quietnotifications.b;
import com.lionmobi.powerclean.view.a.az;
import com.lionmobi.powerclean.view.a.bb;
import com.lionmobi.powerclean.view.a.bk;
import de.greenrobot.event.c;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class af
  extends Fragment
  implements bb, bk
{
  View a;
  private String aA = "boost_charge_notificaiton_animed";
  private RelativeLayout aB;
  private View aC;
  private i aD;
  private List aE;
  private int aF;
  private long aG = 0L;
  private List aj;
  private long ak = 0L;
  private ah al;
  private View am;
  private List an;
  private List ao;
  private boolean ap = false;
  private Context aq;
  private View ar;
  private View as;
  private ImageView at;
  private ImageView au;
  private boolean av = true;
  private List aw = null;
  private int ax = 0;
  private ApplicationEx ay;
  private com.lionmobi.util.g az;
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
        if ((af.i(af.this).size() == 0) && (af.this.getActivity() != null)) {
          af.this.getActivity().isFinishing();
        }
      }
    }
  };
  FrameLayout c;
  private aj d;
  private ai e = new ai(this);
  private ListView f;
  private View g;
  private TextView h;
  private List i;
  
  public af() {}
  
  /* Error */
  private void a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 122	com/lionmobi/powerclean/d/af:aE	Ljava/util/List;
    //   4: invokeinterface 128 1 0
    //   9: istore_2
    //   10: iload_1
    //   11: iload_2
    //   12: if_icmplt +4 -> 16
    //   15: return
    //   16: aload_0
    //   17: getfield 122	com/lionmobi/powerclean/d/af:aE	Ljava/util/List;
    //   20: iload_1
    //   21: invokeinterface 132 2 0
    //   26: checkcast 134	java/lang/String
    //   29: astore_3
    //   30: ldc -120
    //   32: aload_3
    //   33: invokevirtual 140	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   36: ifeq +34 -> 70
    //   39: invokestatic 146	java/lang/System:currentTimeMillis	()J
    //   42: aload_0
    //   43: getfield 95	com/lionmobi/powerclean/d/af:ak	J
    //   46: lsub
    //   47: ldc2_w 147
    //   50: lcmp
    //   51: ifle -36 -> 15
    //   54: aload_0
    //   55: invokespecial 150	com/lionmobi/powerclean/d/af:c	()V
    //   58: aload_0
    //   59: invokespecial 152	com/lionmobi/powerclean/d/af:d	()V
    //   62: aload_0
    //   63: invokestatic 146	java/lang/System:currentTimeMillis	()J
    //   66: putfield 95	com/lionmobi/powerclean/d/af:ak	J
    //   69: return
    //   70: ldc -102
    //   72: aload_3
    //   73: invokevirtual 140	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   76: ifeq +101 -> 177
    //   79: invokestatic 146	java/lang/System:currentTimeMillis	()J
    //   82: aload_0
    //   83: getfield 112	com/lionmobi/powerclean/d/af:aG	J
    //   86: lsub
    //   87: ldc2_w 155
    //   90: lcmp
    //   91: ifle -76 -> 15
    //   94: new 158	com/google/android/gms/ads/c
    //   97: dup
    //   98: aload_0
    //   99: invokevirtual 162	com/lionmobi/powerclean/d/af:getActivity	()Landroid/support/v4/app/j;
    //   102: aload_0
    //   103: invokevirtual 162	com/lionmobi/powerclean/d/af:getActivity	()Landroid/support/v4/app/j;
    //   106: invokevirtual 168	android/support/v4/app/j:getApplicationContext	()Landroid/content/Context;
    //   109: ldc -86
    //   111: iconst_0
    //   112: invokestatic 176	com/lionmobi/util/a/a:getAdmobID	(Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   115: invokespecial 179	com/google/android/gms/ads/c:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   118: astore_3
    //   119: aload_3
    //   120: new 16	com/lionmobi/powerclean/d/af$2
    //   123: dup
    //   124: aload_0
    //   125: invokespecial 180	com/lionmobi/powerclean/d/af$2:<init>	(Lcom/lionmobi/powerclean/d/af;)V
    //   128: invokevirtual 184	com/google/android/gms/ads/c:forAppInstallAd	(Lcom/google/android/gms/ads/formats/f;)Lcom/google/android/gms/ads/c;
    //   131: pop
    //   132: aload_3
    //   133: new 18	com/lionmobi/powerclean/d/af$3
    //   136: dup
    //   137: aload_0
    //   138: invokespecial 185	com/lionmobi/powerclean/d/af$3:<init>	(Lcom/lionmobi/powerclean/d/af;)V
    //   141: invokevirtual 189	com/google/android/gms/ads/c:forContentAd	(Lcom/google/android/gms/ads/formats/h;)Lcom/google/android/gms/ads/c;
    //   144: pop
    //   145: aload_3
    //   146: new 20	com/lionmobi/powerclean/d/af$4
    //   149: dup
    //   150: aload_0
    //   151: invokespecial 190	com/lionmobi/powerclean/d/af$4:<init>	(Lcom/lionmobi/powerclean/d/af;)V
    //   154: invokevirtual 194	com/google/android/gms/ads/c:withAdListener	(Lcom/google/android/gms/ads/a;)Lcom/google/android/gms/ads/c;
    //   157: invokevirtual 198	com/google/android/gms/ads/c:build	()Lcom/google/android/gms/ads/b;
    //   160: invokestatic 204	com/lionmobi/powerclean/a/b:getAdRequestBuilder	()Lcom/google/android/gms/ads/e;
    //   163: invokevirtual 209	com/google/android/gms/ads/e:build	()Lcom/google/android/gms/ads/d;
    //   166: invokevirtual 215	com/google/android/gms/ads/b:loadAd	(Lcom/google/android/gms/ads/d;)V
    //   169: aload_0
    //   170: invokestatic 146	java/lang/System:currentTimeMillis	()J
    //   173: putfield 112	com/lionmobi/powerclean/d/af:aG	J
    //   176: return
    //   177: ldc -39
    //   179: aload_3
    //   180: invokevirtual 140	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   183: ifeq +36 -> 219
    //   186: aload_0
    //   187: getfield 219	com/lionmobi/powerclean/d/af:aB	Landroid/widget/RelativeLayout;
    //   190: ifnull +12 -> 202
    //   193: aload_0
    //   194: getfield 219	com/lionmobi/powerclean/d/af:aB	Landroid/widget/RelativeLayout;
    //   197: bipush 8
    //   199: invokevirtual 224	android/widget/RelativeLayout:setVisibility	(I)V
    //   202: aload_0
    //   203: getfield 226	com/lionmobi/powerclean/d/af:c	Landroid/widget/FrameLayout;
    //   206: ifnull -191 -> 15
    //   209: aload_0
    //   210: getfield 226	com/lionmobi/powerclean/d/af:c	Landroid/widget/FrameLayout;
    //   213: bipush 8
    //   215: invokevirtual 229	android/widget/FrameLayout:setVisibility	(I)V
    //   218: return
    //   219: invokestatic 146	java/lang/System:currentTimeMillis	()J
    //   222: aload_0
    //   223: getfield 95	com/lionmobi/powerclean/d/af:ak	J
    //   226: lsub
    //   227: ldc2_w 147
    //   230: lcmp
    //   231: ifle -216 -> 15
    //   234: aload_0
    //   235: invokespecial 150	com/lionmobi/powerclean/d/af:c	()V
    //   238: aload_0
    //   239: invokespecial 152	com/lionmobi/powerclean/d/af:d	()V
    //   242: aload_0
    //   243: invokestatic 146	java/lang/System:currentTimeMillis	()J
    //   246: putfield 95	com/lionmobi/powerclean/d/af:ak	J
    //   249: return
    //   250: astore_3
    //   251: return
    //   252: astore_3
    //   253: ldc -120
    //   255: astore_3
    //   256: goto -226 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	this	af
    //   0	259	1	paramInt	int
    //   9	4	2	j	int
    //   29	151	3	localObject	Object
    //   250	1	3	localException1	Exception
    //   252	1	3	localException2	Exception
    //   255	1	3	str	String
    // Exception table:
    //   from	to	target	type
    //   0	10	250	java/lang/Exception
    //   30	69	250	java/lang/Exception
    //   70	176	250	java/lang/Exception
    //   177	202	250	java/lang/Exception
    //   202	218	250	java/lang/Exception
    //   219	249	250	java/lang/Exception
    //   16	30	252	java/lang/Exception
  }
  
  private void c()
  {
    try
    {
      this.aB = ((RelativeLayout)this.g.findViewById(2131427342));
      this.aC = ((LayoutInflater)getActivity().getSystemService("layout_inflater")).inflate(2130903169, this.aB);
      return;
    }
    catch (Exception localException) {}
  }
  
  private void d()
  {
    try
    {
      this.aD = new i(getActivity(), com.lionmobi.util.a.a.getFBPID(getActivity().getApplicationContext(), "1539547886295207_1657298547853473", 0));
      this.aD.setAdListener(new ag(this));
      this.aD.loadAd(k.d);
      return;
    }
    catch (Exception localException) {}
  }
  
  private boolean e()
  {
    try
    {
      int j = ((QuickChargingActivity)getActivity()).getCurrentIndex();
      if (j == 2) {
        return true;
      }
    }
    catch (Exception localException)
    {
      return false;
    }
    return false;
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
        if (localComponentName != null)
        {
          System.out.println("pkgName = " + str + "; cn.pkgname = " + localComponentName.getPackageName());
          if (TextUtils.equals(str, localComponentName.getPackageName())) {
            bool1 = true;
          }
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
    if ((this.d != null) && (!isEnabled(getActivity()))) {}
    try
    {
      this.d.iconAnimation();
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void cancelBroadcastReceiver()
  {
    if (getActivity() != null) {}
    try
    {
      if (this.e != null)
      {
        getActivity().unregisterReceiver(this.e);
        this.e = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final List getInstalledApp()
  {
    int j = 0;
    this.an = new ArrayList();
    this.ao = new ArrayList();
    String str = ((ApplicationEx)this.aq.getApplicationContext()).getGlobalSettingPreference().getString("notification_filter_app", "");
    List localList = this.aq.getPackageManager().getInstalledPackages(0);
    for (;;)
    {
      try
      {
        if (j < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(j);
          if (((localPackageInfo.applicationInfo.flags & 0x1) != 0) && (!"com.tencent.mobileqq".equalsIgnoreCase(localPackageInfo.packageName))) {
            break label240;
          }
          com.lionmobi.powerclean.model.bean.e localE = new com.lionmobi.powerclean.model.bean.e();
          localE.a = localPackageInfo.applicationInfo.loadLabel(this.aq.getPackageManager()).toString();
          localE.b = localPackageInfo.packageName;
          localE.c = localPackageInfo.applicationInfo.loadIcon(this.aq.getPackageManager());
          if (str.contains(localPackageInfo.packageName))
          {
            localE.d = true;
            this.ao.add(localE);
            this.an.add(0, localE);
          }
          else
          {
            this.an.add(localE);
          }
        }
      }
      catch (Exception localException) {}
      return this.an;
      label240:
      j += 1;
    }
  }
  
  public final void init(Context paramContext, ah paramAh)
  {
    this.al = paramAh;
    this.aq = paramContext;
    new Thread(new Runnable()
    {
      public final void run()
      {
        af.a(af.this, af.this.getInstalledApp());
        if ((af.a(af.this) != null) && (af.a(af.this).size() > 0)) {
          af.b(af.this);
        }
      }
    }).start();
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1024)
    {
      FlurryAgent.onStartSession(getActivity().getBaseContext());
      paramIntent = new HashMap();
      if ((getActivity() == null) || (getActivity().isFinishing()) || (!isEnabled(getActivity()))) {
        break label91;
      }
      paramIntent.put("充电消息通知是否授权成功", "是");
      FlurryAgent.logEvent("进入授权界面", paramIntent);
    }
    for (;;)
    {
      FlurryAgent.onEndSession(getActivity().getBaseContext());
      return;
      label91:
      paramIntent.put("充电消息通知是否授权成功", "否");
      FlurryAgent.logEvent("进入授权界面", paramIntent);
    }
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = new IntentFilter();
    paramBundle.addAction("com.lionmobi.powerclean.ACTION_GET_NOTIFICATION_COMPLETE");
    getActivity().registerReceiver(this.e, paramBundle);
    c.getDefault().register(this);
  }
  
  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903194, paramViewGroup, false);
    this.a = paramLayoutInflater;
    paramLayoutInflater.findViewById(2131428260).setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (af.c(af.this) != null) {
          af.c(af.this).goBack();
        }
      }
    });
    this.am = paramLayoutInflater.findViewById(2131428261);
    if (!isEnabled(getActivity())) {
      this.am.setVisibility(8);
    }
    this.am.setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (af.d(af.this)) {
          new az(af.this.getActivity(), af.a(af.this), af.e(af.this), af.this).show();
        }
      }
    });
    this.as = paramLayoutInflater.findViewById(2131428262);
    this.f = ((ListView)paramLayoutInflater.findViewById(2131427710));
    this.g = paramLayoutInflater.findViewById(2131427767);
    this.g.setVisibility(8);
    this.ay = ((ApplicationEx)getActivity().getApplication());
    this.az = new com.lionmobi.util.g(getActivity());
    if (getActivity() != null) {}
    try
    {
      this.aE = x.initInstance(getActivity().getApplicationContext(), (ApplicationEx)getActivity().getApplication()).getPriorityList(getActivity().getApplicationContext(), "CHARGING_NOTIFICATION");
      if ((this.aE == null) || (this.aE.size() == 0))
      {
        this.aE = new ArrayList();
        this.aE.add("facebook");
        this.aE.add("admob");
      }
      this.i = new ArrayList();
      this.d = new aj(getActivity(), this.i, this);
      this.f.setAdapter(this.d);
      this.ar = paramLayoutInflater.findViewById(2131428263);
      this.at = ((ImageView)paramLayoutInflater.findViewById(2131428267));
      this.h = ((TextView)paramLayoutInflater.findViewById(2131428266));
      this.h.setText(Html.fromHtml(getString(2131165482)));
      this.au = ((ImageView)paramLayoutInflater.findViewById(2131428265));
      this.ar.setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          af.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
      });
      this.h.setText(Html.fromHtml(getString(2131165482)));
      this.as.setVisibility(0);
      this.ar.setVisibility(8);
      this.f.setOnScrollListener(new AbsListView.OnScrollListener()
      {
        public final void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public final void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          default: 
            return;
          case 0: 
            com.lionmobi.util.j.getInstance().a = true;
            af.f(af.this).setScrollState(false);
            af.f(af.this).notifyDataSetChanged();
            return;
          case 2: 
            com.lionmobi.util.j.getInstance().a = false;
            af.f(af.this).setScrollState(true);
            return;
          }
          com.lionmobi.util.j.getInstance().a = false;
          af.f(af.this).setScrollState(true);
        }
      });
      return paramLayoutInflater;
    }
    catch (Exception paramViewGroup)
    {
      for (;;)
      {
        paramViewGroup.printStackTrace();
      }
    }
  }
  
  public final void onDestroy()
  {
    if ((getActivity() != null) && (!getActivity().isFinishing()) && (this.e != null))
    {
      getActivity().unregisterReceiver(this.e);
      this.e = null;
    }
    c.getDefault().unregister(this);
    super.onDestroy();
  }
  
  public final void onEventMainThread(b paramB)
  {
    if ((Build.VERSION.SDK_INT > 10) && (this.d != null))
    {
      this.d.setModels(paramB.b);
      this.d.notifyDataSetChanged();
    }
  }
  
  public final void onPause()
  {
    super.onPause();
    if (e()) {
      FlurryAgent.endTimedEvent("QuichChargePage_Notification");
    }
  }
  
  public final void onResume()
  {
    super.onResume();
    if (e()) {
      FlurryAgent.logEvent("QuichChargePage_Notification", true);
    }
    this.am.setVisibility(0);
    this.ar.setVisibility(8);
    this.as.setVisibility(0);
    if (isEnabled(getActivity()))
    {
      FlurryAgent.logEvent("充电消息通知-已打开授权展示");
      if ((this.i != null) && (this.i.size() > 0) && (((w)this.i.get(0)).isSettings())) {
        this.i.remove(0);
      }
      this.d.notifyDataSetChanged();
      getActivity().sendBroadcast(new Intent("com.lionmobi.powerclean.ACTION_LIST_NOTIFICATION"));
    }
    for (;;)
    {
      updateAD();
      return;
      FlurryAgent.logEvent("充电消息通知-未打开授权展示");
      w localW = new w();
      localW.setIsSettings(true);
      if (this.i.size() == 0)
      {
        this.i.add(0, localW);
        this.d.notifyDataSetChanged();
      }
    }
  }
  
  public final void opensystemsting()
  {
    startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 1024);
    new Handler().postDelayed(new Runnable()
    {
      public final void run()
      {
        if ((af.this.getActivity() != null) && (!af.this.getActivity().isFinishing())) {
          c.getDefault().post(new dm());
        }
      }
    }, 1500L);
  }
  
  public final void sortApp()
  {
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    if (j < this.an.size())
    {
      if (((com.lionmobi.powerclean.model.bean.e)this.an.get(j)).d) {
        localArrayList.add(0, this.an.get(j));
      }
      for (;;)
      {
        j += 1;
        break;
        localArrayList.add(this.an.get(j));
      }
    }
    this.an = localArrayList;
    getActivity().sendBroadcast(new Intent("com.lionmobi.powerclean.ACTION_LIST_NOTIFICATION"));
  }
  
  public final void updateAD()
  {
    if ((getActivity() != null) && (e()))
    {
      this.aF = 0;
      a(this.aF);
    }
  }
}
