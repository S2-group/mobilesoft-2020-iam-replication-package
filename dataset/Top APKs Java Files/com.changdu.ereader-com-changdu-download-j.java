package com.changdu.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.changdu.ApplicationInit;
import com.changdu.changdulib.e.c.b;
import com.changdu.common.bb;
import com.changdu.common.bk;
import com.changdu.common.widget.dialog.j.a;
import com.changdu.e.i;
import com.changdu.u.a.g;
import com.changdu.util.n;
import com.changdu.util.z;
import com.changdu.zone.ndaction.t.b;
import java.io.File;
import java.util.List;
import java.util.Map;

public final class j
{
  private static ai a = new ai(ApplicationInit.h);
  private static bj b = new bj(ApplicationInit.h);
  private static final String c = ".apk";
  
  private j() {}
  
  public static ai a()
  {
    return (ai)a(i.c.a);
  }
  
  public static i.b a(i.c paramC)
  {
    if (p.a[paramC.ordinal()] != 1) {
      return a;
    }
    return b;
  }
  
  public static String a(int paramInt)
  {
    return ApplicationInit.h.getString(paramInt);
  }
  
  public static String a(DownloadData paramDownloadData, Map<String, List<String>> paramMap)
  {
    Object localObject = "";
    if (paramDownloadData != null)
    {
      int i = paramDownloadData.r();
      String str1 = paramDownloadData.v();
      String str2 = paramDownloadData.w();
      localObject = paramDownloadData.p();
      if (paramMap != null)
      {
        paramDownloadData = (List)paramMap.get("filename");
        if ((paramDownloadData != null) && (paramDownloadData.size() > 0))
        {
          paramMap = (String)paramDownloadData.get(0);
          if (!TextUtils.isEmpty(paramMap))
          {
            paramDownloadData = "";
            int j = paramMap.lastIndexOf('.');
            if (j > 1) {
              paramDownloadData = paramMap.substring(j);
            }
            paramMap = paramDownloadData;
            if (i == 5)
            {
              paramMap = paramDownloadData;
              if (!TextUtils.isEmpty(paramDownloadData))
              {
                paramMap = paramDownloadData;
                if (".zip".equals(paramDownloadData.toLowerCase())) {
                  paramMap = ".ndz";
                }
              }
            }
            paramDownloadData = (DownloadData)localObject;
            if (a((String)localObject, paramMap))
            {
              paramDownloadData = new StringBuilder();
              paramDownloadData.append((String)localObject);
              paramDownloadData.append(paramMap);
              paramDownloadData = paramDownloadData.toString();
            }
            return paramDownloadData;
          }
        }
      }
      if (!TextUtils.isEmpty(str2))
      {
        paramMap = a(str2, i);
        paramDownloadData = (DownloadData)localObject;
        if (a((String)localObject, paramMap))
        {
          paramDownloadData = new StringBuilder();
          paramDownloadData.append((String)localObject);
          paramDownloadData.append(paramMap);
          paramDownloadData = paramDownloadData.toString();
        }
      }
      else
      {
        paramDownloadData = (DownloadData)localObject;
        if (!TextUtils.isEmpty(str1))
        {
          paramMap = a(str1, i);
          paramDownloadData = (DownloadData)localObject;
          if (a((String)localObject, paramMap))
          {
            paramDownloadData = new StringBuilder();
            paramDownloadData.append((String)localObject);
            paramDownloadData.append(paramMap);
            return paramDownloadData.toString();
          }
        }
      }
      localObject = paramDownloadData;
    }
    return localObject;
  }
  
  public static String a(String paramString, int paramInt)
  {
    String str2 = "";
    String str1 = str2;
    if (!TextUtils.isEmpty(paramString))
    {
      str1 = paramString.toLowerCase();
      if (str1.contains(".ndb")) {
        paramString = ".ndb";
      }
      for (;;)
      {
        break;
        if (str1.contains(".zip"))
        {
          paramString = ".zip";
        }
        else if (str1.contains(".ttf"))
        {
          paramString = ".ttf";
        }
        else if (str1.contains(".txt"))
        {
          paramString = ".txt";
        }
        else if (str1.contains(".apk"))
        {
          paramString = ".apk";
        }
        else if (str1.contains(".otf"))
        {
          paramString = ".otf";
        }
        else if (paramInt == 12)
        {
          paramString = ".zip";
        }
        else
        {
          int i = str1.lastIndexOf('?');
          if (i > 1)
          {
            str1 = str1.substring(0, i);
            i = str1.lastIndexOf('.');
            paramString = str2;
            if (i > 1) {
              paramString = str1.substring(i);
            }
          }
          else
          {
            i = str1.lastIndexOf('.');
            paramString = str2;
            if (i > 1) {
              paramString = str1.substring(i);
            }
          }
        }
      }
      str1 = paramString;
      if (paramInt == 5)
      {
        str1 = paramString;
        if (!TextUtils.isEmpty(paramString))
        {
          str1 = paramString;
          if (".zip".equals(paramString.toLowerCase())) {
            str1 = ".ndz";
          }
        }
      }
    }
    return str1;
  }
  
  public static void a(Activity paramActivity, t.b paramB, boolean paramBoolean)
  {
    a(paramActivity, paramB, paramBoolean, false);
  }
  
  public static void a(Activity paramActivity, t.b paramB, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramActivity, paramB, paramBoolean1, paramBoolean2, "");
  }
  
  public static void a(Activity paramActivity, t.b paramB, boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    if (!b.k())
    {
      bk.a(2131558702);
      return;
    }
    if ((paramActivity != null) && (paramB != null))
    {
      String str = paramB.d("software_url");
      if (TextUtils.isEmpty(str))
      {
        bk.a(2131560122);
        return;
      }
      Object localObject4 = new DownloadData();
      ((DownloadData)localObject4).i(paramB.d("software_name"));
      ((DownloadData)localObject4).j(str);
      Object localObject3 = paramB.d("file_extension");
      boolean bool = n.a((String)localObject3);
      int j = 1;
      if (bool) {
        i = Integer.parseInt((String)localObject3);
      } else {
        i = 1;
      }
      ((DownloadData)localObject4).h(i);
      if (((DownloadData)localObject4).r() == 1) {
        ((DownloadData)localObject4).g(paramActivity.getString(2131559465));
      }
      ((DownloadData)localObject4).e(1);
      Object localObject2 = ((DownloadData)localObject4).k();
      Object localObject1 = localObject2;
      if (!TextUtils.isEmpty(str))
      {
        i = ((String)localObject2).lastIndexOf('.');
        int k = ((String)localObject2).lastIndexOf(File.separator);
        if (i >= 0)
        {
          localObject1 = localObject2;
          if (i >= k) {}
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append(".apk");
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
      Object localObject5 = new StringBuilder();
      localObject2 = localObject3;
      if (com.changdu.changdulib.e.k.a((String)localObject3)) {
        localObject2 = Integer.valueOf(1);
      }
      ((StringBuilder)localObject5).append(localObject2);
      ((StringBuilder)localObject5).append("_");
      ((StringBuilder)localObject5).append(paramB.d("software_name"));
      ((DownloadData)localObject4).h(((StringBuilder)localObject5).toString());
      int i = com.changdu.e.h.d().e(((DownloadData)localObject4).r(), ((DownloadData)localObject4).t(), ((DownloadData)localObject4).p());
      if (((((DownloadData)localObject4).d() != 0) || (i != 0)) && (i != 1) && (i != 3))
      {
        if (i == 2)
        {
          bk.a(2131559552);
          if (paramBoolean1)
          {
            a(paramActivity, (String)localObject1);
            return;
          }
          paramB = paramActivity.getPackageManager();
          localObject3 = paramB.getPackageArchiveInfo((String)localObject1, 0);
          if (localObject3 != null)
          {
            paramString = paramB.getApplicationLabel(((PackageInfo)localObject3).applicationInfo).toString();
            localObject2 = ((PackageInfo)localObject3).packageName;
            localObject3 = ((PackageInfo)localObject3).versionName;
            localObject4 = paramB.getInstalledPackages(0);
            i = 0;
            while (i < ((List)localObject4).size())
            {
              Object localObject6 = (PackageInfo)((List)localObject4).get(i);
              if (!((PackageInfo)localObject6).applicationInfo.sourceDir.startsWith("/system/app"))
              {
                str = paramB.getApplicationLabel(((PackageInfo)localObject6).applicationInfo).toString();
                localObject5 = ((PackageInfo)localObject6).packageName;
                localObject6 = ((PackageInfo)localObject6).versionName;
                if ((paramString.equals(str)) && (((String)localObject2).equals(localObject5)) && (((String)localObject3).equals(localObject6)))
                {
                  new j.a(paramActivity).a(2131560255).b(paramActivity.getString(2131559901, new Object[] { paramString })).a(2131558691, new l(paramActivity, (String)localObject1)).b(2131558561, new k()).b();
                  i = j;
                  break label609;
                }
              }
              i += 1;
            }
          }
          i = 0;
          label609:
          if (i == 0) {
            a(paramActivity, (String)localObject1);
          }
        }
        else
        {
          if ((!ApplicationInit.l) && (!paramBoolean2))
          {
            b(str, paramString);
            paramString = new ScrollView(paramActivity);
            localObject1 = new TextView(paramActivity);
            ((TextView)localObject1).setTextColor(ApplicationInit.h.getResources().getColor(2131034237));
            ((TextView)localObject1).setTextSize(20.0F);
            ((TextView)localObject1).setPadding(10, 10, 10, 10);
            if ((paramB.g() != null) && (paramB.g().equals("autoupgrade"))) {
              ((TextView)localObject1).setText(2131560370);
            } else {
              ((TextView)localObject1).setText(2131559128);
            }
            ((TextView)localObject1).setScrollContainer(true);
            paramString.addView((View)localObject1);
            paramB = new g(paramActivity, 2131559102, paramString, 2131558561, 2131559448);
            paramB.setCanceledOnTouchOutside(true);
            paramB.show();
            paramB.a(new m(paramB, (DownloadData)localObject4, paramActivity));
            return;
          }
          b(str, paramString);
          ApplicationInit.l = false;
          if (paramBoolean2)
          {
            paramB = new o((DownloadData)localObject4);
            bb.a().a(paramActivity.getApplicationContext(), DownloadManagerService.class, null, paramB, 1, true);
            return;
          }
          paramB = new Intent(paramActivity, DownloadPanel.class);
          paramB.putExtra("download_data", (Parcelable)localObject4);
          paramActivity.startActivity(paramB);
        }
      }
      else {
        bk.a(2131559553);
      }
    }
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    z.b(paramActivity, paramString);
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString2))
    {
      int i = paramString1.lastIndexOf('.');
      int j = paramString1.lastIndexOf(File.separator);
      if ((i < 0) || (i < j))
      {
        File localFile = new File(paramString1);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString1);
        localStringBuilder.append(paramString2);
        paramString1 = new File(localStringBuilder.toString());
        if (localFile.exists()) {
          return localFile.renameTo(paramString1);
        }
      }
    }
    return false;
  }
  
  public static bj b()
  {
    return (bj)a(i.c.b);
  }
  
  public static String b(String paramString, int paramInt)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!"null".equals(paramString)))
    {
      paramString = ApplicationInit.h.getString(2131560002, new Object[] { paramString });
    }
    else
    {
      String str = DownloadData.d(paramInt);
      paramString = str;
      if ("download".equals(str)) {
        paramString = "";
      }
    }
    if ((paramInt != 13) && (paramInt != 9)) {
      return ApplicationInit.h.getString(2131558746, new Object[] { paramString, DownloadData.d(paramInt) });
    }
    return ApplicationInit.h.getString(2131560047, new Object[] { paramString });
  }
  
  private static void b(String paramString1, String paramString2)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString1))
      {
        i localI = com.changdu.e.h.j();
        localI.b(paramString1);
        localI.a(paramString1, paramString2);
        return;
      }
    }
    catch (Exception paramString1)
    {
      com.changdu.changdulib.e.h.e(paramString1);
    }
  }
  
  public static boolean c()
  {
    return ((WifiManager)ApplicationInit.h.getSystemService("wifi")).isWifiEnabled();
  }
  
  public static boolean d()
  {
    Object localObject = (ConnectivityManager)ApplicationInit.h.getSystemService("connectivity");
    boolean bool2 = false;
    try
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      boolean bool1 = bool2;
      if (localObject != null)
      {
        boolean bool3 = ((NetworkInfo)localObject).isConnected();
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static boolean e()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)ApplicationInit.h.getSystemService("connectivity")).getNetworkInfo(1);
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static boolean f()
  {
    Object localObject = (ConnectivityManager)ApplicationInit.h.getSystemService("connectivity");
    boolean bool2 = false;
    localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
    boolean bool1 = bool2;
    if (localObject != null)
    {
      bool1 = bool2;
      if (((NetworkInfo)localObject).isConnected()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static String g()
  {
    String str3 = "";
    String str2;
    try
    {
      str2 = ((WifiManager)ApplicationInit.h.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      try
      {
        if (!TextUtils.isEmpty(str2))
        {
          String str1 = str2.replaceAll("[:-]", "");
          return str1;
        }
        return str2;
      }
      catch (Exception localException1) {}
      localException2.printStackTrace();
    }
    catch (Exception localException2)
    {
      str2 = str3;
    }
    return str2;
  }
  
  public static boolean h()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)ApplicationInit.h.getSystemService("connectivity")).getActiveNetworkInfo();
    boolean bool2 = true;
    if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 0))
    {
      int i = localNetworkInfo.getSubtype();
      bool1 = bool2;
      if (i == 1) {
        return bool1;
      }
      bool1 = bool2;
      if (i == 4) {
        return bool1;
      }
      if (i == 2) {
        return true;
      }
    }
    boolean bool1 = false;
    return bool1;
  }
}
