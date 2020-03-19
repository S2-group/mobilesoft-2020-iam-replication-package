package com.kayenworks.mcpeaddons;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineConfig.Builder;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.Iterator;
import java.util.List;

public class Application
  extends MultiDexApplication
{
  private static final String TWITTER_API_KEY = "***REMOVED***";
  private static final String TWITTER_API_SECRET = "***REMOVED***";
  private static Context context;
  
  public Application() {}
  
  public static void RequestPermission(Activity paramActivity, String paramString, int paramInt)
  {
    if (ContextCompat.checkSelfPermission(paramActivity, paramString) != 0) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { paramString }, paramInt);
    }
  }
  
  public static boolean appInstalledOrNot(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean checkPermission(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static Context getAppContext()
  {
    return context;
  }
  
  public static int getSdkVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static boolean isApplicationInForeground()
  {
    return MyLifecycleHandler.isApplicationInForeground();
  }
  
  public static boolean isApplicationVisible()
  {
    return MyLifecycleHandler.isApplicationVisible();
  }
  
  public static boolean isFroyo()
  {
    return getSdkVersion() >= 8;
  }
  
  public static boolean isHoneycomb()
  {
    return getSdkVersion() >= 11;
  }
  
  public static boolean isHoneycombTablet(Context paramContext)
  {
    return (isHoneycomb()) && (isTablet(paramContext));
  }
  
  public static boolean isLandscape(Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getConfiguration().orientation;
      return i == 2;
    }
    catch (NullPointerException paramContext) {}
    return false;
  }
  
  public static boolean isPackageExisted(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    MultiDex.install(this);
  }
  
  public void onCreate()
  {
    super.onCreate();
    Object localObject = (ActivityManager)getSystemService("activity");
    localObject = ImagePipelineConfig.newBuilder(getApplicationContext()).setDownsampleEnabled(true).setBitmapMemoryCacheParamsSupplier(new FrescoCacheParams((ActivityManager)localObject)).build();
    Fresco.initialize(getApplicationContext(), (ImagePipelineConfig)localObject);
    context = getApplicationContext();
    registerActivityLifecycleCallbacks(new MyLifecycleHandler());
    Fabric.with(this, new Kit[] { new Twitter(new TwitterAuthConfig("***REMOVED***", "***REMOVED***")), new Crashlytics() });
    if (getPackageName().contains("amazon"))
    {
      Constants.AMAZON_FEATURE = true;
      if (getPackageName().contains("underground")) {
        Constants.USE_AD = false;
      }
    }
    else
    {
      Constants.AMAZON_FEATURE = false;
    }
    String str = Logger.getTag();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MARKET :: ");
    if (Constants.AMAZON_FEATURE) {
      localObject = " AMAZON ";
    } else {
      localObject = " GOOGLE ";
    }
    localStringBuilder.append((String)localObject);
    if (Constants.USE_AD) {
      localObject = "FREE";
    } else {
      localObject = "PRO";
    }
    localStringBuilder.append((String)localObject);
    Logger.W(str, localStringBuilder.toString());
  }
  
  public class FrescoCacheParams
    implements Supplier<MemoryCacheParams>
  {
    private ActivityManager activityManager;
    
    public FrescoCacheParams(ActivityManager paramActivityManager)
    {
      this.activityManager = paramActivityManager;
    }
    
    private int getMaxCacheSize()
    {
      int i = Math.min(this.activityManager.getMemoryClass() * 1048576, Integer.MAX_VALUE);
      if (i < 33554432) {
        return 4194304;
      }
      if (i < 67108864) {
        return 6291456;
      }
      if (Build.VERSION.SDK_INT <= 9) {
        return 8388608;
      }
      return i / 6;
    }
    
    public MemoryCacheParams get()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        int i = getMaxCacheSize();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("fresco cache size = ");
        localStringBuilder.append(i);
        Log.d("####", localStringBuilder.toString());
        return new MemoryCacheParams(i, 1, 1, 1, 1);
      }
      return new MemoryCacheParams(getMaxCacheSize(), 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
  }
}
