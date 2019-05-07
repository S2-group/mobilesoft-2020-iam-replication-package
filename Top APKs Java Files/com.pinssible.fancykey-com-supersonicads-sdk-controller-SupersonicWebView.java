package com.supersonicads.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.supersonicads.sdk.b.a.b;
import com.supersonicads.sdk.c.e;
import com.supersonicads.sdk.c.f;
import com.supersonicads.sdk.c.g;
import com.supersonicads.sdk.data.AdUnitsState;
import com.supersonicads.sdk.data.SSAEnums.ControllerState;
import com.supersonicads.sdk.data.SSAEnums.DebugMode;
import com.supersonicads.sdk.data.SSAEnums.ProductType;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SupersonicWebView
  extends WebView
  implements DownloadListener, a.b
{
  public static int a = 0;
  private static String ak = "success";
  private static String al = "fail";
  public static String b = "is_store";
  public static String c = "is_store_close";
  public static String d = "webview_type";
  public static String e = "external_url";
  public static String f = "secondary_web_view";
  public static int g = 0;
  public static int h = 1;
  public static String i = "appIds";
  public static String j = "requestId";
  public static String k = "isInstalled";
  public static String l = "result";
  private String A;
  private com.supersonicads.sdk.b.a B;
  private boolean C;
  private boolean D;
  private boolean E;
  private boolean F;
  private boolean G;
  private boolean H;
  private String I;
  private String J = "interrupt";
  private CountDownTimer K;
  private CountDownTimer L;
  private CountDownTimer M;
  private int N = 50;
  private int O = 50;
  private String P = "top-right";
  private SupersonicWebView.a Q;
  private View R;
  private FrameLayout S;
  private WebChromeClient.CustomViewCallback T;
  private FrameLayout U;
  private State V;
  private String W;
  private com.supersonicads.sdk.a.d aa;
  private com.supersonicads.sdk.a.a ab;
  private com.supersonicads.sdk.a.b ac;
  private com.supersonicads.sdk.a.c ad;
  private SSAEnums.ControllerState ae = SSAEnums.ControllerState.None;
  private Boolean af = null;
  private String ag;
  private a ah;
  private AdUnitsState ai = new AdUnitsState();
  private Object aj = new Object();
  private BroadcastReceiver am = new SupersonicWebView.7(this);
  private d an;
  private String m = SupersonicWebView.class.getSimpleName();
  private String n = "Supersonic";
  private final String o = "We're sorry, some error occurred. we will investigate it";
  private String p;
  private String q;
  private Map<String, String> r;
  private String s;
  private String t;
  private Map<String, String> u;
  private Boolean v = null;
  private String w;
  private String x;
  private Map<String, String> y;
  private String z;
  
  public SupersonicWebView(Context paramContext)
  {
    super(paramContext);
    com.supersonicads.sdk.c.c.a(this.m, "C'tor");
    c(paramContext);
    this.ag = g.a(paramContext);
    this.B = com.supersonicads.sdk.b.a.a(this.ag);
    this.B.a(this);
    this.Q = new SupersonicWebView.a(this, null);
    setWebViewClient(new SupersonicWebView.f(this, null));
    setWebChromeClient(this.Q);
    o();
    addJavascriptInterface(new c(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new SupersonicWebView.e(this, null));
  }
  
  private String a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("?parameters=").append(paramString2).append("','").append(paramString3).append("','").append(paramString4).append("');");
    return localStringBuilder.toString();
  }
  
  private String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2))) {
        localJSONObject.put(paramString1, com.supersonicads.sdk.c.d.b(paramString2));
      }
      if ((!TextUtils.isEmpty(paramString3)) && (!TextUtils.isEmpty(paramString4))) {
        localJSONObject.put(paramString3, com.supersonicads.sdk.c.d.b(paramString4));
      }
      if ((!TextUtils.isEmpty(paramString5)) && (!TextUtils.isEmpty(paramString6))) {
        localJSONObject.put(paramString5, com.supersonicads.sdk.c.d.b(paramString6));
      }
      if ((!TextUtils.isEmpty(paramString7)) && (!TextUtils.isEmpty(paramString8))) {
        localJSONObject.put(paramString7, com.supersonicads.sdk.c.d.b(paramString8));
      }
      if (!TextUtils.isEmpty(paramString9)) {
        localJSONObject.put(paramString9, paramBoolean);
      }
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
        new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramString1.getStackTrace()[0].getMethodName() });
      }
    }
    return localJSONObject.toString();
  }
  
  private void a(SSAEnums.ProductType paramProductType)
  {
    String str = null;
    if (paramProductType == SSAEnums.ProductType.BrandConnect) {
      str = b("initBrandConnect", "onInitBrandConnectSuccess", "onInitBrandConnectFail");
    }
    for (;;)
    {
      if (str != null) {
        e(str);
      }
      return;
      if (paramProductType == SSAEnums.ProductType.Interstitial)
      {
        paramProductType = new HashMap();
        paramProductType.put("applicationKey", this.w);
        paramProductType.put("applicationUserId", this.x);
        if (this.y != null) {
          paramProductType.putAll(this.y);
        }
        str = a("initInterstitial", b(paramProductType), "onInitInterstitialSuccess", "onInitInterstitialFail");
      }
      else if (paramProductType == SSAEnums.ProductType.OfferWall)
      {
        paramProductType = new HashMap();
        paramProductType.put("applicationKey", this.s);
        paramProductType.put("applicationUserId", this.t);
        if (this.u != null) {
          paramProductType.putAll(this.u);
        }
        str = a("initOfferWall", b(paramProductType), "onInitOfferWallSuccess", "onInitOfferWallFail");
      }
      else if (paramProductType == SSAEnums.ProductType.OfferWallCredits)
      {
        str = a("getUserCredits", a("productType", "OfferWall", "applicationKey", this.z, "applicationUserId", this.A, null, null, null, false), "null", "onGetUserCreditsFail");
      }
    }
  }
  
  private void a(String paramString, SSAEnums.ProductType paramProductType)
  {
    if (d(paramProductType.toString()))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.4(this, paramProductType, paramString));
      }
    }
  }
  
  private void a(String paramString1, String paramString2, SSAEnums.ProductType paramProductType, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1))) {
      a("User id or Application key are missing", paramProductType);
    }
    do
    {
      return;
      if (this.ae == SSAEnums.ControllerState.Ready)
      {
        f.a().a(paramString1, paramProductType);
        f.a().b(paramString2, paramProductType);
        a(paramProductType);
        return;
      }
      setMissProduct(paramProductType);
      if (this.ae == SSAEnums.ControllerState.Failed)
      {
        a(com.supersonicads.sdk.c.d.a(paramString3, "Initiating Controller"), paramProductType);
        return;
      }
    } while (!this.G);
    a();
  }
  
  private void a(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    Object localObject2 = new com.supersonicads.sdk.data.d(paramString1);
    Object localObject1 = ((com.supersonicads.sdk.data.d)localObject2).i(ak);
    localObject2 = ((com.supersonicads.sdk.data.d)localObject2).i(al);
    if (paramBoolean) {
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        break label153;
      }
    }
    for (;;)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject2 = paramString1;
        if (TextUtils.isEmpty(paramString2)) {}
      }
      try
      {
        localObject2 = new JSONObject(paramString1).put("errMsg", paramString2).toString();
        paramString1 = (String)localObject2;
        if (!TextUtils.isEmpty(paramString3)) {}
        try
        {
          paramString1 = new JSONObject((String)localObject2).put("errCode", paramString3).toString();
          e(d((String)localObject1, paramString1));
          return;
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            localObject1 = localObject2;
          }
        }
        catch (JSONException paramString1)
        {
          for (;;)
          {
            paramString1 = (String)localObject2;
          }
        }
      }
      catch (JSONException paramString2)
      {
        for (;;)
        {
          localObject2 = paramString1;
        }
      }
      label153:
      localObject1 = null;
    }
  }
  
  private String b(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("','").append(paramString2).append("','").append(paramString3).append("');");
    return localStringBuilder.toString();
  }
  
  private String b(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      for (;;)
      {
        if (paramMap.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramMap.next();
          try
          {
            localJSONObject.putOpt((String)localEntry.getKey(), com.supersonicads.sdk.c.d.b((String)localEntry.getValue()));
            paramMap.remove();
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              com.supersonicads.sdk.c.c.a(this.m, "flatMapToJsonAsStringfailed " + localJSONException.toString());
            }
          }
        }
      }
    }
    return localJSONObject.toString();
  }
  
  private void b(SSAEnums.ProductType paramProductType)
  {
    String str = "";
    switch (SupersonicWebView.8.a[paramProductType.ordinal()])
    {
    }
    for (;;)
    {
      a(com.supersonicads.sdk.c.d.a(str, "Initiating Controller"), paramProductType);
      return;
      str = "Init BC";
      continue;
      str = "Init IS";
      continue;
      str = "Init OW";
      continue;
      str = "Show OW Credits";
    }
  }
  
  private Object[] b(String paramString1, String paramString2)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      if ((TextUtils.isEmpty(paramString1)) || (paramString1.equalsIgnoreCase("null"))) {
        break label265;
      }
      if ((TextUtils.isEmpty(paramString2)) || (paramString2.equalsIgnoreCase("null"))) {
        break label247;
      }
      localList = getBaseContext().getPackageManager().getInstalledApplications(0);
      paramString1 = new JSONArray(paramString1);
      localJSONObject2 = new JSONObject();
      i1 = 0;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        List localList;
        JSONObject localJSONObject2;
        int i1;
        String str;
        JSONObject localJSONObject3;
        Iterator localIterator;
        label247:
        label265:
        boolean bool = true;
        continue;
        int i2 = 0;
        continue;
        i1 += 1;
      }
    }
    if (i1 < paramString1.length())
    {
      str = paramString1.getString(i1).trim();
      if (!TextUtils.isEmpty(str))
      {
        localJSONObject3 = new JSONObject();
        localIterator = localList.iterator();
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
        } while (!str.equalsIgnoreCase(((ApplicationInfo)localIterator.next()).packageName));
        localJSONObject3.put(k, true);
        localJSONObject2.put(str, localJSONObject3);
        i2 = 1;
        if (i2 == 0)
        {
          localJSONObject3.put(k, false);
          localJSONObject2.put(str, localJSONObject3);
        }
      }
    }
    else
    {
      localJSONObject1.put(l, localJSONObject2);
      localJSONObject1.put(j, paramString2);
      bool = false;
      for (;;)
      {
        return new Object[] { localJSONObject1.toString(), Boolean.valueOf(bool) };
        localJSONObject1.put("error", "requestId is null or empty");
        bool = true;
        continue;
        localJSONObject1.put("error", "appIds is null or empty");
        bool = true;
      }
    }
  }
  
  private void c(Context paramContext)
  {
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, -1);
    this.U = new FrameLayout(paramContext);
    this.S = new FrameLayout(paramContext);
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-1, -1);
    this.S.setLayoutParams(localLayoutParams2);
    this.S.setVisibility(8);
    paramContext = new FrameLayout(paramContext);
    paramContext.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    paramContext.addView(this);
    this.U.addView(this.S, localLayoutParams1);
    this.U.addView(paramContext);
  }
  
  private void c(String paramString1, String paramString2)
  {
    paramString2 = new com.supersonicads.sdk.data.d(paramString2).i("errMsg");
    if (!TextUtils.isEmpty(paramString2))
    {
      Context localContext = getBaseContext();
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.6(this, paramString1, paramString2));
      }
    }
  }
  
  private String d(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("?parameters=").append(paramString2).append("');");
    return localStringBuilder.toString();
  }
  
  private boolean d(String paramString)
  {
    boolean bool4 = true;
    boolean bool2 = true;
    boolean bool1 = true;
    boolean bool5 = false;
    boolean bool3 = false;
    if (paramString == null)
    {
      com.supersonicads.sdk.c.c.c(this.m, "Trying to trigger a listener - no product was found");
      bool2 = bool3;
      return bool2;
    }
    if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
      if (this.ac == null) {}
    }
    do
    {
      for (;;)
      {
        bool2 = bool1;
        if (bool1) {
          break;
        }
        com.supersonicads.sdk.c.c.c(this.m, "Trying to trigger a listener - no listener was found for product " + paramString);
        return bool1;
        bool1 = false;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString()))
      {
        if (this.aa != null) {}
        for (bool1 = bool4;; bool1 = false) {
          break;
        }
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        break label152;
      }
      bool1 = bool5;
    } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString()));
    label152:
    if (this.ad != null) {}
    for (bool1 = bool2;; bool1 = false) {
      break;
    }
  }
  
  private Object[] d(Context paramContext)
  {
    Object localObject = com.supersonicads.sdk.c.a.a(paramContext);
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", "none");
        str = ((com.supersonicads.sdk.c.a)localObject).a();
        if (str != null) {
          localJSONObject.put(com.supersonicads.sdk.c.d.b("deviceOEM"), com.supersonicads.sdk.c.d.b(str));
        }
        str = ((com.supersonicads.sdk.c.a)localObject).b();
        if (str != null)
        {
          localJSONObject.put(com.supersonicads.sdk.c.d.b("deviceModel"), com.supersonicads.sdk.c.d.b(str));
          bool2 = false;
          bool1 = bool2;
        }
      }
      catch (JSONException paramContext)
      {
        String str;
        boolean bool3;
        bool1 = false;
      }
      try
      {
        com.supersonicads.sdk.c.d.h(paramContext);
        bool1 = bool2;
        str = com.supersonicads.sdk.c.d.e();
        bool1 = bool2;
        bool3 = com.supersonicads.sdk.c.d.f();
        bool1 = bool2;
        if (!TextUtils.isEmpty(str))
        {
          bool1 = bool2;
          com.supersonicads.sdk.c.c.a(this.m, "add AID and LAT");
          bool1 = bool2;
          localJSONObject.put("isLimitAdTrackingEnabled", Boolean.valueOf(bool3));
          bool1 = bool2;
          localJSONObject.put("deviceIds" + "[" + "AID" + "]", com.supersonicads.sdk.c.d.b(str));
        }
        bool1 = bool2;
        str = ((com.supersonicads.sdk.c.a)localObject).c();
        if (str != null)
        {
          bool1 = bool2;
          localJSONObject.put(com.supersonicads.sdk.c.d.b("deviceOs"), com.supersonicads.sdk.c.d.b(str));
          bool1 = bool2;
          str = Integer.toString(((com.supersonicads.sdk.c.a)localObject).d());
          if (str == null) {
            continue;
          }
          bool1 = bool2;
          localJSONObject.put(com.supersonicads.sdk.c.d.b("deviceOSVersion"), str);
          bool1 = bool2;
          str = ((com.supersonicads.sdk.c.a)localObject).f();
          if (str != null)
          {
            bool1 = bool2;
            localJSONObject.put(com.supersonicads.sdk.c.d.b("SDKVersion"), com.supersonicads.sdk.c.d.b(str));
          }
          bool1 = bool2;
          if (((com.supersonicads.sdk.c.a)localObject).e() != null)
          {
            bool1 = bool2;
            if (((com.supersonicads.sdk.c.a)localObject).e().length() > 0)
            {
              bool1 = bool2;
              localJSONObject.put(com.supersonicads.sdk.c.d.b("mobileCarrier"), com.supersonicads.sdk.c.d.b(((com.supersonicads.sdk.c.a)localObject).e()));
            }
          }
          bool1 = bool2;
          localObject = com.supersonicads.sdk.c.d.g(paramContext);
          bool1 = bool2;
          if (TextUtils.isEmpty((CharSequence)localObject)) {
            continue;
          }
          bool1 = bool2;
          localJSONObject.put(com.supersonicads.sdk.c.d.b("connectionType"), com.supersonicads.sdk.c.d.b((String)localObject));
          bool1 = bool2;
          localObject = paramContext.getResources().getConfiguration().locale.getLanguage();
          bool1 = bool2;
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            bool1 = bool2;
            localJSONObject.put(com.supersonicads.sdk.c.d.b("deviceLanguage"), com.supersonicads.sdk.c.d.b(((String)localObject).toUpperCase()));
          }
          bool1 = bool2;
          if (!g.a()) {
            continue;
          }
          bool1 = bool2;
          long l1 = com.supersonicads.sdk.c.d.a(paramContext, this.ag);
          bool1 = bool2;
          localJSONObject.put(com.supersonicads.sdk.c.d.b("diskFreeSize"), com.supersonicads.sdk.c.d.b(String.valueOf(l1)));
          bool1 = bool2;
          paramContext = String.valueOf(com.supersonicads.sdk.c.d.a());
          if (paramContext == null) {
            continue;
          }
          bool1 = bool2;
          localObject = new StringBuilder();
          bool1 = bool2;
          ((StringBuilder)localObject).append(com.supersonicads.sdk.c.d.b("deviceScreenSize")).append("[").append(com.supersonicads.sdk.c.d.b("width")).append("]");
          bool1 = bool2;
          localJSONObject.put(((StringBuilder)localObject).toString(), com.supersonicads.sdk.c.d.b(paramContext));
          bool1 = bool2;
          paramContext = String.valueOf(com.supersonicads.sdk.c.d.b());
          if (paramContext == null) {
            continue;
          }
          bool1 = bool2;
          localObject = new StringBuilder();
          bool1 = bool2;
          ((StringBuilder)localObject).append(com.supersonicads.sdk.c.d.b("deviceScreenSize")).append("[").append(com.supersonicads.sdk.c.d.b("height")).append("]");
          bool1 = bool2;
          localJSONObject.put(((StringBuilder)localObject).toString(), com.supersonicads.sdk.c.d.b(paramContext));
          bool1 = bool2;
        }
      }
      catch (JSONException paramContext)
      {
        for (;;) {}
      }
      try
      {
        paramContext = com.supersonicads.sdk.c.d.e(getBaseContext());
        if (!TextUtils.isEmpty(paramContext)) {
          localJSONObject.put(com.supersonicads.sdk.c.d.b("bundleId"), com.supersonicads.sdk.c.d.b(paramContext));
        }
        paramContext = String.valueOf(com.supersonicads.sdk.c.d.d());
        if (!TextUtils.isEmpty(paramContext)) {
          localJSONObject.put(com.supersonicads.sdk.c.d.b("deviceScreenScale"), com.supersonicads.sdk.c.d.b(paramContext));
        }
        paramContext = String.valueOf(com.supersonic.a.a.a());
        bool2 = bool1;
        if (!TextUtils.isEmpty(paramContext))
        {
          localJSONObject.put(com.supersonicads.sdk.c.d.b("unLocked"), com.supersonicads.sdk.c.d.b(paramContext));
          bool2 = bool1;
        }
        return new Object[] { localJSONObject.toString(), Boolean.valueOf(bool2) };
      }
      catch (JSONException paramContext)
      {
        break label771;
      }
      boolean bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      boolean bool1 = true;
      continue;
      label771:
      paramContext.printStackTrace();
      new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
      bool2 = bool1;
    }
  }
  
  private void e(String paramString)
  {
    Object localObject2 = "empty";
    Object localObject1;
    if (getDebugMode() == SSAEnums.DebugMode.MODE_0.getValue()) {
      localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
    }
    for (;;)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("try{").append(paramString).append("}catch(e){").append((String)localObject1).append("}");
      paramString = "javascript:" + ((StringBuilder)localObject2).toString();
      localObject1 = getBaseContext();
      if (!(localObject1 instanceof Activity)) {
        break;
      }
      ((Activity)localObject1).runOnUiThread(new SupersonicWebView.5(this, paramString, (StringBuilder)localObject2));
      return;
      localObject1 = localObject2;
      if (getDebugMode() >= SSAEnums.DebugMode.MODE_1.getValue())
      {
        localObject1 = localObject2;
        if (getDebugMode() <= SSAEnums.DebugMode.MODE_3.getValue()) {
          localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
        }
      }
    }
    new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=contextIsNotActivity" });
  }
  
  @SuppressLint({"NewApi"})
  private void f(String paramString)
  {
    evaluateJavascript(paramString, null);
  }
  
  private String g(String paramString)
  {
    return new com.supersonicads.sdk.data.d(paramString).i(ak);
  }
  
  private Context getBaseContext()
  {
    return ((MutableContextWrapper)getContext()).getBaseContext();
  }
  
  private String getRequestParameters()
  {
    Object localObject = com.supersonicads.sdk.c.a.a(getBaseContext());
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = ((com.supersonicads.sdk.c.a)localObject).f();
    if (!TextUtils.isEmpty(str1)) {
      localStringBuilder.append("SDKVersion").append("=").append(str1).append("&");
    }
    localObject = ((com.supersonicads.sdk.c.a)localObject).c();
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localStringBuilder.append("deviceOs").append("=").append((String)localObject);
    }
    localObject = Uri.parse(com.supersonicads.sdk.c.d.h());
    if (localObject != null)
    {
      String str2 = ((Uri)localObject).getScheme() + ":";
      str1 = ((Uri)localObject).getHost();
      int i1 = ((Uri)localObject).getPort();
      localObject = str1;
      if (i1 != -1) {
        localObject = str1 + ":" + i1;
      }
      localStringBuilder.append("&").append("protocol").append("=").append(str2);
      localStringBuilder.append("&").append("domain").append("=").append((String)localObject);
      localObject = com.supersonicads.sdk.c.d.i();
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        localStringBuilder.append("&").append("controllerConfig").append("=").append((String)localObject);
      }
      localStringBuilder.append("&").append("debug").append("=").append(getDebugMode());
    }
    return localStringBuilder.toString();
  }
  
  private String h(String paramString)
  {
    return new com.supersonicads.sdk.data.d(paramString).i(al);
  }
  
  /* Error */
  private Object[] i(String paramString)
  {
    // Byte code:
    //   0: new 362	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 363	org/json/JSONObject:<init>	()V
    //   7: astore 7
    //   9: ldc_w 613
    //   12: astore 5
    //   14: ldc_w 613
    //   17: astore 4
    //   19: aconst_null
    //   20: astore 6
    //   22: aconst_null
    //   23: astore_3
    //   24: aload_1
    //   25: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   28: ifne +329 -> 357
    //   31: aload_1
    //   32: getstatic 418	com/supersonicads/sdk/data/SSAEnums$ProductType:BrandConnect	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   35: invokevirtual 491	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   38: invokevirtual 633	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   41: ifeq +219 -> 260
    //   44: aload_0
    //   45: getfield 949	com/supersonicads/sdk/controller/SupersonicWebView:p	Ljava/lang/String;
    //   48: astore 5
    //   50: aload_0
    //   51: getfield 951	com/supersonicads/sdk/controller/SupersonicWebView:q	Ljava/lang/String;
    //   54: astore 4
    //   56: aload_0
    //   57: getfield 953	com/supersonicads/sdk/controller/SupersonicWebView:r	Ljava/util/Map;
    //   60: astore_3
    //   61: aload 7
    //   63: ldc_w 484
    //   66: aload_1
    //   67: invokevirtual 377	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   70: pop
    //   71: iconst_0
    //   72: istore_2
    //   73: aload 4
    //   75: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   78: ifne +316 -> 394
    //   81: aload 7
    //   83: ldc_w 447
    //   86: invokestatic 373	com/supersonicads/sdk/c/d:b	(Ljava/lang/String;)Ljava/lang/String;
    //   89: aload 4
    //   91: invokestatic 373	com/supersonicads/sdk/c/d:b	(Ljava/lang/String;)Ljava/lang/String;
    //   94: invokevirtual 377	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload 5
    //   100: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   103: ifne +325 -> 428
    //   106: aload 7
    //   108: ldc_w 438
    //   111: invokestatic 373	com/supersonicads/sdk/c/d:b	(Ljava/lang/String;)Ljava/lang/String;
    //   114: aload 5
    //   116: invokestatic 373	com/supersonicads/sdk/c/d:b	(Ljava/lang/String;)Ljava/lang/String;
    //   119: invokevirtual 377	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   122: pop
    //   123: aload_3
    //   124: ifnull +309 -> 433
    //   127: aload_3
    //   128: invokeinterface 955 1 0
    //   133: ifne +300 -> 433
    //   136: aload_3
    //   137: invokeinterface 573 1 0
    //   142: invokeinterface 579 1 0
    //   147: astore_1
    //   148: aload_1
    //   149: invokeinterface 585 1 0
    //   154: ifeq +279 -> 433
    //   157: aload_1
    //   158: invokeinterface 589 1 0
    //   163: checkcast 591	java/util/Map$Entry
    //   166: astore_3
    //   167: aload_3
    //   168: invokeinterface 594 1 0
    //   173: checkcast 389	java/lang/String
    //   176: ldc_w 957
    //   179: invokevirtual 633	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   182: ifeq +16 -> 198
    //   185: aload_0
    //   186: aload_3
    //   187: invokeinterface 597 1 0
    //   192: checkcast 389	java/lang/String
    //   195: invokespecial 960	com/supersonicads/sdk/controller/SupersonicWebView:setWebviewCache	(Ljava/lang/String;)V
    //   198: aload 7
    //   200: aload_3
    //   201: invokeinterface 594 1 0
    //   206: checkcast 389	java/lang/String
    //   209: invokestatic 373	com/supersonicads/sdk/c/d:b	(Ljava/lang/String;)Ljava/lang/String;
    //   212: aload_3
    //   213: invokeinterface 597 1 0
    //   218: checkcast 389	java/lang/String
    //   221: invokestatic 373	com/supersonicads/sdk/c/d:b	(Ljava/lang/String;)Ljava/lang/String;
    //   224: invokevirtual 377	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   227: pop
    //   228: goto -80 -> 148
    //   231: astore_3
    //   232: aload_3
    //   233: invokevirtual 384	org/json/JSONException:printStackTrace	()V
    //   236: new 386	com/supersonicads/sdk/c/e
    //   239: dup
    //   240: invokespecial 387	com/supersonicads/sdk/c/e:<init>	()V
    //   243: iconst_1
    //   244: anewarray 389	java/lang/String
    //   247: dup
    //   248: iconst_0
    //   249: ldc_w 962
    //   252: aastore
    //   253: invokevirtual 404	com/supersonicads/sdk/c/e:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   256: pop
    //   257: goto -109 -> 148
    //   260: aload_1
    //   261: getstatic 433	com/supersonicads/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   264: invokevirtual 491	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   267: invokevirtual 633	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   270: ifeq +23 -> 293
    //   273: aload_0
    //   274: getfield 440	com/supersonicads/sdk/controller/SupersonicWebView:w	Ljava/lang/String;
    //   277: astore 5
    //   279: aload_0
    //   280: getfield 449	com/supersonicads/sdk/controller/SupersonicWebView:x	Ljava/lang/String;
    //   283: astore 4
    //   285: aload_0
    //   286: getfield 451	com/supersonicads/sdk/controller/SupersonicWebView:y	Ljava/util/Map;
    //   289: astore_3
    //   290: goto -229 -> 61
    //   293: aload_1
    //   294: getstatic 467	com/supersonicads/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/supersonicads/sdk/data/SSAEnums$ProductType;
    //   297: invokevirtual 491	com/supersonicads/sdk/data/SSAEnums$ProductType:toString	()Ljava/lang/String;
    //   300: invokevirtual 633	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   303: ifeq -242 -> 61
    //   306: aload_0
    //   307: getfield 469	com/supersonicads/sdk/controller/SupersonicWebView:s	Ljava/lang/String;
    //   310: astore 5
    //   312: aload_0
    //   313: getfield 471	com/supersonicads/sdk/controller/SupersonicWebView:t	Ljava/lang/String;
    //   316: astore 4
    //   318: aload_0
    //   319: getfield 287	com/supersonicads/sdk/controller/SupersonicWebView:u	Ljava/util/Map;
    //   322: astore_3
    //   323: goto -262 -> 61
    //   326: astore_1
    //   327: aload_1
    //   328: invokevirtual 384	org/json/JSONException:printStackTrace	()V
    //   331: new 386	com/supersonicads/sdk/c/e
    //   334: dup
    //   335: invokespecial 387	com/supersonicads/sdk/c/e:<init>	()V
    //   338: iconst_1
    //   339: anewarray 389	java/lang/String
    //   342: dup
    //   343: iconst_0
    //   344: ldc_w 964
    //   347: aastore
    //   348: invokevirtual 404	com/supersonicads/sdk/c/e:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   351: pop
    //   352: iconst_0
    //   353: istore_2
    //   354: goto -281 -> 73
    //   357: iconst_1
    //   358: istore_2
    //   359: aload 6
    //   361: astore_3
    //   362: goto -289 -> 73
    //   365: astore_1
    //   366: aload_1
    //   367: invokevirtual 384	org/json/JSONException:printStackTrace	()V
    //   370: new 386	com/supersonicads/sdk/c/e
    //   373: dup
    //   374: invokespecial 387	com/supersonicads/sdk/c/e:<init>	()V
    //   377: iconst_1
    //   378: anewarray 389	java/lang/String
    //   381: dup
    //   382: iconst_0
    //   383: ldc_w 966
    //   386: aastore
    //   387: invokevirtual 404	com/supersonicads/sdk/c/e:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   390: pop
    //   391: goto -293 -> 98
    //   394: iconst_1
    //   395: istore_2
    //   396: goto -298 -> 98
    //   399: astore_1
    //   400: aload_1
    //   401: invokevirtual 384	org/json/JSONException:printStackTrace	()V
    //   404: new 386	com/supersonicads/sdk/c/e
    //   407: dup
    //   408: invokespecial 387	com/supersonicads/sdk/c/e:<init>	()V
    //   411: iconst_1
    //   412: anewarray 389	java/lang/String
    //   415: dup
    //   416: iconst_0
    //   417: ldc_w 968
    //   420: aastore
    //   421: invokevirtual 404	com/supersonicads/sdk/c/e:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   424: pop
    //   425: goto -302 -> 123
    //   428: iconst_1
    //   429: istore_2
    //   430: goto -307 -> 123
    //   433: iconst_2
    //   434: anewarray 208	java/lang/Object
    //   437: dup
    //   438: iconst_0
    //   439: aload 7
    //   441: invokevirtual 381	org/json/JSONObject:toString	()Ljava/lang/String;
    //   444: aastore
    //   445: dup
    //   446: iconst_1
    //   447: iload_2
    //   448: invokestatic 672	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   451: aastore
    //   452: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	453	0	this	SupersonicWebView
    //   0	453	1	paramString	String
    //   72	376	2	bool	boolean
    //   23	190	3	localObject1	Object
    //   231	2	3	localJSONException	JSONException
    //   289	73	3	localObject2	Object
    //   17	300	4	str1	String
    //   12	299	5	str2	String
    //   20	340	6	localObject3	Object
    //   7	433	7	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   198	228	231	org/json/JSONException
    //   61	71	326	org/json/JSONException
    //   81	98	365	org/json/JSONException
    //   106	123	399	org/json/JSONException
  }
  
  private String j(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString).append("');");
    return localStringBuilder.toString();
  }
  
  private void o()
  {
    WebSettings localWebSettings = getSettings();
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setUseWideViewPort(true);
    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    localWebSettings.setBuiltInZoomControls(false);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setGeolocationEnabled(true);
    localWebSettings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
    localWebSettings.setDomStorageEnabled(true);
    try
    {
      setDisplayZoomControls(localWebSettings);
      setMediaPlaybackJellyBean(localWebSettings);
      return;
    }
    catch (Throwable localThrowable)
    {
      com.supersonicads.sdk.c.c.b(this.m, "setWebSettings - " + localThrowable.toString());
      new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=setWebViewSettings" });
    }
  }
  
  private void p()
  {
    if (this.an != null) {
      this.an.a();
    }
  }
  
  private void setDisplayZoomControls(WebSettings paramWebSettings)
  {
    if (Build.VERSION.SDK_INT > 11) {
      paramWebSettings.setDisplayZoomControls(false);
    }
  }
  
  public static void setEXTERNAL_URL(String paramString)
  {
    e = paramString;
  }
  
  @SuppressLint({"NewApi"})
  private void setMediaPlaybackJellyBean(WebSettings paramWebSettings)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      paramWebSettings.setMediaPlaybackRequiresUserGesture(false);
    }
  }
  
  private void setMissProduct(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.BrandConnect) {
      this.C = true;
    }
    for (;;)
    {
      com.supersonicads.sdk.c.c.a(this.m, "setMissProduct(" + paramProductType + ")");
      return;
      if (paramProductType == SSAEnums.ProductType.Interstitial) {
        this.D = true;
      } else if (paramProductType == SSAEnums.ProductType.OfferWall) {
        this.E = true;
      } else if (paramProductType == SSAEnums.ProductType.OfferWallCredits) {
        this.F = true;
      }
    }
  }
  
  private void setWebviewBackground(String paramString)
  {
    paramString = new com.supersonicads.sdk.data.d(paramString).i("color");
    int i1 = 0;
    if (!"transparent".equalsIgnoreCase(paramString)) {
      i1 = Color.parseColor(paramString);
    }
    setBackgroundColor(i1);
  }
  
  private void setWebviewCache(String paramString)
  {
    if (paramString.equalsIgnoreCase("0"))
    {
      getSettings().setCacheMode(2);
      return;
    }
    getSettings().setCacheMode(-1);
  }
  
  public void a()
  {
    g.a(this.ag, "", "mobileController.html");
    String str = com.supersonicads.sdk.c.d.h();
    com.supersonicads.sdk.data.c localC = new com.supersonicads.sdk.data.c(str, "");
    this.M = new SupersonicWebView.1(this, 40000L, 1000L).start();
    if (!this.B.b())
    {
      com.supersonicads.sdk.c.c.a(this.m, "Download Mobile Controller: " + str);
      this.B.b(localC);
      return;
    }
    com.supersonicads.sdk.c.c.a(this.m, "Download Mobile Controller: already alive");
  }
  
  public void a(int paramInt)
  {
    try
    {
      loadUrl("about:blank");
      str = "file://" + this.ag + File.separator + "mobileController.html";
      if (new File(this.ag + File.separator + "mobileController.html").exists())
      {
        this.I = getRequestParameters();
        str = str + "?" + this.I;
        this.L = new SupersonicWebView.2(this, 10000L, 1000L, paramInt).start();
      }
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        String str;
        loadUrl(str);
        com.supersonicads.sdk.c.c.a(this.m, "load(): " + str);
        return;
        localThrowable1 = localThrowable1;
        com.supersonicads.sdk.c.c.b(this.m, "WebViewController:: load: " + localThrowable1.toString());
        new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank" });
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          com.supersonicads.sdk.c.c.b(this.m, "WebViewController:: load: " + localThrowable2.toString());
          new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath" });
        }
      }
      com.supersonicads.sdk.c.c.a(this.m, "load(): Mobile Controller HTML Does not exist");
      new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem" });
    }
  }
  
  public void a(Context paramContext)
  {
    paramContext.registerReceiver(this.am, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  public void a(AdUnitsState paramAdUnitsState)
  {
    for (;;)
    {
      int i1;
      synchronized (this.aj)
      {
        if ((paramAdUnitsState.shouldRestore()) && (this.ae.equals(SSAEnums.ControllerState.Ready)))
        {
          Log.d(this.m, "restoreState(state:" + paramAdUnitsState + ")");
          i1 = paramAdUnitsState.getDisplayedProduct();
          if (i1 == -1) {
            break label448;
          }
          if (i1 == SSAEnums.ProductType.BrandConnect.ordinal())
          {
            Log.d(this.m, "onRVAdClosed()");
            if (this.aa != null) {
              this.aa.onRVAdClosed();
            }
            paramAdUnitsState.adOpened(-1);
            String str1;
            String str2;
            Map localMap;
            if (paramAdUnitsState.isInterstitialInitSuccess())
            {
              Log.d(this.m, "onInterstitialAvailability(false)");
              if (this.ac != null) {}
              str1 = paramAdUnitsState.getInterstitialAppKey();
              str2 = paramAdUnitsState.getInterstitialUserId();
              localMap = paramAdUnitsState.getInterstitialExtraParams();
              Log.d(this.m, "initInterstitial(appKey:" + str1 + ", userId:" + str2 + ", extraParam:" + localMap + ")");
              a(str1, str2, localMap, this.ac);
            }
            if (paramAdUnitsState.isRewardedVideoInitSuccess())
            {
              Log.d(this.m, "onRVNoMoreOffers()");
              if (this.aa != null) {
                this.aa.onRVNoMoreOffers();
              }
              str1 = paramAdUnitsState.getRewardedVideoAppKey();
              str2 = paramAdUnitsState.getRewardedVideoUserId();
              localMap = paramAdUnitsState.getRewardedVideoExtraParams();
              Log.d(this.m, "initRewardedVideo(appKey:" + str1 + ", userId:" + str2 + ", extraParam:" + localMap + ")");
              a(str1, str2, localMap, this.aa);
            }
            paramAdUnitsState.setShouldRestore(false);
          }
        }
        else
        {
          this.ai = paramAdUnitsState;
          return;
        }
        if (i1 == SSAEnums.ProductType.Interstitial.ordinal())
        {
          Log.d(this.m, "onInterstitialAdClosed()");
          if (this.ac == null) {
            continue;
          }
          this.ac.onInterstitialClose();
        }
      }
      if (i1 == SSAEnums.ProductType.OfferWall.ordinal())
      {
        Log.d(this.m, "onOWAdClosed()");
        if (this.ad != null)
        {
          this.ad.onOWAdClosed();
          continue;
          label448:
          Log.d(this.m, "No ad was opened");
        }
      }
    }
  }
  
  public void a(com.supersonicads.sdk.data.c paramC)
  {
    if (paramC.a().contains("mobileController.html"))
    {
      a(1);
      return;
    }
    a(paramC.a(), paramC.b());
  }
  
  public void a(String paramString)
  {
    e(d("nativeNavigationPressed", a("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void a(String paramString1, String paramString2)
  {
    e(d("assetCached", a("file", paramString1, "path", paramString2, null, null, null, null, null, false)));
  }
  
  public void a(String paramString1, String paramString2, com.supersonicads.sdk.a.c paramC)
  {
    this.z = paramString1;
    this.A = paramString2;
    this.ad = paramC;
    a(this.z, this.A, SSAEnums.ProductType.OfferWallCredits, "Show OW Credits");
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    e(d("assetCachedFailed", a("file", paramString1, "path", paramString2, "errMsg", paramString3, null, null, null, false)));
  }
  
  public void a(String paramString1, String paramString2, Map<String, String> paramMap, com.supersonicads.sdk.a.b paramB)
  {
    this.w = paramString1;
    this.x = paramString2;
    this.y = paramMap;
    this.ac = paramB;
    this.ai.setInterstitialAppKey(this.w);
    this.ai.setInterstitialUserId(this.x);
    this.ai.setInterstitialExtraParams(this.y);
    this.ai.setReportInitInterstitial(true);
    a(this.w, this.x, SSAEnums.ProductType.Interstitial, "Init IS");
  }
  
  public void a(String paramString1, String paramString2, Map<String, String> paramMap, com.supersonicads.sdk.a.c paramC)
  {
    this.s = paramString1;
    this.t = paramString2;
    this.u = paramMap;
    this.ad = paramC;
    this.ai.setOfferWallExtraParams(this.u);
    this.ai.setOfferwallReportInit(true);
    a(this.s, this.t, SSAEnums.ProductType.OfferWall, "Init OW");
  }
  
  public void a(String paramString1, String paramString2, Map<String, String> paramMap, com.supersonicads.sdk.a.d paramD)
  {
    this.p = paramString1;
    this.q = paramString2;
    this.r = paramMap;
    this.aa = paramD;
    this.ai.setRewardedVideoAppKey(this.p);
    this.ai.setRewardedVideoUserId(this.q);
    this.ai.setRewardedVideoExtraParams(this.r);
    a(this.p, this.q, SSAEnums.ProductType.BrandConnect, "Init BC");
  }
  
  public void a(Map<String, String> paramMap)
  {
    this.u = paramMap;
    e(b("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail"));
  }
  
  public void a(boolean paramBoolean, String paramString)
  {
    e(d("viewableChange", a("webview", paramString, null, null, null, null, null, null, "isViewable", paramBoolean)));
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    String str = "none";
    if (paramBoolean1) {
      str = "wifi";
    }
    for (;;)
    {
      e(d("deviceStatusChanged", a("connectionType", str, null, null, null, null, null, null, null, false)));
      return;
      if (paramBoolean2) {
        str = "3g";
      }
    }
  }
  
  public void b()
  {
    this.ah = null;
  }
  
  public void b(Context paramContext)
  {
    try
    {
      paramContext.unregisterReceiver(this.am);
      return;
    }
    catch (Exception paramContext)
    {
      Log.e(this.m, "unregisterConnectionReceiver - " + paramContext);
      new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
      return;
    }
    catch (IllegalArgumentException paramContext) {}
  }
  
  public void b(com.supersonicads.sdk.data.c paramC)
  {
    if (paramC.a().contains("mobileController.html"))
    {
      this.M.cancel();
      if (this.C) {
        b(SSAEnums.ProductType.BrandConnect);
      }
      if (this.D) {
        b(SSAEnums.ProductType.Interstitial);
      }
      if (this.E) {
        b(SSAEnums.ProductType.OfferWall);
      }
      if (this.F) {
        b(SSAEnums.ProductType.OfferWallCredits);
      }
      return;
    }
    a(paramC.a(), paramC.b(), paramC.c());
  }
  
  public void b(String paramString)
  {
    if (paramString.equals("forceClose")) {
      p();
    }
    e(d("engageEnd", a("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void c()
  {
    if (!d())
    {
      this.ai.setReportLoadInterstitial(true);
      e(b("loadInterstitial", "onLoadInterstitialSuccess", "onLoadInterstitialFail"));
    }
    Context localContext;
    do
    {
      do
      {
        return;
      } while (!d(SSAEnums.ProductType.Interstitial.toString()));
      localContext = getBaseContext();
    } while (!(localContext instanceof Activity));
    ((Activity)localContext).runOnUiThread(new SupersonicWebView.3(this));
  }
  
  public boolean c(String paramString)
  {
    Object localObject = f.a().d();
    if ((localObject != null) && (!((List)localObject).isEmpty()))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (paramString.contains((String)((Iterator)localObject).next()))
        {
          paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
          getBaseContext().startActivity(paramString);
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean d()
  {
    if (this.v == null) {
      return false;
    }
    return this.v.booleanValue();
  }
  
  public void destroy()
  {
    super.destroy();
    if (this.B != null) {
      this.B.a();
    }
    if (this.am != null) {
      this.am = null;
    }
  }
  
  public void e()
  {
    e(b("showInterstitial", "onShowInterstitialSuccess", "onShowInterstitialFail"));
  }
  
  public void f()
  {
    e(b("showBrandConnect", "onShowBrandConnectSuccess", "onShowBrandConnectFail"));
  }
  
  public void g()
  {
    if (this.ae == SSAEnums.ControllerState.Ready) {
      e(j("enterBackground"));
    }
  }
  
  public String getControllerKeyPressed()
  {
    String str = this.J;
    setControllerKeyPressed("interrupt");
    return str;
  }
  
  public int getDebugMode()
  {
    return a;
  }
  
  public FrameLayout getLayout()
  {
    return this.U;
  }
  
  public String getOrientationState()
  {
    return this.W;
  }
  
  public AdUnitsState getSavedState()
  {
    return this.ai;
  }
  
  public State getState()
  {
    return this.V;
  }
  
  public void h()
  {
    if (this.ae == SSAEnums.ControllerState.Ready) {
      e(j("enterForeground"));
    }
  }
  
  public void i()
  {
    e(j("pageFinished"));
  }
  
  public void j()
  {
    e(j("interceptedUrlToStore"));
  }
  
  public void k()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onPause();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.supersonicads.sdk.c.c.a(this.m, "WebViewController: pause() - " + localThrowable);
      new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause" });
    }
  }
  
  public void l()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.supersonicads.sdk.c.c.a(this.m, "WebViewController: onResume() - " + localThrowable);
      new e().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume" });
    }
  }
  
  public boolean m()
  {
    return this.R != null;
  }
  
  public void n()
  {
    this.Q.onHideCustomView();
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    com.supersonicads.sdk.c.c.a(this.m, paramString1 + " " + paramString4);
  }
  
  public WebBackForwardList saveState(Bundle paramBundle)
  {
    return super.saveState(paramBundle);
  }
  
  public void setControllerKeyPressed(String paramString)
  {
    this.J = paramString;
  }
  
  public void setDebugMode(int paramInt)
  {
    a = paramInt;
  }
  
  public void setOnWebViewControllerChangeListener(d paramD)
  {
    this.an = paramD;
  }
  
  public void setOrientationState(String paramString)
  {
    this.W = paramString;
  }
  
  public void setState(State paramState)
  {
    this.V = paramState;
  }
  
  public void setVideoEventsListener(a paramA)
  {
    this.ah = paramA;
  }
  
  public static enum State
  {
    Display,  Gone;
    
    private State() {}
  }
  
  public class c
  {
    volatile int a = 0;
    
    public c(Context paramContext) {}
    
    private void a(String paramString, JSONArray paramJSONArray)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "sendResults: " + this.a);
      if (this.a <= 0) {
        b(paramString, paramJSONArray);
      }
    }
    
    private void a(boolean paramBoolean)
    {
      SupersonicWebView.a(SupersonicWebView.this, Boolean.valueOf(paramBoolean));
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString())) {
        SupersonicWebView.b(SupersonicWebView.this, "onInterstitialAvailability", String.valueOf(SupersonicWebView.L(SupersonicWebView.this)));
      }
    }
    
    private void b(String paramString, JSONArray paramJSONArray)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = SupersonicWebView.a(SupersonicWebView.this, paramString, paramJSONArray.toString(), "onGetUDIASuccess", "onGetUDIAFail");
        SupersonicWebView.c(SupersonicWebView.this, paramString);
      }
    }
    
    @JavascriptInterface
    public void adClicked(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "adClicked(" + paramString + ")");
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.19(this));
        }
        SupersonicWebView.b(SupersonicWebView.this, "onInterstitialAdClicked", paramString);
      }
    }
    
    @JavascriptInterface
    public void adCredited(String paramString)
    {
      String str3 = null;
      boolean bool1 = false;
      Log.d(SupersonicWebView.K(SupersonicWebView.this), "adCredited(" + paramString + ")");
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      String str1 = ((com.supersonicads.sdk.data.d)localObject).i("credits");
      int i;
      String str5;
      int j;
      label91:
      String str4;
      String str2;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str5 = ((com.supersonicads.sdk.data.d)localObject).i("total");
        if (str5 == null) {
          break label190;
        }
        j = Integer.parseInt(str5);
        str4 = ((com.supersonicads.sdk.data.d)localObject).i("productType");
        if (!((com.supersonicads.sdk.data.d)localObject).j("externalPoll")) {
          break label195;
        }
        str1 = SupersonicWebView.D(SupersonicWebView.this);
        str2 = SupersonicWebView.E(SupersonicWebView.this);
        label128:
        if (!str4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          break label354;
        }
        if ((!((com.supersonicads.sdk.data.d)localObject).g("signature")) && (!((com.supersonicads.sdk.data.d)localObject).g("timestamp")) && (!((com.supersonicads.sdk.data.d)localObject).g("totalCreditsFlag"))) {
          break label216;
        }
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
      }
      label190:
      label195:
      label216:
      label260:
      label354:
      label361:
      for (;;)
      {
        return;
        i = 0;
        break;
        j = 0;
        break label91;
        str1 = SupersonicWebView.y(SupersonicWebView.this);
        str2 = SupersonicWebView.z(SupersonicWebView.this);
        break label128;
        boolean bool2;
        if (((com.supersonicads.sdk.data.d)localObject).i("signature").equalsIgnoreCase(com.supersonicads.sdk.c.d.c(str5 + str1 + str2)))
        {
          bool1 = true;
          bool2 = ((com.supersonicads.sdk.data.d)localObject).j("totalCreditsFlag");
          str3 = ((com.supersonicads.sdk.data.d)localObject).i("timestamp");
        }
        for (;;)
        {
          if (!SupersonicWebView.e(SupersonicWebView.this, str4)) {
            break label361;
          }
          localObject = SupersonicWebView.j(SupersonicWebView.this);
          if (!(localObject instanceof Activity)) {
            break;
          }
          ((Activity)localObject).runOnUiThread(new SupersonicWebView.c.12(this, str4, i, bool1, j, bool2, str3, str1, str2, paramString));
          return;
          SupersonicWebView.a(SupersonicWebView.this, paramString, false, "Controller signature is not equal to SDK signature", null);
          break label260;
          bool1 = false;
          bool2 = false;
        }
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "adUnitsReady(" + paramString + ")");
      com.supersonicads.sdk.data.a localA = new com.supersonicads.sdk.data.a(paramString);
      if (!localA.b()) {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "Num Of Ad Units Do Not Exist", null);
      }
      Context localContext;
      do
      {
        do
        {
          return;
          SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
          paramString = localA.c();
        } while (!SupersonicWebView.e(SupersonicWebView.this, paramString));
        localContext = SupersonicWebView.j(SupersonicWebView.this);
      } while (!(localContext instanceof Activity));
      ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.1(this, localA, paramString));
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    @JavascriptInterface
    public void checkInstalledApps(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "checkInstalledApps(" + paramString + ")");
      String str1 = SupersonicWebView.a(SupersonicWebView.this, paramString);
      String str2 = SupersonicWebView.b(SupersonicWebView.this, paramString);
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      paramString = ((com.supersonicads.sdk.data.d)localObject).i(SupersonicWebView.i);
      localObject = ((com.supersonicads.sdk.data.d)localObject).i(SupersonicWebView.j);
      paramString = SupersonicWebView.a(SupersonicWebView.this, paramString, (String)localObject);
      localObject = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        if (TextUtils.isEmpty(str2)) {
          break label164;
        }
        paramString = str2;
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = SupersonicWebView.a(SupersonicWebView.this, paramString, (String)localObject, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
          SupersonicWebView.c(SupersonicWebView.this, paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        } else {
          label164:
          paramString = null;
        }
      }
    }
    
    @JavascriptInterface
    public void createCalendarEvent(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "createCalendarEvent(" + paramString + ")");
      try
      {
        paramString = new JSONObject();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("frequency", "weekly");
        paramString.put("id", "testevent723GDf84");
        paramString.put("description", "Watch this crazy show on cannel 5!");
        paramString.put("start", "2014-02-01T20:00:00-8:00");
        paramString.put("end", "2014-06-30T20:00:00-8:00");
        paramString.put("status", "pending");
        paramString.put("recurrence", localJSONObject.toString());
        paramString.put("reminder", "2014-02-01T19:50:00-8:00");
        return;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void deleteFile(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "deleteFile(" + paramString + ")");
      com.supersonicads.sdk.data.c localC = new com.supersonicads.sdk.data.c(paramString);
      if (!g.b(SupersonicWebView.g(SupersonicWebView.this), localC.b()))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "File not exist", "1");
        return;
      }
      boolean bool = g.a(SupersonicWebView.g(SupersonicWebView.this), localC.b(), localC.a());
      SupersonicWebView.a(SupersonicWebView.this, paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void deleteFolder(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "deleteFolder(" + paramString + ")");
      com.supersonicads.sdk.data.c localC = new com.supersonicads.sdk.data.c(paramString);
      if (!g.b(SupersonicWebView.g(SupersonicWebView.this), localC.b()))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "Folder not exist", "1");
        return;
      }
      boolean bool = g.c(SupersonicWebView.g(SupersonicWebView.this), localC.b());
      SupersonicWebView.a(SupersonicWebView.this, paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void displayWebView(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "displayWebView(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      paramString = new com.supersonicads.sdk.data.d(paramString);
      boolean bool = ((Boolean)paramString.h("display")).booleanValue();
      String str2 = paramString.i("productType");
      int i = 0;
      if (bool)
      {
        if (SupersonicWebView.this.getState() != SupersonicWebView.State.Display)
        {
          SupersonicWebView.this.setState(SupersonicWebView.State.Display);
          com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "State: " + SupersonicWebView.H(SupersonicWebView.this));
          Context localContext = SupersonicWebView.j(SupersonicWebView.this);
          String str1 = SupersonicWebView.this.getOrientationState();
          int j = com.supersonicads.sdk.c.d.a(localContext);
          if (str2.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            paramString = new Intent(localContext, InterstitialActivity.class);
          }
          for (;;)
          {
            if ((i != 0) && (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.BrandConnect.toString()))) {
              SupersonicWebView.r(SupersonicWebView.this).onRVAdOpened();
            }
            paramString.putExtra("orientation_set_flag", str1);
            paramString.putExtra("rotation_set_flag", j);
            localContext.startActivity(paramString);
            return;
            paramString = new Intent(localContext, ControllerActivity.class);
            if (SSAEnums.ProductType.BrandConnect.toString().equalsIgnoreCase(str2))
            {
              paramString.putExtra("productType", SSAEnums.ProductType.BrandConnect.toString());
              SupersonicWebView.F(SupersonicWebView.this).adOpened(SSAEnums.ProductType.BrandConnect.ordinal());
              i = 1;
            }
            else
            {
              paramString.putExtra("productType", SSAEnums.ProductType.OfferWall.toString());
              SupersonicWebView.F(SupersonicWebView.this).adOpened(SSAEnums.ProductType.OfferWall.ordinal());
            }
          }
        }
        com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "State: " + SupersonicWebView.H(SupersonicWebView.this));
        return;
      }
      SupersonicWebView.this.setState(SupersonicWebView.State.Gone);
      SupersonicWebView.I(SupersonicWebView.this);
    }
    
    @JavascriptInterface
    public void getApplicationInfo(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "getApplicationInfo(" + paramString + ")");
      String str1 = SupersonicWebView.a(SupersonicWebView.this, paramString);
      String str2 = SupersonicWebView.b(SupersonicWebView.this, paramString);
      paramString = new com.supersonicads.sdk.data.d(paramString).i("productType");
      Object localObject = new Object[2];
      paramString = SupersonicWebView.d(SupersonicWebView.this, paramString);
      localObject = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        if (TextUtils.isEmpty(str2)) {
          break label156;
        }
        paramString = str2;
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = SupersonicWebView.a(SupersonicWebView.this, paramString, (String)localObject, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
          SupersonicWebView.c(SupersonicWebView.this, paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        } else {
          label156:
          paramString = null;
        }
      }
    }
    
    @JavascriptInterface
    public void getCachedFilesMap(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "getCachedFilesMap(" + paramString + ")");
      String str = SupersonicWebView.a(SupersonicWebView.this, paramString);
      if (!TextUtils.isEmpty(str))
      {
        localObject = new com.supersonicads.sdk.data.d(paramString);
        if (!((com.supersonicads.sdk.data.d)localObject).f("path")) {
          SupersonicWebView.a(SupersonicWebView.this, paramString, false, "path key does not exist", null);
        }
      }
      else
      {
        return;
      }
      Object localObject = (String)((com.supersonicads.sdk.data.d)localObject).h("path");
      if (!g.b(SupersonicWebView.g(SupersonicWebView.this), (String)localObject))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "path file does not exist on disk", null);
        return;
      }
      paramString = g.d(SupersonicWebView.g(SupersonicWebView.this), (String)localObject);
      paramString = SupersonicWebView.a(SupersonicWebView.this, str, paramString, "onGetCachedFilesMapSuccess", "onGetCachedFilesMapFail");
      SupersonicWebView.c(SupersonicWebView.this, paramString);
    }
    
    @JavascriptInterface
    public void getDeviceStatus(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "getDeviceStatus(" + paramString + ")");
      String str1 = SupersonicWebView.a(SupersonicWebView.this, paramString);
      String str2 = SupersonicWebView.b(SupersonicWebView.this, paramString);
      paramString = new Object[2];
      paramString = SupersonicWebView.a(SupersonicWebView.this, SupersonicWebView.j(SupersonicWebView.this));
      String str3 = (String)paramString[0];
      boolean bool = ((Boolean)paramString[1]).booleanValue();
      paramString = null;
      if (bool) {
        if (!TextUtils.isEmpty(str2)) {
          paramString = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = SupersonicWebView.a(SupersonicWebView.this, paramString, str3, "onGetDeviceStatusSuccess", "onGetDeviceStatusFail");
          SupersonicWebView.c(SupersonicWebView.this, paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getOrientation(String paramString)
    {
      paramString = SupersonicWebView.a(SupersonicWebView.this, paramString);
      String str = com.supersonicads.sdk.c.d.c(SupersonicWebView.j(SupersonicWebView.this)).toString();
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = SupersonicWebView.a(SupersonicWebView.this, paramString, str, "onGetOrientationSuccess", "onGetOrientationFail");
        SupersonicWebView.c(SupersonicWebView.this, paramString);
      }
    }
    
    @JavascriptInterface
    public void getUDIA(String paramString)
    {
      this.a = 0;
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "getUDIA(" + paramString + ")");
      String str = SupersonicWebView.a(SupersonicWebView.this, paramString);
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      if (!((com.supersonicads.sdk.data.d)localObject).f("getByFlag")) {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "getByFlag key does not exist", null);
      }
      for (;;)
      {
        return;
        int i = Integer.parseInt(((com.supersonicads.sdk.data.d)localObject).i("getByFlag"));
        if (i == 0) {
          continue;
        }
        localObject = Integer.toBinaryString(i);
        if (TextUtils.isEmpty((CharSequence)localObject))
        {
          SupersonicWebView.a(SupersonicWebView.this, paramString, false, "fialed to convert getByFlag", null);
          return;
        }
        localObject = new StringBuilder((String)localObject).reverse().toString().toCharArray();
        paramString = new JSONArray();
        JSONObject localJSONObject;
        if (localObject[3] == '0') {
          localJSONObject = new JSONObject();
        }
        try
        {
          localJSONObject.put("sessions", f.a().e());
          f.a().f();
          paramString.put(localJSONObject);
          if (localObject[2] != '1') {
            continue;
          }
          this.a += 1;
          localObject = com.supersonicads.sdk.c.b.a(SupersonicWebView.j(SupersonicWebView.this));
          if (localObject != null)
          {
            localJSONObject = new JSONObject();
            try
            {
              localJSONObject.put("latitude", ((Location)localObject).getLatitude());
              localJSONObject.put("longitude", ((Location)localObject).getLongitude());
              paramString.put(localJSONObject);
              this.a -= 1;
              a(str, paramString);
              com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "done location");
              return;
            }
            catch (JSONException paramString)
            {
              return;
            }
          }
          this.a -= 1;
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;) {}
        }
      }
    }
    
    @JavascriptInterface
    public void getUserData(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "getUserData(" + paramString + ")");
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      if (!((com.supersonicads.sdk.data.d)localObject).f("key"))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "key does not exist", null);
        return;
      }
      paramString = SupersonicWebView.a(SupersonicWebView.this, paramString);
      localObject = ((com.supersonicads.sdk.data.d)localObject).i("key");
      String str = f.a().c((String)localObject);
      localObject = SupersonicWebView.a(SupersonicWebView.this, (String)localObject, str, null, null, null, null, null, null, null, false);
      paramString = SupersonicWebView.c(SupersonicWebView.this, paramString, (String)localObject);
      SupersonicWebView.c(SupersonicWebView.this, paramString);
    }
    
    @JavascriptInterface
    public void getUserUniqueId(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "getUserUniqueId(" + paramString + ")");
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      if (!((com.supersonicads.sdk.data.d)localObject).f("productType")) {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "productType does not exist", null);
      }
      do
      {
        return;
        paramString = SupersonicWebView.a(SupersonicWebView.this, paramString);
      } while (TextUtils.isEmpty(paramString));
      localObject = ((com.supersonicads.sdk.data.d)localObject).i("productType");
      String str = f.a().d((String)localObject);
      localObject = SupersonicWebView.a(SupersonicWebView.this, "userUniqueId", str, "productType", (String)localObject, null, null, null, null, null, false);
      paramString = SupersonicWebView.a(SupersonicWebView.this, paramString, (String)localObject, "onGetUserUniqueIdSuccess", "onGetUserUniqueIdFail");
      SupersonicWebView.c(SupersonicWebView.this, paramString);
    }
    
    @JavascriptInterface
    public void initController(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "initController(" + paramString + ")");
      paramString = new com.supersonicads.sdk.data.d(paramString);
      if (paramString.f("stage"))
      {
        paramString = paramString.i("stage");
        if (!"ready".equalsIgnoreCase(paramString)) {
          break label291;
        }
        SupersonicWebView.a(SupersonicWebView.this, SSAEnums.ControllerState.Ready);
        SupersonicWebView.l(SupersonicWebView.this).cancel();
        SupersonicWebView.m(SupersonicWebView.this).cancel();
        if (SupersonicWebView.n(SupersonicWebView.this)) {
          SupersonicWebView.this.a(SupersonicWebView.o(SupersonicWebView.this), SupersonicWebView.p(SupersonicWebView.this), SupersonicWebView.q(SupersonicWebView.this), SupersonicWebView.r(SupersonicWebView.this));
        }
        if (SupersonicWebView.s(SupersonicWebView.this)) {
          SupersonicWebView.this.a(SupersonicWebView.t(SupersonicWebView.this), SupersonicWebView.u(SupersonicWebView.this), SupersonicWebView.v(SupersonicWebView.this), SupersonicWebView.w(SupersonicWebView.this));
        }
        if (SupersonicWebView.x(SupersonicWebView.this)) {
          SupersonicWebView.this.a(SupersonicWebView.y(SupersonicWebView.this), SupersonicWebView.z(SupersonicWebView.this), SupersonicWebView.A(SupersonicWebView.this), SupersonicWebView.B(SupersonicWebView.this));
        }
        if (SupersonicWebView.C(SupersonicWebView.this)) {
          SupersonicWebView.this.a(SupersonicWebView.D(SupersonicWebView.this), SupersonicWebView.E(SupersonicWebView.this), SupersonicWebView.B(SupersonicWebView.this));
        }
        SupersonicWebView.this.a(SupersonicWebView.F(SupersonicWebView.this));
      }
      label291:
      do
      {
        return;
        if ("loaded".equalsIgnoreCase(paramString))
        {
          SupersonicWebView.a(SupersonicWebView.this, SSAEnums.ControllerState.Loaded);
          return;
        }
        if (!"failed".equalsIgnoreCase(paramString)) {
          break;
        }
        SupersonicWebView.a(SupersonicWebView.this, SSAEnums.ControllerState.Failed);
        if (SupersonicWebView.n(SupersonicWebView.this)) {
          SupersonicWebView.a(SupersonicWebView.this, SSAEnums.ProductType.BrandConnect);
        }
        if (SupersonicWebView.s(SupersonicWebView.this)) {
          SupersonicWebView.a(SupersonicWebView.this, SSAEnums.ProductType.Interstitial);
        }
        if (SupersonicWebView.x(SupersonicWebView.this)) {
          SupersonicWebView.a(SupersonicWebView.this, SSAEnums.ProductType.OfferWall);
        }
      } while (!SupersonicWebView.C(SupersonicWebView.this));
      SupersonicWebView.a(SupersonicWebView.this, SSAEnums.ProductType.OfferWallCredits);
      return;
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "No STAGE mentioned! Should not get here!");
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onAdWindowsClosed(" + paramString + ")");
      SupersonicWebView.F(SupersonicWebView.this).adClosed();
      paramString = new com.supersonicads.sdk.data.d(paramString).i("productType");
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.BrandConnect.toString())) {
        Log.d(SupersonicWebView.K(SupersonicWebView.this), "onRVAdClosed()");
      }
      for (;;)
      {
        if ((SupersonicWebView.e(SupersonicWebView.this, paramString)) && (paramString != null))
        {
          Context localContext = SupersonicWebView.j(SupersonicWebView.this);
          if ((localContext instanceof Activity)) {
            ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.11(this, paramString));
          }
        }
        return;
        if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
          Log.d(SupersonicWebView.K(SupersonicWebView.this), "onISAdClosed()");
        } else if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
          Log.d(SupersonicWebView.K(SupersonicWebView.this), "onOWAdClosed()");
        }
      }
    }
    
    @JavascriptInterface
    public void onGenericFunctionFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGenericFunctionFail(" + paramString + ")");
      if (SupersonicWebView.M(SupersonicWebView.this) == null)
      {
        com.supersonicads.sdk.c.c.c(SupersonicWebView.a(SupersonicWebView.this), "genericFunctionListener was not found");
        return;
      }
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      Context localContext = SupersonicWebView.j(SupersonicWebView.this);
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.9(this, str));
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGenericFunctionFail", paramString);
    }
    
    @JavascriptInterface
    public void onGenericFunctionSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGenericFunctionSuccess(" + paramString + ")");
      if (SupersonicWebView.M(SupersonicWebView.this) == null)
      {
        com.supersonicads.sdk.c.c.c(SupersonicWebView.a(SupersonicWebView.this), "genericFunctionListener was not found");
        return;
      }
      Context localContext = SupersonicWebView.j(SupersonicWebView.this);
      if ((localContext instanceof Activity)) {
        ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.8(this));
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetApplicationInfoFail(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGetApplicationInfoFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetApplicationInfoSuccess(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGetApplicationInfoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetCachedFilesMapFail(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGetCachedFilesMapFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetCachedFilesMapSuccess(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGetCachedFilesMapSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetDeviceStatusFail(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGetDeviceStatusFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetDeviceStatusSuccess(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGetDeviceStatusSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetUDIAFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUDIASuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserCreditsFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetUserCreditsFail(" + paramString + ")");
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.10(this, str));
        }
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onGetUserCreditsFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetUserUniqueIdFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onGetUserUniqueIdSuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onInitBrandConnectFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onInitBrandConnectFail(" + paramString + ")");
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      SupersonicWebView.F(SupersonicWebView.this).setRewardedVideoInitSuccess(false);
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.BrandConnect.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.13(this, str));
        }
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onInitBrandConnectFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitBrandConnectSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onInitBrandConnectSuccess(" + paramString + ")");
      com.supersonicads.sdk.data.b localB = new com.supersonicads.sdk.data.b(paramString);
      f.a().a(localB);
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onInitBrandConnectSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onInitInterstitialFail(" + paramString + ")");
      SupersonicWebView.F(SupersonicWebView.this).setInterstitialInitSuccess(false);
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      if (SupersonicWebView.F(SupersonicWebView.this).reportInitInterstitial())
      {
        SupersonicWebView.F(SupersonicWebView.this).setReportInitInterstitial(false);
        if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString()))
        {
          Context localContext = SupersonicWebView.j(SupersonicWebView.this);
          if ((localContext instanceof Activity)) {
            ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.18(this, str));
          }
        }
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onInitInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onInitInterstitialSuccess()");
      SupersonicWebView.b(SupersonicWebView.this, "onInitInterstitialSuccess", "true");
      SupersonicWebView.F(SupersonicWebView.this).setInterstitialInitSuccess(true);
      if (SupersonicWebView.F(SupersonicWebView.this).reportInitInterstitial())
      {
        SupersonicWebView.F(SupersonicWebView.this).setReportInitInterstitial(false);
        if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString()))
        {
          paramString = SupersonicWebView.j(SupersonicWebView.this);
          if ((paramString instanceof Activity)) {
            ((Activity)paramString).runOnUiThread(new SupersonicWebView.c.17(this));
          }
        }
      }
    }
    
    @JavascriptInterface
    public void onInitOfferWallFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onInitOfferWallFail(" + paramString + ")");
      SupersonicWebView.F(SupersonicWebView.this).setOfferwallInitSuccess(false);
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      if (SupersonicWebView.F(SupersonicWebView.this).reportInitOfferwall())
      {
        SupersonicWebView.F(SupersonicWebView.this).setOfferwallReportInit(false);
        if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.OfferWall.toString()))
        {
          Context localContext = SupersonicWebView.j(SupersonicWebView.this);
          if ((localContext instanceof Activity)) {
            ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.4(this, str));
          }
        }
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onInitOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitOfferWallSuccess(String paramString)
    {
      SupersonicWebView.b(SupersonicWebView.this, "onInitOfferWallSuccess", "true");
      SupersonicWebView.F(SupersonicWebView.this).setOfferwallInitSuccess(true);
      if (SupersonicWebView.F(SupersonicWebView.this).reportInitOfferwall())
      {
        SupersonicWebView.F(SupersonicWebView.this).setOfferwallReportInit(false);
        if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.OfferWall.toString()))
        {
          paramString = SupersonicWebView.j(SupersonicWebView.this);
          if ((paramString instanceof Activity)) {
            ((Activity)paramString).runOnUiThread(new SupersonicWebView.c.3(this));
          }
        }
      }
    }
    
    @JavascriptInterface
    public void onLoadInterstitialFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onLoadInterstitialFail(" + paramString + ")");
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString()))
      {
        paramString = SupersonicWebView.j(SupersonicWebView.this);
        if ((paramString instanceof Activity)) {
          ((Activity)paramString).runOnUiThread(new SupersonicWebView.c.6(this, str));
        }
      }
      SupersonicWebView.b(SupersonicWebView.this, "onLoadInterstitialFail", "true");
    }
    
    @JavascriptInterface
    public void onLoadInterstitialSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onLoadInterstitialSuccess(" + paramString + ")");
      a(true);
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString()))
      {
        paramString = SupersonicWebView.j(SupersonicWebView.this);
        if ((paramString instanceof Activity)) {
          ((Activity)paramString).runOnUiThread(new SupersonicWebView.c.5(this));
        }
      }
      SupersonicWebView.b(SupersonicWebView.this, "onLoadInterstitialSuccess", "true");
    }
    
    @JavascriptInterface
    public void onOfferWallGeneric(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onOfferWallGeneric(" + paramString + ")");
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.OfferWall.toString())) {
        SupersonicWebView.B(SupersonicWebView.this).onOWGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onRewardedVideoGeneric(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onRewardedVideoGeneric(" + paramString + ")");
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.BrandConnect.toString())) {
        SupersonicWebView.r(SupersonicWebView.this).onRVGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onShowBrandConnectFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onShowBrandConnectFail(" + paramString + ")");
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.BrandConnect.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.14(this, str));
        }
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onShowBrandConnectFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowBrandConnectSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onShowBrandConnectSuccess(" + paramString + ")");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onShowBrandConnectSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onShowInterstitialFail(" + paramString + ")");
      a(false);
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.7(this, str));
        }
      }
      SupersonicWebView.b(SupersonicWebView.this, "onShowInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onShowInterstitialSuccess(" + paramString + ")");
      SupersonicWebView.F(SupersonicWebView.this).adOpened(SSAEnums.ProductType.Interstitial.ordinal());
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.Interstitial.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.2(this));
        }
        SupersonicWebView.b(SupersonicWebView.this, "onShowInterstitialSuccess", paramString);
      }
      a(false);
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onShowOfferWallFail(" + paramString + ")");
      String str = new com.supersonicads.sdk.data.d(paramString).i("errMsg");
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.16(this, str));
        }
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onShowOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowOfferWallSuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onShowOfferWallSuccess(" + paramString + ")");
      SupersonicWebView.F(SupersonicWebView.this).adOpened(SSAEnums.ProductType.OfferWall.ordinal());
      if (SupersonicWebView.e(SupersonicWebView.this, SSAEnums.ProductType.OfferWall.toString()))
      {
        Context localContext = SupersonicWebView.j(SupersonicWebView.this);
        if ((localContext instanceof Activity)) {
          ((Activity)localContext).runOnUiThread(new SupersonicWebView.c.15(this));
        }
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      SupersonicWebView.b(SupersonicWebView.this, "onShowOfferWallSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onUDIAFail(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onUDIASuccess(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onVideoStatusChanged(String paramString)
    {
      Log.d(SupersonicWebView.a(SupersonicWebView.this), "onVideoStatusChanged(" + paramString + ")");
      paramString = new com.supersonicads.sdk.data.d(paramString);
      String str = paramString.i("productType");
      if ((SupersonicWebView.N(SupersonicWebView.this) != null) && (!TextUtils.isEmpty(str)) && (SSAEnums.ProductType.BrandConnect.toString().equalsIgnoreCase(str)))
      {
        paramString = paramString.i("status");
        if ("started".equalsIgnoreCase(paramString)) {
          SupersonicWebView.N(SupersonicWebView.this).b();
        }
      }
      else
      {
        return;
      }
      if ("paused".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.N(SupersonicWebView.this).c();
        return;
      }
      if ("playing".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.N(SupersonicWebView.this).d();
        return;
      }
      if ("ended".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.N(SupersonicWebView.this).e();
        return;
      }
      if ("stopped".equalsIgnoreCase(paramString))
      {
        SupersonicWebView.N(SupersonicWebView.this).f();
        return;
      }
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "onVideoStatusChanged: unknown status: " + paramString);
    }
    
    @JavascriptInterface
    public void openUrl(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "openUrl(" + paramString + ")");
      Object localObject1 = new com.supersonicads.sdk.data.d(paramString);
      paramString = ((com.supersonicads.sdk.data.d)localObject1).i("url");
      Object localObject2 = ((com.supersonicads.sdk.data.d)localObject1).i("method");
      localObject1 = SupersonicWebView.j(SupersonicWebView.this);
      if (((String)localObject2).equalsIgnoreCase("external_browser")) {
        ((Context)localObject1).startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(paramString)));
      }
      do
      {
        return;
        if (((String)localObject2).equalsIgnoreCase("webview"))
        {
          localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
          ((Intent)localObject2).putExtra(SupersonicWebView.e, paramString);
          ((Intent)localObject2).putExtra(SupersonicWebView.f, true);
          ((Context)localObject1).startActivity((Intent)localObject2);
          return;
        }
      } while (!((String)localObject2).equalsIgnoreCase("store"));
      localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
      ((Intent)localObject2).putExtra(SupersonicWebView.e, paramString);
      ((Intent)localObject2).putExtra(SupersonicWebView.b, true);
      ((Intent)localObject2).putExtra(SupersonicWebView.f, true);
      ((Context)localObject1).startActivity((Intent)localObject2);
    }
    
    @JavascriptInterface
    public void removeCloseEventHandler(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "removeCloseEventHandler(" + paramString + ")");
      if (SupersonicWebView.e(SupersonicWebView.this) != null) {
        SupersonicWebView.e(SupersonicWebView.this).cancel();
      }
      SupersonicWebView.a(SupersonicWebView.this, true);
    }
    
    @JavascriptInterface
    public void saveFile(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "saveFile(" + paramString + ")");
      com.supersonicads.sdk.data.c localC = new com.supersonicads.sdk.data.c(paramString);
      if (com.supersonicads.sdk.c.d.a(SupersonicWebView.j(SupersonicWebView.this), SupersonicWebView.g(SupersonicWebView.this)) <= 0L)
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "no_disk_space", null);
        return;
      }
      if (!g.a())
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "sotrage_unavailable", null);
        return;
      }
      if (g.a(SupersonicWebView.g(SupersonicWebView.this), localC))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "file_already_exist", null);
        return;
      }
      if (!com.supersonicads.sdk.c.d.f(SupersonicWebView.j(SupersonicWebView.this)))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "no_network_connection", null);
        return;
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
      paramString = localC.d();
      if (paramString != null)
      {
        String str2 = String.valueOf(paramString);
        if (!TextUtils.isEmpty(str2))
        {
          String str1 = localC.b();
          paramString = str1;
          if (str1.contains("/"))
          {
            paramString = localC.b().split("/");
            paramString = paramString[(paramString.length - 1)];
          }
          f.a().c(paramString, str2);
        }
      }
      SupersonicWebView.G(SupersonicWebView.this).a(localC);
    }
    
    @JavascriptInterface
    public void setBackButtonState(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "setBackButtonState(" + paramString + ")");
      paramString = new com.supersonicads.sdk.data.d(paramString).i("state");
      f.a().a(paramString);
    }
    
    @JavascriptInterface
    public void setForceClose(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "setForceClose(" + paramString + ")");
      paramString = new com.supersonicads.sdk.data.d(paramString);
      String str1 = paramString.i("width");
      String str2 = paramString.i("height");
      SupersonicWebView.a(SupersonicWebView.this, Integer.parseInt(str1));
      SupersonicWebView.b(SupersonicWebView.this, Integer.parseInt(str2));
      SupersonicWebView.f(SupersonicWebView.this, paramString.i("position"));
    }
    
    @JavascriptInterface
    public void setOrientation(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "setOrientation(" + paramString + ")");
      paramString = new com.supersonicads.sdk.data.d(paramString).i("orientation");
      SupersonicWebView.this.setOrientationState(paramString);
      int i = com.supersonicads.sdk.c.d.a(SupersonicWebView.j(SupersonicWebView.this));
      if (SupersonicWebView.J(SupersonicWebView.this) != null) {
        SupersonicWebView.J(SupersonicWebView.this).a(paramString, i);
      }
    }
    
    @JavascriptInterface
    public void setStoreSearchKeys(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "setStoreSearchKeys(" + paramString + ")");
      f.a().b(paramString);
    }
    
    @JavascriptInterface
    public void setUserData(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "setUserData(" + paramString + ")");
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      if (!((com.supersonicads.sdk.data.d)localObject).f("key"))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "key does not exist", null);
        return;
      }
      if (!((com.supersonicads.sdk.data.d)localObject).f("value"))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "value does not exist", null);
        return;
      }
      String str = ((com.supersonicads.sdk.data.d)localObject).i("key");
      localObject = ((com.supersonicads.sdk.data.d)localObject).i("value");
      if (f.a().a(str, (String)localObject))
      {
        paramString = SupersonicWebView.a(SupersonicWebView.this, paramString);
        str = SupersonicWebView.a(SupersonicWebView.this, str, (String)localObject, null, null, null, null, null, null, null, false);
        paramString = SupersonicWebView.c(SupersonicWebView.this, paramString, str);
        SupersonicWebView.c(SupersonicWebView.this, paramString);
        return;
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, false, "SetUserData failed writing to shared preferences", null);
    }
    
    @JavascriptInterface
    public void setUserUniqueId(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "setUserUniqueId(" + paramString + ")");
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      if ((!((com.supersonicads.sdk.data.d)localObject).f("userUniqueId")) || (!((com.supersonicads.sdk.data.d)localObject).f("productType")))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "uniqueId or productType does not exist", null);
        return;
      }
      String str = ((com.supersonicads.sdk.data.d)localObject).i("userUniqueId");
      localObject = ((com.supersonicads.sdk.data.d)localObject).i("productType");
      if (f.a().b(str, (String)localObject))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, true, null, null);
        return;
      }
      SupersonicWebView.a(SupersonicWebView.this, paramString, false, "setUserUniqueId failed", null);
    }
    
    @JavascriptInterface
    public void setWebviewBackgroundColor(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "setWebviewBackgroundColor(" + paramString + ")");
      SupersonicWebView.g(SupersonicWebView.this, paramString);
    }
    
    @JavascriptInterface
    public void toggleUDIA(String paramString)
    {
      com.supersonicads.sdk.c.c.a(SupersonicWebView.a(SupersonicWebView.this), "toggleUDIA(" + paramString + ")");
      Object localObject = new com.supersonicads.sdk.data.d(paramString);
      if (!((com.supersonicads.sdk.data.d)localObject).f("toggle")) {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "toggle key does not exist", null);
      }
      int i;
      do
      {
        return;
        i = Integer.parseInt(((com.supersonicads.sdk.data.d)localObject).i("toggle"));
      } while (i == 0);
      localObject = Integer.toBinaryString(i);
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        SupersonicWebView.a(SupersonicWebView.this, paramString, false, "fialed to convert toggle", null);
        return;
      }
      if (localObject.toCharArray()[3] == '0')
      {
        f.a().a(true);
        return;
      }
      f.a().a(false);
    }
  }
  
  public static abstract interface d
  {
    public abstract void a();
    
    public abstract void a(String paramString, int paramInt);
  }
}
