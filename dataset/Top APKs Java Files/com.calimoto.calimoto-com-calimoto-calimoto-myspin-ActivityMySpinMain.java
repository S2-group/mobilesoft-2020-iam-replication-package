package com.calimoto.calimoto.myspin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.bosch.myspin.serversdk.MySpinServerSDK;
import com.bosch.myspin.serversdk.focuscontrol.MySpinFocusControlEvent;
import com.calimoto.calimoto.ApplicationCalimoto;
import com.calimoto.calimoto.android.f;
import com.calimoto.calimoto.android.i;
import com.calimoto.calimoto.q.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityMySpinMain
  extends a
{
  public ActivityMySpinMain() {}
  
  public void finish()
  {
    try
    {
      this.a.p();
      try
      {
        if (MySpinServerSDK.a().b())
        {
          Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
          while (localIterator.hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            if (localApplicationInfo.packageName.startsWith("com.bosch.myspin.launcherapp")) {
              startActivity(getPackageManager().getLaunchIntentForPackage(localApplicationInfo.packageName));
            }
          }
        }
      }
      catch (Exception localException1)
      {
        d.a(getApplicationContext(), localException1);
      }
      super.finish();
    }
    catch (Exception localException2)
    {
      d.a(getApplicationContext(), localException2);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      setContentView(2131427367);
      this.a.o();
      findViewById(2131296564).setOnClickListener(new f(getApplicationContext())
      {
        protected void a(View paramAnonymousView)
        {
          ActivityMySpinMain.this.c.a(new MySpinFocusControlEvent(0, 4));
        }
      });
      paramBundle = (Button)findViewById(2131296426);
      paramBundle.setOnClickListener(new f(getApplicationContext())
      {
        protected void a(View paramAnonymousView)
        {
          ActivityMySpinMain.this.startActivity(new Intent(ActivityMySpinMain.this.getApplicationContext(), ActivityMySpinMap.class).putExtra("keyMySpinMapLaunchingActivity", ActivityMySpinMain.class));
        }
      });
      Button localButton1 = (Button)findViewById(2131296428);
      localButton1.setOnClickListener(new f(getApplicationContext())
      {
        protected void a(View paramAnonymousView)
        {
          ActivityMySpinMain.this.startActivity(new Intent(ActivityMySpinMain.this.getApplicationContext(), ActivityMySpinRoundTrip.class));
        }
      });
      Button localButton2 = (Button)findViewById(2131296429);
      localButton2.setOnClickListener(new f(getApplicationContext())
      {
        protected void a(View paramAnonymousView)
        {
          ActivityMySpinMain.this.startActivity(new Intent(ActivityMySpinMain.this.getApplicationContext(), ActivityMySpinMap.class).putExtra("keyMySpinMapLaunchingActivity", ActivityMySpinMain.class).putExtra("keyMySpinStartTracking", true));
        }
      });
      Button localButton3 = (Button)findViewById(2131296427);
      localButton3.setOnClickListener(new f(getApplicationContext())
      {
        protected void a(View paramAnonymousView)
        {
          ActivityMySpinMain.this.startActivity(new Intent(ActivityMySpinMain.this.getApplicationContext(), ActivityMySpinRoutes.class));
        }
      });
      findViewById(2131296565).setOnClickListener(new f(getApplicationContext())
      {
        protected void a(View paramAnonymousView)
        {
          ActivityMySpinMain.this.c.a(new MySpinFocusControlEvent(0, 21));
        }
      });
      findViewById(2131296566).setOnClickListener(new f(getApplicationContext())
      {
        protected void a(View paramAnonymousView)
        {
          ActivityMySpinMain.this.c.a(new MySpinFocusControlEvent(0, 22));
        }
      });
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramBundle);
      localArrayList.add(localButton1);
      localArrayList.add(localButton2);
      localArrayList.add(localButton3);
      this.c = new b(getWindow(), localArrayList, new i(getApplicationContext())
      {
        protected void a()
        {
          ActivityMySpinMain.this.finish();
        }
      }, true);
      return;
    }
    catch (Exception paramBundle)
    {
      d.a(getApplicationContext(), paramBundle);
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    try
    {
      setIntent(paramIntent);
      return;
    }
    catch (Exception paramIntent)
    {
      d.a(getApplicationContext(), paramIntent);
    }
  }
}
