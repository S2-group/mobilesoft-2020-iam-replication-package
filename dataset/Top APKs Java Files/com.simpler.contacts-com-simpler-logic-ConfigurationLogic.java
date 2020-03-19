package com.simpler.logic;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import com.google.gson.Gson;
import com.simpler.data.MetaData;
import com.simpler.data.UserCredit;
import com.simpler.data.tasks.SimplerTaskPriority;
import com.simpler.data.tasks.SimplerTaskType;
import com.simpler.utils.FilesUtils;
import com.simpler.utils.Logger;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ConfigurationLogic
  extends BaseLogic
{
  private static ConfigurationLogic B;
  private final boolean A = true;
  private HashMap<String, MetaData> C;
  private ConfigurationLogic.OnConfigurationFileSetListener D;
  public final String DEFAULT_SHARE_URL_SIMPLER_BACKUP = "simplercontacts.com/get2";
  public final String DEFAULT_SHARE_URL_SIMPLER_CONTACTS = "simplercontacts.com/get";
  public final String DEFAULT_SHARE_URL_SIMPLER_DIALER = "simplercontacts.com/get3";
  public final String DEFAULT_SHARE_URL_SIMPLER_MERGE = "simplercontacts.com/get1";
  private final int a = 5;
  private final int b = 10;
  private final int c = 5;
  private final int d = 5;
  private final int e = 1;
  private final int f = 0;
  private final int g = 500;
  private final int h = 15;
  private final int i = 15;
  private final int j = 3600;
  private final int k = 3;
  private final int l = 5;
  private final int m = 3;
  private final boolean n = false;
  private final boolean o = false;
  private final boolean p = false;
  private final int q = 7;
  private final int r = 1;
  private final long s = 20L;
  private final int t = 5;
  private final boolean u = false;
  private final boolean v = false;
  private final boolean w = true;
  private final boolean x = true;
  private final int y = 5;
  private final boolean z = true;
  
  private ConfigurationLogic() {}
  
  private String a()
  {
    return "https://getsimpler.me/pref/Android_Prefs/simpler_android_5.3.json";
  }
  
  private String a(Context paramContext)
  {
    int i1 = "Simpler".hashCode();
    int i2 = PackageLogic.getInstance().getAppName(paramContext).hashCode();
    return String.format("%s/data/%s/%s", new Object[] { Environment.getExternalStorageDirectory(), Integer.valueOf(i1), Integer.valueOf(i2) });
  }
  
  private boolean a(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
      while (paramActivity.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramActivity.next();
        if (!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015"))
        {
          boolean bool = localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015");
          if (!bool) {
            break;
          }
        }
        else
        {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramActivity)
    {
      Logger.e("Simpler", paramActivity);
    }
    return false;
  }
  
  private long b()
  {
    return getDiffInSeconds() * (101 + new Random().nextInt(99));
  }
  
  private long c()
  {
    int i1 = getDiffInSeconds();
    return i1 + (1 + new Random().nextInt(i1 - 1 - 1 + 1));
  }
  
  public static ConfigurationLogic getInstance()
  {
    if (B == null) {
      B = new ConfigurationLogic();
    }
    return B;
  }
  
  public boolean canDelete(int paramInt)
  {
    if (isDiffValueValid()) {}
    int i1;
    do
    {
      return true;
      i1 = getFreeDeletionsLeftCount();
    } while ((i1 > 0) && (i1 - paramInt >= 0));
    return false;
  }
  
  public boolean canExportToDropbox(int paramInt)
  {
    if (isDiffValueValid()) {}
    while (paramInt <= getMaxExportDropbox()) {
      return true;
    }
    return false;
  }
  
  public boolean canExportToEmail(int paramInt)
  {
    if (isDiffValueValid()) {}
    while (paramInt <= getMaxExportEmail()) {
      return true;
    }
    return false;
  }
  
  public boolean canMerge(Context paramContext, boolean paramBoolean, int paramInt)
  {
    if (isDiffValueValid()) {}
    int i1;
    do
    {
      do
      {
        return true;
        if (!PackageLogic.getInstance().isMergeApp(paramContext.getPackageName())) {
          break;
        }
      } while (!paramBoolean);
      return false;
      i1 = getFreeMergeLeftCount();
    } while ((i1 > 0) && (i1 - paramInt >= 0));
    return false;
  }
  
  public void checkAndShowUpdateDialog(Activity paramActivity)
  {
    paramActivity.runOnUiThread(new c(this, paramActivity));
  }
  
  public void checkAppTurbo(Activity paramActivity, boolean paramBoolean)
  {
    try
    {
      boolean bool1 = PackageLogic.getInstance().isContactsApp(paramActivity.getPackageName());
      boolean bool2 = isAppTurboActive();
      boolean bool3 = isDiffValueValid();
      if ((bool2) && (bool1) && (!bool3) && (a(paramActivity)))
      {
        setDiffValueValid();
        FilesUtils.saveToPreferences("recreate_option_menu", true);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
        localBuilder.setMessage(paramActivity.getString(2131100162));
        localBuilder.setCancelable(false);
        localBuilder.setPositiveButton(paramActivity.getString(2131099961), new d(this, paramBoolean, paramActivity));
        localBuilder.show();
      }
      return;
    }
    catch (Exception paramActivity)
    {
      Logger.e("Simpler", paramActivity);
    }
  }
  
  public boolean checkVersion()
  {
    return FilesUtils.getBooleanFromPreferences("check_version", true);
  }
  
  public MetaData getAppMetaData(String paramString)
  {
    if (this.C == null) {
      this.C = ((HashMap)FilesUtils.loadConfigurationMetaDataMapFromFile());
    }
    if ((this.C != null) && (this.C.containsKey(paramString))) {
      return (MetaData)this.C.get(paramString);
    }
    return null;
  }
  
  public int getAppReviewIntervalBackup()
  {
    return FilesUtils.getIntFromPreferences("app_review_interval_backup", 5);
  }
  
  public int getAppReviewIntervalContacts()
  {
    return FilesUtils.getIntFromPreferences("app_review_interval_contacts", 10);
  }
  
  public int getAppReviewIntervalDialer()
  {
    return FilesUtils.getIntFromPreferences("app_review_interval_dialer", 5);
  }
  
  public int getAppReviewIntervalMerge()
  {
    return FilesUtils.getIntFromPreferences("app_review_interval_merge", 5);
  }
  
  public int getAppReviewResetVersion()
  {
    return FilesUtils.getIntFromPreferences("app_review_reset_version", 1);
  }
  
  public long getDiffInMillis()
  {
    long l2 = FilesUtils.getLongFromPreferences("diff_in_millis", -1L);
    long l1 = l2;
    if (l2 < 0L) {
      l1 = SecurityLogic.getInstance().convertToDiff();
    }
    return l1;
  }
  
  public int getDiffInSeconds()
  {
    int i2 = FilesUtils.getIntFromPreferences("diff_in_seconds", -1);
    int i1 = i2;
    if (i2 < 0)
    {
      i1 = 1001 + new Random().nextInt(999);
      FilesUtils.saveToPreferences("diff_in_seconds", i1);
    }
    return i1;
  }
  
  public int getFreeDeletionsLeftCount()
  {
    int i1 = FilesUtils.getIntFromPreferences("free_deletions_count", 0);
    return getMaxFreeDeletions() - i1;
  }
  
  public int getFreeMergeLeftCount()
  {
    int i1 = FilesUtils.getIntFromPreferences("free_merges_count", 0);
    return getMaxFreeMerges() - i1;
  }
  
  public int getGetItFreeContactsNum()
  {
    return FilesUtils.getIntFromPreferences("get_it_free_contacts_num", 7);
  }
  
  public int getGroupMessageLimit()
  {
    return FilesUtils.getIntFromPreferences("group_message_limit", 5);
  }
  
  public long getInitialBackupDelay()
  {
    return FilesUtils.getLongFromPreferences("initial_backup_delay", 20L);
  }
  
  public int getInitialBackupMaxFails()
  {
    return FilesUtils.getIntFromPreferences("initial_backup_max_fails", 5);
  }
  
  public int getInitialBackupVersion()
  {
    return FilesUtils.getIntFromPreferences("initial_backup_version", 1);
  }
  
  public int getMaxExportDropbox()
  {
    return FilesUtils.getIntFromPreferences("max_export_dropbox", 0);
  }
  
  public int getMaxExportEmail()
  {
    return FilesUtils.getIntFromPreferences("max_export_email", 500);
  }
  
  public int getMaxFreeDeletions()
  {
    return FilesUtils.getIntFromPreferences("max_free_deletions", 15);
  }
  
  public int getMaxFreeMerges()
  {
    return FilesUtils.getIntFromPreferences("max_free_merges", 15);
  }
  
  public int getMergeServiceExecutionIntervalDays()
  {
    return FilesUtils.getIntFromPreferences("merge_service_execution_interval_days", 5);
  }
  
  public int getUpdateInterval()
  {
    return FilesUtils.getIntFromPreferences("update_interval", 3600);
  }
  
  public int getUpgradeProIntervalDays()
  {
    return FilesUtils.getIntFromPreferences("upgrade_pro_interval_days", 3);
  }
  
  public int getUpgradeProReminder()
  {
    return FilesUtils.getIntFromPreferences("upgrade_pro_reminder", 3);
  }
  
  public void increaseFreeDeletions(Context paramContext, int paramInt)
  {
    FilesUtils.saveToPreferences("free_deletions_count", FilesUtils.getIntFromPreferences("free_deletions_count", 0) + paramInt);
    saveUserCreditToFile(paramContext);
  }
  
  public void increaseFreeMerges(Context paramContext, int paramInt)
  {
    FilesUtils.saveToPreferences("free_merges_count", FilesUtils.getIntFromPreferences("free_merges_count", 0) + paramInt);
    saveUserCreditToFile(paramContext);
  }
  
  public void increaseManualMerge()
  {
    FilesUtils.saveToPreferences("manual_merge_count", FilesUtils.getIntFromPreferences("manual_merge_count", 0) + 1);
  }
  
  public boolean isAppTurboActive()
  {
    return FilesUtils.getBooleanFromPreferences("is_app_turbo_active", false);
  }
  
  public boolean isAutoUploadBackupEnabled()
  {
    return FilesUtils.getBooleanFromPreferences("auto_upload_backup", false);
  }
  
  public boolean isDiffValueValid()
  {
    return getDiffInMillis() % getDiffInSeconds() == 0L;
  }
  
  public boolean isGetItFreeEnabled()
  {
    return false;
  }
  
  public boolean isInitialBackupVisibleOnly()
  {
    return FilesUtils.getBooleanFromPreferences("initial_backup_visible_only", false);
  }
  
  public boolean isUpgradeButtonVisible()
  {
    return FilesUtils.getBooleanFromPreferences("upgrade_button_visible", true);
  }
  
  public boolean isUserBackupVisibleOnly()
  {
    return FilesUtils.getBooleanFromPreferences("user_backup_visible_only", true);
  }
  
  protected void killLogic()
  {
    B = null;
  }
  
  public void loadUserCreditFromFile(Context paramContext)
  {
    new b(this, paramContext).execute(new Void[0]);
  }
  
  public void notifyListener()
  {
    if (this.D != null) {
      this.D.onConfigurationFileSet();
    }
  }
  
  public boolean optimizelyTrackRevenue()
  {
    return FilesUtils.getBooleanFromPreferences("optimizely_track_revenue", true);
  }
  
  public void requestConfigurationFile()
  {
    new e(this, SimplerTaskType.CONFIGURATION, SimplerTaskPriority.HIGH).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
  }
  
  public void saveUserCreditToFile(Context paramContext)
  {
    int i1 = FilesUtils.getIntFromPreferences("free_merges_count", 0);
    int i2 = FilesUtils.getIntFromPreferences("free_deletions_count", 0);
    Object localObject = new UserCredit();
    ((UserCredit)localObject).setUsedMerges(i1);
    ((UserCredit)localObject).setUsedDeletions(i2);
    localObject = new Gson().toJson(localObject);
    paramContext = a(paramContext);
    File localFile = new File(paramContext);
    if (localFile.exists()) {
      localFile.delete();
    }
    localFile.getParentFile().mkdirs();
    try
    {
      localFile.createNewFile();
      paramContext = new FileWriter(paramContext);
      paramContext.write((String)localObject);
      paramContext.close();
      Logger.e("Simpler", String.format("-- save credits: [deletions] %s, [merges] %s", new Object[] { Integer.valueOf(i2), Integer.valueOf(i1) }));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Logger.e("Simpler", paramContext);
    }
  }
  
  public long setDiffValueInvalid()
  {
    long l1 = c();
    FilesUtils.saveToPreferences("diff_in_millis", l1);
    return l1;
  }
  
  public long setDiffValueValid()
  {
    long l1 = b();
    FilesUtils.saveToPreferences("diff_in_millis", l1);
    return l1;
  }
  
  public void setListener(ConfigurationLogic.OnConfigurationFileSetListener paramOnConfigurationFileSetListener)
  {
    this.D = paramOnConfigurationFileSetListener;
  }
  
  public void setMetadata(MetaData[] paramArrayOfMetaData)
  {
    if ((paramArrayOfMetaData == null) || (paramArrayOfMetaData.length == 0))
    {
      this.C = null;
      return;
    }
    this.C = new HashMap();
    int i2 = paramArrayOfMetaData.length;
    int i1 = 0;
    while (i1 < i2)
    {
      MetaData localMetaData = paramArrayOfMetaData[i1];
      this.C.put(localMetaData.getPackageName(), localMetaData);
      i1 += 1;
    }
    FilesUtils.saveConfigurationMetaDataMapToFile(this.C);
  }
  
  public boolean shouldMergeOnWelcomeFlow()
  {
    return FilesUtils.getBooleanFromPreferences("merge_on_welcome_flow", false);
  }
}
