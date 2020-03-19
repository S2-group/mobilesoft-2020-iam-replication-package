package com.hideitpro.apps;

import a.a.a.b.a;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.b;
import android.support.v7.app.c;
import android.support.v7.app.c.a;
import android.support.v7.preference.i;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.h.a;
import android.support.v7.view.menu.n;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hideitpro.util.ActivityLogout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HiddenAppsParent
  extends ActivityLogout
  implements AppPickerDialogFragment.AppPickerInterface
{
  AppPickerDialogFragment appPickerDialogFragment;
  private ArrayList<App> hiddenApps = new ArrayList();
  private HiddenAppsAdapter hiddenAppsAdapter;
  LayoutInflater inflater;
  private PackageManager pm;
  View root;
  boolean shouldRestartLaunchers;
  SwipeRefreshLayout swipeRefreshLayout;
  TextView tapAndHoldHint;
  
  public HiddenAppsParent() {}
  
  private void addAppToHiddenApps(App paramApp, int paramInt)
  {
    this.shouldRestartLaunchers = true;
    int i = paramInt;
    if (paramInt < 0) {
      i = this.hiddenApps.size();
    }
    if (!this.hiddenApps.contains(paramApp))
    {
      this.hiddenApps.add(i, paramApp);
      this.hiddenAppsAdapter.notifyItemInserted(i);
    }
    String str = getLastLaunchedPackage();
    if ((str != null) && (str.equals(paramApp.packageName))) {
      setLastLaunchedPackage(null);
    }
    shouldShowTooltip();
  }
  
  private String getLastLaunchedPackage()
  {
    return i.a(this).getString("lhlp", null);
  }
  
  private void removeAppFromHiddenApps(App paramApp)
  {
    int i = this.hiddenApps.indexOf(paramApp);
    if (i >= 0)
    {
      this.hiddenApps.remove(i);
      this.hiddenAppsAdapter.notifyItemRemoved(i);
    }
    this.shouldRestartLaunchers = true;
    shouldShowTooltip();
  }
  
  private void restartLauncher()
  {
    new Thread()
    {
      public void run()
      {
        Object localObject = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject).addCategory("android.intent.category.HOME");
        localObject = HiddenAppsParent.this.pm.queryIntentActivities((Intent)localObject, 0);
        int i = 0;
        while (i < ((List)localObject).size())
        {
          String str = ((ResolveInfo)((List)localObject).get(i)).activityInfo.packageName;
          b.a.a("am force-stop " + str);
          i += 1;
        }
      }
    }.start();
    this.shouldRestartLaunchers = false;
  }
  
  private void setLastLaunchedPackage(String paramString)
  {
    SharedPreferences.Editor localEditor = i.a(this).edit();
    if (paramString == null)
    {
      localEditor.remove("lhlp");
      return;
    }
    localEditor.putString("lhlp", paramString).apply();
  }
  
  private void setupView()
  {
    this.root = findViewById(2131296598);
    this.tapAndHoldHint = ((TextView)findViewById(2131296664));
    this.swipeRefreshLayout = ((SwipeRefreshLayout)findViewById(2131296653));
    this.swipeRefreshLayout.setColorSchemeResources(new int[] { 2131099702, 2131099840, 2131099995 });
    this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.b()
    {
      public void onRefresh()
      {
        new HiddenAppsParent.FindHiddenAppsTask(HiddenAppsParent.this, null).execute(new Integer[0]);
      }
    });
    this.hiddenAppsAdapter = new HiddenAppsAdapter(null);
    Object localObject = (RecyclerView)findViewById(2131296583);
    ((RecyclerView)localObject).setLayoutManager(new GridLayoutManager(this, getResources().getInteger(2131361807), 1, false));
    ((RecyclerView)localObject).setAdapter(this.hiddenAppsAdapter);
    localObject = getLastCustomNonConfigurationInstance();
    if (localObject != null)
    {
      this.hiddenApps = ((RetainData)localObject).hiddenApps;
      return;
    }
    new FindHiddenAppsTask(null).execute(new Integer[0]);
  }
  
  private void shouldShowTooltip()
  {
    if (this.hiddenApps.size() == 0)
    {
      View localView = findViewById(2131296491);
      if (localView != null) {
        showTooltip(localView, getString(2131689732));
      }
      for (;;)
      {
        this.tapAndHoldHint.setVisibility(4);
        return;
        showSuccess(getString(2131689731));
      }
    }
    this.tapAndHoldHint.setVisibility(0);
    hideTooltip();
  }
  
  private void showHiddenAppsOptions(final App paramApp, View paramView)
  {
    h localH = new h(this);
    String[] arrayOfString = this.r.getStringArray(2130903041);
    int i = 0;
    while (i < arrayOfString.length)
    {
      localH.add(0, i, 0, arrayOfString[i]);
      i += 1;
    }
    localH.a(new h.a()
    {
      public boolean onMenuItemSelected(h paramAnonymousH, MenuItem paramAnonymousMenuItem)
      {
        switch (paramAnonymousMenuItem.getItemId())
        {
        default: 
          return true;
        case 0: 
          new HiddenAppsParent.LaunchApp(HiddenAppsParent.this, paramApp).execute(new Void[0]);
          return true;
        case 1: 
          new HiddenAppsParent.UnHideApp(HiddenAppsParent.this, paramApp).execute(new Void[0]);
          return true;
        }
        new c.a(HiddenAppsParent.this).b(HiddenAppsParent.this.getString(2131689730, new Object[] { paramApp.title })).a(2131689623, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            new HiddenAppsParent.DeleteAppTask(HiddenAppsParent.this, HiddenAppsParent.7.this.val$selectedApp).execute(new String[0]);
          }
        }).b(17039360, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        }).a().show();
        return true;
      }
      
      public void onMenuModeChange(h paramAnonymousH) {}
    });
    new n(this, localH, paramView).a();
  }
  
  private void showHideDialog(final App paramApp)
  {
    c.a localA = new c.a(this);
    localA.a(17039379, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        new HiddenAppsParent.HideApp(HiddenAppsParent.this, paramApp).execute(new Void[0]);
        if (HiddenAppsParent.this.appPickerDialogFragment != null) {
          HiddenAppsParent.this.appPickerDialogFragment.dismiss();
        }
      }
    });
    localA.b(17039369, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localA.a(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localA.b(getString(2131689733, new Object[] { paramApp.title }));
    localA.a().show();
  }
  
  private void showNoRoot()
  {
    this.root.setVisibility(8);
    try
    {
      new c.a(this).b(2131689760).a(2131689727).a(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          HiddenAppsParent.this.finish();
        }
      }).a().show();
      return;
    }
    catch (Exception localException) {}
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onAppSelected(App paramApp)
  {
    showHideDialog(paramApp);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(2131689729);
    getSupportActionBar().a(true);
    this.pm = getPackageManager();
    this.inflater = getLayoutInflater();
    setContentView(2131427378);
    setupView();
    new CheckRoot().execute(new Void[0]);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131492865, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      this.appPickerDialogFragment = new AppPickerDialogFragment();
      this.appPickerDialogFragment.show(getFragmentManager(), "pick");
    }
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.shouldRestartLaunchers) {
      restartLauncher();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    String str = getLastLaunchedPackage();
    if (str != null) {
      new HideApp(str).execute(new Void[0]);
    }
  }
  
  public Object onRetainCustomNonConfigurationInstance()
  {
    RetainData localRetainData = new RetainData(null);
    localRetainData.hiddenApps = this.hiddenApps;
    return localRetainData;
  }
  
  class CheckRoot
    extends AsyncTask<Void, Void, Boolean>
  {
    CheckRoot() {}
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      return Boolean.valueOf(b.a.a());
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (isCancelled()) {}
      do
      {
        return;
        HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(false);
      } while (paramBoolean.booleanValue());
      HiddenAppsParent.this.showNoRoot();
    }
    
    protected void onPreExecute()
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(true);
    }
  }
  
  private class DeleteAppTask
    extends AsyncTask<String, Integer, Boolean>
  {
    App app;
    
    DeleteAppTask(App paramApp)
    {
      this.app = paramApp;
    }
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      b.a.a("pm uninstall " + this.app.packageName);
      return Boolean.valueOf(true);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(false);
      if ((paramBoolean != null) && (paramBoolean.booleanValue()))
      {
        HiddenAppsParent.this.removeAppFromHiddenApps(this.app);
        return;
      }
      HiddenAppsParent.this.showToast(HiddenAppsParent.this.r.getString(2131689678));
    }
    
    protected void onPreExecute()
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(true);
    }
  }
  
  private class FindHiddenAppsTask
    extends AsyncTask<Integer, Void, ArrayList<App>>
  {
    private FindHiddenAppsTask() {}
    
    protected ArrayList<App> doInBackground(Integer... paramVarArgs)
    {
      paramVarArgs = new ArrayList();
      try
      {
        Iterator localIterator = HiddenAppsParent.this.pm.getInstalledApplications(128).iterator();
        while (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          boolean bool = localApplicationInfo.enabled;
          if (!bool) {
            try
            {
              paramVarArgs.add(new App(localApplicationInfo, HiddenAppsParent.this.pm));
            }
            catch (Exception localException2)
            {
              com.crashlytics.android.a.a(localException2);
            }
          }
        }
        return paramVarArgs;
      }
      catch (Exception localException1)
      {
        com.crashlytics.android.a.a(localException1);
      }
    }
    
    protected void onPostExecute(ArrayList<App> paramArrayList)
    {
      HiddenAppsParent.access$502(HiddenAppsParent.this, paramArrayList);
      HiddenAppsParent.this.hiddenAppsAdapter.notifyDataSetChanged();
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(false);
      HiddenAppsParent.this.shouldShowTooltip();
    }
    
    protected void onPreExecute()
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(true);
      HiddenAppsParent.this.hiddenApps.clear();
      HiddenAppsParent.this.hiddenAppsAdapter.notifyDataSetChanged();
    }
  }
  
  private class HiddenAppsAdapter
    extends RecyclerView.Adapter<HiddenAppsParent.HiddenAppsHolder>
  {
    private HiddenAppsAdapter() {}
    
    public int getItemCount()
    {
      if (HiddenAppsParent.this.hiddenApps != null) {
        return HiddenAppsParent.this.hiddenApps.size();
      }
      return 0;
    }
    
    public void onBindViewHolder(HiddenAppsParent.HiddenAppsHolder paramHiddenAppsHolder, int paramInt)
    {
      paramHiddenAppsHolder.bindItem((App)HiddenAppsParent.this.hiddenApps.get(paramInt));
    }
    
    public HiddenAppsParent.HiddenAppsHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      return new HiddenAppsParent.HiddenAppsHolder(HiddenAppsParent.this, HiddenAppsParent.this.inflater.inflate(2131427379, paramViewGroup, false));
    }
  }
  
  class HiddenAppsHolder
    extends RecyclerView.ViewHolder
  {
    ImageView icon;
    View item;
    TextView title;
    
    HiddenAppsHolder(View paramView)
    {
      super();
      this.item = paramView;
      this.title = ((TextView)paramView.findViewById(2131296664));
      this.icon = ((ImageView)paramView.findViewById(2131296460));
    }
    
    void bindItem(final App paramApp)
    {
      this.title.setText(paramApp.title);
      this.icon.setImageDrawable(paramApp.icon);
      this.item.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          new HiddenAppsParent.LaunchApp(HiddenAppsParent.this, paramApp).execute(new Void[0]);
        }
      });
      this.item.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          HiddenAppsParent.this.showHiddenAppsOptions(paramApp, paramAnonymousView);
          return true;
        }
      });
    }
  }
  
  private class HideApp
    extends AsyncTask<Void, Void, Boolean>
  {
    App app;
    String packageName;
    
    HideApp(App paramApp)
    {
      this.app = paramApp;
    }
    
    HideApp(String paramString)
    {
      this.packageName = paramString;
    }
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      boolean bool = false;
      if (this.packageName != null) {}
      try
      {
        this.app = new App(HiddenAppsParent.this.pm.getPackageInfo(this.packageName, 128).applicationInfo, HiddenAppsParent.this.pm);
        if (this.app != null)
        {
          paramVarArgs = this.app.packageName;
          b.a.a("pm disable " + paramVarArgs);
          if (HiddenAppsParent.this.pm.getApplicationEnabledSetting(paramVarArgs) == 2) {
            bool = true;
          }
          return Boolean.valueOf(bool);
        }
      }
      catch (PackageManager.NameNotFoundException paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
      return Boolean.valueOf(false);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(false);
      if (paramBoolean.booleanValue()) {
        HiddenAppsParent.this.addAppToHiddenApps(this.app, -1);
      }
      HiddenAppsParent.this.shouldShowTooltip();
    }
    
    protected void onPreExecute()
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(true);
      HiddenAppsParent.this.hideTooltip();
    }
  }
  
  private class LaunchApp
    extends AsyncTask<Void, Integer, Boolean>
  {
    App app;
    
    LaunchApp(App paramApp)
    {
      this.app = paramApp;
    }
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      boolean bool = true;
      b.a.a("pm enable " + this.app.packageName);
      if (HiddenAppsParent.this.pm.getApplicationEnabledSetting(this.app.packageName) == 1) {}
      for (;;)
      {
        return Boolean.valueOf(bool);
        bool = false;
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(false);
      if (paramBoolean.booleanValue()) {}
      try
      {
        HiddenAppsParent.this.startActivity(HiddenAppsParent.this.pm.getLaunchIntentForPackage(this.app.packageName));
        HiddenAppsParent.this.startActivityForResult(HiddenAppsParent.this.pm.getLaunchIntentForPackage(this.app.packageName), 0);
        HiddenAppsParent.this.removeAppFromHiddenApps(this.app);
        HiddenAppsParent.this.setLastLaunchedPackage(this.app.packageName);
        return;
      }
      catch (Exception paramBoolean)
      {
        for (;;)
        {
          HiddenAppsParent.this.showError(HiddenAppsParent.this.getString(2131689932));
        }
      }
    }
    
    protected void onPreExecute()
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(true);
    }
  }
  
  private class RetainData
  {
    ArrayList<App> hiddenApps;
    
    private RetainData() {}
  }
  
  private class UnHideApp
    extends AsyncTask<Void, Void, Boolean>
  {
    App app;
    
    UnHideApp(App paramApp)
    {
      this.app = paramApp;
    }
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      boolean bool = true;
      b.a.a("pm enable " + this.app.packageName);
      if ((HiddenAppsParent.this.getLastLaunchedPackage() != null) && (HiddenAppsParent.this.getLastLaunchedPackage().equals(this.app.packageName))) {
        HiddenAppsParent.this.setLastLaunchedPackage(null);
      }
      if (HiddenAppsParent.this.pm.getApplicationEnabledSetting(this.app.packageName) == 1) {}
      for (;;)
      {
        return Boolean.valueOf(bool);
        bool = false;
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(false);
      if (paramBoolean.booleanValue())
      {
        HiddenAppsParent.this.removeAppFromHiddenApps(this.app);
        return;
      }
      HiddenAppsParent.this.showToast(HiddenAppsParent.this.getString(2131689678));
    }
    
    protected void onPreExecute()
    {
      HiddenAppsParent.this.swipeRefreshLayout.setRefreshing(true);
    }
  }
}
