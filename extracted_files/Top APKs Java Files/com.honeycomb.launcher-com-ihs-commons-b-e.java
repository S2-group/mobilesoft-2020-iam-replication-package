package com.ihs.commons.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.ihs.commons.i.i;
import com.ihs.commons.i.m;
import com.ihs.commons.i.n;
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

class e
{
  private static final Pattern a = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static int b = -1;
  private Map c;
  private Context d;
  private String e;
  private String f;
  private String g;
  private h h;
  private Thread i;
  
  public e(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    com.ihs.commons.i.g.a("assetFileName=" + paramString2 + "\rremotePlistUrl=" + paramString1 + "\rdeleteCachedFile=" + paramBoolean);
    this.d = paramContext;
    this.g = paramString2;
    this.e = paramString1;
    if (!m.a(this.g)) {
      com.ihs.commons.i.g.d("Using unencrypted plist file is not allowed, please use PA instead !!! " + this.g);
    }
    if (!TextUtils.isEmpty(paramString1))
    {
      paramString1 = paramString1.split("/");
      this.f = paramString1[(paramString1.length - 1)];
    }
    if (paramBoolean) {
      e();
    }
    if (b == -1) {
      b = n.a(paramContext).a("hs.commons.config.Test_User_Token", -1);
    }
    if (-1 == b)
    {
      b = new Random(System.currentTimeMillis()).nextInt(1000);
      n.a(paramContext).b("hs.commons.config.Test_User_Token", b);
    }
    if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.f))) {
      f();
    }
  }
  
  /* Error */
  private static Map a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_1
    //   7: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   10: ifne +122 -> 132
    //   13: aload_0
    //   14: invokevirtual 148	android/content/Context:getFilesDir	()Ljava/io/File;
    //   17: ifnull +115 -> 132
    //   20: new 150	java/io/File
    //   23: dup
    //   24: aload_0
    //   25: invokevirtual 148	android/content/Context:getFilesDir	()Ljava/io/File;
    //   28: invokevirtual 153	java/io/File:getPath	()Ljava/lang/String;
    //   31: aload_1
    //   32: invokespecial 156	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   35: astore 6
    //   37: aload 6
    //   39: invokevirtual 160	java/io/File:exists	()Z
    //   42: istore_3
    //   43: iload_3
    //   44: ifeq +88 -> 132
    //   47: new 162	java/io/FileInputStream
    //   50: dup
    //   51: aload 6
    //   53: invokespecial 165	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   56: astore 6
    //   58: aload_1
    //   59: astore 4
    //   61: aload 6
    //   63: astore_1
    //   64: aload_1
    //   65: ifnonnull +80 -> 145
    //   68: aload_2
    //   69: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   72: ifne +73 -> 145
    //   75: aload_0
    //   76: invokevirtual 169	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   79: astore_0
    //   80: aload_0
    //   81: aload_2
    //   82: invokevirtual 175	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
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
    //   99: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   102: ifne +15 -> 117
    //   105: aload_1
    //   106: aload_2
    //   107: invokevirtual 178	java/lang/String:trim	()Ljava/lang/String;
    //   110: invokestatic 75	com/ihs/commons/i/m:a	(Ljava/lang/String;)Z
    //   113: invokestatic 181	com/ihs/commons/i/m:a	(Ljava/io/InputStream;Z)Ljava/util/Map;
    //   116: astore_0
    //   117: aload_1
    //   118: ifnull +7 -> 125
    //   121: aload_1
    //   122: invokevirtual 186	java/io/InputStream:close	()V
    //   125: aload_0
    //   126: areturn
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 189	java/io/FileNotFoundException:printStackTrace	()V
    //   132: aconst_null
    //   133: astore 4
    //   135: aconst_null
    //   136: astore_1
    //   137: goto -73 -> 64
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 190	java/io/IOException:printStackTrace	()V
    //   145: aload 4
    //   147: astore_2
    //   148: goto -60 -> 88
    //   151: astore_1
    //   152: aload_1
    //   153: invokevirtual 190	java/io/IOException:printStackTrace	()V
    //   156: aload_0
    //   157: areturn
    //   158: astore_0
    //   159: aload 4
    //   161: astore_1
    //   162: aload_1
    //   163: ifnull +7 -> 170
    //   166: aload_1
    //   167: invokevirtual 186	java/io/InputStream:close	()V
    //   170: aload_0
    //   171: athrow
    //   172: astore_1
    //   173: aload_1
    //   174: invokevirtual 190	java/io/IOException:printStackTrace	()V
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
  
  private static boolean a(Map paramMap, List paramList)
  {
    if (paramMap.isEmpty()) {
      return false;
    }
    int j = TimeZone.getDefault().getRawOffset() / 3600000;
    Object localObject1 = i.c(paramMap, new String[] { "TimeZone" });
    if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!String.valueOf(j).equalsIgnoreCase((String)localObject1))) {
      return false;
    }
    localObject1 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    Object localObject2 = i.c(paramMap, new String[] { "RegionFormat" });
    if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!((String)localObject1).equalsIgnoreCase((String)localObject2))) {
      return false;
    }
    paramMap = i.e(paramMap, new String[] { "UrlScheme" });
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return true;
    }
    paramMap = paramMap.iterator();
    for (;;)
    {
      if (paramMap.hasNext())
      {
        localObject1 = paramMap.next();
        localObject2 = paramList.iterator();
        do
        {
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
        } while (!((ApplicationInfo)((Iterator)localObject2).next()).packageName.equalsIgnoreCase((String)localObject1));
      }
      for (j = 1; j == 0; j = 0)
      {
        return false;
        return true;
      }
    }
  }
  
  private static h b(Context paramContext, Map paramMap)
  {
    h localH = new h();
    if (paramMap == null) {
      return localH;
    }
    paramMap = i.e(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() < 1)) {
      return localH;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
    paramMap = paramMap.iterator();
    while (paramMap.hasNext())
    {
      Object localObject = paramMap.next();
      if (a((Map)localObject, paramContext)) {
        paramContext = i.a((Map)localObject, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localH.a = bool;
      localH.b = paramContext;
      return localH;
      paramContext = "";
    }
  }
  
  private static void b(Map paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = i.f(paramMap, new String[] { "Data" });
      com.ihs.commons.i.g.b("mergeRegions(), main data = " + localMap1);
      Map localMap2 = i.f(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = i.f(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = i.f(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map)localObject;
        if (localObject == null) {
          paramMap = i.f(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = i.f(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          i.a(localMap1, i.f(paramMap, new String[] { "Data" }));
          return;
        }
      }
    }
  }
  
  private void c(Map paramMap)
  {
    if (paramMap == null) {}
    label214:
    label215:
    for (;;)
    {
      return;
      Map localMap2 = i.f(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        int j = Integer.MAX_VALUE;
        Iterator localIterator = localMap2.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = str.replace(" ", "");
          if (a.matcher((CharSequence)localObject).matches())
          {
            localObject = ((String)localObject).split("-");
            if (2 == localObject.length)
            {
              int k = Integer.valueOf(localObject[0]).intValue();
              int m = Integer.valueOf(localObject[1]).intValue();
              if (k <= m)
              {
                if ((b < k) || (b > m) || (k >= j)) {
                  break label214;
                }
                localMap1 = i.f(localMap2, new String[] { str, "Data" });
                j = k;
              }
            }
          }
        }
        for (;;)
        {
          break;
          if (localMap1 == null) {
            break label215;
          }
          i.a(i.f(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  private void f()
  {
    Map localMap = a(this.d, this.f, this.g);
    if (localMap == null) {
      return;
    }
    b(localMap);
    c(localMap);
    try
    {
      this.c = i.f(localMap, new String[] { "Data" });
      this.h = b(this.d, this.c);
      return;
    }
    finally {}
  }
  
  public int a()
  {
    return b;
  }
  
  public void a(g paramG)
  {
    com.ihs.commons.i.g.b("fetch()");
    if (TextUtils.isEmpty(this.e))
    {
      com.ihs.commons.i.g.b("fetch(), plist url is null or empty");
      paramG.a(false);
      return;
    }
    if (this.i != null)
    {
      paramG.a(false);
      return;
    }
    this.i = new Thread(new f(this, paramG));
    this.i.start();
  }
  
  public Map b()
  {
    return this.c;
  }
  
  public boolean c()
  {
    if (this.h == null) {
      return false;
    }
    return this.h.a;
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  public String d()
  {
    if (this.h == null) {
      return "";
    }
    return this.h.b;
  }
  
  public void e()
  {
    if ((!TextUtils.isEmpty(this.f)) && (this.d.getFilesDir() != null))
    {
      File localFile = new File(this.d.getFilesDir().getPath(), this.f);
      if (localFile.exists()) {
        localFile.delete();
      }
    }
  }
}
