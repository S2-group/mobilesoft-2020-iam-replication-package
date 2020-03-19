package com.gearsandcogs.air.extensions;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import com.adobe.fre.FREContext;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SystemCalls
{
  public static final String TAG = "RunApp";
  
  public SystemCalls() {}
  
  public static JSONArray getApplications(FREContext paramFREContext, String paramString)
  {
    PackageManager localPackageManager = paramFREContext.getActivity().getPackageManager();
    paramFREContext = localPackageManager.getInstalledApplications(128);
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = paramFREContext.iterator();
    if (!localIterator.hasNext()) {
      return localJSONArray;
    }
    ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
    Object localObject = Boolean.valueOf(false);
    if ((localApplicationInfo.flags & 0x1) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      Boolean localBoolean = Boolean.valueOf(bool);
      paramFREContext = (FREContext)localObject;
      if (localBoolean.booleanValue())
      {
        paramFREContext = (FREContext)localObject;
        if (paramString.equals("system")) {
          paramFREContext = Boolean.valueOf(true);
        }
      }
      localObject = paramFREContext;
      if (!localBoolean.booleanValue())
      {
        localObject = paramFREContext;
        if (paramString.equals("user")) {
          localObject = Boolean.valueOf(true);
        }
      }
      if (!((Boolean)localObject).booleanValue()) {
        break;
      }
      Log.d("OUTPUT", "Package name : " + localApplicationInfo.packageName);
      Log.d("OUTPUT", "Name: " + localApplicationInfo.loadLabel(localPackageManager));
      try
      {
        paramFREContext = new JSONObject();
        paramFREContext.put("label", localApplicationInfo.loadLabel(localPackageManager).toString());
        paramFREContext.put("packageName", localApplicationInfo.packageName);
        localJSONArray.put(paramFREContext);
      }
      catch (Exception paramFREContext) {}
      break;
    }
  }
  
  public static Boolean installApplication(FREContext paramFREContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + paramString));
      paramFREContext.getActivity().startActivity(paramString);
      return Boolean.valueOf(true);
    }
    catch (Exception paramFREContext) {}
    return Boolean.valueOf(false);
  }
  
  public static Boolean installLocalApplication(FREContext paramFREContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW").setDataAndType(Uri.parse(paramString), "application/vnd.android.package-archive");
      paramFREContext.getActivity().startActivity(paramString);
      return Boolean.valueOf(true);
    }
    catch (Exception paramFREContext) {}
    return Boolean.valueOf(false);
  }
  
  public static Boolean runApplication(FREContext paramFREContext, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = paramFREContext.getActivity().getPackageManager().getLaunchIntentForPackage(paramString1);
      paramString2 = new JSONObject(paramString2);
      Iterator localIterator = paramString2.keys();
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          paramFREContext.getActivity().startActivity(paramString1);
          return Boolean.valueOf(true);
        }
        String str = (String)localIterator.next();
        if ((paramString2.getString(str) instanceof String)) {
          paramString1.putExtra(str, paramString2.getString(str));
        }
      }
      return Boolean.valueOf(false);
    }
    catch (Exception paramFREContext) {}
  }
  
  public static Boolean uninstallApplication(FREContext paramFREContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
      paramFREContext.getActivity().startActivity(paramString);
      return Boolean.valueOf(true);
    }
    catch (Exception paramFREContext) {}
    return Boolean.valueOf(false);
  }
}
