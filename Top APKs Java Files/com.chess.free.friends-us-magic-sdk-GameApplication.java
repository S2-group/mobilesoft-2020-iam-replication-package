package us.magic.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
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
import o1.l0.bm;
import o1.l0.bs;
import o1.l0.cl;
import us.magic.sdk.plugin.BaseApplication;
import us.magic.sdk.utility.json.JSONArray;
import us.magic.sdk.utility.json.JSONObject;

public class GameApplication
  extends BaseApplication
{
  public static String BITMAPCACHE = Environment.getExternalStorageDirectory().getPath() + "/netimages";
  public static String CONFIG = Environment.getExternalStorageDirectory().getPath() + "/gamecfg";
  private static GameApplication app;
  public static String google_play = "com.android.vending";
  public static Map<String, Boolean> queueMap = new HashMap();
  private List<bm> appDataList;
  private List<String> categoryList;
  boolean dir = new File(BITMAPCACHE).mkdirs();
  private Map<String, String> installPackages;
  private Map<String, List<bm>> map;
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
  
  public void addAppdata(String paramString, bm paramBm)
  {
    if (paramBm != null)
    {
      if (!this.map.containsKey(paramString))
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(paramBm);
        this.map.put(paramString, localArrayList);
      }
    }
    else {
      return;
    }
    ((List)this.map.get(paramString)).add(paramBm);
  }
  
  public List<String> getAppCategoryList()
  {
    if (!bs.a().a()) {
      return new ArrayList();
    }
    return this.categoryList;
  }
  
  public Map<String, List<bm>> getAppData()
  {
    if (!bs.a().a()) {
      return new HashMap();
    }
    return this.map;
  }
  
  public bm getAppData(String paramString)
  {
    if ((this.appDataList != null) && (this.appDataList.size() > 0))
    {
      Iterator localIterator = this.appDataList.iterator();
      while (localIterator.hasNext())
      {
        bm localBm = (bm)localIterator.next();
        if (paramString.equals(localBm.e)) {
          return localBm;
        }
      }
    }
    return null;
  }
  
  public List<bm> getDistinctAppDataList()
  {
    if (!bs.a().a()) {
      return new ArrayList();
    }
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
        localJSONObject = (JSONObject)new cl().a(paramString);
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
    label125:
    do
    {
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
    } while (((List)localObject2).size() <= 0);
    this.categoryList = ((List)localObject2);
    int i = 0;
    while (i < this.categoryList.size())
    {
      paramString = (JSONArray)localJSONObject.get(this.categoryList.get(i));
      int j;
      if ((paramString != null) && (paramString.size() > 0))
      {
        j = 0;
        if (j < paramString.size())
        {
          localObject1 = (JSONObject)paramString.get(j);
          localObject2 = new bm();
          ((bm)localObject2).jdField_a_of_type_JavaLangString = ((String)((JSONObject)localObject1).get("action"));
          ((bm)localObject2).c = ((String)((JSONObject)localObject1).get("name"));
          ((bm)localObject2).e = ((String)((JSONObject)localObject1).get("pname"));
        }
      }
      try
      {
        ((bm)localObject2).jdField_a_of_type_Int = Integer.parseInt((String)((JSONObject)localObject1).get("market"));
        if ((TextUtils.isEmpty(((bm)localObject2).e)) && (((bm)localObject2).jdField_a_of_type_JavaLangString != null) && (((bm)localObject2).jdField_a_of_type_JavaLangString.startsWith("market://details?id="))) {
          ((bm)localObject2).e = ((bm)localObject2).jdField_a_of_type_JavaLangString.substring("market://details?id=".length());
        }
        try
        {
          getPackageManager().getPackageInfo(((bm)localObject2).e, 0);
          j += 1;
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            ((bm)localObject2).b = ((String)((JSONObject)localObject1).get("icon"));
            ((bm)localObject2).d = ((String)((JSONObject)localObject1).get("desc"));
            addAppdata((String)this.categoryList.get(i), (bm)localObject2);
            if (!this.appDataList.contains(localObject2)) {
              this.appDataList.add(localObject2);
            }
          }
        }
        i += 1;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public boolean isInstall(String paramString)
  {
    return this.installPackages.get(paramString) != null;
  }
  
  public void onCreate()
  {
    app = this;
    if (!isDefaultProcess(this)) {}
    for (;;)
    {
      return;
      super.onCreate();
      init();
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
