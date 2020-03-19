package com.baidu.simeji.util;

import android.content.pm.PackageInfo;
import android.view.inputmethod.InputMethodInfo;
import java.lang.ref.WeakReference;
import java.util.List;

public class ApiUtil
{
  private static final int TIME_GAP = 500;
  private static WeakReference<List<InputMethodInfo>> sInputInfos = null;
  private static long sInputTime = 0L;
  private static WeakReference<List<PackageInfo>> sPackageInfos = null;
  private static long sPackageTime = 0L;
  
  public ApiUtil() {}
  
  /* Error */
  public static List<InputMethodInfo> getInputMethodList(android.view.inputmethod.InputMethodManager paramInputMethodManager)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 39	java/lang/System:currentTimeMillis	()J
    //   6: getstatic 21	com/baidu/simeji/util/ApiUtil:sInputTime	J
    //   9: lsub
    //   10: invokestatic 45	java/lang/Math:abs	(J)J
    //   13: ldc2_w 46
    //   16: lcmp
    //   17: ifge +30 -> 47
    //   20: getstatic 19	com/baidu/simeji/util/ApiUtil:sInputInfos	Ljava/lang/ref/WeakReference;
    //   23: ifnull +24 -> 47
    //   26: getstatic 19	com/baidu/simeji/util/ApiUtil:sInputInfos	Ljava/lang/ref/WeakReference;
    //   29: invokevirtual 53	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   32: checkcast 55	java/util/List
    //   35: astore_1
    //   36: aload_1
    //   37: ifnull +10 -> 47
    //   40: aload_1
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: areturn
    //   47: invokestatic 39	java/lang/System:currentTimeMillis	()J
    //   50: putstatic 21	com/baidu/simeji/util/ApiUtil:sInputTime	J
    //   53: aload_0
    //   54: invokevirtual 60	android/view/inputmethod/InputMethodManager:getInputMethodList	()Ljava/util/List;
    //   57: astore_0
    //   58: new 49	java/lang/ref/WeakReference
    //   61: dup
    //   62: aload_0
    //   63: invokespecial 63	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   66: putstatic 19	com/baidu/simeji/util/ApiUtil:sInputInfos	Ljava/lang/ref/WeakReference;
    //   69: goto -27 -> 42
    //   72: astore_0
    //   73: aload_0
    //   74: invokestatic 69	com/baidu/simeji/common/util/SimejiLog:uploadException	(Ljava/lang/Exception;)V
    //   77: new 71	java/util/ArrayList
    //   80: dup
    //   81: invokespecial 72	java/util/ArrayList:<init>	()V
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
  
  /* Error */
  public static List<PackageInfo> getInstalledPackages(android.content.pm.PackageManager paramPackageManager)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 39	java/lang/System:currentTimeMillis	()J
    //   6: getstatic 25	com/baidu/simeji/util/ApiUtil:sPackageTime	J
    //   9: lsub
    //   10: invokestatic 45	java/lang/Math:abs	(J)J
    //   13: ldc2_w 46
    //   16: lcmp
    //   17: ifge +30 -> 47
    //   20: getstatic 23	com/baidu/simeji/util/ApiUtil:sPackageInfos	Ljava/lang/ref/WeakReference;
    //   23: ifnull +24 -> 47
    //   26: getstatic 23	com/baidu/simeji/util/ApiUtil:sPackageInfos	Ljava/lang/ref/WeakReference;
    //   29: invokevirtual 53	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   32: checkcast 55	java/util/List
    //   35: astore_1
    //   36: aload_1
    //   37: ifnull +10 -> 47
    //   40: aload_1
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: areturn
    //   47: invokestatic 39	java/lang/System:currentTimeMillis	()J
    //   50: putstatic 25	com/baidu/simeji/util/ApiUtil:sPackageTime	J
    //   53: aload_0
    //   54: iconst_0
    //   55: invokevirtual 81	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   58: astore_0
    //   59: new 49	java/lang/ref/WeakReference
    //   62: dup
    //   63: aload_0
    //   64: invokespecial 63	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   67: putstatic 23	com/baidu/simeji/util/ApiUtil:sPackageInfos	Ljava/lang/ref/WeakReference;
    //   70: goto -28 -> 42
    //   73: astore_0
    //   74: aload_0
    //   75: invokestatic 69	com/baidu/simeji/common/util/SimejiLog:uploadException	(Ljava/lang/Exception;)V
    //   78: new 71	java/util/ArrayList
    //   81: dup
    //   82: invokespecial 72	java/util/ArrayList:<init>	()V
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
}
