package com.noxgroup.app.cleaner;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.ak;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.noxgroup.app.cleaner.common.ui.BaseLinearLayoutActivity;
import com.noxgroup.app.cleaner.common.update.e;
import com.noxgroup.app.cleaner.common.utils.m;
import com.noxgroup.app.cleaner.common.utils.m.a;
import com.noxgroup.app.cleaner.common.utils.u;
import com.noxgroup.app.cleaner.common.utils.u.a;
import com.noxgroup.app.cleaner.common.utils.v;
import com.noxgroup.app.cleaner.common.widget.NoxBallView;
import com.noxgroup.app.cleaner.common.widget.NoxFlipBubbleView;
import com.noxgroup.app.cleaner.model.AnalyticsPostion;
import com.noxgroup.app.cleaner.model.eventbus.DeleteFileEvent;
import com.noxgroup.app.cleaner.model.eventbus.VIPEvent;
import com.noxgroup.app.cleaner.model.net.AdConfigModel;
import com.noxgroup.app.cleaner.model.net.PackageRuleModel;
import com.noxgroup.app.cleaner.module.antivirus.KillVirusActivity;
import com.noxgroup.app.cleaner.module.applock.e.g;
import com.noxgroup.app.cleaner.module.battery.FriendlyReminderActivity;
import com.noxgroup.app.cleaner.module.cleanapp.CleanFilesActivity;
import com.noxgroup.app.cleaner.module.cleanapp.memory.MemoryOPlusPermisstionActivity;
import com.noxgroup.app.cleaner.module.cleanapp.memory.MemoryWhiteListAcivity;
import com.noxgroup.app.cleaner.module.cleanapp.memory.ScanningMemoryActivity;
import com.noxgroup.app.cleaner.module.main.AboutUsActivity;
import com.noxgroup.app.cleaner.module.main.FeedbackActivity;
import com.noxgroup.app.cleaner.module.main.HelpActivity;
import com.noxgroup.app.cleaner.module.main.commonfun.CommonFunActivity;
import com.noxgroup.app.cleaner.module.pay.VIPActivity;
import com.noxgroup.app.cleaner.module.pay.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.i;

public class MainActivity
  extends BaseLinearLayoutActivity
  implements com.noxgroup.app.cleaner.common.e.b, m.a
{
  public static final int a = 2;
  public static final int b = 3;
  @BindView(2131230807)
  Button btMemorySpeed;
  @BindView(2131230808)
  Button btMore;
  @BindView(2131230826)
  Button btnVirus;
  @BindView(2131230827)
  NoxFlipBubbleView bubbleView;
  boolean c = false;
  @BindView(2131230887)
  DrawerLayout drawerlayout;
  @BindView(2131230923)
  FrameLayout flClean;
  private String g = "";
  private boolean h = false;
  private boolean i = false;
  @BindView(2131231044)
  LinearLayout llyBottom;
  @BindView(2131231060)
  TextView mainCleanTag;
  @BindView(2131231079)
  NoxBallView noxBallView;
  @BindView(2131231122)
  ImageView ringShaddowView;
  @BindView(2131231222)
  TextView tvAboutUs;
  @BindView(2131231232)
  TextView tvCheckUpdate;
  @BindView(2131231254)
  TextView tvFacebook;
  @BindView(2131231255)
  TextView tvFeedback;
  @BindView(2131231259)
  TextView tvHelp;
  @BindView(2131231280)
  TextView tvPercentValue;
  @BindView(2131231295)
  Button tvStartScan;
  @BindView(2131231297)
  TextView tvStorgeValue;
  @BindView(2131231313)
  TextView tvVip;
  @BindView(2131231317)
  TextView tvWhiteList;
  
  public MainActivity() {}
  
  private void a(HashMap paramHashMap)
  {
    HashMap localHashMap = new HashMap();
    com.noxgroup.app.cleaner.common.d.d localD = com.noxgroup.app.cleaner.common.d.d.a();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://www.noxcleaner.com/v2/config/clean");
    localStringBuilder.append(com.noxgroup.app.cleaner.common.d.a.a(paramHashMap));
    localD.a(localStringBuilder.toString(), localHashMap, new MainActivity.2(this, this, AdConfigModel.class));
  }
  
  private void j()
  {
    if ((com.noxgroup.app.cleaner.module.applock.e.b.a()) && (com.noxgroup.app.cleaner.common.e.d.a(this))) {
      g.a().a(this);
    }
  }
  
  private void k()
  {
    try
    {
      if ((!com.noxgroup.app.cleaner.common.e.a.a.a().d()) && (com.noxgroup.app.cleaner.common.e.d.e()) && (!com.noxgroup.app.cleaner.common.e.a.a.a().e()))
      {
        d(true);
        this.i = false;
        return;
      }
      d(false);
      this.i = true;
      return;
    }
    catch (Exception localException) {}
  }
  
  private void l()
  {
    Object localObject2 = getApplicationContext().getPackageManager().getInstalledPackages(0);
    Object localObject1 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((ArrayList)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
    }
    localObject2 = new HashMap();
    ((HashMap)localObject2).put("position", Integer.valueOf(8));
    ((HashMap)localObject2).put("search_package_list", localObject1);
    localObject1 = com.noxgroup.app.cleaner.common.d.d.a();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://www.noxcleaner.com/rule/list");
    localStringBuilder.append(com.noxgroup.app.cleaner.common.d.a.a());
    ((com.noxgroup.app.cleaner.common.d.d)localObject1).a(localStringBuilder.toString(), (Map)localObject2, new MainActivity.1(this, this, PackageRuleModel.class));
  }
  
  private void m()
  {
    new MainActivity.a(this).execute(new Object[0]);
    com.noxgroup.app.cleaner.module.main.commonfun.a.a.a(this);
  }
  
  private void n()
  {
    g(2131165572);
    a(getString(2131558449));
    this.ringShaddowView.setImageResource(2131165564);
    this.tvStartScan.setOnClickListener(this);
    this.noxBallView.setOnClickListener(this);
    this.btMemorySpeed.setOnClickListener(this);
    this.btnVirus.setOnClickListener(this);
    this.btMore.setOnClickListener(this);
    this.tvFacebook.setOnClickListener(this);
    this.tvCheckUpdate.setOnClickListener(this);
    this.tvHelp.setOnClickListener(this);
    this.tvFeedback.setOnClickListener(this);
    this.tvAboutUs.setOnClickListener(this);
    this.tvWhiteList.setOnClickListener(this);
    this.tvVip.setOnClickListener(this);
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)this.bubbleView.getLayoutParams();
    localLayoutParams.height = (v.b(getApplicationContext()) / 4);
    this.bubbleView.setLayoutParams(localLayoutParams);
  }
  
  private void o()
  {
    int j = u.a(this, "VERSION_CODE", 0);
    int k = com.noxgroup.app.cleaner.common.utils.d.e(this);
    if (k > j)
    {
      com.noxgroup.app.cleaner.common.b.a.a().a("normal", "first");
      u.a().a("VERSION_CODE", k).a();
      return;
    }
    com.noxgroup.app.cleaner.common.b.a.a().a("normal", "");
  }
  
  public Object a(Object... paramVarArgs)
  {
    paramVarArgs = "none";
    try
    {
      localObject = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext()).getId();
      paramVarArgs = (Object[])localObject;
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = new HashMap();
    ((HashMap)localObject).put("gaid", paramVarArgs);
    return localObject;
  }
  
  public void a(Object paramObject)
  {
    paramObject = (HashMap)paramObject;
    if (paramObject != null) {
      a(paramObject);
    }
  }
  
  public void a(String paramString, int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 6) {
        return;
      }
      startActivity(new Intent(this, CommonFunActivity.class));
      return;
    }
    startActivityForResult(new Intent(this, CleanFilesActivity.class), 2);
  }
  
  public void b(String paramString, int paramInt) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 2))
    {
      this.g = "";
      new MainActivity.a(this).execute(new Object[0]);
    }
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.bubbleView.setDestroy(false);
  }
  
  @ak(b=23)
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361842);
    f(2131231129);
    ButterKnife.bind(this);
    n();
    m();
    e.a(this, false, 999);
    l();
    new m(this).executeOnExecutor(m.THREAD_POOL_EXECUTOR, new Object[0]);
    o();
    if (!c.a().b(this)) {
      c.a().a(this);
    }
    if (h.a().e())
    {
      e(true);
      this.tvVip.setVisibility(0);
    }
    j();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.bubbleView.setDestroy(true);
  }
  
  public void onNoDoubleClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      super.onNoDoubleClick(paramView);
      return;
    case 2131231317: 
      startActivity(new Intent(this, MemoryWhiteListAcivity.class));
      return;
    case 2131231313: 
      startActivity(new Intent(this, VIPActivity.class));
      com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_VIP_MAIN_LEFT);
      return;
    case 2131231259: 
      com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_HELP);
      startActivity(new Intent(this, HelpActivity.class));
      return;
    case 2131231255: 
      startActivity(new Intent(this, FeedbackActivity.class));
      return;
    case 2131231254: 
      com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_LIKE_US);
    }
    try
    {
      com.noxgroup.app.cleaner.common.utils.d.h(this);
      return;
    }
    catch (Exception paramView) {}
    com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_CHECK_UPDATE);
    e.a(this, true, 998);
    return;
    startActivity(new Intent(this, AboutUsActivity.class));
    return;
    startActivity(new Intent(this, VIPActivity.class));
    com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_VIP_TITLE_RIGHT);
    return;
    if ((!com.noxgroup.app.cleaner.common.e.a.a.a().d()) && (com.noxgroup.app.cleaner.common.e.d.e()) && (!com.noxgroup.app.cleaner.common.e.a.a.a().e()))
    {
      startActivity(new Intent(this, FriendlyReminderActivity.class));
      return;
      if (!this.drawerlayout.g(8388611))
      {
        this.drawerlayout.e(8388611);
        return;
        if (!this.c) {
          return;
        }
        this.c = false;
        long l = u.c(this, "lastCleanTime");
        if (System.currentTimeMillis() - l < 180000L)
        {
          if ((System.currentTimeMillis() - u.c(getApplicationContext(), "system_time") < 180000L) && (Build.VERSION.SDK_INT > 22)) {
            l = u.c(getApplicationContext(), "system_cache");
          } else {
            l = 0L;
          }
          if ((l > 0L) && (com.noxgroup.app.cleaner.common.e.a.a.a().c()))
          {
            a("android.permission.WRITE_EXTERNAL_STORAGE", new MainActivity.3(this, l));
          }
          else
          {
            paramView = new Intent();
            paramView.setFlags(268435456);
            paramView.putExtra("mode", 1);
            com.noxgroup.app.cleaner.module.main.success.a.a(this, paramView, false);
          }
        }
        else
        {
          a("android.permission.WRITE_EXTERNAL_STORAGE", this, 1);
        }
        com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_APP_SCAN);
        return;
        startActivity(new Intent(this, KillVirusActivity.class));
        com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_KILL_VIRUS);
        return;
        a("android.permission.WRITE_EXTERNAL_STORAGE", this, 6);
        return;
        if (Build.VERSION.SDK_INT < 26) {
          startActivityForResult(new Intent(this, ScanningMemoryActivity.class), 2);
        } else if (((AppOpsManager)getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), getPackageName()) == 0) {
          startActivityForResult(new Intent(this, ScanningMemoryActivity.class), 2);
        } else {
          startActivityForResult(new Intent(this, MemoryOPlusPermisstionActivity.class), 2);
        }
        com.noxgroup.app.cleaner.common.b.a.a().a(AnalyticsPostion.POSITION_MEMORY_CLEAN);
      }
    }
    return;
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.c = true;
    if (!this.i) {
      k();
    }
  }
  
  @i(a=ThreadMode.MAIN)
  public void onStorageSizeChanged(DeleteFileEvent paramDeleteFileEvent)
  {
    this.g = "";
    new MainActivity.a(this).execute(new Object[0]);
  }
  
  @i(a=ThreadMode.MAIN)
  public void onVIPEvent(VIPEvent paramVIPEvent)
  {
    if ((paramVIPEvent != null) && (paramVIPEvent.getMessage() == 0))
    {
      if (paramVIPEvent.isSupportSubcript())
      {
        e(true);
        this.tvVip.setVisibility(0);
        return;
      }
      e(false);
      this.tvVip.setVisibility(8);
    }
  }
}
