package net.appcloudbox.common.config;

import android.arch.b.a.a.f;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import com.ihs.app.framework.r;
import com.ihs.commons.a.o;
import com.ihs.commons.e.p;
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
import net.appcloudbox.common.analytics.c.d;
import org.json.JSONException;
import org.json.JSONObject;

class g
{
  private static final String a = g.class.getSimpleName();
  private static final Pattern b = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static int c = -1;
  private static final Map d = new HashMap();
  private boolean e = com.ihs.app.b.a.b();
  private Map f;
  private Context g;
  private String h;
  private String i;
  private final String j;
  private String k;
  private String l;
  private m m;
  private Thread n;
  private l o;
  private k p;
  
  static
  {
    if (net.appcloudbox.common.utils.l.b())
    {
      d.put("OrganicDebug", Integer.valueOf(3));
      d.put("SearchDebug", Integer.valueOf(4));
      d.put("NotOrganicDebug", Integer.valueOf(8));
      return;
    }
    d.put("OrganicEastEightDistrictRelease", Integer.valueOf(1));
    d.put("OrganicNotEastEightDistrictRelease", Integer.valueOf(3));
    d.put("SearchRelease", Integer.valueOf(4));
    d.put("NotOrganicEastEightDistrictRelease", Integer.valueOf(5));
    d.put("NotOrganicNotEastEightDistrictRelease", Integer.valueOf(8));
  }
  
  g(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, k paramK)
  {
    new StringBuilder("assetFileName=").append(paramString3).append("\rremotePlistUrl=").append(paramString2).append("\rdeleteCachedFile=").append(paramBoolean);
    this.g = paramContext;
    this.k = paramString3;
    this.h = paramString1;
    this.i = paramString2;
    if (TextUtils.isEmpty(paramString2)) {}
    for (paramString2 = null;; paramString2 = b(paramString2.substring(paramString2.lastIndexOf("/") + 1)))
    {
      this.j = paramString2;
      this.l = paramString4;
      this.o = l.a(net.appcloudbox.common.preference.c.b().b("hs.commons.config.remote.file.last.modify.info", ""));
      this.p = paramK;
      if (!net.appcloudbox.common.utils.n.a(this.k)) {
        new StringBuilder("Using unencrypted plist file is not allowed, please use PA instead !!! ").append(this.k);
      }
      if (paramBoolean)
      {
        this.o.a(paramContext);
        net.appcloudbox.common.preference.c.b(this.g, "remoteconfig").a();
        if ((!TextUtils.isEmpty("c73df30d92c4d2ec.pa")) && (this.g.getFilesDir() != null))
        {
          paramContext = new File(this.g.getFilesDir().getPath(), "c73df30d92c4d2ec.pa");
          if (paramContext.exists()) {
            paramContext.delete();
          }
        }
      }
      paramContext = com.ihs.commons.e.n.a(com.ihs.app.framework.c.a(), "framework_config");
      if ((!paramContext.a("is_new_user_level_mode_4.7")) && (!TextUtils.isEmpty(paramString1))) {
        paramContext.c("is_new_user_level_mode_4.7", this.e);
      }
      if (c == -1) {
        c = net.appcloudbox.common.preference.c.b().a("hs.commons.config.Test_User_Token", -1);
      }
      if (-1 == c)
      {
        c = new Random(System.currentTimeMillis()).nextInt(1000);
        net.appcloudbox.common.preference.c.b().c("hs.commons.config.Test_User_Token", c);
      }
      if ((!TextUtils.isEmpty(paramString3)) || (!TextUtils.isEmpty("c73df30d92c4d2ec.pa"))) {
        n();
      }
      return;
    }
  }
  
  static int a(net.appcloudbox.common.analytics.c.b paramB)
  {
    d localD;
    if ((paramB == null) || (paramB.b() == null) || (paramB.b() == d.a) || (paramB.b() == d.b))
    {
      localD = d.b;
      if (!net.appcloudbox.common.utils.l.b()) {
        break label119;
      }
      if ((paramB == null) || (!paramB.a())) {
        break label78;
      }
      paramB = (Integer)d.get("SearchDebug");
    }
    for (;;)
    {
      return paramB.intValue();
      localD = d.c;
      break;
      label78:
      if (localD == d.b)
      {
        paramB = (Integer)d.get("OrganicDebug");
      }
      else
      {
        paramB = (Integer)d.get("NotOrganicDebug");
        continue;
        label119:
        int i1 = TimeZone.getDefault().getRawOffset() / 3600000;
        if (localD == d.b)
        {
          if (i1 == 8) {
            paramB = (Integer)d.get("OrganicEastEightDistrictRelease");
          } else {
            paramB = (Integer)d.get("OrganicNotEastEightDistrictRelease");
          }
        }
        else if ((paramB != null) && (paramB.a()) && (i1 == 8)) {
          paramB = (Integer)d.get("OrganicEastEightDistrictRelease");
        } else if ((paramB != null) && (paramB.a()) && (i1 != 8)) {
          paramB = (Integer)d.get("SearchRelease");
        } else if (((paramB == null) || (!paramB.a())) && (i1 == 8)) {
          paramB = (Integer)d.get("NotOrganicEastEightDistrictRelease");
        } else if (((paramB == null) || (!paramB.a())) && (i1 != 8)) {
          paramB = (Integer)d.get("NotOrganicNotEastEightDistrictRelease");
        } else {
          paramB = null;
        }
      }
    }
  }
  
  static long a()
  {
    long l2 = a.a(43200.0F, new String[] { "libCommons", "RemoteConfig", "UpdateInterval" });
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
    boolean bool2 = false;
    com.ihs.commons.e.n localN = com.ihs.commons.e.n.a(com.ihs.app.framework.c.a(), "framework_config");
    boolean bool1 = bool2;
    if (com.ihs.app.b.a.b())
    {
      bool1 = bool2;
      if (localN.a("is_url_first_get", true)) {
        bool1 = true;
      }
    }
    Object localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("bundle_id", this.g.getPackageName());
      if (com.ihs.app.framework.m.c() == r.c) {
        break label550;
      }
      localObject1 = "null";
      ((JSONObject)localObject2).put("custom_user_id", localObject1);
      ((JSONObject)localObject2).put("app_version", com.ihs.app.b.a.f());
      ((JSONObject)localObject2).put("app_version_code", com.ihs.app.b.a.e());
      ((JSONObject)localObject2).put("is_root", android.support.d.a.g.F());
      ((JSONObject)localObject2).put("is_vpn", android.support.d.a.g.E());
      ((JSONObject)localObject2).put("region", com.ihs.commons.b.a.a().c());
      ((JSONObject)localObject2).put("tz", TimeZone.getDefault().getRawOffset() / 1000);
      ((JSONObject)localObject2).put("isTest", paramBoolean);
      ((JSONObject)localObject2).put("key", this.j);
      ((JSONObject)localObject2).put("is_debug", com.ihs.commons.e.h.b());
      ((JSONObject)localObject2).put("install_timestamp", android.support.d.a.g.r());
      ((JSONObject)localObject2).put("is_first", bool1);
      localB = net.appcloudbox.common.analytics.c.a.a(this.g);
      if ((localB == null) || (localB.b() == null) || (localB.b() == d.a)) {
        break label771;
      }
      if (localB.b() != d.b) {
        break label779;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject1;
        net.appcloudbox.common.analytics.c.b localB;
        label299:
        label319:
        long l1;
        label425:
        JSONObject localJSONObject;
        continue;
        String str = "o";
        continue;
        str = "n";
      }
    }
    ((JSONObject)localObject2).put("as", localObject1);
    if (localB == null)
    {
      localObject1 = "";
      ((JSONObject)localObject2).put("ac", localObject1);
      if (localB != null) {
        break label568;
      }
      localObject1 = "";
      ((JSONObject)localObject2).put("ad", localObject1);
      ((JSONObject)localObject2).put("ai", net.appcloudbox.common.analytics.c.a.b());
      ((JSONObject)localObject2).put("ak", com.ihs.commons.config.a.b(new String[] { "libCommons", "Analytics", "FlyerKey" }));
      ((JSONObject)localObject2).put("ul", a(localB));
      ((JSONObject)localObject2).put("iq", paramLong);
      paramLong = (System.currentTimeMillis() - android.support.d.a.g.r()) / 1000L;
      l1 = android.support.d.a.g.v();
      if (paramLong <= l1) {
        break label578;
      }
      ((JSONObject)localObject2).put("install_timespan", paramLong);
      new StringBuilder("PlistServerUrl:").append(this.h).append(" RemoteUserLevelRequestJson:").append(((JSONObject)localObject2).toString());
      localObject1 = new o(this.h, com.ihs.commons.a.a.g.b, (JSONObject)localObject2, true);
      if (!paramBoolean) {
        break label584;
      }
      ((o)localObject1).a(12000);
    }
    for (;;)
    {
      ((o)localObject1).c(p.b(), p.a());
      ((o)localObject1).b(p.c(), p.d());
      ((o)localObject1).a();
      if (((o)localObject1).f() == null) {
        break label596;
      }
      throw new Throwable(((o)localObject1).f().toString());
      label550:
      localObject1 = com.ihs.app.framework.c.c();
      break;
      localObject1 = localB.e();
      break label299;
      label568:
      localObject1 = localB.d();
      break label319;
      label578:
      paramLong = l1;
      break label425;
      label584:
      ((o)localObject1).a(60000);
    }
    label596:
    localObject1 = ((o)localObject1).e();
    try
    {
      int i1 = ((JSONObject)localObject1).getJSONObject("meta").getInt("code");
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
    if (bool1) {
      localN.c("is_url_first_get", false);
    }
    if (!paramBoolean)
    {
      localN.c("hs.commons.config.remote.file.url.version", android.support.d.a.g.j(com.ihs.app.framework.c.a()));
      localN.c("hs.commons.config.remote.file.url", (String)localObject2);
      com.ihs.app.framework.inner.a.b();
    }
    return localObject2;
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
  
  private void a(Map paramMap)
  {
    b(paramMap);
    c(paramMap);
    Object localObject3 = net.appcloudbox.common.analytics.c.a.a(this.g);
    int i5;
    Object localObject1;
    if (f.a(paramMap, new String[] { "UserLevel" })) {
      if (h())
      {
        localObject2 = null;
        i5 = a((net.appcloudbox.common.analytics.c.b)localObject2);
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
      if (f.a(paramMap, new String[] { "GP" }))
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
              break label946;
            }
            f.a((Map)localObject1, (Map)localObject2);
            localObject3 = localObject1;
          }
          f.a(paramMap, (Map)localObject3);
          return;
          localMap = f.h(paramMap, new String[] { "UserLevel" });
          localObject1 = Pattern.compile("[0-9]+");
          if (localMap == null) {
            break label972;
          }
          localObject4 = localMap.keySet();
          if (net.appcloudbox.common.utils.l.b())
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
        for (localObject3 = f.h(localMap, new String[] { localObject4 });; localObject3 = null)
        {
          localObject1 = localObject3;
          if (localObject3 != null) {
            break;
          }
          localObject1 = f.h(localMap, new String[] { "default" });
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
          localMap = f.h(paramMap, new String[] { localObject1 });
          if (localMap == null)
          {
            localObject1 = null;
            break label160;
          }
          if (((net.appcloudbox.common.analytics.c.b)localObject3).b() == d.b) {}
          for (localObject1 = "Organic";; localObject1 = ((net.appcloudbox.common.analytics.c.b)localObject3).c())
          {
            localObject3 = f.h(localMap, new String[] { localObject1 });
            localObject1 = localObject3;
            if (localObject3 != null) {
              break;
            }
            localObject1 = f.h(localMap, new String[] { "Others" });
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
  
  private boolean a(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i2 = 0;
    if (TextUtils.isEmpty(this.i)) {
      this.p.a(false, false);
    }
    for (;;)
    {
      return true;
      int i1;
      if (!paramBoolean1)
      {
        if ((System.currentTimeMillis() <= b()) || (System.currentTimeMillis() - b() >= a()) || (b() == 0L)) {
          if (net.appcloudbox.common.utils.l.a()) {
            new StringBuilder("Time is expired：").append(b()).append(":").append(a());
          }
        }
        for (i1 = 1; i1 == 0; i1 = 0) {
          return false;
        }
      }
      if (this.n != null) {
        return false;
      }
      this.n = new Thread(new h(this, paramBoolean2));
      this.n.start();
      if ((!TextUtils.isEmpty(this.h)) && (this.e))
      {
        this.e = false;
        HandlerThread localHandlerThread = new HandlerThread("FetchPlistServerUrl");
        localHandlerThread.start();
        Handler localHandler = new Handler(localHandlerThread.getLooper());
        i1 = i2;
        while (i1 < 12)
        {
          i2 = new int[] { 15, 30, 60, 90, 120, 360, 1800, 3600, 7200, 10800, 14400, 21600 }[i1];
          localHandler.postDelayed(new i(this, i2, localHandlerThread), i2 * 1000);
          i1 += 1;
        }
      }
    }
  }
  
  /* Error */
  private static String b(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 743	java/io/File:isFile	()Z
    //   4: ifne +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: sipush 1024
    //   12: newarray byte
    //   14: astore 4
    //   16: ldc_w 745
    //   19: invokestatic 751	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   22: astore_3
    //   23: new 753	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 756	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   31: astore_2
    //   32: aload_2
    //   33: astore_0
    //   34: aload_2
    //   35: aload 4
    //   37: iconst_0
    //   38: sipush 1024
    //   41: invokevirtual 760	java/io/FileInputStream:read	([BII)I
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
    //   57: invokevirtual 764	java/security/MessageDigest:update	([BII)V
    //   60: goto -28 -> 32
    //   63: astore_3
    //   64: aload_2
    //   65: astore_0
    //   66: aload_3
    //   67: invokestatic 769	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   70: aload_2
    //   71: ifnull -64 -> 7
    //   74: aload_2
    //   75: invokevirtual 772	java/io/FileInputStream:close	()V
    //   78: aconst_null
    //   79: areturn
    //   80: astore_0
    //   81: aload_0
    //   82: invokestatic 769	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   85: aconst_null
    //   86: areturn
    //   87: aload_2
    //   88: invokevirtual 772	java/io/FileInputStream:close	()V
    //   91: aload_3
    //   92: invokevirtual 776	java/security/MessageDigest:digest	()[B
    //   95: invokestatic 778	net/appcloudbox/common/config/g:a	([B)Ljava/lang/String;
    //   98: areturn
    //   99: astore_0
    //   100: aload_0
    //   101: invokestatic 769	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   104: goto -13 -> 91
    //   107: astore_2
    //   108: aconst_null
    //   109: astore_0
    //   110: aload_0
    //   111: ifnull +7 -> 118
    //   114: aload_0
    //   115: invokevirtual 772	java/io/FileInputStream:close	()V
    //   118: aload_2
    //   119: athrow
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 769	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
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
      com.google.a.a.a.a.a.a.a(paramString);
    }
    return null;
  }
  
  private static m b(Context paramContext, Map paramMap)
  {
    m localM = new m();
    if (paramMap == null) {
      return localM;
    }
    paramMap = f.g(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() <= 0)) {
      return localM;
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
        paramContext = f.a((Map)localObject1, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localM.a = bool;
      localM.b = paramContext;
      return localM;
      i1 = TimeZone.getDefault().getRawOffset() / 3600000;
      Object localObject3 = f.e((Map)localObject2, new String[] { "TimeZone" });
      if ((!TextUtils.isEmpty((CharSequence)localObject3)) && (!String.valueOf(i1).equalsIgnoreCase((String)localObject3)))
      {
        i1 = 0;
        break label101;
      }
      localObject3 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
      Object localObject4 = f.e((Map)localObject2, new String[] { "RegionFormat" });
      if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject3).equalsIgnoreCase((String)localObject4)))
      {
        i1 = 0;
        break label101;
      }
      localObject2 = f.g((Map)localObject2, new String[] { "UrlScheme" });
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
  
  private static void b(Map paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = f.h(paramMap, new String[] { "Data" });
      new StringBuilder("mergeRegions(), main data = ").append(localMap1);
      Map localMap2 = f.h(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = f.h(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = f.h(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map)localObject;
        if (localObject == null) {
          paramMap = f.h(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = f.h(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          f.a(localMap1, f.h(paramMap, new String[] { "Data" }));
          return;
        }
      }
    }
  }
  
  private static void c(Map paramMap)
  {
    if (paramMap == null) {}
    label210:
    label211:
    for (;;)
    {
      return;
      Map localMap2 = f.h(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        int i1 = Integer.MAX_VALUE;
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
              int i2 = Integer.valueOf(localObject[0]).intValue();
              int i3 = Integer.valueOf(localObject[1]).intValue();
              if (i2 <= i3)
              {
                if ((c < i2) || (c > i3) || (i2 >= i1)) {
                  break label210;
                }
                localMap1 = f.h(localMap2, new String[] { str, "Data" });
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
          f.a(f.h(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  public static int d()
  {
    return c;
  }
  
  static boolean k()
  {
    return com.ihs.commons.e.n.a(com.ihs.app.framework.c.a(), "framework_config").a("hs.commons.config.remote.file.url.version", 0L) != com.ihs.app.b.a.e();
  }
  
  private void n()
  {
    Context localContext = this.g;
    String str = this.k;
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
          localObject1 = android.support.d.a.g.a(localFile);
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
        localObject3 = android.support.d.a.g.a(localContext.getAssets(), str);
      }
    }
    if (localObject3 == null) {
      return;
    }
    a((Map)localObject3);
    try
    {
      this.f = f.h((Map)localObject3, new String[] { "Data" });
      this.m = b(this.g, this.f);
      return;
    }
    finally {}
  }
  
  public final void a(int paramInt)
  {
    c = paramInt;
    if ((!TextUtils.isEmpty(this.k)) || (!TextUtils.isEmpty("c73df30d92c4d2ec.pa"))) {
      n();
    }
  }
  
  final void a(String paramString)
  {
    paramString = new Thread(new j(this, paramString));
    paramString.setPriority(1);
    paramString.start();
  }
  
  final boolean a(boolean paramBoolean)
  {
    return a(paramBoolean, true);
  }
  
  final long b()
  {
    return net.appcloudbox.common.preference.c.b(this.g, "remoteconfig").a("updateTime", 0L);
  }
  
  final void c()
  {
    net.appcloudbox.common.preference.c.b(this.g, "remoteconfig").c("updateTime", System.currentTimeMillis());
    if (net.appcloudbox.common.utils.l.a()) {
      new StringBuilder("update last refresh time：").append(b());
    }
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  public final Map e()
  {
    return this.f;
  }
  
  public final boolean f()
  {
    return (this.m != null) && (this.m.a);
  }
  
  public final String g()
  {
    if (this.m == null) {
      return "";
    }
    return this.m.b;
  }
  
  final boolean h()
  {
    boolean bool = true;
    if (TextUtils.isEmpty(this.h)) {
      bool = false;
    }
    while (TextUtils.isEmpty(this.i)) {
      return bool;
    }
    return com.ihs.commons.e.n.a(com.ihs.app.framework.c.a(), "framework_config").a("is_new_user_level_mode_4.7", true);
  }
  
  final void i()
  {
    n();
  }
  
  final void j()
  {
    if (k())
    {
      net.appcloudbox.common.preference.c.b(this.g, "remoteconfig").c("updateTime");
      if (net.appcloudbox.common.utils.l.a()) {
        new StringBuilder("clear last refresh time：").append(b());
      }
      a(true, true);
    }
  }
}
