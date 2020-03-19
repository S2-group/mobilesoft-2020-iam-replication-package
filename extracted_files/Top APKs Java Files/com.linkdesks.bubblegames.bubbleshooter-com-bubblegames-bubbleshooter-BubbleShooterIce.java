package com.bubblegames.bubbleshooter;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.a.a;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.game.UMGameAgent;
import com.umeng.commonsdk.UMConfigure;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

public class BubbleShooterIce
  extends Cocos2dxActivity
{
  private ProgressDialog a = null;
  
  static
  {
    System.loadLibrary("cocos2dcpp");
  }
  
  public BubbleShooterIce() {}
  
  public static BubbleShooterIce a()
  {
    return (BubbleShooterIce)getContext();
  }
  
  public static boolean a(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    try
    {
      List localList = getContext().getPackageManager().getInstalledPackages(0);
      if (localList != null)
      {
        int i = 0;
        while (i < localList.size())
        {
          boolean bool = ((PackageInfo)localList.get(i)).packageName.equals(paramString);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int h()
  {
    BubbleShooterIce localBubbleShooterIce = a();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      if (Build.VERSION.SDK_INT >= 17) {
        localBubbleShooterIce.getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
      } else {
        localBubbleShooterIce.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      }
      i = localDisplayMetrics.heightPixels;
      return i;
    }
    catch (Exception localException2)
    {
      int i;
      label76:
      for (;;) {}
    }
    try
    {
      localBubbleShooterIce.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      i = localDisplayMetrics.heightPixels;
      return i;
    }
    catch (Exception localException1)
    {
      break label76;
    }
    return 0;
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    new a.a(a()).a(paramString1).b(paramString2).a(paramString3, null).b().show();
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString3);
      ((StringBuilder)localObject).append("\nPhone: ");
      ((StringBuilder)localObject).append(Build.MODEL);
      ((StringBuilder)localObject).append("\nOS Version: ");
      ((StringBuilder)localObject).append(Build.VERSION.SDK_INT);
      ((StringBuilder)localObject).append(",");
      ((StringBuilder)localObject).append(Build.VERSION.RELEASE);
      paramString3 = ((StringBuilder)localObject).toString();
      localObject = new Intent("android.intent.action.SEND");
      ((Intent)localObject).setType("plain/text");
      ((Intent)localObject).putExtra("android.intent.extra.EMAIL", new String[] { paramString1 });
      paramString1 = new StringBuilder();
      paramString1.append(paramString3);
      ((Intent)localObject).putExtra("android.intent.extra.TEXT", paramString1.toString());
      ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", paramString2);
      startActivity(Intent.createChooser((Intent)localObject, paramString4));
      return;
    }
    catch (Exception paramString1) {}
  }
  
  public void a(boolean paramBoolean)
  {
    try
    {
      final View localView = getWindow().getDecorView();
      int i = 2;
      if (Build.VERSION.SDK_INT >= 16) {
        i = 1798;
      }
      final int j = i;
      if (Build.VERSION.SDK_INT >= 19) {
        j = i | 0x1000;
      }
      localView.setSystemUiVisibility(j);
      if (paramBoolean) {
        localView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {
          public void onSystemUiVisibilityChange(int paramAnonymousInt)
          {
            if ((paramAnonymousInt & 0x4) == 0) {
              localView.setSystemUiVisibility(j);
            }
          }
        });
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public void b()
  {
    if (this.a != null)
    {
      this.a.dismiss();
      this.a = null;
    }
  }
  
  public void b(String paramString)
  {
    if (this.a != null) {
      return;
    }
    this.a = new ProgressDialog(this);
    this.a.setCancelable(false);
    this.a.setMessage(paramString);
    this.a.show();
  }
  
  public void c()
  {
    try
    {
      UMGameAgent.onKillProcess(this);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void c(String paramString) {}
  
  public String d()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0).applicationInfo.loadLabel(getPackageManager()).toString();
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public void d(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
    LDJniHelper.didSaveImageToAlbum();
  }
  
  public String e()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public void e(String paramString)
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    catch (Exception paramString) {}
  }
  
  public void f()
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(a().getPackageName());
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("http://play.google.com/store/apps/details?id=");
          localStringBuilder.append(a().getPackageName());
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
          return;
        }
        catch (Exception localException2) {}
        localException1 = localException1;
      }
    }
  }
  
  public void f(String paramString)
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    catch (Exception paramString) {}
  }
  
  public void g()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.linkdesks.com/android/More.htm")));
      return;
    }
    catch (Exception localException) {}
  }
  
  public void g(String paramString)
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(paramString);
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("http://play.google.com/store/apps/details?id=");
          localStringBuilder.append(paramString);
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
          return;
        }
        catch (Exception paramString) {}
        localException = localException;
      }
    }
  }
  
  public String getPackageName()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0).packageName;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    e.a(paramInt1, paramInt2, paramIntent);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    if (a.b()) {
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (this.m_activityIgnored) {
      return;
    }
    try
    {
      UMConfigure.init(this, LDJniHelper.getUmengAppID(), LDJniUmengHelper.getLanguageChannel(), 1, null);
      UMGameAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_GAME);
      UMGameAgent.init(this);
      a.a(this);
      f.a().b();
      a(true);
      return;
    }
    catch (Exception paramBundle)
    {
      for (;;) {}
    }
  }
  
  public Cocos2dxGLSurfaceView onCreateView()
  {
    Cocos2dxGLSurfaceView localCocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
    localCocos2dxGLSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);
    return localCocos2dxGLSurfaceView;
  }
  
  public void onDestroy()
  {
    if (this.m_activityIgnored)
    {
      super.onDestroy();
      return;
    }
    a.d(this);
    d.a().d();
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.m_activityIgnored) {
      return;
    }
    try
    {
      UMGameAgent.onPause(this);
      a.c(this);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.m_activityIgnored) {
      return;
    }
    try
    {
      UMGameAgent.onResume(this);
      a.b(this);
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          if ((getCurrentFocus() != Cocos2dxGLSurfaceView.getInstance()) && (Cocos2dxGLSurfaceView.getInstance() != null)) {
            Cocos2dxGLSurfaceView.getInstance().requestFocus();
          }
          return;
        }
        catch (Exception localException2) {}
        localException1 = localException1;
      }
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean) {
      a(false);
    }
  }
}
