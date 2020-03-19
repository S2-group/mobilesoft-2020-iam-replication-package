package com.luutinhit.activity;

import agl;
import agl.b;
import agr;
import ahj;
import aiw.a;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.b;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChooseCalculatorSettings
  extends agr
  implements agl.b
{
  private String m = "ChooseClockSettings";
  private Context n;
  private RecyclerView o;
  private agl p;
  private ArrayList<aiw.a> q = new ArrayList();
  private SharedPreferences r;
  
  public ChooseCalculatorSettings() {}
  
  private int a(ArrayList<aiw.a> paramArrayList)
  {
    String str = this.r.getString("calculator_application", "");
    int i = 0;
    if (!"".equals(str)) {
      try
      {
        while (i < paramArrayList.size())
        {
          boolean bool = ((aiw.a)paramArrayList.get(i)).c.equals(str);
          if (bool) {
            return i;
          }
          i += 1;
        }
        return -1;
      }
      catch (Throwable paramArrayList)
      {
        paramArrayList.printStackTrace();
      }
    }
  }
  
  private ArrayList<aiw.a> g()
  {
    try
    {
      if (this.q == null) {
        this.q = new ArrayList();
      }
      this.q.clear();
      Object localObject = this.n.getPackageManager();
      Iterator localIterator = ((PackageManager)localObject).getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((((PackageManager)localObject).getLaunchIntentForPackage(localPackageInfo.packageName) != null) && (!((PackageManager)localObject).getLaunchIntentForPackage(localPackageInfo.packageName).toString().isEmpty()))
        {
          aiw.a localA = new aiw.a();
          localA.a = localPackageInfo.applicationInfo.loadLabel((PackageManager)localObject).toString();
          localA.c = localPackageInfo.packageName;
          this.q.add(localA);
        }
      }
      Collections.sort(this.q, new Comparator() {});
      if ((this.q == null) || (this.q.isEmpty())) {
        runOnUiThread(new Runnable()
        {
          public final void run()
          {
            Toast.makeText(ChooseCalculatorSettings.a(ChooseCalculatorSettings.this), 2131689512, 1).show();
          }
        });
      }
      localObject = this.q;
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    runOnUiThread(new Runnable()
    {
      public final void run()
      {
        Toast.makeText(ChooseCalculatorSettings.a(ChooseCalculatorSettings.this), 2131689512, 1).show();
      }
    });
    return null;
  }
  
  public final void b(int paramInt)
  {
    try
    {
      if (this.p != null) {
        this.p.d.b();
      }
      aiw.a localA = (aiw.a)this.q.get(paramInt);
      this.r.edit().putString("calculator_application", localA.c).apply();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.getMessage();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427372);
    this.n = getApplicationContext();
    this.r = PreferenceManager.getDefaultSharedPreferences(this.n);
    this.o = ((RecyclerView)findViewById(2131296457));
    this.o.setLayoutManager(new LinearLayoutManager(this));
    new a((byte)0).execute(new Void[0]);
  }
  
  final class a
    extends AsyncTask<Void, Void, ArrayList<aiw.a>>
  {
    private ahj b;
    
    private a() {}
    
    protected final void onPreExecute()
    {
      super.onPreExecute();
      this.b = new ahj(ChooseCalculatorSettings.this);
      this.b.setCancelable(false);
      this.b.show();
    }
  }
}
