import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.supersonicads.sdk.precache.CacheManager;
import com.supersonicads.sdk.precache.CacheManager.OnAppsInstall;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class drz
  extends AsyncTask<Void, Void, Void>
{
  private JSONArray b;
  private PackageManager c;
  
  private drz(CacheManager paramCacheManager) {}
  
  protected Void a(Void... paramVarArgs)
  {
    paramVarArgs = this.c.getInstalledApplications(0);
    this.b = new JSONArray();
    paramVarArgs = paramVarArgs.iterator();
    for (;;)
    {
      if (!paramVarArgs.hasNext()) {
        return null;
      }
      Object localObject1 = (ApplicationInfo)paramVarArgs.next();
      Object localObject2 = ((ApplicationInfo)localObject1).name;
      localObject1 = ((ApplicationInfo)localObject1).packageName;
      try
      {
        localObject2 = new JSONObject();
        ((JSONObject)localObject2).put("packageName", localObject1);
        this.b.put(localObject2);
      }
      catch (JSONException localJSONException) {}
    }
  }
  
  protected void a(Void paramVoid)
  {
    if (CacheManager.b(this.a) != null) {
      CacheManager.b(this.a).onAppsInstallFinish(this.b);
    }
  }
  
  protected void onPreExecute()
  {
    this.c = CacheManager.a(this.a).getPackageManager();
  }
}
