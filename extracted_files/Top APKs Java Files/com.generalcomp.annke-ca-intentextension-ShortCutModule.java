package ca.intentextension;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.proxy.IntentProxy;

public class ShortCutModule
  extends KrollModule
{
  private static final boolean DBG = TiConfig.LOGD;
  private static final String LCAT = "ShortCutModule";
  
  public ShortCutModule() {}
  
  private String getAppName()
  {
    localObject1 = null;
    try
    {
      localObject2 = getActivity().getApplicationContext().getPackageManager();
      localObject1 = localObject2;
      localObject4 = ((PackageManager)localObject2).getApplicationInfo(getActivity().getPackageName(), 0);
      localObject1 = localObject4;
      localObject4 = localObject2;
      localObject2 = localObject1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject2;
        Object localObject3 = null;
        Object localObject4 = localObject1;
      }
    }
    return (String)((PackageManager)localObject4).getApplicationLabel((ApplicationInfo)localObject2);
  }
  
  public static String getAuthorityFromPermission(Context paramContext)
  {
    String str2 = getAuthorityFromPermissionDefault(paramContext);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.trim().equals("")) {}
    }
    else
    {
      str1 = getCurrentLauncherPackageName(paramContext);
      str1 = getThirdAuthorityFromPermission(paramContext, str1 + ".permission.READ_SETTINGS");
    }
    paramContext = str1;
    int i;
    if (TextUtils.isEmpty(str1))
    {
      i = Build.VERSION.SDK_INT;
      if (i >= 8) {
        break label99;
      }
      paramContext = "com.android.launcher.settings";
    }
    for (;;)
    {
      return "content://" + paramContext + "/favorites?notify=true";
      label99:
      if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else {
        paramContext = "com.android.launcher3.settings";
      }
    }
  }
  
  public static String getAuthorityFromPermissionDefault(Context paramContext)
  {
    return getThirdAuthorityFromPermission(paramContext, "com.android.launcher.permission.READ_SETTINGS");
  }
  
  public static String getCurrentLauncherPackageName(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {
      return "";
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      return "";
    }
    return paramContext.activityInfo.packageName;
  }
  
  private Intent getShortCutIntent(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.putExtra(paramString, paramString);
    localIntent.setClass(getActivity(), getActivity().getClass());
    return localIntent;
  }
  
  public static String getThirdAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if (((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) && (!TextUtils.isEmpty(localProviderInfo.authority)) && (localProviderInfo.authority.contains(".launcher.settings")))
            {
              paramContext = localProviderInfo.authority;
              return paramContext;
            }
            i += 1;
          }
        }
      }
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void onAppCreate(TiApplication paramTiApplication)
  {
    Log.d("ShortCutModule", "inside onAppCreate");
  }
  
  public boolean checkShortCut(HashMap paramHashMap)
  {
    String str = "";
    if (paramHashMap.containsKey("shortcutName")) {
      str = (String)paramHashMap.get("shortcutName");
    }
    paramHashMap = null;
    boolean bool2 = false;
    boolean bool5 = false;
    boolean bool4 = false;
    if (TextUtils.isEmpty(null)) {
      paramHashMap = getAuthorityFromPermission(getActivity());
    }
    ContentResolver localContentResolver = getActivity().getContentResolver();
    boolean bool3;
    if (!TextUtils.isEmpty(paramHashMap)) {
      bool3 = bool5;
    }
    try
    {
      paramHashMap = localContentResolver.query(Uri.parse(paramHashMap), new String[] { "title", "iconResource" }, "title=?", new String[] { str }, null);
      boolean bool1 = bool4;
      if (paramHashMap != null)
      {
        bool1 = bool4;
        bool3 = bool5;
        if (paramHashMap.getCount() > 0) {
          bool1 = true;
        }
      }
      bool2 = bool1;
      if (paramHashMap != null)
      {
        bool2 = bool1;
        bool3 = bool1;
        if (!paramHashMap.isClosed())
        {
          bool3 = bool1;
          paramHashMap.close();
          bool2 = bool1;
        }
      }
      return bool2;
    }
    catch (Exception paramHashMap)
    {
      Log.e("isShortCutExist", paramHashMap.getMessage());
    }
    return bool3;
  }
  
  public void createShortCut(HashMap paramHashMap)
  {
    String str1 = "";
    try
    {
      Object localObject = getActivity().getResources();
      String str2 = getActivity().getPackageName();
      int i = -1;
      if (paramHashMap.containsKey("shortcutName")) {
        str1 = (String)paramHashMap.get("shortcutName");
      }
      if (paramHashMap.containsKey("shortcutIcon")) {
        i = ((Resources)localObject).getIdentifier((String)paramHashMap.get("shortcutIcon"), "drawable", str2);
      }
      localObject = Intent.ShortcutIconResource.fromContext(getActivity(), i);
      paramHashMap = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
      paramHashMap.putExtra("duplicate", false);
      paramHashMap.putExtra("android.intent.extra.shortcut.NAME", str1);
      paramHashMap.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)localObject);
      localObject = new Intent("android.intent.action.MAIN");
      ((Intent)localObject).setClass(getActivity(), getActivity().getClass());
      ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
      ((Intent)localObject).putExtra(str1, str1);
      paramHashMap.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)localObject);
      getActivity().sendBroadcast(paramHashMap);
      fireEvent("CreateShortcutSuccess", null);
      return;
    }
    catch (Exception paramHashMap)
    {
      paramHashMap.printStackTrace();
      fireEvent("CreateShortcutError", null);
    }
  }
  
  public void deleteShortCut(HashMap paramHashMap)
  {
    Object localObject = null;
    if (paramHashMap.containsKey("shortcutName")) {
      localObject = (String)paramHashMap.get("shortcutName");
    }
    if (localObject != null)
    {
      paramHashMap = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
      paramHashMap.putExtra("android.intent.extra.shortcut.NAME", (String)localObject);
      localObject = new Intent();
      ((Intent)localObject).setClass(getActivity(), getActivity().getClass());
      ((Intent)localObject).setAction("android.intent.action.MAIN");
      ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
      paramHashMap.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)localObject);
      getActivity().sendBroadcast(paramHashMap);
    }
  }
  
  public String example()
  {
    Log.d("ShortCutModule", "example called");
    return "hello world";
  }
  
  public void getAppDetailSettingIntent()
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    if (Build.VERSION.SDK_INT >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
    }
    for (;;)
    {
      getActivity().startActivity(localIntent);
      return;
      if (Build.VERSION.SDK_INT <= 8)
      {
        localIntent.setAction("android.intent.action.VIEW");
        localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
        localIntent.putExtra("com.android.settings.ApplicationPkgName", getActivity().getPackageName());
      }
    }
  }
  
  public String getExampleProp()
  {
    Log.d("ShortCutModule", "get example property");
    return "hello world";
  }
  
  public EnhancedIntentProxy getIntentExtension(IntentProxy paramIntentProxy)
  {
    return new EnhancedIntentProxy(paramIntentProxy.getIntent());
  }
  
  public void setExampleProp(String paramString)
  {
    Log.d("ShortCutModule", "set example property: " + paramString);
  }
}
