package kr.co.sing.pororof;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import java.util.List;

public class Notice
  extends Activity
{
  public static final String PREFERENCE_FILENAME1 = "ICONIXINFO";
  CheckBox check;
  ImageView exit;
  SharedPreferences setting1 = null;
  WebView webnotice;
  
  public Notice() {}
  
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
    if (paramString == null) {}
    label245:
    do
    {
      Object localObject2;
      Object localObject3;
      Object localObject1;
      String[] arrayOfString;
      int i;
      do
      {
        return false;
        if (!paramString.startsWith("intent://")) {
          break label245;
        }
        localObject2 = null;
        localObject3 = "";
        localObject1 = null;
        arrayOfString = paramString.split(";");
        int j = arrayOfString.length;
        i = 0;
        paramString = (String)localObject3;
        if (i < j) {
          break;
        }
        paramString = makeScheme(paramString, (String)localObject2, (String)localObject1);
      } while (paramString == null);
      startActivity(paramString);
      return true;
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
    } while (!paramString.startsWith("iconix://"));
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
    return true;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903049);
    this.setting1 = getSharedPreferences("ICONIXINFO", 0);
    paramBundle = getIntent().getStringExtra("trueurl");
    Log.i("asdwww", "     url       " + paramBundle);
    getWindowManager().getDefaultDisplay();
    this.webnotice = ((WebView)findViewById(2131361880));
    this.exit = ((ImageView)findViewById(2131361882));
    this.check = ((CheckBox)findViewById(2131361881));
    this.webnotice.getSettings().setJavaScriptEnabled(true);
    this.webnotice.loadUrl(paramBundle);
    this.webnotice.setWebViewClient(new WebViewClientClass(null));
    this.exit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Notice.this.finish();
        System.gc();
      }
    });
  }
  
  public void onDestroy()
  {
    System.gc();
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  public void onStop()
  {
    super.onStop();
    if (this.check.isChecked())
    {
      SharedPreferences.Editor localEditor = this.setting1.edit();
      localEditor.putString("nowday", PororoIntro.nowday());
      localEditor.commit();
    }
    Log.i("asdwww", "check11    " + this.check.isChecked());
  }
  
  private class WebViewClientClass
    extends WebViewClient
  {
    private WebViewClientClass() {}
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Log.i("asdwww", "url   " + paramString);
      if (Notice.this.pororoUrl(paramString)) {
        return true;
      }
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }
  }
}
