package cn.waps;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.util.Xml;
import android.widget.LinearLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

public final class AppConnect
{
  private static boolean K = false;
  private static boolean L = false;
  public static final String LIBRARY_VERSION_NUMBER = "2.1.1";
  public static final String TYPE_BROWSER = "browser";
  public static final String TYPE_PLAYER = "player";
  private static String aA;
  private static String aB;
  private static String aC;
  private static String aD;
  private static boolean aF;
  private static boolean aG;
  private static Map aJ;
  private static boolean aK;
  private static boolean aL;
  private static q aM;
  private static String aN;
  private static t aQ;
  private static Handler aT;
  private static boolean aV;
  private static boolean aZ;
  private static UpdatePointsNotifier at;
  private static String az;
  protected static String b;
  private static int bd = 0;
  protected static String c;
  public static String connectResult;
  protected static Map d;
  protected static int e;
  protected static int f;
  public static boolean finalize_tag;
  protected static List g;
  protected static String h;
  protected static boolean i;
  protected static boolean l;
  private static AppConnect p = null;
  private static ae q = null;
  private static SharedPreferences r = null;
  private static String y;
  private String A = "";
  private String B = "";
  private String C = "";
  private String D = "";
  private String E = "";
  private String F = "";
  private String G = "";
  private int H = 0;
  private int I = 0;
  private String J = "";
  private String M = "";
  private String N = "";
  private String O = "";
  private String P = "";
  private String Q = cl.c(aj.l);
  private String R = "";
  private String S = "";
  private String T = "";
  private final String U = "root";
  private final String V = "base";
  private final String W = "mac";
  private final String X = "x";
  private final String Y = "y";
  private final String Z = "net";
  protected Context a = null;
  private String aE = "";
  private String aH = "";
  private String aI = "";
  private String aO = "";
  private String aP = "";
  private String aR = "";
  private String aS = "";
  private AppListener aU;
  private String aW = "";
  private int aX = -1;
  private String aY = "";
  private final String aa = "im" + "A".replace("A", "") + "si";
  private final String ab = "udid";
  private final String ac = "device_name";
  private final String ad = "device_type";
  private final String ae = "os_version";
  private final String af = "country_code";
  private final String ag = "language";
  private final String ah = "app_version";
  private final String ai = "sdk_version";
  private final String aj = "act";
  private final String ak = "channel";
  private final String al = "device_brand";
  private final String am = "points";
  private final String an = "points";
  private final String ao = "install";
  private final String ap = "uninstall";
  private final String aq = "load";
  private final String ar = "device_width";
  private final String as = "device_height";
  private v au = null;
  private y av = null;
  private u aw = null;
  private x ax = null;
  private w ay = null;
  private HashMap ba;
  private HashMap bb;
  private int bc = -1;
  private String be = aj.K() + aj.J();
  boolean j = true;
  Dialog k;
  bt m;
  aw n;
  private z o = null;
  private String s = "";
  private String t = "";
  private String u = "";
  private String v = "";
  private String w = "";
  private String x = "";
  private String z = "";
  
  static
  {
    b = "";
    y = "";
    c = "";
    K = true;
    L = false;
    az = "";
    aA = aj.v();
    aB = "install";
    aC = "";
    aD = "";
    aF = true;
    aG = true;
    aJ = null;
    aK = true;
    aL = false;
    e = Color.argb(50, 240, 240, 240);
    f = -1;
    g = null;
    h = "ADVIEW";
    aN = "";
    aQ = null;
    i = false;
    aT = null;
    connectResult = "";
    aV = true;
    aZ = true;
    finalize_tag = false;
    l = true;
  }
  
  public AppConnect() {}
  
  private AppConnect(Context paramContext)
  {
    g(paramContext);
    try
    {
      boolean bool = cl.a();
      this.S = (bool + "");
      if (bool) {
        cl.a(r);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    c = a(paramContext);
    if (Looper.myLooper() == null) {
      Looper.prepare();
    }
    f(paramContext);
    this.o = new z(this, null);
    this.o.execute(new Void[0]);
    new ar().a(paramContext);
  }
  
  private String a(int paramInt, SharedPreferences paramSharedPreferences)
  {
    for (;;)
    {
      try
      {
        String str2 = c;
        if (paramInt != 0) {
          if (paramInt > 0)
          {
            str1 = aj.c() + aj.s();
            str2 = str2 + "&points=" + paramInt;
            str1 = q.a(str1, str2);
            if (!cl.b(str1))
            {
              paramSharedPreferences = paramSharedPreferences.edit();
              paramSharedPreferences.putInt("UpdatePoints", 0);
              paramSharedPreferences.commit();
              return str1;
            }
          }
          else
          {
            if (paramInt >= 0) {
              break label173;
            }
            str1 = aj.c() + aj.r();
            str2 = str2 + "&points=" + -paramInt;
            continue;
          }
        }
        return "";
      }
      catch (Exception paramSharedPreferences)
      {
        paramSharedPreferences.printStackTrace();
      }
      label173:
      String str1 = "";
    }
  }
  
  private String a(NodeList paramNodeList)
  {
    paramNodeList = (Element)paramNodeList.item(0);
    if (paramNodeList != null)
    {
      NodeList localNodeList = paramNodeList.getChildNodes();
      int i2 = localNodeList.getLength();
      paramNodeList = "";
      int i1 = 0;
      while (i1 < i2)
      {
        Node localNode = localNodeList.item(i1);
        Object localObject = paramNodeList;
        if (localNode != null) {
          localObject = paramNodeList + localNode.getNodeValue();
        }
        i1 += 1;
        paramNodeList = (NodeList)localObject;
      }
      if (!cl.b(paramNodeList)) {
        return paramNodeList.trim();
      }
      return null;
    }
    return null;
  }
  
  protected static String a(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramArrayOfByte);
      paramArrayOfByte = a(localMessageDigest.digest(), "");
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return "";
  }
  
  protected static String a(byte[] paramArrayOfByte, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i2 = paramArrayOfByte.length;
    int i1 = 0;
    while (i1 < i2)
    {
      String str = Integer.toHexString(paramArrayOfByte[i1] & 0xFF);
      if (str.length() == 1) {
        localStringBuilder.append("0");
      }
      localStringBuilder.append(str).append(paramString);
      i1 += 1;
    }
    return localStringBuilder.toString();
  }
  
  private void a(int paramInt, AppListener paramAppListener)
  {
    this.ba = new HashMap();
    this.ba.put(Integer.valueOf(paramInt), paramAppListener);
  }
  
  private void a(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = r.edit();
    localEditor.putString("pref_offers_type", paramString2);
    if (!cl.b(paramString1)) {
      localEditor.putString("pref_user_id", paramString1);
    }
    localEditor.commit();
  }
  
  protected static String b(Context paramContext)
  {
    ApplicationInfo localApplicationInfo;
    for (;;)
    {
      try
      {
        localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        if (localApplicationInfo == null) {
          continue;
        }
        if (cl.b(aC)) {
          continue;
        }
        Log.d("APP_SDK", "AppId is setted by code,the value is: " + aC);
        if (localApplicationInfo.metaData != null)
        {
          paramContext = localApplicationInfo.metaData.getString(aj.f() + "_ID");
          if (!cl.b(paramContext))
          {
            if (aC.equals(paramContext.trim())) {
              continue;
            }
            Log.w("APP_SDK", "The AppId setted by code is not equals the value setted by manifest! Please check it!");
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        continue;
        if (localApplicationInfo.metaData == null) {
          continue;
        }
        paramContext = localApplicationInfo.metaData.getString(aj.f() + "_ID");
        if (!cl.b(paramContext)) {
          break label295;
        }
      }
      if (cl.b(aC)) {
        aC = r.getString("APP_ID", "");
      }
      return aC;
      Log.d("APP_SDK", "The AppId in manifest is: " + paramContext.trim());
    }
    paramContext = localApplicationInfo.metaData.getString("APP_ID");
    label295:
    while (!cl.b(paramContext))
    {
      aC = paramContext.trim();
      Log.d("APP_SDK", "AppId is setted by manifest, the value is: " + aC);
      break;
    }
  }
  
  private void b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      SharedPreferences.Editor localEditor = r.edit();
      localEditor.putString("pref_p_user_id", paramString1);
      localEditor.commit();
      paramString1 = c(paramContext, paramString1, paramString2, paramString3);
      paramContext = new bm().a(paramContext, paramString1);
      this.k = paramContext;
      if (aT != null) {
        aT.post(new k(this, paramContext));
      }
      for (;;)
      {
        paramContext.setOnShowListener(new l(this));
        return;
        paramContext.show();
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void b(String paramString, int paramInt)
  {
    if ((b()) && (c())) {}
    for (;;)
    {
      int i2;
      try
      {
        i2 = getHistoryPoints(this.a);
        String str = getHistoryPointsName(this.a);
        if ((paramString != null) && ("spend".equals(paramString)))
        {
          if (i2 >= paramInt)
          {
            i1 = -paramInt;
            i2 -= paramInt;
            paramInt = i1;
            i1 = i2;
            paramString = r.edit();
            int i3 = r.getInt("UpdatePoints", 0);
            i2 = paramInt;
            if (i3 != 0) {
              i2 = paramInt + i3;
            }
            paramString.putInt("UpdatePoints", i2);
            paramString.putInt("AllPoints", i1);
            paramString.commit();
            if (at != null) {
              at.getUpdatePoints(str, i1);
            }
            a(str, i1);
            return;
          }
          if (at != null) {
            at.getUpdatePointsFailed((String)d.get("failed_to_spend_points"));
          }
          a((String)d.get("failed_to_spend_points"));
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      if (paramString != null)
      {
        boolean bool = "award".equals(paramString);
        if (bool)
        {
          i1 = i2 + paramInt;
          continue;
          if ((paramString != null) && ("spend".equals(paramString)))
          {
            if (at != null) {
              at.getUpdatePointsFailed((String)d.get("failed_to_spend_points"));
            }
            a((String)d.get("failed_to_spend_points"));
            return;
          }
          if ((paramString == null) || (!"award".equals(paramString))) {
            break;
          }
          if (at != null) {
            at.getUpdatePointsFailed((String)d.get("failed_to_award_points"));
          }
          a((String)d.get("failed_to_award_points"));
          return;
        }
      }
      int i1 = i2;
      paramInt = 0;
    }
  }
  
  private bl c(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    new bl();
    paramContext = new bl();
    paramContext.a(paramString2);
    paramContext.c(c);
    paramContext.d(this.E);
    paramContext.e(paramString1);
    paramContext.h("OffersWebView");
    if (!cl.b(paramString3)) {
      paramContext.g(paramString3);
    }
    paramContext.a(aT);
    paramContext.a(this.bb);
    paramContext.b(this.ba);
    paramContext.a(this.bc);
    return paramContext;
  }
  
  protected static Map c(Context paramContext)
  {
    if (0 == 0) {
      try
      {
        if (new SDKUtils(paramContext).getLanguageCode().toLowerCase().equals("zh")) {
          return be.a();
        }
        paramContext = be.b();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  private void d(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject1;
    int i1;
    int i2;
    label69:
    String str3;
    String str6;
    String str7;
    String str8;
    if ((g != null) && (g.size() > 0))
    {
      localObject1 = g;
      i1 = 0;
      i2 = -1;
      try
      {
        if (i1 >= ((List)localObject1).size()) {
          break label476;
        }
        if (!((AdInfo)((List)localObject1).get(i1)).getAdId().equals(paramString1)) {
          break label467;
        }
        i2 = i1;
      }
      catch (Exception paramContext)
      {
        try
        {
          String str4;
          String str5;
          Object localObject2 = ((PackageManager)localObject2).getApplicationInfo(paramContext.getPackageName(), 128);
          paramString1 = str2;
          String str2 = paramContext.getPackageName();
          paramString1 = str2;
          if (((ApplicationInfo)localObject2).metaData != null)
          {
            paramString1 = str2;
            localObject1 = ((ApplicationInfo)localObject2).metaData.getString("CLIENT_PACKAGE");
          }
          paramString1 = str2;
          bool = cl.b((String)localObject1);
          paramString1 = str2;
          if (bool) {
            break label483;
          }
          if (cl.b(str4)) {
            break label461;
          }
          paramString1 = new cl(paramContext).a(paramContext, str4);
          if (paramString1 == null) {
            break label489;
          }
          paramString1.setFlags(268435456);
          paramContext.startActivity(paramString1);
          getInstance(paramContext).package_receiver(str4, 2);
          i1 = 0;
          if (i1 == 0) {
            break label482;
          }
          if (cl.b(str5)) {
            break label301;
          }
          new SDKUtils(paramContext).openUrlByBrowser(str5, str3);
          return;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
        paramContext = paramContext;
        paramContext.printStackTrace();
        return;
      }
      paramString1 = (AdInfo)((List)localObject1).get(i2);
      str4 = paramString1.getAdPackage();
      str5 = paramString1.d();
      str3 = paramString1.b();
      str6 = paramString1.c();
      str7 = paramString1.e();
      str8 = paramString1.getAdName();
      localObject1 = "";
      str2 = "";
      localObject2 = paramContext.getPackageManager();
      paramString1 = str2;
    }
    for (;;)
    {
      boolean bool;
      label301:
      if ((!cl.b(paramString2)) && (paramString2.equals("download")))
      {
        new ar().b(paramContext, str7, str8);
        return;
      }
      if ((cl.b(str6)) || (str6.equals("false")))
      {
        new ar().b(paramContext, str7, str8);
        return;
      }
      if ((cl.b(paramString3)) || (!"activity".equals(paramString3)))
      {
        paramString1 = new bl();
        paramString1.a(str3);
        paramString1.d(localNameNotFoundException);
        paramString1.h("OffersWebView");
        paramString1.i(str8);
        if (aT != null)
        {
          aT.post(new o(this, paramContext, paramString1));
          return;
        }
        new bm().a(paramContext, paramString1).show();
        return;
        label461:
        i1 = 1;
        continue;
        label467:
        i1 += 1;
        break;
        label476:
        if (i2 != -1) {
          break label69;
        }
      }
      label482:
      return;
      label483:
      String str1 = paramString1;
      continue;
      label489:
      i1 = 1;
    }
  }
  
  private String e(Context paramContext)
  {
    SDKUtils localSDKUtils = new SDKUtils(paramContext);
    String str = localSDKUtils.getMac_Address();
    Object localObject = str;
    if (!cl.b(str)) {
      localObject = "mac" + str.replaceAll(":", "");
    }
    str = new cl(paramContext).b("android", "/Android/data/.class");
    paramContext = "" + "m_udid=" + localSDKUtils.getUdid();
    paramContext = paramContext + "&m_imsi=" + localSDKUtils.getImsi();
    paramContext = paramContext + "&m_net=" + localSDKUtils.getNetType();
    paramContext = paramContext + "&m_mac=" + (String)localObject;
    localObject = paramContext + "&m_device_name=" + this.s;
    paramContext = (Context)localObject;
    if (!cl.b(str)) {
      paramContext = (String)localObject + "&m_pri_key=" + str;
    }
    localObject = paramContext;
    if (!cl.b(this.T)) {
      localObject = paramContext + "&m_bssid=" + this.T;
    }
    return ((String)localObject).replaceAll(" ", "%20");
  }
  
  /* Error */
  private boolean e(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 942	cn/waps/AppConnect:j	(Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   5: astore 5
    //   7: aload 5
    //   9: ifnull +584 -> 593
    //   12: aload 5
    //   14: ldc_w 944
    //   17: invokeinterface 950 2 0
    //   22: ifnull +571 -> 593
    //   25: aload_0
    //   26: aload 5
    //   28: ldc_w 952
    //   31: invokeinterface 950 2 0
    //   36: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   39: astore_1
    //   40: aload_0
    //   41: aload 5
    //   43: ldc_w 944
    //   46: invokeinterface 950 2 0
    //   51: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   54: astore_2
    //   55: aload_0
    //   56: aload 5
    //   58: ldc_w 956
    //   61: invokeinterface 950 2 0
    //   66: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   69: astore_3
    //   70: aload_0
    //   71: aload 5
    //   73: ldc_w 958
    //   76: invokeinterface 950 2 0
    //   81: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   84: astore 4
    //   86: aload_0
    //   87: aload_0
    //   88: aload 5
    //   90: ldc_w 960
    //   93: invokeinterface 950 2 0
    //   98: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   101: putfield 424	cn/waps/AppConnect:aI	Ljava/lang/String;
    //   104: aload_0
    //   105: aload_0
    //   106: aload 5
    //   108: ldc_w 962
    //   111: invokeinterface 950 2 0
    //   116: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   119: putfield 420	cn/waps/AppConnect:aE	Ljava/lang/String;
    //   122: aload_0
    //   123: getfield 420	cn/waps/AppConnect:aE	Ljava/lang/String;
    //   126: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   129: ifne +452 -> 581
    //   132: aload_0
    //   133: getfield 420	cn/waps/AppConnect:aE	Ljava/lang/String;
    //   136: ldc_w 595
    //   139: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   142: ifeq +439 -> 581
    //   145: iconst_0
    //   146: putstatic 186	cn/waps/AppConnect:aF	Z
    //   149: aload_0
    //   150: aload_0
    //   151: aload 5
    //   153: ldc_w 964
    //   156: invokeinterface 950 2 0
    //   161: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   164: putfield 422	cn/waps/AppConnect:aH	Ljava/lang/String;
    //   167: aload_0
    //   168: getfield 422	cn/waps/AppConnect:aH	Ljava/lang/String;
    //   171: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   174: ifne +421 -> 595
    //   177: aload_0
    //   178: getfield 422	cn/waps/AppConnect:aH	Ljava/lang/String;
    //   181: ldc_w 595
    //   184: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   187: ifeq +408 -> 595
    //   190: iconst_0
    //   191: putstatic 188	cn/waps/AppConnect:aG	Z
    //   194: aload_0
    //   195: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   198: ldc_w 966
    //   201: iconst_3
    //   202: invokevirtual 970	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   205: invokeinterface 519 1 0
    //   210: astore 6
    //   212: aload 6
    //   214: ldc_w 972
    //   217: getstatic 186	cn/waps/AppConnect:aF	Z
    //   220: invokeinterface 976 3 0
    //   225: pop
    //   226: aload 6
    //   228: ldc_w 978
    //   231: getstatic 188	cn/waps/AppConnect:aG	Z
    //   234: invokeinterface 976 3 0
    //   239: pop
    //   240: aload 6
    //   242: invokeinterface 530 1 0
    //   247: pop
    //   248: aload_0
    //   249: aload 5
    //   251: ldc_w 980
    //   254: invokeinterface 950 2 0
    //   259: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   262: astore 6
    //   264: aload 6
    //   266: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   269: ifne +49 -> 318
    //   272: aload 6
    //   274: invokevirtual 593	java/lang/String:length	()I
    //   277: ifle +41 -> 318
    //   280: getstatic 155	cn/waps/AppConnect:r	Landroid/content/SharedPreferences;
    //   283: invokeinterface 519 1 0
    //   288: astore 7
    //   290: aload 7
    //   292: ldc_w 982
    //   295: aload 6
    //   297: invokeinterface 620 3 0
    //   302: pop
    //   303: aload 7
    //   305: invokeinterface 530 1 0
    //   310: pop
    //   311: aload_0
    //   312: aload 6
    //   314: invokevirtual 985	cn/waps/AppConnect:b	(Ljava/lang/String;)Ljava/util/Map;
    //   317: pop
    //   318: aload_0
    //   319: aload 5
    //   321: ldc_w 987
    //   324: invokeinterface 950 2 0
    //   329: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   332: astore 6
    //   334: aload_0
    //   335: aload 5
    //   337: ldc_w 989
    //   340: invokeinterface 950 2 0
    //   345: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   348: astore 7
    //   350: aload 7
    //   352: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   355: ifne +34 -> 389
    //   358: getstatic 155	cn/waps/AppConnect:r	Landroid/content/SharedPreferences;
    //   361: invokeinterface 519 1 0
    //   366: astore 8
    //   368: aload 8
    //   370: ldc_w 991
    //   373: aload 7
    //   375: invokeinterface 620 3 0
    //   380: pop
    //   381: aload 8
    //   383: invokeinterface 530 1 0
    //   388: pop
    //   389: aload_0
    //   390: aload 5
    //   392: ldc_w 993
    //   395: invokeinterface 950 2 0
    //   400: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   403: astore 7
    //   405: aload_0
    //   406: aload 5
    //   408: ldc_w 995
    //   411: invokeinterface 950 2 0
    //   416: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   419: astore 8
    //   421: aload_0
    //   422: aload 5
    //   424: ldc_w 997
    //   427: invokeinterface 950 2 0
    //   432: invokespecial 954	cn/waps/AppConnect:a	(Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   435: astore 5
    //   437: aload 5
    //   439: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   442: ifne +34 -> 476
    //   445: getstatic 155	cn/waps/AppConnect:r	Landroid/content/SharedPreferences;
    //   448: invokeinterface 519 1 0
    //   453: astore 9
    //   455: aload 9
    //   457: ldc_w 997
    //   460: aload 5
    //   462: invokeinterface 620 3 0
    //   467: pop
    //   468: aload 9
    //   470: invokeinterface 530 1 0
    //   475: pop
    //   476: aload_1
    //   477: ifnull +116 -> 593
    //   480: aload_1
    //   481: ldc_w 999
    //   484: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   487: ifeq +106 -> 593
    //   490: aload 4
    //   492: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   495: ifne +9 -> 504
    //   498: aload_0
    //   499: aload 4
    //   501: putfield 291	cn/waps/AppConnect:R	Ljava/lang/String;
    //   504: aload_2
    //   505: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   508: ifne +8 -> 516
    //   511: aload_0
    //   512: aload_2
    //   513: putfield 272	cn/waps/AppConnect:J	Ljava/lang/String;
    //   516: aload_3
    //   517: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   520: ifne +7 -> 527
    //   523: iconst_1
    //   524: putstatic 167	cn/waps/AppConnect:L	Z
    //   527: aload 6
    //   529: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   532: ifne +18 -> 550
    //   535: aload 6
    //   537: ldc_w 999
    //   540: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   543: ifeq +7 -> 550
    //   546: iconst_1
    //   547: putstatic 194	cn/waps/AppConnect:aL	Z
    //   550: aload 7
    //   552: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   555: ifne +9 -> 564
    //   558: aload_0
    //   559: aload 7
    //   561: putfield 430	cn/waps/AppConnect:aR	Ljava/lang/String;
    //   564: aload 8
    //   566: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   569: ifne +53 -> 622
    //   572: aload_0
    //   573: aload 8
    //   575: putfield 432	cn/waps/AppConnect:aS	Ljava/lang/String;
    //   578: goto +44 -> 622
    //   581: iconst_1
    //   582: putstatic 186	cn/waps/AppConnect:aF	Z
    //   585: goto -436 -> 149
    //   588: astore_1
    //   589: aload_1
    //   590: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   593: iconst_0
    //   594: ireturn
    //   595: iconst_1
    //   596: putstatic 188	cn/waps/AppConnect:aG	Z
    //   599: goto -405 -> 194
    //   602: astore 6
    //   604: aload 6
    //   606: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   609: goto -291 -> 318
    //   612: astore 7
    //   614: aload 7
    //   616: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   619: goto -230 -> 389
    //   622: iconst_1
    //   623: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	624	0	this	AppConnect
    //   0	624	1	paramString	String
    //   54	459	2	str1	String
    //   69	448	3	str2	String
    //   84	416	4	str3	String
    //   5	456	5	localObject1	Object
    //   210	326	6	localObject2	Object
    //   602	3	6	localException1	Exception
    //   288	272	7	localObject3	Object
    //   612	3	7	localException2	Exception
    //   366	208	8	localObject4	Object
    //   453	16	9	localEditor	SharedPreferences.Editor
    // Exception table:
    //   from	to	target	type
    //   0	7	588	java/lang/Exception
    //   12	149	588	java/lang/Exception
    //   149	194	588	java/lang/Exception
    //   194	248	588	java/lang/Exception
    //   318	334	588	java/lang/Exception
    //   389	476	588	java/lang/Exception
    //   480	504	588	java/lang/Exception
    //   504	516	588	java/lang/Exception
    //   516	527	588	java/lang/Exception
    //   527	550	588	java/lang/Exception
    //   550	564	588	java/lang/Exception
    //   564	578	588	java/lang/Exception
    //   581	585	588	java/lang/Exception
    //   595	599	588	java/lang/Exception
    //   604	609	588	java/lang/Exception
    //   614	619	588	java/lang/Exception
    //   248	318	602	java/lang/Exception
    //   334	389	612	java/lang/Exception
  }
  
  private void f(Context paramContext)
  {
    String str1;
    String str2;
    try
    {
      boolean bool = r.getBoolean("pref_offers_shown", false);
      str1 = r.getString("pref_user_id", "");
      str2 = r.getString("pref_offers_type", "all");
      if ((!bool) || (!new SDKUtils(paramContext).isConnect())) {
        return;
      }
      if (!cl.b(str1)) {
        break label125;
      }
      if ("all".equals(str2))
      {
        showOffers(paramContext);
        return;
      }
      if ("app".equals(str2))
      {
        showAppOffers(paramContext);
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    if ("game".equals(str2))
    {
      showGameOffers(paramContext);
      return;
      label125:
      if ("all".equals(str2))
      {
        showOffers(paramContext, str1);
        return;
      }
      if ("app".equals(str2))
      {
        showAppOffers(paramContext, str1);
        return;
      }
      if ("game".equals(str2)) {
        showGameOffers(paramContext, str1);
      }
    }
  }
  
  private boolean f(String paramString)
  {
    try
    {
      Object localObject = j(paramString);
      if ((localObject != null) && (((Document)localObject).getElementsByTagName("Result") != null))
      {
        paramString = a(((Document)localObject).getElementsByTagName("Result"));
        String str = a(((Document)localObject).getElementsByTagName("Success"));
        try
        {
          localObject = a(((Document)localObject).getElementsByTagName("Pkg"));
          if ((!cl.b((String)localObject)) && (ao.g != null) && (ao.g.containsKey(localObject))) {
            ao.g.remove(localObject);
          }
          if (!cl.b(paramString)) {
            this.aO = paramString;
          }
          if (cl.b(str)) {
            break label137;
          }
          this.aP = str;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
      }
      return false;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    label137:
    return true;
  }
  
  /* Error */
  private void g(Context paramContext)
  {
    // Byte code:
    //   0: new 803	cn/waps/SDKUtils
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   8: ldc_w 1051
    //   11: ldc_w 1053
    //   14: invokevirtual 1056	cn/waps/SDKUtils:loadStringFromLocal	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   17: astore 6
    //   19: aload 6
    //   21: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   24: ifeq +28 -> 52
    //   27: new 803	cn/waps/SDKUtils
    //   30: dup
    //   31: aload_1
    //   32: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   35: invokestatic 1062	java/lang/System:currentTimeMillis	()J
    //   38: invokestatic 1065	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   41: ldc_w 1051
    //   44: ldc_w 1053
    //   47: iconst_1
    //   48: invokevirtual 1069	cn/waps/SDKUtils:saveDataToLocal	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   51: return
    //   52: aload 6
    //   54: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   57: ifne +148 -> 205
    //   60: aload 6
    //   62: ldc_w 1071
    //   65: ldc -99
    //   67: invokevirtual 904	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   70: invokestatic 1077	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   73: lstore_2
    //   74: invokestatic 1062	java/lang/System:currentTimeMillis	()J
    //   77: lstore 4
    //   79: lload 4
    //   81: lload_2
    //   82: lsub
    //   83: ldc2_w 1078
    //   86: lcmp
    //   87: iflt +118 -> 205
    //   90: invokestatic 1084	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   93: ldc_w 1086
    //   96: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   99: ifeq +50 -> 149
    //   102: new 1088	java/io/File
    //   105: dup
    //   106: new 319	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   113: invokestatic 1092	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   116: invokevirtual 1093	java/io/File:toString	()Ljava/lang/String;
    //   119: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: ldc_w 1053
    //   125: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokespecial 1095	java/io/File:<init>	(Ljava/lang/String;)V
    //   134: astore 6
    //   136: new 803	cn/waps/SDKUtils
    //   139: dup
    //   140: aload_1
    //   141: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   144: aload 6
    //   146: invokevirtual 1099	cn/waps/SDKUtils:deleteLocalFiles	(Ljava/io/File;)V
    //   149: new 803	cn/waps/SDKUtils
    //   152: dup
    //   153: aload_1
    //   154: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   157: new 319	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   164: invokestatic 1062	java/lang/System:currentTimeMillis	()J
    //   167: invokevirtual 1102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   170: ldc -99
    //   172: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: ldc_w 1051
    //   181: ldc_w 1053
    //   184: iconst_1
    //   185: invokevirtual 1069	cn/waps/SDKUtils:saveDataToLocal	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   188: return
    //   189: astore_1
    //   190: aload_1
    //   191: invokevirtual 1103	java/lang/NumberFormatException:printStackTrace	()V
    //   194: return
    //   195: astore 6
    //   197: aload 6
    //   199: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   202: goto -53 -> 149
    //   205: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	AppConnect
    //   0	206	1	paramContext	Context
    //   73	9	2	l1	long
    //   77	3	4	l2	long
    //   17	128	6	localObject	Object
    //   195	3	6	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   0	51	189	java/lang/NumberFormatException
    //   52	79	189	java/lang/NumberFormatException
    //   90	149	189	java/lang/NumberFormatException
    //   149	188	189	java/lang/NumberFormatException
    //   197	202	189	java/lang/NumberFormatException
    //   90	149	195	java/lang/Exception
  }
  
  private boolean g(String paramString)
  {
    paramString = j(paramString);
    if (paramString != null)
    {
      Object localObject = a(paramString.getElementsByTagName("Success"));
      if ((localObject != null) && (((String)localObject).equals("true")))
      {
        localObject = r.edit();
        String str = a(paramString.getElementsByTagName("Points"));
        paramString = a(paramString.getElementsByTagName("CurrencyName"));
        if ((str != null) && (paramString != null))
        {
          if (at != null) {
            at.getUpdatePoints(paramString, Integer.parseInt(str));
          }
          a(paramString, Integer.parseInt(str));
          if (b())
          {
            ((SharedPreferences.Editor)localObject).putInt("AllPoints", Integer.parseInt(str));
            ((SharedPreferences.Editor)localObject).putString("PointName", paramString);
            ((SharedPreferences.Editor)localObject).putLong("GetPointsTime", System.currentTimeMillis());
            ((SharedPreferences.Editor)localObject).commit();
          }
          return true;
        }
      }
      else if ((localObject != null) && (((String)localObject).endsWith("false")))
      {
        paramString = a(paramString.getElementsByTagName("Message"));
        if (at != null) {
          at.getUpdatePointsFailed(paramString);
        }
        a(paramString);
        return true;
      }
    }
    return false;
  }
  
  public static int getHistoryPoints(Context paramContext)
  {
    return r.getInt("AllPoints", 0);
  }
  
  public static String getHistoryPointsName(Context paramContext)
  {
    return r.getString("PointName", (String)d.get("points"));
  }
  
  public static AppConnect getInstance(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (Looper.myLooper() == null)
        {
          Looper.prepare();
          if (paramContext == null) {
            break label135;
          }
          if (q == null) {
            q = new ae(paramContext);
          }
          if (r == null) {
            r = paramContext.getSharedPreferences("AppSettings", 0);
          }
          if (p != null)
          {
            boolean bool = "".equals(c);
            if (!bool) {
              break;
            }
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      try
      {
        if (!cl.b(b(paramContext))) {
          break label121;
        }
        Log.e("APP_SDK", "App_ID is not setted!");
        return null;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          p = new AppConnect(paramContext);
        }
      }
      if (aT == null) {
        aT = new Handler();
      }
    }
    for (;;)
    {
      return p;
      label121:
      label135:
      Log.w("APP_SDK", "The context is null");
    }
  }
  
  public static AppConnect getInstance(String paramString, Context paramContext)
  {
    aC = paramString.replaceAll(" ", "");
    p = getInstance(paramContext);
    if (!cl.b(aC))
    {
      paramString = r.edit();
      paramString.putString("APP_ID", aC);
      paramString.commit();
    }
    return p;
  }
  
  public static AppConnect getInstance(String paramString1, String paramString2, Context paramContext)
  {
    aD = paramString2;
    p = getInstance(paramString1, paramContext);
    return p;
  }
  
  private void h(Context paramContext)
  {
    if (this.m == null) {
      this.m = new bt();
    }
    this.m.a(paramContext, q, r, this.aR, this.aS, this.x, b, c);
  }
  
  private boolean h(String paramString)
  {
    try
    {
      paramString = j(paramString);
      if ((paramString != null) && (paramString.getElementsByTagName("Version") != null))
      {
        String str1 = a(paramString.getElementsByTagName("Success"));
        String str2 = a(paramString.getElementsByTagName("Version"));
        this.aI = a(paramString.getElementsByTagName("Update_tip"));
        if ((str1 != null) && (str1.equals("true")))
        {
          if (!cl.b(str2)) {
            this.J = str2;
          }
          return true;
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  private String i(Context paramContext)
  {
    paramContext = paramContext.getSystemService("user");
    if (paramContext == null) {
      return null;
    }
    try
    {
      Object localObject = Process.class.getMethod("myUserHandle", (Class[])null).invoke(Process.class, (Object[])null);
      long l1 = ((Long)paramContext.getClass().getMethod("getSerialNumberForUser", new Class[] { localObject.getClass() }).invoke(paramContext, new Object[] { localObject })).longValue();
      return String.valueOf(l1);
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private void i(String paramString)
  {
    try
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.a);
      localBuilder.setTitle((CharSequence)d.get("update_version"));
      if (!cl.b(this.aI)) {
        localBuilder.setMessage((String)d.get("new_version") + this.J + (String)d.get("version_is_found") + "\n" + this.aI);
      }
      for (;;)
      {
        localBuilder.setPositiveButton((CharSequence)d.get("download"), new i(this, paramString));
        localBuilder.setNegativeButton((CharSequence)d.get("next_time"), new j(this));
        localBuilder.show();
        return;
        localBuilder.setMessage((String)d.get("new_version") + this.J + (String)d.get("version_is_found"));
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private String j(Context paramContext)
  {
    String str = a(paramContext);
    str = str.substring(0, str.indexOf("&rec"));
    paramContext = cl.a(str + "&" + e(paramContext), "V0FQUw==");
    return "un_rec=" + paramContext;
  }
  
  private Document j(String paramString)
  {
    try
    {
      DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
      paramString = new ByteArrayInputStream(paramString.getBytes("UTF-8"));
      paramString = localDocumentBuilderFactory.newDocumentBuilder().parse(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  private void q()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: invokestatic 1084	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   5: ldc_w 1086
    //   8: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +755 -> 766
    //   14: new 1088	java/io/File
    //   17: dup
    //   18: new 319	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   25: invokestatic 1092	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   28: invokevirtual 1093	java/io/File:toString	()Ljava/lang/String;
    //   31: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: ldc_w 1053
    //   37: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokespecial 1095	java/io/File:<init>	(Ljava/lang/String;)V
    //   46: astore 5
    //   48: new 1088	java/io/File
    //   51: dup
    //   52: new 319	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   59: invokestatic 1092	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   62: invokevirtual 1093	java/io/File:toString	()Ljava/lang/String;
    //   65: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: ldc_w 1053
    //   71: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc_w 1273
    //   77: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: ldc_w 1275
    //   83: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokespecial 1095	java/io/File:<init>	(Ljava/lang/String;)V
    //   92: astore 9
    //   94: aload 5
    //   96: invokevirtual 1278	java/io/File:exists	()Z
    //   99: ifne +9 -> 108
    //   102: aload 5
    //   104: invokevirtual 1281	java/io/File:mkdir	()Z
    //   107: pop
    //   108: aload 9
    //   110: invokevirtual 1278	java/io/File:exists	()Z
    //   113: ifne +9 -> 122
    //   116: aload 9
    //   118: invokevirtual 1284	java/io/File:createNewFile	()Z
    //   121: pop
    //   122: new 1286	java/io/FileInputStream
    //   125: dup
    //   126: aload 9
    //   128: invokespecial 1288	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   131: astore 5
    //   133: new 1290	java/io/BufferedReader
    //   136: dup
    //   137: new 1292	java/io/InputStreamReader
    //   140: dup
    //   141: aload 5
    //   143: invokespecial 1295	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   146: invokespecial 1298	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   149: astore 6
    //   151: aload 6
    //   153: ifnull +52 -> 205
    //   156: ldc -99
    //   158: astore 7
    //   160: aload 6
    //   162: invokevirtual 1301	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   165: astore 10
    //   167: aload 7
    //   169: astore 8
    //   171: aload 10
    //   173: ifnull +36 -> 209
    //   176: new 319	java/lang/StringBuilder
    //   179: dup
    //   180: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   183: aload 7
    //   185: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: aload 10
    //   190: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: astore 8
    //   198: aload 8
    //   200: astore 7
    //   202: goto -42 -> 160
    //   205: ldc -99
    //   207: astore 8
    //   209: aload 8
    //   211: astore 7
    //   213: aload 9
    //   215: astore 8
    //   217: aload_0
    //   218: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   221: invokevirtual 632	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   224: iconst_0
    //   225: invokevirtual 1305	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   228: astore 11
    //   230: iconst_0
    //   231: istore_2
    //   232: iload_2
    //   233: aload 11
    //   235: invokeinterface 827 1 0
    //   240: if_icmpge +148 -> 388
    //   243: aload 11
    //   245: iload_2
    //   246: invokeinterface 830 2 0
    //   251: checkcast 1307	android/content/pm/PackageInfo
    //   254: astore 9
    //   256: aload 9
    //   258: getfield 1311	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   261: getfield 1314	android/content/pm/ApplicationInfo:flags	I
    //   264: istore 4
    //   266: aload 9
    //   268: getfield 1311	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   271: astore 10
    //   273: iload_3
    //   274: istore_1
    //   275: iload 4
    //   277: iconst_1
    //   278: iand
    //   279: ifgt +86 -> 365
    //   282: iload_3
    //   283: iconst_1
    //   284: iadd
    //   285: istore_3
    //   286: aload 9
    //   288: getfield 1317	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   291: astore 10
    //   293: aload 10
    //   295: astore 9
    //   297: aload 10
    //   299: ldc_w 1319
    //   302: invokevirtual 1322	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   305: ifeq +16 -> 321
    //   308: aload 10
    //   310: iconst_3
    //   311: aload 10
    //   313: invokevirtual 593	java/lang/String:length	()I
    //   316: invokevirtual 1233	java/lang/String:substring	(II)Ljava/lang/String;
    //   319: astore 9
    //   321: iload_3
    //   322: istore_1
    //   323: aload 7
    //   325: aload 9
    //   327: invokevirtual 1326	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   330: ifne +35 -> 365
    //   333: new 319	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   340: getstatic 169	cn/waps/AppConnect:az	Ljava/lang/String;
    //   343: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: aload 9
    //   348: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: ldc_w 1328
    //   354: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: putstatic 169	cn/waps/AppConnect:az	Ljava/lang/String;
    //   363: iload_3
    //   364: istore_1
    //   365: iload_2
    //   366: iconst_1
    //   367: iadd
    //   368: istore_2
    //   369: iload_1
    //   370: istore_3
    //   371: goto -139 -> 232
    //   374: astore 8
    //   376: aload 8
    //   378: invokevirtual 1329	java/lang/Throwable:printStackTrace	()V
    //   381: aload 9
    //   383: astore 8
    //   385: goto -168 -> 217
    //   388: getstatic 169	cn/waps/AppConnect:az	Ljava/lang/String;
    //   391: ldc_w 1252
    //   394: invokevirtual 1256	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   397: astore 9
    //   399: aload 8
    //   401: ifnull +359 -> 760
    //   404: new 1331	java/io/FileOutputStream
    //   407: dup
    //   408: aload 8
    //   410: iconst_1
    //   411: invokespecial 1334	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   414: astore 7
    //   416: aload 7
    //   418: aload 9
    //   420: invokevirtual 1339	java/io/OutputStream:write	([B)V
    //   423: aload 7
    //   425: ifnull +8 -> 433
    //   428: aload 7
    //   430: invokevirtual 1342	java/io/OutputStream:close	()V
    //   433: aload 5
    //   435: ifnull +8 -> 443
    //   438: aload 5
    //   440: invokevirtual 1343	java/io/FileInputStream:close	()V
    //   443: aload 6
    //   445: ifnull +8 -> 453
    //   448: aload 6
    //   450: invokevirtual 1344	java/io/BufferedReader:close	()V
    //   453: return
    //   454: astore 5
    //   456: aload 5
    //   458: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   461: return
    //   462: astore 5
    //   464: aconst_null
    //   465: astore 8
    //   467: aconst_null
    //   468: astore 6
    //   470: aconst_null
    //   471: astore 7
    //   473: aload 5
    //   475: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   478: aload 6
    //   480: ifnull +8 -> 488
    //   483: aload 6
    //   485: invokevirtual 1342	java/io/OutputStream:close	()V
    //   488: aload 7
    //   490: ifnull +8 -> 498
    //   493: aload 7
    //   495: invokevirtual 1343	java/io/FileInputStream:close	()V
    //   498: aload 8
    //   500: ifnull -47 -> 453
    //   503: aload 8
    //   505: invokevirtual 1344	java/io/BufferedReader:close	()V
    //   508: return
    //   509: astore 5
    //   511: aload 5
    //   513: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   516: return
    //   517: astore 5
    //   519: aconst_null
    //   520: astore 8
    //   522: aconst_null
    //   523: astore 6
    //   525: aconst_null
    //   526: astore 7
    //   528: aload 6
    //   530: ifnull +8 -> 538
    //   533: aload 6
    //   535: invokevirtual 1342	java/io/OutputStream:close	()V
    //   538: aload 7
    //   540: ifnull +8 -> 548
    //   543: aload 7
    //   545: invokevirtual 1343	java/io/FileInputStream:close	()V
    //   548: aload 8
    //   550: ifnull +8 -> 558
    //   553: aload 8
    //   555: invokevirtual 1344	java/io/BufferedReader:close	()V
    //   558: aload 5
    //   560: athrow
    //   561: astore 6
    //   563: aload 6
    //   565: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   568: goto -10 -> 558
    //   571: astore 8
    //   573: aconst_null
    //   574: astore 6
    //   576: aload 5
    //   578: astore 7
    //   580: aload 8
    //   582: astore 5
    //   584: aconst_null
    //   585: astore 8
    //   587: goto -59 -> 528
    //   590: astore 8
    //   592: aload 5
    //   594: astore 7
    //   596: aload 8
    //   598: astore 5
    //   600: aconst_null
    //   601: astore 9
    //   603: aload 6
    //   605: astore 8
    //   607: aload 9
    //   609: astore 6
    //   611: goto -83 -> 528
    //   614: astore 8
    //   616: aload 5
    //   618: astore 7
    //   620: aconst_null
    //   621: astore 9
    //   623: aload 8
    //   625: astore 5
    //   627: aload 6
    //   629: astore 8
    //   631: aload 9
    //   633: astore 6
    //   635: goto -107 -> 528
    //   638: astore 8
    //   640: aload 5
    //   642: astore 9
    //   644: aload 8
    //   646: astore 5
    //   648: aload 6
    //   650: astore 8
    //   652: aload 7
    //   654: astore 6
    //   656: aload 9
    //   658: astore 7
    //   660: goto -132 -> 528
    //   663: astore 5
    //   665: goto -137 -> 528
    //   668: astore 8
    //   670: aconst_null
    //   671: astore 6
    //   673: aload 5
    //   675: astore 7
    //   677: aload 8
    //   679: astore 5
    //   681: aconst_null
    //   682: astore 8
    //   684: goto -211 -> 473
    //   687: astore 8
    //   689: aload 5
    //   691: astore 7
    //   693: aload 8
    //   695: astore 5
    //   697: aconst_null
    //   698: astore 9
    //   700: aload 6
    //   702: astore 8
    //   704: aload 9
    //   706: astore 6
    //   708: goto -235 -> 473
    //   711: astore 8
    //   713: aload 5
    //   715: astore 7
    //   717: aconst_null
    //   718: astore 9
    //   720: aload 8
    //   722: astore 5
    //   724: aload 6
    //   726: astore 8
    //   728: aload 9
    //   730: astore 6
    //   732: goto -259 -> 473
    //   735: astore 8
    //   737: aload 5
    //   739: astore 9
    //   741: aload 8
    //   743: astore 5
    //   745: aload 6
    //   747: astore 8
    //   749: aload 7
    //   751: astore 6
    //   753: aload 9
    //   755: astore 7
    //   757: goto -284 -> 473
    //   760: aconst_null
    //   761: astore 7
    //   763: goto -340 -> 423
    //   766: ldc -99
    //   768: astore 7
    //   770: aconst_null
    //   771: astore 6
    //   773: aconst_null
    //   774: astore 5
    //   776: aconst_null
    //   777: astore 8
    //   779: goto -562 -> 217
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	782	0	this	AppConnect
    //   274	96	1	i1	int
    //   231	138	2	i2	int
    //   1	370	3	i3	int
    //   264	15	4	i4	int
    //   46	393	5	localObject1	Object
    //   454	3	5	localException1	Exception
    //   462	12	5	localException2	Exception
    //   509	3	5	localException3	Exception
    //   517	60	5	localObject2	Object
    //   582	65	5	localObject3	Object
    //   663	11	5	localObject4	Object
    //   679	96	5	localObject5	Object
    //   149	385	6	localBufferedReader	java.io.BufferedReader
    //   561	3	6	localException4	Exception
    //   574	198	6	localObject6	Object
    //   158	611	7	localObject7	Object
    //   169	47	8	localObject8	Object
    //   374	3	8	localThrowable	Throwable
    //   383	171	8	localObject9	Object
    //   571	10	8	localObject10	Object
    //   585	1	8	localObject11	Object
    //   590	7	8	localObject12	Object
    //   605	1	8	localObject13	Object
    //   614	10	8	localObject14	Object
    //   629	1	8	localObject15	Object
    //   638	7	8	localObject16	Object
    //   650	1	8	localObject17	Object
    //   668	10	8	localException5	Exception
    //   682	1	8	localObject18	Object
    //   687	7	8	localException6	Exception
    //   702	1	8	localObject19	Object
    //   711	10	8	localException7	Exception
    //   726	1	8	localObject20	Object
    //   735	7	8	localException8	Exception
    //   747	31	8	localObject21	Object
    //   92	662	9	localObject22	Object
    //   165	147	10	localObject23	Object
    //   228	16	11	localList	List
    // Exception table:
    //   from	to	target	type
    //   160	167	374	java/lang/Throwable
    //   176	198	374	java/lang/Throwable
    //   428	433	454	java/lang/Exception
    //   438	443	454	java/lang/Exception
    //   448	453	454	java/lang/Exception
    //   2	108	462	java/lang/Exception
    //   108	122	462	java/lang/Exception
    //   122	133	462	java/lang/Exception
    //   483	488	509	java/lang/Exception
    //   493	498	509	java/lang/Exception
    //   503	508	509	java/lang/Exception
    //   2	108	517	finally
    //   108	122	517	finally
    //   122	133	517	finally
    //   533	538	561	java/lang/Exception
    //   543	548	561	java/lang/Exception
    //   553	558	561	java/lang/Exception
    //   133	151	571	finally
    //   160	167	590	finally
    //   176	198	590	finally
    //   376	381	590	finally
    //   217	230	614	finally
    //   232	273	614	finally
    //   286	293	614	finally
    //   297	321	614	finally
    //   323	363	614	finally
    //   388	399	614	finally
    //   404	416	614	finally
    //   416	423	638	finally
    //   473	478	663	finally
    //   133	151	668	java/lang/Exception
    //   160	167	687	java/lang/Exception
    //   176	198	687	java/lang/Exception
    //   376	381	687	java/lang/Exception
    //   217	230	711	java/lang/Exception
    //   232	273	711	java/lang/Exception
    //   286	293	711	java/lang/Exception
    //   297	321	711	java/lang/Exception
    //   323	363	711	java/lang/Exception
    //   388	399	711	java/lang/Exception
    //   404	416	711	java/lang/Exception
    //   416	423	735	java/lang/Exception
  }
  
  private void r()
  {
    this.ax = new x(this, this.G);
    this.ax.execute(new Void[0]);
  }
  
  private void s()
  {
    this.au = new v(this, null);
    this.au.execute(new Void[0]);
  }
  
  private void t()
  {
    this.av = new y(this, null);
    this.av.execute(new Void[0]);
  }
  
  private void u()
  {
    this.aw = new u(this, null);
    this.aw.execute(new Void[0]);
  }
  
  /* Error */
  protected android.graphics.Bitmap a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 803	cn/waps/SDKUtils
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   8: aload_3
    //   9: aload 4
    //   11: invokevirtual 1367	cn/waps/SDKUtils:loadStreamFromLocal	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
    //   14: astore 6
    //   16: aload 6
    //   18: astore 5
    //   20: aload 6
    //   22: ifnonnull +88 -> 110
    //   25: aload 6
    //   27: astore 7
    //   29: aload 6
    //   31: astore 8
    //   33: new 803	cn/waps/SDKUtils
    //   36: dup
    //   37: aload_1
    //   38: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   41: aload_2
    //   42: invokevirtual 1371	cn/waps/SDKUtils:getNetDataToStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   45: astore_2
    //   46: aload_2
    //   47: ifnonnull +15 -> 62
    //   50: aload 6
    //   52: ifnull +8 -> 60
    //   55: aload 6
    //   57: invokevirtual 1374	java/io/InputStream:close	()V
    //   60: aconst_null
    //   61: areturn
    //   62: aload 6
    //   64: astore 7
    //   66: aload 6
    //   68: astore 8
    //   70: new 803	cn/waps/SDKUtils
    //   73: dup
    //   74: aload_1
    //   75: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   78: aload_2
    //   79: aload_3
    //   80: aload 4
    //   82: iconst_0
    //   83: invokevirtual 1377	cn/waps/SDKUtils:saveDataToLocal	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;Z)V
    //   86: aload 6
    //   88: astore 7
    //   90: aload 6
    //   92: astore 8
    //   94: new 803	cn/waps/SDKUtils
    //   97: dup
    //   98: aload_1
    //   99: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   102: aload_3
    //   103: aload 4
    //   105: invokevirtual 1367	cn/waps/SDKUtils:loadStreamFromLocal	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
    //   108: astore 5
    //   110: aload 5
    //   112: astore 7
    //   114: aload 5
    //   116: astore 8
    //   118: new 1379	android/graphics/BitmapFactory$Options
    //   121: dup
    //   122: invokespecial 1380	android/graphics/BitmapFactory$Options:<init>	()V
    //   125: astore_1
    //   126: aload 5
    //   128: astore 7
    //   130: aload 5
    //   132: astore 8
    //   134: aload_1
    //   135: iconst_1
    //   136: putfield 1383	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   139: aload 5
    //   141: astore 7
    //   143: aload 5
    //   145: astore 8
    //   147: aload 5
    //   149: aconst_null
    //   150: aload_1
    //   151: invokestatic 1389	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   154: astore_1
    //   155: aload 5
    //   157: ifnull +8 -> 165
    //   160: aload 5
    //   162: invokevirtual 1374	java/io/InputStream:close	()V
    //   165: aload_1
    //   166: areturn
    //   167: astore_2
    //   168: aconst_null
    //   169: astore_1
    //   170: aload_2
    //   171: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   174: aload_1
    //   175: ifnull +7 -> 182
    //   178: aload_1
    //   179: invokevirtual 1374	java/io/InputStream:close	()V
    //   182: aconst_null
    //   183: areturn
    //   184: astore_2
    //   185: aconst_null
    //   186: astore_1
    //   187: aload_1
    //   188: ifnull +7 -> 195
    //   191: aload_1
    //   192: invokevirtual 1374	java/io/InputStream:close	()V
    //   195: aload_2
    //   196: athrow
    //   197: astore_1
    //   198: goto -138 -> 60
    //   201: astore_2
    //   202: goto -37 -> 165
    //   205: astore_1
    //   206: goto -24 -> 182
    //   209: astore_1
    //   210: goto -15 -> 195
    //   213: astore_2
    //   214: aload 7
    //   216: astore_1
    //   217: goto -30 -> 187
    //   220: astore_2
    //   221: goto -34 -> 187
    //   224: astore_2
    //   225: aload 8
    //   227: astore_1
    //   228: goto -58 -> 170
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	this	AppConnect
    //   0	231	1	paramContext	Context
    //   0	231	2	paramString1	String
    //   0	231	3	paramString2	String
    //   0	231	4	paramString3	String
    //   18	143	5	localInputStream1	InputStream
    //   14	77	6	localInputStream2	InputStream
    //   27	188	7	localInputStream3	InputStream
    //   31	195	8	localInputStream4	InputStream
    // Exception table:
    //   from	to	target	type
    //   0	16	167	java/lang/Exception
    //   0	16	184	finally
    //   55	60	197	java/io/IOException
    //   160	165	201	java/io/IOException
    //   178	182	205	java/io/IOException
    //   191	195	209	java/io/IOException
    //   33	46	213	finally
    //   70	86	213	finally
    //   94	110	213	finally
    //   118	126	213	finally
    //   134	139	213	finally
    //   147	155	213	finally
    //   170	174	220	finally
    //   33	46	224	java/lang/Exception
    //   70	86	224	java/lang/Exception
    //   94	110	224	java/lang/Exception
    //   118	126	224	java/lang/Exception
    //   134	139	224	java/lang/Exception
    //   147	155	224	java/lang/Exception
  }
  
  protected AdInfo a(List paramList)
  {
    return a(paramList, "PopAd_ShowTag");
  }
  
  /* Error */
  protected AdInfo a(List paramList, String paramString)
  {
    // Byte code:
    //   0: ldc_w 1397
    //   3: aload_2
    //   4: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   7: ifeq +130 -> 137
    //   10: aload_1
    //   11: ifnull +118 -> 129
    //   14: aload_1
    //   15: ifnull +152 -> 167
    //   18: aload_1
    //   19: invokeinterface 827 1 0
    //   24: ifle +143 -> 167
    //   27: getstatic 155	cn/waps/AppConnect:r	Landroid/content/SharedPreferences;
    //   30: aload_2
    //   31: iconst_m1
    //   32: invokeinterface 737 3 0
    //   37: istore_3
    //   38: iload_3
    //   39: iconst_m1
    //   40: if_icmpeq +7 -> 47
    //   43: iload_3
    //   44: putstatic 230	cn/waps/AppConnect:bd	I
    //   47: getstatic 230	cn/waps/AppConnect:bd	I
    //   50: aload_1
    //   51: invokeinterface 827 1 0
    //   56: if_icmplt +7 -> 63
    //   59: iconst_0
    //   60: putstatic 230	cn/waps/AppConnect:bd	I
    //   63: aload_1
    //   64: ifnull +98 -> 162
    //   67: aload_1
    //   68: invokeinterface 827 1 0
    //   73: ifle +89 -> 162
    //   76: aload_1
    //   77: getstatic 230	cn/waps/AppConnect:bd	I
    //   80: invokeinterface 830 2 0
    //   85: checkcast 832	cn/waps/AdInfo
    //   88: astore_1
    //   89: getstatic 230	cn/waps/AppConnect:bd	I
    //   92: iconst_1
    //   93: iadd
    //   94: putstatic 230	cn/waps/AppConnect:bd	I
    //   97: getstatic 155	cn/waps/AppConnect:r	Landroid/content/SharedPreferences;
    //   100: invokeinterface 519 1 0
    //   105: astore 4
    //   107: aload 4
    //   109: aload_2
    //   110: getstatic 230	cn/waps/AppConnect:bd	I
    //   113: invokeinterface 527 3 0
    //   118: pop
    //   119: aload 4
    //   121: invokeinterface 530 1 0
    //   126: pop
    //   127: aload_1
    //   128: areturn
    //   129: aload_0
    //   130: invokevirtual 1401	cn/waps/AppConnect:getAdInfoList	()Ljava/util/List;
    //   133: astore_1
    //   134: goto -120 -> 14
    //   137: aload_1
    //   138: ifnull +6 -> 144
    //   141: goto -127 -> 14
    //   144: aconst_null
    //   145: astore_1
    //   146: goto -132 -> 14
    //   149: astore_2
    //   150: aconst_null
    //   151: astore_1
    //   152: aload_2
    //   153: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   156: aload_1
    //   157: areturn
    //   158: astore_2
    //   159: goto -7 -> 152
    //   162: aconst_null
    //   163: astore_1
    //   164: goto -75 -> 89
    //   167: aconst_null
    //   168: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	this	AppConnect
    //   0	169	1	paramList	List
    //   0	169	2	paramString	String
    //   37	7	3	i1	int
    //   105	15	4	localEditor	SharedPreferences.Editor
    // Exception table:
    //   from	to	target	type
    //   0	10	149	java/lang/Exception
    //   18	38	149	java/lang/Exception
    //   43	47	149	java/lang/Exception
    //   47	63	149	java/lang/Exception
    //   67	89	149	java/lang/Exception
    //   129	134	149	java/lang/Exception
    //   89	127	158	java/lang/Exception
  }
  
  protected String a(Context paramContext)
  {
    this.a = paramContext;
    if (cl.b(c))
    {
      a();
      c = c + "app_id=" + this.x + "&";
      c = c + "udid=" + b + "&";
      c = c + this.aa + "=" + this.M + "&";
      c = c + "net=" + this.N + "&";
      c = c + "base=" + this.Q + "&";
      c = c + "app_version=" + y + "&";
      c = c + "sdk_version=" + this.z + "&";
      c = c + "device_name=" + this.s + "&";
      c = c + "device_brand=" + this.B + "&";
      c = c + "y=" + this.O + "&";
      c = c + "device_type=" + this.t + "&";
      c = c + "os_version=" + this.u + "&";
      c = c + "country_code=" + this.v + "&";
      c = c + "language=" + this.w + "&";
      c = c + "act=" + paramContext.getPackageName();
      c = c + "&root=" + this.S;
      if (!cl.b(this.A))
      {
        c += "&";
        c = c + "channel=" + this.A;
      }
      if ((this.H > 0) && (this.I > 0))
      {
        c += "&";
        c = c + "device_width=" + this.H + "&";
        c = c + "device_height=" + this.I;
      }
    }
    paramContext = cl.a(e(this.a), "V0FQUw==");
    if (!cl.b(paramContext)) {
      c = c + "&rec=" + paramContext;
    }
    return c.replaceAll(" ", "%20");
  }
  
  protected List a(InputStream paramInputStream)
  {
    for (;;)
    {
      try
      {
        localXmlPullParser = Xml.newPullParser();
        localXmlPullParser.setInput(paramInputStream, "UTF-8");
        i1 = localXmlPullParser.getEventType();
        paramInputStream = null;
        localAdInfo = null;
        if (i1 != 1) {
          switch (i1)
          {
          }
        }
      }
      catch (Exception localException1)
      {
        XmlPullParser localXmlPullParser;
        int i1;
        AdInfo localAdInfo;
        Object localObject;
        paramInputStream = null;
        localException1.printStackTrace();
        return paramInputStream;
      }
      try
      {
        i1 = localXmlPullParser.next();
      }
      catch (Exception localException2)
      {
        continue;
      }
      try
      {
        localObject = new ArrayList();
        paramInputStream = (InputStream)localObject;
      }
      catch (Exception localException3) {}
    }
    if ("Ad".equals(localXmlPullParser.getName())) {
      localAdInfo = new AdInfo();
    }
    for (;;)
    {
      if (localAdInfo != null)
      {
        if ("Id".equals(localXmlPullParser.getName())) {
          localAdInfo.a(localXmlPullParser.nextText());
        }
        if ("Title".equals(localXmlPullParser.getName())) {
          localAdInfo.h(localXmlPullParser.nextText());
        }
        if ("Image".equals(localXmlPullParser.getName())) {
          localAdInfo.b(localXmlPullParser.nextText());
        }
        if ("Content".equals(localXmlPullParser.getName())) {
          localAdInfo.c(localXmlPullParser.nextText());
        }
        if ("ClickUrl".equals(localXmlPullParser.getName())) {
          localAdInfo.d(localXmlPullParser.nextText());
        }
        if ("Show_detail".equals(localXmlPullParser.getName())) {
          localAdInfo.e(localXmlPullParser.nextText());
        }
        if ("AdPackage".equals(localXmlPullParser.getName())) {
          localAdInfo.f(localXmlPullParser.nextText());
        }
        if ("NewBrowser".equals(localXmlPullParser.getName())) {
          localAdInfo.g(localXmlPullParser.nextText());
        }
        if ("Points".equals(localXmlPullParser.getName())) {
          localAdInfo.a(Integer.parseInt(localXmlPullParser.nextText()));
        }
        if ("Description".equals(localXmlPullParser.getName())) {
          localAdInfo.i(localXmlPullParser.nextText());
        }
        if ("Version".equals(localXmlPullParser.getName())) {
          localAdInfo.j(localXmlPullParser.nextText());
        }
        if ("Filesize".equals(localXmlPullParser.getName())) {
          localAdInfo.k(localXmlPullParser.nextText());
        }
        if ("Provider".equals(localXmlPullParser.getName())) {
          localAdInfo.l(localXmlPullParser.nextText());
        }
        if ("ImageUrls".equals(localXmlPullParser.getName())) {
          localAdInfo.a(localXmlPullParser.nextText().split("\\|"));
        }
        if ("DownUrl".equals(localXmlPullParser.getName())) {
          localAdInfo.m(localXmlPullParser.nextText());
        }
        if ("Action".equals(localXmlPullParser.getName())) {
          localAdInfo.n(localXmlPullParser.nextText());
        }
        if ("Header".equals(localXmlPullParser.getName()))
        {
          localAdInfo.o(localXmlPullParser.nextText());
          break;
          if ((!"Ad".equals(localXmlPullParser.getName())) || (localAdInfo == null) || (cl.b(localAdInfo.getAdId()))) {
            break;
          }
          localObject = localAdInfo.a();
          if (!cl.b((String)localObject)) {
            localAdInfo.a(a(this.a, (String)localObject, localAdInfo.getAdId(), "/Android/data/cache/iconCache"));
          }
          paramInputStream.add(localAdInfo);
          localAdInfo = null;
          break;
          g = paramInputStream;
          return paramInputStream;
        }
      }
      break;
    }
  }
  
  /* Error */
  protected void a()
  {
    // Byte code:
    //   0: getstatic 750	cn/waps/AppConnect:d	Ljava/util/Map;
    //   3: ifnonnull +13 -> 16
    //   6: aload_0
    //   7: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   10: invokestatic 1551	cn/waps/AppConnect:c	(Landroid/content/Context;)Ljava/util/Map;
    //   13: putstatic 750	cn/waps/AppConnect:d	Ljava/util/Map;
    //   16: aload_0
    //   17: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   20: invokevirtual 632	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   23: astore 6
    //   25: aload_0
    //   26: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   29: ldc_w 1553
    //   32: invokevirtual 1158	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   35: checkcast 1555	android/telephony/TelephonyManager
    //   38: astore 4
    //   40: aload 6
    //   42: aload_0
    //   43: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   46: invokevirtual 635	android/content/Context:getPackageName	()Ljava/lang/String;
    //   49: sipush 128
    //   52: invokevirtual 641	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   55: astore 5
    //   57: aload 5
    //   59: ifnull +568 -> 627
    //   62: aload_0
    //   63: aload_0
    //   64: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   67: invokestatic 1129	cn/waps/AppConnect:b	(Landroid/content/Context;)Ljava/lang/String;
    //   70: putfield 250	cn/waps/AppConnect:x	Ljava/lang/String;
    //   73: aload_0
    //   74: getfield 250	cn/waps/AppConnect:x	Ljava/lang/String;
    //   77: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   80: istore_1
    //   81: iload_1
    //   82: ifeq +12 -> 94
    //   85: return
    //   86: astore_2
    //   87: aload_2
    //   88: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   91: goto -75 -> 16
    //   94: aload_0
    //   95: aload_0
    //   96: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   99: invokevirtual 635	android/content/Context:getPackageName	()Ljava/lang/String;
    //   102: putfield 262	cn/waps/AppConnect:E	Ljava/lang/String;
    //   105: getstatic 184	cn/waps/AppConnect:aD	Ljava/lang/String;
    //   108: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   111: ifne +551 -> 662
    //   114: aload_0
    //   115: getstatic 184	cn/waps/AppConnect:aD	Ljava/lang/String;
    //   118: putfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   121: ldc_w 643
    //   124: new 319	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   131: ldc_w 1557
    //   134: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload_0
    //   138: getfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   141: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokestatic 650	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   150: pop
    //   151: ldc -99
    //   153: astore_2
    //   154: aload_0
    //   155: getfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   158: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   161: ifeq +40 -> 201
    //   164: aload_0
    //   165: invokestatic 658	cn/waps/aj:f	()Ljava/lang/String;
    //   168: putfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   171: ldc_w 643
    //   174: new 319	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   181: ldc_w 1559
    //   184: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload_0
    //   188: getfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   191: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokestatic 650	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   200: pop
    //   201: aload 6
    //   203: aload_0
    //   204: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   207: invokevirtual 635	android/content/Context:getPackageName	()Ljava/lang/String;
    //   210: iconst_0
    //   211: invokevirtual 1563	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   214: getfield 1566	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   217: putstatic 161	cn/waps/AppConnect:y	Ljava/lang/String;
    //   220: aload_0
    //   221: ldc_w 906
    //   224: putfield 242	cn/waps/AppConnect:t	Ljava/lang/String;
    //   227: aload_0
    //   228: getstatic 1571	android/os/Build:MODEL	Ljava/lang/String;
    //   231: putfield 240	cn/waps/AppConnect:s	Ljava/lang/String;
    //   234: aload_0
    //   235: getstatic 1574	android/os/Build:BRAND	Ljava/lang/String;
    //   238: putfield 256	cn/waps/AppConnect:B	Ljava/lang/String;
    //   241: aload_0
    //   242: getstatic 1579	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putfield 244	cn/waps/AppConnect:u	Ljava/lang/String;
    //   248: aload_0
    //   249: invokestatic 1585	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   252: invokevirtual 1588	java/util/Locale:getCountry	()Ljava/lang/String;
    //   255: putfield 246	cn/waps/AppConnect:v	Ljava/lang/String;
    //   258: aload_0
    //   259: invokestatic 1585	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   262: invokevirtual 1591	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   265: putfield 248	cn/waps/AppConnect:w	Ljava/lang/String;
    //   268: aload_0
    //   269: aload 4
    //   271: invokevirtual 1594	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   274: putfield 274	cn/waps/AppConnect:M	Ljava/lang/String;
    //   277: new 803	cn/waps/SDKUtils
    //   280: dup
    //   281: aload_0
    //   282: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   285: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   288: invokevirtual 1011	cn/waps/SDKUtils:isConnect	()Z
    //   291: ifeq +559 -> 850
    //   294: aload_0
    //   295: new 803	cn/waps/SDKUtils
    //   298: dup
    //   299: aload_0
    //   300: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   303: invokespecial 805	cn/waps/SDKUtils:<init>	(Landroid/content/Context;)V
    //   306: invokevirtual 899	cn/waps/SDKUtils:getMac_Address	()Ljava/lang/String;
    //   309: putfield 280	cn/waps/AppConnect:P	Ljava/lang/String;
    //   312: aload_0
    //   313: getfield 280	cn/waps/AppConnect:P	Ljava/lang/String;
    //   316: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   319: ifne +510 -> 829
    //   322: aload_0
    //   323: new 319	java/lang/StringBuilder
    //   326: dup
    //   327: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   330: ldc_w 305
    //   333: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: aload_0
    //   337: getfield 280	cn/waps/AppConnect:P	Ljava/lang/String;
    //   340: ldc_w 901
    //   343: ldc -99
    //   345: invokevirtual 904	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   348: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   354: putfield 280	cn/waps/AppConnect:P	Ljava/lang/String;
    //   357: aload_0
    //   358: aload_0
    //   359: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   362: invokestatic 1595	cn/waps/cl:b	(Landroid/content/Context;)Ljava/lang/String;
    //   365: putfield 295	cn/waps/AppConnect:T	Ljava/lang/String;
    //   368: aload_0
    //   369: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   372: ldc_w 1597
    //   375: invokevirtual 1158	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   378: checkcast 1599	android/net/ConnectivityManager
    //   381: invokevirtual 1603	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   384: astore_3
    //   385: aload_3
    //   386: ifnull +30 -> 416
    //   389: aload_3
    //   390: invokevirtual 1608	android/net/NetworkInfo:getTypeName	()Ljava/lang/String;
    //   393: invokevirtual 811	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   396: ldc_w 1610
    //   399: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   402: ifne +461 -> 863
    //   405: aload_0
    //   406: aload_3
    //   407: invokevirtual 1608	android/net/NetworkInfo:getTypeName	()Ljava/lang/String;
    //   410: invokevirtual 811	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   413: putfield 276	cn/waps/AppConnect:N	Ljava/lang/String;
    //   416: ldc_w 643
    //   419: new 319	java/lang/StringBuilder
    //   422: dup
    //   423: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   426: ldc_w 1612
    //   429: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: aload_0
    //   433: getfield 276	cn/waps/AppConnect:N	Ljava/lang/String;
    //   436: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   439: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   442: invokestatic 650	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   445: pop
    //   446: aload_0
    //   447: ldc 12
    //   449: putfield 252	cn/waps/AppConnect:z	Ljava/lang/String;
    //   452: aload_0
    //   453: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   456: ldc_w 1614
    //   459: iconst_0
    //   460: invokevirtual 970	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   463: astore_3
    //   464: aload 5
    //   466: getfield 656	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   469: ifnull +644 -> 1113
    //   472: aload 5
    //   474: getfield 656	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   477: ldc_w 1616
    //   480: invokevirtual 665	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   483: astore_2
    //   484: aload_2
    //   485: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   488: ifne +397 -> 885
    //   491: aload_2
    //   492: putstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   495: new 284	cn/waps/cl
    //   498: dup
    //   499: aload_0
    //   500: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   503: invokespecial 851	cn/waps/cl:<init>	(Landroid/content/Context;)V
    //   506: astore_2
    //   507: aload_2
    //   508: ldc_w 906
    //   511: ldc_w 908
    //   514: invokevirtual 910	cn/waps/cl:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   517: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   520: ifeq +22 -> 542
    //   523: aload_2
    //   524: getstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   527: ldc_w 1239
    //   530: invokestatic 1240	cn/waps/cl:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   533: ldc_w 906
    //   536: ldc_w 908
    //   539: invokevirtual 1619	cn/waps/cl:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   542: aload_0
    //   543: new 319	java/lang/StringBuilder
    //   546: dup
    //   547: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   550: ldc_w 1621
    //   553: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   556: getstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   559: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: aload_0
    //   563: getfield 250	cn/waps/AppConnect:x	Ljava/lang/String;
    //   566: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   572: invokevirtual 811	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   575: invokevirtual 1623	java/lang/String:getBytes	()[B
    //   578: invokestatic 1625	cn/waps/AppConnect:a	([B)Ljava/lang/String;
    //   581: invokevirtual 811	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   584: putfield 278	cn/waps/AppConnect:O	Ljava/lang/String;
    //   587: aload_0
    //   588: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   591: ldc_w 1627
    //   594: invokevirtual 1158	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   597: checkcast 1629	android/view/WindowManager
    //   600: astore_2
    //   601: aload_0
    //   602: aload_2
    //   603: invokeinterface 1633 1 0
    //   608: invokevirtual 1638	android/view/Display:getWidth	()I
    //   611: putfield 268	cn/waps/AppConnect:H	I
    //   614: aload_0
    //   615: aload_2
    //   616: invokeinterface 1633 1 0
    //   621: invokevirtual 1641	android/view/Display:getHeight	()I
    //   624: putfield 270	cn/waps/AppConnect:I	I
    //   627: getstatic 282	cn/waps/aj:l	Ljava/lang/String;
    //   630: invokestatic 287	cn/waps/cl:c	(Ljava/lang/String;)Ljava/lang/String;
    //   633: getstatic 1643	cn/waps/aj:m	Ljava/lang/String;
    //   636: invokestatic 287	cn/waps/cl:c	(Ljava/lang/String;)Ljava/lang/String;
    //   639: invokevirtual 1326	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   642: ifeq +460 -> 1102
    //   645: aload_0
    //   646: getstatic 1645	cn/waps/aj:k	Ljava/lang/String;
    //   649: invokestatic 287	cn/waps/cl:c	(Ljava/lang/String;)Ljava/lang/String;
    //   652: putfield 289	cn/waps/AppConnect:Q	Ljava/lang/String;
    //   655: return
    //   656: astore_2
    //   657: aload_2
    //   658: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   661: return
    //   662: aload 5
    //   664: getfield 656	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   667: ifnull +449 -> 1116
    //   670: aload 5
    //   672: getfield 656	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   675: new 319	java/lang/StringBuilder
    //   678: dup
    //   679: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   682: invokestatic 658	cn/waps/aj:f	()Ljava/lang/String;
    //   685: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: ldc_w 1647
    //   691: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   694: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   697: invokevirtual 1649	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   700: astore_2
    //   701: aload_2
    //   702: ifnull +57 -> 759
    //   705: aload_2
    //   706: invokevirtual 1650	java/lang/Object:toString	()Ljava/lang/String;
    //   709: astore_3
    //   710: aload_3
    //   711: astore_2
    //   712: aload_3
    //   713: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   716: ifne -562 -> 154
    //   719: aload_0
    //   720: aload_3
    //   721: putfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   724: ldc_w 643
    //   727: new 319	java/lang/StringBuilder
    //   730: dup
    //   731: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   734: ldc_w 1652
    //   737: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   740: aload_0
    //   741: getfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   744: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   747: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   750: invokestatic 650	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   753: pop
    //   754: aload_3
    //   755: astore_2
    //   756: goto -602 -> 154
    //   759: aload 5
    //   761: getfield 656	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   764: ldc_w 1654
    //   767: invokevirtual 1649	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   770: astore_2
    //   771: aload_2
    //   772: ifnull +344 -> 1116
    //   775: aload_2
    //   776: invokevirtual 1650	java/lang/Object:toString	()Ljava/lang/String;
    //   779: astore_3
    //   780: aload_3
    //   781: astore_2
    //   782: aload_3
    //   783: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   786: ifne -632 -> 154
    //   789: aload_0
    //   790: aload_3
    //   791: putfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   794: ldc_w 643
    //   797: new 319	java/lang/StringBuilder
    //   800: dup
    //   801: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   804: ldc_w 1652
    //   807: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: aload_0
    //   811: getfield 254	cn/waps/AppConnect:A	Ljava/lang/String;
    //   814: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   820: invokestatic 650	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   823: pop
    //   824: aload_3
    //   825: astore_2
    //   826: goto -672 -> 154
    //   829: ldc_w 643
    //   832: ldc_w 1656
    //   835: invokestatic 673	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   838: pop
    //   839: goto -482 -> 357
    //   842: astore_3
    //   843: aload_3
    //   844: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   847: goto -479 -> 368
    //   850: ldc_w 643
    //   853: ldc_w 1658
    //   856: invokestatic 673	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   859: pop
    //   860: goto -492 -> 368
    //   863: aload_0
    //   864: aload_3
    //   865: invokevirtual 1661	android/net/NetworkInfo:getExtraInfo	()Ljava/lang/String;
    //   868: invokevirtual 811	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   871: putfield 276	cn/waps/AppConnect:N	Ljava/lang/String;
    //   874: goto -458 -> 416
    //   877: astore_3
    //   878: aload_3
    //   879: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   882: goto -436 -> 446
    //   885: aload 4
    //   887: ifnull -392 -> 495
    //   890: aload 4
    //   892: invokevirtual 1664	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   895: putstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   898: getstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   901: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   904: ifeq +9 -> 913
    //   907: ldc_w 595
    //   910: putstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   913: getstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   916: invokevirtual 811	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   919: putstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   922: getstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   925: ldc_w 595
    //   928: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   931: ifeq +131 -> 1062
    //   934: aload_0
    //   935: getfield 280	cn/waps/AppConnect:P	Ljava/lang/String;
    //   938: ldc -99
    //   940: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   943: ifeq +119 -> 1062
    //   946: new 1666	java/lang/StringBuffer
    //   949: dup
    //   950: invokespecial 1667	java/lang/StringBuffer:<init>	()V
    //   953: astore_2
    //   954: aload_2
    //   955: ldc_w 1669
    //   958: invokevirtual 1672	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   961: pop
    //   962: aload_3
    //   963: ldc_w 1674
    //   966: aconst_null
    //   967: invokeinterface 677 3 0
    //   972: astore 4
    //   974: aload 4
    //   976: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   979: ifne +19 -> 998
    //   982: aload 4
    //   984: putstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   987: goto -492 -> 495
    //   990: astore_2
    //   991: aload_2
    //   992: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   995: goto -500 -> 495
    //   998: aload_2
    //   999: new 284	cn/waps/cl
    //   1002: dup
    //   1003: aload_0
    //   1004: getfield 238	cn/waps/AppConnect:a	Landroid/content/Context;
    //   1007: invokespecial 851	cn/waps/cl:<init>	(Landroid/content/Context;)V
    //   1010: ldc_w 1676
    //   1013: bipush 32
    //   1015: invokevirtual 1679	cn/waps/cl:a	(Ljava/lang/String;I)Ljava/lang/String;
    //   1018: invokevirtual 1672	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1021: pop
    //   1022: aload_2
    //   1023: invokevirtual 1680	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1026: invokevirtual 811	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1029: putstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   1032: aload_3
    //   1033: invokeinterface 519 1 0
    //   1038: astore_2
    //   1039: aload_2
    //   1040: ldc_w 1674
    //   1043: getstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   1046: invokeinterface 620 3 0
    //   1051: pop
    //   1052: aload_2
    //   1053: invokeinterface 530 1 0
    //   1058: pop
    //   1059: goto -564 -> 495
    //   1062: getstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   1065: ldc_w 595
    //   1068: invokevirtual 669	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1071: ifeq -576 -> 495
    //   1074: aload_0
    //   1075: getfield 280	cn/waps/AppConnect:P	Ljava/lang/String;
    //   1078: invokestatic 513	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   1081: ifne -586 -> 495
    //   1084: aload_0
    //   1085: getfield 280	cn/waps/AppConnect:P	Ljava/lang/String;
    //   1088: putstatic 159	cn/waps/AppConnect:b	Ljava/lang/String;
    //   1091: goto -596 -> 495
    //   1094: astore_2
    //   1095: aload_2
    //   1096: invokevirtual 495	java/lang/Exception:printStackTrace	()V
    //   1099: goto -472 -> 627
    //   1102: aload_0
    //   1103: getstatic 282	cn/waps/aj:l	Ljava/lang/String;
    //   1106: invokestatic 287	cn/waps/cl:c	(Ljava/lang/String;)Ljava/lang/String;
    //   1109: putfield 289	cn/waps/AppConnect:Q	Ljava/lang/String;
    //   1112: return
    //   1113: goto -629 -> 484
    //   1116: ldc -99
    //   1118: astore_2
    //   1119: goto -965 -> 154
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1122	0	this	AppConnect
    //   80	2	1	bool	boolean
    //   86	2	2	localException1	Exception
    //   153	463	2	localObject1	Object
    //   656	2	2	localException2	Exception
    //   700	255	2	localObject2	Object
    //   990	33	2	localException3	Exception
    //   1038	15	2	localEditor	SharedPreferences.Editor
    //   1094	2	2	localException4	Exception
    //   1118	1	2	str	String
    //   384	441	3	localObject3	Object
    //   842	23	3	localException5	Exception
    //   877	156	3	localException6	Exception
    //   38	945	4	localObject4	Object
    //   55	705	5	localApplicationInfo	ApplicationInfo
    //   23	179	6	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   0	16	86	java/lang/Exception
    //   25	57	656	java/lang/Exception
    //   62	81	656	java/lang/Exception
    //   94	151	656	java/lang/Exception
    //   154	201	656	java/lang/Exception
    //   201	277	656	java/lang/Exception
    //   446	484	656	java/lang/Exception
    //   484	495	656	java/lang/Exception
    //   495	542	656	java/lang/Exception
    //   542	587	656	java/lang/Exception
    //   627	655	656	java/lang/Exception
    //   662	701	656	java/lang/Exception
    //   705	710	656	java/lang/Exception
    //   712	754	656	java/lang/Exception
    //   759	771	656	java/lang/Exception
    //   775	780	656	java/lang/Exception
    //   782	824	656	java/lang/Exception
    //   843	847	656	java/lang/Exception
    //   878	882	656	java/lang/Exception
    //   890	913	656	java/lang/Exception
    //   991	995	656	java/lang/Exception
    //   1095	1099	656	java/lang/Exception
    //   1102	1112	656	java/lang/Exception
    //   277	357	842	java/lang/Exception
    //   357	368	842	java/lang/Exception
    //   829	839	842	java/lang/Exception
    //   850	860	842	java/lang/Exception
    //   368	385	877	java/lang/Exception
    //   389	416	877	java/lang/Exception
    //   416	446	877	java/lang/Exception
    //   863	874	877	java/lang/Exception
    //   913	987	990	java/lang/Exception
    //   998	1059	990	java/lang/Exception
    //   1062	1091	990	java/lang/Exception
    //   587	627	1094	java/lang/Exception
  }
  
  protected void a(AppListener paramAppListener)
  {
    this.aU = paramAppListener;
  }
  
  protected void a(String paramString)
  {
    this.aY = paramString;
  }
  
  protected void a(String paramString, int paramInt)
  {
    this.aW = paramString;
    this.aX = paramInt;
  }
  
  protected void a(String paramString1, boolean paramBoolean1, String paramString2, String paramString3, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    try
    {
      if (("true".equals(paramString1)) && (paramBoolean1) && (!cl.b(paramString2)) && (paramString2.compareTo(paramString3) > 0)) {
        i(aj.c() + aj.t() + c);
      }
      if ((paramBoolean2) && (Environment.getExternalStorageState().equals("mounted")))
      {
        paramString1 = new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/cache" + "/" + "AppPackage.dat");
        if (paramString1.exists()) {
          paramString1.delete();
        }
      }
      if (cl.b(az))
      {
        this.ay = new w(this, null);
        this.ay.execute(new Void[0]);
      }
      if ((paramBoolean3) && (paramBoolean4)) {
        setCrashReport(paramBoolean4);
      }
      return;
    }
    catch (Exception paramString1)
    {
      paramString1 = paramString1;
      paramString1.printStackTrace();
      return;
    }
    finally {}
  }
  
  public void awardPoints(int paramInt)
  {
    awardPoints(paramInt, null);
  }
  
  public void awardPoints(int paramInt, UpdatePointsNotifier paramUpdatePointsNotifier)
  {
    if (paramInt < 0) {}
    do
    {
      return;
      this.D = ("" + paramInt);
    } while (p == null);
    at = paramUpdatePointsNotifier;
    p.u();
  }
  
  protected Map b(String paramString)
  {
    try
    {
      aJ = new HashMap();
      if (cl.b(paramString))
      {
        paramString = r.getString("AttrConfig", "");
        Object localObject = paramString;
        if (paramString.endsWith("[;]")) {
          localObject = paramString.substring(0, paramString.lastIndexOf("[;]"));
        }
        if (((String)localObject).indexOf("[;]") > 0)
        {
          paramString = ((String)localObject).split("\\[;\\]");
          i1 = 0;
          if (i1 < paramString.length)
          {
            if (paramString[i1].trim().indexOf("[=]") <= 0) {
              break label194;
            }
            localObject = paramString[i1].trim().split("\\[=\\]");
            if (localObject.length != 2) {
              break label194;
            }
            aJ.put(localObject[0], localObject[1]);
            break label194;
          }
        }
        else if (((String)localObject).trim().indexOf("[=]") > 0)
        {
          paramString = ((String)localObject).trim().split("\\[=\\]");
          if (paramString.length == 2) {
            aJ.put(paramString[0], paramString[1]);
          }
        }
        return aJ;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        int i1;
        paramString.printStackTrace();
        continue;
        continue;
        label194:
        i1 += 1;
      }
    }
  }
  
  protected boolean b()
  {
    return aV;
  }
  
  protected boolean c()
  {
    return aZ;
  }
  
  public void checkUpdate(Context paramContext)
  {
    aM = new q(this, paramContext);
    aM.execute(new Void[0]);
  }
  
  public void clearPoints()
  {
    this.aW = "";
    this.aX = -1;
    this.aY = "";
  }
  
  public void clickAd(Context paramContext, String paramString)
  {
    try
    {
      d(paramContext, paramString, "", "");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void close()
  {
    try
    {
      if ((this.a != null) && (((Activity)this.a).isFinishing()))
      {
        aK = true;
        aJ = null;
        aQ = null;
        l = true;
        connectResult = "";
        if (p != null) {
          p = null;
        }
        bt.b = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected String d()
  {
    String str1 = new SDKUtils(this.a).loadStringFromLocal("UnPackage.dat", "/Android/data/cache");
    label241:
    label247:
    label256:
    for (;;)
    {
      int i1;
      String str2;
      try
      {
        str3 = new SDKUtils(this.a).getInstalled();
        if (cl.b(str1)) {
          break label241;
        }
        String[] arrayOfString = str1.replaceAll("\n", "").split(";");
        i1 = 0;
        str1 = "";
        if (i1 < arrayOfString.length)
        {
          str2 = str1;
          if (str3.contains(arrayOfString[i1])) {
            break label247;
          }
          if (arrayOfString[i1].startsWith("com."))
          {
            str2 = arrayOfString[i1].substring(3, arrayOfString[i1].length());
            str2 = str1 + str2 + ";";
            break label247;
          }
          str2 = str1 + arrayOfString[i1] + ";";
          break label247;
        }
        if (!str1.endsWith(";")) {
          break label256;
        }
        str1.substring(0, str1.length() - 1);
      }
      catch (Exception localException)
      {
        String str3;
        localException.printStackTrace();
        return "";
      }
      if (!cl.b(str3)) {
        new SDKUtils(this.a).saveDataToLocal(str3, "UnPackage.dat", "/Android/data/cache", false);
      }
      return str1;
      Object localObject = "";
      continue;
      i1 += 1;
      localObject = str2;
    }
  }
  
  protected void d(Context paramContext)
  {
    try
    {
      String str1 = "/data/data/" + paramContext.getPackageName();
      String str2 = this.be + j(paramContext);
      if (Build.VERSION.SDK_INT < 17)
      {
        new a.a().init(str1, null, str2);
        return;
      }
      new a.a().init(str1, i(paramContext), str2);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void downloadAd(Context paramContext, String paramString)
  {
    try
    {
      d(paramContext, paramString, "download", "");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public AdInfo getAdInfo()
  {
    return a(null, "DiyAd_ShowTag");
  }
  
  public List getAdInfoList()
  {
    if (l) {
      initAdInfo();
    }
    if ((g != null) && (g.size() > 0)) {
      return g;
    }
    return null;
  }
  
  public String getConfig(String paramString)
  {
    return getConfig(paramString, null);
  }
  
  public String getConfig(String paramString1, String paramString2)
  {
    if (r.getString("AttrConfig", "").equals(""))
    {
      if (paramString2 == null) {
        return getConfig_Sync(paramString1);
      }
      return paramString2;
    }
    paramString2 = b("");
    if (paramString2 != null) {
      try
      {
        if ((paramString2.size() > 0) && (paramString1 != null) && (!cl.b((String)paramString2.get(paramString1))))
        {
          paramString1 = (String)paramString2.get(paramString1);
          return paramString1;
        }
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
    }
    return "";
  }
  
  public void getConfig(String paramString1, String paramString2, AppListener paramAppListener)
  {
    try
    {
      if (!cl.b(connectResult))
      {
        if (paramAppListener != null) {
          paramAppListener.onGetConfig(getConfig_Sync(paramString1));
        }
      }
      else
      {
        new aa(this, paramString1, paramString2, paramAppListener).execute(new String[0]);
        return;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public Map getConfigMap()
  {
    long l1 = System.currentTimeMillis();
    while (1 != 0) {
      try
      {
        Map localMap = b("");
        if ((localMap != null) || (System.currentTimeMillis() - l1 > 1000L)) {
          break label70;
        }
        Thread.sleep(100L);
        long l2 = System.currentTimeMillis();
        if (l2 - l1 > 1000L) {
          return null;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return null;
    label70:
    return localException;
  }
  
  public String getConfig_Sync(String paramString)
  {
    try
    {
      if (!cl.b(connectResult)) {}
      for (localObject1 = connectResult;; localObject1 = q.a(aj.c() + aj.p(), c))
      {
        if (cl.b((String)localObject1)) {
          break label159;
        }
        localObject1 = j((String)localObject1);
        if (localObject1 == null) {
          break;
        }
        Object localObject2 = ((Document)localObject1).getElementsByTagName("Version");
        if (localObject2 == null) {
          break;
        }
        try
        {
          localObject1 = a(((Document)localObject1).getElementsByTagName("ItemReturn"));
          if ((cl.b((String)localObject1)) || (((String)localObject1).length() <= 0)) {
            break;
          }
          localObject2 = r.edit();
          ((SharedPreferences.Editor)localObject2).putString("AttrConfig", (String)localObject1);
          ((SharedPreferences.Editor)localObject2).commit();
          paramString = (String)b((String)localObject1).get(paramString);
          return paramString;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        Object localObject1;
        label159:
        paramString.printStackTrace();
      }
    }
    return "";
    localObject1 = b("");
    if ((localObject1 != null) && (((Map)localObject1).size() > 0)) {
      return (String)((Map)localObject1).get(paramString);
    }
    return "";
  }
  
  public Dialog getOffersDialog()
  {
    if (this.k != null) {
      return this.k;
    }
    return null;
  }
  
  public void getPoints()
  {
    getPoints(null);
  }
  
  public void getPoints(UpdatePointsNotifier paramUpdatePointsNotifier)
  {
    if (p != null)
    {
      at = paramUpdatePointsNotifier;
      p.s();
    }
  }
  
  public Object[] getPointsData()
  {
    return new Object[] { this.aW, Integer.valueOf(this.aX) };
  }
  
  public String getPointsFailedData()
  {
    return this.aY;
  }
  
  public Dialog getPopAdDialog()
  {
    h(this.a);
    return this.m.a();
  }
  
  public LinearLayout getPopAdView(Context paramContext)
  {
    h(paramContext);
    return this.m.a(paramContext, 0, 0);
  }
  
  public LinearLayout getPopAdView(Context paramContext, int paramInt1, int paramInt2)
  {
    h(paramContext);
    return this.m.a(paramContext, paramInt1, paramInt2);
  }
  
  public boolean hasPopAd(Context paramContext)
  {
    h(paramContext);
    return this.m.c(paramContext);
  }
  
  public void initAdInfo()
  {
    l = false;
    if ((g == null) || (g.isEmpty()))
    {
      aQ = new t(this, null);
      aQ.execute(new Void[0]);
    }
    new n(this).start();
  }
  
  public void initFunAd(Context paramContext)
  {
    if (this.n == null) {
      this.n = new aw(paramContext, q, c);
    }
    this.n.a();
  }
  
  public void initPopAd(Context paramContext)
  {
    h(paramContext);
    this.m.a(paramContext);
  }
  
  public void initUninstallAd(Context paramContext)
  {
    try
    {
      aT.post(new p(this, paramContext));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void package_receiver(String paramString, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      aA = aj.v();
      aB = "install";
    }
    for (;;)
    {
      this.G = paramString;
      if (p != null) {
        p.r();
      }
      return;
      aA = aj.v();
      aB = "install";
      continue;
      aA = aj.w();
      aB = "load";
      continue;
      aA = aj.x();
      aB = "load";
      continue;
      aA = aj.u();
      aA = "uninstall";
      continue;
      aA = aj.y();
      aB = "load";
    }
  }
  
  public void setAdBackColor(int paramInt)
  {
    e = paramInt;
  }
  
  public void setAdForeColor(int paramInt)
  {
    f = paramInt;
  }
  
  public void setCrashReport(boolean paramBoolean)
  {
    aK = paramBoolean;
    if (aK)
    {
      ak.a(this.a, q, c).a();
      aK = false;
    }
  }
  
  public void setFeedbackCloseListener(AppListener paramAppListener)
  {
    a(4, paramAppListener);
  }
  
  public void setMoreCloseListener(AppListener paramAppListener)
  {
    a(2, paramAppListener);
  }
  
  public void setOffersCloseListener(AppListener paramAppListener)
  {
    a(1, paramAppListener);
  }
  
  public void setPointsCache(boolean paramBoolean)
  {
    aV = paramBoolean;
  }
  
  public void setRunnableMap(HashMap paramHashMap)
  {
    this.bb = paramHashMap;
  }
  
  public void setTuanCloseListener(AppListener paramAppListener)
  {
    a(3, paramAppListener);
  }
  
  public void setUpdatePointsFlag(boolean paramBoolean)
  {
    aZ = paramBoolean;
  }
  
  public void setWebViewTransparent(boolean paramBoolean)
  {
    i = paramBoolean;
  }
  
  public void showAppOffers(Context paramContext)
  {
    this.bc = 1;
    a("", "app");
    b(paramContext, b, aj.c() + aj.l(), null);
  }
  
  public void showAppOffers(Context paramContext, String paramString)
  {
    this.bc = 1;
    a(paramString, "app");
    b(paramContext, paramString, aj.c() + aj.l(), null);
  }
  
  public void showBannerAd(Context paramContext, LinearLayout paramLinearLayout)
  {
    new a(paramContext, paramLinearLayout).a();
  }
  
  public void showBrowser(Context paramContext, String paramString)
  {
    showBrowser(paramContext, paramString, false);
  }
  
  public void showBrowser(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (this.n == null) {
      this.n = new aw(paramContext, q, c);
    }
    this.n.a(paramContext, "1", paramString, paramBoolean);
  }
  
  public void showFeedback()
  {
    showFeedback(this.a);
  }
  
  public void showFeedback(Context paramContext)
  {
    this.bc = 4;
    bl localBl = c(paramContext, b, aj.c() + aj.B(), null);
    localBl.b("feedback");
    if (aT != null)
    {
      aT.post(new m(this, paramContext, localBl));
      return;
    }
    new bm().a(paramContext, localBl).show();
  }
  
  public void showFunAd(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString1.equals("browser")) {
      showBrowser(paramContext, paramString2);
    }
    while (!paramString1.equals("player")) {
      return;
    }
    showPlayer(paramContext, paramString2);
  }
  
  public void showGameOffers(Context paramContext)
  {
    this.bc = 1;
    a("", "game");
    b(paramContext, b, aj.c() + aj.k(), null);
  }
  
  public void showGameOffers(Context paramContext, String paramString)
  {
    this.bc = 1;
    a(paramString, "game");
    b(paramContext, paramString, aj.c() + aj.k(), null);
  }
  
  public void showMiniAd(Context paramContext, LinearLayout paramLinearLayout, int paramInt)
  {
    new bf(paramContext, paramLinearLayout).a(paramInt);
  }
  
  public void showMore(Context paramContext)
  {
    this.bc = 2;
    b(paramContext, b, aj.c() + aj.n(), null);
  }
  
  public void showMore(Context paramContext, String paramString)
  {
    this.bc = 2;
    b(paramContext, b, aj.c() + aj.n(), paramString);
  }
  
  public void showOffers(Context paramContext)
  {
    this.bc = 1;
    a("", "all");
    b(paramContext, b, aj.c() + aj.m(), null);
  }
  
  public void showOffers(Context paramContext, String paramString)
  {
    this.bc = 1;
    a(paramString, "all");
    b(paramContext, paramString, aj.c() + aj.m(), null);
  }
  
  public void showPlayer(Context paramContext, String paramString)
  {
    showPlayer(paramContext, paramString, false);
  }
  
  public void showPlayer(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (this.n == null) {
      this.n = new aw(paramContext, q, c);
    }
    this.n.a(paramContext, "2", paramString, paramBoolean);
  }
  
  public void showPopAd(Context paramContext)
  {
    h(paramContext);
    this.m.b(paramContext);
  }
  
  public void showPopAd(Context paramContext, int paramInt)
  {
    h(paramContext);
    this.m.a(paramContext, paramInt);
  }
  
  public void showTuanOffers(Context paramContext)
  {
    this.bc = 3;
    b(paramContext, b, aj.g(), null);
  }
  
  public void showTuanOffers(Context paramContext, String paramString)
  {
    this.bc = 3;
    b(paramContext, paramString, aj.g(), null);
  }
  
  public void spendPoints(int paramInt)
  {
    spendPoints(paramInt, null);
  }
  
  public void spendPoints(int paramInt, UpdatePointsNotifier paramUpdatePointsNotifier)
  {
    if (paramInt < 0) {}
    do
    {
      return;
      this.C = ("" + paramInt);
    } while (p == null);
    at = paramUpdatePointsNotifier;
    p.t();
  }
}
