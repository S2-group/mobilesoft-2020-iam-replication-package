package io.wifimap.wifimap.ui.fragments;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import io.wifimap.wifimap.WiFiMapApplication;
import io.wifimap.wifimap.social.FacebookLogin;
import io.wifimap.wifimap.social.GoogleLogin;
import io.wifimap.wifimap.social.VKLogin;
import io.wifimap.wifimap.ui.Dialogs;
import io.wifimap.wifimap.ui.Progress.Dialog;
import io.wifimap.wifimap.ui.activities.BaseActivity;
import io.wifimap.wifimap.utils.Analytics;
import io.wifimap.wifimap.utils.Str;
import io.wifimap.wifimap.utils.ViewUtils;
import java.util.Iterator;
import java.util.List;

public class LoginFragment
  extends BaseFragment
{
  CallbackManager a;
  private Progress.Dialog b;
  @InjectView(2131689774)
  EditText emailText;
  @InjectView(2131689991)
  Button facebookButton;
  @InjectView(2131689805)
  FrameLayout frameLayoutBanner;
  @InjectView(2131689990)
  TextView termsText;
  @InjectView(2131689993)
  Button vkButton;
  
  public LoginFragment()
  {
    super(false);
  }
  
  private void a(String paramString1, String paramString2)
  {
    new LoginFragment.3(this, this, true, paramString1, paramString2).f();
  }
  
  private boolean a(String paramString)
  {
    if (Str.a(paramString))
    {
      this.emailText.requestFocus();
      ViewUtils.a(this.emailText);
      return false;
    }
    return paramString.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
  }
  
  private void l()
  {
    m();
    this.termsText.setText(Html.fromHtml(getString(2131231228)));
    this.termsText.setOnClickListener(new LoginFragment.1(this));
    this.emailText.setOnEditorActionListener(new LoginFragment.2(this));
  }
  
  private void m()
  {
    Iterator localIterator = b().getPackageManager().getInstalledApplications(0).iterator();
    int j = 0;
    int i = 0;
    if (localIterator.hasNext())
    {
      String str = ((ApplicationInfo)localIterator.next()).packageName;
      int k = -1;
      switch (str.hashCode())
      {
      default: 
        switch (k)
        {
        default: 
          label80:
          k = j;
          j = i;
          i = k;
        }
        break;
      }
      for (;;)
      {
        k = j;
        j = i;
        i = k;
        break;
        if (!str.equals("com.facebook.katana")) {
          break label80;
        }
        k = 0;
        break label80;
        if (!str.equals("com.vkontakte.android")) {
          break label80;
        }
        k = 1;
        break label80;
        i = j;
        j = 1;
        continue;
        k = 1;
        j = i;
        i = k;
      }
    }
    if (i == 0) {
      this.facebookButton.setVisibility(8);
    }
    if (j == 0) {
      this.vkButton.setVisibility(8);
    }
  }
  
  @OnClick({2131689995})
  void a()
  {
    if (!e())
    {
      Analytics.e("Email");
      return;
    }
    Analytics.c("Email");
    String str = this.emailText.getText().toString().trim().toLowerCase();
    if (!a(str))
    {
      Analytics.a("Login choose: invalid email", new String[0]);
      Analytics.e("Email");
      Dialogs.a(2131230980, getActivity());
      return;
    }
    Analytics.a("Login choose: start login", new String[] { "method", "email" });
    ViewUtils.b(this.emailText);
    a(str, "");
  }
  
  @OnClick({2131689993})
  void i()
  {
    Analytics.c("VK");
    if (!e())
    {
      Analytics.e("VK");
      return;
    }
    Analytics.a("Login choose: start login", new String[] { "method", "VK" });
    VKLogin.a(b(), this.b);
  }
  
  @OnClick({2131689991})
  void j()
  {
    Analytics.c("Facebook");
    if (!e())
    {
      Analytics.e("Facebook");
      return;
    }
    Analytics.a("Login choose: start login", new String[] { "method", "Facebook" });
    FacebookLogin.a(b(), this.b, this.a);
  }
  
  @OnClick({2131689992})
  void k()
  {
    Analytics.c("Google");
    if (!e())
    {
      Analytics.e("Google");
      return;
    }
    Analytics.a("Login choose: start login", new String[] { "method", "Google" });
    GoogleLogin.a(b());
  }
  
  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.b = new Progress.Dialog(b());
    l();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.a.onActivityResult(paramInt1, paramInt2, paramIntent);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
    AppEventsLogger.activateApp(WiFiMapApplication.b());
    this.a = CallbackManager.Factory.create();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130968661, paramViewGroup, false);
    ButterKnife.inject(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
}
