package com.flipboard.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntentUtil
{
  public static final String API_PARTNER_SAMSUNG = "samsung";
  public static final int API_VERSION = 2;
  public static final String FLIPBOARD_INTENT_API_PARTNER_KEY = "apiPartner";
  public static final String FLIPBOARD_INTENT_API_VERSION_KEY = "apiVersion";
  public static final int NATIVE_APP_TYPE_USE_FLIPBOARD = 0;
  public static final int NATIVE_APP_TYPE_USE_NATIVE_ALWAYS = 2;
  public static final int NATIVE_APP_TYPE_USE_NATIVE_FIRST = 1;
  private static String mFlipboardPackage;
  
  public IntentUtil() {}
  
  public static boolean activityExists(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().resolveActivity(paramIntent, 65536) != null;
  }
  
  public static Intent getDetailIntent(Context paramContext, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    return getDetailIntent(paramContext, paramJSONObject1, paramJSONObject2, null);
  }
  
  public static Intent getDetailIntent(Context paramContext, JSONObject paramJSONObject1, JSONObject paramJSONObject2, String paramString)
  {
    int k = 1;
    if (paramJSONObject1 == null) {
      return null;
    }
    try
    {
      localObject2 = Log.main;
      str = paramJSONObject1.toString();
      if (paramJSONObject2 != null) {
        break label366;
      }
      localObject1 = "null";
      ((Log)localObject2).debug("FDL: prepare to open itme [item: %s]  [service: %s]", new Object[] { str, localObject1 });
      if (!paramJSONObject1.has("flipboardURL")) {
        break label523;
      }
      str = paramJSONObject1.getString("flipboardURL");
    }
    catch (JSONException paramJSONObject1)
    {
      for (;;)
      {
        Object localObject2;
        String str;
        Object localObject1;
        label83:
        label100:
        int j;
        label120:
        label366:
        label375:
        int i;
        label407:
        label473:
        paramContext = null;
        continue;
        continue;
        continue;
        for (;;)
        {
          if (j != 0) {
            break label375;
          }
          paramJSONObject1 = null;
          i = 0;
          if (i == 0) {
            break label407;
          }
          if ((paramJSONObject1 == null) || (localObject2 == null)) {
            break label556;
          }
          paramContext = (Context)localObject2;
          break label120;
          str = null;
          break;
          localObject1 = null;
          break label83;
          localObject2 = null;
          break label100;
          j = 0;
        }
        for (;;)
        {
          break;
          i = 0;
        }
        paramContext = (Context)localObject1;
      }
    }
    if (paramJSONObject1.has("linkURL"))
    {
      localObject1 = paramJSONObject1.getString("linkURL");
      if (!paramJSONObject1.has("nativeAppLink")) {
        break label535;
      }
      localObject2 = paramJSONObject1.getString("nativeAppLink");
      if (!paramJSONObject1.has("nativeAppType")) {
        break label541;
      }
      j = paramJSONObject1.getInt("nativeAppType");
      break label493;
      paramContext = new Intent("android.intent.action.VIEW", Uri.parse(paramContext));
      for (;;)
      {
        try
        {
          Log.main.debug("FDL: create detail intent [nativeAppType: %s]", new Object[] { Integer.valueOf(j) });
          Log.main.debug("FDL: create detail intent flipboardURL is %s", new Object[] { str });
          Log.main.debug("FDL: create detail intent linkURL is %s", new Object[] { localObject1 });
          paramContext.setFlags(268435456);
          paramContext.addFlags(524288);
          paramContext.putExtra("apiPartner", "samsung");
          paramContext.putExtra("apiVersion", 2);
          paramContext.putExtra("fdlVersion", "0.60");
          if ((paramString != null) && (paramString.length() > 0)) {
            paramContext.putExtra("section_title", paramString);
          }
          Log.main.debug("FDL: successfully created intent to open item", new Object[0]);
          paramJSONObject1 = paramContext.getExtras();
          if (paramJSONObject1 == null) {
            break label473;
          }
          paramJSONObject2 = paramJSONObject1.keySet().iterator();
          if (!paramJSONObject2.hasNext()) {
            break label473;
          }
          paramString = (String)paramJSONObject2.next();
          localObject1 = paramJSONObject1.get(paramString);
          Log.main.debug("[%s: %s]", new Object[] { paramString, localObject1.toString() });
          continue;
          Log.main.error("FDL: Exception when creating intent to open item %s" + paramJSONObject1.toString(), new Object[0]);
        }
        catch (JSONException paramJSONObject1) {}
        return paramContext;
        localObject1 = paramJSONObject2.toString();
        break;
        paramJSONObject1 = getInstalledAppPackage(paramJSONObject2, paramContext);
        i = k;
        if (j == 2) {
          break label547;
        }
        if ((localObject2 == null) || (paramJSONObject1 == null)) {
          break label550;
        }
        i = k;
        break label547;
        if ((paramJSONObject1 == null) || (paramJSONObject1.startsWith("flipboard"))) {
          paramJSONObject1 = new Intent("android.intent.action.VIEW", Uri.parse(str));
        }
        try
        {
          paramJSONObject1.setPackage(getFlipboardPackage(paramContext));
          paramContext = paramJSONObject1;
        }
        catch (JSONException paramJSONObject2)
        {
          paramContext = paramJSONObject1;
          paramJSONObject1 = paramJSONObject2;
        }
        paramContext = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject1));
        try
        {
          paramContext.setPackage(paramJSONObject1);
        }
        catch (JSONException paramJSONObject1) {}
      }
      return paramContext;
    }
  }
  
  private static String getFlipboardPackage(Context paramContext)
  {
    int j;
    int i;
    ApplicationInfo localApplicationInfo;
    if (mFlipboardPackage == null)
    {
      paramContext = paramContext.getApplicationContext().getPackageManager().getInstalledApplications(128).iterator();
      j = 0;
      i = 0;
      if (paramContext.hasNext())
      {
        localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ("flipboard.cn".equals(localApplicationInfo.packageName))
        {
          i = j;
          j = 1;
        }
      }
    }
    for (;;)
    {
      int k = j;
      j = i;
      i = k;
      break;
      if ("flipboard.app".equals(localApplicationInfo.packageName))
      {
        k = 1;
        j = i;
        i = k;
        continue;
        if (i != 0) {
          mFlipboardPackage = "flipboard.cn";
        }
        for (;;)
        {
          return mFlipboardPackage;
          if (j != 0) {
            mFlipboardPackage = "flipboard.app";
          } else {
            mFlipboardPackage = "flipboard.app";
          }
        }
      }
      else
      {
        k = i;
        i = j;
        j = k;
      }
    }
  }
  
  private static String getInstalledAppPackage(JSONObject paramJSONObject, Context paramContext)
  {
    if (paramJSONObject != null) {
      try
      {
        if (paramJSONObject.has("nativeAppPackages"))
        {
          paramJSONObject = paramJSONObject.getJSONArray("nativeAppPackages");
          if (paramJSONObject.length() > 0)
          {
            int i = 0;
            while (i < paramJSONObject.length())
            {
              String str = paramJSONObject.getString(i);
              boolean bool = isAppInstalled(str, paramContext);
              if (bool) {
                return str;
              }
              i += 1;
            }
          }
        }
        return null;
      }
      catch (JSONException paramJSONObject)
      {
        Log.main.error(paramJSONObject);
      }
    }
  }
  
  private static boolean isAppInstalled(String paramString, Context paramContext)
  {
    if (paramString != null)
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext()) {
        if (((PackageInfo)paramContext.next()).packageName.equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
}
