package com.mirraw.android.Utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.share.model.AppInviteContent.Builder;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareLinkContent.Builder;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView.ActivityInfo;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView.OnIntentPickedListener;
import com.google.firebase.crash.FirebaseCrash;
import com.localytics.android.Localytics.ProfileScope;
import com.mirraw.android.Mirraw;
import com.mirraw.android.managers.CrashReportManager;
import com.mirraw.android.managers.EventManager;
import com.mirraw.android.managers.LocalyticsManager;
import com.mirraw.android.sharedresources.Logger;
import com.mirraw.android.ui.activities.ProductDetailActivity;
import com.mirraw.android.ui.activities.TabbedHomeActivity;
import com.mirraw.android.ui.activities.WebViewActivity;
import com.mirraw.android.ui.fragments.ProductDetailFragment;
import com.mirraw.android.ui.fragments.ProductDetailWithSimilarProductsFragment;
import com.mirraw.android.ui.fragments.ReferralShareDialogFragment;
import com.mirraw.android.ui.widgets.MaterialImageLoading;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.Branch.BranchLinkCreateListener;
import io.branch.referral.Branch.BranchReferralInitListener;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
      new BranchUniversalObject().setCanonicalIdentifier("referral/" + paramBottomSheetLayout).setTitle((String)localObject2 + " has sent you money!").setContentDescription((String)localObject2 + " wants you to Earn money by shopping from Mirraw. You can earn more by inviting your friends!").addContentMetadata("userName", (String)localObject2).addContentMetadata("userId", paramBottomSheetLayout).addContentMetadata("referrer_id", str1).addContentMetadata("userImage", (String)localObject1).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("refer_friends").addControlParameter("$deeplink_path", "rewards/").addControlParameter("~campaign", "referral_campaign"), new Branch.BranchLinkCreateListener()
      {
        public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
        {
          if (paramAnonymousBranchError == null) {
            new ReferralShareDialogFragment(paramAnonymousString).show(this.val$supportFragmentManager, 2131689769);
          }
        }
      });
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
      new BranchUniversalObject().setCanonicalIdentifier("referral_install/" + paramBottomSheetLayout).setTitle((String)localObject2 + " has sent you money!").setContentDescription((String)localObject2 + " wants you to Earn money by registering with Mirraw !").addContentMetadata("userName", (String)localObject2).addContentMetadata("userId", paramBottomSheetLayout).addContentMetadata("referrer_id", str1).addContentMetadata("userImage", (String)localObject1).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("referral_install").addControlParameter("$deeplink_path", "rewards_install/").addControlParameter("~campaign", "referral_campaign"), new Branch.BranchLinkCreateListener()
      {
        public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
        {
          if (paramAnonymousBranchError == null) {
            new ReferralShareDialogFragment(paramAnonymousString).show(this.val$supportFragmentManager, 2131689769);
          }
        }
      });
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
    Logger.d("doublenotificationfix", "Utils : applyNotificationCount() : updating badge");
    String str = Build.MANUFACTURER;
    if ((paramBoolean.booleanValue()) && (!str.equalsIgnoreCase("xiaomi"))) {}
    try
    {
      ShortcutBadger.applyCountOrThrow(Mirraw.getAppContext(), paramInteger.intValue());
      Logger.d("doublenotificationfix", "Count Applied Successfully");
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
      Logger.d("doublenotificationfix", "Count Applied Failed " + paramBoolean.toString());
      paramBoolean = new HashMap();
      paramBoolean.put(EventManager.getEmailIdKey(), EventManager.getEmailIdValue());
      paramBoolean.put("Notification Badge Count", String.valueOf(paramInteger));
      EventManager.tagEvent("Notification Badge Apply Failed", paramBoolean);
      EventManager.NOTIFICATION_COUNT = Integer.valueOf(0);
      new SharedPreferencesManager(Mirraw.getAppContext()).setShouldRemoveNotificationBadge(Boolean.valueOf(false));
    }
  }
  
  public static boolean bundleFromClevertap(Bundle paramBundle)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramBundle != null)
    {
      bool1 = bool2;
      if (paramBundle.getString("bundleFrom") != null)
      {
        bool1 = bool2;
        if (paramBundle.getString("bundleFrom").equalsIgnoreCase("Clevertap")) {
          bool1 = true;
        }
      }
    }
    return bool1;
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
    ((android.content.ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(paramString1, paramString2));
  }
  
  public static void deleteCache(Context paramContext)
  {
    try
    {
      deleteCacheDir(paramContext.getCacheDir());
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean deleteCacheDir(File paramFile)
  {
    if ((paramFile != null) && (paramFile.isDirectory()))
    {
      arrayOfString = paramFile.list();
      i = 0;
      if (i < arrayOfString.length) {
        if (deleteCacheDir(new File(paramFile, arrayOfString[i]))) {}
      }
    }
    while ((paramFile == null) || (!paramFile.isFile()))
    {
      for (;;)
      {
        String[] arrayOfString;
        int i;
        return false;
        i += 1;
      }
      return paramFile.delete();
    }
    return paramFile.delete();
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
  
  public static String getAccountId()
  {
    Object localObject = new SharedPreferencesManager(Mirraw.getAppContext());
    try
    {
      JSONObject localJSONObject = new JSONObject(new JSONObject(((SharedPreferencesManager)localObject).getLoginResponse()).getString("mBody"));
      String str = new JSONObject(localJSONObject.get("data").toString()).getString("accountable_type");
      localObject = null;
      if (str.equalsIgnoreCase("User"))
      {
        int i = new JSONObject(localJSONObject.get("data").toString()).getInt("id");
        localObject = String.valueOf(i);
      }
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Crashlytics.logException(new Throwable(TAG + " Account ID issue\n" + localException.toString()));
      FirebaseCrash.report(new Exception(TAG + " Account ID issue\n" + localException.toString()));
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
      Crashlytics.logException(new Throwable(TAG + " Accountable ID issue\n" + localException.toString()));
      FirebaseCrash.report(new Exception(TAG + " Accountable ID issue\n" + localException.toString()));
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
  
  public static String getApplicationName()
  {
    return Mirraw.getAppContext().getString(2131230845);
  }
  
  public static String getApplicationName(Context paramContext)
  {
    return paramContext.getApplicationInfo().loadLabel(paramContext.getPackageManager()).toString();
  }
  
  public static ConnectionQuality getConnectionBandwidth()
  {
    return ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
  }
  
  public static String getCurrencySymbol(String paramString1, String paramString2, String paramString3)
  {
    String str = "";
    if (paramString1 != null) {}
    for (;;)
    {
      try
      {
        if (!paramString1.trim().equals(""))
        {
          String[] arrayOfString = paramString1.replaceAll(" ", "").split(",");
          int j = arrayOfString.length;
          int i = 0;
          paramString1 = str;
          if (i < j)
          {
            paramString1 = String.valueOf(Character.toChars((char)Integer.parseInt(arrayOfString[i], 16)));
            str = str + paramString1;
            i += 1;
            continue;
          }
        }
        else
        {
          if (paramString2 == null) {
            continue;
          }
          boolean bool = TextUtils.isEmpty(paramString2);
          if (bool) {
            continue;
          }
          paramString1 = paramString2;
        }
      }
      catch (Exception paramString1)
      {
        CrashReportManager.logException(Integer.valueOf(1), TAG, "currency symbol encoding", paramString1);
        if (paramString2 == null) {
          break;
        }
        paramString1 = paramString2;
        if (!TextUtils.isEmpty(paramString2)) {
          continue;
        }
      }
      return paramString1;
      paramString1 = paramString3;
    }
    return paramString3;
  }
  
  public static String getDate(long paramLong, String paramString)
  {
    paramString = new SimpleDateFormat(paramString, Locale.US);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return paramString.format(localCalendar.getTime());
  }
  
  public static Long getDeliveryMillis(Integer paramInteger1, Integer paramInteger2)
  {
    long l1 = System.currentTimeMillis();
    Long localLong = Long.valueOf(86400000L);
    long l3 = paramInteger1.intValue();
    long l4 = localLong.longValue();
    long l2 = Long.valueOf(paramInteger2.intValue() * localLong.longValue()).longValue();
    l3 = Long.valueOf(l3 * l4).longValue();
    return Long.valueOf(Long.valueOf(l1).longValue() + Long.valueOf(l2 + l3).longValue());
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
  
  public static String getFirebaseRemoteConfigKey(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      paramString = paramString.trim();
      str = paramString;
      if (Mirraw.getAppContext().getPackageName().endsWith(".staging")) {
        str = "staging_" + paramString;
      }
    }
    return str;
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
    paramImageView.subscribe(new BaseBitmapDataSubscriber()
    {
      protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> paramAnonymousDataSource) {}
      
      protected void onNewResultImpl(Bitmap paramAnonymousBitmap)
      {
        if ((this.val$dataSource.isFinished()) && (paramAnonymousBitmap != null)) {
          paramAnonymousBitmap = Bitmap.createBitmap(paramAnonymousBitmap);
        }
        try
        {
          File localFile = new File(Mirraw.getAppContext().getFilesDir(), "share_image_" + System.currentTimeMillis() + ".png");
          FileOutputStream localFileOutputStream = Mirraw.getAppContext().openFileOutput("share_image_" + System.currentTimeMillis() + ".png", 1);
          paramAnonymousBitmap.compress(Bitmap.CompressFormat.PNG, 90, localFileOutputStream);
          localFileOutputStream.close();
          Utils.bmpUri = Uri.fromFile(localFile);
          return;
        }
        catch (IOException paramAnonymousBitmap)
        {
          paramAnonymousBitmap.printStackTrace();
          Crashlytics.logException(new Throwable(Utils.TAG + " \n" + paramAnonymousBitmap.toString()));
          FirebaseCrash.report(new Exception(Utils.TAG + " \n" + paramAnonymousBitmap.toString()));
        }
      }
    }, CallerThreadExecutor.getInstance());
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
  
  public static void hideSoftKeyboard(Activity paramActivity)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 0);
  }
  
  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 2);
  }
  
  public static void imageViewFadeInAnimation(ImageView paramImageView)
  {
    ObjectAnimator.ofFloat(paramImageView, View.ALPHA, new float[] { 0.2F, 1.0F }).setDuration(1200L).start();
  }
  
  public static boolean isDebug()
  {
    return false;
  }
  
  public static boolean isDesignerApp(Context paramContext)
  {
    return EventManager.SUB_APP.startsWith("Designer_");
  }
  
  public static boolean isLocationEnabled(Context paramContext)
  {
    paramContext = (LocationManager)paramContext.getSystemService("location");
    boolean bool = paramContext.isProviderEnabled("network");
    return (paramContext.isProviderEnabled("gps")) || (bool);
  }
  
  public static boolean isMainApp(Context paramContext)
  {
    paramContext = paramContext.getPackageName();
    return (paramContext.equalsIgnoreCase("com.mirraw.android")) || (paramContext.equalsIgnoreCase("com.mirraw.android.staging")) || (paramContext.equalsIgnoreCase("com.mirraw.premium")) || (paramContext.equalsIgnoreCase("com.mirraw.premium.staging"));
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static Toast makeCustomToast(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    View localView = ((Activity)paramContext).getLayoutInflater().inflate(2130968964, (ViewGroup)((Activity)paramContext).findViewById(2131691011));
    paramContext = new Toast(paramContext);
    ((TextView)localView.findViewById(2131689489)).setText(paramCharSequence);
    paramContext.setDuration(paramInt);
    paramContext.setGravity(17, 0, 0);
    paramContext.setView(localView);
    return paramContext;
  }
  
  public static void materialImageAnimate(ImageView paramImageView)
  {
    MaterialImageLoading.animate(paramImageView).setDuration(1000).start();
  }
  
  public static void openWebView(Activity paramActivity, Bundle paramBundle, @Nullable String paramString)
  {
    Intent localIntent = new Intent(paramActivity, WebViewActivity.class);
    if (paramString != null) {
      paramBundle.putString("title", paramString);
    }
    localIntent.putExtras(paramBundle);
    paramActivity.startActivity(localIntent);
    paramActivity.overridePendingTransition(2131034139, 2131034140);
  }
  
  public static void rateTheApp(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramContext.getPackageName()));
    localIntent.addFlags(1208483840);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.mirraw.android.jewellery&ah=j34bb71YH4YjqZhZjRjH_xR-6_M")));
      Crashlytics.logException(new Throwable(TAG + " \n" + localActivityNotFoundException.toString()));
      FirebaseCrash.report(new Exception(TAG + " \n" + localActivityNotFoundException.toString()));
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
  
  @TargetApi(16)
  public static void removeOnGlobalLayoutListener(View paramView, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.getViewTreeObserver().removeGlobalOnLayoutListener(paramOnGlobalLayoutListener);
      return;
    }
    paramView.getViewTreeObserver().removeOnGlobalLayoutListener(paramOnGlobalLayoutListener);
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
  
  public static void sendFeedback(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:androidapp@mirraw.com"));
    if (!paramString.equalsIgnoreCase("other")) {
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString);
    }
    localIntent.putExtra("android.intent.extra.TEXT", getApplicationName() + " ( version " + "0.61.1" + " )\n\n");
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
      Branch.getInstance(Mirraw.getAppContext()).setIdentity(str, new Branch.BranchReferralInitListener()
      {
        public void onInitFinished(JSONObject paramAnonymousJSONObject, BranchError paramAnonymousBranchError)
        {
          if (paramAnonymousBranchError != null) {
            Logger.d(Utils.TAG, paramAnonymousJSONObject.toString());
          }
        }
      });
    }
  }
  
  public static void setClipboard(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT < 11)
    {
      ((android.text.ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
      return;
    }
    ((android.content.ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", paramString));
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
    LocalyticsManager.setLocalyticsProfileAttribute("Installed Apps Package", arrayOfString2, Localytics.ProfileScope.ORGANIZATION);
    LocalyticsManager.setLocalyticsProfileAttribute("Installed Apps", arrayOfString1, Localytics.ProfileScope.ORGANIZATION);
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
        Crashlytics.logException(new Throwable(TAG + " Issue in putting user id/email\n" + localException.toString()));
        FirebaseCrash.report(new Exception(TAG + " Issue in putting user id/email\n" + localException.toString()));
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
      new BranchUniversalObject().setCanonicalIdentifier("invite/" + str1).setTitle((String)localObject1 + " has invited you !").setContentDescription((String)localObject1 + " wants you to download Mirraw App and have a look at awesome designs in their catalog").addContentMetadata("userName", (String)localObject1).addContentMetadata("userId", str1).addContentMetadata("referrer_id", getAccountableId()).addContentMetadata("userImage", str2).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("app_invite").addControlParameter("$deeplink_path", "share_app/").addControlParameter("~campaign", "app_share"), new Branch.BranchLinkCreateListener()
      {
        public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
        {
          if (paramAnonymousBranchError == null)
          {
            paramAnonymousBranchError = new Intent();
            paramAnonymousBranchError.setAction("android.intent.action.SEND");
            paramAnonymousBranchError.putExtra("android.intent.extra.TEXT", "Hey check out awesome ethnic wears at Mirraw\n\n" + paramAnonymousString);
            paramAnonymousBranchError.putExtra("android.intent.extra.SUBJECT", "Check out this awesome app I found!");
            paramAnonymousBranchError.setType("text/plain");
            if (paramAnonymousString != null) {
              this.val$context.startActivity(paramAnonymousBranchError);
            }
          }
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CrashReportManager.logException(Integer.valueOf(0), "shareAppUs", "Some issue in putting user id/email", localException);
        Crashlytics.logException(new Throwable(TAG + " Some issue in putting user id/email\n" + localException.toString()));
        FirebaseCrash.report(new Exception(TAG + " Some issue in putting user id/email\n" + localException.toString()));
      }
    }
  }
  
  public static void shareAppOnFacebook(final Activity paramActivity)
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
    ((BranchUniversalObject)localObject1).generateShortUrl(Mirraw.getAppContext(), (LinkProperties)localObject2, new Branch.BranchLinkCreateListener()
    {
      public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
      {
        if ((paramAnonymousBranchError == null) && (AppInviteDialog.canShow()))
        {
          paramAnonymousString = new AppInviteContent.Builder().setApplinkUrl(paramAnonymousString).setPreviewImageUrl(this.val$banner).build();
          AppInviteDialog.show(paramActivity, paramAnonymousString);
        }
      }
    });
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
      new BranchUniversalObject().setCanonicalIdentifier("invite/" + str1).setTitle((String)localObject1 + " has invited you !").setContentDescription((String)localObject1 + " wants you to download Mirraw App and have a look at awesome designs in their catalog").addContentMetadata("userName", (String)localObject1).addContentMetadata("userId", str1).addContentMetadata("referrer_id", getAccountableId()).addContentMetadata("userImage", str2).generateShortUrl(paramContext, new LinkProperties().setChannel("android_app").setFeature("app_invite").addControlParameter("$deeplink_path", "rewards/").addControlParameter("~campaign", "app_share"), new Branch.BranchLinkCreateListener()
      {
        public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
        {
          if (paramAnonymousBranchError == null)
          {
            Utils.appUrl = paramAnonymousString;
            paramAnonymousString = new Intent();
            paramAnonymousString.setAction("android.intent.action.SEND");
            paramAnonymousString.putExtra("android.intent.extra.TEXT", "Hey check out awesome ethnic wears at Mirraw\n\n" + Utils.appUrl);
            paramAnonymousString.putExtra("android.intent.extra.SUBJECT", "Check out this awesome app I found!");
            paramAnonymousString.setType("text/plain");
            if (Utils.appUrl != null) {
              this.val$context.startActivity(paramAnonymousString);
            }
          }
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CrashReportManager.logException(Integer.valueOf(0), "shareAppUs", "Some issue in putting user id/email", localException);
        Crashlytics.logException(new Throwable(TAG + " Some issue in putting user id/email\n" + localException.toString()));
        FirebaseCrash.report(new Exception(TAG + " Some issue in putting user id/email\n" + localException.toString()));
      }
    }
  }
  
  public static void shareDesigner(final Activity paramActivity, String paramString1, final String paramString2)
  {
    Object localObject1 = new SharedPreferencesManager(Mirraw.getAppContext());
    Object localObject2 = ((SharedPreferencesManager)localObject1).getUserName();
    long l = ((SharedPreferencesManager)localObject1).getUserId();
    localObject1 = new BranchUniversalObject().setCanonicalIdentifier("designer_share/" + paramString1).setTitle((String)localObject2 + " wants you to checkout " + paramString2 + " on Mirraw").setContentDescription((String)localObject2 + " wants you to checkout " + paramString2 + " on Mirraw").addContentMetadata("userName", (String)localObject2).addContentMetadata("userId", String.valueOf(l)).addContentMetadata("designerId", paramString1).addContentMetadata("designerName", paramString2);
    localObject2 = new LinkProperties().setChannel("android_app").setFeature("designer_share").addControlParameter("$deeplink_path", "shared_designer/").addControlParameter("~campaign", "app_designer_share");
    ((BranchUniversalObject)localObject1).generateShortUrl(Mirraw.getAppContext(), (LinkProperties)localObject2, new Branch.BranchLinkCreateListener()
    {
      public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
      {
        if (paramAnonymousBranchError == null)
        {
          paramAnonymousString = paramAnonymousString + "?designer_id=" + this.val$designerId;
          paramAnonymousBranchError = new Intent();
          paramAnonymousBranchError.setAction("android.intent.action.SEND");
          paramAnonymousBranchError.putExtra("android.intent.extra.TEXT", "Hey checkout " + paramString2 + " on Mirraw\n\n" + paramAnonymousString);
          paramAnonymousBranchError.putExtra("android.intent.extra.SUBJECT", "Checkout this designer on Mirraw");
          paramAnonymousBranchError.setType("text/plain");
          if (paramAnonymousString != null) {
            paramActivity.startActivity(paramAnonymousBranchError);
          }
        }
      }
    });
  }
  
  public static void shareProduct(final Context paramContext, final File paramFile, final String paramString1, final String paramString2, final String paramString3, BottomSheetLayout paramBottomSheetLayout, final Uri paramUri)
  {
    final String str = "Hey, check out " + paramString1 + " on Mirraw!\n" + paramString2;
    final Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.TEXT", str);
    localIntent.setType("text/plain");
    paramBottomSheetLayout.showWithSheetView(new IntentPickerSheetView(paramContext, localIntent, "Share this product", new IntentPickerSheetView.OnIntentPickedListener()
    {
      public void onIntentPicked(IntentPickerSheetView.ActivityInfo paramAnonymousActivityInfo)
      {
        this.val$bottomSheetLayout.dismissSheet();
        if ((paramContext instanceof ProductDetailActivity)) {
          ((ProductDetailActivity)paramContext).mProductDetailWithSimilarProductsFragment.tagProductShareClick(paramAnonymousActivityInfo.label, Boolean.valueOf(true));
        }
        label171:
        label286:
        do
        {
          do
          {
            do
            {
              break label171;
              for (;;)
              {
                paramAnonymousActivityInfo = paramAnonymousActivityInfo.getConcreteIntent(localIntent);
                if (paramAnonymousActivityInfo.getComponent().getPackageName().contains("whatsapp")) {
                  break;
                }
                paramAnonymousActivityInfo.putExtra("android.intent.extra.SUBJECT", "Check out this awesome product on Mirraw!");
                if ((!paramAnonymousActivityInfo.getComponent().getPackageName().equalsIgnoreCase("com.facebook.katana")) || (!(paramContext instanceof Activity))) {
                  break label286;
                }
                paramAnonymousActivityInfo = new ShareDialog((Activity)paramContext);
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                  paramAnonymousActivityInfo.show(((ShareLinkContent.Builder)new ShareLinkContent.Builder().setContentDescription(paramString1).setContentUrl(Uri.parse(paramString2))).setImageUrl(Uri.parse(Utils.addHttpSchemeIfMissing(paramString3))).setContentTitle("Mirraw").build());
                }
                return;
                if ((paramContext instanceof TabbedHomeActivity)) {
                  ((TabbedHomeActivity)paramContext).tagFeedFragmentShareProductClickDelegate(paramAnonymousActivityInfo.label, Boolean.valueOf(true));
                }
              }
              String str = paramFile.getAbsolutePath();
              Uri.parse(str);
              paramAnonymousActivityInfo.setAction("android.intent.action.SEND");
              paramAnonymousActivityInfo.putExtra("android.intent.extra.STREAM", paramUri);
              if (str.substring(str.lastIndexOf(".") - 3, str.lastIndexOf(".")).equalsIgnoreCase("jpg")) {
                paramAnonymousActivityInfo.setType("image/JPEG");
              }
              for (;;)
              {
                paramAnonymousActivityInfo.addFlags(1);
                break;
                paramAnonymousActivityInfo.setType("image/PNG");
              }
              if ((!paramAnonymousActivityInfo.getComponent().getPackageName().equalsIgnoreCase("com.google.android.apps.plus")) && (!paramAnonymousActivityInfo.getComponent().getPackageName().equalsIgnoreCase("com.google.android.gm"))) {
                break;
              }
              paramAnonymousActivityInfo.putExtra("android.intent.extra.STREAM", paramUri);
            } while (!(paramContext instanceof Activity));
            ((Activity)paramContext).startActivityForResult(paramAnonymousActivityInfo, 999);
            return;
            if (!paramAnonymousActivityInfo.getComponent().getPackageName().equalsIgnoreCase("com.pinterest")) {
              break;
            }
            paramAnonymousActivityInfo.putExtra("android.intent.extra.STREAM", paramUri);
            paramAnonymousActivityInfo.putExtra("com.pinterest.EXTRA_DESCRIPTION", str);
          } while (!(paramContext instanceof Activity));
          ((Activity)paramContext).startActivityForResult(paramAnonymousActivityInfo, 999);
          return;
        } while (!(paramContext instanceof Activity));
        paramContext.startActivity(paramAnonymousActivityInfo);
      }
    }));
  }
  
  public static void shareProductWithoutImage(final Context paramContext, String paramString1, final String paramString2, BottomSheetLayout paramBottomSheetLayout)
  {
    paramString1 = "Hey, check out " + paramString1 + " on Mirraw!\n" + paramString2;
    paramString2 = new Intent();
    paramString2.setAction("android.intent.action.SEND");
    paramString2.putExtra("android.intent.extra.TEXT", paramString1);
    paramString2.putExtra("android.intent.extra.SUBJECT", "Check out this awesome product on Mirraw!");
    paramString2.setType("text/plain");
    paramBottomSheetLayout.showWithSheetView(new IntentPickerSheetView(paramContext, paramString2, "Share this product", new IntentPickerSheetView.OnIntentPickedListener()
    {
      public void onIntentPicked(IntentPickerSheetView.ActivityInfo paramAnonymousActivityInfo)
      {
        this.val$bottomSheetLayout.dismissSheet();
        if ((paramContext instanceof ProductDetailActivity)) {
          ((ProductDetailActivity)paramContext).mProductDetailFragment.tagProductShareClick(paramAnonymousActivityInfo.label, Boolean.valueOf(false));
        }
        for (;;)
        {
          paramAnonymousActivityInfo = paramAnonymousActivityInfo.getConcreteIntent(paramString2);
          paramContext.startActivity(paramAnonymousActivityInfo);
          return;
          if ((paramContext instanceof TabbedHomeActivity)) {
            ((TabbedHomeActivity)paramContext).tagFeedFragmentShareProductClickDelegate(paramAnonymousActivityInfo.label, Boolean.valueOf(false));
          }
        }
      }
    }));
  }
  
  public static void shareWishlist(Activity paramActivity)
  {
    Object localObject1 = getAccountableId();
    Object localObject2 = "https://api.mirraw.com/api/v1/user/wishlist_designs?user_id=" + (String)localObject1;
    SharedPreferencesManager localSharedPreferencesManager = new SharedPreferencesManager(Mirraw.getAppContext());
    String str = localSharedPreferencesManager.getUserName();
    localObject1 = new BranchUniversalObject().setCanonicalIdentifier("wishlist_share/" + (String)localObject1).setTitle(str + " wants you to check out their Wishlist").setContentDescription(str + " wants you to check out their Wishlist").addContentMetadata("userName", str).addContentMetadata("userId", String.valueOf(localSharedPreferencesManager.getUserId())).addContentMetadata("wishlistUrl", (String)localObject2);
    localObject2 = new LinkProperties().setChannel("android_app").setFeature("wishlist_share").addControlParameter("$deeplink_path", "shared_wishlist/").addControlParameter("~campaign", "app_wishlist_share").addControlParameter("$ios_url", "https://www.mirraw.com");
    ((BranchUniversalObject)localObject1).generateShortUrl(Mirraw.getAppContext(), (LinkProperties)localObject2, new Branch.BranchLinkCreateListener()
    {
      public void onLinkCreate(String paramAnonymousString, BranchError paramAnonymousBranchError)
      {
        if (paramAnonymousBranchError == null)
        {
          Utils.wishlistShareUrl = paramAnonymousString;
          paramAnonymousString = new Intent();
          paramAnonymousString.setAction("android.intent.action.SEND");
          paramAnonymousString.putExtra("android.intent.extra.TEXT", "Hey check out my wishlist on Mirraw\n\n" + Utils.wishlistShareUrl);
          paramAnonymousString.putExtra("android.intent.extra.SUBJECT", "My Wishlist on Mirraw!");
          paramAnonymousString.setType("text/plain");
          if (Utils.wishlistShareUrl != null) {
            this.val$activity.startActivity(paramAnonymousString);
          }
        }
      }
    });
  }
  
  public static void showSnackBar(String paramString, Activity paramActivity, int paramInt)
  {
    paramString = Snackbar.make(paramActivity.findViewById(16908290), paramString, paramInt).setAction("Action", null);
    paramString.getView().setBackgroundColor(paramActivity.getResources().getColor(2131558492));
    paramString.getView().setPadding(0, 10, 0, 10);
    paramString.show();
  }
  
  public static void showSnackbar(String paramString, Activity paramActivity)
  {
    paramString = Snackbar.make(paramActivity.findViewById(16908290), paramString, -2);
    paramString.setAction("Dismiss", new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.val$snackbar.dismiss();
      }
    });
    paramString.getView().setBackgroundColor(paramActivity.getResources().getColor(2131558492));
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
      Crashlytics.logException(new Throwable(TAG + " Volley error message issue\n" + paramString1.toString()));
      FirebaseCrash.report(new Exception(TAG + " Volley error message issue\n" + paramString1.toString()));
    }
    return null;
  }
}
