package com.ssbooks.bodlebook_lite_base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import kr.co.smartstudy.sspatcher.bt;
import kr.co.smartstudy.sspush.SSLocalPush;
import org.json.JSONException;
import org.json.JSONObject;

public class f
{
  public static long a = 0L;
  private static Application b = null;
  
  public f() {}
  
  public static int a(Context paramContext, int paramInt)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5D);
  }
  
  public static Application a()
  {
    return b;
  }
  
  public static SharedPreferences a(Context paramContext)
  {
    return paramContext.getSharedPreferences("bodlebook_lite", 0);
  }
  
  public static String a(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(r.localpush1));
    localArrayList.add(Integer.valueOf(r.localpush2));
    localArrayList.add(Integer.valueOf(r.localpush3));
    return b.getApplicationContext().getSharedPreferences(c.f, 0).getString("LocalPush_MSG" + paramInt, b.getString(((Integer)localArrayList.get(0)).intValue()));
  }
  
  public static void a(Application paramApplication)
  {
    b = paramApplication;
  }
  
  public static void a(View paramView, Context paramContext)
  {
    Object localObject = paramContext.getResources().getDisplayMetrics();
    double d1 = ((DisplayMetrics)localObject).widthPixels / 800.0D;
    double d2 = ((DisplayMetrics)localObject).heightPixels / 480.0D;
    localObject = (RelativeLayout.LayoutParams)paramView.getLayoutParams();
    if (((RelativeLayout.LayoutParams)localObject).leftMargin > 0) {
      ((RelativeLayout.LayoutParams)localObject).leftMargin = ((int)Math.round(((RelativeLayout.LayoutParams)localObject).leftMargin * d1));
    }
    if (((RelativeLayout.LayoutParams)localObject).topMargin > 0) {
      ((RelativeLayout.LayoutParams)localObject).topMargin = ((int)Math.round(((RelativeLayout.LayoutParams)localObject).topMargin * d2));
    }
    if (((RelativeLayout.LayoutParams)localObject).rightMargin > 0) {
      ((RelativeLayout.LayoutParams)localObject).rightMargin = ((int)Math.round(((RelativeLayout.LayoutParams)localObject).rightMargin * d1));
    }
    if (((RelativeLayout.LayoutParams)localObject).bottomMargin > 0) {
      ((RelativeLayout.LayoutParams)localObject).bottomMargin = ((int)Math.round(((RelativeLayout.LayoutParams)localObject).bottomMargin * d2));
    }
    if (((RelativeLayout.LayoutParams)localObject).width > 0) {
      ((RelativeLayout.LayoutParams)localObject).width = ((int)Math.round(d1 * ((RelativeLayout.LayoutParams)localObject).width));
    }
    if (((RelativeLayout.LayoutParams)localObject).height > 0) {
      ((RelativeLayout.LayoutParams)localObject).height = ((int)Math.round(((RelativeLayout.LayoutParams)localObject).height * d2));
    }
    paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int j = paramView.getChildCount();
      int i = 0;
      while (i < j)
      {
        a(paramView.getChildAt(i), paramContext);
        i += 1;
      }
    }
  }
  
  public static HashSet<String> b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      String str1 = localPackageInfo.packageName.toLowerCase();
      String str2 = localPackageInfo.versionName;
      int j = localPackageInfo.versionCode;
      localHashSet.add(str1);
      i += 1;
    }
    return localHashSet;
  }
  
  public static void b()
  {
    if (a == 0L)
    {
      a = System.currentTimeMillis();
      return;
    }
    long l1 = System.currentTimeMillis();
    long l2 = a;
    a = l1;
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("event", "tts");
      localJSONObject.put("time", bt.b());
      localJSONObject.put("stime", (l1 - l2) / 1000L);
      bt.a().d(localJSONObject.toString());
      return;
    }
    catch (JSONException localJSONException) {}
  }
  
  public static String c()
  {
    SharedPreferences.Editor localEditor = b.getApplicationContext().getSharedPreferences(c.f, 0).edit();
    String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    localEditor.putString("INSTALLDATE", str);
    localEditor.commit();
    g();
    return str;
  }
  
  public static void c(Context paramContext)
  {
    paramContext = paramContext.getResources();
    paramContext.getDisplayMetrics();
    Configuration localConfiguration = paramContext.getConfiguration();
    if ((c.k != null) && (!c.k.equals(""))) {
      localConfiguration.locale = new Locale(c.k);
    }
    paramContext.updateConfiguration(localConfiguration, b.getBaseContext().getResources().getDisplayMetrics());
  }
  
  public static String d()
  {
    String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    return b.getApplicationContext().getSharedPreferences(c.f, 0).getString("INSTALLDATE", str);
  }
  
  public static void e()
  {
    SharedPreferences.Editor localEditor = b.getApplicationContext().getSharedPreferences(c.f, 0).edit();
    localEditor.putBoolean("ISSETPUSH", true);
    localEditor.commit();
  }
  
  public static boolean f()
  {
    return b.getApplicationContext().getSharedPreferences(c.f, 0).getBoolean("ISSETPUSH", false);
  }
  
  public static void g()
  {
    ArrayList localArrayList1 = new ArrayList();
    localArrayList1.add(Integer.valueOf(r.localpush1));
    localArrayList1.add(Integer.valueOf(r.localpush2));
    localArrayList1.add(Integer.valueOf(r.localpush3));
    ArrayList localArrayList2 = new ArrayList();
    Random localRandom = new Random();
    SharedPreferences.Editor localEditor = b.getApplicationContext().getSharedPreferences(c.f, 0).edit();
    int i = 0;
    while (i < 12)
    {
      if (localArrayList2.size() < 1)
      {
        Log.d("SSLocalPush", "Reset" + i);
        localArrayList2.addAll(localArrayList1);
      }
      int j = localRandom.nextInt(localArrayList2.size());
      localEditor.putString("LocalPush_MSG" + i, b.getString(((Integer)localArrayList2.get(j)).intValue()));
      localArrayList2.remove(j);
      i += 1;
    }
    localEditor.commit();
    e();
  }
  
  public static void h()
  {
    Object localObject1 = Calendar.getInstance();
    Object localObject2 = new Random();
    int j = 2001;
    int i = 0;
    while (i < 3)
    {
      SSLocalPush.c(b, j);
      j += 1;
      i += 1;
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    localCalendar.set(1, localCalendar.get(1));
    localCalendar.set(2, 11);
    localCalendar.set(5, 1);
    localCalendar.set(11, 10);
    localCalendar.set(12, 0);
    localCalendar.add(12, ((Random)localObject2).nextInt(60) - 30);
    String str = b.getString(r.localpush1);
    if (localCalendar.getTime().compareTo(((Calendar)localObject1).getTime()) > 0) {
      SSLocalPush.a(b, 2001, localCalendar, str, "");
    }
    localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    localCalendar.set(1, localCalendar.get(1));
    localCalendar.set(2, 11);
    localCalendar.set(5, 25);
    localCalendar.set(11, 10);
    localCalendar.set(12, 0);
    localCalendar.add(12, ((Random)localObject2).nextInt(60) - 30);
    str = b.getString(r.localpush2);
    if (localCalendar.getTime().compareTo(((Calendar)localObject1).getTime()) > 0) {
      SSLocalPush.a(b, 2002, localCalendar, str, "");
    }
    localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    if ((localCalendar.get(2) == 0) && (localCalendar.get(5) == 1)) {
      localCalendar.set(1, localCalendar.get(1));
    }
    for (;;)
    {
      localCalendar.set(2, 0);
      localCalendar.set(5, 1);
      localCalendar.set(11, 10);
      localCalendar.set(12, 0);
      localCalendar.add(12, ((Random)localObject2).nextInt(60) - 30);
      localObject2 = b.getString(r.localpush3);
      if (localCalendar.getTime().compareTo(((Calendar)localObject1).getTime()) > 0) {
        SSLocalPush.a(b, 2003, localCalendar, (String)localObject2, "");
      }
      localObject1 = SSLocalPush.a(b).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (kr.co.smartstudy.sspush.f)((Iterator)localObject1).next();
        Log.i("SSLocalPush", "before " + ((kr.co.smartstudy.sspush.f)localObject2).a().toString());
      }
      localCalendar.set(1, localCalendar.get(1) + 1);
    }
  }
}
