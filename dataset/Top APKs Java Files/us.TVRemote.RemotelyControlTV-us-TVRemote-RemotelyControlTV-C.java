package us.TVRemote.RemotelyControlTV;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import com.appnext.ads.interstitial.Interstitial;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.ads.splash.SplashConfig.Theme;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.unity3d.ads.UnityAds;
import java.util.Iterator;
import java.util.List;

public class C
  extends Activity
  implements View.OnClickListener
{
  private String a = "";
  private ImageView a2;
  private Context c = this;
  private boolean clicked = true;
  private Interstitial interstitial_Ad;
  public boolean isOnline = false;
  private SharedPreferences prefs;
  
  public C() {}
  
  private void ads()
  {
    if (System.currentTimeMillis() > 1539853200000L)
    {
      if (Build.VERSION.SDK_INT < 14) {
        break label60;
      }
      if (this.interstitial_Ad.isAdLoaded())
      {
        this.interstitial_Ad.showAd();
        this.interstitial_Ad.loadAd();
      }
    }
    else
    {
      return;
    }
    if (UnityAds.isReady())
    {
      UnityAds.show(this);
      return;
    }
    StartAppAd.showAd(this);
    return;
    label60:
    if (UnityAds.isReady())
    {
      UnityAds.show(this);
      return;
    }
    StartAppAd.showAd(this);
  }
  
  public boolean isPackageExisted(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    return true;
  }
  
  public void onBackPressed()
  {
    if (System.currentTimeMillis() > 1539853200000L) {
      StartAppAd.onBackPressed(this);
    }
    try
    {
      getPackageManager().setComponentEnabledSetting(new ComponentName(this, A.class), 2, 1);
      super.onBackPressed();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void onClick(View paramView)
  {
    boolean bool = true;
    this.clicked = this.prefs.getBoolean("clicked", true);
    paramView = this.prefs.edit();
    if (this.clicked) {
      bool = false;
    }
    paramView.putBoolean("clicked", bool).commit();
    if (this.clicked)
    {
      ads();
      return;
    }
    if (this.isOnline)
    {
      new AlertDialog.Builder(this).setTitle("New version").setMessage("Download tool which supports your TV model!").setPositiveButton(17039379, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          try
          {
            paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
            paramAnonymousDialogInterface.setFlags(268435456);
            paramAnonymousDialogInterface.setData(Uri.parse("market://details?id=" + C.this.a));
            C.this.c.startActivity(paramAnonymousDialogInterface);
            return;
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            C.this.ads();
          }
        }
      }).setIcon(17301543).show();
      return;
    }
    ads();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    StartAppSDK.init(this, "203929839", true);
    if (System.currentTimeMillis() > 1539853200000L) {
      StartAppAd.showSplash(this, paramBundle, new SplashConfig().setTheme(SplashConfig.Theme.BLAZE));
    }
    StartAppAd.disableSplash();
    UnityAds.initialize(this, "1781742", null);
    if (Build.VERSION.SDK_INT >= 14)
    {
      this.interstitial_Ad = new Interstitial(this, "1f1d131f-636b-42fd-80b4-15a0d5d9e93c");
      this.interstitial_Ad.loadAd();
    }
    setContentView(2130903076);
    this.prefs = getSharedPreferences(getPackageName(), 0);
    if (!this.prefs.getBoolean("rate", false)) {
      new AlertDialog.Builder(this).setTitle("Request").setMessage("Please rate our app").setPositiveButton(17039379, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          try
          {
            paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
            paramAnonymousDialogInterface.setFlags(268435456);
            paramAnonymousDialogInterface.setData(Uri.parse("market://details?id=" + C.this.c.getPackageName()));
            C.this.c.startActivity(paramAnonymousDialogInterface);
            C.this.prefs.edit().putBoolean("rate", true).commit();
            return;
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            for (;;)
            {
              C.this.ads();
            }
          }
        }
      }).setNegativeButton(17039369, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          C.this.ads();
        }
      }).setIcon(17301543).show();
    }
    if ("us.TVRemote.RemotelyControlTV".equals(getPackageName())) {}
    for (this.a = "us.TVRemote.RemotelyControlTV2";; this.a = "us.TVRemote.RemotelyControlTV")
    {
      if (!isPackageExisted(this.a)) {
        new dlTask(null).execute(new String[] { "https://play.google.com/store/apps/details?id=" + this.a });
      }
      this.a2 = ((ImageView)findViewById(2131230842));
      this.a2.setOnClickListener(this);
      return;
    }
  }
  
  protected void onDestroy()
  {
    try
    {
      getPackageManager().setComponentEnabledSetting(new ComponentName(this, A.class), 2, 1);
      super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private class dlTask
    extends AsyncTask<String, Void, Boolean>
  {
    private int response = 0;
    
    private dlTask() {}
    
    /* Error */
    protected Boolean doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 16	us/TVRemote/RemotelyControlTV/C$dlTask:this$0	Lus/TVRemote/RemotelyControlTV/C;
      //   4: ldc 33
      //   6: invokevirtual 37	us/TVRemote/RemotelyControlTV/C:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   9: checkcast 39	android/net/ConnectivityManager
      //   12: invokevirtual 43	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
      //   15: astore_2
      //   16: aload_2
      //   17: ifnull +124 -> 141
      //   20: aload_2
      //   21: invokevirtual 49	android/net/NetworkInfo:isConnected	()Z
      //   24: ifeq +117 -> 141
      //   27: new 51	java/net/URL
      //   30: dup
      //   31: aload_1
      //   32: iconst_0
      //   33: aaload
      //   34: invokespecial 54	java/net/URL:<init>	(Ljava/lang/String;)V
      //   37: invokevirtual 58	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   40: checkcast 60	java/net/HttpURLConnection
      //   43: astore_1
      //   44: aload_1
      //   45: sipush 10000
      //   48: invokevirtual 64	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   51: aload_1
      //   52: sipush 15000
      //   55: invokevirtual 67	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   58: aload_1
      //   59: ldc 69
      //   61: invokevirtual 72	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   64: aload_1
      //   65: iconst_1
      //   66: invokevirtual 76	java/net/HttpURLConnection:setDoInput	(Z)V
      //   69: aload_1
      //   70: invokevirtual 79	java/net/HttpURLConnection:connect	()V
      //   73: aload_0
      //   74: aload_1
      //   75: invokevirtual 83	java/net/HttpURLConnection:getResponseCode	()I
      //   78: putfield 21	us/TVRemote/RemotelyControlTV/C$dlTask:response	I
      //   81: aload_1
      //   82: invokevirtual 87	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   85: astore_1
      //   86: aload_1
      //   87: ifnull +7 -> 94
      //   90: aload_1
      //   91: invokevirtual 92	java/io/InputStream:close	()V
      //   94: aload_0
      //   95: getfield 21	us/TVRemote/RemotelyControlTV/C$dlTask:response	I
      //   98: sipush 200
      //   101: if_icmpne +45 -> 146
      //   104: iconst_1
      //   105: invokestatic 98	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   108: areturn
      //   109: astore_1
      //   110: iconst_0
      //   111: ifeq -17 -> 94
      //   114: new 100	java/lang/NullPointerException
      //   117: dup
      //   118: invokespecial 101	java/lang/NullPointerException:<init>	()V
      //   121: athrow
      //   122: astore_1
      //   123: goto -29 -> 94
      //   126: astore_2
      //   127: iconst_0
      //   128: ifeq +11 -> 139
      //   131: new 100	java/lang/NullPointerException
      //   134: dup
      //   135: invokespecial 101	java/lang/NullPointerException:<init>	()V
      //   138: athrow
      //   139: aload_2
      //   140: athrow
      //   141: iconst_0
      //   142: invokestatic 98	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   145: areturn
      //   146: iconst_0
      //   147: invokestatic 98	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   150: areturn
      //   151: astore_1
      //   152: goto -13 -> 139
      //   155: astore_1
      //   156: goto -62 -> 94
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	159	0	this	dlTask
      //   0	159	1	paramVarArgs	String[]
      //   15	6	2	localNetworkInfo	android.net.NetworkInfo
      //   126	14	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   27	86	109	java/lang/Exception
      //   114	122	122	java/io/IOException
      //   27	86	126	finally
      //   131	139	151	java/io/IOException
      //   90	94	155	java/io/IOException
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue()) {
        C.this.isOnline = true;
      }
    }
  }
}
