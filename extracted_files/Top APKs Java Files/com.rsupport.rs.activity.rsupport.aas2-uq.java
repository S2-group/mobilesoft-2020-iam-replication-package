import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class uq
{
  private static final int jdField_a_of_type_Int = 0;
  private static Activity jdField_a_of_type_AndroidAppActivity;
  public static String a;
  private static un jdField_a_of_type_Un;
  private static up jdField_a_of_type_Up;
  private static uq jdField_a_of_type_Uq;
  private static uo[] jdField_a_of_type_ArrayOfUo;
  private static final int b = 1;
  public static String b;
  private static final int jdField_c_of_type_Int = 2;
  private static String jdField_c_of_type_JavaLangString;
  private static int d = 0;
  private se jdField_a_of_type_Se;
  private boolean jdField_a_of_type_Boolean = false;
  
  static
  {
    jdField_a_of_type_JavaLangString = "";
  }
  
  public uq(Activity paramActivity)
  {
    jdField_a_of_type_AndroidAppActivity = paramActivity;
    jdField_a_of_type_Un = new un(paramActivity);
    paramActivity.getFilesDir().mkdirs();
    jdField_c_of_type_JavaLangString = paramActivity.getFilesDir().getAbsolutePath();
  }
  
  private static double a()
  {
    return un.b;
  }
  
  public static String a()
  {
    String str = "";
    int i = 0;
    for (;;)
    {
      if (i >= jdField_a_of_type_ArrayOfUo.length)
      {
        vf.c("RemoteCall", "updatePackageNameCheck : " + str);
        return str;
      }
      uo localUo = jdField_a_of_type_ArrayOfUo[i];
      if (!localUo.jdField_c_of_type_Boolean) {
        str = localUo.jdField_b_of_type_JavaLangString;
      }
      i += 1;
    }
  }
  
  public static void a()
  {
    if ((!jdField_a_of_type_JavaLangString.equals("")) && (jdField_a_of_type_JavaLangString.length() > 0)) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(jdField_a_of_type_JavaLangString));
        jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
        jdField_a_of_type_AndroidAppActivity.finish();
      }
      while (f())
      {
        Intent localIntent;
        return;
      }
      d = 0;
      g();
      new us().start();
      return;
    }
  }
  
  private static void a(Activity paramActivity, String paramString1, String paramString2)
  {
    if (qq.a(null) != null) {
      qq.a(null).e();
    }
    ((NotificationManager)paramActivity.getSystemService("notification")).cancelAll();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addFlags(329252864);
    localIntent.putExtra("ServiceType", 0);
    localIntent.putExtra("UpdateServerURL", b());
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
    paramActivity.finish();
    System.exit(0);
  }
  
  public static void a(Activity paramActivity, se paramSe)
  {
    new ur(paramActivity, paramSe).start();
  }
  
  private static void a(String paramString, int paramInt)
  {
    int i = 0;
    for (;;)
    {
      if (i >= jdField_a_of_type_ArrayOfUo.length) {
        return;
      }
      uo localUo = jdField_a_of_type_ArrayOfUo[i];
      if ((localUo.jdField_b_of_type_JavaLangString.equals(paramString)) && (localUo.jdField_a_of_type_Int <= paramInt)) {
        localUo.jdField_c_of_type_Boolean = true;
      }
      i += 1;
    }
  }
  
  private static void a(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addFlags(329252864);
    localIntent.putExtra("ServiceType", 0);
    localIntent.putExtra("UpdateServerURL", b());
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
  }
  
  private static boolean a()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!jdField_a_of_type_JavaLangString.equals(""))
    {
      bool1 = bool2;
      if (jdField_a_of_type_JavaLangString.length() > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static boolean a(String paramString)
  {
    try
    {
      List localList = jdField_a_of_type_AndroidAppActivity.getPackageManager().getInstalledApplications(0);
      int i = 0;
      for (;;)
      {
        if (i >= localList.size()) {
          return false;
        }
        boolean bool = ((ApplicationInfo)localList.get(i)).packageName.equals(paramString);
        if (bool) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static uo[] a()
  {
    return jdField_a_of_type_ArrayOfUo;
  }
  
  private static double b()
  {
    return un.jdField_a_of_type_Int;
  }
  
  private static int b()
  {
    return d;
  }
  
  private static String b()
  {
    vh.a.getSharedPreferences("setting", 0);
    rl.jdField_a_of_type_Boolean = false;
    return "http://" + ml.e + "/WebRelay/Gate3011";
  }
  
  private static String b(int paramInt)
  {
    return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("")).append(jdField_c_of_type_JavaLangString).toString())).append("/").toString())).append("installfile").toString())).append(String.valueOf(paramInt)).toString() + ".apk";
  }
  
  private static String b(String paramString)
  {
    return new StringBuilder(String.valueOf("")).append(jdField_a_of_type_Up.jdField_a_of_type_JavaLangString).toString() + paramString;
  }
  
  private static boolean b()
  {
    int i = 0;
    Object localObject = new uu().a(b(), Build.MODEL, Build.VERSION.RELEASE, vh.a.getApplicationContext().getPackageName());
    boolean bool;
    if ((localObject == null) || (((String)localObject).length() <= 0)) {
      bool = false;
    }
    for (;;)
    {
      System.out.println("jsonString : " + (String)localObject);
      try
      {
        localObject = new JSONObject((String)localObject);
        System.out.println("retcode : " + ((JSONObject)localObject).getString("retcode"));
        System.out.println("DownDefaultAddress : " + ((JSONObject)localObject).getString("DownDefaultAddress"));
        JSONObject localJSONObject = new JSONObject(((JSONObject)localObject).getString("Data"));
        System.out.println("Code : " + localJSONObject.getString("Code"));
        System.out.println("Name : " + localJSONObject.getString("Name"));
        System.out.println("OS : " + localJSONObject.getString("OS"));
        System.out.println("BuildNo : " + localJSONObject.getString("BuildNo"));
        System.out.println("NickName : " + localJSONObject.getString("NickName"));
        System.out.println("Telecom : " + localJSONObject.getString("Telecom"));
        System.out.println("StartPackageName : " + localJSONObject.getString("StartPackageName"));
        System.out.println("StartClassName : " + localJSONObject.getString("StartClassName"));
        up localUp = new up();
        jdField_a_of_type_Up = localUp;
        localUp.jdField_a_of_type_JavaLangString = ((JSONObject)localObject).getString("DownDefaultAddress");
        jdField_a_of_type_Up.jdField_b_of_type_JavaLangString = localJSONObject.getString("Name");
        jdField_a_of_type_Up.jdField_c_of_type_JavaLangString = localJSONObject.getString("OS");
        jdField_a_of_type_Up.d = localJSONObject.getString("BuildNo");
        jdField_a_of_type_Up.e = localJSONObject.getString("NickName");
        jdField_a_of_type_Up.f = localJSONObject.getString("Telecom");
        jdField_a_of_type_Up.g = localJSONObject.getString("StartPackageName");
        jdField_a_of_type_Up.h = localJSONObject.getString("StartClassName");
        localObject = new JSONArray(localJSONObject.getString("InstallFiles"));
        jdField_a_of_type_Up.jdField_a_of_type_Int = ((JSONArray)localObject).length();
        jdField_a_of_type_ArrayOfUo = new uo[jdField_a_of_type_Up.jdField_a_of_type_Int];
        System.out.println("---------------------------");
        for (;;)
        {
          if (i >= ((JSONArray)localObject).length()) {
            return bool;
          }
          localJSONObject = ((JSONArray)localObject).getJSONObject(i);
          System.out.println("VersionCode : " + localJSONObject.getInt("VersionCode"));
          System.out.println("VersionName : " + localJSONObject.getString("VersionName"));
          System.out.println("RunNeed : " + localJSONObject.getBoolean("RunNeed"));
          System.out.println("Signing : " + localJSONObject.getBoolean("Signing"));
          System.out.println("PackageName : " + localJSONObject.getString("PackageName"));
          System.out.println("DownUrl : " + localJSONObject.getString("DownUrl"));
          jdField_a_of_type_ArrayOfUo[i] = new uo();
          jdField_a_of_type_ArrayOfUo[i].jdField_a_of_type_Int = localJSONObject.getInt("VersionCode");
          jdField_a_of_type_ArrayOfUo[i].jdField_a_of_type_JavaLangString = localJSONObject.getString("VersionName");
          jdField_a_of_type_ArrayOfUo[i].jdField_a_of_type_Boolean = localJSONObject.getBoolean("RunNeed");
          jdField_a_of_type_ArrayOfUo[i].jdField_b_of_type_Boolean = localJSONObject.getBoolean("Signing");
          jdField_a_of_type_ArrayOfUo[i].jdField_b_of_type_JavaLangString = localJSONObject.getString("PackageName");
          jdField_a_of_type_ArrayOfUo[i].jdField_c_of_type_JavaLangString = localJSONObject.getString("DownUrl");
          if (!localJSONObject.isNull("MarketUri"))
          {
            jdField_a_of_type_ArrayOfUo[i].d = localJSONObject.getString("MarketUri");
            System.out.println("MarketUri : " + localJSONObject.getString("MarketUri"));
            if (jdField_a_of_type_ArrayOfUo[i].d.length() > 1) {
              jdField_a_of_type_JavaLangString = jdField_a_of_type_ArrayOfUo[i].d;
            }
          }
          if (jdField_a_of_type_ArrayOfUo[i].jdField_b_of_type_JavaLangString.contains("com.rsupport.starter")) {
            jdField_a_of_type_ArrayOfUo[i].jdField_c_of_type_Boolean = true;
          }
          System.out.println("---------------------------");
          i += 1;
        }
        bool = true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return bool;
      }
    }
  }
  
  private static String c()
  {
    vh.a.getSharedPreferences("setting", 0);
    rl.jdField_a_of_type_Boolean = false;
    return "http://";
  }
  
  private void c()
  {
    String str = new uu().a(b(), Build.MODEL, Build.VERSION.RELEASE, vh.a.getApplicationContext().getPackageName());
    if ((str == null) || (str.length() <= 0)) {}
    for (int i = 0;; i = 1)
    {
      c(str);
      if (i == 0)
      {
        this.jdField_a_of_type_Se.a(-1);
        return;
      }
      if (!c())
      {
        this.jdField_a_of_type_Se.a(1);
        return;
      }
      this.jdField_a_of_type_Se.a(0);
      return;
    }
  }
  
  private static void c(String paramString)
  {
    System.out.println("jsonString : " + paramString);
    try
    {
      paramString = new JSONObject(paramString);
      System.out.println("retcode : " + paramString.getString("retcode"));
      System.out.println("DownDefaultAddress : " + paramString.getString("DownDefaultAddress"));
      JSONObject localJSONObject = new JSONObject(paramString.getString("Data"));
      System.out.println("Code : " + localJSONObject.getString("Code"));
      System.out.println("Name : " + localJSONObject.getString("Name"));
      System.out.println("OS : " + localJSONObject.getString("OS"));
      System.out.println("BuildNo : " + localJSONObject.getString("BuildNo"));
      System.out.println("NickName : " + localJSONObject.getString("NickName"));
      System.out.println("Telecom : " + localJSONObject.getString("Telecom"));
      System.out.println("StartPackageName : " + localJSONObject.getString("StartPackageName"));
      System.out.println("StartClassName : " + localJSONObject.getString("StartClassName"));
      up localUp = new up();
      jdField_a_of_type_Up = localUp;
      localUp.jdField_a_of_type_JavaLangString = paramString.getString("DownDefaultAddress");
      jdField_a_of_type_Up.jdField_b_of_type_JavaLangString = localJSONObject.getString("Name");
      jdField_a_of_type_Up.jdField_c_of_type_JavaLangString = localJSONObject.getString("OS");
      jdField_a_of_type_Up.d = localJSONObject.getString("BuildNo");
      jdField_a_of_type_Up.e = localJSONObject.getString("NickName");
      jdField_a_of_type_Up.f = localJSONObject.getString("Telecom");
      jdField_a_of_type_Up.g = localJSONObject.getString("StartPackageName");
      jdField_a_of_type_Up.h = localJSONObject.getString("StartClassName");
      paramString = new JSONArray(localJSONObject.getString("InstallFiles"));
      jdField_a_of_type_Up.jdField_a_of_type_Int = paramString.length();
      jdField_a_of_type_ArrayOfUo = new uo[jdField_a_of_type_Up.jdField_a_of_type_Int];
      System.out.println("---------------------------");
      int i = 0;
      for (;;)
      {
        if (i >= paramString.length()) {
          return;
        }
        localJSONObject = paramString.getJSONObject(i);
        System.out.println("VersionCode : " + localJSONObject.getInt("VersionCode"));
        System.out.println("VersionName : " + localJSONObject.getString("VersionName"));
        System.out.println("RunNeed : " + localJSONObject.getBoolean("RunNeed"));
        System.out.println("Signing : " + localJSONObject.getBoolean("Signing"));
        System.out.println("PackageName : " + localJSONObject.getString("PackageName"));
        System.out.println("DownUrl : " + localJSONObject.getString("DownUrl"));
        jdField_a_of_type_ArrayOfUo[i] = new uo();
        jdField_a_of_type_ArrayOfUo[i].jdField_a_of_type_Int = localJSONObject.getInt("VersionCode");
        jdField_a_of_type_ArrayOfUo[i].jdField_a_of_type_JavaLangString = localJSONObject.getString("VersionName");
        jdField_a_of_type_ArrayOfUo[i].jdField_a_of_type_Boolean = localJSONObject.getBoolean("RunNeed");
        jdField_a_of_type_ArrayOfUo[i].jdField_b_of_type_Boolean = localJSONObject.getBoolean("Signing");
        jdField_a_of_type_ArrayOfUo[i].jdField_b_of_type_JavaLangString = localJSONObject.getString("PackageName");
        jdField_a_of_type_ArrayOfUo[i].jdField_c_of_type_JavaLangString = localJSONObject.getString("DownUrl");
        if (!localJSONObject.isNull("MarketUri"))
        {
          jdField_a_of_type_ArrayOfUo[i].d = localJSONObject.getString("MarketUri");
          System.out.println("MarketUri : " + localJSONObject.getString("MarketUri"));
          if (jdField_a_of_type_ArrayOfUo[i].d.length() > 1) {
            jdField_a_of_type_JavaLangString = jdField_a_of_type_ArrayOfUo[i].d;
          }
        }
        if (jdField_a_of_type_ArrayOfUo[i].jdField_b_of_type_JavaLangString.contains("com.rsupport.starter")) {
          jdField_a_of_type_ArrayOfUo[i].jdField_c_of_type_Boolean = true;
        }
        System.out.println("---------------------------");
        i += 1;
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static boolean c()
  {
    try
    {
      PackageManager localPackageManager = jdField_a_of_type_AndroidAppActivity.getPackageManager();
      List localList = localPackageManager.getInstalledApplications(0);
      int i = 0;
      for (;;)
      {
        if (i >= localList.size()) {
          return d();
        }
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 8192);
        vf.c("ReMoteCall", "app.packName : " + localApplicationInfo.packageName);
        if (localPackageInfo != null) {
          a(localApplicationInfo.packageName, localPackageInfo.versionCode);
        }
        i += 1;
      }
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static void d()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(jdField_a_of_type_JavaLangString));
    jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
    jdField_a_of_type_AndroidAppActivity.finish();
  }
  
  private static void d(String paramString)
  {
    try
    {
      paramString = "chmod 754 " + paramString;
      Runtime.getRuntime().exec(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static boolean d()
  {
    boolean bool2 = false;
    int i = 0;
    for (;;)
    {
      boolean bool1;
      if (i >= jdField_a_of_type_ArrayOfUo.length) {
        bool1 = true;
      }
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!jdField_a_of_type_ArrayOfUo[i].jdField_c_of_type_Boolean);
      i += 1;
    }
  }
  
  private static void e()
  {
    d = 0;
    g();
    new us().start();
  }
  
  private static void e(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
  }
  
  private static boolean e()
  {
    try
    {
      List localList = jdField_a_of_type_AndroidAppActivity.getPackageManager().getInstalledApplications(0);
      int i = 0;
      for (;;)
      {
        if (i >= localList.size()) {
          return false;
        }
        boolean bool = ((ApplicationInfo)localList.get(i)).packageName.equals("com.rsupport.starter");
        if (bool) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static void f()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addFlags(329252864);
    localIntent.putExtra("ServiceType", 0);
    localIntent.putExtra("UpdateServerURL", b());
    localIntent.setComponent(new ComponentName("com.rsupport.starter", "com.rsupport.starter.MainActivity"));
    jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
  }
  
  private static boolean f()
  {
    try
    {
      Object localObject1 = jdField_a_of_type_AndroidAppActivity.getPackageManager().getInstalledApplications(0);
      int i = 0;
      for (;;)
      {
        if (i >= ((List)localObject1).size()) {
          return false;
        }
        Object localObject2 = (ApplicationInfo)((List)localObject1).get(i);
        if (((ApplicationInfo)localObject2).packageName.contains("com.rsupport.starter"))
        {
          localObject1 = jdField_a_of_type_AndroidAppActivity;
          String str = ((ApplicationInfo)localObject2).packageName;
          localObject2 = ((ApplicationInfo)localObject2).packageName + ".MainActivity";
          if (qq.a(null) != null) {
            qq.a(null).e();
          }
          ((NotificationManager)((Activity)localObject1).getSystemService("notification")).cancelAll();
          Intent localIntent = new Intent("android.intent.action.MAIN");
          localIntent.setAction("android.intent.action.MAIN");
          localIntent.addFlags(329252864);
          localIntent.putExtra("ServiceType", 0);
          localIntent.putExtra("UpdateServerURL", b());
          localIntent.setComponent(new ComponentName(str, (String)localObject2));
          jdField_a_of_type_AndroidAppActivity.startActivity(localIntent);
          ((Activity)localObject1).finish();
          System.exit(0);
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static void g()
  {
    try
    {
      String str = "chmod 771 " + jdField_c_of_type_JavaLangString;
      Runtime.getRuntime().exec(str);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static boolean g()
  {
    Account[] arrayOfAccount = AccountManager.get(jdField_a_of_type_AndroidAppActivity).getAccounts();
    int j;
    int i;
    if (arrayOfAccount != null)
    {
      j = arrayOfAccount.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (arrayOfAccount[i].type.contains("com.google")) {
        return true;
      }
      i += 1;
    }
  }
  
  private static void h()
  {
    d = 0;
    try
    {
      String str = "chmod 771 " + jdField_c_of_type_JavaLangString;
      Runtime.getRuntime().exec(str);
      new us().start();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private static void i()
  {
    d = 0;
    new ut().start();
  }
  
  private static void j()
  {
    d = 0;
    try
    {
      String str = "chmod 771 " + jdField_c_of_type_JavaLangString;
      Runtime.getRuntime().exec(str);
      new us().start();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public final void a(se paramSe)
  {
    this.jdField_a_of_type_Se = paramSe;
    paramSe = new uu().a(b(), Build.MODEL, Build.VERSION.RELEASE, vh.a.getApplicationContext().getPackageName());
    if ((paramSe == null) || (paramSe.length() <= 0)) {}
    for (int i = 0;; i = 1)
    {
      c(paramSe);
      if (i == 0)
      {
        this.jdField_a_of_type_Se.a(-1);
        return;
      }
      if (!c())
      {
        this.jdField_a_of_type_Se.a(1);
        return;
      }
      this.jdField_a_of_type_Se.a(0);
      return;
    }
  }
}
