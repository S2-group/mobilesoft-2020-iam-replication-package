package com.heyzap.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import com.heyzap.a.g;
import com.heyzap.a.j;
import com.heyzap.common.c.d;
import com.heyzap.common.d.a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public final class k
{
  private static final String a = "https://" + a.a + "/in_game_api/ads/";
  private static final Integer b = Integer.valueOf(86400000);
  
  public static void a(Context paramContext)
  {
    d.a().execute(new Runnable()
    {
      public final void run()
      {
        if (Utils.a()) {}
        final SharedPreferences localSharedPreferences;
        for (String str = k.a() + "gtc/amazon.json";; str = k.a() + "gtc/android.json")
        {
          localSharedPreferences = this.a.getSharedPreferences("com.heyzap.sdk.ads", 0);
          long l = localSharedPreferences.getLong("last_checked_packages", 0L);
          System.currentTimeMillis();
          if ((Utils.isDebug(this.a).booleanValue()) || (Utils.a(Long.valueOf(l), k.b()).booleanValue())) {
            break;
          }
          return;
        }
        a.a(this.a, str, new j(), new g()
        {
          public final void onSuccess(int paramAnonymous2Int, Header[] paramAnonymous2ArrayOfHeader, JSONObject paramAnonymous2JSONObject)
          {
            paramAnonymous2Int = 0;
            Object localObject2;
            Object localObject1;
            try
            {
              paramAnonymous2ArrayOfHeader = paramAnonymous2JSONObject.optString("version", "0");
              localObject2 = k.1.this.a.getPackageManager().getInstalledPackages(0);
              localObject1 = new HashSet();
              localObject2 = ((List)localObject2).iterator();
              while (((Iterator)localObject2).hasNext()) {
                ((HashSet)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
              }
              localObject2 = paramAnonymous2JSONObject.optJSONArray("packages");
            }
            catch (Exception paramAnonymous2ArrayOfHeader)
            {
              Logger.trace(paramAnonymous2ArrayOfHeader);
              return;
            }
            int i;
            if (localObject2 != null)
            {
              ((JSONArray)localObject2).length();
              paramAnonymous2JSONObject = new byte[(((JSONArray)localObject2).length() + 7) / 8];
              i = 0;
            }
            for (;;)
            {
              if (paramAnonymous2Int < ((JSONArray)localObject2).length())
              {
                if (((HashSet)localObject1).contains(((JSONArray)localObject2).getString(paramAnonymous2Int)))
                {
                  i = paramAnonymous2Int / 8;
                  paramAnonymous2JSONObject[i] = ((byte)(paramAnonymous2JSONObject[i] | 1 << paramAnonymous2Int % 8));
                  i = 1;
                }
              }
              else
              {
                if (i != 0)
                {
                  localObject1 = new j();
                  ((j)localObject1).a("version", paramAnonymous2ArrayOfHeader);
                  ((j)localObject1).a("data", Base64.encodeToString(paramAnonymous2JSONObject, 2));
                  a.d(k.1.this.a, k.a() + "aip", (j)localObject1, new g()
                  {
                    public final void onSuccess(int paramAnonymous3Int, Header[] paramAnonymous3ArrayOfHeader, JSONObject paramAnonymous3JSONObject) {}
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
