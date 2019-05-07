package com.acapelagroup.android.tts;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class acattsandroidInstaller
  extends Thread
{
  private static final String TAG = "acattsinstaller";
  static acattsandroidInstaller instance = null;
  boolean error = false;
  String installationPath = null;
  Activity myContext = null;
  
  private acattsandroidInstaller(Activity paramActivity, String paramString)
  {
    this.myContext = paramActivity;
    this.installationPath = paramString;
  }
  
  public static acattsandroidInstaller getInstance()
  {
    if (instance != null) {
      return instance;
    }
    try
    {
      if (instance == null) {
        instance = new acattsandroidInstaller(null, null);
      }
      return instance;
    }
    finally {}
  }
  
  public static acattsandroidInstaller getInstance(Activity paramActivity, String paramString)
  {
    if (instance != null) {
      return instance;
    }
    try
    {
      if (instance == null)
      {
        instance = new acattsandroidInstaller(paramActivity, paramString);
        Log.i("acattsinstaller", "Voice installer created");
      }
      return instance;
    }
    finally {}
  }
  
  public boolean deleteDirectory(File paramFile)
  {
    if (paramFile.exists())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int i = 0;
      if (i < arrayOfFile.length)
      {
        if (arrayOfFile[i].isDirectory()) {
          deleteDirectory(arrayOfFile[i]);
        }
        for (;;)
        {
          i += 1;
          break;
          arrayOfFile[i].delete();
        }
      }
    }
    return paramFile.delete();
  }
  
  public void extractVoicesFromAssets(String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        Log.v("acattsinstaller", "extractVoicesFromAssets");
        paramString2 = new ZipFile(paramString2);
        Enumeration localEnumeration = paramString2.entries();
        if (!localEnumeration.hasMoreElements()) {
          break;
        }
        Object localObject1 = (ZipEntry)localEnumeration.nextElement();
        if (((ZipEntry)localObject1).getName().contains("assets"))
        {
          Object localObject2 = ((ZipEntry)localObject1).getName();
          Log.v("acattsinstaller", (String)localObject2);
          Object localObject3 = ((String)localObject2).substring(((String)localObject2).indexOf("/"), ((String)localObject2).lastIndexOf("/"));
          localObject3 = paramString1 + "/" + (String)localObject3;
          Log.v("acattsinstaller", (String)localObject3);
          if (new File((String)localObject3).mkdirs()) {
            Log.v("acattsinstaller", "folder created");
          }
          localObject2 = new File(paramString1, ((String)localObject2).substring(((String)localObject2).indexOf("/"), ((String)localObject2).length()));
          Log.v("acattsinstaller", ((File)localObject2).getName());
          if (!((File)localObject2).createNewFile()) {
            Log.v("acattsinstaller", "file already exists");
          }
          localObject2 = new FileOutputStream((File)localObject2);
          if (localObject2 == null) {
            Log.v("acattsinstaller", "Error FileOutputStream");
          }
          localObject1 = paramString2.getInputStream((ZipEntry)localObject1);
          if (localObject1 == null) {
            Log.v("acattsinstaller", "Error getInputStream");
          }
          localObject3 = new byte['Ϩ'];
          int i = ((InputStream)localObject1).read((byte[])localObject3, 0, 1000);
          if (i != -1) {
            ((FileOutputStream)localObject2).write((byte[])localObject3, 0, i);
          } else {
            ((FileOutputStream)localObject2).flush();
          }
        }
      }
      catch (IOException paramString1)
      {
        Log.e("acattsinstaller", paramString1.getMessage());
        this.error = true;
        return;
      }
    }
    Log.i("acattsinstaller", "Done installing voices");
    this.error = false;
  }
  
  public String getApkPath()
  {
    Object localObject = this.myContext.getPackageManager();
    String str = this.myContext.getPackageName();
    localObject = ((PackageManager)localObject).getInstalledApplications(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      Log.d("PackageList", "package: " + localApplicationInfo.packageName + ", sourceDir: " + localApplicationInfo.sourceDir);
      if (localApplicationInfo.packageName.equals(str))
      {
        Log.d("Package found", "package: " + localApplicationInfo.packageName + ", sourceDir: " + localApplicationInfo.sourceDir);
        return localApplicationInfo.sourceDir;
      }
    }
    return "";
  }
  
  public String getInstallPath()
  {
    return this.installationPath;
  }
  
  public boolean isSuccess()
  {
    return !this.error;
  }
  
  public void run()
  {
    Log.i("acattsinstaller", "Installing voices...");
    new File("/");
    this.myContext.getPackageName();
    String str = getApkPath();
    Log.i("acattsinstaller", "Installing voices with APK path: " + str + ", target path: " + this.installationPath);
    if ((str != "") && (this.installationPath != null)) {
      extractVoicesFromAssets(this.installationPath, str);
    }
  }
  
  public String searchPackages(File paramFile)
  {
    String str1;
    if (paramFile.isDirectory())
    {
      String[] arrayOfString = paramFile.list();
      if (arrayOfString != null)
      {
        int i = 0;
        while (i < arrayOfString.length)
        {
          String str2 = "";
          str1 = str2;
          if (!arrayOfString[i].contains("proc"))
          {
            str1 = str2;
            if (!arrayOfString[i].contains("dev"))
            {
              str1 = str2;
              if (!arrayOfString[i].contains("sys"))
              {
                str1 = str2;
                if (!arrayOfString[i].contains("root"))
                {
                  str1 = str2;
                  if (!arrayOfString[i].contains("acct"))
                  {
                    str1 = str2;
                    if (!arrayOfString[i].contains("sdcard/DCIM"))
                    {
                      str1 = str2;
                      if (!arrayOfString[i].contains("sdcard/.Trashes"))
                      {
                        str1 = str2;
                        if (!arrayOfString[i].contains("cache")) {
                          str1 = searchPackages(new File(paramFile, arrayOfString[i]));
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          if (str1 != "") {
            return str1;
          }
          i += 1;
        }
      }
    }
    else if (this.myContext != null)
    {
      str1 = this.myContext.getPackageName();
      if (paramFile.getAbsolutePath().contains(str1))
      {
        Log.v("acattsinstaller", "APK found " + paramFile.getAbsolutePath());
        return paramFile.getAbsolutePath();
      }
    }
    return "";
  }
}
