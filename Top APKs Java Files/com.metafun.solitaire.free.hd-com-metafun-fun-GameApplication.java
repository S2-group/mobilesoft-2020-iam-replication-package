package com.metafun.fun;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import com.metafun.fun.utility.json.JSONArray;
import com.metafun.fun.utility.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import y.c.ax;
import y.c.bm;

public class GameApplication
  extends BaseApplication
{
  public static String BITMAPCACHE = Environment.getExternalStorageDirectory().getPath() + "/netimages";
  public static String CONFIG = Environment.getExternalStorageDirectory().getPath() + "/gamecfg";
  private static GameApplication app;
  public static String google_play = "com.android.vending";
  public static Map<String, Boolean> queueMap = new HashMap();
  private List<ax> appDataList;
  private List<String> categoryList;
  boolean dir = new File(BITMAPCACHE).mkdirs();
  private Map<String, String> installPackages;
  private Map<String, List<ax>> map;
  boolean mk = new File(CONFIG).mkdirs();
  
  public GameApplication() {}
  
  public static GameApplication getInstance()
  {
    return app;
  }
  
  private static String getProcessName(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == paramInt) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  private void init()
  {
    this.installPackages = new HashMap();
    this.map = new LinkedHashMap();
    this.appDataList = new ArrayList();
    this.categoryList = new ArrayList();
  }
  
  private static boolean isDefaultProcess(Application paramApplication)
  {
    String str = getProcessName(paramApplication, Process.myPid());
    return (str != null) && (str.equals(paramApplication.getPackageName()));
  }
  
  public void addAppdata(String paramString, ax paramAx)
  {
    if (paramAx != null)
    {
      if (!this.map.containsKey(paramString))
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(paramAx);
        this.map.put(paramString, localArrayList);
      }
    }
    else {
      return;
    }
    ((List)this.map.get(paramString)).add(paramAx);
  }
  
  public List<String> getAppCategoryList()
  {
    return this.categoryList;
  }
  
  public Map<String, List<ax>> getAppData()
  {
    return this.map;
  }
  
  public ax getAppData(String paramString)
  {
    if ((this.appDataList != null) && (this.appDataList.size() > 0))
    {
      Iterator localIterator = this.appDataList.iterator();
      while (localIterator.hasNext())
      {
        ax localAx = (ax)localIterator.next();
        if (paramString.equals(localAx.e)) {
          return localAx;
        }
      }
    }
    return null;
  }
  
  public List<ax> getDistinctAppDataList()
  {
    return this.appDataList;
  }
  
  public void initAppData(final String paramString)
  {
    JSONObject localJSONObject;
    Object localObject1;
    Object localObject2;
    for (;;)
    {
      try
      {
        localJSONObject = (JSONObject)new bm().a(paramString);
        app.getAppData().clear();
        if (localJSONObject == null) {
          continue;
        }
        localObject1 = localJSONObject.keySet().iterator();
        localObject2 = new ArrayList();
      }
      catch (Exception paramString)
      {
        String str;
        return;
        ((List)localObject2).add(str);
        continue;
      }
      finally {}
      if (!((Iterator)localObject1).hasNext()) {
        break label125;
      }
      str = (String)((Iterator)localObject1).next();
      if ((str != null) && (str.trim().length() != 0)) {
        continue;
      }
      ((Iterator)localObject1).remove();
    }
    for (;;)
    {
      label125:
      Collections.sort((List)localObject2, new Comparator()
      {
        public int a(String paramAnonymousString1, String paramAnonymousString2)
        {
          paramAnonymousString1 = "\"" + paramAnonymousString1 + "\":";
          int i = paramString.indexOf(paramAnonymousString1);
          paramAnonymousString1 = "\"" + paramAnonymousString2 + "\":";
          return i - paramString.indexOf(paramAnonymousString1);
        }
      });
      if (((List)localObject2).size() > 0)
      {
        this.categoryList = ((List)localObject2);
        int i = 0;
        while (i < this.categoryList.size())
        {
          paramString = (JSONArray)localJSONObject.get(this.categoryList.get(i));
          if ((paramString != null) && (paramString.size() > 0))
          {
            int j = 0;
            while (j < paramString.size())
            {
              localObject1 = (JSONObject)paramString.get(j);
              localObject2 = new ax();
              ((ax)localObject2).a = ((String)((JSONObject)localObject1).get("action"));
              ((ax)localObject2).c = ((String)((JSONObject)localObject1).get("name"));
              ((ax)localObject2).e = ((String)((JSONObject)localObject1).get("pname"));
              if ((TextUtils.isEmpty(((ax)localObject2).e)) && (((ax)localObject2).a != null) && (((ax)localObject2).a.startsWith("market://details?id="))) {
                ((ax)localObject2).e = ((ax)localObject2).a.substring("market://details?id=".length());
              }
              ((ax)localObject2).b = ((String)((JSONObject)localObject1).get("icon"));
              ((ax)localObject2).d = ((String)((JSONObject)localObject1).get("desc"));
              addAppdata((String)this.categoryList.get(i), (ax)localObject2);
              if (!this.appDataList.contains(localObject2)) {
                this.appDataList.add(localObject2);
              }
              j += 1;
            }
          }
          i += 1;
        }
      }
    }
  }
  
  public boolean isInstall(String paramString)
  {
    return this.installPackages.get(paramString) != null;
  }
  
  public void onCreate()
  {
    if (!isDefaultProcess(this)) {}
    for (;;)
    {
      return;
      super.onCreate();
      init();
      app = this;
      List localList = getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        this.installPackages.put(localPackageInfo.packageName, localPackageInfo.packageName);
        i += 1;
      }
    }
  }
  
  public void writeFile(String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        paramString2 = openFileOutput(paramString2, 0);
      }
      catch (FileNotFoundException paramString1)
      {
        paramString1.printStackTrace();
        continue;
      }
      finally {}
      try
      {
        paramString2.write(paramString1.getBytes("UTF-8"));
        paramString2.close();
        return;
      }
      catch (IOException paramString1)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public void writeLocal(String paramString)
  {
    for (;;)
    {
      try
      {
        localFileOutputStream = openFileOutput("apps.json", 0);
      }
      catch (FileNotFoundException paramString)
      {
        FileOutputStream localFileOutputStream;
        paramString.printStackTrace();
        continue;
      }
      finally {}
      try
      {
        localFileOutputStream.write(paramString.getBytes("UTF-8"));
        localFileOutputStream.close();
        return;
      }
      catch (IOException paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
}
