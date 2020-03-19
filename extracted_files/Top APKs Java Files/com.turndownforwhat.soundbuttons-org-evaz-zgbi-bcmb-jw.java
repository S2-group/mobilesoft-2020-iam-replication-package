package org.evaz.zgbi.bcmb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class jw
{
  private static int br;
  private static final byte[] dm;
  private static String ri;
  
  public jw() {}
  
  private static String dm(int paramInt)
  {
    return ri((byte)dm[''], (byte)dm[9], (byte)dm['']).intern().substring(ri((byte)dm[''], (byte)dm[9], (byte)dm['']).intern().length() - paramInt);
  }
  
  private static List<String> dm(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String ri()
    throws Exception
  {
    if (ri.equals(ri((byte)dm[''], (byte)dm[9], (byte)dm['']).intern())) {
      return kw.ri(ri(lh.ri.length), dm(lh.ri.length));
    }
    return ri(0);
  }
  
  private static String ri(byte paramByte1, int paramInt, byte paramByte2)
  {
    paramInt = 7 - paramInt * 4;
    paramByte2 = paramByte2 * 2 + 98;
    int j = paramByte1 * 9839 + 5;
    byte[] arrayOfByte1 = dm;
    paramByte1 = 0;
    byte b = 0;
    byte[] arrayOfByte2 = new byte[j];
    int i = paramInt;
    if (arrayOfByte1 == null)
    {
      paramByte2 = paramInt;
      i = j;
      paramByte1 = b;
    }
    for (;;)
    {
      paramByte2 += -i;
      i = paramInt;
      paramInt = paramByte1 + 1;
      arrayOfByte2[paramByte1] = ((byte)paramByte2);
      if (paramInt == j) {
        return new String(arrayOfByte2, 0);
      }
      b = i + 1;
      i = arrayOfByte1[b];
      paramByte1 = paramInt;
      paramInt = b;
    }
  }
  
  private static String ri(int paramInt)
  {
    return ri.substring(lh.ri.length - lh.ri.length, ri.length() - paramInt);
  }
  
  private static List<String> ri(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(lh.ri.length - lh.ri.length);
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
  
  private static void ri(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(ri((byte)dm[9], (byte)dm[''], (byte)dm[9]).intern(), paramInt).apply();
  }
  
  public static boolean ri(Context paramContext)
    throws Exception
  {
    List localList = dm(ri());
    return ri(paramContext, ri(paramContext, false), localList);
  }
  
  private static boolean ri(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          ri(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean ri(String paramString)
    throws Exception
  {
    return ri().contains(paramString);
  }
}
