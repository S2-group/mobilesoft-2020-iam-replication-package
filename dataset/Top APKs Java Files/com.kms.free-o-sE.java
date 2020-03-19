package o;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.Looper;
import com.kavsdk.antivirus.MonitorItem;
import com.kavsdk.shared.MountListener;
import com.kavsdk.shared.MountReceiver;
import com.kavsdk.shared.SdkUtils;
import com.kavsdk.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

final class sE
  implements MountListener
{
  private static int 九難 = 226;
  private static final byte[] 論寸口脈平而死者 = { 27, -125, -24, 8, 67, 4, -1, -10, 64, 3, 8, -67, 55, 3, -1, 21, -12, 1, 15, 55, 3, -1, 21, -12, 70, 8, -4, 15, -8, 16, -1, -4, -3, -52, 61, 7, 8, -13, 11, 8, -68, 53, 4, 19, -9, 8, 1, -62, 39, -14, 17, -11, 2, 11, 19, -14, 1, 55, 3, 19, 64, 3, 8, 70, 8, -4, 3, -13, 10, 47, 2, -1, 5, -80, 78, 5, -12, 5, 7, 7, -5, -69, 79, 4, 1, -3, 13, -3, 5, -79 };
  private final String 一難;
  private final Handler 七難 = new Handler(Looper.getMainLooper());
  private AtomicBoolean 三難 = new AtomicBoolean(false);
  private final Context 二難;
  private volatile ta 五難 = null;
  private int 僅輸入原文;
  private final Runnable 八難 = new sF(this);
  private final Object 六難 = new Object();
  private MountReceiver 四難 = null;
  private AtomicBoolean 論太過不及等反常脈象 = new AtomicBoolean(true);
  private final String 論寸口脈與經經脈榮衛度數;
  private boolean 論尺寸為脈之大要會 = false;
  private boolean 論王脈 = false;
  private ExecutorService 論脈之陰陽虛實;
  private volatile boolean 論脈有輕重 = false;
  private boolean 論脈有陰陽之法 = true;
  private final int 難經本義 = 1500;
  private int 難經本義卷上;
  
  public sE(int paramInt1, int paramInt2, String paramString1, String paramString2, Context paramContext, ud paramUd, ug paramUg)
  {
    this.一難 = paramString2;
    this.論寸口脈與經經脈榮衛度數 = paramString1;
    this.二難 = paramContext;
    new Thread(new sH(this, paramInt1, paramInt2, paramUd, paramUg)).start();
  }
  
  private boolean 三難()
  {
    ta localTa = 論尺寸為脈之大要會();
    if (localTa.難經本義() == 0)
    {
      int j = this.僅輸入原文;
      int i;
      if (this.論脈有陰陽之法) {
        i = 1;
      } else {
        i = 0;
      }
      localTa.難經本義(j, i);
      localTa.難經本義(this.難經本義卷上);
      return true;
    }
    return false;
  }
  
  private void 五難()
  {
    if ((this.論王脈) || (!this.論太過不及等反常脈象.get()) || (!this.三難.get())) {
      return;
    }
    new Thread(new sI(this)).start();
  }
  
  private void 四難()
  {
    this.七難.removeCallbacks(this.八難);
    this.論太過不及等反常脈象.set(false);
    論太過不及等反常脈象();
    難經本義(1500L);
  }
  
  private void 論太過不及等反常脈象()
  {
    for (;;)
    {
      try
      {
        if ((this.三難.get()) && (this.論太過不及等反常脈象.get()))
        {
          i = 1;
          if (this.論尺寸為脈之大要會 != i) {
            if (i != 0)
            {
              if (三難())
              {
                this.論脈之陰陽虛實 = Executors.newSingleThreadExecutor();
                this.論尺寸為脈之大要會 = true;
                notifyAll();
              }
            }
            else
            {
              論尺寸為脈之大要會().僅輸入原文();
              this.論脈之陰陽虛實.shutdown();
              this.論尺寸為脈之大要會 = false;
            }
          }
          return;
        }
      }
      finally {}
      int i = 0;
    }
  }
  
  private ta 論尺寸為脈之大要會()
  {
    if (this.論脈有輕重) {
      return this.五難;
    }
    synchronized (this.六難)
    {
      for (;;)
      {
        boolean bool = this.論脈有輕重;
        if (bool) {
          break;
        }
        try
        {
          this.六難.wait();
        }
        catch (InterruptedException localInterruptedException)
        {
          Thread.currentThread().interrupt();
        }
      }
    }
    return this.五難;
  }
  
  private void 論脈有陰陽之法()
  {
    難經本義(1500L);
  }
  
  private static String 難經本義(int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte1 = 論寸口脈平而死者;
    int k = 0;
    int n = 0;
    int m = paramInt2 + 4;
    paramInt2 = 69 - paramInt3;
    int j = paramInt1 * 2 + 47;
    byte[] arrayOfByte2 = new byte[m];
    paramInt3 = j;
    paramInt1 = paramInt2;
    if (arrayOfByte1 == null)
    {
      paramInt3 = m;
      paramInt1 = paramInt2;
      paramInt2 = n;
    }
    for (;;)
    {
      paramInt3 = j + paramInt3 - 2;
      paramInt1 += 1;
      k = paramInt2;
      int i = (byte)paramInt3;
      paramInt2 = k + 1;
      arrayOfByte2[k] = i;
      if (paramInt2 == m) {
        return new String(arrayOfByte2, 0).intern();
      }
      k = arrayOfByte1[paramInt1];
      j = paramInt3;
      paramInt3 = k;
    }
  }
  
  private void 難經本義(int paramInt1, int paramInt2, ud arg3, ug paramUg)
  {
    this.五難 = ta.難經本義(new uG(), this.論寸口脈與經經脈榮衛度數, paramUg, ???, paramInt1, paramInt2, 1);
    if (this.五難 == null) {
      throw new RuntimeException(難經本義(論寸口脈平而死者[38], 論寸口脈平而死者[43] - 1, 論寸口脈平而死者[17] - 1));
    }
    ??? = SdkUtils.getInstalledApplications(this.二難, 0).iterator();
    while (???.hasNext())
    {
      paramUg = (ApplicationInfo)???.next();
      this.五難.難經本義(paramUg.dataDir, 2472);
    }
    ??? = Utils.getStoragePaths(Integer.MAX_VALUE);
    paramInt1 = 0;
    while (paramInt1 < ???.size())
    {
      this.五難.難經本義((String)???.get(paramInt1), 2472);
      paramInt1 += 1;
    }
    ??? = this.五難;
    paramInt1 = 論寸口脈平而死者[17] - 1;
    ???.難經本義(難經本義(paramInt1, paramInt1, -論寸口脈平而死者[44]), 2472);
    ??? = this.五難;
    paramInt1 = 論寸口脈平而死者[17];
    paramInt2 = 論寸口脈平而死者[9];
    ???.難經本義(難經本義(paramInt1 - 1, paramInt2, paramInt2 + 3), 2440);
    this.五難.一難(this.一難);
    this.五難.一難(this.論寸口脈與經經脈榮衛度數);
    ??? = this.五難;
    paramInt1 = 論寸口脈平而死者[17] - 1;
    ???.一難(難經本義(paramInt1, paramInt1, paramInt1 | 0x2D));
    ??? = this.五難;
    paramInt1 = 論寸口脈平而死者[17];
    paramInt2 = 論寸口脈平而死者[17];
    ???.一難(難經本義(paramInt1 - 1, paramInt2, paramInt2 | 0x40));
    ??? = this.五難;
    paramInt1 = 論寸口脈平而死者[17] - 1;
    ???.一難(難經本義(paramInt1, paramInt1, -論寸口脈平而死者[16]));
    ??? = this.五難;
    paramInt1 = 論寸口脈平而死者[17];
    paramInt2 = 論寸口脈平而死者[52];
    ???.一難(難經本義(paramInt1 - 1, paramInt2, paramInt2 | 0x30));
    this.五難.一難(難經本義(論寸口脈平而死者[17] - 1, 論寸口脈平而死者[3], 論寸口脈平而死者[34]));
    this.論脈有輕重 = true;
    synchronized (this.六難)
    {
      this.六難.notifyAll();
      return;
    }
  }
  
  private void 難經本義(long paramLong)
  {
    this.七難.removeCallbacks(this.八難);
    this.七難.postDelayed(this.八難, paramLong);
    this.論王脈 = true;
  }
  
  protected void finalize()
  {
    try
    {
      論寸口脈與經經脈榮衛度數();
      return;
    }
    finally
    {
      super.finalize();
    }
  }
  
  public void mediaChanged(boolean paramBoolean, Intent paramIntent)
  {
    if (!this.三難.get()) {
      return;
    }
    if (!paramBoolean)
    {
      四難();
      return;
    }
    if (paramIntent.getAction().equals(難經本義(25, 論寸口脈平而死者[0], 42)))
    {
      五難();
      return;
    }
    論脈有陰陽之法();
  }
  
  public void 一難()
  {
    if (this.三難.compareAndSet(false, true)) {
      try
      {
        this.四難 = MountReceiver.getInstance();
        this.四難.setAppContext(this.二難);
        this.四難.registerListener(this);
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    論太過不及等反常脈象();
  }
  
  public void 一難(String paramString)
  {
    論尺寸為脈之大要會().一難(paramString);
  }
  
  public boolean 二難()
  {
    try
    {
      for (;;)
      {
        bool = this.論尺寸為脈之大要會;
        if (bool) {
          break;
        }
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      boolean bool = this.論尺寸為脈之大要會;
      return bool;
    }
    finally {}
  }
  
  public List<MonitorItem> 僅輸入原文()
  {
    return 論尺寸為脈之大要會().一難();
  }
  
  public void 僅輸入原文(int paramInt)
  {
    try
    {
      this.難經本義卷上 = paramInt;
      if (this.論尺寸為脈之大要會) {
        論尺寸為脈之大要會().難經本義(paramInt);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void 僅輸入原文(String paramString)
  {
    論尺寸為脈之大要會().僅輸入原文(paramString);
  }
  
  public void 論寸口脈與經經脈榮衛度數()
  {
    if (this.三難.compareAndSet(true, false)) {
      try
      {
        this.四難.unregisterListener(this);
        this.四難 = null;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    論太過不及等反常脈象();
  }
  
  public void 論寸口脈與經經脈榮衛度數(String paramString)
  {
    論尺寸為脈之大要會().論寸口脈與經經脈榮衛度數(paramString);
  }
  
  public ExecutorService 難經本義()
  {
    if ((this.論脈之陰陽虛實 != null) && (!this.論脈之陰陽虛實.isShutdown())) {
      return this.論脈之陰陽虛實;
    }
    return null;
  }
  
  public void 難經本義(int paramInt)
  {
    for (;;)
    {
      try
      {
        this.僅輸入原文 = paramInt;
        if (this.論尺寸為脈之大要會)
        {
          ta localTa = 論尺寸為脈之大要會();
          if (this.論脈有陰陽之法)
          {
            i = 1;
            localTa.難經本義(paramInt, i);
          }
        }
        else
        {
          return;
        }
      }
      finally {}
      int i = 0;
    }
  }
  
  public void 難經本義(String paramString)
  {
    論尺寸為脈之大要會().難經本義(paramString);
  }
  
  public void 難經本義(String paramString, int paramInt)
  {
    論尺寸為脈之大要會().難經本義(paramString, paramInt);
  }
  
  public List<String> 難經本義卷上()
  {
    return 論尺寸為脈之大要會().論寸口脈與經經脈榮衛度數();
  }
  
  public void 難經本義卷上(String paramString)
  {
    論尺寸為脈之大要會().難經本義卷上(paramString);
  }
}
