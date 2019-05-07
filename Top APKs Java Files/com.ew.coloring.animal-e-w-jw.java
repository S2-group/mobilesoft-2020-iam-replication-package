package e.w;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.ActivityNotFoundException;
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
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ew.sdk.activity.WebActivity;
import com.ew.sdk.model.SelfAdData;
import com.ew.sdk.model.SelfMarketInfo;
import com.ew.sdk.model.TaskAdData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class jw
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
      iw.a("Get Version Code Error!!!", localException);
    }
    return -1;
  }
  
  public static int a(Context paramContext)
  {
    AccountManager localAccountManager = AccountManager.get(paramContext);
    if (ActivityCompat.checkSelfPermission(paramContext, "android.permission.GET_ACCOUNTS") != 0) {
      return -1;
    }
    paramContext = localAccountManager.getAccounts();
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
        iw.a("Get Meta_Data Info error:", paramString);
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
      if ("install".equals(paramSelfAdData.q))
      {
        String str = paramSelfAdData.a;
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
        paramContext = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
        paramContext.addFlags(268435456);
        return paramContext;
        paramContext = new Intent(paramContext, WebActivity.class);
        paramContext.addFlags(268435456);
        paramContext.putExtra("ad_url", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
        return paramContext;
      }
    } while (!"follow".equals(paramSelfAdData.q));
    return a(paramSelfAdData.t, paramSelfAdData.r, paramSelfAdData.s);
  }
  
  public static Intent a(SelfAdData paramSelfAdData, String paramString)
  {
    Object localObject2 = null;
    Object localObject1;
    if (paramSelfAdData.y != null)
    {
      if (paramSelfAdData.y.a == null) {
        break label273;
      }
      localObject1 = hv.a().a(paramSelfAdData.y.a);
      if ((localObject1 == null) || (!a(((id)localObject1).b))) {
        break label273;
      }
    }
    for (;;)
    {
      int i;
      if ((localObject1 == null) && (paramSelfAdData.y.b != null))
      {
        String[] arrayOfString = paramSelfAdData.y.b;
        int j = arrayOfString.length;
        i = 0;
        if (i < j)
        {
          Object localObject3 = arrayOfString[i];
          localObject3 = hv.a().a((String)localObject3);
          if ((localObject3 != null) && (a(((id)localObject3).b))) {
            localObject1 = localObject3;
          }
        }
      }
      for (;;)
      {
        if (localObject1 != null)
        {
          paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(((id)localObject1).c, paramSelfAdData, paramString)));
          paramSelfAdData.setPackage(((id)localObject1).b);
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
          } while (TextUtils.isEmpty(paramSelfAdData.l));
          paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
          paramSelfAdData.addFlags(268435456);
          return paramSelfAdData;
          localObject1 = localObject2;
        } while (TextUtils.isEmpty(paramSelfAdData.l));
        paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
        paramSelfAdData.addFlags(268435456);
        return paramSelfAdData;
      }
      label273:
      localObject1 = null;
    }
  }
  
  public static Intent a(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = null;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return null;
    }
    iw.a("social goMarket type=" + paramString1);
    id localId = hv.a().a(paramString1);
    if (localId != null)
    {
      String str2 = localId.c;
      String str1 = str2;
      if ("facebook".equals(paramString1))
      {
        str1 = str2;
        if (!a("com.facebook.katana")) {}
      }
      for (;;)
      {
        try
        {
          if (a.a().a().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode >= 3002850)
          {
            paramString1 = "fb://facewebmodal/f?href=https://www.facebook.com/" + paramString2;
            str1 = paramString1;
          }
        }
        catch (Exception paramString1)
        {
          iw.a(paramString1);
          str1 = str2;
          continue;
        }
        try
        {
          if (TextUtils.isEmpty(str1)) {
            break label281;
          }
          paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(a(str1, paramString2)));
          try
          {
            paramString1.addFlags(268435456);
            return paramString1;
          }
          catch (Exception paramString3) {}
        }
        catch (Exception paramString1)
        {
          paramString1 = localObject;
          continue;
        }
        try
        {
          if (TextUtils.isEmpty(localId.d)) {
            break;
          }
          paramString3 = new Intent("android.intent.action.VIEW", Uri.parse(a(localId.d, paramString2)));
          try
          {
            paramString3.addFlags(268435456);
            return paramString3;
          }
          catch (Exception paramString2)
          {
            paramString1 = paramString3;
          }
        }
        catch (Exception paramString2)
        {
          continue;
        }
        iw.a("social goMarket error ", paramString2);
        return paramString1;
        if (TextUtils.isEmpty(paramString3)) {
          paramString1 = localId.d;
        } else {
          paramString1 = "fb://page/" + paramString3;
        }
      }
      return paramString1;
      label281:
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
        iw.a("Get Meta_Data Info error:", paramString);
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
        paramString1 = paramString1 + "&referrer=utm_source%3D" + a.a().a().getPackageName() + "%26utm_content%3D" + paramString2 + "%utm_campaign%3D" + iy.c();
      }
      catch (Exception paramString1)
      {
        paramString1 = str;
        continue;
      }
      iw.a("marketurl=" + paramString1);
      return paramString1;
      str = paramString1;
      paramString1 = paramString1.replace("$PKGNAME", paramSelfAdData.j);
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
      iw.a("social url=" + str);
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
      iw.a(paramContext);
    }
  }
  
  public static void a(Context paramContext, SelfAdData paramSelfAdData, int paramInt)
  {
    int i = 0;
    Object localObject3 = paramSelfAdData.t;
    String str2 = paramSelfAdData.r;
    String str3 = paramSelfAdData.s;
    int j = paramSelfAdData.z * 1000;
    if ((TextUtils.isEmpty((CharSequence)localObject3)) || (TextUtils.isEmpty(str2))) {
      return;
    }
    iw.a("social goMarket type=" + (String)localObject3);
    id localId = hv.a().a((String)localObject3);
    String str1;
    Object localObject1;
    if (localId != null)
    {
      str1 = localId.c;
      localObject1 = str1;
      if ("facebook".equals(localObject3))
      {
        localObject1 = str1;
        if (a("com.facebook.katana")) {
          localObject3 = str1;
        }
      }
    }
    for (;;)
    {
      try
      {
        int k = a.a().a().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
        localObject3 = str1;
        iw.a("facebook social versionCode = " + k);
        if (k >= 3002850)
        {
          localObject3 = str1;
          localObject1 = "fb://facewebmodal/f?href=https://www.facebook.com/" + str2;
          localObject3 = localObject1;
          iw.a("facebook social marketUrl = " + (String)localObject1);
        }
      }
      catch (Exception localException1)
      {
        try
        {
          if (!TextUtils.isEmpty(localId.c))
          {
            localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(a((String)localObject1, str2)));
            ((Intent)localObject1).addFlags(268435456);
            paramContext.startActivity((Intent)localObject1);
            if ((i != 0) || (paramSelfAdData.A)) {
              break;
            }
            paramSelfAdData.A = true;
            try
            {
              paramSelfAdData = new Handler(Looper.getMainLooper());
              paramContext = new jx(paramContext, paramInt);
              if (j <= 3000) {
                break label482;
              }
              l = j;
              paramSelfAdData.postDelayed(paramContext, l);
              return;
            }
            catch (Exception paramContext)
            {
              iw.a("activeListener onReward error ", paramContext);
              return;
            }
            localObject3 = str1;
            if (TextUtils.isEmpty(str3))
            {
              localObject3 = str1;
              localObject1 = localId.d;
              continue;
            }
            localObject3 = str1;
            localObject1 = "fb://page/" + str3;
            continue;
            localException1 = localException1;
            iw.a(localException1);
            Object localObject2 = localObject3;
            continue;
          }
        }
        catch (Exception localException2)
        {
          try
          {
            if (!TextUtils.isEmpty(localId.d))
            {
              Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(a(localId.d, str2)));
              localIntent.addFlags(268435456);
              paramContext.startActivity(localIntent);
            }
          }
          catch (Exception localException3)
          {
            iw.a("social goMarket error ", localException3);
          }
        }
      }
      i = 1;
      continue;
      label482:
      long l = 3000L;
    }
  }
  
  public static void a(Context paramContext, SelfAdData paramSelfAdData, String paramString)
  {
    Object localObject1;
    if (paramSelfAdData.y != null)
    {
      if (paramSelfAdData.y.a == null) {
        break label423;
      }
      localObject1 = hv.a().a(paramSelfAdData.y.a);
      if ((localObject1 == null) || (!a(((id)localObject1).b))) {
        break label423;
      }
    }
    for (;;)
    {
      int i;
      Object localObject3;
      if ((localObject1 == null) && (paramSelfAdData.y.b != null))
      {
        String[] arrayOfString = paramSelfAdData.y.b;
        int j = arrayOfString.length;
        i = 0;
        if (i < j)
        {
          localObject3 = arrayOfString[i];
          localObject3 = hv.a().a((String)localObject3);
          if ((localObject3 != null) && (a(((id)localObject3).b))) {
            localObject1 = localObject3;
          }
        }
      }
      for (;;)
      {
        if ((localObject1 == null) || (("googleplay".equals(((id)localObject1).a)) && (a(paramContext) == 0))) {}
        try
        {
          if (!TextUtils.isEmpty(paramSelfAdData.l))
          {
            paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
            paramSelfAdData.addFlags(268435456);
            paramContext.startActivity(paramSelfAdData);
          }
          for (;;)
          {
            return;
            i += 1;
            break;
            try
            {
              localObject3 = new Intent("android.intent.action.VIEW", Uri.parse(a(((id)localObject1).c, paramSelfAdData, paramString)));
              ((Intent)localObject3).setPackage(((id)localObject1).b);
              ((Intent)localObject3).addFlags(268435456);
              paramContext.startActivity((Intent)localObject3);
              return;
            }
            catch (ActivityNotFoundException localActivityNotFoundException)
            {
              try
              {
                if (TextUtils.isEmpty(paramSelfAdData.l)) {
                  continue;
                }
                paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
                paramSelfAdData.addFlags(268435456);
                paramContext.startActivity(paramSelfAdData);
                return;
              }
              catch (Exception paramContext)
              {
                return;
              }
            }
            try
            {
              if (!TextUtils.isEmpty(paramSelfAdData.l))
              {
                paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
                paramSelfAdData.addFlags(268435456);
                paramContext.startActivity(paramSelfAdData);
                return;
              }
            }
            catch (Exception paramContext)
            {
              return;
            }
          }
        }
        catch (Exception paramContext)
        {
          try
          {
            do
            {
              if (!TextUtils.isEmpty(paramSelfAdData.l))
              {
                paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
                paramSelfAdData.addFlags(268435456);
                paramContext.startActivity(paramSelfAdData);
                return;
              }
            } while (TextUtils.isEmpty(paramSelfAdData.b));
            iz.a(paramContext, paramSelfAdData.f(), paramSelfAdData.j, paramSelfAdData.g());
            return;
          }
          catch (Exception paramContext) {}
          paramContext = paramContext;
          return;
        }
      }
      label423:
      Object localObject2 = null;
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
    Object localObject = paramSelfAdData.a;
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
          localObject = new TaskAdData();
          ((TaskAdData)localObject).c = paramSelfAdData;
          ((TaskAdData)localObject).a = paramString;
          ((TaskAdData)localObject).b = (System.currentTimeMillis() / 1000L);
          hi.a().a((TaskAdData)localObject);
          a(paramContext, paramSelfAdData, paramString);
          return;
        }
        catch (Exception paramContext)
        {
          iw.a(paramContext);
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
      new Handler(Looper.getMainLooper()).postDelayed(new jy(paramSelfAdData, paramContext), 3000L);
      paramSelfAdData = new Intent("android.intent.action.VIEW", Uri.parse(a(paramSelfAdData.l, paramSelfAdData, paramString)));
      paramSelfAdData.addFlags(268435456);
      paramContext.startActivity(paramSelfAdData);
      return;
    }
    catch (Exception paramContext)
    {
      iw.a(paramContext);
      return;
    }
    try
    {
      localObject = new Intent(paramContext, WebActivity.class);
      ((Intent)localObject).putExtra("ad_url", a(paramSelfAdData.l, paramSelfAdData, paramString));
      paramContext.startActivity((Intent)localObject);
      return;
    }
    catch (Exception paramContext)
    {
      iw.a(paramContext);
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
        iw.a("Get Meta_Data Info error:", paramString);
      }
    }
    return false;
  }
  
  public static boolean c(String paramString)
  {
    paramString = hv.a().a(paramString);
    return (paramString != null) && (a(paramString.b));
  }
}
