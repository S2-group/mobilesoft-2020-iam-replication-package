import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class uy
{
  private static final Pattern e = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static int f = -1;
  Map<String, ?> a;
  String b;
  public uy.b c;
  Thread d;
  private Context g;
  private String h;
  private String i;
  
  public uy(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    new StringBuilder("assetFileName=").append(paramString2).append("\rremotePlistUrl=").append(paramString1).append("\rdeleteCachedFile=").append(paramBoolean);
    this.g = paramContext;
    this.i = paramString2;
    this.b = paramString1;
    if (!vs.a(this.i)) {
      new StringBuilder("Using unencrypted plist file is not allowed, please use PA instead !!! ").append(this.i);
    }
    if (!TextUtils.isEmpty(paramString1))
    {
      paramString1 = paramString1.split("/");
      this.h = paramString1[(paramString1.length - 1)];
    }
    if ((paramBoolean) && (!TextUtils.isEmpty(this.h)) && (this.g.getFilesDir() != null))
    {
      paramString1 = new File(this.g.getFilesDir().getPath(), this.h);
      if (paramString1.exists()) {
        paramString1.delete();
      }
    }
    if (f == -1) {
      f = vt.a(paramContext).a("hs.commons.config.Test_User_Token", -1);
    }
    if (-1 == f)
    {
      f = new Random(System.currentTimeMillis()).nextInt(1000);
      vt.a(paramContext).b("hs.commons.config.Test_User_Token", f);
    }
    if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.h)))
    {
      paramContext = a(this.g, this.h, this.i);
      if (paramContext != null) {}
    }
    else
    {
      return;
    }
    c(paramContext);
    d(paramContext);
    try
    {
      this.a = vq.e(paramContext, new String[] { "Data" });
      this.c = b(this.g, this.a);
      return;
    }
    finally {}
  }
  
  public static int a()
  {
    return f;
  }
  
  /* Error */
  private static Map<String, ?> a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_1
    //   7: invokestatic 83	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   10: ifne +122 -> 132
    //   13: aload_0
    //   14: invokevirtual 99	android/content/Context:getFilesDir	()Ljava/io/File;
    //   17: ifnull +115 -> 132
    //   20: new 101	java/io/File
    //   23: dup
    //   24: aload_0
    //   25: invokevirtual 99	android/content/Context:getFilesDir	()Ljava/io/File;
    //   28: invokevirtual 105	java/io/File:getPath	()Ljava/lang/String;
    //   31: aload_1
    //   32: invokespecial 108	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   35: astore 6
    //   37: aload 6
    //   39: invokevirtual 112	java/io/File:exists	()Z
    //   42: istore_3
    //   43: iload_3
    //   44: ifeq +88 -> 132
    //   47: new 173	java/io/FileInputStream
    //   50: dup
    //   51: aload 6
    //   53: invokespecial 176	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   56: astore 6
    //   58: aload_1
    //   59: astore 4
    //   61: aload 6
    //   63: astore_1
    //   64: aload_1
    //   65: ifnonnull +80 -> 145
    //   68: aload_2
    //   69: invokestatic 83	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   72: ifne +73 -> 145
    //   75: aload_0
    //   76: invokevirtual 180	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   79: astore_0
    //   80: aload_0
    //   81: aload_2
    //   82: invokevirtual 186	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   85: astore_0
    //   86: aload_0
    //   87: astore_1
    //   88: aload 5
    //   90: astore_0
    //   91: aload_1
    //   92: ifnull +25 -> 117
    //   95: aload 5
    //   97: astore_0
    //   98: aload_2
    //   99: invokestatic 83	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   102: ifne +15 -> 117
    //   105: aload_1
    //   106: aload_2
    //   107: invokevirtual 189	java/lang/String:trim	()Ljava/lang/String;
    //   110: invokestatic 75	vs:a	(Ljava/lang/String;)Z
    //   113: invokestatic 192	vs:a	(Ljava/io/InputStream;Z)Ljava/util/Map;
    //   116: astore_0
    //   117: aload_1
    //   118: ifnull +7 -> 125
    //   121: aload_1
    //   122: invokevirtual 197	java/io/InputStream:close	()V
    //   125: aload_0
    //   126: areturn
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 200	java/io/FileNotFoundException:printStackTrace	()V
    //   132: aconst_null
    //   133: astore 4
    //   135: aconst_null
    //   136: astore_1
    //   137: goto -73 -> 64
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 201	java/io/IOException:printStackTrace	()V
    //   145: aload 4
    //   147: astore_2
    //   148: goto -60 -> 88
    //   151: astore_1
    //   152: aload_1
    //   153: invokevirtual 201	java/io/IOException:printStackTrace	()V
    //   156: aload_0
    //   157: areturn
    //   158: astore_0
    //   159: aload 4
    //   161: astore_1
    //   162: aload_1
    //   163: ifnull +7 -> 170
    //   166: aload_1
    //   167: invokevirtual 197	java/io/InputStream:close	()V
    //   170: aload_0
    //   171: athrow
    //   172: astore_1
    //   173: aload_1
    //   174: invokevirtual 201	java/io/IOException:printStackTrace	()V
    //   177: goto -7 -> 170
    //   180: astore_0
    //   181: goto -19 -> 162
    //   184: astore_0
    //   185: goto -23 -> 162
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	paramContext	Context
    //   0	188	1	paramString1	String
    //   0	188	2	paramString2	String
    //   42	2	3	bool	boolean
    //   1	159	4	str	String
    //   4	92	5	localObject1	Object
    //   35	27	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   47	58	127	java/io/FileNotFoundException
    //   80	86	140	java/io/IOException
    //   121	125	151	java/io/IOException
    //   6	43	158	finally
    //   47	58	158	finally
    //   128	132	158	finally
    //   166	170	172	java/io/IOException
    //   68	80	180	finally
    //   80	86	180	finally
    //   141	145	180	finally
    //   98	117	184	finally
  }
  
  private static uy.b b(Context paramContext, Map<String, ?> paramMap)
  {
    uy.b localB = new uy.b();
    if (paramMap == null) {
      return localB;
    }
    paramMap = vq.d(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() <= 0)) {
      return localB;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
    paramMap = paramMap.iterator();
    Object localObject2;
    int j;
    if (paramMap.hasNext())
    {
      Object localObject1 = paramMap.next();
      localObject2 = (Map)localObject1;
      if (((Map)localObject2).isEmpty())
      {
        j = 0;
        label100:
        if (j == 0) {
          break label391;
        }
        paramContext = vq.a((Map)localObject1, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localB.a = bool;
      localB.b = paramContext;
      return localB;
      j = TimeZone.getDefault().getRawOffset() / 3600000;
      Object localObject3 = vq.c((Map)localObject2, new String[] { "TimeZone" });
      if ((!TextUtils.isEmpty((CharSequence)localObject3)) && (!String.valueOf(j).equalsIgnoreCase((String)localObject3)))
      {
        j = 0;
        break label100;
      }
      localObject3 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
      Object localObject4 = vq.c((Map)localObject2, new String[] { "RegionFormat" });
      if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject3).equalsIgnoreCase((String)localObject4)))
      {
        j = 0;
        break label100;
      }
      localObject2 = vq.d((Map)localObject2, new String[] { "UrlScheme" });
      if ((localObject2 == null) || (((List)localObject2).isEmpty()))
      {
        j = 1;
        break label100;
      }
      localObject2 = ((List)localObject2).iterator();
      label391:
      label396:
      for (;;)
      {
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = ((Iterator)localObject2).next();
          localObject4 = paramContext.iterator();
          do
          {
            if (!((Iterator)localObject4).hasNext()) {
              break;
            }
          } while (!((ApplicationInfo)((Iterator)localObject4).next()).packageName.equalsIgnoreCase((String)localObject3));
        }
        for (j = 1;; j = 0)
        {
          if (j != 0) {
            break label396;
          }
          j = 0;
          break label100;
          j = 1;
          break label100;
          break;
        }
      }
      paramContext = "";
    }
  }
  
  private static void c(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = vq.e(paramMap, new String[] { "Data" });
      new StringBuilder("mergeRegions(), main data = ").append(localMap1);
      Map localMap2 = vq.e(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = vq.e(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = vq.e(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map<String, ?>)localObject;
        if (localObject == null) {
          paramMap = vq.e(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = vq.e(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          vq.a(localMap1, vq.e(paramMap, new String[] { "Data" }));
          return;
        }
      }
    }
  }
  
  private static void d(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    label208:
    label209:
    for (;;)
    {
      return;
      Map localMap2 = vq.e(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        int j = Integer.MAX_VALUE;
        Iterator localIterator = localMap2.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = str.replace(" ", "");
          if (e.matcher((CharSequence)localObject).matches())
          {
            localObject = ((String)localObject).split("-");
            if (2 == localObject.length)
            {
              int k = Integer.valueOf(localObject[0]).intValue();
              int m = Integer.valueOf(localObject[1]).intValue();
              if (k <= m)
              {
                if ((f < k) || (f > m) || (k >= j)) {
                  break label208;
                }
                localMap1 = vq.e(localMap2, new String[] { str, "Data" });
                j = k;
              }
            }
          }
        }
        for (;;)
        {
          break;
          if (localMap1 == null) {
            break label209;
          }
          vq.a(vq.e(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  protected final Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean);
  }
  
  public static final class b
  {
    public boolean a = false;
    public String b = "";
    
    public b() {}
  }
}
