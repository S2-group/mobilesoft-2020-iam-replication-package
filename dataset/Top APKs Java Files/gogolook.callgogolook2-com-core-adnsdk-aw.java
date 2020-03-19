package com.core.adnsdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;

final class aw
{
  private static final AtomicInteger a = new AtomicInteger(1);
  
  protected static int a()
  {
    if (Build.VERSION.SDK_INT < 17)
    {
      int k;
      int i;
      do
      {
        k = a.get();
        int j = k + 1;
        i = j;
        if (j > 16777215) {
          i = 1;
        }
      } while (!a.compareAndSet(k, i));
      return k;
    }
    return View.generateViewId();
  }
  
  protected static String a(Context paramContext)
    throws Exception
  {
    return paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.dataDir;
  }
  
  protected static String a(Context paramContext, String paramString)
  {
    boolean bool;
    if (!Environment.getExternalStorageState().equals("mounted"))
    {
      if (Build.VERSION.SDK_INT >= 9)
      {
        bool = Environment.isExternalStorageRemovable();
        if (bool) {
          break label125;
        }
      }
    }
    else
    {
      if (Build.VERSION.SDK_INT < 8) {
        break label66;
      }
      paramContext = paramContext.getExternalCacheDir();
    }
    label40:
    label66:
    label125:
    for (paramContext = paramContext.getAbsolutePath();; paramContext = paramContext.getCacheDir().getAbsolutePath())
    {
      return a(new String[] { paramContext, paramString });
      bool = true;
      break;
      paramContext = "/Android/data/" + paramContext.getPackageName() + "/cache/";
      paramContext = new File(Environment.getExternalStorageDirectory().getPath() + paramContext);
      break label40;
    }
  }
  
  protected static String a(Exception paramException)
  {
    StringWriter localStringWriter = new StringWriter();
    paramException.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
  
  protected static String a(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    int i = paramString.lastIndexOf(File.separator) + 1;
    int j = paramString.length();
    if (j > i) {
      return paramString.substring(i, j);
    }
    return "";
  }
  
  protected static String a(String... paramVarArgs)
  {
    String str = paramVarArgs[0];
    int i = 1;
    while (i < 2)
    {
      str = str + File.separator + paramVarArgs[1];
      i += 1;
    }
    return str;
  }
  
  private static boolean a(Activity paramActivity)
  {
    Window localWindow = paramActivity.getWindow();
    if ((localWindow != null) && ((localWindow.getAttributes().flags & 0x1000000) != 0)) {}
    for (;;)
    {
      return true;
      try
      {
        int i = paramActivity.getPackageManager().getActivityInfo(paramActivity.getComponentName(), 0).flags;
        if ((i & 0x200) != 0) {}
      }
      catch (PackageManager.NameNotFoundException paramActivity)
      {
        for (;;)
        {
          ay.b("Utils", "hasHardwareAcceleration: error = " + a(paramActivity));
        }
      }
    }
    return false;
  }
  
  protected static String b(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes(), 0, paramString.length());
      byte[] arrayOfByte = ((MessageDigest)localObject).digest();
      int j = arrayOfByte.length;
      paramString = "";
      int i = 0;
      while (i < j)
      {
        int k = arrayOfByte[i] & 0xFF;
        localObject = paramString;
        if (k <= 15) {
          localObject = paramString + "0";
        }
        paramString = (String)localObject + Integer.toHexString(k);
        i += 1;
      }
      return paramString.toUpperCase();
    }
    catch (NoSuchAlgorithmException paramString)
    {
      return null;
    }
  }
  
  protected static String b(String[] paramArrayOfString)
  {
    int i = 0;
    byte[] arrayOfByte1 = Base64.decode(paramArrayOfString[0], 0);
    paramArrayOfString = Base64.decode(paramArrayOfString[1], 0);
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length];
    while (i < paramArrayOfString.length)
    {
      arrayOfByte2[i] = ((byte)(arrayOfByte1[i] ^ paramArrayOfString[i]));
      i += 1;
    }
    return new String(arrayOfByte2);
  }
  
  protected static PackageInfo[] b(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    List localList = ((PackageManager)localObject).getInstalledPackages(0);
    paramContext = new ArrayList();
    int j = 0;
    if (j < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(j);
      if (((PackageManager)localObject).getLaunchIntentForPackage(localPackageInfo.packageName) != null) {}
      for (;;)
      {
        try
        {
          i = ((PackageManager)localObject).getApplicationInfo(localPackageInfo.packageName, 0).flags;
          if ((i & 0x1) == 0) {
            continue;
          }
          i = 1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          int i = 0;
          continue;
        }
        if (i == 0) {
          paramContext.add(localPackageInfo);
        }
        j += 1;
        break;
        i = 0;
      }
    }
    if (paramContext.size() <= 0) {
      return null;
    }
    localObject = new PackageInfo[paramContext.size()];
    paramContext.toArray((Object[])localObject);
    Arrays.sort((Object[])localObject, new Comparator() {});
    return localObject;
  }
  
  public static int c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return -1;
    }
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  protected static JSONArray c(Context paramContext)
  {
    PackageInfo[] arrayOfPackageInfo = b(paramContext);
    if (arrayOfPackageInfo == null) {
      return new JSONArray();
    }
    paramContext = paramContext.getPackageManager();
    JSONArray localJSONArray1 = new JSONArray();
    int i = 0;
    while (i < arrayOfPackageInfo.length)
    {
      JSONArray localJSONArray2 = new JSONArray();
      localJSONArray2.put(arrayOfPackageInfo[i].packageName);
      localJSONArray2.put(arrayOfPackageInfo[i].versionName);
      localJSONArray2.put(arrayOfPackageInfo[i].applicationInfo.loadLabel(paramContext).toString());
      localJSONArray1.put(localJSONArray2);
      i += 1;
    }
    return localJSONArray1;
  }
  
  protected static float d(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi / 160.0F;
  }
  
  public static boolean e(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      return a((Activity)paramContext);
    }
    return false;
  }
}
