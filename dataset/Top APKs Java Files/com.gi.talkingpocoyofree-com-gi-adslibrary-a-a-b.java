package com.gi.adslibrary.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class b
  extends a
{
  private static final String a = b.class.getSimpleName();
  private List<String> b;
  private String c;
  private String d;
  private String e;
  
  protected b(String paramString1, String paramString2, String paramString3)
  {
    super("BannerTypeLaunchApp");
    this.c = paramString1;
    this.e = paramString2;
    this.d = paramString3;
  }
  
  public List<String> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((!paramBoolean) && (localPackageInfo.versionName == null)) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public boolean a(Context paramContext, String paramString)
  {
    if (this.b == null) {
      this.b = a(paramContext, false);
    }
    paramContext = this.b.iterator();
    while (paramContext.hasNext())
    {
      String str = (String)paramContext.next();
      if ((paramString != null) && (paramString.equals(str))) {
        return true;
      }
    }
    return false;
  }
  
  public View.OnClickListener b(final Context paramContext)
  {
    new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (b.this.a(paramContext, b.a(b.this)))
        {
          paramAnonymousView = paramContext.getPackageName();
          if (b.a(b.this).equals(paramAnonymousView)) {}
        }
        while ((b.c(b.this) == null) || (b.c(b.this).length() <= 0)) {
          try
          {
            paramAnonymousView = new Intent("android.intent.action.MAIN");
            paramAnonymousView.setComponent(new ComponentName(b.a(b.this), b.b(b.this)));
            paramContext.startActivity(paramAnonymousView);
            System.exit(1);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            paramAnonymousView.printStackTrace();
            return;
          }
        }
        try
        {
          paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(b.c(b.this)));
          paramContext.startActivity(paramAnonymousView);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    };
  }
}
