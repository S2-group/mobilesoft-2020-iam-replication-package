package com.format.gesturelauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.gesture.GestureLibrary;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AppSelect
  extends AppCompatActivity
{
  String MethodNameForReturn;
  ListView mainListView;
  ArrayList<String> packagename = new ArrayList();
  
  public AppSelect() {}
  
  private boolean checkAlreadyExist(ApplicationInfo paramApplicationInfo)
  {
    Iterator localIterator = MobileConnectService.lib.getGestureEntries().iterator();
    while (localIterator.hasNext())
    {
      NameFilter localNameFilter = new NameFilter((String)localIterator.next());
      if ((localNameFilter.getMethod().equals("mapp")) && (localNameFilter.getPackName().equals(paramApplicationInfo.packageName))) {
        return true;
      }
    }
    return false;
  }
  
  private boolean checkForLaunchIntent(ApplicationInfo paramApplicationInfo)
  {
    try
    {
      if (getPackageManager().getLaunchIntentForPackage(paramApplicationInfo.packageName) != null) {
        return true;
      }
      Log.v("fz", "filtered apps can't run: " + paramApplicationInfo.packageName);
    }
    catch (Exception paramApplicationInfo)
    {
      for (;;)
      {
        paramApplicationInfo.printStackTrace();
      }
    }
    return false;
  }
  
  public void GenerateMethod(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      this.MethodNameForReturn = (paramString3 + "##" + paramString1 + "##" + paramString2);
      paramString1 = new Intent(this, GestureActivity.class);
      paramString1.putExtra("method", this.MethodNameForReturn);
      paramString1.putExtra("name", new NameFilter(this.MethodNameForReturn).getFilteredName());
      ((AnalyticsApplication)getApplication()).getDefaultTracker().send(new HitBuilders.EventBuilder().setCategory("Mobile").setAction("newGestureToDraw").setLabel(this.MethodNameForReturn).build());
      startActivity(paramString1);
      finish();
      return;
    }
    catch (Exception paramString1)
    {
      Toast.makeText(getApplicationContext(), "Fail to run " + paramString2 + "\n Error message: " + paramString1.toString(), 1).show();
    }
  }
  
  public void LoadMobileApps(final Context paramContext)
  {
    paramContext = new ArrayAdapter(this, 17367043, new ArrayList());
    PackageManager localPackageManager = getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    Collections.sort((List)localObject, new ApplicationInfo.DisplayNameComparator(localPackageManager));
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((checkForLaunchIntent(localApplicationInfo) == true) && (!checkAlreadyExist(localApplicationInfo)))
      {
        paramContext.add(localPackageManager.getApplicationLabel(localApplicationInfo).toString());
        this.packagename.add(localApplicationInfo.packageName);
      }
    }
    this.mainListView.setAdapter(paramContext);
    this.mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (String)AppSelect.this.packagename.get(paramAnonymousInt);
        AppSelect.this.GenerateMethod("mapp", paramAnonymousAdapterView, (String)paramContext.getItem(paramAnonymousInt));
      }
    });
  }
  
  public void LoadTimers()
  {
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367043, new ArrayList());
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "Alarm";
    arrayOfString[1] = "Alarm List";
    arrayOfString[2] = "Timer";
    arrayOfString[3] = "Stopwatch";
    String str1 = getString(2131361999);
    String str2 = getString(2131361998);
    String str3 = getString(2131362001);
    String str4 = getString(2131362000);
    final ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (!TimerCheckExist(arrayOfString[i]))
      {
        localArrayAdapter.add(new String[] { str1, str2, str3, str4 }[i]);
        localArrayList.add(arrayOfString[i]);
      }
      i += 1;
    }
    this.mainListView.setAdapter(localArrayAdapter);
    this.mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (String)localArrayList.get(paramAnonymousInt);
        AppSelect.this.GenerateMethod("timer", paramAnonymousAdapterView, AppSelect.this.mainListView.getItemAtPosition(paramAnonymousInt).toString());
      }
    });
  }
  
  public void LoadWearApps(Context paramContext)
  {
    paramContext = new ArrayAdapter(this, 17367043, new ArrayList());
    String[] arrayOfString1 = MobileConnectService.wearPackList;
    String[] arrayOfString2 = MobileConnectService.wearAppList;
    int i = 0;
    while (i < arrayOfString1.length)
    {
      paramContext.add(arrayOfString2[i]);
      this.packagename.add(arrayOfString1[i]);
      i += 1;
    }
    this.mainListView.setAdapter(paramContext);
    this.mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (String)AppSelect.this.packagename.get(paramAnonymousInt);
        AppSelect.this.GenerateMethod("wearapp", paramAnonymousAdapterView, AppSelect.this.mainListView.getItemAtPosition(paramAnonymousInt).toString());
      }
    });
  }
  
  public boolean TimerCheckExist(String paramString)
  {
    Iterator localIterator = MobileConnectService.lib.getGestureEntries().iterator();
    while (localIterator.hasNext())
    {
      NameFilter localNameFilter = new NameFilter((String)localIterator.next());
      if ((localNameFilter.getMethod().equals("timer")) && (localNameFilter.getPackName().equals(paramString))) {
        return true;
      }
    }
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968617);
    this.mainListView = ((ListView)findViewById(2131820814));
    paramBundle = getIntent();
    String str = paramBundle.getStringExtra("type");
    int i = -1;
    switch (str.hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    for (;;)
    {
      try
      {
        if (this.mainListView.getAdapter().getCount() <= 0)
        {
          Toast.makeText(getApplicationContext(), 2131361912, 0).show();
          finish();
        }
        return;
      }
      catch (NullPointerException paramBundle) {}
      if (!str.equals("wear")) {
        break;
      }
      i = 0;
      break;
      if (!str.equals("mobile")) {
        break;
      }
      i = 1;
      break;
      if (!str.equals("timer")) {
        break;
      }
      i = 2;
      break;
      if (!str.equals("call")) {
        break;
      }
      i = 3;
      break;
      if (!str.equals("tasker")) {
        break;
      }
      i = 4;
      break;
      LoadWearApps(getApplicationContext());
      continue;
      LoadMobileApps(getApplicationContext());
      continue;
      LoadTimers();
      continue;
      GenerateMethod("call", paramBundle.getStringExtra("number"), paramBundle.getStringExtra("name"));
      continue;
      GenerateMethod("tasker", paramBundle.getStringExtra("task"), paramBundle.getStringExtra("task"));
    }
  }
}
