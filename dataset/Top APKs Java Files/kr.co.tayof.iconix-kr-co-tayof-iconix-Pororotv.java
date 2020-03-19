package kr.co.tayof.iconix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import java.util.List;
import kr.co.tayof.iconix.common.MZLandingUtils;

public class Pororotv
  extends Activity
{
  private static boolean appRunning = false;
  ImageView exit;
  WebView webnotice;
  
  public Pororotv() {}
  
  private boolean checkNotification()
  {
    Log.i("push", "check notification");
    try
    {
      String str1 = getIntent().getStringExtra("pushid");
      if ((str1 != null) && (str1.length() > 0))
      {
        Log.i("push", "push id not null");
        String str2 = getIntent().getStringExtra("lnktype");
        String str3 = getIntent().getStringExtra("lnkinfo");
        new MZLandingUtils().startPushAction(this, str2, str3, str1);
        return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isAppRunning()
  {
    return appRunning;
  }
  
  private boolean isPororoTv(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int j = paramContext.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(i)).packageName.indexOf(paramString) != -1) {
        return true;
      }
      i += 1;
    }
  }
  
  private Intent makeScheme(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString3 == null) || (paramString2 == null) || (paramString1 == null)) {
      return null;
    }
    paramString1 = String.format("%s://%s", new Object[] { paramString1, paramString2 });
    paramString2 = "market://details?id=" + paramString3;
    if (isPororoTv(this, paramString3)) {}
    for (paramString1 = Uri.parse(paramString1);; paramString1 = Uri.parse(paramString2)) {
      return new Intent("android.intent.action.VIEW", paramString1);
    }
  }
  
  private boolean pororoUrl(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    Log.i("asdwww", "urlstr  " + paramString);
    if (paramString.startsWith("intent://"))
    {
      Object localObject2 = null;
      Object localObject3 = "";
      Object localObject1 = null;
      String[] arrayOfString = paramString.split(";");
      int j = arrayOfString.length;
      int i = 0;
      paramString = (String)localObject3;
      if (i >= j)
      {
        paramString = makeScheme(paramString, (String)localObject2, (String)localObject1);
        if (paramString == null) {
          return false;
        }
      }
      else
      {
        Object localObject5 = arrayOfString[i];
        String str;
        Object localObject4;
        if (((String)localObject5).startsWith("intent"))
        {
          localObject3 = localObject5.split("#")[0].replaceAll("intent://", "");
          str = paramString;
          localObject4 = localObject1;
        }
        for (;;)
        {
          i += 1;
          localObject2 = localObject3;
          localObject1 = localObject4;
          paramString = str;
          break;
          if (((String)localObject5).startsWith("scheme"))
          {
            localObject5 = ((String)localObject5).split("=");
            localObject3 = localObject2;
            localObject4 = localObject1;
            str = paramString;
            if (localObject5.length > 1)
            {
              str = localObject5[1];
              localObject3 = localObject2;
              localObject4 = localObject1;
            }
          }
          else
          {
            localObject3 = localObject2;
            localObject4 = localObject1;
            str = paramString;
            if (((String)localObject5).startsWith("package"))
            {
              localObject5 = ((String)localObject5).split("=");
              localObject3 = localObject2;
              localObject4 = localObject1;
              str = paramString;
              if (localObject5.length > 1)
              {
                localObject4 = localObject5[1];
                localObject3 = localObject2;
                str = paramString;
              }
            }
          }
        }
      }
      startActivity(paramString);
      return true;
    }
    if (paramString.startsWith("iconix://"))
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    if (paramString.startsWith("tstore://"))
    {
      if (isPororoTv(this, "kr.co.iconix.pororotv"))
      {
        Log.i("asdwww", "1");
        startActivity(getPackageManager().getLaunchIntentForPackage("kr.co.iconix.pororotv"));
      }
      for (;;)
      {
        return true;
        Log.i("asdwww", "2");
        paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        paramString.addFlags(536870912);
        startActivity(paramString);
      }
    }
    return false;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    appRunning = true;
    setContentView(2130903050);
    this.exit = ((ImageView)findViewById(2131361882));
    this.webnotice = ((WebView)findViewById(2131361883));
    paramBundle = this.webnotice.getSettings();
    StringBuffer localStringBuffer = new StringBuffer(paramBundle.getUserAgentString());
    localStringBuffer.append(";pororoApp");
    paramBundle.setUserAgentString(localStringBuffer.toString());
    this.webnotice.getSettings().setJavaScriptEnabled(true);
    this.webnotice.loadUrl("http://api.pororohome.com/event/transfer?appId=kr.co.tayof.iconix&market=3");
    this.webnotice.setWebViewClient(new WebViewClientClass(null));
    this.exit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Pororotv.this.finish();
        System.gc();
      }
    });
  }
  
  public void onDestroy()
  {
    System.gc();
    appRunning = false;
    super.onDestroy();
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    checkNotification();
  }
  
  private class WebViewClientClass
    extends WebViewClient
  {
    private WebViewClientClass() {}
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Log.i("asdwww", "url   " + paramString);
      if (Pororotv.this.pororoUrl(paramString)) {
        return true;
      }
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }
  }
}
