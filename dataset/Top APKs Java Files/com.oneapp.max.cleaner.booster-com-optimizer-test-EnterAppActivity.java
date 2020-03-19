package com.optimizer.test;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ihs.app.framework.b.a;
import com.ihs.app.framework.b.c;
import com.ihs.commons.e.i;
import com.optimizer.test.f.m;
import com.optimizer.test.f.v;
import com.optimizer.test.main.MainActivity;
import com.optimizer.test.module.about.PrivacyPolicyActivity;
import com.optimizer.test.module.about.TermsOfServiceActivity;
import com.optimizer.test.module.callassistant.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class EnterAppActivity
  extends c
{
  private Handler a = new Handler();
  private boolean b;
  
  public EnterAppActivity() {}
  
  protected final int d()
  {
    return 2131886287;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131493001);
    paramBundle = (TextView)findViewById(2131363274);
    if (!"en".equals(m.a())) {
      paramBundle.setTypeface(Typeface.SANS_SERIF, 1);
    }
    this.b = i.a(this, "optimizer_enter_app").a("PREF_KEY_IS_FIRST_ENTER", true);
    if (this.b)
    {
      com.ihs.app.a.a.a("Splash_Viewed");
      if (!com.optimizer.test.module.gdpr.a.a()) {
        if (com.ihs.commons.config.a.b(new String[] { "Application", "Modules", "GDPR", "PopupBeforeStart" })) {
          com.optimizer.test.module.gdpr.a.a(this, b.a.b, getString(2131821462), new b.c()
          {
            public final void a()
            {
              com.ihs.app.a.a.a("gdpr_autorization_successful");
            }
            
            public final void b()
            {
              EnterAppActivity.this.finish();
            }
          });
        }
      }
      ((Button)findViewById(2131364043)).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          com.ihs.app.a.a.a("Splash_BtnClicked");
          i.a(EnterAppActivity.this, "optimizer_enter_app").c("PREF_KEY_IS_FIRST_ENTER", false);
          if (!com.optimizer.test.module.gdpr.a.a()) {
            if (!com.ihs.commons.config.a.b(new String[] { "Application", "Modules", "GDPR", "PopupBeforeStart" }))
            {
              com.optimizer.test.module.gdpr.a.a(EnterAppActivity.this, b.a.b, EnterAppActivity.this.getString(2131821462), new b.c()
              {
                public final void a()
                {
                  EnterAppActivity.a(EnterAppActivity.this).postDelayed(new Runnable()
                  {
                    public final void run()
                    {
                      Intent localIntent = new Intent(EnterAppActivity.this, MainActivity.class).setFlags(536870912);
                      localIntent.putExtra("EXTRA_STARTED_FROM_ENTER_ACTIVITY", true);
                      EnterAppActivity.this.startActivity(localIntent);
                      EnterAppActivity.this.finish();
                      com.ihs.app.a.a.a("gdpr_autorization_successful");
                    }
                  }, 1000L);
                }
                
                public final void b()
                {
                  EnterAppActivity.this.finish();
                }
              });
              return;
            }
          }
          paramAnonymousView = new Intent(EnterAppActivity.this, MainActivity.class).setFlags(536870912);
          paramAnonymousView.putExtra("EXTRA_STARTED_FROM_ENTER_ACTIVITY", true);
          EnterAppActivity.this.startActivity(paramAnonymousView);
          EnterAppActivity.this.finish();
        }
      });
      Object localObject = getResources().getString(2131821872, new Object[] { getResources().getString(2131821461), getResources().getString(2131821944) });
      paramBundle = new SpannableString((CharSequence)localObject);
      int j = ((String)localObject).indexOf(getResources().getString(2131821461));
      int i = j;
      if (j == -1) {
        i = 0;
      }
      j = getResources().getString(2131821461).length() + i;
      paramBundle.setSpan(new UnderlineSpan(), i, j, 33);
      paramBundle.setSpan(new ClickableSpan()
      {
        public final void onClick(View paramAnonymousView)
        {
          EnterAppActivity.this.startActivity(new Intent(EnterAppActivity.this, PrivacyPolicyActivity.class));
        }
      }, i, j, 33);
      paramBundle.setSpan(new ForegroundColorSpan(-1291845632), i, j, 33);
      j = ((String)localObject).indexOf(getResources().getString(2131821944));
      i = j;
      if (j == -1) {
        i = 0;
      }
      j = getResources().getString(2131821944).length() + i;
      paramBundle.setSpan(new UnderlineSpan(), i, j, 33);
      paramBundle.setSpan(new ClickableSpan()
      {
        public final void onClick(View paramAnonymousView)
        {
          EnterAppActivity.this.startActivity(new Intent(EnterAppActivity.this, TermsOfServiceActivity.class));
        }
      }, i, j, 33);
      paramBundle.setSpan(new ForegroundColorSpan(-1291845632), i, j, 33);
      localObject = (TextView)findViewById(2131364040);
      ((TextView)localObject).setText(paramBundle);
      ((TextView)localObject).setMovementMethod(LinkMovementMethod.getInstance());
    }
    for (;;)
    {
      if ((d.a()) && (!i.a(this, "PREF_FILE_SPLASH_SHOW_CALL_ASSISTANT_PROMOTE_INFO").a("PREF_KEY_SPLASH_SHOW_CALL_ASSISTANT_PROMOTE_TIME")))
      {
        i.a(this, "PREF_FILE_SPLASH_SHOW_CALL_ASSISTANT_PROMOTE_INFO").b("PREF_KEY_SPLASH_SHOW_CALL_ASSISTANT_PROMOTE_TIME", System.currentTimeMillis());
        if (!d.g()) {
          break;
        }
        net.appcloudbox.common.analytics.a.a("CallAss_IfAccess_Received", new String[] { "AccessType", "Allow" });
      }
      return;
      ((LinearLayout)findViewById(2131364037)).setVisibility(8);
    }
    net.appcloudbox.common.analytics.a.a("CallAss_IfAccess_Received", new String[] { "AccessType", "NotAllow" });
    new Thread(new Runnable()
    {
      public final void run()
      {
        try
        {
          if (!v.a("android.permission.READ_CONTACTS")) {
            d.a("911");
          }
          if (!v.a("android.permission.READ_CALL_LOG")) {
            com.ihs.app.framework.a.a().getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[] { "name" }, null, null, "date DESC");
          }
          return;
        }
        catch (Exception localException) {}
      }
    }).start();
  }
  
  protected void onPause()
  {
    super.onPause();
    this.a.removeCallbacksAndMessages(null);
  }
  
  protected void onResume()
  {
    super.onResume();
    if (!this.b) {
      if (!com.optimizer.test.module.gdpr.a.a()) {
        com.optimizer.test.module.gdpr.a.a(this, b.a.b, getString(2131821462), new b.c()
        {
          public final void a()
          {
            Intent localIntent = new Intent(EnterAppActivity.this, MainActivity.class);
            localIntent.putExtra("EXTRA_STARTED_FROM_ENTER_ACTIVITY", true);
            EnterAppActivity.this.startActivity(localIntent);
            EnterAppActivity.this.finish();
            com.ihs.app.a.a.a("gdpr_autorization_successful");
          }
          
          public final void b()
          {
            EnterAppActivity.this.finish();
          }
        });
      }
    }
    while (i.a(this, "optimizer_enter_app").a("PREF_KEY_HAS_SEND_FIURRY_MESSAGE", false))
    {
      return;
      this.a.postDelayed(new Runnable()
      {
        public final void run()
        {
          Intent localIntent = new Intent(EnterAppActivity.this, MainActivity.class);
          localIntent.putExtra("EXTRA_STARTED_FROM_ENTER_ACTIVITY", true);
          EnterAppActivity.this.startActivity(localIntent);
          EnterAppActivity.this.overridePendingTransition(2130771998, 2130771992);
          EnterAppActivity.this.finish();
        }
      }, 1000L);
      return;
    }
    i.a(this, "optimizer_enter_app").c("PREF_KEY_HAS_SEND_FIURRY_MESSAGE", true);
    this.a.postDelayed(new Runnable()
    {
      public final void run()
      {
        e.a().a.execute(new Runnable()
        {
          public final void run()
          {
            Object localObject = new ArrayList();
            try
            {
              List localList = com.ihs.app.framework.a.a().getPackageManager().getInstalledApplications(128);
              localObject = localList;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                localException.printStackTrace();
              }
            }
            localObject = ((List)localObject).iterator();
            while (((Iterator)localObject).hasNext()) {
              if (TextUtils.equals(((ApplicationInfo)((Iterator)localObject).next()).packageName, "com.facebook.orca")) {
                com.ihs.app.a.a.a("Have_Messager");
              }
            }
          }
        });
      }
    }, 1000L);
  }
}
