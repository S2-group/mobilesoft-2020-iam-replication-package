package com.david.android.languageswitch.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.david.android.languageswitch.c.a;
import com.david.android.languageswitch.model.Paragraph;
import com.david.android.languageswitch.model.Story;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class b
{
  public static int a(Context paramContext)
  {
    int i = 168;
    if (paramContext != null)
    {
      paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { 2130772069 });
      i = (int)paramContext.getDimension(0, 0.0F);
      paramContext.recycle();
    }
    return i;
  }
  
  public static Story a(String paramString)
  {
    paramString = Story.find(Story.class, "title_Id = ?", new String[] { paramString });
    if (!paramString.isEmpty()) {
      return (Story)paramString.get(0);
    }
    return null;
  }
  
  public static String a(long paramLong)
  {
    long l1 = paramLong / 1000L;
    paramLong = l1 / 3600L;
    long l2 = l1 % 3600L;
    l1 = l2 / 60L;
    l2 %= 60L;
    if (paramLong > 0L) {
      return String.format("%d:%02d:%02d", new Object[] { Long.valueOf(paramLong), Long.valueOf(l1), Long.valueOf(l2) });
    }
    return String.format("%d:%02d", new Object[] { Long.valueOf(l1), Long.valueOf(l2) });
  }
  
  public static String a(Context paramContext, d.d paramD)
  {
    paramContext = new a(paramContext).ao();
    if (z.a(new String[] { paramContext }))
    {
      paramContext = paramContext.split(String.valueOf(':'));
      if (paramContext.length > 1) {
        return paramContext[1];
      }
    }
    return null;
  }
  
  public static List<String> a()
  {
    HashSet localHashSet = new HashSet();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = Story.listAll(Story.class).iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((Story)localIterator1.next()).getLanguagesSupported().iterator();
      while (localIterator2.hasNext()) {
        localHashSet.add((String)localIterator2.next());
      }
    }
    localArrayList.addAll(localHashSet);
    return localArrayList;
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    Toast.makeText(paramActivity, paramString, 1).show();
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    if (paramContext != null) {
      Toast.makeText(paramContext, paramInt, 0).show();
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      Toast.makeText(paramContext, paramString, 0).show();
    }
  }
  
  public static boolean a(Context paramContext, Story paramStory)
  {
    return (paramStory != null) && (paramStory.isPaid()) && (!paramStory.isPaymentMade()) && (!paramStory.hasAtLeastTwoDownloadedLanguages(paramContext));
  }
  
  public static boolean a(a paramA)
  {
    return (paramA.G()) || (b(paramA)) || (paramA.aB());
  }
  
  public static boolean a(Object... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      if (paramVarArgs[i] == null) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static Paragraph b(String paramString)
  {
    paramString = Paragraph.find(Paragraph.class, "title = ?", new String[] { paramString });
    if (!paramString.isEmpty()) {
      return (Paragraph)paramString.get(0);
    }
    return null;
  }
  
  public static void b(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      paramContext = Toast.makeText(paramContext, paramString, 0);
      paramContext.setGravity(16, 0, 0);
      paramContext.show();
    }
  }
  
  public static boolean b(Activity paramActivity, String paramString)
  {
    paramActivity = paramActivity.getPackageManager().getInstalledApplications(128).iterator();
    while (paramActivity.hasNext()) {
      if (((ApplicationInfo)paramActivity.next()).packageName.contains(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }
  
  public static boolean b(Context paramContext, Story paramStory)
  {
    return (paramStory != null) && (paramStory.isPaid()) && (!paramStory.isPaymentMade());
  }
  
  public static boolean b(a paramA)
  {
    return (z.a(new String[] { paramA.s(), paramA.t() })) && (paramA.t().contains(paramA.s()));
  }
  
  public static boolean c(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131755009);
  }
  
  public static boolean c(a paramA)
  {
    return (paramA.E()) || ((!paramA.D()) && (paramA.v() < paramA.R())) || (d(paramA)) || (paramA.aA());
  }
  
  public static boolean d(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration();
    if (Build.VERSION.SDK_INT >= 17) {
      return paramContext.getLayoutDirection() == 1;
    }
    return false;
  }
  
  public static boolean d(a paramA)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!paramA.F())
    {
      bool1 = bool2;
      if (z.a(new String[] { paramA.s(), paramA.u() }))
      {
        bool1 = bool2;
        if (!paramA.u().contains(paramA.s())) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public static String e(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      return paramContext.versionName;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
  }
  
  public static boolean e(a paramA)
  {
    return (paramA.E()) || (paramA.G());
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      String str = paramContext.getSimCountryIso();
      if ((str != null) && (str.length() == 2)) {
        return str.toLowerCase(Locale.US);
      }
      if (paramContext.getPhoneType() != 2)
      {
        paramContext = paramContext.getNetworkCountryIso();
        if ((paramContext != null) && (paramContext.length() == 2))
        {
          paramContext = paramContext.toLowerCase(Locale.US);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean f(a paramA)
  {
    return (paramA.E()) || (d(paramA));
  }
  
  public static boolean g(Context paramContext)
  {
    paramContext = new a(paramContext);
    return (!paramContext.q()) && ((!paramContext.am()) || (paramContext.an()));
  }
  
  public static boolean g(a paramA)
  {
    return (a(paramA)) && (c(paramA));
  }
  
  public static String h(a paramA)
  {
    if ((paramA.ac() == 0L) && (paramA.ab() > 0L))
    {
      paramA.d(System.currentTimeMillis());
      try
      {
        long l1 = paramA.ac();
        long l2 = paramA.ab();
        l1 = TimeUnit.MILLISECONDS.toMinutes(l1 - l2);
        paramA = l1 + " minutes ";
        return paramA;
      }
      catch (Exception paramA) {}
    }
    return "";
  }
  
  public static boolean h(Context paramContext)
  {
    return z.a(new String[] { new a(paramContext).ao() });
  }
  
  public static void i(a paramA)
  {
    if ((paramA.as() != 0L) && (paramA.ar()) && (System.currentTimeMillis() - paramA.as() > 1800000L))
    {
      paramA.x(false);
      paramA.f(0L);
    }
  }
  
  public static boolean i(Context paramContext)
  {
    return z.a(new String[] { new a(paramContext).aq() });
  }
  
  public static d.d j(Context paramContext)
  {
    int i = 0;
    paramContext = new a(paramContext).ao();
    if (z.a(new String[] { paramContext }))
    {
      paramContext = paramContext.split(String.valueOf(':'))[0];
      switch (paramContext.hashCode())
      {
      default: 
        label72:
        i = -1;
      }
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return null;
        if (!paramContext.equals("fb")) {
          break label72;
        }
        continue;
        if (!paramContext.equals("go")) {
          break label72;
        }
        i = 1;
      }
    }
    return d.d.a;
    return d.d.b;
  }
  
  public static String k(Context paramContext)
  {
    return paramContext.getString(2131427659) + " " + new a(paramContext).aw();
  }
}
