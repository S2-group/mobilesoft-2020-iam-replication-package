package com.huami.watch.companion.notification;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.huami.watch.companion.usersettings.UserSettings;
import com.huami.watch.companion.usersettings.UserSettingsManager;
import com.huami.watch.util.Box;
import com.huami.watch.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationManager
{
  public static final boolean ENABLE = true;
  public static final String SELF_PACKAGE_NAME = "com.huami.watch.hmwatchmanager";
  public static final String TAG = "Noti-Manager";
  private static NotificationManager a;
  private final List<NotificationApp> b;
  private Context c;
  private final AtomicBoolean d;
  private final AtomicBoolean e;
  private final AtomicBoolean f;
  private NotificationManager.NotificationSmartFilter g;
  private NotificationManager.NotificationKeywordFilter h;
  
  private NotificationManager(Context paramContext)
  {
    this.c = paramContext;
    this.b = new ArrayList();
    this.d = new AtomicBoolean();
    this.e = new AtomicBoolean();
    this.f = new AtomicBoolean();
    this.g = NotificationManager.NotificationSmartFilter.getFilter(paramContext);
    this.h = NotificationManager.NotificationKeywordFilter.getFilter(paramContext);
  }
  
  private void a()
  {
    b();
    d();
    preloadIsWatchNoDisturbON();
    this.g.init();
    this.h.init();
  }
  
  private void a(List<NotificationApp> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0)) {
      try
      {
        Object localObject1 = new JSONArray();
        Object localObject2 = paramList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          NotificationApp localNotificationApp = (NotificationApp)((Iterator)localObject2).next();
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("Pkg", localNotificationApp.packageName);
          localJSONObject.put("ON", localNotificationApp.on);
          ((JSONArray)localObject1).put(localJSONObject);
        }
        localObject1 = ((JSONArray)localObject1).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Save Apps : ");
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(", Count : ");
        ((StringBuilder)localObject2).append(paramList.size());
        Log.d("Noti-Manager", ((StringBuilder)localObject2).toString(), new Object[0]);
        UserSettings.putString(this.c.getContentResolver(), "huami.watch.companion.phone.notification.blacklist", (String)localObject1);
        return;
      }
      catch (JSONException paramList)
      {
        paramList.printStackTrace();
        return;
      }
    }
    Log.d("Noti-Manager", "Save Apps : [], Count : 0", new Object[0]);
    UserSettings.putString(this.c.getContentResolver(), "huami.watch.companion.phone.notification.blacklist", "");
  }
  
  private void a(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Save IsUserON : ");
    localStringBuilder.append(paramBoolean);
    Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    UserSettings.putBoolean(this.c.getContentResolver(), "huami.watch.companion.phone.notification.useron", paramBoolean);
  }
  
  private boolean a(String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramBoolean;
    }
    try
    {
      paramString = new JSONObject(paramString).optString("s.nodisturb");
      if (!TextUtils.isEmpty(paramString))
      {
        boolean bool = Boolean.valueOf(paramString).booleanValue();
        try
        {
          paramString = new StringBuilder();
          paramString.append("Read Is WatchNoDisturb ON : ");
          paramString.append(bool);
          Log.d("Noti-Manager", paramString.toString(), new Object[0]);
          return bool;
        }
        catch (Exception paramString)
        {
          paramBoolean = bool;
        }
      }
      else
      {
        return paramBoolean;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      Log.w("Noti-Manager", "Parse WatchNoDisturb Err!!", paramString, new Object[0]);
    }
    return paramBoolean;
  }
  
  private void b()
  {
    synchronized (this.b)
    {
      this.b.clear();
      this.b.addAll(c());
      return;
    }
  }
  
  private void b(boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Save IsScreenONIgnore : ");
    ((StringBuilder)localObject).append(paramBoolean);
    Log.d("Noti-Manager", ((StringBuilder)localObject).toString(), new Object[0]);
    localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("IsScreenONIgnore", paramBoolean);
      UserSettings.putString(this.c.getContentResolver(), "huami.watch.companion.phone.notification", ((JSONObject)localObject).toString());
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }
  
  private List<NotificationApp> c()
  {
    ArrayList localArrayList = new ArrayList();
    String str2 = UserSettings.getString(this.c.getContentResolver(), "huami.watch.companion.phone.notification.blacklist");
    if (!TextUtils.isEmpty(str2)) {
      try
      {
        JSONArray localJSONArray = new JSONArray(str2);
        int i = 0;
        while (i < localJSONArray.length())
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          NotificationApp localNotificationApp = new NotificationApp();
          String str1 = localJSONObject.optString("AppPkg");
          boolean bool;
          if (TextUtils.isEmpty(str1))
          {
            str1 = localJSONObject.getString("Pkg");
            bool = localJSONObject.getBoolean("ON");
          }
          else
          {
            bool = localJSONObject.optBoolean("AppOn", false);
          }
          localNotificationApp.packageName = str1;
          localNotificationApp.on = bool;
          localArrayList.add(localNotificationApp);
          i += 1;
        }
        localStringBuilder = new StringBuilder();
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    StringBuilder localStringBuilder;
    localStringBuilder.append("Read Saved Apps : ");
    localStringBuilder.append(str2);
    localStringBuilder.append(", Count : ");
    localStringBuilder.append(localArrayList.size());
    Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    return localArrayList;
  }
  
  private void d()
  {
    this.d.set(e());
    f();
  }
  
  private boolean e()
  {
    boolean bool = UserSettings.getBoolean(this.c.getContentResolver(), "huami.watch.companion.phone.notification.useron", true);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Read IsUserON : ");
    localStringBuilder.append(bool);
    Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    return bool;
  }
  
  private void f()
  {
    Object localObject = UserSettings.get(this.c.getContentResolver(), "huami.watch.companion.phone.notification");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Read Settings : ");
    localStringBuilder.append((String)localObject);
    Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      try
      {
        localObject = new JSONObject((String)localObject);
        this.e.set(((JSONObject)localObject).optBoolean("IsScreenONIgnore", false));
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
    }
    this.e.set(false);
  }
  
  public static List<ApplicationInfo> getInstalledPackages(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledApplications(paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.w("Noti-Manager", "GetInstalledApplications Error!!", paramContext, new Object[0]);
    }
    return null;
  }
  
  /* Error */
  public static List<ApplicationInfo> getInstalledPackagesFallback(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 295	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: new 42	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 43	java/util/ArrayList:<init>	()V
    //   12: astore 5
    //   14: aconst_null
    //   15: astore 4
    //   17: aconst_null
    //   18: astore_2
    //   19: aload_2
    //   20: astore_0
    //   21: invokestatic 313	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   24: ldc_w 315
    //   27: invokevirtual 319	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   30: astore 6
    //   32: aload_2
    //   33: astore_0
    //   34: new 321	java/io/BufferedReader
    //   37: dup
    //   38: new 323	java/io/InputStreamReader
    //   41: dup
    //   42: aload 6
    //   44: invokevirtual 329	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   47: invokespecial 332	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   50: invokespecial 335	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   53: astore_2
    //   54: aload_2
    //   55: invokevirtual 338	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   58: astore_0
    //   59: aload_0
    //   60: ifnull +31 -> 91
    //   63: aload 5
    //   65: aload_3
    //   66: aload_0
    //   67: aload_0
    //   68: bipush 58
    //   70: invokevirtual 344	java/lang/String:indexOf	(I)I
    //   73: iconst_1
    //   74: iadd
    //   75: invokevirtual 348	java/lang/String:substring	(I)Ljava/lang/String;
    //   78: iload_1
    //   79: invokevirtual 352	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   82: invokeinterface 270 2 0
    //   87: pop
    //   88: goto -34 -> 54
    //   91: aload 6
    //   93: invokevirtual 355	java/lang/Process:waitFor	()I
    //   96: pop
    //   97: aload_2
    //   98: invokevirtual 358	java/io/BufferedReader:close	()V
    //   101: aload 5
    //   103: areturn
    //   104: astore_3
    //   105: aload_2
    //   106: astore_0
    //   107: aload_3
    //   108: astore_2
    //   109: goto +40 -> 149
    //   112: astore_3
    //   113: goto +11 -> 124
    //   116: astore_2
    //   117: goto +32 -> 149
    //   120: astore_3
    //   121: aload 4
    //   123: astore_2
    //   124: aload_2
    //   125: astore_0
    //   126: aload_3
    //   127: invokevirtual 217	java/lang/Exception:printStackTrace	()V
    //   130: aload_2
    //   131: ifnull +15 -> 146
    //   134: aload_2
    //   135: invokevirtual 358	java/io/BufferedReader:close	()V
    //   138: aload 5
    //   140: areturn
    //   141: astore_0
    //   142: aload_0
    //   143: invokevirtual 359	java/io/IOException:printStackTrace	()V
    //   146: aload 5
    //   148: areturn
    //   149: aload_0
    //   150: ifnull +15 -> 165
    //   153: aload_0
    //   154: invokevirtual 358	java/io/BufferedReader:close	()V
    //   157: goto +8 -> 165
    //   160: astore_0
    //   161: aload_0
    //   162: invokevirtual 359	java/io/IOException:printStackTrace	()V
    //   165: aload_2
    //   166: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	167	0	paramContext	Context
    //   0	167	1	paramInt	int
    //   18	91	2	localObject1	Object
    //   116	1	2	localObject2	Object
    //   123	43	2	localObject3	Object
    //   4	62	3	localPackageManager	PackageManager
    //   104	4	3	localObject4	Object
    //   112	1	3	localException1	Exception
    //   120	7	3	localException2	Exception
    //   15	107	4	localObject5	Object
    //   12	135	5	localArrayList	ArrayList
    //   30	62	6	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   54	59	104	finally
    //   63	88	104	finally
    //   91	97	104	finally
    //   54	59	112	java/lang/Exception
    //   63	88	112	java/lang/Exception
    //   91	97	112	java/lang/Exception
    //   21	32	116	finally
    //   34	54	116	finally
    //   126	130	116	finally
    //   21	32	120	java/lang/Exception
    //   34	54	120	java/lang/Exception
    //   97	101	141	java/io/IOException
    //   134	138	141	java/io/IOException
    //   153	157	160	java/io/IOException
  }
  
  public static NotificationManager getManager(Context paramContext)
  {
    init(paramContext);
    return a;
  }
  
  public static void init(Context paramContext)
  {
    if (a == null)
    {
      a = new NotificationManager(paramContext.getApplicationContext());
      a.a();
    }
  }
  
  public void addAllApps(List<NotificationApp> paramList)
  {
    if (paramList != null)
    {
      if (paramList.size() == 0) {
        return;
      }
      synchronized (this.b)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          NotificationApp localNotificationApp1 = (NotificationApp)paramList.next();
          NotificationApp localNotificationApp2 = findApp(localNotificationApp1.packageName);
          if (localNotificationApp2 != null)
          {
            if (localNotificationApp2.on != localNotificationApp1.on) {
              this.b.remove(localNotificationApp2);
            }
          }
          else {
            this.b.add(localNotificationApp1);
          }
        }
        a(this.b);
        UserSettingsManager.getManager(this.c).syncAllToCloudAsync();
        return;
      }
    }
  }
  
  public void addApp(NotificationApp paramNotificationApp)
  {
    synchronized (this.b)
    {
      NotificationApp localNotificationApp = findApp(paramNotificationApp.packageName);
      if (localNotificationApp != null)
      {
        if (localNotificationApp.on == paramNotificationApp.on) {
          return;
        }
        this.b.remove(localNotificationApp);
      }
      this.b.add(paramNotificationApp);
      a(this.b);
      UserSettingsManager.getManager(this.c).syncAllToCloudAsync();
      return;
    }
  }
  
  public void addKeyword(String paramString)
  {
    this.h.addKeyword(paramString);
  }
  
  public NotificationApp findApp(String paramString)
  {
    synchronized (this.b)
    {
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext())
      {
        NotificationApp localNotificationApp = (NotificationApp)localIterator.next();
        if (localNotificationApp.packageName.equals(paramString)) {
          return localNotificationApp;
        }
      }
      return null;
    }
  }
  
  public List<NotificationApp> getAllApps()
  {
    ArrayList localArrayList = new ArrayList();
    synchronized (this.b)
    {
      localArrayList.addAll(this.b);
      return localArrayList;
    }
  }
  
  @NonNull
  public List<String> getAllKeywords()
  {
    return this.h.getKeywords();
  }
  
  public NotificationApp getApp(int paramInt)
  {
    List localList = this.b;
    if (paramInt >= 0) {}
    for (;;)
    {
      try
      {
        if (paramInt >= this.b.size()) {
          break label53;
        }
        localNotificationApp = (NotificationApp)this.b.get(paramInt);
        return localNotificationApp;
      }
      finally
      {
        NotificationApp localNotificationApp;
        continue;
      }
      throw localNotificationApp;
      label53:
      Object localObject2 = null;
    }
  }
  
  public int getAppsCount()
  {
    synchronized (this.b)
    {
      int i = this.b.size();
      return i;
    }
  }
  
  public NotificationManager.NotificationSmartFilter getSmartFilter()
  {
    return this.g;
  }
  
  public boolean hasApp(String paramString)
  {
    synchronized (this.b)
    {
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext()) {
        if (((NotificationApp)localIterator.next()).packageName.equals(paramString)) {
          return true;
        }
      }
      return false;
    }
  }
  
  public boolean isAppFiltered(String paramString, PackageManager paramPackageManager, boolean paramBoolean)
  {
    if ("com.huami.watch.hmwatchmanager".equals(paramString))
    {
      bool1 = isInUserBlacklist(paramString);
      if (paramBoolean)
      {
        paramString = new StringBuilder();
        paramString.append("Self isInUserBlacklist : ");
        paramString.append(bool1);
        Log.d("Noti-Manager", paramString.toString(), new Object[0]);
      }
      return bool1;
    }
    boolean bool1 = isInWhitelist(paramString, paramBoolean);
    boolean bool2 = isInUserBlacklist(paramString);
    if (paramBoolean)
    {
      paramPackageManager = new StringBuilder();
      paramPackageManager.append("<");
      paramPackageManager.append(paramString);
      paramPackageManager.append("> isInWhitelist : ");
      paramPackageManager.append(bool1);
      paramPackageManager.append(", isInUserBlacklist : ");
      paramPackageManager.append(bool2);
      Log.d("Noti-Manager", paramPackageManager.toString(), new Object[0]);
    }
    return (!bool1) || (bool2);
  }
  
  public boolean isDelKeywordDialogDisplayed()
  {
    return Box.getDelNotiKeyWordDialogDisplayed();
  }
  
  public boolean isInBlacklist(String paramString, boolean paramBoolean)
  {
    boolean bool1 = isInUserBlacklist(paramString);
    boolean bool2 = isInSmartFilterBlacklist(paramString);
    if (paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("<");
      localStringBuilder.append(paramString);
      localStringBuilder.append("> isInUserBlacklist : ");
      localStringBuilder.append(bool1);
      localStringBuilder.append(", isSmartFilterON : ");
      localStringBuilder.append(isSmartFilterON());
      localStringBuilder.append(", isInSmartFilterBlacklist : ");
      localStringBuilder.append(bool2);
      Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    }
    return bool1 | bool2;
  }
  
  public boolean isInSmartFilterBlacklist(String paramString)
  {
    return this.g.hasApp(paramString) & isSmartFilterON();
  }
  
  public boolean isInSmartFilterWhitelist(String paramString)
  {
    return this.g.hasAppInWhitelist(paramString) & isSmartFilterON();
  }
  
  public boolean isInUserBlacklist(String paramString)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      NotificationApp localNotificationApp = (NotificationApp)localIterator.next();
      if ((localNotificationApp.packageName.equals(paramString)) && (!localNotificationApp.on)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isInUserWhitelist(String paramString)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      NotificationApp localNotificationApp = (NotificationApp)localIterator.next();
      if ((localNotificationApp.packageName.equals(paramString)) && (localNotificationApp.on)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isInWhitelist(String paramString, boolean paramBoolean)
  {
    boolean bool1 = isInUserWhitelist(paramString);
    boolean bool2 = isInSmartFilterWhitelist(paramString);
    if (paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("<");
      localStringBuilder.append(paramString);
      localStringBuilder.append("> isInUserWhitelist : ");
      localStringBuilder.append(bool1);
      localStringBuilder.append(", isSmartFilterON : ");
      localStringBuilder.append(isSmartFilterON());
      localStringBuilder.append(", isInSmartFilterWhitelist : ");
      localStringBuilder.append(bool2);
      Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    }
    return bool1 | bool2;
  }
  
  public boolean isKeywordFilter(String paramString, String[] paramArrayOfString)
  {
    return this.h.isFiltered(paramString, paramArrayOfString);
  }
  
  public boolean isKeywordFilterON()
  {
    return this.h.isKeywordFilterON();
  }
  
  public boolean isNLSAvailable(Context paramContext)
  {
    return (isSystemNLSPermissionEnabled(paramContext)) && (isUserON());
  }
  
  public boolean isScreenONIgnore()
  {
    boolean bool = this.e.get();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("IsScreenONIgnore : ");
    localStringBuilder.append(bool);
    Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    return bool;
  }
  
  public boolean isSmartFilterON()
  {
    return this.g.isSmartFilterON();
  }
  
  public boolean isSystemNLSPermissionEnabled(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    boolean bool;
    if ((!TextUtils.isEmpty(str)) && (str.contains(paramContext.getApplicationContext().getPackageName()))) {
      bool = true;
    } else {
      bool = false;
    }
    paramContext = new StringBuilder();
    paramContext.append("IsSystemNLSPermissionEnabled : ");
    paramContext.append(bool);
    Log.d("Noti-Manager", paramContext.toString(), new Object[0]);
    return bool;
  }
  
  public boolean isUserON()
  {
    return this.d.get();
  }
  
  public boolean isWatchNoDisturbON()
  {
    boolean bool = this.f.get();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Is WatchNoDisturb ON : ");
    localStringBuilder.append(bool);
    Log.d("Noti-Manager", localStringBuilder.toString(), new Object[0]);
    return bool;
  }
  
  public void preloadIsWatchNoDisturbON()
  {
    preloadIsWatchNoDisturbON(UserSettings.get(this.c.getContentResolver(), "huami.watch.wearsettings.config"));
  }
  
  public void preloadIsWatchNoDisturbON(String paramString)
  {
    this.f.set(a(paramString, this.f.get()));
  }
  
  public void removeAllApps(List<String> paramList)
  {
    synchronized (this.b)
    {
      ArrayList localArrayList = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        NotificationApp localNotificationApp = findApp((String)paramList.next());
        if (localNotificationApp != null) {
          localArrayList.add(localNotificationApp);
        }
      }
      if (localArrayList.size() == 0) {
        return;
      }
      this.b.removeAll(localArrayList);
      a(this.b);
      UserSettingsManager.getManager(this.c).syncAllToCloudAsync();
      return;
    }
  }
  
  public void removeApp(@NonNull NotificationApp paramNotificationApp)
  {
    removeApp(paramNotificationApp.packageName);
  }
  
  public void removeApp(String paramString)
  {
    synchronized (this.b)
    {
      paramString = findApp(paramString);
      if (paramString == null) {
        return;
      }
      this.b.remove(paramString);
      a(this.b);
      UserSettingsManager.getManager(this.c).syncAllToCloudAsync();
      return;
    }
  }
  
  public void removeKeyword(String paramString)
  {
    this.h.removeKeyword(paramString);
  }
  
  public void setDelKeywordDialogDisplayed(boolean paramBoolean)
  {
    Box.putDelNotiKeyWordDialogDisplayed(paramBoolean);
  }
  
  public void setKeywordFilterON(boolean paramBoolean)
  {
    this.h.setKeywordFilterON(paramBoolean);
  }
  
  public void setScreenOnIgnore(boolean paramBoolean)
  {
    this.e.set(paramBoolean);
    b(paramBoolean);
  }
  
  public void setSmartFilterON(boolean paramBoolean)
  {
    this.g.setSmartFilterON(paramBoolean);
  }
  
  public void setUserON(boolean paramBoolean)
  {
    this.d.set(paramBoolean);
    a(paramBoolean);
  }
  
  public void updateApp(NotificationApp paramNotificationApp)
  {
    synchronized (this.b)
    {
      NotificationApp localNotificationApp = findApp(paramNotificationApp.packageName);
      if (localNotificationApp == null) {
        return;
      }
      localNotificationApp.packageName = paramNotificationApp.packageName;
      localNotificationApp.on = paramNotificationApp.on;
      a(this.b);
      UserSettingsManager.getManager(this.c).syncAllToCloudAsync();
      return;
    }
  }
}
