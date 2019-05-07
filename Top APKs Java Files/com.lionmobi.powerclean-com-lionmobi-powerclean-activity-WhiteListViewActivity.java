package com.lionmobi.powerclean.activity;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.a.a;
import com.flurry.android.FlurryAgent;
import com.lionmobi.powerclean.model.b.bd;
import com.lionmobi.powerclean.model.b.cf;
import com.lionmobi.powerclean.model.bean.d;
import com.lionmobi.util.fontIcon.FontIconDrawable;
import de.greenrobot.event.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WhiteListViewActivity
  extends e
{
  Intent a = null;
  bz b;
  ArrayList c = new ArrayList();
  ArrayList d = new ArrayList();
  String e = "WhitelistView";
  private ListView f;
  private TextView g;
  private View h;
  private List i = new ArrayList();
  private List j = new ArrayList();
  private Set k = new HashSet();
  private a l;
  private Thread m = null;
  
  public WhiteListViewActivity() {}
  
  public void addwhitelist(View paramView)
  {
    this.a = new Intent(this, WhiteListAddActivity.class);
    startActivity(this.a);
  }
  
  public ArrayList getAllApp()
  {
    localArrayList = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    try
    {
      List localList = localPackageManager.getInstalledPackages(0);
      int n = 0;
      while (n < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(n);
        d localD = new d();
        localD.setAppLable(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
        localD.setPkgName(localPackageInfo.packageName);
        localArrayList.add(localD);
        n += 1;
      }
      return localArrayList;
    }
    catch (Exception localException) {}
  }
  
  public Drawable getPackageIcon(String paramString)
  {
    try
    {
      PackageManager localPackageManager = getApplication().getPackageManager();
      paramString = localPackageManager.getApplicationInfo(paramString, 0).loadIcon(localPackageManager);
      return paramString;
    }
    catch (Exception paramString) {}
    return getResources().getDrawable(17301651);
  }
  
  public ArrayList getwhitelist()
  {
    int i1 = 0;
    ArrayList localArrayList1 = new ArrayList();
    Object localObject = getPackageManager();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    Resources localResources = getResources();
    int n = i1;
    if (this.j.size() > 0)
    {
      Iterator localIterator = this.j.iterator();
      for (;;)
      {
        n = i1;
        if (!localIterator.hasNext()) {
          break;
        }
        String str = (String)localIterator.next();
        n = i1;
        if (str == null) {
          break;
        }
        d localD = new d();
        for (;;)
        {
          try
          {
            ApplicationInfo localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(str, 0);
            if (localApplicationInfo != null)
            {
              localD.setPkgName(str);
              localD.setAppLable(localApplicationInfo.loadLabel((PackageManager)localObject).toString());
              if (!this.k.contains(str)) {
                break label205;
              }
              localD.setVersionName(localResources.getString(2131165191));
            }
            if (!this.k.contains(str)) {
              break label220;
            }
            localArrayList3.add(localD);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
          break;
          label205:
          localD.setVersionName(localResources.getString(2131165194));
        }
        label220:
        localArrayList2.add(localD);
      }
    }
    if (n < this.c.size())
    {
      if ((((d)this.c.get(n)).getAppLable() != null) && (!((d)this.c.get(n)).getAppLable().isEmpty()))
      {
        localObject = ((d)this.c.get(n)).getPkgName();
        if ((this.i.contains(localObject)) && (!this.j.contains(localObject)))
        {
          if (!this.k.contains(localObject)) {
            break label379;
          }
          ((d)this.c.get(n)).setVersionName(localResources.getString(2131165191));
          localArrayList3.add(this.c.get(n));
        }
      }
      for (;;)
      {
        n += 1;
        break;
        label379:
        ((d)this.c.get(n)).setVersionName(localResources.getString(2131165194));
        localArrayList2.add(this.c.get(n));
      }
    }
    localArrayList1.addAll(localArrayList2);
    localArrayList1.addAll(localArrayList3);
    return localArrayList1;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903092);
    this.b = new bz(this, this);
    this.f = ((ListView)findViewById(2131427766));
    this.f.setVisibility(8);
    this.f.setAdapter(this.b);
    this.g = ((TextView)findViewById(2131427509));
    this.h = findViewById(2131427526);
    this.h.setVisibility(0);
    this.l = new a(this);
    ((a)this.l.id(2131427502)).clicked(this, "onReturn");
    ((a)this.l.id(2131427373)).clicked(this, "onReturn");
    ((a)this.l.id(2131427764)).clicked(this, "addwhitelist");
    ((a)this.l.id(2131427373)).text(2131165822);
    ((a)this.l.id(2131427507)).image(FontIconDrawable.inflate(this, 2131034127));
    ((a)((a)this.l.id(2131427388)).image(FontIconDrawable.inflate(this, 2131034126))).clicked(this, "onReturn");
    if (getIntent().getBooleanExtra("showTips", false)) {
      ((a)((a)this.l.id(2131427765)).text(2131165502)).visible();
    }
    if (!c.getDefault().isRegistered(this)) {
      c.getDefault().register(this);
    }
  }
  
  protected void onDestroy()
  {
    if (c.getDefault().isRegistered(this)) {
      c.getDefault().unregister(this);
    }
    super.onDestroy();
  }
  
  public void onEventMainThread(bd paramBd)
  {
    this.i = paramBd.getIgnorecurrpkglist();
    this.j = paramBd.getIgnoreuserpkg();
    this.k = paramBd.getIgnoresysdefpkg();
    this.d = getwhitelist();
    Collections.sort(this.d);
    paramBd = this.d.size();
    this.g.setText(paramBd);
    this.b.notifyDataSetChanged();
    this.f.setVisibility(0);
    this.h.setVisibility(8);
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    try
    {
      Object localObject2 = getPackageManager();
      Object localObject1 = ((PackageManager)localObject2).getActivityInfo(getComponentName(), 0);
      localObject2 = ((ActivityInfo)localObject1).loadLabel((PackageManager)localObject2).toString();
      String str = getResources().getString(2131165922);
      if ((localObject2 != null) && (!"".equals(localObject2)) && (!str.equals(localObject2)))
      {
        FlurryAgent.logEvent((String)localObject2);
        return;
      }
      localObject1 = ((ActivityInfo)localObject1).name;
      FlurryAgent.logEvent(((String)localObject1).substring(((String)localObject1).lastIndexOf(".") + 1));
      return;
    }
    catch (Exception localException) {}
  }
  
  public void onReturn(View paramView)
  {
    finish();
  }
  
  protected void onStart()
  {
    super.onStart();
    if (!c.getDefault().isRegistered(this)) {
      c.getDefault().register(this);
    }
    this.m = new Thread(new Runnable()
    {
      public final void run()
      {
        WhiteListViewActivity.this.c = WhiteListViewActivity.this.getAllApp();
        c.getDefault().post(new cf(cf.b));
      }
    });
    this.m.start();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
}
