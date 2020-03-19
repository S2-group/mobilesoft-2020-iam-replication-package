package com.ntrack.audioroute;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioRouteHostController
{
  boolean a = false;
  private String b;
  private long c = 0L;
  private Map<String, Pair<Long, Integer>> d = new HashMap();
  private boolean e = true;
  private OnModuleCreatedListener f;
  private Bitmap g;
  private Activity h;
  
  static
  {
    System.loadLibrary("audiomodule");
  }
  
  public AudioRouteHostController() {}
  
  static String a(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getFilesDir());
    localStringBuilder.append("/audioroutescan.dat");
    return localStringBuilder.toString();
  }
  
  static void a() {}
  
  private void a(Activity paramActivity, String paramString, Bitmap paramBitmap, OnModuleCreatedListener paramOnModuleCreatedListener, final int paramInt, long paramLong)
  {
    this.h = paramActivity;
    this.f = paramOnModuleCreatedListener;
    this.b = paramString;
    if (paramInt != -1)
    {
      this.c = paramLong;
      this.a = Audioroute.isModuleConnected(this.c);
      if (this.a) {}
    }
    else
    {
      for (boolean bool = Audioroute.isAlive(this.c);; bool = false)
      {
        this.a = bool;
        break label205;
        if (this.d.containsKey(this.b))
        {
          paramActivity = (Pair)this.d.get(this.b);
          this.c = ((Long)paramActivity.first).longValue();
          this.d.put(this.b, new Pair(Long.valueOf(this.c), Integer.valueOf(((Integer)paramActivity.second).intValue() + 1)));
          break;
        }
        this.c = Audioroute.a(paramActivity, 2, 2);
        this.d.put(this.b, new Pair(Long.valueOf(this.c), Integer.valueOf(1)));
      }
    }
    label205:
    if ((AudioModuleForegroundService.instance != null) && (this.e))
    {
      AudioModuleForegroundService.instance.addListener(new AudioModuleForegroundService.OnServiceStartedListener()
      {
        public final void onDisconnect() {}
        
        public final void serviceStarted() {}
      });
      a(this.a ^ true, paramInt);
      return;
    }
    AudioModuleForegroundService.startService(this.h, new AudioModuleForegroundService.OnServiceStartedListener()
    {
      public final void onDisconnect() {}
      
      public final void serviceStarted()
      {
        AudioRouteHostController.this.a(AudioRouteHostController.this.a ^ true, paramInt);
      }
    }, paramBitmap);
  }
  
  private String b(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 19)
      {
        paramContext = readFile(a(paramContext), StandardCharsets.UTF_8);
        return paramContext;
      }
      return null;
    }
    catch (IOException paramContext) {}
    return null;
  }
  
  public static String readFile(String paramString, Charset paramCharset)
  {
    paramString = new FileInputStream(paramString);
    try
    {
      paramCharset = new BufferedReader(new InputStreamReader(paramString, paramCharset));
      StringBuilder localStringBuilder = new StringBuilder();
      char[] arrayOfChar = new char[' '];
      for (;;)
      {
        int i = paramCharset.read(arrayOfChar, 0, 8192);
        if (i <= 0) {
          break;
        }
        localStringBuilder.append(arrayOfChar, 0, i);
      }
      paramCharset = localStringBuilder.toString();
      return paramCharset;
    }
    finally
    {
      paramString.close();
    }
  }
  
  final void a(boolean paramBoolean, int paramInt)
  {
    Intent localIntent = this.h.getPackageManager().getLaunchIntentForPackage(this.b);
    if (localIntent != null)
    {
      localIntent.putExtra("gobacktoapp", this.h.getPackageName());
      localIntent.putExtra("requestingNewInstance", true);
      localIntent.putExtra("audiorouteconnection", true);
      localIntent.putExtra("request_new_instance", paramBoolean);
      if (paramInt != -1) {
        localIntent.putExtra("force_instance_resuscitating", paramInt);
      }
      this.h.startActivity(localIntent);
      if (!paramBoolean) {}
    }
    try
    {
      Audioroute.a(this.c);
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    Log.d("AudioRoute", "Error establishing connection to module, invalid state");
    return;
  }
  
  final boolean a(String paramString, Context paramContext, ScanModulesListener paramScanModulesListener)
  {
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      try
      {
        JSONArray localJSONArray = new JSONObject(paramString).getJSONArray("apps");
        i = 0;
        j = localJSONArray.length();
        bool = true;
        if (i >= j) {
          continue;
        }
        localModuleInfo1 = new ModuleInfo();
        localObject = localJSONArray.getJSONObject(i);
        localModuleInfo1.friendlyName = ((JSONObject)localObject).getString("name");
        localModuleInfo1.isSynth = ((JSONObject)localObject).getInt("generator");
        localModuleInfo1.numericId = i;
        localModuleInfo1.packagename = ((JSONObject)localObject).getString("package_name");
        localModuleInfo1.wantsMidi = ((JSONObject)localObject).getInt("accepts_midi");
        localModuleInfo1.minVersion = ((JSONObject)localObject).getInt("app_version");
        if (((JSONObject)localObject).optInt("supports_multiple_instances") == 0) {
          continue;
        }
      }
      catch (JSONException paramContext)
      {
        int i;
        int j;
        ModuleInfo localModuleInfo1;
        Object localObject;
        ModuleInfo localModuleInfo2;
        continue;
        boolean bool = false;
        continue;
      }
      localModuleInfo1.supportsMultiInstance = bool;
      if (!localModuleInfo1.packagename.equals(paramContext.getPackageName()))
      {
        j = 0;
        if (j < localList.size())
        {
          localObject = (PackageInfo)localList.get(j);
          if (((PackageInfo)localObject).packageName.equals(localModuleInfo1.packagename))
          {
            if (((PackageInfo)localObject).versionCode < localModuleInfo1.minVersion) {
              continue;
            }
            localModuleInfo2 = (ModuleInfo)localHashMap.get(((PackageInfo)localObject).packageName);
            if ((localModuleInfo2 == null) || (localModuleInfo1.minVersion >= localModuleInfo2.minVersion))
            {
              localHashMap.put(((PackageInfo)localObject).packageName, localModuleInfo1);
              continue;
            }
          }
          j += 1;
          continue;
        }
      }
      i += 1;
    }
    paramScanModulesListener.onScanFinished(new ArrayList(localHashMap.values()));
    return true;
    paramContext = new StringBuilder("Error parsing json: ");
    paramContext.append(paramString);
    Log.d("AudioRoute", paramContext.toString());
    return false;
  }
  
  public long getNativeEngineReference()
  {
    return this.c;
  }
  
  public Drawable getPackageIcon(String paramString)
  {
    return AudiorouteActivityController.a(this.h, paramString);
  }
  
  public Map<String, String> getScanParams(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("android_sdkint", Integer.toString(Build.VERSION.SDK_INT));
    localHashMap.put("cpu_abi", Build.CPU_ABI);
    localHashMap.put("additional_abi", Build.CPU_ABI2);
    localHashMap.put("device_brand", Build.BRAND);
    localHashMap.put("device", Build.DEVICE);
    localHashMap.put("hardware", Build.HARDWARE);
    localHashMap.put("manufacturer", Build.MANUFACTURER);
    localHashMap.put("model", Build.MODEL);
    localHashMap.put("product", Build.PRODUCT);
    localHashMap.put("hostapp", paramContext.getPackageName());
    return localHashMap;
  }
  
  public String getScanUrl(Context paramContext)
  {
    return "https://audioroute.ntrack.com/ws/apps/scan.php";
  }
  
  public void instantiateAudiorouteModule(Activity paramActivity, String paramString, OnModuleCreatedListener paramOnModuleCreatedListener)
  {
    a(paramActivity, paramString, this.g, paramOnModuleCreatedListener, -1, 0L);
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    if (paramIntent.hasExtra("audioroutemoduleindex"))
    {
      int i = paramIntent.getIntExtra("audioroutemoduleindex", -1);
      int j = paramIntent.getIntExtra("instanceindex", -1);
      String str = paramIntent.getStringExtra("audioroutereturningfromapp");
      if ((paramIntent.getIntExtra("audioroutemodulecreated", 0) != 0) && (this.f != null))
      {
        paramIntent = new ModuleRuntimeInfo();
        paramIntent.moduleindex = i;
        paramIntent.instanceId = j;
        paramIntent.packagename = str;
        paramIntent.engineref = getNativeEngineReference();
        this.f.onModuleCreated(paramIntent);
      }
    }
  }
  
  public void releaseModuleInstance(ModuleRuntimeInfo paramModuleRuntimeInfo)
  {
    Pair localPair = (Pair)this.d.get(paramModuleRuntimeInfo.packagename);
    if (localPair == null) {
      return;
    }
    if (((Integer)localPair.second).intValue() <= 1)
    {
      Audioroute.a(paramModuleRuntimeInfo.engineref, paramModuleRuntimeInfo.instanceId);
      this.d.remove(paramModuleRuntimeInfo.packagename);
    }
    else
    {
      this.d.put(paramModuleRuntimeInfo.packagename, new Pair(localPair.first, Integer.valueOf(((Integer)localPair.second).intValue() - 1)));
    }
    if ((this.d.isEmpty()) && (this.e)) {
      AudioModuleForegroundService.stopService(this.h);
    }
  }
  
  public void resuscitateApp(Activity paramActivity, OnModuleCreatedListener paramOnModuleCreatedListener, ModuleRuntimeInfo paramModuleRuntimeInfo)
  {
    a(paramActivity, paramModuleRuntimeInfo.packagename, this.g, paramOnModuleCreatedListener, paramModuleRuntimeInfo.instanceId, paramModuleRuntimeInfo.engineref);
  }
  
  public boolean scanInstalledModules(final Activity paramActivity, final ScanModulesListener paramScanModulesListener, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 23)
    {
      Log.i("AudioRoute", "Audioroute requires API 24 or higher");
      return false;
    }
    this.h = paramActivity;
    if (!paramBoolean)
    {
      localObject = new File(a(paramActivity));
      if (!((File)localObject).exists())
      {
        paramBoolean = false;
      }
      else
      {
        localObject = new Date(((File)localObject).lastModified());
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.add(10, -1);
        paramBoolean = ((Date)localObject).after(localCalendar.getTime());
      }
      if (paramBoolean)
      {
        localObject = b(paramActivity);
        if (localObject != null) {
          a((String)localObject, paramActivity, paramScanModulesListener);
        }
        return true;
      }
    }
    Object localObject = new UrlTask();
    ((UrlTask)localObject).a = new UrlTask.UrlTaskListener()
    {
      public final void onError(UrlTask.UrlError paramAnonymousUrlError) {}
      
      public final void onResponse(String paramAnonymousString)
      {
        if (paramAnonymousString == null)
        {
          Log.d("AudioRoute", "Got a null response");
          return;
        }
        if (AudioRouteHostController.this.a(paramAnonymousString, paramActivity, paramScanModulesListener))
        {
          Object localObject = AudioRouteHostController.this;
          localObject = new File(AudioRouteHostController.a(paramActivity));
          try
          {
            localObject = new FileOutputStream((File)localObject, false);
            ((FileOutputStream)localObject).write(paramAnonymousString.getBytes());
            ((FileOutputStream)localObject).close();
            return;
          }
          catch (Exception paramAnonymousString)
          {
            paramAnonymousString.printStackTrace();
          }
        }
      }
    };
    ((UrlTask)localObject).b = getScanParams(paramActivity);
    ((UrlTask)localObject).execute(new String[] { getScanUrl(paramActivity) });
    return true;
  }
  
  public void setHostNotificationIcon(Bitmap paramBitmap)
  {
    this.g = paramBitmap;
  }
  
  public void setShowForegroundServiceNotification(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }
  
  public void showUserInterface(Activity paramActivity, ModuleRuntimeInfo paramModuleRuntimeInfo)
  {
    Intent localIntent = paramActivity.getPackageManager().getLaunchIntentForPackage(paramModuleRuntimeInfo.packagename);
    if (localIntent != null)
    {
      localIntent.putExtra("requestingNewInstance", false);
      localIntent.putExtra("showinterfaceforinstance", paramModuleRuntimeInfo.instanceId);
      paramActivity.startActivity(localIntent);
    }
  }
  
  public class ModuleInfo
  {
    public String friendlyName;
    public int isSynth;
    public int minVersion;
    public int numericId;
    public String packagename;
    public boolean supportsMultiInstance;
    public int wantsMidi;
    
    public ModuleInfo() {}
  }
  
  public static class ModuleRuntimeInfo
  {
    public long engineref;
    public int instanceId;
    public int moduleindex;
    public String packagename;
    
    public ModuleRuntimeInfo() {}
  }
  
  public static abstract interface OnModuleCreatedListener
  {
    public abstract void onModuleCreated(AudioRouteHostController.ModuleRuntimeInfo paramModuleRuntimeInfo);
  }
  
  public static abstract interface ScanModulesListener
  {
    public abstract void onScanFinished(List<AudioRouteHostController.ModuleInfo> paramList);
  }
}
