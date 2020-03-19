package com.uc.base.system;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import com.UCMobile.model.StatsModel;
import com.UCMobile.model.w;
import com.uc.base.util.assistant.e;
import com.uc.base.util.assistant.i;
import com.uc.base.util.shellnetwork.BrowserURLUtil;
import com.uc.base.util.temp.l;
import com.uc.e.a.c.g;
import com.uc.e.a.c.h;
import com.uc.webview.export.annotations.Jni;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemHelper
{
  private static final String TAG = SystemHelper.class.getSimpleName();
  private static SystemHelper hxw = null;
  private static Boolean hxx;
  static final List<String> hxy = Arrays.asList(new String[] { "android.permission.DELETE_PACKAGES", "android.permission.READ_PHONE_STATE", "android.permission.SEND_SMS", "android.permission.CALL_PHONE", "android.permission.READ_CONTACTS", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.GET_ACCOUNTS", "android.permission.GET_TASKS", "android.permission.INJECT_EVENTS", "android.permission.KILL_BACKGROUND_PROCESSES", "com.android.browser.permission.READ_HISTORY_BOOKMARKS" });
  static final List<String> hxz = Arrays.asList(new String[] { "android.net.conn.CONNECTIVITY_CHANGE", "android.intent.action.USER_PRESENT", "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_REPLACED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.BOOT_COMPLETED" });
  
  private SystemHelper() {}
  
  public static boolean IsExternalAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static String base64m9DecodeStr(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    try
    {
      paramString = Base64.decode(paramString, 2);
      if (paramString == null) {
        return "";
      }
      d.aGJ();
      paramString = d.aV(paramString);
      if (paramString == null) {
        return "";
      }
      paramString = new String(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      e.f(paramString);
    }
    return "";
  }
  
  public static Bitmap createBitmap(byte[] paramArrayOfByte)
  {
    return com.uc.base.image.c.createBitmap(paramArrayOfByte);
  }
  
  private static void enableAirPlaneBySys(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.settings.AIRPLANE_MODE_SETTINGS"));
  }
  
  public static boolean enableAirPlaneMode(Context paramContext, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      enableAirPlaneBySys(paramContext);
    }
    while (paramContext == null) {
      return false;
    }
    try
    {
      ContentResolver localContentResolver = paramContext.getContentResolver();
      if (paramBoolean) {}
      for (String str = "1";; str = "0")
      {
        Settings.System.putString(localContentResolver, "airplane_mode_on", str);
        paramContext.sendBroadcast(new Intent("android.intent.action.AIRPLANE_MODE"));
        return true;
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      enableAirPlaneBySys(paramContext);
    }
  }
  
  public static void enableMobileConnection(Context paramContext, final boolean paramBoolean)
  {
    if (paramContext != null) {
      com.uc.e.a.f.a.execute(new Runnable()
      {
        public final void run()
        {
          try
          {
            if (Build.VERSION.SDK_INT >= 20)
            {
              localObject = new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity");
              Intent localIntent2 = new Intent();
              localIntent2.setComponent((ComponentName)localObject);
              this.val$context.startActivity(localIntent2);
              return;
            }
            Object localObject = (ConnectivityManager)this.val$context.getSystemService("connectivity");
            ConnectivityManager.class.getMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE }).invoke(localObject, new Object[] { Boolean.valueOf(paramBoolean) });
            return;
          }
          catch (Throwable localThrowable)
          {
            try
            {
              Intent localIntent1 = new Intent("android.settings.WIRELESS_SETTINGS");
              this.val$context.startActivity(localIntent1);
              return;
            }
            catch (Exception localException)
            {
              e.f(localException);
            }
          }
        }
      });
    }
  }
  
  public static void enableWIFI(Context paramContext, final boolean paramBoolean)
  {
    if (paramContext != null) {
      com.uc.e.a.f.a.execute(new Runnable()
      {
        public final void run()
        {
          ((WifiManager)this.val$context.getSystemService("wifi")).setWifiEnabled(paramBoolean);
        }
      });
    }
  }
  
  public static String getApnProxy()
  {
    String str2 = a.QX();
    String str3 = String.valueOf(a.eE());
    String str1;
    if (com.uc.e.a.i.b.oe(str2)) {
      str1 = "";
    }
    do
    {
      return str1;
      str1 = str2;
    } while (com.uc.e.a.i.b.oe(str3));
    return str2 + ":" + str3;
  }
  
  private String getAppBroadcasts(PackageInfo paramPackageInfo, Map<String, List<String>> paramMap)
  {
    if ((paramPackageInfo == null) || (paramPackageInfo.packageName == null) || (paramPackageInfo.packageName.length() == 0)) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = hxz.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int i = hxz.indexOf(str);
      if (((List)paramMap.get(str)).indexOf(paramPackageInfo.packageName) >= 0) {
        localStringBuffer.append(String.valueOf(i) + ",");
      }
    }
    if ((localStringBuffer.length() != 0) && (localStringBuffer.charAt(localStringBuffer.length() - 1) == ',')) {
      localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    }
    new StringBuilder("getAppBroadcasts = ").append(localStringBuffer.toString());
    return localStringBuffer.toString();
  }
  
  private String getAppPermissions(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo == null) || (paramPackageInfo.requestedPermissions == null) || (paramPackageInfo.requestedPermissions.length == 0)) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    paramPackageInfo = paramPackageInfo.requestedPermissions;
    int j = paramPackageInfo.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramPackageInfo[i];
      int k = hxy.indexOf(localObject);
      if (k >= 0) {
        localStringBuffer.append(String.valueOf(k) + ",");
      }
      i += 1;
    }
    if ((localStringBuffer.length() != 0) && (localStringBuffer.charAt(localStringBuffer.length() - 1) == ',')) {
      localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    }
    new StringBuilder("getAppPermissions = ").append(localStringBuffer.toString());
    return localStringBuffer.toString();
  }
  
  private String getAppSignaturesMD5(PackageInfo paramPackageInfo)
  {
    int k = 0;
    for (;;)
    {
      int i;
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        int m = paramPackageInfo.signatures.length;
        String[] arrayOfString = new String[m];
        i = 0;
        if (i < m)
        {
          if (paramPackageInfo.signatures[i] != null)
          {
            ((MessageDigest)localObject).update(paramPackageInfo.signatures[i].toByteArray());
            byte[] arrayOfByte = ((MessageDigest)localObject).digest();
            StringBuffer localStringBuffer = new StringBuffer();
            int j = 0;
            if (j < arrayOfByte.length)
            {
              localStringBuffer.append(Integer.toHexString(arrayOfByte[j] & 0xFF | 0xFF00).substring(6));
              j += 1;
              continue;
            }
            arrayOfString[i] = localStringBuffer.toString();
            arrayOfString[i].toLowerCase(Locale.ENGLISH);
          }
        }
        else
        {
          if (m > 1)
          {
            Arrays.sort(arrayOfString);
            paramPackageInfo = new StringBuffer();
            i = 0;
            if (i < m)
            {
              paramPackageInfo.append(arrayOfString[i]);
              i += 1;
              continue;
            }
            ((MessageDigest)localObject).update(paramPackageInfo.toString().getBytes());
            paramPackageInfo = ((MessageDigest)localObject).digest();
            localObject = new StringBuffer();
            i = k;
            if (i < paramPackageInfo.length)
            {
              ((StringBuffer)localObject).append(Integer.toHexString(paramPackageInfo[i] & 0xFF | 0xFF00).substring(6));
              i += 1;
              continue;
            }
            return ((StringBuffer)localObject).toString().toLowerCase(Locale.ENGLISH);
          }
          if (m == 1)
          {
            paramPackageInfo = arrayOfString[0];
            return paramPackageInfo;
          }
          return "";
        }
      }
      catch (Exception paramPackageInfo)
      {
        e.f(paramPackageInfo);
        return "";
      }
      i += 1;
    }
  }
  
  public static long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return Math.round(localStatFs.getAvailableBlocks() * l / 1048576.0D);
  }
  
  public static int getCPUCoreCount()
  {
    return g.Rs();
  }
  
  public static String getCpuArch()
  {
    return com.uc.base.util.f.c.getCpuArch();
  }
  
  public static String getCpuInstruction()
  {
    return "thumb";
  }
  
  public static long getCurThreadID()
  {
    return Process.myTid();
  }
  
  public static long getFileSize(String paramString)
  {
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isFile())) {
      return paramString.length();
    }
    return 0L;
  }
  
  public static SystemHelper getInstance()
  {
    if (hxw == null) {
      hxw = new SystemHelper();
    }
    return hxw;
  }
  
  public static int getLauncherAppsCount()
  {
    return l.getLauncherAppsCount();
  }
  
  public static List<String> getLaunchers(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager();
        Object localObject = new Intent("android.intent.action.MAIN");
        ((Intent)localObject).addCategory("android.intent.category.HOME");
        localObject = paramContext.queryIntentActivities((Intent)localObject, 0);
        if (((List)localObject).size() != 0)
        {
          paramContext = new ArrayList();
          int j = ((List)localObject).size();
          int i = 0;
          if (i < j)
          {
            paramContext.add(((ResolveInfo)((List)localObject).get(i)).activityInfo.packageName);
            i += 1;
            continue;
          }
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }
  
  public static long getSpace(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 1)
    {
      if (!IsExternalAvailable()) {
        return 0L;
      }
      localStatFs = new StatFs(com.uc.e.a.c.b.getExternalStorageDirectory().getPath());
      l2 = localStatFs.getBlockSize();
      if (paramInt2 == 1) {}
      for (l1 = localStatFs.getAvailableBlocks();; l1 = localStatFs.getBlockCount()) {
        return l1 * l2;
      }
    }
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l2 = localStatFs.getBlockSize();
    if (paramInt2 == 1) {}
    for (long l1 = localStatFs.getAvailableBlocks();; l1 = localStatFs.getBlockCount()) {
      return l1 * l2;
    }
  }
  
  private static Map<String, List<String>> getSysReceiverInfoMap(PackageManager paramPackageManager)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = hxz.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramPackageManager.queryBroadcastReceivers(new Intent(str), 0);
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        new StringBuilder("info:").append(localResolveInfo);
        localArrayList.add(localResolveInfo.activityInfo.applicationInfo.packageName);
      }
      localHashMap.put(str, localArrayList);
    }
    return localHashMap;
  }
  
  public static String getUCApkPath()
  {
    Context localContext = com.uc.base.system.d.b.mContext;
    String str = "";
    if (localContext != null) {
      str = localContext.getApplicationInfo().sourceDir;
    }
    return str;
  }
  
  public static String getUCMSignature()
  {
    Object localObject = com.uc.base.system.d.b.mContext;
    if (localObject == null) {
      return "";
    }
    try
    {
      com.uc.e.a.h.d.RM();
      localObject = com.uc.e.a.h.d.getPackageInfo(((Context)localObject).getPackageName(), 64);
      if (localObject != null)
      {
        localObject = String.valueOf(localObject.signatures[0].toChars());
        return localObject;
      }
    }
    catch (Exception localException)
    {
      e.f(localException);
    }
    return "";
  }
  
  public static String getUCMSignatureMD5()
  {
    String str1 = "";
    String str2 = getUCMSignature();
    if (!com.uc.e.a.i.b.oe(str2)) {
      str1 = com.uc.base.util.b.b.ah(str2.getBytes());
    }
    return str1;
  }
  
  public static String getUCMobileApks()
  {
    return getUCMobileApks(com.uc.base.system.d.b.mContext);
  }
  
  public static String getUCMobileApks(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = new StringBuilder();
    try
    {
      com.uc.e.a.h.d.RM();
      Iterator localIterator = com.uc.e.a.h.d.RN().iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.packageName != null) && (localPackageInfo.packageName.trim().length() != 0) && (localPackageInfo.packageName.startsWith("com.UCMobile"))) {
          paramContext.append(localPackageInfo.packageName).append('|');
        }
      }
      return paramContext.toString();
    }
    catch (Exception localException)
    {
      e.f(localException);
      if ((paramContext.length() != 0) && (paramContext.charAt(paramContext.length() - 1) == '|')) {
        paramContext.deleteCharAt(paramContext.length() - 1);
      }
    }
  }
  
  public static String getUcParamItemString(String paramString)
  {
    return i.cz(paramString);
  }
  
  public static String getValidUrl(String paramString)
  {
    return BrowserURLUtil.getValidUrl(paramString);
  }
  
  public static boolean isAirplaneModeOn(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        boolean bool = "1".equals(Settings.System.getString(paramContext.getContentResolver(), "airplane_mode_on"));
        return bool;
      }
      catch (Throwable paramContext)
      {
        e.f(paramContext);
      }
    }
    return false;
  }
  
  public static boolean isAndroidVersionMatched(String paramString)
  {
    Object localObject = paramString;
    if (paramString.startsWith(",")) {
      localObject = "-2147483648" + paramString;
    }
    paramString = (String)localObject;
    if (((String)localObject).endsWith(",")) {
      paramString = (String)localObject + Integer.MAX_VALUE;
    }
    paramString = com.uc.e.a.i.b.bT(paramString, ",");
    if (paramString.length < 3) {}
    do
    {
      for (;;)
      {
        return false;
        localObject = new int[paramString.length];
        int i = 0;
        String str;
        if (i < localObject.length) {
          str = paramString[i].trim();
        }
        int j;
        try
        {
          localObject[i] = Integer.parseInt(str);
          if (i > 0)
          {
            j = localObject[i];
            int k = localObject[(i - 1)];
            if (j < k) {}
          }
          else
          {
            i += 1;
          }
        }
        catch (NumberFormatException paramString) {}
      }
      j = Build.VERSION.SDK_INT;
      if (j < localObject[0]) {
        return true;
      }
      i = 1;
      while (i < localObject.length - 1)
      {
        if (j == localObject[i]) {
          return true;
        }
        i += 1;
      }
    } while (j <= localObject[(localObject.length - 1)]);
    return true;
    return false;
  }
  
  public static boolean isAssumeCharset(byte[] paramArrayOfByte, String paramString)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0) || (paramString == null) || (paramString.length() == 0)) {}
    for (;;)
    {
      return false;
      try
      {
        paramString = new String(paramArrayOfByte, paramString).getBytes(paramString);
        if ((paramString != null) && (paramString.length == paramArrayOfByte.length))
        {
          int j = paramArrayOfByte.length;
          int i = 0;
          for (;;)
          {
            if (i >= j) {
              break label81;
            }
            int k = paramArrayOfByte[i];
            int m = paramString[i];
            if (k != m) {
              break;
            }
            i += 1;
          }
          label81:
          return true;
        }
      }
      catch (UnsupportedEncodingException paramArrayOfByte)
      {
        e.f(paramArrayOfByte);
      }
    }
    return false;
  }
  
  public static boolean isEnableSmartNoImage()
  {
    return w.isEnableSmartNoImage();
  }
  
  public static boolean isEnableUCDesktopEntrance()
  {
    return com.uc.application.desktopwidget.b.b.aCF();
  }
  
  public static boolean isMainThread()
  {
    return com.uc.e.a.f.a.isMainThread();
  }
  
  public static boolean isMultiWindowGallery()
  {
    return SystemUtil.aGU();
  }
  
  public static boolean isRunningInBackground()
  {
    return !com.uc.base.system.f.a.hxp;
  }
  
  public static boolean isRunnningInBackgroundOrScreenLock()
  {
    return (isRunningInBackground()) || (isScreenLock());
  }
  
  public static boolean isScreenLock()
  {
    try
    {
      Object localObject = com.uc.base.system.d.b.mContext;
      if (localObject == null) {
        return false;
      }
      localObject = (KeyguardManager)((Context)localObject).getSystemService("keyguard");
      if (localObject != null)
      {
        boolean bool = ((KeyguardManager)localObject).inKeyguardRestrictedInputMode();
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException)
    {
      e.f(localException);
    }
    return false;
  }
  
  public static boolean isSdcardSymlink()
  {
    if (hxx == null) {
      hxx = Boolean.valueOf(com.uc.e.a.k.b.oq("/sdcard"));
    }
    return hxx.booleanValue();
  }
  
  public static String m9Base64EncodeStr(String paramString)
  {
    if (com.uc.e.a.i.b.oe(paramString)) {}
    for (;;)
    {
      return "";
      try
      {
        d.aGJ();
        paramString = d.F(paramString.getBytes("utf-8"));
        if (paramString != null)
        {
          paramString = Base64.encodeToString(paramString, 2);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        e.f(paramString);
      }
    }
    return "";
  }
  
  public static String m9Base64UrlEncodeStr(@Nullable String paramString)
  {
    if (com.uc.e.a.i.b.oe(paramString)) {}
    for (;;)
    {
      return "";
      try
      {
        d.aGJ();
        paramString = d.F(paramString.getBytes("utf-8"));
        if (paramString != null)
        {
          paramString = URLEncoder.encode(Base64.encodeToString(paramString, 2));
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        e.f(paramString);
      }
    }
    return "";
  }
  
  static native byte[] nativeM9Decode(byte[] paramArrayOfByte);
  
  static native byte[] nativeM9DecodeAndUnzipData(boolean paramBoolean1, boolean paramBoolean2, byte[] paramArrayOfByte);
  
  static native byte[] nativeM9Encode(byte[] paramArrayOfByte);
  
  public static String unGzipByteArrayToString(byte[] paramArrayOfByte)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      return null;
    }
    Object localObject1 = localObject2;
    ByteArrayInputStream localByteArrayInputStream;
    GZIPInputStream localGZIPInputStream;
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      localObject1 = localObject2;
      localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
      localObject1 = localObject2;
      localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject1 = localObject2;
      paramArrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        localObject1 = localObject2;
        int i = localGZIPInputStream.read(paramArrayOfByte, 0, 1024);
        if (i == -1) {
          break;
        }
        localObject1 = localObject2;
        localByteArrayOutputStream.write(paramArrayOfByte, 0, i);
      }
      localObject1 = localObject2;
    }
    catch (Exception paramArrayOfByte)
    {
      e.f(paramArrayOfByte);
      return localObject1;
    }
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    paramArrayOfByte = localObject3;
    if (arrayOfByte != null) {}
    try
    {
      paramArrayOfByte = new String(arrayOfByte, "utf-8");
      localObject1 = paramArrayOfByte;
      localByteArrayOutputStream.flush();
    }
    catch (Exception localException2)
    {
      try
      {
        localByteArrayOutputStream.close();
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            localGZIPInputStream.close();
            try
            {
              localByteArrayInputStream.close();
              return paramArrayOfByte;
            }
            catch (Exception localException1)
            {
              localObject1 = paramArrayOfByte;
              e.f(localException1);
              return paramArrayOfByte;
            }
            paramArrayOfByte = paramArrayOfByte;
            localObject1 = localException1;
            e.f(paramArrayOfByte);
            paramArrayOfByte = localObject3;
            continue;
            localException2 = localException2;
            localObject1 = paramArrayOfByte;
            e.f(localException2);
          }
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            localObject1 = paramArrayOfByte;
            e.f(localException3);
          }
        }
      }
    }
  }
  
  public static String urlBase64m9DecodeStr(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    try
    {
      paramString = URLDecoder.decode(paramString);
      if (com.uc.e.a.i.b.oe(paramString)) {
        return "";
      }
      paramString = Base64.decode(paramString, 0);
      if (paramString == null) {
        return "";
      }
      getInstance();
      paramString = nativeM9Decode(paramString);
      if (paramString == null) {
        return "";
      }
      paramString = new String(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      e.f(paramString);
    }
    return "";
  }
  
  public boolean alipayIsInstall()
  {
    com.uc.e.a.h.d.RM();
    return com.uc.e.a.h.d.nR("com.alipay.android.app");
  }
  
  public boolean callUCDL(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString1))) {
      return false;
    }
    try
    {
      Intent localIntent = new Intent("uc.ucdl.intent.action.NEW_TASK");
      localIntent.putExtra("url", paramString1);
      localIntent.putExtra("cookie", paramString2);
      localIntent.putExtra("ref", paramString3);
      localIntent.putExtra("ua", paramString4);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      e.f(paramContext);
    }
    return false;
  }
  
  public void changeCrashState(int paramInt, boolean paramBoolean)
  {
    StatsModel.changeCrashState(paramInt, paramBoolean);
  }
  
  /* Error */
  public int extractedFile(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: aconst_null
    //   4: astore 11
    //   6: aload_1
    //   7: ifnull +10 -> 17
    //   10: aload_2
    //   11: invokestatic 737	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   14: ifeq +5 -> 19
    //   17: iconst_m1
    //   18: ireturn
    //   19: aload_1
    //   20: invokevirtual 763	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   23: invokevirtual 769	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   26: astore 9
    //   28: aload 9
    //   30: aload_2
    //   31: invokevirtual 775	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   34: astore 9
    //   36: aload 9
    //   38: ifnonnull +14 -> 52
    //   41: aconst_null
    //   42: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   45: aload 9
    //   47: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   50: iconst_m1
    //   51: ireturn
    //   52: aload 9
    //   54: invokevirtual 786	java/io/InputStream:available	()I
    //   57: i2l
    //   58: lstore 5
    //   60: iconst_0
    //   61: iconst_1
    //   62: invokestatic 788	com/uc/base/system/SystemHelper:getSpace	(II)J
    //   65: lload 5
    //   67: lcmp
    //   68: iflt +109 -> 177
    //   71: new 352	java/io/File
    //   74: dup
    //   75: new 216	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 217	java/lang/StringBuilder:<init>	()V
    //   82: aload_1
    //   83: invokevirtual 498	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   86: getfield 791	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   89: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload_2
    //   93: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokespecial 393	java/io/File:<init>	(Ljava/lang/String;)V
    //   102: astore 12
    //   104: aload 12
    //   106: invokevirtual 396	java/io/File:exists	()Z
    //   109: ifeq +9 -> 118
    //   112: aload 12
    //   114: invokevirtual 794	java/io/File:delete	()Z
    //   117: pop
    //   118: aload_1
    //   119: aload_2
    //   120: iconst_0
    //   121: invokevirtual 798	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   124: astore_1
    //   125: iconst_0
    //   126: istore_3
    //   127: sipush 5012
    //   130: newarray byte
    //   132: astore_2
    //   133: aload 9
    //   135: aload_2
    //   136: iconst_0
    //   137: sipush 5012
    //   140: invokevirtual 799	java/io/InputStream:read	([BII)I
    //   143: istore 4
    //   145: iload 4
    //   147: ifle +169 -> 316
    //   150: aload_1
    //   151: aload_2
    //   152: iconst_0
    //   153: iload 4
    //   155: invokevirtual 802	java/io/FileOutputStream:write	([BII)V
    //   158: goto -25 -> 133
    //   161: astore_2
    //   162: aload_1
    //   163: astore_2
    //   164: aload 9
    //   166: astore_1
    //   167: aload_2
    //   168: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   171: aload_1
    //   172: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   175: iconst_m1
    //   176: ireturn
    //   177: iconst_1
    //   178: iconst_1
    //   179: invokestatic 788	com/uc/base/system/SystemHelper:getSpace	(II)J
    //   182: lstore 7
    //   184: lload 7
    //   186: lload 5
    //   188: lcmp
    //   189: ifge +15 -> 204
    //   192: aconst_null
    //   193: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   196: aload 9
    //   198: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   201: bipush -2
    //   203: ireturn
    //   204: new 216	java/lang/StringBuilder
    //   207: dup
    //   208: invokespecial 217	java/lang/StringBuilder:<init>	()V
    //   211: ldc_w 804
    //   214: invokestatic 807	com/uc/e/a/c/b:nJ	(Ljava/lang/String;)Ljava/lang/String;
    //   217: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: ldc_w 809
    //   223: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: astore_1
    //   230: new 352	java/io/File
    //   233: dup
    //   234: new 216	java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial 217	java/lang/StringBuilder:<init>	()V
    //   241: aload_1
    //   242: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: aload_2
    //   246: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   252: invokespecial 393	java/io/File:<init>	(Ljava/lang/String;)V
    //   255: astore_2
    //   256: new 352	java/io/File
    //   259: dup
    //   260: aload_1
    //   261: invokespecial 393	java/io/File:<init>	(Ljava/lang/String;)V
    //   264: astore_1
    //   265: aload_1
    //   266: invokevirtual 396	java/io/File:exists	()Z
    //   269: ifeq +29 -> 298
    //   272: aload_2
    //   273: invokevirtual 396	java/io/File:exists	()Z
    //   276: ifeq +8 -> 284
    //   279: aload_2
    //   280: invokevirtual 794	java/io/File:delete	()Z
    //   283: pop
    //   284: new 801	java/io/FileOutputStream
    //   287: dup
    //   288: aload_2
    //   289: invokespecial 812	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   292: astore_1
    //   293: iconst_1
    //   294: istore_3
    //   295: goto -168 -> 127
    //   298: aload_1
    //   299: invokevirtual 815	java/io/File:mkdir	()Z
    //   302: pop
    //   303: goto -19 -> 284
    //   306: astore_1
    //   307: aload 9
    //   309: astore_1
    //   310: aload 11
    //   312: astore_2
    //   313: goto -146 -> 167
    //   316: aload_1
    //   317: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   320: aload 9
    //   322: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   325: iload_3
    //   326: ireturn
    //   327: astore_1
    //   328: aconst_null
    //   329: astore 9
    //   331: aload 10
    //   333: astore_2
    //   334: aload_2
    //   335: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   338: aload 9
    //   340: invokestatic 781	com/uc/e/a/k/a:b	(Ljava/io/Closeable;)V
    //   343: aload_1
    //   344: athrow
    //   345: astore_1
    //   346: aload 10
    //   348: astore_2
    //   349: goto -15 -> 334
    //   352: astore 10
    //   354: aload_1
    //   355: astore_2
    //   356: aload 10
    //   358: astore_1
    //   359: goto -25 -> 334
    //   362: astore_1
    //   363: aconst_null
    //   364: astore_1
    //   365: aload 11
    //   367: astore_2
    //   368: goto -201 -> 167
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	371	0	this	SystemHelper
    //   0	371	1	paramContext	Context
    //   0	371	2	paramString	String
    //   126	200	3	i	int
    //   143	11	4	j	int
    //   58	129	5	l1	long
    //   182	3	7	l2	long
    //   26	313	9	localObject1	Object
    //   1	346	10	localObject2	Object
    //   352	5	10	localObject3	Object
    //   4	362	11	localObject4	Object
    //   102	11	12	localFile	File
    // Exception table:
    //   from	to	target	type
    //   127	133	161	java/lang/Exception
    //   133	145	161	java/lang/Exception
    //   150	158	161	java/lang/Exception
    //   52	118	306	java/lang/Exception
    //   118	125	306	java/lang/Exception
    //   177	184	306	java/lang/Exception
    //   204	284	306	java/lang/Exception
    //   284	293	306	java/lang/Exception
    //   298	303	306	java/lang/Exception
    //   28	36	327	finally
    //   52	118	345	finally
    //   118	125	345	finally
    //   177	184	345	finally
    //   204	284	345	finally
    //   284	293	345	finally
    //   298	303	345	finally
    //   127	133	352	finally
    //   133	145	352	finally
    //   150	158	352	finally
    //   28	36	362	java/lang/Exception
  }
  
  public int extractedFile(String paramString)
  {
    return extractedFile(com.uc.base.system.d.b.mContext, paramString);
  }
  
  public JSONObject getAppInfo(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = new JSONObject();
    try
    {
      com.uc.e.a.h.d.RM();
      paramString = com.uc.e.a.h.d.hp(paramString);
      if (paramString != null)
      {
        localObject = paramString.applicationInfo.loadLabel((PackageManager)localObject).toString();
        String str = String.valueOf(paramString.versionName);
        int i = paramString.versionCode;
        long l = new File(paramString.applicationInfo.publicSourceDir).length();
        paramContext.put("appName", localObject);
        paramContext.put("versionName", str);
        paramContext.put("versionCode", i);
        paramContext.put("appSize", l);
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  @Jni
  public String getBrowserPkgInfo(String paramString)
  {
    String str = "";
    if ("version".equals(paramString)) {
      str = "12.8.8.1140";
    }
    do
    {
      return str;
      if ("subversion".equals(paramString)) {
        return "inapppatch";
      }
      if ("region_version".equals(paramString)) {
        return "inter";
      }
      if ("lang_list".equals(paramString)) {
        return "en-us,ru,vi,id,pt-br,es-la,th,zh-tw,ar-sa,bd,ur,hi,ta,mr,te,gu,bn,kn,ml,pa,or,ur-in,as,mn,bh";
      }
      if ("prd".equals(paramString)) {
        return "UCMobile";
      }
      if ("lang".equals(paramString)) {
        return "en-us";
      }
      if ("pver".equals(paramString)) {
        return "3.1";
      }
      if ("platfrom".equals(paramString)) {
        return "android";
      }
    } while (!"profile_id".equals(paramString));
    return "145";
  }
  
  public String getCurrentIAPName()
  {
    Object localObject = a.getActiveNetworkInfo();
    if (localObject != null)
    {
      int i = ((NetworkInfo)localObject).getType();
      if (i == 1) {
        localObject = "wifi";
      }
      String str;
      do
      {
        return localObject;
        if (i != 0) {
          break;
        }
        str = ((NetworkInfo)localObject).getExtraInfo();
        localObject = str;
      } while (str != null);
      return "";
    }
    return "";
  }
  
  public String getInstalledApks(int paramInt)
  {
    return getInstalledApks(com.uc.base.system.d.b.mContext, paramInt);
  }
  
  public String getInstalledApks(Context paramContext, int paramInt)
  {
    if (paramContext == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      PackageInfo localPackageInfo;
      char c;
      try
      {
        com.uc.e.a.h.d.RM();
        Iterator localIterator = com.uc.e.a.h.d.RN().iterator();
        if (!localIterator.hasNext()) {
          continue;
        }
        localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.packageName == null) || (localPackageInfo.packageName.trim().length() == 0)) {
          continue;
        }
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          break label365;
        }
        c = 'S';
      }
      catch (Exception paramContext)
      {
        e.f(paramContext);
        if ((localStringBuilder.length() == 0) || (localStringBuilder.charAt(localStringBuilder.length() - 1) != '|')) {
          continue;
        }
        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        return localStringBuilder.toString();
      }
      localStringBuilder.append('P').append('=').append(localPackageInfo.packageName).append(';');
      localStringBuilder.append('C').append('=').append(localPackageInfo.versionCode).append(';');
      if ((localPackageInfo.versionName == null) || (localPackageInfo.versionName.trim().length() == 0))
      {
        localStringBuilder.append('N').append('=').append("-1").append(';');
        localStringBuilder.append('T').append('=').append(c).append(';');
        localStringBuilder.append('A').append('=').append(localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager())).append(';');
        localStringBuilder.append('S').append('=').append(new File(localPackageInfo.applicationInfo.publicSourceDir).length()).append('|');
      }
      else
      {
        localStringBuilder.append('N').append('=').append(localPackageInfo.versionName).append(';');
        continue;
        label365:
        c = 'U';
        if ((paramInt != 0) && ((paramInt != 1) || (c != 'S'))) {
          if ((paramInt != 2) || (c != 'U')) {}
        }
      }
    }
  }
  
  public String[] getInstalledApksInfo(int paramInt)
  {
    return getInstalledApksInfo(com.uc.base.system.d.b.mContext, paramInt);
  }
  
  public String[] getInstalledApksInfo(Context paramContext, int paramInt)
  {
    if (paramContext == null) {
      return null;
    }
    for (;;)
    {
      try
      {
        localPackageManager = paramContext.getPackageManager();
        localMap = getSysReceiverInfoMap(localPackageManager);
        paramContext = localPackageManager.getInstalledPackages(4160);
        if (paramContext == null) {
          continue;
        }
        arrayOfString = new String[paramContext.size() * 9];
        localIterator = paramContext.iterator();
        i = 0;
      }
      catch (Exception paramContext)
      {
        PackageManager localPackageManager;
        Map localMap;
        String[] arrayOfString;
        Iterator localIterator;
        int i;
        PackageInfo localPackageInfo;
        e.f(paramContext);
        paramContext = null;
        continue;
        int j = 0;
        continue;
        paramContext = null;
        continue;
        if ((paramInt == 0) || ((paramInt == 1) && (j != 0))) {
          continue;
        }
        if ((paramInt != 2) || (j != 0)) {
          continue;
        }
        continue;
        paramContext = "U";
        continue;
      }
      if (!localIterator.hasNext()) {
        continue;
      }
      localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        continue;
      }
      j = 1;
      continue;
      arrayOfString[(i + 0)] = localPackageInfo.packageName;
      arrayOfString[(i + 1)] = String.valueOf(localPackageInfo.versionCode);
      arrayOfString[(i + 2)] = localPackageInfo.versionName;
      arrayOfString[(i + 3)] = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
      if (j == 0) {
        continue;
      }
      paramContext = "S";
      arrayOfString[(i + 4)] = paramContext;
      arrayOfString[(i + 5)] = getAppSignaturesMD5(localPackageInfo);
      arrayOfString[(i + 6)] = getAppPermissions(localPackageInfo);
      arrayOfString[(i + 7)] = getAppBroadcasts(localPackageInfo, localMap);
      arrayOfString[(i + 8)] = String.valueOf(getFileSize(localPackageInfo.applicationInfo.publicSourceDir));
      i += 9;
    }
    paramContext = new String[i];
    System.arraycopy(arrayOfString, 0, paramContext, 0, i);
    return paramContext;
  }
  
  public String getMacAddress()
  {
    return h.getMacAddress();
  }
  
  public boolean hasProxyForCurApn()
  {
    return a.hasProxyForCurApn();
  }
  
  public native String nativeUcApkUmengMd5();
  
  public void openAccessPointSetting(Context paramContext)
  {
    Intent localIntent;
    if (paramContext != null) {
      localIntent = new Intent("android.settings.APN_SETTINGS");
    }
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public void openWifiSetting(Context paramContext)
  {
    Intent localIntent;
    if (paramContext != null) {
      localIntent = new Intent("android.settings.WIFI_SETTINGS");
    }
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public Object[] parseBaiduSugJson(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return null;
    }
    try
    {
      if (paramString.length() >= 19)
      {
        JSONArray localJSONArray = new JSONObject(paramString.substring(17, paramString.length() - 2)).getJSONArray("s");
        Object[] arrayOfObject = new Object[localJSONArray.length() + 1];
        int i = 0;
        for (;;)
        {
          paramString = arrayOfObject;
          if (i >= localJSONArray.length()) {
            break;
          }
          arrayOfObject[i] = localJSONArray.get(i);
          i += 1;
        }
      }
      paramString = null;
      return paramString;
    }
    catch (Exception paramString)
    {
      e.f(paramString);
    }
    return null;
  }
  
  public String[] parseBibeiJson(String paramString)
  {
    String[] arrayOfString = new String[5];
    try
    {
      paramString = new JSONObject(paramString);
      String str1 = paramString.getString("RESULT");
      int i = paramString.getInt("upcount");
      String str2 = paramString.getString("upurl");
      String str3 = paramString.getString("prompt");
      int j = paramString.getInt("position");
      arrayOfString[0] = str1;
      arrayOfString[1] = String.valueOf(i);
      arrayOfString[2] = str2;
      arrayOfString[3] = str3;
      arrayOfString[4] = String.valueOf(j);
      return arrayOfString;
    }
    catch (Throwable paramString)
    {
      e.f(paramString);
      arrayOfString[0] = "";
      arrayOfString[1] = "";
      arrayOfString[2] = "";
      arrayOfString[3] = "";
      arrayOfString[4] = "";
    }
    return arrayOfString;
  }
  
  public Object[] parseGoogleSugJson(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      try
      {
        JSONArray localJSONArray = new JSONArray(paramString).getJSONArray(1);
        Object[] arrayOfObject = new Object[localJSONArray.length() + 1];
        int i = 0;
        for (;;)
        {
          paramString = arrayOfObject;
          if (i >= localJSONArray.length()) {
            break;
          }
          arrayOfObject[i] = localJSONArray.get(i);
          i += 1;
        }
        return null;
      }
      catch (Exception paramString)
      {
        e.f(paramString);
      }
    }
  }
  
  public Object[] parseVideoJsonData(String paramString)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray(paramString);
      Object[] arrayOfObject = new Object[localJSONArray.length() * 2];
      int i = 0;
      for (;;)
      {
        paramString = arrayOfObject;
        if (i >= localJSONArray.length()) {
          break;
        }
        paramString = localJSONArray.getJSONObject(i);
        arrayOfObject[(i * 2)] = paramString.getString("title");
        arrayOfObject[(i * 2 + 1)] = paramString.getString("vtype");
        i += 1;
      }
      return paramString;
    }
    catch (JSONException paramString)
    {
      e.f(paramString);
      paramString = null;
    }
  }
  
  public Object[] parseYandexSugJson(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      try
      {
        JSONArray localJSONArray = new JSONArray(paramString.substring(paramString.indexOf('(') + 1, paramString.lastIndexOf(')'))).getJSONArray(1);
        Object[] arrayOfObject = new Object[localJSONArray.length() + 1];
        int i = 0;
        for (;;)
        {
          paramString = arrayOfObject;
          if (i >= localJSONArray.length()) {
            break;
          }
          arrayOfObject[i] = localJSONArray.get(i);
          i += 1;
        }
        return null;
      }
      catch (Exception paramString)
      {
        e.f(paramString);
      }
    }
  }
  
  public String queryAppsInfo(Context paramContext, List<String> paramList)
  {
    JSONObject localJSONObject1 = new JSONObject();
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (!paramList.hasNext()) {
          break;
        }
        String str = (String)paramList.next();
        JSONObject localJSONObject2 = getAppInfo(paramContext, str);
        if (localJSONObject2 == null) {
          localJSONObject1.put(str, "");
        } else {
          localJSONObject1.put(str, localJSONObject2);
        }
      }
      catch (Exception paramContext)
      {
        return "";
      }
    }
    return localJSONObject1.toString();
  }
  
  public boolean returnToCaller(Context paramContext)
  {
    if ((paramContext instanceof Activity))
    {
      paramContext = (Activity)paramContext;
      if ((paramContext != null) && (paramContext.moveTaskToBack(true))) {
        return true;
      }
    }
    return false;
  }
  
  public void sendBroadcast(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      try
      {
        File localFile = new File("/mnt/" + paramString);
        if (!localFile.exists())
        {
          localFile = new File(paramString);
          if (!localFile.exists()) {
            paramString = Uri.parse(paramString);
          }
        }
        for (;;)
        {
          paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", paramString));
          return;
          paramString = Uri.fromFile(localFile);
          continue;
          paramString = Uri.fromFile(localFile);
        }
        return;
      }
      catch (Exception paramContext)
      {
        e.f(paramContext);
      }
    }
  }
  
  public boolean startBarcodeScan(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramContext == null) {}
    while (paramContext.getApplicationContext() == null) {
      return false;
    }
    ((com.uc.module.b.a)com.uc.base.e.b.getService(com.uc.module.b.a.class)).openScanner((Activity)paramContext, paramInt, paramBoolean1, paramBoolean3);
    return true;
  }
  
  public boolean startBarcodeScan(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    return startBarcodeScan(paramContext, 5, paramBoolean1, paramBoolean2, true);
  }
  
  public boolean startBarcodeScan(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    com.uc.base.system.f.b.putBoolean("startbarcodefromlauncher", paramBoolean3);
    return startBarcodeScan(com.uc.base.system.d.b.mContext, paramBoolean1, paramBoolean2);
  }
}
