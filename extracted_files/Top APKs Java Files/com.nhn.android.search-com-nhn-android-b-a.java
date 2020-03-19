package com.nhn.android.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class a
{
  public static HashMap<String, String> a = new HashMap();
  
  static
  {
    a.put("SamsungSans", "삼성Sans");
    a.put("Choco cooky", "초코쿠키");
    a.put("ChocoEUKor", "초코쿠키");
    a.put("CoolEUKor", "쿨");
    a.put("Choco", "초코쿠키");
    a.put("Tinker", "팅커벨");
    a.put("AppleMint", "애플민트");
  }
  
  public static String a()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return str2.toUpperCase();
    }
    return str1.toUpperCase() + " " + str2;
  }
  
  public static String a(File paramFile, c paramC, boolean paramBoolean)
  {
    if (!paramFile.exists()) {
      paramFile.mkdirs();
    }
    return a(paramFile.getAbsolutePath() + "/", paramC.e);
  }
  
  static String a(String paramString)
  {
    String str2 = "";
    a();
    str1 = str2;
    for (;;)
    {
      try
      {
        localZipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(paramString)));
        str1 = str2;
        paramString = new byte['Ѐ'];
        paramString = str2;
        str1 = paramString;
        localZipEntry = localZipInputStream.getNextEntry();
        if (localZipEntry == null) {
          continue;
        }
        str1 = paramString;
        str2 = localZipEntry.getName();
        str1 = str2;
        if (str2.indexOf(".ttf") == -1) {
          continue;
        }
        str1 = str2;
        if (str2.startsWith("assets/fonts/")) {
          continue;
        }
        str1 = str2;
        if (str2.startsWith("assets/font.")) {
          continue;
        }
      }
      catch (Exception paramString)
      {
        ZipInputStream localZipInputStream;
        ZipEntry localZipEntry;
        paramString = str1;
        continue;
        int i = 1;
        continue;
        i = 0;
        continue;
      }
      paramString = str2;
      str1 = str2;
      if (!localZipEntry.isDirectory())
      {
        paramString = str2;
        if (i != 1)
        {
          str1 = str2;
          i = str2.lastIndexOf("/");
          paramString = str2;
          if (i != -1)
          {
            str1 = str2;
            paramString = str2.substring(i + 1);
          }
          str1 = paramString;
          localZipInputStream.close();
          str1 = paramString;
          localZipInputStream.closeEntry();
          str1 = paramString;
          localZipInputStream.close();
          return paramString;
        }
      }
    }
    str1 = paramString;
    localZipInputStream.close();
    return paramString;
  }
  
  static String a(String paramString1, String paramString2)
  {
    Object localObject = "";
    String str = a();
    int i;
    for (;;)
    {
      ZipInputStream localZipInputStream;
      try
      {
        localZipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(paramString2)));
        arrayOfByte = new byte['Ѐ'];
        paramString2 = (String)localObject;
        localZipEntry = localZipInputStream.getNextEntry();
        if (localZipEntry == null) {
          break label219;
        }
        localObject = localZipEntry.getName();
        if (str.startsWith("SAMSUNG") != true) {
          break label195;
        }
        if (((String)localObject).indexOf(".ttf") == -1) {
          break label243;
        }
        if (((String)localObject).indexOf("fonts/") != -1) {
          break label190;
        }
      }
      catch (IOException paramString1)
      {
        byte[] arrayOfByte;
        ZipEntry localZipEntry;
        paramString1.printStackTrace();
        return "";
      }
      paramString2 = (String)localObject;
      if (!localZipEntry.isDirectory())
      {
        paramString2 = (String)localObject;
        if (i != 1)
        {
          i = ((String)localObject).lastIndexOf("/");
          paramString2 = (String)localObject;
          if (i != -1) {
            paramString2 = ((String)localObject).substring(i + 1);
          }
          localObject = new FileOutputStream(paramString1 + paramString2);
          i = localZipInputStream.read(arrayOfByte);
          if (i != -1)
          {
            ((FileOutputStream)localObject).write(arrayOfByte, 0, i);
            continue;
            label190:
            i = 0;
            continue;
            label195:
            if (((String)localObject).indexOf(".ttf") != -1) {
              break label251;
            }
            i = 1;
            break;
          }
          else
          {
            ((FileOutputStream)localObject).close();
            localZipInputStream.closeEntry();
            label219:
            localZipInputStream.close();
            return paramString1 + paramString2;
            label243:
            i = 1;
          }
        }
      }
    }
    for (;;)
    {
      break;
      label251:
      i = 0;
    }
  }
  
  public static c[] a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject1 = paramContext.getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      if ((((ApplicationInfo)localObject2).packageName.startsWith("com.monotype.android.font.") == true) || (((ApplicationInfo)localObject2).packageName.startsWith("com.hy.app.FontSettings.") == true))
      {
        c localC = new c();
        localC.a = paramContext.getApplicationLabel((ApplicationInfo)localObject2).toString();
        localC.b = ((String)a.get(localC.a));
        if (localC.b == null) {
          localC.b = localC.a;
        }
        localC.c = ((ApplicationInfo)localObject2).packageName;
        localC.e = ((ApplicationInfo)localObject2).publicSourceDir;
        localC.f = false;
        localObject2 = new File("/system/fonts", a(localC.e));
        if (((File)localObject2).exists() == true) {
          localC.d = ((File)localObject2).getAbsolutePath();
        }
        localArrayList.add(localC);
      }
    }
    return (c[])localArrayList.toArray(new c[0]);
  }
}
