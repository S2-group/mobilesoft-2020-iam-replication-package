package net.easyconn.carman.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import net.easyconn.carman.HomeActivity;
import net.easyconn.carman.common.b.d;
import net.easyconn.carman.common.base.j;
import net.easyconn.carman.common.e.t;
import net.easyconn.carman.common.stats.StatsUtils;
import net.easyconn.carman.common.stats.field.Motion;
import net.easyconn.carman.common.stats.field.NewMotion;
import net.easyconn.carman.phone.PhonePageNewFragment;
import net.easyconn.carman.phone.view.HomePhoneNewView;
import net.easyconn.carman.system.dialog.impl.WhichAppDialog;
import net.easyconn.carman.system.dialog.impl.WhichAppDialog.b;
import net.easyconn.carman.utils.BaseHelper;
import net.easyconn.carman.utils.L;
import net.easyconn.carman.utils.MapHelper;
import net.easyconn.carman.view.home.HomeMoreView;
import net.easyconn.carman.view.home.HomeMoreView.a;
import net.easyconn.carman.view.home.HomeMusicView;
import net.easyconn.carman.view.home.HomeNaviView;
import net.easyconn.carman.view.home.HomeNaviView.a;

public final class AggregationPageFragment
  extends Fragment
  implements net.easyconn.carman.common.b.a, net.easyconn.carman.common.b.b, net.easyconn.carman.common.b.c, d, j, HomeMoreView.a, HomeNaviView.a
{
  public static final String TAG = AggregationPageFragment.class.getSimpleName();
  private HomeActivity mActivity;
  private HomeMoreView mHomeMoreView;
  private HomePhoneNewView mHomePhoneView;
  private int mHomePhoneViewLightMode;
  private HomeMusicView mMusicView;
  private HomeNaviView mNaviView;
  private net.easyconn.carman.common.view.b mSingleClickListener = new net.easyconn.carman.common.view.b()
  {
    public void onSingleClick(View paramAnonymousView)
    {
      L.p(TAG, " onSingleclick");
      paramAnonymousView.getId();
    }
  };
  private RelativeLayout rl_agg_main;
  
  public AggregationPageFragment() {}
  
  private void initListener() {}
  
  private void initParams() {}
  
  private void initView(View paramView)
  {
    this.rl_agg_main = ((RelativeLayout)paramView.findViewById(2131231076));
    this.mHomePhoneView = ((HomePhoneNewView)paramView.findViewById(2131230797));
    this.mNaviView = ((HomeNaviView)paramView.findViewById(2131230842));
    this.mNaviView.setActionListener(this);
    this.mMusicView = ((HomeMusicView)paramView.findViewById(2131230841));
    this.mHomeMoreView = ((HomeMoreView)paramView.findViewById(2131230840));
    this.mHomeMoreView.setOnMoreAction(this);
  }
  
  private void openWebGoogleNavi() {}
  
  private void showMapSelectDialog()
  {
    ArrayList localArrayList = MapHelper.getAvailableMap(this.mActivity);
    if ((localArrayList != null) && (localArrayList.size() > 0))
    {
      final WhichAppDialog localWhichAppDialog = (WhichAppDialog)net.easyconn.carman.common.dialog.a.a(WhichAppDialog.class);
      localWhichAppDialog.setPackageInfos(localArrayList);
      localWhichAppDialog.setTitle(2131558721);
      localWhichAppDialog.setListner(new WhichAppDialog.b()
      {
        public void a(PackageInfo paramAnonymousPackageInfo)
        {
          localWhichAppDialog.dismiss();
          StatsUtils.onActionAndValue(AggregationPageFragment.this.mActivity, NewMotion.GLOBAL_CLICK.value, Motion.WHICH_MAP_SELECTED.getCode(), paramAnonymousPackageInfo.applicationInfo.packageName);
          t.a(AggregationPageFragment.this.getActivity(), "which_map", paramAnonymousPackageInfo.packageName);
          BaseHelper.openApp(AggregationPageFragment.this.mActivity, paramAnonymousPackageInfo.packageName);
        }
      });
      localWhichAppDialog.show();
      return;
    }
    Toast.makeText(this.mActivity, this.mActivity.getString(2131558614), 1).show();
  }
  
  public void backHomeOrGoCompanyBySpeech(String paramString1, String paramString2, net.easyconn.carman.common.d.c paramC) {}
  
  public void backHomeWidgetDriver() {}
  
  public ArrayList<PackageInfo> getAailableMap(Context paramContext)
  {
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      if ((localList != null) && (localList.size() > 0))
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = MapHelper.mWhiteMap.iterator();
        for (;;)
        {
          paramContext = localArrayList;
          if (!localIterator.hasNext()) {
            break;
          }
          paramContext = (String[])localIterator.next();
          if (MapHelper.isAppInstalled(localList, paramContext[1]) != null) {
            localArrayList.add(MapHelper.isAppInstalled(localList, paramContext[1]));
          }
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
  }
  
  public void getDestinationBySpeech(String paramString1, String paramString2, net.easyconn.carman.common.d.c paramC) {}
  
  public int getDriverType()
  {
    return -1;
  }
  
  public int getMapOperatorType()
  {
    return getDriverType();
  }
  
  public void getNearbyByPoiSpeech(String paramString1, String paramString2, int paramInt, net.easyconn.carman.common.d.c paramC) {}
  
  public boolean isCompanyAddressExist()
  {
    return false;
  }
  
  public boolean isConsumeCenter(int paramInt)
  {
    return false;
  }
  
  public boolean isConsumeLeftDown(int paramInt)
  {
    return false;
  }
  
  public boolean isConsumeLeftUp(int paramInt)
  {
    return false;
  }
  
  public boolean isConsumeRightDown(int paramInt)
  {
    return false;
  }
  
  public boolean isConsumeRightUp(int paramInt)
  {
    return false;
  }
  
  public boolean isHomeAddressExist()
  {
    return false;
  }
  
  public boolean isOnHomeWidgetDriver()
  {
    return false;
  }
  
  public boolean miniGoMapPage(int paramInt)
  {
    return false;
  }
  
  public void miniGoMusicPage(int paramInt) {}
  
  public void miniGoPhonePage(int paramInt)
  {
    this.mActivity.replaceFragment(new PhonePageNewFragment(), false);
  }
  
  public boolean miniGoTalkPage(int paramInt)
  {
    return false;
  }
  
  public void musicPlay2Page() {}
  
  public void naviRouteAgain() {}
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }
  
  public void onAddSpeechFragment() {}
  
  public void onAddUserFragment() {}
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mActivity = ((HomeActivity)paramActivity);
  }
  
  public void onBackHomeClick(boolean paramBoolean) {}
  
  public void onBackHomeLongClick() {}
  
  public boolean onBackPressed()
  {
    return false;
  }
  
  public void onCenterKey(int paramInt) {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131361881, null);
    initView(paramLayoutInflater);
    initListener();
    initParams();
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
  }
  
  public void onFragmentBackPressed(boolean paramBoolean) {}
  
  public void onGoCompanyClick(boolean paramBoolean) {}
  
  public void onGoCompanyLongClick() {}
  
  public void onHomeActivityPause() {}
  
  public void onHomeActivityResume() {}
  
  public void onImFragment2Map(String paramString) {}
  
  public boolean onLeftDownKey(int paramInt)
  {
    return false;
  }
  
  public void onLeftMenuHomeClick(int paramInt) {}
  
  public boolean onLeftUpKey(int paramInt)
  {
    return false;
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
  }
  
  public void onMiniGoHome(int paramInt) {}
  
  public boolean onMiniLeftKey(int paramInt)
  {
    if ((paramInt == -93) && (this.mMusicView != null))
    {
      this.mMusicView.onMiniLeftKey();
      return true;
    }
    return false;
  }
  
  public boolean onMiniRightKey(int paramInt)
  {
    if ((paramInt == -93) && (this.mMusicView != null))
    {
      this.mMusicView.onMiniRightKey();
      return true;
    }
    return false;
  }
  
  public void onNormalActionClick()
  {
    String str = t.a(this.mActivity, "which_map", "");
    if ((!TextUtils.isEmpty(str)) && (BaseHelper.openApp(this.mActivity, str))) {
      return;
    }
    showMapSelectDialog();
  }
  
  public void onNormalActionLongClick()
  {
    showMapSelectDialog();
  }
  
  public void onNormalClick()
  {
    this.mActivity.onMoreItemClick();
  }
  
  public void onPause()
  {
    super.onPause();
    if (getUserVisibleHint()) {
      onVisibilityChangedToUser(false, false);
    }
  }
  
  public void onPopUserFragment() {}
  
  public void onResume()
  {
    super.onResume();
    if (getUserVisibleHint()) {
      onVisibilityChangedToUser(true, false);
    }
  }
  
  public boolean onRightDownKey(int paramInt)
  {
    if (paramInt == -93)
    {
      StatsUtils.onAction(this.mActivity, NewMotion.GLOBAL_WRC_CLICK, Motion.PHONE_GENERAL_CLICK_CALL_PHONE_F.toString());
      this.mHomePhoneView.bleClick();
    }
    for (;;)
    {
      return false;
      if (paramInt != -95) {}
    }
  }
  
  public boolean onRightUpKey(int paramInt)
  {
    if (paramInt == -93) {}
    for (;;)
    {
      return false;
      if (paramInt != -95) {}
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
  
  public void onSettingChange(String paramString, int paramInt) {}
  
  public void onSettingChange(String paramString, boolean paramBoolean) {}
  
  public void onSpeechFinishRoutePlan(int paramInt, double paramDouble1, double paramDouble2, String paramString) {}
  
  public void onSpeechFinishRoutePlan(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, String paramString) {}
  
  public void onStopNavigationClick() {}
  
  public void onTopFragmentPop(int paramInt, String paramString) {}
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
  
  public void onVisibilityChangedToUser(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1) {
      if ("AggregationPageFragment" != null)
      {
        com.a.a.b.a("AggregationPageFragment");
        localStringBuilder = new StringBuilder().append("AggregationPageFragment").append(" - display - ");
        if (!paramBoolean2) {
          break label61;
        }
        str = "setUserVisibleHint";
        L.w("MobclickAgent", str);
      }
    }
    label61:
    while ("AggregationPageFragment" == null) {
      for (;;)
      {
        return;
        str = "onResume";
      }
    }
    com.a.a.b.b("AggregationPageFragment");
    StringBuilder localStringBuilder = new StringBuilder().append("AggregationPageFragment").append(" - hidden - ");
    if (paramBoolean2) {}
    for (String str = "setUserVisibleHint";; str = "onPause")
    {
      L.w("MobclickAgent", str);
      return;
    }
  }
  
  public void resetStatus()
  {
    this.rl_agg_main.setPadding((int)getResources().getDimension(2131100307), 0, (int)getResources().getDimension(2131100307), 0);
  }
  
  public void setFullScreen()
  {
    this.rl_agg_main.setPadding(0, 0, 0, 0);
  }
  
  public void setRoomDestination(String paramString1, String paramString2, String paramString3) {}
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    super.setUserVisibleHint(paramBoolean);
    if (isResumed()) {
      onVisibilityChangedToUser(paramBoolean, true);
    }
  }
  
  public void startChat() {}
  
  public void unbindMusicService() {}
}
