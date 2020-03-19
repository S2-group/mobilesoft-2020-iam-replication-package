package com.appsladder.tuneupmaster.activities;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDataObserver.Stub;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.com.appsladder.tuneupmaster.utilities.Adds;
import com.com.appsladder.tuneupmaster.utilities.ConsentCheck;
import com.com.appsladder.tuneupmaster.utilities.DatabaseHelper;
import com.com.appsladder.tuneupmaster.utilities.GetPro;
import com.com.appsladder.tuneupmaster.utilities.Utility;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends AppCompatActivity
  implements ActivityCompat.OnRequestPermissionsResultCallback
{
  private static final int CLEAR_APP_CACHE = 99;
  private static Adds adds;
  private static Boolean fullExecution = Boolean.valueOf(false);
  String[] customItems = { "Memory TuneUp", "Cache TuneUp", "Sensor Calibration", "Display Calibration", "Android OS TuneUp", "Battery TuneUp" };
  private DatabaseHelper db;
  
  public MainActivity() {}
  
  private void afterMemoryClean()
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Tuning your device memory"), Html.fromHtml("Almost done with tuning your device memory..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          if (!MainActivity.fullExecution.booleanValue())
          {
            MainActivity.this.showSuccessDialog();
            return;
          }
          MainActivity.adds.showAdd(3000L);
          MainActivity.this.showSuccessDialogForActivity(Integer.valueOf(0));
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 4000L);
  }
  
  private void androidOSTuneUp()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Performing Android tune up for ");
    localStringBuilder.append(Utility.getAndroidVersionAndName());
    androidOSTuneUp1(localStringBuilder.toString());
  }
  
  private void androidOSTuneUp1(final String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Performing Android tune up for ");
    ((StringBuilder)localObject).append(Utility.getAndroidVersionAndName());
    localObject = ProgressDialog.show(this, Html.fromHtml(((StringBuilder)localObject).toString()), Html.fromHtml("Getting all the components that can be fine tuned..."), true);
    ((ProgressDialog)localObject).show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          this.val$dialog1.dismiss();
          MainActivity.this.androidOSTuneUp2(paramString);
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 5000L);
  }
  
  private void androidOSTuneUp2(final String paramString)
  {
    paramString = ProgressDialog.show(this, Html.fromHtml("Performing tuning of your android operating system"), Html.fromHtml(paramString), true);
    paramString.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          paramString.dismiss();
          MainActivity.this.performMemoryClean();
          MainActivity.this.androidOSTuneUp3();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 12000L);
  }
  
  private void androidOSTuneUp3()
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Performing android OS tune up"), Html.fromHtml("Almost done with tuning your device android system..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          if (!MainActivity.fullExecution.booleanValue())
          {
            MainActivity.this.showSuccessDialog();
            return;
          }
          MainActivity.adds.showAdd(1000L);
          MainActivity.this.showSuccessDialogForActivity(Integer.valueOf(4));
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 2000L);
  }
  
  private void batteryTuneUp()
  {
    batteryTuneUp1("Trying to optimise your battery usage by all applications installed in your device");
  }
  
  private void batteryTuneUp1(final String paramString)
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Performing battery usage optimisation"), Html.fromHtml("Getting current battery usage statistics..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          MainActivity.this.batteryTuneUp2(paramString);
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 9000L);
  }
  
  private void batteryTuneUp2(final String paramString)
  {
    Object localObject = (BatteryManager)getSystemService("batterymanager");
    try
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        int i = ((BatteryManager)localObject).getIntProperty(4);
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Pending battery percentage: ");
        ((StringBuilder)localObject).append(i);
        localObject = ProgressDialog.show(this, Html.fromHtml(((StringBuilder)localObject).toString()), Html.fromHtml(paramString), true);
        ((ProgressDialog)localObject).show();
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              this.val$dialog1.dismiss();
              MainActivity.this.performMemoryClean();
              MainActivity.this.batteryTuneUp3();
              return;
            }
            catch (Exception localException)
            {
              for (;;) {}
            }
          }
        }, 3000L);
        return;
      }
      localObject = ProgressDialog.show(this, Html.fromHtml("Performing battery usage optimisation"), Html.fromHtml(paramString), true);
      ((ProgressDialog)localObject).show();
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          try
          {
            this.val$dialog1.dismiss();
            MainActivity.this.batteryTuneUp3();
            return;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
        }
      }, 3000L);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    paramString = ProgressDialog.show(this, Html.fromHtml("Performing battery usage optimisation"), Html.fromHtml(paramString), true);
    paramString.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          paramString.dismiss();
          MainActivity.this.performMemoryClean();
          MainActivity.this.batteryTuneUp3();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 3000L);
  }
  
  private void batteryTuneUp3()
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Performing battery usage optimisation"), Html.fromHtml("Almost done with increasing your battery lifetime..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          if (!MainActivity.fullExecution.booleanValue()) {
            MainActivity.this.showSuccessDialog();
          } else {
            MainActivity.this.showSuccessDialog();
          }
          Toast.makeText(MainActivity.this.getBaseContext(), "Successfully optimised your batter usage", 0).show();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 2000L);
  }
  
  private void blockNotifications()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(Html.fromHtml("<font color=\"white\">Notifications Blocker<br></font>"));
    localBuilder.setMessage(Html.fromHtml("<font color=\"#D1D0D0\"> To block unnecessary notifications and to fix delay in delivery of important notifications  download Rapid Notifications tool from Google play.<br>Please click on OK to download Rapid Notifications from Google Play Store</font>"));
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.rapidNotifications();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private void cacheTuneUp()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(((ApplicationInfo)localIterator.next()).packageName);
      localStringBuilder.append("<br>");
    }
    cacheTuneUpDialog1(localStringBuilder.toString());
  }
  
  private void cacheTuneUpDialog1(final String paramString)
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Cleaning up your device cache"), Html.fromHtml("Getting all the applications installed..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          MainActivity.this.cacheTuneUpDialog2(paramString);
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 2000L);
  }
  
  private void cacheTuneUpDialog2(final String paramString)
  {
    paramString = ProgressDialog.show(this, Html.fromHtml("Cleaning cache of all the below packages"), Html.fromHtml(paramString), true);
    paramString.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          paramString.dismiss();
          MainActivity.this.cacheTuneUpDialog3();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 10000L);
  }
  
  private void cacheTuneUpDialog3()
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Cleaning up your device cache"), Html.fromHtml("Almost done with cleaning up your device cache..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          MainActivity.this.checkPermission();
          if (!MainActivity.fullExecution.booleanValue())
          {
            MainActivity.this.showSuccessDialog();
            return;
          }
          MainActivity.this.showSuccessDialogForActivity(Integer.valueOf(1));
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 5000L);
  }
  
  private void checkPermission()
  {
    if (ContextCompat.checkSelfPermission(this, "android.permission.CLEAR_APP_CACHE") != 0) {
      ActivityCompat.requestPermissions(this, new String[] { "android.permission.CLEAR_APP_CACHE" }, 99);
    }
  }
  
  private void clearCache()
  {
    CachePackageDataObserver localCachePackageDataObserver = new CachePackageDataObserver(null);
    PackageManager localPackageManager = getPackageManager();
    Class localClass = Long.TYPE;
    try
    {
      localPackageManager.getClass().getMethod("freeStorageAndNotify", new Class[] { localClass, IPackageDataObserver.class }).invoke(localPackageManager, new Object[] { Long.valueOf(Long.MAX_VALUE), localCachePackageDataObserver });
      return;
    }
    catch (Exception localException) {}
  }
  
  private void displayCalibration()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Calibrating touch screen of your device: ");
    localStringBuilder.append(Utility.getDeviceName());
    displayCalibration1(localStringBuilder.toString());
  }
  
  private void displayCalibration1(final String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(Utility.getDeviceName());
    ((StringBuilder)localObject).append("<br>");
    ((StringBuilder)localObject).append(Utility.getAndroidVersionAndName());
    localObject = ProgressDialog.show(this, Html.fromHtml(((StringBuilder)localObject).toString()), Html.fromHtml("Getting optimum value for calibrating your device display..."), true);
    ((ProgressDialog)localObject).show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          this.val$dialog1.dismiss();
          MainActivity.this.displayCalibration2(paramString);
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 4000L);
  }
  
  private void displayCalibration2(final String paramString)
  {
    paramString = ProgressDialog.show(this, Html.fromHtml("Performing calibration of your device touch screen"), Html.fromHtml(paramString), true);
    paramString.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          paramString.dismiss();
          MainActivity.this.performMemoryClean();
          MainActivity.this.displayCalibration3();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 9000L);
  }
  
  private void displayCalibration3()
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Performing display calibration"), Html.fromHtml("Almost done with performing display calibration..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          if (!MainActivity.fullExecution.booleanValue())
          {
            MainActivity.this.showSuccessDialog();
            return;
          }
          MainActivity.this.showSuccessDialogForActivity(Integer.valueOf(3));
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 4000L);
  }
  
  private void memoryTuneUp()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(((ApplicationInfo)localIterator.next()).packageName);
      localStringBuilder.append("<br>");
    }
    performMemoryCleanDiagalog1(localStringBuilder.toString());
  }
  
  private void netTuneUp()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(Html.fromHtml("<font color=\"white\">Notifications Blocker<br></font>"));
    localBuilder.setMessage(Html.fromHtml("<font color=\"#D1D0D0\"> To spped up data connection and to monitor data usage  download Network TuneUp from Google play.<br>Please click on OK to download Network TuneUp from Google Play Store</font>"));
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.openNetworkTuneUp();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private void openNetworkTuneUp()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.appsladder.nettuneup")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appsladder.nettuneup")));
    return;
    Toast.makeText(getApplicationContext(), "Not able to open since Google play not found!!!!", 0).show();
  }
  
  private void openRapidCleaner()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.appsladder.rapidcleaner")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appsladder.rapidcleaner")));
    return;
    Toast.makeText(getApplicationContext(), "Not able to open since Google play not found!!!!", 0).show();
  }
  
  private void performMemoryClean()
  {
    CachePackageDataObserver localCachePackageDataObserver = new CachePackageDataObserver(null);
    PackageManager localPackageManager = getPackageManager();
    Method[] arrayOfMethod = localPackageManager.getClass().getDeclaredMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = arrayOfMethod[i];
      if (localMethod.getName().equals("freeStorageAndNotify")) {
        try
        {
          localMethod.invoke(localPackageManager, new Object[] { Long.valueOf(-1073741824L), localCachePackageDataObserver });
          return;
        }
        catch (Exception localException)
        {
          Log.d("error", localException.getMessage());
          return;
        }
      }
      i += 1;
    }
  }
  
  private void performMemoryCleanDiagalog1(final String paramString)
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Tuning your device memory"), Html.fromHtml("Getting all the applications installed..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          MainActivity.this.performMemoryCleanDiagalog2(paramString);
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 3000L);
  }
  
  private void performMemoryCleanDiagalog2(final String paramString)
  {
    paramString = ProgressDialog.show(this, Html.fromHtml("Cleaning junk memory of all the below packages"), Html.fromHtml(paramString), true);
    paramString.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          paramString.dismiss();
          MainActivity.this.performMemoryClean();
          MainActivity.this.afterMemoryClean();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 10000L);
  }
  
  private void proApp()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.appsladder.tuneupmaster.pro")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appsladder.tuneupmaster.pro")));
    return;
    Toast.makeText(getApplicationContext(), "Not able to open since Google play not found!!!!", 0).show();
  }
  
  private void rapidNotifications()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.appsladder.rapidnotifications.pro")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appsladder.rapidnotifications.pro")));
    return;
    Toast.makeText(getApplicationContext(), "Not able to open since Google play not found!!!!", 0).show();
  }
  
  private void rateApp()
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(getApplication().getPackageName());
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(getApplication().getPackageName());
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
    return;
    Toast.makeText(getApplicationContext(), "Not able to open since Google play not found!!!!", 0).show();
  }
  
  private void sensorCalibration()
  {
    Object localObject = (SensorManager)getSystemService("sensor");
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((SensorManager)localObject).getSensorList(-1).iterator();
    while (((Iterator)localObject).hasNext()) {
      localStringBuilder.append(((Sensor)((Iterator)localObject).next()).getName());
    }
    sensorCalibration1(localStringBuilder.toString());
  }
  
  private void sensorCalibration1(final String paramString)
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Performing sensor calibration"), Html.fromHtml("Getting all the sensors in your device..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          MainActivity.this.sensorCalibration2(paramString);
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 3000L);
  }
  
  private void sensorCalibration2(final String paramString)
  {
    LayoutInflater.from(this).inflate(2131361835, null);
    paramString = ProgressDialog.show(this, Html.fromHtml("Performing calibration of all the below sensors"), Html.fromHtml(paramString), true);
    paramString.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          paramString.dismiss();
          MainActivity.this.performMemoryClean();
          MainActivity.this.sensorCalibration3();
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 13000L);
  }
  
  private void sensorCalibration3()
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, Html.fromHtml("Performing sensor calibration"), Html.fromHtml("Almost done with performing sensor calibration..."), true);
    localProgressDialog.show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          localProgressDialog.dismiss();
          if (!MainActivity.fullExecution.booleanValue())
          {
            MainActivity.this.showSuccessDialog();
            return;
          }
          MainActivity.this.showSuccessDialogForActivity(Integer.valueOf(2));
          return;
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }, 4000L);
  }
  
  private void showCleanerPopup()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(Html.fromHtml("<font color=\"white\">Advanced Memory Clean<br></font>"));
    localBuilder.setMessage(Html.fromHtml("<font color=\"#D1D0D0\"> To clean more junk and make more space we need to download another program which is equipped with advanced algorithms - Rapid Cleaner.<br>Please click on OK to download Rapid Cleaner from Google Play Store</font>"));
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.openRapidCleaner();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private void showSuccessDialog()
  {
    fullExecution = Boolean.valueOf(false);
    Object localObject = new AlertDialog.Builder(this);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<font color=\"#D1D0D0\">");
    localStringBuilder.append(Utility.getDeviceName());
    localStringBuilder.append("<br>");
    localStringBuilder.append(Utility.getAndroidVersionAndName());
    localStringBuilder.append("</font>");
    ((AlertDialog.Builder)localObject).setTitle(Html.fromHtml(localStringBuilder.toString()));
    ((AlertDialog.Builder)localObject).setMessage(Html.fromHtml("<font color=\"#D1D0D0\"><b>SUCCESS <br><br></b></font><b><font color=\"white\"> Please restart the device for changes to take effect.</font></b><br><br><font color=\"#D1D0D0\"> If you are satisfied with the performance of this application, then please rate this app with 5 stars and share.<br> Please let us know your feedback at appsladder.feedback@gmail.com</font>"));
    ((AlertDialog.Builder)localObject).setCancelable(true);
    ((AlertDialog.Builder)localObject).setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    ((AlertDialog.Builder)localObject).create().show();
    localObject = getApplicationContext().getSharedPreferences("proVersion", 0).edit();
    ((SharedPreferences.Editor)localObject).putBoolean("pro", false);
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  private void showSuccessDialogForActivity(final Integer paramInteger)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(Html.fromHtml(this.customItems[paramInteger.intValue()]));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.customItems[paramInteger.intValue()]);
    localStringBuilder.append(" successfully completed. Press OK to continue.");
    localBuilder.setMessage(Html.fromHtml(localStringBuilder.toString()));
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramInteger.intValue() == 0)
        {
          MainActivity.this.cacheTuneUp();
          return;
        }
        if (paramInteger.intValue() == 1)
        {
          MainActivity.this.sensorCalibration();
          return;
        }
        if (paramInteger.intValue() == 2)
        {
          MainActivity.this.displayCalibration();
          return;
        }
        if (paramInteger.intValue() == 3)
        {
          MainActivity.this.androidOSTuneUp();
          return;
        }
        if (paramInteger.intValue() == 4)
        {
          MainActivity.this.batteryTuneUp();
          return;
        }
        MainActivity.this.showSuccessDialog();
      }
    });
    localBuilder.create().show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361819);
    this.db = new DatabaseHelper(this);
    setSupportActionBar((Toolbar)findViewById(2131230906));
    GetPro.app_launched(this);
    adds = new Adds(getApplicationContext());
    paramBundle = new ArrayAdapter(this, 2131361836, this.customItems);
    ListView localListView = (ListView)findViewById(2131230819);
    localListView.setDivider(new ColorDrawable(-8552577));
    localListView.setDividerHeight(1);
    localListView.setSelector(2131165305);
    localListView.setAdapter(paramBundle);
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == 0)
        {
          MainActivity.this.memoryTuneUp();
          return;
        }
        if (paramAnonymousInt == 1)
        {
          MainActivity.this.cacheTuneUp();
          return;
        }
        if (paramAnonymousInt == 2)
        {
          MainActivity.this.sensorCalibration();
          return;
        }
        if (paramAnonymousInt == 3)
        {
          MainActivity.this.displayCalibration();
          return;
        }
        if (paramAnonymousInt == 4)
        {
          MainActivity.this.androidOSTuneUp();
          return;
        }
        if (paramAnonymousInt == 5)
        {
          MainActivity.this.batteryTuneUp();
          return;
        }
        if (paramAnonymousInt == 6) {
          MainActivity.this.showCleanerPopup();
        }
      }
    });
    paramBundle = (Button)findViewById(2131230838);
    paramBundle.setText(Html.fromHtml("<b>   Quick Tuneup</b><br><font color=\"#7D7F7F\" size=\"2dp\">     Use this option to tune and calibrate all components of your device</font>"));
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.access$702(Boolean.valueOf(true));
        MainActivity.this.memoryTuneUp();
      }
    });
    paramBundle = (Button)findViewById(2131230828);
    paramBundle.setText(Html.fromHtml("<b>   Advanced Memory Cleanup</b><br><font color=\"#7D7F7F\" size=\"2dp\">     Use this option to free up more space in your device using advanced algorithms</font>"));
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.showCleanerPopup();
      }
    });
    paramBundle = (Button)findViewById(2131230829);
    paramBundle.setText(Html.fromHtml("<b>   Network TuneUp</b><br><font color=\"#7D7F7F\" size=\"2dp\">     Application to speed up network and also monitor mobile data and wifi usage</font>"));
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.netTuneUp();
      }
    });
    paramBundle = (Button)findViewById(2131230830);
    paramBundle.setText(Html.fromHtml("<b>   Notifications Blocker</b><br><font color=\"#7D7F7F\" size=\"2dp\">     Tool to block notifications and also to fix delay in delivery of notifications</font>"));
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.blockNotifications();
      }
    });
    ConsentCheck.updateUserConsent("tuneup", this, this.db);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131427328, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 2131230841)
    {
      rateApp();
      return true;
    }
    if (i == 2131230835)
    {
      proApp();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if (paramInt != 99) {
      return;
    }
    if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0)) {
      clearCache();
    }
  }
  
  private class CachePackageDataObserver
    extends IPackageDataObserver.Stub
  {
    private CachePackageDataObserver() {}
    
    public void onRemoveCompleted(String paramString, boolean paramBoolean) {}
  }
}
