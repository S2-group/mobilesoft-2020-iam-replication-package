package com.paytm.analytics.schedulers.b;

import com.evernote.android.job.c;

public class a
  extends c
{
  private final int a = 10;
  
  public a() {}
  
  /* Error */
  @android.support.annotation.NonNull
  protected com.evernote.android.job.c.b a(com.evernote.android.job.c.a paramA)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 25	com/evernote/android/job/c$a:d	()I
    //   4: bipush 10
    //   6: if_icmplt +16 -> 22
    //   9: ldc 27
    //   11: iconst_0
    //   12: anewarray 29	java/lang/Object
    //   15: invokestatic 34	timber/log/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   18: getstatic 40	com/evernote/android/job/c$b:FAILURE	Lcom/evernote/android/job/c$b;
    //   21: areturn
    //   22: ldc 42
    //   24: iconst_0
    //   25: anewarray 29	java/lang/Object
    //   28: invokestatic 45	timber/log/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   31: invokestatic 51	com/paytm/analytics/e:e	()Z
    //   34: ifeq +16 -> 50
    //   37: ldc 53
    //   39: iconst_0
    //   40: anewarray 29	java/lang/Object
    //   43: invokestatic 34	timber/log/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   46: getstatic 40	com/evernote/android/job/c$b:FAILURE	Lcom/evernote/android/job/c$b;
    //   49: areturn
    //   50: aload_0
    //   51: invokevirtual 57	com/paytm/analytics/schedulers/b/a:i	()Landroid/content/Context;
    //   54: invokevirtual 63	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   57: sipush 128
    //   60: invokevirtual 69	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   63: astore_2
    //   64: new 71	java/util/ArrayList
    //   67: dup
    //   68: invokespecial 72	java/util/ArrayList:<init>	()V
    //   71: astore_1
    //   72: aload_2
    //   73: invokeinterface 78 1 0
    //   78: astore_2
    //   79: aload_2
    //   80: invokeinterface 83 1 0
    //   85: ifeq +49 -> 134
    //   88: aload_2
    //   89: invokeinterface 87 1 0
    //   94: checkcast 89	android/content/pm/ApplicationInfo
    //   97: astore_3
    //   98: aload_3
    //   99: getfield 92	android/content/pm/ApplicationInfo:flags	I
    //   102: iconst_1
    //   103: iand
    //   104: ifne -25 -> 79
    //   107: aload_1
    //   108: aload_3
    //   109: getfield 96	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   112: invokeinterface 100 2 0
    //   117: pop
    //   118: goto -39 -> 79
    //   121: astore_1
    //   122: aload_1
    //   123: invokestatic 105	com/google/b/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   126: aload_1
    //   127: invokestatic 107	timber/log/a:b	(Ljava/lang/Throwable;)V
    //   130: getstatic 40	com/evernote/android/job/c$b:FAILURE	Lcom/evernote/android/job/c$b;
    //   133: areturn
    //   134: new 109	java/util/HashMap
    //   137: dup
    //   138: invokespecial 110	java/util/HashMap:<init>	()V
    //   141: astore_2
    //   142: aload_2
    //   143: ldc 112
    //   145: aload_1
    //   146: invokeinterface 118 3 0
    //   151: pop
    //   152: invokestatic 121	com/paytm/analytics/e:a	()Lcom/paytm/analytics/e;
    //   155: invokevirtual 124	com/paytm/analytics/e:b	()Lcom/paytm/analytics/a;
    //   158: new 126	com/paytm/analytics/c/a/c$a
    //   161: dup
    //   162: ldc -128
    //   164: invokespecial 131	com/paytm/analytics/c/a/c$a:<init>	(Ljava/lang/String;)V
    //   167: aload_2
    //   168: invokevirtual 134	com/paytm/analytics/c/a/c$a:a	(Ljava/lang/Object;)Lcom/paytm/analytics/c/a/c$a;
    //   171: invokevirtual 137	com/paytm/analytics/c/a/c$a:a	()Lcom/paytm/analytics/c/a/c;
    //   174: invokeinterface 142 2 0
    //   179: ldc -112
    //   181: iconst_0
    //   182: anewarray 29	java/lang/Object
    //   185: invokestatic 45	timber/log/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   188: getstatic 147	com/evernote/android/job/c$b:SUCCESS	Lcom/evernote/android/job/c$b;
    //   191: astore_1
    //   192: aload_1
    //   193: areturn
    //   194: astore_1
    //   195: aload_1
    //   196: invokestatic 105	com/google/b/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   199: aload_1
    //   200: invokestatic 107	timber/log/a:b	(Ljava/lang/Throwable;)V
    //   203: goto -73 -> 130
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	a
    //   0	206	1	paramA	com.evernote.android.job.c.a
    //   63	105	2	localObject	Object
    //   97	12	3	localApplicationInfo	android.content.pm.ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   0	22	121	com/paytm/analytics/a/a/a
    //   22	50	121	com/paytm/analytics/a/a/a
    //   50	79	121	com/paytm/analytics/a/a/a
    //   79	118	121	com/paytm/analytics/a/a/a
    //   134	192	121	com/paytm/analytics/a/a/a
    //   0	22	194	java/lang/Exception
    //   22	50	194	java/lang/Exception
    //   50	79	194	java/lang/Exception
    //   79	118	194	java/lang/Exception
    //   134	192	194	java/lang/Exception
  }
  
  protected void a(int paramInt)
  {
    timber.log.a.a("Job rescheduled with JobId: " + paramInt, new Object[0]);
  }
}
