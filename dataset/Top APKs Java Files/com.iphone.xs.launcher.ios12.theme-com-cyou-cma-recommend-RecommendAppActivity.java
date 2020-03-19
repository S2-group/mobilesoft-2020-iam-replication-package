package com.cyou.cma.recommend;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.cyou.cma.clauncher.CmaActivity;
import com.cyou.cma.ʼˉ;
import java.util.List;

public class RecommendAppActivity
  extends CmaActivity
{
  private String ʻ = null;
  private Handler ʼ = new ˑ(this);
  
  public RecommendAppActivity() {}
  
  private boolean ʻ()
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
            ʼˉ.ʻ(this, 2131165599, 2000);
            paramBundle.printStackTrace();
          }
        }
      }
    }
    if (ʻ()) {}
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
          ʼˉ.ʻ(this, 2131165599, 2000);
          paramBundle.printStackTrace();
        }
      }
    }
  }
  
  protected void onDestroy()
  {
    this.ʼ.removeMessages(3);
    this.ʼ.removeMessages(1);
    this.ʼ.removeMessages(2);
    super.onDestroy();
  }
  
  protected void onStop()
  {
    super.onStop();
    finish();
  }
}
