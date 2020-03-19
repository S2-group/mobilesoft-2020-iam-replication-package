package o;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.kavsdk.shared.SdkUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class sS
{
  private static final Set<String> 僅輸入原文;
  private static int 四難;
  private static final byte[] 論太過不及等反常脈象 = { 96, 48, -114, -31, 12, -2, -63, 69, -18, 12, 6, 2, -7, -7, -57, 51, 13, -10, 14, -3, -6, -5, -54, 51, 15, 0, -66, 73, -22, 19, -17, 5, 5, -12, 13, -13, 6, -2, 13, 12, -2, -63, 69, -14, -2, -53, 61, 3, 1, 9, -74, 53, 12, -1, 6, -19, 8, 5, -9, 13, -17, 6, -2, 9, 6, 12, -2, -63, 69, -14, -2, -53, 51, 13, -10, 14, -3, -6, -5, -54, 55, -4, 18, 6, -44, 34, 7, -17, 13, 12, -2, -63, 69, -14, -2, -53, 51, 13, -10, 14, -3, -6, -5, -54, 51, 15, 0, -66, 69, -18, 12, 6, 2, -7, -7, -6, 15, 0, 3, 12, -2, -63, 51, 13, -10, 14, -3, -6, -5, -54, 72, -17, 9, -10, 5, 5, -7, 12, -2, -63, 69, -14, -2, -53, 51, 13, -10, 14, -3, -6, -5, -54, 66, 2, -13, 7, 3, -14, 3, 5, 5, 5, 1, -19, 11, 0, -7, 13, 12, -2, -63, 57, 8, 0, -8, 5, -7, -55, 51, 13, -10, 14, -3, -6, -5, -54, 56, -1, 0, -1, -2, -1, 2, 8 };
  private static final String 難經本義;
  private static final Set<String> 難經本義卷上;
  private final String 一難;
  private final boolean 三難;
  private final String 二難;
  private final String 論寸口脈與經經脈榮衛度數;
  private final String 論尺寸為脈之大要會;
  
  static
  {
    四難 = 215;
    難經本義 = sS.class.getSimpleName();
    int i = 論太過不及等反常脈象[25];
    String str1 = 難經本義(i, i | 0x31, -論太過不及等反常脈象[30]);
    i = 論太過不及等反常脈象[25];
    僅輸入原文 = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { str1, 難經本義(i, i, 論太過不及等反常脈象[49]) })));
    i = 論太過不及等反常脈象[25];
    str1 = 難經本義(i, i | 0x4F, 論太過不及等反常脈象[31]);
    i = 論太過不及等反常脈象[25];
    String str2 = 難經本義(i, i | 0x81, 論太過不及等反常脈象[49]);
    i = 論太過不及等反常脈象[25];
    String str3 = 難經本義(i, i | 0x67, 論太過不及等反常脈象['¤']);
    i = 論太過不及等反常脈象[25];
    難經本義卷上 = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { str1, str2, str3, 難經本義(i, i | 0xA4, 論太過不及等反常脈象[25]), 難經本義(論太過不及等反常脈象[25], -論太過不及等反常脈象[3], -論太過不及等反常脈象[81]) })));
  }
  
  sS(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.一難 = paramString1;
    this.論寸口脈與經經脈榮衛度數 = paramString2;
    if (paramString3 == null) {
      paramString3 = "";
    }
    this.二難 = paramString3;
    this.論尺寸為脈之大要會 = paramString4;
    this.三難 = 難經本義(this.二難);
  }
  
  private static String 難經本義(int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte1 = 論太過不及等反常脈象;
    paramInt1 = paramInt1 * 3 + 99;
    paramInt3 = 36 - paramInt3;
    paramInt2 = 168 - paramInt2;
    int i = 0;
    int k = 0;
    byte[] arrayOfByte2 = new byte[paramInt3];
    int j = paramInt3 - 1;
    paramInt3 = paramInt1;
    paramInt1 = paramInt2;
    if (arrayOfByte1 == null)
    {
      paramInt3 = paramInt2;
      i = j;
      paramInt1 = paramInt2;
      paramInt2 = k;
    }
    for (;;)
    {
      paramInt1 += 1;
      paramInt3 += i;
      i = paramInt2;
      arrayOfByte2[i] = ((byte)paramInt3);
      paramInt2 = i + 1;
      if (i == j) {
        return new String(arrayOfByte2, 0).intern();
      }
      i = arrayOfByte1[paramInt1];
    }
  }
  
  public static List<sS> 難經本義(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(難經本義(paramContext, (PackageInfo)((Iterator)localObject).next()));
    }
    return localArrayList;
  }
  
  public static sS 難經本義(Context paramContext, PackageInfo paramPackageInfo)
  {
    paramContext = paramContext.getPackageManager();
    return new sS(paramPackageInfo.packageName, paramPackageInfo.applicationInfo.sourceDir, paramContext.getInstallerPackageName(paramPackageInfo.packageName), SdkUtils.getPackageStringForStatistics(paramPackageInfo));
  }
  
  static sS 難經本義(Context paramContext, String paramString)
  {
    try
    {
      paramContext = 難經本義(paramContext, paramContext.getPackageManager().getPackageInfo(paramString, 0));
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw paramContext;
    }
  }
  
  private static boolean 難經本義(String paramString)
  {
    return (僅輸入原文.contains(paramString)) || (難經本義卷上.contains(paramString));
  }
  
  public String 一難()
  {
    return this.論尺寸為脈之大要會;
  }
  
  public String 僅輸入原文()
  {
    return this.論寸口脈與經經脈榮衛度數;
  }
  
  public boolean 論寸口脈與經經脈榮衛度數()
  {
    return this.三難;
  }
  
  public String 難經本義()
  {
    return this.一難;
  }
  
  public String 難經本義卷上()
  {
    return this.二難;
  }
}
