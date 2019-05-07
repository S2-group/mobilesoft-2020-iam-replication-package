package com.ripl.android.instagram;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.FileProvider;
import com.ripl.android.f;
import com.ripl.android.i.ab;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class b
{
  public b() {}
  
  private void a(String paramString)
  {
    ((ClipboardManager)com.ripl.android.b.b().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Ripl text", paramString));
  }
  
  private void b(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent;
    if (a())
    {
      localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType(paramString2);
      paramString1 = new File(paramString1);
      paramString2 = com.ripl.android.b.b();
      localIntent.putExtra("android.intent.extra.STREAM", FileProvider.a(paramString2, paramString2.getString(2131755128), paramString1));
      localIntent.setPackage("com.instagram.android");
    }
    try
    {
      a(paramString3);
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    new ab().a(2131755284);
    return;
    new ab().a(2131755335);
  }
  
  public void a(Activity paramActivity, String paramString)
  {
    Intent localIntent;
    if (a())
    {
      localIntent = com.ripl.android.b.b().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
      if (localIntent == null) {
        break label59;
      }
    }
    try
    {
      a(paramString);
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    new ab().a(2131755284);
    return;
    new ab().a(2131755335);
    label59:
  }
  
  public void a(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    b(paramActivity, paramString1, paramString2, paramString3);
  }
  
  public boolean a()
  {
    new f();
    if (f.i()) {
      return true;
    }
    Object localObject = com.ripl.android.b.b().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledApplications(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        boolean bool = ((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.instagram.android");
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
}
