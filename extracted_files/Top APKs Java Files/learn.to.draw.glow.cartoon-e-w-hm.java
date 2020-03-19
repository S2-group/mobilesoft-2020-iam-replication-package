package e.w;

import android.accounts.AccountManager;
import android.app.Application;
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
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ew.sdk.TaskActiveListener;
import com.ew.sdk.activity.WebActivity;
import com.ew.sdk.model.SelfAdData;
import com.ew.sdk.model.SelfMarketInfo;
import com.ew.sdk.model.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class hm
{
  public static int a()
  {
    try
    {
      int i = a.d().c().getPackageManager().getPackageInfo(a.d().c().getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      gk.a("Get Version Code Error!!!", localException);
    }
    return -1;
  }
  
  public static Intent a(SelfAdData paramSelfAdData, String paramString)
  {
    Object localObject2 = null;
    if (paramSelfAdData.market != null) {
      if (paramSelfAdData.market.jdField_a_of_type_JavaLangString == null) {
        break label257;
      }
    }
    label257:
    for (Object localObject1 = fj.a().i(paramSelfAdData.market.jdField_a_of_type_JavaLangString);; localObject1 = null)
    {
      int i;
      if ((localObject1 == null) && (paramSelfAdData.market.jdField_a_of_type_ArrayOfJavaLangString != null))
      {
        String[] arrayOfString = paramSelfAdData.market.jdField_a_of_type_ArrayOfJavaLangString;
        int j = arrayOfString.length;
        i = 0;
        if (i < j)
        {
          Object localObject3 = arrayOfString[i];
          localObject3 = fj.a().i((String)localObject3);
          if (localObject3 != null) {
            localObject1 = localObject3;
          }
        }
      }
      for (;;)
      {
        if ((localObject1 != null) && (a(((fq)localObject1).b)))
        {
          paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(((fq)localObject1).c, paramSelfAdData, paramString)));
          paramSelfAdData.setPackage(((fq)localObject1).b);
          paramSelfAdData.addFlags(268435456);
          localObject1 = paramSelfAdData;
        }
        do
        {
          do
          {
            return localObject1;
            i += 1;
            break;
            localObject1 = localObject2;
          } while (TextUtils.isEmpty(paramSelfAdData.weburl));
          paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.weburl, paramSelfAdData, paramString)));
          paramSelfAdData.addFlags(268435456);
          return paramSelfAdData;
          localObject1 = localObject2;
        } while (TextUtils.isEmpty(paramSelfAdData.weburl));
        paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.weburl, paramSelfAdData, paramString)));
        paramSelfAdData.addFlags(268435456);
        return paramSelfAdData;
      }
    }
  }
  
  public static Intent a(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return null;
    }
    gk.a("social goMarket type=" + paramString1);
    fq localFq = fj.a().i(paramString1);
    if (localFq != null)
    {
      String str2 = localFq.c;
      String str1 = str2;
      Object localObject1 = paramString2;
      if ("facebook".equals(paramString1)) {
        localObject1 = paramString2;
      }
      for (;;)
      {
        try
        {
          if (a.d().c().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850)
          {
            localObject1 = paramString2;
            paramString2 = "https://www.facebook.com/" + paramString2;
            localObject1 = paramString2;
            paramString1 = "fb://facewebmodal/f?href=" + paramString2;
            localObject1 = paramString2;
            str1 = paramString1;
          }
        }
        catch (Exception paramString1)
        {
          gk.a(paramString1);
          str1 = str2;
          continue;
        }
        try
        {
          if (TextUtils.isEmpty(str1)) {
            break label286;
          }
          paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(b(str1, (String)localObject1)));
          try
          {
            paramString1.addFlags(268435456);
            return paramString1;
          }
          catch (Exception paramString2) {}
        }
        catch (Exception paramString1)
        {
          paramString1 = localObject2;
          continue;
        }
        try
        {
          if (TextUtils.isEmpty(localFq.d)) {
            break;
          }
          localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(b(localFq.d, (String)localObject1)));
          try
          {
            ((Intent)localObject1).addFlags(268435456);
            return localObject1;
          }
          catch (Exception paramString2)
          {
            paramString1 = (String)localObject1;
          }
        }
        catch (Exception paramString2)
        {
          continue;
        }
        gk.a("social goMarket error ", paramString2);
        return paramString1;
        localObject1 = paramString2;
        paramString1 = "fb://page/" + paramString2;
      }
      return paramString1;
      label286:
      return null;
    }
    return null;
  }
  
  private static String a(String paramString1, SelfAdData paramSelfAdData, String paramString2)
  {
    String str;
    if (!TextUtils.isEmpty(paramString1)) {
      str = paramString1;
    }
    for (;;)
    {
      try
      {
        if (paramString1.indexOf("play.google.com") < 0) {
          continue;
        }
        str = paramString1;
        paramString1 = paramString1 + "&referrer=utm_source%3D" + a.d().c().getPackageName() + "%26utm_content%3D" + paramString2 + "%utm_campaign%3D" + gm.c();
      }
      catch (Exception paramString1)
      {
        paramString1 = str;
        continue;
      }
      gk.a("marketurl=" + paramString1);
      return paramString1;
      str = paramString1;
      paramString1 = paramString1.replace("$PKGNAME", paramSelfAdData.pkgname);
      str = paramString1;
      paramString1 = paramString1.replace("$HostPKGNAME", a.d().c().getPackageName());
      str = paramString1;
      paramString1 = paramString1.replace("$AdType", paramString2);
    }
  }
  
  public static List a(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((PackageInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      gk.a(paramContext);
    }
  }
  
  /* Error */
  public static void a(Context paramContext, SelfAdData paramSelfAdData, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_1
    //   4: getfield 55	com/ew/sdk/model/SelfAdData:market	Lcom/ew/sdk/model/SelfMarketInfo;
    //   7: ifnull +399 -> 406
    //   10: aload_1
    //   11: getfield 55	com/ew/sdk/model/SelfAdData:market	Lcom/ew/sdk/model/SelfMarketInfo;
    //   14: getfield 60	com/ew/sdk/model/SelfMarketInfo:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   17: ifnull +18 -> 35
    //   20: invokestatic 65	e/w/fj:a	()Le/w/fj;
    //   23: aload_1
    //   24: getfield 55	com/ew/sdk/model/SelfAdData:market	Lcom/ew/sdk/model/SelfMarketInfo;
    //   27: getfield 60	com/ew/sdk/model/SelfMarketInfo:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   30: invokevirtual 69	e/w/fj:i	(Ljava/lang/String;)Le/w/fq;
    //   33: astore 5
    //   35: aload 5
    //   37: ifnonnull +442 -> 479
    //   40: aload_1
    //   41: getfield 55	com/ew/sdk/model/SelfAdData:market	Lcom/ew/sdk/model/SelfMarketInfo;
    //   44: getfield 72	com/ew/sdk/model/SelfMarketInfo:jdField_a_of_type_ArrayOfJavaLangString	[Ljava/lang/String;
    //   47: ifnull +432 -> 479
    //   50: aload_1
    //   51: getfield 55	com/ew/sdk/model/SelfAdData:market	Lcom/ew/sdk/model/SelfMarketInfo;
    //   54: getfield 72	com/ew/sdk/model/SelfMarketInfo:jdField_a_of_type_ArrayOfJavaLangString	[Ljava/lang/String;
    //   57: astore 7
    //   59: aload 7
    //   61: arraylength
    //   62: istore 4
    //   64: iconst_0
    //   65: istore_3
    //   66: iload_3
    //   67: iload 4
    //   69: if_icmpge +410 -> 479
    //   72: aload 7
    //   74: iload_3
    //   75: aaload
    //   76: astore 6
    //   78: invokestatic 65	e/w/fj:a	()Le/w/fj;
    //   81: aload 6
    //   83: invokevirtual 69	e/w/fj:i	(Ljava/lang/String;)Le/w/fq;
    //   86: astore 6
    //   88: aload 6
    //   90: ifnull +393 -> 483
    //   93: aload 6
    //   95: astore 5
    //   97: aload 5
    //   99: ifnull +234 -> 333
    //   102: aload 5
    //   104: getfield 77	e/w/fq:b	Ljava/lang/String;
    //   107: invokestatic 80	e/w/hm:a	(Ljava/lang/String;)Z
    //   110: ifeq +223 -> 333
    //   113: ldc -25
    //   115: aload 5
    //   117: getfield 232	e/w/fq:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   120: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   123: ifeq +85 -> 208
    //   126: aload_0
    //   127: invokestatic 235	e/w/hm:b	(Landroid/content/Context;)I
    //   130: ifne +78 -> 208
    //   133: aload_1
    //   134: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   137: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   140: ifne +38 -> 178
    //   143: new 82	android/content/Intent
    //   146: dup
    //   147: ldc 84
    //   149: aload_1
    //   150: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   153: aload_1
    //   154: aload_2
    //   155: invokestatic 89	e/w/hm:a	(Ljava/lang/String;Lcom/ew/sdk/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   158: invokestatic 95	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   161: invokespecial 99	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   164: astore_1
    //   165: aload_1
    //   166: ldc 104
    //   168: invokevirtual 108	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   171: pop
    //   172: aload_0
    //   173: aload_1
    //   174: invokevirtual 239	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   177: return
    //   178: aload_1
    //   179: getfield 242	com/ew/sdk/model/SelfAdData:uri	Ljava/lang/String;
    //   182: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   185: ifne +297 -> 482
    //   188: aload_0
    //   189: aload_1
    //   190: invokevirtual 245	com/ew/sdk/model/SelfAdData:getUri	()Ljava/lang/String;
    //   193: aload_1
    //   194: getfield 185	com/ew/sdk/model/SelfAdData:pkgname	Ljava/lang/String;
    //   197: aload_1
    //   198: invokevirtual 248	com/ew/sdk/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   201: invokestatic 253	e/w/gn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   204: pop
    //   205: return
    //   206: astore_0
    //   207: return
    //   208: new 82	android/content/Intent
    //   211: dup
    //   212: ldc 84
    //   214: aload 5
    //   216: getfield 86	e/w/fq:c	Ljava/lang/String;
    //   219: aload_1
    //   220: aload_2
    //   221: invokestatic 89	e/w/hm:a	(Ljava/lang/String;Lcom/ew/sdk/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   224: invokestatic 95	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   227: invokespecial 99	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   230: astore 6
    //   232: aload 6
    //   234: aload 5
    //   236: getfield 77	e/w/fq:b	Ljava/lang/String;
    //   239: invokevirtual 103	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   242: pop
    //   243: aload 6
    //   245: ldc 104
    //   247: invokevirtual 108	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   250: pop
    //   251: aload_0
    //   252: aload 6
    //   254: invokevirtual 239	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   257: return
    //   258: astore 5
    //   260: aload_1
    //   261: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   264: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   267: ifne +38 -> 305
    //   270: new 82	android/content/Intent
    //   273: dup
    //   274: ldc 84
    //   276: aload_1
    //   277: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   280: aload_1
    //   281: aload_2
    //   282: invokestatic 89	e/w/hm:a	(Ljava/lang/String;Lcom/ew/sdk/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   285: invokestatic 95	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   288: invokespecial 99	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   291: astore_1
    //   292: aload_1
    //   293: ldc 104
    //   295: invokevirtual 108	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   298: pop
    //   299: aload_0
    //   300: aload_1
    //   301: invokevirtual 239	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   304: return
    //   305: aload_1
    //   306: getfield 242	com/ew/sdk/model/SelfAdData:uri	Ljava/lang/String;
    //   309: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   312: ifne +170 -> 482
    //   315: aload_0
    //   316: aload_1
    //   317: invokevirtual 245	com/ew/sdk/model/SelfAdData:getUri	()Ljava/lang/String;
    //   320: aload_1
    //   321: getfield 185	com/ew/sdk/model/SelfAdData:pkgname	Ljava/lang/String;
    //   324: aload_1
    //   325: invokevirtual 248	com/ew/sdk/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   328: invokestatic 253	e/w/gn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   331: pop
    //   332: return
    //   333: aload_1
    //   334: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   337: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   340: ifne +38 -> 378
    //   343: new 82	android/content/Intent
    //   346: dup
    //   347: ldc 84
    //   349: aload_1
    //   350: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   353: aload_1
    //   354: aload_2
    //   355: invokestatic 89	e/w/hm:a	(Ljava/lang/String;Lcom/ew/sdk/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   358: invokestatic 95	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   361: invokespecial 99	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   364: astore_1
    //   365: aload_1
    //   366: ldc 104
    //   368: invokevirtual 108	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   371: pop
    //   372: aload_0
    //   373: aload_1
    //   374: invokevirtual 239	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   377: return
    //   378: aload_1
    //   379: getfield 242	com/ew/sdk/model/SelfAdData:uri	Ljava/lang/String;
    //   382: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   385: ifne +97 -> 482
    //   388: aload_0
    //   389: aload_1
    //   390: invokevirtual 245	com/ew/sdk/model/SelfAdData:getUri	()Ljava/lang/String;
    //   393: aload_1
    //   394: getfield 185	com/ew/sdk/model/SelfAdData:pkgname	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 248	com/ew/sdk/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   401: invokestatic 253	e/w/gn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   404: pop
    //   405: return
    //   406: aload_1
    //   407: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   410: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   413: ifne +38 -> 451
    //   416: new 82	android/content/Intent
    //   419: dup
    //   420: ldc 84
    //   422: aload_1
    //   423: getfield 111	com/ew/sdk/model/SelfAdData:weburl	Ljava/lang/String;
    //   426: aload_1
    //   427: aload_2
    //   428: invokestatic 89	e/w/hm:a	(Ljava/lang/String;Lcom/ew/sdk/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   431: invokestatic 95	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   434: invokespecial 99	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   437: astore_1
    //   438: aload_1
    //   439: ldc 104
    //   441: invokevirtual 108	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   444: pop
    //   445: aload_0
    //   446: aload_1
    //   447: invokevirtual 239	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   450: return
    //   451: aload_1
    //   452: getfield 242	com/ew/sdk/model/SelfAdData:uri	Ljava/lang/String;
    //   455: invokestatic 117	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   458: ifne +24 -> 482
    //   461: aload_0
    //   462: aload_1
    //   463: invokevirtual 245	com/ew/sdk/model/SelfAdData:getUri	()Ljava/lang/String;
    //   466: aload_1
    //   467: getfield 185	com/ew/sdk/model/SelfAdData:pkgname	Ljava/lang/String;
    //   470: aload_1
    //   471: invokevirtual 248	com/ew/sdk/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   474: invokestatic 253	e/w/gn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   477: pop
    //   478: return
    //   479: goto -382 -> 97
    //   482: return
    //   483: iload_3
    //   484: iconst_1
    //   485: iadd
    //   486: istore_3
    //   487: goto -421 -> 66
    //   490: astore_0
    //   491: return
    //   492: astore_0
    //   493: return
    //   494: astore_0
    //   495: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	496	0	paramContext	Context
    //   0	496	1	paramSelfAdData	SelfAdData
    //   0	496	2	paramString	String
    //   65	422	3	i	int
    //   62	8	4	j	int
    //   1	234	5	localObject1	Object
    //   258	1	5	localActivityNotFoundException	android.content.ActivityNotFoundException
    //   76	177	6	localObject2	Object
    //   57	16	7	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   133	177	206	java/lang/Exception
    //   178	205	206	java/lang/Exception
    //   208	257	258	android/content/ActivityNotFoundException
    //   260	304	490	java/lang/Exception
    //   305	332	490	java/lang/Exception
    //   333	377	492	java/lang/Exception
    //   378	405	492	java/lang/Exception
    //   406	450	494	java/lang/Exception
    //   451	478	494	java/lang/Exception
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    j = 0;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return;
    }
    gk.a("social goMarket type=" + paramString1);
    k = 1;
    localFq = fj.a().i(paramString1);
    i = k;
    String str2;
    String str1;
    if (localFq != null)
    {
      str2 = localFq.c;
      str1 = paramString2;
      if (!"facebook".equals(paramString1)) {
        break label275;
      }
      paramString1 = paramString2;
    }
    for (;;)
    {
      try
      {
        if (a.d().c().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850)
        {
          paramString1 = paramString2;
          paramString2 = "https://www.facebook.com/" + paramString2;
          paramString1 = paramString2;
          str1 = "fb://facewebmodal/f?href=" + paramString2;
          paramString1 = str1;
        }
      }
      catch (Exception paramString2)
      {
        gk.a(paramString2);
        str1 = paramString1;
      }
      try
      {
        if (TextUtils.isEmpty(localFq.c)) {
          break label357;
        }
        paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(b(paramString1, paramString2)));
        paramString1.addFlags(268435456);
        paramContext.startActivity(paramString1);
        i = 0;
      }
      catch (Exception paramString1)
      {
        for (;;)
        {
          try
          {
            if (TextUtils.isEmpty(localFq.d)) {
              break label351;
            }
            paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(b(localFq.d, paramString2)));
            paramString1.addFlags(268435456);
            paramContext.startActivity(paramString1);
            i = j;
          }
          catch (Exception paramString1)
          {
            gk.a("social goMarket error ", paramString1);
            i = k;
          }
          break;
          i = 1;
        }
        i = 1;
        continue;
      }
      if (i != 0) {
        break;
      }
      try
      {
        new Handler(Looper.getMainLooper()).postDelayed(new hn(paramContext, paramInt), 3000L);
        return;
      }
      catch (Exception paramContext)
      {
        gk.a("activeListener onReward error ", paramContext);
        return;
      }
      paramString1 = paramString2;
      str1 = "fb://page/" + paramString2;
      paramString1 = str1;
      continue;
      label275:
      paramString1 = str2;
      paramString2 = str1;
    }
  }
  
  public static void a(TaskActiveListener paramTaskActiveListener)
  {
    try
    {
      localObject1 = ge.a().c("taskAdDatas");
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        gk.a("offer json is null");
        return;
      }
      localObject1 = ha.b(new JSONArray((String)localObject1), b.class);
      if ((localObject1 == null) || (((List)localObject1).size() == 0))
      {
        gk.a("offer taskAdDatas is null");
        return;
      }
    }
    catch (Exception paramTaskActiveListener)
    {
      gk.a(paramTaskActiveListener);
      return;
    }
    Object localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      b localB = (b)((Iterator)localObject2).next();
      gk.a("offer taskAdData hasActive=" + localB.d + ",pkgname=" + localB.c.pkgname + ",type=" + localB.jdField_a_of_type_JavaLangString + ",taskTime=" + localB.b);
    }
    int i = -1;
    Object localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (b)((Iterator)localObject1).next();
      gk.a("offer taskAdData for start");
      if ((localObject2 == null) || (((b)localObject2).c == null))
      {
        gk.a("offer taskAdData continue");
      }
      else
      {
        a.d();
        if (a.u != null)
        {
          a.d();
          if (a.u.size() != 0) {}
        }
        else
        {
          gk.a("offer install packagename is null");
        }
        a.d();
        int j = i;
        if (a.u.contains(((b)localObject2).c.pkgname))
        {
          j = i;
          if (((b)localObject2).a())
          {
            j = i;
            if (!((b)localObject2).d)
            {
              j = i;
              if (a.g.equals(((b)localObject2).jdField_a_of_type_JavaLangString))
              {
                j = i + ((b)localObject2).c.coins * hf.n;
                dx.a().b(null, ((b)localObject2).jdField_a_of_type_JavaLangString, "install", ((b)localObject2).c);
                ((b)localObject2).d = true;
                fx.a().a((b)localObject2);
                gk.a("offer task active coins=" + j);
              }
            }
          }
        }
        gk.a("offer taskAdData for end");
        i = j;
      }
    }
    if (paramTaskActiveListener != null)
    {
      gk.a("offer send listener coins=" + i);
      paramTaskActiveListener.onReward(a.d().c(), i);
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, "packageName") == 0;
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = null;
      try
      {
        paramString = a.d().c().getPackageManager().getPackageInfo(paramString, 0);
        if (paramString == null) {
          continue;
        }
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        for (;;)
        {
          paramString = localObject;
        }
      }
    }
  }
  
  public static int b(Context paramContext)
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
  
  public static int b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return -1;
      Object localObject = a.d().c();
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getInt(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        gk.a("Get Meta_Data Info error:", paramString);
      }
    }
    return -1;
  }
  
  public static Intent b(Context paramContext, SelfAdData paramSelfAdData, String paramString)
  {
    if (paramSelfAdData == null) {}
    do
    {
      return null;
      if ("install".equals(paramSelfAdData.tasktype))
      {
        String str = paramSelfAdData.action;
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
            return a(paramSelfAdData, paramString);
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
        paramContext = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.weburl, paramSelfAdData, paramString)));
        paramContext.addFlags(268435456);
        return paramContext;
        paramContext = new Intent(paramContext, WebActivity.class);
        paramContext.addFlags(268435456);
        paramContext.putExtra("ad_url", Uri.parse(a(paramSelfAdData.weburl, paramSelfAdData, paramString)));
        return paramContext;
      }
    } while (!"follow".equals(paramSelfAdData.tasktype));
    return a(paramSelfAdData.feature, paramSelfAdData.socialid);
  }
  
  private static String b(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (!TextUtils.isEmpty(paramString1)) {}
    try
    {
      str = paramString1.replace("$ACCOUNT", paramString2);
      gk.a("social url=" + str);
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
  
  public static boolean b()
  {
    NetworkInfo localNetworkInfo;
    if (a.d().c().checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      localNetworkInfo = ((ConnectivityManager)a.d().c().getSystemService("connectivity")).getActiveNetworkInfo();
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
  
  public static String c()
  {
    int i = e();
    if (i == -1) {
      return "unknow";
    }
    if (i == 1) {
      return "wifi";
    }
    switch (((TelephonyManager)a.d().c().getSystemService("phone")).getNetworkType())
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
  
  public static String c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      Object localObject = a.d().c();
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
        gk.a("Get Meta_Data Info error:", paramString);
      }
    }
    return null;
  }
  
  public static void c(Context paramContext, SelfAdData paramSelfAdData, String paramString)
  {
    if ((paramContext == null) || (paramSelfAdData == null)) {
      return;
    }
    Object localObject = paramSelfAdData.action;
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
          localObject = new b();
          ((b)localObject).c = paramSelfAdData;
          ((b)localObject).jdField_a_of_type_JavaLangString = paramString;
          ((b)localObject).b = (System.currentTimeMillis() / 1000L);
          fx.a().a((b)localObject);
          a(paramContext, paramSelfAdData, paramString);
          return;
        }
        catch (Exception paramContext)
        {
          gk.a(paramContext);
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
      new Handler(Looper.getMainLooper()).postDelayed(new ho(paramSelfAdData, paramContext), 3000L);
      paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.weburl, paramSelfAdData, paramString)));
      paramSelfAdData.addFlags(268435456);
      paramContext.startActivity(paramSelfAdData);
      return;
    }
    catch (Exception paramContext)
    {
      gk.a(paramContext);
      return;
    }
    try
    {
      localObject = new Intent(paramContext, WebActivity.class);
      ((Intent)localObject).putExtra("ad_url", a(paramSelfAdData.weburl, paramSelfAdData, paramString));
      paramContext.startActivity((Intent)localObject);
      return;
    }
    catch (Exception paramContext)
    {
      gk.a(paramContext);
    }
  }
  
  public static boolean d()
  {
    return (a.d().c().getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = a.d().c();
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
        gk.a("Get Meta_Data Info error:", paramString);
      }
    }
    return false;
  }
  
  private static int e()
  {
    Object localObject = (ConnectivityManager)a.d().c().getSystemService("connectivity");
    if (localObject == null) {
      return -1;
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if (localObject == null) {
      return -1;
    }
    return ((NetworkInfo)localObject).getType();
  }
  
  public static boolean e(String paramString)
  {
    paramString = fj.a().i(paramString);
    return (paramString != null) && (a(paramString.b));
  }
}
