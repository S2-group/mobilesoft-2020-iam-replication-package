import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public final class fz
{
  private static final String a = fz.class.getSimpleName();
  private static fj<String, Drawable> b = new fj();
  
  public fz() {}
  
  public static fj<String, Drawable> a()
  {
    return b;
  }
  
  private static String a(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("imsi", ff.b(paramContext));
        localJSONObject.put("imei", ff.c(paramContext));
        localJSONObject.put("version", Build.VERSION.RELEASE);
        localJSONObject.put("level", Build.VERSION.SDK_INT);
        localObject2 = ff.d(paramContext);
        if (localObject2 != null)
        {
          if (((fh)localObject2).d == null) {
            continue;
          }
          localObject1 = ((fh)localObject2).d;
          localJSONObject.put("nopname", localObject1);
          if (((fh)localObject2).e == null) {
            break label376;
          }
          localObject1 = ((fh)localObject2).e;
          localJSONObject.put("nopid", localObject1);
          if (((fh)localObject2).f == null) {
            break label382;
          }
          localObject1 = ((fh)localObject2).f;
          localJSONObject.put("nopcountry", localObject1);
          if (((fh)localObject2).c == null) {
            break label388;
          }
          localObject1 = ((fh)localObject2).c;
          localJSONObject.put("sopcountry", localObject1);
        }
      }
      catch (JSONException paramContext)
      {
        Object localObject2;
        Object localObject1;
        String str2;
        long l;
        fk.a(a, "Motility - not able to write additional trackingData", paramContext);
        continue;
      }
      try
      {
        localJSONObject.put("app", paramContext.getPackageName() + "_" + paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName);
        localObject1 = new gd(paramContext);
        ((gd)localObject1).a();
        localObject2 = ((gd)localObject1).a("rtd.timestamp");
        str2 = ((gd)localObject1).a("rtd.apps");
        ((gd)localObject1).b();
        l = System.currentTimeMillis();
        if ((localObject2 == null) || ((TextUtils.isDigitsOnly((CharSequence)localObject2)) && (Long.valueOf((String)localObject2).longValue() < l - 900000L)))
        {
          paramContext = a(paramContext, str2);
          if (paramContext != null) {
            localJSONObject.put("installed", paramContext);
          }
        }
        try
        {
          fk.c(a, "Motility - trackingData:" + localJSONObject.toString());
          paramContext = ga.a(localJSONObject.toString());
          return paramContext;
        }
        catch (Exception paramContext)
        {
          fk.a(a, "Motility - not able to encrypt additional trackingData", paramContext);
          return null;
        }
        localObject1 = "";
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localJSONObject.put("app", "not available");
        continue;
      }
      label376:
      String str1 = "";
      continue;
      label382:
      str1 = "";
      continue;
      label388:
      str1 = "";
    }
  }
  
  private static String a(Context paramContext, String paramString)
  {
    int j = 0;
    Object localObject2 = paramContext.getPackageManager();
    Object localObject1 = new ArrayList();
    if (localObject2 != null)
    {
      localObject2 = paramContext.getPackageManager().getInstalledPackages(0);
      i = 0;
      while (i < ((List)localObject2).size())
      {
        localObject3 = (PackageInfo)((List)localObject2).get(i);
        ((List)localObject1).add(((PackageInfo)localObject3).packageName + ":" + ((PackageInfo)localObject3).versionName);
        i += 1;
      }
    }
    localObject2 = new StringBuilder();
    int i = j;
    while (i < ((List)localObject1).size())
    {
      ((StringBuilder)localObject2).append((String)((List)localObject1).get(i));
      if (i + 1 < ((List)localObject1).size()) {
        ((StringBuilder)localObject2).append(',');
      }
      i += 1;
    }
    localObject1 = ((StringBuilder)localObject2).toString();
    Object localObject3 = String.valueOf(((String)localObject1).hashCode());
    fk.c(a, "Motility - compare old hash:" + paramString + " with new hash:" + (String)localObject3);
    localObject2 = new gd(paramContext);
    ((gd)localObject2).a();
    if (paramString == null)
    {
      ((gd)localObject2).a(String.valueOf(System.currentTimeMillis()), "rtd.timestamp");
      ((gd)localObject2).a(localObject3, "rtd.apps");
      paramContext = (Context)localObject1;
    }
    for (;;)
    {
      ((gd)localObject2).b();
      return paramContext;
      if (paramString.equals(String.valueOf(localObject3)))
      {
        paramContext = null;
        ((gd)localObject2).b(String.valueOf(System.currentTimeMillis()), "rtd.timestamp");
      }
      else
      {
        ((gd)localObject2).b(String.valueOf(System.currentTimeMillis()), "rtd.timestamp");
        ((gd)localObject2).b(localObject3, "rtd.apps");
        paramContext = (Context)localObject1;
      }
    }
  }
  
  private static String a(fq paramFq)
  {
    paramFq = b(paramFq);
    try
    {
      fk.c(a, "Motility - geoData:" + paramFq.toString());
      paramFq = ga.a(paramFq.toString());
      return paramFq;
    }
    catch (Exception paramFq)
    {
      fk.a(a, "Motility - not able to encrypt additional geoData", paramFq);
    }
    return null;
  }
  
  private static String a(fv paramFv)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (paramFv.c.name() != null) {
        localJSONObject.put("gender", paramFv.c.name());
      }
      if (paramFv.b != null) {
        localJSONObject.put("yob", paramFv.b);
      }
      if (paramFv.a != null) {
        localJSONObject.put("dob", paramFv.a.getTime());
      }
      if (paramFv.d != null) {
        localJSONObject.put("geo", b(paramFv.d));
      }
      return null;
    }
    catch (JSONException paramFv)
    {
      for (;;)
      {
        try
        {
          fk.c(a, "Motility - userData:" + localJSONObject.toString());
          paramFv = ga.a(localJSONObject.toString());
          return paramFv;
        }
        catch (Exception paramFv)
        {
          fk.a(a, "Motility - not able to encrypt additional geoData", paramFv);
        }
        paramFv = paramFv;
        fk.a(a, "Motility - not able to write additional geoData", paramFv);
      }
    }
  }
  
  private static List<NameValuePair> a(List<ft> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      Iterator localIterator = paramList.iterator();
      int i = 1;
      while (localIterator.hasNext())
      {
        localStringBuilder.append(((ft)localIterator.next()).a());
        if (paramList.size() > i) {
          localStringBuilder.append(',');
        }
        i += 1;
      }
    }
    paramList = new ArrayList();
    if (!TextUtils.isEmpty(localStringBuilder)) {
      paramList.add(new BasicNameValuePair("adTypes", localStringBuilder.toString()));
    }
    return paramList;
  }
  
  public static List<NameValuePair> a(List<String> paramList, List<ft> paramList1, String paramString1, fv paramFv, boolean paramBoolean, String paramString2, Context paramContext, List<NameValuePair> paramList2)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (!TextUtils.isEmpty(paramString2)) {
      localArrayList2.add(new BasicNameValuePair("pubid", paramString2));
    }
    fd localFd;
    Object localObject;
    label395:
    boolean bool;
    label440:
    int i;
    if (2 == paramContext.getResources().getConfiguration().orientation)
    {
      localArrayList2.add(new BasicNameValuePair("orientation", "landscape"));
      if (paramContext != null)
      {
        localArrayList2.add(new BasicNameValuePair("useragent", fe.c(paramContext)));
        localFd = fg.a(paramContext);
        if (localFd != null)
        {
          if ((localFd.d != null) && (localFd.d.size() > 0))
          {
            List localList = localFd.d;
            localObject = null;
            paramString2 = (String)localObject;
            if (localList != null)
            {
              paramString2 = (String)localObject;
              if (localList.size() > 0)
              {
                localObject = (Address)localList.get(0);
                fk.a(a, "Motility - use geo address: " + localObject);
                paramString2 = new fq();
                paramString2.a = Double.valueOf(((Address)localObject).getLatitude());
                paramString2.b = Double.valueOf(((Address)localObject).getLongitude());
                localList = fg.a(((Address)localObject).getCountryCode());
                if ((localList != null) && (!localList.isEmpty())) {
                  paramString2.c = ((Locale)localList.get(0)).getISO3Country();
                }
                paramString2.i = fr.a;
                paramString2.h = ((Address)localObject).getPostalCode();
              }
            }
            fk.a(a, "Motility - geodata:" + paramString2.toString());
            if (paramString2 != null) {
              localArrayList2.add(new BasicNameValuePair("rtbGeo", a(paramString2)));
            }
          }
          localObject = null;
          paramString2 = (String)localObject;
          if (localFd != null)
          {
            if (TextUtils.isEmpty(localFd.a)) {
              break label926;
            }
            paramString2 = localFd.a;
          }
          localArrayList2.add(new BasicNameValuePair("country", paramString2));
        }
        if ((!fe.a(paramContext)) || (!"wifi".equals(fe.b(paramContext)))) {
          break label972;
        }
        bool = true;
        localArrayList2.add(new BasicNameValuePair("wifi", String.valueOf(bool)));
        paramString2 = ff.d(paramContext);
        if (paramString2 != null)
        {
          localArrayList2.add(new BasicNameValuePair("sopname", paramString2.a));
          localArrayList2.add(new BasicNameValuePair("sopid", paramString2.b));
        }
        paramString2 = paramContext.getResources().getDisplayMetrics();
        i = paramString2.densityDpi;
        localArrayList2.add(new BasicNameValuePair("screenWidth", String.valueOf(paramString2.widthPixels)));
        switch (i)
        {
        default: 
          localArrayList2.add(new BasicNameValuePair("screenDensity", "hdpi"));
        }
      }
    }
    for (;;)
    {
      localArrayList2.add(new BasicNameValuePair("rtd", a(paramContext)));
      localArrayList2.add(new BasicNameValuePair("motrev", "3.9.0"));
      paramString2 = new gd(paramContext);
      paramString2.a();
      localObject = paramString2.b("new.install.checked");
      paramString2.b();
      if (localObject == null)
      {
        localArrayList2.add(new BasicNameValuePair("nist", "true"));
        paramString2 = new gd(paramContext);
        paramString2.a();
        paramString2.a(Boolean.valueOf(false), "new.install.checked");
        paramString2.b();
      }
      localArrayList1.addAll(localArrayList2);
      localArrayList1.addAll(a(paramList1));
      if (paramFv != null) {
        localArrayList1.add(new BasicNameValuePair("rtbUser", a(paramFv)));
      }
      if ((paramList2 == null) || (paramList2.size() <= 0)) {
        break label1074;
      }
      paramList1 = paramList2.iterator();
      while (paramList1.hasNext())
      {
        paramFv = (NameValuePair)paramList1.next();
        localArrayList1.add(new BasicNameValuePair("motc" + paramFv.getName(), paramFv.getValue()));
      }
      localArrayList2.add(new BasicNameValuePair("orientation", "portrait"));
      break;
      label926:
      if (!TextUtils.isEmpty(localFd.b))
      {
        paramString2 = localFd.b;
        break label395;
      }
      paramString2 = (String)localObject;
      if (TextUtils.isEmpty(localFd.c)) {
        break label395;
      }
      paramString2 = localFd.c;
      break label395;
      label972:
      bool = false;
      break label440;
      localArrayList2.add(new BasicNameValuePair("screenDensity", "ldpi"));
      continue;
      localArrayList2.add(new BasicNameValuePair("screenDensity", "mdpi"));
      continue;
      localArrayList2.add(new BasicNameValuePair("screenDensity", "hdpi"));
      continue;
      localArrayList2.add(new BasicNameValuePair("screenDensity", "xhdpi"));
    }
    label1074:
    paramList1 = new StringBuilder();
    if ((paramList != null) && (paramList.size() > 0))
    {
      paramFv = paramList.iterator();
      i = 1;
      while (paramFv.hasNext())
      {
        paramList1.append((String)paramFv.next());
        if (paramList.size() > i) {
          paramList1.append(',');
        }
        i += 1;
      }
      localArrayList1.add(new BasicNameValuePair("keywords", paramList1.toString()));
    }
    if (paramBoolean)
    {
      localArrayList1.add(new BasicNameValuePair("testmode", "true"));
      if ((!TextUtils.isEmpty(paramString1)) && (paramString1.equals("Motility-TestApp-Debugmode"))) {
        fk.a();
      }
    }
    if (!TextUtils.isEmpty(paramString1)) {
      localArrayList1.add(new BasicNameValuePair("pageid", paramString1));
    }
    return localArrayList1;
  }
  
  public static void a(String paramString, Context paramContext)
  {
    fk.c(a, "Motility - advertisement successful clicked and redirected");
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setFlags(268435456);
    if (paramContext.getPackageManager().queryIntentActivities(paramString, 0).size() > 0) {
      paramContext.startActivity(paramString);
    }
  }
  
  private static JSONObject b(fq paramFq)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (paramFq.b != null) {
        localJSONObject.put("lon", paramFq.b);
      }
      if (paramFq.a != null) {
        localJSONObject.put("lat", paramFq.a);
      }
      if (paramFq.c != null) {
        localJSONObject.put("country", paramFq.c);
      }
      if (paramFq.d != null) {
        localJSONObject.put("region", paramFq.d);
      }
      if (paramFq.f != null) {
        localJSONObject.put("metro", paramFq.f);
      }
      if (paramFq.e != null) {
        localJSONObject.put("regionfips", paramFq.e);
      }
      if (paramFq.g != null) {
        localJSONObject.put("city", paramFq.g);
      }
      if (paramFq.i != null) {
        localJSONObject.put("type", paramFq.i);
      }
      if (paramFq.i != null) {
        localJSONObject.put("zip", paramFq.h);
      }
      return localJSONObject;
    }
    catch (JSONException paramFq)
    {
      fk.a(a, "Motility - not able to write additional geoData", paramFq);
    }
    return localJSONObject;
  }
}
