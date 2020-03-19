package com.smsrobot.period.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.l;
import android.support.v4.app.n;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.smsrobot.lib.c.d;
import com.smsrobot.period.PeriodApp;
import java.util.Iterator;
import java.util.List;

public class a
  extends l
{
  public a() {}
  
  public static void a(n paramN)
  {
    try
    {
      PeriodApp localPeriodApp = PeriodApp.a();
      Object localObject = localPeriodApp.getSharedPreferences("apprater", 0);
      if (((SharedPreferences)localObject).getBoolean("dontshowagain", false)) {
        return;
      }
      SharedPreferences.Editor localEditor = ((SharedPreferences)localObject).edit();
      long l = ((SharedPreferences)localObject).getLong("launch_count", 0L) + 1L;
      localEditor.putLong("launch_count", l);
      Long localLong = Long.valueOf(((SharedPreferences)localObject).getLong("date_firstlaunch", 0L));
      localObject = localLong;
      if (localLong.longValue() == 0L)
      {
        localObject = Long.valueOf(System.currentTimeMillis());
        localEditor.putLong("date_firstlaunch", ((Long)localObject).longValue());
      }
      if ((l >= 18L) && (System.currentTimeMillis() >= ((Long)localObject).longValue() + 604800000L))
      {
        localEditor.putLong("launch_count", 0L);
        if (a(localPeriodApp)) {
          b(paramN);
        }
      }
      d.a(localEditor);
      return;
    }
    catch (Exception paramN) {}
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager().getInstalledPackages(8192).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  public static void b(Context paramContext)
  {
    try
    {
      if (a(paramContext))
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.smsrobot.period"));
        localIntent.addFlags(268435456);
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (Exception paramContext)
    {
      Log.e("AppRater", "Failed to show rate play page", paramContext);
    }
  }
  
  public static void b(n paramN)
  {
    try
    {
      new a().show(paramN.getSupportFragmentManager(), "AppRater");
      return;
    }
    catch (Exception paramN)
    {
      Log.e("AppRater", "Rate fragment dialog failed to open", paramN);
    }
  }
  
  public Dialog onCreateDialog(final Bundle paramBundle)
  {
    try
    {
      paramBundle = new Dialog(getActivity());
      paramBundle.setContentView(2130903213);
      paramBundle.setTitle(2131165261);
      paramBundle.setCancelable(true);
      setCancelable(true);
      final PeriodApp localPeriodApp = PeriodApp.a();
      ((Button)paramBundle.findViewById(2131558948)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.smsrobot.period"));
            paramAnonymousView.addFlags(268435456);
            localPeriodApp.startActivity(paramAnonymousView);
            paramAnonymousView = localPeriodApp.getSharedPreferences("apprater", 0).edit();
            if (paramAnonymousView != null)
            {
              paramAnonymousView.putBoolean("dontshowagain", true);
              d.a(paramAnonymousView);
            }
            paramBundle.dismiss();
            return;
          }
          catch (Exception paramAnonymousView)
          {
            Log.e("AppRater", "rate click", paramAnonymousView);
          }
        }
      });
      ((Button)paramBundle.findViewById(2131558949)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramBundle.dismiss();
        }
      });
      ((Button)paramBundle.findViewById(2131558950)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = localPeriodApp.getSharedPreferences("apprater", 0).edit();
          if (paramAnonymousView != null)
          {
            paramAnonymousView.putBoolean("dontshowagain", true);
            d.a(paramAnonymousView);
          }
          paramBundle.dismiss();
        }
      });
      return paramBundle;
    }
    catch (Exception paramBundle)
    {
      Log.e("AppRater", "Rate dialog failed to open", paramBundle);
    }
    return null;
  }
}
