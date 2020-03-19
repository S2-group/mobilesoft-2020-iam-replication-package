package com.boo.boomoji.Profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.boo.boomoji.Controller.BooMojiApplication;
import com.boo.boomoji.Controller.LocalDataController.Constant;
import com.boo.boomoji.Controller.LocalDataController.LocalData;
import com.boo.boomoji.Fragment.BaseFragment;
import com.boo.boomoji.Home.HomeActivity;
import com.boo.boomoji.Management.DipperHelp.DipperStatisticsHelper;
import com.boo.boomoji.Profile.ProfilePhoto.ProfileActivity;
import com.boo.boomoji.Utils.GeneralUtils.BooMojiUtils;
import com.boo.boomoji.Utils.GeneralUtils.DevUtil;
import com.boo.boomoji.Utils.GeneralUtils.PrefUtils;
import com.boo.boomoji.View.GeneralView.CustomPopwindow;
import com.boo.boomoji.user.utils.PreferenceManager;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

public class MeFragment
  extends BaseFragment
  implements View.OnClickListener
{
  private static final String TAG = "MeFragment";
  private final int CLICK_TIME = 1000;
  @BindView(2131296331)
  Button btnDownloadBoo;
  private boolean haoveBoo;
  private boolean hasShowProfile;
  private boolean isonclick = false;
  @BindView(2131296591)
  SimpleDraweeView ivBoo;
  @BindView(2131296610)
  ImageView ivMessageNew;
  private LinearLayout ll_feedback;
  private LinearLayout ll_keyboard;
  private LinearLayout ll_rate;
  private LinearLayout ll_setting;
  private LinearLayout ll_share;
  private Handler mHandler = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what != 0) {
        return;
      }
      MeFragment.access$002(MeFragment.this, false);
    }
  };
  private CustomPopwindow mKeyboardWindow;
  private RelativeLayout rl_adress;
  Unbinder unbinder;
  
  public MeFragment() {}
  
  private void initView(View paramView)
  {
    this.rl_adress = ((RelativeLayout)paramView.findViewById(2131296829));
    this.ll_keyboard = ((LinearLayout)paramView.findViewById(2131296652));
    this.ll_share = ((LinearLayout)paramView.findViewById(2131296664));
    this.ll_feedback = ((LinearLayout)paramView.findViewById(2131296645));
    this.ll_rate = ((LinearLayout)paramView.findViewById(2131296662));
    this.ll_setting = ((LinearLayout)paramView.findViewById(2131296663));
    this.rl_adress.setOnClickListener(this);
    this.ll_setting.setOnClickListener(this);
    this.ll_share.setOnClickListener(this);
    this.ll_rate.setOnClickListener(this);
    this.ll_keyboard.setOnClickListener(this);
    this.ll_feedback.setOnClickListener(this);
  }
  
  public static MeFragment newInstance()
  {
    return new MeFragment();
  }
  
  public boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i != 2131296645)
    {
      if (i != 2131296652)
      {
        if (i != 2131296829)
        {
          switch (i)
          {
          default: 
            return;
          case 2131296664: 
            PreferenceManager.getInstance().setBoomojiFromBoo(false);
            if (!DevUtil.isFastClick()) {
              break;
            }
            BooMojiUtils.shareLink(getActivity(), "text/html", String.format(getString(2131624278), new Object[0]), "https://boomoji.app.link/vIQMSIlmKM", String.format(getString(2131624264), new Object[0]), Constant.FROMSETTING);
            return;
          case 2131296663: 
            PreferenceManager.getInstance().setBoomojiFromBoo(false);
            if (!DevUtil.isFastClick()) {
              break;
            }
            paramView = new Intent(getActivity(), SettingActivity.class);
            ((HomeActivity)getActivity()).intentTo(paramView);
            return;
          case 2131296662: 
            PreferenceManager.getInstance().setBoomojiFromBoo(false);
            if (!DevUtil.isFastClick()) {
              break;
            }
            BooMojiApplication.getLocalData().setInt(LocalData.KEY_SHARE_COUNT, 5);
            if (getActivity() == null) {
              break;
            }
            ((HomeActivity)getActivity()).showRatePop();
            return;
          }
        }
        else
        {
          PreferenceManager.getInstance().setBoomojiFromBoo(false);
          if (DevUtil.isFastClick())
          {
            BooMojiApplication.isRunning = true;
            BooMojiApplication.getLocalData().setBoolean(LocalData.KEY_HASSHOWPRO, true);
            paramView = new Intent(getActivity(), ProfileActivity.class);
            ((HomeActivity)getActivity()).intentTo(paramView);
          }
        }
      }
      else
      {
        PreferenceManager.getInstance().setBoomojiFromBoo(false);
        if (DevUtil.isFastClick())
        {
          PrefUtils.setBoolean(getActivity(), Constant.ISSHOWKEYBOARF, false);
          if (getActivity() != null) {
            ((HomeActivity)getActivity()).showKeyBoardPop();
          }
        }
      }
    }
    else
    {
      PreferenceManager.getInstance().setBoomojiFromBoo(false);
      if (DevUtil.isFastClick()) {
        BooMojiUtils.emailUs(getActivity());
      }
    }
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427469, null);
    initView(paramLayoutInflater);
    this.unbinder = ButterKnife.bind(this, paramLayoutInflater);
    BooMojiUtils.loadRefreshLoading(getActivity().getBaseContext(), this.ivBoo, 2131558402);
    this.haoveBoo = BooMojiApplication.getLocalData().getBoolean(LocalData.KEY_HASBOO);
    if (!this.haoveBoo)
    {
      this.btnDownloadBoo.setText(getResources().getString(2131624214));
      return paramLayoutInflater;
    }
    this.btnDownloadBoo.setText(getResources().getString(2131624178));
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
    this.unbinder.unbind();
  }
  
  public void onResume()
  {
    this.hasShowProfile = BooMojiApplication.getLocalData().getBoolean(LocalData.KEY_HASSHOWPRO);
    if (this.hasShowProfile) {
      this.ivMessageNew.setVisibility(8);
    } else {
      this.ivMessageNew.setVisibility(0);
    }
    super.onResume();
    this.haoveBoo = BooMojiApplication.getLocalData().getBoolean(LocalData.KEY_HASBOO);
    if (!isAvilible(getActivity(), "com.boo.chat"))
    {
      this.btnDownloadBoo.setText(getResources().getString(2131624214));
      return;
    }
    this.btnDownloadBoo.setText(getResources().getString(2131624178));
  }
  
  public void onStart()
  {
    super.onStart();
  }
  
  public void onStop()
  {
    super.onStop();
  }
  
  @OnClick({2131296331})
  public void onViewClicked()
  {
    Intent localIntent;
    if (!isAvilible(getActivity(), "com.boo.chat"))
    {
      DipperStatisticsHelper.eventDownLoadBoo(Constant.DOWNLOADFROMME);
      localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(Constant.BOOMOJI_BANNER_ME));
      if (localIntent.resolveActivity(getActivity().getPackageManager()) != null) {
        getActivity().startActivity(localIntent);
      }
    }
    else
    {
      PreferenceManager.getInstance().setBoomojiFromBoo(true);
      localIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.boo.chat");
      getActivity().startActivityForResult(localIntent, 1009);
    }
  }
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
}
