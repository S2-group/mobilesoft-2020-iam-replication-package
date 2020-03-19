package com.oneaudience.sdk;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import com.oneaudience.sdk.b.b.b.a;
import com.oneaudience.sdk.b.b.b.b;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class m
{
  private static String a = m.class.getSimpleName();
  private static m h;
  private final int b = 10000;
  private Context c;
  private SharedPreferences d;
  private b e;
  private i f;
  private long g = 0L;
  
  private m(Context paramContext)
  {
    this.c = paramContext;
    this.d = paramContext.getSharedPreferences("oneaudience", 0);
    this.e = b.a(this.c);
    this.f = new i(this.c);
  }
  
  public static m a(Context paramContext)
  {
    try
    {
      if (h == null) {
        h = new m(paramContext);
      }
      paramContext = h;
      return paramContext;
    }
    finally {}
  }
  
  private void a(String paramString, Activity paramActivity)
  {
    try
    {
      if (this.d.getBoolean("eula_shown", false))
      {
        com.oneaudience.sdk.b.d.b(a, "Eula was showed already");
        return;
      }
      Bundle localBundle = new Bundle();
      localBundle.putString("appKey", paramString);
      new g(paramActivity).a(null, null, localBundle);
      return;
    }
    catch (Exception paramString)
    {
      com.oneaudience.sdk.b.d.b(a, "Show EULA Exception", paramString);
    }
  }
  
  /* Error */
  public static boolean a()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 91	com/oneaudience/sdk/m:h	Lcom/oneaudience/sdk/m;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +10 -> 18
    //   11: iconst_1
    //   12: istore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: iload_0
    //   17: ireturn
    //   18: iconst_0
    //   19: istore_0
    //   20: goto -7 -> 13
    //   23: astore_1
    //   24: ldc 2
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   12	8	0	bool	boolean
    //   6	2	1	localM	m
    //   23	5	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	7	23	finally
  }
  
  private void b(final String paramString)
  {
    try
    {
      if (!(this.c.getApplicationContext() instanceof Application)) {
        return;
      }
      Application localApplication = (Application)this.c.getApplicationContext();
      if (Build.VERSION.SDK_INT >= 14)
      {
        localApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
        {
          public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
          
          public void onActivityDestroyed(Activity paramAnonymousActivity) {}
          
          public void onActivityPaused(Activity paramAnonymousActivity) {}
          
          public void onActivityResumed(Activity paramAnonymousActivity)
          {
            if (m.a(m.this) != null) {
              m.a(m.this).edit().putLong("lastOpenDate", System.currentTimeMillis()).apply();
            }
          }
          
          public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
          
          public void onActivityStarted(Activity paramAnonymousActivity) {}
          
          public void onActivityStopped(Activity paramAnonymousActivity)
          {
            try
            {
              com.oneaudience.sdk.b.d.b(m.e(), "Activity stopped checking if request should be sent.");
              m.this.a(paramString, false);
              return;
            }
            finally
            {
              paramAnonymousActivity = finally;
              throw paramAnonymousActivity;
            }
          }
        });
        return;
      }
    }
    catch (Exception paramString)
    {
      com.oneaudience.sdk.b.d.b(a, "Exception Registering activity lifecycle", paramString);
    }
  }
  
  public void a(String paramString)
  {
    if ((this.c != null) && (this.d != null))
    {
      String str1 = g.a(this.c, this.d);
      String str2 = this.d.getString("email_from_set_function", "");
      if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(paramString)) && (!str2.equalsIgnoreCase(paramString)))
      {
        this.d.edit().putString("email_from_set_function", paramString).commit();
        if (g.c(this.c, this.d))
        {
          paramString = new Bundle();
          paramString.putString("appKey", str1);
          paramString.putBoolean("opt_out", false);
          paramString.putBoolean("force_config", true);
          new c(null).a(null, null, paramString);
        }
        return;
      }
      Log.w(a, "Error occurred while saving email address: appKey or email is empty. or the email is not new");
      return;
    }
    Log.w(a, "Error occurred while saving email address. did you call init function first?");
  }
  
  public void a(String paramString, Context paramContext)
  {
    this.d.edit().putString("app_key", paramString).apply();
    if ((paramContext instanceof Activity))
    {
      Activity localActivity = (Activity)paramContext;
      boolean bool = this.d.getBoolean("eula_shown", false);
      if ((g.a(this.c, "showEula", false)) && (!bool)) {
        a(paramString, localActivity);
      }
      if ((localActivity != null) && (g.b(this.c, this.d)))
      {
        g.a(localActivity, q.a);
        this.d.edit().putBoolean("permission_request_shown", true).apply();
      }
    }
    this.d.edit().putLong("lastOpenDate", System.currentTimeMillis()).apply();
    if (((paramContext instanceof Activity)) || ((paramContext instanceof Application))) {
      b(paramString);
    }
    Log.i(a.class.getSimpleName(), "One Audience SDK Init Completed");
    a(paramString, false);
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    boolean bool3;
    do
    {
      boolean bool2;
      do
      {
        try
        {
          if ((this.g != 0L) && (System.currentTimeMillis() - this.g < 10000L))
          {
            com.oneaudience.sdk.b.d.b(a, "checkDeviceInfo ** preventing duplicate call **");
            return;
          }
          this.g = System.currentTimeMillis();
          com.oneaudience.sdk.b.d.b(a, "checkDeviceInfo ** setting timestamp **");
          bool2 = this.d.getBoolean("optout", false);
          bool3 = this.d.getBoolean("opt_out_reported", false);
          if ((bool2) && (!bool3))
          {
            bool1 = true;
            Bundle localBundle = new Bundle();
            localBundle.putString("appKey", paramString);
            localBundle.putBoolean("force_config", false);
            localBundle.putBoolean("opt_out", bool1);
            new e(null).a(null, null, localBundle);
            return;
          }
        }
        catch (Exception paramString)
        {
          com.oneaudience.sdk.b.d.b(a, "Send Configuration Exception", paramString);
          return;
        }
        bool1 = paramBoolean;
      } while (!bool2);
      boolean bool1 = paramBoolean;
    } while (!bool3);
    com.oneaudience.sdk.b.d.b(a, "Do nothing user opt out");
  }
  
  public void b()
  {
    String str = g.a(this.c, this.d);
    this.d.edit().putBoolean("optout", true).apply();
    if (!TextUtils.isEmpty(str))
    {
      a(str, true);
      return;
    }
    com.oneaudience.sdk.b.d.c(a, "Couldn't opt out from oneAudience");
  }
  
  public void c()
  {
    try
    {
      String str = g.a(this.c, this.d);
      SharedPreferences.Editor localEditor = this.d.edit();
      localEditor.putBoolean("eula", true);
      localEditor.putBoolean("eula_shown", true);
      boolean bool = localEditor.commit();
      com.oneaudience.sdk.b.d.c(a, "Eula accepted save status: %b", new Object[] { Boolean.valueOf(bool) });
      if (!TextUtils.isEmpty(str)) {
        a(str, false);
      }
      return;
    }
    catch (Exception localException)
    {
      com.oneaudience.sdk.b.d.b(a, "EULA Accepted Exception", localException);
    }
  }
  
  public void d()
  {
    try
    {
      SharedPreferences.Editor localEditor = this.d.edit();
      localEditor.putBoolean("eula_shown", true);
      boolean bool = localEditor.commit();
      com.oneaudience.sdk.b.d.c(a, "Eula declined save status: %b", new Object[] { Boolean.valueOf(bool) });
      return;
    }
    catch (Exception localException)
    {
      com.oneaudience.sdk.b.d.b(a, "EULA Declined Exception", localException);
    }
  }
  
  private class a
    extends r<Void, Void, Void>
  {
    private a() {}
    
    protected Void a(Void... paramVarArgs)
    {
      paramVarArgs = g.d(m.c(m.this));
      if (!TextUtils.isEmpty(paramVarArgs)) {
        m.a(m.this).edit().putString("advertising_id", paramVarArgs).apply();
      }
      return null;
    }
  }
  
  private class b
    extends r<Void, Void, Boolean>
  {
    private String b;
    private boolean c;
    
    public b(String paramString, boolean paramBoolean)
    {
      this.b = paramString;
      this.c = paramBoolean;
    }
    
    private boolean c()
    {
      com.oneaudience.sdk.b.d.b(m.e(), "Populating Installed Apps to Registry");
      Object localObject3 = m.c(m.this).getPackageManager();
      Object localObject1 = ((PackageManager)localObject3).getInstalledApplications(128);
      TreeSet localTreeSet = new TreeSet(m.d(m.this).b());
      Iterator localIterator = ((List)localObject1).iterator();
      boolean bool = false;
      if (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((localApplicationInfo != null) && (!localApplicationInfo.packageName.equalsIgnoreCase("com.android.keyguard")))
        {
          if (localApplicationInfo != null) {}
          try
          {
            localObject1 = ((PackageManager)localObject3).getApplicationLabel(localApplicationInfo);
            label108:
            localObject1 = (String)localObject1;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              com.oneaudience.sdk.b.d.c(m.e(), "Couldn't get application package name");
              localObject2 = "NA";
            }
            if (localApplicationInfo.packageName == null) {
              break label220;
            }
            localTreeSet.remove(localApplicationInfo.packageName);
          }
          if ((localApplicationInfo.packageName != null) && (!m.d(m.this).b(localApplicationInfo.packageName)))
          {
            com.oneaudience.sdk.b.d.c(m.e(), "ADDING APP TO DICTIONARY: %s", new Object[] { localObject1 });
            m.d(m.this).a(localApplicationInfo.packageName, localObject1);
            bool = true;
          }
        }
        label220:
        for (;;)
        {
          break;
          localObject1 = "NA";
          break label108;
        }
      }
      Object localObject2 = localTreeSet.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        bool = m.d(m.this).a((String)localObject3);
        com.oneaudience.sdk.b.d.b("APP WAS REMOVED: %s", (String)localObject3);
        if (!bool) {
          com.oneaudience.sdk.b.d.d(m.e(), "Can't remove package: %s", new Object[] { localObject3 });
        }
        bool = true;
      }
      m.d(m.this).a();
      m.a(m.this).edit().putBoolean("apps_registry_first_save", true).commit();
      return bool;
    }
    
    private boolean d()
    {
      Object localObject1;
      if (g.b(m.c(m.this), "android.permission.GET_ACCOUNTS"))
      {
        localObject1 = AccountManager.get(m.c(m.this)).getAccounts();
        if (!com.oneaudience.sdk.b.b.c.a((Object[])localObject1)) {
          break label38;
        }
      }
      label38:
      Object localObject2;
      String str;
      do
      {
        return false;
        localObject2 = new HashSet(com.oneaudience.sdk.b.b.b.a(Arrays.asList((Object[])localObject1), new b.a()
        {
          public String a(Account paramAnonymousAccount)
          {
            return paramAnonymousAccount.name;
          }
        })).iterator();
        localObject1 = new StringBuilder();
        com.oneaudience.sdk.b.b.b.a((Iterator)localObject2, new b.b()
        {
          public Object a(String paramAnonymousString)
          {
            StringBuilder localStringBuilder;
            if (Patterns.EMAIL_ADDRESS.matcher(paramAnonymousString).matches())
            {
              localStringBuilder = this.a.append(paramAnonymousString);
              if (!this.b.hasNext()) {
                break label45;
              }
            }
            label45:
            for (paramAnonymousString = ",";; paramAnonymousString = "")
            {
              localStringBuilder.append(paramAnonymousString);
              return null;
            }
          }
        });
        localObject2 = m.a(m.this).getString("email_from_get_accounts", "");
        str = ((StringBuilder)localObject1).toString();
      } while ((TextUtils.isEmpty(str)) || (((String)localObject2).equalsIgnoreCase(str)));
      m.a(m.this).edit().putString("email_from_get_accounts", ((StringBuilder)localObject1).toString()).commit();
      return true;
    }
    
    protected Boolean a(Void... paramVarArgs)
    {
      boolean bool1 = c();
      boolean bool2 = d();
      if ((bool1) || (bool2)) {}
      for (bool1 = true;; bool1 = false) {
        return Boolean.valueOf(bool1);
      }
    }
    
    protected void a(Boolean paramBoolean)
    {
      super.a(paramBoolean);
      Bundle localBundle = new Bundle();
      localBundle.putString("appKey", this.b);
      localBundle.putBoolean("opt_out", this.c);
      if (paramBoolean.booleanValue())
      {
        com.oneaudience.sdk.b.d.b(m.e(), "Device info changed");
        localBundle.putBoolean("force_config", true);
      }
      for (;;)
      {
        new m.c(m.this, null).a(null, null, localBundle);
        return;
        com.oneaudience.sdk.b.d.b(m.e(), "Device Info didn't change");
        localBundle.putBoolean("force_config", false);
      }
    }
  }
  
  private final class c
    extends p
  {
    private final String b = m.e.class.getSimpleName();
    
    private c() {}
    
    public void a(Uri paramUri, String paramString, Bundle paramBundle)
    {
      if ((paramBundle == null) || (!paramBundle.containsKey("appKey")))
      {
        com.oneaudience.sdk.b.d.b(this.b, "App key is null");
        return;
      }
      paramUri = paramBundle.getString("appKey");
      boolean bool1 = paramBundle.getBoolean("force_config", false);
      boolean bool2 = paramBundle.getBoolean("opt_out", false);
      b(m.b(m.this), new m.d(m.this, paramUri, bool1, bool2), new Void[0]);
    }
  }
  
  private class d
    extends r<Void, Void, Void>
  {
    private String b;
    private boolean c;
    private boolean d;
    
    public d(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.b = paramString;
      this.c = paramBoolean1;
      this.d = paramBoolean2;
    }
    
    private int a(boolean paramBoolean)
    {
      com.oneaudience.sdk.b.d.b(m.e(), "Performing configuration sync... FETCHING");
      e localE = new e(paramBoolean);
      com.oneaudience.sdk.b.a.d localD = new d().a(localE.a(m.c(m.this), m.a(m.this), m.d(m.this), this.b));
      switch (localD.a)
      {
      default: 
        com.oneaudience.sdk.b.d.c(m.e(), "Performing configuration sync... FETCHING FAILED");
      }
      for (;;)
      {
        return localD.a;
        localE.a(m.c(m.this), m.a(m.this), localD);
        com.oneaudience.sdk.b.d.b(m.e(), "Performing configuration sync... FETCHING DONE");
        com.oneaudience.sdk.b.d.b(m.e(), "Performing configuration sync... FETCHING UP-TO DATE");
      }
    }
    
    protected Void a(Void... paramVarArgs)
    {
      int j = 0;
      com.oneaudience.sdk.b.d.b(m.e(), "Performing configuration sync...");
      long l1 = m.a(m.this).getLong("interval", 86400000L);
      long l2 = m.a(m.this).getLong("last_updated", 0L);
      paramVarArgs = m.a(m.this).getString("oneAudienceId", "");
      if ((paramVarArgs != null) && (!paramVarArgs.equals(""))) {}
      for (int i = 1; (!this.d) && (!this.c) && (i != 0) && ((l1 < 0L) || (l1 + l2 > System.currentTimeMillis())); i = 0)
      {
        com.oneaudience.sdk.b.d.b(m.e(), "Performing configuration sync... No Need For A Configuration");
        return null;
      }
      int k = a(this.d);
      if ((k == 100000) || (k == 100001))
      {
        i = 1;
        if (i == 0) {
          break label369;
        }
        paramVarArgs = m.a(m.this).edit();
        paramVarArgs.putLong("last_updated", System.currentTimeMillis());
        paramVarArgs.remove("events");
        if (this.d) {
          paramVarArgs.putBoolean("opt_out_reported", true);
        }
        paramVarArgs.apply();
        Log.i(a.class.getSimpleName(), "One Audience SDK Server OK");
        label228:
        if ((!this.d) || ((this.d) && (i == 0)))
        {
          if (i == 0)
          {
            i = j;
            if (k == 100004) {}
          }
          else
          {
            i = 1;
          }
          if (i == 0) {
            break label383;
          }
        }
      }
      label369:
      label383:
      for (l1 = m.a(m.this).getLong("interval", 86400000L);; l1 = 3600000L)
      {
        com.oneaudience.sdk.b.d.b(m.e(), "Status: " + String.valueOf(k));
        com.oneaudience.sdk.b.d.b(m.e(), "Interval for next config is: " + String.valueOf(l1));
        g.a(m.c(m.this), l1);
        com.oneaudience.sdk.b.d.b(m.e(), "Performing configuration sync... DONE");
        return null;
        i = 0;
        break;
        Log.w(a.class.getSimpleName(), "One Audience SDK Server Failed");
        break label228;
      }
    }
  }
  
  private final class e
    extends p
  {
    private final String b = e.class.getSimpleName();
    
    private e() {}
    
    public void a(Uri paramUri, String paramString, Bundle paramBundle)
    {
      if ((paramBundle == null) || (!paramBundle.containsKey("appKey")))
      {
        com.oneaudience.sdk.b.d.b(this.b, "App key is null");
        return;
      }
      paramUri = paramBundle.getString("appKey");
      boolean bool = paramBundle.getBoolean("opt_out", false);
      a(m.b(m.this), new m.a(m.this, null), new Void[0]);
      if (!g.c(m.c(m.this), m.a(m.this)))
      {
        paramString = new Bundle();
        paramString.putString("appKey", paramUri);
        paramString.putBoolean("opt_out", bool);
        paramString.putBoolean("force_config", false);
        new m.c(m.this, null).a(null, null, paramString);
        return;
      }
      a(m.b(m.this), new m.f(m.this, null), new Bundle[0]);
      b(m.b(m.this), new m.b(m.this, paramUri, bool), new Void[0]);
    }
  }
  
  private class f
    extends r<Bundle, Void, Void>
  {
    private f() {}
    
    protected Void a(Bundle... paramVarArgs)
    {
      paramVarArgs = com.oneaudience.sdk.b.a.a();
      Object localObject = m.a(m.this).getString("facebook_json", "");
      if ((paramVarArgs != null) && (TextUtils.isEmpty((CharSequence)localObject)))
      {
        localObject = new k();
        paramVarArgs = new d().a(((k)localObject).a(m.c(m.this), m.a(m.this), paramVarArgs));
        if (paramVarArgs != null) {
          ((k)localObject).a(m.c(m.this), m.a(m.this), paramVarArgs);
        }
      }
      return null;
    }
  }
  
  private final class g
    extends p
  {
    private Activity b;
    private final String c = g.class.getSimpleName();
    
    public g(Activity paramActivity)
    {
      this.b = paramActivity;
    }
    
    void a(Uri paramUri, String paramString, Bundle paramBundle)
    {
      if ((paramBundle == null) || (!paramBundle.containsKey("appKey")))
      {
        com.oneaudience.sdk.b.d.b(this.c, "App key is null");
        return;
      }
      paramUri = paramBundle.getString("appKey");
      b(m.b(m.this), new m.h(m.this, paramUri, this.b), new Void[0]);
    }
  }
  
  private class h
    extends r<Void, Void, Void>
  {
    String a;
    Activity b;
    
    public h(String paramString, Activity paramActivity)
    {
      this.a = paramString;
      this.b = paramActivity;
    }
    
    protected Void a(Void... paramVarArgs)
    {
      l.a(this.b, new i().a(this.b, m.a(m.this), this.a).b);
      return null;
    }
  }
  
  protected static class i
    implements s.a
  {
    protected Context a = null;
    
    public i(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public void a(r paramR, Exception paramException)
    {
      com.oneaudience.sdk.b.d.e(m.e(), "Error has occurred at task: %s\nerror:%s", new Object[] { paramR.getClass().getSimpleName(), paramException });
      if (this.a != null)
      {
        paramR = this.a.getSharedPreferences("oneaudience", 0);
        g.a(this.a, paramR, paramException);
      }
    }
  }
}
