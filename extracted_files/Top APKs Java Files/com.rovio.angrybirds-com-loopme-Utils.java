package com.loopme;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Utils
{
  private static final String a = Utils.class.getSimpleName();
  private static Context b;
  
  public Utils() {}
  
  static void a(Context paramContext)
  {
    b = paramContext;
  }
  
  public static void animateAppear(View paramView)
  {
    paramView.animate().setDuration(500L).alpha(1.0F);
  }
  
  public static int convertDpToPixel(float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, b.getResources().getDisplayMetrics());
  }
  
  public static DisplayMetrics getDisplayMetrics(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (paramContext == null) {
      return localDisplayMetrics;
    }
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  /* Error */
  public static android.location.Location getLastKnownLocation()
  {
    // Byte code:
    //   0: getstatic 25	com/loopme/Utils:b	Landroid/content/Context;
    //   3: ldc 95
    //   5: invokevirtual 75	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   8: checkcast 97	android/location/LocationManager
    //   11: astore_0
    //   12: aload_0
    //   13: ifnonnull +5 -> 18
    //   16: aconst_null
    //   17: areturn
    //   18: aload_0
    //   19: ldc 99
    //   21: invokevirtual 102	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   24: astore_1
    //   25: aload_0
    //   26: ldc 104
    //   28: invokevirtual 102	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   31: astore_0
    //   32: aload_1
    //   33: ifnonnull +7 -> 40
    //   36: aload_0
    //   37: ifnull -21 -> 16
    //   40: aload_1
    //   41: ifnull +96 -> 137
    //   44: aload_0
    //   45: ifnull +92 -> 137
    //   48: aload_1
    //   49: invokevirtual 110	android/location/Location:getTime	()J
    //   52: aload_0
    //   53: invokevirtual 110	android/location/Location:getTime	()J
    //   56: lcmp
    //   57: ifle +75 -> 132
    //   60: aload_1
    //   61: astore_2
    //   62: aload_2
    //   63: areturn
    //   64: astore_1
    //   65: getstatic 18	com/loopme/Utils:a	Ljava/lang/String;
    //   68: ldc 112
    //   70: getstatic 118	com/loopme/Logging$LogLevel:DEBUG	Lcom/loopme/Logging$LogLevel;
    //   73: invokestatic 124	com/loopme/Logging:out	(Ljava/lang/String;Ljava/lang/String;Lcom/loopme/Logging$LogLevel;)V
    //   76: aconst_null
    //   77: astore_1
    //   78: goto -53 -> 25
    //   81: astore_1
    //   82: getstatic 18	com/loopme/Utils:a	Ljava/lang/String;
    //   85: ldc 126
    //   87: getstatic 118	com/loopme/Logging$LogLevel:DEBUG	Lcom/loopme/Logging$LogLevel;
    //   90: invokestatic 124	com/loopme/Logging:out	(Ljava/lang/String;Ljava/lang/String;Lcom/loopme/Logging$LogLevel;)V
    //   93: aconst_null
    //   94: astore_1
    //   95: goto -70 -> 25
    //   98: astore_0
    //   99: getstatic 18	com/loopme/Utils:a	Ljava/lang/String;
    //   102: ldc -128
    //   104: getstatic 118	com/loopme/Logging$LogLevel:DEBUG	Lcom/loopme/Logging$LogLevel;
    //   107: invokestatic 124	com/loopme/Logging:out	(Ljava/lang/String;Ljava/lang/String;Lcom/loopme/Logging$LogLevel;)V
    //   110: aconst_null
    //   111: astore_0
    //   112: goto -80 -> 32
    //   115: astore_0
    //   116: getstatic 18	com/loopme/Utils:a	Ljava/lang/String;
    //   119: ldc -126
    //   121: getstatic 118	com/loopme/Logging$LogLevel:DEBUG	Lcom/loopme/Logging$LogLevel;
    //   124: invokestatic 124	com/loopme/Logging:out	(Ljava/lang/String;Ljava/lang/String;Lcom/loopme/Logging$LogLevel;)V
    //   127: aconst_null
    //   128: astore_0
    //   129: goto -97 -> 32
    //   132: aload_0
    //   133: astore_2
    //   134: goto -72 -> 62
    //   137: aload_1
    //   138: astore_2
    //   139: aload_1
    //   140: ifnonnull -78 -> 62
    //   143: aload_0
    //   144: astore_2
    //   145: goto -83 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   11	42	0	localObject1	Object
    //   98	1	0	localSecurityException1	SecurityException
    //   111	1	0	localObject2	Object
    //   115	1	0	localIllegalArgumentException1	IllegalArgumentException
    //   128	16	0	localObject3	Object
    //   24	37	1	localLocation	android.location.Location
    //   64	1	1	localSecurityException2	SecurityException
    //   77	1	1	localObject4	Object
    //   81	1	1	localIllegalArgumentException2	IllegalArgumentException
    //   94	46	1	localObject5	Object
    //   61	84	2	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   18	25	64	java/lang/SecurityException
    //   18	25	81	java/lang/IllegalArgumentException
    //   25	32	98	java/lang/SecurityException
    //   25	32	115	java/lang/IllegalArgumentException
  }
  
  public static String getStringFromStream(InputStream paramInputStream)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localStringBuilder.append(new String(arrayOfByte, 0, i));
    }
    paramInputStream.close();
    return localStringBuilder.toString();
  }
  
  public static boolean isOnline(Context paramContext)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isConnected()))
      {
        bool = paramContext.isAvailable();
        if (!bool) {}
      }
      for (boolean bool = true;; bool = false) {
        return bool;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean isPackageInstalled(List paramList)
  {
    if (b == null) {
      return false;
    }
    Iterator localIterator = b.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      int i = 0;
      while (i < paramList.size())
      {
        if (((String)paramList.get(i)).equalsIgnoreCase(localPackageInfo.packageName)) {
          return true;
        }
        i += 1;
      }
    }
  }
}
