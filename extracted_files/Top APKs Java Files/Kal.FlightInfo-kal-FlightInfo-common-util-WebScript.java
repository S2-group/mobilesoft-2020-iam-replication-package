package kal.FlightInfo.common.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Process;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import kal.FlightInfo.activities.MainActivity;
import kal.FlightInfo.activities.fragments.WebViewFragment;
import kal.FlightInfo.common.KalSession;
import kal.FlightInfo.common.views.KalWebView;

public class WebScript
{
  private static final String TAG = "WebScript";
  public static String afterSessionCheckUrl;
  public static String callBackUrl = "";
  private static int curBrightnessValue = 0;
  public static boolean isAfterPushpopup;
  public static boolean isAfterSessioncheckLoad;
  private static boolean isBeforeBoardingPassPage;
  public static boolean isCallUrl = false;
  public static boolean isLoginAfterLoad;
  public static String loginAfterUrl = "";
  
  static
  {
    isLoginAfterLoad = false;
    afterSessionCheckUrl = "";
    isAfterSessioncheckLoad = false;
    isAfterPushpopup = false;
    isBeforeBoardingPassPage = false;
  }
  
  public WebScript() {}
  
  private static String getString(Context paramContext, int paramInt)
  {
    return paramContext.getResources().getString(paramInt);
  }
  
  private static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static boolean isIspReturn(String paramString, MainActivity paramMainActivity)
  {
    Intent localIntent;
    if ((paramString.startsWith("http://")) || (paramString.startsWith("https://")))
    {
      if (paramString.equals("http://m.vguard.co.kr/card/vguard_webstandard.apk"))
      {
        localIntent = new Intent("android.intent.action.VIEW");
        localIntent.addCategory("android.intent.category.DEFAULT");
        localIntent.setData(Uri.parse("market://details?id=kr.co.shiftworks.vguardweb"));
        paramMainActivity.startActivity(localIntent);
      }
      paramMainActivity.getWebViewFragment().getWebView().loadUrl(paramString);
    }
    for (;;)
    {
      return true;
      try
      {
        localIntent = Intent.parseUri(paramString, 1);
        if (!paramString.startsWith("intent")) {
          break;
        }
        if (paramMainActivity.getPackageManager().resolveActivity(localIntent, 0) == null)
        {
          String str = localIntent.getPackage();
          if (str != null)
          {
            localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:" + str));
            try
            {
              paramMainActivity.startActivity(localIntent);
              return true;
            }
            catch (ActivityNotFoundException paramMainActivity)
            {
              LogControl.e("intent", paramString + ":" + paramMainActivity.getMessage());
              return false;
            }
          }
        }
        if ('Ư' != 43) {
          break label253;
        }
      }
      catch (URISyntaxException paramMainActivity)
      {
        LogControl.e("Browser", "Bad URI " + paramString + ":" + paramMainActivity.getMessage());
        return false;
      }
      paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localIntent.getDataString())));
      return true;
      label253:
      localIntent.addCategory("android.intent.category.BROWSABLE");
      localIntent.setComponent(null);
      try
      {
        boolean bool = paramMainActivity.startActivityIfNeeded(localIntent, -1);
        if (bool) {
          return true;
        }
      }
      catch (ActivityNotFoundException paramMainActivity)
      {
        LogControl.e("intent", paramString + ":" + paramMainActivity.getMessage());
        return false;
      }
    }
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      LogControl.e("Exception", paramString.getMessage());
    }
    catch (Exception paramString)
    {
      try
      {
        paramMainActivity.startActivity(paramString);
        return true;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      paramString = paramString;
    }
    return true;
  }
  
  public static boolean isTstoreExist(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int j = paramContext.size();
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (((ApplicationInfo)paramContext.get(i)).packageName.indexOf("com.skt.skaf.A000Z00040") != -1) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean runScript(MainActivity paramMainActivity, String paramString)
  {
    LogControl.i("WebScript", "Url in runScript : " + paramString);
    Object localObject1 = KalUtil.getCookieString(paramMainActivity);
    LogControl.i("WebScript", "Cookie in runScript  == " + (String)localObject1);
    String[] arrayOfString = paramString.split("://");
    Object localObject3 = "";
    Object localObject4 = "";
    Object localObject5 = null;
    if (arrayOfString.length > 0) {
      localObject3 = arrayOfString[0];
    }
    localObject1 = localObject5;
    if (arrayOfString.length > 1)
    {
      String str = arrayOfString[1].split("\\?")[0];
      LogControl.i("WebScript", "components[1] = " + arrayOfString[1]);
      localObject4 = str;
      localObject1 = localObject5;
      if (arrayOfString[1].indexOf("?") > 0)
      {
        localObject1 = arrayOfString[1].substring(arrayOfString[1].indexOf("?") + 1, arrayOfString[1].length()).split("\\|");
        LogControl.i("WebScript", "params = " + Arrays.toString((Object[])localObject1));
        localObject4 = str;
      }
    }
    if (paramString.contains("boarding-pass.html"))
    {
      LogControl.i("WebScript", "boarding-pass = " + paramString);
      KalUtil.curBrightnessValue = Settings.System.getInt(paramMainActivity.getContentResolver(), "screen_brightness", -1);
      KalUtil.setScreenBrightnessMaximum(paramMainActivity);
    }
    for (isBeforeBoardingPassPage = true;; isBeforeBoardingPassPage = false)
    {
      do
      {
        LogControl.i("WebScript", "scheme = " + (String)localObject3 + " / function = " + (String)localObject4);
        if (!"koreanair".equals(localObject3)) {
          break label446;
        }
        if (!"goSetting".equals(localObject4)) {
          break;
        }
        LoadingDialog.hideLoading();
        if (paramMainActivity != null) {
          paramMainActivity.goSetting();
        }
        return true;
      } while ((!isBeforeBoardingPassPage) || ("koreanair".equals(localObject3)));
      LogControl.i("WebScript", "boarding-pass = after " + paramString);
      KalUtil.setScreenBrightnessOriginal(paramMainActivity);
    }
    if ("doAutoLogin".equals(localObject4)) {
      LogControl.i("TEST", "doAutoLogin xxx ");
    }
    label446:
    label876:
    Object localObject2;
    for (;;)
    {
      try
      {
        callBackUrl = URLDecoder.decode(localObject1[0], "UTF-8");
        isCallUrl = true;
        paramMainActivity.getWebViewFragment().goMenuLinkLogin();
        localObject3 = paramMainActivity.getWebViewFragment().getWebView();
        localObject1 = ((TelephonyManager)paramMainActivity.getSystemService("phone")).getSimOperatorName();
        if (!paramString.startsWith("ispmobile://TID=")) {
          break label1553;
        }
        paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        try
        {
          paramMainActivity.startActivity(paramString);
          CookieHandler.setDefault(new java.net.CookieManager());
          CookieSyncManager.createInstance(paramMainActivity);
          paramString = android.webkit.CookieManager.getInstance().getCookie(paramMainActivity.getResources().getString(2131099747));
          LogControl.i("WebScript", "ispmobile://TID= : payment cookie store = " + paramString);
          PrefManager.setCookie(paramMainActivity, paramString);
          paramMainActivity.finish();
          Process.killProcess(Process.myPid());
          return true;
        }
        catch (ActivityNotFoundException paramString)
        {
          if (!((String)localObject2).equalsIgnoreCase("SKTelecom")) {
            break;
          }
        }
        localUnsupportedEncodingException = localUnsupportedEncodingException;
        localUnsupportedEncodingException.printStackTrace();
        continue;
      }
      if ("webViewReady".equals(localObject4))
      {
        LogControl.i("WebScript", "webViewReady URL " + paramString);
        LoadingDialog.hideLoading();
        paramMainActivity.getWebViewFragment().goSessionCallback();
      }
      else if ("hideProgressBar".equals(localObject4))
      {
        LoadingDialog.hideLoading();
      }
      else
      {
        if ("sessionCallback".equals(localObject4))
        {
          LogControl.i("TEST", "sessionCallback = " + localUnsupportedEncodingException[0]);
          if (localUnsupportedEncodingException[0].equals("true"))
          {
            KalSession.getInstace().isLogin = true;
            LogControl.i("TEST", "sessionCallback = KalSession.getInstace().isLogin is true");
            if (isAfterSessioncheckLoad)
            {
              isAfterSessioncheckLoad = false;
              paramMainActivity.loadUrl(afterSessionCheckUrl);
              afterSessionCheckUrl = "";
            }
            if (!KalSession.getInstace().isLogin) {
              break label876;
            }
            paramMainActivity.runOnUiThread(new Runnable()
            {
              public void run()
              {
                this.val$mainActivity.goSkypass();
                this.val$mainActivity.showMyPage();
                this.val$mainActivity.setCategoryListRefresh();
                this.val$mainActivity.setMyPageListRefresh();
              }
            });
          }
          for (;;)
          {
            return true;
            LogControl.i("TEST", "sessionCallback = KalSession.getInstace().isLogin is false");
            PrefManager.setEffectiveTo(paramMainActivity, "");
            if (paramMainActivity.getWebViewFragment().loginState())
            {
              if (isAfterSessioncheckLoad)
              {
                KalSession.getInstace().isLogin = false;
                isAfterSessioncheckLoad = false;
                paramMainActivity.getWebViewFragment().goMenuLinkLogin(afterSessionCheckUrl);
                afterSessionCheckUrl = "";
              }
              for (;;)
              {
                KalSession.getInstace().isLogin = true;
                break;
                LogControl.i("TEST", "sessionCallback = go login ");
                paramMainActivity.getWebViewFragment().goLogin();
              }
            }
            KalSession.getInstace().isLogin = false;
            if (!isAfterSessioncheckLoad) {
              break;
            }
            isAfterSessioncheckLoad = false;
            paramMainActivity.loadUrl(afterSessionCheckUrl);
            afterSessionCheckUrl = "";
            break;
            paramMainActivity.runOnUiThread(new Runnable()
            {
              public void run()
              {
                this.val$mainActivity.setCategoryListRefresh();
                this.val$mainActivity.setMyPageListRefresh();
                this.val$mainActivity.hideMyPage();
              }
            });
          }
        }
        if ("login".equals(localObject4))
        {
          PrefManager.setLogInType(paramMainActivity, localUnsupportedEncodingException[0]);
          paramMainActivity.login(localUnsupportedEncodingException[1]);
          PrefManager.setLogInID(paramMainActivity, localUnsupportedEncodingException[1]);
          PrefManager.setLogInPW(paramMainActivity, localUnsupportedEncodingException[2]);
          PrefManager.setAutoLogin(paramMainActivity, true);
          KalSession.getInstace().isLogin = true;
          KalSession.getInstace().isUserLogout = false;
          paramMainActivity.goSkypass();
          paramMainActivity.showMyPage();
          paramMainActivity.setMyPageListRefresh();
          paramMainActivity.setCategoryListRefresh();
          LogControl.i("TEST", "login callback");
          if (isLoginAfterLoad)
          {
            LogControl.i("WebScript", "login callback isLoginAfterLoad = " + isLoginAfterLoad);
            isLoginAfterLoad = false;
            paramMainActivity.loadUrl(loginAfterUrl);
            loginAfterUrl = "";
          }
          if (isCallUrl)
          {
            isCallUrl = false;
            paramMainActivity.getWebViewFragment().goAutoLoginCallback(callBackUrl);
            callBackUrl = "";
          }
          LogControl.i("WebScript", "Login Success!!");
          return true;
        }
        if ("loginFail".equals(localObject4))
        {
          KalSession.getInstace().isLogin = false;
          paramMainActivity.setMyPageListRefresh();
          paramMainActivity.setCategoryListRefresh();
          PrefManager.setLogInID(paramMainActivity, "");
          PrefManager.setLogInPW(paramMainActivity, "");
          PrefManager.setLogInType(paramMainActivity, "");
        }
        else
        {
          if ("logout".equals(localObject4))
          {
            KalSession.getInstace().isLogin = false;
            paramMainActivity.setMyPageListRefresh();
            paramMainActivity.setCategoryListRefresh();
            KalSession.getInstace().isUserLogout = true;
            paramMainActivity.getWebViewFragment().logOutFinished();
            LogControl.i("WebScript", "======================");
            return true;
          }
          if ("ready".equals(localObject4)) {
            return true;
          }
          if ("goLogout".equals(localObject4))
          {
            if (KalSession.getInstace().isLogin)
            {
              KalSession.getInstace().isLogin = false;
              KalSession.getInstace().isUserLogout = true;
              paramMainActivity.hideMyPage();
              paramMainActivity.getWebViewFragment().goLogout();
            }
            for (;;)
            {
              return true;
              paramMainActivity.getWebViewFragment().openLoginWindow();
            }
          }
          if ("destroy".equals(localObject4))
          {
            paramMainActivity.finishApp();
            return true;
          }
          if ("goBrowser".equals(localObject4))
          {
            paramString = StringUtil.emptyIfNull(localUnsupportedEncodingException[0]);
            if (paramString.equals("http%3A%2F%2Fmicro.koreanair.com%2Fmainevent%2F201604%2Fgear_s2%2Fm_gear_s2.html")) {
              try
              {
                localObject2 = URLDecoder.decode(paramString, "UTF-8");
                LogControl.d("WebScript", paramString + ": " + (String)localObject2);
                paramMainActivity.openNewWindowWebViewFragment((String)localObject2);
                return true;
              }
              catch (UnsupportedEncodingException paramMainActivity)
              {
                for (;;)
                {
                  paramMainActivity.printStackTrace();
                }
              }
            }
            try
            {
              localObject3 = URLDecoder.decode(paramString, "UTF-8");
              paramString = (String)localObject3;
              if (!((String)localObject3).startsWith("http")) {
                paramString = paramMainActivity.getResources().getString(2131099747) + (String)localObject3;
              }
              LogControl.i("WebScript", "External Browser url : " + paramString);
              paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
              paramString.addFlags(268435456);
              paramMainActivity.startActivity(paramString);
            }
            catch (Exception paramMainActivity)
            {
              for (;;)
              {
                paramMainActivity = localObject2[0];
              }
            }
            return true;
            if (!isTstoreExist(paramMainActivity)) {
              break;
            }
          }
        }
      }
    }
    for (paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(getString(paramMainActivity, 2131099737), new Object[0])));; paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(getString(paramMainActivity, 2131099736), new Object[0]))))
    {
      try
      {
        paramMainActivity.startActivity(paramString);
      }
      catch (ActivityNotFoundException paramString)
      {
        paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(getString(paramMainActivity, 2131099736), new Object[0]))));
      }
      break;
    }
    label1553:
    if (paramString.startsWith("http://itunes.apple.com/kr/app/id369125087?mt=8")) {
      return true;
    }
    if (paramString.startsWith("http://mobile.vpay.co.kr/jsp/MISP/andown.jsp"))
    {
      if (paramMainActivity.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("ispmobile:")), 65536).size() > 0) {
        return true;
      }
      if ((((String)localObject2).equalsIgnoreCase("SKTelecom")) && (isTstoreExist(paramMainActivity))) {}
      for (paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(getString(paramMainActivity, 2131099737), new Object[0])));; paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramString, new Object[0]))))
      {
        paramMainActivity.startActivity(paramString);
        return true;
      }
    }
    if ((paramString.startsWith(paramMainActivity.getResources().getString(2131099731))) && (((WebView)localObject3).getUrl().contains("mode=INTRO")))
    {
      if (isAppInstalled(paramMainActivity, "com.shcard.smartpay")) {
        return true;
      }
      paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramMainActivity.getResources().getString(2131099743), new Object[0]))));
      return true;
    }
    if ((paramString.startsWith(paramMainActivity.getResources().getString(2131099735))) && (((WebView)localObject3).getUrl().contains("mode=INTRO")))
    {
      if (isAppInstalled(paramMainActivity, "com.softforum.sample")) {
        return true;
      }
      paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramMainActivity.getResources().getString(2131099735), new Object[0]))));
      return true;
    }
    if ((paramString.startsWith(paramMainActivity.getResources().getString(2131099740))) && (((WebView)localObject3).getUrl().contains("mode=INTRO")))
    {
      if (isAppInstalled(paramMainActivity, "com.lotte.lottesmartpay")) {
        return true;
      }
      paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramMainActivity.getResources().getString(2131099740), new Object[0]))));
      return true;
    }
    if ((paramString.startsWith(paramMainActivity.getResources().getString(2131099733))) && (((WebView)localObject3).getUrl().contains("mode=INTRO")))
    {
      if (isAppInstalled(paramMainActivity, "com.ilk.visa3d")) {
        return true;
      }
      if ((((String)localObject2).equalsIgnoreCase("SKTelecom")) && (isTstoreExist(paramMainActivity))) {
        paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(getString(paramMainActivity, 2131099734), new Object[0])));
      }
      try
      {
        for (;;)
        {
          paramMainActivity.startActivity(paramString);
          return true;
          paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramMainActivity.getResources().getString(2131099733), new Object[0])));
        }
      }
      catch (ActivityNotFoundException paramString)
      {
        for (;;)
        {
          paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(getString(paramMainActivity, 2131099733), new Object[0])));
          try
          {
            paramMainActivity.startActivity(paramString);
          }
          catch (ActivityNotFoundException paramMainActivity)
          {
            return true;
          }
        }
      }
    }
    if (paramString.equalsIgnoreCase("ispmobile://"))
    {
      if (paramMainActivity.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("ispmobile:")), 65536).size() > 0)
      {
        paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
        return true;
      }
      return true;
    }
    if (paramString.startsWith("loadtowallet://download"))
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      try
      {
        paramMainActivity.startActivity(paramString);
        return false;
      }
      catch (ActivityNotFoundException paramString)
      {
        for (;;)
        {
          paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format(getString(paramMainActivity, 2131099742), new Object[0])));
          try
          {
            paramMainActivity.startActivity(paramString);
          }
          catch (ActivityNotFoundException paramMainActivity)
          {
            return true;
          }
        }
      }
    }
    if (paramString.startsWith("kakaotalk:"))
    {
      LogControl.i("WebScript", "web link kakao1 = " + paramString);
      if (isAppInstalled(paramMainActivity, "com.kakao.talk")) {
        return true;
      }
      paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(paramMainActivity.getResources().getString(2131099759), new Object[0]))));
      return true;
    }
    if ((paramString != null) && ((paramString.contains("http://market.android.com")) || (paramString.contains("vguard")) || (paramString.contains("droidxantivirus")) || (paramString.contains("smshinhanansimclick://")) || (paramString.contains("smshinhancardusim://")) || (paramString.contains("smhyundaiansimclick://")) || (paramString.contains("mpocketansimclick://")) || (paramString.contains("market://")) || (paramString.startsWith("lottesmartpay://")) || (paramString.startsWith("lotteappcard://")) || (paramString.contains("v3mobile")) || (paramString.contains("ilkansimmobilevaccine")) || (paramString.endsWith(".apk")) || (paramString.contains("ansimclick")) || (paramString.startsWith("hanaansim:")) || (paramString.startsWith("mvaccinecheck://")) || (paramString.contains("mvaccine")) || (paramString.contains("market://details?id=com.shcard.smartpay")) || (paramString.contains("shinhan-sr-ansimclick://")) || (paramString.contains("citicardapp")) || (paramString.contains("citispay")) || (paramString.contains("cpy")) || (paramString.contains("paycla")) || (paramString.contains("com.ahnlab.v3mobileplus")) || (paramString.contains("smartwall")) || (paramString.contains("appfree://")) || (paramString.contains("http://m.ahnlab.com/kr/site/download")) || (paramString.contains("kakao")))) {
      try
      {
        localObject2 = Intent.parseUri(paramString, 1);
        if (!paramString.startsWith("lottesmartpay://"))
        {
          boolean bool = paramString.startsWith("lotteappcard://");
          if (!bool) {}
        }
        else
        {
          paramMainActivity.printStackTrace();
        }
      }
      catch (URISyntaxException paramMainActivity)
      {
        for (;;)
        {
          try
          {
            paramString = new Intent("android.intent.action.VIEW", Uri.parse(((Intent)localObject2).getDataString()));
          }
          catch (ActivityNotFoundException paramMainActivity) {}
          try
          {
            paramMainActivity.startActivity(paramString);
            return true;
          }
          catch (ActivityNotFoundException paramMainActivity)
          {
            for (;;) {}
          }
          paramMainActivity = paramMainActivity;
          return false;
          paramMainActivity.printStackTrace();
        }
      }
      catch (ActivityNotFoundException paramMainActivity) {}
    }
    for (;;)
    {
      LogControl.d("TEST", "Activity not found");
      return true;
      if (paramString.startsWith("intent")) {
        if (paramMainActivity.getPackageManager().resolveActivity((Intent)localObject2, 0) == null)
        {
          paramString = ((Intent)localObject2).getPackage();
          if (paramString != null)
          {
            localObject2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString));
            paramString = (String)localObject2;
          }
        }
      }
      try
      {
        paramMainActivity.startActivity((Intent)localObject2);
        return true;
      }
      catch (ActivityNotFoundException paramMainActivity) {}
      localObject2 = new Intent("android.intent.action.VIEW", Uri.parse(((Intent)localObject2).getDataString()));
      paramString = (String)localObject2;
      paramMainActivity.startActivity((Intent)localObject2);
      for (;;)
      {
        return true;
        paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      }
      if ((paramString.startsWith("intent")) && (paramString.contains("com.hanaskcard.paycla"))) {
        try
        {
          paramMainActivity.startActivity(Intent.parseUri(paramString, 1));
          return true;
        }
        catch (URISyntaxException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return false;
        }
        catch (ActivityNotFoundException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return true;
        }
      }
      if ((paramString.startsWith("intent")) && ((paramString.contains("com.lotte.lottesmartpay")) || (paramString.contains("com.lcacApp")))) {
        try
        {
          paramMainActivity.startActivity(Intent.parseUri(paramString, 1));
          return true;
        }
        catch (URISyntaxException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return false;
        }
        catch (ActivityNotFoundException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return true;
        }
      }
      if ((paramString.startsWith("intent")) && (paramString.contains("com.ahnlab.v3mobileplus"))) {
        try
        {
          ((WebView)localObject3).getContext().startActivity(Intent.parseUri(paramString, 0));
          return true;
        }
        catch (URISyntaxException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return false;
        }
        catch (ActivityNotFoundException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return true;
        }
      }
      if ((paramString.startsWith("intent")) && (paramString.contains("kakao")))
      {
        LogControl.i("WebScript", "web link kakao intent= " + paramString);
        try
        {
          paramMainActivity.startActivity(Intent.parseUri(paramString, 1));
          return true;
        }
        catch (URISyntaxException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return false;
        }
        catch (ActivityNotFoundException paramMainActivity)
        {
          paramMainActivity.printStackTrace();
          return true;
        }
      }
      if ((!paramString.startsWith("http")) && (!paramString.startsWith("javascript")) && (!paramString.contains("intent://TID="))) {
        try
        {
          paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Intent.parseUri(paramString, 1).getDataString())));
          return true;
        }
        catch (Exception paramMainActivity)
        {
          paramMainActivity.getMessage();
          return false;
        }
      }
      if ((paramString.startsWith("http")) && (paramString.contains("E_Timetable.pdf"))) {
        try
        {
          paramMainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
          return true;
        }
        catch (Exception paramMainActivity)
        {
          paramMainActivity.getMessage();
          return false;
        }
      }
      if ((paramString.startsWith("http")) && (paramString.contains("/mobile/korea/ko/ibe/payment.html#paynow/")))
      {
        localObject2 = "?appVersion=" + KalUtil.getVersionName(paramMainActivity);
        paramString = paramString + (String)localObject2;
        paramMainActivity.getWebViewFragment().getWebView().loadUrl(paramString);
        LogControl.d("WebScript", paramString);
        return true;
      }
      if (paramString.contains("/mobile/korea/ko/ibe/bookingGate.html"))
      {
        localObject2 = "&appVersion=" + KalUtil.getVersionName(paramMainActivity);
        paramString = paramString + (String)localObject2;
        paramMainActivity.getWebViewFragment().getWebView().loadUrl(paramString);
        LogControl.d("WebScript", paramString);
        return true;
      }
      if (paramString.contains(".koreanair.com")) {
        break;
      }
      return isIspReturn(paramString, paramMainActivity);
    }
  }
}
