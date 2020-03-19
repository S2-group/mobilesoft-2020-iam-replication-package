package com.ihs.a.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.ihs.a.h.d;
import com.ihs.a.h.e;
import com.ihs.a.h.g;
import com.ihs.a.h.h;
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

class c
{
  private static final Pattern a = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static int b = -1;
  private Map<String, ?> c;
  private Context d;
  private String e;
  private String f;
  private String g;
  private b h;
  private Thread i;
  
  public c(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    d.a("assetFileName=" + paramString2 + "\rremotePlistUrl=" + paramString1 + "\rdeleteCachedFile=" + paramBoolean);
    this.d = paramContext;
    this.g = paramString2;
    this.e = paramString1;
    if (!g.a(this.g)) {
      d.d("Using unencrypted plist file is not allowed, please use PA instead !!! " + this.g);
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
      b = h.a(paramContext).a("hs.commons.config.Test_User_Token", -1);
    }
    if (-1 == b)
    {
      b = new Random(System.currentTimeMillis()).nextInt(1000);
      h.a(paramContext).b("hs.commons.config.Test_User_Token", b);
    }
    if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.f))) {
      f();
    }
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
    //   7: invokestatic 92	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   10: ifne +122 -> 132
    //   13: aload_0
    //   14: invokevirtual 155	android/content/Context:getFilesDir	()Ljava/io/File;
    //   17: ifnull +115 -> 132
    //   20: new 157	java/io/File
    //   23: dup
    //   24: aload_0
    //   25: invokevirtual 155	android/content/Context:getFilesDir	()Ljava/io/File;
    //   28: invokevirtual 160	java/io/File:getPath	()Ljava/lang/String;
    //   31: aload_1
    //   32: invokespecial 163	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   35: astore 6
    //   37: aload 6
    //   39: invokevirtual 167	java/io/File:exists	()Z
    //   42: istore_3
    //   43: iload_3
    //   44: ifeq +88 -> 132
    //   47: new 169	java/io/FileInputStream
    //   50: dup
    //   51: aload 6
    //   53: invokespecial 172	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   56: astore 6
    //   58: aload_1
    //   59: astore 4
    //   61: aload 6
    //   63: astore_1
    //   64: aload_1
    //   65: ifnonnull +80 -> 145
    //   68: aload_2
    //   69: invokestatic 92	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   72: ifne +73 -> 145
    //   75: aload_0
    //   76: invokevirtual 176	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   79: astore_0
    //   80: aload_0
    //   81: aload_2
    //   82: invokevirtual 182	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
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
    //   99: invokestatic 92	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   102: ifne +15 -> 117
    //   105: aload_1
    //   106: aload_2
    //   107: invokevirtual 185	java/lang/String:trim	()Ljava/lang/String;
    //   110: invokestatic 82	com/ihs/a/h/g:a	(Ljava/lang/String;)Z
    //   113: invokestatic 188	com/ihs/a/h/g:a	(Ljava/io/InputStream;Z)Ljava/util/Map;
    //   116: astore_0
    //   117: aload_1
    //   118: ifnull +7 -> 125
    //   121: aload_1
    //   122: invokevirtual 193	java/io/InputStream:close	()V
    //   125: aload_0
    //   126: areturn
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 196	java/io/FileNotFoundException:printStackTrace	()V
    //   132: aconst_null
    //   133: astore 4
    //   135: aconst_null
    //   136: astore_1
    //   137: goto -73 -> 64
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 197	java/io/IOException:printStackTrace	()V
    //   145: aload 4
    //   147: astore_2
    //   148: goto -60 -> 88
    //   151: astore_1
    //   152: aload_1
    //   153: invokevirtual 197	java/io/IOException:printStackTrace	()V
    //   156: aload_0
    //   157: areturn
    //   158: astore_0
    //   159: aload 4
    //   161: astore_1
    //   162: aload_1
    //   163: ifnull +7 -> 170
    //   166: aload_1
    //   167: invokevirtual 193	java/io/InputStream:close	()V
    //   170: aload_0
    //   171: athrow
    //   172: astore_1
    //   173: aload_1
    //   174: invokevirtual 197	java/io/IOException:printStackTrace	()V
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
  
  private static boolean a(Map<String, ?> paramMap, List<ApplicationInfo> paramList)
  {
    if (paramMap.isEmpty()) {
      return false;
    }
    int j = TimeZone.getDefault().getRawOffset() / 3600000;
    Object localObject1 = e.d(paramMap, new String[] { "TimeZone" });
    if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!String.valueOf(j).equalsIgnoreCase((String)localObject1))) {
      return false;
    }
    localObject1 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    Object localObject2 = e.d(paramMap, new String[] { "RegionFormat" });
    if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!((String)localObject1).equalsIgnoreCase((String)localObject2))) {
      return false;
    }
    paramMap = e.f(paramMap, new String[] { "UrlScheme" });
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
  
  private static b b(Context paramContext, Map<String, ?> paramMap)
  {
    b localB = new b();
    if (paramMap == null) {
      return localB;
    }
    paramMap = e.f(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() < 1)) {
      return localB;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
    paramMap = paramMap.iterator();
    while (paramMap.hasNext())
    {
      Object localObject = paramMap.next();
      if (a((Map)localObject, paramContext)) {
        paramContext = e.a((Map)localObject, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localB.a = bool;
      localB.b = paramContext;
      return localB;
      paramContext = "";
    }
  }
  
  private static void b(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = e.g(paramMap, new String[] { "Data" });
      d.b("mergeRegions(), main data = " + localMap1);
      Map localMap2 = e.g(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = e.g(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = e.g(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map<String, ?>)localObject;
        if (localObject == null) {
          paramMap = e.g(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = e.g(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          e.a(localMap1, e.g(paramMap, new String[] { "Data" }));
          return;
        }
      }
    }
  }
  
  private void c(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    label214:
    label215:
    for (;;)
    {
      return;
      Map localMap2 = e.g(paramMap, new String[] { "Segments" });
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
                localMap1 = e.g(localMap2, new String[] { str, "Data" });
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
          e.a(e.g(paramMap, new String[] { "Data" }), localMap1);
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
      this.c = e.g(localMap, new String[] { "Data" });
      this.h = b(this.d, this.c);
      return;
    }
    finally {}
  }
  
  public int a()
  {
    return b;
  }
  
  public void a(final a paramA)
  {
    d.b("fetch()");
    if (TextUtils.isEmpty(this.e))
    {
      d.b("fetch(), plist url is null or empty");
      paramA.a(false);
      return;
    }
    if (this.i != null)
    {
      paramA.a(false);
      return;
    }
    this.i = new Thread(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_1
        //   2: aload_0
        //   3: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   6: invokestatic 28	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;)Landroid/content/Context;
        //   9: invokevirtual 34	android/content/Context:getFilesDir	()Ljava/io/File;
        //   12: ifnonnull +23 -> 35
        //   15: aload_0
        //   16: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/c$a;
        //   19: iconst_0
        //   20: invokeinterface 39 2 0
        //   25: aload_0
        //   26: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   29: aconst_null
        //   30: invokestatic 42	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   33: pop
        //   34: return
        //   35: aload_0
        //   36: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   39: invokestatic 28	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;)Landroid/content/Context;
        //   42: invokevirtual 34	android/content/Context:getFilesDir	()Ljava/io/File;
        //   45: invokevirtual 48	java/io/File:getPath	()Ljava/lang/String;
        //   48: astore_2
        //   49: new 44	java/io/File
        //   52: dup
        //   53: aload_2
        //   54: invokespecial 51	java/io/File:<init>	(Ljava/lang/String;)V
        //   57: astore_3
        //   58: aload_3
        //   59: invokevirtual 55	java/io/File:exists	()Z
        //   62: ifne +8 -> 70
        //   65: aload_3
        //   66: invokevirtual 58	java/io/File:mkdir	()Z
        //   69: pop
        //   70: new 44	java/io/File
        //   73: dup
        //   74: aload_2
        //   75: new 60	java/lang/StringBuilder
        //   78: dup
        //   79: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   82: ldc 63
        //   84: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   87: aload_0
        //   88: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   91: invokestatic 70	com/ihs/a/b/c:b	(Lcom/ihs/a/b/c;)Ljava/lang/String;
        //   94: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   97: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   100: invokespecial 76	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   103: astore_3
        //   104: new 78	com/ihs/a/c/a
        //   107: dup
        //   108: aload_0
        //   109: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   112: invokestatic 81	com/ihs/a/b/c:c	(Lcom/ihs/a/b/c;)Ljava/lang/String;
        //   115: invokespecial 82	com/ihs/a/c/a:<init>	(Ljava/lang/String;)V
        //   118: astore 4
        //   120: aload 4
        //   122: sipush 10000
        //   125: invokevirtual 85	com/ihs/a/c/a:a	(I)Lcom/ihs/a/c/a;
        //   128: sipush 30000
        //   131: invokevirtual 87	com/ihs/a/c/a:b	(I)Lcom/ihs/a/c/a;
        //   134: pop
        //   135: aload 4
        //   137: aload_3
        //   138: invokevirtual 90	com/ihs/a/c/a:a	(Ljava/io/File;)Lcom/ihs/a/c/a;
        //   141: pop
        //   142: aload 4
        //   144: invokevirtual 92	com/ihs/a/c/a:a	()V
        //   147: aload 4
        //   149: invokevirtual 95	com/ihs/a/c/a:e	()Z
        //   152: ifne +23 -> 175
        //   155: aload_0
        //   156: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/c$a;
        //   159: iconst_0
        //   160: invokeinterface 39 2 0
        //   165: aload_0
        //   166: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   169: aconst_null
        //   170: invokestatic 42	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   173: pop
        //   174: return
        //   175: aload_3
        //   176: invokestatic 100	com/ihs/a/h/g:a	(Ljava/io/File;)Ljava/util/Map;
        //   179: astore 4
        //   181: aload 4
        //   183: invokestatic 103	com/ihs/a/b/c:a	(Ljava/util/Map;)V
        //   186: aload_0
        //   187: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   190: aload 4
        //   192: invokestatic 106	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/util/Map;)V
        //   195: aload 4
        //   197: iconst_1
        //   198: anewarray 108	java/lang/String
        //   201: dup
        //   202: iconst_0
        //   203: ldc 110
        //   205: aastore
        //   206: invokestatic 116	com/ihs/a/h/e:g	(Ljava/util/Map;[Ljava/lang/String;)Ljava/util/Map;
        //   209: astore 4
        //   211: aload 4
        //   213: ifnonnull +28 -> 241
        //   216: ldc 118
        //   218: invokestatic 122	com/ihs/a/h/d:b	(Ljava/lang/String;)V
        //   221: aload_0
        //   222: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/c$a;
        //   225: iconst_0
        //   226: invokeinterface 39 2 0
        //   231: aload_0
        //   232: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   235: aconst_null
        //   236: invokestatic 42	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   239: pop
        //   240: return
        //   241: new 44	java/io/File
        //   244: dup
        //   245: aload_2
        //   246: aload_0
        //   247: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   250: invokestatic 70	com/ihs/a/b/c:b	(Lcom/ihs/a/b/c;)Ljava/lang/String;
        //   253: invokespecial 76	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   256: astore_2
        //   257: aload_2
        //   258: invokevirtual 55	java/io/File:exists	()Z
        //   261: ifeq +8 -> 269
        //   264: aload_2
        //   265: invokevirtual 125	java/io/File:delete	()Z
        //   268: pop
        //   269: aload_3
        //   270: aload_2
        //   271: invokevirtual 129	java/io/File:renameTo	(Ljava/io/File;)Z
        //   274: ifne +28 -> 302
        //   277: ldc -125
        //   279: invokestatic 122	com/ihs/a/h/d:b	(Ljava/lang/String;)V
        //   282: aload_0
        //   283: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/c$a;
        //   286: iconst_0
        //   287: invokeinterface 39 2 0
        //   292: aload_0
        //   293: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   296: aconst_null
        //   297: invokestatic 42	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   300: pop
        //   301: return
        //   302: aload_0
        //   303: monitorenter
        //   304: aload_0
        //   305: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   308: invokestatic 135	com/ihs/a/b/c:d	(Lcom/ihs/a/b/c;)Ljava/util/Map;
        //   311: ifnull +20 -> 331
        //   314: aload_0
        //   315: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   318: invokestatic 135	com/ihs/a/b/c:d	(Lcom/ihs/a/b/c;)Ljava/util/Map;
        //   321: aload 4
        //   323: invokeinterface 141 2 0
        //   328: ifne +35 -> 363
        //   331: aload_0
        //   332: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   335: aload 4
        //   337: invokestatic 144	com/ihs/a/b/c:b	(Lcom/ihs/a/b/c;Ljava/util/Map;)Ljava/util/Map;
        //   340: pop
        //   341: aload_0
        //   342: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   345: aload_0
        //   346: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   349: invokestatic 28	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;)Landroid/content/Context;
        //   352: aload 4
        //   354: invokestatic 147	com/ihs/a/b/c:a	(Landroid/content/Context;Ljava/util/Map;)Lcom/ihs/a/b/c$b;
        //   357: invokestatic 150	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Lcom/ihs/a/b/c$b;)Lcom/ihs/a/b/c$b;
        //   360: pop
        //   361: iconst_1
        //   362: istore_1
        //   363: aload_0
        //   364: monitorexit
        //   365: aload_0
        //   366: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/c$a;
        //   369: iload_1
        //   370: invokeinterface 39 2 0
        //   375: aload_0
        //   376: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   379: aconst_null
        //   380: invokestatic 42	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   383: pop
        //   384: return
        //   385: astore_2
        //   386: aload_0
        //   387: monitorexit
        //   388: aload_2
        //   389: athrow
        //   390: astore_2
        //   391: aload_0
        //   392: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   395: aconst_null
        //   396: invokestatic 42	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   399: pop
        //   400: aload_2
        //   401: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	402	0	this	1
        //   1	369	1	bool	boolean
        //   48	223	2	localObject1	Object
        //   385	4	2	localObject2	Object
        //   390	11	2	localObject3	Object
        //   57	213	3	localFile	File
        //   118	235	4	localObject4	Object
        // Exception table:
        //   from	to	target	type
        //   304	314	385	finally
        //   314	331	385	finally
        //   331	361	385	finally
        //   363	365	385	finally
        //   386	388	385	finally
        //   2	25	390	finally
        //   35	70	390	finally
        //   70	165	390	finally
        //   175	211	390	finally
        //   216	231	390	finally
        //   241	269	390	finally
        //   269	292	390	finally
        //   302	304	390	finally
        //   365	375	390	finally
        //   388	390	390	finally
      }
    });
    this.i.start();
  }
  
  public Map<String, ?> b()
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
    throws CloneNotSupportedException
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
  
  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean);
  }
  
  private static class b
  {
    public boolean a = false;
    public String b = "";
    
    public b() {}
  }
}
