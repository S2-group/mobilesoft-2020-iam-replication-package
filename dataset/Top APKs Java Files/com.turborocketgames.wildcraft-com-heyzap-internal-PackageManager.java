package com.heyzap.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.util.Base64;
import com.heyzap.common.concurrency.ExecutorPool;
import com.heyzap.common.net.APIClient;
import com.heyzap.http.JsonHttpResponseHandler;
import com.heyzap.http.RequestParams;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class PackageManager
{
  private static final String BASE_URL;
  private static final String LAST_CHECK_KEY = "last_checked_packages";
  private static final Integer MILLIS_BETWEEN_CHECKS = Integer.valueOf(86400000);
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://");
    localStringBuilder.append(APIClient.DOMAIN);
    localStringBuilder.append("/in_game_api/ads/");
    BASE_URL = localStringBuilder.toString();
  }
  
  public PackageManager() {}
  
  public static void checkInstalledPackages(Context paramContext)
  {
    ExecutorPool.getInstance().execute(new Runnable()
    {
      public void run()
      {
        Object localObject;
        if (Utils.isAmazon())
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(PackageManager.BASE_URL);
          ((StringBuilder)localObject).append("gtc/amazon.json");
          localObject = ((StringBuilder)localObject).toString();
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(PackageManager.BASE_URL);
          ((StringBuilder)localObject).append("gtc/android.json");
          localObject = ((StringBuilder)localObject).toString();
        }
        final SharedPreferences localSharedPreferences = this.val$context.getSharedPreferences("com.heyzap.sdk.ads", 0);
        long l = localSharedPreferences.getLong("last_checked_packages", 0L);
        System.currentTimeMillis();
        if ((!Utils.isDebug(this.val$context).booleanValue()) && (!Utils.isExpired(Long.valueOf(l), PackageManager.MILLIS_BETWEEN_CHECKS).booleanValue())) {
          return;
        }
        APIClient.get(this.val$context, (String)localObject, new RequestParams(), new JsonHttpResponseHandler()
        {
          public void onSuccess(int paramAnonymous2Int, Header[] paramAnonymous2ArrayOfHeader, JSONObject paramAnonymous2JSONObject)
          {
            for (;;)
            {
              try
              {
                paramAnonymous2ArrayOfHeader = paramAnonymous2JSONObject.optString("version", "0");
                Object localObject1 = PackageManager.1.this.val$context.getPackageManager();
                paramAnonymous2Int = 0;
                Object localObject2 = ((android.content.pm.PackageManager)localObject1).getInstalledPackages(0);
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
                    localObject1 = new RequestParams();
                    ((RequestParams)localObject1).put("version", paramAnonymous2ArrayOfHeader);
                    ((RequestParams)localObject1).put("data", Base64.encodeToString(paramAnonymous2JSONObject, 2));
                    paramAnonymous2ArrayOfHeader = PackageManager.1.this.val$context;
                    paramAnonymous2JSONObject = new StringBuilder();
                    paramAnonymous2JSONObject.append(PackageManager.BASE_URL);
                    paramAnonymous2JSONObject.append("aip");
                    APIClient.post(paramAnonymous2ArrayOfHeader, paramAnonymous2JSONObject.toString(), (RequestParams)localObject1, new JsonHttpResponseHandler()
                    {
                      public void onSuccess(int paramAnonymous3Int, Header[] paramAnonymous3ArrayOfHeader, JSONObject paramAnonymous3JSONObject) {}
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
