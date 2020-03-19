package org.cocos2dx.cpp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;

public class AppActivity
  extends Cocos2dxActivity
{
  private static SharedPreferences preferences;
  public static AppActivity self;
  
  public AppActivity() {}
  
  public static void openLinkOrPackage(String paramString)
  {
    Object localObject = self.getPackageManager().getInstalledPackages(0);
    int k = 0;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (localObject != null)
      {
        if (i < ((List)localObject).size()) {
          break label84;
        }
        j = k;
      }
      for (;;)
      {
        if (j == 0)
        {
          paramString = String.format("market://details?id=%s", new Object[] { paramString });
          localObject = new Intent("android.intent.action.VIEW");
          ((Intent)localObject).setData(Uri.parse(paramString));
          self.startActivity((Intent)localObject);
        }
        return;
        label84:
        if (!((PackageInfo)((List)localObject).get(i)).packageName.equals(paramString)) {
          break;
        }
        j = 1;
        try
        {
          self.startActivity(self.getPackageManager().getLaunchIntentForPackage(paramString));
        }
        catch (Exception localException)
        {
          String str = String.format("market://details?id=%s", new Object[] { paramString });
          Intent localIntent = new Intent("android.intent.action.VIEW");
          localIntent.setData(Uri.parse(str));
          self.startActivity(localIntent);
        }
      }
      i += 1;
    }
  }
  
  private void writeToUserDefaults(String paramString1, String paramString2)
  {
    preferences = getSharedPreferences("Cocos2dxPrefsFile", 0);
    SharedPreferences.Editor localEditor = preferences.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    self = this;
    super.onCreate(paramBundle);
    writeToUserDefaults("appName", getPackageName());
  }
}
