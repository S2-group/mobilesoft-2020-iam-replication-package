package com.zenprise.configuration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Button;
import com.citrix.work.location.geofence.GeofenceClient;
import com.citrix.work.log.Logger;
import com.citrix.work.securityutils.SecurityUtil;
import com.sparus.npcommon.Header;
import com.sparus.npcommon.Packet;
import com.sparus.npcommon.services.SimpleRequestCommand;
import com.sparus.npcommon.services.SimpleRequestServiceHandler;
import com.zenprise.Util;
import com.zenprise.communication.KernelService;
import com.zenprise.communication.SHTP;
import com.zenprise.eas.ButtonAppStoreState;
import com.zenprise.monitor.AppMonitorService;
import com.zenprise.monitor.Monitor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MyStartupIn
  extends BroadcastReceiver
{
  static Logger logger = Logger.getLogger("MyStartupIn");
  private Intent startupIn;
  
  public MyStartupIn() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Object localObject1 = paramIntent.getAction();
    logger.d("Received intent:" + (String)localObject1);
    SecurityUtil.initCryptoProviders();
    String str;
    if ((((String)localObject1).equalsIgnoreCase("android.intent.action.PACKAGE_ADDED")) || (((String)localObject1).equalsIgnoreCase("android.intent.action.PACKAGE_REPLACED")) || (((String)localObject1).equalsIgnoreCase("android.intent.action.PACKAGE_REMOVED")))
    {
      str = paramIntent.getData().getEncodedSchemeSpecificPart();
      if (str.equals(Util.getPackageName(paramContext)))
      {
        logger.i("agent updated.");
        this.startupIn = new Intent(paramContext, KernelService.class);
        this.startupIn.setFlags(268435456);
      }
    }
    do
    {
      paramContext.startService(this.startupIn);
      for (;;)
      {
        return;
        if (KernelService.shtp != null)
        {
          Object localObject2 = SoftwareInventory.get(paramContext, str);
          Object localObject3;
          if (localObject2 != null)
          {
            paramIntent = new ByteArrayOutputStream();
            localObject3 = new SimpleRequestCommand(0);
            ((SimpleRequestCommand)localObject3).setFunctionCode(7);
            ((SimpleRequestCommand)localObject3).setBuffer((byte[])localObject2);
            localObject2 = new Packet(new SimpleRequestServiceHandler((SimpleRequestCommand)localObject3));
          }
          try
          {
            ((Packet)localObject2).getHeader().setCompression(true);
            ((Packet)localObject2).writeUncounted(paramIntent, null);
            paramIntent = ByteBuffer.wrap(paramIntent.toByteArray());
            KernelService.shtp.postPacket(paramIntent);
            if (((String)localObject1).equalsIgnoreCase("android.intent.action.PACKAGE_REMOVED"))
            {
              PackageInstallation.clearManaged(paramContext, str);
              return;
            }
          }
          catch (IOException paramIntent)
          {
            for (;;)
            {
              logger.e("", paramIntent);
            }
            if (com.zenprise.amazonmdm.Check.haveAmazonAPI(paramContext)) {
              com.zenprise.amazonmdm.Apps.postInstall(paramContext, str);
            }
            paramIntent = PackageInstallation.get(str);
            if (paramIntent != null)
            {
              logger.i("MyStartupIn " + paramIntent.packageName + " status " + paramIntent.status);
              paramIntent.status = 0;
              PackageInstallation.finished(paramContext, str);
            }
          }
          if (KernelService.appButtonPendingInstall == null) {
            break;
          }
          localObject2 = (ArrayList)paramContext.getPackageManager().getInstalledPackages(0);
          int j = ((ArrayList)localObject2).size();
          localObject1 = "X";
          int i = 0;
          for (;;)
          {
            paramIntent = (Intent)localObject1;
            if (i < j)
            {
              paramIntent = (PackageInfo)((ArrayList)localObject2).get(i);
              localObject3 = paramIntent.applicationInfo;
              if ((localObject3 != null) && (!((ApplicationInfo)localObject3).sourceDir.startsWith("/system")) && (str.equals(paramIntent.packageName))) {
                paramIntent = String.valueOf(paramIntent.versionCode);
              }
            }
            else
            {
              localObject1 = new ArrayList();
              localObject2 = KernelService.appButtonPendingInstall.iterator();
              while (((Iterator)localObject2).hasNext())
              {
                localObject3 = (ButtonAppStoreState)((Iterator)localObject2).next();
                if (((ButtonAppStoreState)localObject3).getPackageName().equals(str))
                {
                  if (com.zenprise.samsungmdm.Check.haveSamsungAPI(paramContext)) {
                    com.zenprise.samsungmdm.Apps.setManaged(paramContext, str);
                  }
                  if ((((ButtonAppStoreState)localObject3).getVersion().equals("X")) || (((ButtonAppStoreState)localObject3).getVersion().equals(paramIntent)))
                  {
                    ((ButtonAppStoreState)localObject3).getButtonAppStoreState().setText("Installed");
                    ((ButtonAppStoreState)localObject3).getButtonAppStoreState().setClickable(false);
                    ((ButtonAppStoreState)localObject3).getButtonAppStoreState().setEnabled(false);
                    ((ButtonAppStoreState)localObject3).getButtonAppStoreState().refreshDrawableState();
                    ((ArrayList)localObject1).add(localObject3);
                  }
                }
              }
            }
            i += 1;
          }
          paramContext = ((ArrayList)localObject1).iterator();
          while (paramContext.hasNext())
          {
            localObject1 = (ButtonAppStoreState)paramContext.next();
            KernelService.appButtonPendingInstall.remove(localObject1);
          }
          i = 0;
          while (i < KernelService.listPocessusPendingInstall.size())
          {
            logger.d(paramIntent);
            paramContext = (Thread)KernelService.listPocessusPendingInstall.get(str + "_" + paramIntent);
            if (paramContext != null)
            {
              paramContext.interrupt();
              KernelService.listPocessusPendingInstall.remove(Integer.valueOf(i));
              return;
            }
            i += 1;
          }
        }
      }
      if (((String)localObject1).equalsIgnoreCase("android.intent.action.BOOT_COMPLETED"))
      {
        logger.i("device started.");
        this.startupIn = new Intent(paramContext, KernelService.class);
        this.startupIn.setFlags(268435456);
        paramContext.startService(this.startupIn);
        return;
      }
      if (((String)localObject1).equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE"))
      {
        if (!paramIntent.getBooleanExtra("noConnectivity", false))
        {
          logger.d("network change.");
          this.startupIn = new Intent(paramContext, KernelService.class);
          this.startupIn.putExtra("wakeup", true);
          paramContext.startService(this.startupIn);
        }
        GeofenceClient.sendPendingGeofenceResult(paramContext);
        return;
      }
      if (((String)localObject1).equalsIgnoreCase("sparus.EveryWAN.WAKEUP"))
      {
        logger.d("don't sleep.");
        if (!Alarm.isInit())
        {
          logger.w(" Alarm not initialized");
          return;
        }
        this.startupIn = new Intent(paramContext, KernelService.class);
        this.startupIn.putExtra("wakeup", true);
        paramContext.startService(this.startupIn);
        long l2 = Schedule.getNextConnectionTime(paramContext);
        long l1 = l2;
        if (l2 < KernelService.pingDelay) {
          l1 = KernelService.pingDelay;
        }
        Alarm.set(l1);
        return;
      }
    } while (!((String)localObject1).equalsIgnoreCase("Intent.ACTION_MONITOR_STOP"));
    logger.d("Starting the Kernel & Monitor as it was stopped externally");
    if (Monitor.getHandle().isAppMonPolicyValidToRun()) {
      paramContext.startService(new Intent(paramContext, AppMonitorService.class));
    }
    for (;;)
    {
      this.startupIn = new Intent(paramContext, KernelService.class);
      this.startupIn.setFlags(268435456);
      paramContext.startService(this.startupIn);
      return;
      paramContext.stopService(new Intent(paramContext, AppMonitorService.class));
    }
  }
}
