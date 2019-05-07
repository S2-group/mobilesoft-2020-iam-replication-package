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
import com.oneaudience.sdk.b.b.b;
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

public class l
{
  private static String a = l.class.getSimpleName();
  private static l h;
  private final int b = 10000;
  private Context c;
  private SharedPreferences d;
  private a e;
  private i f;
  private long g = 0L;
  
  private l(Context paramContext)
  {
    this.c = paramContext;
    this.d = paramContext.getSharedPreferences("oneaudience", 0);
    this.e = a.a(this.c);
    this.f = new i(this.c);
  }
  
  public static l a(Context paramContext)
  {
    try
    {
      if (h == null) {
        h = new l(paramContext);
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
            if (l.a(l.this) != null) {
              l.a(l.this).edit().putLong("lastOpenDate", System.currentTimeMillis()).apply();
            }
          }
          
          public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
          
          public void onActivityStarted(Activity paramAnonymousActivity) {}
          
          public void onActivityStopped(Activity paramAnonymousActivity)
          {
            try
            {
              com.oneaudience.sdk.b.d.b(l.e(), "Activity stopped checking if request should be sent.");
              l.this.a(paramString, false);
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
  
  public void a(String paramString, Context paramContext)
  {
    this.d.edit().putString("app_key", paramString).apply();
    if ((paramContext instanceof Activity))
    {
      Activity localActivity = (Activity)paramContext;
      boolean bool = this.d.getBoolean("eula_shown", false);
      if ((f.a(this.c, "showEula", false)) && (!bool)) {
        a(paramString, localActivity);
      }
      if ((localActivity != null) && (f.b(this.c, this.d)))
      {
        f.a(localActivity, p.a);
        this.d.edit().putBoolean("permission_request_shown", true).apply();
      }
    }
    this.d.edit().putLong("lastOpenDate", System.currentTimeMillis()).apply();
    if (((paramContext instanceof Activity)) || ((paramContext instanceof Application))) {
      b(paramString);
    }
    Log.i(OneAudience.class.getSimpleName(), "One Audience SDK Init Completed");
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
  
  public void c()
  {
    try
    {
      String str = f.a(this.c, this.d);
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
    extends q<Void, Void, Void>
  {
    private a() {}
    
    protected Void a(Void... paramVarArgs)
    {
      paramVarArgs = f.d(l.c(l.this));
      if (!TextUtils.isEmpty(paramVarArgs)) {
        l.a(l.this).edit().putString("advertising_id", paramVarArgs).apply();
      }
      return null;
    }
  }
  
  private class b
    extends q<Void, Void, Boolean>
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
      com.oneaudience.sdk.b.d.b(l.e(), "Populating Installed Apps to Registry");
      Object localObject3 = l.c(l.this).getPackageManager();
      Object localObject1 = ((PackageManager)localObject3).getInstalledApplications(128);
      TreeSet localTreeSet = new TreeSet(l.d(l.this).b());
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
              com.oneaudience.sdk.b.d.c(l.e(), "Couldn't get application package name");
              localObject2 = "NA";
            }
            if (localApplicationInfo.packageName == null) {
              break label220;
            }
            localTreeSet.remove(localApplicationInfo.packageName);
          }
          if ((localApplicationInfo.packageName != null) && (!l.d(l.this).b(localApplicationInfo.packageName)))
          {
            com.oneaudience.sdk.b.d.c(l.e(), "ADDING APP TO DICTIONARY: %s", new Object[] { localObject1 });
            l.d(l.this).a(localApplicationInfo.packageName, localObject1);
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
        bool = l.d(l.this).a((String)localObject3);
        com.oneaudience.sdk.b.d.b("APP WAS REMOVED: %s", (String)localObject3);
        if (!bool) {
          com.oneaudience.sdk.b.d.d(l.e(), "Can't remove package: %s", new Object[] { localObject3 });
        }
        bool = true;
      }
      l.d(l.this).a();
      l.a(l.this).edit().putBoolean("apps_registry_first_save", true).commit();
      return bool;
    }
    
    private boolean d()
    {
      Object localObject1;
      if (f.b(l.c(l.this), "android.permission.GET_ACCOUNTS"))
      {
        localObject1 = AccountManager.get(l.c(l.this)).getAccounts();
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
        localObject2 = new HashSet(b.a(Arrays.asList((Object[])localObject1), new b.a()
        {
          public String a(Account paramAnonymousAccount)
          {
            return paramAnonymousAccount.name;
          }
        })).iterator();
        localObject1 = new StringBuilder();
        b.a((Iterator)localObject2, new b.b()
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
        localObject2 = l.a(l.this).getString("email_from_get_accounts", "");
        str = ((StringBuilder)localObject1).toString();
      } while ((TextUtils.isEmpty(str)) || (((String)localObject2).equalsIgnoreCase(str)));
      l.a(l.this).edit().putString("email_from_get_accounts", ((StringBuilder)localObject1).toString()).commit();
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
        com.oneaudience.sdk.b.d.b(l.e(), "Device info changed");
        localBundle.putBoolean("force_config", true);
      }
      for (;;)
      {
        new l.c(l.this, null).a(null, null, localBundle);
        return;
        com.oneaudience.sdk.b.d.b(l.e(), "Device Info didn't change");
        localBundle.putBoolean("force_config", false);
      }
    }
  }
  
  private final class c
    extends o
  {
    private final String b = l.e.class.getSimpleName();
    
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
      b(l.b(l.this), new l.d(l.this, paramUri, bool1, bool2), new Void[0]);
    }
  }
  
  private class d
    extends q<Void, Void, Void>
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
      com.oneaudience.sdk.b.d.b(l.e(), "Performing configuration sync... FETCHING");
      d localD = new d(paramBoolean);
      com.oneaudience.sdk.b.a.d localD1 = new c().a(localD.a(l.c(l.this), l.a(l.this), l.d(l.this), this.b));
      switch (localD1.a)
      {
      default: 
        com.oneaudience.sdk.b.d.c(l.e(), "Performing configuration sync... FETCHING FAILED");
      }
      for (;;)
      {
        return localD1.a;
        localD.a(l.c(l.this), l.a(l.this), localD1);
        com.oneaudience.sdk.b.d.b(l.e(), "Performing configuration sync... FETCHING DONE");
        com.oneaudience.sdk.b.d.b(l.e(), "Performing configuration sync... FETCHING UP-TO DATE");
      }
    }
    
    protected Void a(Void... paramVarArgs)
    {
      int j = 0;
      com.oneaudience.sdk.b.d.b(l.e(), "Performing configuration sync...");
      long l1 = l.a(l.this).getLong("interval", 86400000L);
      long l2 = l.a(l.this).getLong("last_updated", 0L);
      paramVarArgs = l.a(l.this).getString("oneAudienceId", "");
      if ((paramVarArgs != null) && (!paramVarArgs.equals(""))) {}
      for (int i = 1; (!this.d) && (!this.c) && (i != 0) && ((l1 < 0L) || (l1 + l2 > System.currentTimeMillis())); i = 0)
      {
        com.oneaudience.sdk.b.d.b(l.e(), "Performing configuration sync... No Need For A Configuration");
        return null;
      }
      int k = a(this.d);
      if ((k == 100000) || (k == 100001))
      {
        i = 1;
        if (i == 0) {
          break label369;
        }
        paramVarArgs = l.a(l.this).edit();
        paramVarArgs.putLong("last_updated", System.currentTimeMillis());
        paramVarArgs.remove("events");
        if (this.d) {
          paramVarArgs.putBoolean("opt_out_reported", true);
        }
        paramVarArgs.apply();
        Log.i(OneAudience.class.getSimpleName(), "One Audience SDK Server OK");
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
      for (l1 = l.a(l.this).getLong("interval", 86400000L);; l1 = 3600000L)
      {
        com.oneaudience.sdk.b.d.b(l.e(), "Status: " + String.valueOf(k));
        com.oneaudience.sdk.b.d.b(l.e(), "Interval for next config is: " + String.valueOf(l1));
        f.a(l.c(l.this), l1);
        com.oneaudience.sdk.b.d.b(l.e(), "Performing configuration sync... DONE");
        return null;
        i = 0;
        break;
        Log.w(OneAudience.class.getSimpleName(), "One Audience SDK Server Failed");
        break label228;
      }
    }
  }
  
  private final class e
    extends o
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
      a(l.b(l.this), new l.a(l.this, null), new Void[0]);
      if (!f.c(l.c(l.this), l.a(l.this)))
      {
        paramString = new Bundle();
        paramString.putString("appKey", paramUri);
        paramString.putBoolean("opt_out", bool);
        paramString.putBoolean("force_config", false);
        new l.c(l.this, null).a(null, null, paramString);
        return;
      }
      a(l.b(l.this), new l.f(l.this, null), new Bundle[0]);
      b(l.b(l.this), new l.b(l.this, paramUri, bool), new Void[0]);
    }
  }
  
  private class f
    extends q<Bundle, Void, Void>
  {
    private f() {}
    
    protected Void a(Bundle... paramVarArgs)
    {
      paramVarArgs = com.oneaudience.sdk.b.a.a();
      Object localObject = l.a(l.this).getString("facebook_json", "");
      if ((paramVarArgs != null) && (TextUtils.isEmpty((CharSequence)localObject)))
      {
        localObject = new j();
        paramVarArgs = new c().a(((j)localObject).a(l.c(l.this), l.a(l.this), paramVarArgs));
        if (paramVarArgs != null) {
          ((j)localObject).a(l.c(l.this), l.a(l.this), paramVarArgs);
        }
      }
      return null;
    }
  }
  
  private final class g
    extends o
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
      b(l.b(l.this), new l.h(l.this, paramUri, this.b), new Void[0]);
    }
  }
  
  private class h
    extends q<Void, Void, Void>
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
      k.a(this.b, new h().a(this.b, l.a(l.this), this.a).b);
      return null;
    }
  }
  
  protected static class i
    implements r.a
  {
    protected Context a = null;
    
    public i(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public void a(q paramQ, Exception paramException)
    {
      com.oneaudience.sdk.b.d.e(l.e(), "Error has occurred at task: %s\nerror:%s", new Object[] { paramQ.getClass().getSimpleName(), paramException });
      if (this.a != null)
      {
        paramQ = this.a.getSharedPreferences("oneaudience", 0);
        f.a(this.a, paramQ, paramException);
      }
    }
  }
}
