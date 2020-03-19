package com.heyzap.d;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.heyzap.a.c.e;
import com.heyzap.a.e.a;
import com.heyzap.c.m;
import com.heyzap.c.q;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class x
{
  private static final String a = "https://" + a.a + "/in_game_api/ads/";
  private static final Integer b = Integer.valueOf(86400000);
  
  public static void a(Context paramContext)
  {
    e.a().execute(new Runnable()
    {
      public void run()
      {
        if (ah.a()) {}
        final SharedPreferences localSharedPreferences;
        boolean bool;
        for (String str = x.a() + "gtc/amazon.json";; str = x.a() + "gtc/android.json")
        {
          localSharedPreferences = x.this.getSharedPreferences("com.heyzap.sdk.ads", 0);
          long l = localSharedPreferences.getLong("last_checked_packages", 0L);
          System.currentTimeMillis();
          bool = ah.a(x.this).booleanValue();
          if ((bool) || (ah.a(Long.valueOf(l), x.b()).booleanValue())) {
            break;
          }
          return;
        }
        if ((bool) && ((x.this instanceof Activity))) {
          Activity localActivity = (Activity)x.this;
        }
        a.a(x.this, str, new q(), new m()
        {
          public void a(int paramAnonymous2Int, Header[] paramAnonymous2ArrayOfHeader, JSONObject paramAnonymous2JSONObject)
          {
            int k = 0;
            Object localObject2;
            Object localObject1;
            try
            {
              paramAnonymous2ArrayOfHeader = paramAnonymous2JSONObject.optString("version", "0");
              localObject2 = x.this.getPackageManager().getInstalledPackages(0);
              localObject1 = new HashSet();
              localObject2 = ((List)localObject2).iterator();
              while (((Iterator)localObject2).hasNext()) {
                ((HashSet)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
              }
              localObject2 = paramAnonymous2JSONObject.optJSONArray("packages");
            }
            catch (Exception paramAnonymous2ArrayOfHeader)
            {
              w.a(paramAnonymous2ArrayOfHeader);
              return;
            }
            if (localObject2 != null)
            {
              ((JSONArray)localObject2).length();
              paramAnonymous2JSONObject = new byte[(((JSONArray)localObject2).length() + 7) / 8];
              paramAnonymous2Int = 0;
            }
            int j;
            for (int i = 0;; i = j)
            {
              if (paramAnonymous2Int < ((JSONArray)localObject2).length())
              {
                j = i;
                if (((HashSet)localObject1).contains(((JSONArray)localObject2).getString(paramAnonymous2Int)))
                {
                  j = paramAnonymous2Int / 8;
                  paramAnonymous2JSONObject[j] = ((byte)(paramAnonymous2JSONObject[j] | 1 << paramAnonymous2Int % 8));
                  j = i + 1;
                  k = 1;
                }
              }
              else
              {
                if (k != 0)
                {
                  localObject1 = new q();
                  ((q)localObject1).a("version", paramAnonymous2ArrayOfHeader);
                  ((q)localObject1).a("data", g.a(paramAnonymous2JSONObject, 2));
                  a.c(x.this, x.a() + "aip", (q)localObject1, new m()
                  {
                    public void a(int paramAnonymous3Int, Header[] paramAnonymous3ArrayOfHeader, JSONObject paramAnonymous3JSONObject) {}
                  });
                }
                paramAnonymous2ArrayOfHeader = localSharedPreferences.edit();
                paramAnonymous2ArrayOfHeader.putLong("last_checked_packages", System.currentTimeMillis());
                paramAnonymous2ArrayOfHeader.commit();
                return;
              }
              paramAnonymous2Int += 1;
            }
          }
        });
      }
    });
  }
}
