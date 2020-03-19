package com.smsBlocker.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.RingtonePreference;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Settings
  extends PreferenceActivity
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  ProgressDialog a;
  private EditTextPreference b;
  private CheckBoxPreference c;
  private CheckBoxPreference d;
  private CheckBoxPreference e;
  private CheckBoxPreference f;
  private EditTextPreference g;
  private RingtonePreference h;
  private Preference i;
  private Preference j;
  private SharedPreferences k;
  private Handler l = new aw(this);
  private Handler m = new aq(this);
  
  public Settings() {}
  
  private String a()
  {
    try
    {
      String str = ((TelephonyManager)getSystemService("phone")).getDeviceId();
      return str;
    }
    catch (Exception localException) {}
    return "error";
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
    int n = 0;
    for (;;)
    {
      if (n >= paramContext.size()) {
        return false;
      }
      if (((ApplicationInfo)paramContext.get(n)).packageName.equals("com.smsBlockerUnlocker")) {
        return true;
      }
      n += 1;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2130903062);
    this.k = PreferenceManager.getDefaultSharedPreferences(this);
    this.k.registerOnSharedPreferenceChangeListener(this);
    this.b = ((EditTextPreference)getPreferenceScreen().findPreference("country_code_dialog"));
    this.b.setSummary(this.k.getString("country_code_dialog", "91"));
    this.c = ((CheckBoxPreference)getPreferenceScreen().findPreference("App_Autostart"));
    this.d = ((CheckBoxPreference)getPreferenceScreen().findPreference("AutoBlockSMS"));
    this.e = ((CheckBoxPreference)getPreferenceScreen().findPreference("SMSNotification"));
    this.f = ((CheckBoxPreference)getPreferenceScreen().findPreference("autodelete"));
    this.h = ((RingtonePreference)getPreferenceScreen().findPreference("suspicioustone"));
    char c1;
    int n;
    if (!this.k.getString("suspicioustone", "Silent").equals("Silent"))
    {
      paramBundle = RingtoneManager.getRingtone(this, Uri.parse(this.k.getString("suspicioustone", "Silent")));
      if (paramBundle != null)
      {
        paramBundle = paramBundle.getTitle(this);
        this.h.setSummary(paramBundle);
        this.g = ((EditTextPreference)getPreferenceScreen().findPreference("setpassword"));
        this.j = getPreferenceScreen().findPreference("preBackup");
        this.j.setOnPreferenceClickListener(new as(this));
        getWindow().setBackgroundDrawableResource(2130837505);
        getListView().setBackgroundColor(0);
        getListView().setCacheColorHint(0);
        setTitleColor(-1);
        char[] arrayOfChar = a().toLowerCase().toCharArray();
        c1 = arrayOfChar[2];
        if (!Character.isDigit(c1)) {
          break label901;
        }
        n = "0123456789".indexOf(c1);
        paramBundle = "" + "3456789012".substring(n, n + 1);
        label374:
        c1 = arrayOfChar[4];
        if (!Character.isDigit(c1)) {
          break label991;
        }
        n = "0123456789".indexOf(c1);
        paramBundle = paramBundle + "3456789012".substring(n, n + 1);
        label438:
        c1 = arrayOfChar[7];
        if (!Character.isDigit(c1)) {
          break label1077;
        }
        n = "0123456789".indexOf(c1);
        paramBundle = paramBundle + "3456789012".substring(n, n + 1);
        label503:
        c1 = arrayOfChar[8];
        if (!Character.isDigit(c1)) {
          break label1163;
        }
        n = "0123456789".indexOf(c1);
        paramBundle = paramBundle + "3456789012".substring(n, n + 1);
        label568:
        c1 = arrayOfChar[9];
        if (!Character.isDigit(c1)) {
          break label1249;
        }
        n = "0123456789".indexOf(c1);
        paramBundle = paramBundle + "3456789012".substring(n, n + 1);
        label633:
        c1 = arrayOfChar[10];
        if (!Character.isDigit(c1)) {
          break label1335;
        }
        n = "0123456789".indexOf(c1);
        paramBundle = paramBundle + "3456789012".substring(n, n + 1);
        label698:
        c1 = arrayOfChar[11];
        char c2 = arrayOfChar[12];
        paramBundle = paramBundle + c1 + c2;
        this.i = getPreferenceScreen().findPreference("my_productid");
        this.i.setTitle(paramBundle);
        paramBundle = a(this, "trialflag.txt");
        if ((!paramBundle.equals("0")) && (!a(this))) {
          break label1421;
        }
        this.e.setEnabled(true);
        this.e.setLayoutResource(2130903064);
        this.j.setEnabled(true);
        this.j.setLayoutResource(2130903064);
        this.f.setEnabled(true);
        this.f.setLayoutResource(2130903064);
        this.h.setEnabled(true);
        this.h.setLayoutResource(2130903064);
        this.g.setEnabled(true);
        this.g.setLayoutResource(2130903064);
      }
    }
    for (;;)
    {
      if (!a(this)) {
        break label1514;
      }
      this.i.setSummary("Premium version");
      return;
      this.h.setSummary("Silent");
      break;
      label901:
      if (Character.isLetter(c1))
      {
        n = "abcdefghijklmnopqrstuvwxyz".indexOf(c1);
        paramBundle = "" + "defghijklmnopqrstuvwxyzabc".substring(n, n + 1);
        break label374;
      }
      paramBundle = "" + "a";
      break label374;
      label991:
      if (Character.isLetter(c1))
      {
        n = "abcdefghijklmnopqrstuvwxyz".indexOf(c1);
        paramBundle = paramBundle + "defghijklmnopqrstuvwxyzabc".substring(n, n + 1);
        break label438;
      }
      paramBundle = paramBundle + "a";
      break label438;
      label1077:
      if (Character.isLetter(c1))
      {
        n = "abcdefghijklmnopqrstuvwxyz".indexOf(c1);
        paramBundle = paramBundle + "defghijklmnopqrstuvwxyzabc".substring(n, n + 1);
        break label503;
      }
      paramBundle = paramBundle + "a";
      break label503;
      label1163:
      if (Character.isLetter(c1))
      {
        n = "abcdefghijklmnopqrstuvwxyz".indexOf(c1);
        paramBundle = paramBundle + "defghijklmnopqrstuvwxyzabc".substring(n, n + 1);
        break label568;
      }
      paramBundle = paramBundle + "a";
      break label568;
      label1249:
      if (Character.isLetter(c1))
      {
        n = "abcdefghijklmnopqrstuvwxyz".indexOf(c1);
        paramBundle = paramBundle + "defghijklmnopqrstuvwxyzabc".substring(n, n + 1);
        break label633;
      }
      paramBundle = paramBundle + "a";
      break label633;
      label1335:
      if (Character.isLetter(c1))
      {
        n = "abcdefghijklmnopqrstuvwxyz".indexOf(c1);
        paramBundle = paramBundle + "defghijklmnopqrstuvwxyzabc".substring(n, n + 1);
        break label698;
      }
      paramBundle = paramBundle + "a";
      break label698;
      label1421:
      this.e.setEnabled(false);
      this.e.setLayoutResource(2130903065);
      this.j.setEnabled(false);
      this.j.setLayoutResource(2130903065);
      this.f.setEnabled(false);
      this.f.setLayoutResource(2130903065);
      this.h.setEnabled(false);
      this.h.setLayoutResource(2130903065);
      this.g.setEnabled(false);
      this.g.setLayoutResource(2130903065);
    }
    label1514:
    if (paramBundle.equals("0"))
    {
      this.i.setSummary("Free version\n15 day Premium trial activated");
      return;
    }
    this.i.setSummary("Free version");
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131296260, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.k.unregisterOnSharedPreferenceChangeListener(this);
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    case 2131361847: 
    case 2131361848: 
    case 2131361849: 
    case 2131361850: 
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131361846: 
      new AlertDialog.Builder(this).setTitle("Help").setMessage(">> Registration Number\nThis is a unique number for smsBlocker in your phone. Mention this number in your all communication to us.\n\n>> SMS blocking\nCheck the box if you want smsBlocker to filter & block messages.\n\n>> Spam auto-blocking\nCheck the box 'On' and smsBlocker will automatically detect and block spam SMS based on its own logic.\n\n>> Country code\nSpecify the calling/ dialling code of the country where you want to use smsBlocker. The blocking logic differs from country to country. So specify correct code.\n\n>> Block notification\nCheck the box if you want to be notified for every SMS blocked.\n\n>> Delete Logs automatically\nsmsBlocker will display 100 latest records and older record will be deleted automatically, one by one, making space for new records.\n\n>> Backup/restore\nBackup/restore all data of smsBlocker. You are recommend to keep a backup also on your Pc, laptop or email.\n\n>> Suspicious tone\nSet the tone for suspicious SMS pop up.\n\n>> Set password\nSet password to access/open the app. If you forget the password you will not be able to access the app.").setPositiveButton(2131230723, new ao(this)).create().show();
      return true;
    case 2131361851: 
      new AlertDialog.Builder(this).setTitle("About").setMessage("smsBlocker\nVersion 2.1.2\n\nCopyright: Optinno\n© 2009-2011\n\nInnovated by: Optinno Mobitech Pvt. Ltd. Pune, India.\n\nwww.optinno.com\n\nSupport: support@optinno.com\nAll rights reserved.").setPositiveButton(2131230723, new ap(this)).create().show();
      return true;
    case 2131361852: 
      paramMenuItem = new Intent("android.intent.action.SEND");
      paramMenuItem.setType("text/plain");
      paramMenuItem.putExtra("android.intent.extra.TEXT", "Hey, I came across this amazing app on Market- smsBlocker by Optinno. It blocks spam sms automatically. No more irritating spam. Do try.");
      startActivity(Intent.createChooser(paramMenuItem, "Share Text"));
      return true;
    }
    startActivity(new Intent(this, Feedback.class));
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return super.onPrepareOptionsMenu(paramMenu);
  }
  
  protected void onResume()
  {
    super.onResume();
    String str = a(this, "trialflag.txt");
    if ((a(this)) || (str.equals("0")))
    {
      this.e.setEnabled(true);
      this.e.setLayoutResource(2130903064);
      this.j.setEnabled(true);
      this.j.setLayoutResource(2130903064);
      this.f.setEnabled(true);
      this.f.setLayoutResource(2130903064);
      this.h.setEnabled(true);
      this.h.setLayoutResource(2130903064);
      this.g.setEnabled(true);
      this.g.setLayoutResource(2130903064);
    }
    while (a(this))
    {
      this.i.setSummary("Premium version");
      return;
      this.e.setEnabled(false);
      this.e.setLayoutResource(2130903065);
      this.j.setEnabled(false);
      this.j.setLayoutResource(2130903065);
      this.f.setEnabled(false);
      this.f.setLayoutResource(2130903065);
      this.h.setEnabled(false);
      this.h.setLayoutResource(2130903065);
      this.g.setEnabled(false);
      this.g.setLayoutResource(2130903065);
    }
    if (str.equals("0"))
    {
      this.i.setSummary("Free version\n15 day Premium trial activated");
      return;
    }
    this.i.setSummary("Free version");
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (paramString.equals("country_code_dialog"))
    {
      paramSharedPreferences = this.b.getText();
      this.b.setSummary(paramSharedPreferences);
    }
    if (paramString.equals("suspicioustone"))
    {
      if (this.k.getString("suspicioustone", "Silent").equals("Silent")) {
        break label110;
      }
      paramSharedPreferences = RingtoneManager.getRingtone(this, Uri.parse(this.k.getString("suspicioustone", "Silent")));
      if (paramSharedPreferences == null) {
        break label110;
      }
      paramSharedPreferences = paramSharedPreferences.getTitle(this);
      this.h.setSummary(paramSharedPreferences);
    }
    for (;;)
    {
      paramString.equals("App_Autostart");
      return;
      label110:
      this.h.setSummary("Silent");
    }
  }
}
