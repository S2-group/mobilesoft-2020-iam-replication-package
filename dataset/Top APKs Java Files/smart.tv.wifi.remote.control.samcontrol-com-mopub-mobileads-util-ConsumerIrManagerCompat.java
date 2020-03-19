package com.mopub.mobileads.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class ConsumerIrManagerCompat
{
  public static final int HTCSUPPORT = 1;
  public static final String PREFERENCE_FILE_NAME = "IR_Temp";
  public static final String PREFERENCE_KEY_FRAME = "IR_FRAME";
  public static final String PREFERENCE_KEY_FREQUENCY = "IR_FREQUENCY";
  protected Context mContext;
  protected int supportedAPIs = 0;
  
  public ConsumerIrManagerCompat(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public static ConsumerIrManagerCompat createInstance(Context paramContext)
  {
    if ((Build.VERSION.SDK_INT <= 19) && (hasPackage("com.htc.cirmodule", paramContext))) {
      return new ConsumerIrManagerHtc(paramContext);
    }
    return new ConsumerIrManagerBase(paramContext);
  }
  
  public static boolean hasPackage(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public abstract UUID cancelCommand();
  
  public abstract UUID discardCommand(UUID paramUUID);
  
  public abstract ConsumerIrManagerCompat.CarrierFrequencyRange[] getCarrierFrequencies();
  
  public int getSupportedAPIs()
  {
    return this.supportedAPIs;
  }
  
  public abstract boolean hasIrEmitter();
  
  public abstract boolean isStarted();
  
  public abstract UUID learnIRCmd(int paramInt);
  
  public abstract void start();
  
  public abstract void stop();
  
  public abstract void transmit(int paramInt, int[] paramArrayOfInt);
}
