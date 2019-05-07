package org.puwo.fxdu.aatu;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class kp
{
  private static int kt;
  private static final byte[] li;
  private static String ws;
  
  public kp() {}
  
  private static String li(int paramInt)
  {
    return ws((byte)li[''], (byte)li[9], (byte)li['']).intern().substring(ws((byte)li[''], (byte)li[9], (byte)li['']).intern().length() - paramInt);
  }
  
  private static List<String> li(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    ArrayList localArrayList = new ArrayList(paramString.countTokens());
    while (paramString.hasMoreTokens()) {
      localArrayList.add(paramString.nextToken().trim());
    }
    return localArrayList;
  }
  
  private static String ws()
    throws Exception
  {
    if (ws.equals(ws((byte)li[''], (byte)li[9], (byte)li['']).intern())) {
      return zz.ws(ws(yu.ws.length), li(yu.ws.length));
    }
    return ws(0);
  }
  
  private static String ws(byte paramByte1, int paramInt, byte paramByte2)
  {
    paramInt = 7 - paramInt * 4;
    paramByte2 = paramByte2 * 2 + 98;
    int j = paramByte1 * 9839 + 5;
    byte[] arrayOfByte1 = li;
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
  
  private static String ws(int paramInt)
  {
    return ws.substring(yu.ws.length - yu.ws.length, ws.length() - paramInt);
  }
  
  private static List<String> ws(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(yu.ws.length - yu.ws.length);
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
  
  private static void ws(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt(ws((byte)li[9], (byte)li[''], (byte)li[9]).intern(), paramInt).apply();
  }
  
  public static boolean ws(Context paramContext)
    throws Exception
  {
    List localList = li(ws());
    return ws(paramContext, ws(paramContext, false), localList);
  }
  
  private static boolean ws(Context paramContext, List<String> paramList1, List<String> paramList2)
  {
    int i = 0;
    while (i < paramList1.size())
    {
      int j = 0;
      while (j < paramList2.size())
      {
        if (((String)paramList1.get(i)).equals((String)paramList2.get(j)))
        {
          ws(paramContext, j);
          return true;
        }
        j += 1;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean ws(String paramString)
    throws Exception
  {
    return ws().contains(paramString);
  }
}
