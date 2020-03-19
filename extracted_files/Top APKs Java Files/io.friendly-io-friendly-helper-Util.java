package io.friendly.helper;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.ActionMenuView.ActionMenuChildView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.javiersantos.bottomdialogs.BottomDialog.Builder;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;
import com.thefinestartist.utils.content.ContextUtil;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.utils.URLEncodedUtils;
import de.mateware.snacky.Snacky;
import de.mateware.snacky.Snacky.Builder;
import io.friendly.activity.BaseActivity;
import io.friendly.activity.CustomPinActivity;
import io.friendly.activity.ManageFavoriteActivity;
import io.friendly.activity.SplashActivity;
import io.friendly.activity.ThankYouActivity;
import io.friendly.activity.page.OnePageActivity;
import io.friendly.finestwebview.FinestWebView.Builder;
import io.friendly.model.provider.UsersFacebookProvider;
import io.friendly.model.ui.Suggestion;
import io.friendly.model.user.AbstractFavorite;
import io.friendly.preference.UserGlobalPreference;
import io.friendly.preference.UserPreference;
import io.friendly.service.ActionBroadcastReceiver;
import io.friendly.ui.GoogleFloatSearchView;
import io.friendly.util.ShareImage;
import io.friendly.util.WebviewFallback;
import io.friendly.webview.JavascriptInterface;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util
{
  public static String ALTERNATIVE_APPS = "";
  public static final String ALTERNATIVE_APPS_REMOTE = "alternative_apps";
  public static String ALTERNATIVE_APP_SEPARATOR = ";";
  public static String ANDROID = "android";
  public static String APPLICATION_PREFERENCES = "application#preferences";
  public static final int CHECK_ACTIVITY_NOTIFICATION = 20000;
  public static String CURRENT_USER = "current#user";
  public static final int DEFAULT_UNLOCK = 120000;
  public static final String END_DAY_TIME = "0630";
  public static final String END_NIGHT_TIME = "0700";
  public static String FACEBOOK_HOME_PAGE = "home.php";
  public static String FACEBOOK_SHARER_PAGE = "/composer/mbasic/";
  public static String HEADER_JSON = "application/json";
  public static final int HIDE_LOADER = 400;
  public static String PREFERENCE_BOTTOM_INTRO = "preferences#bottomIntro#5";
  public static String PREFERENCE_ONBOARDING = "preferences#onboarding#2";
  public static String PREFERENCE_RATE_DIALOG = "preferences#ratedialog1";
  public static String PREFERENCE_SHOWCASE_TABS = "preferences#showcasetabs";
  public static String PREFERENCE_WHATS_NEW = "preferences#whats_new";
  public static final int REQUEST_CODE_SHARE_URL = 1022;
  public static final int REQUEST_LOCATION = 2;
  public static final int REQUEST_STORAGE = 1;
  public static String SETTINGS_CHANGE_USER = "settings_change_user";
  public static String SETTINGS_REFRESH = "settings_refresh";
  public static String SETTINGS_RELOAD = "settings_reload";
  public static String SETTINGS_RELOAD_USER = "settings_reload_user";
  public static String SHOWCASE_DRAWER = "showcase#drawer#2";
  public static String SHOWCASE_SETTINGS = "showcase#friendlySettings";
  public static final String START_DAY_TIME = "1730";
  public static final String START_NIGHT_TIME = "1830";
  public static String USER_STR = "user";
  public static String X_API_KEY = "bJCDASwvVA7xHrZWJn9rf9N2R5Uf1sdA4FrGxBtS";
  public static final Pattern urlPattern = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", 42);
  
  public Util() {}
  
  public static void LongLog(String paramString1, String paramString2)
  {
    String str;
    if ((paramString2 != null) && (!paramString2.isEmpty())) {
      str = paramString2;
    } else {
      str = "LongLog";
    }
    if (paramString1.length() > 4000)
    {
      paramString2 = new StringBuilder();
      paramString2.append("sb.length = ");
      paramString2.append(paramString1.length());
      Log.e(str, paramString2.toString());
      int k = paramString1.length() / 4000;
      int j;
      for (int i = 0; i <= k; i = j)
      {
        j = i + 1;
        int m = j * 4000;
        if (m >= paramString1.length())
        {
          paramString2 = new StringBuilder();
          paramString2.append("chunk ");
          paramString2.append(i);
          paramString2.append(" of ");
          paramString2.append(k);
          paramString2.append(":");
          paramString2.append(paramString1.substring(i * 4000));
          Log.e(str, paramString2.toString());
        }
        else
        {
          paramString2 = new StringBuilder();
          paramString2.append("chunk ");
          paramString2.append(i);
          paramString2.append(" of ");
          paramString2.append(k);
          paramString2.append(":");
          paramString2.append(paramString1.substring(i * 4000, m));
          Log.e(str, paramString2.toString());
        }
      }
    }
    Log.e(paramString2, paramString1);
  }
  
  public static void androidIDInitialization(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (str != null) {
      str = md5(str).toUpperCase();
    } else {
      str = "";
    }
    UserGlobalPreference.saveUM5(paramContext, str);
  }
  
  public static boolean checkMessengerClient(Activity paramActivity, String paramString1, String paramString2)
  {
    if (Level.isAnyOf(paramString1, new String[] { Level.CONVERSATION, Level.MESSAGE }))
    {
      switch (UserPreference.getMessengerClient(paramActivity))
      {
      default: 
        return false;
      case 2: 
        launchMessengerClient(paramActivity, getMessengerIntent(paramString1, paramString2, "com.facebook.mlite"));
        return true;
      }
      launchMessengerClient(paramActivity, getMessengerIntent(paramString1, paramString2, "com.facebook.orca"));
      return true;
    }
    return false;
  }
  
  public static boolean checkWebViewIntent(Activity paramActivity, String paramString)
  {
    if (paramString.startsWith("fb://webview/?url="))
    {
      launchExternalURL(getValue(paramString, "url"), paramActivity);
      return true;
    }
    return false;
  }
  
  public static void closeKeyboard(Activity paramActivity)
  {
    View localView = paramActivity.getCurrentFocus();
    if (localView != null)
    {
      paramActivity = (InputMethodManager)paramActivity.getSystemService("input_method");
      if (paramActivity != null) {
        paramActivity.hideSoftInputFromWindow(localView.getWindowToken(), 0);
      }
    }
  }
  
  public static boolean containString(List<String> paramList, String paramString)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (paramString.equals((String)paramList.next())) {
        return true;
      }
    }
    return false;
  }
  
  public static PendingIntent createPendingIntent(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, ActionBroadcastReceiver.class);
    localIntent.putExtra("io.friendly.ACTION_SOURCE", paramInt);
    return PendingIntent.getBroadcast(paramContext, paramInt, localIntent, 0);
  }
  
  public static int darkerColor(int paramInt)
  {
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[2] *= 0.9F;
    return Color.HSVToColor(arrayOfFloat);
  }
  
  public static void debugToast(Context paramContext, String paramString) {}
  
  public static String decodeBase64(String paramString)
  {
    try
    {
      paramString = new String(Base64.decode(paramString, 0), "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static boolean determineNightMode(Context paramContext, UsersFacebookProvider paramUsersFacebookProvider)
  {
    boolean bool;
    switch (UserPreference.getNightHoursMode(paramContext))
    {
    default: 
      bool = false;
      break;
    case 2: 
      bool = isSelectedNightHours(paramContext);
      break;
    case 1: 
      bool = isAutoNight();
      break;
    case 0: 
      bool = true;
    }
    if (!UserPreference.getNightModeToggle(paramContext)) {
      bool = false;
    }
    UserPreference.saveNightModeEnabled(paramContext, bool);
    if (paramUsersFacebookProvider != null) {
      paramUsersFacebookProvider.updateCurrentUserPreference(UserPreference.getAllUserSharedPreferencesString(paramContext));
    }
    return bool;
  }
  
  public static void displaySnack(Activity paramActivity, String paramString)
  {
    if (paramActivity == null) {
      return;
    }
    Snacky.builder().setActivity(paramActivity).setText(paramString).setDuration(0).build().show();
  }
  
  public static void displaySnackFromID(Activity paramActivity, int paramInt)
  {
    if (paramInt > 0)
    {
      if (paramActivity == null) {
        return;
      }
      displaySnack(paramActivity, paramActivity.getString(paramInt));
      return;
    }
  }
  
  public static void displaySnackFromView(View paramView, String paramString)
  {
    if (paramView == null) {
      return;
    }
    Snacky.builder().setView(paramView).setText(paramString).setDuration(0).build().show();
  }
  
  public static void displaySnackHTML(Activity paramActivity, Spanned paramSpanned)
  {
    if (paramSpanned == null) {
      return;
    }
    displaySnack(paramActivity, paramSpanned.toString());
  }
  
  public static void downloadPicture(Activity paramActivity, String paramString)
  {
    if (paramString != null)
    {
      if (paramActivity == null) {
        return;
      }
      if (!hasStoragePermission(paramActivity))
      {
        requestStoragePermission(paramActivity);
        return;
      }
      if (!ShareImage.resolve(paramActivity)) {}
    }
    try
    {
      if (!paramString.contains("http")) {
        break label233;
      }
      Object localObject1 = paramActivity.getString(2131755070).replace(" ", "friendly");
      localObject1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), (String)localObject1);
      if (!((File)localObject1).exists()) {
        ((File)localObject1).mkdirs();
      }
      localObject1 = ".jpg";
      if (paramString.contains(".gif")) {
        localObject1 = ".gif";
      } else if (paramString.contains(".png")) {
        localObject1 = ".png";
      }
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("IMG_");
      ((StringBuilder)localObject2).append(System.currentTimeMillis());
      ((StringBuilder)localObject2).append((String)localObject1);
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = (DownloadManager)paramActivity.getSystemService("download");
      paramString = new DownloadManager.Request(Uri.parse(paramString));
      paramString.setAllowedNetworkTypes(3).setDestinationInExternalPublicDir(UserGlobalPreference.getDownloadFolder(paramActivity), (String)localObject1).setTitle((CharSequence)localObject1).setDescription(paramActivity.getString(2131755183)).setNotificationVisibility(1);
      ((DownloadManager)localObject2).enqueue(paramString);
      Tracking.trackPictureDownloaded(paramActivity);
      return;
    }
    catch (IllegalStateException|IllegalArgumentException|NullPointerException paramString)
    {
      for (;;) {}
    }
    Toast.makeText(paramActivity, paramActivity.getString(2131755189), 0).show();
    label233:
    return;
  }
  
  public static void downloadVideo(Activity paramActivity, String paramString)
  {
    if (!isNetworkAvailable(paramActivity)) {
      return;
    }
    if (!hasStoragePermission(paramActivity))
    {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
      return;
    }
    if (ShareImage.resolve(paramActivity)) {}
    try
    {
      Object localObject1 = paramActivity.getResources().getString(2131755070);
      localObject1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), (String)localObject1);
      if (!((File)localObject1).exists()) {
        ((File)localObject1).mkdirs();
      }
      localObject1 = ".mp4";
      if (paramString.contains(".avi")) {
        localObject1 = ".avi";
      } else if (paramString.contains(".mkv")) {
        localObject1 = ".mkv";
      } else if (paramString.contains(".wav")) {
        localObject1 = ".wav";
      }
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("VID_");
      ((StringBuilder)localObject2).append(System.currentTimeMillis());
      ((StringBuilder)localObject2).append((String)localObject1);
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = (DownloadManager)paramActivity.getSystemService("download");
      paramString = new DownloadManager.Request(Uri.parse(paramString));
      paramString.setAllowedNetworkTypes(3).setDestinationInExternalPublicDir(UserGlobalPreference.getDownloadFolder(paramActivity), (String)localObject1).setTitle((CharSequence)localObject1).setDescription(paramActivity.getResources().getString(2131755183)).setNotificationVisibility(1);
      ((DownloadManager)localObject2).enqueue(paramString);
      Toast.makeText(paramActivity, paramActivity.getResources().getString(2131755183), 0).show();
      Tracking.trackVideoDownloaded(paramActivity);
      return;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    Toast.makeText(paramActivity, paramActivity.getResources().getString(2131755190), 1).show();
  }
  
  public static int dpToPx(Context paramContext, float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getDisplayMetrics());
  }
  
  public static ImageView findOverflowMenuButton(Activity paramActivity, ViewGroup paramViewGroup)
  {
    ImageView localImageView = null;
    if (paramViewGroup == null) {
      return null;
    }
    int i = 0;
    int j = paramViewGroup.getChildCount();
    while (i < j)
    {
      View localView = paramViewGroup.getChildAt(i);
      if (((localView instanceof ImageView)) && ((localView.getClass().getSimpleName().equals("OverflowMenuButton")) || ((localView instanceof ActionMenuView.ActionMenuChildView)))) {
        localImageView = (ImageView)localView;
      } else if ((localView instanceof ViewGroup)) {
        localImageView = findOverflowMenuButton(paramActivity, (ViewGroup)localView);
      }
      if (localImageView != null) {
        return localImageView;
      }
      i += 1;
    }
    return localImageView;
  }
  
  public static Bitmap flip(Bitmap paramBitmap, Util.Direction paramDirection)
  {
    Matrix localMatrix = new Matrix();
    if (paramDirection == Util.Direction.VERTICAL)
    {
      localMatrix.preScale(1.0F, -1.0F);
    }
    else
    {
      if (paramDirection != Util.Direction.HORIZONTAL) {
        return paramBitmap;
      }
      localMatrix.preScale(-1.0F, 1.0F);
    }
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
    return paramBitmap;
  }
  
  public static Drawable flipDrawable(Context paramContext, int paramInt)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
    return new BitmapDrawable(paramContext.getResources(), flip(localBitmap, Util.Direction.VERTICAL));
  }
  
  public static String formatTime(Context paramContext, String paramString)
  {
    if (DateFormat.is24HourFormat(paramContext)) {
      return paramString;
    }
    try
    {
      paramContext = new SimpleDateFormat("H:mm", Locale.US).parse(paramString);
      paramContext = new SimpleDateFormat("K:mm a", Locale.US).format(paramContext);
      return paramContext;
    }
    catch (ParseException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static float getAlphaBottomMenu(Context paramContext)
  {
    return 0.4F;
  }
  
  public static String[] getAlternativeFBClients()
  {
    return Pattern.compile(ALTERNATIVE_APP_SEPARATOR).split(ALTERNATIVE_APPS);
  }
  
  public static SharedPreferences getApplicationPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences(APPLICATION_PREFERENCES, 0);
  }
  
  public static SharedPreferences.Editor getApplicationPreferencesEditor(Context paramContext)
  {
    return paramContext.getSharedPreferences(APPLICATION_PREFERENCES, 0).edit();
  }
  
  public static boolean getBottomIntro(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getBoolean(PREFERENCE_BOTTOM_INTRO, false);
  }
  
  public static Bitmap getCircleBitmap(Bitmap paramBitmap)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-65536);
    localCanvas.drawOval(localRectF, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    paramBitmap.recycle();
    return localBitmap;
  }
  
  public static String getCurrentUser(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getString(CURRENT_USER, "");
  }
  
  public static long getDateDiff(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(Math.abs(paramLong2 - paramLong1), TimeUnit.MILLISECONDS);
  }
  
  public static String getDefaultUserAgent(Context paramContext)
  {
    try
    {
      paramContext = WebSettings.getDefaultUserAgent(paramContext);
      return paramContext;
    }
    catch (AndroidRuntimeException paramContext)
    {
      paramContext.printStackTrace();
    }
    return UserGlobalPreference.USER_AGENT;
  }
  
  public static String getDesktopURL(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()) && (paramString.contains("https://www.facebook.com"))) {
      return paramString.replace("\"", "");
    }
    return "https://www.facebook.com";
  }
  
  public static String getDeviceConfig()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("App version: 505 - 3.1.05\nModel: ");
    localStringBuilder.append(android.os.Build.MANUFACTURER);
    localStringBuilder.append(" ");
    localStringBuilder.append(android.os.Build.MODEL);
    localStringBuilder.append(" ");
    localStringBuilder.append(android.os.Build.DEVICE);
    localStringBuilder.append("\nAndroid: ");
    localStringBuilder.append(Build.VERSION.SDK_INT);
    localStringBuilder.append("\n\n");
    return localStringBuilder.toString();
  }
  
  public static int getDeviceMetricHeight(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if ((paramContext != null) && (paramContext.getDefaultDisplay() != null))
    {
      Point localPoint = new Point();
      paramContext.getDefaultDisplay().getRealSize(localPoint);
      return localPoint.y;
    }
    return 1920;
  }
  
  public static int getDeviceMetricWidth(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if ((paramContext != null) && (paramContext.getDefaultDisplay() != null))
    {
      Point localPoint = new Point();
      paramContext.getDefaultDisplay().getRealSize(localPoint);
      return localPoint.x;
    }
    return 1080;
  }
  
  public static String getFileExtension(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    if (paramString.contains(".mp4")) {
      return "mp4";
    }
    if (paramString.contains(".jpg")) {
      return "jpg";
    }
    if (paramString.contains(".png")) {
      return "png";
    }
    if (paramString.contains(".avi")) {
      return "avi";
    }
    if (paramString.contains(".mkv")) {
      return "mkv";
    }
    if (paramString.contains(".wav")) {
      return "wav";
    }
    return "";
  }
  
  public static String getFileTypeFromString(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null))
    {
      paramContext = paramContext.getContentResolver();
      MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
      if (localMimeTypeMap != null) {
        return localMimeTypeMap.getExtensionFromMimeType(paramContext.getType(Uri.parse(paramString)));
      }
      return "";
    }
    return "";
  }
  
  public static String getHash(Context paramContext, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(UserGlobalPreference.getUUID(paramContext));
      localStringBuilder.append(paramString);
      paramContext = Util.AeSimpleSHA1.SHA1(localStringBuilder.toString());
      return paramContext;
    }
    catch (NoSuchAlgorithmException|UnsupportedEncodingException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static int getHeightToolbar(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(16843499, localTypedValue, true)) {
      return (int)pxToDp(paramContext, TypedValue.complexToDimension(localTypedValue.data, paramContext.getResources().getDisplayMetrics()));
    }
    return 54;
  }
  
  public static String getLocalIpAddress()
  {
    try
    {
      Object localObject;
      int i;
      do
      {
        do
        {
          Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
          Iterator localIterator2;
          while (!localIterator2.hasNext())
          {
            if (!localIterator1.hasNext()) {
              break;
            }
            localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
          }
          localObject = (InetAddress)localIterator2.next();
        } while (((InetAddress)localObject).isLoopbackAddress());
        localObject = ((InetAddress)localObject).getHostAddress();
        i = ((String)localObject).indexOf(':');
        if (i < 0) {
          i = 1;
        } else {
          i = 0;
        }
      } while (i == 0);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static Intent getMessengerIntent(String paramString1, String paramString2, String paramString3)
  {
    paramString2 = Urls.getUserFacebookID(paramString2);
    if ((Level.isAnyOf(paramString1, new String[] { Level.CONVERSATION })) && (!paramString2.isEmpty()))
    {
      paramString1 = new StringBuilder();
      paramString1.append("fb-messenger://user/");
      paramString1.append(paramString2);
      return new Intent("android.intent.action.VIEW", Uri.parse(paramString1.toString())).setPackage(paramString3);
    }
    return ContextUtil.getPackageManager().getLaunchIntentForPackage(paramString3);
  }
  
  public static boolean getOnBoarding(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getBoolean(PREFERENCE_ONBOARDING, false);
  }
  
  public static long getRandom()
  {
    double d1 = new Random().nextDouble();
    double d2 = 123456789L;
    Double.isNaN(d2);
    return (d1 * d2);
  }
  
  public static boolean getRateDialogShown(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getBoolean(PREFERENCE_RATE_DIALOG, false);
  }
  
  public static boolean getShowcaseDrawer(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getBoolean(SHOWCASE_DRAWER, false);
  }
  
  public static boolean getShowcaseFriendlySettings(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getBoolean(SHOWCASE_SETTINGS, false);
  }
  
  public static String getShowcaseTabs(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getString(PREFERENCE_SHOWCASE_TABS, "");
  }
  
  public static View getToolbarTitle(Toolbar paramToolbar)
  {
    int j = paramToolbar.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = paramToolbar.getChildAt(i);
      if ((localView instanceof TextView)) {
        return localView;
      }
      i += 1;
    }
    return null;
  }
  
  public static String getUserCountry(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      String str = paramContext.getSimCountryIso();
      if ((str != null) && (str.length() == 2)) {
        return str.toLowerCase(Locale.US);
      }
      if (paramContext.getPhoneType() != 2)
      {
        paramContext = paramContext.getNetworkCountryIso();
        if ((paramContext != null) && (paramContext.length() == 2))
        {
          paramContext = paramContext.toLowerCase(Locale.US);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String getValue(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = URLEncodedUtils.parse(new URI(paramString1), "UTF-8").iterator();
      while (paramString1.hasNext())
      {
        NameValuePair localNameValuePair = (NameValuePair)paramString1.next();
        if (localNameValuePair.getName().equals(paramString2))
        {
          paramString1 = localNameValuePair.getValue();
          return paramString1;
        }
      }
    }
    catch (URISyntaxException paramString1)
    {
      paramString1.printStackTrace();
    }
    return "";
  }
  
  public static String getVersionName(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
        return paramContext;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return "";
  }
  
  public static String getWhatsNew(Context paramContext)
  {
    return getApplicationPreferences(paramContext).getString(PREFERENCE_WHATS_NEW, "");
  }
  
  public static String getWhatsNewMessage(Context paramContext)
  {
    InputStream localInputStream = paramContext.getResources().openRawResource(2131689472);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    try
    {
      for (;;)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      localByteArrayOutputStream.close();
      localInputStream.close();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return replaceAppName(paramContext, localByteArrayOutputStream.toString());
  }
  
  public static boolean hasLocationPermission(Activity paramActivity)
  {
    return ContextCompat.checkSelfPermission(paramActivity, "android.permission.ACCESS_FINE_LOCATION") == 0;
  }
  
  public static boolean hasStoragePermission(Activity paramActivity)
  {
    return ContextCompat.checkSelfPermission(paramActivity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
  }
  
  public static boolean haveAlternativeFBClient()
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        Object localObject = ContextUtil.getPackageManager();
        String[] arrayOfString = getAlternativeFBClients();
        localObject = ((PackageManager)localObject).getInstalledApplications(128).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          int j = arrayOfString.length;
          int i = 0;
          while (i < j)
          {
            boolean bool = arrayOfString[i].equals(localApplicationInfo.packageName);
            if (bool) {
              return true;
            }
            i += 1;
          }
        }
      }
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void injectScriptFile(WebView paramWebView, String paramString)
  {
    try
    {
      paramString = Base64.encodeToString(paramString.getBytes("UTF-8"), 2);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var script = document.createElement('script');script.type = 'text/javascript';script.innerHTML = window.atob('");
      localStringBuilder.append(paramString);
      localStringBuilder.append("');parent.appendChild(script)})()");
      paramWebView.loadUrl(localStringBuilder.toString());
      return;
    }
    catch (UnsupportedEncodingException paramWebView)
    {
      paramWebView.printStackTrace();
    }
  }
  
  @SuppressLint({"WrongConstant"})
  public static boolean isAutoNight()
  {
    boolean bool2 = false;
    boolean bool1 = false;
    try
    {
      Object localObject = Calendar.getInstance();
      TimeZone localTimeZone = TimeZone.getDefault();
      ((Calendar)localObject).set(1, 0);
      localObject = new SimpleDateFormat("HHmm", Locale.getDefault()).format(((Calendar)localObject).getTime());
      int i;
      if (localTimeZone.useDaylightTime()) {
        i = Integer.parseInt("1730");
      } else {
        i = Integer.parseInt("1830");
      }
      int j;
      if (localTimeZone.useDaylightTime()) {
        j = Integer.parseInt("0630");
      } else {
        j = Integer.parseInt("0700");
      }
      int k = Integer.parseInt((String)localObject);
      if (i > j)
      {
        if ((k > i) || (k < j)) {
          bool1 = true;
        }
        return bool1;
      }
      bool1 = bool2;
      if (i < j)
      {
        bool1 = bool2;
        if (k >= i)
        {
          bool1 = bool2;
          if (k <= j) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isDesktopModeEnabled(String paramString)
  {
    return (paramString != null) && ((paramString.startsWith("https://www.")) || (paramString.startsWith("http://www.")));
  }
  
  public static boolean isFacebookLink(String paramString)
  {
    return (paramString.startsWith("https://m.facebook")) || (paramString.startsWith("http://m.facebook")) || (paramString.startsWith("https://facebook")) || (paramString.startsWith("http://facebook")) || (paramString.startsWith("https://www.facebook")) || (paramString.startsWith("http://www.facebook")) || (paramString.startsWith("about:bookmarks"));
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    boolean bool2 = false;
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    }
    catch (NullPointerException paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.isConnected()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isNetworkNotAvailable(Context paramContext)
  {
    return isNetworkAvailable(paramContext) ^ true;
  }
  
  public static boolean isRTL()
  {
    return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
  }
  
  public static boolean isSelectedNightHours(Context paramContext)
  {
    Object localObject = Calendar.getInstance();
    int i = ((Calendar)localObject).get(11);
    int j = ((Calendar)localObject).get(12);
    if ((!UserPreference.getEndNightHours(paramContext).isEmpty()) && (!UserPreference.getStartNightHours(paramContext).isEmpty())) {
      try
      {
        localObject = UserPreference.getStartNightHours(paramContext);
        paramContext = UserPreference.getEndNightHours(paramContext);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(i);
        localStringBuilder.append(":");
        localStringBuilder.append(j);
        boolean bool = isTimeBetweenTwoTime((String)localObject, paramContext, localStringBuilder.toString());
        return bool;
      }
      catch (ParseException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean isTimeBetweenTwoTime(String paramString1, String paramString2, String paramString3)
    throws ParseException
  {
    Date localDate1 = new SimpleDateFormat("HH:mm", Locale.US).parse(paramString1);
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTime(localDate1);
    Date localDate2 = new SimpleDateFormat("HH:mm", Locale.US).parse(paramString3);
    Calendar localCalendar3 = Calendar.getInstance();
    localCalendar3.setTime(localDate2);
    paramString3 = new SimpleDateFormat("HH:mm", Locale.US).parse(paramString2);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTime(paramString3);
    paramString1 = localDate2;
    if (localDate2.compareTo(paramString3) < 0)
    {
      localCalendar3.add(5, 1);
      paramString1 = localCalendar3.getTime();
    }
    paramString2 = localDate1;
    if (localDate1.compareTo(paramString3) < 0)
    {
      localCalendar1.add(5, 1);
      paramString2 = localCalendar1.getTime();
    }
    if (paramString1.before(paramString2))
    {
      Log.e("date", "First ---- ");
      return false;
    }
    paramString2 = paramString3;
    if (paramString1.after(paramString3))
    {
      localCalendar2.add(5, 1);
      paramString2 = localCalendar2.getTime();
    }
    return paramString1.before(paramString2);
  }
  
  public static void launchAdURL(String paramString, Activity paramActivity)
  {
    unlockTimer(paramActivity);
    launchExternalURLFromExternalBrowser(paramString, paramActivity);
  }
  
  public static void launchBookmarkURL(Activity paramActivity, String paramString1, AbstractFavorite paramAbstractFavorite, int paramInt, String paramString2)
  {
    if (paramString1 == null) {
      return;
    }
    new Handler().postDelayed(new -..Lambda.Util.Usza0Swbg8hs83nDk6YCoydqHHA(paramString1, paramActivity, paramString2, paramAbstractFavorite, paramInt), 200L);
  }
  
  public static void launchExternalURL(String paramString, Activity paramActivity)
  {
    unlockTimer(paramActivity);
    switch (UserPreference.getBrowser(paramActivity))
    {
    default: 
      return;
    case 2: 
      launchExternalURLFromExternalBrowser(paramString, paramActivity);
      return;
    case 1: 
      launchExternalURLFromCustomTabs(paramString, paramActivity);
      return;
    }
    launchExternalURLFromFinest(paramString, null, -1, paramActivity);
  }
  
  public static void launchExternalURLFromCustomTabs(String paramString, Activity paramActivity)
  {
    CustomTabsIntent.Builder localBuilder = new CustomTabsIntent.Builder();
    localBuilder.setToolbarColor(-1);
    localBuilder.setShowTitle(true);
    localBuilder.setStartAnimations(paramActivity, 2130771987, 2130771990);
    localBuilder.setExitAnimations(paramActivity, 2130771983, 2130771984);
    localBuilder.addDefaultShareMenuItem();
    localBuilder.addMenuItem(paramActivity.getString(2131755165), createPendingIntent(paramActivity.getApplicationContext(), 1));
    localBuilder.addMenuItem(String.format(paramActivity.getString(2131755483), new Object[] { paramActivity.getString(2131755070) }), createPendingIntent(paramActivity.getApplicationContext(), 2));
    CustomTabs.openCustomTab(paramActivity, localBuilder.build(), Uri.parse(paramString), new WebviewFallback());
  }
  
  public static void launchExternalURLFromExternalBrowser(String paramString, Activity paramActivity)
  {
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      paramString.printStackTrace();
      displaySnackFromID(paramActivity, 2131755187);
    }
  }
  
  public static void launchExternalURLFromFinest(String paramString, AbstractFavorite paramAbstractFavorite, int paramInt, Activity paramActivity)
  {
    try
    {
      new FinestWebView.Builder(paramActivity).setFavorite(paramAbstractFavorite, paramInt).progressBarColor(Theme.getFriendlyPrimaryColor(paramActivity, 2131100478)).progressBarHeight(dpToPx(paramActivity, 2)).showProgressBar(true).showDivider(true).gradientDivider(true).dividerHeight(dpToPx(paramActivity, 6)).dividerColorRes(2131099962).statusBarColor(Theme.getFriendlyPrimaryColor(paramActivity, 2131100479)).toolbarColor(Theme.getFriendlyPrimaryColor(paramActivity, 2131100478)).titleColorRes(2131099967).urlColorRes(2131099963).iconDefaultColorRes(2131099967).progressBarColorRes(2131099967).swipeRefreshColor(Theme.getFriendlyPrimaryColor(paramActivity, 2131100478)).menuDropShadowSize(dpToPx(paramActivity, 5)).menuDropShadowColorRes(2131099960).webViewJavaScriptEnabled(true).webViewGeolocationEnabled(true).webViewAllowContentAccess(true).webViewAllowFileAccess(true).webViewDisplayZoomControls(true).webViewLoadWithOverviewMode(true).webViewSupportMultipleWindows(true).theme(2131820778).setCustomAnimations(2130771987, 2130771990, 2130771983, 2130771984).show(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramAbstractFavorite = new StringBuilder();
      paramAbstractFavorite.append("friendly");
      paramAbstractFavorite.append(paramString.getMessage());
      Log.e("shouldOverrideUrlLoad", paramAbstractFavorite.toString());
    }
  }
  
  public static void launchFriendlyTwitterVersion(Activity paramActivity)
  {
    if (paramActivity == null) {
      return;
    }
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=io.friendly.twitter")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=io.friendly.twitter&referrer=utm_source%3DFriendly%2520App")));
      localActivityNotFoundException.printStackTrace();
    }
  }
  
  public static void launchGoogleSearch(Activity paramActivity, String paramString, int paramInt)
  {
    launchExternalURLFromFinest(GoogleFloatSearchView.getGoogleSearchURLFromQuery(paramString, null), null, paramInt, paramActivity);
    Tracking.trackGoogleSearchOpen(paramActivity, paramString);
  }
  
  public static void launchMainActivity(Activity paramActivity)
  {
    paramActivity.startActivity(new Intent(paramActivity, SplashActivity.class));
    paramActivity.overridePendingTransition(17432576, 17432577);
  }
  
  public static void launchManageFavoriteActivity(Activity paramActivity, View paramView)
  {
    Intent localIntent = new Intent(paramActivity, ManageFavoriteActivity.class);
    if (Build.VERSION.SDK_INT >= 21)
    {
      String str = paramActivity.getString(2131755063);
      paramView = ActivityOptionsCompat.makeSceneTransitionAnimation(paramActivity, paramView, str);
      localIntent.putExtra(ManageFavoriteActivity.TRANSITION_VIEW_NAME, str);
      paramActivity.startActivityForResult(localIntent, 1003, paramView.toBundle());
      return;
    }
    paramActivity.startActivityForResult(localIntent, 1003);
  }
  
  protected static void launchMessengerClient(Activity paramActivity, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      new Handler().postDelayed(new Util.1(paramActivity, paramIntent), 350L);
      return;
    }
    Toast.makeText(paramActivity, paramActivity.getString(2131755370), 1).show();
  }
  
  public static void launchOnePageActivity(Activity paramActivity, Intent paramIntent)
  {
    paramActivity.startActivityForResult(paramIntent, 1003);
    paramActivity.overridePendingTransition(2130771987, 2130771990);
  }
  
  public static void launchOnePageActivityURL(Activity paramActivity, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (checkMessengerClient(paramActivity, paramString1, paramString2)) {
      return;
    }
    if (checkWebViewIntent(paramActivity, paramString2)) {
      return;
    }
    Intent localIntent = new Intent(paramActivity, OnePageActivity.class);
    localIntent.putExtra(OnePageActivity.LEVEL, paramString1);
    localIntent.putExtra(OnePageActivity.URL, paramString2);
    localIntent.putExtra(OnePageActivity.MESSAGE_BADGE, paramInt1);
    localIntent.putExtra(OnePageActivity.NOTIFICATION_BADGE, paramInt2);
    paramActivity.startActivityForResult(localIntent, 1003);
    paramActivity.overridePendingTransition(2130771987, 2130771990);
  }
  
  public static void launchSuggestionURL(Activity paramActivity, String paramString, Suggestion paramSuggestion, int paramInt)
  {
    if (isFacebookLink(paramString))
    {
      paramSuggestion = new Intent(paramActivity, OnePageActivity.class);
      paramSuggestion.putExtra(OnePageActivity.URL, paramString);
      launchOnePageActivity(paramActivity, paramSuggestion);
    }
    else
    {
      launchSuggestionURLFromFinest(paramString, paramInt, paramActivity);
    }
    Tracking.trackSuggestionOpen(paramActivity, paramString);
  }
  
  public static void launchSuggestionURLFromFinest(String paramString, int paramInt, Activity paramActivity)
  {
    launchExternalURLFromFinest(paramString, null, paramInt, paramActivity);
  }
  
  public static void launchThankActivity(Activity paramActivity)
  {
    paramActivity.startActivity(new Intent(paramActivity, ThankYouActivity.class));
    paramActivity.overridePendingTransition(17432576, 17432577);
  }
  
  public static void launchWhatsNew(Activity paramActivity, String paramString)
  {
    if (paramActivity != null)
    {
      if (paramActivity.isFinishing()) {
        return;
      }
      BottomDialog.Builder localBuilder = new BottomDialog.Builder(paramActivity).setBackground(2131231328);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramActivity.getString(2131755050));
      localStringBuilder.append(" ");
      localStringBuilder.append(getVersionName(paramActivity));
      localBuilder.setTitle(localStringBuilder.toString()).setContent(paramString).setPositiveText(paramActivity.getString(17039370)).setPositiveBackgroundColor(Theme.getFriendlyPrimaryColor(paramActivity, 2131100478)).setNegativeText(2131755480).setNegativeTextColor(Theme.getFriendlyPrimaryColor(paramActivity, 2131100478)).onNegative(new -..Lambda.Util.hNCPSOTZaEwtaNv5ZbffsJhpuTc(paramActivity)).show();
      return;
    }
  }
  
  public static String md5(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      StringBuffer localStringBuffer = new StringBuffer();
      int i = 0;
      while (i < localObject.length)
      {
        localStringBuffer.append(Integer.toHexString(localObject[i] & 0xFF));
        i += 1;
      }
      localObject = localStringBuffer.toString();
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return paramString;
  }
  
  public static void openPageFromIntent(Activity paramActivity, String paramString)
  {
    String str;
    if (paramString.startsWith("https://facebook"))
    {
      str = paramString.replace("https://facebook", "https://m.facebook");
    }
    else if (paramString.startsWith("https://www.facebook"))
    {
      str = paramString.replace("https://www.facebook", "https://m.facebook");
    }
    else if (paramString.startsWith("http://www.facebook"))
    {
      str = paramString.replace("http://www.facebook", "http://m.facebook");
    }
    else
    {
      str = paramString;
      if (paramString.startsWith("http://facebook")) {
        str = paramString.replace("http://facebook", "http://m.facebook");
      }
    }
    paramString = new Intent(paramActivity, OnePageActivity.class);
    paramString.putExtra(OnePageActivity.URL, str);
    paramString.addFlags(4194304);
    paramActivity.startActivityForResult(paramString, 1003);
    paramActivity.overridePendingTransition(2130771987, 2130771990);
  }
  
  public static Date parseDate(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm", Locale.US);
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (ParseException paramString)
    {
      for (;;) {}
    }
    return new Date(0L);
  }
  
  public static float pxToDp(Context paramContext, float paramFloat)
  {
    return paramFloat / paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static int randomAdBucketWeight()
  {
    return randomRange(-1, 1);
  }
  
  public static int randomRange(int paramInt1, int paramInt2)
  {
    return new Random().nextInt(paramInt2 - paramInt1 + 1) + paramInt1;
  }
  
  public static void relaunchMainActivity(Activity paramActivity)
  {
    Intent localIntent = new Intent(paramActivity, SplashActivity.class);
    localIntent.addFlags(67108864);
    localIntent.addFlags(65536);
    paramActivity.startActivity(localIntent);
    paramActivity.overridePendingTransition(0, 0);
  }
  
  static String replaceAppName(Context paramContext, String paramString)
  {
    String str = paramString;
    if (paramString.contains("Friendly")) {
      str = paramString.replace("Friendly", paramContext.getText(2131755070));
    }
    return str;
  }
  
  public static void requestLocationPermission(Activity paramActivity)
  {
    if (hasLocationPermission(paramActivity)) {
      return;
    }
    ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 2);
  }
  
  public static void requestStoragePermission(Activity paramActivity)
  {
    if (!hasStoragePermission(paramActivity)) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
    }
  }
  
  public static List<Spannable> reverseSpannableList(List<Spannable> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramList.size() - 1;
    while (i >= 0)
    {
      localArrayList.add(paramList.get(i));
      i -= 1;
    }
    return localArrayList;
  }
  
  public static List<String> reverseStringList(List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramList.size() - 1;
    while (i >= 0)
    {
      localArrayList.add(paramList.get(i));
      i -= 1;
    }
    return localArrayList;
  }
  
  public static List<Spannable> reverseStringListToSpannableList(List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramList.size() - 1;
    while (i >= 0)
    {
      localArrayList.add(new SpannableString((CharSequence)paramList.get(i)));
      i -= 1;
    }
    return localArrayList;
  }
  
  public static void saveBottomIntro(Context paramContext, boolean paramBoolean)
  {
    getApplicationPreferencesEditor(paramContext).putBoolean(PREFERENCE_BOTTOM_INTRO, paramBoolean).commit();
  }
  
  public static void saveCurrentUser(Context paramContext, String paramString)
  {
    getApplicationPreferencesEditor(paramContext).putString(CURRENT_USER, paramString).commit();
  }
  
  public static void saveOnBoarding(Context paramContext, boolean paramBoolean)
  {
    getApplicationPreferencesEditor(paramContext).putBoolean(PREFERENCE_ONBOARDING, paramBoolean).commit();
  }
  
  public static void saveRateDialogShown(Context paramContext, boolean paramBoolean)
  {
    getApplicationPreferencesEditor(paramContext).putBoolean(PREFERENCE_RATE_DIALOG, paramBoolean).commit();
  }
  
  public static void saveShowcaseDrawer(Context paramContext, boolean paramBoolean)
  {
    getApplicationPreferencesEditor(paramContext).putBoolean(SHOWCASE_DRAWER, paramBoolean).commit();
  }
  
  public static void saveShowcaseFriendlySettings(Context paramContext, boolean paramBoolean)
  {
    getApplicationPreferencesEditor(paramContext).putBoolean(SHOWCASE_SETTINGS, paramBoolean).commit();
  }
  
  public static void saveShowcaseTabs(Context paramContext, String paramString)
  {
    getApplicationPreferencesEditor(paramContext).putString(PREFERENCE_SHOWCASE_TABS, paramString).commit();
  }
  
  public static void saveWhatsNew(Context paramContext, String paramString)
  {
    getApplicationPreferencesEditor(paramContext).putString(PREFERENCE_WHATS_NEW, paramString).commit();
  }
  
  public static void scrollToTop(WebView paramWebView)
  {
    paramWebView = ObjectAnimator.ofInt(paramWebView, "scrollY", new int[] { paramWebView.getScrollY(), 0 });
    paramWebView.setDuration(300L);
    paramWebView.start();
  }
  
  public static void sendFeedBackEmail(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { Build.getSupportEmail() });
    localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getString(2131755451));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getDeviceConfig());
    localStringBuilder.append(paramString);
    localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
    localIntent.setType("message/rfc822");
    try
    {
      paramContext.startActivity(Intent.createChooser(localIntent, "Send email using..."));
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      for (;;) {}
    }
    Toast.makeText(paramContext.getApplicationContext(), "No email clients installed.", 0).show();
  }
  
  public static void setAlternativeApps(String paramString)
  {
    ALTERNATIVE_APPS = paramString;
  }
  
  public static void setDesktopMode(Context paramContext, String paramString, WebSettings paramWebSettings, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramContext = UserGlobalPreference.USER_AGENT_DESKTOP;
    } else {
      paramContext = UserGlobalPreference.decideUserAgent(paramContext, paramString);
    }
    paramWebSettings.setUserAgentString(paramContext);
    paramWebSettings.setUseWideViewPort(paramBoolean);
    paramWebSettings.setLoadWithOverviewMode(paramBoolean);
    paramWebSettings.setSupportZoom(false);
    paramWebSettings.setBuiltInZoomControls(false);
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  public static void setUpWebViewSettings(BaseActivity paramBaseActivity, WebSettings paramWebSettings, WebView paramWebView, String paramString, boolean paramBoolean)
  {
    CookieManager.getInstance().setAcceptCookie(true);
    if (Build.VERSION.SDK_INT >= 21) {
      CookieManager.getInstance().setAcceptThirdPartyCookies(paramWebView, true);
    }
    paramWebSettings.setJavaScriptEnabled(true);
    paramWebSettings.setAllowFileAccess(true);
    paramWebSettings.setSaveFormData(true);
    paramWebSettings.setDomStorageEnabled(true);
    paramWebSettings.setDatabaseEnabled(true);
    paramWebSettings.setAppCachePath(paramBaseActivity.getApplicationContext().getCacheDir().getAbsolutePath());
    paramWebSettings.setAppCacheEnabled(true);
    paramWebSettings.setCacheMode(-1);
    paramWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    paramWebSettings.setSavePassword(true);
    paramWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    paramWebView.setLayerType(2, null);
    updateWebViewSettings(paramBaseActivity, paramWebSettings);
    paramWebView.addJavascriptInterface(new JavascriptInterface(paramBaseActivity, paramString), "_fas_");
    setDesktopMode(paramBaseActivity, paramString, paramWebSettings, paramBoolean);
  }
  
  public static void shareImage(Activity paramActivity, Uri paramUri)
  {
    if (paramUri != null)
    {
      shareImageFromURL(paramActivity, paramUri.toString());
      return;
    }
    Toast.makeText(paramActivity, paramActivity.getString(2131755189), 1).show();
  }
  
  public static void shareImageFromURL(Activity paramActivity, String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      Intent localIntent = new Intent(paramActivity, OnePageActivity.class);
      localIntent.putExtra(OnePageActivity.URL, "https://m.facebook.com/home.php?sk=h_chr");
      localIntent.putExtra(OnePageActivity.LEVEL, Level.SHARER_PICTURE);
      localIntent.putExtra(OnePageActivity.PICTURE_URI, paramString);
      localIntent.addFlags(4194304);
      paramActivity.startActivityForResult(localIntent, 1042);
      paramActivity.overridePendingTransition(2130771987, 2130771990);
      Tracking.trackSharing(paramActivity, "Picture - 1");
      return;
    }
    Toast.makeText(paramActivity, paramActivity.getString(2131755189), 1).show();
  }
  
  public static void shareImages(Activity paramActivity, ArrayList<Uri> paramArrayList)
  {
    if (paramArrayList != null)
    {
      Object localObject = new Intent(paramActivity, OnePageActivity.class);
      ((Intent)localObject).putExtra(OnePageActivity.URL, "https://m.facebook.com/home.php?sk=h_chr");
      ((Intent)localObject).putExtra(OnePageActivity.LEVEL, Level.SHARER_PICTURE);
      ((Intent)localObject).putParcelableArrayListExtra(OnePageActivity.PICTURE_URIS, paramArrayList);
      ((Intent)localObject).addFlags(4194304);
      paramActivity.startActivityForResult((Intent)localObject, 1042);
      paramActivity.overridePendingTransition(2130771987, 2130771990);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Picture - ");
      ((StringBuilder)localObject).append(paramArrayList.size());
      Tracking.trackSharing(paramActivity, ((StringBuilder)localObject).toString());
      return;
    }
    Toast.makeText(paramActivity, paramActivity.getString(2131755189), 1).show();
  }
  
  public static void sharePage(Activity paramActivity, String paramString)
  {
    if (paramString.length() > 0)
    {
      Object localObject = paramString;
      if (!Patterns.WEB_URL.matcher(paramString.toLowerCase()).matches())
      {
        localObject = paramString;
        if (paramString.contains("http"))
        {
          paramString = paramString.substring(paramString.indexOf("http"));
          localObject = paramString;
          if (paramString.contains(" ")) {
            localObject = paramString.substring(0, paramString.indexOf(" "));
          }
        }
      }
      try
      {
        paramString = new StringBuilder();
        paramString.append("https://www.facebook.com/sharer.php?u=");
        paramString.append(URLEncoder.encode((String)localObject, "utf-8"));
        localObject = paramString.toString();
      }
      catch (UnsupportedEncodingException paramString)
      {
        paramString.printStackTrace();
        localObject = "";
      }
      if (((String)localObject).contains("mobile.facebook.com"))
      {
        paramString = ((String)localObject).replace("mobile.facebook.com", "www.facebook.com");
      }
      else
      {
        paramString = (String)localObject;
        if (((String)localObject).contains("m.facebook.com")) {
          paramString = ((String)localObject).replace("m.facebook.com", "www.facebook.com");
        }
      }
      localObject = new Intent(paramActivity, OnePageActivity.class);
      ((Intent)localObject).putExtra(OnePageActivity.URL, paramString);
      ((Intent)localObject).putExtra(OnePageActivity.LEVEL, Level.SHARER_LINK);
      ((Intent)localObject).addFlags(4194304);
      paramActivity.startActivityForResult((Intent)localObject, 1003);
      paramActivity.overridePendingTransition(2130771987, 2130771990);
      Tracking.trackSharing(paramActivity, "Link");
    }
  }
  
  public static void sharePageFromContext(Context paramContext, String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("https://www.facebook.com/sharer.php?u=");
    ((StringBuilder)localObject).append(paramString);
    localObject = ((StringBuilder)localObject).toString();
    if (((String)localObject).contains("mobile.facebook.com"))
    {
      paramString = ((String)localObject).replace("mobile.facebook.com", "www.facebook.com");
    }
    else
    {
      paramString = (String)localObject;
      if (((String)localObject).contains("m.facebook.com")) {
        paramString = ((String)localObject).replace("m.facebook.com", "www.facebook.com");
      }
    }
    localObject = new Intent(paramContext, OnePageActivity.class);
    ((Intent)localObject).putExtra(OnePageActivity.LEVEL, Level.SHARER_LINK);
    ((Intent)localObject).putExtra(OnePageActivity.URL, paramString);
    ((Intent)localObject).setFlags(268435456);
    Tracking.trackSharing(paramContext, "Link");
    paramContext.startActivity((Intent)localObject);
  }
  
  public static void unlockTimer(Activity paramActivity)
  {
    LockManager localLockManager = LockManager.getInstance();
    if (localLockManager != null)
    {
      localLockManager.enableAppLock(paramActivity, CustomPinActivity.class);
      int j = UserPreference.getValueFromLockInterval(paramActivity, UserPreference.getLockInterval(paramActivity));
      int i = 120000;
      if (j >= 120000) {
        i = UserPreference.getValueFromLockInterval(paramActivity, UserPreference.getLockInterval(paramActivity));
      }
      localLockManager.getAppLock().setTimeout(i);
    }
  }
  
  public static void updateWebViewSettings(Context paramContext, WebSettings paramWebSettings)
  {
    paramWebSettings.setLoadsImagesAutomatically(true);
    paramWebSettings.setGeolocationEnabled(true);
    paramWebSettings.setGeolocationDatabasePath(paramContext.getFilesDir().getPath());
  }
}
