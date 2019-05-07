package com.mopub.ad;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.view.View;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AdFromGlispa
  extends Ad
{
  int a = 4;
  List b = new ArrayList();
  private String c = "76466412-8cad-4818-9eb7-2f6b2021da3c";
  
  public AdFromGlispa() {}
  
  abstract void b();
  
  public View getAdView(Activity paramActivity, boolean paramBoolean)
  {
    paramActivity = ((h)this.b.get(0)).getAdView(paramActivity, paramBoolean);
    this.b.remove(0);
    return paramActivity;
  }
  
  public String getAndroidId(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      System.out.println(paramContext.getMessage());
    }
    return "";
  }
  
  public List getInstalledApps(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      System.out.println(paramContext.getMessage());
    }
  }
  
  public String getParameterNew(Context paramContext)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      Object localObject = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("ver", ((PackageInfo)localObject).versionName);
      localJSONObject1.put("app", localJSONObject2);
      localObject = new JSONObject();
      ((JSONObject)localObject).put("os", "Android");
      ((JSONObject)localObject).put("osv", Build.VERSION.RELEASE);
      ((JSONObject)localObject).put("ua", System.getProperty("http.agent"));
      ((JSONObject)localObject).put("id", AdTools.getGoogleAdIdV2(paramContext));
      ((JSONObject)localObject).put("androidid", getAndroidId(paramContext));
      ((JSONObject)localObject).put("devicetype", 1);
      ((JSONObject)localObject).put("language", Locale.getDefault().getDisplayLanguage());
      ((JSONObject)localObject).put("make", Build.MANUFACTURER);
      ((JSONObject)localObject).put("model", Build.MODEL);
      localJSONObject1.put("device", localObject);
      paramContext = new JSONObject();
      paramContext.put("id", this.c);
      localObject = new JSONArray();
      ((JSONArray)localObject).put("title");
      ((JSONArray)localObject).put("short_description");
      ((JSONArray)localObject).put("icon_72");
      ((JSONArray)localObject).put("images");
      paramContext.put("fields", localObject);
      paramContext.put("keywords", "Shopping, Action, Tools, Utilities, Lifestyle");
      paramContext.put("count", this.a);
      localJSONObject1.put("ad", paramContext);
      return localJSONObject1.toString();
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        System.out.println(paramContext.getMessage());
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public void loadAd(Context paramContext)
  {
    if (this.state == 1) {
      return;
    }
    this.state = 1;
    Object localObject = new DefaultHttpClient();
    HttpPost localHttpPost = new HttpPost("http://rt.api.glispa.com/native/v1/ad");
    try
    {
      StringEntity localStringEntity = new StringEntity(getParameterNew(paramContext), "UTF-8");
      localHttpPost.setHeader("Content-Type", "application/json");
      localHttpPost.setEntity(localStringEntity);
      localObject = ((DefaultHttpClient)localObject).execute(localHttpPost);
      if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 200)
      {
        localObject = EntityUtils.toString(((HttpResponse)localObject).getEntity());
        int i = this.b.size();
        parseGlispaAdInfoFromJson(paramContext, (String)localObject);
        if (this.b.size() > i)
        {
          b();
          this.state = 0;
          return;
        }
      }
    }
    catch (UnsupportedEncodingException paramContext)
    {
      System.out.println("UnsupportedEncodingException:  " + paramContext.getMessage());
      paramContext.printStackTrace();
      this.state = 0;
      a();
      return;
    }
    catch (ClientProtocolException paramContext)
    {
      for (;;)
      {
        System.out.println("ClientProtocolException:  " + paramContext.getMessage());
        paramContext.printStackTrace();
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        System.out.println("IOException:  " + paramContext.getMessage());
        paramContext.printStackTrace();
      }
    }
  }
  
  public void parseGlispaAdInfoFromJson(Context paramContext, String paramString)
  {
    paramString = paramString.trim();
    try
    {
      paramString = new JSONObject(paramString).getJSONArray("ads");
      int i = 0;
      for (;;)
      {
        int j = paramString.length();
        if (i < j) {
          try
          {
            JSONObject localJSONObject = paramString.getJSONObject(i);
            if (!AdTools.isPackageInstalled(paramContext, localJSONObject.getString("appid"))) {
              this.b.add(new h(this, localJSONObject));
            }
            i += 1;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
            }
          }
        }
      }
      return;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}
