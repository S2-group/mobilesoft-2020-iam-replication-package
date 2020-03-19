package kr.co.sbs.lib.iaweb;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.ProgressBar;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kr.co.sbs.lib.iaweb.listener.IAWOpenFileChooser;
import kr.co.sbs.lib.iaweb.listener.IAWPageLoadListener;
import kr.co.sbs.lib.iaweb.listener.IAWProgressListener;
import kr.co.sbs.lib.iaweb.listener.IAWShowCustomViewListener;
import kr.co.sbs.lib.iaweb.listener.IAWUriHandler;
import kr.co.sbs.lib.iaweb.listener.a;

public class IAWebView
  extends WebView
{
  private e a;
  private d b;
  private String c;
  private boolean d;
  
  public IAWebView(Context paramContext)
  {
    super(paramContext);
    c();
  }
  
  public IAWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    c();
  }
  
  public IAWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    c();
  }
  
  @TargetApi(21)
  public IAWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    c();
  }
  
  private void c()
  {
    boolean bool = true;
    WebSettings localWebSettings = getSettings();
    this.c = localWebSettings.getUserAgentString();
    localWebSettings.setJavaScriptEnabled(true);
    if (Build.VERSION.SDK_INT > 16) {
      localWebSettings.setAllowFileAccessFromFileURLs(true);
    }
    localWebSettings.setAppCacheEnabled(true);
    localWebSettings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
    localWebSettings.setSupportZoom(true);
    localWebSettings.setBuiltInZoomControls(true);
    if (Build.VERSION.SDK_INT >= 11) {
      localWebSettings.setDisplayZoomControls(false);
    }
    localWebSettings.setCacheMode(-1);
    localWebSettings.setDomStorageEnabled(true);
    if (Build.VERSION.SDK_INT < 18) {
      localWebSettings.setLightTouchEnabled(true);
    }
    if (Build.VERSION.SDK_INT >= 21) {
      localWebSettings.setMixedContentMode(0);
    }
    localWebSettings.setUseWideViewPort(true);
    localWebSettings.setLoadWithOverviewMode(true);
    if (Build.VERSION.SDK_INT >= 14) {
      localWebSettings.setTextZoom(100);
    }
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.LOW);
    setVerticalScrollbarOverlay(true);
    this.b = new d(getContext());
    setWebChromeClient(this.b);
    this.a = new e(getContext(), this.b);
    setWebViewClient(this.a);
    try
    {
      if (Build.VERSION.SDK_INT >= 21) {
        if (f.b()) {
          break label221;
        }
      }
      for (;;)
      {
        setWebContentsDebuggingEnabled(bool);
        return;
        label221:
        bool = false;
      }
      return;
    }
    catch (NoSuchMethodError localNoSuchMethodError) {}
  }
  
  private String getAppVersion()
  {
    try
    {
      String str = getContext().getPackageName();
      Iterator localIterator = getContext().getPackageManager().getInstalledPackages(128).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localPackageInfo.packageName.equals(str))
        {
          str = localPackageInfo.versionName;
          return str;
        }
      }
    }
    catch (Exception localException) {}
    return "";
  }
  
  public final void a()
  {
    for (;;)
    {
      try
      {
        if (Build.VERSION.SDK_INT >= 11) {
          onPause();
        }
      }
      catch (Exception localException1)
      {
        f.a(localException1);
        return;
      }
      try
      {
        pauseTimers();
        return;
      }
      catch (Exception localException2)
      {
        f.a(localException2);
      }
      Class.forName("android.webkit.WebView").getMethod("onPause", null).invoke(this, null);
    }
  }
  
  public final void a(String paramString1, String paramString2)
  {
    try
    {
      if (TextUtils.isEmpty(paramString2))
      {
        loadUrl(paramString1);
        return;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("Referer", paramString2);
      loadUrl(paramString1, localHashMap);
      return;
    }
    catch (Exception paramString1)
    {
      f.a(paramString1);
    }
  }
  
  public final void a(String paramString, IAWUriHandler paramIAWUriHandler)
  {
    g localG = this.a.b;
    localG.b.remove(paramString);
    localG.b.put(paramString, paramIAWUriHandler);
  }
  
  public final void a(a paramA)
  {
    WebSettings localWebSettings = getSettings();
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setSupportMultipleWindows(true);
    this.b.b = paramA;
  }
  
  public final void a(kr.co.sbs.lib.iaweb.listener.b paramB1, kr.co.sbs.lib.iaweb.listener.b paramB2)
  {
    e localE = this.a;
    localE.c = paramB1;
    localE.d = paramB2;
  }
  
  public final void b()
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 11) {
        onResume();
      }
      for (;;)
      {
        try
        {
          resumeTimers();
          if (this.d) {
            reload();
          }
          return;
        }
        catch (Exception localException2)
        {
          f.a(localException2);
        }
        Class.forName("android.webkit.WebView").getMethod("onResume", null).invoke(this, null);
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        f.a(localException1);
      }
    }
  }
  
  public b getCapture()
  {
    Object localObject = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    Drawable localDrawable = getBackground();
    if (localDrawable != null) {
      localDrawable.draw(localCanvas);
    }
    for (;;)
    {
      localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
      return new b(getTitle(), getOriginalUrl(), (Drawable)localObject);
      localCanvas.drawColor(-1);
    }
  }
  
  public IAWOpenFileChooser getOpenFileChooser()
  {
    return this.b.c;
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    f.a(new Object[] { "## onWindowFocusChanged()" });
    f.a(new Object[] { "++ this : " + this });
    f.a(new Object[] { "++ hasWindowFocus : " + paramBoolean });
    super.onWindowFocusChanged(paramBoolean);
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    f.a(new Object[] { "## onWindowVisibilityChanged()" });
    f.a(new Object[] { "++ this : " + this });
    StringBuilder localStringBuilder = new StringBuilder("++ visibility : ");
    String str;
    switch (paramInt)
    {
    default: 
      str = "unknown";
    }
    for (;;)
    {
      f.a(new Object[] { str });
      if (paramInt == 0) {
        c.a().b = this;
      }
      super.onWindowVisibilityChanged(paramInt);
      return;
      str = "View.VISIBLE";
      continue;
      str = "View.INVISIBLE";
      continue;
      str = "View.GONE";
    }
  }
  
  public void setIntentHandler(IAWUriHandler paramIAWUriHandler)
  {
    this.a.a.a = paramIAWUriHandler;
  }
  
  public void setOpenFileChooser(IAWOpenFileChooser paramIAWOpenFileChooser)
  {
    this.b.c = paramIAWOpenFileChooser;
  }
  
  public void setPageLoadListener(IAWPageLoadListener paramIAWPageLoadListener)
  {
    this.a.f = paramIAWPageLoadListener;
  }
  
  public void setProgressBar(ProgressBar paramProgressBar)
  {
    this.a.e = paramProgressBar;
  }
  
  public void setProgressListener(IAWProgressListener paramIAWProgressListener)
  {
    d localD = this.b;
    localD.a = paramIAWProgressListener;
    localD.e = 0;
  }
  
  public void setShowCustomViewListener(IAWShowCustomViewListener paramIAWShowCustomViewListener)
  {
    this.b.d = paramIAWShowCustomViewListener;
  }
  
  public void setUserAgent(String paramString)
  {
    paramString = String.format("%s %s/%s", new Object[] { this.c, paramString, getAppVersion() });
    getSettings().setUserAgentString(paramString);
  }
}
