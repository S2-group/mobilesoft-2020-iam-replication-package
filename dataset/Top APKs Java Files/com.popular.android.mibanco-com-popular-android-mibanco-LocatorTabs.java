package com.popular.android.mibanco;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Instrumentation;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.popular.android.mibanco.base.BaseActivity;
import com.popular.android.mibanco.locator.LocationManager;
import com.popular.android.mibanco.utils.ActionMenuListener;
import com.popular.android.mibanco.utils.AnalyticsHelper;
import com.popular.android.mibanco.utils.BaseActivityHelper;
import com.popular.android.mibanco.utils.FontChanger;
import com.popular.android.mibanco.utils.Utils;
import com.popular.android.mibanco.views.ActionBarBackport;
import com.popular.android.mibanco.views.DialogHolo;
import java.util.List;

@SuppressLint({"NewApi"})
@Instrumented
public class LocatorTabs
  extends TabActivity
  implements TraceFieldInterface
{
  private ActionBarBackport actionMenu;
  private BaseActivityHelper baseActivityHelper = new BaseActivityHelper();
  private LocationManager locationManager;
  private TabHost.OnTabChangeListener tabChangedListener;
  private TabHost tabHost;
  
  public LocatorTabs() {}
  
  private void createActionMenu()
  {
    if (this.actionMenu == null) {
      this.actionMenu = new ActionBarBackport(this);
    }
    this.actionMenu.clearActionMenu();
    this.actionMenu.addActionMenuImageItem(2130837532, null, new ActionMenuListener()
    {
      public void onClick(Context paramAnonymousContext)
      {
        LocatorTabs.this.openOptionsMenu();
      }
    });
  }
  
  private void setTabHost()
  {
    this.tabHost = getTabHost();
    this.tabHost.setup(getLocalActivityManager());
    this.tabChangedListener = new TabHost.OnTabChangeListener()
    {
      public void onTabChanged(String paramAnonymousString)
      {
        LocatorTabs.this.getWindow().setSoftInputMode(3);
      }
    };
    TextView localTextView = (TextView)getLayoutInflater().inflate(2130903106, this.tabHost.getTabWidget(), false);
    localTextView.setText(getResources().getString(2131165251));
    this.tabHost.addTab(this.tabHost.newTabSpec("ATM").setIndicator(localTextView).setContent(new Intent(this, LocatorFacilityList.class)));
    localTextView = (TextView)getLayoutInflater().inflate(2130903107, this.tabHost.getTabWidget(), false);
    localTextView.setText(getString(2131165252));
    Object localObject = new Intent(this, LocatorFacilityList.class);
    ((Intent)localObject).putExtra("branches", true);
    this.tabHost.addTab(this.tabHost.newTabSpec("BRANCHES").setIndicator(localTextView).setContent((Intent)localObject));
    localTextView = (TextView)getLayoutInflater().inflate(2130903108, this.tabHost.getTabWidget(), false);
    localTextView.setText(getString(2131165254));
    localObject = getPackageManager().getInstalledPackages(0);
    int j = 0;
    int i = 0;
    if (i >= ((List)localObject).size())
    {
      i = j;
      label233:
      if (i == 0) {
        break label382;
      }
    }
    for (;;)
    {
      try
      {
        localObject = new Intent().setClass(this, LocatorMap.class);
        ((Intent)localObject).putExtra("from", getIntent().getIntExtra("from", -1));
        this.tabHost.addTab(this.tabHost.newTabSpec("MAP").setIndicator(localTextView).setContent((Intent)localObject));
        this.tabHost.setCurrentTab(0);
        getWindow().setSoftInputMode(3);
        this.tabHost.setOnTabChangedListener(this.tabChangedListener);
        return;
        String str = ((PackageInfo)((List)localObject).get(i)).toString();
        if ((str.contains("google.android.apps.maps")) || (str.contains("google.android.maps")))
        {
          i = 1;
          break label233;
        }
        i += 1;
      }
      catch (Exception localException)
      {
        Log.w("LocatorTabs", localException);
        continue;
      }
      label382:
      Log.e("LocatorTabs", "Did not find required map packages. Map tab will not be available.");
    }
  }
  
  public BaseActivityHelper getBaseActivityHelper()
  {
    return this.baseActivityHelper;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      return;
    }
    switch (paramInt2)
    {
    default: 
      return;
    }
    this.locationManager.connect(this, null);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("LocatorTabs");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "LocatorTabs#onCreate", null);
      super.onCreate(paramBundle);
      this.locationManager = new LocationManager(this);
      App.getApplicationInstance().setLocationManager(this.locationManager);
      if (Build.VERSION.SDK_INT >= 11L)
      {
        getWindow().requestFeature(8);
        getActionBar().show();
      }
      setContentView(2130903109);
      BaseActivity.setActionBarLogoBackButtonForActivity(this);
      paramBundle = (TextView)findViewById(2131099699);
      if (paramBundle != null) {
        paramBundle.setText(getTitle());
      }
      if (Build.VERSION.SDK_INT < 11L) {
        createActionMenu();
      }
      setTabHost();
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "LocatorTabs#onCreate", null);
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    Utils.setupLanguage(this);
    getMenuInflater().inflate(2131558400, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    this.baseActivityHelper.cancelStackedTasks();
    this.baseActivityHelper.dismissStackedDialogs();
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 16908332: 
      onBackPressed();
    }
    for (;;)
    {
      return true;
      new Thread(new Runnable()
      {
        public void run()
        {
          new Instrumentation().sendKeyDownUpSync(82);
        }
      }).start();
      continue;
      startActivity(new Intent(this, Contact.class));
      continue;
      startActivity(new Intent(this, SettingsList.class));
      continue;
      showLogoutDialog();
    }
  }
  
  @SuppressLint({"NewApi"})
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    if ((Build.VERSION.SDK_INT >= 14) && (ViewConfiguration.get(this).hasPermanentMenuKey())) {
      paramMenu.findItem(2131100007).setVisible(true);
    }
    paramMenu.findItem(2131100004).setVisible(false);
    paramMenu.findItem(2131100006).setVisible(false);
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    this.tabChangedListener.onTabChanged(this.tabHost.getCurrentTabTag());
  }
  
  protected void onStart()
  {
    ApplicationStateMonitor.getInstance().activityStarted();
    super.onStart();
    FontChanger.changeFonts(getWindow().getDecorView().getRootView());
    View localView = findViewById(2131099693);
    if (localView != null)
    {
      if (Build.VERSION.SDK_INT < 11L) {
        break label59;
      }
      localView.setVisibility(8);
    }
    for (;;)
    {
      AnalyticsHelper.logScreenName("Locator");
      return;
      label59:
      localView.setVisibility(0);
    }
  }
  
  protected void onStop()
  {
    ApplicationStateMonitor.getInstance().activityStopped();
    this.locationManager.disconnect();
    super.onStop();
  }
  
  public void showLogoutDialog()
  {
    final DialogHolo localDialogHolo = new DialogHolo(this);
    localDialogHolo.setTitle(getResources().getString(2131165248));
    localDialogHolo.setMessage(getResources().getString(2131165245));
    localDialogHolo.setPositiveButton(getResources().getString(2131165239), new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Utils.dismissDialog(localDialogHolo);
        App.getApplicationInstance().reLogin(LocatorTabs.this);
      }
    });
    localDialogHolo.setNegativeButton(getResources().getString(2131165240), new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Utils.dismissDialog(localDialogHolo);
      }
    });
    Utils.showDialog(localDialogHolo, this);
  }
}
