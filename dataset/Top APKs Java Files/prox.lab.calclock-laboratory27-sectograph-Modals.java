package laboratory27.sectograph;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.CalendarContract.Events;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class Modals
  extends AppCompatActivity
{
  String TAG = "ModalClass";
  GestureDetector mGestureDetector;
  
  public Modals() {}
  
  public static class Modal_about_secret
    extends Modals
  {
    public Modal_about_secret() {}
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968645);
      ((Button)findViewById(2131755309)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_about_secret.this.finish();
        }
      });
      switch (Integer.valueOf(Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("pref_servicemenu_updatemethod", "1"))).intValue())
      {
      default: 
        return;
      case 1: 
        ((RadioButton)findViewById(2131755306)).setChecked(true);
        return;
      case 2: 
        ((RadioButton)findViewById(2131755307)).setChecked(true);
        return;
      }
      ((RadioButton)findViewById(2131755308)).setChecked(true);
    }
    
    public void onServiceMenu_UpdateMethod_RadioButtonClicked(View paramView)
    {
      boolean bool = ((RadioButton)paramView).isChecked();
      SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
      switch (paramView.getId())
      {
      }
      for (;;)
      {
        update_RunUpdater();
        return;
        if (bool)
        {
          localEditor.putString("pref_servicemenu_updatemethod", "1");
          localEditor.commit();
          continue;
          if (bool)
          {
            localEditor.putString("pref_servicemenu_updatemethod", "2");
            localEditor.commit();
            continue;
            if (bool)
            {
              localEditor.putString("pref_servicemenu_updatemethod", "3");
              localEditor.commit();
            }
          }
        }
      }
    }
    
    public void update_RunUpdater()
    {
      Object localObject = new TimeEventsReceiver();
      if (localObject != null) {}
      try
      {
        unregisterReceiver((BroadcastReceiver)localObject);
      }
      catch (Exception localException1)
      {
        try
        {
          for (;;)
          {
            stopService(new Intent(this, Services.class));
            try
            {
              localObject = (AlarmManager)getBaseContext().getSystemService("alarm");
              PendingIntent localPendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, new Intent(getBaseContext(), AlarmManagerReceiver.class), 134217728);
              ((AlarmManager)localObject).cancel(localPendingIntent);
              localPendingIntent.cancel();
              RunUpdater.stratProcess(getBaseContext());
              Toast.makeText(getApplicationContext(), "♻", 1).show();
              return;
              localException1 = localException1;
            }
            catch (Exception localException2)
            {
              for (;;) {}
            }
          }
        }
        catch (Exception localException3)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public static class Modal_battery_save
    extends Modals
  {
    public Modal_battery_save() {}
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968646);
      paramBundle = getResources().getString(2131296383);
      for (;;)
      {
        try
        {
          int i = Build.VERSION.SDK_INT;
          switch (i)
          {
          }
        }
        catch (Exception localException)
        {
          Object localObject;
          continue;
        }
        localObject = (TextView)findViewById(2131755311);
        try
        {
          ((TextView)localObject).setText(Html.fromHtml(paramBundle));
          ((Button)findViewById(2131755310)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              Modals.Modal_battery_save.this.finish();
            }
          });
          return;
          localObject = paramBundle + getResources().getString(2131296385);
          paramBundle = (Bundle)localObject;
          continue;
          localObject = paramBundle + getResources().getString(2131296386);
          paramBundle = (Bundle)localObject;
          continue;
          localObject = paramBundle + getResources().getString(2131296386);
          paramBundle = (Bundle)localObject;
          continue;
          localObject = paramBundle + getResources().getString(2131296387);
          paramBundle = (Bundle)localObject;
          continue;
          localObject = paramBundle + getResources().getString(2131296387);
          paramBundle = (Bundle)localObject;
        }
        catch (Exception paramBundle) {}
      }
    }
  }
  
  public static class Modal_buy_extension_24_mode
    extends Modals
  {
    IInAppBillingService inAppBillingService;
    ServiceConnection serviceConnection = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        Modals.Modal_buy_extension_24_mode.this.inAppBillingService = IInAppBillingService.Stub.asInterface(paramAnonymousIBinder);
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Utils.sendStringExtraData("priceAmountMicros_mode24", ((Billing)Billing.getInAppPurchases(Modals.Modal_buy_extension_24_mode.this.getBaseContext(), Modals.Modal_buy_extension_24_mode.this.inAppBillingService, "inapp", new String[] { "mode_24_billing_ind" }).get(0)).price.toString(), Modals.Modal_buy_extension_24_mode.this.getBaseContext());
              this.val$mHandler2.post(new Runnable()
              {
                public void run()
                {
                  String str1 = Utils.getStringExtraData("priceAmountMicros_mode24", Modals.Modal_buy_extension_24_mode.this.getBaseContext());
                  String str2 = Modals.Modal_buy_extension_24_mode.this.getResources().getString(2131296465);
                  Button localButton = (Button)Modals.Modal_buy_extension_24_mode.this.findViewById(2131755321);
                  try
                  {
                    localButton.setTypeface(Typeface.createFromAsset(Modals.Modal_buy_extension_24_mode.this.getAssets(), "fonts/roboto-regular.ttf"));
                    localButton.setText(str2 + " " + str1);
                    Modals.Modal_buy_extension_24_mode.this.findViewById(2131755312).setVisibility(0);
                    Modals.Modal_buy_extension_24_mode.this.findViewById(2131755323).setVisibility(8);
                    return;
                  }
                  catch (Exception localException)
                  {
                    for (;;) {}
                  }
                }
              });
              return;
            }
            catch (Exception localException)
            {
              for (;;) {}
            }
          }
        }).start();
        ((Button)Modals.Modal_buy_extension_24_mode.this.findViewById(2131755321)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            try
            {
              Modals.Modal_buy_extension_24_mode.this.purchaseProduct(Modals.Modal_buy_extension_24_mode.this.getBaseContext(), Modals.Modal_buy_extension_24_mode.this.inAppBillingService, "inapp", "mode_24_billing_ind");
              return;
            }
            catch (Exception paramAnonymous2View)
            {
              paramAnonymous2View.printStackTrace();
            }
          }
        });
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        Modals.Modal_buy_extension_24_mode.this.inAppBillingService = null;
      }
    };
    
    public Modal_buy_extension_24_mode() {}
    
    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      if (paramInt1 == 1234) {
        if (paramIntent.getIntExtra("RESPONSE_CODE", -1) == 0)
        {
          String str = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
          paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");
          paramIntent = Billing.readPurchase(str);
          if ((paramIntent[2].equals("mode_24_billing_ind")) && (paramIntent[4].equals("0")))
          {
            Log.w(this.TAG, "MODE 24 BAY it is TRUE. token: " + paramIntent[6]);
            ConfigClass.changeMode24PayStatus(true, getBaseContext());
            Utils.sendStringExtraData("NEED_UPDATE_LEFT_GRAY_BTN", "GO", getBaseContext());
          }
        }
      }
      for (;;)
      {
        try
        {
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              Modals.Modal_buy_extension_24_mode.this.finish();
            }
          }, 200L);
          return;
        }
        catch (Exception paramIntent)
        {
          finish();
        }
        System.out.println("onActivityResult BILLING_RESPONSE_RESULT_OK false");
        continue;
        System.out.println("onActivityResult NOT ISSET");
      }
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968647);
      Object localObject = (Typeface)ConfigClass.fontCache.get("fonts/materialIcons-regular.ttf");
      paramBundle = (Bundle)localObject;
      if (localObject == null) {}
      try
      {
        paramBundle = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/materialIcons-regular.ttf");
        ConfigClass.fontCache.put("fonts/materialIcons-regular.ttf", paramBundle);
        localObject = (TextView)findViewById(2131755313);
        TextView localTextView1 = (TextView)findViewById(2131755314);
        TextView localTextView2 = (TextView)findViewById(2131755317);
        TextView localTextView3 = (TextView)findViewById(2131755318);
        ((TextView)localObject).setTypeface(paramBundle);
        localTextView1.setTypeface(paramBundle);
        localTextView2.setTypeface(paramBundle);
        localTextView3.setTypeface(paramBundle);
        ((TextView)findViewById(2131755319)).setText(Html.fromHtml(getString(2131296462)));
        ((Button)findViewById(2131755320)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Modals.Modal_buy_extension_24_mode.this.finish();
          }
        });
        ((ImageView)findViewById(2131755157)).setImageBitmap(Graph_24.getBitmap(getBaseContext(), 0L, 1, "24", "dark_theme"));
        paramBundle = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        paramBundle.setPackage("com.android.vending");
        bindService(paramBundle, this.serviceConnection, 1);
        return;
      }
      catch (Exception paramBundle)
      {
        for (;;)
        {
          paramBundle = (Bundle)localObject;
        }
      }
    }
    
    public void onDestroy()
    {
      super.onDestroy();
      if (this.serviceConnection != null) {}
      try
      {
        unbindService(this.serviceConnection);
        return;
      }
      catch (Exception localException) {}
    }
    
    public void onStop()
    {
      super.onStop();
      if (this.serviceConnection != null) {}
      try
      {
        unbindService(this.serviceConnection);
        return;
      }
      catch (Exception localException) {}
    }
    
    public void purchaseProduct(Context paramContext, IInAppBillingService paramIInAppBillingService, String paramString1, String paramString2)
      throws Exception
    {
      paramContext = (PendingIntent)paramIInAppBillingService.getBuyIntent(3, paramContext.getPackageName(), paramString2, paramString1, "12345").getParcelable("BUY_INTENT");
      if (Build.VERSION.SDK_INT >= 16) {
        startIntentSenderForResult(paramContext.getIntentSender(), 1234, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), null);
      }
    }
  }
  
  public static class Modal_buy_extension_palette
    extends Modals
  {
    IInAppBillingService inAppBillingService;
    ServiceConnection serviceConnection = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        Modals.Modal_buy_extension_palette.this.inAppBillingService = IInAppBillingService.Stub.asInterface(paramAnonymousIBinder);
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Utils.sendStringExtraData("priceAmountMicros", ((Billing)Billing.getInAppPurchases(Modals.Modal_buy_extension_palette.this.getBaseContext(), Modals.Modal_buy_extension_palette.this.inAppBillingService, "inapp", new String[] { "pay_palette_billing_ind" }).get(0)).price.toString(), Modals.Modal_buy_extension_palette.this.getBaseContext());
              this.val$mHandler2.post(new Runnable()
              {
                public void run()
                {
                  String str1 = Utils.getStringExtraData("priceAmountMicros", Modals.Modal_buy_extension_palette.this.getBaseContext());
                  String str2 = Modals.Modal_buy_extension_palette.this.getResources().getString(2131296465);
                  Button localButton = (Button)Modals.Modal_buy_extension_palette.this.findViewById(2131755321);
                  try
                  {
                    localButton.setTypeface(Typeface.createFromAsset(Modals.Modal_buy_extension_palette.this.getAssets(), "fonts/roboto-regular.ttf"));
                    localButton.setText(str2 + " " + str1);
                    Modals.Modal_buy_extension_palette.this.findViewById(2131755312).setVisibility(0);
                    Modals.Modal_buy_extension_palette.this.findViewById(2131755323).setVisibility(8);
                    return;
                  }
                  catch (Exception localException)
                  {
                    for (;;) {}
                  }
                }
              });
              return;
            }
            catch (Exception localException)
            {
              for (;;) {}
            }
          }
        }).start();
        ((Button)Modals.Modal_buy_extension_palette.this.findViewById(2131755321)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            try
            {
              Modals.Modal_buy_extension_palette.this.purchaseProduct(Modals.Modal_buy_extension_palette.this.getBaseContext(), Modals.Modal_buy_extension_palette.this.inAppBillingService, "inapp", "pay_palette_billing_ind");
              return;
            }
            catch (Exception paramAnonymous2View)
            {
              paramAnonymous2View.printStackTrace();
            }
          }
        });
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        Modals.Modal_buy_extension_palette.this.inAppBillingService = null;
      }
    };
    
    public Modal_buy_extension_palette() {}
    
    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      if (paramInt1 == 1234) {
        if (paramIntent.getIntExtra("RESPONSE_CODE", -1) == 0)
        {
          String str = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
          paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");
          paramIntent = Billing.readPurchase(str);
          if ((paramIntent[2].equals("pay_palette_billing_ind")) && (paramIntent[4].equals("0")))
          {
            Log.w(this.TAG, "PALETTE BAY it is TRUE. token: " + paramIntent[6]);
            DesignWidget.changePalettePayStatus(true, getBaseContext());
          }
        }
      }
      for (;;)
      {
        try
        {
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              Modals.Modal_buy_extension_palette.this.finish();
            }
          }, 200L);
          return;
        }
        catch (Exception paramIntent)
        {
          finish();
        }
        System.out.println("onActivityResult BILLING_RESPONSE_RESULT_OK false");
        continue;
        System.out.println("onActivityResult NOT ISSET");
      }
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968648);
      paramBundle = (TextView)findViewById(2131755324);
      Button localButton = (Button)findViewById(2131755321);
      paramBundle.setText(Html.fromHtml(getString(2131296466)));
      ((Button)findViewById(2131755320)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_buy_extension_palette.this.finish();
        }
      });
      paramBundle = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      paramBundle.setPackage("com.android.vending");
      bindService(paramBundle, this.serviceConnection, 1);
    }
    
    public void onDestroy()
    {
      super.onDestroy();
      if (this.serviceConnection != null) {}
      try
      {
        unbindService(this.serviceConnection);
        return;
      }
      catch (Exception localException) {}
    }
    
    public void onStop()
    {
      super.onStop();
      if (this.serviceConnection != null) {}
      try
      {
        unbindService(this.serviceConnection);
        return;
      }
      catch (Exception localException) {}
    }
    
    public void purchaseProduct(Context paramContext, IInAppBillingService paramIInAppBillingService, String paramString1, String paramString2)
      throws Exception
    {
      paramContext = (PendingIntent)paramIInAppBillingService.getBuyIntent(3, paramContext.getPackageName(), paramString2, paramString1, "12345").getParcelable("BUY_INTENT");
      if (Build.VERSION.SDK_INT >= 16) {
        startIntentSenderForResult(paramContext.getIntentSender(), 1234, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), null);
      }
    }
  }
  
  public static class Modal_calendar_denied_or_not_found
    extends Modals
  {
    public Modal_calendar_denied_or_not_found() {}
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968649);
      ((Button)findViewById(2131755310)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_calendar_denied_or_not_found.this.finish();
        }
      });
      ((Button)findViewById(2131755325)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            Modals.Modal_calendar_denied_or_not_found.this.finish();
            paramAnonymousView = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", Modals.Modal_calendar_denied_or_not_found.this.getPackageName(), null));
            paramAnonymousView.addFlags(268435456);
            Modals.Modal_calendar_denied_or_not_found.this.startActivity(paramAnonymousView);
            return;
          }
          catch (Exception paramAnonymousView) {}
        }
      });
    }
  }
  
  public static class Modal_demo_mode
    extends Modals
  {
    public Modal_demo_mode() {}
    
    public void onBackPressed()
    {
      try
      {
        Intent localIntent = new Intent("android.intent.action.MAIN");
        localIntent.addCategory("android.intent.category.HOME");
        localIntent.setFlags(268435456);
        startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        finish();
      }
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968650);
      ((Button)findViewById(2131755326)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_demo_mode.this.finish();
        }
      });
    }
  }
  
  public static class Modal_external_pkg_problem
    extends Modals
  {
    public Modal_external_pkg_problem() {}
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968651);
      paramBundle = getIntent().getAction();
      if ((paramBundle != null) && (!paramBundle.equals("none")) && (!paramBundle.equals(""))) {}
      try
      {
        ((TextView)findViewById(2131755327)).setText(paramBundle);
        ((Button)findViewById(2131755310)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Modals.Modal_external_pkg_problem.this.finish();
          }
        });
        return;
      }
      catch (Exception paramBundle)
      {
        for (;;) {}
      }
    }
  }
  
  public static class Modal_goole_calendar_not_found
    extends Modals
  {
    public Modal_goole_calendar_not_found() {}
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968652);
      ((Button)findViewById(2131755310)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_goole_calendar_not_found.this.finish();
        }
      });
      ((Button)findViewById(2131755328)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            Modals.Modal_goole_calendar_not_found.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.calendar")));
            Modals.Modal_goole_calendar_not_found.this.finish();
            return;
          }
          catch (ActivityNotFoundException paramAnonymousView)
          {
            for (;;)
            {
              Modals.Modal_goole_calendar_not_found.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.calendar")));
            }
          }
        }
      });
    }
  }
  
  public static class Modal_home_button_config
    extends Modals
  {
    int progress_2_glob = 0;
    int progress_glob = 200;
    
    public Modal_home_button_config() {}
    
    protected void onCreate(final Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968653);
      paramBundle = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
      Object localObject = (SeekBar)findViewById(2131755329);
      ((SeekBar)localObject).setProgress(PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getInt("PREF_transparent_widget_conf_btn", 70));
      ((SeekBar)localObject).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
      {
        public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
        {
          Modals.Modal_home_button_config.this.progress_glob = paramAnonymousInt;
        }
        
        public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
        
        public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
        {
          System.out.println(Modals.Modal_home_button_config.this.progress_glob);
          paramAnonymousSeekBar = paramBundle.edit();
          paramAnonymousSeekBar.putInt("PREF_transparent_widget_conf_btn", Modals.Modal_home_button_config.this.progress_glob);
          paramAnonymousSeekBar.commit();
          DataRefresher.getObject(Modals.Modal_home_button_config.this.getBaseContext()).UpdateData(0L, true);
        }
      });
      localObject = (SeekBar)findViewById(2131755331);
      ((SeekBar)localObject).setProgress(PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getInt("PREF_zum_widget_size", 15));
      ((SeekBar)localObject).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
      {
        public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
        {
          Modals.Modal_home_button_config.this.progress_2_glob = paramAnonymousInt;
        }
        
        public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
        
        public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
        {
          paramAnonymousSeekBar = paramBundle.edit();
          paramAnonymousSeekBar.putInt("PREF_zum_widget_size", Modals.Modal_home_button_config.this.progress_2_glob);
          paramAnonymousSeekBar.commit();
          DataRefresher.getObject(Modals.Modal_home_button_config.this.getBaseContext()).UpdateData(0L, true);
        }
      });
      ((Button)findViewById(2131755257)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_home_button_config.this.finish();
        }
      });
      boolean bool = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getBoolean("PREF_icon_widget_conf_btn", false);
      localObject = (Switch)findViewById(2131755330);
      ((Switch)localObject).setChecked(bool);
      ((Switch)localObject).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          paramAnonymousCompoundButton = paramBundle.edit();
          paramAnonymousCompoundButton.putBoolean("PREF_icon_widget_conf_btn", paramAnonymousBoolean);
          paramAnonymousCompoundButton.commit();
          DataRefresher.getObject(Modals.Modal_home_button_config.this.getBaseContext()).UpdateData(0L, true);
        }
      });
    }
  }
  
  public static class Modal_main_widget
    extends Modals
  {
    public Modal_main_widget() {}
    
    public void onBackPressed()
    {
      super.onBackPressed();
      overridePendingTransition(0, 0);
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968654);
      overridePendingTransition(2131034128, 2131034129);
      paramBundle = Typeface.createFromAsset(getAssets(), "fonts/materialIcons-regular.ttf");
      TextView localTextView1 = (TextView)findViewById(2131755334);
      TextView localTextView2 = (TextView)findViewById(2131755336);
      TextView localTextView3 = (TextView)findViewById(2131755338);
      TextView localTextView4 = (TextView)findViewById(2131755340);
      TextView localTextView5 = (TextView)findViewById(2131755342);
      localTextView1.setTypeface(paramBundle);
      localTextView2.setTypeface(paramBundle);
      localTextView3.setTypeface(paramBundle);
      localTextView4.setTypeface(paramBundle);
      localTextView5.setTypeface(paramBundle);
      ((LinearLayout)findViewById(2131755332)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_main_widget.this.finish();
          paramAnonymousView = new Intent(Modals.Modal_main_widget.this, Modals.Modal_home_button_config.class);
          Modals.Modal_main_widget.this.startActivity(paramAnonymousView);
        }
      });
      ((LinearLayout)findViewById(2131755333)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_main_widget.this.finish();
          Modals.Modal_main_widget.this.overridePendingTransition(0, 0);
        }
      });
      ((LinearLayout)findViewById(2131755335)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_main_widget.this, Modals.Modal_widget_button_area_info.class);
          Modals.Modal_main_widget.this.startActivity(paramAnonymousView);
          Modals.Modal_main_widget.this.finish();
        }
      });
      ((LinearLayout)findViewById(2131755337)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_main_widget.this, SettingsActivity.class);
          Modals.Modal_main_widget.this.startActivity(paramAnonymousView);
        }
      });
      ((LinearLayout)findViewById(2131755339)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_main_widget.this, MainActivity.class);
          Modals.Modal_main_widget.this.startActivity(paramAnonymousView);
          Modals.Modal_main_widget.this.finish();
        }
      });
      ((LinearLayout)findViewById(2131755341)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          long l = System.currentTimeMillis();
          paramAnonymousView = Calendar.getInstance(Locale.getDefault());
          paramAnonymousView.setTimeInMillis(l);
          paramAnonymousView.set(12, 0);
          paramAnonymousView.set(13, 0);
          paramAnonymousView.set(14, 0);
          l = paramAnonymousView.getTimeInMillis();
          try
          {
            paramAnonymousView = new Intent("android.intent.action.INSERT");
            paramAnonymousView.setData(CalendarContract.Events.CONTENT_URI);
            paramAnonymousView.putExtra("beginTime", l);
            paramAnonymousView.putExtra("endTime", 3600000L + l);
            Modals.Modal_main_widget.this.startActivity(paramAnonymousView);
            Modals.Modal_main_widget.this.finish();
            return;
          }
          catch (Exception paramAnonymousView)
          {
            for (;;)
            {
              paramAnonymousView = new Intent(Modals.Modal_main_widget.this, Modals.Modal_goole_calendar_not_found.class);
              Modals.Modal_main_widget.this.startActivity(paramAnonymousView);
            }
          }
        }
      });
    }
  }
  
  public static class Modal_rate_alert
    extends Modals
  {
    public Modal_rate_alert() {}
    
    public void onBackPressed()
    {
      finish();
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968655);
      ((Button)findViewById(2131755343)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_rate_alert.this, Modals.Modal_rate_alert_good.class);
          Modals.Modal_rate_alert.this.startActivity(paramAnonymousView);
          try
          {
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                Modals.Modal_rate_alert.this.finish();
              }
            }, 300L);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            Modals.Modal_rate_alert.this.finish();
          }
        }
      });
      ((Button)findViewById(2131755344)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_rate_alert.this, Modals.Modal_rate_alert_good.class);
          Modals.Modal_rate_alert.this.startActivity(paramAnonymousView);
          try
          {
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                Modals.Modal_rate_alert.this.finish();
              }
            }, 300L);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            Modals.Modal_rate_alert.this.finish();
          }
        }
      });
      ((Button)findViewById(2131755345)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_rate_alert.this, Modals.Modal_rate_alert_good.class);
          Modals.Modal_rate_alert.this.startActivity(paramAnonymousView);
          try
          {
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                Modals.Modal_rate_alert.this.finish();
              }
            }, 300L);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            Modals.Modal_rate_alert.this.finish();
          }
        }
      });
      ((Button)findViewById(2131755346)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_rate_alert.this, Modals.Modal_rate_alert_bad.class);
          Modals.Modal_rate_alert.this.startActivity(paramAnonymousView);
          try
          {
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                Modals.Modal_rate_alert.this.finish();
              }
            }, 300L);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            Modals.Modal_rate_alert.this.finish();
          }
        }
      });
      ((Button)findViewById(2131755347)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(Modals.Modal_rate_alert.this, Modals.Modal_rate_alert_bad.class);
          Modals.Modal_rate_alert.this.startActivity(paramAnonymousView);
          try
          {
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                Modals.Modal_rate_alert.this.finish();
              }
            }, 300L);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            Modals.Modal_rate_alert.this.finish();
          }
        }
      });
    }
  }
  
  public static class Modal_rate_alert_bad
    extends Modals
  {
    public Modal_rate_alert_bad() {}
    
    public void onBackPressed()
    {
      finish();
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968656);
      ((Button)findViewById(2131755348)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_rate_alert_bad.this.finish();
        }
      });
      ((Button)findViewById(2131755349)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = Modals.Modal_rate_alert_bad.this.getResources().getString(2131296436);
          String str1 = Modals.Modal_rate_alert_bad.this.getResources().getString(2131296427);
          String str2 = Modals.Modal_rate_alert_bad.this.getResources().getString(2131296426);
          String str3 = Utils.get_version_app(Modals.Modal_rate_alert_bad.this.getBaseContext());
          Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "27applab@gmail.com", null));
          localIntent.putExtra("android.intent.extra.SUBJECT", str1 + " v." + str3);
          localIntent.putExtra("android.intent.extra.TEXT", str2);
          Modals.Modal_rate_alert_bad.this.startActivity(Intent.createChooser(localIntent, paramAnonymousView));
          Modals.Modal_rate_alert_bad.this.finish();
        }
      });
    }
  }
  
  public static class Modal_rate_alert_good
    extends Modals
  {
    public Modal_rate_alert_good() {}
    
    public void onBackPressed()
    {
      finish();
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968657);
      ((Button)findViewById(2131755348)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Modals.Modal_rate_alert_good.this.finish();
        }
      });
      ((Button)findViewById(2131755350)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = Modals.Modal_rate_alert_good.this.getPackageName();
          try
          {
            Modals.Modal_rate_alert_good.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramAnonymousView)));
            Modals.Modal_rate_alert_good.this.finish();
            return;
          }
          catch (ActivityNotFoundException localActivityNotFoundException)
          {
            for (;;)
            {
              Modals.Modal_rate_alert_good.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramAnonymousView)));
            }
          }
        }
      });
    }
  }
  
  public static class Modal_support_donate
    extends Modals
  {
    IInAppBillingService inAppBillingService;
    ServiceConnection serviceConnection = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        Modals.Modal_support_donate.this.inAppBillingService = IInAppBillingService.Stub.asInterface(paramAnonymousIBinder);
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              List localList1 = Billing.getInAppPurchases(Modals.Modal_support_donate.this.getBaseContext(), Modals.Modal_support_donate.this.inAppBillingService, "inapp", new String[] { "donate_beer_billing_ind" });
              List localList2 = Billing.getInAppPurchases(Modals.Modal_support_donate.this.getBaseContext(), Modals.Modal_support_donate.this.inAppBillingService, "inapp", new String[] { "donate_cake_billing_ind" });
              List localList3 = Billing.getInAppPurchases(Modals.Modal_support_donate.this.getBaseContext(), Modals.Modal_support_donate.this.inAppBillingService, "inapp", new String[] { "donate_dinner_billing_ind" });
              Utils.sendStringExtraData("donate_beer_price", ((Billing)localList1.get(0)).price.toString(), Modals.Modal_support_donate.this.getBaseContext());
              Utils.sendStringExtraData("donate_cake_price", ((Billing)localList2.get(0)).price.toString(), Modals.Modal_support_donate.this.getBaseContext());
              Utils.sendStringExtraData("donate_dinner_price", ((Billing)localList3.get(0)).price.toString(), Modals.Modal_support_donate.this.getBaseContext());
              this.val$mHandler2.post(new Runnable()
              {
                public void run()
                {
                  Utils.getStringExtraData("donate_beer_price", Modals.Modal_support_donate.this.getBaseContext());
                  String str1 = Utils.getStringExtraData("donate_cake_price", Modals.Modal_support_donate.this.getBaseContext());
                  Utils.getStringExtraData("donate_dinner_price", Modals.Modal_support_donate.this.getBaseContext());
                  Modals.Modal_support_donate.this.getResources().getString(2131296310);
                  String str2 = Modals.Modal_support_donate.this.getResources().getString(2131296311);
                  Modals.Modal_support_donate.this.getResources().getString(2131296312);
                  Typeface localTypeface = Typeface.createFromAsset(Modals.Modal_support_donate.this.getAssets(), "fonts/roboto-regular.ttf");
                  TextView localTextView = (TextView)Modals.Modal_support_donate.this.findViewById(2131755360);
                  try
                  {
                    localTextView.setTypeface(localTypeface);
                    localTextView.setText(str2 + " " + str1);
                    Modals.Modal_support_donate.this.findViewById(2131755361).setVisibility(8);
                    return;
                  }
                  catch (Exception localException)
                  {
                    for (;;) {}
                  }
                }
              });
              return;
            }
            catch (Exception localException)
            {
              for (;;) {}
            }
          }
        }).start();
        ((LinearLayout)Modals.Modal_support_donate.this.findViewById(2131755359)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            try
            {
              Modals.Modal_support_donate.this.purchaseProduct(Modals.Modal_support_donate.this.getBaseContext(), Modals.Modal_support_donate.this.inAppBillingService, "inapp", "donate_cake_billing_ind");
              return;
            }
            catch (Exception paramAnonymous2View)
            {
              paramAnonymous2View.printStackTrace();
            }
          }
        });
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        Modals.Modal_support_donate.this.inAppBillingService = null;
      }
    };
    
    public Modal_support_donate() {}
    
    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      if (paramInt1 == 1234) {
        if (paramIntent.getIntExtra("RESPONSE_CODE", -1) == 0)
        {
          Object localObject = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
          paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");
          paramIntent = Billing.readPurchase((String)localObject);
          if (paramIntent[4].equals("0"))
          {
            Log.w("Modal_support_donate", "DONATE it is TRUE. token: " + paramIntent[6]);
            new Thread(new Runnable()
            {
              public void run()
              {
                try
                {
                  Billing.consumePurchase(Modals.Modal_support_donate.this.getBaseContext(), Modals.Modal_support_donate.this.inAppBillingService, this.val$token_str);
                  this.val$mHandler2.post(new Runnable()
                  {
                    public void run() {}
                  });
                  return;
                }
                catch (Exception localException)
                {
                  for (;;)
                  {
                    localException.printStackTrace();
                  }
                }
              }
            }).start();
            localObject = new Intent(this, Modals.Modal_thanks_for_donate.class);
            ((Intent)localObject).putExtra("thanks_text", paramIntent[2]);
            startActivity((Intent)localObject);
          }
        }
      }
      for (;;)
      {
        try
        {
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              Modals.Modal_support_donate.this.finish();
            }
          }, 200L);
          return;
        }
        catch (Exception paramIntent)
        {
          finish();
        }
        System.out.println("onActivityResult BILLING_RESPONSE_RESULT_OK false");
        continue;
        System.out.println("onActivityResult NOT ISSET");
      }
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968659);
      paramBundle = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      paramBundle.setPackage("com.android.vending");
      bindService(paramBundle, this.serviceConnection, 1);
      ((LinearLayout)findViewById(2131755354)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = Modals.Modal_support_donate.this.getPackageName();
          try
          {
            Modals.Modal_support_donate.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramAnonymousView)));
            Modals.Modal_support_donate.this.finish();
            return;
          }
          catch (ActivityNotFoundException localActivityNotFoundException)
          {
            for (;;)
            {
              Modals.Modal_support_donate.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramAnonymousView)));
            }
          }
        }
      });
      ((LinearLayout)findViewById(2131755355)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "27applab@gmail.com", null));
          String str = Utils.get_version_app(Modals.Modal_support_donate.this.getBaseContext());
          paramAnonymousView.putExtra("android.intent.extra.SUBJECT", "Sectograph v." + str);
          str = Modals.Modal_support_donate.this.getResources().getString(2131296396);
          Modals.Modal_support_donate.this.startActivity(Intent.createChooser(paramAnonymousView, str));
          Modals.Modal_support_donate.this.finish();
        }
      });
      ((LinearLayout)findViewById(2131755356)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          String str = Modals.Modal_support_donate.this.getPackageName();
          paramAnonymousView = new Intent("android.intent.action.SEND");
          paramAnonymousView.setType("text/plain");
          str = "Sectograph http://play.google.com/store/apps/details?id=" + str;
          paramAnonymousView.putExtra("android.intent.extra.SUBJECT", "Sectograph");
          paramAnonymousView.putExtra("android.intent.extra.TEXT", str);
          str = Modals.Modal_support_donate.this.getResources().getString(2131296397);
          Modals.Modal_support_donate.this.startActivity(Intent.createChooser(paramAnonymousView, str));
          Modals.Modal_support_donate.this.finish();
        }
      });
      ((LinearLayout)findViewById(2131755357)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (LinearLayout)Modals.Modal_support_donate.this.findViewById(2131755358);
          if (paramAnonymousView.getVisibility() == 8)
          {
            paramAnonymousView.setVisibility(0);
            return;
          }
          paramAnonymousView.setVisibility(8);
        }
      });
    }
    
    public void onDestroy()
    {
      super.onDestroy();
      if (this.serviceConnection != null) {}
      try
      {
        unbindService(this.serviceConnection);
        return;
      }
      catch (Exception localException) {}
    }
    
    public void onStop()
    {
      super.onStop();
      if (this.serviceConnection != null) {}
      try
      {
        unbindService(this.serviceConnection);
        return;
      }
      catch (Exception localException) {}
    }
    
    public void purchaseProduct(Context paramContext, IInAppBillingService paramIInAppBillingService, String paramString1, String paramString2)
      throws Exception
    {
      paramContext = (PendingIntent)paramIInAppBillingService.getBuyIntent(3, paramContext.getPackageName(), paramString2, paramString1, "12345").getParcelable("BUY_INTENT");
      if (Build.VERSION.SDK_INT >= 16) {
        startIntentSenderForResult(paramContext.getIntentSender(), 1234, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), null);
      }
    }
  }
  
  public static class Modal_thanks_for_donate
    extends Modals
  {
    public Modal_thanks_for_donate() {}
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setContentView(2130968660);
      paramBundle = (TextView)findViewById(2131755362);
      String str = getIntent().getStringExtra("thanks_text");
      if (str.equals("donate_beer_billing_ind")) {
        paramBundle.setText(getResources().getString(2131296306));
      }
      do
      {
        return;
        if (str.equals("donate_cake_billing_ind"))
        {
          paramBundle.setText(getResources().getString(2131296307));
          return;
        }
      } while (!str.equals("donate_dinner_billing_ind"));
      paramBundle.setText(getResources().getString(2131296308));
    }
  }
  
  public static class Modal_widget_button_area_info
    extends Modals
  {
    public Modal_widget_button_area_info() {}
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      Utils.setTheme(this);
      setContentView(2130968661);
      int i = PreferenceManager.getDefaultSharedPreferences(this).getInt("pref_click_areas_count", 2);
      if (i == 2) {
        ((RadioButton)findViewById(2131755363)).setChecked(true);
      }
      for (;;)
      {
        ((Button)findViewById(2131755366)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = PreferenceManager.getDefaultSharedPreferences(Modals.Modal_widget_button_area_info.this.getBaseContext()).edit();
            paramAnonymousView.putBoolean("pref_click_areas_mode", true);
            paramAnonymousView.commit();
            DataRefresher.getObject(Modals.Modal_widget_button_area_info.this.getBaseContext()).UpdateData(0L, true);
            Modals.Modal_widget_button_area_info.this.finish();
          }
        });
        return;
        if (i == 5) {
          ((RadioButton)findViewById(2131755364)).setChecked(true);
        } else {
          ((RadioButton)findViewById(2131755365)).setChecked(true);
        }
      }
    }
    
    public void onRadioButtonClicked(View paramView)
    {
      boolean bool = ((RadioButton)paramView).isChecked();
      SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
      switch (paramView.getId())
      {
      }
      do
      {
        do
        {
          do
          {
            return;
          } while (!bool);
          localEditor.putInt("pref_click_areas_count", 2);
          localEditor.commit();
          return;
        } while (!bool);
        localEditor.putInt("pref_click_areas_count", 5);
        localEditor.commit();
        return;
      } while (!bool);
      localEditor.putInt("pref_click_areas_count", 1);
      localEditor.commit();
    }
  }
  
  public static class Modal_widget_button_config
    extends Modals
  {
    public String CLECKED_BUTTON = "";
    
    public Modal_widget_button_config() {}
    
    private void showRadioButtonDialog(final Context paramContext, final ProgressBar paramProgressBar, final String paramString)
    {
      final Dialog localDialog = new Dialog(this);
      localDialog.requestWindowFeature(1);
      localDialog.setContentView(2130968632);
      final RadioGroup localRadioGroup = (RadioGroup)localDialog.findViewById(2131755280);
      localDialog.setOnShowListener(new DialogInterface.OnShowListener()
      {
        public void onShow(DialogInterface paramAnonymousDialogInterface)
        {
          paramProgressBar.setVisibility(4);
          paramProgressBar.setIndeterminate(false);
        }
      });
      new Thread(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 37	laboratory27/sectograph/Modals$Modal_widget_button_config$5:this$0	Llaboratory27/sectograph/Modals$Modal_widget_button_config;
          //   4: invokevirtual 62	laboratory27/sectograph/Modals$Modal_widget_button_config:getPackageManager	()Landroid/content/pm/PackageManager;
          //   7: astore_2
          //   8: aload_2
          //   9: ifnull +229 -> 238
          //   12: aload_2
          //   13: sipush 128
          //   16: invokevirtual 68	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
          //   19: astore_1
          //   20: aload_1
          //   21: new 70	android/content/pm/ApplicationInfo$DisplayNameComparator
          //   24: dup
          //   25: aload_2
          //   26: invokespecial 73	android/content/pm/ApplicationInfo$DisplayNameComparator:<init>	(Landroid/content/pm/PackageManager;)V
          //   29: invokestatic 79	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
          //   32: aload_1
          //   33: ifnull +155 -> 188
          //   36: aload_1
          //   37: invokeinterface 85 1 0
          //   42: astore_2
          //   43: aload_2
          //   44: invokeinterface 91 1 0
          //   49: ifeq +120 -> 169
          //   52: aload_2
          //   53: invokeinterface 95 1 0
          //   58: checkcast 97	android/content/pm/ApplicationInfo
          //   61: astore_3
          //   62: new 99	android/widget/RadioButton
          //   65: dup
          //   66: aload_0
          //   67: getfield 39	laboratory27/sectograph/Modals$Modal_widget_button_config$5:val$this_ctx	Landroid/content/Context;
          //   70: invokespecial 102	android/widget/RadioButton:<init>	(Landroid/content/Context;)V
          //   73: astore 4
          //   75: aload 4
          //   77: aload_3
          //   78: aload_0
          //   79: getfield 37	laboratory27/sectograph/Modals$Modal_widget_button_config$5:this$0	Llaboratory27/sectograph/Modals$Modal_widget_button_config;
          //   82: invokevirtual 62	laboratory27/sectograph/Modals$Modal_widget_button_config:getPackageManager	()Landroid/content/pm/PackageManager;
          //   85: invokevirtual 106	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
          //   88: invokeinterface 112 1 0
          //   93: invokevirtual 116	android/widget/RadioButton:setText	(Ljava/lang/CharSequence;)V
          //   96: aload_0
          //   97: getfield 41	laboratory27/sectograph/Modals$Modal_widget_button_config$5:val$rg	Landroid/widget/RadioGroup;
          //   100: aload 4
          //   102: invokevirtual 122	android/widget/RadioGroup:addView	(Landroid/view/View;)V
          //   105: goto -62 -> 43
          //   108: astore_1
          //   109: aload_0
          //   110: getfield 43	laboratory27/sectograph/Modals$Modal_widget_button_config$5:val$context	Landroid/content/Context;
          //   113: new 124	java/lang/StringBuilder
          //   116: dup
          //   117: invokespecial 125	java/lang/StringBuilder:<init>	()V
          //   120: ldc 127
          //   122: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   125: aload_0
          //   126: getfield 37	laboratory27/sectograph/Modals$Modal_widget_button_config$5:this$0	Llaboratory27/sectograph/Modals$Modal_widget_button_config;
          //   129: ldc -124
          //   131: invokevirtual 136	laboratory27/sectograph/Modals$Modal_widget_button_config:getString	(I)Ljava/lang/String;
          //   134: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   137: ldc -118
          //   139: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   142: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   145: iconst_0
          //   146: invokestatic 145	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
          //   149: invokevirtual 148	android/widget/Toast:show	()V
          //   152: aload_0
          //   153: getfield 49	laboratory27/sectograph/Modals$Modal_widget_button_config$5:val$mHandler	Landroid/os/Handler;
          //   156: new 18	laboratory27/sectograph/Modals$Modal_widget_button_config$5$2
          //   159: dup
          //   160: aload_0
          //   161: invokespecial 151	laboratory27/sectograph/Modals$Modal_widget_button_config$5$2:<init>	(Llaboratory27/sectograph/Modals$Modal_widget_button_config$5;)V
          //   164: invokevirtual 157	android/os/Handler:post	(Ljava/lang/Runnable;)Z
          //   167: pop
          //   168: return
          //   169: aload_0
          //   170: getfield 41	laboratory27/sectograph/Modals$Modal_widget_button_config$5:val$rg	Landroid/widget/RadioGroup;
          //   173: new 16	laboratory27/sectograph/Modals$Modal_widget_button_config$5$1
          //   176: dup
          //   177: aload_0
          //   178: aload_1
          //   179: invokespecial 160	laboratory27/sectograph/Modals$Modal_widget_button_config$5$1:<init>	(Llaboratory27/sectograph/Modals$Modal_widget_button_config$5;Ljava/util/List;)V
          //   182: invokevirtual 164	android/widget/RadioGroup:setOnCheckedChangeListener	(Landroid/widget/RadioGroup$OnCheckedChangeListener;)V
          //   185: goto -33 -> 152
          //   188: aload_0
          //   189: getfield 43	laboratory27/sectograph/Modals$Modal_widget_button_config$5:val$context	Landroid/content/Context;
          //   192: new 124	java/lang/StringBuilder
          //   195: dup
          //   196: invokespecial 125	java/lang/StringBuilder:<init>	()V
          //   199: ldc 127
          //   201: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   204: aload_0
          //   205: getfield 37	laboratory27/sectograph/Modals$Modal_widget_button_config$5:this$0	Llaboratory27/sectograph/Modals$Modal_widget_button_config;
          //   208: ldc -124
          //   210: invokevirtual 136	laboratory27/sectograph/Modals$Modal_widget_button_config:getString	(I)Ljava/lang/String;
          //   213: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   216: ldc -118
          //   218: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   221: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   224: iconst_0
          //   225: invokestatic 145	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
          //   228: invokevirtual 148	android/widget/Toast:show	()V
          //   231: goto -79 -> 152
          //   234: astore_1
          //   235: goto -83 -> 152
          //   238: aload_0
          //   239: getfield 43	laboratory27/sectograph/Modals$Modal_widget_button_config$5:val$context	Landroid/content/Context;
          //   242: new 124	java/lang/StringBuilder
          //   245: dup
          //   246: invokespecial 125	java/lang/StringBuilder:<init>	()V
          //   249: ldc 127
          //   251: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   254: aload_0
          //   255: getfield 37	laboratory27/sectograph/Modals$Modal_widget_button_config$5:this$0	Llaboratory27/sectograph/Modals$Modal_widget_button_config;
          //   258: ldc -124
          //   260: invokevirtual 136	laboratory27/sectograph/Modals$Modal_widget_button_config:getString	(I)Ljava/lang/String;
          //   263: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   266: ldc -118
          //   268: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   271: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   274: iconst_0
          //   275: invokestatic 145	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
          //   278: invokevirtual 148	android/widget/Toast:show	()V
          //   281: goto -129 -> 152
          //   284: astore_1
          //   285: goto -133 -> 152
          //   288: astore_1
          //   289: goto -137 -> 152
          //   292: astore_2
          //   293: goto -261 -> 32
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	296	0	this	5
          //   19	18	1	localList	List
          //   108	71	1	localException1	Exception
          //   234	1	1	localException2	Exception
          //   284	1	1	localException3	Exception
          //   288	1	1	localException4	Exception
          //   7	46	2	localObject	Object
          //   292	1	2	localException5	Exception
          //   61	17	3	localApplicationInfo	ApplicationInfo
          //   73	28	4	localRadioButton	RadioButton
          // Exception table:
          //   from	to	target	type
          //   0	8	108	java/lang/Exception
          //   12	20	108	java/lang/Exception
          //   36	43	108	java/lang/Exception
          //   43	105	108	java/lang/Exception
          //   169	185	108	java/lang/Exception
          //   188	231	234	java/lang/Exception
          //   238	281	284	java/lang/Exception
          //   109	152	288	java/lang/Exception
          //   20	32	292	java/lang/Exception
        }
      }).start();
    }
    
    protected void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      Utils.setTheme(this);
      setContentView(2130968662);
      Object localObject = "";
      final SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
      final String str2 = getIntent().getAction();
      paramBundle = (Bundle)localObject;
      int i;
      if (!str2.equals(""))
      {
        paramBundle = (Bundle)localObject;
        if (str2 != null)
        {
          paramBundle = localSharedPreferences.getString(str2, "none");
          localObject = localSharedPreferences.getString(str2 + "_OTHER_TEXT", "none");
          i = -1;
          switch (paramBundle.hashCode())
          {
          default: 
            paramBundle = (Bundle)localObject;
            switch (i)
            {
            default: 
              paramBundle = (Bundle)localObject;
            }
            break;
          }
        }
      }
      for (;;)
      {
        if (!localSharedPreferences.getBoolean(ConfigClass.MODE_24_PAY_PREFERENCE_KEY, false))
        {
          localObject = (RadioButton)findViewById(2131755372);
          ((RadioButton)localObject).setAlpha(0.3F);
          ((RadioButton)localObject).setClickable(false);
        }
        final ProgressBar localProgressBar = (ProgressBar)findViewById(2131755376);
        ListView localListView = (ListView)findViewById(2131755375);
        String str1 = getResources().getString(2131296446);
        localObject = str1;
        if (!paramBundle.equals(""))
        {
          localObject = str1;
          if (paramBundle != null)
          {
            localObject = str1;
            if (!paramBundle.equals("none")) {
              localObject = paramBundle;
            }
          }
        }
        localListView.setAdapter(new ArrayAdapter(this, 17367043, new String[] { localObject }));
        localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            switch (paramAnonymousInt)
            {
            default: 
              return;
            }
            localProgressBar.setVisibility(0);
            localProgressBar.setIndeterminate(true);
            Modals.Modal_widget_button_config.this.showRadioButtonDialog(Modals.Modal_widget_button_config.this.getBaseContext(), localProgressBar, str2);
          }
        });
        ((Button)findViewById(2131755377)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            DataRefresher.getObject(Modals.Modal_widget_button_config.this.getBaseContext()).UpdateData(0L, true);
            Modals.Modal_widget_button_config.this.finish();
          }
        });
        ((Button)findViewById(2131755378)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            try
            {
              paramAnonymousView = localSharedPreferences.edit();
              paramAnonymousView.putBoolean("pref_click_areas_mode", false);
              paramAnonymousView.commit();
              DataRefresher.getObject(Modals.Modal_widget_button_config.this.getBaseContext()).UpdateData(0L, true);
              Modals.Modal_widget_button_config.this.finish();
              return;
            }
            catch (Exception paramAnonymousView)
            {
              for (;;) {}
            }
          }
        });
        return;
        if (!paramBundle.equals("none")) {
          break;
        }
        i = 0;
        break;
        if (!paramBundle.equals("main_activity")) {
          break;
        }
        i = 1;
        break;
        if (!paramBundle.equals("open_calendar")) {
          break;
        }
        i = 2;
        break;
        if (!paramBundle.equals("time_plus")) {
          break;
        }
        i = 3;
        break;
        if (!paramBundle.equals("time_minus")) {
          break;
        }
        i = 4;
        break;
        if (!paramBundle.equals("time_reset")) {
          break;
        }
        i = 5;
        break;
        if (!paramBundle.equals("add_event")) {
          break;
        }
        i = 6;
        break;
        if (!paramBundle.equals("12_24_mode")) {
          break;
        }
        i = 7;
        break;
        if (!paramBundle.equals("other_action")) {
          break;
        }
        i = 8;
        break;
        ((RadioButton)findViewById(2131755367)).setChecked(true);
        paramBundle = (Bundle)localObject;
        continue;
        ((RadioButton)findViewById(2131755368)).setChecked(true);
        paramBundle = (Bundle)localObject;
        continue;
        ((RadioButton)findViewById(2131755369)).setChecked(true);
        paramBundle = (Bundle)localObject;
        continue;
        ((RadioButton)findViewById(2131755370)).setChecked(true);
        paramBundle = (Bundle)localObject;
        continue;
        ((RadioButton)findViewById(2131755371)).setChecked(true);
        paramBundle = (Bundle)localObject;
        continue;
        ((RadioButton)findViewById(2131755372)).setChecked(true);
        paramBundle = (Bundle)localObject;
        continue;
        ((RadioButton)findViewById(2131755373)).setChecked(true);
        ((LinearLayout)findViewById(2131755374)).setVisibility(0);
        paramBundle = (Bundle)localObject;
      }
    }
    
    public void onRadioButtonClicked(View paramView)
    {
      LinearLayout localLinearLayout = (LinearLayout)findViewById(2131755374);
      localLinearLayout.setVisibility(8);
      String str = getIntent().getAction();
      SharedPreferences.Editor localEditor;
      boolean bool;
      if ((!str.equals("")) && (str != null))
      {
        localEditor = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
        bool = ((RadioButton)paramView).isChecked();
        switch (paramView.getId())
        {
        }
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return;
                  } while (!bool);
                  localEditor.putString(str, "none");
                  localEditor.commit();
                  return;
                } while (!bool);
                localEditor.putString(str, "main_activity");
                localEditor.commit();
                return;
              } while (!bool);
              localEditor.putString(str, "open_calendar");
              localEditor.commit();
              return;
            } while (!bool);
            localEditor.putString(str, "time_reset");
            localEditor.commit();
            return;
          } while (!bool);
          localEditor.putString(str, "add_event");
          localEditor.commit();
          return;
        } while (!bool);
        localEditor.putString(str, "12_24_mode");
        localEditor.commit();
        return;
      } while (!bool);
      localEditor.putString(str, "other_action");
      localEditor.commit();
      localLinearLayout.setVisibility(0);
    }
  }
}
