package artemitsoft.com.appupdate.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import artemitsoft.com.appupdate.db.DbHelper;
import artemitsoft.com.appupdate.models.AppListModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CheckService
  extends Service
{
  public static String TAG = "UpdateApp";
  public Drawable appIcon;
  public int checkVersionCount = 0;
  public Context context = this;
  public Controller controller;
  public DbHelper dbHelper = new DbHelper(this.context);
  public File file;
  public ArrayList<AppListModel> lstAppList = new ArrayList();
  
  public CheckService() {}
  
  public ArrayList<AppListModel> getAllApp()
  {
    this.dbHelper.DeleteAll();
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = getPackageManager();
    new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
    Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(128);
    Object localObject3 = new ArrayList();
    int i = 0;
    while (i < ((List)localObject2).size())
    {
      if (((PackageManager)localObject1).getLaunchIntentForPackage(((ApplicationInfo)((List)localObject2).get(i)).packageName) != null) {
        ((List)localObject3).add((ApplicationInfo)((List)localObject2).get(i));
      }
      i += 1;
    }
    localObject1 = ((List)localObject3).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      try
      {
        Object localObject4 = getPackageManager().getPackageInfo(((ApplicationInfo)localObject2).packageName, 0);
        localObject3 = ((ApplicationInfo)localObject2).loadLabel(getPackageManager()).toString();
        String str1 = ((ApplicationInfo)localObject2).packageName;
        String str2 = ((ApplicationInfo)localObject2).publicSourceDir;
        String str3 = ((PackageInfo)localObject4).versionName;
        long l1 = ((PackageInfo)localObject4).firstInstallTime;
        long l2 = ((PackageInfo)localObject4).lastUpdateTime;
        this.appIcon = ((ApplicationInfo)localObject2).loadIcon(getPackageManager());
        this.file = new File(((PackageInfo)localObject4).applicationInfo.publicSourceDir);
        localObject4 = new AppListModel();
        ((AppListModel)localObject4).setAppName((String)localObject3);
        ((AppListModel)localObject4).setAppPackageName(str1);
        ((AppListModel)localObject4).setAppSourceDir(str2);
        ((AppListModel)localObject4).setAppVersion(str3);
        ((AppListModel)localObject4).setAppInstalledDate(l1);
        ((AppListModel)localObject4).setAppUpdateDate(l2);
        if ((((ApplicationInfo)localObject2).flags & 0x81) > 0)
        {
          ((AppListModel)localObject4).setAppType(0);
          this.dbHelper.Insert((AppListModel)localObject4);
        }
        else
        {
          ((AppListModel)localObject4).setAppType(1);
          this.dbHelper.Insert((AppListModel)localObject4);
        }
        localArrayList.add(new AppListModel((String)localObject3, this.appIcon, str1, str2, str3, l1, l2));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    Collections.sort(this.lstAppList, AppListModel.sortByAppName);
    return localArrayList;
  }
  
  public void getUpdatedAppList()
  {
    Iterator localIterator = this.lstAppList.iterator();
    while (localIterator.hasNext()) {
      try
      {
        Log.d(TAG, ((AppListModel)localIterator.next()).getAppName());
        versionCall((AppListModel)localIterator.next());
      }
      catch (Exception localException)
      {
        Log.d("Error", String.valueOf(localException));
      }
    }
  }
  
  @Nullable
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.controller = new Controller(this.context);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    Toast.makeText(this, "Service Destroyed", 1).show();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (this.controller.isNetworkAvailable()) {
      new ApiTaskFOrSystemApp().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
    Log.d("Service Started", "Service Started");
    return 1;
  }
  
  public void versionCall(AppListModel paramAppListModel)
  {
    this.checkVersionCount += 1;
    try
    {
      String str1 = (String)new VersionChecker().execute(new String[] { paramAppListModel.getAppPackageName() }).get();
    }
    catch (ExecutionException localExecutionException)
    {
      localExecutionException.printStackTrace();
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
    String str2 = null;
    this.controller.sendNotifyAppUpdate(paramAppListModel, str2, this.lstAppList);
    Log.d(TAG, str2);
  }
  
  public class ApiTaskFOrSystemApp
    extends AsyncTask<Void, Void, Void>
  {
    public ApiTaskFOrSystemApp() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      CheckService.this.checkVersionCount = 0;
      CheckService.this.lstAppList = CheckService.this.getAllApp();
      CheckService.this.getUpdatedAppList();
      return null;
    }
  }
}
