package m.f;

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
import com.metafun.fun.TaskActiveListener;
import com.metafun.fun.activity.WebActivity;
import com.metafun.fun.model.SelfAdData;
import com.metafun.fun.model.SelfMarketInfo;
import com.metafun.fun.model.TaskAdData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class il
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
      hk.a("Get Version Code Error!!!", localException);
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
  
  public static int a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return -1;
      Object localObject = a.a().a();
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
        hk.a("Get Meta_Data Info error:", paramString);
      }
    }
    return -1;
  }
  
  public static Intent a(Context paramContext, SelfAdData paramSelfAdData, String paramString)
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
  
  public static Intent a(SelfAdData paramSelfAdData, String paramString)
  {
    Object localObject2 = null;
    if (paramSelfAdData.market != null) {
      if (paramSelfAdData.market.jdField_a_of_type_JavaLangString == null) {
        break label257;
      }
    }
    label257:
    for (Object localObject1 = gj.a().a(paramSelfAdData.market.jdField_a_of_type_JavaLangString);; localObject1 = null)
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
          localObject3 = gj.a().a((String)localObject3);
          if (localObject3 != null) {
            localObject1 = localObject3;
          }
        }
      }
      for (;;)
      {
        if ((localObject1 != null) && (a(((gq)localObject1).b)))
        {
          paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(((gq)localObject1).c, paramSelfAdData, paramString)));
          paramSelfAdData.setPackage(((gq)localObject1).b);
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
    hk.a("social goMarket type=" + paramString1);
    gq localGq = gj.a().a(paramString1);
    if (localGq != null)
    {
      String str2 = localGq.c;
      String str1 = str2;
      Object localObject1 = paramString2;
      if ("facebook".equals(paramString1)) {
        localObject1 = paramString2;
      }
      for (;;)
      {
        try
        {
          if (a.a().a().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850)
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
          hk.a(paramString1);
          str1 = str2;
          continue;
        }
        try
        {
          if (TextUtils.isEmpty(str1)) {
            break label286;
          }
          paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(a(str1, (String)localObject1)));
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
          if (TextUtils.isEmpty(localGq.d)) {
            break;
          }
          localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(a(localGq.d, (String)localObject1)));
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
        hk.a("social goMarket error ", paramString2);
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
        hk.a("Get Meta_Data Info error:", paramString);
      }
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
        paramString1 = paramString1 + "&referrer=utm_source%3D" + a.a().a().getPackageName() + "%26utm_content%3D" + paramString2 + "%utm_campaign%3D" + hm.c();
      }
      catch (Exception paramString1)
      {
        paramString1 = str;
        continue;
      }
      hk.a("marketurl=" + paramString1);
      return paramString1;
      str = paramString1;
      paramString1 = paramString1.replace("$PKGNAME", paramSelfAdData.pkgname);
      str = paramString1;
      paramString1 = paramString1.replace("$HostPKGNAME", a.a().a().getPackageName());
      str = paramString1;
      paramString1 = paramString1.replace("$AdType", paramString2);
    }
  }
  
  private static String a(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (!TextUtils.isEmpty(paramString1)) {}
    try
    {
      str = paramString1.replace("$ACCOUNT", paramString2);
      hk.a("social url=" + str);
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
      hk.a(paramContext);
    }
  }
  
  /* Error */
  public static void a(Context paramContext, SelfAdData paramSelfAdData, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_1
    //   4: getfield 172	com/metafun/fun/model/SelfAdData:market	Lcom/metafun/fun/model/SelfMarketInfo;
    //   7: ifnull +400 -> 407
    //   10: aload_1
    //   11: getfield 172	com/metafun/fun/model/SelfAdData:market	Lcom/metafun/fun/model/SelfMarketInfo;
    //   14: getfield 176	com/metafun/fun/model/SelfMarketInfo:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   17: ifnull +18 -> 35
    //   20: invokestatic 181	m/f/gj:a	()Lm/f/gj;
    //   23: aload_1
    //   24: getfield 172	com/metafun/fun/model/SelfAdData:market	Lcom/metafun/fun/model/SelfMarketInfo;
    //   27: getfield 176	com/metafun/fun/model/SelfMarketInfo:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   30: invokevirtual 184	m/f/gj:a	(Ljava/lang/String;)Lm/f/gq;
    //   33: astore 5
    //   35: aload 5
    //   37: ifnonnull +443 -> 480
    //   40: aload_1
    //   41: getfield 172	com/metafun/fun/model/SelfAdData:market	Lcom/metafun/fun/model/SelfMarketInfo;
    //   44: getfield 187	com/metafun/fun/model/SelfMarketInfo:jdField_a_of_type_ArrayOfJavaLangString	[Ljava/lang/String;
    //   47: ifnull +433 -> 480
    //   50: aload_1
    //   51: getfield 172	com/metafun/fun/model/SelfAdData:market	Lcom/metafun/fun/model/SelfMarketInfo;
    //   54: getfield 187	com/metafun/fun/model/SelfMarketInfo:jdField_a_of_type_ArrayOfJavaLangString	[Ljava/lang/String;
    //   57: astore 7
    //   59: aload 7
    //   61: arraylength
    //   62: istore 4
    //   64: iconst_0
    //   65: istore_3
    //   66: iload_3
    //   67: iload 4
    //   69: if_icmpge +411 -> 480
    //   72: aload 7
    //   74: iload_3
    //   75: aaload
    //   76: astore 6
    //   78: invokestatic 181	m/f/gj:a	()Lm/f/gj;
    //   81: aload 6
    //   83: invokevirtual 184	m/f/gj:a	(Ljava/lang/String;)Lm/f/gq;
    //   86: astore 6
    //   88: aload 6
    //   90: ifnull +394 -> 484
    //   93: aload 6
    //   95: astore 5
    //   97: aload 5
    //   99: ifnull +235 -> 334
    //   102: aload 5
    //   104: getfield 192	m/f/gq:b	Ljava/lang/String;
    //   107: invokestatic 195	m/f/il:a	(Ljava/lang/String;)Z
    //   110: ifeq +224 -> 334
    //   113: ldc_w 337
    //   116: aload 5
    //   118: getfield 338	m/f/gq:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   121: invokevirtual 107	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   124: ifeq +85 -> 209
    //   127: aload_0
    //   128: invokestatic 340	m/f/il:a	(Landroid/content/Context;)I
    //   131: ifne +78 -> 209
    //   134: aload_1
    //   135: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   138: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   141: ifne +38 -> 179
    //   144: new 124	android/content/Intent
    //   147: dup
    //   148: ldc 126
    //   150: aload_1
    //   151: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   154: aload_1
    //   155: aload_2
    //   156: invokestatic 132	m/f/il:a	(Ljava/lang/String;Lcom/metafun/fun/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   159: invokestatic 138	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   162: invokespecial 142	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   165: astore_1
    //   166: aload_1
    //   167: ldc -113
    //   169: invokevirtual 147	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   172: pop
    //   173: aload_0
    //   174: aload_1
    //   175: invokevirtual 344	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   178: return
    //   179: aload_1
    //   180: getfield 347	com/metafun/fun/model/SelfAdData:uri	Ljava/lang/String;
    //   183: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   186: ifne +297 -> 483
    //   189: aload_0
    //   190: aload_1
    //   191: invokevirtual 350	com/metafun/fun/model/SelfAdData:getUri	()Ljava/lang/String;
    //   194: aload_1
    //   195: getfield 290	com/metafun/fun/model/SelfAdData:pkgname	Ljava/lang/String;
    //   198: aload_1
    //   199: invokevirtual 353	com/metafun/fun/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   202: invokestatic 358	m/f/hn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   205: pop
    //   206: return
    //   207: astore_0
    //   208: return
    //   209: new 124	android/content/Intent
    //   212: dup
    //   213: ldc 126
    //   215: aload 5
    //   217: getfield 198	m/f/gq:c	Ljava/lang/String;
    //   220: aload_1
    //   221: aload_2
    //   222: invokestatic 132	m/f/il:a	(Ljava/lang/String;Lcom/metafun/fun/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   225: invokestatic 138	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   228: invokespecial 142	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   231: astore 6
    //   233: aload 6
    //   235: aload 5
    //   237: getfield 192	m/f/gq:b	Ljava/lang/String;
    //   240: invokevirtual 202	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   243: pop
    //   244: aload 6
    //   246: ldc -113
    //   248: invokevirtual 147	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   251: pop
    //   252: aload_0
    //   253: aload 6
    //   255: invokevirtual 344	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   258: return
    //   259: astore 5
    //   261: aload_1
    //   262: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   265: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   268: ifne +38 -> 306
    //   271: new 124	android/content/Intent
    //   274: dup
    //   275: ldc 126
    //   277: aload_1
    //   278: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   281: aload_1
    //   282: aload_2
    //   283: invokestatic 132	m/f/il:a	(Ljava/lang/String;Lcom/metafun/fun/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   286: invokestatic 138	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   289: invokespecial 142	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   292: astore_1
    //   293: aload_1
    //   294: ldc -113
    //   296: invokevirtual 147	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   299: pop
    //   300: aload_0
    //   301: aload_1
    //   302: invokevirtual 344	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   305: return
    //   306: aload_1
    //   307: getfield 347	com/metafun/fun/model/SelfAdData:uri	Ljava/lang/String;
    //   310: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   313: ifne +170 -> 483
    //   316: aload_0
    //   317: aload_1
    //   318: invokevirtual 350	com/metafun/fun/model/SelfAdData:getUri	()Ljava/lang/String;
    //   321: aload_1
    //   322: getfield 290	com/metafun/fun/model/SelfAdData:pkgname	Ljava/lang/String;
    //   325: aload_1
    //   326: invokevirtual 353	com/metafun/fun/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   329: invokestatic 358	m/f/hn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   332: pop
    //   333: return
    //   334: aload_1
    //   335: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   338: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   341: ifne +38 -> 379
    //   344: new 124	android/content/Intent
    //   347: dup
    //   348: ldc 126
    //   350: aload_1
    //   351: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   354: aload_1
    //   355: aload_2
    //   356: invokestatic 132	m/f/il:a	(Ljava/lang/String;Lcom/metafun/fun/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   359: invokestatic 138	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   362: invokespecial 142	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   365: astore_1
    //   366: aload_1
    //   367: ldc -113
    //   369: invokevirtual 147	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   372: pop
    //   373: aload_0
    //   374: aload_1
    //   375: invokevirtual 344	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   378: return
    //   379: aload_1
    //   380: getfield 347	com/metafun/fun/model/SelfAdData:uri	Ljava/lang/String;
    //   383: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   386: ifne +97 -> 483
    //   389: aload_0
    //   390: aload_1
    //   391: invokevirtual 350	com/metafun/fun/model/SelfAdData:getUri	()Ljava/lang/String;
    //   394: aload_1
    //   395: getfield 290	com/metafun/fun/model/SelfAdData:pkgname	Ljava/lang/String;
    //   398: aload_1
    //   399: invokevirtual 353	com/metafun/fun/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   402: invokestatic 358	m/f/hn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   405: pop
    //   406: return
    //   407: aload_1
    //   408: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   411: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   414: ifne +38 -> 452
    //   417: new 124	android/content/Intent
    //   420: dup
    //   421: ldc 126
    //   423: aload_1
    //   424: getfield 129	com/metafun/fun/model/SelfAdData:weburl	Ljava/lang/String;
    //   427: aload_1
    //   428: aload_2
    //   429: invokestatic 132	m/f/il:a	(Ljava/lang/String;Lcom/metafun/fun/model/SelfAdData;Ljava/lang/String;)Ljava/lang/String;
    //   432: invokestatic 138	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   435: invokespecial 142	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   438: astore_1
    //   439: aload_1
    //   440: ldc -113
    //   442: invokevirtual 147	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   445: pop
    //   446: aload_0
    //   447: aload_1
    //   448: invokevirtual 344	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   451: return
    //   452: aload_1
    //   453: getfield 347	com/metafun/fun/model/SelfAdData:uri	Ljava/lang/String;
    //   456: invokestatic 71	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   459: ifne +24 -> 483
    //   462: aload_0
    //   463: aload_1
    //   464: invokevirtual 350	com/metafun/fun/model/SelfAdData:getUri	()Ljava/lang/String;
    //   467: aload_1
    //   468: getfield 290	com/metafun/fun/model/SelfAdData:pkgname	Ljava/lang/String;
    //   471: aload_1
    //   472: invokevirtual 353	com/metafun/fun/model/SelfAdData:getTitle	()Ljava/lang/String;
    //   475: invokestatic 358	m/f/hn:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   478: pop
    //   479: return
    //   480: goto -383 -> 97
    //   483: return
    //   484: iload_3
    //   485: iconst_1
    //   486: iadd
    //   487: istore_3
    //   488: goto -422 -> 66
    //   491: astore_0
    //   492: return
    //   493: astore_0
    //   494: return
    //   495: astore_0
    //   496: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	497	0	paramContext	Context
    //   0	497	1	paramSelfAdData	SelfAdData
    //   0	497	2	paramString	String
    //   65	423	3	i	int
    //   62	8	4	j	int
    //   1	235	5	localObject1	Object
    //   259	1	5	localActivityNotFoundException	android.content.ActivityNotFoundException
    //   76	178	6	localObject2	Object
    //   57	16	7	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   134	178	207	java/lang/Exception
    //   179	206	207	java/lang/Exception
    //   209	258	259	android/content/ActivityNotFoundException
    //   261	305	491	java/lang/Exception
    //   306	333	491	java/lang/Exception
    //   334	378	493	java/lang/Exception
    //   379	406	493	java/lang/Exception
    //   407	451	495	java/lang/Exception
    //   452	479	495	java/lang/Exception
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    j = 0;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return;
    }
    hk.a("social goMarket type=" + paramString1);
    k = 1;
    localGq = gj.a().a(paramString1);
    i = k;
    String str2;
    String str1;
    if (localGq != null)
    {
      str2 = localGq.c;
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
        if (a.a().a().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850)
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
        hk.a(paramString2);
        str1 = paramString1;
      }
      try
      {
        if (TextUtils.isEmpty(localGq.c)) {
          break label357;
        }
        paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(a(paramString1, paramString2)));
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
            if (TextUtils.isEmpty(localGq.d)) {
              break label351;
            }
            paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(a(localGq.d, paramString2)));
            paramString1.addFlags(268435456);
            paramContext.startActivity(paramString1);
            i = j;
          }
          catch (Exception paramString1)
          {
            hk.a("social goMarket error ", paramString1);
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
        new Handler(Looper.getMainLooper()).postDelayed(new im(paramContext, paramInt), 3000L);
        return;
      }
      catch (Exception paramContext)
      {
        hk.a("activeListener onReward error ", paramContext);
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
    for (;;)
    {
      int j;
      try
      {
        Object localObject = h.a().a("taskAdDatas");
        if (TextUtils.isEmpty((CharSequence)localObject)) {
          return;
        }
        localObject = ia.a(new JSONArray((String)localObject), TaskAdData.class);
        if ((localObject != null) && (((List)localObject).size() != 0))
        {
          i = 0;
          localObject = ((List)localObject).iterator();
          if (((Iterator)localObject).hasNext())
          {
            TaskAdData localTaskAdData = (TaskAdData)((Iterator)localObject).next();
            if ((localTaskAdData == null) || (localTaskAdData.adData == null)) {
              continue;
            }
            a.a();
            j = i;
            if (!a.a.contains(localTaskAdData.adData.pkgname)) {
              break label224;
            }
            j = i;
            if (!localTaskAdData.hasTaskTime()) {
              break label224;
            }
            j = i;
            if (localTaskAdData.hasActive) {
              break label224;
            }
            j = i;
            if (!a.g.equals(localTaskAdData.type)) {
              break label224;
            }
            j = i + localTaskAdData.adData.coins * i.c;
            ey.a().b(null, localTaskAdData.type, "install", localTaskAdData.adData);
            localTaskAdData.hasActive = true;
            gy.a().a(localTaskAdData);
            break label224;
          }
          if (paramTaskActiveListener != null)
          {
            paramTaskActiveListener.onReward(a.a().a(), i);
            return;
          }
        }
      }
      catch (Exception paramTaskActiveListener)
      {
        hk.a(paramTaskActiveListener);
      }
      return;
      label224:
      int i = j;
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
        paramString = a.a().a().getPackageManager().getPackageInfo(paramString, 0);
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
  
  public static void b(Context paramContext, SelfAdData paramSelfAdData, String paramString)
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
          a(paramContext, paramSelfAdData, paramString);
          paramContext = new TaskAdData();
          paramContext.adData = paramSelfAdData;
          paramContext.type = paramString;
          paramContext.taskTime = (System.currentTimeMillis() / 1000L);
          gy.a().a(paramContext);
          return;
        }
        catch (Exception paramContext)
        {
          hk.a(paramContext);
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
      new Handler(Looper.getMainLooper()).postDelayed(new in(paramSelfAdData, paramContext), 3000L);
      paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.weburl, paramSelfAdData, paramString)));
      paramSelfAdData.addFlags(268435456);
      paramContext.startActivity(paramSelfAdData);
      return;
    }
    catch (Exception paramContext)
    {
      hk.a(paramContext);
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
      hk.a(paramContext);
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
        hk.a("Get Meta_Data Info error:", paramString);
      }
    }
    return false;
  }
  
  public static boolean c(String paramString)
  {
    paramString = gj.a().a(paramString);
    return (paramString != null) && (a(paramString.b));
  }
}
