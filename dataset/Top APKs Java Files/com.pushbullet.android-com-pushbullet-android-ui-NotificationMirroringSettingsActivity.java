package com.pushbullet.android.ui;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import com.pushbullet.android.notifications.mirroring.DisabledApps;
import com.pushbullet.substruct.analytics.Analytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NotificationMirroringSettingsActivity
  extends BasicActivity
{
  private List<PackageInfo> mApplications;
  
  public NotificationMirroringSettingsActivity() {}
  
  private List<PackageInfo> getApplications()
  {
    Object localObject1 = new ArrayList();
    HashMap localHashMap = new HashMap();
    Object localObject2 = getPackageManager();
    Iterator localIterator = ((PackageManager)localObject2).getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      String str = localPackageInfo.applicationInfo.loadLabel((PackageManager)localObject2).toString();
      ((List)localObject1).add(str);
      localHashMap.put(str, localPackageInfo);
    }
    Collections.sort((List)localObject1);
    localObject2 = new ArrayList(((List)localObject1).size());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      ((List)localObject2).add(localHashMap.get((String)((Iterator)localObject1).next()));
    }
    return localObject2;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903042);
    ListView localListView = (ListView)findViewById(16908298);
    localListView.addHeaderView(LayoutInflater.from(this).inflate(2130903060, null), null, false);
    localListView.setAdapter(new ApplicationsAdapter(this));
    if (paramBundle == null) {
      Analytics.track("view_notification_mirroring_settings");
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getMenuInflater().inflate(2131558401, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    ApplicationsAdapter localApplicationsAdapter = (ApplicationsAdapter)((HeaderViewListAdapter)((ListView)findViewById(16908298)).getAdapter()).getWrappedAdapter();
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131099718: 
      if (this.mApplications != null)
      {
        paramMenuItem = this.mApplications.iterator();
        while (paramMenuItem.hasNext()) {
          DisabledApps.remove(((PackageInfo)paramMenuItem.next()).packageName);
        }
        localApplicationsAdapter.notifyDataSetChanged();
      }
      return true;
    }
    if (this.mApplications != null)
    {
      paramMenuItem = this.mApplications.iterator();
      while (paramMenuItem.hasNext()) {
        DisabledApps.add(((PackageInfo)paramMenuItem.next()).packageName);
      }
      localApplicationsAdapter.notifyDataSetChanged();
    }
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    ApplicationsAdapter localApplicationsAdapter = (ApplicationsAdapter)((HeaderViewListAdapter)((ListView)findViewById(16908298)).getAdapter()).getWrappedAdapter();
    this.mApplications = getApplications();
    localApplicationsAdapter.setApplications(this.mApplications);
  }
}
