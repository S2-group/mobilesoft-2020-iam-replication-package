package io.github.nikhilbhutani.analyzer;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import java.util.ArrayList;
import java.util.List;

public class DataAnalyzer
{
  ArrayList<String> appName = new ArrayList();
  public final Context context;
  public final PackageManager packageManager;
  List<ApplicationInfo> packages;
  
  public DataAnalyzer(Context paramContext)
  {
    this.packageManager = paramContext.getPackageManager();
    this.context = paramContext;
  }
  
  public Drawable getAppIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getApplicationIcon(paramApplicationInfo);
  }
  
  public String getAppName(ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getApplicationLabel(paramApplicationInfo).toString();
  }
  
  public List getApplicationMeta()
  {
    this.packages = this.packageManager.getInstalledApplications(128);
    return this.packages;
  }
  
  public String getDataTransmitted(ApplicationInfo paramApplicationInfo)
  {
    return String.valueOf(TrafficStats.getUidTxBytes(paramApplicationInfo.uid));
  }
  
  public String getPacketsReceived(ApplicationInfo paramApplicationInfo)
  {
    return String.valueOf(TrafficStats.getUidRxPackets(paramApplicationInfo.uid));
  }
  
  public String getPacketsTransmitted(ApplicationInfo paramApplicationInfo)
  {
    return String.valueOf(TrafficStats.getUidTxPackets(paramApplicationInfo.uid));
  }
  
  public String getReceivedData(ApplicationInfo paramApplicationInfo)
  {
    return String.valueOf(TrafficStats.getUidRxBytes(paramApplicationInfo.uid));
  }
}
