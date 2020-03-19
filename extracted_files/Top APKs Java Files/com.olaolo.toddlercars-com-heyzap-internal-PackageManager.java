package com.heyzap.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import com.heyzap.http.JsonHttpResponseHandler;
import com.heyzap.http.RequestParams;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class PackageManager
{
  private static final String BASE_URL = "https://ads.heyzap.com/in_game_api/ads/";
  private static final String LAST_CHECK_KEY = "last_checked_packages";
  private static final long MILLIS_BETWEEN_CHECKS = 86400000L;
  
  public PackageManager() {}
  
  public static void checkInstalledPackages(Context paramContext)
  {
    ExecutorPool.getInstance().execute(new Runnable()
    {
      public void run()
      {
        if (Utils.isAmazon()) {}
        final SharedPreferences localSharedPreferences;
        for (String str = "https://ads.heyzap.com/in_game_api/ads/gtc/amazon.json";; str = "https://ads.heyzap.com/in_game_api/ads/gtc/android.json")
        {
          localSharedPreferences = this.val$context.getSharedPreferences("com.heyzap.sdk.ads", 0);
          long l = localSharedPreferences.getLong("last_checked_packages", 0L);
          if (System.currentTimeMillis() - l >= 86400000L) {
            break;
          }
          return;
        }
        APIClient.get(this.val$context, str, new RequestParams(), new JsonHttpResponseHandler()
        {
          public void onSuccess(int paramAnonymous2Int, Header[] paramAnonymous2ArrayOfHeader, JSONObject paramAnonymous2JSONObject)
          {
            paramAnonymous2Int = 0;
            Object localObject2;
            Object localObject1;
            try
            {
              paramAnonymous2ArrayOfHeader = paramAnonymous2JSONObject.getString("version");
              localObject2 = PackageManager.1.this.val$context.getPackageManager().getInstalledPackages(0);
              localObject1 = new HashSet();
              localObject2 = ((List)localObject2).iterator();
              while (((Iterator)localObject2).hasNext()) {
                ((HashSet)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
              }
              localObject2 = paramAnonymous2JSONObject.getJSONArray("packages");
            }
            catch (Exception paramAnonymous2ArrayOfHeader)
            {
              paramAnonymous2ArrayOfHeader.printStackTrace();
              return;
            }
            paramAnonymous2JSONObject = new byte[(((JSONArray)localObject2).length() + 7) / 8];
            int i = 0;
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
                  localObject1 = new RequestParams();
                  ((RequestParams)localObject1).put("version", paramAnonymous2ArrayOfHeader);
                  ((RequestParams)localObject1).put("data", Base64.encodeToString(paramAnonymous2JSONObject, 2));
                  APIClient.post(PackageManager.1.this.val$context, "https://ads.heyzap.com/in_game_api/ads/aip", (RequestParams)localObject1, new JsonHttpResponseHandler());
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
