package com.ihs.commons.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.text.TextUtils;
import com.ihs.commons.a.a;
import com.ihs.commons.analytics.publisher.b.a;
import com.ihs.commons.analytics.publisher.b.a.b;
import com.ihs.commons.e.f;
import com.ihs.commons.e.g;
import com.ihs.commons.e.i;
import com.ihs.commons.e.j;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

class c
{
  private static final String a = c.class.getSimpleName();
  private static final Pattern b = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static int c = -1;
  private static final Map<String, Integer> d = new HashMap();
  private Map<String, ?> e;
  private Context f;
  private String g;
  private String h;
  private String i;
  private String j;
  private c k;
  private Thread l;
  private b m;
  
  static
  {
    if (f.a())
    {
      d.put("OrganicDebug", Integer.valueOf(3));
      d.put("NotOrganicDebug", Integer.valueOf(8));
      return;
    }
    d.put("OrganicEastEightDistrictRelease", Integer.valueOf(1));
    d.put("OrganicNotEastEightDistrictRelease", Integer.valueOf(3));
    d.put("NotOrganicEastEightDistrictRelease", Integer.valueOf(5));
    d.put("NotOrganicNotEastEightDistrictRelease", Integer.valueOf(8));
  }
  
  public c(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    f.b("RemoteConfig", "assetFileName=" + paramString2 + "\rremotePlistUrl=" + paramString1 + "\rdeleteCachedFile=" + paramBoolean);
    this.f = paramContext;
    this.i = paramString2;
    this.g = paramString1;
    this.j = paramString3;
    this.m = b.c(paramContext);
    if (!i.a(this.i)) {
      f.e("RemoteConfig", "Using unencrypted plist file is not allowed, please use PA instead !!! " + this.i);
    }
    if (!TextUtils.isEmpty(paramString1))
    {
      paramString1 = paramString1.split("/");
      this.h = paramString1[(paramString1.length - 1)];
    }
    if (paramBoolean)
    {
      this.m.b(paramContext);
      d();
      k();
    }
    if (c == -1) {
      c = j.a(paramContext).b("hs.commons.config.Test_User_Token", -1);
    }
    if (-1 == c)
    {
      c = new Random(System.currentTimeMillis()).nextInt(1000);
      j.a(paramContext).d("hs.commons.config.Test_User_Token", c);
    }
    if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.h))) {
      l();
    }
  }
  
  private int a(b.a paramA)
  {
    if ((paramA == null) || (paramA.d() == null) || (paramA.d() == b.a.b.a) || (paramA.d() == b.a.b.b))
    {
      paramA = b.a.b.b;
      if (!f.a()) {
        break label91;
      }
      if (paramA != b.a.b.b) {
        break label74;
      }
      paramA = (Integer)d.get("OrganicDebug");
    }
    for (;;)
    {
      return paramA.intValue();
      paramA = b.a.b.c;
      break;
      label74:
      paramA = (Integer)d.get("NotOrganicDebug");
      continue;
      label91:
      if (TimeZone.getDefault().getRawOffset() / 3600000 == 8)
      {
        if (paramA == b.a.b.b) {
          paramA = (Integer)d.get("OrganicEastEightDistrictRelease");
        } else {
          paramA = (Integer)d.get("NotOrganicEastEightDistrictRelease");
        }
      }
      else if (paramA == b.a.b.b) {
        paramA = (Integer)d.get("OrganicNotEastEightDistrictRelease");
      } else {
        paramA = (Integer)d.get("NotOrganicNotEastEightDistrictRelease");
      }
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
    //   7: invokestatic 148	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   10: ifne +122 -> 132
    //   13: aload_0
    //   14: invokevirtual 249	android/content/Context:getFilesDir	()Ljava/io/File;
    //   17: ifnull +115 -> 132
    //   20: new 251	java/io/File
    //   23: dup
    //   24: aload_0
    //   25: invokevirtual 249	android/content/Context:getFilesDir	()Ljava/io/File;
    //   28: invokevirtual 254	java/io/File:getPath	()Ljava/lang/String;
    //   31: aload_1
    //   32: invokespecial 256	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   35: astore 6
    //   37: aload 6
    //   39: invokevirtual 259	java/io/File:exists	()Z
    //   42: istore_3
    //   43: iload_3
    //   44: ifeq +88 -> 132
    //   47: new 261	java/io/FileInputStream
    //   50: dup
    //   51: aload 6
    //   53: invokespecial 264	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   56: astore 6
    //   58: aload_1
    //   59: astore 4
    //   61: aload 6
    //   63: astore_1
    //   64: aload_1
    //   65: ifnonnull +80 -> 145
    //   68: aload_2
    //   69: invokestatic 148	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   72: ifne +73 -> 145
    //   75: aload_0
    //   76: invokevirtual 268	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   79: astore_0
    //   80: aload_0
    //   81: aload_2
    //   82: invokevirtual 274	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
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
    //   99: invokestatic 148	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   102: ifne +15 -> 117
    //   105: aload_1
    //   106: aload_2
    //   107: invokevirtual 277	java/lang/String:trim	()Ljava/lang/String;
    //   110: invokestatic 138	com/ihs/commons/e/i:a	(Ljava/lang/String;)Z
    //   113: invokestatic 280	com/ihs/commons/e/i:a	(Ljava/io/InputStream;Z)Ljava/util/Map;
    //   116: astore_0
    //   117: aload_1
    //   118: ifnull +7 -> 125
    //   121: aload_1
    //   122: invokevirtual 285	java/io/InputStream:close	()V
    //   125: aload_0
    //   126: areturn
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 288	java/io/FileNotFoundException:printStackTrace	()V
    //   132: aconst_null
    //   133: astore 4
    //   135: aconst_null
    //   136: astore_1
    //   137: goto -73 -> 64
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 289	java/io/IOException:printStackTrace	()V
    //   145: aload 4
    //   147: astore_2
    //   148: goto -60 -> 88
    //   151: astore_1
    //   152: aload_1
    //   153: invokevirtual 289	java/io/IOException:printStackTrace	()V
    //   156: aload_0
    //   157: areturn
    //   158: astore_0
    //   159: aload 4
    //   161: astore_1
    //   162: aload_1
    //   163: ifnull +7 -> 170
    //   166: aload_1
    //   167: invokevirtual 285	java/io/InputStream:close	()V
    //   170: aload_0
    //   171: athrow
    //   172: astore_1
    //   173: aload_1
    //   174: invokevirtual 289	java/io/IOException:printStackTrace	()V
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
  
  private Map<String, ?> a(Map<String, ?> paramMap, String paramString, int paramInt)
  {
    if ((paramInt < 0) || (paramMap == null) || (TextUtils.isEmpty(paramString)))
    {
      paramString = null;
      return paramString;
    }
    Map localMap = g.h(paramMap, new String[] { paramString });
    paramMap = Pattern.compile("[0-9]+");
    if ((localMap != null) && (a(localMap.keySet())))
    {
      paramString = localMap.keySet().iterator();
      label72:
      String str;
      String[] arrayOfString;
      int n;
      int i1;
      for (;;)
      {
        if (paramString.hasNext())
        {
          str = (String)paramString.next();
          arrayOfString = str.split("-");
          if (arrayOfString.length == 1)
          {
            if ("default".equals(arrayOfString[0])) {
              continue;
            }
            if (!paramMap.matcher(arrayOfString[0].trim()).matches()) {
              break label284;
            }
            n = Integer.valueOf(arrayOfString[0]).intValue();
            i1 = n;
            label155:
            if ((i1 == -1) || (n == -1) || (i1 > paramInt) || (n < paramInt)) {
              break label291;
            }
          }
        }
      }
      for (paramMap = g.h(localMap, new String[] { str });; paramMap = null)
      {
        paramString = paramMap;
        if (paramMap != null) {
          break;
        }
        return g.h(localMap, new String[] { "default" });
        if ((arrayOfString.length == 2) && (paramMap.matcher(arrayOfString[0].trim()).matches()) && (paramMap.matcher(arrayOfString[1].trim()).matches()))
        {
          i1 = Integer.valueOf(arrayOfString[0]).intValue();
          n = Integer.valueOf(arrayOfString[1]).intValue();
          break label155;
        }
        label284:
        n = -1;
        i1 = -1;
        break label155;
        label291:
        break label72;
      }
    }
    return null;
  }
  
  private static Map<String, ?> a(Map<String, ?> paramMap, String paramString, b.a paramA)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramMap != null)
    {
      localObject1 = localObject2;
      if (!TextUtils.isEmpty(paramString))
      {
        if (paramA != null) {
          break label29;
        }
        localObject1 = localObject2;
      }
    }
    label29:
    do
    {
      return localObject1;
      paramString = g.h(paramMap, new String[] { paramString });
      localObject1 = localObject2;
    } while (paramString == null);
    if (paramA.d() == b.a.b.b) {}
    for (paramMap = "Organic";; paramMap = paramA.e())
    {
      paramMap = g.h(paramString, new String[] { paramMap });
      localObject1 = paramMap;
      if (paramMap != null) {
        break;
      }
      return g.h(paramString, new String[] { "Others" });
    }
  }
  
  private void a(Map<String, ?> paramMap)
  {
    b(paramMap);
    c(paramMap);
    Object localObject = com.ihs.commons.analytics.publisher.b.b(this.f);
    if (g.a(paramMap, new String[] { "UserLevel" }))
    {
      int n = a((b.a)localObject);
      localObject = a(paramMap, "UserLevel", n);
      f.b(a, "it has userlevel(" + n + ") config!");
    }
    for (;;)
    {
      b((Map)localObject);
      c((Map)localObject);
      g.a(paramMap, (Map)localObject);
      return;
      localObject = a(paramMap, this.j, (b.a)localObject);
      f.b(a, "it has GP(" + this.j + ") config!");
    }
  }
  
  private static boolean a(Map<String, ?> paramMap, List<ApplicationInfo> paramList)
  {
    if (paramMap.isEmpty()) {
      return false;
    }
    int n = TimeZone.getDefault().getRawOffset() / 3600000;
    Object localObject1 = g.e(paramMap, new String[] { "TimeZone" });
    if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!String.valueOf(n).equalsIgnoreCase((String)localObject1))) {
      return false;
    }
    localObject1 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    Object localObject2 = g.e(paramMap, new String[] { "RegionFormat" });
    if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!((String)localObject1).equalsIgnoreCase((String)localObject2))) {
      return false;
    }
    paramMap = g.g(paramMap, new String[] { "UrlScheme" });
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
      for (n = 1; n == 0; n = 0)
      {
        return false;
        return true;
      }
    }
  }
  
  private boolean a(Set<String> paramSet)
  {
    if (!f.a()) {
      return true;
    }
    HashSet localHashSet = new HashSet();
    paramSet = paramSet.iterator();
    int i2 = 0;
    String str;
    Pattern localPattern;
    String[] arrayOfString;
    int n;
    int i1;
    for (;;)
    {
      if (paramSet.hasNext())
      {
        str = (String)paramSet.next();
        if ("default".equals(str))
        {
          i2 = 1;
        }
        else
        {
          localPattern = Pattern.compile("[0-9]+");
          arrayOfString = str.split("-");
          if ((arrayOfString.length == 1) && (localPattern.matcher(arrayOfString[0].trim()).matches()))
          {
            n = Integer.valueOf(arrayOfString[0]).intValue();
            i1 = n;
          }
        }
      }
    }
    for (;;)
    {
      label120:
      int i3;
      if ((n != -1) && (i1 != -1))
      {
        i3 = n;
        if (i1 >= n) {}
      }
      else
      {
        f.e(a, str + " 格式错误!");
        Process.killProcess(Process.myPid());
        i3 = n;
      }
      label174:
      if (i3 <= i1)
      {
        if (localHashSet.contains(Integer.valueOf(i3))) {
          break label285;
        }
        localHashSet.add(Integer.valueOf(i3));
      }
      for (;;)
      {
        i3 += 1;
        break label174;
        break;
        if ((arrayOfString.length != 2) || (!localPattern.matcher(arrayOfString[0].trim()).matches()) || (!localPattern.matcher(arrayOfString[1].trim()).matches())) {
          break label343;
        }
        n = Integer.valueOf(arrayOfString[0]).intValue();
        i1 = Integer.valueOf(arrayOfString[1]).intValue();
        break label120;
        label285:
        f.e(a, str + " 多个用户等级有重叠!");
        Process.killProcess(Process.myPid());
      }
      if (i2 != 0) {
        break;
      }
      f.e(a, "no default config!");
      Process.killProcess(Process.myPid());
      return true;
      label343:
      i1 = -1;
      n = -1;
    }
  }
  
  private static c b(Context paramContext, Map<String, ?> paramMap)
  {
    c localC = new c();
    if (paramMap == null) {
      return localC;
    }
    paramMap = g.g(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() < 1)) {
      return localC;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
    paramMap = paramMap.iterator();
    while (paramMap.hasNext())
    {
      Object localObject = paramMap.next();
      if (a((Map)localObject, paramContext)) {
        paramContext = g.a((Map)localObject, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localC.a = bool;
      localC.b = paramContext;
      return localC;
      paramContext = "";
    }
  }
  
  private static void b(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = g.h(paramMap, new String[] { "Data" });
      f.c("RemoteConfig", "mergeRegions(), main data = " + localMap1);
      Map localMap2 = g.h(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = g.h(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = g.h(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map<String, ?>)localObject;
        if (localObject == null) {
          paramMap = g.h(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = g.h(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          g.a(localMap1, g.h(paramMap, new String[] { "Data" }));
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
      Map localMap2 = g.h(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        int n = Integer.MAX_VALUE;
        Iterator localIterator = localMap2.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = str.replace(" ", "");
          if (b.matcher((CharSequence)localObject).matches())
          {
            localObject = ((String)localObject).split("-");
            if (2 == localObject.length)
            {
              int i1 = Integer.valueOf(localObject[0]).intValue();
              int i2 = Integer.valueOf(localObject[1]).intValue();
              if (i1 <= i2)
              {
                if ((c < i1) || (c > i2) || (i1 >= n)) {
                  break label214;
                }
                localMap1 = g.h(localMap2, new String[] { str, "Data" });
                n = i1;
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
          g.a(g.h(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  private void l()
  {
    Map localMap = a(this.f, this.h, this.i);
    if (localMap == null) {
      return;
    }
    a(localMap);
    try
    {
      this.e = g.h(localMap, new String[] { "Data" });
      this.k = b(this.f, this.e);
      return;
    }
    finally {}
  }
  
  long a()
  {
    long l2 = b.a(7200.0F, new String[] { "libCommons", "RemoteConfig", "UpdateInterval" });
    long l1;
    if (l2 > 86400.0D) {
      l1 = 86400L;
    }
    for (;;)
    {
      return (l1 * 1000.0D);
      l1 = l2;
      if (l2 < 60.0D) {
        l1 = 60L;
      }
    }
  }
  
  public void a(int paramInt)
  {
    c = paramInt;
    if ((!TextUtils.isEmpty(this.i)) || (!TextUtils.isEmpty(this.h))) {
      l();
    }
  }
  
  public boolean a(final a paramA)
  {
    f.c("RemoteConfig", "fetch()");
    if (TextUtils.isEmpty(this.g))
    {
      f.c("RemoteConfig", "fetch(), plist url is null or empty");
      paramA.a(false, false);
      return true;
    }
    if (!c()) {
      return false;
    }
    if (this.l != null) {
      return false;
    }
    this.l = new Thread(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            if (c.a(c.this).getFilesDir() == null)
            {
              paramA.a(false, false);
              return;
            }
            Object localObject1 = c.a(c.this).getFilesDir().getPath();
            File localFile = new File((String)localObject1);
            if (!localFile.exists()) {
              localFile.mkdir();
            }
            localFile = new File((String)localObject1, "temp." + c.b(c.this));
            a localA = new a(c.c(c.this));
            if (c.c(c.this).equals(c.d(c.this).a))
            {
              localObject4 = new HashMap();
              if (!TextUtils.isEmpty(c.d(c.this).b)) {
                ((Map)localObject4).put("If-Modified-Since", c.d(c.this).b);
              }
              if (!TextUtils.isEmpty(c.d(c.this).c)) {
                ((Map)localObject4).put("If-None-Match", c.d(c.this).c);
              }
              if (!((Map)localObject4).isEmpty()) {
                localA.a((Map)localObject4);
              }
            }
            localA.a(10000).b(30000);
            localA.a(localFile);
            localA.a();
            f.b("RemoteConfig", "fetch remoteconfig finish");
            if (!localA.e())
            {
              paramA.a(false, false);
              return;
            }
            if (localA.f() == 304)
            {
              f.b("RemoteConfig", "RemoteConfig not modify");
              c.this.e();
              paramA.a(false, true);
              return;
            }
            Object localObject4 = i.a(localFile);
            c.a(c.this, (Map)localObject4);
            localObject4 = g.h((Map)localObject4, new String[] { "Data" });
            if (localObject4 == null)
            {
              f.c("RemoteConfig", "fetch(), parser stream failed");
              paramA.a(false, false);
              return;
            }
            localObject1 = new File((String)localObject1, c.b(c.this));
            if (((File)localObject1).exists())
            {
              c.d(c.this).b(c.a(c.this));
              ((File)localObject1).delete();
            }
            if (!localFile.renameTo((File)localObject1))
            {
              f.c("RemoteConfig", "fetch(), rename temp to plist file name failed");
              paramA.a(false, false);
              return;
            }
            c.d(c.this).a = c.c(c.this);
            c.d(c.this).b = ((String)localA.l().get("Last-Modified"));
            c.d(c.this).c = ((String)localA.l().get("Etag"));
            c.d(c.this).a(c.a(c.this));
            f.b("RemoteConfig", "RemoteConfig modified Last-Modified: " + c.d(c.this).b + " ETag: " + c.d(c.this).c);
            try
            {
              if ((c.e(c.this) == null) || (!c.e(c.this).equals(localObject4)))
              {
                c.b(c.this, (Map)localObject4);
                c.a(c.this, c.a(c.a(c.this), (Map)localObject4));
                bool = true;
                c.this.e();
                paramA.a(bool, true);
                return;
              }
            }
            finally {}
            boolean bool = false;
          }
          finally
          {
            c.a(c.this, null);
          }
        }
      }
    });
    this.l.start();
    return true;
  }
  
  long b()
  {
    return j.a(this.f, "remoteconfig").a("updateTime", 0L);
  }
  
  boolean c()
  {
    if ((System.currentTimeMillis() <= b()) || (System.currentTimeMillis() - b() >= a()) || (b() == 0L))
    {
      if (f.b()) {
        f.b("RemoteConfig", "Time is expired：" + b() + ":" + a());
      }
      return true;
    }
    f.b("RemoteConfig", "Time is not expired");
    return false;
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  void d()
  {
    j.a(this.f, "remoteconfig").b();
  }
  
  void e()
  {
    j.a(this.f, "remoteconfig").c("updateTime", System.currentTimeMillis());
    if (f.b()) {
      f.b("RemoteConfig", "update last refresh time：" + b());
    }
  }
  
  public int f()
  {
    return c;
  }
  
  public Map<String, ?> g()
  {
    return this.e;
  }
  
  public boolean h()
  {
    if (this.k == null) {
      return false;
    }
    return this.k.a;
  }
  
  public String i()
  {
    if (this.k == null) {
      return "";
    }
    return this.k.b;
  }
  
  public void j()
  {
    l();
  }
  
  public void k()
  {
    if ((!TextUtils.isEmpty(this.h)) && (this.f.getFilesDir() != null))
    {
      File localFile = new File(this.f.getFilesDir().getPath(), this.h);
      if (localFile.exists()) {
        localFile.delete();
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean1, boolean paramBoolean2);
  }
  
  private static class b
  {
    public String a;
    public String b;
    public String c;
    
    private b() {}
    
    static b a(String paramString)
    {
      b localB = new b();
      if (TextUtils.isEmpty(paramString)) {
        return localB;
      }
      try
      {
        paramString = new JSONObject(paramString);
        localB.a = paramString.optString("remoteUrl");
        localB.b = paramString.optString("lastModified");
        localB.c = paramString.optString("eTag");
        return localB;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
        f.d("RemoteFile LastModifyInfo create from json failed");
      }
      return localB;
    }
    
    static b c(Context paramContext)
    {
      return a(j.a(paramContext).b("hs.commons.config.remote.file.last.modify.info", ""));
    }
    
    String a()
    {
      Object localObject = new JSONObject();
      try
      {
        ((JSONObject)localObject).put("remoteUrl", this.a);
        ((JSONObject)localObject).put("lastModified", this.b);
        ((JSONObject)localObject).put("eTag", this.c);
        localObject = ((JSONObject)localObject).toString();
        return localObject;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        f.d("RemoteFile LastModifyInfo to json failed");
      }
      return null;
    }
    
    void a(Context paramContext)
    {
      j.a(paramContext).d("hs.commons.config.remote.file.last.modify.info", a());
    }
    
    void b(Context paramContext)
    {
      this.a = "";
      this.b = "";
      this.c = "";
      a(paramContext);
    }
  }
  
  private static class c
  {
    public boolean a = false;
    public String b = "";
    
    public c() {}
  }
}
