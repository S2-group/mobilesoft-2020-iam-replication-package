package com.riffsy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.tenor.android.core.constant.StringConstant;
import com.tenor.android.ime.util.ImeSessionUtils;
import java.util.HashSet;
import java.util.Set;

public class SessionUtils
  extends ImeSessionUtils
{
  private static final String KEY_ACCESSIBILITY_DIALOG_SHOWED = "key_accessibility_dialog_status";
  private static final String KEY_APP_VERSION = "key_app_version";
  public static final String KEY_CONTAINING_SEARCH_TAG = "KEY_CONTAINING_SEARCH_TAG";
  public static final String KEY_CONTAINING_SEARCH_TAG1 = "KEY_CONTAINING_SEARCH_TAG1";
  public static final String KEY_CONTAINING_SEARCH_TAG2 = "KEY_CONTAINING_SEARCH_TAG2";
  public static final String KEY_DETAILS_ACTIVITY_OPEN_COUNT = "KEY_DETAILS_ACTIVITY_OPEN_COUNT";
  private static final String KEY_DRAW_OVER_DIALOG_SHOWED = "key_draw_over_dialog_status";
  private static final String KEY_EDIT_CROP_FTUE_SHOWN = "KEY_EDIT_CROP_FTUE_SHOWN";
  private static final String KEY_FIRST_SEND_MAIN_ONSTART = "KEY_FIRST_SEND_MAIN_ONSTART";
  private static final String KEY_IS_BACK_PRESSED_ON_TUTORIAL_SERVICE_ACCESSIBILITY = "KEY_IS_BACK_PRESSED_ON_TUTORIAL_SERVICE_ACCESSIBILITY";
  private static final String KEY_IS_BACK_PRESSED_ON_TUTORIAL_SERVICE_INPUT_SETTING = "KEY_IS_BACK_PRESSED_ON_TUTORIAL_SERVICE_INPUT_SETTING";
  private static final String KEY_IS_FIRST_TIME_FAVORITE_ICON_USER = "KEY_IS_FIRST_TIME_FAVORITE_ICON_USER";
  private static final String KEY_IS_KEYBOARD_ID_PAIRED_WITH_GCM_ID = "KEY_IS_KEYBOARD_ID_PAIRED_WITH_GCM_ID";
  private static final String KEY_LAST_SENT_GIF_URI_MMS = "key_last_sent_gif_uri_mms";
  private static final String KEY_NAVIGATE_BACK_FROM_ACTIVITY = "KEY_NAVIGATE_BACK_FROM_ACTIVITY";
  public static final String KEY_NEW_PROFILE_PHOTO_URI = "KEY_NEW_PROFILE_PHOTO_URI";
  private static final String KEY_NOTIFICATION_ID = "KEY_NOTIFICATION_ID";
  private static final String KEY_NOTIFICATION_TIMESTAMP = "key_notification_timestamp";
  private static final String KEY_PERSONALIZED_TAGS = "key_personalized_tags";
  private static final String KEY_RECORD_CHATHEAD_FTUE_BUBBLE_SHOWING = "KEY_RECORD_CHATHEAD_FTUE_BUBBLE_SHOWING";
  private static final String KEY_RECORD_CHATHEAD_FTUE_SHOWN = "KEY_RECORD_CHATHEAD_FTUE_SHOWN";
  private static final String KEY_RECORD_CHATHEAD_X_POS = "KEY_RECORD_CHATHEAD_IS_X_RIGHT";
  private static final String KEY_RECORD_CHATHEAD_Y_POS = "KEY_RECORD_CHATHEAD_Y_POS";
  private static final String KEY_SHARED_OUTPUT_INDEX = "key_shared_output_index";
  private static final String KEY_SHARES_UPLOAD_NOTIFICATION_TIMESTAMP = "key_shares_upload_notification_timestamp";
  private static final String KEY_SLACK_NOTIFICATION_TIMESTAMP = "key_slack_notification_timestamp";
  private static final String KEY_TRY_UPLOAD_NOTIFICATION_TIMESTAMP = "key_try_upload_notification_timestamp";
  private static final String KEY_UUID = "KEY_UUID";
  public static final int TWITTER_UPSELL_INTERVAL = 50;
  
  public SessionUtils() {}
  
  public static String getAndroidAdvertiseId()
  {
    return getAndroidAdvertiseId(RiffsyApp.getInstance());
  }
  
  public static String getContainingSearchTag()
  {
    return getPreferences().getString("KEY_CONTAINING_SEARCH_TAG", "");
  }
  
  @Deprecated
  public static String getContainingSearchTag1()
  {
    return getPreferences().getString("KEY_CONTAINING_SEARCH_TAG1", "");
  }
  
  @Deprecated
  public static String getContainingSearchTag2()
  {
    return getPreferences().getString("KEY_CONTAINING_SEARCH_TAG2", "");
  }
  
  public static String getGoogleCloudMessagingId()
  {
    return getGoogleCloudMessagingId(RiffsyApp.getInstance());
  }
  
  @NonNull
  public static Set<String> getInstalledPackages()
  {
    Object localObject1;
    try
    {
      localObject1 = getInstalledPackages(RiffsyApp.getInstance());
      return localObject1;
    }
    catch (JsonSyntaxException localJsonSyntaxException) {}
    try
    {
      localObject1 = getPreferences(RiffsyApp.getInstance()).getString("KEY_INSTALLED_PACKAGES_SET_ID", "");
      localObject1 = StringConstant.getOrEmpty(localJsonSyntaxException.getMessage()) + "|" + (String)localObject1;
      if (localJsonSyntaxException.getCause() != null)
      {
        localObject1 = new JsonSyntaxException((String)localObject1, localJsonSyntaxException.getCause());
        CrashlyticsHelper.logException((Throwable)localObject1);
        return new HashSet();
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Object localObject2 = "";
        continue;
        localObject2 = new JsonSyntaxException((String)localObject2);
        ((JsonSyntaxException)localObject2).setStackTrace(Thread.currentThread().getStackTrace());
      }
    }
  }
  
  public static String getKeyboardId()
  {
    return getAnonId(RiffsyApp.getInstance());
  }
  
  public static String getNewProfilePhotoUri()
  {
    return getPreferences().getString("KEY_NEW_PROFILE_PHOTO_URI", "");
  }
  
  public static String getNotificationId()
  {
    return getPreferences().getString("KEY_NOTIFICATION_ID", "");
  }
  
  private static SharedPreferences getPreferences()
  {
    return getPreferences(RiffsyApp.getInstance());
  }
  
  public static int getRecordChatheadXPos()
  {
    return getPreferences().getInt("KEY_RECORD_CHATHEAD_IS_X_RIGHT", Integer.MAX_VALUE);
  }
  
  public static int getRecordChatheadYPos(Context paramContext)
  {
    return getPreferences().getInt("KEY_RECORD_CHATHEAD_Y_POS", (int)(UIUtils.getScreenHeight(paramContext) * 0.8D));
  }
  
  public static int getSavedAppVersion()
  {
    return getPreferences().getInt("key_app_version", 0);
  }
  
  public static boolean hasEditCropFtueShown()
  {
    return getPreferences().getBoolean("KEY_EDIT_CROP_FTUE_SHOWN", false);
  }
  
  public static boolean hasGoogleCloudMessagingId()
  {
    return !TextUtils.isEmpty(getGoogleCloudMessagingId());
  }
  
  public static boolean hasKeyboardId()
  {
    return !TextUtils.isEmpty(getKeyboardId());
  }
  
  public static boolean hasRecordChatheadFtueShown()
  {
    return getPreferences().getBoolean("KEY_RECORD_CHATHEAD_FTUE_SHOWN", false);
  }
  
  public static void incrementDetailsActivityOpenCount()
  {
    int j = getPreferences().getInt("KEY_DETAILS_ACTIVITY_OPEN_COUNT", 0) + 1;
    int i = j;
    if (j >= 50) {
      i = 0;
    }
    getPreferences().edit().putInt("KEY_DETAILS_ACTIVITY_OPEN_COUNT", i).apply();
  }
  
  public static boolean isFirstSendShown()
  {
    return getPreferences().getBoolean("KEY_FIRST_SEND_MAIN_ONSTART", false);
  }
  
  public static boolean isFirstTimeFavoriteIconUser()
  {
    return getPreferences().getBoolean("KEY_IS_FIRST_TIME_FAVORITE_ICON_USER", true);
  }
  
  public static boolean isKeyboardIdPairedWithGCMId()
  {
    return getPreferences().getBoolean("KEY_IS_KEYBOARD_ID_PAIRED_WITH_GCM_ID", false);
  }
  
  public static boolean isNavigateBackFromActivity()
  {
    return getPreferences().getBoolean("KEY_NAVIGATE_BACK_FROM_ACTIVITY", false);
  }
  
  public static boolean isRecordChatheadFtueBubbleShowing()
  {
    return getPreferences().getBoolean("KEY_RECORD_CHATHEAD_FTUE_BUBBLE_SHOWING", false);
  }
  
  public static boolean isShowTwitterUpsell()
  {
    return getPreferences().getInt("KEY_DETAILS_ACTIVITY_OPEN_COUNT", 0) == 1;
  }
  
  public static boolean isTryUploadNotificationAvailable()
  {
    long l = getPreferences().getLong("key_try_upload_notification_timestamp", 0L);
    if (l < 0L) {}
    while (l < System.currentTimeMillis()) {
      return false;
    }
    return true;
  }
  
  public static void removeDeprecatedKeys()
  {
    try
    {
      remove(RiffsyApp.getInstance(), new String[] { "KEY_UUID", "key_personalized_tags", "KEY_IS_BACK_PRESSED_ON_TUTORIAL_SERVICE_INPUT_SETTING", "key_shared_output_index", "key_last_sent_gif_uri_mms", "key_notification_timestamp", "key_accessibility_dialog_status", "key_draw_over_dialog_status", "key_shares_upload_notification_timestamp", "KEY_IS_BACK_PRESSED_ON_TUTORIAL_SERVICE_ACCESSIBILITY", "key_slack_notification_timestamp" });
      return;
    }
    catch (Exception localException) {}
  }
  
  public static void setContainingSearchTag(String paramString)
  {
    getPreferences().edit().putString("KEY_CONTAINING_SEARCH_TAG", paramString).apply();
  }
  
  @Deprecated
  public static void setContainingSearchTag1(String paramString)
  {
    getPreferences().edit().putString("KEY_CONTAINING_SEARCH_TAG1", paramString).apply();
  }
  
  @Deprecated
  public static void setContainingSearchTag2(String paramString)
  {
    getPreferences().edit().putString("KEY_CONTAINING_SEARCH_TAG2", paramString).apply();
  }
  
  public static void setFirstSendShown(boolean paramBoolean)
  {
    getPreferences().edit().putBoolean("KEY_FIRST_SEND_MAIN_ONSTART", paramBoolean).apply();
  }
  
  public static void setFirstTimeFavoriteIconUser(boolean paramBoolean)
  {
    getPreferences().edit().putBoolean("KEY_IS_FIRST_TIME_FAVORITE_ICON_USER", paramBoolean).apply();
  }
  
  public static void setHasEditCropFtueShown(boolean paramBoolean)
  {
    getPreferences().edit().putBoolean("KEY_EDIT_CROP_FTUE_SHOWN", paramBoolean).apply();
  }
  
  public static void setHasRecordChatheadFtueShown(boolean paramBoolean)
  {
    getPreferences().edit().putBoolean("KEY_RECORD_CHATHEAD_FTUE_SHOWN", paramBoolean).apply();
  }
  
  public static void setInstalledPackages(@Nullable Set<String> paramSet)
  {
    setInstalledPackages(RiffsyApp.getInstance(), paramSet);
  }
  
  public static void setIsRecordChatheadFtueBubbleShowing(boolean paramBoolean)
  {
    getPreferences().edit().putBoolean("KEY_RECORD_CHATHEAD_FTUE_BUBBLE_SHOWING", paramBoolean).apply();
  }
  
  public static void setKeyboardId(@Nullable String paramString)
  {
    setAnonId(RiffsyApp.getInstance(), paramString);
  }
  
  public static void setKeyboardIdPairedWithGCMId(boolean paramBoolean)
  {
    getPreferences().edit().putBoolean("KEY_IS_KEYBOARD_ID_PAIRED_WITH_GCM_ID", paramBoolean).apply();
  }
  
  public static void setNavigateBackFromActivity(boolean paramBoolean)
  {
    getPreferences().edit().putBoolean("KEY_NAVIGATE_BACK_FROM_ACTIVITY", paramBoolean).apply();
  }
  
  public static void setNewProfilePhotoUri(String paramString)
  {
    getPreferences().edit().putString("KEY_NEW_PROFILE_PHOTO_URI", paramString).apply();
  }
  
  public static void setNextTryUploadNotification(long paramLong)
  {
    getPreferences().edit().putLong("key_try_upload_notification_timestamp", paramLong).apply();
  }
  
  public static void setNotificationId(String paramString)
  {
    getPreferences().edit().putString("KEY_NOTIFICATION_ID", paramString).apply();
  }
  
  public static void setRecordChatheadXPos(int paramInt)
  {
    getPreferences().edit().putInt("KEY_RECORD_CHATHEAD_IS_X_RIGHT", paramInt).apply();
  }
  
  public static void setRecordChatheadYPos(int paramInt)
  {
    getPreferences().edit().putInt("KEY_RECORD_CHATHEAD_Y_POS", paramInt).apply();
  }
  
  public static void updateAppVersion()
  {
    getPreferences().edit().putInt("key_app_version", 236).apply();
  }
}
