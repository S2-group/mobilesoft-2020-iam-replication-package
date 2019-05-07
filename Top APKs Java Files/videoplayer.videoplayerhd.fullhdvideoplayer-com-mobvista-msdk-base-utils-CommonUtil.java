package com.mobvista.msdk.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.mobvista.msdk.MobVistaConstans;
import com.mobvista.msdk.base.controller.MVSDKContext;
import com.mobvista.msdk.base.db.CommonSDKDBHelper;
import com.mobvista.msdk.base.db.SCDao;
import com.mobvista.msdk.base.entity.CampaignEx;
import com.mobvista.msdk.setting.Setting;
import com.mobvista.msdk.setting.SettingManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonUtil
{
  static List<String> a;
  private static String b = "[一-龥]";
  private static Pattern c = Pattern.compile("[一-龥]");
  private static int d = 1;
  private static boolean e = true;
  
  public CommonUtil() {}
  
  public static String GetMD5Sign(HashMap<String, String> paramHashMap, String paramString)
  {
    Object localObject = new ArrayList(paramHashMap.entrySet());
    Collections.sort((List)localObject, new CommonUtil.1());
    paramHashMap = new StringBuilder();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      paramHashMap.append((String)localEntry.getKey()).append("=").append((String)localEntry.getValue()).append("&");
    }
    if (paramHashMap.toString().endsWith("&")) {
      paramHashMap.deleteCharAt(paramHashMap.length() - 1);
    }
    paramHashMap.append(paramString);
    paramHashMap = CommonMD5.getMD5(paramHashMap.toString());
    CommonLogUtil.d("CommonUtils", "MD5Sign=" + paramHashMap);
    return paramHashMap;
  }
  
  public static Drawable bitMapToDrawble(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      return new BitmapDrawable(paramBitmap);
    }
    return null;
  }
  
  public static void cacheSCCampaigns(List<CampaignEx> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      CampaignEx localCampaignEx = (CampaignEx)paramList.get(i);
      if ((!isAppInstalled(MVSDKContext.getInstance().getContext(), localCampaignEx.getPackageName())) && (localCampaignEx != null) && (localCampaignEx.getOfferType() == 99)) {
        localArrayList.add(localCampaignEx);
      }
      i += 1;
    }
    SCDao.getInstance(CommonSDKDBHelper.getInstance(MVSDKContext.getInstance().getContext())).insertOrUpdate(localArrayList);
  }
  
  public static void chmod(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = "chmod " + paramString1 + " " + paramString2;
      Runtime.getRuntime().exec(paramString1);
      return;
    }
    catch (IOException paramString1)
    {
      CommonLogUtil.e("CommonUtils", "chmod", paramString1);
    }
  }
  
  public static String countTimeIntervalText(long paramLong)
  {
    paramLong = System.currentTimeMillis() - paramLong;
    if (paramLong < 900000L) {
      return "刚刚";
    }
    if (paramLong < 3600000L) {
      return "一小时内";
    }
    if (paramLong < 86400000L) {
      return (int)(paramLong / 3600000L) + "小时前";
    }
    return DateFormat.format("MM-dd kk:mm", System.currentTimeMillis()).toString();
  }
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static double formatPointTwo(Double paramDouble)
  {
    try
    {
      paramDouble = new DecimalFormat("#.00").format(paramDouble);
      if (StringUtils.notNull(paramDouble))
      {
        double d1 = Double.parseDouble(paramDouble);
        return d1;
      }
    }
    catch (Exception paramDouble)
    {
      paramDouble.printStackTrace();
    }
    return 0.0D;
  }
  
  public static int getAppInstallNums(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return -1;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        if ((paramContext != null) && (paramContext.size() > 0))
        {
          int i = paramContext.size();
          return i;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return -1;
  }
  
  public static String getApplicationMetaData(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get(paramString);
      if ((paramContext instanceof String)) {
        return paramContext.toString();
      }
      if ((paramContext instanceof Integer)) {
        return ((Integer)paramContext).intValue();
      }
      if ((paramContext instanceof Boolean))
      {
        paramContext = ((Boolean)paramContext).booleanValue();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      CommonLogUtil.w("CommonUtils", paramContext);
    }
    return "";
  }
  
  public static long getAvailableExternalSize()
  {
    if (hasSDCard())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l;
    }
    return -1L;
  }
  
  public static long getAvailableInternalSize()
  {
    if (hasSDCard())
    {
      StatFs localStatFs = new StatFs(Environment.getRootDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l;
    }
    return -1L;
  }
  
  public static float getBattery(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    paramContext.getIntExtra("status", 0);
    paramContext.getIntExtra("health", 1);
    paramContext.getBooleanExtra("present", false);
    int i = paramContext.getIntExtra("level", 0);
    int j = paramContext.getIntExtra("scale", 0);
    paramContext.getIntExtra("plugged", 0);
    paramContext.getIntExtra("voltage", 0);
    paramContext.getIntExtra("temperature", 0);
    paramContext.getStringExtra("technology");
    return i / j;
  }
  
  public static int getDaoHangHeight(Context paramContext)
  {
    try
    {
      if (paramContext.getResources().getIdentifier("config_showNavigationBar", "bool", "android") != 0)
      {
        int i = paramContext.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        i = paramContext.getResources().getDimensionPixelSize(i);
        return i;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static float getDensity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static String getExOrInsIds(JSONArray paramJSONArray)
  {
    if (paramJSONArray == null) {
      return "";
    }
    Object localObject = MVSDKContext.getInstance().getAppId();
    Setting localSetting = SettingManager.getInstance().getSettingByAppId((String)localObject);
    localObject = localSetting;
    if (localSetting == null) {
      localObject = SettingManager.getInstance().getDefaultSetting();
    }
    int j = ((Setting)localObject).getPcrn();
    if (paramJSONArray.length() > j)
    {
      localObject = new JSONArray();
      int i = 0;
      for (;;)
      {
        if (i < j) {
          try
          {
            ((JSONArray)localObject).put(paramJSONArray.get(i));
            i += 1;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }
      return ((JSONArray)localObject).toString();
    }
    return paramJSONArray.toString();
  }
  
  public static long getFileSize(File paramFile)
  {
    if (paramFile.exists()) {
      return new FileInputStream(paramFile).available();
    }
    paramFile.createNewFile();
    CommonLogUtil.e("获取文件大小", "文件不存在!");
    return 0L;
  }
  
  public static String getFixedNumber(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramInt <= 0) || (paramString.length() < paramInt)) {}
    Matcher localMatcher;
    do
    {
      return null;
      CommonLogUtil.d("CommonUtils", "getFixedNumber, str is : " + paramString);
      localMatcher = Pattern.compile("\\d{" + paramInt + "}").matcher(paramString);
    } while (!localMatcher.find());
    paramInt = localMatcher.start();
    return paramString.substring(paramInt, localMatcher.group().length() + paramInt);
  }
  
  public static String getHostIp()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || (!InetAddressUtils.isIPv4Address(localInetAddress.getHostAddress())) || (localInetAddress.getHostAddress().toString().equals("null")) || (localInetAddress.getHostAddress() == null));
      Object localObject = localInetAddress.getHostAddress().toString().trim();
      return localObject;
    }
    catch (Exception localException)
    {
      CommonLogUtil.e("WifiPreference IpAddress", localException.toString());
    }
    return "";
  }
  
  public static int getLengthWithoutSpace(CharSequence paramCharSequence)
  {
    int j = 0;
    int m = paramCharSequence.length();
    int i = 0;
    while (i < m)
    {
      int k = j;
      if (paramCharSequence.charAt(i) != ' ') {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public static String getLocalMacAddress()
  {
    localObject1 = null;
    Object localObject2 = null;
    try
    {
      FileInputStream localFileInputStream;
      byte[] arrayOfByte;
      int i;
      if (new File("sys/class/net/wlan0/address").exists())
      {
        localFileInputStream = new FileInputStream("sys/class/net/wlan0/address");
        arrayOfByte = new byte[' '];
        i = localFileInputStream.read(arrayOfByte);
        localObject1 = localObject2;
        if (i > 0) {
          localObject1 = new String(arrayOfByte, 0, i, "utf-8");
        }
        localObject2 = localObject1;
      }
      Object localObject3;
      return localObject1;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          localFileInputStream.close();
          if (localObject1 != null)
          {
            localObject2 = localObject1;
            if (((String)localObject1).length() != 0) {
              continue;
            }
          }
          localObject2 = localObject1;
          localFileInputStream = new FileInputStream("sys/class/net/eth0/address");
          localObject2 = localObject1;
          arrayOfByte = new byte[' '];
          localObject2 = localObject1;
          i = localFileInputStream.read(arrayOfByte);
          localObject2 = localObject1;
          if (i > 0)
          {
            localObject2 = localObject1;
            localObject1 = new String(arrayOfByte, 0, i, "utf-8");
            localObject2 = localObject1;
          }
        }
        catch (Exception localException3)
        {
          localObject1 = localObject3;
          localObject3 = localException3;
          continue;
          continue;
          localObject1 = "";
        }
        try
        {
          localFileInputStream.close();
          localObject1 = localObject2;
          if (localObject1 == null) {
            continue;
          }
        }
        catch (Exception localException4)
        {
          localObject1 = localObject3;
          localObject3 = localException4;
          continue;
        }
        try
        {
          if (((String)localObject1).length() == 0) {
            continue;
          }
          localObject2 = localObject1;
          if (((String)localObject1).endsWith("\n")) {
            localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
          }
          localObject1 = localObject2;
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            return localObject1;
          }
          return "";
        }
        catch (Exception localException2)
        {
          continue;
        }
        localException1 = localException1;
        localObject1 = null;
        CommonLogUtil.w("CommonUtils", "Exception", localException1);
        localObject3 = localObject1;
      }
    }
  }
  
  public static int getNextId()
  {
    int i = d;
    d = i + 1;
    return i;
  }
  
  public static String getPhoneImsi(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getApplicationContext().getSystemService("phone")).getSubscriberId();
    CommonLogUtil.d("CommonUtils", "IMSI is : " + str);
    paramContext = str;
    if (TextUtils.isEmpty(str)) {
      paramContext = "";
    }
    return paramContext;
  }
  
  public static String getPhoneNum(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getApplicationContext().getSystemService("phone")).getLine1Number();
    paramContext = str;
    if (TextUtils.isEmpty(str)) {
      paramContext = "";
    }
    return paramContext;
  }
  
  public static String getPhoneType()
  {
    String str = Build.MODEL;
    CommonLogUtil.d("CommonUtils", "phoneType is : " + str);
    return str;
  }
  
  public static int getRepeatTimes(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return 0;
    }
    paramString = paramString.toCharArray();
    HashMap localHashMap = new HashMap();
    int i = 0;
    int j = 0;
    if (i < paramString.length)
    {
      char c1 = paramString[i];
      Integer localInteger = (Integer)localHashMap.get(Character.valueOf(c1));
      if (localInteger == null) {}
      for (int k = 0;; k = localInteger.intValue())
      {
        int m = k + 1;
        localHashMap.put(Character.valueOf(c1), Integer.valueOf(m));
        k = j;
        if (j < m) {
          k = m;
        }
        i += 1;
        j = k;
        break;
      }
    }
    return j;
  }
  
  public static long getSDCardAvailableSize()
  {
    if (haveSDCard())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l;
    }
    return -1L;
  }
  
  public static Bitmap getScaleBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Object localObject = new Matrix();
    ((Matrix)localObject).postScale(paramInt1 / f1, paramInt2 / f2);
    localObject = Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, (Matrix)localObject, true);
    paramBitmap.recycle();
    return localObject;
  }
  
  public static float getScaledDensity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().scaledDensity;
  }
  
  public static int getScreenAllHeight(Activity paramActivity)
  {
    if (paramActivity == null) {
      return 0;
    }
    try
    {
      paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(paramActivity, new Object[] { localDisplayMetrics });
      int i = localDisplayMetrics.heightPixels;
      return i;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return 0;
  }
  
  public static int getScreenAllWidth(Activity paramActivity)
  {
    if (paramActivity == null) {
      return 0;
    }
    try
    {
      paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(paramActivity, new Object[] { localDisplayMetrics });
      int i = localDisplayMetrics.widthPixels;
      return i;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return 0;
  }
  
  public static int getScreenContentHeight(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().heightPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int getScreenContentWidth(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().widthPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static float getScreenHeight(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }
  
  public static float getScreenWidth(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
      i = paramContext.getResources().getDimensionPixelSize(i);
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static long getSystemAvailableSize()
  {
    StatFs localStatFs = new StatFs(Environment.getRootDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static String getTime24Hours()
  {
    return new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date(System.currentTimeMillis()));
  }
  
  public static int getTitleBarHeight(Activity paramActivity)
  {
    return paramActivity.getWindow().findViewById(16908290).getTop() - getStatusBarHeight(paramActivity);
  }
  
  public static boolean hasFroyo()
  {
    return Build.VERSION.SDK_INT >= 8;
  }
  
  public static boolean hasGingerbread()
  {
    return Build.VERSION.SDK_INT >= 9;
  }
  
  public static boolean hasHoneycomb()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean hasHoneycombMR1()
  {
    return Build.VERSION.SDK_INT >= 12;
  }
  
  public static boolean hasJellyBean()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean hasSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean haveSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static void hideBottomUIMenu(View paramView)
  {
    if (paramView == null) {}
    for (;;)
    {
      return;
      try
      {
        if (Build.VERSION.SDK_INT >= 11)
        {
          paramView.setSystemUiVisibility(4102);
          return;
        }
      }
      catch (Throwable paramView)
      {
        paramView.printStackTrace();
      }
    }
  }
  
  public static void hideInputMethod(Activity paramActivity)
  {
    hideInputMethod(paramActivity, paramActivity.getCurrentFocus());
  }
  
  public static void hideInputMethod(Context paramContext, View paramView)
  {
    if ((paramContext == null) || (paramView == null)) {}
    do
    {
      return;
      paramContext = (InputMethodManager)paramContext.getSystemService("input_method");
    } while (paramContext == null);
    paramContext.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void initFbInstalled(Context paramContext)
  {
    try
    {
      if ((isAppInstalled(paramContext, "com.instagram.android")) || (isAppInstalled(paramContext, "com.facebook.katana")))
      {
        e = true;
        return;
      }
      e = false;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean isAlphaBetaNumbericString(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile("^[a-zA-Z0-9]+$").matcher(paramString).find();
  }
  
  public static boolean isAlphaBetaString(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile("^[a-zA-Z]+$").matcher(paramString).find();
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    if (a == null) {
      a = new ArrayList();
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager();
        if ((MVSDKContext.packageInfoList != null) && (MVSDKContext.packageInfoList.size() != 0)) {
          break label172;
        }
        MVSDKContext.packageInfoList = new ArrayList();
        paramContext = paramContext.getInstalledPackages(0);
        i = 0;
        if (i >= paramContext.size()) {
          break label172;
        }
        MVSDKContext.packageInfoList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
        continue;
        if (i < MVSDKContext.packageInfoList.size())
        {
          paramContext = (String)MVSDKContext.packageInfoList.get(i);
          a.add(paramContext);
          i += 1;
          continue;
        }
        boolean bool = a.contains(paramString);
        return bool;
      }
      catch (Throwable paramContext)
      {
        if (MobVistaConstans.DEBUG) {
          paramContext.printStackTrace();
        }
        return false;
      }
      return a.contains(paramString);
      label172:
      int i = 0;
    }
  }
  
  public static boolean isChineseByREG(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return Pattern.compile("[\\u4E00-\\u9FBF]+").matcher(paramString.trim()).find();
  }
  
  public static boolean isContainsChinese(String paramString)
  {
    return c.matcher(paramString).find();
  }
  
  public static boolean isContinuousNum(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    if (!isNumbericString(paramString)) {
      return true;
    }
    int k = paramString.length();
    int i = 0;
    while (i < k - 1)
    {
      int m = paramString.charAt(i);
      int j = (char)(m + 1);
      if (m == 57) {
        j = 48;
      }
      if (paramString.charAt(i + 1) != j) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean isContinuousStr(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    int k = paramString.length();
    int i = 0;
    for (;;)
    {
      if (i >= k) {
        break label64;
      }
      int m = paramString.charAt(i);
      int j = (char)(m + 1);
      if (i + 1 < k) {
        j = paramString.charAt(i + 1);
      }
      if (j != m + 1) {
        break;
      }
      i += 1;
    }
    label64:
    return true;
  }
  
  public static boolean isContinuousWord(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    if (!isAlphaBetaString(paramString)) {
      return true;
    }
    int k = paramString.length();
    paramString = paramString.toLowerCase();
    int i = 0;
    while (i < k - 1)
    {
      int m = paramString.charAt(i);
      int j = (char)(m + 1);
      if (m == 122) {
        j = 97;
      }
      if (paramString.charAt(i + 1) != j) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean isFBInstalled(Context paramContext)
  {
    return e;
  }
  
  public static boolean isGranted(String paramString, Context paramContext)
  {
    try
    {
      if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0)
      {
        CommonLogUtil.d("CommonUtils", "Permission " + paramString + " is granted");
        return true;
      }
      CommonLogUtil.d("CommonUtils", "Permission " + paramString + " is NOT granted");
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isMMYY(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        bool1 = bool2;
        if (paramString.length() == 4)
        {
          int i = Integer.parseInt(paramString.substring(0, 2));
          bool1 = bool2;
          if (i > 0)
          {
            bool1 = bool2;
            if (i < 13) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    catch (Exception paramString)
    {
      CommonLogUtil.e("CommonUtils", "Exception", paramString);
    }
    return false;
  }
  
  public static boolean isNetConnect(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static <T extends String> boolean isNotNullOrEmpty(T paramT)
  {
    return (paramT != null) && (paramT.length() > 0);
  }
  
  public static boolean isNotNullOrEmpty(List paramList)
  {
    return !isNullOrEmpty(paramList);
  }
  
  public static <T> boolean isNotNullOrEmpty(T[] paramArrayOfT)
  {
    return !isNullOrEmpty(paramArrayOfT);
  }
  
  public static <T extends String> boolean isNullOrEmpty(T paramT)
  {
    return (paramT == null) || (paramT.length() == 0);
  }
  
  public static boolean isNullOrEmpty(List paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  public static <T> boolean isNullOrEmpty(T[] paramArrayOfT)
  {
    return (paramArrayOfT == null) || (paramArrayOfT.length == 0);
  }
  
  public static boolean isNumbericString(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile("^[0-9]+$").matcher(paramString).find();
  }
  
  public static boolean isPhoneNum(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.matches("1[0-9]{10}")) && (!isRepeatedStr(paramString)) && (!isContinuousNum(paramString));
  }
  
  public static boolean isRealDate(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.length() != paramInt + 4)) {}
    do
    {
      do
      {
        int i;
        do
        {
          int j;
          do
          {
            do
            {
              return false;
            } while (!paramString.matches("[0-9]+"));
            i = Integer.parseInt(paramString.substring(0, paramInt));
            j = Integer.parseInt(paramString.substring(paramInt, paramInt + 2));
            paramInt = Integer.parseInt(paramString.substring(paramInt + 2, paramInt + 4));
          } while ((i <= 0) || (j <= 0) || (j > 12) || (paramInt <= 0) || (paramInt > 31));
          switch (j)
          {
          case 3: 
          case 5: 
          case 7: 
          case 8: 
          case 10: 
          default: 
            return true;
          }
        } while (paramInt > 30);
        return true;
        if (((i % 4 != 0) || (i % 100 == 0)) && (i % 400 != 0)) {
          break;
        }
      } while (paramInt > 29);
      return true;
    } while (paramInt > 28);
    return true;
  }
  
  public static boolean isRepeatedStr(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    int j;
    do
    {
      return false;
      j = paramString.length();
    } while (j <= 1);
    int k = paramString.charAt(0);
    int i = 1;
    for (;;)
    {
      if (i >= j) {
        break label48;
      }
      if (k != paramString.charAt(i)) {
        break;
      }
      i += 1;
    }
    label48:
    return true;
  }
  
  public static boolean isRetargetOffer(CampaignEx paramCampaignEx)
  {
    if (paramCampaignEx != null) {
      try
      {
        int i = paramCampaignEx.getRetarget_offer();
        return i == 1;
      }
      catch (Exception paramCampaignEx)
      {
        paramCampaignEx.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isWifiConnect(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = "wifi".equals(paramContext.getTypeName().toLowerCase(Locale.US));
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void measureView(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null) {
      localLayoutParams1 = new ViewGroup.LayoutParams(-1, -2);
    }
    int j = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams1.width);
    int i = localLayoutParams1.height;
    if (i > 0) {}
    for (i = View.MeasureSpec.makeMeasureSpec(i, 1073741824);; i = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(j, i);
      return;
    }
  }
  
  public static double parseStr2Double(String paramString)
  {
    double d1 = 0.0D;
    try
    {
      if (!TextUtils.isEmpty(paramString)) {
        d1 = Double.parseDouble(paramString);
      }
      return d1;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0.0D;
  }
  
  public static boolean patternMatcher(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return false;
    }
    return Pattern.compile(paramString1).matcher(paramString2).find();
  }
  
  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int px2sp(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().scaledDensity + 0.5F);
  }
  
  public static byte[] read2Byte(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    localByteArrayOutputStream.close();
    paramInputStream.close();
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static String secFormatTime(int paramInt)
  {
    if (paramInt <= 0) {
      return "00:00";
    }
    try
    {
      int j = paramInt / 60;
      if (j < 60) {
        return unitFormat(j) + ":" + unitFormat(paramInt % 60);
      }
      int i = j / 60;
      if (i > 99) {
        return "99:59:59";
      }
      j %= 60;
      String str = unitFormat(i) + ":" + unitFormat(j) + ":" + unitFormat(paramInt - i * 3600 - j * 60);
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "";
  }
  
  public static SpannableString setDigitalColor(String paramString, int paramInt)
  {
    if (paramString == null)
    {
      paramString = null;
      return paramString;
    }
    SpannableString localSpannableString = new SpannableString(paramString);
    Matcher localMatcher = Pattern.compile("(-?[0-9]+)(,[0-9]+)*(\\.[0-9]+)?").matcher(paramString);
    for (;;)
    {
      paramString = localSpannableString;
      if (!localMatcher.find()) {
        break;
      }
      int i = localMatcher.start();
      int j = localMatcher.group().length();
      localSpannableString.setSpan(new ForegroundColorSpan(paramInt), i, j + i, 34);
    }
  }
  
  public static void showInputMethod(Context paramContext, View paramView)
  {
    if ((paramContext == null) || (paramView == null)) {}
    do
    {
      return;
      paramContext = (InputMethodManager)paramContext.getSystemService("input_method");
    } while (paramContext == null);
    paramContext.showSoftInput(paramView, 0);
  }
  
  public static int sp2px(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().scaledDensity * paramFloat + 0.5F);
  }
  
  public static String unitFormat(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 10)) {}
    try
    {
      return "0" + Integer.toString(paramInt);
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return String.valueOf(paramInt);
    return "";
  }
  
  public static boolean vertifyGetOrderSign(HashMap<String, String> paramHashMap, String paramString)
  {
    String str = (String)paramHashMap.get("sign");
    paramHashMap.remove("sign");
    paramHashMap = GetMD5Sign(paramHashMap, paramString);
    CommonLogUtil.d("CommonUtils", "sign=" + paramHashMap + "order.sign=" + str);
    return paramHashMap.equals(str);
  }
  
  public static boolean vertifyGetOrderSign(JSONObject paramJSONObject, String paramString)
  {
    Iterator localIterator = paramJSONObject.keys();
    HashMap localHashMap = new HashMap();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, paramJSONObject.getString(str));
    }
    return vertifyGetOrderSign(localHashMap, paramString);
  }
}
