package com.ants360.yicamera.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import com.ants360.yicamera.activity.BaseActivity;
import com.ants360.yicamera.bean.DeviceInfo;
import com.ants360.yicamera.constants.PlatformConst.Abilities;
import com.ants360.yicamera.fragment.SimpleDialogFragment;
import com.ants360.yicamera.util.v;
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
    return v.a().b(paramString);
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
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, com.ants360.yicamera.e.e paramE)
  {
    a(paramInt1, paramInt2, paramInt3, paramInt4, false, paramE);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    a(this.a.getString(paramInt1), this.a.getText(paramInt2), this.a.getString(paramInt3), this.a.getString(paramInt4), paramBoolean, paramE);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, com.ants360.yicamera.e.e paramE)
  {
    a(paramInt1, paramInt2, paramInt3, false, paramE);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    a(this.a.getText(paramInt1), this.a.getString(paramInt2), this.a.getString(paramInt3), paramBoolean, paramE);
  }
  
  public void a(int paramInt1, int paramInt2, com.ants360.yicamera.e.e paramE)
  {
    a(paramInt1, paramInt2, false, paramE);
  }
  
  public void a(int paramInt1, int paramInt2, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    a(null, this.a.getText(paramInt1), 0, this.a.getString(paramInt2), paramBoolean, paramE);
  }
  
  public void a(int paramInt, com.ants360.yicamera.e.e paramE)
  {
    a(paramInt, false, paramE);
  }
  
  public void a(int paramInt, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    a(null, this.a.getText(paramInt), 0, null, paramBoolean, paramE);
  }
  
  public void a(View paramView1, View paramView2, View paramView3, com.ants360.yicamera.e.e paramE)
  {
    a(paramView1, paramView2, paramView3, true, false, false, null, paramE);
  }
  
  public void a(View paramView1, View paramView2, View paramView3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, com.ants360.yicamera.e.e paramE1, com.ants360.yicamera.e.e paramE2)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramE1).c(paramBoolean3).a(paramView1).b(paramView2).c(paramView3).c(paramE2).a(paramBoolean1).b(paramBoolean2).a(this.a.getSupportFragmentManager());
  }
  
  public void a(View paramView, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    a(paramView, null, null, paramBoolean, false, false, paramE, null);
  }
  
  public void a(CharSequence paramCharSequence, com.ants360.yicamera.e.e paramE)
  {
    a(paramCharSequence, null, null, paramE);
  }
  
  public void a(CharSequence paramCharSequence, String paramString1, String paramString2, com.ants360.yicamera.e.e paramE)
  {
    a(paramCharSequence, paramString1, paramString2, false, paramE);
  }
  
  public void a(CharSequence paramCharSequence, String paramString1, String paramString2, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramE).a(paramCharSequence).b(paramString1).c(paramString2).b(paramBoolean).a(this.a.getSupportFragmentManager());
  }
  
  public void a(String paramString, int paramInt)
  {
    v.a().a(paramString, paramInt);
  }
  
  public void a(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    a(paramString, this.a.getText(paramInt1), paramInt2, null, paramBoolean, paramE);
  }
  
  public void a(String paramString, int paramInt, com.ants360.yicamera.e.e paramE)
  {
    a(null, paramString, 0, this.a.getString(paramInt), false, paramE);
  }
  
  public void a(String paramString, long paramLong)
  {
    v.a().a(paramString, paramLong);
  }
  
  public void a(String paramString1, CharSequence paramCharSequence, int paramInt, String paramString2, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a().a(paramString1).a(paramCharSequence).c(paramInt).c(paramString2).b().b(paramBoolean).b(paramE).a(this.a.getSupportFragmentManager());
  }
  
  public void a(String paramString1, CharSequence paramCharSequence, String paramString2, String paramString3, boolean paramBoolean, com.ants360.yicamera.e.e paramE)
  {
    if (!this.a.c) {
      return;
    }
    SimpleDialogFragment.a(paramE).a(paramString1).a(paramCharSequence).b(paramString2).c(paramString3).b(paramBoolean).a(this.a.getSupportFragmentManager());
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    v.a().a(paramString, paramBoolean);
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
  
  public boolean a(PlatformConst.Abilities paramAbilities, DeviceInfo paramDeviceInfo)
  {
    if ((paramDeviceInfo.y() != 2) && (paramDeviceInfo.a(paramAbilities)))
    {
      paramDeviceInfo = "";
      switch (1.a[paramAbilities.ordinal()])
      {
      default: 
        paramAbilities = paramDeviceInfo;
      }
      for (;;)
      {
        c(this.a.getString(2131492933, new Object[] { paramAbilities }));
        return false;
        paramAbilities = this.a.getString(2131492934);
        continue;
        paramAbilities = this.a.getString(2131492935);
        continue;
        paramAbilities = this.a.getString(2131492936);
        continue;
        paramAbilities = this.a.getString(2131492940);
        continue;
        paramAbilities = this.a.getString(2131492937);
        continue;
        paramAbilities = this.a.getString(2131492939);
        continue;
        paramAbilities = this.a.getString(2131492941);
        continue;
        paramAbilities = this.a.getString(2131492938);
      }
    }
    return true;
  }
  
  public int b(String paramString, int paramInt)
  {
    return v.a().b(paramString, paramInt);
  }
  
  public long b(String paramString, long paramLong)
  {
    return v.a().b(paramString, paramLong);
  }
  
  public void b(int paramInt)
  {
    Toast.makeText(this.a.getApplicationContext(), paramInt, 0).show();
  }
  
  public void b(int paramInt, com.ants360.yicamera.e.e paramE)
  {
    a(this.a.getText(paramInt), paramE);
  }
  
  public void b(String paramString)
  {
    v.a().f(paramString);
  }
  
  public boolean b()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected()) && (localNetworkInfo.getType() == 0);
  }
  
  public boolean b(String paramString, boolean paramBoolean)
  {
    return v.a().b(paramString, paramBoolean);
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
  
  public boolean f()
  {
    int i;
    int j;
    if (com.ants360.yicamera.a.e.j())
    {
      i = b("AGREEMENT_EU_VERSION_NUM", 0);
      j = b("POLICY_EU_VERSION_NUM", 0);
      if ((i >= this.a.getResources().getInteger(2131296259)) && (j >= this.a.getResources().getInteger(2131296271))) {}
    }
    else
    {
      do
      {
        return true;
        if (!com.ants360.yicamera.a.e.i()) {
          break;
        }
        i = b("AGREEMENT_ISR_VERSION_NUM", 0);
        j = b("POLICY_ISR_VERSION_NUM", 0);
      } while ((i < this.a.getResources().getInteger(2131296260)) || (j < this.a.getResources().getInteger(2131296272)));
    }
    label180:
    label240:
    do
    {
      do
      {
        do
        {
          do
          {
            return false;
            if (!com.ants360.yicamera.a.e.h()) {
              break label180;
            }
            i = b("AGREEMENT_USA_VERSION_NUM", 0);
            j = b("POLICY_USA_VERSION_NUM", 0);
            if (i < this.a.getResources().getInteger(2131296263)) {
              break;
            }
          } while (j >= this.a.getResources().getInteger(2131296275));
          return true;
          if (!com.ants360.yicamera.a.e.k()) {
            break label240;
          }
          i = b("AGREEMENT_SEA_VERSION_NUM", 0);
          j = b("POLICY_SEA_VERSION_NUM", 0);
          if (i < this.a.getResources().getInteger(2131296261)) {
            break;
          }
        } while (j >= this.a.getResources().getInteger(2131296273));
        return true;
      } while (!com.ants360.yicamera.a.e.f());
      i = b("AGREEMENT_TW_VERSION_NUM", 0);
      j = b("POLICY_TW_VERSION_NUM", 0);
      if (i < this.a.getResources().getInteger(2131296262)) {
        break;
      }
    } while (j >= this.a.getResources().getInteger(2131296274));
    return true;
  }
}
