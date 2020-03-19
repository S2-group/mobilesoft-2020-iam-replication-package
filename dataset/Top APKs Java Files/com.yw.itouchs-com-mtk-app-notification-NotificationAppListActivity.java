package com.mtk.app.notification;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.mtk.main.MainActivity;
import com.mtk.main.MainService;
import com.mtk.main.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NotificationAppListActivity
  extends Activity
{
  private LayoutInflater a;
  private Context b;
  private TabHost c = null;
  private ListView d;
  private ListView e;
  private List<Map<String, Object>> f = null;
  private List<Map<String, Object>> g = null;
  private List<Map<String, Object>> h = null;
  private d i = null;
  private c j = null;
  
  public NotificationAppListActivity() {}
  
  private void a()
  {
    this.c = ((TabHost)findViewById(16908306));
    this.c.setup();
    this.c.addTab(this.c.newTabSpec("personal_app").setContent(2131296646).setIndicator(getString(2131755588)));
    this.c.addTab(this.c.newTabSpec("system_app").setContent(2131296647).setIndicator(getString(2131755648)));
  }
  
  private void b()
  {
    TabWidget localTabWidget = this.c.getTabWidget();
    int k = 0;
    while (k < localTabWidget.getChildCount())
    {
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)((TextView)localTabWidget.getChildAt(k).findViewById(16908310)).getLayoutParams();
      k += 1;
    }
  }
  
  private void c()
  {
    c.a().c();
  }
  
  private void d()
  {
    e.a().c();
  }
  
  private void e()
  {
    this.d = ((ListView)findViewById(2131296614));
    this.j = new c(this);
    this.d.setAdapter(this.j);
    this.e = ((ListView)findViewById(2131296615));
    this.i = new d(this);
    this.e.setAdapter(this.i);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131492990);
    this.b = this;
    a();
    b();
    paramBundle = new a(this);
    try
    {
      paramBundle.execute(new String[] { "" });
    }
    catch (Exception paramBundle)
    {
      for (;;) {}
    }
    Toast.makeText(this, 2131755531, 1).show();
    findViewById(2131296311).setOnClickListener(new -..Lambda.NotificationAppListActivity.AHAcCoHc3BRuEDSsdRpXq70X6S4(this));
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558400, paramMenu);
    paramMenu.findItem(2131296623).setVisible(true);
    return true;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    d();
    c();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296623) {
      if (Build.VERSION.SDK_INT < 18) {
        startActivity(MainActivity.a);
      } else {
        startActivity(MainActivity.b);
      }
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  private class a
    extends AsyncTask<String, Integer, Boolean>
  {
    private ProgressDialog b;
    private final Context c;
    
    public a(Context paramContext)
    {
      Log.i("AppManager/NotificationAppList", "LoadPackageTask(), Create LoadPackageTask!");
      this.c = paramContext;
      a();
    }
    
    private void a()
    {
      try
      {
        this.b = new ProgressDialog(this.c);
        this.b.setTitle(2131755594);
        this.b.setMessage(this.c.getString(2131755593));
        this.b.show();
        Log.i("AppManager/NotificationAppList", "createProgressDialog(), ProgressDialog shows");
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    
    private void b()
    {
      for (;;)
      {
        try
        {
          NotificationAppListActivity.a(NotificationAppListActivity.this, new ArrayList());
          NotificationAppListActivity.b(NotificationAppListActivity.this, new ArrayList());
          NotificationAppListActivity.c(NotificationAppListActivity.this, new ArrayList());
          Object localObject1 = e.a().b();
          HashSet localHashSet1 = c.a().b();
          HashSet localHashSet2 = e.a().d();
          Object localObject3 = NotificationAppListActivity.this.getPackageManager().getInstalledPackages(0);
          HashSet localHashSet3 = a.a().b();
          localObject3 = ((List)localObject3).iterator();
          if (((Iterator)localObject3).hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
            if ((localPackageInfo == null) || (localHashSet2.contains(localPackageInfo.packageName))) {
              continue;
            }
            HashMap localHashMap = new HashMap();
            localHashMap.put("package_icon", this.c.getPackageManager().getApplicationIcon(localPackageInfo.applicationInfo));
            localHashMap.put("package_text", this.c.getPackageManager().getApplicationLabel(localPackageInfo.applicationInfo).toString());
            localHashMap.put("package_name", localPackageInfo.packageName);
            if ((!((HashSet)localObject1).contains(localPackageInfo.packageName)) && (!localHashSet1.contains(localPackageInfo.packageName)) && (localHashSet3.contains(localPackageInfo.packageName)))
            {
              bool = true;
              localHashMap.put("package_switch", Boolean.valueOf(bool));
              if (!b.a(localPackageInfo.applicationInfo))
              {
                NotificationAppListActivity.a(NotificationAppListActivity.this).add(localHashMap);
                continue;
              }
              NotificationAppListActivity.c(NotificationAppListActivity.this).add(localHashMap);
            }
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("loadPackageList(), PersonalAppList=");
            ((StringBuilder)localObject1).append(NotificationAppListActivity.a(NotificationAppListActivity.this));
            Log.i("AppManager/NotificationAppList", ((StringBuilder)localObject1).toString());
            return;
          }
        }
        finally {}
        boolean bool = false;
      }
    }
    
    private void c()
    {
      try
      {
        Object localObject1 = new NotificationAppListActivity.b(NotificationAppListActivity.this);
        if (NotificationAppListActivity.a(NotificationAppListActivity.this) != null) {
          Collections.sort(NotificationAppListActivity.a(NotificationAppListActivity.this), (Comparator)localObject1);
        }
        if (NotificationAppListActivity.c(NotificationAppListActivity.this) != null) {
          Collections.sort(NotificationAppListActivity.c(NotificationAppListActivity.this), (Comparator)localObject1);
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("sortPackageList(), PersonalAppList=");
        ((StringBuilder)localObject1).append(NotificationAppListActivity.a(NotificationAppListActivity.this));
        Log.i("AppManager/NotificationAppList", ((StringBuilder)localObject1).toString());
        return;
      }
      finally {}
    }
    
    protected Boolean a(String... paramVarArgs)
    {
      Log.i("AppManager/NotificationAppList", "doInBackground(), Begin load and sort package list!");
      b();
      c();
      return Boolean.valueOf(true);
    }
    
    protected void a(Boolean paramBoolean)
    {
      Log.i("AppManager/NotificationAppList", "onPostExecute(), Load and sort package list complete!");
      NotificationAppListActivity.d(NotificationAppListActivity.this);
      try
      {
        if (this.b != null)
        {
          if (this.b.isShowing()) {
            this.b.dismiss();
          }
          this.b = null;
        }
        return;
      }
      catch (Exception paramBoolean) {}
    }
  }
  
  private class b
    implements Comparator<Map<String, Object>>
  {
    private final String b = "package_text";
    
    public b() {}
    
    public int a(Map<String, Object> paramMap1, Map<String, Object> paramMap2)
    {
      return ((String)paramMap1.get(this.b)).compareToIgnoreCase((String)paramMap2.get(this.b));
    }
  }
  
  private class c
    extends BaseAdapter
  {
    private Activity b;
    
    public c(Context paramContext)
    {
      this.b = ((NotificationAppListActivity)paramContext);
      NotificationAppListActivity.a(NotificationAppListActivity.this, this.b.getLayoutInflater());
    }
    
    public int getCount()
    {
      return NotificationAppListActivity.a(NotificationAppListActivity.this).size() + 2;
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView;
      Object localObject;
      if (paramView == null)
      {
        paramViewGroup = new a();
        localView = NotificationAppListActivity.b(NotificationAppListActivity.this).inflate(2131493006, null);
        localView.setPadding(0, 30, 0, 30);
        paramViewGroup.a = ((TextView)localView.findViewById(2131296654));
        paramViewGroup.b = ((ImageView)localView.findViewById(2131296651));
        paramViewGroup.c = ((Switch)localView.findViewById(2131296653));
        localView.setTag(paramViewGroup);
      }
      else
      {
        localObject = (a)paramView.getTag();
        paramViewGroup = (ViewGroup)localObject;
        localView = paramView;
        if (localObject == null)
        {
          paramViewGroup = new a();
          paramViewGroup.a = ((TextView)paramView.findViewById(2131296654));
          paramViewGroup.b = ((ImageView)paramView.findViewById(2131296651));
          paramViewGroup.c = ((Switch)paramView.findViewById(2131296653));
          paramView.setTag(paramViewGroup);
          localView = paramView;
        }
      }
      paramViewGroup.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          int i = paramInt;
          if (i == 0)
          {
            if (paramAnonymousBoolean)
            {
              MainService.b().m();
              return;
            }
            MainService.b().n();
            return;
          }
          if (i == 1)
          {
            if (paramAnonymousBoolean)
            {
              MainService.b().j();
              return;
            }
            MainService.b().k();
            return;
          }
          paramAnonymousCompoundButton = (Map)NotificationAppListActivity.a(NotificationAppListActivity.this).get(i - 2);
          if (paramAnonymousCompoundButton == null) {
            return;
          }
          paramAnonymousCompoundButton.remove("package_switch");
          paramAnonymousCompoundButton.put("package_switch", Boolean.valueOf(paramAnonymousBoolean));
          paramAnonymousCompoundButton = (String)paramAnonymousCompoundButton.get("package_name");
          if (!paramAnonymousBoolean)
          {
            e.a().a(paramAnonymousCompoundButton);
            a.a().b(paramAnonymousCompoundButton);
            return;
          }
          e.a().b(paramAnonymousCompoundButton);
          c.a().a(paramAnonymousCompoundButton);
          a.a().a(paramAnonymousCompoundButton);
        }
      });
      if (paramInt >= 2)
      {
        paramView = (Map)NotificationAppListActivity.a(NotificationAppListActivity.this).get(paramInt - 2);
        localObject = (Drawable)paramView.get("package_icon");
        paramViewGroup.b.setImageDrawable((Drawable)localObject);
        localObject = (String)paramView.get("package_text");
        paramViewGroup.a.setText((CharSequence)localObject);
        paramView = (Boolean)paramView.get("package_switch");
        paramViewGroup.c.setChecked(paramView.booleanValue());
        return localView;
      }
      boolean bool;
      if (paramInt == 0)
      {
        paramViewGroup.b.setImageResource(2131230821);
        paramViewGroup.a.setText(2131755395);
        bool = MainService.b().l();
        paramViewGroup.c.setChecked(Boolean.valueOf(bool).booleanValue());
        return localView;
      }
      if (paramInt == 1)
      {
        paramViewGroup.b.setImageResource(2131230867);
        paramViewGroup.a.setText(2131755631);
        bool = MainService.b().i();
        paramViewGroup.c.setChecked(Boolean.valueOf(bool).booleanValue());
      }
      return localView;
    }
    
    public class a
    {
      public TextView a;
      public ImageView b;
      public Switch c;
      
      public a() {}
    }
  }
  
  private class d
    extends BaseAdapter
  {
    private Activity b;
    
    public d(Context paramContext)
    {
      this.b = ((NotificationAppListActivity)paramContext);
      NotificationAppListActivity.a(NotificationAppListActivity.this, this.b.getLayoutInflater());
    }
    
    public int getCount()
    {
      return NotificationAppListActivity.c(NotificationAppListActivity.this).size();
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = new a();
        paramViewGroup = NotificationAppListActivity.b(NotificationAppListActivity.this).inflate(2131493006, null);
        paramViewGroup.setPadding(0, 30, 0, 30);
        paramView.a = ((TextView)paramViewGroup.findViewById(2131296654));
        paramView.b = ((ImageView)paramViewGroup.findViewById(2131296651));
        paramView.c = ((Switch)paramViewGroup.findViewById(2131296653));
        paramViewGroup.setTag(paramView);
      }
      else
      {
        localObject1 = (a)paramView.getTag();
        paramViewGroup = paramView;
        paramView = (View)localObject1;
      }
      paramView.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          int i = paramInt;
          paramAnonymousCompoundButton = (Map)NotificationAppListActivity.c(NotificationAppListActivity.this).get(i);
          if (paramAnonymousCompoundButton == null) {
            return;
          }
          paramAnonymousCompoundButton.remove("package_switch");
          paramAnonymousCompoundButton.put("package_switch", Boolean.valueOf(paramAnonymousBoolean));
          paramAnonymousCompoundButton = (String)paramAnonymousCompoundButton.get("package_name");
          if (!paramAnonymousBoolean)
          {
            e.a().a(paramAnonymousCompoundButton);
            a.a().b(paramAnonymousCompoundButton);
            return;
          }
          e.a().b(paramAnonymousCompoundButton);
          c.a().a(paramAnonymousCompoundButton);
          a.a().a(paramAnonymousCompoundButton);
        }
      });
      Object localObject1 = (Map)NotificationAppListActivity.c(NotificationAppListActivity.this).get(paramInt);
      Object localObject2 = (Drawable)((Map)localObject1).get("package_icon");
      paramView.b.setImageDrawable((Drawable)localObject2);
      localObject2 = (String)((Map)localObject1).get("package_text");
      paramView.a.setText((CharSequence)localObject2);
      localObject1 = (Boolean)((Map)localObject1).get("package_switch");
      paramView.c.setChecked(((Boolean)localObject1).booleanValue());
      return paramViewGroup;
    }
    
    public class a
    {
      public TextView a;
      public ImageView b;
      public Switch c;
      
      public a() {}
    }
  }
}
