package android.support.v7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.baloota.dumpster.logger.a;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class cn
  extends cl
{
  private Context a = null;
  private boolean b = false;
  private final Object c = new Object();
  private ConcurrentHashMap<String, cm> d = null;
  private BroadcastReceiver e = new BroadcastReceiver()
  {
    /* Error */
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      // Byte code:
      //   0: aload_2
      //   1: invokevirtual 28	android/content/Intent:getData	()Landroid/net/Uri;
      //   4: ifnull +459 -> 463
      //   7: aload_2
      //   8: invokevirtual 28	android/content/Intent:getData	()Landroid/net/Uri;
      //   11: invokevirtual 34	android/net/Uri:getSchemeSpecificPart	()Ljava/lang/String;
      //   14: astore 4
      //   16: aload 4
      //   18: ifnull +444 -> 462
      //   21: ldc 36
      //   23: aload_2
      //   24: invokevirtual 39	android/content/Intent:getAction	()Ljava/lang/String;
      //   27: invokevirtual 45	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   30: ifeq +233 -> 263
      //   33: aload_0
      //   34: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   37: invokestatic 48	android/support/v7/cn:a	(Landroid/support/v7/cn;)Ljava/lang/Object;
      //   40: astore_2
      //   41: aload_2
      //   42: monitorenter
      //   43: aload_0
      //   44: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   47: invokestatic 52	android/support/v7/cn:b	(Landroid/support/v7/cn;)Ljava/util/concurrent/ConcurrentHashMap;
      //   50: aload 4
      //   52: invokevirtual 58	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   55: checkcast 60	android/support/v7/cm
      //   58: astore 5
      //   60: aload 5
      //   62: ifnull +33 -> 95
      //   65: aload 5
      //   67: invokevirtual 63	android/support/v7/cm:a	()Ljava/io/FileInputStream;
      //   70: astore 5
      //   72: aload 5
      //   74: ifnull +8 -> 82
      //   77: aload 5
      //   79: invokevirtual 68	java/io/FileInputStream:close	()V
      //   82: aload_0
      //   83: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   86: invokestatic 52	android/support/v7/cn:b	(Landroid/support/v7/cn;)Ljava/util/concurrent/ConcurrentHashMap;
      //   89: aload 4
      //   91: invokevirtual 71	java/util/concurrent/ConcurrentHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   94: pop
      //   95: aload_0
      //   96: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   99: aload 4
      //   101: invokestatic 74	android/support/v7/cn:a	(Landroid/support/v7/cn;Ljava/lang/String;)Landroid/support/v7/cm;
      //   104: astore 5
      //   106: aload 5
      //   108: ifnull +85 -> 193
      //   111: aload 5
      //   113: new 65	java/io/FileInputStream
      //   116: dup
      //   117: aload 5
      //   119: invokevirtual 77	android/support/v7/cm:c	()Ljava/lang/String;
      //   122: invokespecial 80	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
      //   125: invokevirtual 83	android/support/v7/cm:a	(Ljava/io/FileInputStream;)V
      //   128: aload_0
      //   129: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   132: invokestatic 52	android/support/v7/cn:b	(Landroid/support/v7/cn;)Ljava/util/concurrent/ConcurrentHashMap;
      //   135: aload 4
      //   137: aload 5
      //   139: invokevirtual 87	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   142: pop
      //   143: aload_1
      //   144: new 89	java/lang/StringBuilder
      //   147: dup
      //   148: invokespecial 90	java/lang/StringBuilder:<init>	()V
      //   151: ldc 92
      //   153: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   156: aload 4
      //   158: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   161: ldc 98
      //   163: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   166: aload_0
      //   167: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   170: invokestatic 52	android/support/v7/cn:b	(Landroid/support/v7/cn;)Ljava/util/concurrent/ConcurrentHashMap;
      //   173: aload 4
      //   175: invokevirtual 58	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   178: checkcast 60	android/support/v7/cm
      //   181: invokevirtual 77	android/support/v7/cm:c	()Ljava/lang/String;
      //   184: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   187: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   190: invokestatic 107	com/baloota/dumpster/logger/a:d	(Landroid/content/Context;Ljava/lang/String;)V
      //   193: aload_2
      //   194: monitorexit
      //   195: return
      //   196: astore 5
      //   198: aload_1
      //   199: aload 5
      //   201: invokevirtual 110	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   204: aload 5
      //   206: iconst_0
      //   207: invokestatic 113	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
      //   210: goto -128 -> 82
      //   213: astore_1
      //   214: aload_2
      //   215: monitorexit
      //   216: aload_1
      //   217: athrow
      //   218: astore 6
      //   220: aload 5
      //   222: aconst_null
      //   223: invokevirtual 83	android/support/v7/cm:a	(Ljava/io/FileInputStream;)V
      //   226: aload_1
      //   227: aload 6
      //   229: invokevirtual 114	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
      //   232: aload 6
      //   234: iconst_0
      //   235: invokestatic 113	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
      //   238: goto -110 -> 128
      //   241: astore 6
      //   243: aload 5
      //   245: aconst_null
      //   246: invokevirtual 83	android/support/v7/cm:a	(Ljava/io/FileInputStream;)V
      //   249: aload_1
      //   250: aload 6
      //   252: invokevirtual 110	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   255: aload 6
      //   257: invokestatic 117	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   260: goto -132 -> 128
      //   263: ldc 119
      //   265: aload_2
      //   266: invokevirtual 39	android/content/Intent:getAction	()Ljava/lang/String;
      //   269: invokevirtual 45	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   272: ifeq +190 -> 462
      //   275: aload_0
      //   276: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   279: invokestatic 48	android/support/v7/cn:a	(Landroid/support/v7/cn;)Ljava/lang/Object;
      //   282: astore 5
      //   284: aload 5
      //   286: monitorenter
      //   287: aload_2
      //   288: invokevirtual 123	android/content/Intent:getExtras	()Landroid/os/Bundle;
      //   291: astore_2
      //   292: aload_1
      //   293: invokestatic 129	com/baloota/dumpster/preferences/a:h	(Landroid/content/Context;)Z
      //   296: ifeq +123 -> 419
      //   299: aload_2
      //   300: ldc -125
      //   302: invokevirtual 137	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
      //   305: istore_3
      //   306: iload_3
      //   307: ifeq +14 -> 321
      //   310: iload_3
      //   311: ifeq +135 -> 446
      //   314: aload_1
      //   315: invokestatic 140	com/baloota/dumpster/preferences/a:i	(Landroid/content/Context;)Z
      //   318: ifeq +128 -> 446
      //   321: aload_0
      //   322: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   325: invokestatic 52	android/support/v7/cn:b	(Landroid/support/v7/cn;)Ljava/util/concurrent/ConcurrentHashMap;
      //   328: aload 4
      //   330: invokevirtual 58	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   333: checkcast 60	android/support/v7/cm
      //   336: astore_2
      //   337: aload_2
      //   338: ifnull +81 -> 419
      //   341: aload_0
      //   342: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   345: invokestatic 52	android/support/v7/cn:b	(Landroid/support/v7/cn;)Ljava/util/concurrent/ConcurrentHashMap;
      //   348: aload 4
      //   350: invokevirtual 71	java/util/concurrent/ConcurrentHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   353: pop
      //   354: aload_0
      //   355: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   358: aload_2
      //   359: invokestatic 143	android/support/v7/cn:a	(Landroid/support/v7/cn;Landroid/support/v7/cm;)I
      //   362: pop
      //   363: aload_2
      //   364: invokevirtual 63	android/support/v7/cm:a	()Ljava/io/FileInputStream;
      //   367: astore 4
      //   369: aload 4
      //   371: ifnull +8 -> 379
      //   374: aload 4
      //   376: invokevirtual 68	java/io/FileInputStream:close	()V
      //   379: aload_1
      //   380: new 89	java/lang/StringBuilder
      //   383: dup
      //   384: invokespecial 90	java/lang/StringBuilder:<init>	()V
      //   387: ldc -111
      //   389: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   392: iload_3
      //   393: invokevirtual 148	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
      //   396: ldc -106
      //   398: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   401: ldc -104
      //   403: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   406: aload_2
      //   407: invokevirtual 77	android/support/v7/cm:c	()Ljava/lang/String;
      //   410: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   413: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   416: invokestatic 107	com/baloota/dumpster/logger/a:d	(Landroid/content/Context;Ljava/lang/String;)V
      //   419: aload 5
      //   421: monitorexit
      //   422: return
      //   423: astore_1
      //   424: aload 5
      //   426: monitorexit
      //   427: aload_1
      //   428: athrow
      //   429: astore 4
      //   431: aload_1
      //   432: aload 4
      //   434: invokevirtual 110	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   437: aload 4
      //   439: iconst_0
      //   440: invokestatic 113	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
      //   443: goto -64 -> 379
      //   446: aload_0
      //   447: getfield 12	android/support/v7/cn$1:a	Landroid/support/v7/cn;
      //   450: invokestatic 52	android/support/v7/cn:b	(Landroid/support/v7/cn;)Ljava/util/concurrent/ConcurrentHashMap;
      //   453: aload 4
      //   455: invokevirtual 71	java/util/concurrent/ConcurrentHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   458: pop
      //   459: goto -40 -> 419
      //   462: return
      //   463: aconst_null
      //   464: astore 4
      //   466: goto -450 -> 16
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	469	0	this	1
      //   0	469	1	paramAnonymousContext	Context
      //   0	469	2	paramAnonymousIntent	Intent
      //   305	88	3	bool	boolean
      //   14	361	4	localObject1	Object
      //   429	25	4	localException1	Exception
      //   464	1	4	localObject2	Object
      //   58	80	5	localObject3	Object
      //   196	48	5	localException2	Exception
      //   218	15	6	localFileNotFoundException	FileNotFoundException
      //   241	15	6	localException3	Exception
      // Exception table:
      //   from	to	target	type
      //   65	72	196	java/lang/Exception
      //   77	82	196	java/lang/Exception
      //   43	60	213	finally
      //   65	72	213	finally
      //   77	82	213	finally
      //   82	95	213	finally
      //   95	106	213	finally
      //   111	128	213	finally
      //   128	193	213	finally
      //   193	195	213	finally
      //   198	210	213	finally
      //   214	216	213	finally
      //   220	238	213	finally
      //   243	260	213	finally
      //   111	128	218	java/io/FileNotFoundException
      //   111	128	241	java/lang/Exception
      //   287	306	423	finally
      //   314	321	423	finally
      //   321	337	423	finally
      //   341	363	423	finally
      //   363	369	423	finally
      //   374	379	423	finally
      //   379	419	423	finally
      //   419	422	423	finally
      //   424	427	423	finally
      //   431	443	423	finally
      //   446	459	423	finally
      //   363	369	429	java/lang/Exception
      //   374	379	429	java/lang/Exception
    }
  };
  private BroadcastReceiver f = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(paramAnonymousIntent.getAction())) {}
      for (;;)
      {
        int i;
        Object localObject2;
        synchronized (cn.a(cn.this))
        {
          paramAnonymousIntent = paramAnonymousIntent.getStringArrayExtra("android.intent.extra.changed_package_list");
          i = 0;
          if (i >= paramAnonymousIntent.length) {
            break label195;
          }
          localObject2 = cn.a(cn.this, paramAnonymousIntent[i]);
          if (localObject2 == null) {
            break label356;
          }
        }
        try
        {
          ((cm)localObject2).a(new FileInputStream(((cm)localObject2).c()));
          cn.b(cn.this).put(paramAnonymousIntent[i], localObject2);
          a.d(paramAnonymousContext, ">>> ACTION_EXTERNAL_APPLICATIONS_AVAILABLE add to list (" + paramAnonymousIntent[i] + ", " + ((cm)cn.b(cn.this).get(paramAnonymousIntent[i])).c());
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;)
          {
            ((cm)localObject2).a(null);
            a.a(paramAnonymousContext, localFileNotFoundException.getMessage(), localFileNotFoundException, false);
          }
          paramAnonymousContext = finally;
          throw paramAnonymousContext;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            ((cm)localObject2).a(null);
            a.a(paramAnonymousContext, localException2.getMessage(), localException2);
          }
        }
        label195:
        do
        {
          return;
        } while (!"android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(paramAnonymousIntent.getAction()));
        synchronized (cn.a(cn.this))
        {
          paramAnonymousIntent = paramAnonymousIntent.getStringArrayExtra("android.intent.extra.changed_package_list");
          i = 0;
          if (i < paramAnonymousIntent.length)
          {
            localObject2 = (cm)cn.b(cn.this).get(paramAnonymousIntent[i]);
            if (localObject2 != null) {
              cn.b(cn.this).remove(paramAnonymousIntent[i]);
            }
            try
            {
              localObject2 = ((cm)localObject2).a();
              if (localObject2 != null) {
                ((FileInputStream)localObject2).close();
              }
            }
            catch (Exception localException1)
            {
              for (;;)
              {
                a.a(paramAnonymousContext, localException1.getMessage(), localException1, false);
              }
            }
            a.d(paramAnonymousContext, ">>> ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE remove from list (" + paramAnonymousIntent[i] + ")");
            i += 1;
          }
        }
        return;
        label356:
        i += 1;
      }
    }
  };
  
  public cn(Context paramContext)
  {
    this.a = paramContext;
    this.d = new ConcurrentHashMap();
  }
  
  /* Error */
  private int a(cm paramCm)
  {
    // Byte code:
    //   0: invokestatic 59	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   3: invokevirtual 63	java/util/UUID:toString	()Ljava/lang/String;
    //   6: astore 8
    //   8: aconst_null
    //   9: astore 12
    //   11: invokestatic 69	java/lang/System:currentTimeMillis	()J
    //   14: lstore_3
    //   15: aload_0
    //   16: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   19: invokestatic 74	com/baloota/dumpster/handler/files/e:d	(Landroid/content/Context;)Z
    //   22: istore 7
    //   24: aload_1
    //   25: invokevirtual 78	android/support/v7/cm:c	()Ljava/lang/String;
    //   28: astore 9
    //   30: aload 9
    //   32: ifnull +728 -> 760
    //   35: aload_0
    //   36: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   39: aload 9
    //   41: invokestatic 81	com/baloota/dumpster/handler/files/e:c	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   44: astore 15
    //   46: aload 15
    //   48: ifnonnull +14 -> 62
    //   51: aload_0
    //   52: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   55: ldc 83
    //   57: invokestatic 88	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;)V
    //   60: iconst_m1
    //   61: ireturn
    //   62: aload 9
    //   64: bipush 46
    //   66: invokevirtual 94	java/lang/String:lastIndexOf	(I)I
    //   69: istore_2
    //   70: iload_2
    //   71: ifle +13 -> 84
    //   74: aload 9
    //   76: iload_2
    //   77: iconst_1
    //   78: iadd
    //   79: invokevirtual 98	java/lang/String:substring	(I)Ljava/lang/String;
    //   82: astore 12
    //   84: new 100	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   91: aload 8
    //   93: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: ldc 107
    //   98: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: aload 12
    //   103: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: astore 16
    //   111: aload_1
    //   112: invokevirtual 111	android/support/v7/cm:b	()Ljava/lang/CharSequence;
    //   115: ifnull +45 -> 160
    //   118: aload_1
    //   119: invokevirtual 111	android/support/v7/cm:b	()Ljava/lang/CharSequence;
    //   122: invokeinterface 114 1 0
    //   127: astore 13
    //   129: aload_0
    //   130: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   133: aload 13
    //   135: sipush 9110
    //   138: lload_3
    //   139: invokestatic 117	com/baloota/dumpster/handler/files/e:a	(Landroid/content/Context;Ljava/lang/String;IJ)Landroid/net/Uri;
    //   142: astore 17
    //   144: aload 17
    //   146: ifnonnull +20 -> 166
    //   149: aload_0
    //   150: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   153: ldc 119
    //   155: invokestatic 88	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;)V
    //   158: iconst_m1
    //   159: ireturn
    //   160: aconst_null
    //   161: astore 13
    //   163: goto -34 -> 129
    //   166: aconst_null
    //   167: astore 10
    //   169: aconst_null
    //   170: astore 9
    //   172: aconst_null
    //   173: astore 14
    //   175: aconst_null
    //   176: astore 11
    //   178: aload_0
    //   179: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   182: ldc 121
    //   184: invokevirtual 127	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   187: checkcast 129	android/os/PowerManager
    //   190: iconst_1
    //   191: ldc -125
    //   193: invokevirtual 135	android/os/PowerManager:newWakeLock	(ILjava/lang/String;)Landroid/os/PowerManager$WakeLock;
    //   196: astore 8
    //   198: aload 14
    //   200: astore 9
    //   202: aload 8
    //   204: astore 10
    //   206: aload 8
    //   208: invokevirtual 140	android/os/PowerManager$WakeLock:acquire	()V
    //   211: aload 14
    //   213: astore 9
    //   215: aload 8
    //   217: astore 10
    //   219: aload_1
    //   220: invokevirtual 143	android/support/v7/cm:a	()Ljava/io/FileInputStream;
    //   223: astore 18
    //   225: aload 18
    //   227: ifnull +527 -> 754
    //   230: aload 14
    //   232: astore 9
    //   234: aload 8
    //   236: astore 10
    //   238: new 145	java/io/FileOutputStream
    //   241: dup
    //   242: new 100	java/lang/StringBuilder
    //   245: dup
    //   246: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   249: aload 15
    //   251: invokevirtual 150	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   254: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: ldc -104
    //   259: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: aload 16
    //   264: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   270: invokespecial 155	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   273: astore_1
    //   274: aload_1
    //   275: astore 9
    //   277: aload_1
    //   278: ifnull +202 -> 480
    //   281: sipush 1024
    //   284: newarray byte
    //   286: astore 9
    //   288: aload 18
    //   290: aload 9
    //   292: invokevirtual 161	java/io/FileInputStream:read	([B)I
    //   295: istore_2
    //   296: iload_2
    //   297: iconst_m1
    //   298: if_icmpeq +175 -> 473
    //   301: aload_1
    //   302: aload 9
    //   304: iconst_0
    //   305: iload_2
    //   306: invokevirtual 165	java/io/FileOutputStream:write	([BII)V
    //   309: goto -21 -> 288
    //   312: astore 10
    //   314: aload_1
    //   315: astore 9
    //   317: aload 8
    //   319: astore_1
    //   320: aload 9
    //   322: astore 8
    //   324: aload 10
    //   326: astore 9
    //   328: aload_0
    //   329: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   332: aload 9
    //   334: invokevirtual 168	java/io/IOException:getMessage	()Ljava/lang/String;
    //   337: aload 9
    //   339: iconst_0
    //   340: invokestatic 171	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   343: aload 8
    //   345: ifnull +8 -> 353
    //   348: aload 8
    //   350: invokevirtual 174	java/io/FileOutputStream:close	()V
    //   353: aload_1
    //   354: ifnull +390 -> 744
    //   357: aload_1
    //   358: invokevirtual 177	android/os/PowerManager$WakeLock:release	()V
    //   361: iconst_0
    //   362: istore_2
    //   363: iload_2
    //   364: ifeq +263 -> 627
    //   367: new 147	java/io/File
    //   370: dup
    //   371: new 100	java/lang/StringBuilder
    //   374: dup
    //   375: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   378: aload 15
    //   380: invokevirtual 150	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   383: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: ldc -104
    //   388: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: aload 16
    //   393: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   399: invokespecial 178	java/io/File:<init>	(Ljava/lang/String;)V
    //   402: invokevirtual 181	java/io/File:length	()J
    //   405: lstore 5
    //   407: lload 5
    //   409: lconst_0
    //   410: lcmp
    //   411: ifne +169 -> 580
    //   414: aload_0
    //   415: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   418: invokevirtual 185	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   421: aload 17
    //   423: aconst_null
    //   424: aconst_null
    //   425: invokevirtual 191	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   428: pop
    //   429: new 147	java/io/File
    //   432: dup
    //   433: new 100	java/lang/StringBuilder
    //   436: dup
    //   437: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   440: aload 15
    //   442: invokevirtual 150	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   445: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: ldc -104
    //   450: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: aload 16
    //   455: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   461: invokespecial 178	java/io/File:<init>	(Ljava/lang/String;)V
    //   464: invokevirtual 194	java/io/File:delete	()Z
    //   467: pop
    //   468: iconst_m1
    //   469: ireturn
    //   470: astore_1
    //   471: iconst_m1
    //   472: ireturn
    //   473: aload_1
    //   474: invokevirtual 197	java/io/FileOutputStream:flush	()V
    //   477: aload_1
    //   478: astore 9
    //   480: aload 9
    //   482: ifnull +8 -> 490
    //   485: aload 9
    //   487: invokevirtual 174	java/io/FileOutputStream:close	()V
    //   490: aload 8
    //   492: ifnull +257 -> 749
    //   495: aload 8
    //   497: invokevirtual 177	android/os/PowerManager$WakeLock:release	()V
    //   500: iconst_1
    //   501: istore_2
    //   502: goto -139 -> 363
    //   505: astore_1
    //   506: aconst_null
    //   507: astore 8
    //   509: iconst_0
    //   510: istore_2
    //   511: aload 11
    //   513: astore 9
    //   515: aload 8
    //   517: astore 10
    //   519: aload_0
    //   520: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   523: aload_1
    //   524: invokevirtual 198	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   527: aload_1
    //   528: invokestatic 201	com/baloota/dumpster/logger/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   531: aload 11
    //   533: ifnull +8 -> 541
    //   536: aload 11
    //   538: invokevirtual 174	java/io/FileOutputStream:close	()V
    //   541: aload 8
    //   543: ifnull +198 -> 741
    //   546: aload 8
    //   548: invokevirtual 177	android/os/PowerManager$WakeLock:release	()V
    //   551: goto -188 -> 363
    //   554: astore_1
    //   555: aconst_null
    //   556: astore 8
    //   558: aload 9
    //   560: ifnull +8 -> 568
    //   563: aload 9
    //   565: invokevirtual 174	java/io/FileOutputStream:close	()V
    //   568: aload 8
    //   570: ifnull +8 -> 578
    //   573: aload 8
    //   575: invokevirtual 177	android/os/PowerManager$WakeLock:release	()V
    //   578: aload_1
    //   579: athrow
    //   580: aload_0
    //   581: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   584: aload 17
    //   586: aload 12
    //   588: sipush 9110
    //   591: aload 15
    //   593: aload 16
    //   595: aload 13
    //   597: lload 5
    //   599: iconst_1
    //   600: iload 7
    //   602: lload_3
    //   603: invokestatic 204	com/baloota/dumpster/handler/files/e:a	(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;ILjava/io/File;Ljava/lang/String;Ljava/lang/String;JZZJ)Z
    //   606: ifeq -546 -> 60
    //   609: aload 17
    //   611: invokevirtual 210	android/net/Uri:getPathSegments	()Ljava/util/List;
    //   614: iconst_1
    //   615: invokeinterface 216 2 0
    //   620: checkcast 90	java/lang/String
    //   623: invokestatic 222	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   626: ireturn
    //   627: aload_0
    //   628: getfield 27	android/support/v7/cn:a	Landroid/content/Context;
    //   631: invokevirtual 185	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   634: aload 17
    //   636: aconst_null
    //   637: aconst_null
    //   638: invokevirtual 191	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   641: pop
    //   642: iconst_m1
    //   643: ireturn
    //   644: astore_1
    //   645: goto -155 -> 490
    //   648: astore 8
    //   650: goto -297 -> 353
    //   653: astore_1
    //   654: goto -113 -> 541
    //   657: astore 9
    //   659: goto -91 -> 568
    //   662: astore_1
    //   663: aload 10
    //   665: astore 8
    //   667: goto -109 -> 558
    //   670: astore 10
    //   672: aload_1
    //   673: astore 9
    //   675: aload 10
    //   677: astore_1
    //   678: goto -120 -> 558
    //   681: astore 9
    //   683: aload_1
    //   684: astore 10
    //   686: aload 9
    //   688: astore_1
    //   689: aload 8
    //   691: astore 9
    //   693: aload 10
    //   695: astore 8
    //   697: goto -139 -> 558
    //   700: astore_1
    //   701: goto -192 -> 509
    //   704: astore 9
    //   706: aload_1
    //   707: astore 11
    //   709: aload 9
    //   711: astore_1
    //   712: goto -203 -> 509
    //   715: astore 9
    //   717: aconst_null
    //   718: astore 8
    //   720: aload 10
    //   722: astore_1
    //   723: goto -395 -> 328
    //   726: astore 9
    //   728: aconst_null
    //   729: astore 10
    //   731: aload 8
    //   733: astore_1
    //   734: aload 10
    //   736: astore 8
    //   738: goto -410 -> 328
    //   741: goto -378 -> 363
    //   744: iconst_0
    //   745: istore_2
    //   746: goto -383 -> 363
    //   749: iconst_1
    //   750: istore_2
    //   751: goto -388 -> 363
    //   754: aconst_null
    //   755: astore 9
    //   757: goto -277 -> 480
    //   760: iconst_m1
    //   761: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	762	0	this	cn
    //   0	762	1	paramCm	cm
    //   69	682	2	i	int
    //   14	589	3	l1	long
    //   405	193	5	l2	long
    //   22	579	7	bool	boolean
    //   6	568	8	localObject1	Object
    //   648	1	8	localException1	Exception
    //   665	72	8	localObject2	Object
    //   28	536	9	localObject3	Object
    //   657	1	9	localException2	Exception
    //   673	1	9	localCm1	cm
    //   681	6	9	localObject4	Object
    //   691	1	9	localObject5	Object
    //   704	6	9	localException3	Exception
    //   715	1	9	localIOException1	IOException
    //   726	1	9	localIOException2	IOException
    //   755	1	9	localObject6	Object
    //   167	70	10	localObject7	Object
    //   312	13	10	localIOException3	IOException
    //   517	147	10	localObject8	Object
    //   670	6	10	localObject9	Object
    //   684	51	10	localCm2	cm
    //   176	532	11	localCm3	cm
    //   9	578	12	str1	String
    //   127	469	13	str2	String
    //   173	58	14	localObject10	Object
    //   44	548	15	localFile	java.io.File
    //   109	485	16	str3	String
    //   142	493	17	localUri	android.net.Uri
    //   223	66	18	localFileInputStream	FileInputStream
    // Exception table:
    //   from	to	target	type
    //   281	288	312	java/io/IOException
    //   288	296	312	java/io/IOException
    //   301	309	312	java/io/IOException
    //   473	477	312	java/io/IOException
    //   429	468	470	java/lang/Exception
    //   178	198	505	java/lang/Exception
    //   178	198	554	finally
    //   485	490	644	java/lang/Exception
    //   348	353	648	java/lang/Exception
    //   536	541	653	java/lang/Exception
    //   563	568	657	java/lang/Exception
    //   206	211	662	finally
    //   219	225	662	finally
    //   238	274	662	finally
    //   519	531	662	finally
    //   281	288	670	finally
    //   288	296	670	finally
    //   301	309	670	finally
    //   473	477	670	finally
    //   328	343	681	finally
    //   206	211	700	java/lang/Exception
    //   219	225	700	java/lang/Exception
    //   238	274	700	java/lang/Exception
    //   281	288	704	java/lang/Exception
    //   288	296	704	java/lang/Exception
    //   301	309	704	java/lang/Exception
    //   473	477	704	java/lang/Exception
    //   178	198	715	java/io/IOException
    //   206	211	726	java/io/IOException
    //   219	225	726	java/io/IOException
    //   238	274	726	java/io/IOException
  }
  
  private cm a(String paramString)
  {
    PackageManager localPackageManager = this.a.getPackageManager();
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      if ((paramString != null) && (paramString.applicationInfo != null))
      {
        cm localCm = new cm();
        localCm.a(paramString.applicationInfo.loadLabel(localPackageManager));
        localCm.a(paramString.applicationInfo.sourceDir);
        return localCm;
      }
    }
    catch (Exception paramString)
    {
      a.a(this.a, paramString.getMessage(), paramString);
      return null;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
  }
  
  private void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  public void a()
  {
    a.d(this.a, "app started");
    for (;;)
    {
      int i;
      ApplicationInfo localApplicationInfo;
      cm localCm;
      synchronized (this.c)
      {
        a(false);
        PackageManager localPackageManager = this.a.getPackageManager();
        List localList = localPackageManager.getInstalledApplications(128);
        i = 0;
        if (i < localList.size())
        {
          localApplicationInfo = (ApplicationInfo)localList.get(i);
          if ((localApplicationInfo == null) || (a(localApplicationInfo))) {
            break label261;
          }
          localCm = new cm();
          localCm.a(localApplicationInfo.loadLabel(localPackageManager));
          localCm.a(localApplicationInfo.sourceDir);
        }
      }
      try
      {
        try
        {
          FileInputStream localFileInputStream = new FileInputStream(localCm.c());
          if (localFileInputStream == null) {
            break label261;
          }
          localCm.a(localFileInputStream);
          this.d.put(localApplicationInfo.packageName, localCm);
        }
        catch (Exception localException)
        {
          for (;;)
          {
            a.a(this.a, localException.getMessage(), localException);
            localIntentFilter = null;
          }
        }
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        localIntentFilter.addDataScheme("package");
        this.a.registerReceiver(this.e, localIntentFilter);
        localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
        localIntentFilter.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
        this.a.registerReceiver(this.f, localIntentFilter);
        a(true);
        return;
        localObject1 = finally;
        throw localObject1;
        label261:
        i += 1;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Object localObject2 = null;
        }
      }
    }
  }
  
  public void b()
  {
    synchronized (this.c)
    {
      a(false);
      this.a.unregisterReceiver(this.e);
      this.a.unregisterReceiver(this.f);
      Iterator localIterator = this.d.entrySet().iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          Object localObject3 = (cm)((Map.Entry)localIterator.next()).getValue();
          try
          {
            localObject3 = ((cm)localObject3).a();
            if (localObject3 != null) {
              ((FileInputStream)localObject3).close();
            }
          }
          catch (Exception localException)
          {
            a.a(this.a, localException.getMessage(), localException, false);
          }
        }
      }
    }
    this.d.clear();
  }
}
