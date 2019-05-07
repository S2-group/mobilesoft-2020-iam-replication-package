package com.aws.android.tsunami.clock;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.aws.android.lib.view.base.ListItem;
import java.util.ArrayList;
import java.util.List;

public class ClockPackageFinderActivity
  extends Activity
{
  public static List<ListItem> possibleApplications = new ArrayList();
  private boolean launchClock = true;
  private ProgressDialog searchDialog = null;
  private int widgetId = 0;
  
  public ClockPackageFinderActivity() {}
  
  public void findAllAvailableClockPackages()
  {
    Object localObject = getPackageManager().getInstalledApplications(0);
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        ClockPackageFinderActivity.access$002(ClockPackageFinderActivity.this, ProgressDialog.show(ClockPackageFinderActivity.this, "", ClockPackageFinderActivity.this.getString(2131231019), true));
      }
    });
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    if ((localObject != null) && (!((List)localObject).isEmpty()))
    {
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      int i = 0;
      if (i < ((List)localObject).size())
      {
        String str;
        if (((List)localObject).get(i) != null)
        {
          if (((((ApplicationInfo)((List)localObject).get(i)).className == null) || (!((ApplicationInfo)((List)localObject).get(i)).className.contains("clock"))) && ((((ApplicationInfo)((List)localObject).get(i)).packageName == null) || (!((ApplicationInfo)((List)localObject).get(i)).packageName.contains("clock")))) {
            break label264;
          }
          if (getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)((List)localObject).get(i)).packageName) != null)
          {
            str = (String)getPackageManager().getApplicationLabel((ApplicationInfo)((List)localObject).get(i));
            if (str == null) {
              break label227;
            }
            localArrayList1.add(new ListItem(str, ((List)localObject).get(i)));
          }
        }
        for (;;)
        {
          i += 1;
          break;
          label227:
          localArrayList1.add(new ListItem(((ApplicationInfo)((List)localObject).get(i)).packageName, ((List)localObject).get(i)));
          continue;
          label264:
          if ((getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)((List)localObject).get(i)).packageName) != null) && (((ApplicationInfo)((List)localObject).get(i)).packageName != null))
          {
            str = (String)getPackageManager().getApplicationLabel((ApplicationInfo)((List)localObject).get(i));
            if (str != null) {
              localArrayList2.add(new ListItem(str, ((List)localObject).get(i)));
            } else {
              localArrayList2.add(new ListItem(((ApplicationInfo)((List)localObject).get(i)).packageName, ((List)localObject).get(i)));
            }
          }
        }
      }
      if (!localArrayList1.isEmpty()) {
        break label468;
      }
    }
    label468:
    for (possibleApplications = localArrayList2;; possibleApplications = localArrayList1)
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (ClockPackageFinderActivity.this.searchDialog != null) {
            ClockPackageFinderActivity.this.searchDialog.dismiss();
          }
        }
      });
      localObject = new Intent(this, ClockPackageChooserActivity.class);
      ((Intent)localObject).putExtra("appWidgetId", this.widgetId);
      ((Intent)localObject).putExtra(getString(2131231010), this.launchClock);
      startActivity((Intent)localObject);
      finish();
      return;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.widgetId = getIntent().getIntExtra("appWidgetId", this.widgetId);
    this.launchClock = getIntent().getBooleanExtra(getString(2131231010), true);
    findAllAvailableClockPackages();
  }
}
