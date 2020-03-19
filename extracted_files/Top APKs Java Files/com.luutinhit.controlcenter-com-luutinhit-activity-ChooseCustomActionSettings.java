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
import android.view.MenuItem;
import android.widget.Toast;
import ic;
import ie;
import ig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChooseCustomActionSettings
  extends agr
  implements agl.b
{
  private String m = "ChooseCustomAction";
  private Context n;
  private RecyclerView o;
  private agl p;
  private ArrayList<aiw.a> q = new ArrayList();
  private SharedPreferences r;
  private int s = 0;
  
  public ChooseCustomActionSettings() {}
  
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
      if (this.q != null)
      {
        localObject = new aiw.a();
        ((aiw.a)localObject).a = getString(2131689593);
        ((aiw.a)localObject).c = "";
        this.q.add(0, localObject);
      }
      if ((this.q == null) || (this.q.isEmpty())) {
        runOnUiThread(new Runnable()
        {
          public final void run()
          {
            Toast.makeText(ChooseCustomActionSettings.a(ChooseCustomActionSettings.this), 2131689512, 1).show();
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
        Toast.makeText(ChooseCustomActionSettings.a(ChooseCustomActionSettings.this), 2131689512, 1).show();
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
      localA = (aiw.a)this.q.get(paramInt);
      paramInt = this.s;
      switch (this.s)
      {
      case 4: 
        this.r.edit().putString("custom_action_4", localA.c).apply();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      aiw.a localA;
      localThrowable.getMessage();
      return;
    }
    this.r.edit().putString("custom_action_3", localA.c).apply();
    return;
    this.r.edit().putString("custom_action_2", localA.c).apply();
    return;
    this.r.edit().putString("custom_action_1", localA.c).apply();
    return;
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    overridePendingTransition(2130771993, 2130772000);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427372);
    if (f().a() != null) {
      f().a().a(true);
    }
    this.n = getApplicationContext();
    this.r = PreferenceManager.getDefaultSharedPreferences(this.n);
    this.o = ((RecyclerView)findViewById(2131296457));
    this.o.setLayoutManager(new LinearLayoutManager(this));
    paramBundle = getIntent();
    if (paramBundle != null)
    {
      this.s = paramBundle.getIntExtra("extra_custom_action", 0);
      int i = this.s;
    }
    new a((byte)0).execute(new Void[0]);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 16908332) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    onBackPressed();
    return true;
  }
  
  final class a
    extends AsyncTask<Void, Void, ArrayList<aiw.a>>
  {
    private ahj b;
    
    private a() {}
    
    protected final void onPreExecute()
    {
      super.onPreExecute();
      this.b = new ahj(ChooseCustomActionSettings.this);
      this.b.setCancelable(false);
      this.b.show();
    }
  }
}
