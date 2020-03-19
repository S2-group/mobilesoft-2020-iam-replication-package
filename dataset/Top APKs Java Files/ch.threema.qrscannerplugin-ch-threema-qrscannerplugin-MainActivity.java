package ch.threema.qrscannerplugin;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends Activity
{
  private final String a = "ch.threema.app";
  private final String b = "QRScanner";
  private final int c = 4242;
  private PackageManager d;
  
  public MainActivity() {}
  
  private boolean a(String paramString)
  {
    Iterator localIterator = this.d.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 4242) {
      finish();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.d = getPackageManager();
    if (a("ch.threema.app"))
    {
      paramBundle = this.d.getLaunchIntentForPackage("ch.threema.app");
      paramBundle.addFlags(67108864);
      paramBundle.addFlags(524288);
      startActivity(paramBundle);
      finish();
      return;
    }
    new AlertDialog.Builder(this).setIconAttribute(16843605).setTitle(2130968583).setMessage(2130968582).setNegativeButton(2130968577, new j(this)).setNeutralButton(getString(2130968579), new i(this)).setPositiveButton(2130968581, new h(this)).show();
  }
}
