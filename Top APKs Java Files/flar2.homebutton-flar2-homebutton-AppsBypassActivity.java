package flar2.homebutton;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.j;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.x;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import flar2.homebutton.utils.f;
import flar2.homebutton.utils.i;
import flar2.homebutton.utils.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AppsBypassActivity
  extends f
{
  private List<a> n;
  private RecyclerView o;
  private ProgressBar p;
  private i q;
  private Switch r;
  
  public AppsBypassActivity() {}
  
  private void a(String paramString, boolean paramBoolean)
  {
    HashSet localHashSet = new HashSet(this.q.e("pref_apps_bypass"));
    if (localHashSet.contains(paramString))
    {
      if (!paramBoolean) {
        localHashSet.remove(paramString);
      }
    }
    else {
      localHashSet.add(paramString);
    }
    this.q.a("pref_apps_bypass", localHashSet);
    android.support.v4.a.c.a(getApplicationContext()).a(new Intent("homebutton.intent.action.SETTINGS_CHANGED"));
  }
  
  private List<a> k()
  {
    this.n = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledApplications(128);
    HashSet localHashSet = new HashSet(this.q.e("pref_apps_bypass"));
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      String str = ((ApplicationInfo)localObject).packageName;
      boolean bool;
      if ((Build.VERSION.SDK_INT >= 21) && (MainActivity.a(this)))
      {
        if ((localPackageManager.getLaunchIntentForPackage(str) == null) && (localPackageManager.getLeanbackLaunchIntentForPackage(str) == null)) {
          continue;
        }
        bool = localHashSet.contains(str);
        localList = this.n;
        localObject = new a(str, ((ApplicationInfo)localObject).loadLabel(localPackageManager).toString(), ((ApplicationInfo)localObject).loadIcon(localPackageManager), bool);
      }
      else
      {
        if (localPackageManager.getLaunchIntentForPackage(str) == null) {
          continue;
        }
        bool = localHashSet.contains(str);
        localList = this.n;
        localObject = new a(str, ((ApplicationInfo)localObject).loadLabel(localPackageManager).toString(), ((ApplicationInfo)localObject).loadIcon(localPackageManager), bool);
      }
      localList.add(localObject);
    }
    Collections.sort(this.n, new Comparator()
    {
      public int a(AppsBypassActivity.a paramAnonymousA1, AppsBypassActivity.a paramAnonymousA2)
      {
        return String.CASE_INSENSITIVE_ORDER.compare(paramAnonymousA1.b, paramAnonymousA2.b);
      }
    });
    return this.n;
  }
  
  private List<a> l()
  {
    this.n = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    new HashSet(this.q.e("pref_apps_bypass"));
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      String str = localApplicationInfo.packageName;
      if (localPackageManager.getLaunchIntentForPackage(str) != null)
      {
        this.n.add(new a(str, localApplicationInfo.loadLabel(localPackageManager).toString(), localApplicationInfo.loadIcon(localPackageManager), true));
        a(str, true);
      }
    }
    Collections.sort(this.n, new Comparator()
    {
      public int a(AppsBypassActivity.a paramAnonymousA1, AppsBypassActivity.a paramAnonymousA2)
      {
        return String.CASE_INSENSITIVE_ORDER.compare(paramAnonymousA1.b, paramAnonymousA2.b);
      }
    });
    return this.n;
  }
  
  private List<a> m()
  {
    this.n = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    new HashSet(this.q.e("pref_apps_bypass"));
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      String str = localApplicationInfo.packageName;
      if (localPackageManager.getLaunchIntentForPackage(str) != null)
      {
        this.n.add(new a(str, localApplicationInfo.loadLabel(localPackageManager).toString(), localApplicationInfo.loadIcon(localPackageManager), false));
        a(str, false);
      }
    }
    Collections.sort(this.n, new Comparator()
    {
      public int a(AppsBypassActivity.a paramAnonymousA1, AppsBypassActivity.a paramAnonymousA2)
      {
        return String.CASE_INSENSITIVE_ORDER.compare(paramAnonymousA1.b, paramAnonymousA2.b);
      }
    });
    return this.n;
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    overridePendingTransition(0, 2130771994);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    if (l.a)
    {
      overridePendingTransition(2130771968, 2130771969);
      l.a = false;
    }
    l.b(this);
    overridePendingTransition(2130771995, 17432577);
    super.onCreate(paramBundle);
    setContentView(2131492892);
    a((Toolbar)findViewById(2131362259));
    g().a(true);
    if (Build.VERSION.SDK_INT < 21)
    {
      paramBundle = android.support.v4.a.a.a(this, 2131230745);
      paramBundle.setColorFilter(android.support.v4.a.a.c(this, 17170443), PorterDuff.Mode.SRC_ATOP);
      g().a(paramBundle);
    }
    this.q = new i(this);
    this.p = ((ProgressBar)findViewById(2131362171));
    this.o = ((RecyclerView)findViewById(2131361855));
    this.o.setHasFixedSize(true);
    this.o.setLayoutManager(new LinearLayoutManager(this));
    this.r = ((Switch)findViewById(2131361995));
    this.r.setChecked(this.q.c("pref_global_bypass").booleanValue());
    this.r.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        boolean bool = false;
        if (paramAnonymousBoolean)
        {
          new AppsBypassActivity.b(AppsBypassActivity.this, null).execute(new Void[0]);
          paramAnonymousCompoundButton = AppsBypassActivity.a(AppsBypassActivity.this);
        }
        for (paramAnonymousBoolean = true;; paramAnonymousBoolean = bool)
        {
          paramAnonymousCompoundButton.a("pref_global_bypass", paramAnonymousBoolean);
          return;
          new AppsBypassActivity.e(AppsBypassActivity.this, null).execute(new Void[0]);
          paramAnonymousCompoundButton = AppsBypassActivity.a(AppsBypassActivity.this);
        }
      }
    });
    new c(null).execute(new Void[0]);
  }
  
  class a
  {
    String a;
    String b;
    Drawable c;
    boolean d;
    
    a(String paramString1, String paramString2, Drawable paramDrawable, boolean paramBoolean)
    {
      this.a = paramString1;
      this.b = paramString2;
      this.c = paramDrawable;
      this.d = paramBoolean;
    }
    
    public String a()
    {
      return this.a;
    }
    
    void a(boolean paramBoolean)
    {
      this.d = paramBoolean;
    }
  }
  
  private class b
    extends AsyncTask<Void, Void, List<AppsBypassActivity.a>>
  {
    private b() {}
    
    protected List<AppsBypassActivity.a> a(Void... paramVarArgs)
    {
      return AppsBypassActivity.c(AppsBypassActivity.this);
    }
    
    protected void a(List<AppsBypassActivity.a> paramList)
    {
      AppsBypassActivity.b(AppsBypassActivity.this).setVisibility(8);
      if (paramList != null)
      {
        paramList = new AppsBypassActivity.d(AppsBypassActivity.this, AppsBypassActivity.d(AppsBypassActivity.this));
        AppsBypassActivity.e(AppsBypassActivity.this).setAdapter(paramList);
      }
    }
    
    protected void onPreExecute()
    {
      AppsBypassActivity.b(AppsBypassActivity.this).setVisibility(0);
    }
  }
  
  private class c
    extends AsyncTask<Void, Void, List<AppsBypassActivity.a>>
  {
    private c() {}
    
    protected List<AppsBypassActivity.a> a(Void... paramVarArgs)
    {
      return AppsBypassActivity.g(AppsBypassActivity.this);
    }
    
    protected void a(List<AppsBypassActivity.a> paramList)
    {
      AppsBypassActivity.b(AppsBypassActivity.this).setVisibility(8);
      if (paramList != null)
      {
        paramList = new AppsBypassActivity.d(AppsBypassActivity.this, AppsBypassActivity.d(AppsBypassActivity.this));
        AppsBypassActivity.e(AppsBypassActivity.this).setAdapter(paramList);
      }
    }
    
    protected void onPreExecute()
    {
      AppsBypassActivity.b(AppsBypassActivity.this).setVisibility(0);
    }
  }
  
  private class d
    extends RecyclerView.a<a>
  {
    List<AppsBypassActivity.a> a;
    
    d()
    {
      Object localObject;
      this.a = localObject;
    }
    
    public int a()
    {
      if (this.a != null) {
        return this.a.size();
      }
      return 0;
    }
    
    public a a(ViewGroup paramViewGroup, int paramInt)
    {
      return new a(LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492905, paramViewGroup, false));
    }
    
    public void a(a paramA, final int paramInt)
    {
      paramA.o.setText(((AppsBypassActivity.a)this.a.get(paramInt)).b);
      paramA.p.setImageDrawable(((AppsBypassActivity.a)this.a.get(paramInt)).c);
      paramA.q.setChecked(((AppsBypassActivity.a)this.a.get(paramInt)).d);
      paramA.q.setTag(this.a.get(paramInt));
      paramA.a.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (SwitchCompat)paramAnonymousView.findViewById(2131361849);
          AppsBypassActivity.a localA = (AppsBypassActivity.a)paramAnonymousView.getTag();
          paramAnonymousView.setChecked(paramAnonymousView.isChecked() ^ true);
          localA.a(paramAnonymousView.isChecked());
          ((AppsBypassActivity.a)AppsBypassActivity.d.this.a.get(paramInt)).a(paramAnonymousView.isChecked());
          AppsBypassActivity.a(AppsBypassActivity.this, ((AppsBypassActivity.a)AppsBypassActivity.d.this.a.get(paramInt)).a(), paramAnonymousView.isChecked());
        }
      });
    }
    
    class a
      extends RecyclerView.x
    {
      RelativeLayout a;
      TextView o;
      ImageView p;
      SwitchCompat q;
      
      a(View paramView)
      {
        super();
        this.a = ((RelativeLayout)paramView.findViewById(2131361854));
        this.o = ((TextView)paramView.findViewById(2131361851));
        this.p = ((ImageView)paramView.findViewById(2131361850));
        this.q = ((SwitchCompat)paramView.findViewById(2131361849));
      }
    }
  }
  
  private class e
    extends AsyncTask<Void, Void, List<AppsBypassActivity.a>>
  {
    private e() {}
    
    protected List<AppsBypassActivity.a> a(Void... paramVarArgs)
    {
      return AppsBypassActivity.f(AppsBypassActivity.this);
    }
    
    protected void a(List<AppsBypassActivity.a> paramList)
    {
      AppsBypassActivity.b(AppsBypassActivity.this).setVisibility(8);
      if (paramList != null)
      {
        paramList = new AppsBypassActivity.d(AppsBypassActivity.this, AppsBypassActivity.d(AppsBypassActivity.this));
        AppsBypassActivity.e(AppsBypassActivity.this).setAdapter(paramList);
      }
    }
    
    protected void onPreExecute()
    {
      AppsBypassActivity.b(AppsBypassActivity.this).setVisibility(0);
    }
  }
}
