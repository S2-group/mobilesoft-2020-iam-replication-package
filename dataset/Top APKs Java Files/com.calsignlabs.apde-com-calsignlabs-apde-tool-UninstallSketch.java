package com.calsignlabs.apde.tool;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.MenuItem;
import com.calsignlabs.apde.APDE;
import com.calsignlabs.apde.APDE.SketchLocation;
import com.calsignlabs.apde.EditorActivity;
import com.calsignlabs.apde.KeyBinding;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class UninstallSketch
  implements Tool
{
  public static final String PACKAGE_NAME = "com.calsignlabs.apde.tool.UninstallSketch";
  private APDE context;
  
  public UninstallSketch() {}
  
  private String getPackageName(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("processing.test.");
    localStringBuilder.append(paramString.toLowerCase(Locale.US));
    return localStringBuilder.toString();
  }
  
  private boolean isSketchInstalled(String paramString)
  {
    Object localObject = this.context.getPackageManager().getInstalledPackages(0);
    paramString = getPackageName(paramString);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((PackageInfo)((Iterator)localObject).next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean createSelectionActionModeMenuItem(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public KeyBinding getKeyBinding()
  {
    return null;
  }
  
  public String getMenuTitle()
  {
    return this.context.getResources().getString(2131690188);
  }
  
  public void init(APDE paramAPDE)
  {
    this.context = paramAPDE;
  }
  
  public void run()
  {
    Object localObject = this.context.getSketchName();
    if (isSketchInstalled((String)localObject))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package:");
      localStringBuilder.append(getPackageName((String)localObject));
      localObject = new Intent("android.intent.action.DELETE", Uri.parse(localStringBuilder.toString()));
      this.context.getEditor().startActivity((Intent)localObject);
      return;
    }
    localObject = new AlertDialog.Builder(this.context.getEditor());
    ((AlertDialog.Builder)localObject).setTitle(2131690190);
    ((AlertDialog.Builder)localObject).setMessage(2131690189);
    ((AlertDialog.Builder)localObject).setNeutralButton(2131689949, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    ((AlertDialog.Builder)localObject).create().show();
  }
  
  public boolean showInToolsMenu(APDE.SketchLocation paramSketchLocation)
  {
    return true;
  }
}
