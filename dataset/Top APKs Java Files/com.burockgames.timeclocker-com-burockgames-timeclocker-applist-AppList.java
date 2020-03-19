package com.burockgames.timeclocker.applist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.e;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.burockgames.timeclocker.a.d;
import com.burockgames.timeclocker.a.f;
import com.burockgames.timeclocker.a.g;
import com.burockgames.timeclocker.a.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppList
  extends e
{
  static boolean[] a;
  private static int j;
  private static int k;
  private g b;
  private PackageManager c = null;
  private LinearLayout d;
  private ListView e;
  private a f;
  private List<b> g = null;
  private ArrayList<String> h;
  private CheckBox i;
  private int l;
  private int m;
  
  public AppList() {}
  
  private void a(List<b> paramList)
  {
    int n = 0;
    while (n < paramList.size())
    {
      int i2;
      for (int i1 = 0; i1 < paramList.size() - 1; i1 = i2)
      {
        Object localObject = ((b)paramList.get(i1)).b();
        i2 = i1 + 1;
        if (((String)localObject).compareTo(((b)paramList.get(i2)).b()) > 0)
        {
          localObject = (b)paramList.get(i2);
          paramList.set(i2, paramList.get(i1));
          paramList.set(i1, localObject);
        }
      }
      n += 1;
    }
  }
  
  private void b(List<String> paramList)
  {
    int n = 0;
    while (n < paramList.size())
    {
      int i2;
      for (int i1 = 0; i1 < paramList.size() - 1; i1 = i2)
      {
        String str = (String)paramList.get(i1);
        i2 = i1 + 1;
        if (str.compareTo((String)paramList.get(i2)) > 0)
        {
          str = (String)paramList.get(i1);
          paramList.set(i1, paramList.get(i2));
          paramList.set(i2, str);
        }
      }
      n += 1;
    }
  }
  
  private void e()
  {
    int n = this.m;
    if (n == 0) {
      findViewById(2131296561).setBackgroundResource(2131230832);
    } else if (n == 1) {
      findViewById(2131296561).setBackgroundResource(2131230833);
    }
    setSupportActionBar((Toolbar)findViewById(2131296709));
    if (getSupportActionBar() != null)
    {
      getSupportActionBar().b(true);
      getSupportActionBar().a(true);
      getSupportActionBar().a(2131230926);
    }
    ((ImageButton)findViewById(2131296710)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = AppList.this;
        AppList.a(paramAnonymousView, AppList.a(paramAnonymousView));
        paramAnonymousView = new StringBuilder();
        int i = 0;
        while (i < AppList.a(AppList.this).size())
        {
          paramAnonymousView.append((String)AppList.a(AppList.this).get(i));
          paramAnonymousView.append("(&)");
          i += 1;
        }
        g localG = AppList.b(AppList.this);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(AppList.b(AppList.this).a());
        localStringBuilder.append(paramAnonymousView);
        localG.a("chosenApps", localStringBuilder.toString());
        AppList.this.finish();
      }
    });
    this.i = ((CheckBox)findViewById(2131296711));
    this.i.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = (ListView)AppList.this.findViewById(2131296515);
        boolean bool = AppList.c(AppList.this).isChecked();
        int k = 0;
        int i;
        if (!bool)
        {
          i = 0;
          while (i < paramAnonymousView.getChildCount())
          {
            paramAnonymousView.getChildAt(i).setBackground(null);
            i += 1;
          }
          AppList.a(AppList.this).clear();
          i = 0;
          while (i < AppList.a.length)
          {
            AppList.a[i] = false;
            i += 1;
          }
          AppList.a(0);
        }
        else if (AppList.c(AppList.this).isChecked())
        {
          AppList localAppList = AppList.this;
          AppList.a(localAppList, h.b(null, AppList.d(localAppList)));
          i = 0;
          while (i < paramAnonymousView.getChildCount())
          {
            if (!((b)AppList.e(AppList.this).get(i)).e()) {
              paramAnonymousView.getChildAt(i).setBackgroundResource(AppList.f(AppList.this));
            }
            i += 1;
          }
          AppList.a(AppList.this).clear();
          i = 0;
          int j;
          for (;;)
          {
            j = k;
            if (i >= AppList.e(AppList.this).size()) {
              break;
            }
            if (!((b)AppList.e(AppList.this).get(i)).e()) {
              AppList.a(AppList.this).add(((b)AppList.e(AppList.this).get(i)).a());
            }
            i += 1;
          }
          while (j < AppList.a.length)
          {
            if (!((b)AppList.e(AppList.this).get(j)).e()) {
              AppList.a[j] = true;
            }
            j += 1;
          }
          AppList.a(AppList.e(AppList.this).size() - AppList.a());
        }
        paramAnonymousView = new StringBuilder();
        paramAnonymousView.append(AppList.b());
        paramAnonymousView.append(" ");
        paramAnonymousView.append(AppList.this.getResources().getString(2131624043));
        paramAnonymousView = paramAnonymousView.toString();
        AppList.c(AppList.this).setText(paramAnonymousView);
      }
    });
    this.e = ((ListView)findViewById(2131296515));
    this.e.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (((b)AppList.e(AppList.this).get(paramAnonymousInt)).e())
        {
          Toast.makeText(AppList.this.getApplicationContext(), AppList.this.getResources().getString(2131624039), 0).show();
          return;
        }
        if (AppList.a[paramAnonymousInt] == 0)
        {
          AppList.a[paramAnonymousInt] = true;
          paramAnonymousAdapterView = AppList.this;
          AppList.a(paramAnonymousAdapterView, h.b(null, AppList.d(paramAnonymousAdapterView)));
          paramAnonymousView.setBackgroundResource(AppList.f(AppList.this));
          AppList.c();
          if (AppList.b() == AppList.e(AppList.this).size() - AppList.a()) {
            AppList.c(AppList.this).setChecked(true);
          }
          AppList.a(AppList.this).add(((b)AppList.e(AppList.this).get(paramAnonymousInt)).a());
        }
        else if (AppList.a[paramAnonymousInt] != 0)
        {
          AppList.a[paramAnonymousInt] = false;
          paramAnonymousView.setBackground(null);
          AppList.d();
          if (AppList.b() != AppList.e(AppList.this).size() - AppList.a()) {
            AppList.c(AppList.this).setChecked(false);
          }
          AppList.a(AppList.this).remove(((b)AppList.e(AppList.this).get(paramAnonymousInt)).a());
        }
        paramAnonymousAdapterView = new StringBuilder();
        paramAnonymousAdapterView.append(AppList.b());
        paramAnonymousAdapterView.append(" ");
        paramAnonymousAdapterView.append(AppList.this.getResources().getString(2131624043));
        paramAnonymousAdapterView = paramAnonymousAdapterView.toString();
        AppList.c(AppList.this).setText(paramAnonymousAdapterView);
      }
    });
    this.e.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        try
        {
          paramAnonymousAdapterView = AppList.b(AppList.this).b();
          paramAnonymousView = ((b)AppList.e(AppList.this).get(paramAnonymousInt)).a();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramAnonymousView);
          localStringBuilder.append("(&)");
          if (paramAnonymousAdapterView.contains(localStringBuilder.toString()))
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(paramAnonymousView);
            localStringBuilder.append("(&)");
            paramAnonymousAdapterView = paramAnonymousAdapterView.replace(localStringBuilder.toString(), "");
            Toast.makeText(AppList.this.getApplicationContext(), AppList.this.getResources().getString(2131624042), 1).show();
          }
          else
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(paramAnonymousAdapterView);
            localStringBuilder.append(paramAnonymousView);
            localStringBuilder.append("(&)");
            paramAnonymousAdapterView = localStringBuilder.toString();
            Toast.makeText(AppList.this.getApplicationContext(), AppList.this.getResources().getString(2131624041), 1).show();
          }
          AppList.b(AppList.this).a("invalidApps", paramAnonymousAdapterView);
          AppList.g(AppList.this);
          return true;
        }
        catch (Exception paramAnonymousAdapterView)
        {
          paramAnonymousAdapterView.printStackTrace();
        }
        return true;
      }
    });
    this.d = ((LinearLayout)findViewById(2131296495));
    Toast.makeText(getApplicationContext(), getResources().getString(2131624040), 1).show();
  }
  
  private void f()
  {
    new a(null).execute(new String[0]);
  }
  
  private List<b> g()
  {
    int n = Build.VERSION.SDK_INT;
    int i1 = 0;
    if (n >= 26) {
      n = 0;
    } else {
      n = 1;
    }
    Object localObject1 = this.b.a();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject2 = this.c.getInstalledApplications(128);
    ArrayList localArrayList2 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      try
      {
        if (this.c.getLaunchIntentForPackage(localApplicationInfo.packageName) != null)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(localApplicationInfo.packageName);
          ((StringBuilder)localObject3).append("(&)");
          if (!((String)localObject1).contains(((StringBuilder)localObject3).toString())) {
            localArrayList2.add(localApplicationInfo);
          }
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    k = 0;
    while (i1 < localArrayList2.size())
    {
      localObject1 = (ApplicationInfo)localArrayList2.get(i1);
      String str = ((ApplicationInfo)localObject1).packageName;
      localObject3 = ((ApplicationInfo)localObject1).loadLabel(getPackageManager()).toString();
      localObject2 = this.b.b();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("(&)");
      boolean bool = ((String)localObject2).contains(localStringBuilder.toString());
      if (bool) {
        k += 1;
      }
      if (n != 0) {}
      try
      {
        localObject1 = ((ApplicationInfo)localObject1).loadIcon(getPackageManager());
        localObject2 = null;
        break label316;
        localObject2 = d.a(getPackageManager(), str);
        localObject1 = android.support.v4.a.a.a(getApplicationContext(), 2131230892);
      }
      catch (Exception localException1)
      {
        label316:
        for (;;) {}
      }
      localObject1 = android.support.v4.a.a.a(getApplicationContext(), 2131230892);
      localObject2 = null;
      localArrayList1.add(new b(str, (String)localObject3, (Bitmap)localObject2, (Drawable)localObject1, bool));
      i1 += 1;
    }
    return localArrayList1;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(f.a(paramContext, new g(paramContext).p()));
  }
  
  public void onBackPressed()
  {
    super.onStop();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this.b = new g(getApplicationContext());
    this.m = this.b.u();
    int n = this.m;
    if (n == 1) {
      setTheme(2131689764);
    } else if (n == 2) {
      setTheme(2131689765);
    } else if (n == 3) {
      setTheme(2131689766);
    } else if (n == 4) {
      setTheme(2131689767);
    }
    super.onCreate(paramBundle);
    setContentView(2131427362);
    e();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onResume()
  {
    super.onResume();
    f();
  }
  
  @SuppressLint({"StaticFieldLeak"})
  private class a
    extends AsyncTask<String, Integer, Boolean>
  {
    private a() {}
    
    protected Boolean a(String... paramVarArgs)
    {
      AppList.a(AppList.this, new ArrayList());
      AppList.a(0);
      paramVarArgs = AppList.this;
      AppList.a(paramVarArgs, paramVarArgs.getPackageManager());
      paramVarArgs = AppList.this;
      AppList.b(paramVarArgs, AppList.k(paramVarArgs));
      paramVarArgs = AppList.this;
      AppList.c(paramVarArgs, AppList.e(paramVarArgs));
      AppList.a = new boolean[AppList.e(AppList.this).size()];
      int i = 0;
      while (i < AppList.a.length)
      {
        AppList.a[i] = false;
        i += 1;
      }
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          AppList.a(AppList.this, new a(AppList.this, AppList.e(AppList.this), AppList.b(AppList.this).u()));
          AppList.i(AppList.this).setAdapter(AppList.j(AppList.this));
          AppList.c(AppList.this).setChecked(false);
          CheckBox localCheckBox = AppList.c(AppList.this);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("0 ");
          localStringBuilder.append(AppList.this.getResources().getString(2131624043));
          localCheckBox.setText(String.valueOf(localStringBuilder.toString()));
        }
      });
      return null;
    }
    
    protected void a(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      AppList.h(AppList.this).setVisibility(8);
      AppList.i(AppList.this).setVisibility(0);
      AppList.j(AppList.this).notifyDataSetChanged();
      paramBoolean = (ImageButton)AppList.this.findViewById(2131296710);
      CheckBox localCheckBox = (CheckBox)AppList.this.findViewById(2131296711);
      paramBoolean.setClickable(true);
      localCheckBox.setClickable(true);
    }
    
    protected void onPreExecute()
    {
      AppList.h(AppList.this).setVisibility(0);
      AppList.i(AppList.this).setVisibility(8);
      ImageButton localImageButton = (ImageButton)AppList.this.findViewById(2131296710);
      CheckBox localCheckBox = (CheckBox)AppList.this.findViewById(2131296711);
      localImageButton.setClickable(false);
      localCheckBox.setClickable(false);
      super.onPreExecute();
    }
  }
}
