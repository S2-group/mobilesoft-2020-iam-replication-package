package cmn;

import android.content.Context;
import java.util.concurrent.CountDownLatch;

final class l
  implements Runnable
{
  l(Context paramContext, CountDownLatch paramCountDownLatch) {}
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: invokestatic 28	cmn/k:a	()Z
    //   3: istore_1
    //   4: iload_1
    //   5: ifne +15 -> 20
    //   8: invokestatic 31	cmn/k:c	()Z
    //   11: pop
    //   12: aload_0
    //   13: getfield 16	cmn/l:b	Ljava/util/concurrent/CountDownLatch;
    //   16: invokevirtual 36	java/util/concurrent/CountDownLatch:countDown	()V
    //   19: return
    //   20: invokestatic 42	android/os/SystemClock:elapsedRealtime	()J
    //   23: lstore_2
    //   24: aload_0
    //   25: getfield 14	cmn/l:a	Landroid/content/Context;
    //   28: invokevirtual 48	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   31: astore 4
    //   33: aload 4
    //   35: iconst_0
    //   36: invokevirtual 54	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   39: astore 4
    //   41: aload 4
    //   43: invokeinterface 59 1 0
    //   48: ifeq +12 -> 60
    //   51: invokestatic 62	cmn/k:b	()Ljava/util/concurrent/atomic/AtomicReference;
    //   54: invokevirtual 68	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   57: ifnonnull +37 -> 94
    //   60: new 70	cmn/m
    //   63: dup
    //   64: iconst_0
    //   65: invokespecial 73	cmn/m:<init>	(B)V
    //   68: astore 5
    //   70: aload 5
    //   72: aload 4
    //   74: invokestatic 79	java/util/Collections:unmodifiableList	(Ljava/util/List;)Ljava/util/List;
    //   77: putfield 82	cmn/m:a	Ljava/util/List;
    //   80: aload 5
    //   82: lload_2
    //   83: putfield 85	cmn/m:b	J
    //   86: invokestatic 62	cmn/k:b	()Ljava/util/concurrent/atomic/AtomicReference;
    //   89: aload 5
    //   91: invokevirtual 89	java/util/concurrent/atomic/AtomicReference:set	(Ljava/lang/Object;)V
    //   94: invokestatic 31	cmn/k:c	()Z
    //   97: pop
    //   98: aload_0
    //   99: getfield 16	cmn/l:b	Ljava/util/concurrent/CountDownLatch;
    //   102: invokevirtual 36	java/util/concurrent/CountDownLatch:countDown	()V
    //   105: return
    //   106: astore 4
    //   108: aload 4
    //   110: invokevirtual 92	java/lang/Throwable:printStackTrace	()V
    //   113: invokestatic 96	java/util/Collections:emptyList	()Ljava/util/List;
    //   116: astore 4
    //   118: goto -77 -> 41
    //   121: astore 4
    //   123: invokestatic 31	cmn/k:c	()Z
    //   126: pop
    //   127: aload_0
    //   128: getfield 16	cmn/l:b	Ljava/util/concurrent/CountDownLatch;
    //   131: invokevirtual 36	java/util/concurrent/CountDownLatch:countDown	()V
    //   134: aload 4
    //   136: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	137	0	this	l
    //   3	2	1	bool	boolean
    //   23	60	2	l	long
    //   31	42	4	localObject1	Object
    //   106	3	4	localThrowable	Throwable
    //   116	1	4	localList	java.util.List
    //   121	14	4	localObject2	Object
    //   68	22	5	localM	m
    // Exception table:
    //   from	to	target	type
    //   33	41	106	java/lang/Throwable
    //   0	4	121	finally
    //   20	33	121	finally
    //   33	41	121	finally
    //   41	60	121	finally
    //   60	94	121	finally
    //   108	118	121	finally
  }
}
