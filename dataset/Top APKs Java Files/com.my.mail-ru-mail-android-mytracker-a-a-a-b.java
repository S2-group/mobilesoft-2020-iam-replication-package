package ru.mail.android.mytracker.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.android.mytracker.a.a.d;
import ru.mail.android.mytracker.e;

public class b
  extends a
{
  public b(String paramString1, String paramString2, Context paramContext, e paramE)
  {
    super(paramString1, "appsinstalled", paramString2, paramContext, paramE);
  }
  
  private String d()
  {
    Object localObject3 = null;
    try
    {
      Object localObject1 = this.a.getPackageManager().getInstalledApplications(128);
      if (localObject1 != null)
      {
        localObject3 = new JSONObject();
        try
        {
          Object localObject4 = new JSONObject();
          ((JSONObject)localObject3).put("installedApps", localObject4);
          JSONArray localJSONArray = new JSONArray();
          ((JSONObject)localObject4).put("items", localJSONArray);
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject4 = (ApplicationInfo)((Iterator)localObject1).next();
            if ((((ApplicationInfo)localObject4).flags & 0x1) == 0)
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("bundle", ((ApplicationInfo)localObject4).packageName);
              localJSONArray.put(localJSONObject);
            }
          }
          return localObject3;
        }
        catch (JSONException localJSONException)
        {
          ru.mail.android.mytracker.c.a(localJSONException.toString());
          localObject3 = ((JSONObject)localObject3).toString();
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        ru.mail.android.mytracker.c.a(localThrowable.toString());
        Object localObject2 = null;
      }
    }
  }
  
  protected d a()
  {
    d localD = super.a();
    if (!localD.a()) {
      return localD;
    }
    String str1 = d();
    if (str1 == null)
    {
      localD.a(false);
      return localD;
    }
    try
    {
      ru.mail.android.mytracker.e.c localC = ru.mail.android.mytracker.e.c.a().a(this.a);
      String str2 = localC.b();
      String str3 = ru.mail.android.mytracker.e.b.a(str1);
      if (str2.equals(str3)) {
        break label136;
      }
      ru.mail.android.mytracker.c.a("Apps hash changed");
      boolean bool = a(str1);
      localD.a(bool);
      if (bool)
      {
        localC.a(str3);
        ru.mail.android.mytracker.c.a("Apps tracked successfully");
        return localD;
      }
    }
    catch (Throwable localThrowable)
    {
      ru.mail.android.mytracker.c.a("PreferencesManager error: " + localThrowable);
      localD.a(false);
      return localD;
    }
    ru.mail.android.mytracker.c.a("Track apps failed");
    return localD;
    label136:
    ru.mail.android.mytracker.c.a("Apps hash did not changed");
    return localD;
  }
}
