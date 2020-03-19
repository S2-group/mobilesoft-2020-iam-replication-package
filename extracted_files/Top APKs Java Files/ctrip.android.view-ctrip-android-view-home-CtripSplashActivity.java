package ctrip.android.view.home;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.crittercism.app.Crittercism;
import com.ctrip.android.asyncimageloader.core.ImageLoader;
import ctrip.android.view.flight.inland.FlightInquireActivity;
import ctrip.android.view.h5.activity.H5Container;
import ctrip.android.view.hotel.inland.HotelInquireActivity;
import ctrip.android.view.myctrip.CtripLowPriceTransitActivity;
import ctrip.android.view.push.PushReceiver;
import ctrip.android.view.push.PushService;
import ctrip.android.view.train.TrainInquireActivity;
import ctrip.android.view.travel.VacationInquireActivityV2;
import ctrip.android.view.urihandle.IntentUriHandlerActivity;
import ctrip.b.c;
import ctrip.base.logical.component.CtripBaseApplication;
import ctrip.base.logical.util.b;
import ctrip.business.controller.BusinessController;
import ctrip.business.controller.a;
import ctrip.business.controller.d;
import ctrip.business.database.aj;
import ctrip.business.orm.f;
import ctrip.business.util.CtripActionCodeLogUtil;
import ctrip.business.util.FileUtil;
import ctrip.business.util.LogUtil;
import ctrip.business.util.StringUtil;
import ctrip.business.util.VersionControlUtil;
import ctrip.business.viewmodel.URLViewModel;
import java.util.Iterator;
import java.util.List;

public class CtripSplashActivity
  extends FragmentActivity
{
  private boolean a = false;
  private boolean b = false;
  private long c = 0L;
  private LinearLayout d;
  private LinearLayout e;
  private LinearLayout f;
  private LinearLayout.LayoutParams g = new LinearLayout.LayoutParams(-2, -2);
  private ImageView h;
  private String i = "";
  private boolean j = false;
  private boolean k = false;
  private boolean l = false;
  private boolean m = false;
  private Handler n = new n(this);
  private SharedPreferences o;
  
  public CtripSplashActivity() {}
  
  private int a(Context paramContext)
  {
    try
    {
      int i1 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  private String a(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int i2 = arrayOfProviderInfo.length;
          int i1 = 0;
          while (i1 < i2)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i1];
            if (paramString.equals(localProviderInfo.readPermission)) {
              return localProviderInfo.authority;
            }
            i1 += 1;
          }
        }
      }
    }
    return null;
  }
  
  /* Error */
  private boolean a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokevirtual 140	ctrip/android/view/home/CtripSplashActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   6: astore 4
    //   8: aload_1
    //   9: invokestatic 146	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   12: astore_1
    //   13: aload_0
    //   14: ldc -109
    //   16: invokevirtual 151	ctrip/android/view/home/CtripSplashActivity:getString	(I)Ljava/lang/String;
    //   19: astore 5
    //   21: aload 4
    //   23: aload_1
    //   24: iconst_2
    //   25: anewarray 124	java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: ldc -103
    //   32: aastore
    //   33: dup
    //   34: iconst_1
    //   35: ldc -101
    //   37: aastore
    //   38: ldc -99
    //   40: iconst_1
    //   41: anewarray 124	java/lang/String
    //   44: dup
    //   45: iconst_0
    //   46: aload 5
    //   48: aastore
    //   49: aconst_null
    //   50: invokevirtual 163	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   53: astore_1
    //   54: aload_1
    //   55: ifnull +26 -> 81
    //   58: aload_1
    //   59: invokeinterface 169 1 0
    //   64: istore_2
    //   65: iload_2
    //   66: ifle +15 -> 81
    //   69: aload_1
    //   70: ifnull +9 -> 79
    //   73: aload_1
    //   74: invokeinterface 172 1 0
    //   79: iconst_1
    //   80: ireturn
    //   81: aload_1
    //   82: ifnull +9 -> 91
    //   85: aload_1
    //   86: invokeinterface 172 1 0
    //   91: iconst_0
    //   92: ireturn
    //   93: astore_1
    //   94: aconst_null
    //   95: astore_1
    //   96: aload_1
    //   97: ifnull +9 -> 106
    //   100: aload_1
    //   101: invokeinterface 172 1 0
    //   106: iconst_0
    //   107: ireturn
    //   108: astore 4
    //   110: aload_3
    //   111: astore_1
    //   112: aload 4
    //   114: astore_3
    //   115: aload_1
    //   116: ifnull +9 -> 125
    //   119: aload_1
    //   120: invokeinterface 172 1 0
    //   125: aload_3
    //   126: athrow
    //   127: astore_3
    //   128: goto -13 -> 115
    //   131: astore_3
    //   132: goto -36 -> 96
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	this	CtripSplashActivity
    //   0	135	1	paramString	String
    //   64	2	2	i1	int
    //   1	125	3	localObject1	Object
    //   127	1	3	localObject2	Object
    //   131	1	3	localException	Exception
    //   6	16	4	localContentResolver	android.content.ContentResolver
    //   108	5	4	localObject3	Object
    //   19	28	5	str	String
    // Exception table:
    //   from	to	target	type
    //   8	54	93	java/lang/Exception
    //   8	54	108	finally
    //   58	65	127	finally
    //   58	65	131	java/lang/Exception
  }
  
  private void h()
  {
    Intent localIntent1 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", getString(2131492979));
    localIntent1.putExtra("duplicate", false);
    Intent localIntent2 = new Intent("android.intent.action.MAIN");
    localIntent2.addCategory("android.intent.category.LAUNCHER");
    localIntent2.setFlags(270532608);
    String str = getPackageName();
    localIntent2.setComponent(new ComponentName(str, str + "." + getLocalClassName()));
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    localIntent1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this, 2130838331));
    sendBroadcast(localIntent1);
  }
  
  private boolean i()
  {
    try
    {
      if (a("content://com.android.launcher2.settings/favorites?notify=true")) {
        return true;
      }
      if (a("content://com.android.launcher.settings/favorites?notify=true")) {
        return true;
      }
      String str = a(this, "com.android.launcher.permission.READ_SETTINGS");
      if (!TextUtils.isEmpty(str))
      {
        boolean bool = a("content://" + str + "/favorites?notify=true");
        return bool;
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  private void j()
  {
    if (!this.l) {
      runOnUiThread(new w(this));
    }
    if (!this.k)
    {
      this.k = false;
      this.n.postDelayed(new x(this), 1000L);
      return;
    }
    this.k = false;
  }
  
  public void a()
  {
    this.d = new LinearLayout(this);
    this.d.setOrientation(1);
    this.g.setMargins(10, 0, 10, 0);
    TextView localTextView = new TextView(this);
    localTextView.setTextAppearance(this, 2131558994);
    localTextView.setText(b());
    CheckBox localCheckBox = new CheckBox(this);
    localCheckBox.setText(2131496007);
    localCheckBox.setTextAppearance(this, 2131559090);
    this.d.setBackgroundColor(getResources().getColor(2131230731));
    localCheckBox.setOnClickListener(new y(this, localCheckBox));
    this.d.addView(localTextView, this.g);
    this.d.addView(localCheckBox, this.g);
  }
  
  public String b()
  {
    switch (a.f)
    {
    default: 
      return getString(2131492991);
    }
    return getString(2131492992);
  }
  
  public void c()
  {
    this.e = new LinearLayout(CtripBaseApplication.a());
    this.e.setOrientation(1);
    this.g.setMargins(10, 0, 10, 0);
    TextView localTextView = new TextView(CtripBaseApplication.a());
    localTextView.setTextAppearance(CtripBaseApplication.a(), 2131558994);
    localTextView.setText(2131492978);
    this.e.setBackgroundColor(getResources().getColor(2131230731));
    this.e.addView(localTextView, this.g);
  }
  
  public void d()
  {
    this.f = new LinearLayout(this);
    this.f.setOrientation(1);
    this.g.setMargins(10, 0, 10, 0);
    TextView localTextView = new TextView(this);
    localTextView.setTextAppearance(this, 2131558994);
    localTextView.setText(2131492976);
    this.f.setBackgroundColor(getResources().getColor(2131230731));
    this.f.addView(localTextView, this.g);
  }
  
  public void e()
  {
    Object localObject;
    if (!this.j)
    {
      this.j = true;
      if (aj.q() != null) {
        this.n.sendEmptyMessage(65538);
      }
      localObject = aj.r();
      if ((localObject == null) || (StringUtil.emptyOrNull(((URLViewModel)localObject).image_URL))) {
        break label106;
      }
      this.i = ((URLViewModel)localObject).image_URL;
      if (System.currentTimeMillis() - this.c < 300L)
      {
        localObject = this.n.obtainMessage(65537);
        this.n.sendMessageDelayed((Message)localObject, 300L);
      }
    }
    else
    {
      return;
    }
    this.n.sendEmptyMessage(65537);
    return;
    label106:
    if (System.currentTimeMillis() - this.c < 300L)
    {
      localObject = this.n.obtainMessage(65539);
      this.n.sendMessageDelayed((Message)localObject, 300L);
      return;
    }
    this.n.sendEmptyMessage(65539);
  }
  
  public void f()
  {
    try
    {
      Object localObject1 = getSharedPreferences(PushService.PREFS_NAME, 0).edit();
      ((SharedPreferences.Editor)localObject1).putBoolean("Background", true);
      ((SharedPreferences.Editor)localObject1).commit();
      CtripActionCodeLogUtil.getInstance().pushToFile();
      c.a().b();
      CtripBaseApplication.a().b = false;
      g();
      new WebView(this).clearCache(true);
      BusinessController.setAttribute(d.a, "");
      BusinessController.setAttribute(d.b, "");
      ctrip.business.comm.KeepAliveConfig.heartBeatFlag = false;
      FileUtil.deleteTmpFile();
      f.a();
      finish();
      Object localObject2 = ((ActivityManager)getSystemService("activity")).getRunningAppProcesses();
      localObject1 = getPackageName();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
        if ((localRunningAppProcessInfo.processName.startsWith((String)localObject1)) && (!localRunningAppProcessInfo.processName.endsWith(":push"))) {
          Process.killProcess(localRunningAppProcessInfo.pid);
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void g()
  {
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    try
    {
      localNotificationManager.cancelAll();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    int i2 = 0;
    requestWindowFeature(1);
    getWindow().setFlags(2000, 2000);
    Object localObject = getSharedPreferences(PushService.PREFS_NAME, 0).edit();
    ((SharedPreferences.Editor)localObject).putBoolean("Background", false);
    ((SharedPreferences.Editor)localObject).commit();
    super.onCreate(paramBundle);
    this.o = PreferenceManager.getDefaultSharedPreferences(this);
    int i1 = a(this);
    int i3 = this.o.getInt("version_code", 0);
    if ((i1 != i3) && (!i())) {
      h();
    }
    if (i1 != i3) {
      this.o.edit().putInt("version_code", i1).commit();
    }
    Crittercism.initialize(getApplicationContext(), "5162932b5483080d5d000007");
    Crittercism.setUsername(VersionControlUtil.getClientID(""));
    setContentView(2130903060);
    this.h = ((ImageView)findViewById(2131296380));
    CtripBaseApplication.a().d = true;
    paramBundle = getIntent();
    if (paramBundle != null)
    {
      if ((paramBundle.getExtras() != null) && (paramBundle.getExtras().getBoolean("EXIT_APP", false))) {
        f();
      }
      if (paramBundle.getBooleanExtra("PushFromURL", false))
      {
        localObject = paramBundle.getAction();
        if (localObject != null) {
          if (((String)localObject).equals("FOCUS_FLIGHT_TAG"))
          {
            this.k = true;
            localObject = new Intent(getApplicationContext(), FlightInquireActivity.class);
            ((Intent)localObject).setFlags(603979776);
            ((Intent)localObject).setAction("FOCUS_FLIGHT_TAG");
            ((Intent)localObject).putExtra(PushReceiver.FROMPUSHINTENT, paramBundle.getBooleanExtra(PushReceiver.FROMPUSHINTENT, true));
            if (!StringUtil.emptyOrNull(paramBundle.getStringExtra(PushReceiver.ADDITION))) {
              ((Intent)localObject).putExtra(PushReceiver.ADDITION, paramBundle.getStringExtra(PushReceiver.ADDITION));
            }
            startActivity((Intent)localObject);
          }
        }
      }
      for (;;)
      {
        return;
        if (((String)localObject).equals("LOW_PRICE_TAG"))
        {
          this.k = true;
          localObject = new Intent(getApplicationContext(), CtripLowPriceTransitActivity.class);
          ((Intent)localObject).setAction("LOW_PRICE_TAG");
          ((Intent)localObject).putExtra(PushReceiver.FROMPUSHINTENT, paramBundle.getBooleanExtra(PushReceiver.FROMPUSHINTENT, true));
          if (!StringUtil.emptyOrNull(paramBundle.getStringExtra(PushReceiver.ADDITION))) {
            ((Intent)localObject).putExtra(PushReceiver.ADDITION, paramBundle.getStringExtra(PushReceiver.ADDITION));
          }
          startActivity((Intent)localObject);
          return;
        }
        if (((String)localObject).equals("FLIGHT_SEAT_SELECT_UPDATE"))
        {
          this.k = true;
          localObject = new Intent(getApplicationContext(), FlightInquireActivity.class);
          ((Intent)localObject).setFlags(603979776);
          ((Intent)localObject).setAction("FLIGHT_SEAT_SELECT_UPDATE");
          ((Intent)localObject).putExtra(PushReceiver.FROMPUSHINTENT, paramBundle.getBooleanExtra(PushReceiver.FROMPUSHINTENT, true));
          if (!StringUtil.emptyOrNull(paramBundle.getStringExtra(PushReceiver.ADDITION))) {
            ((Intent)localObject).putExtra(PushReceiver.ADDITION, paramBundle.getStringExtra(PushReceiver.ADDITION));
          }
          startActivity((Intent)localObject);
          return;
        }
        if (((String)localObject).equals("UNKOWN_MESSAGE"))
        {
          this.k = true;
          localObject = new Intent(getApplicationContext(), CtripHomeActivity.class);
          ((Intent)localObject).setAction("UNKOWN_MESSAGE");
          ((Intent)localObject).putExtra(PushReceiver.FROMPUSHINTENT, paramBundle.getBooleanExtra(PushReceiver.FROMPUSHINTENT, true));
          if (!StringUtil.emptyOrNull(paramBundle.getStringExtra(PushReceiver.ADDITION))) {
            ((Intent)localObject).putExtra(PushReceiver.ADDITION, paramBundle.getStringExtra(PushReceiver.ADDITION));
          }
          if (!StringUtil.emptyOrNull(paramBundle.getStringExtra("message"))) {
            ((Intent)localObject).putExtra("message", paramBundle.getStringExtra("message"));
          }
          if (!StringUtil.emptyOrNull(paramBundle.getStringExtra(PushReceiver.MESSAGE))) {
            ((Intent)localObject).putExtra(PushReceiver.MESSAGE, paramBundle.getStringExtra(PushReceiver.MESSAGE));
          }
          startActivity((Intent)localObject);
          return;
        }
        if (((String)localObject).equals("load url"))
        {
          this.k = true;
          localObject = new Intent(getApplicationContext(), H5Container.class);
          ((Intent)localObject).putExtra(PushReceiver.FROMPUSHINTENT, paramBundle.getBooleanExtra(PushReceiver.FROMPUSHINTENT, true));
          if (!StringUtil.emptyOrNull(paramBundle.getStringExtra("load url"))) {
            ((Intent)localObject).putExtra("load url", paramBundle.getStringExtra("load url"));
          }
          if (!StringUtil.emptyOrNull(paramBundle.getStringExtra(PushReceiver.ADDITION))) {
            ((Intent)localObject).putExtra(PushReceiver.ADDITION, paramBundle.getStringExtra(PushReceiver.ADDITION));
          }
          startActivity((Intent)localObject);
          return;
        }
        this.k = false;
        return;
        this.k = false;
        return;
        Uri localUri = paramBundle.getData();
        if (localUri == null) {
          break label1170;
        }
        this.k = false;
        String str1 = localUri.getScheme();
        String str2 = localUri.getHost();
        localObject = null;
        paramBundle = paramBundle.getAction();
        if ((!StringUtil.emptyOrNull(paramBundle)) && ("load url".equals(paramBundle))) {
          paramBundle = new Intent(getApplicationContext(), IntentUriHandlerActivity.class);
        }
        while (paramBundle != null)
        {
          this.k = true;
          paramBundle.setData(localUri);
          if (!CtripBaseApplication.a().b) {
            break label1164;
          }
          startActivity(paramBundle);
          finish();
          return;
          paramBundle = (Bundle)localObject;
          if (str2 != null)
          {
            paramBundle = (Bundle)localObject;
            if (str1 != null)
            {
              i1 = i2;
              if (str1.equals("ctrip"))
              {
                i1 = i2;
                if (str2.equals("wireless")) {
                  i1 = 1;
                }
              }
              if (i1 != 0)
              {
                paramBundle = new Intent(getApplicationContext(), IntentUriHandlerActivity.class);
              }
              else if (str1.equals("ctrip"))
              {
                if (str2.equals("flightSearch"))
                {
                  paramBundle = new Intent(getApplicationContext(), FlightInquireActivity.class);
                }
                else if (str2.equals("vacationSearch"))
                {
                  paramBundle = new Intent(getApplicationContext(), VacationInquireActivityV2.class);
                }
                else if (str2.equals("hotelSearch"))
                {
                  paramBundle = new Intent(getApplicationContext(), HotelInquireActivity.class);
                }
                else
                {
                  paramBundle = (Bundle)localObject;
                  if (str2.equals("trainSearch")) {
                    paramBundle = new Intent(getApplicationContext(), TrainInquireActivity.class);
                  }
                }
              }
              else
              {
                paramBundle = (Bundle)localObject;
                if (str1.equals("aladdin"))
                {
                  paramBundle = (Bundle)localObject;
                  if (str2.equals("flight")) {
                    paramBundle = new Intent(getApplicationContext(), FlightInquireActivity.class);
                  }
                }
              }
            }
          }
        }
      }
      label1164:
      startActivity(paramBundle);
      return;
      label1170:
      this.k = false;
      return;
    }
    this.k = false;
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.onCreateDialog(paramInt);
    case 1118481: 
      if (Build.VERSION.SDK_INT >= 14) {
        return new AlertDialog.Builder(this).setIcon(17301659).setTitle(2131496708).setView(this.d).setPositiveButton(2131496203, new aa(this)).setNegativeButton(2131496013, new z(this)).setCancelable(false).create();
      }
      return new AlertDialog.Builder(this).setIcon(17301659).setTitle(2131496708).setView(this.d).setPositiveButton(2131496013, new ac(this)).setNegativeButton(2131496203, new ab(this)).setCancelable(false).create();
    case 1118482: 
      if (Build.VERSION.SDK_INT >= 14) {
        return new AlertDialog.Builder(this).setIcon(17301659).setTitle(2131496708).setView(this.e).setPositiveButton(2131496203, new ad(this)).setCancelable(false).create();
      }
      return new AlertDialog.Builder(this).setIcon(17301659).setTitle(2131496708).setView(this.e).setNegativeButton(2131496203, new ae(this)).setCancelable(false).create();
    }
    if (Build.VERSION.SDK_INT >= 14) {
      return new AlertDialog.Builder(this).setIcon(17301659).setTitle(2131496708).setView(this.f).setNegativeButton(2131496525, new p(this)).setPositiveButton(2131496203, new af(this)).setCancelable(false).create();
    }
    return new AlertDialog.Builder(this).setIcon(17301659).setTitle(2131496708).setView(this.f).setPositiveButton(2131496525, new r(this)).setNegativeButton(2131496203, new q(this)).setCancelable(false).create();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      if (ImageLoader.getInstance().isInited()) {
        ImageLoader.getInstance().clearMemoryCache();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (4 == paramInt) {
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    this.m = true;
    if ((paramIntent != null) && (paramIntent.getExtras() != null))
    {
      boolean bool = paramIntent.getExtras().getBoolean("EXIT_APP", false);
      this.l = paramIntent.getExtras().getBoolean("START_HOME", false);
      if (!bool) {
        break label63;
      }
      LogUtil.e("jacky, onNewIntent..quit");
      f();
    }
    for (;;)
    {
      super.onNewIntent(paramIntent);
      return;
      label63:
      if (this.l) {
        startActivity(new Intent(getApplicationContext(), CtripHomeActivity.class));
      }
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return true;
  }
  
  protected void onResume()
  {
    if (b.c()) {}
    try
    {
      removeDialog(1118481);
      super.onResume();
      if (!this.b)
      {
        this.b = true;
        this.c = System.currentTimeMillis();
        new Thread(new s(this)).start();
      }
      while ((this.l) || (CtripBaseApplication.a().m() == null)) {
        return;
      }
      f();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
}
