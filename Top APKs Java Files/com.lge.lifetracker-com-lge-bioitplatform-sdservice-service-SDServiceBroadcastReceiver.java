package com.lge.bioitplatform.sdservice.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.lge.bioitplatform.sdservice.config.Config;
import com.lge.bioitplatform.sdservice.database.Database;
import com.lge.bioitplatform.sdservice.debug.DataLogger;
import com.lge.bioitplatform.sdservice.exception.MemoryException;
import com.lge.bioitplatform.sdservice.service.migration.DBMigration;
import com.lge.bioitplatform.sdservice.util.Preference;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class SDServiceBroadcastReceiver
  extends BroadcastReceiver
{
  private static final String TAG;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(SDServiceBroadcastReceiver.class.getSimpleName());
    localStringBuilder.append("::");
    TAG = localStringBuilder.toString();
  }
  
  public SDServiceBroadcastReceiver() {}
  
  private boolean isInstalledPackage(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (Config.DEBUG_MODE)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(TAG);
        localStringBuilder.append("[isInstalledPackage] pkg name: ");
        localStringBuilder.append(localPackageInfo.packageName);
        DataLogger.info(localStringBuilder.toString());
      }
      if (localPackageInfo.packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void g3Migration(Context paramContext)
  {
    paramContext = new g3MigrationThread(paramContext, null);
    paramContext.setPriority(5);
    paramContext.start();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    Object localObject = paramIntent.getAction();
    if (localObject != null)
    {
      if (((String)localObject).length() == 0) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(TAG);
      localStringBuilder.append("[onReceive] Intent action: ");
      localStringBuilder.append(paramIntent.getAction());
      DataLogger.debug(localStringBuilder.toString());
      if (((String)localObject).equals("android.intent.action.PACKAGE_REPLACED")) {
        if (paramIntent.getData() != null)
        {
          if (paramIntent.getData().getSchemeSpecificPart() == null) {
            return;
          }
          paramIntent = paramIntent.getData().getSchemeSpecificPart();
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(TAG);
          ((StringBuilder)localObject).append("[onReceive] receive intent after update");
          DataLogger.debug(((StringBuilder)localObject).toString());
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(TAG);
          ((StringBuilder)localObject).append("[onReceive] replaced package: ");
          ((StringBuilder)localObject).append(paramIntent);
          DataLogger.debug(((StringBuilder)localObject).toString());
          if (paramIntent.equalsIgnoreCase("com.lge.lifetracker"))
          {
            if (Preference.getInt(paramContext, "migration_state", 2) != 0)
            {
              if (isInstalledPackage(paramContext, "com.lge.bioitplatform.sdservice.service"))
              {
                paramIntent = Database.getInstance(paramContext);
                if (paramIntent.getMigrationCompleted()) {
                  return;
                }
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append(TAG);
                ((StringBuilder)localObject).append("[onCreate] agent app was running");
                DataLogger.debug(((StringBuilder)localObject).toString());
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append(TAG);
                ((StringBuilder)localObject).append("[onCreate] agent app was replaced");
                DataLogger.debug(((StringBuilder)localObject).toString());
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append(TAG);
                ((StringBuilder)localObject).append("[onReceive] move DB from agent to health app");
                DataLogger.debug(((StringBuilder)localObject).toString());
                try
                {
                  if (paramIntent.transferPerson() == 1L) {
                    paramIntent.transferGoal();
                  }
                }
                catch (MemoryException paramIntent)
                {
                  paramIntent.printStackTrace();
                }
                paramContext.sendBroadcast(new Intent("com.lge.bioitplatform.sdservice.intent.INTENT_MIGRATION"));
              }
              else
              {
                paramIntent = new StringBuilder();
                paramIntent.append(TAG);
                paramIntent.append("[onCreate] agent app was not running");
                DataLogger.debug(paramIntent.toString());
                g3Migration(paramContext);
              }
              paramIntent = new Intent("com.lge.bioitplatform.sdservice.intent.INTENT_PROFILE_MIGRATION_COMPLETE");
              paramContext.sendBroadcast(paramIntent);
              paramContext = new StringBuilder();
              paramContext.append(TAG);
              paramContext.append("[onReceive] send Intent: ");
              paramContext.append(paramIntent.getAction());
              DataLogger.debug(paramContext.toString());
            }
          }
          else if (paramIntent.equalsIgnoreCase("com.lge.bioitplatform.sdservice.service")) {
            paramContext.sendBroadcast(new Intent("com.lge.bioitplatform.action.STOP_SERVICE"));
          }
        }
        else
        {
          return;
        }
      }
      return;
    }
  }
  
  class g3MigrationThread
    extends Thread
  {
    private Context context;
    
    private g3MigrationThread(Context paramContext)
    {
      this.context = paramContext;
    }
    
    public void run()
    {
      Object localObject1 = this.context.getDatabasePath("lifetracker2.db");
      if ((localObject1 != null) && (((File)localObject1).exists()))
      {
        if (Database.getInstance(this.context).getAllActivity() == null)
        {
          Object localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(SDServiceBroadcastReceiver.TAG);
          ((StringBuilder)localObject2).append("[onCreate] lifetracker DB exists");
          DataLogger.debug(((StringBuilder)localObject2).toString());
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(SDServiceBroadcastReceiver.TAG);
          ((StringBuilder)localObject2).append("[onCreate] health app was replaced");
          DataLogger.debug(((StringBuilder)localObject2).toString());
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(SDServiceBroadcastReceiver.TAG);
          ((StringBuilder)localObject2).append("[onReceive] lifetracker2.db.getParent() = ");
          ((StringBuilder)localObject2).append(((File)localObject1).getParent());
          DataLogger.info(((StringBuilder)localObject2).toString());
          localObject1 = ((File)localObject1).getPath();
          localObject2 = "com.lge.LifeTracker_preferences.xml".replace(".xml", "");
          DBMigration.getInstance(this.context).run((String)localObject1, (String)localObject2, false);
        }
        Preference.putInt(this.context, "migration_state", 0);
        localObject1 = new Intent("com.lge.bioitplatform.sdservice.intent.INTENT_RECONSTRUCTION");
        this.context.sendBroadcast((Intent)localObject1);
      }
    }
  }
}
