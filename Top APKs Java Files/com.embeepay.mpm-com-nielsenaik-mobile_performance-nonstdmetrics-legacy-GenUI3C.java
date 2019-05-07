package com.nielsenaik.mobile_performance.nonstdmetrics.legacy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.nielsenaik.mobile_performance.client.IQClient;
import com.nielsenaik.mobile_performance.client.Log;
import com.nielsenaik.mobile_performance.client.QueryOnlyMetric;
import com.nielsenaik.mobile_performance.client.metrics.ui.UI3C;
import com.nielsenaik.mobile_performance.nonstdmetrics.scanners.AppUtil;
import com.nielsenaik.mobile_performance.stdmetrics.TrafficStats;
import java.util.Iterator;
import java.util.List;

public class GenUI3C
  extends QueryOnlyMetric
{
  private final Context mContext;
  private final UI3C mMetric = new UI3C();
  
  public GenUI3C(Context paramContext, IQClient paramIQClient)
  {
    super(paramIQClient);
    this.mContext = paramContext;
  }
  
  private void submitMetric(String paramString, int paramInt)
  {
    this.mMetric.reset();
    this.mMetric.dwInstAppId = AppUtil.generateAppInstanceId(paramString);
    this.mMetric.llTcpOctCntTx = TrafficStats.getUidTcpTxBytes(paramInt);
    this.mMetric.llTcpOctCntRx = TrafficStats.getUidTcpRxBytes(paramInt);
    this.mMetric.llTcpPktCntTx = TrafficStats.getUidTcpTxSegments(paramInt);
    this.mMetric.llTcpPktCntRx = TrafficStats.getUidTcpRxSegments(paramInt);
    this.mMetric.llUdpOctCntTx = TrafficStats.getUidUdpTxBytes(paramInt);
    this.mMetric.llUdpOctCntRx = TrafficStats.getUidUdpRxBytes(paramInt);
    this.mMetric.llUdpPktCntTx = TrafficStats.getUidUdpTxPackets(paramInt);
    this.mMetric.llUdpPktCntRx = TrafficStats.getUidUdpRxPackets(paramInt);
    if ((this.mMetric.llTcpOctCntTx > 0L) || (this.mMetric.llTcpOctCntRx > 0L) || (this.mMetric.llUdpOctCntTx > 0L) || (this.mMetric.llUdpOctCntRx > 0L))
    {
      Log.d("IQAgent", "GenUI3C - submittingfor " + paramString);
      this.mClient.submitMetric(this.mMetric);
    }
  }
  
  public int metricID()
  {
    return UI3C.ID;
  }
  
  public void submit()
  {
    Iterator localIterator = this.mContext.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      submitMetric(localApplicationInfo.packageName, localApplicationInfo.uid);
    }
  }
}
