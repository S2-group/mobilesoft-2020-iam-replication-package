package com.smsBlocker.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.ads.AdView;
import com.smsBlocker.a.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ShowBlockedSMSFromNotification
  extends ListActivity
  implements Runnable
{
  Cursor a;
  a b;
  ProgressDialog c;
  private String d = "";
  private String e = "";
  private String f = "";
  private Button g;
  private TextView h;
  private AdView i;
  private Handler j = new ck(this);
  
  public ShowBlockedSMSFromNotification() {}
  
  private int a()
  {
    int k = 0;
    try
    {
      String str = a(this, "count.txt");
      if (!str.equals("")) {
        k = Integer.parseInt(str);
      }
      return k;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public static String a(long paramLong, String paramString)
  {
    paramString = new SimpleDateFormat(paramString);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return paramString.format(localCalendar.getTime());
  }
  
  private static String a(Context paramContext, String paramString)
  {
    paramString = new File(paramContext.getFilesDir().getAbsolutePath(), paramString);
    paramContext = new StringBuilder();
    for (;;)
    {
      try
      {
        paramString = new BufferedReader(new FileReader(paramString));
        str = paramString.readLine();
        if (str != null) {
          continue;
        }
      }
      catch (IOException paramString)
      {
        String str;
        continue;
      }
      return paramContext.toString();
      paramContext.append(str);
    }
  }
  
  private String b()
  {
    try
    {
      String str = ((TelephonyManager)getSystemService("phone")).getDeviceId();
      return str;
    }
    catch (Exception localException) {}
    return "error";
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903048);
    getWindow().setFeatureInt(7, 2130903076);
    getWindow().setFlags(1024, 1024);
    this.i = ((AdView)findViewById(2131361817));
    this.c = ProgressDialog.show(this, "", "Loading list. Please wait...", true);
    this.g = ((Button)findViewById(2131361843));
    this.g.setOnClickListener(new cd(this));
    paramBundle = getPackageManager().getInstalledApplications(128);
    int k = 0;
    if (k >= paramBundle.size())
    {
      k = 0;
      label126:
      if (k != 0)
      {
        this.i.a();
        this.i.setVisibility(8);
        this.g.setVisibility(8);
      }
      new Thread(this).start();
      paramBundle = a(this, "helpus.txt");
      if (paramBundle.equals("")) {
        break label328;
      }
    }
    label328:
    for (k = Integer.parseInt(paramBundle);; k = 0)
    {
      if (k == 1) {
        new cf(this.j).start();
      }
      getListView().setEmptyView(findViewById(2131361802));
      getListView().setCacheColorHint(0);
      setTitle("Blocked SMS");
      this.h = ((TextView)findViewById(2131361816));
      k = a();
      this.h.setText("Total SMS blocked- " + k);
      ((NotificationManager)getSystemService("notification")).cancel(1);
      return;
      if (((ApplicationInfo)paramBundle.get(k)).packageName.equals("com.smsBlockerUnlocker"))
      {
        k = 1;
        break label126;
      }
      k += 1;
      break;
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.a != null)
    {
      this.a.close();
      this.a = null;
    }
    if (this.b != null)
    {
      this.b.c();
      this.b = null;
    }
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    super.onListItemClick(paramListView, paramView, paramInt, paramLong);
    if (this.a.moveToPosition(paramInt))
    {
      this.e = this.a.getString(this.a.getColumnIndex("person"));
      this.d = this.a.getString(this.a.getColumnIndex("body"));
      this.f = this.a.getString(this.a.getColumnIndex("_id"));
    }
    new AlertDialog.Builder(this).setTitle(this.e).setMessage(this.d).setPositiveButton("Delete", new cb(this)).setNegativeButton("Move to Inbox", new ch(this)).create().show();
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void run()
  {
    try
    {
      Thread.sleep(1500L);
      this.b = new a(this);
      if (this.a != null) {
        this.a.close();
      }
      this.a = this.b.a();
      startManagingCursor(this.a);
      this.j.sendEmptyMessage(0);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
}
