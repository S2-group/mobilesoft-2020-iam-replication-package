package o;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import com.kaspersky.components.statistics.popularity.Wlips;
import com.kms.ksn.locator.ServiceLocator;
import java.util.Iterator;
import java.util.List;

final class ua
  implements Runnable
{
  private static final byte[] 一難 = { 89, 42, -17, 119, -12, 19, -5, -17, 5, 25, 6, 1, -9, -3, -70, 82, -7, -81, 81, -16, 7, -12, -70, 53, -13, -5, 5, 1, 25, 6, 1, -9, -3, -70, 82, -7, -81, 81, -16, 7, -12, -70, 53, -13, -5, 5, 1, -53, 11, -15, 31, 43, -12, 12, -5, -8, -7, -70, 18, -8, 1, -19, 51, 23, 3, -20, 17, -13, 8, -15, 1, -6, -3, -26, 27, 3, -5, -40, 43, -2, -5, 1 };
  private static int 論寸口脈與經經脈榮衛度數 = 175;
  
  ua(vl paramVl, Context paramContext, PowerManager.WakeLock paramWakeLock) {}
  
  private static String 難經本義(int paramInt1, int paramInt2, int paramInt3)
  {
    int m = paramInt2 * 5 + 70;
    paramInt1 += 4;
    byte[] arrayOfByte1 = 一難;
    int j = paramInt3 * 7 + 6;
    int i = 0;
    int k = 0;
    byte[] arrayOfByte2 = new byte[j];
    paramInt2 = paramInt1;
    paramInt3 = m;
    if (arrayOfByte1 == null)
    {
      paramInt3 = j;
      i = paramInt1;
      paramInt2 = paramInt1;
      paramInt1 = k;
    }
    for (;;)
    {
      paramInt3 = paramInt3 + i + 2;
      i = paramInt1;
      paramInt1 = i + 1;
      arrayOfByte2[i] = ((byte)paramInt3);
      if (paramInt1 == j) {
        return new String(arrayOfByte2, 0);
      }
      paramInt2 += 1;
      i = arrayOfByte1[paramInt2];
    }
  }
  
  public void run()
  {
    label352:
    for (;;)
    {
      try
      {
        Object localObject1 = tZ.難經本義(this.難經本義);
        Object localObject2 = this.僅輸入原文.getPackageManager();
        Wlips localWlips = new Wlips(ServiceLocator.難經本義().僅輸入原文());
        try
        {
          localObject2 = ((PackageManager)localObject2).getInstalledPackages(0);
        }
        catch (Exception localException)
        {
          tZ.僅輸入原文(this.僅輸入原文, this.難經本義);
          mJ.難經本義(this.難經本義卷上);
          return;
        }
        localObject2 = ((List)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          if (localPackageInfo == null) {
            break label352;
          }
          localException.難經本義(localPackageInfo.applicationInfo.sourceDir);
          break label352;
        }
        localObject1 = ((List)localObject1).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          localObject2 = (ub)((Iterator)localObject1).next();
          localException.難經本義(((ub)localObject2).難經本義(), ((ub)localObject2).僅輸入原文());
          continue;
        }
        if (!localException.難經本義())
        {
          i = -一難[11];
          j = 一難[11];
          String str = 難經本義(i, j, j - 1).intern();
          i = 論寸口脈與經經脈榮衛度數 & 0x14;
          Log.e(str, 難經本義(i, i - 4, -一難[79]).intern());
        }
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        int i;
        int j;
        if (Build.VERSION.SDK_INT == 16)
        {
          i = -一難[11];
          j = 一難[11];
          Log.e(難經本義(i, j, j - 1).intern(), 難經本義(一難[63], 一難[11] - 1, 一難[20]).intern());
        }
        else
        {
          throw localUnsatisfiedLinkError;
        }
      }
      this.難經本義.難經本義(null);
      this.難經本義.僅輸入原文(System.currentTimeMillis());
      this.難經本義.九難();
      tZ.僅輸入原文(this.僅輸入原文, this.難經本義);
      mJ.難經本義(this.難經本義卷上);
      return;
    }
  }
}
