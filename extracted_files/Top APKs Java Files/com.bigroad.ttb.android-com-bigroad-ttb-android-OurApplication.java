package com.bigroad.ttb.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.Toast;
import com.bigroad.a.af;
import com.bigroad.ttb.android.g.d;
import com.bigroad.ttb.android.g.h;
import com.bigroad.ttb.android.g.i;
import com.bigroad.ttb.android.receiver.BatteryStatusReceiver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class OurApplication
  extends Application
{
  public static com.bigroad.ttb.android.g.a a;
  private static OurApplication b;
  
  public OurApplication() {}
  
  public static com.bigroad.ttb.android.d.b A()
  {
    return com.bigroad.ttb.android.d.b.a(b);
  }
  
  public static cj B()
  {
    return cj.a();
  }
  
  public static do C()
  {
    return do.a();
  }
  
  public static ay D()
  {
    return ay.a();
  }
  
  public static boolean E()
  {
    boolean bool = false;
    if (b.getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() > 0) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean F()
  {
    Iterator localIterator = b.getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean G()
  {
    return b.getPackageManager().hasSystemFeature("android.hardware.camera");
  }
  
  public static File H()
  {
    File localFile = L();
    if (localFile != null) {
      return localFile;
    }
    return M();
  }
  
  public static void I()
  {
    b(L());
    b(M());
  }
  
  public static Uri J()
  {
    return Uri.parse("mailto:support@bigroad.com?subject=" + h.a);
  }
  
  private static void K()
  {
    Object localObject = com.bigroad.ttb.android.g.f.b;
    a = new com.bigroad.ttb.android.g.a(65536, new d(new String[] { "TT-LocTracker" }, (com.bigroad.ttb.android.g.f)localObject));
    localObject = new i();
    ((i)localObject).a(new com.bigroad.ttb.android.g.j());
    ((i)localObject).a(a);
    com.bigroad.ttb.android.g.b.a((com.bigroad.ttb.android.g.g)localObject);
  }
  
  private static File L()
  {
    return a(a().getExternalFilesDir(null));
  }
  
  private static File M()
  {
    return a(a().getFilesDir());
  }
  
  private long N()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l1 = localStatFs.getBlockSize();
    long l2 = localStatFs.getAvailableBlocks();
    com.bigroad.ttb.android.g.b.c("TT-App", "Free internal space: " + Formatter.formatFileSize(this, l2 * l1));
    return l2 * l1;
  }
  
  @SuppressLint({"ShowToast"})
  public static Toast a(Context paramContext)
  {
    paramContext = Toast.makeText(paramContext, 2131230877, 1);
    paramContext.setGravity(83, 0, 0);
    return paramContext;
  }
  
  public static OurApplication a()
  {
    return b;
  }
  
  private static File a(bq paramBq, String paramString)
  {
    localObject = H();
    if (localObject == null) {}
    do
    {
      return null;
      try
      {
        paramString = File.createTempFile("tempFile", paramString, (File)localObject);
        af.a(paramBq);
      }
      catch (IOException paramBq)
      {
        try
        {
          localObject = new FileOutputStream(paramString);
        }
        catch (IOException paramBq)
        {
          paramBq = null;
          continue;
        }
        try
        {
          paramBq.a((OutputStream)localObject);
          ((FileOutputStream)localObject).close();
          return paramString;
        }
        catch (IOException paramBq)
        {
          paramBq = (bq)localObject;
          continue;
        }
        paramBq = paramBq;
        paramBq = null;
        paramString = null;
      }
    } while (paramString == null);
    paramString.delete();
    return null;
  }
  
  private static File a(File paramFile)
  {
    if (paramFile == null) {
      paramFile = null;
    }
    File localFile;
    do
    {
      return paramFile;
      localFile = new File(paramFile, "tmp");
      paramFile = localFile;
    } while (localFile.exists());
    localFile.mkdir();
    return localFile;
  }
  
  public static File a(InputStream paramInputStream)
  {
    return a(new bp(paramInputStream), null);
  }
  
  public static File a(byte[] paramArrayOfByte)
  {
    return a(new bo(paramArrayOfByte), null);
  }
  
  private static void a(File[] paramArrayOfFile)
  {
    if (paramArrayOfFile == null) {}
    for (;;)
    {
      return;
      int j = paramArrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        paramArrayOfFile[i].delete();
        i += 1;
      }
    }
  }
  
  @SuppressLint({"ShowToast"})
  public static Toast b(Context paramContext)
  {
    paramContext = Toast.makeText(paramContext, 2131230985, 1);
    paramContext.setGravity(83, 0, 0);
    return paramContext;
  }
  
  public static g b()
  {
    return g.a(b);
  }
  
  private static void b(File paramFile)
  {
    if (paramFile == null) {
      return;
    }
    a(paramFile.listFiles());
  }
  
  public static by c()
  {
    return by.a(b);
  }
  
  public static bu d()
  {
    return bu.a(b);
  }
  
  public static com.bigroad.ttb.android.c.a e()
  {
    return com.bigroad.ttb.android.c.a.a(b);
  }
  
  public static com.bigroad.ttb.android.e.a f()
  {
    return com.bigroad.ttb.android.e.a.a(b);
  }
  
  public static cs g()
  {
    return cs.a(b);
  }
  
  public static n h()
  {
    return n.a(b);
  }
  
  public static com.bigroad.ttb.android.location.a i()
  {
    return com.bigroad.ttb.android.location.a.a(b);
  }
  
  public static as j()
  {
    return as.a(b);
  }
  
  public static br k()
  {
    return br.a(b);
  }
  
  public static dk l()
  {
    return dk.a(b);
  }
  
  public static ao m()
  {
    return ao.a(b);
  }
  
  public static x n()
  {
    return x.a(b);
  }
  
  public static al o()
  {
    return al.a(b);
  }
  
  public static be p()
  {
    return be.a(b);
  }
  
  public static t q()
  {
    return t.a(b);
  }
  
  public static bm r()
  {
    return bm.a(b);
  }
  
  public static av s()
  {
    return av.a(b);
  }
  
  public static com.bigroad.ttb.android.location.f t()
  {
    return com.bigroad.ttb.android.location.f.a(b);
  }
  
  public static ad u()
  {
    return ad.a(b);
  }
  
  public static q v()
  {
    return q.a(b);
  }
  
  public static ac w()
  {
    return ac.a(b);
  }
  
  public static com.bigroad.ttb.android.f.b x()
  {
    return com.bigroad.ttb.android.f.b.a();
  }
  
  public static df y()
  {
    return df.a(b);
  }
  
  public static j z()
  {
    return j.a(b);
  }
  
  public void onCreate()
  {
    super.onCreate();
    b = this;
    K();
    com.bigroad.ttb.android.g.b.c("TT-App", "Application starting");
    com.google.analytics.tracking.android.n.a().a(getApplicationContext());
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
    localIntentFilter.addAction("android.intent.action.BATTERY_LOW");
    localIntentFilter.addAction("android.intent.action.BATTERY_OKAY");
    registerReceiver(new BatteryStatusReceiver(), localIntentFilter);
    I();
    N();
    g();
    h();
    f();
    r();
    u();
    y();
    A();
    B();
  }
}
