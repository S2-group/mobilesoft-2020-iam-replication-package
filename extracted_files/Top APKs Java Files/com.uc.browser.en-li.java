import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.c2dm.a;
import com.uc.platform.h;
import java.util.Iterator;
import java.util.List;

public final class li
{
  private static StringBuffer a = new StringBuffer();
  private static final String[] b = { "com.whatsapp", "com.facebook.katana", "jp.naver.line.android", "com.google.android.youtube", "com.android.vending", "com.android.chrome", "com.opera.mini.android", "com.vkontakte.android", "com.yandex.browser" };
  
  private static boolean a(String paramString)
  {
    if (pf.a(paramString)) {}
    for (;;)
    {
      return false;
      int i = 0;
      while (i < 9)
      {
        if (paramString.equals(b[i])) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static Object[] a()
  {
    if (a.length() <= 0) {
      return null;
    }
    Object[] arrayOfObject = lb.a(a.toString(), "mini_app_upload");
    a.setLength(0);
    return arrayOfObject;
  }
  
  private static String b(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0)) {
      return "0";
    }
    return paramString.trim();
  }
  
  public static void b()
  {
    int k = 0;
    int j = 0;
    Context localContext = a.d;
    int i = pu.m;
    if ((i <= 0) || (i > 30)) {}
    for (i = 0; i == 0; i = 1)
    {
      label29:
      return;
      long l1 = i;
      long l2 = h.a(localContext);
      if ((l2 > 0L) && (l1 * 24L * 3600L * 1000L + l2 >= System.currentTimeMillis())) {
        break label421;
      }
    }
    localContext = a.d;
    a.setLength(0);
    StringBuilder localStringBuilder = new StringBuilder("(");
    for (;;)
    {
      try
      {
        localList = localContext.getPackageManager().getInstalledPackages(0);
        i = k;
        if (localList != null)
        {
          Iterator localIterator = localList.iterator();
          i = j;
          if (localIterator.hasNext())
          {
            localPackageInfo = (PackageInfo)localIterator.next();
            if ((localPackageInfo.packageName == null) || (localPackageInfo.packageName.trim().length() == 0)) {
              continue;
            }
            if ((localPackageInfo.applicationInfo.flags & 0x1) != 0)
            {
              j = i;
              if (!a(localPackageInfo.packageName.trim())) {
                break label426;
              }
              i += 1;
              localStringBuilder.append("pi_").append(localPackageInfo.packageName.trim()).append(',');
              localStringBuilder.append(b(localPackageInfo.versionName)).append(';');
              continue;
            }
          }
        }
      }
      catch (Throwable localThrowable)
      {
        List localList;
        PackageInfo localPackageInfo;
        return;
        if (i > 200)
        {
          j = i;
          if (!a(localPackageInfo.packageName.trim())) {
            break label426;
          }
        }
        j = i + 1;
        localThrowable.append(localPackageInfo.packageName.trim()).append(',');
        localThrowable.append(b(localPackageInfo.versionName)).append(';');
        break label426;
        i = localList.size();
        h.a(localContext, System.currentTimeMillis());
        if ((i <= 0) || (localThrowable.length() == 1) || (localThrowable.charAt(localThrowable.length() - 1) != ';')) {
          break label29;
        }
        localThrowable.append("Appcount,").append(i);
        localThrowable.append(')');
        a.append(localThrowable.toString());
        return;
      }
      finally
      {
        h.a(localContext, System.currentTimeMillis());
      }
      label421:
      i = 0;
      break;
      label426:
      i = j;
    }
  }
  
  /* Error */
  public static void c()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: new 197	java/io/File
    //   7: dup
    //   8: new 109	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   15: getstatic 200	lb:a	Ljava/lang/String;
    //   18: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: ldc -54
    //   23: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokespecial 203	java/io/File:<init>	(Ljava/lang/String;)V
    //   32: astore_0
    //   33: aload_0
    //   34: invokevirtual 206	java/io/File:exists	()Z
    //   37: ifne +4 -> 41
    //   40: return
    //   41: new 208	java/io/FileInputStream
    //   44: dup
    //   45: aload_0
    //   46: invokespecial 211	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   49: astore_0
    //   50: new 213	java/io/DataInputStream
    //   53: dup
    //   54: aload_0
    //   55: invokespecial 216	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   58: astore_1
    //   59: aload_1
    //   60: invokevirtual 219	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   63: pop
    //   64: getstatic 17	li:a	Ljava/lang/StringBuffer;
    //   67: aload_1
    //   68: invokevirtual 219	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   71: invokevirtual 192	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   74: pop
    //   75: aload_0
    //   76: invokevirtual 222	java/io/FileInputStream:close	()V
    //   79: aload_1
    //   80: invokevirtual 223	java/io/DataInputStream:close	()V
    //   83: return
    //   84: astore_0
    //   85: return
    //   86: astore_2
    //   87: aload_0
    //   88: invokevirtual 222	java/io/FileInputStream:close	()V
    //   91: aload_1
    //   92: invokevirtual 223	java/io/DataInputStream:close	()V
    //   95: return
    //   96: astore_0
    //   97: return
    //   98: astore_0
    //   99: aconst_null
    //   100: astore_0
    //   101: aload_1
    //   102: ifnull +7 -> 109
    //   105: aload_1
    //   106: invokevirtual 222	java/io/FileInputStream:close	()V
    //   109: aload_0
    //   110: ifnull -70 -> 40
    //   113: aload_0
    //   114: invokevirtual 223	java/io/DataInputStream:close	()V
    //   117: return
    //   118: astore_0
    //   119: return
    //   120: astore_1
    //   121: aconst_null
    //   122: astore_0
    //   123: aload_0
    //   124: ifnull +7 -> 131
    //   127: aload_0
    //   128: invokevirtual 222	java/io/FileInputStream:close	()V
    //   131: aload_2
    //   132: ifnull +7 -> 139
    //   135: aload_2
    //   136: invokevirtual 223	java/io/DataInputStream:close	()V
    //   139: aload_1
    //   140: athrow
    //   141: astore_0
    //   142: goto -51 -> 91
    //   145: astore_0
    //   146: goto -67 -> 79
    //   149: astore_1
    //   150: goto -41 -> 109
    //   153: astore_0
    //   154: goto -23 -> 131
    //   157: astore_0
    //   158: goto -19 -> 139
    //   161: astore_1
    //   162: goto -39 -> 123
    //   165: astore_3
    //   166: aload_1
    //   167: astore_2
    //   168: aload_3
    //   169: astore_1
    //   170: goto -47 -> 123
    //   173: astore_1
    //   174: aconst_null
    //   175: astore_2
    //   176: aload_0
    //   177: astore_1
    //   178: aload_2
    //   179: astore_0
    //   180: goto -79 -> 101
    //   183: astore_2
    //   184: aload_0
    //   185: astore_2
    //   186: aload_1
    //   187: astore_0
    //   188: aload_2
    //   189: astore_1
    //   190: goto -89 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   32	44	0	localObject1	Object
    //   84	4	0	localException1	Exception
    //   96	1	0	localException2	Exception
    //   98	1	0	localException3	Exception
    //   100	14	0	localObject2	Object
    //   118	1	0	localException4	Exception
    //   122	6	0	localObject3	Object
    //   141	1	0	localException5	Exception
    //   145	1	0	localException6	Exception
    //   153	1	0	localException7	Exception
    //   157	20	0	localException8	Exception
    //   179	9	0	localObject4	Object
    //   3	103	1	localDataInputStream	java.io.DataInputStream
    //   120	20	1	localObject5	Object
    //   149	1	1	localException9	Exception
    //   161	6	1	localObject6	Object
    //   169	1	1	localObject7	Object
    //   173	1	1	localException10	Exception
    //   177	13	1	localObject8	Object
    //   1	1	2	localObject9	Object
    //   86	50	2	localException11	Exception
    //   167	12	2	localObject10	Object
    //   183	1	2	localException12	Exception
    //   185	4	2	localObject11	Object
    //   165	4	3	localObject12	Object
    // Exception table:
    //   from	to	target	type
    //   79	83	84	java/lang/Exception
    //   59	64	86	java/lang/Exception
    //   91	95	96	java/lang/Exception
    //   41	50	98	java/lang/Exception
    //   113	117	118	java/lang/Exception
    //   41	50	120	finally
    //   87	91	141	java/lang/Exception
    //   75	79	145	java/lang/Exception
    //   105	109	149	java/lang/Exception
    //   127	131	153	java/lang/Exception
    //   135	139	157	java/lang/Exception
    //   50	59	161	finally
    //   59	64	165	finally
    //   64	75	165	finally
    //   50	59	173	java/lang/Exception
    //   64	75	183	java/lang/Exception
  }
  
  /* Error */
  public static void d()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: new 197	java/io/File
    //   7: dup
    //   8: new 109	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   15: getstatic 200	lb:a	Ljava/lang/String;
    //   18: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: ldc -54
    //   23: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokespecial 203	java/io/File:<init>	(Ljava/lang/String;)V
    //   32: astore_0
    //   33: aload_0
    //   34: invokevirtual 206	java/io/File:exists	()Z
    //   37: ifeq +8 -> 45
    //   40: aload_0
    //   41: invokevirtual 226	java/io/File:delete	()Z
    //   44: pop
    //   45: new 228	java/io/FileOutputStream
    //   48: dup
    //   49: aload_0
    //   50: invokespecial 229	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   53: astore_0
    //   54: new 231	java/io/DataOutputStream
    //   57: dup
    //   58: aload_0
    //   59: invokespecial 234	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   62: astore_1
    //   63: aload_1
    //   64: getstatic 239	qm:ai	Ljava/lang/String;
    //   67: invokevirtual 242	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   70: aload_1
    //   71: getstatic 17	li:a	Ljava/lang/StringBuffer;
    //   74: invokevirtual 58	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   77: invokevirtual 242	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   80: aload_0
    //   81: invokevirtual 243	java/io/FileOutputStream:close	()V
    //   84: aload_1
    //   85: invokevirtual 244	java/io/DataOutputStream:close	()V
    //   88: return
    //   89: astore_0
    //   90: aconst_null
    //   91: astore_0
    //   92: aload_1
    //   93: ifnull +7 -> 100
    //   96: aload_1
    //   97: invokevirtual 243	java/io/FileOutputStream:close	()V
    //   100: aload_0
    //   101: ifnull -13 -> 88
    //   104: aload_0
    //   105: invokevirtual 244	java/io/DataOutputStream:close	()V
    //   108: return
    //   109: astore_0
    //   110: return
    //   111: astore_1
    //   112: aconst_null
    //   113: astore_0
    //   114: aload_0
    //   115: ifnull +7 -> 122
    //   118: aload_0
    //   119: invokevirtual 243	java/io/FileOutputStream:close	()V
    //   122: aload_2
    //   123: ifnull +7 -> 130
    //   126: aload_2
    //   127: invokevirtual 244	java/io/DataOutputStream:close	()V
    //   130: aload_1
    //   131: athrow
    //   132: astore_0
    //   133: goto -49 -> 84
    //   136: astore_0
    //   137: return
    //   138: astore_1
    //   139: goto -39 -> 100
    //   142: astore_0
    //   143: goto -21 -> 122
    //   146: astore_0
    //   147: goto -17 -> 130
    //   150: astore_1
    //   151: goto -37 -> 114
    //   154: astore_3
    //   155: aload_1
    //   156: astore_2
    //   157: aload_3
    //   158: astore_1
    //   159: goto -45 -> 114
    //   162: astore_1
    //   163: aconst_null
    //   164: astore_2
    //   165: aload_0
    //   166: astore_1
    //   167: aload_2
    //   168: astore_0
    //   169: goto -77 -> 92
    //   172: astore_2
    //   173: aload_0
    //   174: astore_2
    //   175: aload_1
    //   176: astore_0
    //   177: aload_2
    //   178: astore_1
    //   179: goto -87 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   32	49	0	localObject1	Object
    //   89	1	0	localException1	Exception
    //   91	14	0	localObject2	Object
    //   109	1	0	localException2	Exception
    //   113	6	0	localObject3	Object
    //   132	1	0	localException3	Exception
    //   136	1	0	localException4	Exception
    //   142	1	0	localException5	Exception
    //   146	20	0	localException6	Exception
    //   168	9	0	localObject4	Object
    //   3	94	1	localDataOutputStream	java.io.DataOutputStream
    //   111	20	1	localObject5	Object
    //   138	1	1	localException7	Exception
    //   150	6	1	localObject6	Object
    //   158	1	1	localObject7	Object
    //   162	1	1	localException8	Exception
    //   166	13	1	localObject8	Object
    //   1	167	2	localObject9	Object
    //   172	1	2	localException9	Exception
    //   174	4	2	localObject10	Object
    //   154	4	3	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   45	54	89	java/lang/Exception
    //   104	108	109	java/lang/Exception
    //   45	54	111	finally
    //   80	84	132	java/lang/Exception
    //   84	88	136	java/lang/Exception
    //   96	100	138	java/lang/Exception
    //   118	122	142	java/lang/Exception
    //   126	130	146	java/lang/Exception
    //   54	63	150	finally
    //   63	80	154	finally
    //   54	63	162	java/lang/Exception
    //   63	80	172	java/lang/Exception
  }
}
