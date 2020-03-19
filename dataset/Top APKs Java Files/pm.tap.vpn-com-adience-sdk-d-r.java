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
    Object localObject2 = i.a();
    com.adience.sdk.r localR = com.adience.sdk.r.a(this.e);
    Object localObject1;
    int j;
    label59:
    int i;
    if (paramBoolean)
    {
      localObject1 = (Integer)((i)localObject2).v.a();
      j = ((Integer)localObject1).intValue();
      if (!paramBoolean) {
        break label557;
      }
      localObject1 = (Integer)((i)localObject2).x.a();
      i = ((Integer)localObject1).intValue();
      localObject1 = localR.a(j);
      localObject2 = localR.b(i);
    }
    for (;;)
    {
      JSONObject localJSONObject1;
      Object localObject3;
      Object localObject4;
      try
      {
        localJSONObject1 = new JSONObject();
        localObject3 = paramL.e();
        if (localObject3 != null)
        {
          localJSONObject1.put("[276]", localObject3);
          localJSONObject1.put("[139]", paramL.k());
          localJSONObject1.put("[140]", paramL.a());
          localJSONObject1.put("[141]", this.e.getPackageName());
          localJSONObject1.put("[109]", Build.VERSION.RELEASE);
          localJSONObject1.put("[407]", com.adience.sdk.e.n.c(this.e));
          localJSONObject1.put("[111]", ((TelephonyManager)this.e.getSystemService("phone")).getNetworkOperatorName());
          localJSONObject1.put("[112]", k.c());
          if (((List)localObject1).size() > 0)
          {
            localObject3 = new JSONObject();
            localObject4 = ((List)localObject1).iterator();
            if (((Iterator)localObject4).hasNext()) {
              break label614;
            }
            localJSONObject1.put("[128]", localObject3);
          }
          if (((List)localObject2).size() > 0)
          {
            localObject3 = new JSONObject();
            localObject4 = ((List)localObject2).iterator();
            if (((Iterator)localObject4).hasNext()) {
              break label686;
            }
            localJSONObject1.put("[129]", localObject3);
          }
          localJSONObject1.put("[130]", i());
          localObject3 = m.a().b();
          if (localObject3 != null) {
            localJSONObject1.put("[131]", localObject3);
          }
          if (((List)localObject2).size() == i) {
            break label833;
          }
          bool = true;
          localJSONObject1.put("[132]", a(bool));
          localObject3 = paramL.c().b();
          if (localObject3 != null) {
            localJSONObject1.put("[275]", localObject3);
          }
          localObject4 = paramL.n();
          if (localObject4 != null) {
            localJSONObject1.put("[301]", localObject4);
          }
          localJSONObject1.put("[333]", TimeZone.getDefault().getID());
          localJSONObject1.put("[340]", Locale.getDefault().getLanguage());
          if (Build.VERSION.SDK_INT >= 11) {
            localJSONObject1.put("[341]", j());
          }
          localJSONObject1.put("[357]", i.b().b());
          localJSONObject1.put("[412]", com.adience.sdk.n.c(this.e));
          if (!paramBoolean) {
            break label737;
          }
          paramBoolean = this.d.b();
          if (paramBoolean) {
            break label737;
          }
          return false;
          localObject1 = (Integer)((i)localObject2).w.a();
          break;
          label557:
          localObject1 = (Integer)((i)localObject2).y.a();
          break label59;
        }
        localJSONObject1.put("[74]", paramL.h());
        continue;
        localQ = (q)((Iterator)localObject4).next();
      }
      catch (JSONException localJSONException)
      {
        m.a(44, localJSONException, 45, new Object[0]);
        paramL.m();
        return true;
      }
      label614:
      JSONObject localJSONObject2 = localQ.f();
      localJSONObject2.put("[127]", localQ.c());
      j = localQ.h();
      if (j > 0) {
        localJSONObject2.put("[241]", j);
      }
      ((JSONObject)localObject3).put(localQ.d(), localJSONObject2);
      continue;
      label686:
      q localQ = (q)((Iterator)localObject4).next();
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("[127]", localQ.c());
      ((JSONObject)localObject3).put(localQ.d(), localJSONObject2);
      continue;
      label737:
      ((l)paramL.a(o.o)).a(this.f, localJSONObject1.toString());
      localR.a(localJSONException, (List)localObject2);
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
      continue;
      label833:
      boolean bool = false;
    }
  }
  
  protected int b()
  {
    return 44;
  }
}
