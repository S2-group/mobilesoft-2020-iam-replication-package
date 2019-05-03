package com.flipboard.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import com.flipboard.data.Request;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class FDLUtil
{
  private static final String ACCOUNT_SYNC_ACTION = "flipboard.app.broadcast.SYNC_USER_CHANGE";
  private static final String BROADCAST_PERMISSION = "sstream.app.broadcast.SYNC_USER";
  public static final String CLIENT_PARTNER = "cnn";
  public static final String FDL_VERSION = "0.60";
  public static final String FLIPBOARD_CHINA_PACKAGE = "flipboard.cn";
  public static final String FLIPBOARD_DEV_PACKAGE = "flipboard.internal.debug";
  public static final String FLIPBOARD_PACKAGE = "flipboard.app";
  public static final String FLIPBOARD_SERVICE = "flipboard";
  private static final String KEY_BROADCAST_OAUTH_TOKEN = "oauth_token";
  public static final String KEY_FDL_VERSION = "fdlVersion";
  private static final String KEY_SYNC_SERVICE = "logged_in_to_service";
  public static final String SHARED_PREFS_NAME = "com.flipboard.fdl.sharedPreferences";
  private static String device;
  private static String mFlipboardPackage;
  private static String model;
  
  public FDLUtil() {}
  
  private static String SHA1str(String paramString)
  {
    try
    {
      paramString = paramString.getBytes();
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA1");
      localMessageDigest.update(paramString, 0, paramString.length);
      paramString = localMessageDigest.digest();
      paramString = Format.format("%08x%08x%08x%08x%08x", new Object[] { Integer.valueOf(toInteger(paramString, 0)), Integer.valueOf(toInteger(paramString, 4)), Integer.valueOf(toInteger(paramString, 8)), Integer.valueOf(toInteger(paramString, 12)), Integer.valueOf(toInteger(paramString, 16)) });
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException("SHA1: " + paramString.getMessage());
    }
  }
  
  public static String appMode(boolean paramBoolean)
  {
    if (paramBoolean) {
      return "apad";
    }
    return "aphone";
  }
  
  public static String device(boolean paramBoolean)
  {
    String str2 = device;
    String str1 = str2;
    if (str2 == null) {
      if (!paramBoolean) {
        break label58;
      }
    }
    label58:
    for (str1 = "apad";; str1 = "aphone")
    {
      str1 = Format.format("%s-%s-%s-%s", new Object[] { str1, Build.MANUFACTURER, Build.MODEL, Integer.valueOf(Build.VERSION.SDK_INT) });
      device = str1;
      return str1;
    }
  }
  
  public static String escapeURL(String paramString)
  {
    try
    {
      String str = escapeURL(paramString, "utf-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Log.main.warning(localUnsupportedEncodingException);
    }
    return paramString;
  }
  
  public static String escapeURL(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    if (paramString1 == null) {
      return null;
    }
    return URLEncoder.encode(paramString1, paramString2);
  }
  
  public static void fireTokenBroadcast(String paramString1, String paramString2)
  {
    Log.main.debug("fire token broadcast %s", new Object[] { paramString1 });
    if (paramString1 == null)
    {
      Log.main.error("No token present, not firing account sync intent", new Object[0]);
      return;
    }
    Intent localIntent = new Intent("flipboard.app.broadcast.SYNC_USER_CHANGE");
    String str2 = getFlipboardPackage();
    localIntent.setPackage(str2);
    if (paramString1.length() > 6) {}
    for (String str1 = paramString1.substring(paramString1.length() - 6, paramString1.length());; str1 = paramString1)
    {
      localIntent.putExtra("oauth_token", paramString1);
      localIntent.putExtra("logged_in_to_service", paramString2);
      localIntent.putExtra("fdlVersion", "0.60");
      localIntent.addFlags(32);
      Log.main.debug("FDL: broadcast intent [to: %s] [fdlVersion: %s] [authToken: %s] [service: %s]", new Object[] { str2, "0.60", str1, paramString2 });
      Request.getInstance().getContext().sendBroadcast(localIntent, "sstream.app.broadcast.SYNC_USER");
      return;
    }
  }
  
  public static String generateDeviceId(Context paramContext)
  {
    return SHA1str(getDeviceSerialNumber(paramContext));
  }
  
  private static String getDeviceSerialNumber(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (str != null)
    {
      paramContext = str;
      if (!str.equals("9774d56d682e549c")) {}
    }
    else
    {
      paramContext = UUID.randomUUID().toString();
    }
    return paramContext;
  }
  
  public static String getFlipboardPackage()
  {
    return getFlipboardPackage(true, true);
  }
  
  public static String getFlipboardPackage(boolean paramBoolean1, boolean paramBoolean2)
  {
    int k;
    int j;
    int i;
    ApplicationInfo localApplicationInfo;
    if (mFlipboardPackage == null)
    {
      Iterator localIterator = Request.getInstance().getContext().getApplicationContext().getPackageManager().getInstalledApplications(128).iterator();
      k = 0;
      j = 0;
      i = 0;
      if (localIterator.hasNext())
      {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ("flipboard.cn".equals(localApplicationInfo.packageName))
        {
          i = k;
          k = 1;
        }
      }
    }
    for (;;)
    {
      int m = k;
      k = i;
      i = m;
      break;
      if ("flipboard.internal.debug".equals(localApplicationInfo.packageName))
      {
        j = k;
        k = i;
        m = 1;
        i = j;
        j = m;
      }
      else if ("flipboard.app".equals(localApplicationInfo.packageName))
      {
        m = 1;
        k = i;
        i = m;
        continue;
        if ((paramBoolean1) && (i != 0)) {
          mFlipboardPackage = "flipboard.cn";
        }
        for (;;)
        {
          return mFlipboardPackage;
          if (j != 0) {
            mFlipboardPackage = "flipboard.internal.debug";
          } else if (k != 0) {
            mFlipboardPackage = "flipboard.app";
          } else if (paramBoolean2) {
            mFlipboardPackage = "flipboard.app";
          }
        }
      }
      else
      {
        m = i;
        i = k;
        k = m;
      }
    }
  }
  
  public static float getScreenInches(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    float f1 = paramContext.widthPixels / paramContext.xdpi;
    float f2 = paramContext.heightPixels / paramContext.ydpi;
    return (float)Math.sqrt(f2 * f2 + f1 * f1);
  }
  
  public static String model()
  {
    String str2 = model;
    String str1 = str2;
    if (str2 == null)
    {
      str1 = Format.format("%s-%s", new Object[] { Build.MANUFACTURER, Build.MODEL });
      model = str1;
    }
    return str1;
  }
  
  private static int toInteger(byte[] paramArrayOfByte, int paramInt)
  {
    return ((paramArrayOfByte[(paramInt + 0)] & 0xFF) << 24) + ((paramArrayOfByte[(paramInt + 1)] & 0xFF) << 16) + ((paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8) + ((paramArrayOfByte[(paramInt + 3)] & 0xFF) << 0);
  }
}
