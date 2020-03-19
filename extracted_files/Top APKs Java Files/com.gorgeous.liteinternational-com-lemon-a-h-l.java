package com.lemon.a.h;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.j.b.ah;
import org.b.b.d;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\r\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\026\020\021\032\0020\0222\006\020\023\032\0020\0242\006\020\025\032\0020\004J\026\020\026\032\0020\0272\006\020\023\032\0020\0242\006\020\025\032\0020\004R\024\020\003\032\0020\004XD¢\006\b\n\000\032\004\b\005\020\006R\024\020\007\032\0020\004XD¢\006\b\n\000\032\004\b\b\020\006R\024\020\t\032\0020\004XD¢\006\b\n\000\032\004\b\n\020\006R\024\020\013\032\0020\004XD¢\006\b\n\000\032\004\b\f\020\006R\024\020\r\032\0020\004XD¢\006\b\n\000\032\004\b\016\020\006R\024\020\017\032\0020\004XD¢\006\b\n\000\032\004\b\020\020\006¨\006\030"}, d2={"Lcom/lemon/ltcommon/util/PackageUtil;", "", "()V", "MEIPAI_PACKAGE_NAME", "", "getMEIPAI_PACKAGE_NAME", "()Ljava/lang/String;", "QQ_INTERNATIONAL_PACKAGE_NAME", "getQQ_INTERNATIONAL_PACKAGE_NAME", "QQ_LITE_PACKAGE_NAME", "getQQ_LITE_PACKAGE_NAME", "QQ_PACKAGE_NAME", "getQQ_PACKAGE_NAME", "SINA_PACKAGE_NAME", "getSINA_PACKAGE_NAME", "WECHAT_PACKAGE_NAME", "getWECHAT_PACKAGE_NAME", "getPackageVersionCode", "", "context", "Landroid/content/Context;", "packageName", "isPackageInstalled", "", "libktcommon_overseasRelease"}, k=1, mv={1, 1, 9})
public final class l
{
  @d
  private static final String dMG = "com.tencent.mm";
  @d
  private static final String dMH = "com.tencent.mobileqq";
  @d
  private static final String dMI = "com.tencent.qqlite";
  @d
  private static final String dMJ = "com.tencent.mobileqqi";
  @d
  private static final String dMK = "com.sina.weibo";
  @d
  private static final String dML = "com.meitu.meipaimv";
  public static final l eTp = new l();
  
  private l() {}
  
  public final boolean S(@d Context paramContext, @d String paramString)
  {
    ah.s(paramContext, "context");
    ah.s(paramString, "packageName");
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int j = ((Collection)paramContext).size();
      int i = 0;
      while (i < j)
      {
        if (ah.S(((PackageInfo)paramContext.get(i)).packageName, paramString)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public final int U(@d Context paramContext, @d String paramString)
  {
    ah.s(paramContext, "context");
    ah.s(paramString, "packageName");
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int j = ((Collection)paramContext).size();
      int i = 0;
      while (i < j)
      {
        if (ah.S(((PackageInfo)paramContext.get(i)).packageName, paramString)) {
          return ((PackageInfo)paramContext.get(i)).versionCode;
        }
        i += 1;
      }
    }
    return 0;
  }
  
  @d
  public final String aEA()
  {
    return dML;
  }
  
  @d
  public final String aEv()
  {
    return dMG;
  }
  
  @d
  public final String aEw()
  {
    return dMH;
  }
  
  @d
  public final String aEx()
  {
    return dMI;
  }
  
  @d
  public final String aEy()
  {
    return dMJ;
  }
  
  @d
  public final String aEz()
  {
    return dMK;
  }
}
