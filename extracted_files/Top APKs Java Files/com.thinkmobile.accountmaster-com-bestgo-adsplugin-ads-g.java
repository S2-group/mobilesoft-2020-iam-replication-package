package com.bestgo.adsplugin.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.bestgo.adsplugin.R.id;
import com.bestgo.adsplugin.R.layout;
import com.bestgo.adsplugin.ads.activity.RecommendAdActivity;
import com.bestgo.adsplugin.ads.listener.AdStateListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONObject;

public class g
{
  private static final int h = Build.VERSION.SDK_INT;
  WebView a;
  String b;
  private Context c;
  private ArrayList<com.bestgo.adsplugin.views.b> d;
  private float e;
  private AdStateListener f;
  private com.bestgo.adsplugin.views.a g;
  private SharedPreferences i;
  
  public g() {}
  
  private void a(a.t paramT)
  {
    try
    {
      paramT.b = true;
      return;
    }
    finally
    {
      paramT = finally;
      throw paramT;
    }
  }
  
  private void a(b paramB)
  {
    SharedPreferences.Editor localEditor = this.i.edit();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("taskId");
    localStringBuilder.append(paramB.a);
    localEditor.putString(localStringBuilder.toString(), paramB.toString());
    localEditor.commit();
  }
  
  private View b(int paramInt)
  {
    Object localObject2 = AdAppHelper.getInstance(this.c).getConfig();
    final int j = ((a)localObject2).t.a;
    int m = 1;
    if ((j == 1) && (((a)localObject2).t.c > 0)) {
      j = 1;
    } else {
      j = 0;
    }
    Object localObject1 = null;
    if (j == 0) {
      return null;
    }
    if (((a)localObject2).t.h != null)
    {
      if (((a)localObject2).t.h.length == 0) {
        return null;
      }
      if (this.d.size() < ((a)localObject2).t.h.length) {
        this.d.add(new com.bestgo.adsplugin.views.b(this.c));
      }
      j = 0;
      while (j < this.d.size())
      {
        com.bestgo.adsplugin.views.b localB = (com.bestgo.adsplugin.views.b)this.d.get(j);
        Object localObject3 = (ViewGroup)localB.getParent();
        if (localObject3 != null) {
          ((ViewGroup)localObject3).removeView(localB);
        }
        Object localObject4;
        if ((j < ((a)localObject2).t.h.length) && (j < ((a)localObject2).t.g.length))
        {
          k = localB.getChildCount();
          localObject3 = localObject2.t.h[j];
          localObject4 = localObject2.t.g[j];
          if ((((a.v)localObject3).b) && (!((a.v)localObject3).m)) {
            if (k == 0) {
              localObject5 = LayoutInflater.from(this.c);
            }
          }
        }
        try
        {
          localObject2 = ((LayoutInflater)localObject5).inflate(R.layout.adsplugin_recommend_native_layout, localB, false);
          localObject1 = localObject2;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            float f1;
            Object localObject6;
          }
        }
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = ((LayoutInflater)localObject5).inflate(R.layout.adsplugin_recommend_native_nowebview_layout, localB, false);
        }
        localB.addView((View)localObject2, new FrameLayout.LayoutParams(-2, -2, 17));
        AdAppHelper.getInstance(this.c).logEvent("ADSDK_广告位_显示", "RECOMMEND_NATIVE", ((a.v)localObject3).e);
        localObject1 = localB.findViewById(R.id.ads_plugin_native_ad_unit);
        localObject2 = (FrameLayout.LayoutParams)((View)localObject1).getLayoutParams();
        if (((a.v)localObject3).n > 0) {
          k = (int)(((a.v)localObject3).n * this.e);
        } else {
          k = -2;
        }
        ((FrameLayout.LayoutParams)localObject2).width = k;
        if (paramInt == -1)
        {
          if (((a.v)localObject3).o > 0)
          {
            f1 = ((a.v)localObject3).o;
          }
          else
          {
            k = -2;
            break label443;
          }
        }
        else
        {
          if (paramInt <= 0) {
            break label453;
          }
          f1 = paramInt;
        }
        int k = (int)(f1 * this.e);
        label443:
        ((FrameLayout.LayoutParams)localObject2).height = k;
        break label473;
        label453:
        if (paramInt == -2)
        {
          ((FrameLayout.LayoutParams)localObject2).height = -2;
          ((FrameLayout.LayoutParams)localObject2).gravity = 17;
        }
        label473:
        ((View)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
        if (((a.v)localObject3).p)
        {
          this.a = ((WebView)((View)localObject1).findViewById(R.id.webView));
          if ((this.a == null) || (this.a.getVisibility() == 0)) {
            break label1091;
          }
          this.a.addJavascriptInterface(new a((a.v)localObject3), "AdControl");
          j();
          localObject2 = ((a.v)localObject3).h;
          localObject4 = new StringBuffer();
          localObject5 = new HashMap();
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(((a.v)localObject3).n);
          ((StringBuilder)localObject1).append("x");
          ((StringBuilder)localObject1).append(((a.v)localObject3).o);
          ((HashMap)localObject5).put("format", ((StringBuilder)localObject1).toString());
          ((HashMap)localObject5).put("package_name", this.c.getPackageName());
          ((HashMap)localObject5).put("gl", Locale.getDefault().getCountry());
          ((HashMap)localObject5).put("hl", Locale.getDefault().getLanguage());
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(TimeZone.getDefault().getRawOffset() / 3600000);
          ((StringBuilder)localObject1).append("");
          ((HashMap)localObject5).put("t", ((StringBuilder)localObject1).toString());
          paramInt = m;
          if (((String)localObject2).indexOf("?") > 0) {
            paramInt = 0;
          }
          localObject3 = ((HashMap)localObject5).keySet().iterator();
          if (((Iterator)localObject3).hasNext()) {
            localObject6 = (String)((Iterator)localObject3).next();
          }
        }
        try
        {
          localObject7 = new StringBuilder();
          if (paramInt == 0) {
            break label1118;
          }
          localObject1 = "?";
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            Object localObject7;
            break;
            String str = "&";
          }
          paramInt = 0;
        }
        ((StringBuilder)localObject7).append((String)localObject1);
        ((StringBuilder)localObject7).append(URLEncoder.encode(((String)localObject6).toString(), "UTF-8"));
        ((StringBuilder)localObject7).append("=");
        ((StringBuilder)localObject7).append(URLEncoder.encode((String)((HashMap)localObject5).get(localObject6), "UTF-8"));
        ((StringBuffer)localObject4).append(((StringBuilder)localObject7).toString());
        break label1126;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append(((StringBuffer)localObject4).toString());
        localObject1 = ((StringBuilder)localObject1).toString();
        this.a.loadUrl((String)localObject1);
        this.a.setVisibility(0);
        break label1091;
        localObject1 = (ImageView)localB.findViewById(R.id.ads_plugin_native_ad_media);
        Object localObject5 = (TextView)localB.findViewById(R.id.ads_plugin_native_ad_title);
        localObject6 = (TextView)localB.findViewById(R.id.ads_plugin_native_ad_body);
        localObject2 = (ImageView)localB.findViewById(R.id.ads_plugin_native_ad_icon);
        localObject7 = localB.findViewById(R.id.ads_plugin_ll_header);
        if (paramInt != -2) {
          ((View)localObject7).setVisibility(8);
        } else {
          ((View)localObject7).setVisibility(0);
        }
        ((TextView)localObject5).setText(((a.t)localObject4).i);
        ((TextView)localObject6).setText(((a.t)localObject4).j);
        localObject5 = localB.findViewById(R.id.ads_plugin_pb);
        localB.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            AdAppHelper.getInstance(g.a(g.this)).logEvent("ADSDK_广告位_点击", "RECOMMEND_NATIVE", this.a.e);
            g.a(g.this, this.b, this.a.l);
            if (g.b(g.this) != null) {
              g.b(g.this).onAdClick(new AdType(12), j);
            }
          }
        });
        localObject6 = com.bestgo.adsplugin.utils.d.a(this.c);
        ((com.a.a.b.d)localObject6).a(((a.v)localObject3).g, (ImageView)localObject1, new com.a.a.b.f.a()
        {
          public void a(String paramAnonymousString, View paramAnonymousView) {}
          
          public void a(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
          {
            this.a.setVisibility(8);
          }
          
          public void a(String paramAnonymousString, View paramAnonymousView, com.a.a.b.a.b paramAnonymousB) {}
          
          public void b(String paramAnonymousString, View paramAnonymousView) {}
        });
        ((com.a.a.b.d)localObject6).a(((a.t)localObject4).f, (ImageView)localObject2);
        label1091:
        localB.a();
        return localB;
        j += 1;
      }
    }
    else
    {
      return null;
    }
  }
  
  private void b(String paramString, boolean paramBoolean)
  {
    int j = 1;
    try
    {
      this.c.getPackageManager().getApplicationInfo("com.android.vending", 0);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      ThrowableExtension.printStackTrace(localNameNotFoundException);
      j = 0;
    }
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      if ((j != 0) && (!paramBoolean)) {
        paramString.setPackage("com.android.vending");
      }
      paramString.setFlags(268435456);
      this.c.startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
    }
  }
  
  private b c(int paramInt)
  {
    try
    {
      Object localObject1 = this.i;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("taskId");
      ((StringBuilder)localObject2).append(paramInt);
      localObject1 = ((SharedPreferences)localObject1).getString(((StringBuilder)localObject2).toString(), "");
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = new JSONObject((String)localObject1);
        localObject2 = new b(null);
        ((b)localObject2).a = ((JSONObject)localObject1).getInt("taskId");
        ((b)localObject2).b = ((JSONObject)localObject1).getLong("initTime");
        ((b)localObject2).c = ((JSONObject)localObject1).getLong("lastShowTime");
        ((b)localObject2).d = ((JSONObject)localObject1).getLong("lastShowCount");
        return localObject2;
      }
      localObject1 = new b(null);
      ((b)localObject1).a = paramInt;
      ((b)localObject1).b = System.currentTimeMillis();
      ((b)localObject1).c = 0L;
      ((b)localObject1).d = 0L;
      return localObject1;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private void j()
  {
    this.a.setDrawingCacheBackgroundColor(-1);
    this.a.setFocusableInTouchMode(true);
    this.a.setFocusable(true);
    this.a.setDrawingCacheEnabled(false);
    this.a.setWillNotCacheDrawing(true);
    if (Build.VERSION.SDK_INT <= 22)
    {
      this.a.setAnimationCacheEnabled(false);
      this.a.setAlwaysDrawnWithCacheEnabled(false);
    }
    this.a.setBackgroundColor(-1);
    this.a.setScrollbarFadingEnabled(true);
    this.a.setSaveEnabled(true);
    this.a.setNetworkAvailable(true);
    this.a.setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
    this.b = this.a.getSettings().getUserAgentString();
    k();
  }
  
  private void k()
  {
    if (this.a == null) {
      return;
    }
    WebSettings localWebSettings = this.a.getSettings();
    if (h < 18) {
      localWebSettings.setAppCacheMaxSize(Long.MAX_VALUE);
    }
    if (h < 17) {
      localWebSettings.setEnableSmoothTransition(true);
    }
    if (h > 16) {
      localWebSettings.setMediaPlaybackRequiresUserGesture(true);
    }
    if (h >= 21) {
      localWebSettings.setMixedContentMode(2);
    }
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setAppCacheEnabled(true);
    localWebSettings.setCacheMode(-1);
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setDisplayZoomControls(false);
    localWebSettings.setAllowContentAccess(true);
    localWebSettings.setAllowFileAccess(true);
    localWebSettings.setGeolocationEnabled(true);
    if (h >= 16)
    {
      localWebSettings.setAllowFileAccessFromFileURLs(false);
      localWebSettings.setAllowUniversalAccessFromFileURLs(false);
    }
    if (h < 18) {
      localWebSettings.setSavePassword(true);
    }
    localWebSettings.setSaveFormData(true);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setSupportMultipleWindows(false);
  }
  
  public View a(int paramInt)
  {
    return b(paramInt);
  }
  
  public void a()
  {
    a localA = AdAppHelper.getInstance(this.c).getConfig();
    int j;
    if ((localA.t.a == 1) && (localA.t.c > 0)) {
      j = 1;
    } else {
      j = 0;
    }
    if (j == 0) {
      return;
    }
    if (localA.t.h != null)
    {
      if (localA.t.h.length == 0) {
        return;
      }
      com.a.a.b.d localD = com.bestgo.adsplugin.utils.d.a(this.c);
      j = 0;
      while (j < localA.t.h.length)
      {
        final a.v localV = localA.t.h[j];
        if ((localV != null) && ((!localV.c) || (System.currentTimeMillis() - localV.d >= AdAppHelper.MAX_REQEUST_TIME)) && (localV.a) && (!localV.b))
        {
          localV.c = true;
          localV.d = System.currentTimeMillis();
          localV.c = false;
          localD.a(localV.g, new com.a.a.b.f.a()
          {
            public void a(String paramAnonymousString, View paramAnonymousView) {}
            
            public void a(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
            {
              localV.c = false;
              localV.b = true;
            }
            
            public void a(String paramAnonymousString, View paramAnonymousView, com.a.a.b.a.b paramAnonymousB)
            {
              localV.c = false;
            }
            
            public void b(String paramAnonymousString, View paramAnonymousView)
            {
              localV.c = false;
            }
          });
        }
        j += 1;
      }
    }
  }
  
  public void a(Activity paramActivity, FrameLayout paramFrameLayout)
  {
    a localA = AdAppHelper.getInstance(this.c).getConfig();
    int j = localA.t.a;
    int k = 0;
    if ((j == 1) && (localA.t.d > 0)) {
      j = 1;
    } else {
      j = 0;
    }
    if (j == 0) {
      return;
    }
    if (localA.t.i != null)
    {
      if (localA.t.i.length == 0) {
        return;
      }
      j = k;
      a.k localK;
      if (localA.t.f == 1)
      {
        j = new Random().nextInt(localA.t.i.length);
        localK = localA.t.i[j];
        j = k;
        if (localK != null)
        {
          j = k;
          if (localK.a)
          {
            j = k;
            if (localK.c)
            {
              j = k;
              if (!localK.h)
              {
                if (this.g != null) {
                  this.g.b();
                }
                this.g = new com.bestgo.adsplugin.views.a(paramActivity, paramFrameLayout, localK);
                this.g.a();
                AdAppHelper.getInstance(this.c).logEvent("ADSDK_广告位_显示", "RECOMMEND_HOME", localK.b);
                paramActivity = c(localK.j);
                if (paramActivity == null) {
                  return;
                }
                paramActivity.c = System.currentTimeMillis();
                paramActivity.d += 1L;
                a(paramActivity);
                return;
              }
            }
          }
        }
      }
      while (j < localA.t.i.length)
      {
        localK = localA.t.i[j];
        if ((localK != null) && (localK.a) && (localK.c) && (!localK.h))
        {
          if (this.g != null) {
            this.g.b();
          }
          this.g = new com.bestgo.adsplugin.views.a(paramActivity, paramFrameLayout, localK);
          this.g.a();
          AdAppHelper.getInstance(this.c).logEvent("ADSDK_广告位_显示", "RECOMMEND_HOME", localK.b);
          paramActivity = c(localK.j);
          if (paramActivity == null) {
            break;
          }
          paramActivity.c = System.currentTimeMillis();
          paramActivity.d += 1L;
          a(paramActivity);
          return;
        }
        j += 1;
      }
    }
  }
  
  public void a(Context paramContext)
  {
    this.c = paramContext;
    this.d = new ArrayList();
    this.e = this.c.getResources().getDisplayMetrics().density;
    this.i = paramContext.getSharedPreferences("home_recommend", 0);
  }
  
  public void a(AdStateListener paramAdStateListener)
  {
    this.f = paramAdStateListener;
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    this.g.packageChanged(paramString, paramBoolean);
  }
  
  public void b()
  {
    a localA = AdAppHelper.getInstance(this.c).getConfig();
    int j;
    if ((localA.t.a == 1) && (localA.t.b > 0)) {
      j = 1;
    } else {
      j = 0;
    }
    if (j == 0) {
      return;
    }
    if (localA.t.g != null)
    {
      if (localA.t.g.length == 0) {
        return;
      }
      j = 0;
      while (j < localA.t.g.length)
      {
        a.t localT = localA.t.g[j];
        if ((localT != null) && ((!localT.c) || (System.currentTimeMillis() - localT.d >= AdAppHelper.MAX_REQEUST_TIME)) && (localT.a) && (!localT.b))
        {
          localT.c = true;
          localT.d = System.currentTimeMillis();
          localT.c = false;
          a(localT);
        }
        j += 1;
      }
    }
  }
  
  public void c()
  {
    a localA = AdAppHelper.getInstance(this.c).getConfig();
    int m = localA.t.a;
    int k = 0;
    int j = 1;
    if ((m != 1) || (localA.t.d <= 0)) {
      j = 0;
    }
    if (j == 0) {
      return;
    }
    if (localA.t.i != null)
    {
      if (localA.t.i.length == 0) {
        return;
      }
      com.a.a.b.d localD = com.bestgo.adsplugin.utils.d.a(this.c);
      j = k;
      while (j < localA.t.i.length)
      {
        final a.k localK = localA.t.i[j];
        if ((localK != null) && (localK.a) && ((!localK.c) || (!localK.d)))
        {
          if (!localK.c) {
            localD.a(localK.e, new com.a.a.b.f.a()
            {
              public void a(String paramAnonymousString, View paramAnonymousView) {}
              
              public void a(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
              {
                localK.c = true;
              }
              
              public void a(String paramAnonymousString, View paramAnonymousView, com.a.a.b.a.b paramAnonymousB) {}
              
              public void b(String paramAnonymousString, View paramAnonymousView) {}
            });
          }
          if (!localK.d) {
            localD.a(localK.f, new com.a.a.b.f.a()
            {
              public void a(String paramAnonymousString, View paramAnonymousView) {}
              
              public void a(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
              {
                localK.d = true;
              }
              
              public void a(String paramAnonymousString, View paramAnonymousView, com.a.a.b.a.b paramAnonymousB) {}
              
              public void b(String paramAnonymousString, View paramAnonymousView) {}
            });
          }
        }
        j += 1;
      }
    }
  }
  
  public boolean d()
  {
    if (!AdAppHelper.getInstance(this.c).isNetworkConnected(this.c)) {
      return false;
    }
    a localA = AdAppHelper.getInstance(this.c).getConfig();
    int j = localA.t.a;
    boolean bool = true;
    if ((j != 1) || (localA.t.b <= 0)) {
      bool = false;
    }
    if (!bool) {
      return bool;
    }
    if (System.currentTimeMillis() - AdAppHelper.INIT_TIME < localA.r.m) {
      return false;
    }
    if (localA.t.g != null)
    {
      if (localA.t.g.length == 0) {
        return false;
      }
      j = 0;
      while (j < localA.t.g.length)
      {
        a.t localT = localA.t.g[j];
        if (localT != null) {
          bool = localT.m;
        }
        j += 1;
      }
    }
    return false;
  }
  
  public void e()
  {
    Object localObject1 = AdAppHelper.getInstance(this.c).getConfig();
    int j = ((a)localObject1).t.a;
    int k = 0;
    if ((j == 1) && (((a)localObject1).t.b > 0)) {
      j = 1;
    } else {
      j = 0;
    }
    if (j == 0) {
      return;
    }
    if (((a)localObject1).t.g != null)
    {
      if (((a)localObject1).t.g.length == 0) {
        return;
      }
      j = k;
      Object localObject2;
      if (((a)localObject1).t.f == 1)
      {
        j = new Random().nextInt(((a)localObject1).t.g.length);
        localObject2 = localObject1.t.g[j];
        j = k;
        if (localObject2 != null)
        {
          j = k;
          if (((a.t)localObject2).a)
          {
            j = k;
            if (((a.t)localObject2).b)
            {
              j = k;
              if (!((a.t)localObject2).m)
              {
                localObject1 = new Intent(this.c, RecommendAdActivity.class);
                RecommendAdActivity.a = (a.t)localObject2;
                ((Intent)localObject1).setFlags(268435456);
                localObject2 = this.c;
                ((Context)localObject2).startActivity((Intent)localObject1);
                return;
              }
            }
          }
        }
      }
      for (;;)
      {
        if (j >= ((a)localObject1).t.g.length) {
          return;
        }
        localObject2 = localObject1.t.g[j];
        if ((localObject2 != null) && (((a.t)localObject2).a) && (((a.t)localObject2).b) && (!((a.t)localObject2).m))
        {
          localObject1 = new Intent(this.c, RecommendAdActivity.class);
          RecommendAdActivity.a = (a.t)localObject2;
          ((Intent)localObject1).setFlags(268435456);
          localObject2 = this.c;
          break;
        }
        j += 1;
      }
    }
  }
  
  public boolean f()
  {
    a localA = AdAppHelper.getInstance(this.c).getConfig();
    boolean bool;
    if ((localA.t.a == 1) && (localA.t.d > 0)) {
      bool = true;
    } else {
      bool = false;
    }
    if (!bool) {
      return bool;
    }
    if ((localA.t.i != null) && (localA.t.i.length != 0))
    {
      int j = 0;
      while (j < localA.t.i.length)
      {
        a.k localK = localA.t.i[j];
        b localB = c(localK.j);
        if (localB != null)
        {
          if (System.currentTimeMillis() - localB.c > 86400000L)
          {
            localB.d = 0L;
            a(localB);
          }
          if ((localB.d < localK.l) && (localB.b + localK.k * 86400000L >= System.currentTimeMillis()) && (localK != null) && (!localK.h) && (localK.c) && (localK.d)) {
            return true;
          }
        }
        j += 1;
      }
    }
    return false;
  }
  
  public void g() {}
  
  public void h() {}
  
  public void i()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        List localList = g.a(g.this).getPackageManager().getInstalledApplications(128);
        if (localList != null)
        {
          int i = 0;
          while (i < localList.size())
          {
            AdAppHelper.getInstance(g.a(g.this)).updateRecommendPackageStates(((ApplicationInfo)localList.get(i)).packageName, true);
            i += 1;
          }
        }
      }
    }).start();
  }
  
  private class a
  {
    private a.v b;
    
    public a(a.v paramV)
    {
      this.b = paramV;
    }
    
    @JavascriptInterface
    public boolean checkPackage(String paramString)
    {
      try
      {
        g.a(g.this).getPackageManager().getApplicationInfo(paramString, 0);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        ThrowableExtension.printStackTrace(paramString);
      }
      return false;
    }
    
    @JavascriptInterface
    public void close() {}
    
    @JavascriptInterface
    public void onPageLoaded() {}
    
    @JavascriptInterface
    public void openLink(String paramString1, String paramString2)
    {
      try
      {
        paramString2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
        if (checkPackage(paramString1)) {
          paramString2.setPackage(paramString1);
        }
        paramString2.setFlags(268435456);
        g.a(g.this).startActivity(paramString2);
        return;
      }
      catch (Exception paramString1)
      {
        ThrowableExtension.printStackTrace(paramString1);
      }
    }
  }
  
  private class b
  {
    public int a;
    public long b;
    public long c;
    public long d;
    
    private b() {}
    
    public String toString()
    {
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("taskId", this.a);
        localJSONObject.put("initTime", this.b);
        localJSONObject.put("lastShowTime", this.c);
        localJSONObject.put("lastShowCount", this.d);
        return localJSONObject.toString();
        return "";
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
}
