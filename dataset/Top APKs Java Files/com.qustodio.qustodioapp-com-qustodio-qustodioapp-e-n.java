package com.qustodio.qustodioapp.e;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.usage.UsageEvents;
import android.app.usage.UsageEvents.Event;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.qustodio.qustodioapp.ParentStatusActivity;
import com.qustodio.qustodioapp.QustodioApp;
import com.qustodio.qustodioapp.activities.PasswordRequestActivity;
import com.qustodio.qustodioapp.activities.StatusActivity;
import com.qustodio.qustodioapp.f.i;
import com.qustodio.qustodioapp.o.ab;
import com.qustodio.qustodioapp.o.z;
import com.qustodio.qustodioapp.service.QustodioService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class n
{
  private static final Logger d = LoggerFactory.getLogger(n.class);
  private static n e = null;
  protected Context a;
  protected HashSet<String> b;
  protected HashSet<String> c;
  private HashSet<String> f;
  private Boolean g;
  private String h;
  
  public n(Context paramContext)
  {
    this.a = paramContext;
    paramContext = new IntentFilter();
    paramContext.addAction("android.intent.action.PACKAGE_ADDED");
    paramContext.addAction("android.intent.action.PACKAGE_REMOVED");
    paramContext.addDataScheme("package");
    this.a.registerReceiver(new p(this, null), paramContext);
  }
  
  private String a()
  {
    Object localObject = ((ActivityManager)this.a.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null) {
      return "";
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if (localRunningAppProcessInfo.pkgList.length == 1) {
        return localRunningAppProcessInfo.pkgList[0];
      }
    }
    return "";
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size();
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private boolean a(File paramFile, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (paramFile != null)
    {
      bool1 = bool2;
      if (paramFile.exists()) {
        i = 0;
      }
    }
    try
    {
      while (i < paramArrayOfString1.length)
      {
        Object localObject = new File(paramFile, paramArrayOfString2[i]);
        InputStream localInputStream = this.a.getAssets().open(paramArrayOfString1[i]);
        localObject = new FileOutputStream((File)localObject);
        byte[] arrayOfByte = new byte[localInputStream.available()];
        localInputStream.read(arrayOfByte);
        ((OutputStream)localObject).write(arrayOfByte);
        localInputStream.close();
        ((OutputStream)localObject).close();
        i += 1;
      }
      bool1 = true;
      return bool1;
    }
    catch (IOException paramFile)
    {
      paramFile.printStackTrace();
    }
    return false;
  }
  
  private boolean a(String paramString)
  {
    boolean bool2 = false;
    PackageManager localPackageManager = this.a.getPackageManager();
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 4096).requestedPermissions;
      boolean bool1 = bool2;
      if (paramString != null)
      {
        boolean bool3 = org.apache.commons.b.a.b(paramString, "android.permission.INTERNET");
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static n c(Context paramContext)
  {
    if (e == null) {
      e = new n(paramContext.getApplicationContext());
    }
    return e;
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Could not get package name: " + paramContext);
    }
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "160.2.2.2-family";
  }
  
  @SuppressLint({"WrongConstant", "NewApi"})
  public static String f(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 21) {
      return null;
    }
    paramContext = (UsageStatsManager)paramContext.getSystemService("usagestats");
    int i;
    if (paramContext != null)
    {
      i = 1;
      if (i == 0) {
        break label102;
      }
      long l = System.currentTimeMillis();
      paramContext = paramContext.queryUsageStats(0, l - 3600000L, l);
      if ((paramContext == null) || (paramContext.isEmpty())) {
        break label95;
      }
      i = 1;
    }
    for (;;)
    {
      if (i != 0)
      {
        Collections.sort(paramContext, new o());
        return ((UsageStats)paramContext.get(0)).getPackageName();
        i = 0;
        break;
        label95:
        i = 0;
        continue;
      }
      return null;
      label102:
      paramContext = null;
    }
  }
  
  public static String z()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return z.a(str2);
    }
    return z.a(str1) + " " + str2;
  }
  
  public String A()
  {
    return Build.MODEL;
  }
  
  public void B() {}
  
  public PackageInfo a(String paramString, ArrayList<String> paramArrayList)
  {
    PackageManager localPackageManager = this.a.getPackageManager();
    do
    {
      try
      {
        paramString = localPackageManager.getPackageInfo(paramString, 0);
        Object localObject = paramArrayList;
        if (paramArrayList == null) {}
        localObject = paramString;
      }
      catch (PackageManager.NameNotFoundException paramArrayList)
      {
        try
        {
          localObject = new ArrayList();
          ((ArrayList)localObject).add(paramString.applicationInfo.loadLabel(localPackageManager).toString());
          localObject = paramString;
          return localObject;
        }
        catch (PackageManager.NameNotFoundException paramArrayList)
        {
          continue;
        }
        paramArrayList = paramArrayList;
        paramString = null;
      }
    } while (!b.a);
    paramArrayList.printStackTrace();
    return paramString;
  }
  
  String a(Intent paramIntent)
  {
    String str = "";
    ResolveInfo localResolveInfo = this.a.getPackageManager().resolveActivity(paramIntent, 65536);
    paramIntent = str;
    if (localResolveInfo != null) {
      paramIntent = localResolveInfo.activityInfo.packageName;
    }
    return paramIntent;
  }
  
  public String a(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      String str = localBufferedReader.readLine();
      if (str == null) {
        break;
      }
      localStringBuilder.append(str).append("\n");
    }
    paramInputStream.close();
    return localStringBuilder.toString();
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", paramString1, null));
    if (!TextUtils.isEmpty(paramString2)) {
      paramString1.putExtra("android.intent.extra.SUBJECT", paramString2);
    }
    if (!TextUtils.isEmpty(paramString3)) {
      paramString1.putExtra("android.intent.extra.TEXT", paramString3);
    }
    paramActivity.startActivity(Intent.createChooser(paramString1, ""));
  }
  
  public void a(Activity paramActivity, boolean paramBoolean1, boolean paramBoolean2)
  {
    Intent localIntent = new Intent(paramActivity, StatusActivity.class);
    if (paramBoolean1) {
      localIntent = new Intent(paramActivity, ParentStatusActivity.class);
    }
    localIntent.setFlags(268435456);
    localIntent.putExtra(StatusActivity.p, true);
    PasswordRequestActivity.b(paramBoolean2);
    paramActivity.startActivity(localIntent);
    paramActivity.finish();
  }
  
  public void a(IBinder paramIBinder)
  {
    ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(paramIBinder, 0);
  }
  
  public void a(File paramFile1, File paramFile2)
  {
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramFile1.read(arrayOfByte);
      if (i <= 0) {
        break;
      }
      paramFile2.write(arrayOfByte, 0, i);
    }
    paramFile1.close();
    paramFile2.close();
  }
  
  public boolean a(CharSequence paramCharSequence)
  {
    return Patterns.EMAIL_ADDRESS.matcher(paramCharSequence).matches();
  }
  
  public boolean a(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    boolean bool = false;
    File localFile = this.a.getFilesDir();
    if (localFile.exists()) {
      bool = a(localFile, paramArrayOfString1, paramArrayOfString2);
    }
    return bool;
  }
  
  HashSet<String> b(Intent paramIntent)
  {
    Object localObject = this.a.getPackageManager().queryIntentActivities(paramIntent, 65536);
    paramIntent = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramIntent.add(((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName);
    }
    return paramIntent;
  }
  
  public int e(String paramString)
  {
    return this.a.getPackageManager().getPackageInfo(paramString, 0).versionCode;
  }
  
  public void f(String paramString)
  {
    this.h = paramString;
  }
  
  public Drawable g(String paramString)
  {
    PackageManager localPackageManager = this.a.getPackageManager();
    try
    {
      paramString = localPackageManager.getApplicationIcon(paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public boolean h(String paramString)
  {
    if (QustodioApp.c().getPackageName().equals(paramString))
    {
      if (QustodioService.a) {
        return i(paramString);
      }
      return false;
    }
    return i(paramString);
  }
  
  public boolean i(String paramString)
  {
    if (this.a != null) {
      try
      {
        Object localObject = (ActivityManager)this.a.getSystemService("activity");
        if (localObject != null)
        {
          localObject = ((ActivityManager)localObject).getRunningServices(Integer.MAX_VALUE);
          if ((localObject != null) && (((List)localObject).size() > 0))
          {
            localObject = ((List)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject).next();
              if ((localRunningServiceInfo != null) && (localRunningServiceInfo.service != null))
              {
                boolean bool = paramString.equals(localRunningServiceInfo.service.getPackageName());
                if (bool) {
                  return true;
                }
              }
            }
          }
        }
      }
      catch (Exception paramString) {}
    }
    return false;
  }
  
  public ApplicationInfo j(String paramString)
  {
    try
    {
      paramString = this.a.getPackageManager().getApplicationInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public String k(String paramString)
  {
    ApplicationInfo localApplicationInfo = j(paramString);
    if (localApplicationInfo != null) {
      paramString = this.a.getPackageManager().getApplicationLabel(localApplicationInfo);
    }
    for (;;)
    {
      return (String)paramString;
    }
  }
  
  public boolean l(String paramString)
  {
    return a(this.a, paramString);
  }
  
  protected void m(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setData(Uri.parse(paramString));
    if ((!ab.a(this.a, localIntent)) && (this.a != null)) {
      Toast.makeText(this.a, paramString, 1).show();
    }
  }
  
  public void n(String paramString)
  {
    if (this.a != null)
    {
      String str = a.a(this.a, paramString);
      Intent localIntent = new Intent("android.intent.action.VIEW", a.a(this.a, paramString, str));
      localIntent.setFlags(268435456);
      if (!ab.a(this.a, localIntent))
      {
        if (!str.contentEquals("com.amazon.venezia")) {
          break label90;
        }
        m("http://www.amazon.com/gp/mas/dl/android?p=" + paramString);
      }
    }
    return;
    label90:
    m("https://play.google.com/store/apps/details?id=" + paramString);
  }
  
  public void o(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
    localIntent.setFlags(268435456);
    if (!ab.a(this.a, localIntent)) {
      n(paramString);
    }
  }
  
  public String p(String paramString)
  {
    return a(new FileInputStream(new File(paramString)));
  }
  
  public boolean p()
  {
    if (this.a == null) {}
    do
    {
      return false;
      localObject = (ConnectivityManager)this.a.getSystemService("connectivity");
    } while (localObject == null);
    Object localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if ((localObject != null) && (((NetworkInfo)localObject).isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public HashSet<String> q()
  {
    Intent localIntent;
    if (this.b == null)
    {
      localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.HOME");
      String str = a(localIntent);
      if ((TextUtils.isEmpty(str)) || (str.equalsIgnoreCase("android"))) {
        break label80;
      }
    }
    label80:
    for (this.b = new HashSet(Arrays.asList(new String[] { a(localIntent) }));; this.b = b(localIntent)) {
      return this.b;
    }
  }
  
  public HashSet<String> r()
  {
    Object localObject;
    String str;
    if (this.c == null)
    {
      localObject = new Intent("android.intent.action.CALL");
      ((Intent)localObject).setData(Uri.parse("tel:1234"));
      str = a((Intent)localObject);
      if ((TextUtils.isEmpty(str)) || (str.equalsIgnoreCase("android"))) {
        break label170;
      }
      this.c = new HashSet(Arrays.asList(new String[] { a((Intent)localObject) }));
      ((Intent)localObject).setAction("android.intent.action.DIAL");
      str = a((Intent)localObject);
      if ((TextUtils.isEmpty(str)) || (str.equalsIgnoreCase("android"))) {
        break label194;
      }
      if (this.c != null) {
        break label182;
      }
      this.c = new HashSet(Arrays.asList(new String[] { a((Intent)localObject) }));
    }
    for (;;)
    {
      localObject = this.c;
      QustodioApp.c().e().d();
      ((HashSet)localObject).add("com.android.phone");
      return this.c;
      label170:
      this.c = b((Intent)localObject);
      break;
      label182:
      this.c.add(str);
      continue;
      label194:
      if (this.c == null) {
        this.c = b((Intent)localObject);
      } else {
        this.c.addAll(b((Intent)localObject));
      }
    }
  }
  
  public HashSet<String> s()
  {
    if (this.f == null)
    {
      this.f = new HashSet();
      Iterator localIterator = this.a.getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (!a(localApplicationInfo.packageName)) {
          this.f.add(localApplicationInfo.packageName);
        }
      }
    }
    return this.f;
  }
  
  @SuppressLint({"InlinedApi", "NewApi", "WrongConstant"})
  public String t()
  {
    label88:
    for (;;)
    {
      try
      {
        Object localObject = (UsageStatsManager)this.a.getSystemService("usagestats");
        if (localObject != null)
        {
          UsageEvents localUsageEvents = ((UsageStatsManager)localObject).queryEvents(System.currentTimeMillis() - 10000L, System.currentTimeMillis());
          if (localUsageEvents != null)
          {
            localObject = new UsageEvents.Event();
            if (localUsageEvents.hasNextEvent())
            {
              UsageEvents.Event localEvent = new UsageEvents.Event();
              localUsageEvents.getNextEvent(localEvent);
              if (localEvent.getEventType() != 1) {
                break label88;
              }
              localObject = localEvent;
              break label88;
            }
            localObject = ((UsageEvents.Event)localObject).getClassName();
            return localObject;
          }
        }
      }
      catch (Exception localException) {}
      return null;
    }
  }
  
  public String u()
  {
    try
    {
      String str = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.a.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public int v()
  {
    Object localObject2 = ((ActivityManager)this.a.getSystemService("activity")).getRunningAppProcesses();
    int[] arrayOfInt = new int[7];
    int[] tmp25_23 = arrayOfInt;
    tmp25_23[0] = 'È';
    int[] tmp31_25 = tmp25_23;
    tmp31_25[1] = '';
    int[] tmp37_31 = tmp31_25;
    tmp37_31[2] = 100;
    int[] tmp42_37 = tmp37_31;
    tmp42_37[3] = 'Ɛ';
    int[] tmp48_42 = tmp42_37;
    tmp48_42[4] = 'Ĭ';
    int[] tmp54_48 = tmp48_42;
    tmp54_48[5] = 'Ϩ';
    int[] tmp60_54 = tmp54_48;
    tmp60_54[6] = 'Ǵ';
    tmp60_54;
    int i = arrayOfInt.length;
    Object localObject1;
    int j;
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    if ((localObject2 != null) && (((List)localObject2).size() > 0))
    {
      localObject1 = (ActivityManager.RunningAppProcessInfo)((List)localObject2).get(0);
      Iterator localIterator = ((List)localObject2).iterator();
      localObject2 = localObject1;
      j = i;
      if (localIterator.hasNext())
      {
        localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (i != 0) {
          break label225;
        }
        j = i;
        localObject2 = localObject1;
      }
    }
    for (;;)
    {
      if (localObject2 != null)
      {
        i = q.a(((ActivityManager.RunningAppProcessInfo)localObject2).uid);
        if (com.qustodio.qustodioapp.a.a(false)) {
          d.debug(String.format("PRIO %d process found: %s pid: %d uid: %d", new Object[] { Integer.valueOf(j), ((ActivityManager.RunningAppProcessInfo)localObject2).processName, Integer.valueOf(((ActivityManager.RunningAppProcessInfo)localObject2).pid), Integer.valueOf(((ActivityManager.RunningAppProcessInfo)localObject2).uid) }));
        }
        return i;
        label225:
        j = 0;
        int k = i;
        for (localObject2 = localObject1;; localObject2 = localObject1)
        {
          localObject1 = localObject2;
          i = k;
          if (j >= k) {
            break;
          }
          localObject1 = localObject2;
          if (localRunningAppProcessInfo.importance == arrayOfInt[j])
          {
            localObject1 = localRunningAppProcessInfo;
            k = j;
          }
          j += 1;
        }
      }
      return -1;
      localObject2 = null;
      j = i;
    }
  }
  
  public String w()
  {
    Object localObject;
    if (Build.VERSION.SDK_INT < 21) {
      localObject = u();
    }
    String str;
    do
    {
      return localObject;
      if (this.g == null) {
        this.g = Boolean.valueOf(com.qustodio.qustodioapp.a.c(this.a));
      }
      if (!this.g.booleanValue())
      {
        if (!TextUtils.isEmpty(this.h)) {
          return this.h;
        }
        return a();
      }
      str = f(this.a);
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return a();
  }
  
  public void x()
  {
    QustodioApp.c().C();
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    ((Intent)localObject).setFlags(268468224);
    boolean bool = ab.a(this.a, (Intent)localObject);
    if (bool) {}
    for (localObject = "Success";; localObject = "Fail")
    {
      com.qustodio.qustodioapp.n.b.a("Android-app-configuration", "home_intent", (String)localObject);
      if ((!bool) && (com.qustodio.qustodioapp.a.a(false))) {
        d.error("startActivity_noRaise isIntentAvailable");
      }
      return;
    }
  }
  
  public boolean y()
  {
    boolean bool2 = false;
    String str = Environment.getExternalStorageState();
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(str))
    {
      bool1 = bool2;
      if (str.equals("mounted")) {
        bool1 = true;
      }
    }
    return bool1;
  }
}
