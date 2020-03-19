package com.ac_arcana.shared.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;

public class CommonTasks
{
  public CommonTasks() {}
  
  public static void openACArcanaApp(Context paramContext, String paramString1, String paramString2)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    List localList = localPackageManager.getInstalledApplications(0);
    Iterator localIterator = localList.iterator();
    if (!localIterator.hasNext()) {
      paramString1 = localList.iterator();
    }
    do
    {
      if (!paramString1.hasNext())
      {
        paramString1 = new Intent("android.intent.action.VIEW");
        paramString1.setData(Uri.parse("market://details?id=" + paramString2));
        paramContext.startActivity(paramString1);
        return;
        if (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString1)) {
          break;
        }
        paramContext.startActivity(localPackageManager.getLaunchIntentForPackage(paramString1));
        return;
      }
    } while (!((ApplicationInfo)paramString1.next()).packageName.equals(paramString2));
    paramContext.startActivity(localPackageManager.getLaunchIntentForPackage(paramString2));
  }
  
  public static void openMarket(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("market://details?id=" + paramString));
    paramContext.startActivity(localIntent);
  }
  
  public static void openMoreApps(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("market://search?q=pub:\"AC Arcana\""));
    paramContext.startActivity(localIntent);
  }
  
  public static void openRecentChanges(Context paramContext, final String[] paramArrayOfString)
  {
    ((Activity)paramContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        final Dialog localDialog = new Dialog(CommonTasks.this);
        View localView = ((Activity)CommonTasks.this).getLayoutInflater().inflate(R.layout.recent_changes, null);
        localDialog.setContentView(localView);
        localDialog.setCancelable(true);
        localDialog.setTitle("Recent Changes");
        LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(R.id.linearLayout1);
        int i = 0;
        for (;;)
        {
          if (i >= paramArrayOfString.length)
          {
            ((Button)localView.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                localDialog.dismiss();
              }
            });
            localDialog.show();
            return;
          }
          TextView localTextView = new TextView(CommonTasks.this);
          localTextView.setText(paramArrayOfString[i]);
          localLinearLayout.addView(localTextView);
          i += 1;
        }
      }
    });
  }
  
  public static void openURL(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
  }
}
