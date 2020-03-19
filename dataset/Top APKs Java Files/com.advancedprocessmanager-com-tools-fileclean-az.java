package com.tools.fileclean;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.widget.TextView;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class az
  extends AsyncTask
{
  CountDownLatch a;
  
  az(ak paramAk) {}
  
  protected String a(String... paramVarArgs)
  {
    int j = Integer.parseInt(paramVarArgs[0]);
    this.b.m = true;
    int i = 0;
    while (i < this.b.e.length)
    {
      this.b.e[i].f.clear();
      this.b.e[i].d = 0;
      this.b.e[i].e = 0L;
      i += 1;
    }
    publishProgress(new Integer[] { Integer.valueOf(0) });
    Object localObject2;
    Object localObject1;
    Object localObject3;
    try
    {
      this.b.v = 0;
      this.b.u = 0;
      this.a = new CountDownLatch(1);
      paramVarArgs = this.b.f.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      localObject2 = this.b.f.getInstalledPackages(8704);
      localObject1 = ((PackageInfo)((List)localObject2).get(((List)localObject2).size() - 1)).packageName;
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (PackageInfo)((Iterator)localObject2).next();
        paramVarArgs.invoke(this.b.f, new Object[] { ((PackageInfo)localObject3).packageName, new ba(this, (String)localObject1) });
      }
      if (!Environment.getExternalStorageState().equals("mounted")) {
        break label420;
      }
    }
    catch (Exception paramVarArgs)
    {
      System.out.println("Exception: ee" + paramVarArgs.getMessage());
    }
    paramVarArgs = new ArrayList();
    label420:
    do
    {
      paramVarArgs.add(Environment.getExternalStorageDirectory());
      while ((paramVarArgs.size() > 0) && (this.b.m))
      {
        localObject2 = (File)paramVarArgs.get(0);
        paramVarArgs.remove(0);
        localObject1 = ((File)localObject2).listFiles();
        if (localObject1 != null)
        {
          if (localObject1.length != 0) {
            break;
          }
          localObject1 = this.b.e[5];
          ((as)localObject1).d += 1;
          this.b.e[5].f.add(localObject2);
        }
      }
      this.b.t.sendEmptyMessage(1);
      return null;
      this.a.await();
      break;
    } while (((File)localObject2).getAbsolutePath().split("/").length >= j);
    int k = localObject1.length;
    i = 0;
    label466:
    if (i < k)
    {
      localObject2 = localObject1[i];
      localObject3 = ((File)localObject2).getAbsolutePath();
      if (!((File)localObject2).isDirectory()) {
        break label644;
      }
      if (!((String)localObject3).endsWith("/DCIM/.thumbnails")) {
        break label581;
      }
      this.b.b((File)localObject2);
      localObject2 = this.b;
      ((ak)localObject2).u += this.b.e[1].d;
    }
    for (;;)
    {
      localObject2 = this.b;
      ((ak)localObject2).u += 1;
      publishProgress(new Integer[] { Integer.valueOf(0) });
      i += 1;
      break label466;
      break;
      label581:
      if (((String)localObject3).endsWith("/LOST.DIR"))
      {
        this.b.c((File)localObject2);
        localObject2 = this.b;
        ((ak)localObject2).u += this.b.e[2].d;
      }
      else
      {
        paramVarArgs.add(localObject2);
        continue;
        label644:
        this.b.a((File)localObject2);
      }
    }
  }
  
  protected void a(String paramString) {}
  
  protected void a(Integer... paramVarArgs)
  {
    this.b.k.setText("(" + this.b.u + ") " + this.b.p);
    this.b.d.notifyDataSetChanged();
  }
  
  protected void onCancelled()
  {
    System.out.println("onCancelled");
    if (this.a != null) {
      this.a.countDown();
    }
  }
  
  protected void onPreExecute() {}
}
