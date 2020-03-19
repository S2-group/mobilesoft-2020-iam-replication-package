package com.cleanmaster.function.security.scan.engine;

import android.content.Context;
import android.os.RemoteException;

public class l
  extends Thread
{
  private Context a;
  private ISecurityScanCallback b;
  private byte[] c = new byte[0];
  private volatile boolean d;
  private long e;
  private long f;
  private volatile boolean g;
  private volatile boolean h;
  private a i;
  private e j = new m(this);
  private int k = 0;
  
  public l(Context paramContext, ISecurityScanCallback paramISecurityScanCallback, int paramInt)
  {
    super("SecurityScanThread");
    if ((paramContext == null) || (paramISecurityScanCallback == null)) {
      throw new IllegalArgumentException("The context or callback of the SecurityScanThread can't be null.");
    }
    this.k = paramInt;
    this.a = paramContext;
    this.b = paramISecurityScanCallback;
  }
  
  private void a(String paramString) {}
  
  private void b()
  {
    synchronized (this.c)
    {
      if (this.i != null)
      {
        this.i.a();
        this.i = null;
      }
      this.d = true;
      return;
    }
  }
  
  private void c()
  {
    synchronized (this.c)
    {
      if ((!this.d) && (!this.g) && (this.h)) {
        a("onScanDone : " + (System.currentTimeMillis() - this.e) + " ms");
      }
      try
      {
        if (this.b != null) {
          this.b.b();
        }
        this.g = true;
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
        }
      }
    }
  }
  
  public void a()
  {
    b();
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 32	com/cleanmaster/function/security/scan/engine/l:c	[B
    //   4: astore 4
    //   6: aload 4
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 60	com/cleanmaster/function/security/scan/engine/l:d	Z
    //   13: ifne +95 -> 108
    //   16: aload_0
    //   17: invokestatic 92	java/lang/System:currentTimeMillis	()J
    //   20: putfield 94	com/cleanmaster/function/security/scan/engine/l:e	J
    //   23: aload_0
    //   24: ldc 118
    //   26: invokespecial 57	com/cleanmaster/function/security/scan/engine/l:a	(Ljava/lang/String;)V
    //   29: aload_0
    //   30: getfield 41	com/cleanmaster/function/security/scan/engine/l:k	I
    //   33: istore_1
    //   34: iload_1
    //   35: iconst_3
    //   36: if_icmpne +19 -> 55
    //   39: aload_0
    //   40: getfield 50	com/cleanmaster/function/security/scan/engine/l:b	Lcom/cleanmaster/function/security/scan/engine/ISecurityScanCallback;
    //   43: ifnull +12 -> 55
    //   46: aload_0
    //   47: getfield 50	com/cleanmaster/function/security/scan/engine/l:b	Lcom/cleanmaster/function/security/scan/engine/ISecurityScanCallback;
    //   50: invokeinterface 119 1 0
    //   55: aconst_null
    //   56: astore_2
    //   57: aload_0
    //   58: getfield 48	com/cleanmaster/function/security/scan/engine/l:a	Landroid/content/Context;
    //   61: invokevirtual 125	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   64: iconst_0
    //   65: invokevirtual 131	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   68: astore_3
    //   69: aload_3
    //   70: astore_2
    //   71: aload_0
    //   72: getfield 41	com/cleanmaster/function/security/scan/engine/l:k	I
    //   75: iconst_2
    //   76: iand
    //   77: ifeq +31 -> 108
    //   80: aload_0
    //   81: new 69	com/cleanmaster/function/security/scan/engine/a
    //   84: dup
    //   85: aload_0
    //   86: getfield 48	com/cleanmaster/function/security/scan/engine/l:a	Landroid/content/Context;
    //   89: aload_0
    //   90: getfield 39	com/cleanmaster/function/security/scan/engine/l:j	Lcom/cleanmaster/function/security/scan/engine/e;
    //   93: aload_2
    //   94: iconst_1
    //   95: invokespecial 134	com/cleanmaster/function/security/scan/engine/a:<init>	(Landroid/content/Context;Lcom/cleanmaster/function/security/scan/engine/e;Ljava/util/List;Z)V
    //   98: putfield 67	com/cleanmaster/function/security/scan/engine/l:i	Lcom/cleanmaster/function/security/scan/engine/a;
    //   101: aload_0
    //   102: getfield 67	com/cleanmaster/function/security/scan/engine/l:i	Lcom/cleanmaster/function/security/scan/engine/a;
    //   105: invokevirtual 137	com/cleanmaster/function/security/scan/engine/a:start	()V
    //   108: aload 4
    //   110: monitorexit
    //   111: return
    //   112: astore_2
    //   113: aload_2
    //   114: invokevirtual 110	android/os/RemoteException:printStackTrace	()V
    //   117: goto -62 -> 55
    //   120: astore_2
    //   121: aload 4
    //   123: monitorexit
    //   124: aload_2
    //   125: athrow
    //   126: astore_3
    //   127: goto -56 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	this	l
    //   33	4	1	m	int
    //   56	38	2	localObject1	Object
    //   112	2	2	localRemoteException	RemoteException
    //   120	5	2	localObject2	Object
    //   68	2	3	localList	java.util.List
    //   126	1	3	localException	Exception
    //   4	118	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   39	55	112	android/os/RemoteException
    //   9	34	120	finally
    //   39	55	120	finally
    //   57	69	120	finally
    //   71	108	120	finally
    //   108	111	120	finally
    //   113	117	120	finally
    //   121	124	120	finally
    //   57	69	126	java/lang/Exception
  }
}
