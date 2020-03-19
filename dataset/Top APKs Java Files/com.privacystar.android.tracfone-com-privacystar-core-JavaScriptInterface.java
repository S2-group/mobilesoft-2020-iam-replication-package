package com.privacystar.core;

import android.app.PendingIntent;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import com.privacystar.core.a.c.a;
import com.privacystar.core.alarms.UXPerpetualAlarm;
import com.privacystar.core.blocking.CallBlockingReceiver;
import com.privacystar.core.callerid.CallerIdActivity;
import com.privacystar.core.callerid.CallerIdService;
import com.privacystar.core.callerid.CallerIdViewV2;
import com.privacystar.core.callerid.OverlayView;
import com.privacystar.core.d.d;
import com.privacystar.core.d.e;
import com.privacystar.core.d.g;
import com.privacystar.core.d.h;
import com.privacystar.core.d.j;
import com.privacystar.core.d.l;
import com.privacystar.core.d.m;
import com.privacystar.core.d.o;
import com.privacystar.core.d.o.a;
import com.privacystar.core.d.p;
import com.privacystar.core.d.r;
import com.privacystar.core.d.s;
import com.privacystar.core.d.t;
import com.privacystar.core.d.v;
import com.privacystar.core.googleplay.v3.IABV3Activity;
import com.privacystar.core.javascript.JavaScriptService;
import com.privacystar.core.ui.EulaActivity;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JavaScriptInterface
{
  public static final String AD_TRAN_ID = "getNewAdNative";
  public static final String EXTRA_JSI_ID = "javaScriptInterfaceIdExtra";
  public static final String NATIVE_CB = "javascript:com.privacystar.nativecb.";
  private static final String TAG = "JavaScriptInterface#";
  protected final boolean API19;
  private final int REQUEST_CODE = 123;
  private Context context;
  private WebView dialogWebView;
  private String[] gmailAddresses;
  private final int id = new Random().nextInt();
  private WebView parentWebView;
  
  public JavaScriptInterface(Context paramContext, WebView paramWebView)
  {
    if (Build.VERSION.SDK_INT >= 19) {}
    for (boolean bool = true;; bool = false)
    {
      this.API19 = bool;
      o.a(this);
      setContext(paramContext);
      setParentWebView(paramWebView);
      return;
    }
  }
  
  private void addToActivity(String paramString1, String paramString2, String paramString3)
  {
    com.privacystar.common.c.a.b("JavaScriptInterface#addToActivity", "name: " + paramString1 + "; type: " + paramString2 + "; phone: " + paramString3 + ";", getContext());
    paramString1 = "INSERT into blockingHistory (type, phone, name, calledTimestamp) VALUES ('" + escapeQuotesSQL(paramString2) + "', '" + escapeQuotesSQL(paramString3) + "', '" + escapeQuotesSQL(paramString1) + "', current_timestamp);";
    ((PrivacyStarApplication)getContext().getApplicationContext()).d().a(getContext(), paramString1);
  }
  
  private void blockedCallNotification(String paramString)
  {
    String str;
    if (shouldShowBlockNotification(paramString))
    {
      paramString = j.c();
      str = this.context.getFilesDir().getPath() + "/uiAssets/Templates/";
      if ((paramString.length() < 2) || (!paramString.substring(0, 2).equalsIgnoreCase("es"))) {
        showRichToastFromFile(str + "blkNotification_en.htm", 1);
      }
    }
    else
    {
      return;
    }
    showRichToastFromFile(str + "blkNotification_es.htm", 1);
  }
  
  private String escapeQuotesSQL(String paramString)
  {
    return paramString.replace("'", "''");
  }
  
  private String getDndMessage()
  {
    Object localObject = ((PrivacyStarApplication)getContext().getApplicationContext()).d().a("SELECT message FROM blockingOptions WHERE optionType = 'dnd';", getContext());
    try
    {
      localObject = ((JSONObject)localObject).getJSONArray("results");
      if ((localObject != null) && (((JSONArray)localObject).length() != 0))
      {
        localObject = ((JSONArray)localObject).getJSONObject(0).optString("message");
        return localObject;
      }
    }
    catch (Exception localException)
    {
      com.privacystar.common.c.a.a("JavaScriptInterface#getDndMessage", "getting DND message", localException, getContext());
      return "";
    }
    return "";
  }
  
  private String getJsonForLogs(Vector<com.privacystar.core.b.b> paramVector)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[");
    paramVector = paramVector.iterator();
    while (paramVector.hasNext())
    {
      localStringBuffer.append(((com.privacystar.core.b.b)paramVector.next()).a());
      if (paramVector.hasNext()) {
        localStringBuffer.append(",");
      }
    }
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
  
  private String getNameFromBlockList(String paramString)
  {
    try
    {
      paramString = "SELECT name FROM blockList WHERE phone = '" + escapeQuotesSQL(paramString) + "' AND unblocked = 0;";
      paramString = ((PrivacyStarApplication)getContext().getApplicationContext()).d().a(paramString, getContext()).getJSONArray("results");
      if ((paramString != null) && (paramString.length() != 0))
      {
        paramString = paramString.getJSONObject(0).optString("name");
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#getNameFromBlockList", "An error occurred: " + paramString.getMessage(), getContext());
      if (s.a(getContext())) {
        h.a("getNameFromBlockList() " + paramString.getMessage(), false);
      }
      return "";
    }
    return null;
  }
  
  private void launch(String paramString, boolean paramBoolean)
  {
    Intent localIntent = new Intent(getContext(), CallerIdActivity.class);
    if (paramBoolean) {
      localIntent.putExtra("com.privacystar.android.packageName", paramString);
    }
    for (;;)
    {
      localIntent.setFlags(268435456);
      localIntent.addFlags(134217728);
      localIntent.addFlags(8388608);
      localIntent.addFlags(32768);
      localIntent.addFlags(65536);
      getContext().startActivity(localIntent);
      return;
      localIntent.putExtra("com.privacystar.android.adUrl", paramString);
    }
  }
  
  private void setCIDFullscreen(final boolean paramBoolean1, final boolean paramBoolean2, final String paramString)
  {
    final Handler localHandler = new Handler(getContext().getMainLooper());
    localHandler.post(new Runnable()
    {
      public final void run()
      {
        try
        {
          CallerIdViewV2.setUseFullScreen(paramBoolean1);
          OverlayView localOverlayView = CallerIdService.f();
          if (localOverlayView != null) {
            localOverlayView.o();
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            com.privacystar.common.c.a.a("JavaScriptInterface#setCIDFullscreen", "while setting size", localException, JavaScriptInterface.this.getContext());
            localException.printStackTrace();
          }
        }
        if (paramBoolean2) {
          localHandler.postDelayed(new Runnable()
          {
            public final void run()
            {
              JavaScriptInterface.this.showLiveCallerId(JavaScriptInterface.70.this.d);
            }
          }, 200L);
        }
      }
    });
  }
  
  private void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private boolean shouldShowBlockNotification(String paramString)
  {
    if (!paramString.equalsIgnoreCase("call")) {
      return false;
    }
    try
    {
      paramString = ((PrivacyStarApplication)getContext().getApplicationContext()).d().a("SELECT optionType, checked FROM blockingOptions WHERE optionType = 'blkNotification';", getContext()).getJSONArray("results");
      if ((paramString != null) && (paramString.length() != 0))
      {
        int i = paramString.getJSONObject(0).getInt("checked");
        return i == 1;
      }
    }
    catch (Exception paramString)
    {
      com.privacystar.common.c.a.a("JavaScriptInterface#shouldShowBlockNotification", "in shouldShowBlockNotification()", paramString, getContext());
      return false;
    }
    return false;
  }
  
  @JavascriptInterface
  public void answerCurrentCall()
  {
    com.privacystar.core.d.b.b(getContext());
  }
  
  @JavascriptInterface
  public void cacheCallOrTextHistory(String paramString)
  {
    com.privacystar.core.service.d.a.a(getContext(), com.privacystar.core.service.d.b.O, getJsonForLogs(j.a(paramString, 3, 15, 0, getContext())));
  }
  
  @JavascriptInterface
  public void callBackNewOccurenceBlocked(final String paramString1, final String paramString2, final String paramString3, String paramString4)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        int j = 1;
        String str1;
        label105:
        int i;
        if ((paramString2.equalsIgnoreCase("undefined")) || (paramString2.length() <= 3))
        {
          str1 = "Unknown";
          localObject = t.a(JavaScriptInterface.this.getContext());
          final boolean bool1 = com.privacystar.common.sdk.org.metova.a.h.d.b.d(paramString1, "call");
          if (!bool1) {
            break label259;
          }
          if (com.privacystar.common.sdk.org.metova.a.h.d.b.e(paramString3, "scammer")) {
            ((t)localObject).a("72|" + paramString2 + "|1", null);
          }
          ((t)localObject).a("70|1", null);
          final boolean bool2 = com.privacystar.common.sdk.org.metova.a.h.d.b.e(paramString3, "dnd");
          if (com.privacystar.core.service.d.a.C(JavaScriptInterface.this.getContext())) {
            new Handler(JavaScriptInterface.this.getContext().getMainLooper()).post(new Runnable()
            {
              public final void run()
              {
                Context localContext = JavaScriptInterface.this.getContext();
                boolean bool = bool1;
                if (bool2) {}
                for (String str = "";; str = JavaScriptInterface.16.this.a)
                {
                  p.a(localContext, bool, str);
                  return;
                }
              }
            });
          }
          localObject = ((PrivacyStarApplication)JavaScriptInterface.this.getContext().getApplicationContext()).c();
          if (((com.privacystar.core.d.a)localObject).m().get(com.privacystar.core.d.a.g) == null) {
            break label270;
          }
          i = 1;
          label195:
          if (((com.privacystar.core.d.a)localObject).m().get(com.privacystar.core.d.a.h) == null) {
            break label275;
          }
          label209:
          if (i == 0) {
            break label367;
          }
          if (!str1.equals("Unknown")) {
            break label280;
          }
          if (j != 0) {
            break label430;
          }
          JavaScriptInterface.this.addToActivity("", "dnd", str1);
        }
        label259:
        label270:
        label275:
        label280:
        String str2;
        for (;;)
        {
          JavaScriptInterface.this.callbackNativeCB("updateNewBlockedInformation();");
          return;
          str1 = paramString2;
          break;
          ((t)localObject).a("71|1", null);
          break label105;
          i = 0;
          break label195;
          j = 0;
          break label209;
          str2 = JavaScriptInterface.this.getNameFromBlockList(str1);
          localObject = str2;
          if (str2 != null) {
            break label401;
          }
          localObject = "";
          if (paramString3.equals("blacklist")) {
            break label401;
          }
          localObject = JavaScriptInterface.this.getDndMessage();
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            localObject = Uri.decode((String)localObject);
            JavaScriptInterface.this.sendSMS(str1, (String)localObject);
          }
          JavaScriptInterface.this.addToActivity("", "dnd", str1);
        }
        label367:
        if (!str1.equalsIgnoreCase("Unknown"))
        {
          str2 = JavaScriptInterface.this.getNameFromBlockList(str1);
          localObject = str2;
          if (str2 != null) {}
        }
        label401:
        label430:
        for (Object localObject = "";; localObject = "")
        {
          JavaScriptInterface.this.addToActivity((String)localObject, paramString1, str1);
          JavaScriptInterface.this.blockedCallNotification(paramString1);
          break;
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void callBackOnStart()
  {
    callbackNativeCB("onResumeCB();");
  }
  
  @JavascriptInterface
  public void callBackPushReceived(JSONObject paramJSONObject)
  {
    callbackNativeCB("callBackPushReceived('" + com.privacystar.core.service.b.b.a(paramJSONObject) + "');");
  }
  
  @JavascriptInterface
  public void callBackScheduledEventArrived(JSONObject paramJSONObject)
  {
    try
    {
      callbackNativeCB("callBackScheduledEventArrived('" + com.privacystar.core.service.b.b.a(paramJSONObject) + "');");
      return;
    }
    catch (Exception paramJSONObject)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#callBackScheduledEventArrived", "Error sending event JSON", getContext());
      if (s.a(getContext())) {
        h.a("callBackScheduleEventArrived() " + paramJSONObject.getMessage(), false);
      }
      paramJSONObject.printStackTrace();
    }
  }
  
  public void callQueueComplete(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "queueCompleteJob(1)";; str = "queueCompleteJob(0)")
    {
      callbackToJavaScriptService(str);
      return;
    }
  }
  
  @JavascriptInterface
  public void callbackCallEnded(String paramString) {}
  
  @JavascriptInterface
  public void callbackCallOffhook(String paramString) {}
  
  public void callbackNativeCB(String paramString)
  {
    callbackRaw("javascript:com.privacystar.nativecb." + paramString);
  }
  
  @JavascriptInterface
  public void callbackNewIncomingNumber(String paramString)
  {
    Log.i("JavaScriptInterface#callbackNewIncomingNumber", "New incoming number");
    startLiveCallerId(paramString);
  }
  
  @JavascriptInterface
  public void callbackOnWebViewError(JSONObject paramJSONObject)
  {
    try
    {
      callbackNativeCB("onWebviewError('" + com.privacystar.core.service.b.b.a(paramJSONObject) + "');");
      return;
    }
    catch (Exception paramJSONObject)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#callbackOnWebviewError", "Error sending webview error JSON", getContext());
      if (s.a(getContext())) {
        h.a("callbackOnWebViewError() " + paramJSONObject.getMessage(), false);
      }
      paramJSONObject.printStackTrace();
    }
  }
  
  public void callbackRaw(final String paramString)
  {
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        if (JavaScriptInterface.this.API19)
        {
          JavaScriptInterface.this.getParentWebView().evaluateJavascript(paramString, null);
          return;
        }
        JavaScriptInterface.this.getParentWebView().loadUrl(paramString);
      }
    });
  }
  
  public void callbackToDialogNativeCB(final String paramString)
  {
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        String str = "javascript:com.privacystar.nativecb." + paramString;
        if (JavaScriptInterface.this.API19)
        {
          JavaScriptInterface.this.getDialogWebView().evaluateJavascript(str, null);
          return;
        }
        JavaScriptInterface.this.getDialogWebView().loadUrl(str);
      }
    });
  }
  
  @JavascriptInterface
  public void callbackToJavaScriptService(String paramString)
  {
    Intent localIntent = new Intent(getContext().getApplicationContext(), JavaScriptService.class);
    if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramString)) {
      localIntent.putExtra("initialCallback", paramString);
    }
    getContext().startService(localIntent);
  }
  
  @JavascriptInterface
  public void cancelNotification(String paramString)
  {
    p.a(paramString, getContext());
  }
  
  @JavascriptInterface
  public void checkOwnedProducts()
  {
    checkOwnedProducts(3);
  }
  
  @JavascriptInterface
  public void checkOwnedProducts(int paramInt)
  {
    if (paramInt == 2)
    {
      localIntent = new Intent(com.privacystar.core.googleplay.a.b);
      localIntent.putExtra("iab_intent_action", com.privacystar.core.googleplay.a.d);
      l.a(getContext()).a(localIntent);
      return;
    }
    Intent localIntent = new Intent(getContext(), IABV3Activity.class);
    localIntent.putExtra("iab_intent_action", com.privacystar.core.googleplay.a.d);
    localIntent.putExtra("javaScriptInterfaceIdExtra", getId());
    localIntent.setFlags(268435456);
    localIntent.addFlags(134217728);
    localIntent.addFlags(8388608);
    localIntent.addFlags(65536);
    localIntent.addFlags(4);
    getContext().startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void disablePSAnalyticsEvents(int[] paramArrayOfInt)
  {
    o.a.d(paramArrayOfInt);
    o.a.a(getContext());
  }
  
  @JavascriptInterface
  public void dismissDialog()
  {
    l.a(getContext()).a(new Intent("dismissDialogsIntent"));
    com.privacystar.core.service.d.a.f(true, getContext());
  }
  
  @JavascriptInterface
  public void dismissOverlayDialog() {}
  
  @JavascriptInterface
  public void dispatchPSAnalytics()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          t.a(JavaScriptInterface.this.getContext()).b(null, JavaScriptInterface.this);
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.a("JavaScriptInterface#dispatchPSAnalytics", "dispatching PS analytics", localException, JavaScriptInterface.this.getContext());
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void dispatchPSAnalytics(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          t.a(JavaScriptInterface.this.getContext()).b(paramString, JavaScriptInterface.this);
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.a("JavaScriptInterface#dispatchPSAnalytics", "dispatching PS analytics", localException, JavaScriptInterface.this.getContext());
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void dispatchPSAnalyticsCB(JSONObject paramJSONObject)
  {
    callbackNativeCB("dispatchPSAnalyticsCB('" + com.privacystar.core.service.b.b.a(paramJSONObject) + "');");
  }
  
  @JavascriptInterface
  public void downloadAd()
  {
    downloadAd("");
  }
  
  @JavascriptInterface
  public void downloadAd(String paramString)
  {
    com.privacystar.core.a.c.e().a(getAdLocation(), paramString, getContext());
  }
  
  @JavascriptInterface
  public void enableAllPSAnalyticsEvents() {}
  
  @JavascriptInterface
  public void enableBlockNotification(boolean paramBoolean)
  {
    o.a(paramBoolean, true, getContext());
  }
  
  @JavascriptInterface
  public void enableBlockingReceivers(boolean paramBoolean)
  {
    o.a(paramBoolean, true, getContext());
  }
  
  @JavascriptInterface
  public void enableDismissWithHome() {}
  
  @JavascriptInterface
  public void enableGoogleAnalytics(boolean paramBoolean)
  {
    o.b(paramBoolean, true, getContext());
  }
  
  @JavascriptInterface
  public void enableKeepAliveService(boolean paramBoolean)
  {
    o.c(paramBoolean, true, getContext());
  }
  
  @JavascriptInterface
  public void enableOnlyPSAnalyticsEvents(int[] paramArrayOfInt)
  {
    o.a.b(paramArrayOfInt);
    o.a.a(getContext());
  }
  
  @JavascriptInterface
  public void enablePS(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          com.privacystar.core.service.d.a.j(paramString, JavaScriptInterface.this.getContext());
          CallBlockingReceiver.a();
          JavaScriptInterface.this.enablePSCB(true, "");
          return;
        }
        catch (Throwable localThrowable)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#enablePS", "Error toggling application enable/disable state", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("enablePS() " + localThrowable.getMessage(), false);
          }
          JavaScriptInterface.this.enablePSCB(false, localThrowable.getMessage());
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void enablePSAnalytics(boolean paramBoolean)
  {
    if (paramBoolean) {
      o.a.a(0);
    }
    for (;;)
    {
      o.a.a(getContext());
      return;
      o.a.b(0);
    }
  }
  
  @JavascriptInterface
  public void enablePSAnalyticsEvents(int[] paramArrayOfInt)
  {
    o.a.c(paramArrayOfInt);
    o.a.a(getContext());
  }
  
  @JavascriptInterface
  public void enablePSCB(boolean paramBoolean, String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      if (paramBoolean) {
        localJSONObject.put("result", "true");
      }
      for (;;)
      {
        callbackNativeCB("enablePSCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
        return;
        localJSONObject.put("result", "false");
        localJSONObject.put("message", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#enablePSCB", "Error in callback enablePSCB", getContext());
      if (s.a(getContext())) {
        h.a("enablePSCB() " + paramString.getMessage(), false);
      }
      paramString.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void endCurrentCall()
  {
    CallBlockingReceiver.a(true);
    Context localContext = getContext();
    try
    {
      com.privacystar.common.c.a.b("CallBlockingReceiver", "CallBlockingReceiver**Ending call", localContext);
      Object localObject = com.privacystar.core.d.b.b();
      localObject.getClass().getMethod("endCall", new Class[0]).invoke(localObject, new Object[0]);
      Thread.sleep(500L);
      localObject = (TelephonyManager)localContext.getSystemService("phone");
      boolean bool1;
      boolean bool2;
      if (((TelephonyManager)localObject).getCallState() == 2)
      {
        bool1 = true;
        if (((TelephonyManager)localObject).getCallState() != 1) {
          break label200;
        }
        bool2 = true;
        label92:
        com.privacystar.common.c.a.b("CallBlockingReceiver#endCall", "Call status after attempted to end call/ send to voicemail ----> call off hook: " + Boolean.toString(bool1) + "   call still ringing: " + Boolean.toString(bool2), localContext);
        i = Build.VERSION.SDK_INT;
        if ((bool2) && (i >= 16)) {
          localContext.sendBroadcast(new Intent("com.android.phone.ACTION_HANG_UP_ONGOING_CALL"));
        }
        if (((TelephonyManager)localObject).getCallState() != 1) {
          break label205;
        }
      }
      label200:
      label205:
      for (int i = 1;; i = 0)
      {
        if (i != 0) {
          com.privacystar.common.c.a.d("JavaScriptInterface#endCurrentCall", "Unable to end call on this device!", localContext);
        }
        com.privacystar.core.service.d.a.a(false, localContext);
        return;
        bool1 = false;
        break;
        bool2 = false;
        break label92;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void execSQL(final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = ((PrivacyStarApplication)JavaScriptInterface.this.getContext().getApplicationContext()).d().a(JavaScriptInterface.this.getContext(), paramString1);
        try
        {
          localJSONObject.put("tranId", paramString2);
          JavaScriptInterface.this.callbackNativeCB("execSQLCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#querySQL", "Error executing query", JavaScriptInterface.this.getContext());
            if (s.a(JavaScriptInterface.this.getContext())) {
              h.a("execSQL() " + localJSONException.getMessage(), false);
            }
            localJSONException.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void execSQLWithResult(final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = ((PrivacyStarApplication)JavaScriptInterface.this.getContext().getApplicationContext()).d().a(paramString1, JavaScriptInterface.this.getContext());
        if (localJSONObject != null) {}
        for (;;)
        {
          try
          {
            localJSONObject.put("tranId", paramString2);
            JavaScriptInterface.this.callbackNativeCB("execSQLWithResultCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
            return;
          }
          catch (JSONException localJSONException)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#execSQLWithResult", "Error with JSON in execSQLWith", JavaScriptInterface.this.getContext());
            if (!s.a(JavaScriptInterface.this.getContext())) {
              continue;
            }
            h.a("execSQLWithResult() " + localJSONException.getMessage(), false);
            localJSONException.printStackTrace();
          }
          localJSONObject = new JSONObject();
          localJSONObject.put("tranId", paramString2);
          localJSONObject.put("sqlQuery", paramString1);
          localJSONObject.put("error", "Error executing query");
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void fetchUi(final int paramInt)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("fetchUI");
        localIntent.putExtra("fetchUIUpdateExtra", paramInt);
        l.a(JavaScriptInterface.this.getContext()).a(localIntent);
      }
    }).start();
  }
  
  public void fetchUiCB(boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    callbackNativeCB("fetchUiCB(" + localJSONObject + ");");
  }
  
  @JavascriptInterface
  public void finishMainActivity()
  {
    l.a(getContext()).a(new Intent("finishMainActivity"));
  }
  
  @JavascriptInterface
  public void flurryLogEvent(String paramString1, String paramString2)
  {
    trackEvent(paramString1, paramString1, "", "0");
  }
  
  @JavascriptInterface
  public void flurryOnPageView()
  {
    trackPageView("Unknown");
  }
  
  @JavascriptInterface
  public void flurrySetUserId(String paramString)
  {
    com.privacystar.common.c.a.d("JavaScriptInterface##flurrySetUserId", "flurrySetUserId method called by native though it is deperacted", getContext());
  }
  
  @JavascriptInterface
  public void gaTrackException(String paramString, int paramInt)
  {
    boolean bool = true;
    if (paramInt == 1) {}
    for (;;)
    {
      h.a(paramString, bool);
      return;
      bool = false;
    }
  }
  
  @JavascriptInterface
  public void gaTrackPurchase(final String paramString1, final long paramLong, String paramString2, final String paramString3, final String paramString4, final String paramString5)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("setGAPurchaseIntent");
        localIntent.putExtra("purchaseIdExtra", paramString1);
        localIntent.putExtra("purchaseAmountExtra", paramLong);
        localIntent.putExtra("orderAffilitationExtra", paramString3);
        localIntent.putExtra("productSKUExtra", paramString4);
        localIntent.putExtra("productNameExtra", paramString5);
        localIntent.putExtra("productCategoryExtra", this.f);
        l.a(JavaScriptInterface.this.getContext()).a(localIntent);
      }
    }).start();
  }
  
  protected c.a getAdLocation()
  {
    return c.a.a;
  }
  
  @JavascriptInterface
  public void getApplicationIcon(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        boolean bool1 = false;
        Object localObject = JavaScriptInterface.this.getContext().getPackageManager();
        try
        {
          localObject = ((PackageManager)localObject).getApplicationIcon(paramString);
          Bitmap localBitmap = Bitmap.createBitmap(((Drawable)localObject).getIntrinsicWidth(), ((Drawable)localObject).getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
          Canvas localCanvas = new Canvas(localBitmap);
          ((Drawable)localObject).setBounds(new Rect(0, 0, ((Drawable)localObject).getIntrinsicWidth(), ((Drawable)localObject).getIntrinsicHeight()));
          ((Drawable)localObject).setAlpha(-3);
          ((Drawable)localObject).draw(localCanvas);
          boolean bool2 = g.a(localBitmap, "icons/", paramString + ".png");
          bool1 = bool2;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            com.privacystar.common.c.a.c("JavaScriptInterface#getApplicationIcon()", "Error saving icon: " + localNameNotFoundException.getMessage(), JavaScriptInterface.this.getContext());
            localNameNotFoundException.printStackTrace();
          }
        }
        localObject = "{\"result\":" + bool1 + ",\"packageName\":\"" + paramString + "\"}";
        com.privacystar.common.c.a.b("JavaScriptInterface#getAppIcon()", "Getting icon for " + paramString + " returns " + (String)localObject, JavaScriptInterface.this.getContext());
        JavaScriptInterface.this.callbackNativeCB("getApplicationIconCB('" + (String)localObject + "');");
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getCallLog(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String str = JavaScriptInterface.this.getJsonForLogs(j.a(JavaScriptInterface.this.getContext(), paramString1, paramString2, paramString3, paramString4));
          JavaScriptInterface.this.callbackNativeCB("getCallLogCB('" + str + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getCallLog", "Error retreiving or reporting call log: " + localException.getMessage(), JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getCallLog() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getCallOrTextHistory()
  {
    com.privacystar.common.sdk.org.metova.a.h.d.b.b(com.privacystar.core.service.d.a.k(com.privacystar.core.service.d.b.O, getContext()));
    callbackNativeCB("getCallOrTextHistoryCB('" + com.privacystar.core.service.d.a.k(com.privacystar.core.service.d.b.O, getContext()) + "');");
  }
  
  @JavascriptInterface
  public void getCallOrTextHistory(String paramString, int paramInt1, int paramInt2)
  {
    getCallOrTextHistory(paramString, paramInt1, paramInt2, 0);
  }
  
  @JavascriptInterface
  public void getCallOrTextHistory(final String paramString, final int paramInt1, final int paramInt2, final int paramInt3)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String str = JavaScriptInterface.this.getJsonForLogs(j.a(paramString, paramInt1, paramInt2, paramInt3, JavaScriptInterface.this.getContext()));
          JavaScriptInterface.this.callbackNativeCB("getCallOrTextHistoryCB('" + str + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getCallOrTextHistory", "Error retreiving or reporting call and text history: " + localException.getMessage(), JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getCallOrTextHistory() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getContactId(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String[] arrayOfString = com.privacystar.core.d.c.a(JavaScriptInterface.this.getContext().getContentResolver(), paramString);
          JavaScriptInterface.this.getContactIdCB(arrayOfString[3]);
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.a("JavaScriptInterface#getContactId", "while calling getContactId", localException, JavaScriptInterface.this.getContext());
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getContactIdCB(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramString)) {
        localJSONObject.put("cid", paramString);
      }
      callbackNativeCB("getContactIdCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
      return;
    }
    catch (Exception paramString)
    {
      com.privacystar.common.c.a.a("JavaScriptInterface#getContactIdCB", "while calling callback getContactIdCB", paramString, getContext());
      paramString.printStackTrace();
    }
  }
  
  protected Context getContext()
  {
    return this.context;
  }
  
  @JavascriptInterface
  public void getCurrentOrder(final String paramString1, final int paramInt1, final String paramString2, final String paramString3, final int paramInt2)
  {
    new Thread(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 26	com/privacystar/core/JavaScriptInterface$32:a	Ljava/lang/String;
        //   4: aload_0
        //   5: getfield 24	com/privacystar/core/JavaScriptInterface$32:f	Lcom/privacystar/core/JavaScriptInterface;
        //   8: invokevirtual 47	com/privacystar/core/JavaScriptInterface:getContext	()Landroid/content/Context;
        //   11: invokestatic 53	com/privacystar/core/service/d/a:h	(Ljava/lang/String;Landroid/content/Context;)V
        //   14: aload_0
        //   15: getfield 28	com/privacystar/core/JavaScriptInterface$32:b	Ljava/lang/String;
        //   18: aload_0
        //   19: getfield 24	com/privacystar/core/JavaScriptInterface$32:f	Lcom/privacystar/core/JavaScriptInterface;
        //   22: invokevirtual 47	com/privacystar/core/JavaScriptInterface:getContext	()Landroid/content/Context;
        //   25: invokestatic 56	com/privacystar/core/service/d/a:i	(Ljava/lang/String;Landroid/content/Context;)V
        //   28: invokestatic 61	com/privacystar/common/sdk/org/metova/a/d/a/d:a	()Lcom/privacystar/common/sdk/org/metova/a/d/a/d;
        //   31: invokevirtual 64	com/privacystar/common/sdk/org/metova/a/d/a/d:b	()Lcom/privacystar/common/sdk/org/metova/a/d/a/c;
        //   34: astore_2
        //   35: invokestatic 69	com/privacystar/common/sdk/org/metova/a/d/a/b:a	()Lcom/privacystar/common/sdk/org/metova/a/d/a/b;
        //   38: invokevirtual 72	com/privacystar/common/sdk/org/metova/a/d/a/b:b	()Lcom/privacystar/common/sdk/org/metova/a/d/a/a;
        //   41: astore_3
        //   42: aload_2
        //   43: ifnull +40 -> 83
        //   46: aload_3
        //   47: ifnull +36 -> 83
        //   50: aload_0
        //   51: getfield 30	com/privacystar/core/JavaScriptInterface$32:c	I
        //   54: iconst_1
        //   55: if_icmpeq +28 -> 83
        //   58: aload_2
        //   59: aload_3
        //   60: aload_0
        //   61: getfield 24	com/privacystar/core/JavaScriptInterface$32:f	Lcom/privacystar/core/JavaScriptInterface;
        //   64: invokevirtual 47	com/privacystar/core/JavaScriptInterface:getContext	()Landroid/content/Context;
        //   67: invokestatic 77	com/privacystar/common/sdk/org/metova/android/a/a/a/a/a:a	(Lcom/privacystar/common/sdk/org/metova/a/d/a/c;Lcom/privacystar/common/sdk/org/metova/a/d/a/a;Landroid/content/Context;)Lorg/json/JSONObject;
        //   70: astore_2
        //   71: aload_0
        //   72: getfield 24	com/privacystar/core/JavaScriptInterface$32:f	Lcom/privacystar/core/JavaScriptInterface;
        //   75: aload_2
        //   76: invokestatic 82	com/privacystar/core/service/b/b:a	(Lorg/json/JSONObject;)Ljava/lang/String;
        //   79: invokevirtual 86	com/privacystar/core/JavaScriptInterface:getCurrentOrderCB	(Ljava/lang/String;)V
        //   82: return
        //   83: aload_0
        //   84: getfield 26	com/privacystar/core/JavaScriptInterface$32:a	Ljava/lang/String;
        //   87: astore 5
        //   89: aload_0
        //   90: getfield 32	com/privacystar/core/JavaScriptInterface$32:d	I
        //   93: istore_1
        //   94: aload_0
        //   95: getfield 28	com/privacystar/core/JavaScriptInterface$32:b	Ljava/lang/String;
        //   98: astore_2
        //   99: aload_0
        //   100: getfield 34	com/privacystar/core/JavaScriptInterface$32:e	Ljava/lang/String;
        //   103: astore_3
        //   104: aload_0
        //   105: getfield 24	com/privacystar/core/JavaScriptInterface$32:f	Lcom/privacystar/core/JavaScriptInterface;
        //   108: invokevirtual 47	com/privacystar/core/JavaScriptInterface:getContext	()Landroid/content/Context;
        //   111: astore 4
        //   113: new 88	org/apache/http/client/methods/HttpGet
        //   116: dup
        //   117: new 90	java/lang/StringBuilder
        //   120: dup
        //   121: invokespecial 91	java/lang/StringBuilder:<init>	()V
        //   124: aload 5
        //   126: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: aload_2
        //   130: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   136: invokespecial 101	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
        //   139: astore_3
        //   140: new 103	org/apache/http/params/BasicHttpParams
        //   143: dup
        //   144: invokespecial 104	org/apache/http/params/BasicHttpParams:<init>	()V
        //   147: astore_2
        //   148: aload_2
        //   149: iload_1
        //   150: invokestatic 110	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
        //   153: aload_2
        //   154: iload_1
        //   155: invokestatic 113	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
        //   158: new 115	org/apache/http/impl/client/DefaultHttpClient
        //   161: dup
        //   162: aload_2
        //   163: invokespecial 118	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
        //   166: astore 6
        //   168: aconst_null
        //   169: astore_2
        //   170: aload 6
        //   172: aload_3
        //   173: invokeinterface 124 2 0
        //   178: astore_3
        //   179: aload_3
        //   180: astore_2
        //   181: aload 5
        //   183: ldc 126
        //   185: invokevirtual 132	java/lang/String:indexOf	(Ljava/lang/String;)I
        //   188: istore_1
        //   189: aload 5
        //   191: iconst_0
        //   192: ldc 126
        //   194: invokevirtual 136	java/lang/String:length	()I
        //   197: iload_1
        //   198: iadd
        //   199: invokevirtual 140	java/lang/String:substring	(II)Ljava/lang/String;
        //   202: pop
        //   203: aload_2
        //   204: invokeinterface 146 1 0
        //   209: invokeinterface 152 1 0
        //   214: invokestatic 157	com/privacystar/common/sdk/org/metova/a/h/b/b:a	(Ljava/io/InputStream;)Ljava/lang/String;
        //   217: astore_3
        //   218: ldc -97
        //   220: new 90	java/lang/StringBuilder
        //   223: dup
        //   224: ldc -95
        //   226: invokespecial 162	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   229: aload_3
        //   230: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   236: aload 4
        //   238: invokestatic 167	com/privacystar/common/c/a:a	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
        //   241: aload_3
        //   242: ifnull -160 -> 82
        //   245: new 169	com/privacystar/common/sdk/org/metova/a/d/a/c
        //   248: dup
        //   249: invokespecial 170	com/privacystar/common/sdk/org/metova/a/d/a/c:<init>	()V
        //   252: astore 5
        //   254: new 172	org/json/JSONObject
        //   257: dup
        //   258: invokespecial 173	org/json/JSONObject:<init>	()V
        //   261: astore_2
        //   262: aload 5
        //   264: aload_2
        //   265: aload_3
        //   266: invokestatic 176	com/privacystar/common/sdk/org/metova/android/a/a/a/a/a:a	(Lcom/privacystar/common/sdk/org/metova/a/d/a/c;Lorg/json/JSONObject;Ljava/lang/String;)V
        //   269: new 178	android/content/Intent
        //   272: dup
        //   273: ldc -76
        //   275: invokespecial 181	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   278: astore_3
        //   279: aload_3
        //   280: ldc -73
        //   282: aload_2
        //   283: invokevirtual 184	org/json/JSONObject:toString	()Ljava/lang/String;
        //   286: invokevirtual 188	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   289: pop
        //   290: aload 4
        //   292: aload_3
        //   293: invokevirtual 194	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
        //   296: return
        //   297: astore_2
        //   298: ldc -97
        //   300: ldc -60
        //   302: aload 4
        //   304: invokestatic 198	com/privacystar/common/c/a:c	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
        //   307: aload_2
        //   308: invokevirtual 201	java/lang/Throwable:printStackTrace	()V
        //   311: return
        //   312: astore 6
        //   314: new 172	org/json/JSONObject
        //   317: dup
        //   318: invokespecial 173	org/json/JSONObject:<init>	()V
        //   321: astore_3
        //   322: ldc -53
        //   324: ldc -51
        //   326: aload 4
        //   328: invokestatic 198	com/privacystar/common/c/a:c	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
        //   331: aload 6
        //   333: invokevirtual 206	java/lang/Exception:printStackTrace	()V
        //   336: aload_3
        //   337: ldc -48
        //   339: iconst_m1
        //   340: invokevirtual 212	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   343: pop
        //   344: aload_3
        //   345: ldc -42
        //   347: aload 6
        //   349: invokevirtual 217	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   352: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   355: pop
        //   356: new 178	android/content/Intent
        //   359: dup
        //   360: ldc -76
        //   362: invokespecial 181	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   365: astore 6
        //   367: aload 6
        //   369: ldc -73
        //   371: aload_3
        //   372: invokevirtual 184	org/json/JSONObject:toString	()Ljava/lang/String;
        //   375: invokevirtual 188	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   378: pop
        //   379: aload 4
        //   381: aload 6
        //   383: invokevirtual 194	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
        //   386: goto -205 -> 181
        //   389: astore_3
        //   390: ldc -53
        //   392: ldc -34
        //   394: aload 4
        //   396: invokestatic 198	com/privacystar/common/c/a:c	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
        //   399: aload_3
        //   400: invokevirtual 206	java/lang/Exception:printStackTrace	()V
        //   403: goto -222 -> 181
        //   406: astore_3
        //   407: ldc -97
        //   409: ldc -32
        //   411: aload 4
        //   413: invokestatic 198	com/privacystar/common/c/a:c	(Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
        //   416: aload_3
        //   417: invokevirtual 206	java/lang/Exception:printStackTrace	()V
        //   420: aload_2
        //   421: ldc -42
        //   423: aload_3
        //   424: invokevirtual 217	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   427: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   430: pop
        //   431: new 178	android/content/Intent
        //   434: dup
        //   435: ldc -76
        //   437: invokespecial 181	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   440: astore_3
        //   441: aload_3
        //   442: ldc -73
        //   444: aload_2
        //   445: invokevirtual 184	org/json/JSONObject:toString	()Ljava/lang/String;
        //   448: invokevirtual 188	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   451: pop
        //   452: aload 4
        //   454: aload_3
        //   455: invokevirtual 194	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
        //   458: return
        //   459: astore_3
        //   460: new 178	android/content/Intent
        //   463: dup
        //   464: ldc -76
        //   466: invokespecial 181	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   469: astore 5
        //   471: aload 5
        //   473: ldc -73
        //   475: aload_2
        //   476: invokevirtual 184	org/json/JSONObject:toString	()Ljava/lang/String;
        //   479: invokevirtual 188	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   482: pop
        //   483: aload 4
        //   485: aload 5
        //   487: invokevirtual 194	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
        //   490: aload_3
        //   491: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	492	0	this	32
        //   93	106	1	i	int
        //   34	249	2	localObject1	Object
        //   297	179	2	localThrowable	Throwable
        //   41	331	3	localObject2	Object
        //   389	11	3	localException1	Exception
        //   406	18	3	localException2	Exception
        //   440	15	3	localIntent1	Intent
        //   459	32	3	localObject3	Object
        //   111	373	4	localContext	Context
        //   87	399	5	localObject4	Object
        //   166	5	6	localDefaultHttpClient	DefaultHttpClient
        //   312	36	6	localException3	Exception
        //   365	17	6	localIntent2	Intent
        // Exception table:
        //   from	to	target	type
        //   203	241	297	java/lang/Throwable
        //   245	262	297	java/lang/Throwable
        //   269	296	297	java/lang/Throwable
        //   431	458	297	java/lang/Throwable
        //   460	492	297	java/lang/Throwable
        //   170	179	312	java/lang/Exception
        //   336	386	389	java/lang/Exception
        //   262	269	406	java/lang/Exception
        //   262	269	459	finally
        //   407	431	459	finally
      }
    }).start();
  }
  
  public void getCurrentOrderCB(String paramString)
  {
    callbackNativeCB("getCurrentOrderCB('" + paramString + "');");
  }
  
  @JavascriptInterface
  public void getDataConnectionState()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String str = com.privacystar.core.service.c.c(JavaScriptInterface.this.getContext());
          localJSONObject = new JSONObject();
          if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(str))
          {
            if (com.privacystar.common.sdk.org.metova.a.h.d.b.c("mobile", str)) {
              localJSONObject.put("data connection", 1);
            }
            for (;;)
            {
              localJSONObject.put("connection name", str);
              JavaScriptInterface.this.callbackNativeCB("getDataConnectionStateCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
              return;
              if ((!com.privacystar.common.sdk.org.metova.a.h.d.b.c("wifi", str)) && (!com.privacystar.common.sdk.org.metova.a.h.d.b.c("wi-fi", str))) {
                break;
              }
              localJSONObject.put("data connection", 2);
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            JSONObject localJSONObject;
            com.privacystar.common.c.a.d("JavaScriptInterface#getDataConnectionState", "Error reporting  data connection state", JavaScriptInterface.this.getContext());
            if (!s.a(JavaScriptInterface.this.getContext())) {
              break;
            }
            h.a("getDataConnectionState() " + localException.getMessage(), false);
            return;
            if (com.privacystar.common.sdk.org.metova.a.h.d.b.c("wifi", localException))
            {
              localJSONObject.put("data connection", 3);
              continue;
              localJSONObject.put("data connection", 0);
            }
          }
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public int getDataConnectionStateValue()
  {
    try
    {
      String str = com.privacystar.core.service.c.c(getContext());
      if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(str))
      {
        if (com.privacystar.common.sdk.org.metova.a.h.d.b.c("mobile", str)) {
          return 1;
        }
        if (!com.privacystar.common.sdk.org.metova.a.h.d.b.c("wifi", str))
        {
          boolean bool = com.privacystar.common.sdk.org.metova.a.h.d.b.c("wi-fi", str);
          if (!bool) {}
        }
        else
        {
          return 2;
        }
        return 3;
      }
    }
    catch (Exception localException)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#getDataConnectionStateValue", "Error reporting  data connection state", getContext());
      if (s.a(getContext())) {
        h.a("getDataConnectionStateValue() " + localException.getMessage(), false);
      }
    }
    return 0;
  }
  
  @JavascriptInterface
  public void getDeviceImei()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("IMEI", j.m(JavaScriptInterface.this.context));
          JavaScriptInterface.this.callbackNativeCB("getDeviceImeiCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (JSONException localJSONException)
        {
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getDeviceImei() " + localJSONException.getMessage(), false);
          }
          localJSONException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getDeviceImsi()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("IMSI", j.l(JavaScriptInterface.this.context));
          JavaScriptInterface.this.callbackNativeCB("getDeviceImsiCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (JSONException localJSONException)
        {
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getDeviceImsi() " + localJSONException.getMessage(), false);
          }
          localJSONException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getDeviceInfo()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String str = com.privacystar.core.service.b.b.a(j.e(JavaScriptInterface.this.getContext()));
          JavaScriptInterface.this.callbackNativeCB("getDeviceInfoCB('" + str.toString() + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getDeviceInfo", "Error getting device info JSON", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getDeviceInfo() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getDeviceNetworkType()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("NetworkType", Integer.toString(this.a));
          JavaScriptInterface.this.callbackNativeCB("getDeviceNetworkTypeCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getDeviceNetworkType", "Error retreiving network type", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getDeviceNetworkType() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getDevicePin()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String str = com.privacystar.core.service.b.b.a(j.f(JavaScriptInterface.this.getContext()));
          JavaScriptInterface.this.callbackNativeCB("getDevicePinCB('" + str + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getDevicePin", "Error reporting device pin", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getDevicePin() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getDeviceRadioType()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("RadioType", Integer.toString(this.a));
          JavaScriptInterface.this.callbackNativeCB("getDeviceRadioTypeCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getDeviceRadioType", "Error retreiving radio type", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getDeviceRadioType() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getDeviceSimSerialNumber()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("ICCID", this.a);
          JavaScriptInterface.this.callbackNativeCB("getDeviceSimSerialNumberCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getDeviceSimSerialNumber", "Error retreiving ICCID aka sim serial number", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getDeviceSimSerialNumber() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public WebView getDialogWebView()
  {
    return this.dialogWebView;
  }
  
  @JavascriptInterface
  public void getEmailAddress()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONArray localJSONArray = new JSONArray();
        String[] arrayOfString = j.a(JavaScriptInterface.this.getContext());
        JSONObject localJSONObject = new JSONObject();
        for (;;)
        {
          int i;
          try
          {
            j = arrayOfString.length;
            if (j != 0) {
              break label168;
            }
            localJSONObject.put("emailAddresses", null);
            JavaScriptInterface.this.callbackNativeCB("getEmailAddressCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
            return;
          }
          catch (Exception localException)
          {
            int j;
            com.privacystar.common.c.a.d("JavaScriptInterface#getEmailAddress", "JSONException creating email list JSON", JavaScriptInterface.this.getContext());
            if (!s.a(JavaScriptInterface.this.getContext())) {
              continue;
            }
            h.a("getEmailAddress() " + localException.getMessage(), false);
            localException.printStackTrace();
            return;
          }
          if (i < j)
          {
            localJSONArray.put(arrayOfString[i]);
            i += 1;
          }
          else
          {
            localJSONObject.put("emailAddresses", localJSONArray);
            continue;
            label168:
            i = 0;
          }
        }
      }
    }).start();
  }
  
  public String getEmailAddressString()
  {
    return com.privacystar.core.service.d.a.e(getContext());
  }
  
  @JavascriptInterface
  public float getFontScale()
  {
    return getContext().getResources().getConfiguration().fontScale;
  }
  
  @JavascriptInterface
  public void getGroupedCallLog(String paramString1, String paramString2, String paramString3, String paramString4) {}
  
  @JavascriptInterface
  public void getGroupedSmsLog(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String[] arrayOfString = j.d(JavaScriptInterface.this.getContext());
          JavaScriptInterface.this.callbackNativeCB("getGroupedCallLogCB('" + arrayOfString[0] + "'); com.privacystar.nativecb.getGroupedSmsLogCB('" + arrayOfString[1] + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getSmsLog", "Error retreiving or reporting sms log", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getSmsLog() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public int getId()
  {
    return this.id;
  }
  
  @JavascriptInterface
  public void getInstalledApplications()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        PackageManager localPackageManager = JavaScriptInterface.this.getContext().getPackageManager();
        Object localObject = localPackageManager.getInstalledApplications(0);
        JSONObject localJSONObject1 = new JSONObject();
        JSONArray localJSONArray = new JSONArray();
        try
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("name", localApplicationInfo.loadLabel(localPackageManager));
            localJSONObject2.put("package", localApplicationInfo.packageName);
            localJSONObject2.put("category", "Social");
            localJSONArray.put(localJSONObject2);
          }
          localJSONObject1.put("result", localJSONArray);
        }
        catch (JSONException localJSONException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getInstalledApplcations", "Error reporting installed applications", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getInstalledApplications() " + localJSONException.getMessage(), false);
          }
          localJSONException.printStackTrace();
          return;
        }
        JavaScriptInterface.this.callbackNativeCB("getInstalledApplicationsCB('" + com.privacystar.core.service.b.b.a(localJSONObject1) + "');");
      }
    }).start();
  }
  
  public void getJSProperties(int paramInt, String... paramVarArgs)
  {
    StringBuffer localStringBuffer = new StringBuffer("javascript:window.Android.getJSPropertiesCB(");
    localStringBuffer.append(paramInt).append(',').append(Arrays.toString(paramVarArgs)).append(");");
    callbackRaw(localStringBuffer.toString());
  }
  
  @JavascriptInterface
  public void getJSPropertiesCB(int paramInt, String[] paramArrayOfString)
  {
    com.privacystar.core.javascript.a localA = o.b(paramInt);
    if (localA != null) {
      localA.a(paramArrayOfString);
    }
  }
  
  @JavascriptInterface
  public void getLastIncomingNumber()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("phone", this.a);
          JavaScriptInterface.this.callbackNativeCB("getLastIncomingNumberCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getLastIncomingNumber", "Error retreiving last incoming call", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getLastIncomingNumber() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getLicenseData(final String paramString1, final int paramInt1, final String paramString2, final String paramString3, final int paramInt2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        com.privacystar.core.service.d.a.h(paramString1, JavaScriptInterface.this.getContext());
        com.privacystar.core.service.d.a.i(paramString2, JavaScriptInterface.this.getContext());
        com.privacystar.common.sdk.org.metova.a.f.a.a.b localB = com.privacystar.common.sdk.org.metova.android.provisioning.service.b.a.a().b();
        Object localObject;
        if ((localB != null) && (paramInt2 != 1))
        {
          com.privacystar.core.service.c.a.a();
          localObject = com.privacystar.core.service.c.a.a(localB, JavaScriptInterface.this.getContext());
        }
        for (;;)
        {
          JavaScriptInterface.this.getLicenseDataCB(com.privacystar.core.service.b.b.a((JSONObject)localObject));
          JSONObject localJSONObject;
          do
          {
            return;
            localJSONObject = com.privacystar.core.service.c.a.a().a(paramString1, paramInt1, paramString2, paramString3, JavaScriptInterface.this.getContext());
          } while (localJSONObject == null);
          localObject = localJSONObject;
          if (localJSONObject.length() == 0)
          {
            com.privacystar.core.service.c.a.a();
            localObject = com.privacystar.core.service.c.a.a(localB, JavaScriptInterface.this.getContext());
          }
        }
      }
    }).start();
  }
  
  public void getLicenseDataCB(String paramString)
  {
    callbackNativeCB("getLicenseDataCB('" + paramString + "');");
  }
  
  @JavascriptInterface
  public void getLocale()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("locale", Locale.getDefault().toString());
          JavaScriptInterface.this.callbackNativeCB("getLocaleCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getLocale", "Error ", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getLocale() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public String getLocaleString()
  {
    return Locale.getDefault().toString();
  }
  
  @JavascriptInterface
  public void getLocation()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          Object localObject = new m(JavaScriptInterface.this.getContext());
          double d1 = ((m)localObject).a();
          double d2 = ((m)localObject).b();
          localObject = "{\"lat\":\"" + d1 + "\", \"lon\":\"" + d2 + "\"}";
          JavaScriptInterface.this.callbackNativeCB("getLocationCB('" + (String)localObject + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getLocation", "Error retreiving or reporting location", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getLocation() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
          JavaScriptInterface.this.sendEmailMessageCB(false);
        }
      }
    }).start();
  }
  
  public double[] getLocationArray()
  {
    m localM = new m(getContext());
    return new double[] { localM.a(), localM.b() };
  }
  
  @JavascriptInterface
  public void getNameFromContacts(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String str = com.privacystar.common.sdk.org.metova.android.b.a.a.a(JavaScriptInterface.this.getContext().getContentResolver(), paramString);
          JavaScriptInterface.this.getNameFromContactsCB(str);
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.a("JavaScriptInterface#getNameFromContacts", "while calling getNameFromContacts", localException, JavaScriptInterface.this.getContext());
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getNameFromContactsCB(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramString)) {
        localJSONObject.put("name", paramString);
      }
      callbackNativeCB("getNameFromContactsCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
      return;
    }
    catch (Exception paramString)
    {
      com.privacystar.common.c.a.a("JavaScriptInterface#getNameFromContactsCB", "calling callback getNameFromContactsCB", paramString, getContext());
      paramString.printStackTrace();
    }
  }
  
  public WebView getParentWebView()
  {
    return this.parentWebView;
  }
  
  @JavascriptInterface
  public void getPhoneNumber()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        String str = j.b(JavaScriptInterface.this.getContext());
        try
        {
          localJSONObject.put("phoneNumber", str);
          JavaScriptInterface.this.callbackNativeCB("getPhoneNumberCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getPhoneNumber", "Error creating phone number JSON", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getPhoneNumber() --> " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public String getPhoneNumberString()
  {
    String str2 = com.privacystar.core.service.d.a.d(getContext());
    String str1 = str2;
    if (str2.equals(""))
    {
      getContext().getSystemService("phone");
      str1 = com.privacystar.core.service.d.a.d(getContext());
    }
    return str1;
  }
  
  @JavascriptInterface
  public void getRegionalSettings()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("dateFormat", j.i(JavaScriptInterface.this.getContext()));
          localJSONObject.put("timeFormat", j.c(JavaScriptInterface.this.getContext()));
          localJSONObject.put("timeZone", j.a());
          localJSONObject.put("numberFormat", j.d());
          localJSONObject.put("currencySymbol", j.b());
          JavaScriptInterface.this.callbackNativeCB("getRegionalSettingsCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getRegionalSettings", localException.getMessage(), JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getRegionalSettings() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getSharedPreference(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        String str = com.privacystar.core.service.d.a.k(paramString, JavaScriptInterface.this.getContext());
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put(paramString, str);
          JavaScriptInterface.this.callbackNativeCB("getSharedPreferenceCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#getSharedPreference", "Error creating callback JSON", JavaScriptInterface.this.getContext());
            if (s.a(JavaScriptInterface.this.getContext())) {
              h.a("getSharedPreference() " + localException.getMessage(), false);
            }
          }
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getSimOperator()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("operator", j.n(JavaScriptInterface.this.context));
          JavaScriptInterface.this.callbackNativeCB("getSimOperatorCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (JSONException localJSONException)
        {
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getSimOperator() " + localJSONException.getMessage(), false);
          }
          localJSONException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getSmsLog(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          Object localObject = JavaScriptInterface.this;
          Context localContext = JavaScriptInterface.this.getContext();
          String str1 = paramString1;
          String str2 = paramString2;
          String str3 = paramString3;
          String str4 = paramString4;
          localObject = ((JavaScriptInterface)localObject).getJsonForLogs(j.a(localContext, str1, str2, str3));
          JavaScriptInterface.this.callbackNativeCB("getSmsLogCB('" + (String)localObject + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getSmsLog", "retreiving or reporting sms log", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getSmsLog() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getSmsText(final int paramInt)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          String str = com.privacystar.common.sdk.org.a.a.b.a.a(j.a(JavaScriptInterface.this.getContext(), paramInt));
          com.privacystar.common.c.a.b("JavaScriptInterface.getSmsText(...).new Runnable() {...}#run", "Retrieving message body for " + paramInt + ".", JavaScriptInterface.this.getContext());
          JavaScriptInterface.this.callbackNativeCB("getSmsTextCB('" + str + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#getSmsText", "Error retreiving message body for id " + paramInt, JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("getSmsText() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getStringHash(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("hashValue", d.a(paramString));
          JavaScriptInterface.this.callbackNativeCB("getStringHashCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#getStringHash", "Error hashing string or adding hash to Json", JavaScriptInterface.this.getContext());
            if (s.a(JavaScriptInterface.this.getContext())) {
              h.a("getStringHash() " + localException.getMessage(), false);
            }
            localException.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void getWebViewHeight()
  {
    com.privacystar.common.c.a.b("JavaScriptInterface#getWebViewHeight", "Measured height: " + getParentWebView().getMeasuredHeight(), getContext());
    callbackNativeCB("getWebViewHeightCB(" + getParentWebView().getMeasuredHeight() + ");");
  }
  
  @JavascriptInterface
  public void getWebViewWidth()
  {
    com.privacystar.common.c.a.b("JavaScriptInterface#getWebViewWidth", "Measured width: " + getParentWebView().getMeasuredWidth(), getContext());
    callbackNativeCB("getWebViewWidthCB(" + getParentWebView().getMeasuredWidth() + ");");
  }
  
  @JavascriptInterface
  public void getWindowHeight()
  {
    Rect localRect = new Rect();
    this.parentWebView.getWindowVisibleDisplayFrame(localRect);
    callbackNativeCB("getWindowHeightCB(" + localRect.height() + ");");
  }
  
  @JavascriptInterface
  public void getWindowWidth()
  {
    Rect localRect = new Rect();
    this.parentWebView.getWindowVisibleDisplayFrame(localRect);
    callbackNativeCB("getWindowWidthCB(" + localRect.width() + ");");
  }
  
  @JavascriptInterface
  public void goBack()
  {
    callbackNativeCB("goBack();");
  }
  
  @JavascriptInterface
  public void handleAdCallback(JSONObject paramJSONObject)
  {
    com.privacystar.core.a.c.e().a(paramJSONObject, getAdLocation());
  }
  
  public void handleXtifyWSCallback(JSONObject paramJSONObject)
  {
    bool2 = false;
    String str1 = com.privacystar.core.service.d.a.H(getContext());
    bool1 = bool2;
    if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(str1)) {}
    for (;;)
    {
      try
      {
        if (paramJSONObject.getInt("httpStatusCode") != 200) {
          continue;
        }
        localObject1 = new JSONObject(paramJSONObject.getString("resultData"));
        paramJSONObject = new JSONArray(str1);
        if (paramJSONObject.length() <= 0) {
          continue;
        }
        localObject2 = paramJSONObject.getJSONObject(0);
        str2 = ((JSONObject)localObject1).optString("html");
        str3 = ((JSONObject)localObject1).optString("title");
        paramJSONObject = ((JSONObject)localObject1).optString("nT");
        localObject1 = ((JSONObject)localObject1).optString("nM");
        bool1 = com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramJSONObject);
        if (bool1) {
          continue;
        }
      }
      catch (Exception paramJSONObject)
      {
        Object localObject1;
        Object localObject2;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        com.privacystar.common.c.a.a("JavaScriptInterface#handleXtifyWSCallback", "processing the Xtify pull WS request", paramJSONObject, getContext());
        paramJSONObject.printStackTrace();
        bool1 = bool2;
        continue;
        JSONObject localJSONObject = paramJSONObject;
        continue;
        continue;
        continue;
        bool1 = false;
        continue;
        continue;
      }
      try
      {
        paramJSONObject = new String(com.privacystar.core.googleplay.a.a.a(paramJSONObject));
        localObject1 = new String(com.privacystar.core.googleplay.a.a.a((String)localObject1));
        str4 = ((JSONObject)localObject2).getString("xtifyTranId");
        str5 = ((JSONObject)localObject2).getString("notifId");
        str6 = ((JSONObject)localObject2).getString("from");
        str7 = ((JSONObject)localObject2).getString("batchId");
        if (com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramJSONObject))
        {
          str1 = ((JSONObject)localObject2).getString("notifTicker");
          if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramJSONObject)) {
            continue;
          }
          paramJSONObject = ((JSONObject)localObject2).getString("notifTitle");
          if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b((String)localObject1)) {
            continue;
          }
          localObject1 = ((JSONObject)localObject2).getString("notifMessage");
          localObject2 = v.a(str4, str5, str6, ((JSONObject)localObject2).getInt("messageType"), str3, str2, str7, this.context);
          if (com.privacystar.common.sdk.org.metova.a.h.d.b.b((String)localObject2)) {
            continue;
          }
          p.a(str1, paramJSONObject, (String)localObject1, (String)localObject2, Long.valueOf(604800000L).longValue(), this.context);
          bool1 = true;
          callQueueComplete(bool1);
        }
      }
      catch (com.privacystar.core.googleplay.a.b localB)
      {
        paramJSONObject = "";
        localObject1 = "";
        com.privacystar.common.c.a.c("XtifyUtil#handleReceived", "Error decoding title or message from Base64.", this.context);
        localB.printStackTrace();
      }
    }
  }
  
  @JavascriptInterface
  public void hideLiveCallerId()
  {
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        OverlayView localOverlayView = CallerIdService.f();
        if (localOverlayView != null) {
          localOverlayView.i();
        }
      }
    });
  }
  
  @JavascriptInterface
  public void hideLiveCallerIdAfterPhoneApp()
  {
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        CallerIdService.f();
        CallerIdViewV2.j();
      }
    });
  }
  
  @JavascriptInterface
  public void isAppInstalled(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          JavaScriptInterface localJavaScriptInterface = JavaScriptInterface.this;
          StringBuilder localStringBuilder = new StringBuilder("isAppInstalledCB('");
          String str = paramString;
          localJavaScriptInterface.callbackNativeCB(r.a(JavaScriptInterface.this.getContext()).contains(str) + "');");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#isAppInstalled", "Error checking for application", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("isAppInstalled() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
          JavaScriptInterface.this.callbackNativeCB("isAppInstalledCB('false');");
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void isOriginalAppInstalled()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          JavaScriptInterface localJavaScriptInterface = JavaScriptInterface.this;
          StringBuilder localStringBuilder = new StringBuilder("isOriginalAppInstalledCB('");
          Context localContext = JavaScriptInterface.this.getContext();
          boolean bool1 = r.a(localContext).contains("com.privacystar.android");
          boolean bool2 = com.privacystar.common.sdk.org.metova.a.h.d.b.e(localContext.getPackageName(), "com.privacystar.android");
          if ((bool1) && (!bool2)) {}
          for (bool1 = true;; bool1 = false)
          {
            localJavaScriptInterface.callbackNativeCB(bool1 + "');");
            return;
          }
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#isOriginalAppInstalled", "Error checking for old application packages", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("isOriginalAppInstalled() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void launchAdUrl(String paramString)
  {
    launch(paramString, false);
  }
  
  @JavascriptInterface
  public void launchApplication(String paramString)
  {
    launch(paramString, true);
  }
  
  @JavascriptInterface
  public void nativeMessageLoggedCB(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      callbackNativeCB("nativeMessageLoggedCB('" + paramString1 + ", " + paramString2 + ", " + paramString3 + "');");
      return;
    }
    catch (Exception paramString1)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#nativeMessageLoggedCB", "Error reporting log: " + paramString1.getMessage(), getContext());
      if (s.a(getContext())) {
        h.a("nativeMessageLoggedCB() " + paramString1.getMessage(), false);
      }
      paramString1.printStackTrace();
    }
  }
  
  public void newXidReceived(String paramString)
  {
    callbackToJavaScriptService("newXidReceivedCB('" + paramString + "');");
  }
  
  public void notificationSelectedCB(String paramString)
  {
    callbackNativeCB("notificationSelectedCB('" + paramString + "');");
  }
  
  @JavascriptInterface
  public void notifyOnReady() {}
  
  public void notifyOptionMenuItemSelectedCB(String paramString)
  {
    callbackNativeCB("notifyOptionMenuItemSelectedCB('" + paramString + "');");
  }
  
  @JavascriptInterface
  public void onInAppBillingErrorCB(String paramString)
  {
    callbackNativeCB("onInAppBillingErrorCB('" + paramString + "');");
  }
  
  @JavascriptInterface
  public void onLongPress(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder("onSwipeCB('");
    if (paramBoolean) {}
    for (String str = "start";; str = "end")
    {
      callbackNativeCB(str + "');");
      return;
    }
  }
  
  @JavascriptInterface
  public void onSearchBarFocused()
  {
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        CallerIdService.a(true);
      }
    });
  }
  
  @JavascriptInterface
  public void onSearchBarUnfocused()
  {
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        CallerIdService.a(false);
      }
    });
  }
  
  @JavascriptInterface
  public void onSwipe(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      callbackNativeCB("onSwipeCB(" + i + "," + paramInt1 + "," + paramInt2 + "," + paramInt3 + "," + paramInt4 + ");");
      com.privacystar.common.c.a.b("JavaScriptInterface.onSwipe", "Callback looks like: (" + i + "," + paramInt1 + "," + paramInt2 + "," + paramInt3 + "," + paramInt4 + ");", getContext());
      return;
    }
  }
  
  @JavascriptInterface
  public void openAddContactScreen(final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
        localIntent.putExtra("name", paramString1);
        localIntent.putExtra("phone", paramString2);
        localIntent.addFlags(268435456);
        JavaScriptInterface.this.getContext().startActivity(localIntent);
      }
    }).start();
  }
  
  @JavascriptInterface
  public void openAppWithRoute(String paramString)
  {
    try
    {
      com.privacystar.common.c.a.b("JavaScriptInterface#openAppWithRoute", "Route: " + paramString, this.context);
      com.privacystar.core.service.d.a.a(getContext(), "route", paramString);
      paramString = new Intent(getContext(), EulaActivity.class);
      paramString.setFlags(67108864);
      paramString.setFlags(268435456);
      getContext().startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      com.privacystar.common.c.a.a("JavaScriptInterface#openAppWithRoute", "while opening app to route", paramString, getContext());
    }
  }
  
  @JavascriptInterface
  public void openCancelScreen()
  {
    com.privacystar.common.c.a.b("JavaScriptInterface#startCancel", "Sending intent for cancel.", getContext());
    Intent localIntent = new Intent(com.privacystar.core.googleplay.a.b);
    localIntent.putExtra("iab_intent_action", com.privacystar.core.googleplay.a.e);
    l.a(getContext()).a(localIntent);
  }
  
  @JavascriptInterface
  public void openContactScreen(String paramString)
  {
    try
    {
      com.privacystar.common.c.a.b("JavaScriptInterface#openContactsScreen", "Lookup key: " + paramString, getContext());
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, paramString));
      localIntent.addFlags(268435456);
      this.context.startActivity(localIntent);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void openOverlayDialog(String paramString)
  {
    CallerIdService.a(paramString, this);
  }
  
  @JavascriptInterface
  public void printPSAnalyticsSettings() {}
  
  @JavascriptInterface
  public void purchaseItem(String paramString1, String paramString2)
  {
    purchaseItem(paramString1, paramString2, 3);
  }
  
  @JavascriptInterface
  public void purchaseItem(String paramString1, String paramString2, int paramInt)
  {
    if (paramInt == 2)
    {
      localIntent = new Intent(com.privacystar.core.googleplay.a.b);
      localIntent.putExtra("iab_intent_action", com.privacystar.core.googleplay.a.c);
      localIntent.putExtra("product_sku", paramString1);
      localIntent.putExtra("payload", paramString2);
      l.a(getContext()).a(localIntent);
      return;
    }
    Intent localIntent = new Intent(getContext(), IABV3Activity.class);
    localIntent.putExtra("iab_intent_action", com.privacystar.core.googleplay.a.c);
    localIntent.putExtra("product_sku", paramString1);
    localIntent.putExtra("payload", paramString2);
    localIntent.putExtra("javaScriptInterfaceIdExtra", getId());
    localIntent.addFlags(268435456);
    getContext().startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void purchaseStateChangedCB(String paramString1, String paramString2)
  {
    callbackNativeCB("purchaseStateChangedCB('" + paramString1 + "', '" + paramString2 + "');");
  }
  
  @JavascriptInterface
  public void requestFileDownload(final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        com.privacystar.core.service.c.a(paramString1, paramString2, v.a(), JavaScriptInterface.this, JavaScriptInterface.this.getContext());
      }
    }).start();
  }
  
  @JavascriptInterface
  public void sendEmailMessage(final String paramString1, final String paramString2, final String paramString3)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.setType("message/rfc822");
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { paramString1 });
          localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
          localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(paramString3));
          localIntent.setFlags(268435456);
          JavaScriptInterface.this.getContext().startActivity(localIntent);
          JavaScriptInterface.this.sendEmailMessageCB(true);
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#sendEmailMessage", "Error sending email", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("sendEmailMessage() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
          JavaScriptInterface.this.sendEmailMessageCB(false);
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void sendEmailMessageCB(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "{ \"result\": " + "true}";; str = "{ \"result\": " + "false}")
    {
      callbackNativeCB("sendEmailMessageCB('" + str + "');");
      return;
    }
  }
  
  @JavascriptInterface
  public void sendHTTPRequest(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        if (paramString2.equalsIgnoreCase("post")) {
          com.privacystar.core.service.c.a(paramString1, paramString3, paramString4, paramInt, paramString5, JavaScriptInterface.this, JavaScriptInterface.this.getContext());
        }
        do
        {
          return;
          if (paramString2.equalsIgnoreCase("get"))
          {
            com.privacystar.core.service.c.a(paramString1, paramString4, paramInt, paramString5, JavaScriptInterface.this, JavaScriptInterface.this.getContext());
            return;
          }
        } while (!paramString2.equalsIgnoreCase("PUT"));
        com.privacystar.core.service.c.b(paramString1, paramString3, paramString4, paramInt, paramString5, JavaScriptInterface.this, JavaScriptInterface.this.getContext());
      }
    }).start();
  }
  
  @JavascriptInterface
  public void sendHTTPRequestCB(JSONObject paramJSONObject)
  {
    if (paramJSONObject.has("tranId")) {
      try
      {
        if (com.privacystar.common.sdk.org.metova.a.h.d.b.e(paramJSONObject.getString("tranId"), "getNewAdNative"))
        {
          handleAdCallback(paramJSONObject);
          return;
        }
        if (com.privacystar.common.sdk.org.metova.a.h.d.b.e(paramJSONObject.getString("tranId"), "sendTrackingNotif"))
        {
          handleXtifyWSCallback(paramJSONObject);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    callbackNativeCB("sendHTTPRequestCB('" + com.privacystar.core.service.b.b.a(paramJSONObject) + "');");
  }
  
  @JavascriptInterface
  public void sendPagedWSRequest(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5)
  {
    sendWSRequest(paramString1, paramString2, paramString3, paramString4, paramInt, paramString5, true);
  }
  
  public void sendRequestFileDownloadCB(boolean paramBoolean, String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("success", paramBoolean).put("fileName", paramString1);
      if (!com.privacystar.common.sdk.org.metova.a.h.d.b.c(paramString2)) {
        localJSONObject.put("extension", paramString2);
      }
      callbackNativeCB("requestFileDownloadCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
      return;
    }
    catch (JSONException paramString1)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#sendRequestFileDownloadCB", paramString1.getMessage(), getContext());
      if (s.a(getContext())) {
        h.a("sendRequestFileDownloadCB() " + paramString1.getMessage(), false);
      }
      paramString1.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void sendSMS(final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          PendingIntent localPendingIntent = PendingIntent.getBroadcast(JavaScriptInterface.this.context, 0, new Intent(), 0);
          SmsManager.getDefault().sendTextMessage(paramString1, null, paramString2, localPendingIntent, localPendingIntent);
          JavaScriptInterface.this.sendSMSCB(true);
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#sendSMS", "Error sending sms", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("sendSMS() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
          JavaScriptInterface.this.sendSMSCB(false);
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void sendSMSCB(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "{ \"result\": " + "true}";; str = "{ \"result\": " + "false}")
    {
      callbackNativeCB("sendSMSCB('" + str + "');");
      return;
    }
  }
  
  @JavascriptInterface
  public void sendWSRequest(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5)
  {
    sendWSRequest(paramString1, paramString2, paramString3, paramString4, paramInt, paramString5, false);
  }
  
  @JavascriptInterface
  public void sendWSRequest(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final int paramInt, final String paramString5, final boolean paramBoolean)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        if (paramString2.equalsIgnoreCase("post"))
        {
          localObject1 = paramString1;
          str = paramString2;
          com.privacystar.core.service.c.a((String)localObject1, paramString3, paramString4, paramInt, paramString5, JavaScriptInterface.this, paramBoolean, JavaScriptInterface.this.getContext());
        }
        while (!paramString2.equalsIgnoreCase("get")) {
          return;
        }
        Object localObject2 = paramString1;
        Object localObject1 = paramString2;
        Object localObject3 = paramString4;
        int i = paramInt;
        String str = paramString5;
        JavaScriptInterface localJavaScriptInterface = JavaScriptInterface.this;
        localObject1 = JavaScriptInterface.this.getContext();
        com.privacystar.core.service.c.a((String)localObject2);
        localObject2 = new HttpGet((String)localObject2);
        com.privacystar.core.service.c.a((String)localObject3, (HttpRequestBase)localObject2, (Context)localObject1);
        localObject3 = new DefaultHttpClient();
        try
        {
          localObject2 = ((DefaultHttpClient)localObject3).execute((HttpUriRequest)localObject2);
          new com.privacystar.core.service.a().a((HttpResponse)localObject2, "", i, localJavaScriptInterface, PrivacyStarApplication.a(), str, false);
          return;
        }
        catch (Exception localException2)
        {
          localObject3 = new JSONObject();
          com.privacystar.common.c.a.a("WebServices#sendTwoStepGetRequest", "while attempting to send web service get request", localException2, (Context)localObject1);
          localException2.printStackTrace();
          try
          {
            ((JSONObject)localObject3).put("tranId", str);
            ((JSONObject)localObject3).put("httpStatusCode", -1);
            ((JSONObject)localObject3).put("exceptionText", localException2.getMessage());
            localJavaScriptInterface.sendWSRequestCB((JSONObject)localObject3);
            return;
          }
          catch (Exception localException1)
          {
            com.privacystar.common.c.a.a("WebServices#sendTwoStepGetRequest", " while processing web service get callback. Nothing sent", localException1, (Context)localObject1);
            localException1.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void sendWSRequestCB(JSONObject paramJSONObject)
  {
    callbackNativeCB("sendWSRequestCB('" + com.privacystar.core.service.b.b.a(paramJSONObject) + "');");
  }
  
  @JavascriptInterface
  public void setAdVisibility(boolean paramBoolean)
  {
    com.privacystar.core.a.c.e().a(getAdLocation(), getContext(), paramBoolean, true);
  }
  
  @JavascriptInterface
  public void setCIDFullscreen(boolean paramBoolean)
  {
    setCIDFullscreen(paramBoolean, false, null);
  }
  
  @JavascriptInterface
  public void setCreateOrModifyContact(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        for (;;)
        {
          int i;
          int j;
          try
          {
            Object localObject1 = new JSONObject(paramString);
            Context localContext = JavaScriptInterface.this.getContext();
            try
            {
              localObject1 = ((JSONObject)localObject1).getJSONArray("vcard");
              ContentResolver localContentResolver = localContext.getContentResolver();
              i = 0;
              if (i < ((JSONArray)localObject1).length())
              {
                Object localObject3 = ((JSONArray)localObject1).getJSONObject(i);
                Object localObject2 = ((JSONObject)localObject3).getJSONArray("tel");
                j = 0;
                int k = 0;
                if (j < ((JSONArray)localObject2).length())
                {
                  if (com.privacystar.common.sdk.org.metova.a.h.d.b.b(com.privacystar.common.sdk.org.metova.android.b.a.a.b(localContentResolver, ((JSONArray)localObject2).getJSONObject(j).getString("value")))) {
                    break label548;
                  }
                  k = 1;
                  break label548;
                }
                if (k != 0)
                {
                  ((PrivacyStarApplication)localContext.getApplicationContext()).b().setCreateOrModifyContactCB(false);
                }
                else
                {
                  localObject2 = new ArrayList();
                  Object localObject4 = ((JSONObject)localObject3).getJSONObject("n");
                  JSONArray localJSONArray = ((JSONObject)localObject3).getJSONArray("tel");
                  ((ArrayList)localObject2).add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
                  ContentProviderOperation.Builder localBuilder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
                  localBuilder.withValueBackReference("raw_contact_id", 0);
                  localBuilder.withValue("mimetype", "vnd.android.cursor.item/name");
                  localBuilder.withValue("data2", ((JSONObject)localObject4).get("given-name"));
                  localBuilder.withValue("data3", ((JSONObject)localObject4).get("family-name"));
                  localBuilder.withValue("data1", ((JSONObject)localObject3).getString("fn"));
                  ((ArrayList)localObject2).add(localBuilder.build());
                  localObject4 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
                  ((ContentProviderOperation.Builder)localObject4).withValueBackReference("raw_contact_id", 0);
                  ((ContentProviderOperation.Builder)localObject4).withValue("mimetype", "vnd.android.cursor.item/name");
                  ((ContentProviderOperation.Builder)localObject4).withValue("data1", ((JSONObject)localObject3).getString("fn"));
                  ((ArrayList)localObject2).add(((ContentProviderOperation.Builder)localObject4).build());
                  j = 0;
                  if (j < localJSONArray.length())
                  {
                    localObject3 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
                    ((ContentProviderOperation.Builder)localObject3).withValueBackReference("raw_contact_id", 0);
                    ((ContentProviderOperation.Builder)localObject3).withValue("mimetype", "vnd.android.cursor.item/phone_v2");
                    localObject4 = localJSONArray.getJSONObject(j);
                    ((ContentProviderOperation.Builder)localObject3).withValue("data1", ((JSONObject)localObject4).getString("value"));
                    ((ContentProviderOperation.Builder)localObject3).withValue("data2", Integer.valueOf(((JSONObject)localObject4).getInt("type")));
                    ((ArrayList)localObject2).add(((ContentProviderOperation.Builder)localObject3).build());
                    j += 1;
                    continue;
                  }
                  localContentResolver.applyBatch("com.android.contacts", (ArrayList)localObject2);
                  ((PrivacyStarApplication)localContext.getApplicationContext()).b().setCreateOrModifyContactCB(true);
                }
              }
            }
            catch (Exception localException)
            {
              com.privacystar.common.c.a.a("ContactUtil#createOrEditContact", "while adding or editing conntact", localException, localContext);
              localException.printStackTrace();
              ((PrivacyStarApplication)localContext.getApplicationContext()).b().setCreateOrModifyContactCB(false);
            }
            return;
          }
          catch (JSONException localJSONException)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#setCreateOrModifyContact", "Error parsing JSON from string: " + paramString, JavaScriptInterface.this.getContext());
            if (!s.a(JavaScriptInterface.this.getContext())) {
              continue;
            }
            h.a("setCreateOrModifyContact() " + localJSONException.getMessage(), false);
            return;
          }
          label548:
          j += 1;
          continue;
          i += 1;
        }
      }
    }).start();
  }
  
  public void setCreateOrModifyContactCB(boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("result", paramBoolean);
      callbackNativeCB("setCreateOrModifyContactCB('" + localJSONObject.toString() + "');");
      return;
    }
    catch (Exception localException)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#setCreateOrModifyContactCB", "Error calling callback setCreateOrModifyContactCB", getContext());
      if (s.a(getContext())) {
        h.a("setCreateOrModifyContactCB() " + localException.getMessage(), false);
      }
      localException.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void setDataChanged(String paramString)
  {
    Log.i("JavaScriptInterface#setDataChanged", "UX calling setDataChanged");
    new Thread(new Runnable()
    {
      public final void run()
      {
        CallBlockingReceiver.a();
        CallBlockingReceiver.a(JavaScriptInterface.this.getContext().getApplicationContext());
        ((PrivacyStarApplication)JavaScriptInterface.this.getContext().getApplicationContext()).c().b();
        ((PrivacyStarApplication)JavaScriptInterface.this.getContext().getApplicationContext()).d().a(JavaScriptInterface.this.getContext());
      }
    }).start();
  }
  
  @JavascriptInterface
  public void setDataChangedCB(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "{ \"result\": " + "true}";; str = "{ \"result\": " + "false}")
    {
      callbackNativeCB("setDataChangedCB('" + str + "');");
      return;
    }
  }
  
  @JavascriptInterface
  public void setDialogWebView(WebView paramWebView)
  {
    this.dialogWebView = paramWebView;
  }
  
  @JavascriptInterface
  public void setEmailAddress(String paramString)
  {
    setEmailAddressCB(j.a(this.context, paramString));
  }
  
  @JavascriptInterface
  public void setEmailAddressCB(boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("result", paramBoolean);
      callbackNativeCB("setEmailAddressCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
      return;
    }
    catch (Exception localException)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#setEmailAddressCB", "Error creating set email address JSON", getContext());
      if (s.a(getContext())) {
        h.a("setEmailAddressCB() " + localException.getMessage(), false);
      }
      localException.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void setGmailAddresses(String[] paramArrayOfString)
  {
    Context localContext = this.context;
    j.a(paramArrayOfString);
  }
  
  @JavascriptInterface
  public void setLiveCallerIdToastTemplate(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          com.privacystar.core.service.d.a.f(paramString, JavaScriptInterface.this.getContext());
          JavaScriptInterface.this.setLiveCallerIdToastTemplateCB(true);
          return;
        }
        catch (Throwable localThrowable)
        {
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("setLiveCallerIdToastTemplate() " + localThrowable.getMessage(), false);
          }
          JavaScriptInterface.this.setLiveCallerIdToastTemplateCB(false);
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void setLiveCallerIdToastTemplateCB(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "{ \"result\": " + "true}";; str = "{ \"result\": " + "false}")
    {
      callbackNativeCB("setLiveCallerIdToastTemplateCB('" + str + "');");
      return;
    }
  }
  
  @JavascriptInterface
  public void setMinimumLogVerbosity(String paramString)
  {
    o.a(paramString, true, getContext());
  }
  
  @JavascriptInterface
  public void setOptionMenuItems(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          com.privacystar.core.service.d.a.l(paramString, JavaScriptInterface.this.getContext());
          l.a(JavaScriptInterface.this.getContext()).a(new Intent("menuItemsIntent"));
          JSONObject localJSONObject1 = new JSONObject();
          localJSONObject1.put("result", true);
          JavaScriptInterface.this.callbackNativeCB("setOptionMenuItemsCB('" + com.privacystar.core.service.b.b.a(localJSONObject1) + "');");
          return;
        }
        catch (Exception localException1)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#setOptionMenuItems", "Error setting menu JSON", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("setOptionMenuItems() " + localException1.getMessage(), false);
          }
          localException1.printStackTrace();
          try
          {
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("result", false);
            JavaScriptInterface.this.callbackNativeCB("setOptionMenuItemsCB('" + com.privacystar.core.service.b.b.a(localJSONObject2) + "');");
            return;
          }
          catch (Exception localException2)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#setOptionMenuItems", "Error creating JSON values for menu options CB", JavaScriptInterface.this.getContext());
            if (s.a(JavaScriptInterface.this.getContext())) {
              h.a("setOptionMenuItems():e2 " + localException2.getMessage(), false);
            }
            localException2.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void setPSAnalyticsBitmap(int[] paramArrayOfInt)
  {
    o.a.a(paramArrayOfInt);
    o.a.a(getContext());
  }
  
  public void setParentWebView(WebView paramWebView)
  {
    this.parentWebView = paramWebView;
  }
  
  @JavascriptInterface
  public void setPhoneNumber(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        JavaScriptInterface.this.setPhoneNumberCB(j.b(JavaScriptInterface.this.getContext(), paramString));
      }
    }).start();
  }
  
  @JavascriptInterface
  public void setPhoneNumberCB(boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("result", paramBoolean);
      callbackNativeCB("setPhoneNumberCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
      return;
    }
    catch (Exception localException)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#setPhoneNumberCB", "Error creating set phone number JSON", getContext());
      if (s.a(getContext())) {
        h.a("setPhoneNumberCB() --> " + localException.getMessage(), false);
      }
      localException.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void setReferrer(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("setReferrerIntent");
        localIntent.putExtra("referrerURL", paramString);
        l.a(JavaScriptInterface.this.getContext()).a(localIntent);
      }
    }).start();
  }
  
  @JavascriptInterface
  public void setScheduledEvent(String paramString1, final long paramLong, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        for (;;)
        {
          int i;
          try
          {
            if (paramLong != 0L)
            {
              Object localObject = com.privacystar.core.service.d.a.q(JavaScriptInterface.this.getContext());
              JSONArray localJSONArray = new JSONArray();
              if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b((String)localObject))
              {
                localObject = new JSONArray((String)localObject);
                i = 0;
                if (i < ((JSONArray)localObject).length())
                {
                  JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(i);
                  if (com.privacystar.common.sdk.org.metova.a.h.d.b.e(paramString2, localJSONObject.getString("tranId"))) {
                    break label250;
                  }
                  localJSONArray.put(localJSONObject);
                  break label250;
                }
              }
              localObject = new JSONObject();
              ((JSONObject)localObject).put("frequency", paramLong * 1000L);
              ((JSONObject)localObject).put("startDate", this.c);
              ((JSONObject)localObject).put("tranId", paramString2);
              localJSONArray.put(localObject);
              com.privacystar.core.service.d.a.m(localJSONArray.toString(), JavaScriptInterface.this.getContext());
              UXPerpetualAlarm.a(paramString2, paramLong, e.a(this.c), JavaScriptInterface.this.getContext());
            }
            else
            {
              UXPerpetualAlarm.a(paramString2, JavaScriptInterface.this.getContext());
              return;
            }
          }
          catch (Exception localException)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#setScheduledEvent", "Error setting up new alarm", JavaScriptInterface.this.getContext());
            if (s.a(JavaScriptInterface.this.getContext())) {
              h.a("setScheduledEvent() " + localException.getMessage(), false);
            }
            localException.printStackTrace();
            return;
          }
          label250:
          i += 1;
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void setSharedPreference(final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          boolean bool = com.privacystar.core.service.d.a.a(JavaScriptInterface.this.getContext(), paramString1, paramString2);
          JavaScriptInterface.this.callbackNativeCB("setSharedPreferenceCB(" + bool + ");");
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.d("JavaScriptInterface#setSharedPreference", "Error setting value or attempting callback", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("setSharedPreference() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void setTimer(int paramInt)
  {
    long l = paramInt;
    try
    {
      UXPerpetualAlarm.a("18559726", paramInt, l * 1000L + System.currentTimeMillis(), getContext());
      return;
    }
    catch (JSONException localJSONException)
    {
      com.privacystar.common.c.a.c("JavaScriptInterface#setTimer", "JSONException setting timer!", getContext());
      localJSONException.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void setWebViewHeight(final int paramInt)
  {
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        ViewGroup.LayoutParams localLayoutParams = JavaScriptInterface.this.getParentWebView().getLayoutParams();
        localLayoutParams.height = paramInt;
        JavaScriptInterface.this.getParentWebView().setLayoutParams(localLayoutParams);
      }
    });
  }
  
  @JavascriptInterface
  public void setXtifyRegistration(final int paramInt)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          if (paramInt == 0) {
            com.xtify.sdk.api.c.b(JavaScriptInterface.this.getContext());
          }
          for (;;)
          {
            JavaScriptInterface.this.setXtifyRegistrationCB(com.xtify.sdk.api.c.a(JavaScriptInterface.this.getContext()));
            return;
            com.xtify.sdk.api.c.c(JavaScriptInterface.this.getContext());
          }
          return;
        }
        catch (Exception localException)
        {
          JavaScriptInterface.this.setXtifyRegistrationCB("");
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("setXtifyRegistration() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public void setXtifyRegistrationCB(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("xid", paramString);
      callbackNativeCB("setXtifyRegistrationCB('" + com.privacystar.core.service.b.b.a(localJSONObject) + "');");
      return;
    }
    catch (Exception paramString)
    {
      com.privacystar.common.c.a.d("JavaScriptInterface#setXtifyRegistrationCB", "Error sending Xtify registration", getContext());
      if (s.a(getContext())) {
        h.a("setXtifyRegistrationCB() " + paramString.getMessage(), false);
      }
      paramString.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void showBlockedCallNotification(final String paramString, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        p.a(JavaScriptInterface.this.getContext(), true, paramString, paramInt);
      }
    }).start();
  }
  
  @JavascriptInterface
  public void showLiveCallerId()
  {
    showLiveCallerId("");
  }
  
  @JavascriptInterface
  public void showLiveCallerId(String paramString)
  {
    if (com.privacystar.common.sdk.org.metova.a.h.d.b.e(paramString, "preload"))
    {
      startLiveCallerId(paramString);
      return;
    }
    new Handler(getContext().getMainLooper()).post(new Runnable()
    {
      public final void run()
      {
        if (CallerIdService.c())
        {
          OverlayView localOverlayView = CallerIdService.f();
          if (localOverlayView != null) {
            localOverlayView.k();
          }
        }
      }
    });
  }
  
  @JavascriptInterface
  public void showLiveCallerId(String paramString, boolean paramBoolean)
  {
    setCIDFullscreen(paramBoolean, true, paramString);
  }
  
  @JavascriptInterface
  public void showLiveCallerId(boolean paramBoolean)
  {
    showLiveCallerId("", paramBoolean);
  }
  
  @JavascriptInterface
  public void showNotification(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          JSONObject localJSONObject1 = new JSONObject(paramString);
          p.a(localJSONObject1.getString("tickerText"), localJSONObject1.getString("titleText"), localJSONObject1.getString("messageText"), localJSONObject1.getString("tranId"), localJSONObject1.getLong("dismissTimeout"), JavaScriptInterface.this.getContext());
          localJSONObject1 = new JSONObject();
          localJSONObject1.put("result", true);
          JavaScriptInterface.this.callbackNativeCB("showNotificationCB('" + com.privacystar.core.service.b.b.a(localJSONObject1) + "');");
          return;
        }
        catch (Exception localException1)
        {
          do
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#showNotification", "Error creating notification", JavaScriptInterface.this.getContext());
            if (s.a(JavaScriptInterface.this.getContext())) {
              h.a("showNotification() " + localException1.getMessage(), false);
            }
            try
            {
              JSONObject localJSONObject2 = new JSONObject();
              localJSONObject2.put("result", false);
              JavaScriptInterface.this.callbackNativeCB("showNotificationCB('" + com.privacystar.core.service.b.b.a(localJSONObject2) + "');");
              return;
            }
            catch (Exception localException2)
            {
              com.privacystar.common.c.a.d("JavaScriptInterface#showNotification", "Error creating JSON values for notification CB", JavaScriptInterface.this.getContext());
            }
          } while (!s.a(JavaScriptInterface.this.getContext()));
          h.a("showNotification():e2 " + localException2.getMessage(), false);
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void showRichDialog(String paramString1, String paramString2)
  {
    showRichDialog(paramString1, paramString2, "");
  }
  
  @JavascriptInterface
  public void showRichDialog(final String paramString1, final String paramString2, final String paramString3)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("SHOW_RICH_DIALOG");
        if (com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramString2)) {
          localIntent.putExtra("html", paramString1);
        }
        for (;;)
        {
          l.a(JavaScriptInterface.this.getContext()).a(localIntent);
          return;
          localIntent.putExtra("url", paramString2);
          localIntent.putExtra("script", paramString3);
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void showRichToast(final String paramString, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("com.privacystar.SHOW_RICH_TOAST");
        localIntent.putExtra("body", paramString);
        localIntent.putExtra("gravity", paramInt);
        JavaScriptInterface.this.getContext().sendOrderedBroadcast(localIntent, null);
      }
    }).start();
  }
  
  @JavascriptInterface
  public void showRichToastFromFile(final String paramString, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("com.privacystar.SHOW_RICH_TOAST");
        localIntent.putExtra("file", paramString);
        localIntent.putExtra("gravity", paramInt);
        JavaScriptInterface.this.getContext().sendOrderedBroadcast(localIntent, null);
      }
    }).start();
  }
  
  @JavascriptInterface
  public void showToast(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          Toast.makeText(JavaScriptInterface.this.getContext(), paramString, 0).show();
          JavaScriptInterface.this.showToastCB(true);
          return;
        }
        catch (Exception localException)
        {
          JavaScriptInterface.this.showToastCB(false);
          com.privacystar.common.c.a.d("JavaScriptInterface#showToast", "Error showing toast message", JavaScriptInterface.this.getContext());
          if (s.a(JavaScriptInterface.this.getContext())) {
            h.a("showToast() " + localException.getMessage(), false);
          }
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void showToastCB(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "{ \"result\": " + "true}";; str = "{ \"result\": " + "false}")
    {
      callbackNativeCB("showToastCB('" + str + "');");
      return;
    }
  }
  
  @JavascriptInterface
  public void startJavaScriptService()
  {
    JavaScriptService.a(true);
    callbackToJavaScriptService("");
  }
  
  @JavascriptInterface
  public void startLiveCallerId(String paramString)
  {
    boolean bool = com.privacystar.common.sdk.org.metova.a.h.d.b.e(paramString, "preload");
    if ((CallerIdService.c()) && (bool)) {
      stopLiveCallerId();
    }
    if ((!CallerIdService.c()) || (bool))
    {
      paramString = new Intent(getContext().getApplicationContext(), CallerIdService.class);
      paramString.putExtra("preload", bool);
      getContext().startService(paramString);
    }
  }
  
  @JavascriptInterface
  public void startUninstallFlow()
  {
    startUninstallFlow(null);
  }
  
  @JavascriptInterface
  public void startUninstallFlow(String paramString)
  {
    String str = "com.privacystar.android";
    if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramString)) {
      str = "com.privacystar.android" + "." + paramString;
    }
    paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + str));
    paramString.addFlags(268435456);
    getContext().startActivity(paramString);
  }
  
  @JavascriptInterface
  public void stopLiveCallerId()
  {
    Intent localIntent = new Intent(getContext().getApplicationContext(), CallerIdService.class);
    getContext().stopService(localIntent);
  }
  
  public void timerCB()
  {
    callbackToJavaScriptService("timerFiredCB();");
  }
  
  @JavascriptInterface
  public void trackEvent(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("trackEventIntent");
        localIntent.putExtra("categoryName", paramString1);
        localIntent.putExtra("actionName", paramString2);
        localIntent.putExtra("label", paramString3);
        localIntent.putExtra("value", Long.parseLong(paramString4));
        l.a(JavaScriptInterface.this.getContext()).a(localIntent);
      }
    }).start();
  }
  
  @JavascriptInterface
  public void trackPSAnalytics(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          t.a(JavaScriptInterface.this.getContext()).a(paramString, JavaScriptInterface.this);
          return;
        }
        catch (Exception localException)
        {
          com.privacystar.common.c.a.a("JavaScriptInterface#trackPSAnalytics", "tracking PSAnalytics event", localException, JavaScriptInterface.this.getContext());
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void trackPSAnalyticsCB(JSONObject paramJSONObject)
  {
    callbackNativeCB("trackPSAnalyticsCB('" + com.privacystar.core.service.b.b.a(paramJSONObject) + "');");
  }
  
  @JavascriptInterface
  public void trackPageView(final String paramString)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        Intent localIntent = new Intent("trackPageIntent");
        localIntent.putExtra("pagePath", paramString);
        l.a(JavaScriptInterface.this.getContext()).a(localIntent);
      }
    }).start();
  }
  
  @JavascriptInterface
  public void transferToDialog(String paramString)
  {
    if (this.dialogWebView != null)
    {
      callbackToDialogNativeCB("transferToDialogCB('" + paramString + "');");
      return;
    }
    com.privacystar.common.c.a.d("JavaScriptInterface#transferToDialog", "dialogWebView is null so nothing was passed.", getContext());
  }
  
  @JavascriptInterface
  public void transferToParent(String paramString)
  {
    paramString = paramString.replace("'", "\\'").replace('\n', ' ');
    if (this.parentWebView != null)
    {
      callbackNativeCB("transferToParentCB('" + paramString + "');");
      return;
    }
    com.privacystar.common.c.a.d("JavaScriptInterface#transferToParent", "parentWebView is null so nothing was passed.", getContext());
  }
  
  @JavascriptInterface
  public void writeLog(final String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        int i = paramString2.toLowerCase().charAt(0);
        j = com.privacystar.common.c.a.a();
        localBoolean3 = Boolean.valueOf(true);
        switch (i)
        {
        default: 
        case 118: 
          for (;;)
          {
            try
            {
              localBoolean3 = Boolean.valueOf(false);
              localBoolean1 = localBoolean3;
              if (j <= 2)
              {
                Log.i("JavaScriptInterface#writeLog", "invalid logLevel");
                localBoolean1 = localBoolean3;
              }
            }
            catch (Throwable localThrowable)
            {
              Boolean localBoolean1;
              if (!s.a(JavaScriptInterface.this.getContext())) {
                continue;
              }
              h.a("writeLog() " + localThrowable.getMessage(), false);
              Boolean localBoolean2 = Boolean.valueOf(false);
              continue;
              localBoolean2 = localBoolean3;
              if (j > 1) {
                continue;
              }
              Log.d("JavaScriptInterface#writeLog", paramString1);
              localBoolean2 = localBoolean3;
              continue;
              localBoolean2 = localBoolean3;
              if (j > 2) {
                continue;
              }
              Log.i("JavaScriptInterface#writeLog", paramString1);
              localBoolean2 = localBoolean3;
              continue;
              localBoolean2 = localBoolean3;
              if (j > 3) {
                continue;
              }
              Log.w("JavaScriptInterface#writeLog", paramString1);
              localBoolean2 = localBoolean3;
              continue;
              localBoolean2 = localBoolean3;
              if (j > 0) {
                continue;
              }
              Log.e("JavaScriptInterface#writeLog", paramString1);
              localBoolean2 = localBoolean3;
              continue;
            }
            JavaScriptInterface.this.writeLogCB(localBoolean1.booleanValue());
            return;
            localBoolean1 = localBoolean3;
            if (j <= 0)
            {
              Log.v("JavaScriptInterface#writeLog", paramString1);
              localBoolean1 = localBoolean3;
            }
          }
        }
      }
    }).start();
  }
  
  @JavascriptInterface
  public void writeLogCB(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = "{ \"result\": " + "true}";; str = "{ \"result\": " + "false}")
    {
      callbackNativeCB("writeLogCB('" + str + "');");
      return;
    }
  }
  
  @JavascriptInterface
  public void xtifyPullMessage(String paramString)
  {
    boolean bool = true;
    try
    {
      if (!com.privacystar.common.sdk.org.metova.a.h.d.b.b(paramString))
      {
        Object localObject = new JSONArray(paramString);
        if (((JSONArray)localObject).length() > 0)
        {
          localObject = ((JSONArray)localObject).getJSONObject(0).getString("batchId");
          com.privacystar.core.service.d.a.s(paramString, getContext());
          v.a((String)localObject, true, getContext());
          if (!bool)
          {
            com.privacystar.common.c.a.d("JavaScriptInterface#xtifyPullMessage", "Some error occurred, calling queue complete with 0", getContext());
            callQueueComplete(bool);
          }
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        com.privacystar.common.c.a.a("JavaScriptInterface#xtifyPullMessage", "parsing JSON parameters", paramString, this.context);
        paramString.printStackTrace();
        bool = false;
      }
    }
  }
}
