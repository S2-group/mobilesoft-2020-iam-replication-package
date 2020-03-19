package com.cyou.cma.recommend;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.cyou.cma.ar;
import com.cyou.cma.clauncher.CmaActivity;
import java.util.List;

public class RecommendAppActivity
  extends CmaActivity
{
  private String a = null;
  private Handler b = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      do
      {
        do
        {
          do
          {
            return;
          } while (RecommendAppActivity.this.isFinishing());
          Bundle localBundle = new Bundle();
          localBundle.putParcelable("data", (RecommendAppActivity.RecommendData)paramAnonymousMessage.obj);
          RecommendAppActivity.this.showDialog(1, localBundle);
          return;
        } while (RecommendAppActivity.this.isFinishing());
        RecommendAppActivity.this.showDialog(2, null);
        return;
      } while (RecommendAppActivity.this.isFinishing());
      RecommendAppActivity.this.showDialog(3, null);
    }
  };
  
  public RecommendAppActivity() {}
  
  private boolean a()
  {
    boolean bool2 = false;
    List localList = getPackageManager().getInstalledPackages(0);
    boolean bool1 = bool2;
    int i;
    if (localList != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < localList.size())
      {
        if ("com.cynad.cma.locker".equals(((PackageInfo)localList.get(i)).packageName)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    int k = 0;
    super.onCreate(paramBundle);
    paramBundle = getIntent().getStringExtra("recommend_app");
    if ("Mobogenie".equalsIgnoreCase(paramBundle))
    {
      paramBundle = getPackageManager().getInstalledPackages(0);
      j = k;
      if (paramBundle != null)
      {
        i = 0;
        j = k;
        if (i < paramBundle.size())
        {
          if (!"top.com.mobogenie.free".equals(((PackageInfo)paramBundle.get(i)).packageName)) {
            break label103;
          }
          j = 1;
        }
      }
      if (j == 0) {}
    }
    label103:
    while (!"PopLocker".equals(paramBundle)) {
      for (;;)
      {
        try
        {
          int j;
          int i;
          startActivity(getPackageManager().getLaunchIntentForPackage("top.com.mobogenie.free"));
          finish();
          return;
          i += 1;
        }
        catch (Exception paramBundle)
        {
          paramBundle.printStackTrace();
          continue;
        }
        try
        {
          paramBundle = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=top.com.mobogenie.free&referrer=utm_source%3Dclauncher"));
          paramBundle.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
          paramBundle.setFlags(268435456);
          startActivity(paramBundle);
        }
        catch (Exception paramBundle)
        {
          paramBundle.printStackTrace();
          try
          {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=top.com.mobogenie.free&referrer=utm_source%3Dclauncher")));
          }
          catch (Exception paramBundle)
          {
            ar.a(this, 2131165668, 2000);
            paramBundle.printStackTrace();
          }
        }
      }
    }
    if (a()) {}
    for (;;)
    {
      try
      {
        startActivity(getPackageManager().getLaunchIntentForPackage("com.cynad.cma.locker"));
        finish();
        return;
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
        continue;
      }
      try
      {
        paramBundle = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.cynad.cma.locker&referrer=utm_source%3Dclauncher"));
        paramBundle.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
        paramBundle.setFlags(268435456);
        startActivity(paramBundle);
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
        try
        {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.cynad.cma.locker&referrer=utm_source%3Dclauncher")));
        }
        catch (Exception paramBundle)
        {
          ar.a(this, 2131165668, 2000);
          paramBundle.printStackTrace();
        }
      }
    }
  }
  
  protected void onDestroy()
  {
    this.b.removeMessages(3);
    this.b.removeMessages(1);
    this.b.removeMessages(2);
    super.onDestroy();
  }
  
  protected void onStop()
  {
    super.onStop();
    finish();
  }
}
