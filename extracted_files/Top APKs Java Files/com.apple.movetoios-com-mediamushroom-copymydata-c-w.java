package com.mediamushroom.copymydata.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class w
  implements d
{
  w()
  {
    c(">> CMDGenerateInstalledApps");
    c("<< CMDGenerateInstalledApps");
  }
  
  private String a(Signature[] paramArrayOfSignature)
  {
    if (paramArrayOfSignature == null) {
      return "";
    }
    int j = paramArrayOfSignature.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Object localObject = new ByteArrayInputStream(paramArrayOfSignature[i].toByteArray());
        try
        {
          localObject = (X509Certificate)CertificateFactory.getInstance("X509").generateCertificate((InputStream)localObject);
          if (localObject == null) {
            break label150;
          }
          localObject = ((X509Certificate)localObject).getIssuerDN();
          if (localObject == null) {
            break label150;
          }
          localObject = ((Principal)localObject).getName();
          c("getCertificate, Issuer:  " + (String)localObject);
          if (localObject == null) {
            break label150;
          }
          int k = ((String)localObject).length();
          if (k == 0) {
            break label150;
          }
          return localObject;
        }
        catch (Exception localException)
        {
          d("getCertificate, Exception: " + localException);
        }
      }
      return "";
      label150:
      i += 1;
    }
  }
  
  private byte[] a(y paramY)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("itemID", paramY.a());
      localJSONObject.put("itemTitle", paramY.b);
      localJSONObject.put("itemExternalID", paramY.c);
      localJSONObject.put("itemCertificate", paramY.d);
      paramY = localJSONObject.toString().getBytes();
      return paramY;
    }
    catch (Exception paramY)
    {
      d("buildApplicationData, Exception: " + paramY);
    }
    return null;
  }
  
  private b[] b(String paramString)
  {
    c(">> cacheInstalledApps");
    b[] arrayOfB = c.a(8, paramString);
    Object localObject = arrayOfB;
    if (arrayOfB == null)
    {
      c(">> cacheInstalledApps, No cache available - reading installed apps");
      localObject = b();
      c.a(8, paramString, (b[])localObject);
    }
    c("<< cacheInstalledApps, Num Items: " + localObject.length);
    return localObject;
  }
  
  private y[] b()
  {
    c(">> readUserApplications");
    Object localObject1 = a.a();
    ArrayList localArrayList = new ArrayList();
    label253:
    for (;;)
    {
      try
      {
        PackageManager localPackageManager = ((Context)localObject1).getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
        if (localIterator.hasNext())
        {
          localObject1 = (ApplicationInfo)localIterator.next();
          String str2 = ((ApplicationInfo)localObject1).packageName;
          Object localObject3 = localPackageManager.getApplicationLabel((ApplicationInfo)localObject1);
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(str2, 64);
          if (localObject3 == null) {
            break label253;
          }
          localObject1 = ((CharSequence)localObject3).toString();
          String str1 = "";
          if (localPackageInfo != null) {
            str1 = a(localPackageInfo.signatures);
          }
          c("App:       " + str2);
          c("App Label: " + localObject3);
          localObject3 = new y(this, null);
          ((y)localObject3).a = str2;
          ((y)localObject3).c = str2;
          ((y)localObject3).b = ((String)localObject1);
          ((y)localObject3).d = str1;
          localArrayList.add(localObject3);
        }
        Object localObject2 = "";
      }
      catch (Exception localException)
      {
        d("*******readUserApplications, Exception: " + localException);
        localObject2 = (y[])localArrayList.toArray(new y[localArrayList.size()]);
        c("<< readUserApplications");
        return localObject2;
      }
    }
  }
  
  private static void c(String paramString)
  {
    com.mediamushroom.copymydata.b.c.d("CMDGenerateInstalledApps", paramString);
  }
  
  private static void d(String paramString)
  {
    com.mediamushroom.copymydata.b.c.b("CMDGenerateInstalledApps", paramString);
  }
  
  public e a()
  {
    az localAz = new az();
    localAz.a("__all");
    localAz.a(2);
    return new e(0, localAz);
  }
  
  public e a(String paramString)
  {
    c(">> getItemSummaries");
    if (!paramString.equals("__all"))
    {
      c("getItemSummaries, Bad Account ID: " + paramString);
      return new e(5101);
    }
    b[] arrayOfB = b(paramString);
    int j = arrayOfB.length;
    az[] arrayOfAz = new az[j];
    int i = 0;
    while (i < j)
    {
      y localY = (y)arrayOfB[i];
      az localAz = new az();
      localAz.a(8);
      localAz.a(localY.a());
      localAz.b(paramString);
      arrayOfAz[i] = localAz;
      i += 1;
    }
    paramString = new e(0, arrayOfAz);
    c("<< getItemSummaries, Num Items: " + arrayOfAz.length);
    return paramString;
  }
  
  public e a(String paramString1, String paramString2)
  {
    c(">> getFullItem");
    if (!paramString1.equals("__all"))
    {
      c("getFullItem, Bad Account ID: " + paramString1);
      return new e(5101);
    }
    b(paramString1);
    y localY = (y)c.a(8, paramString1, paramString2);
    if (localY == null)
    {
      c("getFullItem, Cannot Find Item: " + paramString2);
      return new e(5103);
    }
    az localAz = new az();
    localAz.a(8);
    localAz.b(paramString1);
    localAz.a(paramString2);
    localAz.a(a(localY));
    paramString1 = new e(0, localAz);
    paramString1.a("application/json");
    c("<< getFullItem");
    return paramString1;
  }
}
