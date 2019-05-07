package com.trendmicro.tmmssuite.core.util;

import a.a.a.d;
import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.trendmicro.tmmssuite.core.app.a;
import com.trendmicro.tmmssuite.core.sys.b;
import com.trendmicro.tmmssuite.core.sys.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SuppressLint({"DefaultLocale"})
public class i
{
  public static final String a = ((Context)b.a(a.a)).getPackageName();
  public static final Object b = new Object();
  public static final String c = ((Context)b.a(a.a)).getFilesDir() + "/lib/testPkgInstaller.apk";
  private static Class<?> d = null;
  private static Object e = null;
  private static Method f = null;
  private static boolean g = true;
  private static PackageManager h;
  private static Map<String, Boolean> i = new HashMap();
  private static PackageInfo j = null;
  private static String k = null;
  
  @SuppressLint({"NewApi"})
  public static long a(PackageInfo paramPackageInfo)
  {
    if (Build.VERSION.SDK_INT >= 9) {}
    for (long l1 = paramPackageInfo.firstInstallTime;; l1 = 0L)
    {
      long l2 = l1;
      if (l1 <= 0L) {
        l2 = new File(paramPackageInfo.applicationInfo.sourceDir).lastModified();
      }
      return l2;
    }
  }
  
  public static PackageManager a()
  {
    if (h == null) {
      h = ((Context)b.a(a.a)).getPackageManager();
    }
    return h;
  }
  
  public static String a(String paramString)
  {
    return b(b(paramString));
  }
  
  public static String a(String paramString1, Resources paramResources, String paramString2)
  {
    if ((paramResources != null) && (paramString1 != null))
    {
      int m = paramResources.getIdentifier(paramString2, "string", paramString1);
      if (m != 0) {
        return paramResources.getString(m);
      }
    }
    return null;
  }
  
  private static String a(ArrayList<byte[]> paramArrayList)
  {
    Iterator localIterator = null;
    Object localObject = localIterator;
    if (paramArrayList != null)
    {
      if (paramArrayList.isEmpty()) {
        localObject = localIterator;
      }
    }
    else {
      return localObject;
    }
    localObject = new ArrayList();
    try
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext()) {
        ((ArrayList)localObject).add(a((byte[])paramArrayList.next()));
      }
      paramArrayList = new String();
    }
    catch (Exception paramArrayList)
    {
      paramArrayList.printStackTrace();
      return null;
    }
    localIterator = ((ArrayList)localObject).iterator();
    for (;;)
    {
      localObject = paramArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      String str = (String)localIterator.next();
      localObject = paramArrayList;
      if (paramArrayList.length() > 0) {
        localObject = paramArrayList + ",";
      }
      paramArrayList = (String)localObject + str;
    }
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("sha-1");
      localMessageDigest.update(paramArrayOfByte);
      return g.b(localMessageDigest.digest());
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  private static ArrayList<String> a(Certificate[] paramArrayOfCertificate)
  {
    int m = 0;
    if ((paramArrayOfCertificate == null) || (paramArrayOfCertificate.length <= 0)) {
      return null;
    }
    ArrayList localArrayList1 = new ArrayList();
    if (paramArrayOfCertificate.length == 1)
    {
      localArrayList1.add(a(paramArrayOfCertificate[0].getPublicKey().getEncoded()));
      return localArrayList1;
    }
    ArrayList localArrayList2 = new ArrayList();
    localArrayList2.add(paramArrayOfCertificate[0].getPublicKey().getEncoded());
    if (m < paramArrayOfCertificate.length - 1)
    {
      if (a(paramArrayOfCertificate[m], paramArrayOfCertificate[(m + 1)])) {
        if (!paramArrayOfCertificate[m].equals(paramArrayOfCertificate[(m + 1)])) {
          localArrayList2.add(paramArrayOfCertificate[(m + 1)].getPublicKey().getEncoded());
        }
      }
      for (;;)
      {
        m += 1;
        break;
        String str = a(localArrayList2);
        if (!localArrayList1.contains(str)) {
          localArrayList1.add(str);
        }
        localArrayList2.clear();
        localArrayList2.add(paramArrayOfCertificate[(m + 1)].getPublicKey().getEncoded());
      }
    }
    if (localArrayList2.size() > 0)
    {
      paramArrayOfCertificate = a(localArrayList2);
      if (!localArrayList1.contains(paramArrayOfCertificate)) {
        localArrayList1.add(paramArrayOfCertificate);
      }
      localArrayList2.clear();
    }
    return localArrayList1;
  }
  
  public static List<String> a(Context paramContext, boolean paramBoolean)
  {
    synchronized (b)
    {
      if ((b.a(a.j) == null) && (b.a(a.k) == null)) {
        a(paramContext);
      }
      paramContext = new ArrayList();
      if (b.a(a.j) != null) {
        paramContext.addAll((Collection)b.a(a.j));
      }
      if ((paramBoolean) && (b.a(a.k) != null)) {
        paramContext.addAll((Collection)b.a(a.k));
      }
      paramContext = new ArrayList(new HashSet(paramContext));
      paramContext.remove(a);
      return paramContext;
    }
  }
  
  public static List<String> a(Intent paramIntent, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Context localContext = (Context)b.a(a.a);
    Object localObject = a().queryIntentActivities(paramIntent, 65536);
    paramIntent = (ResolveInfo)((List)localObject).get(0);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if ((paramIntent.priority == localResolveInfo.priority) && (paramIntent.isDefault == localResolveInfo.isDefault) && ((!paramBoolean) || (a(localContext, localResolveInfo.activityInfo.packageName)))) {
        localArrayList.add(localResolveInfo.activityInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static List<PackageInfo> a(boolean paramBoolean)
  {
    return a(paramBoolean, 512);
  }
  
  public static List<PackageInfo> a(boolean paramBoolean, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = (Context)b.a(a.a);
    PackageManager localPackageManager = ((Context)localObject).getPackageManager();
    localObject = a((Context)localObject, paramBoolean).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      try
      {
        localArrayList.add(localPackageManager.getPackageInfo(str, paramInt));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        c.b(str + " not found");
        localNameNotFoundException.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext)
  {
    for (;;)
    {
      synchronized (b)
      {
        if ((b.a(a.j) == null) && (b.a(a.k) == null))
        {
          localObject2 = new ArrayList();
          ArrayList localArrayList = new ArrayList();
          if (a(paramContext, (List)localObject2, localArrayList))
          {
            b.a(a.j, localObject2);
            b.a(a.k, localArrayList);
          }
        }
        else
        {
          return;
        }
        c.e("Failed to list packages by PkgManager");
        Object localObject2 = i("pm list packages -s");
        if (localObject2 != null)
        {
          paramContext = i("pm list packages -3");
          b.a(a.j, paramContext);
          b.a(a.k, localObject2);
        }
      }
      paramContext = i("pm list packages");
    }
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if ((paramString == null) || (paramContext == null)) {
      return;
    }
    try
    {
      paramString = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + paramString));
      paramString.setFlags(281051136);
      if (paramBoolean)
      {
        paramString.putExtra(":settings:show_fragment_as_subsetting", paramBoolean);
        paramString.putExtra("extra_prefs_show_button_bar", paramBoolean);
        paramString.putExtra("extra_prefs_set_back_text", "BACK");
      }
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      if (paramContext == null) {
        return false;
      }
      return a(paramContext);
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static boolean a(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    if ((paramContext == null) || ((paramList1 == null) && (paramList2 == null))) {
      return false;
    }
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(8704);
        if (paramContext == null) {
          break label168;
        }
        Iterator localIterator = paramContext.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((localApplicationInfo.sourceDir == null) || (localApplicationInfo.packageName.equals(a))) {
          continue;
        }
        if ((localApplicationInfo.flags & 0x1) == 1)
        {
          if (paramList2 == null) {
            continue;
          }
          paramList2.add(localApplicationInfo.packageName);
          continue;
        }
        if (paramList1 == null) {
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        c.b("Failed to use pm.getInstalledApplications in PackageUtil.getPackageListByPkgManager");
        if (paramList2 != null) {
          paramList2.clear();
        }
        if (paramList1 != null) {
          paramList1.clear();
        }
        return false;
      }
      paramList1.add(localApplicationInfo.packageName);
    }
    paramContext.clear();
    return true;
    label168:
    return false;
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) > 0;
  }
  
  private static boolean a(Certificate paramCertificate1, Certificate paramCertificate2)
  {
    if ((paramCertificate1 == null) || (paramCertificate2 == null)) {
      return false;
    }
    try
    {
      paramCertificate1.verify(paramCertificate2.getPublicKey());
      return true;
    }
    catch (Exception paramCertificate1)
    {
      c.c("Failed to verify certificate.");
    }
    return false;
  }
  
  public static boolean a(int[] paramArrayOfInt)
  {
    int n = paramArrayOfInt.length;
    int m = 0;
    while (m < n)
    {
      if (paramArrayOfInt[m] != 0) {
        return false;
      }
      m += 1;
    }
    return true;
  }
  
  private static Certificate[] a(JarFile paramJarFile, JarEntry paramJarEntry, byte[] paramArrayOfByte)
  {
    try
    {
      paramJarFile = paramJarFile.getInputStream(paramJarEntry);
      while (paramJarFile.read(paramArrayOfByte, 0, paramArrayOfByte.length) != -1) {}
      paramJarFile.close();
      if (paramJarEntry != null) {}
      for (paramJarFile = paramJarEntry.getCertificates();; paramJarFile = null)
      {
        paramJarFile = (Certificate[])paramJarFile;
        return paramJarFile;
      }
      return null;
    }
    catch (IOException paramJarFile)
    {
      paramJarFile.printStackTrace();
    }
  }
  
  public static ActivityInfo b()
  {
    Object localObject = (Context)b.a(a.a);
    localObject = a().resolveActivity(h((Context)localObject, c), 65536);
    c.c("getDefaultPkgInstaller info = " + localObject + ", pkgName = " + ((ResolveInfo)localObject).activityInfo.packageName);
    return ((ResolveInfo)localObject).activityInfo;
  }
  
  public static PackageInfo b(String paramString)
  {
    Object localObject = a();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(paramString, 64);
      return localObject;
    }
    catch (Exception localException)
    {
      c.b("Fail to find the path of " + paramString + ", Exception: " + localException.toString());
    }
    return null;
  }
  
  public static String b(PackageInfo paramPackageInfo)
  {
    Object localObject;
    if ((paramPackageInfo == null) || (paramPackageInfo.applicationInfo == null)) {
      localObject = null;
    }
    String str;
    do
    {
      return localObject;
      localObject = (Context)b.a(a.a);
      str = paramPackageInfo.applicationInfo.loadLabel(((Context)localObject).getPackageManager()).toString();
      localObject = str;
    } while (str != null);
    return paramPackageInfo.applicationInfo.name;
  }
  
  private static String b(ArrayList<String> paramArrayList)
  {
    Object localObject1;
    if ((paramArrayList == null) || (paramArrayList.size() <= 0))
    {
      localObject1 = null;
      return localObject1;
    }
    Object[] arrayOfObject = paramArrayList.toArray();
    Arrays.sort(arrayOfObject);
    paramArrayList = new String();
    int n = arrayOfObject.length;
    int m = 0;
    for (;;)
    {
      localObject1 = paramArrayList;
      if (m >= n) {
        break;
      }
      Object localObject2 = arrayOfObject[m];
      localObject1 = paramArrayList;
      if (paramArrayList.length() > 0) {
        localObject1 = paramArrayList + ";";
      }
      paramArrayList = (String)localObject1 + (String)localObject2;
      m += 1;
    }
  }
  
  private static String b(Certificate[] paramArrayOfCertificate)
  {
    return b(a(paramArrayOfCertificate));
  }
  
  public static List<String> b(boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.BROWSABLE");
    localIntent.setData(Uri.parse("http://www.trendmicro.com"));
    return a(localIntent, paramBoolean);
  }
  
  public static void b(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (!TextUtils.isEmpty(paramString)) {
      paramString = h(paramContext, paramString);
    }
    try
    {
      List localList = c(true);
      if ((localList != null) && (localList.size() > 0))
      {
        paramString.setPackage((String)localList.get(0));
        if (!paramBoolean) {
          paramString.addFlags(402653184);
        }
        paramContext.startActivity(paramString);
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean b(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "com.android.internal.app.ResolverActivity";
    arrayOfString[1] = "com.android.internal.app.ResolverActivityEx";
    int n = arrayOfString.length;
    int m = 0;
    while (m < n)
    {
      String str = arrayOfString[m];
      boolean bool = g(paramContext, str);
      c.a("startSelectPkgInstaller: " + str + ", return: " + bool);
      if (bool) {
        return true;
      }
      m += 1;
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      if (paramContext == null) {
        return false;
      }
      return b(paramContext);
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean b(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x8) > 0;
  }
  
  public static PackageInfo c(String paramString)
  {
    ApplicationInfo localApplicationInfo = null;
    if (new File(paramString).exists())
    {
      PackageInfo localPackageInfo = a().getPackageArchiveInfo(paramString, 0);
      if (localPackageInfo != null) {
        localApplicationInfo = localPackageInfo.applicationInfo;
      }
      if (localApplicationInfo != null)
      {
        localApplicationInfo.sourceDir = paramString;
        localApplicationInfo.publicSourceDir = paramString;
      }
      return localPackageInfo;
    }
    return null;
  }
  
  public static String c(Context paramContext)
  {
    Object localObject = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    paramContext.getPreferredActivities((List)localObject, localArrayList, null);
    int n = localArrayList.size();
    int m = 0;
    while (m < n)
    {
      IntentFilter localIntentFilter = (IntentFilter)((List)localObject).get(m);
      if ((localIntentFilter.hasAction("android.intent.action.MAIN")) && (localIntentFilter.hasCategory("android.intent.category.HOME")) && (localIntentFilter.hasCategory("android.intent.category.DEFAULT"))) {
        return ((ComponentName)localArrayList.get(m)).getPackageName();
      }
      m += 1;
    }
    localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    ((Intent)localObject).addCategory("android.intent.category.DEFAULT");
    paramContext = paramContext.queryIntentActivities((Intent)localObject, 65536);
    if (paramContext.size() > 0) {
      return ((ResolveInfo)paramContext.get(0)).activityInfo.packageName;
    }
    return null;
  }
  
  public static List<String> c(boolean paramBoolean)
  {
    return a(h((Context)b.a(a.a), c), paramBoolean);
  }
  
  public static boolean c()
  {
    ActivityInfo localActivityInfo = b();
    return a.equals(localActivityInfo.packageName);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      if (paramContext == null) {
        return false;
      }
      return c(paramContext);
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean c(ApplicationInfo paramApplicationInfo)
  {
    if ((paramApplicationInfo.flags & 0x200000) > 0)
    {
      c.c(paramApplicationInfo.packageName + " is already stopped");
      return true;
    }
    return false;
  }
  
  public static PackageInfo d(String paramString)
  {
    return d.a(paramString);
  }
  
  public static List<String> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = ((InputMethodManager)paramContext.getSystemService("input_method")).getEnabledInputMethodList();
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((InputMethodInfo)paramContext.next()).getPackageName());
      }
    }
    return localArrayList;
  }
  
  public static void d()
  {
    try
    {
      a().clearPackagePreferredActivities(a);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    paramContext = ((DevicePolicyManager)paramContext.getSystemService("device_policy")).getActiveAdmins();
    if ((paramContext != null) && (!paramContext.isEmpty()))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (((ComponentName)paramContext.next()).getPackageName().equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean d(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x80) > 0;
  }
  
  public static String e(String paramString)
  {
    paramString = h(paramString);
    if ((paramString == null) || (paramString.length == 0)) {
      return null;
    }
    return b(paramString);
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    return (!b(paramContext, paramString)) && (!d(paramContext, paramString));
  }
  
  public static boolean e(ApplicationInfo paramApplicationInfo)
  {
    if (a(paramApplicationInfo)) {
      if (i.containsKey(paramApplicationInfo.packageName)) {
        return ((Boolean)i.get(paramApplicationInfo.packageName)).booleanValue();
      }
    }
    boolean bool;
    try
    {
      Object localObject = a();
      if (j == null) {
        j = ((PackageManager)localObject).getPackageInfo("android", 64);
      }
      localObject = ((PackageManager)localObject).getPackageInfo(paramApplicationInfo.packageName, 8768);
      if ((localObject != null) && (((PackageInfo)localObject).signatures != null) && (j.signatures[0].equals(localObject.signatures[0])))
      {
        i.put(paramApplicationInfo.packageName, Boolean.valueOf(false));
        return false;
      }
      if (k == null)
      {
        k = c((Context)b.a(a.a));
        c.b("LAUNCHER_PKG_NAME: " + k);
      }
      if (k != null)
      {
        bool = k.equals(paramApplicationInfo.packageName);
        if (!bool) {}
        for (bool = true;; bool = false) {
          try
          {
            i.put(paramApplicationInfo.packageName, Boolean.valueOf(bool));
          }
          catch (PackageManager.NameNotFoundException paramApplicationInfo) {}
        }
      }
      else
      {
        bool = false;
      }
    }
    catch (PackageManager.NameNotFoundException paramApplicationInfo)
    {
      bool = false;
    }
    return bool;
  }
  
  public static int f(String paramString)
  {
    int m = 0;
    localObject = null;
    str = null;
    try
    {
      paramString = Runtime.getRuntime().exec(paramString);
      str = paramString;
      localObject = paramString;
      a localA1 = new a(paramString.getErrorStream());
      str = paramString;
      localObject = paramString;
      a localA2 = new a(paramString.getInputStream());
      str = paramString;
      localObject = paramString;
      localA1.start();
      str = paramString;
      localObject = paramString;
      localA2.start();
      str = paramString;
      localObject = paramString;
      int n = paramString.waitFor();
      m = n;
    }
    catch (Exception paramString)
    {
      localObject = str;
      paramString.printStackTrace();
      return 0;
    }
    finally
    {
      if (localObject == null) {
        break label109;
      }
    }
    return m;
  }
  
  public static void f(Context paramContext, String paramString)
  {
    a(paramContext, paramString, false);
  }
  
  public static Resources g(String paramString)
  {
    try
    {
      paramString = a().getResourcesForApplication(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    try
    {
      Intent localIntent = h(paramContext, c);
      localIntent.setComponent(new ComponentName("android", paramString));
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      c.b("Failed to startSelectPkgInstaller");
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static Intent h(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (Build.VERSION.SDK_INT <= 23) {}
    for (paramContext = Uri.parse("file://" + paramString);; paramContext = FileProvider.getUriForFile(paramContext, "com.trendmicro.tmmspersonal.provider.GenericFileProvider", new File(paramString)))
    {
      localIntent.setDataAndType(paramContext, "application/vnd.android.package-archive");
      localIntent.addFlags(268435456);
      c.a("Install Uri : " + paramContext.toString());
      return localIntent;
      localIntent.addFlags(1);
    }
  }
  
  /* Error */
  private static Certificate[] h(String paramString)
  {
    // Byte code:
    //   0: sipush 8192
    //   3: newarray byte
    //   5: astore_2
    //   6: new 429	java/util/jar/JarFile
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 726	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   14: astore_1
    //   15: aload_1
    //   16: astore_0
    //   17: aload_1
    //   18: invokevirtual 730	java/util/jar/JarFile:entries	()Ljava/util/Enumeration;
    //   21: astore_3
    //   22: aload_1
    //   23: astore_0
    //   24: aload_3
    //   25: invokeinterface 735 1 0
    //   30: ifeq +127 -> 157
    //   33: aload_1
    //   34: astore_0
    //   35: aload_3
    //   36: invokeinterface 738 1 0
    //   41: checkcast 444	java/util/jar/JarEntry
    //   44: astore 4
    //   46: aload_1
    //   47: astore_0
    //   48: aload 4
    //   50: invokevirtual 741	java/util/jar/JarEntry:isDirectory	()Z
    //   53: ifne -31 -> 22
    //   56: aload_1
    //   57: astore_0
    //   58: aload 4
    //   60: invokevirtual 744	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   63: ldc_w 746
    //   66: invokevirtual 749	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   69: ifne -47 -> 22
    //   72: aload_1
    //   73: astore_0
    //   74: aload_1
    //   75: aload 4
    //   77: aload_2
    //   78: invokestatic 751	com/trendmicro/tmmssuite/core/util/i:a	(Ljava/util/jar/JarFile;Ljava/util/jar/JarEntry;[B)[Ljava/security/cert/Certificate;
    //   81: astore_2
    //   82: aload_2
    //   83: astore_0
    //   84: aload_1
    //   85: ifnull +7 -> 92
    //   88: aload_1
    //   89: invokevirtual 752	java/util/jar/JarFile:close	()V
    //   92: aload_0
    //   93: areturn
    //   94: astore_1
    //   95: aload_1
    //   96: invokevirtual 188	java/lang/Exception:printStackTrace	()V
    //   99: aload_0
    //   100: areturn
    //   101: astore_2
    //   102: aconst_null
    //   103: astore_1
    //   104: aload_1
    //   105: astore_0
    //   106: aload_2
    //   107: invokevirtual 188	java/lang/Exception:printStackTrace	()V
    //   110: aload_1
    //   111: ifnull +7 -> 118
    //   114: aload_1
    //   115: invokevirtual 752	java/util/jar/JarFile:close	()V
    //   118: aconst_null
    //   119: areturn
    //   120: astore_0
    //   121: aload_0
    //   122: invokevirtual 188	java/lang/Exception:printStackTrace	()V
    //   125: goto -7 -> 118
    //   128: astore_1
    //   129: aconst_null
    //   130: astore_0
    //   131: aload_0
    //   132: ifnull +7 -> 139
    //   135: aload_0
    //   136: invokevirtual 752	java/util/jar/JarFile:close	()V
    //   139: aload_1
    //   140: athrow
    //   141: astore_0
    //   142: aload_0
    //   143: invokevirtual 188	java/lang/Exception:printStackTrace	()V
    //   146: goto -7 -> 139
    //   149: astore_1
    //   150: goto -19 -> 131
    //   153: astore_2
    //   154: goto -50 -> 104
    //   157: aconst_null
    //   158: astore_0
    //   159: goto -75 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	paramString	String
    //   14	75	1	localJarFile	JarFile
    //   94	2	1	localException1	Exception
    //   103	12	1	localObject1	Object
    //   128	12	1	localObject2	Object
    //   149	1	1	localObject3	Object
    //   5	78	2	localObject4	Object
    //   101	6	2	localException2	Exception
    //   153	1	2	localException3	Exception
    //   21	15	3	localEnumeration	java.util.Enumeration
    //   44	32	4	localJarEntry	JarEntry
    // Exception table:
    //   from	to	target	type
    //   88	92	94	java/lang/Exception
    //   6	15	101	java/lang/Exception
    //   114	118	120	java/lang/Exception
    //   6	15	128	finally
    //   135	139	141	java/lang/Exception
    //   17	22	149	finally
    //   24	33	149	finally
    //   35	46	149	finally
    //   48	56	149	finally
    //   58	72	149	finally
    //   74	82	149	finally
    //   106	110	149	finally
    //   17	22	153	java/lang/Exception
    //   24	33	153	java/lang/Exception
    //   35	46	153	java/lang/Exception
    //   48	56	153	java/lang/Exception
    //   58	72	153	java/lang/Exception
    //   74	82	153	java/lang/Exception
  }
  
  /* Error */
  private static List<String> i(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: ldc_w 754
    //   9: invokestatic 423	com/trendmicro/tmmssuite/core/sys/c:c	(Ljava/lang/String;)V
    //   12: new 158	java/util/ArrayList
    //   15: dup
    //   16: invokespecial 163	java/util/ArrayList:<init>	()V
    //   19: astore_2
    //   20: invokestatic 668	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   23: aload_0
    //   24: invokevirtual 672	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   27: astore_1
    //   28: new 6	com/trendmicro/tmmssuite/core/util/i$a
    //   31: dup
    //   32: aload_1
    //   33: invokevirtual 678	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   36: invokespecial 681	com/trendmicro/tmmssuite/core/util/i$a:<init>	(Ljava/io/InputStream;)V
    //   39: astore_3
    //   40: new 6	com/trendmicro/tmmssuite/core/util/i$a
    //   43: dup
    //   44: aload_1
    //   45: invokevirtual 683	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   48: aload_2
    //   49: invokespecial 757	com/trendmicro/tmmssuite/core/util/i$a:<init>	(Ljava/io/InputStream;Ljava/util/List;)V
    //   52: astore 4
    //   54: aload_3
    //   55: invokevirtual 686	com/trendmicro/tmmssuite/core/util/i$a:start	()V
    //   58: aload 4
    //   60: invokevirtual 686	com/trendmicro/tmmssuite/core/util/i$a:start	()V
    //   63: aload_1
    //   64: invokevirtual 689	java/lang/Process:waitFor	()I
    //   67: ifeq +9 -> 76
    //   70: ldc_w 759
    //   73: invokestatic 334	com/trendmicro/tmmssuite/core/sys/c:b	(Ljava/lang/String;)V
    //   76: aload_1
    //   77: ifnull +7 -> 84
    //   80: aload_1
    //   81: invokevirtual 762	java/lang/Process:destroy	()V
    //   84: new 65	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   91: ldc_w 764
    //   94: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload_0
    //   98: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: ldc_w 766
    //   104: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: aload_2
    //   108: invokeinterface 522 1 0
    //   113: invokevirtual 769	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   116: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 346	com/trendmicro/tmmssuite/core/sys/c:e	(Ljava/lang/String;)V
    //   122: aload_2
    //   123: invokeinterface 522 1 0
    //   128: ifle -124 -> 4
    //   131: aload_2
    //   132: areturn
    //   133: astore_1
    //   134: ldc_w 771
    //   137: aload_1
    //   138: invokestatic 774	com/trendmicro/tmmssuite/core/sys/c:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   141: goto -57 -> 84
    //   144: astore_1
    //   145: aconst_null
    //   146: astore_1
    //   147: aload_1
    //   148: ifnull -64 -> 84
    //   151: aload_1
    //   152: invokevirtual 762	java/lang/Process:destroy	()V
    //   155: goto -71 -> 84
    //   158: astore_1
    //   159: ldc_w 771
    //   162: aload_1
    //   163: invokestatic 774	com/trendmicro/tmmssuite/core/sys/c:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   166: goto -82 -> 84
    //   169: astore_0
    //   170: aconst_null
    //   171: astore_1
    //   172: aload_1
    //   173: ifnull +7 -> 180
    //   176: aload_1
    //   177: invokevirtual 762	java/lang/Process:destroy	()V
    //   180: aload_0
    //   181: athrow
    //   182: astore_1
    //   183: ldc_w 771
    //   186: aload_1
    //   187: invokestatic 774	com/trendmicro/tmmssuite/core/sys/c:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   190: goto -10 -> 180
    //   193: astore_0
    //   194: goto -22 -> 172
    //   197: astore_3
    //   198: goto -51 -> 147
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	201	0	paramString	String
    //   27	54	1	localProcess	Process
    //   133	5	1	localException1	Exception
    //   144	1	1	localException2	Exception
    //   146	6	1	localObject1	Object
    //   158	5	1	localException3	Exception
    //   171	6	1	localObject2	Object
    //   182	5	1	localException4	Exception
    //   19	113	2	localArrayList	ArrayList
    //   39	16	3	localA1	a
    //   197	1	3	localException5	Exception
    //   52	7	4	localA2	a
    // Exception table:
    //   from	to	target	type
    //   80	84	133	java/lang/Exception
    //   20	28	144	java/lang/Exception
    //   151	155	158	java/lang/Exception
    //   20	28	169	finally
    //   176	180	182	java/lang/Exception
    //   28	76	193	finally
    //   28	76	197	java/lang/Exception
  }
  
  static class a
    extends Thread
  {
    InputStream a = null;
    List<String> b = null;
    
    a(InputStream paramInputStream)
    {
      this.a = paramInputStream;
    }
    
    a(InputStream paramInputStream, List<String> paramList)
    {
      this.a = paramInputStream;
      this.b = paramList;
    }
    
    public void run()
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this.a));
      try
      {
        for (;;)
        {
          String str1 = localBufferedReader.readLine();
          if (str1 == null) {
            break label167;
          }
          if ((this.b != null) && (!TextUtils.isEmpty(str1)))
          {
            String str2 = str1.trim();
            if (!str2.toLowerCase(Locale.ENGLISH).startsWith("package:")) {
              break;
            }
            int i = str2.indexOf(":");
            this.b.add(str2.substring(i + 1));
          }
        }
      }
      catch (Exception localException4)
      {
        for (;;)
        {
          if (localBufferedReader != null) {}
          try
          {
            localBufferedReader.close();
            if (this.a != null) {
              this.a.close();
            }
            return;
          }
          catch (Exception localException3) {}
          c.c("not package info:" + localException4);
        }
      }
      finally
      {
        for (;;)
        {
          if (localBufferedReader != null) {}
          try
          {
            localBufferedReader.close();
            if (this.a != null) {
              this.a.close();
            }
          }
          catch (Exception localException2)
          {
            for (;;) {}
          }
          throw localObject;
          label167:
          if (localBufferedReader != null) {}
          try
          {
            localBufferedReader.close();
            if (this.a != null)
            {
              this.a.close();
              return;
            }
          }
          catch (Exception localException1) {}
        }
      }
    }
  }
}
