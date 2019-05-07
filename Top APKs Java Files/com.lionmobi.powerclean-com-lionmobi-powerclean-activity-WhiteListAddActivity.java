package com.lionmobi.powerclean.activity;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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

public class WhiteListAddActivity
  extends e
{
  bx a;
  ArrayList b = null;
  ArrayList c = null;
  private ListView d;
  private View e;
  private LinearLayout f;
  private TextView g;
  private List h = new ArrayList();
  private List i = new ArrayList();
  private Set j = new HashSet();
  private a k;
  private Thread l = null;
  
  public WhiteListAddActivity() {}
  
  public ArrayList getAllApp()
  {
    localArrayList = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    int m = 0;
    try
    {
      while (m < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(m);
        d localD = new d();
        localD.setAppLable(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
        localD.setPkgName(localPackageInfo.packageName);
        localArrayList.add(localD);
        m += 1;
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
    int m = 0;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    Resources localResources = getResources();
    Object localObject1 = null;
    String str;
    Object localObject2;
    if (this.i.size() > 0)
    {
      Iterator localIterator = this.i.iterator();
      while (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        localObject1 = str;
        if (str == null) {
          break;
        }
        localObject1 = new d();
        for (;;)
        {
          try
          {
            ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str, 0);
            if (localApplicationInfo != null)
            {
              ((d)localObject1).setPkgName(str);
              ((d)localObject1).setAppLable(localApplicationInfo.loadLabel(localPackageManager).toString());
              if (!this.j.contains(str)) {
                break label195;
              }
              ((d)localObject1).setVersionName(localResources.getString(2131165191));
            }
            if (!this.j.contains(str)) {
              break label209;
            }
            localArrayList3.add(localObject1);
            localObject1 = str;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            localObject2 = str;
          }
          break;
          label195:
          localObject2.setVersionName(localResources.getString(2131165194));
        }
        label209:
        localArrayList2.add(localObject2);
        localObject2 = str;
      }
    }
    for (;;)
    {
      if (m < this.b.size())
      {
        if ((((d)this.b.get(m)).getAppLable() != null) && (!((d)this.b.get(m)).getAppLable().isEmpty()))
        {
          str = ((d)this.b.get(m)).getPkgName();
          if ((!this.h.contains(str)) && (!this.i.contains(str)))
          {
            if (!this.j.contains(localObject2)) {
              break label365;
            }
            ((d)this.b.get(m)).setVersionName(localResources.getString(2131165191));
            localArrayList3.add(this.b.get(m));
          }
        }
        for (;;)
        {
          m += 1;
          break;
          label365:
          ((d)this.b.get(m)).setVersionName(localResources.getString(2131165194));
          localArrayList2.add(this.b.get(m));
        }
      }
      localArrayList1.addAll(localArrayList2);
      localArrayList1.addAll(localArrayList3);
      return localArrayList1;
      continue;
      localObject2 = null;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903092);
    this.a = new bx(this, this);
    this.d = ((ListView)findViewById(2131427766));
    this.d.setVisibility(0);
    this.e = findViewById(2131427764);
    this.e.setVisibility(8);
    this.f = ((LinearLayout)findViewById(2131427506));
    this.f.setVisibility(8);
    this.g = ((TextView)findViewById(2131427373));
    this.g.setText(2131165188);
    this.k = new a(this);
    ((a)((a)this.k.id(2131427388)).image(FontIconDrawable.inflate(this, 2131034126))).clicked(this, "onReturn");
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
    this.h = paramBd.getIgnorecurrpkglist();
    this.i = paramBd.getIgnoreuserpkg();
    this.j = paramBd.getIgnoresysdefpkg();
    this.c = getwhitelist();
    Collections.sort(this.c);
    this.d.setAdapter(this.a);
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
    if (!c.getDefault().isRegistered(this)) {
      c.getDefault().register(this);
    }
    this.l = new Thread(new Runnable()
    {
      public final void run()
      {
        WhiteListAddActivity.this.b = WhiteListAddActivity.this.getAllApp();
        c.getDefault().post(new cf());
      }
    });
    this.l.start();
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
}
