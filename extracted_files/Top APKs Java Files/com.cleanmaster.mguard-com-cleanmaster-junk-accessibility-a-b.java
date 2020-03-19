package com.cleanmaster.junk.accessibility.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cleanmaster.junk.accessibility.a.a.c.a;
import java.util.Iterator;
import java.util.List;

public final class b
{
  public static b a;
  private PackageManager b;
  private Context c;
  private c d;
  private List<PackageInfo> e = null;
  
  public b(Context paramContext)
  {
    this.c = paramContext;
    this.b = this.c.getPackageManager();
    paramContext = this.c;
    if (c.b != null) {
      paramContext = c.b;
    }
    for (;;)
    {
      this.d = paramContext;
      return;
      paramContext = new c(paramContext);
      c.b = paramContext;
    }
  }
  
  private PackageInfo a(String paramString)
  {
    if (this.e == null) {}
    try
    {
      this.e = this.b.getInstalledPackages(0);
      if ((this.e == null) || (paramString.isEmpty())) {
        break label85;
      }
      Iterator localIterator = this.e.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localPackageInfo.packageName.equals(paramString)) {
          return localPackageInfo;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    return null;
    label85:
    return null;
  }
  
  private static boolean a(c.a paramA, PackageInfo paramPackageInfo)
  {
    int i;
    if ((paramA != null) && (paramPackageInfo != null))
    {
      if (paramA.a.equals("versionCode"))
      {
        int j = paramPackageInfo.versionCode;
        try
        {
          i = Integer.parseInt(paramA.b);
          paramPackageInfo = paramA.c;
          if (paramPackageInfo.isEmpty()) {
            break label525;
          }
          if (paramPackageInfo.equals("ne"))
          {
            if (j == i) {
              break label495;
            }
            i = 1;
          }
          else if (paramPackageInfo.equals("equal"))
          {
            if (j != i) {
              break label500;
            }
            i = 1;
          }
          else if (paramPackageInfo.equals("ge"))
          {
            if (j < i) {
              break label505;
            }
            i = 1;
          }
          else if (paramPackageInfo.equals("greater"))
          {
            if (j <= i) {
              break label510;
            }
            i = 1;
          }
          else if (paramPackageInfo.equals("le"))
          {
            if (j > i) {
              break label515;
            }
            i = 1;
          }
          else
          {
            if (!paramPackageInfo.equals("less")) {
              break label525;
            }
            if (j >= i) {
              break label520;
            }
            i = 1;
          }
        }
        catch (Exception paramA)
        {
          paramA.printStackTrace();
          return false;
        }
        new StringBuilder("compareFeatureItem unmatch key=").append(paramA.a).append(" configfile value=").append(paramA.b).append(" configfile condition=").append(paramA.c).append(" system value=").append(j);
        return false;
      }
      if (!paramA.a.equals("versionName")) {}
    }
    for (;;)
    {
      try
      {
        paramPackageInfo = paramPackageInfo.versionName;
        String str1 = paramA.b;
        String str2 = paramA.c;
        if ((str1.isEmpty()) || (paramPackageInfo.isEmpty())) {
          break label530;
        }
        if (str2.isEmpty())
        {
          break label530;
          if (bool) {
            break label493;
          }
          new StringBuilder("compareFeatureItem unmatch key=").append(paramA.a).append(" configfile value=").append(paramA.b).append(" configfile condition=").append(paramA.c).append(" system value=").append(paramPackageInfo);
          return false;
        }
        if (str2.equals("contain"))
        {
          bool = paramPackageInfo.contains(str1);
          continue;
        }
        if (str2.equals("equal"))
        {
          bool = paramPackageInfo.equals(str1);
          continue;
        }
        if (str2.equals("lfm"))
        {
          if (paramPackageInfo.indexOf(str1) < 0) {
            break label536;
          }
          bool = true;
          continue;
        }
        if (str2.equals("ne"))
        {
          if (paramPackageInfo.indexOf(str1) >= 0) {
            break label542;
          }
          bool = true;
          continue;
        }
        if (str2.equals("rfm"))
        {
          i = paramPackageInfo.lastIndexOf(str1);
          if (i >= 0)
          {
            bool = true;
            continue;
          }
          bool = false;
          continue;
        }
        bool = false;
        continue;
        new StringBuilder("compareFeatureItem  Illegal featureItem  key key=").append(paramA.a);
      }
      catch (Exception paramA)
      {
        paramA.printStackTrace();
        return false;
      }
      return false;
      return false;
      while (i != 0)
      {
        label493:
        return true;
        label495:
        i = 0;
        continue;
        label500:
        i = 0;
        continue;
        label505:
        i = 0;
        continue;
        label510:
        i = 0;
        continue;
        label515:
        i = 0;
        continue;
        label520:
        i = 0;
        continue;
        label525:
        i = 0;
      }
      break;
      label530:
      boolean bool = false;
      continue;
      label536:
      bool = false;
      continue;
      label542:
      bool = false;
    }
  }
  
  /* Error */
  public final boolean a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 38	com/cleanmaster/junk/accessibility/a/b:d	Lcom/cleanmaster/junk/accessibility/a/c;
    //   6: astore_3
    //   7: new 118	java/lang/StringBuilder
    //   10: dup
    //   11: ldc -95
    //   13: invokespecial 123	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   16: aload_3
    //   17: getfield 163	com/cleanmaster/junk/accessibility/a/c:a	I
    //   20: invokevirtual 136	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_3
    //   25: getfield 166	com/cleanmaster/junk/accessibility/a/c:c	Lcom/cleanmaster/junk/accessibility/a/a/a;
    //   28: ifnonnull +8 -> 36
    //   31: aload_3
    //   32: invokevirtual 169	com/cleanmaster/junk/accessibility/a/c:a	()I
    //   35: pop
    //   36: aload_3
    //   37: getfield 166	com/cleanmaster/junk/accessibility/a/c:c	Lcom/cleanmaster/junk/accessibility/a/a/a;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnull +117 -> 159
    //   45: aload_3
    //   46: getfield 174	com/cleanmaster/junk/accessibility/a/a/a:b	Landroid/util/SparseArray;
    //   49: iload_1
    //   50: invokevirtual 180	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   53: checkcast 182	com/cleanmaster/junk/accessibility/a/a/b
    //   56: astore_3
    //   57: aload_3
    //   58: ifnull +101 -> 159
    //   61: new 118	java/lang/StringBuilder
    //   64: dup
    //   65: ldc -72
    //   67: invokespecial 123	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   70: aload_3
    //   71: invokevirtual 187	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: aload_3
    //   76: ifnull +103 -> 179
    //   79: aload_3
    //   80: getfield 190	com/cleanmaster/junk/accessibility/a/a/b:d	Lcom/cleanmaster/junk/accessibility/a/a/c;
    //   83: astore 4
    //   85: aload_0
    //   86: aload_3
    //   87: getfield 191	com/cleanmaster/junk/accessibility/a/a/b:c	Ljava/lang/String;
    //   90: invokespecial 193	com/cleanmaster/junk/accessibility/a/b:a	(Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   93: astore_3
    //   94: aload_3
    //   95: ifnull +79 -> 174
    //   98: aload 4
    //   100: ifnull +74 -> 174
    //   103: aload 4
    //   105: getfield 197	com/cleanmaster/junk/accessibility/a/a/c:a	Ljava/util/List;
    //   108: astore 4
    //   110: aload 4
    //   112: ifnull +57 -> 169
    //   115: aload 4
    //   117: invokeinterface 62 1 0
    //   122: astore 4
    //   124: aload 4
    //   126: invokeinterface 67 1 0
    //   131: ifeq +33 -> 164
    //   134: aload 4
    //   136: invokeinterface 71 1 0
    //   141: checkcast 87	com/cleanmaster/junk/accessibility/a/a/c$a
    //   144: aload_3
    //   145: invokestatic 199	com/cleanmaster/junk/accessibility/a/b:a	(Lcom/cleanmaster/junk/accessibility/a/a/c$a;Landroid/content/pm/PackageInfo;)Z
    //   148: istore_2
    //   149: iload_2
    //   150: ifne -26 -> 124
    //   153: iconst_0
    //   154: istore_2
    //   155: aload_0
    //   156: monitorexit
    //   157: iload_2
    //   158: ireturn
    //   159: aconst_null
    //   160: astore_3
    //   161: goto -86 -> 75
    //   164: iconst_1
    //   165: istore_2
    //   166: goto -11 -> 155
    //   169: iconst_0
    //   170: istore_2
    //   171: goto -16 -> 155
    //   174: iconst_0
    //   175: istore_2
    //   176: goto -21 -> 155
    //   179: iconst_0
    //   180: istore_2
    //   181: goto -26 -> 155
    //   184: astore_3
    //   185: aload_0
    //   186: monitorexit
    //   187: aload_3
    //   188: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	this	b
    //   0	189	1	paramInt	int
    //   148	33	2	bool	boolean
    //   6	155	3	localObject1	Object
    //   184	4	3	localObject2	Object
    //   83	52	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	36	184	finally
    //   36	41	184	finally
    //   45	57	184	finally
    //   61	75	184	finally
    //   79	94	184	finally
    //   103	110	184	finally
    //   115	124	184	finally
    //   124	149	184	finally
  }
}
