package com.mobvista.msdk.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.mobvista.msdk.MobVistaConstans;
import com.mobvista.msdk.b.b;
import com.mobvista.msdk.base.b.i;
import com.mobvista.msdk.base.entity.CampaignEx;
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

public final class l
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
      StringBuilder localStringBuilder = new StringBuilder("format before num:");
      localStringBuilder.append(paramDouble);
      h.b("CommonUtils", localStringBuilder.toString());
      paramDouble = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US)).format(paramDouble);
      localStringBuilder = new StringBuilder("format after format:");
      localStringBuilder.append(paramDouble);
      h.b("CommonUtils", localStringBuilder.toString());
      if (r.b(paramDouble))
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
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int a(Object paramObject)
  {
    if (paramObject != null) {
      try
      {
        if ((paramObject instanceof String))
        {
          int i = Integer.parseInt((String)paramObject);
          return i;
        }
      }
      catch (Throwable paramObject)
      {
        h.c("CommonUtils", paramObject.getMessage(), paramObject);
      }
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
    //   5: aconst_null
    //   6: astore 7
    //   8: aconst_null
    //   9: astore 8
    //   11: aload_0
    //   12: invokevirtual 144	java/io/File:exists	()Z
    //   15: ifeq +37 -> 52
    //   18: new 146	java/io/FileInputStream
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 149	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   26: astore_0
    //   27: aload_0
    //   28: invokevirtual 153	java/io/FileInputStream:available	()I
    //   31: istore_1
    //   32: iload_1
    //   33: i2l
    //   34: lstore_2
    //   35: goto +32 -> 67
    //   38: astore 7
    //   40: aload_0
    //   41: astore 6
    //   43: aload 7
    //   45: astore_0
    //   46: goto +35 -> 81
    //   49: goto +44 -> 93
    //   52: aload_0
    //   53: invokevirtual 156	java/io/File:createNewFile	()Z
    //   56: pop
    //   57: ldc -98
    //   59: ldc -96
    //   61: invokestatic 162	com/mobvista/msdk/base/utils/h:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   64: aload 8
    //   66: astore_0
    //   67: lload_2
    //   68: lstore 4
    //   70: aload_0
    //   71: ifnull +32 -> 103
    //   74: aload_0
    //   75: invokevirtual 165	java/io/FileInputStream:close	()V
    //   78: lload_2
    //   79: lreturn
    //   80: astore_0
    //   81: aload 6
    //   83: ifnull +8 -> 91
    //   86: aload 6
    //   88: invokevirtual 165	java/io/FileInputStream:close	()V
    //   91: aload_0
    //   92: athrow
    //   93: lload_2
    //   94: lstore 4
    //   96: aload_0
    //   97: ifnull +6 -> 103
    //   100: goto -26 -> 74
    //   103: lload 4
    //   105: lreturn
    //   106: astore_0
    //   107: aload 7
    //   109: astore_0
    //   110: goto -17 -> 93
    //   113: astore 6
    //   115: goto -66 -> 49
    //   118: astore_0
    //   119: lload_2
    //   120: lreturn
    //   121: astore 6
    //   123: goto -32 -> 91
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramFile	java.io.File
    //   31	2	1	i	int
    //   1	119	2	l1	long
    //   68	36	4	l2	long
    //   3	84	6	localFile	java.io.File
    //   113	1	6	localException1	Exception
    //   121	1	6	localException2	Exception
    //   6	1	7	localObject1	Object
    //   38	70	7	localObject2	Object
    //   9	56	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   27	32	38	finally
    //   11	27	80	finally
    //   52	64	80	finally
    //   11	27	106	java/lang/Exception
    //   52	64	106	java/lang/Exception
    //   27	32	113	java/lang/Exception
    //   74	78	118	java/lang/Exception
    //   86	91	121	java/lang/Exception
  }
  
  public static Drawable a(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      return new BitmapDrawable(paramBitmap);
    }
    return null;
  }
  
  public static String a(JSONArray paramJSONArray)
  {
    Object localObject = com.mobvista.msdk.base.controller.a.d().k();
    b.a();
    com.mobvista.msdk.b.a localA = b.b((String)localObject);
    localObject = localA;
    if (localA == null)
    {
      b.a();
      localObject = b.b();
    }
    int j = ((com.mobvista.msdk.b.a)localObject).T();
    if (paramJSONArray.length() > j)
    {
      localObject = new JSONArray();
      int i = 0;
      while (i < j)
      {
        try
        {
          ((JSONArray)localObject).put(paramJSONArray.get(i));
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
        i += 1;
      }
      return ((JSONArray)localObject).toString();
    }
    return paramJSONArray.toString();
  }
  
  public static void a(View paramView)
  {
    if (paramView == null) {
      return;
    }
    try
    {
      if (Build.VERSION.SDK_INT >= 11) {
        paramView.setSystemUiVisibility(4102);
      }
      return;
    }
    catch (Throwable paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public static void a(ImageView paramImageView)
  {
    if (paramImageView == null) {
      return;
    }
    try
    {
      paramImageView.setImageResource(0);
      paramImageView.setImageDrawable(null);
      paramImageView.setImageURI(null);
      paramImageView.setImageBitmap(null);
      return;
    }
    catch (Throwable paramImageView)
    {
      if (MobVistaConstans.DEBUG) {
        paramImageView.printStackTrace();
      }
    }
  }
  
  public static void a(List<CampaignEx> paramList)
  {
    if (paramList != null)
    {
      if (paramList.size() == 0) {
        return;
      }
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      int j = paramList.size();
      while (i < j)
      {
        CampaignEx localCampaignEx = (CampaignEx)paramList.get(i);
        if ((!a(com.mobvista.msdk.base.controller.a.d().i(), localCampaignEx.getPackageName())) && (localCampaignEx != null) && (localCampaignEx.getOfferType() == 99)) {
          localArrayList.add(localCampaignEx);
        }
        i += 1;
      }
      com.mobvista.msdk.base.b.r.a(i.a(com.mobvista.msdk.base.controller.a.d().i())).a(localArrayList);
      return;
    }
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
      int i;
      try
      {
        paramContext = paramContext.getPackageManager();
        if ((com.mobvista.msdk.base.controller.a.b != null) && (com.mobvista.msdk.base.controller.a.b.size() != 0)) {
          break label247;
        }
        com.mobvista.msdk.base.controller.a.b = new ArrayList();
        paramContext = paramContext.getInstalledPackages(0);
        List localList = com.mobvista.msdk.base.controller.a.d().b();
        i = 0;
        if (i >= paramContext.size()) {
          break label247;
        }
        PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
        if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
          com.mobvista.msdk.base.controller.a.b.add(localPackageInfo.packageName);
        } else if ((localList != null) && (localList.size() > 0) && (localList.contains(localPackageInfo.packageName))) {
          com.mobvista.msdk.base.controller.a.b.add(localPackageInfo.packageName);
        }
      }
      catch (Throwable paramContext)
      {
        boolean bool;
        if (!MobVistaConstans.DEBUG) {
          continue;
        }
        paramContext.printStackTrace();
        return false;
      }
      if (i < com.mobvista.msdk.base.controller.a.b.size())
      {
        paramContext = (String)com.mobvista.msdk.base.controller.a.b.get(i);
        a.add(paramContext);
        i += 1;
      }
      else
      {
        bool = a.contains(paramString);
        return bool;
        return a.contains(paramString);
        i += 1;
        continue;
        label247:
        i = 0;
      }
    }
  }
  
  public static boolean a(CampaignEx paramCampaignEx)
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
        paramContext = new StringBuilder("Permission ");
        paramContext.append(paramString);
        paramContext.append(" is granted");
        h.a("CommonUtils", paramContext.toString());
        return true;
      }
      paramContext = new StringBuilder("Permission ");
      paramContext.append(paramString);
      paramContext.append(" is NOT granted");
      h.a("CommonUtils", paramContext.toString());
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
  
  public static String b(List<String> paramList)
  {
    if (paramList != null) {}
    try
    {
      if (paramList.size() != 0)
      {
        JSONArray localJSONArray = new JSONArray();
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          String str = (String)paramList.next();
          if (r.b(str)) {
            localJSONArray.put(str);
          }
        }
        return localJSONArray.toString();
      }
      return "[]";
    }
    catch (Throwable paramList)
    {
      for (;;) {}
    }
    h.c("CommonUtils", paramList.getMessage(), paramList);
    return "[]";
  }
  
  public static List<String> b(JSONArray paramJSONArray)
  {
    if (paramJSONArray != null) {
      try
      {
        if (paramJSONArray.length() > 0)
        {
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          while (i < paramJSONArray.length())
          {
            String str = paramJSONArray.optString(i);
            if (r.b(str)) {
              localArrayList.add(str);
            }
            i += 1;
          }
          return localArrayList;
        }
      }
      catch (Throwable paramJSONArray)
      {
        h.c("CommonUtils", paramJSONArray.getMessage(), paramJSONArray);
        return null;
      }
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
  
  public static <T extends String> boolean b(T paramT)
  {
    return (paramT != null) && (paramT.length() > 0);
  }
  
  public static double c(String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        double d1 = Double.parseDouble(paramString);
        return d1;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0.0D;
  }
  
  public static void c(Context paramContext)
  {
    try
    {
      if ((!a(paramContext, "com.instagram.android")) && (!a(paramContext, "com.facebook.katana")))
      {
        e = false;
        return;
      }
      e = true;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
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
      if (r.b(paramString))
      {
        paramString = URLEncoder.encode(paramString, "utf-8");
        return paramString;
      }
    }
    catch (Throwable paramString)
    {
      h.c("CommonUtils", paramString.getMessage(), paramString);
    }
    return "";
  }
  
  /* Error */
  public static List<com.mobvista.msdk.base.entity.j> d()
  {
    // Byte code:
    //   0: ldc 49
    //   2: ldc_w 443
    //   5: invokestatic 58	com/mobvista/msdk/base/utils/h:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   8: new 259	java/util/ArrayList
    //   11: dup
    //   12: invokespecial 260	java/util/ArrayList:<init>	()V
    //   15: astore 5
    //   17: new 445	java/io/BufferedReader
    //   20: dup
    //   21: new 447	java/io/InputStreamReader
    //   24: dup
    //   25: invokestatic 453	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   28: ldc_w 455
    //   31: invokevirtual 459	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   34: invokevirtual 465	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   37: invokespecial 468	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   40: sipush 1024
    //   43: invokespecial 471	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   46: astore_3
    //   47: aload_3
    //   48: astore_2
    //   49: aload_3
    //   50: invokevirtual 474	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   53: astore 4
    //   55: aload 4
    //   57: ifnull +125 -> 182
    //   60: aload_3
    //   61: astore_2
    //   62: aload 4
    //   64: ldc_w 476
    //   67: invokevirtual 479	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   70: ifeq -23 -> 47
    //   73: aload_3
    //   74: astore_2
    //   75: aload 4
    //   77: ldc_w 481
    //   80: invokevirtual 485	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   83: astore 6
    //   85: aload 6
    //   87: ifnull -40 -> 47
    //   90: aload_3
    //   91: astore_2
    //   92: aload 6
    //   94: arraylength
    //   95: ifle -48 -> 47
    //   98: aload_3
    //   99: astore_2
    //   100: aload 6
    //   102: aload 6
    //   104: arraylength
    //   105: iconst_1
    //   106: isub
    //   107: aaload
    //   108: astore 6
    //   110: aload_3
    //   111: astore_2
    //   112: aload 5
    //   114: aload 6
    //   116: invokeinterface 280 2 0
    //   121: pop
    //   122: aload_3
    //   123: astore_2
    //   124: new 37	java/lang/StringBuilder
    //   127: dup
    //   128: ldc_w 487
    //   131: invokespecial 43	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   134: astore 7
    //   136: aload_3
    //   137: astore_2
    //   138: aload 7
    //   140: aload 4
    //   142: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload_3
    //   147: astore_2
    //   148: aload 7
    //   150: ldc_w 489
    //   153: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: aload_3
    //   158: astore_2
    //   159: aload 7
    //   161: aload 6
    //   163: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload_3
    //   168: astore_2
    //   169: ldc 49
    //   171: aload 7
    //   173: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: invokestatic 162	com/mobvista/msdk/base/utils/h:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   179: goto -132 -> 47
    //   182: aload_3
    //   183: invokevirtual 490	java/io/BufferedReader:close	()V
    //   186: goto +48 -> 234
    //   189: astore_2
    //   190: aload_2
    //   191: invokevirtual 100	java/lang/Exception:printStackTrace	()V
    //   194: goto +40 -> 234
    //   197: astore 4
    //   199: goto +13 -> 212
    //   202: astore_2
    //   203: aconst_null
    //   204: astore_3
    //   205: goto +280 -> 485
    //   208: astore 4
    //   210: aconst_null
    //   211: astore_3
    //   212: aload_3
    //   213: astore_2
    //   214: aload 4
    //   216: invokevirtual 100	java/lang/Exception:printStackTrace	()V
    //   219: aload_3
    //   220: ifnull +14 -> 234
    //   223: aload_3
    //   224: invokevirtual 490	java/io/BufferedReader:close	()V
    //   227: goto +7 -> 234
    //   230: astore_2
    //   231: goto -41 -> 190
    //   234: new 259	java/util/ArrayList
    //   237: dup
    //   238: invokespecial 260	java/util/ArrayList:<init>	()V
    //   241: astore_2
    //   242: invokestatic 180	com/mobvista/msdk/base/controller/a:d	()Lcom/mobvista/msdk/base/controller/a;
    //   245: invokevirtual 267	com/mobvista/msdk/base/controller/a:i	()Landroid/content/Context;
    //   248: invokevirtual 327	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   251: astore_3
    //   252: iconst_0
    //   253: istore_1
    //   254: aload_3
    //   255: iconst_0
    //   256: invokevirtual 335	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   259: astore_3
    //   260: invokestatic 180	com/mobvista/msdk/base/controller/a:d	()Lcom/mobvista/msdk/base/controller/a;
    //   263: invokevirtual 338	com/mobvista/msdk/base/controller/a:b	()Ljava/util/List;
    //   266: astore 4
    //   268: iconst_0
    //   269: istore_0
    //   270: iload_0
    //   271: aload_3
    //   272: invokeinterface 257 1 0
    //   277: if_icmpge +88 -> 365
    //   280: aload_3
    //   281: iload_0
    //   282: invokeinterface 261 2 0
    //   287: checkcast 340	android/content/pm/PackageInfo
    //   290: astore 6
    //   292: aload 6
    //   294: getfield 344	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   297: getfield 349	android/content/pm/ApplicationInfo:flags	I
    //   300: iconst_1
    //   301: iand
    //   302: ifgt +18 -> 320
    //   305: aload_2
    //   306: aload 6
    //   308: getfield 352	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   311: invokeinterface 280 2 0
    //   316: pop
    //   317: goto +199 -> 516
    //   320: aload 4
    //   322: ifnull +194 -> 516
    //   325: aload 4
    //   327: invokeinterface 257 1 0
    //   332: ifle +184 -> 516
    //   335: aload 4
    //   337: aload 6
    //   339: getfield 352	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   342: invokeinterface 355 2 0
    //   347: ifeq +169 -> 516
    //   350: aload_2
    //   351: aload 6
    //   353: getfield 352	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   356: invokeinterface 280 2 0
    //   361: pop
    //   362: goto +154 -> 516
    //   365: new 259	java/util/ArrayList
    //   368: dup
    //   369: invokespecial 260	java/util/ArrayList:<init>	()V
    //   372: astore_3
    //   373: iload_1
    //   374: istore_0
    //   375: iload_0
    //   376: aload 5
    //   378: invokeinterface 257 1 0
    //   383: if_icmpge +85 -> 468
    //   386: aload 5
    //   388: iload_0
    //   389: invokeinterface 261 2 0
    //   394: checkcast 125	java/lang/String
    //   397: astore 4
    //   399: aload_2
    //   400: aload 4
    //   402: invokeinterface 355 2 0
    //   407: ifeq +116 -> 523
    //   410: new 37	java/lang/StringBuilder
    //   413: dup
    //   414: ldc_w 492
    //   417: invokespecial 43	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   420: astore 6
    //   422: aload 6
    //   424: aload 4
    //   426: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   429: pop
    //   430: ldc 49
    //   432: aload 6
    //   434: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   437: invokestatic 58	com/mobvista/msdk/base/utils/h:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   440: new 494	com/mobvista/msdk/base/entity/j
    //   443: dup
    //   444: invokespecial 495	com/mobvista/msdk/base/entity/j:<init>	()V
    //   447: astore 6
    //   449: aload 6
    //   451: aload 4
    //   453: putfield 497	com/mobvista/msdk/base/entity/j:a	Ljava/lang/String;
    //   456: aload_3
    //   457: aload 6
    //   459: invokeinterface 280 2 0
    //   464: pop
    //   465: goto +58 -> 523
    //   468: ldc 49
    //   470: ldc_w 499
    //   473: invokestatic 58	com/mobvista/msdk/base/utils/h:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   476: aload_3
    //   477: areturn
    //   478: astore 4
    //   480: aload_2
    //   481: astore_3
    //   482: aload 4
    //   484: astore_2
    //   485: aload_3
    //   486: ifnull +15 -> 501
    //   489: aload_3
    //   490: invokevirtual 490	java/io/BufferedReader:close	()V
    //   493: goto +8 -> 501
    //   496: astore_3
    //   497: aload_3
    //   498: invokevirtual 100	java/lang/Exception:printStackTrace	()V
    //   501: aload_2
    //   502: athrow
    //   503: astore_2
    //   504: ldc 49
    //   506: aload_2
    //   507: invokevirtual 134	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   510: aload_2
    //   511: invokestatic 137	com/mobvista/msdk/base/utils/h:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   514: aconst_null
    //   515: areturn
    //   516: iload_0
    //   517: iconst_1
    //   518: iadd
    //   519: istore_0
    //   520: goto -250 -> 270
    //   523: iload_0
    //   524: iconst_1
    //   525: iadd
    //   526: istore_0
    //   527: goto -152 -> 375
    // Local variable table:
    //   start	length	slot	name	signature
    //   269	258	0	i	int
    //   253	121	1	j	int
    //   48	121	2	localObject1	Object
    //   189	2	2	localException1	Exception
    //   202	1	2	localObject2	Object
    //   213	1	2	localObject3	Object
    //   230	1	2	localException2	Exception
    //   241	261	2	localObject4	Object
    //   503	8	2	localThrowable	Throwable
    //   46	444	3	localObject5	Object
    //   496	2	3	localException3	Exception
    //   53	88	4	str	String
    //   197	1	4	localException4	Exception
    //   208	7	4	localException5	Exception
    //   266	186	4	localObject6	Object
    //   478	5	4	localObject7	Object
    //   15	372	5	localArrayList	ArrayList
    //   83	375	6	localObject8	Object
    //   134	38	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   182	186	189	java/lang/Exception
    //   49	55	197	java/lang/Exception
    //   62	73	197	java/lang/Exception
    //   75	85	197	java/lang/Exception
    //   92	98	197	java/lang/Exception
    //   100	110	197	java/lang/Exception
    //   112	122	197	java/lang/Exception
    //   124	136	197	java/lang/Exception
    //   138	146	197	java/lang/Exception
    //   148	157	197	java/lang/Exception
    //   159	167	197	java/lang/Exception
    //   169	179	197	java/lang/Exception
    //   17	47	202	finally
    //   17	47	208	java/lang/Exception
    //   223	227	230	java/lang/Exception
    //   49	55	478	finally
    //   62	73	478	finally
    //   75	85	478	finally
    //   92	98	478	finally
    //   100	110	478	finally
    //   112	122	478	finally
    //   124	136	478	finally
    //   138	146	478	finally
    //   148	157	478	finally
    //   159	167	478	finally
    //   169	179	478	finally
    //   214	219	478	finally
    //   489	493	496	java/lang/Exception
    //   0	17	503	java/lang/Throwable
    //   182	186	503	java/lang/Throwable
    //   190	194	503	java/lang/Throwable
    //   223	227	503	java/lang/Throwable
    //   234	252	503	java/lang/Throwable
    //   254	268	503	java/lang/Throwable
    //   270	317	503	java/lang/Throwable
    //   325	362	503	java/lang/Throwable
    //   365	373	503	java/lang/Throwable
    //   375	465	503	java/lang/Throwable
    //   468	476	503	java/lang/Throwable
    //   489	493	503	java/lang/Throwable
    //   497	501	503	java/lang/Throwable
    //   501	503	503	java/lang/Throwable
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
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(UUID.randomUUID().toString());
      ((StringBuilder)localObject2).append(System.currentTimeMillis());
      localObject2 = ((StringBuilder)localObject2).toString();
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    Object localObject3 = localObject1;
    if (r.a((String)localObject1))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(System.currentTimeMillis());
      localObject3 = ((StringBuilder)localObject1).toString();
    }
    return localObject3;
  }
  
  public static float f(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static int g(Context paramContext)
  {
    if (paramContext == null) {
      return -1;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        int i = paramContext.size();
        return i;
      }
      return -1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
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
    boolean bool1;
    try
    {
      paramContext = paramContext.getResources();
      int i = paramContext.getIdentifier("config_showNavigationBar", "bool", "android");
      if (i > 0) {
        bool1 = paramContext.getBoolean(i);
      } else {
        bool1 = false;
      }
      try
      {
        paramContext = Class.forName("android.os.SystemProperties");
        paramContext = (String)paramContext.getMethod("get", new Class[] { String.class }).invoke(paramContext, new Object[] { "qemu.hw.mainkeys" });
        if ("1".equals(paramContext)) {
          return false;
        }
        boolean bool2 = "0".equals(paramContext);
        if (!bool2) {
          return bool1;
        }
        return true;
      }
      catch (Throwable paramContext) {}
      h.c("CommonUtils", paramContext.getMessage(), paramContext);
    }
    catch (Throwable paramContext)
    {
      bool1 = false;
    }
    return bool1;
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
