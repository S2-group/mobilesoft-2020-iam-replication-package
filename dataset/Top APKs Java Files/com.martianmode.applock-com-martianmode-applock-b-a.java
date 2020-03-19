package com.martianmode.applock.b;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
  extends AsyncTask<Void, Void, Void>
{
  private static boolean a = false;
  private final PackageManager b;
  private final File c;
  
  public a(PackageManager paramPackageManager, File paramFile)
  {
    this.b = paramPackageManager;
    this.c = paramFile;
  }
  
  private Bitmap a(Drawable paramDrawable)
  {
    Object localObject;
    if ((paramDrawable instanceof BitmapDrawable))
    {
      localObject = (BitmapDrawable)paramDrawable;
      if (((BitmapDrawable)localObject).getBitmap() != null) {
        return ((BitmapDrawable)localObject).getBitmap();
      }
    }
    if ((paramDrawable.getIntrinsicWidth() > 0) && (paramDrawable.getIntrinsicHeight() > 0)) {
      localObject = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    } else {
      localObject = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localObject;
  }
  
  private void a(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    paramPackageManager = a(paramPackageManager.getApplicationIcon(paramApplicationInfo));
    File localFile = this.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramApplicationInfo.packageName);
    localStringBuilder.append(com.martianmode.applock.f.b.c());
    paramApplicationInfo = new File(localFile, localStringBuilder.toString());
    try
    {
      if (paramApplicationInfo.createNewFile())
      {
        paramApplicationInfo = new FileOutputStream(paramApplicationInfo.getPath());
        paramPackageManager.compress(com.martianmode.applock.f.b.b(), 70, paramApplicationInfo);
        paramApplicationInfo.close();
        return;
      }
    }
    catch (IOException paramPackageManager)
    {
      paramPackageManager.printStackTrace();
    }
  }
  
  private boolean a(String paramString)
  {
    File localFile = this.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(com.martianmode.applock.f.b.c());
    return new File(localFile, localStringBuilder.toString()).exists();
  }
  
  private boolean a(List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      if (!paramList2.contains(paramList1.get(i))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  protected Void a(Void... paramVarArgs)
  {
    Object localObject3 = this.b.getInstalledApplications(0);
    paramVarArgs = new ArrayList();
    if (((List)localObject3).size() == com.martianmode.applock.c.b.c())
    {
      Object localObject1 = com.martianmode.applock.c.b.e();
      Object localObject2 = new ArrayList();
      localObject3 = ((List)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject3).next();
        if (this.b.getLaunchIntentForPackage(localApplicationInfo.packageName) != null)
        {
          paramVarArgs.add(localApplicationInfo);
          ((List)localObject2).add(localApplicationInfo.packageName);
        }
      }
      ((List)localObject2).remove("com.martianmode.applock");
      if ((((List)localObject1).size() != ((List)localObject2).size()) || (a((List)localObject1, (List)localObject2)))
      {
        localObject1 = new ArrayList();
        paramVarArgs = paramVarArgs.iterator();
        while (paramVarArgs.hasNext())
        {
          localObject2 = (ApplicationInfo)paramVarArgs.next();
          if (!a(((ApplicationInfo)localObject2).packageName)) {
            a(this.b, (ApplicationInfo)localObject2);
          }
          ((List)localObject1).add(new com.martianmode.applock.a.b(((ApplicationInfo)localObject2).packageName, this.b.getApplicationLabel((ApplicationInfo)localObject2).toString()));
        }
        com.martianmode.applock.c.b.a((List)localObject1);
        a = true;
      }
    }
    return null;
  }
}
