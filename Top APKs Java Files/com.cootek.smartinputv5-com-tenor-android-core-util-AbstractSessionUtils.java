package com.tenor.android.core.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class AbstractSessionUtils
{
  private static final String DEVICE_PREF = "device_preferences";
  private static final String KEY_ANDROID_ADVERTISE_ID = "KEY_ANDROID_ADVERTISE_ID";
  private static final String KEY_ANON_ID = "KEY_ANON_ID";
  private static final String KEY_GOOGLE_CLOUD_MESSAGING_ID = "KEY_GOOGLE_CLOUD_MESSAGING_ID";
  private static final String KEY_INSTALLED_PACKAGES_SET_ID = "KEY_INSTALLED_PACKAGES_SET_ID";
  private static final String KEY_KEYBOARD_ID = "KEY_KEYBOARD_ID";
  private static final String KEY_KEYBOARD_REQUEST_DELAY = "KEY_KEYBOARD_REQUEST_DELAY";
  private static final String KEY_KEYBOARD_RESPONSE_CACHE_SIZE = "KEY_KEYBOARD_RESPONSE_CACHE_SIZE";
  private static final String KEY_KEYBOARD_RESPONSE_CACHE_TIMEOUT = "KEY_KEYBOARD_RESPONSE_CACHE_TIMEOUT";
  private static final String KEY_SESSION_ID = "KEY_SESSION_ID";
  private static final String KEY_SESSION_ID_EXPIRATION = "KEY_SESSION_ID_EXPIRATION";
  
  public AbstractSessionUtils() {}
  
  public static String getAndroidAdvertiseId(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getString("KEY_ANDROID_ADVERTISE_ID", "");
  }
  
  public static String getAnonId(@NonNull Context paramContext)
  {
    try
    {
      String str = getPreferences(paramContext).getString("KEY_ANON_ID", "");
      boolean bool = TextUtils.isEmpty(str);
      if (!bool) {
        return str;
      }
      str = getPreferences(paramContext).getString("KEY_KEYBOARD_ID", "");
      if (!TextUtils.isEmpty(str)) {
        getPreferences(paramContext).edit().putString("KEY_ANON_ID", str).apply();
      }
      return str;
    }
    finally {}
  }
  
  public static String getGoogleCloudMessagingId(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getString("KEY_GOOGLE_CLOUD_MESSAGING_ID", "");
  }
  
  @NonNull
  public static Set<String> getInstalledPackages(@NonNull Context paramContext)
  {
    paramContext = getPreferences(paramContext).getString("KEY_INSTALLED_PACKAGES_SET_ID", "");
    if (TextUtils.isEmpty(paramContext)) {
      return new HashSet();
    }
    Type localType = new AbstractSessionUtils.1().getType();
    return (Set)AbstractGsonUtils.getInstance().a(paramContext, localType);
  }
  
  public static long getKeyboardRequestDelay(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getLong("KEY_KEYBOARD_REQUEST_DELAY", 100L);
  }
  
  public static int getKeyboardResponseCacheSize(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getInt("KEY_KEYBOARD_RESPONSE_CACHE_SIZE", 25);
  }
  
  public static long getKeyboardResponseCacheTimeout(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getLong("KEY_KEYBOARD_RESPONSE_CACHE_TIMEOUT", TimeUnit.MINUTES.toMillis(30L));
  }
  
  protected static SharedPreferences getPreferences(@NonNull Context paramContext)
  {
    return paramContext.getSharedPreferences("device_preferences", 0);
  }
  
  public static String getSessionId(@NonNull Context paramContext)
  {
    return getSessionId(paramContext, false);
  }
  
  private static String getSessionId(@NonNull Context paramContext, boolean paramBoolean)
  {
    long l2 = getPreferences(paramContext).getLong("KEY_SESSION_ID_EXPIRATION", 0L);
    String str = getPreferences(paramContext).getString("KEY_SESSION_ID", "");
    long l1 = System.currentTimeMillis();
    if ((TextUtils.isEmpty(str)) || (l1 >= l2) || (paramBoolean))
    {
      l2 = TimeUnit.HOURS.toMillis(1L);
      str = AbstractUuidUtils.createUuid();
      getPreferences(paramContext).edit().putLong("KEY_SESSION_ID_EXPIRATION", l1 + l2).apply();
      getPreferences(paramContext).edit().putString("KEY_SESSION_ID", str).apply();
    }
    return str;
  }
  
  public static boolean hasAnonId(@NonNull Context paramContext)
  {
    return TextUtils.isEmpty(getAnonId(paramContext)) ^ true;
  }
  
  protected static void remove(@NonNull Context paramContext, String... paramVarArgs)
  {
    paramContext = getPreferences(paramContext).edit();
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      paramContext.remove(paramVarArgs[i]);
      i += 1;
    }
    paramContext.apply();
  }
  
  public static String resetSessionId(@NonNull Context paramContext)
  {
    return getSessionId(paramContext, true);
  }
  
  public static void setAndroidAdvertiseId(@NonNull Context paramContext, @Nullable String paramString)
  {
    if (paramString == null) {
      return;
    }
    getPreferences(paramContext).edit().putString("KEY_ANDROID_ADVERTISE_ID", paramString).apply();
  }
  
  public static void setAnonId(@NonNull Context paramContext, @Nullable String paramString)
  {
    try
    {
      boolean bool = TextUtils.isEmpty(paramString);
      if (bool) {
        return;
      }
      getPreferences(paramContext).edit().putString("KEY_ANON_ID", paramString).apply();
      return;
    }
    finally {}
  }
  
  public static void setGoogleCloudMessagingId(@NonNull Context paramContext, @Nullable String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    getPreferences(paramContext).edit().putString("KEY_GOOGLE_CLOUD_MESSAGING_ID", paramString).apply();
  }
  
  public static void setInstalledPackages(@NonNull Context paramContext, @Nullable Set<String> paramSet)
  {
    String str2 = "";
    String str1 = str2;
    if (paramSet != null)
    {
      str1 = str2;
      if (!paramSet.isEmpty()) {
        str1 = AbstractGsonUtils.getInstance().b(paramSet);
      }
    }
    getPreferences(paramContext).edit().putString("KEY_INSTALLED_PACKAGES_SET_ID", str1).apply();
  }
  
  protected static void setKeyboardRequestDelay(@NonNull Context paramContext, long paramLong)
  {
    getPreferences(paramContext).edit().putLong("KEY_KEYBOARD_REQUEST_DELAY", paramLong).apply();
  }
  
  protected static void setKeyboardResponseCacheSize(@NonNull Context paramContext, int paramInt)
  {
    getPreferences(paramContext).edit().putInt("KEY_KEYBOARD_RESPONSE_CACHE_SIZE", paramInt).apply();
  }
  
  protected static void setKeyboardResponseCacheTimeout(@NonNull Context paramContext, long paramLong)
  {
    getPreferences(paramContext).edit().putLong("KEY_KEYBOARD_RESPONSE_CACHE_TIMEOUT", paramLong).apply();
  }
}
