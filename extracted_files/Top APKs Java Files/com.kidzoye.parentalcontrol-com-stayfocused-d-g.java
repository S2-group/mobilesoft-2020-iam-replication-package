package com.stayfocused.d;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.d;
import com.stayfocused.home.DashboardActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class g
{
  private static g c;
  private final PackageManager a;
  private final Intent b;
  private final Intent d;
  private final Intent e;
  private final Context f;
  private Drawable g;
  
  private g(Context paramContext)
  {
    this.f = paramContext;
    this.a = paramContext.getPackageManager();
    this.b = new Intent("android.intent.action.MAIN", null);
    this.b.addCategory("android.intent.category.LAUNCHER");
    this.d = new Intent("android.intent.action.DIAL");
    this.e = new Intent("android.intent.action.CALL");
    this.g = d.a(paramContext, 2131230868);
  }
  
  private Bitmap a(Drawable paramDrawable)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  public static g a(Context paramContext)
  {
    try
    {
      if (c == null) {
        c = new g(paramContext);
      }
      paramContext = c;
      return paramContext;
    }
    finally {}
  }
  
  public a a(String paramString)
  {
    try
    {
      ApplicationInfo localApplicationInfo = this.a.getApplicationInfo(paramString, 128);
      paramString = new a(this.a.getApplicationLabel(localApplicationInfo).toString(), paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public List<a> a()
  {
    this.b.setPackage(null);
    Object localObject1 = this.a.queryIntentActivities(this.b, 0);
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ResolveInfo)((Iterator)localObject1).next();
      try
      {
        localObject2 = a(((ResolveInfo)localObject2).activityInfo.packageName);
        if ((localObject2 != null) && (!localArrayList.contains(localObject2))) {
          localArrayList.add(localObject2);
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  public void a(DashboardActivity paramDashboardActivity)
  {
    Object localObject1 = paramDashboardActivity.getResources();
    Intent localIntent1 = new Intent();
    localIntent1.setAction("android.intent.action.SEND");
    localIntent1.putExtra("android.intent.extra.TEXT", "Checkout Stay Focused. It helps you stay focused by restricting the daily usage of blocked apps to the selected time. https://play.google.com/store/apps/details?id=com.stayfocused");
    localIntent1.setType("message/rfc822");
    Object localObject2 = new Intent("android.intent.action.SEND");
    ((Intent)localObject2).setType("text/plain");
    localObject1 = Intent.createChooser(localIntent1, ((Resources)localObject1).getString(2131689869));
    Object localObject3 = this.a;
    int i = 0;
    localObject2 = ((PackageManager)localObject3).queryIntentActivities((Intent)localObject2, 0);
    localObject3 = new ArrayList();
    while (i < ((List)localObject2).size())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((List)localObject2).get(i);
      String str = localResolveInfo.activityInfo.packageName;
      if (str.contains("android.email"))
      {
        localIntent1.setPackage(str);
      }
      else if ((str.contains("twitter")) || (str.contains("facebook")) || (str.contains("whats")))
      {
        Intent localIntent2 = new Intent();
        localIntent2.setComponent(new ComponentName(str, localResolveInfo.activityInfo.name));
        localIntent2.setAction("android.intent.action.SEND");
        localIntent2.setType("text/plain");
        localIntent2.putExtra("android.intent.extra.TEXT", "Checkout Stay Focused. It helps you stay focused by restricting the daily usage of blocked apps to the selected time. https://play.google.com/store/apps/details?id=com.stayfocused");
        if (str.contains("android.gm")) {
          localIntent2.setType("message/rfc822");
        }
        ((List)localObject3).add(new LabeledIntent(localIntent2, str, localResolveInfo.loadLabel(this.a), localResolveInfo.icon));
      }
      i += 1;
    }
    ((Intent)localObject1).putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[])((List)localObject3).toArray(new LabeledIntent[((List)localObject3).size()]));
    paramDashboardActivity.startActivity((Intent)localObject1);
  }
  
  public void a(Class paramClass)
  {
    paramClass = new ComponentName(this.f, paramClass);
    this.a.setComponentEnabledSetting(paramClass, 1, 1);
  }
  
  public Bitmap b(String paramString)
  {
    try
    {
      paramString = this.a.getApplicationIcon(paramString);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    paramString = this.g;
    if ((paramString instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramString).getBitmap();
    }
    return a(paramString);
  }
  
  public String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getString(2131689915, new Object[] { this.a.getPackageInfo(paramContext.getPackageName(), 0).versionName });
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public List<a> b()
  {
    Object localObject = this.a;
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      localArrayList.add(new a(localPackageInfo.applicationInfo.loadLabel(this.a).toString(), localPackageInfo.applicationInfo.packageName));
      i += 1;
    }
    return localArrayList;
  }
  
  public void b(Class paramClass)
  {
    paramClass = new ComponentName(this.f, paramClass);
    this.a.setComponentEnabledSetting(paramClass, 2, 1);
  }
  
  public a c(String paramString)
  {
    this.b.setPackage(paramString);
    if (this.a.queryIntentActivities(this.b, 0).size() > 0) {
      return a(paramString);
    }
    return null;
  }
  
  public String c()
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = this.a.resolveActivity((Intent)localObject, 65536);
    if (localObject != null) {
      return ((ResolveInfo)localObject).activityInfo.packageName;
    }
    return null;
  }
  
  public boolean d(String paramString)
  {
    this.b.setPackage(paramString);
    paramString = this.a;
    Intent localIntent = this.b;
    boolean bool = false;
    if (paramString.queryIntentActivities(localIntent, 0).size() > 0) {
      bool = true;
    }
    return bool;
  }
  
  public String e(String paramString)
  {
    try
    {
      paramString = this.a.getApplicationInfo(paramString, 128);
      paramString = this.a.getApplicationLabel(paramString).toString();
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static class a
    implements Comparable
  {
    public final String a;
    public final String b;
    
    public a(String paramString1, String paramString2)
    {
      this.b = paramString1;
      this.a = paramString2;
    }
    
    public int compareTo(Object paramObject)
    {
      return this.b.compareTo(((a)paramObject).b);
    }
    
    public boolean equals(Object paramObject)
    {
      paramObject = (a)paramObject;
      return this.a.equals(paramObject.a);
    }
    
    public int hashCode()
    {
      return this.a.hashCode();
    }
  }
}
