package com.nielsenaik.mobile_performance.nonstdmetrics.legacy;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageStats;
import com.nielsenaik.mobile_performance.client.IQClient;
import com.nielsenaik.mobile_performance.client.Log;
import com.nielsenaik.mobile_performance.client.QueryOnlyMetric;
import com.nielsenaik.mobile_performance.client.metrics.ui.UI34;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GenUI34
  extends QueryOnlyMetric
{
  private static boolean queryInProgress = false;
  private Context mContext;
  
  public GenUI34(Context paramContext, IQClient paramIQClient)
  {
    super(paramIQClient);
    this.mContext = paramContext;
  }
  
  public int metricID()
  {
    return UI34.ID;
  }
  
  public void submit()
  {
    Log.d("IQAgent", "UI34 submit");
    if (!queryInProgress)
    {
      queryInProgress = true;
      new GenerateUI34Task(null).start();
      return;
    }
    Log.d("IQAgent", "UI34 query already in progress");
  }
  
  private class GenerateUI34Task
    extends Thread
  {
    private long cacheSize = 0L;
    private final IPackageStatsObserver callback = new PackageSizeReceiver(null);
    private final Condition callbackComplete = this.completionLock.newCondition();
    private long codeSize = 0L;
    private final Lock completionLock = new ReentrantLock();
    private long dataSize = 0L;
    private int numPackages = 0;
    private final UI34 ui34 = new UI34();
    
    private GenerateUI34Task() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: lconst_0
      //   2: putfield 35	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:codeSize	J
      //   5: aload_0
      //   6: lconst_0
      //   7: putfield 37	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:dataSize	J
      //   10: aload_0
      //   11: lconst_0
      //   12: putfield 39	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:cacheSize	J
      //   15: aload_0
      //   16: iconst_0
      //   17: putfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:numPackages	I
      //   20: aload_0
      //   21: getfield 30	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:this$0	Lcom/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34;
      //   24: invokestatic 89	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34:access$100	(Lcom/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34;)Landroid/content/Context;
      //   27: invokevirtual 95	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   30: astore_1
      //   31: aload_1
      //   32: iconst_0
      //   33: invokevirtual 101	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
      //   36: astore_3
      //   37: aload_1
      //   38: invokevirtual 107	java/lang/Object:getClass	()Ljava/lang/Class;
      //   41: ldc 109
      //   43: iconst_2
      //   44: anewarray 111	java/lang/Class
      //   47: dup
      //   48: iconst_0
      //   49: ldc 113
      //   51: aastore
      //   52: dup
      //   53: iconst_1
      //   54: ldc 115
      //   56: aastore
      //   57: invokevirtual 119	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   60: astore_2
      //   61: aload_3
      //   62: invokeinterface 125 1 0
      //   67: astore_3
      //   68: aload_3
      //   69: invokeinterface 131 1 0
      //   74: ifeq +317 -> 391
      //   77: aload_3
      //   78: invokeinterface 135 1 0
      //   83: checkcast 137	android/content/pm/PackageInfo
      //   86: astore 4
      //   88: ldc -117
      //   90: new 141	java/lang/StringBuilder
      //   93: dup
      //   94: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   97: ldc -112
      //   99: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   102: aload 4
      //   104: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   107: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   110: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   113: invokestatic 162	com/nielsenaik/mobile_performance/client/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   116: pop
      //   117: aload_2
      //   118: aload_1
      //   119: iconst_2
      //   120: anewarray 103	java/lang/Object
      //   123: dup
      //   124: iconst_0
      //   125: aload 4
      //   127: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   130: aastore
      //   131: dup
      //   132: iconst_1
      //   133: aload_0
      //   134: getfield 51	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:callback	Landroid/content/pm/IPackageStatsObserver;
      //   137: aastore
      //   138: invokevirtual 168	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   141: pop
      //   142: aload_0
      //   143: getfield 56	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:completionLock	Ljava/util/concurrent/locks/Lock;
      //   146: invokeinterface 171 1 0
      //   151: aload_0
      //   152: getfield 64	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:callbackComplete	Ljava/util/concurrent/locks/Condition;
      //   155: invokeinterface 176 1 0
      //   160: aload_0
      //   161: getfield 56	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:completionLock	Ljava/util/concurrent/locks/Lock;
      //   164: invokeinterface 179 1 0
      //   169: goto -101 -> 68
      //   172: astore_1
      //   173: ldc -117
      //   175: new 141	java/lang/StringBuilder
      //   178: dup
      //   179: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   182: ldc -75
      //   184: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   187: aload_1
      //   188: invokevirtual 182	java/lang/RuntimeException:toString	()Ljava/lang/String;
      //   191: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   194: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   197: invokestatic 185	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   200: pop
      //   201: iconst_0
      //   202: invokestatic 189	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34:access$302	(Z)Z
      //   205: pop
      //   206: return
      //   207: astore 5
      //   209: ldc -117
      //   211: new 141	java/lang/StringBuilder
      //   214: dup
      //   215: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   218: ldc -65
      //   220: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   223: aload 4
      //   225: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   228: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   231: ldc -63
      //   233: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   236: aload 5
      //   238: invokevirtual 197	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
      //   241: invokevirtual 200	java/lang/Throwable:toString	()Ljava/lang/String;
      //   244: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   247: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   250: invokestatic 185	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   253: pop
      //   254: goto -186 -> 68
      //   257: astore_1
      //   258: ldc -117
      //   260: new 141	java/lang/StringBuilder
      //   263: dup
      //   264: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   267: ldc -75
      //   269: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   272: aload_1
      //   273: invokevirtual 201	java/lang/Exception:toString	()Ljava/lang/String;
      //   276: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   279: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   282: invokestatic 185	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   285: pop
      //   286: goto -85 -> 201
      //   289: astore 5
      //   291: ldc -117
      //   293: new 141	java/lang/StringBuilder
      //   296: dup
      //   297: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   300: ldc -53
      //   302: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   305: aload 4
      //   307: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
      //   310: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   313: ldc -63
      //   315: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   318: aload 5
      //   320: invokevirtual 201	java/lang/Exception:toString	()Ljava/lang/String;
      //   323: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   326: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   329: invokestatic 185	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   332: pop
      //   333: goto -265 -> 68
      //   336: astore 4
      //   338: ldc -117
      //   340: new 141	java/lang/StringBuilder
      //   343: dup
      //   344: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   347: ldc -51
      //   349: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   352: aload 4
      //   354: invokevirtual 201	java/lang/Exception:toString	()Ljava/lang/String;
      //   357: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   360: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   363: invokestatic 185	com/nielsenaik/mobile_performance/client/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   366: pop
      //   367: aload_0
      //   368: getfield 56	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:completionLock	Ljava/util/concurrent/locks/Lock;
      //   371: invokeinterface 179 1 0
      //   376: goto -308 -> 68
      //   379: astore_1
      //   380: aload_0
      //   381: getfield 56	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:completionLock	Ljava/util/concurrent/locks/Lock;
      //   384: invokeinterface 179 1 0
      //   389: aload_1
      //   390: athrow
      //   391: ldc -117
      //   393: new 141	java/lang/StringBuilder
      //   396: dup
      //   397: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   400: ldc -49
      //   402: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   405: aload_0
      //   406: getfield 35	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:codeSize	J
      //   409: invokestatic 212	java/lang/Long:toString	(J)Ljava/lang/String;
      //   412: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   415: ldc -42
      //   417: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   420: aload_0
      //   421: getfield 37	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:dataSize	J
      //   424: invokestatic 212	java/lang/Long:toString	(J)Ljava/lang/String;
      //   427: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   430: ldc -40
      //   432: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   435: aload_0
      //   436: getfield 39	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:cacheSize	J
      //   439: invokestatic 212	java/lang/Long:toString	(J)Ljava/lang/String;
      //   442: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   445: ldc -38
      //   447: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   450: aload_0
      //   451: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:numPackages	I
      //   454: invokestatic 223	java/lang/Integer:toString	(I)Ljava/lang/String;
      //   457: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   460: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   463: invokestatic 162	com/nielsenaik/mobile_performance/client/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   466: pop
      //   467: aload_0
      //   468: getfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:ui34	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI34;
      //   471: aload_0
      //   472: getfield 35	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:codeSize	J
      //   475: l2i
      //   476: putfield 226	com/nielsenaik/mobile_performance/client/metrics/ui/UI34:dwSizeCode	I
      //   479: aload_0
      //   480: getfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:ui34	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI34;
      //   483: aload_0
      //   484: getfield 37	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:dataSize	J
      //   487: l2i
      //   488: putfield 229	com/nielsenaik/mobile_performance/client/metrics/ui/UI34:dwSizeData	I
      //   491: aload_0
      //   492: getfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:ui34	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI34;
      //   495: aload_0
      //   496: getfield 39	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:cacheSize	J
      //   499: l2i
      //   500: putfield 232	com/nielsenaik/mobile_performance/client/metrics/ui/UI34:dwSizeCache	I
      //   503: aload_0
      //   504: getfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:ui34	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI34;
      //   507: aload_0
      //   508: getfield 41	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:numPackages	I
      //   511: i2s
      //   512: putfield 236	com/nielsenaik/mobile_performance/client/metrics/ui/UI34:wNumApp	S
      //   515: aload_0
      //   516: getfield 30	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:this$0	Lcom/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34;
      //   519: invokestatic 240	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34:access$200	(Lcom/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34;)Lcom/nielsenaik/mobile_performance/client/IQClient;
      //   522: aload_0
      //   523: getfield 46	com/nielsenaik/mobile_performance/nonstdmetrics/legacy/GenUI34$GenerateUI34Task:ui34	Lcom/nielsenaik/mobile_performance/client/metrics/ui/UI34;
      //   526: invokevirtual 246	com/nielsenaik/mobile_performance/client/IQClient:submitMetric	(Lcom/nielsenaik/mobile_performance/client/Metric;)I
      //   529: pop
      //   530: goto -329 -> 201
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	533	0	this	GenerateUI34Task
      //   30	89	1	localPackageManager	android.content.pm.PackageManager
      //   172	16	1	localRuntimeException	RuntimeException
      //   257	16	1	localException1	Exception
      //   379	11	1	localObject1	Object
      //   60	58	2	localMethod	java.lang.reflect.Method
      //   36	42	3	localObject2	Object
      //   86	220	4	localPackageInfo	android.content.pm.PackageInfo
      //   336	17	4	localException2	Exception
      //   207	30	5	localInvocationTargetException	java.lang.reflect.InvocationTargetException
      //   289	30	5	localException3	Exception
      // Exception table:
      //   from	to	target	type
      //   37	68	172	java/lang/RuntimeException
      //   68	117	172	java/lang/RuntimeException
      //   117	142	172	java/lang/RuntimeException
      //   160	169	172	java/lang/RuntimeException
      //   209	254	172	java/lang/RuntimeException
      //   291	333	172	java/lang/RuntimeException
      //   367	376	172	java/lang/RuntimeException
      //   380	391	172	java/lang/RuntimeException
      //   391	530	172	java/lang/RuntimeException
      //   117	142	207	java/lang/reflect/InvocationTargetException
      //   37	68	257	java/lang/Exception
      //   68	117	257	java/lang/Exception
      //   160	169	257	java/lang/Exception
      //   209	254	257	java/lang/Exception
      //   291	333	257	java/lang/Exception
      //   367	376	257	java/lang/Exception
      //   380	391	257	java/lang/Exception
      //   391	530	257	java/lang/Exception
      //   117	142	289	java/lang/Exception
      //   142	160	336	java/lang/Exception
      //   142	160	379	finally
      //   338	367	379	finally
    }
    
    private class PackageSizeReceiver
      extends IPackageStatsObserver.Stub
    {
      private PackageSizeReceiver() {}
      
      public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
      {
        try
        {
          GenUI34.GenerateUI34Task.this.completionLock.lock();
          if (paramBoolean)
          {
            GenUI34.GenerateUI34Task.access$514(GenUI34.GenerateUI34Task.this, paramPackageStats.codeSize);
            GenUI34.GenerateUI34Task.access$614(GenUI34.GenerateUI34Task.this, paramPackageStats.dataSize);
            GenUI34.GenerateUI34Task.access$714(GenUI34.GenerateUI34Task.this, paramPackageStats.cacheSize);
            Log.d("IQAgent", "GenUI34 PackageSizeReceiver for " + paramPackageStats.packageName + ", codeSize = " + Long.toString(paramPackageStats.codeSize) + ", dataSize = " + Long.toString(paramPackageStats.dataSize) + ", cacheSize = " + Long.toString(paramPackageStats.cacheSize));
            GenUI34.GenerateUI34Task.access$808(GenUI34.GenerateUI34Task.this);
          }
          return;
        }
        catch (RuntimeException paramPackageStats)
        {
          Log.w("IQAgent", "GenUI34 exception thrown in PackageSizeReceiver.onGetStatsCompleted() : " + paramPackageStats.toString());
          return;
        }
        catch (Exception paramPackageStats)
        {
          Log.w("IQAgent", "GenUI34 exception thrown in PackageSizeReceiver.onGetStatsCompleted() : " + paramPackageStats.toString());
          return;
        }
        finally
        {
          GenUI34.GenerateUI34Task.this.callbackComplete.signalAll();
          GenUI34.GenerateUI34Task.this.completionLock.unlock();
        }
      }
    }
  }
}
