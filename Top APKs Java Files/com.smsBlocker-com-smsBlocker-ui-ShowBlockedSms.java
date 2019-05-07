package com.smsBlocker.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.smsBlocker.a.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ShowBlockedSms
  extends ListActivity
{
  Cursor a;
  a b;
  private String c = "";
  private String d = "";
  private String e = "";
  private TextView f;
  
  public ShowBlockedSms() {}
  
  private int a()
  {
    int i = 0;
    try
    {
      String str = a(this, "count.txt");
      if (!str.equals("")) {
        i = Integer.parseInt(str);
      }
      return i;
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
  
  private static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(i)).packageName.equals("com.smsBlockerUnlocker")) {
        return true;
      }
      i += 1;
    }
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903053);
    this.b = new a(this);
    if (this.a != null) {
      this.a.close();
    }
    this.a = this.b.a();
    startManagingCursor(this.a);
    paramBundle = new SimpleCursorAdapter(this, 2130903068, this.a, new String[] { "person", "body", "date" }, new int[] { 2131361832, 2131361835, 2131361836 });
    paramBundle.setViewBinder(new by(this));
    getListView().setEmptyView(findViewById(2131361802));
    getListView().setCacheColorHint(0);
    setListAdapter(paramBundle);
    setTitle("Blocked SMS");
    this.f = ((TextView)findViewById(2131361816));
    int i = a();
    this.f.setText("Total SMS blocked: " + i);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131296259, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.a != null)
    {
      this.a.close();
      this.a = null;
    }
    this.b.c();
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    super.onListItemClick(paramListView, paramView, paramInt, paramLong);
    if (this.a.moveToPosition(paramInt))
    {
      this.d = this.a.getString(this.a.getColumnIndex("person"));
      this.c = this.a.getString(this.a.getColumnIndex("body"));
      this.e = this.a.getString(this.a.getColumnIndex("_id"));
    }
    new AlertDialog.Builder(this).setTitle(this.d).setMessage(this.c).setPositiveButton("Delete", new cj(this)).setNegativeButton("Move to Inbox", new ci(this)).create().show();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131361846: 
      new AlertDialog.Builder(this).setTitle("Help").setMessage("Click on the message to read, delete, or move to inbox.\nIn Premium version- Logs show latest 100 records. All older records are deleted automatically, one by one, making space for new records.").setPositiveButton(2131230723, new bx(this)).create().show();
      return true;
    }
    if ((a(this, "trialflag.txt").equals("0")) || (a(this)))
    {
      if (new a(this).e() == 0L)
      {
        Toast.makeText(this, "No Logs found.", 0).show();
        return true;
      }
      paramMenuItem = new AlertDialog.Builder(this);
      paramMenuItem.setMessage("Are you sure you want to clear Logs?").setCancelable(false).setPositiveButton("Yes", new cg(this)).setNegativeButton("No", new ce(this));
      paramMenuItem.create().show();
      return true;
    }
    paramMenuItem = new AlertDialog.Builder(this);
    paramMenuItem.setMessage("This is a premium feature.").setPositiveButton("Get Premium version", new cc(this)).setNegativeButton("Cancel", new ca(this));
    paramMenuItem.create().show();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.a != null)
    {
      this.a.close();
      this.a = null;
    }
    this.b.c();
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.a != null) {
      this.a.close();
    }
    this.a = this.b.a();
    startManagingCursor(this.a);
    SimpleCursorAdapter localSimpleCursorAdapter = new SimpleCursorAdapter(this, 2130903068, this.a, new String[] { "person", "body", "date" }, new int[] { 2131361832, 2131361835, 2131361836 });
    localSimpleCursorAdapter.setViewBinder(new cl(this));
    setListAdapter(localSimpleCursorAdapter);
    int i = a();
    this.f.setText("Total SMS blocked- " + i);
  }
  
  protected void onStop()
  {
    super.onStop();
  }
}
