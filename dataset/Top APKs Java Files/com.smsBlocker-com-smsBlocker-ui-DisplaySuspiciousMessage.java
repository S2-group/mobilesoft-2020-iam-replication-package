package com.smsBlocker.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.Toast;
import com.smsBlocker.logic.MainLogic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class DisplaySuspiciousMessage
  extends Activity
{
  private static Uri e;
  private static Uri f;
  private static Uri g;
  private static Uri h;
  private String a = "";
  private String b = "";
  private Notification c;
  private NotificationManager d;
  private AlertDialog i;
  
  static
  {
    Uri localUri = Uri.parse("content://mms-sms/");
    e = localUri;
    f = Uri.withAppendedPath(localUri, "threadID");
    localUri = Uri.parse("content://sms");
    g = localUri;
    h = Uri.withAppendedPath(localUri, "inbox");
  }
  
  public DisplaySuspiciousMessage() {}
  
  public static String a(Context paramContext, String paramString)
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
  
  public static final boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    int j = 0;
    for (;;)
    {
      if (j >= paramContext.size()) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(j)).packageName.equals("com.smsBlockerUnlocker")) {
        return true;
      }
      j += 1;
    }
  }
  
  private static int b(Context paramContext)
  {
    paramContext = paramContext.getContentResolver().query(h, new String[] { "_id", "body" }, "read=0", null, "date DESC");
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        j = paramContext.getCount();
        paramContext.close();
        int k = j;
        if (j == 0)
        {
          k = j;
          if (0L > 0L) {
            k = 1;
          }
        }
        return k;
      }
      finally
      {
        paramContext.close();
      }
      int j = 0;
    }
  }
  
  private static long b(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return 0L;
    }
    Uri.Builder localBuilder = f.buildUpon();
    localBuilder.appendQueryParameter("recipient", paramString);
    paramContext = paramContext.getContentResolver().query(localBuilder.build(), new String[] { "_id" }, null, null, null);
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        if (paramContext.moveToFirst())
        {
          l = paramContext.getLong(0);
          return l;
        }
      }
      finally
      {
        paramContext.close();
      }
      long l = 0L;
      continue;
      l = 0L;
    }
  }
  
  private static Intent b()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setType("vnd.android-dir/mms-sms");
    localIntent.setFlags(872415232);
    return localIntent;
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    paramContext = paramContext.fileList();
    int k = paramContext.length;
    int j = 0;
    for (;;)
    {
      if (j >= k) {
        return false;
      }
      if (paramContext[j].equals(paramString)) {
        return true;
      }
      j += 1;
    }
  }
  
  public final void a()
  {
    int n = 0;
    try
    {
      String str = a(this, "count.txt");
      Object localObject = a(this, "dailycount.txt");
      if (!str.equals(""))
      {
        j = Integer.parseInt(str);
        k = n;
        m = j;
        try
        {
          if (!((String)localObject).equals(""))
          {
            k = Integer.parseInt((String)localObject);
            m = j;
          }
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            k = n;
            m = j;
          }
        }
        localObject = m + 1;
        str = k + 1;
        try
        {
          OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(openFileOutput("count.txt", 0));
          localOutputStreamWriter.write((String)localObject);
          localOutputStreamWriter.flush();
          localOutputStreamWriter.close();
          try
          {
            localObject = new OutputStreamWriter(openFileOutput("dailycount.txt", 0));
            ((OutputStreamWriter)localObject).write(str);
            ((OutputStreamWriter)localObject).flush();
            ((OutputStreamWriter)localObject).close();
            return;
          }
          catch (IOException localIOException1) {}
        }
        catch (IOException localIOException2)
        {
          for (;;) {}
        }
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        int m = 0;
        int k = n;
        continue;
        int j = 0;
      }
    }
  }
  
  public final void a(String paramString)
  {
    try
    {
      if (c(this, "blocklist.txt")) {}
      for (Object localObject = openFileOutput("blocklist.txt", 32768);; localObject = openFileOutput("blocklist.txt", 0))
      {
        localObject = new OutputStreamWriter((OutputStream)localObject);
        ((OutputStreamWriter)localObject).write(paramString);
        ((OutputStreamWriter)localObject).flush();
        ((OutputStreamWriter)localObject).close();
        return;
      }
      return;
    }
    catch (IOException paramString) {}
  }
  
  public final void b(String paramString)
  {
    try
    {
      if (c(this, "trustedlist.txt")) {}
      for (Object localObject = openFileOutput("trustedlist.txt", 32768);; localObject = openFileOutput("trustedlist.txt", 0))
      {
        localObject = new OutputStreamWriter((OutputStream)localObject);
        ((OutputStreamWriter)localObject).write(paramString);
        ((OutputStreamWriter)localObject).flush();
        ((OutputStreamWriter)localObject).close();
        return;
      }
      return;
    }
    catch (IOException paramString) {}
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    if ((!this.a.equals("")) && (!this.b.equals("")))
    {
      String str = new MainLogic(this).a(this.b);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("address", this.b);
      localContentValues.put("date", String.valueOf(System.currentTimeMillis()));
      localContentValues.put("person", str);
      localContentValues.put("read", Integer.valueOf(0));
      localContentValues.put("status", Integer.valueOf(-1));
      localContentValues.put("type", Integer.valueOf(1));
      localContentValues.put("body", this.a);
      getContentResolver().insert(Uri.parse("content://sms/inbox"), localContentValues);
      this.a = "";
      this.b = "";
      Toast.makeText(this, "Message moved to inbox.", 0).show();
      this.i.dismiss();
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    this.a = paramBundle.getString("message");
    this.b = paramBundle.getString("number");
    this.i = new AlertDialog.Builder(this).setIcon(2130837515).setTitle(this.b).setMessage(this.a).setPositiveButton(2131230724, new bc(this)).setNeutralButton(2131230726, new ba(this)).setNegativeButton(2131230727, new ay(this)).create();
    this.i.show();
    if ((a(this, "trialflag.txt").equals("0")) || (a(this)))
    {
      paramBundle = PreferenceManager.getDefaultSharedPreferences(this).getString("suspicioustone", "Silent");
      if (!paramBundle.equals("Silent")) {
        MediaPlayer.create(this, Uri.parse(paramBundle)).start();
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    String str2;
    Object localObject;
    String str1;
    int j;
    if ((!this.a.equals("")) && (!this.b.equals("")))
    {
      str2 = new MainLogic(this).a(this.b);
      localObject = new ContentValues();
      ((ContentValues)localObject).put("address", this.b);
      ((ContentValues)localObject).put("date", String.valueOf(System.currentTimeMillis()));
      ((ContentValues)localObject).put("person", str2);
      ((ContentValues)localObject).put("read", Integer.valueOf(0));
      ((ContentValues)localObject).put("status", Integer.valueOf(-1));
      ((ContentValues)localObject).put("type", Integer.valueOf(1));
      ((ContentValues)localObject).put("body", this.a);
      getContentResolver().insert(Uri.parse("content://sms/inbox"), (ContentValues)localObject);
      str1 = "";
      localObject = new SpannableString(str2 + ": " + this.a);
      ((SpannableString)localObject).setSpan(new StyleSpan(1), 0, str2.length(), 33);
      this.d = ((NotificationManager)getSystemService("notification"));
      this.c = new Notification(2130837525, (CharSequence)localObject, System.currentTimeMillis());
      localObject = null;
      j = b(this);
      if (j <= 1) {
        break label419;
      }
      localObject = b();
      str2 = getString(2131230728);
      str1 = getString(2131230729, new Object[] { Integer.valueOf(j) });
    }
    for (;;)
    {
      localObject = PendingIntent.getActivity(this, 0, (Intent)localObject, 0);
      this.c.setLatestEventInfo(this, str2, str1, (PendingIntent)localObject);
      localObject = this.c;
      ((Notification)localObject).defaults |= 0xFFFFFFFF;
      localObject = this.c;
      ((Notification)localObject).flags |= 0x10;
      this.d.notify(2, this.c);
      paramIntent = paramIntent.getExtras();
      this.a = paramIntent.getString("message");
      this.b = paramIntent.getString("number");
      this.i.setTitle(this.b);
      this.i.setMessage(this.a);
      return;
      label419:
      if (j == 1)
      {
        long l = b(this, this.b);
        localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).setFlags(335544320);
        if (l > 0L) {
          ((Intent)localObject).setData(Uri.withAppendedPath(f, String.valueOf(l)));
        }
        for (;;)
        {
          str1 = this.a;
          break;
          localObject = b();
        }
      }
      str2 = "";
    }
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    if ((!this.a.equals("")) && (!this.b.equals("")))
    {
      String str = new MainLogic(this).a(this.b);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("address", this.b);
      localContentValues.put("date", String.valueOf(System.currentTimeMillis()));
      localContentValues.put("person", str);
      localContentValues.put("read", Integer.valueOf(0));
      localContentValues.put("status", Integer.valueOf(-1));
      localContentValues.put("type", Integer.valueOf(1));
      localContentValues.put("body", this.a);
      getContentResolver().insert(Uri.parse("content://sms/inbox"), localContentValues);
      this.a = "";
      this.b = "";
      this.i.dismiss();
      Toast.makeText(this, "Message moved to inbox.", 0).show();
      finish();
    }
  }
}
