package com.heyzap.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import com.heyzap.a.c.c;
import com.heyzap.a.e.a;
import com.heyzap.c.g;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public final class j
{
  private static final String a;
  private static final Integer b = Integer.valueOf(86400000);
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder("https://");
    localStringBuilder.append(a.a);
    localStringBuilder.append("/in_game_api/ads/");
    a = localStringBuilder.toString();
  }
  
  public static void a(Context paramContext)
  {
    c.a().execute(new Runnable()
    {
      public final void run()
      {
        Object localObject;
        if (Utils.a())
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(j.a());
          ((StringBuilder)localObject).append("gtc/amazon.json");
          localObject = ((StringBuilder)localObject).toString();
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(j.a());
          ((StringBuilder)localObject).append("gtc/android.json");
          localObject = ((StringBuilder)localObject).toString();
        }
        final SharedPreferences localSharedPreferences = this.a.getSharedPreferences("com.heyzap.sdk.ads", 0);
        long l = localSharedPreferences.getLong("last_checked_packages", 0L);
        System.currentTimeMillis();
        if ((!Utils.isDebug(this.a).booleanValue()) && (!Utils.a(Long.valueOf(l), j.b()).booleanValue())) {
          return;
        }
        a.a(this.a, (String)localObject, new com.heyzap.c.j(), new g()
        {
          public final void onSuccess(int paramAnonymous2Int, Header[] paramAnonymous2ArrayOfHeader, JSONObject paramAnonymous2JSONObject)
          {
            for (;;)
            {
              try
              {
                paramAnonymous2ArrayOfHeader = paramAnonymous2JSONObject.optString("version", "0");
                Object localObject1 = j.1.this.a.getPackageManager();
                paramAnonymous2Int = 0;
                Object localObject2 = ((PackageManager)localObject1).getInstalledPackages(0);
                localObject1 = new HashSet();
                localObject2 = ((List)localObject2).iterator();
                if (((Iterator)localObject2).hasNext())
                {
                  ((HashSet)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
                  continue;
                }
                localObject2 = paramAnonymous2JSONObject.optJSONArray("packages");
                if (localObject2 != null)
                {
                  ((JSONArray)localObject2).length();
                  paramAnonymous2JSONObject = new byte[(((JSONArray)localObject2).length() + 7) / 8];
                  int i = 0;
                  if (paramAnonymous2Int < ((JSONArray)localObject2).length())
                  {
                    if (!((HashSet)localObject1).contains(((JSONArray)localObject2).getString(paramAnonymous2Int))) {
                      break label287;
                    }
                    i = paramAnonymous2Int / 8;
                    paramAnonymous2JSONObject[i] = ((byte)(paramAnonymous2JSONObject[i] | 1 << paramAnonymous2Int % 8));
                    i = 1;
                    break label287;
                  }
                  if (i != 0)
                  {
                    localObject1 = new com.heyzap.c.j();
                    ((com.heyzap.c.j)localObject1).a("version", paramAnonymous2ArrayOfHeader);
                    ((com.heyzap.c.j)localObject1).a("data", Base64.encodeToString(paramAnonymous2JSONObject, 2));
                    paramAnonymous2ArrayOfHeader = j.1.this.a;
                    paramAnonymous2JSONObject = new StringBuilder();
                    paramAnonymous2JSONObject.append(j.a());
                    paramAnonymous2JSONObject.append("aip");
                    a.d(paramAnonymous2ArrayOfHeader, paramAnonymous2JSONObject.toString(), (com.heyzap.c.j)localObject1, new g()
                    {
                      public final void onSuccess(int paramAnonymous3Int, Header[] paramAnonymous3ArrayOfHeader, JSONObject paramAnonymous3JSONObject) {}
                    });
                  }
                }
                paramAnonymous2ArrayOfHeader = localSharedPreferences.edit();
                paramAnonymous2ArrayOfHeader.putLong("last_checked_packages", System.currentTimeMillis());
                paramAnonymous2ArrayOfHeader.commit();
                return;
              }
              catch (Exception paramAnonymous2ArrayOfHeader)
              {
                Logger.trace(paramAnonymous2ArrayOfHeader);
                return;
              }
              label287:
              paramAnonymous2Int += 1;
            }
          }
        });
      }
    });
  }
}
