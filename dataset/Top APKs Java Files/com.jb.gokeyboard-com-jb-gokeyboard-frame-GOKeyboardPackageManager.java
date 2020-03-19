package com.jb.gokeyboard.frame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import com.jb.gokeyboard.GoKeyboardApplication;
import com.jb.gokeyboard.common.util.l;
import com.jb.gokeyboard.statistics.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class GOKeyboardPackageManager
  extends BroadcastReceiver
{
  private static GOKeyboardPackageManager a = null;
  private byte[] b = new byte[0];
  private Map<String, List<PackageInfo>> c = new HashMap();
  private CopyOnWriteArrayList<a> d = new CopyOnWriteArrayList();
  private boolean e;
  
  public GOKeyboardPackageManager() {}
  
  public static GOKeyboardPackageManager a()
  {
    try
    {
      if (a == null) {
        a = new GOKeyboardPackageManager();
      }
      GOKeyboardPackageManager localGOKeyboardPackageManager = a;
      return localGOKeyboardPackageManager;
    }
    finally {}
  }
  
  private String a(String paramString, boolean paramBoolean)
  {
    String str = paramString;
    if (paramBoolean) {
      str = "pad_" + paramString;
    }
    return str;
  }
  
  private void a(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Context cannot be null!");
    }
    synchronized (this.b)
    {
      Object localObject = paramContext.getPackageManager();
      paramContext = null;
      try
      {
        localObject = ((PackageManager)localObject).getInstalledPackages(0);
        paramContext = (Context)localObject;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      if ((paramContext == null) || (paramContext.size() <= 0)) {
        return;
      }
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo != null) && (!"com.jb.gokeyboard.plugin.removeads".equals(localPackageInfo.packageName)))
      {
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
          a(localPackageInfo);
        }
      }
    }
  }
  
  private void a(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo == null) || (TextUtils.isEmpty(paramPackageInfo.packageName))) {}
    Object localObject1;
    String str;
    do
    {
      return;
      localObject1 = paramPackageInfo.packageName;
      str = c((String)localObject1);
    } while (TextUtils.isEmpty(str));
    if ((TextUtils.equals(str, "language")) && (this.e)) {}
    for (;;)
    {
      try
      {
        localObject2 = GoKeyboardApplication.b().getSharedPreferences("DownloadingLanguage_static", 0).getString("downloading_lauange_" + (String)localObject1, "");
        if (!TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject2 = ((String)localObject2).split(",");
          if ((localObject2 != null) && (localObject2.length == 2) && (System.currentTimeMillis() - Long.valueOf(localObject2[0]).longValue() <= 3600000L)) {
            f.a().a("uselang_b000", "f_language_apk", (String)localObject1, localObject2[1], paramPackageInfo.versionCode);
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject2;
        continue;
      }
      synchronized (this.b)
      {
        localObject2 = (List)this.c.get(str);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = new ArrayList();
          this.c.put(str, localObject1);
        }
        ((List)localObject1).add(paramPackageInfo);
        return;
      }
    }
  }
  
  private boolean b(String paramString)
  {
    Object localObject = c(paramString);
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      return false;
    }
    synchronized (this.b)
    {
      localObject = (List)this.c.get(localObject);
      if (localObject == null) {
        return false;
      }
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo != null) && (paramString.equals(localPackageInfo.packageName)))
        {
          ((List)localObject).remove(localPackageInfo);
          return true;
        }
      }
    }
    return false;
  }
  
  private String c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if ((paramString.startsWith("com.jb.gokeyboard.font.")) || (paramString.startsWith("com.jb.gokeyboard.plugin.font."))) {
      return "font";
    }
    if ((paramString.startsWith("com.jb.gokeyboard.plugin.")) || (paramString.equals("com.jb.gokeyboard.handwrite.zh"))) {
      return "plugin";
    }
    if (paramString.startsWith("com.jb.gokeyboard.theme.")) {
      return "theme_phone";
    }
    if (paramString.startsWith("com.jb.gokeyboard.pad.theme.")) {
      return "theme_pad";
    }
    if (paramString.startsWith("com.jb.gokeyboard.langpack.")) {
      return "language";
    }
    return "other";
  }
  
  public List<PackageInfo> a(String paramString)
  {
    synchronized (this.b)
    {
      paramString = (List)this.c.get(paramString);
      if ((paramString == null) || (paramString.size() <= 0)) {
        return null;
      }
      paramString = new ArrayList(paramString);
      return paramString;
    }
  }
  
  public void a(final Context paramContext, boolean paramBoolean)
  {
    this.e = paramBoolean;
    l.a(new c()
    {
      public void run()
      {
        GOKeyboardPackageManager.a(GOKeyboardPackageManager.this, paramContext);
      }
    });
  }
  
  public void a(a paramA)
  {
    if (this.d != null) {
      synchronized (this.d)
      {
        if (!this.d.contains(paramA)) {
          this.d.add(paramA);
        }
        return;
      }
    }
  }
  
  public void b(a paramA)
  {
    if (this.d != null) {
      synchronized (this.d)
      {
        if (this.d.contains(paramA)) {
          this.d.remove(paramA);
        }
        return;
      }
    }
  }
  
  public void onReceive(Context arg1, Intent paramIntent)
  {
    Object localObject = null;
    a localA = null;
    String str2 = paramIntent.getAction();
    String str1 = paramIntent.getData().getSchemeSpecificPart();
    if (TextUtils.isEmpty(str1)) {}
    for (;;)
    {
      return;
      boolean bool = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
      if (("android.intent.action.PACKAGE_ADDED".equals(str2)) && (!bool))
      {
        ??? = ???.getPackageManager();
        try
        {
          ??? = ???.getPackageInfo(str1, 0);
          if (??? == null) {
            continue;
          }
          a(???);
          if ((this.e) && ((str1.startsWith("com.jb.gokeyboard.theme.")) || (str1.startsWith("com.jb.gokeyboard.pad.theme."))))
          {
            bool = str1.startsWith("com.jb.gokeyboard.pad.theme.");
            ??? = a("key_theme_is_new", bool);
            paramIntent = b.a().a(???, "");
            paramIntent = paramIntent + ":" + str1;
            b.a().b(???, paramIntent);
            ??? = a("key_show_animation_for_new_theme", bool);
            b.a().d(???, true);
          }
          synchronized (this.d)
          {
            paramIntent = this.d.iterator();
            while (paramIntent.hasNext())
            {
              localA = (a)paramIntent.next();
              if (localA != null) {
                localA.d(str1);
              }
            }
          }
        }
        catch (PackageManager.NameNotFoundException ???)
        {
          for (;;)
          {
            ???.printStackTrace();
            ??? = localA;
          }
          return;
        }
      }
      else
      {
        if (("android.intent.action.PACKAGE_REMOVED".equals(str2)) && (!bool))
        {
          b(str1);
          if ((this.e) && ((str1.startsWith("com.jb.gokeyboard.theme.")) || (str1.startsWith("com.jb.gokeyboard.pad.theme."))))
          {
            bool = str1.startsWith("com.jb.gokeyboard.pad.theme.");
            ??? = a("key_theme_is_new", bool);
            paramIntent = b.a().a(???, "").replace(str1, "");
            b.a().b(???, paramIntent);
            ??? = a("key_show_animation_for_new_theme", bool);
            b.a().d(???, true);
          }
          synchronized (this.d)
          {
            paramIntent = this.d.iterator();
            while (paramIntent.hasNext())
            {
              localA = (a)paramIntent.next();
              if (localA != null) {
                localA.e(str1);
              }
            }
          }
          return;
        }
        if ("android.intent.action.PACKAGE_REPLACED".equals(str2))
        {
          b(str1);
          ??? = ???.getPackageManager();
          try
          {
            ??? = ???.getPackageInfo(str1, 0);
            if (??? != null)
            {
              a(???);
              synchronized (this.d)
              {
                paramIntent = this.d.iterator();
                while (paramIntent.hasNext())
                {
                  localA = (a)paramIntent.next();
                  if (localA != null) {
                    localA.f(str1);
                  }
                }
              }
            }
          }
          catch (Throwable ???)
          {
            for (;;)
            {
              ???.printStackTrace();
              ??? = localObject;
            }
          }
        }
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void d(String paramString);
    
    public abstract void e(String paramString);
    
    public abstract void f(String paramString);
  }
}
