package com.nielsenaik.mobile_performance.nonstdmetrics.oneshots;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.nielsenaik.mobile_performance.client.IEventCallback;
import com.nielsenaik.mobile_performance.client.IQClient;
import com.nielsenaik.mobile_performance.client.Log;
import com.nielsenaik.mobile_performance.client.QueryOnlyMetric;
import com.nielsenaik.mobile_performance.client.metrics.ui.UI4B;
import com.nielsenaik.mobile_performance.nonstdmetrics.scanners.AppUtil;
import java.nio.ByteBuffer;
import java.util.List;

public class GenUI4B
  extends QueryOnlyMetric
{
  private static final boolean DEBUG = true;
  private static final String defaultVersion = "Unknown";
  private static GenUI4B mInstance = null;
  private Context mContext;
  private IEventCallback mIEventCallback;
  private long mLimitedAppInstId = 0L;
  private UI4B ui4B = new UI4B();
  
  private GenUI4B(Context paramContext, IQClient paramIQClient)
  {
    super(paramIQClient);
    this.mContext = paramContext;
    this.mIEventCallback = new IEventCallback()
    {
      public void onEvent(int paramAnonymousInt1, int paramAnonymousInt2, ByteBuffer paramAnonymousByteBuffer)
      {
        try
        {
          GenUI4B.access$002(GenUI4B.this, paramAnonymousByteBuffer.getLong(0));
          return;
        }
        catch (IndexOutOfBoundsException paramAnonymousByteBuffer)
        {
          GenUI4B.access$002(GenUI4B.this, 0L);
        }
      }
    };
    Log.d("IQAgent", "Registering UI4B callback");
    this.mClient.registerForEvent(-2147483640, UI4B.ID, this.mIEventCallback);
  }
  
  public static void externalSubmit(String paramString)
  {
    if (mInstance != null) {
      mInstance.submit(paramString);
    }
  }
  
  public static GenUI4B getInstance(Context paramContext, IQClient paramIQClient)
  {
    if (mInstance == null) {
      mInstance = new GenUI4B(paramContext, paramIQClient);
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
    //   3: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   6: aload_1
    //   7: putfield 91	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppName	Ljava/lang/String;
    //   10: aload_0
    //   11: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   14: ldc 13
    //   16: putfield 94	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppVersion	Ljava/lang/String;
    //   19: aload_0
    //   20: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   23: aload_1
    //   24: putfield 97	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppDescription	Ljava/lang/String;
    //   27: aload_0
    //   28: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   31: aload_1
    //   32: invokestatic 103	com/nielsenaik/mobile_performance/nonstdmetrics/scanners/AppUtil:mapNameToIqCode	(Ljava/lang/String;)B
    //   35: putfield 107	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:ucAppType	B
    //   38: aload_0
    //   39: getfield 43	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:mContext	Landroid/content/Context;
    //   42: invokevirtual 113	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   45: astore_2
    //   46: aload_2
    //   47: aload_1
    //   48: iconst_0
    //   49: invokevirtual 119	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   52: astore_1
    //   53: aload_0
    //   54: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   57: aload_1
    //   58: getfield 124	android/content/pm/PackageInfo:versionCode	I
    //   61: invokestatic 130	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   64: putfield 94	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppVersion	Ljava/lang/String;
    //   67: aload_0
    //   68: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   71: aload_1
    //   72: getfield 134	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   75: aload_2
    //   76: invokevirtual 140	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   79: invokeinterface 145 1 0
    //   84: putfield 97	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppDescription	Ljava/lang/String;
    //   87: aload_0
    //   88: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   91: aload_0
    //   92: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   95: getfield 91	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppName	Ljava/lang/String;
    //   98: aload_0
    //   99: getfield 43	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:mContext	Landroid/content/Context;
    //   102: invokestatic 149	com/nielsenaik/mobile_performance/nonstdmetrics/scanners/AppUtil:generateLongAppInstanceId	(Ljava/lang/String;Landroid/content/Context;)J
    //   105: putfield 152	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:qwInstAppID	J
    //   108: ldc 50
    //   110: new 154	java/lang/StringBuilder
    //   113: dup
    //   114: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   117: ldc -99
    //   119: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aload_0
    //   123: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   126: getfield 91	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppName	Ljava/lang/String;
    //   129: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: ldc -93
    //   134: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload_0
    //   138: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   141: getfield 107	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:ucAppType	B
    //   144: invokestatic 168	java/lang/Byte:toString	(B)Ljava/lang/String;
    //   147: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: ldc -86
    //   152: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: aload_0
    //   156: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   159: getfield 97	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppDescription	Ljava/lang/String;
    //   162: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: ldc -84
    //   167: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload_0
    //   171: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   174: getfield 94	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:szAppVersion	Ljava/lang/String;
    //   177: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: ldc -82
    //   182: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: aload_0
    //   186: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   189: getfield 152	com/nielsenaik/mobile_performance/client/metrics/ui/UI4B:qwInstAppID	J
    //   192: invokestatic 179	java/lang/Long:toString	(J)Ljava/lang/String;
    //   195: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual 180	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: invokestatic 58	com/nielsenaik/mobile_performance/client/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   204: pop
    //   205: aload_0
    //   206: getfield 62	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:mClient	Lcom/nielsenaik/mobile_performance/client/IQClient;
    //   209: aload_0
    //   210: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI4B:ui4B	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI4B;
    //   213: invokevirtual 184	com/nielsenaik/mobile_performance/client/IQClient:submitMetric	(Lcom/nielsenaik/mobile_performance/client/Metric;)I
    //   216: pop
    //   217: aload_0
    //   218: monitorexit
    //   219: return
    //   220: astore_1
    //   221: aload_0
    //   222: monitorexit
    //   223: aload_1
    //   224: athrow
    //   225: astore_1
    //   226: goto -139 -> 87
    //   229: astore_1
    //   230: goto -143 -> 87
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	233	0	this	GenUI4B
    //   0	233	1	paramString	String
    //   45	31	2	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   2	46	220	finally
    //   46	87	220	finally
    //   87	217	220	finally
    //   46	87	225	android/content/res/Resources$NotFoundException
    //   46	87	229	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public int metricID()
  {
    return UI4B.ID;
  }
  
  public void submit()
  {
    Log.d("IQAgent", "UI4B submit");
    if (this.mLimitedAppInstId != 0L)
    {
      List localList = this.mContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if (AppUtil.generateLongAppInstanceId(this.ui4B.szAppName, this.mContext) == this.mLimitedAppInstId) {
          submit(localPackageInfo.packageName);
        }
        i += 1;
      }
    }
  }
}
