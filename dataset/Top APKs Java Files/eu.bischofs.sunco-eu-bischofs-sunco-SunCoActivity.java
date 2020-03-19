package eu.bischofs.sunco;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import eu.bischofs.android.commons.b.b;
import eu.bischofs.android.commons.b.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SunCoActivity
  extends Activity
{
  public SunCoActivity() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    int j = 0;
    super.onCreate(paramBundle);
    setContentView(2130903040);
    if (paramBundle == null) {
      getFragmentManager().beginTransaction().replace(2131296298, g.a()).commit();
    }
    paramBundle = new ArrayList();
    if (android.support.a.b.a.a(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
      paramBundle.add("android.permission.ACCESS_FINE_LOCATION");
    }
    if (android.support.a.b.a.a(this, "android.permission.CAMERA") != 0) {
      paramBundle.add("android.permission.CAMERA");
    }
    if (!paramBundle.isEmpty()) {
      android.support.a.a.a.a(this, (String[])paramBundle.toArray(new String[paramBundle.size()]), 2);
    }
    int i;
    if (!a.a(this))
    {
      paramBundle = getPackageManager().getInstalledPackages(1).iterator();
      i = 0;
    }
    for (;;)
    {
      if (!paramBundle.hasNext())
      {
        if (j == 0)
        {
          b.a(this, "PhotoMap Ad", 5, 37);
          b.a(this, "PhotoMap Ad", 2130903053, "market://details?id=eu.bischofs.photomap");
        }
        if (i == 0)
        {
          b.a(this, "MapCam Ad", 13, 37);
          b.a(this, "MapCam Ad", 2130903052, "market://details?id=eu.bischofs.mapcam");
        }
        e.a(this, 8, 16);
        e.a(this, getResources().getString(2131165640), "market://details?id=eu.bischofs.sunco");
        return;
      }
      PackageInfo localPackageInfo = (PackageInfo)paramBundle.next();
      if (localPackageInfo.packageName.equalsIgnoreCase("eu.bischofs.mapcam")) {
        i = 1;
      } else if (localPackageInfo.packageName.equalsIgnoreCase("eu.bischofs.photomap")) {
        j = 1;
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558401, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296338)
    {
      startActivity(new Intent(this, SettingsActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      return;
    } while ((paramArrayOfInt.length <= 0) || (paramArrayOfInt[0] != 0));
    getFragmentManager().beginTransaction().replace(2131296298, g.a()).commit();
  }
}
