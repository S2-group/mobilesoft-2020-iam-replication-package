package com.emoji.panel.views.tabs.sticker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.b;
import android.support.design.widget.TabLayout.e;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.emoji.common.h;
import com.emoji.panel.views.tabs.FaceView.a;
import com.more.setting.MainActivity;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class StickView
  implements FaceView.a
{
  private static final String[] bKk = new String[0];
  private static final String[] bKl = new String[0];
  private ViewPager LK;
  private cl.b aRt;
  private com.emoji.panel.views.tabs.a bFr;
  private ViewGroup bFw;
  private TabLayout bHB;
  Context bKe = null;
  private StickView bKm;
  a bKn;
  private GifListChangeLocalReceiver bKo;
  Drawable bKp;
  private ImageView bKq;
  private HashMap<String, b> bKr;
  private List<b> bKs;
  private List<b> bKt;
  private ArrayMap<String, View> bKu;
  private boolean bKv = false;
  private List<b> baH;
  Context mContext;
  private int mState = 0;
  
  public StickView(Context paramContext, com.emoji.panel.views.tabs.a paramA, cl.b paramB)
  {
    this.mContext = paramContext;
    this.bKe = this.mContext;
    this.bFr = paramA;
    this.aRt = paramB;
    int i = this.mContext.getResources().getDimensionPixelOffset(2131427540);
    int j = this.bFr.mg();
    this.bKo = new GifListChangeLocalReceiver(this.mContext, (byte)0);
    paramContext = this.bFr.mb();
    paramContext = LayoutInflater.from(new ContextThemeWrapper(this.mContext, 2131558770)).inflate(2130903304, paramContext, false);
    this.bFw = ((ViewGroup)paramContext);
    this.bKq = ((ImageView)paramContext.findViewById(2131755919));
    this.bKq.setOnTouchListener(new View.OnTouchListener()
    {
      public final boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0) {
          StickView.this.tA();
        }
        return false;
      }
    });
    this.bKu = new ArrayMap();
    this.bKq.setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        paramAnonymousView = StickView.a(StickView.this);
        if (paramAnonymousView == null) {
          return;
        }
        Intent localIntent = new Intent(paramAnonymousView, MainActivity.class);
        cd.b.tm();
        paramAnonymousView.startActivity(localIntent.setAction(cd.b.to()).setFlags(337641472).putExtra("EXTRA_PACKAGE", cd.b.tm().tl()));
      }
    });
    this.bKr = new HashMap();
    this.bKs = new ArrayList();
    this.bKt = new ArrayList();
    uz();
    this.baH = new ArrayList();
    uA();
    this.baH.addAll(this.bKt);
    this.baH.addAll(this.bKs);
    this.bKp = ContextCompat.getDrawable(this.mContext, 2130838164);
    this.bKm = this;
    this.bHB = ((TabLayout)this.bFw.findViewById(2131755301));
    this.LK = ((ViewPager)this.bFw.findViewById(2131755302));
    this.LK.getLayoutParams().height = (j - i);
    this.bKn = new a(this.baH);
    this.LK.setAdapter(this.bKn);
    this.bHB.setupWithViewPager(this.LK);
    uB();
    a(this.bHB);
    this.LK.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public final void onPageScrollStateChanged(int paramAnonymousInt)
      {
        if ((StickView.e(StickView.this) == 0) && (paramAnonymousInt == 2)) {
          StickView.a(StickView.this, true);
        }
        for (;;)
        {
          StickView.a(StickView.this, paramAnonymousInt);
          return;
          StickView.a(StickView.this, false);
        }
      }
      
      public final void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
      {
        Object localObject;
        if (paramAnonymousInt1 > 0)
        {
          localObject = (View)StickView.c(StickView.this).get(((StickView.b)StickView.b(StickView.this).get(paramAnonymousInt1 - 1)).packName);
          if ((localObject instanceof b))
          {
            localObject = (b)localObject;
            ((b)localObject).aao.smoothScrollToPosition(((b)localObject).bJJ.getItemCount() - 1);
          }
        }
        if (paramAnonymousInt1 < StickView.b(StickView.this).size() - 1)
        {
          localObject = (View)StickView.c(StickView.this).get(((StickView.b)StickView.b(StickView.this).get(paramAnonymousInt1 + 1)).packName);
          if ((localObject instanceof b)) {
            ((b)localObject).ux();
          }
        }
        if (StickView.d(StickView.this))
        {
          localObject = (View)StickView.c(StickView.this).get(((StickView.b)StickView.b(StickView.this).get(paramAnonymousInt1)).packName);
          if ((localObject instanceof b)) {
            ((b)localObject).ux();
          }
        }
      }
      
      public final void onPageSelected(int paramAnonymousInt) {}
    });
    c(paramB, true);
  }
  
  private void a(TabLayout paramTabLayout)
  {
    paramTabLayout.setOnTabSelectedListener(new TabLayout.b()
    {
      public final void c(TabLayout.e paramAnonymousE)
      {
        int i = paramAnonymousE.Mj;
        StickView.b localB = (StickView.b)StickView.this.bKn.bKx.get(i);
        if (localB.isAd) {}
        for (StickView.this.bKe = StickView.a(StickView.this);; StickView.this.bKe = localB.context)
        {
          StickView.f(StickView.this).setCurrentItem(i);
          i = paramAnonymousE.Mj - StickView.g(StickView.this).size();
          if (i >= 0) {
            MobclickAgent.onEvent(StickView.a(StickView.this), "sticker_ad_show_times", StickView.uC()[i]);
          }
          try
          {
            ((ImageView)paramAnonymousE.Mk.findViewById(2131755368)).setBackgroundDrawable(StickView.this.bKp);
            return;
          }
          catch (Exception paramAnonymousE)
          {
            Log.e("StickView", "onTabSelected: " + paramAnonymousE);
          }
        }
      }
      
      public final void d(TabLayout.e paramAnonymousE)
      {
        ((ImageView)paramAnonymousE.Mk.findViewById(2131755368)).setBackgroundDrawable(null);
      }
    });
  }
  
  private void bS(String paramString)
  {
    if (TextUtils.equals(paramString, this.mContext.getPackageName())) {}
    for (long l = 0L;; l = com.emoji.common.d.v(this.mContext, paramString))
    {
      c(paramString, l);
      if (this.bKr.get(paramString) != null) {
        this.bKs.remove(this.bKr.get(paramString));
      }
      q(this.bKt);
      this.baH.clear();
      this.baH.addAll(this.bKt);
      this.baH.addAll(this.bKs);
      this.bKn = new a(this.baH);
      this.LK.setAdapter(this.bKn);
      this.bHB.setupWithViewPager(this.LK);
      uB();
      a(this.bHB);
      return;
    }
  }
  
  private void c(String paramString, long paramLong)
  {
    Context localContext = this.mContext;
    String str = "sticker_default_previewicon";
    if (!TextUtils.equals(paramString, this.mContext.getPackageName()))
    {
      localContext = com.emoji.common.d.u(this.mContext, paramString);
      str = "previewicon";
    }
    int i = h.h(localContext, str, "drawable");
    if ((localContext == null) || (i == 0)) {
      return;
    }
    this.bKt.add(new b(paramString, localContext, i, paramLong));
  }
  
  private void e(String paramString, boolean paramBoolean)
  {
    int i = 0;
    for (;;)
    {
      if (i < this.bKt.size())
      {
        if (!TextUtils.equals(((b)this.bKt.get(i)).packName, paramString)) {
          break label225;
        }
        if ((paramBoolean) && (this.bKr.get(paramString) != null)) {
          this.bKs.add(this.bKr.get(paramString));
        }
        this.bKt.remove(i);
        if (this.bKt.size() == 0)
        {
          c(this.mContext.getPackageName(), 0L);
          h.c(this.mContext, "enable_gif_" + this.mContext.getPackageName(), true);
        }
      }
      this.baH.clear();
      this.baH.addAll(this.bKt);
      this.baH.addAll(this.bKs);
      this.bKn = new a(this.baH);
      this.LK.setAdapter(this.bKn);
      this.bHB.setupWithViewPager(this.LK);
      uB();
      a(this.bHB);
      return;
      label225:
      i += 1;
    }
  }
  
  private void q(List<b> paramList)
  {
    Collections.sort(paramList, new Comparator() {});
  }
  
  private void uA()
  {
    if (h.a(this.mContext, "enable_gif_" + this.mContext.getPackageName(), Boolean.valueOf(true)).booleanValue()) {
      c(this.mContext.getPackageName(), 0L);
    }
    Iterator localIterator = this.mContext.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (cd.b.bH(localPackageInfo.packageName))
      {
        if (this.bKr.get(localPackageInfo.packageName) != null) {
          this.bKs.remove(this.bKr.get(localPackageInfo.packageName));
        }
        if (h.a(this.mContext, "enable_gif_" + localPackageInfo.packageName, Boolean.valueOf(true)).booleanValue()) {
          c(localPackageInfo.packageName, localPackageInfo.firstInstallTime);
        }
      }
    }
    q(this.bKt);
  }
  
  private void uB()
  {
    int i = 0;
    while (i < this.baH.size())
    {
      b localB = (b)this.baH.get(i);
      View localView = LayoutInflater.from(this.mContext).inflate(2130903101, null);
      ImageView localImageView = (ImageView)localView.findViewById(2131755368);
      localImageView.setImageDrawable(ContextCompat.getDrawable(localB.context, localB.icon));
      if (i == 0) {
        localImageView.setBackgroundDrawable(this.bKp);
      }
      this.bHB.M(i).v(localView);
      i += 1;
    }
  }
  
  private void uz()
  {
    int i = 0;
    while (i < bKk.length)
    {
      Object localObject = bKk[i];
      String str1 = cd.b.tm().tl() + bKl[i];
      String str2 = "sticker_ad_" + (String)localObject;
      int j = h.h(this.mContext, str2, "drawable");
      if (j != 0)
      {
        localObject = new b(str1, this.mContext, j, (String)localObject);
        this.bKs.add(localObject);
        this.bKr.put(str1, localObject);
      }
      i += 1;
    }
  }
  
  public final void Y(String paramString) {}
  
  public final void c(Intent paramIntent)
  {
    if ((!paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED")) && (!paramIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))) {}
    String str;
    do
    {
      do
      {
        return;
        str = paramIntent.getDataString().substring(8);
      } while ((TextUtils.isEmpty(str)) || (!cd.b.bH(str)));
      if (paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED"))
      {
        bS(str);
        return;
      }
    } while (!paramIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"));
    e(str, true);
  }
  
  public final void c(cl.b paramB, boolean paramBoolean)
  {
    if (paramB == null) {}
    for (;;)
    {
      return;
      Object localObject = paramB.bW("emoji_btn");
      this.bKq.setImageDrawable(h.a(ContextCompat.getDrawable(this.mContext, 2130837824), (ColorStateList)localObject));
      this.bKp.setColorFilter(paramB.bX("emoji_circle_bg"), PorterDuff.Mode.MULTIPLY);
      this.aRt = paramB;
      if (this.bKu != null)
      {
        localObject = this.bKu.values().iterator();
        while (((Iterator)localObject).hasNext())
        {
          View localView = (View)((Iterator)localObject).next();
          if ((localView instanceof b)) {
            ((b)localView).f(paramB);
          }
        }
      }
    }
  }
  
  public final void dK(int paramInt) {}
  
  public final int getCurrentProgress()
  {
    return 0;
  }
  
  public final String getName()
  {
    return "sticker";
  }
  
  public final View getView()
  {
    return this.bFw;
  }
  
  public final void hide() {}
  
  public final void recycle()
  {
    if ((this.bKo != null) && (this.mContext != null))
    {
      LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.bKo);
      this.bKo = null;
    }
    Iterator localIterator = this.bKu.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (View)localIterator.next();
      if ((localObject instanceof b))
      {
        localObject = (b)localObject;
        if (((b)localObject).bJM != null) {
          ((b)localObject).bJM.bJZ = null;
        }
        ((b)localObject).bJL = null;
        if (((b)localObject).bJR != null)
        {
          ((b)localObject).bJR.bJF = true;
          ((b)localObject).bJR.recycle();
        }
        if (((b)localObject).bJP != null) {
          ((b)localObject).bJP.removeCallbacks(((b)localObject).bJS);
        }
        ((b)localObject).bJh = null;
      }
    }
  }
  
  public final void show()
  {
    al.a.a(this.bFw, true, new Runnable()
    {
      public final void run() {}
    });
  }
  
  public final void tA()
  {
    if (this.bFr != null) {
      this.bFr.ad(-1, 0);
    }
  }
  
  public final void tK() {}
  
  public final int tN()
  {
    return 0;
  }
  
  public final void tO() {}
  
  public class GifListChangeLocalReceiver
    extends BroadcastReceiver
  {
    private GifListChangeLocalReceiver(Context paramContext)
    {
      this$1 = new IntentFilter();
      StickView.this.addAction("keyboard.giflist.change");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(this, StickView.this);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getBooleanExtra("select", true))
      {
        StickView.a(StickView.this, paramIntent.getStringExtra("packageName"));
        return;
      }
      StickView.b(StickView.this, paramIntent.getStringExtra("packageName"));
    }
  }
  
  private final class a
    extends PagerAdapter
  {
    List<StickView.b> bKx;
    
    a()
    {
      Object localObject;
      this.bKx = localObject;
    }
    
    public final void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((View)paramObject);
    }
    
    public final int getCount()
    {
      return this.bKx.size();
    }
    
    public final CharSequence getPageTitle(int paramInt)
    {
      return "";
    }
    
    public final Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      StickView.b localB = (StickView.b)this.bKx.get(paramInt);
      Object localObject;
      if (localB.isAd)
      {
        localObject = new c(StickView.a(StickView.this), localB.name, localB.packName);
        StickView.c(StickView.this).put(localB.packName, localObject);
        paramViewGroup.addView((View)localObject);
        return localObject;
      }
      if (StickView.c(StickView.this).get(localB.packName) != null) {
        localObject = (b)StickView.c(StickView.this).get(localB.packName);
      }
      for (;;)
      {
        paramViewGroup.addView((View)localObject);
        return localObject;
        localObject = new b(StickView.a(StickView.this), StickView.h(StickView.this), localB.context, StickView.i(StickView.this));
        StickView.c(StickView.this).put(localB.packName, localObject);
      }
    }
    
    public final boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }
  
  final class b
  {
    Context context;
    long firstInstallTime;
    int icon;
    boolean isAd;
    String name;
    String packName;
    
    b(String paramString, Context paramContext, int paramInt, long paramLong)
    {
      this.packName = paramString;
      this.context = paramContext;
      this.icon = paramInt;
      this.firstInstallTime = paramLong;
    }
    
    b(String paramString1, Context paramContext, int paramInt, String paramString2)
    {
      this.packName = paramString1;
      this.context = paramContext;
      this.icon = paramInt;
      this.name = paramString2;
      this.isAd = true;
    }
    
    public final boolean equals(Object paramObject)
    {
      if (((paramObject instanceof CharSequence)) && (TextUtils.equals((CharSequence)paramObject, this.packName))) {
        return true;
      }
      return super.equals(paramObject);
    }
  }
}
