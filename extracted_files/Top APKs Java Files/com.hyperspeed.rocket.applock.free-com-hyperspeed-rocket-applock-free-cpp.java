package com.hyperspeed.rocket.applock.free;

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

public class cpp
{
  public static int as;
  private static final String hv = cpp.class.getSimpleName();
  private static final Pattern jd = Pattern.compile("\\s*\\d{0,999}\\s*-\\s*\\d{0,999}\\s*");
  private static final Map<String, Integer> nf;
  private String bh;
  private Thread cg;
  Map<String, ?> er;
  private b ew;
  private Context fe;
  private final String hi = "c73df30d92c4d2ec.pa";
  private String oi;
  private HandlerThread qw;
  private Handler ss;
  public c td;
  public String xv;
  private a xz;
  private String yf;
  private final String yr;
  private cpr yt;
  
  static
  {
    as = -1;
    nf = new HashMap();
    if (cqi.er())
    {
      nf.put("OrganicDebug", Integer.valueOf(3));
      nf.put("SearchDebug", Integer.valueOf(4));
      nf.put("NotOrganicDebug", Integer.valueOf(8));
      return;
    }
    nf.put("OrganicEastEightDistrictRelease", Integer.valueOf(1));
    nf.put("OrganicNotEastEightDistrictRelease", Integer.valueOf(3));
    nf.put("SearchRelease", Integer.valueOf(4));
    nf.put("NotOrganicEastEightDistrictRelease", Integer.valueOf(5));
    nf.put("NotOrganicNotEastEightDistrictRelease", Integer.valueOf(8));
  }
  
  cpp(final Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, a paramA)
  {
    new StringBuilder("assetFileName=").append(paramString3).append("\rremotePlistUrl=").append(paramString2).append("\rdeleteCachedFile=").append(paramBoolean);
    this.fe = paramContext;
    this.xv = paramString3;
    this.yf = paramString1;
    this.bh = paramString2;
    if (TextUtils.isEmpty(paramString2)) {}
    for (paramContext = null;; paramContext = er(paramString2.substring(paramString2.lastIndexOf("/") + 1)))
    {
      this.yr = paramContext;
      this.oi = paramString4;
      this.ew = b.as(cqa.er().er("hs.commons.config.remote.file.last.modify.info", ""));
      this.xz = paramA;
      this.qw = new HandlerThread("PlistDownload");
      this.qw.start();
      this.ss = new Handler(this.qw.getLooper());
      if (!cqn.as(this.xv)) {
        new StringBuilder("Using unencrypted plist file is not allowed, please use PA instead !!! ").append(this.xv);
      }
      if (paramBoolean)
      {
        this.ew.er();
        cqa.er(this.fe, "remoteconfig").as();
        if ((!TextUtils.isEmpty("c73df30d92c4d2ec.pa")) && (this.fe.getFilesDir() != null))
        {
          paramContext = new File(this.fe.getFilesDir().getPath(), "c73df30d92c4d2ec.pa");
          if (paramContext.exists()) {
            paramContext.delete();
          }
        }
      }
      paramContext = bfi.as(bdr.as(), "framework_config");
      if ((!paramContext.as("is_new_user_level_mode_4.7")) && (!TextUtils.isEmpty(paramString1))) {
        paramContext.xv("is_new_user_level_mode_4.7", bei.er());
      }
      if (as == -1) {
        as = cqa.er().er("hs.commons.config.Test_User_Token", -1);
      }
      if (-1 == as)
      {
        as = new Random(System.currentTimeMillis()).nextInt(1000);
        cqa.er().td("hs.commons.config.Test_User_Token", as);
      }
      if ((!TextUtils.isEmpty(paramString3)) || (!TextUtils.isEmpty("c73df30d92c4d2ec.pa"))) {
        jd();
      }
      if ((TextUtils.isEmpty(paramString1)) || (!bei.er())) {
        break;
      }
      paramContext = new HandlerThread("FetchPlistServerUrl");
      paramContext.start();
      paramString1 = new Handler(paramContext.getLooper());
      int i = 0;
      while (i < 13)
      {
        final int j = new int[] { 15, 30, 45, 60, 90, 120, 360, 1800, 3600, 7200, 10800, 14400, 21600 }[i];
        paramString1.postDelayed(new Runnable()
        {
          public final void run()
          {
            if (bds.xv() != bds.b.xv) {
              return;
            }
            if (!cpp.nf()) {
              paramContext.quit();
            }
            cpp.as(cpp.this, j);
          }
        }, j * 1000);
        i += 1;
      }
    }
  }
  
  public static int as(cpl.a paramA)
  {
    cpl.a.b localB;
    if ((paramA == null) || (paramA.as == null) || (paramA.as == cpl.a.b.as) || (paramA.as == cpl.a.b.er))
    {
      localB = cpl.a.b.er;
      if (!cqi.er()) {
        break label119;
      }
      if ((paramA == null) || (!paramA.xz)) {
        break label78;
      }
      paramA = (Integer)nf.get("SearchDebug");
    }
    for (;;)
    {
      return paramA.intValue();
      localB = cpl.a.b.xv;
      break;
      label78:
      if (localB == cpl.a.b.er)
      {
        paramA = (Integer)nf.get("OrganicDebug");
      }
      else
      {
        paramA = (Integer)nf.get("NotOrganicDebug");
        continue;
        label119:
        int i = TimeZone.getDefault().getRawOffset() / 3600000;
        if (localB == cpl.a.b.er)
        {
          if (i == 8) {
            paramA = (Integer)nf.get("OrganicEastEightDistrictRelease");
          } else {
            paramA = (Integer)nf.get("OrganicNotEastEightDistrictRelease");
          }
        }
        else if ((paramA != null) && (paramA.xz) && (i == 8)) {
          paramA = (Integer)nf.get("OrganicEastEightDistrictRelease");
        } else if ((paramA != null) && (paramA.xz) && (i != 8)) {
          paramA = (Integer)nf.get("SearchRelease");
        } else if (((paramA == null) || (!paramA.xz)) && (i == 8)) {
          paramA = (Integer)nf.get("NotOrganicEastEightDistrictRelease");
        } else if (((paramA == null) || (!paramA.xz)) && (i != 8)) {
          paramA = (Integer)nf.get("NotOrganicNotEastEightDistrictRelease");
        } else {
          paramA = null;
        }
      }
    }
  }
  
  static long as()
  {
    long l2 = cpn.as(43200.0F, new String[] { "libCommons", "RemoteConfig", "UpdateInterval" });
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
  
  private String as(long paramLong)
  {
    bool1 = false;
    bfi localBfi = bfi.as(bdr.as(), "framework_config");
    boolean bool2;
    Object localObject2;
    if ((bei.er()) && (localBfi.as("is_url_first_get", true)))
    {
      bool2 = true;
      localObject2 = new JSONObject();
    }
    try
    {
      ((JSONObject)localObject2).put("bundle_id", this.fe.getPackageName());
      if (bds.xv() == bds.b.xv) {
        break label568;
      }
      localObject1 = "null";
      label73:
      ((JSONObject)localObject2).put("custom_user_id", localObject1);
      ((JSONObject)localObject2).put("app_version", bei.jd());
      ((JSONObject)localObject2).put("app_version_code", bei.hv());
      localObject1 = Build.TAGS;
      if ((localObject1 == null) || (!((String)localObject1).contains("test-keys"))) {
        break label858;
      }
      i = 1;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject1;
        int i;
        label134:
        cpl.a localA;
        long l;
        JSONObject localJSONObject;
        continue;
        for (;;)
        {
          if (i == 0) {
            break label576;
          }
          bool1 = true;
          break;
          str = "o";
          break label286;
          i = 0;
        }
        for (;;)
        {
          if (i == 0) {
            break label627;
          }
          bool1 = true;
          break;
          i = 0;
        }
        String str = "n";
      }
    }
    ((JSONObject)localObject2).put("is_root", bool1);
    ((JSONObject)localObject2).put("is_vpn", cql.as());
    ((JSONObject)localObject2).put("region", bev.as().xv());
    ((JSONObject)localObject2).put("tz", TimeZone.getDefault().getRawOffset() / 1000);
    ((JSONObject)localObject2).put("key", this.yr);
    ((JSONObject)localObject2).put("is_debug", bfe.er());
    ((JSONObject)localObject2).put("install_timestamp", bdq.as());
    ((JSONObject)localObject2).put("is_first", bool2);
    localA = cpl.as(this.fe);
    if ((localA != null) && (localA.as != null) && (localA.as != cpl.a.b.as))
    {
      if (localA.as != cpl.a.b.er) {
        break label878;
      }
      break label850;
      label286:
      ((JSONObject)localObject2).put("as", localObject1);
      if (localA == null)
      {
        localObject1 = "";
        label306:
        ((JSONObject)localObject2).put("ac", localObject1);
        if (localA != null) {
          break label649;
        }
        localObject1 = "";
        label326:
        ((JSONObject)localObject2).put("ad", localObject1);
        ((JSONObject)localObject2).put("ai", cpl.er());
        ((JSONObject)localObject2).put("ak", ben.xv(new String[] { "libCommons", "Analytics", "FlyerKey" }));
        ((JSONObject)localObject2).put("ul", as(localA));
        ((JSONObject)localObject2).put("iq", paramLong);
        paramLong = (System.currentTimeMillis() - bdq.as()) / 1000L;
        l = bdu.td();
        if (paramLong <= l) {
          break label659;
        }
      }
      for (;;)
      {
        ((JSONObject)localObject2).put("install_timespan", paramLong);
        new StringBuilder("PlistServerUrl:").append(this.yf).append(" RemoteUserLevelRequestJson:").append(((JSONObject)localObject2).toString());
        localObject1 = new beq(this.yf, bet.d.er, (JSONObject)localObject2, true);
        ((beq)localObject1).as(15000);
        ((beq)localObject1).er(15000);
        ((beq)localObject1).xv(bfj.er(), bfj.as());
        ((beq)localObject1).er(bfj.xv(), bfj.td());
        ((beq)localObject1).as();
        if (((beq)localObject1).jd() == null) {
          break label665;
        }
        throw new Throwable(((beq)localObject1).jd().toString());
        bool2 = false;
        break;
        label568:
        localObject1 = bdr.xv();
        break label73;
        label576:
        if (cql.er())
        {
          bool1 = true;
          break label134;
        }
        if (cql.xv())
        {
          bool1 = true;
          break label134;
        }
        if (cql.as(new String[] { "/system/xbin/which", "su" }) == null) {
          break label873;
        }
        i = 1;
        break label863;
        label627:
        if (!cql.td()) {
          break label134;
        }
        bool1 = true;
        break label134;
        localObject1 = localA.qw;
        break label306;
        label649:
        localObject1 = localA.fe;
        break label326;
        label659:
        paramLong = l;
      }
      label665:
      localObject1 = ((beq)localObject1).hv();
      try
      {
        i = ((JSONObject)localObject1).getJSONObject("meta").getInt("code");
        new StringBuilder("RemoteUserLevelResponseJson:").append(((JSONObject)localObject1).toString());
        if (i != 200) {
          throw new Throwable("response code is not 200");
        }
      }
      catch (Exception localException)
      {
        throw new Throwable("get plistUrl failed. Message:" + localException.getMessage());
      }
      localJSONObject = localException.getJSONObject("data");
      localObject2 = localJSONObject.getString("url");
      bool1 = localJSONObject.getBoolean("t");
      if (bool2) {
        localBfi.xv("is_url_first_get", false);
      }
      if (!bool1)
      {
        localBfi.er("hs.commons.config.remote.file.url.version", cqp.as(bdr.as()));
        localBfi.xv("hs.commons.config.remote.file.url", (String)localObject2);
        bdx.er();
      }
      return localObject2;
    }
  }
  
  private static String as(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
      return null;
    }
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() < 2) {
        localStringBuilder.append(0);
      }
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private void as(final String paramString)
  {
    this.ss.post(new Runnable()
    {
      public final void run()
      {
        if (cpp.xv(cpp.this) != null)
        {
          if (TextUtils.equals(cpp.xv(cpp.this).as(), paramString)) {
            return;
          }
          cpp.this.as(-1L, cpp.xv(cpp.this).as(), "cancel");
          cpp.xv(cpp.this).hv();
        }
        cpp.as(cpp.this, new cpr(paramString));
        if (cpp.td(cpp.this).getFilesDir() == null)
        {
          cpp.as(cpp.this).as(false, false);
          return;
        }
        final String str = cpp.td(cpp.this).getFilesDir().getPath();
        File localFile = new File(str);
        if (!localFile.exists()) {
          localFile.mkdir();
        }
        localFile = new File(str, "temp.c73df30d92c4d2ec.pa");
        if (paramString.equals(paramString))
        {
          HashMap localHashMap = new HashMap();
          if (!TextUtils.isEmpty(cpp.hv(cpp.this).er)) {
            localHashMap.put("If-Modified-Since", cpp.hv(cpp.this).er);
          }
          if (!TextUtils.isEmpty(cpp.hv(cpp.this).xv)) {
            localHashMap.put("If-None-Match", cpp.hv(cpp.this).xv);
          }
          if (!localHashMap.isEmpty()) {
            cpp.xv(cpp.this).as(localHashMap);
          }
        }
        final long l = System.currentTimeMillis();
        cpp.xv(cpp.this).as(10000).er(30000);
        cpp.xv(cpp.this).as(localFile);
        cpp.xv(cpp.this).as(new cpr.b()
        {
          public final void as(cpr paramAnonymous2Cpr)
          {
            boolean bool2 = false;
            cpp.as(cpp.this, null);
            long l2 = System.currentTimeMillis() - l;
            if (!paramAnonymous2Cpr.xv())
            {
              cpp.this.as(l2, paramAnonymous2Cpr.as(), "not succeeded:" + paramAnonymous2Cpr.yr + "error:" + paramAnonymous2Cpr.td());
              cpp.as(cpp.this).as(false, false);
              return;
            }
            if (paramAnonymous2Cpr.yr == 304)
            {
              cpp.this.as(l2, paramAnonymous2Cpr.as(), "304");
              cpp.this.xv();
              cpp.as(cpp.this).as(false, true);
              return;
            }
            Object localObject1 = cqm.as(str);
            cpp.as(cpp.this, (Map)localObject1);
            Object localObject2 = cqj.fe((Map)localObject1, new String[] { "Data" });
            Object localObject3;
            if (localObject2 == null)
            {
              localObject2 = cpp.this;
              localObject3 = paramAnonymous2Cpr.as();
              paramAnonymous2Cpr = new StringBuilder("code:").append(paramAnonymous2Cpr.yr).append("fetch(), parser stream failed. File md5").append(cpp.as(str)).append(" File size:").append(str.length()).append("rootData");
              if (localObject1 == null) {}
              for (bool1 = true;; bool1 = false)
              {
                ((cpp)localObject2).as(l2, (String)localObject3, bool1);
                cpp.as(cpp.this).as(false, false);
                return;
              }
            }
            localObject1 = new File(this.xv, "c73df30d92c4d2ec.pa");
            if (((File)localObject1).exists())
            {
              localObject3 = cpp.hv(cpp.this);
              cpp.td(cpp.this);
              ((cpp.b)localObject3).er();
            }
            for (boolean bool1 = ((File)localObject1).delete();; bool1 = true)
            {
              if (!str.renameTo((File)localObject1))
              {
                localObject2 = cpp.this;
                localObject3 = paramAnonymous2Cpr.as();
                paramAnonymous2Cpr = new StringBuilder("code:").append(paramAnonymous2Cpr.yr).append("fetch(), rename temp to plist file name failed. File md5").append(cpp.as(str)).append(" File size:");
                if (str.exists()) {}
                for (long l1 = str.length();; l1 = -1L)
                {
                  ((cpp)localObject2).as(l2, (String)localObject3, l1 + " plistFile exitsts" + ((File)localObject1).exists() + bool1);
                  cpp.as(cpp.this).as(false, false);
                  return;
                }
              }
              cpp.hv(cpp.this).as = cpp.3.this.as;
              cpp.hv(cpp.this).er = ((String)paramAnonymous2Cpr.hi.get("Last-Modified"));
              cpp.hv(cpp.this).xv = ((String)paramAnonymous2Cpr.hi.get("Etag"));
              localObject1 = cpp.hv(cpp.this);
              cpp.td(cpp.this);
              ((cpp.b)localObject1).as();
              new StringBuilder("RemoteConfig modified Last-Modified: ").append(cpp.hv(cpp.this).er).append(" ETag: ").append(cpp.hv(cpp.this).xv);
              try
              {
                if (cpp.jd(cpp.this) != null)
                {
                  bool1 = bool2;
                  if (cpp.jd(cpp.this).equals(localObject2)) {}
                }
                else
                {
                  cpp.er(cpp.this, (Map)localObject2);
                  cpp.as(cpp.this, cpp.as(cpp.td(cpp.this), (Map)localObject2));
                  bool1 = true;
                }
                cpp.this.xv();
                cpp.as(cpp.this).as(bool1, true);
                if (!bool1) {
                  break;
                }
                cpp.this.as(l2, paramAnonymous2Cpr.as(), null);
                return;
              }
              finally {}
            }
          }
          
          public final void as(cpr paramAnonymous2Cpr, cqg paramAnonymous2Cqg)
          {
            cpp.as(cpp.this, null);
            long l1 = System.currentTimeMillis();
            long l2 = l;
            cpp.this.as(l1 - l2, paramAnonymous2Cpr.as(), "not succeeded:" + paramAnonymous2Cpr.yr + "error:" + paramAnonymous2Cqg);
            cpp.as(cpp.this).as(false, false);
          }
        });
        cpp.xv(cpp.this).as(cpp.nf(cpp.this));
      }
    });
  }
  
  private void as(Map<String, ?> paramMap)
  {
    er(paramMap);
    xv(paramMap);
    Object localObject3 = cpl.as(this.fe);
    int n;
    Object localObject1;
    if (cqj.er(paramMap, new String[] { "UserLevel" })) {
      if (hv())
      {
        localObject2 = null;
        n = as((cpl.a)localObject2);
        if ((n < 0) || (paramMap == null) || (TextUtils.isEmpty("UserLevel")))
        {
          localObject1 = null;
          label72:
          if (localObject1 != null)
          {
            er((Map)localObject1);
            xv((Map)localObject1);
          }
          new StringBuilder("it has userlevel(").append(n).append(") config!");
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
      int k;
      Object localObject5;
      Pattern localPattern;
      String[] arrayOfString;
      int i;
      int j;
      if (cqj.er(paramMap, new String[] { "GP" }))
      {
        localObject1 = this.oi;
        if ((paramMap == null) || (TextUtils.isEmpty((CharSequence)localObject1)) || (localObject3 == null))
        {
          localObject1 = null;
          if (localObject1 != null)
          {
            er((Map)localObject1);
            xv((Map)localObject1);
          }
          new StringBuilder("it has GP(").append(this.oi).append(") config!");
          localObject3 = localObject2;
          if (localObject1 != null)
          {
            if (localObject2 == null) {
              break label946;
            }
            cqj.as((Map)localObject1, (Map)localObject2);
            localObject3 = localObject1;
          }
          cqj.as(paramMap, (Map)localObject3);
          return;
          localMap = cqj.fe(paramMap, new String[] { "UserLevel" });
          localObject1 = Pattern.compile("[0-9]+");
          if (localMap == null) {
            break label972;
          }
          localObject4 = localMap.keySet();
          if (cqi.er())
          {
            localObject3 = new HashSet();
            localObject4 = ((Set)localObject4).iterator();
            k = 0;
            for (;;)
            {
              if (((Iterator)localObject4).hasNext())
              {
                localObject5 = (String)((Iterator)localObject4).next();
                if ("default".equals(localObject5))
                {
                  k = 1;
                }
                else
                {
                  localPattern = Pattern.compile("[0-9]+");
                  arrayOfString = ((String)localObject5).split("-");
                  if ((arrayOfString.length == 1) && (localPattern.matcher(arrayOfString[0].trim()).matches()))
                  {
                    i = Integer.valueOf(arrayOfString[0]).intValue();
                    j = i;
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
        int m;
        if ((i != -1) && (j != -1))
        {
          m = i;
          if (j >= i) {}
        }
        else
        {
          new StringBuilder().append((String)localObject5).append(" 格式错误!");
          Process.killProcess(Process.myPid());
          m = i;
        }
        label437:
        if (m <= j)
        {
          if (((Set)localObject3).contains(Integer.valueOf(m))) {
            break label548;
          }
          ((Set)localObject3).add(Integer.valueOf(m));
        }
        for (;;)
        {
          m += 1;
          break label437;
          break;
          if ((arrayOfString.length != 2) || (!localPattern.matcher(arrayOfString[0].trim()).matches()) || (!localPattern.matcher(arrayOfString[1].trim()).matches())) {
            break label965;
          }
          i = Integer.valueOf(arrayOfString[0]).intValue();
          j = Integer.valueOf(arrayOfString[1]).intValue();
          break label391;
          label548:
          new StringBuilder().append((String)localObject5).append(" 多个用户等级有重叠!");
          Process.killProcess(Process.myPid());
        }
        if (k == 0) {
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
            k = -1;
            m = 0;
            if (localObject5.length == 1)
            {
              if ("default".equals(localObject5[0])) {
                continue;
              }
              i = m;
              j = k;
              if (((Pattern)localObject1).matcher(localObject5[0].trim()).matches())
              {
                i = Integer.valueOf(localObject5[0]).intValue();
                j = i;
              }
              label696:
              if ((j == -1) || (i == -1) || (j > n) || (i < n)) {
                break label847;
              }
            }
          }
        }
        for (localObject3 = cqj.fe(localMap, new String[] { localObject4 });; localObject3 = null)
        {
          localObject1 = localObject3;
          if (localObject3 != null) {
            break;
          }
          localObject1 = cqj.fe(localMap, new String[] { "default" });
          break;
          i = m;
          j = k;
          if (localObject5.length != 2) {
            break label696;
          }
          i = m;
          j = k;
          if (!((Pattern)localObject1).matcher(localObject5[0].trim()).matches()) {
            break label696;
          }
          i = m;
          j = k;
          if (!((Pattern)localObject1).matcher(localObject5[1].trim()).matches()) {
            break label696;
          }
          j = Integer.valueOf(localObject5[0]).intValue();
          i = Integer.valueOf(localObject5[1]).intValue();
          break label696;
          label847:
          break label601;
          localMap = cqj.fe(paramMap, new String[] { localObject1 });
          if (localMap == null)
          {
            localObject1 = null;
            break label160;
          }
          if (((cpl.a)localObject3).as == cpl.a.b.er) {}
          for (localObject1 = "Organic";; localObject1 = ((cpl.a)localObject3).er)
          {
            localObject3 = cqj.fe(localMap, new String[] { localObject1 });
            localObject1 = localObject3;
            if (localObject3 != null) {
              break;
            }
            localObject1 = cqj.fe(localMap, new String[] { "Others" });
            break;
          }
          label946:
          localObject3 = localObject1;
          break label224;
          localObject1 = null;
          break label199;
        }
        label965:
        i = -1;
        j = -1;
      }
      label972:
      localObject1 = null;
      break label72;
      localObject2 = localObject3;
      break;
    }
  }
  
  private static c er(Context paramContext, Map<String, ?> paramMap)
  {
    c localC = new c();
    if (paramMap == null) {
      return localC;
    }
    paramMap = cqj.nf(paramMap, new String[] { "RestrictedUser" });
    if ((paramMap == null) || (paramMap.size() <= 0)) {
      return localC;
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
        label101:
        if (i == 0) {
          break label393;
        }
        paramContext = cqj.as((Map)localObject1, "", new String[] { "Description" });
      }
    }
    for (boolean bool = true;; bool = false)
    {
      localC.as = bool;
      localC.er = paramContext;
      return localC;
      i = TimeZone.getDefault().getRawOffset() / 3600000;
      Object localObject3 = cqj.hv((Map)localObject2, new String[] { "TimeZone" });
      if ((!TextUtils.isEmpty((CharSequence)localObject3)) && (!String.valueOf(i).equalsIgnoreCase((String)localObject3)))
      {
        i = 0;
        break label101;
      }
      localObject3 = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
      Object localObject4 = cqj.hv((Map)localObject2, new String[] { "RegionFormat" });
      if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject3).equalsIgnoreCase((String)localObject4)))
      {
        i = 0;
        break label101;
      }
      localObject2 = cqj.nf((Map)localObject2, new String[] { "UrlScheme" });
      if ((localObject2 == null) || (((List)localObject2).isEmpty()))
      {
        i = 1;
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
        for (i = 1;; i = 0)
        {
          if (i != 0) {
            break label398;
          }
          i = 0;
          break label101;
          i = 1;
          break label101;
          break;
        }
      }
      paramContext = "";
    }
  }
  
  /* Error */
  private static String er(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 837	java/io/File:isFile	()Z
    //   4: ifne +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: sipush 1024
    //   12: newarray byte
    //   14: astore 4
    //   16: ldc_w 839
    //   19: invokestatic 845	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   22: astore_3
    //   23: new 847	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 850	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   31: astore_2
    //   32: aload_2
    //   33: astore_0
    //   34: aload_2
    //   35: aload 4
    //   37: iconst_0
    //   38: sipush 1024
    //   41: invokevirtual 854	java/io/FileInputStream:read	([BII)I
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
    //   57: invokevirtual 858	java/security/MessageDigest:update	([BII)V
    //   60: goto -28 -> 32
    //   63: astore_3
    //   64: aload_2
    //   65: astore_0
    //   66: aload_3
    //   67: invokevirtual 861	java/lang/Exception:printStackTrace	()V
    //   70: aload_2
    //   71: ifnull -64 -> 7
    //   74: aload_2
    //   75: invokevirtual 864	java/io/FileInputStream:close	()V
    //   78: aconst_null
    //   79: areturn
    //   80: astore_0
    //   81: aload_0
    //   82: invokevirtual 865	java/io/IOException:printStackTrace	()V
    //   85: aconst_null
    //   86: areturn
    //   87: aload_2
    //   88: invokevirtual 864	java/io/FileInputStream:close	()V
    //   91: aload_3
    //   92: invokevirtual 869	java/security/MessageDigest:digest	()[B
    //   95: invokestatic 871	com/hyperspeed/rocket/applock/free/cpp:as	([B)Ljava/lang/String;
    //   98: areturn
    //   99: astore_0
    //   100: aload_0
    //   101: invokevirtual 865	java/io/IOException:printStackTrace	()V
    //   104: goto -13 -> 91
    //   107: astore_2
    //   108: aconst_null
    //   109: astore_0
    //   110: aload_0
    //   111: ifnull +7 -> 118
    //   114: aload_0
    //   115: invokevirtual 864	java/io/FileInputStream:close	()V
    //   118: aload_2
    //   119: athrow
    //   120: astore_0
    //   121: aload_0
    //   122: invokevirtual 865	java/io/IOException:printStackTrace	()V
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
    //   44	13	1	i	int
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
  
  private static String er(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = new StringBuilder();
      localObject = ((MessageDigest)localObject).digest();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        int k = localObject[i];
        paramString.append(Integer.toHexString(k >> 4 & 0xF));
        paramString.append(Integer.toHexString(k & 0xF));
        i += 1;
      }
      paramString = paramString.toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static void er(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    for (;;)
    {
      return;
      Map localMap1 = cqj.fe(paramMap, new String[] { "Data" });
      new StringBuilder("mergeRegions(), main data = ").append(localMap1);
      Map localMap2 = cqj.fe(paramMap, new String[] { "Regions" });
      if (localMap2 != null)
      {
        String str1 = Locale.getDefault().getCountry().trim();
        paramMap = cqj.fe(localMap2, new String[] { str1 });
        Object localObject = paramMap;
        if (paramMap == null) {
          localObject = cqj.fe(localMap2, new String[] { Locale.getDefault().getCountry().toUpperCase() });
        }
        paramMap = (Map<String, ?>)localObject;
        if (localObject == null) {
          paramMap = cqj.fe(localMap2, new String[] { Locale.getDefault().getCountry().toLowerCase() });
        }
        if (paramMap == null)
        {
          localObject = localMap2.keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            if (str2.toUpperCase().equals(str1.toUpperCase())) {
              paramMap = cqj.fe(localMap2, new String[] { str2 });
            }
          }
        }
        while (paramMap != null)
        {
          cqj.as(localMap1, cqj.fe(paramMap, new String[] { "Data" }));
          return;
        }
      }
    }
  }
  
  static boolean nf()
  {
    return bfi.as(bdr.as(), "framework_config").as("hs.commons.config.remote.file.url.version", 0L) != bei.hv();
  }
  
  public static int td()
  {
    return as;
  }
  
  private static void xv(Map<String, ?> paramMap)
  {
    if (paramMap == null) {}
    label210:
    label211:
    for (;;)
    {
      return;
      Map localMap2 = cqj.fe(paramMap, new String[] { "Segments" });
      if (localMap2 != null)
      {
        Map localMap1 = null;
        int i = Integer.MAX_VALUE;
        Iterator localIterator = localMap2.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = str.replace(" ", "");
          if (jd.matcher((CharSequence)localObject).matches())
          {
            localObject = ((String)localObject).split("-");
            if (2 == localObject.length)
            {
              int j = Integer.valueOf(localObject[0]).intValue();
              int k = Integer.valueOf(localObject[1]).intValue();
              if (j <= k)
              {
                if ((as < j) || (as > k) || (j >= i)) {
                  break label210;
                }
                localMap1 = cqj.fe(localMap2, new String[] { str, "Data" });
                i = j;
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
          cqj.as(cqj.fe(paramMap, new String[] { "Data" }), localMap1);
          return;
        }
      }
    }
  }
  
  final void as(long paramLong, String paramString1, String paramString2)
  {
    final JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("bundle_id", this.fe.getPackageName());
        if (bds.xv() == bds.b.xv) {
          continue;
        }
        localObject = "null";
        localJSONObject.put("custom_user_id", localObject);
        localJSONObject.put("app_version", bei.jd());
        localJSONObject.put("app_version_code", bei.hv());
        localJSONObject.put("app_os_version_code", bei.nf());
        localJSONObject.put("cf_ul", ben.as("no_configure", new String[] { "UserLevel" }));
        localJSONObject.put("clt_ul", as(cpl.as(this.fe)));
        localJSONObject.put("install_timestamp", bdq.as());
        localJSONObject.put("download_timespan", paramLong);
        localJSONObject.put("now_time", System.currentTimeMillis());
        String str = er(new File(this.fe.getFilesDir().getPath(), "c73df30d92c4d2ec.pa"));
        localObject = str;
        if (TextUtils.isEmpty(str)) {
          localObject = "";
        }
        localJSONObject.put("file_md5", localObject);
        localJSONObject.put("now_url", this.bh);
        localJSONObject.put("down_url", paramString1);
        localJSONObject.put("t", nf());
        if (!TextUtils.isEmpty(paramString2)) {
          continue;
        }
        paramString1 = "";
        localJSONObject.put("errorInfo", paramString1);
        localJSONObject.put("is_first_run", bei.er());
        if ((TextUtils.isEmpty(paramString2)) && (!nf()))
        {
          if (cpl.xv() <= 0L) {
            continue;
          }
          paramLong = (System.currentTimeMillis() - cpl.xv()) / 1000L;
          localJSONObject.put("server_time_span", paramLong);
        }
        paramLong = (System.currentTimeMillis() - bdq.as()) / 1000L;
        l = bdu.td();
        if (paramLong <= l) {
          continue;
        }
        localJSONObject.put("install_timespan", paramLong);
      }
      catch (JSONException paramString1)
      {
        Object localObject;
        long l;
        continue;
      }
      paramString1 = new Thread(new Runnable()
      {
        public final void run()
        {
          cpp.fe();
          new StringBuilder("logToServer json:").append(localJSONObject.toString());
          beq localBeq = new beq(cpp.yf(), bet.d.er, localJSONObject, true);
          localBeq.xv(bfj.er(), bfj.as());
          localBeq.er(bfj.xv(), bfj.td());
          localBeq.as();
          if (!localBeq.xv())
          {
            cpp.fe();
            new StringBuilder("logToServer failed").append(localBeq.jd());
            return;
          }
          cpp.fe();
          new StringBuilder("logToServer succeed").append(localBeq.td());
        }
      });
      paramString1.setPriority(1);
      paramString1.start();
      return;
      localObject = bdr.xv();
      continue;
      paramString1 = paramString2;
      continue;
      paramLong = -1L;
      continue;
      paramLong = l;
    }
  }
  
  public final boolean as(boolean paramBoolean, final int paramInt)
  {
    if (TextUtils.isEmpty(this.bh))
    {
      this.xz.as(false, false);
      return true;
    }
    if (!paramBoolean)
    {
      if ((System.currentTimeMillis() <= er()) || (System.currentTimeMillis() - er() >= as()) || (er() == 0L)) {
        if (cqi.as()) {
          new StringBuilder("Time is expired：").append(er()).append(":").append(as());
        }
      }
      for (int i = 1; i == 0; i = 0) {
        return false;
      }
    }
    if (!hv())
    {
      as(this.bh);
      return true;
    }
    if (this.cg != null) {
      return false;
    }
    this.cg = new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          Object localObject1 = bfi.as(bdr.as(), "framework_config");
          if (((bfi)localObject1).as("hs.commons.config.remote.file.url.version", 0L) == bei.hv()) {
            localObject1 = ((bfi)localObject1).as("hs.commons.config.remote.file.url", "");
          }
          String str;
          while (TextUtils.isEmpty((CharSequence)localObject1))
          {
            cpp.as(cpp.this).as(false, false);
            return;
            try
            {
              localObject1 = cpp.as(cpp.this, paramInt);
            }
            catch (Throwable localThrowable)
            {
              new StringBuilder("error info:").append(localThrowable.getMessage());
              str = null;
            }
          }
          cpp.as(cpp.this, str);
          cpp.er(cpp.this, str);
          return;
        }
        finally
        {
          cpp.er(cpp.this);
        }
      }
    });
    this.cg.start();
    return true;
  }
  
  protected Object clone()
  {
    throw new CloneNotSupportedException();
  }
  
  final long er()
  {
    return cqa.er(this.fe, "remoteconfig").as("updateTime", 0L);
  }
  
  public final boolean hv()
  {
    boolean bool = true;
    if (TextUtils.isEmpty(this.yf)) {
      bool = false;
    }
    while (TextUtils.isEmpty(this.bh)) {
      return bool;
    }
    return bfi.as(bdr.as(), "framework_config").as("is_new_user_level_mode_4.7", true);
  }
  
  public final void jd()
  {
    Context localContext = this.fe;
    String str = this.xv;
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
          localObject1 = cqm.as(localFile);
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
        localObject3 = cqm.as(localContext.getAssets(), str);
      }
    }
    if (localObject3 == null) {
      return;
    }
    as((Map)localObject3);
    try
    {
      this.er = cqj.fe((Map)localObject3, new String[] { "Data" });
      this.td = er(this.fe, this.er);
      return;
    }
    finally {}
  }
  
  final void xv()
  {
    cqa.er(this.fe, "remoteconfig").xv("updateTime", System.currentTimeMillis());
    if (cqi.as()) {
      new StringBuilder("update last refresh time：").append(er());
    }
  }
  
  public static abstract interface a
  {
    public abstract void as(boolean paramBoolean1, boolean paramBoolean2);
  }
  
  static final class b
  {
    public String as;
    public String er;
    public String xv;
    
    private b() {}
    
    static b as(String paramString)
    {
      b localB = new b();
      if (TextUtils.isEmpty(paramString)) {
        return localB;
      }
      try
      {
        paramString = new JSONObject(paramString);
        localB.as = paramString.optString("remoteUrl");
        localB.er = paramString.optString("lastModified");
        localB.xv = paramString.optString("eTag");
        return localB;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
      return localB;
    }
    
    private String xv()
    {
      Object localObject = new JSONObject();
      try
      {
        ((JSONObject)localObject).put("remoteUrl", this.as);
        ((JSONObject)localObject).put("lastModified", this.er);
        ((JSONObject)localObject).put("eTag", this.xv);
        localObject = ((JSONObject)localObject).toString();
        return localObject;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      return null;
    }
    
    final void as()
    {
      cqa.er().td("hs.commons.config.remote.file.last.modify.info", xv());
    }
    
    final void er()
    {
      this.as = "";
      this.er = "";
      this.xv = "";
      as();
    }
  }
  
  public static final class c
  {
    public boolean as = false;
    public String er = "";
    
    public c() {}
  }
}
