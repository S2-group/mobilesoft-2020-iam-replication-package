package com.tenor.android.core.extension.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.tenor.android.core.helper.GsonHelper;
import com.tenor.android.core.util.CoreSessionUtils;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CoreExtSessionUtils
  extends CoreSessionUtils
{
  private static final String KEY_GOOGLE_CLOUD_MESSAGING_ID = "KEY_GOOGLE_CLOUD_MESSAGING_ID";
  private static final String KEY_INSTALLED_PACKAGES_SET_ID = "KEY_INSTALLED_PACKAGES_SET_ID";
  private static final String KEY_KEYBOARD_REQUEST_DELAY = "KEY_KEYBOARD_REQUEST_DELAY";
  private static final String KEY_KEYBOARD_RESPONSE_CACHE_SIZE = "KEY_KEYBOARD_RESPONSE_CACHE_SIZE";
  private static final String KEY_KEYBOARD_RESPONSE_CACHE_TIMEOUT = "KEY_KEYBOARD_RESPONSE_CACHE_TIMEOUT";
  private static final String KEY_SESSION_ID = "KEY_SESSION_ID";
  private static final String KEY_SESSION_ID_EXPIRATION = "KEY_SESSION_ID_EXPIRATION";
  private static final String KEY_UPDATED_TERMS_ACCEPTED = "tenor.KEY_UPDATED_TERMS_ACCEPTED";
  private static final String KEY_UPDATED_TERMS_ACCEPT_LATER_COUNT = "tenor.KEY_UPDATED_TERMS_ACCEPT_LATER_COUNT";
  private static final String KEY_UPDATED_TERMS_DISPLAYED = "tenor.KEY_UPDATED_TERMS_DISPLAYED";
  
  public CoreExtSessionUtils() {}
  
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
    Type localType = new CoreExtSessionUtils.1().getType();
    return (Set)GsonHelper.get().fromJson(paramContext, localType);
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
  
  public static int getUpdateTermsDismissedCount(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getInt("tenor.KEY_UPDATED_TERMS_ACCEPT_LATER_COUNT", 0);
  }
  
  public static boolean isUpdateTermsAccepted(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getBoolean("tenor.KEY_UPDATED_TERMS_ACCEPTED", false);
  }
  
  public static boolean isUpdateTermsDisplayed(@NonNull Context paramContext)
  {
    return getPreferences(paramContext).getBoolean("tenor.KEY_UPDATED_TERMS_DISPLAYED", false);
  }
  
  public static void notifyUpdateTermsAccepted(@NonNull Context paramContext)
  {
    notifyUpdateTermsDisplayed(paramContext);
    setUpdateTermsAccepted(paramContext, true);
  }
  
  public static void notifyUpdateTermsDisplayed(@NonNull Context paramContext)
  {
    setUpdateTermsDisplayed(paramContext, true);
  }
  
  public static String resetSessionId(@NonNull Context paramContext)
  {
    return getSessionId(paramContext, true);
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
        str1 = GsonHelper.get().toJson(paramSet);
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
  
  public static void setUpdateTermsAccepted(@NonNull Context paramContext, boolean paramBoolean)
  {
    getPreferences(paramContext).edit().putBoolean("tenor.KEY_UPDATED_TERMS_ACCEPTED", paramBoolean).apply();
  }
  
  public static void setUpdateTermsDismissedCount(@NonNull Context paramContext, int paramInt)
  {
    getPreferences(paramContext).edit().putInt("tenor.KEY_UPDATED_TERMS_ACCEPT_LATER_COUNT", paramInt).apply();
  }
  
  public static void setUpdateTermsDisplayed(@NonNull Context paramContext, boolean paramBoolean)
  {
    getPreferences(paramContext).edit().putBoolean("tenor.KEY_UPDATED_TERMS_DISPLAYED", paramBoolean).apply();
  }
}
