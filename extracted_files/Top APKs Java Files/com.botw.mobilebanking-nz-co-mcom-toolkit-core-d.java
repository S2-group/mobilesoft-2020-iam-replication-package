package nz.co.mcom.toolkit.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import nz.co.mcom.toolkit.c;
import nz.co.mcom.toolkit.core.module.a;
import nz.co.mcom.toolkit.k;
import nz.co.mcom.toolkit.ui.MCDialogueManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public d() {}
  
  public static int a(int paramInt, Activity paramActivity)
  {
    return (int)paramActivity.getResources().getDimension(paramInt);
  }
  
  public static String a(Context paramContext)
  {
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if (paramContext != null)
    {
      paramContext = paramContext.getConnectionInfo();
      if (paramContext != null) {
        return paramContext.getMacAddress();
      }
    }
    return null;
  }
  
  public static String a(String paramString)
  {
    StringBuilder localStringBuilder1;
    for (;;)
    {
      int m;
      try
      {
        paramString = paramString.split("[.]");
        localStringBuilder1 = new StringBuilder();
        int i = 0;
        int j = 0;
        if ((i >= paramString.length) || (j != 0)) {
          break;
        }
        Object localObject = paramString[i];
        StringBuilder localStringBuilder2 = new StringBuilder();
        m = 0;
        int k = j;
        char c;
        if (m < localObject.length())
        {
          c = localObject.charAt(m);
          if (!Character.isDigit(c)) {
            k = 1;
          }
        }
        else
        {
          if (localStringBuilder2.toString().equals("")) {
            break;
          }
          if (localStringBuilder1.length() != 0) {
            localStringBuilder1.append('.');
          }
          localStringBuilder1.append(localStringBuilder2);
          i += 1;
          j = k;
          continue;
        }
        localStringBuilder2.append(c);
      }
      catch (Exception paramString)
      {
        return "";
      }
      m += 1;
    }
    return localStringBuilder1.toString();
  }
  
  public static String a(String paramString1, String paramString2, JSONArray paramJSONArray)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("module", paramString1);
      localJSONObject.put("method", paramString2);
      localJSONObject.put("params", paramJSONArray);
      localJSONObject.put("callbackMethod", "callback");
      return "javascript:mobiliti.callJsMethod('" + localJSONObject.toString() + "');";
    }
    catch (JSONException paramString1)
    {
      for (;;) {}
    }
  }
  
  public static void a(Activity paramActivity)
  {
    View localView = paramActivity.getWindow().getCurrentFocus();
    if (localView == null) {
      localView = paramActivity.findViewById(16908290);
    }
    for (;;)
    {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
      return;
    }
  }
  
  public static boolean a()
  {
    try
    {
      Camera localCamera = Camera.open();
      if (localCamera != null)
      {
        Object localObject = localCamera.getParameters().getSupportedPictureSizes();
        Camera.Size localSize = (Camera.Size)((List)localObject).get(((List)localObject).size() - 1);
        localObject = (Camera.Size)((List)localObject).get(0);
        localCamera.release();
        int i = localSize.height;
        if (localSize.width * i <= 2000000)
        {
          i = ((Camera.Size)localObject).height;
          int j = ((Camera.Size)localObject).width;
          if (i * j <= 2000000) {}
        }
        else
        {
          return true;
        }
      }
      return false;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean a(Activity paramActivity)
  {
    Iterator localIterator = ((InputMethodManager)paramActivity.getSystemService("input_method")).getInputMethodList().iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      InputMethodInfo localInputMethodInfo = (InputMethodInfo)localIterator.next();
      if ((!localInputMethodInfo.getId().equals(Settings.Secure.getString(paramActivity.getContentResolver(), "default_input_method"))) || ((localInputMethodInfo.getServiceInfo().applicationInfo.flags & 0x1) != 0)) {
        break label115;
      }
      i = 1;
    }
    label115:
    for (;;)
    {
      break;
      if (i != 0) {
        try
        {
          ((MCDialogueManager)a.a(MCDialogueManager.class)).a(paramActivity, paramActivity.getString(k.login_ex_keyboard_title), paramActivity.getString(k.login_ex_keyboard_message));
          return true;
        }
        catch (Exception paramActivity)
        {
          return true;
        }
      }
      return false;
    }
  }
  
  public static boolean a(Context paramContext)
  {
    int i;
    int j;
    if ((Build.VERSION.SDK_INT >= 11) && (Build.VERSION.SDK_INT <= 13))
    {
      i = 1;
      if (b()) {
        break label49;
      }
      j = 1;
      label26:
      if ((!c()) || (!ViewConfiguration.get(paramContext).hasPermanentMenuKey())) {
        break label54;
      }
    }
    label49:
    label54:
    do
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label26;
      if (i != 0) {
        return false;
      }
    } while (j != 0);
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().queryIntentActivities(new Intent(paramString), 65536).size() > 0;
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public static int[] a(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint;
    int j;
    if (Build.VERSION.SDK_INT >= 13)
    {
      localPoint = new Point();
      paramContext.getSize(localPoint);
      j = localPoint.y;
    }
    for (int i = localPoint.x;; i = paramContext.getWidth())
    {
      return new int[] { j, i };
      j = paramContext.getHeight();
    }
  }
  
  public static String b(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static boolean b()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      if ((Build.TAGS != null) && (Build.TAGS.toLowerCase().contains("test-keys"))) {
        return true;
      }
      if (!new File("/system/etc/security/otacerts.zip").exists()) {
        return true;
      }
      if (new File("/system/app/Superuser.apk").exists()) {
        return true;
      }
      Object localObject2 = paramContext.getPackageManager();
      Object localObject1 = new ArrayList(Arrays.asList(paramContext.getResources().getStringArray(c.package_blacklist)));
      localObject2 = ((PackageManager)localObject2).getInstalledPackages(1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
        if (((ArrayList)localObject1).contains(localPackageInfo.packageName.toLowerCase())) {
          return true;
        }
        if (localPackageInfo.applicationInfo.uid == 0) {
          break label332;
        }
        if (Arrays.asList(new int[][] { localPackageInfo.gids }).contains(Integer.valueOf(0))) {
          break label332;
        }
      }
      localObject1 = new ArrayList(Arrays.asList(paramContext.getResources().getStringArray(c.binary_blacklist))).iterator();
      while (((Iterator)localObject1).hasNext()) {
        if (new File((String)((Iterator)localObject1).next()).exists()) {
          return true;
        }
      }
      paramContext = new ArrayList(Arrays.asList(paramContext.getResources().getStringArray(c.directory_list))).iterator();
      while (paramContext.hasNext())
      {
        localObject1 = (String)paramContext.next();
        localObject2 = new File((String)localObject1);
        if ((((File)localObject2).exists()) && (((File)localObject2).isDirectory()))
        {
          if (((File)localObject2).canWrite()) {
            return true;
          }
          if (((String)localObject1).contains("/data"))
          {
            boolean bool = ((File)localObject2).canRead();
            if (bool) {
              return true;
            }
          }
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      return false;
    }
    label332:
    return true;
  }
  
  public static boolean c()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
}
