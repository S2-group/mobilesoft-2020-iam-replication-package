package com.newsy.activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.newsy.application.NewsyApplication;
import com.newsy.c.a.b.e;
import com.newsy.c.a.b.g;
import com.newsy.c.a.c.h;
import com.newsy.d.A;
import com.newsy.d.D;
import com.newsy.d.r;
import com.newsy.d.s;
import com.newsy.service.NewsyService;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsyNavigationActivity
  extends k
{
  private NewsyApplication a;
  private DrawerLayout b;
  private ListView c;
  private ActionBarDrawerToggle d;
  private View e;
  private View f;
  private FrameLayout g;
  private com.newsy.i.o h = com.newsy.i.o.a();
  private ActionBar i;
  private int j;
  private List<String> k = new ArrayList();
  private boolean l = true;
  private boolean m = false;
  private boolean n = false;
  private int o;
  private int p = 0;
  private boolean q;
  private s r;
  private AdapterView.OnItemClickListener s = new p(this);
  
  public NewsyNavigationActivity() {}
  
  private static String a(Intent paramIntent)
  {
    String str = null;
    if (paramIntent.getAction().equals("android.intent.action.VIEW")) {
      str = paramIntent.getData().getPath().replace("/", "");
    }
    while (!paramIntent.getAction().equals("com.newsy.ACTION_NOTIFICATION_OPENED")) {
      return str;
    }
    return paramIntent.getExtras().getString("com.newsy.EXTRA_STORY_ID");
  }
  
  private void a(int paramInt)
  {
    this.p = paramInt;
    this.c.setItemChecked(paramInt, true);
    if (paramInt < 0) {}
    for (String str = com.newsy.e.a.c;; str = (String)this.k.get(paramInt))
    {
      e(str);
      this.b.closeDrawer(this.c);
      return;
    }
  }
  
  private void a(Fragment paramFragment, boolean paramBoolean)
  {
    this.g.removeAllViews();
    paramFragment = getSupportFragmentManager().beginTransaction().replace(2131099726, paramFragment).setTransition(4099);
    if (paramBoolean) {
      paramFragment.addToBackStack(null);
    }
    paramFragment.commitAllowingStateLoss();
    b(paramBoolean);
  }
  
  private void a(com.newsy.b.a.a.c paramC)
  {
    this.k.clear();
    this.k.add(com.newsy.e.a.a);
    this.k.add(com.newsy.e.a.b);
    this.k.add(com.newsy.e.a.d);
    this.k.add(com.newsy.e.a.g);
    paramC = paramC.iterator();
    while (paramC.hasNext())
    {
      com.newsy.b.a.a.d localD = (com.newsy.b.a.a.d)paramC.next();
      this.k.add(localD.b());
    }
    this.k.add(com.newsy.e.a.h);
    this.k.add(com.newsy.e.a.f);
    this.k.add(com.newsy.e.a.e);
    i();
    this.n = true;
  }
  
  private void b(boolean paramBoolean)
  {
    boolean bool = true;
    this.d.setDrawerIndicatorEnabled(false);
    this.b.setDrawerLockMode(1);
    ActionBarDrawerToggle localActionBarDrawerToggle = this.d;
    if (!paramBoolean) {}
    for (paramBoolean = bool;; paramBoolean = false)
    {
      localActionBarDrawerToggle.setDrawerIndicatorEnabled(paramBoolean);
      return;
    }
  }
  
  public static boolean b(String paramString)
  {
    return (!TextUtils.equals(paramString, com.newsy.e.a.a)) && (!TextUtils.equals(paramString, com.newsy.e.a.b)) && (!TextUtils.equals(paramString, com.newsy.e.a.d)) && (!TextUtils.equals(paramString, com.newsy.e.a.c)) && (!TextUtils.equals(paramString, com.newsy.e.a.e)) && (!TextUtils.equals(paramString, com.newsy.e.a.f)) && (!TextUtils.equals(paramString, com.newsy.e.a.g)) && (!TextUtils.equals(paramString, com.newsy.e.a.h));
  }
  
  private void c()
  {
    String str = a(getIntent());
    if (c(str)) {
      d(str);
    }
    for (;;)
    {
      com.newsy.d.a.k.a().show(getSupportFragmentManager(), null);
      return;
      e();
    }
  }
  
  private void c(boolean paramBoolean)
  {
    this.b.setDrawerLockMode(1);
    this.g.setVisibility(8);
    this.e.setVisibility(0);
    if (paramBoolean)
    {
      this.i.hide();
      this.f.setVisibility(0);
      return;
    }
    this.i.show();
    this.f.setVisibility(8);
  }
  
  private boolean c(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!TextUtils.isDigitsOnly(paramString))) {}
    do
    {
      return false;
      paramString = this.a.a(Integer.valueOf(Integer.parseInt(paramString)));
    } while ((!this.a.g()) && (!com.newsy.i.k.a().e(paramString)));
    return true;
  }
  
  private void d(String paramString)
  {
    this.q = true;
    if (NewsyApplication.b().g())
    {
      Intent localIntent = new Intent(NewsyApplication.b(), NewsyService.class);
      localIntent.setAction("com.newsy.service.NewsyService.FETCH_STORY_BY_STORY_ID");
      localIntent.putExtra("story_id", paramString);
      startService(localIntent);
      return;
    }
    startActivityForResult(com.google.a.c.a(Integer.valueOf(Integer.parseInt(paramString))), 372);
  }
  
  private static boolean d()
  {
    Iterator localIterator = NewsyApplication.b().getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals("com.android.vending")) {
        return true;
      }
    }
    return false;
  }
  
  private void e()
  {
    if (NewsyApplication.b().g()) {
      f();
    }
    do
    {
      return;
      if (g(com.newsy.e.a.d))
      {
        this.k.clear();
        this.k.add(com.newsy.e.a.d);
        this.k.add(com.newsy.e.a.h);
        this.k.add(com.newsy.e.a.e);
        i();
        h();
        this.n = false;
        this.c.setSelection(this.k.indexOf(com.newsy.e.a.d));
        this.c.performClick();
        k();
        return;
      }
    } while (b());
    a(true);
  }
  
  private void e(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.equals(com.newsy.e.a.a))
      {
        this.g.setPadding(0, 0, 0, 0);
        paramString = getResources().getDrawable(2130837601);
        this.i.setBackgroundDrawable(paramString);
        this.i.setIcon(2130837557);
        setTitle("");
      }
    }
    else {
      return;
    }
    this.g.setPadding(0, this.j, 0, 0);
    Drawable localDrawable = getResources().getDrawable(2130837694);
    this.i.setBackgroundDrawable(localDrawable);
    this.i.setIcon(2130837599);
    setTitle(paramString);
  }
  
  private void f()
  {
    Intent localIntent = new Intent(NewsyApplication.b(), NewsyService.class);
    localIntent.setAction("com.newsy.service.NewsyService.FETCH_CATEGORIES");
    startService(localIntent);
  }
  
  private void f(String paramString)
  {
    Intent localIntent = new Intent(NewsyApplication.b(), NewsyService.class);
    localIntent.setAction("com.newsy.service.NewsyService.FETCH_CATEGORY_STORIES");
    localIntent.putExtra("category", paramString);
    localIntent.putExtra("offset", 0);
    localIntent.putExtra("limit", 25);
    startService(localIntent);
  }
  
  private void g()
  {
    Intent localIntent = new Intent(NewsyApplication.b(), NewsyService.class);
    localIntent.setAction("com.newsy.service.NewsyService.FETCH_TOP_STORIES");
    localIntent.putExtra("offset", 0);
    localIntent.putExtra("limit", 25);
    startService(localIntent);
  }
  
  private static boolean g(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = NewsyApplication.b().g();
    boolean bool4 = paramString.equals(com.newsy.e.a.e);
    boolean bool3 = paramString.equals(com.newsy.e.a.d);
    if ((com.newsy.g.f.a().e()) && (NewsyApplication.b().b(com.newsy.e.a.d))) {}
    for (int i1 = 1;; i1 = 0)
    {
      if ((!bool1) && (!bool4))
      {
        bool1 = bool2;
        if (bool3)
        {
          bool1 = bool2;
          if (i1 == 0) {}
        }
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
  }
  
  private void h()
  {
    getActionBar().show();
    this.g.setVisibility(0);
    this.e.setVisibility(8);
    this.b.setDrawerLockMode(0);
  }
  
  private void i()
  {
    this.c = ((ListView)findViewById(2131099781));
    this.b = ((DrawerLayout)findViewById(2131099725));
    this.b.setDrawerShadow(2130837598, 8388611);
    this.c.setOnItemClickListener(this.s);
    this.c.setAdapter(new com.newsy.a.i(this, 2130903085, this.k));
    this.i.setDisplayHomeAsUpEnabled(true);
    this.i.setHomeButtonEnabled(true);
  }
  
  private void j()
  {
    Intent localIntent = new Intent(NewsyApplication.b(), NewsyService.class);
    localIntent.setAction("com.newsy.service.NewsyService.FETCH_TRENDING_STORIES");
    localIntent.putExtra("offset", 0);
    localIntent.putExtra("limit", 25);
    startService(localIntent);
  }
  
  private void k()
  {
    if (g(com.newsy.e.a.d)) {
      if (com.newsy.i.p.b())
      {
        localObject = new D();
        a((Fragment)localObject, false);
        i1 = this.k.indexOf(com.newsy.e.a.d);
        this.o = i1;
        a(i1);
      }
    }
    while (b()) {
      for (;;)
      {
        int i1;
        return;
        Object localObject = new A();
      }
    }
    a(false);
  }
  
  private boolean l()
  {
    return getSupportFragmentManager().getBackStackEntryCount() > 0;
  }
  
  private void m()
  {
    boolean bool = true;
    getSupportFragmentManager().popBackStackImmediate();
    int i1;
    if (!l())
    {
      i1 = 1;
      if (i1 != 0) {
        break label46;
      }
    }
    for (;;)
    {
      b(bool);
      if (i1 != 0) {
        a(this.o);
      }
      return;
      i1 = 0;
      break;
      label46:
      bool = false;
    }
  }
  
  private void n()
  {
    a(new r(), false);
    a(this.k.indexOf(com.newsy.e.a.e));
  }
  
  public final void a(String paramString)
  {
    boolean bool;
    if (b(paramString))
    {
      String str = this.a.f(paramString);
      if ((str == null) || (str.equals(this.k.get(this.o)))) {
        bool = true;
      }
    }
    for (;;)
    {
      if (bool)
      {
        h();
        this.r = s.a(paramString);
        a(this.r, false);
        a(this.o);
      }
      return;
      bool = false;
      continue;
      bool = paramString.equals(this.k.get(this.o));
    }
  }
  
  @Subscribe
  public void categoriesFetched(com.newsy.c.a.b.b paramB)
  {
    if ((this.m) && (this.k.size() > this.o))
    {
      paramB = (String)this.k.get(this.o);
      a(NewsyApplication.b().d());
      if (this.k.contains(paramB))
      {
        int i1 = this.k.indexOf(paramB);
        this.c.setSelection(i1);
        this.c.performClick();
        a(i1);
      }
    }
    for (;;)
    {
      this.m = false;
      return;
      a(NewsyApplication.b().d());
      g();
    }
  }
  
  @Subscribe
  public void categoryStoriesFetched(com.newsy.c.a.b.c paramC)
  {
    a(paramC.a);
  }
  
  @Subscribe
  public void connectivityChanged(com.newsy.c.a.c.b paramB)
  {
    if (paramB.a)
    {
      a();
      if ((!this.n) && (!this.m))
      {
        this.m = true;
        f();
      }
    }
  }
  
  @Subscribe
  public void deviceStoryClicked(com.newsy.c.a.c.c paramC)
  {
    if (NewsyApplication.b().g()) {
      if (paramC.b != null)
      {
        str = paramC.b;
        if (str != null) {
          startActivity(com.google.a.c.a(paramC.a.a(), str));
        }
      }
    }
    while (b()) {
      for (;;)
      {
        return;
        String str = this.r.a();
      }
    }
    a(false);
  }
  
  @Subscribe
  public void myQueueStoryClicked(com.newsy.c.a.c.a.d paramD)
  {
    startActivity(com.google.a.c.a(paramD.a.a(), com.newsy.e.a.d));
  }
  
  @Subscribe
  public void navigateToSettings(h paramH)
  {
    n();
  }
  
  @Subscribe
  public void noNetworkOkClicked(com.newsy.c.a.c.i paramI)
  {
    finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 372) {
      e();
    }
    while ((paramInt1 != 9000) || (paramInt2 != 0)) {
      return;
    }
    finish();
  }
  
  public void onBackPressed()
  {
    if (this.b.isDrawerOpen(this.c))
    {
      this.b.closeDrawer(this.c);
      return;
    }
    if (l())
    {
      m();
      return;
    }
    super.onBackPressed();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.d.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(8);
    requestWindowFeature(9);
    getActionBar().hide();
    setContentView(2130903063);
    this.a = NewsyApplication.b();
    this.b = ((DrawerLayout)findViewById(2131099725));
    this.c = ((ListView)findViewById(2131099781));
    this.e = findViewById(2131099727);
    this.f = this.e.findViewById(2131099782);
    this.g = ((FrameLayout)findViewById(2131099726));
    this.i = getActionBar();
    c(true);
    this.b.setDrawerShadow(2130837598, 8388611);
    this.d = new n(this, this, this.b, 2130837559, 2131427469, 2131427468);
    this.b.setDrawerListener(this.d);
    setTitle("");
    this.i.setIcon(2130837557);
    paramBundle = new TypedValue();
    getTheme().resolveAttribute(16843499, paramBundle, true);
    this.j = getResources().getDimensionPixelSize(paramBundle.resourceId);
    if (d())
    {
      i1 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
      if (i1 == 0) {
        break label286;
      }
      if (!GooglePlayServicesUtil.isUserRecoverableError(i1)) {
        break label279;
      }
      paramBundle = GooglePlayServicesUtil.getErrorDialog(i1, this, 9000);
      paramBundle.setOnDismissListener(new o(this));
      paramBundle.show();
    }
    label279:
    label286:
    for (int i1 = 0;; i1 = 1)
    {
      if (i1 != 0) {
        c();
      }
      return;
      finish();
      break;
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    paramIntent = a(paramIntent);
    if (c(paramIntent)) {
      d(paramIntent);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return this.d.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    com.newsy.c.a.a().unregister(this);
    a();
  }
  
  protected void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    if (this.d != null) {
      this.d.syncState();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    com.newsy.c.a.a().register(this);
    if (this.l) {
      this.l = false;
    }
    label73:
    for (;;)
    {
      this.q = false;
      return;
      if ((!this.q) && ((!this.n) || (!NewsyApplication.b().g()))) {}
      for (int i1 = 1;; i1 = 0)
      {
        if (i1 == 0) {
          break label73;
        }
        this.m = true;
        e();
        break;
      }
    }
  }
  
  @Subscribe
  public void refreshStoriesInCategory(com.newsy.c.a.c.c.a paramA)
  {
    if (NewsyApplication.b().g())
    {
      c(false);
      if (TextUtils.equals(paramA.a, com.newsy.e.a.a))
      {
        g();
        return;
      }
      if (TextUtils.equals(paramA.a, com.newsy.e.a.b))
      {
        j();
        return;
      }
      f(paramA.a);
      return;
    }
    a(false);
  }
  
  @Subscribe
  public void searchClosing(com.newsy.c.a.c.b.a paramA)
  {
    m();
  }
  
  @Subscribe
  public void searchInitiated(com.newsy.c.a.c.b.b paramB)
  {
    if (NewsyApplication.b().g())
    {
      paramB = paramB.a;
      localIntent = new Intent(NewsyApplication.b(), NewsyService.class);
      localIntent.setAction("com.newsy.service.NewsyService.FETCH_STORIES_BY_SEARCH_TERM");
      localIntent.putExtra("search_term", paramB);
      localIntent.putExtra("offset", 0);
      localIntent.putExtra("limit", 25);
      startService(localIntent);
      this.h.a(this, getString(2131427410), new q(this, localIntent));
    }
    while (b())
    {
      Intent localIntent;
      return;
    }
    a(false);
  }
  
  @Subscribe
  public void showQueueClicked(com.newsy.c.a.c.c.b paramB)
  {
    k();
  }
  
  @Subscribe
  public void showSearchClicked(com.newsy.c.a.c.c.c paramC)
  {
    if (NewsyApplication.b().g())
    {
      a(new com.newsy.d.n(), true);
      e(com.newsy.e.a.c);
      return;
    }
    a(false);
  }
  
  @Subscribe
  public void storyFetched(e paramE)
  {
    this.a.a(paramE.a);
    startActivity(com.google.a.c.a(paramE.a.a()));
  }
  
  @Subscribe
  public void topStoriesFetched(com.newsy.c.a.b.f paramF)
  {
    if (com.newsy.g.f.a().j())
    {
      com.newsy.g.f.a().k();
      com.newsy.d.a.p.a().show(getFragmentManager(), null);
    }
    a(com.newsy.e.a.a);
  }
  
  @Subscribe
  public void trendingStoriesFetched(g paramG)
  {
    a(com.newsy.e.a.b);
  }
}
