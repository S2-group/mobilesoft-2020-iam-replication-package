package com.superpro.WJ.WJ.ZC;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.ox.component.Aw.bW;
import com.ox.component.WJ;
import com.superpro.WJ.Ox.gn;
import com.superpro.WJ.Ox.sb;
import com.umeng.statistics.StatisticsUtils;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ZC
{
  private static AtomicBoolean Aw;
  public static final AdError WJ = new AdError(-1, "Unsupported facebook, Probably because facebook is not installed natively.");
  private static ReentrantLock ZC = new ReentrantLock();
  private static long xf;
  
  static
  {
    Aw = new AtomicBoolean(true);
    xf = 0L;
  }
  
  public static boolean Aw()
  {
    return WJ(28800000L);
  }
  
  private static String WJ(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append((char)(paramArrayOfInt[i] - (j - i)));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void WJ()
  {
    Object localObject = WJ.WJ();
    AdSettings.isTestMode((Context)localObject);
    localObject = ((Context)localObject).getSharedPreferences("FBAdPrefs", 0).getString("deviceIdHash", (String)null);
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      bW.ZC("test", "error: set facebook device faild...");
      return;
    }
    AdSettings.addTestDevice((String)localObject);
  }
  
  private static void WJ(boolean paramBoolean)
  {
    gn.WJ().Aw("FU_IFACU", paramBoolean);
  }
  
  public static boolean WJ(long paramLong)
  {
    final long l = System.currentTimeMillis();
    if (l - xf > paramLong) {
      new sb(3)
      {
        public void run()
        {
          if (ZC.ZC().tryLock()) {}
          for (;;)
          {
            try
            {
              Object localObject1 = WJ.WJ().getPackageManager();
              if (localObject1 != null)
              {
                localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
                if (((Iterator)localObject1).hasNext())
                {
                  if (!((PackageInfo)((Iterator)localObject1).next()).packageName.startsWith("com.facebook.")) {
                    continue;
                  }
                  bool = true;
                  ZC.bW().set(bool);
                  if (!ZC.bW().get()) {
                    StatisticsUtils.statisics("ad", "source", new String[] { "error", "unsupported_fb" });
                  }
                  ZC.Aw(l);
                  return;
                }
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              return;
            }
            finally
            {
              ZC.ZC().unlock();
            }
            boolean bool = false;
          }
        }
      }.WJ();
    }
    return Aw.get();
  }
  
  public static boolean WJ(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        str1 = URLDecoder.decode(paramString, "UTF-8");
        paramString = str1;
      }
      catch (Exception localException)
      {
        String str1;
        String str2;
        int j;
        int i;
        for (;;) {}
      }
      if (paramString != null)
      {
        paramString = paramString.split("&");
        if ((paramString != null) && (paramString.length > 0))
        {
          str1 = WJ(new int[] { 125, 123, 115, 100, 119, 104, 112, 101 });
          str2 = WJ(new int[] { 130, 108, 122, 107, 111, 105, 109, 110, 111, 122, 105 });
          j = paramString.length;
          i = 0;
          while (i < j)
          {
            String[] arrayOfString = paramString[i].split("=");
            if ((arrayOfString != null) && (arrayOfString.length == 2) && (TextUtils.equals(str1, arrayOfString[0])) && (TextUtils.equals(str2, arrayOfString[1])))
            {
              WJ(true);
              return true;
            }
            i += 1;
          }
        }
      }
    }
  }
  
  public static boolean xf()
  {
    return gn.WJ().ZC("FU_IFACU", false);
  }
}
