package com.rhythm.hexise.inst;

import aha;
import ahj;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bq;
import bwy;
import bwz;
import bxa;
import bxb;
import bxc;
import bxd;
import bxe;
import bxf;
import bxq;
import bxt;
import bxu;
import bxw;
import byl;
import byn;
import byo;
import byp;
import byq;
import byx;
import byy;
import bza;
import bzb;
import bzc;
import bzf;
import bzh;
import bzi;
import bzl;
import bzo;
import bzu;
import bzx;
import bzz;
import caa;
import cac;
import cad;
import cae;
import caf;
import cag;
import cah;
import com.crashlytics.android.Crashlytics;
import hb;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import ru;

@SuppressLint({"SetTextI18n"})
public abstract class BaseInstaller
  extends AppCompatActivity
  implements View.OnClickListener, byy, bza
{
  private static int B = Runtime.getRuntime().availableProcessors();
  private bzf A;
  private ThreadPoolExecutor C = new ThreadPoolExecutor(B, B, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.CallerRunsPolicy());
  private Handler D = new Handler(Looper.getMainLooper());
  private ahj E;
  private LayoutInflater F;
  private final byx G = new byx(this);
  private List i = new ArrayList();
  private Map j = new HashMap();
  private bzx k = null;
  private ListView l;
  private byp m;
  private byl n;
  private boolean o = false;
  private List p = new ArrayList();
  private TextView q;
  private TextView r;
  private TextView s;
  private int t = 0;
  private int u = 0;
  private byl v = null;
  private bzo w = null;
  private int x = 0;
  private bxu y;
  private SQLiteOpenHelper z;
  
  public BaseInstaller() {}
  
  @TargetApi(19)
  private void a(Uri paramUri)
  {
    try
    {
      getContentResolver().takePersistableUriPermission(paramUri, 3);
      return;
    }
    catch (Throwable paramUri)
    {
      Crashlytics.logException(paramUri);
    }
  }
  
  private void a(TextView paramTextView, boolean paramBoolean)
  {
    paramTextView.setEnabled(paramBoolean);
    if (paramBoolean) {}
    for (int i1 = -1;; i1 = getResources().getColor(caa.textDisabled))
    {
      paramTextView.setTextColor(i1);
      return;
    }
  }
  
  private void a(List paramList)
  {
    new bzl(this, paramList, new bxf(this, paramList)).show();
  }
  
  private boolean a(byl paramByl)
  {
    if (paramByl == null) {}
    for (;;)
    {
      return false;
      if (paramByl.j != byo.c) {
        try
        {
          PackageInfo localPackageInfo = getPackageManager().getPackageInfo(paramByl.f, 0);
          if (localPackageInfo != null)
          {
            int i1 = localPackageInfo.versionCode;
            int i2 = paramByl.i;
            if (i1 == i2) {
              return true;
            }
          }
        }
        catch (PackageManager.NameNotFoundException paramByl) {}
      }
    }
    return false;
  }
  
  private void b(byl paramByl)
  {
    if (paramByl != null) {}
    try
    {
      Uri localUri = Uri.fromFile(new File(paramByl.d));
      Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
      localIntent.setDataAndType(localUri, "application/vnd.android.package-archive");
      this.v = paramByl;
      startActivityForResult(localIntent, 0);
      return;
    }
    catch (Exception localException)
    {
      Log.e("com.rhmsoft.inst", "Error when installing apk file " + paramByl.d + ": ", localException);
      try
      {
        Toast.makeText(this, getString(cag.operation_failed) + "\n" + localException.getClass().getName() + ": " + localException.getMessage(), 1).show();
        return;
      }
      catch (Throwable paramByl)
      {
        Crashlytics.logException(paramByl);
      }
    }
  }
  
  private void b(List paramList)
  {
    this.i = paramList;
    this.m.a(this.i);
    this.m.notifyDataSetChanged();
    this.p.clear();
    l();
  }
  
  private void b(boolean paramBoolean)
  {
    int i2 = 1;
    int i1 = 0;
    int i3 = PreferenceManager.getDefaultSharedPreferences(this).getInt("sortType", 4);
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("sortAscend", true);
    if (this.x != i3)
    {
      PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("sortType", this.x).apply();
      i1 = 1;
    }
    if (bool != paramBoolean)
    {
      PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("sortAscend", paramBoolean).apply();
      i1 = i2;
    }
    for (;;)
    {
      if (i1 != 0)
      {
        Comparator localComparator = bzc.a(this.x, paramBoolean);
        Collections.sort(this.i, localComparator);
        Collections.sort(this.p, localComparator);
        this.m.a(this.i);
        this.m.notifyDataSetChanged();
      }
      return;
    }
  }
  
  private void c(List paramList)
  {
    int i1;
    if (this.j.size() == 0)
    {
      i1 = 1;
      paramList = paramList.iterator();
    }
    for (;;)
    {
      if (!paramList.hasNext()) {
        return;
      }
      byl localByl = (byl)paramList.next();
      if (i1 != 0)
      {
        localByl.j = byo.d;
        continue;
        i1 = 0;
        break;
      }
      if (this.j.containsKey(localByl.f))
      {
        int i2 = ((Integer)this.j.get(localByl.f)).intValue();
        if ((localByl.i == -1) || (localByl.i <= i2) || (i2 == Integer.MIN_VALUE)) {
          localByl.j = byo.c;
        } else {
          localByl.j = byo.b;
        }
      }
      else
      {
        localByl.j = byo.a;
      }
    }
  }
  
  private void c(boolean paramBoolean)
  {
    this.y = new bxu(this, paramBoolean, null);
    bzc.a(this.y, new Void[0]);
  }
  
  private void l()
  {
    boolean bool2 = true;
    int i1 = this.p.size();
    TextView localTextView = this.q;
    boolean bool1;
    if (i1 > 0)
    {
      bool1 = true;
      a(localTextView, bool1);
      localTextView = this.r;
      if (i1 <= 0) {
        break label87;
      }
      bool1 = bool2;
      label43:
      a(localTextView, bool1);
      if (i1 != 0) {
        break label92;
      }
      this.s.setText(cag.select);
      if (g() != null) {
        g().b(cag.app_name);
      }
    }
    label87:
    label92:
    do
    {
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label43;
      this.s.setText(cag.deselect);
    } while (g() == null);
    g().a(getString(cag.app_name) + " (" + i1 + ")");
  }
  
  private void m()
  {
    if (this.p.size() > this.t)
    {
      b((byl)this.p.get(this.t));
      this.t += 1;
      return;
    }
    this.t = 0;
  }
  
  private void n()
  {
    bzc.a(new bxq(this, null), new Void[0]);
  }
  
  private bzf o()
  {
    if (this.A == null) {
      this.A = new bzf(this.z);
    }
    return this.A;
  }
  
  /* Error */
  private void p()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 84	com/rhythm/hexise/inst/BaseInstaller:j	Ljava/util/Map;
    //   4: invokeinterface 537 1 0
    //   9: aload_0
    //   10: invokevirtual 234	com/rhythm/hexise/inst/BaseInstaller:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: iconst_0
    //   14: invokevirtual 541	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   17: invokeinterface 421 1 0
    //   22: astore_1
    //   23: aload_1
    //   24: invokeinterface 427 1 0
    //   29: ifeq +99 -> 128
    //   32: aload_1
    //   33: invokeinterface 431 1 0
    //   38: checkcast 246	android/content/pm/PackageInfo
    //   41: astore_2
    //   42: aload_0
    //   43: getfield 84	com/rhythm/hexise/inst/BaseInstaller:j	Ljava/util/Map;
    //   46: aload_2
    //   47: getfield 544	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   50: aload_2
    //   51: getfield 249	android/content/pm/PackageInfo:versionCode	I
    //   54: invokestatic 548	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   57: invokeinterface 552 3 0
    //   62: pop
    //   63: goto -40 -> 23
    //   66: astore_1
    //   67: aload_0
    //   68: invokevirtual 234	com/rhythm/hexise/inst/BaseInstaller:getPackageManager	()Landroid/content/pm/PackageManager;
    //   71: iconst_0
    //   72: invokevirtual 555	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   75: invokeinterface 421 1 0
    //   80: astore_1
    //   81: aload_1
    //   82: invokeinterface 427 1 0
    //   87: ifeq +41 -> 128
    //   90: aload_1
    //   91: invokeinterface 431 1 0
    //   96: checkcast 557	android/content/pm/ApplicationInfo
    //   99: astore_2
    //   100: aload_0
    //   101: getfield 84	com/rhythm/hexise/inst/BaseInstaller:j	Ljava/util/Map;
    //   104: aload_2
    //   105: getfield 558	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   108: ldc_w 447
    //   111: invokestatic 548	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   114: invokeinterface 552 3 0
    //   119: pop
    //   120: goto -39 -> 81
    //   123: astore_1
    //   124: aload_1
    //   125: invokestatic 170	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   128: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	this	BaseInstaller
    //   22	11	1	localIterator1	Iterator
    //   66	1	1	localException1	Exception
    //   80	11	1	localIterator2	Iterator
    //   123	2	1	localException2	Exception
    //   41	64	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	23	66	java/lang/Exception
    //   23	63	66	java/lang/Exception
    //   67	81	123	java/lang/Exception
    //   81	120	123	java/lang/Exception
  }
  
  public final void a(Message paramMessage)
  {
    if (101 == paramMessage.what)
    {
      paramMessage = new bzu(this, 101);
      if (!isFinishing()) {
        paramMessage.show();
      }
    }
    else
    {
      return;
    }
    paramMessage.onCancel(paramMessage);
  }
  
  public final void a(bzb paramBzb)
  {
    this.G.sendMessage(Message.obtain(this.G, 101));
  }
  
  public ahj k()
  {
    return this.E;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent arg3)
  {
    super.onActivityResult(paramInt1, paramInt2, ???);
    switch (paramInt1)
    {
    default: 
    case 0: 
      do
      {
        return;
        if ((this.v != null) && (a(this.v))) {
          this.u += 1;
        }
        this.v = null;
        m();
      } while (this.t != 0);
      if (this.u > 0) {
        bzc.a(new bxt(this, null), new Void[0]);
      }
      this.u = 0;
      return;
    }
    if ((paramInt2 == -1) && (??? != null))
    {
      ??? = ???.getData();
      if (??? != null)
      {
        Object localObject1 = ???.getAuthority();
        List localList = ???.getPathSegments();
        if (("com.android.externalstorage.documents".equals(localObject1)) && (localList != null) && (localList.size() == 2) && ("tree".equals(localList.get(0))))
        {
          localObject1 = ((String)localList.get(1)).split(":");
          if ((localObject1.length == 1) && (!"primary".equalsIgnoreCase(localObject1[0])))
          {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("extSdUri", ???.toString()).apply();
            a(???);
          }
        }
      }
    }
    synchronized (byn.a)
    {
      byn.a.notify();
      return;
    }
  }
  
  public void onBackPressed()
  {
    finish();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == cad.deleteBtn)
    {
      paramView = new ArrayList();
      paramView.addAll(this.p);
      a(paramView);
    }
    do
    {
      for (;;)
      {
        l();
        return;
        if (paramView.getId() != cad.installBtn) {
          break;
        }
        m();
      }
    } while (paramView.getId() != cad.selectBtn);
    int i2;
    if (this.p.size() > 0)
    {
      this.p.clear();
      i2 = 0;
    }
    for (;;)
    {
      int i1;
      try
      {
        if (this.l.getChildCount() <= 0) {
          break;
        }
        i1 = 0;
        int i3 = this.l.getChildAt(i1).getBottom();
        if (i3 < 0)
        {
          i1 += 1;
          continue;
          paramView = this.m.a().iterator();
          if (!paramView.hasNext()) {
            break label296;
          }
          byl localByl = (byl)paramView.next();
          this.p.add(localByl);
          continue;
        }
        i3 = this.l.getChildCount() - 1;
        if (this.l.getChildAt(i3).getTop() > this.l.getHeight())
        {
          i3 -= 1;
          continue;
          if (i1 > i3) {
            break;
          }
          paramView = this.l.getChildAt(i1);
          if (!(paramView.getTag() instanceof bxw)) {
            break label301;
          }
          paramView = ((bxw)paramView.getTag()).f;
          if (i2 != 0)
          {
            i4 = cac.check;
            paramView.setImageResource(i4);
            break label301;
          }
          int i4 = cac.uncheck;
          continue;
        }
      }
      catch (Throwable paramView)
      {
        Log.e("com.rhmsoft.inst", "Error when refresh check status: ", paramView);
      }
      continue;
      label296:
      i2 = 1;
      continue;
      label301:
      i1 += 1;
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if ((this.k != null) && (this.k.isShowing())) {
      this.k.a(paramConfiguration);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Crashlytics.start(this);
    this.E = aha.a(this).a(cah.analytics);
    getWindow().addFlags(128);
    setContentView(cae.main_screen);
    this.F = ((LayoutInflater)getSystemService("layout_inflater"));
    if (g() != null)
    {
      g().b(cag.app_name);
      g().a(cac.icon);
      g().a(new ColorDrawable(bq.b(this, caa.background)));
      g().a(true);
    }
    if (!"mounted".equals(Environment.getExternalStorageState()))
    {
      Toast.makeText(this, cag.noSDCard, 1).show();
      finish();
      return;
    }
    this.z = new bzh(this);
    this.q = ((TextView)findViewById(cad.deleteBtn));
    this.q.setOnClickListener(this);
    this.r = ((TextView)findViewById(cad.installBtn));
    this.r.setOnClickListener(this);
    this.s = ((TextView)findViewById(cad.selectBtn));
    this.s.setOnClickListener(this);
    this.l = ((ListView)findViewById(cad.entryList));
    this.m = new bwy(this, this, cae.entry, this.i);
    this.l.setAdapter(this.m);
    paramBundle = (TextView)findViewById(cad.empty);
    this.l.setEmptyView(paramBundle);
    paramBundle.setVisibility(8);
    this.l.setOnItemClickListener(new bwz(this));
    this.l.setOnItemLongClickListener(new bxa(this));
    l();
    byq.b(this);
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    if (paramInt == 0) {
      return new bzi(this);
    }
    return super.onCreateDialog(paramInt);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    paramMenu.add(0, 0, 0, cag.refresh);
    paramMenu.add(0, 1, 1, cag.sort);
    paramMenu.add(0, 2, 2, cag.about);
    paramMenu.add(0, 3, 3, cag.settings);
    getMenuInflater().inflate(caf.options_menu, paramMenu);
    paramMenu = (SearchView)hb.a(paramMenu.findItem(cad.search));
    if (paramMenu != null) {
      paramMenu.setOnQueryTextListener(new bxb(this));
    }
    return true;
  }
  
  protected void onDestroy()
  {
    if (this.z != null) {
      this.z.close();
    }
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return true;
    case 0: 
      c(true);
      return true;
    case 1: 
      int i1 = PreferenceManager.getDefaultSharedPreferences(this).getInt("sortType", 4);
      this.x = i1;
      new ru(this).a(cag.sortBy).a(bzz.sort_types, i1, new bxe(this)).a(cag.ascending, new bxd(this)).b(cag.descending, new bxc(this)).b().show();
      return true;
    case 2: 
      showDialog(0);
      return true;
    }
    paramMenuItem = new Intent();
    paramMenuItem.setClass(this, InstallerPreference.class);
    startActivity(paramMenuItem);
    return true;
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    boolean bool2 = bzc.d(this);
    MenuItem localMenuItem = paramMenu.findItem(2);
    if (!bool2) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      localMenuItem.setVisible(bool1);
      paramMenu.findItem(3).setVisible(bool2);
      return true;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if ((!this.o) && (this.i.size() == 0))
    {
      if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("initialized", false)) {
        break label50;
      }
      c(false);
    }
    for (;;)
    {
      this.o = true;
      return;
      label50:
      n();
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    aha.a(this).a(this);
  }
  
  protected void onStop()
  {
    super.onStop();
    aha.a(this).c(this);
  }
}
