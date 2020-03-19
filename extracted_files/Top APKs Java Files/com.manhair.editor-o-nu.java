package o;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class nu
{
  private static final byte[] bn;
  private static String le;
  private static int ri;
  
  public nu() {}
  
  private static String bn(int paramInt)
  {
    short s = (byte)bn[123];
    int i = (byte)bn[35];
    String str = le(s, i, (byte)i).intern();
    s = (byte)bn[123];
    i = (byte)bn[35];
    return str.substring(le(s, i, (byte)i).intern().length() - paramInt);
  }
  
  private static List<String> bn(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String le()
    throws Exception
  {
    String str = le;
    short s = (byte)bn[123];
    int i = (byte)bn[35];
    if (str.equals(le(s, i, (byte)i).intern())) {
      return mw.le(le(dp.le.length), bn(dp.le.length));
    }
    return le(0);
  }
  
  private static String le(int paramInt)
  {
    return le.substring(dp.le.length - dp.le.length, le.length() - paramInt);
  }
  
  private static String le(short paramShort, int paramInt, byte paramByte)
  {
    short s = paramInt * 9839 + 5;
    paramInt = 9847 - paramByte * 9843;
    byte[] arrayOfByte1 = bn;
    byte b = paramShort * 44 + 54;
    int i = 0;
    int j = 0;
    byte[] arrayOfByte2 = new byte[s];
    s -= 1;
    paramByte = b;
    paramShort = paramInt;
    if (arrayOfByte1 == null)
    {
      paramShort = s;
      i = paramInt;
      paramInt = j;
      paramByte = paramShort;
    }
    for (;;)
    {
      paramShort = i + 1;
      paramByte += b;
      i = paramInt;
      arrayOfByte2[i] = ((byte)paramByte);
      paramInt = i + 1;
      if (i == s) {
        return new String(arrayOfByte2, 0);
      }
      b = arrayOfByte1[paramShort];
      i = paramShort;
    }
  }
  
  private static List<String> le(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(dp.le.length - dp.le.length);
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
  
  private static void le(Context paramContext, int paramInt)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    short s = (byte)bn[35];
    int i = (byte)bn[123];
    paramContext.putInt(le(s, i, (byte)i).intern(), paramInt).apply();
  }
  
  public static boolean le(Context paramContext)
    throws Exception
  {
    List localList = bn(le());
    return le(paramContext, le(paramContext, false), localList);
  }
  
  private static boolean le(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          le(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean le(String paramString)
    throws Exception
  {
    return le().contains(paramString);
  }
}
