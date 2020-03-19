package com.tools.c;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.tools.tools.m;
import com.tools.tools.r;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class dl
  extends Fragment
{
  public List a = new ArrayList();
  Handler aj = new dw(this);
  PackageManager b;
  LayoutInflater c;
  ea d;
  SharedPreferences e;
  GridView f;
  LinearLayout g;
  public LinearLayout h;
  int i;
  
  public dl() {}
  
  public void M()
  {
    try
    {
      Object localObject = this.b.getInstalledPackages(0);
      this.a.clear();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (!localPackageInfo.applicationInfo.sourceDir.startsWith("/system")) {
          this.a.add(new dy(this, localPackageInfo));
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.h = ((LinearLayout)paramLayoutInflater.inflate(2130968637, paramViewGroup, false));
    this.h.setBackgroundColor(m.b(h(), 2130771969));
    this.c = paramLayoutInflater;
    return this.h;
  }
  
  public void a()
  {
    this.f.setVisibility(8);
    this.g.setVisibility(0);
    new dx(this).start();
  }
  
  @SuppressLint({"NewApi"})
  public void c()
  {
    super.c();
    this.b = h().getPackageManager();
    this.e = h().getPreferences(0);
    this.d = new ea(this, h());
    this.f = ((GridView)this.h.findViewById(2131427363));
    this.g = ((LinearLayout)this.h.findViewById(2131427334));
    this.f.setAdapter(this.d);
    int k = r.b(h()) / 350;
    int j = k;
    if (k < 1) {
      j = 1;
    }
    this.f.setNumColumns(j);
    this.f.setOnItemClickListener(new dm(this));
    this.f.setOnItemLongClickListener(new dn(this));
    ((Button)this.h.findViewById(2131427355)).setOnClickListener(new dp(this));
    ((Button)this.h.findViewById(2131427356)).setOnClickListener(new du(this));
    ((Button)this.h.findViewById(2131427357)).setOnClickListener(new dv(this));
    this.i = h().getResources().getDimensionPixelSize(2131296273);
  }
  
  public void d(Bundle paramBundle)
  {
    super.d(paramBundle);
  }
  
  public void q()
  {
    super.q();
    a();
  }
}
