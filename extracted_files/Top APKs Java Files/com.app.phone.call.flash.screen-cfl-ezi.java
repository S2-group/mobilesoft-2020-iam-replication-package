package cfl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import java.io.File;
import java.security.MessageDigest;
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

public class ezi
{
  public static int a;
  private static final String f = ezi.class.getSimpleName();
  private static final Pattern g = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static final Map<String, Integer> h;
  Map<String, ?> b;
  public Context c;
  public String d;
  public c e;
  private boolean i = edc.b();
  private String j;
  private String k;
  private final String l;
  private final String m = "c73df30d92c4d2ec.pa";
  private String n;
  private Thread o;
  private b p;
  private a q;
  
  static
  {
    a = -1;
    h = new HashMap();
    if (fab.b())
    {
      h.put("OrganicDebug", Integer.valueOf(3));
      h.put("SearchDebug", Integer.valueOf(4));
      h.put("NotOrganicDebug", Integer.valueOf(8));
      return;
    }
    h.put("OrganicEastEightDistrictRelease", Integer.valueOf(1));
    h.put("OrganicNotEastEightDistrictRelease", Integer.valueOf(3));
    h.put("SearchRelease", Integer.valueOf(4));
    h.put("NotOrganicEastEightDistrictRelease", Integer.valueOf(5));
    h.put("NotOrganicNotEastEightDistrictRelease", Integer.valueOf(8));
  }
  
  ezi(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, a paramA)
  {
    new StringBuilder("assetFileName=").append(paramString3).append("\rremotePlistUrl=").append(paramString2).append("\rdeleteCachedFile=").append(paramBoolean);
    this.c = paramContext;
    this.d = paramString3;
    this.j = paramString1;
    this.k = paramString2;
    if (TextUtils.isEmpty(paramString2)) {}
    for (paramContext = null;; paramContext = b(paramString2.substring(paramString2.lastIndexOf("/") + 1)))
    {
      this.l = paramContext;
      this.n = paramString4;
      this.p = b.a(ezt.b().b("hs.commons.config.remote.file.last.modify.info", ""));
      this.q = paramA;
      if (!fag.a(this.d)) {
        new StringBuilder("Using unencrypted plist file is not allowed, please use PA instead !!! ").append(this.d);
      }
      if (paramBoolean)
      {
        this.p.b();
        ezt.b(this.c, "remoteconfig").a();
        if ((!TextUtils.isEmpty("c73df30d92c4d2ec.pa")) && (this.c.getFilesDir() != null))
        {
          paramContext = new File(this.c.getFilesDir().getPath(), "c73df30d92c4d2ec.pa");
          if (paramContext.exists()) {
            paramContext.delete();
          }
        }
      }
      paramContext = eet.a(ecl.i(), "framework_config");
      if ((!paramContext.a("is_new_user_level_mode_4.7")) && (!TextUtils.isEmpty(paramString1))) {
        paramContext.b("is_new_user_level_mode_4.7", this.i);
      }
      if (a == -1) {
        a = ezt.b().b("hs.commons.config.Test_User_Token", -1);
      }
      if (-1 == a)
      {
        a = new Random(System.currentTimeMillis()).nextInt(1000);
        ezt.b().d("hs.commons.config.Test_User_Token", a);
      }
      if ((!TextUtils.isEmpty(paramString3)) || (!TextUtils.isEmpty("c73df30d92c4d2ec.pa"))) {
        f();
      }
      return;
    }
  }
  
  public static int a(eze.a paramA)
  {
    eze.a.b localB;
    if ((paramA == null) || (paramA.a == null) || (paramA.a == eze.a.b.a) || (paramA.a == eze.a.b.b))
    {
      localB = eze.a.b.b;
      if (!fab.b()) {
        break label119;
      }
      if ((paramA == null) || (!paramA.s)) {
        break label78;
      }
      paramA = (Integer)h.get("SearchDebug");
    }
    for (;;)
    {
      return paramA.intValue();
      localB = eze.a.b.c;
      break;
      label78:
      if (localB == eze.a.b.b)
      {
        paramA = (Integer)h.get("OrganicDebug");
      }
      else
      {
        paramA = (Integer)h.get("NotOrganicDebug");
        continue;
        label119:
        int i1 = TimeZone.getDefault().getRawOffset() / 3600000;
        if (localB == eze.a.b.b)
        {
          if (i1 == 8) {
            paramA = (Integer)h.get("OrganicEastEightDistrictRelease");
          } else {
            paramA = (Integer)h.get("OrganicNotEastEightDistrictRelease");
          }
        }
        else if ((paramA != null) && (paramA.s) && (i1 == 8)) {
          paramA = (Integer)h.get("OrganicEastEightDistrictRelease");
        } else if ((paramA != null) && (paramA.s) && (i1 != 8)) {
          paramA = (Integer)h.get("SearchRelease");
        } else if (((paramA == null) || (!paramA.s)) && (i1 == 8)) {
          paramA = (Integer)h.get("NotOrganicEastEightDistrictRelease");
        } else if (((paramA == null) || (!paramA.s)) && (i1 != 8)) {
          paramA = (Integer)h.get("NotOrganicNotEastEightDistrictRelease");
        } else {
          paramA = null;
        }
      }
    }
  }
  
  static long a()
  {
    long l2 = ezg.a(43200.0F, new String[] { "libCommons", "RemoteConfig", "UpdateInterval" });
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
  
  private String a(boolean paramBoolean, long paramLong)
  {
    bool1 = false;
    eet localEet = eet.a(ecl.i(), "framework_config");
    boolean bool2;
    Object localObject2;
    if ((edc.b()) && (localEet.a("is_url_first_get", true)))
    {
      bool2 = true;
      localObject2 = new JSONObject();
    }
    try
    {
      ((JSONObject)localObject2).put("bundle_id", this.c.getPackageName());
      if (ecm.c() == ecm.b.c) {
        break label589;
      }
      localObject1 = "null";
      label73:
      ((JSONObject)localObject2).put("custom_user_id", localObject1);
      ((JSONObject)localObject2).put("app_version", edc.f());
      ((JSONObject)localObject2).put("app_version_code", edc.e());
      localObject1 = Build.TAGS;
      if ((localObject1 == null) || (!((String)localObject1).contains("test-keys"))) {
        break label893;
      }
      i1 = 1;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject1;
        int i1;
        label135:
        eze.a localA;
        long l1;
        JSONObject localJSONObject;
        continue;
        for (;;)
        {
          if (i1 == 0) {
            break label597;
          }
          bool1 = true;
          break;
          str = "o";
          break label297;
          i1 = 0;
        }
        for (;;)
        {
          if (i1 == 0) {
            break label649;
          }
          bool1 = true;
          break;
          i1 = 0;
        }
        String str = "n";
      }
    }
    ((JSONObject)localObject2).put("is_root", bool1);
    ((JSONObject)localObject2).put("is_vpn", fae.a());
    ((JSONObject)localObject2).put("region", eeg.a().c());
    ((JSONObject)localObject2).put("tz", TimeZone.getDefault().getRawOffset() / 1000);
    ((JSONObject)localObject2).put("isTest", paramBoolean);
    ((JSONObject)localObject2).put("key", this.l);
    ((JSONObject)localObject2).put("is_debug", eep.b());
    ((JSONObject)localObject2).put("install_timestamp", eck.a());
    ((JSONObject)localObject2).put("is_first", bool2);
    localA = eze.a(this.c);
    if ((localA != null) && (localA.a != null) && (localA.a != eze.a.b.a))
    {
      if (localA.a != eze.a.b.b) {
        break label916;
      }
      break label885;
      label297:
      ((JSONObject)localObject2).put("as", localObject1);
      if (localA == null)
      {
        localObject1 = "";
        label317:
        ((JSONObject)localObject2).put("ac", localObject1);
        if (localA != null) {
          break label671;
        }
        localObject1 = "";
        label337:
        ((JSONObject)localObject2).put("ad", localObject1);
        if (TextUtils.isEmpty(eze.a)) {
          eze.a = ezf.b();
        }
        ((JSONObject)localObject2).put("ai", eze.a);
        ((JSONObject)localObject2).put("ak", edy.b(new String[] { "libCommons", "Analytics", "FlyerKey" }));
        ((JSONObject)localObject2).put("ul", a(localA));
        ((JSONObject)localObject2).put("iq", paramLong);
        paramLong = (System.currentTimeMillis() - eck.a()) / 1000L;
        l1 = eco.d();
        if (paramLong <= l1) {
          break label681;
        }
        label458:
        ((JSONObject)localObject2).put("install_timespan", paramLong);
        new StringBuilder("PlistServerUrl:").append(this.j).append(" RemoteUserLevelRequestJson:").append(((JSONObject)localObject2).toString());
        localObject1 = new eeb(this.j, eee.d.b, (JSONObject)localObject2, true);
        if (!paramBoolean) {
          break label687;
        }
        ((eeb)localObject1).a(12000);
      }
      for (;;)
      {
        ((eeb)localObject1).c(eeu.b(), eeu.a());
        ((eeb)localObject1).b(eeu.c(), eeu.d());
        ((eeb)localObject1).a();
        if (((eeb)localObject1).f() == null) {
          break label699;
        }
        throw new Throwable(((eeb)localObject1).f().toString());
        bool2 = false;
        break;
        label589:
        localObject1 = ecl.k();
        break label73;
        label597:
        if (fae.b())
        {
          bool1 = true;
          break label135;
        }
        if (fae.c())
        {
          bool1 = true;
          break label135;
        }
        if (fae.a(new String[] { "/system/xbin/which", "su" }) == null) {
          break label910;
        }
        i1 = 1;
        break label899;
        label649:
        if (!fae.d()) {
          break label135;
        }
        bool1 = true;
        break label135;
        localObject1 = localA.n;
        break label317;
        label671:
        localObject1 = localA.h;
        break label337;
        label681:
        paramLong = l1;
        break label458;
        label687:
        ((eeb)localObject1).a(60000);
      }
      label699:
      localObject1 = ((eeb)localObject1).e();
      try
      {
        i1 = ((JSONObject)localObject1).getJSONObject("meta").getInt("code");
        new StringBuilder("RemoteUserLevelResponseJson:").append(((JSONObject)localObject1).toString());
        if (i1 != 200) {
          throw new Throwable("response code is not 200");
        }
      }
      catch (Exception localException)
      {
        throw new Throwable("get plistUrl failed. Message:" + localException.getMessage());
      }
      localJSONObject = localException.getJSONObject("data");
      localObject2 = localJSONObject.getString("url");
      paramBoolean = localJSONObject.getBoolean("t");
      if (bool2) {
        localEet.b("is_url_first_get", false);
      }
      if (!paramBoolean)
      {
        localEet.b("hs.commons.config.remote.file.url.version", fai.a(ecl.i()));
        localEet.c("hs.commons.config.remote.file.url", (String)localObject2);
        ecr.b();
      }
      return localObject2;
    }
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
      return null;
    }
    int i1 = 0;
    while (i1 < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i1] & 0xFF);
      if (str.length() < 2) {
        localStringBuilder.append(0);
      }
      localStringBuilder.append(str);
      i1 += 1;
    }
    return localStringBuilder.toString();
  }
  
  private void a(Map<String, ?> paramMap)
  {
    b(paramMap);
    c(paramMap);
    Object localObject3 = eze.a(this.c);
    int i5;
    Object localObject1;
    if (fac.a(paramMap, new String[] { "UserLevel" })) {
      if (e())
      {
        localObject2 = null;
        i5 = a((eze.a)localObject2);
        if ((i5 < 0) || (paramMap == null) || (TextUtils.isEmpty("UserLevel")))
        {
          localObject1 = null;
          label72:
          if (localObject1 != null)
          {
            b((Map)localObject1);
            c((Map)localObject1);
          }
          new StringBuilder("it has userlevel(").append(i5).append(") config!");
          localObject3 = localObject2;
        }
      }
    }
    for (Object localObject2 = localObject1;; localObject2 = null)
    {
      label160:
      label199:
      label224:
      Map localMap;
      Object localObject4;
      int i3;
      Object localObject5;
      Pattern localPattern;
      String[] arrayOfString;
      int i1;
      int i2;
      if (fac.a(paramMap, new String[] { "GP" }))
      {
        localObject1 = this.n;
        if ((paramMap == null) || (TextUtils.isEmpty((CharSequence)localObject1)) || (localObject3 == null))
        {
          localObject1 = null;
          if (localObject1 != null)
          {
            b((Map)localObject1);
            c((Map)localObject1);
          }
          new StringBuilder("it has GP(").append(this.n).append(") config!");
          localObject3 = localObject2;
          if (localObject1 != null)
          {
            if (localObject2 == null) {
              break label946;
            }
            fac.a((Map)localObject1, (Map)localObject2);
            localObject3 = localObject1;
          }
          fac.a(paramMap, (Map)localObject3);
          return;
          localMap = fac.h(paramMap, new String[] { "UserLevel" });
          localObject1 = Pattern.compile("[0-9]+");
          if (localMap == null) {
            break label972;
          }
          localObject4 = localMap.keySet();
          if (fab.b())
          {
            localObject3 = new HashSet();
            localObject4 = ((Set)localObject4).iterator();
            i3 = 0;
            for (;;)
            {
              if (((Iterator)localObject4).hasNext())
              {
                localObject5 = (String)((Iterator)localObject4).next();
                if ("default".equals(localObject5))
                {
                  i3 = 1;
                }
                else
                {
                  localPattern = Pattern.compile("[0-9]+");
                  arrayOfString = ((String)localObject5).split("-");
                  if ((arrayOfString.length == 1) && (localPattern.matcher(arrayOfString[0].trim()).matches()))
                  {
                    i1 = Integer.valueOf(arrayOfString[0]).intValue();
                    i2 = i1;
                  }
                }
              }
            }
          }
        }
      }
      for (;;)
      {
        label391:
        int i4;
        if ((i1 != -1) && (i2 != -1))
        {
          i4 = i1;
          if (i2 >= i1) {}
        }
        else
        {
          new StringBuilder().append((String)localObject5).append(" 格式错误!");
          Process.killProcess(Process.myPid());
          i4 = i1;
        }
        label437:
        if (i4 <= i2)
        {
          if (((Set)localObject3).contains(Integer.valueOf(i4))) {
            break label548;
          }
          ((Set)localObject3).add(Integer.valueOf(i4));
        }
        for (;;)
        {
          i4 += 1;
          break label437;
          break;
          if ((arrayOfString.length != 2) || (!localPattern.matcher(arrayOfString[0].trim()).matches()) || (!localPattern.matcher(arrayOfString[1].trim()).matches())) {
            break label965;
          }
          i1 = Integer.valueOf(arrayOfString[0]).intValue();
          i2 = Integer.valueOf(arrayOfString[1]).intValue();
          break label391;
          label548:
          new StringBuilder().append((String)localObject5).append(" 多个用户等级有重叠!");
          Process.killProcess(Process.myPid());
        }
        if (i3 == 0) {
          Process.killProcess(Process.myPid());
        }
        localObject3 = localMap.keySet().iterator();
        for (;;)
        {
          label601:
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (String)((Iterator)localObject3).next();
            localObject5 = ((String)localObject4).split("-");
            i3 = -1;
            i4 = 0;
            if (localObject5.length == 1)
            {
              if ("default".equals(localObject5[0])) {
                continue;
              }
              i1 = i4;
              i2 = i3;
              if (((Pattern)localObject1).matcher(localObject5[0].trim()).matches())
              {
                i1 = Integer.valueOf(localObject5[0]).intValue();
                i2 = i1;
              }
              label696:
              if ((i2 == -1) || (i1 == -1) || (i2 > i5) || (i1 < i5)) {
                break label847;
              }
            }
          }
        }
        for (localObject3 = fac.h(localMap, new String[] { localObject4 });; localObject3 = null)
        {
          localObject1 = localObject3;
          if (localObject3 != null) {
            break;
          }
          localObject1 = fac.h(localMap, new String[] { "default" });
          break;
          i1 = i4;
          i2 = i3;
          if (localObject5.length != 2) {
            break label696;
          }
          i1 = i4;
          i2 = i3;
          if (!((Pattern)localObject1).matcher(localObject5[0].trim()).matches()) {
            break label696;
          }
          i1 = i4;
          i2 = i3;
          if (!((Pattern)localObject1).matcher(localObject5[1].trim()).matches()) {
            break label696;
          }
          i2 = Integer.valueOf(localObject5[0]).intValue();
          i1 = Integer.valueOf(localObject5[1]).intValue();
          break label696;
          label847:
          break label601;
          localMap = fac.h(paramMap, new String[] { localObject1 });
          if (localMap == null)
          {
            localObject1 = null;
            break label160;
          }
          if (((eze.a)localObject3).a == eze.a.b.b) {}
          for (localObject1 = "Organic";; localObject1 = ((eze.a)localObject3).b)
          {
            localObject3 = fac.h(localMap, new String[] { localObject1 });
            localObject1 = localObject3;
            if (localObject3 != null) {
              break;
            }
            localObject1 = fac.h(localMap, new String[] { "Others" });
            break;
          }
          label946:
          localObject3 = localObject1;
          break label224;
          localObject1 = null;
          break label199;
        }
        label965:
        i1 = -1;
        i2 = -1;
      }
      label972:
      localObject1 = null;
      break label72;
      localObject2 = localObject3;
      break;
    }
  }
  
  private static c b(Context paramContext, Map<String, ?> paramMap)
  {
    c localC = new c();
    if (paramMap == null) {
      return localC;
    }
    paramMap = fac.g(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() <= 0)) {
      return localC;
    }
    paramContext = paramContext.getPackageManager().getInstalledApplications(8192);
    paramMap = paramMap.iterator();
    Object localObject2;
    int i1;
    if (paramMap.hasNext())
    {
      Object localObject1 = paramMap.next();
      localObject2 = (Map)localObject1;
      if (((Map)localObject2).isEmpty())
      {
        i1 = 0;
        label101:
        if (i1 == 0) {
          break label393;
        }
        paramContext = fac.a((Map)localObject1, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localC.a = bool;
      localC.b = paramContext;
      return localC;
      i1 = TimeZone.getDefault().getRawOffset() / 3600000;
      Object localObject3 = fac.e((Map)localObject2, new String[] { "TimeZone" });
      if ((!TextUtils.isEmpty((CharSequence)localObject3)) && (!String.valueOf(i1).equalsIgnoreCase((String)localObject3)))
      {
        i1 = 0;
        break label101;
      }
      localObject3 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
      Object localObject4 = fac.e((Map)localObject2, new String[] { "RegionFormat" });
      if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject3).equalsIgnoreCase((String)localObject4)))
      {
        i1 = 0;
        break label101;
      }
      localObject2 = fac.g((Map)localObject2, new String[] { "UrlScheme" });
      if ((localObject2 == null) || (((List)localObject2).isEmpty()))
      {
        i1 = 1;
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
        for (i1 = 1;; i1 = 0)
        {
          if (i1 != 0) {
            break label398;
          }
          i1 = 0;
          break label101;
          i1 = 1;
          break label101;
          break;
        }
      }
      paramContext = "";
    }
  }
  
  /* Error */
  private static String b(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 789	java/io/File:isFile	()Z
    //   4: ifne +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: sipush 1024
    //   12: newarray byte
    //   14: astore 4
    //   16: ldc_w 791
    //   19: invokestatic 797	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   22: astore_3
    //   23: new 799	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 802	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   31: astore_2
    //   32: aload_2
    //   33: astore_0
    //   34: aload_2
    //   35: aload 4
    //   37: iconst_0
    //   38: sipush 1024
    //   41: invokevirtual 806	java/io/FileInputStream:read	([BII)I
    //   44: istore_1
    //   45: iload_1
    //   46: iconst_m1
    //   47: if_icmpeq +40 -> 87
    //   50: aload_2
    //   51: astore_0
    //   52: aload_3
    //   53: aload 4
    //   55: iconst_0
    //   56: iload_1
    //   57: invokevirtual 810	java/security/MessageDigest:update	([BII)V
    //   60: goto -28 -> 32
    //   63: astore_3
    //   64: aload_2
    //   65: astore_0
    //   66: aload_3
    //   67: invokestatic 815	cfl/dpi:a	(Ljava/lang/Throwable;)V
    //   70: aload_2
    //   71: ifnull -64 -> 7
    //   74: aload_2
    //   75: invokevirtual 818	java/io/FileInputStream:close	()V
    //   78: aconst_null
    //   79: areturn
    //   80: astore_0
    //   81: aload_0
    //   82: invokestatic 815	cfl/dpi:a	(Ljava/lang/Throwable;)V
    //   85: aconst_null
    //   86: areturn
    //   87: aload_2
    //   88: invokevirtual 818	java/io/FileInputStream:close	()V
    //   91: aload_3
    //   92: invokevirtual 822	java/security/MessageDigest:digest	()[B
    //   95: invokestatic 824	cfl/ezi:a	([B)Ljava/lang/String;
    //   98: areturn
    //   99: astore_0
    //   100: aload_0
    //   101: invokestatic 815	cfl/dpi:a	(Ljava/lang/Throwable;)V
    //   104: goto -13 -> 91
    //   107: astore_2
    //   108: aconst_null
    //   109: astore_0
    //   110: aload_0
    //   111: ifnull +7 -> 118
    //   114: aload_0
    //   115: invokevirtual 818	java/io/FileInputStream:close	()V
    //   118: aload_2
    //   119: athrow
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 815	cfl/dpi:a	(Ljava/lang/Throwable;)V
    //   125: goto -7 -> 118
    //   128: astore_2
    //   129: goto -19 -> 110
    //   132: astore_3
    //   133: aconst_null
    //   134: astore_2
    //   135: goto -71 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	138	0	paramFile	File
    //   44	13	1	i1	int
    //   31	57	2	localFileInputStream	java.io.FileInputStream
    //   107	12	2	localObject1	Object
    //   128	1	2	localObject2	Object
    //   134	1	2	localObject3	Object
    //   22	31	3	localMessageDigest	MessageDigest
    //   63	29	3	localException1	Exception
    //   132	1	3	localException2	Exception
    //   14	40	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   34	45	63	java/lang/Exception
    //   52	60	63	java/lang/Exception
    //   74	78	80	java/io/IOException
    //   87	91	99	java/io/IOException
    //   16	32	107	finally
    //   114	118	120	java/io/IOException
    //   34	45	128	finally
    //   52	60	128	finally
    //   66	70	128	finally
    //   16	32	132	java/lang/Exception
  }
  
  private static String b(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = new StringBuilder();
      localObject = ((MessageDigest)localObject).digest();
      int i2 = localObject.length;
      int i1 = 0;
      while (i1 < i2)
      {
        int i3 = localObject[i1];
        paramString.append(Integer.toHexString(i3 >> 4 & 0xF));
        paramString.append(Integer.toHexString(i3 & 0xF));
        i1 += 1;
      }
      paramString = paramString.toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      dpi.a(paramString);
    }
    return null;
  }
  
  private static void b(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = fac.h(paramMap, new String[] { "Data" });
      new StringBuilder("mergeRegions(), main data = ").append(localMap1);
      Map localMap2 = fac.h(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = fac.h(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = fac.h(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map<String, ?>)localObject;
        if (localObject == null) {
          paramMap = fac.h(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = fac.h(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          fac.a(localMap1, fac.h(paramMap, new String[] { "Data" }));
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
      Map localMap2 = fac.h(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        int i1 = Integer.MAX_VALUE;
        Iterator localIterator = localMap2.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = str.replace(" ", "");
          if (g.matcher((CharSequence)localObject).matches())
          {
            localObject = ((String)localObject).split("-");
            if (2 == localObject.length)
            {
              int i2 = Integer.valueOf(localObject[0]).intValue();
              int i3 = Integer.valueOf(localObject[1]).intValue();
              if (i2 <= i3)
              {
                if ((a < i2) || (a > i3) || (i2 >= i1)) {
                  break label210;
                }
                localMap1 = fac.h(localMap2, new String[] { str, "Data" });
                i1 = i2;
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
          fac.a(fac.h(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  public static int d()
  {
    return a;
  }
  
  public static boolean g()
  {
    return eet.a(ecl.i(), "framework_config").a("hs.commons.config.remote.file.url.version", 0L) != edc.e();
  }
  
  final void a(final String paramString)
  {
    paramString = new Thread(new Runnable()
    {
      public final void run()
      {
        JSONObject localJSONObject = new JSONObject();
        for (;;)
        {
          try
          {
            localJSONObject.put("bundle_id", ezi.b(ezi.this).getPackageName());
            if (ecm.c() == ecm.b.c) {
              continue;
            }
            localObject = "null";
            localJSONObject.put("custom_user_id", localObject);
            localJSONObject.put("app_version", edc.f());
            localJSONObject.put("app_version_code", edc.e());
            localJSONObject.put("app_os_version_code", edc.g());
            localJSONObject.put("cf_ul", edy.a("no_configure", new String[] { "UserLevel" }));
            localJSONObject.put("clt_ul", ezi.a(eze.a(ezi.b(ezi.this))));
            localJSONObject.put("install_timestamp", eck.a());
            localJSONObject.put("now_time", System.currentTimeMillis());
            String str = ezi.a(new File(ezi.b(ezi.this).getFilesDir().getPath(), "c73df30d92c4d2ec.pa"));
            localObject = str;
            if (TextUtils.isEmpty(str)) {
              localObject = "";
            }
            localJSONObject.put("file_md5", localObject);
            localJSONObject.put("url", ezi.c(ezi.this));
            localJSONObject.put("t", ezi.g());
            if (!TextUtils.isEmpty(paramString)) {
              continue;
            }
            localObject = "";
            localJSONObject.put("errorInfo", localObject);
            l1 = (System.currentTimeMillis() - eck.a()) / 1000L;
            l2 = eco.d();
            if (l1 <= l2) {
              continue;
            }
            localJSONObject.put("install_timespan", l1);
          }
          catch (JSONException localJSONException)
          {
            Object localObject;
            long l1;
            long l2;
            continue;
          }
          ezi.h();
          new StringBuilder("logToServer json:").append(localJSONObject.toString());
          localObject = new eeb(ezi.i(), eee.d.b, localJSONObject, true);
          ((eeb)localObject).c(eeu.b(), eeu.a());
          ((eeb)localObject).b(eeu.c(), eeu.d());
          ((eeb)localObject).a();
          if (((eeb)localObject).c()) {
            continue;
          }
          ezi.h();
          new StringBuilder("logToServer failed").append(((eeb)localObject).f());
          return;
          localObject = ecl.k();
          continue;
          localObject = paramString;
          continue;
          l1 = l2;
        }
        ezi.h();
        new StringBuilder("logToServer succeed").append(((eeb)localObject).d());
      }
    });
    paramString.setPriority(1);
    paramString.start();
  }
  
  public final boolean a(boolean paramBoolean1, final boolean paramBoolean2)
  {
    final int i2 = 0;
    if (TextUtils.isEmpty(this.k)) {
      this.q.a(false, false);
    }
    for (;;)
    {
      return true;
      int i1;
      if (!paramBoolean1)
      {
        if ((System.currentTimeMillis() <= b()) || (System.currentTimeMillis() - b() >= a()) || (b() == 0L)) {
          if (fab.a()) {
            new StringBuilder("Time is expired：").append(b()).append(":").append(a());
          }
        }
        for (i1 = 1; i1 == 0; i1 = 0) {
          return false;
        }
      }
      if (this.o != null) {
        return false;
      }
      this.o = new Thread(new Runnable()
      {
        public final void run()
        {
          for (;;)
          {
            try
            {
              if ((ezi.this.e()) && (paramBoolean2))
              {
                Object localObject1 = eet.a(ecl.i(), "framework_config");
                if (((eet)localObject1).a("hs.commons.config.remote.file.url.version", 0L) == edc.e())
                {
                  localObject1 = ((eet)localObject1).a("hs.commons.config.remote.file.url", "");
                  if (TextUtils.isEmpty((CharSequence)localObject1)) {
                    ezi.a(ezi.this).a(false, false);
                  }
                }
                else
                {
                  try
                  {
                    localObject1 = ezi.a(ezi.this, false, 0L);
                  }
                  catch (Throwable localThrowable)
                  {
                    new StringBuilder("error info:").append(localThrowable.getMessage());
                    localObject2 = null;
                  }
                  continue;
                }
                ezi.a(ezi.this, (String)localObject2);
              }
              if (ezi.b(ezi.this).getFilesDir() == null)
              {
                ezi.a(ezi.this).a(false, false);
                return;
              }
              Object localObject5 = ezi.b(ezi.this).getFilesDir().getPath();
              Object localObject2 = new File((String)localObject5);
              if (!((File)localObject2).exists()) {
                ((File)localObject2).mkdir();
              }
              localObject2 = new File((String)localObject5, "temp.c73df30d92c4d2ec.pa");
              ezk localEzk = new ezk(ezi.c(ezi.this));
              if (ezi.c(ezi.this).equals(ezi.d(ezi.this).a))
              {
                localObject6 = new HashMap();
                if (!TextUtils.isEmpty(ezi.d(ezi.this).b)) {
                  ((Map)localObject6).put("If-Modified-Since", ezi.d(ezi.this).b);
                }
                if (!TextUtils.isEmpty(ezi.d(ezi.this).c)) {
                  ((Map)localObject6).put("If-None-Match", ezi.d(ezi.this).c);
                }
                if (!((Map)localObject6).isEmpty()) {
                  localEzk.a((Map)localObject6);
                }
              }
              localEzk.a(10000).b(30000);
              localEzk.a((File)localObject2);
              localEzk.a();
              if (!localEzk.d())
              {
                ezi.this.a("not succeeded:" + localEzk.j + "error:" + localEzk.e());
                ezi.a(ezi.this).a(false, false);
                return;
              }
              if (localEzk.j == 304)
              {
                ezi.this.c();
                ezi.a(ezi.this).a(false, true);
                return;
              }
              Object localObject6 = faf.a((File)localObject2);
              ezi.a(ezi.this, (Map)localObject6);
              localObject6 = fac.h((Map)localObject6, new String[] { "Data" });
              if (localObject6 == null)
              {
                new StringBuilder("code:").append(localEzk.j).append("fetch(), parser stream failed");
                ezi.this.a("fetch(), parser stream failed");
                ezi.a(ezi.this).a(false, false);
                return;
              }
              localObject5 = new File((String)localObject5, "c73df30d92c4d2ec.pa");
              if (((File)localObject5).exists())
              {
                ezi.b localB = ezi.d(ezi.this);
                ezi.b(ezi.this);
                localB.b();
                ((File)localObject5).delete();
              }
              if (!((File)localObject2).renameTo((File)localObject5))
              {
                ezi.this.a("fetch(), rename temp to plist file name failed");
                ezi.a(ezi.this).a(false, false);
                return;
              }
              ezi.d(ezi.this).a = ezi.c(ezi.this);
              ezi.d(ezi.this).b = ((String)localEzk.k.get("Last-Modified"));
              ezi.d(ezi.this).c = ((String)localEzk.k.get("Etag"));
              localObject2 = ezi.d(ezi.this);
              ezi.b(ezi.this);
              ((ezi.b)localObject2).a();
              new StringBuilder("RemoteConfig modified Last-Modified: ").append(ezi.d(ezi.this).b).append(" ETag: ").append(ezi.d(ezi.this).c);
              try
              {
                if ((ezi.e(ezi.this) == null) || (!ezi.e(ezi.this).equals(localObject6)))
                {
                  ezi.b(ezi.this, (Map)localObject6);
                  ezi.a(ezi.this, ezi.a(ezi.b(ezi.this), (Map)localObject6));
                  bool = true;
                  ezi.this.c();
                  ezi.a(ezi.this).a(bool, true);
                  return;
                }
              }
              finally {}
              boolean bool = false;
            }
            finally
            {
              ezi.f(ezi.this);
            }
          }
        }
      });
      this.o.start();
      if ((!TextUtils.isEmpty(this.j)) && (this.i))
      {
        this.i = false;
        final HandlerThread localHandlerThread = new HandlerThread("FetchPlistServerUrl");
        localHandlerThread.start();
        Handler localHandler = new Handler(localHandlerThread.getLooper());
        i1 = i2;
        while (i1 < 12)
        {
          i2 = new int[] { 15, 30, 60, 90, 120, 360, 1800, 3600, 7200, 10800, 14400, 21600 }[i1];
          localHandler.postDelayed(new Runnable()
          {
            public final void run()
            {
              if (ecm.c() != ecm.b.c) {}
              for (;;)
              {
                return;
                try
                {
                  String str = ezi.a(ezi.this, true, i2);
                  if ((!TextUtils.isEmpty(str)) && (!TextUtils.equals(ezi.c(ezi.this), str)))
                  {
                    ezi.a(ezi.this, str);
                    ezi.g(ezi.this);
                  }
                  if (!ezi.g())
                  {
                    localHandlerThread.quit();
                    return;
                  }
                }
                catch (Throwable localThrowable)
                {
                  ezi.h();
                  localThrowable.getMessage();
                }
              }
            }
          }, i2 * 1000);
          i1 += 1;
        }
      }
    }
  }
  
  public final long b()
  {
    return ezt.b(this.c, "remoteconfig").a("updateTime", 0L);
  }
  
  final void c()
  {
    ezt.b(this.c, "remoteconfig").c("updateTime", System.currentTimeMillis());
    if (fab.a()) {
      new StringBuilder("update last refresh time：").append(b());
    }
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  public final boolean e()
  {
    boolean bool = true;
    if (TextUtils.isEmpty(this.j)) {
      bool = false;
    }
    while (TextUtils.isEmpty(this.k)) {
      return bool;
    }
    return eet.a(ecl.i(), "framework_config").a("is_new_user_level_mode_4.7", true);
  }
  
  public final void f()
  {
    Context localContext = this.c;
    String str = this.d;
    Object localObject3 = null;
    Object localObject1 = localObject3;
    if (!TextUtils.isEmpty("c73df30d92c4d2ec.pa"))
    {
      localObject1 = localObject3;
      if (localContext.getFilesDir() != null)
      {
        File localFile = new File(localContext.getFilesDir().getPath(), "c73df30d92c4d2ec.pa");
        localObject1 = localObject3;
        if (localFile.exists()) {
          localObject1 = faf.a(localFile);
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
        localObject3 = faf.a(localContext.getAssets(), str);
      }
    }
    if (localObject3 == null) {
      return;
    }
    a((Map)localObject3);
    try
    {
      this.b = fac.h((Map)localObject3, new String[] { "Data" });
      this.e = b(this.c, this.b);
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
        dpi.a(paramString);
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
        dpi.a(localJSONException);
      }
      return null;
    }
    
    final void a()
    {
      ezt.b().d("hs.commons.config.remote.file.last.modify.info", c());
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
