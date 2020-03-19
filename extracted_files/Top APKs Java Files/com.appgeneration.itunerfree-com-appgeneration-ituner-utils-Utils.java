package com.appgeneration.ituner.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.design.R.id;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.appgeneration.ituner.MyApplication;
import com.appgeneration.ituner.analytics.AnalyticsManager;
import com.appgeneration.ituner.application.AppSettingsManager;
import com.appgeneration.ituner.billing.BillingManager;
import com.appgeneration.ituner.location.MytunerLocationManager;
import com.appgeneration.ituner.media.service.MediaService;
import com.appgeneration.ituner.media.service.MediaService.Binder;
import com.appgeneration.ituner.navigation.NavigationListFragmentAdapter;
import com.appgeneration.ituner.preference.Preferences;
import com.appgeneration.itunerlib.R.layout;
import com.appgeneration.itunerlib.R.string;
import com.appgeneration.mytuner.dataprovider.db.greendao.DaoSession;
import com.appgeneration.mytuner.dataprovider.db.objects.Country;
import com.appgeneration.mytuner.dataprovider.db.objects.Music;
import com.appgeneration.mytuner.dataprovider.db.objects.NavigationEntityItem;
import com.appgeneration.mytuner.dataprovider.db.objects.Playable;
import com.appgeneration.mytuner.dataprovider.db.objects.Podcast;
import com.appgeneration.mytuner.dataprovider.db.objects.PodcastEpisode;
import com.appgeneration.mytuner.dataprovider.helpers.EventsHelper;
import com.bosch.myspin.serversdk.MySpinServerSDK;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class Utils
{
  private static final String EXP_PATH = "/Android/obb/";
  private static HashMap<Context, ServiceBinder> sConnectionMap = new HashMap();
  
  public Utils() {}
  
  public static ServiceToken bindToService(Activity paramActivity)
  {
    return bindToService(paramActivity, null);
  }
  
  public static ServiceToken bindToService(Activity paramActivity, ServiceConnection paramServiceConnection)
  {
    Activity localActivity2 = paramActivity.getParent();
    Activity localActivity1 = localActivity2;
    if (localActivity2 == null) {
      localActivity1 = paramActivity;
    }
    paramActivity = new ContextWrapper(localActivity1);
    paramActivity.startService(new Intent(paramActivity, MediaService.class));
    paramServiceConnection = new ServiceBinder(paramServiceConnection);
    if (paramActivity.bindService(new Intent().setClass(paramActivity, MediaService.class), paramServiceConnection, 0))
    {
      sConnectionMap.put(paramActivity, paramServiceConnection);
      return new ServiceToken(paramActivity);
    }
    Log.e("Music", "Failed to bind to service");
    return null;
  }
  
  public static ServiceToken bindToService(Service paramService, ServiceConnection paramServiceConnection)
  {
    paramService = new ContextWrapper(paramService);
    paramService.startService(new Intent(paramService, MediaService.class));
    paramServiceConnection = new ServiceBinder(paramServiceConnection);
    if (paramService.bindService(new Intent().setClass(paramService, MediaService.class), paramServiceConnection, 0))
    {
      sConnectionMap.put(paramService, paramServiceConnection);
      return new ServiceToken(paramService);
    }
    Log.e("Music", "Failed to bind to service");
    return null;
  }
  
  public static void buyPro(Context paramContext, String paramString)
  {
    Object localObject = BillingManager.getInstance();
    if (((BillingManager)localObject).shouldUseInApp())
    {
      ((BillingManager)localObject).purchaseInapp();
      AnalyticsManager.getInstance().reportEcommerceCheckoutStep(2);
    }
    for (;;)
    {
      return;
      if (paramString != null) {
        try
        {
          if (!paramString.isEmpty())
          {
            localObject = new Intent("android.intent.action.VIEW");
            ((Intent)localObject).setData(Uri.parse(paramString));
            paramContext.startActivity((Intent)localObject);
            return;
          }
        }
        catch (Exception paramContext) {}
      }
    }
  }
  
  public static void buySong(Context paramContext, MediaService paramMediaService)
  {
    if ((paramContext == null) || (paramMediaService == null)) {
      return;
    }
    Object localObject = paramMediaService.getCurrentMetadataString();
    paramMediaService = paramMediaService.getCurrentMusic();
    if ((localObject != null) && (!((String)localObject).isEmpty())) {}
    try
    {
      paramMediaService = paramContext.getString(R.string.url_buy_song, new Object[] { Uri.encode((String)localObject) });
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setData(Uri.parse(paramMediaService));
      paramContext.startActivity((Intent)localObject);
      for (;;)
      {
        AnalyticsManager.getInstance().reportGoalReached("FOLLOWED_ITUNES_LINK", 2, R.string.pref_key_goal_followed_itunes_link);
        return;
        if (paramMediaService != null)
        {
          paramMediaService = paramContext.getString(R.string.url_buy_song, new Object[] { Uri.encode(paramMediaService.getName() + " " + paramMediaService.getArtist()) });
          localObject = new Intent("android.intent.action.VIEW");
          ((Intent)localObject).setData(Uri.parse(paramMediaService));
          paramContext.startActivity((Intent)localObject);
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
  }
  
  public static void callTrackingURL(String paramString)
  {
    if (paramString == null) {
      return;
    }
    new OkHttpClient().newCall(new Request.Builder().url(paramString).build()).enqueue(new Callback()
    {
      public final void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
      {
        String str = this.val$trackingURL;
        if (paramAnonymousIOException != null) {}
        for (paramAnonymousCall = paramAnonymousIOException.getMessage();; paramAnonymousCall = "null")
        {
          Log.e("Utils.callTrackingURL", String.format("Failed when calling tracking url: %s (%s)", new Object[] { str, paramAnonymousCall }));
          return;
        }
      }
      
      public final void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
        throws IOException
      {
        Log.e("Utils.callTrackingURL", "Tracking url success: " + this.val$trackingURL);
      }
    });
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    if (paramContext == null) {
      return paramInt;
    }
    paramContext = paramContext.getResources().getDisplayMetrics();
    return Math.round(paramInt * (paramContext.xdpi / 160.0F));
  }
  
  public static String[] getAPKExpansionFiles(Context paramContext, int paramInt1, int paramInt2)
  {
    Object localObject1 = paramContext.getPackageName();
    paramContext = new Vector();
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      Object localObject2 = Environment.getExternalStorageDirectory();
      File localFile = new File(((File)localObject2).toString() + "/Android/obb/" + (String)localObject1);
      Log.e(Utils.class.getSimpleName(), "PATH: " + ((File)localObject2).toString() + "/Android/obb/" + (String)localObject1);
      if (!localFile.exists()) {
        break label329;
      }
      if (paramInt1 > 0)
      {
        localObject2 = localFile + File.separator + "main." + paramInt1 + "." + (String)localObject1 + ".obb";
        Log.e(Utils.class.getSimpleName(), "MAIN PATH: " + (String)localObject2);
        if (new File((String)localObject2).isFile()) {
          paramContext.add(localObject2);
        }
      }
      if (paramInt2 > 0)
      {
        localObject1 = localFile + File.separator + "patch." + paramInt1 + "." + (String)localObject1 + ".obb";
        Log.e(Utils.class.getSimpleName(), "MAIN PATH: " + (String)localObject1);
        if (new File((String)localObject1).isFile()) {
          paramContext.add(localObject1);
        }
      }
    }
    for (;;)
    {
      localObject1 = new String[paramContext.size()];
      paramContext.toArray((Object[])localObject1);
      return localObject1;
      label329:
      Log.e(Utils.class.getSimpleName(), "The path does not exist!!!");
    }
  }
  
  public static Date getCurrentDate()
  {
    Date localDate = new Date();
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(Locale.UK);
    localGregorianCalendar.setTime(localDate);
    return localGregorianCalendar.getTime();
  }
  
  public static long getCurrentTimeInSeconds()
  {
    return System.currentTimeMillis() / 1000L;
  }
  
  public static String getLogcatDump()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
      StringBuilder localStringBuilder = new StringBuilder();
      for (;;)
      {
        String str2 = localBufferedReader.readLine();
        if (str2 == null) {
          break;
        }
        localStringBuilder.append(str2);
      }
      str1 = localStringBuilder.toString();
    }
    catch (Exception localException)
    {
      return "";
    }
    String str1;
    return str1;
  }
  
  public static String getNetworkChangedMessage(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      if (localNetworkInfo.getType() == 1) {
        return paramContext.getString(R.string.TRANS_NETWORK_OVER_WIFI);
      }
      if (localNetworkInfo.getType() == 0)
      {
        switch (localNetworkInfo.getSubtype())
        {
        default: 
          return paramContext.getString(R.string.TRANS_NETWORK_OVER_DATA);
        case 1: 
        case 2: 
        case 4: 
        case 7: 
        case 11: 
          return paramContext.getString(R.string.TRANS_NETWORK_OVER_2G);
        case 3: 
        case 5: 
        case 6: 
        case 8: 
        case 9: 
        case 10: 
        case 12: 
        case 14: 
        case 15: 
          return paramContext.getString(R.string.TRANS_NETWORK_OVER_3G);
        }
        return paramContext.getString(R.string.TRANS_NETWORK_OVER_4G);
      }
      return null;
    }
    return paramContext.getString(R.string.TRANS_NETWORK_OVER_NONE);
  }
  
  public static int getOnboardingBehavior()
  {
    return new SecureRandom().nextInt(2);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public static String getWeekDayFromInt(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(7, paramInt);
    return new SimpleDateFormat("EEEE").format(localCalendar.getTime());
  }
  
  public static boolean isAffectedSamsungDevice()
  {
    return Pattern.compile("(SM\\-(N910|N920|G935))").matcher(Build.MODEL).find();
  }
  
  public static boolean isAppturboUnlockable(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)((Iterator)localObject).next();
    } while ((!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) && (!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015")) && (!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appofthenight")) && (!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.gameoftheday")));
    do
    {
      return true;
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setFlags(268435456);
      ((Intent)localObject).setData(Uri.parse("appturbo://check"));
    } while (paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 65536).size() > 0);
    return false;
  }
  
  public static boolean isNetworkConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean isTakingScreenshots()
  {
    try
    {
      Class.forName("com.appgeneration.ituner.test.PhoneScreenshots");
      Class.forName("com.appgeneration.ituner.test.TabletScreenshots");
      Class.forName("com.appgeneration.ituner.test.TVScreenshots");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return false;
  }
  
  public static String makeTimeString(Context paramContext, long paramLong)
  {
    if (paramLong < 3600L) {}
    for (int i = R.string.durationformatshort;; i = R.string.durationformatlong)
    {
      long l1 = paramLong / 3600L;
      long l2 = paramLong / 60L;
      long l3 = paramLong / 60L;
      if (paramContext == null) {
        break;
      }
      return paramContext.getString(i, new Object[] { Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l3 % 60L), Long.valueOf(paramLong), Long.valueOf(paramLong % 60L) });
    }
    return "";
  }
  
  public static String makeTimeStringWithUnit(long paramLong)
  {
    if (paramLong > 3600L) {
      return paramLong / 3600L + " hours";
    }
    if (paramLong > 60L) {
      return paramLong / 60L + " minutes";
    }
    return paramLong + " seconds";
  }
  
  public static void populateCountriesDialogAdapter(Context paramContext, NavigationListFragmentAdapter paramNavigationListFragmentAdapter)
  {
    if (paramNavigationListFragmentAdapter == null) {
      return;
    }
    Object localObject2 = MyApplication.getInstance().getDaoSession();
    List localList = Country.getAll((DaoSession)localObject2);
    Object localObject1 = Country.getByCountryCode((DaoSession)localObject2, paramContext.getResources().getConfiguration().locale.getCountry().toLowerCase(Locale.US));
    if (localObject1 != null) {
      localList.add(0, localObject1);
    }
    paramContext = MytunerLocationManager.getLatitude();
    Double localDouble = MytunerLocationManager.getLongitude();
    if ((paramContext != null) && (localDouble != null))
    {
      paramContext = Country.getClosestCountry((DaoSession)localObject2, paramContext, localDouble);
      if (paramContext != null)
      {
        localObject2 = paramContext.getCode();
        if (localObject1 == null) {
          break label143;
        }
      }
    }
    label143:
    for (localObject1 = ((Country)localObject1).getCode();; localObject1 = "")
    {
      if (!((String)localObject2).equals(localObject1)) {
        localList.add(0, paramContext);
      }
      paramNavigationListFragmentAdapter.setNotifyOnChange(false);
      paramNavigationListFragmentAdapter.clear();
      paramNavigationListFragmentAdapter.addAll(localList);
      paramNavigationListFragmentAdapter.notifyDataSetChanged();
      return;
      paramContext = null;
      break;
    }
  }
  
  public static int pxToDp(Context paramContext, int paramInt)
  {
    if (paramContext == null) {
      return paramInt;
    }
    return Math.round(paramInt / (paramContext.getResources().getDisplayMetrics().densityDpi / 160));
  }
  
  /* Error */
  public static void reportAdblockerPresence()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: new 370	java/io/BufferedReader
    //   7: dup
    //   8: new 372	java/io/InputStreamReader
    //   11: dup
    //   12: new 683	java/io/FileInputStream
    //   15: dup
    //   16: ldc_w 685
    //   19: invokespecial 686	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   22: invokespecial 393	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   25: invokespecial 396	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   28: astore_0
    //   29: aload_0
    //   30: invokevirtual 399	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   33: astore_1
    //   34: aload_1
    //   35: ifnull +36 -> 71
    //   38: aload_1
    //   39: ldc_w 688
    //   42: invokevirtual 692	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   45: ifeq -16 -> 29
    //   48: invokestatic 126	com/appgeneration/ituner/analytics/AnalyticsManager:getInstance	()Lcom/appgeneration/ituner/analytics/AnalyticsManager;
    //   51: ldc_w 694
    //   54: ldc_w 696
    //   57: ldc_w 401
    //   60: lconst_0
    //   61: invokevirtual 700	com/appgeneration/ituner/analytics/AnalyticsManager:reportEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V
    //   64: aload_0
    //   65: invokevirtual 703	java/io/BufferedReader:close	()V
    //   68: return
    //   69: astore_0
    //   70: return
    //   71: aload_0
    //   72: invokevirtual 703	java/io/BufferedReader:close	()V
    //   75: return
    //   76: astore_0
    //   77: return
    //   78: astore_0
    //   79: aload_1
    //   80: astore_0
    //   81: aload_0
    //   82: ifnull -14 -> 68
    //   85: aload_0
    //   86: invokevirtual 703	java/io/BufferedReader:close	()V
    //   89: return
    //   90: astore_0
    //   91: return
    //   92: astore_0
    //   93: aload_2
    //   94: astore_1
    //   95: aload_1
    //   96: ifnull +7 -> 103
    //   99: aload_1
    //   100: invokevirtual 703	java/io/BufferedReader:close	()V
    //   103: aload_0
    //   104: athrow
    //   105: astore_1
    //   106: goto -3 -> 103
    //   109: astore_2
    //   110: aload_0
    //   111: astore_1
    //   112: aload_2
    //   113: astore_0
    //   114: goto -19 -> 95
    //   117: astore_1
    //   118: goto -37 -> 81
    // Local variable table:
    //   start	length	slot	name	signature
    //   28	37	0	localBufferedReader	BufferedReader
    //   69	3	0	localIOException1	IOException
    //   76	1	0	localIOException2	IOException
    //   78	1	0	localIOException3	IOException
    //   80	6	0	localObject1	Object
    //   90	1	0	localIOException4	IOException
    //   92	19	0	localObject2	Object
    //   113	1	0	localObject3	Object
    //   3	97	1	localObject4	Object
    //   105	1	1	localIOException5	IOException
    //   111	1	1	localObject5	Object
    //   117	1	1	localIOException6	IOException
    //   1	93	2	localObject6	Object
    //   109	4	2	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   64	68	69	java/io/IOException
    //   71	75	76	java/io/IOException
    //   4	29	78	java/io/IOException
    //   85	89	90	java/io/IOException
    //   4	29	92	finally
    //   99	103	105	java/io/IOException
    //   29	34	109	finally
    //   38	64	109	finally
    //   29	34	117	java/io/IOException
    //   38	64	117	java/io/IOException
  }
  
  public static void share(Context paramContext, MediaService paramMediaService)
  {
    if ((paramContext == null) || (paramMediaService == null)) {
      return;
    }
    paramContext.getString(R.string.app_name);
    Object localObject1 = paramMediaService.getCurrentPlayable();
    Object localObject2 = paramMediaService.getCurrentMusic();
    String str = AppSettingsManager.getInstance().getAppUrl();
    if ((localObject1 instanceof PodcastEpisode))
    {
      paramMediaService = ((PodcastEpisode)localObject1).getPodcast().getTitle(paramContext).toString();
      label59:
      if (localObject2 == null) {
        break label239;
      }
      localObject1 = ((Music)localObject2).getTitle(paramContext).toString();
      label74:
      if (localObject2 == null) {
        break label244;
      }
      localObject2 = ((Music)localObject2).getSubTitle(paramContext).toString();
      label89:
      if (paramMediaService == null) {
        break label247;
      }
      if (localObject1 == null) {
        break label249;
      }
      localObject1 = String.format(Locale.US, "%s - %s", new Object[] { localObject2, localObject1 });
    }
    label239:
    label244:
    label247:
    label249:
    for (paramMediaService = paramContext.getString(R.string.TRANS_SHARE_TEXT_SOURCE_AND_SONG, new Object[] { localObject1, paramMediaService, str });; paramMediaService = paramContext.getString(R.string.TRANS_SHARE_TEXT_SOURCE, new Object[] { paramMediaService, str }))
    {
      localObject1 = new Intent();
      ((Intent)localObject1).setAction("android.intent.action.SEND");
      ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", "myTuner Android");
      ((Intent)localObject1).putExtra("android.intent.extra.TEXT", paramMediaService);
      ((Intent)localObject1).setType("text/plain");
      paramContext.startActivity((Intent)localObject1);
      AnalyticsManager.getInstance().reportEvent("SOCIAL", "SHARE", "", 0L);
      BadgesHelpers.setTaskCompleted(R.string.badge_share);
      return;
      if (localObject1 != null)
      {
        paramMediaService = ((Playable)localObject1).getTitle(paramContext).toString();
        break label59;
      }
      paramMediaService = null;
      break label59;
      localObject1 = null;
      break label74;
      localObject2 = null;
      break label89;
      break;
    }
  }
  
  public static void showCountriesDialog(final Context paramContext, final boolean paramBoolean)
  {
    Object localObject = new NavigationListFragmentAdapter(paramContext, R.layout.row_icon_and_text);
    populateCountriesDialogAdapter(paramContext, (NavigationListFragmentAdapter)localObject);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramContext.getString(R.string.TRANS_PREF_DEFAULT_COUNTRY));
    if (!paramBoolean) {
      localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      });
    }
    localBuilder.setSingleChoiceItems((ListAdapter)localObject, 0, new DialogInterface.OnClickListener()
    {
      public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Object localObject = (NavigationEntityItem)this.val$adapter.getItem(paramAnonymousInt);
        if ((localObject instanceof Country))
        {
          localObject = ((Country)localObject).getCode();
          Preferences.setDefaultCountryCode((String)localObject);
          AnalyticsManager.getInstance().reportEvent("PREFERENCE_CHANGED", "CHANGED_COUNTRY", (String)localObject, 0L);
          AnalyticsManager.getInstance().reportGoalReached("CHANGED_COUNTRY", 7, R.string.pref_key_goal_changed_country);
          EventsHelper.sendEvent(paramContext, "com.appgeneration.mytuner.events.PREF_DEFAULT_COUNTRY_CHANGED");
          if (!paramBoolean) {
            BadgesHelpers.setTaskCompleted(R.string.badge_location);
          }
          paramAnonymousDialogInterface.dismiss();
        }
      }
    });
    localObject = new BroadcastReceiver()
    {
      public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        Utils.populateCountriesDialogAdapter(paramAnonymousContext, this.val$adapter);
      }
    };
    DialogInterface.OnDismissListener local8 = new DialogInterface.OnDismissListener()
    {
      public final void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        EventsHelper.unregisterReceiver(this.val$context, this.val$locationUpdateReceiver);
      }
    };
    localBuilder.P.mOnDismissListener = local8;
    EventsHelper.registerReceiver(paramContext, (BroadcastReceiver)localObject, new String[] { "com.appgeneration.mytuner.location.LOCATION_UPDATED" });
    localBuilder.show();
    Log.e("dialog_test", "showing countries dialog!");
  }
  
  public static void showSnackbar(Activity paramActivity, String paramString)
  {
    showSnackbar(paramActivity, paramString, null, null, null);
  }
  
  public static void showSnackbar(Activity paramActivity, String paramString1, String paramString2, Integer paramInteger, SnackbarCallback paramSnackbarCallback)
  {
    paramString1 = Snackbar.make(paramActivity.findViewById(16908290), paramString1, 0);
    if (paramInteger != null)
    {
      int i = dpToPx(paramActivity, 30);
      int j = dpToPx(paramActivity, 5);
      paramActivity = ActivityCompat.getDrawable(paramActivity, paramInteger.intValue());
      paramActivity.setBounds(0, 0, i, i);
      paramInteger = (TextView)paramString1.getView().findViewById(R.id.snackbar_text);
      if (paramInteger != null)
      {
        paramInteger.setCompoundDrawables(paramActivity, null, null, null);
        paramInteger.setCompoundDrawablePadding(j);
        paramInteger.setGravity(16);
      }
    }
    if ((paramString2 != null) && (paramSnackbarCallback != null))
    {
      paramString1.setAction(paramString2, new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          this.val$callback.onActionClicked();
        }
      });
      paramString1.setCallback(new Snackbar.Callback()
      {
        public final void onDismissed(Snackbar paramAnonymousSnackbar, int paramAnonymousInt)
        {
          if (paramAnonymousInt != 1) {
            this.val$callback.onDismissed();
          }
        }
      });
    }
    paramString1.show();
  }
  
  public static void showToast(Context paramContext, String paramString)
  {
    showToast(paramContext, paramString, 0);
  }
  
  public static void showToast(Context paramContext, final String paramString, final int paramInt)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.isEmpty()) || (MySpinServerSDK.sharedInstance().isConnected())) {
      return;
    }
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        Toast localToast = Toast.makeText(this.val$context, paramString, paramInt);
        localToast.setGravity(17, 0, 0);
        localToast.show();
      }
    });
  }
  
  public static void unbindFromService(ServiceToken paramServiceToken)
  {
    if (paramServiceToken == null) {
      Log.e("MusicUtils", "Trying to unbind with null token");
    }
    do
    {
      return;
      paramServiceToken = paramServiceToken.mWrappedContext;
      ServiceBinder localServiceBinder = (ServiceBinder)sConnectionMap.remove(paramServiceToken);
      if (localServiceBinder == null)
      {
        Log.e("MusicUtils", "Trying to unbind for unknown Context");
        return;
      }
      paramServiceToken.unbindService(localServiceBinder);
    } while (!sConnectionMap.isEmpty());
    MediaService.sService = null;
  }
  
  private static class ServiceBinder
    implements ServiceConnection
  {
    ServiceConnection mCallback;
    
    ServiceBinder(ServiceConnection paramServiceConnection)
    {
      this.mCallback = paramServiceConnection;
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      MediaService.sService = ((MediaService.Binder)paramIBinder).getService();
      if (this.mCallback != null) {
        this.mCallback.onServiceConnected(paramComponentName, paramIBinder);
      }
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      if (this.mCallback != null) {
        this.mCallback.onServiceDisconnected(paramComponentName);
      }
      MediaService.sService = null;
    }
  }
  
  public static class ServiceToken
  {
    ContextWrapper mWrappedContext;
    
    ServiceToken(ContextWrapper paramContextWrapper)
    {
      this.mWrappedContext = paramContextWrapper;
    }
  }
  
  public static abstract interface SnackbarCallback
  {
    public abstract void onActionClicked();
    
    public abstract void onDismissed();
  }
}
