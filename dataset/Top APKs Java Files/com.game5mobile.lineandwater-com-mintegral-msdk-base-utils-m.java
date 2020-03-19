package com.mintegral.msdk.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.mintegral.msdk.MIntegralConstans;
import com.mintegral.msdk.b.b;
import com.mintegral.msdk.base.b.s;
import com.mintegral.msdk.base.entity.CampaignEx;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

public final class m
{
  static List<String> a;
  private static String b = "[一-龥]";
  private static Pattern c = Pattern.compile("[一-龥]");
  private static int d = 1;
  private static boolean e = true;
  
  public static double a(Double paramDouble)
  {
    try
    {
      i.b("CommonUtils", "format before num:" + paramDouble);
      paramDouble = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US)).format(paramDouble);
      i.b("CommonUtils", "format after format:" + paramDouble);
      if (v.b(paramDouble))
      {
        double d1 = Double.parseDouble(paramDouble);
        return d1;
      }
    }
    catch (Exception paramDouble)
    {
      paramDouble.printStackTrace();
    }
    return 0.0D;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    f1 = 2.5F;
    try
    {
      f2 = paramContext.getResources().getDisplayMetrics().density;
      if (f2 != 0.0F) {
        break label36;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        float f2;
        paramContext.printStackTrace();
        continue;
        f1 = f2;
      }
    }
    return (int)(paramFloat / f1 + 0.5F);
  }
  
  public static int a(Object paramObject)
  {
    int j = 0;
    int i = j;
    if (paramObject != null) {
      i = j;
    }
    try
    {
      if ((paramObject instanceof String)) {
        i = Integer.parseInt((String)paramObject);
      }
      return i;
    }
    catch (Throwable paramObject)
    {
      i.c("CommonUtils", paramObject.getMessage(), paramObject);
    }
    return 0;
  }
  
  /* Error */
  public static long a(java.io.File paramFile)
    throws Exception
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_2
    //   2: aconst_null
    //   3: astore 6
    //   5: aload_0
    //   6: invokevirtual 147	java/io/File:exists	()Z
    //   9: ifeq +37 -> 46
    //   12: new 149	java/io/FileInputStream
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 152	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   20: astore_0
    //   21: aload_0
    //   22: invokevirtual 156	java/io/FileInputStream:available	()I
    //   25: istore_1
    //   26: iload_1
    //   27: i2l
    //   28: lstore_2
    //   29: lload_2
    //   30: lstore 4
    //   32: aload_0
    //   33: ifnull +10 -> 43
    //   36: aload_0
    //   37: invokevirtual 159	java/io/FileInputStream:close	()V
    //   40: lload_2
    //   41: lstore 4
    //   43: lload 4
    //   45: lreturn
    //   46: aload_0
    //   47: invokevirtual 162	java/io/File:createNewFile	()Z
    //   50: pop
    //   51: ldc -92
    //   53: ldc -90
    //   55: invokestatic 168	com/mintegral/msdk/base/utils/i:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: aconst_null
    //   59: astore_0
    //   60: goto -31 -> 29
    //   63: astore_0
    //   64: aconst_null
    //   65: astore_0
    //   66: lload_2
    //   67: lstore 4
    //   69: aload_0
    //   70: ifnull -27 -> 43
    //   73: aload_0
    //   74: invokevirtual 159	java/io/FileInputStream:close	()V
    //   77: lconst_0
    //   78: lreturn
    //   79: astore_0
    //   80: lconst_0
    //   81: lreturn
    //   82: astore_0
    //   83: aload 6
    //   85: ifnull +8 -> 93
    //   88: aload 6
    //   90: invokevirtual 159	java/io/FileInputStream:close	()V
    //   93: aload_0
    //   94: athrow
    //   95: astore_0
    //   96: lload_2
    //   97: lreturn
    //   98: astore 6
    //   100: goto -7 -> 93
    //   103: astore 7
    //   105: aload_0
    //   106: astore 6
    //   108: aload 7
    //   110: astore_0
    //   111: goto -28 -> 83
    //   114: astore 6
    //   116: goto -50 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	paramFile	java.io.File
    //   25	2	1	i	int
    //   1	96	2	l1	long
    //   30	38	4	l2	long
    //   3	86	6	localObject1	Object
    //   98	1	6	localException1	Exception
    //   106	1	6	localFile	java.io.File
    //   114	1	6	localException2	Exception
    //   103	6	7	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	21	63	java/lang/Exception
    //   46	58	63	java/lang/Exception
    //   73	77	79	java/lang/Exception
    //   5	21	82	finally
    //   46	58	82	finally
    //   36	40	95	java/lang/Exception
    //   88	93	98	java/lang/Exception
    //   21	26	103	finally
    //   21	26	114	java/lang/Exception
  }
  
  public static String a(JSONArray paramJSONArray)
  {
    Object localObject = com.mintegral.msdk.base.controller.a.d().k();
    b.a();
    com.mintegral.msdk.b.a localA = b.b((String)localObject);
    localObject = localA;
    if (localA == null)
    {
      b.a();
      localObject = b.b();
    }
    int j = ((com.mintegral.msdk.b.a)localObject).ab();
    if (paramJSONArray.length() > j)
    {
      localObject = new JSONArray();
      int i = 0;
      for (;;)
      {
        if (i < j) {
          try
          {
            ((JSONArray)localObject).put(paramJSONArray.get(i));
            i += 1;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }
      return ((JSONArray)localObject).toString();
    }
    return paramJSONArray.toString();
  }
  
  public static void a(View paramView)
  {
    if (paramView == null) {}
    for (;;)
    {
      return;
      try
      {
        if (Build.VERSION.SDK_INT >= 11)
        {
          paramView.setSystemUiVisibility(4102);
          return;
        }
      }
      catch (Throwable paramView)
      {
        paramView.printStackTrace();
      }
    }
  }
  
  public static void a(ImageView paramImageView)
  {
    if (paramImageView == null) {}
    do
    {
      return;
      try
      {
        paramImageView.setImageResource(0);
        paramImageView.setImageDrawable(null);
        paramImageView.setImageURI(null);
        paramImageView.setImageBitmap(null);
        return;
      }
      catch (Throwable paramImageView) {}
    } while (!MIntegralConstans.DEBUG);
    paramImageView.printStackTrace();
  }
  
  public static void a(List<CampaignEx> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      CampaignEx localCampaignEx = (CampaignEx)paramList.get(i);
      if (((!a(com.mintegral.msdk.base.controller.a.d().i(), localCampaignEx.getPackageName())) && (localCampaignEx.getOfferType() == 99)) || (a(localCampaignEx))) {
        localArrayList.add(localCampaignEx);
      }
      i += 1;
    }
    s.a(com.mintegral.msdk.base.b.i.a(com.mintegral.msdk.base.controller.a.d().i())).a(localArrayList);
  }
  
  public static boolean a()
  {
    return e;
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = "wifi".equals(paramContext.getTypeName().toLowerCase(Locale.US));
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (a == null) {
      a = new ArrayList();
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager();
        if ((com.mintegral.msdk.base.controller.a.b == null) || (com.mintegral.msdk.base.controller.a.b.size() == 0))
        {
          com.mintegral.msdk.base.controller.a.b = new ArrayList();
          paramContext = paramContext.getInstalledPackages(0);
          List localList = com.mintegral.msdk.base.controller.a.d().b();
          i = 0;
          if (i < paramContext.size())
          {
            PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
            if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
              com.mintegral.msdk.base.controller.a.b.add(localPackageInfo.packageName);
            } else if ((localList != null) && (localList.size() > 0) && (localList.contains(localPackageInfo.packageName))) {
              com.mintegral.msdk.base.controller.a.b.add(localPackageInfo.packageName);
            }
          }
        }
      }
      catch (Throwable paramContext)
      {
        if (MIntegralConstans.DEBUG) {
          paramContext.printStackTrace();
        }
        return false;
      }
      int i = 0;
      while (i < com.mintegral.msdk.base.controller.a.b.size())
      {
        paramContext = (String)com.mintegral.msdk.base.controller.a.b.get(i);
        a.add(paramContext);
        i += 1;
      }
      boolean bool = a.contains(paramString);
      return bool;
      return a.contains(paramString);
      i += 1;
    }
  }
  
  public static boolean a(CampaignEx paramCampaignEx)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramCampaignEx != null)
    {
      bool1 = bool2;
      if (!TextUtils.isEmpty(paramCampaignEx.getDeepLinkURL())) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static <T extends String> boolean a(T paramT)
  {
    return (paramT == null) || (paramT.length() == 0);
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    try
    {
      if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0)
      {
        i.a("CommonUtils", "Permission " + paramString + " is granted");
        return true;
      }
      i.a("CommonUtils", "Permission " + paramString + " is NOT granted");
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int b()
  {
    int i = d;
    d = i + 1;
    return i;
  }
  
  public static int b(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static String b(List<String> paramList)
  {
    if (paramList != null)
    {
      try
      {
        if (paramList.size() == 0) {
          break label88;
        }
        JSONArray localJSONArray = new JSONArray();
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          String str = (String)paramList.next();
          if (v.b(str)) {
            localJSONArray.put(str);
          }
        }
        paramList = localJSONArray.toString();
      }
      catch (Throwable paramList)
      {
        i.c("CommonUtils", paramList.getMessage(), paramList);
        return "[]";
      }
      return paramList;
    }
    label88:
    return "[]";
  }
  
  public static List<String> b(JSONArray paramJSONArray)
  {
    if (paramJSONArray != null) {}
    try
    {
      if (paramJSONArray.length() > 0)
      {
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        for (;;)
        {
          localObject = localArrayList;
          if (i >= paramJSONArray.length()) {
            break;
          }
          localObject = paramJSONArray.optString(i);
          if (v.b((String)localObject)) {
            localArrayList.add(localObject);
          }
          i += 1;
        }
      }
      Object localObject = null;
      return localObject;
    }
    catch (Throwable paramJSONArray)
    {
      i.c("CommonUtils", paramJSONArray.getMessage(), paramJSONArray);
    }
    return null;
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean b(CampaignEx paramCampaignEx)
  {
    if (paramCampaignEx != null) {
      try
      {
        int i = paramCampaignEx.getRetarget_offer();
        return i == 1;
      }
      catch (Exception paramCampaignEx)
      {
        paramCampaignEx.printStackTrace();
      }
    }
    return false;
  }
  
  public static <T extends String> boolean b(T paramT)
  {
    return (paramT != null) && (paramT.length() > 0);
  }
  
  public static double c(String paramString)
  {
    double d1 = 0.0D;
    try
    {
      if (!TextUtils.isEmpty(paramString)) {
        d1 = Double.parseDouble(paramString);
      }
      return d1;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0.0D;
  }
  
  public static float c(Context paramContext)
  {
    float f;
    try
    {
      f = paramContext.getResources().getDisplayMetrics().density;
      if (f == 0.0F) {
        return 2.5F;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return 2.5F;
    }
    return f;
  }
  
  public static boolean c()
  {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }
  
  public static int d(Context paramContext)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().scaledDensity * 30.0F + 0.5F);
  }
  
  public static String d(String paramString)
  {
    try
    {
      if (v.b(paramString))
      {
        paramString = URLEncoder.encode(paramString, "utf-8");
        return paramString;
      }
    }
    catch (Throwable paramString)
    {
      i.c("CommonUtils", paramString.getMessage(), paramString);
    }
    return "";
  }
  
  /* Error */
  public static List<com.mintegral.msdk.base.entity.l> d()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: ldc 39
    //   4: ldc_w 442
    //   7: invokestatic 60	com/mintegral/msdk/base/utils/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   10: new 257	java/util/ArrayList
    //   13: dup
    //   14: invokespecial 258	java/util/ArrayList:<init>	()V
    //   17: astore 5
    //   19: new 444	java/io/BufferedReader
    //   22: dup
    //   23: new 446	java/io/InputStreamReader
    //   26: dup
    //   27: invokestatic 452	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   30: ldc_w 454
    //   33: invokevirtual 458	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   36: invokevirtual 464	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   39: invokespecial 467	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: sipush 1024
    //   45: invokespecial 470	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   48: astore_3
    //   49: aload_3
    //   50: astore_2
    //   51: aload_3
    //   52: invokevirtual 473	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   55: astore 4
    //   57: aload 4
    //   59: ifnull +207 -> 266
    //   62: aload_3
    //   63: astore_2
    //   64: aload 4
    //   66: ldc_w 475
    //   69: invokevirtual 478	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   72: ifeq -23 -> 49
    //   75: aload_3
    //   76: astore_2
    //   77: aload 4
    //   79: ldc_w 480
    //   82: invokevirtual 484	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   85: astore 6
    //   87: aload 6
    //   89: ifnull -40 -> 49
    //   92: aload_3
    //   93: astore_2
    //   94: aload 6
    //   96: arraylength
    //   97: ifle -48 -> 49
    //   100: aload_3
    //   101: astore_2
    //   102: aload 6
    //   104: aload 6
    //   106: arraylength
    //   107: iconst_1
    //   108: isub
    //   109: aaload
    //   110: astore 6
    //   112: aload_3
    //   113: astore_2
    //   114: aload 5
    //   116: aload 6
    //   118: invokeinterface 281 2 0
    //   123: pop
    //   124: aload_3
    //   125: astore_2
    //   126: ldc 39
    //   128: new 41	java/lang/StringBuilder
    //   131: dup
    //   132: ldc_w 486
    //   135: invokespecial 47	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   138: aload 4
    //   140: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc_w 488
    //   146: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 6
    //   151: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: invokestatic 168	com/mintegral/msdk/base/utils/i:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   160: goto -111 -> 49
    //   163: astore 4
    //   165: aload_3
    //   166: astore_2
    //   167: aload 4
    //   169: invokevirtual 102	java/lang/Exception:printStackTrace	()V
    //   172: aload_3
    //   173: ifnull +7 -> 180
    //   176: aload_3
    //   177: invokevirtual 489	java/io/BufferedReader:close	()V
    //   180: new 257	java/util/ArrayList
    //   183: dup
    //   184: invokespecial 258	java/util/ArrayList:<init>	()V
    //   187: astore_2
    //   188: invokestatic 177	com/mintegral/msdk/base/controller/a:d	()Lcom/mintegral/msdk/base/controller/a;
    //   191: invokevirtual 265	com/mintegral/msdk/base/controller/a:i	()Landroid/content/Context;
    //   194: invokevirtual 328	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   197: iconst_0
    //   198: invokevirtual 336	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   201: astore_3
    //   202: invokestatic 177	com/mintegral/msdk/base/controller/a:d	()Lcom/mintegral/msdk/base/controller/a;
    //   205: invokevirtual 339	com/mintegral/msdk/base/controller/a:b	()Ljava/util/List;
    //   208: astore 4
    //   210: iconst_0
    //   211: istore_0
    //   212: iload_0
    //   213: aload_3
    //   214: invokeinterface 255 1 0
    //   219: if_icmpge +149 -> 368
    //   222: aload_3
    //   223: iload_0
    //   224: invokeinterface 259 2 0
    //   229: checkcast 341	android/content/pm/PackageInfo
    //   232: astore 6
    //   234: aload 6
    //   236: getfield 345	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   239: getfield 350	android/content/pm/ApplicationInfo:flags	I
    //   242: iconst_1
    //   243: iand
    //   244: ifgt +79 -> 323
    //   247: aload_2
    //   248: aload 6
    //   250: getfield 353	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   253: invokeinterface 281 2 0
    //   258: pop
    //   259: iload_0
    //   260: iconst_1
    //   261: iadd
    //   262: istore_0
    //   263: goto -51 -> 212
    //   266: aload_3
    //   267: invokevirtual 489	java/io/BufferedReader:close	()V
    //   270: goto -90 -> 180
    //   273: astore_2
    //   274: aload_2
    //   275: invokevirtual 102	java/lang/Exception:printStackTrace	()V
    //   278: goto -98 -> 180
    //   281: astore_2
    //   282: ldc 39
    //   284: aload_2
    //   285: invokevirtual 137	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   288: aload_2
    //   289: invokestatic 140	com/mintegral/msdk/base/utils/i:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   292: aconst_null
    //   293: areturn
    //   294: astore_2
    //   295: aload_2
    //   296: invokevirtual 102	java/lang/Exception:printStackTrace	()V
    //   299: goto -119 -> 180
    //   302: astore_3
    //   303: aconst_null
    //   304: astore_2
    //   305: aload_2
    //   306: ifnull +7 -> 313
    //   309: aload_2
    //   310: invokevirtual 489	java/io/BufferedReader:close	()V
    //   313: aload_3
    //   314: athrow
    //   315: astore_2
    //   316: aload_2
    //   317: invokevirtual 102	java/lang/Exception:printStackTrace	()V
    //   320: goto -7 -> 313
    //   323: aload 4
    //   325: ifnull -66 -> 259
    //   328: aload 4
    //   330: invokeinterface 255 1 0
    //   335: ifle -76 -> 259
    //   338: aload 4
    //   340: aload 6
    //   342: getfield 353	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   345: invokeinterface 356 2 0
    //   350: ifeq -91 -> 259
    //   353: aload_2
    //   354: aload 6
    //   356: getfield 353	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   359: invokeinterface 281 2 0
    //   364: pop
    //   365: goto -106 -> 259
    //   368: new 257	java/util/ArrayList
    //   371: dup
    //   372: invokespecial 258	java/util/ArrayList:<init>	()V
    //   375: astore_3
    //   376: iload_1
    //   377: istore_0
    //   378: iload_0
    //   379: aload 5
    //   381: invokeinterface 255 1 0
    //   386: if_icmpge +78 -> 464
    //   389: aload 5
    //   391: iload_0
    //   392: invokeinterface 259 2 0
    //   397: checkcast 128	java/lang/String
    //   400: astore 4
    //   402: aload_2
    //   403: aload 4
    //   405: invokeinterface 356 2 0
    //   410: ifeq +75 -> 485
    //   413: ldc 39
    //   415: new 41	java/lang/StringBuilder
    //   418: dup
    //   419: ldc_w 491
    //   422: invokespecial 47	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   425: aload 4
    //   427: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   433: invokestatic 60	com/mintegral/msdk/base/utils/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   436: new 493	com/mintegral/msdk/base/entity/l
    //   439: dup
    //   440: invokespecial 494	com/mintegral/msdk/base/entity/l:<init>	()V
    //   443: astore 6
    //   445: aload 6
    //   447: aload 4
    //   449: putfield 496	com/mintegral/msdk/base/entity/l:a	Ljava/lang/String;
    //   452: aload_3
    //   453: aload 6
    //   455: invokeinterface 281 2 0
    //   460: pop
    //   461: goto +24 -> 485
    //   464: ldc 39
    //   466: ldc_w 498
    //   469: invokestatic 60	com/mintegral/msdk/base/utils/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   472: aload_3
    //   473: areturn
    //   474: astore_3
    //   475: goto -170 -> 305
    //   478: astore 4
    //   480: aconst_null
    //   481: astore_3
    //   482: goto -317 -> 165
    //   485: iload_0
    //   486: iconst_1
    //   487: iadd
    //   488: istore_0
    //   489: goto -111 -> 378
    // Local variable table:
    //   start	length	slot	name	signature
    //   211	278	0	i	int
    //   1	376	1	j	int
    //   50	198	2	localObject1	Object
    //   273	2	2	localException1	Exception
    //   281	8	2	localThrowable	Throwable
    //   294	2	2	localException2	Exception
    //   304	6	2	localObject2	Object
    //   315	88	2	localException3	Exception
    //   48	219	3	localObject3	Object
    //   302	12	3	localObject4	Object
    //   375	98	3	localArrayList1	ArrayList
    //   474	1	3	localObject5	Object
    //   481	1	3	localObject6	Object
    //   55	84	4	str	String
    //   163	5	4	localException4	Exception
    //   208	240	4	localObject7	Object
    //   478	1	4	localException5	Exception
    //   17	373	5	localArrayList2	ArrayList
    //   85	369	6	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   51	57	163	java/lang/Exception
    //   64	75	163	java/lang/Exception
    //   77	87	163	java/lang/Exception
    //   94	100	163	java/lang/Exception
    //   102	112	163	java/lang/Exception
    //   114	124	163	java/lang/Exception
    //   126	160	163	java/lang/Exception
    //   266	270	273	java/lang/Exception
    //   2	19	281	java/lang/Throwable
    //   176	180	281	java/lang/Throwable
    //   180	210	281	java/lang/Throwable
    //   212	259	281	java/lang/Throwable
    //   266	270	281	java/lang/Throwable
    //   274	278	281	java/lang/Throwable
    //   295	299	281	java/lang/Throwable
    //   309	313	281	java/lang/Throwable
    //   313	315	281	java/lang/Throwable
    //   316	320	281	java/lang/Throwable
    //   328	365	281	java/lang/Throwable
    //   368	376	281	java/lang/Throwable
    //   378	461	281	java/lang/Throwable
    //   464	472	281	java/lang/Throwable
    //   176	180	294	java/lang/Exception
    //   19	49	302	finally
    //   309	313	315	java/lang/Exception
    //   51	57	474	finally
    //   64	75	474	finally
    //   77	87	474	finally
    //   94	100	474	finally
    //   102	112	474	finally
    //   114	124	474	finally
    //   126	160	474	finally
    //   167	172	474	finally
    //   19	49	478	java/lang/Exception
  }
  
  public static int e(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
      i = paramContext.getResources().getDimensionPixelSize(i);
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String e()
  {
    Object localObject1 = "";
    try
    {
      localObject2 = UUID.randomUUID().toString() + System.currentTimeMillis();
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Object localObject2;
        localThrowable.printStackTrace();
      }
    }
    localObject2 = localObject1;
    if (v.a((String)localObject1)) {
      localObject2 = System.currentTimeMillis();
    }
    return localObject2;
  }
  
  public static float f(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int g(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return -1;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        if ((paramContext != null) && (paramContext.size() > 0))
        {
          int i = paramContext.size();
          return i;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return -1;
  }
  
  public static int h(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = o(paramContext).heightPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int i(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = o(paramContext).widthPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int j(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().widthPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int k(Context paramContext)
  {
    if (paramContext == null) {
      return 0;
    }
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().heightPixels;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int l(Context paramContext)
  {
    try
    {
      if (paramContext.getResources().getIdentifier("config_showNavigationBar", "bool", "android") != 0)
      {
        int i = paramContext.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        i = paramContext.getResources().getDimensionPixelSize(i);
        return i;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static boolean m(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.getResources();
        int i = paramContext.getIdentifier("config_showNavigationBar", "bool", "android");
        if (i > 0)
        {
          bool1 = paramContext.getBoolean(i);
          boolean bool2;
          return bool1;
        }
      }
      catch (Throwable paramContext)
      {
        try
        {
          paramContext = Class.forName("android.os.SystemProperties");
          paramContext = (String)paramContext.getMethod("get", new Class[] { String.class }).invoke(paramContext, new Object[] { "qemu.hw.mainkeys" });
          if ("1".equals(paramContext)) {
            return false;
          }
          bool2 = "0".equals(paramContext);
          if (!bool2) {
            break label115;
          }
          return true;
        }
        catch (Throwable paramContext)
        {
          continue;
        }
        paramContext = paramContext;
        bool1 = false;
        i.c("CommonUtils", paramContext.getMessage(), paramContext);
        return bool1;
      }
      label115:
      boolean bool1 = false;
    }
  }
  
  public static boolean n(Context paramContext)
  {
    try
    {
      boolean bool = ((PowerManager)paramContext.getSystemService("power")).isScreenOn();
      return bool;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private static DisplayMetrics o(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(localDisplay, new Object[] { localDisplayMetrics });
      return localDisplayMetrics;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return paramContext.getResources().getDisplayMetrics();
  }
}
