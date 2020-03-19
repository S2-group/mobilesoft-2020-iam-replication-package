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

public final class xp
{
  private static final byte[] da;
  private static int mv;
  private static String wh;
  
  public xp() {}
  
  private static String da(int paramInt)
  {
    return wh((byte)da[123], (short)da[35], (byte)da[122]).intern().substring(wh((byte)da[123], (short)da[35], (byte)da[122]).intern().length() - paramInt);
  }
  
  private static List<String> da(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String wh()
    throws Exception
  {
    if (wh.equals(wh((byte)da[123], (short)da[35], (byte)da[122]).intern())) {
      return dx.wh(wh(al.wh.length), da(al.wh.length));
    }
    return wh(0);
  }
  
  private static String wh(int paramInt)
  {
    return wh.substring(al.wh.length - al.wh.length, wh.length() - paramInt);
  }
  
  private static String wh(short paramShort1, short paramShort2, byte paramByte)
  {
    byte[] arrayOfByte1 = da;
    short s1 = 0;
    short s2 = 0;
    int i = paramByte * 9839 + 5;
    paramShort2 += 4;
    byte b = paramShort1 * 44 + 54;
    byte[] arrayOfByte2 = new byte[i];
    paramShort1 = s1;
    paramByte = b;
    s1 = paramShort2;
    if (arrayOfByte1 == null) {
      paramByte = paramShort2;
    }
    for (paramShort1 = s2;; paramShort1 = s1)
    {
      paramByte = b + -paramByte;
      s1 = paramShort2;
      paramShort2 = s1 + 1;
      s1 = paramShort1 + 1;
      arrayOfByte2[paramShort1] = ((byte)paramByte);
      if (s1 == i) {
        return new String(arrayOfByte2, 0);
      }
      paramShort1 = arrayOfByte1[paramShort2];
      b = paramByte;
      paramByte = paramShort1;
    }
  }
  
  private static List<String> wh(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(al.wh.length - al.wh.length);
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
  
  private static void wh(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(wh((byte)da[122], (short)9842, (byte)da[123]).intern(), paramInt).apply();
  }
  
  public static boolean wh(Context paramContext)
    throws Exception
  {
    List localList = da(wh());
    return wh(paramContext, wh(paramContext, false), localList);
  }
  
  private static boolean wh(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          wh(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean wh(String paramString)
    throws Exception
  {
    return wh().contains(paramString);
  }
}
