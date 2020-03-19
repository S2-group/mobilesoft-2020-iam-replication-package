package com.amber.amberutils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class LockSdConfig
{
  public static String FILE_DIR;
  private static final String LOCK_APPLY_LWP_PKG = "lock_apply_lwp_pkg";
  private static final String LOCK_CONFIG_FILE_NAME = "lock_sd_config";
  private static final String LOCK_CONFIG_LOCKER_SWITCH = "lock_config_locker_switch";
  private static final String LOCK_CONFIG_MAIN_PKG = "lock_config_main_pkg";
  private static final String LOCK_CONFIG_PASSWORD_CODE = "lock_config_password_code";
  private static final String LOCK_CONFIG_PASSWORD_SWITCH = "lock_config_password_switch";
  private static final String LOCK_CONFIG_PASSWORD_TYPE = "lock_config_password_type";
  private static final String LOCK_CONFIG_SKIN_PKG = "lock_config_skin_pkg";
  private static final String LOCK_CONFIG_VERIFY_ANSWER = "lock_config_verify_answer";
  private static final String LOCK_CONFIG_VERIFY_PROBLEM = "lock_config_verify_problem";
  public static final String LWP_PKG_PRE = "mobi.infolife.ezweather.livewallpaper";
  public static LockSdConfig lockSdConfig;
  private Context context;
  private String currentPkg;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory().getPath());
    localStringBuilder.append("/amber widgets/");
    FILE_DIR = localStringBuilder.toString();
  }
  
  public LockSdConfig(Context paramContext)
  {
    this.context = paramContext;
    this.currentPkg = paramContext.getApplicationContext().getPackageName();
  }
  
  private String buildLockerConfig(String paramString1, String paramString2)
  {
    Object localObject = getConfigFromSd();
    try
    {
      localObject = new JSONObject((String)localObject);
      try
      {
        ((JSONObject)localObject).put(paramString1, paramString2);
        paramString1 = (String)localObject;
      }
      catch (JSONException paramString2)
      {
        paramString1 = (String)localObject;
      }
      paramString2.printStackTrace();
    }
    catch (JSONException paramString2)
    {
      paramString1 = null;
    }
    if (paramString1 == null) {
      return "";
    }
    return paramString1.toString();
  }
  
  private String buildLockerMainConfig(String paramString1, String paramString2)
  {
    Object localObject = getConfigFromSd();
    try
    {
      localObject = new JSONObject((String)localObject);
      try
      {
        ((JSONObject)localObject).put("lock_config_main_pkg", paramString1);
        ((JSONObject)localObject).put("lock_config_skin_pkg", paramString2);
        paramString1 = (String)localObject;
      }
      catch (JSONException paramString2)
      {
        paramString1 = (String)localObject;
      }
      paramString2.printStackTrace();
    }
    catch (JSONException paramString2)
    {
      paramString1 = null;
    }
    if (paramString1 == null) {
      return "";
    }
    return paramString1.toString();
  }
  
  public static Context createContextByPkgName(Context paramContext, String paramString)
  {
    if (paramContext.getPackageName().equals(paramString)) {
      return paramContext;
    }
    try
    {
      paramString = paramContext.createPackageContext(paramString, 3);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return paramContext;
  }
  
  private String getConfigFromSd()
  {
    return getStringFromFile(this.context, "lock_sd_config");
  }
  
  public static LockSdConfig getInstance(Context paramContext)
  {
    if (lockSdConfig != null) {
      return lockSdConfig;
    }
    lockSdConfig = new LockSdConfig(paramContext);
    return lockSdConfig;
  }
  
  private Object getMateData(String paramString1, String paramString2)
    throws NullPointerException
  {
    try
    {
      paramString1 = createContextByPkgName(this.context, paramString1).getPackageManager().getApplicationInfo(paramString1, 128).metaData.get(paramString2);
      return paramString1;
    }
    catch (PackageManager.NameNotFoundException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static String getStringFromFile(Context paramContext, String paramString)
  {
    paramContext = new byte['Ѐ'];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    String str = "{\n    \"lock_config_main_pkg\": \"\",\n    \"lock_config_skin_pkg\": \"\",\n    \"lock_config_locker_switch\": \"\",\n    \"lock_config_password_code\": \"\",\n    \"lock_config_password_type\": \"\",\n    \"lock_config_password_switch\": \"\",\n    \"lock_config_verify_problem\": \"\",\n    \"lock_config_verify_answer\": \"\"\n}";
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("lock_config_main_pkg", "");
      localJSONObject.put("lock_config_skin_pkg", "");
      localJSONObject.put("lock_config_locker_switch", "");
      localJSONObject.put("lock_config_password_code", "");
      localJSONObject.put("lock_config_password_type", "");
      localJSONObject.put("lock_config_password_switch", "");
      localJSONObject.put("lock_config_verify_problem", "");
      localJSONObject.put("lock_config_verify_answer", "");
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    Object localObject1 = FILE_DIR;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(paramString);
    localObject1 = new File(((StringBuilder)localObject2).toString());
    paramString = str;
    try
    {
      localObject2 = new FileInputStream((File)localObject1);
      for (;;)
      {
        paramString = str;
        int i = ((FileInputStream)localObject2).read(paramContext);
        if (i == -1) {
          break;
        }
        paramString = str;
        localByteArrayOutputStream.write(paramContext, 0, i);
      }
      paramString = str;
      localObject1 = new String(localByteArrayOutputStream.toByteArray(), "UTF-8");
      paramContext = str;
      paramString = str;
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        paramContext = (Context)localObject1;
      }
      paramString = paramContext;
      ((FileInputStream)localObject2).close();
      paramString = paramContext;
      localByteArrayOutputStream.close();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return paramString;
  }
  
  public static void initSdConfig(Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        LockScreenPreference.setHasInitSdConfig(this.val$context);
        Object localObject;
        if (LockScreenPreference.isLockScreenSwitch(this.val$context))
        {
          localObject = LockSdConfig.getInstance(this.val$context);
          ((LockSdConfig)localObject).saveLockerSwitchToSd("lock_config_switch_open");
          ((LockSdConfig)localObject).saveMainPkgToSd(this.val$context.getPackageName());
          return;
        }
        PackageManager localPackageManager = this.val$context.getPackageManager();
        int i;
        PackageInfo localPackageInfo;
        if (localPackageManager != null)
        {
          List localList = localPackageManager.getInstalledPackages(8192);
          i = 0;
          if (i < localList.size())
          {
            localPackageInfo = (PackageInfo)localList.get(i);
            localObject = null;
            try
            {
              ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 128);
              localObject = localApplicationInfo;
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException2)
            {
              localNameNotFoundException2.printStackTrace();
            }
            if (localObject != null)
            {
              localObject = ((ApplicationInfo)localObject).metaData;
              if ((localObject == null) || (TextUtils.isEmpty(((Bundle)localObject).getString("LOCK_SCREEN"))) || (localPackageInfo.packageName.equals(this.val$context.getPackageName()))) {}
            }
          }
        }
        for (;;)
        {
          try
          {
            localObject = this.val$context.createPackageContext(localPackageInfo.packageName, 3);
            if ((localObject == null) || (!LockScreenPreference.isLockScreenSwitch((Context)localObject))) {
              break label239;
            }
            j = 1;
            if (j != 0)
            {
              localObject = LockSdConfig.getInstance(this.val$context);
              ((LockSdConfig)localObject).saveLockerSwitchToSd("lock_config_switch_open");
              ((LockSdConfig)localObject).saveMainPkgToSd(localPackageInfo.packageName);
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException1)
          {
            localNameNotFoundException1.printStackTrace();
          }
          i += 1;
          break;
          return;
          label239:
          int j = 0;
        }
      }
    }).start();
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
  {
    if (paramString != null) {
      if ("".equals(paramString)) {
        return false;
      }
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
    return false;
  }
  
  public static void writeFileToSD(String paramString1, String paramString2)
  {
    try
    {
      if (!Environment.getExternalStorageState().equals("mounted"))
      {
        Log.d("TestFile", "SD card is not avaiable/writeable right now.");
        return;
      }
      Object localObject1 = FILE_DIR;
      File localFile = new File((String)localObject1);
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(paramString1);
      localObject2 = new File(((StringBuilder)localObject2).toString());
      if (!localFile.exists())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Create the path:");
        localStringBuilder.append((String)localObject1);
        Log.d("TestFile", localStringBuilder.toString());
        localFile.mkdir();
      }
      if (!((File)localObject2).exists())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Create the file:");
        ((StringBuilder)localObject1).append(paramString1);
        Log.d("TestFile", ((StringBuilder)localObject1).toString());
        ((File)localObject2).createNewFile();
      }
      paramString1 = new FileOutputStream((File)localObject2);
      paramString1.write(paramString2.getBytes());
      paramString1.close();
      return;
    }
    catch (Exception paramString1)
    {
      Log.e("TestFile", "Error on writeFilToSD.");
      paramString1.printStackTrace();
    }
  }
  
  public void applyLwpChanged(Context paramContext, String paramString)
  {
    saveLwpPkgToSd(paramString);
    paramString = new Intent();
    paramString.setAction("broadcast_form_finish");
    paramContext.sendBroadcast(paramString);
  }
  
  public void clearLockConfig()
  {
    writeFileToSD("lock_sd_config", "");
  }
  
  public String getLockApplyLwpPkg()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_apply_lwp_pkg");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public String getLockMainPkg()
  {
    try
    {
      Object localObject = new JSONObject(getConfigFromSd());
      String str = ((JSONObject)localObject).getString("lock_config_main_pkg");
      localObject = ((JSONObject)localObject).getString("lock_config_skin_pkg");
      if (isAppExist(this.context, str))
      {
        if (localObject != null)
        {
          saveMainAndSkinToSd((String)localObject, str);
          return str;
        }
        saveMainAndSkinToSd(this.currentPkg, str);
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    saveMainAndSkinToSd(this.currentPkg, this.currentPkg);
    return this.currentPkg;
  }
  
  public String getLockSkinPkg()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_config_skin_pkg");
      if (isAppExist(this.context, str))
      {
        if (str.startsWith("mobi.infolife.ezweather")) {
          return str;
        }
      }
      else {
        return "";
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return this.currentPkg;
  }
  
  public String getLockerSwitch()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_config_locker_switch");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "lock_config_switch_close";
  }
  
  public String getPasswordCode()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_config_password_code");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public String getPasswordSwitch()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_config_password_switch");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "lock_config_switch_close";
  }
  
  public String getPasswordType()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_config_password_type");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "lock_config_password_type_four_code";
  }
  
  public String getVerifyAnswer()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_config_verify_answer");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public String getVerifyProblem()
  {
    try
    {
      String str = new JSONObject(getConfigFromSd()).getString("lock_config_verify_problem");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public boolean isOpenLocker(String paramString)
  {
    return (paramString.startsWith("mobi.infolife.ezweather.livewallpaper")) && (!TextUtils.isEmpty(getLockSkinPkg())) && (!getLockSkinPkg().equals(paramString)) && (getLockerSwitch().equals("lock_config_switch_open"));
  }
  
  public void saveLockerSwitchToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_locker_switch", paramString));
  }
  
  public void saveLwpPkgToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_apply_lwp_pkg", paramString));
  }
  
  public void saveMainAndSkinToSd(String paramString1, String paramString2)
  {
    writeFileToSD("lock_sd_config", buildLockerMainConfig(paramString2, paramString1));
  }
  
  public void saveMainPkgToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_main_pkg", paramString));
  }
  
  public void saveOpenCount()
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("OPEN_COUNT", "1"));
  }
  
  public void savePasswordCodeToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_password_code", paramString));
  }
  
  public void savePasswordSwitchToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_password_switch", paramString));
  }
  
  public void savePasswordTypeToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_password_type", paramString));
  }
  
  public void saveSkinPkgToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_skin_pkg", paramString));
  }
  
  public void saveVerifyAnswerToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_verify_answer", paramString));
  }
  
  public void saveVerifyProblemToSd(String paramString)
  {
    writeFileToSD("lock_sd_config", buildLockerConfig("lock_config_verify_problem", paramString));
  }
}
