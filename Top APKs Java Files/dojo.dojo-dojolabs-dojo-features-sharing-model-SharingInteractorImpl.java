package dojolabs.dojo.features.sharing.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Telephony.Sms;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import dojolabs.dojo.features.sharing.presenter.SharingContract.Interactor;
import dojolabs.dojo.features.sharing.presenter.SharingContract.Interactor.Callback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SharingInteractorImpl
  implements SharingContract.Interactor
{
  private static List<String> EMAIL_PKGS;
  private static String FACEBOOK_PKG = "com.facebook.katana";
  private static String SLACK_PKG = "com.Slack";
  private static String SMS_PKG;
  private static String TELEGRAM_PKG = "org.telegram.messenger";
  private static String TWITTER_PKG = "com.twitter.android";
  private static String WHATSAPP_PKG = "com.whatsapp";
  private SharingContract.Interactor.Callback mCallback;
  
  public SharingInteractorImpl(SharingContract.Interactor.Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }
  
  private void addAppToList(List<ShareAppData> paramList, PackageManager paramPackageManager, String paramString, boolean paramBoolean)
  {
    if (paramString != null)
    {
      Drawable localDrawable = findIcon(paramPackageManager, paramString);
      paramPackageManager = getAppName(paramPackageManager, paramString);
      if (localDrawable != null) {
        paramList.add(new ShareAppData(paramString, paramPackageManager, localDrawable, paramBoolean));
      }
    }
  }
  
  private void findApps(PackageManager paramPackageManager)
  {
    Iterator localIterator = paramPackageManager.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.contains("put app name"))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Installed package :");
        localStringBuilder.append(localApplicationInfo.packageName);
        Log.d("KING_JULIAN", localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Source dir : ");
        localStringBuilder.append(localApplicationInfo.sourceDir);
        Log.d("KING_JULIAN", localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Launch Activity :");
        localStringBuilder.append(paramPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName));
        Log.d("KING_JULIAN", localStringBuilder.toString());
      }
    }
  }
  
  private Drawable findIcon(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getApplicationIcon(paramString);
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      ThrowableExtension.printStackTrace(paramPackageManager);
    }
    return null;
  }
  
  private String getAppName(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = (String)paramPackageManager.getApplicationLabel(paramPackageManager.getApplicationInfo(paramString, 128));
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      ThrowableExtension.printStackTrace(paramPackageManager);
    }
    return "";
  }
  
  private List<String> getMailAppsPackageNameList(PackageManager paramPackageManager)
  {
    Object localObject = new Intent("android.intent.action.SENDTO");
    ((Intent)localObject).setType("text/html");
    ((Intent)localObject).setData(Uri.parse("mailto:"));
    localObject = paramPackageManager.queryIntentActivities((Intent)localObject, 0);
    paramPackageManager = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramPackageManager.add(((ResolveInfo)((Iterator)localObject).next()).activityInfo.applicationInfo.packageName);
    }
    return paramPackageManager;
  }
  
  private boolean isPackageInstalled(PackageManager paramPackageManager, String paramString)
  {
    return getInstalledAppsPackageNameList(paramPackageManager).contains(paramString);
  }
  
  @RequiresApi(api=19)
  public void generateApplist(Context paramContext, PackageManager paramPackageManager)
  {
    ArrayList localArrayList = new ArrayList();
    SMS_PKG = Telephony.Sms.getDefaultSmsPackage(paramContext);
    addAppToList(localArrayList, paramPackageManager, SMS_PKG, false);
    addAppToList(localArrayList, paramPackageManager, FACEBOOK_PKG, false);
    addAppToList(localArrayList, paramPackageManager, TWITTER_PKG, false);
    addAppToList(localArrayList, paramPackageManager, WHATSAPP_PKG, false);
    addAppToList(localArrayList, paramPackageManager, SLACK_PKG, false);
    addAppToList(localArrayList, paramPackageManager, TELEGRAM_PKG, false);
    EMAIL_PKGS = getMailAppsPackageNameList(paramPackageManager);
    paramContext = EMAIL_PKGS.iterator();
    while (paramContext.hasNext()) {
      addAppToList(localArrayList, paramPackageManager, (String)paramContext.next(), true);
    }
    this.mCallback.onAppListReady(localArrayList);
  }
  
  protected List<String> getInstalledAppsPackageNameList(PackageManager paramPackageManager)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    ((Intent)localObject).setFlags(270532608);
    localObject = paramPackageManager.queryIntentActivities((Intent)localObject, 0);
    paramPackageManager = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramPackageManager.add(((ResolveInfo)((Iterator)localObject).next()).activityInfo.applicationInfo.packageName);
    }
    return paramPackageManager;
  }
}
