package in.fulldive.vrapps.components;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import de.greenrobot.event.EventBus;
import in.fulldive.common.framework.ResourcesManager;
import in.fulldive.common.utils.Constants;
import in.fulldive.common.utils.HLog;
import in.fulldive.social.data.Tools;
import in.fulldive.social.data.Tools.QueryResultItem;
import in.fulldive.vrapps.events.ApplicationsListEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApplicationManager
{
  private static final String a = ApplicationManager.class.getSimpleName();
  private static final List<String> b = Collections.singletonList("in.fulldive.shell");
  private EventBus c = EventBus.getDefault();
  private final Context d;
  private final ResourcesManager e;
  private final AppsDatabaseHelper f;
  private int g = -1;
  private Set<String> h = new HashSet();
  private Set<String> i = new HashSet();
  private List<AppItem> j = new ArrayList();
  private List<AppItem> k = new ArrayList();
  
  public ApplicationManager(@NonNull ResourcesManager paramResourcesManager)
  {
    this.e = paramResourcesManager;
    this.d = paramResourcesManager.a();
    this.f = new AppsDatabaseHelper(this.d);
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo)
  {
    return ((paramApplicationInfo.flags & 0x1) == 0) && (!b.contains(paramApplicationInfo.packageName));
  }
  
  private void d()
  {
    this.g = 0;
    new Thread(new Runnable()
    {
      public void run()
      {
        ApplicationManager.a(ApplicationManager.this, 0);
        ApplicationManager.a(ApplicationManager.this).postSticky(new ApplicationsListEvent(0));
        if (ApplicationManager.b(ApplicationManager.this).isEmpty()) {
          ApplicationManager.c(ApplicationManager.this);
        }
        if (ApplicationManager.b(ApplicationManager.this).isEmpty()) {
          ApplicationManager.d(ApplicationManager.this);
        }
        ApplicationManager.e(ApplicationManager.this);
        ApplicationManager.b(ApplicationManager.this).addAll(ApplicationManager.f(ApplicationManager.this));
        ApplicationManager.g(ApplicationManager.this);
        ApplicationManager.a(ApplicationManager.this).postSticky(new ApplicationsListEvent(1, ApplicationManager.h(ApplicationManager.this), ApplicationManager.i(ApplicationManager.this)));
        ApplicationManager.a(ApplicationManager.this, 1);
      }
    }).start();
  }
  
  private void e()
  {
    Object localObject1 = null;
    long l = this.e.b("ApplicationManager.PROPERTY_CACHE_TIME", 0L);
    if ((l > 0L) && (System.currentTimeMillis() - l < 86400000L)) {}
    HashSet localHashSet;
    do
    {
      return;
      localHashSet = new HashSet();
      try
      {
        Object localObject2 = Constants.a() + "/googleplay/ids";
        Bundle localBundle = new Bundle(1);
        localBundle.putString("Content-type", "application/json");
        localObject2 = Tools.a((String)localObject2, null, localBundle, "GET");
        if (((Tools.QueryResultItem)localObject2).a == 200)
        {
          HLog.a(a, "All vr Apps received from the server");
          localObject2 = ((Tools.QueryResultItem)localObject2).b;
          localObject1 = localObject2;
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          try
          {
            localObject1 = new JSONArray((String)localObject1);
            int m = 0;
            if (m >= ((JSONArray)localObject1).length()) {
              break;
            }
            localHashSet.add(((JSONArray)localObject1).getString(m));
            m += 1;
            continue;
            HLog.a(a, "Could not get a list of vr apps from the server");
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
          localException2 = localException2;
          localException2.printStackTrace();
        }
      }
    } while ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localHashSet.isEmpty()));
    this.f.a((String[])localHashSet.toArray(new String[localHashSet.size()]));
    this.e.a("ApplicationManager.PROPERTY_CACHE_TIME", System.currentTimeMillis());
  }
  
  private void f()
  {
    List localList = this.f.b();
    if (!localList.isEmpty()) {
      this.h.addAll(localList);
    }
  }
  
  private void g()
  {
    this.i.clear();
    List localList = this.f.c();
    if (!localList.isEmpty()) {
      this.i.addAll(localList);
    }
  }
  
  private void h()
  {
    this.j = new ArrayList();
    this.k = new ArrayList();
    PackageManager localPackageManager = this.d.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      try
      {
        if (a(localApplicationInfo))
        {
          AppItem localAppItem = new AppItem(localApplicationInfo.packageName, localPackageManager.getApplicationIcon(localApplicationInfo.packageName), localApplicationInfo.loadLabel(localPackageManager).toString());
          this.j.add(localAppItem);
          if (this.h.contains(localApplicationInfo.packageName))
          {
            localAppItem.a(true);
            this.k.add(localAppItem);
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    Collections.sort(this.j);
    Collections.sort(this.k);
  }
  
  public void a()
  {
    if (this.g != 0) {
      d();
    }
  }
  
  public void a(final String paramString)
  {
    this.f.a(paramString);
    this.h.add(paramString);
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          String str = Constants.a() + "/googleplay/review";
          Bundle localBundle = new Bundle(1);
          localBundle.putString("Content-type", "application/json");
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("id", paramString);
          if (Tools.a(str, localJSONObject.toString(), localBundle, "POST").a / 100 == 2)
          {
            HLog.a(ApplicationManager.c(), "App " + paramString + " was successfully sent to the server");
            return;
          }
          HLog.a(ApplicationManager.c(), "Could not send app " + paramString + " to the server");
          return;
        }
        catch (Exception localException)
        {
          HLog.a(ApplicationManager.c(), localException);
        }
      }
    }).start();
  }
  
  public void b()
  {
    this.f.a();
    this.c.removeStickyEvent(ApplicationsListEvent.class);
  }
}
