package cmcm.wizard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.inputmethod.theme.f;
import com.ksmobile.keyboard.commonutils.h;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public final class e
{
  public static final int[] a = { a.f.wizard_tips_content1, a.f.wizard_tips_content2, a.f.wizard_tips_content3, a.f.wizard_tips_content4 };
  public static final int[] b = { a.f.wizard_tips_title1, a.f.wizard_tips_title1, a.f.wizard_tips_title2, a.f.wizard_tips_title2 };
  public static final String[] c = { "1", "3", "5", "7", "9", "b", "d", "f" };
  private static e d;
  private Drawable[] e = null;
  private String f;
  private String g;
  private int h = 0;
  private int i = 0;
  
  private e() {}
  
  public static e a()
  {
    if (d == null) {
      d = new e();
    }
    return d;
  }
  
  private boolean b(Activity paramActivity)
  {
    if (!h.i()) {
      return false;
    }
    if (!TextUtils.isEmpty(com.ksmobile.keyboard.commonutils.c.a))
    {
      if (TextUtils.isEmpty(com.ksmobile.keyboard.commonutils.c.b)) {
        return false;
      }
      try
      {
        Object localObject1 = Drawable.createFromStream(paramActivity.getAssets().open("oem/keyboard_theme_blur.jpg"), null);
        Object localObject2 = Drawable.createFromStream(paramActivity.getAssets().open("oem/keyboard_theme_preview.jpg"), null);
        if (localObject1 != null)
        {
          if (localObject2 == null) {
            return false;
          }
          this.e = new Drawable[] { localObject2, localObject1 };
          this.f = com.ksmobile.keyboard.commonutils.c.a;
          localObject1 = com.android.inputmethod.theme.a.c.l(com.ksmobile.keyboard.commonutils.c.b);
          localObject2 = new Intent();
          ((Intent)localObject2).putExtra("theme_package", (String)localObject1);
          ((Intent)localObject2).setClass(paramActivity, ThemeSetupWizardActivity.class);
          ((Intent)localObject2).addFlags(67108864);
          paramActivity.startActivity((Intent)localObject2);
          paramActivity.overridePendingTransition(a.a.fade_in, a.a.fade_out);
          paramActivity.finish();
          c.a(paramActivity, "key_new_user_guide_enable", Boolean.valueOf(false));
          return true;
        }
        return false;
      }
      catch (IOException paramActivity)
      {
        paramActivity.printStackTrace();
        return false;
      }
    }
    return false;
  }
  
  private Drawable[] b(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return null;
      }
      try
      {
        Context localContext = paramContext.createPackageContext(paramString, 3);
        paramContext = localContext.getResources();
        localContext.getPackageManager();
        int j = paramContext.getIdentifier("keyboard_theme_preview", "drawable", paramString);
        int k = paramContext.getIdentifier("keyboard_theme_blur", "drawable", paramString);
        if ((j > 0) && (k > 0))
        {
          paramString = paramContext.getDrawable(j);
          paramContext = paramContext.getDrawable(k);
          if ((paramString != null) && (paramContext != null))
          {
            this.e = new Drawable[] { paramString, paramContext };
            paramContext = this.e;
            return paramContext;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  private boolean c(Activity paramActivity)
  {
    if (paramActivity == null) {
      return false;
    }
    String str;
    Object localObject;
    if (this.g == null)
    {
      str = a().a(paramActivity);
    }
    else
    {
      str = this.g;
      localObject = paramActivity.getPackageManager();
    }
    try
    {
      this.f = ((PackageManager)localObject).getApplicationInfo(str, 0).loadLabel((PackageManager)localObject).toString();
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    this.f = str;
    if (TextUtils.isEmpty(str)) {
      return false;
    }
    this.e = a().b(paramActivity, str);
    if ((this.e != null) && (this.e[0] != null) && (this.e[1] != null))
    {
      localObject = new Intent();
      ((Intent)localObject).putExtra("theme_package", str);
      ((Intent)localObject).setClass(paramActivity, ThemeSetupWizardActivity.class);
      ((Intent)localObject).addFlags(67108864);
      paramActivity.startActivity((Intent)localObject);
      paramActivity.overridePendingTransition(a.a.fade_in, a.a.fade_out);
      paramActivity.finish();
      c.a(paramActivity, "key_new_user_guide_enable", Boolean.valueOf(false));
    }
    return true;
  }
  
  public String a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = null;
    paramContext = null;
    long l1;
    Iterator localIterator;
    if (localPackageManager != null)
    {
      localObject = localPackageManager.getInstalledApplications(128);
      if (localObject != null)
      {
        if (((List)localObject).size() <= 0) {
          return null;
        }
        l1 = 0L;
        localIterator = ((List)localObject).iterator();
      }
    }
    for (;;)
    {
      localObject = paramContext;
      ApplicationInfo localApplicationInfo;
      if (localIterator.hasNext())
      {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((localApplicationInfo == null) || (localApplicationInfo.metaData == null) || (!localApplicationInfo.metaData.getBoolean("KEYBOARD_THEME_SUPPORT", false)) || (!f.a(localApplicationInfo))) {
          continue;
        }
      }
      try
      {
        l2 = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 128).lastUpdateTime;
        if (l2 <= l1) {
          continue;
        }
      }
      catch (Exception localException2)
      {
        try
        {
          localObject = localApplicationInfo.packageName;
        }
        catch (Exception localException2)
        {
          try
          {
            long l2;
            paramContext = localApplicationInfo.loadLabel(localPackageManager);
            if (!TextUtils.isEmpty(paramContext)) {
              this.f = paramContext.toString();
            }
            paramContext = (Context)localObject;
            l1 = l2;
            for (;;)
            {
              break;
              for (;;)
              {
                break;
                return null;
                return localObject;
                localException1 = localException1;
              }
              localException2 = localException2;
              l1 = l2;
            }
          }
          catch (Exception paramContext)
          {
            for (;;) {}
          }
        }
      }
    }
  }
  
  public void a(String paramString)
  {
    this.g = paramString;
  }
  
  public boolean a(Activity paramActivity)
  {
    if ((h.i()) && (!com.android.inputmethod.theme.a.c.q())) {
      return false;
    }
    boolean bool2 = c(paramActivity);
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = b(paramActivity);
    }
    return bool1;
  }
  
  public Drawable[] a(Context paramContext, String paramString)
  {
    if ((this.e != null) && (this.e[0] != null) && (this.e[1] != null)) {
      return this.e;
    }
    return b(paramContext, paramString);
  }
  
  public String b()
  {
    return this.f;
  }
  
  public String[] b(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getResources();
      if (localObject == null) {
        return null;
      }
      paramContext = ((Resources)localObject).getString(b[this.h]);
      localObject = ((Resources)localObject).getString(a[this.h]);
      return new String[] { paramContext, localObject };
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public int c()
  {
    return this.h;
  }
  
  public String[] c(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getResources();
      if (localObject == null) {
        return null;
      }
      paramContext = ((Resources)localObject).getString(b[this.i]);
      localObject = ((Resources)localObject).getString(a[this.i]);
      return new String[] { paramContext, localObject };
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public int d()
  {
    return this.i;
  }
  
  public boolean e()
  {
    return false;
  }
  
  public String f()
  {
    return this.g;
  }
}
