package mobi.infolife.ezweather;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mobi.infolife.e.a;
import mobi.infolife.ezweather.fragments.activity.WeatherActivity;
import mobi.infolife.ezweather.sdk.c.c.b;
import mobi.infolife.ezweather.widgetscommon.PreferencesLibrary;
import mobi.infolife.utils.s;
import mobi.infolife.utils.t;

public class LabActivity
  extends ActionBarActivity
{
  public static boolean a = false;
  private View b;
  private Context c;
  private LinearLayout d;
  private final String e = "mobi.infolife.ezweather.infocollection";
  private final String f = "mobi.infolife.ezweather.infocollection.StartService";
  private final String g = "mobi.infolife.ezweather.infocollection.StartActivity";
  private final String h = "https://play.google.com/store/apps/details?id=mobi.infolife.ezweather.infocollection";
  private final int i = 1001;
  private a j;
  private View.OnClickListener k = new View.OnClickListener()
  {
    public void onClick(final View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      case 2131690286: 
      case 2131690290: 
      case 2131690292: 
      case 2131690294: 
      case 2131690295: 
      case 2131690296: 
      case 2131690298: 
      case 2131690299: 
      case 2131690300: 
      case 2131690301: 
      default: 
        return;
      case 2131690287: 
        paramAnonymousView = new Intent(LabActivity.this, DiyWidgetActivity.class);
        LabActivity.this.startActivity(paramAnonymousView);
        LabActivity.b(LabActivity.this).a("open_DiyWidgetActivity", new HashMap());
        return;
      case 2131690289: 
        LabActivity.a(LabActivity.this, LabActivity.a(LabActivity.this), "mobi.infolife.earthquakealert");
        LabActivity.b(LabActivity.this).a("open_earthquake_layout", new HashMap());
        return;
      case 2131690291: 
        LabActivity.a(LabActivity.this, LabActivity.a(LabActivity.this), "mobi.infolife.tsunamialert");
        LabActivity.b(LabActivity.this).a("open_tsunami_layout", new HashMap());
        return;
      case 2131690302: 
        paramAnonymousView = new Intent(LabActivity.a(LabActivity.this), LabAboutActivity.class);
        LabActivity.this.startActivity(paramAnonymousView);
        LabActivity.b(LabActivity.this).a("open_LabAboutActivity", new HashMap());
        LabActivity.this.overridePendingTransition(2130968579, 2130968580);
        return;
      case 2131690293: 
        LabActivity.a(LabActivity.this, LabActivity.a(LabActivity.this), "mobi.infolife.weatheralert");
        LabActivity.b(LabActivity.this).a("open_urgency_alert_layout", new HashMap());
        return;
      case 2131690297: 
        paramAnonymousView = new Intent(LabActivity.a(LabActivity.this), PressureSensorActivity.class);
        LabActivity.this.startActivity(paramAnonymousView);
        LabActivity.b(LabActivity.this).a("open_PressureSensorActivity", new HashMap());
        LabActivity.this.overridePendingTransition(2130968579, 2130968580);
        return;
      case 2131690285: 
        int i = WeatherActivity.a;
        LabActivity.b(LabActivity.this).a("open_share_layout", new HashMap());
        paramAnonymousView = mobi.infolife.ezweather.sdk.c.c.a(LabActivity.a(LabActivity.this), i);
        paramAnonymousView.a(new c.b()
        {
          public void onFailed(String paramAnonymous2String) {}
          
          public void onSuccess()
          {
            if (paramAnonymousView.d() == null)
            {
              Toast.makeText(LabActivity.this, LabActivity.this.getResources().getString(2131231001), 0).show();
              return;
            }
            Intent localIntent = new Intent(LabActivity.a(LabActivity.this), ShareWeatherInfoActivity.class);
            LabActivity.this.startActivity(localIntent);
            LabActivity.b(LabActivity.this).a("open_ShareWeatherInfoActivity", new HashMap());
          }
        }, LabActivity.a(LabActivity.this), i);
        return;
      }
      LabActivity.c(LabActivity.this);
      LabActivity.b(LabActivity.this).a("open_smart_report_widget_layout", new HashMap());
    }
  };
  
  public LabActivity() {}
  
  private void a()
  {
    final mobi.infolife.smartreport.c localC = new mobi.infolife.smartreport.c(this.c);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.c);
    Object localObject = LayoutInflater.from(this.c).inflate(2130903298, null);
    localBuilder.setView((View)localObject);
    CheckBox localCheckBox = (CheckBox)((View)localObject).findViewById(2131690800);
    localObject = (CheckBox)((View)localObject).findViewById(2131690801);
    localCheckBox.setChecked(localC.a());
    ((CheckBox)localObject).setChecked(localC.b());
    localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        localC.a(paramAnonymousBoolean);
        HashMap localHashMap = new HashMap();
        if (paramAnonymousBoolean) {}
        for (paramAnonymousCompoundButton = "Morning ON";; paramAnonymousCompoundButton = "Morning OFF")
        {
          localHashMap.put("SWITCH", paramAnonymousCompoundButton);
          MobclickAgent.onEvent(LabActivity.a(LabActivity.this), "SmartReport", localHashMap);
          return;
        }
      }
    });
    ((CheckBox)localObject).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        localC.b(paramAnonymousBoolean);
        HashMap localHashMap = new HashMap();
        if (paramAnonymousBoolean) {}
        for (paramAnonymousCompoundButton = "Night ON";; paramAnonymousCompoundButton = "Night OFF")
        {
          localHashMap.put("SWITCH", paramAnonymousCompoundButton);
          MobclickAgent.onEvent(LabActivity.a(LabActivity.this), "SmartReport", localHashMap);
          return;
        }
      }
    });
    localBuilder.create().show();
  }
  
  private void a(Context paramContext, String paramString)
  {
    if (d(paramContext, paramString))
    {
      b(paramContext, paramString);
      return;
    }
    try
    {
      String str = "//details?id=" + paramString + "&referrer=utm_source%3D" + "lab" + "%26utm_medium%3D" + paramContext.getPackageName();
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market:" + str)));
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString)));
      localException.printStackTrace();
    }
  }
  
  private void b()
  {
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131690287);
    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131690289);
    LinearLayout localLinearLayout3 = (LinearLayout)findViewById(2131690297);
    this.d = ((LinearLayout)findViewById(2131690291));
    LinearLayout localLinearLayout4 = (LinearLayout)findViewById(2131690293);
    LinearLayout localLinearLayout5 = (LinearLayout)findViewById(2131690302);
    ImageView localImageView = (ImageView)findViewById(2131690296);
    LinearLayout localLinearLayout6 = (LinearLayout)findViewById(2131690295);
    LinearLayout localLinearLayout7 = (LinearLayout)findViewById(2131690285);
    localLinearLayout6.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(LabActivity.a(LabActivity.this), WorldClockActivity.class);
        LabActivity.this.startActivity(paramAnonymousView);
        LabActivity.b(LabActivity.this).a("open_WorldClockActivity", new HashMap());
        LabActivity.this.overridePendingTransition(2130968579, 2130968580);
      }
    });
    localLinearLayout1.setOnClickListener(this.k);
    localLinearLayout2.setOnClickListener(this.k);
    localLinearLayout3.setOnClickListener(this.k);
    this.d.setOnClickListener(this.k);
    localLinearLayout4.setOnClickListener(this.k);
    localLinearLayout7.setOnClickListener(this.k);
    localLinearLayout5.setOnClickListener(this.k);
    PreferencesLibrary.getWorldClockStat(this.c);
    if (a)
    {
      localLinearLayout3.setVisibility(0);
      localImageView.setVisibility(0);
    }
    ((LinearLayout)findViewById(2131690288)).setOnClickListener(this.k);
  }
  
  private void b(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString, c(paramContext, paramString));
    localIntent.setFlags(131072);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private String c(Context paramContext, String paramString)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator();
    while (paramContext.hasNext())
    {
      localObject = (ResolveInfo)paramContext.next();
      if (((ResolveInfo)localObject).activityInfo.packageName.equals(paramString)) {
        return ((ResolveInfo)localObject).activityInfo.name;
      }
    }
    return "";
  }
  
  private boolean d(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if (paramContext != null)
      {
        int m = 0;
        while (m < paramContext.size())
        {
          if (paramString.equals(((PackageInfo)paramContext.get(m)).packageName)) {
            return true;
          }
          m += 1;
        }
      }
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onCreate(Bundle paramBundle)
  {
    setTheme(2131624067);
    this.j = new a(this);
    super.onCreate(paramBundle);
    this.c = this;
    this.b = LayoutInflater.from(this).inflate(2130903205, null);
    s.a(this.c, this.b, this);
    setContentView(this.b);
    t.a(2131231257, this);
    b();
    if (System.currentTimeMillis() > 1406908800000L)
    {
      this.d.setVisibility(0);
      findViewById(2131690290).setVisibility(0);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      finish();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }
  
  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean) {
      s.a(this.c, this.b, this);
    }
  }
}
