package sudroid.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipFile;
import sudroid.AssertUtil;
import sudroid.LogUtils;

public class PackageManagerUtil
{
  public PackageManagerUtil() {}
  
  public static Set<String> getInstalledPackagenameSet(Context paramContext)
    throws IOException
  {
    AssertUtil.checkNull(paramContext);
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    if ((paramContext != null) && (paramContext.size() != 0)) {
      paramContext = paramContext.iterator();
    }
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return localHashSet;
      }
      localHashSet.add(((ApplicationInfo)paramContext.next()).packageName);
    }
  }
  
  public static String getPackageName(Context paramContext, File paramFile)
  {
    AssertUtil.checkNull(paramContext);
    AssertUtil.checkNull(paramFile);
    if (FileUtil.doesExisted(paramFile))
    {
      LogUtils.w(paramFile.getAbsolutePath() + " doesn't exist!");
      return null;
    }
    try
    {
      new ZipFile(paramFile, 1);
      paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramFile.getAbsolutePath(), 0);
      if (paramContext == null)
      {
        LogUtils.w("Can't get package archive info!");
        return null;
      }
    }
    catch (IOException paramContext)
    {
      LogUtils.w(paramFile.getAbsolutePath() + " is not a zip file!");
      return null;
    }
    return paramContext.packageName;
  }
  
  public static String getPackageName(Context paramContext, String paramString)
  {
    AssertUtil.checkNull(paramContext);
    AssertUtil.checkStringNullOrEmpty(paramString);
    return getPackageName(paramContext, new File(paramString));
  }
  
  public static String getVersion(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64);
    if (paramContext == null) {
      return null;
    }
    return paramContext.versionName;
  }
}
