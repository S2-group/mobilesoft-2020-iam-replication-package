package com.jb.zcamera.wecloudpush;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.jb.zcamera.camera.bd;
import com.jb.zcamera.filterstore.imageloade.KPNetworkImageView;
import com.jb.zcamera.utils.ab;
import com.jb.zcamera.vip.subscription.SVipActivity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class c
{
  private static ExecutorService a = Executors.newFixedThreadPool(5);
  
  public static b a(String paramString)
  {
    b localB = new b();
    try
    {
      paramString = new JSONObject(paramString);
      localB.a(paramString.optLong("message_id"));
      localB.a(paramString.optString("title"));
      localB.b(paramString.optString("content"));
      localB.e(paramString.optString("action_type"));
      localB.f(paramString.optString("action_param"));
      localB.o(paramString.optString("icon"));
      localB.d(paramString.optString("banner"));
      localB.g(paramString.optString("black_list"));
      localB.c(paramString.optString("type"));
      localB.i(paramString.optString("d_type"));
      localB.j(paramString.optString("position"));
      localB.p(paramString.optString("is_vip"));
      localB.q(paramString.optString("sale_start_date"));
      localB.r(paramString.optString("sale_end_date"));
      localB.n(paramString.optString("button_left"));
      localB.k(paramString.optString("button_right"));
      localB.l(paramString.optString("action_type_right"));
      localB.m(paramString.optString("action_param_right"));
      return localB;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return localB;
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    Object localObject1 = "";
    for (;;)
    {
      try
      {
        if ("1".equals(paramString))
        {
          localObject1 = com.jb.zcamera.e.d.a("wecloud_push_main");
          com.jb.zcamera.e.d.a("wecloud_push_main", "");
          if (!TextUtils.isEmpty((CharSequence)localObject1)) {
            break;
          }
          return;
        }
        if ("2".equals(paramString))
        {
          localObject1 = com.jb.zcamera.e.d.a("wecloud_push_filter_store");
          com.jb.zcamera.e.d.a("wecloud_push_filter_store", "");
          continue;
        }
        if (!"3".equals(paramString)) {
          continue;
        }
      }
      catch (Throwable paramActivity)
      {
        paramActivity.printStackTrace();
        return;
      }
      localObject1 = com.jb.zcamera.e.d.a("wecloud_push_gallery");
      com.jb.zcamera.e.d.a("wecloud_push_gallery", "");
    }
    Object localObject2 = com.jb.zcamera.e.d.a("wecloud_push_message_id");
    com.jb.zcamera.background.pro.b.d((String)localObject2 + "wc_d_show_mid");
    paramString = a((String)localObject1);
    Object localObject3 = paramString.o();
    String str = paramString.l();
    localObject1 = new AlertDialog.Builder(paramActivity);
    ((AlertDialog.Builder)localObject1).setNegativeButton((CharSequence)localObject3, new e());
    ((AlertDialog.Builder)localObject1).setPositiveButton(str, new f((String)localObject2, paramString, paramActivity));
    if (!TextUtils.isEmpty(paramString.e()))
    {
      localObject3 = LayoutInflater.from(paramActivity).inflate(2130903332, null, false);
      ((AlertDialog.Builder)localObject1).setView((View)localObject3);
      paramActivity = (TextView)((View)localObject3).findViewById(2131297436);
      localObject2 = (KPNetworkImageView)((View)localObject3).findViewById(2131297437);
      localObject3 = (TextView)((View)localObject3).findViewById(2131297438);
      paramActivity.setText(paramString.b());
      ((TextView)localObject3).setText(paramString.c());
      ((KPNetworkImageView)localObject2).setDefaultImageResId(2130837833);
      ((KPNetworkImageView)localObject2).setErrorImageResId(2130837833);
      ((KPNetworkImageView)localObject2).setImageUrl(paramString.e());
    }
    for (;;)
    {
      paramActivity = ((AlertDialog.Builder)localObject1).create();
      paramActivity.setCancelable(true);
      paramActivity.setCanceledOnTouchOutside(false);
      paramActivity.show();
      return;
      ((AlertDialog.Builder)localObject1).setTitle(paramString.b());
      ((AlertDialog.Builder)localObject1).setMessage(paramString.c());
    }
  }
  
  public static void a(Context paramContext)
  {
    ab.e(paramContext);
  }
  
  public static void a(Context paramContext, b paramB)
  {
    if ((!TextUtils.isEmpty(paramB.r())) && (!TextUtils.isEmpty(paramB.s())))
    {
      bd.h(paramB.r());
      bd.i(paramB.s());
    }
    SVipActivity.startSVipActivity(paramContext, 7, true);
  }
  
  public static void a(b paramB, a paramA)
  {
    if ((paramB == null) || (paramA == null)) {
      return;
    }
    a.submit(new d(paramB, paramA));
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      paramString = paramString.split(",");
      int k = paramString.length;
      int i = 0;
      while (i < k)
      {
        Object localObject = paramString[i];
        int j = 0;
        while (j < paramContext.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.get(j);
          if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (localObject.equals(localPackageInfo.packageName))) {
            return true;
          }
          j += 1;
        }
        i += 1;
      }
    }
  }
  
  public static void b(Context paramContext) {}
}
