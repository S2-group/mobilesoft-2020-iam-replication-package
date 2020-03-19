package com.kapp.ifont.x.perappfonts;

import a.a.b.c;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.AsyncTaskLoader;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class b
  extends AsyncTaskLoader<List<a>>
{
  public static final Comparator<a> b = new Comparator()
  {
    private final Collator a = Collator.getInstance();
    
    public int a(a paramAnonymousA1, a paramAnonymousA2)
    {
      Object localObject = paramAnonymousA1.b();
      String str = paramAnonymousA2.b();
      paramAnonymousA2 = (a)localObject;
      if (localObject == null) {
        paramAnonymousA2 = paramAnonymousA1.c();
      }
      localObject = str;
      if (str == null) {
        localObject = paramAnonymousA1.c();
      }
      return this.a.compare(paramAnonymousA2, (String)localObject);
    }
  };
  public static final Comparator<ApplicationInfo> c = new Comparator()
  {
    private final Collator a = Collator.getInstance();
    
    public int a(ApplicationInfo paramAnonymousApplicationInfo1, ApplicationInfo paramAnonymousApplicationInfo2)
    {
      boolean bool1 = m.c(paramAnonymousApplicationInfo1);
      boolean bool2 = m.c(paramAnonymousApplicationInfo2);
      paramAnonymousApplicationInfo1 = paramAnonymousApplicationInfo1.packageName;
      paramAnonymousApplicationInfo2 = paramAnonymousApplicationInfo2.packageName;
      if ((bool1) && (bool2)) {
        return this.a.compare(paramAnonymousApplicationInfo1, paramAnonymousApplicationInfo2);
      }
      if (bool1) {
        return -1;
      }
      if (bool2) {
        return 1;
      }
      return this.a.compare(paramAnonymousApplicationInfo1, paramAnonymousApplicationInfo2);
    }
  };
  private static final String f = b.class.getSimpleName();
  public final PackageManager a;
  List<a> d;
  c e;
  
  public b(Context paramContext)
  {
    super(paramContext);
    this.a = paramContext.getPackageManager();
  }
  
  public List<a> a()
  {
    int i = 0;
    Object localObject1 = this.a.getInstalledApplications(0);
    if (localObject1 == null) {
      localObject1 = new ArrayList();
    }
    for (;;)
    {
      Context localContext = getContext();
      Collections.sort((List)localObject1, new a(localContext));
      ArrayList localArrayList = new ArrayList();
      if (i < ((List)localObject1).size())
      {
        Object localObject2 = (ApplicationInfo)((List)localObject1).get(i);
        if (((ApplicationInfo)localObject2).packageName.equals(localContext.getPackageName())) {}
        for (;;)
        {
          i += 1;
          break;
          if ((!((ApplicationInfo)localObject2).packageName.startsWith("com.monotype.android.font.")) && (!((ApplicationInfo)localObject2).packageName.startsWith("com.kapp.cm.theme.")))
          {
            localObject2 = new a(this, (ApplicationInfo)localObject2);
            ((a)localObject2).a(localContext);
            localArrayList.add(localObject2);
            Collections.sort(localArrayList, new b(localContext));
            c.a().d(new com.kapp.ifont.x.perappfonts.a.a(localArrayList, 1));
          }
        }
      }
      return localArrayList;
    }
  }
  
  protected void onReset()
  {
    super.onReset();
    onStopLoading();
    this.d = null;
    if (this.e != null)
    {
      getContext().unregisterReceiver(this.e);
      this.e = null;
    }
  }
  
  protected void onStartLoading()
  {
    if (this.d != null) {
      deliverResult(this.d);
    }
    if (this.e == null) {
      this.e = new c(this);
    }
    if (this.d == null) {
      forceLoad();
    }
  }
  
  public static class a
    implements Comparator<ApplicationInfo>
  {
    private final Collator a = Collator.getInstance();
    private SharedPreferences b;
    private SharedPreferences c;
    
    public a(Context paramContext)
    {
      this.c = paramContext.getSharedPreferences("main_pref", 1);
      this.b = paramContext.getSharedPreferences("font_typeface", 1);
    }
    
    private boolean a(String paramString)
    {
      if (("android".equals(paramString)) || ("com.android.systemui".equals(paramString))) {
        return this.c.contains(paramString);
      }
      return this.b.contains(paramString);
    }
    
    public int a(ApplicationInfo paramApplicationInfo1, ApplicationInfo paramApplicationInfo2)
    {
      int j = -1;
      String str1 = paramApplicationInfo1.packageName;
      String str2 = paramApplicationInfo2.packageName;
      boolean bool1 = a(paramApplicationInfo1.packageName);
      boolean bool2 = a(paramApplicationInfo2.packageName);
      int i;
      if ((bool1) && (bool2)) {
        i = this.a.compare(str1, str2);
      }
      do
      {
        do
        {
          return i;
          i = j;
        } while (bool1);
        if (bool2) {
          return 1;
        }
        bool1 = m.c(paramApplicationInfo1);
        bool2 = m.c(paramApplicationInfo2);
        if ((bool1) && (bool2)) {
          return this.a.compare(str1, str2);
        }
        i = j;
      } while (bool1);
      if (bool2) {
        return 1;
      }
      return this.a.compare(str1, str2);
    }
  }
  
  public static class b
    implements Comparator<a>
  {
    private final Collator a = Collator.getInstance();
    private SharedPreferences b;
    private SharedPreferences c;
    
    public b(Context paramContext)
    {
      this.c = paramContext.getSharedPreferences("main_pref", 1);
      this.b = paramContext.getSharedPreferences("font_typeface", 1);
    }
    
    private boolean a(String paramString)
    {
      if (("android".equals(paramString)) || ("com.android.systemui".equals(paramString))) {
        return this.c.contains(paramString);
      }
      return this.b.contains(paramString);
    }
    
    public int a(a paramA1, a paramA2)
    {
      Object localObject2 = paramA1.b();
      String str = paramA2.b();
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = paramA1.c();
      }
      localObject2 = str;
      if (str == null) {
        localObject2 = paramA1.c();
      }
      boolean bool1 = a(paramA1.c());
      boolean bool2 = a(paramA2.c());
      if ((bool1) && (bool2)) {
        return this.a.compare((String)localObject1, (String)localObject2);
      }
      if (bool1) {
        return -1;
      }
      if (bool2) {
        return 1;
      }
      bool1 = m.c(paramA1.a());
      bool2 = m.c(paramA2.a());
      if ((bool1) && (bool2)) {
        return this.a.compare((String)localObject1, (String)localObject2);
      }
      if (bool1) {
        return -1;
      }
      if (bool2) {
        return 1;
      }
      return this.a.compare((String)localObject1, (String)localObject2);
    }
  }
  
  public static class c
    extends BroadcastReceiver
  {
    final b a;
    
    public c(b paramB)
    {
      this.a = paramB;
      paramB = new IntentFilter("android.intent.action.PACKAGE_ADDED");
      paramB.addAction("android.intent.action.PACKAGE_REMOVED");
      paramB.addAction("android.intent.action.PACKAGE_CHANGED");
      paramB.addDataScheme("package");
      this.a.getContext().registerReceiver(this, paramB);
      paramB = new IntentFilter();
      paramB.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
      paramB.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
      this.a.getContext().registerReceiver(this, paramB);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      this.a.onContentChanged();
    }
  }
}
