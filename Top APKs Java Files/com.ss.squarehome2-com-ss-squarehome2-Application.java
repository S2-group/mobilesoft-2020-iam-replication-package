package com.ss.squarehome2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.LauncherApps;
import android.content.pm.LauncherApps.Callback;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ShortcutInfo;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.support.v4.a.e.b;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.ss.e.a.a.a;
import com.ss.f.g;
import com.ss.f.k;
import com.ss.f.q;
import com.ss.f.q.a;
import com.ss.iconpack.b.a;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Application
  extends android.app.Application
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private static boolean D = false;
  private static boolean E = false;
  private static LinkedList<WeakReference<Runnable>> F = new LinkedList();
  private static q.a G;
  private static boolean H = false;
  private static q.a I;
  private static JSONArray J;
  private static PackageInfo K;
  private static boolean L = false;
  private static com.ss.e.a.a M;
  public static Comparator<ae> a;
  public static LinkedList<WeakReference<Runnable>> b;
  private static final ArrayList<ae> c = new ArrayList();
  private static final ArrayList<ae> d = new ArrayList();
  private static final HashMap<String, ae> e = new HashMap();
  private static Handler f;
  private static JSONArray g;
  private static HashMap<String, LinkedList<String>> h;
  private static JSONObject i;
  private static JSONObject j;
  private static JSONObject k;
  private static JSONArray l;
  private static Application m;
  private static int n = 0;
  private static boolean o = false;
  private static float p;
  private static float q;
  private static float r;
  private static Drawable s;
  private static Drawable t;
  private static Bitmap u;
  private static q v = new q();
  private b A;
  private BroadcastReceiver B;
  private Locale C;
  private ServiceConnection N = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      Application.a(a.a.a(paramAnonymousIBinder));
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      Application.a(null);
    }
  };
  private com.ss.launcher.counter.b w = new com.ss.launcher.counter.b();
  private com.ss.launcher.c.c x;
  private com.ss.launcher.c.b y;
  private final BroadcastReceiver z = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Application.a(Application.this);
    }
  };
  
  static
  {
    b = new LinkedList();
  }
  
  public Application() {}
  
  private void H()
  {
    int i3 = 0;
    int i1 = 0;
    int i2;
    for (;;)
    {
      i2 = i3;
      if (i1 >= c.size()) {
        break;
      }
      ((ae)c.get(i1)).a(this, this.w);
      i1 += 1;
    }
    while (i2 < d.size())
    {
      ((ae)d.get(i2)).a(this, this.w);
      i2 += 1;
    }
    b(500L);
  }
  
  private void I()
  {
    a = new Comparator()
    {
      private Collator b = Collator.getInstance(Application.j());
      
      public int a(ae paramAnonymousAe1, ae paramAnonymousAe2)
      {
        int i;
        boolean bool1;
        boolean bool2;
        if (Application.z() == 0)
        {
          if ((paramAnonymousAe1.q()) && (!paramAnonymousAe2.q())) {
            return -1;
          }
          if ((!paramAnonymousAe1.q()) && (paramAnonymousAe2.q())) {
            return 1;
          }
          i = paramAnonymousAe1.h();
          int j = paramAnonymousAe2.h();
          if (i != j) {
            return j - i;
          }
          bool1 = paramAnonymousAe1.p();
          bool2 = paramAnonymousAe2.p();
          if ((bool1) && (!bool2)) {
            return -1;
          }
          if ((!bool1) && (bool2)) {
            return 1;
          }
        }
        else if (Application.z() == 2)
        {
          bool1 = paramAnonymousAe1.p();
          bool2 = paramAnonymousAe2.p();
          if ((bool1) && (!bool2)) {
            return -1;
          }
          if ((!bool1) && (bool2)) {
            return 1;
          }
        }
        if (paramAnonymousAe1.d == paramAnonymousAe2.d)
        {
          paramAnonymousAe1 = paramAnonymousAe1.d(Application.A()).toString();
          paramAnonymousAe2 = paramAnonymousAe2.d(Application.A()).toString();
          if ((Application.j().getLanguage().equals("zh")) && (Application.j().getCountry().equals("CN")))
          {
            String str1 = Character.toString(Application.a(paramAnonymousAe1.charAt(0)));
            String str2 = Character.toString(Application.a(paramAnonymousAe2.charAt(0)));
            i = this.b.compare(str1, str2);
            if (i != 0) {
              return i;
            }
          }
          return this.b.compare(paramAnonymousAe1, paramAnonymousAe2);
        }
        return -Float.compare(paramAnonymousAe1.d, paramAnonymousAe2.d);
      }
    };
  }
  
  private static void J()
  {
    k = cd.c(new File(m.getFilesDir(), "hiddens"));
    if (k == null) {
      k = new JSONObject();
    }
    i = cd.c(new File(m.getFilesDir(), "labels"));
    if (i == null) {
      i = new JSONObject();
    }
    j = cd.c(new File(m.getFilesDir(), "icons"));
    if (j == null) {
      j = new JSONObject();
    }
  }
  
  private static void K()
  {
    Context localContext = m.getApplicationContext();
    try
    {
      String[] arrayOfString = f.a(localContext, "folders").list();
      int i2 = arrayOfString.length;
      int i1 = 0;
      while (i1 < i2)
      {
        String str = arrayOfString[i1];
        m.c(new ae(localContext, str));
        i1 += 1;
      }
      P();
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private void L()
  {
    d(true);
    this.A.a();
    a(0L);
  }
  
  private void M()
  {
    a(0L);
  }
  
  private static void N()
  {
    int i3 = n;
    int i2 = 0;
    int i1 = 0;
    switch (i3)
    {
    default: 
      return;
    case 2: 
      a(0.0F);
      return;
    case 1: 
      O();
      a(0.0F);
    }
    while (i1 < J.length())
    {
      try
      {
        localObject = a(J.getString(i1));
        if (localObject != null) {
          ((ae)localObject).d = (J.length() - i1);
        }
      }
      catch (JSONException localJSONException)
      {
        Object localObject;
        ae localAe;
        Float localFloat;
        float f1;
        int i4;
        for (;;) {}
      }
      i1 += 1;
      continue;
      if ((m.y != null) && (m.y.b()))
      {
        localObject = m.y.e();
        i1 = 0;
        while (i1 < c.size())
        {
          localAe = (ae)c.get(i1);
          localFloat = (Float)((HashMap)localObject).get(localAe.a());
          if (localFloat != null) {
            f1 = localFloat.floatValue();
          } else {
            f1 = 0.0F;
          }
          localAe.d = f1;
          i1 += 1;
        }
        i1 = 0;
        while (i1 < d.size())
        {
          localAe = (ae)d.get(i1);
          localFloat = (Float)((HashMap)localObject).get(localAe.a());
          if (localFloat != null) {
            f1 = localFloat.floatValue();
          } else {
            f1 = 0.0F;
          }
          localAe.d = f1;
          i1 += 1;
        }
        localObject = new ArrayList();
        ((ArrayList)localObject).addAll(c);
        Collections.sort((List)localObject, new Comparator()
        {
          public int a(ae paramAnonymousAe1, ae paramAnonymousAe2)
          {
            return -Float.compare(paramAnonymousAe1.d, paramAnonymousAe2.d);
          }
        });
        i4 = ak.a(m, "smartPickNum", 11);
        for (i3 = 0; i2 < ((ArrayList)localObject).size(); i3 = i1)
        {
          localAe = (ae)((ArrayList)localObject).get(i2);
          i1 = i3;
          if (!localAe.g()) {
            if (localAe.e())
            {
              i1 = i3;
            }
            else
            {
              i3 += 1;
              i1 = i3;
              if (i3 > i4)
              {
                localAe.d = 0.0F;
                i1 = i3;
              }
            }
          }
          i2 += 1;
        }
      }
    }
  }
  
  private static void O()
  {
    JSONArray localJSONArray;
    if (n == 1)
    {
      if (J != null) {
        return;
      }
      J = cd.b(new File(m.getFilesDir(), "userSort"));
      if (J != null) {
        return;
      }
      localJSONArray = new JSONArray();
    }
    else
    {
      localJSONArray = null;
    }
    J = localJSONArray;
  }
  
  private static void P()
  {
    int i1 = 0;
    while (i1 < c.size())
    {
      ae localAe = (ae)c.get(i1);
      localAe.a(d(localAe));
      i1 += 1;
    }
    if (n == 0) {
      N();
    }
  }
  
  private static ArrayList<ae> Q()
  {
    ArrayList localArrayList = new ArrayList(c.size());
    Iterator localIterator = k.keys();
    for (;;)
    {
      String str;
      if (localIterator.hasNext()) {
        str = (String)localIterator.next();
      }
      try
      {
        if (!k.getBoolean(str)) {
          continue;
        }
        localArrayList.add(a(str));
      }
      catch (JSONException localJSONException) {}
      return localArrayList;
    }
  }
  
  private void R()
  {
    if (m != null)
    {
      if (K != null) {
        return;
      }
      Object localObject1 = m.getApplicationContext();
      Object localObject2 = (WifiManager)((Context)localObject1).getSystemService("wifi");
      if (localObject2 != null)
      {
        if (((WifiManager)localObject2).getWifiState() != 3) {
          return;
        }
        if (com.ss.launcher.utils.b.a().a((Context)localObject1, "com.ss.squarehome.key", true, false) == null) {
          return;
        }
        long l1 = PreferenceManager.getDefaultSharedPreferences((Context)localObject1).getLong("lastCheckTimeForSalesInfo", 0L);
        if (System.currentTimeMillis() - l1 > 86400000L)
        {
          localObject2 = new q.a()
          {
            private String c;
            
            public void a()
            {
              this.c = cd.b("https://mytestinfoblog.blogspot.kr/p/sh-sales.html", "__sh_sales_info__");
            }
            
            public void run()
            {
              if (!TextUtils.isEmpty(this.c)) {}
              try
              {
                Object localObject = new JSONObject(this.c);
                long l = ((JSONObject)localObject).getLong("to");
                if (l > System.currentTimeMillis())
                {
                  localObject = this.a.getString(2131558830, new Object[] { Integer.valueOf(((JSONObject)localObject).getInt("dc")), Long.valueOf((l - System.currentTimeMillis()) / 3600000L) });
                  localObject = new e.b(this.a).a(2131165366).a(this.a.getString(2131558860)).b((CharSequence)localObject).a(true);
                  Intent localIntent = com.ss.launcher.utils.b.a().a(this.a, "com.ss.squarehome.key", true, false);
                  localIntent.setFlags(268435456);
                  ((e.b)localObject).a(PendingIntent.getActivity(this.a, 0, localIntent, 134217728));
                  ((NotificationManager)Application.this.getSystemService("notification")).notify(0, ((e.b)localObject).a());
                }
                return;
              }
              catch (JSONException localJSONException) {}
            }
          };
          v.a((q.a)localObject2);
          localObject1 = PreferenceManager.getDefaultSharedPreferences((Context)localObject1).edit();
          ((SharedPreferences.Editor)localObject1).putLong("lastCheckTimeForSalesInfo", System.currentTimeMillis());
          ((SharedPreferences.Editor)localObject1).apply();
        }
      }
    }
  }
  
  private boolean S()
  {
    if ((M == null) && (K != null))
    {
      Intent localIntent = new Intent(com.ss.e.a.a.class.getName());
      localIntent.setPackage(K.packageName);
      bindService(localIntent, this.N, 1);
      return true;
    }
    return false;
  }
  
  static char a(char paramChar)
  {
    char c1 = paramChar;
    if ('0' <= paramChar)
    {
      c1 = paramChar;
      if (paramChar <= '9') {
        c1 = '1';
      }
    }
    if ((j().getLanguage().equals("ko")) && (g.a(c1)))
    {
      paramChar = g.a(g.c(c1));
    }
    else
    {
      paramChar = c1;
      if (j().getLanguage().equals("zh"))
      {
        paramChar = c1;
        if (j().getCountry().equals("CN"))
        {
          String str = k.a(c1);
          paramChar = c1;
          if (str.length() > 0)
          {
            paramChar = c1;
            if (!str.equals("?")) {
              paramChar = str.charAt(0);
            }
          }
        }
      }
    }
    return Character.toUpperCase(paramChar);
  }
  
  public static int a()
  {
    int i2 = m.getResources().getDisplayMetrics().densityDpi;
    int i1 = 192;
    if ((i2 <= 480) && (i2 <= 320)) {
      if (i2 > 240) {
        i1 = 144;
      } else if (i2 > 160) {
        i1 = 96;
      } else {
        i1 = 72;
      }
    }
    return Math.min(m.getResources().getDimensionPixelSize(2131099663) * ak.a(m, "iconSize", 100) / 100 * 3 / 2, i1);
  }
  
  private static PackageInfo a(PackageManager paramPackageManager)
  {
    if (!L) {
      L = true;
    }
    try
    {
      K = paramPackageManager.getPackageInfo("com.ss.squarehome.key", 64);
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      for (;;) {}
    }
    K = null;
    return K;
  }
  
  public static Drawable a(Context paramContext)
  {
    float f1 = ak.a(paramContext, "iconScale", 100.0F) / 100.0F;
    float f2 = ak.a(paramContext, "iconDx", 0.0F) / 100.0F;
    float f3 = ak.a(paramContext, "iconDy", 0.0F) / 100.0F;
    int i1 = a();
    Drawable localDrawable1 = l.a(paramContext, ak.a(paramContext, "iconBg", null), i1, i1, false);
    Drawable localDrawable2 = l.a(paramContext, ak.a(paramContext, "iconFg", null), i1, i1, false);
    Bitmap localBitmap = l.a(paramContext, ak.a(paramContext, "iconMask", null), i1);
    return com.ss.iconpack.b.a(paramContext, com.ss.iconpack.b.a(paramContext, paramContext.getResources(), 2131165423), f1, f2, f3, localDrawable1, localDrawable2, localBitmap, null);
  }
  
  public static Drawable a(Context paramContext, ae paramAe)
  {
    boolean bool = o;
    Object localObject = null;
    if (!bool)
    {
      p = ak.a(paramContext, "iconScale", 100.0F) / 100.0F;
      q = ak.a(paramContext, "iconDx", 0.0F) / 100.0F;
      r = ak.a(paramContext, "iconDy", 0.0F) / 100.0F;
      int i1 = a();
      s = l.a(paramContext, ak.a(paramContext, "iconBg", null), i1, i1, false);
      t = l.a(paramContext, ak.a(paramContext, "iconFg", null), i1, i1, false);
      u = l.a(paramContext, ak.a(paramContext, "iconMask", null), i1);
      o = true;
    }
    b.a local5 = new b.a()
    {
      public Drawable a(Context paramAnonymousContext)
      {
        return this.a.g(paramAnonymousContext);
      }
    };
    com.ss.launcher.utils.c localC = paramAe.b();
    float f1 = p;
    float f2 = q;
    float f3 = r;
    Drawable localDrawable1 = s;
    Drawable localDrawable2 = t;
    Bitmap localBitmap = u;
    paramAe = localObject;
    if (localC != null) {
      paramAe = localC.b();
    }
    return com.ss.iconpack.b.a(paramContext, local5, f1, f2, f3, localDrawable1, localDrawable2, localBitmap, paramAe, true);
  }
  
  private static ae a(com.ss.launcher.utils.c paramC)
  {
    if ((paramC != null) && (m != null))
    {
      Iterator localIterator = com.ss.launcher.utils.b.a().a(m, paramC.b().getPackageName(), paramC.c()).iterator();
      while (localIterator.hasNext()) {
        if (((com.ss.launcher.utils.c)localIterator.next()).b().equals(paramC.b())) {
          return m.b(paramC);
        }
      }
    }
    return null;
  }
  
  public static ae a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return (ae)e.get(paramString);
  }
  
  public static ArrayList<ae> a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString2 == null)
    {
      a(c, localArrayList, paramString1, Integer.MAX_VALUE);
      paramString2 = d;
    }
    for (;;)
    {
      a(paramString2, localArrayList, paramString1, Integer.MAX_VALUE);
      return localArrayList;
      if (paramString2.startsWith("#"))
      {
        paramString2 = paramString2.substring(1);
        if (paramString2.equals(m.getString(2131558625)))
        {
          paramString2 = Q();
          continue;
        }
        if (paramString2.equals(m.getString(2131558422))) {
          break;
        }
        if (!paramString2.equals(m.getString(2131558968))) {
          return localArrayList;
        }
        paramString2 = q();
        continue;
      }
      paramString2 = d(paramString2);
    }
    return localArrayList;
  }
  
  private Locale a(Configuration paramConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      LocaleList localLocaleList = paramConfiguration.getLocales();
      if (localLocaleList.size() > 0) {
        return localLocaleList.get(0);
      }
    }
    return paramConfiguration.locale;
  }
  
  private static void a(float paramFloat)
  {
    int i3 = 0;
    int i1 = 0;
    int i2;
    for (;;)
    {
      i2 = i3;
      if (i1 >= c.size()) {
        break;
      }
      ((ae)c.get(i1)).d = paramFloat;
      i1 += 1;
    }
    while (i2 < d.size())
    {
      ((ae)d.get(i2)).d = paramFloat;
      i2 += 1;
    }
  }
  
  public static void a(long paramLong)
  {
    Iterator localIterator = F.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (WeakReference)localIterator.next();
      if ((localObject != null) && (((WeakReference)localObject).get() != null))
      {
        localObject = (Runnable)((WeakReference)localObject).get();
        f.removeCallbacks((Runnable)localObject);
        f.postDelayed((Runnable)localObject, paramLong);
      }
      else
      {
        localIterator.remove();
      }
    }
  }
  
  public static void a(ae paramAe)
  {
    if ((m != null) && (m.y != null))
    {
      m.y.a(paramAe.a());
      boolean bool = paramAe.q();
      paramAe.a(System.currentTimeMillis());
      if (bool) {
        a(0L);
      }
      if (n == 0) {
        N();
      }
    }
  }
  
  public static void a(d paramD)
  {
    Application localApplication = m;
    paramD = new ae(m, paramD.a());
    localApplication.c(paramD);
    P();
    paramD.a(m, m.w);
    m.A.a();
    a(0L);
  }
  
  public static void a(CharSequence paramCharSequence, HashMap<String, Integer> paramHashMap)
  {
    if (paramCharSequence.length() > 0)
    {
      int i2 = 0;
      char c1 = paramCharSequence.charAt(0);
      boolean bool1 = Character.isUpperCase(c1);
      a(paramHashMap, c1);
      int i1 = 1;
      while (i1 < paramCharSequence.length())
      {
        c1 = paramCharSequence.charAt(i1);
        boolean bool3 = b(c1);
        boolean bool2 = Character.isUpperCase(c1);
        if ((Character.isDigit(c1)) || ((i2 != 0) && (!bool3)) || ((!bool1) && (bool2))) {
          a(paramHashMap, c1);
        }
        i1 += 1;
        i2 = bool3;
        bool1 = bool2;
      }
    }
  }
  
  public static void a(Runnable paramRunnable)
  {
    b(paramRunnable);
    b.add(new WeakReference(paramRunnable));
  }
  
  public static void a(Runnable paramRunnable, boolean paramBoolean)
  {
    if (paramRunnable != null) {
      F.add(new WeakReference(paramRunnable));
    }
    if ((!D) && (!E))
    {
      if (paramBoolean) {
        return;
      }
      D = true;
      G = new q.a()
      {
        public void a()
        {
          PackageManager localPackageManager = Application.A().getPackageManager();
          if (Build.VERSION.SDK_INT >= 21)
          {
            Object localObject2 = com.ss.launcher.utils.b.a().a(Application.A());
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              localObject1 = new LinkedList();
              ((List)localObject1).add(null);
            }
            localObject1 = ((List)localObject1).iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject2 = (UserHandle)((Iterator)localObject1).next();
              Iterator localIterator = localPackageManager.getInstalledPackages(128).iterator();
              while (localIterator.hasNext()) {
                Application.a(((PackageInfo)localIterator.next()).packageName, (UserHandle)localObject2, null);
              }
            }
          }
          Object localObject1 = localPackageManager.getInstalledPackages(128).iterator();
          while (((Iterator)localObject1).hasNext()) {
            Application.a(((PackageInfo)((Iterator)localObject1).next()).packageName, null, null);
          }
        }
        
        public void run()
        {
          if (Application.B() == this)
          {
            Application.a(null);
            Application.a(true);
            Application.C();
            if (Application.c(Application.A()).b())
            {
              Application.x();
              Application.a(Application.c(Application.A()).f());
              Application.a(0L);
            }
            Application.m();
          }
        }
      };
      v.a(G);
    }
  }
  
  private void a(String paramString, UserHandle paramUserHandle)
  {
    d(paramString, paramUserHandle);
    if (paramString.equals("com.ss.squarehome.key"))
    {
      L = false;
      K = null;
      a(this, false);
      MainActivity.a(false);
    }
    j(paramString);
  }
  
  public static void a(String paramString, UserHandle paramUserHandle, List<ae> paramList)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    com.ss.launcher.utils.b localB = com.ss.launcher.utils.b.a();
    Iterator localIterator = localB.a(m, paramString, paramUserHandle).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (com.ss.launcher.utils.c)localIterator.next();
      localObject = m.b((com.ss.launcher.utils.c)localObject);
      ((ae)localObject).a(ae.b);
      if (paramList != null) {
        paramList.add(localObject);
      }
    }
    paramString = localB.b(m, paramString, paramUserHandle).iterator();
    while (paramString.hasNext())
    {
      paramUserHandle = (com.ss.launcher.utils.c)paramString.next();
      paramUserHandle = m.b(paramUserHandle);
      paramUserHandle.a(ae.c);
      if (paramList != null) {
        paramList.add(paramUserHandle);
      }
    }
  }
  
  public static void a(ArrayList<ae> paramArrayList)
  {
    int i1 = paramArrayList.size() - 1;
    while (i1 >= 0)
    {
      if (((ae)paramArrayList.get(i1)).g()) {
        paramArrayList.remove(i1);
      }
      i1 -= 1;
    }
  }
  
  private static void a(ArrayList<ae> paramArrayList1, ArrayList<ae> paramArrayList2, String paramString, int paramInt)
  {
    int i1;
    if ((!j().getLanguage().equals("en")) && (ak.a(m, "searchEnLabel", true))) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    int i2 = 0;
    while ((i2 < paramArrayList1.size()) && (paramArrayList2.size() < paramInt))
    {
      ae localAe = (ae)paramArrayList1.get(i2);
      if (localAe != null)
      {
        localAe.b(false);
        if ((paramString != null) && (paramString.length() > 0))
        {
          if (paramString.length() == 1)
          {
            char c1 = paramString.charAt(0);
            if (a(localAe.a(m), c1)) {
              break label216;
            }
            if ((i1 == 0) || (c1 < 'A') || (c1 > 'Z') || (!a(localAe.c(m), c1))) {
              break label223;
            }
          }
          else
          {
            if (b(localAe.a(m).toString(), paramString)) {
              break label216;
            }
            if ((i1 == 0) || (!b(localAe.c(m).toString(), paramString))) {
              break label223;
            }
          }
          localAe.b(true);
        }
        label216:
        paramArrayList2.add(localAe);
      }
      label223:
      i2 += 1;
    }
  }
  
  private static void a(ArrayList<ae> paramArrayList, HashMap<String, Long> paramHashMap)
  {
    int i1 = 0;
    while (i1 < paramArrayList.size())
    {
      ae localAe = (ae)paramArrayList.get(i1);
      long l1;
      if (paramHashMap.containsKey(localAe.a())) {
        l1 = ((Long)paramHashMap.get(localAe.a())).longValue();
      } else {
        l1 = 0L;
      }
      localAe.a(l1);
      i1 += 1;
    }
  }
  
  public static void a(ArrayList<String> paramArrayList, boolean paramBoolean)
  {
    if (g == null)
    {
      g = cd.b(new File(m.getFilesDir(), "tags"));
      if (g == null) {
        g = new JSONArray();
      }
    }
    int i1;
    if (paramArrayList != null) {
      i1 = 0;
    }
    for (;;)
    {
      if (i1 < g.length()) {}
      try
      {
        paramArrayList.add(g.getString(i1));
        i1 += 1;
        continue;
        if (!paramBoolean)
        {
          Collections.addAll(paramArrayList, m.getResources().getStringArray(2130837506));
          if (ak.a(m, "tvApps", false)) {
            paramArrayList.add(m.getString(2131558968));
          }
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  private static void a(HashMap<String, Integer> paramHashMap, char paramChar)
  {
    String str = Character.toString(a(paramChar));
    Integer localInteger = (Integer)paramHashMap.get(str);
    if (localInteger != null) {}
    for (localInteger = Integer.valueOf(localInteger.intValue() + 1);; localInteger = Integer.valueOf(1))
    {
      paramHashMap.put(str, localInteger);
      return;
    }
  }
  
  private void a(String[] paramArrayOfString, UserHandle paramUserHandle)
  {
    if (paramArrayOfString != null)
    {
      ArrayList localArrayList = new ArrayList();
      int i2 = 0;
      int i3 = paramArrayOfString.length;
      int i1 = 0;
      while (i1 < i3)
      {
        a(paramArrayOfString[i1], paramUserHandle, localArrayList);
        i1 += 1;
      }
      i1 = i2;
      if (m.y.b())
      {
        a(localArrayList, m.y.f());
        i1 = i2;
      }
      while (i1 < localArrayList.size())
      {
        paramArrayOfString = (ae)localArrayList.get(i1);
        paramArrayOfString.a(d(paramArrayOfString));
        i1 += 1;
      }
    }
    N();
    L();
  }
  
  public static boolean a(char paramChar1, char paramChar2)
  {
    if ((j().getLanguage().equals("zh")) && (j().getCountry().equals("CN")))
    {
      if (k.b(paramChar1) == 0) {
        return paramChar1 == paramChar2;
      }
      return k.a(paramChar1).charAt(0) == paramChar2;
    }
    return g.a(paramChar2, paramChar1);
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean)
  {
    if (com.ss.squarehome2.a.a.a()) {
      return true;
    }
    Object localObject = paramContext;
    if (paramContext == null) {
      localObject = m;
    }
    paramContext = ((Context)localObject).getPackageManager();
    if (a(paramContext) == null) {
      return false;
    }
    if (K.versionCode < 5)
    {
      if (paramBoolean) {
        Toast.makeText((Context)localObject, 2131558965, 1).show();
      }
      return false;
    }
    if (K.signatures[0].hashCode() != 1310029541)
    {
      Toast.makeText((Context)localObject, 2131558799, 1).show();
      return false;
    }
    try
    {
      paramBoolean = TextUtils.equals(paramContext.getInstallerPackageName("com.ss.squarehome.key"), "com.android.vending");
      if (paramBoolean) {
        return true;
      }
    }
    catch (NullPointerException paramContext)
    {
      for (;;)
      {
        try
        {
          if (M != null)
          {
            if (M.a(((Context)localObject).getPackageName()) != 2) {
              return true;
            }
            Toast.makeText((Context)localObject, 2131558719, 1).show();
            return false;
          }
          paramBoolean = ((Application)((Context)localObject).getApplicationContext()).S();
          return paramBoolean;
        }
        catch (Exception paramContext) {}
        paramContext = paramContext;
      }
    }
    return false;
  }
  
  public static boolean a(ae paramAe, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      i.remove(paramAe.a());
    }
    try
    {
      i.put(paramAe.a(), paramString);
      if (cd.a(i, new File(m.getFilesDir(), "labels")))
      {
        String str = paramString;
        if (TextUtils.isEmpty(paramString)) {
          str = null;
        }
        paramAe.a(str);
        if (!paramAe.e()) {
          m.A.a();
        }
        a(0L);
        return true;
      }
      return false;
    }
    catch (JSONException paramAe) {}
    return false;
  }
  
  public static boolean a(ae paramAe, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      k.put(paramAe.a(), true);
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
    k.remove(paramAe.a());
    paramAe.f();
    if (n == 0) {
      N();
    }
    if (cd.a(k, new File(m.getFilesDir(), "hiddens")))
    {
      m.A.a();
      a(0L);
      return true;
    }
    return false;
  }
  
  public static boolean a(CharSequence paramCharSequence, char paramChar)
  {
    if ((paramCharSequence != null) && (paramCharSequence.length() > 0))
    {
      if (a(paramChar) != paramChar) {
        return b(paramCharSequence.toString(), Character.toString(paramChar));
      }
      if (a(paramCharSequence.charAt(0)) == paramChar) {
        return true;
      }
      if (paramCharSequence.length() > 1)
      {
        boolean bool1 = Character.isUpperCase(paramCharSequence.charAt(0));
        int i2 = 0;
        int i1 = 1;
        while (i1 < paramCharSequence.length())
        {
          char c1 = paramCharSequence.charAt(i1);
          boolean bool3 = b(c1);
          boolean bool2 = Character.isUpperCase(c1);
          if (((Character.isDigit(c1)) || ((i2 != 0) && (!bool3)) || ((!bool1) && (bool2))) && (a(c1) == paramChar)) {
            return true;
          }
          i1 += 1;
          i2 = bool3;
          bool1 = bool2;
        }
      }
    }
    return false;
  }
  
  public static boolean a(String paramString, List<ae> paramList)
  {
    if ((paramString.startsWith("#")) && (paramString.substring(1).equals(m.getString(2131558625)))) {
      return c(paramList);
    }
    if (h == null)
    {
      a(null, false);
      h = new HashMap(g.length());
    }
    JSONArray localJSONArray = new JSONArray();
    LinkedList localLinkedList = new LinkedList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = ((ae)paramList.next()).a();
      localLinkedList.add(str);
      localJSONArray.put(str);
    }
    paramList = new File(m.getFilesDir(), "tagData");
    paramList.mkdirs();
    if (!cd.a(localJSONArray, new File(paramList, paramString))) {
      return false;
    }
    h.put(paramString, localLinkedList);
    return true;
  }
  
  public static boolean a(List<ae> paramList)
  {
    J = new JSONArray();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ae localAe = (ae)paramList.next();
      J.put(localAe.a());
    }
    if (cd.a(J, new File(m.getFilesDir(), "userSort")))
    {
      N();
      a(0L);
      return true;
    }
    J = null;
    N();
    a(0L);
    return false;
  }
  
  public static boolean a(JSONArray paramJSONArray)
  {
    if (!cd.a(paramJSONArray, new File(m.getFilesDir(), "tags"))) {
      return false;
    }
    g = paramJSONArray;
    return true;
  }
  
  public static long b(Context paramContext)
  {
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      long l2 = ak.a(paramContext, "frt", 0L);
      long l1 = System.currentTimeMillis();
      l2 = Math.min(localPackageInfo.firstInstallTime, l2);
      l1 -= l2;
      if (l1 < 0L) {
        return -1L;
      }
      return 1296000000L - l1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return -1L;
  }
  
  /* Error */
  private ae b(com.ss.launcher.utils.c paramC)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokeinterface 976 1 0
    //   8: astore_3
    //   9: getstatic 124	com/ss/squarehome2/Application:e	Ljava/util/HashMap;
    //   12: aload_3
    //   13: invokevirtual 288	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   16: checkcast 174	com/ss/squarehome2/ae
    //   19: astore 4
    //   21: aload 4
    //   23: ifnonnull +95 -> 118
    //   26: new 174	com/ss/squarehome2/ae
    //   29: dup
    //   30: aload_0
    //   31: aload_1
    //   32: invokespecial 979	com/ss/squarehome2/ae:<init>	(Landroid/content/Context;Lcom/ss/launcher/utils/c;)V
    //   35: astore_1
    //   36: getstatic 210	com/ss/squarehome2/Application:i	Lorg/json/JSONObject;
    //   39: aload_3
    //   40: invokevirtual 982	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   43: istore_2
    //   44: iload_2
    //   45: ifeq +14 -> 59
    //   48: aload_1
    //   49: getstatic 210	com/ss/squarehome2/Application:i	Lorg/json/JSONObject;
    //   52: aload_3
    //   53: invokevirtual 984	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   56: invokevirtual 928	com/ss/squarehome2/ae:a	(Ljava/lang/String;)V
    //   59: getstatic 214	com/ss/squarehome2/Application:j	Lorg/json/JSONObject;
    //   62: aload_3
    //   63: invokevirtual 982	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   66: istore_2
    //   67: iload_2
    //   68: ifeq +14 -> 82
    //   71: aload_1
    //   72: getstatic 214	com/ss/squarehome2/Application:j	Lorg/json/JSONObject;
    //   75: aload_3
    //   76: invokevirtual 984	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   79: invokevirtual 986	com/ss/squarehome2/ae:b	(Ljava/lang/String;)V
    //   82: getstatic 124	com/ss/squarehome2/Application:e	Ljava/util/HashMap;
    //   85: aload_3
    //   86: aload_1
    //   87: invokevirtual 851	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   90: pop
    //   91: getstatic 117	com/ss/squarehome2/Application:c	Ljava/util/ArrayList;
    //   94: aload_1
    //   95: invokevirtual 355	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   98: pop
    //   99: aload_1
    //   100: aload_0
    //   101: invokevirtual 790	com/ss/squarehome2/ae:a	(Landroid/content/Context;)Ljava/lang/CharSequence;
    //   104: pop
    //   105: aload_1
    //   106: aload_0
    //   107: aload_0
    //   108: getfield 143	com/ss/squarehome2/Application:w	Lcom/ss/launcher/counter/b;
    //   111: invokevirtual 177	com/ss/squarehome2/ae:a	(Landroid/content/Context;Lcom/ss/launcher/counter/b;)V
    //   114: aload_0
    //   115: monitorexit
    //   116: aload_1
    //   117: areturn
    //   118: aload_0
    //   119: monitorexit
    //   120: aload 4
    //   122: areturn
    //   123: astore_1
    //   124: aload_0
    //   125: monitorexit
    //   126: aload_1
    //   127: athrow
    //   128: astore 4
    //   130: goto -71 -> 59
    //   133: astore 4
    //   135: goto -53 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	138	0	this	Application
    //   0	138	1	paramC	com.ss.launcher.utils.c
    //   43	25	2	bool	boolean
    //   8	78	3	str	String
    //   19	102	4	localAe	ae
    //   128	1	4	localJSONException1	JSONException
    //   133	1	4	localJSONException2	JSONException
    // Exception table:
    //   from	to	target	type
    //   2	21	123	finally
    //   26	44	123	finally
    //   48	59	123	finally
    //   59	67	123	finally
    //   71	82	123	finally
    //   82	114	123	finally
    //   48	59	128	org/json/JSONException
    //   71	82	133	org/json/JSONException
  }
  
  public static ae b(String paramString)
  {
    paramString = com.ss.launcher.utils.d.a(m, paramString);
    if (paramString != null) {
      return a(paramString);
    }
    return null;
  }
  
  public static void b()
  {
    com.ss.iconpack.b.a(m, ak.a(m, "iconPack", ak.b));
    o = false;
    E = false;
    D = false;
    G = null;
    c.clear();
    d.clear();
    e.clear();
    J();
    K();
    l = m.i("appsToShowNoti");
    n = ak.a(m, "sortBy", 0);
  }
  
  private static void b(long paramLong)
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext())
    {
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      if ((localWeakReference != null) && (localWeakReference.get() != null))
      {
        f.removeCallbacks((Runnable)localWeakReference.get());
        f.postDelayed((Runnable)localWeakReference.get(), paramLong);
      }
      else
      {
        localIterator.remove();
      }
    }
  }
  
  public static void b(Runnable paramRunnable)
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext())
    {
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      if ((localWeakReference == null) || (localWeakReference.get() == null) || (localWeakReference.get() == paramRunnable)) {
        localIterator.remove();
      }
    }
  }
  
  private void b(String paramString, UserHandle paramUserHandle)
  {
    ArrayList localArrayList = new ArrayList();
    a(paramString, paramUserHandle, localArrayList);
    if (m.y.b()) {
      a(localArrayList, m.y.f());
    }
    int i1 = 0;
    while (i1 < localArrayList.size())
    {
      paramUserHandle = (ae)localArrayList.get(i1);
      paramUserHandle.a(d(paramUserHandle));
      i1 += 1;
    }
    N();
    if (paramString.equals("com.ss.squarehome.key"))
    {
      L = false;
      K = null;
      a(this, false);
      MainActivity.a(false);
    }
    j(paramString);
  }
  
  public static void b(ArrayList<ae> paramArrayList)
  {
    int i1 = paramArrayList.size() - 1;
    while (i1 >= 0)
    {
      if (((ae)paramArrayList.get(i1)).p()) {
        paramArrayList.remove(i1);
      }
      i1 -= 1;
    }
  }
  
  private static void b(ArrayList<ae> paramArrayList, ArrayList<String> paramArrayList1, boolean paramBoolean)
  {
    Object localObject = m;
    int i1 = 0;
    boolean bool = ak.a((Context)localObject, "searchInFolder", false);
    localObject = new HashMap();
    while (i1 < paramArrayList.size())
    {
      ae localAe = (ae)paramArrayList.get(i1);
      if ((localAe != null) && (!localAe.g()) && ((bool) || (!localAe.p())))
      {
        a(localAe.a(m), (HashMap)localObject);
        if ((paramBoolean) && (!H)) {
          a(localAe.c(m), (HashMap)localObject);
        }
      }
      i1 += 1;
    }
    paramArrayList1.addAll(((HashMap)localObject).keySet());
    Collections.sort(paramArrayList1, new Comparator()
    {
      private Collator a = Collator.getInstance(Application.j());
      
      public int a(String paramAnonymousString1, String paramAnonymousString2)
      {
        return this.a.compare(paramAnonymousString1, paramAnonymousString2);
      }
    });
  }
  
  private static void b(HashMap<String, Long> paramHashMap)
  {
    a(c, paramHashMap);
    a(d, paramHashMap);
  }
  
  public static void b(List<ae> paramList)
  {
    
    try
    {
      Collections.sort(paramList, a);
      return;
    }
    catch (Exception paramList) {}
  }
  
  private void b(String[] paramArrayOfString, UserHandle paramUserHandle)
  {
    if (paramArrayOfString != null)
    {
      int i2 = paramArrayOfString.length;
      int i1 = 0;
      while (i1 < i2)
      {
        d(paramArrayOfString[i1], paramUserHandle);
        i1 += 1;
      }
    }
    L();
  }
  
  private static boolean b(char paramChar)
  {
    if ((paramChar != ' ') && (paramChar != '"') && (paramChar != '[') && (paramChar != ']') && (paramChar != 'の')) {
      switch (paramChar)
      {
      default: 
        switch (paramChar)
        {
        default: 
          switch (paramChar)
          {
          default: 
            return false;
          }
          break;
        }
        break;
      }
    }
    return true;
  }
  
  public static boolean b(ae paramAe)
  {
    paramAe = paramAe.a();
    if (k.has(paramAe)) {}
    try
    {
      boolean bool = k.getBoolean(paramAe);
      return bool;
    }
    catch (JSONException paramAe)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean b(ae paramAe, String paramString)
  {
    boolean bool = TextUtils.isEmpty(paramString);
    int i1 = 0;
    if (bool) {
      j.remove(paramAe.a());
    }
    try
    {
      j.put(paramAe.a(), paramString);
      if (cd.a(j, new File(m.getFilesDir(), "icons")))
      {
        String str = paramString;
        if (TextUtils.isEmpty(paramString)) {
          str = null;
        }
        paramAe.b(str);
        if (paramAe.e()) {
          while (i1 < d.size())
          {
            paramString = (ae)d.get(i1);
            if (d.a(m, paramString.a()).d(paramAe.a()))
            {
              paramString.m();
              paramString.n();
            }
            i1 += 1;
          }
        }
        a(0L);
        return true;
      }
      return false;
    }
    catch (Exception paramAe) {}
    return false;
  }
  
  public static boolean b(String paramString1, String paramString2)
  {
    paramString1 = k(paramString1);
    paramString2 = paramString2.split(" |,");
    int i3 = paramString2.length;
    int i1 = 0;
    for (;;)
    {
      int i2 = 1;
      if (i1 >= i3) {
        break;
      }
      String str1 = paramString2[i1];
      if (str1.length() > 0)
      {
        while (paramString1.size() > 0)
        {
          String str2 = (String)paramString1.remove(0);
          if ((str2.length() > 0) && (g.a(str1, str2))) {
            break label84;
          }
        }
        i2 = 0;
        label84:
        if (i2 == 0) {
          return false;
        }
      }
      i1 += 1;
    }
    return true;
  }
  
  public static void c(Runnable paramRunnable)
  {
    Iterator localIterator = F.iterator();
    while (localIterator.hasNext())
    {
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      if ((localWeakReference == null) || (localWeakReference.get() == null) || (localWeakReference.get() == paramRunnable)) {
        localIterator.remove();
      }
    }
  }
  
  public static void c(String paramString)
  {
    d.b(m, paramString);
    paramString = (ae)e.remove(paramString);
    if (paramString != null)
    {
      d.remove(paramString);
      if (paramString.d() != null)
      {
        j.remove(paramString.a());
        cd.a(j, new File(m.getFilesDir(), "icons"));
      }
      P();
      m.A.a();
      a(0L);
    }
  }
  
  private void c(String paramString, UserHandle paramUserHandle)
  {
    d(paramString, paramUserHandle);
    ArrayList localArrayList = new ArrayList();
    a(paramString, paramUserHandle, localArrayList);
    if (m.y.b()) {
      a(localArrayList, m.y.f());
    }
    int i1 = 0;
    while (i1 < localArrayList.size())
    {
      paramUserHandle = (ae)localArrayList.get(i1);
      paramUserHandle.a(d(paramUserHandle));
      i1 += 1;
    }
    N();
    j(paramString);
  }
  
  public static void c(ArrayList<ae> paramArrayList)
  {
    int i1 = paramArrayList.size() - 1;
    while (i1 >= 0)
    {
      if (((ae)paramArrayList.get(i1)).e()) {
        paramArrayList.remove(i1);
      }
      i1 -= 1;
    }
  }
  
  private static void c(boolean paramBoolean)
  {
    int i1 = 0;
    while (i1 < c.size())
    {
      ae localAe = (ae)c.get(i1);
      localAe.m();
      if (paramBoolean) {
        localAe.f(m);
      }
      i1 += 1;
    }
  }
  
  static boolean c()
  {
    return d.size() > 0;
  }
  
  /* Error */
  private boolean c(ae paramAe)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 1076	com/ss/squarehome2/ae:o	()Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq +9 -> 17
    //   11: iconst_0
    //   12: istore_2
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_2
    //   16: ireturn
    //   17: aload_1
    //   18: invokevirtual 285	com/ss/squarehome2/ae:a	()Ljava/lang/String;
    //   21: astore_3
    //   22: getstatic 214	com/ss/squarehome2/Application:j	Lorg/json/JSONObject;
    //   25: aload_3
    //   26: invokevirtual 982	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   29: istore_2
    //   30: iload_2
    //   31: ifeq +14 -> 45
    //   34: aload_1
    //   35: getstatic 214	com/ss/squarehome2/Application:j	Lorg/json/JSONObject;
    //   38: aload_3
    //   39: invokevirtual 984	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   42: invokevirtual 986	com/ss/squarehome2/ae:b	(Ljava/lang/String;)V
    //   45: getstatic 119	com/ss/squarehome2/Application:d	Ljava/util/ArrayList;
    //   48: aload_1
    //   49: invokevirtual 355	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   52: pop
    //   53: getstatic 124	com/ss/squarehome2/Application:e	Ljava/util/HashMap;
    //   56: aload_1
    //   57: invokevirtual 285	com/ss/squarehome2/ae:a	()Ljava/lang/String;
    //   60: aload_1
    //   61: invokevirtual 851	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   64: pop
    //   65: iconst_1
    //   66: istore_2
    //   67: goto -54 -> 13
    //   70: astore_1
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_1
    //   74: athrow
    //   75: astore_3
    //   76: goto -31 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	Application
    //   0	79	1	paramAe	ae
    //   6	61	2	bool	boolean
    //   21	18	3	str	String
    //   75	1	3	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   2	7	70	finally
    //   17	30	70	finally
    //   34	45	70	finally
    //   45	65	70	finally
    //   34	45	75	org/json/JSONException
  }
  
  public static boolean c(String paramString1, String paramString2)
  {
    Object localObject = new JSONArray();
    int i1 = 0;
    while (i1 < g.length())
    {
      try
      {
        String str = g.getString(i1);
        if (str.equals(paramString1)) {
          ((JSONArray)localObject).put(paramString2);
        } else {
          ((JSONArray)localObject).put(str);
        }
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
      i1 += 1;
    }
    if (!cd.a((JSONArray)localObject, new File(m.getFilesDir(), "tags"))) {
      return false;
    }
    g = (JSONArray)localObject;
    if (h != null)
    {
      localObject = (LinkedList)h.remove(paramString1);
      h.put(paramString2, localObject);
    }
    localObject = new File(m.getFilesDir(), "tagData");
    new File((File)localObject, paramString1).renameTo(new File((File)localObject, paramString2));
    return true;
  }
  
  private static boolean c(List<ae> paramList)
  {
    JSONObject localJSONObject = new JSONObject();
    paramList = paramList.iterator();
    for (;;)
    {
      ae localAe;
      if (paramList.hasNext()) {
        localAe = (ae)paramList.next();
      }
      try
      {
        localJSONObject.put(localAe.a(), true);
      }
      catch (JSONException localJSONException) {}
      if (!cd.a(localJSONObject, new File(m.getFilesDir(), "hiddens"))) {
        return false;
      }
      k = localJSONObject;
      paramList = c.iterator();
      while (paramList.hasNext()) {
        ((ae)paramList.next()).f();
      }
      paramList = d.iterator();
      while (paramList.hasNext()) {
        ((ae)paramList.next()).f();
      }
      if (n == 0) {
        N();
      }
      m.A.a();
      a(0L);
      return true;
    }
  }
  
  public static q d()
  {
    return v;
  }
  
  public static ArrayList<ae> d(String paramString)
  {
    if ((paramString.startsWith("#")) && (paramString.substring(1).equals(m.getString(2131558625)))) {
      return Q();
    }
    Object localObject1 = h;
    int i1 = 0;
    if (localObject1 == null)
    {
      a(null, false);
      h = new HashMap(g.length());
    }
    Object localObject2 = new File(m.getFilesDir(), "tagData");
    ((File)localObject2).mkdirs();
    if (!h.containsKey(paramString))
    {
      localObject1 = new LinkedList();
      localObject2 = cd.b(new File((File)localObject2, paramString));
    }
    for (;;)
    {
      if ((localObject2 != null) && (i1 < ((JSONArray)localObject2).length())) {}
      try
      {
        ((LinkedList)localObject1).add(((JSONArray)localObject2).getString(i1));
        i1 += 1;
        continue;
        h.put(paramString, localObject1);
        localObject1 = new ArrayList();
        paramString = ((LinkedList)h.get(paramString)).iterator();
        while (paramString.hasNext())
        {
          localObject2 = (String)paramString.next();
          if (a((String)localObject2) != null) {
            ((ArrayList)localObject1).add(a((String)localObject2));
          }
        }
        return localObject1;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  private void d(String paramString, UserHandle paramUserHandle)
  {
    try
    {
      boolean bool = TextUtils.isEmpty(paramString);
      if (bool) {
        return;
      }
      int i1 = c.size() - 1;
      while (i1 >= 0)
      {
        ae localAe = (ae)c.get(i1);
        if (localAe.a(paramString, paramUserHandle))
        {
          c.remove(i1);
          e.remove(localAe.a());
        }
        i1 -= 1;
      }
      return;
    }
    finally {}
  }
  
  public static void d(ArrayList<ae> paramArrayList)
  {
    int i1 = paramArrayList.size() - 1;
    while (i1 >= 0)
    {
      if (((ae)paramArrayList.get(i1)).s()) {
        paramArrayList.remove(i1);
      }
      i1 -= 1;
    }
  }
  
  private static void d(boolean paramBoolean)
  {
    int i1 = 0;
    while (i1 < d.size())
    {
      ae localAe = (ae)d.get(i1);
      localAe.m();
      localAe.n();
      if (paramBoolean)
      {
        localAe.f(m);
        localAe.h(m);
      }
      i1 += 1;
    }
  }
  
  private static boolean d(ae paramAe)
  {
    int i1 = 0;
    while (i1 < d.size())
    {
      ae localAe = (ae)d.get(i1);
      if (d.a(m, localAe.a()).d(paramAe.a())) {
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  public static com.ss.launcher.c.c e()
  {
    return m.x;
  }
  
  public static boolean e(String paramString)
  {
    g.put(paramString);
    return cd.a(g, new File(m.getFilesDir(), "tags"));
  }
  
  public static com.ss.launcher.counter.b f()
  {
    return m.w;
  }
  
  public static boolean f(String paramString)
  {
    JSONArray localJSONArray = new JSONArray();
    int i1 = 0;
    for (;;)
    {
      if (i1 < g.length()) {}
      try
      {
        String str = g.getString(i1);
        if (!str.equals(paramString)) {
          localJSONArray.put(str);
        }
        i1 += 1;
        continue;
        if (!cd.a(localJSONArray, new File(m.getFilesDir(), "tags"))) {
          return false;
        }
        g = localJSONArray;
        if (h != null) {
          h.remove(paramString);
        }
        new File(new File(m.getFilesDir(), "tagData"), paramString).delete();
        return true;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  public static Handler g()
  {
    return f;
  }
  
  public static boolean g(String paramString)
  {
    if (l != null)
    {
      int i1 = 0;
      while (i1 < l.length())
      {
        try
        {
          boolean bool = l.getString(i1).equals(paramString);
          if (bool) {
            return true;
          }
        }
        catch (JSONException localJSONException)
        {
          for (;;) {}
        }
        i1 += 1;
      }
    }
    return false;
  }
  
  public static void h()
  {
    Thread local13 = new Thread()
    {
      public void run()
      {
        Application.f(Application.A()).a(true, true, true);
      }
    };
    local13.setPriority(1);
    local13.start();
  }
  
  public static void h(String paramString)
  {
    Iterator localIterator = d.iterator();
    while (localIterator.hasNext())
    {
      ae localAe = (ae)localIterator.next();
      if (localAe.a().equals(paramString))
      {
        localAe.m();
        localAe.n();
        localAe.k();
        localAe.l();
        localAe.f(m);
        localAe.h(m);
        P();
        localAe.a(m, m.w);
        m.A.a();
        a(500L);
      }
    }
  }
  
  private JSONArray i(String paramString)
  {
    if ((ak.a(paramString)) && (!a(this, false)) && (b(this) <= 0L)) {
      return null;
    }
    paramString = ak.a(this, paramString, null);
    if (paramString != null) {}
    try
    {
      paramString = new JSONArray(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
    return null;
  }
  
  public static void i()
  {
    m.w.a();
  }
  
  public static Locale j()
  {
    if (m.C != null) {
      return m.C;
    }
    return m.a(m.getApplicationContext().getResources().getConfiguration());
  }
  
  private void j(String paramString)
  {
    final String str = ak.a(getApplicationContext(), "iconPack", ak.b);
    if (TextUtils.equals(paramString, str))
    {
      f.post(new Runnable()
      {
        public void run()
        {
          com.ss.iconpack.b.a(Application.this.getApplicationContext(), str);
          Application.u();
        }
      });
    }
    else
    {
      d(true);
      a(0L);
    }
    this.A.a();
  }
  
  private static List<String> k(String paramString)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge Z and I\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static boolean k()
  {
    String str = j().getLanguage();
    switch (str.hashCode())
    {
    default: 
      break;
    case 3734: 
      if (str.equals("uk")) {
        i1 = 13;
      }
      break;
    case 3679: 
      if (str.equals("sr")) {
        i1 = 12;
      }
      break;
    case 3678: 
      if (str.equals("sq")) {
        i1 = 11;
      }
      break;
    case 3673: 
      if (str.equals("sl")) {
        i1 = 10;
      }
      break;
    case 3672: 
      if (str.equals("sk")) {
        i1 = 9;
      }
      break;
    case 3645: 
      if (str.equals("ro")) {
        i1 = 8;
      }
      break;
    case 3521: 
      if (str.equals("no")) {
        i1 = 7;
      }
      break;
    case 3370: 
      if (str.equals("is")) {
        i1 = 6;
      }
      break;
    case 3338: 
      if (str.equals("hr")) {
        i1 = 5;
      }
      break;
    case 3247: 
      if (str.equals("et")) {
        i1 = 4;
      }
      break;
    case 3197: 
      if (str.equals("da")) {
        i1 = 3;
      }
      break;
    case 3184: 
      if (str.equals("cs")) {
        i1 = 2;
      }
      break;
    case 3141: 
      if (str.equals("bg")) {
        i1 = 0;
      }
      break;
    case 3139: 
      if (str.equals("be")) {
        i1 = 1;
      }
      break;
    }
    int i1 = -1;
    switch (i1)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean l()
  {
    return E;
  }
  
  public static void m()
  {
    H = true;
    I = new q.a()
    {
      private int a = 0;
      private int b = 0;
      
      public void a()
      {
        try
        {
          while ((this.a < Application.D().size()) && (this == Application.E()))
          {
            ((ae)Application.D().get(this.a)).f(Application.A());
            if (Application.F().a() > 0)
            {
              if (this != Application.E()) {
                break label193;
              }
              Application.F().a(this);
              return;
            }
            this.a += 1;
          }
          while ((this.b < Application.G().size()) && (this == Application.E()))
          {
            ae localAe = (ae)Application.G().get(this.b);
            localAe.f(Application.A());
            localAe.h(Application.A());
            if (Application.F().a() > 0)
            {
              if (this != Application.E()) {
                return;
              }
              Application.F().a(this);
              return;
            }
            this.b += 1;
          }
          if (this == Application.E())
          {
            Application.b(null);
            Application.b(false);
            Application.g(Application.A()).a();
          }
          return;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          return;
        }
        label193:
        return;
      }
      
      public void run() {}
    };
    v.a(I);
  }
  
  public static void n()
  {
    J = new JSONArray();
    new File(m.getFilesDir(), "userSort").delete();
    N();
    a(0L);
  }
  
  public static long o()
  {
    if ((m != null) && (m.y != null)) {
      return m.y.g();
    }
    return 0L;
  }
  
  public static ArrayList<String> p()
  {
    return b.a(m.A);
  }
  
  public static ArrayList<ae> q()
  {
    ArrayList localArrayList = new ArrayList(c.size());
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext())
    {
      ae localAe = (ae)localIterator.next();
      if (localAe.r()) {
        localArrayList.add(localAe);
      }
    }
    return localArrayList;
  }
  
  public static boolean r()
  {
    i = new JSONObject();
    int i3 = 0;
    int i1 = 0;
    int i2;
    for (;;)
    {
      i2 = i3;
      if (i1 >= c.size()) {
        break;
      }
      ((ae)c.get(i1)).a(null);
      i1 += 1;
    }
    while (i2 < d.size())
    {
      ((ae)d.get(i2)).a(null);
      i2 += 1;
    }
    m.A.a();
    a(0L);
    return cd.a(i, new File(m.getFilesDir(), "labels"));
  }
  
  public static boolean s()
  {
    j = new JSONObject();
    int i3 = 0;
    int i1 = 0;
    int i2;
    for (;;)
    {
      i2 = i3;
      if (i1 >= c.size()) {
        break;
      }
      ((ae)c.get(i1)).b(null);
      i1 += 1;
    }
    while (i2 < d.size())
    {
      d localD = d.a(m, ((ae)d.get(i2)).a());
      localD.b(null);
      localD.c(null);
      i2 += 1;
    }
    m();
    a(0L);
    return cd.a(j, new File(m.getFilesDir(), "icons"));
  }
  
  public static boolean t()
  {
    if (m.y.c())
    {
      b(m.y.f());
      m.x.e();
      return true;
    }
    return false;
  }
  
  public static void u()
  {
    o = false;
    com.ss.iconpack.b.a(a());
    c(false);
    d(false);
    a(0L);
    m();
  }
  
  public static File v()
  {
    return m.y.a();
  }
  
  public static void w()
  {
    Toast.makeText(m, 2131558786, 1).show();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (!a(paramConfiguration).equals(this.C))
    {
      E = false;
      D = false;
      G = null;
      c.clear();
      d.clear();
      e.clear();
      K();
      this.C = a(paramConfiguration);
      I();
      MainActivity.a(false);
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    m = this;
    this.C = j();
    f = new Handler();
    this.A = new b();
    com.github.ajalt.reprint.a.c.a(this);
    I();
    if (Build.VERSION.SDK_INT >= 17) {
      com.ss.iconpack.b.a("https://total-launcher.blogspot.com/p/icon-packs.html");
    }
    com.ss.iconpack.b.a(a());
    com.ss.iconpack.b.a(m, ak.a(m, "iconPack", ak.b));
    J();
    K();
    l = i("appsToShowNoti");
    this.w.a(this, new Runnable()
    {
      public void run()
      {
        Application.b(Application.this);
      }
    }, true, true);
    this.x = new com.ss.launcher.c.c(this, f);
    this.x.a(new Runnable()
    {
      public void run()
      {
        if (Application.l())
        {
          Application.x();
          Application.a(0L);
        }
      }
    });
    this.y = new com.ss.launcher.c.b(this, this.x);
    Object localObject = new q.a()
    {
      public void a()
      {
        Application.c(Application.this).d();
      }
      
      public void run()
      {
        if (Application.y())
        {
          Application.a(Application.c(Application.this).f());
          Application.d(Application.this).e();
        }
      }
    };
    v.a((q.a)localObject);
    if (Build.VERSION.SDK_INT >= 21)
    {
      ((LauncherApps)getSystemService("launcherapps")).registerCallback(new LauncherApps.Callback()
      {
        public void onPackageAdded(String paramAnonymousString, UserHandle paramAnonymousUserHandle)
        {
          Application.b(Application.this, paramAnonymousString, paramAnonymousUserHandle);
        }
        
        public void onPackageChanged(String paramAnonymousString, UserHandle paramAnonymousUserHandle)
        {
          Application.c(Application.this, paramAnonymousString, paramAnonymousUserHandle);
        }
        
        public void onPackageRemoved(String paramAnonymousString, UserHandle paramAnonymousUserHandle)
        {
          Application.a(Application.this, paramAnonymousString, paramAnonymousUserHandle);
        }
        
        public void onPackagesAvailable(String[] paramAnonymousArrayOfString, UserHandle paramAnonymousUserHandle, boolean paramAnonymousBoolean)
        {
          Application.a(Application.this, paramAnonymousArrayOfString, paramAnonymousUserHandle);
        }
        
        public void onPackagesUnavailable(String[] paramAnonymousArrayOfString, UserHandle paramAnonymousUserHandle, boolean paramAnonymousBoolean)
        {
          Application.b(Application.this, paramAnonymousArrayOfString, paramAnonymousUserHandle);
        }
        
        public void onShortcutsChanged(String paramAnonymousString, List<ShortcutInfo> paramAnonymousList, UserHandle paramAnonymousUserHandle)
        {
          super.onShortcutsChanged(paramAnonymousString, paramAnonymousList, paramAnonymousUserHandle);
          Application.e(Application.this);
        }
      });
    }
    else
    {
      this.B = new a(null);
      localObject = new IntentFilter("android.intent.action.PACKAGE_ADDED");
      ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
      ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_CHANGED");
      ((IntentFilter)localObject).addDataScheme("package");
      registerReceiver(this.B, (IntentFilter)localObject);
      localObject = new IntentFilter();
      ((IntentFilter)localObject).addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
      ((IntentFilter)localObject).addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
      registerReceiver(this.B, (IntentFilter)localObject);
    }
    registerReceiver(this.z, new IntentFilter("android.intent.action.SCREEN_ON"));
    n = ak.a(this, "sortBy", 0);
    ak.b(this).registerOnSharedPreferenceChangeListener(this);
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    boolean bool = paramString.equals("sortBy");
    int i3 = 0;
    int i2 = 0;
    if ((!bool) && (!paramString.equals("smartPickNum")))
    {
      bool = paramString.equals("searchEnLabel");
      int i1 = 1;
      if (bool)
      {
        if ((j().getLanguage().equals("en")) || (!ak.a(this, "searchEnLabel", true))) {
          i1 = 0;
        }
        if (i1 == 0)
        {
          i1 = i2;
          while (i1 < c.size())
          {
            ((ae)c.get(i1)).l();
            i1 += 1;
          }
        }
      }
      else
      {
        if (!paramString.equals("searchInFolder")) {
          break label135;
        }
      }
      this.A.a();
      return;
      label135:
      if (paramString.equals("iconPack"))
      {
        ak.c(this, "newIconPack", true);
        com.ss.iconpack.b.a(this, ak.a(this, paramString, ak.b));
      }
      while (paramString.equals("iconSize"))
      {
        u();
        return;
      }
      if (paramString.startsWith("tileBackground_"))
      {
        be.c(Integer.parseInt(paramString.substring("tileBackground_".length())));
        return;
      }
      if (paramString.equals("appsToShowNoti"))
      {
        l = i(paramString);
        i1 = i3;
        while (i1 < d.size())
        {
          ((ae)d.get(i1)).a(this, this.w);
          i1 += 1;
        }
        b(0L);
        return;
      }
      if ((paramString.equals("missedCalls")) || (paramString.equals("unreadMessages")) || (paramString.equals("unreadGmails")) || (paramString.equals("thirdPartyCounter")) || (paramString.equals("useNotiIcon"))) {
        H();
      }
    }
    else
    {
      n = ak.a(this, "sortBy", 0);
      N();
      a(0L);
    }
  }
  
  private class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Application.f(Application.this).g();
      paramContext = paramIntent.getAction();
      if ((!"android.intent.action.PACKAGE_CHANGED".equals(paramContext)) && (!"android.intent.action.PACKAGE_ADDED".equals(paramContext)) && (!"android.intent.action.PACKAGE_REMOVED".equals(paramContext)))
      {
        if ("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(paramContext))
        {
          paramContext = paramIntent.getStringArrayExtra("android.intent.extra.changed_package_list");
          Application.a(Application.this, paramContext, null);
          return;
        }
        if ("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(paramContext))
        {
          paramContext = paramIntent.getStringArrayExtra("android.intent.extra.changed_package_list");
          Application.b(Application.this, paramContext, null);
        }
      }
      else
      {
        paramIntent = paramIntent.getData().getSchemeSpecificPart();
        if ("android.intent.action.PACKAGE_CHANGED".equals(paramContext))
        {
          Application.c(Application.this, paramIntent, null);
          return;
        }
        if ("android.intent.action.PACKAGE_REMOVED".equals(paramContext))
        {
          Application.a(Application.this, paramIntent, null);
          return;
        }
        Application.b(Application.this, paramIntent, null);
      }
    }
  }
  
  private static class b
  {
    private ArrayList<String> a = new ArrayList();
    
    b()
    {
      Object localObject = new File(Application.A().getCacheDir(), "searchInitials");
      int i;
      if (((File)localObject).exists())
      {
        localObject = cd.b((File)localObject);
        if (localObject != null) {
          i = 0;
        }
      }
      for (;;)
      {
        if (i < ((JSONArray)localObject).length()) {}
        try
        {
          this.a.add(((JSONArray)localObject).getString(i));
          i += 1;
          continue;
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;) {}
        }
      }
    }
    
    void a()
    {
      for (;;)
      {
        String str;
        try
        {
          this.a.clear();
          boolean bool2 = Application.j().getLanguage().equals("en");
          bool1 = true;
          if ((!bool2) && (ak.a(Application.A(), "searchEnLabel", true)))
          {
            Application.a(Application.D(), this.a, bool1);
            Application.a(Application.G(), this.a, bool1);
            CharSequence localCharSequence = null;
            Iterator localIterator = this.a.iterator();
            if (localIterator.hasNext())
            {
              str = (String)localIterator.next();
              if (!TextUtils.equals(str, localCharSequence)) {
                break label159;
              }
              localIterator.remove();
              continue;
            }
            cd.a(new JSONArray(this.a), new File(Application.A().getCacheDir(), "searchInitials"));
            return;
          }
        }
        finally {}
        boolean bool1 = false;
        continue;
        label159:
        Object localObject2 = str;
      }
    }
  }
}
