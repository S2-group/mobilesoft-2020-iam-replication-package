package pgdating.mtph.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewStub;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.internal.LinkedTreeMap;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import pgdating.mtph.application.database.DatabaseAdapter;
import pgdating.mtph.application.handlers.MobileResponseHandler;
import pgdating.mtph.application.models.Messages;
import pgdating.mtph.application.models.Slider;

public abstract class Base
  extends MultiDexApplication
{
  protected static Application mInstance;
  public static Tracker mTracker;
  private BaseActivity activity = null;
  private String apiUrl = "";
  private String applicationName = "";
  private String[] baseApiError = new String[0];
  protected DatabaseAdapter db = null;
  private String facebookAppId = "";
  private String googleAppId = "";
  private FirebaseAnalytics mFirebaseAnalytics;
  protected String[] modules = new String[0];
  protected boolean useFirebaseAnalytics = false;
  protected boolean useGoogleAnalytics = false;
  
  public Base() {}
  
  public static ByteArrayInputStream asByteArray(Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(paramCompressFormat, 100, localByteArrayOutputStream);
    return new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
  }
  
  public static int dipToPixels(Context paramContext, float paramFloat)
  {
    return Math.round(TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics()));
  }
  
  public static String generateString(int paramInt)
  {
    Random localRandom = new Random();
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    int i = 0;
    while (i < paramInt)
    {
      localStringBuilder.append("0123456789qwertyuiopasdfghjklzxcvbnm".charAt(localRandom.nextInt("0123456789qwertyuiopasdfghjklzxcvbnm".length())));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String[] getActiveModules()
  {
    return getInstance().modules;
  }
  
  public static String[] getApiError()
  {
    return getInstance().baseApiError;
  }
  
  public static SharedPreferences getApplicationSharedPreferences(String paramString, int paramInt)
  {
    return mInstance.getSharedPreferences(paramString, paramInt);
  }
  
  public static Bitmap getBitmap(Intent paramIntent, String paramString, Bitmap.CompressFormat paramCompressFormat)
  {
    if (paramIntent == null) {
      return null;
    }
    paramString = (Bitmap)paramIntent.getExtras().get(paramString);
    if (paramString == null) {
      return null;
    }
    paramIntent = new ByteArrayOutputStream();
    paramString.compress(paramCompressFormat, 100, paramIntent);
    paramCompressFormat = Environment.getExternalStorageDirectory();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(System.currentTimeMillis());
    ((StringBuilder)localObject).append(".png");
    paramCompressFormat = new File(paramCompressFormat, ((StringBuilder)localObject).toString());
    try
    {
      if (!Boolean.valueOf(paramCompressFormat.createNewFile()).booleanValue()) {
        return null;
      }
      localObject = new FileOutputStream(paramCompressFormat);
      ((FileOutputStream)localObject).write(paramIntent.toByteArray());
      ((FileOutputStream)localObject).close();
      paramIntent = paramCompressFormat.getPath();
      try
      {
        paramIntent = new ExifInterface(paramIntent);
      }
      catch (IOException paramIntent)
      {
        reportCrash(paramIntent);
        paramIntent = null;
      }
      int i = paramIntent.getAttributeInt("Orientation", 0);
      if (i != 3)
      {
        if (i != 6)
        {
          if (i != 8) {
            return paramString;
          }
          paramIntent = rotateImage(paramString, 270.0F, Boolean.valueOf(false));
        }
        else
        {
          paramIntent = rotateImage(paramString, 90.0F, Boolean.valueOf(false));
        }
      }
      else {
        paramIntent = rotateImage(paramString, 180.0F, Boolean.valueOf(false));
      }
      return paramIntent;
    }
    catch (IOException paramIntent)
    {
      reportCrash(paramIntent);
    }
    return paramString;
  }
  
  public static Bitmap getBitmapFromFile(String paramString)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("pictureImagePath1 = ");
    localStringBuilder1.append(paramString);
    Log.d("pictureImagePath1", localStringBuilder1.toString());
    Bitmap localBitmap = BitmapFactory.decodeFile(paramString);
    localStringBuilder1 = null;
    if (localBitmap == null) {
      return null;
    }
    try
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("width = ");
      localStringBuilder2.append(localBitmap.getWidth());
      localStringBuilder2.append(" height = ");
      localStringBuilder2.append(localBitmap.getHeight());
      Log.d("photo", localStringBuilder2.toString());
      try
      {
        paramString = new ExifInterface(paramString);
      }
      catch (IOException paramString)
      {
        reportCrash(paramString);
        paramString = localStringBuilder1;
      }
      switch (paramString.getAttributeInt("Orientation", 0))
      {
      case 8: 
        paramString = rotateImage(localBitmap, 270.0F, Boolean.valueOf(false));
        break;
      case 7: 
        paramString = rotateImage(localBitmap, -90.0F, Boolean.valueOf(true));
        break;
      case 6: 
        paramString = rotateImage(localBitmap, 90.0F, Boolean.valueOf(false));
        break;
      case 5: 
        paramString = rotateImage(localBitmap, 90.0F, Boolean.valueOf(true));
        break;
      case 4: 
        paramString = rotateImage(localBitmap, 180.0F, Boolean.valueOf(true));
        break;
      case 3: 
        paramString = rotateImage(localBitmap, 180.0F, Boolean.valueOf(false));
        break;
      case 2: 
        paramString = rotateImage(localBitmap, 0.0F, Boolean.valueOf(true));
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      reportCrash(paramString);
      return localBitmap;
    }
    return localBitmap;
  }
  
  public static Base getInstance()
  {
    return (Base)mInstance;
  }
  
  public static Bitmap getOrientedBitmap(Bitmap paramBitmap, Uri paramUri)
  {
    Object localObject = null;
    if (paramBitmap == null) {
      return null;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("width = ");
      localStringBuilder.append(paramBitmap.getWidth());
      localStringBuilder.append(" height = ");
      localStringBuilder.append(paramBitmap.getHeight());
      Log.d("photo", localStringBuilder.toString());
      try
      {
        paramUri = new ExifInterface(paramUri.getPath());
      }
      catch (IOException paramUri)
      {
        reportCrash(paramUri);
        paramUri = localObject;
      }
      switch (paramUri.getAttributeInt("Orientation", 0))
      {
      case 8: 
        paramUri = rotateImage(paramBitmap, 270.0F, Boolean.valueOf(false));
        paramBitmap = paramUri;
        break;
      case 7: 
        paramUri = rotateImage(paramBitmap, -90.0F, Boolean.valueOf(true));
        paramBitmap = paramUri;
        break;
      case 6: 
        paramUri = rotateImage(paramBitmap, 90.0F, Boolean.valueOf(false));
        paramBitmap = paramUri;
        break;
      case 5: 
        paramUri = rotateImage(paramBitmap, 90.0F, Boolean.valueOf(true));
        paramBitmap = paramUri;
        break;
      case 4: 
        paramUri = rotateImage(paramBitmap, 180.0F, Boolean.valueOf(true));
        paramBitmap = paramUri;
        break;
      case 3: 
        paramUri = rotateImage(paramBitmap, 180.0F, Boolean.valueOf(false));
        paramBitmap = paramUri;
        break;
      case 2: 
        paramUri = rotateImage(paramBitmap, 0.0F, Boolean.valueOf(true));
        paramBitmap = paramUri;
        return paramBitmap;
      }
    }
    catch (Exception paramUri)
    {
      reportCrash(paramUri);
      return paramBitmap;
    }
    return paramBitmap;
  }
  
  public static int getRandomInteger(int paramInt)
  {
    return new Random().nextInt(paramInt) + 1;
  }
  
  public static void reportCrash(Exception paramException)
  {
    paramException.printStackTrace();
    if (getInstance().getPackageName().toString().equals("pgdating.pilotgroup.datingpro")) {
      Crashlytics.logException(paramException);
    }
  }
  
  public static void reportCrash(String paramString)
  {
    Log.e("DATINGAPP", paramString);
    if (getInstance().getPackageName().toString().equals("pgdating.pilotgroup.datingpro")) {
      Crashlytics.log(paramString);
    }
  }
  
  public static void reportCrash(String paramString, Exception paramException)
  {
    Log.e("DATINGAPP", paramString);
    if (getInstance().getPackageName().toString().equals("pgdating.pilotgroup.datingpro")) {
      Crashlytics.logException(paramException);
    }
  }
  
  public static Bitmap rotateImage(Bitmap paramBitmap, float paramFloat, Boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("width = ");
    ((StringBuilder)localObject).append(paramBitmap.getWidth());
    ((StringBuilder)localObject).append(" height = ");
    ((StringBuilder)localObject).append(paramBitmap.getHeight());
    Log.d("Bitmap source", ((StringBuilder)localObject).toString());
    localObject = new Matrix();
    ((Matrix)localObject).postRotate(paramFloat);
    if (paramBoolean.booleanValue()) {
      ((Matrix)localObject).postScale(-1.0F, 1.0F);
    }
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject, true);
  }
  
  public abstract void buildMainMenu(Menu paramMenu, MenuInflater paramMenuInflater);
  
  public boolean checkPermissions(Map<String, Object> paramMap)
  {
    if ((paramMap.containsKey("info")) && ((paramMap.get("info") instanceof LinkedTreeMap)))
    {
      paramMap = (LinkedTreeMap)paramMap.get("info");
      if (paramMap.containsKey("access_denied"))
      {
        paramMap = Html.fromHtml(String.valueOf(paramMap.get("access_denied"))).toString();
        Messages.getInstance().add(paramMap);
        Log.e("DATINGAPI", "Access denied! No permissions for this action!");
        paramMap = new Bundle();
        paramMap.putBoolean("select_group", true);
        getInstance().startModuleActivity("users_payments", "ServicesActivity", paramMap);
        return false;
      }
    }
    return true;
  }
  
  public String getApiUrl()
  {
    return this.apiUrl;
  }
  
  public String getApplicationName()
  {
    return this.applicationName;
  }
  
  public abstract String getApplicationPackageName();
  
  public DatabaseAdapter getDatabase()
  {
    return this.db;
  }
  
  public Tracker getDefaultTracker()
  {
    try
    {
      if (mTracker == null)
      {
        mTracker = GoogleAnalytics.getInstance(this).newTracker(getString(R.string.trackerId));
        mTracker.enableExceptionReporting(true);
        mTracker.enableAdvertisingIdCollection(true);
        mTracker.enableAutoActivityTracking(true);
      }
      Tracker localTracker = mTracker;
      return localTracker;
    }
    finally {}
  }
  
  public abstract String[] getDependencies();
  
  public String getFacebookAppId()
  {
    return this.facebookAppId;
  }
  
  public String getGoogleAppId()
  {
    return this.googleAppId;
  }
  
  public abstract Slider getMediaSlider(int paramInt, String paramString, ViewStub paramViewStub);
  
  public abstract void init(MobileResponseHandler paramMobileResponseHandler);
  
  public void onCreate()
  {
    super.onCreate();
    mInstance = this;
    ArrayList localArrayList = new ArrayList(Arrays.asList(getDependencies()));
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (!localArrayList.contains(localApplicationInfo.packageName)) {
        localArrayList.remove(localApplicationInfo.packageName);
      }
    }
    this.modules = ((String[])localArrayList.toArray(new String[localArrayList.size()]));
    localArrayList.clear();
    this.db = new DatabaseAdapter(this);
    this.baseApiError = new String[] { getString(R.string.error_api_response) };
    mTracker = getDefaultTracker();
    this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    if (getInstance().getPackageName().toString().equals("pgdating.pilotgroup.datingpro")) {
      Fabric.with(this, new Kit[] { new Crashlytics() });
    }
  }
  
  public abstract void onNavigationItemSelected(MenuItem paramMenuItem, Activity paramActivity);
  
  public abstract boolean onOptionsItemSelected(int paramInt);
  
  public abstract void refreshMainMenu(Menu paramMenu);
  
  public abstract void refreshSettingsMenu(Menu paramMenu);
  
  public void setActivity(BaseActivity paramBaseActivity)
  {
    this.activity = paramBaseActivity;
  }
  
  public Base setApiUrl(String paramString)
  {
    this.apiUrl = paramString;
    return this;
  }
  
  public Base setApplicationName(String paramString)
  {
    this.applicationName = paramString;
    return this;
  }
  
  public Base setFacebookAppId(String paramString)
  {
    this.facebookAppId = paramString;
    return this;
  }
  
  public Base setGoogleAppId(String paramString)
  {
    this.googleAppId = paramString;
    return this;
  }
  
  public void startActivity(String paramString, Bundle paramBundle)
  {
    if (this.activity == null) {
      return;
    }
    if (this.activity.getClass().getCanonicalName().equals(paramString)) {
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("class name = ");
    ((StringBuilder)localObject).append(paramString);
    Log.d("class name", ((StringBuilder)localObject).toString());
    try
    {
      localObject = Class.forName(paramString);
      localObject = new Intent(this.activity, (Class)localObject);
      if (paramBundle != null) {
        ((Intent)localObject).putExtras(paramBundle);
      }
      BaseActivity.needFinishOnBackPressed = Boolean.valueOf(false);
      ((Intent)localObject).addFlags(67108864);
      this.activity.startActivity((Intent)localObject);
    }
    catch (ClassNotFoundException paramBundle)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No ");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("activity");
      reportCrash(((StringBuilder)localObject).toString(), paramBundle);
    }
    this.activity.finish();
  }
  
  public void startActivity(String paramString, Bundle paramBundle, int paramInt)
  {
    if (this.activity == null) {
      return;
    }
    if (this.activity.getClass().getCanonicalName().equals(paramString)) {
      return;
    }
    try
    {
      localObject = Class.forName(paramString);
      localObject = new Intent(this.activity, (Class)localObject);
      if (paramBundle != null) {
        ((Intent)localObject).putExtras(paramBundle);
      }
      ((Intent)localObject).addFlags(paramInt);
      this.activity.startActivity((Intent)localObject);
    }
    catch (ClassNotFoundException paramBundle)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No ");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(" activity");
      reportCrash(((StringBuilder)localObject).toString(), paramBundle);
    }
    this.activity.finish();
  }
  
  public void startActivityWithoutFinishing(String paramString1, String paramString2, Bundle paramBundle)
  {
    if (!Arrays.asList(this.modules).contains(paramString1)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getApplicationPackageName());
    localStringBuilder.append(".modules.");
    localStringBuilder.append(paramString1);
    localStringBuilder.append(".");
    localStringBuilder.append(paramString2);
    paramString1 = localStringBuilder.toString();
    if (this.activity == null) {
      return;
    }
    if (this.activity.getClass().getCanonicalName().equals(paramString1)) {
      return;
    }
    paramString2 = new StringBuilder();
    paramString2.append("class name = ");
    paramString2.append(paramString1);
    Log.d("class name", paramString2.toString());
    try
    {
      paramString2 = Class.forName(paramString1);
      paramString2 = new Intent(this.activity, paramString2);
      if (paramBundle != null) {
        paramString2.putExtras(paramBundle);
      }
      BaseActivity.needFinishOnBackPressed = Boolean.valueOf(true);
      paramString2.addFlags(67108864);
      this.activity.startActivity(paramString2);
      return;
    }
    catch (ClassNotFoundException paramString2)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("No ");
      paramBundle.append(paramString1);
      paramBundle.append(" activity");
      reportCrash(paramBundle.toString(), paramString2);
    }
  }
  
  public void startApplicationActivity(String paramString)
  {
    startApplicationActivity(paramString, null);
  }
  
  public void startApplicationActivity(String paramString, Bundle paramBundle)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getApplicationPackageName());
    localStringBuilder.append(".");
    localStringBuilder.append(paramString);
    startActivity(localStringBuilder.toString(), paramBundle);
  }
  
  public void startBaseActivity(String paramString)
  {
    startBaseActivity(paramString, null);
  }
  
  public void startBaseActivity(String paramString, Bundle paramBundle)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Base.class.getPackage().getName());
    localStringBuilder.append(".");
    localStringBuilder.append(paramString);
    startActivity(localStringBuilder.toString(), paramBundle);
  }
  
  public void startModuleActivity(String paramString1, String paramString2)
  {
    startModuleActivity(paramString1, paramString2, null);
  }
  
  public void startModuleActivity(String paramString1, String paramString2, Bundle paramBundle)
  {
    if (!Arrays.asList(this.modules).contains(paramString1)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getApplicationPackageName());
    localStringBuilder.append(".modules.");
    localStringBuilder.append(paramString1);
    localStringBuilder.append(".");
    localStringBuilder.append(paramString2);
    startActivity(localStringBuilder.toString(), paramBundle);
  }
  
  public void startModuleActivity(String paramString1, String paramString2, Bundle paramBundle, int paramInt)
  {
    if (!Arrays.asList(this.modules).contains(paramString1)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getApplicationPackageName());
    localStringBuilder.append(".modules.");
    localStringBuilder.append(paramString1);
    localStringBuilder.append(".");
    localStringBuilder.append(paramString2);
    startActivity(localStringBuilder.toString(), paramBundle, paramInt);
  }
  
  public void trackActivity(String paramString)
  {
    if (this.useGoogleAnalytics) {
      try
      {
        Tracker localTracker = mTracker;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Activity~");
        localStringBuilder.append(paramString);
        localTracker.setScreenName(localStringBuilder.toString());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
      }
      catch (Exception localException)
      {
        reportCrash(localException);
      }
    }
    if (this.useFirebaseAnalytics) {
      try
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("content_type", "activity");
        localBundle.putString("dp_activity_name", paramString);
        this.mFirebaseAnalytics.logEvent("select_content", localBundle);
        return;
      }
      catch (Exception paramString)
      {
        reportCrash(paramString);
      }
    }
  }
  
  public void trackEvent(String paramString1, String paramString2)
  {
    if (this.useGoogleAnalytics) {
      try
      {
        mTracker.send(new HitBuilders.EventBuilder().setCategory(paramString2).setAction(paramString1).build());
      }
      catch (Exception localException)
      {
        reportCrash(localException);
      }
    }
    if (this.useFirebaseAnalytics) {
      try
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("content_type", "event");
        localBundle.putString("dp_event_category", paramString2);
        localBundle.putString("dp_event_action", paramString1);
        this.mFirebaseAnalytics.logEvent("select_content", localBundle);
        return;
      }
      catch (Exception paramString1)
      {
        reportCrash(paramString1);
      }
    }
  }
}
