import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

public class yf
{
  public static int a;
  private static final String h = yf.class.getSimpleName();
  private static final Pattern i = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static final Map<String, Integer> j;
  Map<String, ?> b;
  String c;
  public String d;
  public String e;
  public yf.c f;
  Thread g;
  private Context k;
  private String l;
  private yf.b m;
  
  static
  {
    a = -1;
    j = new HashMap();
    if (yy.b())
    {
      j.put("OrganicDebug", Integer.valueOf(3));
      j.put("NotOrganicDebug", Integer.valueOf(8));
      return;
    }
    j.put("OrganicEastEightDistrictRelease", Integer.valueOf(1));
    j.put("OrganicNotEastEightDistrictRelease", Integer.valueOf(3));
    j.put("NotOrganicEastEightDistrictRelease", Integer.valueOf(5));
    j.put("NotOrganicNotEastEightDistrictRelease", Integer.valueOf(8));
  }
  
  public yf(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    new StringBuilder("assetFileName=").append(paramString2).append("\rremotePlistUrl=").append(paramString1).append("\rdeleteCachedFile=").append(paramBoolean);
    this.k = paramContext;
    this.e = paramString2;
    this.c = paramString1;
    this.l = paramString3;
    this.m = yf.b.a(yn.a().a("hs.commons.config.remote.file.last.modify.info", ""));
    if (!zc.a(this.e)) {
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
      paramContext = yn.a(this.k, "remoteconfig");
      if (!paramContext.b.equals(paramContext.a.getPackageName() + "_preferences")) {
        break label361;
      }
      yn.a(paramContext.b, true, "should use clear() instead");
      paramString1 = new Bundle();
      paramString1.putString("EXTRA_FILE_NAME", paramContext.b);
      yu.a(paramContext.a, yn.a(paramContext.a), "METHOD_CLEAR", paramString1);
    }
    for (;;)
    {
      if ((!TextUtils.isEmpty(this.d)) && (this.k.getFilesDir() != null))
      {
        paramContext = new File(this.k.getFilesDir().getPath(), this.d);
        if (paramContext.exists()) {
          paramContext.delete();
        }
      }
      if (a == -1) {
        a = yn.a().a("hs.commons.config.Test_User_Token", -1);
      }
      if (-1 == a)
      {
        a = new Random(System.currentTimeMillis()).nextInt(1000);
        yn.a().b("hs.commons.config.Test_User_Token", a);
      }
      if ((!TextUtils.isEmpty(paramString2)) || (!TextUtils.isEmpty(this.d))) {
        e();
      }
      return;
      label361:
      yn.a(paramContext.b, false, "should use clearInterProcess() instead");
      paramContext.a.getSharedPreferences(paramContext.b, 0).edit().clear().apply();
    }
  }
  
  static long a()
  {
    long l2 = yd.e(new String[] { "libCommons", "RemoteConfig", "UpdateInterval" });
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
    Object localObject1 = yb.a();
    int n;
    label97:
    int i4;
    if (yz.a(paramMap, new String[] { "UserLevel" })) {
      if ((localObject1 == null) || (((yb.a)localObject1).a == 0) || (((yb.a)localObject1).a == yb.a.b.a) || (((yb.a)localObject1).a == yb.a.b.b))
      {
        n = yb.a.b.b;
        if (!yy.b()) {
          break label189;
        }
        if (n != yb.a.b.b) {
          break label171;
        }
        localObject1 = (Integer)j.get("OrganicDebug");
        i4 = ((Integer)localObject1).intValue();
        if ((i4 >= 0) && (paramMap != null) && (!TextUtils.isEmpty("UserLevel"))) {
          break label290;
        }
        localObject1 = null;
      }
    }
    for (;;)
    {
      new StringBuilder("it has userlevel(").append(i4).append(") config!");
      b((Map)localObject1);
      c((Map)localObject1);
      yz.a(paramMap, (Map)localObject1);
      return;
      n = yb.a.b.c;
      break;
      label171:
      localObject1 = (Integer)j.get("NotOrganicDebug");
      break label97;
      label189:
      if (TimeZone.getDefault().getRawOffset() / 3600000 == 8)
      {
        if (n == yb.a.b.b)
        {
          localObject1 = (Integer)j.get("OrganicEastEightDistrictRelease");
          break label97;
        }
        localObject1 = (Integer)j.get("NotOrganicEastEightDistrictRelease");
        break label97;
      }
      if (n == yb.a.b.b)
      {
        localObject1 = (Integer)j.get("OrganicNotEastEightDistrictRelease");
        break label97;
      }
      localObject1 = (Integer)j.get("NotOrganicNotEastEightDistrictRelease");
      break label97;
      label290:
      Map localMap = yz.h(paramMap, new String[] { "UserLevel" });
      localObject1 = Pattern.compile("[0-9]+");
      if (localMap != null)
      {
        Object localObject3 = localMap.keySet();
        Object localObject2;
        int i2;
        Object localObject4;
        Pattern localPattern;
        String[] arrayOfString;
        int i1;
        if (yy.b())
        {
          localObject2 = new HashSet();
          localObject3 = ((Set)localObject3).iterator();
          i2 = 0;
          for (;;)
          {
            if (((Iterator)localObject3).hasNext())
            {
              localObject4 = (String)((Iterator)localObject3).next();
              if ("default".equals(localObject4))
              {
                i2 = 1;
              }
              else
              {
                localPattern = Pattern.compile("[0-9]+");
                arrayOfString = ((String)localObject4).split("-");
                if ((arrayOfString.length == 1) && (localPattern.matcher(arrayOfString[0].trim()).matches()))
                {
                  n = Integer.valueOf(arrayOfString[0]).intValue();
                  i1 = n;
                }
              }
            }
          }
        }
        for (;;)
        {
          label450:
          int i3;
          if ((n != -1) && (i1 != -1))
          {
            i3 = n;
            if (i1 >= n) {}
          }
          else
          {
            new StringBuilder().append((String)localObject4).append(" 格式错误!");
            Process.killProcess(Process.myPid());
            i3 = n;
          }
          label496:
          if (i3 <= i1)
          {
            if (((Set)localObject2).contains(Integer.valueOf(i3))) {
              break label607;
            }
            ((Set)localObject2).add(Integer.valueOf(i3));
          }
          for (;;)
          {
            i3 += 1;
            break label496;
            break;
            if ((arrayOfString.length != 2) || (!localPattern.matcher(arrayOfString[0].trim()).matches()) || (!localPattern.matcher(arrayOfString[1].trim()).matches())) {
              break label1064;
            }
            n = Integer.valueOf(arrayOfString[0]).intValue();
            i1 = Integer.valueOf(arrayOfString[1]).intValue();
            break label450;
            label607:
            new StringBuilder().append((String)localObject4).append(" 多个用户等级有重叠!");
            Process.killProcess(Process.myPid());
          }
          if (i2 == 0) {
            Process.killProcess(Process.myPid());
          }
          localObject2 = localMap.keySet().iterator();
          for (;;)
          {
            label660:
            if (((Iterator)localObject2).hasNext())
            {
              localObject3 = (String)((Iterator)localObject2).next();
              localObject4 = ((String)localObject3).split("-");
              i2 = -1;
              i3 = 0;
              if (localObject4.length == 1)
              {
                if ("default".equals(localObject4[0])) {
                  continue;
                }
                n = i3;
                i1 = i2;
                if (((Pattern)localObject1).matcher(localObject4[0].trim()).matches())
                {
                  n = Integer.valueOf(localObject4[0]).intValue();
                  i1 = n;
                }
                label755:
                if ((i1 == -1) || (n == -1) || (i1 > i4) || (n < i4)) {
                  break label906;
                }
              }
            }
          }
          for (localObject2 = yz.h(localMap, new String[] { localObject3 });; localObject2 = null)
          {
            localObject1 = localObject2;
            if (localObject2 != null) {
              break;
            }
            localObject1 = yz.h(localMap, new String[] { "default" });
            break;
            n = i3;
            i1 = i2;
            if (localObject4.length != 2) {
              break label755;
            }
            n = i3;
            i1 = i2;
            if (!((Pattern)localObject1).matcher(localObject4[0].trim()).matches()) {
              break label755;
            }
            n = i3;
            i1 = i2;
            if (!((Pattern)localObject1).matcher(localObject4[1].trim()).matches()) {
              break label755;
            }
            i1 = Integer.valueOf(localObject4[0]).intValue();
            n = Integer.valueOf(localObject4[1]).intValue();
            break label755;
            label906:
            break label660;
            localObject2 = this.l;
            if ((paramMap == null) || (TextUtils.isEmpty((CharSequence)localObject2)) || (localObject1 == null)) {}
            for (localObject1 = null;; localObject1 = null)
            {
              new StringBuilder("it has GP(").append(this.l).append(") config!");
              break;
              localMap = yz.h(paramMap, new String[] { localObject2 });
              if (localMap != null) {
                break label987;
              }
            }
            label987:
            if (((yb.a)localObject1).a == yb.a.b.b) {}
            for (localObject1 = "Organic";; localObject1 = ((yb.a)localObject1).b)
            {
              localObject2 = yz.h(localMap, new String[] { localObject1 });
              localObject1 = localObject2;
              if (localObject2 != null) {
                break;
              }
              localObject1 = yz.h(localMap, new String[] { "Others" });
              break;
            }
          }
          label1064:
          n = -1;
          i1 = -1;
        }
      }
      localObject1 = null;
    }
  }
  
  private static yf.c b(Context paramContext, Map<String, ?> paramMap)
  {
    yf.c localC = new yf.c();
    if (paramMap == null) {
      return localC;
    }
    paramMap = yz.g(paramMap, new String[] { "RestrictedUser" });
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
        paramContext = yz.a((Map)localObject1, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localC.a = bool;
      localC.b = paramContext;
      return localC;
      n = TimeZone.getDefault().getRawOffset() / 3600000;
      Object localObject3 = yz.e((Map)localObject2, new String[] { "TimeZone" });
      if ((!TextUtils.isEmpty((CharSequence)localObject3)) && (!String.valueOf(n).equalsIgnoreCase((String)localObject3)))
      {
        n = 0;
        break label101;
      }
      localObject3 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
      Object localObject4 = yz.e((Map)localObject2, new String[] { "RegionFormat" });
      if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject3).equalsIgnoreCase((String)localObject4)))
      {
        n = 0;
        break label101;
      }
      localObject2 = yz.g((Map)localObject2, new String[] { "UrlScheme" });
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
      Map localMap1 = yz.h(paramMap, new String[] { "Data" });
      new StringBuilder("mergeRegions(), main data = ").append(localMap1);
      Map localMap2 = yz.h(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = yz.h(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = yz.h(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map<String, ?>)localObject;
        if (localObject == null) {
          paramMap = yz.h(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = yz.h(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          yz.a(localMap1, yz.h(paramMap, new String[] { "Data" }));
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
      Map localMap2 = yz.h(paramMap, new String[] { "Segments" });
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
                localMap1 = yz.h(localMap2, new String[] { str, "Data" });
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
          yz.a(yz.h(paramMap, new String[] { "Data" }), localMap1);
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
    yn localYn = yn.a(this.k, "remoteconfig");
    if (localYn.b.equals(localYn.a.getPackageName() + "_preferences")) {
      return localYn.b("updateTime");
    }
    yn.a(localYn.b, false, "should use getLongInterProcess() instead");
    return localYn.a.getSharedPreferences(localYn.b, 0).getLong("updateTime", 0L);
  }
  
  final void c()
  {
    yn localYn = yn.a(this.k, "remoteconfig");
    long l1 = System.currentTimeMillis();
    if (localYn.b.equals(localYn.a.getPackageName() + "_preferences")) {
      localYn.a("updateTime", l1);
    }
    for (;;)
    {
      if (yy.a()) {
        new StringBuilder("update last refresh time：").append(b());
      }
      return;
      yn.a(localYn.b, false, "should use putLongInterProcess() instead");
      localYn.a.getSharedPreferences(localYn.b, 0).edit().putLong("updateTime", l1).apply();
    }
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  public final void e()
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
          localObject1 = zb.a((File)localObject4);
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
        localObject3 = zb.a(localContext.getAssets(), str);
      }
    }
    if (localObject3 == null) {
      return;
    }
    a((Map)localObject3);
    try
    {
      this.b = yz.h((Map)localObject3, new String[] { "Data" });
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
      yn.a().b("hs.commons.config.remote.file.last.modify.info", c());
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
