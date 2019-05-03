package net.appcloudbox.common.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.text.TextUtils;
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
import net.appcloudbox.common.analytics.c.a.a;
import net.appcloudbox.common.analytics.c.a.a.b;
import net.appcloudbox.common.preference.b;
import net.appcloudbox.common.utils.h;
import net.appcloudbox.common.utils.i;
import net.appcloudbox.common.utils.k;
import net.appcloudbox.common.utils.l;
import org.json.JSONException;
import org.json.JSONObject;

class c
{
  static int a;
  private static final String h = c.class.getSimpleName();
  private static final Pattern i = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static final Map<String, Integer> j;
  Map<String, ?> b;
  String c;
  String d;
  String e;
  c f;
  Thread g;
  private Context k;
  private String l;
  private b m;
  
  static
  {
    a = -1;
    j = new HashMap();
    if (h.b())
    {
      j.put("OrganicDebug", Integer.valueOf(3));
      j.put("SearchDebug", Integer.valueOf(4));
      j.put("NotOrganicDebug", Integer.valueOf(8));
      return;
    }
    j.put("OrganicEastEightDistrictRelease", Integer.valueOf(1));
    j.put("OrganicNotEastEightDistrictRelease", Integer.valueOf(3));
    j.put("SearchRelease", Integer.valueOf(4));
    j.put("NotOrganicEastEightDistrictRelease", Integer.valueOf(5));
    j.put("NotOrganicNotEastEightDistrictRelease", Integer.valueOf(8));
  }
  
  public c(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    new StringBuilder("assetFileName=").append(paramString2).append("\rremotePlistUrl=").append(paramString1).append("\rdeleteCachedFile=").append(paramBoolean);
    this.k = paramContext;
    this.e = paramString2;
    this.c = paramString1;
    this.l = paramString3;
    this.m = b.a(net.appcloudbox.common.preference.c.c().b("hs.commons.config.remote.file.last.modify.info", ""));
    if (!l.a(this.e)) {
      new StringBuilder("Using unencrypted plist file is not allowed, please use PA instead !!! ").append(this.e);
    }
    if (!TextUtils.isEmpty(paramString1))
    {
      paramContext = paramString1.split("/");
      this.d = paramContext[(paramContext.length - 1)];
    }
    if (paramBoolean)
    {
      this.m.b();
      net.appcloudbox.common.preference.c.b(this.k, "remoteconfig").b();
      if ((!TextUtils.isEmpty(this.d)) && (this.k.getFilesDir() != null))
      {
        paramContext = new File(this.k.getFilesDir().getPath(), this.d);
        if (paramContext.exists()) {
          paramContext.delete();
        }
      }
    }
    if (a == -1) {
      a = net.appcloudbox.common.preference.c.c().a("hs.commons.config.Test_User_Token", -1);
    }
    if (-1 == a)
    {
      a = new Random(System.currentTimeMillis()).nextInt(1000);
      net.appcloudbox.common.preference.c.c().c("hs.commons.config.Test_User_Token", a);
    }
    if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.d))) {
      e();
    }
  }
  
  static int a(a.a paramA)
  {
    a.a.b localB;
    if ((paramA == null) || (paramA.a == null) || (paramA.a == a.a.b.a) || (paramA.a == a.a.b.b))
    {
      localB = a.a.b.b;
      if (!h.b()) {
        break label119;
      }
      if ((paramA == null) || (!paramA.s)) {
        break label78;
      }
      paramA = (Integer)j.get("SearchDebug");
    }
    for (;;)
    {
      return paramA.intValue();
      localB = a.a.b.c;
      break;
      label78:
      if (localB == a.a.b.b)
      {
        paramA = (Integer)j.get("OrganicDebug");
      }
      else
      {
        paramA = (Integer)j.get("NotOrganicDebug");
        continue;
        label119:
        int n = TimeZone.getDefault().getRawOffset() / 3600000;
        if ((paramA != null) && (paramA.s)) {
          paramA = (Integer)j.get("SearchRelease");
        } else if (n == 8)
        {
          if (localB == a.a.b.b) {
            paramA = (Integer)j.get("OrganicEastEightDistrictRelease");
          } else {
            paramA = (Integer)j.get("NotOrganicEastEightDistrictRelease");
          }
        }
        else if (localB == a.a.b.b) {
          paramA = (Integer)j.get("OrganicNotEastEightDistrictRelease");
        } else {
          paramA = (Integer)j.get("NotOrganicNotEastEightDistrictRelease");
        }
      }
    }
  }
  
  static long a()
  {
    long l2 = a.d(new String[] { "libCommons", "RemoteConfig", "UpdateInterval" });
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
  
  private void a(Map<String, ?> paramMap)
  {
    b(paramMap);
    c(paramMap);
    Object localObject3 = net.appcloudbox.common.analytics.c.a.a(this.k);
    Object localObject2 = null;
    int i4;
    Object localObject1;
    if (i.b(paramMap, new String[] { "UserLevel" }))
    {
      i4 = a((a.a)localObject3);
      if ((i4 >= 0) && (paramMap != null) && (!TextUtils.isEmpty("UserLevel"))) {
        break label220;
      }
      localObject1 = null;
    }
    for (;;)
    {
      if (localObject1 != null)
      {
        b((Map)localObject1);
        c((Map)localObject1);
      }
      new StringBuilder("it has userlevel(").append(i4).append(") config!");
      localObject2 = localObject1;
      label149:
      label188:
      label213:
      label220:
      Map localMap;
      Object localObject4;
      int i2;
      Object localObject5;
      Pattern localPattern;
      String[] arrayOfString;
      int n;
      int i1;
      if (i.b(paramMap, new String[] { "GP" }))
      {
        localObject1 = this.l;
        if ((paramMap == null) || (TextUtils.isEmpty((CharSequence)localObject1)) || (localObject3 == null))
        {
          localObject1 = null;
          if (localObject1 != null)
          {
            b((Map)localObject1);
            c((Map)localObject1);
          }
          new StringBuilder("it has GP(").append(this.l).append(") config!");
          localObject3 = localObject2;
          if (localObject1 != null)
          {
            if (localObject2 == null) {
              break label935;
            }
            i.a((Map)localObject1, (Map)localObject2);
            localObject3 = localObject1;
          }
          i.a(paramMap, (Map)localObject3);
          return;
          localMap = i.h(paramMap, new String[] { "UserLevel" });
          localObject1 = Pattern.compile("[0-9]+");
          if (localMap == null) {
            break label961;
          }
          localObject4 = localMap.keySet();
          if (h.b())
          {
            localObject2 = new HashSet();
            localObject4 = ((Set)localObject4).iterator();
            i2 = 0;
            for (;;)
            {
              if (((Iterator)localObject4).hasNext())
              {
                localObject5 = (String)((Iterator)localObject4).next();
                if ("default".equals(localObject5))
                {
                  i2 = 1;
                }
                else
                {
                  localPattern = Pattern.compile("[0-9]+");
                  arrayOfString = ((String)localObject5).split("-");
                  if ((arrayOfString.length == 1) && (localPattern.matcher(arrayOfString[0].trim()).matches()))
                  {
                    n = Integer.valueOf(arrayOfString[0]).intValue();
                    i1 = n;
                  }
                }
              }
            }
          }
        }
      }
      for (;;)
      {
        label380:
        int i3;
        if ((n != -1) && (i1 != -1))
        {
          i3 = n;
          if (i1 >= n) {}
        }
        else
        {
          new StringBuilder().append((String)localObject5).append(" 格式错误!");
          Process.killProcess(Process.myPid());
          i3 = n;
        }
        label426:
        if (i3 <= i1)
        {
          if (((Set)localObject2).contains(Integer.valueOf(i3))) {
            break label537;
          }
          ((Set)localObject2).add(Integer.valueOf(i3));
        }
        for (;;)
        {
          i3 += 1;
          break label426;
          break;
          if ((arrayOfString.length != 2) || (!localPattern.matcher(arrayOfString[0].trim()).matches()) || (!localPattern.matcher(arrayOfString[1].trim()).matches())) {
            break label954;
          }
          n = Integer.valueOf(arrayOfString[0]).intValue();
          i1 = Integer.valueOf(arrayOfString[1]).intValue();
          break label380;
          label537:
          new StringBuilder().append((String)localObject5).append(" 多个用户等级有重叠!");
          Process.killProcess(Process.myPid());
        }
        if (i2 == 0) {
          Process.killProcess(Process.myPid());
        }
        localObject2 = localMap.keySet().iterator();
        for (;;)
        {
          label590:
          if (((Iterator)localObject2).hasNext())
          {
            localObject4 = (String)((Iterator)localObject2).next();
            localObject5 = ((String)localObject4).split("-");
            i2 = -1;
            i3 = 0;
            if (localObject5.length == 1)
            {
              if ("default".equals(localObject5[0])) {
                continue;
              }
              n = i3;
              i1 = i2;
              if (((Pattern)localObject1).matcher(localObject5[0].trim()).matches())
              {
                n = Integer.valueOf(localObject5[0]).intValue();
                i1 = n;
              }
              label685:
              if ((i1 == -1) || (n == -1) || (i1 > i4) || (n < i4)) {
                break label836;
              }
            }
          }
        }
        for (localObject2 = i.h(localMap, new String[] { localObject4 });; localObject2 = null)
        {
          localObject1 = localObject2;
          if (localObject2 != null) {
            break;
          }
          localObject1 = i.h(localMap, new String[] { "default" });
          break;
          n = i3;
          i1 = i2;
          if (localObject5.length != 2) {
            break label685;
          }
          n = i3;
          i1 = i2;
          if (!((Pattern)localObject1).matcher(localObject5[0].trim()).matches()) {
            break label685;
          }
          n = i3;
          i1 = i2;
          if (!((Pattern)localObject1).matcher(localObject5[1].trim()).matches()) {
            break label685;
          }
          i1 = Integer.valueOf(localObject5[0]).intValue();
          n = Integer.valueOf(localObject5[1]).intValue();
          break label685;
          label836:
          break label590;
          localMap = i.h(paramMap, new String[] { localObject1 });
          if (localMap == null)
          {
            localObject1 = null;
            break label149;
          }
          if (((a.a)localObject3).a == a.a.b.b) {}
          for (localObject1 = "Organic";; localObject1 = ((a.a)localObject3).b)
          {
            localObject3 = i.h(localMap, new String[] { localObject1 });
            localObject1 = localObject3;
            if (localObject3 != null) {
              break;
            }
            localObject1 = i.h(localMap, new String[] { "Others" });
            break;
          }
          label935:
          localObject3 = localObject1;
          break label213;
          localObject1 = null;
          break label188;
        }
        label954:
        n = -1;
        i1 = -1;
      }
      label961:
      localObject1 = null;
    }
  }
  
  private static c b(Context paramContext, Map<String, ?> paramMap)
  {
    c localC = new c();
    if (paramMap == null) {
      return localC;
    }
    paramMap = i.g(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() <= 0)) {
      return localC;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
    paramMap = paramMap.iterator();
    Object localObject2;
    int n;
    if (paramMap.hasNext())
    {
      Object localObject1 = paramMap.next();
      localObject2 = (Map)localObject1;
      if (((Map)localObject2).isEmpty())
      {
        n = 0;
        label101:
        if (n == 0) {
          break label393;
        }
        paramContext = i.a((Map)localObject1, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localC.a = bool;
      localC.b = paramContext;
      return localC;
      n = TimeZone.getDefault().getRawOffset() / 3600000;
      Object localObject3 = i.e((Map)localObject2, new String[] { "TimeZone" });
      if ((!TextUtils.isEmpty((CharSequence)localObject3)) && (!String.valueOf(n).equalsIgnoreCase((String)localObject3)))
      {
        n = 0;
        break label101;
      }
      localObject3 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
      Object localObject4 = i.e((Map)localObject2, new String[] { "RegionFormat" });
      if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject3).equalsIgnoreCase((String)localObject4)))
      {
        n = 0;
        break label101;
      }
      localObject2 = i.g((Map)localObject2, new String[] { "UrlScheme" });
      if ((localObject2 == null) || (((List)localObject2).isEmpty()))
      {
        n = 1;
        break label101;
      }
      localObject2 = ((List)localObject2).iterator();
      label393:
      label398:
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
        for (n = 1;; n = 0)
        {
          if (n != 0) {
            break label398;
          }
          n = 0;
          break label101;
          n = 1;
          break label101;
          break;
        }
      }
      paramContext = "";
    }
  }
  
  private static void b(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = i.h(paramMap, new String[] { "Data" });
      new StringBuilder("mergeRegions(), main data = ").append(localMap1);
      Map localMap2 = i.h(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = i.h(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = i.h(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map<String, ?>)localObject;
        if (localObject == null) {
          paramMap = i.h(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = i.h(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          i.a(localMap1, i.h(paramMap, new String[] { "Data" }));
          return;
        }
      }
    }
  }
  
  private static void c(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    label210:
    label211:
    for (;;)
    {
      return;
      Map localMap2 = i.h(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        int n = Integer.MAX_VALUE;
        Iterator localIterator = localMap2.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = str.replace(" ", "");
          if (i.matcher((CharSequence)localObject).matches())
          {
            localObject = ((String)localObject).split("-");
            if (2 == localObject.length)
            {
              int i1 = Integer.valueOf(localObject[0]).intValue();
              int i2 = Integer.valueOf(localObject[1]).intValue();
              if (i1 <= i2)
              {
                if ((a < i1) || (a > i2) || (i1 >= n)) {
                  break label210;
                }
                localMap1 = i.h(localMap2, new String[] { str, "Data" });
                n = i1;
              }
            }
          }
        }
        for (;;)
        {
          break;
          if (localMap1 == null) {
            break label211;
          }
          i.a(i.h(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  public static int d()
  {
    return a;
  }
  
  final long b()
  {
    return net.appcloudbox.common.preference.c.b(this.k, "remoteconfig").a("updateTime", 0L);
  }
  
  final void c()
  {
    net.appcloudbox.common.preference.c.b(this.k, "remoteconfig").c("updateTime", System.currentTimeMillis());
    if (h.a()) {
      new StringBuilder("update last refresh time：").append(b());
    }
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  final void e()
  {
    Context localContext = this.k;
    Object localObject4 = this.d;
    String str = this.e;
    Object localObject3 = null;
    Object localObject1 = localObject3;
    if (!TextUtils.isEmpty((CharSequence)localObject4))
    {
      localObject1 = localObject3;
      if (localContext.getFilesDir() != null)
      {
        localObject4 = new File(localContext.getFilesDir().getPath(), (String)localObject4);
        localObject1 = localObject3;
        if (((File)localObject4).exists()) {
          localObject1 = k.a((File)localObject4);
        }
      }
    }
    if (localObject1 != null)
    {
      localObject3 = localObject1;
      if (!((Map)localObject1).isEmpty()) {}
    }
    else
    {
      localObject3 = localObject1;
      if (!TextUtils.isEmpty(str)) {
        localObject3 = k.a(localContext.getAssets(), str);
      }
    }
    if (localObject3 == null) {
      return;
    }
    a((Map)localObject3);
    try
    {
      this.b = i.h((Map)localObject3, new String[] { "Data" });
      this.f = b(this.k, this.b);
      return;
    }
    finally {}
  }
  
  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean1, boolean paramBoolean2);
  }
  
  private static final class b
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
      }
      return localB;
    }
    
    private String c()
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
      }
      return null;
    }
    
    final void a()
    {
      net.appcloudbox.common.preference.c.c().d("hs.commons.config.remote.file.last.modify.info", c());
    }
    
    final void b()
    {
      this.a = "";
      this.b = "";
      this.c = "";
      a();
    }
  }
  
  private static final class c
  {
    public boolean a = false;
    public String b = "";
    
    public c() {}
  }
}
