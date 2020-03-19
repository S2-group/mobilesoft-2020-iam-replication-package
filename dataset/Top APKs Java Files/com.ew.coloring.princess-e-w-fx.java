package e.w;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ew.sdk.activity.WebActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class fx
{
  public static int a()
  {
    try
    {
      int i = a.a().a().getPackageManager().getPackageInfo(a.a().a().getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      ez.a("Get Version Code Error!!!", localException);
    }
    return -1;
  }
  
  public static int a(Context paramContext)
  {
    if (!a(paramContext, "android.permission.GET_ACCOUNTS")) {
      return -1;
    }
    paramContext = AccountManager.get(paramContext).getAccounts();
    if ((paramContext != null) && (paramContext.length > 0)) {
      return 1;
    }
    return 0;
  }
  
  public static Intent a(Context paramContext, eo paramEo, String paramString)
  {
    if (paramEo == null) {
      return null;
    }
    String str = paramEo.jdField_a_of_type_JavaLangString;
    int i = -1;
    switch (str.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return null;
      case 0: 
        return a(paramEo, paramString);
        if (str.equals("market"))
        {
          i = 0;
          continue;
          if (str.equals("browse"))
          {
            i = 1;
            continue;
            if (str.equals("webview")) {
              i = 2;
            }
          }
        }
        break;
      }
    }
    paramContext = new Intent("android.intent.action.VIEW", Uri.parse(a(paramEo.h, paramEo, paramString)));
    paramContext.addFlags(268435456);
    return paramContext;
    paramContext = new Intent(paramContext, WebActivity.class);
    paramContext.addFlags(268435456);
    paramContext.putExtra("ad_url", Uri.parse(a(paramEo.h, paramEo, paramString)));
    return paramContext;
  }
  
  public static Intent a(eo paramEo, String paramString)
  {
    Object localObject2 = null;
    if (paramEo.jdField_a_of_type_EWEq != null) {
      if (paramEo.jdField_a_of_type_EWEq.jdField_a_of_type_JavaLangString == null) {
        break label241;
      }
    }
    label241:
    for (Object localObject1 = eg.a().a(paramEo.jdField_a_of_type_EWEq.jdField_a_of_type_JavaLangString);; localObject1 = null)
    {
      int i;
      if ((localObject1 == null) && (paramEo.jdField_a_of_type_EWEq.jdField_a_of_type_ArrayOfJavaLangString != null))
      {
        String[] arrayOfString = paramEo.jdField_a_of_type_EWEq.jdField_a_of_type_ArrayOfJavaLangString;
        int j = arrayOfString.length;
        i = 0;
        if (i < j)
        {
          Object localObject3 = arrayOfString[i];
          localObject3 = eg.a().a((String)localObject3);
          if (localObject3 != null) {
            localObject1 = localObject3;
          }
        }
      }
      for (;;)
      {
        if ((localObject1 != null) && (a(((ek)localObject1).b)))
        {
          paramString = new Intent("android.intent.action.VIEW", Uri.parse(a(((ek)localObject1).c, paramEo, paramString)));
          paramString.setPackage(((ek)localObject1).b);
          paramString.addFlags(268435456);
        }
        do
        {
          do
          {
            return paramString;
            i += 1;
            break;
            paramString = localObject2;
          } while (TextUtils.isEmpty(paramEo.h));
          paramEo = new Intent("android.intent.action.VIEW", Uri.parse(paramEo.h));
          paramEo.addFlags(268435456);
          return paramEo;
          paramString = localObject2;
        } while (TextUtils.isEmpty(paramEo.h));
        paramEo = new Intent("android.intent.action.VIEW", Uri.parse(paramEo.h));
        paramEo.addFlags(268435456);
        return paramEo;
      }
    }
  }
  
  public static String a()
  {
    int i = b();
    if (i == -1) {
      return "unknow";
    }
    if (i == 1) {
      return "wifi";
    }
    switch (((TelephonyManager)a.a().a().getSystemService("phone")).getNetworkType())
    {
    case 13: 
    default: 
      return "4g";
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return "2g";
    }
    return "3g";
  }
  
  public static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      Object localObject = a.a().a();
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getString(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        ez.a("Get Meta_Data Info error:", paramString);
      }
    }
    return null;
  }
  
  private static String a(String paramString1, eo paramEo, String paramString2)
  {
    String str = paramString1;
    if (!TextUtils.isEmpty(paramString1)) {
      str = paramString1;
    }
    try
    {
      paramString1 = paramString1.replace("$PKGNAME", paramEo.g);
      str = paramString1;
      paramString1 = paramString1.replace("$HostPKGNAME", a.a().a().getPackageName());
      str = paramString1;
      paramString1 = paramString1.replace("$AdType", paramString2);
      str = paramString1;
    }
    catch (Exception paramString1)
    {
      for (;;) {}
    }
    ez.a("marketurl=" + str);
    return str;
  }
  
  private static String a(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (!TextUtils.isEmpty(paramString1)) {}
    try
    {
      str = paramString1.replace("$ACCOUNT", paramString2);
      ez.a("social url=" + str);
      return str;
    }
    catch (Exception paramString2)
    {
      for (;;)
      {
        str = paramString1;
      }
    }
  }
  
  public static List a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      localArrayList.add(((PackageInfo)paramContext.next()).packageName);
    }
    return localArrayList;
  }
  
  /* Error */
  public static void a(Context paramContext, eo paramEo, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_1
    //   4: getfield 125	e/w/eo:jdField_a_of_type_EWEq	Le/w/eq;
    //   7: ifnull +380 -> 387
    //   10: aload_1
    //   11: getfield 125	e/w/eo:jdField_a_of_type_EWEq	Le/w/eq;
    //   14: getfield 128	e/w/eq:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   17: ifnull +18 -> 35
    //   20: invokestatic 133	e/w/eg:a	()Le/w/eg;
    //   23: aload_1
    //   24: getfield 125	e/w/eo:jdField_a_of_type_EWEq	Le/w/eq;
    //   27: getfield 128	e/w/eq:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   30: invokevirtual 136	e/w/eg:a	(Ljava/lang/String;)Le/w/ek;
    //   33: astore 5
    //   35: aload 5
    //   37: ifnonnull +418 -> 455
    //   40: aload_1
    //   41: getfield 125	e/w/eo:jdField_a_of_type_EWEq	Le/w/eq;
    //   44: getfield 139	e/w/eq:jdField_a_of_type_ArrayOfJavaLangString	[Ljava/lang/String;
    //   47: ifnull +408 -> 455
    //   50: aload_1
    //   51: getfield 125	e/w/eo:jdField_a_of_type_EWEq	Le/w/eq;
    //   54: getfield 139	e/w/eq:jdField_a_of_type_ArrayOfJavaLangString	[Ljava/lang/String;
    //   57: astore 7
    //   59: aload 7
    //   61: arraylength
    //   62: istore 4
    //   64: iconst_0
    //   65: istore_3
    //   66: iload_3
    //   67: iload 4
    //   69: if_icmpge +386 -> 455
    //   72: aload 7
    //   74: iload_3
    //   75: aaload
    //   76: astore 6
    //   78: invokestatic 133	e/w/eg:a	()Le/w/eg;
    //   81: aload 6
    //   83: invokevirtual 136	e/w/eg:a	(Ljava/lang/String;)Le/w/ek;
    //   86: astore 6
    //   88: aload 6
    //   90: ifnull +369 -> 459
    //   93: aload 6
    //   95: astore 5
    //   97: aload 5
    //   99: ifnull +220 -> 319
    //   102: aload 5
    //   104: getfield 144	e/w/ek:b	Ljava/lang/String;
    //   107: invokestatic 147	e/w/fx:a	(Ljava/lang/String;)Z
    //   110: ifeq +209 -> 319
    //   113: ldc_w 273
    //   116: aload 5
    //   118: getfield 274	e/w/ek:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   121: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   124: ifeq +80 -> 204
    //   127: aload_0
    //   128: invokestatic 276	e/w/fx:a	(Landroid/content/Context;)I
    //   131: ifne +73 -> 204
    //   134: aload_1
    //   135: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   138: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   141: ifne +33 -> 174
    //   144: new 88	android/content/Intent
    //   147: dup
    //   148: ldc 90
    //   150: aload_1
    //   151: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   154: invokestatic 102	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   157: invokespecial 106	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   160: astore_1
    //   161: aload_1
    //   162: ldc 107
    //   164: invokevirtual 111	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   167: pop
    //   168: aload_0
    //   169: aload_1
    //   170: invokevirtual 280	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   173: return
    //   174: aload_1
    //   175: getfield 281	e/w/eo:b	Ljava/lang/String;
    //   178: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   181: ifne +277 -> 458
    //   184: aload_0
    //   185: aload_1
    //   186: invokevirtual 283	e/w/eo:b	()Ljava/lang/String;
    //   189: aload_1
    //   190: getfield 208	e/w/eo:g	Ljava/lang/String;
    //   193: aload_1
    //   194: invokevirtual 285	e/w/eo:c	()Ljava/lang/String;
    //   197: invokestatic 290	e/w/fc:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   200: pop
    //   201: return
    //   202: astore_0
    //   203: return
    //   204: new 88	android/content/Intent
    //   207: dup
    //   208: ldc 90
    //   210: aload 5
    //   212: getfield 150	e/w/ek:c	Ljava/lang/String;
    //   215: aload_1
    //   216: aload_2
    //   217: invokestatic 96	e/w/fx:a	(Ljava/lang/String;Le/w/eo;Ljava/lang/String;)Ljava/lang/String;
    //   220: invokestatic 102	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   223: invokespecial 106	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   226: astore_2
    //   227: aload_2
    //   228: aload 5
    //   230: getfield 144	e/w/ek:b	Ljava/lang/String;
    //   233: invokevirtual 154	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   236: pop
    //   237: aload_2
    //   238: ldc 107
    //   240: invokevirtual 111	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   243: pop
    //   244: aload_0
    //   245: aload_2
    //   246: invokevirtual 280	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   249: return
    //   250: astore_2
    //   251: aload_1
    //   252: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   255: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   258: ifne +33 -> 291
    //   261: new 88	android/content/Intent
    //   264: dup
    //   265: ldc 90
    //   267: aload_1
    //   268: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   271: invokestatic 102	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   274: invokespecial 106	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   277: astore_1
    //   278: aload_1
    //   279: ldc 107
    //   281: invokevirtual 111	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   284: pop
    //   285: aload_0
    //   286: aload_1
    //   287: invokevirtual 280	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   290: return
    //   291: aload_1
    //   292: getfield 281	e/w/eo:b	Ljava/lang/String;
    //   295: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   298: ifne +160 -> 458
    //   301: aload_0
    //   302: aload_1
    //   303: invokevirtual 283	e/w/eo:b	()Ljava/lang/String;
    //   306: aload_1
    //   307: getfield 208	e/w/eo:g	Ljava/lang/String;
    //   310: aload_1
    //   311: invokevirtual 285	e/w/eo:c	()Ljava/lang/String;
    //   314: invokestatic 290	e/w/fc:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   317: pop
    //   318: return
    //   319: aload_1
    //   320: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   323: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   326: ifne +33 -> 359
    //   329: new 88	android/content/Intent
    //   332: dup
    //   333: ldc 90
    //   335: aload_1
    //   336: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   339: invokestatic 102	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   342: invokespecial 106	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   345: astore_1
    //   346: aload_1
    //   347: ldc 107
    //   349: invokevirtual 111	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   352: pop
    //   353: aload_0
    //   354: aload_1
    //   355: invokevirtual 280	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   358: return
    //   359: aload_1
    //   360: getfield 281	e/w/eo:b	Ljava/lang/String;
    //   363: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   366: ifne +92 -> 458
    //   369: aload_0
    //   370: aload_1
    //   371: invokevirtual 283	e/w/eo:b	()Ljava/lang/String;
    //   374: aload_1
    //   375: getfield 208	e/w/eo:g	Ljava/lang/String;
    //   378: aload_1
    //   379: invokevirtual 285	e/w/eo:c	()Ljava/lang/String;
    //   382: invokestatic 290	e/w/fc:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   385: pop
    //   386: return
    //   387: aload_1
    //   388: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   391: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   394: ifne +33 -> 427
    //   397: new 88	android/content/Intent
    //   400: dup
    //   401: ldc 90
    //   403: aload_1
    //   404: getfield 93	e/w/eo:h	Ljava/lang/String;
    //   407: invokestatic 102	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   410: invokespecial 106	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   413: astore_1
    //   414: aload_1
    //   415: ldc 107
    //   417: invokevirtual 111	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   420: pop
    //   421: aload_0
    //   422: aload_1
    //   423: invokevirtual 280	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   426: return
    //   427: aload_1
    //   428: getfield 281	e/w/eo:b	Ljava/lang/String;
    //   431: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   434: ifne +24 -> 458
    //   437: aload_0
    //   438: aload_1
    //   439: invokevirtual 283	e/w/eo:b	()Ljava/lang/String;
    //   442: aload_1
    //   443: getfield 208	e/w/eo:g	Ljava/lang/String;
    //   446: aload_1
    //   447: invokevirtual 285	e/w/eo:c	()Ljava/lang/String;
    //   450: invokestatic 290	e/w/fc:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   453: pop
    //   454: return
    //   455: goto -358 -> 97
    //   458: return
    //   459: iload_3
    //   460: iconst_1
    //   461: iadd
    //   462: istore_3
    //   463: goto -397 -> 66
    //   466: astore_0
    //   467: return
    //   468: astore_0
    //   469: return
    //   470: astore_0
    //   471: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	472	0	paramContext	Context
    //   0	472	1	paramEo	eo
    //   0	472	2	paramString	String
    //   65	398	3	i	int
    //   62	8	4	j	int
    //   1	228	5	localObject1	Object
    //   76	18	6	localObject2	Object
    //   57	16	7	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   134	173	202	java/lang/Exception
    //   174	201	202	java/lang/Exception
    //   244	249	250	android/content/ActivityNotFoundException
    //   251	290	466	java/lang/Exception
    //   291	318	466	java/lang/Exception
    //   319	358	468	java/lang/Exception
    //   359	386	468	java/lang/Exception
    //   387	426	470	java/lang/Exception
    //   427	454	470	java/lang/Exception
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    int i = 0;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {}
    for (;;)
    {
      return;
      ez.a("social goMarket type=" + paramString1);
      paramString1 = eg.a().a(paramString1);
      if (paramString1 != null) {
        try
        {
          if (!TextUtils.isEmpty(paramString1.c))
          {
            paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a(paramString1.c, paramString2))));
            if (i != 0) {
              continue;
            }
            new Handler().postDelayed(new fy(paramContext, paramInt), 3000L);
          }
        }
        catch (Exception localException)
        {
          try
          {
            if (!TextUtils.isEmpty(paramString1.d)) {
              paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a(paramString1.d, paramString2))));
            }
          }
          catch (Exception paramString1)
          {
            ez.a("social goMarket error ", paramString1);
          }
        }
      }
    }
    for (;;)
    {
      i = 1;
      break;
      try
      {
        if (!TextUtils.isEmpty(paramString1.d)) {
          paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a(paramString1.d, paramString2))));
        }
      }
      catch (Exception paramString1)
      {
        ez.a("social goMarket error ", paramString1);
      }
    }
  }
  
  public static boolean a()
  {
    NetworkInfo localNetworkInfo;
    if (a.a().a().checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      localNetworkInfo = ((ConnectivityManager)a.a().a().getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null) {}
    }
    else
    {
      return false;
    }
    int i = localNetworkInfo.getType();
    if (((i == 1) || (i == 0) || (i == 6)) && (localNetworkInfo.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (paramContext.getPackageManager().checkPermission(paramString, "packageName") == 0) {}
    for (int i = 1; i != 0; i = 0) {
      return true;
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    boolean bool = false;
    Object localObject = null;
    try
    {
      paramString = a.a().a().getPackageManager().getPackageInfo(paramString, 0);
      if (paramString != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString = localObject;
      }
    }
  }
  
  private static int b()
  {
    Object localObject = (ConnectivityManager)a.a().a().getSystemService("connectivity");
    if (localObject == null) {
      return -1;
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if (localObject == null) {
      return -1;
    }
    return ((NetworkInfo)localObject).getType();
  }
  
  public static void b(Context paramContext, eo paramEo, String paramString)
  {
    if ((paramContext == null) || (paramEo == null)) {
      return;
    }
    Object localObject = paramEo.jdField_a_of_type_JavaLangString;
    int i = -1;
    switch (((String)localObject).hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
      case 0: 
        try
        {
          a(paramContext, paramEo, paramString);
          paramContext = new et();
          paramContext.jdField_a_of_type_EWEo = paramEo;
          paramContext.jdField_a_of_type_JavaLangString = paramString;
          er.a().a(paramContext);
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
        if (((String)localObject).equals("market"))
        {
          i = 0;
          continue;
          if (((String)localObject).equals("browse"))
          {
            i = 1;
            continue;
            if (((String)localObject).equals("webview")) {
              i = 2;
            }
          }
        }
        break;
      }
    }
    try
    {
      new Handler().postDelayed(new fz(paramContext, paramEo), 3000L);
      paramEo = new Intent("android.intent.action.VIEW", Uri.parse(a(paramEo.h, paramEo, paramString)));
      paramEo.addFlags(268435456);
      paramContext.startActivity(paramEo);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    try
    {
      localObject = new Intent(paramContext, WebActivity.class);
      ((Intent)localObject).putExtra("ad_url", a(paramEo.h, paramEo, paramString));
      paramContext.startActivity((Intent)localObject);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean b()
  {
    return (a.a().a().getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = a.a().a();
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getBoolean(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        ez.a("Get Meta_Data Info error:", paramString);
      }
    }
    return false;
  }
  
  public static boolean c(String paramString)
  {
    paramString = eg.a().a(paramString);
    return (paramString != null) && (a(paramString.b));
  }
}
