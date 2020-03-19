package com.smsBlocker.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

final class z
  extends Handler
{
  z(InitialUserData paramInitialUserData) {}
  
  public final void handleMessage(Message paramMessage)
  {
    this.a.c.dismiss();
    int j = paramMessage.what;
    Object localObject;
    int i;
    if (j == 0)
    {
      localObject = InitialUserData.a(this.a, "trialflag.txt");
      paramMessage = PreferenceManager.getDefaultSharedPreferences(this.a).edit();
      paramMessage.putString("country_code_dialog", this.a.a.getText().toString());
      paramMessage.putBoolean("App_Autostart", true);
      paramMessage.putBoolean("AutoBlockSMS", true);
      paramMessage.putBoolean("SMSNotification", true);
      paramMessage.putString("setpassword", "");
      if (!((String)localObject).equals("0"))
      {
        localObject = this.a.getPackageManager().getInstalledApplications(128);
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= ((List)localObject).size())
      {
        i = 0;
        label148:
        if (i == 0) {
          break label285;
        }
        paramMessage.putBoolean("autodelete", true);
        paramMessage.commit();
        paramMessage = this.a;
      }
      try
      {
        paramMessage = new OutputStreamWriter(paramMessage.openFileOutput("firstinstallation.txt", 0));
        paramMessage.write("1");
        paramMessage.flush();
        paramMessage.close();
        paramMessage = new Intent(this.a, MainUi.class);
        this.a.startActivity(paramMessage);
        this.a.finish();
        if (j == 1) {
          Toast.makeText(this.a, "Could not connect to the internet. Please try again!", 0).show();
        }
        return;
        if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.smsBlockerUnlocker"))
        {
          i = 1;
          break label148;
        }
        i += 1;
        continue;
        label285:
        paramMessage.putBoolean("autodelete", false);
      }
      catch (IOException paramMessage)
      {
        for (;;) {}
      }
    }
  }
}
