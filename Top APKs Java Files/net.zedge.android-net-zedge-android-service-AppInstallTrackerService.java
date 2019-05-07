package net.zedge.android.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.zedge.android.ZedgeApplication;
import net.zedge.android.api.ApiException;
import net.zedge.android.api.ApiRequestFactory;
import net.zedge.android.api.request.ApiRequest.Callback;
import net.zedge.android.api.request.AppSyncApiRequest;
import net.zedge.android.api.response.AppSyncApiResponse;
import net.zedge.android.api.response.ConfigApiResponse;
import net.zedge.android.api.response.ZedgeErrorResponse;
import net.zedge.android.appwidget.WidgetHelper;
import net.zedge.android.appwidget.WidgetHelper.Event;
import net.zedge.android.config.ConfigurationLoader;
import net.zedge.android.config.ConfigurationLoader.LoadConfigCallback;
import net.zedge.android.database.ZedgeDatabaseHelper;
import net.zedge.android.report.ErrorReporter;
import net.zedge.log.Logger;
import net.zedge.thrift.ContentType;
import roboguice.util.Ln;

public class AppInstallTrackerService
  extends Service
{
  public static final int ACTION_PACKAGE_CHANGED = 2;
  public static final int ACTION_START_TRACKING = 1;
  public static final String KEY_ACTION = "action";
  public static final String KEY_ITEM = "item";
  public static final String KEY_PACKAGE_INTENT = "package_intent";
  protected AppInstallTrackerInfo appInfo;
  protected ContentResolver contentResolver;
  
  public AppInstallTrackerService() {}
  
  protected void deleteItemFromDatabase(String paramString)
  {
    if (paramString == null) {
      Ln.w("Ignoring deleteItemFromDatabase because packageName argument is null", new Object[0]);
    }
    do
    {
      return;
      if (getApplicationContext().getDatabaseHelper().deleteItem(paramString) > 0) {
        Ln.d("PACKAGE DELETED: %s", new Object[] { paramString });
      }
    } while (!getApplicationContext().getWidgetHelper().deleteFromGameWidgetSorting(paramString));
    notifyWidgetUpdated();
  }
  
  protected void deleteItemFromDatabase(net.zedge.android.content.Item paramItem)
  {
    if (paramItem == null) {
      Ln.w("Ignoring deleteItemFromDatabase because Item argument is null", new Object[0]);
    }
    WidgetHelper localWidgetHelper;
    do
    {
      return;
      if (getApplicationContext().getDatabaseHelper().deleteItem(paramItem) > 0) {
        Ln.d("Item deleted - item id %s, %s", new Object[] { Integer.valueOf(paramItem.getId()), paramItem.getContentType() });
      }
      paramItem = paramItem.getPackageName();
      localWidgetHelper = getApplicationContext().getWidgetHelper();
    } while ((paramItem == null) || (!localWidgetHelper.deleteFromGameWidgetSorting(paramItem)));
    notifyWidgetUpdated();
  }
  
  public ZedgeApplication getApplicationContext()
  {
    return (ZedgeApplication)super.getApplicationContext();
  }
  
  protected void handlePackageIntent(Intent paramIntent, int paramInt)
  {
    boolean bool1 = false;
    Ln.i("Received package intent: " + paramIntent.getAction(), new Object[0]);
    Object localObject = paramIntent.getAction();
    boolean bool2 = "android.intent.action.PACKAGE_ADDED".equals(localObject);
    boolean bool3 = "android.intent.action.PACKAGE_REMOVED".equals(localObject);
    boolean bool4 = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
    if ((bool2) || (bool3))
    {
      paramIntent = paramIntent.getData();
      if (paramIntent == null) {
        stopSelf(paramInt);
      }
      label229:
      for (;;)
      {
        return;
        paramIntent = paramIntent.getSchemeSpecificPart();
        int j = resolveVersionCode(paramIntent);
        if (!bool2) {
          bool1 = true;
        }
        logEvent(paramIntent, j, bool1);
        localObject = this.appInfo.get(paramIntent);
        int i = 0;
        if (localObject != null) {
          if (bool2)
          {
            updateItemDetailFragment((net.zedge.android.content.Item)localObject);
            saveItemToDatabase(paramIntent, (net.zedge.android.content.Item)localObject);
            this.appInfo.remove(paramIntent);
            i = 1;
          }
        }
        for (;;)
        {
          if (i == 0) {
            break label229;
          }
          stopSelf(paramInt);
          return;
          if (bool4) {
            break;
          }
          deleteItemFromDatabase((net.zedge.android.content.Item)localObject);
          break;
          if ((bool3) && (!bool4))
          {
            deleteItemFromDatabase(paramIntent);
            i = 1;
          }
          else if (bool2)
          {
            syncPackageAdded(paramIntent, j, paramInt);
          }
          else
          {
            i = 1;
          }
        }
      }
    }
    stopSelf(paramInt);
  }
  
  protected void logEvent(String paramString, int paramInt, boolean paramBoolean)
  {
    Logger localLogger = getApplicationContext().getLogger();
    net.zedge.log.Item localItem = new net.zedge.log.Item();
    localItem.setCtype((byte)ContentType.APPLICATION.getValue());
    localItem.setId("market:" + paramString + ":" + paramInt);
    localLogger.installEvent(localItem, paramBoolean, null);
  }
  
  protected void notifyWidgetUpdated()
  {
    sendBroadcast(new Intent("net.zedge.android.ACTION_UPDATE_WIDGET"));
    Ln.v("Broadcast %s sent", new Object[] { "net.zedge.android.ACTION_UPDATE_WIDGET" });
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.appInfo = new AppInstallTrackerInfo(getApplicationContext());
    this.contentResolver = getContentResolver();
    Ln.d("AppInstallTrackerService starting", new Object[0]);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    Ln.d("AppInstallTrackerService stopping", new Object[0]);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    paramInt1 = paramIntent.getIntExtra("action", -1);
    switch (paramInt1)
    {
    default: 
      if (paramInt1 == -1) {
        throw new IllegalArgumentException("Intent is missing the extra parameter with key AppInstallTrackerService.KEY_ACTION");
      }
      break;
    case 1: 
      startTrackingItem(paramIntent);
      stopSelf(paramInt2);
    case 2: 
      for (;;)
      {
        return 3;
        handlePackageIntent((Intent)paramIntent.getParcelableExtra("package_intent"), paramInt2);
      }
    }
    throw new IllegalArgumentException("Unknown action with id " + paramInt1);
  }
  
  protected int resolveVersionCode(String paramString)
  {
    List localList = getPackageManager().getInstalledPackages(0);
    int k = 0;
    int i = 0;
    int j = k;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {}
      while (!localPackageInfo.packageName.equals(paramString))
      {
        i += 1;
        break;
      }
      j = localPackageInfo.versionCode;
    }
    return j;
  }
  
  protected void runSyncRequest(String paramString1, String paramString2, int paramInt)
  {
    getApplicationContext().getApiRequestFactory().newAppSyncApiRequest(paramString1).runWithCallback(new AppSyncCallback(paramString2, paramInt));
  }
  
  protected void saveItemToDatabase(String paramString, net.zedge.android.content.Item paramItem)
  {
    if (paramItem == null) {
      Ln.w("Ignoring saveItemToDatabase because Item argument is null", new Object[0]);
    }
    for (;;)
    {
      return;
      if (!paramItem.getPackageName().equalsIgnoreCase(paramString))
      {
        Ln.w("Ignoring saveItemToDatabase because package name does not match installed package name", new Object[0]);
        return;
      }
      if (paramItem.getContentTypeId() == null)
      {
        Ln.w("Ignoring saveItemToDatabase because content type is unknown", new Object[0]);
        return;
      }
      try
      {
        if (getApplicationContext().getDatabaseHelper().insertOrReplaceItem(paramItem))
        {
          if (getApplicationContext().getWidgetHelper().insertOrUpdateGameWidgetSorting(paramItem.getId(), paramString, WidgetHelper.Event.INSTALL))
          {
            Ln.i("GAME WIDGET SORT DB UPDATED WITH: " + paramItem.getId() + ", package name: " + paramString, new Object[0]);
            notifyWidgetUpdated();
          }
          Ln.i("Added app with package name %s, item id %s, %s to downloads", new Object[] { paramString, Integer.valueOf(paramItem.getId()), paramItem.getContentType() });
          return;
        }
      }
      catch (IOException paramItem)
      {
        Ln.e("Could not add app %s to Zedge database", new Object[] { paramString });
        Ln.d(paramItem);
      }
    }
  }
  
  protected void startTrackingItem(Intent paramIntent)
  {
    Ln.d("Received AppInstallTrackerService startup intent", new Object[0]);
    if (paramIntent.hasExtra("item"))
    {
      paramIntent = (net.zedge.android.content.Item)paramIntent.getSerializableExtra("item");
      Ln.d("Listening to added/removed-events for: " + paramIntent.getPackageName(), new Object[0]);
      this.appInfo.add(paramIntent.getPackageName(), paramIntent);
    }
  }
  
  protected void syncPackageAdded(final String paramString, int paramInt1, final int paramInt2)
  {
    if (paramString == null)
    {
      Ln.w("Ignoring syncPackageAdded because packageName argument is null", new Object[0]);
      return;
    }
    if (paramString.equals(""))
    {
      Ln.w("Ignoring syncPackageAdded because packageName argument is an empty string", new Object[0]);
      return;
    }
    if (paramInt1 <= 0)
    {
      Ln.w("Ignoring syncPackageAdded because the versionCode argument is illegal (should be > 0)", new Object[0]);
      return;
    }
    final String str = paramString + ":" + paramInt1;
    if (!getApplicationContext().getConfigurationLoader().hasConfiguration())
    {
      getApplicationContext().getConfigurationLoader().loadConfigurationWithCallback(new ConfigurationLoader.LoadConfigCallback()
      {
        public void configLoaded(ConfigApiResponse paramAnonymousConfigApiResponse)
        {
          AppInstallTrackerService.this.runSyncRequest(str, paramString, paramInt2);
        }
        
        public void loadConfigFailed() {}
      });
      return;
    }
    runSyncRequest(str, paramString, paramInt2);
  }
  
  protected void updateItemDetailFragment(net.zedge.android.content.Item paramItem)
  {
    Intent localIntent = new Intent("net.zedge.android.ACTION_APP_INSTALLED");
    localIntent.putExtra("item", paramItem);
    sendBroadcast(localIntent);
  }
  
  protected class AppSyncCallback
    implements ApiRequest.Callback<AppSyncApiResponse>
  {
    private String packageName;
    private int serviceId;
    
    public AppSyncCallback(String paramString, int paramInt)
    {
      this.packageName = paramString;
      this.serviceId = paramInt;
    }
    
    public void requestComplete(AppSyncApiResponse paramAppSyncApiResponse)
    {
      paramAppSyncApiResponse = paramAppSyncApiResponse.getItems().iterator();
      while (paramAppSyncApiResponse.hasNext())
      {
        net.zedge.android.content.Item localItem = (net.zedge.android.content.Item)paramAppSyncApiResponse.next();
        AppInstallTrackerService.this.saveItemToDatabase(this.packageName, localItem);
      }
      AppInstallTrackerService.this.stopSelf(this.serviceId);
    }
    
    public void requestFailed(ApiException paramApiException, ZedgeErrorResponse paramZedgeErrorResponse)
    {
      AppInstallTrackerService.this.stopSelf(this.serviceId);
      Ln.e("Could not sync app info from server for app with package name %s", new Object[] { this.packageName });
      AppInstallTrackerService.this.getApplicationContext().getErrorReporter().send(paramApiException);
      if (paramZedgeErrorResponse != null)
      {
        Ln.d(paramZedgeErrorResponse, new Object[0]);
        Ln.v(paramApiException);
        return;
      }
      Ln.d(paramApiException);
    }
  }
}
