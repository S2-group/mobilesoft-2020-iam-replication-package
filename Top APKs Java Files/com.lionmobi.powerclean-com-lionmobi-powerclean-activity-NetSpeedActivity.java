package com.lionmobi.powerclean.activity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.i;
import com.facebook.ads.k;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.formats.g;
import com.google.android.gms.ads.formats.h;
import com.lionmobi.powerclean.ApplicationEx;
import com.lionmobi.powerclean.e.x;
import com.lionmobi.powerclean.model.b.dm;
import com.lionmobi.powerclean.view.MyListView;
import com.lionmobi.powerclean.view.a.bj;
import com.lionmobi.powerclean.view.a.bk;
import com.lionmobi.util.af;
import com.lionmobi.util.ag;
import com.lionmobi.util.at;
import com.lionmobi.util.fontIcon.FontIconDrawable;
import com.lionmobi.util.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NetSpeedActivity
  extends e
{
  private LinearLayout A;
  private com.facebook.ads.b B;
  private List C;
  private int D;
  private i E;
  private long F = 0L;
  private long G = 0L;
  HashMap a = new HashMap();
  bk b = new bk()
  {
    public final void opensystemsting()
    {
      if (!NetSpeedActivity.this.isFinishing())
      {
        NetSpeedActivity.this.startActivityForResult(new Intent("android.settings.USAGE_ACCESS_SETTINGS"), 2048);
        new Handler().postDelayed(new Runnable()
        {
          public final void run()
          {
            de.greenrobot.event.c.getDefault().post(new dm());
          }
        }, 1500L);
      }
    }
  };
  private long c;
  private long d;
  private MyListView e;
  private av f;
  private List g;
  private List h = new ArrayList();
  private List i = new ArrayList();
  private List j = new ArrayList();
  private Map k = new HashMap();
  private Map l = new HashMap();
  private com.a.a m;
  private boolean n;
  private boolean o = true;
  private Handler p = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 2: 
        NetSpeedActivity.this.setNetworkState(NetSpeedActivity.this.getNetworkType());
        NetSpeedActivity.b(NetSpeedActivity.this).addAll(NetSpeedActivity.a(NetSpeedActivity.this));
        NetSpeedActivity.c(NetSpeedActivity.this).notifyDataSetChanged();
        NetSpeedActivity.d(NetSpeedActivity.this);
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427754)).visibility(0);
        return;
      }
      NetSpeedActivity.this.setNetworkState(NetSpeedActivity.this.getNetworkType());
      if (paramAnonymousMessage.arg1 > 102400L)
      {
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427746)).textColor(NetSpeedActivity.this.getResources().getColor(2131361869));
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427745)).image(2130837518);
        if (paramAnonymousMessage.arg2 <= 20480L) {
          break label437;
        }
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427748)).textColor(NetSpeedActivity.this.getResources().getColor(2131361869));
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427747)).image(2130837520);
      }
      for (;;)
      {
        NetSpeedActivity.b(NetSpeedActivity.this).clear();
        NetSpeedActivity.b(NetSpeedActivity.this).addAll(NetSpeedActivity.a(NetSpeedActivity.this));
        NetSpeedActivity.c(NetSpeedActivity.this).notifyDataSetChanged();
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427746)).text(af.getSpeedSizeString(Long.valueOf(paramAnonymousMessage.arg1)) + "/s");
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427748)).text(af.getSpeedSizeString(Long.valueOf(paramAnonymousMessage.arg2)) + "/s");
        return;
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427746)).textColor(NetSpeedActivity.this.getResources().getColor(2131361889));
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427745)).image(2130837614);
        break;
        label437:
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427748)).textColor(NetSpeedActivity.this.getResources().getColor(2131361889));
        ((com.a.a)NetSpeedActivity.e(NetSpeedActivity.this).id(2131427747)).image(2130837795);
      }
    }
  };
  private Thread q;
  private TextView r;
  private WifiManager s;
  private boolean t;
  private boolean u;
  private boolean v = true;
  private bj w;
  private int x = 0;
  private LinearLayout y;
  private FrameLayout z;
  
  public NetSpeedActivity() {}
  
  private void a()
  {
    if ((ag.isNetworkProtectEnabled(getApplicationContext())) && ((com.lionmobi.powerclean.locker.c.c.isUsageStatsPermissionGranted(this)) || ((Build.VERSION.SDK_INT >= 21) && (!com.lionmobi.powerclean.locker.c.c.hasUsageAccessSetting(this)))))
    {
      a(true);
      return;
    }
    a(false);
  }
  
  /* Error */
  private void a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 166	com/lionmobi/powerclean/activity/NetSpeedActivity:C	Ljava/util/List;
    //   4: invokeinterface 172 1 0
    //   9: istore_2
    //   10: iload_1
    //   11: iload_2
    //   12: if_icmplt +4 -> 16
    //   15: return
    //   16: aload_0
    //   17: getfield 166	com/lionmobi/powerclean/activity/NetSpeedActivity:C	Ljava/util/List;
    //   20: iload_1
    //   21: invokeinterface 176 2 0
    //   26: checkcast 178	java/lang/String
    //   29: astore_3
    //   30: ldc -76
    //   32: aload_3
    //   33: invokevirtual 184	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   36: ifeq +34 -> 70
    //   39: invokestatic 190	java/lang/System:currentTimeMillis	()J
    //   42: aload_0
    //   43: getfield 126	com/lionmobi/powerclean/activity/NetSpeedActivity:F	J
    //   46: lsub
    //   47: ldc2_w 191
    //   50: lcmp
    //   51: ifle -36 -> 15
    //   54: aload_0
    //   55: invokespecial 194	com/lionmobi/powerclean/activity/NetSpeedActivity:c	()V
    //   58: aload_0
    //   59: invokespecial 196	com/lionmobi/powerclean/activity/NetSpeedActivity:d	()V
    //   62: aload_0
    //   63: invokestatic 190	java/lang/System:currentTimeMillis	()J
    //   66: putfield 126	com/lionmobi/powerclean/activity/NetSpeedActivity:F	J
    //   69: return
    //   70: ldc -58
    //   72: aload_3
    //   73: invokevirtual 184	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   76: ifeq +95 -> 171
    //   79: invokestatic 190	java/lang/System:currentTimeMillis	()J
    //   82: aload_0
    //   83: getfield 128	com/lionmobi/powerclean/activity/NetSpeedActivity:G	J
    //   86: lsub
    //   87: ldc2_w 199
    //   90: lcmp
    //   91: ifle -76 -> 15
    //   94: new 202	com/google/android/gms/ads/c
    //   97: dup
    //   98: aload_0
    //   99: aload_0
    //   100: invokevirtual 139	com/lionmobi/powerclean/activity/NetSpeedActivity:getApplicationContext	()Landroid/content/Context;
    //   103: ldc -52
    //   105: iconst_0
    //   106: invokestatic 210	com/lionmobi/util/a/a:getAdmobID	(Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   109: invokespecial 213	com/google/android/gms/ads/c:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   112: astore_3
    //   113: aload_3
    //   114: new 24	com/lionmobi/powerclean/activity/NetSpeedActivity$4
    //   117: dup
    //   118: aload_0
    //   119: invokespecial 214	com/lionmobi/powerclean/activity/NetSpeedActivity$4:<init>	(Lcom/lionmobi/powerclean/activity/NetSpeedActivity;)V
    //   122: invokevirtual 218	com/google/android/gms/ads/c:forAppInstallAd	(Lcom/google/android/gms/ads/formats/f;)Lcom/google/android/gms/ads/c;
    //   125: pop
    //   126: aload_3
    //   127: new 26	com/lionmobi/powerclean/activity/NetSpeedActivity$5
    //   130: dup
    //   131: aload_0
    //   132: invokespecial 219	com/lionmobi/powerclean/activity/NetSpeedActivity$5:<init>	(Lcom/lionmobi/powerclean/activity/NetSpeedActivity;)V
    //   135: invokevirtual 223	com/google/android/gms/ads/c:forContentAd	(Lcom/google/android/gms/ads/formats/h;)Lcom/google/android/gms/ads/c;
    //   138: pop
    //   139: aload_3
    //   140: new 28	com/lionmobi/powerclean/activity/NetSpeedActivity$6
    //   143: dup
    //   144: aload_0
    //   145: invokespecial 224	com/lionmobi/powerclean/activity/NetSpeedActivity$6:<init>	(Lcom/lionmobi/powerclean/activity/NetSpeedActivity;)V
    //   148: invokevirtual 228	com/google/android/gms/ads/c:withAdListener	(Lcom/google/android/gms/ads/a;)Lcom/google/android/gms/ads/c;
    //   151: invokevirtual 232	com/google/android/gms/ads/c:build	()Lcom/google/android/gms/ads/b;
    //   154: invokestatic 238	com/lionmobi/powerclean/a/b:getAdRequestBuilder	()Lcom/google/android/gms/ads/e;
    //   157: invokevirtual 243	com/google/android/gms/ads/e:build	()Lcom/google/android/gms/ads/d;
    //   160: invokevirtual 249	com/google/android/gms/ads/b:loadAd	(Lcom/google/android/gms/ads/d;)V
    //   163: aload_0
    //   164: invokestatic 190	java/lang/System:currentTimeMillis	()J
    //   167: putfield 128	com/lionmobi/powerclean/activity/NetSpeedActivity:G	J
    //   170: return
    //   171: ldc -5
    //   173: aload_3
    //   174: invokevirtual 184	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   177: ifeq +36 -> 213
    //   180: aload_0
    //   181: getfield 253	com/lionmobi/powerclean/activity/NetSpeedActivity:y	Landroid/widget/LinearLayout;
    //   184: ifnull +12 -> 196
    //   187: aload_0
    //   188: getfield 253	com/lionmobi/powerclean/activity/NetSpeedActivity:y	Landroid/widget/LinearLayout;
    //   191: bipush 8
    //   193: invokevirtual 258	android/widget/LinearLayout:setVisibility	(I)V
    //   196: aload_0
    //   197: getfield 260	com/lionmobi/powerclean/activity/NetSpeedActivity:z	Landroid/widget/FrameLayout;
    //   200: ifnull -185 -> 15
    //   203: aload_0
    //   204: getfield 260	com/lionmobi/powerclean/activity/NetSpeedActivity:z	Landroid/widget/FrameLayout;
    //   207: bipush 8
    //   209: invokevirtual 263	android/widget/FrameLayout:setVisibility	(I)V
    //   212: return
    //   213: invokestatic 190	java/lang/System:currentTimeMillis	()J
    //   216: aload_0
    //   217: getfield 126	com/lionmobi/powerclean/activity/NetSpeedActivity:F	J
    //   220: lsub
    //   221: ldc2_w 191
    //   224: lcmp
    //   225: ifle -210 -> 15
    //   228: aload_0
    //   229: invokespecial 194	com/lionmobi/powerclean/activity/NetSpeedActivity:c	()V
    //   232: aload_0
    //   233: invokespecial 196	com/lionmobi/powerclean/activity/NetSpeedActivity:d	()V
    //   236: aload_0
    //   237: invokestatic 190	java/lang/System:currentTimeMillis	()J
    //   240: putfield 126	com/lionmobi/powerclean/activity/NetSpeedActivity:F	J
    //   243: return
    //   244: astore_3
    //   245: return
    //   246: astore_3
    //   247: ldc -76
    //   249: astore_3
    //   250: goto -220 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	this	NetSpeedActivity
    //   0	253	1	paramInt	int
    //   9	4	2	i1	int
    //   29	145	3	localObject	Object
    //   244	1	3	localException1	Exception
    //   246	1	3	localException2	Exception
    //   249	1	3	str	String
    // Exception table:
    //   from	to	target	type
    //   0	10	244	java/lang/Exception
    //   30	69	244	java/lang/Exception
    //   70	170	244	java/lang/Exception
    //   171	196	244	java/lang/Exception
    //   196	212	244	java/lang/Exception
    //   213	243	244	java/lang/Exception
    //   16	30	246	java/lang/Exception
  }
  
  private void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      ag.setNetworkProtectEnable(getApplicationContext(), true);
      ((com.a.a)this.m.id(2131427750)).visibility(8);
      ((com.a.a)this.m.id(2131427751)).visibility(0);
    }
    while (ag.isNetworkProtectEnabled(getApplicationContext()))
    {
      ((com.a.a)((com.a.a)this.m.id(2131427743)).image(2130837664)).visibility(0);
      return;
      ag.setNetworkProtectEnable(getApplicationContext(), false);
      ((com.a.a)this.m.id(2131427750)).visibility(0);
      ((com.a.a)this.m.id(2131427751)).visibility(8);
    }
    ((com.a.a)((com.a.a)this.m.id(2131427743)).image(2130837663)).visibility(0);
  }
  
  private void b()
  {
    if (this.t)
    {
      Intent localIntent = new Intent(this, MainActivity.class);
      localIntent.setFlags(335544320);
      startActivity(localIntent);
    }
  }
  
  private void c()
  {
    try
    {
      this.y = ((LinearLayout)findViewById(2131427342));
      this.A = ((LinearLayout)((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903177, this.y));
      return;
    }
    catch (Exception localException) {}
  }
  
  private void d()
  {
    try
    {
      this.E = new i(this, com.lionmobi.util.a.a.getFBPID(getApplicationContext(), "1539547886295207_1713573698892624", 0));
      this.E.setAdListener(new au(this));
      this.E.loadAd(k.d);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void getAppTrafficList()
  {
    PackageManager localPackageManager = getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(12288).iterator();
    int i2;
    label68:
    int i1;
    com.lionmobi.powerclean.model.bean.b localB;
    long l1;
    int i3;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        String[] arrayOfString = localPackageInfo.requestedPermissions;
        if ((arrayOfString != null) && (arrayOfString.length > 0))
        {
          int i4 = arrayOfString.length;
          i2 = 0;
          if (i2 < i4) {
            if ("android.permission.INTERNET".equals(arrayOfString[i2]))
            {
              i1 = localPackageInfo.applicationInfo.uid;
              long l3 = TrafficStats.getUidRxBytes(i1);
              long l4 = TrafficStats.getUidTxBytes(i1);
              if ((l3 >= 0L) && (l4 >= 0L) && (this.a.containsKey(Integer.valueOf(i1))) && (!((Boolean)this.a.get(Integer.valueOf(i1))).booleanValue()) && (!o.getLionmobiList().contains(localPackageManager.getNameForUid(i1))))
              {
                this.a.put(Integer.valueOf(i1), Boolean.valueOf(true));
                localB = new com.lionmobi.powerclean.model.bean.b();
                localB.setUid(i1);
                localB.setPackageName(localPackageInfo.applicationInfo.processName);
                localB.setName(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
                localB.setIcon(localPackageInfo.applicationInfo.loadIcon(localPackageManager));
                l1 = 0L;
                if (this.k.get(Integer.valueOf(i1)) != null) {
                  l1 = ((Long)this.k.get(Integer.valueOf(i1))).longValue();
                }
                long l2 = 0L;
                if (this.l.get(Integer.valueOf(i1)) != null) {
                  l2 = ((Long)this.l.get(Integer.valueOf(i1))).longValue();
                }
                localB.setDownLoad(l1);
                localB.setUpLoad(l2);
                localB.setRx(l3);
                localB.setTx(l4);
                if (((localPackageInfo.applicationInfo.flags & 0x1) != 0) || ((localPackageInfo.applicationInfo.flags & 0x80) != 0)) {
                  break label517;
                }
                l1 = localB.getRx();
                l1 = localB.getTx() + l1;
                i3 = this.j.size();
                if (l1 == 0L) {
                  break label502;
                }
                i1 = 0;
                label424:
                if (i1 >= this.j.size()) {
                  break label676;
                }
                if (((com.lionmobi.powerclean.model.bean.b)this.j.get(i1)).getRx() + ((com.lionmobi.powerclean.model.bean.b)this.j.get(i1)).getTx() >= l1) {
                  break label495;
                }
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      this.j.add(i1, localB);
      for (;;)
      {
        i2 += 1;
        break label68;
        break;
        label495:
        i1 += 1;
        break label424;
        label502:
        this.j.add(localB);
      }
      label517:
      l1 = localB.getRx();
      l1 = localB.getTx() + l1;
      i3 = this.i.size();
      if (l1 != 0L)
      {
        i1 = 0;
        label553:
        if (i1 >= this.i.size()) {
          break label671;
        }
        if (((com.lionmobi.powerclean.model.bean.b)this.i.get(i1)).getRx() + ((com.lionmobi.powerclean.model.bean.b)this.i.get(i1)).getTx() >= l1) {}
      }
      for (;;)
      {
        this.i.add(i1, localB);
        break;
        i1 += 1;
        break label553;
        this.i.add(localB);
        break;
        this.g.addAll(this.j);
        this.g.addAll(this.i);
        return;
        label671:
        i1 = i3;
      }
      label676:
      i1 = i3;
    }
  }
  
  public int getNetworkType()
  {
    for (;;)
    {
      try
      {
        Object localObject = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
        if (localObject == null) {
          return 0;
        }
        i1 = ((NetworkInfo)localObject).getType();
        if (i1 == 0)
        {
          localObject = ((NetworkInfo)localObject).getExtraInfo();
          if (localObject != null)
          {
            i1 = 2;
            return i1;
          }
        }
        else
        {
          if (i1 != 1) {
            break;
          }
          return 1;
        }
      }
      catch (Exception localException)
      {
        return 0;
      }
      int i1 = 0;
    }
    return 0;
  }
  
  public void getRunningApp(Context paramContext)
  {
    try
    {
      Object localObject = (ActivityManager)paramContext.getSystemService("activity");
      paramContext = ((ActivityManager)localObject).getRunningServices(Integer.MAX_VALUE);
      localObject = ((ActivityManager)localObject).getRunningAppProcesses();
      if (paramContext.size() + ((List)localObject).size() != this.x)
      {
        if (this.g.size() > 0) {
          this.g.clear();
        }
        this.a.clear();
        this.i.clear();
        this.j.clear();
        this.x = (paramContext.size() + ((List)localObject).size());
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
          if (!this.a.containsKey(Integer.valueOf(localRunningAppProcessInfo.uid))) {
            this.a.put(Integer.valueOf(localRunningAppProcessInfo.uid), Boolean.valueOf(false));
          }
        }
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          localObject = (ActivityManager.RunningServiceInfo)paramContext.next();
          if (!this.a.containsKey(Integer.valueOf(((ActivityManager.RunningServiceInfo)localObject).uid))) {
            this.a.put(Integer.valueOf(((ActivityManager.RunningServiceInfo)localObject).uid), Boolean.valueOf(false));
          }
        }
        getAppTrafficList();
        return;
      }
      if (this.g.size() > 0) {
        this.g.clear();
      }
      Collections.sort(this.j, new Comparator()
      {
        public final int compare(com.lionmobi.powerclean.model.bean.b paramAnonymousB1, com.lionmobi.powerclean.model.bean.b paramAnonymousB2)
        {
          if (paramAnonymousB1.getTx() + paramAnonymousB1.getRx() > paramAnonymousB2.getTx() + paramAnonymousB2.getRx()) {
            return -1;
          }
          if (paramAnonymousB1.getTx() + paramAnonymousB1.getRx() == paramAnonymousB2.getTx() + paramAnonymousB2.getRx()) {
            return 0;
          }
          return 1;
        }
      });
      Collections.sort(this.i, new Comparator()
      {
        public final int compare(com.lionmobi.powerclean.model.bean.b paramAnonymousB1, com.lionmobi.powerclean.model.bean.b paramAnonymousB2)
        {
          if (paramAnonymousB1.getTx() + paramAnonymousB1.getRx() > paramAnonymousB2.getTx() + paramAnonymousB2.getRx()) {
            return -1;
          }
          if (paramAnonymousB1.getTx() + paramAnonymousB1.getRx() == paramAnonymousB2.getTx() + paramAnonymousB2.getRx()) {
            return 0;
          }
          return 1;
        }
      });
      this.g.addAll(this.j);
      this.g.addAll(this.i);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public void inflateAd(i paramI, View paramView)
  {
    try
    {
      ImageView localImageView = (ImageView)paramView.findViewById(2131428054);
      TextView localTextView1 = (TextView)paramView.findViewById(2131428057);
      TextView localTextView2 = (TextView)paramView.findViewById(2131428058);
      TextView localTextView3 = (TextView)paramView.findViewById(2131428055);
      FrameLayout localFrameLayout = (FrameLayout)paramView.findViewById(2131428053);
      localTextView3.setText(paramI.getAdCallToAction());
      localTextView3.setVisibility(0);
      localTextView1.setText(paramI.getAdTitle());
      localTextView2.setText(paramI.getAdBody());
      i.downloadAndDisplayImage(paramI.getAdIcon(), localImageView);
      paramI.registerViewForInteraction(paramView);
      if (this.B == null)
      {
        this.B = new com.facebook.ads.b(this, paramI, true);
        paramI = new FrameLayout.LayoutParams(at.dpToPx(14.0F, getResources()), at.dpToPx(14.0F, getResources()));
        paramI.setMargins(0, 0, getResources().getDimensionPixelSize(2131099719), getResources().getDimensionPixelSize(2131099720));
        paramI.gravity = 85;
        localFrameLayout.addView(this.B, paramI);
      }
      return;
    }
    catch (Exception paramI) {}
  }
  
  public boolean isAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 2048)
    {
      if (com.lionmobi.powerclean.locker.c.c.isUsageStatsPermissionGranted(this)) {
        a(true);
      }
      FlurryAgent.onStartSession(getBaseContext());
      paramIntent = new HashMap();
      if (!com.lionmobi.powerclean.locker.c.c.isUsageStatsPermissionGranted(this)) {
        break label77;
      }
      paramIntent.put("网速保护是否授权成功", "是");
      FlurryAgent.logEvent("进入授权界面", paramIntent);
    }
    for (;;)
    {
      FlurryAgent.onEndSession(getBaseContext());
      return;
      label77:
      paramIntent.put("网速保护是否授权成功", "否");
      FlurryAgent.logEvent("进入授权界面", paramIntent);
    }
  }
  
  public void onBackPressed()
  {
    b();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903089);
    this.e = ((MyListView)findViewById(2131427749));
    this.r = ((TextView)findViewById(2131427744));
    this.s = ((WifiManager)getSystemService("wifi"));
    this.g = new ArrayList();
    this.f = new av(this, (byte)0);
    this.e.setAdapter(this.f);
    this.w = new bj(this);
    this.w.setListener(this.b);
    this.m = new com.a.a(this);
    ((com.a.a)((com.a.a)this.m.id(2131427388)).image(FontIconDrawable.inflate(this, 2131034126))).clicked(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        NetSpeedActivity.f(NetSpeedActivity.this);
        NetSpeedActivity.this.finish();
      }
    });
    ((com.a.a)((com.a.a)this.m.id(2131427649)).image(FontIconDrawable.inflate(this, 2131034157))).clicked(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (NetSpeedActivity.this.isAppInstalled(NetSpeedActivity.this, "com.lionmobi.netmaster"))
        {
          paramAnonymousView = NetSpeedActivity.this.getPackageManager().getLaunchIntentForPackage("com.lionmobi.netmaster");
          paramAnonymousView.putExtra("JumpTag", "networkProtect");
          paramAnonymousView.addFlags(268435456);
          NetSpeedActivity.this.startActivity(paramAnonymousView);
          return;
        }
        paramAnonymousView = new com.lionmobi.powerclean.view.a.e(NetSpeedActivity.this, 1);
        paramAnonymousView.setListener(new com.lionmobi.powerclean.view.a.f()
        {
          public final void clickCancel() {}
          
          public final void clickOK()
          {
            NetSpeedActivity.g(NetSpeedActivity.this);
          }
        });
        paramAnonymousView.show();
      }
    });
    ((com.a.a)this.m.id(2131427750)).clicked(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        NetSpeedActivity.h(NetSpeedActivity.this);
        FlurryAgent.logEvent("开启网速保护");
      }
    });
    ((com.a.a)this.m.id(2131427752)).clicked(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        NetSpeedActivity.i(NetSpeedActivity.this);
        FlurryAgent.logEvent("取消网速保护");
      }
    });
    ((com.a.a)this.m.id(2131427753)).clicked(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        NetSpeedActivity.this.startActivity(new Intent(NetSpeedActivity.this, NetworkProtectHistoryActivity.class));
        FlurryAgent.logEvent("查看保护记录");
      }
    });
    int i1 = Integer.parseInt(((ApplicationEx)getApplication()).getGlobalSettingPreference().getString("theme", "0"));
    if (i1 == 0)
    {
      setTheme(2131558407);
      ((com.a.a)this.m.id(2131427750)).background(2130837747);
      ((com.a.a)this.m.id(2131427753)).background(2130837747);
    }
    for (;;)
    {
      if (getIntent() != null)
      {
        this.t = getIntent().getBooleanExtra("bar_to_speed", false);
        this.u = getIntent().getBooleanExtra("from_device_states", false);
      }
      try
      {
        this.C = x.initInstance(getApplicationContext(), (ApplicationEx)getApplication()).getPriorityList(getApplicationContext(), "NETWORK_PROTECT_BANNER");
        if ((this.C == null) || (this.C.size() == 0))
        {
          this.C = new ArrayList();
          this.C.add("facebook");
          this.C.add("admob");
        }
        if (!isFinishing()) {
          com.lionmobi.c.b.c.getInstance(getApplicationContext()).initAdData("NETWORK_PROTECT_RESULT");
        }
        return;
        if (i1 == 1)
        {
          setTheme(2131558403);
          ((com.a.a)this.m.id(2131427750)).background(2130837742);
          ((com.a.a)this.m.id(2131427753)).background(2130837742);
          continue;
        }
        if (i1 == 2)
        {
          setTheme(2131558404);
          ((com.a.a)this.m.id(2131427750)).background(2130837743);
          ((com.a.a)this.m.id(2131427753)).background(2130837743);
          continue;
        }
        if (i1 == 3)
        {
          setTheme(2131558405);
          ((com.a.a)this.m.id(2131427750)).background(2130837744);
          ((com.a.a)this.m.id(2131427753)).background(2130837744);
          continue;
        }
        setTheme(2131558406);
        ((com.a.a)this.m.id(2131427750)).background(2130837745);
        ((com.a.a)this.m.id(2131427753)).background(2130837745);
      }
      catch (Exception paramBundle)
      {
        for (;;)
        {
          paramBundle.printStackTrace();
        }
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.p != null) {
      this.p.removeCallbacksAndMessages(null);
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    this.n = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    this.D = 0;
    a(this.D);
    this.n = true;
    if ((this.t) && (this.v))
    {
      FlurryAgent.logEvent("Tools-NetworkSpeed");
      this.v = false;
    }
    FlurryAgent.logEvent("实时网速列表展示");
    if ((this.q != null) && (this.q.isAlive())) {}
    do
    {
      return;
      this.q = new Thread(new Runnable()
      {
        public final void run()
        {
          try
          {
            NetSpeedActivity.a(NetSpeedActivity.this, TrafficStats.getTotalRxBytes());
            NetSpeedActivity.b(NetSpeedActivity.this, TrafficStats.getTotalTxBytes());
            if (NetSpeedActivity.j(NetSpeedActivity.this))
            {
              NetSpeedActivity.k(NetSpeedActivity.this);
              NetSpeedActivity.this.getRunningApp(NetSpeedActivity.this);
              if ((NetSpeedActivity.a(NetSpeedActivity.this) != null) && (NetSpeedActivity.a(NetSpeedActivity.this).size() > 0)) {
                NetSpeedActivity.l(NetSpeedActivity.this).sendEmptyMessage(2);
              }
            }
            while ((NetSpeedActivity.this != null) && (NetSpeedActivity.m(NetSpeedActivity.this)) && (!NetSpeedActivity.this.isFinishing()))
            {
              long l1 = System.currentTimeMillis();
              int i = NetSpeedActivity.n(NetSpeedActivity.this);
              int j = NetSpeedActivity.o(NetSpeedActivity.this);
              NetSpeedActivity.a(NetSpeedActivity.this, i, j);
              Message localMessage = NetSpeedActivity.l(NetSpeedActivity.this).obtainMessage();
              localMessage.what = 1;
              localMessage.arg1 = i;
              localMessage.arg2 = j;
              if ((NetSpeedActivity.a(NetSpeedActivity.this) != null) && (NetSpeedActivity.a(NetSpeedActivity.this).size() > 0)) {
                NetSpeedActivity.l(NetSpeedActivity.this).sendMessage(localMessage);
              }
              long l2 = System.currentTimeMillis();
              l1 = 1000L - (l1 - l2);
              if (l1 > 0L) {
                try
                {
                  Thread.sleep(l1);
                }
                catch (InterruptedException localInterruptedException)
                {
                  localInterruptedException.printStackTrace();
                }
              }
            }
            return;
          }
          catch (Exception localException) {}
        }
      });
      this.q.start();
    } while (this.o);
    a();
  }
  
  public void setNetworkState(int paramInt)
  {
    Object localObject = (TelephonyManager)getSystemService("phone");
    switch (paramInt)
    {
    default: 
      localObject = "";
    }
    for (;;)
    {
      this.r.setText((CharSequence)localObject);
      return;
      localObject = getResources().getString(2131165976);
      continue;
      StringBuilder localStringBuilder = new StringBuilder().append(((TelephonyManager)localObject).getNetworkOperatorName()).append(" ");
      paramInt = ((TelephonyManager)localObject).getNetworkType();
      localObject = "";
      switch (paramInt)
      {
      }
      for (;;)
      {
        localObject = (String)localObject;
        break;
        localObject = "unknown";
        continue;
        localObject = "2G";
        continue;
        localObject = "3G";
        continue;
        localObject = "4G";
      }
      localObject = this.s.getConnectionInfo().getSSID();
      if (((String)localObject).length() > 1) {
        localObject = ((String)localObject).substring(1, ((String)localObject).length() - 1);
      } else {
        localObject = "";
      }
    }
  }
}
