package com.luutinhit.activity;

import agi;
import agi.a;
import agj;
import agj.a;
import agq;
import agr;
import ahj;
import aie;
import aiw;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.w;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import di;
import ic;
import id;
import id.a;
import ie;
import ig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import ji;
import no;
import no.a;

public class CustomizeControls
  extends agr
  implements agi.a, agj.a, agq
{
  private static Handler m = new Handler();
  private SharedPreferences A;
  private ArrayList<String> B = new ArrayList(Arrays.asList(new String[] { "com.luutinhit.controlcenter.control_flashlight", "com.luutinhit.controlcenter.control_clock", "com.luutinhit.controlcenter.control_calculator", "com.luutinhit.controlcenter.control_camera" }));
  private ArrayList<String> C;
  private Runnable D = new Runnable()
  {
    public final void run()
    {
      CustomizeControls.a(CustomizeControls.this);
    }
  };
  private id E;
  private final int n = 500;
  private String o = "CustomizeControls";
  private Context p;
  private PackageManager q;
  private no r;
  private no s;
  private int t = 0;
  private RecyclerView u;
  private RecyclerView v;
  private ArrayList<aie> w = new ArrayList();
  private ArrayList<aie> x = new ArrayList();
  private agi y;
  private agj z;
  
  public CustomizeControls() {}
  
  private Drawable a(String paramString)
  {
    try
    {
      paramString = this.q.getApplicationIcon(paramString);
      if (paramString != null)
      {
        if (!(paramString instanceof BitmapDrawable)) {
          return paramString;
        }
        paramString = Bitmap.createScaledBitmap(((BitmapDrawable)paramString).getBitmap(), Math.round(paramString.getIntrinsicWidth() * 0.4F), Math.round(paramString.getIntrinsicHeight() * 0.4F), false);
        paramString = new BitmapDrawable(getResources(), paramString);
        return paramString;
      }
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return this.p.getResources().getDrawable(2131230887);
  }
  
  private LayerDrawable b(int paramInt)
  {
    Object localObject = ContextCompat.getDrawable(this, paramInt);
    localObject = new LayerDrawable(new Drawable[] { ContextCompat.getDrawable(this, 2131230870), localObject });
    ((LayerDrawable)localObject).setLayerInset(0, 0, 0, 0, 0);
    ((LayerDrawable)localObject).setLayerInset(1, aiw.a(6), aiw.a(6), aiw.a(6), aiw.a(6));
    return localObject;
  }
  
  private boolean b(String paramString)
  {
    try
    {
      boolean bool = this.A.getBoolean(paramString, false);
      return bool;
    }
    catch (Throwable paramString) {}
    return false;
  }
  
  private ArrayList<String> c(String paramString)
  {
    try
    {
      paramString = TextUtils.split(this.A.getString(paramString, TextUtils.join("‚‗‚", this.B)), "‚‗‚");
      Arrays.toString(paramString);
      paramString = new ArrayList(Arrays.asList(paramString));
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return this.B;
  }
  
  private void h()
  {
    if (this.C != null)
    {
      Iterator localIterator1 = this.C.iterator();
      while (localIterator1.hasNext())
      {
        String str = (String)localIterator1.next();
        if (this.w != null)
        {
          Iterator localIterator2 = this.w.iterator();
          while (localIterator2.hasNext())
          {
            aie localAie = (aie)localIterator2.next();
            if ((localAie != null) && (str != null) && (str.equals(localAie.c))) {
              this.x.add(localAie);
            }
          }
        }
      }
      if ((this.x != null) && (this.w != null)) {
        this.w.removeAll(this.x);
      }
      this.t = this.C.size();
    }
  }
  
  private ArrayList<aie> i()
  {
    for (;;)
    {
      try
      {
        if (this.w == null) {
          this.w = new ArrayList();
        }
        this.w.clear();
        Object localObject1 = this.p.getPackageManager();
        Iterator localIterator = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
        if (localIterator.hasNext())
        {
          Object localObject3 = (PackageInfo)localIterator.next();
          if ((((PackageManager)localObject1).getLaunchIntentForPackage(((PackageInfo)localObject3).packageName) == null) || (((PackageManager)localObject1).getLaunchIntentForPackage(((PackageInfo)localObject3).packageName).toString().isEmpty())) {
            continue;
          }
          Object localObject2 = ((PackageInfo)localObject3).applicationInfo.loadLabel((PackageManager)localObject1).toString();
          String str = ((PackageInfo)localObject3).packageName;
          localObject3 = a(((PackageInfo)localObject3).packageName);
          localObject3 = new LayerDrawable(new Drawable[] { ContextCompat.getDrawable(this, 2131230870), localObject3 });
          ((LayerDrawable)localObject3).setLayerInset(0, 0, 0, 0, 0);
          ((LayerDrawable)localObject3).setLayerInset(1, aiw.a(6), aiw.a(6), aiw.a(6), aiw.a(6));
          localObject2 = new aie(0, (String)localObject2, str, (LayerDrawable)localObject3);
          this.w.add(localObject2);
          continue;
        }
        Collections.sort(this.w, new Comparator() {});
        if (Build.VERSION.SDK_INT >= 21)
        {
          localObject1 = new aie(0, getString(2131689650), "com.luutinhit.controlcenter.control_screenshot", b(2131230956));
          this.w.add(0, localObject1);
        }
        if (Build.VERSION.SDK_INT >= 21)
        {
          localObject1 = new aie(0, getString(2131689620), "com.luutinhit.controlcenter.control_record", b(2131230944));
          this.w.add(0, localObject1);
        }
        localObject1 = new aie(0, getString(2131689572), "com.luutinhit.controlcenter.control_flashlight", b(2131230914));
        this.w.add(0, localObject1);
        localObject1 = new aie(0, getString(2131689534), "com.luutinhit.controlcenter.control_clock", b(2131230906));
        this.w.add(0, localObject1);
        localObject1 = new aie(0, getString(2131689524), "com.luutinhit.controlcenter.control_calculator", b(2131230902));
        this.w.add(0, localObject1);
        localObject1 = new aie(0, getString(2131689525), "com.luutinhit.controlcenter.control_camera", b(2131230903));
        this.w.add(0, localObject1);
        if (this.w == null) {
          continue;
        }
        if (!this.w.isEmpty()) {
          continue;
        }
      }
      catch (Throwable localThrowable2)
      {
        ArrayList localArrayList;
        continue;
        int i = 0;
        continue;
      }
      if (i >= this.w.size()) {
        continue;
      }
      ((aie)this.w.get(i)).a = i;
      i += 1;
    }
    runOnUiThread(new Runnable()
    {
      public final void run()
      {
        Toast.makeText(CustomizeControls.c(CustomizeControls.this), 2131689512, 1).show();
      }
    });
    try
    {
      h();
    }
    catch (Throwable localThrowable1)
    {
      localThrowable1.getMessage();
    }
    localArrayList = this.w;
    return localArrayList;
    runOnUiThread(new Runnable()
    {
      public final void run()
      {
        Toast.makeText(CustomizeControls.c(CustomizeControls.this), 2131689512, 1).show();
      }
    });
    return null;
  }
  
  public final void a(int paramInt, String paramString1, String paramString2, LayerDrawable paramLayerDrawable)
  {
    if ((paramString2.equals("com.luutinhit.controlcenter.control_record")) && (Build.VERSION.SDK_INT >= 21) && (!b("record_setup_success"))) {
      new id.a(this).a(2131689620).b(2131689555).a().a(17039370, new DialogInterface.OnClickListener()
      {
        public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          CustomizeControls.b(CustomizeControls.this);
        }
      }).b(17039360, null).b().show();
    }
    paramString1 = new aie(paramInt, paramString1, paramString2, paramLayerDrawable);
    if (this.t < 8)
    {
      this.t += 1;
      paramString2 = this.z;
      paramInt = this.t - 1;
      paramString2.b.add(paramInt, paramString1);
      paramString2.e(paramInt);
      this.y.b = this.t;
    }
    if (this.t == 8) {
      this.s.a(null);
    }
    m.removeCallbacks(this.D);
    m.postDelayed(this.D, 500L);
  }
  
  public final void a(RecyclerView.w paramW)
  {
    no localNo = this.r;
    if ((no.a.b(localNo.p)) && (paramW.c.getParent() == localNo.p))
    {
      localNo.a();
      localNo.h = 0.0F;
      localNo.g = 0.0F;
      localNo.a(paramW, 2);
    }
  }
  
  public final void b(int paramInt, String paramString1, String paramString2, LayerDrawable paramLayerDrawable)
  {
    paramString1 = new aie(paramInt, paramString1, paramString2, paramLayerDrawable);
    this.t -= 1;
    this.y.b = this.t;
    paramString2 = this.y;
    paramLayerDrawable = this.y;
    int j = paramInt;
    if (paramLayerDrawable.a != null)
    {
      int i = 0;
      for (;;)
      {
        j = paramInt;
        if (i >= paramLayerDrawable.a.size()) {
          break;
        }
        aie localAie = (aie)paramLayerDrawable.a.get(i);
        if (localAie != null)
        {
          j = 1;
          while (j < 9)
          {
            if (localAie.a == paramInt + j)
            {
              j = i;
              break label142;
            }
            j += 1;
          }
        }
        i += 1;
      }
    }
    label142:
    paramString2.a.add(j, paramString1);
    paramString2.e(j);
    if (this.t == 7) {
      this.s.a(this.u);
    }
    m.removeCallbacks(this.D);
    m.postDelayed(this.D, 500L);
  }
  
  public final void g()
  {
    m.removeCallbacks(this.D);
    m.postDelayed(this.D, 500L);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    overridePendingTransition(2130771993, 2130772000);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427360);
    paramBundle = f().a();
    if (paramBundle != null)
    {
      paramBundle.a(true);
      paramBundle.a(2131689558);
    }
    this.p = getApplicationContext();
    this.q = getPackageManager();
    this.A = ji.a(this.p);
    try
    {
      ((LinearLayout)findViewById(2131296451)).getLayoutTransition().enableTransitionType(4);
    }
    catch (Throwable paramBundle)
    {
      paramBundle.getMessage();
    }
    this.v = ((RecyclerView)findViewById(2131296455));
    this.u = ((RecyclerView)findViewById(2131296454));
    this.u.setNestedScrollingEnabled(false);
    paramBundle = new LinearLayoutManager(this);
    paramBundle.a(1);
    LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(this);
    localLinearLayoutManager.a(1);
    this.v.setLayoutManager(paramBundle);
    this.u.setLayoutManager(localLinearLayoutManager);
    this.v.setNestedScrollingEnabled(false);
    this.C = c("favorite_action_choose");
    new a((byte)0).execute(new Void[0]);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 16908332) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    di.a(this);
    overridePendingTransition(2130771993, 2130772000);
    return true;
  }
  
  public void onPause()
  {
    super.onPause();
    m.removeCallbacks(this.D);
    m.post(this.D);
  }
  
  public void onResume()
  {
    super.onResume();
    if ((Build.VERSION.SDK_INT <= 19) && (!b("show_alert_limitation")))
    {
      if (this.E == null)
      {
        id.a localA = new id.a(this).a(2131689586);
        String str;
        switch (Build.VERSION.SDK_INT)
        {
        default: 
          str = "Pre-LOLLIPOP";
          break;
        case 19: 
        case 20: 
          str = "KITKAT";
          break;
        case 16: 
        case 17: 
        case 18: 
          str = "JELLY BEAN";
        }
        this.E = localA.b(getString(2131689587, new Object[] { str })).a().a(17039370, new DialogInterface.OnClickListener()
        {
          public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            CustomizeControls.d(CustomizeControls.this).dismiss();
          }
        }).b();
      }
      this.E.show();
    }
  }
  
  final class a
    extends AsyncTask<Void, Void, ArrayList<aie>>
  {
    private ahj b;
    
    private a() {}
    
    protected final void onPreExecute()
    {
      super.onPreExecute();
      this.b = new ahj(CustomizeControls.this);
      this.b.setCancelable(false);
      this.b.show();
    }
  }
}
