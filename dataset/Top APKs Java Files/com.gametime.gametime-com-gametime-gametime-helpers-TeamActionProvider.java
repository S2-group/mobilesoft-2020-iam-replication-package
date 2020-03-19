package com.gametime.gametime.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class TeamActionProvider
  extends ActionProvider
  implements MenuItem.OnMenuItemClickListener
{
  static final int LIST_LENGTH = 3;
  Context mContext;
  
  public TeamActionProvider(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
  }
  
  public boolean hasSubMenu()
  {
    Log.d(getClass().getSimpleName(), "hasSubMenu");
    return true;
  }
  
  public View onCreateActionView()
  {
    Log.d(getClass().getSimpleName(), "onCreateActionView");
    new TextView(this.mContext).setText("Pick");
    return null;
  }
  
  public boolean onMenuItemClick(MenuItem paramMenuItem)
  {
    Toast.makeText(this.mContext, paramMenuItem.getTitle(), 0).show();
    return true;
  }
  
  public boolean onPerformDefaultAction()
  {
    Log.d(getClass().getSimpleName(), "onPerformDefaultAction");
    return super.onPerformDefaultAction();
  }
  
  public void onPrepareSubMenu(SubMenu paramSubMenu)
  {
    Log.d(getClass().getSimpleName(), "onPrepareSubMenu");
    paramSubMenu.clear();
    PackageManager localPackageManager = this.mContext.getPackageManager();
    List localList = localPackageManager.getInstalledApplications(1);
    int i = 0;
    ApplicationInfo localApplicationInfo;
    while (i < Math.min(3, localList.size()))
    {
      localApplicationInfo = (ApplicationInfo)localList.get(i);
      i += 1;
    }
    if (3 < localList.size())
    {
      paramSubMenu = paramSubMenu.addSubMenu(0, 3, 3, "hoge");
      i = 0;
      while (i < localList.size())
      {
        localApplicationInfo = (ApplicationInfo)localList.get(i);
        paramSubMenu.add(0, i, i, localPackageManager.getApplicationLabel(localApplicationInfo)).setIcon(localApplicationInfo.loadIcon(localPackageManager)).setOnMenuItemClickListener(this);
        i += 1;
      }
    }
  }
}
