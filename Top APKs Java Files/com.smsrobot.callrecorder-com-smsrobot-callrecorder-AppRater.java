package com.smsrobot.callrecorder;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.smsrobot.lib.util.SharedPreferencesCompat;
import java.util.Iterator;
import java.util.List;

public class AppRater
{
  private static final int DAYS_UNTIL_PROMPT = 7;
  private static final String GooglePlayStorePackageNameNew = "com.android.vending";
  private static final String GooglePlayStorePackageNameOld = "com.google.market";
  private static final int LAUNCHES_UNTIL_PROMPT = 10;
  
  public AppRater() {}
  
  public static void app_launched(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getSharedPreferences("apprater_call_x", 0);
      if (((SharedPreferences)localObject).getBoolean("dontshowagain_call_x", false)) {
        return;
      }
      SharedPreferences.Editor localEditor = ((SharedPreferences)localObject).edit();
      long l = ((SharedPreferences)localObject).getLong("launch_count_call_x", 0L) + 1L;
      localEditor.putLong("launch_count_call_x", l);
      Long localLong = Long.valueOf(((SharedPreferences)localObject).getLong("date_firstlaunch_call_x", 0L));
      localObject = localLong;
      if (localLong.longValue() == 0L)
      {
        localObject = Long.valueOf(System.currentTimeMillis());
        localEditor.putLong("date_firstlaunch_call_x", ((Long)localObject).longValue());
      }
      if ((l >= 10L) && (System.currentTimeMillis() >= ((Long)localObject).longValue() + 604800000L))
      {
        localEditor.putLong("launch_count_call_x", 0L);
        if (isMarketInstalled(paramContext)) {
          showRateDialog(paramContext, localEditor);
        }
      }
      SharedPreferencesCompat.apply(localEditor);
      return;
    }
    catch (Exception paramContext)
    {
      Log.e("callX", paramContext.toString());
    }
  }
  
  public static boolean isMarketInstalled(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager().getInstalledPackages(8192).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while ((!localPackageInfo.packageName.equals("com.google.market")) && (!localPackageInfo.packageName.equals("com.android.vending")));
    return true;
  }
  
  public static void showRateDialog(Context paramContext, final SharedPreferences.Editor paramEditor)
  {
    final Dialog localDialog = new Dialog(paramContext);
    localDialog.setContentView(2130903087);
    localDialog.setTitle(paramContext.getText(2131492903));
    ((Button)localDialog.findViewById(2131165421)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AppRater.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.smsrobot.callrecorder")));
        if (paramEditor != null)
        {
          paramEditor.putBoolean("dontshowagain_call_x", true);
          SharedPreferencesCompat.apply(paramEditor);
        }
        localDialog.dismiss();
      }
    });
    ((Button)localDialog.findViewById(2131165422)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AppRater.this.dismiss();
      }
    });
    ((Button)localDialog.findViewById(2131165423)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (AppRater.this != null)
        {
          AppRater.this.putBoolean("dontshowagain_call_x", true);
          SharedPreferencesCompat.apply(AppRater.this);
        }
        localDialog.dismiss();
      }
    });
    localDialog.show();
  }
}
