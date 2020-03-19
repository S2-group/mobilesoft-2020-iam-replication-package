package com.smsBlocker.logic;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.List;

public class Ba
  extends Service
{
  Handler a = new b(this);
  
  public Ba() {}
  
  public static final boolean a(Context paramContext)
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
  
  public static boolean a(Context paramContext, String paramString)
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
  
  public static String b(Context paramContext, String paramString)
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
  
  public final void a()
  {
    try
    {
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(openFileOutput("counterdate.txt", 0));
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTimeInMillis(System.currentTimeMillis());
      localCalendar.set(11, 0);
      localCalendar.set(12, 1);
      localOutputStreamWriter.write(String.valueOf(localCalendar.getTimeInMillis()));
      localOutputStreamWriter.flush();
      localOutputStreamWriter.close();
      return;
    }
    catch (IOException localIOException) {}
  }
  
  public final void a(String paramString)
  {
    try
    {
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(openFileOutput("afterpaidcount.txt", 0));
      localOutputStreamWriter.write(paramString);
      localOutputStreamWriter.flush();
      localOutputStreamWriter.close();
      return;
    }
    catch (IOException paramString) {}
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    super.onStart(paramIntent, paramInt);
    if (paramIntent == null)
    {
      stopSelf();
      return;
    }
    paramIntent = paramIntent.getExtras();
    String str = paramIntent.getString("address");
    new a(this, paramIntent.getString("message"), str).start();
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }
}
