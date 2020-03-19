package com.devfo.andutils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DevfoPackageManager
{
  private Activity _activity;
  private PackageManager pm;
  
  public DevfoPackageManager(DevfoUnityPlayerActivity paramDevfoUnityPlayerActivity)
  {
    this._activity = paramDevfoUnityPlayerActivity;
    this.pm = this._activity.getApplicationContext().getPackageManager();
  }
  
  private void launchComponent(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    localIntent.setFlags(268435456);
    this._activity.startActivity(localIntent);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo, boolean paramBoolean)
  {
    boolean bool = false;
    if (paramBoolean)
    {
      paramBoolean = bool;
      if (Build.VERSION.SDK_INT >= 8) {
        paramBoolean = this.pm.addPermissionAsync(paramPermissionInfo);
      }
      return paramBoolean;
    }
    return this.pm.addPermission(paramPermissionInfo);
  }
  
  public String getInstalledApplications(boolean paramBoolean)
  {
    List localList = this._activity.getPackageManager().getInstalledPackages(0);
    JSONObject localJSONObject = new JSONObject();
    int i = 0;
    if (i >= localList.size()) {
      return localJSONObject.toString();
    }
    PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
    if ((!paramBoolean) && (localPackageInfo.versionName == null)) {}
    for (;;)
    {
      i += 1;
      break;
      try
      {
        localJSONObject.put(localPackageInfo.applicationInfo.loadLabel(this._activity.getPackageManager()).toString(), localPackageInfo.packageName);
      }
      catch (JSONException localJSONException) {}
    }
  }
  
  /* Error */
  public String getPackageInfo(String paramString, int paramInt)
  {
    // Byte code:
    //   0: new 89	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 90	org/json/JSONObject:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 29	com/devfo/andutils/DevfoPackageManager:pm	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: iload_2
    //   14: invokevirtual 137	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   17: astore_1
    //   18: aload_3
    //   19: ldc -117
    //   21: aload_1
    //   22: getfield 141	android/content/pm/PackageInfo:sharedUserLabel	I
    //   25: invokevirtual 144	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   28: pop
    //   29: aload_3
    //   30: ldc -110
    //   32: aload_1
    //   33: getfield 148	android/content/pm/PackageInfo:versionCode	I
    //   36: invokevirtual 144	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   39: pop
    //   40: aload_3
    //   41: ldc -106
    //   43: aload_1
    //   44: getfield 153	android/content/pm/PackageInfo:gids	[I
    //   47: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   50: pop
    //   51: aload_3
    //   52: ldc -101
    //   54: aload_1
    //   55: getfield 158	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   58: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   61: pop
    //   62: aload_3
    //   63: ldc -96
    //   65: aload_1
    //   66: getfield 162	android/content/pm/PackageInfo:sharedUserId	Ljava/lang/String;
    //   69: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   72: pop
    //   73: aload_3
    //   74: ldc -93
    //   76: aload_1
    //   77: getfield 110	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   80: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   83: pop
    //   84: aload_3
    //   85: ldc -91
    //   87: aload_1
    //   88: getfield 168	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   91: invokevirtual 169	java/lang/Object:toString	()Ljava/lang/String;
    //   94: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload_3
    //   99: ldc -85
    //   101: aload_1
    //   102: getfield 174	android/content/pm/PackageInfo:configPreferences	[Landroid/content/pm/ConfigurationInfo;
    //   105: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload_3
    //   110: ldc -80
    //   112: aload_1
    //   113: getfield 179	android/content/pm/PackageInfo:permissions	[Landroid/content/pm/PermissionInfo;
    //   116: invokevirtual 130	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   119: pop
    //   120: aload_3
    //   121: invokevirtual 100	org/json/JSONObject:toString	()Ljava/lang/String;
    //   124: areturn
    //   125: astore_1
    //   126: new 181	java/lang/StringBuilder
    //   129: dup
    //   130: ldc -73
    //   132: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   135: aload_1
    //   136: invokevirtual 185	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   139: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 190	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: areturn
    //   146: astore_1
    //   147: goto -27 -> 120
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	this	DevfoPackageManager
    //   0	150	1	paramString	String
    //   0	150	2	paramInt	int
    //   7	114	3	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   8	18	125	android/content/pm/PackageManager$NameNotFoundException
    //   18	120	125	android/content/pm/PackageManager$NameNotFoundException
    //   18	120	146	org/json/JSONException
  }
  
  public boolean hasPermission(String paramString1, String paramString2)
  {
    return this.pm.checkPermission(paramString1, paramString2) == 0;
  }
  
  public boolean isSafeMode()
  {
    return this.pm.isSafeMode();
  }
  
  public void removeAppIcon()
  {
    this.pm.setComponentEnabledSetting(this._activity.getComponentName(), 2, 1);
  }
  
  public void removePermission(String paramString)
  {
    this.pm.removePermission(paramString);
  }
  
  public void startApplication(String paramString)
  {
    try
    {
      Object localObject = new Intent("android.intent.action.MAIN");
      ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
      ((Intent)localObject).addFlags(65536);
      localObject = this._activity.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator();
      ResolveInfo localResolveInfo;
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          return;
        }
        localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      } while (!localResolveInfo.activityInfo.packageName.equalsIgnoreCase(paramString));
      launchComponent(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name);
      return;
    }
    catch (ActivityNotFoundException paramString) {}
  }
}
