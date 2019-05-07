package com.lookout.ui.v2;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.lookout.StatusSettingsCore;
import com.lookout.ao;
import com.lookout.d.j;
import com.lookout.l.e;
import com.lookout.security.ah;
import com.lookout.security.progressbar.ScanViewPager;
import com.lookout.types.h;
import com.lookout.ui.components.g;
import com.lookout.utils.eo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityActivity
  extends g
  implements LoaderManager.LoaderCallbacks
{
  private static final org.a.b b = org.a.c.a(SecurityActivity.class);
  private LayoutInflater c;
  private final Handler d = new Handler(com.lookout.c.e.l.b());
  private e e;
  private ViewFlipper f;
  private ListView g;
  private ListView h;
  private ViewFlipper i;
  private ViewGroup j;
  private ViewGroup k;
  private ViewGroup l;
  private boolean m = false;
  private View n;
  private ProgressBar o;
  private LinearLayout p;
  private ScanViewPager q;
  private TextView r;
  private TextView s;
  private ah t = ah.a();
  private final com.lookout.l.d u = new ci(this);
  
  public SecurityActivity() {}
  
  private void a(int paramInt)
  {
    if (this.q == null)
    {
      this.p = ((LinearLayout)this.c.inflate(2130903134, null, false));
      this.q = ((ScanViewPager)this.p.findViewById(2131689690));
      a(this.p, com.lookout.androidsecurity.h.a.d.b);
      this.q.startAnimation(AnimationUtils.loadAnimation(d(), 2130968583));
    }
    if ((com.lookout.security.progressbar.a)this.q.getAdapter() == null)
    {
      com.lookout.security.progressbar.a localA = new com.lookout.security.progressbar.a(d(), 60);
      this.q.setAdapter(localA);
    }
    if (this.i.indexOfChild(this.p) == -1) {
      this.i.addView(this.p);
    }
    this.i.setDisplayedChild(this.i.indexOfChild(this.p));
    this.r.setVisibility(8);
    this.q.d(paramInt);
  }
  
  private void a(View paramView, com.lookout.androidsecurity.h.a.d paramD)
  {
    Object localObject = paramView.findViewById(2131689823);
    paramView = (TextView)((View)localObject).findViewById(2131690150);
    paramD = (ImageView)((View)localObject).findViewById(2131690148);
    localObject = (TextView)((View)localObject).findViewById(2131690149);
    paramView.setText("");
    paramD.setImageResource(2130837939);
    ((TextView)localObject).setText(b());
  }
  
  private void k()
  {
    ImageView localImageView = (ImageView)this.h.findViewById(2131690011);
    TextView localTextView1 = (TextView)this.h.findViewById(2131690012);
    TextView localTextView2 = (TextView)this.h.findViewById(2131690013);
    localImageView.setImageResource(2130837941);
    localTextView1.setText(2131166211);
    localTextView2.setText(new j(this).a(2131166158));
    this.f.setDisplayedChild(this.f.indexOfChild(this.h));
  }
  
  private void l()
  {
    Button localButton = (Button)findViewById(2131690014);
    localButton.setBackgroundResource(2130837834);
    localButton.setText(2131166070);
    localButton.setContentDescription(getString(2131166070));
    localButton.setTextColor(getResources().getColor(2131623974));
    localButton.setOnClickListener(new cl(this));
  }
  
  private void m()
  {
    Button localButton = (Button)findViewById(2131690014);
    localButton.setBackgroundResource(2130837837);
    localButton.setText(2131166152);
    localButton.setContentDescription(getString(2131166152));
    localButton.setTextColor(getResources().getColor(2131623961));
    localButton.setOnClickListener(new cm(this));
  }
  
  private void n()
  {
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    localHashMap.put("icon", Integer.valueOf(2130837942));
    localHashMap.put("text", getString(2131166154, new Object[] { Integer.valueOf(getPackageManager().getInstalledPackages(0).size()) }));
    localHashMap.put("subtext", getString(2131166088));
    localArrayList.add(localHashMap);
    localHashMap = new HashMap();
    localHashMap.put("icon", Integer.valueOf(2130837940));
    localHashMap.put("text", getString(2131166002));
    localHashMap.put("subtext", new j(this).a(2131166141));
    localArrayList.add(localHashMap);
    localHashMap = new HashMap();
    localHashMap.put("icon", Integer.valueOf(2130837943));
    localHashMap.put("text", getString(2131166194));
    localHashMap.put("subtext", getString(2131166150));
    localArrayList.add(localHashMap);
    this.h.setAdapter(new com.lookout.ui.components.f(this, localArrayList));
  }
  
  private void o()
  {
    if (this.j == null)
    {
      this.j = ((ViewGroup)this.c.inflate(2130903171, this.i, false));
      localObject = (ImageView)this.j.findViewById(2131690148);
      TextView localTextView1 = (TextView)this.j.findViewById(2131690149);
      TextView localTextView2 = (TextView)this.j.findViewById(2131689989);
      ((ImageView)localObject).setImageResource(2130837939);
      localTextView1.setText(b());
      localTextView2.setText(new j(this).a(2131166158));
    }
    if (this.i.indexOfChild(this.j) == -1) {
      this.i.addView(this.j);
    }
    this.i.setDisplayedChild(this.i.indexOfChild(this.j));
    Object localObject = new cn(this);
    eo.a(this, this.j, com.lookout.security.e.a.c.a, (View.OnClickListener)localObject);
  }
  
  private void p()
  {
    TextView localTextView1;
    if (this.k == null)
    {
      this.k = ((ViewGroup)this.c.inflate(2130903167, this.i, false));
      a(this.k, com.lookout.androidsecurity.h.a.d.d);
      this.o = ((ProgressBar)this.k.findViewById(2131689963));
      localTextView1 = (TextView)this.k.findViewById(2131689824);
      this.s = ((TextView)this.k.findViewById(2131689964));
      localTextView1.setText(d().getString(2131165519));
    }
    if (this.i.indexOfChild(this.k) == -1) {
      this.i.addView(this.k);
    }
    this.i.setDisplayedChild(this.i.indexOfChild(this.k));
    if (this.l == null)
    {
      this.l = ((ViewGroup)this.c.inflate(2130903182, null));
      localTextView1 = (TextView)this.l.findViewById(2131690044);
      TextView localTextView2 = (TextView)this.l.findViewById(2131690045);
      ImageView localImageView = (ImageView)this.l.findViewById(2131690043);
      localTextView1.setText(2131166151);
      localTextView2.setText(getString(2131166153, new Object[] { com.lookout.utils.v.a(System.currentTimeMillis()) }));
      localImageView.setImageResource(2130837969);
      localImageView.setBackgroundDrawable(null);
    }
    if (!this.m)
    {
      s();
      r();
      this.g.addHeaderView(this.l);
      this.g.setAdapter(this.e);
      this.m = true;
    }
    this.r.setVisibility(8);
  }
  
  private void q()
  {
    p();
    t();
    this.o.setProgress(0);
    this.r.setVisibility(0);
    o();
  }
  
  private void r()
  {
    this.g.addHeaderView(this.n);
  }
  
  private void s()
  {
    this.g.removeHeaderView(this.n);
    this.g.removeHeaderView(this.l);
    this.g.setAdapter(null);
  }
  
  private void t()
  {
    if (this.i.indexOfChild(this.k) != -1) {
      this.i.removeView(this.k);
    }
    if (this.m)
    {
      this.g.removeHeaderView(this.l);
      this.e.notifyDataSetChanged();
      this.m = false;
    }
    this.r.setVisibility(0);
  }
  
  public int a()
  {
    return 2130903197;
  }
  
  public void a(android.support.v4.a.o paramO, Cursor paramCursor)
  {
    this.e.changeCursor(paramCursor);
  }
  
  public int b()
  {
    return 2131166157;
  }
  
  public void i()
  {
    Object localObject = com.lookout.androidsecurity.h.a.c.i();
    com.lookout.androidsecurity.h.a.d localD = ((com.lookout.androidsecurity.h.a.c)localObject).m();
    if (((com.lookout.androidsecurity.h.a.c)localObject).d())
    {
      if ((com.lookout.androidsecurity.h.a.d.d == localD) && (((com.lookout.androidsecurity.h.a.c)localObject).k() > 0))
      {
        p();
        i1 = (int)(((com.lookout.androidsecurity.h.a.c)localObject).g() * 100.0F);
        localObject = new StringBuffer().append(i1).append("%");
        this.s.setText(d().getString(2131165213, new Object[] { ((StringBuffer)localObject).toString() }));
        this.o.setProgress(i1);
      }
      while (com.lookout.androidsecurity.h.a.d.b != localD)
      {
        int i1;
        return;
      }
      a(((com.lookout.androidsecurity.h.a.c)localObject).j());
      return;
    }
    q();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.c = getLayoutInflater();
    this.f = ((ViewFlipper)findViewById(2131689897));
    this.g = ((ListView)findViewById(2131689711));
    this.h = com.lookout.ui.v2.components.d.a(this);
    this.n = this.c.inflate(2130903198, null);
    r();
    this.i = ((ViewFlipper)this.g.findViewById(2131690182));
    ((TextView)this.g.findViewById(2131690042)).setText(2131166137);
    this.r = ((TextView)this.g.findViewById(2131690041));
    this.r.setOnClickListener(new ck(this));
    o();
    this.e = new e(this, null);
    this.g.setAdapter(this.e);
  }
  
  public android.support.v4.a.o onCreateLoader(int paramInt, Bundle paramBundle)
  {
    return new com.lookout.l.f(this);
  }
  
  public void onLoaderReset(android.support.v4.a.o paramO)
  {
    this.e.changeCursor(null);
  }
  
  protected void onPause()
  {
    super.onPause();
    t();
    com.lookout.c.b.a.a().c(this);
  }
  
  protected void onResume()
  {
    super.onResume();
    com.lookout.c.b.a.a().b(this);
    i();
  }
  
  @com.e.a.l
  public void onSecurityEvent(com.lookout.x.a.o paramO)
  {
    i();
  }
  
  protected void onStart()
  {
    int i1 = 1;
    super.onStart();
    com.lookout.b.f.a().b(this);
    Object localObject = com.lookout.v.b();
    for (;;)
    {
      try
      {
        localObject = ((ao)localObject).d().getAVSetting();
        h localH = h.b;
        if (localObject != localH) {
          continue;
        }
      }
      catch (com.lookout.c.d localD)
      {
        b.d("Unable to load status settings", localD);
        continue;
        k();
        l();
        n();
        continue;
      }
      if (i1 == 0) {
        continue;
      }
      this.f.setDisplayedChild(this.f.indexOfChild(this.g));
      getSupportLoaderManager().initLoader(10, null, this).k();
      com.lookout.l.b.a().a(this.u);
      return;
      i1 = 0;
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    com.lookout.b.f.a().a(this);
    com.lookout.l.b.a().b(this.u);
  }
}
