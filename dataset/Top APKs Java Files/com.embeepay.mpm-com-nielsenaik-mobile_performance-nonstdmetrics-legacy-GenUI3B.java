package com.nielsenaik.mobile_performance.nonstdmetrics.legacy;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.nielsenaik.mobile_performance.client.IEventCallback;
import com.nielsenaik.mobile_performance.client.IQClient;
import com.nielsenaik.mobile_performance.client.Log;
import com.nielsenaik.mobile_performance.client.QueryOnlyMetric;
import com.nielsenaik.mobile_performance.client.metrics.ui.UI3B;
import com.nielsenaik.mobile_performance.nonstdmetrics.scanners.AppUtil;
import java.nio.ByteBuffer;
import java.util.List;

public class GenUI3B
  extends QueryOnlyMetric
{
  private static final boolean DEBUG = false;
  private static final String defaultVersion = "Unknown";
  private static GenUI3B mInstance = null;
  private int limitedAppInstId = 0;
  private Context mContext;
  private IEventCallback mIEventCallback;
  private UI3B ui3B = new UI3B();
  
  private GenUI3B(Context paramContext, IQClient paramIQClient)
  {
    super(paramIQClient);
    this.mContext = paramContext;
    this.mIEventCallback = new IEventCallback()
    {
      public void onEvent(int paramAnonymousInt1, int paramAnonymousInt2, ByteBuffer paramAnonymousByteBuffer)
      {
        try
        {
          GenUI3B.access$002(GenUI3B.this, paramAnonymousByteBuffer.getInt(0));
          return;
        }
        catch (IndexOutOfBoundsException paramAnonymousByteBuffer)
        {
          GenUI3B.access$002(GenUI3B.this, 0);
        }
      }
    };
    this.mClient.registerForEvent(-2147483640, UI3B.ID, this.mIEventCallback);
  }
  
  public static void externalSubmit(String paramString)
  {
    if (mInstance != null) {
      mInstance.submit(paramString);
    }
  }
  
  public static GenUI3B getInstance(Context paramContext, IQClient paramIQClient)
  {
    if (mInstance == null) {
      mInstance = new GenUI3B(paramContext, paramIQClient);
    }
    return mInstance;
  }
  
  /* Error */
  private void submit(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:ui3B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3B;
    //   6: aload_1
    //   7: putfield 78	com/nielsenaik/mobile_performance/client/metrics/ui/UI3B:szAppName	Ljava/lang/String;
    //   10: aload_0
    //   11: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:ui3B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3B;
    //   14: ldc 13
    //   16: putfield 81	com/nielsenaik/mobile_performance/client/metrics/ui/UI3B:szAppVersion	Ljava/lang/String;
    //   19: aload_0
    //   20: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:ui3B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3B;
    //   23: aload_1
    //   24: invokestatic 87	com/nielsenaik/mobile_performance/nonstdmetrics/scanners/AppUtil:mapNameToIqCode	(Ljava/lang/String;)B
    //   27: putfield 91	com/nielsenaik/mobile_performance/client/metrics/ui/UI3B:ucAppType	B
    //   30: aload_0
    //   31: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:ui3B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3B;
    //   34: aload_1
    //   35: invokestatic 95	com/nielsenaik/mobile_performance/nonstdmetrics/scanners/AppUtil:generateAppInstanceId	(Ljava/lang/String;)I
    //   38: putfield 98	com/nielsenaik/mobile_performance/client/metrics/ui/UI3B:dwInstAppID	I
    //   41: aload_0
    //   42: getfield 43	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:mContext	Landroid/content/Context;
    //   45: invokevirtual 104	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   48: astore_2
    //   49: aload_2
    //   50: aload_1
    //   51: iconst_0
    //   52: invokevirtual 110	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   55: astore_1
    //   56: aload_0
    //   57: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:ui3B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3B;
    //   60: aload_1
    //   61: getfield 115	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   64: putfield 81	com/nielsenaik/mobile_performance/client/metrics/ui/UI3B:szAppVersion	Ljava/lang/String;
    //   67: aload_0
    //   68: getfield 52	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:mClient	Lcom/nielsenaik/mobile_performance/client/IQClient;
    //   71: aload_0
    //   72: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI3B:ui3B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3B;
    //   75: invokevirtual 119	com/nielsenaik/mobile_performance/client/IQClient:submitMetric	(Lcom/nielsenaik/mobile_performance/client/Metric;)I
    //   78: pop
    //   79: aload_0
    //   80: monitorexit
    //   81: return
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    //   87: astore_1
    //   88: goto -21 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	GenUI3B
    //   0	91	1	paramString	String
    //   48	2	2	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   2	49	82	finally
    //   49	67	82	finally
    //   67	79	82	finally
    //   49	67	87	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public int metricID()
  {
    return UI3B.ID;
  }
  
  public void submit()
  {
    Log.d("IQAgent", "UI3B submit");
    List localList = this.mContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((this.limitedAppInstId == 0) || (AppUtil.generateAppInstanceId(localPackageInfo.packageName) == this.limitedAppInstId)) {
        submit(localPackageInfo.packageName);
      }
      i += 1;
    }
  }
}
