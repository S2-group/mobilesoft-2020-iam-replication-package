package com.zenprise.configuration;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.citrix.work.log.Logger;
import com.sparus.npcommon.ServicesEnumeration;
import com.zenprise.communication.KernelService;
import com.zenprise.communication.Response;
import java.util.ArrayList;

public class Shell
  extends Activity
{
  static Logger logger = Logger.getLogger("Shell");
  private long reference = 0L;
  
  public Shell() {}
  
  private void request(String paramString, int paramInt)
  {
    if (paramInt == 0)
    {
      Intent localIntent = new Intent("com.googlecode.android_scripting.action.LAUNCH_BACKGROUND_SCRIPT");
      localIntent.setClassName("com.googlecode.android_scripting", "com.googlecode.android_scripting.activity.ScriptingLayerServiceLauncher");
      localIntent.putExtra("com.googlecode.android_scripting.extra.SCRIPT_PATH", paramString);
      localIntent.setFlags(268435456);
      startActivityForResult(localIntent, (int)this.reference);
    }
  }
  
  private boolean sl4aCheckInstall()
  {
    boolean bool2 = false;
    ArrayList localArrayList = (ArrayList)getPackageManager().getInstalledPackages(0);
    int j = localArrayList.size();
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        PackageInfo localPackageInfo = (PackageInfo)localArrayList.get(i);
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo != null) && (!localApplicationInfo.sourceDir.startsWith("/system")) && (localPackageInfo.packageName.equals("com.googlecode.android_scripting"))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    new Response(KernelService.shtp, ServicesEnumeration.NPC_SHELL, 0, Long.valueOf(paramInt1), true);
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      int i = getIntent().getIntExtra("commandCode", -1);
      this.reference = getIntent().getLongExtra("reference", -1L);
      if (sl4aCheckInstall())
      {
        request(getIntent().getStringExtra("path"), i);
        return;
      }
      new Response(KernelService.shtp, ServicesEnumeration.NPC_SHELL, 1, Long.valueOf(this.reference), true);
      finish();
      return;
    }
    catch (Exception paramBundle)
    {
      logger.e("", paramBundle);
      new Response(KernelService.shtp, ServicesEnumeration.NPC_SHELL, 1, Long.valueOf(this.reference), true);
    }
  }
}
