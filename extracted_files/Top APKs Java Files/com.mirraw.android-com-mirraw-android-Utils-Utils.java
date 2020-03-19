package com.mirraw.android.Utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;
import com.localytics.android.Localytics;
import com.localytics.android.Localytics.ProfileScope;
import com.mirraw.android.Mirraw;
import com.mirraw.android.managers.CrashReportManager;
import com.mirraw.android.managers.EventManager;
import com.mirraw.android.sharedresources.Logger;
import com.mirraw.android.ui.activities.TabbedHomeActivity;
import com.mirraw.android.ui.widgets.MaterialImageLoading;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.util.LinkProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.ShortcutBadger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
  private static final String TAG = Utils.class.getSimpleName();
  static String appUrl;
  static Uri bmpUri;
  static String wishlistShareUrl;
  
  public Utils() {}
  
  public static String addHttpSchemeIfMissing(String paramString)
  {
    String str = paramString;
    if (!paramString.toLowerCase().matches("^\\w+://.*")) {
      str = "http://" + paramString;
    }
    return str;
  }
  
  public static int adjustAlpha(int paramInt, float paramFloat)
  {
    return Color.argb(Math.round(Color.alpha(paramInt) * paramFloat), Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt));
  }
  
  public static void appReferral(Context paramContext, BottomSheetLayout paramBottomSheetLayout, FragmentManager paramFragmentManager)
  {
    Object localObject1 = new SharedPreferencesManager(Mirraw.getAppContext());
    Object localObject2 = new JSONObject();
    paramBottomSheetLayout = EventManager.getCustomerId();
    String str2 = EventManager.getEmailIdValue();
    String str1 = getAccountableId();
    try
    {
      ((JSONObject)localObject2).put("referring_user_id", paramBottomSheetLayout);
      ((JSONObject)localObject2).put("referring_user_name", str2);
      localObject2 = ((SharedPreferencesManager)localObject1).getUserName();
      localObject1 = ((SharedPreferencesManager)localObject1).getUserImage();
      new BranchUniversalObject().setCanonicalIdentifier("referral/" + paramBottomSheetLayout).setTitle((String)localObject2 + " has sent you money!").setContentDescription((String)localObject2 + " wants you to Earn money by shopping from Mirraw. You can earn more by inviting your friends!").addContentMetadata("userName", (String)localObject2).addContentMetadata("userId", paramBottomSheetLayout).addContentMetadata("referrer_id", str1).addContentMetadata("userImage", (String)localObject1).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("refer_friends").addControlParameter("$deeplink_path", "rewards/").addControlParameter("~campaign", "referral_campaign"), new Utils.3(paramFragmentManager));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CrashReportManager.logException(Integer.valueOf(0), "shareAppUs", "Some issue in putting user id/email", localException);
      }
    }
  }
  
  public static void appReferralInstall(Context paramContext, BottomSheetLayout paramBottomSheetLayout, FragmentManager paramFragmentManager)
  {
    Object localObject1 = new SharedPreferencesManager(Mirraw.getAppContext());
    Object localObject2 = new JSONObject();
    paramBottomSheetLayout = EventManager.getCustomerId();
    String str2 = EventManager.getEmailIdValue();
    String str1 = getAccountableId();
    try
    {
      ((JSONObject)localObject2).put("referring_user_id", paramBottomSheetLayout);
      ((JSONObject)localObject2).put("referring_user_name", str2);
      localObject2 = ((SharedPreferencesManager)localObject1).getUserName();
      localObject1 = ((SharedPreferencesManager)localObject1).getUserImage();
      new BranchUniversalObject().setCanonicalIdentifier("referral_install/" + paramBottomSheetLayout).setTitle((String)localObject2 + " has sent you money!").setContentDescription((String)localObject2 + " wants you to Earn money by registering with Mirraw !").addContentMetadata("userName", (String)localObject2).addContentMetadata("userId", paramBottomSheetLayout).addContentMetadata("referrer_id", str1).addContentMetadata("userImage", (String)localObject1).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("referral_install").addControlParameter("$deeplink_path", "rewards_install/").addControlParameter("~campaign", "referral_campaign"), new Utils.4(paramFragmentManager));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CrashReportManager.logException(Integer.valueOf(0), "shareAppUs", "Some issue in putting user id/email", localException);
      }
    }
  }
  
  public static void applyNotificationCount(Boolean paramBoolean, Integer paramInteger)
  {
    String str = Build.MANUFACTURER;
    if ((paramBoolean.booleanValue()) && (!str.equalsIgnoreCase("xiaomi"))) {}
    try
    {
      ShortcutBadger.applyCountOrThrow(Mirraw.getAppContext(), paramInteger.intValue());
      Logger.d(TAG, "Count Applied Successfully");
      paramBoolean = new HashMap();
      paramBoolean.put(EventManager.getEmailIdKey(), EventManager.getEmailIdValue());
      paramBoolean.put("Notification Badge Count", String.valueOf(paramInteger));
      EventManager.tagEvent("Notification Badge Applied Successfully", paramBoolean);
      new SharedPreferencesManager(Mirraw.getAppContext()).setShouldRemoveNotificationBadge(Boolean.valueOf(true));
      return;
    }
    catch (ShortcutBadgeException paramBoolean)
    {
      paramBoolean.printStackTrace();
      Logger.d(TAG, "Count Applied Failed");
      paramBoolean = new HashMap();
      paramBoolean.put(EventManager.getEmailIdKey(), EventManager.getEmailIdValue());
      paramBoolean.put("Notification Badge Count", String.valueOf(paramInteger));
      EventManager.tagEvent("Notification Badge Apply Failed", paramBoolean);
      EventManager.NOTIFICATION_COUNT = Integer.valueOf(0);
      new SharedPreferencesManager(Mirraw.getAppContext()).setShouldRemoveNotificationBadge(Boolean.valueOf(false));
    }
  }
  
  public static boolean bundleFromLocalytics(Bundle paramBundle)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramBundle != null)
    {
      bool1 = bool2;
      if (paramBundle.getString("bundleFrom") != null)
      {
        bool1 = bool2;
        if (paramBundle.getString("bundleFrom").equalsIgnoreCase("localytics")) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static void contactSupport(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:androidapp@mirraw.com"));
    localIntent.putExtra("android.intent.extra.SUBJECT", "Unable to place order");
    paramContext.startActivity(Intent.createChooser(localIntent, "Select Mail Client"));
  }
  
  public static void copyToClipboard(Context paramContext, String paramString1, String paramString2)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramString1, paramString2));
  }
  
  public static void deleteShareFile(ArrayList<Uri> paramArrayList)
  {
    if (paramArrayList.size() > 0)
    {
      int i = 0;
      while (i < paramArrayList.size())
      {
        Object localObject = (Uri)paramArrayList.get(i);
        if (localObject != null)
        {
          localObject = new File(((Uri)localObject).getPath());
          if (localObject != null) {
            ((File)localObject).delete();
          }
        }
        i += 1;
      }
    }
  }
  
  private static Account getAccount(AccountManager paramAccountManager)
  {
    if (ActivityCompat.checkSelfPermission(Mirraw.getAppContext(), "android.permission.GET_ACCOUNTS") == 0)
    {
      paramAccountManager = paramAccountManager.getAccountsByType("com.google");
      if (paramAccountManager.length > 0) {
        return paramAccountManager[0];
      }
      return null;
    }
    return null;
  }
  
  public static String getAccountableId()
  {
    Object localObject = new SharedPreferencesManager(Mirraw.getAppContext());
    try
    {
      JSONObject localJSONObject = new JSONObject(new JSONObject(((SharedPreferencesManager)localObject).getLoginResponse()).getString("mBody"));
      String str = new JSONObject(localJSONObject.get("data").toString()).getString("accountable_type");
      localObject = null;
      if (str.equalsIgnoreCase("User"))
      {
        int i = new JSONObject(localJSONObject.get("data").toString()).getInt("accountable_id");
        localObject = String.valueOf(i);
      }
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Crashlytics.logException(new Throwable(TAG + " Accountable ID issue" + "\n" + localException.toString()));
    }
    return null;
  }
  
  public static int getAge(int paramInt1, int paramInt2, int paramInt3)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    int k = localGregorianCalendar.get(1);
    int i = localGregorianCalendar.get(2);
    int j = localGregorianCalendar.get(5);
    localGregorianCalendar.set(paramInt1, paramInt2, paramInt3);
    paramInt2 = k - localGregorianCalendar.get(1);
    if (i >= localGregorianCalendar.get(2))
    {
      paramInt1 = paramInt2;
      if (i == localGregorianCalendar.get(2))
      {
        paramInt1 = paramInt2;
        if (j >= localGregorianCalendar.get(5)) {}
      }
    }
    else
    {
      paramInt1 = paramInt2 - 1;
    }
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Age < 0");
    }
    return paramInt1;
  }
  
  private static Account[] getAllAccounts(AccountManager paramAccountManager)
  {
    if (ActivityCompat.checkSelfPermission(Mirraw.getAppContext(), "android.permission.GET_ACCOUNTS") == 0) {
      return paramAccountManager.getAccountsByType("com.google");
    }
    return null;
  }
  
  public static String getAllEmails(Context paramContext)
  {
    Account[] arrayOfAccount = getAllAccounts(AccountManager.get(paramContext));
    paramContext = "";
    if (arrayOfAccount == null) {
      return "";
    }
    int j = arrayOfAccount.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfAccount[i].name;
      paramContext = paramContext + " " + str;
      i += 1;
    }
    return paramContext.replaceFirst(" ", "");
  }
  
  public static String getCurrencySymbol(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = String.valueOf(Character.toChars((char)Integer.parseInt(paramString1, 16)));
      return paramString1;
    }
    catch (Exception paramString1)
    {
      do
      {
        CrashReportManager.logException(Integer.valueOf(1), TAG, "currency symbol encoding", paramString1);
        if (paramString2 == null) {
          break;
        }
        paramString1 = paramString2;
      } while (!TextUtils.isEmpty(paramString2));
    }
    return paramString3;
  }
  
  public static String getEmail(Context paramContext)
  {
    paramContext = getAccount(AccountManager.get(paramContext));
    if (paramContext == null) {
      return "None";
    }
    return paramContext.name;
  }
  
  public static String getEmailFromDevice()
  {
    Object localObject = "None";
    if (ContextCompat.checkSelfPermission(Mirraw.getAppContext(), "android.permission.GET_ACCOUNTS") == 0)
    {
      String str = getAllEmails(Mirraw.getAppContext());
      localObject = str;
      if (str.contains(" ")) {
        localObject = str.replaceAll(" ", ",");
      }
    }
    return localObject;
  }
  
  public static StringBuilder getGoogleIds(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      if (i == paramArrayOfString.length - 1) {
        localStringBuilder.append(paramArrayOfString[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(paramArrayOfString[i]).append(",");
      }
    }
    return localStringBuilder;
  }
  
  public static Uri getLocalBitmapUri(ImageView paramImageView, Uri paramUri)
  {
    paramUri = ImageRequestBuilder.newBuilderWithSource(paramUri).setAutoRotateEnabled(true).build();
    paramImageView = Fresco.getImagePipeline().fetchDecodedImage(paramUri, paramImageView.getContext());
    bmpUri = null;
    paramImageView.subscribe(new Utils.10(paramImageView), CallerThreadExecutor.getInstance());
    try
    {
      Thread.sleep(500L);
      return bmpUri;
    }
    catch (InterruptedException paramImageView)
    {
      for (;;)
      {
        paramImageView.printStackTrace();
      }
    }
  }
  
  public static NotificationManager getNotificationManager()
  {
    return (NotificationManager)Mirraw.getAppContext().getSystemService("notification");
  }
  
  public static String getPhoneNumber(Context paramContext)
  {
    if (ContextCompat.checkSelfPermission(Mirraw.getAppContext(), "android.permission.READ_PHONE_STATE") == 0) {
      return ((TelephonyManager)paramContext.getSystemService("phone")).getLine1Number();
    }
    return null;
  }
  
  public static String getTimeRemaining(Long paramLong)
  {
    Object localObject2 = Long.valueOf(paramLong.longValue() / 1000L);
    Object localObject1 = Long.valueOf(((Long)localObject2).longValue() / 60L);
    paramLong = Long.valueOf(((Long)localObject1).longValue() / 60L);
    long l = paramLong.longValue() / 24L;
    localObject1 = Long.valueOf(((Long)localObject1).longValue() % 60L);
    if (paramLong.longValue() < 10L)
    {
      paramLong = "0" + paramLong;
      if (((Long)localObject1).longValue() >= 10L) {
        break label216;
      }
      localObject1 = "0" + localObject1;
      label123:
      localObject2 = Long.valueOf(((Long)localObject2).longValue() % 60L);
      if (((Long)localObject2).longValue() >= 10L) {
        break label224;
      }
    }
    label216:
    label224:
    for (localObject2 = "0" + localObject2;; localObject2 = String.valueOf(localObject2))
    {
      return paramLong + ":" + (String)localObject1 + ":" + (String)localObject2;
      paramLong = String.valueOf(paramLong);
      break;
      localObject1 = String.valueOf(localObject1);
      break label123;
    }
  }
  
  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 2);
  }
  
  public static void imageViewFadeInAnimation(ImageView paramImageView)
  {
    ObjectAnimator.ofFloat(paramImageView, View.ALPHA, new float[] { 0.2F, 1.0F }).setDuration(1200L).start();
  }
  
  public static boolean isLocationEnabled(Context paramContext)
  {
    paramContext = (LocationManager)paramContext.getSystemService("location");
    boolean bool = paramContext.isProviderEnabled("network");
    return (paramContext.isProviderEnabled("gps")) || (bool);
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static void materialImageAnimate(ImageView paramImageView)
  {
    MaterialImageLoading.animate(paramImageView).setDuration(1000).start();
  }
  
  public static void rateTheApp(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.mirraw.android&ah=j34bb71YH4YjqZhZjRjH_xR-6_M"));
    localIntent.addFlags(1208483840);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.mirraw.android&ah=j34bb71YH4YjqZhZjRjH_xR-6_M")));
      Crashlytics.logException(new Throwable(TAG + " " + "\n" + localActivityNotFoundException.toString()));
    }
  }
  
  public static JSONArray removeJsonObjectAtJsonArrayIndex(JSONArray paramJSONArray, int paramInt)
    throws JSONException
  {
    if ((paramInt < 0) || (paramInt > paramJSONArray.length() - 1)) {
      throw new IndexOutOfBoundsException();
    }
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    int j = paramJSONArray.length();
    while (i < j)
    {
      if (i != paramInt) {
        localJSONArray.put(paramJSONArray.get(i));
      }
      i += 1;
    }
    return localJSONArray;
  }
  
  public static double round(double paramDouble, int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException();
    }
    long l = Math.pow(10.0D, paramInt);
    return Math.round(paramDouble * l) / l;
  }
  
  public static void saveDeviceEmails()
  {
    new SharedPreferencesManager(Mirraw.getAppContext()).setDeviceEmails(getAllEmails(Mirraw.getAppContext()));
  }
  
  public static void saveRegisteredEmail(String paramString)
  {
    SharedPreferencesManager localSharedPreferencesManager = new SharedPreferencesManager(Mirraw.getAppContext());
    String str = localSharedPreferencesManager.getRegisteredEmails();
    String[] arrayOfString = str.split(" ");
    int k = 0;
    int m = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < m)
      {
        if (arrayOfString[i].equals(paramString)) {
          j = 1;
        }
      }
      else
      {
        if (j == 0) {
          break;
        }
        return;
      }
      i += 1;
    }
    if (str.equals(""))
    {
      localSharedPreferencesManager.setRegisteredEmails(paramString);
      return;
    }
    localSharedPreferencesManager.setRegisteredEmails(paramString + " " + str);
  }
  
  public static void saveRegisteredEmails(String paramString)
  {
    SharedPreferencesManager localSharedPreferencesManager = new SharedPreferencesManager(Mirraw.getAppContext());
    String str = localSharedPreferencesManager.getRegisteredEmails();
    String[] arrayOfString = str.split(" ");
    int k = 0;
    int m = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < m)
      {
        if (arrayOfString[i].equals(paramString)) {
          j = 1;
        }
      }
      else
      {
        if (j == 0) {
          break;
        }
        return;
      }
      i += 1;
    }
    if (str.equals(""))
    {
      localSharedPreferencesManager.setRegisteredEmails(paramString);
      return;
    }
    localSharedPreferencesManager.setRegisteredEmails(paramString + " " + str);
  }
  
  public static void sendFeedback(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:androidapp@mirraw.com"));
    localIntent.putExtra("android.intent.extra.SUBJECT", "App Feedback");
    paramContext.startActivity(Intent.createChooser(localIntent, "Select Mail Client"));
  }
  
  public static void sendPaymentQuery(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:team@mirraw.com"));
      localIntent.putExtra("android.intent.extra.SUBJECT", "Payment Query");
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      paramContext.startActivity(Intent.createChooser(localIntent, "Select Mail Client"));
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Toast.makeText(paramContext, "There is no email client installed.", 0).show();
    }
  }
  
  public static void setBranchIdentity()
  {
    String str = getAccountableId();
    if (!str.equalsIgnoreCase(""))
    {
      Logger.d(TAG, str);
      Branch.getInstance(Mirraw.getAppContext()).setIdentity(str, new Utils.12());
    }
  }
  
  public static void setInstalledAppsToProfile(Context paramContext)
  {
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    String[] arrayOfString1 = new String[localList.size()];
    String[] arrayOfString2 = new String[localList.size()];
    int i = 0;
    while (i < localList.size())
    {
      if ((((PackageInfo)localList.get(i)).applicationInfo.flags & 0x1) != 0)
      {
        String str = ((PackageInfo)localList.get(i)).packageName;
        arrayOfString1[i] = String.valueOf(((PackageInfo)localList.get(i)).applicationInfo.loadLabel(paramContext.getPackageManager()));
        arrayOfString2[i] = str;
      }
      i += 1;
    }
    Localytics.setProfileAttribute("Installed Apps Package", arrayOfString2, Localytics.ProfileScope.ORGANIZATION);
    Localytics.setProfileAttribute("Installed Apps", arrayOfString1, Localytics.ProfileScope.ORGANIZATION);
  }
  
  public static void shareApp(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    JSONObject localJSONObject = new JSONObject();
    String str1 = EventManager.getCustomerId();
    String str2 = EventManager.getEmailIdValue();
    try
    {
      localJSONObject.put("referring_user_id", str1);
      localJSONObject.put("referring_user_name", str2);
      localIntent.putExtra("android.intent.extra.TEXT", "Hey check out awesome ethnic wears at: " + "https://play.google.com/store/apps/details?id=com.mirraw.android");
      localIntent.setType("text/plain");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CrashReportManager.logException(Integer.valueOf(0), "shareApp", "Some issue in putting user id/email", localException);
        Crashlytics.logException(new Throwable(TAG + " Issue in putting user id/email" + "\n" + localException.toString()));
      }
    }
  }
  
  public static void shareAppByBranch(Context paramContext)
  {
    Object localObject1 = new SharedPreferencesManager(Mirraw.getAppContext());
    Object localObject2 = new JSONObject();
    String str1 = EventManager.getCustomerId();
    String str2 = EventManager.getEmailIdValue();
    try
    {
      ((JSONObject)localObject2).put("referring_user_id", str1);
      ((JSONObject)localObject2).put("referring_user_name", str2);
      localObject2 = ((SharedPreferencesManager)localObject1).getUserName();
      str2 = ((SharedPreferencesManager)localObject1).getUserImage();
      localObject1 = localObject2;
      if (((String)localObject2).equalsIgnoreCase("")) {
        localObject1 = "Your friend";
      }
      new BranchUniversalObject().setCanonicalIdentifier("invite/" + str1).setTitle((String)localObject1 + " has invited you !").setContentDescription((String)localObject1 + " wants you to download Mirraw App and have a look at awesome designs in their catalog").addContentMetadata("userName", (String)localObject1).addContentMetadata("userId", str1).addContentMetadata("referrer_id", getAccountableId()).addContentMetadata("userImage", str2).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("app_invite").addControlParameter("$deeplink_path", "share_app/").addControlParameter("~campaign", "app_share"), new Utils.11(paramContext));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CrashReportManager.logException(Integer.valueOf(0), "shareAppUs", "Some issue in putting user id/email", localException);
        Crashlytics.logException(new Throwable(TAG + " Some issue in putting user id/email" + "\n" + localException.toString()));
      }
    }
  }
  
  public static void shareAppOnFacebook(Activity paramActivity)
  {
    Object localObject2 = EventManager.getCustomerId();
    SharedPreferencesManager localSharedPreferencesManager = new SharedPreferencesManager(Mirraw.getAppContext());
    Object localObject1 = "Your friend";
    if (localSharedPreferencesManager.getLoggedIn()) {
      localObject1 = localSharedPreferencesManager.getUserName();
    }
    String str = localSharedPreferencesManager.getUserImage();
    localObject1 = new BranchUniversalObject().setCanonicalIdentifier("invite/" + (String)localObject2).setTitle((String)localObject1 + " has invited you !").setContentDescription((String)localObject1 + " wants you to download Mirraw App and have a look at awesome designs in their catalog").addContentMetadata("userName", (String)localObject1).addContentMetadata("userId", (String)localObject2).addContentMetadata("referrer_id", getAccountableId()).addContentMetadata("userImage", str);
    localObject2 = new LinkProperties().setChannel("facebook").setFeature("app_invite_facebook").addControlParameter("$deeplink_path", "rewards/").addControlParameter("~campaign", "app_facebook_share");
    str = localSharedPreferencesManager.getShareAppOnFbBanner();
    ((BranchUniversalObject)localObject1).generateShortUrl(Mirraw.getAppContext(), (LinkProperties)localObject2, new Utils.5(str, paramActivity));
    localSharedPreferencesManager.clearShareAppOnFbBanner();
  }
  
  public static void shareAppUs(Context paramContext)
  {
    Object localObject1 = new SharedPreferencesManager(Mirraw.getAppContext());
    Object localObject2 = new JSONObject();
    String str1 = EventManager.getCustomerId();
    String str2 = EventManager.getEmailIdValue();
    try
    {
      ((JSONObject)localObject2).put("referring_user_id", str1);
      ((JSONObject)localObject2).put("referring_user_name", str2);
      localObject2 = ((SharedPreferencesManager)localObject1).getUserName();
      str2 = ((SharedPreferencesManager)localObject1).getUserImage();
      localObject1 = localObject2;
      if (((String)localObject2).equalsIgnoreCase("")) {
        localObject1 = "Your friend";
      }
      new BranchUniversalObject().setCanonicalIdentifier("invite/" + str1).setTitle((String)localObject1 + " has invited you !").setContentDescription((String)localObject1 + " wants you to download Mirraw App and have a look at awesome designs in their catalog").addContentMetadata("userName", (String)localObject1).addContentMetadata("userId", str1).addContentMetadata("referrer_id", getAccountableId()).addContentMetadata("userImage", str2).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("app_invite").addControlParameter("$deeplink_path", "rewards/").addControlParameter("~campaign", "app_share"), new Utils.2(paramContext));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CrashReportManager.logException(Integer.valueOf(0), "shareAppUs", "Some issue in putting user id/email", localException);
        Crashlytics.logException(new Throwable(TAG + " Some issue in putting user id/email" + "\n" + localException.toString()));
      }
    }
  }
  
  public static void shareDesigner(Activity paramActivity, String paramString1, String paramString2)
  {
    Object localObject1 = new SharedPreferencesManager(Mirraw.getAppContext());
    Object localObject2 = ((SharedPreferencesManager)localObject1).getUserName();
    long l = ((SharedPreferencesManager)localObject1).getUserId();
    localObject1 = new BranchUniversalObject().setCanonicalIdentifier("designer_share/" + paramString1).setTitle((String)localObject2 + " wants you to checkout " + paramString2 + " on Mirraw").setContentDescription((String)localObject2 + " wants you to checkout " + paramString2 + " on Mirraw").addContentMetadata("userName", (String)localObject2).addContentMetadata("userId", String.valueOf(l)).addContentMetadata("designerId", paramString1).addContentMetadata("designerName", paramString2);
    localObject2 = new LinkProperties().setChannel("android_app").setFeature("designer_share").addControlParameter("$deeplink_path", "shared_designer/").addControlParameter("~campaign", "app_designer_share");
    ((BranchUniversalObject)localObject1).generateShortUrl(Mirraw.getAppContext(), (LinkProperties)localObject2, new Utils.7(paramString1, paramString2, paramActivity));
  }
  
  public static void shareProduct(Context paramContext, File paramFile, String paramString1, String paramString2, String paramString3, BottomSheetLayout paramBottomSheetLayout, Uri paramUri)
  {
    String str = "Hey, check out " + paramString1 + " on Mirraw!" + "\n" + paramString2;
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.TEXT", str);
    localIntent.setType("text/plain");
    paramBottomSheetLayout.showWithSheetView(new IntentPickerSheetView(paramContext, localIntent, "Share this product", new Utils.9(paramBottomSheetLayout, paramContext, localIntent, paramFile, paramUri, paramString1, paramString2, paramString3, str)));
  }
  
  public static void shareProductWithoutImage(Context paramContext, String paramString1, String paramString2, BottomSheetLayout paramBottomSheetLayout)
  {
    paramString1 = "Hey, check out " + paramString1 + " on Mirraw!" + "\n" + paramString2;
    paramString2 = new Intent();
    paramString2.setAction("android.intent.action.SEND");
    paramString2.putExtra("android.intent.extra.TEXT", paramString1);
    paramString2.putExtra("android.intent.extra.SUBJECT", "Check out this awesome product on Mirraw!");
    paramString2.setType("text/plain");
    paramBottomSheetLayout.showWithSheetView(new IntentPickerSheetView(paramContext, paramString2, "Share this product", new Utils.8(paramBottomSheetLayout, paramContext, paramString2)));
  }
  
  public static void shareWishlist(Activity paramActivity)
  {
    Object localObject1 = getAccountableId();
    Object localObject2 = "https://api.mirraw.com/api/v1/user/wishlist_designs?user_id=" + (String)localObject1;
    SharedPreferencesManager localSharedPreferencesManager = new SharedPreferencesManager(Mirraw.getAppContext());
    String str = localSharedPreferencesManager.getUserName();
    localObject1 = new BranchUniversalObject().setCanonicalIdentifier("wishlist_share/" + (String)localObject1).setTitle(str + " wants you to check out their Wishlist").setContentDescription(str + " wants you to check out their Wishlist").addContentMetadata("userName", str).addContentMetadata("userId", String.valueOf(localSharedPreferencesManager.getUserId())).addContentMetadata("wishlistUrl", (String)localObject2);
    localObject2 = new LinkProperties().setChannel("android_app").setFeature("wishlist_share").addControlParameter("$deeplink_path", "shared_wishlist/").addControlParameter("~campaign", "app_wishlist_share").addControlParameter("$ios_url", "https://www.mirraw.com");
    ((BranchUniversalObject)localObject1).generateShortUrl(Mirraw.getAppContext(), (LinkProperties)localObject2, new Utils.6(paramActivity));
  }
  
  public static void showSnackBar(String paramString, Activity paramActivity, int paramInt)
  {
    paramString = Snackbar.make(paramActivity.findViewById(16908290), paramString, paramInt).setAction("Action", null);
    paramString.getView().setBackgroundColor(paramActivity.getResources().getColor(2131689557));
    paramString.getView().setPadding(0, 10, 0, 10);
    paramString.show();
  }
  
  public static void showSnackbar(String paramString, Activity paramActivity)
  {
    paramString = Snackbar.make(paramActivity.findViewById(16908290), paramString, -2);
    paramString.setAction("Dismiss", new Utils.1(paramString));
    paramString.getView().setBackgroundColor(paramActivity.getResources().getColor(2131689557));
    paramString.getView().setPadding(0, 10, 0, 10);
    paramString.show();
  }
  
  public static void startTabbedHomeActivityWithTarget(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent(Mirraw.getAppContext(), TabbedHomeActivity.class);
    localIntent.putExtra("target", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  public static String trimVolleyErrorMessage(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new JSONObject(paramString1).getString(paramString2);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.getMessage();
      CrashReportManager.logException(Integer.valueOf(0), TAG, "Volley Error message issue", paramString1);
      Crashlytics.logException(new Throwable(TAG + " Volley error message issue" + "\n" + paramString1.toString()));
    }
    return null;
  }
}
