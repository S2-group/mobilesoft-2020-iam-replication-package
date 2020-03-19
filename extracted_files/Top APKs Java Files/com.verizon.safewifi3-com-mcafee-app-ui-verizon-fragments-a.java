package com.mcafee.app.ui.verizon.fragments;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mcafee.partner.web.a.a.d;
import com.mcafee.ui.resources.R.id;
import com.mcafee.ui.resources.R.layout;
import java.util.Iterator;
import java.util.List;

public class a
  extends Fragment
  implements com.mcafee.partner.web.ui.c
{
  private static final String a = a.class.getSimpleName();
  private View b;
  
  public a() {}
  
  private boolean a(String paramString)
  {
    Iterator localIterator = j().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public final void O() {}
  
  public final View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.main_vpn_screen, paramViewGroup, false);
    this.b = paramLayoutInflater.findViewById(R.id.upsellBannerFragment);
    paramViewGroup = new com.mcafee.verizon.vpn.a.b(i());
    com.mcafee.app.ui.verizon.settingsFragments.a.a(i(), paramViewGroup.b());
    return paramLayoutInflater;
  }
  
  public final com.mcafee.partner.web.a.c a(Object paramObject)
    throws Exception
  {
    paramObject = com.mcafee.verizon.vpn.b.b(i());
    paramObject = new com.mcafee.partner.web.a.b.e(com.mcafee.verizon.vpn.b.a(i()), paramObject);
    new StringBuilder("Making subscription call ").append(paramObject.toString());
    paramObject = new com.mcafee.partner.web.b(i()).a("https://ispcapi.mcafee.com/ISPCAPIService.svc/VERIZONSP/GetSubscriptionDetails", paramObject);
    if (!TextUtils.isEmpty(paramObject)) {
      return new d().a(paramObject);
    }
    return null;
  }
  
  public final void a(Context paramContext)
  {
    super.a(paramContext);
  }
  
  public final void a(Bundle paramBundle)
  {
    super.a(paramBundle);
    new com.mcafee.partner.web.ui.b(j(), this).a();
  }
  
  public final void a(Object paramObject, com.mcafee.partner.web.a.c paramC)
  {
    paramObject = (com.mcafee.partner.web.a.c.e)paramC;
    new StringBuilder("Making subscription call successful ").append(paramObject.toString());
    if ((a("com.securityandprivacy.android.verizon.vms")) || (a("com.asurion.android.verizon.vms")))
    {
      if (!TextUtils.isEmpty(paramObject.d)) {
        this.b.setVisibility(8);
      }
    }
    else {
      return;
    }
    this.b.setVisibility(0);
  }
  
  public final void b(Object paramObject, com.mcafee.partner.web.a.c paramC)
  {
    paramObject = (com.mcafee.partner.web.a.c.e)paramC;
    new StringBuilder("Making subscription call failure ").append(paramObject.toString());
    if ((a("com.securityandprivacy.android.verizon.vms")) || (a("com.asurion.android.verizon.vms")))
    {
      if (!TextUtils.isEmpty(paramObject.d)) {
        this.b.setVisibility(8);
      }
    }
    else {
      return;
    }
    this.b.setVisibility(0);
  }
  
  public final void e()
  {
    super.e();
  }
  
  public final void f()
  {
    super.f();
  }
}
