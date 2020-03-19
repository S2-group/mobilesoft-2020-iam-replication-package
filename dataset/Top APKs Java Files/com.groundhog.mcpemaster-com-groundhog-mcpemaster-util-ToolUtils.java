package com.groundhog.mcpemaster.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.groundhog.mcpemaster.Constant;
import com.groundhog.mcpemaster.MyApplication;
import com.groundhog.mcpemaster.activity.dialog.ClubPrivilegeResourceTipDialog;
import com.groundhog.mcpemaster.activity.dialog.DialogFactory;
import com.groundhog.mcpemaster.activity.dialog.EncryptResourceTipDialog;
import com.groundhog.mcpemaster.activity.list.skin.handler.SkinActionHandler;
import com.groundhog.mcpemaster.activity.memoryClean.MemoryCleanActivity;
import com.groundhog.mcpemaster.datatracker.Tracker;
import com.groundhog.mcpemaster.entity.MarketInfo;
import com.groundhog.mcpemaster.entity.McServerVersion;
import com.groundhog.mcpemaster.entity.McVersion;
import com.groundhog.mcpemaster.entity.ResourceDetailEntity;
import com.groundhog.mcpemaster.entity.config.ConfigManager;
import com.groundhog.mcpemaster.entity.config.FuncConfigBean;
import com.groundhog.mcpemaster.entity.config.IdConfigBean;
import com.groundhog.mcpemaster.handler.WorldMapHandler;
import com.groundhog.mcpemaster.helper.ResourceActionHelper;
import com.groundhog.mcpemaster.masterclub.manager.PrivilegePluginManager;
import com.groundhog.mcpemaster.mcfloat.SaveMapView;
import com.groundhog.mcpemaster.mcfloat.model.JsItem;
import com.groundhog.mcpemaster.persistence.ExternalJsDao;
import com.groundhog.mcpemaster.persistence.ResourceDao;
import com.groundhog.mcpemaster.persistence.model.McResources;
import com.groundhog.mcpemaster.pref.PrefUtil;
import com.groundhog.mcpemaster.texture.common.TextureOperationManager;
import com.groundhog.mcpemaster.usercomment.bean.ResourceDetailResourceBean;
import com.mcbox.apiutil.MD5;
import com.mcbox.pesdk.archive.io.LevelDataConverter;
import com.mcbox.pesdk.launcher.LauncherFunc;
import com.mcbox.pesdk.launcher.LauncherManager;
import com.mcbox.pesdk.launcher.LauncherRuntime;
import com.mcbox.pesdk.mcfloat.util.EncryptUtil;
import com.mcbox.pesdk.mcfloat.util.LauncherUtil;
import com.mcbox.pesdk.util.McInstallInfoUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtils
{
  public static String TAG = "ToolUtils";
  public static String UMENG_UPDATE_VERSIONNAME;
  public static final String a = "wovCisOZwrTDlsKuwofCrMKZwqfCmsOQwoXCiMKTw5Y=";
  public static List<String> allScriptList;
  public static Map<String, Integer> downloadingMap = new HashMap();
  public static Map<String, Integer> downloadingSkin = new HashMap();
  private static ExternalJsDao enable_js_dao;
  public static Map<String, JsItem> external_js_map;
  public static String gameMapPath = null;
  private static boolean isRequiredReport;
  protected static Context mContext;
  public static String mapBackupPath;
  protected static boolean thisTimeNoTip;
  public static String verSplit;
  private boolean mIsLight = false;
  
  static
  {
    allScriptList = null;
    UMENG_UPDATE_VERSIONNAME = null;
    mContext = null;
    external_js_map = new HashMap();
    thisTimeNoTip = false;
    verSplit = "/";
    isRequiredReport = true;
    mapBackupPath = null;
  }
  
  public ToolUtils() {}
  
  public static void actionEnableMenClean(Activity paramActivity, boolean paramBoolean, Integer paramInteger)
  {
    if ((paramBoolean) || (PrivilegePluginManager.a(MyApplication.getmContext()).c()))
    {
      Intent localIntent = new Intent(paramActivity, MemoryCleanActivity.class);
      localIntent.putExtra("startType", paramInteger);
      localIntent.putExtra("isEnableMemClean", paramBoolean);
      paramActivity.startActivity(localIntent);
      return;
    }
    if (paramInteger.equals(Constant.startMcInternal))
    {
      startMcInternal(paramActivity);
      return;
    }
    startPlug(paramActivity);
  }
  
  public static int canUseThisResource(int paramInt, List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return 0;
    }
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        break label414;
      }
      paramList = (String)localIterator.next();
      String str1 = "";
      int i = 0;
      char c;
      while (i < paramList.length())
      {
        c = paramList.charAt(i);
        if (!Character.isLetter(c)) {
          break;
        }
        str1 = str1 + c;
        i += 1;
      }
      String str2 = "";
      while (i < paramList.length())
      {
        c = paramList.charAt(i);
        if (!Character.isDigit(c)) {
          break;
        }
        str2 = str2 + c;
        i += 1;
      }
      paramList = null;
      if (paramInt == 0)
      {
        paramList = ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIdConfigConfig().getModSupportTag();
        if (!ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIsSupportPlugin()) {
          return 2;
        }
      }
      else if (paramInt == 1)
      {
        paramList = ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIdConfigConfig().getTextureSupportTag();
        if (!ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIsSupportTexture()) {
          return 2;
        }
      }
      else if (paramInt == 2)
      {
        paramList = ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIdConfigConfig().getAddonSupportTag();
        if (!ConfigManager.getInstance(MyApplication.getmContext()).getConfig().isSupportAddon()) {
          return 2;
        }
      }
      if (TextUtils.isEmpty(paramList)) {
        break;
      }
      String str3 = "";
      String str4 = "";
      i = 0;
      if (i < paramList.length())
      {
        c = paramList.charAt(i);
        if (Character.isDigit(c)) {
          str3 = str3 + c;
        }
        for (;;)
        {
          i += 1;
          break;
          if (!Character.isLetter(c)) {
            break label364;
          }
          str4 = str4 + c;
        }
      }
      label364:
      paramList = Integer.valueOf(str2);
      str1 = str1.toUpperCase();
      try
      {
        i = Integer.parseInt(str3);
        if (str4.toUpperCase().equals(str1))
        {
          int j = paramList.intValue();
          if (i >= j) {
            break;
          }
          return 1;
        }
      }
      catch (Exception paramList) {}
    }
    label414:
    return 2;
  }
  
  private static boolean checkInternalScriptCanUse(String paramString)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((TextUtils.isEmpty(paramString)) || ((!paramString.endsWith(".modpkg")) && (!paramString.endsWith(".js")))) {
      bool1 = false;
    }
    label70:
    do
    {
      do
      {
        do
        {
          return bool1;
          if (!paramString.equals("quality_plugin.modpkg")) {
            break label70;
          }
          if (!PrivilegePluginManager.a(mContext).b(0)) {
            break;
          }
          bool1 = bool2;
        } while (PrivilegePluginManager.a(mContext).f(0));
        return false;
        bool1 = bool2;
      } while (!paramString.equals("quick_building_plugin.modpkg"));
      if (!PrivilegePluginManager.a(mContext).b(1)) {
        break;
      }
      bool1 = bool2;
    } while (PrivilegePluginManager.a(mContext).f(1));
    return false;
  }
  
  public static void checkLoadResourceMd5(Activity paramActivity)
  {
    List localList = new ExternalJsDao(paramActivity.getApplicationContext()).listAllOpenJs();
    Object localObject2 = new ResourceDao(paramActivity);
    try
    {
      EncryptUtil.isEncryptSkin = false;
      EncryptUtil.encryptPlugin.clear();
      EncryptUtil.clearPayModScriptList();
      str = PrefUtil.getSkin();
      if (!StringUtils.isNull(str))
      {
        if (!str.endsWith(".png")) {
          break label427;
        }
        localObject1 = new File(Environment.getExternalStorageDirectory(), "/games/com.mojang/minecraftpe/custom.png");
        localObject2 = ((ResourceDao)localObject2).getById(Integer.valueOf(str.substring(str.lastIndexOf("/") + 1, str.length()).split("\\.")[0]).intValue());
        if ((localObject2 != null) && (((McResources)localObject2).getEncryptType() == 2))
        {
          if ((new MD5().getMD5((File)localObject1).equalsIgnoreCase(((McResources)localObject2).getMd5())) && (MyApplication.getApplication().getUserId() != -1L) && ((!((McResources)localObject2).isClubPrivileges()) || (MyApplication.getApplication().isClubMember())) && ((((McResources)localObject2).isClubPrivileges()) || (((McResources)localObject2).getUserId() == MyApplication.getApplication().getUserId()))) {
            break label448;
          }
          PrefUtil.setSkin(null, paramActivity);
        }
      }
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        String str;
        Object localObject1;
        label223:
        label427:
        paramActivity.printStackTrace();
        continue;
        label448:
        EncryptUtil.isEncryptSkin = true;
      }
    }
    if (localList != null)
    {
      paramActivity = localList.iterator();
      if (paramActivity.hasNext()) {
        localObject1 = (JsItem)paramActivity.next();
      }
    }
    for (;;)
    {
      try
      {
        if ((StringUtils.isNull(((JsItem)localObject1).getFullName())) || ((!((JsItem)localObject1).getFullName().endsWith(".js")) && (!((JsItem)localObject1).getFullName().endsWith(".modpkg"))) || (((JsItem)localObject1).getMd5() == null)) {
          break label223;
        }
        if ((!new MD5().getMD5(new File(((JsItem)localObject1).getFullName())).equalsIgnoreCase(((JsItem)localObject1).getMd5())) || (MyApplication.getApplication().getUserId() == -1L) || ((((JsItem)localObject1).isClubPrivileges()) && (!MyApplication.getApplication().isClubMember()))) {
          break label461;
        }
        if ((((JsItem)localObject1).isClubPrivileges()) || (MyApplication.getApplication().getUserId() == ((JsItem)localObject1).getUserId())) {
          break label456;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      localObject1 = ((JsItem)localObject1).getFullName();
      if ((i == 0) || (localObject1 == null) || (!((String)localObject1).contains("/"))) {
        break label223;
      }
      localObject1 = ((String)localObject1).substring(((String)localObject1).lastIndexOf("/") + 1, ((String)localObject1).length());
      EncryptUtil.encryptPlugin.add(localObject1);
      break label223;
      break label223;
      File localFile = new File(str);
      break;
      return;
      label456:
      int i = 1;
      continue;
      label461:
      i = 0;
    }
  }
  
  public static boolean checkMcpeInstalled(Activity paramActivity)
  {
    if (!McInstallInfoUtil.isInstallMc(paramActivity))
    {
      ToastUtils.showCustomToast(paramActivity, paramActivity.getString(2131165862));
      return false;
    }
    return true;
  }
  
  public static void checkToShowPraise(Activity paramActivity)
  {
    Object localObject = PrefUtil.getPraiseInfo(paramActivity);
    if (StringUtils.isNull((String)localObject)) {}
    String str;
    do
    {
      long l1;
      long l2;
      int i;
      do
      {
        do
        {
          return;
          localObject = ((String)localObject).split("_");
        } while ((localObject.length != 4) || (localObject[1].equals("0")));
        updatePraiseInfo(paramActivity, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
        l1 = Long.parseLong(localObject[3]);
        l2 = System.currentTimeMillis();
        i = Integer.parseInt(localObject[2]);
      } while ((i >= 3) || ((i == 0) && (l2 - l1 < 86400000L)) || ((i == 1) && (l2 - l1 < 604800000L)) || ((i == 2) && (l2 - l1 < -1702967296L)));
      str = "" + McInstallInfoUtil.getVersionCode(paramActivity, paramActivity.getPackageName());
    } while (!localObject[0].equals(str));
    DialogFactory.ShowPraiseTipDialog(paramActivity);
  }
  
  public static boolean cleanTextureAndPlugin(Activity paramActivity)
  {
    String str1 = PrefUtil.getCurrentMcVersion(paramActivity);
    String str2 = McInstallInfoUtil.getMCVersion(paramActivity);
    if (str1 == null)
    {
      if (str2 != null) {
        DialogFactory.ShowSwitchGameTipDialog(paramActivity);
      }
      PrefUtil.setCurrentMcVersion(paramActivity, str2);
      return false;
    }
    if ((!str1.equals(str2)) && (!McVersion.fromVersionString(str1).isCompatibleVersion(McVersion.fromVersionString(str2))))
    {
      TextureOperationManager.getInstance(paramActivity).disableAllTexture();
      new ExternalJsDao(paramActivity).cleanAllEnableScript();
      DialogFactory.ShowSwitchGameTipDialog(paramActivity);
      PrefUtil.setCurrentMcVersion(paramActivity, str2);
      return false;
    }
    return true;
  }
  
  public static void collectDeviceInfo(Context paramContext)
  {
    try
    {
      localObject = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 1);
      if (localObject == null) {
        break label87;
      }
      if (((PackageInfo)localObject).versionName != null) {
        break label157;
      }
      paramContext = "null";
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        for (;;)
        {
          Object localObject;
          label87:
          int j;
          int i;
          ((Field)localObject).setAccessible(true);
          LogManager.d(TAG, ((Field)localObject).getName() + " : " + ((Field)localObject).get(null));
          i += 1;
          continue;
          label157:
          paramContext = ((PackageInfo)localObject).versionName;
        }
        paramContext = paramContext;
        paramContext.printStackTrace();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          LogManager.LogError(TAG, "an error occured when collect crash info:" + localException.getMessage());
        }
      }
    }
    localObject = ((PackageInfo)localObject).versionCode + "";
    LogManager.d(TAG, "versionName:" + paramContext + ",versionCode=" + (String)localObject);
    paramContext = Build.class.getDeclaredFields();
    j = paramContext.length;
    i = 0;
    if (i < j) {
      localObject = paramContext[i];
    }
  }
  
  public static boolean copyApkFromAssets(Activity paramActivity, String paramString1, String paramString2)
  {
    try
    {
      paramActivity = paramActivity.getAssets().open(paramString1);
      paramString1 = new File(paramString2);
      paramString1.createNewFile();
      paramString1 = new FileOutputStream(paramString1);
      paramString2 = new byte['Ѐ'];
      for (;;)
      {
        int i = paramActivity.read(paramString2);
        if (i <= 0) {
          break;
        }
        paramString1.write(paramString2, 0, i);
      }
      paramString1.close();
    }
    catch (IOException paramActivity)
    {
      paramActivity.printStackTrace();
      return false;
    }
    paramActivity.close();
    return true;
  }
  
  public static int countMap(String paramString)
  {
    int i = 1;
    paramString = Pattern.compile("-").matcher(paramString);
    while (paramString.find()) {
      i += 1;
    }
    return i;
  }
  
  private static void encryptResourceProcess(Activity paramActivity, boolean paramBoolean, Integer paramInteger)
  {
    EncryptResourceTipDialog localEncryptResourceTipDialog = new EncryptResourceTipDialog(paramActivity, new ToolUtils.3(paramInteger, paramActivity, paramBoolean));
    localEncryptResourceTipDialog.setIsCheckPay(true);
    if ((paramInteger == Constant.startMcInternal) || (!PrefUtil.getFloatingWindowStatue(paramActivity))) {
      localEncryptResourceTipDialog.setIsCheckPay(false);
    }
    if (localEncryptResourceTipDialog.checkEncryptResIsMatchUserId(MyApplication.getApplication().getUserId()))
    {
      localEncryptResourceTipDialog.show();
      return;
    }
    actionEnableMenClean(paramActivity, paramBoolean, paramInteger);
  }
  
  public static void getAllActivitys(Context paramContext) {}
  
  public static List<String> getAllInternalScripts(Context paramContext)
  {
    int i = 0;
    if (allScriptList == null)
    {
      Object localObject1 = mContext.getDir("modscripts", 0);
      if ((((File)localObject1).exists()) && (((File)localObject1).isDirectory()))
      {
        paramContext = new ArrayList();
        localObject1 = ((File)localObject1).listFiles();
        int j = localObject1.length;
        while (i < j)
        {
          Object localObject2 = localObject1[i];
          if (checkInternalScriptCanUse(localObject2.getName())) {
            paramContext.add(localObject2.getName());
          }
          i += 1;
        }
        if (paramContext.size() > 0) {
          allScriptList = paramContext;
        }
      }
    }
    return allScriptList;
  }
  
  public static ArrayList<MarketInfo> getAllMarketByInstallApp(Activity paramActivity)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      LogManager.LogInfo(TAG, "appName=" + localPackageInfo.applicationInfo.loadLabel(paramActivity.getPackageManager()).toString() + ",packageName=" + localPackageInfo.packageName);
      if (ChannelInfo.hasMarket(localPackageInfo.packageName).booleanValue())
      {
        MarketInfo localMarketInfo = new MarketInfo();
        localMarketInfo.setAppName(localPackageInfo.applicationInfo.loadLabel(paramActivity.getPackageManager()).toString());
        localMarketInfo.setPackageName(localPackageInfo.packageName);
        localMarketInfo.setVersionName(localPackageInfo.versionName);
        localMarketInfo.setVersionCode(localPackageInfo.versionCode);
        localMarketInfo.setAppIcon(localPackageInfo.applicationInfo.loadIcon(paramActivity.getPackageManager()));
        localArrayList.add(localMarketInfo);
      }
    }
    return localArrayList;
  }
  
  public static List<String> getAllScriptsList(Context paramContext)
  {
    String str1;
    String str2;
    File[] arrayOfFile;
    Object localObject;
    int j;
    String str3;
    int i;
    if (allScriptList == null)
    {
      str1 = McInstallInfoUtil.getMCVersion(paramContext) + "";
      str2 = str1.substring(0, str1.lastIndexOf("."));
      arrayOfFile = paramContext.getDir("modscripts", 0).listFiles();
      if ((arrayOfFile != null) && (arrayOfFile.length > 0))
      {
        localObject = "";
        j = 0;
        if (j < arrayOfFile.length)
        {
          str3 = arrayOfFile[j].getName();
          if (str3.split("_").length > 2) {
            i = 1;
          }
        }
      }
    }
    for (;;)
    {
      label106:
      paramContext = (Context)localObject;
      if (i != 0) {
        if (j != arrayOfFile.length - 1) {
          break label253;
        }
      }
      label253:
      for (paramContext = (String)localObject + str3;; paramContext = (String)localObject + str3 + ";")
      {
        j += 1;
        localObject = paramContext;
        break;
        if (str3.indexOf("_") > -1)
        {
          paramContext = str3.substring(0, str3.lastIndexOf("_") + 1);
          if ((!str3.equals(paramContext + str1 + ".js")) && (!str3.equals(paramContext + str2 + ".js"))) {
            break label316;
          }
          i = 1;
          break label106;
        }
        i = 1;
        break label106;
      }
      if (((String)localObject).trim().length() > 0) {
        allScriptList = new ArrayList(Arrays.asList(((String)localObject).split(";")));
      }
      return allScriptList;
      label316:
      i = 0;
    }
  }
  
  public static String getAppVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static long getAvailaleSize()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      return i * l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1L;
  }
  
  public static String getCountConversion(Integer paramInteger)
  {
    return getCountConversion(paramInteger, MyApplication.getApplication().getResources().getString(2131165619));
  }
  
  public static String getCountConversion(Integer paramInteger, String paramString)
  {
    if (paramInteger != null) {
      try
      {
        if (paramInteger.intValue() > 10000000) {
          return String.format(paramString, new Object[] { String.valueOf(Math.round(paramInteger.intValue() / 10000000)) + StringUtils.getString(2131165452) });
        }
        if (paramInteger.intValue() > 10000) {
          return String.format(paramString, new Object[] { String.valueOf(Math.round(paramInteger.intValue() / 10000)) + StringUtils.getString(2131165453) });
        }
        if (paramInteger.intValue() > 1000) {
          return String.format(paramString, new Object[] { String.valueOf(Math.round(paramInteger.intValue() / 1000)) + StringUtils.getString(2131165454) });
        }
        paramInteger = String.format(paramString, new Object[] { String.valueOf(paramInteger) });
        return paramInteger;
      }
      catch (Exception paramInteger)
      {
        paramInteger.printStackTrace();
      }
    }
    return "";
  }
  
  public static String getCountString(Integer paramInteger)
  {
    Integer localInteger = paramInteger;
    if (paramInteger == null) {
      localInteger = Integer.valueOf(0);
    }
    return String.format(MyApplication.getApplication().getString(2131165619), new Object[] { String.valueOf(localInteger) });
  }
  
  public static String getDetailSupportVersion(List<McServerVersion> paramList)
  {
    Iterator localIterator = null;
    Object localObject2 = null;
    Object localObject1 = localIterator;
    if (paramList != null)
    {
      localObject1 = localIterator;
      if (paramList.size() > 0)
      {
        localIterator = paramList.iterator();
        paramList = localObject2;
        localObject1 = paramList;
        if (localIterator.hasNext())
        {
          localObject1 = (McServerVersion)localIterator.next();
          if (isLetterOrNumber(((McServerVersion)localObject1).getAttributeName())) {
            break label106;
          }
          if (paramList == null) {
            paramList = ((McServerVersion)localObject1).getAttributeName();
          }
        }
      }
    }
    label106:
    for (;;)
    {
      break;
      paramList = paramList + verSplit + ((McServerVersion)localObject1).getAttributeName();
      continue;
      return localObject1;
    }
  }
  
  public static String getDeviceId()
  {
    String str2 = ((TelephonyManager)MyApplication.getApplication().getSystemService("phone")).getDeviceId();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
    }
    return str1;
  }
  
  public static Set<String> getEnabledScripts()
  {
    String str = getPrefs(1).getString("enabledScripts", "");
    if (str.equals("")) {
      return new HashSet();
    }
    return new HashSet(Arrays.asList(str.split(";")));
  }
  
  public static String getGameMapPath()
  {
    if (gameMapPath == null) {
      gameMapPath = new File(Environment.getExternalStorageDirectory(), "games/com.mojang/minecraftWorlds").getAbsolutePath();
    }
    return gameMapPath;
  }
  
  public static String getGameVer(String paramString)
  {
    String str = paramString;
    if (!TextUtils.isEmpty(paramString))
    {
      str = paramString;
      if (paramString.substring(paramString.length() - 1, paramString.length()).equals("0")) {
        str = paramString.substring(0, paramString.length() - 2);
      }
    }
    return str;
  }
  
  private static String getLastServerVersion(String paramString)
  {
    int i = 0;
    if (-1 == paramString.indexOf(verSplit))
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramString;
      paramString = arrayOfString;
      if (paramString.length - 1 >= 0) {
        break label46;
      }
    }
    for (;;)
    {
      return paramString[i];
      paramString = paramString.split(verSplit);
      break;
      label46:
      i = paramString.length - 1;
    }
  }
  
  public static String getMapBackupPath()
  {
    if (mapBackupPath == null) {
      mapBackupPath = new File(Environment.getExternalStorageDirectory(), "mcpemaster/mapsave/").getAbsolutePath();
    }
    return mapBackupPath;
  }
  
  public static MarketInfo getMarketForUpdate(Activity paramActivity)
  {
    paramActivity = getMarketOfCurChannel(paramActivity);
    if (paramActivity != null) {}
    return paramActivity;
  }
  
  public static MarketInfo getMarketOfCurChannel(Activity paramActivity)
  {
    ChannelInfo localChannelInfo = ChannelInfo.getCurChannelInfo();
    if (!StringUtils.isNull(localChannelInfo.getmMarketUrl()))
    {
      paramActivity = new MarketInfo();
      paramActivity.setMarketUrl(localChannelInfo.getmMarketUrl());
      paramActivity.setMinecraftUrl(localChannelInfo.getmMineCraftUrl());
      return paramActivity;
    }
    Object localObject = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localChannelInfo != null) && (localChannelInfo.getmMarketPackage().equals(localPackageInfo.packageName)))
      {
        localObject = new MarketInfo();
        ((MarketInfo)localObject).setAppName(localPackageInfo.applicationInfo.loadLabel(paramActivity.getPackageManager()).toString());
        ((MarketInfo)localObject).setMarketUrl(localChannelInfo.getmMarketUrl());
        ((MarketInfo)localObject).setMinecraftUrl(localChannelInfo.getmMineCraftUrl());
        ((MarketInfo)localObject).setPackageName(localPackageInfo.packageName);
        ((MarketInfo)localObject).setVersionName(localPackageInfo.versionName);
        ((MarketInfo)localObject).setVersionCode(localPackageInfo.versionCode);
        ((MarketInfo)localObject).setAppIcon(localPackageInfo.applicationInfo.loadIcon(paramActivity.getPackageManager()));
        return localObject;
      }
    }
    return null;
  }
  
  public static String getPlugFileName(String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    if (paramBoolean) {
      return Environment.getExternalStorageDirectory() + "/" + "mcpemaster/scripts" + File.separator + paramString + ".js";
    }
    return paramString + ".js";
  }
  
  public static List<String> getPluginAppVersion(ResourceDetailEntity paramResourceDetailEntity)
  {
    return paramResourceDetailEntity.getChildVersion();
  }
  
  public static List<String> getPluginAppVersion(ResourceDetailResourceBean paramResourceDetailResourceBean)
  {
    return McpMasterUtils.getChildVersion(paramResourceDetailResourceBean.getVersions());
  }
  
  public static SharedPreferences getPrefs(int paramInt)
  {
    
    switch (paramInt)
    {
    default: 
      return PreferenceManager.getDefaultSharedPreferences(mContext);
    case 1: 
      return mContext.getSharedPreferences("mcpelauncherprefs", 0);
    }
    return mContext.getSharedPreferences("safe_mode_counter", 0);
  }
  
  public static String getProcessName(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == paramInt) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  private static String getReallyGameVersion(Map<String, String> paramMap, String paramString, int paramInt)
  {
    Object localObject2 = paramMap.entrySet();
    paramMap = null;
    Object localObject1 = null;
    boolean bool2;
    label98:
    int i;
    boolean bool1;
    label115:
    boolean bool3;
    if (localObject2 != null)
    {
      localObject2 = ((Set)localObject2).iterator();
      bool2 = false;
      paramMap = (Map<String, String>)localObject1;
      if (((Iterator)localObject2).hasNext())
      {
        localObject1 = (Map.Entry)((Iterator)localObject2).next();
        paramMap = ((Map.Entry)localObject1).getKey().toString();
        localObject1 = ((Map.Entry)localObject1).getValue().toString();
        if (-1 != paramMap.indexOf(verSplit)) {
          break label189;
        }
        paramMap = new String[] { paramMap };
        localObject1 = getLastServerVersion((String)localObject1);
        int j = paramMap.length;
        i = 0;
        bool1 = bool2;
        if (i < j)
        {
          bool1 = hasMatchedTheResource(paramMap[i], paramString);
          if (!bool1) {
            break label200;
          }
        }
        if (paramInt != 0) {
          break label207;
        }
        bool3 = ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIsSupportPlugin();
      }
    }
    for (;;)
    {
      paramMap = (Map<String, String>)localObject1;
      bool2 = bool1;
      if (!bool3) {
        break;
      }
      paramMap = (Map<String, String>)localObject1;
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      paramMap = ConfigManager.getInstance(MyApplication.getmContext()).getVisualVerionName();
      return paramMap;
      label189:
      paramMap = paramMap.split(verSplit);
      break label98;
      label200:
      i += 1;
      break label115;
      label207:
      if (paramInt == 1) {
        bool3 = ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIsSupportTexture();
      } else {
        bool3 = true;
      }
    }
  }
  
  public static List<String> getResServerVersion(List<McServerVersion> paramList)
  {
    if ((paramList == null) && (paramList.size() < 1)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    Pattern localPattern = Pattern.compile("^[a-zA-Z][0-9]$", 2);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      McServerVersion localMcServerVersion = (McServerVersion)paramList.next();
      if ((!TextUtils.isEmpty(localMcServerVersion.getAttributeName())) && (localPattern.matcher(localMcServerVersion.getAttributeName()).matches())) {
        localArrayList.add(localMcServerVersion.getAttributeName().trim());
      }
    }
    return localArrayList;
  }
  
  public static String getSupportVerson(List<McServerVersion> paramList)
  {
    Object localObject;
    if ((paramList != null) && (paramList.size() > 0))
    {
      Iterator localIterator = paramList.iterator();
      paramList = null;
      localObject = paramList;
      if (localIterator.hasNext())
      {
        localObject = (McServerVersion)localIterator.next();
        if (paramList == null) {}
        for (paramList = ((McServerVersion)localObject).getAttributeName();; paramList = paramList + verSplit + ((McServerVersion)localObject).getAttributeName()) {
          break;
        }
      }
    }
    else
    {
      localObject = null;
    }
    return localObject;
  }
  
  public static List<String> getTextureAppVersion(ResourceDetailEntity paramResourceDetailEntity)
  {
    return paramResourceDetailEntity.getChildVersion();
  }
  
  public static List<String> getTextureAppVersion(ResourceDetailResourceBean paramResourceDetailResourceBean)
  {
    return McpMasterUtils.getChildVersion(paramResourceDetailResourceBean.getVersions());
  }
  
  public static String getUmengUpdateApp()
  {
    if (UMENG_UPDATE_VERSIONNAME == null)
    {
      Tracker.b(MyApplication.getApplication());
      String str = Tracker.a(MyApplication.getApplication(), "upgrade_mode");
      UMENG_UPDATE_VERSIONNAME = str;
      LogManager.d(TAG, "MainActivity.prepare4UmengUpdate, update_mode = " + str);
      if (StringUtils.isNull(str)) {
        UMENG_UPDATE_VERSIONNAME = "";
      }
    }
    return UMENG_UPDATE_VERSIONNAME;
  }
  
  public static String getVersionCode()
  {
    try
    {
      Object localObject = MyApplication.getmContext().getPackageManager().getPackageInfo(MyApplication.getmContext().getPackageName(), 0);
      localObject = ((PackageInfo)localObject).versionCode + "";
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "unknown";
  }
  
  private static boolean hasMatchedTheResource(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {}
    for (;;)
    {
      return false;
      if (!paramString1.equals("ALL_VERSION"))
      {
        String str1 = "";
        int i = 0;
        char c;
        while (i < paramString1.length())
        {
          c = paramString1.charAt(i);
          if (!Character.isLetter(c)) {
            break;
          }
          str1 = str1 + c;
          i += 1;
        }
        String str2 = "";
        while (i < paramString1.length())
        {
          c = paramString1.charAt(i);
          if (!Character.isDigit(c)) {
            break;
          }
          str2 = str2 + c;
          i += 1;
        }
        paramString1 = "";
        String str3 = "";
        i = 0;
        if (i < paramString2.length())
        {
          c = paramString2.charAt(i);
          if (Character.isDigit(c)) {
            paramString1 = paramString1 + c;
          }
          for (;;)
          {
            i += 1;
            break;
            if (!Character.isLetter(c)) {
              break label221;
            }
            str3 = str3 + c;
          }
        }
        label221:
        paramString2 = Integer.valueOf(str2);
        str1 = str1.toUpperCase();
        try
        {
          i = Integer.parseInt(paramString1);
          if (str3.toUpperCase().equals(str1))
          {
            int j = paramString2.intValue();
            if (i >= j) {
              return true;
            }
          }
        }
        catch (Exception paramString1) {}
      }
    }
    return false;
  }
  
  public static boolean hasResourceUsing(Activity paramActivity)
  {
    SkinActionHandler localSkinActionHandler = new SkinActionHandler(paramActivity);
    List localList = new ExternalJsDao(paramActivity).listAllOpenJs();
    return (TextureOperationManager.getInstance(paramActivity).hasTextureUsing()) || (localSkinActionHandler.isUsingSkin()) || ((localList != null) && (localList.size() > 0));
  }
  
  public static boolean isInstallMapMaster()
  {
    boolean bool = false;
    try
    {
      PackageInfo localPackageInfo = MyApplication.getApplication().getPackageManager().getPackageInfo(Constant.MAP_PACKAGE_NAME, 0);
      if (localPackageInfo != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isInstallMultiplayerMaster()
  {
    boolean bool = false;
    try
    {
      PackageInfo localPackageInfo = MyApplication.getApplication().getPackageManager().getPackageInfo(Constant.MUL_PACKAGE_NAME, 0);
      if (localPackageInfo != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isInstallYouTube()
  {
    boolean bool = false;
    try
    {
      PackageInfo localPackageInfo = MyApplication.getApplication().getPackageManager().getPackageInfo(Constant.YOUTUBE_PACKAGE_NAME, 0);
      if (localPackageInfo != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isLetterOrDigit(String paramString)
  {
    return paramString.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
  }
  
  public static boolean isLetterOrNumber(String paramString)
  {
    return paramString.matches("^[A-Za-z0-9]+$");
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    paramContext = getProcessName(paramContext, Process.myPid());
    return (paramContext != null) && (paramContext.equals("com.groundhog.mcpemaster"));
  }
  
  public static boolean isSpecialModel()
  {
    String str = Build.MANUFACTURER;
    return (str != null) && (str.equalsIgnoreCase("Meizu"));
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static void killMCProgress(Activity paramActivity)
  {
    if (WorldMapHandler.isMcRunning(paramActivity)) {
      McInstallInfoUtil.killMc(paramActivity);
    }
  }
  
  public static void loadAllEnabledModPkgTexturePack()
  {
    LauncherRuntime localLauncherRuntime = LauncherManager.getInstance().getLauncherRuntime();
    if (localLauncherRuntime == null) {}
    for (;;)
    {
      return;
      Object localObject1 = loadAllExternalScript();
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (JsItem)((Iterator)localObject1).next();
          if (((JsItem)localObject2).state == 0)
          {
            localObject2 = new File(((JsItem)localObject2).getFullName());
            if (((File)localObject2).getName().endsWith(".modpkg")) {
              try
              {
                localLauncherRuntime.loadModPkg((File)localObject2);
              }
              catch (IOException localIOException)
              {
                localIOException.printStackTrace();
              }
            }
          }
        }
      }
    }
  }
  
  public static List<Integer> loadAllEnabledScriptIds()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = loadAllExternalScript();
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        JsItem localJsItem = (JsItem)((Iterator)localObject).next();
        if (localJsItem.state == 0)
        {
          File localFile = new File(localJsItem.getFullName());
          if ((localFile.exists()) && ((localFile.getName().endsWith(".js")) || (localFile.getName().endsWith(".modpkg")))) {
            localArrayList.add(localJsItem.getOriId());
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static List<JsItem> loadAllExternalScript()
  {
    if (enable_js_dao == null) {
      enable_js_dao = new ExternalJsDao(mContext);
    }
    return enable_js_dao.listAll();
  }
  
  public static void loadExternalScriptsaCB()
  {
    LauncherRuntime localLauncherRuntime = LauncherManager.getInstance().getLauncherRuntime();
    if (localLauncherRuntime == null) {}
    for (;;)
    {
      return;
      Object localObject = loadAllExternalScript();
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          JsItem localJsItem = (JsItem)((Iterator)localObject).next();
          if (localJsItem.state == 0) {
            localLauncherRuntime.showMessage(localJsItem.getFullName(), StringUtils.getString(2131165451));
          }
          try
          {
            File localFile = new File(localJsItem.getFullName());
            if ((localFile.getName().endsWith(".js")) || (localFile.getName().endsWith(".modpkg"))) {
              localLauncherRuntime.loadScript(localFile);
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
            }
          }
          localJsItem.setState(1);
        }
      }
    }
  }
  
  public static void marketDetailPage(Activity paramActivity, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      if ("com.sec.android.app.samsungapps".equals(paramString1)) {
        localIntent.setData(Uri.parse("samsungapps://ProductDetail/" + paramString2));
      }
      for (;;)
      {
        localIntent.setPackage(paramString1);
        paramActivity.startActivity(localIntent);
        return;
        if ("com.amazon.venezia".equals(paramString1)) {
          localIntent.setData(Uri.parse("amzn://apps/android?p=" + paramString2));
        } else {
          localIntent.setData(Uri.parse("market://details?id=" + paramString2));
        }
      }
      return;
    }
    catch (Exception paramActivity) {}
  }
  
  public static void recoverMapBackup(Context paramContext, boolean paramBoolean)
  {
    try
    {
      Thread.sleep(200L);
      Object localObject = PrefUtil.getRecoverMapPath(paramContext);
      if ((localObject != null) && (((String)localObject).indexOf(";") > -1))
      {
        localObject = ((String)localObject).split(";");
        SaveMapView.recoveryMap(paramContext, localObject[0], localObject[1], paramBoolean);
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void reportGooglePlay()
  {
    SharedPreferences localSharedPreferences = PrefUtil.getPrefs(0);
    if ((!Boolean.valueOf(localSharedPreferences.getBoolean("googleplayreported", false)).booleanValue()) && (McInstallInfoUtil.isAppInstalled(MyApplication.getApplication(), "com.android.vending")))
    {
      Tracker.onEvent("googleplay_exist");
      localSharedPreferences.edit().putBoolean("googleplayreported", true).commit();
    }
  }
  
  protected static void requireInit()
  {
    if (mContext == null) {
      mContext = MyApplication.getApplication();
    }
  }
  
  public static void setContext(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static void setDownloadCount(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
  {
    new Thread(new ToolUtils.2(paramInt1, paramInt2, paramInt3, paramInt4, paramString, paramContext)).start();
  }
  
  public static void setSupportVersionTextView(List<McServerVersion> paramList, TextView paramTextView, int paramInt)
  {
    int j = 0;
    for (;;)
    {
      Object localObject;
      int i;
      try
      {
        localObject = new StringBuilder().append("mContext is null?");
        if (mContext != null) {
          break label283;
        }
        str = "true";
        LogManager.LogInfo("setSupportVersonTextView", str);
        paramTextView.setBackgroundColor(mContext.getResources().getColor(2131558788));
        McInstallInfoUtil.init(mContext);
        localObject = ConfigManager.getInstance(MyApplication.getmContext()).getVisualVerionName();
        str = "";
        Iterator localIterator = paramList.iterator();
        paramList = str;
        i = j;
        String[] arrayOfString;
        if (localIterator.hasNext())
        {
          arrayOfString = ((McServerVersion)localIterator.next()).getAttributeName().split(verSplit);
          if (arrayOfString.length - 1 < 0)
          {
            i = 0;
            str = arrayOfString[i];
            int k = arrayOfString.length;
            i = 0;
            paramList = str;
            if (i >= k) {
              continue;
            }
            if (!((String)localObject).startsWith(arrayOfString[i])) {
              break label291;
            }
            i = 1;
            paramList = str;
          }
        }
        else
        {
          if (paramInt != 0) {
            break label233;
          }
          if (!ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIsSupportPlugin()) {
            break label227;
          }
          if (i == 0) {
            continue;
          }
          paramTextView.setText((CharSequence)localObject);
          return;
        }
        i = arrayOfString.length - 1;
        continue;
        paramTextView.setText(paramList);
        return;
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
        return;
      }
      label227:
      paramTextView.setText(paramList);
      return;
      label233:
      if (paramInt == 1)
      {
        if (ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIsSupportTexture())
        {
          if (i != 0)
          {
            paramTextView.setText((CharSequence)localObject);
            return;
          }
          paramTextView.setText(paramList);
          return;
        }
        paramTextView.setText(paramList);
        return;
      }
      paramTextView.setText("");
      return;
      label283:
      String str = "false";
      continue;
      label291:
      i += 1;
    }
  }
  
  public static void showClubExpiredDlg(Activity paramActivity)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("alert", "clubres_guoqi_alert");
    Tracker.a(paramActivity, "club_alert", localHashMap);
    new ClubPrivilegeResourceTipDialog(paramActivity, new ToolUtils.4(paramActivity)).show();
  }
  
  @SuppressLint({"InflateParams"})
  public static void showResourcesGuidTip(Activity paramActivity)
  {
    if (PrefUtil.getResourceGuidTip())
    {
      ViewGroup localViewGroup = (ViewGroup)paramActivity.getWindow().getDecorView();
      paramActivity = LayoutInflater.from(paramActivity).inflate(2130903455, null);
      Button localButton = (Button)paramActivity.findViewById(2131625005);
      paramActivity.setOnTouchListener(new ToolUtils.5());
      localButton.setOnClickListener(new ToolUtils.6(paramActivity, localViewGroup));
      PrefUtil.setResourceGuidTip(false);
      localViewGroup.addView(paramActivity);
    }
  }
  
  public static void showSupportGameVersion(Map<String, String> paramMap, TextView paramTextView, int paramInt)
  {
    for (;;)
    {
      try
      {
        StringBuilder localStringBuilder = new StringBuilder().append("mContext is null?");
        if (mContext != null) {
          break label157;
        }
        str = "true";
        LogManager.LogInfo("setSupportVersionTextView", str);
        paramTextView.setBackgroundColor(mContext.getResources().getColor(2131558788));
        McInstallInfoUtil.init(mContext);
        if (paramInt == 0)
        {
          paramTextView.setText(getReallyGameVersion(paramMap, ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIdConfigConfig().getModSupportTag(), paramInt));
          return;
        }
        if (paramInt == 1)
        {
          paramTextView.setText(getReallyGameVersion(paramMap, ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIdConfigConfig().getTextureSupportTag(), paramInt));
          return;
        }
      }
      catch (Exception paramMap)
      {
        paramMap.printStackTrace();
        return;
      }
      if (paramInt == 2) {
        paramTextView.setText(getReallyGameVersion(paramMap, ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIdConfigConfig().getAddonSupportTag(), paramInt));
      }
      return;
      label157:
      String str = "false";
    }
  }
  
  public static void startGame(Integer paramInteger, Activity paramActivity)
  {
    PrefUtil.setWorldLocation(paramActivity, "");
    if (LauncherUtil.getPrefs(0).getBoolean("isEnableMemClean", true))
    {
      Intent localIntent = new Intent(paramActivity, MemoryCleanActivity.class);
      localIntent.putExtra("startType", paramInteger);
      paramActivity.startActivity(localIntent);
      return;
    }
    if (paramInteger.equals(Constant.startMcInternal))
    {
      startMcInternal(paramActivity);
      return;
    }
    startPlug(paramActivity);
  }
  
  public static void startMC(Activity paramActivity, boolean paramBoolean1, boolean paramBoolean2)
  {
    startMC(paramActivity, true, false, paramBoolean2);
  }
  
  public static void startMC(Activity paramActivity, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    for (;;)
    {
      int i;
      try
      {
        if (!checkMcpeInstalled(paramActivity)) {
          break label332;
        }
        Tracker.a(paramActivity, "start_mc_btn", "");
        isRequiredReport = true;
        i = getEnabledScripts().size();
        paramBoolean1 = getPrefs(0).getBoolean("zz_skin_enable", false);
        com.mcbox.pesdk.launcher.LauncherConstants.USER_ID = MyApplication.getApplication().getUserId();
        paramBoolean2 = ConfigManager.getInstance(MyApplication.getmContext()).getConfig().getIsSupportFloat();
        File localFile = new File(Constant.TEXTURE_RESOURCE_PACKS);
        boolean bool = localFile.exists();
        if (!bool) {
          break label333;
        }
        localObject = FileUtil.readFile(localFile);
        long l = localFile.length();
        if ((bool) && ((l <= 0L) || ((localObject != null) && (((String)localObject).equals("\n"))))) {
          localFile.delete();
        }
        if (!PrefUtil.getFloatingWindowStatue(MyApplication.getmContext()))
        {
          localObject = new HashMap();
          ((HashMap)localObject).put("param", "1");
          isRequiredReport = false;
          Tracker.a(MyApplication.getmContext(), "floatwin_succeed", (HashMap)localObject);
        }
        if (!paramBoolean2)
        {
          startMcWithCleanMem(paramActivity, Constant.startMcInternal, paramBoolean3);
          if (!isRequiredReport) {
            break label332;
          }
          paramActivity = new HashMap();
          paramActivity.put("param", "0");
          isRequiredReport = false;
          Tracker.a(MyApplication.getmContext(), "floatwin_succeed", paramActivity);
          return;
        }
        startMcWithCleanMem(paramActivity, Constant.startMcWithPlug, paramBoolean3);
        if (isRequiredReport)
        {
          localObject = new HashMap();
          ((HashMap)localObject).put("param", "2");
          isRequiredReport = false;
          Tracker.a(MyApplication.getmContext(), "floatwin_succeed", (HashMap)localObject);
        }
        if ((paramBoolean1) && (i > 0))
        {
          Tracker.b(paramActivity, "start_mc_skin_js");
          return;
        }
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
        return;
      }
      if (paramBoolean1)
      {
        Tracker.b(paramActivity, "start_mc_skin");
        return;
      }
      if (i > 0) {
        Tracker.b(paramActivity, "start_mc_js");
      }
      label332:
      return;
      label333:
      Object localObject = null;
    }
  }
  
  public static void startMcInternal(Activity paramActivity)
  {
    Intent localIntent = paramActivity.getPackageManager().getLaunchIntentForPackage("com.mojang.minecraftpe");
    if (localIntent != null) {
      paramActivity.startActivity(localIntent);
    }
  }
  
  public static void startMcWithCleanMem(Activity paramActivity, Integer paramInteger, boolean paramBoolean)
  {
    if (Constant.startMcWithPlug == paramInteger) {}
    for (boolean bool = cleanTextureAndPlugin(paramActivity);; bool = true)
    {
      if (bool)
      {
        PrefUtil.setWorldLocation(paramActivity, "");
        bool = LauncherUtil.getPrefs(0).getBoolean("isEnableMemClean", true);
        ResourceDao localResourceDao = new ResourceDao(paramActivity);
        if ((paramBoolean) || (localResourceDao.listAllClubPrivilegeDownload() == null) || (localResourceDao.listAllClubPrivilegeDownload().size() <= 0)) {
          break label92;
        }
        if (!ResourceActionHelper.a(paramActivity)) {}
      }
      else
      {
        return;
      }
      if (!MyApplication.getApplication().isUserClubMember())
      {
        showClubExpiredDlg(paramActivity);
        return;
      }
      label92:
      encryptResourceProcess(paramActivity, bool, paramInteger);
      return;
    }
  }
  
  public static void startMcWithFloatWindow(Activity paramActivity)
  {
    if (!LauncherManager.getInstance().isPluginOK(paramActivity)) {}
    try
    {
      LauncherManager.getInstance().reloadPlugin(paramActivity);
      localLauncherFunc = LauncherManager.getInstance().getLauncherFunc();
      if (localLauncherFunc == null) {}
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          LauncherFunc localLauncherFunc;
          localLauncherFunc.startMcWithFloatWindow(paramActivity);
          return;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          Tracker.c(paramActivity, "startMcWithFloatWindow2 error: MSG=" + localException2.getMessage() + "\nToString=" + localException2.toString());
          throw new RuntimeException("startMcWithFloatWindow error:", localException2);
        }
        localException1 = localException1;
        localException1.printStackTrace();
      }
    }
  }
  
  public static void startMcWithSpecifyMap(Activity paramActivity, String paramString)
  {
    File localFile;
    if (!TextUtils.isEmpty(paramString))
    {
      localFile = new File(paramString, "level.dat");
      if (!localFile.exists())
      {
        startMC(paramActivity, false, false);
        return;
      }
    }
    try
    {
      WorldMapHandler.updateLevelTime(paramActivity, paramString, LevelDataConverter.read(localFile), new ToolUtils.1());
      startMC(paramActivity, false, false);
      return;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static void startPlug(Activity paramActivity)
  {
    checkLoadResourceMd5(paramActivity);
    if (WorldMapHandler.isMcRunning(paramActivity)) {
      McInstallInfoUtil.killMc(paramActivity);
    }
    PrefUtil.setModeSafe(paramActivity, false);
    startMcWithFloatWindow(paramActivity);
  }
  
  public static void updatePraiseInfo(Context paramContext, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3)
  {
    int i = 0;
    int k = 3;
    String str = PrefUtil.getPraiseInfo(paramContext);
    String[] arrayOfString = null;
    if (!StringUtils.isNull(str)) {
      arrayOfString = str.split("_");
    }
    str = "" + McInstallInfoUtil.getVersionCode(paramContext, paramContext.getPackageName());
    long l;
    if ((arrayOfString != null) && (arrayOfString.length == 4) && (arrayOfString[0].equals(str)))
    {
      l = Long.parseLong(arrayOfString[3]);
      i = Integer.parseInt(arrayOfString[2]);
      if (!paramBoolean3.booleanValue()) {
        break label234;
      }
      l = System.currentTimeMillis();
      i += 1;
    }
    label164:
    label227:
    label234:
    for (;;)
    {
      int j = i;
      if (i >= 3) {
        j = 3;
      }
      paramBoolean3 = new StringBuilder().append(str).append("_");
      if (paramBoolean2.booleanValue())
      {
        paramBoolean2 = "1";
        paramBoolean2 = paramBoolean3.append(paramBoolean2).append("_");
        if (!paramBoolean1.booleanValue()) {
          break label227;
        }
      }
      for (i = k;; i = j)
      {
        PrefUtil.setPraiseInfo(paramContext, i + "_" + l);
        return;
        l = System.currentTimeMillis();
        break;
        paramBoolean2 = "0";
        break label164;
      }
    }
  }
}
