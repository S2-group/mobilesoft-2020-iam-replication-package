package me.rapchat.rapchat.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Telephony.Sms;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import com.a.a.a;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import me.rapchat.rapchat.rest.objects.Featuring;
import me.rapchat.rapchat.rest.objects.Rap;
import me.rapchat.rapchat.rest.objects.t;
import me.rapchat.rapchat.views.main.fragments.BottomSheetFragment;
import org.json.JSONException;
import org.json.JSONObject;

public class q
{
  private static q a = new q();
  
  public q() {}
  
  public static int a(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return android.support.v4.content.b.c(paramContext, paramInt);
    }
    return paramContext.getResources().getColor(paramInt);
  }
  
  public static String a(BigDecimal paramBigDecimal)
  {
    int i = (int)paramBigDecimal.longValue();
    i -= i / 3600 * 3600;
    int j = i / 60;
    paramBigDecimal = new StringBuilder();
    paramBigDecimal.append(j);
    paramBigDecimal.append(":");
    paramBigDecimal.append(i - j * 60);
    paramBigDecimal.append("s");
    return paramBigDecimal.toString();
  }
  
  public static t a(Activity paramActivity)
  {
    return (t)new Gson().fromJson(b("user_session", "", paramActivity), t.class);
  }
  
  public static q a()
  {
    return a;
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    Snackbar.make(paramActivity.getWindow().getDecorView().findViewById(16908290), paramString, 2000).show();
  }
  
  public static void a(Activity paramActivity, t paramT)
  {
    a("user_session", new Gson().toJson(paramT), paramActivity);
  }
  
  public static void a(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.clear();
    paramContext.commit();
  }
  
  public static void a(FragmentActivity paramFragmentActivity, List<Featuring> paramList)
  {
    paramList = new GroupRapMultiUserBottomSheet(paramFragmentActivity, paramList);
    paramList.show(paramFragmentActivity.f(), paramList.getTag());
  }
  
  public static void a(String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("endpoint", paramString1);
      localJSONObject.put("description", paramString2);
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
    a.a().a("API Error", localJSONObject);
  }
  
  public static void a(String paramString1, String paramString2, Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.commit();
  }
  
  public static void a(Rap paramRap, String paramString1, FragmentActivity paramFragmentActivity, String paramString2)
  {
    paramRap = new BottomSheetFragment(paramFragmentActivity, paramRap, a(paramFragmentActivity).c(), paramString2);
    paramRap.show(paramFragmentActivity.f(), paramRap.getTag());
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static String b(String paramString1, String paramString2, Context paramContext)
  {
    if (paramContext != null) {
      return PreferenceManager.getDefaultSharedPreferences(paramContext).getString(paramString1, paramString2);
    }
    return "Empty";
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        if (paramContext.getType() == 1) {
          return true;
        }
        if (paramContext.getType() == 0) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static void c(Context paramContext)
  {
    if (paramContext != null)
    {
      Activity localActivity = (Activity)paramContext;
      if (localActivity.getCurrentFocus() != null) {
        ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(localActivity.getCurrentFocus().getWindowToken(), 0);
      }
    }
  }
  
  public static String d(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return Telephony.Sms.getDefaultSmsPackage(paramContext);
    }
    Intent localIntent = new Intent("android.intent.action.VIEW").addCategory("android.intent.category.DEFAULT").setType("vnd.android-dir/mms-sms");
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    if ((paramContext != null) && (!paramContext.isEmpty())) {
      return ((ResolveInfo)paramContext.get(0)).activityInfo.packageName;
    }
    return null;
  }
  
  public void a(Context paramContext, String paramString)
  {
    new Handler(Looper.getMainLooper()).post(new -..Lambda.q.aNjrmMgX84rENPENdGtwV5XyZtI(paramContext, paramString));
  }
}
