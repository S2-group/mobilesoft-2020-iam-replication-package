package com.dsi.ant.plugins.antplus.pccbase;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.dsi.ant.plugins.antplus.pcc.defines.DeviceState;
import com.dsi.ant.plugins.antplus.pcc.defines.RequestAccessResult;
import com.dsi.ant.plugins.internal.pluginsipc.AntPluginDeviceDbProvider.DeviceDbDeviceInfo;
import com.dsi.ant.plugins.utility.log.LogAnt;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AntPluginPcc
{
  static volatile String N = "";
  static volatile String O = "";
  public static final String PATH_ANTPLUS_PLUGINS_PKG = "com.dsi.ant.plugins.antplus";
  private static final String a = "AntPluginPcc";
  ServiceConnection P;
  HandlerThread Q = new HandlerThread("PluginPCCMsgHandler");
  volatile Handler R;
  Handler.Callback S = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      Object localObject = AntPluginPcc.d();
      StringBuilder localStringBuilder = new StringBuilder("Plugin Msg Handler received: ");
      localStringBuilder.append(paramAnonymousMessage.what);
      localStringBuilder.append(", ");
      localStringBuilder.append(paramAnonymousMessage.arg1);
      LogAnt.v((String)localObject, localStringBuilder.toString());
      if (AntPluginPcc.c(AntPluginPcc.this).tryLock()) {
        try
        {
          AntPluginPcc.this.handleNonCmdPluginMessage(paramAnonymousMessage);
          return true;
        }
        finally
        {
          AntPluginPcc.c(AntPluginPcc.this).unlock();
        }
      }
      try
      {
        AntPluginPcc.this.Z.exchange(paramAnonymousMessage);
        AntPluginPcc.this.aa.await();
        return true;
      }
      catch (BrokenBarrierException localBrokenBarrierException)
      {
        for (;;) {}
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
      localObject = AntPluginPcc.this;
      localStringBuilder = new StringBuilder("InterruptedException in mPluginMsgHandler trying to fwd message ");
      localStringBuilder.append(paramAnonymousMessage.what);
      ((AntPluginPcc)localObject).a(localStringBuilder.toString());
      Thread.currentThread().interrupt();
      return true;
      localObject = AntPluginPcc.this;
      localStringBuilder = new StringBuilder("BrokenBarrierException in mPluginMsgHandler trying to fwd message ");
      localStringBuilder.append(paramAnonymousMessage.what);
      ((AntPluginPcc)localObject).a(localStringBuilder.toString());
      return true;
    }
  };
  HandlerThread T = new HandlerThread("PluginPCCEventHandler");
  volatile Handler U;
  Handler.Callback V = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      Object localObject2 = AntPluginPcc.this.mReleaseHandle;
      if (localObject2 == null) {
        return true;
      }
      try
      {
        AntPluginPcc.this.ac.await();
        synchronized (((PccReleaseHandle)localObject2).stateLock)
        {
          if (!((PccReleaseHandle)localObject2).isActive()) {
            return true;
          }
          int i = paramAnonymousMessage.what;
          if (i != 1)
          {
            if (i != 3)
            {
              localObject2 = AntPluginPcc.d();
              StringBuilder localStringBuilder = new StringBuilder("Unrecognized plugin event received: ");
              localStringBuilder.append(paramAnonymousMessage.arg1);
              LogAnt.w((String)localObject2, localStringBuilder.toString());
            }
            else
            {
              i = paramAnonymousMessage.arg1;
              AntPluginPcc.this.af = Integer.valueOf(i);
              paramAnonymousMessage = AntPluginPcc.d();
              localObject2 = new StringBuilder("State event: ");
              ((StringBuilder)localObject2).append(i);
              LogAnt.v(paramAnonymousMessage, ((StringBuilder)localObject2).toString());
              if (i == -100) {
                AntPluginPcc.this.a("Device dead");
              } else {
                AntPluginPcc.this.mStateChangeReceiver.onDeviceStateChange(DeviceState.getValueFromInt(i));
              }
            }
          }
          else {
            AntPluginPcc.this.handlePluginEvent(paramAnonymousMessage);
          }
          return true;
        }
      }
      catch (InterruptedException paramAnonymousMessage)
      {
        for (;;) {}
      }
      LogAnt.i(AntPluginPcc.d(), "Plugin event thread interrupted while waiting for initialization to complete.");
      Thread.currentThread().interrupt();
      return true;
    }
  };
  Messenger W;
  UUID X;
  Messenger Y;
  Exchanger<Message> Z = new Exchanger();
  CyclicBarrier aa = new CyclicBarrier(2);
  boolean ab = false;
  CountDownLatch ac = new CountDownLatch(1);
  Context ad;
  AntPluginDeviceDbProvider.DeviceDbDeviceInfo ae;
  Integer af = null;
  private final ReentrantLock b = new ReentrantLock();
  private Thread c;
  private boolean d = false;
  private final Object e = new Object();
  private boolean f = false;
  private Object g = new Object();
  protected volatile PccReleaseHandle<?> mReleaseHandle;
  protected IDeviceStateChangeReceiver mStateChangeReceiver;
  protected int reportedServiceVersion;
  protected boolean supportsRssiEvent;
  
  public AntPluginPcc()
  {
    this.T.start();
    this.U = new Handler(this.T.getLooper(), this.V);
    this.Q.start();
    this.R = new Handler(this.Q.getLooper(), this.S);
  }
  
  /* Error */
  private Message a(Message paramMessage)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_1
    //   8: getfield 179	android/os/Message:what	I
    //   11: istore_2
    //   12: aload_0
    //   13: invokestatic 185	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   16: putfield 187	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:c	Ljava/lang/Thread;
    //   19: aload_0
    //   20: getfield 189	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:Y	Landroid/os/Messenger;
    //   23: astore 4
    //   25: aload 4
    //   27: ifnull +438 -> 465
    //   30: aload_0
    //   31: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   34: ldc2_w 190
    //   37: getstatic 197	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   40: invokevirtual 201	java/util/concurrent/locks/ReentrantLock:tryLock	(JLjava/util/concurrent/TimeUnit;)Z
    //   43: ifne +11 -> 54
    //   46: new 170	java/util/concurrent/TimeoutException
    //   49: dup
    //   50: invokespecial 202	java/util/concurrent/TimeoutException:<init>	()V
    //   53: athrow
    //   54: aload_0
    //   55: getfield 189	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:Y	Landroid/os/Messenger;
    //   58: aload_1
    //   59: invokevirtual 208	android/os/Messenger:send	(Landroid/os/Message;)V
    //   62: aload_0
    //   63: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:Z	Ljava/util/concurrent/Exchanger;
    //   66: aconst_null
    //   67: ldc2_w 209
    //   70: getstatic 213	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   73: invokevirtual 217	java/util/concurrent/Exchanger:exchange	(Ljava/lang/Object;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    //   76: checkcast 176	android/os/Message
    //   79: astore_1
    //   80: aload_1
    //   81: getfield 179	android/os/Message:what	I
    //   84: iload_2
    //   85: if_icmpne +89 -> 174
    //   88: aload_1
    //   89: invokestatic 220	android/os/Message:obtain	(Landroid/os/Message;)Landroid/os/Message;
    //   92: astore_1
    //   93: aload_0
    //   94: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   97: invokevirtual 223	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   100: aload_0
    //   101: getfield 132	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:aa	Ljava/util/concurrent/CyclicBarrier;
    //   104: invokevirtual 227	java/util/concurrent/CyclicBarrier:await	()I
    //   107: pop
    //   108: aload_3
    //   109: monitorexit
    //   110: aload_1
    //   111: areturn
    //   112: new 229	java/lang/StringBuilder
    //   115: dup
    //   116: ldc -25
    //   118: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   121: astore_1
    //   122: aload_1
    //   123: iload_2
    //   124: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   127: pop
    //   128: aload_0
    //   129: aload_1
    //   130: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   136: invokestatic 185	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   139: invokevirtual 245	java/lang/Thread:interrupt	()V
    //   142: aload_3
    //   143: monitorexit
    //   144: aconst_null
    //   145: areturn
    //   146: new 229	java/lang/StringBuilder
    //   149: dup
    //   150: ldc -9
    //   152: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   155: astore_1
    //   156: aload_1
    //   157: iload_2
    //   158: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: aload_0
    //   163: aload_1
    //   164: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   170: aload_3
    //   171: monitorexit
    //   172: aconst_null
    //   173: areturn
    //   174: aload_0
    //   175: aload_1
    //   176: invokevirtual 250	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleNonCmdPluginMessage	(Landroid/os/Message;)V
    //   179: aload_0
    //   180: getfield 132	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:aa	Ljava/util/concurrent/CyclicBarrier;
    //   183: invokevirtual 227	java/util/concurrent/CyclicBarrier:await	()I
    //   186: pop
    //   187: goto -125 -> 62
    //   190: new 229	java/lang/StringBuilder
    //   193: dup
    //   194: ldc -4
    //   196: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   199: astore_1
    //   200: aload_1
    //   201: iload_2
    //   202: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   205: pop
    //   206: aload_0
    //   207: aload_1
    //   208: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   214: invokestatic 185	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   217: invokevirtual 245	java/lang/Thread:interrupt	()V
    //   220: aload_0
    //   221: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   224: invokevirtual 223	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   227: aload_3
    //   228: monitorexit
    //   229: aconst_null
    //   230: areturn
    //   231: new 229	java/lang/StringBuilder
    //   234: dup
    //   235: ldc -2
    //   237: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   240: astore_1
    //   241: aload_1
    //   242: iload_2
    //   243: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   246: pop
    //   247: aload_0
    //   248: aload_1
    //   249: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   252: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   255: aload_0
    //   256: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   259: invokevirtual 223	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   262: aload_3
    //   263: monitorexit
    //   264: aconst_null
    //   265: areturn
    //   266: new 229	java/lang/StringBuilder
    //   269: dup
    //   270: ldc_w 256
    //   273: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   276: astore_1
    //   277: aload_1
    //   278: iload_2
    //   279: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload_0
    //   284: aload_1
    //   285: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   288: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   291: aload_0
    //   292: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   295: invokevirtual 223	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   298: aload_3
    //   299: monitorexit
    //   300: aconst_null
    //   301: areturn
    //   302: new 229	java/lang/StringBuilder
    //   305: dup
    //   306: ldc_w 258
    //   309: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   312: astore_1
    //   313: aload_1
    //   314: iload_2
    //   315: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload_0
    //   320: aload_1
    //   321: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   327: invokestatic 185	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   330: invokevirtual 245	java/lang/Thread:interrupt	()V
    //   333: aload_0
    //   334: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   337: invokevirtual 223	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   340: aload_3
    //   341: monitorexit
    //   342: aconst_null
    //   343: areturn
    //   344: astore_1
    //   345: goto +47 -> 392
    //   348: new 229	java/lang/StringBuilder
    //   351: dup
    //   352: ldc_w 260
    //   355: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   358: astore_1
    //   359: aload_1
    //   360: iload_2
    //   361: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   364: pop
    //   365: aload_1
    //   366: ldc_w 262
    //   369: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: pop
    //   373: aload_0
    //   374: aload_1
    //   375: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   378: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   381: aload_0
    //   382: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   385: invokevirtual 223	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   388: aload_3
    //   389: monitorexit
    //   390: aconst_null
    //   391: areturn
    //   392: aload_0
    //   393: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:b	Ljava/util/concurrent/locks/ReentrantLock;
    //   396: invokevirtual 223	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   399: aload_1
    //   400: athrow
    //   401: new 229	java/lang/StringBuilder
    //   404: dup
    //   405: ldc_w 267
    //   408: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   411: astore_1
    //   412: aload_1
    //   413: iload_2
    //   414: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   417: pop
    //   418: aload_0
    //   419: aload_1
    //   420: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   423: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   426: aload_3
    //   427: monitorexit
    //   428: aconst_null
    //   429: areturn
    //   430: new 229	java/lang/StringBuilder
    //   433: dup
    //   434: ldc_w 269
    //   437: invokespecial 232	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   440: astore_1
    //   441: aload_1
    //   442: iload_2
    //   443: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   446: pop
    //   447: aload_0
    //   448: aload_1
    //   449: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   452: invokevirtual 242	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:a	(Ljava/lang/String;)V
    //   455: invokestatic 185	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   458: invokevirtual 245	java/lang/Thread:interrupt	()V
    //   461: aload_3
    //   462: monitorexit
    //   463: aconst_null
    //   464: areturn
    //   465: aload_3
    //   466: monitorexit
    //   467: aconst_null
    //   468: areturn
    //   469: astore_1
    //   470: aload_3
    //   471: monitorexit
    //   472: aload_1
    //   473: athrow
    //   474: astore_1
    //   475: goto -45 -> 430
    //   478: astore_1
    //   479: goto -78 -> 401
    //   482: astore_1
    //   483: goto -135 -> 348
    //   486: astore_1
    //   487: goto -185 -> 302
    //   490: astore_1
    //   491: goto -225 -> 266
    //   494: astore_1
    //   495: goto -349 -> 146
    //   498: astore_1
    //   499: goto -387 -> 112
    //   502: astore_1
    //   503: goto -272 -> 231
    //   506: astore_1
    //   507: goto -317 -> 190
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	510	0	this	AntPluginPcc
    //   0	510	1	paramMessage	Message
    //   11	432	2	i	int
    //   4	467	3	localReentrantLock	ReentrantLock
    //   23	3	4	localMessenger	Messenger
    // Exception table:
    //   from	to	target	type
    //   54	62	344	finally
    //   62	80	344	finally
    //   80	93	344	finally
    //   174	179	344	finally
    //   179	187	344	finally
    //   190	220	344	finally
    //   231	255	344	finally
    //   266	291	344	finally
    //   302	333	344	finally
    //   348	381	344	finally
    //   7	25	469	finally
    //   30	54	469	finally
    //   93	100	469	finally
    //   100	108	469	finally
    //   108	110	469	finally
    //   112	144	469	finally
    //   146	172	469	finally
    //   220	229	469	finally
    //   255	264	469	finally
    //   291	300	469	finally
    //   333	342	469	finally
    //   381	390	469	finally
    //   392	401	469	finally
    //   401	428	469	finally
    //   430	463	469	finally
    //   465	467	469	finally
    //   470	472	469	finally
    //   30	54	474	java/lang/InterruptedException
    //   30	54	478	java/util/concurrent/TimeoutException
    //   54	62	482	android/os/RemoteException
    //   62	80	482	android/os/RemoteException
    //   80	93	482	android/os/RemoteException
    //   174	179	482	android/os/RemoteException
    //   179	187	482	android/os/RemoteException
    //   190	220	482	android/os/RemoteException
    //   231	255	482	android/os/RemoteException
    //   266	291	482	android/os/RemoteException
    //   302	333	482	android/os/RemoteException
    //   62	80	486	java/lang/InterruptedException
    //   62	80	490	java/util/concurrent/TimeoutException
    //   100	108	494	java/util/concurrent/BrokenBarrierException
    //   100	108	498	java/lang/InterruptedException
    //   179	187	502	java/util/concurrent/BrokenBarrierException
    //   179	187	506	java/lang/InterruptedException
  }
  
  private Messenger a()
  {
    return new Messenger(this.R);
  }
  
  private void a(int paramInt)
  {
    try
    {
      synchronized (this.b)
      {
        if (this.Y == null) {
          break label75;
        }
        this.Y.send(createCmdMsg(paramInt, null));
      }
    }
    catch (RemoteException localRemoteException)
    {
      String str;
      StringBuilder localStringBuilder;
      label75:
      for (;;) {}
    }
    str = a;
    localStringBuilder = new StringBuilder("RemoteException, unable to cleanly release (cmd ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(")");
    LogAnt.e(str, localStringBuilder.toString());
    return;
    throw str;
  }
  
  private void a(Intent paramIntent, Bundle paramBundle)
  {
    synchronized (this.g)
    {
      if (!this.f)
      {
        this.f = true;
        if (!this.ad.bindService(paramIntent, this.P, 1))
        {
          LogAnt.e(a, "Binding to plugin failed");
          a(paramBundle);
        }
      }
      return;
    }
  }
  
  private void a(Bundle paramBundle)
  {
    closePluginConnection();
    paramBundle = (Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver");
    Message localMessage = Message.obtain();
    localMessage.what = -4;
    try
    {
      paramBundle.send(localMessage);
      return;
    }
    catch (RemoteException paramBundle)
    {
      for (;;) {}
    }
    LogAnt.e(a, "Remote exception sending failure msg to client");
  }
  
  private void a(Messenger paramMessenger, String paramString1, String paramString2)
  {
    Message localMessage = Message.obtain();
    localMessage.what = -5;
    Bundle localBundle = new Bundle();
    localBundle.putString("string_DependencyPackageName", paramString1);
    localBundle.putString("string_DependencyName", paramString2);
    localMessage.setData(localBundle);
    try
    {
      paramMessenger.send(localMessage);
      return;
    }
    catch (RemoteException paramMessenger)
    {
      for (;;) {}
    }
    a("Remote exception sending plugin 'dependency not installed' msg to client");
  }
  
  private void e()
  {
    synchronized (this.g)
    {
      boolean bool = this.f;
      if (bool)
      {
        try
        {
          this.ad.unbindService(this.P);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          String str = a;
          StringBuilder localStringBuilder = new StringBuilder("Unexpected error unbinding service, ");
          localStringBuilder.append(localIllegalArgumentException);
          LogAnt.e(str, localStringBuilder.toString());
        }
        this.f = false;
      }
      return;
    }
  }
  
  public static int getInstalledPluginsVersionNumber(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals("com.dsi.ant.plugins.antplus"))
      {
        if (!localPackageInfo.applicationInfo.enabled) {
          return -2;
        }
        return localPackageInfo.versionCode;
      }
    }
    return -1;
  }
  
  public static String getInstalledPluginsVersionString(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals("com.dsi.ant.plugins.antplus")) {
        return localPackageInfo.versionName;
      }
    }
    return null;
  }
  
  public static String getMissingDependencyName()
  {
    return O;
  }
  
  public static String getMissingDependencyPackageName()
  {
    return N;
  }
  
  protected static <T extends AntPluginPcc> AsyncScanController<T> requestAccess_Helper_AsyncScanController(Context paramContext, int paramInt, T paramT, AsyncScanController.IAsyncScanResultReceiver paramIAsyncScanResultReceiver)
  {
    paramIAsyncScanResultReceiver = new AsyncScanController(paramIAsyncScanResultReceiver, paramT);
    return requestAsyncScan_Helper_SubMain(paramContext, paramInt, new Bundle(), paramT, paramIAsyncScanResultReceiver);
  }
  
  protected static <T extends AntPluginPcc> PccReleaseHandle<T> requestAccess_Helper_AsyncSearchByDevNumber(Context paramContext, int paramInt1, int paramInt2, T paramT, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 3);
    localBundle.putInt("int_AntDeviceID", paramInt1);
    localBundle.putInt("int_ProximityBin", paramInt2);
    return requestAccess_Helper_Main(paramContext, localBundle, paramT, new RequestAccessResultHandler_AsyncSearchByDevNumber(), paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
  }
  
  protected static <T extends AntPluginPcc> PccReleaseHandle<T> requestAccess_Helper_Main(Context paramContext, Bundle paramBundle, T paramT, RequestAccessResultHandler<T> paramRequestAccessResultHandler, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    if ((paramIPluginAccessResultReceiver != null) && (paramIDeviceStateChangeReceiver != null))
    {
      paramT.getClass();
      paramIPluginAccessResultReceiver = new StandardReleaseHandle(paramT, paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
      paramT.mReleaseHandle = paramIPluginAccessResultReceiver;
      paramT.mStateChangeReceiver = paramIPluginAccessResultReceiver.stateSink;
      paramRequestAccessResultHandler.setReturnInfo(paramT, paramIPluginAccessResultReceiver.resultSink);
      requestAccess_Helper_SubMain(paramContext, paramBundle, paramT, paramRequestAccessResultHandler);
      return paramIPluginAccessResultReceiver;
    }
    paramBundle = new StringBuilder("Invalid argument: ");
    if (paramIPluginAccessResultReceiver == null) {
      paramContext = "resultReceiver ";
    } else {
      paramContext = "stateReceiver ";
    }
    paramBundle.append(paramContext);
    paramBundle.append(" is null ");
    throw new IllegalArgumentException(paramBundle.toString());
  }
  
  protected static <T extends AntPluginPcc> PccReleaseHandle<T> requestAccess_Helper_SearchActivity(Activity paramActivity, Context paramContext, boolean paramBoolean, int paramInt, T paramT, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 1);
    localBundle.putBoolean("b_ForceManualSelect", paramBoolean);
    localBundle.putInt("int_ProximityBin", paramInt);
    return requestAccess_Helper_Main(paramContext, localBundle, paramT, new RequestAccessResultHandler_UI(paramActivity), paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
  }
  
  protected static <T extends AntPluginPcc> void requestAccess_Helper_SubMain(Context paramContext, Bundle paramBundle, T paramT, Handler paramHandler)
  {
    if (paramHandler == null) {
      throw new IllegalArgumentException("resultHandler passed from client was null");
    }
    String str = paramContext.getPackageName();
    Object localObject = paramContext.getApplicationInfo();
    localObject = paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject).toString();
    paramBundle.putString("str_ApplicationNamePackage", str);
    paramBundle.putString("str_ApplicationNameTitle", (String)localObject);
    if (!paramBundle.containsKey("int_RssiMode")) {
      paramBundle.putInt("int_RssiMode", 1);
    }
    paramT.bindAndRequest(paramContext, paramBundle, paramHandler);
  }
  
  protected static <T extends AntPluginPcc> AsyncScanController<T> requestAsyncScan_Helper_SubMain(Context paramContext, int paramInt, Bundle paramBundle, T paramT, AsyncScanController<T> paramAsyncScanController)
  {
    if (getInstalledPluginsVersionNumber(paramContext) < 10800)
    {
      LogAnt.e(a, "Binding to plugin failed, version requirement not met for async scan controller mode");
      N = paramT.getServiceBindIntent().getComponent().getPackageName();
      paramContext = new StringBuilder();
      paramContext.append(paramT.getPluginPrintableName());
      paramContext.append(" minimum v.10800");
      O = paramContext.toString();
      paramAsyncScanController.scanResultReceiver.onSearchStopped(RequestAccessResult.DEPENDENCY_NOT_INSTALLED);
      return null;
    }
    paramBundle.putInt("int_RequestAccessMode", 2);
    paramBundle.putInt("int_ProximityBin", paramInt);
    requestAccess_Helper_SubMain(paramContext, paramBundle, paramT, paramAsyncScanController.getScanResultHandler());
    return paramAsyncScanController;
  }
  
  public static boolean startPluginManagerActivity(Activity paramActivity)
  {
    if (getInstalledPluginsVersionNumber(paramActivity) > 0)
    {
      Intent localIntent = new Intent();
      localIntent.setClassName("com.dsi.ant.plugins.antplus", "com.dsi.ant.plugins.antplus.utility.db.Activity_PluginMgrDashboard");
      paramActivity.startActivity(localIntent);
      return true;
    }
    return false;
  }
  
  <T extends AntPluginPcc> void a(AsyncScanController.AsyncScanResultDeviceInfo paramAsyncScanResultDeviceInfo, Messenger paramMessenger, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    this.mStateChangeReceiver = paramIDeviceStateChangeReceiver;
    paramIDeviceStateChangeReceiver = new Bundle();
    paramIDeviceStateChangeReceiver.putParcelable("parcelable_AsyncScanResultDeviceInfo", paramAsyncScanResultDeviceInfo);
    paramIDeviceStateChangeReceiver.putParcelable("msgr_ReqAccResultReceiver", paramMessenger);
    paramAsyncScanResultDeviceInfo = sendPluginCommand(10100, paramIDeviceStateChangeReceiver);
    if (paramAsyncScanResultDeviceInfo == null)
    {
      LogAnt.e(a, "connectToAsyncResult died in sendPluginCommand()");
      paramAsyncScanResultDeviceInfo = Message.obtain();
      paramAsyncScanResultDeviceInfo.what = -4;
    }
    try
    {
      paramMessenger.send(paramAsyncScanResultDeviceInfo);
      return;
    }
    catch (RemoteException paramAsyncScanResultDeviceInfo)
    {
      for (;;) {}
    }
    a("Remote exception sending async connect failure msg to client");
    return;
    if (paramAsyncScanResultDeviceInfo.arg1 != 0)
    {
      paramMessenger = new StringBuilder("Request to connectToAsync Result cmd failed with code ");
      paramMessenger.append(paramAsyncScanResultDeviceInfo.arg1);
      throw new RuntimeException(paramMessenger.toString());
    }
    paramAsyncScanResultDeviceInfo.recycle();
  }
  
  void a(AntPluginDeviceDbProvider.DeviceDbDeviceInfo paramDeviceDbDeviceInfo, UUID paramUUID, Messenger paramMessenger, int paramInt1, int paramInt2)
  {
    this.ae = paramDeviceDbDeviceInfo;
    this.X = paramUUID;
    this.Y = paramMessenger;
    this.reportedServiceVersion = paramInt2;
    if (this.af == null) {
      this.af = Integer.valueOf(paramInt1);
    }
    this.ab = true;
  }
  
  void a(String paramString)
  {
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder("ConnectionDied: ");
    localStringBuilder.append(paramString);
    LogAnt.w(str, localStringBuilder.toString());
    if (this.mReleaseHandle != null)
    {
      if (this.mReleaseHandle.isClosed) {
        return;
      }
      c();
      this.mStateChangeReceiver.onDeviceStateChange(DeviceState.DEAD);
      return;
    }
  }
  
  void b()
  {
    a(10101);
  }
  
  protected void bindAndRequest(Context paramContext, final Bundle paramBundle, Handler paramHandler)
  {
    this.ad = paramContext;
    paramContext = new Messenger(paramHandler);
    paramBundle.putParcelable("msgr_PluginMsgHandler", a());
    paramBundle.putParcelable("msgr_ReqAccResultReceiver", paramContext);
    LogAnt.setVersion("BBD30500");
    try
    {
      LogAnt.getDebugLevel(this.ad.createPackageContext("com.dsi.ant.plugins.antplus", 4));
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramHandler = a;
      localObject = new StringBuilder("Unable to configure logging, plugins package not found: ");
      ((StringBuilder)localObject).append(paramContext);
      LogAnt.e(paramHandler, ((StringBuilder)localObject).toString());
    }
    paramBundle.putInt("int_PluginLibVersion", 30500);
    paramBundle.putString("string_PluginLibVersion", "3.5.0");
    paramBundle.putInt("more", 1);
    Object localObject = getServiceBindIntent();
    paramHandler = null;
    Iterator localIterator = this.ad.getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      paramContext = paramHandler;
      if (!localIterator.hasNext()) {
        break;
      }
      paramContext = (PackageInfo)localIterator.next();
    } while (!paramContext.packageName.equals(((Intent)localObject).getComponent().getPackageName()));
    if (paramContext == null)
    {
      LogAnt.e(a, "Binding to plugin failed, not installed");
      a((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), ((Intent)localObject).getComponent().getPackageName(), "ANT+ Plugins Service");
      return;
    }
    if (paramContext.versionCode < getRequiredServiceVersionForBind())
    {
      LogAnt.e(a, "Binding to plugin failed, version requirement not met");
      paramContext = (Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver");
      paramBundle = ((Intent)localObject).getComponent().getPackageName();
      paramHandler = new StringBuilder("ANT+ Plugins Service minimum v.");
      paramHandler.append(getRequiredServiceVersionForBind());
      a(paramContext, paramBundle, paramHandler.toString());
      return;
    }
    this.P = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName arg1, IBinder paramAnonymousIBinder)
      {
        synchronized (AntPluginPcc.a(AntPluginPcc.this))
        {
          if (AntPluginPcc.b(AntPluginPcc.this)) {
            return;
          }
          AntPluginPcc.this.W = new Messenger(paramAnonymousIBinder);
          paramAnonymousIBinder = Message.obtain();
          paramAnonymousIBinder.what = 0;
          paramAnonymousIBinder.setData(paramBundle);
          try
          {
            AntPluginPcc.this.W.send(paramAnonymousIBinder);
          }
          catch (RemoteException paramAnonymousIBinder)
          {
            for (;;) {}
          }
          AntPluginPcc.a(AntPluginPcc.this, paramBundle);
          return;
        }
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        if (!AntPluginPcc.this.ab)
        {
          AntPluginPcc.a(AntPluginPcc.this, paramBundle);
          return;
        }
        AntPluginPcc.this.a("OnServiceDisconnected fired");
      }
    };
    a((Intent)localObject, paramBundle);
  }
  
  void c()
  {
    synchronized (this.b)
    {
      this.af = Integer.valueOf(-100);
      try
      {
        a(10002);
        closePluginConnection();
        return;
      }
      finally
      {
        localObject1 = finally;
        closePluginConnection();
        throw localObject1;
      }
    }
  }
  
  protected void closePluginConnection()
  {
    Message localMessage;
    synchronized (this.e)
    {
      if (this.d) {
        return;
      }
      this.d = true;
      ??? = this.W;
      if (??? != null)
      {
        ??? = new CountDownLatch(1);
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("msgr_PluginMsgHandler", a());
        localBundle.putParcelable("msgr_ReqAccResultReceiver", new Messenger(new Handler(this.Q.getLooper())
        {
          public void handleMessage(Message paramAnonymousMessage)
          {
            this.a.countDown();
          }
        }));
        localMessage = Message.obtain();
        localMessage.what = 1;
        localMessage.setData(localBundle);
      }
    }
    try
    {
      ((Messenger)???).send(localMessage);
      ((CountDownLatch)???).await(500L, TimeUnit.MILLISECONDS);
      this.Q.quit();
      try
      {
        this.Q.join(1000L);
      }
      catch (InterruptedException localInterruptedException1)
      {
        for (;;) {}
      }
      LogAnt.e(a, "Plugin Msg Handler thread failed to shut down cleanly, InterruptedException");
      Thread.currentThread().interrupt();
      this.U = null;
      this.T.quit();
      try
      {
        this.T.join(1000L);
      }
      catch (InterruptedException localInterruptedException2)
      {
        for (;;) {}
      }
      LogAnt.e(a, "Plugin Event Handler thread failed to shut down cleanly, InterruptedException");
      Thread.currentThread().interrupt();
      e();
      if (this.b.tryLock()) {
        this.b.unlock();
      } else {
        this.c.interrupt();
      }
      synchronized (this.b)
      {
        this.Y = null;
        return;
      }
      localObject2 = finally;
      throw localObject2;
    }
    catch (RemoteException|InterruptedException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  protected Message createCmdMsg(int paramInt, Bundle paramBundle)
  {
    Message localMessage = Message.obtain();
    localMessage.what = paramInt;
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putSerializable("uuid_AccessToken", this.X);
    localMessage.setData(localBundle);
    return localMessage;
  }
  
  public int getAntDeviceNumber()
  {
    return this.ae.antDeviceNumber.intValue();
  }
  
  public DeviceState getCurrentDeviceState()
  {
    return DeviceState.getValueFromInt(this.af.intValue());
  }
  
  public String getDeviceName()
  {
    return this.ae.visibleName;
  }
  
  public abstract String getPluginPrintableName();
  
  public abstract int getRequiredServiceVersionForBind();
  
  public abstract Intent getServiceBindIntent();
  
  protected void handleNonCmdPluginMessage(Message paramMessage)
  {
    Handler localHandler = this.U;
    if (localHandler != null)
    {
      Message localMessage = localHandler.obtainMessage(paramMessage.what, paramMessage.arg1, paramMessage.arg2, paramMessage.obj);
      localMessage.setData(paramMessage.getData());
      localMessage.replyTo = paramMessage.replyTo;
      localHandler.sendMessage(localMessage);
    }
  }
  
  public abstract void handlePluginEvent(Message paramMessage);
  
  public boolean isUserPreferredDeviceForPlugin()
  {
    return this.ae.isPreferredDevice.booleanValue() == true;
  }
  
  public boolean isUserRecognizedDevice()
  {
    return this.ae.device_dbId != null;
  }
  
  public void releaseAccess()
  {
    this.mReleaseHandle.close();
  }
  
  protected Message sendPluginCommand(int paramInt, Bundle paramBundle)
  {
    return a(createCmdMsg(paramInt, paramBundle));
  }
  
  protected Message sendPluginCommand(Message paramMessage)
  {
    Bundle localBundle2 = paramMessage.getData();
    Bundle localBundle1 = localBundle2;
    if (localBundle2 == null)
    {
      localBundle1 = new Bundle();
      paramMessage.setData(localBundle1);
    }
    localBundle1.putSerializable("uuid_AccessToken", this.X);
    return a(paramMessage);
  }
  
  protected boolean subscribeToEvent(int paramInt)
  {
    Message localMessage = createCmdMsg(10000, null);
    localMessage.arg1 = paramInt;
    localMessage = sendPluginCommand(localMessage);
    if (localMessage == null)
    {
      LogAnt.e(a, "subscribeToEvent died in sendPluginCommand()");
      return false;
    }
    if (localMessage.arg1 != 0)
    {
      String str = a;
      StringBuilder localStringBuilder = new StringBuilder("Subscribing to event ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" failed with code ");
      localStringBuilder.append(localMessage.arg1);
      LogAnt.e(str, localStringBuilder.toString());
      localMessage.recycle();
      return false;
    }
    localMessage.recycle();
    return true;
  }
  
  protected void unsubscribeFromEvent(int paramInt)
  {
    Message localMessage = createCmdMsg(10001, null);
    localMessage.arg1 = paramInt;
    Object localObject = sendPluginCommand(localMessage);
    if (localObject == null)
    {
      LogAnt.e(a, "unsubscribeFromEvent died in sendPluginCommand()");
      return;
    }
    if (((Message)localObject).arg1 != 0)
    {
      localObject = new StringBuilder("Unsubscribing to event ");
      ((StringBuilder)localObject).append(paramInt);
      ((StringBuilder)localObject).append(" failed with code ");
      ((StringBuilder)localObject).append(localMessage.arg1);
      throw new RuntimeException(((StringBuilder)localObject).toString());
    }
    ((Message)localObject).recycle();
  }
  
  public static abstract interface IDeviceStateChangeReceiver
  {
    public abstract void onDeviceStateChange(DeviceState paramDeviceState);
  }
  
  public static abstract interface IPluginAccessResultReceiver<T extends AntPluginPcc>
  {
    public abstract void onResultReceived(T paramT, RequestAccessResult paramRequestAccessResult, DeviceState paramDeviceState);
  }
  
  protected static class RequestAccessResultHandler<T extends AntPluginPcc>
    extends Handler
  {
    protected AntPluginPcc.IPluginAccessResultReceiver<T> resultReceiver;
    protected T retPccObject;
    
    public RequestAccessResultHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      Object localObject = AntPluginPcc.d();
      StringBuilder localStringBuilder = new StringBuilder("ReqAcc Handler received: ");
      localStringBuilder.append(paramMessage.what);
      LogAnt.v((String)localObject, localStringBuilder.toString());
      paramMessage.getData().setClassLoader(getClass().getClassLoader());
      if (!handleRequestAccessResult(paramMessage))
      {
        localObject = RequestAccessResult.getValueFromInt(paramMessage.what);
        if (localObject == RequestAccessResult.UNRECOGNIZED)
        {
          localStringBuilder = new StringBuilder("Unrecognized return code (need app lib upgrade): ");
          localStringBuilder.append(paramMessage.what);
          localStringBuilder.append("!!!");
          handleRequestAccessFailed(localStringBuilder.toString(), (RequestAccessResult)localObject);
          return;
        }
        handleRequestAccessFailed(((RequestAccessResult)localObject).toString(), (RequestAccessResult)localObject);
      }
    }
    
    public void handleRequestAccessFailed(String paramString, RequestAccessResult paramRequestAccessResult)
    {
      String str = AntPluginPcc.d();
      StringBuilder localStringBuilder = new StringBuilder("RequestAccess failed: ");
      localStringBuilder.append(paramString);
      LogAnt.w(str, localStringBuilder.toString());
      this.retPccObject.c();
      this.resultReceiver.onResultReceived(null, paramRequestAccessResult, DeviceState.DEAD);
    }
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      int i = paramMessage.what;
      if (i != -5)
      {
        if (i != 0) {
          return false;
        }
        Bundle localBundle = paramMessage.getData();
        int j = localBundle.getInt("int_ServiceVersion", 0);
        Messenger localMessenger = (Messenger)localBundle.getParcelable("msgr_PluginComm");
        UUID localUUID = (UUID)localBundle.get("uuid_AccessToken");
        int k = localBundle.getInt("int_InitialDeviceStateCode");
        AntPluginDeviceDbProvider.DeviceDbDeviceInfo localDeviceDbDeviceInfo = (AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localBundle.getParcelable("parcelable_DeviceDbInfo");
        paramMessage = localDeviceDbDeviceInfo;
        if (localDeviceDbDeviceInfo == null)
        {
          paramMessage = new AntPluginDeviceDbProvider.DeviceDbDeviceInfo(0);
          paramMessage.antDeviceNumber = Integer.valueOf(localBundle.getInt("int_AntDeviceID", -1));
          paramMessage.visibleName = localBundle.getString("str_DeviceName");
          paramMessage.isPreferredDevice = Boolean.valueOf(false);
        }
        this.retPccObject.supportsRssiEvent = localBundle.getBoolean("bool_RssiSupport", false);
        this.retPccObject.a(paramMessage, localUUID, localMessenger, k, j);
        this.resultReceiver.onResultReceived(this.retPccObject, RequestAccessResult.getValueFromInt(i), DeviceState.getValueFromInt(k));
        this.retPccObject.ac.countDown();
        return true;
      }
      paramMessage = paramMessage.getData();
      AntPluginPcc.N = paramMessage.getString("string_DependencyPackageName");
      AntPluginPcc.O = paramMessage.getString("string_DependencyName");
      paramMessage = new StringBuilder("Missing Dependency: ");
      paramMessage.append(AntPluginPcc.N);
      paramMessage.append(" not installed.");
      handleRequestAccessFailed(paramMessage.toString(), RequestAccessResult.DEPENDENCY_NOT_INSTALLED);
      return true;
    }
    
    protected void setReturnInfo(T paramT, AntPluginPcc.IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver)
    {
      this.retPccObject = paramT;
      this.resultReceiver = paramIPluginAccessResultReceiver;
    }
  }
  
  protected static class RequestAccessResultHandler_AsyncSearchByDevNumber<T extends AntPluginPcc>
    extends AntPluginPcc.RequestAccessResultHandler<T>
  {
    protected RequestAccessResultHandler_AsyncSearchByDevNumber() {}
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      if (paramMessage.what == -7)
      {
        handleRequestAccessFailed("Search for device timed out.", RequestAccessResult.SEARCH_TIMEOUT);
        return true;
      }
      return super.handleRequestAccessResult(paramMessage);
    }
  }
  
  protected static class RequestAccessResultHandler_UI<T extends AntPluginPcc>
    extends AntPluginPcc.RequestAccessResultHandler<T>
  {
    private Activity a;
    
    public RequestAccessResultHandler_UI(Activity paramActivity)
    {
      this.a = paramActivity;
    }
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        paramMessage = (Intent)paramMessage.getData().getParcelable("intent_ActivityToLaunch");
        if (!this.retPccObject.mReleaseHandle.isClosed) {
          this.a.startActivity(paramMessage);
        }
        return true;
      }
      return super.handleRequestAccessResult(paramMessage);
    }
  }
  
  protected final class StandardReleaseHandle<T extends AntPluginPcc>
    extends PccReleaseHandle<T>
  {
    protected StandardReleaseHandle(AntPluginPcc.IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
    {
      super(localIDeviceStateChangeReceiver);
    }
    
    protected final void requestCancelled()
    {
      AntPluginPcc.this.closePluginConnection();
    }
  }
}
