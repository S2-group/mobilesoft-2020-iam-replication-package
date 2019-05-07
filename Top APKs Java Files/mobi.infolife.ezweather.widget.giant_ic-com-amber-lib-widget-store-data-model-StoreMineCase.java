package com.amber.lib.widget.store.data.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.amber.lib.device.DeviceId;
import com.amber.lib.widget.store.data.entities.ItemData;
import com.amber.lib.widget.store.data.parser.DataParse;
import com.amber.lib.widget.store.data.utils.StoreDataUtils;
import com.amber.lib.widget.store.utils.ReflectUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StoreMineCase
{
  public static final String EZWEATHER_PLUGIN = "EZWEATHER_PLUGIN";
  public static final String META_DATA_VALUE = "mobi.infolife.ezweather.plugin.widgetskin";
  private OkHttpClient mClient;
  private Context mContext;
  private ExecutorService mService;
  
  public StoreMineCase(Context paramContext)
  {
    this.mContext = paramContext;
    this.mClient = new OkHttpClient();
    this.mService = Executors.newSingleThreadExecutor();
  }
  
  private List<PackageInfo> getInstalledAppList(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private String getMineUrl(Context paramContext)
  {
    return "http://f.store.amberweather.com/get_plugins_info.php?w=" + StoreDataUtils.getWidthDimen(paramContext) + "&UID=" + DeviceId.getDeviceId(paramContext);
  }
  
  public void loadInstalledWidget(final Callback paramCallback)
  {
    this.mService.submit(new Runnable()
    {
      public void run()
      {
        ArrayList localArrayList = new ArrayList();
        PackageManager localPackageManager = StoreMineCase.this.mContext.getPackageManager();
        Object localObject1 = StoreMineCase.this.getInstalledAppList(StoreMineCase.this.mContext, 8192);
        Object localObject3 = null;
        if (localObject1 == null) {
          if (paramCallback != null) {
            paramCallback.onLoadNoData();
          }
        }
        label419:
        do
        {
          do
          {
            return;
            Iterator localIterator = ((List)localObject1).iterator();
            for (;;)
            {
              if (!localIterator.hasNext()) {
                break label419;
              }
              PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
              String str = localPackageInfo.packageName;
              int i;
              boolean bool;
              Object localObject2;
              try
              {
                localObject1 = localPackageManager.getApplicationInfo(str, 128);
                localObject3 = localObject1;
                if (localObject1 == null) {
                  continue;
                }
                localObject3 = localObject1;
                if (((ApplicationInfo)localObject1).metaData == null) {
                  continue;
                }
                Object localObject4 = ((ApplicationInfo)localObject1).metaData.getString("EZWEATHER_PLUGIN");
                if (localObject4 != null)
                {
                  localObject3 = localObject1;
                  if (!"mobi.infolife.ezweather.plugin.widgetskin".equals(localObject4)) {
                    continue;
                  }
                  localObject4 = ReflectUtil.createContextByPkgName(StoreMineCase.this.mContext, str);
                  i = 0;
                  localObject3 = new ItemData();
                  ((ItemData)localObject3).setPackageName(localPackageInfo.packageName);
                  ((ItemData)localObject3).setName(localPackageManager.getApplicationLabel((ApplicationInfo)localObject1).toString());
                  try
                  {
                    bool = ((Context)localObject4).getResources().getBoolean(ReflectUtil.getResourceId(StoreMineCase.this.mContext, "isFree", "bool", str));
                    i = bool;
                  }
                  catch (Exception localException2)
                  {
                    for (;;)
                    {
                      localException2.printStackTrace();
                      continue;
                      ((ItemData)localObject3).setPrice(2.0F);
                    }
                  }
                  if (i != 0)
                  {
                    ((ItemData)localObject3).setPrice(0.0F);
                    localArrayList.add(localObject3);
                    localObject3 = localObject1;
                  }
                }
              }
              catch (Exception localException1)
              {
                for (;;)
                {
                  localException1.printStackTrace();
                  localObject2 = localObject3;
                }
                localObject3 = localObject2;
              }
              if (StoreMineCase.this.mContext.getPackageName().equals(localException2))
              {
                i = 0;
                localObject3 = new ItemData();
                ((ItemData)localObject3).setPackageName(localException2);
                ((ItemData)localObject3).setName(localPackageManager.getApplicationLabel(localObject2).toString());
              }
              try
              {
                bool = StoreMineCase.this.mContext.getResources().getBoolean(ReflectUtil.getResourceId(StoreMineCase.this.mContext, "isFree", "bool", localException2));
                i = bool;
              }
              catch (Exception localException3)
              {
                for (;;)
                {
                  localException3.printStackTrace();
                  continue;
                  ((ItemData)localObject3).setPrice(2.0F);
                }
              }
              if (i == 0) {
                break;
              }
              ((ItemData)localObject3).setPrice(0.0F);
              localArrayList.add(localObject3);
              localObject3 = localObject2;
            }
            if (localArrayList.size() <= 0) {
              break;
            }
          } while (paramCallback == null);
          paramCallback.onLoadComplete(localArrayList);
          return;
        } while (paramCallback == null);
        paramCallback.onLoadNoData();
      }
    });
  }
  
  public void request(String paramString, Callback paramCallback)
  {
    String str = getMineUrl(this.mContext);
    paramString = RequestBody.create(MediaType.parse("Application/Json"), paramString);
    paramString = new Request.Builder().url(str).post(paramString).build();
    try
    {
      paramString = this.mClient.newCall(paramString).execute().body().string();
      if (TextUtils.isEmpty(paramString))
      {
        if (paramCallback != null) {
          paramCallback.onLoadNoData();
        }
      }
      else
      {
        paramString = new DataParse(paramString, 3).getWidgetItemList(this.mContext, 0, false);
        if ((paramString != null) && (paramString.size() > 0))
        {
          if (paramCallback == null) {
            return;
          }
          paramCallback.onLoadComplete(paramString);
        }
      }
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
      if (paramCallback != null)
      {
        paramCallback.onLoadFailed();
        return;
        if (paramCallback != null) {
          paramCallback.onLoadNoData();
        }
      }
    }
  }
  
  public static abstract interface Callback
  {
    public abstract void onLoadComplete(List<ItemData> paramList);
    
    public abstract void onLoadFailed();
    
    public abstract void onLoadNoData();
  }
}
