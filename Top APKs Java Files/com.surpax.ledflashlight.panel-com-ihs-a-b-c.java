package com.ihs.a.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.ihs.a.e.g;
import com.ihs.a.e.i;
import com.ihs.a.e.j;
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

final class c
{
  private static final Pattern a = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private Map b;
  private Context c;
  private String d;
  private String e;
  private String f;
  private e g;
  private Thread h;
  
  public c(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    new StringBuilder("assetFileName=").append(paramString2).append("\rremotePlistUrl=").append(paramString1).append("\rdeleteCachedFile=").append(paramBoolean).toString();
    this.c = paramContext;
    this.f = paramString2;
    this.d = paramString1;
    if (!i.a(this.f)) {
      new StringBuilder("Using unencrypted plist file is not allowed, please use PA instead !!! ").append(this.f).toString();
    }
    if (!TextUtils.isEmpty(paramString1))
    {
      paramContext = paramString1.split("/");
      this.e = paramContext[(paramContext.length - 1)];
    }
    if ((paramBoolean) && (!TextUtils.isEmpty(this.e)))
    {
      paramContext = new File(this.c.getFilesDir().getPath(), this.e);
      if (paramContext.exists()) {
        paramContext.delete();
      }
    }
    if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.e)))
    {
      paramContext = a(this.c, this.e, this.f);
      if (paramContext != null) {}
    }
    else
    {
      return;
    }
    b(paramContext);
    c(paramContext);
    try
    {
      this.b = g.e(paramContext, new String[] { "Data" });
      this.g = b(this.c, this.b);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
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
    //   7: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   10: ifne +115 -> 125
    //   13: new 90	java/io/File
    //   16: dup
    //   17: aload_0
    //   18: invokevirtual 96	android/content/Context:getFilesDir	()Ljava/io/File;
    //   21: invokevirtual 99	java/io/File:getPath	()Ljava/lang/String;
    //   24: aload_1
    //   25: invokespecial 102	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: astore 6
    //   30: aload 6
    //   32: invokevirtual 106	java/io/File:exists	()Z
    //   35: istore_3
    //   36: iload_3
    //   37: ifeq +88 -> 125
    //   40: new 142	java/io/FileInputStream
    //   43: dup
    //   44: aload 6
    //   46: invokespecial 145	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   49: astore 6
    //   51: aload_1
    //   52: astore 4
    //   54: aload 6
    //   56: astore_1
    //   57: aload_1
    //   58: ifnonnull +80 -> 138
    //   61: aload_2
    //   62: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   65: ifne +73 -> 138
    //   68: aload_0
    //   69: invokevirtual 149	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   72: astore_0
    //   73: aload_0
    //   74: aload_2
    //   75: invokevirtual 155	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   78: astore_0
    //   79: aload_0
    //   80: astore_1
    //   81: aload 5
    //   83: astore_0
    //   84: aload_1
    //   85: ifnull +25 -> 110
    //   88: aload 5
    //   90: astore_0
    //   91: aload_2
    //   92: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   95: ifne +15 -> 110
    //   98: aload_1
    //   99: aload_2
    //   100: invokevirtual 158	java/lang/String:trim	()Ljava/lang/String;
    //   103: invokestatic 70	com/ihs/a/e/i:a	(Ljava/lang/String;)Z
    //   106: invokestatic 161	com/ihs/a/e/i:a	(Ljava/io/InputStream;Z)Ljava/util/Map;
    //   109: astore_0
    //   110: aload_1
    //   111: ifnull +7 -> 118
    //   114: aload_1
    //   115: invokevirtual 166	java/io/InputStream:close	()V
    //   118: aload_0
    //   119: areturn
    //   120: astore_1
    //   121: aload_1
    //   122: invokevirtual 169	java/io/FileNotFoundException:printStackTrace	()V
    //   125: aconst_null
    //   126: astore 4
    //   128: aconst_null
    //   129: astore_1
    //   130: goto -73 -> 57
    //   133: astore_0
    //   134: aload_0
    //   135: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   138: aload 4
    //   140: astore_2
    //   141: goto -60 -> 81
    //   144: astore_1
    //   145: aload_1
    //   146: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   149: aload_0
    //   150: areturn
    //   151: astore_0
    //   152: aload 4
    //   154: astore_1
    //   155: aload_1
    //   156: ifnull +7 -> 163
    //   159: aload_1
    //   160: invokevirtual 166	java/io/InputStream:close	()V
    //   163: aload_0
    //   164: athrow
    //   165: astore_1
    //   166: aload_1
    //   167: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   170: goto -7 -> 163
    //   173: astore_0
    //   174: goto -19 -> 155
    //   177: astore_0
    //   178: goto -23 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	paramContext	Context
    //   0	181	1	paramString1	String
    //   0	181	2	paramString2	String
    //   35	2	3	bool	boolean
    //   1	152	4	str	String
    //   4	85	5	localObject1	Object
    //   28	27	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   40	51	120	java/io/FileNotFoundException
    //   73	79	133	java/io/IOException
    //   114	118	144	java/io/IOException
    //   6	36	151	finally
    //   40	51	151	finally
    //   121	125	151	finally
    //   159	163	165	java/io/IOException
    //   61	73	173	finally
    //   73	79	173	finally
    //   134	138	173	finally
    //   91	110	177	finally
  }
  
  private static e b(Context paramContext, Map paramMap)
  {
    e localE = new e();
    if (paramMap == null) {
      return localE;
    }
    paramMap = g.d(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() <= 0)) {
      return localE;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
    paramMap = paramMap.iterator();
    Object localObject2;
    int i;
    if (paramMap.hasNext())
    {
      Object localObject1 = paramMap.next();
      localObject2 = (Map)localObject1;
      if (((Map)localObject2).isEmpty())
      {
        i = 0;
        label100:
        if (i == 0) {
          break label389;
        }
        paramContext = g.a((Map)localObject1, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localE.a = bool;
      localE.b = paramContext;
      return localE;
      i = TimeZone.getDefault().getRawOffset() / 3600000;
      Object localObject3 = g.c((Map)localObject2, new String[] { "TimeZone" });
      if ((!TextUtils.isEmpty((CharSequence)localObject3)) && (!String.valueOf(i).equalsIgnoreCase((String)localObject3)))
      {
        i = 0;
        break label100;
      }
      localObject3 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
      Object localObject4 = g.c((Map)localObject2, new String[] { "RegionFormat" });
      if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject3).equalsIgnoreCase((String)localObject4)))
      {
        i = 0;
        break label100;
      }
      localObject2 = g.d((Map)localObject2, new String[] { "UrlScheme" });
      if ((localObject2 == null) || (((List)localObject2).isEmpty()))
      {
        i = 1;
        break label100;
      }
      localObject2 = ((List)localObject2).iterator();
      label389:
      label394:
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
        for (i = 1;; i = 0)
        {
          if (i != 0) {
            break label394;
          }
          i = 0;
          break label100;
          i = 1;
          break label100;
          break;
        }
      }
      paramContext = "";
    }
  }
  
  private static void b(Map paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = g.e(paramMap, new String[] { "Data" });
      new StringBuilder("mergeRegions(), main data = ").append(localMap1).toString();
      Map localMap2 = g.e(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = g.e(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = g.e(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map)localObject;
        if (localObject == null) {
          paramMap = g.e(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = g.e(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          g.a(localMap1, g.e(paramMap, new String[] { "Data" }));
          return;
        }
      }
    }
  }
  
  private void c(Map paramMap)
  {
    if (paramMap == null) {}
    label265:
    label266:
    for (;;)
    {
      return;
      int i = j.a(this.c).a("hs.commons.config.Test_User_Token", -1);
      int j = i;
      if (-1 == i)
      {
        j = new Random(System.currentTimeMillis()).nextInt(1000);
        j.a(this.c).b("hs.commons.config.Test_User_Token", j);
      }
      Map localMap2 = g.e(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        i = Integer.MAX_VALUE;
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
                if ((j < k) || (j > m) || (k >= i)) {
                  break label265;
                }
                localMap1 = g.e(localMap2, new String[] { str, "Data" });
                i = k;
              }
            }
          }
        }
        for (;;)
        {
          break;
          if (localMap1 == null) {
            break label266;
          }
          g.a(g.e(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  public final Map a()
  {
    return this.b;
  }
  
  public final void a(final d paramD)
  {
    if (TextUtils.isEmpty(this.d))
    {
      paramD.a(false);
      return;
    }
    if (this.h != null)
    {
      paramD.a(false);
      return;
    }
    this.h = new Thread(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   4: invokestatic 32	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;)Landroid/content/Context;
        //   7: invokevirtual 38	android/content/Context:getFilesDir	()Ljava/io/File;
        //   10: invokevirtual 44	java/io/File:getPath	()Ljava/lang/String;
        //   13: astore 7
        //   15: new 40	java/io/File
        //   18: dup
        //   19: aload 7
        //   21: invokespecial 47	java/io/File:<init>	(Ljava/lang/String;)V
        //   24: astore 4
        //   26: aload 4
        //   28: invokevirtual 51	java/io/File:exists	()Z
        //   31: ifne +9 -> 40
        //   34: aload 4
        //   36: invokevirtual 54	java/io/File:mkdir	()Z
        //   39: pop
        //   40: new 40	java/io/File
        //   43: dup
        //   44: aload 7
        //   46: new 56	java/lang/StringBuilder
        //   49: dup
        //   50: ldc 58
        //   52: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   55: aload_0
        //   56: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   59: invokestatic 62	com/ihs/a/b/c:b	(Lcom/ihs/a/b/c;)Ljava/lang/String;
        //   62: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   65: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   68: invokespecial 72	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   71: astore 8
        //   73: aload_0
        //   74: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   77: invokestatic 75	com/ihs/a/b/c:c	(Lcom/ihs/a/b/c;)Ljava/lang/String;
        //   80: invokestatic 80	com/ihs/a/c/b/c:a	(Ljava/lang/CharSequence;)Lcom/ihs/a/c/b/c;
        //   83: sipush 10000
        //   86: invokevirtual 83	com/ihs/a/c/b/c:b	(I)Lcom/ihs/a/c/b/c;
        //   89: sipush 30000
        //   92: invokevirtual 85	com/ihs/a/c/b/c:a	(I)Lcom/ihs/a/c/b/c;
        //   95: astore 5
        //   97: aload 5
        //   99: astore 4
        //   101: aload 5
        //   103: invokevirtual 87	com/ihs/a/c/b/c:b	()Z
        //   106: ifeq +381 -> 487
        //   109: aload 5
        //   111: astore 4
        //   113: aload 5
        //   115: aload 8
        //   117: invokevirtual 90	com/ihs/a/c/b/c:a	(Ljava/io/File;)Lcom/ihs/a/c/b/c;
        //   120: pop
        //   121: iconst_1
        //   122: istore_2
        //   123: iload_2
        //   124: istore_1
        //   125: aload 5
        //   127: ifnull +11 -> 138
        //   130: aload 5
        //   132: invokevirtual 94	com/ihs/a/c/b/c:d	()Lcom/ihs/a/c/b/c;
        //   135: pop
        //   136: iload_2
        //   137: istore_1
        //   138: iload_1
        //   139: ifne +111 -> 250
        //   142: aload_0
        //   143: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/d;
        //   146: iconst_0
        //   147: invokeinterface 99 2 0
        //   152: aload_0
        //   153: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   156: aconst_null
        //   157: invokestatic 102	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   160: pop
        //   161: return
        //   162: astore 6
        //   164: aconst_null
        //   165: astore 5
        //   167: aload 5
        //   169: astore 4
        //   171: aload 6
        //   173: invokevirtual 105	com/ihs/a/c/b/f:printStackTrace	()V
        //   176: aload 5
        //   178: ifnull +304 -> 482
        //   181: aload 5
        //   183: invokevirtual 94	com/ihs/a/c/b/c:d	()Lcom/ihs/a/c/b/c;
        //   186: pop
        //   187: iconst_0
        //   188: istore_1
        //   189: goto -51 -> 138
        //   192: astore 6
        //   194: aconst_null
        //   195: astore 5
        //   197: aload 5
        //   199: astore 4
        //   201: aload 6
        //   203: invokevirtual 106	java/lang/Exception:printStackTrace	()V
        //   206: aload 5
        //   208: ifnull +274 -> 482
        //   211: aload 5
        //   213: invokevirtual 94	com/ihs/a/c/b/c:d	()Lcom/ihs/a/c/b/c;
        //   216: pop
        //   217: iconst_0
        //   218: istore_1
        //   219: goto -81 -> 138
        //   222: aload 4
        //   224: ifnull +9 -> 233
        //   227: aload 4
        //   229: invokevirtual 94	com/ihs/a/c/b/c:d	()Lcom/ihs/a/c/b/c;
        //   232: pop
        //   233: aload 5
        //   235: athrow
        //   236: astore 4
        //   238: aload_0
        //   239: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   242: aconst_null
        //   243: invokestatic 102	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   246: pop
        //   247: aload 4
        //   249: athrow
        //   250: aload 8
        //   252: invokestatic 111	com/ihs/a/e/i:a	(Ljava/io/File;)Ljava/util/Map;
        //   255: astore 4
        //   257: aload 4
        //   259: invokestatic 114	com/ihs/a/b/c:a	(Ljava/util/Map;)V
        //   262: aload_0
        //   263: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   266: aload 4
        //   268: invokestatic 117	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/util/Map;)V
        //   271: aload 4
        //   273: iconst_1
        //   274: anewarray 119	java/lang/String
        //   277: dup
        //   278: iconst_0
        //   279: ldc 121
        //   281: aastore
        //   282: invokestatic 127	com/ihs/a/e/g:e	(Ljava/util/Map;[Ljava/lang/String;)Ljava/util/Map;
        //   285: astore 4
        //   287: aload 4
        //   289: ifnonnull +23 -> 312
        //   292: aload_0
        //   293: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/d;
        //   296: iconst_0
        //   297: invokeinterface 99 2 0
        //   302: aload_0
        //   303: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   306: aconst_null
        //   307: invokestatic 102	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   310: pop
        //   311: return
        //   312: new 40	java/io/File
        //   315: dup
        //   316: aload 7
        //   318: aload_0
        //   319: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   322: invokestatic 62	com/ihs/a/b/c:b	(Lcom/ihs/a/b/c;)Ljava/lang/String;
        //   325: invokespecial 72	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   328: astore 5
        //   330: aload 5
        //   332: invokevirtual 51	java/io/File:exists	()Z
        //   335: ifeq +9 -> 344
        //   338: aload 5
        //   340: invokevirtual 130	java/io/File:delete	()Z
        //   343: pop
        //   344: aload 8
        //   346: aload 5
        //   348: invokevirtual 134	java/io/File:renameTo	(Ljava/io/File;)Z
        //   351: ifne +23 -> 374
        //   354: aload_0
        //   355: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/d;
        //   358: iconst_0
        //   359: invokeinterface 99 2 0
        //   364: aload_0
        //   365: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   368: aconst_null
        //   369: invokestatic 102	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   372: pop
        //   373: return
        //   374: aload_0
        //   375: monitorenter
        //   376: aload_0
        //   377: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   380: invokestatic 137	com/ihs/a/b/c:d	(Lcom/ihs/a/b/c;)Ljava/util/Map;
        //   383: ifnull +18 -> 401
        //   386: aload_0
        //   387: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   390: invokestatic 137	com/ihs/a/b/c:d	(Lcom/ihs/a/b/c;)Ljava/util/Map;
        //   393: aload 4
        //   395: invokevirtual 141	java/lang/Object:equals	(Ljava/lang/Object;)Z
        //   398: ifne +79 -> 477
        //   401: aload_0
        //   402: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   405: aload 4
        //   407: invokestatic 144	com/ihs/a/b/c:b	(Lcom/ihs/a/b/c;Ljava/util/Map;)Ljava/util/Map;
        //   410: pop
        //   411: aload_0
        //   412: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   415: aload_0
        //   416: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   419: invokestatic 32	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;)Landroid/content/Context;
        //   422: aload 4
        //   424: invokestatic 147	com/ihs/a/b/c:a	(Landroid/content/Context;Ljava/util/Map;)Lcom/ihs/a/b/e;
        //   427: invokestatic 150	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Lcom/ihs/a/b/e;)Lcom/ihs/a/b/e;
        //   430: pop
        //   431: iconst_1
        //   432: istore_3
        //   433: aload_0
        //   434: monitorexit
        //   435: aload_0
        //   436: getfield 20	com/ihs/a/b/c$1:a	Lcom/ihs/a/b/d;
        //   439: iload_3
        //   440: invokeinterface 99 2 0
        //   445: aload_0
        //   446: getfield 18	com/ihs/a/b/c$1:b	Lcom/ihs/a/b/c;
        //   449: aconst_null
        //   450: invokestatic 102	com/ihs/a/b/c:a	(Lcom/ihs/a/b/c;Ljava/lang/Thread;)Ljava/lang/Thread;
        //   453: pop
        //   454: return
        //   455: astore 4
        //   457: aload_0
        //   458: monitorexit
        //   459: aload 4
        //   461: athrow
        //   462: astore 5
        //   464: goto -242 -> 222
        //   467: astore 6
        //   469: goto -272 -> 197
        //   472: astore 6
        //   474: goto -307 -> 167
        //   477: iconst_0
        //   478: istore_3
        //   479: goto -46 -> 433
        //   482: iconst_0
        //   483: istore_1
        //   484: goto -346 -> 138
        //   487: iconst_0
        //   488: istore_2
        //   489: goto -366 -> 123
        //   492: astore 5
        //   494: aconst_null
        //   495: astore 4
        //   497: goto -275 -> 222
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	500	0	this	1
        //   124	360	1	i	int
        //   122	367	2	j	int
        //   432	47	3	bool	boolean
        //   24	204	4	localObject1	Object
        //   236	12	4	localObject2	Object
        //   255	168	4	localMap	Map
        //   455	5	4	localObject3	Object
        //   495	1	4	localObject4	Object
        //   95	252	5	localObject5	Object
        //   462	1	5	localObject6	Object
        //   492	1	5	localObject7	Object
        //   162	10	6	localF1	com.ihs.a.c.b.f
        //   192	10	6	localException1	Exception
        //   467	1	6	localException2	Exception
        //   472	1	6	localF2	com.ihs.a.c.b.f
        //   13	304	7	str	String
        //   71	274	8	localFile	File
        // Exception table:
        //   from	to	target	type
        //   73	97	162	com/ihs/a/c/b/f
        //   73	97	192	java/lang/Exception
        //   0	40	236	finally
        //   40	73	236	finally
        //   130	136	236	finally
        //   142	152	236	finally
        //   181	187	236	finally
        //   211	217	236	finally
        //   227	233	236	finally
        //   233	236	236	finally
        //   250	287	236	finally
        //   292	302	236	finally
        //   312	344	236	finally
        //   344	364	236	finally
        //   374	376	236	finally
        //   435	445	236	finally
        //   457	462	236	finally
        //   376	401	455	finally
        //   401	431	455	finally
        //   433	435	455	finally
        //   101	109	462	finally
        //   113	121	462	finally
        //   171	176	462	finally
        //   201	206	462	finally
        //   101	109	467	java/lang/Exception
        //   113	121	467	java/lang/Exception
        //   101	109	472	com/ihs/a/c/b/f
        //   113	121	472	com/ihs/a/c/b/f
        //   73	97	492	finally
      }
    });
    this.h.start();
  }
  
  public final boolean b()
  {
    if (this.g == null) {
      return false;
    }
    return this.g.a;
  }
  
  public final String c()
  {
    if (this.g == null) {
      return "";
    }
    return this.g.b;
  }
  
  protected final Object clone()
  {
    throw new CloneNotSupportedException();
  }
}
