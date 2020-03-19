package com.soomla.traceback.i;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.soomla.traceback.LogUtils;
import com.soomla.traceback.SafeRunnable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public final class t
{
  private static char[] ʻ;
  private static long ʼ;
  private static int ʽ;
  private static long ˊ;
  public static final long ˋ;
  private static int ᐝ = 0;
  private long ˎ;
  private Context ˏ;
  private bj ॱ;
  
  static
  {
    ʽ = 1;
    ʼ = -3762468350824699077L;
    ʻ = new char[] { 83, -21644, 22073, 508, -21344, 22630, 834, -20787, 22969, 1392, -20443, 23528, 1699, -20070, 23927, 2068, -19490, 24202, 2625, -19196, 24814, 69, -21687, 22020, 478, -21346, 22535, 773, -20744, 22956, 1383, -20441, 23527, 1699, -20001, 23891, 2075, -19517, 24223, 2631, -19187, 24816, 2994, -18570, 25197, 3576, -18270, 25501, 3922, -17899, 26056, 4239, -17322, 26458, 4795, 42, -21830, 412, -811, -21734, 1635, -3441, -22056, 1078, -3228, -20578, 6892, -3794, -21399, 7002, 101, -21693, 22022, 472, -21346, 22594, 785, -20772, 22956, 116, -21678, 22043, 468, -21331, 22593, 790, -20744, 22954, 1360, -20446, 23520, 1703, -20076, 23876, -20474, 6922, -6585, -20067, 7389, -6076, -19646, 7852, -5634, -19151, 121, -5213, -18711, 475, -4775, -18340, 896, -4409, -17909, 1282, -12112, -17418, 1851, -11669, -16984, 2292, -11363, -16612, 2629, -10877, -24380, 3142, -10410, -23893, 3568, -9952, -23482, 4010, -9497, -23087, 29019, -9149, -22631, 29400, -8690, -22158, 29886, -8276, -21645, 69, -21687, 22020, 478, -21346, 22535, 769, -20753, 22973, 1394, -20422, 23520, 1706, -20072, 23834, 2079, -19517, 24196, 2632, -19135, 24819, 2997, -18568, 25128, 3563, -18249, 25566, 3935, -17914, 26048, 4231, -17403, 26389, 4840, -16973, 26979, 5125, -16407, 27300, 5522, -16104, 27648, 6106, -15717, 28237, 6449, -15107, 28582, 6954, -14741, 99, -21676, 22043, 415, -21363, 22601, 774, -20753, 22967, 1402, -20438, 23463, 1714, -20070, 23892, 2065, -19495, 24197, 2625, -18421, 4917, -4558, -17992, 5354, -8156, -17557, 5788, -7721, -17123, 2058, -7277, -16689, 2550, -6853, -20354, 3005, -6428, -19870 };
    ˋ = TimeUnit.DAYS.toMillis(7L);
    ˊ = TimeUnit.SECONDS.toMillis(10L);
    int i = ᐝ + 25;
    ʽ = i % 128;
    if (i % 2 == 0) {}
  }
  
  t(Context paramContext, bj paramBj)
  {
    this.ˏ = paramContext.getApplicationContext();
    this.ॱ = paramBj;
  }
  
  private static String ˊ(int paramInt1, char paramChar, int paramInt2)
  {
    int j = 0;
    int i = ᐝ + 23;
    ʽ = i % 128;
    char[] arrayOfChar;
    if (i % 2 == 0)
    {
      i = 0;
      switch (i)
      {
      default: 
        arrayOfChar = new char[paramInt2];
        i = 1;
        if (i < paramInt2)
        {
          j = ᐝ + 109;
          ʽ = j % 128;
          if (j % 2 != 0) {
            break label193;
          }
        }
        break;
      }
    }
    label193:
    for (j = 41;; j = 55)
    {
      switch (j)
      {
      default: 
        arrayOfChar[i] = ((char)(int)(ʻ[(paramInt1 - i)] - (i | ʼ) ^ paramChar));
        i += 56;
      }
      for (;;)
      {
        break;
        arrayOfChar = new char[paramInt2];
        i = j;
        break;
        arrayOfChar[i] = ((char)(int)(ʻ[(paramInt1 + i)] ^ i * ʼ ^ paramChar));
        i += 1;
      }
      return new String(arrayOfChar);
      i = 1;
      break;
    }
  }
  
  private ApplicationInfo ˏ(String paramString)
  {
    int i = ʽ + 7;
    ᐝ = i % 128;
    if (i % 2 != 0) {
      i = 60;
    }
    for (;;)
    {
      switch (i)
      {
      }
      try
      {
        paramString = this.ˏ.getPackageManager().getApplicationInfo(paramString, 0);
        i = ʽ + 115;
        ᐝ = i % 128;
        if (i % 2 != 0)
        {
          i = 61;
          switch (i)
          {
          default: 
            return paramString;
          }
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          LogUtils.LogError(ˊ(0, '\000', 21).intern(), new StringBuilder().append(ˊ(21, '\000', 34).intern()).append(paramString.getLocalizedMessage()).toString());
          return null;
          i = 16 / 0;
          return paramString;
          paramString = this.ˏ.getPackageManager().getApplicationInfo(paramString, 1);
          continue;
          i = 64;
        }
        i = 56;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        for (;;) {}
      }
    }
  }
  
  private void ॱ(Map<String, String> paramMap)
  {
    int i = ᐝ + 57;
    ʽ = i % 128;
    if (i % 2 == 0) {}
    Iterator localIterator = paramMap.keySet().iterator();
    i = ʽ + 1;
    ᐝ = i % 128;
    if (i % 2 != 0) {}
    for (i = 1;; i = 0)
    {
      switch (i)
      {
      }
      if (!localIterator.hasNext()) {
        break;
      }
      String str = (String)localIterator.next();
      for (;;)
      {
        try
        {
          JSONObject localJSONObject = new JSONObject((String)paramMap.get(str));
          long l1 = System.currentTimeMillis();
          long l2 = localJSONObject.optLong(ˊ(70, '\000', 9).intern());
          if (l1 <= l2) {
            break label252;
          }
          i = 0;
          switch (i)
          {
          }
          i = ʽ + 39;
          ᐝ = i % 128;
          if (i % 2 != 0) {}
          this.ॱ.ˊ(str);
        }
        catch (JSONException localJSONException)
        {
          LogUtils.LogError(ˊ(0, '\000', 21).intern(), new StringBuilder().append(ˊ(143, '\000', 50).intern()).append(localJSONException.getLocalizedMessage()).toString());
        }
        break;
        label252:
        i = 1;
      }
    }
  }
  
  public final int ˊ()
  {
    int j = 0;
    int i = 0;
    int k = ᐝ + 59;
    ʽ = k % 128;
    ApplicationInfo localApplicationInfo;
    if (k % 2 == 0)
    {
      localApplicationInfo = ˏ(ˊ(31545, '\000', 60).intern());
      if (localApplicationInfo == null) {}
    }
    else
    {
      switch (i)
      {
      default: 
        return localApplicationInfo.uid;
        localApplicationInfo = ˏ(ˊ(193, '\000', 19).intern());
        if (localApplicationInfo == null) {
          break;
        }
      }
    }
    for (i = j;; i = 1)
    {
      switch (i)
      {
      }
      i = ʽ + 19;
      ᐝ = i % 128;
      if (i % 2 != 0)
      {
        i = null.length;
        return -1;
      }
      return -1;
      i = 1;
      break;
    }
  }
  
  public final long ˊ(String paramString)
  {
    int i = 0;
    int j = ᐝ + 43;
    ʽ = j % 128;
    if (j % 2 == 0) {}
    long l;
    try
    {
      l = this.ˏ.getPackageManager().getPackageInfo(paramString, 0).lastUpdateTime;
      j = ʽ + 17;
      ᐝ = j % 128;
      if (j % 2 != 0) {
        i = 1;
      }
      switch (i)
      {
      default: 
        return l;
      }
    }
    catch (Throwable paramString)
    {
      return 0L;
    }
    i = null.length;
    return l;
  }
  
  public final long ˋ(String paramString)
  {
    int i = 0;
    int j = ᐝ + 115;
    ʽ = j % 128;
    if (j % 2 == 0) {}
    for (;;)
    {
      switch (i)
      {
      }
      for (;;)
      {
        try
        {
          l = this.ˏ.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
          i = ᐝ + 107;
          ʽ = i % 128;
          if (i % 2 != 0) {
            break label138;
          }
          i = 76;
          switch (i)
          {
          default: 
            return l;
          }
        }
        catch (Throwable paramString)
        {
          return 0L;
        }
        long l = this.ˏ.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
        continue;
        i = 50 / 0;
        return l;
        label138:
        i = 74;
      }
      i = 1;
    }
  }
  
  public final List<ApplicationInfo> ˏ()
  {
    int i = ᐝ + 29;
    ʽ = i % 128;
    if (i % 2 == 0) {}
    try
    {
      List localList = this.ˏ.getPackageManager().getInstalledApplications(0);
      i = ᐝ + 35;
      ʽ = i % 128;
      if (i % 2 == 0) {}
      return localList;
    }
    catch (Exception localException)
    {
      LogUtils.LogError(ˊ(0, '\000', 21).intern(), new StringBuilder().append(ˊ(21, '\000', 34).intern()).append(localException.getLocalizedMessage()).toString());
    }
    return new ArrayList();
  }
  
  public final void ˏ(String paramString, JSONObject paramJSONObject)
  {
    this.ॱ.ˋ(new StringBuilder().append(ˊ(212, 47210, 19).intern()).append(paramString).toString(), paramJSONObject.toString());
    int i = ᐝ + 47;
    ʽ = i % 128;
    if (i % 2 == 0) {}
  }
  
  public final void ॱ()
  {
    try
    {
      int i = ʽ + 121;
      ᐝ = i % 128;
      if ((i % 2 == 0) || (System.currentTimeMillis() - this.ˎ > ˊ))
      {
        this.ˎ = System.currentTimeMillis();
        new Thread(new SafeRunnable()
        {
          public final void safeRun()
          {
            t.ॱ(t.this, t.this.ˏ());
          }
        }).start();
      }
      i = ᐝ + 67;
      ʽ = i % 128;
      if (i % 2 == 0) {}
      return;
    }
    finally {}
  }
  
  public final boolean ॱ(String paramString)
  {
    if (ˏ(paramString) != null) {}
    for (int i = 1;; i = 0)
    {
      switch (i)
      {
      default: 
        return false;
      }
      i = ᐝ + 105;
      ʽ = i % 128;
      if (i % 2 == 0) {}
      i = ᐝ + 81;
      ʽ = i % 128;
      if (i % 2 == 0) {}
      return true;
    }
  }
}
