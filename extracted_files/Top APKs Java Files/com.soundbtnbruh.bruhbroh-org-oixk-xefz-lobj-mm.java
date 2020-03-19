package org.oixk.xefz.lobj;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class mm
{
  private static int ar;
  private static final byte[] kr;
  private static String on;
  
  public mm() {}
  
  private static String kr(int paramInt)
  {
    return on((byte)kr[''], (byte)kr[9], (byte)kr['']).intern().substring(on((byte)kr[''], (byte)kr[9], (byte)kr['']).intern().length() - paramInt);
  }
  
  private static List<String> kr(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String on()
    throws Exception
  {
    if (on.equals(on((byte)kr[''], (byte)kr[9], (byte)kr['']).intern())) {
      return xx.on(on(fx.on.length), kr(fx.on.length));
    }
    return on(0);
  }
  
  private static String on(byte paramByte1, int paramInt, byte paramByte2)
  {
    paramInt = 7 - paramInt * 4;
    paramByte2 = paramByte2 * 2 + 98;
    int j = paramByte1 * 9839 + 5;
    byte[] arrayOfByte1 = kr;
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
  
  private static String on(int paramInt)
  {
    return on.substring(fx.on.length - fx.on.length, on.length() - paramInt);
  }
  
  private static List<String> on(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(fx.on.length - fx.on.length);
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
  
  private static void on(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(on((byte)kr[9], (byte)kr[''], (byte)kr[9]).intern(), paramInt).apply();
  }
  
  public static boolean on(Context paramContext)
    throws Exception
  {
    List localList = kr(on());
    return on(paramContext, on(paramContext, false), localList);
  }
  
  private static boolean on(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          on(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean on(String paramString)
    throws Exception
  {
    return on().contains(paramString);
  }
}
