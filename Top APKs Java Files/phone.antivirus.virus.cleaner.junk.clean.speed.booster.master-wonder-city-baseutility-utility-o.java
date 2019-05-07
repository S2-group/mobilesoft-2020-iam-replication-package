package wonder.city.baseutility.utility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.provider.Settings.Secure;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import com.ali.mobisecenhance.Init;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import wonder.city.baseutility.utility.e.f;
import wonder.city.baseutility.utility.g.e;
import z.z.z.z2;

public class o
{
  static String a = o.class.getName();
  public static long b = 0L;
  public static long c = 0L;
  public static long d = 0L;
  
  public o() {}
  
  public static void A(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("ShortCut", 0).edit();
    paramContext.putBoolean("HasShortCut", true);
    paramContext.apply();
  }
  
  public static boolean B(Context paramContext)
  {
    return paramContext.getSharedPreferences("ShortCut", 0).getBoolean("HasShortCut", false);
  }
  
  public static String C(Context paramContext)
  {
    return paramContext.getSharedPreferences("SystemMem", 0).getString("SystemMem", null);
  }
  
  public static boolean D(Context paramContext)
  {
    long l = s(paramContext);
    return System.currentTimeMillis() - l > 50000L;
  }
  
  public static boolean E(Context paramContext)
  {
    long l = s(paramContext);
    return System.currentTimeMillis() - l > 180000L;
  }
  
  public static boolean F(Context paramContext)
  {
    long l = u(paramContext);
    return System.currentTimeMillis() - l > 480000L;
  }
  
  public static List<wonder.city.baseutility.utility.g.a> G(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getSharedPreferences("sp_w_l", 0).getString("canlaunchapps", "").split(";");
    int j = paramContext.length;
    int i = 0;
    if (i < j)
    {
      String[] arrayOfString = paramContext[i];
      if ((arrayOfString == null) || (arrayOfString.isEmpty())) {}
      for (;;)
      {
        i += 1;
        break;
        arrayOfString = arrayOfString.split(":");
        if (arrayOfString.length == 2)
        {
          wonder.city.baseutility.utility.g.a localA = new wonder.city.baseutility.utility.g.a();
          localA.b(arrayOfString[0]);
          localA.a(arrayOfString[1]);
          localArrayList.add(localA);
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean H(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static List<ActivityManager.RunningAppProcessInfo> I(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = paramContext;
        paramContext = null;
      }
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static List<ApplicationInfo> J(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = paramContext;
        paramContext = null;
      }
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  /* Error */
  public static List<ApplicationInfo> K(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 176	wonder/city/baseutility/utility/o:J	(Landroid/content/Context;)Ljava/util/List;
    //   7: astore_3
    //   8: aload_3
    //   9: ifnull +89 -> 98
    //   12: aload_3
    //   13: invokeinterface 180 1 0
    //   18: astore 4
    //   20: aload 4
    //   22: invokeinterface 185 1 0
    //   27: ifeq +71 -> 98
    //   30: aload 4
    //   32: invokeinterface 189 1 0
    //   37: checkcast 145	android/content/pm/ApplicationInfo
    //   40: astore 5
    //   42: aload 5
    //   44: getfield 149	android/content/pm/ApplicationInfo:flags	I
    //   47: iconst_1
    //   48: iand
    //   49: ifeq +44 -> 93
    //   52: iconst_1
    //   53: istore_1
    //   54: aload 5
    //   56: getfield 192	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   59: aload_0
    //   60: invokevirtual 195	android/content/Context:getPackageName	()Ljava/lang/String;
    //   63: invokevirtual 198	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   66: istore_2
    //   67: iload_1
    //   68: ifne +7 -> 75
    //   71: iload_2
    //   72: ifeq -52 -> 20
    //   75: aload 4
    //   77: invokeinterface 201 1 0
    //   82: goto -62 -> 20
    //   85: astore_0
    //   86: aload_3
    //   87: astore_0
    //   88: ldc 2
    //   90: monitorexit
    //   91: aload_0
    //   92: areturn
    //   93: iconst_0
    //   94: istore_1
    //   95: goto -41 -> 54
    //   98: aload_3
    //   99: astore_0
    //   100: goto -12 -> 88
    //   103: astore_0
    //   104: ldc 2
    //   106: monitorexit
    //   107: aload_0
    //   108: athrow
    //   109: astore_0
    //   110: aconst_null
    //   111: astore_0
    //   112: goto -24 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	paramContext	Context
    //   53	42	1	i	int
    //   66	6	2	bool	boolean
    //   7	92	3	localList	List
    //   18	58	4	localIterator	Iterator
    //   40	15	5	localApplicationInfo	ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   12	20	85	java/lang/Exception
    //   20	52	85	java/lang/Exception
    //   54	67	85	java/lang/Exception
    //   75	82	85	java/lang/Exception
    //   3	8	103	finally
    //   12	20	103	finally
    //   20	52	103	finally
    //   54	67	103	finally
    //   75	82	103	finally
    //   3	8	109	java/lang/Exception
  }
  
  public static List<ResolveInfo> L(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      Intent localIntent = new Intent("android.intent.action.MAIN", null);
      localIntent.addCategory("android.intent.category.LAUNCHER");
      paramContext = paramContext.queryIntentActivities(localIntent, 0);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static boolean M(Context paramContext)
  {
    return k(paramContext, "com.android.vending");
  }
  
  public static boolean N(Context paramContext)
  {
    return l(paramContext, "android.settings.ACCESSIBILITY_SETTINGS");
  }
  
  public static boolean O(Context paramContext)
  {
    return "Coolpad Y76".equals(wonder.city.baseutility.utility.g.d.a(paramContext).f());
  }
  
  public static boolean P(Context paramContext)
  {
    int i = wonder.city.baseutility.utility.a.a.E(paramContext);
    return (i == 0) || (i == 1) || ((i > 3) && ((i - 4) % 3 == 0));
  }
  
  /* Error */
  public static String Q(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 259	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: ldc_w 261
    //   7: invokevirtual 155	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   10: checkcast 263	android/net/wifi/WifiManager
    //   13: astore_0
    //   14: aload_0
    //   15: ifnull +81 -> 96
    //   18: aload_0
    //   19: invokevirtual 267	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   22: astore_0
    //   23: aload_0
    //   24: ifnull +67 -> 91
    //   27: aload_0
    //   28: invokevirtual 272	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   31: astore_0
    //   32: aload_0
    //   33: astore_2
    //   34: aload_0
    //   35: ifnull +45 -> 80
    //   38: aload_0
    //   39: astore_1
    //   40: aload_0
    //   41: ldc_w 274
    //   44: invokevirtual 278	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   47: ifeq +9 -> 56
    //   50: aload_0
    //   51: iconst_1
    //   52: invokevirtual 282	java/lang/String:substring	(I)Ljava/lang/String;
    //   55: astore_1
    //   56: aload_1
    //   57: astore_2
    //   58: aload_1
    //   59: ldc_w 274
    //   62: invokevirtual 285	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   65: ifeq +15 -> 80
    //   68: aload_1
    //   69: iconst_0
    //   70: aload_1
    //   71: invokevirtual 289	java/lang/String:length	()I
    //   74: iconst_1
    //   75: isub
    //   76: invokevirtual 292	java/lang/String:substring	(II)Ljava/lang/String;
    //   79: astore_2
    //   80: aload_2
    //   81: areturn
    //   82: astore_0
    //   83: aconst_null
    //   84: areturn
    //   85: astore_0
    //   86: aconst_null
    //   87: astore_0
    //   88: goto -65 -> 23
    //   91: aconst_null
    //   92: astore_0
    //   93: goto -61 -> 32
    //   96: aconst_null
    //   97: astore_0
    //   98: goto -75 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	paramContext	Context
    //   39	32	1	localObject1	Object
    //   33	48	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	14	82	java/lang/NullPointerException
    //   18	23	85	java/lang/NoSuchMethodError
  }
  
  private static List<wonder.city.baseutility.utility.g.a> R(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = (UsageStatsManager)paramContext.getApplicationContext().getSystemService("usagestats");
    long l = System.currentTimeMillis();
    if (localObject2 != null)
    {
      localObject1 = ((UsageStatsManager)localObject2).queryUsageStats(4, l - 200L, l);
      if ((localObject1 != null) && (!((List)localObject1).isEmpty())) {
        break label110;
      }
      l = System.currentTimeMillis();
      if (localObject2 == null) {
        break label105;
      }
    }
    label105:
    for (Object localObject1 = ((UsageStatsManager)localObject2).queryUsageStats(4, l - 3000L, l);; localObject1 = null)
    {
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (!((List)localObject1).isEmpty()) {
          break label113;
        }
      }
      return localArrayList;
      localObject1 = null;
      break;
    }
    label110:
    localObject2 = localObject1;
    label113:
    localObject1 = paramContext.getPackageManager();
    localObject2 = ((List)localObject2).iterator();
    for (;;)
    {
      Object localObject3;
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (UsageStats)((Iterator)localObject2).next();
        if (localObject3 != null)
        {
          localObject3 = ((UsageStats)localObject3).getPackageName();
          if ((TextUtils.isEmpty((CharSequence)localObject3)) || (((String)localObject3).equals(paramContext.getPackageName()))) {}
        }
      }
      else
      {
        try
        {
          Object localObject4 = ((PackageManager)localObject1).getApplicationInfo((String)localObject3, 0);
          if ((localObject4 == null) || ((((ApplicationInfo)localObject4).flags & 0x1) != 0)) {
            continue;
          }
          localObject4 = (String)((PackageManager)localObject1).getApplicationLabel((ApplicationInfo)localObject4);
          wonder.city.baseutility.utility.g.a localA = new wonder.city.baseutility.utility.g.a();
          localA.a((String)localObject4);
          localA.b((String)localObject3);
          localA.c("0MB");
          localArrayList.add(localA);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
        return localArrayList;
      }
    }
  }
  
  public static Bitmap a(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      return null;
    }
    try
    {
      paramDrawable = ((BitmapDrawable)paramDrawable).getBitmap();
      return paramDrawable;
    }
    catch (Exception paramDrawable)
    {
      paramDrawable.printStackTrace();
    }
    return null;
  }
  
  @TargetApi(9)
  public static Drawable a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramContext = paramContext.getPackageInfo(paramString, 0).applicationInfo.loadIcon(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static Uri a(String paramString, int paramInt)
  {
    return Uri.parse("android.resource://" + paramString + "/drawable/" + paramInt);
  }
  
  public static SpannableStringBuilder a(Context paramContext, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, Object... paramVarArgs)
  {
    return a(paramContext, paramContext.getString(paramInt1, paramVarArgs), paramInt2, paramInt3, paramBoolean, paramVarArgs.length);
  }
  
  public static SpannableStringBuilder a(Context paramContext, int paramInt1, int paramInt2, Object... paramVarArgs)
  {
    return a(paramContext, paramContext.getString(paramInt1, paramVarArgs), paramInt2, 0, false, paramVarArgs.length);
  }
  
  public static SpannableStringBuilder a(Context paramContext, String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    paramString = new SpannableStringBuilder(paramString);
    while (paramInt3 > 0)
    {
      String str = paramString.toString();
      int i = str.indexOf("(") + 1;
      int j = str.indexOf(")");
      if (j > i)
      {
        if (paramInt1 != 0) {
          paramString.setSpan(new ForegroundColorSpan(paramContext.getResources().getColor(paramInt1)), i, j, 33);
        }
        if (paramInt2 != 0) {
          paramString.setSpan(new AbsoluteSizeSpan(paramContext.getResources().getDimensionPixelSize(paramInt2)), i, j, 33);
        }
        if (paramBoolean) {
          paramString.setSpan(new StyleSpan(1), i, j, 33);
        }
      }
      j = str.indexOf(")");
      if ((j > i) && (j > 1))
      {
        paramString.delete(i - 1, i);
        paramString.delete(j - 1, j);
      }
      paramInt3 -= 1;
    }
    return paramString;
  }
  
  private static String a(int paramInt)
  {
    if (paramInt >= 1024)
    {
      float f = (float)((int)(paramInt / 1024.0F * 10.0F) / 10.0D);
      return f + "MB";
    }
    return paramInt + "KB";
  }
  
  /* Error */
  public static String a(Object paramObject)
  {
    // Byte code:
    //   0: new 450	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 451	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_3
    //   8: new 453	java/io/ObjectOutputStream
    //   11: dup
    //   12: aload_3
    //   13: invokespecial 456	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_2
    //   17: aload_2
    //   18: astore 5
    //   20: aload_3
    //   21: astore 4
    //   23: aload_2
    //   24: aload_0
    //   25: invokevirtual 460	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   28: aload_2
    //   29: astore 5
    //   31: aload_3
    //   32: astore 4
    //   34: aload_3
    //   35: ldc_w 462
    //   38: invokevirtual 465	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
    //   41: astore_0
    //   42: aload_2
    //   43: astore 5
    //   45: aload_3
    //   46: astore 4
    //   48: aload_0
    //   49: ldc_w 467
    //   52: invokestatic 472	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   55: astore_1
    //   56: aload_1
    //   57: astore_0
    //   58: aload_2
    //   59: ifnull +7 -> 66
    //   62: aload_2
    //   63: invokevirtual 475	java/io/ObjectOutputStream:close	()V
    //   66: aload_0
    //   67: astore_1
    //   68: aload_3
    //   69: ifnull +9 -> 78
    //   72: aload_3
    //   73: invokevirtual 476	java/io/ByteArrayOutputStream:close	()V
    //   76: aload_0
    //   77: astore_1
    //   78: aload_1
    //   79: areturn
    //   80: astore_1
    //   81: aload_1
    //   82: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   85: goto -19 -> 66
    //   88: astore_1
    //   89: aload_1
    //   90: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   93: aload_0
    //   94: areturn
    //   95: astore_1
    //   96: aconst_null
    //   97: astore_2
    //   98: aconst_null
    //   99: astore_3
    //   100: aconst_null
    //   101: astore_0
    //   102: aload_2
    //   103: astore 5
    //   105: aload_3
    //   106: astore 4
    //   108: aload_1
    //   109: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   112: aload_2
    //   113: ifnull +7 -> 120
    //   116: aload_2
    //   117: invokevirtual 475	java/io/ObjectOutputStream:close	()V
    //   120: aload_0
    //   121: astore_1
    //   122: aload_3
    //   123: ifnull -45 -> 78
    //   126: aload_3
    //   127: invokevirtual 476	java/io/ByteArrayOutputStream:close	()V
    //   130: aload_0
    //   131: areturn
    //   132: astore_1
    //   133: aload_1
    //   134: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   137: aload_0
    //   138: areturn
    //   139: astore_1
    //   140: aload_1
    //   141: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   144: goto -24 -> 120
    //   147: astore_0
    //   148: aconst_null
    //   149: astore 5
    //   151: aconst_null
    //   152: astore_3
    //   153: aload 5
    //   155: ifnull +8 -> 163
    //   158: aload 5
    //   160: invokevirtual 475	java/io/ObjectOutputStream:close	()V
    //   163: aload_3
    //   164: ifnull +7 -> 171
    //   167: aload_3
    //   168: invokevirtual 476	java/io/ByteArrayOutputStream:close	()V
    //   171: aload_0
    //   172: athrow
    //   173: astore_1
    //   174: aload_1
    //   175: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   178: goto -15 -> 163
    //   181: astore_1
    //   182: aload_1
    //   183: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   186: goto -15 -> 171
    //   189: astore_0
    //   190: aconst_null
    //   191: astore 5
    //   193: goto -40 -> 153
    //   196: astore_0
    //   197: aload 4
    //   199: astore_3
    //   200: goto -47 -> 153
    //   203: astore_1
    //   204: aconst_null
    //   205: astore_2
    //   206: aconst_null
    //   207: astore_0
    //   208: goto -106 -> 102
    //   211: astore_1
    //   212: aconst_null
    //   213: astore_0
    //   214: goto -112 -> 102
    //   217: astore_1
    //   218: goto -116 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	paramObject	Object
    //   55	24	1	localObject1	Object
    //   80	2	1	localIOException1	java.io.IOException
    //   88	2	1	localIOException2	java.io.IOException
    //   95	14	1	localException1	Exception
    //   121	1	1	localObject2	Object
    //   132	2	1	localIOException3	java.io.IOException
    //   139	2	1	localIOException4	java.io.IOException
    //   173	2	1	localIOException5	java.io.IOException
    //   181	2	1	localIOException6	java.io.IOException
    //   203	1	1	localException2	Exception
    //   211	1	1	localException3	Exception
    //   217	1	1	localException4	Exception
    //   16	190	2	localObjectOutputStream1	java.io.ObjectOutputStream
    //   7	193	3	localObject3	Object
    //   21	177	4	localObject4	Object
    //   18	174	5	localObjectOutputStream2	java.io.ObjectOutputStream
    // Exception table:
    //   from	to	target	type
    //   62	66	80	java/io/IOException
    //   72	76	88	java/io/IOException
    //   0	8	95	java/lang/Exception
    //   126	130	132	java/io/IOException
    //   116	120	139	java/io/IOException
    //   0	8	147	finally
    //   158	163	173	java/io/IOException
    //   167	171	181	java/io/IOException
    //   8	17	189	finally
    //   23	28	196	finally
    //   34	42	196	finally
    //   48	56	196	finally
    //   108	112	196	finally
    //   8	17	203	java/lang/Exception
    //   23	28	211	java/lang/Exception
    //   34	42	211	java/lang/Exception
    //   48	56	217	java/lang/Exception
  }
  
  public static String a(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramArrayOfString != null)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        localStringBuilder.append(paramArrayOfString[i]);
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static HashMap<String, Integer> a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 481	java/io/ByteArrayInputStream
    //   5: dup
    //   6: aload_0
    //   7: ldc_w 467
    //   10: invokestatic 486	java/net/URLDecoder:decode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   13: ldc_w 462
    //   16: invokevirtual 490	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   19: invokespecial 493	java/io/ByteArrayInputStream:<init>	([B)V
    //   22: astore_0
    //   23: new 495	java/io/ObjectInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 498	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore_1
    //   32: aload_1
    //   33: invokevirtual 501	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   36: checkcast 503	java/util/HashMap
    //   39: astore_2
    //   40: aload_1
    //   41: ifnull +7 -> 48
    //   44: aload_1
    //   45: invokevirtual 504	java/io/ObjectInputStream:close	()V
    //   48: aload_0
    //   49: ifnull +7 -> 56
    //   52: aload_0
    //   53: invokevirtual 505	java/io/ByteArrayInputStream:close	()V
    //   56: aload_2
    //   57: areturn
    //   58: astore_1
    //   59: aload_1
    //   60: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   63: goto -15 -> 48
    //   66: astore_0
    //   67: aload_0
    //   68: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   71: aload_2
    //   72: areturn
    //   73: astore_0
    //   74: aconst_null
    //   75: astore_0
    //   76: aconst_null
    //   77: astore_1
    //   78: aload_0
    //   79: ifnull +7 -> 86
    //   82: aload_0
    //   83: invokevirtual 504	java/io/ObjectInputStream:close	()V
    //   86: aload_1
    //   87: ifnull +93 -> 180
    //   90: aload_1
    //   91: invokevirtual 505	java/io/ByteArrayInputStream:close	()V
    //   94: aconst_null
    //   95: areturn
    //   96: astore_0
    //   97: aload_0
    //   98: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   101: goto -15 -> 86
    //   104: astore_0
    //   105: aload_0
    //   106: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   109: aconst_null
    //   110: areturn
    //   111: astore_1
    //   112: aconst_null
    //   113: astore_0
    //   114: aload_2
    //   115: ifnull +7 -> 122
    //   118: aload_2
    //   119: invokevirtual 504	java/io/ObjectInputStream:close	()V
    //   122: aload_0
    //   123: ifnull +7 -> 130
    //   126: aload_0
    //   127: invokevirtual 505	java/io/ByteArrayInputStream:close	()V
    //   130: aload_1
    //   131: athrow
    //   132: astore_2
    //   133: aload_2
    //   134: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   137: goto -15 -> 122
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   145: goto -15 -> 130
    //   148: astore_1
    //   149: goto -35 -> 114
    //   152: astore_3
    //   153: aload_1
    //   154: astore_2
    //   155: aload_3
    //   156: astore_1
    //   157: goto -43 -> 114
    //   160: astore_1
    //   161: aconst_null
    //   162: astore_2
    //   163: aload_0
    //   164: astore_1
    //   165: aload_2
    //   166: astore_0
    //   167: goto -89 -> 78
    //   170: astore_2
    //   171: aload_0
    //   172: astore_2
    //   173: aload_1
    //   174: astore_0
    //   175: aload_2
    //   176: astore_1
    //   177: goto -99 -> 78
    //   180: aconst_null
    //   181: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	182	0	paramString	String
    //   31	14	1	localObjectInputStream	java.io.ObjectInputStream
    //   58	2	1	localIOException1	java.io.IOException
    //   77	14	1	localObject1	Object
    //   111	20	1	localObject2	Object
    //   148	6	1	localObject3	Object
    //   156	1	1	localObject4	Object
    //   160	1	1	localException1	Exception
    //   164	13	1	str1	String
    //   1	118	2	localHashMap	HashMap
    //   132	2	2	localIOException2	java.io.IOException
    //   154	12	2	localObject5	Object
    //   170	1	2	localException2	Exception
    //   172	4	2	str2	String
    //   152	4	3	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   44	48	58	java/io/IOException
    //   52	56	66	java/io/IOException
    //   2	23	73	java/lang/Exception
    //   82	86	96	java/io/IOException
    //   90	94	104	java/io/IOException
    //   2	23	111	finally
    //   118	122	132	java/io/IOException
    //   126	130	140	java/io/IOException
    //   23	32	148	finally
    //   32	40	152	finally
    //   23	32	160	java/lang/Exception
    //   32	40	170	java/lang/Exception
  }
  
  public static List<wonder.city.baseutility.utility.g.a> a(Context paramContext, int paramInt, boolean paramBoolean1, HashMap<String, Integer> paramHashMap, List<ActivityManager.RunningAppProcessInfo> paramList, ActivityManager paramActivityManager, boolean paramBoolean2)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (!paramBoolean2) {}
    for (List localList = q(paramContext);; localList = null)
    {
      String str = paramContext.getPackageName();
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      ArrayList localArrayList3 = new ArrayList();
      ArrayList localArrayList4 = new ArrayList();
      HashMap localHashMap = new HashMap();
      if (paramList != null)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          Object localObject1 = (ActivityManager.RunningAppProcessInfo)paramList.next();
          if (((ActivityManager.RunningAppProcessInfo)localObject1).importance >= 100)
          {
            String[] arrayOfString = ((ActivityManager.RunningAppProcessInfo)localObject1).pkgList;
            int i = ((ActivityManager.RunningAppProcessInfo)localObject1).pid;
            Object localObject2 = new int[1];
            localObject2[0] = i;
            int j = 0;
            label175:
            label200:
            int k;
            if (paramBoolean1)
            {
              localObject2 = paramActivityManager.getProcessMemoryInfo((int[])localObject2);
              if ((localObject2 != null) && (localObject2.length > 0))
              {
                i = localObject2[0].dalvikPrivateDirty;
                localHashMap.put(((ActivityManager.RunningAppProcessInfo)localObject1).processName, Integer.valueOf(i));
                if (i < paramInt) {
                  continue;
                }
                j = 0;
                if (j < arrayOfString.length)
                {
                  localObject1 = arrayOfString[j];
                  if ((paramBoolean2) || (localList == null) || (!localList.contains(localObject1))) {
                    break label344;
                  }
                  k = i;
                }
              }
            }
            for (;;)
            {
              j += 1;
              i = k;
              break label200;
              break;
              i = 0;
              break label175;
              if (paramHashMap.containsKey(((ActivityManager.RunningAppProcessInfo)localObject1).processName)) {}
              try
              {
                j = ((Integer)paramHashMap.get(((ActivityManager.RunningAppProcessInfo)localObject1).processName)).intValue();
                i = j;
                if (j != 0) {
                  break label175;
                }
                localObject2 = paramActivityManager.getProcessMemoryInfo((int[])localObject2);
                if ((localObject2 != null) && (localObject2.length > 0)) {
                  i = localObject2[0].dalvikPrivateDirty;
                }
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  j = 0;
                }
                i = 0;
              }
              break label175;
              label344:
              k = i;
              if (!str.equals(localObject1)) {
                if (localArrayList4.contains(localObject1))
                {
                  localObject1 = a(localArrayList3, (String)localObject1);
                  k = i;
                  if (localObject1 != null)
                  {
                    if (((wonder.city.baseutility.utility.g.a)localObject1).d().contains("KB")) {
                      i += (int)Double.parseDouble(((wonder.city.baseutility.utility.g.a)localObject1).d().replace("KB", ""));
                    }
                    for (;;)
                    {
                      ((wonder.city.baseutility.utility.g.a)localObject1).c(a(i));
                      k = i;
                      break;
                      i += (int)(1024.0D * Double.parseDouble(((wonder.city.baseutility.utility.g.a)localObject1).d().replace("MB", "")));
                    }
                  }
                }
                else
                {
                  boolean bool = a(localPackageManager, (String)localObject1);
                  localObject2 = c(localPackageManager, (String)localObject1);
                  k = i;
                  if (localObject2 != null)
                  {
                    ((wonder.city.baseutility.utility.g.a)localObject2).a(bool);
                    ((wonder.city.baseutility.utility.g.a)localObject2).c(a(i));
                    ((wonder.city.baseutility.utility.g.a)localObject2).a(0);
                    localArrayList4.add(localObject1);
                    localArrayList3.add(localObject2);
                    if (bool)
                    {
                      localArrayList1.add(localObject2);
                      k = i;
                    }
                    else
                    {
                      localArrayList2.add(localObject2);
                      k = i;
                    }
                  }
                }
              }
            }
          }
        }
        if (paramBoolean1)
        {
          paramHashMap = a(localHashMap);
          if ((paramHashMap != null) && (paramHashMap.length() > 0)) {
            h(paramContext, paramHashMap);
          }
        }
      }
      return a(paramContext, localArrayList2, localArrayList1);
    }
  }
  
  public static List<wonder.city.baseutility.utility.g.a> a(Context paramContext, int paramInt, boolean paramBoolean1, HashMap<String, Integer> paramHashMap, boolean paramBoolean2)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (!paramBoolean2) {}
    for (List localList = q(paramContext);; localList = null)
    {
      String str = paramContext.getPackageName();
      HashMap localHashMap = new HashMap();
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      ArrayList localArrayList3 = new ArrayList();
      ArrayList localArrayList4 = new ArrayList();
      Object localObject1 = f.a(paramContext);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        label175:
        label298:
        label431:
        label460:
        label555:
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (wonder.city.baseutility.utility.e.a)((Iterator)localObject1).next();
          int i = ((wonder.city.baseutility.utility.e.a)localObject2).d;
          Object localObject3 = new int[1];
          localObject3[0] = i;
          int j = 0;
          if (paramBoolean1)
          {
            localObject3 = localActivityManager.getProcessMemoryInfo((int[])localObject3);
            if ((localObject3 != null) && (localObject3.length > 0))
            {
              i = localObject3[0].dalvikPrivateDirty;
              localHashMap.put(((wonder.city.baseutility.utility.e.a)localObject2).a(), Integer.valueOf(i));
              if (i < paramInt) {
                continue;
              }
              localObject2 = ((wonder.city.baseutility.utility.e.a)localObject2).a();
              if (((!paramBoolean2) && (localList != null) && (localList.contains(localObject2))) || (str.equals(localObject2))) {
                continue;
              }
              if (!localArrayList4.contains(localObject2)) {
                break label460;
              }
              localObject2 = a(localArrayList1, (String)localObject2);
              if (localObject2 != null)
              {
                if (!((wonder.city.baseutility.utility.g.a)localObject2).d().contains("KB")) {
                  break label431;
                }
                i = (int)Double.parseDouble(((wonder.city.baseutility.utility.g.a)localObject2).d().replace("KB", "")) + i;
                ((wonder.city.baseutility.utility.g.a)localObject2).c(a(i));
              }
            }
          }
          for (;;)
          {
            if (!paramBoolean1) {
              break label555;
            }
            localObject2 = a(localHashMap);
            if ((localObject2 == null) || (((String)localObject2).length() <= 0)) {
              break;
            }
            h(paramContext, (String)localObject2);
            break;
            i = 0;
            break label175;
            if (paramHashMap.containsKey(((wonder.city.baseutility.utility.e.a)localObject2).a())) {}
            try
            {
              j = ((Integer)paramHashMap.get(((wonder.city.baseutility.utility.e.a)localObject2).a())).intValue();
              i = j;
              if (j != 0) {
                break label175;
              }
              localObject3 = localActivityManager.getProcessMemoryInfo((int[])localObject3);
              if ((localObject3 != null) && (localObject3.length > 0)) {
                i = localObject3[0].dalvikPrivateDirty;
              }
            }
            catch (Exception localException)
            {
              for (;;)
              {
                j = 0;
              }
              i = 0;
            }
            break label175;
            i = (int)(1024.0D * Double.parseDouble(((wonder.city.baseutility.utility.g.a)localObject2).d().replace("MB", ""))) + i;
            break label298;
            boolean bool = a(localPackageManager, (String)localObject2);
            localObject3 = c(localPackageManager, (String)localObject2);
            if (localObject3 != null)
            {
              ((wonder.city.baseutility.utility.g.a)localObject3).a(bool);
              ((wonder.city.baseutility.utility.g.a)localObject3).c(a(i));
              ((wonder.city.baseutility.utility.g.a)localObject3).a(0);
              localArrayList4.add(localObject2);
              localArrayList1.add(localObject3);
              if (bool) {
                localArrayList2.add(localObject3);
              } else {
                localArrayList3.add(localObject3);
              }
            }
          }
        }
      }
      return a(paramContext, localArrayList3, localArrayList2);
    }
  }
  
  public static List<wonder.city.baseutility.utility.g.a> a(Context paramContext, HashMap<String, Integer> paramHashMap, boolean paramBoolean)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 24) {
      localObject = R(paramContext);
    }
    List localList;
    do
    {
      return localObject;
      if (Build.VERSION.SDK_INT > 22) {
        return a(paramContext, 1024, false, paramHashMap, paramBoolean);
      }
      localObject = (ActivityManager)paramContext.getSystemService("activity");
      localList = ((ActivityManager)localObject).getRunningAppProcesses();
      if ((localList == null) || (localList.size() <= 1)) {
        return a(paramContext, 1024, false, paramHashMap, paramBoolean);
      }
      localList = a(paramContext, 1024, false, paramHashMap, localList, (ActivityManager)localObject, paramBoolean);
      localObject = localList;
    } while (localList.size() != 0);
    return a(paramContext, 1024, false, paramHashMap, paramBoolean);
  }
  
  public static List<wonder.city.baseutility.utility.g.a> a(Context paramContext, List<wonder.city.baseutility.utility.g.a> paramList1, List<wonder.city.baseutility.utility.g.a> paramList2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(paramList1);
    Random localRandom = new Random();
    int i;
    if (paramList1.size() <= 5)
    {
      i = localRandom.nextInt(4) + 8;
      if (i >= paramList2.size()) {}
    }
    for (;;)
    {
      int j = 0;
      while (j < i)
      {
        localArrayList.add(paramList2.get(j));
        j += 1;
      }
      i = paramList2.size();
      continue;
      j = localRandom.nextInt(4) + 4;
      i = j;
      if (j >= paramList2.size()) {
        i = paramList2.size();
      }
    }
    return b(paramContext, localArrayList);
  }
  
  public static List<wonder.city.baseutility.utility.g.a> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = L(paramContext);
    String str1 = paramContext.getPackageName();
    paramContext = localArrayList;
    if (localObject != null) {}
    try
    {
      Collections.sort((List)localObject, new ResolveInfo.DisplayNameComparator(localPackageManager));
      localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        if (((Iterator)localObject).hasNext())
        {
          paramContext = (ResolveInfo)((Iterator)localObject).next();
          String str2 = paramContext.activityInfo.packageName;
          if ((str2 == null) || ("".equals(str2.trim())) || ((!paramBoolean) && (str2.equals(str1)))) {
            continue;
          }
          wonder.city.baseutility.utility.g.a localA = new wonder.city.baseutility.utility.g.a();
          try
          {
            paramContext = (String)paramContext.loadLabel(localPackageManager);
            boolean bool = a(localPackageManager, str2);
            localA.a(paramContext);
            localA.b(str2);
            localA.a(bool);
            localArrayList.add(localA);
          }
          catch (SecurityException paramContext)
          {
            for (;;)
            {
              paramContext = "";
            }
          }
        }
      }
      paramContext = localArrayList;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
  }
  
  public static List<wonder.city.baseutility.utility.g.a> a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    long l = paramContext.getSharedPreferences("sp_w_l", 0).getLong("sp_save_lat", 0L);
    Object localObject;
    if ((paramBoolean2) && (System.currentTimeMillis() - l < 86400000L))
    {
      localObject = G(paramContext);
      if (((List)localObject).size() == 0) {}
    }
    List localList;
    do
    {
      return localObject;
      localList = a(paramContext, paramBoolean1);
      localObject = localList;
    } while (localList == null);
    a(paramContext, localList);
    return localList;
  }
  
  public static wonder.city.baseutility.utility.g.a a(List<wonder.city.baseutility.utility.g.a> paramList, String paramString)
  {
    Object localObject;
    if ((paramList == null) || (paramList.size() == 0) || (paramString == null))
    {
      localObject = null;
      return localObject;
    }
    int j = paramList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label78;
      }
      wonder.city.baseutility.utility.g.a localA = (wonder.city.baseutility.utility.g.a)paramList.get(i);
      localObject = localA;
      if (paramString.trim().equals(localA.b().trim())) {
        break;
      }
      i += 1;
    }
    label78:
    return null;
  }
  
  public static void a(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    if (localActivityManager == null) {
      return;
    }
    List localList = r(paramContext);
    Iterator localIterator = f.a(paramContext).iterator();
    label30:
    label84:
    label94:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        paramContext = ((wonder.city.baseutility.utility.e.a)localIterator.next()).c;
        if (paramContext == null) {
          continue;
        }
        if (paramContext.indexOf(":") != -1) {
          break label84;
        }
      }
      for (;;)
      {
        if (localList.contains(paramContext)) {
          break label94;
        }
        localActivityManager.killBackgroundProcesses(paramContext);
        break label30;
        break;
        paramContext = paramContext.split(":")[0];
      }
    }
  }
  
  public static void a(Context paramContext, Class<?> paramClass)
  {
    paramClass = new Intent(paramContext, paramClass);
    paramClass.setFlags(67108864);
    paramClass.addFlags(536870912);
    paramContext.startActivity(paramClass);
  }
  
  public static void a(Context paramContext, Class<?> paramClass, int paramInt1, int paramInt2, String paramString)
  {
    Intent localIntent1 = new Intent();
    localIntent1.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramContext.getString(paramInt1));
    localIntent1.putExtra("duplicate", false);
    localIntent1.putExtra("android.intent.extra.shortcut.ICON", BitmapFactory.decodeResource(paramContext.getResources(), paramInt2));
    Intent localIntent2 = new Intent();
    String str = paramContext.getPackageName();
    localIntent2.setAction(str + ".shortcut." + paramString);
    localIntent2.addCategory("android.intent.category.DEFAULT");
    localIntent2.setComponent(new ComponentName(paramContext, paramClass));
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    paramContext.sendBroadcast(localIntent1);
  }
  
  public static void a(Context paramContext, final String paramString1, final String paramString2)
  {
    new Thread()
    {
      static
      {
        Init.doFixC(1.class, 851892790);
        if (Build.VERSION.SDK_INT < 0) {
          z2.class.toString();
        }
      }
      
      public native void run();
    }.start();
  }
  
  public static void a(Context paramContext, List<wonder.city.baseutility.utility.g.a> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      paramContext = paramContext.getSharedPreferences("sp_w_l", 0).edit();
      StringBuilder localStringBuilder = new StringBuilder();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        wonder.city.baseutility.utility.g.a localA = (wonder.city.baseutility.utility.g.a)paramList.next();
        localStringBuilder.append(localA.b()).append(":").append(localA.a()).append(";");
      }
      paramContext.putLong("sp_save_lat", System.currentTimeMillis());
      paramContext.putString("canlaunchapps", localStringBuilder.toString());
      paramContext.apply();
    }
  }
  
  public static void a(SharedPreferences paramSharedPreferences, String paramString, List<String> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return;
    }
    Iterator localIterator = paramList.iterator();
    paramList = "";
    int i = 0;
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      i += 1;
      if (i == 1) {}
      for (paramList = paramList + str;; paramList = paramList + ";" + str) {
        break;
      }
    }
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString, paramList);
    paramSharedPreferences.apply();
  }
  
  public static void a(View paramView)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramView.getContext().getSystemService("input_method");
    if (localInputMethodManager.isActive()) {
      localInputMethodManager.hideSoftInputFromWindow(paramView.getApplicationWindowToken(), 0);
    }
  }
  
  public static boolean a(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 0).applicationInfo;
      int i;
      if (paramPackageManager != null) {
        i = paramPackageManager.flags;
      }
      return (i & 0x1) != 0;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return false;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      return paramString2 == null;
    }
    return paramString1.equals(paramString2);
  }
  
  @TargetApi(9)
  public static Drawable b(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 0).applicationInfo.loadIcon(paramPackageManager);
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return null;
  }
  
  private static List<wonder.city.baseutility.utility.g.a> b(Context paramContext, List<wonder.city.baseutility.utility.g.a> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return paramList;
    }
    if (!c(paramContext))
    {
      Collections.sort(paramList, new c());
      return paramList;
    }
    Collections.sort(paramList, new d());
    return paramList;
  }
  
  public static void b(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    if (localActivityManager == null) {
      return;
    }
    String str = n(paramContext);
    Iterator localIterator = f.a(paramContext).iterator();
    label30:
    label98:
    label108:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        paramContext = ((wonder.city.baseutility.utility.e.a)localIterator.next()).c;
        if (paramContext == null) {
          continue;
        }
        if (paramContext.indexOf(":") != -1) {
          break label98;
        }
      }
      for (;;)
      {
        if ((paramContext == null) || ("".equals(paramContext.trim())) || (paramContext.equals(str))) {
          break label108;
        }
        localActivityManager.killBackgroundProcesses(paramContext);
        break label30;
        break;
        paramContext = paramContext.split(":")[0];
      }
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("sp_w_l", 0).edit();
    paramContext.putString("sp_f_pk", paramString);
    paramContext.apply();
  }
  
  @TargetApi(9)
  public static wonder.city.baseutility.utility.g.a c(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      wonder.city.baseutility.utility.g.a localA = new wonder.city.baseutility.utility.g.a();
      paramPackageManager = paramPackageManager.getApplicationLabel(paramPackageManager.getPackageInfo(paramString, 0).applicationInfo).toString();
      localA.b(paramString);
      localA.a(paramPackageManager);
      return localA;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return null;
  }
  
  public static void c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("sp_w_l", 0).edit();
    paramContext.putString("sp_f_gpk", paramString);
    paramContext.apply();
  }
  
  public static boolean c(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.getLanguage().endsWith("ar");
  }
  
  public static List<wonder.city.baseutility.utility.g.a> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1;
    label25:
    try
    {
      localObject1 = new ArrayList();
    }
    finally {}
    try
    {
      localObject4 = J(paramContext);
      localObject1 = localObject4;
    }
    catch (RuntimeException localRuntimeException)
    {
      break label25;
    }
    Object localObject4 = paramContext.getPackageName();
    PackageManager localPackageManager = paramContext.getPackageManager();
    if ((localObject1 != null) && (!((List)localObject1).isEmpty())) {
      localObject1 = ((List)localObject1).iterator();
    }
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject5 = (ApplicationInfo)((Iterator)localObject1).next();
      if (!((String)localObject4).equals(((ApplicationInfo)localObject5).packageName))
      {
        wonder.city.baseutility.utility.g.a localA = new wonder.city.baseutility.utility.g.a();
        localA.a(((ApplicationInfo)localObject5).loadLabel(localPackageManager).toString());
        localA.b(((ApplicationInfo)localObject5).packageName);
        if ((((ApplicationInfo)localObject5).flags & 0x1) == 0) {
          localA.a(false);
        }
        for (;;)
        {
          localArrayList.add(localA);
          break;
          localA.a(true);
        }
        Object localObject3 = e(paramContext);
        if (localObject3 == null) {
          return localArrayList;
        }
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject5 = (PackageInfo)((Iterator)localObject3).next();
          if (!((String)localObject4).equals(((PackageInfo)localObject5).packageName))
          {
            localA = new wonder.city.baseutility.utility.g.a();
            localA.b(((PackageInfo)localObject5).packageName);
            ApplicationInfo localApplicationInfo = ((PackageInfo)localObject5).applicationInfo;
            if (localApplicationInfo != null)
            {
              localA.a(localApplicationInfo.loadLabel(localPackageManager).toString());
              if ((localApplicationInfo.flags & 0x1) == 0) {
                localA.a(false);
              }
            }
            for (;;)
            {
              localArrayList.add(localA);
              break;
              localA.a(true);
              continue;
              localA.a(((PackageInfo)localObject5).packageName);
              localA.a(false);
            }
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static void d(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {}
    int i;
    String str;
    do
    {
      return;
      String[] arrayOfString = j(paramContext);
      if ((arrayOfString == null) || (arrayOfString.length == 0)) {
        break label141;
      }
      int j = arrayOfString.length;
      localObject = "";
      i = 0;
      if (i >= j) {
        break;
      }
      str = arrayOfString[i];
    } while (paramString.equals(str));
    if ("".equals(localObject)) {}
    for (Object localObject = str;; localObject = (String)localObject + ";" + str)
    {
      i += 1;
      break;
    }
    c(paramContext, (String)localObject + ";" + paramString);
    return;
    label141:
    c(paramContext, paramString);
  }
  
  /* Error */
  public static List<PackageInfo> e(Context paramContext)
  {
    // Byte code:
    //   0: new 99	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 100	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: invokevirtual 166	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: astore_2
    //   13: invokestatic 869	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   16: astore_0
    //   17: aload_0
    //   18: ifnonnull +24 -> 42
    //   21: iconst_0
    //   22: ifeq +11 -> 33
    //   25: new 253	java/lang/NullPointerException
    //   28: dup
    //   29: invokespecial 870	java/lang/NullPointerException:<init>	()V
    //   32: athrow
    //   33: aconst_null
    //   34: areturn
    //   35: astore_0
    //   36: aload_0
    //   37: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   40: aconst_null
    //   41: areturn
    //   42: aload_0
    //   43: ldc_w 872
    //   46: invokevirtual 876	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   49: astore 4
    //   51: aload 4
    //   53: ifnonnull +22 -> 75
    //   56: iconst_0
    //   57: ifeq -24 -> 33
    //   60: new 253	java/lang/NullPointerException
    //   63: dup
    //   64: invokespecial 870	java/lang/NullPointerException:<init>	()V
    //   67: athrow
    //   68: astore_0
    //   69: aload_0
    //   70: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   73: aconst_null
    //   74: areturn
    //   75: new 878	java/io/BufferedReader
    //   78: dup
    //   79: new 880	java/io/InputStreamReader
    //   82: dup
    //   83: aload 4
    //   85: invokevirtual 886	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   88: invokespecial 887	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   91: invokespecial 890	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   94: astore_1
    //   95: aload_1
    //   96: astore_0
    //   97: aload_1
    //   98: invokevirtual 893	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   101: astore 5
    //   103: aload 5
    //   105: ifnull +44 -> 149
    //   108: aload_1
    //   109: astore_0
    //   110: aload 5
    //   112: aload 5
    //   114: bipush 58
    //   116: invokevirtual 895	java/lang/String:indexOf	(I)I
    //   119: iconst_1
    //   120: iadd
    //   121: invokevirtual 282	java/lang/String:substring	(I)Ljava/lang/String;
    //   124: astore 5
    //   126: aload_1
    //   127: astore_0
    //   128: aload_3
    //   129: aload_2
    //   130: aload 5
    //   132: iconst_0
    //   133: invokevirtual 345	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   136: invokeinterface 134 2 0
    //   141: pop
    //   142: goto -47 -> 95
    //   145: astore_0
    //   146: goto -51 -> 95
    //   149: aload_1
    //   150: astore_0
    //   151: aload 4
    //   153: invokevirtual 898	java/lang/Process:waitFor	()I
    //   156: pop
    //   157: aload_1
    //   158: ifnull +7 -> 165
    //   161: aload_1
    //   162: invokevirtual 899	java/io/BufferedReader:close	()V
    //   165: aload_3
    //   166: areturn
    //   167: astore_2
    //   168: aload_1
    //   169: astore_0
    //   170: aload_2
    //   171: invokevirtual 900	java/lang/InterruptedException:printStackTrace	()V
    //   174: goto -17 -> 157
    //   177: astore_2
    //   178: aload_1
    //   179: astore_0
    //   180: aload_2
    //   181: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   184: aload_1
    //   185: ifnull -20 -> 165
    //   188: aload_1
    //   189: invokevirtual 899	java/io/BufferedReader:close	()V
    //   192: goto -27 -> 165
    //   195: astore_0
    //   196: aload_0
    //   197: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   200: goto -35 -> 165
    //   203: astore_0
    //   204: aload_0
    //   205: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   208: goto -43 -> 165
    //   211: astore_1
    //   212: aconst_null
    //   213: astore_0
    //   214: aload_0
    //   215: ifnull +7 -> 222
    //   218: aload_0
    //   219: invokevirtual 899	java/io/BufferedReader:close	()V
    //   222: aload_1
    //   223: athrow
    //   224: astore_0
    //   225: aload_0
    //   226: invokevirtual 477	java/io/IOException:printStackTrace	()V
    //   229: goto -7 -> 222
    //   232: astore_1
    //   233: goto -19 -> 214
    //   236: astore_2
    //   237: aconst_null
    //   238: astore_1
    //   239: goto -61 -> 178
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	242	0	paramContext	Context
    //   94	95	1	localBufferedReader	java.io.BufferedReader
    //   211	12	1	localObject1	Object
    //   232	1	1	localObject2	Object
    //   238	1	1	localObject3	Object
    //   12	118	2	localPackageManager	PackageManager
    //   167	4	2	localInterruptedException	InterruptedException
    //   177	4	2	localIOException1	java.io.IOException
    //   236	1	2	localIOException2	java.io.IOException
    //   7	159	3	localArrayList	ArrayList
    //   49	103	4	localProcess	Process
    //   101	30	5	str	String
    // Exception table:
    //   from	to	target	type
    //   25	33	35	java/io/IOException
    //   60	68	68	java/io/IOException
    //   128	142	145	android/content/pm/PackageManager$NameNotFoundException
    //   151	157	167	java/lang/InterruptedException
    //   97	103	177	java/io/IOException
    //   110	126	177	java/io/IOException
    //   128	142	177	java/io/IOException
    //   151	157	177	java/io/IOException
    //   170	174	177	java/io/IOException
    //   188	192	195	java/io/IOException
    //   161	165	203	java/io/IOException
    //   13	17	211	finally
    //   42	51	211	finally
    //   75	95	211	finally
    //   218	222	224	java/io/IOException
    //   97	103	232	finally
    //   110	126	232	finally
    //   128	142	232	finally
    //   151	157	232	finally
    //   170	174	232	finally
    //   180	184	232	finally
    //   13	17	236	java/io/IOException
    //   42	51	236	java/io/IOException
    //   75	95	236	java/io/IOException
  }
  
  public static void e(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {}
    String[] arrayOfString;
    do
    {
      return;
      arrayOfString = j(paramContext);
    } while ((arrayOfString == null) || (arrayOfString.length == 0));
    int j = arrayOfString.length;
    int i = 0;
    Object localObject = "";
    if (i < j)
    {
      String str = arrayOfString[i];
      if (paramString.equals(str)) {}
      for (;;)
      {
        i += 1;
        break;
        if ("".equals(localObject)) {
          localObject = str;
        } else {
          localObject = (String)localObject + ";" + str;
        }
      }
    }
    c(paramContext, (String)localObject);
  }
  
  public static List<e> f(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1;
    label25:
    try
    {
      localObject1 = new ArrayList();
    }
    finally {}
    try
    {
      localObject4 = J(paramContext);
      localObject1 = localObject4;
    }
    catch (RuntimeException localRuntimeException)
    {
      break label25;
    }
    Object localObject4 = paramContext.getPackageManager();
    if ((localObject1 != null) && (!((List)localObject1).isEmpty())) {
      localObject1 = ((List)localObject1).iterator();
    }
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject5 = (ApplicationInfo)((Iterator)localObject1).next();
      e localE = new e();
      localE.a(((ApplicationInfo)localObject5).loadLabel((PackageManager)localObject4).toString());
      localE.b(((ApplicationInfo)localObject5).packageName);
      if ((((ApplicationInfo)localObject5).flags & 0x1) == 0)
      {
        localArrayList.add(localE);
        continue;
        Object localObject3 = e(paramContext);
        if (localObject3 == null) {
          return localArrayList;
        }
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject5 = (PackageInfo)((Iterator)localObject3).next();
          localE = new e();
          localE.b(((PackageInfo)localObject5).packageName);
          ApplicationInfo localApplicationInfo = ((PackageInfo)localObject5).applicationInfo;
          if (localApplicationInfo != null)
          {
            localE.a(localApplicationInfo.loadLabel((PackageManager)localObject4).toString());
            if ((localApplicationInfo.flags & 0x1) == 0) {
              localArrayList.add(localE);
            }
          }
          else
          {
            localE.a(((PackageInfo)localObject5).packageName);
            localArrayList.add(localE);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static void f(Context paramContext, String paramString)
  {
    int i = 0;
    if ((paramString == null) || ("".equals(paramString.trim()))) {
      return;
    }
    String str = paramContext.getSharedPreferences("sp_w_l", 0).getString("sp_f_pk", "");
    Object localObject = str.split(";");
    int j = localObject.length;
    for (;;)
    {
      if (i >= j) {
        break label74;
      }
      if (localObject[i].equals(paramString)) {
        break;
      }
      i += 1;
    }
    label74:
    localObject = paramString;
    if (!"".equals(str)) {
      localObject = str + ";" + paramString;
    }
    b(paramContext, (String)localObject);
  }
  
  public static List<String> g(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1;
    Object localObject4;
    label25:
    try
    {
      localObject1 = new ArrayList();
    }
    finally {}
    try
    {
      localObject4 = J(paramContext);
      localObject1 = localObject4;
    }
    catch (RuntimeException localRuntimeException)
    {
      break label25;
    }
    if ((localObject1 != null) && (!((List)localObject1).isEmpty())) {
      localObject1 = ((List)localObject1).iterator();
    }
    while (((Iterator)localObject1).hasNext())
    {
      localObject4 = (ApplicationInfo)((Iterator)localObject1).next();
      if ((((ApplicationInfo)localObject4).flags & 0x1) == 0)
      {
        localArrayList.add(((ApplicationInfo)localObject4).packageName);
        continue;
        Object localObject3 = e(paramContext);
        if (localObject3 == null) {
          return localArrayList;
        }
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (PackageInfo)((Iterator)localObject3).next();
          ApplicationInfo localApplicationInfo = ((PackageInfo)localObject4).applicationInfo;
          if (localApplicationInfo != null)
          {
            if ((localApplicationInfo.flags & 0x1) == 0) {
              localArrayList.add(((PackageInfo)localObject4).packageName);
            }
          }
          else {
            localArrayList.add(((PackageInfo)localObject4).packageName);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static void g(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim()))) {
      return;
    }
    String[] arrayOfString = i(paramContext);
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (arrayOfString != null)
    {
      int j = arrayOfString.length;
      int i = 0;
      for (;;)
      {
        localObject1 = localObject2;
        if (i >= j) {
          break;
        }
        localObject1 = localObject2;
        if (!paramString.equals(arrayOfString[i])) {
          localObject1 = (String)localObject2 + ";" + arrayOfString[i];
        }
        i += 1;
        localObject2 = localObject1;
      }
    }
    paramString = (String)localObject1;
    if (((String)localObject1).startsWith(";")) {
      paramString = ((String)localObject1).substring(1);
    }
    b(paramContext, paramString);
  }
  
  public static List<wonder.city.baseutility.utility.g.a> h(Context paramContext)
  {
    List localList = d(paramContext);
    paramContext = i(paramContext);
    if (paramContext == null) {
      return localList;
    }
    ArrayList localArrayList = new ArrayList();
    int k = localList.size();
    int i = 0;
    if (i < paramContext.length)
    {
      Object localObject = paramContext[i];
      int j = 0;
      for (;;)
      {
        if (j < k)
        {
          if ((localList.get(j) != null) && (((wonder.city.baseutility.utility.g.a)localList.get(j)).b() != null) && (((wonder.city.baseutility.utility.g.a)localList.get(j)).b().equals(localObject))) {
            localArrayList.add(localList.get(j));
          }
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
    localList.removeAll(localArrayList);
    return localList;
  }
  
  public static void h(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("SystemMem", 0).edit();
    paramContext.putString("SystemMem", paramString);
    paramContext.apply();
  }
  
  public static boolean i(Context paramContext, String paramString)
  {
    new Intent();
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null)
    {
      paramContext.startActivity(paramString);
      return true;
    }
    return false;
  }
  
  public static String[] i(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("sp_w_l", 0).getString("sp_f_pk", "");
    if (("".equals(paramContext)) || ("".equals(paramContext.trim()))) {
      return null;
    }
    return paramContext.split(";");
  }
  
  public static void j(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    int i;
    do
    {
      return;
      paramContext = paramContext.getSharedPreferences("sp_w_l", 0);
      String str = paramContext.getString("canlaunchapps", "");
      i = str.length();
      paramString = str.replaceAll(paramString + ":.+?;", "");
    } while (i == paramString.length());
    paramContext = paramContext.edit();
    paramContext.putString("canlaunchapps", paramString);
    paramContext.apply();
  }
  
  public static String[] j(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("sp_w_l", 0).getString("sp_f_gpk", "");
    if (("".equals(paramContext)) || ("".equals(paramContext.trim()))) {
      return null;
    }
    return paramContext.split(";");
  }
  
  public static List<e> k(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = j(paramContext);
    if (arrayOfString == null) {
      return localArrayList;
    }
    paramContext = f(paramContext);
    int i = 0;
    if (i < arrayOfString.length)
    {
      int j = 0;
      for (;;)
      {
        if (j < paramContext.size())
        {
          e localE = (e)paramContext.get(j);
          if (arrayOfString[i].equals(localE.b())) {
            localArrayList.add(localE);
          }
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
    return localArrayList;
  }
  
  public static boolean k(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static List<e> l(Context paramContext)
  {
    Object localObject1 = new ArrayList();
    String[] arrayOfString = j(paramContext);
    Object localObject2 = f(paramContext);
    if (localObject2 == null) {
      return localObject1;
    }
    paramContext = paramContext.getPackageName();
    int j;
    int i;
    if (arrayOfString == null)
    {
      if (paramContext == null) {
        return localObject2;
      }
      j = ((List)localObject2).size();
      i = 0;
      if (i >= j) {
        break label224;
      }
      localObject1 = (e)((List)localObject2).get(i);
      if ((localObject1 == null) || (!paramContext.equals(((e)localObject1).b()))) {}
    }
    for (;;)
    {
      if (i != -1) {
        ((List)localObject2).remove(i);
      }
      return localObject2;
      i += 1;
      break;
      localObject2 = ((List)localObject2).iterator();
      label163:
      label219:
      label222:
      for (;;)
      {
        e localE;
        if (((Iterator)localObject2).hasNext())
        {
          localE = (e)((Iterator)localObject2).next();
          if ((paramContext != null) && (paramContext.equals(localE.b()))) {
            continue;
          }
          j = arrayOfString.length;
          i = 0;
          if (i >= j) {
            break label219;
          }
          String str = arrayOfString[i];
          if ((str == null) || (!str.equals(localE.b()))) {}
        }
        for (i = 1;; i = 0)
        {
          if (i != 0) {
            break label222;
          }
          ((List)localObject1).add(localE);
          break;
          i += 1;
          break label163;
          return localObject1;
        }
      }
      label224:
      i = -1;
    }
  }
  
  public static boolean l(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramString = new Intent(paramString);
    if (paramContext.getPackageManager().queryIntentActivities(paramString, 0).size() != 0) {
      bool = true;
    }
    return bool;
  }
  
  @TargetApi(9)
  public static String m(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramContext = paramContext.getApplicationLabel(paramContext.getPackageInfo(paramString, 0).applicationInfo).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static List<wonder.city.baseutility.utility.g.a> m(Context paramContext)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = i(paramContext);
    if (arrayOfString == null) {
      return localArrayList;
    }
    paramContext = paramContext.getPackageManager();
    int j = arrayOfString.length;
    while (i < j)
    {
      String str = arrayOfString[i];
      wonder.city.baseutility.utility.g.a localA = new wonder.city.baseutility.utility.g.a();
      try
      {
        ApplicationInfo localApplicationInfo = paramContext.getPackageInfo(str, 0).applicationInfo;
        localA.b(str);
        localA.a(localApplicationInfo.loadLabel(paramContext).toString());
        localArrayList.add(localA);
        i += 1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
    }
  }
  
  public static String n(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {}
    while (paramContext.activityInfo.packageName.equals("android")) {
      return null;
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static boolean n(Context paramContext, String paramString)
  {
    Object localObject2 = (AccessibilityManager)paramContext.getSystemService("accessibility");
    Object localObject1 = new ArrayList();
    try
    {
      localObject2 = ((AccessibilityManager)localObject2).getEnabledAccessibilityServiceList(16);
      localObject1 = localObject2;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;) {}
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_accessibility_services");
      if (paramContext == null) {
        break label177;
      }
      localObject1 = new TextUtils.SimpleStringSplitter(':');
      ((TextUtils.SimpleStringSplitter)localObject1).setString(paramContext);
      do
      {
        if (!((TextUtils.SimpleStringSplitter)localObject1).hasNext()) {
          break;
        }
      } while (!((TextUtils.SimpleStringSplitter)localObject1).next().equalsIgnoreCase(localNullPointerException + "/" + paramString));
      return true;
    }
    localObject2 = paramContext.getPackageName();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ResolveInfo localResolveInfo = ((AccessibilityServiceInfo)((Iterator)localObject1).next()).getResolveInfo();
      if ((localResolveInfo != null) && (((String)localObject2).equals(localResolveInfo.serviceInfo.packageName)) && (paramString.equals(localResolveInfo.serviceInfo.name))) {
        return true;
      }
    }
    label177:
    return false;
  }
  
  public static List<String> o(Context paramContext)
  {
    paramContext = p(paramContext);
    paramContext.add("android");
    paramContext.add("com.android.systemui");
    paramContext.add("com.android.settings");
    paramContext.add("com.android.mms");
    paramContext.add("com.android.phone");
    paramContext.add("com.android.providers.telephony");
    paramContext.add("com.android.server.telecom");
    paramContext.add("com.android.dialer");
    paramContext.add("com.android.stk");
    paramContext.add("com.google.android.gms");
    paramContext.add("com.android.vending");
    paramContext.add("com.android.defcontainer");
    paramContext.add("com.android.providers.settings");
    paramContext.add("com.android.keyguard");
    return paramContext;
  }
  
  public static List<String> p(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(n(paramContext));
    localArrayList.add(paramContext.getPackageName());
    return localArrayList;
  }
  
  public static List<String> q(Context paramContext)
  {
    List localList = o(paramContext);
    paramContext = i(paramContext);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.length)
      {
        localList.add(paramContext[i]);
        i += 1;
      }
    }
    return localList;
  }
  
  public static List<String> r(Context paramContext)
  {
    List localList = p(paramContext);
    paramContext = i(paramContext);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.length)
      {
        localList.add(paramContext[i]);
        i += 1;
      }
    }
    return localList;
  }
  
  public static long s(Context paramContext)
  {
    return paramContext.getSharedPreferences("sp_et_te", 0).getLong("sp_f_c_t", 0L);
  }
  
  public static void t(Context paramContext)
  {
    long l = System.currentTimeMillis();
    paramContext = paramContext.getSharedPreferences("sp_et_te", 0).edit();
    paramContext.putLong("sp_f_c_t", l);
    paramContext.apply();
  }
  
  public static long u(Context paramContext)
  {
    return paramContext.getSharedPreferences("sp_et_te", 0).getLong("sp_f_l_t", 0L);
  }
  
  public static long v(Context paramContext)
  {
    return paramContext.getSharedPreferences("sp_et_te", 0).getLong("sp_f_cc_t", 0L);
  }
  
  public static boolean w(Context paramContext)
  {
    long l = v(paramContext);
    return System.currentTimeMillis() - l > 180000L;
  }
  
  public static void x(Context paramContext)
  {
    long l = System.currentTimeMillis();
    paramContext = paramContext.getSharedPreferences("sp_et_te", 0).edit();
    paramContext.putLong("sp_f_cc_t", l);
    paramContext.apply();
  }
  
  public static void y(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("ShortCut", 0).edit();
    paramContext.putBoolean("EntryShortCut", true);
    paramContext.apply();
  }
  
  public static boolean z(Context paramContext)
  {
    return paramContext.getSharedPreferences("ShortCut", 0).getBoolean("EntryShortCut", false);
  }
}
