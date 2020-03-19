package com.chineseskill.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PhoneUtil
{
  private static long lastClickTime;
  
  public PhoneUtil() {}
  
  public static void alert(Context paramContext, int paramInt)
  {
    alert(paramContext, paramContext.getResources().getString(paramInt));
  }
  
  public static void alert(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    alert(paramContext, paramContext.getResources().getString(paramInt1), paramContext.getResources().getString(paramInt2), paramOnClickListener);
  }
  
  public static void alert(Context paramContext, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    alert(paramContext, paramContext.getResources().getString(paramInt), paramOnClickListener);
  }
  
  public static void alert(Context paramContext, String paramString)
  {
    alert(paramContext, paramString, null);
  }
  
  public static void alert(Context paramContext, String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    alert(paramContext, paramString, "Error", paramOnClickListener);
  }
  
  public static void alert(Context paramContext, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener)
  {
    new AlertDialog.Builder(paramContext).setTitle(paramString2).setMessage(paramString1).setPositiveButton(2131165254, paramOnClickListener).show();
  }
  
  public static void alertThenFinish(Activity paramActivity, int paramInt)
  {
    alert(paramActivity, paramActivity.getResources().getString(paramInt), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$context.finish();
      }
    });
  }
  
  public static boolean checkNetworkStatus(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean checkWifiConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    int i;
    if ((paramContext != null) && (paramContext.isConnectedOrConnecting())) {
      i = 1;
    }
    while (i != 0) {
      if (paramContext.getType() == 1)
      {
        return true;
        i = 0;
      }
      else
      {
        return false;
      }
    }
    return false;
  }
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static Pair<String, Integer> getAppVersion(Context paramContext)
  {
    for (;;)
    {
      try
      {
        PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
        String str = localPackageInfo.versionName;
        if (str != null)
        {
          paramContext = str;
          if (!str.equals(""))
          {
            paramContext = new Pair(paramContext, Integer.valueOf(localPackageInfo.versionCode));
            return paramContext;
          }
        }
      }
      catch (Exception paramContext)
      {
        return new Pair("", Integer.valueOf(-1));
      }
      paramContext = "";
    }
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return -1;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    Object localObject1 = "";
    Object localObject2;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      if (paramContext != null)
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (!paramContext.equals("")) {}
      }
      else
      {
        return "";
      }
    }
    catch (Exception paramContext)
    {
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  public static int getNumCores()
  {
    return Runtime.getRuntime().availableProcessors();
  }
  
  public static String getOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static int getScreenHeight(Activity paramActivity)
  {
    return px2dip(paramActivity, paramActivity.getWindowManager().getDefaultDisplay().getHeight());
  }
  
  @SuppressLint({"NewApi"})
  public static int getScreenSwDp(Activity paramActivity)
  {
    Object localObject;
    int j;
    int i;
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject = new Point();
      paramActivity.getWindowManager().getDefaultDisplay().getRealSize((Point)localObject);
      j = ((Point)localObject).x;
      i = ((Point)localObject).y;
      if (i >= j) {
        break label85;
      }
    }
    for (;;)
    {
      return px2dip(paramActivity, i);
      localObject = new DisplayMetrics();
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      i = ((DisplayMetrics)localObject).heightPixels;
      j = ((DisplayMetrics)localObject).widthPixels;
      break;
      label85:
      i = j;
    }
  }
  
  public static String getTimeNow()
  {
    return new SimpleDateFormat("yyyyMMddhhmmss", Locale.US).format(new Date());
  }
  
  public static void hideSoftInput(Activity paramActivity)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramActivity.getSystemService("input_method");
    paramActivity = paramActivity.getCurrentFocus();
    if (paramActivity != null) {
      localInputMethodManager.hideSoftInputFromWindow(paramActivity.getWindowToken(), 2);
    }
  }
  
  public static boolean isConnectToInternet(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.isConnected();
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isDebuggable(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0).flags;
      return (i & 0x2) != 0;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isDestroy(Context paramContext)
  {
    try
    {
      boolean bool = ((Boolean)paramContext.getClass().getMethod("isDestroy", new Class[0]).invoke(paramContext, new Object[0])).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (IllegalAccessException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (InvocationTargetException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static boolean isFastDoubleClick()
  {
    long l = System.currentTimeMillis();
    if (l - lastClickTime < 300L) {
      return true;
    }
    lastClickTime = l;
    return false;
  }
  
  public static boolean isSoftInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int px2sp(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().scaledDensity + 0.5F);
  }
  
  public static String readDeviceId(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }
  
  public static String readUniqueID(Context paramContext)
  {
    String str2 = readDeviceId(paramContext);
    String str1 = str2;
    if (str2 == null)
    {
      Log.i("PhoneUtil", "read wifi mac address");
      str1 = readWifiMacAddress(paramContext);
    }
    paramContext = str1;
    if (str1 == null)
    {
      Log.i("PhoneUtil", "generate a random id");
      paramContext = String.valueOf(System.currentTimeMillis()) + "-" + String.format("%04d", new Object[] { Integer.valueOf((int)(Math.random() * 10000.0D)) });
    }
    return paramContext;
  }
  
  public static String readWifiMacAddress(Context paramContext)
  {
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if (paramContext == null) {
      return null;
    }
    return paramContext.getConnectionInfo().getMacAddress();
  }
  
  public static void setTitle(Activity paramActivity, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    ImageView localImageView1 = (ImageView)paramActivity.findViewById(2131362276);
    ImageView localImageView2 = (ImageView)paramActivity.findViewById(2131362278);
    ((TextView)paramActivity.findViewById(2131362277)).setText(paramString);
    if ((paramBoolean1) && (paramBoolean2))
    {
      if (paramBoolean3)
      {
        localImageView1.setVisibility(8);
        localImageView2.setVisibility(0);
        localImageView2.setImageDrawable(paramActivity.getResources().getDrawable(2130837700));
        return;
      }
      localImageView1.setVisibility(0);
      localImageView2.setVisibility(8);
      localImageView1.setImageDrawable(paramActivity.getResources().getDrawable(2130837934));
      return;
    }
    localImageView1.setImageDrawable(paramActivity.getResources().getDrawable(2130837676));
  }
  
  public static void showHashKey(Context paramContext)
  {
    int i = 0;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.chineseskill", 64).signatures;
      int j = paramContext.length;
      while (i < j)
      {
        Object localObject = paramContext[i];
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
        localMessageDigest.update(localObject.toByteArray());
        Log.i("KeyHash:", Base64.encodeToString(localMessageDigest.digest(), 0));
        i += 1;
      }
      return;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
  }
  
  public static void showSoftInput(Activity paramActivity, EditText paramEditText)
  {
    paramEditText.setFocusable(true);
    paramEditText.setFocusableInTouchMode(true);
    paramEditText.requestFocus();
    ((InputMethodManager)paramActivity.getSystemService("input_method")).showSoftInput(paramEditText, 2);
  }
  
  public static int sp2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().scaledDensity + 0.5F);
  }
  
  public static void switchInputMethod(Context paramContext)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramContext.getSystemService("input_method");
    if (localInputMethodManager != null)
    {
      localInputMethodManager.showInputMethodPicker();
      return;
    }
    toast(paramContext, "无法切换输入法");
  }
  
  public static String throwableToString(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
  
  public static void toast(Context paramContext, int paramInt)
  {
    toast(paramContext, paramContext.getResources().getString(paramInt));
  }
  
  public static void toast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }
  
  public static void toastInCenter(Context paramContext, int paramInt)
  {
    paramContext = Toast.makeText(paramContext, paramContext.getResources().getString(paramInt), 0);
    paramContext.setGravity(17, 0, 0);
    paramContext.show();
  }
  
  public static void toastShort(Context paramContext, int paramInt)
  {
    Toast.makeText(paramContext, paramContext.getResources().getString(paramInt), 0).show();
  }
}
