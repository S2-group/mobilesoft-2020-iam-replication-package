package iqzone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class am
  implements ew
{
  private static final Logger a = LoggerFactory.getLogger(am.class);
  private static boolean f;
  private final ag b = new ag(Looper.getMainLooper());
  private final Context c;
  private final Set<fa> d = Collections.synchronizedSet(new HashSet());
  private final ExecutorService e;
  private String g;
  private String h;
  private String i;
  private int[] j = null;
  private Boolean k;
  private String l;
  private String m;
  private String n;
  
  public am(final Context paramContext, final ExecutorService paramExecutorService)
  {
    this.c = paramContext.getApplicationContext();
    this.e = paramExecutorService;
    this.b.post(new Runnable()
    {
      public void run()
      {
        am.a(am.this, new WebView(paramContext).getSettings().getUserAgentString());
      }
    });
    paramExecutorService = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        am.E().debug("TIMINGLOGS networkstatebroadcast onReceive");
        try
        {
          if (paramAnonymousIntent.getExtras() != null)
          {
            paramAnonymousContext = (NetworkInfo)paramAnonymousIntent.getExtras().get("networkInfo");
            if ((paramAnonymousContext != null) && (paramAnonymousContext.getState() == NetworkInfo.State.CONNECTED))
            {
              am.E().debug("connected leak");
              am.E().debug("timinglogs networkListeners.size 0 = " + am.a(am.this).size());
              paramExecutorService.execute(new Runnable()
              {
                public void run()
                {
                  gy.a(false);
                  am.E().debug("timinglogs networkListeners.size 1 = " + am.a(am.this).size());
                  Object localObject = new HashSet(am.a(am.this));
                  am.E().debug("timinglogs networkListeners = " + am.a(am.this));
                  localObject = ((HashSet)localObject).iterator();
                  while (((Iterator)localObject).hasNext())
                  {
                    fa localFa = (fa)((Iterator)localObject).next();
                    am.E().debug("timinglogs v = " + localFa);
                    localFa.a(true);
                  }
                }
              });
              return;
            }
            paramAnonymousContext = new HashSet(am.a(am.this)).iterator();
            while (paramAnonymousContext.hasNext()) {
              ((fa)paramAnonymousContext.next()).a(false);
            }
          }
          return;
        }
        catch (Throwable paramAnonymousContext)
        {
          am.E().error("ERROR:", paramAnonymousContext);
        }
      }
    };
    a().a(new Runnable()
    {
      public void run()
      {
        if (!am.F())
        {
          am.a(true);
          paramContext.registerReceiver(paramExecutorService, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
      }
    });
  }
  
  private static int[] a(WindowManager paramWindowManager)
  {
    Display localDisplay = paramWindowManager.getDefaultDisplay();
    Object localObject = new DisplayMetrics();
    localDisplay.getMetrics((DisplayMetrics)localObject);
    paramWindowManager = new int[2];
    paramWindowManager[0] = ((DisplayMetrics)localObject).widthPixels;
    paramWindowManager[1] = ((DisplayMetrics)localObject).heightPixels;
    if ((Build.VERSION.SDK_INT >= 14) && (Build.VERSION.SDK_INT < 17)) {}
    try
    {
      paramWindowManager[0] = ((Integer)Display.class.getMethod("getRawWidth", new Class[0]).invoke(localDisplay, new Object[0])).intValue();
      paramWindowManager[1] = ((Integer)Display.class.getMethod("getRawHeight", new Class[0]).invoke(localDisplay, new Object[0])).intValue();
      if (Build.VERSION.SDK_INT < 17) {}
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        try
        {
          localObject = new Point();
          Display.class.getMethod("getRealSize", new Class[] { Point.class }).invoke(localDisplay, new Object[] { localObject });
          paramWindowManager[0] = ((Point)localObject).x;
          paramWindowManager[1] = ((Point)localObject).y;
          return paramWindowManager;
        }
        catch (Exception localException1)
        {
          a.warn("ignoring this: ", localException1);
        }
        localException2 = localException2;
        a.warn("ignoring this: ", localException2);
      }
    }
    return paramWindowManager;
  }
  
  private static boolean b(WindowManager paramWindowManager)
  {
    boolean bool = true;
    int[] arrayOfInt = a(paramWindowManager);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    double d1 = Math.pow(arrayOfInt[0] / localDisplayMetrics.xdpi, 2.0D);
    if (Math.sqrt(Math.pow(arrayOfInt[1] / localDisplayMetrics.ydpi, 2.0D) + d1) >= 7.0D) {}
    for (;;)
    {
      a.debug("istablet = " + bool);
      return bool;
      bool = false;
    }
  }
  
  public String A()
  {
    if (this.n != null) {
      return this.n;
    }
    Object localObject1 = "";
    try
    {
      Object localObject2 = this.c.getResources();
      localObject2 = ((Resources)localObject2).getText(((Resources)localObject2).getIdentifier("app_name", "string", this.c.getPackageName())).toString();
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a.debug("Couldn't get App Name: " + localException);
      }
    }
    this.n = ((String)localObject1);
    return localObject1;
  }
  
  public List<String> B()
  {
    try
    {
      Object localObject = this.c.getPackageManager().getInstalledApplications(128);
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localArrayList.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      a.error("ERROR", localThrowable);
      return new ArrayList();
    }
  }
  
  public HashMap<String, String> C()
  {
    a.debug("getting location");
    HashMap localHashMap = new HashMap();
    Object localObject = null;
    try
    {
      Location localLocation = ((LocationManager)this.c.getSystemService("location")).getLastKnownLocation("gps");
      localObject = localLocation;
      if (localLocation != null)
      {
        localObject = localLocation;
        localHashMap.put("GPS_FOUND", "true");
        localObject = localLocation;
        localHashMap.put("GPS_LAT", String.valueOf(localLocation.getLatitude()));
        localObject = localLocation;
        localHashMap.put("GPS_LONG", String.valueOf(localLocation.getLongitude()));
        localObject = localLocation;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        a.error("ERROR from getLocation(): ", localThrowable);
      }
    }
    if (localObject == null)
    {
      localHashMap.put("GPS_FOUND", "false");
      localHashMap.put("GPS_LAT", "0");
      localHashMap.put("GPS_LONG", "0");
    }
    a.debug("returning location");
    return localHashMap;
  }
  
  public String D()
  {
    return "";
  }
  
  public ex a(File paramFile)
  {
    return new ah(new BitmapDrawable(BitmapFactory.decodeFile(paramFile.getAbsolutePath())));
  }
  
  public ey a()
  {
    new ey()
    {
      public void a(Runnable paramAnonymousRunnable)
      {
        am.b(am.this).post(paramAnonymousRunnable);
      }
      
      public void a(Runnable paramAnonymousRunnable, long paramAnonymousLong)
      {
        am.b(am.this).postDelayed(paramAnonymousRunnable, paramAnonymousLong);
      }
    };
  }
  
  public gj a(ew paramEw, Map<String, String> paramMap, gk paramGk)
  {
    return new cv(this.c, this, paramMap, paramGk, this.e);
  }
  
  public u a(int paramInt, x paramX, ExecutorService paramExecutorService)
  {
    switch (paramInt)
    {
    default: 
      return null;
    }
    try
    {
      return new ch(this, paramX, paramExecutorService);
    }
    catch (Throwable paramX) {}
    return new aq(this, paramX, paramExecutorService);
    return new bp(this, paramX, paramExecutorService);
    return new bf(this, paramX, paramExecutorService);
    return new av(this, paramX, paramExecutorService);
    return new ba(this, paramX, paramExecutorService);
    return new ca(this, paramX, paramExecutorService);
    return new bv(this, paramX, paramExecutorService);
    a.debug("getRefreshable case = AppMonetAdModule");
    paramX = new bk(this, paramX, paramExecutorService);
    return paramX;
    return null;
  }
  
  public File a(String paramString)
  {
    return this.c.getDir(paramString, 0);
  }
  
  public void a(ew paramEw, Map<String, String> paramMap, gk paramGk, ml<Void, fw> paramMl)
  {
    new gw(this.c, paramEw, paramMap, paramGk, this.e, paramMl);
  }
  
  public void a(fa paramFa)
  {
    this.d.add(paramFa);
  }
  
  public void a(Runnable paramRunnable, ey paramEy)
  {
    try
    {
      Looper localLooper1 = Looper.getMainLooper();
      Looper localLooper2 = Looper.myLooper();
      if (localLooper1 == localLooper2) {
        try
        {
          paramRunnable.run();
          return;
        }
        catch (Throwable paramRunnable)
        {
          a.error("ERROR:", paramRunnable);
          return;
        }
      }
      paramEy.a(paramRunnable);
    }
    catch (Throwable paramRunnable)
    {
      a.error("ERROR running thread: ", paramRunnable);
      return;
    }
  }
  
  public gj b(ew paramEw, Map<String, String> paramMap, gk paramGk)
  {
    return new cu(this.c, paramEw, paramMap, paramGk, this.e);
  }
  
  public void b(fa paramFa)
  {
    this.d.remove(paramFa);
  }
  
  public boolean b()
  {
    return this.c.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
  }
  
  public gj c(ew paramEw, Map<String, String> paramMap, gk paramGk)
  {
    return new cw(this.c, paramEw, paramMap, paramGk, this.e);
  }
  
  public File c()
  {
    return Environment.getExternalStorageDirectory();
  }
  
  public gj d(ew paramEw, Map<String, String> paramMap, gk paramGk)
  {
    return new ct(this.c, paramEw, paramMap, paramGk, this.e);
  }
  
  public File d()
  {
    return this.c.getCacheDir();
  }
  
  public fc e()
  {
    a.debug("createViewGroup");
    return new ak(this, new RelativeLayout(this.c));
  }
  
  public gj e(ew paramEw, Map<String, String> paramMap, gk paramGk)
  {
    return new cq(this.c, paramEw, paramMap, paramGk, this.e);
  }
  
  public gj f(ew paramEw, Map<String, String> paramMap, gk paramGk)
  {
    return new cn(this.c, paramEw, paramMap, paramGk, this.e);
  }
  
  public boolean f()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.c.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }
  
  public String g()
  {
    if (this.g != null) {
      return this.g;
    }
    String str3 = "";
    try
    {
      localObject = ao.a(this.c);
      if (localObject == null) {}
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        String str1;
        for (Object localObject = ((an)localObject).a();; str1 = "")
        {
          this.g = ((String)localObject);
          return this.g;
          localThrowable1 = localThrowable1;
          a.error("ERROR: " + localThrowable1.getMessage(), localThrowable1);
          str1 = null;
          break;
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          a.warn("error getting ad info", localThrowable2);
          String str2 = str3;
        }
      }
    }
  }
  
  public String h()
  {
    if (this.h == null) {
      return "";
    }
    return this.h;
  }
  
  public String i()
  {
    if (this.i != null) {
      return this.i;
    }
    ny localNy = new ny();
    try
    {
      this.i = localNy.a(Build.MODEL);
      return this.i;
    }
    catch (nr localNr)
    {
      for (;;)
      {
        a.error("ERRORL:", localNr);
        this.i = "unknown";
      }
    }
  }
  
  public String j()
  {
    NetworkInfo.State localState = null;
    Object localObject2 = (ConnectivityManager)this.c.getSystemService("connectivity");
    Object localObject1 = ((ConnectivityManager)localObject2).getNetworkInfo(0);
    if (localObject1 != null) {}
    for (localObject1 = ((NetworkInfo)localObject1).getState();; localObject1 = null)
    {
      localObject2 = ((ConnectivityManager)localObject2).getNetworkInfo(1);
      if (localObject2 != null) {
        localState = ((NetworkInfo)localObject2).getState();
      }
      if ((localObject1 != NetworkInfo.State.CONNECTED) && (localObject1 != NetworkInfo.State.CONNECTING)) {
        break;
      }
      return "mobile";
    }
    if ((localState == NetworkInfo.State.CONNECTED) || (localState == NetworkInfo.State.CONNECTING)) {
      return "wifi";
    }
    return "";
  }
  
  public String k()
  {
    return Build.BRAND;
  }
  
  public String l()
  {
    return Build.MANUFACTURER;
  }
  
  public String m()
  {
    return Build.PRODUCT;
  }
  
  public String n()
  {
    return Build.MODEL;
  }
  
  public String o()
  {
    a.debug("getting network class");
    for (;;)
    {
      try
      {
        switch (((TelephonyManager)this.c.getSystemService("phone")).getNetworkType())
        {
        case 1: 
          a.debug("getting network class blank");
          return "";
        }
      }
      catch (Exception localException)
      {
        a.debug("Exception ignored while getting network class: " + localException);
        return "";
      }
      a.debug("getting network class 2G");
      return "2G";
      a.debug("getting network class 3G");
      return "3G";
      a.debug("getting network class 4G");
      return "4G";
    }
  }
  
  public String p()
  {
    try
    {
      float f1 = this.c.getResources().getDisplayMetrics().density;
      return String.valueOf(f1);
    }
    catch (Exception localException)
    {
      a.warn("ignoring this: ", localException);
    }
    return "";
  }
  
  public int[] q()
  {
    Display localDisplay;
    int[] arrayOfInt;
    if (this.j == null)
    {
      localDisplay = ((WindowManager)this.c.getSystemService("window")).getDefaultDisplay();
      arrayOfInt = new int[2];
      int[] tmp31_30 = arrayOfInt;
      tmp31_30[0] = -1;
      int[] tmp35_31 = tmp31_30;
      tmp35_31[1] = -1;
      tmp35_31;
    }
    try
    {
      Point localPoint = new Point();
      localDisplay.getSize(localPoint);
      arrayOfInt[0] = localPoint.x;
      arrayOfInt[1] = localPoint.y;
      this.j = arrayOfInt;
      return (int[])this.j.clone();
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;)
      {
        arrayOfInt[0] = localDisplay.getWidth();
        arrayOfInt[1] = localDisplay.getHeight();
      }
    }
  }
  
  public String r()
  {
    return "android";
  }
  
  public String s()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public String t()
  {
    return Build.VERSION.RELEASE;
  }
  
  public boolean u()
  {
    if (this.k != null) {
      return this.k.booleanValue();
    }
    boolean bool = b((WindowManager)this.c.getSystemService("window"));
    this.k = Boolean.valueOf(bool);
    return bool;
  }
  
  public String v()
  {
    return this.c.getPackageName();
  }
  
  public Context w()
  {
    return this.c;
  }
  
  public String x()
  {
    return ((TelephonyManager)this.c.getSystemService("phone")).getNetworkOperatorName();
  }
  
  public String y()
  {
    if (this.l != null) {
      return this.l;
    }
    this.l = Settings.Secure.getString(this.c.getContentResolver(), "android_id");
    return this.l;
  }
  
  public String z()
  {
    if (this.m != null) {
      return this.m;
    }
    try
    {
      if (gv.a(this.c, "android.permission.READ_PHONE_STATE"))
      {
        String str1 = ((TelephonyManager)this.c.getSystemService("phone")).getDeviceId();
        this.m = str1;
        return str1;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        a.error("ERROR from getIMEI(): ", localThrowable);
        String str2 = "";
      }
    }
  }
}
