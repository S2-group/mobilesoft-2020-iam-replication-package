package com.core42matters.android.registrar;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import dalvik.system.DexFile;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class PackageUtils
{
  static final Comparator<PackageInfo> PACKAGE_COMPARATOR = new Comparator()
  {
    public int compare(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
    {
      return paramAnonymousPackageInfo1.packageName.compareTo(paramAnonymousPackageInfo2.packageName);
    }
  };
  static final Set<String> SHORTHAND_LIBS = new HashSet(Arrays.asList(new String[] { "android.support.design.", "android.support.v4.", "android.support.v7.", "android.support.v8.", "android.support.v13.", "android.support.v14.", "android.support.v17.", "com.fasterxml.jackson.", "com.google.gson.", "com.google.zxing." }));
  
  private PackageUtils()
  {
    throw new AssertionError();
  }
  
  static List<String> digIntoApk(Context paramContext, ApplicationInfo paramApplicationInfo)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      ZipInputStream localZipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(paramApplicationInfo.sourceDir)));
      byte[] arrayOfByte = new byte['ࠀ'];
      String str1 = UUID.randomUUID().toString();
      paramApplicationInfo = paramApplicationInfo.packageName + ".";
      for (;;)
      {
        Object localObject1 = localZipInputStream.getNextEntry();
        if (localObject1 == null) {
          break;
        }
        Object localObject2 = ((ZipEntry)localObject1).getName();
        if ((!((ZipEntry)localObject1).isDirectory()) && (((String)localObject2).startsWith("classes")) && (((String)localObject2).endsWith(".dex")))
        {
          localObject1 = str1 + "-" + (String)localObject2;
          localObject2 = paramContext.openFileOutput((String)localObject1, 0);
          for (;;)
          {
            int i = localZipInputStream.read(arrayOfByte);
            if (i == -1) {
              break;
            }
            ((FileOutputStream)localObject2).write(arrayOfByte, 0, i);
          }
          ((FileOutputStream)localObject2).close();
          try
          {
            localObject2 = new DexFile(paramContext.getFileStreamPath((String)localObject1));
            Enumeration localEnumeration = ((DexFile)localObject2).entries();
            while (localEnumeration.hasMoreElements())
            {
              String str2 = (String)localEnumeration.nextElement();
              if ((!str2.contains("$")) && (!str2.startsWith(paramApplicationInfo))) {
                localArrayList.add(str2);
              }
            }
            ((DexFile)localObject2).close();
          }
          finally
          {
            paramContext.deleteFile((String)localObject1);
          }
          paramContext.deleteFile((String)localObject1);
        }
        localZipInputStream.closeEntry();
      }
      localZipInputStream.close();
      return localArrayList;
    }
    catch (IOException paramContext) {}
    return localArrayList;
  }
  
  static Set<String> findPackages(List<String> paramList)
  {
    Collections.sort(paramList);
    HashSet localHashSet = new HashSet();
    String str1 = null;
    Object localObject = null;
    int i = 0;
    Iterator localIterator = paramList.iterator();
    paramList = str1;
    while (localIterator.hasNext())
    {
      str1 = (String)localIterator.next();
      str1 = str1.substring(0, str1.lastIndexOf('.'));
      if ((!str1.equals("android")) && (!TextUtils.equals((CharSequence)localObject, str1))) {
        if (str1.equals(paramList))
        {
          i += 1;
        }
        else
        {
          if (paramList != null)
          {
            if ((str1.startsWith(paramList)) && (i > 2))
            {
              localObject = str1;
              continue;
            }
            String str2 = paramList.substring(paramList.lastIndexOf('.') + 1);
            if ((str2.length() != 1) || (str2.charAt(0) < 'a') || (str2.charAt(0) > 'z')) {
              localHashSet.add(shorthand(paramList));
            }
          }
          paramList = str1;
          i = 1;
        }
      }
    }
    if (paramList != null)
    {
      localObject = paramList.substring(paramList.lastIndexOf('.') + 1);
      if ((((String)localObject).length() != 1) || (((String)localObject).charAt(0) < 'a') || (((String)localObject).charAt(0) > 'z')) {
        localHashSet.add(shorthand(paramList));
      }
    }
    return localHashSet;
  }
  
  static long fsSize(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      long l = paramString.getBlockSize();
      l = paramString.getAvailableBlocks() * l / 1024L;
      return l;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return -1L;
  }
  
  static List<PackageInfo> getInstalledApps(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    int i;
    if (paramBoolean2) {
      i = 4096;
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getInstalledPackages(i).iterator();
        if (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          try
          {
            ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
            if ((localApplicationInfo.flags & 0x1) != 0) {
              continue;
            }
            i = 1;
            j = i;
            if (paramBoolean1)
            {
              if ((localApplicationInfo.flags & 0x80) == 0) {
                continue;
              }
              j = 1;
              break label148;
            }
            if (j == 0) {
              continue;
            }
            localArrayList.add(localPackageInfo);
          }
          catch (Exception localException) {}
          continue;
          i = 0;
          continue;
          i = 0;
          continue;
          j = 0;
        }
      }
      catch (Exception paramContext)
      {
        Collections.sort(localArrayList, PACKAGE_COMPARATOR);
        return localArrayList;
      }
      label148:
      int j = i | j;
    }
  }
  
  static boolean isDownloaded(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x81) == 0;
  }
  
  static JSONObject makeSnapshot(Context paramContext, Set<String> paramSet1, Set<String> paramSet2)
  {
    long l1 = new File(paramContext.getFilesDir().getAbsoluteFile().toString()).getFreeSpace();
    PackageManager localPackageManager = paramContext.getPackageManager();
    boolean bool;
    Object localObject2;
    JSONObject localJSONObject1;
    if ((paramSet1 != null) && (paramSet1.size() > 0))
    {
      bool = true;
      localObject2 = getInstalledApps(paramContext, true, bool);
      localJSONObject1 = new JSONObject();
    }
    for (;;)
    {
      try
      {
        Object localObject1 = Locale.getDefault();
        localJSONObject1.put("language", ((Locale)localObject1).getLanguage());
        localJSONObject1.put("country", ((Locale)localObject1).getCountry());
        localObject1 = paramContext.getResources().getDisplayMetrics();
        localJSONObject1.put("density", ((DisplayMetrics)localObject1).density);
        localJSONObject1.put("widthPixels", ((DisplayMetrics)localObject1).widthPixels);
        localJSONObject1.put("heightPixels", ((DisplayMetrics)localObject1).heightPixels);
        long l2 = fsSize(Environment.getRootDirectory().getAbsolutePath());
        if (l2 >= 0L) {
          localJSONObject1.put("internalAvailableKB", l2);
        }
        l2 = fsSize(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (l2 >= 0L) {
          localJSONObject1.put("externalAvailableKB", l2);
        }
        localJSONObject1.put("device", Build.DEVICE);
        localJSONObject1.put("model", Build.MODEL);
        localJSONObject1.put("manufacturer", Build.MANUFACTURER);
        localJSONObject1.put("product", Build.PRODUCT);
        localJSONObject1.put("brand", Build.BRAND);
        localJSONObject1.put("sdk", Build.VERSION.SDK_INT);
        localObject1 = new JSONArray();
        localObject2 = ((List)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          JSONObject localJSONObject2 = new JSONObject();
          if ((localPackageInfo.applicationInfo.flags & 0x80) == 0) {
            break label646;
          }
          bool = true;
          localJSONObject2.put("pn", localPackageInfo.packageName);
          localJSONObject2.put("t", localPackageInfo.applicationInfo.loadLabel(localPackageManager));
          localJSONObject2.put("usa", bool);
          localJSONObject2.put("vc", localPackageInfo.versionCode);
          localJSONObject2.put("vn", localPackageInfo.versionName);
          localJSONObject2.put("fit", localPackageInfo.firstInstallTime);
          localJSONObject2.put("lut", localPackageInfo.lastUpdateTime);
          String str = localPackageManager.getInstallerPackageName(localPackageInfo.packageName);
          if (!TextUtils.isEmpty(str))
          {
            localJSONObject2.put("inst", str);
            if ((l1 > 50331648L) && (paramSet2 != null))
            {
              bool = paramSet2.contains(localPackageInfo.packageName);
              if (!bool) {}
            }
          }
          try
          {
            localJSONObject2.put("classpath", new JSONArray(findPackages(digIntoApk(paramContext, localPackageInfo.applicationInfo))));
            if ((paramSet1 != null) && (paramSet1.contains(localPackageInfo.packageName)) && (localPackageInfo.requestedPermissions != null) && (localPackageInfo.requestedPermissions.length > 0)) {
              localJSONObject2.put("permissions", new JSONArray(Arrays.asList(localPackageInfo.requestedPermissions)));
            }
            ((JSONArray)localObject1).put(localJSONObject2);
          }
          catch (Exception localException)
          {
            Logger.w(localException.getMessage());
            continue;
          }
        }
        bool = false;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return localJSONObject1;
        localJSONObject1.put("apps", localObject1);
        return localJSONObject1;
      }
      catch (JSONException paramContext)
      {
        return null;
      }
      break;
      label646:
      bool = false;
    }
  }
  
  static PackageInfo packageInfo(Context paramContext)
  {
    return packageInfo(paramContext.getPackageManager(), paramContext.getPackageName());
  }
  
  static PackageInfo packageInfo(PackageManager paramPackageManager, String paramString)
  {
    Object localObject = null;
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 4294);
      if (paramPackageManager == null) {
        throw new NullPointerException("Cannot get the package info of " + paramString);
      }
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      for (;;)
      {
        paramPackageManager = localObject;
      }
    }
    return paramPackageManager;
  }
  
  static String shorthand(String paramString)
  {
    Iterator localIterator = SHORTHAND_LIBS.iterator();
    do
    {
      str = paramString;
      if (!localIterator.hasNext()) {
        break;
      }
      str = (String)localIterator.next();
    } while (!paramString.startsWith(str));
    String str = str.substring(0, str.length() - 1);
    return str;
  }
  
  static void validateManifest(Context paramContext)
    throws SecurityException
  {
    if (packageInfo(paramContext) == null) {
      throw new NullPointerException("Package info missing for " + paramContext.getPackageName());
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager.checkPermission("android.permission.INTERNET", paramContext.getPackageName()) != 0) {
      throw new SecurityException("No android.permission.INTERNET granted");
    }
    if (localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0) {
      throw new SecurityException("No android.permission.ACCESS_NETWORK_STATE granted");
    }
  }
}