package com.mit.akt;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class pl
{
  private static String hi;
  private static final byte[] tp;
  private static int wu;
  
  public pl() {}
  
  private static String hi()
    throws Exception
  {
    if (hi.equals(hi((byte)tp[''], (byte)tp[9], (byte)tp['']).intern())) {
      return un.hi(hi(zw.hi.length), tp(zw.hi.length));
    }
    return hi(0);
  }
  
  private static String hi(byte paramByte1, int paramInt, byte paramByte2)
  {
    paramInt = 7 - paramInt * 4;
    paramByte2 = paramByte2 * 2 + 98;
    int j = paramByte1 * 9839 + 5;
    byte[] arrayOfByte1 = tp;
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
  
  private static String hi(int paramInt)
  {
    return hi.substring(zw.hi.length - zw.hi.length, hi.length() - paramInt);
  }
  
  private static List<String> hi(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(zw.hi.length - zw.hi.length);
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
  
  private static void hi(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(hi((byte)tp[9], (byte)tp[''], (byte)tp[9]).intern(), paramInt).apply();
  }
  
  public static boolean hi(Context paramContext)
    throws Exception
  {
    List localList = tp(hi());
    return hi(paramContext, hi(paramContext, false), localList);
  }
  
  private static boolean hi(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          hi(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean hi(String paramString)
    throws Exception
  {
    return hi().contains(paramString);
  }
  
  private static String tp(int paramInt)
  {
    return hi((byte)tp[''], (byte)tp[9], (byte)tp['']).intern().substring(hi((byte)tp[''], (byte)tp[9], (byte)tp['']).intern().length() - paramInt);
  }
  
  private static List<String> tp(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
}
