package cn.jingling.motu.photowonder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import cn.jingling.lib.f.f;
import cn.jingling.lib.f.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class PostStartupMotuService
  extends BaseService
{
  private Thread aQg = null;
  private boolean aQh = false;
  
  public PostStartupMotuService() {}
  
  public void onCreate()
  {
    super.onCreate();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (!this.aQh)
    {
      this.aQg = new b(null);
      this.aQg.setPriority(1);
      this.aQg.start();
      this.aQh = true;
    }
    return 2;
  }
  
  private final class a
    implements PostStartupService.b
  {
    private a() {}
    
    public final void bk(Context paramContext)
    {
      Object localObject2 = new ArrayList();
      Object localObject1 = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      Object localObject3;
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (ApplicationInfo)((Iterator)localObject1).next();
        if (((((ApplicationInfo)localObject3).flags & 0x1) == 0) && (!paramContext.getPackageName().equals(((ApplicationInfo)localObject3).packageName))) {
          ((List)localObject2).add(localObject3);
        }
      }
      if (((List)localObject2).size() > 0)
      {
        localObject1 = new ArrayList(((List)localObject2).size());
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
          HashMap localHashMap = new HashMap();
          localHashMap.put("packageName", ((ApplicationInfo)localObject3).packageName);
          ((List)localObject1).add(localHashMap);
        }
        localObject1 = new JSONArray((Collection)localObject1);
      }
      try
      {
        localObject1 = f.b(((JSONArray)localObject1).toString(), "mobuladuapps2016".getBytes());
        localObject2 = new JSONArray();
        localObject3 = new JSONObject();
        ((JSONObject)localObject3).put("data", localObject1);
        ((JSONArray)localObject2).put(localObject3);
        k.i("PostStartupMotuService", "apps to post: " + localObject2);
        localObject1 = new n(paramContext, ((JSONArray)localObject2).toString());
        ((n)localObject1).a(paramContext, new n.1((n)localObject1, null));
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private final class b
    extends Thread
  {
    private b() {}
    
    public final void run()
    {
      Iterator localIterator = PostStartupMotuService.a(PostStartupMotuService.this).iterator();
      while (localIterator.hasNext()) {
        ((PostStartupService.b)localIterator.next()).bk(PostStartupMotuService.this);
      }
    }
  }
}
