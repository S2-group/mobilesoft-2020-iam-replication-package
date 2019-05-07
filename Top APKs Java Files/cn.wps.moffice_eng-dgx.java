import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.util.Base64;
import cn.wps.moffice.OfficeApp;
import cn.wps.moffice.define.VersionManager;
import cn.wps.moffice.main.common.ServerParamsUtil;
import cn.wps.moffice.main.common.ServerParamsUtil.Extras;
import cn.wps.moffice.main.common.ServerParamsUtil.Params;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class dgx
  extends dgl<dgx.a>
{
  public dgx()
  {
    for (;;)
    {
      Object localObject3;
      try
      {
        if ((VersionManager.aVM()) || (dgv.aEo() != 0L)) {
          break label536;
        }
        dgv.a(aEq(), System.currentTimeMillis());
        i = 1;
        if (i == 0)
        {
          long l1 = dgv.aEo();
          Object localObject1 = ServerParamsUtil.sm("install_uninstall");
          if ((localObject1 == null) || (((ServerParamsUtil.Params)localObject1).result != 0) || (!((ServerParamsUtil.Params)localObject1).status.equals("true"))) {
            break label558;
          }
          i = 0;
          if (i >= ((ServerParamsUtil.Params)localObject1).extras.size()) {
            break label548;
          }
          if (!((ServerParamsUtil.Extras)((ServerParamsUtil.Params)localObject1).extras.get(i)).key.equals("period")) {
            break label541;
          }
          if (i == -1) {
            break label558;
          }
          long l2 = Long.parseLong(((ServerParamsUtil.Extras)((ServerParamsUtil.Params)localObject1).extras.get(i)).value);
          if (System.currentTimeMillis() - l1 <= l2 * 86400000L) {
            break label553;
          }
          i = 1;
          if (i != 0)
          {
            localObject1 = ftx.bGs().getString("last_collect_apps", "");
            if (((String)localObject1).isEmpty())
            {
              localObject1 = null;
              localObject2 = c((JSONArray)localObject1);
              localObject3 = aEq();
              localObject1 = c((JSONArray)localObject3);
              dgv.a((JSONArray)localObject3, System.currentTimeMillis());
              i = 0;
              if (i >= ((ArrayList)localObject2).size()) {
                break label326;
              }
              j = 0;
              if (j >= ((ArrayList)localObject1).size()) {
                break label529;
              }
              if (!((JSONObject)((ArrayList)localObject1).get(j)).getString("package").equals(((JSONObject)((ArrayList)localObject2).get(i)).getString("package"))) {
                break label319;
              }
              ((ArrayList)localObject2).remove(i);
              ((ArrayList)localObject1).remove(j);
              i -= 1;
              break label529;
            }
            localObject1 = new JSONArray(new String(Base64.decode((String)localObject1, 0)));
            continue;
          }
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
      label319:
      label326:
      do
      {
        j += 1;
        break;
        if (!((ArrayList)localObject2).isEmpty())
        {
          localObject3 = new StringBuilder();
          j = ((ArrayList)localObject2).size();
          i = 0;
          while (i < j)
          {
            JSONObject localJSONObject = (JSONObject)((ArrayList)localObject2).get(i);
            ((StringBuilder)localObject3).append("(").append(localJSONObject.getString("name")).append("|").append(localJSONObject.getString("package")).append("),");
            i += 1;
          }
          ((dgx.a)this.dxe).dxW = ((StringBuilder)localObject3).toString();
        }
      } while (localThrowable.isEmpty());
      Object localObject2 = new StringBuilder();
      int j = localThrowable.size();
      int i = 0;
      while (i < j)
      {
        localObject3 = (JSONObject)localThrowable.get(i);
        ((StringBuilder)localObject2).append("(").append(((JSONObject)localObject3).getString("name")).append("|").append(((JSONObject)localObject3).getString("package")).append("),");
        i += 1;
      }
      ((dgx.a)this.dxe).dxV = ((StringBuilder)localObject2).toString();
      return;
      label529:
      i += 1;
      continue;
      label536:
      i = 0;
      continue;
      label541:
      i += 1;
      continue;
      label548:
      i = -1;
      continue;
      label553:
      i = 0;
      continue;
      label558:
      i = 0;
    }
  }
  
  private static JSONArray aEq()
  {
    PackageManager localPackageManager = OfficeApp.ark().getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(8192);
    JSONArray localJSONArray = new JSONArray();
    Collections.sort((List)localObject, new ApplicationInfo.DisplayNameComparator(localPackageManager));
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      if (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo.flags & 0x1) != 0) {
          continue;
        }
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("name", localApplicationInfo.loadLabel(localPackageManager).toString());
          localJSONObject.put("package", localApplicationInfo.packageName);
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
        }
      }
    }
    return localJSONArray;
  }
  
  private static ArrayList<JSONObject> c(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    int j = paramJSONArray.length();
    while (i < j)
    {
      localArrayList.add(paramJSONArray.getJSONObject(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public final void aDQ()
  {
    this.dxe = new dgx.a();
  }
  
  final class a
    extends dha
  {
    String dxV;
    String dxW;
    
    a() {}
    
    protected final void d(JSONObject paramJSONObject)
      throws JSONException
    {
      if (!hw.isEmpty(this.dxV)) {
        paramJSONObject.put("install", this.dxV);
      }
      if (!hw.isEmpty(this.dxW)) {
        paramJSONObject.put("uninstall", this.dxW);
      }
    }
    
    public final String type()
    {
      return "app_change";
    }
  }
}
