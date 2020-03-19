package com.sand.airdroid.components.apk;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.sand.airdroid.database.AppCache;
import com.sand.airdroid.database.AppCacheDao;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;

public class ApkCacheThread
  extends Thread
{
  public static Logger a = Logger.a("ApkCacheThread");
  @Inject
  AppCacheDao b;
  @Inject
  PackageManager c;
  @Inject
  ApkCacheManager d;
  
  public ApkCacheThread() {}
  
  public void run()
  {
    a.a("start caching...");
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.c.getInstalledPackages(0).iterator();
    if (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      AppCache localAppCache = new AppCache();
      localAppCache.a(localPackageInfo.packageName);
      localAppCache.b(localPackageInfo.applicationInfo.loadLabel(this.c).toString());
      localAppCache.a(Integer.valueOf(localPackageInfo.versionCode));
      localAppCache.c(localPackageInfo.versionName);
      localAppCache.d(localPackageInfo.applicationInfo.sourceDir);
      File localFile = new File(localPackageInfo.applicationInfo.sourceDir);
      localAppCache.b(Long.valueOf(localFile.length()));
      localAppCache.c(Long.valueOf(localFile.lastModified()));
      localAppCache.b(Boolean.valueOf(localFile.canRead()));
      if (((localPackageInfo.applicationInfo.flags & 0x80) != 0) || ((localPackageInfo.applicationInfo.flags & 0x1) != 0)) {
        localAppCache.a(Boolean.valueOf(true));
      }
      for (;;)
      {
        localArrayList.add(localAppCache);
        break;
        localAppCache.a(Boolean.valueOf(false));
      }
    }
    this.b.a(localArrayList);
    this.d.b();
    a.a("cache finished...");
  }
}
