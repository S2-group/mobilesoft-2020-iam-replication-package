package com.plexapp.plex.application;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Pair;
import com.plexapp.plex.application.h.n;
import com.plexapp.plex.application.h.q;
import com.plexapp.plex.net.ar;
import com.plexapp.plex.net.ax;
import com.plexapp.plex.net.bq;
import com.plexapp.plex.utilities.bh;
import com.plexapp.plex.utilities.dm;
import com.plexapp.plex.utilities.du;
import com.plexapp.plex.videoplayer.f;
import com.plexapp.plex.videoplayer.h;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class g
{
  public static g a;
  private Boolean b;
  private String c;
  private HashMap<String, Boolean> d = new HashMap();
  
  protected g() {}
  
  public static g B()
  {
    if (a == null) {
      a = new g();
    }
    return a;
  }
  
  private boolean C()
  {
    String str = g();
    return (str.contains("bueller")) || (str.startsWith("AFTB"));
  }
  
  private boolean D()
  {
    if (!h().equalsIgnoreCase("wetek")) {}
    String str;
    do
    {
      return false;
      str = e();
    } while ((!str.equals("wetekcore")) && (!str.equals("wetekhub")) && (!str.equals("wetekplay2")));
    return true;
  }
  
  private static ApplicationInfo d(String paramString)
  {
    Iterator localIterator = PlexApplication.b().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.equals(paramString)) {
        return localApplicationInfo;
      }
    }
    return null;
  }
  
  private static boolean e(String paramString)
  {
    return paramString.equals("192.168.49.1");
  }
  
  public boolean A()
  {
    return (t()) || (D());
  }
  
  public Map<String, Map<String, String>> a(ar paramAr)
  {
    HashMap localHashMap1;
    if (y())
    {
      paramAr = new HashMap();
      paramAr.put("bitrate", "448");
      localHashMap1 = new HashMap();
      localHashMap1.put("ac3", paramAr);
      localHashMap1.put("eac3", paramAr);
      return localHashMap1;
    }
    if ((u()) && ((paramAr.a("height", 1081) > 1080) || (paramAr.a("width", 1921) > 1920)))
    {
      localHashMap1 = new HashMap();
      localHashMap1.put("level", h.b.b());
      localHashMap1.put("width", "3840");
      localHashMap1.put("height", "2160");
      localHashMap1.put("bitrate", "20000");
      localHashMap1.put("bitDepth", "8");
      HashMap localHashMap2 = new HashMap();
      paramAr = ((ax)paramAr.a().get(0)).b(1);
      if ((paramAr != null) && (paramAr.c("level"))) {
        localHashMap2.put("level", "150");
      }
      for (;;)
      {
        localHashMap2.put("width", "3840");
        localHashMap2.put("height", "2160");
        localHashMap2.put("bitrate", "25000");
        localHashMap2.put("bitDepth", "8");
        paramAr = new HashMap();
        paramAr.put("h264", localHashMap1);
        paramAr.put("hevc", localHashMap2);
        return paramAr;
        localHashMap2.put("frameRate", "23.97");
      }
    }
    return null;
  }
  
  boolean a()
  {
    String str = f();
    return (str.equals("sdk")) || (str.contains("_sdk")) || (str.contains("sdk_"));
  }
  
  public boolean a(String paramString)
  {
    return d(paramString) != null;
  }
  
  public boolean b()
  {
    return false;
  }
  
  public boolean b(String paramString)
  {
    paramString = d(paramString);
    return (paramString != null) && (paramString.enabled);
  }
  
  public boolean c(String paramString)
  {
    if (!this.d.containsKey(paramString)) {
      this.d.put(paramString, Boolean.valueOf(PlexApplication.b().getPackageManager().hasSystemFeature(paramString)));
    }
    return ((Boolean)this.d.get(paramString)).booleanValue();
  }
  
  public String[] c()
  {
    return PlexApplication.b().getPackageManager().getSystemSharedLibraryNames();
  }
  
  public String d()
  {
    return Build.BRAND;
  }
  
  public String e()
  {
    return Build.DEVICE;
  }
  
  public String f()
  {
    return Build.PRODUCT;
  }
  
  public String g()
  {
    return Build.MODEL;
  }
  
  public String h()
  {
    return Build.MANUFACTURER;
  }
  
  public String i()
  {
    return Build.CPU_ABI;
  }
  
  public long j()
  {
    return System.currentTimeMillis();
  }
  
  public String k()
  {
    Object localObject;
    if (this.c == null)
    {
      localObject = new q("general.uuid", n.a);
      if (!((q)localObject).e()) {
        break label41;
      }
    }
    for (this.c = ((q)localObject).c();; this.c = String.format("%s-%s", new Object[] { du.a((Context)localObject), ((PlexApplication)localObject).getPackageName().replace('.', '-') }))
    {
      return this.c;
      label41:
      localObject = PlexApplication.b();
    }
  }
  
  public String l()
  {
    try
    {
      String str;
      boolean bool;
      do
      {
        InetAddress localInetAddress;
        do
        {
          Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
          Enumeration localEnumeration2;
          while (!localEnumeration2.hasMoreElements())
          {
            if (!localEnumeration1.hasMoreElements()) {
              break;
            }
            localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
          }
          localInetAddress = (InetAddress)localEnumeration2.nextElement();
          str = localInetAddress.getHostAddress();
        } while ((e(str)) || (localInetAddress.isLoopbackAddress()));
        bool = localInetAddress instanceof Inet4Address;
      } while (!bool);
      return str;
    }
    catch (SocketException localSocketException)
    {
      bh.a(localSocketException, "Error getting local IP.", new Object[0]);
    }
    return "";
  }
  
  boolean m()
  {
    if (!PlexApplication.b().s()) {}
    while (Calendar.getInstance().get(1) != 2000) {
      return true;
    }
    return false;
  }
  
  public String n()
  {
    if (PlexApplication.b().p()) {
      return "Mobile";
    }
    if (PlexApplication.b().s()) {
      return "Android TV";
    }
    return "Anvergo";
  }
  
  public boolean o()
  {
    if (x()) {
      return true;
    }
    return PlexApplication.b().getResources().getBoolean(2131689474);
  }
  
  public int p()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public boolean q()
  {
    return (!"armv7a".equals("noAbi")) && (!PlexApplication.b().s()) && (!PlexApplication.b().t());
  }
  
  public boolean r()
  {
    if (this.b == null)
    {
      Pair localPair = f.a();
      if ((localPair == null) || (!h.a((String)localPair.first, h.b.b()))) {
        break label50;
      }
    }
    label50:
    for (this.b = Boolean.valueOf(true);; this.b = Boolean.valueOf(false)) {
      return this.b.booleanValue();
    }
  }
  
  public boolean s()
  {
    if (y()) {}
    while ((!PlexApplication.b().s()) && (!u())) {
      return false;
    }
    return true;
  }
  
  public boolean t()
  {
    return (C()) || (u()) || (v());
  }
  
  public boolean u()
  {
    String str = g();
    return (str.startsWith("AFTS")) || (str.startsWith("AFTRS"));
  }
  
  public boolean v()
  {
    return (g().startsWith("AFTM")) || (g().startsWith("AFTT"));
  }
  
  public boolean w()
  {
    return h().equalsIgnoreCase("amazon");
  }
  
  public boolean x()
  {
    return (w()) && ((Build.MODEL.startsWith("KF")) || (Build.MODEL.equals("Kindle Fire")));
  }
  
  public boolean y()
  {
    return (PlexApplication.b().s()) && (Build.MODEL.startsWith("BRAVIA"));
  }
  
  public boolean z()
  {
    return dm.b();
  }
}
