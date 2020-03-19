package com.pvu.nhg;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class qe
{
  private static final byte[] ns;
  private static int qg;
  private static String xk;
  
  public qe() {}
  
  private static String ns(int paramInt)
  {
    int i = (byte)ns[59];
    byte b = (byte)ns[9];
    String str = xk(i, b, b).intern();
    i = (byte)ns[59];
    b = (byte)ns[9];
    return str.substring(xk(i, b, b).intern().length() - paramInt);
  }
  
  private static List<String> ns(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String xk()
    throws Exception
  {
    String str = xk;
    int i = (byte)ns[59];
    byte b = (byte)ns[9];
    if (str.equals(xk(i, b, b).intern())) {
      return id.xk(xk(lx.xk.length), ns(lx.xk.length));
    }
    return xk(0);
  }
  
  private static String xk(int paramInt)
  {
    return xk.substring(lx.xk.length - lx.xk.length, xk.length() - paramInt);
  }
  
  private static String xk(int paramInt, byte paramByte, short paramShort)
  {
    byte[] arrayOfByte1 = ns;
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
  
  private static List<String> xk(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(lx.xk.length - lx.xk.length);
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
  
  private static void xk(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    int i = (byte)ns[''];
    byte b = (byte)ns[125];
    paramContext.putInt(xk(i, b, b).intern(), paramInt).apply();
  }
  
  public static boolean xk(Context paramContext)
    throws Exception
  {
    List localList = ns(xk());
    return xk(paramContext, xk(paramContext, false), localList);
  }
  
  private static boolean xk(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          xk(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean xk(String paramString)
    throws Exception
  {
    return xk().contains(paramString);
  }
}
