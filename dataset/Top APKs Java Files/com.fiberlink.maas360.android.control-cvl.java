import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.LruCache;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class cvl
  implements cbm
{
  public static final String a = cvl.class.getSimpleName();
  public static final Object b = new Object();
  private Context c;
  private LruCache<String, String> d = new LruCache(10);
  private Map<String, PackageInfo> e;
  
  public cvl(Context paramContext)
  {
    this.c = paramContext;
    this.e = new ConcurrentHashMap();
  }
  
  private void a(List<PackageInfo> paramList)
  {
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramList.next();
        this.e.put(localPackageInfo.applicationInfo.packageName, localPackageInfo);
      }
    }
  }
  
  private String b(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return null;
    }
    try
    {
      paramPackageInfo = new ByteArrayInputStream(paramPackageInfo.signatures[0].toByteArray());
      paramPackageInfo = ((X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(paramPackageInfo)).getPublicKey().toString();
      return paramPackageInfo;
    }
    catch (CertificateException paramPackageInfo)
    {
      dho.c(a, paramPackageInfo);
    }
    return null;
  }
  
  public PackageInfo a(String paramString)
  {
    this.d.remove(paramString);
    PackageInfo localPackageInfo = null;
    if (this.e.size() != 0)
    {
      if (this.c.getPackageName().equals(paramString))
      {
        localPackageInfo = (PackageInfo)this.e.get(paramString);
        dho.b(a, new String[] { "Ignoring package removed intent" });
      }
    }
    else {
      return localPackageInfo;
    }
    return (PackageInfo)this.e.remove(paramString);
  }
  
  public PackageInfo a(String paramString, int paramInt)
  {
    PackageInfo localPackageInfo2 = (PackageInfo)this.e.get(paramString);
    switch (paramInt)
    {
    }
    for (;;)
    {
      PackageInfo localPackageInfo1 = null;
      for (;;)
      {
        return localPackageInfo1;
        if (localPackageInfo2 != null) {}
        try
        {
          if (localPackageInfo2.signatures != null)
          {
            localPackageInfo1 = localPackageInfo2;
            if (localPackageInfo2.signatures.length != 0) {}
          }
          else
          {
            localPackageInfo1 = this.c.getPackageManager().getPackageInfo(paramString, 64);
            this.e.put(paramString, localPackageInfo1);
            return localPackageInfo1;
          }
        }
        catch (PackageManager.NameNotFoundException paramString)
        {
          dho.c(a, paramString);
        }
      }
    }
  }
  
  public List<PackageInfo> a()
  {
    if (this.e.size() == 0) {}
    synchronized (b)
    {
      if (this.e.size() == 0)
      {
        List localList = this.c.getPackageManager().getInstalledPackages(0);
        dho.b(a, new String[] { "Size of Installed Package List : " + localList.size() });
        a(localList);
      }
      return new ArrayList(this.e.values());
    }
  }
  
  public void a(PackageInfo paramPackageInfo)
  {
    if (this.e.size() != 0)
    {
      dho.b(a, new String[] { "Size of Installed Package List after adding a Package : " + this.e.size() });
      this.e.put(paramPackageInfo.applicationInfo.packageName, paramPackageInfo);
    }
  }
  
  public PackageInfo b(String paramString)
  {
    if (this.e.size() == 0) {
      a();
    }
    PackageInfo localPackageInfo = (PackageInfo)this.e.get(paramString);
    if (localPackageInfo != null) {
      return localPackageInfo;
    }
    throw new PackageManager.NameNotFoundException(paramString);
  }
  
  public Map<String, PackageInfo> b()
  {
    if (this.e.size() == 0) {
      a();
    }
    return this.e;
  }
  
  public boolean c(String paramString)
  {
    try
    {
      b(paramString);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public String d(String paramString)
  {
    ??? = (String)this.d.get(paramString);
    if (TextUtils.isEmpty((CharSequence)???)) {
      synchronized (b)
      {
        String str = b(a(paramString, 64));
        if (!TextUtils.isEmpty(str)) {
          this.d.put(paramString, str);
        }
        return str;
      }
    }
    return ???;
  }
}
