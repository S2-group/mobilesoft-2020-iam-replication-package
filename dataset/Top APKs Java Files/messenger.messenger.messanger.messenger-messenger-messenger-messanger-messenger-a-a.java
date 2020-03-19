package messenger.messenger.messanger.messenger.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.support.constraint.a.a.l;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import messenger.messenger.messanger.messenger.MessengerApplication;
import messenger.messenger.messanger.messenger.api.MessengerAPI;
import messenger.messenger.messanger.messenger.model.AppLaunchCountModel;
import messenger.messenger.messanger.messenger.model.TypeAwareModel;
import messenger.messenger.messanger.messenger.utils.d;
import retrofit2.av;
import retrofit2.g;

public final class a
  extends android.support.v4.a.a<List<TypeAwareModel>>
{
  private static final Comparator<AppLaunchCountModel> f = c.a;
  private PackageManager g = e().getPackageManager();
  private List<TypeAwareModel> h;
  private HashMap<String, Integer> i = new HashMap();
  
  public a(Context paramContext)
  {
    super(paramContext);
  }
  
  private void a(List<TypeAwareModel> paramList)
  {
    if (g())
    {
      b.a.a.b("%s+++ Warning! An async query came in while the Loader was reset! +++", new Object[] { "ADP_AppListLoader" });
      if (paramList != null) {
        b(paramList);
      }
    }
    List localList;
    do
    {
      return;
      localList = this.h;
      this.h = paramList;
      if (f())
      {
        b.a.a.a("ADP_AppListLoader+++ Delivering results to the LoaderManager for the ListFragment to display! +++", new Object[0]);
        super.b(paramList);
      }
    } while ((localList == null) || (localList == paramList));
    b.a.a.a("%s+++ Releasing any old data associated with this Loader. +++", new Object[] { "ADP_AppListLoader" });
    b(localList);
  }
  
  private static void b(List<TypeAwareModel> paramList)
  {
    b.a.a.a("%s+++ appList called! +++ %d", new Object[] { "ADP_AppListLoader", Integer.valueOf(paramList.size()) });
  }
  
  private List<TypeAwareModel> r()
  {
    int j = 0;
    b.a.a.a("%s+++ loadInBackground() called! +++", new Object[] { "ADP_AppListLoader" });
    try
    {
      localObject3 = new BufferedReader(new InputStreamReader(new BufferedInputStream(MessengerApplication.a().getAssets().open("applist.json"))));
      localObject4 = new StringBuilder();
      for (localObject1 = ((BufferedReader)localObject3).readLine(); localObject1 != null; localObject1 = ((BufferedReader)localObject3).readLine()) {
        ((StringBuilder)localObject4).append((String)localObject1);
      }
      this.i = ((HashMap)d.a(((StringBuilder)localObject4).toString(), this.i.getClass()));
    }
    catch (IOException localIOException2)
    {
      label293:
      for (;;)
      {
        Object localObject3;
        Object localObject4;
        Object localObject1;
        ApplicationInfo localApplicationInfo;
        AppLaunchCountModel localAppLaunchCountModel;
        int k;
      }
    }
    if (this.i == null) {
      this.i = new HashMap();
    }
    localObject1 = this.g.getInstalledApplications(0);
    if (localObject1 == null)
    {
      localObject1 = new ArrayList();
      messenger.messenger.messanger.messenger.utils.b.a();
      localObject4 = new ArrayList(((List)localObject1).size());
      if (j < ((List)localObject1).size())
      {
        localApplicationInfo = (ApplicationInfo)((List)localObject1).get(j);
        if ((localApplicationInfo != null) && (this.i.containsKey(localApplicationInfo.packageName)))
        {
          localAppLaunchCountModel = new AppLaunchCountModel();
          k = messenger.messenger.messanger.messenger.utils.b.a(localApplicationInfo.packageName);
          localAppLaunchCountModel.setLaunchCount(k);
          messenger.messenger.messanger.messenger.utils.b.a(k);
          localObject3 = localApplicationInfo.loadLabel(this.g);
          if (localObject3 == null) {
            break label293;
          }
        }
        for (localObject3 = ((CharSequence)localObject3).toString();; localObject3 = localApplicationInfo.packageName)
        {
          localAppLaunchCountModel.setmLabel((String)localObject3);
          localAppLaunchCountModel.setPackageName(localApplicationInfo.packageName);
          ((List)localObject4).add(localAppLaunchCountModel);
          j += 1;
          break;
        }
      }
      Collections.sort((List)localObject4, f);
      try
      {
        localObject1 = (ArrayList)messenger.messenger.messanger.messenger.api.b.a().getAppCardForLandingScreen("MAIN_SCREEN").a().b();
        localObject3 = new ArrayList();
        ((List)localObject3).addAll((Collection)localObject4);
        if (!l.a((Collection)localObject1))
        {
          Collections.sort((List)localObject1, b.a);
          ((List)localObject3).addAll((Collection)localObject1);
          return localObject3;
        }
      }
      catch (IOException localIOException1)
      {
        for (;;)
        {
          b.a.a.a(localIOException1);
          Object localObject2 = null;
        }
      }
      return localObject3;
    }
  }
  
  protected final void i()
  {
    b.a.a.a("%s+++ onStartLoading() called! +++", new Object[] { "ADP_AppListLoader" });
    if (this.h != null)
    {
      b.a.a.a("%s+++ Delivering previously loaded data to the client...", new Object[] { "ADP_AppListLoader" });
      a(this.h);
    }
    if (q())
    {
      b.a.a.a("%s+++ A content change has been detected... so force load! +++", new Object[] { "ADP_AppListLoader" });
      k();
    }
    while (this.h != null) {
      return;
    }
    b.a.a.a("%s+++ The current data is data is null... so force load! +++", new Object[] { "ADP_AppListLoader" });
    k();
  }
  
  public final void k()
  {
    b.a.a.a("%s+++ forceLoad() called! +++", new Object[] { "ADP_AppListLoader" });
    super.k();
  }
  
  protected final void m()
  {
    b.a.a.a("%s+++ onStopLoading() called! +++", new Object[] { "ADP_AppListLoader" });
    j();
  }
  
  protected final void p()
  {
    b.a.a.a("%s+++ onReset() called! +++", new Object[] { "ADP_AppListLoader" });
    m();
    if (this.h != null)
    {
      b(this.h);
      this.h = null;
    }
  }
}
