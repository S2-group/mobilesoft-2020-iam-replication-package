import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.autonavi.common.CC;
import java.util.Iterator;
import java.util.List;

public class ads
{
  private static ads a;
  
  private ads() {}
  
  public static ads a()
  {
    try
    {
      if (a == null) {
        a = new ads();
      }
      ads localAds = a;
      return localAds;
    }
    finally {}
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    Object localObject = CC.getApplication().getPackageManager().getInstalledPackages(0);
    if ((localObject == null) || (((List)localObject).size() == 0)) {
      return false;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo != null) && (TextUtils.equals(paramString, localPackageInfo.packageName))) {
        return true;
      }
    }
    return false;
  }
  
  public static void b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    SharedPreferences.Editor localEditor = CC.getApplication().getSharedPreferences("save_auto_preference", 0).edit();
    localEditor.putString("auto_id_key", paramString);
    localEditor.apply();
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString1, String paramString2);
  }
}
