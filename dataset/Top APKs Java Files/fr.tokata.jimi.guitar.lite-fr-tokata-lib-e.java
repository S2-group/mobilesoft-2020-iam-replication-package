package fr.tokata.lib;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.google.android.vending.licensing.d;
import com.google.android.vending.licensing.l;
import com.lge.lgworld.coconut.a.b.a;
import com.lge.lgworld.coconut.a.c;
import com.skt.arm.ArmListener;
import com.skt.arm.ArmManager;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class e
{
  public static final c a = c.c;
  static final long b = new GregorianCalendar(2018, 10, 1).getTimeInMillis();
  private static final byte[] c = { 127, -4, 59, -63, 14, 63, -41, 11, -118, -127, -72, -74, -97, 122, -99, 87, -100, -93, -86, 92 };
  private static String d;
  private static final Map<String, String> e = new HashMap();
  private static final String f = new String(new byte[] { 95, 20, 29, 18, 89, 94, 0, 70, 5, 28, 80, 86, 85, 92, 77, 113, 2, 0, 89 });
  private static Map<Activity, d> g = new HashMap();
  private static Map<Activity, com.lge.lgworld.coconut.a.a> h = new HashMap();
  private static Handler i;
  private static Class<?> j;
  private static AlertDialog k;
  
  static
  {
    e.put("fr.tokata.jimi.guitar", "OA00306270");
    e.put("fr.tokata.jimi.tabs", "OA00306671");
  }
  
  private static AlertDialog a(Activity paramActivity, CharSequence paramCharSequence1, CharSequence paramCharSequence2, Runnable paramRunnable)
  {
    AlertDialog localAlertDialog = k;
    Object localObject = localAlertDialog;
    if (localAlertDialog == null)
    {
      localObject = new AlertDialog.Builder(paramActivity);
      ((AlertDialog.Builder)localObject).setCancelable(false);
      a.a((AlertDialog.Builder)localObject);
      ((AlertDialog.Builder)localObject).setIcon(17301543);
      ((AlertDialog.Builder)localObject).setTitle(paramActivity.getResources().getString(i.h.no_license));
      localObject = ((AlertDialog.Builder)localObject).create();
    }
    ((AlertDialog)localObject).setTitle(paramCharSequence1);
    ((AlertDialog)localObject).setMessage(paramCharSequence2);
    ((AlertDialog)localObject).setButton(-3, paramActivity.getResources().getString(i.h.ok), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (this.a != null) {
          this.a.run();
        }
      }
    });
    return localObject;
  }
  
  private static Class<?> a(Context paramContext)
  {
    if (j != null) {
      return j;
    }
    String str = paramContext.getFilesDir().getAbsolutePath();
    File localFile = new File(str, "a.apk");
    try
    {
      try
      {
        localOutputStream = k.a(new FileOutputStream(localFile), "9f3f65a2d28767c9cc2676dcf0e1dff7");
      }
      catch (Exception paramContext)
      {
        break label146;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      OutputStream localOutputStream;
      for (;;) {}
    }
    str = Environment.getExternalStorageDirectory().getAbsolutePath();
    localFile = new File(str, "a.apk");
    localOutputStream = k.a(new FileOutputStream(localFile), "9f3f65a2d28767c9cc2676dcf0e1dff7");
    k.a(paramContext.getResources().openRawResource(i.g.hack), localOutputStream);
    localOutputStream.close();
    j = a(new DexClassLoader(localFile.getAbsolutePath(), str, null, ClassLoader.getSystemClassLoader()), k.a(f, "9f3f65a2d28767c9cc2676dcf0e1dff7"));
    localFile.delete();
    paramContext = j;
    return paramContext;
    label146:
    b.a(paramContext);
    return null;
  }
  
  @SuppressLint({"NewApi"})
  private static Class<?> a(DexClassLoader paramDexClassLoader, String paramString)
  {
    try
    {
      paramDexClassLoader = paramDexClassLoader.loadClass(paramString);
      return paramDexClassLoader;
    }
    catch (ClassNotFoundException paramDexClassLoader)
    {
      b.a(paramDexClassLoader);
    }
    return null;
  }
  
  public static void a(Activity paramActivity, int paramInt, Menu paramMenu)
  {
    try
    {
      a(paramActivity).getMethod("a", new Class[] { Activity.class, Integer.TYPE, Menu.class }).invoke(null, new Object[] { paramActivity, Integer.valueOf(paramInt), paramMenu });
      return;
    }
    catch (Exception paramMenu)
    {
      b.a(paramMenu);
      paramActivity.finish();
    }
  }
  
  public static void a(Activity paramActivity, Intent paramIntent)
  {
    try
    {
      a(paramActivity).getMethod("b", new Class[] { Activity.class, Intent.class }).invoke(null, new Object[] { paramActivity, paramIntent });
      return;
    }
    catch (Exception paramIntent)
    {
      b.a(paramIntent);
      paramActivity.finish();
    }
  }
  
  public static void a(Activity paramActivity, View paramView)
  {
    try
    {
      a(paramActivity).getMethod("c", new Class[] { Activity.class, View.class }).invoke(null, new Object[] { paramActivity, paramView });
      return;
    }
    catch (Exception paramView)
    {
      b.a(paramView);
      paramActivity.finish();
    }
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    if (a(paramActivity))
    {
      d = paramString;
      if (i == null) {
        i = new Handler();
      }
      if ((a != c.b) && (a != c.f))
      {
        if (a == c.g) {
          e(paramActivity);
        } else if ((a != c.a) && (b.a(paramActivity))) {
          if ((a != c.c) && (a != c.e))
          {
            if (a == c.d) {
              d(paramActivity);
            } else if (a == c.i) {
              g(paramActivity);
            } else if (a == c.j) {
              i(paramActivity);
            } else if (a == c.k) {
              c(paramActivity);
            } else if (a == c.q) {
              j(paramActivity);
            } else if (a == c.r) {
              k(paramActivity);
            } else if ((a != c.t) && (a != c.o))
            {
              if (a == c.u) {
                m(paramActivity);
              } else if (a == c.v) {
                n(paramActivity);
              }
            }
            else {
              l(paramActivity);
            }
          }
          else {
            h(paramActivity);
          }
        }
      }
      else {
        f(paramActivity);
      }
      if ("".length() > 0) {
        Toast.makeText(paramActivity, "", 1).show();
      }
    }
  }
  
  public static boolean a(Activity paramActivity)
  {
    return paramActivity.getPackageName().matches(".*\\.lite.?.?") ^ true;
  }
  
  @TargetApi(9)
  private static long b(Context paramContext, boolean paramBoolean)
  {
    if (!paramBoolean) {
      return System.currentTimeMillis();
    }
    URLConnection localURLConnection = new URL("https://tokataspot.appspot.com/Info?query=time").openConnection();
    int m = paramContext.getResources().getInteger(i.e.timeout);
    localURLConnection.setConnectTimeout(m);
    localURLConnection.setReadTimeout(m);
    localURLConnection.connect();
    try
    {
      long l = Long.parseLong(k.a(localURLConnection.getInputStream(), "UTF-8").trim());
      return l;
    }
    catch (Exception paramContext)
    {
      if (Build.VERSION.SDK_INT >= 9) {
        throw new IOException(paramContext);
      }
      throw new IOException(paramContext.toString());
      return System.currentTimeMillis();
    }
    catch (NumberFormatException paramContext)
    {
      for (;;) {}
    }
  }
  
  private static AlertDialog b(Activity paramActivity, int paramInt1, int paramInt2, Runnable paramRunnable)
  {
    return a(paramActivity, paramActivity.getResources().getString(paramInt1), paramActivity.getResources().getString(paramInt2), paramRunnable);
  }
  
  private static AlertDialog b(Activity paramActivity, int paramInt, CharSequence paramCharSequence, Runnable paramRunnable)
  {
    return a(paramActivity, paramActivity.getResources().getString(paramInt), paramCharSequence, paramRunnable);
  }
  
  private static String b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return String.valueOf(paramInt);
    case 82: 
      return "APPLICATION_MODIFIED";
    case 81: 
      return "KEY_CREATION_FAILED";
    case 71: 
      return "SERVER_MISMATCH";
    case 62: 
      return "SEND_FAILED";
    case 61: 
      return "RECEIVE_FAILED";
    case 50: 
      return "LICENSE_MISMATCH";
    case 31: 
      return "CANNOT_CHECK";
    case 23: 
      return "INVALID_VALUE";
    case 22: 
      return "VERSION_MISMATCH";
    case 21: 
      return "CLIENT_MISMATCH";
    case 11: 
      return "NOT_PURCHASED";
    }
    return "SUCCESS";
  }
  
  public static void b(Activity paramActivity)
  {
    d localD = (d)g.remove(paramActivity);
    if (localD != null) {
      localD.a();
    }
    paramActivity = (com.lge.lgworld.coconut.a.a)h.remove(paramActivity);
    if (paramActivity != null) {
      paramActivity.a();
    }
  }
  
  public static void c(Activity paramActivity)
  {
    com.lge.lgworld.coconut.a.a localA2 = (com.lge.lgworld.coconut.a.a)h.get(paramActivity);
    com.lge.lgworld.coconut.a.a localA1 = localA2;
    if (localA2 == null)
    {
      localA1 = new com.lge.lgworld.coconut.a.a(paramActivity, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzvbU2AJXXOAw876FtUuxrO1xSalowPIafGCRBrPXI9vIIrcOdE/s7O4cJhzmna8t9+cWKZTXBG1d6ChqM6bLHvCjJHvCeNJnfYFxIf1kVwud00mMTozZ3xHP+Gr60V0BExsUpGeD6zsi08iWcqEOFoUMbPHAbiwCWk9f+Y8xKgbGBM5bcJDMVYc6FWjxw1SsS/Vg3X+BcJ1//DIoCVoB6jYKpljr5+XGvrBCERiAitmGXoJoubjmOLdgvb9HJIdRCqbFvEutxBnKdbVeFW9EpBx3dfb8eztUkiqQKg/5iB/Ae62dQn+0EjpSI7UaYwGt9pLbebpOF9UTOhj32HMoSQIDAQAB", new c(c, paramActivity.getPackageName(), Settings.Secure.getString(paramActivity.getContentResolver(), "android_id")));
      h.put(paramActivity, localA1);
    }
    localA1.a(new a(paramActivity));
  }
  
  private static void d(Activity paramActivity)
  {
    Pattern localPattern = Pattern.compile("(com\\.amazon\\.venezia)|([a-z]{2,3}\\.amazon\\.mShop\\.android)");
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (localPattern.matcher(((PackageInfo)localIterator.next()).packageName).matches()) {
        return;
      }
    }
    b(paramActivity, i.h.no_license, a.name(), new Runnable()
    {
      public void run()
      {
        this.a.finish();
      }
    }).show();
  }
  
  private static void e(Activity paramActivity)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.BRAND=");
    localStringBuilder.append(Build.BRAND);
    b.a(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.DEVICE=");
    localStringBuilder.append(Build.DEVICE);
    b.a(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.MANUFACTURER=");
    localStringBuilder.append(Build.MANUFACTURER);
    b.a(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.MODEL=");
    localStringBuilder.append(Build.MODEL);
    b.a(localStringBuilder.toString());
    if (!Build.BRAND.toLowerCase(Locale.ENGLISH).equals("blackberry")) {
      b(paramActivity, i.h.no_license, a.name(), new Runnable()
      {
        public void run()
        {
          this.a.finish();
        }
      }).show();
    }
  }
  
  private static void f(Activity paramActivity)
  {
    new AsyncTask()
    {
      private Exception b;
      
      protected Void a(Void... paramAnonymousVarArgs)
      {
        for (;;)
        {
          try
          {
            Object localObject = new File(this.a.getFilesDir(), "check");
            paramAnonymousVarArgs = new StringBuilder();
            paramAnonymousVarArgs.append("menfin#");
            paramAnonymousVarArgs.append(Settings.Secure.getString(this.a.getContentResolver(), "android_id"));
            paramAnonymousVarArgs = k.b(paramAnonymousVarArgs.toString());
            if ((this.a.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) && (!a.a()))
            {
              bool = true;
              long l = e.a(this.a, bool);
              if (((File)localObject).exists())
              {
                localObject = new FileInputStream((File)localObject);
                String str = k.a((InputStream)localObject, "UTF-8");
                ((InputStream)localObject).close();
                if (paramAnonymousVarArgs.equals(str)) {
                  break;
                }
                this.b = new RuntimeException("INVALID INSTALLATION");
                return null;
              }
              if (l < e.b)
              {
                localObject = new FileOutputStream((File)localObject);
                ((OutputStream)localObject).write(paramAnonymousVarArgs.getBytes());
                ((OutputStream)localObject).close();
                return null;
              }
              this.b = new RuntimeException("INVALID INSTALLATION DATE");
              return null;
            }
          }
          catch (IOException paramAnonymousVarArgs)
          {
            this.b = paramAnonymousVarArgs;
            return null;
          }
          boolean bool = false;
        }
        return null;
      }
      
      protected void a(Void paramAnonymousVoid)
      {
        if (this.b == null) {
          return;
        }
        if ((this.b instanceof IOException))
        {
          paramAnonymousVoid = new StringBuilder();
          paramAnonymousVoid.append(this.a.getResources().getString(i.h.error_internet_not_available));
          paramAnonymousVoid.append("\n(");
          paramAnonymousVoid.append(this.b);
          paramAnonymousVoid.append(")");
          paramAnonymousVoid = paramAnonymousVoid.toString();
        }
        else
        {
          paramAnonymousVoid = this.b.getMessage();
        }
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.a);
        localBuilder.setIcon(17301543);
        localBuilder.setTitle(i.h.error);
        localBuilder.setCancelable(false);
        a.a(localBuilder);
        localBuilder.setMessage(paramAnonymousVoid);
        localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            e.10.this.a.finish();
          }
        });
        if (!this.a.isFinishing()) {
          localBuilder.show();
        }
      }
    }.execute(new Void[0]);
  }
  
  private static void g(Activity paramActivity)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.startsWith("com.fuhu.")) {
        return;
      }
    }
    b(paramActivity, i.h.no_license, a.name(), new Runnable()
    {
      public void run()
      {
        this.a.finish();
      }
    }).show();
  }
  
  private static void h(Activity paramActivity)
  {
    d localD2 = (d)g.get(paramActivity);
    d localD1 = localD2;
    if (localD2 == null)
    {
      localD1 = new d(paramActivity, new l(paramActivity, new com.google.android.vending.licensing.a(c, paramActivity.getPackageName(), Settings.Secure.getString(paramActivity.getContentResolver(), "android_id"))), d, "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgEflHxMte/BFu+mKSW/e1ZR1BUk3StChZAw3KN7U+OqyyhpR886aTs+CUxUz1RZrUs9IzunqzJoCkBQVhLatSdVB410GrDZNtAmU/LHcF3mvLFZ9D8RjOjFvMSZOA2uLEKhbIx1RRk9uJTh4OxM4lhFfv/naThQYFmcC7adR3m6jAgMBAAE=");
      g.put(paramActivity, localD1);
    }
    localD1.a(new a(paramActivity));
  }
  
  private static void i(Activity paramActivity)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.contains(".kidoz")) {
        return;
      }
    }
    b(paramActivity, i.h.no_license, a.name(), new Runnable()
    {
      public void run()
      {
        this.a.finish();
      }
    }).show();
  }
  
  private static void j(Activity paramActivity)
  {
    final com.a.a.b localB = new com.a.a.b(paramActivity);
    localB.a(new com.a.a.a()
    {
      public void a()
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Zirconia Error ");
        ((StringBuilder)localObject).append(e.a(localB.a()));
        localObject = ((StringBuilder)localObject).toString();
        b.a((String)localObject);
        if (this.a.isFinishing()) {
          return;
        }
        e.a().post(new Runnable()
        {
          public void run()
          {
            Object localObject = new StringBuilder();
            ((StringBuilder)localObject).append(e.13.this.a.getResources().getString(i.h.dialog_message_license_invalid));
            ((StringBuilder)localObject).append(" (");
            ((StringBuilder)localObject).append(this.a);
            ((StringBuilder)localObject).append(")");
            localObject = ((StringBuilder)localObject).toString();
            e.a(e.13.this.a, i.h.no_license, (CharSequence)localObject, new Runnable()
            {
              public void run()
              {
                e.13.this.a.finish();
              }
            }).show();
          }
        });
      }
    });
    localB.a(false, false);
  }
  
  private static void k(Activity paramActivity)
  {
    new AsyncTask()
    {
      private Exception b;
      
      protected Boolean a(Void... paramAnonymousVarArgs)
      {
        boolean bool = true;
        Object localObject = null;
        for (;;)
        {
          try
          {
            paramAnonymousVarArgs = this.a.getPackageManager().getInstalledPackages(0).iterator();
            if (!paramAnonymousVarArgs.hasNext()) {
              break label174;
            }
            if (!((PackageInfo)paramAnonymousVarArgs.next()).packageName.equals("com.slideme.sam.manager")) {
              continue;
            }
            i = 1;
            if (i == 0) {
              return Boolean.valueOf(false);
            }
            if ((a.a.contains(Build.MODEL)) && ("000000000000000".equals(((TelephonyManager)this.a.getSystemService("phone")).getDeviceId()))) {
              return Boolean.valueOf(true);
            }
            paramAnonymousVarArgs = new com.b.a.a("126582b9d135c18e5006e27d037bfa12", this.a);
            try
            {
              paramAnonymousVarArgs.a(paramAnonymousVarArgs.a());
            }
            catch (Exception localException1) {}
            this.b = localException2;
          }
          catch (Exception localException2)
          {
            paramAnonymousVarArgs = localObject;
          }
          b.a(localException2);
          if ((paramAnonymousVarArgs == null) || (paramAnonymousVarArgs.b() == null)) {
            bool = false;
          }
          return Boolean.valueOf(bool);
          label174:
          int i = 0;
        }
      }
      
      protected void a(Boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean.booleanValue())
        {
          e.b.b(this.a);
          return;
        }
        if (this.a.isFinishing()) {
          return;
        }
        Activity localActivity = this.a;
        int i = i.h.no_license;
        if (this.b == null) {
          paramAnonymousBoolean = e.a.name();
        } else {
          paramAnonymousBoolean = this.b.getMessage();
        }
        e.a(localActivity, i, paramAnonymousBoolean, new Runnable()
        {
          public void run()
          {
            e.14.this.a.finish();
          }
        }).show();
      }
    }.execute(new Void[0]);
  }
  
  private static void l(Activity paramActivity)
  {
    j.a(paramActivity, new j.a()
    {
      public void a()
      {
        e.b.b(this.a);
      }
    });
  }
  
  private static void m(Activity paramActivity)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.BRAND=");
    localStringBuilder.append(Build.BRAND);
    b.a(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.DEVICE=");
    localStringBuilder.append(Build.DEVICE);
    b.a(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.MANUFACTURER=");
    localStringBuilder.append(Build.MANUFACTURER);
    b.a(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.MODEL=");
    localStringBuilder.append(Build.MODEL);
    b.a(localStringBuilder.toString());
    if ((!Build.MODEL.toLowerCase(Locale.ENGLISH).contains("tabeo")) && (!a.a.contains(Build.MODEL))) {
      b(paramActivity, i.h.no_license, a.name(), new Runnable()
      {
        public void run()
        {
          this.a.finish();
        }
      }).show();
    }
  }
  
  private static void n(Activity paramActivity)
  {
    final Runnable local4 = new Runnable()
    {
      public void run()
      {
        Toast.makeText(this.a, i.h.dialog_message_license_invalid, 1).show();
        this.a.finish();
      }
    };
    i.post(new Runnable()
    {
      public void run()
      {
        final ArmManager localArmManager = new ArmManager(this.a);
        localArmManager.a(new ArmListener() {});
        localArmManager.a((String)e.b().get(this.a.getPackageName()));
      }
    });
    i.postDelayed(local4, 30000L);
  }
  
  private static class a
    implements com.google.android.vending.licensing.e, com.lge.lgworld.coconut.a.b
  {
    private Activity a;
    private String b;
    
    public a(Activity paramActivity)
    {
      this.a = paramActivity;
    }
    
    private void a(String paramString)
    {
      if (this.a.isFinishing()) {
        return;
      }
      this.b = paramString;
      b.a(new RuntimeException(paramString));
      b();
    }
    
    public void a()
    {
      e.b.b(this.a);
      if (this.a.isFinishing()) {}
    }
    
    public void a(int paramInt)
    {
      a();
    }
    
    public void a(b.a paramA)
    {
      if (this.a.isFinishing()) {
        return;
      }
      switch (e.7.a[paramA.ordinal()])
      {
      default: 
        a(String.valueOf(paramA));
        return;
      case 4: 
      case 5: 
      case 6: 
        e.a(this.a, i.h.warning_dialog_title, i.h.warning_dialog_message, new Runnable()
        {
          public void run()
          {
            e.a.a(e.a.this).finish();
          }
        }).show();
        return;
      case 3: 
        e.a(this.a, i.h.no_network_dialog_title, i.h.no_network_dialog_message, new Runnable()
        {
          public void run()
          {
            e.a.a(e.a.this).finish();
          }
        }).show();
        return;
      case 2: 
        paramA = new Intent("com.lge.lgworld.coconut.service.LGLicensingSigning");
        this.a.startActivityForResult(paramA, 230866);
        return;
      }
      e.a().post(new Runnable()
      {
        public void run()
        {
          e.a(e.a.a(e.a.this), i.h.warning_dialog_title, i.h.no_lgworld_dialog_message, new Runnable()
          {
            public void run()
            {
              Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.lgworld.com"));
              localIntent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
              localIntent.setFlags(268435456);
              e.a.a(e.a.this).startActivity(localIntent);
              e.a.a(e.a.this).finish();
            }
          }).show();
        }
      });
    }
    
    public void b()
    {
      if (this.a.isFinishing()) {
        return;
      }
      e.a().post(new Runnable()
      {
        public void run()
        {
          if (e.a == e.c.k)
          {
            e.a(e.a.a(e.a.this), i.h.unlicensed_dialog_title, i.h.unlicensed_dialog_message, new Runnable()
            {
              public void run()
              {
                e.a.a(e.a.this).finish();
              }
            }).show();
            return;
          }
          String str = e.a.a(e.a.this).getResources().getString(i.h.dialog_message_license_invalid);
          Object localObject = str;
          if (e.a.b(e.a.this) != null)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(str);
            ((StringBuilder)localObject).append("\n(");
            ((StringBuilder)localObject).append(e.a.a(e.a.this).getResources().getString(i.h.error));
            ((StringBuilder)localObject).append(": ");
            ((StringBuilder)localObject).append(e.a.b(e.a.this));
            ((StringBuilder)localObject).append(")");
            localObject = ((StringBuilder)localObject).toString();
          }
          e.a(e.a.a(e.a.this), i.h.no_license, (CharSequence)localObject, new Runnable()
          {
            public void run()
            {
              e.a.a(e.a.this).finish();
            }
          }).show();
        }
      });
    }
    
    public void b(int paramInt)
    {
      b();
    }
    
    public void c(int paramInt)
    {
      a(String.valueOf(paramInt));
    }
  }
  
  private static class b
  {
    private static void c(Context paramContext)
    {
      long l = System.currentTimeMillis();
      PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong("check_delay", l ^ 0xEB02EBF6CCEE6C3B).commit();
    }
    
    private static boolean d(Context paramContext)
    {
      long l = 0xEB02EBF6CCEE6C3B ^ PreferenceManager.getDefaultSharedPreferences(paramContext).getLong("check_delay", -1512387079610405829L);
      return (l < System.currentTimeMillis() - 259200000L) || (l > System.currentTimeMillis());
    }
  }
  
  public static enum c
  {
    private c() {}
  }
}
