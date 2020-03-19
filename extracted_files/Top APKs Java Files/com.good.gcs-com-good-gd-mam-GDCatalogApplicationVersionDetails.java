package com.good.gd.mam;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.good.gd.client.GDClient;
import com.good.gt.f.c;
import com.good.gt.f.e;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GDCatalogApplicationVersionDetails
{
  private String a;
  private String b;
  private boolean c;
  private boolean d;
  private Date e;
  private String f;
  private int g;
  
  public GDCatalogApplicationVersionDetails() {}
  
  final void a(int paramInt)
  {
    this.g = paramInt;
  }
  
  final void a(String paramString)
  {
    this.a = paramString;
  }
  
  final void a(Date paramDate)
  {
    this.e = paramDate;
  }
  
  final void a(boolean paramBoolean)
  {
    this.c = paramBoolean;
  }
  
  final void b(String paramString)
  {
    this.b = paramString;
  }
  
  final void b(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }
  
  final void c(String paramString)
  {
    this.f = paramString;
  }
  
  public String getApplicationVersion()
  {
    return this.b;
  }
  
  public String getNativeApplicationIdentifier()
  {
    return this.a;
  }
  
  public Date getReleaseDate()
  {
    return this.e;
  }
  
  public String getReleaseNotes()
  {
    return this.f;
  }
  
  public int getSize()
  {
    return this.g;
  }
  
  public boolean inMarket()
  {
    return this.c;
  }
  
  public boolean isInstalled()
  {
    return this.d;
  }
  
  public boolean isInstalledCheck()
  {
    boolean bool = e.a(GDClient.a().j(), this.a, this.b).a();
    if (!bool)
    {
      Iterator localIterator = GDClient.a().j().getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext()) {
        if (((ApplicationInfo)localIterator.next()).packageName.equalsIgnoreCase(this.a)) {
          return true;
        }
      }
    }
    return bool;
  }
}
