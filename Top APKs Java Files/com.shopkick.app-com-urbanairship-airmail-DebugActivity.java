package com.urbanairship.airmail;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.urbanairship.Logger;
import com.urbanairship.push.proto.Messages.PushNotification;
import com.urbanairship.push.proto.Messages.PushNotification.Builder;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;

public class DebugActivity
  extends Activity
  implements Runnable
{
  Handler handler = null;
  UAShared mixin = UAShared.get();
  Thread thread = null;
  
  public DebugActivity() {}
  
  public void attachButtons()
  {
    ViewGroup localViewGroup = (ViewGroup)findViewById(2131230728);
    if (localViewGroup == null) {
      Logger.error("Could not find main view");
    }
    for (;;)
    {
      return;
      int i = 0;
      while (i < localViewGroup.getChildCount())
      {
        View localView = localViewGroup.getChildAt(i);
        if ((localView instanceof Button)) {
          ((Button)localView).setOnClickListener(clicked());
        }
        i += 1;
      }
    }
  }
  
  public View.OnClickListener clicked()
  {
    new View.OnClickListener()
    {
      public void onClick(final View paramAnonymousView)
      {
        int i = paramAnonymousView.getId();
        if (i == 2131230731)
        {
          Logger.debug("start");
          AirMailService.actionStart(DebugActivity.this);
        }
        do
        {
          for (;;)
          {
            return;
            if (i == 2131230732)
            {
              Logger.debug("stop");
              AirMailService.actionStop(DebugActivity.this);
              return;
            }
            if (i == 2131230735)
            {
              Logger.debug("Populating reliers");
              paramAnonymousView = new String[6];
              paramAnonymousView[0] = "com.android.calculator2";
              paramAnonymousView[1] = "com.android.browser";
              paramAnonymousView[2] = "com.android.contacts";
              paramAnonymousView[3] = "com.google.android.talk";
              paramAnonymousView[4] = "com.google.android.youtube";
              paramAnonymousView[5] = "com.google.android.calendar";
              int j = paramAnonymousView.length;
              i = 0;
              while (i < j)
              {
                Relier.getOrCreate(paramAnonymousView[i], DebugActivity.this).enable();
                i += 1;
              }
            }
            else
            {
              if (i == 2131230738)
              {
                paramAnonymousView = DebugActivity.this.mixin.prefs().edit();
                paramAnonymousView.remove("APID");
                paramAnonymousView.remove("APSECRET");
                paramAnonymousView.commit();
                return;
              }
              if (i == 2131230737)
              {
                paramAnonymousView = DebugActivity.this;
                AirMailService.actionStop(paramAnonymousView);
                localObject = DebugActivity.this.mixin.prefs().edit();
                ((SharedPreferences.Editor)localObject).remove("APID");
                ((SharedPreferences.Editor)localObject).remove("APSECRET");
                ((SharedPreferences.Editor)localObject).commit();
                Toast.makeText(paramAnonymousView, "APID and secret cleared, restarting AirMailService in 5 seconds", 1).show();
                new Thread()
                {
                  public void run()
                  {
                    try
                    {
                      Thread.sleep(5000L);
                      AirMailService.actionStart(paramAnonymousView);
                      return;
                    }
                    catch (InterruptedException localInterruptedException)
                    {
                      for (;;)
                      {
                        Logger.warn("Interrupted", localInterruptedException);
                      }
                    }
                  }
                }.start();
                return;
              }
              if (i == 2131230733)
              {
                Logger.debug(String.format("APID: %s", new Object[] { DebugActivity.this.mixin.pushId() }));
                return;
              }
              if (i != 2131230734) {
                break;
              }
              Logger.debug("=== INSTALLED PACKAGES ===");
              paramAnonymousView = DebugActivity.this.getPackageManager().getInstalledPackages(0).iterator();
              while (paramAnonymousView.hasNext()) {
                Logger.debug(((PackageInfo)paramAnonymousView.next()).packageName);
              }
            }
          }
        } while (i != 2131230736);
        Logger.debug("Notify me!");
        paramAnonymousView = Relier.getOrCreate("com.android.browser", DebugActivity.this);
        Object localObject = Messages.PushNotification.newBuilder().setMessageId("message id!").setMessage("Message!").setPackageName(paramAnonymousView.pkgName).setPayload("Is it tomorrow or just the end of time?").build();
        paramAnonymousView.enable();
        paramAnonymousView.assignField("app_key", "foo");
        paramAnonymousView.deliverPush((Messages.PushNotification)localObject);
        Logger.debug("App key: " + paramAnonymousView.appKey());
      }
    };
  }
  
  public Handler generateHandler()
  {
    new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        Object localObject = (TextView)DebugActivity.this.findViewById(2131230729);
        paramAnonymousMessage = (TextView)DebugActivity.this.findViewById(2131230730);
        ((TextView)localObject).setText("Connected: " + AirMailService.connected);
        if ((AirMailService.connected) && (AirMailService.lastKeepAlive != null))
        {
          localObject = DateFormat.getDateInstance(2).format(AirMailService.lastKeepAlive);
          String str = DateFormat.getTimeInstance(1).format(AirMailService.lastKeepAlive);
          paramAnonymousMessage.setText("Last Keepalive: " + ((String)localObject).toString() + " " + str.toString());
          return;
        }
        paramAnonymousMessage.setText("");
      }
    };
  }
  
  public void initThread()
  {
    Logger.debug("DebugActivity bg thread started");
    killThread();
    this.thread = new Thread(this);
    this.thread.start();
  }
  
  public void killThread()
  {
    if (this.thread != null)
    {
      this.thread.interrupt();
      Logger.debug("DebugActivity bg thread killed");
      this.thread = null;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903042);
    attachButtons();
  }
  
  public void onPause()
  {
    super.onPause();
    killThread();
  }
  
  public void onResume()
  {
    super.onResume();
    initThread();
  }
  
  public void onStop()
  {
    super.onStop();
    killThread();
  }
  
  public void run()
  {
    try
    {
      for (;;)
      {
        this.handler.sendEmptyMessage(0);
        Thread.sleep(5000L);
      }
      return;
    }
    catch (Exception localException)
    {
      Logger.error("Error.", localException);
    }
  }
}
