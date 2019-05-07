package com.apkinstaller.ApkInstaller.ui;

import android.app.Fragment;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.dj;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import com.apkinstaller.ApkInstaller.d.c;
import com.apkinstaller.ApkInstaller.ui.a.m;
import com.apkinstaller.ApkInstaller.ui.fragment.WebFragment;
import com.apkinstaller.ApkInstaller.ui.fragment.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Installer
  extends AppCompatActivity
  implements dj, View.OnClickListener, SearchView.OnQueryTextListener, com.apkinstaller.ApkInstaller.ui.fragment.b
{
  ImageView n;
  ImageView o;
  SearchView p;
  ViewPager q;
  TabLayout r;
  m s;
  List<String> t;
  public Set<String> u = new HashSet();
  
  public Installer() {}
  
  private void a(int paramInt1, int paramInt2)
  {
    switch (paramInt2)
    {
    }
    Fragment localFragment;
    do
    {
      return;
      localFragment = this.s.c(paramInt2);
    } while ((localFragment == null) || (!(localFragment instanceof f)));
    ((f)localFragment).a(paramInt1);
  }
  
  public final void c()
  {
    this.q.b(1);
  }
  
  public final void c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 1: 
      this.o.setImageResource(2130837666);
      return;
    }
    this.o.setImageResource(2130837668);
  }
  
  public void onBackPressed()
  {
    if (this.q.b() == 0)
    {
      if (!this.p.isIconified()) {
        this.p.onActionViewCollapsed();
      }
    }
    else
    {
      Fragment localFragment = this.s.c(this.q.b());
      if ((localFragment != null) && ((localFragment instanceof WebFragment))) {
        ((WebFragment)localFragment).b();
      }
    }
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 16908332: 
      finish();
      return;
    case 16908313: 
      a(paramView.getId(), this.q.b());
      return;
    }
    a(paramView.getId(), this.q.b());
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903104);
    this.n = ((ImageView)findViewById(16908313));
    this.n.setOnClickListener(this);
    this.o = ((ImageView)findViewById(16908314));
    this.o.setOnClickListener(this);
    findViewById(16908332).setOnClickListener(this);
    this.p = ((SearchView)findViewById(2131558583));
    this.p.setOnQueryTextListener(this);
    com.apkinstaller.ApkInstaller.ui.b.b.a(this.p);
    this.t = new ArrayList();
    this.t.add(getString(2131099845));
    this.t.add(getString(2131099765));
    this.t.add(getString(2131099857));
    this.t.add(getString(2131099770));
    this.t.add(getString(2131099888));
    this.t.add(getString(2131099849));
    this.t.add(getString(2131099912));
    this.t.add(getString(2131099930));
    this.s = new m(getFragmentManager(), this.t);
    this.q = ((ViewPager)findViewById(2131558575));
    this.q.a(this.s);
    this.q.a(this);
    this.r = ((TabLayout)findViewById(2131558582));
    this.r.setupWithViewPager(this.q);
    paramBundle = getPackageManager().getInstalledApplications(0).iterator();
    while (paramBundle.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramBundle.next();
      this.u.add(localApplicationInfo.packageName);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onPageScrollStateChanged(int paramInt) {}
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}
  
  public void onPageSelected(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      this.n.setVisibility(8);
      this.o.setVisibility(8);
      this.p.setVisibility(8);
      return;
    }
    this.n.setVisibility(0);
    this.o.setVisibility(0);
    this.p.setVisibility(0);
  }
  
  public boolean onQueryTextChange(String paramString)
  {
    Object localObject;
    if (this.q.b() == 0)
    {
      localObject = this.s.c(this.q.b());
      if ((localObject != null) && ((localObject instanceof f)))
      {
        localObject = (f)localObject;
        paramString = paramString.trim();
        if (paramString.length() <= 0) {
          break label151;
        }
        ((f)localObject).t.setNotifyOnChange(false);
        ((f)localObject).t.clear();
        Iterator localIterator = ((f)localObject).k.iterator();
        while (localIterator.hasNext())
        {
          c localC = (c)localIterator.next();
          if ((localC.m != null) && (localC.m.toLowerCase(Locale.getDefault()).contains(paramString.toLowerCase(Locale.getDefault())))) {
            ((f)localObject).t.add(localC);
          }
        }
        ((f)localObject).t.notifyDataSetChanged();
      }
    }
    for (;;)
    {
      return true;
      label151:
      ((f)localObject).t.setNotifyOnChange(false);
      ((f)localObject).t.clear();
      ((f)localObject).t.addAll(((f)localObject).k);
      ((f)localObject).t.notifyDataSetChanged();
    }
  }
  
  public boolean onQueryTextSubmit(String paramString)
  {
    this.p.clearFocus();
    return false;
  }
}
