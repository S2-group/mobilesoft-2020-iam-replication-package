package net.zedge.android.util;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.zedge.android.ZedgeApplication;
import net.zedge.android.api.ApiException;
import net.zedge.android.api.ApiRequestFactory;
import net.zedge.android.api.request.ApiRequest.Callback;
import net.zedge.android.api.request.AppSyncApiRequest;
import net.zedge.android.api.response.AppSyncApiResponse;
import net.zedge.android.api.response.AppSyncApiResponse.DeletedInfo;
import net.zedge.android.api.response.ZedgeErrorResponse;
import net.zedge.android.appwidget.WidgetHelper;
import net.zedge.android.appwidget.WidgetHelper.Event;
import net.zedge.android.content.Item;
import net.zedge.android.database.ZedgeDatabaseHelper;
import net.zedge.android.report.ErrorReporter;
import roboguice.util.Ln;

public class AppInventorySync
{
  private static final String MARKET_PACKAGE_NAME = "com.google.android.feedback";
  private static final String PLAY_PACKAGE_NAME = "com.android.vending";
  protected ApiRequestFactory mApiRequestFactory;
  protected PackageManager mPackageManager;
  protected WidgetHelper mWidgetHelper;
  protected ZedgeApplication mZedgeApplication;
  protected ZedgeDatabaseHelper mZedgeDatabaseHelper;
  protected String mZedgePackageName;
  
  public AppInventorySync(ZedgeApplication paramZedgeApplication, String paramString, ApiRequestFactory paramApiRequestFactory, ZedgeDatabaseHelper paramZedgeDatabaseHelper, WidgetHelper paramWidgetHelper, PackageManager paramPackageManager)
  {
    this.mZedgeApplication = paramZedgeApplication;
    this.mZedgePackageName = paramString;
    this.mApiRequestFactory = paramApiRequestFactory;
    this.mZedgeDatabaseHelper = paramZedgeDatabaseHelper;
    this.mWidgetHelper = paramWidgetHelper;
    this.mPackageManager = paramPackageManager;
  }
  
  protected void deleteApps(List<AppSyncApiResponse.DeletedInfo> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      AppSyncApiResponse.DeletedInfo localDeletedInfo = (AppSyncApiResponse.DeletedInfo)paramList.next();
      if (!isAppInstalled(localDeletedInfo.packageName))
      {
        Ln.v("App inventory sync deleting item %s", new Object[] { localDeletedInfo.packageName });
        this.mZedgeDatabaseHelper.deleteItem(localDeletedInfo.packageName);
        Ln.d("App inventory sync deleted item %s", new Object[] { localDeletedInfo.packageName });
        this.mWidgetHelper.deleteFromGameWidgetSorting(localDeletedInfo.packageName);
      }
    }
  }
  
  protected List<PackageInfo> getFilteredPackages()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.mPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      String str = this.mPackageManager.getInstallerPackageName(localPackageInfo.packageName);
      if ((str != null) && ((localPackageInfo.applicationInfo.flags & 0x1) == 0) && ((str.equals("com.android.vending")) || (str.equals("com.google.android.feedback"))) && (!localPackageInfo.packageName.equals(this.mZedgePackageName)) && (localPackageInfo.versionCode > 0)) {
        localLinkedList.add(localPackageInfo);
      }
    }
    return localLinkedList;
  }
  
  protected String getInstalledPackages()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = getFilteredPackages().iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      localStringBuilder.append(localPackageInfo.packageName).append(":").append(localPackageInfo.versionCode);
      if (localIterator.hasNext()) {
        localStringBuilder.append(",");
      }
    }
    return localStringBuilder.toString();
  }
  
  protected void insertOrUpdateApps(List<Item> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Item localItem = (Item)paramList.next();
      String str = localItem.getPackageName();
      if (isAppInstalled(str)) {
        try
        {
          Ln.v("App inventory sync insert or update %s", new Object[] { str });
          this.mZedgeDatabaseHelper.insertOrReplaceItem(localItem);
          Ln.d("App inventory sync inserted or updated %s.", new Object[] { str });
          this.mWidgetHelper.insertOrUpdateGameWidgetSorting(localItem.getId(), str, WidgetHelper.Event.SYNC);
        }
        catch (IOException localIOException)
        {
          Ln.e("Could not insert or update %s", new Object[] { str });
          Ln.d(localIOException);
        }
      }
    }
  }
  
  protected boolean isAppInstalled(String paramString)
  {
    try
    {
      this.mPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  protected void notifyWidgetUpdated()
  {
    this.mZedgeApplication.sendBroadcast(new Intent("net.zedge.android.ACTION_UPDATE_WIDGET"));
    Ln.v("Broadcast %s sent", new Object[] { "net.zedge.android.ACTION_UPDATE_WIDGET" });
  }
  
  public void sync()
  {
    String str = getInstalledPackages();
    Ln.i("Starting to sync app inventory", new Object[0]);
    this.mApiRequestFactory.newAppSyncApiRequest(str).runWithCallback(new InventorySyncCallback());
  }
  
  protected class InventorySyncCallback
    implements ApiRequest.Callback<AppSyncApiResponse>
  {
    protected InventorySyncCallback() {}
    
    public void requestComplete(AppSyncApiResponse paramAppSyncApiResponse)
    {
      AppInventorySync.this.deleteApps(paramAppSyncApiResponse.getDeleted());
      AppInventorySync.this.insertOrUpdateApps(paramAppSyncApiResponse.getItems());
      Ln.i("Sync app inventory complete", new Object[0]);
      AppInventorySync.this.notifyWidgetUpdated();
    }
    
    public void requestFailed(ApiException paramApiException, ZedgeErrorResponse paramZedgeErrorResponse)
    {
      AppInventorySync.this.mZedgeApplication.getErrorReporter().send(paramApiException);
      Ln.w("Could not sync app inventory", new Object[0]);
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
