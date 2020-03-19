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
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class LockSdConfig
{
  public static String FILE_DIR = Environment.getExternalStorageDirectory().getPath() + "/amber widgets/";
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
  
  public LockSdConfig(Context paramContext)
  {
    this.context = paramContext;
    this.currentPkg = paramContext.getApplicationContext().getPackageName();
  }
  
  private String buildLockerConfig(String paramString1, String paramString2)
  {
    localObject1 = getConfigFromSd();
    Object localObject2 = null;
    for (;;)
    {
      try
      {
        localObject1 = new JSONObject((String)localObject1);
        paramString2.printStackTrace();
      }
      catch (JSONException paramString2)
      {
        try
        {
          ((JSONObject)localObject1).put(paramString1, paramString2);
          paramString1 = (String)localObject1;
          if (paramString1 != null) {
            break;
          }
          return "";
        }
        catch (JSONException paramString2)
        {
          for (;;)
          {
            paramString1 = (String)localObject1;
          }
        }
        paramString2 = paramString2;
        paramString1 = localObject2;
      }
    }
    return paramString1.toString();
  }
  
  private String buildLockerMainConfig(String paramString1, String paramString2)
  {
    localObject1 = getConfigFromSd();
    Object localObject2 = null;
    for (;;)
    {
      try
      {
        localObject1 = new JSONObject((String)localObject1);
        paramString2.printStackTrace();
      }
      catch (JSONException paramString2)
      {
        try
        {
          ((JSONObject)localObject1).put("lock_config_main_pkg", paramString1);
          ((JSONObject)localObject1).put("lock_config_skin_pkg", paramString2);
          paramString1 = (String)localObject1;
          if (paramString1 != null) {
            break;
          }
          return "";
        }
        catch (JSONException paramString2)
        {
          for (;;)
          {
            paramString1 = (String)localObject1;
          }
        }
        paramString2 = paramString2;
        paramString1 = localObject2;
      }
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
      paramContext = paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
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
  
  /* Error */
  public static String getStringFromFile(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: sipush 1024
    //   3: newarray byte
    //   5: astore_3
    //   6: new 174	java/io/ByteArrayOutputStream
    //   9: dup
    //   10: invokespecial 175	java/io/ByteArrayOutputStream:<init>	()V
    //   13: astore 4
    //   15: ldc -79
    //   17: astore_0
    //   18: new 101	org/json/JSONObject
    //   21: dup
    //   22: invokespecial 178	org/json/JSONObject:<init>	()V
    //   25: astore 5
    //   27: aload 5
    //   29: ldc 20
    //   31: ldc 110
    //   33: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   36: pop
    //   37: aload 5
    //   39: ldc 32
    //   41: ldc 110
    //   43: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   46: pop
    //   47: aload 5
    //   49: ldc 17
    //   51: ldc 110
    //   53: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   56: pop
    //   57: aload 5
    //   59: ldc 23
    //   61: ldc 110
    //   63: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   66: pop
    //   67: aload 5
    //   69: ldc 29
    //   71: ldc 110
    //   73: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   76: pop
    //   77: aload 5
    //   79: ldc 26
    //   81: ldc 110
    //   83: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   86: pop
    //   87: aload 5
    //   89: ldc 38
    //   91: ldc 110
    //   93: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   96: pop
    //   97: aload 5
    //   99: ldc 35
    //   101: ldc 110
    //   103: invokevirtual 108	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   106: pop
    //   107: getstatic 76	com/amber/amberutils/LockSdConfig:FILE_DIR	Ljava/lang/String;
    //   110: astore 5
    //   112: new 61	java/io/File
    //   115: dup
    //   116: new 50	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   123: aload 5
    //   125: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: aload_1
    //   129: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: invokespecial 179	java/io/File:<init>	(Ljava/lang/String;)V
    //   138: astore 5
    //   140: aload_0
    //   141: astore_1
    //   142: new 181	java/io/FileInputStream
    //   145: dup
    //   146: aload 5
    //   148: invokespecial 184	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   151: astore 5
    //   153: aload_0
    //   154: astore_1
    //   155: aload 5
    //   157: aload_3
    //   158: invokevirtual 188	java/io/FileInputStream:read	([B)I
    //   161: istore_2
    //   162: iload_2
    //   163: iconst_m1
    //   164: if_icmpeq +33 -> 197
    //   167: aload_0
    //   168: astore_1
    //   169: aload 4
    //   171: aload_3
    //   172: iconst_0
    //   173: iload_2
    //   174: invokevirtual 192	java/io/ByteArrayOutputStream:write	([BII)V
    //   177: goto -24 -> 153
    //   180: astore_0
    //   181: aload_0
    //   182: invokevirtual 130	java/lang/Exception:printStackTrace	()V
    //   185: aload_1
    //   186: areturn
    //   187: astore 5
    //   189: aload 5
    //   191: invokevirtual 113	org/json/JSONException:printStackTrace	()V
    //   194: goto -87 -> 107
    //   197: aload_0
    //   198: astore_1
    //   199: new 121	java/lang/String
    //   202: dup
    //   203: aload 4
    //   205: invokevirtual 196	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   208: ldc -58
    //   210: invokespecial 201	java/lang/String:<init>	([BLjava/lang/String;)V
    //   213: astore_3
    //   214: aload_0
    //   215: astore_1
    //   216: aload_3
    //   217: invokestatic 207	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   220: ifne +5 -> 225
    //   223: aload_3
    //   224: astore_0
    //   225: aload_0
    //   226: astore_1
    //   227: aload 5
    //   229: invokevirtual 210	java/io/FileInputStream:close	()V
    //   232: aload_0
    //   233: astore_1
    //   234: aload 4
    //   236: invokevirtual 211	java/io/ByteArrayOutputStream:close	()V
    //   239: aload_0
    //   240: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	241	0	paramContext	Context
    //   0	241	1	paramString	String
    //   161	13	2	i	int
    //   5	219	3	localObject1	Object
    //   13	222	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   25	131	5	localObject2	Object
    //   187	41	5	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   142	153	180	java/lang/Exception
    //   155	162	180	java/lang/Exception
    //   169	177	180	java/lang/Exception
    //   199	214	180	java/lang/Exception
    //   216	223	180	java/lang/Exception
    //   227	232	180	java/lang/Exception
    //   234	239	180	java/lang/Exception
    //   18	107	187	org/json/JSONException
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
        }
        for (;;)
        {
          return;
          PackageManager localPackageManager = this.val$context.getPackageManager();
          if (localPackageManager != null)
          {
            List localList = localPackageManager.getInstalledPackages(8192);
            int i = 0;
            while (i < localList.size())
            {
              PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
              localObject = null;
              try
              {
                ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 128);
                localObject = localApplicationInfo;
              }
              catch (PackageManager.NameNotFoundException localNameNotFoundException2)
              {
                for (;;)
                {
                  localNameNotFoundException2.printStackTrace();
                  continue;
                  j = 0;
                }
              }
              if (localObject != null)
              {
                localObject = ((ApplicationInfo)localObject).metaData;
                if ((localObject == null) || (TextUtils.isEmpty(((Bundle)localObject).getString("LOCK_SCREEN"))) || (localPackageInfo.packageName.equals(this.val$context.getPackageName()))) {}
              }
              try
              {
                localObject = this.val$context.createPackageContext(localPackageInfo.packageName, 3);
                if ((localObject == null) || (!LockScreenPreference.isLockScreenSwitch((Context)localObject))) {
                  break label227;
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
                for (;;)
                {
                  int j;
                  localNameNotFoundException1.printStackTrace();
                }
              }
              i += 1;
            }
          }
        }
      }
    }).start();
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
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
      String str = FILE_DIR;
      File localFile1 = new File(str);
      File localFile2 = new File(str + paramString1);
      if (!localFile1.exists())
      {
        Log.d("TestFile", "Create the path:" + str);
        localFile1.mkdir();
      }
      if (!localFile2.exists())
      {
        Log.d("TestFile", "Create the file:" + paramString1);
        localFile2.createNewFile();
      }
      paramString1 = new FileOutputStream(localFile2);
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
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      saveMainAndSkinToSd(this.currentPkg, this.currentPkg);
      return this.currentPkg;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
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
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return this.currentPkg;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
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
