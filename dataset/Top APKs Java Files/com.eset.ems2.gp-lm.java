import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings.Secure;
import com.eset.commoncore.core.ApplicationBase;
import com.eset.commontools.log.Module;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Module(1)
public class lm
  extends amw
  implements ajm
{
  public lm() {}
  
  private List<ResolveInfo> a(List<ResolveInfo> paramList)
  {
    LinkedList localLinkedList1 = new LinkedList();
    LinkedList localLinkedList2 = new LinkedList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramList.next();
      String str = localResolveInfo.activityInfo.packageName;
      if (!localLinkedList2.contains(str))
      {
        localLinkedList1.add(localResolveInfo);
        localLinkedList2.add(str);
      }
    }
    return localLinkedList1;
  }
  
  private List<nw> a(boolean paramBoolean)
  {
    localLinkedList = new LinkedList();
    try
    {
      Object localObject1 = l().getInstalledApplications(0);
      HashSet localHashSet = new HashSet();
      na localNa = new na();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = ((ApplicationInfo)((Iterator)localObject1).next()).packageName;
        if (localHashSet.add(localObject2))
        {
          localObject2 = localNa.a((String)localObject2, paramBoolean);
          if (localObject2 != null) {
            localLinkedList.add(localObject2);
          }
        }
      }
      return localLinkedList;
    }
    catch (Exception localException) {}
  }
  
  private List<ResolveInfo> b(Intent paramIntent)
  {
    int i = 0;
    if (lu.c() >= 23) {
      i = 131072;
    }
    return l().queryIntentActivities(paramIntent, i);
  }
  
  private List<ResolveInfo> c(Intent paramIntent)
  {
    return l().queryIntentActivities(paramIntent, 65536);
  }
  
  private LinkedList<nw> k()
  {
    localLinkedList = new LinkedList();
    try
    {
      Object localObject = d();
      na localNa = new na();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        nw localNw = localNa.a(((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName, false);
        if (localNw != null) {
          localLinkedList.add(localNw);
        }
      }
      return localLinkedList;
    }
    catch (Exception localException) {}
  }
  
  private PackageManager l()
  {
    return amq.a().getPackageManager();
  }
  
  public List<nw> a()
  {
    localLinkedList = new LinkedList();
    try
    {
      Object localObject1 = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
      Object localObject2 = b((Intent)localObject1);
      localObject1 = new HashSet();
      na localNa = new na();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = ((ResolveInfo)((Iterator)localObject2).next()).activityInfo.packageName;
        if (((Set)localObject1).add(localObject3))
        {
          localObject3 = localNa.a((String)localObject3, true);
          if (localObject3 != null) {
            localLinkedList.add(localObject3);
          }
        }
      }
      return localLinkedList;
    }
    catch (Exception localException) {}
  }
  
  public nw a(Intent paramIntent)
  {
    Object localObject = null;
    Uri localUri = paramIntent.getData();
    paramIntent = localObject;
    if (localUri != null) {
      paramIntent = a(localUri.getEncodedSchemeSpecificPart());
    }
    return paramIntent;
  }
  
  public nw a(String paramString)
  {
    return new na().a(paramString, true);
  }
  
  public boolean a(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString1, paramString2);
    return c(localIntent).size() > 0;
  }
  
  public boolean b(String paramString)
  {
    return new na().a(paramString);
  }
  
  public List<lz> c()
  {
    LinkedList localLinkedList = new LinkedList();
    PackageManager localPackageManager = l();
    Iterator localIterator = d().iterator();
    for (;;)
    {
      ResolveInfo localResolveInfo;
      if (localIterator.hasNext()) {
        localResolveInfo = (ResolveInfo)localIterator.next();
      }
      try
      {
        Object localObject1 = localPackageManager.getLaunchIntentForPackage(localResolveInfo.activityInfo.packageName);
        if (localObject1 != null) {}
        for (localObject1 = ((Intent)localObject1).getComponent().getClassName();; localObject1 = localResolveInfo.activityInfo.name)
        {
          Object localObject2 = localPackageManager.getPackageInfo(localResolveInfo.activityInfo.packageName, 8);
          int i = ((PackageInfo)localObject2).versionCode;
          localObject2 = ((PackageInfo)localObject2).providers;
          localLinkedList.add(new lz(localResolveInfo.loadLabel(localPackageManager).toString(), localResolveInfo.activityInfo.packageName, (String)localObject1, i, (ProviderInfo[])localObject2, localResolveInfo.activityInfo.applicationInfo.uid));
          break;
        }
        return localLinkedList;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  public boolean c(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = k().iterator();
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((nw)localIterator.next()).c().equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public List<ResolveInfo> d()
  {
    return a(b(new Intent("android.intent.action.VIEW", Uri.parse("http://google.com"))));
  }
  
  public boolean d(String paramString)
  {
    String str = Settings.Secure.getString(amq.a().getContentResolver(), "default_input_method");
    return (str != null) && (str.contains(paramString));
  }
  
  public Drawable e(String paramString)
  {
    PackageManager localPackageManager = l();
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 0).applicationInfo.loadIcon(localPackageManager);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public List<nw> e()
  {
    return sp.b(sp.a(a(false), 2, 1));
  }
  
  public String f(String paramString)
  {
    Object localObject = l();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(paramString, 0).applicationInfo.loadLabel((PackageManager)localObject).toString();
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return paramString;
  }
  
  public List<nw> f()
  {
    return sp.b(sp.a(a(false), 1, 0));
  }
  
  public String g()
  {
    return amq.a().getPackageName();
  }
  
  public void g(String paramString)
  {
    nw localNw = a(paramString);
    int i;
    if ((localNw != null) && (localNw.g()))
    {
      i = 1;
      if (((la)amq.b(la.class)).a(paramString) == null) {
        break label72;
      }
    }
    label72:
    for (int j = 1;; j = 0)
    {
      if (i == 0) {
        break label77;
      }
      anh.a(ajp.bu, new alh(ali.a, localNw.a()));
      return;
      i = 0;
      break;
    }
    label77:
    if (j != 0)
    {
      ((ln)ajk.a(ln.class)).a(paramString);
      anh.a(ajp.aQ, paramString);
      return;
    }
    h(paramString);
  }
  
  public int h()
  {
    int i = 0;
    Object localObject = amq.a().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(g(), 128);
      if (localObject != null) {
        i = ((PackageInfo)localObject).versionCode;
      }
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public void h(String paramString)
  {
    paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
    paramString.setFlags(268435456);
    amq.a().startActivity(paramString);
  }
  
  public List<nw> i()
  {
    return new na().a();
  }
  
  public boolean i(String paramString)
  {
    if (lu.a(23)) {
      return amq.a().getPackageManager().checkPermission(paramString, g()) == 0;
    }
    return true;
  }
  
  public aca j(String paramString)
  {
    PackageManager localPackageManager = l();
    try
    {
      paramString = localPackageManager.getPermissionGroupInfo(localPackageManager.getPermissionInfo(paramString, 0).group, 0);
      paramString = new aca(paramString.name, paramString.loadLabel(localPackageManager).toString());
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public LinkedList<nw> j()
  {
    LinkedList localLinkedList = new LinkedList();
    for (;;)
    {
      try
      {
        PackageManager localPackageManager = l();
        Object localObject = localPackageManager.getInstalledPackages(0);
        String str = g();
        localObject = ((List)localObject).iterator();
        if (((Iterator)localObject).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          if ((!localPackageInfo.packageName.startsWith("com.eset.ems")) || (localPackageInfo.packageName.equals(str))) {
            continue;
          }
          nw localNw = new nw((String)localPackageInfo.applicationInfo.loadLabel(localPackageManager), localPackageInfo.applicationInfo.sourceDir, localPackageInfo.applicationInfo.packageName, localPackageInfo.versionName, localPackageInfo.versionCode);
          if ((localPackageInfo.applicationInfo.flags & 0x1) != 0)
          {
            bool = true;
            localNw.a(bool);
            localLinkedList.add(localNw);
          }
        }
        else
        {
          return localLinkedList;
        }
      }
      catch (Exception localException)
      {
        aoa.a(16, lm.class, new Object[] { "${150}", localException });
      }
      boolean bool = false;
    }
  }
  
  public void k(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    localIntent.setFlags(268435456);
    sq.a(localIntent);
  }
  
  public void l(String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
      localIntent.setFlags(268435456);
      List localList = c(localIntent);
      paramString = null;
      Object localObject2 = null;
      Object localObject1 = paramString;
      if (localList != null)
      {
        localObject1 = paramString;
        if (!localList.isEmpty())
        {
          localObject1 = localList.iterator();
          do
          {
            paramString = localObject2;
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
            paramString = (ResolveInfo)((Iterator)localObject1).next();
          } while ((paramString.activityInfo.applicationInfo.flags & 0x1) == 0);
          localObject1 = paramString;
          if (paramString == null) {
            localObject1 = (ResolveInfo)localList.get(0);
          }
        }
      }
      if (localObject1 != null) {
        localIntent.setClassName(((ResolveInfo)localObject1).activityInfo.packageName, ((ResolveInfo)localObject1).activityInfo.name);
      }
      sq.a(localIntent);
      return;
    }
    catch (Exception paramString) {}
  }
  
  public String m(String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = l();
    try
    {
      localObject2 = ((PackageManager)localObject2).getPackageArchiveInfo(paramString, 128);
      paramString = localObject1;
      if (localObject2 != null) {
        paramString = ((PackageInfo)localObject2).applicationInfo.packageName;
      }
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
}
