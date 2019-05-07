import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.util.Base64;
import cn.wps.moffice.OfficeApp;
import cn.wps.moffice.define.VersionManager;
import cn.wps.moffice.main.common.ServerParamsUtil;
import cn.wps.moffice.main.common.ServerParamsUtil.Extras;
import cn.wps.moffice.main.common.ServerParamsUtil.Params;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class dgw
  extends dgl<dgw.a>
{
  public dgw() {}
  
  private boolean a(dgw.a paramA)
  {
    long l1;
    int i;
    Object localObject3;
    HashMap localHashMap;
    Object localObject5;
    for (;;)
    {
      try
      {
        if ((!VersionManager.aWD()) && (!VersionManager.aVM()))
        {
          l1 = dgv.aEp();
          Object localObject1 = ServerParamsUtil.sm("act_collect");
          if ((localObject1 == null) || (((ServerParamsUtil.Params)localObject1).result != 0) || (!((ServerParamsUtil.Params)localObject1).status.equals("true"))) {
            break label356;
          }
          i = 0;
          if (i >= ((ServerParamsUtil.Params)localObject1).extras.size()) {
            break label346;
          }
          if (!((ServerParamsUtil.Extras)((ServerParamsUtil.Params)localObject1).extras.get(i)).key.equals("period")) {
            break label339;
          }
          if (i == -1) {
            break label356;
          }
          long l2 = Long.parseLong(((ServerParamsUtil.Extras)((ServerParamsUtil.Params)localObject1).extras.get(i)).value);
          if (System.currentTimeMillis() - l1 <= l2 * 86400000L) {
            break label351;
          }
          i = 1;
          if (i != 0)
          {
            localObject3 = OfficeApp.ark().getPackageManager();
            localObject1 = ((PackageManager)localObject3).getInstalledApplications(8192);
            Collections.sort((List)localObject1, new ApplicationInfo.DisplayNameComparator((PackageManager)localObject3));
            localObject3 = new JSONArray();
            localHashMap = new HashMap();
            localObject1 = ((List)localObject1).iterator();
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
            localObject4 = (ApplicationInfo)((Iterator)localObject1).next();
            if ((((ApplicationInfo)localObject4).flags & 0x1) != 0) {
              continue;
            }
            l1 = jt("/sdcard/Android/data/" + ((ApplicationInfo)localObject4).packageName);
            if (l1 == 0L) {
              continue;
            }
            localObject5 = new JSONObject();
            ((JSONObject)localObject5).put("pn", ((ApplicationInfo)localObject4).packageName).put("active", l1);
            ((JSONArray)localObject3).put(localObject5);
            localHashMap.put(((ApplicationInfo)localObject4).packageName, Long.valueOf(l1));
            continue;
          }
        }
        if (hw.isEmpty(paramA.dxT)) {
          break label681;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
      return true;
      label339:
      i += 1;
      continue;
      label346:
      i = -1;
      continue;
      label351:
      i = 0;
      continue;
      label356:
      i = 0;
    }
    Object localObject4 = new StringBuilder();
    if (dgv.aEp() == 0L)
    {
      localObject2 = localHashMap.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject5 = (Map.Entry)((Iterator)localObject2).next();
        ((StringBuilder)localObject4).append("(").append((String)((Map.Entry)localObject5).getKey()).append("|").append(((Map.Entry)localObject5).getValue()).append("),");
      }
    }
    Object localObject2 = ftx.bGs().getString("last_collect_app_actives", "");
    label479:
    int j;
    if (((String)localObject2).isEmpty())
    {
      localObject2 = null;
      j = ((JSONArray)localObject2).length();
      i = 0;
    }
    for (;;)
    {
      if (i < j)
      {
        localObject5 = ((JSONArray)localObject2).getJSONObject(i).getString("pn");
        l1 = ((JSONArray)localObject2).getJSONObject(i).getLong("active");
        if ((!localHashMap.containsKey(localObject5)) || (((Long)localHashMap.get(localObject5)).longValue() != l1)) {
          break label683;
        }
        localHashMap.remove(localObject5);
        break label683;
        localObject2 = new JSONArray(new String(Base64.decode((String)localObject2, 0)));
        break label479;
      }
      dgv.b((JSONArray)localObject3, System.currentTimeMillis());
      localObject2 = localHashMap.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Map.Entry)((Iterator)localObject2).next();
        ((StringBuilder)localObject4).append("(").append((String)((Map.Entry)localObject3).getKey()).append("|").append(((Map.Entry)localObject3).getValue()).append("),");
      }
      paramA.dxT = ((StringBuilder)localObject4).toString();
      break;
      label681:
      return false;
      label683:
      i += 1;
    }
  }
  
  private long jt(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists())
    {
      long l2;
      if (paramString.isDirectory())
      {
        long l1 = paramString.lastModified();
        l2 = l1;
        if (paramString.list() != null)
        {
          paramString = paramString.listFiles();
          int j = paramString.length;
          int i = 0;
          l2 = l1;
          if (i < j)
          {
            l2 = jt(paramString[i].getAbsolutePath());
            if (l2 > l1) {
              l1 = l2;
            }
            for (;;)
            {
              i += 1;
              break;
            }
          }
        }
      }
      else
      {
        l2 = paramString.lastModified();
      }
      return l2;
    }
    return 0L;
  }
  
  public final void aDQ()
  {
    this.dxe = new dgw.a();
  }
  
  final class a
    extends dha
  {
    public String dxT;
    
    a() {}
    
    protected final void d(JSONObject paramJSONObject)
      throws JSONException
    {
      if (!hw.isEmpty(this.dxT)) {
        paramJSONObject.put("app_active", this.dxT).put("need_encrypt", "need_encrypt");
      }
    }
    
    public final String type()
    {
      return "folder";
    }
  }
}
