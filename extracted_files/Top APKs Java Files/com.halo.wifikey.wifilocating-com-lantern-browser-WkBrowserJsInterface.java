package com.lantern.browser;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Toast;
import com.bluefay.b.f;
import com.lantern.core.g;
import com.lantern.core.i;
import com.lantern.core.k;
import com.lantern.core.location.d;
import com.snda.wifilocating.wxapi.WXEntryActivity;
import com.snda.wifilocating.wxapi.WkWeiXinUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WkBrowserJsInterface
{
  private static final String JSON_BSSID = "bssid";
  private static final String JSON_SSID = "ssid";
  public static final String PARAM_KEY_HID = "appHid";
  public static final String PARAM_KEY_PAGE_INDEX = "pageIndex";
  public static final String PARAM_KEY_POSITION = "prositon";
  public static final String PARAM_KEY_READABLE_ID = "readableId";
  public static final String PARAM_KEY_SOURCE = "source";
  private static final String TAG = "JsInterfaceUtils";
  private static com.lantern.core.location.b mLocCallback;
  private static String mLocCb = "";
  private static WebView mLocWebView = null;
  private static Handler mMainHandler;
  private static String mRegisterCb = "";
  private static WebView mRegisterWebview = null;
  
  public WkBrowserJsInterface()
  {
    mMainHandler = new Handler(Looper.getMainLooper());
    mLocCallback = new v(this);
  }
  
  public static void activityForResult(int paramInt)
  {
    if (!TextUtils.isEmpty(mRegisterCb))
    {
      if (!((WkBrowserWebView)mRegisterWebview).f()) {
        loadJs(mRegisterWebview, mRegisterCb + "();");
      }
      mRegisterWebview = null;
      mRegisterCb = null;
    }
  }
  
  public static void appState(WebView paramWebView, String paramString1, String paramString2)
  {
    if (bb.a(paramWebView)) {}
    while (TextUtils.isEmpty(paramString2)) {
      return;
    }
    JSONArray localJSONArray1 = new JSONArray();
    JSONArray localJSONArray2 = new JSONArray();
    String str2 = "," + paramString2 + ",";
    PackageManager localPackageManager = paramWebView.getContext().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    JSONObject localJSONObject;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        paramString2 = localPackageInfo.packageName;
        if (str2.indexOf("," + paramString2 + ",") != -1)
        {
          localJSONObject = new JSONObject();
          try
          {
            localJSONArray1.put(paramString2);
            localJSONObject.put("pkg", paramString2);
            localJSONObject.put("vcode", localPackageInfo.versionCode);
            localJSONObject.put("vname", localPackageInfo.versionName);
            if ((localPackageInfo.applicationInfo.flags & 0x1) > 0) {
              localJSONObject.put("issys", true);
            }
          }
          catch (Exception paramString2)
          {
            label209:
            String str1;
            label227:
            f.a(paramString2);
          }
          try
          {
            str1 = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
            paramString2 = str1;
          }
          catch (Exception localException)
          {
            f.a(localException);
            break label227;
          }
          if ((paramString2 != null) && (!paramString2.equalsIgnoreCase(""))) {
            break label402;
          }
          paramString2 = localPackageInfo.applicationInfo.packageName;
        }
      }
    }
    label402:
    for (;;)
    {
      localJSONObject.put("name", paramString2);
      localJSONArray2.put(localJSONObject);
      break;
      break;
      localJSONObject.put("issys", false);
      break label209;
      if (localJSONArray1.length() > 0) {
        try
        {
          f.a("JsInterfaceUtils", new Object[] { localJSONArray2.toString() });
          loadJs(paramWebView, paramString1 + "(" + localJSONArray1.toString() + "," + localJSONArray2.toString() + ");");
          return;
        }
        catch (Exception paramWebView)
        {
          f.a(paramWebView);
          return;
        }
      }
      loadJs(paramWebView, paramString1 + "([],[]);");
      return;
    }
  }
  
  public static void browser(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    try
    {
      paramString = new Intent("wifi.intent.action.BROWSER", Uri.parse(paramString.replaceAll(" ", "%20")));
      paramString.addFlags(268435456);
      paramString.setPackage(paramWebView.getContext().getPackageName());
      paramWebView.getContext().startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramWebView) {}
  }
  
  public static void cancelListener(WebView paramWebView)
  {
    f.a("cancelListener", new Object[0]);
    if (bb.a(paramWebView)) {
      return;
    }
    mLocCb = null;
    mLocWebView = null;
    d.a(paramWebView.getContext()).a(mLocCallback);
  }
  
  public static void closeBannerAd(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.h();
          return;
        }
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
      }
    }
  }
  
  public static void closeBrowser(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.k();
          return;
        }
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
      }
    }
  }
  
  public static void cltInfo(WebView paramWebView, String paramString)
  {
    cltInfo(paramWebView, paramString, "");
  }
  
  public static void cltInfo(WebView paramWebView, String paramString1, String paramString2)
  {
    int i = 0;
    if (bb.a(paramWebView)) {
      return;
    }
    String[] arrayOfString = new String[10];
    arrayOfString[0] = "vcode";
    arrayOfString[1] = "vname";
    arrayOfString[2] = "chanid";
    arrayOfString[3] = "appid";
    arrayOfString[4] = "uhid";
    arrayOfString[5] = "dhid";
    arrayOfString[6] = "ph";
    arrayOfString[7] = "nick";
    arrayOfString[8] = "ii";
    arrayOfString[9] = "mac";
    Context localContext = paramWebView.getContext();
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put("vcode", String.valueOf(g.b(localContext)));
      localJSONObject1.put("vname", g.a(localContext));
      localJSONObject1.put("chanid", g.l(localContext));
      localJSONObject1.put("appid", com.lantern.core.a.j().i());
      localJSONObject1.put("uhid", k.c(""));
      localJSONObject1.put("dhid", k.b(""));
      localJSONObject1.put("ph", "");
      localJSONObject1.put("nick", "");
      localJSONObject1.put("ii", g.c(localContext));
      localJSONObject1.put("mac", g.e(localContext));
      f.a("cltInfo info.toString():" + localJSONObject1.toString(), new Object[0]);
      if (TextUtils.isEmpty(paramString2))
      {
        loadJs(paramWebView, paramString1 + "(" + localJSONObject1.toString() + ")");
        return;
      }
    }
    catch (JSONException localJSONException1)
    {
      for (;;)
      {
        f.a(localJSONException1);
      }
      JSONObject localJSONObject2 = new JSONObject();
      paramString2 = "," + paramString2 + ",";
      for (;;)
      {
        if (i < 10)
        {
          if (paramString2.indexOf(arrayOfString[i]) != -1) {}
          try
          {
            localJSONObject2.put(arrayOfString[i], localJSONObject1.get(arrayOfString[i]));
            i += 1;
          }
          catch (JSONException localJSONException2)
          {
            for (;;)
            {
              f.a(localJSONException2);
            }
          }
        }
      }
      if (localJSONObject2.length() <= 0)
      {
        loadJs(paramWebView, paramString1 + "({})");
        return;
      }
      loadJs(paramWebView, paramString1 + "(" + localJSONObject2.toString() + ")");
    }
  }
  
  private static Map<String, String> decodeParams(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    }
    HashMap localHashMap;
    for (;;)
    {
      return paramString;
      localHashMap = new HashMap();
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        if (localJSONObject.has("tyep")) {
          localHashMap.put("type", localJSONObject.getString("type"));
        }
        paramString = localHashMap;
        if (localJSONObject.has("data"))
        {
          localHashMap.put("data", localJSONObject.getString("data"));
          return localHashMap;
        }
      }
      catch (Exception paramString)
      {
        f.a(paramString);
      }
    }
    return localHashMap;
  }
  
  public static void getBbxDetailHtml(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    if ((!TextUtils.isEmpty(paramString)) && (paramString.contains("WiFikey_bbx")))
    {
      com.lantern.analytics.a.e().a("bdload1");
      return;
    }
    com.lantern.analytics.a.e().a("bdload2");
  }
  
  public static void getBbxHtml(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    if ((!TextUtils.isEmpty(paramString)) && (paramString.contains("WiFikey_bbx")))
    {
      com.lantern.analytics.a.e().a("bcload1");
      return;
    }
    com.lantern.analytics.a.e().a("bload2");
  }
  
  public static void getHtml(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    paramWebView = (WkBrowserWebView)paramWebView;
    if ((!TextUtils.isEmpty(paramString)) && ((paramString.contains("javascript:WiFikey.openOrBrowse")) || (paramString.contains("/feed.css"))))
    {
      paramWebView.a(true);
      return;
    }
    paramWebView.a(false);
  }
  
  public static String getLaLo(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return "";
    }
    paramWebView = com.lantern.core.a.j();
    if (paramWebView != null) {
      return paramWebView.m() + "," + paramWebView.c() + "," + paramWebView.d();
    }
    f.a("getLaLo null", new Object[0]);
    return "";
  }
  
  public static int getOsVer(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return -1;
    }
    return Build.VERSION.SDK_INT;
  }
  
  public static String getShareData(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      return "";
    }
    if (!TextUtils.isEmpty(paramString)) {
      return PreferenceManager.getDefaultSharedPreferences(paramWebView.getContext()).getString(paramString, "");
    }
    return "";
  }
  
  public static String getUserInfo(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      paramWebView = "";
    }
    Object localObject;
    do
    {
      return paramWebView;
      localObject = new HashMap();
      ((HashMap)localObject).put("vcode", String.valueOf(g.b(paramWebView.getContext())));
      ((HashMap)localObject).put("vname", g.a(paramWebView.getContext()));
      ((HashMap)localObject).put("chanid", g.l(paramWebView.getContext()));
      ((HashMap)localObject).put("appid", com.lantern.core.a.j().i());
      ((HashMap)localObject).put("uhid", k.c(""));
      localObject = (String)((HashMap)localObject).get(paramString);
      f.a("getUserInfo key:" + paramString + " ret:" + (String)localObject, new Object[0]);
      paramWebView = (WebView)localObject;
    } while (!TextUtils.isEmpty((CharSequence)localObject));
    return "";
  }
  
  public static String getWifiScanResult(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return "";
    }
    JSONArray localJSONArray;
    try
    {
      localJSONArray = new JSONArray();
      paramWebView = ((WifiManager)paramWebView.getContext().getSystemService("wifi")).getScanResults();
      if ((paramWebView != null) && (paramWebView.size() > 0)) {
        paramWebView = paramWebView.iterator();
      }
      while (paramWebView.hasNext())
      {
        ScanResult localScanResult = (ScanResult)paramWebView.next();
        JSONObject localJSONObject = new JSONObject();
        if ((!TextUtils.isEmpty(localScanResult.SSID)) && (!TextUtils.isEmpty(localScanResult.BSSID)))
        {
          localJSONObject.put("ssid", localScanResult.SSID);
          localJSONObject.put("bssid", localScanResult.BSSID);
          localJSONArray.put(localJSONObject);
          continue;
          return "";
        }
      }
    }
    catch (JSONException paramWebView)
    {
      f.a(paramWebView);
    }
    while (localJSONArray.length() <= 0) {}
    paramWebView = localJSONArray.toString();
    return paramWebView;
  }
  
  public static String getcltInfo(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      return "";
    }
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    if (paramString.equals("vcode")) {
      return String.valueOf(g.b(paramWebView.getContext()));
    }
    if (paramString.equals("vname")) {
      return g.a(paramWebView.getContext());
    }
    if (paramString.equals("chanid")) {
      return g.l(paramWebView.getContext());
    }
    if (paramString.equals("appid")) {
      return com.lantern.core.a.j().i();
    }
    if (paramString.equals("uhid")) {
      return k.c("");
    }
    if (paramString.equals("dhid")) {
      return k.b("");
    }
    if (paramString.equals("ii")) {
      return g.c(paramWebView.getContext());
    }
    if (paramString.equals("mac")) {
      return g.e(paramWebView.getContext());
    }
    if (paramString.equals("ssid"))
    {
      paramWebView = bb.a(paramWebView.getContext());
      f.a("getcltInfo ssid:" + paramWebView, new Object[0]);
      return paramWebView;
    }
    if (paramString.equals("bssid"))
    {
      paramWebView = bb.b(paramWebView.getContext());
      f.a("getcltInfo bssid:" + paramWebView, new Object[0]);
      return paramWebView;
    }
    if (paramString.equals("ph")) {
      return k.b(paramWebView.getContext());
    }
    if (paramString.equals("nick")) {
      return k.c(paramWebView.getContext());
    }
    if ("osver".equals(paramString)) {
      return String.valueOf(Build.VERSION.SDK_INT);
    }
    if ("netmode".equals(paramString)) {
      return g.n(paramWebView.getContext());
    }
    if ("simop".equals(paramString)) {
      return bb.e(paramWebView.getContext());
    }
    if ("manufacturer".equals(paramString)) {
      return Build.MANUFACTURER;
    }
    if ("osvername".equals(paramString)) {
      return Build.VERSION.RELEASE;
    }
    if ("model".equals(paramString)) {
      return Build.MODEL;
    }
    if ("device".equals(paramString)) {
      return Build.DEVICE;
    }
    if ("brand".equals(paramString)) {
      return Build.BRAND;
    }
    if ("product".equals(paramString)) {
      return Build.PRODUCT;
    }
    if ("androidid".equals(paramString)) {
      return g.i(paramWebView.getContext());
    }
    return "";
  }
  
  public static void hideActionBar(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.j();
          return;
        }
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
      }
    }
  }
  
  public static void hideInputKeyBoard(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    try
    {
      ((InputMethodManager)paramWebView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(paramWebView.getWindowToken(), 0);
      return;
    }
    catch (Exception paramWebView)
    {
      f.a(paramWebView);
    }
  }
  
  public static void hideOptionMenu(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.j();
          return;
        }
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
      }
    }
  }
  
  public static boolean isAppInstalled(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return true;
      try
      {
        paramWebView = paramWebView.getContext().getPackageManager().getPackageInfo(paramString, 0);
        if (paramWebView != null) {
          continue;
        }
        return false;
      }
      catch (PackageManager.NameNotFoundException paramWebView)
      {
        for (;;)
        {
          paramWebView = null;
        }
      }
    }
  }
  
  private static boolean isFakeUser()
  {
    String str = k.c("");
    return ("a0000000000000000000000000000001".equalsIgnoreCase(str)) || (TextUtils.isEmpty(str));
  }
  
  public static String isGuest(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return "";
    }
    if (isFakeUser()) {
      return "1";
    }
    return "-1";
  }
  
  public static boolean isWXAppInstalledAndSupported(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return false;
    }
    return WkWeiXinUtil.isWXAppInstalledAndSupported();
  }
  
  private static void loadJs(WebView paramWebView, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return;
      f.a("JsInterfaceUtils", new Object[] { paramString });
    } while (paramWebView == null);
    try
    {
      mMainHandler.post(new x(paramWebView, paramString));
      return;
    }
    catch (Exception paramWebView)
    {
      f.a(paramWebView);
    }
  }
  
  public static void locationListener(WebView paramWebView, String paramString)
  {
    f.a("locationListener cb:" + paramString, new Object[0]);
    if (bb.a(paramWebView)) {
      return;
    }
    mLocCb = paramString;
    mLocWebView = paramWebView;
    d.a(paramWebView.getContext()).b(mLocCallback);
  }
  
  public static void onCompletedLatestNews(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.f();
          return;
        }
      }
      catch (Exception paramWebView) {}
    }
  }
  
  public static void onLoading(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.g();
          return;
        }
      }
      catch (Exception paramWebView) {}
    }
  }
  
  public static void openAppDetail(WebView paramWebView, String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    try
    {
      Intent localIntent = new Intent("wifi.intent.action.APPSTORE_DETAIL_MAIN");
      localIntent.putExtra("appHid", paramString1);
      localIntent.putExtra("readableId", paramString2);
      localIntent.putExtra("pageIndex", paramInt1);
      localIntent.putExtra("prositon", paramInt2);
      localIntent.putExtra("source", paramString3);
      localIntent.addFlags(268435456);
      localIntent.setPackage(paramWebView.getContext().getPackageName());
      paramWebView.getContext().startActivity(localIntent);
      return;
    }
    catch (Exception paramWebView) {}
  }
  
  public static void openAppWall(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    try
    {
      Intent localIntent = new Intent("wifi.intent.action.APPSTORE_MAIN");
      localIntent.setPackage(paramWebView.getContext().getPackageName());
      localIntent.addFlags(268435456);
      paramWebView.getContext().startActivity(localIntent);
      return;
    }
    catch (Exception paramWebView) {}
  }
  
  public static void openOrBrowse(WebView paramWebView, String paramString1, String paramString2)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    if (!TextUtils.isEmpty(paramString1))
    {
      Context localContext = paramWebView.getContext();
      try
      {
        PackageInfo localPackageInfo = localContext.getPackageManager().getPackageInfo(paramString1, 0);
        if (localPackageInfo != null) {
          try
          {
            localContext.startActivity(localContext.getPackageManager().getLaunchIntentForPackage(paramString1));
            return;
          }
          catch (Exception paramWebView)
          {
            return;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Object localObject = null;
        }
      }
    }
    browser(paramWebView, paramString2);
  }
  
  public static void reRegister(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {}
    while ((!TextUtils.isEmpty(k.c(""))) && (!isFakeUser())) {
      return;
    }
    mRegisterWebview = paramWebView;
    mRegisterCb = paramString;
    try
    {
      paramString = new Intent("wifi.intent.action.ADD_ACCOUNT_MAIN");
      paramString.addFlags(268435456);
      paramString.setPackage(paramWebView.getContext().getPackageName());
      paramWebView.getContext().startActivity(paramString);
      return;
    }
    catch (Exception paramWebView) {}
  }
  
  private static void runJavaScriptMethord(WebView paramWebView, String paramString, String... paramVarArgs)
  {
    paramString = "javascript:" + paramString + "('";
    int i = 0;
    while (i < paramVarArgs.length)
    {
      String str = paramVarArgs[i];
      str = paramString + str;
      paramString = str;
      if (i != paramVarArgs.length - 1) {
        paramString = str + ", ";
      }
      i += 1;
    }
    paramString = paramString + "')";
    try
    {
      paramWebView.loadUrl(paramString);
      return;
    }
    catch (Exception paramWebView)
    {
      f.a(paramWebView);
    }
  }
  
  public static void sendMessageTo(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        if (!TextUtils.isEmpty(paramString))
        {
          Object localObject = new JSONObject(paramString);
          paramString = ((JSONObject)localObject).getString("subject");
          String str = ((JSONObject)localObject).getString("content");
          localObject = ((JSONObject)localObject).getString("title");
          mMainHandler.post(new aa(paramString, str, paramWebView, (String)localObject));
          return;
        }
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
      }
    }
  }
  
  public static void sendSMS(WebView paramWebView, String paramString1, String paramString2)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        SmsManager localSmsManager = SmsManager.getDefault();
        paramWebView = PendingIntent.getBroadcast(paramWebView.getContext(), 0, new Intent(), 0);
        if (paramString2.length() >= 70)
        {
          paramString2 = localSmsManager.divideMessage(paramString2).iterator();
          while (paramString2.hasNext()) {
            localSmsManager.sendTextMessage(paramString1, null, (String)paramString2.next(), paramWebView, null);
          }
        }
        localSmsManager.sendTextMessage(paramString1, null, paramString2, paramWebView, null);
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
        return;
      }
    }
  }
  
  public static void sendSMSWithUI(WebView paramWebView, String paramString1, String paramString2)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    try
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("smsto:" + paramString1));
      paramString1.putExtra("sms_body", paramString2);
      paramWebView.getContext().startActivity(paramString1);
      return;
    }
    catch (Exception paramWebView)
    {
      f.a(paramWebView);
    }
  }
  
  public static void sendWeixinOrSNS(WebView paramWebView, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    if (!WkWeiXinUtil.isWXAppInstalledAndSupported())
    {
      Toast.makeText(paramWebView.getContext(), R.string.browser_weixin_tips, 0).show();
      return;
    }
    WkWeiXinUtil.shareToWeiXin(paramInt, paramString3, paramString1, paramString2, paramString4);
    WXEntryActivity.setListener(new z(paramInt));
    paramWebView = new HashMap();
    paramWebView.put("src", "web");
    paramWebView.put("title", paramString1);
    paramWebView.put("url", paramString3);
    paramWebView = new JSONObject(paramWebView).toString();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      com.lantern.analytics.a.e().a("cht", paramWebView);
      return;
    case 1: 
      com.lantern.analytics.a.e().a("mmt", paramWebView);
      return;
    }
    com.lantern.analytics.a.e().a("favo", paramWebView);
  }
  
  public static void setOrientation(WebView paramWebView, int paramInt)
  {
    if (bb.a(paramWebView)) {}
    do
    {
      do
      {
        return;
        paramWebView = (WkBrowserWebView)paramWebView;
      } while (paramWebView.a() == null);
      paramWebView = paramWebView.a().getActivity();
    } while ((paramWebView == null) || (paramWebView.getRequestedOrientation() == paramInt));
    try
    {
      paramWebView.setRequestedOrientation(paramInt);
      return;
    }
    catch (Exception paramWebView)
    {
      f.a(paramWebView);
    }
  }
  
  public static void setShareData(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {}
    while (TextUtils.isEmpty(paramString)) {
      return;
    }
    paramString = com.lantern.webox.d.b.a(paramString);
    paramWebView = PreferenceManager.getDefaultSharedPreferences(paramWebView.getContext()).edit();
    paramString = paramString.entrySet().iterator();
    while (paramString.hasNext()) {
      try
      {
        Map.Entry localEntry = (Map.Entry)paramString.next();
        paramWebView.putString((String)localEntry.getKey(), String.valueOf(localEntry.getValue()));
      }
      catch (Exception localException)
      {
        f.a(localException);
      }
    }
    paramWebView.commit();
  }
  
  public static void shareToWeiXin(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {}
    do
    {
      for (;;)
      {
        return;
        try
        {
          if (!WkWeiXinUtil.isWXAppInstalledAndSupported())
          {
            Toast.makeText(paramWebView.getContext(), R.string.browser_weixin_tips, 0).show();
            if (TextUtils.isEmpty(paramString)) {
              continue;
            }
            paramString = new JSONObject(paramString);
            if (!paramString.has("callback")) {
              continue;
            }
            runJavaScriptMethord(paramWebView, paramString.getString("callback"), new String[] { "-2" });
          }
        }
        catch (Exception paramWebView)
        {
          f.a(paramWebView);
          return;
        }
      }
    } while (TextUtils.isEmpty(paramString));
    new Thread(new ab(paramString, paramWebView)).start();
  }
  
  public static void shareToWeixinCallback(WebView paramWebView, String paramString1, String paramString2, int paramInt)
  {
    new Thread(new ae(paramWebView.getContext(), paramString1, paramWebView, paramInt, paramString2)).start();
  }
  
  public static void showActionBar(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.i();
          return;
        }
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
      }
    }
  }
  
  public static void showInputKeyBoard(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    try
    {
      ((InputMethodManager)paramWebView.getContext().getSystemService("input_method")).toggleSoftInput(2, 0);
      return;
    }
    catch (Exception paramWebView)
    {
      f.a(paramWebView);
    }
  }
  
  public static void showOptionMenu(WebView paramWebView)
  {
    if (bb.a(paramWebView)) {}
    for (;;)
    {
      return;
      try
      {
        paramWebView = ((WkBrowserWebView)paramWebView).a();
        if (paramWebView != null)
        {
          paramWebView.i();
          return;
        }
      }
      catch (Exception paramWebView)
      {
        f.a(paramWebView);
      }
    }
  }
  
  public static String signParams(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {}
    do
    {
      return "";
      paramWebView = com.lantern.core.a.j();
    } while (paramWebView == null);
    HashMap localHashMap = new HashMap();
    Object localObject = paramWebView.o();
    if (((HashMap)localObject).size() > 0)
    {
      localObject = ((HashMap)localObject).entrySet().iterator();
      while (((Iterator)localObject).hasNext()) {
        try
        {
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
          localHashMap.put((String)localEntry.getKey(), (String)localEntry.getValue());
        }
        catch (Exception localException2)
        {
          f.a(localException2);
        }
      }
    }
    paramString = com.lantern.webox.d.b.a(paramString);
    if ((paramString != null) && (paramString.size() > 0))
    {
      paramString = paramString.entrySet().iterator();
      while (paramString.hasNext()) {
        try
        {
          localObject = (Map.Entry)paramString.next();
          localHashMap.put((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue());
        }
        catch (Exception localException1)
        {
          f.a(localException1);
        }
      }
    }
    return s.b(paramWebView.a("00300305", localHashMap));
  }
  
  public static void startActivity(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {
      return;
    }
    try
    {
      paramString = new Intent(paramString);
      paramString.setPackage(paramWebView.getContext().getPackageName());
      paramString.addFlags(268435456);
      paramWebView.getContext().startActivity(paramString);
      return;
    }
    catch (Exception paramWebView)
    {
      f.a(paramWebView);
    }
  }
  
  public static void trace(WebView paramWebView, String paramString)
  {
    if (bb.a(paramWebView)) {}
    do
    {
      return;
      paramString = decodeParams(paramString);
    } while (paramString == null);
    paramWebView = (String)paramString.get("type");
    paramString = (String)paramString.get("data");
    com.lantern.analytics.a.e().a(paramWebView, paramString);
  }
  
  public static void viewAppInMarket(WebView paramWebView, String paramString)
  {
    f.a("JsInterfaceUtils", new Object[] { "viewAppInMarket:" + paramString });
    if (bb.a(paramWebView)) {
      return;
    }
    mMainHandler.post(new y(paramString, paramWebView));
  }
}
