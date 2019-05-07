package com.smsBlocker.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.List;

public class MainUi
  extends TabActivity
{
  private static String[] b = { "Block list", "Trusted sender list", "Filter content", "Show blocked SMS", "Settings", "About" };
  private Button a;
  
  public MainUi() {}
  
  private static View a(Context paramContext, int paramInt)
  {
    paramContext = LayoutInflater.from(paramContext).inflate(2130903072, null);
    switch (paramInt)
    {
    default: 
      return null;
    case 1: 
      ((ImageView)paramContext.findViewById(2131361839)).setImageResource(2130837506);
      return paramContext;
    case 2: 
      ((ImageView)paramContext.findViewById(2131361839)).setImageResource(2130837504);
      return paramContext;
    case 3: 
      ((ImageView)paramContext.findViewById(2131361839)).setImageResource(2130837509);
      return paramContext;
    case 4: 
      ((ImageView)paramContext.findViewById(2131361839)).setImageResource(2130837533);
      return paramContext;
    }
    ((ImageView)paramContext.findViewById(2131361839)).setImageResource(2130837547);
    return paramContext;
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.fileList();
    int j = paramContext.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (paramContext[i].equals(paramString)) {
        return true;
      }
      i += 1;
    }
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    getWindow().setFormat(1);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903073);
    paramBundle = getPackageManager().getInstalledApplications(128);
    int i = 0;
    for (;;)
    {
      label43:
      Object localObject1;
      Object localObject2;
      Object localObject3;
      if (i >= paramBundle.size())
      {
        i = 0;
        if (i == 0) {
          break label737;
        }
        getWindow().setFeatureInt(7, 2130903075);
        getWindow().setFlags(1024, 1024);
        localObject1 = PreferenceManager.getDefaultSharedPreferences(this);
        ((SharedPreferences)localObject1).getBoolean("App_Autostart", true);
        paramBundle = (TabHost)findViewById(16908306);
        paramBundle.getTabWidget().setDividerDrawable(2130837560);
        localObject2 = new Intent().setClass(this, BlockList.class);
        localObject3 = a(paramBundle.getContext(), 1);
        paramBundle.addTab(paramBundle.newTabSpec("blocklist").setIndicator((View)localObject3).setContent((Intent)localObject2));
        localObject2 = new Intent().setClass(this, TrustedList.class);
        localObject3 = a(paramBundle.getContext(), 2);
        paramBundle.addTab(paramBundle.newTabSpec("trustedsender").setIndicator((View)localObject3).setContent((Intent)localObject2));
        localObject2 = new Intent().setClass(this, FilterContent.class);
        localObject3 = a(paramBundle.getContext(), 3);
        paramBundle.addTab(paramBundle.newTabSpec("filtercontent").setIndicator((View)localObject3).setContent((Intent)localObject2));
        localObject2 = new Intent().setClass(this, ShowBlockedSms.class);
        localObject3 = a(paramBundle.getContext(), 4);
        paramBundle.addTab(paramBundle.newTabSpec("blockedsms").setIndicator((View)localObject3).setContent((Intent)localObject2));
        localObject2 = new Intent().setClass(this, Settings.class);
        localObject3 = a(paramBundle.getContext(), 5);
        paramBundle.addTab(paramBundle.newTabSpec("settings").setIndicator((View)localObject3).setContent((Intent)localObject2));
        paramBundle.setCurrentTab(0);
        if (!a(this, "installdate.txt"))
        {
          localObject2 = Calendar.getInstance();
          ((Calendar)localObject2).setTimeInMillis(System.currentTimeMillis());
          ((Calendar)localObject2).set(11, 8);
          ((Calendar)localObject2).set(12, 0);
          localObject2 = String.valueOf(((Calendar)localObject2).getTimeInMillis());
        }
      }
      try
      {
        localObject3 = new OutputStreamWriter(openFileOutput("installdate.txt", 0));
        ((OutputStreamWriter)localObject3).write((String)localObject2);
        ((OutputStreamWriter)localObject3).flush();
        ((OutputStreamWriter)localObject3).close();
        try
        {
          localObject3 = new OutputStreamWriter(openFileOutput("weekdate.txt", 0));
          ((OutputStreamWriter)localObject3).write((String)localObject2);
          ((OutputStreamWriter)localObject3).flush();
          ((OutputStreamWriter)localObject3).close();
          new AlertDialog.Builder(this).setTitle("Important setting!").setMessage("Create a list of SMS senders whose ALL SMS are to be allowed (For ex. SMS from your bank etc.).\nNote that phonebook contacts are by default added in this list.").setPositiveButton(2131230723, new au(this)).create().show();
          long l;
          if (!a(this, "newinstalldate.txt"))
          {
            localObject2 = Calendar.getInstance();
            ((Calendar)localObject2).setTimeInMillis(System.currentTimeMillis());
            ((Calendar)localObject2).set(11, 8);
            ((Calendar)localObject2).set(12, 0);
            l = ((Calendar)localObject2).getTimeInMillis();
          }
          try
          {
            localObject2 = new OutputStreamWriter(openFileOutput("newinstalldate.txt", 0));
            ((OutputStreamWriter)localObject2).write(String.valueOf(l));
            ((OutputStreamWriter)localObject2).flush();
            ((OutputStreamWriter)localObject2).close();
            if (!a(this, "trialflag.txt")) {}
            try
            {
              localObject2 = new OutputStreamWriter(openFileOutput("trialflag.txt", 0));
              ((OutputStreamWriter)localObject2).write("0");
              ((OutputStreamWriter)localObject2).flush();
              ((OutputStreamWriter)localObject2).close();
              localObject1 = ((SharedPreferences)localObject1).edit();
              ((SharedPreferences.Editor)localObject1).putBoolean("App_Autostart", true);
              ((SharedPreferences.Editor)localObject1).putBoolean("AutoBlockSMS", true);
              ((SharedPreferences.Editor)localObject1).putBoolean("SMSNotification", true);
              ((SharedPreferences.Editor)localObject1).putString("setpassword", "");
              ((SharedPreferences.Editor)localObject1).putBoolean("autodelete", true);
              ((SharedPreferences.Editor)localObject1).commit();
              paramBundle.setCurrentTab(0);
              return;
              if (((ApplicationInfo)paramBundle.get(i)).packageName.equals("com.smsBlockerUnlocker"))
              {
                i = 1;
                break label43;
              }
              i += 1;
              continue;
              label737:
              getWindow().setFeatureInt(7, 2130903077);
              getWindow().setFlags(1024, 1024);
              this.a = ((Button)findViewById(2131361844));
              this.a.setVisibility(0);
              this.a.setOnClickListener(new bb(this));
            }
            catch (IOException localIOException1)
            {
              for (;;) {}
            }
          }
          catch (IOException localIOException2)
          {
            for (;;) {}
          }
        }
        catch (IOException localIOException3)
        {
          for (;;) {}
        }
      }
      catch (IOException localIOException4)
      {
        for (;;) {}
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}
