package artemitsoft.com.appupdate.activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import artemitsoft.com.appupdate.AppController;
import artemitsoft.com.appupdate.adapter.ApplicationAdapter;
import artemitsoft.com.appupdate.adapter.ApplicationAdapter.CustomButtonListener;
import artemitsoft.com.appupdate.db.DbHelper;
import artemitsoft.com.appupdate.models.AppListModel;
import artemitsoft.com.appupdate.models.Enums.GoActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppListActivity
  extends BaseActivity
  implements ApplicationAdapter.CustomButtonListener
{
  public String TAG = AppListActivity.class.getSimpleName();
  public AppController appController = AppController.getInstance();
  public List<AppListModel> appList;
  public ApplicationAdapter applicationAdapter;
  public DbHelper dbHelper;
  public ListView list;
  public LinearLayout llBody;
  public RelativeLayout llLoading;
  public PackageManager packageManager;
  
  public AppListActivity() {}
  
  public void init()
  {
    new LoadAppListAsync().execute(new Void[0]);
  }
  
  public void onButtonClickListener(AppListModel paramAppListModel)
  {
    if ((this.appController.getSelectedActivity() == Enums.GoActivity.UserAppUpdate.getValue()) || (this.appController.getSelectedActivity() == Enums.GoActivity.SystemAppUpdate.getValue()))
    {
      Intent localIntent = new Intent(this, AppDetailActivity.class);
      localIntent.putExtra("AppListModel", paramAppListModel.getAppPackageName());
      startActivity(localIntent);
    }
    if (AppController.getInstance().getSelectedActivity() == Enums.GoActivity.UnInstallApp.getValue()) {
      unInstallApp(paramAppListModel.getAppPackageName());
    }
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361821);
    paramBundle = (Toolbar)findViewById(2131230970);
    paramBundle.setNavigationIcon(2131165295);
    if (this.appController.getSelectedActivity() == Enums.GoActivity.UserAppUpdate.getValue()) {
      paramBundle.setTitle(2131558502);
    }
    if (this.appController.getSelectedActivity() == Enums.GoActivity.UnInstallApp.getValue()) {
      paramBundle.setTitle(2131558500);
    }
    if (this.appController.getSelectedActivity() == Enums.GoActivity.SystemAppUpdate.getValue()) {
      paramBundle.setTitle(2131558499);
    }
    setSupportActionBar(paramBundle);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    this.packageManager = getPackageManager();
    this.dbHelper = new DbHelper(this);
    this.list = ((ListView)findViewById(2131230859));
    this.llBody = ((LinearLayout)findViewById(2131230863));
    this.llLoading = ((RelativeLayout)findViewById(2131230867));
    this.llBody.setVisibility(8);
    this.llLoading.setVisibility(0);
    init();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 16908332) {
      return true;
    }
    finish();
    overridePendingTransition(2130771990, 2130771993);
    return true;
  }
  
  public void onResume()
  {
    ShowAds(this, (LinearLayout)findViewById(2131230748));
    super.onResume();
  }
  
  public class LoadAppListAsync
    extends AsyncTask<Void, Void, List<AppListModel>>
  {
    private Drawable appIcon;
    
    public LoadAppListAsync() {}
    
    private List<AppListModel> getAllApp()
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject1 = new ArrayList();
      new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
      Object localObject2 = AppListActivity.this.packageManager.getInstalledApplications(128);
      int i = 0;
      while (i < ((List)localObject2).size())
      {
        if (AppListActivity.this.packageManager.getLaunchIntentForPackage(((ApplicationInfo)((List)localObject2).get(i)).packageName) != null) {
          ((List)localObject1).add((ApplicationInfo)((List)localObject2).get(i));
        }
        i += 1;
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        try
        {
          PackageInfo localPackageInfo = AppListActivity.this.packageManager.getPackageInfo(((ApplicationInfo)localObject2).packageName, 0);
          String str1 = ((ApplicationInfo)localObject2).loadLabel(AppListActivity.this.packageManager).toString();
          this.appIcon = ((ApplicationInfo)localObject2).loadIcon(AppListActivity.this.packageManager);
          String str2 = ((ApplicationInfo)localObject2).packageName;
          String str3 = ((ApplicationInfo)localObject2).publicSourceDir;
          String str4 = localPackageInfo.versionName;
          long l1 = localPackageInfo.firstInstallTime;
          long l2 = localPackageInfo.lastUpdateTime;
          if ((((ApplicationInfo)localObject2).flags & 0x81) > 0)
          {
            if (AppListActivity.this.appController.getSelectedActivity() == Enums.GoActivity.SystemAppUpdate.getValue()) {
              localArrayList.add(new AppListModel(str1, this.appIcon, str2, str3, str4, l1, l2));
            }
          }
          else if (AppListActivity.this.appController.getSelectedActivity() != Enums.GoActivity.SystemAppUpdate.getValue()) {
            localArrayList.add(new AppListModel(str1, this.appIcon, str2, str3, str4, l1, l2));
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
      }
      Collections.sort(localArrayList, AppListModel.sortByAppName);
      return localArrayList;
    }
    
    protected List<AppListModel> doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = AppListActivity.this;
      List localList = getAllApp();
      paramVarArgs.appList = localList;
      return localList;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
    }
    
    protected void onPostExecute(List<AppListModel> paramList)
    {
      AppListActivity.this.llBody.setVisibility(0);
      AppListActivity.this.llLoading.setVisibility(8);
      AppListActivity.this.applicationAdapter = new ApplicationAdapter(AppListActivity.this, 2131361866, paramList);
      AppListActivity.this.list.setAdapter(AppListActivity.this.applicationAdapter);
      AppListActivity.this.applicationAdapter.setCustomButtonListener(AppListActivity.this);
      super.onPostExecute(paramList);
    }
    
    protected void onPreExecute()
    {
      AppListActivity.this.llBody.setVisibility(8);
      AppListActivity.this.llLoading.setVisibility(0);
      super.onPreExecute();
    }
    
    protected void onProgressUpdate(Void... paramVarArgs)
    {
      super.onProgressUpdate(paramVarArgs);
    }
  }
}
