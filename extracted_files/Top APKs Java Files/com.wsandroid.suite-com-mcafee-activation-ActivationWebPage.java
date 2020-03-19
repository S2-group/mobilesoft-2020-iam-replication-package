package com.mcafee.activation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.http.SslError;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mcafee.af.a.a.d;
import com.mcafee.af.a.a.f;
import com.mcafee.af.a.a.g;
import com.mcafee.app.BaseActivity;
import com.mcafee.app.o.a;
import com.mcafee.oobe.BackgroundRegistrationError.ResultCode;
import com.mcafee.oobe.OOBEService;
import com.mcafee.utils.bk;
import com.mcafee.wsstorage.ConfigManager;
import com.mcafee.wsstorage.ConfigManager.Configuration;
import com.wavesecure.activities.m;
import com.wavesecure.core.g.a;
import com.wavesecure.utils.CommonPhoneUtils;
import com.wavesecure.utils.CommonPhoneUtils.SimState;
import com.wavesecure.utils.Constants.DialogID;
import com.wavesecure.utils.WSAndroidIntents;
import com.wavesecure.utils.aa;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.util.EncodingUtils;
import org.json.JSONObject;

public class ActivationWebPage
  extends BaseActivity
  implements com.mcafee.actionbar.c, com.mcafee.activityplugins.d, com.wavesecure.activities.o, g.a, com.wavesecure.core.g.b
{
  public static final byte[] d = { 121, 101, 51, 67, 67, 53, 121, 110, 118, 103, 65 };
  public static final byte[] e = { 103, 102, 49, 117, 122, 54, 79, 77, 49, 82, 88, 48, 80, 71, 109, 105 };
  public static final byte[] f = { 90, 110, 98, 49, 120, 118 };
  public static final byte[] g = { 83, 117, 67, 79, 52, 53, 115, 72, 107, 87, 78, 116, 117, 111, 78, 120, 57, 90, 106, 112, 106, 83, 121, 72, 87, 121, 101, 105, 112, 85, 118, 119 };
  private int A;
  private boolean B = false;
  private boolean C = false;
  private boolean D = false;
  private ActionMode E;
  h a;
  a b;
  b c;
  private WebView h = null;
  private View i = null;
  private m j = null;
  private Context k = null;
  private final Object l = new Object();
  private com.wavesecure.core.g m = null;
  private String n = null;
  private com.wavesecure.activities.o o = null;
  private boolean p = false;
  private boolean q = false;
  private com.mcafee.registration.storage.a r;
  private final String s = "TRIGGER";
  private final String t = "RESELLER_ID";
  private int u;
  private boolean v = false;
  private long w = 0L;
  private long x = 0L;
  private boolean y = false;
  private boolean z;
  
  public ActivationWebPage()
  {
    super(2147483621);
  }
  
  private void A()
  {
    if (!com.mcafee.w.b.a(getApplicationContext(), "force_registration")) {
      return;
    }
    this.r.f(false);
    new com.mcafee.activitystack.c(this).a(com.mcafee.activitystack.a.a);
    if (OOBEService.a()) {
      OOBEService.a(BackgroundRegistrationError.ResultCode.i);
    }
    Process.killProcess(Process.myPid());
  }
  
  private void B()
  {
    if (getIntent().getBooleanExtra("IS_OOBE_ACTIVATION", false))
    {
      CommonPhoneUtils.e(getApplicationContext(), false);
      Intent localIntent = new Intent("com.mcafee.action.LGE_OOBE_EXIT");
      localIntent.setFlags(268435456);
      sendBroadcast(localIntent);
      com.mcafee.android.e.o.b("ActivationWebPage", "Intent with action com.mcafee.action.LGE_OOBE_EXIT has been broadcast.");
    }
    this.b.d.finish();
    finish();
    A();
  }
  
  private boolean C()
  {
    return getIntent().getBooleanExtra("IS_OOBE_ACTIVATION", false);
  }
  
  private Dialog a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = new com.mcafee.app.g.b(this).a(com.mcafee.wsstorage.h.b(this).ba()).b(paramString).a(a.g.ws_btn_continue_free, 1, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ActivationWebPage.a(ActivationWebPage.this, null);
        }
      }).b(a.g.use_wifi_text, 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = (WifiManager)ActivationWebPage.i(ActivationWebPage.this).getSystemService("wifi");
          if (paramAnonymousDialogInterface != null) {
            paramAnonymousDialogInterface.setWifiEnabled(false);
          }
          ActivationWebPage.a(ActivationWebPage.this, null);
        }
      }).a();
      paramString.setOnKeyListener(new DialogInterface.OnKeyListener()
      {
        public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          switch (paramAnonymousInt)
          {
          }
          for (;;)
          {
            return false;
            ActivationWebPage.j(ActivationWebPage.this);
          }
        }
      });
      return paramString;
    }
    return null;
  }
  
  private void a(long paramLong, int paramInt)
  {
    synchronized (this.l)
    {
      if (this.m == null)
      {
        this.m = new com.wavesecure.core.g(1000L * paramLong, paramInt, this, this);
        this.m.i();
      }
      return;
    }
  }
  
  private void a(Context paramContext, String paramString1, String paramString2)
  {
    if (this.j == null) {
      this.j = m.a(paramContext, paramString1, paramString2, false, false, null, this.k.getString(a.g.ws_cancel), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ActivationWebPage.j(ActivationWebPage.this);
        }
      });
    }
  }
  
  @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
  private void a(Bundle paramBundle)
  {
    a(this, this.r.ba(), this.k.getResources().getString(a.g.ws_purchase_wait_title));
    ConfigManager localConfigManager = ConfigManager.a(this.k);
    if (localConfigManager.c(ConfigManager.Configuration.cy))
    {
      str = com.mcafee.wsstorage.h.b(this.k).bi();
      if (TextUtils.isEmpty(str))
      {
        if (!this.v)
        {
          com.mcafee.android.e.o.b("ActivationWebPage", "Attempting to obtain MSISDN using header enrichment...");
          new c(paramBundle).execute(new Void[0]);
          return;
        }
        com.mcafee.android.e.o.b("ActivationWebPage", "Failed to obtain MSISDN using header enrichment.");
      }
    }
    for (;;)
    {
      this.n = localConfigManager.d(ConfigManager.Configuration.ds);
      if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
        com.mcafee.android.e.o.c("ActivationWebPage", "Activation web url: " + this.n);
      }
      if (this.B) {
        this.n += "/Register/Login?Mode=Existing&FlowType=ActivationCode";
      }
      if (this.C) {
        this.n += "/Register/Login?Mode=Existing&FlowType=Login";
      }
      if (this.D) {
        this.n += "/Register/Login?Mode=New&FlowType=Login";
      }
      this.h.getSettings().setJavaScriptEnabled(true);
      if (CommonPhoneUtils.r(this.k) >= 14) {
        this.h.getSettings().setTextZoom(100);
      }
      this.h.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
      this.h.clearCache(true);
      a();
      this.h.setWebViewClient(new b());
      this.h.addJavascriptInterface(new a(this), "ActivationControlInterface");
      this.h.setVerticalScrollBarEnabled(false);
      this.h.getSettings().setSaveFormData(false);
      this.h.clearFormData();
      if ((paramBundle == null) || (!Locale.getDefault().toString().equals(paramBundle.getString("LAST_LOCALE_USED")))) {
        break;
      }
      this.h.restoreState(paramBundle);
      q();
      return;
      com.mcafee.android.e.o.b("ActivationWebPage", "MSISDN obtained using header enrichment is: " + str);
      continue;
      com.mcafee.android.e.o.b("ActivationWebPage", "Obtaining MSISDN using header enrichment not supported for current branding configuration.");
    }
    paramBundle = null;
    try
    {
      str = bk.a(this.n);
      paramBundle = str;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      for (;;)
      {
        com.mcafee.android.e.o.e("ActivationWebPage", "URL is mailformed");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        com.mcafee.android.e.o.e("ActivationWebPage", "URL is null");
        continue;
        paramBundle = this.n + "?authcontext=" + paramBundle;
      }
    }
    paramBundle = com.mcafee.mobile.web.b.a.a(this.k, paramBundle, e, d);
    String str = o();
    paramBundle = com.mcafee.mobile.web.c.a.a(str, this.k, paramBundle);
    if (com.mcafee.android.e.o.a("ActivationWebPage", 4))
    {
      com.mcafee.android.e.o.c("ActivationWebPage", "PlainText data = " + str);
      com.mcafee.android.e.o.c("ActivationWebPage", "Encrypted data = " + paramBundle);
    }
    if ((this.B) || (this.C) || (this.D))
    {
      paramBundle = this.n + "&authcontext=" + paramBundle;
      a(this.h, str, paramBundle);
      this.w = System.currentTimeMillis();
      a(localConfigManager.b(ConfigManager.Configuration.k) + 10, 0);
      return;
    }
  }
  
  private void a(boolean paramBoolean)
  {
    synchronized (this.l)
    {
      if (this.m != null)
      {
        this.m.j();
        if (!paramBoolean) {
          this.m = null;
        }
      }
      return;
    }
  }
  
  private static final String b(Context paramContext)
  {
    return com.mcafee.android.provider.b.a(paramContext).a("culture", "en-US");
  }
  
  private void b(int paramInt)
  {
    this.h.setVisibility(8);
    this.i.setVisibility(0);
    showDialog(paramInt);
  }
  
  private void b(String paramString, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(5);
    localStringBuffer.append('0');
    localStringBuffer.append(paramInt / 60);
    localStringBuffer.append(':');
    if (paramInt % 60 <= 9) {
      localStringBuffer.append(0);
    }
    localStringBuffer.append(paramInt % 60);
    this.j.a(paramString + "\n" + aa.a(getString(a.g.ws_time_left), new String[] { localStringBuffer.toString() }));
  }
  
  private void f(int paramInt)
  {
    if ((j(paramInt)) && (x()))
    {
      if (w()) {
        v();
      }
    }
    else {
      return;
    }
    com.mcafee.wsstorage.h.b(this.k).ar("10000");
  }
  
  private void i()
  {
    a(false);
  }
  
  private boolean j(int paramInt)
  {
    return (paramInt == 3) || (paramInt == 4);
  }
  
  private void n()
  {
    if (this.E != null) {
      this.E.finish();
    }
  }
  
  /* Error */
  private String o()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   4: invokestatic 422	com/mcafee/wsstorage/ConfigManager:a	(Landroid/content/Context;)Lcom/mcafee/wsstorage/ConfigManager;
    //   7: astore 9
    //   9: ldc_w 709
    //   12: astore 6
    //   14: aload 6
    //   16: astore_2
    //   17: new 711	org/json/JSONObject
    //   20: dup
    //   21: invokespecial 712	org/json/JSONObject:<init>	()V
    //   24: astore 10
    //   26: aload 6
    //   28: astore_2
    //   29: aload 10
    //   31: ldc_w 714
    //   34: ldc_w 716
    //   37: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   40: pop
    //   41: aload 6
    //   43: astore_2
    //   44: aload 10
    //   46: ldc_w 722
    //   49: invokestatic 724	com/wavesecure/utils/CommonPhoneUtils:g	()Ljava/lang/String;
    //   52: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   55: pop
    //   56: aload 6
    //   58: astore_2
    //   59: aload 10
    //   61: ldc_w 726
    //   64: aload_0
    //   65: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   68: invokestatic 728	com/wavesecure/utils/CommonPhoneUtils:o	(Landroid/content/Context;)Ljava/lang/String;
    //   71: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   74: pop
    //   75: new 730	com/mcafee/android/i/j
    //   78: dup
    //   79: aload_0
    //   80: invokespecial 731	com/mcafee/android/i/j:<init>	(Landroid/content/Context;)V
    //   83: ldc_w 733
    //   86: invokevirtual 736	com/mcafee/android/i/j:a	(Ljava/lang/String;)Lcom/mcafee/android/i/g;
    //   89: checkcast 738	com/mcafee/android/i/f
    //   92: astore_2
    //   93: aload_2
    //   94: ldc_w 740
    //   97: ldc_w 709
    //   100: invokeinterface 741 3 0
    //   105: astore_3
    //   106: ldc_w 294
    //   109: iconst_3
    //   110: invokestatic 456	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
    //   113: ifeq +29 -> 142
    //   116: ldc_w 294
    //   119: new 458	java/lang/StringBuilder
    //   122: dup
    //   123: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   126: ldc_w 743
    //   129: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_3
    //   133: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   142: aload_3
    //   143: invokevirtual 746	java/lang/String:trim	()Ljava/lang/String;
    //   146: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   149: ifne +1316 -> 1465
    //   152: aload 10
    //   154: ldc_w 748
    //   157: aload_3
    //   158: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   161: pop
    //   162: aload_2
    //   163: ldc_w 750
    //   166: ldc_w 709
    //   169: invokeinterface 741 3 0
    //   174: astore_3
    //   175: aload_3
    //   176: invokevirtual 746	java/lang/String:trim	()Ljava/lang/String;
    //   179: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   182: ifne +13 -> 195
    //   185: aload 10
    //   187: ldc_w 752
    //   190: aload_3
    //   191: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   194: pop
    //   195: aload_2
    //   196: ldc_w 754
    //   199: ldc_w 709
    //   202: invokeinterface 741 3 0
    //   207: astore_3
    //   208: aload_3
    //   209: invokevirtual 746	java/lang/String:trim	()Ljava/lang/String;
    //   212: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   215: ifne +13 -> 228
    //   218: aload 10
    //   220: ldc_w 756
    //   223: aload_3
    //   224: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   227: pop
    //   228: aload_2
    //   229: ldc_w 758
    //   232: ldc_w 709
    //   235: invokeinterface 741 3 0
    //   240: astore_2
    //   241: aload_2
    //   242: invokevirtual 746	java/lang/String:trim	()Ljava/lang/String;
    //   245: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   248: ifne +13 -> 261
    //   251: aload 10
    //   253: ldc_w 760
    //   256: aload_2
    //   257: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   260: pop
    //   261: aload 6
    //   263: astore_2
    //   264: aload_0
    //   265: getfield 221	com/mcafee/activation/ActivationWebPage:r	Lcom/mcafee/registration/storage/a;
    //   268: invokevirtual 763	com/mcafee/registration/storage/a:aO	()Z
    //   271: ifeq +1238 -> 1509
    //   274: iconst_2
    //   275: istore_1
    //   276: aload 6
    //   278: astore_2
    //   279: aload 10
    //   281: ldc_w 765
    //   284: iload_1
    //   285: invokestatic 769	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   288: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   291: pop
    //   292: aload 6
    //   294: astore_2
    //   295: aload 10
    //   297: ldc_w 771
    //   300: aload 9
    //   302: invokevirtual 773	com/mcafee/wsstorage/ConfigManager:B	()Ljava/lang/String;
    //   305: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   308: pop
    //   309: aload 6
    //   311: astore_2
    //   312: aload 10
    //   314: ldc_w 775
    //   317: ldc_w 777
    //   320: aload_0
    //   321: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   324: invokestatic 779	com/wavesecure/utils/CommonPhoneUtils:y	(Landroid/content/Context;)Ljava/lang/String;
    //   327: invokevirtual 783	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   330: invokevirtual 786	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   333: pop
    //   334: aload 6
    //   336: astore_2
    //   337: aload_0
    //   338: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   341: invokestatic 789	com/wavesecure/utils/CommonPhoneUtils:v	(Landroid/content/Context;)Z
    //   344: ifeq +1170 -> 1514
    //   347: ldc_w 791
    //   350: astore_3
    //   351: aload 6
    //   353: astore_2
    //   354: aload 10
    //   356: ldc_w 793
    //   359: aload_3
    //   360: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   363: pop
    //   364: aload 6
    //   366: astore_2
    //   367: aload 10
    //   369: ldc_w 795
    //   372: invokestatic 799	com/wavesecure/utils/i:a	()Ljava/lang/String;
    //   375: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   378: pop
    //   379: aload 6
    //   381: astore_2
    //   382: aload_0
    //   383: getfield 801	com/mcafee/activation/ActivationWebPage:z	Z
    //   386: ifeq +1135 -> 1521
    //   389: aload 6
    //   391: astore_2
    //   392: aload 9
    //   394: getstatic 804	com/mcafee/wsstorage/ConfigManager$Configuration:fk	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   397: invokevirtual 807	com/mcafee/wsstorage/ConfigManager:a	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Lcom/mcafee/wsstorage/ConfigValue;
    //   400: invokevirtual 810	com/mcafee/wsstorage/ConfigValue:a	()Ljava/lang/String;
    //   403: astore_3
    //   404: aload 6
    //   406: astore_2
    //   407: aload 10
    //   409: ldc_w 812
    //   412: aload 9
    //   414: getstatic 815	com/mcafee/wsstorage/ConfigManager$Configuration:fj	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   417: invokevirtual 453	com/mcafee/wsstorage/ConfigManager:d	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Ljava/lang/String;
    //   420: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   423: pop
    //   424: aload 6
    //   426: astore_2
    //   427: aload 10
    //   429: ldc_w 817
    //   432: iconst_1
    //   433: invokevirtual 786	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   436: pop
    //   437: aload 6
    //   439: astore_2
    //   440: ldc_w 294
    //   443: iconst_3
    //   444: invokestatic 456	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
    //   447: ifeq +32 -> 479
    //   450: aload 6
    //   452: astore_2
    //   453: ldc_w 294
    //   456: new 458	java/lang/StringBuilder
    //   459: dup
    //   460: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   463: ldc_w 819
    //   466: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: aload_3
    //   470: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   476: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   479: aload 6
    //   481: astore_2
    //   482: aload_3
    //   483: ldc_w 821
    //   486: invokevirtual 783	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   489: ifne +1050 -> 1539
    //   492: aload 6
    //   494: astore_2
    //   495: aload_3
    //   496: aload_3
    //   497: ldc_w 823
    //   500: invokevirtual 827	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   503: iconst_1
    //   504: iadd
    //   505: invokevirtual 830	java/lang/String:substring	(I)Ljava/lang/String;
    //   508: astore_3
    //   509: aload 6
    //   511: astore_2
    //   512: aload 10
    //   514: ldc_w 832
    //   517: aload_3
    //   518: iconst_0
    //   519: aload_3
    //   520: ldc_w 823
    //   523: invokevirtual 827	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   526: invokevirtual 835	java/lang/String:substring	(II)Ljava/lang/String;
    //   529: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   532: pop
    //   533: aload 6
    //   535: astore_2
    //   536: aload 9
    //   538: getstatic 428	com/mcafee/wsstorage/ConfigManager$Configuration:cy	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   541: invokevirtual 431	com/mcafee/wsstorage/ConfigManager:c	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Z
    //   544: ifeq +1013 -> 1557
    //   547: aload 6
    //   549: astore_2
    //   550: aload 9
    //   552: invokevirtual 838	com/mcafee/wsstorage/ConfigManager:H	()Ljava/lang/String;
    //   555: astore_3
    //   556: aload 6
    //   558: astore_2
    //   559: ldc_w 294
    //   562: new 458	java/lang/StringBuilder
    //   565: dup
    //   566: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   569: ldc_w 840
    //   572: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: aload_3
    //   576: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: ldc_w 842
    //   582: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   585: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   588: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   591: aload 6
    //   593: astore_2
    //   594: aload 10
    //   596: ldc_w 844
    //   599: aload_3
    //   600: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   603: pop
    //   604: aload 6
    //   606: astore_2
    //   607: aload_3
    //   608: ldc_w 846
    //   611: invokevirtual 850	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   614: astore 4
    //   616: aload 6
    //   618: astore_2
    //   619: aload 4
    //   621: arraylength
    //   622: ifle +984 -> 1606
    //   625: aload 4
    //   627: iconst_0
    //   628: aaload
    //   629: astore_3
    //   630: aload 6
    //   632: astore_2
    //   633: aload 4
    //   635: arraylength
    //   636: iconst_1
    //   637: if_icmple +982 -> 1619
    //   640: aload 4
    //   642: iconst_1
    //   643: aaload
    //   644: astore 4
    //   646: aload 6
    //   648: astore_2
    //   649: aload 10
    //   651: ldc_w 852
    //   654: aload_3
    //   655: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   658: pop
    //   659: aload 6
    //   661: astore_2
    //   662: aload 10
    //   664: ldc_w 854
    //   667: aload_0
    //   668: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   671: invokestatic 857	com/wavesecure/utils/CommonPhoneUtils:X	(Landroid/content/Context;)Ljava/lang/String;
    //   674: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   677: pop
    //   678: aload 6
    //   680: astore_2
    //   681: invokestatic 244	com/mcafee/oobe/OOBEService:a	()Z
    //   684: ifeq +1320 -> 2004
    //   687: aload 6
    //   689: astore_2
    //   690: invokestatic 860	com/mcafee/oobe/OOBEService:b	()Lcom/mcafee/oobe/e;
    //   693: astore_3
    //   694: aload 6
    //   696: astore_2
    //   697: aload 10
    //   699: ldc_w 862
    //   702: aload_0
    //   703: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   706: invokestatic 864	com/wavesecure/utils/CommonPhoneUtils:l	(Landroid/content/Context;)Ljava/lang/String;
    //   709: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   712: pop
    //   713: aload 6
    //   715: astore_2
    //   716: new 866	com/mcafee/dynamicbranding/e
    //   719: dup
    //   720: aload_0
    //   721: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   724: invokespecial 867	com/mcafee/dynamicbranding/e:<init>	(Landroid/content/Context;)V
    //   727: astore 5
    //   729: aload 6
    //   731: astore_2
    //   732: aload_0
    //   733: getfield 801	com/mcafee/activation/ActivationWebPage:z	Z
    //   736: ifeq +897 -> 1633
    //   739: aload 6
    //   741: astore_2
    //   742: aload 10
    //   744: ldc_w 869
    //   747: aload 9
    //   749: getstatic 872	com/mcafee/wsstorage/ConfigManager$Configuration:fm	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   752: invokevirtual 453	com/mcafee/wsstorage/ConfigManager:d	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Ljava/lang/String;
    //   755: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   758: pop
    //   759: aload 6
    //   761: astore_2
    //   762: aload 10
    //   764: ldc_w 874
    //   767: aload_0
    //   768: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   771: invokestatic 876	com/wavesecure/utils/CommonPhoneUtils:f	(Landroid/content/Context;)Ljava/lang/String;
    //   774: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   777: pop
    //   778: aload 6
    //   780: astore_2
    //   781: aload_0
    //   782: getfield 221	com/mcafee/activation/ActivationWebPage:r	Lcom/mcafee/registration/storage/a;
    //   785: invokevirtual 763	com/mcafee/registration/storage/a:aO	()Z
    //   788: ifeq +918 -> 1706
    //   791: aload 6
    //   793: astore_2
    //   794: aload 10
    //   796: ldc_w 878
    //   799: aload 4
    //   801: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   804: pop
    //   805: aload 6
    //   807: astore_2
    //   808: aload 10
    //   810: ldc_w 880
    //   813: aload_0
    //   814: aload_0
    //   815: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   818: invokevirtual 882	com/mcafee/activation/ActivationWebPage:a	(Landroid/content/Context;)Z
    //   821: invokevirtual 786	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   824: pop
    //   825: aload 6
    //   827: astore_2
    //   828: aload 10
    //   830: ldc_w 884
    //   833: ldc_w 709
    //   836: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   839: pop
    //   840: aload 6
    //   842: astore_2
    //   843: aload_0
    //   844: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   847: invokestatic 886	com/wavesecure/utils/CommonPhoneUtils:z	(Landroid/content/Context;)Ljava/lang/String;
    //   850: astore 4
    //   852: aload 6
    //   854: astore_2
    //   855: aload 4
    //   857: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   860: ifne +29 -> 889
    //   863: aload 6
    //   865: astore_2
    //   866: aload 4
    //   868: invokevirtual 889	java/lang/String:length	()I
    //   871: iconst_2
    //   872: if_icmple +17 -> 889
    //   875: aload 6
    //   877: astore_2
    //   878: aload 10
    //   880: ldc_w 891
    //   883: aload 4
    //   885: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   888: pop
    //   889: aload 6
    //   891: astore_2
    //   892: aload_0
    //   893: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   896: invokestatic 893	com/wavesecure/utils/CommonPhoneUtils:A	(Landroid/content/Context;)Ljava/lang/String;
    //   899: astore 4
    //   901: aload 6
    //   903: astore_2
    //   904: aload 4
    //   906: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   909: ifne +29 -> 938
    //   912: aload 6
    //   914: astore_2
    //   915: aload 4
    //   917: invokevirtual 889	java/lang/String:length	()I
    //   920: iconst_2
    //   921: if_icmple +17 -> 938
    //   924: aload 6
    //   926: astore_2
    //   927: aload 10
    //   929: ldc_w 895
    //   932: aload 4
    //   934: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   937: pop
    //   938: aload 6
    //   940: astore_2
    //   941: aload_0
    //   942: getfield 221	com/mcafee/activation/ActivationWebPage:r	Lcom/mcafee/registration/storage/a;
    //   945: invokevirtual 897	com/mcafee/registration/storage/a:s	()Ljava/lang/String;
    //   948: astore 4
    //   950: aload 6
    //   952: astore_2
    //   953: aload_0
    //   954: getfield 221	com/mcafee/activation/ActivationWebPage:r	Lcom/mcafee/registration/storage/a;
    //   957: invokevirtual 899	com/mcafee/registration/storage/a:p	()Ljava/lang/String;
    //   960: astore 5
    //   962: aload_3
    //   963: ifnull +102 -> 1065
    //   966: aload 6
    //   968: astore_2
    //   969: aload_3
    //   970: invokevirtual 902	com/mcafee/oobe/e:a	()Ljava/lang/String;
    //   973: astore 7
    //   975: aload 6
    //   977: astore_2
    //   978: aload_3
    //   979: invokevirtual 904	com/mcafee/oobe/e:c	()Ljava/lang/String;
    //   982: astore 8
    //   984: aload 6
    //   986: astore_2
    //   987: aload_3
    //   988: invokevirtual 906	com/mcafee/oobe/e:d	()Ljava/lang/String;
    //   991: astore 4
    //   993: aload 6
    //   995: astore_2
    //   996: aload 4
    //   998: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1001: ifne +17 -> 1018
    //   1004: aload 6
    //   1006: astore_2
    //   1007: aload 10
    //   1009: ldc_w 908
    //   1012: aload 4
    //   1014: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1017: pop
    //   1018: aload 6
    //   1020: astore_2
    //   1021: aload_3
    //   1022: invokevirtual 910	com/mcafee/oobe/e:b	()Ljava/lang/String;
    //   1025: astore_3
    //   1026: aload 8
    //   1028: astore 5
    //   1030: aload 7
    //   1032: astore 4
    //   1034: aload 6
    //   1036: astore_2
    //   1037: aload_3
    //   1038: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1041: ifne +24 -> 1065
    //   1044: aload 6
    //   1046: astore_2
    //   1047: aload 10
    //   1049: ldc_w 912
    //   1052: aload_3
    //   1053: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1056: pop
    //   1057: aload 7
    //   1059: astore 4
    //   1061: aload 8
    //   1063: astore 5
    //   1065: aload 6
    //   1067: astore_2
    //   1068: ldc_w 294
    //   1071: iconst_3
    //   1072: invokestatic 456	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
    //   1075: ifeq +44 -> 1119
    //   1078: aload 6
    //   1080: astore_2
    //   1081: ldc_w 294
    //   1084: new 458	java/lang/StringBuilder
    //   1087: dup
    //   1088: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   1091: ldc_w 914
    //   1094: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1097: aload 4
    //   1099: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1102: ldc_w 916
    //   1105: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1108: aload 5
    //   1110: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1113: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1116: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1119: aload 6
    //   1121: astore_2
    //   1122: aload_0
    //   1123: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   1126: invokestatic 422	com/mcafee/wsstorage/ConfigManager:a	(Landroid/content/Context;)Lcom/mcafee/wsstorage/ConfigManager;
    //   1129: astore_3
    //   1130: aload 6
    //   1132: astore_2
    //   1133: aload 5
    //   1135: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1138: ifne +586 -> 1724
    //   1141: aload 6
    //   1143: astore_2
    //   1144: aload 10
    //   1146: ldc_w 918
    //   1149: aload 5
    //   1151: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1154: pop
    //   1155: aload 6
    //   1157: astore_2
    //   1158: aload_0
    //   1159: invokevirtual 265	com/mcafee/activation/ActivationWebPage:getIntent	()Landroid/content/Intent;
    //   1162: ldc_w 920
    //   1165: invokevirtual 923	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1168: astore_3
    //   1169: aload 6
    //   1171: astore_2
    //   1172: aload_3
    //   1173: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1176: ifne +702 -> 1878
    //   1179: aload 6
    //   1181: astore_2
    //   1182: aload_0
    //   1183: getfield 801	com/mcafee/activation/ActivationWebPage:z	Z
    //   1186: ifeq +692 -> 1878
    //   1189: aload 6
    //   1191: astore_2
    //   1192: aload 10
    //   1194: ldc_w 925
    //   1197: aload_3
    //   1198: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1201: pop
    //   1202: aload 6
    //   1204: astore_2
    //   1205: invokestatic 928	com/mcafee/utils/PINUtils:a	()Ljava/lang/String;
    //   1208: astore_3
    //   1209: aload 6
    //   1211: astore_2
    //   1212: aload 10
    //   1214: ldc_w 930
    //   1217: aload_3
    //   1218: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1221: pop
    //   1222: aload 6
    //   1224: astore_2
    //   1225: aload_0
    //   1226: getfield 221	com/mcafee/activation/ActivationWebPage:r	Lcom/mcafee/registration/storage/a;
    //   1229: aload_3
    //   1230: invokevirtual 932	com/mcafee/registration/storage/a:Z	(Ljava/lang/String;)V
    //   1233: aload 6
    //   1235: astore_2
    //   1236: aload 10
    //   1238: ldc_w 934
    //   1241: aload_0
    //   1242: invokespecial 935	com/mcafee/activation/ActivationWebPage:p	()Ljava/lang/String;
    //   1245: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1248: pop
    //   1249: aload 6
    //   1251: astore_2
    //   1252: aload 10
    //   1254: ldc_w 937
    //   1257: ldc_w 939
    //   1260: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1263: pop
    //   1264: aload 10
    //   1266: ldc_w 941
    //   1269: new 730	com/mcafee/android/i/j
    //   1272: dup
    //   1273: aload_0
    //   1274: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   1277: invokespecial 731	com/mcafee/android/i/j:<init>	(Landroid/content/Context;)V
    //   1280: ldc_w 733
    //   1283: invokevirtual 736	com/mcafee/android/i/j:a	(Ljava/lang/String;)Lcom/mcafee/android/i/g;
    //   1286: checkcast 738	com/mcafee/android/i/f
    //   1289: ldc_w 943
    //   1292: iconst_0
    //   1293: invokeinterface 945 3 0
    //   1298: invokestatic 949	java/lang/String:valueOf	(Z)Ljava/lang/String;
    //   1301: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1304: pop
    //   1305: aload 10
    //   1307: ldc_w 951
    //   1310: aload_0
    //   1311: getfield 221	com/mcafee/activation/ActivationWebPage:r	Lcom/mcafee/registration/storage/a;
    //   1314: invokevirtual 954	com/mcafee/registration/storage/a:aB	()Z
    //   1317: invokestatic 958	java/lang/Boolean:toString	(Z)Ljava/lang/String;
    //   1320: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1323: pop
    //   1324: aload 10
    //   1326: ldc -68
    //   1328: aload_0
    //   1329: getfield 960	com/mcafee/activation/ActivationWebPage:u	I
    //   1332: invokestatic 769	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   1335: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1338: pop
    //   1339: aload 9
    //   1341: getstatic 963	com/mcafee/wsstorage/ConfigManager$Configuration:ei	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   1344: invokevirtual 453	com/mcafee/wsstorage/ConfigManager:d	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Ljava/lang/String;
    //   1347: astore_3
    //   1348: aload_3
    //   1349: astore_2
    //   1350: aload_3
    //   1351: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1354: ifeq +7 -> 1361
    //   1357: getstatic 966	org/json/JSONObject:NULL	Ljava/lang/Object;
    //   1360: astore_2
    //   1361: aload 10
    //   1363: ldc_w 968
    //   1366: aload_2
    //   1367: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1370: pop
    //   1371: aload 6
    //   1373: astore_2
    //   1374: aload 10
    //   1376: ldc -64
    //   1378: aload 9
    //   1380: getstatic 971	com/mcafee/wsstorage/ConfigManager$Configuration:et	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   1383: invokevirtual 453	com/mcafee/wsstorage/ConfigManager:d	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Ljava/lang/String;
    //   1386: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1389: pop
    //   1390: aload 6
    //   1392: astore_2
    //   1393: aload 10
    //   1395: ldc_w 973
    //   1398: iconst_1
    //   1399: invokevirtual 786	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   1402: pop
    //   1403: aload 6
    //   1405: astore_2
    //   1406: aload 10
    //   1408: invokevirtual 974	org/json/JSONObject:toString	()Ljava/lang/String;
    //   1411: astore_3
    //   1412: aload_3
    //   1413: astore_2
    //   1414: aload_3
    //   1415: ldc_w 976
    //   1418: ldc_w 978
    //   1421: invokevirtual 982	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   1424: astore_3
    //   1425: aload_3
    //   1426: astore_2
    //   1427: ldc_w 294
    //   1430: iconst_4
    //   1431: invokestatic 456	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
    //   1434: ifeq +29 -> 1463
    //   1437: ldc_w 294
    //   1440: new 458	java/lang/StringBuilder
    //   1443: dup
    //   1444: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   1447: ldc_w 984
    //   1450: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1453: aload_2
    //   1454: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1457: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1460: invokestatic 470	com/mcafee/android/e/o:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   1463: aload_2
    //   1464: areturn
    //   1465: aload 10
    //   1467: ldc_w 748
    //   1470: ldc_w 986
    //   1473: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1476: pop
    //   1477: goto -1315 -> 162
    //   1480: astore_2
    //   1481: aload 6
    //   1483: astore_2
    //   1484: ldc_w 294
    //   1487: ldc_w 988
    //   1490: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1493: goto -1232 -> 261
    //   1496: astore_3
    //   1497: ldc_w 294
    //   1500: ldc_w 990
    //   1503: aload_3
    //   1504: invokestatic 993	com/mcafee/android/e/o:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1507: aload_2
    //   1508: areturn
    //   1509: iconst_1
    //   1510: istore_1
    //   1511: goto -1235 -> 276
    //   1514: ldc_w 995
    //   1517: astore_3
    //   1518: goto -1167 -> 351
    //   1521: aload 6
    //   1523: astore_2
    //   1524: aload 9
    //   1526: getstatic 998	com/mcafee/wsstorage/ConfigManager$Configuration:bc	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   1529: invokevirtual 807	com/mcafee/wsstorage/ConfigManager:a	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Lcom/mcafee/wsstorage/ConfigValue;
    //   1532: invokevirtual 810	com/mcafee/wsstorage/ConfigValue:a	()Ljava/lang/String;
    //   1535: astore_3
    //   1536: goto -1099 -> 437
    //   1539: aload 6
    //   1541: astore_2
    //   1542: aload 10
    //   1544: ldc_w 832
    //   1547: ldc_w 709
    //   1550: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1553: pop
    //   1554: goto -1021 -> 533
    //   1557: aload 6
    //   1559: astore_2
    //   1560: aload_0
    //   1561: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   1564: invokestatic 1000	com/mcafee/activation/ActivationWebPage:b	(Landroid/content/Context;)Ljava/lang/String;
    //   1567: astore_3
    //   1568: aload 6
    //   1570: astore_2
    //   1571: ldc_w 294
    //   1574: new 458	java/lang/StringBuilder
    //   1577: dup
    //   1578: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   1581: ldc_w 1002
    //   1584: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1587: aload_3
    //   1588: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1591: ldc_w 842
    //   1594: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1597: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1600: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1603: goto -1012 -> 591
    //   1606: aload 6
    //   1608: astore_2
    //   1609: invokestatic 537	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1612: invokevirtual 1005	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   1615: astore_3
    //   1616: goto -986 -> 630
    //   1619: aload 6
    //   1621: astore_2
    //   1622: invokestatic 537	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1625: invokevirtual 1008	java/util/Locale:getCountry	()Ljava/lang/String;
    //   1628: astore 4
    //   1630: goto -984 -> 646
    //   1633: aload 6
    //   1635: astore_2
    //   1636: aload 5
    //   1638: invokeinterface 1012 1 0
    //   1643: astore 7
    //   1645: aload 6
    //   1647: astore_2
    //   1648: aload 7
    //   1650: astore 5
    //   1652: aload 7
    //   1654: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1657: ifeq +15 -> 1672
    //   1660: aload 6
    //   1662: astore_2
    //   1663: aload_0
    //   1664: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   1667: invokestatic 1016	com/mcafee/wsstorage/g:c	(Landroid/content/Context;)Ljava/lang/String;
    //   1670: astore 5
    //   1672: aload 5
    //   1674: ifnull -915 -> 759
    //   1677: aload 6
    //   1679: astore_2
    //   1680: aload 5
    //   1682: invokevirtual 889	java/lang/String:length	()I
    //   1685: iconst_2
    //   1686: if_icmple -927 -> 759
    //   1689: aload 6
    //   1691: astore_2
    //   1692: aload 10
    //   1694: ldc_w 869
    //   1697: aload 5
    //   1699: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1702: pop
    //   1703: goto -944 -> 759
    //   1706: aload 6
    //   1708: astore_2
    //   1709: aload 10
    //   1711: ldc_w 878
    //   1714: ldc_w 709
    //   1717: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1720: pop
    //   1721: goto -916 -> 805
    //   1724: aload 6
    //   1726: astore_2
    //   1727: aload_3
    //   1728: getstatic 428	com/mcafee/wsstorage/ConfigManager$Configuration:cy	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
    //   1731: invokevirtual 431	com/mcafee/wsstorage/ConfigManager:c	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Z
    //   1734: ifeq +75 -> 1809
    //   1737: aload 6
    //   1739: astore_2
    //   1740: aload_0
    //   1741: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   1744: invokestatic 332	com/mcafee/wsstorage/h:b	(Landroid/content/Context;)Lcom/mcafee/wsstorage/h;
    //   1747: invokevirtual 434	com/mcafee/wsstorage/h:bi	()Ljava/lang/String;
    //   1750: astore_3
    //   1751: aload 6
    //   1753: astore_2
    //   1754: ldc_w 294
    //   1757: iconst_3
    //   1758: invokestatic 456	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
    //   1761: ifeq +32 -> 1793
    //   1764: aload 6
    //   1766: astore_2
    //   1767: ldc_w 294
    //   1770: new 458	java/lang/StringBuilder
    //   1773: dup
    //   1774: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   1777: ldc_w 1018
    //   1780: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1783: aload_3
    //   1784: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1787: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1790: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1793: aload 6
    //   1795: astore_2
    //   1796: aload 10
    //   1798: ldc_w 918
    //   1801: aload_3
    //   1802: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1805: pop
    //   1806: goto -651 -> 1155
    //   1809: aload 6
    //   1811: astore_2
    //   1812: aload_0
    //   1813: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   1816: invokestatic 1020	com/wavesecure/utils/CommonPhoneUtils:B	(Landroid/content/Context;)Ljava/lang/String;
    //   1819: astore_3
    //   1820: aload 6
    //   1822: astore_2
    //   1823: ldc_w 294
    //   1826: iconst_3
    //   1827: invokestatic 456	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
    //   1830: ifeq +32 -> 1862
    //   1833: aload 6
    //   1835: astore_2
    //   1836: ldc_w 294
    //   1839: new 458	java/lang/StringBuilder
    //   1842: dup
    //   1843: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   1846: ldc_w 1022
    //   1849: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1852: aload_3
    //   1853: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1856: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1859: invokestatic 301	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1862: aload 6
    //   1864: astore_2
    //   1865: aload 10
    //   1867: ldc_w 918
    //   1870: aload_3
    //   1871: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1874: pop
    //   1875: goto -720 -> 1155
    //   1878: aload 6
    //   1880: astore_2
    //   1881: aload 4
    //   1883: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1886: ifeq +49 -> 1935
    //   1889: aload 6
    //   1891: astore_2
    //   1892: aload_0
    //   1893: getfield 170	com/mcafee/activation/ActivationWebPage:k	Landroid/content/Context;
    //   1896: invokestatic 1026	com/wavesecure/utils/x:a	(Landroid/content/Context;)Ljava/lang/String;
    //   1899: astore 4
    //   1901: aload 6
    //   1903: astore_2
    //   1904: aload 4
    //   1906: astore_3
    //   1907: aload 4
    //   1909: invokestatic 324	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1912: ifeq +7 -> 1919
    //   1915: ldc_w 709
    //   1918: astore_3
    //   1919: aload 6
    //   1921: astore_2
    //   1922: aload 10
    //   1924: ldc_w 925
    //   1927: aload_3
    //   1928: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1931: pop
    //   1932: goto -730 -> 1202
    //   1935: aload 6
    //   1937: astore_2
    //   1938: aload 10
    //   1940: ldc_w 925
    //   1943: aload 4
    //   1945: invokevirtual 720	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1948: pop
    //   1949: goto -747 -> 1202
    //   1952: astore_2
    //   1953: aload 6
    //   1955: astore_2
    //   1956: ldc_w 294
    //   1959: iconst_5
    //   1960: invokestatic 456	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
    //   1963: ifeq -592 -> 1371
    //   1966: aload 6
    //   1968: astore_2
    //   1969: ldc_w 294
    //   1972: new 458	java/lang/StringBuilder
    //   1975: dup
    //   1976: invokespecial 459	java/lang/StringBuilder:<init>	()V
    //   1979: ldc_w 1028
    //   1982: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1985: ldc_w 709
    //   1988: invokevirtual 465	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1991: invokevirtual 468	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1994: invokestatic 1030	com/mcafee/android/e/o:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1997: goto -626 -> 1371
    //   2000: astore_3
    //   2001: goto -504 -> 1497
    //   2004: aconst_null
    //   2005: astore_3
    //   2006: goto -1312 -> 694
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2009	0	this	ActivationWebPage
    //   275	1236	1	i1	int
    //   16	1448	2	localObject1	Object
    //   1480	1	2	localException1	Exception
    //   1483	455	2	str1	String
    //   1952	1	2	localException2	Exception
    //   1955	14	2	str2	String
    //   105	1321	3	localObject2	Object
    //   1496	8	3	localException3	Exception
    //   1517	411	3	localObject3	Object
    //   2000	1	3	localException4	Exception
    //   2005	1	3	localObject4	Object
    //   614	1330	4	localObject5	Object
    //   727	971	5	localObject6	Object
    //   12	1955	6	str3	String
    //   973	680	7	str4	String
    //   982	80	8	str5	String
    //   7	1518	9	localConfigManager	ConfigManager
    //   24	1915	10	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   75	142	1480	java/lang/Exception
    //   142	162	1480	java/lang/Exception
    //   162	195	1480	java/lang/Exception
    //   195	228	1480	java/lang/Exception
    //   228	261	1480	java/lang/Exception
    //   1465	1477	1480	java/lang/Exception
    //   17	26	1496	java/lang/Exception
    //   29	41	1496	java/lang/Exception
    //   44	56	1496	java/lang/Exception
    //   59	75	1496	java/lang/Exception
    //   264	274	1496	java/lang/Exception
    //   279	292	1496	java/lang/Exception
    //   295	309	1496	java/lang/Exception
    //   312	334	1496	java/lang/Exception
    //   337	347	1496	java/lang/Exception
    //   354	364	1496	java/lang/Exception
    //   367	379	1496	java/lang/Exception
    //   382	389	1496	java/lang/Exception
    //   392	404	1496	java/lang/Exception
    //   407	424	1496	java/lang/Exception
    //   427	437	1496	java/lang/Exception
    //   440	450	1496	java/lang/Exception
    //   453	479	1496	java/lang/Exception
    //   482	492	1496	java/lang/Exception
    //   495	509	1496	java/lang/Exception
    //   512	533	1496	java/lang/Exception
    //   536	547	1496	java/lang/Exception
    //   550	556	1496	java/lang/Exception
    //   559	591	1496	java/lang/Exception
    //   594	604	1496	java/lang/Exception
    //   607	616	1496	java/lang/Exception
    //   619	625	1496	java/lang/Exception
    //   633	640	1496	java/lang/Exception
    //   649	659	1496	java/lang/Exception
    //   662	678	1496	java/lang/Exception
    //   681	687	1496	java/lang/Exception
    //   690	694	1496	java/lang/Exception
    //   697	713	1496	java/lang/Exception
    //   716	729	1496	java/lang/Exception
    //   732	739	1496	java/lang/Exception
    //   742	759	1496	java/lang/Exception
    //   762	778	1496	java/lang/Exception
    //   781	791	1496	java/lang/Exception
    //   794	805	1496	java/lang/Exception
    //   808	825	1496	java/lang/Exception
    //   828	840	1496	java/lang/Exception
    //   843	852	1496	java/lang/Exception
    //   855	863	1496	java/lang/Exception
    //   866	875	1496	java/lang/Exception
    //   878	889	1496	java/lang/Exception
    //   892	901	1496	java/lang/Exception
    //   904	912	1496	java/lang/Exception
    //   915	924	1496	java/lang/Exception
    //   927	938	1496	java/lang/Exception
    //   941	950	1496	java/lang/Exception
    //   953	962	1496	java/lang/Exception
    //   969	975	1496	java/lang/Exception
    //   978	984	1496	java/lang/Exception
    //   987	993	1496	java/lang/Exception
    //   996	1004	1496	java/lang/Exception
    //   1007	1018	1496	java/lang/Exception
    //   1021	1026	1496	java/lang/Exception
    //   1037	1044	1496	java/lang/Exception
    //   1047	1057	1496	java/lang/Exception
    //   1068	1078	1496	java/lang/Exception
    //   1081	1119	1496	java/lang/Exception
    //   1122	1130	1496	java/lang/Exception
    //   1133	1141	1496	java/lang/Exception
    //   1144	1155	1496	java/lang/Exception
    //   1158	1169	1496	java/lang/Exception
    //   1172	1179	1496	java/lang/Exception
    //   1182	1189	1496	java/lang/Exception
    //   1192	1202	1496	java/lang/Exception
    //   1205	1209	1496	java/lang/Exception
    //   1212	1222	1496	java/lang/Exception
    //   1225	1233	1496	java/lang/Exception
    //   1236	1249	1496	java/lang/Exception
    //   1252	1264	1496	java/lang/Exception
    //   1374	1390	1496	java/lang/Exception
    //   1393	1403	1496	java/lang/Exception
    //   1406	1412	1496	java/lang/Exception
    //   1414	1425	1496	java/lang/Exception
    //   1484	1493	1496	java/lang/Exception
    //   1524	1536	1496	java/lang/Exception
    //   1542	1554	1496	java/lang/Exception
    //   1560	1568	1496	java/lang/Exception
    //   1571	1603	1496	java/lang/Exception
    //   1609	1616	1496	java/lang/Exception
    //   1622	1630	1496	java/lang/Exception
    //   1636	1645	1496	java/lang/Exception
    //   1652	1660	1496	java/lang/Exception
    //   1663	1672	1496	java/lang/Exception
    //   1680	1689	1496	java/lang/Exception
    //   1692	1703	1496	java/lang/Exception
    //   1709	1721	1496	java/lang/Exception
    //   1727	1737	1496	java/lang/Exception
    //   1740	1751	1496	java/lang/Exception
    //   1754	1764	1496	java/lang/Exception
    //   1767	1793	1496	java/lang/Exception
    //   1796	1806	1496	java/lang/Exception
    //   1812	1820	1496	java/lang/Exception
    //   1823	1833	1496	java/lang/Exception
    //   1836	1862	1496	java/lang/Exception
    //   1865	1875	1496	java/lang/Exception
    //   1881	1889	1496	java/lang/Exception
    //   1892	1901	1496	java/lang/Exception
    //   1907	1915	1496	java/lang/Exception
    //   1922	1932	1496	java/lang/Exception
    //   1938	1949	1496	java/lang/Exception
    //   1956	1966	1496	java/lang/Exception
    //   1969	1997	1496	java/lang/Exception
    //   1264	1348	1952	java/lang/Exception
    //   1350	1361	1952	java/lang/Exception
    //   1361	1371	1952	java/lang/Exception
    //   1427	1463	2000	java/lang/Exception
  }
  
  private final String p()
  {
    String str2 = ((com.mcafee.android.i.d)new com.mcafee.android.i.j(this.k).a("branding.referrer")).a("iid", null);
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = com.mcafee.w.b.c(this.k, "install_id");
    }
    if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
      com.mcafee.android.e.o.b("ActivationWebPage", "IID string = " + str1);
    }
    return str1;
  }
  
  private void q()
  {
    if ((!isFinishing()) && (this.j != null))
    {
      this.j.dismiss();
      this.j = null;
    }
  }
  
  private void s()
  {
    if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
      com.mcafee.android.e.o.b("ActivationWebPage", "hideSoftInputWindow");
    }
    InputMethodManager localInputMethodManager = (InputMethodManager)this.k.getSystemService("input_method");
    if ((localInputMethodManager != null) && (localInputMethodManager.isActive())) {}
    try
    {
      localInputMethodManager.hideSoftInputFromWindow(this.h.getWindowToken(), 0);
      return;
    }
    catch (Exception localException)
    {
      while (!com.mcafee.android.e.o.a("ActivationWebPage", 3)) {}
      com.mcafee.android.e.o.b("ActivationWebPage", "Exception: ", localException);
    }
  }
  
  private void t()
  {
    sendBroadcast(WSAndroidIntents.cx.a(this.k));
    this.r.aF(true);
    if ((com.mcafee.registration.storage.a.a(this).m()) && (this.p))
    {
      this.c.k();
      this.c.o();
    }
    for (;;)
    {
      if ((this.z) && (this.p) && (!this.r.cY()) && (!u())) {
        sendBroadcast(WSAndroidIntents.bZ.a(this.k));
      }
      return;
      this.r.d(false);
      this.c.d(7);
    }
  }
  
  private boolean u()
  {
    com.mcafee.o.c localC = new com.mcafee.o.c(this.k);
    return (localC.f() == 3) || (localC.f() == 4);
  }
  
  private void v()
  {
    ConfigManager localConfigManager = ConfigManager.a(this.k);
    com.mcafee.wsstorage.h.b(this.k).ar(localConfigManager.d(ConfigManager.Configuration.fV));
  }
  
  private boolean w()
  {
    String str = ConfigManager.a(this.k).d(ConfigManager.Configuration.fV);
    return (!TextUtils.isEmpty(str)) && (!str.equalsIgnoreCase("null"));
  }
  
  private boolean x()
  {
    return TextUtils.isEmpty(com.mcafee.wsstorage.h.b(this.k).dI());
  }
  
  private String y()
  {
    ConfigManager localConfigManager = ConfigManager.a(this.k);
    String str1 = "";
    Object localObject1 = str1;
    for (;;)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localObject1 = str1;
        if (com.mcafee.android.e.o.a("ActivationWebPage", 3))
        {
          localObject1 = str1;
          com.mcafee.android.e.o.b("ActivationWebPage", "Configuration.MLS_PROMO_CAMPAIGN_ID " + localConfigManager.d(ConfigManager.Configuration.fj));
        }
        localObject1 = str1;
        if (localConfigManager.c(ConfigManager.Configuration.cy))
        {
          localObject1 = str1;
          localObject2 = localConfigManager.H();
          localObject1 = str1;
          com.mcafee.android.e.o.b("ActivationWebPage", "FORCE_LANG_AND_BRANDING is true so using string table locale '" + (String)localObject2 + "'.");
          localObject1 = str1;
          localJSONObject.put("LOCALE", localObject2);
          localObject1 = str1;
          localObject2 = ((String)localObject2).split("-");
          localObject1 = str1;
          if (localObject2.length <= 0) {
            break label395;
          }
          localObject2 = localObject2[0];
          localObject1 = str1;
          localJSONObject.put("IS_REGISTERED_USER", com.mcafee.w.c.a(this, "user_registered"));
          localObject1 = str1;
          localJSONObject.put("DEFAULT_LOCALE", localObject2);
          localObject1 = str1;
          localJSONObject.put("BRANDING_ID", localConfigManager.d(ConfigManager.Configuration.fm));
          localObject1 = str1;
          localJSONObject.put("AUTO_ACTIVATION", true);
          localObject1 = str1;
          localJSONObject.put("IS_REACTIVATION", this.r.n());
          localObject1 = str1;
          localObject2 = localJSONObject.toString();
          localObject1 = localObject2;
          localObject2 = ((String)localObject2).replace("'", "\\'");
          localObject1 = localObject2;
          localObject2 = localObject1;
        }
      }
      catch (Exception localException1) {}
      try
      {
        if (com.mcafee.android.e.o.a("ActivationWebPage", 4))
        {
          com.mcafee.android.e.o.c("ActivationWebPage", "Input JSON Data: " + (String)localObject1);
          localObject2 = localObject1;
        }
        return localObject2;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localObject1 = str1;
      String str2 = b(this.k);
      Object localObject2 = str2;
      localObject1 = str1;
      if (com.mcafee.android.e.o.a("ActivationWebPage", 3))
      {
        localObject1 = str1;
        com.mcafee.android.e.o.b("ActivationWebPage", "FORCE_LANG_AND_BRANDING is false so using current locale '" + str2 + "'.");
        localObject2 = str2;
        continue;
        localObject2 = localObject1;
        if (com.mcafee.android.e.o.a("ActivationWebPage", 5))
        {
          com.mcafee.android.e.o.d("ActivationWebPage", "Exception is creating JSON Data: ", localException1);
          return localObject1;
          label395:
          localObject1 = localException1;
          localObject2 = Locale.getDefault().getLanguage();
        }
      }
    }
  }
  
  private void z()
  {
    this.a = h.a(this.k);
    this.b = a.a(this.k, this);
    this.c = b.a(this.k);
    this.r.f(false);
    this.o = this;
    if (this.m != null) {
      this.m.a(this);
    }
    this.c.a(this);
  }
  
  void a()
  {
    com.mcafee.android.e.o.b("ActivationWebPage", "CleanCookies");
    try
    {
      CookieSyncManager.createInstance(this);
      CookieManager localCookieManager = CookieManager.getInstance();
      if (localCookieManager != null) {
        localCookieManager.removeAllCookie();
      }
      return;
    }
    catch (Exception localException)
    {
      com.mcafee.android.e.o.e("ActivationWebPage", "CleanCookies ", localException);
    }
  }
  
  public void a(int paramInt)
  {
    h();
    a(true);
    if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
      com.mcafee.android.e.o.c("ActivationWebPage", "Posting web-page after phone # verification: " + paramInt);
    }
    try
    {
      this.h.loadUrl("javascript:PhoneVerificationCallback(\"" + String.valueOf(paramInt) + "\")");
      this.r.f(false);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (com.mcafee.android.e.o.a("ActivationWebPage", 5)) {
          com.mcafee.android.e.o.d("ActivationWebPage", "Some Javascript error in proceeding after phone # verification: ", localException);
        }
      }
    }
  }
  
  public void a(int paramInt1, int paramInt2) {}
  
  void a(WebView paramWebView, String paramString1, String paramString2)
  {
    CookieSyncManager.createInstance(this);
    CookieManager.getInstance().removeAllCookie();
    com.mcafee.android.e.o.c("ActivationWebPage", "Reloading web-page.");
    paramWebView.postUrl(paramString2, EncodingUtils.getBytes(paramString1, "base64"));
    this.w = System.currentTimeMillis();
  }
  
  public void a(String paramString, int paramInt)
  {
    com.mcafee.app.o.a(this.k, paramString, paramInt).a();
  }
  
  public boolean a(Context paramContext)
  {
    label78:
    boolean bool;
    do
    {
      ApplicationInfo localApplicationInfo;
      try
      {
        Object localObject = paramContext.getPackageManager();
        paramContext = paramContext.getPackageName();
        localObject = ((PackageManager)localObject).getInstalledApplications(0).iterator();
        while (((Iterator)localObject).hasNext())
        {
          localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          if (!localApplicationInfo.processName.equalsIgnoreCase(paramContext)) {
            break label78;
          }
          com.mcafee.android.e.o.b("ActivationWebPage", "Same pkg ignore");
        }
        return false;
      }
      catch (Exception paramContext)
      {
        com.mcafee.android.e.o.b("ActivationWebPage", "Exception: ", paramContext);
      }
      bool = localApplicationInfo.processName.startsWith("com.wsandroid.suite");
    } while (!bool);
    return true;
  }
  
  public void d(int paramInt)
  {
    com.mcafee.android.e.o.c("ActivationWebPage", "Timeout close webview");
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        ActivationWebPage.a(ActivationWebPage.this);
        if (!ActivationWebPage.this.isFinishing())
        {
          if (!CommonPhoneUtils.N(ActivationWebPage.i(ActivationWebPage.this))) {
            ActivationWebPage.this.showDialog(7);
          }
        }
        else {
          return;
        }
        ActivationWebPage.this.showDialog(4);
      }
    });
  }
  
  public void d_(int paramInt)
  {
    if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
      com.mcafee.android.e.o.b("ActivationWebPage", "timeoutThreadExit id " + paramInt);
    }
    com.mcafee.registration.storage.a localA = com.mcafee.registration.storage.a.a(this.k);
    synchronized (this.l)
    {
      this.m = null;
      switch (paramInt)
      {
      }
    }
    d(0);
    return;
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        ActivationWebPage.this.a(0);
      }
    });
    com.mcafee.android.e.o.b("ActivationWebPage", "Finished posting back to server.");
    localObject2.o("");
    if (localObject2.aF())
    {
      localObject2.aa("");
      return;
    }
  }
  
  public void e(final int paramInt)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((ActivationWebPage.s(ActivationWebPage.this) != null) && (paramInt < 40) && (ActivationWebPage.t(ActivationWebPage.this))) {
          ActivationWebPage.a(ActivationWebPage.this, aa.a(ActivationWebPage.this.getString(a.g.ws_activation_prog_verification_body), new String[] { "‎" + ActivationWebPage.k(ActivationWebPage.this).I() + "‎" }), paramInt);
        }
      }
    });
  }
  
  public void g()
  {
    if (this.j == null)
    {
      if (this.r.l()) {
        this.j = m.a(this, getText(a.g.ws_activation_prog_verification_title), aa.a(getString(a.g.ws_activation_prog_verification_body), new String[] { "‎" + this.r.J() + this.r.I() + "‎" }), false, false, null, getText(a.g.ws_cancel), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            ActivationWebPage.this.h();
            ActivationWebPage.this.a(2);
            if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
              com.mcafee.android.e.o.b("ActivationWebPage", "Finished posting back to server with cancel status.");
            }
            ActivationWebPage.this.c.d();
            ActivationWebPage.e(ActivationWebPage.this);
          }
        });
      }
    }
    else {
      return;
    }
    this.j = m.a(this, getText(a.g.ws_activation_prog_checking_account_title), getText(a.g.ws_configuration_msg));
  }
  
  public void h()
  {
    this.q = false;
    com.mcafee.registration.storage.a.a(this).f(false);
    q();
  }
  
  public void onActionModeFinished(ActionMode paramActionMode)
  {
    super.onActionModeFinished(paramActionMode);
    this.E = null;
  }
  
  public void onActionModeStarted(ActionMode paramActionMode)
  {
    super.onActionModeStarted(paramActionMode);
    this.E = paramActionMode;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    if (c() != null) {
      c().d();
    }
    this.k = getApplicationContext();
    this.r = com.mcafee.registration.storage.a.a(this.k);
    this.u = getIntent().getIntExtra("trigger_id", 0);
    this.B = getIntent().getBooleanExtra("launch_activation_code", false);
    this.C = getIntent().getBooleanExtra("login_page_after_purchase", false);
    this.D = getIntent().getBooleanExtra("registration_page_after_purchase", false);
    this.z = getIntent().getBooleanExtra("IS_MLS", false);
    this.A = getIntent().getIntExtra("MLS_PROMO_SUM", 0);
    if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
      com.mcafee.android.e.o.b("ActivationWebPage", "isMLS: " + this.z + "mlsPromoSum: " + this.A);
    }
    setContentView(a.f.activation_web_page);
    this.i = findViewById(a.d.blank_view);
    this.h = ((WebView)findViewById(a.d.activateWebview));
    z();
    if (!CommonPhoneUtils.a(this.b.d))
    {
      showDialog(1);
      return;
    }
    if (!CommonPhoneUtils.N(this.k))
    {
      showDialog(6);
      return;
    }
    this.y = true;
    a(paramBundle);
  }
  
  public Dialog onCreateDialog(int paramInt)
  {
    if (isFinishing())
    {
      localObject1 = null;
      return localObject1;
    }
    super.onCreateDialog(paramInt);
    Object localObject2 = "";
    Object localObject1 = localObject2;
    switch (paramInt)
    {
    default: 
      localObject1 = localObject2;
    }
    for (;;)
    {
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        break label211;
      }
      localObject2 = new com.mcafee.app.g.b(this).a(com.mcafee.wsstorage.h.b(this).ba()).b((CharSequence)localObject1).a(a.g.btn_close, 1, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ActivationWebPage.j(ActivationWebPage.this);
        }
      }).a();
      ((Dialog)localObject2).setOnKeyListener(new DialogInterface.OnKeyListener()
      {
        public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          switch (paramAnonymousInt)
          {
          }
          for (;;)
          {
            return false;
            ActivationWebPage.j(ActivationWebPage.this);
            continue;
            if (ActivationWebPage.u(ActivationWebPage.this))
            {
              com.mcafee.android.e.o.b("ActivationWebPage", "DIALOG_ERROR_NO_INTERNET KEYCODE_MENU exit ");
              ActivationWebPage.j(ActivationWebPage.this);
            }
          }
        }
      });
      localObject1 = localObject2;
      if (paramInt != 1) {
        break;
      }
      ((Dialog)localObject2).setCancelable(false);
      return localObject2;
      return a(getString(a.g.slow_connection_alert));
      localObject1 = getString(a.g.use_wifi_alert);
      continue;
      localObject1 = getString(a.g.ws_error_no_internet);
      continue;
      localObject1 = getString(a.g.ws_activation_error_timeout);
      continue;
      localObject1 = getString(a.g.ws_error_invalid_sim_state);
      continue;
      localObject1 = getString(a.g.ws_error_no_permission);
    }
    label211:
    return null;
  }
  
  public void onDestroy()
  {
    q();
    if (this.c != null)
    {
      this.c.a(null);
      this.c = null;
    }
    if (this.b != null)
    {
      this.b.b();
      this.b = null;
    }
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0) {
      switch (paramInt)
      {
      }
    }
    for (;;)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      if ((this.h != null) && (this.h.canGoBack() == true))
      {
        if ((!CommonPhoneUtils.a(this.b.d)) || (this.h.getOriginalUrl().equalsIgnoreCase(this.h.getUrl())))
        {
          B();
          return true;
        }
        this.h.goBack();
        return true;
      }
      B();
      return true;
      if (C())
      {
        com.mcafee.android.e.o.b("ActivationWebPage", "onKeyDown KEYCODE_MENU exit ");
        B();
      }
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    n();
  }
  
  protected void onResume()
  {
    super.onResume();
    if (!CommonPhoneUtils.a(this.b.d)) {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          ActivationWebPage.this.showDialog(1);
        }
      });
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if ((this.h != null) && (this.y))
    {
      paramBundle.putString("LAST_LOCALE_USED", Locale.getDefault().toString());
      this.h.saveState(paramBundle);
    }
  }
  
  public class a
  {
    public final String a = "ISO_COUNTRY_CODE";
    public final String b = "PROVISIONING_ID";
    public final String c = "ALREADY_HAS_FLEX";
    public final String d = "PIN_RESET_EMAIL";
    public final String e = "RANSOMWARE_FIX";
    private Activity g;
    
    public a(Activity paramActivity)
    {
      this.g = paramActivity;
    }
    
    @JavascriptInterface
    public void RePostData()
    {
      try
      {
        final String str = ActivationWebPage.c(ActivationWebPage.this);
        if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
          com.mcafee.android.e.o.b("ActivationWebPage", "Reposting data: " + str);
        }
        str = "javascript:RepostDataCallback('" + str + "')";
        if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
          com.mcafee.android.e.o.b("ActivationWebPage", "Reposting data call back function: " + str);
        }
        ActivationWebPage.o(ActivationWebPage.this).post(new Runnable()
        {
          public void run()
          {
            ActivationWebPage.o(ActivationWebPage.this).loadUrl(str);
          }
        });
        return;
      }
      catch (Exception localException)
      {
        com.mcafee.android.e.o.b("ActivationWebPage", "Error reposting data: ", localException);
      }
    }
    
    /* Error */
    @JavascriptInterface
    public void StoreActivationData(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   4: invokestatic 125	com/mcafee/activation/ActivationWebPage:e	(Lcom/mcafee/activation/ActivationWebPage;)V
      //   7: ldc 72
      //   9: iconst_4
      //   10: invokestatic 77	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
      //   13: ifeq +27 -> 40
      //   16: ldc 72
      //   18: new 79	java/lang/StringBuilder
      //   21: dup
      //   22: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   25: ldc 127
      //   27: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   30: aload_1
      //   31: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   34: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   37: invokestatic 129	com/mcafee/android/e/o:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   40: iconst_0
      //   41: istore 9
      //   43: iconst_0
      //   44: istore 10
      //   46: iconst_0
      //   47: istore 8
      //   49: iconst_0
      //   50: istore 4
      //   52: iload 9
      //   54: istore 5
      //   56: iload 10
      //   58: istore 6
      //   60: aload_0
      //   61: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   64: invokestatic 133	com/mcafee/activation/ActivationWebPage:i	(Lcom/mcafee/activation/ActivationWebPage;)Landroid/content/Context;
      //   67: invokestatic 138	com/mcafee/wsstorage/ConfigManager:a	(Landroid/content/Context;)Lcom/mcafee/wsstorage/ConfigManager;
      //   70: astore 16
      //   72: iload 9
      //   74: istore 5
      //   76: iload 10
      //   78: istore 6
      //   80: aload 16
      //   82: getstatic 144	com/mcafee/wsstorage/ConfigManager$Configuration:fn	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
      //   85: invokevirtual 147	com/mcafee/wsstorage/ConfigManager:d	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;)Ljava/lang/String;
      //   88: astore 23
      //   90: iload 9
      //   92: istore 5
      //   94: iload 10
      //   96: istore 6
      //   98: new 149	org/json/JSONObject
      //   101: dup
      //   102: aload_1
      //   103: invokespecial 151	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   106: astore 24
      //   108: iload 9
      //   110: istore 5
      //   112: iload 10
      //   114: istore 6
      //   116: ldc 72
      //   118: ldc -103
      //   120: invokestatic 129	com/mcafee/android/e/o:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   123: iload 9
      //   125: istore 5
      //   127: iload 10
      //   129: istore 6
      //   131: aload_0
      //   132: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   135: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   138: invokestatic 163	java/lang/System:currentTimeMillis	()J
      //   141: invokevirtual 169	com/mcafee/registration/storage/a:q	(J)V
      //   144: iload 9
      //   146: istore 5
      //   148: iload 10
      //   150: istore 6
      //   152: aload 24
      //   154: ldc -85
      //   156: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   159: astore_1
      //   160: iload 9
      //   162: istore 5
      //   164: iload 10
      //   166: istore 6
      //   168: aload 24
      //   170: ldc -79
      //   172: invokevirtual 181	org/json/JSONObject:getInt	(Ljava/lang/String;)I
      //   175: istore_3
      //   176: iload 9
      //   178: istore 5
      //   180: iload 10
      //   182: istore 6
      //   184: ldc 72
      //   186: iconst_4
      //   187: invokestatic 77	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
      //   190: ifeq +44 -> 234
      //   193: iload 9
      //   195: istore 5
      //   197: iload 10
      //   199: istore 6
      //   201: ldc 72
      //   203: new 79	java/lang/StringBuilder
      //   206: dup
      //   207: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   210: ldc -73
      //   212: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   215: iload_3
      //   216: invokevirtual 186	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   219: ldc -68
      //   221: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   224: aload_1
      //   225: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   228: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   231: invokestatic 129	com/mcafee/android/e/o:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   234: iload 9
      //   236: istore 5
      //   238: iload 10
      //   240: istore 6
      //   242: invokestatic 163	java/lang/System:currentTimeMillis	()J
      //   245: lstore 14
      //   247: iload 9
      //   249: istore 5
      //   251: iload 10
      //   253: istore 6
      //   255: aload_1
      //   256: invokestatic 194	java/lang/Long:parseLong	(Ljava/lang/String;)J
      //   259: ldc2_w 195
      //   262: lmul
      //   263: lstore 12
      //   265: iload 9
      //   267: istore 5
      //   269: iload 10
      //   271: istore 6
      //   273: aload 16
      //   275: iload_3
      //   276: invokestatic 201	java/lang/Integer:toString	(I)Ljava/lang/String;
      //   279: lload 12
      //   281: lload 14
      //   283: iconst_1
      //   284: invokevirtual 204	com/mcafee/wsstorage/ConfigManager:a	(Ljava/lang/String;JJZ)Z
      //   287: ifeq +2404 -> 2691
      //   290: iload 9
      //   292: istore 5
      //   294: iload 10
      //   296: istore 6
      //   298: aload 24
      //   300: ldc -50
      //   302: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   305: astore_1
      //   306: iload 9
      //   308: istore 5
      //   310: iload 10
      //   312: istore 6
      //   314: aload_1
      //   315: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   318: ifne +2406 -> 2724
      //   321: iload 9
      //   323: istore 5
      //   325: iload 10
      //   327: istore 6
      //   329: aload_1
      //   330: ldc -42
      //   332: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   335: ifne +2389 -> 2724
      //   338: iload 9
      //   340: istore 5
      //   342: iload 10
      //   344: istore 6
      //   346: aload_0
      //   347: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   350: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   353: aload_1
      //   354: invokevirtual 223	com/mcafee/registration/storage/a:a_	(Ljava/lang/String;)V
      //   357: iload 9
      //   359: istore 5
      //   361: iload 10
      //   363: istore 6
      //   365: aload 16
      //   367: iconst_0
      //   368: invokevirtual 226	com/mcafee/wsstorage/ConfigManager:b	(Z)V
      //   371: iload 9
      //   373: istore 5
      //   375: iload 10
      //   377: istore 6
      //   379: ldc 72
      //   381: new 79	java/lang/StringBuilder
      //   384: dup
      //   385: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   388: ldc -28
      //   390: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   393: aload_1
      //   394: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   397: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   400: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   403: iload 9
      //   405: istore 5
      //   407: aload 24
      //   409: ldc -26
      //   411: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   414: astore_1
      //   415: iload 9
      //   417: istore 5
      //   419: aload_1
      //   420: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   423: ifne +2368 -> 2791
      //   426: iload 9
      //   428: istore 5
      //   430: aload_1
      //   431: ldc -24
      //   433: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   436: ifeq +2355 -> 2791
      //   439: iload 9
      //   441: istore 5
      //   443: aload_0
      //   444: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   447: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   450: iconst_1
      //   451: invokevirtual 234	com/mcafee/registration/storage/a:i	(Z)V
      //   454: iload 9
      //   456: istore 5
      //   458: iload 10
      //   460: istore 6
      //   462: aload 24
      //   464: ldc -20
      //   466: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   469: astore_1
      //   470: iload 9
      //   472: istore 5
      //   474: iload 10
      //   476: istore 6
      //   478: aload_1
      //   479: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   482: ifne +37 -> 519
      //   485: iload 9
      //   487: istore 5
      //   489: iload 10
      //   491: istore 6
      //   493: aload_1
      //   494: ldc -42
      //   496: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   499: ifne +20 -> 519
      //   502: iload 9
      //   504: istore 5
      //   506: iload 10
      //   508: istore 6
      //   510: aload 16
      //   512: getstatic 239	com/mcafee/wsstorage/ConfigManager$Configuration:z	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
      //   515: aload_1
      //   516: invokevirtual 242	com/mcafee/wsstorage/ConfigManager:a	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;Ljava/lang/String;)V
      //   519: iload 9
      //   521: istore 5
      //   523: iload 10
      //   525: istore 6
      //   527: aload 24
      //   529: ldc -12
      //   531: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   534: astore_1
      //   535: iload 9
      //   537: istore 5
      //   539: iload 10
      //   541: istore 6
      //   543: aload_1
      //   544: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   547: ifne +37 -> 584
      //   550: iload 9
      //   552: istore 5
      //   554: iload 10
      //   556: istore 6
      //   558: aload_1
      //   559: ldc -42
      //   561: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   564: ifne +20 -> 584
      //   567: iload 9
      //   569: istore 5
      //   571: iload 10
      //   573: istore 6
      //   575: aload 16
      //   577: getstatic 247	com/mcafee/wsstorage/ConfigManager$Configuration:A	Lcom/mcafee/wsstorage/ConfigManager$Configuration;
      //   580: aload_1
      //   581: invokevirtual 242	com/mcafee/wsstorage/ConfigManager:a	(Lcom/mcafee/wsstorage/ConfigManager$Configuration;Ljava/lang/String;)V
      //   584: iload 9
      //   586: istore 5
      //   588: iload 10
      //   590: istore 6
      //   592: aload 24
      //   594: ldc -7
      //   596: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   599: astore_1
      //   600: iload 9
      //   602: istore 5
      //   604: iload 10
      //   606: istore 6
      //   608: aload_1
      //   609: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   612: ifne +38 -> 650
      //   615: iload 9
      //   617: istore 5
      //   619: iload 10
      //   621: istore 6
      //   623: aload_1
      //   624: ldc -42
      //   626: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   629: ifne +21 -> 650
      //   632: iload 9
      //   634: istore 5
      //   636: iload 10
      //   638: istore 6
      //   640: aload 16
      //   642: aload_1
      //   643: invokestatic 253	com/mcafee/utils/af:a	(Ljava/lang/String;)I
      //   646: i2l
      //   647: invokevirtual 255	com/mcafee/wsstorage/ConfigManager:b	(J)V
      //   650: iload 9
      //   652: istore 5
      //   654: iload 10
      //   656: istore 6
      //   658: aload 24
      //   660: ldc_w 257
      //   663: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   666: astore_1
      //   667: iload 9
      //   669: istore 5
      //   671: iload 10
      //   673: istore 6
      //   675: aload_1
      //   676: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   679: ifne +38 -> 717
      //   682: iload 9
      //   684: istore 5
      //   686: iload 10
      //   688: istore 6
      //   690: aload_1
      //   691: ldc -42
      //   693: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   696: ifne +21 -> 717
      //   699: iload 9
      //   701: istore 5
      //   703: iload 10
      //   705: istore 6
      //   707: aload 16
      //   709: aload_1
      //   710: invokestatic 253	com/mcafee/utils/af:a	(Ljava/lang/String;)I
      //   713: i2l
      //   714: invokevirtual 259	com/mcafee/wsstorage/ConfigManager:a	(J)V
      //   717: iload 9
      //   719: istore 5
      //   721: iload 10
      //   723: istore 6
      //   725: aload 24
      //   727: ldc_w 261
      //   730: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   733: astore 16
      //   735: iload 9
      //   737: istore 5
      //   739: iload 10
      //   741: istore 6
      //   743: aload 16
      //   745: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   748: ifne +2649 -> 3397
      //   751: iload 9
      //   753: istore 5
      //   755: iload 10
      //   757: istore 6
      //   759: aload 16
      //   761: ldc -42
      //   763: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   766: ifne +2631 -> 3397
      //   769: iload 9
      //   771: istore 5
      //   773: iload 10
      //   775: istore 6
      //   777: aload 16
      //   779: invokevirtual 265	java/lang/String:length	()I
      //   782: iconst_2
      //   783: if_icmple +2614 -> 3397
      //   786: iload 9
      //   788: istore 5
      //   790: iload 10
      //   792: istore 6
      //   794: aload_0
      //   795: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   798: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   801: aload 16
      //   803: invokevirtual 268	com/mcafee/registration/storage/a:N	(Ljava/lang/String;)V
      //   806: iload 9
      //   808: istore 5
      //   810: iload 10
      //   812: istore 6
      //   814: aload 24
      //   816: ldc_w 270
      //   819: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   822: astore 17
      //   824: iload 9
      //   826: istore 5
      //   828: iload 10
      //   830: istore 6
      //   832: aload 17
      //   834: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   837: ifne +2552 -> 3389
      //   840: iload 9
      //   842: istore 5
      //   844: iload 10
      //   846: istore 6
      //   848: aload 17
      //   850: ldc -42
      //   852: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   855: ifne +2534 -> 3389
      //   858: iload 9
      //   860: istore 5
      //   862: iload 10
      //   864: istore 6
      //   866: aload 17
      //   868: invokevirtual 265	java/lang/String:length	()I
      //   871: iconst_2
      //   872: if_icmple +2517 -> 3389
      //   875: iload 9
      //   877: istore 5
      //   879: iload 10
      //   881: istore 6
      //   883: aload_0
      //   884: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   887: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   890: aload 17
      //   892: invokevirtual 273	com/mcafee/registration/storage/a:O	(Ljava/lang/String;)V
      //   895: iload 9
      //   897: istore 5
      //   899: iload 10
      //   901: istore 6
      //   903: aload 24
      //   905: ldc_w 275
      //   908: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   911: astore_1
      //   912: iload 9
      //   914: istore 5
      //   916: iload 10
      //   918: istore 6
      //   920: aload_1
      //   921: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   924: ifne +99 -> 1023
      //   927: iload 9
      //   929: istore 5
      //   931: iload 10
      //   933: istore 6
      //   935: aload_1
      //   936: ldc -42
      //   938: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   941: ifne +82 -> 1023
      //   944: iload 9
      //   946: istore 5
      //   948: iload 10
      //   950: istore 6
      //   952: aload_1
      //   953: invokevirtual 265	java/lang/String:length	()I
      //   956: iconst_2
      //   957: if_icmple +66 -> 1023
      //   960: iload 9
      //   962: istore 5
      //   964: iload 10
      //   966: istore 6
      //   968: new 277	com/mcafee/dynamicbranding/e
      //   971: dup
      //   972: aload_0
      //   973: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   976: invokestatic 133	com/mcafee/activation/ActivationWebPage:i	(Lcom/mcafee/activation/ActivationWebPage;)Landroid/content/Context;
      //   979: invokespecial 280	com/mcafee/dynamicbranding/e:<init>	(Landroid/content/Context;)V
      //   982: astore 18
      //   984: iload 9
      //   986: istore 5
      //   988: iload 10
      //   990: istore 6
      //   992: aload_1
      //   993: aload 18
      //   995: invokeinterface 284 1 0
      //   1000: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
      //   1003: ifeq +1844 -> 2847
      //   1006: iload 9
      //   1008: istore 5
      //   1010: iload 10
      //   1012: istore 6
      //   1014: aload_0
      //   1015: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1018: iconst_1
      //   1019: invokestatic 291	com/mcafee/activation/ActivationWebPage:c	(Lcom/mcafee/activation/ActivationWebPage;Z)Z
      //   1022: pop
      //   1023: iconst_0
      //   1024: istore 11
      //   1026: iconst_0
      //   1027: istore 7
      //   1029: iload 9
      //   1031: istore 5
      //   1033: iload 11
      //   1035: istore 4
      //   1037: aload 24
      //   1039: ldc_w 293
      //   1042: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1045: astore_1
      //   1046: iload 7
      //   1048: istore 6
      //   1050: iload 9
      //   1052: istore 5
      //   1054: iload 11
      //   1056: istore 4
      //   1058: aload_1
      //   1059: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1062: ifne +45 -> 1107
      //   1065: iload 7
      //   1067: istore 6
      //   1069: iload 9
      //   1071: istore 5
      //   1073: iload 11
      //   1075: istore 4
      //   1077: aload_1
      //   1078: ldc -24
      //   1080: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1083: ifeq +24 -> 1107
      //   1086: iconst_1
      //   1087: istore 4
      //   1089: iconst_1
      //   1090: istore 6
      //   1092: iload 9
      //   1094: istore 5
      //   1096: aload_0
      //   1097: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1100: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1103: iconst_1
      //   1104: invokevirtual 296	com/mcafee/registration/storage/a:ar	(Z)V
      //   1107: iload 6
      //   1109: istore 7
      //   1111: iload 9
      //   1113: istore 5
      //   1115: iload 10
      //   1117: istore 6
      //   1119: aload 24
      //   1121: ldc_w 298
      //   1124: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1127: astore 18
      //   1129: iload 9
      //   1131: istore 5
      //   1133: iload 10
      //   1135: istore 6
      //   1137: aload 18
      //   1139: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1142: ifne +21 -> 1163
      //   1145: iload 9
      //   1147: istore 5
      //   1149: iload 10
      //   1151: istore 6
      //   1153: aload 18
      //   1155: ldc -42
      //   1157: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1160: ifeq +1731 -> 2891
      //   1163: iload 9
      //   1165: istore 5
      //   1167: iload 10
      //   1169: istore 6
      //   1171: aload_0
      //   1172: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1175: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1178: iconst_1
      //   1179: invokevirtual 300	com/mcafee/registration/storage/a:e	(Z)V
      //   1182: iload 9
      //   1184: istore 5
      //   1186: iload 10
      //   1188: istore 6
      //   1190: aload 24
      //   1192: ldc_w 302
      //   1195: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1198: astore 18
      //   1200: iload 9
      //   1202: istore 5
      //   1204: iload 10
      //   1206: istore 6
      //   1208: aload 18
      //   1210: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1213: ifne +21 -> 1234
      //   1216: iload 9
      //   1218: istore 5
      //   1220: iload 10
      //   1222: istore 6
      //   1224: aload 18
      //   1226: ldc -42
      //   1228: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1231: ifeq +1753 -> 2984
      //   1234: iload 9
      //   1236: istore 5
      //   1238: iload 10
      //   1240: istore 6
      //   1242: aload_0
      //   1243: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1246: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1249: ldc_w 304
      //   1252: invokevirtual 307	com/mcafee/registration/storage/a:aa	(Ljava/lang/String;)V
      //   1255: iload 9
      //   1257: istore 5
      //   1259: iload 10
      //   1261: istore 6
      //   1263: aload_0
      //   1264: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1267: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1270: iconst_1
      //   1271: invokevirtual 310	com/mcafee/registration/storage/a:J	(Z)V
      //   1274: ldc_w 304
      //   1277: astore 18
      //   1279: iload 9
      //   1281: istore 5
      //   1283: iload 10
      //   1285: istore 6
      //   1287: aload 24
      //   1289: ldc_w 312
      //   1292: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1295: astore_1
      //   1296: iload 9
      //   1298: istore 5
      //   1300: iload 10
      //   1302: istore 6
      //   1304: aload_1
      //   1305: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1308: ifne +38 -> 1346
      //   1311: iload 9
      //   1313: istore 5
      //   1315: iload 10
      //   1317: istore 6
      //   1319: aload_1
      //   1320: ldc -42
      //   1322: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1325: ifne +21 -> 1346
      //   1328: iload 9
      //   1330: istore 5
      //   1332: iload 10
      //   1334: istore 6
      //   1336: aload_1
      //   1337: ldc_w 314
      //   1340: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1343: ifeq +1683 -> 3026
      //   1346: iload 9
      //   1348: istore 5
      //   1350: iload 10
      //   1352: istore 6
      //   1354: aload_0
      //   1355: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1358: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1361: iconst_0
      //   1362: invokevirtual 317	com/mcafee/registration/storage/a:h	(Z)V
      //   1365: ldc_w 319
      //   1368: astore 19
      //   1370: iload 9
      //   1372: istore 5
      //   1374: aload 24
      //   1376: ldc_w 321
      //   1379: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1382: astore_1
      //   1383: iload 9
      //   1385: istore 5
      //   1387: aload_1
      //   1388: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1391: ifne +1991 -> 3382
      //   1394: iload 9
      //   1396: istore 5
      //   1398: aload_1
      //   1399: ldc -42
      //   1401: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1404: ifne +1978 -> 3382
      //   1407: iload 9
      //   1409: istore 5
      //   1411: aload_0
      //   1412: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1415: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1418: aload_1
      //   1419: invokevirtual 324	com/mcafee/registration/storage/a:U	(Ljava/lang/String;)V
      //   1422: iload 9
      //   1424: istore 5
      //   1426: aload 24
      //   1428: ldc_w 326
      //   1431: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1434: astore 20
      //   1436: iload 9
      //   1438: istore 5
      //   1440: aload 20
      //   1442: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1445: ifne +33 -> 1478
      //   1448: iload 9
      //   1450: istore 5
      //   1452: aload 20
      //   1454: ldc -42
      //   1456: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1459: ifne +19 -> 1478
      //   1462: iload 9
      //   1464: istore 5
      //   1466: aload_0
      //   1467: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1470: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1473: aload 20
      //   1475: invokevirtual 329	com/mcafee/registration/storage/a:V	(Ljava/lang/String;)V
      //   1478: iload 9
      //   1480: istore 5
      //   1482: aload 24
      //   1484: ldc 41
      //   1486: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1489: astore 20
      //   1491: iload 9
      //   1493: istore 5
      //   1495: aload 20
      //   1497: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1500: ifne +33 -> 1533
      //   1503: iload 9
      //   1505: istore 5
      //   1507: aload 20
      //   1509: ldc -42
      //   1511: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1514: ifne +19 -> 1533
      //   1517: iload 9
      //   1519: istore 5
      //   1521: aload_0
      //   1522: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1525: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1528: aload 20
      //   1530: invokevirtual 331	com/mcafee/registration/storage/a:q	(Ljava/lang/String;)V
      //   1533: iload 9
      //   1535: istore 5
      //   1537: aload 24
      //   1539: ldc 45
      //   1541: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1544: astore 21
      //   1546: iload 9
      //   1548: istore 5
      //   1550: aload 21
      //   1552: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1555: ifne +33 -> 1588
      //   1558: iload 9
      //   1560: istore 5
      //   1562: aload 21
      //   1564: ldc -42
      //   1566: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1569: ifne +19 -> 1588
      //   1572: iload 9
      //   1574: istore 5
      //   1576: aload_0
      //   1577: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1580: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1583: aload 21
      //   1585: invokevirtual 334	com/mcafee/registration/storage/a:H	(Ljava/lang/String;)V
      //   1588: iload 9
      //   1590: istore 5
      //   1592: aload 24
      //   1594: ldc 49
      //   1596: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1599: astore 21
      //   1601: iload 9
      //   1603: istore 5
      //   1605: aload 21
      //   1607: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1610: ifne +1766 -> 3376
      //   1613: iload 9
      //   1615: istore 5
      //   1617: aload 21
      //   1619: ldc -24
      //   1621: invokevirtual 220	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1624: istore 4
      //   1626: iload 4
      //   1628: ifeq +1748 -> 3376
      //   1631: iconst_1
      //   1632: istore 4
      //   1634: aload_0
      //   1635: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1638: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1641: iconst_1
      //   1642: invokevirtual 296	com/mcafee/registration/storage/a:ar	(Z)V
      //   1645: iload 4
      //   1647: istore 5
      //   1649: aload 24
      //   1651: ldc 53
      //   1653: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1656: astore 21
      //   1658: iload 4
      //   1660: istore 5
      //   1662: aload 24
      //   1664: ldc 57
      //   1666: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1669: astore 22
      //   1671: iload 4
      //   1673: istore 5
      //   1675: aload 22
      //   1677: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1680: ifne +1493 -> 3173
      //   1683: iload 4
      //   1685: istore 5
      //   1687: aload 22
      //   1689: ldc -24
      //   1691: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1694: ifeq +1479 -> 3173
      //   1697: iload 4
      //   1699: istore 5
      //   1701: aload_0
      //   1702: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1705: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1708: iconst_1
      //   1709: invokevirtual 340	com/mcafee/registration/storage/a:al	(Z)V
      //   1712: iload 4
      //   1714: istore 5
      //   1716: aload 21
      //   1718: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1721: ifne +1493 -> 3214
      //   1724: iload 4
      //   1726: istore 5
      //   1728: aload 21
      //   1730: ldc -42
      //   1732: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1735: ifne +1479 -> 3214
      //   1738: iload 4
      //   1740: istore 5
      //   1742: aload_0
      //   1743: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1746: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1749: aload 21
      //   1751: invokevirtual 343	com/mcafee/registration/storage/a:ab	(Ljava/lang/String;)V
      //   1754: iload 4
      //   1756: istore 5
      //   1758: aload 24
      //   1760: ldc_w 345
      //   1763: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1766: astore 22
      //   1768: iload 4
      //   1770: istore 5
      //   1772: aload_0
      //   1773: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1776: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1779: astore 25
      //   1781: aload 22
      //   1783: astore 21
      //   1785: iload 4
      //   1787: istore 5
      //   1789: aload 22
      //   1791: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1794: ifeq +8 -> 1802
      //   1797: ldc_w 304
      //   1800: astore 21
      //   1802: iload 4
      //   1804: istore 5
      //   1806: aload 25
      //   1808: aload 21
      //   1810: invokevirtual 348	com/mcafee/registration/storage/a:az	(Ljava/lang/String;)V
      //   1813: iload 4
      //   1815: istore 5
      //   1817: aload 24
      //   1819: ldc_w 350
      //   1822: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1825: astore 22
      //   1827: iload 4
      //   1829: istore 5
      //   1831: aload_0
      //   1832: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1835: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1838: astore 25
      //   1840: aload 22
      //   1842: astore 21
      //   1844: iload 4
      //   1846: istore 5
      //   1848: aload 22
      //   1850: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1853: ifeq +8 -> 1861
      //   1856: ldc_w 304
      //   1859: astore 21
      //   1861: iload 4
      //   1863: istore 5
      //   1865: aload 25
      //   1867: aload 21
      //   1869: invokevirtual 353	com/mcafee/registration/storage/a:aA	(Ljava/lang/String;)V
      //   1872: iload 4
      //   1874: istore 5
      //   1876: aload 24
      //   1878: ldc_w 355
      //   1881: invokevirtual 175	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1884: astore 21
      //   1886: iload 4
      //   1888: istore 5
      //   1890: iload 4
      //   1892: istore 6
      //   1894: aload 21
      //   1896: invokestatic 212	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1899: ifne +1407 -> 3306
      //   1902: iload 4
      //   1904: istore 5
      //   1906: iload 4
      //   1908: istore 6
      //   1910: aload_0
      //   1911: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1914: invokestatic 133	com/mcafee/activation/ActivationWebPage:i	(Lcom/mcafee/activation/ActivationWebPage;)Landroid/content/Context;
      //   1917: invokestatic 360	com/mcafee/wsstorage/h:b	(Landroid/content/Context;)Lcom/mcafee/wsstorage/h;
      //   1920: aload 21
      //   1922: invokevirtual 362	com/mcafee/wsstorage/h:ar	(Ljava/lang/String;)V
      //   1925: iload 4
      //   1927: istore 5
      //   1929: iload 4
      //   1931: istore 6
      //   1933: aload_0
      //   1934: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1937: getfield 365	com/mcafee/activation/ActivationWebPage:a	Lcom/mcafee/activation/h;
      //   1940: invokevirtual 369	com/mcafee/activation/h:b	()V
      //   1943: iload 4
      //   1945: istore 5
      //   1947: iload 4
      //   1949: istore 6
      //   1951: aload_0
      //   1952: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1955: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   1958: ldc_w 304
      //   1961: invokevirtual 372	com/mcafee/registration/storage/a:E	(Ljava/lang/String;)V
      //   1964: iload 4
      //   1966: istore 5
      //   1968: iload 4
      //   1970: istore 6
      //   1972: aload_0
      //   1973: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1976: invokestatic 375	com/mcafee/activation/ActivationWebPage:q	(Lcom/mcafee/activation/ActivationWebPage;)Z
      //   1979: ifeq +1364 -> 3343
      //   1982: iload 4
      //   1984: istore 5
      //   1986: iload 4
      //   1988: istore 6
      //   1990: aload_0
      //   1991: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   1994: invokestatic 378	com/mcafee/activation/ActivationWebPage:p	(Lcom/mcafee/activation/ActivationWebPage;)Z
      //   1997: ifne +1346 -> 3343
      //   2000: iload 4
      //   2002: ifne +1341 -> 3343
      //   2005: iload 4
      //   2007: istore 5
      //   2009: iload 4
      //   2011: istore 6
      //   2013: aload_0
      //   2014: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2017: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   2020: iconst_1
      //   2021: invokevirtual 380	com/mcafee/registration/storage/a:A	(Z)V
      //   2024: iload 4
      //   2026: istore 5
      //   2028: iload 4
      //   2030: istore 6
      //   2032: new 23	com/mcafee/activation/ActivationWebPage$a$7
      //   2035: dup
      //   2036: aload_0
      //   2037: aload 23
      //   2039: invokespecial 381	com/mcafee/activation/ActivationWebPage$a$7:<init>	(Lcom/mcafee/activation/ActivationWebPage$a;Ljava/lang/String;)V
      //   2042: invokestatic 386	com/mcafee/android/c/g:a	(Ljava/lang/Runnable;)V
      //   2045: iload 4
      //   2047: istore 5
      //   2049: iload 4
      //   2051: istore 6
      //   2053: aload_0
      //   2054: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2057: invokestatic 133	com/mcafee/activation/ActivationWebPage:i	(Lcom/mcafee/activation/ActivationWebPage;)Landroid/content/Context;
      //   2060: invokestatic 360	com/mcafee/wsstorage/h:b	(Landroid/content/Context;)Lcom/mcafee/wsstorage/h;
      //   2063: iconst_1
      //   2064: invokevirtual 389	com/mcafee/wsstorage/h:V	(Z)J
      //   2067: lstore 14
      //   2069: iconst_0
      //   2070: istore_2
      //   2071: lload 14
      //   2073: lconst_0
      //   2074: lcmp
      //   2075: ifle +21 -> 2096
      //   2078: iload 4
      //   2080: istore 5
      //   2082: iload 4
      //   2084: istore 6
      //   2086: lload 14
      //   2088: ldc2_w 390
      //   2091: ldiv
      //   2092: l2i
      //   2093: iconst_1
      //   2094: iadd
      //   2095: istore_2
      //   2096: iload 4
      //   2098: istore 5
      //   2100: iload 4
      //   2102: istore 6
      //   2104: ldc 72
      //   2106: iconst_3
      //   2107: invokestatic 77	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
      //   2110: ifeq +70 -> 2180
      //   2113: iload 4
      //   2115: istore 5
      //   2117: iload 4
      //   2119: istore 6
      //   2121: ldc 72
      //   2123: new 79	java/lang/StringBuilder
      //   2126: dup
      //   2127: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2130: ldc_w 393
      //   2133: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2136: iload_2
      //   2137: invokevirtual 186	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2140: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2143: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2146: iload 4
      //   2148: istore 5
      //   2150: iload 4
      //   2152: istore 6
      //   2154: ldc 72
      //   2156: new 79	java/lang/StringBuilder
      //   2159: dup
      //   2160: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2163: ldc_w 395
      //   2166: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2169: lload 12
      //   2171: invokevirtual 398	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   2174: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2177: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2180: iload 4
      //   2182: istore 5
      //   2184: iload 4
      //   2186: istore 6
      //   2188: ldc 72
      //   2190: iconst_3
      //   2191: invokestatic 77	com/mcafee/android/e/o:a	(Ljava/lang/String;I)Z
      //   2194: ifeq +381 -> 2575
      //   2197: iload 4
      //   2199: istore 5
      //   2201: iload 4
      //   2203: istore 6
      //   2205: ldc 72
      //   2207: new 79	java/lang/StringBuilder
      //   2210: dup
      //   2211: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2214: ldc_w 400
      //   2217: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2220: aload 18
      //   2222: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2225: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2228: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2231: iload 4
      //   2233: istore 5
      //   2235: iload 4
      //   2237: istore 6
      //   2239: ldc 72
      //   2241: new 79	java/lang/StringBuilder
      //   2244: dup
      //   2245: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2248: ldc_w 402
      //   2251: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2254: iload_3
      //   2255: invokevirtual 186	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2258: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2261: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2264: iload 4
      //   2266: istore 5
      //   2268: iload 4
      //   2270: istore 6
      //   2272: ldc 72
      //   2274: new 79	java/lang/StringBuilder
      //   2277: dup
      //   2278: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2281: ldc_w 404
      //   2284: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2287: iload 7
      //   2289: invokevirtual 407	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
      //   2292: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2295: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2298: iload 4
      //   2300: istore 5
      //   2302: iload 4
      //   2304: istore 6
      //   2306: ldc 72
      //   2308: new 79	java/lang/StringBuilder
      //   2311: dup
      //   2312: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2315: ldc_w 409
      //   2318: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2321: aload 19
      //   2323: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2326: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2329: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2332: iload 4
      //   2334: istore 5
      //   2336: iload 4
      //   2338: istore 6
      //   2340: ldc 72
      //   2342: new 79	java/lang/StringBuilder
      //   2345: dup
      //   2346: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2349: ldc_w 411
      //   2352: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2355: aload 16
      //   2357: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2360: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2363: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2366: iload 4
      //   2368: istore 5
      //   2370: iload 4
      //   2372: istore 6
      //   2374: ldc 72
      //   2376: new 79	java/lang/StringBuilder
      //   2379: dup
      //   2380: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2383: ldc_w 413
      //   2386: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2389: aload 17
      //   2391: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2394: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2397: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2400: iload 4
      //   2402: istore 5
      //   2404: iload 4
      //   2406: istore 6
      //   2408: ldc 72
      //   2410: new 79	java/lang/StringBuilder
      //   2413: dup
      //   2414: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2417: ldc_w 415
      //   2420: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2423: aload 20
      //   2425: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2428: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2431: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2434: iload 4
      //   2436: istore 5
      //   2438: iload 4
      //   2440: istore 6
      //   2442: ldc 72
      //   2444: new 79	java/lang/StringBuilder
      //   2447: dup
      //   2448: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2451: ldc_w 417
      //   2454: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2457: iload 4
      //   2459: invokevirtual 407	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
      //   2462: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2465: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2468: iload 4
      //   2470: istore 5
      //   2472: iload 4
      //   2474: istore 6
      //   2476: ldc 72
      //   2478: new 79	java/lang/StringBuilder
      //   2481: dup
      //   2482: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2485: ldc_w 419
      //   2488: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2491: aload_1
      //   2492: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2495: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2498: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2501: iload 4
      //   2503: istore 5
      //   2505: iload 4
      //   2507: istore 6
      //   2509: ldc 72
      //   2511: new 79	java/lang/StringBuilder
      //   2514: dup
      //   2515: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2518: ldc_w 421
      //   2521: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2524: iload_2
      //   2525: i2l
      //   2526: invokestatic 424	java/lang/Long:toString	(J)Ljava/lang/String;
      //   2529: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2532: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2535: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2538: iload 4
      //   2540: istore 5
      //   2542: iload 4
      //   2544: istore 6
      //   2546: ldc 72
      //   2548: new 79	java/lang/StringBuilder
      //   2551: dup
      //   2552: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2555: ldc_w 426
      //   2558: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2561: lload 12
      //   2563: invokestatic 424	java/lang/Long:toString	(J)Ljava/lang/String;
      //   2566: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2569: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2572: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2575: iload 4
      //   2577: istore 5
      //   2579: iload 4
      //   2581: istore 6
      //   2583: invokestatic 432	com/intelsecurity/analytics/api/Track:userAttribute	()Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2586: ldc_w 434
      //   2589: invokevirtual 440	com/intelsecurity/analytics/api/trackers/UserAttributes:dataSet	(Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2592: ldc_w 442
      //   2595: aload 18
      //   2597: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2600: ldc_w 448
      //   2603: iload_3
      //   2604: invokestatic 451	java/lang/String:valueOf	(I)Ljava/lang/String;
      //   2607: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2610: ldc_w 453
      //   2613: iload 7
      //   2615: invokestatic 456	java/lang/String:valueOf	(Z)Ljava/lang/String;
      //   2618: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2621: ldc_w 458
      //   2624: aload 16
      //   2626: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2629: ldc_w 460
      //   2632: aload 17
      //   2634: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2637: ldc_w 462
      //   2640: iload 4
      //   2642: invokestatic 456	java/lang/String:valueOf	(Z)Ljava/lang/String;
      //   2645: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2648: ldc_w 464
      //   2651: aload_1
      //   2652: invokestatic 467	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   2655: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2658: ldc_w 469
      //   2661: iload_2
      //   2662: invokestatic 451	java/lang/String:valueOf	(I)Ljava/lang/String;
      //   2665: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2668: ldc_w 471
      //   2671: lload 12
      //   2673: invokestatic 473	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   2676: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2679: ldc_w 475
      //   2682: aload 20
      //   2684: invokevirtual 446	com/intelsecurity/analytics/api/trackers/UserAttributes:add	(Ljava/lang/String;Ljava/lang/String;)Lcom/intelsecurity/analytics/api/trackers/UserAttributes;
      //   2687: invokevirtual 479	com/intelsecurity/analytics/api/trackers/UserAttributes:finish	()Z
      //   2690: pop
      //   2691: aload_0
      //   2692: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2695: invokestatic 375	com/mcafee/activation/ActivationWebPage:q	(Lcom/mcafee/activation/ActivationWebPage;)Z
      //   2698: ifeq +18 -> 2716
      //   2701: iload 4
      //   2703: ifne +13 -> 2716
      //   2706: aload_0
      //   2707: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2710: invokestatic 378	com/mcafee/activation/ActivationWebPage:p	(Lcom/mcafee/activation/ActivationWebPage;)Z
      //   2713: ifeq +10 -> 2723
      //   2716: aload_0
      //   2717: getfield 61	com/mcafee/activation/ActivationWebPage$a:g	Landroid/app/Activity;
      //   2720: invokevirtual 483	android/app/Activity:finish	()V
      //   2723: return
      //   2724: iload 9
      //   2726: istore 5
      //   2728: iload 10
      //   2730: istore 6
      //   2732: aload 16
      //   2734: iconst_1
      //   2735: invokevirtual 226	com/mcafee/wsstorage/ConfigManager:b	(Z)V
      //   2738: iload 9
      //   2740: istore 5
      //   2742: iload 10
      //   2744: istore 6
      //   2746: ldc 72
      //   2748: new 79	java/lang/StringBuilder
      //   2751: dup
      //   2752: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2755: ldc_w 485
      //   2758: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2761: aload_1
      //   2762: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2765: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2768: invokestatic 93	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   2771: goto -2368 -> 403
      //   2774: astore_1
      //   2775: iload 5
      //   2777: istore 4
      //   2779: ldc 72
      //   2781: ldc_w 486
      //   2784: aload_1
      //   2785: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   2788: goto -97 -> 2691
      //   2791: iload 9
      //   2793: istore 5
      //   2795: aload_0
      //   2796: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2799: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   2802: iconst_0
      //   2803: invokevirtual 234	com/mcafee/registration/storage/a:i	(Z)V
      //   2806: goto -2352 -> 454
      //   2809: astore_1
      //   2810: iload 9
      //   2812: istore 5
      //   2814: iload 10
      //   2816: istore 6
      //   2818: ldc 72
      //   2820: ldc_w 486
      //   2823: aload_1
      //   2824: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   2827: goto -2373 -> 454
      //   2830: astore_1
      //   2831: ldc 72
      //   2833: ldc_w 486
      //   2836: aload_1
      //   2837: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   2840: iload 6
      //   2842: istore 4
      //   2844: goto -153 -> 2691
      //   2847: iload 9
      //   2849: istore 5
      //   2851: iload 10
      //   2853: istore 6
      //   2855: aload 18
      //   2857: aload_1
      //   2858: invokeinterface 490 2 0
      //   2863: goto -1840 -> 1023
      //   2866: astore_1
      //   2867: iload 9
      //   2869: istore 5
      //   2871: iload 10
      //   2873: istore 6
      //   2875: ldc 72
      //   2877: ldc_w 486
      //   2880: aload_1
      //   2881: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   2884: iload 4
      //   2886: istore 7
      //   2888: goto -1777 -> 1111
      //   2891: iload 9
      //   2893: istore 5
      //   2895: iload 10
      //   2897: istore 6
      //   2899: aload_0
      //   2900: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2903: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   2906: iconst_0
      //   2907: invokevirtual 300	com/mcafee/registration/storage/a:e	(Z)V
      //   2910: iload 9
      //   2912: istore 5
      //   2914: iload 10
      //   2916: istore 6
      //   2918: aload 18
      //   2920: astore_1
      //   2921: aload 18
      //   2923: ldc_w 492
      //   2926: invokevirtual 495	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   2929: ifne +33 -> 2962
      //   2932: iload 9
      //   2934: istore 5
      //   2936: iload 10
      //   2938: istore 6
      //   2940: new 79	java/lang/StringBuilder
      //   2943: dup
      //   2944: invokespecial 80	java/lang/StringBuilder:<init>	()V
      //   2947: ldc_w 492
      //   2950: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2953: aload 18
      //   2955: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2958: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2961: astore_1
      //   2962: iload 9
      //   2964: istore 5
      //   2966: iload 10
      //   2968: istore 6
      //   2970: aload_0
      //   2971: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2974: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   2977: aload_1
      //   2978: invokevirtual 497	com/mcafee/registration/storage/a:o	(Ljava/lang/String;)V
      //   2981: goto -1799 -> 1182
      //   2984: iload 9
      //   2986: istore 5
      //   2988: iload 10
      //   2990: istore 6
      //   2992: aload_0
      //   2993: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   2996: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   2999: aload 18
      //   3001: invokevirtual 307	com/mcafee/registration/storage/a:aa	(Ljava/lang/String;)V
      //   3004: iload 9
      //   3006: istore 5
      //   3008: iload 10
      //   3010: istore 6
      //   3012: aload_0
      //   3013: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   3016: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   3019: iconst_0
      //   3020: invokevirtual 310	com/mcafee/registration/storage/a:J	(Z)V
      //   3023: goto -1744 -> 1279
      //   3026: iload 9
      //   3028: istore 5
      //   3030: iload 10
      //   3032: istore 6
      //   3034: aload_0
      //   3035: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   3038: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   3041: iconst_1
      //   3042: invokevirtual 317	com/mcafee/registration/storage/a:h	(Z)V
      //   3045: ldc_w 499
      //   3048: astore 19
      //   3050: goto -1680 -> 1370
      //   3053: astore_1
      //   3054: iload 9
      //   3056: istore 5
      //   3058: iload 10
      //   3060: istore 6
      //   3062: ldc 72
      //   3064: ldc_w 486
      //   3067: aload_1
      //   3068: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3071: ldc_w 501
      //   3074: astore_1
      //   3075: goto -1653 -> 1422
      //   3078: astore 20
      //   3080: iload 9
      //   3082: istore 5
      //   3084: iload 10
      //   3086: istore 6
      //   3088: ldc 72
      //   3090: ldc_w 486
      //   3093: aload 20
      //   3095: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3098: goto -1620 -> 1478
      //   3101: astore 20
      //   3103: iload 9
      //   3105: istore 5
      //   3107: iload 10
      //   3109: istore 6
      //   3111: ldc 72
      //   3113: ldc_w 503
      //   3116: aload 20
      //   3118: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3121: ldc_w 304
      //   3124: astore 20
      //   3126: goto -1593 -> 1533
      //   3129: astore 21
      //   3131: iload 9
      //   3133: istore 5
      //   3135: iload 10
      //   3137: istore 6
      //   3139: ldc 72
      //   3141: ldc_w 505
      //   3144: aload 21
      //   3146: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3149: goto -1561 -> 1588
      //   3152: iload 4
      //   3154: istore 5
      //   3156: iload 4
      //   3158: istore 6
      //   3160: ldc 72
      //   3162: ldc_w 486
      //   3165: aload 21
      //   3167: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3170: goto -1525 -> 1645
      //   3173: iload 4
      //   3175: istore 5
      //   3177: aload_0
      //   3178: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   3181: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   3184: iconst_0
      //   3185: invokevirtual 340	com/mcafee/registration/storage/a:al	(Z)V
      //   3188: goto -1476 -> 1712
      //   3191: astore 21
      //   3193: iload 4
      //   3195: istore 5
      //   3197: iload 4
      //   3199: istore 6
      //   3201: ldc 72
      //   3203: ldc_w 507
      //   3206: aload 21
      //   3208: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3211: goto -1457 -> 1754
      //   3214: iload 4
      //   3216: istore 5
      //   3218: aload_0
      //   3219: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   3222: invokestatic 157	com/mcafee/activation/ActivationWebPage:k	(Lcom/mcafee/activation/ActivationWebPage;)Lcom/mcafee/registration/storage/a;
      //   3225: ldc_w 304
      //   3228: invokevirtual 343	com/mcafee/registration/storage/a:ab	(Ljava/lang/String;)V
      //   3231: goto -1477 -> 1754
      //   3234: astore 21
      //   3236: iload 4
      //   3238: istore 5
      //   3240: iload 4
      //   3242: istore 6
      //   3244: ldc 72
      //   3246: ldc_w 509
      //   3249: aload 21
      //   3251: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3254: goto -1441 -> 1813
      //   3257: astore 21
      //   3259: iload 4
      //   3261: istore 5
      //   3263: iload 4
      //   3265: istore 6
      //   3267: ldc 72
      //   3269: ldc_w 511
      //   3272: aload 21
      //   3274: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3277: goto -1405 -> 1872
      //   3280: astore 21
      //   3282: iload 4
      //   3284: istore 5
      //   3286: iload 4
      //   3288: istore 6
      //   3290: ldc 72
      //   3292: ldc_w 513
      //   3295: aload 21
      //   3297: invokestatic 488	com/mcafee/android/e/o:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   3300: aconst_null
      //   3301: astore 21
      //   3303: goto -1417 -> 1886
      //   3306: iload 4
      //   3308: istore 5
      //   3310: iload 4
      //   3312: istore 6
      //   3314: aload_0
      //   3315: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   3318: invokestatic 378	com/mcafee/activation/ActivationWebPage:p	(Lcom/mcafee/activation/ActivationWebPage;)Z
      //   3321: ifeq -1396 -> 1925
      //   3324: iload 4
      //   3326: istore 5
      //   3328: iload 4
      //   3330: istore 6
      //   3332: aload_0
      //   3333: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   3336: iload_3
      //   3337: invokestatic 516	com/mcafee/activation/ActivationWebPage:b	(Lcom/mcafee/activation/ActivationWebPage;I)V
      //   3340: goto -1415 -> 1925
      //   3343: iload 4
      //   3345: istore 5
      //   3347: iload 4
      //   3349: istore 6
      //   3351: aload_0
      //   3352: getfield 36	com/mcafee/activation/ActivationWebPage$a:f	Lcom/mcafee/activation/ActivationWebPage;
      //   3355: invokestatic 519	com/mcafee/activation/ActivationWebPage:n	(Lcom/mcafee/activation/ActivationWebPage;)V
      //   3358: goto -1313 -> 2045
      //   3361: astore_1
      //   3362: iconst_1
      //   3363: istore 4
      //   3365: goto -586 -> 2779
      //   3368: astore 21
      //   3370: iconst_1
      //   3371: istore 4
      //   3373: goto -221 -> 3152
      //   3376: iconst_0
      //   3377: istore 4
      //   3379: goto -1734 -> 1645
      //   3382: ldc_w 501
      //   3385: astore_1
      //   3386: goto -1964 -> 1422
      //   3389: ldc_w 304
      //   3392: astore 17
      //   3394: goto -2499 -> 895
      //   3397: ldc_w 304
      //   3400: astore 16
      //   3402: goto -2596 -> 806
      //   3405: astore 21
      //   3407: iload 8
      //   3409: istore 4
      //   3411: goto -259 -> 3152
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3414	0	this	a
      //   0	3414	1	paramString	String
      //   2070	592	2	i	int
      //   175	3162	3	j	int
      //   50	3360	4	bool1	boolean
      //   54	3292	5	bool2	boolean
      //   58	3292	6	bool3	boolean
      //   1027	1860	7	bool4	boolean
      //   47	3361	8	bool5	boolean
      //   41	3091	9	bool6	boolean
      //   44	3092	10	bool7	boolean
      //   1024	50	11	bool8	boolean
      //   263	2409	12	l1	long
      //   245	1842	14	l2	long
      //   70	3331	16	localObject1	Object
      //   822	2571	17	str1	String
      //   982	2018	18	localObject2	Object
      //   1368	1681	19	str2	String
      //   1434	1249	20	str3	String
      //   3078	16	20	localException1	Exception
      //   3101	16	20	localException2	Exception
      //   3124	1	20	str4	String
      //   1544	377	21	localObject3	Object
      //   3129	37	21	localException3	Exception
      //   3191	16	21	localException4	Exception
      //   3234	16	21	localException5	Exception
      //   3257	16	21	localException6	Exception
      //   3280	16	21	localException7	Exception
      //   3301	1	21	localObject4	Object
      //   3368	1	21	localException8	Exception
      //   3405	1	21	localException9	Exception
      //   1669	180	22	str5	String
      //   88	1950	23	str6	String
      //   106	1771	24	localJSONObject	JSONObject
      //   1779	87	25	localA	com.mcafee.registration.storage.a
      // Exception table:
      //   from	to	target	type
      //   60	72	2774	org/json/JSONException
      //   80	90	2774	org/json/JSONException
      //   98	108	2774	org/json/JSONException
      //   116	123	2774	org/json/JSONException
      //   131	144	2774	org/json/JSONException
      //   152	160	2774	org/json/JSONException
      //   168	176	2774	org/json/JSONException
      //   184	193	2774	org/json/JSONException
      //   201	234	2774	org/json/JSONException
      //   242	247	2774	org/json/JSONException
      //   255	265	2774	org/json/JSONException
      //   273	290	2774	org/json/JSONException
      //   298	306	2774	org/json/JSONException
      //   314	321	2774	org/json/JSONException
      //   329	338	2774	org/json/JSONException
      //   346	357	2774	org/json/JSONException
      //   365	371	2774	org/json/JSONException
      //   379	403	2774	org/json/JSONException
      //   407	415	2774	org/json/JSONException
      //   419	426	2774	org/json/JSONException
      //   430	439	2774	org/json/JSONException
      //   443	454	2774	org/json/JSONException
      //   462	470	2774	org/json/JSONException
      //   478	485	2774	org/json/JSONException
      //   493	502	2774	org/json/JSONException
      //   510	519	2774	org/json/JSONException
      //   527	535	2774	org/json/JSONException
      //   543	550	2774	org/json/JSONException
      //   558	567	2774	org/json/JSONException
      //   575	584	2774	org/json/JSONException
      //   592	600	2774	org/json/JSONException
      //   608	615	2774	org/json/JSONException
      //   623	632	2774	org/json/JSONException
      //   640	650	2774	org/json/JSONException
      //   658	667	2774	org/json/JSONException
      //   675	682	2774	org/json/JSONException
      //   690	699	2774	org/json/JSONException
      //   707	717	2774	org/json/JSONException
      //   725	735	2774	org/json/JSONException
      //   743	751	2774	org/json/JSONException
      //   759	769	2774	org/json/JSONException
      //   777	786	2774	org/json/JSONException
      //   794	806	2774	org/json/JSONException
      //   814	824	2774	org/json/JSONException
      //   832	840	2774	org/json/JSONException
      //   848	858	2774	org/json/JSONException
      //   866	875	2774	org/json/JSONException
      //   883	895	2774	org/json/JSONException
      //   903	912	2774	org/json/JSONException
      //   920	927	2774	org/json/JSONException
      //   935	944	2774	org/json/JSONException
      //   952	960	2774	org/json/JSONException
      //   968	984	2774	org/json/JSONException
      //   992	1006	2774	org/json/JSONException
      //   1014	1023	2774	org/json/JSONException
      //   1037	1046	2774	org/json/JSONException
      //   1058	1065	2774	org/json/JSONException
      //   1077	1086	2774	org/json/JSONException
      //   1096	1107	2774	org/json/JSONException
      //   1119	1129	2774	org/json/JSONException
      //   1137	1145	2774	org/json/JSONException
      //   1153	1163	2774	org/json/JSONException
      //   1171	1182	2774	org/json/JSONException
      //   1190	1200	2774	org/json/JSONException
      //   1208	1216	2774	org/json/JSONException
      //   1224	1234	2774	org/json/JSONException
      //   1242	1255	2774	org/json/JSONException
      //   1263	1274	2774	org/json/JSONException
      //   1287	1296	2774	org/json/JSONException
      //   1304	1311	2774	org/json/JSONException
      //   1319	1328	2774	org/json/JSONException
      //   1336	1346	2774	org/json/JSONException
      //   1354	1365	2774	org/json/JSONException
      //   1374	1383	2774	org/json/JSONException
      //   1387	1394	2774	org/json/JSONException
      //   1398	1407	2774	org/json/JSONException
      //   1411	1422	2774	org/json/JSONException
      //   1426	1436	2774	org/json/JSONException
      //   1440	1448	2774	org/json/JSONException
      //   1452	1462	2774	org/json/JSONException
      //   1466	1478	2774	org/json/JSONException
      //   1482	1491	2774	org/json/JSONException
      //   1495	1503	2774	org/json/JSONException
      //   1507	1517	2774	org/json/JSONException
      //   1521	1533	2774	org/json/JSONException
      //   1537	1546	2774	org/json/JSONException
      //   1550	1558	2774	org/json/JSONException
      //   1562	1572	2774	org/json/JSONException
      //   1576	1588	2774	org/json/JSONException
      //   1592	1601	2774	org/json/JSONException
      //   1605	1613	2774	org/json/JSONException
      //   1617	1626	2774	org/json/JSONException
      //   1649	1658	2774	org/json/JSONException
      //   1662	1671	2774	org/json/JSONException
      //   1675	1683	2774	org/json/JSONException
      //   1687	1697	2774	org/json/JSONException
      //   1701	1712	2774	org/json/JSONException
      //   1716	1724	2774	org/json/JSONException
      //   1728	1738	2774	org/json/JSONException
      //   1742	1754	2774	org/json/JSONException
      //   1758	1768	2774	org/json/JSONException
      //   1772	1781	2774	org/json/JSONException
      //   1789	1797	2774	org/json/JSONException
      //   1806	1813	2774	org/json/JSONException
      //   1817	1827	2774	org/json/JSONException
      //   1831	1840	2774	org/json/JSONException
      //   1848	1856	2774	org/json/JSONException
      //   1865	1872	2774	org/json/JSONException
      //   1876	1886	2774	org/json/JSONException
      //   1894	1902	2774	org/json/JSONException
      //   1910	1925	2774	org/json/JSONException
      //   1933	1943	2774	org/json/JSONException
      //   1951	1964	2774	org/json/JSONException
      //   1972	1982	2774	org/json/JSONException
      //   1990	2000	2774	org/json/JSONException
      //   2013	2024	2774	org/json/JSONException
      //   2032	2045	2774	org/json/JSONException
      //   2053	2069	2774	org/json/JSONException
      //   2086	2096	2774	org/json/JSONException
      //   2104	2113	2774	org/json/JSONException
      //   2121	2146	2774	org/json/JSONException
      //   2154	2180	2774	org/json/JSONException
      //   2188	2197	2774	org/json/JSONException
      //   2205	2231	2774	org/json/JSONException
      //   2239	2264	2774	org/json/JSONException
      //   2272	2298	2774	org/json/JSONException
      //   2306	2332	2774	org/json/JSONException
      //   2340	2366	2774	org/json/JSONException
      //   2374	2400	2774	org/json/JSONException
      //   2408	2434	2774	org/json/JSONException
      //   2442	2468	2774	org/json/JSONException
      //   2476	2501	2774	org/json/JSONException
      //   2509	2538	2774	org/json/JSONException
      //   2546	2575	2774	org/json/JSONException
      //   2583	2691	2774	org/json/JSONException
      //   2732	2738	2774	org/json/JSONException
      //   2746	2771	2774	org/json/JSONException
      //   2795	2806	2774	org/json/JSONException
      //   2818	2827	2774	org/json/JSONException
      //   2855	2863	2774	org/json/JSONException
      //   2875	2884	2774	org/json/JSONException
      //   2899	2910	2774	org/json/JSONException
      //   2921	2932	2774	org/json/JSONException
      //   2940	2962	2774	org/json/JSONException
      //   2970	2981	2774	org/json/JSONException
      //   2992	3004	2774	org/json/JSONException
      //   3012	3023	2774	org/json/JSONException
      //   3034	3045	2774	org/json/JSONException
      //   3062	3071	2774	org/json/JSONException
      //   3088	3098	2774	org/json/JSONException
      //   3111	3121	2774	org/json/JSONException
      //   3139	3149	2774	org/json/JSONException
      //   3160	3170	2774	org/json/JSONException
      //   3177	3188	2774	org/json/JSONException
      //   3201	3211	2774	org/json/JSONException
      //   3218	3231	2774	org/json/JSONException
      //   3244	3254	2774	org/json/JSONException
      //   3267	3277	2774	org/json/JSONException
      //   3290	3300	2774	org/json/JSONException
      //   3314	3324	2774	org/json/JSONException
      //   3332	3340	2774	org/json/JSONException
      //   3351	3358	2774	org/json/JSONException
      //   407	415	2809	java/lang/Exception
      //   419	426	2809	java/lang/Exception
      //   430	439	2809	java/lang/Exception
      //   443	454	2809	java/lang/Exception
      //   2795	2806	2809	java/lang/Exception
      //   60	72	2830	java/lang/Exception
      //   80	90	2830	java/lang/Exception
      //   98	108	2830	java/lang/Exception
      //   116	123	2830	java/lang/Exception
      //   131	144	2830	java/lang/Exception
      //   152	160	2830	java/lang/Exception
      //   168	176	2830	java/lang/Exception
      //   184	193	2830	java/lang/Exception
      //   201	234	2830	java/lang/Exception
      //   242	247	2830	java/lang/Exception
      //   255	265	2830	java/lang/Exception
      //   273	290	2830	java/lang/Exception
      //   298	306	2830	java/lang/Exception
      //   314	321	2830	java/lang/Exception
      //   329	338	2830	java/lang/Exception
      //   346	357	2830	java/lang/Exception
      //   365	371	2830	java/lang/Exception
      //   379	403	2830	java/lang/Exception
      //   462	470	2830	java/lang/Exception
      //   478	485	2830	java/lang/Exception
      //   493	502	2830	java/lang/Exception
      //   510	519	2830	java/lang/Exception
      //   527	535	2830	java/lang/Exception
      //   543	550	2830	java/lang/Exception
      //   558	567	2830	java/lang/Exception
      //   575	584	2830	java/lang/Exception
      //   592	600	2830	java/lang/Exception
      //   608	615	2830	java/lang/Exception
      //   623	632	2830	java/lang/Exception
      //   640	650	2830	java/lang/Exception
      //   658	667	2830	java/lang/Exception
      //   675	682	2830	java/lang/Exception
      //   690	699	2830	java/lang/Exception
      //   707	717	2830	java/lang/Exception
      //   725	735	2830	java/lang/Exception
      //   743	751	2830	java/lang/Exception
      //   759	769	2830	java/lang/Exception
      //   777	786	2830	java/lang/Exception
      //   794	806	2830	java/lang/Exception
      //   814	824	2830	java/lang/Exception
      //   832	840	2830	java/lang/Exception
      //   848	858	2830	java/lang/Exception
      //   866	875	2830	java/lang/Exception
      //   883	895	2830	java/lang/Exception
      //   903	912	2830	java/lang/Exception
      //   920	927	2830	java/lang/Exception
      //   935	944	2830	java/lang/Exception
      //   952	960	2830	java/lang/Exception
      //   968	984	2830	java/lang/Exception
      //   992	1006	2830	java/lang/Exception
      //   1014	1023	2830	java/lang/Exception
      //   1119	1129	2830	java/lang/Exception
      //   1137	1145	2830	java/lang/Exception
      //   1153	1163	2830	java/lang/Exception
      //   1171	1182	2830	java/lang/Exception
      //   1190	1200	2830	java/lang/Exception
      //   1208	1216	2830	java/lang/Exception
      //   1224	1234	2830	java/lang/Exception
      //   1242	1255	2830	java/lang/Exception
      //   1263	1274	2830	java/lang/Exception
      //   1287	1296	2830	java/lang/Exception
      //   1304	1311	2830	java/lang/Exception
      //   1319	1328	2830	java/lang/Exception
      //   1336	1346	2830	java/lang/Exception
      //   1354	1365	2830	java/lang/Exception
      //   1894	1902	2830	java/lang/Exception
      //   1910	1925	2830	java/lang/Exception
      //   1933	1943	2830	java/lang/Exception
      //   1951	1964	2830	java/lang/Exception
      //   1972	1982	2830	java/lang/Exception
      //   1990	2000	2830	java/lang/Exception
      //   2013	2024	2830	java/lang/Exception
      //   2032	2045	2830	java/lang/Exception
      //   2053	2069	2830	java/lang/Exception
      //   2086	2096	2830	java/lang/Exception
      //   2104	2113	2830	java/lang/Exception
      //   2121	2146	2830	java/lang/Exception
      //   2154	2180	2830	java/lang/Exception
      //   2188	2197	2830	java/lang/Exception
      //   2205	2231	2830	java/lang/Exception
      //   2239	2264	2830	java/lang/Exception
      //   2272	2298	2830	java/lang/Exception
      //   2306	2332	2830	java/lang/Exception
      //   2340	2366	2830	java/lang/Exception
      //   2374	2400	2830	java/lang/Exception
      //   2408	2434	2830	java/lang/Exception
      //   2442	2468	2830	java/lang/Exception
      //   2476	2501	2830	java/lang/Exception
      //   2509	2538	2830	java/lang/Exception
      //   2546	2575	2830	java/lang/Exception
      //   2583	2691	2830	java/lang/Exception
      //   2732	2738	2830	java/lang/Exception
      //   2746	2771	2830	java/lang/Exception
      //   2818	2827	2830	java/lang/Exception
      //   2855	2863	2830	java/lang/Exception
      //   2875	2884	2830	java/lang/Exception
      //   2899	2910	2830	java/lang/Exception
      //   2921	2932	2830	java/lang/Exception
      //   2940	2962	2830	java/lang/Exception
      //   2970	2981	2830	java/lang/Exception
      //   2992	3004	2830	java/lang/Exception
      //   3012	3023	2830	java/lang/Exception
      //   3034	3045	2830	java/lang/Exception
      //   3062	3071	2830	java/lang/Exception
      //   3088	3098	2830	java/lang/Exception
      //   3111	3121	2830	java/lang/Exception
      //   3139	3149	2830	java/lang/Exception
      //   3160	3170	2830	java/lang/Exception
      //   3201	3211	2830	java/lang/Exception
      //   3244	3254	2830	java/lang/Exception
      //   3267	3277	2830	java/lang/Exception
      //   3290	3300	2830	java/lang/Exception
      //   3314	3324	2830	java/lang/Exception
      //   3332	3340	2830	java/lang/Exception
      //   3351	3358	2830	java/lang/Exception
      //   1037	1046	2866	java/lang/Exception
      //   1058	1065	2866	java/lang/Exception
      //   1077	1086	2866	java/lang/Exception
      //   1096	1107	2866	java/lang/Exception
      //   1374	1383	3053	java/lang/Exception
      //   1387	1394	3053	java/lang/Exception
      //   1398	1407	3053	java/lang/Exception
      //   1411	1422	3053	java/lang/Exception
      //   1426	1436	3078	java/lang/Exception
      //   1440	1448	3078	java/lang/Exception
      //   1452	1462	3078	java/lang/Exception
      //   1466	1478	3078	java/lang/Exception
      //   1482	1491	3101	java/lang/Exception
      //   1495	1503	3101	java/lang/Exception
      //   1507	1517	3101	java/lang/Exception
      //   1521	1533	3101	java/lang/Exception
      //   1537	1546	3129	java/lang/Exception
      //   1550	1558	3129	java/lang/Exception
      //   1562	1572	3129	java/lang/Exception
      //   1576	1588	3129	java/lang/Exception
      //   1649	1658	3191	java/lang/Exception
      //   1662	1671	3191	java/lang/Exception
      //   1675	1683	3191	java/lang/Exception
      //   1687	1697	3191	java/lang/Exception
      //   1701	1712	3191	java/lang/Exception
      //   1716	1724	3191	java/lang/Exception
      //   1728	1738	3191	java/lang/Exception
      //   1742	1754	3191	java/lang/Exception
      //   3177	3188	3191	java/lang/Exception
      //   3218	3231	3191	java/lang/Exception
      //   1758	1768	3234	java/lang/Exception
      //   1772	1781	3234	java/lang/Exception
      //   1789	1797	3234	java/lang/Exception
      //   1806	1813	3234	java/lang/Exception
      //   1817	1827	3257	java/lang/Exception
      //   1831	1840	3257	java/lang/Exception
      //   1848	1856	3257	java/lang/Exception
      //   1865	1872	3257	java/lang/Exception
      //   1876	1886	3280	java/lang/Exception
      //   1634	1645	3361	org/json/JSONException
      //   1634	1645	3368	java/lang/Exception
      //   1592	1601	3405	java/lang/Exception
      //   1605	1613	3405	java/lang/Exception
      //   1617	1626	3405	java/lang/Exception
    }
    
    @JavascriptInterface
    public void Trace(String paramString)
    {
      if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
        com.mcafee.android.e.o.b("ActivationWebPage", paramString);
      }
    }
    
    @JavascriptInterface
    public void VerifyPhoneNumber(String paramString)
    {
      ConfigManager localConfigManager = ConfigManager.a(ActivationWebPage.i(ActivationWebPage.this));
      ActivationWebPage.k(ActivationWebPage.this).f(true);
      if (!paramString.startsWith("+")) {}
      for (Object localObject = "+" + paramString;; localObject = paramString)
      {
        ActivationWebPage.k(ActivationWebPage.this).o((String)localObject);
        ActivationWebPage.b(ActivationWebPage.this, true);
        ActivationWebPage.this.a.b();
        com.mcafee.android.e.o.e("ActivationWebPage", "Start phone verification loopback.");
        localObject = Constants.DialogID.aa;
        localObject = CommonPhoneUtils.a(ActivationWebPage.i(ActivationWebPage.this), false, false);
        if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
          com.mcafee.android.e.o.b("ActivationWebPage", "Imsi State after getCurrentIMSIStateOnBoot = " + localObject);
        }
        if ((CommonPhoneUtils.b(ActivationWebPage.i(ActivationWebPage.this))) || ((localObject != CommonPhoneUtils.SimState.e) && (localObject != CommonPhoneUtils.SimState.f) && (localObject != CommonPhoneUtils.SimState.g)))
        {
          if (ActivationWebPage.this.b.a(paramString, com.mcafee.registration.storage.a.a(ActivationWebPage.i(ActivationWebPage.this)).aC(), "") != Constants.DialogID.ad)
          {
            com.mcafee.android.e.o.e("ActivationWebPage", "Phone format verification failure.");
            ActivationWebPage.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                ActivationWebPage.this.a(0);
              }
            });
          }
          do
          {
            return;
            com.mcafee.android.e.o.e("ActivationWebPage", "Phone format verification and sending SMS success.");
            localObject = ActivationWebPage.k(ActivationWebPage.this).g();
            new com.mcafee.o.c(ActivationWebPage.i(ActivationWebPage.this));
          } while (paramString.equalsIgnoreCase((String)localObject));
          ActivationWebPage.a(ActivationWebPage.this, null);
          ActivationWebPage.a(ActivationWebPage.this, new com.wavesecure.core.g(localConfigManager.b(ConfigManager.Configuration.j) * 1000, 1, ActivationWebPage.this, (g.a)ActivationWebPage.l(ActivationWebPage.this)));
          ActivationWebPage.b(ActivationWebPage.this).i();
          ActivationWebPage.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              ActivationWebPage.this.g();
            }
          });
          return;
        }
        ActivationWebPage.this.showDialog(3);
        return;
      }
    }
    
    @JavascriptInterface
    public void close(final boolean paramBoolean, final String paramString)
    {
      com.mcafee.android.c.g.b(new Runnable()
      {
        public void run()
        {
          if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
            com.mcafee.android.e.o.c("ActivationWebPage", "Exit payment error msg " + paramString);
          }
          if ((paramBoolean) && (paramString != null))
          {
            com.wavesecure.utils.j.a(ActivationWebPage.this, ActivationWebPage.k(ActivationWebPage.this).ba(), paramString, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
                  com.mcafee.android.e.o.c("ActivationWebPage", "Exit Callback " + ActivationWebPage.a.1.this.a);
                }
                ActivationWebPage.a.a(ActivationWebPage.a.this).finish();
              }
            });
            return;
          }
          ActivationWebPage.a.a(ActivationWebPage.a.this).finish();
        }
      });
    }
    
    @JavascriptInterface
    public void navigation(String paramString)
    {
      if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
        com.mcafee.android.e.o.b("ActivationWebPage", "navigation requested: " + paramString);
      }
      if ("Exit".equalsIgnoreCase(paramString))
      {
        ActivationWebPage.this.b.d.finish();
        ActivationWebPage.this.finish();
        ActivationWebPage.m(ActivationWebPage.this);
      }
    }
    
    @JavascriptInterface
    public void notify(String paramString1, final String paramString2)
    {
      if (com.mcafee.android.e.o.a("ActivationWebPage", 4))
      {
        com.mcafee.android.e.o.c("ActivationWebPage", "Callback from web page: " + paramString1);
        com.mcafee.android.e.o.c("ActivationWebPage", "Response = " + paramString2);
      }
      try
      {
        getClass().getMethod(paramString1, new Class[] { String.class }).invoke(this, new Object[] { paramString2 });
        return;
      }
      catch (SecurityException localSecurityException)
      {
        do
        {
          com.mcafee.android.e.o.d("ActivationWebPage", "", localSecurityException);
        } while (!paramString1.equalsIgnoreCase("StoreActivationData"));
        com.mcafee.android.c.a.b(new Runnable()
        {
          public void run()
          {
            ActivationWebPage.a.this.StoreActivationData(paramString2);
            com.mcafee.android.e.o.b("ActivationWebPage", "explicit call to StoreActivationData()");
          }
        });
        return;
      }
      catch (Exception paramString1)
      {
        ActivationWebPage.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            ActivationWebPage.this.showDialog(2);
          }
        });
        return;
      }
      catch (NoSuchMethodException paramString1)
      {
        com.mcafee.android.e.o.d("ActivationWebPage", "", paramString1);
      }
    }
    
    @JavascriptInterface
    public void openURL(String paramString)
    {
      if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
        com.mcafee.android.e.o.b("ActivationWebPage", "Opening URL: " + paramString);
      }
      CommonPhoneUtils.e(ActivationWebPage.i(ActivationWebPage.this), paramString);
    }
    
    @JavascriptInterface
    public void promoButtonClick(String paramString)
    {
      if (com.mcafee.android.e.o.a("ActivationWebPage", 3)) {
        com.mcafee.android.e.o.b("ActivationWebPage", "promo button click: " + paramString);
      }
      if (Integer.parseInt(paramString) == 8)
      {
        ActivationWebPage.n(ActivationWebPage.this);
        this.g.finish();
        return;
      }
      navigation("Exit");
    }
  }
  
  public class b
    extends WebViewClient
  {
    public b() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
        com.mcafee.android.e.o.c("ActivationWebPage", "Activation page loaded: " + paramString);
      }
      ActivationWebPage.f(ActivationWebPage.this);
      ActivationWebPage.a(ActivationWebPage.this);
      if (ActivationWebPage.b(ActivationWebPage.this) != null)
      {
        ActivationWebPage.b(ActivationWebPage.this).j();
        ActivationWebPage.a(ActivationWebPage.this, null);
      }
      ActivationWebPage.a(ActivationWebPage.this, System.currentTimeMillis());
      int i = (int)((ActivationWebPage.g(ActivationWebPage.this) - ActivationWebPage.h(ActivationWebPage.this)) / 1000L);
      if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
        com.mcafee.android.e.o.c("ActivationWebPage", "Activation page load time: " + i);
      }
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
        com.mcafee.android.e.o.c("ActivationWebPage", "Error received : " + paramInt + " :" + paramString1);
      }
      switch (paramInt)
      {
      default: 
        if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
          com.mcafee.android.e.o.c("ActivationWebPage", "Closing web-page due to error: " + paramInt);
        }
        ActivationWebPage.a(ActivationWebPage.this);
        ActivationWebPage.e(ActivationWebPage.this);
        ActivationWebPage.a(ActivationWebPage.this, 1);
        return;
      case -6: 
        com.mcafee.android.e.o.c("ActivationWebPage", "Reloading web-page.");
        paramString1 = ActivationWebPage.c(ActivationWebPage.this);
        ActivationWebPage.this.a(paramWebView, paramString1, ActivationWebPage.d(ActivationWebPage.this));
        return;
      }
      if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
        com.mcafee.android.e.o.c("ActivationWebPage", "Closing web-page due to error: " + paramInt);
      }
      ActivationWebPage.a(ActivationWebPage.this);
      ActivationWebPage.e(ActivationWebPage.this);
      ActivationWebPage.a(ActivationWebPage.this, 4);
    }
    
    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      if (com.mcafee.debug.b.a(ActivationWebPage.this, "unsecured_mode", false))
      {
        if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
          com.mcafee.android.e.o.c("ActivationWebPage", "Ignore SSL error: " + paramSslError);
        }
        paramSslErrorHandler.proceed();
        return;
      }
      if (com.mcafee.android.e.o.a("ActivationWebPage", 4)) {
        com.mcafee.android.e.o.c("ActivationWebPage", "Closing web-page due to SSL error: " + paramSslError);
      }
      paramSslErrorHandler.cancel();
      ActivationWebPage.a(ActivationWebPage.this);
      if (ActivationWebPage.b(ActivationWebPage.this) != null)
      {
        ActivationWebPage.b(ActivationWebPage.this).j();
        ActivationWebPage.a(ActivationWebPage.this, null);
      }
      ActivationWebPage.a(ActivationWebPage.this, 4);
    }
  }
  
  private class c
    extends AsyncTask<Void, Void, Void>
  {
    private final Bundle b;
    
    public c(Bundle paramBundle)
    {
      this.b = paramBundle;
    }
    
    protected Void a(Void... paramVarArgs)
    {
      paramVarArgs = CommonPhoneUtils.V(ActivationWebPage.i(ActivationWebPage.this));
      com.mcafee.wsstorage.h.b(ActivationWebPage.i(ActivationWebPage.this)).Q(paramVarArgs);
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      ActivationWebPage.a(ActivationWebPage.this, true);
      ActivationWebPage.a(ActivationWebPage.this, this.b);
    }
  }
}
