package com.heyzap.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.widget.Toast;
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
  private static final String BASE_URL = "https://" + APIClient.DOMAIN + "/in_game_api/ads/";
  private static final String LAST_CHECK_KEY = "last_checked_packages";
  private static final Integer MILLIS_BETWEEN_CHECKS = Integer.valueOf(86400000);
  
  public PackageManager() {}
  
  public static void checkInstalledPackages(Context paramContext)
  {
    ExecutorPool.getInstance().execute(new Runnable()
    {
      public void run()
      {
        if (Utils.isAmazon()) {}
        final SharedPreferences localSharedPreferences;
        final boolean bool;
        for (String str = PackageManager.BASE_URL + "gtc/amazon.json";; str = PackageManager.BASE_URL + "gtc/android.json")
        {
          localSharedPreferences = this.val$context.getSharedPreferences("com.heyzap.sdk.ads", 0);
          long l = localSharedPreferences.getLong("last_checked_packages", 0L);
          System.currentTimeMillis();
          bool = Utils.isDebug(this.val$context).booleanValue();
          if ((bool) || (Utils.isExpired(Long.valueOf(l), PackageManager.MILLIS_BETWEEN_CHECKS).booleanValue())) {
            break;
          }
          return;
        }
        Object localObject2 = null;
        Object localObject1 = localObject2;
        if (bool)
        {
          localObject1 = localObject2;
          if ((this.val$context instanceof Activity)) {
            localObject1 = (Activity)this.val$context;
          }
        }
        APIClient.get(this.val$context, str, new RequestParams(), new JsonHttpResponseHandler()
        {
          public void onSuccess(int paramAnonymous2Int, Header[] paramAnonymous2ArrayOfHeader, JSONObject paramAnonymous2JSONObject)
          {
            int k = 0;
            if ((bool) && (this.val$activity != null)) {
              this.val$activity.runOnUiThread(new Runnable()
              {
                public void run()
                {
                  Toast.makeText(PackageManager.1.this.val$context, "cip", 0).show();
                }
              });
            }
            Object localObject2;
            Object localObject1;
            try
            {
              paramAnonymous2ArrayOfHeader = paramAnonymous2JSONObject.optString("version", "0");
              localObject2 = PackageManager.1.this.val$context.getPackageManager().getInstalledPackages(0);
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
            final int m;
            if (localObject2 != null)
            {
              m = ((JSONArray)localObject2).length();
              paramAnonymous2JSONObject = new byte[(((JSONArray)localObject2).length() + 7) / 8];
              paramAnonymous2Int = 0;
            }
            int j;
            for (final int i = 0;; i = j)
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
                  localObject1 = new RequestParams();
                  ((RequestParams)localObject1).put("version", paramAnonymous2ArrayOfHeader);
                  ((RequestParams)localObject1).put("data", Base64.encodeToString(paramAnonymous2JSONObject, 2));
                  APIClient.post(PackageManager.1.this.val$context, PackageManager.BASE_URL + "aip", (RequestParams)localObject1, new JsonHttpResponseHandler()
                  {
                    public void onSuccess(int paramAnonymous3Int, Header[] paramAnonymous3ArrayOfHeader, JSONObject paramAnonymous3JSONObject)
                    {
                      if ((PackageManager.1.1.this.val$debug) && (PackageManager.1.1.this.val$activity != null)) {
                        PackageManager.1.1.this.val$activity.runOnUiThread(new Runnable()
                        {
                          public void run()
                          {
                            Toast.makeText(PackageManager.1.this.val$context, String.format("cip %s.%s", new Object[] { Integer.valueOf(PackageManager.1.1.2.this.val$installedCount), Integer.valueOf(PackageManager.1.1.2.this.val$totalCount) }), 1).show();
                          }
                        });
                      }
                    }
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
