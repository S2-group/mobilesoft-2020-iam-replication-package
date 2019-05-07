package com.nvison.gpsmycity.master.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nvison.gpsmycity.data.DatabaseManager;
import com.nvison.gpsmycity.data.SessionManager;
import com.nvison.gpsmycity.entity.City;
import com.nvison.gpsmycity.entity.Upgrade;
import com.nvison.gpsmycity.master.common.AppConstants;
import com.nvison.gpsmycity.master.objects.GuidsDo;
import com.nvison.gpsmycity.master.utils.KeyValue;
import com.nvison.gpsmycity.master.utils.PreferenceUtils;
import com.nvison.gpsmycity.master.utils.SharedPrefUtils;
import com.nvison.gpsmycity.master.utils.StringUtils;
import com.nvison.gpsmycity.menutab.MainActivity;
import com.nvison.gpsmycity.util.Utils;
import com.nvison.gpsmycity.web.WebManager;
import com.skobbler.ngx.SKDeveloperKeyException;
import com.skobbler.ngx.SKMaps;
import com.skobbler.ngx.SKMapsInitSettings;
import com.skobbler.ngx.map.SKMapViewStyle;
import com.skobbler.ngx.navigation.SKAdvisorSettings;
import com.skobbler.ngx.navigation.SKAdvisorSettings.SKAdvisorLanguage;
import com.skobbler.ngx.packages.SKPackage;
import com.skobbler.ngx.packages.SKPackageManager;
import com.skobbler.ngx.util.SKLogging;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GuidSplashScreenActivity
  extends FragmentActivity
{
  public static final long KILO = 1024L;
  public static final long MEGA = 1048576L;
  public static final int REQUEST_FOR_AUTHENTICATION = 1;
  public static final int REQUEST_FOR_PROMO_CODE_STATUS = 2;
  private static final String TAG = "SplashActivity";
  private static String mapResourcesDir1;
  public static String mapResourcesDirPath = "";
  public static int newMapVersionDetected = 0;
  private final int SPLASH_DISPLAY_LENGTH = 1000;
  private TextView cityLocation;
  private TextView cityName;
  Context context;
  private boolean initializeLibrary;
  private Thread mSplashThread;
  View view;
  
  public GuidSplashScreenActivity() {}
  
  private void checkIsAppUpgraded()
  {
    if (new SessionManager(this.context).getPromoCode().equals(""))
    {
      if (this.mSplashThread != null)
      {
        this.mSplashThread.isAlive();
        this.mSplashThread.start();
      }
      return;
    }
    upgradeAppToFullVersion();
  }
  
  public static String chooseStoragePath(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1;
    if (getAvailableMemorySize(Environment.getDataDirectory().getPath()) >= 52428800L)
    {
      if ((paramContext == null) || (paramContext.getFilesDir() == null)) {
        break label78;
      }
      localObject1 = paramContext.getFilesDir().getPath();
    }
    label78:
    do
    {
      do
      {
        return localObject1;
        if ((paramContext != null) && (paramContext.getExternalFilesDir(null) != null) && (getAvailableMemorySize(paramContext.getExternalFilesDir(null).toString()) >= 52428800L)) {
          return paramContext.getExternalFilesDir(null).toString();
        }
        if ((paramContext != null) && (paramContext.getFilesDir() != null)) {
          return paramContext.getFilesDir().getPath();
        }
        localObject1 = localObject2;
      } while (paramContext == null);
      localObject1 = localObject2;
    } while (paramContext.getExternalFilesDir(null) == null);
    return paramContext.getExternalFilesDir(null).toString();
  }
  
  /* Error */
  public static long getAvailableMemorySize(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 127	android/os/StatFs
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 130	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: aload_0
    //   12: ifnull +142 -> 154
    //   15: aconst_null
    //   16: astore_3
    //   17: aload_0
    //   18: invokevirtual 136	java/lang/Object:getClass	()Ljava/lang/Class;
    //   21: ldc -118
    //   23: iconst_0
    //   24: anewarray 140	java/lang/Class
    //   27: invokevirtual 144	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   30: astore 4
    //   32: aload 4
    //   34: astore_3
    //   35: aload_3
    //   36: ifnull +106 -> 142
    //   39: aload_3
    //   40: aload_0
    //   41: iconst_0
    //   42: anewarray 132	java/lang/Object
    //   45: invokevirtual 150	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   48: checkcast 152	java/lang/Long
    //   51: invokevirtual 156	java/lang/Long:longValue	()J
    //   54: lstore_1
    //   55: lload_1
    //   56: lreturn
    //   57: astore_0
    //   58: ldc 25
    //   60: new 158	java/lang/StringBuilder
    //   63: dup
    //   64: ldc -96
    //   66: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   69: aload_0
    //   70: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: iconst_0
    //   77: invokestatic 172	com/skobbler/ngx/util/SKLogging:writeLog	(Ljava/lang/String;Ljava/lang/String;I)V
    //   80: aload_3
    //   81: astore_0
    //   82: goto -71 -> 11
    //   85: astore 4
    //   87: ldc 25
    //   89: new 158	java/lang/StringBuilder
    //   92: dup
    //   93: ldc -82
    //   95: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   98: aload 4
    //   100: invokevirtual 177	java/lang/NoSuchMethodException:getMessage	()Ljava/lang/String;
    //   103: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: iconst_0
    //   110: invokestatic 172	com/skobbler/ngx/util/SKLogging:writeLog	(Ljava/lang/String;Ljava/lang/String;I)V
    //   113: goto -78 -> 35
    //   116: astore_3
    //   117: aload_0
    //   118: invokevirtual 184	android/os/StatFs:getAvailableBlocks	()I
    //   121: i2l
    //   122: aload_0
    //   123: invokevirtual 187	android/os/StatFs:getBlockSize	()I
    //   126: i2l
    //   127: lmul
    //   128: lreturn
    //   129: astore_3
    //   130: aload_0
    //   131: invokevirtual 184	android/os/StatFs:getAvailableBlocks	()I
    //   134: i2l
    //   135: aload_0
    //   136: invokevirtual 187	android/os/StatFs:getBlockSize	()I
    //   139: i2l
    //   140: lmul
    //   141: lreturn
    //   142: aload_0
    //   143: invokevirtual 184	android/os/StatFs:getAvailableBlocks	()I
    //   146: i2l
    //   147: aload_0
    //   148: invokevirtual 187	android/os/StatFs:getBlockSize	()I
    //   151: i2l
    //   152: lmul
    //   153: lreturn
    //   154: lconst_0
    //   155: lreturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	paramString	String
    //   54	2	1	l	long
    //   1	80	3	localObject	Object
    //   116	1	3	localIllegalAccessException	IllegalAccessException
    //   129	1	3	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   30	3	4	localMethod	java.lang.reflect.Method
    //   85	14	4	localNoSuchMethodException	NoSuchMethodException
    // Exception table:
    //   from	to	target	type
    //   2	11	57	java/lang/IllegalArgumentException
    //   17	32	85	java/lang/NoSuchMethodException
    //   39	55	116	java/lang/IllegalAccessException
    //   39	55	129	java/lang/reflect/InvocationTargetException
  }
  
  public static boolean initializeLibrary(Context paramContext)
  {
    SKLogging.enableLogs(true);
    String str = new PreferenceUtils(paramContext).getmapSettingsPath(PreferenceUtils.mapSettingsPath, "");
    if (str.equals("")) {
      mapResourcesDir1 = mapResourcesDir1 + File.separator + "PreinstalledMaps" + File.separator + "v1" + File.separator + "20150413" + File.separator + "package";
    }
    SKMapsInitSettings localSKMapsInitSettings = new SKMapsInitSettings();
    localSKMapsInitSettings.setMapResourcesPaths(str, new SKMapViewStyle(str + "daystyle/", "daystyle.json"));
    SKAdvisorSettings localSKAdvisorSettings = localSKMapsInitSettings.getAdvisorSettings();
    localSKAdvisorSettings.setAdvisorConfigPath(str + "/Advisor");
    localSKAdvisorSettings.setResourcePath(str + "/Advisor/Languages");
    localSKAdvisorSettings.setLanguage(SKAdvisorSettings.SKAdvisorLanguage.LANGUAGE_EN);
    localSKAdvisorSettings.setAdvisorVoice("en");
    localSKMapsInitSettings.setAdvisorSettings(localSKAdvisorSettings);
    localSKMapsInitSettings.setPreinstalledMapsPath(str + "/PreinstalledMaps");
    localSKMapsInitSettings.setConnectivityMode(2);
    localSKMapsInitSettings.setMapDetailLevel("full/");
    try
    {
      SKMaps.getInstance().initializeSKMaps(paramContext, localSKMapsInitSettings);
      System.out.println("");
      return true;
    }
    catch (SKDeveloperKeyException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private void upgradeAppToFullVersion()
  {
    Upgrade localUpgrade = new Upgrade();
    localUpgrade.setId(1);
    try
    {
      localUpgrade.setVersion(new Sha1Hex().makeSHA1Hash(Utils.getGuide(this).inapp_code));
      Utils.setAPP_VERSION(true);
      DatabaseManager.getInstance().updateUpgradeTable(localUpgrade);
      this.mSplashThread.start();
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
  }
  
  public int getVersionCode()
  {
    try
    {
      int i = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0;
  }
  
  public void initializeGuideSplash()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent(GuidSplashScreenActivity.this.context, MainActivity.class);
        GuidSplashScreenActivity.this.startActivity(localIntent);
        GuidSplashScreenActivity.this.finish();
      }
    }, 1000L);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903146);
    this.context = this;
    this.view = getLayoutInflater().inflate(2130903146, null);
    String str = new SessionManager(this.context).getMapPath();
    Object localObject;
    int j;
    int i;
    if (!StringUtils.isEmpty(str))
    {
      localObject = new File(str + File.separator + "map");
      paramBundle = "";
      localObject = ((File)localObject).listFiles();
      j = localObject.length;
      i = 0;
    }
    for (;;)
    {
      label113:
      ImageView localImageView;
      if (i >= j)
      {
        this.initializeLibrary = initializeLibrary(this);
        localObject = SKPackageManager.getInstance().getInstalledPackages();
        if (localObject.length > 0) {
          localImageView = localObject[0];
        }
        j = 0;
        i = 0;
        label145:
        if (i < localObject.length) {
          break label468;
        }
        i = j;
        label154:
        if ((!TextUtils.isEmpty(paramBundle)) && (i == 0))
        {
          paramBundle = paramBundle.substring(0, paramBundle.lastIndexOf(".skm"));
          SKPackageManager.getInstance().addOfflinePackage(str + File.separator + "map/", paramBundle);
        }
        paramBundle = DatabaseManager.getInstance().loadCity(true);
        if (paramBundle == null) {
          finish();
        }
        Utils.setCurrentCity(paramBundle);
        new SessionManager(this.context).persistCityId(paramBundle.getCityId());
        localImageView = (ImageView)findViewById(2131493172);
        localObject = (ProgressBar)findViewById(2131493503);
      }
      try
      {
        localImageView.setImageBitmap(Utils.getBitmapFromSDCard(Uri.parse("/" + paramBundle.getImage()), str));
        this.cityLocation = ((TextView)findViewById(2131493499));
        this.cityName = ((TextView)findViewById(2131493500));
        this.cityName.setText(paramBundle.getCityName());
        this.cityLocation.setText(paramBundle.getAppTitle());
        ((ProgressBar)localObject).setVisibility(8);
        if (new PreferenceUtils(this).getUserPremium(PreferenceUtils.user_premium, 0) == 1)
        {
          paramBundle = new KeyValue(AppConstants.guideIdForUpgrade, "true");
          SharedPrefUtils.setValue(this, SharedPrefUtils.ISUPGRADED, paramBundle);
        }
        for (;;)
        {
          initializeGuideSplash();
          return;
          localImageView = localObject[i];
          if ((localImageView.isFile()) && (localImageView.getName().contains(".skm")))
          {
            paramBundle = localImageView.getName();
            break label113;
          }
          i += 1;
          break;
          label468:
          if (paramBundle.equalsIgnoreCase(localObject[i].getName()))
          {
            i = 1;
            break label154;
          }
          i += 1;
          break label145;
          if (SharedPrefUtils.getKeyValue(this, SharedPrefUtils.ISBUY, AppConstants.guideIdForUpgrade, "").equalsIgnoreCase("true"))
          {
            paramBundle = new KeyValue(AppConstants.guideIdForUpgrade, "true");
            SharedPrefUtils.setValue(this, SharedPrefUtils.ISUPGRADED, paramBundle);
          }
          else
          {
            paramBundle = new KeyValue(AppConstants.guideIdForUpgrade, "false");
            SharedPrefUtils.setValue(this, SharedPrefUtils.ISUPGRADED, paramBundle);
          }
        }
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (new SessionManager(this.context).getPersistSid().equals("")) {
      WebManager.getInstance().authenticate(this.context, 1, true);
    }
  }
  
  public class Sha1Hex
  {
    public Sha1Hex() {}
    
    public String makeSHA1Hash(String paramString)
      throws NoSuchAlgorithmException
    {
      Object localObject = MessageDigest.getInstance("SHA1");
      ((MessageDigest)localObject).reset();
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      paramString = "";
      int i = 0;
      for (;;)
      {
        if (i >= localObject.length) {
          return paramString;
        }
        paramString = paramString + Integer.toString((localObject[i] & 0xFF) + 256, 16).substring(1);
        i += 1;
      }
    }
  }
}
