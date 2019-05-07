package com.nielsenaik.mobile_performance.nonstdmetrics.legacy;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.nielsenaik.mobile_performance.client.IQClient;
import com.nielsenaik.mobile_performance.client.Log;
import com.nielsenaik.mobile_performance.client.QueryOnlyMetric;
import com.nielsenaik.mobile_performance.client.metrics.ui.UI35;
import com.nielsenaik.mobile_performance.nonstdmetrics.scanners.AppUtil;
import java.util.List;

public class GenUI35
  extends QueryOnlyMetric
{
  private static final boolean DEBUG = false;
  private static GenUI35 mInstance = null;
  private Context mContext;
  private UI35 ui35 = new UI35();
  
  private GenUI35(Context paramContext, IQClient paramIQClient)
  {
    super(paramIQClient);
    this.mContext = paramContext;
  }
  
  public static void externalSubmit(String paramString)
  {
    if (mInstance != null) {
      mInstance.submit(paramString);
    }
  }
  
  public static GenUI35 getInstance(Context paramContext, IQClient paramIQClient)
  {
    if (mInstance == null) {
      mInstance = new GenUI35(paramContext, paramIQClient);
    }
    return mInstance;
  }
  
  private void submit(String paramString)
  {
    try
    {
      this.ui35.szAppName = paramString;
      this.ui35.ucAppType = AppUtil.mapNameToIqCode(paramString);
      this.ui35.dwInstAppID = AppUtil.generateAppInstanceId(paramString);
      this.mClient.submitMetric(this.ui35);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public int metricID()
  {
    return UI35.ID;
  }
  
  public void submit()
  {
    Log.d("IQAgent", "UI35 submit");
    List localList = this.mContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      submit(((PackageInfo)localList.get(i)).packageName);
      i += 1;
    }
  }
}
