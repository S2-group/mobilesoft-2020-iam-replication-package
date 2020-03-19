package com.smsBlocker.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AppLauncherUi
  extends Activity
{
  public AppLauncherUi() {}
  
  private static String a(Context paramContext, String paramString)
  {
    paramString = new File(paramContext.getFilesDir().getAbsolutePath(), paramString);
    paramContext = new StringBuilder();
    for (;;)
    {
      try
      {
        paramString = new BufferedReader(new FileReader(paramString));
        str = paramString.readLine();
        if (str != null) {
          continue;
        }
      }
      catch (IOException paramString)
      {
        String str;
        continue;
      }
      return paramContext.toString();
      paramContext.append(str);
    }
  }
  
  private static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(i)).packageName.equals("com.smsBlockerUnlocker")) {
        return true;
      }
      i += 1;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = fileList();
    int j = paramBundle.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {}
      for (i = 0;; i = 1)
      {
        if (i != 0) {
          break label95;
        }
        finish();
        paramBundle = PreferenceManager.getDefaultSharedPreferences(this).edit();
        paramBundle.putBoolean("App_Autostart", false);
        paramBundle.commit();
        startActivity(new Intent(this, InitialUserData.class));
        return;
        if (!paramBundle[i].equals("firstinstallation.txt")) {
          break;
        }
      }
      i += 1;
    }
    label95:
    paramBundle = a(this, "trialflag.txt");
    if ((a(this)) || (paramBundle.equals("0")))
    {
      if (!PreferenceManager.getDefaultSharedPreferences(this).getString("setpassword", "").equals(""))
      {
        finish();
        startActivity(new Intent(this, PasswordProtect.class));
        return;
      }
      finish();
      startActivity(new Intent(this, MainUi.class));
      return;
    }
    finish();
    startActivity(new Intent(this, MainUi.class));
  }
}
