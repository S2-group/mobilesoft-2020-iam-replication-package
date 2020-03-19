package com.xqk.ajk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class iw
{
  private static final byte[] el;
  private static int pj;
  private static String yp;
  
  public iw() {}
  
  private static String el(int paramInt)
  {
    short s = (byte)el[125];
    int i = (byte)el[9];
    String str = yp(s, i, (byte)i).intern();
    s = (byte)el[125];
    i = (byte)el[9];
    return str.substring(yp(s, i, (byte)i).intern().length() - paramInt);
  }
  
  private static List<String> el(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String yp()
    throws Exception
  {
    String str = yp;
    short s = (byte)el[125];
    int i = (byte)el[9];
    if (str.equals(yp(s, i, (byte)i).intern())) {
      return nr.yp(yp(ad.yp.length), el(ad.yp.length));
    }
    return yp(0);
  }
  
  private static String yp(int paramInt)
  {
    return yp.substring(ad.yp.length - ad.yp.length, yp.length() - paramInt);
  }
  
  private static String yp(short paramShort, int paramInt1, int paramInt2)
  {
    int j = -1;
    paramInt2 = 8 - paramInt2 * 4;
    paramShort = paramShort * 9839 + 5;
    int i = 100 - paramInt1 * 2;
    byte[] arrayOfByte1 = el;
    byte[] arrayOfByte2 = new byte[paramShort];
    int m = paramShort - 1;
    paramInt1 = j;
    paramShort = paramInt2;
    int k;
    if (arrayOfByte1 == null)
    {
      i = paramInt2;
      k = m;
      paramShort = paramInt2;
      paramInt1 = j;
    }
    for (paramInt2 = k;; paramInt2 = arrayOfByte1[paramShort])
    {
      i += paramInt2;
      paramShort += 1;
      paramInt1 += 1;
      arrayOfByte2[paramInt1] = ((byte)i);
      if (paramInt1 == m) {
        return new String(arrayOfByte2, 0);
      }
    }
  }
  
  private static List<String> yp(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(ad.yp.length - ad.yp.length);
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
  
  private static void yp(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    short s = (byte)el[9];
    int i = (byte)el[125];
    paramContext.putInt(yp(s, i, (byte)i).intern(), paramInt).apply();
  }
  
  public static boolean yp(Context paramContext)
    throws Exception
  {
    List localList = el(yp());
    return yp(paramContext, yp(paramContext, false), localList);
  }
  
  private static boolean yp(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          yp(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean yp(String paramString)
    throws Exception
  {
    return yp().contains(paramString);
  }
}
