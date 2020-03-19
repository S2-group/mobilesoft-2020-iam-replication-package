import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bwu
  extends bxh
{
  private int jdField_do;
  private List<String> jdField_for;
  private int jdField_int;
  
  public bwu(JSONObject paramJSONObject)
  {
    super(5);
    String str = paramJSONObject.optString("_068e13c11c8e7a33196e72147b99f184");
    if ("exist".equals(str)) {
      this.jdField_do = 1;
    } else if ("not-exist".equals(str)) {
      this.jdField_do = 2;
    }
    str = paramJSONObject.optString("_4a9a34d721ab8fa21f1731d6a622a392");
    if ("AND".equals(str)) {
      this.jdField_int = 1;
    } else if ("OR".equals(str)) {
      this.jdField_int = 2;
    }
    paramJSONObject = paramJSONObject.optJSONArray("_2724fb4a95dd8eb8755a7ac8123239fb");
    if ((paramJSONObject != null) && (paramJSONObject.length() > 0))
    {
      this.jdField_for = new LinkedList();
      int i = 0;
      while (i < paramJSONObject.length())
      {
        this.jdField_for.add(paramJSONObject.getString(i));
        i += 1;
      }
    }
  }
  
  private static boolean jdMethod_do(PackageManager paramPackageManager, String paramString, List<PackageInfo> paramList)
  {
    if (paramList == null) {}
    try
    {
      paramPackageManager.getPackageInfo(paramString, 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    paramPackageManager = paramList.iterator();
    while (paramPackageManager.hasNext()) {
      if (((PackageInfo)paramPackageManager.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
    return false;
  }
  
  public final boolean jdMethod_do(byl paramByl, bxi paramBxi)
  {
    paramByl = this.jdField_for;
    boolean bool3 = false;
    boolean bool2 = false;
    if ((paramByl != null) && (this.jdField_int != 0))
    {
      if (this.jdField_do == 0) {
        return false;
      }
      paramBxi = bvb.jdMethod_do().getPackageManager();
      paramByl = null;
      if (this.jdField_for.size() > 100) {
        paramByl = paramBxi.getInstalledPackages(128);
      }
      if (this.jdField_int == 1)
      {
        if (this.jdField_do == 1)
        {
          localIterator = this.jdField_for.iterator();
          while (localIterator.hasNext()) {
            if (!jdMethod_do(paramBxi, (String)localIterator.next(), paramByl)) {
              return false;
            }
          }
          return true;
        }
        localIterator = this.jdField_for.iterator();
        while (localIterator.hasNext()) {
          if (jdMethod_do(paramBxi, (String)localIterator.next(), paramByl)) {
            return false;
          }
        }
        return true;
      }
      if (this.jdField_do == 1)
      {
        localIterator = this.jdField_for.iterator();
        do
        {
          bool1 = bool2;
          if (!localIterator.hasNext()) {
            break;
          }
        } while (!jdMethod_do(paramBxi, (String)localIterator.next(), paramByl));
        bool1 = true;
        return bool1;
      }
      Iterator localIterator = this.jdField_for.iterator();
      do
      {
        bool1 = bool3;
        if (!localIterator.hasNext()) {
          break;
        }
      } while (jdMethod_do(paramBxi, (String)localIterator.next(), paramByl));
      boolean bool1 = true;
      return bool1;
    }
    return false;
  }
}
