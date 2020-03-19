package srf;

import android.content.pm.PackageInfo;
import android.view.inputmethod.InputMethodInfo;
import java.lang.ref.WeakReference;
import java.util.List;

public class arp
{
  private static WeakReference<List<InputMethodInfo>> a = null;
  private static long b = 0L;
  private static WeakReference<List<PackageInfo>> c = null;
  private static long d = 0L;
  
  /* Error */
  public static List<PackageInfo> a(android.content.pm.PackageManager paramPackageManager)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 32	java/lang/System:currentTimeMillis	()J
    //   6: getstatic 22	srf/arp:d	J
    //   9: lsub
    //   10: invokestatic 38	java/lang/Math:abs	(J)J
    //   13: ldc2_w 39
    //   16: lcmp
    //   17: ifge +30 -> 47
    //   20: getstatic 20	srf/arp:c	Ljava/lang/ref/WeakReference;
    //   23: ifnull +24 -> 47
    //   26: getstatic 20	srf/arp:c	Ljava/lang/ref/WeakReference;
    //   29: invokevirtual 46	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   32: checkcast 48	java/util/List
    //   35: astore_1
    //   36: aload_1
    //   37: ifnull +10 -> 47
    //   40: aload_1
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: areturn
    //   47: invokestatic 32	java/lang/System:currentTimeMillis	()J
    //   50: putstatic 22	srf/arp:d	J
    //   53: aload_0
    //   54: iconst_0
    //   55: invokevirtual 54	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   58: astore_0
    //   59: new 42	java/lang/ref/WeakReference
    //   62: dup
    //   63: aload_0
    //   64: invokespecial 58	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   67: putstatic 20	srf/arp:c	Ljava/lang/ref/WeakReference;
    //   70: goto -28 -> 42
    //   73: astore_0
    //   74: aload_0
    //   75: invokestatic 63	srf/yg:a	(Ljava/lang/Exception;)V
    //   78: new 65	java/util/ArrayList
    //   81: dup
    //   82: invokespecial 67	java/util/ArrayList:<init>	()V
    //   85: astore_0
    //   86: goto -44 -> 42
    //   89: astore_0
    //   90: ldc 2
    //   92: monitorexit
    //   93: aload_0
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	paramPackageManager	android.content.pm.PackageManager
    //   35	6	1	localList	List
    // Exception table:
    //   from	to	target	type
    //   53	70	73	java/lang/Exception
    //   3	36	89	finally
    //   47	53	89	finally
    //   53	70	89	finally
    //   74	86	89	finally
  }
  
  /* Error */
  public static List<InputMethodInfo> a(android.view.inputmethod.InputMethodManager paramInputMethodManager)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 32	java/lang/System:currentTimeMillis	()J
    //   6: getstatic 18	srf/arp:b	J
    //   9: lsub
    //   10: invokestatic 38	java/lang/Math:abs	(J)J
    //   13: ldc2_w 39
    //   16: lcmp
    //   17: ifge +30 -> 47
    //   20: getstatic 16	srf/arp:a	Ljava/lang/ref/WeakReference;
    //   23: ifnull +24 -> 47
    //   26: getstatic 16	srf/arp:a	Ljava/lang/ref/WeakReference;
    //   29: invokevirtual 46	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   32: checkcast 48	java/util/List
    //   35: astore_1
    //   36: aload_1
    //   37: ifnull +10 -> 47
    //   40: aload_1
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: areturn
    //   47: invokestatic 32	java/lang/System:currentTimeMillis	()J
    //   50: putstatic 18	srf/arp:b	J
    //   53: aload_0
    //   54: invokevirtual 76	android/view/inputmethod/InputMethodManager:getInputMethodList	()Ljava/util/List;
    //   57: astore_0
    //   58: new 42	java/lang/ref/WeakReference
    //   61: dup
    //   62: aload_0
    //   63: invokespecial 58	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   66: putstatic 16	srf/arp:a	Ljava/lang/ref/WeakReference;
    //   69: goto -27 -> 42
    //   72: astore_0
    //   73: aload_0
    //   74: invokestatic 63	srf/yg:a	(Ljava/lang/Exception;)V
    //   77: new 65	java/util/ArrayList
    //   80: dup
    //   81: invokespecial 67	java/util/ArrayList:<init>	()V
    //   84: astore_0
    //   85: goto -43 -> 42
    //   88: astore_0
    //   89: ldc 2
    //   91: monitorexit
    //   92: aload_0
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	paramInputMethodManager	android.view.inputmethod.InputMethodManager
    //   35	6	1	localList	List
    // Exception table:
    //   from	to	target	type
    //   53	69	72	java/lang/Exception
    //   3	36	88	finally
    //   47	53	88	finally
    //   53	69	88	finally
    //   73	85	88	finally
  }
}
