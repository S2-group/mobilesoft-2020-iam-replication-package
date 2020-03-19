package com.loopme.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;
import com.loopme.AES;
import com.loopme.LoopMeBanner;
import com.loopme.LoopMeBannerView;
import com.loopme.constants.StretchOption;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

public class Utils
{
  private static final String LOG_TAG = Utils.class.getSimpleName();
  private static AudioManager sAudioManager;
  private static LocationManager sLocationManager;
  private static PackageManager sPackageManager;
  private static Resources sResources;
  private static WindowManager sWindowManager;
  
  public Utils() {}
  
  public static void animateAppear(View paramView)
  {
    paramView.animate().setDuration(500L).alpha(1.0F);
  }
  
  public static FrameLayout.LayoutParams calculateNewLayoutParams(FrameLayout.LayoutParams paramLayoutParams, int paramInt1, int paramInt2, int paramInt3, int paramInt4, StretchOption paramStretchOption)
  {
    paramLayoutParams.gravity = 17;
    float f = 0.0F;
    if (paramInt1 > paramInt2)
    {
      paramLayoutParams.width = paramInt3;
      paramLayoutParams.height = ((int)(paramInt2 / paramInt1 * paramInt3));
      paramInt1 = paramLayoutParams.height;
      if (paramLayoutParams.height != 0) {
        f = (paramInt4 - paramInt1) * 100 / paramLayoutParams.height;
      }
    }
    switch (1.$SwitchMap$com$loopme$constants$StretchOption[paramStretchOption.ordinal()])
    {
    default: 
    case 1: 
      do
      {
        return paramLayoutParams;
        paramLayoutParams.height = paramInt4;
        paramLayoutParams.width = ((int)(paramInt1 / paramInt2 * paramInt4));
        paramInt1 = paramLayoutParams.width;
        if (paramLayoutParams.width == 0) {
          break;
        }
        f = (paramInt3 - paramInt1) * 100 / paramLayoutParams.width;
        break;
      } while (f >= 11.0F);
      paramLayoutParams.width = paramInt3;
      paramLayoutParams.height = paramInt4;
      return paramLayoutParams;
    }
    paramLayoutParams.width = paramInt3;
    paramLayoutParams.height = paramInt4;
    return paramLayoutParams;
  }
  
  public static void clearCache(Context paramContext)
  {
    VideoUtils.clearCache(paramContext);
  }
  
  public static int convertDpToPixel(float paramFloat)
  {
    if (sResources != null) {
      return (int)TypedValue.applyDimension(1, paramFloat, sResources.getDisplayMetrics());
    }
    return 0;
  }
  
  public static int convertPixelToDp(int paramInt)
  {
    if (sResources != null) {
      return (int)(paramInt / sResources.getDisplayMetrics().density);
    }
    return 0;
  }
  
  public static BitmapDrawable decodeImage(String paramString)
  {
    return new BitmapDrawable(null, new ByteArrayInputStream(Base64.decode(paramString, 0)));
  }
  
  private static String deleteLastCharacter(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString.substring(0, paramString.length() - 1);
    }
    return "";
  }
  
  public static DisplayMetrics getDisplayMetrics()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    if (sWindowManager == null) {
      return localDisplayMetrics;
    }
    sWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static String getEncryptedString(String paramString)
  {
    AES.setDefaultKey();
    AES.encrypt(paramString);
    return deleteLastCharacter(AES.getEncryptedString());
  }
  
  public static Location getLastKnownLocation()
  {
    if (sLocationManager == null) {}
    Object localObject1;
    do
    {
      return null;
      localObject1 = null;
      try
      {
        localObject2 = sLocationManager.getLastKnownLocation("gps");
        localObject1 = localObject2;
      }
      catch (SecurityException localSecurityException1)
      {
        for (;;)
        {
          Object localObject2;
          Logging.out(LOG_TAG, "Failed to retrieve GPS location: access appears to be disabled.");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException1)
      {
        for (;;)
        {
          Logging.out(LOG_TAG, "Failed to retrieve GPS location: device has no GPS provider.");
        }
        return localIllegalArgumentException1;
      }
      localObject2 = null;
      try
      {
        Location localLocation = sLocationManager.getLastKnownLocation("network");
        localObject2 = localLocation;
      }
      catch (SecurityException localSecurityException2)
      {
        for (;;)
        {
          Logging.out(LOG_TAG, "Failed to retrieve network location: access appears to be disabled.");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        for (;;)
        {
          Logging.out(LOG_TAG, "Failed to retrieve network location: device has no network provider.");
        }
      }
    } while ((localObject1 == null) && (localObject2 == null));
    if ((localObject1 != null) && (localObject2 != null)) {
      if (localObject1.getTime() > ((Location)localObject2).getTime()) {
        return localObject1;
      }
    }
    if (localObject1 != null) {
      return localObject1;
    }
    return localIllegalArgumentException1;
  }
  
  public static ViewGroup.LayoutParams getParamsSafety(LoopMeBanner paramLoopMeBanner)
  {
    try
    {
      paramLoopMeBanner = paramLoopMeBanner.getBannerView().getLayoutParams();
      return paramLoopMeBanner;
    }
    catch (NullPointerException paramLoopMeBanner)
    {
      Logging.out(LOG_TAG, "Warning! Check integration of LoopMeBanner");
    }
    return null;
  }
  
  public static int getScreenHeight()
  {
    if (sWindowManager == null) {
      return 0;
    }
    Display localDisplay = sWindowManager.getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    return localPoint.y;
  }
  
  public static int getScreenOrientation()
  {
    if (sWindowManager == null) {
      return 0;
    }
    int i = sWindowManager.getDefaultDisplay().getRotation();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    sWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    int j = localDisplayMetrics.widthPixels;
    int k = localDisplayMetrics.heightPixels;
    if (((i != 0) && (i != 2)) || ((k > j) || (((i == 1) || (i == 3)) && (j > k))))
    {
      switch (i)
      {
      default: 
        return 1;
      case 0: 
        return 1;
      case 1: 
        return 0;
      case 2: 
        return 9;
      }
      return 8;
    }
    switch (i)
    {
    default: 
      return 0;
    case 0: 
      return 0;
    case 1: 
      return 1;
    case 2: 
      return 8;
    }
    return 9;
  }
  
  public static int getScreenWidth()
  {
    if (sWindowManager == null) {
      return 0;
    }
    Display localDisplay = sWindowManager.getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    return localPoint.x;
  }
  
  public static String getStringFromStream(InputStream paramInputStream)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    byte[] arrayOfByte = new byte['က'];
    try
    {
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localStringBuilder.append(new String(arrayOfByte, 0, i));
      }
      return localStringBuilder.toString();
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
    for (;;)
    {
      paramInputStream.close();
    }
  }
  
  public static float getSystemVolume()
  {
    if (sAudioManager != null) {
      return Math.round(sAudioManager.getStreamVolume(2) * 100 / sAudioManager.getStreamMaxVolume(2)) / 100.0F;
    }
    return 1.0F;
  }
  
  public static String getUrlEncodedString(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return "unknown";
  }
  
  public static String getViewVisibility(View paramView)
  {
    switch (paramView.getVisibility())
    {
    default: 
      return null;
    case 0: 
      return "VISIBLE";
    case 4: 
      return "INVISIBLE";
    }
    return "GONE";
  }
  
  public static void init(Context paramContext)
  {
    if (paramContext != null)
    {
      sWindowManager = (WindowManager)paramContext.getSystemService("window");
      sResources = paramContext.getResources();
      sLocationManager = (LocationManager)paramContext.getSystemService("location");
      sPackageManager = paramContext.getPackageManager();
      sAudioManager = (AudioManager)paramContext.getSystemService("audio");
    }
  }
  
  public static boolean isEmulator()
  {
    return (Build.MODEL.contains("google_sdk")) || (Build.MODEL.contains("Emulator")) || (Build.MODEL.contains("Android SDK")) || (Build.MANUFACTURER.contains("Genymotion"));
  }
  
  public static boolean isOnline(Context paramContext)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isConnected()))
      {
        boolean bool = paramContext.isAvailable();
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isPackageInstalled(List<String> paramList)
  {
    if (sPackageManager == null) {
      return false;
    }
    Iterator localIterator = sPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      int i = 0;
      while (i < paramList.size())
      {
        if (((String)paramList.get(i)).equalsIgnoreCase(localPackageInfo.packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
}
