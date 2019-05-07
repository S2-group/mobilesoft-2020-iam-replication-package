package com.simpler.ui.fragments.settings;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.simpler.data.SettingsListItem;
import com.simpler.data.SimplerUser;
import com.simpler.interfaces.OnSettingsInteractionListener;
import com.simpler.logic.LogicManager;
import com.simpler.logic.LoginLogic;
import com.simpler.logic.PackageLogic;
import com.simpler.logic.SettingsLogic;
import com.simpler.ui.adapters.SettingsAdapter;
import com.simpler.ui.fragments.BaseFragment;
import com.simpler.ui.views.AppsPromoView;
import com.simpler.ui.views.AppsPromoView.AppType;
import com.simpler.ui.views.AppsPromoView.OnAppsPromoClickListener;
import com.simpler.ui.views.ProfileView;
import com.simpler.utils.AnalyticsUtils;
import com.simpler.utils.Logger;
import com.simpler.utils.ThemeUtils;
import java.util.Iterator;
import java.util.List;

public class GeneralSettingsFragment
  extends BaseFragment
  implements AdapterView.OnItemClickListener, AppsPromoView.OnAppsPromoClickListener
{
  private OnSettingsInteractionListener a;
  private SettingsLogic b;
  private ListView c;
  private SettingsAdapter d;
  private ProfileView e;
  
  public GeneralSettingsFragment() {}
  
  private void a(String paramString)
  {
    LogicManager.getInstance().getSettingsLogic().openAppInGooglePlay(getActivity(), paramString);
  }
  
  private boolean b(String paramString)
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void onAppPromoClick(AppsPromoView.AppType paramAppType)
  {
    String str = PackageLogic.getInstance().getPackageName(paramAppType);
    if (b(str))
    {
      startActivity(getActivity().getPackageManager().getLaunchIntentForPackage(str));
      AnalyticsUtils.switchToSimplerContacts();
      return;
    }
    a(str);
    AnalyticsUtils.onSettingsAppPromoClick(paramAppType);
  }
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    if ((paramContext instanceof Activity)) {
      paramContext = (Activity)paramContext;
    }
    try
    {
      this.a = ((OnSettingsInteractionListener)paramContext);
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      Logger.e("Simpler", paramContext.getClass().getSimpleName() + " must implement OnSettingsInteractionListener");
      Logger.e("Simpler", localClassCastException);
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903165, paramViewGroup, false);
    this.c = ((ListView)paramLayoutInflater.findViewById(2131558551));
    this.c.setOnItemClickListener(this);
    this.d = new SettingsAdapter(getActivity(), this.b.createGeneralSettingsListItems(getActivity()));
    paramViewGroup = PackageLogic.getInstance().getPromoAppType(getActivity());
    this.c.addFooterView(new AppsPromoView(getActivity(), paramViewGroup, this));
    this.c.setAdapter(this.d);
    setProfileView();
    setThemeValues(paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onGetDataDone(Object paramObject, int paramInt) {}
  
  public void onGetDataError(String paramString, int paramInt) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= 1;
    if (paramInt < 0) {
      if (this.a != null) {
        this.a.onProfileViewClick();
      }
    }
    do
    {
      do
      {
        do
        {
          return;
          paramAdapterView = (SettingsListItem)this.d.getItem(paramInt);
          switch (p.a[paramAdapterView.getSettingsOption().ordinal()])
          {
          default: 
            return;
          case 1: 
            try
            {
              paramView = PackageLogic.getInstance();
              paramAdapterView = paramView.getShareAppSubject(getActivity());
              Object localObject = paramView.getShareAppUrl(getActivity());
              paramView = paramView.getShareAppBody(getActivity()) + ": " + (String)localObject;
              localObject = new Intent("android.intent.action.SEND");
              ((Intent)localObject).setType("text/plain");
              ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", paramAdapterView);
              ((Intent)localObject).putExtra("android.intent.extra.TEXT", paramView);
              startActivity(Intent.createChooser((Intent)localObject, "Share"));
              return;
            }
            catch (ActivityNotFoundException paramAdapterView)
            {
              Logger.e("Simpler", paramAdapterView);
              return;
            }
          case 2: 
            a(PackageLogic.getInstance().getPackageName(getActivity()));
            return;
          }
        } while (this.a == null);
        this.a.onReplaceFragment(SupportFragment.class, getString(2131100086), null);
        return;
      } while (this.a == null);
      this.a.onReplaceFragment(AboutFragment.class, getString(2131099704), null);
      return;
    } while (this.a == null);
    this.a.onReplaceFragment(AdvancedSettingsFragment.class, getString(2131100048), null);
  }
  
  protected void registerToLogic()
  {
    this.b = LogicManager.getInstance().getSettingsLogic();
    this.b.registerUiHandler(getHandler());
  }
  
  public void setProfileView()
  {
    if (this.e == null)
    {
      this.e = new ProfileView(getActivity());
      this.e.showSettingsDivider();
    }
    SimplerUser localSimplerUser = LoginLogic.getInstance().getUser();
    if (localSimplerUser != null)
    {
      this.e.setName(localSimplerUser.getFullName());
      this.e.setEmail(localSimplerUser.getEmail());
    }
    for (;;)
    {
      this.e.setAvatar(localSimplerUser);
      if (this.c.getHeaderViewsCount() < 1) {
        this.c.addHeaderView(this.e);
      }
      this.e.invalidate();
      return;
      this.e.setName(getString(2131100089));
      String str = String.format(getString(2131099858), new Object[] { getString(2131100294) });
      this.e.setEmail(str);
    }
  }
  
  protected void setThemeValues(View paramView)
  {
    paramView.setBackgroundResource(ThemeUtils.getScreenBackgroundColor());
  }
  
  protected void unRegisterFromLogic()
  {
    this.b.unRegisterUiHandler(getHandler());
  }
}
