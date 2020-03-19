package com.dragonflow.genie.main.ui;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.dragonflow.common.system.CommonContext;
import com.dragonflow.common.system.CommonString;
import com.dragonflow.common.system.CommonSystem;
import com.dragonflow.genie.common.pojo.SSOUserInfo;
import com.dragonflow.genie.common.preferences.PreferencesCloud;
import com.dragonflow.genie.common.tools.CommonRouterInfo;
import com.dragonflow.genie.common.widget.CircleImageView;
import java.util.List;

public class MainUserInfoFragment
  extends Fragment
{
  private MainActivity activity;
  private CircleImageView imageView_usericon;
  private ImageView img_dropdown_ico;
  private LinearLayout main_linlayout_app_cloud;
  private LinearLayout main_linlayout_app_genie;
  private LinearLayout main_linlayout_app_insight;
  private LinearLayout main_linlayout_app_support;
  private TextView main_userinfo_email;
  private TextView main_userinfo_name;
  private SSOUserInfo ssoUserInfo;
  
  public MainUserInfoFragment() {}
  
  private void InitData()
  {
    this.main_userinfo_email.setText(CommonRouterInfo.getCloudemail());
    this.ssoUserInfo = PreferencesCloud.CreateInstance().get_AllSSOUserInfo();
    if (this.ssoUserInfo != null)
    {
      this.main_userinfo_name.setText(this.ssoUserInfo.getFirst_Name() + " " + this.ssoUserInfo.getLast_Name());
      createUserIcon();
    }
  }
  
  private void createUserIcon()
  {
    int i = CommonSystem.dipTopx(48);
    int j = CommonSystem.dipTopx(48);
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.ssoUserInfo != null)
    {
      if (!CommonString.isEmpty(this.ssoUserInfo.getLast_Name())) {
        localStringBuffer.append(this.ssoUserInfo.getLast_Name().toUpperCase().charAt(0));
      }
      if (!CommonString.isEmpty(this.ssoUserInfo.getFirst_Name())) {
        localStringBuffer.append(this.ssoUserInfo.getFirst_Name().toUpperCase().charAt(0));
      }
    }
    Paint localPaint = new Paint(1);
    localPaint.setColor(ContextCompat.getColor(getActivity(), 2131755076));
    localCanvas.drawCircle(i / 2, j / 2, i / 2, localPaint);
    localPaint = new Paint(1);
    localPaint.setTextSize(CommonSystem.dipTopx(20));
    localPaint.setColor(-1);
    localPaint.setFakeBoldText(true);
    float f1 = localPaint.measureText(localStringBuffer.toString());
    f1 = (i - f1) / 2.0F;
    float f2 = (j - (localPaint.getFontMetrics().bottom - localPaint.getFontMetrics().top)) / 2.0F;
    float f3 = Math.abs(localPaint.getFontMetrics().top);
    localCanvas.drawText(localStringBuffer.toString(), f1, f2 + f3, localPaint);
    this.imageView_usericon.setImageBitmap(localBitmap);
  }
  
  private boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private boolean openApp(String paramString)
  {
    Object localObject = getActivity().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getLaunchIntentForPackage(paramString);
      if (localObject == null) {
        try
        {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
          return false;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString)));
          return false;
        }
      }
      paramString = ((Intent)localActivityNotFoundException).addCategory("android.intent.category.LAUNCHER");
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    if (PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()) != null)
    {
      paramString.putExtra("token", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getToken());
      paramString.putExtra("email", CommonRouterInfo.getCloudemail());
      paramString.putExtra("customer_ID", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getCustomer_ID());
      paramString.putExtra("countryCode", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getCountryCode());
      paramString.putExtra("x_cloudID", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getX_cloudID());
      paramString.putExtra("first_Name", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getFirst_Name());
      paramString.putExtra("last_Name", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getLast_Name());
    }
    getActivity().startActivity(paramString);
    return true;
  }
  
  private void showChangePasswrodDialog(final PopupWindow paramPopupWindow, TextView paramTextView)
  {
    paramTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramPopupWindow.dismiss();
        MainUserInfoFragment.this.startActivity(new Intent(MainUserInfoFragment.this.getActivity(), ChangePasswordActivity.class));
      }
    });
  }
  
  private void showUserInfoDropdownView(View paramView)
  {
    PopupWindow localPopupWindow = new PopupWindow(-2, -2);
    Object localObject = LayoutInflater.from(getActivity()).inflate(2130968752, null);
    ((View)localObject).measure(0, 0);
    localPopupWindow.setContentView((View)localObject);
    localPopupWindow.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getActivity(), 2131755096)));
    localObject = (TextView)((View)localObject).findViewById(2131821534);
    localPopupWindow.setOutsideTouchable(true);
    localPopupWindow.setTouchable(true);
    localPopupWindow.setFocusable(true);
    localPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
    {
      public void onDismiss()
      {
        if (MainUserInfoFragment.this.img_dropdown_ico != null) {
          MainUserInfoFragment.this.img_dropdown_ico.setImageResource(2130903098);
        }
      }
    });
    localPopupWindow.showAsDropDown(paramView);
    if (this.img_dropdown_ico != null) {
      this.img_dropdown_ico.setImageResource(2130903099);
    }
    if (localPopupWindow.isShowing()) {
      showChangePasswrodDialog(localPopupWindow, (TextView)localObject);
    }
  }
  
  private void startAPP(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      if (isAvilible(CommonContext.getInstance(), paramString1))
      {
        paramString3 = new Intent();
        paramString3.setComponent(new ComponentName(paramString1, paramString2));
        if (PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()) != null)
        {
          paramString3.putExtra("token", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getToken());
          paramString3.putExtra("email", CommonRouterInfo.getCloudemail());
          paramString3.putExtra("customer_ID", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getCustomer_ID());
          paramString3.putExtra("countryCode", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getCountryCode());
          paramString3.putExtra("x_cloudID", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getX_cloudID());
          paramString3.putExtra("first_Name", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getFirst_Name());
          paramString3.putExtra("last_Name", PreferencesCloud.CreateInstance().get_SSOUserInfo(CommonRouterInfo.getCloudemail()).getLast_Name());
        }
        startActivity(paramString3);
        return;
      }
      try
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString3)));
        return;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.activity = ((MainActivity)getActivity());
    ((LinearLayout)getView().findViewById(2131821355)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainUserInfoFragment.this.showUserInfoDropdownView(paramAnonymousView);
      }
    });
    this.imageView_usericon = ((CircleImageView)getView().findViewById(2131821354));
    this.img_dropdown_ico = ((ImageView)getView().findViewById(2131821358));
    this.main_userinfo_email = ((TextView)getView().findViewById(2131821357));
    this.main_userinfo_name = ((TextView)getView().findViewById(2131821356));
    this.main_linlayout_app_support = ((LinearLayout)getView().findViewById(2131821361));
    this.main_linlayout_app_genie = ((LinearLayout)getView().findViewById(2131821362));
    this.main_linlayout_app_genie.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainUserInfoFragment.this.openApp("com.netgear.readycloud");
      }
    });
    this.main_linlayout_app_cloud = ((LinearLayout)getView().findViewById(2131821363));
    this.main_linlayout_app_cloud.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainUserInfoFragment.this.openApp("com.csscorp.gearhead");
      }
    });
    this.main_linlayout_app_insight = ((LinearLayout)getView().findViewById(2131821360));
    this.main_linlayout_app_insight.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainUserInfoFragment.this.openApp("com.netgear.android");
      }
    });
    this.main_linlayout_app_support.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainUserInfoFragment.this.openApp("com.android.netgeargenie");
      }
    });
    ((ImageView)getView().findViewById(2131821359)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          PreferencesCloud.CreateInstance().remove_SSOUserInfo();
          CommonRouterInfo.setCloudpass("");
          CommonRouterInfo.setChangepassword("");
          CommonRouterInfo.setCloudemail("");
          CommonRouterInfo.getCloudDevices().clear();
          CommonRouterInfo.setIsClaimDevice(false);
          paramAnonymousView = new Intent(MainUserInfoFragment.this.getActivity(), MainActivity.class);
          paramAnonymousView.setFlags(335544320);
          ActivityCompat.startActivity(MainUserInfoFragment.this.getActivity(), paramAnonymousView, null);
          MainUserInfoFragment.this.getActivity().finish();
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130968717, paramViewGroup, false);
  }
  
  public void onResume()
  {
    super.onResume();
    InitData();
  }
}
