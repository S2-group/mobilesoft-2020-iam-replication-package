package me.a.a.k;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public static String a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("me.kiip.sdk", 1);
    String str = localSharedPreferences.getString("kiip_uuid", null);
    paramContext = str;
    if (str == null)
    {
      paramContext = UUID.randomUUID().toString();
      localSharedPreferences.edit().putString("kiip_uuid", paramContext).commit();
    }
    return paramContext;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    return c(paramContext).getString(paramString, "");
  }
  
  public static String a(String paramString)
  {
    int i = 0;
    try
    {
      Object localObject = MessageDigest.getInstance("SHA-1");
      paramString = paramString.getBytes("UTF-8");
      ((MessageDigest)localObject).update(paramString, 0, paramString.length);
      paramString = new StringBuilder();
      localObject = ((MessageDigest)localObject).digest();
      int j = localObject.length;
      while (i < j)
      {
        paramString.append(String.format("%02x", new Object[] { Byte.valueOf(localObject[i]) }));
        i += 1;
      }
      paramString = paramString.toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("DeviceHelper", paramString.getMessage());
    }
    return "";
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    c(paramContext).edit().putString(paramString1, paramString2).commit();
  }
  
  public static boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  public static JSONArray b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledPackages(0).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (a(localPackageInfo)) {
          continue;
        }
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("i", localPackageInfo.packageName);
          localJSONObject.put("v", localPackageInfo.versionName);
          localJSONObject.put("n", localPackageInfo.applicationInfo.loadLabel(paramContext).toString());
          localArrayList.add(localJSONObject);
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            Log.e("DeviceHelper", localJSONException.getLocalizedMessage());
          }
        }
      }
    }
    return new JSONArray(localArrayList);
  }
  
  private static SharedPreferences c(Context paramContext)
  {
    return paramContext.getSharedPreferences("me.kiip.sdk", 0);
  }
}
