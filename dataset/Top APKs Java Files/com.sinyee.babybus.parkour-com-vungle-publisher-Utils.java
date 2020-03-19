package com.vungle.publisher;

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
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Utils
{
  private static final String a = Utils.class.getSimpleName();
  private static Context b;
  
  public Utils() {}
  
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
    //   0: getstatic 46	com/vungle/publisher/Utils:b	Landroid/content/Context;
    //   3: ldc 94
    //   5: invokevirtual 74	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   8: checkcast 96	android/location/LocationManager
    //   11: astore_0
    //   12: aload_0
    //   13: ifnonnull +5 -> 18
    //   16: aconst_null
    //   17: areturn
    //   18: aload_0
    //   19: ldc 98
    //   21: invokevirtual 101	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   24: astore_1
    //   25: aload_0
    //   26: ldc 103
    //   28: invokevirtual 101	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   31: astore_0
    //   32: aload_1
    //   33: ifnonnull +7 -> 40
    //   36: aload_0
    //   37: ifnull -21 -> 16
    //   40: aload_1
    //   41: ifnull +84 -> 125
    //   44: aload_0
    //   45: ifnull +80 -> 125
    //   48: aload_1
    //   49: invokevirtual 109	android/location/Location:getTime	()J
    //   52: aload_0
    //   53: invokevirtual 109	android/location/Location:getTime	()J
    //   56: lcmp
    //   57: ifle +63 -> 120
    //   60: aload_1
    //   61: astore_2
    //   62: aload_2
    //   63: areturn
    //   64: astore_1
    //   65: getstatic 18	com/vungle/publisher/Utils:a	Ljava/lang/String;
    //   68: ldc 111
    //   70: invokestatic 117	com/vungle/log/Logger:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   73: aconst_null
    //   74: astore_1
    //   75: goto -50 -> 25
    //   78: astore_1
    //   79: getstatic 18	com/vungle/publisher/Utils:a	Ljava/lang/String;
    //   82: ldc 119
    //   84: invokestatic 117	com/vungle/log/Logger:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: aconst_null
    //   88: astore_1
    //   89: goto -64 -> 25
    //   92: astore_0
    //   93: getstatic 18	com/vungle/publisher/Utils:a	Ljava/lang/String;
    //   96: ldc 121
    //   98: invokestatic 117	com/vungle/log/Logger:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: aconst_null
    //   102: astore_0
    //   103: goto -71 -> 32
    //   106: astore_0
    //   107: getstatic 18	com/vungle/publisher/Utils:a	Ljava/lang/String;
    //   110: ldc 123
    //   112: invokestatic 117	com/vungle/log/Logger:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: aconst_null
    //   116: astore_0
    //   117: goto -85 -> 32
    //   120: aload_0
    //   121: astore_2
    //   122: goto -60 -> 62
    //   125: aload_1
    //   126: astore_2
    //   127: aload_1
    //   128: ifnonnull -66 -> 62
    //   131: aload_0
    //   132: astore_2
    //   133: goto -71 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   11	42	0	localObject1	Object
    //   92	1	0	localSecurityException1	SecurityException
    //   102	1	0	localObject2	Object
    //   106	1	0	localIllegalArgumentException1	IllegalArgumentException
    //   116	16	0	localObject3	Object
    //   24	37	1	localLocation	android.location.Location
    //   64	1	1	localSecurityException2	SecurityException
    //   74	1	1	localObject4	Object
    //   78	1	1	localIllegalArgumentException2	IllegalArgumentException
    //   88	40	1	localObject5	Object
    //   61	72	2	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   18	25	64	java/lang/SecurityException
    //   18	25	78	java/lang/IllegalArgumentException
    //   25	32	92	java/lang/SecurityException
    //   25	32	106	java/lang/IllegalArgumentException
  }
  
  public static String getStringFromStream(InputStream paramInputStream)
    throws IOException
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
        boolean bool = paramContext.isAvailable();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isPackageInstalled(List<String> paramList)
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
