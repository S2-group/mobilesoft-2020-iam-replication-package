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

public final class fr
{
  private static final byte[] ij;
  private static int tg;
  private static String xk;
  
  public fr() {}
  
  private static String ij(int paramInt)
  {
    short s = (byte)ij[123];
    int i = (byte)ij[35];
    String str = xk(s, i, (byte)i).intern();
    s = (byte)ij[123];
    i = (byte)ij[35];
    return str.substring(xk(s, i, (byte)i).intern().length() - paramInt);
  }
  
  private static List<String> ij(String paramString)
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
    short s = (byte)ij[123];
    int i = (byte)ij[35];
    if (str.equals(xk(s, i, (byte)i).intern())) {
      return jr.xk(xk(xl.xk.length), ij(xl.xk.length));
    }
    return xk(0);
  }
  
  private static String xk(int paramInt)
  {
    return xk.substring(xl.xk.length - xl.xk.length, xk.length() - paramInt);
  }
  
  private static String xk(short paramShort, int paramInt, byte paramByte)
  {
    short s = paramInt * 9839 + 5;
    paramInt = 9847 - paramByte * 9843;
    byte[] arrayOfByte1 = ij;
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
  
  private static List<String> xk(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(xl.xk.length - xl.xk.length);
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
    short s = (byte)ij[35];
    int i = (byte)ij[123];
    paramContext.putInt(xk(s, i, (byte)i).intern(), paramInt).apply();
  }
  
  public static boolean xk(Context paramContext)
    throws Exception
  {
    List localList = ij(xk());
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
