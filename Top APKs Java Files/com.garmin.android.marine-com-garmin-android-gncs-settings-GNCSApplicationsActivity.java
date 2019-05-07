package com.garmin.android.gncs.settings;

import android.app.ActionBar;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.garmin.android.framework.util.inject.Injector;
import com.garmin.android.gncs.GNCSApplicationInfo;
import com.garmin.android.gncs.GNCSNotificationInfo.NotificationType;
import com.garmin.android.gncs.GNCSUtil;
import com.garmin.android.gncs.R.array;
import com.garmin.android.gncs.R.id;
import com.garmin.android.gncs.R.layout;
import com.garmin.android.gncs.R.string;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GNCSApplicationsActivity
  extends ListActivity
{
  public static final String EXTRA_TEXT_COLOR = "textColor";
  private AsyncTask<Void, Void, List<GNCSPackageInfo>> appTask;
  private int textColor;
  private GNCSNotificationInfo.NotificationType type;
  
  public GNCSApplicationsActivity() {}
  
  private int getTypePosition(GNCSNotificationInfo.NotificationType paramNotificationType)
  {
    int[] arrayOfInt = getResources().getIntArray(R.array.gncs_notification_type_dd_index);
    try
    {
      int i = arrayOfInt[paramNotificationType.ordinal()];
      return i;
    }
    catch (IndexOutOfBoundsException paramNotificationType)
    {
      for (;;) {}
    }
    return arrayOfInt[0];
  }
  
  private void showNotificationTypeDialog(GNCSNotificationInfo.NotificationType paramNotificationType, GNCSPackageInfo paramGNCSPackageInfo, final GNCSApplicationInfo paramGNCSApplicationInfo)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.select_type_title);
    View localView = LayoutInflater.from(this).inflate(R.layout.notification_type_dialog, null);
    PackageManager localPackageManager = getPackageManager();
    ((ImageView)localView.findViewById(R.id.icon)).setImageDrawable(paramGNCSPackageInfo.packageInfo.applicationInfo.loadIcon(localPackageManager));
    ((TextView)localView.findViewById(R.id.text1)).setText(paramGNCSPackageInfo.displayName);
    paramGNCSPackageInfo = ArrayAdapter.createFromResource(this, R.array.gncs_notification_types, 17367048);
    paramGNCSPackageInfo.setDropDownViewResource(R.layout.spinner_drop_down_item);
    ((Spinner)localView.findViewById(R.id.notification_type)).setAdapter(paramGNCSPackageInfo);
    ((Spinner)localView.findViewById(R.id.notification_type)).setSelection(getTypePosition(paramNotificationType));
    ((Spinner)localView.findViewById(R.id.notification_type)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = GNCSApplicationsActivity.this.getResources().getIntArray(R.array.gncs_notification_type_ordinals);
        try
        {
          paramGNCSApplicationInfo.type = GNCSNotificationInfo.NotificationType.values()[paramAnonymousAdapterView[paramAnonymousInt]];
          return;
        }
        catch (IndexOutOfBoundsException paramAnonymousAdapterView)
        {
          for (;;) {}
        }
        paramGNCSApplicationInfo.type = GNCSNotificationInfo.NotificationType.OTHER;
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
        paramGNCSApplicationInfo.type = null;
      }
    });
    localBuilder.setView(localView);
    localBuilder.setPositiveButton(R.string.action_add_app, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        GNCSSettings.getSettings().save(GNCSApplicationsActivity.this, paramGNCSApplicationInfo);
        GNCSApplicationsActivity.this.finish();
      }
    });
    localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
    {
      getActionBar().setDisplayHomeAsUpEnabled(true);
      getActionBar().setHomeButtonEnabled(true);
    }
    this.textColor = getIntent().getIntExtra("textColor", -16777216);
  }
  
  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    paramListView = (GNCSPackageInfo)paramListView.getAdapter().getItem(paramInt);
    paramView = new GNCSApplicationInfo(paramListView.packageInfo.packageName, ((GNCSUtil)Injector.singletonOf(GNCSUtil.class)).getPackageDisplayName(this, paramListView.packageInfo.packageName), this.type, true);
    showNotificationTypeDialog(GNCSSettings.getSettings().getDefaultNotificationType(paramListView.packageInfo.packageName), paramListView, paramView);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 16908332) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    finish();
    return true;
  }
  
  public void onPause()
  {
    super.onPause();
    this.appTask.cancel(true);
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.appTask != null) {
      this.appTask.cancel(true);
    }
    this.appTask = new PackagesAsyncTask(null);
    this.appTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[] { null });
  }
  
  class PackagesAsyncTask
    extends AsyncTask<Void, Void, List<GNCSPackageInfo>>
  {
    private PackagesAsyncTask() {}
    
    protected List<GNCSPackageInfo> doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = GNCSApplicationsActivity.this.getPackageManager();
      Object localObject1 = paramVarArgs.getInstalledPackages(4096);
      ArrayList localArrayList = new ArrayList(((List)localObject1).size());
      List localList = GNCSSettings.getSettings().getLoadedPackages();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
        if (paramVarArgs.getLaunchIntentForPackage(localPackageInfo.packageName) != null)
        {
          Object localObject2 = GNCSApplicationsActivity.this.type;
          GNCSNotificationInfo.NotificationType localNotificationType = GNCSNotificationInfo.NotificationType.SMS;
          int i = 1;
          int i1 = 0;
          if (localObject2 == localNotificationType)
          {
            if (localPackageInfo.requestedPermissions != null)
            {
              localObject2 = localPackageInfo.requestedPermissions;
              int i3 = localObject2.length;
              int k = 0;
              i = 0;
              int j = 0;
              int m;
              int i2;
              int n;
              for (;;)
              {
                m = k;
                i2 = i;
                n = j;
                if (i1 >= i3) {
                  break;
                }
                localNotificationType = localObject2[i1];
                if (localNotificationType.equals("android.permission.RECEIVE_MMS"))
                {
                  if ((k != 0) && (i != 0))
                  {
                    n = 1;
                    m = k;
                    i2 = i;
                    break;
                  }
                  j = 1;
                }
                if (localNotificationType.equals("android.permission.RECEIVE_SMS"))
                {
                  if ((j != 0) && (i != 0))
                  {
                    m = 1;
                    i2 = i;
                    n = j;
                    break;
                  }
                  k = 1;
                }
                if (localNotificationType.equals("android.permission.READ_SMS"))
                {
                  if ((j != 0) && (k != 0))
                  {
                    i2 = 1;
                    m = k;
                    n = j;
                    break;
                  }
                  i = 1;
                }
                i1 += 1;
              }
              if ((n != 0) && (m != 0) && (i2 != 0)) {
                localArrayList.add(new GNCSPackageInfo(localPackageInfo, ((GNCSUtil)Injector.singletonOf(GNCSUtil.class)).getPackageDisplayName(GNCSApplicationsActivity.this, localPackageInfo.packageName)));
              }
            }
          }
          else
          {
            localObject2 = localList.iterator();
            while (((Iterator)localObject2).hasNext()) {
              if (((String)((Iterator)localObject2).next()).equals(localPackageInfo.packageName)) {
                break label380;
              }
            }
            i = 0;
            label380:
            if (i == 0) {
              localArrayList.add(new GNCSPackageInfo(localPackageInfo, ((GNCSUtil)Injector.singletonOf(GNCSUtil.class)).getPackageDisplayName(GNCSApplicationsActivity.this, localPackageInfo.packageName)));
            }
          }
        }
        else if (localPackageInfo.packageName.equals("com.google.android.googlequicksearchbox"))
        {
          localArrayList.add(new GNCSPackageInfo(localPackageInfo, ((GNCSUtil)Injector.singletonOf(GNCSUtil.class)).getPackageDisplayName(GNCSApplicationsActivity.this, localPackageInfo.packageName)));
        }
      }
      Collections.sort(localArrayList);
      return localArrayList;
    }
    
    public void onPostExecute(List<GNCSPackageInfo> paramList)
    {
      if (isCancelled()) {
        return;
      }
      GNCSPackageAdapter localGNCSPackageAdapter = new GNCSPackageAdapter(GNCSApplicationsActivity.this, GNCSApplicationsActivity.this.textColor);
      if ((paramList != null) && (paramList.size() > 0))
      {
        localGNCSPackageAdapter.addAll(paramList);
        GNCSApplicationsActivity.this.setListAdapter(localGNCSPackageAdapter);
      }
    }
  }
}
