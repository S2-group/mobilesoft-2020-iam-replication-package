package com.simpler.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.Button;
import com.simpler.application.SimplerApplication;
import com.simpler.data.MetaData;
import com.simpler.ui.views.AppsPromoView.AppType;
import com.simpler.utils.FilesUtils;
import com.simpler.utils.Logger;
import com.simpler.utils.StringsUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PackageLogic
  extends BaseLogic
{
  private static PackageLogic e;
  private final String a = "com.simpler.contacts";
  private final String b = "com.simpler.dialer";
  private final String c = "com.simpler.merge";
  private final String d = "com.simpler.backup";
  
  public PackageLogic() {}
  
  private void a(int paramInt, String paramString)
  {
    FilesUtils.saveToPreferences(paramString, paramInt + 1);
  }
  
  private boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static PackageLogic getInstance()
  {
    if (e == null) {
      e = new PackageLogic();
    }
    return e;
  }
  
  public void checkAndShowUpdateDialog(Activity paramActivity)
  {
    if (paramActivity == null) {}
    int i;
    do
    {
      String str;
      do
      {
        do
        {
          return;
          localObject1 = paramActivity.getPackageName();
          localObject2 = ConfigurationLogic.getInstance().getAppMetaData((String)localObject1);
        } while (localObject2 == null);
        str = ((MetaData)localObject2).getPlayStoreVer();
      } while (StringsUtils.versionCompare(LogicManager.getInstance().getSettingsLogic().getSimplerVersion(paramActivity), str).intValue() >= 0);
      if (StringsUtils.versionCompare(FilesUtils.getStringFromPreferences("update_dialog_last_version", "0"), str).intValue() < 0)
      {
        FilesUtils.saveToPreferences("update_dialog_last_version", str);
        FilesUtils.saveToPreferences("update_dialog_shown_count", 0);
      }
      i = FilesUtils.getIntFromPreferences("update_dialog_shown_count", 0);
    } while (i >= ((MetaData)localObject2).getUpgradeDialogMaxPopUps());
    Object localObject1 = new l(this, paramActivity, (String)localObject1, (MetaData)localObject2, i);
    Object localObject2 = new AlertDialog.Builder(paramActivity);
    ((AlertDialog.Builder)localObject2).setMessage(paramActivity.getString(2131099941));
    ((AlertDialog.Builder)localObject2).setPositiveButton(paramActivity.getString(2131100126), (DialogInterface.OnClickListener)localObject1);
    ((AlertDialog.Builder)localObject2).setNegativeButton(paramActivity.getString(2131099957), (DialogInterface.OnClickListener)localObject1);
    ((AlertDialog.Builder)localObject2).setOnCancelListener(new m(this, i));
    paramActivity = ((AlertDialog.Builder)localObject2).show();
    paramActivity.setCanceledOnTouchOutside(false);
    try
    {
      paramActivity = paramActivity.getButton(-1);
      paramActivity.setTextColor(SettingsLogic.getPrimaryColor());
      paramActivity.setTypeface(null, 1);
      return;
    }
    catch (Exception paramActivity)
    {
      Logger.e("Simpler", paramActivity);
    }
  }
  
  public int getAppAboutIconResId(String paramString)
  {
    int j = 2130837580;
    int i;
    if (isDialerApp(paramString)) {
      i = 2130837581;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (isContactsApp(paramString));
      if (isBackupApp(paramString)) {
        return 2130837579;
      }
      i = j;
    } while (!isMergeApp(paramString));
    return 2130837582;
  }
  
  public int getAppLauncherIconResId(String paramString)
  {
    int j = 2130837761;
    int i;
    if (isDialerApp(paramString)) {
      i = 2130837762;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (isContactsApp(paramString));
      if (isBackupApp(paramString)) {
        return 2130837760;
      }
      i = j;
    } while (!isMergeApp(paramString));
    return 2130837763;
  }
  
  public String getAppName(Context paramContext)
  {
    int j = 2131100294;
    Context localContext = paramContext;
    if (paramContext == null) {
      localContext = SimplerApplication.getContext();
    }
    paramContext = localContext.getPackageName();
    int i;
    if (isDialerApp(paramContext)) {
      i = 2131100298;
    }
    for (;;)
    {
      return localContext.getString(i);
      i = j;
      if (!isContactsApp(paramContext)) {
        if (isBackupApp(paramContext))
        {
          i = 2131100296;
        }
        else
        {
          i = j;
          if (isMergeApp(paramContext)) {
            i = 2131100299;
          }
        }
      }
    }
  }
  
  public String getFlurryApiKey(String paramString)
  {
    String str = "JXB5GDD5VRB5MWXYN2V4";
    if (isBackupApp(paramString)) {
      str = "Q4RM52V64CJ2MC59NK52";
    }
    do
    {
      return str;
      if (isMergeApp(paramString)) {
        return "PMKCWCRTX9WZYYWH82WB";
      }
    } while (!isDialerApp(paramString));
    return "TBQCC8F4YDN6NBFR8BWR";
  }
  
  public String getGoogleAnalyticsTrackingId(String paramString)
  {
    String str = "UA-31677367-12";
    if (isBackupApp(paramString)) {
      str = "UA-31677367-13";
    }
    do
    {
      return str;
      if (isMergeApp(paramString)) {
        return "UA-31677367-14";
      }
    } while (!isDialerApp(paramString));
    return "UA-31677367-16";
  }
  
  public String getGooglePlayLicenseKey(String paramString)
  {
    if (isDialerApp(paramString)) {
      return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmzaEcrpZnmZloGVALdEmUNzYfIYBalMUc5axPF7T/B6xa0OnRYrjLJcpNX0iUYecPWu0MFEETQrwi8khuMWxlxxtCZQhB0aU8ui5NF93Y/fgeC3OG/VZuWCFl4x1HjjuMizNB7mHHXjTRy/4NNItwTSP3PQtd4ykbRScqm6wIHUduh56qif7s+goImUX0ahVfa6BchU+LGv/ujthr51f2a6XosswDCS7wBtPYJI9e7ttcY2mYxWUCupDaO5+xIF10jkr9zntIepgH43AVu76N8TnLMQUvWJf+iVg3PigcTusio2KN5iY5Z9huZQ1UHP1coxR+F7ygAZ5b1TX5Q5yZQIDAQAB";
    }
    if (isContactsApp(paramString)) {
      return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxK4illt/FY2figwRi3/65J/GgXhHoadD3e/ta3nG4/LPqtosyYkwde0whIBHS4HGO/KjL+ApJsU3V0Rh9+ikz25vUOn1ZGKguIVUcDR3xv8O/ocmrI3oeCYxBEIiTaMStWB8NJrgxjDZuD3q1PeaD7JpPgMfmMnEf/gV1RLFsfjwFhNmxKY3S4Le5CMLbB/XXu0Kg0S/zdL+GnXqxswJOiPhYuD39/i+MFbu151218GQBs/aJGRQEjJWBwH62uT9qd6kCHwKWujwXsK5d+Jk5piimGiNeaTcqdYpoXZqPw5EcZ+4mphu52sfEudy36XV6784qf75QqLOuL46eVedawIDAQAB";
    }
    if (isBackupApp(paramString)) {
      return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnrPF9NnxQn6M3+N4Zo+0h2E4Sn2WCKG9LatZnsz+e/BbNBiBjbkVr+49DLPIwrGZBpQk4NOca5dhkGg2pYBCRm3EXmBluGmVtmtJhOcb1KGmeeB4+85T5wo6I26DsCKFP5ommtu5QPi76ma9cDOYvO77OsvC3BqrPzEgdwq8WbK2aOQ+7aGxjFEQKq07Id3+27MILW+txz+1sbw/KRHSup3NhM7KldEjDAwsAOtKxNRkIrbp6Eu0LIA48ol028j0Yz2YfTPrZqgh/A7YJ2OcD1LbPdcjgbic2u9S5iSNZJkQTP+KKwVEKmkKxMG9n5A/J+nNmmI/kSGkr3uW0/7FyQIDAQAB";
    }
    if (isMergeApp(paramString)) {
      return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhDYsEDwI/4CsiH0IIQoH4SKrmLHjGlmEYvuXB6cgE/ahj56J52O7nJvu7zy0agwxe7kvInzXE7QCSiJVsS32Hx+825XQFnxWbV/nJxNt8J28bNMRiOf1E+S2CCFjohhEozl51zDt+yG+nVwGHniJd49pldlGzaaODUuhN7PGI1eA8xhOscwI+E/IkqUfFBQyEfQwK/raWAGJwgDhfZHzXi5uyG5WmVXWTahyJC7shdtfgTQYZvwMQfAj9jKBuLdnFmwkqHKfgH0R+FMrFkLw5TKf/2JmbMh5BjWCDUGrJ7/4BeUJY5tBMpeSu2glJWdi999/n1BXBStwNxwWZliFAwIDAQAB";
    }
    return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxK4illt/FY2figwRi3/65J/GgXhHoadD3e/ta3nG4/LPqtosyYkwde0whIBHS4HGO/KjL+ApJsU3V0Rh9+ikz25vUOn1ZGKguIVUcDR3xv8O/ocmrI3oeCYxBEIiTaMStWB8NJrgxjDZuD3q1PeaD7JpPgMfmMnEf/gV1RLFsfjwFhNmxKY3S4Le5CMLbB/XXu0Kg0S/zdL+GnXqxswJOiPhYuD39/i+MFbu151218GQBs/aJGRQEjJWBwH62uT9qd6kCHwKWujwXsK5d+Jk5piimGiNeaTcqdYpoXZqPw5EcZ+4mphu52sfEudy36XV6784qf75QqLOuL46eVedawIDAQAB";
  }
  
  public String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public String getPackageName(AppsPromoView.AppType paramAppType)
  {
    switch (n.a[paramAppType.ordinal()])
    {
    default: 
      return null;
    case 1: 
      return "com.simpler.contacts";
    case 2: 
      return "com.simpler.dialer";
    case 3: 
      return "com.simpler.merge";
    }
    return "com.simpler.backup";
  }
  
  public ArrayList<AppsPromoView.AppType> getPromoAppType(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageName();
    if (isDialerApp(paramContext))
    {
      localArrayList.add(AppsPromoView.AppType.CONTACTS);
      localArrayList.add(AppsPromoView.AppType.MERGE);
      localArrayList.add(AppsPromoView.AppType.BACKUP);
    }
    do
    {
      return localArrayList;
      if (isContactsApp(paramContext))
      {
        localArrayList.add(AppsPromoView.AppType.DIALER);
        localArrayList.add(AppsPromoView.AppType.MERGE);
        localArrayList.add(AppsPromoView.AppType.BACKUP);
        return localArrayList;
      }
      if (isMergeApp(paramContext))
      {
        localArrayList.add(AppsPromoView.AppType.DIALER);
        localArrayList.add(AppsPromoView.AppType.CONTACTS);
        localArrayList.add(AppsPromoView.AppType.BACKUP);
        return localArrayList;
      }
    } while (!isBackupApp(paramContext));
    localArrayList.add(AppsPromoView.AppType.DIALER);
    localArrayList.add(AppsPromoView.AppType.CONTACTS);
    localArrayList.add(AppsPromoView.AppType.MERGE);
    return localArrayList;
  }
  
  public int getRateDialogActionsToPopUp(String paramString)
  {
    ConfigurationLogic localConfigurationLogic = ConfigurationLogic.getInstance();
    int i = localConfigurationLogic.getAppReviewIntervalContacts();
    if (isDialerApp(paramString)) {
      i = localConfigurationLogic.getAppReviewIntervalDialer();
    }
    do
    {
      return i;
      if (isBackupApp(paramString)) {
        return localConfigurationLogic.getAppReviewIntervalBackup();
      }
    } while (!isMergeApp(paramString));
    return localConfigurationLogic.getAppReviewIntervalMerge();
  }
  
  public String getShareAppBody(Context paramContext)
  {
    Object localObject = null;
    String str = paramContext.getPackageName();
    if (isDialerApp(str))
    {
      localObject = paramContext.getString(2131100298);
      paramContext = paramContext.getString(2131100073);
    }
    for (;;)
    {
      return String.format("%s - %s", new Object[] { localObject, paramContext });
      if (isContactsApp(str))
      {
        localObject = paramContext.getString(2131100297);
        paramContext = paramContext.getString(2131100167);
      }
      else if (isBackupApp(str))
      {
        localObject = paramContext.getString(2131100296);
        paramContext = paramContext.getString(2131099889);
      }
      else if (isMergeApp(str))
      {
        localObject = paramContext.getString(2131100299);
        paramContext = paramContext.getString(2131099919);
      }
      else
      {
        str = null;
        paramContext = (Context)localObject;
        localObject = str;
      }
    }
  }
  
  public String getShareAppSubject(Context paramContext)
  {
    return String.format("%s %s", new Object[] { paramContext.getString(2131099754), getAppName(paramContext) });
  }
  
  public String getShareAppUrl(Context paramContext)
  {
    paramContext = paramContext.getPackageName();
    ConfigurationLogic localConfigurationLogic = ConfigurationLogic.getInstance();
    MetaData localMetaData = localConfigurationLogic.getAppMetaData(paramContext);
    if (localMetaData == null)
    {
      if (isContactsApp(paramContext))
      {
        localConfigurationLogic.getClass();
        return "simplercontacts.com/get";
      }
      if (isDialerApp(paramContext))
      {
        localConfigurationLogic.getClass();
        return "simplercontacts.com/get3";
      }
      if (isMergeApp(paramContext))
      {
        localConfigurationLogic.getClass();
        return "simplercontacts.com/get1";
      }
      localConfigurationLogic.getClass();
      return "simplercontacts.com/get2";
    }
    return localMetaData.getShareUrl();
  }
  
  public boolean isBackupApp(String paramString)
  {
    return paramString.equals("com.simpler.backup");
  }
  
  public boolean isContactsApp(String paramString)
  {
    return paramString.equals("com.simpler.contacts");
  }
  
  public boolean isDialerApp(String paramString)
  {
    return paramString.equals("com.simpler.dialer");
  }
  
  public boolean isMergeApp(String paramString)
  {
    return paramString.equals("com.simpler.merge");
  }
  
  public boolean isSimplerBackupExists(Context paramContext)
  {
    return a(paramContext, "com.simpler.backup");
  }
  
  public boolean isSimplerContactsExists(Context paramContext)
  {
    return a(paramContext, "com.simpler.contacts");
  }
  
  public boolean isSimplerDialerExists(Context paramContext)
  {
    return a(paramContext, "com.simpler.dialer");
  }
  
  public boolean isSimplerMergeExists(Context paramContext)
  {
    return a(paramContext, "com.simpler.merge");
  }
  
  protected void killLogic()
  {
    e = null;
  }
  
  public boolean shouldRunFindDuplicatesBackgroundTask(Context paramContext)
  {
    String str = paramContext.getPackageName();
    if (isMergeApp(str)) {}
    do
    {
      return false;
      if (isContactsApp(str)) {
        return true;
      }
      if ((isDialerApp(str)) && (!isSimplerContactsExists(paramContext)) && (!isSimplerMergeExists(paramContext))) {
        return true;
      }
    } while ((!isBackupApp(str)) || (isSimplerContactsExists(paramContext)) || (isSimplerMergeExists(paramContext)) || (isSimplerDialerExists(paramContext)));
    return true;
  }
  
  public boolean shouldShowNotificationService(Context paramContext)
  {
    String str = paramContext.getPackageName();
    if (isContactsApp(str)) {}
    while (((isMergeApp(str)) && (!isSimplerContactsExists(paramContext))) || ((isDialerApp(str)) && (!isSimplerContactsExists(paramContext)) && (!isSimplerMergeExists(paramContext))) || ((isBackupApp(str)) && (!isSimplerContactsExists(paramContext)) && (!isSimplerMergeExists(paramContext)) && (!isSimplerDialerExists(paramContext)))) {
      return true;
    }
    return false;
  }
}
