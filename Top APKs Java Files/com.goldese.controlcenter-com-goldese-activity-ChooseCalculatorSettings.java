package com.goldese.activity;

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
import bci;
import bci.b;
import bcx;
import bec.a;
import hp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChooseCalculatorSettings
  extends hp
  implements bci.b
{
  private String n = "ChooseClockSettings";
  private Context o;
  private RecyclerView p;
  private bci q;
  private ArrayList<bec.a> r = new ArrayList();
  private SharedPreferences s;
  
  public ChooseCalculatorSettings() {}
  
  private int a(ArrayList<bec.a> paramArrayList)
  {
    int i = 0;
    String str = this.s.getString("calculator_application", "");
    if (!"".equals(str)) {
      try
      {
        while (i < paramArrayList.size())
        {
          boolean bool = ((bec.a)paramArrayList.get(i)).c.equals(str);
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
  
  private ArrayList<bec.a> g()
  {
    try
    {
      if (this.r == null) {
        this.r = new ArrayList();
      }
      this.r.clear();
      PackageManager localPackageManager = this.o.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageManager.getLaunchIntentForPackage(localPackageInfo.packageName) != null) && (!localPackageManager.getLaunchIntentForPackage(localPackageInfo.packageName).toString().isEmpty()))
        {
          bec.a localA = new bec.a();
          localA.a = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
          localA.c = localPackageInfo.packageName;
          this.r.add(localA);
        }
      }
      Collections.sort(this.r, new Comparator() {});
    }
    catch (Throwable localThrowable)
    {
      runOnUiThread(new Runnable()
      {
        public final void run()
        {
          Toast.makeText(ChooseCalculatorSettings.a(ChooseCalculatorSettings.this), 2131230744, 1).show();
        }
      });
      return null;
    }
    if ((this.r == null) || (this.r.isEmpty())) {
      runOnUiThread(new Runnable()
      {
        public final void run()
        {
          Toast.makeText(ChooseCalculatorSettings.a(ChooseCalculatorSettings.this), 2131230744, 1).show();
        }
      });
    }
    ArrayList localArrayList = this.r;
    return localArrayList;
  }
  
  public final void a_(int paramInt)
  {
    try
    {
      if (this.q != null) {
        this.q.d.b();
      }
      bec.a localA = (bec.a)this.r.get(paramInt);
      this.s.edit().putString("calculator_application", localA.c).apply();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.getMessage();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968615);
    this.o = getApplicationContext();
    this.s = PreferenceManager.getDefaultSharedPreferences(this.o);
    this.p = ((RecyclerView)findViewById(2131689659));
    this.p.setLayoutManager(new LinearLayoutManager(this));
    new a((byte)0).execute(new Void[0]);
  }
  
  final class a
    extends AsyncTask<Void, Void, ArrayList<bec.a>>
  {
    private bcx b;
    
    private a() {}
    
    protected final void onPreExecute()
    {
      super.onPreExecute();
      this.b = new bcx(ChooseCalculatorSettings.this);
      this.b.setCancelable(false);
      this.b.show();
    }
  }
}
