package com.nielsenaik.mobile_performance.nonstdmetrics.oneshots;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageStats;
import com.nielsenaik.mobile_performance.client.IQClient;
import com.nielsenaik.mobile_performance.client.Log;
import com.nielsenaik.mobile_performance.client.QueryOnlyMetric;
import com.nielsenaik.mobile_performance.client.metrics.ui.UI3A;
import java.lang.reflect.Field;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GenUI3A
  extends QueryOnlyMetric
{
  private static boolean queryInProgress = false;
  private Context mContext;
  
  public GenUI3A(Context paramContext, IQClient paramIQClient)
  {
    super(paramIQClient);
    this.mContext = paramContext;
  }
  
  public int metricID()
  {
    return UI3A.ID;
  }
  
  public void submit()
  {
    Log.d("IQAgent", "UI3A submit");
    if (!queryInProgress)
    {
      queryInProgress = true;
      new GenerateUI3ATask(null).start();
      return;
    }
    Log.d("IQAgent", "UI3A query already in progress");
  }
  
  private class GenerateUI3ATask
    extends Thread
  {
    private long cacheSize = 0L;
    private long cacheSizeExt = 0L;
    private final IPackageStatsObserver callback = new PackageSizeReceiver(null);
    private final Condition callbacksComplete = this.completionLock.newCondition();
    private long codeSize = 0L;
    private long codeSizeExt = 0L;
    private final Lock completionLock = new ReentrantLock();
    private long dataSize = 0L;
    private long dataSizeExt = 0L;
    private long mediaSize = 0L;
    private int numPackages = 0;
    private long obbSize = 0L;
    private final UI3A ui3A = new UI3A();
    
    private GenerateUI3ATask() {}
    
    private int reflectSizeFieldValues(PackageStats paramPackageStats, String paramString, int paramInt)
    {
      try
      {
        long l = paramPackageStats.getClass().getField(paramString).getLong(paramPackageStats);
        return (int)l;
      }
      catch (Exception paramPackageStats)
      {
        Log.i("IQAgent", "Failed to reflect " + paramString);
      }
      return paramInt;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: iconst_0
      //   2: putfield 56	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:numPackages	I
      //   5: aload_0
      //   6: lconst_0
      //   7: putfield 40	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:codeSize	J
      //   10: aload_0
      //   11: lconst_0
      //   12: putfield 42	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:dataSize	J
      //   15: aload_0
      //   16: lconst_0
      //   17: putfield 44	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:cacheSize	J
      //   20: aload_0
      //   21: lconst_0
      //   22: putfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:codeSizeExt	J
      //   25: aload_0
      //   26: lconst_0
      //   27: putfield 48	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:dataSizeExt	J
      //   30: aload_0
      //   31: lconst_0
      //   32: putfield 50	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:cacheSizeExt	J
      //   35: aload_0
      //   36: lconst_0
      //   37: putfield 52	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:obbSize	J
      //   40: aload_0
      //   41: lconst_0
      //   42: putfield 54	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:mediaSize	J
      //   45: aload_0
      //   46: getfield 35	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:this$0	Lcom/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A;
      //   49: invokestatic 152	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A:access$100	(Lcom/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A;)Landroid/content/Context;
      //   52: invokevirtual 158	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   55: astore_1
      //   56: aload_1
      //   57: iconst_0
      //   58: invokevirtual 164	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
      //   61: astore_3
      //   62: aload_1
      //   63: invokevirtual 112	java/lang/Object:getClass	()Ljava/lang/Class;
      //   66: ldc -90
      //   68: iconst_2
      //   69: anewarray 114	java/lang/Class
      //   72: dup
      //   73: iconst_0
      //   74: ldc -88
      //   76: aastore
      //   77: dup
      //   78: iconst_1
      //   79: ldc -86
      //   81: aastore
      //   82: invokevirtual 174	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   85: astore_2
      //   86: aload_3
      //   87: invokeinterface 180 1 0
      //   92: astore_3
      //   93: aload_3
      //   94: invokeinterface 186 1 0
      //   99: ifeq +285 -> 384
      //   102: aload_3
      //   103: invokeinterface 190 1 0
      //   108: checkcast 192	android/content/pm/PackageInfo
      //   111: astore 4
      //   113: ldc 126
      //   115: new 128	java/lang/StringBuilder
      //   118: dup
      //   119: invokespecial 129	java/lang/StringBuilder:<init>	()V
      //   122: ldc -62
      //   124: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   127: aload 4
      //   129: getfield 198	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   132: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   135: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   138: invokestatic 201	com/nielsenaik/mobile_performance/client/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   141: pop
      //   142: aload_2
      //   143: aload_1
      //   144: iconst_2
      //   145: anewarray 108	java/lang/Object
      //   148: dup
      //   149: iconst_0
      //   150: aload 4
      //   152: getfield 198	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   155: aastore
      //   156: dup
      //   157: iconst_1
      //   158: aload_0
      //   159: getfield 66	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:callback	Landroid/content/pm/IPackageStatsObserver;
      //   162: aastore
      //   163: invokevirtual 207	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   166: pop
      //   167: aload_0
      //   168: getfield 71	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:completionLock	Ljava/util/concurrent/locks/Lock;
      //   171: invokeinterface 210 1 0
      //   176: aload_0
      //   177: getfield 79	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:callbacksComplete	Ljava/util/concurrent/locks/Condition;
      //   180: invokeinterface 215 1 0
      //   185: aload_0
      //   186: getfield 71	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:completionLock	Ljava/util/concurrent/locks/Lock;
      //   189: invokeinterface 218 1 0
      //   194: goto -101 -> 93
      //   197: astore_1
      //   198: ldc 126
      //   200: new 128	java/lang/StringBuilder
      //   203: dup
      //   204: invokespecial 129	java/lang/StringBuilder:<init>	()V
      //   207: ldc -36
      //   209: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   212: aload_1
      //   213: invokevirtual 221	java/lang/Exception:toString	()Ljava/lang/String;
      //   216: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   219: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   222: invokestatic 224	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   225: pop
      //   226: iconst_0
      //   227: invokestatic 228	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A:access$302	(Z)Z
      //   230: pop
      //   231: return
      //   232: astore 5
      //   234: ldc 126
      //   236: new 128	java/lang/StringBuilder
      //   239: dup
      //   240: invokespecial 129	java/lang/StringBuilder:<init>	()V
      //   243: ldc -26
      //   245: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   248: aload 4
      //   250: getfield 198	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   253: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   256: ldc -24
      //   258: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   261: aload 5
      //   263: invokevirtual 236	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
      //   266: invokevirtual 239	java/lang/Throwable:toString	()Ljava/lang/String;
      //   269: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   272: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   275: invokestatic 224	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   278: pop
      //   279: goto -186 -> 93
      //   282: astore 5
      //   284: ldc 126
      //   286: new 128	java/lang/StringBuilder
      //   289: dup
      //   290: invokespecial 129	java/lang/StringBuilder:<init>	()V
      //   293: ldc -15
      //   295: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   298: aload 4
      //   300: getfield 198	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   303: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   306: ldc -24
      //   308: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   311: aload 5
      //   313: invokevirtual 221	java/lang/Exception:toString	()Ljava/lang/String;
      //   316: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   319: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   322: invokestatic 224	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   325: pop
      //   326: goto -233 -> 93
      //   329: astore 4
      //   331: ldc 126
      //   333: new 128	java/lang/StringBuilder
      //   336: dup
      //   337: invokespecial 129	java/lang/StringBuilder:<init>	()V
      //   340: ldc -13
      //   342: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   345: aload 4
      //   347: invokevirtual 221	java/lang/Exception:toString	()Ljava/lang/String;
      //   350: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   353: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   356: invokestatic 224	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   359: pop
      //   360: aload_0
      //   361: getfield 71	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:completionLock	Ljava/util/concurrent/locks/Lock;
      //   364: invokeinterface 218 1 0
      //   369: goto -276 -> 93
      //   372: astore_1
      //   373: aload_0
      //   374: getfield 71	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:completionLock	Ljava/util/concurrent/locks/Lock;
      //   377: invokeinterface 218 1 0
      //   382: aload_1
      //   383: athrow
      //   384: ldc 126
      //   386: new 128	java/lang/StringBuilder
      //   389: dup
      //   390: invokespecial 129	java/lang/StringBuilder:<init>	()V
      //   393: ldc -11
      //   395: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   398: aload_0
      //   399: getfield 40	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:codeSize	J
      //   402: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   405: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   408: ldc -4
      //   410: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   413: aload_0
      //   414: getfield 42	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:dataSize	J
      //   417: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   420: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   423: ldc -2
      //   425: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   428: aload_0
      //   429: getfield 44	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:cacheSize	J
      //   432: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   435: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   438: ldc_w 256
      //   441: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   444: aload_0
      //   445: getfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:codeSizeExt	J
      //   448: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   451: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   454: ldc_w 258
      //   457: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   460: aload_0
      //   461: getfield 48	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:dataSizeExt	J
      //   464: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   467: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   470: ldc_w 260
      //   473: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   476: aload_0
      //   477: getfield 50	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:cacheSizeExt	J
      //   480: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   483: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   486: ldc_w 262
      //   489: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   492: aload_0
      //   493: getfield 52	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:obbSize	J
      //   496: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   499: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   502: ldc_w 264
      //   505: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   508: aload_0
      //   509: getfield 54	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:mediaSize	J
      //   512: invokestatic 250	java/lang/Long:toString	(J)Ljava/lang/String;
      //   515: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   518: ldc_w 266
      //   521: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   524: aload_0
      //   525: getfield 56	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:numPackages	I
      //   528: invokestatic 271	java/lang/Integer:toString	(I)Ljava/lang/String;
      //   531: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   534: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   537: invokestatic 201	com/nielsenaik/mobile_performance/client/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   540: pop
      //   541: aload_0
      //   542: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   545: aload_0
      //   546: getfield 40	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:codeSize	J
      //   549: putfield 274	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeCode	J
      //   552: aload_0
      //   553: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   556: aload_0
      //   557: getfield 42	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:dataSize	J
      //   560: putfield 277	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeData	J
      //   563: aload_0
      //   564: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   567: aload_0
      //   568: getfield 44	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:cacheSize	J
      //   571: putfield 280	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeCache	J
      //   574: aload_0
      //   575: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   578: aload_0
      //   579: getfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:codeSizeExt	J
      //   582: putfield 283	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeCodeExternal	J
      //   585: aload_0
      //   586: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   589: aload_0
      //   590: getfield 48	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:dataSizeExt	J
      //   593: putfield 286	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeDataExternal	J
      //   596: aload_0
      //   597: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   600: aload_0
      //   601: getfield 50	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:cacheSizeExt	J
      //   604: putfield 289	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeCacheExternal	J
      //   607: aload_0
      //   608: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   611: aload_0
      //   612: getfield 52	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:obbSize	J
      //   615: putfield 292	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeObbExternal	J
      //   618: aload_0
      //   619: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   622: aload_0
      //   623: getfield 54	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:mediaSize	J
      //   626: putfield 295	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:qwSizeMediaExternal	J
      //   629: aload_0
      //   630: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   633: aload_0
      //   634: getfield 56	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:numPackages	I
      //   637: i2s
      //   638: putfield 299	com/nielsenaik/mobile_performance/client/metrics/ui/UI3A:wNumApp	S
      //   641: aload_0
      //   642: getfield 35	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:this$0	Lcom/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A;
      //   645: invokestatic 303	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A:access$200	(Lcom/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A;)Lcom/nielsenaik/mobile_performance/client/IQClient;
      //   648: aload_0
      //   649: getfield 61	com/nielsenaik/mobile_performance/nonstdmetrics/oneshots/GenUI3A$GenerateUI3ATask:ui3A	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI3A;
      //   652: invokevirtual 309	com/nielsenaik/mobile_performance/client/IQClient:submitMetric	(Lcom/nielsenaik/mobile_performance/client/Metric;)I
      //   655: pop
      //   656: goto -430 -> 226
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	659	0	this	GenerateUI3ATask
      //   55	89	1	localPackageManager	android.content.pm.PackageManager
      //   197	16	1	localException1	Exception
      //   372	11	1	localObject1	Object
      //   85	58	2	localMethod	java.lang.reflect.Method
      //   61	42	3	localObject2	Object
      //   111	188	4	localPackageInfo	android.content.pm.PackageInfo
      //   329	17	4	localException2	Exception
      //   232	30	5	localInvocationTargetException	java.lang.reflect.InvocationTargetException
      //   282	30	5	localException3	Exception
      // Exception table:
      //   from	to	target	type
      //   62	93	197	java/lang/Exception
      //   93	142	197	java/lang/Exception
      //   185	194	197	java/lang/Exception
      //   234	279	197	java/lang/Exception
      //   284	326	197	java/lang/Exception
      //   360	369	197	java/lang/Exception
      //   373	384	197	java/lang/Exception
      //   384	656	197	java/lang/Exception
      //   142	167	232	java/lang/reflect/InvocationTargetException
      //   142	167	282	java/lang/Exception
      //   167	185	329	java/lang/Exception
      //   167	185	372	finally
      //   331	360	372	finally
    }
    
    private class PackageSizeReceiver
      extends IPackageStatsObserver.Stub
    {
      private PackageSizeReceiver() {}
      
      public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
      {
        try
        {
          GenUI3A.GenerateUI3ATask.this.completionLock.lock();
          if (paramBoolean)
          {
            GenUI3A.GenerateUI3ATask.access$514(GenUI3A.GenerateUI3ATask.this, paramPackageStats.codeSize);
            GenUI3A.GenerateUI3ATask.access$614(GenUI3A.GenerateUI3ATask.this, paramPackageStats.dataSize);
            GenUI3A.GenerateUI3ATask.access$714(GenUI3A.GenerateUI3ATask.this, paramPackageStats.cacheSize);
            int i = GenUI3A.GenerateUI3ATask.this.reflectSizeFieldValues(paramPackageStats, "externalCodeSize", 0);
            GenUI3A.GenerateUI3ATask.access$914(GenUI3A.GenerateUI3ATask.this, i);
            int j = GenUI3A.GenerateUI3ATask.this.reflectSizeFieldValues(paramPackageStats, "externalDataSize", 0);
            GenUI3A.GenerateUI3ATask.access$1014(GenUI3A.GenerateUI3ATask.this, j);
            int k = GenUI3A.GenerateUI3ATask.this.reflectSizeFieldValues(paramPackageStats, "externalCacheSize", 0);
            GenUI3A.GenerateUI3ATask.access$1114(GenUI3A.GenerateUI3ATask.this, k);
            int m = GenUI3A.GenerateUI3ATask.this.reflectSizeFieldValues(paramPackageStats, "externalObbSize", 0);
            GenUI3A.GenerateUI3ATask.access$1214(GenUI3A.GenerateUI3ATask.this, m);
            int n = GenUI3A.GenerateUI3ATask.this.reflectSizeFieldValues(paramPackageStats, "externalMediaSize", 0);
            GenUI3A.GenerateUI3ATask.access$1314(GenUI3A.GenerateUI3ATask.this, n);
            Log.d("IQAgent", "GenUI3A PackageSizeReceiver for " + paramPackageStats.packageName + ", codeSize = " + Long.toString(paramPackageStats.codeSize) + ", dataSize = " + Long.toString(paramPackageStats.dataSize) + ", cacheSize = " + Long.toString(paramPackageStats.cacheSize) + ", codeSizeExt = " + Long.toString(i) + ", dataSizeExt = " + Long.toString(j) + ", cacheSizeExt = " + Long.toString(k) + ", obbSizeExt = " + Long.toString(m) + ", mediaSizeExt = " + Long.toString(n));
            GenUI3A.GenerateUI3ATask.access$1408(GenUI3A.GenerateUI3ATask.this);
          }
          return;
        }
        catch (Exception paramPackageStats)
        {
          Log.w("IQAgent", "GenUI3A exception thrown in PackageSizeReceiver.onGetStatsCompleted() : " + paramPackageStats.toString());
          return;
        }
        finally
        {
          GenUI3A.GenerateUI3ATask.this.callbacksComplete.signalAll();
          GenUI3A.GenerateUI3ATask.this.completionLock.unlock();
        }
      }
    }
  }
}
