package com.netqin.antivirus.scan;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import com.netqin.antivirus.util.NQSPFManager;
import com.netqin.antivirus.util.NQSPFManager.EnumNetQin;
import com.netqin.antivirus.util.t;
import com.netqin.antivirus.util.u;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ScanController
  extends Thread
  implements e
{
  private static int p = 0;
  private static Object r = new Object();
  public Vector a = new Vector();
  public ArrayList b = new ArrayList();
  public ArrayList c = new ArrayList();
  public List d = new ArrayList();
  public int e = 0;
  public int f = 0;
  private int g = 2;
  private Context h;
  private g i;
  private b j;
  private volatile boolean k;
  private a l;
  private LinkedHashSet m;
  private l n;
  private Handler o;
  private int q = 0;
  private long s = 0L;
  private long t = 0L;
  
  public ScanController(Context paramContext)
  {
    super("nqscan");
    this.h = paramContext.getApplicationContext();
    this.k = true;
    this.n = l.a(this.h);
  }
  
  public static int a()
  {
    synchronized (r)
    {
      r.notify();
      int i1 = p;
      return i1;
    }
  }
  
  public static m a(Context paramContext, String paramString)
  {
    m localM = new m();
    if (paramString.equals("duplicate_dex"))
    {
      localM.b = paramContext.getResources().getString(2131361816);
      localM.a = paramContext.getResources().getString(2131361839);
      localM.c = ScanCommon.EnumVirusEngine.other;
      return localM;
    }
    paramString = paramString.split("\\.");
    try
    {
      paramString = (ScanCommon.EnumVirusEngine)Enum.valueOf(ScanCommon.EnumVirusEngine.class, paramString[1]);
      switch (k.a[paramString.ordinal()])
      {
      default: 
        return localM;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = (ScanCommon.EnumVirusEngine)Enum.valueOf(ScanCommon.EnumVirusEngine.class, "other");
        continue;
        localM.b = paramContext.getResources().getString(2131361816);
        localM.a = paramContext.getResources().getString(2131361833);
        localM.c = ScanCommon.EnumVirusEngine.other;
        continue;
        localM.b = paramContext.getResources().getString(2131361817);
        localM.a = paramContext.getResources().getString(2131361826);
        localM.c = ScanCommon.EnumVirusEngine.payment;
        continue;
        localM.b = paramContext.getResources().getString(2131361818);
        localM.a = paramContext.getResources().getString(2131361827);
        localM.c = ScanCommon.EnumVirusEngine.privacy;
        continue;
        localM.b = paramContext.getResources().getString(2131361819);
        localM.a = paramContext.getResources().getString(2131361828);
        localM.c = ScanCommon.EnumVirusEngine.remote;
        continue;
        localM.b = paramContext.getResources().getString(2131361821);
        localM.a = paramContext.getResources().getString(2131361829);
        localM.c = ScanCommon.EnumVirusEngine.spread;
        continue;
        localM.b = paramContext.getResources().getString(2131361814);
        localM.a = paramContext.getResources().getString(2131361830);
        localM.c = ScanCommon.EnumVirusEngine.expense;
        continue;
        localM.b = paramContext.getResources().getString(2131361822);
        localM.a = paramContext.getResources().getString(2131361831);
        localM.c = ScanCommon.EnumVirusEngine.system;
        continue;
        localM.b = paramContext.getResources().getString(2131361815);
        localM.a = paramContext.getResources().getString(2131361837);
        localM.c = ScanCommon.EnumVirusEngine.fraud;
        continue;
        localM.b = paramContext.getResources().getString(2131361820);
        localM.a = paramContext.getResources().getString(2131361832);
        localM.c = ScanCommon.EnumVirusEngine.rogue;
        continue;
        localM.b = paramContext.getResources().getString(2131361824);
        localM.a = paramContext.getResources().getString(2131361836);
        localM.c = ScanCommon.EnumVirusEngine.warning;
        continue;
        localM.b = paramContext.getResources().getString(2131361823);
        localM.a = paramContext.getResources().getString(2131361834);
        localM.c = ScanCommon.EnumVirusEngine.adware;
        continue;
        localM.b = paramContext.getResources().getString(2131361825);
        localM.a = paramContext.getResources().getString(2131361835);
        localM.c = ScanCommon.EnumVirusEngine.pirated;
        continue;
        localM.b = paramContext.getResources().getString(2131361824);
        localM.a = paramContext.getResources().getString(2131361836);
        localM.c = ScanCommon.EnumVirusEngine.questionable;
      }
    }
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramArrayOfByte));
      String str = paramArrayOfByte.getPublicKey().toString();
      com.netqin.antivirus.util.a.a("ScanController", "cert.getPublicKey=" + str);
      if (paramArrayOfByte.getPublicKey().getAlgorithm().equals("DSA")) {
        return ((DSAPublicKey)paramArrayOfByte.getPublicKey()).getY().toString(16).toUpperCase();
      }
      if (paramArrayOfByte.getPublicKey().getAlgorithm().equals("RSA")) {
        return ((RSAPublicKey)paramArrayOfByte.getPublicKey()).getModulus().toString(16).toUpperCase();
      }
      int i1 = str.indexOf("modulus") + 8;
      int i2 = str.indexOf("\n", i1);
      int i3 = str.indexOf(",", i1);
      if (i1 == -1) {
        return null;
      }
      if (i2 != -1) {}
      for (paramArrayOfByte = str.substring(i1, i2);; paramArrayOfByte = str.substring(i1, i3))
      {
        return paramArrayOfByte.trim().toUpperCase();
        if (i3 == -1) {
          break;
        }
      }
      return null;
    }
    catch (CertificateException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
      return null;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  private void a(VirusItem paramVirusItem)
  {
    int i1;
    if (!ScanCommon.b(this.h)) {
      if (!paramVirusItem.category.equalsIgnoreCase(ScanCommon.EnumVirusEngine.warning.toString())) {
        i1 = 1;
      }
    }
    for (;;)
    {
      ResultItem localResultItem;
      if (i1 != 0)
      {
        localResultItem = new ResultItem();
        localResultItem.type = paramVirusItem.type;
        localResultItem.isNativeEngineVirus = true;
        localResultItem.resultType = 0;
        localResultItem.type = paramVirusItem.type;
        localResultItem.fileName = paramVirusItem.fileName;
        localResultItem.fullPath = paramVirusItem.fullPath;
        localResultItem.virusName = paramVirusItem.virusName;
        localResultItem.desc = paramVirusItem.desc;
        localResultItem.nickName = paramVirusItem.nickName;
        localResultItem.classify = paramVirusItem.classify;
        localResultItem.category = paramVirusItem.category;
        localResultItem.packageName = paramVirusItem.packageName;
        localResultItem.programName = paramVirusItem.programName;
        if ((!a(localResultItem)) && (localResultItem.resultType != 1))
        {
          if (localResultItem.type != 2) {
            break label214;
          }
          localResultItem.fileSize = ScanCommon.a(this.h, localResultItem.packageName);
        }
      }
      for (;;)
      {
        paramVirusItem = this.c;
        i1 = this.e;
        this.e = (i1 + 1);
        paramVirusItem.add(i1, localResultItem);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, localResultItem);
        return;
        i1 = 0;
        break;
        label214:
        if (localResultItem.type == 1) {
          localResultItem.fileSize = new File(localResultItem.fullPath).length();
        }
      }
      i1 = 1;
    }
  }
  
  private void a(VirusItem paramVirusItem, String paramString)
  {
    paramString = a(this.h, paramString);
    paramVirusItem.category = paramString.b;
    paramVirusItem.classify = paramString.c;
    paramVirusItem.desc = paramString.a;
  }
  
  private void a(b paramB)
  {
    c localC;
    do
    {
      if ((!paramB.c()) || (!this.k)) {
        return;
      }
      for (localC = paramB.b(); (localC != null) && (localC.b); localC = paramB.b()) {}
    } while ((localC == null) || (localC.b) || (((this.g == 2) || (this.g == 7)) && (!localC.a.toLowerCase().endsWith("apk"))));
    if (this.i != null) {
      this.i.a(1, localC.a, null, null, false, false);
    }
    com.netqin.antivirus.util.a.a("ScanController", "begin getPublicKey pkgName=");
    String str = b(localC.a);
    Object localObject = d(localC.a);
    com.netqin.antivirus.util.a.a("ScanController", "end getPublicKey pkgName=" + (String)localObject + "publickey=" + str);
    if ((this.g == 1) || (this.g == 3) || (this.g == 5)) {}
    for (str = this.n.a(localC.a, (String)localObject, str, true, false); str != null; str = this.n.a(localC.a, (String)localObject, str, true, true))
    {
      localObject = new VirusItem();
      a((VirusItem)localObject, str);
      File localFile = new File(localC.a);
      ((VirusItem)localObject).fileName = localFile.getName();
      ((VirusItem)localObject).fullPath = localFile.getAbsolutePath();
      ((VirusItem)localObject).type = 1;
      ((VirusItem)localObject).virusName = str;
      ((VirusItem)localObject).description = this.n.b();
      ((VirusItem)localObject).nickName = this.n.c();
      this.a.add(localObject);
      a((VirusItem)localObject);
      if (this.i == null) {
        break;
      }
      this.i.a(1, localC.a, null, null, true, false);
      break;
    }
  }
  
  private void a(List paramList, com.netqin.antivirus.b.b.a paramA)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramList.next();
      if (localRunningServiceInfo.service.getPackageName().toString().compareToIgnoreCase(paramA.p()) == 0)
      {
        if (paramA.a == null) {
          paramA.a = new ArrayList();
        }
        paramA.a.add(localRunningServiceInfo.service.getClassName());
      }
    }
  }
  
  private boolean a(ResultItem paramResultItem)
  {
    boolean bool2 = false;
    boolean bool1;
    int i1;
    if (this.c == null)
    {
      bool1 = bool2;
      if (this.c.size() <= 0) {}
    }
    else
    {
      i1 = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i1 < this.c.size())
      {
        if (((ResultItem)this.c.get(i1)).equals(paramResultItem)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  private Certificate[] a(JarFile paramJarFile, JarEntry paramJarEntry)
  {
    Object localObject = null;
    try
    {
      byte[] arrayOfByte = new byte[' '];
      paramJarFile = new BufferedInputStream(paramJarFile.getInputStream(paramJarEntry));
      while (paramJarFile.read(arrayOfByte, 0, arrayOfByte.length) != -1) {}
      paramJarFile.close();
      paramJarFile = localObject;
      if (paramJarEntry != null) {
        paramJarFile = paramJarEntry.getCertificates();
      }
      return paramJarFile;
    }
    catch (RuntimeException paramJarFile)
    {
      return null;
    }
    catch (IOException paramJarFile) {}
    return null;
  }
  
  private void b(ResultItem paramResultItem)
  {
    if ((paramResultItem.virusName != null) && (paramResultItem.virusName.length() > 0) && (paramResultItem.virusName.compareToIgnoreCase("null") != 0))
    {
      VirusItem localVirusItem = new VirusItem();
      a(localVirusItem, paramResultItem.virusName);
      paramResultItem.category = localVirusItem.category;
      paramResultItem.classify = localVirusItem.classify;
      paramResultItem.desc = localVirusItem.desc;
    }
  }
  
  private void c(String paramString)
  {
    if (this.i != null) {
      this.i.a(1, paramString, null, null, false, false);
    }
    File localFile = new File(paramString);
    String str;
    Object localObject;
    if (localFile.exists())
    {
      str = b(paramString);
      localObject = d(paramString);
      if ((this.g == 1) || (this.g == 3) || (this.g == 5)) {
        str = this.n.a(paramString, (String)localObject, str, true, false);
      }
    }
    for (;;)
    {
      if (str != null)
      {
        localObject = new VirusItem();
        a((VirusItem)localObject, str);
        ((VirusItem)localObject).fileName = localFile.getName();
        ((VirusItem)localObject).fullPath = localFile.getAbsolutePath();
        ((VirusItem)localObject).type = 1;
        ((VirusItem)localObject).virusName = str;
        ((VirusItem)localObject).description = this.n.b();
        if (this.a != null)
        {
          this.a.add(localObject);
          a((VirusItem)localObject);
        }
        if (this.i != null) {
          this.i.a(1, paramString, null, null, true, false);
        }
      }
      return;
      str = this.n.a(paramString, (String)localObject, str, true, true);
      continue;
      str = null;
    }
  }
  
  private String d(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramString.toLowerCase().endsWith(".apk"))
      {
        paramString = this.h.getPackageManager().getPackageArchiveInfo(paramString, 0);
        localObject1 = localObject2;
        if (paramString != null) {
          localObject1 = paramString.packageName;
        }
      }
    }
    return localObject1;
  }
  
  private static void d(int paramInt)
  {
    synchronized (r)
    {
      p += paramInt;
      r.notify();
      return;
    }
  }
  
  private boolean g()
  {
    if ((this.g == 1) || (this.g == 5))
    {
      this.j = new b();
      this.j.a(u.a());
    }
    return this.n.a();
  }
  
  private void h()
  {
    if (this.g == 3)
    {
      localIterator = this.m.iterator();
      if ((localIterator.hasNext()) && (this.k)) {}
    }
    while ((this.g != 1) && (this.g != 5)) {
      for (;;)
      {
        Iterator localIterator;
        return;
        File localFile = (File)localIterator.next();
        if (localFile != null) {
          if (localFile.isDirectory())
          {
            b localB = new b();
            localB.a(localFile.getAbsolutePath());
            a(localB);
          }
          else
          {
            c(localFile.getAbsolutePath());
          }
        }
      }
    }
    a(this.j);
  }
  
  private void i()
  {
    Object localObject4 = null;
    for (;;)
    {
      try
      {
        localB = new com.netqin.antivirus.b.b.b(this.h);
      }
      finally
      {
        com.netqin.antivirus.b.b.b localB;
        PackageManager localPackageManager;
        List localList;
        PackageInfo localPackageInfo1;
        String str3;
        String str2;
        String str4;
        continue;
        i1 += 1;
        continue;
        int i1 = 0;
        int i2 = 0;
        continue;
      }
      try
      {
        if (this.b != null)
        {
          this.b.clear();
          localPackageManager = this.h.getPackageManager();
          localList = ((ActivityManager)this.h.getSystemService("activity")).getRunningServices(10000);
          if (this.g != 6) {
            continue;
          }
          String[] arrayOfString = NQSPFManager.a(this.h).a.c(NQSPFManager.EnumNetQin.newinstallapk).split("\n");
          i2 = arrayOfString.length;
          i1 = 0;
          if (i1 >= i2) {
            continue;
          }
          localObject4 = arrayOfString[i1];
          if (TextUtils.isEmpty((CharSequence)localObject4)) {
            continue;
          }
          this.d.add(localObject4);
          continue;
        }
        this.b = new ArrayList();
        continue;
        if (localObject4 == null) {
          continue;
        }
      }
      finally
      {
        localObject4 = localB;
      }
    }
    ((com.netqin.antivirus.b.b.b)localObject4).a();
    throw localObject1;
    if (this.d != null)
    {
      i1 = this.d.size();
      if (i1 > 0) {}
    }
    else if (localB != null)
    {
      localB.a();
    }
    do
    {
      return;
      localObject4 = new ArrayList(2);
      i1 = 0;
      for (;;)
      {
        if (i1 >= this.d.size()) {
          break label1513;
        }
        String str1 = (String)this.d.get(i1);
        try
        {
          ((List)localObject4).add(localPackageManager.getPackageInfo(str1, 192));
          i1 += 1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
      }
      if (i2 >= ((List)localObject4).size()) {
        break label1482;
      }
      boolean bool = this.k;
      if (bool) {
        break;
      }
    } while (localB == null);
    localB.a();
    return;
    localObject4 = com.netqin.android.a.a(this.h);
    break label1513;
    localPackageInfo1 = (PackageInfo)((List)localObject4).get(i2);
    str3 = localPackageInfo1.applicationInfo.publicSourceDir;
    str2 = localPackageInfo1.packageName;
    str4 = localPackageInfo1.applicationInfo.loadLabel(localPackageManager).toString();
    if (i2 % 3 == 0) {
      System.gc();
    }
    for (;;)
    {
      try
      {
        if ((str3.toLowerCase().startsWith("/system/")) || (str3.toLowerCase().startsWith("/opl/"))) {
          continue;
        }
        com.netqin.antivirus.b.b.a localA = new com.netqin.antivirus.b.b.a();
        int i3;
        if (this.i != null)
        {
          this.i.a(2, str3, str4, str2, false, false);
          localObject2 = this.i;
          i3 = this.q;
          this.q = (i3 + 1);
          ((g)localObject2).c(i3);
        }
        Object localObject5 = null;
        com.netqin.antivirus.util.a.a("ScanController", "begin getPublicKey pkgName=" + str2);
        PackageInfo localPackageInfo2 = localPackageManager.getPackageInfo(localPackageInfo1.packageName, 64);
        com.netqin.antivirus.util.a.a("ScanController", "piFull.signatures length=" + localPackageInfo2.signatures.length);
        Object localObject2 = localObject5;
        if (localPackageInfo2.signatures != null)
        {
          localObject2 = localObject5;
          if (localPackageInfo2.signatures.length > 0)
          {
            com.netqin.antivirus.util.a.a("ScanController", "piFull.signatures[0] length=" + localPackageInfo2.signatures[0].toByteArray().length);
            localObject2 = a(localPackageInfo2.signatures[0].toByteArray());
            com.netqin.antivirus.util.a.a("ScanController", "end getPublicKey pkgName=" + str2 + "publickey=" + (String)localObject2);
          }
        }
        if ((this.g == 1) || (this.g == 3) || (this.g == 5))
        {
          com.netqin.antivirus.util.a.a("ScanController", "scan pkgName=" + str2);
          localObject2 = this.n.a(str3, str2, (String)localObject2, true, false);
          if (localObject2 != null)
          {
            localObject5 = new VirusItem();
            a((VirusItem)localObject5, (String)localObject2);
            ((VirusItem)localObject5).packageName = str2;
            ((VirusItem)localObject5).fullPath = str3;
            ((VirusItem)localObject5).type = 2;
            ((VirusItem)localObject5).virusName = ((String)localObject2);
            ((VirusItem)localObject5).description = this.n.b();
            ((VirusItem)localObject5).programName = str4;
            ((VirusItem)localObject5).nickName = this.n.c();
            this.a.add(localObject5);
            a((VirusItem)localObject5);
            if (this.i != null) {
              this.i.a(2, str3, str4, str2, true, false);
            }
          }
          localObject5 = new StringBuilder();
          i3 = i1 + 1;
        }
        try
        {
          localA.p(i1 + "");
          localA.s(str4);
          localA.q(str2);
          localA.r(str3);
          localA.v(localPackageInfo1.versionCode + "");
          localA.w(localPackageInfo1.versionName);
          localA.t(com.netqin.antivirus.b.b.c.a(str3));
          if (localObject2 != null) {
            localA.u((String)localObject2);
          }
          a(localList, localA);
          localObject2 = localB.a(str2, null, null, null, false);
          if (localObject2 != null)
          {
            localA.c(((com.netqin.antivirus.b.b.a)localObject2).c());
            localA.b(((com.netqin.antivirus.b.b.a)localObject2).b());
            localA.u(((com.netqin.antivirus.b.b.a)localObject2).t());
            localA.i(((com.netqin.antivirus.b.b.a)localObject2).h());
          }
          this.b.add(localA);
          if ((localA.a() != null) && (localA.a().length > 0))
          {
            com.netqin.antivirus.util.a.a("ScanController", "info.getCertRSA() != null begin createFile " + str2);
            com.netqin.antivirus.b.b.c.a(this.h.getFilesDir().getAbsolutePath() + "/" + localA.p(), localA.a());
            com.netqin.antivirus.util.a.a("ScanController", "info.getCertRSA() != null end createFile " + str2);
            i1 = i3;
            i2 += 1;
            break;
            if ((this.g == 7) || (this.g == 2))
            {
              localObject2 = this.n.a(str3, str2, (String)localObject2, false, true);
              com.netqin.antivirus.util.a.a("ScanController", "scan pkgName=" + str2);
              continue;
            }
            localObject2 = this.n.a(str3, str2, (String)localObject2, true, true);
            continue;
          }
          com.netqin.antivirus.util.a.a("ScanController", "begin getSignFile " + str2);
          localObject2 = com.netqin.antivirus.b.b.c.a(localA.q(), ".RSA");
          com.netqin.antivirus.util.a.a("ScanController", "end getSignFile " + str2);
          i1 = i3;
          if (localObject2 == null) {
            continue;
          }
          i1 = i3;
          if (localObject2.length <= 0) {
            continue;
          }
          localA.a((byte[])localObject2);
          com.netqin.antivirus.util.a.a("ScanController", "begin createFile " + str2 + " size=" + localObject2.length);
          com.netqin.antivirus.b.b.c.a(this.h.getFilesDir().getAbsolutePath() + "/" + localA.p(), (byte[])localObject2);
          com.netqin.antivirus.util.a.a("ScanController", "end createFile " + str2);
          i1 = i3;
          continue;
          localException1.printStackTrace();
        }
        catch (Exception localException1)
        {
          i1 = i3;
        }
      }
      catch (Exception localException2)
      {
        continue;
      }
      continue;
      if (this.i != null) {
        this.i.a(2, str3, str4, str2, false, false);
      }
    }
    label1482:
    if (localB != null) {
      localB.a();
    }
    System.gc();
  }
  
  private void j()
  {
    int i2;
    Object localObject2;
    String str;
    label44:
    Object localObject1;
    int i4;
    int i3;
    if (this.b != null)
    {
      ArrayList localArrayList = this.b;
      i2 = 0;
      if (i2 < localArrayList.size())
      {
        localObject2 = (com.netqin.antivirus.b.b.a)localArrayList.get(i2);
        str = ((com.netqin.antivirus.b.b.a)localObject2).p();
        i1 = 0;
        if (i1 >= this.c.size()) {
          break label1232;
        }
        localObject1 = (VirusItem)this.c.get(i1);
        if ((((VirusItem)localObject1).type == 2) && (((VirusItem)localObject1).packageName.compareToIgnoreCase(str) == 0))
        {
          i4 = 1;
          i3 = i1;
        }
      }
    }
    for (int i1 = i4;; i1 = 0)
    {
      localObject1 = new ResultItem();
      if ((localObject2 != null) && (((com.netqin.antivirus.b.b.a)localObject2).c() != null))
      {
        ((ResultItem)localObject1).cloudSecurity = Integer.parseInt(((com.netqin.antivirus.b.b.a)localObject2).c());
        if (!ScanCommon.b(this.h)) {
          break label359;
        }
        if (((ResultItem)localObject1).cloudSecurity < 40)
        {
          ((ResultItem)localObject1).type = 2;
          ((ResultItem)localObject1).programName = ((com.netqin.antivirus.b.b.a)localObject2).r();
          ((ResultItem)localObject1).packageName = ((com.netqin.antivirus.b.b.a)localObject2).p();
          ((ResultItem)localObject1).fullPath = ((com.netqin.antivirus.b.b.a)localObject2).q();
          ((ResultItem)localObject1).cloudsecurityDesc = ((com.netqin.antivirus.b.b.a)localObject2).k();
          ((ResultItem)localObject1).cloudScore = ((com.netqin.antivirus.b.b.a)localObject2).h();
          ((ResultItem)localObject1).cloudServerId = ((com.netqin.antivirus.b.b.a)localObject2).f();
          ((ResultItem)localObject1).virusName = ((com.netqin.antivirus.b.b.a)localObject2).t();
          ((ResultItem)localObject1).isNativeEngineVirus = false;
          ((ResultItem)localObject1).resultType = 0;
          ((ResultItem)localObject1).category = this.h.getString(2131361816);
          b((ResultItem)localObject1);
          if (i1 != 0)
          {
            this.c.remove(i3);
            this.e -= 1;
            com.netqin.antivirus.scan.resultdb.b.b(this.h, str);
          }
          ((ResultItem)localObject1).fileSize = ScanCommon.a(this.h, ((ResultItem)localObject1).packageName);
          localObject2 = this.c;
          i1 = this.e;
          this.e = (i1 + 1);
          ((ArrayList)localObject2).add(i1, localObject1);
          com.netqin.antivirus.scan.resultdb.b.b(this.h, (ResultItem)localObject1);
        }
      }
      for (;;)
      {
        i2 += 1;
        break;
        i1 += 1;
        break label44;
        label359:
        if ((localObject2 != null) && (((com.netqin.antivirus.b.b.a)localObject2).c() != null))
        {
          i4 = Integer.parseInt(((com.netqin.antivirus.b.b.a)localObject2).c());
          com.netqin.antivirus.util.a.d("test", "security=" + i4);
          if ((i4 == 10) || (i4 == 31) || (i4 == 30) || (i4 == 32) || (i4 == 33))
          {
            ((ResultItem)localObject1).isNativeEngineVirus = false;
            ((ResultItem)localObject1).type = 2;
            ((ResultItem)localObject1).programName = ((com.netqin.antivirus.b.b.a)localObject2).r();
            ((ResultItem)localObject1).packageName = ((com.netqin.antivirus.b.b.a)localObject2).p();
            ((ResultItem)localObject1).fullPath = ((com.netqin.antivirus.b.b.a)localObject2).q();
            ((ResultItem)localObject1).cloudsecurityDesc = ((com.netqin.antivirus.b.b.a)localObject2).k();
            ((ResultItem)localObject1).cloudSecurity = i4;
            ((ResultItem)localObject1).cloudScore = ((com.netqin.antivirus.b.b.a)localObject2).h();
            ((ResultItem)localObject1).cloudServerId = ((com.netqin.antivirus.b.b.a)localObject2).f();
            ((ResultItem)localObject1).virusName = ((com.netqin.antivirus.b.b.a)localObject2).t();
            switch (i4)
            {
            }
          }
        }
      }
      ((ResultItem)localObject1).category = this.h.getString(2131361816);
      ((ResultItem)localObject1).resultType = 0;
      if (i1 != 0)
      {
        if (((ResultItem)this.c.get(i3)).resultType != 0) {
          break label717;
        }
        this.e -= 1;
      }
      for (;;)
      {
        this.c.remove(i3);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, str);
        ((ResultItem)localObject1).fileSize = ScanCommon.a(this.h, ((ResultItem)localObject1).packageName);
        b((ResultItem)localObject1);
        localObject2 = this.c;
        i1 = this.e;
        this.e = (i1 + 1);
        ((ArrayList)localObject2).add(i1, localObject1);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, (ResultItem)localObject1);
        break;
        label717:
        this.f -= 1;
      }
      ((ResultItem)localObject1).category = this.h.getString(2131361809);
      ((ResultItem)localObject1).resultType = 1;
      if (i1 != 0)
      {
        if (((ResultItem)this.c.get(i3)).resultType != 0) {
          break label884;
        }
        this.e -= 1;
      }
      for (;;)
      {
        this.c.remove(i3);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, str);
        ((ResultItem)localObject1).virusName = "a.pirated.XX";
        ((ResultItem)localObject1).fileSize = ScanCommon.a(this.h, ((ResultItem)localObject1).packageName);
        ((ResultItem)localObject1).classify = ScanCommon.EnumVirusEngine.pirated;
        b((ResultItem)localObject1);
        localObject2 = this.c;
        i1 = this.e;
        i3 = this.f;
        this.f = (i3 + 1);
        ((ArrayList)localObject2).add(i1 + i3, localObject1);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, (ResultItem)localObject1);
        break;
        label884:
        this.f -= 1;
      }
      ((ResultItem)localObject1).category = this.h.getString(2131361925);
      ((ResultItem)localObject1).resultType = 5;
      if (i1 != 0)
      {
        if (((ResultItem)this.c.get(i3)).resultType != 0) {
          break label1051;
        }
        this.e -= 1;
      }
      for (;;)
      {
        this.c.remove(i3);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, str);
        ((ResultItem)localObject1).classify = ScanCommon.EnumVirusEngine.questionable;
        ((ResultItem)localObject1).fileSize = ScanCommon.a(this.h, ((ResultItem)localObject1).packageName);
        ((ResultItem)localObject1).virusName = "a.questionable.XX";
        b((ResultItem)localObject1);
        localObject2 = this.c;
        i1 = this.e;
        i3 = this.f;
        this.f = (i3 + 1);
        ((ArrayList)localObject2).add(i1 + i3, localObject1);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, (ResultItem)localObject1);
        break;
        label1051:
        this.f -= 1;
      }
      ((ResultItem)localObject1).category = this.h.getString(2131361823);
      ((ResultItem)localObject1).resultType = 6;
      if (i1 != 0)
      {
        if (((ResultItem)this.c.get(i3)).resultType != 0) {
          break label1218;
        }
        this.e -= 1;
      }
      for (;;)
      {
        this.c.remove(i3);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, str);
        ((ResultItem)localObject1).classify = ScanCommon.EnumVirusEngine.adware;
        ((ResultItem)localObject1).fileSize = ScanCommon.a(this.h, ((ResultItem)localObject1).packageName);
        ((ResultItem)localObject1).virusName = "a.adware.XX";
        b((ResultItem)localObject1);
        localObject2 = this.c;
        i1 = this.e;
        i3 = this.f;
        this.f = (i3 + 1);
        ((ArrayList)localObject2).add(i1 + i3, localObject1);
        com.netqin.antivirus.scan.resultdb.b.b(this.h, (ResultItem)localObject1);
        break;
        label1218:
        this.f -= 1;
      }
      return;
      label1232:
      i3 = 0;
    }
  }
  
  public VirusItem a(String paramString)
  {
    int i2 = 1;
    if (!this.n.a()) {
      return null;
    }
    Object localObject2 = this.h.getPackageManager();
    for (;;)
    {
      try
      {
        PackageInfo localPackageInfo = ((PackageManager)localObject2).getPackageInfo(paramString, 192);
        if ((localPackageInfo.signatures == null) || (localPackageInfo.signatures.length <= 0)) {
          break label313;
        }
        localObject1 = a(localPackageInfo.signatures[0].toByteArray());
        com.netqin.antivirus.util.a.a("ScanController", "publicKey=" + (String)localObject1);
        String str1 = localPackageInfo.applicationInfo.publicSourceDir;
        String str2 = this.n.a(str1, localPackageInfo.packageName, (String)localObject1, true, false);
        if (str2 == null) {
          break;
        }
        localObject1 = new VirusItem();
        a((VirusItem)localObject1, str2);
        i1 = i2;
        if (!ScanCommon.b(this.h))
        {
          if (!((VirusItem)localObject1).category.equalsIgnoreCase(ScanCommon.EnumVirusEngine.warning.toString())) {
            i1 = i2;
          }
        }
        else
        {
          if (i1 == 0) {
            break;
          }
          ((VirusItem)localObject1).fileName = paramString;
          ((VirusItem)localObject1).programName = localPackageInfo.applicationInfo.loadLabel((PackageManager)localObject2).toString();
          ((VirusItem)localObject1).fullPath = str1;
          ((VirusItem)localObject1).type = 2;
          ((VirusItem)localObject1).virusName = str2;
          ((VirusItem)localObject1).packageName = paramString;
          localObject2 = new ResultItem();
          ((ResultItem)localObject2).resultType = 0;
          ((ResultItem)localObject2).classify = ((VirusItem)localObject1).classify;
          ((VirusItem)localObject1).fileSize = ScanCommon.a(this.h, paramString);
          ((ResultItem)localObject2).setVirusInfo((VirusItem)localObject1);
          if (!com.netqin.antivirus.scan.resultdb.b.a(this.h, (ResultItem)localObject2)) {
            com.netqin.antivirus.scan.resultdb.b.b(this.h, (ResultItem)localObject2);
          }
          return localObject1;
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      int i1 = 0;
      continue;
      label313:
      Object localObject1 = null;
    }
  }
  
  public void a(int paramInt)
  {
    if (paramInt == 0) {
      j();
    }
    this.i.b(paramInt);
    this.i.e();
  }
  
  public void a(g paramG, Handler paramHandler)
  {
    this.i = paramG;
    this.o = paramHandler;
    this.q = 0;
  }
  
  public void a(boolean paramBoolean)
  {
    long l1 = 0L;
    this.k = paramBoolean;
    com.netqin.antivirus.c.b localB;
    if (!paramBoolean)
    {
      if ((!ScanCommon.a(this.h)) || (this.g == 5)) {}
      localB = new com.netqin.antivirus.c.b(this.h);
      localB.e();
      if (this.l != null) {
        this.l.a();
      }
    }
    else
    {
      return;
    }
    long l2 = System.currentTimeMillis();
    l2 -= l2;
    if (l2 < 0L) {}
    for (;;)
    {
      localB.d(l1 + "");
      this.t = l1;
      return;
      l1 = l2;
    }
  }
  
  public String b(String paramString)
  {
    if ((paramString == null) || (!paramString.toLowerCase().endsWith(".apk"))) {
      return null;
    }
    for (;;)
    {
      try
      {
        JarFile localJarFile = new JarFile(paramString);
        paramString = localJarFile.entries();
        if (paramString.hasMoreElements())
        {
          Object localObject = (JarEntry)paramString.nextElement();
          if ((((JarEntry)localObject).isDirectory()) || (((JarEntry)localObject).getName().startsWith("META-INF/"))) {
            continue;
          }
          localObject = a(localJarFile, (JarEntry)localObject);
          paramString = (String)localObject;
          if (localObject == null)
          {
            localJarFile.close();
            return null;
          }
          localJarFile.close();
          if ((paramString != null) && (paramString.length > 0))
          {
            if (paramString[0].getPublicKey().getAlgorithm().equals("DSA")) {
              return ((DSAPublicKey)paramString[0].getPublicKey()).getY().toString(16).toUpperCase();
            }
            paramString = a(new Signature(paramString[0].getEncoded()).toByteArray());
            return paramString;
          }
          return null;
        }
      }
      catch (CertificateEncodingException paramString)
      {
        return null;
      }
      catch (IOException paramString)
      {
        return null;
      }
      catch (RuntimeException paramString)
      {
        return null;
      }
      paramString = null;
    }
  }
  
  public void b()
  {
    if (this.i != null) {
      this.i.i();
    }
  }
  
  public void b(int paramInt)
  {
    if (paramInt == 3)
    {
      this.i.a(paramInt);
      this.i.e();
      return;
    }
    this.i.e();
  }
  
  public void c()
  {
    this.t = 0L;
  }
  
  public void c(int paramInt)
  {
    this.g = paramInt;
  }
  
  public void d()
  {
    if (!g()) {
      if (this.i != null) {
        this.i.a(1);
      }
    }
    label63:
    label146:
    label260:
    label329:
    label663:
    do
    {
      do
      {
        com.netqin.antivirus.c.b localB;
        do
        {
          do
          {
            do
            {
              return;
              if (this.c == null) {
                break;
              }
              if ((this.g != 1) && (this.g != 2) && (this.g != 3)) {
                this.c.clear();
              }
              this.s = System.currentTimeMillis();
              if ((this.g == 1) || (this.g == 2) || (this.g == 7) || (this.g == 5) || (this.g == 6))
              {
                localB = new com.netqin.antivirus.c.b(this.h);
                localB.a();
                com.netqin.antivirus.b.a.a = 1;
                if (this.g != 1) {
                  break label260;
                }
                com.netqin.antivirus.b.a.a = 1;
                localB.h(com.netqin.antivirus.b.a.a + "");
                localB.c(com.netqin.b.a.a());
              }
            } while (!this.k);
            if (this.i != null) {
              this.i.d();
            }
            if (this.a != null) {
              this.a.removeAllElements();
            }
            for (;;)
            {
              if (l.a(this.n)) {
                break label329;
              }
              if (this.i == null) {
                break;
              }
              this.i.a(1);
              return;
              this.c = new ArrayList();
              break label63;
              if (this.g == 5)
              {
                com.netqin.antivirus.b.a.a = 2;
                break label146;
              }
              if (this.g == 6)
              {
                com.netqin.antivirus.b.a.a = 3;
                break label146;
              }
              if ((this.g != 2) && (this.g != 7)) {
                break label146;
              }
              com.netqin.antivirus.b.a.a = 1;
              break label146;
              this.a = new Vector();
            }
          } while (!this.k);
          int i1 = 0;
          if ((this.g == 2) || (this.g == 1) || (this.g == 7) || (this.g == 5)) {
            i1 = 1;
          }
          if ((this.i != null) && (i1 != 0)) {
            this.i.h();
          }
          if (i1 == 0) {
            break;
          }
          this.b = new ArrayList();
        } while (!this.k);
        i();
        for (;;)
        {
          if ((this.g == 1) || (this.g == 5) || (this.g == 3))
          {
            if (this.i != null) {
              this.i.g();
            }
            if (!this.k) {
              break;
            }
            h();
          }
          if (!this.k) {
            break;
          }
          long l1 = System.currentTimeMillis();
          if ((this.g == 1) || (this.g == 2) || (this.g == 5) || (this.g == 6) || (this.g == 7))
          {
            localB = new com.netqin.antivirus.c.b(this.h);
            Long localLong = Long.valueOf(Long.valueOf(l1).longValue() - this.s);
            this.t = localLong.longValue();
            localB.d(localLong.toString());
          }
          if ((this.i != null) && (this.g != 3)) {
            this.i.f();
          }
          if (this.l != null)
          {
            this.l.a();
            this.l = null;
          }
          if (this.g != 3) {
            break label663;
          }
          if ((this.i == null) || (!this.k)) {
            break;
          }
          this.i.e();
          return;
          if (this.g == 6) {
            i();
          }
        }
      } while (!this.k);
      if ((this.b != null) && (this.b.size() > 0) && (this.o != null))
      {
        this.o.post(new j(this));
        return;
      }
    } while ((this.i == null) || (!this.k));
    this.i.e();
  }
  
  public void destroy()
  {
    this.k = false;
    this.a = null;
    this.c = null;
    this.b = null;
    this.m = null;
    this.d = null;
  }
  
  public int e()
  {
    try
    {
      int i1 = this.h.getPackageManager().getInstalledApplications(0).size();
      return i1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public void f()
  {
    List localList = com.netqin.antivirus.scan.resultdb.b.a(this.h);
    if (this.c != null) {
      this.c.clear();
    }
    while ((localList == null) || (localList.isEmpty()) || (this.c == null))
    {
      return;
      this.c = new ArrayList();
    }
    int i1 = 0;
    label62:
    ResultItem localResultItem;
    ArrayList localArrayList;
    int i2;
    if (i1 < localList.size())
    {
      localResultItem = (ResultItem)localList.get(i1);
      if (localResultItem.resultType != 0) {
        break label127;
      }
      localArrayList = this.c;
      i2 = this.e;
      this.e = (i2 + 1);
      localArrayList.add(i2, localResultItem);
    }
    for (;;)
    {
      i1 += 1;
      break label62;
      break;
      label127:
      if ((localResultItem.resultType >= 1) && (localResultItem.resultType <= 6))
      {
        localArrayList = this.c;
        i2 = this.e;
        int i3 = this.f;
        this.f = (i3 + 1);
        localArrayList.add(i2 + i3, localResultItem);
      }
    }
  }
  
  public void run()
  {
    if (!this.k) {
      return;
    }
    d(1);
    this.k = true;
    d();
    d(-1);
  }
}
