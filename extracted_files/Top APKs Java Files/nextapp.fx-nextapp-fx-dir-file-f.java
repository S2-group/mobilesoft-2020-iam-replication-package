package nextapp.fx.dir.file;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import nextapp.fx.k;
import nextapp.maui.j.j;
import nextapp.maui.j.j.a;

public class f
{
  private static final k a = new k("/data/app");
  private static final Collection<String> b;
  private static final Collection<String> c;
  
  static
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("/property_contexts");
    localHashSet.add("/charger");
    localHashSet.add("/d");
    localHashSet.add("/etc");
    localHashSet.add("/oem");
    localHashSet.add("/root");
    localHashSet.add("/sdcard");
    localHashSet.add("/sbin");
    localHashSet.add("/xbin");
    localHashSet.add("/usr");
    localHashSet.add("/opt");
    localHashSet.add("/lib");
    localHashSet.add("/bin");
    localHashSet.add("/home");
    localHashSet.add("/var");
    localHashSet.add("/system");
    b = Collections.unmodifiableCollection(localHashSet);
    localHashSet = new HashSet();
    localHashSet.add("/data/anr");
    localHashSet.add("/data/app");
    localHashSet.add("/data/app-asec");
    localHashSet.add("/data/app-lib");
    localHashSet.add("/data/app-private");
    localHashSet.add("/data/data");
    localHashSet.add("/data/home");
    localHashSet.add("/data/local");
    localHashSet.add("/data/media");
    localHashSet.add("/data/system");
    localHashSet.add("/data/user");
    c = Collections.unmodifiableCollection(localHashSet);
  }
  
  private static void a(Context paramContext, Collection<File> paramCollection)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (ApplicationInfo)paramContext.next();
      if (((ApplicationInfo)localObject).sourceDir != null)
      {
        localObject = new k(((ApplicationInfo)localObject).sourceDir);
        if (((k)localObject).a(a))
        {
          localObject = new File("/data/app/" + ((k)localObject).a(2));
          if ((!paramCollection.contains(localObject)) && (((File)localObject).exists()) && (((File)localObject).isDirectory())) {
            paramCollection.add(localObject);
          }
        }
      }
    }
  }
  
  private static void a(Collection<String> paramCollection, Collection<File> paramCollection1)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      File localFile = new File((String)paramCollection.next());
      if ((!paramCollection1.contains(localFile)) && (localFile.exists())) {
        paramCollection1.add(localFile);
      }
    }
  }
  
  public static File[] a(Context paramContext, File paramFile)
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    Object localObject = j.a();
    paramFile = new k(paramFile.getAbsolutePath());
    int i = paramFile.e();
    localObject = ((j)localObject).d().iterator();
    while (((Iterator)localObject).hasNext())
    {
      j.a localA = (j.a)((Iterator)localObject).next();
      k localK = new k(localA.d());
      if ((localK.a(paramFile)) && (localK.e() == i + 1)) {
        localLinkedHashSet.add(new File(localA.d()));
      }
    }
    if (i == 0) {
      a(b, localLinkedHashSet);
    }
    while (localLinkedHashSet.size() == 0)
    {
      return null;
      if ("data".equals(paramFile.a(0))) {
        if (i == 1) {
          a(c, localLinkedHashSet);
        } else if (i == 2) {
          if ("app".equals(paramFile.a(1))) {
            a(paramContext, localLinkedHashSet);
          } else if ("data".equals(paramFile.a(1))) {
            b(paramContext, localLinkedHashSet);
          }
        }
      }
    }
    paramContext = new File[localLinkedHashSet.size()];
    localLinkedHashSet.toArray(paramContext);
    return paramContext;
  }
  
  private static void b(Context paramContext, Collection<File> paramCollection)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (ApplicationInfo)paramContext.next();
      localObject = new File("/data/data/" + ((ApplicationInfo)localObject).packageName);
      if ((!paramCollection.contains(localObject)) && (((File)localObject).exists()) && (((File)localObject).isDirectory())) {
        paramCollection.add(localObject);
      }
    }
  }
}
