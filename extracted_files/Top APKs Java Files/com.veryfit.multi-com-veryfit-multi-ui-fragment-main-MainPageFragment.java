package com.veryfit.multi.ui.fragment.main;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.friends.Wechat.ShareParams;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.wechat.moments.WechatMoments.ShareParams;
import com.project.library.core.APPCoreServiceListener;
import com.project.library.core.CoreServiceProxy;
import com.project.library.device.cmd.BindUnbindCmd;
import com.project.library.device.cmd.DeviceRestartCmd;
import com.project.library.util.DebugLog;
import com.veryfit.multi.VeryFitApplication;
import com.veryfit.multi.base.BaseFragment;
import com.veryfit.multi.share.AppSharedPreferences;
import com.veryfit.multi.ui.adapter.MainDataAdapter;
import com.veryfit.multi.ui.fragment.MainPageChildFragment;
import com.veryfit.multi.ui.fragment.OnHealthDataChangedListener;
import com.veryfit.multi.ui.fragment.inter.NotifyParentFragment;
import com.veryfit.multi.util.NetUtils;
import com.veryfit.multi.util.ScreenShot;
import com.veryfit.multi.util.ScreenUtil;
import com.veryfit.multi.util.ShareCtrl;
import com.veryfit.multi.util.ShareCtrl.OnShareListener;
import com.veryfit.multi.util.TimeZoneUtil;
import com.veryfit.multi.util.Util;
import com.veryfit.multi.view.BufferDialog;
import com.veryfit.multi.view.CirclePageIndicator;
import com.veryfit.multi.view.DialogUtil;
import com.veryfit.multi.view.DialogUtil.OnShareSelectListener;
import com.veryfit.multi.view.MainPageRelativeLayout;
import com.veryfit.multi.vpeffect.AccordionTransformer;
import com.veryfit.multi.vpeffect.CubeTransformer;
import com.veryfit.multi.vpeffect.DefaultTransformer;
import com.veryfit.multi.vpeffect.DepthPageTransformer;
import com.veryfit.multi.vpeffect.ZoomOutPageTransformer;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainPageFragment
  extends BaseFragment
  implements View.OnClickListener, NotifyParentFragment, ShareCtrl.OnShareListener
{
  private TextView backToday;
  private Calendar c;
  private MainPageChildFragment currentFragment;
  private int dateOffset;
  private CheckBox dateView;
  private BufferDialog dialog = null;
  private Handler handler = new Handler();
  private ImageButton imgBtn_share;
  private boolean isPrepared = false;
  private boolean isTimeZoneChanged = false;
  private boolean isTimeZoneReStart = false;
  private boolean isTimeZoneReconnect = false;
  private AppSharedPreferences mAppSharedPreferences = AppSharedPreferences.getInstance();
  private CoreServiceProxy mCore = CoreServiceProxy.getInstance();
  private APPCoreServiceListener mCoreServiceListener = new APPCoreServiceListener()
  {
    public void onBLEConnected()
    {
      if (MainPageFragment.this.getActivity() == null) {}
      do
      {
        return;
        MainPageFragment.this.handTimeZone();
        if (MainPageFragment.this.isTimeZoneReconnect)
        {
          DebugLog.d("TimeZone重启");
          MainPageFragment.this.isTimeZoneReconnect = false;
          MainPageFragment.this.isTimeZoneReStart = true;
          MainPageFragment.this.mCore.write(DeviceRestartCmd.getInstance().getDeviceRestartCmd());
        }
      } while (!MainPageFragment.this.isTimeZoneReStart);
      DebugLog.d("TimeZone重启成功");
      MainPageFragment.this.isTimeZoneReStart = false;
      MainPageFragment.this.scrollView.onStartUpdate2();
      MainPageFragment.this.dialog.setTitle(MainPageFragment.this.getActivity().getResources().getString(2131100162));
      MainPageFragment.this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          MainPageFragment.this.dialog.dismiss();
        }
      }, 2000L);
    }
    
    public void onBindUnbind(byte paramAnonymousByte)
    {
      if (paramAnonymousByte == 20)
      {
        DebugLog.e("TimeZone解绑成功");
        String str = AppSharedPreferences.getInstance().getBindDeviceAddr();
        if (!TextUtils.isEmpty(str))
        {
          MainPageFragment.this.isTimeZoneReconnect = true;
          MainPageFragment.this.mCore.connect(str);
        }
      }
    }
    
    public void onSyncData(int paramAnonymousInt)
    {
      DebugLog.d("process=" + paramAnonymousInt);
      MainPageFragment.this.scrollView.syncData(paramAnonymousInt);
      if ((paramAnonymousInt == 100) && (MainPageFragment.this.isTimeZoneChanged))
      {
        DebugLog.d("TimeZone解绑");
        MainPageFragment.this.isTimeZoneChanged = false;
        MainPageFragment.this.mCore.writeForce(BindUnbindCmd.getInstance().getUnbindCmd());
      }
    }
  };
  private OnHealthDataChangedListener mOnHealthDataChangedListener = null;
  private View mRootView = null;
  private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener()
  {
    public void onPageScrollStateChanged(int paramAnonymousInt) {}
    
    public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
    
    public void onPageSelected(int paramAnonymousInt)
    {
      DebugLog.d("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%*****" + paramAnonymousInt);
      if (paramAnonymousInt == 0) {
        MainPageFragment.this.mAppSharedPreferences.setIsRealTime(true);
      }
      for (;;)
      {
        MainPageFragment.this.currentFragment = ((MainPageChildFragment)MainPageFragment.this.pagerAdapter.getItem(paramAnonymousInt));
        MainPageFragment.this.currentFragment.setNotifyParentFragment(MainPageFragment.this);
        return;
        MainPageFragment.this.mAppSharedPreferences.setIsRealTime(false);
      }
    }
  };
  private ViewPager pager;
  private MainDataAdapter pagerAdapter;
  private MainPageRelativeLayout scrollView;
  
  public MainPageFragment() {}
  
  private void initView()
  {
    setNavigationBar();
    this.c = Calendar.getInstance();
    this.backToday = ((TextView)this.mRootView.findViewById(2131231009));
    this.backToday.setOnClickListener(this);
    this.scrollView = ((MainPageRelativeLayout)this.mRootView.findViewById(2131231004));
    this.scrollView.init(this);
    this.dateView = ((CheckBox)this.mRootView.findViewById(2131231007));
    setDate(this.dateOffset);
    this.dateView.setOnClickListener(this);
    this.pager = ((ViewPager)this.mRootView.findViewById(2131230978));
    this.pagerAdapter = new MainDataAdapter(getChildFragmentManager());
    this.pager.setAdapter(this.pagerAdapter);
    this.pager.setPageTransformer(true, new CubeTransformer());
    this.currentFragment = ((MainPageChildFragment)this.pagerAdapter.getItem(0));
    this.currentFragment.setNotifyParentFragment(this);
    CirclePageIndicator localCirclePageIndicator = (CirclePageIndicator)this.mRootView.findViewById(2131230841);
    localCirclePageIndicator.setViewPager(this.pager);
    localCirclePageIndicator.setOnPageChangeListener(this.pageChangeListener);
    this.imgBtn_share = ((ImageButton)this.mRootView.findViewById(2131231006));
    this.imgBtn_share.setOnClickListener(this);
    this.mCore.addListener(this.mCoreServiceListener);
  }
  
  private boolean isInstalled(String paramString)
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)localIterator.next();
    } while ((paramString == null) || (!paramString.equals(localPackageInfo.packageName)));
    return true;
  }
  
  private void setDate(int paramInt)
  {
    if (paramInt == 0)
    {
      this.dateView.setText(2131099813);
      return;
    }
    this.dateView.setText(Util.formatToMonthAndDate(this.c, -paramInt));
  }
  
  private void setNavigationBar()
  {
    if (getActivity() != null) {
      ScreenUtil.setImmersiveStatusBar(getActivity());
    }
  }
  
  public void handTimeZone()
  {
    DebugLog.d("TimeZone=" + TimeZoneUtil.getCurrentTimeZone());
    if (this.dialog == null) {
      this.dialog = new BufferDialog(getActivity());
    }
    if (!this.mAppSharedPreferences.getTimeZone().equals(""))
    {
      if (!this.mAppSharedPreferences.getTimeZone().equals(TimeZoneUtil.getCurrentTimeZone()))
      {
        this.mAppSharedPreferences.setTimeZone(TimeZoneUtil.getCurrentTimeZone());
        DebugLog.d("TimeZone切换了时区");
        this.isTimeZoneChanged = true;
        this.scrollView.onStartUpdate2();
        this.dialog.setTitle(getActivity().getResources().getString(2131100163));
        this.dialog.show();
      }
      return;
    }
    this.mAppSharedPreferences.setTimeZone(TimeZoneUtil.getCurrentTimeZone());
  }
  
  public boolean isHealthDataSyncing()
  {
    return this.scrollView.isHealthDataSyncing();
  }
  
  protected void lazyLoad()
  {
    if ((this.isPrepared) && (!getUserVisibleHint())) {}
  }
  
  public void notifyParentReloadMyDate(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.dateView.setChecked(true);
      setDate(paramInt);
      return;
    }
    setDate(this.dateOffset);
    this.dateView.setChecked(false);
    this.currentFragment.onReloadData(this.dateOffset);
  }
  
  public void onCancel(int paramInt)
  {
    switch (paramInt)
    {
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131231008: 
    default: 
      return;
    case 2131231007: 
      setDate(this.dateOffset);
      this.currentFragment.onDateClick(this.dateOffset);
      return;
    case 2131231009: 
      onClickGoToTheDay(0);
      Calendar.getInstance();
      VeryFitApplication.getInstance();
      VeryFitApplication.date = -1L;
      this.currentFragment.onReloadData(0);
      return;
    }
    if (!NetUtils.isConnected(getActivity()))
    {
      Toast.makeText(getActivity(), 2131100051, 0).show();
      return;
    }
    DialogUtil.showShareDialog(getActivity(), new DialogUtil.OnShareSelectListener()
    {
      public void onCancel() {}
      
      public void onShareSelect(int paramAnonymousInt)
      {
        if (NetUtils.isConnected(MainPageFragment.this.getActivity()))
        {
          Log.d(MainPageFragment.this.getTag(), "share:=" + paramAnonymousInt);
          switch (paramAnonymousInt)
          {
          case 4: 
          default: 
            return;
          case 5: 
            ShareCtrl.shareToFacebook(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          case 2: 
            Log.d(MainPageFragment.this.getTag(), "SHARE_TYEP_WECHAT");
            ShareCtrl.shareToWeChat(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          case 3: 
            Log.d(MainPageFragment.this.getTag(), "SHARE_TYEP_FIRENDS");
            ShareCtrl.shareToMoments(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          case 1: 
            ShareCtrl.shareToQQ(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          case 6: 
            ShareCtrl.shareToTwitter(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          case 7: 
            ShareCtrl.shareToInstagram(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          case 9: 
            if (MainPageFragment.this.isInstalled("com.whatsapp"))
            {
              ShareCtrl.shareToWhatsApp(MainPageFragment.this.getActivity(), MainPageFragment.this);
              return;
            }
            Util.showToast(MainPageFragment.this.getActivity(), 2131100194, 1);
            return;
          case 8: 
            ShareCtrl.shareToLinkedin(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          case 10: 
            ShareCtrl.shareToEmail(MainPageFragment.this.getActivity(), MainPageFragment.this);
            return;
          }
          ShareCtrl.shareToFlickr(MainPageFragment.this.getActivity(), MainPageFragment.this);
          return;
        }
        Toast.makeText(MainPageFragment.this.getActivity(), 2131100051, 0).show();
      }
    });
  }
  
  public void onClickGoToTheDay(int paramInt)
  {
    int i = 0;
    this.dateOffset = paramInt;
    VeryFitApplication.dateOffset = paramInt;
    setDate(paramInt);
    this.dateView.setChecked(false);
    TextView localTextView = this.backToday;
    if (paramInt != 0) {}
    for (paramInt = i;; paramInt = 8)
    {
      localTextView.setVisibility(paramInt);
      return;
    }
  }
  
  public void onComplete(final int paramInt)
  {
    getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        switch (paramInt)
        {
        case 1: 
        case 2: 
        case 3: 
        case 4: 
        case 7: 
        case 9: 
        case 10: 
        default: 
          return;
        case 5: 
          MainPageFragment.this.showToastShort(2131100058);
          return;
        case 6: 
          MainPageFragment.this.showToastShort(2131100059);
          return;
        case 8: 
          MainPageFragment.this.showToastShort(2131100189);
          return;
        }
        MainPageFragment.this.showToastShort(2131100191);
      }
    });
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (this.mRootView != null)
    {
      paramLayoutInflater = (ViewGroup)this.mRootView.getParent();
      if (paramLayoutInflater != null) {
        paramLayoutInflater.removeView(this.mRootView);
      }
    }
    for (;;)
    {
      return this.mRootView;
      this.mRootView = paramLayoutInflater.inflate(2130903097, paramViewGroup, false);
      initView();
      VeryFitApplication.dateOffset = this.dateOffset;
      this.isPrepared = true;
      lazyLoad();
    }
  }
  
  public void onDateScrolling(int paramInt)
  {
    setDate(paramInt);
  }
  
  public void onError(final int paramInt)
  {
    if (getActivity() == null) {
      return;
    }
    getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        switch (paramInt)
        {
        default: 
          return;
        case 1: 
          MainPageFragment.this.showToastShort(2131100053);
          return;
        case 2: 
        case 3: 
          MainPageFragment.this.showToastShort(2131100054);
          return;
        case 4: 
          MainPageFragment.this.showToastShort(2131100055);
          return;
        case 5: 
          MainPageFragment.this.showToastShort(2131100056);
          return;
        case 6: 
          MainPageFragment.this.showToastShort(2131100057);
          return;
        case 7: 
          MainPageFragment.this.showToastShort(2131100182);
          return;
        case 9: 
          MainPageFragment.this.showToastShort(2131100183);
          return;
        case 8: 
          MainPageFragment.this.showToastShort(2131100184);
          return;
        case 10: 
          MainPageFragment.this.showToastShort(2131100185);
          return;
        }
        MainPageFragment.this.showToastShort(2131100186);
      }
    });
    Log.d(getTag(), "onError:" + paramInt);
  }
  
  public void onHealthDataChanged()
  {
    DebugLog.e("数据同步完成，通知子fragment更新数据");
    if (this.currentFragment != null) {
      this.currentFragment.onHealthDataChanged();
    }
    if (this.mOnHealthDataChangedListener != null) {
      this.mOnHealthDataChangedListener.OnHealthDataChanged();
    }
  }
  
  public void onPause()
  {
    this.scrollView.removeAppListener();
    super.onPause();
  }
  
  public void onResume()
  {
    this.scrollView.setLinkState();
    this.scrollView.addAppListener();
    super.onResume();
    boolean bool = this.scrollView.hasSync;
    if ((this.mCore.isDeviceConnected()) && (getActivity() != null)) {
      handTimeZone();
    }
  }
  
  public void onRootViewCreate(View paramView) {}
  
  public void onThemeChanged()
  {
    DebugLog.e("主页收到主题切换的通知");
  }
  
  public void onVisible()
  {
    super.onVisible();
  }
  
  public void setOnHealthDataChanged(OnHealthDataChangedListener paramOnHealthDataChangedListener)
  {
    this.mOnHealthDataChangedListener = paramOnHealthDataChangedListener;
  }
  
  public void setTransfromer(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      this.pager.setPageTransformer(true, new DefaultTransformer());
      return;
    case 1: 
      this.pager.setPageTransformer(true, new DepthPageTransformer());
      return;
    case 2: 
      this.pager.setPageTransformer(true, new CubeTransformer());
      return;
    case 3: 
      this.pager.setPageTransformer(true, new AccordionTransformer());
      return;
    }
    this.pager.setPageTransformer(true, new ZoomOutPageTransformer());
  }
  
  public void shareToMoments(final Activity paramActivity)
  {
    new File(Environment.getExternalStorageDirectory() + "/s_ido.png").delete();
    ScreenShot.shoot(paramActivity);
    WechatMoments.ShareParams localShareParams = new WechatMoments.ShareParams();
    localShareParams.setShareType(4);
    localShareParams.setImagePath(Environment.getExternalStorageDirectory() + "/s_ido.png");
    Platform localPlatform = ShareSDK.getPlatform(WechatMoments.NAME);
    localPlatform.setPlatformActionListener(new PlatformActionListener()
    {
      public void onCancel(Platform paramAnonymousPlatform, int paramAnonymousInt)
      {
        Log.d("ShareCtrl", "shareToMoments--------------------onCancel");
      }
      
      public void onComplete(Platform paramAnonymousPlatform, int paramAnonymousInt, HashMap<String, Object> paramAnonymousHashMap)
      {
        Log.d("ShareCtrl", "shareToMoments--------------------onComplete");
      }
      
      public void onError(Platform paramAnonymousPlatform, int paramAnonymousInt, Throwable paramAnonymousThrowable)
      {
        Log.d("ShareCtrl", "shareToMoments--------------------onError");
        Toast.makeText(paramActivity, "分享失败", 0).show();
      }
    });
    localPlatform.share(localShareParams);
  }
  
  public void shareToWeChat(Activity paramActivity)
  {
    new File(Environment.getExternalStorageDirectory() + "/s_ido.png").delete();
    ScreenShot.shoot(paramActivity);
    paramActivity = new Wechat.ShareParams();
    paramActivity.setShareType(2);
    paramActivity.setImagePath(Environment.getExternalStorageDirectory() + "/s_ido.png");
    Platform localPlatform = ShareSDK.getPlatform(Wechat.NAME);
    localPlatform.setPlatformActionListener(new PlatformActionListener()
    {
      public void onCancel(Platform paramAnonymousPlatform, int paramAnonymousInt)
      {
        Log.d("ShareCtrl", "shareToWeChat--------------------onCancel");
      }
      
      public void onComplete(Platform paramAnonymousPlatform, int paramAnonymousInt, HashMap<String, Object> paramAnonymousHashMap)
      {
        Log.d("ShareCtrl", "shareToWeChat--------------------onComplete");
      }
      
      public void onError(Platform paramAnonymousPlatform, int paramAnonymousInt, Throwable paramAnonymousThrowable)
      {
        Log.d("ShareCtrl", "shareToWeChat--------------------onError");
      }
    });
    localPlatform.share(paramActivity);
  }
}
