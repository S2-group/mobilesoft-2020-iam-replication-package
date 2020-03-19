import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import cn.wps.moffice.OfficeApp;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class dgk
  extends dgl<dgk.a>
{
  public dgk() {}
  
  private static String aDR()
  {
    localStringBuilder = new StringBuilder();
    try
    {
      PackageManager localPackageManager = OfficeApp.ark().getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(8192).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((localApplicationInfo.flags & 0x1) == 0)
        {
          localStringBuilder.append("(").append(localApplicationInfo.loadLabel(localPackageManager).toString()).append("|").append(localApplicationInfo.packageName).append(")");
          localStringBuilder.append(",");
        }
      }
      return localStringBuilder.toString();
    }
    catch (Exception localException) {}
  }
  
  public final void aDQ()
  {
    this.dxe = new dgk.a();
  }
  
  final class a
    extends dha
  {
    public String dxc;
    
    a() {}
    
    protected final void d(JSONObject paramJSONObject)
      throws JSONException
    {
      paramJSONObject.put("apps", this.dxc).put("need_encrypt", "need_encrypt");
    }
    
    public final String type()
    {
      return "app_infos";
    }
  }
}
