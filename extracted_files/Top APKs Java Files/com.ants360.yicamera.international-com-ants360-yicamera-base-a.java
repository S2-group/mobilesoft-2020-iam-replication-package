package com.ants360.yicamera.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import com.ants360.yicamera.activity.BaseActivity;
import com.ants360.yicamera.e.f;
import com.ants360.yicamera.fragment.SimpleDialogFragment;
import com.ants360.yicamera.util.u;
import java.util.List;

public class a
{
  private BaseActivity a;
  
  public a(BaseActivity paramBaseActivity)
  {
    this.a = paramBaseActivity;
  }
  
  public String a(String paramString)
  {
    return u.a().b(paramString);
  }
  
  public void a(float paramFloat, boolean paramBoolean)
  {
    WindowManager.LayoutParams localLayoutParams = this.a.getWindow().getAttributes();
    localLayoutParams.alpha = paramFloat;
    this.a.getWindow().setAttributes(localLayoutParams);
    if (paramBoolean)
    {
      this.a.getWindow().addFlags(2);
      return;
    }
    this.a.getWindow().clearFlags(2);
  }
  
  public void a(int paramInt)
  {
    a(paramInt, null);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, f paramF)
  {
    a(paramInt1, paramInt2, paramInt3, paramInt4, false, paramF);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, f paramF)
  {
    a(this.a.getString(paramInt1), this.a.getText(paramInt2), this.a.getString(paramInt3), this.a.getString(paramInt4), paramBoolean, paramF);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, f paramF)
  {
    a(paramInt1, paramInt2, paramInt3, false, paramF);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, f paramF)
  {
    a(this.a.getText(paramInt1), this.a.getString(paramInt2), this.a.getString(paramInt3), paramBoolean, paramF);
  }
  
  public void a(int paramInt1, int paramInt2, f paramF)
  {
    a(paramInt1, paramInt2, false, paramF);
  }
  
  public void a(int paramInt1, int paramInt2, boolean paramBoolean, f paramF)
  {
    a(null, this.a.getText(paramInt1), 0, this.a.getString(paramInt2), paramBoolean, paramF);
  }
  
  public void a(int paramInt, f paramF)
  {
    a(paramInt, false, paramF);
  }
  
  public void a(int paramInt, boolean paramBoolean, f paramF)
  {
    a(null, this.a.getText(paramInt), 0, null, paramBoolean, paramF);
  }
  
  public void a(View paramView, int paramInt1, int paramInt2, f paramF)
  {
    a(paramView, paramInt1, paramInt2, false, paramF);
  }
  
  public void a(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, f paramF)
  {
    a(paramView, this.a.getString(paramInt1), this.a.getString(paramInt2), paramBoolean, paramF);
  }
  
  public void a(View paramView1, View paramView2, View paramView3, f paramF)
  {
    a(paramView1, paramView2, paramView3, true, false, false, null, paramF);
  }
  
  public void a(View paramView1, View paramView2, View paramView3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, f paramF1, f paramF2)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramF1).d(paramBoolean3).a(paramView1).b(paramView2).c(paramView3).c(paramF2).a(paramBoolean1).b(paramBoolean2).a(this.a.getSupportFragmentManager());
  }
  
  public void a(View paramView, String paramString1, String paramString2, boolean paramBoolean, f paramF)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramF).a(paramView).b(paramString1).c(paramString2).c(paramBoolean).a(this.a.getSupportFragmentManager());
  }
  
  public void a(View paramView, boolean paramBoolean, f paramF)
  {
    a(paramView, null, null, paramBoolean, false, false, paramF, null);
  }
  
  public void a(CharSequence paramCharSequence, f paramF)
  {
    a(paramCharSequence, null, null, paramF);
  }
  
  public void a(CharSequence paramCharSequence, String paramString1, String paramString2, f paramF)
  {
    a(paramCharSequence, paramString1, paramString2, false, paramF);
  }
  
  public void a(CharSequence paramCharSequence, String paramString1, String paramString2, boolean paramBoolean, f paramF)
  {
    a(paramCharSequence, paramString1, paramString2, paramBoolean, false, paramF);
  }
  
  public void a(CharSequence paramCharSequence, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, f paramF)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramF).a(paramCharSequence).b(paramString1).c(paramString2).b(paramBoolean1).c(paramBoolean2).a(this.a.getSupportFragmentManager());
  }
  
  public void a(String paramString, int paramInt)
  {
    u.a().a(paramString, paramInt);
  }
  
  public void a(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, f paramF)
  {
    a(paramString, this.a.getText(paramInt1), paramInt2, null, paramBoolean, paramF);
  }
  
  public void a(String paramString, int paramInt, f paramF)
  {
    a(null, paramString, 0, this.a.getString(paramInt), false, paramF);
  }
  
  public void a(String paramString, long paramLong)
  {
    u.a().a(paramString, paramLong);
  }
  
  public void a(String paramString1, CharSequence paramCharSequence, int paramInt, String paramString2, boolean paramBoolean, f paramF)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a().a(paramString1).a(paramCharSequence).a(paramInt).c(paramString2).b().b(paramBoolean).b(paramF).a(this.a.getSupportFragmentManager());
  }
  
  public void a(String paramString1, CharSequence paramCharSequence, String paramString2, String paramString3, boolean paramBoolean, f paramF)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramF).a(paramString1).a(paramCharSequence).b(paramString2).c(paramString3).b(paramBoolean).a(this.a.getSupportFragmentManager());
  }
  
  public void a(String paramString1, CharSequence paramCharSequence, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, f paramF)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramF).a(paramString1).a(paramCharSequence).b(paramString2).c(paramString3).b(paramBoolean1).c(paramBoolean2).a(this.a.getSupportFragmentManager());
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    u.a().a(paramString, paramBoolean);
  }
  
  public boolean a()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected()) && (localNetworkInfo.getType() == 1);
  }
  
  public boolean a(Context paramContext, boolean paramBoolean)
  {
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    if (paramBoolean) {}
    for (int i = paramContext.requestAudioFocus(null, 3, 2); i == 1; i = paramContext.abandonAudioFocus(null)) {
      return true;
    }
    return false;
  }
  
  public int b(String paramString, int paramInt)
  {
    return u.a().b(paramString, paramInt);
  }
  
  public long b(String paramString, long paramLong)
  {
    return u.a().b(paramString, paramLong);
  }
  
  public void b(int paramInt)
  {
    Toast.makeText(this.a.getApplicationContext(), paramInt, 0).show();
  }
  
  public void b(int paramInt, f paramF)
  {
    a(this.a.getText(paramInt), paramF);
  }
  
  public void b(String paramString)
  {
    u.a().f(paramString);
  }
  
  public boolean b()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected()) && (localNetworkInfo.getType() == 0);
  }
  
  public boolean b(String paramString, boolean paramBoolean)
  {
    return u.a().b(paramString, paramBoolean);
  }
  
  public void c(int paramInt)
  {
    Toast.makeText(this.a.getApplicationContext(), paramInt, 1).show();
  }
  
  public void c(String paramString)
  {
    Toast.makeText(this.a.getApplicationContext(), paramString, 0).show();
  }
  
  public boolean c()
  {
    Object localObject = (ConnectivityManager)this.a.getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).isAvailable())) {
        return true;
      }
    }
    return false;
  }
  
  public boolean d()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public boolean e()
  {
    Object localObject = this.a.getPackageManager();
    if (localObject == null) {}
    for (;;)
    {
      return false;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      if (localObject != null)
      {
        int i = 0;
        while (i < ((List)localObject).size())
        {
          if (((PackageInfo)((List)localObject).get(i)).packageName.equals("com.tencent.mm")) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
}
