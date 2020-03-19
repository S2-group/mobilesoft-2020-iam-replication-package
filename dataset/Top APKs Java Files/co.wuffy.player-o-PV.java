package o;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PV
{
  private static String ʻ = ˊ(new char[] { -6399, 9189, 25411, -29204, -30139, -2369, 7327, -2195, -31338, -5372, 21875, -18078, -17019, 21814, -1580, -8301, 23146, -20251, 19068, -14483, -836, 30325, -10349, 567, -14302, 2305, -19585, -8580, 29287, -1536, -20405, 19260, -1580, -8301, 23146, -20251, 27467, -23046 }).intern();
  private static char ʼ;
  private static char ʽ;
  public static int ˊ;
  private static char ˊॱ;
  public static int ˏ;
  private static int ˏॱ;
  public static String ॱ;
  private static int ॱˊ;
  private static char ᐝ = 51378;
  iF ˋ;
  Context ˎ;
  
  static
  {
    ʽ = '㶚';
    ˊॱ = 60760;
    ʼ = 44745;
    ॱˊ = 0;
    ˏॱ = 1;
    ॱ = ˊ(new char[] { 27653, 4995, -25528, -11763, 31570, -14042, -12597, 3241, -9306, -28427, -8990, 19751 }).intern();
    ˏ = 0;
    ˊ = 1;
  }
  
  public PV(Context paramContext, QQ paramQQ)
  {
    this.ˎ = paramContext;
    this.ˋ = paramQQ;
  }
  
  /* Error */
  private static String ˊ(char[] paramArrayOfChar)
  {
    // Byte code:
    //   0: goto +303 -> 303
    //   3: iconst_0
    //   4: istore 6
    //   6: goto +275 -> 281
    //   9: aload_0
    //   10: arraylength
    //   11: ifge +6 -> 17
    //   14: goto +39 -> 53
    //   17: goto +116 -> 133
    //   20: iload 5
    //   22: tableswitch	default:+18->40, 0:+133->155
    //   40: iload 7
    //   42: istore 5
    //   44: goto +15 -> 59
    //   47: iconst_1
    //   48: istore 6
    //   50: goto +125 -> 175
    //   53: iconst_1
    //   54: istore 5
    //   56: goto -36 -> 20
    //   59: aload 9
    //   61: iconst_0
    //   62: aload_0
    //   63: iload 5
    //   65: caload
    //   66: castore
    //   67: aload 9
    //   69: iconst_1
    //   70: aload_0
    //   71: iload 5
    //   73: iconst_1
    //   74: iadd
    //   75: caload
    //   76: castore
    //   77: getstatic 33	o/PV:ʽ	C
    //   80: istore_1
    //   81: getstatic 36	o/PV:ˊॱ	C
    //   84: istore_2
    //   85: getstatic 39	o/PV:ʼ	C
    //   88: istore_3
    //   89: getstatic 31	o/PV:ᐝ	C
    //   92: istore 4
    //   94: aload 9
    //   96: iload_1
    //   97: iload_2
    //   98: iload_3
    //   99: iload 4
    //   101: invokestatic 122	o/It:ॱ	([CCCCC)V
    //   104: aload 8
    //   106: iload 5
    //   108: aload 9
    //   110: iconst_0
    //   111: caload
    //   112: castore
    //   113: aload 8
    //   115: iload 5
    //   117: iconst_1
    //   118: iadd
    //   119: aload 9
    //   121: iconst_1
    //   122: caload
    //   123: castore
    //   124: iload 5
    //   126: iconst_2
    //   127: iadd
    //   128: istore 5
    //   130: goto +12 -> 142
    //   133: iconst_0
    //   134: istore 5
    //   136: goto -116 -> 20
    //   139: astore_0
    //   140: aload_0
    //   141: athrow
    //   142: iload 5
    //   144: aload_0
    //   145: arraylength
    //   146: if_icmpge +6 -> 152
    //   149: goto +123 -> 272
    //   152: goto -105 -> 47
    //   155: new 60	java/lang/String
    //   158: dup
    //   159: aload 8
    //   161: iconst_1
    //   162: aload 8
    //   164: iconst_0
    //   165: caload
    //   166: iconst_1
    //   167: iadd
    //   168: invokestatic 128	java/util/Arrays:copyOfRange	([CII)[C
    //   171: invokespecial 131	java/lang/String:<init>	([C)V
    //   174: areturn
    //   175: iload 6
    //   177: tableswitch	default:+23->200, 0:+59->236, 1:+-22->155
    //   200: goto +72 -> 272
    //   203: astore_0
    //   204: aload_0
    //   205: athrow
    //   206: getstatic 41	o/PV:ॱˊ	I
    //   209: bipush 75
    //   211: iadd
    //   212: istore 6
    //   214: iload 6
    //   216: sipush 128
    //   219: irem
    //   220: putstatic 43	o/PV:ˏॱ	I
    //   223: iload 6
    //   225: iconst_2
    //   226: irem
    //   227: ifne +6 -> 233
    //   230: goto +36 -> 266
    //   233: goto -230 -> 3
    //   236: getstatic 43	o/PV:ˏॱ	I
    //   239: bipush 9
    //   241: iadd
    //   242: istore 6
    //   244: iload 6
    //   246: sipush 128
    //   249: irem
    //   250: putstatic 41	o/PV:ॱˊ	I
    //   253: iload 6
    //   255: iconst_2
    //   256: irem
    //   257: ifeq +6 -> 263
    //   260: goto +18 -> 278
    //   263: goto -204 -> 59
    //   266: iconst_1
    //   267: istore 6
    //   269: goto +12 -> 281
    //   272: iconst_0
    //   273: istore 6
    //   275: goto -100 -> 175
    //   278: goto -219 -> 59
    //   281: iload 6
    //   283: tableswitch	default:+17->300, 0:+-141->142
    //   300: goto -291 -> 9
    //   303: aload_0
    //   304: arraylength
    //   305: newarray char
    //   307: astore 8
    //   309: iconst_0
    //   310: istore 5
    //   312: iconst_0
    //   313: istore 7
    //   315: iconst_2
    //   316: newarray char
    //   318: astore 9
    //   320: goto -114 -> 206
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	323	0	paramArrayOfChar	char[]
    //   80	17	1	c1	char
    //   84	14	2	c2	char
    //   88	11	3	c3	char
    //   92	8	4	c4	char
    //   20	291	5	i	int
    //   4	278	6	j	int
    //   40	274	7	k	int
    //   104	204	8	arrayOfChar1	char[]
    //   59	260	9	arrayOfChar2	char[]
    // Exception table:
    //   from	to	target	type
    //   77	81	139	java/lang/Exception
    //   81	85	139	java/lang/Exception
    //   85	89	139	java/lang/Exception
    //   89	94	139	java/lang/Exception
    //   94	104	139	java/lang/Exception
    //   94	104	203	java/lang/Exception
  }
  
  public static abstract interface iF
  {
    public abstract void ॱ(int paramInt);
  }
  
  private final class ˊ
    extends AsyncTask<PU, PR, PU>
  {
    private ˊ() {}
    
    private PU ˊ(PU... paramVarArgs)
    {
      paramVarArgs[0].ˊ();
      PackageManager localPackageManager = PV.this.ˎ.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        Object localObject1 = (ApplicationInfo)localIterator.next();
        Object localObject2 = ((ApplicationInfo)localObject1).metaData;
        if (localObject2 != null)
        {
          String str = ((Bundle)localObject2).getString(PV.ॱ);
          if (str != null)
          {
            localObject2 = paramVarArgs[0];
            localObject1 = new PR(PV.this.ˎ, str, localPackageManager.getApplicationIcon((ApplicationInfo)localObject1), (String)localPackageManager.getApplicationLabel((ApplicationInfo)localObject1), localPackageManager.getLaunchIntentForPackage(((ApplicationInfo)localObject1).packageName));
            ((PU)localObject2).ˋ.add(localObject1);
          }
        }
      }
      try
      {
        Thread.sleep(1000L);
        return paramVarArgs[0];
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
    
    protected final void onPreExecute()
    {
      if (PV.this.ˋ != null) {
        PV.this.ˋ.ॱ(PV.ˏ);
      }
    }
  }
}
