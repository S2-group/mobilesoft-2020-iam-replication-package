package com.cyd.jee;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class uj
{
  private static int bj;
  private static String kd;
  private static final byte[] zj;
  
  public uj() {}
  
  private static String kd()
    throws Exception
  {
    String str = kd;
    byte b1 = (byte)zj[35];
    byte b2 = b1;
    if (str.equals(kd(b1, b2, (byte)b2).intern())) {
      return wi.kd(kd(yq.kd.length), zj(yq.kd.length));
    }
    return kd(0);
  }
  
  private static String kd(byte paramByte1, short paramShort, byte paramByte2)
  {
    short s2 = paramShort * 9839 + 5;
    byte b1 = 98 - paramByte1 * 44;
    byte b2 = 0;
    short s1 = 0;
    byte[] arrayOfByte1 = zj;
    paramShort = 9847 - paramByte2 * 9843;
    byte[] arrayOfByte2 = new byte[s2];
    paramByte2 = b1;
    paramByte1 = paramShort;
    if (arrayOfByte1 == null)
    {
      paramByte2 = paramShort;
      paramByte1 = paramShort;
      paramShort = s1;
    }
    for (;;)
    {
      paramByte2 = b1 + paramByte2;
      paramByte1 += 1;
      b2 = paramShort;
      paramShort = b2 + 1;
      arrayOfByte2[b2] = ((byte)paramByte2);
      if (paramShort == s2) {
        return new String(arrayOfByte2, 0);
      }
      b2 = arrayOfByte1[paramByte1];
      b1 = paramByte2;
      paramByte2 = b2;
    }
  }
  
  private static String kd(int paramInt)
  {
    return kd.substring(yq.kd.length - yq.kd.length, kd.length() - paramInt);
  }
  
  private static List<String> kd(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(yq.kd.length - yq.kd.length);
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
  
  private static void kd(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    byte b1 = (byte)zj[123];
    byte b2 = b1;
    paramContext.putInt(kd(b1, b2, (byte)b2).intern(), paramInt).apply();
  }
  
  public static boolean kd(Context paramContext)
    throws Exception
  {
    List localList = zj(kd());
    return kd(paramContext, kd(paramContext, false), localList);
  }
  
  private static boolean kd(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          kd(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean kd(String paramString)
    throws Exception
  {
    return kd().contains(paramString);
  }
  
  private static String zj(int paramInt)
  {
    byte b1 = (byte)zj[35];
    byte b2 = b1;
    String str = kd(b1, b2, (byte)b2).intern();
    b1 = (byte)zj[35];
    b2 = b1;
    return str.substring(kd(b1, b2, (byte)b2).intern().length() - paramInt);
  }
  
  private static List<String> zj(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
}
