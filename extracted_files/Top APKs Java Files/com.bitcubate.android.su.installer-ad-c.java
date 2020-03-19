package ad;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.support.v4.app.a;
import android.util.Patterns;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class c
{
  static ArrayList<String> a;
  private static Activity b;
  private static SharedPreferences c;
  private static String[] d = { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };
  private static String[] e = { "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS" };
  
  public c(Activity paramActivity)
  {
    b = paramActivity;
  }
  
  public static String a(Context paramContext)
  {
    Pattern localPattern = Patterns.EMAIL_ADDRESS;
    if (g(paramContext))
    {
      paramContext = AccountManager.get(paramContext).getAccounts();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        if (localPattern.matcher(localObject.name).matches()) {
          return localObject.name;
        }
        i += 1;
      }
    }
    return Build.BRAND + Build.MANUFACTURER + Build.MODEL + Build.DEVICE;
  }
  
  public static ArrayList<String> a(boolean paramBoolean)
  {
    a = new ArrayList();
    Iterator localIterator = b.getPackageManager().getInstalledApplications(8192).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x1) == 0) && ((localApplicationInfo.flags & 0x80) == 0) && (localApplicationInfo.flags != 0)) {
        a.add(localApplicationInfo.packageName);
      }
    }
    return a;
  }
  
  public static void a(Context paramContext, String paramString)
  {
    c = PreferenceManager.getDefaultSharedPreferences(paramContext);
    paramContext = c.edit();
    paramContext.putString("UUID", paramString);
    paramContext.commit();
  }
  
  public static int b(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String c(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if (str != null) {
      return str;
    }
    return e(paramContext);
  }
  
  public static String d(Context paramContext)
  {
    if (a.b(paramContext, "android.permission.ACCESS_WIFI_STATE") == 0)
    {
      String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (str != null) {
        return str;
      }
      return c(paramContext);
    }
    return c(paramContext);
  }
  
  public static String e(Context paramContext)
  {
    c = PreferenceManager.getDefaultSharedPreferences(paramContext);
    return c.getString("UUID", f(paramContext));
  }
  
  public static String f(Context paramContext)
  {
    UUID localUUID = UUID.randomUUID();
    a(paramContext, localUUID.toString());
    return localUUID.toString();
  }
  
  private static boolean g(Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0;
  }
  
  public String a()
  {
    return new JSONArray(a(false)).toString();
  }
}
