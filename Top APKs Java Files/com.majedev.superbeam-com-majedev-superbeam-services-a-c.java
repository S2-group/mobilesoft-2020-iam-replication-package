package com.majedev.superbeam.services.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import b.a.a.ag;
import b.a.a.q;
import b.a.a.t;
import com.majedev.superbeam.services.m;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
  extends b
{
  public static final String a = c.class.getSimpleName();
  Context b;
  f c;
  com.majedev.superbeam.model.a d;
  
  public c(Context paramContext, boolean paramBoolean)
  {
    super(paramBoolean);
    this.b = paramContext;
    this.d = com.majedev.superbeam.model.a.a();
  }
  
  public void a(q paramQ, t paramT, b.a.a.l.e paramE)
  {
    super.a(paramQ, paramT, paramE);
    boolean bool = m.a(paramQ);
    paramQ = URLDecoder.decode(paramQ.g().c(), "UTF-8");
    paramE = (String)paramE.a("ip");
    if (paramQ.startsWith("/get/")) {}
    for (;;)
    {
      try
      {
        i = Integer.parseInt(paramQ.substring(1).split("\\/")[1]);
        com.majedev.superbeam.services.a localA = new com.majedev.superbeam.services.a();
        localA.b = i;
        localA.a = paramE;
        localA.c = 0;
        paramE = new d(this, localA);
        if ((paramQ.startsWith("/getzip")) || (paramQ.startsWith("/getstream"))) {
          if (paramQ.startsWith("/getzip"))
          {
            b1 = 1;
            localA.c = b1;
            if (this.c.a(localA)) {
              continue;
            }
            paramQ = new b.a.a.f.i(this.b.getString(2131296472), "UTF-8");
            paramQ.a("text/html");
            paramT.a(paramQ);
            paramT.a(403);
            return;
          }
        }
      }
      catch (Exception localException)
      {
        i = -1;
        continue;
        byte b1 = 3;
        continue;
        if (paramQ.startsWith("/getapk"))
        {
          localException.c = 2;
          continue;
        }
        localException.c = 0;
        continue;
        if ((!bool) && (localException.c == 3))
        {
          paramQ = new b.a.a.f.i("FORBIDDEN!", "UTF-8");
          paramQ.a("text/plain");
          paramT.a(paramQ);
          paramT.a(403);
          return;
        }
        if ((i > -1) && (i < this.d.a.size()))
        {
          if (this.c != null) {
            this.c.b(localException);
          }
          paramQ = (com.majedev.superbeam.model.b)this.d.a.get(i);
          paramQ = new h(this, new FileInputStream(paramQ.a), paramQ.b, paramE);
          paramQ.a("application/octet-stream");
          paramQ.b("UTF-8");
          paramT.a(paramQ);
          paramT.a("Content-Disposition", "attachment");
          paramT.a(200);
          return;
        }
        if ((localException.c == 1) || (localException.c == 3))
        {
          localException.b = 0;
          if (this.c != null) {
            this.c.b(localException);
          }
          if (localException.c == 1)
          {
            paramQ = new i(this, paramE);
            paramQ.a("application/zip; charset=utf-8");
            paramT.a("Content-Disposition", "attachment; filename=\"superbeamed_files.zip\"");
            paramT.a(paramQ);
            paramT.a(200);
            return;
          }
          paramQ = new e(this, paramE);
          paramQ.a("application/octet-stream; charset=utf-8");
          paramT.a("Content-Disposition", "attachment; filename=\"superbeamed_files.stream\"");
          paramT.a(paramQ);
          paramT.a(200);
          return;
        }
        if (localException.c == 2)
        {
          localException.b = 0;
          if (this.c != null) {
            this.c.b(localException);
          }
          paramQ = this.b.getPackageManager();
          String str = this.b.getPackageName();
          Iterator localIterator = paramQ.getInstalledApplications(0).iterator();
          paramQ = null;
          if (!localIterator.hasNext())
          {
            if ((paramQ != null) && (paramQ.canRead()))
            {
              paramQ = new h(this, new FileInputStream(paramQ), paramQ.length(), paramE);
              paramQ.a("application/vnd.android.package-archive");
              paramQ.b("UTF-8");
              paramT.a(paramQ);
              paramT.a("Content-Disposition", "attachment; filename=\"" + str + ".apk\"");
              paramT.a(200);
            }
          }
          else
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            if (!localApplicationInfo.packageName.equalsIgnoreCase(str)) {
              continue;
            }
            paramQ = new File(localApplicationInfo.sourceDir);
            continue;
          }
          paramQ = new b.a.a.f.i("CANNOT READ APK!", "UTF-8");
          paramQ.a("text/plain");
          paramT.a(paramQ);
          paramT.a(404);
          return;
        }
        paramQ = new b.a.a.f.i("NOT FOUND!", "UTF-8");
        paramQ.a("text/plain");
        paramT.a(paramQ);
        paramT.a(404);
        return;
      }
      int i = -1;
    }
  }
  
  public void a(f paramF)
  {
    this.c = paramF;
  }
}
