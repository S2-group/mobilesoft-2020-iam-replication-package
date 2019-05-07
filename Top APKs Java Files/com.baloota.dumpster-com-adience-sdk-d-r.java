package com.adience.sdk.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.adience.a.c;
import com.adience.sdk.aa;
import com.adience.sdk.d;
import com.adience.sdk.e.m;
import com.adience.sdk.g;
import com.adience.sdk.i;
import com.adience.sdk.i.b;
import com.adience.sdk.i.d;
import com.adience.sdk.i.f;
import com.adience.sdk.k;
import com.adience.sdk.o;
import com.adience.sdk.q;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class r
  extends f
  implements e
{
  public r(Context paramContext, com.adience.sdk.a.f paramF)
  {
    super(paramContext, o.h, o.g, i.a().r, i.a().s, i.a().t, paramF);
  }
  
  private JSONObject a(boolean paramBoolean)
    throws JSONException
  {
    Object localObject = d.a(this.e);
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = ((d)localObject).c();
    if (localJSONObject2.length() > 0) {
      localJSONObject1.put("[133]", localJSONObject2);
    }
    localJSONObject2 = ((d)localObject).h();
    if (localJSONObject2.length() > 0) {
      localJSONObject1.put("[135]", localJSONObject2);
    }
    JSONObject localJSONObject3 = ((d)localObject).j();
    if (localJSONObject2.length() > 0) {
      localJSONObject1.put("[136]", localJSONObject3);
    }
    localJSONObject2 = ((d)localObject).f();
    if (localJSONObject2.length() > 0) {
      localJSONObject1.put("[137]", localJSONObject2);
    }
    localJSONObject1.put("[138]", paramBoolean);
    localObject = ((d)localObject).e();
    if (((JSONObject)localObject).length() > 0) {
      localJSONObject1.put("[300]", localObject);
    }
    localJSONObject1.put("[373]", d.a());
    return localJSONObject1;
  }
  
  static boolean a(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0) {
      return true;
    }
    Iterator localIterator = i.a().c().iterator();
    String str;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      str = ((String)localIterator.next()).toLowerCase(Locale.US);
    } while (!paramPackageInfo.packageName.toLowerCase(Locale.US).contains(str));
    return true;
  }
  
  @TargetApi(9)
  private JSONObject i()
    throws JSONException
  {
    PackageManager localPackageManager = this.e.getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(0);
    JSONObject localJSONObject = new JSONObject();
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return localJSONObject;
      }
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (a(localPackageInfo))
      {
        String str = k.a(localPackageManager, localPackageInfo);
        localJSONObject.accumulate(str, localPackageInfo.packageName);
        if (Build.VERSION.SDK_INT >= 9) {
          localJSONObject.accumulate(str, Long.valueOf(localPackageInfo.firstInstallTime));
        }
      }
    }
  }
  
  @TargetApi(11)
  private JSONObject j()
  {
    JSONObject localJSONObject = new JSONObject();
    InputMethodManager localInputMethodManager = (InputMethodManager)this.e.getSystemService("input_method");
    Iterator localIterator = localInputMethodManager.getEnabledInputMethodList().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localJSONObject;
      }
      Object localObject = (InputMethodInfo)localIterator.next();
      String str1 = ((InputMethodInfo)localObject).getPackageName();
      try
      {
        localJSONObject.put(str1, new JSONArray());
        localObject = localInputMethodManager.getEnabledInputMethodSubtypeList((InputMethodInfo)localObject, true).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str2 = ((InputMethodSubtype)((Iterator)localObject).next()).getLocale();
          if (!TextUtils.isEmpty(str2))
          {
            int j = str2.indexOf("_");
            int i = j;
            if (j == -1) {
              i = str2.length();
            }
            str2 = str2.substring(0, i);
            try
            {
              localJSONObject.accumulate(str1, str2);
            }
            catch (JSONException localJSONException1) {}
          }
        }
      }
      catch (JSONException localJSONException2)
      {
        for (;;) {}
      }
    }
  }
  
  public void a(l paramL, String paramString)
    throws aa
  {
    long l1 = ((Long)i.a().u.a()).longValue();
    long l2 = new Random().nextInt((int)l1);
    long l3 = l1 / 2L;
    paramL.a(this.f, paramString, l1 + (l2 - l3));
  }
  
  public void a(com.adience.sdk.l paramL, c paramC, String paramString)
    throws com.adience.a.b, IOException, aa
  {
    paramL = a(paramL, paramC.a("sub", paramString, true));
    if (paramL != null)
    {
      paramL = paramL.optJSONArray("bl");
      if (i.a() != null)
      {
        paramC = i.b();
        paramC.a(paramL);
        paramC.a();
      }
    }
  }
  
  public boolean a(boolean paramBoolean, com.adience.sdk.l paramL)
    throws aa
  {
    h();
    localObject2 = i.a();
    localR = com.adience.sdk.r.a(this.e);
    Object localObject1;
    int j;
    label59:
    int i;
    if (paramBoolean)
    {
      localObject1 = (Integer)((i)localObject2).v.a();
      j = ((Integer)localObject1).intValue();
      if (!paramBoolean) {
        break label609;
      }
      localObject1 = (Integer)((i)localObject2).x.a();
      i = ((Integer)localObject1).intValue();
      localObject1 = localR.a(j);
      localObject2 = localR.b(i);
    }
    for (;;)
    {
      try
      {
        localJSONObject = new JSONObject();
        localObject3 = paramL.e();
        if (localObject3 != null)
        {
          localJSONObject.put("[276]", localObject3);
          localJSONObject.put("[139]", paramL.k());
          localJSONObject.put("[140]", paramL.a());
          localJSONObject.put("[141]", this.e.getPackageName());
          localJSONObject.put("[109]", Build.VERSION.RELEASE);
          localJSONObject.put("[407]", com.adience.sdk.e.n.c(this.e));
          localJSONObject.put("[111]", ((TelephonyManager)this.e.getSystemService("phone")).getNetworkOperatorName());
          localJSONObject.put("[112]", k.c());
          if (((List)localObject1).size() > 0)
          {
            localObject3 = new JSONObject();
            localObject4 = ((List)localObject1).iterator();
            if (((Iterator)localObject4).hasNext()) {
              break label666;
            }
            localJSONObject.put("[128]", localObject3);
          }
          if (((List)localObject2).size() > 0)
          {
            localObject3 = new JSONObject();
            localObject4 = ((List)localObject2).iterator();
            if (((Iterator)localObject4).hasNext()) {
              break label738;
            }
            localJSONObject.put("[129]", localObject3);
          }
          localJSONObject.put("[130]", i());
          localObject3 = m.a().b();
          if (localObject3 != null) {
            localJSONObject.put("[131]", localObject3);
          }
          if (((List)localObject2).size() == i) {
            break label789;
          }
          bool = true;
          localJSONObject.put("[132]", a(bool));
          localObject3 = paramL.c().b();
          if (localObject3 != null) {
            localJSONObject.put("[275]", localObject3);
          }
          localObject4 = paramL.n();
          if (localObject4 != null) {
            localJSONObject.put("[301]", localObject4);
          }
          localJSONObject.put("[333]", TimeZone.getDefault().getID());
          localJSONObject.put("[340]", Locale.getDefault().getLanguage());
          if (Build.VERSION.SDK_INT >= 11) {
            localJSONObject.put("[341]", j());
          }
          localJSONObject.put("[357]", i.b().b());
          localJSONObject.put("[412]", com.adience.sdk.n.c(this.e));
          localObject5 = new JSONObject();
        }
      }
      catch (JSONException localJSONException1)
      {
        label609:
        m.a(44, localJSONException1, 45, new Object[0]);
      }
      try
      {
        localObject6 = new JSONArray((String)i.a().ac.a());
        i = 0;
        if (i < ((JSONArray)localObject6).length()) {
          break label795;
        }
        localJSONObject.put("[481]", localObject5);
      }
      catch (JSONException localJSONException2)
      {
        for (;;)
        {
          String str;
          m.a(44, localJSONException2);
          break;
          ((l)paramL.a(o.o)).a(this.f, localJSONObject.toString());
          localR.a(localJSONException1, (List)localObject2);
          d localD = d.a(this.e);
          localD.d();
          localD.g();
          localD.i();
          localD.k();
          if (localObject3 != null) {
            paramL.c().c();
          }
          if (localObject4 != null) {
            paramL.o();
          }
          m.a().c();
        }
      }
      if (!paramBoolean) {
        break label835;
      }
      paramBoolean = this.d.b();
      if (paramBoolean) {
        break label835;
      }
      return false;
      localObject1 = (Integer)((i)localObject2).w.a();
      break;
      localObject1 = (Integer)((i)localObject2).y.a();
      break label59;
      localJSONObject.put("[74]", paramL.h());
      continue;
      paramL.m();
      return true;
      label666:
      Object localObject5 = (q)((Iterator)localObject4).next();
      Object localObject6 = ((q)localObject5).f();
      ((JSONObject)localObject6).put("[127]", ((q)localObject5).c());
      j = ((q)localObject5).h();
      if (j > 0) {
        ((JSONObject)localObject6).put("[241]", j);
      }
      ((JSONObject)localObject3).put(((q)localObject5).d(), localObject6);
      continue;
      label738:
      localObject5 = (q)((Iterator)localObject4).next();
      localObject6 = new JSONObject();
      ((JSONObject)localObject6).put("[127]", ((q)localObject5).c());
      ((JSONObject)localObject3).put(((q)localObject5).d(), localObject6);
      continue;
      label789:
      boolean bool = false;
      continue;
      label795:
      str = ((JSONArray)localObject6).getString(i);
      ((JSONObject)localObject5).put(str, System.getProperty(str));
      i += 1;
    }
  }
  
  protected int b()
  {
    return 44;
  }
}
