package mobi.infolife.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import mobi.infolife.ezweather.EmptyService;
import mobi.infolife.ezweather.FAQWebViewActivity;
import mobi.infolife.ezweather.d.a.h;
import mobi.infolife.ezweather.livewallpaper.g;
import mobi.infolife.ezweather.widgetscommon.PreferencesLibrary;
import mobi.infolife.ezweather.widgetscommon.WeatherUtilsLibrary;

public class d
{
  public static String a = "";
  public static String b = "";
  private static String c = d.class.getName();
  
  public d() {}
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int a(Class paramClass, String paramString1, String paramString2)
  {
    int j = 0;
    for (;;)
    {
      try
      {
        Class[] arrayOfClass = paramClass.getClasses();
        int k = arrayOfClass.length;
        int i = 0;
        if (i < k)
        {
          paramClass = arrayOfClass[i];
          if (paramClass.getName().split("\\$")[1].equals(paramString1))
          {
            i = j;
            if (paramClass != null) {
              i = paramClass.getField(paramString2).getInt(paramClass);
            }
            return i;
          }
          i += 1;
        }
        else
        {
          paramClass = null;
        }
      }
      catch (Exception paramClass)
      {
        paramClass.printStackTrace();
        return 0;
      }
    }
  }
  
  public static String a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext, 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "Beta";
  }
  
  public static List<PackageInfo> a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<mobi.infolife.ezweather.storecache.a> a(Context paramContext, int paramInt, List<mobi.infolife.ezweather.storecache.a> paramList)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = a(paramContext, 8192);
    if (paramInt == 3) {
      c(paramContext, paramList);
    }
    if (paramInt == 4) {
      a(paramContext, paramList);
    }
    if (paramInt == 5) {
      b(paramContext, paramList);
    }
    Iterator localIterator = ((List)localObject).iterator();
    localObject = null;
    label275:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        String str2 = ((PackageInfo)localIterator.next()).packageName;
        try
        {
          ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str2, 128);
          localObject = localApplicationInfo;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
          str1 = ((ApplicationInfo)localObject).metaData.getString("EZWEATHER_PLUGIN");
          if (str1 == null) {
            break label275;
          }
        }
        if (localObject != null)
        {
          String str1;
          if ((((ApplicationInfo)localObject).metaData != null) && (!"".equals(str1)))
          {
            switch (paramInt)
            {
            }
            for (;;)
            {
              break;
              if ("mobi.infolife.ezweather.plugin.iconset".equals(str1))
              {
                paramList.add(a(paramContext, str2, localPackageManager, (ApplicationInfo)localObject, 3));
                continue;
                if ("mobi.infolife.ezweather.plugin.widgetskin".equals(str1))
                {
                  paramList.add(a(paramContext, str2, localPackageManager, (ApplicationInfo)localObject, 1));
                  continue;
                  if ("mobi.infolife.ezweather.plugin.notificationskin".equals(str1)) {
                    paramList.add(a(paramContext, str2, localPackageManager, (ApplicationInfo)localObject, 4));
                  }
                }
              }
            }
          }
        }
      }
      else
      {
        return paramList;
      }
    }
  }
  
  private static mobi.infolife.ezweather.storecache.a a(Context paramContext, String paramString, PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo, int paramInt)
  {
    Context localContext = WeatherUtilsLibrary.getPluginAppContext(paramContext, paramString);
    mobi.infolife.ezweather.storecache.a localA = new mobi.infolife.ezweather.storecache.a(paramString);
    localA.r(paramPackageManager.getApplicationLabel(paramApplicationInfo).toString());
    if (localContext != null) {}
    for (;;)
    {
      try
      {
        bool = localContext.getResources().getBoolean(mobi.infolife.ezweather.e.d.a(paramContext, "isFree", "bool", paramString));
        if (bool)
        {
          localA.u("0.00");
          mobi.infolife.ezweather.d.a.a.a(paramContext, paramString, true);
        }
        localA.e(true);
        localA.d(paramInt);
        return localA;
      }
      catch (Exception paramPackageManager)
      {
        boolean bool = false;
        continue;
      }
      localA.u("0.00");
    }
  }
  
  public static mobi.infolife.ezweather.storecache.a a(Context paramContext, mobi.infolife.ezweather.storecache.a paramA)
  {
    Context localContext = WeatherUtilsLibrary.getPluginAppContext(paramContext, paramA.j());
    try
    {
      paramA.o(localContext.getString(mobi.infolife.ezweather.e.d.a(paramContext, "restriction", "string", paramA.j())));
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          paramA.n(localContext.getString(mobi.infolife.ezweather.e.d.a(paramContext, "feature", "string", paramA.j())));
          return paramA;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
        localException = localException;
        localException.printStackTrace();
      }
    }
    return paramA;
  }
  
  public static void a(Activity paramActivity)
  {
    Object localObject = PreferencesLibrary.getLanguage(paramActivity);
    if (!TextUtils.equals((CharSequence)localObject, "auto"))
    {
      if (!((String)localObject).contains("_")) {
        break label85;
      }
      localObject = ((String)localObject).split("_");
    }
    label85:
    for (localObject = new Locale(localObject[0], localObject[1]);; localObject = new Locale((String)localObject))
    {
      Locale.setDefault((Locale)localObject);
      Configuration localConfiguration = new Configuration();
      localConfiguration.locale = ((Locale)localObject);
      paramActivity.getBaseContext().getResources().updateConfiguration(localConfiguration, paramActivity.getBaseContext().getResources().getDisplayMetrics());
      return;
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }
  
  private static void a(Context paramContext, List<mobi.infolife.ezweather.storecache.a> paramList)
  {
    int i = 0;
    while (i < 11)
    {
      mobi.infolife.g.d localD = mobi.infolife.g.e.a(i);
      mobi.infolife.ezweather.storecache.a localA = new mobi.infolife.ezweather.storecache.a(localD.b());
      localA.r(localD.c());
      localA.e(true);
      localA.d(4);
      localA.u("0.00");
      mobi.infolife.ezweather.d.a.a.a(paramContext, localD.b(), true);
      paramList.add(0, localA);
      i += 1;
    }
  }
  
  public static void a(Context paramContext, mobi.infolife.ezweather.storecache.a paramA, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      StringBuilder localStringBuilder = new StringBuilder().append("market://details?id=").append(paramA.j()).append("&referrer=utm_source%3D").append("elite").append("%26utm_medium%3D").append(paramString);
      if (mobi.infolife.ezweather.e.x(paramContext) >= 3) {}
      for (String str = "_old_user";; str = "_new_user")
      {
        localIntent.setData(Uri.parse(str));
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return;
      }
      try
      {
        localIntent = new Intent("android.intent.action.VIEW");
        paramString = new StringBuilder().append(paramA.c()).append("&referrer=utm_source%3D").append("elite").append("%26utm_medium%3D").append(paramString);
        if (mobi.infolife.ezweather.e.x(paramContext) >= 3)
        {
          paramA = "_old_user";
          localIntent.setData(Uri.parse(paramA));
          localIntent.setFlags(268435456);
          paramContext.startActivity(localIntent);
        }
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
    catch (Exception localException)
    {
      if (paramA.c() == null) {}
    }
    for (;;)
    {
      localException.printStackTrace();
      return;
      paramA = "_new_user";
    }
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    if (g.b(paramContext, paramContext.getPackageName()))
    {
      if (paramBoolean) {
        ((Activity)paramContext).finish();
      }
      System.gc();
      return;
    }
    Log.d(c, "------killSelf------");
    Log.d(c, "----e,pty service-----");
    paramContext.startService(new Intent(paramContext, EmptyService.class));
    Process.killProcess(Process.myPid());
  }
  
  public static void a(ImageView paramImageView)
  {
    ((AnimationDrawable)paramImageView.getBackground()).start();
  }
  
  public static void a(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int i = 0;
    int j = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
    paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
  }
  
  public static void a(String paramString) {}
  
  public static String b(Context paramContext)
  {
    return "\t" + a(paramContext);
  }
  
  public static String b(String paramString)
  {
    Log.d(c, "CommUtils getFileNameByUrl url:" + paramString);
    paramString = paramString.split("/");
    paramString = paramString[(paramString.length - 1)];
    Log.d(c, "fileName: " + paramString);
    return paramString;
  }
  
  public static List<mobi.infolife.ezweather.storecache.a> b(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = a(paramContext, 8192);
    if (paramInt == 3) {
      c(paramContext, localArrayList);
    }
    if (paramInt == 4) {
      a(paramContext, localArrayList);
    }
    if (paramInt == 5) {
      b(paramContext, localArrayList);
    }
    Iterator localIterator = ((List)localObject).iterator();
    localObject = null;
    label280:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        String str2 = ((PackageInfo)localIterator.next()).packageName;
        try
        {
          ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str2, 128);
          localObject = localApplicationInfo;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
          str1 = ((ApplicationInfo)localObject).metaData.getString("EZWEATHER_PLUGIN");
          if (str1 == null) {
            break label280;
          }
        }
        if (localObject != null)
        {
          String str1;
          if ((((ApplicationInfo)localObject).metaData != null) && (!"".equals(str1)))
          {
            switch (paramInt)
            {
            }
            for (;;)
            {
              break;
              if ("mobi.infolife.ezweather.plugin.iconset".equals(str1))
              {
                localArrayList.add(a(paramContext, str2, localPackageManager, (ApplicationInfo)localObject, 3));
                continue;
                if ("mobi.infolife.ezweather.plugin.widgetskin".equals(str1))
                {
                  localArrayList.add(a(paramContext, str2, localPackageManager, (ApplicationInfo)localObject, 1));
                  continue;
                  if ("mobi.infolife.ezweather.plugin.notificationskin".equals(str1)) {
                    localArrayList.add(a(paramContext, str2, localPackageManager, (ApplicationInfo)localObject, 4));
                  }
                }
              }
            }
          }
        }
      }
      else
      {
        return localArrayList;
      }
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
  
  private static void b(Context paramContext, List<mobi.infolife.ezweather.storecache.a> paramList)
  {
    mobi.infolife.ezweather.storecache.a localA = new mobi.infolife.ezweather.storecache.a(e.a[0]);
    localA.r("Default");
    localA.e(true);
    localA.d(5);
    localA.u("0.00");
    mobi.infolife.ezweather.d.a.a.a(paramContext, e.a[0], true);
    paramList.add(0, localA);
  }
  
  public static void b(Context paramContext, mobi.infolife.ezweather.storecache.a paramA)
  {
    Object localObject2 = null;
    String str = paramA.c();
    Object localObject1 = localObject2;
    if (str != null)
    {
      localObject1 = localObject2;
      if (!str.equals("")) {
        localObject1 = Uri.parse(str);
      }
    }
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramA.j())));
      return;
    }
    catch (ActivityNotFoundException paramA)
    {
      while (localObject1 == null) {}
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", (Uri)localObject1));
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static Context c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().createPackageContext(paramString, 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void c(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramContext.getPackageName())));
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramContext.getPackageName())));
    }
  }
  
  public static void c(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, FAQWebViewActivity.class);
    localIntent.putExtra("flag", paramInt);
    paramContext.startActivity(localIntent);
  }
  
  private static void c(Context paramContext, List<mobi.infolife.ezweather.storecache.a> paramList)
  {
    mobi.infolife.ezweather.storecache.a localA = new mobi.infolife.ezweather.storecache.a(paramContext.getPackageName());
    localA.r("Default");
    localA.e(true);
    localA.d(3);
    localA.u("0.00");
    mobi.infolife.ezweather.d.a.a.a(paramContext, paramContext.getPackageName(), true);
    paramList.add(0, localA);
    localA = new mobi.infolife.ezweather.storecache.a(paramContext.getPackageName() + ".new");
    localA.r("default_color");
    localA.e(true);
    localA.d(3);
    localA.u("0.00");
    localA.e(true);
    mobi.infolife.ezweather.d.a.a.a(paramContext, paramContext.getPackageName(), true);
    paramList.add(1, localA);
  }
  
  public static boolean c(String paramString)
  {
    return (paramString != null) && (!paramString.equals(""));
  }
  
  public static Class<?> d(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass(paramString + ".R");
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String d(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return paramContext.getString(2131231951);
    case 0: 
    case 1: 
    case 2: 
      return paramContext.getResources().getString(2131231953);
    case 3: 
    case 4: 
    case 5: 
      return paramContext.getString(2131231954);
    case 6: 
    case 7: 
      return paramContext.getString(2131231952);
    }
    return paramContext.getString(2131231955);
  }
  
  public static void d(Context paramContext)
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = mobi.infolife.ezweather.d.a.a.c(paramContext);
    if ((localCalendar.get(11) != 12) || (localCalendar.get(12) != i))
    {
      h.a(paramContext, false, "check version return----" + localCalendar.get(11) + "," + localCalendar.get(12) + "," + i);
      return;
    }
    new Thread(new d.1(paramContext)).start();
  }
  
  public static List<Map<String, Object>> e(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = a(paramContext, 4096);
    paramContext = paramContext.getPackageManager();
    localObject1 = ((List)localObject1).iterator();
    label195:
    while (((Iterator)localObject1).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
      Object localObject2 = localPackageInfo.requestedPermissions;
      if (localObject2 != null)
      {
        int m = localObject2.length;
        int i = 0;
        int j = 0;
        int k = 0;
        for (;;)
        {
          if (i >= m) {
            break label195;
          }
          Object localObject3 = localObject2[i];
          if ("android.permission.KILL_BACKGROUND_PROCESSES".equals(localObject3)) {
            k = 1;
          }
          if ("android.permission.GET_TASKS".equals(localObject3)) {
            j = 1;
          }
          if ((j != 0) && (k != 0))
          {
            localObject2 = new HashMap();
            ((Map)localObject2).put("icon", localPackageInfo.applicationInfo.loadIcon(paramContext));
            ((Map)localObject2).put("name", localPackageInfo.applicationInfo.loadLabel(paramContext).toString());
            localArrayList.add(localObject2);
            break;
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static List<mobi.infolife.ezweather.storecache.a> e(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new mobi.infolife.ezweather.storecache.a();
    ((mobi.infolife.ezweather.storecache.a)localObject).q(paramContext.getPackageName());
    ((mobi.infolife.ezweather.storecache.a)localObject).r(paramContext.getString(2131231063));
    ((mobi.infolife.ezweather.storecache.a)localObject).n(paramContext.getString(2131231971));
    ((mobi.infolife.ezweather.storecache.a)localObject).o(paramContext.getString(2131231972));
    ((mobi.infolife.ezweather.storecache.a)localObject).a(b.a(b.a(paramContext)));
    localArrayList.add(localObject);
    localObject = paramContext.getPackageManager();
    Iterator localIterator = a(paramContext, 8192).iterator();
    while (localIterator.hasNext())
    {
      String str1 = ((PackageInfo)localIterator.next()).packageName;
      mobi.infolife.ezweather.storecache.a localA = new mobi.infolife.ezweather.storecache.a();
      try
      {
        ApplicationInfo localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(str1, 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          String str2 = localApplicationInfo.metaData.getString("EZWEATHER_PLUGIN");
          if ((str2 != null) && (!"".equals(str2)) && (paramString.equals(str2)))
          {
            localA.q(str1);
            localA.c(1);
            localA.a(b.a(b.a(((PackageManager)localObject).getApplicationIcon(localApplicationInfo))));
            localA.r(((PackageManager)localObject).getApplicationLabel(localApplicationInfo).toString());
            localArrayList.add(a(paramContext, localA));
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  public static void f(Context paramContext)
  {
    try
    {
      if (mobi.infolife.ezweather.e.a.a(paramContext.getApplicationContext(), "com.facebook.katana")) {}
      for (Uri localUri = Uri.parse("fb://page/1034152116610641");; localUri = Uri.parse("https://www.facebook.com/pages/EZ-Weather/1034152116610641"))
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", localUri));
        return;
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void g(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    localIntent.addFlags(67108864);
    paramContext.startActivity(localIntent);
    Process.killProcess(Process.myPid());
  }
  
  public static boolean h(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131361793);
  }
}
