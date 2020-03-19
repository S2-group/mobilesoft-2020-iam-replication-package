package com.lookout.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.lookout.LookoutApplication;
import com.lookout.PhoneInfo;
import com.lookout.ad.z;
import com.lookout.bi;
import com.lookout.c.e.w;
import com.lookout.d.l;
import com.lookout.d.m;
import com.lookout.e;
import com.lookout.s;
import com.lookout.u.g;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.a.b;
import org.a.c;
import org.apache.a.e.d;
import org.json.JSONArray;
import org.json.JSONException;

public class k
{
  public static final String[] a;
  public static final String[] b;
  public static final String[] c;
  public static final String[] d;
  public static final String[] e;
  public static final Map f;
  public static final Map g;
  static final List h = Arrays.asList(new String[] { "Z667", "4015T", "0P9O1", "Z830" });
  private static final b i = c.a(k.class);
  private static final String[] j;
  private static final String[] k;
  private static final Map l;
  private static final String[] m;
  private static final Set n;
  private static final Map o;
  private static final Map p;
  private static final String[] q;
  private static final Set r;
  private static Set s;
  private static k t;
  private static final String[] u = { "kindle fire", "kfot", "kftt", "kfjwi", "kfjwa", "kfsowi", "kfapwa", "kfapwi", "kfthwa", "kfthwi", "kfarwi", "kfaswi", "kfsawa", "kfsawi" };
  private static Set v;
  
  static
  {
    a = new String[] { "26201", "20416", "20420" };
    b = new String[] { "20801", "20802" };
    c = new String[] { "26003" };
    d = new String[] { "28310", "60201", "41677", "54101", "65202", "62402", "63086", "63907", "64602", "61404", "60501" };
    e = new String[] { "telogic", "upc" };
    j = new String[] { "Telekom", "Telekom.de", "Telekom D", "T-Mobile", "T-Mobile.de", "T-Mobile D", "Congstar", "congstar.de", "congstar", "ja! mobil", "PENNY MOBIL" };
    k = new String[] { "Sprint" };
    l = new HashMap();
    l.put("uk", "gb");
    l.put("el", "gr");
    m = new String[] { "sgh-t989", "sgh-t759", "sgh-t679" };
    n = new HashSet(Arrays.asList(m));
    o = new HashMap();
    o.put("50501", "Telstra");
    o.put("310410", "AT&T");
    o.put("23433", "EE");
    o.put("23434", "EE");
    o.put("31166", "MetroPCS");
    o.put("311660", "MetroPCS");
    o.put("23430", "EE");
    o.put("44050", "KDDI");
    o.put("44051", "KDDI");
    o.put("21901", "DTCroatia");
    p = new HashMap();
    p.put("htc ruby", Environment.getExternalStorageDirectory().getPath() + File.separator + "ext_sd");
    q = new String[] { "Vcast" };
    r = new HashSet(Arrays.asList(q));
    HashMap localHashMap = new HashMap();
    localHashMap.put("T-Mobile", "T-Mobile");
    localHashMap.put("ATT", "AT&T");
    localHashMap.put("Deutsche_Telecom", "Deutsche Telecom");
    localHashMap.put("EE", "EE");
    localHashMap.put("MetroPCS", "MetroPCS");
    localHashMap.put("Everything_Everywhere", "EE");
    localHashMap.put("Sprint", "Sprint");
    localHashMap.put("com.sprint.ce.update", "Sprint");
    localHashMap.put("com.appattach", "appAttach");
    localHashMap.put("Samsung", "Samsung");
    localHashMap.put("DoMob_interstitial", "DoMob Interstitial");
    localHashMap.put("DoMob_banner", "DoMob Banner");
    localHashMap.put("Amazon_Market", "Amazon Market");
    localHashMap.put("Amazon_Fire", "Amazon Fire");
    localHashMap.put("Orange", "Orange");
    localHashMap.put("au", "au");
    f = Collections.unmodifiableMap(localHashMap);
    localHashMap = new HashMap();
    localHashMap.put("EE", "EE");
    g = Collections.unmodifiableMap(localHashMap);
  }
  
  public k() {}
  
  public static k a()
  {
    try
    {
      if (t == null) {
        t = new k();
      }
      k localK = t;
      return localK;
    }
    finally {}
  }
  
  public static String a(String paramString)
  {
    Iterator localIterator = o.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((String)localEntry.getValue()).equals(paramString)) {
        return (String)localEntry.getKey();
      }
    }
    return null;
  }
  
  private String a(String paramString1, String paramString2)
  {
    String str = null;
    if (Arrays.asList(b).contains(paramString1)) {
      str = "Orange_fr";
    }
    do
    {
      do
      {
        return str;
        if (!Arrays.asList(c).contains(paramString1)) {
          break;
        }
      } while ((paramString2 != null) && (Arrays.asList(e).contains(paramString2.toLowerCase())));
      return "Orange_pl";
    } while (!Arrays.asList(d).contains(paramString1));
    return "Orange";
  }
  
  private boolean a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject == null)
    {
      i.d("Couldn't get carrier.");
      return false;
    }
    String str = b((TelephonyManager)localObject, "");
    localObject = str;
    if (e.a())
    {
      localObject = str;
      if (!TextUtils.isEmpty(com.lookout.i.a.f())) {
        localObject = com.lookout.i.a.f();
      }
    }
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (((String)localObject).equalsIgnoreCase(paramString1)))
    {
      paramContext = o(paramContext);
      if ((TextUtils.isEmpty(paramContext)) || (!paramContext.equalsIgnoreCase(paramString2))) {}
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private String b(String paramString)
  {
    if (l.containsKey(paramString)) {
      return (String)l.get(paramString);
    }
    return paramString;
  }
  
  private String b(String paramString1, String paramString2)
  {
    if (("310120".equals(paramString1)) && (Arrays.asList(k).contains(paramString2))) {
      return "Sprint";
    }
    if (("312530".equals(paramString1)) && ("Sprint".equalsIgnoreCase(paramString2))) {
      return "Sprint Prepaid";
    }
    if (("311870".equals(paramString1)) && ("Boost Mobile".equalsIgnoreCase(paramString2))) {
      return "Boost Mobile Prepaid";
    }
    if (("311490".equals(paramString1)) && ("Virgin Mobile".equalsIgnoreCase(paramString2))) {
      return "Virgin Mobile Prepaid";
    }
    return null;
  }
  
  private String c(TelephonyManager paramTelephonyManager)
  {
    if ((e.a()) && (!TextUtils.isEmpty(com.lookout.i.a.f()))) {
      return com.lookout.i.a.f();
    }
    paramTelephonyManager = paramTelephonyManager.getNetworkCountryIso();
    if (paramTelephonyManager != null) {
      return b(paramTelephonyManager.toLowerCase(Locale.US));
    }
    return null;
  }
  
  private boolean c(String paramString)
  {
    return v.contains(paramString);
  }
  
  public static boolean p()
  {
    return "au".equalsIgnoreCase(g.a().ap());
  }
  
  public static boolean q()
  {
    return "Amazon_Market".equalsIgnoreCase(g.a().ap());
  }
  
  private boolean v()
  {
    boolean bool = false;
    for (;;)
    {
      try
      {
        Object localObject = v;
        if (localObject != null) {
          break label133;
        }
        try
        {
          localObject = t();
          if (TextUtils.isEmpty((CharSequence)localObject))
          {
            i.e("Couldn't read file.");
            return bool;
          }
        }
        catch (Exception localException)
        {
          i.d("Couldn't read file.", localException);
          continue;
        }
        v = new HashSet();
      }
      finally {}
      try
      {
        JSONArray localJSONArray = new JSONArray(str);
        int i1 = 0;
        while (i1 < localJSONArray.length())
        {
          v.add(localJSONArray.getString(i1).toString());
          i1 += 1;
        }
      }
      catch (JSONException localJSONException)
      {
        i.d("Couldn't parse json", localJSONException);
        v = null;
      }
      label133:
      bool = true;
    }
  }
  
  private List w()
  {
    List localList1 = LookoutApplication.getContext().getPackageManager().getInstalledApplications(128);
    if (e.a()) {
      try
      {
        if ((s != null) && (localList1 != null) && (s.size() <= localList1.size()))
        {
          Iterator localIterator = s.iterator();
          int i1 = 0;
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            ((ApplicationInfo)localList1.get(i1)).packageName = str;
            i1 += 1;
          }
        }
        return localList1;
      }
      finally {}
    }
    return localList2;
  }
  
  String a(TelephonyManager paramTelephonyManager)
  {
    if (paramTelephonyManager == null)
    {
      i.d("Couldn't get carrier.");
      return "Unknown";
    }
    Object localObject1;
    if ((e.a()) && (d.c(com.lookout.i.a.e()))) {
      localObject1 = com.lookout.i.a.e();
    }
    Object localObject2;
    while ((d.c((String)localObject1)) && (o.containsKey(localObject1)))
    {
      return (String)o.get(((String)localObject1).toLowerCase(Locale.US));
      localObject2 = paramTelephonyManager.getSimOperator();
      localObject1 = localObject2;
      if (d.b((String)localObject2)) {
        localObject1 = paramTelephonyManager.getNetworkOperator();
      }
    }
    if ((e.a()) && (d.c(com.lookout.i.a.d()))) {
      localObject2 = com.lookout.i.a.d();
    }
    for (;;)
    {
      paramTelephonyManager = b((String)localObject1, (String)localObject2);
      if (paramTelephonyManager == null) {
        break;
      }
      return paramTelephonyManager;
      localObject2 = paramTelephonyManager.getSimOperatorName();
      Object localObject3 = localObject2;
      if (d.b((String)localObject2)) {
        localObject3 = paramTelephonyManager.getNetworkOperatorName();
      }
      localObject2 = localObject3;
      if (d.b((String)localObject3))
      {
        int i1 = paramTelephonyManager.getSimState();
        if (i1 != 1)
        {
          localObject2 = localObject3;
          if (i1 != 0) {}
        }
        else
        {
          localObject2 = "NOSIM";
        }
      }
    }
    paramTelephonyManager = a((String)localObject1, (String)localObject2);
    if (paramTelephonyManager != null) {
      return paramTelephonyManager;
    }
    if ((Arrays.asList(a).contains(localObject1)) && (Arrays.asList(j).contains(localObject2))) {
      return "DT";
    }
    if ((("310260".equals(localObject1)) || ("31026".equals(localObject1))) && ("MetroPCS".equals(localObject2))) {
      return "MetroPCS";
    }
    if (("310260".equals(localObject1)) || ("31026".equals(localObject1))) {
      return "T-Mobile";
    }
    if (o.containsValue(localObject2)) {
      return (String)localObject2 + " " + "Roaming";
    }
    return localObject2;
  }
  
  public String a(TelephonyManager paramTelephonyManager, String paramString)
  {
    paramTelephonyManager = c(paramTelephonyManager);
    if (paramTelephonyManager == null) {
      return paramString;
    }
    return paramTelephonyManager;
  }
  
  public boolean a(Context paramContext)
  {
    return (n.a().e(paramContext)) && (b(paramContext));
  }
  
  public String b(TelephonyManager paramTelephonyManager, String paramString)
  {
    if ((e.a()) && (!TextUtils.isEmpty(com.lookout.i.a.f()))) {
      paramString = com.lookout.i.a.f();
    }
    do
    {
      return paramString;
      paramTelephonyManager = paramTelephonyManager.getSimCountryIso();
    } while (paramTelephonyManager == null);
    return b(paramTelephonyManager.toLowerCase(Locale.US));
  }
  
  public boolean b()
  {
    return (n.contains(Build.MODEL.toLowerCase(Locale.US))) || (w.a().d());
  }
  
  public boolean b(Context paramContext)
  {
    paramContext = o(paramContext);
    return (!TextUtils.isEmpty(paramContext)) && (paramContext.equalsIgnoreCase("Telstra"));
  }
  
  boolean b(TelephonyManager paramTelephonyManager)
  {
    paramTelephonyManager = a(paramTelephonyManager);
    return ("Sprint Prepaid".equals(paramTelephonyManager)) || ("Boost Mobile Prepaid".equals(paramTelephonyManager)) || ("Virgin Mobile Prepaid".equals(paramTelephonyManager));
  }
  
  public boolean c()
  {
    return (Build.MODEL.toLowerCase(Locale.US).startsWith("zte")) || (Build.BRAND.toLowerCase(Locale.US).startsWith("zte"));
  }
  
  public boolean c(Context paramContext)
  {
    return (f(paramContext)) || (g(paramContext));
  }
  
  public boolean d()
  {
    return Build.MODEL.toLowerCase(Locale.US).equals("htc vie_u");
  }
  
  public boolean d(Context paramContext)
  {
    return (l(paramContext)) || (c(paramContext));
  }
  
  public String e(Context paramContext)
  {
    return c((TelephonyManager)paramContext.getSystemService("phone"));
  }
  
  public boolean e()
  {
    return (Arrays.asList(u).contains(Build.MODEL.toLowerCase(Locale.US))) || (g.a().ag());
  }
  
  public boolean f()
  {
    return (g.a().ag()) || (s.a().a("suppress_premium"));
  }
  
  public boolean f(Context paramContext)
  {
    return a(paramContext, "de", "DT");
  }
  
  public boolean g()
  {
    return g.a().ag();
  }
  
  public boolean g(Context paramContext)
  {
    return a(paramContext, "nl", "DT");
  }
  
  public String h()
  {
    return (String)p.get(Build.MODEL.toLowerCase(Locale.US));
  }
  
  public boolean h(Context paramContext)
  {
    return a(paramContext, "us", "T-Mobile");
  }
  
  public boolean i()
  {
    return (w.a().d()) && (!d());
  }
  
  public boolean i(Context paramContext)
  {
    return a(paramContext, "fr", "Orange_fr");
  }
  
  public boolean j()
  {
    return (!m()) && (d.a(bi.a().h(LookoutApplication.getContext()), "EE"));
  }
  
  public boolean j(Context paramContext)
  {
    return a(paramContext, "gb", "EE");
  }
  
  public boolean k()
  {
    return g.a().a("EE");
  }
  
  public boolean k(Context paramContext)
  {
    paramContext = o(paramContext);
    return (!TextUtils.isEmpty(paramContext)) && (paramContext.equalsIgnoreCase("Sprint"));
  }
  
  public boolean l()
  {
    return (k()) || (j()) || (j(LookoutApplication.getContext()));
  }
  
  public boolean l(Context paramContext)
  {
    return a(paramContext, "us", "AT&T");
  }
  
  public boolean m()
  {
    boolean bool1 = k();
    boolean bool2 = g.a().a("samsungKnox");
    return (bool1) || (bool2);
  }
  
  public boolean m(Context paramContext)
  {
    return (n(paramContext)) && (z.a().a(com.lookout.ad.a.D));
  }
  
  public boolean n()
  {
    return (g.a().a("attPrepaid")) || (g.a().a("Prepaid"));
  }
  
  public boolean n(Context paramContext)
  {
    return a(paramContext, "us", "MetroPCS");
  }
  
  public String o(Context paramContext)
  {
    return a((TelephonyManager)paramContext.getSystemService("phone"));
  }
  
  public boolean o()
  {
    return a(LookoutApplication.getContext(), "jp", "KDDI");
  }
  
  public String p(Context paramContext)
  {
    String str = "";
    l localL = new l();
    if (q(paramContext))
    {
      if (localL.b() == m.b) {
        str = a().o(paramContext);
      }
      return str;
    }
    if ((a().l()) && (!a().k())) {
      return (String)f.get("EE");
    }
    return (String)f.get(PhoneInfo.getChannel(paramContext));
  }
  
  public boolean q(Context paramContext)
  {
    return b((TelephonyManager)paramContext.getSystemService("phone"));
  }
  
  public String r()
  {
    Object localObject = LookoutApplication.getContext();
    if (localObject == null)
    {
      i.d("Couldn't get jni context.");
      localObject = null;
      if (localObject != null) {
        break label47;
      }
      i.d("No carrier available, can't determine which header enrichment headers to use.");
    }
    label47:
    while (!((String)localObject).equalsIgnoreCase("telstra"))
    {
      return null;
      localObject = o((Context)localObject);
      break;
    }
    return "x-up-telstra-uid";
  }
  
  public boolean s()
  {
    return r.contains(g.a().ap());
  }
  
  protected String t()
  {
    return com.lookout.androidsecurity.i.a.a(new InputStreamReader(LookoutApplication.getContext().getAssets().open("one_plus.json")));
  }
  
  public int u()
  {
    if (!v())
    {
      i.e("Couldn't load the json array.");
      return 0;
    }
    Object localObject = w();
    if (localObject == null)
    {
      i.e("Couldn't get any installed packages.");
      return 0;
    }
    localObject = ((List)localObject).iterator();
    int i1 = 0;
    if (((Iterator)localObject).hasNext())
    {
      if (!c(((ApplicationInfo)((Iterator)localObject).next()).packageName)) {
        break label88;
      }
      i1 += 1;
    }
    label88:
    for (;;)
    {
      break;
      return i1;
    }
  }
}
