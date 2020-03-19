package free.vpn.proxy.unblock.android.easy.app.b;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.ads.AdView;
import de.blinkt.openvpn.core.OpenVPNService;
import de.blinkt.openvpn.core.VpnStatus;
import de.blinkt.openvpn.core.v;
import free.vpn.proxy.unblock.android.easy.app.MainActivity;
import free.vpn.proxy.unblock.android.easy.app.a.b;
import free.vpn.proxy.unblock.android.easy.app.c.e;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public final class h
  extends Fragment
  implements DialogInterface.OnClickListener, View.OnClickListener, v
{
  SharedPreferences a;
  SharedPreferences b;
  AdView c;
  WebView d;
  protected OpenVPNService e;
  boolean f = false;
  private TextView g;
  private TextView h;
  private Button i;
  private View j;
  private String k;
  private e l;
  private boolean m = false;
  private boolean n = false;
  private boolean o = false;
  private boolean p = false;
  private String q;
  private String r;
  private int s;
  private Timer t;
  private int u = 0;
  private ServiceConnection v;
  private boolean w;
  
  public h() {}
  
  private static int a(float paramFloat, Context paramContext)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  private boolean a(Intent paramIntent)
  {
    try
    {
      startActivity(paramIntent);
      return true;
    }
    catch (ActivityNotFoundException paramIntent) {}
    return false;
  }
  
  private static boolean c(String paramString1, String paramString2)
  {
    int i4;
    int i1;
    label68:
    do
    {
      try
      {
        paramString1 = paramString1.split("\\.");
        paramString2 = paramString2.split("\\.");
        i4 = Math.max(paramString1.length, paramString2.length);
        i1 = 0;
      }
      catch (Exception paramString1)
      {
        int i2;
        int i3;
        return false;
      }
      if (i1 < paramString1.length)
      {
        i2 = Integer.parseInt(paramString1[i1]);
        if (i1 >= paramString2.length) {
          break label68;
        }
        i3 = Integer.parseInt(paramString2[i1]);
      }
      for (;;)
      {
        if (i2 < i3)
        {
          return true;
          i2 = 0;
          break;
          i3 = 0;
          continue;
        }
        if (i2 > i3) {
          break label96;
        }
        i1 += 1;
      }
    } while (i1 < i4);
    label96:
    return false;
  }
  
  private static int d(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new BigInteger(paramString2).multiply(new BigInteger("100")).divide(new BigInteger(paramString1));
      paramString2 = paramString1;
      if (paramString1.compareTo(new BigInteger("100")) >= 0) {
        paramString2 = new BigInteger("100");
      }
      return Integer.parseInt(paramString2.toString());
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        paramString1 = new BigInteger("0");
      }
    }
  }
  
  private boolean e()
  {
    int i1 = 180;
    Time localTime;
    int i2;
    label37:
    try
    {
      localTime = new Time();
      localTime.setToNow();
    }
    catch (Exception localException1) {}
    try
    {
      i2 = Integer.parseInt(this.a.getString("adI2", "180"));
      i1 = i2;
    }
    catch (Exception localException2)
    {
      break label37;
    }
    long l1 = TimeUnit.MILLISECONDS.toSeconds(localTime.toMillis(true) - this.a.getLong("interstitialLastShow", 0L));
    return l1 > i1;
  }
  
  private void f()
  {
    if (this.t != null)
    {
      this.t.cancel();
      this.t = null;
    }
  }
  
  private void g()
  {
    if (e())
    {
      e localE = this.l;
      e.b(this.j);
      a(2);
    }
  }
  
  private void h()
  {
    if (this.a.getBoolean("CONNECTED", false))
    {
      SharedPreferences.Editor localEditor = this.a.edit();
      localEditor.putBoolean("CONNECTED", false);
      localEditor.putBoolean("CONNECTING", false);
      localEditor.commit();
      if (getActivity() != null)
      {
        de.blinkt.openvpn.core.o.b(getActivity());
        if ((this.e != null) && (this.e.c() != null)) {
          this.e.c().c();
        }
      }
    }
    do
    {
      for (;;)
      {
        d();
        this.m = false;
        return;
        if ((this.a.getBoolean("CONNECTED", false)) || (this.a.getBoolean("CONNECTING", false))) {
          break;
        }
        if (getActivity() != null) {
          ((MainActivity)getActivity()).a();
        }
      }
    } while ((this.a.getBoolean("CONNECTED", false)) || (!this.a.getBoolean("CONNECTING", false)) || (getActivity() == null));
    try
    {
      de.blinkt.openvpn.core.o.b(getActivity());
      if ((this.e != null) && (this.e.c() != null)) {
        this.e.c().c();
      }
      getActivity().finish();
      startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public final void a()
  {
    d();
    e localE = this.l;
    e.a(this.j);
    ((ScrollView)this.j.findViewById(2131230845)).setVerticalScrollBarEnabled(false);
    this.u = 0;
    new x(this).execute(new String[0]);
    b(250);
  }
  
  public final void a(int paramInt)
  {
    if (getActivity() == null) {
      return;
    }
    if ((((MainActivity)getActivity()).a != null) && (((MainActivity)getActivity()).a.d()))
    {
      localE = this.l;
      e.c(this.j);
      b(500);
      return;
    }
    if (paramInt > 0)
    {
      new Handler().postDelayed(new l(this, paramInt), 250L);
      return;
    }
    e localE = this.l;
    e.c(this.j);
  }
  
  final void a(String paramString)
  {
    try
    {
      if (getActivity() == null) {
        return;
      }
      getActivity().runOnUiThread(new u(this, paramString));
      return;
    }
    catch (Exception paramString) {}
  }
  
  public final void a(String paramString1, String paramString2, int paramInt, de.blinkt.openvpn.core.s paramS)
  {
    try
    {
      if (getActivity() == null) {
        return;
      }
      if ((paramS == de.blinkt.openvpn.core.s.f) || (paramS == de.blinkt.openvpn.core.s.i) || (paramS == de.blinkt.openvpn.core.s.g)) {
        if (this.a.getBoolean("CONNECTED", false))
        {
          paramString1 = this.a.edit();
          paramString1.putBoolean("CONNECTED", false);
          paramString1.putBoolean("CONNECTING", false);
          paramString1.commit();
        }
      }
      for (;;)
      {
        if (paramInt == 2131165477) {
          a("Problem creating TUN interface (Bug of Android)! Please restart your phone and try again. If that doesnt work re-install the application.");
        }
        if (!this.w) {
          break;
        }
        d();
        return;
        if (paramS == de.blinkt.openvpn.core.s.a)
        {
          paramString1 = this.a.edit();
          paramString1.putBoolean("CONNECTED", true);
          paramString1.putBoolean("CONNECTING", false);
          paramString1.commit();
        }
      }
      return;
    }
    catch (Exception paramString1) {}
  }
  
  public final void b()
  {
    int i3 = 3;
    Object localObject1 = new ArrayList();
    BitmapFactory.decodeResource(getResources(), 2130837584);
    BitmapFactory.decodeResource(getResources(), 2130837556);
    ArrayList localArrayList = new ArrayList();
    if (getActivity() == null) {
      return;
    }
    Object localObject2 = this.a.getStringSet("mAllowedAppsVpn", null);
    if (localObject2 != null) {
      ((ArrayList)localObject1).addAll((Collection)localObject2);
    }
    for (;;)
    {
      Object localObject3 = getActivity().getPackageManager();
      ((PackageManager)localObject3).getInstalledApplications(128);
      new Vector();
      try
      {
        int i1 = ((PackageManager)localObject3).getApplicationInfo("android", 128).uid;
        Iterator localIterator = ((ArrayList)localObject1).iterator();
        int i2 = 0;
        for (;;)
        {
          if (!localIterator.hasNext())
          {
            localArrayList.add(new free.vpn.proxy.unblock.android.easy.app.a.a(getResources().getDrawable(2130837589)));
            localObject1 = (GridView)this.j.findViewById(2131230861);
            ((GridView)localObject1).setAdapter(new b(getActivity(), localArrayList));
            ((GridView)localObject1).setOnItemClickListener(new q(this, localArrayList));
            i1 = 4;
          }
          try
          {
            localObject2 = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
            float f1 = ((DisplayMetrics)localObject2).widthPixels;
            int i4 = a(88.0F, getActivity());
            i4 = (int)(f1 / i4);
            i1 = i4;
            if (i4 < 3)
            {
              i1 = i3;
              i1 = (i2 + i1) / i1;
              localObject2 = ((GridView)localObject1).getLayoutParams();
              ((ViewGroup.LayoutParams)localObject2).height = a(i1 * 94, getActivity());
              ((GridView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
              return;
              ((ArrayList)localObject1).add("com.android.chrome");
              ((ArrayList)localObject1).add("org.mozilla.firefox");
              ((ArrayList)localObject1).add("com.google.android.browser");
              ((ArrayList)localObject1).add("com.sec.android.app.sbrowser");
              ((ArrayList)localObject1).add("com.android.browser");
              ((ArrayList)localObject1).add("com.android.vending");
              ((ArrayList)localObject1).add("com.google.android.youtube");
              ((ArrayList)localObject1).add("com.facebook.katana");
              ((ArrayList)localObject1).add("com.opera.mini.native");
              ((ArrayList)localObject1).add("com.whatsapp");
              ((ArrayList)localObject1).add("com.facebook.orca");
              ((ArrayList)localObject1).add("com.viber.voip");
              ((ArrayList)localObject1).add("com.skype.raider");
              ((ArrayList)localObject1).add("com.tencent.mm");
              ((ArrayList)localObject1).add("org.zwanoo.android.speedtest");
              localObject2 = new HashSet();
              ((Set)localObject2).addAll((Collection)localObject1);
              localObject3 = this.a.edit();
              ((SharedPreferences.Editor)localObject3).putStringSet("mAllowedAppsVpn", (Set)localObject2);
              ((SharedPreferences.Editor)localObject3).commit();
              break;
              str = (String)localIterator.next();
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              try
              {
                String str;
                ApplicationInfo localApplicationInfo = ((PackageManager)localObject3).getApplicationInfo(str, 0);
                localObject2 = localApplicationInfo.loadLabel((PackageManager)localObject3);
                localObject1 = localObject2;
                if (TextUtils.isEmpty((CharSequence)localObject2)) {
                  localObject1 = localApplicationInfo.packageName;
                }
                localArrayList.add(new free.vpn.proxy.unblock.android.easy.app.a.a(localApplicationInfo.loadIcon((PackageManager)localObject3), ((CharSequence)localObject1).toString(), str));
                i2 += 1;
              }
              catch (PackageManager.NameNotFoundException localNameNotFoundException1) {}
              localException = localException;
            }
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2)
      {
        for (;;) {}
      }
    }
  }
  
  /* Error */
  public final boolean b(int paramInt)
  {
    // Byte code:
    //   0: new 231	android/text/format/Time
    //   3: dup
    //   4: invokespecial 232	android/text/format/Time:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: invokevirtual 235	android/text/format/Time:setToNow	()V
    //   12: aload_0
    //   13: getfield 237	free/vpn/proxy/unblock/android/easy/app/b/h:a	Landroid/content/SharedPreferences;
    //   16: ldc -17
    //   18: ldc -15
    //   20: invokeinterface 247 3 0
    //   25: invokestatic 205	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   28: istore_2
    //   29: aload_0
    //   30: invokevirtual 139	free/vpn/proxy/unblock/android/easy/app/b/h:getActivity	()Landroid/app/Activity;
    //   33: ifnonnull +5 -> 38
    //   36: iconst_0
    //   37: ireturn
    //   38: aload_0
    //   39: invokevirtual 139	free/vpn/proxy/unblock/android/easy/app/b/h:getActivity	()Landroid/app/Activity;
    //   42: checkcast 332	free/vpn/proxy/unblock/android/easy/app/MainActivity
    //   45: getfield 378	free/vpn/proxy/unblock/android/easy/app/MainActivity:a	Lfree/vpn/proxy/unblock/android/easy/app/c/a;
    //   48: ifnull +107 -> 155
    //   51: aload_0
    //   52: invokevirtual 139	free/vpn/proxy/unblock/android/easy/app/b/h:getActivity	()Landroid/app/Activity;
    //   55: checkcast 332	free/vpn/proxy/unblock/android/easy/app/MainActivity
    //   58: getfield 378	free/vpn/proxy/unblock/android/easy/app/MainActivity:a	Lfree/vpn/proxy/unblock/android/easy/app/c/a;
    //   61: invokevirtual 382	free/vpn/proxy/unblock/android/easy/app/c/a:d	()Z
    //   64: ifeq +91 -> 155
    //   67: aload_0
    //   68: getfield 54	free/vpn/proxy/unblock/android/easy/app/b/h:n	Z
    //   71: ifne +84 -> 155
    //   74: aload_0
    //   75: getfield 56	free/vpn/proxy/unblock/android/easy/app/b/h:o	Z
    //   78: ifne +77 -> 155
    //   81: aload_0
    //   82: getfield 58	free/vpn/proxy/unblock/android/easy/app/b/h:p	Z
    //   85: ifne +70 -> 155
    //   88: getstatic 253	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   91: aload_3
    //   92: iconst_1
    //   93: invokevirtual 257	android/text/format/Time:toMillis	(Z)J
    //   96: aload_0
    //   97: getfield 237	free/vpn/proxy/unblock/android/easy/app/b/h:a	Landroid/content/SharedPreferences;
    //   100: ldc_w 259
    //   103: lconst_0
    //   104: invokeinterface 263 4 0
    //   109: lsub
    //   110: invokevirtual 267	java/util/concurrent/TimeUnit:toSeconds	(J)J
    //   113: iload_2
    //   114: i2l
    //   115: lcmp
    //   116: ifle +39 -> 155
    //   119: aload_0
    //   120: getfield 88	free/vpn/proxy/unblock/android/easy/app/b/h:l	Lfree/vpn/proxy/unblock/android/easy/app/c/e;
    //   123: astore_3
    //   124: aload_0
    //   125: getfield 183	free/vpn/proxy/unblock/android/easy/app/b/h:j	Landroid/view/View;
    //   128: invokestatic 287	free/vpn/proxy/unblock/android/easy/app/c/e:b	(Landroid/view/View;)V
    //   131: new 386	android/os/Handler
    //   134: dup
    //   135: invokespecial 387	android/os/Handler:<init>	()V
    //   138: new 621	free/vpn/proxy/unblock/android/easy/app/b/r
    //   141: dup
    //   142: aload_0
    //   143: iload_1
    //   144: invokespecial 622	free/vpn/proxy/unblock/android/easy/app/b/r:<init>	(Lfree/vpn/proxy/unblock/android/easy/app/b/h;I)V
    //   147: iload_1
    //   148: i2l
    //   149: invokevirtual 397	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   152: pop
    //   153: iconst_1
    //   154: ireturn
    //   155: iconst_0
    //   156: ireturn
    //   157: astore_3
    //   158: aload_0
    //   159: iconst_0
    //   160: putfield 52	free/vpn/proxy/unblock/android/easy/app/b/h:m	Z
    //   163: aload_0
    //   164: invokevirtual 624	free/vpn/proxy/unblock/android/easy/app/b/h:c	()V
    //   167: iconst_0
    //   168: ireturn
    //   169: astore 4
    //   171: sipush 180
    //   174: istore_2
    //   175: goto -146 -> 29
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	178	0	this	h
    //   0	178	1	paramInt	int
    //   28	147	2	i1	int
    //   7	117	3	localObject	Object
    //   157	1	3	localException1	Exception
    //   169	1	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	12	157	java/lang/Exception
    //   29	36	157	java/lang/Exception
    //   38	153	157	java/lang/Exception
    //   12	29	169	java/lang/Exception
  }
  
  public final void c()
  {
    try
    {
      if (getActivity() == null) {
        return;
      }
      if (((MainActivity)getActivity()).a != null) {
        ((MainActivity)getActivity()).a.c();
      }
      c(100);
      if (this.m)
      {
        h();
        return;
      }
    }
    catch (Exception localException) {}
  }
  
  public final void c(int paramInt)
  {
    new Handler().postDelayed(new s(this), paramInt + 250);
  }
  
  public final void d()
  {
    if (getActivity() == null) {
      return;
    }
    getActivity().runOnUiThread(new t(this));
  }
  
  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.n)
    {
      paramDialogInterface = this.a.edit();
      if (paramInt == -1)
      {
        new z(this).execute(new String[] { "0" });
        paramDialogInterface.putBoolean("RATEREMIND", false);
        paramDialogInterface.commit();
      }
    }
    for (;;)
    {
      this.n = false;
      this.o = false;
      this.p = false;
      return;
      if (paramInt == -2)
      {
        new z(this).execute(new String[] { "1" });
        localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).setData(Uri.parse("market://details?id=free.vpn.proxy.unblock.android.easy.app"));
        if (!a((Intent)localObject)) {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=free.vpn.proxy.unblock.android.easy.app")));
        }
        paramDialogInterface.putBoolean("RATEREMIND", false);
        break;
      }
      if (paramInt != -3) {
        break;
      }
      new z(this).execute(new String[] { "2" });
      Object localObject = new SimpleDateFormat("yyyy-MM-dd");
      Date localDate = new Date();
      paramDialogInterface.putBoolean("RATEREMIND", true);
      paramDialogInterface.putString("RATEREMINDDATE", ((SimpleDateFormat)localObject).format(localDate));
      break;
      if (this.o)
      {
        if (paramInt == -1)
        {
          new aa(this).execute(new String[] { "0" });
        }
        else if (paramInt == -2)
        {
          new aa(this).execute(new String[] { "1" });
          paramDialogInterface = new Intent("android.intent.action.SEND");
          paramDialogInterface.setType("text/plain");
          paramDialogInterface.putExtra("android.intent.extra.SUBJECT", "VPN Easy, best free android vpn.");
          paramDialogInterface.putExtra("android.intent.extra.TEXT", "VPN Easy opens all blocked sites and applications, protects your privacy and secures your data on public Wifi networks.\nUnbeatable quality for free!\n\nhttps://play.google.com/store/apps/details?id=free.vpn.proxy.unblock.android.easy.app");
          startActivity(Intent.createChooser(paramDialogInterface, "Share via"));
        }
      }
      else if (paramInt == -1)
      {
        new aa(this).execute(new String[] { "0" });
      }
      else if (paramInt == -2)
      {
        paramDialogInterface = this.a.getString("USER", "").substring(0, 12);
        localObject = new Intent("android.intent.action.SEND");
        ((Intent)localObject).setType("message/rfc822");
        ((Intent)localObject).putExtra("android.intent.extra.EMAIL", new String[] { "support@zpn.im" });
        ((Intent)localObject).putExtra("android.intent.extra.SUBJECT", "VPN Easy Feedback (" + paramDialogInterface + ")");
        try
        {
          startActivity(Intent.createChooser((Intent)localObject, "Send feedback to us."));
        }
        catch (ActivityNotFoundException paramDialogInterface) {}
      }
    }
  }
  
  public final void onClick(View paramView)
  {
    rateClick(paramView);
  }
  
  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.a = getActivity().getApplicationContext().getSharedPreferences("MyPrefs", 0);
    this.b = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
    this.j = paramLayoutInflater.inflate(2130903059, paramViewGroup, false);
    this.l = ((MainActivity)getActivity()).d;
    b();
    this.i = ((Button)this.j.findViewById(2131230858));
    this.g = ((TextView)this.j.findViewById(2131230855));
    this.h = ((TextView)this.j.findViewById(2131230860));
    ((TextView)this.j.findViewById(2131230848)).setText(this.a.getString("VERSION", ""));
    this.i.setOnClickListener(new i(this));
    d();
    if (getActivity() != null) {
      getActivity().runOnUiThread(new j(this));
    }
    if (getActivity() != null) {
      getActivity().runOnUiThread(new m(this));
    }
    paramLayoutInflater = this.l;
    e.a(this.j);
    ((ScrollView)this.j.findViewById(2131230845)).setVerticalScrollBarEnabled(false);
    this.u = 0;
    new x(this).execute(new String[0]);
    g();
    return this.j;
  }
  
  public final void onDestroy()
  {
    super.onDestroy();
    if (this.d != null)
    {
      this.d.destroy();
      this.d = null;
    }
    if (this.c != null)
    {
      this.c.destroy();
      this.c = null;
    }
    f();
    try
    {
      VpnStatus.b(this);
      try
      {
        if (this.v != null)
        {
          if (getActivity() == null) {
            return;
          }
          getActivity().unbindService(this.v);
          this.v = null;
          this.e = null;
          return;
        }
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public final void onPause()
  {
    super.onPause();
    this.w = false;
    f();
    if (this.d != null) {
      this.d.setEnabled(false);
    }
    if (this.c != null)
    {
      this.c.pause();
      this.c.setEnabled(false);
    }
    try
    {
      VpnStatus.b(this);
      try
      {
        if (this.v != null)
        {
          if (getActivity() == null) {
            return;
          }
          getActivity().unbindService(this.v);
          this.v = null;
          this.e = null;
          return;
        }
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public final void onResume()
  {
    super.onResume();
    this.w = true;
    this.t = new Timer();
    this.t.schedule(new o(this), 0L, 1000L);
    g();
    if (this.d != null) {
      this.d.setEnabled(true);
    }
    if (this.c != null)
    {
      this.c.resume();
      this.c.setEnabled(true);
    }
    try
    {
      this.v = new k(this);
      VpnStatus.a(this);
      if (getActivity() != null)
      {
        Intent localIntent = new Intent(getActivity(), OpenVPNService.class);
        localIntent.setAction("de.blinkt.openvpn.START_SERVICE");
        getActivity().bindService(localIntent, this.v, 1);
      }
      d();
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void onStop()
  {
    super.onStop();
  }
  
  public final void rateClick(View paramView)
  {
    for (;;)
    {
      try
      {
        if (((this.s == 5) && (paramView.getId() == 2131230868)) || ((this.s == 4) && ((paramView.getId() == 2131230868) || (paramView.getId() == 2131230867))) || ((this.s == 3) && ((paramView.getId() == 2131230868) || (paramView.getId() == 2131230867) || (paramView.getId() == 2131230866))) || ((this.s == 2) && ((paramView.getId() == 2131230868) || (paramView.getId() == 2131230867) || (paramView.getId() == 2131230866) || (paramView.getId() == 2131230865))) || ((this.s == 1) && ((paramView.getId() == 2131230868) || (paramView.getId() == 2131230867) || (paramView.getId() == 2131230866) || (paramView.getId() == 2131230865) || (paramView.getId() == 2131230864))))
        {
          paramView = this.q;
          localObject1 = this.r;
          this.n = true;
          if (getActivity() != null)
          {
            localObject2 = new AlertDialog.Builder(getActivity());
            ((AlertDialog.Builder)localObject2).setTitle(paramView);
            ((AlertDialog.Builder)localObject2).setMessage((CharSequence)localObject1);
            ((AlertDialog.Builder)localObject2).setNeutralButton("Ask me later", this);
            ((AlertDialog.Builder)localObject2).setNegativeButton("Yes", this);
            ((AlertDialog.Builder)localObject2).setPositiveButton("No, thanks", this);
            ((AlertDialog.Builder)localObject2).show();
          }
        }
      }
      catch (Exception paramView)
      {
        Object localObject1;
        Object localObject2;
        continue;
      }
      try
      {
        paramView = getActivity();
        if (paramView == null)
        {
          return;
          if ((paramView.getId() == 2131230868) || (paramView.getId() == 2131230867) || (paramView.getId() == 2131230866) || (paramView.getId() == 2131230865) || (paramView.getId() == 2131230864))
          {
            new z(this).execute(new String[] { "0" });
            paramView = this.a.edit();
            paramView.putBoolean("RATEREMIND", false);
            paramView.commit();
            this.p = true;
            if (getActivity() == null) {
              continue;
            }
            paramView = new AlertDialog.Builder(getActivity());
            paramView.setTitle("Give us feedback");
            paramView.setMessage("Would you like to give us a feedback about your issue?");
            paramView.setNegativeButton("Yes", this);
            paramView.setPositiveButton("No, thanks", this);
            paramView.show();
            continue;
          }
          new z(this).execute(new String[] { "2" });
          paramView = this.a.edit();
          localObject1 = new SimpleDateFormat("yyyy-MM-dd");
          localObject2 = new Date();
          paramView.putBoolean("RATEREMIND", true);
          paramView.putString("RATEREMINDDATE", ((SimpleDateFormat)localObject1).format((Date)localObject2));
          paramView.commit();
          continue;
        }
        getActivity().findViewById(2131230863).setVisibility(8);
        return;
      }
      catch (Exception paramView) {}
    }
  }
}
