package com.oneaudience.sdk;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.oneaudience.sdk.b.b.b;
import com.oneaudience.sdk.b.b.e;
import com.oneaudience.sdk.b.d;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class ad
  extends ah<Void, Void, Boolean>
{
  private String b;
  
  public ad(w paramW, String paramString)
  {
    this.b = paramString;
  }
  
  private boolean c()
  {
    d.b(w.a(), "Populating Installed Apps to Registry");
    Object localObject3 = w.c(this.a).getPackageManager();
    Object localObject1 = ((PackageManager)localObject3).getInstalledApplications(128);
    TreeSet localTreeSet = new TreeSet(w.d(this.a).b());
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
            d.c(w.a(), "Couldn't get application package name");
            localObject2 = "NA";
          }
          if (localApplicationInfo.packageName == null) {
            break label220;
          }
          localTreeSet.remove(localApplicationInfo.packageName);
        }
        if ((localApplicationInfo.packageName != null) && (!w.d(this.a).b(localApplicationInfo.packageName)))
        {
          d.c(w.a(), "ADDING APP TO DICTIONARY: %s", new Object[] { localObject1 });
          w.d(this.a).a(localApplicationInfo.packageName, localObject1);
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
      bool = w.d(this.a).a((String)localObject3);
      d.b("APP WAS REMOVED: %s", (String)localObject3);
      if (!bool) {
        d.d(w.a(), "Can't remove package: %s", new Object[] { localObject3 });
      }
      bool = true;
    }
    w.d(this.a).a();
    w.b(this.a).edit().putBoolean("apps_registry_first_save", true).commit();
    return bool;
  }
  
  private boolean d()
  {
    Object localObject1;
    if (r.b(w.c(this.a), "android.permission.GET_ACCOUNTS"))
    {
      localObject1 = AccountManager.get(w.c(this.a)).getAccounts();
      if (!e.a((Object[])localObject1)) {
        break label38;
      }
    }
    label38:
    Object localObject2;
    String str;
    do
    {
      return false;
      localObject2 = new HashSet(b.a(Arrays.asList((Object[])localObject1), new ae(this))).iterator();
      localObject1 = new StringBuilder();
      b.a((Iterator)localObject2, new af(this, (StringBuilder)localObject1, (Iterator)localObject2));
      localObject2 = w.b(this.a).getString("email", "");
      str = ((StringBuilder)localObject1).toString();
    } while ((TextUtils.isEmpty(str)) || (((String)localObject2).equalsIgnoreCase(str)));
    w.b(this.a).edit().putString("email", ((StringBuilder)localObject1).toString()).commit();
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
    Object localObject = w.b(this.a).getString("oneAudienceId", "");
    int i;
    if ((localObject != null) && (!((String)localObject).equals("")))
    {
      i = 1;
      localObject = new Bundle();
      ((Bundle)localObject).putString("appKey", this.b);
      if ((!paramBoolean.booleanValue()) && (i != 0)) {
        break label107;
      }
      d.b(w.a(), "Device info changed");
      ((Bundle)localObject).putBoolean("force_config", true);
    }
    for (;;)
    {
      new y(this.a, null).a(null, null, (Bundle)localObject);
      return;
      i = 0;
      break;
      label107:
      d.b(w.a(), "Device Info didn't change");
      ((Bundle)localObject).putBoolean("force_config", false);
    }
  }
}
