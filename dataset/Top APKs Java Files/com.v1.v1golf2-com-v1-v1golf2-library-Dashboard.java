package com.v1.v1golf2.library;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.flurry.android.FlurryAgent;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dashboard
  extends BaseActivity
{
  private static boolean t;
  private Boolean A = Boolean.valueOf(false);
  private Display B;
  private RelativeLayout C;
  private Boolean D = Boolean.valueOf(false);
  private Dialog E;
  private int F = 0;
  private Boolean G = Boolean.valueOf(true);
  private String H = "";
  private String I = "";
  private String J;
  private String K;
  private int L;
  private NotificationManager M;
  String o = "0";
  String p = "0";
  String q;
  String r;
  protected nu s = new nu(this);
  private ImageView u;
  private SharedPreferences v;
  private SharedPreferences.Editor w;
  private V1GolfLib x;
  private GridView y;
  private Boolean z;
  
  public Dashboard() {}
  
  private View a(View paramView, int paramInt, Integer[] paramArrayOfInteger, float paramFloat)
  {
    Object localObject = (TextView)paramView.findViewById(pj.be);
    if (localObject != null) {
      switch (paramInt)
      {
      }
    }
    for (;;)
    {
      localObject = (ImageView)paramView.findViewById(pj.bd);
      if (localObject != null) {}
      try
      {
        ((ImageView)localObject).setImageResource(paramArrayOfInteger[paramInt].intValue());
        ((ImageView)localObject).setLayoutParams(new LinearLayout.LayoutParams((int)paramFloat, (int)paramFloat));
        return paramView;
      }
      catch (OutOfMemoryError paramArrayOfInteger)
      {
        ((ImageView)localObject).setImageResource(pi.af);
        return paramView;
      }
      catch (Exception paramArrayOfInteger)
      {
        paramArrayOfInteger.printStackTrace();
      }
      if (getPackageName().equals("com.v1.v1pro"))
      {
        ((TextView)localObject).setText(getString(pm.iu));
      }
      else
      {
        ((TextView)localObject).setText(getString(pm.dR));
        continue;
        if (getPackageName().equals("com.v1.v1pro"))
        {
          ((TextView)localObject).setText(getString(pm.dA));
        }
        else
        {
          ((TextView)localObject).setText(getString(pm.gB));
          continue;
          ((TextView)localObject).setText(getString(pm.dL));
          continue;
          ((TextView)localObject).setText(getString(pm.bE));
          continue;
          ((TextView)localObject).setText(getString(pm.dp));
          continue;
          if (getPackageName().equals("com.v1.v1pro")) {
            ((TextView)localObject).setText(getString(pm.ix));
          } else {
            ((TextView)localObject).setText(getString(pm.gA));
          }
        }
      }
    }
    return paramView;
  }
  
  public static boolean j()
  {
    return t;
  }
  
  private ArrayList l()
  {
    localArrayList = new ArrayList();
    try
    {
      List localList = getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        if (i >= localList.size()) {
          return localArrayList;
        }
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if (localPackageInfo.versionName != null)
        {
          jc localJc = new jc(this);
          jc.a(localJc, localPackageInfo.packageName);
          localArrayList.add(localJc);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception localException) {}
  }
  
  public final String[] a(String paramString)
  {
    int i = 0;
    int j = this.v.getInt(paramString + "_size", 0);
    String[] arrayOfString = new String[j];
    for (;;)
    {
      if (i >= j) {
        return arrayOfString;
      }
      arrayOfString[i] = this.v.getString(paramString + "_" + i, null);
      i += 1;
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getKeyCode() == 84) {
      return false;
    }
    try
    {
      boolean bool = super.dispatchKeyEvent(paramKeyEvent);
      return bool;
    }
    catch (Exception paramKeyEvent) {}
    return false;
  }
  
  public final String k()
  {
    Object localObject1 = AccountManager.get(this).getAccountsByType("com.google");
    Object localObject2 = new LinkedList();
    int j = localObject1.length;
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        if ((((List)localObject2).isEmpty()) || (((List)localObject2).get(0) == null)) {
          break label128;
        }
        localObject1 = (String)((List)localObject2).get(0);
        localObject2 = ((String)localObject1).split("@");
        if ((localObject2.length <= 0) || (localObject2[0] == null)) {
          break;
        }
        if (localObject2[1].equals("gmail.com")) {
          localObject1 = localObject2[0];
        }
        return localObject1;
      }
      ((List)localObject2).add(localObject1[i].name);
      i += 1;
    }
    return null;
    label128:
    return null;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    b();
    try
    {
      a(this.u);
      if (this.B.getWidth() > this.B.getHeight())
      {
        this.L = 3;
        this.y.setNumColumns(this.L);
        this.u.setImageDrawable(getResources().getDrawable(pi.c));
        return;
      }
      this.L = 2;
      this.y.setNumColumns(this.L);
      this.u.setImageDrawable(getResources().getDrawable(pi.b));
      return;
    }
    catch (Exception paramConfiguration)
    {
      for (;;) {}
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((Build.VERSION.SDK_INT >= 11) && (!ig.b.contains(getPackageName()))) {
      setTheme(16973931);
    }
    this.v = PreferenceManager.getDefaultSharedPreferences(this);
    this.w = this.v.edit();
    t = true;
    this.x = ((V1GolfLib)getApplication());
    this.r = getPackageName();
    this.I = this.x.d();
    this.x.b();
    boolean bool1 = this.v.getBoolean("UseInternal", false);
    boolean bool2 = this.v.getBoolean("MergedSD", false);
    if ((!Boolean.valueOf(bool1).booleanValue()) && (!Boolean.valueOf(bool2).booleanValue()) && (Build.VERSION.SDK_INT >= 19)) {
      startActivity(new Intent(this, MergeData.class));
    }
    this.M = ((NotificationManager)getSystemService("notification"));
    paramBundle = getIntent().getExtras();
    if ((paramBundle != null) && (paramBundle.getString("FromNotify").equals("true"))) {
      a(getIntent());
    }
    Log.d("V1", Build.PRODUCT);
    Log.d("V1", "myDirectory=" + this.I);
    setContentView(pk.o);
    bv.a(this);
    this.B = ((WindowManager)getSystemService("window")).getDefaultDisplay();
    this.C = ((RelativeLayout)findViewById(pj.dE));
    this.C.setVisibility(8);
    ((ImageView)findViewById(pj.dz)).setVisibility(8);
    ((TextView)findViewById(pj.dD)).setTextColor(-1);
    this.u = ((ImageView)findViewById(pj.N));
    this.y = ((GridView)findViewById(pj.aV));
    this.y.setOnTouchListener(new iq(this));
    if (this.B.getWidth() > this.B.getHeight())
    {
      this.L = 3;
      this.y.setNumColumns(this.L);
      this.u.setImageDrawable(getResources().getDrawable(pi.c));
    }
    for (;;)
    {
      FlurryAgent.onPageView();
      this.u.setVisibility(0);
      if (Build.VERSION.SDK_INT < 11) {
        this.C.setVisibility(0);
      }
      getWindow().setSoftInputMode(3);
      this.z = Boolean.valueOf(this.v.getBoolean("PaidFlag", true));
      this.y.setAdapter(new iz(this));
      this.y.setOnItemLongClickListener(new ir(this));
      this.y.setOnItemClickListener(new is(this));
      return;
      this.L = 2;
      this.y.setNumColumns(this.L);
      this.u.setImageDrawable(getResources().getDrawable(pi.b));
    }
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    return new AlertDialog.Builder(this).setTitle(pm.da).setMessage(pm.cY).setCancelable(false).setPositiveButton(pm.db, new it(this)).setNegativeButton(pm.cZ, new iu(this)).create();
  }
  
  protected void onDestroy()
  {
    if ((this.E != null) && (this.E.isShowing())) {
      this.E.dismiss();
    }
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    int i = 0;
    List localList;
    if (paramInt == 4)
    {
      paramKeyEvent = new ArrayList();
      localList = ((ActivityManager)getSystemService("activity")).getRunningAppProcesses();
      paramInt = 0;
      if (paramInt < localList.size()) {
        break label80;
      }
      int j = paramKeyEvent.size();
      paramInt = i;
      label54:
      if (paramInt < j) {
        break label127;
      }
      if (!this.A.booleanValue()) {
        break label162;
      }
      finish();
    }
    for (;;)
    {
      bool = true;
      return bool;
      label80:
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(paramInt);
      jc localJc = new jc(this);
      jc.a(localJc, localRunningAppProcessInfo.processName);
      paramKeyEvent.add(localJc);
      paramInt += 1;
      break;
      label127:
      if (jc.a((jc)paramKeyEvent.get(paramInt)).equals("kr.co.sorf.allinonegolf")) {
        this.A = Boolean.valueOf(true);
      }
      paramInt += 1;
      break label54;
      label162:
      paramKeyEvent = new Intent("android.intent.action.MAIN");
      paramKeyEvent.addCategory("android.intent.category.HOME");
      paramKeyEvent.setFlags(268435456);
      startActivity(paramKeyEvent);
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    t = false;
  }
  
  protected void onResume()
  {
    this.y.invalidate();
    t = true;
    this.o = this.v.getString("LoggedInUser", "0");
    this.p = this.v.getString("LoggedInUser_ISA", "0");
    try
    {
      new ja(this, (byte)0).execute(new URL[0]);
      if (getPackageName().equals("com.v1.v1sports")) {}
      try
      {
        new ix(this, (byte)0).execute(new URL[0]);
        if (getPackageName().equals("com.v1.v1pro")) {
          b();
        }
        super.onResume();
        return;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public boolean onSearchRequested()
  {
    this.o = this.v.getString("LoggedInUser", "0");
    this.p = this.v.getString("LoggedInUser_ISA", "0");
    if ((this.o.equals("0")) && (this.p.equals("0")))
    {
      Toast.makeText(getApplicationContext(), getString(pm.fq), 0).show();
      return false;
    }
    return super.onSearchRequested();
  }
  
  public void onStart()
  {
    super.onStart();
    FlurryAgent.onStartSession(this, getString(pm.dg));
  }
  
  public void onStop()
  {
    try
    {
      super.onStop();
      FlurryAgent.onEndSession(this);
      return;
    }
    catch (Exception localException) {}
  }
}
