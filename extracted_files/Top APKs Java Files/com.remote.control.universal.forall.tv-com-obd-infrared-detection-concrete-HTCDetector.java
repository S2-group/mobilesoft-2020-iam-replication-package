package com.obd.infrared.detection.concrete;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.obd.infrared.detection.IDetector;
import com.obd.infrared.detection.InfraRedDetector.DetectorParams;
import com.obd.infrared.transmit.TransmitterType;
import java.util.Iterator;
import java.util.List;

public class HTCDetector
  implements IDetector
{
  public HTCDetector() {}
  
  private boolean hasPackage(String paramString, Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.contains(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public TransmitterType getTransmitterType()
  {
    return TransmitterType.HTC;
  }
  
  public boolean hasTransmitter(InfraRedDetector.DetectorParams paramDetectorParams)
  {
    try
    {
      boolean bool = hasPackage("com.htc.cirmodule", paramDetectorParams.context);
      return bool;
    }
    catch (Exception paramDetectorParams) {}
    return false;
  }
}
