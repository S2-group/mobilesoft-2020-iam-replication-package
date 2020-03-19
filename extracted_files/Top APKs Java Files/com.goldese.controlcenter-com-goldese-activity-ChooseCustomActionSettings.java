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
import android.view.MenuItem;
import android.widget.Toast;
import bci;
import bci.b;
import bcx;
import bec.a;
import hn;
import hp;
import hr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChooseCustomActionSettings
  extends hp
  implements bci.b
{
  private String n = "ChooseCustomAction";
  private Context o;
  private RecyclerView p;
  private bci q;
  private ArrayList<bec.a> r = new ArrayList();
  private SharedPreferences s;
  private int t = 0;
  
  public ChooseCustomActionSettings() {}
  
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
          Toast.makeText(ChooseCustomActionSettings.a(ChooseCustomActionSettings.this), 2131230744, 1).show();
        }
      });
      return null;
    }
    Object localObject = new bec.a();
    ((bec.a)localObject).a = getString(2131230800);
    ((bec.a)localObject).c = "";
    this.r.add(0, localObject);
    if ((this.r == null) || (this.r.isEmpty())) {
      runOnUiThread(new Runnable()
      {
        public final void run()
        {
          Toast.makeText(ChooseCustomActionSettings.a(ChooseCustomActionSettings.this), 2131230744, 1).show();
        }
      });
    }
    localObject = this.r;
    return localObject;
  }
  
  public final void a_(int paramInt)
  {
    try
    {
      if (this.q != null) {
        this.q.d.b();
      }
      bec.a localA = (bec.a)this.r.get(paramInt);
      paramInt = this.t;
      switch (this.t)
      {
      case 1: 
        this.s.edit().putString("custom_action_1", localA.c).apply();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.getMessage();
      return;
    }
    this.s.edit().putString("custom_action_2", localThrowable.c).apply();
    return;
    this.s.edit().putString("custom_action_3", localThrowable.c).apply();
    return;
    this.s.edit().putString("custom_action_4", localThrowable.c).apply();
    return;
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    overridePendingTransition(2131034135, 2131034144);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968615);
    if (f().a() != null) {
      f().a().a(true);
    }
    this.o = getApplicationContext();
    this.s = PreferenceManager.getDefaultSharedPreferences(this.o);
    this.p = ((RecyclerView)findViewById(2131689659));
    this.p.setLayoutManager(new LinearLayoutManager(this));
    paramBundle = getIntent();
    if (paramBundle != null)
    {
      this.t = paramBundle.getIntExtra("extra_custom_action", 0);
      int i = this.t;
    }
    new a((byte)0).execute(new Void[0]);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    onBackPressed();
    return true;
  }
  
  final class a
    extends AsyncTask<Void, Void, ArrayList<bec.a>>
  {
    private bcx b;
    
    private a() {}
    
    protected final void onPreExecute()
    {
      super.onPreExecute();
      this.b = new bcx(ChooseCustomActionSettings.this);
      this.b.setCancelable(false);
      this.b.show();
    }
  }
}
