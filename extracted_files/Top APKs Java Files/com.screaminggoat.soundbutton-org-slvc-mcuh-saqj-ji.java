package org.slvc.mcuh.saqj;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class ji
{
  private static final byte[] dd;
  private static int hz;
  private static String je;
  
  public ji() {}
  
  private static String dd(int paramInt)
  {
    short s = (byte)dd[35];
    String str = je(s, (byte)s, (byte)dd[123]).intern();
    s = (byte)dd[35];
    return str.substring(je(s, (byte)s, (byte)dd[123]).intern().length() - paramInt);
  }
  
  private static List<String> dd(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String je()
    throws Exception
  {
    String str = je;
    short s = (byte)dd[35];
    if (str.equals(je(s, (byte)s, (byte)dd[123]).intern())) {
      return kw.je(je(ln.je.length), dd(ln.je.length));
    }
    return je(0);
  }
  
  private static String je(int paramInt)
  {
    return je.substring(ln.je.length - ln.je.length, je.length() - paramInt);
  }
  
  private static String je(short paramShort, byte paramByte1, byte paramByte2)
  {
    byte b1 = 98 - paramShort * 44;
    paramShort = 0;
    byte b3 = 0;
    paramByte2 = paramByte2 * 9843 + 4;
    short s = paramByte1 * 9839 + 5;
    byte[] arrayOfByte1 = dd;
    byte[] arrayOfByte2 = new byte[s];
    paramByte1 = paramShort;
    paramShort = paramByte2;
    byte b2;
    if (arrayOfByte1 == null)
    {
      paramShort = s;
      b1 = paramByte2;
      b2 = paramByte2;
      paramByte1 = b3;
      paramByte2 = paramShort;
    }
    for (;;)
    {
      paramShort = b2 + 1;
      b1 = paramByte2 + b1;
      b2 = paramByte1 + 1;
      arrayOfByte2[paramByte1] = ((byte)b1);
      if (b2 == s) {
        return new String(arrayOfByte2, 0);
      }
      paramByte2 = b1;
      b1 = arrayOfByte1[paramShort];
      paramByte1 = b2;
      b2 = paramShort;
    }
  }
  
  private static List<String> je(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(ln.je.length - ln.je.length);
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
  
  private static void je(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    short s = (byte)dd[123];
    paramContext.putInt(je(s, (byte)s, (byte)dd[35]).intern(), paramInt).apply();
  }
  
  public static boolean je(Context paramContext)
    throws Exception
  {
    List localList = dd(je());
    return je(paramContext, je(paramContext, false), localList);
  }
  
  private static boolean je(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          je(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean je(String paramString)
    throws Exception
  {
    return je().contains(paramString);
  }
}
