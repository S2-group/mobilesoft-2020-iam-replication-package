package com.prestigio.android.ereader.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MMonitor
{
  private static final int MSG_UPDATE = 0;
  public static final String TAG = MMonitor.class.getSimpleName();
  private static MMonitor instance;
  private Context mContext;
  private OnMonitorUpdateListener mListener;
  private boolean monitorTraffic;
  private String packageName;
  private long updateTime = 1000L;
  
  MMonitor() {}
  
  private String getCPUFromPROCLine(String paramString)
  {
    int i = paramString.indexOf("%");
    return paramString.substring(paramString.substring(0, i - 1).lastIndexOf(" "), i + 1);
  }
  
  public static MMonitor getInstance()
  {
    if (instance == null) {
      instance = new MMonitor();
    }
    return instance;
  }
  
  private long getMemoryUsage()
  {
    return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
  }
  
  private void notifyListeners(final String paramString1, final String paramString2, final String paramString3)
  {
    new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (MMonitor.this.mListener != null) {
          MMonitor.this.mListener.OnMonitorUpdate(paramString1, paramString2, paramString3);
        }
      }
    }.sendEmptyMessage(0);
  }
  
  public MMonitor attachListener(OnMonitorUpdateListener paramOnMonitorUpdateListener)
  {
    this.mListener = paramOnMonitorUpdateListener;
    return this;
  }
  
  public MMonitor enabelTrafficMonitor(Context paramContext)
  {
    this.mContext = paramContext;
    this.monitorTraffic = true;
    return this;
  }
  
  public String getCPUUsage()
  {
    for (;;)
    {
      Object localObject2;
      try
      {
        localObject2 = Runtime.getRuntime().exec("top -m 15 -d 1 -n 1");
        str = null;
      }
      catch (Exception localException)
      {
        String str;
        label92:
        localException.printStackTrace();
        return null;
      }
      for (;;)
      {
        try
        {
          localObject2 = new BufferedReader(new InputStreamReader(((Process)localObject2).getInputStream()));
        }
        finally
        {
          Object localObject1;
          break label92;
        }
        try
        {
          str = ((BufferedReader)localObject2).readLine();
          if (str != null)
          {
            if ((this.packageName != null) && (str.contains(this.packageName)))
            {
              str = getCPUFromPROCLine(str);
              if (localObject2 != null) {
                ((BufferedReader)localObject2).close();
              }
              return str;
            }
            str = ((BufferedReader)localObject2).readLine();
            break;
          }
          if (localObject2 == null) {
            continue;
          }
          ((BufferedReader)localObject2).close();
        }
        finally
        {
          localObject1 = localObject2;
          localObject2 = localObject4;
          break label92;
        }
      }
    }
    if (str != null) {
      str.close();
    }
    throw ((Throwable)localObject2);
    return null;
  }
  
  public String getTrafficUsage()
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject = (ApplicationInfo)localIterator.next();
        if (!((ApplicationInfo)localObject).packageName.equals(this.packageName)) {
          continue;
        }
        int i = ((ApplicationInfo)localObject).uid;
        localObject = ((ApplicationInfo)localObject).packageName;
        try
        {
          localPackageManager.getApplicationInfo((String)localObject, 0);
          double d = TrafficStats.getUidRxBytes(i) / 1048576.0D + TrafficStats.getUidTxBytes(i) / 1048576.0D;
          if (d > 0.0D) {
            return String.format("%.2f", new Object[] { Double.valueOf(d) }) + " MB";
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
      }
    }
    return "0 MB";
  }
  
  public MMonitor setPackageName(String paramString)
  {
    this.packageName = paramString;
    return this;
  }
  
  public MMonitor setUpdateTime(long paramLong)
  {
    this.updateTime = paramLong;
    return this;
  }
  
  public void startMonitor()
  {
    new Timer(true).schedule(new Task(), this.updateTime);
  }
  
  public static abstract interface OnMonitorUpdateListener
  {
    public abstract void OnMonitorUpdate(String paramString1, String paramString2, String paramString3);
  }
  
  class Task
    extends TimerTask
  {
    Task() {}
    
    public void run()
    {
      new Thread()
      {
        public void run()
        {
          super.run();
          String str1 = MMonitor.this.getCPUUsage();
          if ((str1 != null) && (TextUtils.isEmpty(str1))) {}
          MMonitor localMMonitor = MMonitor.this;
          String str2 = MMonitor.this.getCPUUsage();
          long l = MMonitor.this.getMemoryUsage();
          if (MMonitor.this.monitorTraffic) {}
          for (str1 = MMonitor.this.getTrafficUsage();; str1 = null)
          {
            localMMonitor.notifyListeners(str2, String.valueOf(l), str1);
            MMonitor.this.startMonitor();
            return;
          }
        }
      }.start();
    }
  }
}
