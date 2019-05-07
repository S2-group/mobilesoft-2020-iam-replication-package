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
import org.json.JSONException;
import org.json.JSONObject;

public class diw
{
  public static int a = -1;
  private static final String h = "diw";
  private static final Pattern i = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static final Map<String, Integer> j = new HashMap();
  Map<String, ?> b;
  String c;
  public String d;
  public String e;
  public diw.c f;
  Thread g;
  private Context k;
  private String l;
  private diw.b m;
  
  static
  {
    if (djp.b())
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
  
  public diw(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder("assetFileName=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("\rremotePlistUrl=");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("\rdeleteCachedFile=");
    localStringBuilder.append(paramBoolean);
    this.k = paramContext;
    this.e = paramString2;
    this.c = paramString1;
    this.l = paramString3;
    this.m = diw.b.a(djg.b().b("hs.commons.config.remote.file.last.modify.info", ""));
    if (!djt.a(this.e)) {
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
      djg.b(this.k, "remoteconfig").a();
      if ((!TextUtils.isEmpty(this.d)) && (this.k.getFilesDir() != null))
      {
        paramContext = new File(this.k.getFilesDir().getPath(), this.d);
        if (paramContext.exists()) {
          paramContext.delete();
        }
      }
    }
    if (a == -1) {
      a = djg.b().a("hs.commons.config.Test_User_Token", -1);
    }
    if (-1 == a)
    {
      a = new Random(System.currentTimeMillis()).nextInt(1000);
      djg.b().b("hs.commons.config.Test_User_Token", a);
    }
    if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.d))) {
      e();
    }
  }
  
  public static int a(dis.a paramA)
  {
    dis.a.b localB;
    if ((paramA != null) && (paramA.a != null) && (paramA.a != dis.a.b.a) && (paramA.a != dis.a.b.b)) {
      localB = dis.a.b.c;
    } else {
      localB = dis.a.b.b;
    }
    if (djp.b())
    {
      if ((paramA != null) && (paramA.s)) {
        paramA = (Integer)j.get("SearchDebug");
      } else if (localB == dis.a.b.b) {
        paramA = (Integer)j.get("OrganicDebug");
      } else {
        paramA = (Integer)j.get("NotOrganicDebug");
      }
    }
    else
    {
      int n = TimeZone.getDefault().getRawOffset() / 3600000;
      if ((paramA != null) && (paramA.s)) {
        paramA = (Integer)j.get("SearchRelease");
      } else if (n == 8)
      {
        if (localB == dis.a.b.b) {
          paramA = (Integer)j.get("OrganicEastEightDistrictRelease");
        } else {
          paramA = (Integer)j.get("NotOrganicEastEightDistrictRelease");
        }
      }
      else if (localB == dis.a.b.b) {
        paramA = (Integer)j.get("OrganicNotEastEightDistrictRelease");
      } else {
        paramA = (Integer)j.get("NotOrganicNotEastEightDistrictRelease");
      }
    }
    return paramA.intValue();
  }
  
  static long a()
  {
    long l1 = diu.e(new String[] { "libCommons", "RemoteConfig", "UpdateInterval" });
    double d1 = l1;
    if (d1 > 86400.0D) {
      l1 = 86400L;
    } else if (d1 < 60.0D) {
      l1 = 60L;
    }
    return (l1 * 1000.0D);
  }
  
  private void a(Map<String, ?> paramMap)
  {
    b(paramMap);
    c(paramMap);
    Object localObject3 = dis.a(this.k);
    Map localMap;
    Object localObject1;
    Object localObject2;
    if (djq.b(paramMap, new String[] { "UserLevel" }))
    {
      int i4 = a((dis.a)localObject3);
      if ((i4 >= 0) && (paramMap != null) && (!TextUtils.isEmpty("UserLevel")))
      {
        localMap = djq.h(paramMap, new String[] { "UserLevel" });
        localObject1 = Pattern.compile("[0-9]+");
        if (localMap != null)
        {
          Object localObject4 = localMap.keySet();
          Object localObject5;
          int n;
          int i1;
          if (djp.b())
          {
            localObject2 = new HashSet();
            localObject4 = ((Set)localObject4).iterator();
            int i2 = 0;
            while (((Iterator)localObject4).hasNext())
            {
              localObject5 = (String)((Iterator)localObject4).next();
              if ("default".equals(localObject5))
              {
                i2 = 1;
              }
              else
              {
                Object localObject6 = Pattern.compile("[0-9]+");
                String[] arrayOfString = ((String)localObject5).split("-");
                if ((arrayOfString.length == 1) && (((Pattern)localObject6).matcher(arrayOfString[0].trim()).matches()))
                {
                  n = Integer.valueOf(arrayOfString[0]).intValue();
                  i1 = n;
                }
                else if ((arrayOfString.length == 2) && (((Pattern)localObject6).matcher(arrayOfString[0].trim()).matches()) && (((Pattern)localObject6).matcher(arrayOfString[1].trim()).matches()))
                {
                  n = Integer.valueOf(arrayOfString[0]).intValue();
                  i1 = Integer.valueOf(arrayOfString[1]).intValue();
                }
                else
                {
                  i1 = -1;
                  n = -1;
                }
                int i3;
                if ((n != -1) && (i1 != -1))
                {
                  i3 = n;
                  if (i1 >= n) {}
                }
                else
                {
                  localObject6 = new StringBuilder();
                  ((StringBuilder)localObject6).append((String)localObject5);
                  ((StringBuilder)localObject6).append(" 格式错误!");
                  Process.killProcess(Process.myPid());
                  i3 = n;
                }
                while (i3 <= i1)
                {
                  if (!((Set)localObject2).contains(Integer.valueOf(i3)))
                  {
                    ((Set)localObject2).add(Integer.valueOf(i3));
                  }
                  else
                  {
                    localObject6 = new StringBuilder();
                    ((StringBuilder)localObject6).append((String)localObject5);
                    ((StringBuilder)localObject6).append(" 多个用户等级有重叠!");
                    Process.killProcess(Process.myPid());
                  }
                  i3 += 1;
                }
              }
            }
            if (i2 == 0) {
              Process.killProcess(Process.myPid());
            }
          }
          localObject2 = localMap.keySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject4 = (String)((Iterator)localObject2).next();
            localObject5 = ((String)localObject4).split("-");
            if (localObject5.length == 1)
            {
              if ("default".equals(localObject5[0])) {
                continue;
              }
              if (((Pattern)localObject1).matcher(localObject5[0].trim()).matches())
              {
                n = Integer.valueOf(localObject5[0]).intValue();
                i1 = n;
                break label617;
              }
            }
            else if ((localObject5.length == 2) && (((Pattern)localObject1).matcher(localObject5[0].trim()).matches()) && (((Pattern)localObject1).matcher(localObject5[1].trim()).matches()))
            {
              n = Integer.valueOf(localObject5[0]).intValue();
              i1 = Integer.valueOf(localObject5[1]).intValue();
              break label617;
            }
            i1 = 0;
            n = -1;
            label617:
            if ((n != -1) && (i1 != -1) && (n <= i4) && (i1 >= i4))
            {
              localObject2 = djq.h(localMap, new String[] { localObject4 });
              break label661;
            }
          }
          localObject2 = null;
          label661:
          localObject1 = localObject2;
          if (localObject2 != null) {
            break label693;
          }
          localObject1 = djq.h(localMap, new String[] { "default" });
          break label693;
        }
      }
      localObject1 = null;
      label693:
      if (localObject1 != null)
      {
        b((Map)localObject1);
        c((Map)localObject1);
      }
      localObject2 = new StringBuilder("it has userlevel(");
      ((StringBuilder)localObject2).append(i4);
      ((StringBuilder)localObject2).append(") config!");
      localObject2 = localObject1;
    }
    else
    {
      localObject2 = null;
    }
    if (djq.b(paramMap, new String[] { "GP" }))
    {
      localObject1 = this.l;
      if ((paramMap != null) && (!TextUtils.isEmpty((CharSequence)localObject1)) && (localObject3 != null))
      {
        localMap = djq.h(paramMap, new String[] { localObject1 });
        if (localMap != null)
        {
          if (((dis.a)localObject3).a == dis.a.b.b) {
            localObject1 = "Organic";
          } else {
            localObject1 = ((dis.a)localObject3).b;
          }
          localObject3 = djq.h(localMap, new String[] { localObject1 });
          localObject1 = localObject3;
          if (localObject3 != null) {
            break label887;
          }
          localObject1 = djq.h(localMap, new String[] { "Others" });
          break label887;
        }
      }
      localObject1 = null;
      label887:
      if (localObject1 != null)
      {
        b((Map)localObject1);
        c((Map)localObject1);
      }
      localObject3 = new StringBuilder("it has GP(");
      ((StringBuilder)localObject3).append(this.l);
      ((StringBuilder)localObject3).append(") config!");
    }
    else
    {
      localObject1 = null;
    }
    if (localObject1 != null)
    {
      localObject3 = localObject1;
      if (localObject2 != null)
      {
        djq.a((Map)localObject1, (Map)localObject2);
        localObject3 = localObject1;
      }
    }
    else
    {
      localObject3 = localObject2;
    }
    djq.a(paramMap, (Map)localObject3);
  }
  
  private static diw.c b(Context paramContext, Map<String, ?> paramMap)
  {
    diw.c localC = new diw.c();
    if (paramMap == null) {
      return localC;
    }
    boolean bool = true;
    Object localObject1 = djq.g(paramMap, new String[] { "RestrictedUser" });
    if (localObject1 != null)
    {
      if (((List)localObject1).size() <= 0) {
        return localC;
      }
      paramMap = paramContext.getPackageManager().getInstalledApplications(8192);
      paramContext = "";
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Map localMap = (Map)((Iterator)localObject1).next();
        if (localMap.isEmpty()) {}
        for (;;)
        {
          n = 0;
          break label370;
          n = TimeZone.getDefault().getRawOffset() / 3600000;
          Object localObject2 = djq.e(localMap, new String[] { "TimeZone" });
          if ((TextUtils.isEmpty((CharSequence)localObject2)) || (String.valueOf(n).equalsIgnoreCase((String)localObject2)))
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(Locale.getDefault().getLanguage());
            ((StringBuilder)localObject2).append("_");
            ((StringBuilder)localObject2).append(Locale.getDefault().getCountry());
            localObject2 = ((StringBuilder)localObject2).toString();
            Object localObject3 = djq.e(localMap, new String[] { "RegionFormat" });
            if ((TextUtils.isEmpty((CharSequence)localObject3)) || (((String)localObject2).equalsIgnoreCase((String)localObject3)))
            {
              localObject2 = djq.g(localMap, new String[] { "UrlScheme" });
              if ((localObject2 == null) || (((List)localObject2).isEmpty())) {
                break;
              }
              localObject2 = ((List)localObject2).iterator();
              label361:
              do
              {
                if (!((Iterator)localObject2).hasNext()) {
                  break;
                }
                localObject3 = ((Iterator)localObject2).next();
                Iterator localIterator = paramMap.iterator();
                while (localIterator.hasNext()) {
                  if (((ApplicationInfo)localIterator.next()).packageName.equalsIgnoreCase((String)localObject3))
                  {
                    n = 1;
                    break label361;
                  }
                }
                n = 0;
              } while (n != 0);
            }
          }
        }
        int n = 1;
        label370:
        if (n != 0)
        {
          paramContext = djq.a(localMap, "", new String[] { "Description" });
          break label397;
        }
      }
      bool = false;
      label397:
      localC.a = bool;
      localC.b = paramContext;
      return localC;
    }
    return localC;
  }
  
  private static void b(Map<String, ?> paramMap)
  {
    if (paramMap == null) {
      return;
    }
    Map localMap1 = djq.h(paramMap, new String[] { "Data" });
    new StringBuilder("mergeRegions(), main data = ").append(localMap1);
    Map localMap2 = djq.h(paramMap, new String[] { "Regions" });
    if (localMap2 != null)
    {
      String str = Locale.getDefault().getCountry().trim();
      paramMap = djq.h(localMap2, new String[] { str });
      Object localObject = paramMap;
      if (paramMap == null) {
        localObject = djq.h(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
      }
      paramMap = (Map<String, ?>)localObject;
      if (localObject == null) {
        paramMap = djq.h(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
      }
      localObject = paramMap;
      if (paramMap == null)
      {
        Iterator localIterator = localMap2.keySet().iterator();
        do
        {
          localObject = paramMap;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject = (String)localIterator.next();
        } while (!((String)localObject).toUpperCase().equals(str.toUpperCase()));
        localObject = djq.h(localMap2, new String[] { localObject });
      }
      if (localObject != null) {
        djq.a(localMap1, djq.h((Map)localObject, new String[] { "Data" }));
      }
    }
  }
  
  private static void c(Map<String, ?> paramMap)
  {
    if (paramMap == null) {
      return;
    }
    Map localMap2 = djq.h(paramMap, new String[] { "Segments" });
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
            if ((i1 <= i2) && (a >= i1) && (a <= i2) && (i1 < n))
            {
              localMap1 = djq.h(localMap2, new String[] { str, "Data" });
              n = i1;
            }
          }
        }
      }
      if (localMap1 != null) {
        djq.a(djq.h(paramMap, new String[] { "Data" }), localMap1);
      }
    }
  }
  
  public static int d()
  {
    return a;
  }
  
  final long b()
  {
    return djg.b(this.k, "remoteconfig").a("updateTime", 0L);
  }
  
  final void c()
  {
    djg.b(this.k, "remoteconfig").c("updateTime", System.currentTimeMillis());
    if (djp.a()) {
      new StringBuilder("update last refresh time：").append(b());
    }
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  public final void e()
  {
    Context localContext = this.k;
    Object localObject1 = this.d;
    String str = this.e;
    if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (localContext.getFilesDir() != null))
    {
      localObject1 = new File(localContext.getFilesDir().getPath(), (String)localObject1);
      if (((File)localObject1).exists())
      {
        localObject1 = djs.a((File)localObject1);
        break label63;
      }
    }
    localObject1 = null;
    label63:
    Object localObject3;
    if (localObject1 != null)
    {
      localObject3 = localObject1;
      if (!((Map)localObject1).isEmpty()) {}
    }
    else
    {
      localObject3 = localObject1;
      if (!TextUtils.isEmpty(str)) {
        localObject3 = djs.a(localContext.getAssets(), str);
      }
    }
    if (localObject3 == null) {
      return;
    }
    a((Map)localObject3);
    try
    {
      this.b = djq.h((Map)localObject3, new String[] { "Data" });
      this.f = b(this.k, this.b);
      return;
    }
    finally {}
  }
  
  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean1, boolean paramBoolean2);
  }
  
  static final class b
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
      djg.b().d("hs.commons.config.remote.file.last.modify.info", c());
    }
    
    final void b()
    {
      this.a = "";
      this.b = "";
      this.c = "";
      a();
    }
  }
  
  public static final class c
  {
    public boolean a = false;
    public String b = "";
    
    public c() {}
  }
}
