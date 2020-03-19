package com.david.android.languageswitch.ui;

import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.Application;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.app.n;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaControllerCompat.h;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import com.david.android.languageswitch.alarmservice.AlarmReceiver;
import com.david.android.languageswitch.d.c;
import com.david.android.languageswitch.d.e.a;
import com.david.android.languageswitch.d.e.b;
import com.david.android.languageswitch.download.DownloadService;
import com.david.android.languageswitch.download.DownloadService.b;
import com.david.android.languageswitch.model.Story;
import com.david.android.languageswitch.model.UpdateItem;
import com.david.android.languageswitch.utils.IabHelper;
import com.david.android.languageswitch.utils.IabHelper.IabAsyncInProgressException;
import com.david.android.languageswitch.utils.IabHelper.a;
import com.david.android.languageswitch.utils.IabHelper.c;
import com.david.android.languageswitch.utils.IabHelper.d;
import com.david.android.languageswitch.utils.IabHelper.e;
import com.david.android.languageswitch.utils.aa;
import com.david.android.languageswitch.utils.d.b;
import com.david.android.languageswitch.utils.j;
import com.david.android.languageswitch.utils.j.a;
import com.david.android.languageswitch.utils.k;
import com.david.android.languageswitch.utils.l;
import com.david.android.languageswitch.utils.q;
import com.david.android.languageswitch.utils.r;
import com.david.android.languageswitch.utils.x.d;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.perf.metrics.AppStartTrace;
import io.fabric.sdk.android.services.b.i;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity
  extends b
  implements View.OnClickListener, g.a, t.b, v.a, j.a, x.d
{
  private static final String i = com.david.android.languageswitch.utils.m.a(MainActivity.class);
  private static String j = "IS_PREVIEW";
  private Menu A;
  private boolean B;
  IabHelper e;
  j f;
  IabHelper.c g = new IabHelper.c()
  {
    private void a(q paramAnonymousQ)
    {
      try
      {
        MainActivity.this.e.a(paramAnonymousQ, new IabHelper.a()
        {
          public void a(q paramAnonymous2Q, k paramAnonymous2K)
          {
            paramAnonymous2Q = Story.listAll(Story.class).iterator();
            while (paramAnonymous2Q.hasNext())
            {
              paramAnonymous2K = (Story)paramAnonymous2Q.next();
              if (paramAnonymous2K.isPaid())
              {
                paramAnonymous2K.setPaymentMade(true);
                paramAnonymous2K.save();
              }
            }
            c.a(MainActivity.this, e.b.y, e.a.bx, "", 0L);
            MainActivity.a(MainActivity.this, "unlock_all_current");
          }
        });
        return;
      }
      catch (IabHelper.IabAsyncInProgressException paramAnonymousQ)
      {
        paramAnonymousQ.printStackTrace();
      }
    }
    
    public void a(k paramAnonymousK, q paramAnonymousQ)
    {
      com.david.android.languageswitch.utils.m.b(MainActivity.G(), new Object[] { "Purchase finished: " + paramAnonymousK + ", purchase: " + paramAnonymousQ });
      if (paramAnonymousK.b())
      {
        if ((!"unlock_all_current".equals(paramAnonymousQ.c())) && (!"all_premium".equals(paramAnonymousQ.c())) && (!"all_premium_plus".equals(paramAnonymousQ.c())) && (!"all_unlocked".equals(paramAnonymousQ.c())) && (!"no_ads".equals(paramAnonymousQ.c())) && (!"subscription_first_cheaper".equals(paramAnonymousQ.c()))) {
          break label150;
        }
        if (!"unlock_all_current".equals(paramAnonymousQ.c())) {
          break label138;
        }
        a(paramAnonymousQ);
      }
      label138:
      label150:
      do
      {
        return;
        MainActivity.a(MainActivity.this, paramAnonymousQ.c());
        return;
        paramAnonymousK = Story.find(Story.class, "sku = ?", new String[] { paramAnonymousQ.c() });
      } while (paramAnonymousK.isEmpty());
      paramAnonymousK = (Story)paramAnonymousK.get(0);
      c.a(MainActivity.this, e.b.y, e.a.aC, paramAnonymousK.getTitleId(), 0L);
      MainActivity.a(MainActivity.this, paramAnonymousQ, paramAnonymousK);
      MainActivity.this.b(paramAnonymousK);
      MainActivity.c(MainActivity.this).b();
    }
  };
  IabHelper.e h = new IabHelper.e()
  {
    public void a(k paramAnonymousK, l paramAnonymousL)
    {
      Log.d(MainActivity.G(), "Query inventory finished.");
      if (MainActivity.this.e == null) {
        return;
      }
      if (paramAnonymousK.c())
      {
        com.david.android.languageswitch.utils.b.a(MainActivity.this, "Failed to query inventory: " + paramAnonymousK);
        return;
      }
      Log.d(MainActivity.G(), "Query inventory was successful.");
      paramAnonymousK = paramAnonymousL.a();
      boolean bool2;
      if ((paramAnonymousK != null) && (!paramAnonymousK.isEmpty()))
      {
        paramAnonymousK = paramAnonymousK.iterator();
        bool1 = false;
        bool2 = false;
        if (paramAnonymousK.hasNext())
        {
          paramAnonymousL = (q)paramAnonymousK.next();
          Object localObject = Story.find(Story.class, "sku = ?", new String[] { paramAnonymousL.c() });
          if (!((List)localObject).isEmpty())
          {
            localObject = (Story)((List)localObject).get(0);
            MainActivity.this.b((Story)localObject);
          }
          if (paramAnonymousL.c().equals("no_ads"))
          {
            MainActivity.d(MainActivity.this);
            MainActivity.b(MainActivity.this).n(true);
            bool2 = true;
          }
          if (paramAnonymousL.c().equals("all_unlocked"))
          {
            MainActivity.d(MainActivity.this);
            bool1 = true;
          }
          if (paramAnonymousL.c().equals("all_premium"))
          {
            MainActivity.d(MainActivity.this);
            bool1 = true;
            bool2 = true;
          }
          if (paramAnonymousL.c().equals("all_premium_plus"))
          {
            MainActivity.d(MainActivity.this);
            bool1 = true;
            bool2 = true;
          }
          if ((!paramAnonymousL.c().equals("subscription_free_trial")) && (!paramAnonymousL.c().equals("subscription_first_cheaper")) && (!paramAnonymousL.c().equals("subscription_2"))) {
            break label390;
          }
          MainActivity.d(MainActivity.this);
          bool2 = true;
        }
      }
      boolean bool3;
      for (boolean bool1 = true;; bool1 = bool3)
      {
        MainActivity.b(MainActivity.this).p(bool2);
        MainActivity.b(MainActivity.this).n(bool1);
        if ((bool2) || (bool1)) {
          c.a(MainActivity.this, "buyer", 6);
        }
        bool3 = bool1;
        bool1 = bool2;
        bool2 = bool3;
        break;
        MainActivity.c(MainActivity.this).b();
        Log.d(MainActivity.G(), "Initial inventory query finished; enabling main UI.");
        return;
        label390:
        bool3 = bool2;
        bool2 = bool1;
      }
    }
  };
  private com.david.android.languageswitch.c.a k;
  private int l;
  private DownloadService m;
  private BroadcastReceiver n;
  private ServiceConnection o;
  private boolean p;
  private h q;
  private a r;
  private int s;
  private s t;
  private f u;
  private z v;
  private d w;
  private ao x;
  private ab y;
  private b z;
  
  public MainActivity() {}
  
  private void H()
  {
    if ((!this.k.Z()) && (System.currentTimeMillis() - this.k.at() > 21600000L)) {
      com.david.android.languageswitch.utils.d.a(this, new d.b()
      {
        public void b_()
        {
          com.david.android.languageswitch.utils.d.a(MainActivity.this);
        }
      });
    }
  }
  
  private void I()
  {
    if ((!isFinishing()) && (!ah()))
    {
      if (!this.B) {
        break label55;
      }
      this.B = false;
      this.u = new f(this, true, new f.a()
      {
        public void a()
        {
          c.a(MainActivity.this, e.b.J, e.a.eg, "", 0L);
          MainActivity.a(MainActivity.this);
        }
      });
      this.u.show();
    }
    label55:
    do
    {
      return;
      List localList = K();
      if (!localList.isEmpty())
      {
        this.x = new ao(this, localList);
        this.x.show();
        return;
      }
      if (L())
      {
        if (com.david.android.languageswitch.utils.b.g(this))
        {
          this.y = new ab(this);
          this.y.show();
          return;
        }
        J();
        return;
      }
    } while (!M());
    this.u = new f(this, false, null);
    this.u.show();
  }
  
  private void J()
  {
    this.t = new s(this, new s.b()
    {
      public void a()
      {
        c.a(MainActivity.this, e.b.G, e.a.eM, "", 0L);
        MainActivity.this.m();
      }
      
      public void a(String paramAnonymousString, MainActivity.b paramAnonymousB)
      {
        MainActivity.this.a(paramAnonymousString, paramAnonymousB);
        MainActivity.b(MainActivity.this).i(0);
      }
    }, false);
    this.t.show();
  }
  
  private List<UpdateItem> K()
  {
    Object localObject = UpdateItem.listAll(UpdateItem.class);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      UpdateItem localUpdateItem = (UpdateItem)((Iterator)localObject).next();
      if (!localUpdateItem.isSeenByUser()) {
        if (com.david.android.languageswitch.utils.z.a(new String[] { localUpdateItem.getUpdateText() })) {
          localArrayList.add(localUpdateItem);
        }
      }
    }
    return localArrayList;
  }
  
  private boolean L()
  {
    if (!com.david.android.languageswitch.utils.b.e(this.k)) {
      if ((com.david.android.languageswitch.utils.z.a(new String[] { this.k.J(), this.k.K() })) && ((!com.david.android.languageswitch.utils.b.b(this.k)) || (!com.david.android.languageswitch.utils.b.d(this.k))) && (this.k.w() >= this.k.P())) {
        return true;
      }
    }
    return false;
  }
  
  private boolean M()
  {
    if (com.david.android.languageswitch.utils.z.a(new String[] { this.k.O() })) {
      return (!f.a(this.k)) || ((!com.david.android.languageswitch.utils.b.c(this.k)) && (!this.k.q()));
    }
    return false;
  }
  
  private void N()
  {
    e.a localA;
    if (getIntent().hasExtra("ITEM_JUST_BOUGHT_SKU"))
    {
      localA = null;
      if (getIntent().getStringExtra("ITEM_JUST_BOUGHT_SKU").equals("no_ads")) {
        localA = e.a.bu;
      }
      if (getIntent().getStringExtra("ITEM_JUST_BOUGHT_SKU").equals("all_unlocked")) {
        localA = e.a.bv;
      }
      if (getIntent().getStringExtra("ITEM_JUST_BOUGHT_SKU").equals("all_premium")) {
        localA = e.a.ei;
      }
      if (getIntent().getStringExtra("ITEM_JUST_BOUGHT_SKU").equals("all_premium_plus")) {
        localA = e.a.dl;
      }
      if (!getIntent().getStringExtra("ITEM_JUST_BOUGHT_SKU").equals("subscription_first_cheaper")) {
        break label293;
      }
      localA = e.a.co;
    }
    label286:
    label293:
    for (;;)
    {
      e.b localB;
      StringBuilder localStringBuilder;
      if (localA != null)
      {
        localB = e.b.y;
        localStringBuilder = new StringBuilder();
        if (this.k == null) {
          break label286;
        }
      }
      for (String str = com.david.android.languageswitch.utils.b.h(this.k);; str = "")
      {
        c.a(this, localB, localA, str + O(), 0L);
        c.a(this, e.b.L, localA, String.valueOf(this.k.ay()) + " paragraphs finished", 0L);
        c.a(this, e.b.L, localA, String.valueOf(this.k.az()) + " started tracks", 0L);
        c(getIntent().getStringExtra("ITEM_JUST_BOUGHT_SKU"));
        getIntent().removeExtra("ITEM_JUST_BOUGHT_SKU");
        return;
      }
    }
  }
  
  private String O()
  {
    if (this.z != null) {
      return this.z.name();
    }
    if (getIntent().getStringExtra("LAST_PREMIUM_SOURCE") != null)
    {
      String str = getIntent().getStringExtra("LAST_PREMIUM_SOURCE");
      getIntent().removeExtra("LAST_PREMIUM_SOURCE");
      return str;
    }
    return "";
  }
  
  private void P()
  {
    if (!this.k.m())
    {
      startActivityForResult(OnBoardingTutorialActivity.a(this), 911);
      overridePendingTransition(0, 0);
    }
  }
  
  private boolean Q()
  {
    Iterator localIterator = getApplication().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      String str = ((ApplicationInfo)localIterator.next()).packageName;
      if ((str.contains("cc.madkite.freedom")) || (str.contains("madkite.freedom")) || (str.contains("com.dimonvideo.luckypatcher")) || (str.contains("com.chelpus.lackypatch")) || (str.contains("com.blackmartalpha")) || (str.contains("org.blackmart.market")) || (str.contains("com.allinone.free")) || (str.contains("com.repodroid.app")) || (str.contains("com.baseappfull.fwd")) || (str.contains("com.zmapp")) || (str.contains("com.dv.marketmod.installer")) || (str.contains("org.mobilism.android")) || (str.contains("org.creeplays.hack")) || (str.contains("com.android.vending.billing.InAppBillingService.LACK"))) {
        return true;
      }
    }
    return false;
  }
  
  private boolean R()
  {
    return (i.g(this)) && (Q());
  }
  
  private void S()
  {
    findViewById(2131952062).setOnClickListener(this);
    findViewById(2131952065).setOnClickListener(this);
    findViewById(2131952068).setOnClickListener(this);
  }
  
  private void T()
  {
    this.n = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        float f = paramAnonymousIntent.getFloatExtra("STORY_DOWNLOADED_PROGRESS", 0.0F);
        paramAnonymousContext = paramAnonymousIntent.getStringExtra("STORY_DOWNLOADED_NAME");
        MainActivity.c(MainActivity.this).a(f, paramAnonymousContext);
        MainActivity.e(MainActivity.this).a(f);
        if (f == -1.0F)
        {
          if ((MainActivity.b(MainActivity.this, paramAnonymousContext)) && (MainActivity.f(MainActivity.this) != null) && (MainActivity.f(MainActivity.this).a() != null)) {
            MainActivity.this.r();
          }
          MainActivity.c(MainActivity.this, paramAnonymousContext);
        }
      }
    };
    android.support.v4.a.d.a(this).a(this.n, new IntentFilter("com.david.android.languageswitch.download.DOWNLOAD_PROGRESS"));
    this.o = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        paramAnonymousComponentName = (DownloadService.b)paramAnonymousIBinder;
        MainActivity.a(MainActivity.this, paramAnonymousComponentName.a());
        MainActivity.a(MainActivity.this, true);
        if (MainActivity.g(MainActivity.this) != null)
        {
          MainActivity.g(MainActivity.this).a();
          MainActivity.a(MainActivity.this, null);
        }
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        MainActivity.a(MainActivity.this, false);
      }
    };
    if (!this.p) {
      bindService(new Intent(this, DownloadService.class), this.o, 1);
    }
  }
  
  private void U()
  {
    W();
    ac();
  }
  
  private void V()
  {
    Y();
    ad();
  }
  
  private void W()
  {
    b(2131952064, 2131952063, 2130837896);
    a(2131952070, 2131952069, 2130837762);
    b(2131952067, 2131952066, 2130837752);
  }
  
  private void X()
  {
    b(2131952064, 2131952063, 2130837896);
    b(2131952070, 2131952069, 2130837763);
    a(2131952067, 2131952066, 2130837751);
  }
  
  private void Y()
  {
    a(2131952064, 2131952063, 2130837895);
    b(2131952070, 2131952069, 2130837763);
    b(2131952067, 2131952066, 2130837752);
  }
  
  private void Z()
  {
    switch (this.l)
    {
    default: 
      return;
    case 0: 
      V();
      return;
    case 1: 
      A();
      return;
    }
    U();
  }
  
  public static PendingIntent a(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, MainActivity.class);
    localIntent.addFlags(67108864);
    return PendingIntent.getActivity(paramContext, 1001, localIntent, 134217728);
  }
  
  public static PendingIntent a(Context paramContext, int paramInt, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramContext, MainActivity.class);
    localIntent.setFlags(536870912);
    localIntent.putExtra("com.david.android.languageswitch.EXTRA_START_FULLSCREEN", true);
    localIntent.putExtra(j, paramBoolean);
    return PendingIntent.getActivity(paramContext, paramInt, localIntent, 268435456);
  }
  
  private void a(int paramInt1, int paramInt2, int paramInt3)
  {
    ((TextView)findViewById(paramInt1)).setTextColor(android.support.v4.a.b.c(this, 2131820783));
    ((TextView)findViewById(paramInt1)).setTextSize(0, getResources().getDimension(2131493008));
    ((ImageView)findViewById(paramInt2)).setImageDrawable(android.support.v4.a.b.a(this, paramInt3));
  }
  
  private void a(q paramQ, Story paramStory)
  {
    Product localProduct = new Product();
    localProduct.setPrice(0.5D);
    localProduct.setName(paramStory.getTitleId());
    localProduct.setQuantity(1);
    localProduct.setId(paramStory.getSku());
    c.a(this, paramQ, localProduct);
    c(paramStory.getSku());
  }
  
  private void a(GoogleSignInResult paramGoogleSignInResult)
  {
    Log.d(i, "handleSignInResult:" + paramGoogleSignInResult.isSuccess());
    if (paramGoogleSignInResult.isSuccess())
    {
      paramGoogleSignInResult = paramGoogleSignInResult.getSignInAccount();
      this.k.h(paramGoogleSignInResult.getEmail());
    }
  }
  
  private void a(Calendar paramCalendar, PendingIntent paramPendingIntent, int paramInt)
  {
    ((AlarmManager)getSystemService("alarm")).setRepeating(1, paramCalendar.getTimeInMillis(), 86400000L * paramInt, paramPendingIntent);
  }
  
  private void aa()
  {
    this.l = 1;
    t localT = af();
    localT.a(this);
    localT.a(this);
    android.support.v4.app.t localT1 = getSupportFragmentManager().a();
    localT1.b(2131951868, localT, "LIBRARY_FRAGMENT_TAG");
    localT1.a(null);
    localT1.d();
  }
  
  private void ab()
  {
    Snackbar.a(findViewById(2131951869), 2131427600, 0).a(2131427467, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.c(MainActivity.this).c(true);
      }
    }).b();
  }
  
  private void ac()
  {
    this.l = 2;
    v localV = ag();
    android.support.v4.app.t localT = getSupportFragmentManager().a();
    localT.b(2131951868, localV, "MORE_FRAGMENT_TAG");
    localT.a(null);
    localT.d();
  }
  
  private void ad()
  {
    this.l = 0;
    w localW = ae();
    localW.a(this);
    android.support.v4.app.t localT = getSupportFragmentManager().a();
    localT.b(2131951868, localW, "MY_STORIES_FRAGMENT_TAG");
    localT.a(null);
    localT.d();
  }
  
  private w ae()
  {
    if (getSupportFragmentManager().a("MY_STORIES_FRAGMENT_TAG") != null) {
      return (w)getSupportFragmentManager().a("MY_STORIES_FRAGMENT_TAG");
    }
    return new w();
  }
  
  private t af()
  {
    if (getSupportFragmentManager().a("LIBRARY_FRAGMENT_TAG") != null) {
      return (t)getSupportFragmentManager().a("LIBRARY_FRAGMENT_TAG");
    }
    return new t();
  }
  
  private v ag()
  {
    if (getSupportFragmentManager().a("MORE_FRAGMENT_TAG") != null) {
      return (v)getSupportFragmentManager().a("MORE_FRAGMENT_TAG");
    }
    return new v();
  }
  
  private boolean ah()
  {
    return a(new Dialog[] { this.v, this.u, this.t, this.x, this.w, this.y });
  }
  
  private boolean ai()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (aj() != null)
    {
      bool1 = bool2;
      if (aj().b() != null)
      {
        bool1 = bool2;
        if (aj().b().a() == 3) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private MediaControllerCompat aj()
  {
    return MediaControllerCompat.a(this);
  }
  
  private void ak()
  {
    new x(this).show();
  }
  
  private void al()
  {
    if (com.david.android.languageswitch.utils.b.i(this)) {}
    for (String str = "logged_in";; str = "not_logged_in")
    {
      c.a(3, str, this);
      return;
    }
  }
  
  private void am()
  {
    if (getIntent().hasExtra("COMES_FROM_PREMIUM_CHEAPER_PROMO_NOTIFICATION"))
    {
      this.k.x(true);
      this.k.f(System.currentTimeMillis());
      this.B = true;
    }
  }
  
  private void an()
  {
    if (getIntent().hasExtra("COMES_FROM_NOTIFICATION"))
    {
      c.a(this, e.b.z, e.a.bc, "", 0L);
      getIntent().removeExtra("COMES_FROM_NOTIFICATION");
    }
    if (getIntent().hasExtra("COMES_FROM_NEW_STORY_NOTIFICATION"))
    {
      c.a(this, e.b.z, e.a.cp, "", 0L);
      getIntent().removeExtra("COMES_FROM_NEW_STORY_NOTIFICATION");
    }
    if (getIntent().hasExtra("COMES_FROM_BRING_THEM_BACK_NOTIFICATION"))
    {
      c.a(this, e.b.z, e.a.cq, "", 0L);
      getIntent().removeExtra("COMES_FROM_BRING_THEM_BACK_NOTIFICATION");
    }
    if (getIntent().hasExtra("COMES_FROM_PREMIUM_CHEAPER_PROMO_NOTIFICATION"))
    {
      c.a(this, e.b.z, e.a.dX, "", 0L);
      getIntent().removeExtra("COMES_FROM_PREMIUM_CHEAPER_PROMO_NOTIFICATION");
    }
  }
  
  public static Intent b(Context paramContext)
  {
    return new Intent(paramContext, MainActivity.class);
  }
  
  private void b(int paramInt1, int paramInt2, int paramInt3)
  {
    ((TextView)findViewById(paramInt1)).setTextColor(android.support.v4.a.b.c(this, 2131820570));
    ((TextView)findViewById(paramInt1)).setTextSize(0, getResources().getDimension(2131493009));
    ((ImageView)findViewById(paramInt2)).setImageDrawable(android.support.v4.a.b.a(this, paramInt3));
  }
  
  private void b(String paramString)
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("ITEM_JUST_BOUGHT_SKU", paramString);
    if (this.z != null) {}
    for (paramString = this.z.name();; paramString = "")
    {
      localIntent.putExtra("LAST_PREMIUM_SOURCE", paramString);
      finish();
      startActivity(localIntent);
      return;
    }
  }
  
  public static Intent c(Context paramContext)
  {
    paramContext = b(paramContext);
    paramContext.putExtra("COMES_FROM_BRING_THEM_BACK_NOTIFICATION", true);
    return paramContext;
  }
  
  private void c(Intent paramIntent)
  {
    boolean bool2 = false;
    if ((paramIntent != null) && (paramIntent.getBooleanExtra("com.david.android.languageswitch.EXTRA_START_FULLSCREEN", false)))
    {
      MediaControllerCompat localMediaControllerCompat = aj();
      boolean bool1 = bool2;
      if (localMediaControllerCompat != null)
      {
        bool1 = bool2;
        if (localMediaControllerCompat.c() != null)
        {
          bool1 = bool2;
          if (localMediaControllerCompat.c().a() != null)
          {
            bool1 = bool2;
            if (aa.a(this, localMediaControllerCompat.c().a().b().toString())) {
              bool1 = true;
            }
          }
        }
      }
      startActivity(FullScreenPlayerActivity.a(this, paramIntent, bool1));
    }
  }
  
  private void c(String paramString)
  {
    if ((this.k.ac() == 0L) && (this.k.ab() > 0L))
    {
      this.k.d(System.currentTimeMillis());
      paramString = "";
    }
    try
    {
      long l1 = this.k.ab();
      long l2 = this.k.ac();
      l1 = TimeUnit.MILLISECONDS.toDays(l1 - l2);
      paramString = String.valueOf(l1);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    c.a(this, e.b.v, e.a.cP, paramString, 0L);
  }
  
  private void d(String paramString)
  {
    if ((e(paramString)) && (aj() != null) && (aj().a() != null)) {
      aj().a().c();
    }
  }
  
  private boolean e(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!com.david.android.languageswitch.utils.z.b(new String[] { paramString }))
    {
      bool1 = bool2;
      if (aj() != null)
      {
        bool1 = bool2;
        if (aj().c() != null)
        {
          bool1 = bool2;
          if (aj().c().a() != null) {
            bool1 = paramString.equals(r.a(aj().c().a().a()));
          }
        }
      }
    }
    return bool1;
  }
  
  public void A()
  {
    X();
    aa();
  }
  
  public String B()
  {
    t localT = af();
    if (localT == null) {
      return null;
    }
    return localT.e();
  }
  
  public void C()
  {
    if (this.a != null)
    {
      if (com.david.android.languageswitch.utils.b.h(this))
      {
        this.a.setVisible(true);
        this.a.setTitle(getString(2131427581) + ' ' + this.k.ap());
      }
    }
    else {
      return;
    }
    this.a.setVisible(false);
  }
  
  public void D()
  {
    com.david.android.languageswitch.utils.m.b(i, new Object[] { "Received broadcast notification. Querying inventory." });
    try
    {
      this.e.a(this.h);
      return;
    }
    catch (IabHelper.IabAsyncInProgressException localIabAsyncInProgressException)
    {
      com.david.android.languageswitch.utils.b.a(this, "Error querying inventory. Another async operation in progress.");
    }
  }
  
  public boolean E()
  {
    return af().isVisible();
  }
  
  public void F()
  {
    J();
  }
  
  protected void a(Bundle paramBundle)
  {
    if (paramBundle == null)
    {
      c(getIntent());
      this.l = 1;
      return;
    }
    this.l = paramBundle.getInt("SHOWN_FRAGMENT");
  }
  
  public void a(Story paramStory)
  {
    if ((!e(paramStory.getTitleId())) || (!ai()))
    {
      paramStory.deleteFiles(this, false);
      ae().b();
      af().d();
      d(paramStory.getTitleId());
      return;
    }
    com.david.android.languageswitch.utils.b.a(getApplicationContext(), getString(2131427481));
  }
  
  public void a(Story paramStory, Pair<View, String>[] paramArrayOfPair)
  {
    if ((this.k.k()) && (aa.b(this, paramStory))) {
      aa.a(this, paramStory);
    }
    do
    {
      return;
      this.w = null;
      this.w = this.q.a(paramStory, false, this, this.m);
    } while (this.w == null);
    this.w.show();
  }
  
  public void a(CharSequence paramCharSequence)
  {
    com.david.android.languageswitch.utils.m.b(i, new Object[] { "Setting toolbar title to ", paramCharSequence });
    if (paramCharSequence == null) {
      getString(2131427793);
    }
  }
  
  public void a(String paramString)
  {
    if (!R())
    {
      Log.d(i, "Launching purchase flow for gas.");
      try
      {
        if (this.e != null)
        {
          if (!"subscription_first_cheaper".equals(paramString))
          {
            this.e.a(this, paramString, 10001, this.g, "");
            return;
          }
          this.e.b(this, paramString, 10001, this.g, "");
          return;
        }
      }
      catch (IabHelper.IabAsyncInProgressException paramString)
      {
        com.david.android.languageswitch.utils.b.a(this, "Error launching purchase flow. Another async operation in progress.");
      }
    }
  }
  
  public void a(String paramString, b paramB)
  {
    this.z = paramB;
    a(paramString);
  }
  
  public void a(boolean paramBoolean)
  {
    if (!paramBoolean) {
      af().a(false);
    }
    af().b(paramBoolean);
  }
  
  void b(Story paramStory)
  {
    paramStory.setPaymentMade(true);
    paramStory.save();
  }
  
  public void b(final Story paramStory, Pair<View, String>... paramVarArgs)
  {
    new Handler().post(new Runnable()
    {
      public void run()
      {
        if (Story.find(Story.class, "title_Id = ?", new String[] { paramStory.getTitleId() }).isEmpty()) {
          com.david.android.languageswitch.utils.g.a(paramStory);
        }
      }
    });
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (Build.VERSION.SDK_INT >= 21) {
      if (!com.david.android.languageswitch.utils.b.b(this))
      {
        localObject1 = localObject2;
        if (!com.david.android.languageswitch.utils.b.c(this)) {}
      }
      else
      {
        localObject1 = ActivityOptions.makeSceneTransitionAnimation(this, paramVarArgs).toBundle();
      }
    }
    startActivityForResult(StoryDetailsActivity.a(this, paramStory.getTitleId()), 100, (Bundle)localObject1);
  }
  
  public void c(final Story paramStory)
  {
    if (!ah())
    {
      this.v = new z(this, paramStory, new z.a()
      {
        public void a()
        {
          c.a(MainActivity.this, e.b.d, e.a.l, paramStory.getTitleId(), 0L);
        }
        
        public void a(String paramAnonymousString)
        {
          MainActivity.i(MainActivity.this).a(MainActivity.this, paramStory, paramAnonymousString, MainActivity.h(MainActivity.this));
        }
        
        public void a(String paramAnonymousString1, String paramAnonymousString2, int paramAnonymousInt)
        {
          MainActivity.b(MainActivity.this).a(paramAnonymousString1);
          MainActivity.b(MainActivity.this).b(paramAnonymousString2);
          MainActivity.b(MainActivity.this).e(1);
          aa.a(MainActivity.this, paramAnonymousString1, paramAnonymousString2, paramAnonymousInt, paramStory);
        }
      });
      this.v.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface) {}
      });
      this.v.setOnDismissListener(new DialogInterface.OnDismissListener()
      {
        public void onDismiss(DialogInterface paramAnonymousDialogInterface) {}
      });
      this.v.show();
    }
  }
  
  public void d(Story paramStory)
  {
    new g(this, paramStory, this).show();
  }
  
  public void e(Story paramStory)
  {
    this.q.a(this, paramStory, this.m);
  }
  
  public void f(Story paramStory)
  {
    new ap(this, paramStory).show();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == 2468) && ((this.m == null) || (!this.m.a())))
    {
      this.r = new a(paramIntent.getStringExtra("LANGUAGE_TO_DOWNLOAD"), paramIntent.getStringExtra("STORY_TO_DOWNLOAD_OR_BUY"), paramIntent.getBooleanExtra("FORCE_SHOW_DIALOG", false));
      if (this.m != null)
      {
        this.r.a();
        this.r = null;
      }
      if (paramInt1 != 198) {
        break label198;
      }
      paramIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(paramIntent);
      if (paramIntent != null) {
        a(paramIntent);
      }
    }
    label198:
    do
    {
      return;
      if (paramInt2 == 7732)
      {
        List localList = Story.find(Story.class, "title_Id = ?", new String[] { paramIntent.getStringExtra("STORY_TO_DOWNLOAD_OR_BUY") });
        if (localList.isEmpty()) {
          break;
        }
        a(((Story)localList.get(0)).getSku());
        break;
      }
      if (paramInt2 != 2469) {
        break;
      }
      a(paramIntent.getStringExtra("SKU_TO_BUY"), b.c);
      break;
      if (paramInt1 == 911)
      {
        C();
        af().c(true);
        return;
      }
    } while (this.e == null);
    this.e.a(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    finish();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131952062: 
      c.a(this, e.b.b, e.a.N, "", 0L);
      V();
      return;
    case 2131952065: 
      c.a(this, e.b.b, e.a.P, "", 0L);
      A();
      return;
    }
    c.a(this, e.b.b, e.a.O, "", 0L);
    U();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    AppStartTrace.setLauncherActivityOnCreateTime("com.david.android.languageswitch.ui.MainActivity");
    super.onCreate(paramBundle);
    setContentView(2130968604);
    c.a(this, getIntent());
    com.david.android.languageswitch.utils.m.b(i, new Object[] { "Activity onCreate" });
    this.k = new com.david.android.languageswitch.c.a(this);
    P();
    this.q = new h(this);
    h();
    a(paramBundle);
    Z();
    S();
    com.google.firebase.a.a(this);
    i().setVisibility(8);
    MobileAds.initialize(getApplicationContext(), getString(2131427783));
    com.david.android.languageswitch.a.a.a(this);
    w();
    Crashlytics.log("createdMainActivity");
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    this.A = paramMenu;
    getMenuInflater().inflate(2132017156, paramMenu);
    a(paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.f != null) {
      unregisterReceiver(this.f);
    }
    Log.d(i, "Destroying helper.");
    if (this.e != null)
    {
      this.e.b();
      this.e = null;
    }
  }
  
  public boolean onMenuOpened(int paramInt, Menu paramMenu)
  {
    this.s += 1;
    if ((this.s == 3) && (paramMenu != null)) {
      paramMenu.add(0, 5, 0, 2131427846);
    }
    return super.onMenuOpened(paramInt, paramMenu);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    com.david.android.languageswitch.utils.m.b(i, new Object[] { "onNewIntent, intent=" + paramIntent });
    a(null);
    c(paramIntent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((paramMenuItem != null) && (paramMenuItem.getItemId() == 16908332))
    {
      onBackPressed();
      return true;
    }
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      if (!ah())
      {
        new an(this).show();
        continue;
        a(this);
        continue;
        ak();
        continue;
        com.david.android.languageswitch.utils.b.a(this, getString(2131427573, new Object[] { this.k.ap() }));
        this.k.C("");
        this.k.D("");
        this.k.E("");
        this.a.setVisible(false);
        com.facebook.login.m.a().b();
        com.david.android.languageswitch.utils.d.b(this);
        continue;
        startActivityForResult(l(), 198);
      }
    }
  }
  
  protected void onResume()
  {
    AppStartTrace.setLauncherActivityOnResumeTime("com.david.android.languageswitch.ui.MainActivity");
    Crashlytics.log("resumed MainActivity");
    super.onResume();
    H();
    am();
    an();
    I();
    C();
    al();
    u();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    String str = B();
    if (str != null) {
      paramBundle.putString("com.david.android.languageswitch.MEDIA_ID", str);
    }
    paramBundle.putInt("SHOWN_FRAGMENT", this.l);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    AppStartTrace.setLauncherActivityOnStartTime("com.david.android.languageswitch.ui.MainActivity");
    super.onStart();
    T();
  }
  
  protected void onStop()
  {
    super.onStop();
    if (((this.p) && (this.m == null)) || ((this.m != null) && (!this.m.a()))) {}
    try
    {
      unbindService(this.o);
      this.p = false;
      android.support.v4.a.d.a(this).a(this.n);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        localIllegalArgumentException.printStackTrace();
      }
    }
  }
  
  protected void p()
  {
    af().f();
  }
  
  public void w()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    localCalendar.set(11, 18);
    localCalendar.set(12, 15);
    a(localCalendar, PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmReceiver.class), 0), 1);
  }
  
  public void x()
  {
    y();
  }
  
  void y()
  {
    if (this.e == null)
    {
      Log.d(i, "Creating IAB helper.");
      this.e = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArFtkW6/B2qEV0b5oM2ZeaR6jr8E1UZppFxYkXo+xhGYt5nRljInuvpo+y4rVXtO4+jt+Gnn+6q+9ZMKNYxfc+edmsn1abqHL2xHWGztRqBjLoMGnsyN/y7fePY6KRJ7TC2T/fWff+Cd6G0defzXs21hn2rNbmkqFl1jOju6dNxcYQIJ6LdB5HhDdA1/epn8D9KBxi4R/8VSJS01nVsKDg7yoVR+9fWvz9LjqdFWvOtSvwjmgks7xFu3vD3zXqpOSgstf/YXY1KIVBBZXRQ1iRYn0IY68iGALdCNg4UfltqoypVtc6yHj7E0sLr6elheiNw0V8NXK0vg6tcJkMnXPFwIDAQAB");
      this.e.a(false);
      Log.d(i, "Starting setup.");
      this.e.a(new IabHelper.d()
      {
        public void a(k paramAnonymousK)
        {
          Log.d(MainActivity.G(), "Setup finished.");
          if (!paramAnonymousK.b()) {
            com.david.android.languageswitch.utils.b.a(MainActivity.this, "Problem setting up in-app billing: " + paramAnonymousK);
          }
          while (MainActivity.this.e == null) {
            return;
          }
          MainActivity.this.f = new j(MainActivity.this);
          paramAnonymousK = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
          MainActivity.this.registerReceiver(MainActivity.this.f, paramAnonymousK);
          Log.d(MainActivity.G(), "Setup successful. Querying inventory.");
          try
          {
            MainActivity.this.e.a(MainActivity.this.h);
            return;
          }
          catch (IabHelper.IabAsyncInProgressException paramAnonymousK)
          {
            com.david.android.languageswitch.utils.b.a(MainActivity.this, "Error querying inventory. Another async operation in progress.");
          }
        }
      });
    }
  }
  
  public void z()
  {
    ab();
  }
  
  private class a
  {
    private final String b;
    private final String c;
    private final boolean d;
    
    a(String paramString1, String paramString2, boolean paramBoolean)
    {
      this.b = paramString1;
      this.c = paramString2;
      this.d = paramBoolean;
    }
    
    public void a()
    {
      Object localObject = Story.find(Story.class, "title_Id = ?", new String[] { this.c });
      if (!((List)localObject).isEmpty())
      {
        localObject = (Story)((List)localObject).get(0);
        if (this.b != null) {
          break label126;
        }
        c.a(MainActivity.this, e.b.n, e.a.Y, "", 0L);
        MainActivity.a(MainActivity.this, null);
        MainActivity.a(MainActivity.this, MainActivity.i(MainActivity.this).a((Story)localObject, this.d, MainActivity.this, MainActivity.h(MainActivity.this)));
        if (MainActivity.j(MainActivity.this) != null) {
          MainActivity.j(MainActivity.this).show();
        }
      }
      return;
      label126:
      c.a(MainActivity.this, e.b.n, e.a.Z, "", 0L);
      MainActivity.i(MainActivity.this).a(MainActivity.this, (Story)localObject, this.b, MainActivity.h(MainActivity.this));
    }
  }
  
  public static enum b
  {
    private b() {}
  }
}
