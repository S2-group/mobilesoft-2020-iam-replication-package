package com.martianmode.applock.b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.martianmode.applock.c.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class b
  extends AsyncTask<Void, Void, Void>
{
  private final PackageManager a;
  private final File b;
  private final Locale c;
  private final a d;
  private String[] e;
  private String[] f;
  private String[] g;
  private List<String> h = new ArrayList();
  private List<String> i = new ArrayList();
  private List<com.martianmode.applock.a.b> j = new ArrayList();
  private List<com.martianmode.applock.a.a> k = new ArrayList();
  
  public b(Context paramContext, a paramA)
  {
    this.a = paramContext.getPackageManager();
    this.c = com.martianmode.applock.f.b.a(paramContext.getResources().getConfiguration());
    this.b = paramContext.getFilesDir();
    this.e = paramContext.getResources().getStringArray(2130903048);
    this.f = paramContext.getResources().getStringArray(2130903046);
    this.g = paramContext.getResources().getStringArray(2130903047);
    this.d = paramA;
  }
  
  private Bitmap a(Drawable paramDrawable)
  {
    Object localObject;
    if ((paramDrawable instanceof BitmapDrawable))
    {
      localObject = (BitmapDrawable)paramDrawable;
      if (((BitmapDrawable)localObject).getBitmap() != null) {
        return ((BitmapDrawable)localObject).getBitmap();
      }
    }
    if ((paramDrawable.getIntrinsicWidth() > 0) && (paramDrawable.getIntrinsicHeight() > 0)) {
      localObject = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    } else {
      localObject = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localObject;
  }
  
  private void a()
  {
    this.k = new ArrayList();
    if ((this.g.length == this.e.length) && (this.e.length == this.f.length))
    {
      if (this.g.length >= 3)
      {
        String str = b();
        if ((!TextUtils.isEmpty(str)) && (!this.g[2].equals(str))) {
          this.g[2] = str;
        }
      }
      int m = 0;
      while (m < this.g.length)
      {
        this.k.add(new com.martianmode.applock.a.a(this.g[m], this.e[m], this.f[m]));
        m += 1;
      }
    }
  }
  
  private void a(ApplicationInfo paramApplicationInfo)
  {
    if ((!"com.martianmode.applocker".equals(paramApplicationInfo.packageName)) && (this.a.getLaunchIntentForPackage(paramApplicationInfo.packageName) != null))
    {
      String str;
      if (!a(paramApplicationInfo.packageName))
      {
        if (a(this.a, paramApplicationInfo))
        {
          str = this.a.getApplicationLabel(paramApplicationInfo).toString();
          this.h.add(paramApplicationInfo.packageName);
          this.i.add(str);
          this.j.add(new com.martianmode.applock.a.b(paramApplicationInfo.packageName, str));
        }
      }
      else
      {
        str = this.a.getApplicationLabel(paramApplicationInfo).toString();
        this.h.add(paramApplicationInfo.packageName);
        this.i.add(str);
        this.j.add(new com.martianmode.applock.a.b(paramApplicationInfo.packageName, str));
      }
    }
  }
  
  private boolean a(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    paramPackageManager = a(paramPackageManager.getApplicationIcon(paramApplicationInfo));
    File localFile = this.b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramApplicationInfo.packageName);
    localStringBuilder.append(com.martianmode.applock.f.b.c());
    paramApplicationInfo = new File(localFile, localStringBuilder.toString());
    try
    {
      if (paramApplicationInfo.createNewFile())
      {
        paramApplicationInfo = new FileOutputStream(paramApplicationInfo.getPath());
        paramPackageManager.compress(com.martianmode.applock.f.b.b(), 70, paramApplicationInfo);
        paramApplicationInfo.close();
        return true;
      }
    }
    catch (IOException paramPackageManager)
    {
      paramPackageManager.printStackTrace();
    }
    return false;
  }
  
  private boolean a(String paramString)
  {
    File localFile = this.b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(com.martianmode.applock.f.b.c());
    return new File(localFile, localStringBuilder.toString()).exists();
  }
  
  private String b()
  {
    Object localObject = new Intent("android.settings.SETTINGS");
    localObject = this.a.queryIntentActivities((Intent)localObject, 65536);
    if ((localObject != null) && (((List)localObject).size() != 0)) {
      return ((ResolveInfo)((List)localObject).get(0)).activityInfo.packageName;
    }
    return "";
  }
  
  private void b(String paramString)
  {
    File localFile = this.b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(com.martianmode.applock.f.b.c());
    new File(localFile, localStringBuilder.toString()).delete();
  }
  
  protected Void a(Void... paramVarArgs)
  {
    if ((com.martianmode.applock.f.b.d()) && (com.martianmode.applock.f.b.a(this.b))) {
      com.martianmode.applock.c.b.a(false);
    }
    a();
    paramVarArgs = this.a.getInstalledApplications(0);
    Object localObject3;
    Object localObject1;
    Object localObject2;
    Object localObject4;
    if ((com.martianmode.applock.c.b.b()) && (this.c.getDisplayLanguage().equalsIgnoreCase(d.b("com.martianmode.applock.APP_LANGUAGE", this.c.getDisplayLanguage()))))
    {
      int m = com.martianmode.applock.c.b.c();
      if (paramVarArgs.size() == m)
      {
        this.j = com.martianmode.applock.c.b.d();
        new a(this.a, this.b).execute(new Void[0]);
        break label571;
      }
      if (paramVarArgs.size() > m)
      {
        localObject3 = com.martianmode.applock.c.b.e();
        localObject1 = new ArrayList();
        localObject2 = new ArrayList();
        localObject4 = paramVarArgs.iterator();
        while (((Iterator)localObject4).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject4).next();
          if (this.a.getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
            ((List)localObject1).add(localApplicationInfo.packageName);
          }
        }
        ((List)localObject1).removeAll((Collection)localObject3);
        localObject3 = ((List)localObject1).iterator();
      }
    }
    for (;;)
    {
      if (((Iterator)localObject3).hasNext()) {
        localObject4 = (String)((Iterator)localObject3).next();
      }
      try
      {
        localObject4 = this.a.getApplicationInfo((String)localObject4, 0);
        ((List)localObject2).add(this.a.getApplicationLabel((ApplicationInfo)localObject4).toString());
        if (a(((ApplicationInfo)localObject4).packageName)) {
          continue;
        }
        a(this.a, (ApplicationInfo)localObject4);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      if (((List)localObject1).size() > 0) {
        com.martianmode.applock.c.b.b((List)localObject1, (List)localObject2);
      }
      this.j = com.martianmode.applock.c.b.d();
      com.martianmode.applock.c.b.a(paramVarArgs.size());
      break label571;
      localObject1 = com.martianmode.applock.c.b.e();
      localObject2 = new ArrayList();
      localObject3 = paramVarArgs.iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
        if (this.a.getLaunchIntentForPackage(((ApplicationInfo)localObject4).packageName) != null) {
          ((List)localObject2).add(((ApplicationInfo)localObject4).packageName);
        }
      }
      ((List)localObject1).removeAll((Collection)localObject2);
      localObject2 = ((List)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        if (a((String)localObject3)) {
          b((String)localObject3);
        }
      }
      com.martianmode.applock.c.b.b((List)localObject1);
      com.martianmode.applock.c.b.a(paramVarArgs.size());
      this.j = com.martianmode.applock.c.b.d();
      break label571;
      localObject1 = paramVarArgs.iterator();
      while (((Iterator)localObject1).hasNext()) {
        a((ApplicationInfo)((Iterator)localObject1).next());
      }
      com.martianmode.applock.c.b.a(this.h, this.i);
      com.martianmode.applock.c.b.a(paramVarArgs.size());
      com.martianmode.applock.c.b.a(true);
      d.a("com.martianmode.applock.APP_LANGUAGE", this.c.getDisplayLanguage());
      this.j = com.martianmode.applock.c.b.d();
      label571:
      return null;
    }
  }
  
  protected void a(Void paramVoid)
  {
    super.onPostExecute(paramVoid);
    this.d.onFinish(this.j, this.k);
  }
  
  public static abstract interface a
  {
    public abstract void onFinish(List<com.martianmode.applock.a.b> paramList, List<com.martianmode.applock.a.a> paramList1);
  }
}
