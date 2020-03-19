package de.sbcomputing.sbdoodle.android;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.files.FileHandle;
import de.sbcomputing.common.osaccess.ImageAccessInterface;
import de.sbcomputing.common.osaccess.OSAccess;
import java.util.LinkedList;
import java.util.List;

public class AndroidOSAccess
  implements OSAccess
{
  public static int RESULT_LOAD_IMAGE = 1;
  public static int RESULT_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
  private AndroidApplication app;
  private ImageAccessInterface iai = null;
  private int imageIndex = 0;
  private FileHandle imagePath = null;
  
  public AndroidOSAccess(AndroidApplication paramAndroidApplication)
  {
    this.app = paramAndroidApplication;
  }
  
  public void createPickIntent()
  {
    if (this.app == null) {
      return;
    }
    Intent localIntent = new Intent("android.intent.action.PICK");
    localIntent.setType("image/*");
    this.app.startActivityForResult(localIntent, RESULT_LOAD_IMAGE);
  }
  
  public void finishImageAccess(final boolean paramBoolean, final int paramInt)
  {
    if (this.iai == null) {
      return;
    }
    Gdx.app.postRunnable(new Runnable()
    {
      public void run()
      {
        AndroidOSAccess.this.iai.imageCopyFinshed(paramBoolean, paramInt);
      }
    });
  }
  
  public ImageAccessInterface imageAccessInterface()
  {
    return this.iai;
  }
  
  public int imageIndex()
  {
    return this.imageIndex;
  }
  
  public FileHandle imagePath()
  {
    return this.imagePath;
  }
  
  public List<String> installedPackages()
  {
    LinkedList localLinkedList = new LinkedList();
    List localList = this.app.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return localLinkedList;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localLinkedList.add(localPackageInfo.packageName);
      }
      i += 1;
    }
  }
  
  public int sdkVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public void startEmail(String paramString1, String paramString2)
  {
    paramString1 = "mailto:" + paramString1;
    paramString2 = new Intent("android.intent.action.SENDTO");
    paramString2.setData(Uri.parse(paramString1));
    try
    {
      this.app.startActivity(paramString2);
      return;
    }
    catch (ActivityNotFoundException paramString1) {}
  }
  
  public void startImageCopy(ImageAccessInterface paramImageAccessInterface, FileHandle paramFileHandle, int paramInt)
  {
    this.iai = paramImageAccessInterface;
    this.imagePath = paramFileHandle;
    this.imageIndex = paramInt;
    try
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        if (ContextCompat.checkSelfPermission(this.app, "android.permission.READ_EXTERNAL_STORAGE") != 0)
        {
          paramImageAccessInterface = this.app;
          int i = RESULT_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;
          ActivityCompat.requestPermissions(paramImageAccessInterface, new String[] { "android.permission.READ_EXTERNAL_STORAGE" }, i);
          return;
        }
        createPickIntent();
        return;
      }
    }
    catch (Exception paramImageAccessInterface)
    {
      finishImageAccess(false, paramInt);
      return;
    }
    createPickIntent();
  }
  
  public String uuid()
  {
    return Settings.Secure.getString(this.app.getContext().getContentResolver(), "android_id");
  }
}
