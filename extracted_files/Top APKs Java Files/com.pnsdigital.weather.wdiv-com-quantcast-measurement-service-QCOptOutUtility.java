package com.quantcast.measurement.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.Iterator;
import java.util.List;

class QCOptOutUtility
{
  private static final String QCMEASUREMENT_OPTOUT_STRING = "QC-OPT-OUT";
  public static final String QC_NOTIF_OPT_OUT_CHANGED = "QC_OUC";
  
  QCOptOutUtility() {}
  
  static void askEveryone(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = Boolean.valueOf(false);
    Object localObject4 = paramContext.getPackageManager();
    Object localObject3 = localObject1;
    Iterator localIterator;
    if (localObject4 != null) {
      localIterator = ((PackageManager)localObject4).getInstalledPackages(0).iterator();
    }
    for (;;)
    {
      localObject3 = localObject1;
      if (localIterator.hasNext())
      {
        localObject3 = (PackageInfo)localIterator.next();
        if (!((PackageInfo)localObject3).packageName.equals(paramContext.getPackageName())) {
          localObject4 = localObject1;
        }
      }
      else
      {
        try
        {
          localObject3 = paramContext.createPackageContext(((PackageInfo)localObject3).packageName, 0);
          if (paramBoolean2)
          {
            localObject4 = localObject1;
            if (isQuantified((Context)localObject3))
            {
              localObject4 = localObject1;
              createOptOut((Context)localObject3, paramBoolean1);
            }
          }
          else
          {
            localObject4 = localObject1;
            localObject3 = Boolean.valueOf(isOptedOut((Context)localObject3, false));
            localObject4 = localObject3;
            boolean bool = ((Boolean)localObject3).booleanValue();
            localObject1 = localObject3;
            if (bool)
            {
              if (!paramBoolean2) {
                createOptOut(paramContext, ((Boolean)localObject3).booleanValue());
              }
              return;
            }
          }
        }
        catch (Exception localException)
        {
          Object localObject2 = localObject4;
        }
      }
    }
  }
  
  /* Error */
  static void createOptOut(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: ldc 8
    //   8: iconst_3
    //   9: invokevirtual 94	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   12: astore_0
    //   13: iload_1
    //   14: ifeq +24 -> 38
    //   17: iconst_1
    //   18: istore_2
    //   19: aload_0
    //   20: astore_3
    //   21: aload_0
    //   22: astore 4
    //   24: aload_0
    //   25: iload_2
    //   26: invokevirtual 100	java/io/FileOutputStream:write	(I)V
    //   29: aload_0
    //   30: ifnull +7 -> 37
    //   33: aload_0
    //   34: invokevirtual 103	java/io/FileOutputStream:close	()V
    //   37: return
    //   38: iconst_0
    //   39: istore_2
    //   40: goto -21 -> 19
    //   43: astore_0
    //   44: aload_3
    //   45: ifnull -8 -> 37
    //   48: aload_3
    //   49: invokevirtual 103	java/io/FileOutputStream:close	()V
    //   52: return
    //   53: astore_0
    //   54: return
    //   55: astore_0
    //   56: aload 4
    //   58: ifnull +8 -> 66
    //   61: aload 4
    //   63: invokevirtual 103	java/io/FileOutputStream:close	()V
    //   66: aload_0
    //   67: athrow
    //   68: astore_0
    //   69: return
    //   70: astore_3
    //   71: goto -5 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	paramContext	Context
    //   0	74	1	paramBoolean	boolean
    //   18	22	2	i	int
    //   4	45	3	localContext1	Context
    //   70	1	3	localIOException	java.io.IOException
    //   1	61	4	localContext2	Context
    // Exception table:
    //   from	to	target	type
    //   5	13	43	java/lang/Exception
    //   24	29	43	java/lang/Exception
    //   48	52	53	java/io/IOException
    //   5	13	55	finally
    //   24	29	55	finally
    //   33	37	68	java/io/IOException
    //   61	66	70	java/io/IOException
  }
  
  static boolean isOptedOut(Context paramContext)
  {
    return isOptedOut(paramContext, true);
  }
  
  /* Error */
  private static boolean isOptedOut(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aconst_null
    //   3: astore 7
    //   5: aconst_null
    //   6: astore 5
    //   8: aconst_null
    //   9: astore 4
    //   11: aload_0
    //   12: ldc 8
    //   14: invokevirtual 109	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   17: astore 6
    //   19: aload 6
    //   21: astore 4
    //   23: aload 6
    //   25: astore 7
    //   27: aload 6
    //   29: astore 5
    //   31: aload 6
    //   33: invokevirtual 115	java/io/FileInputStream:read	()I
    //   36: istore_2
    //   37: iload_2
    //   38: ifeq +21 -> 59
    //   41: iconst_1
    //   42: istore_1
    //   43: iload_1
    //   44: istore_3
    //   45: aload 6
    //   47: ifnull +10 -> 57
    //   50: aload 6
    //   52: invokevirtual 116	java/io/FileInputStream:close	()V
    //   55: iload_1
    //   56: istore_3
    //   57: iload_3
    //   58: ireturn
    //   59: iconst_0
    //   60: istore_1
    //   61: goto -18 -> 43
    //   64: astore 5
    //   66: iload_1
    //   67: ifeq +13 -> 80
    //   70: aload 4
    //   72: astore 5
    //   74: aload_0
    //   75: iconst_0
    //   76: iconst_0
    //   77: invokestatic 118	com/quantcast/measurement/service/QCOptOutUtility:askEveryone	(Landroid/content/Context;ZZ)V
    //   80: aload 4
    //   82: ifnull -25 -> 57
    //   85: aload 4
    //   87: invokevirtual 116	java/io/FileInputStream:close	()V
    //   90: iconst_0
    //   91: ireturn
    //   92: astore_0
    //   93: iconst_0
    //   94: ireturn
    //   95: astore_0
    //   96: aload 7
    //   98: ifnull -41 -> 57
    //   101: aload 7
    //   103: invokevirtual 116	java/io/FileInputStream:close	()V
    //   106: iconst_0
    //   107: ireturn
    //   108: astore_0
    //   109: iconst_0
    //   110: ireturn
    //   111: astore_0
    //   112: aload 5
    //   114: ifnull +8 -> 122
    //   117: aload 5
    //   119: invokevirtual 116	java/io/FileInputStream:close	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_0
    //   125: iload_1
    //   126: ireturn
    //   127: astore 4
    //   129: goto -7 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	132	0	paramContext	Context
    //   0	132	1	paramBoolean	boolean
    //   36	2	2	i	int
    //   1	57	3	bool	boolean
    //   9	77	4	localObject1	Object
    //   127	1	4	localIOException	java.io.IOException
    //   6	24	5	localObject2	Object
    //   64	1	5	localFileNotFoundException	java.io.FileNotFoundException
    //   72	46	5	localObject3	Object
    //   17	34	6	localFileInputStream	java.io.FileInputStream
    //   3	99	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   11	19	64	java/io/FileNotFoundException
    //   31	37	64	java/io/FileNotFoundException
    //   85	90	92	java/io/IOException
    //   11	19	95	java/io/IOException
    //   31	37	95	java/io/IOException
    //   101	106	108	java/io/IOException
    //   11	19	111	finally
    //   31	37	111	finally
    //   74	80	111	finally
    //   50	55	124	java/io/IOException
    //   117	122	127	java/io/IOException
  }
  
  static boolean isQuantified(Context paramContext)
  {
    paramContext = paramContext.getFileStreamPath("QC-OPT-OUT");
    return (paramContext != null) && (paramContext.exists());
  }
  
  static void saveOptOutStatus(Context paramContext, boolean paramBoolean)
  {
    createOptOut(paramContext, paramBoolean);
    askEveryone(paramContext, paramBoolean, true);
    QCNotificationCenter.INSTANCE.postNotification("QC_OUC", Boolean.valueOf(paramBoolean));
  }
}
