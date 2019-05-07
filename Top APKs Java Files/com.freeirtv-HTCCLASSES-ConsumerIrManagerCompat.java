package HTCCLASSES;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;
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
  protected TextView textView;
  
  public ConsumerIrManagerCompat(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public static ConsumerIrManagerCompat createInstance(Context paramContext)
  {
    if (hasPackage("com.htc.cirmodule", paramContext)) {
      return new ConsumerIrManagerHtc(paramContext);
    }
    return new ConsumerIrManagerBase(paramContext);
  }
  
  public static boolean hasPackage(String paramString, Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public abstract UUID cancelCommand();
  
  public abstract UUID discardCommand(UUID paramUUID);
  
  public abstract CarrierFrequencyRange[] getCarrierFrequencies();
  
  public int getSupportedAPIs()
  {
    return this.supportedAPIs;
  }
  
  public abstract boolean hasIrEmitter();
  
  public abstract boolean isStarted();
  
  public abstract UUID learnIRCmd(int paramInt);
  
  public void setTextView(TextView paramTextView)
  {
    this.textView = paramTextView;
  }
  
  public abstract void start();
  
  public abstract void stop();
  
  public abstract void transmit(int paramInt, int[] paramArrayOfInt);
  
  public final class CarrierFrequencyRange
  {
    private int maxfreq;
    private int minfreq;
    
    public CarrierFrequencyRange(int paramInt1, int paramInt2)
    {
      this.minfreq = paramInt1;
      this.maxfreq = paramInt2;
    }
    
    public int getMaxFrequency()
    {
      return this.maxfreq;
    }
    
    public int getMinFrequency()
    {
      return this.minfreq;
    }
  }
}
