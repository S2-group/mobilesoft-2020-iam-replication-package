package com.enjoymobi.xvideoplayer.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.a.a;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.RatingBar;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class a
{
  public static void a(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramContext.getString(2131558457));
    ((StringBuilder)localObject).append(paramContext.getPackageName());
    localObject = ((StringBuilder)localObject).toString();
    if (b(paramContext))
    {
      localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
      localIntent.setData(Uri.parse((String)localObject));
    }
    else
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(paramContext.getPackageName());
      localIntent.setData(Uri.parse(localStringBuilder.toString()));
    }
    if (localIntent.resolveActivity(paramContext.getPackageManager()) == null) {
      localIntent.setData(Uri.parse((String)localObject));
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    Object localObject1 = paramContext.getSharedPreferences("Pref", 0);
    if (paramBoolean)
    {
      com.enjoymobi.xvideoplayer.a.a.a(paramContext, "CLICK_SETTING_FIVESTARS", SettingActivity.class.getSimpleName());
    }
    else
    {
      if (!((SharedPreferences)localObject1).getBoolean("need_again", true)) {
        return;
      }
      long l1 = System.currentTimeMillis();
      long l2 = ((SharedPreferences)localObject1).getLong("show_time", 0L);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("previousTime:");
      ((StringBuilder)localObject2).append(l2);
      a.a.a.c.a(((StringBuilder)localObject2).toString());
      if (l1 - l2 < 86400000L)
      {
        localObject2 = new Date(l2);
        localObject3 = new Date();
        Calendar.getInstance().setTime((Date)localObject3);
        int i = Calendar.getInstance().get(1);
        int j = Calendar.getInstance().get(6);
        Calendar.getInstance().setTime((Date)localObject2);
        int k = Calendar.getInstance().get(1);
        int m = Calendar.getInstance().get(6);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("nowYear:");
        ((StringBuilder)localObject2).append(i);
        ((StringBuilder)localObject2).append(" preYear:");
        ((StringBuilder)localObject2).append(k);
        ((StringBuilder)localObject2).append(" nowDays:");
        ((StringBuilder)localObject2).append(j);
        ((StringBuilder)localObject2).append(" preDays:");
        ((StringBuilder)localObject2).append(m);
        a.a.a.c.a(((StringBuilder)localObject2).toString());
        if (i == k)
        {
          if (j - m >= 1) {}
        }
        else if (i < k) {
          return;
        }
      }
      com.enjoymobi.xvideoplayer.a.a.a(paramContext, "FIVE_STAR_SHOW", paramContext.getClass().getSimpleName());
    }
    Object localObject4 = View.inflate(paramContext, 2131361862, null);
    Object localObject3 = (RatingBar)((View)localObject4).findViewById(2131230865);
    Object localObject2 = new a.a(paramContext, 2131624100).b((View)localObject4).b();
    ((View)localObject4).findViewById(2131230775).setOnClickListener(new b((android.support.v7.app.a)localObject2));
    localObject4 = new AtomicBoolean(true);
    ((RatingBar)localObject3).setOnRatingBarChangeListener(new c(paramContext, (AtomicBoolean)localObject4, (SharedPreferences)localObject1, (android.support.v7.app.a)localObject2));
    ((android.support.v7.app.a)localObject2).setOnDismissListener(new d((AtomicBoolean)localObject4, paramBoolean, (SharedPreferences)localObject1));
    localObject1 = ((android.support.v7.app.a)localObject2).getWindow().getAttributes();
    ((WindowManager.LayoutParams)localObject1).width = ((int)TypedValue.applyDimension(1, 275.0F, paramContext.getResources().getDisplayMetrics()));
    ((android.support.v7.app.a)localObject2).getWindow().setAttributes((WindowManager.LayoutParams)localObject1);
    ((android.support.v7.app.a)localObject2).show();
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.equalsIgnoreCase("com.android.vending")) {
        return true;
      }
    }
    return false;
  }
}
