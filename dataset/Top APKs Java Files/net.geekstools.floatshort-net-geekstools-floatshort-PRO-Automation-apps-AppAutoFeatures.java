package net.geekstools.floatshort.PRO.Automation.apps;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.geekstools.floatshort.PRO.Automation.categories.CategoryAutoFeatures;
import net.geekstools.floatshort.PRO.BindServices;
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClass;
import net.geekstools.floatshort.PRO.Util.Functions.PublicVariable;
import net.geekstools.floatshort.PRO.Util.NavAdapter.NavDrawerItem;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterFull;
import net.geekstools.floatshort.PRO.Util.UI.SimpleGestureFilterFull.SimpleGestureListener;

public class AppAutoFeatures
  extends AppCompatActivity
  implements View.OnClickListener, SimpleGestureFilterFull.SimpleGestureListener
{
  Drawable AppIcon;
  String AppName = "Application";
  String AppTime = "00:00";
  RelativeLayout MainView;
  String PackageName;
  Activity activity;
  ListView acttionElementsList;
  AppAutoListAdapter adapter;
  List<ApplicationInfo> applicationInfoList;
  Button autoApps;
  Button autoCategories;
  LinearLayout autoIdentifier;
  Button bluetooth;
  int color;
  TextView desc;
  RelativeLayout fullActionButton;
  FunctionsClass functionsClass;
  Button gps;
  LinearLayout indexView;
  ListView listView;
  ProgressBar loadingBarLTR;
  RelativeLayout loadingSplash;
  Map<String, Integer> mapIndex;
  ArrayList<NavDrawerItem> navDrawerItems;
  Button nfc;
  int pressColor;
  SimpleGestureFilterFull simpleGestureFilterFull;
  Button time;
  Button wifi;
  
  public AppAutoFeatures() {}
  
  public void autoBluetooth()
  {
    PublicVariable.autoID = getString(2131624003);
    if ((Build.VERSION.SDK_INT > 22) && (checkSelfPermission("android.permission.BLUETOOTH") == -1))
    {
      requestPermissions(new String[] { "android.permission.BLUETOOTH" }, 999);
      return;
    }
    new LoadAutoApplications(null).execute(new Void[0]);
  }
  
  public void autoGPS()
  {
    PublicVariable.autoID = getString(2131624077);
    if ((Build.VERSION.SDK_INT > 22) && (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == -1))
    {
      requestPermissions(new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 777);
      return;
    }
    new LoadAutoApplications(null).execute(new Void[0]);
  }
  
  public void autoNfc()
  {
    PublicVariable.autoID = getString(2131624113);
    if ((Build.VERSION.SDK_INT > 22) && (checkSelfPermission("android.permission.NFC") == -1))
    {
      requestPermissions(new String[] { "android.permission.NFC" }, 888);
      return;
    }
    new LoadAutoApplications(null).execute(new Void[0]);
  }
  
  public void autoTime()
  {
    PublicVariable.autoID = getString(2131624210);
    new LoadAutoApplications(null).execute(new Void[0]);
  }
  
  public void autoWiFi()
  {
    PublicVariable.autoID = getString(2131624231);
    if ((Build.VERSION.SDK_INT > 22) && (checkSelfPermission("android.permission.ACCESS_WIFI_STATE") == -1))
    {
      requestPermissions(new String[] { "android.permission.ACCESS_WIFI_STATE" }, 888);
      return;
    }
    new LoadAutoApplications(null).execute(new Void[0]);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    this.simpleGestureFilterFull.onTouchEvent(paramMotionEvent);
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void onBackPressed()
  {
    try
    {
      this.functionsClass.overrideBackPress(this);
      overridePendingTransition(17432576, 2130771986);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void onClick(final View paramView)
  {
    if ((paramView instanceof TextView))
    {
      paramView = (TextView)paramView;
      this.listView.post(new Runnable()
      {
        public void run()
        {
          AppAutoFeatures.this.listView.smoothScrollToPositionFromTop(((Integer)AppAutoFeatures.this.mapIndex.get(paramView.getText().toString())).intValue(), 0, 200);
        }
      });
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427357);
    this.listView = ((ListView)findViewById(2131296476));
    this.indexView = ((LinearLayout)findViewById(2131296583));
    this.autoIdentifier = ((LinearLayout)findViewById(2131296307));
    this.autoIdentifier.bringToFront();
    this.MainView = ((RelativeLayout)findViewById(2131296260));
    this.fullActionButton = ((RelativeLayout)findViewById(2131296420));
    this.acttionElementsList = ((ListView)findViewById(2131296286));
    this.autoApps = ((Button)findViewById(2131296302));
    this.autoApps.bringToFront();
    this.autoCategories = ((Button)findViewById(2131296303));
    this.autoCategories.bringToFront();
    this.wifi = ((Button)findViewById(2131296675));
    this.bluetooth = ((Button)findViewById(2131296320));
    this.gps = ((Button)findViewById(2131296427));
    this.nfc = ((Button)findViewById(2131296497));
    this.time = ((Button)findViewById(2131296640));
    this.simpleGestureFilterFull = new SimpleGestureFilterFull(getApplicationContext(), this);
    this.functionsClass = new FunctionsClass(getApplicationContext(), this, getClass().getSimpleName());
    this.activity = this;
    if ((this.functionsClass.returnAPI() >= 26) && (!this.functionsClass.ControlPanel()))
    {
      paramBundle = this.MainView;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("<big>");
      ((StringBuilder)localObject1).append(getString(2131624060));
      ((StringBuilder)localObject1).append("</big>");
      paramBundle = Snackbar.make(paramBundle, Html.fromHtml(((StringBuilder)localObject1).toString()), -2);
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("<b>");
      ((StringBuilder)localObject1).append(getString(2131624059).toUpperCase());
      ((StringBuilder)localObject1).append("</b>");
      paramBundle = paramBundle.setAction(Html.fromHtml(((StringBuilder)localObject1).toString()), new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = PreferenceManager.getDefaultSharedPreferences(AppAutoFeatures.this.getApplicationContext()).edit();
          paramAnonymousView.putBoolean("stable", true);
          paramAnonymousView.apply();
          AppAutoFeatures.this.startService(new Intent(AppAutoFeatures.this.getApplicationContext(), BindServices.class));
        }
      });
      paramBundle.setActionTextColor(PublicVariable.colorLightDarkOpposite);
      paramBundle.setActionTextColor(getResources().getColor(2131099763));
      localObject1 = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[] { 0, this.functionsClass.setColorAlpha(PublicVariable.primaryColor, 207.0F), 0 });
      paramBundle.getView().setBackground((Drawable)localObject1);
      paramBundle.show();
    }
    if (this.functionsClass.appThemeTransparent() == true) {
      this.functionsClass.setThemeColorAuto(this.MainView, true);
    } else {
      this.functionsClass.setThemeColorAuto(this.MainView, false);
    }
    this.navDrawerItems = new ArrayList();
    this.mapIndex = new LinkedHashMap();
    this.autoApps.setTextColor(getResources().getColor(2131099732));
    this.autoCategories.setTextColor(getResources().getColor(2131099732));
    if ((PublicVariable.themeLightDark) && (this.functionsClass.appThemeTransparent()))
    {
      this.autoApps.setTextColor(getResources().getColor(2131099703));
      this.autoCategories.setTextColor(getResources().getColor(2131099703));
    }
    Object localObject1 = (RippleDrawable)getResources().getDrawable(2131230900);
    Object localObject2 = (GradientDrawable)((RippleDrawable)localObject1).findDrawableByLayerId(2131296413);
    paramBundle = (GradientDrawable)((RippleDrawable)localObject1).findDrawableByLayerId(2131296311);
    GradientDrawable localGradientDrawable = (GradientDrawable)((RippleDrawable)localObject1).findDrawableByLayerId(16908334);
    if (this.functionsClass.appThemeTransparent())
    {
      ((RippleDrawable)localObject1).setColor(ColorStateList.valueOf(PublicVariable.primaryColorOpposite));
      localObject3 = this.functionsClass;
      int i = this.functionsClass.mixColors(PublicVariable.primaryColor, PublicVariable.colorLightDark, 0.75F);
      float f;
      if (this.functionsClass.wallpaperStaticLive()) {
        f = 245.0F;
      } else {
        f = 113.0F;
      }
      ((GradientDrawable)localObject2).setColor(((FunctionsClass)localObject3).setColorAlpha(i, f));
      localObject2 = this.functionsClass;
      i = PublicVariable.primaryColor;
      if (this.functionsClass.wallpaperStaticLive()) {
        f = 150.0F;
      } else {
        f = 155.0F;
      }
      paramBundle.setTint(((FunctionsClass)localObject2).setColorAlpha(i, f));
      localGradientDrawable.setColor(PublicVariable.primaryColorOpposite);
    }
    else
    {
      ((RippleDrawable)localObject1).setColor(ColorStateList.valueOf(PublicVariable.primaryColorOpposite));
      ((GradientDrawable)localObject2).setColor(PublicVariable.primaryColor);
      paramBundle.setTint(PublicVariable.primaryColor);
      localGradientDrawable.setColor(PublicVariable.primaryColorOpposite);
    }
    this.autoApps.setBackground((Drawable)localObject1);
    localObject1 = (RippleDrawable)getResources().getDrawable(2131230878);
    localGradientDrawable = (GradientDrawable)((RippleDrawable)localObject1).findDrawableByLayerId(2131296413);
    localObject2 = (GradientDrawable)((RippleDrawable)localObject1).findDrawableByLayerId(2131296311);
    Object localObject3 = (GradientDrawable)((RippleDrawable)localObject1).findDrawableByLayerId(16908334);
    if (this.functionsClass.appThemeTransparent())
    {
      ((RippleDrawable)localObject1).setColor(ColorStateList.valueOf(PublicVariable.primaryColor));
      localGradientDrawable.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 255.0F));
      if (this.functionsClass.returnAPI() > 21) {
        ((GradientDrawable)localObject2).setTint(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
      } else {
        paramBundle.setColor(this.functionsClass.setColorAlpha(PublicVariable.primaryColorOpposite, 175.0F));
      }
      ((GradientDrawable)localObject3).setColor(PublicVariable.primaryColor);
    }
    else
    {
      ((RippleDrawable)localObject1).setColor(ColorStateList.valueOf(PublicVariable.primaryColor));
      localGradientDrawable.setColor(PublicVariable.primaryColorOpposite);
      ((GradientDrawable)localObject2).setTint(PublicVariable.primaryColorOpposite);
      ((GradientDrawable)localObject3).setColor(PublicVariable.primaryColor);
    }
    this.autoCategories.setBackground((Drawable)localObject1);
    paramBundle = (ImageView)findViewById(2131296479);
    localObject1 = (LayerDrawable)getResources().getDrawable(2131230885);
    ((GradientDrawable)((LayerDrawable)localObject1).findDrawableByLayerId(2131296312)).setColor(PublicVariable.primaryColorOpposite);
    paramBundle.setImageDrawable((Drawable)localObject1);
    this.loadingSplash = ((RelativeLayout)findViewById(2131296482));
    if (this.functionsClass.appThemeTransparent() == true) {
      this.loadingSplash.setBackgroundColor(0);
    } else {
      this.loadingSplash.setBackgroundColor(getWindow().getNavigationBarColor());
    }
    this.loadingBarLTR = ((ProgressBar)findViewById(2131296480));
    this.desc = ((TextView)findViewById(2131296381));
    paramBundle = Typeface.createFromAsset(getAssets(), "upcil.ttf");
    this.desc.setTypeface(paramBundle);
    if (PublicVariable.themeLightDark)
    {
      this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeTextColor, PorterDuff.Mode.MULTIPLY);
      this.desc.setTextColor(getResources().getColor(2131099703));
    }
    else if (!PublicVariable.themeLightDark)
    {
      this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeColor, PorterDuff.Mode.MULTIPLY);
      this.desc.setTextColor(getResources().getColor(2131099732));
    }
    this.autoCategories.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PublicVariable.autoID = null;
        try
        {
          AppAutoFeatures.this.functionsClass.overrideBackPressToClass(CategoryAutoFeatures.class, ActivityOptions.makeCustomAnimation(AppAutoFeatures.this.getApplicationContext(), 2130771989, 2130771990));
          AppAutoFeatures.this.finish();
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    PublicVariable.autoID = null;
  }
  
  public void onPause()
  {
    super.onPause();
    if (PublicVariable.actionCenter == true) {
      this.functionsClass.closeMenuOptionAuto(this.fullActionButton, this.acttionElementsList);
    }
    if (PublicVariable.recoveryCenter == true) {
      this.functionsClass.closeRecoveryMenuOptionAuto(this.fullActionButton, this.acttionElementsList);
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (PublicVariable.themeLightDark)
    {
      this.color = PublicVariable.themeColor;
      this.pressColor = PublicVariable.themeTextColor;
    }
    else if (!PublicVariable.themeLightDark)
    {
      this.color = PublicVariable.themeTextColor;
      this.pressColor = PublicVariable.themeColor;
    }
    if (PublicVariable.autoID != null)
    {
      LayerDrawable localLayerDrawable1 = (LayerDrawable)getResources().getDrawable(2131230910);
      GradientDrawable localGradientDrawable1 = (GradientDrawable)localLayerDrawable1.findDrawableByLayerId(2131296312);
      LayerDrawable localLayerDrawable2 = (LayerDrawable)getResources().getDrawable(2131230877);
      GradientDrawable localGradientDrawable2 = (GradientDrawable)localLayerDrawable2.findDrawableByLayerId(2131296312);
      LayerDrawable localLayerDrawable3 = (LayerDrawable)getResources().getDrawable(2131230886);
      GradientDrawable localGradientDrawable3 = (GradientDrawable)localLayerDrawable3.findDrawableByLayerId(2131296312);
      LayerDrawable localLayerDrawable4 = (LayerDrawable)getResources().getDrawable(2131230888);
      GradientDrawable localGradientDrawable4 = (GradientDrawable)localLayerDrawable4.findDrawableByLayerId(2131296312);
      LayerDrawable localLayerDrawable5 = (LayerDrawable)getResources().getDrawable(2131230904);
      GradientDrawable localGradientDrawable5 = (GradientDrawable)localLayerDrawable5.findDrawableByLayerId(2131296312);
      this.wifi.setBackground(localLayerDrawable1);
      this.bluetooth.setBackground(localLayerDrawable2);
      this.gps.setBackground(localLayerDrawable3);
      this.nfc.setBackground(localLayerDrawable4);
      this.time.setBackground(localLayerDrawable5);
      if (PublicVariable.autoID.equals(getString(2131624231)))
      {
        localGradientDrawable1.setColor(this.pressColor);
        this.wifi.setBackground(localLayerDrawable1);
        autoWiFi();
        localGradientDrawable2.setColor(this.color);
        localGradientDrawable3.setColor(this.color);
        localGradientDrawable4.setColor(this.color);
        localGradientDrawable5.setColor(this.color);
        this.bluetooth.setBackground(localLayerDrawable2);
        this.gps.setBackground(localLayerDrawable3);
        this.nfc.setBackground(localLayerDrawable4);
        this.time.setBackground(localLayerDrawable5);
        return;
      }
      if (PublicVariable.autoID.equals(getString(2131624003)))
      {
        localGradientDrawable2.setColor(this.pressColor);
        this.bluetooth.setBackground(localLayerDrawable2);
        autoBluetooth();
        localGradientDrawable1.setColor(this.color);
        localGradientDrawable3.setColor(this.color);
        localGradientDrawable4.setColor(this.color);
        localGradientDrawable5.setColor(this.color);
        this.wifi.setBackground(localLayerDrawable1);
        this.gps.setBackground(localLayerDrawable3);
        this.nfc.setBackground(localLayerDrawable4);
        this.time.setBackground(localLayerDrawable5);
        return;
      }
      if (PublicVariable.autoID.equals(getString(2131624077)))
      {
        localGradientDrawable3.setColor(this.pressColor);
        this.gps.setBackground(localLayerDrawable3);
        autoGPS();
        localGradientDrawable1.setColor(this.color);
        localGradientDrawable2.setColor(this.color);
        localGradientDrawable4.setColor(this.color);
        localGradientDrawable5.setColor(this.color);
        this.wifi.setBackground(localLayerDrawable1);
        this.bluetooth.setBackground(localLayerDrawable2);
        this.nfc.setBackground(localLayerDrawable4);
        this.time.setBackground(localLayerDrawable5);
        return;
      }
      if (PublicVariable.autoID.equals(getString(2131624113)))
      {
        localGradientDrawable4.setColor(this.pressColor);
        this.nfc.setBackground(localLayerDrawable4);
        autoNfc();
        localGradientDrawable1.setColor(this.color);
        localGradientDrawable2.setColor(this.color);
        localGradientDrawable3.setColor(this.color);
        localGradientDrawable5.setColor(this.color);
        this.wifi.setBackground(localLayerDrawable1);
        this.bluetooth.setBackground(localLayerDrawable2);
        this.gps.setBackground(localLayerDrawable3);
        this.time.setBackground(localLayerDrawable5);
        return;
      }
      if (PublicVariable.autoID.equals(getString(2131624210)))
      {
        localGradientDrawable5.setColor(this.pressColor);
        this.time.setBackground(localLayerDrawable5);
        autoTime();
        localGradientDrawable1.setColor(this.color);
        localGradientDrawable2.setColor(this.color);
        localGradientDrawable3.setColor(this.color);
        localGradientDrawable4.setColor(this.color);
        this.wifi.setBackground(localLayerDrawable1);
        this.bluetooth.setBackground(localLayerDrawable2);
        this.gps.setBackground(localLayerDrawable3);
        this.nfc.setBackground(localLayerDrawable4);
      }
    }
  }
  
  public void onStart()
  {
    super.onStart();
    final LayerDrawable localLayerDrawable1 = (LayerDrawable)getResources().getDrawable(2131230910);
    final GradientDrawable localGradientDrawable1 = (GradientDrawable)localLayerDrawable1.findDrawableByLayerId(2131296312);
    final LayerDrawable localLayerDrawable2 = (LayerDrawable)getResources().getDrawable(2131230877);
    final GradientDrawable localGradientDrawable2 = (GradientDrawable)localLayerDrawable2.findDrawableByLayerId(2131296312);
    final LayerDrawable localLayerDrawable3 = (LayerDrawable)getResources().getDrawable(2131230886);
    final GradientDrawable localGradientDrawable3 = (GradientDrawable)localLayerDrawable3.findDrawableByLayerId(2131296312);
    final LayerDrawable localLayerDrawable4 = (LayerDrawable)getResources().getDrawable(2131230888);
    final GradientDrawable localGradientDrawable4 = (GradientDrawable)localLayerDrawable4.findDrawableByLayerId(2131296312);
    final LayerDrawable localLayerDrawable5 = (LayerDrawable)getResources().getDrawable(2131230904);
    final GradientDrawable localGradientDrawable5 = (GradientDrawable)localLayerDrawable5.findDrawableByLayerId(2131296312);
    if (PublicVariable.themeLightDark)
    {
      this.color = PublicVariable.themeColor;
      this.pressColor = PublicVariable.themeTextColor;
    }
    else if (!PublicVariable.themeLightDark)
    {
      this.color = PublicVariable.themeTextColor;
      this.pressColor = PublicVariable.themeColor;
    }
    localGradientDrawable1.setColor(this.color);
    localGradientDrawable2.setColor(this.color);
    localGradientDrawable3.setColor(this.color);
    localGradientDrawable4.setColor(this.color);
    localGradientDrawable5.setColor(this.color);
    this.wifi.setBackground(localLayerDrawable1);
    this.bluetooth.setBackground(localLayerDrawable2);
    this.gps.setBackground(localLayerDrawable3);
    this.nfc.setBackground(localLayerDrawable4);
    this.time.setBackground(localLayerDrawable5);
    this.wifi.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localGradientDrawable1.setColor(AppAutoFeatures.this.pressColor);
        AppAutoFeatures.this.wifi.setBackground(localLayerDrawable1);
        AppAutoFeatures.this.autoWiFi();
        localGradientDrawable2.setColor(AppAutoFeatures.this.color);
        localGradientDrawable3.setColor(AppAutoFeatures.this.color);
        localGradientDrawable4.setColor(AppAutoFeatures.this.color);
        localGradientDrawable5.setColor(AppAutoFeatures.this.color);
        AppAutoFeatures.this.bluetooth.setBackground(localLayerDrawable2);
        AppAutoFeatures.this.gps.setBackground(localLayerDrawable3);
        AppAutoFeatures.this.nfc.setBackground(localLayerDrawable4);
        AppAutoFeatures.this.time.setBackground(localLayerDrawable5);
      }
    });
    this.bluetooth.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localGradientDrawable2.setColor(AppAutoFeatures.this.pressColor);
        AppAutoFeatures.this.bluetooth.setBackground(localLayerDrawable2);
        AppAutoFeatures.this.autoBluetooth();
        localGradientDrawable1.setColor(AppAutoFeatures.this.color);
        localGradientDrawable3.setColor(AppAutoFeatures.this.color);
        localGradientDrawable4.setColor(AppAutoFeatures.this.color);
        localGradientDrawable5.setColor(AppAutoFeatures.this.color);
        AppAutoFeatures.this.wifi.setBackground(localLayerDrawable1);
        AppAutoFeatures.this.gps.setBackground(localLayerDrawable3);
        AppAutoFeatures.this.nfc.setBackground(localLayerDrawable4);
        AppAutoFeatures.this.time.setBackground(localLayerDrawable5);
      }
    });
    this.gps.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localGradientDrawable3.setColor(AppAutoFeatures.this.pressColor);
        AppAutoFeatures.this.gps.setBackground(localLayerDrawable3);
        AppAutoFeatures.this.autoGPS();
        localGradientDrawable1.setColor(AppAutoFeatures.this.color);
        localGradientDrawable2.setColor(AppAutoFeatures.this.color);
        localGradientDrawable4.setColor(AppAutoFeatures.this.color);
        localGradientDrawable5.setColor(AppAutoFeatures.this.color);
        AppAutoFeatures.this.wifi.setBackground(localLayerDrawable1);
        AppAutoFeatures.this.bluetooth.setBackground(localLayerDrawable2);
        AppAutoFeatures.this.nfc.setBackground(localLayerDrawable4);
        AppAutoFeatures.this.time.setBackground(localLayerDrawable5);
      }
    });
    this.nfc.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localGradientDrawable4.setColor(AppAutoFeatures.this.pressColor);
        AppAutoFeatures.this.nfc.setBackground(localLayerDrawable4);
        AppAutoFeatures.this.autoNfc();
        localGradientDrawable1.setColor(AppAutoFeatures.this.color);
        localGradientDrawable2.setColor(AppAutoFeatures.this.color);
        localGradientDrawable3.setColor(AppAutoFeatures.this.color);
        localGradientDrawable5.setColor(AppAutoFeatures.this.color);
        AppAutoFeatures.this.wifi.setBackground(localLayerDrawable1);
        AppAutoFeatures.this.bluetooth.setBackground(localLayerDrawable2);
        AppAutoFeatures.this.gps.setBackground(localLayerDrawable3);
        AppAutoFeatures.this.time.setBackground(localLayerDrawable5);
      }
    });
    this.time.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localGradientDrawable5.setColor(AppAutoFeatures.this.pressColor);
        AppAutoFeatures.this.time.setBackground(localLayerDrawable5);
        AppAutoFeatures.this.autoTime();
        localGradientDrawable1.setColor(AppAutoFeatures.this.color);
        localGradientDrawable2.setColor(AppAutoFeatures.this.color);
        localGradientDrawable3.setColor(AppAutoFeatures.this.color);
        localGradientDrawable4.setColor(AppAutoFeatures.this.color);
        AppAutoFeatures.this.wifi.setBackground(localLayerDrawable1);
        AppAutoFeatures.this.bluetooth.setBackground(localLayerDrawable2);
        AppAutoFeatures.this.gps.setBackground(localLayerDrawable3);
        AppAutoFeatures.this.nfc.setBackground(localLayerDrawable4);
      }
    });
  }
  
  public void onStop()
  {
    super.onStop();
  }
  
  public void onSwipe(int paramInt)
  {
    if (paramInt != 1)
    {
      switch (paramInt)
      {
      default: 
        return;
      case 4: 
        System.out.println("Swipe Right");
        return;
      }
      System.out.println("Swipe Left");
      try
      {
        this.functionsClass.overrideBackPressToClass(CategoryAutoFeatures.class, ActivityOptions.makeCustomAnimation(getApplicationContext(), 2130771989, 2130771990));
        finish();
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        return;
      }
    }
    System.out.println("SWIPE UP");
    for (;;)
    {
      try
      {
        paramInt = this.listView.getLastVisiblePosition();
        int i = this.listView.getAdapter().getCount();
        if (paramInt != i - 1) {
          continue;
        }
        try
        {
          this.functionsClass.overrideBackPress(this);
          overridePendingTransition(17432576, 2130771986);
          return;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          return;
        }
      }
      catch (Exception localException4)
      {
        continue;
      }
      try
      {
        this.functionsClass.overrideBackPress(this);
        overridePendingTransition(17432576, 2130771986);
        return;
      }
      catch (Exception localException3)
      {
        localException3.printStackTrace();
      }
    }
  }
  
  private class LoadApplicationsIndex
    extends AsyncTask<Void, Void, Void>
  {
    private LoadApplicationsIndex() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      int i = 0;
      while (i < AppAutoFeatures.this.navDrawerItems.size())
      {
        try
        {
          paramVarArgs = ((NavDrawerItem)AppAutoFeatures.this.navDrawerItems.get(i)).getDesc().substring(0, 1).toUpperCase();
          if (AppAutoFeatures.this.mapIndex.get(paramVarArgs) == null) {
            AppAutoFeatures.this.mapIndex.put(paramVarArgs, Integer.valueOf(i));
          }
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
        }
        i += 1;
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      paramVoid = (LayerDrawable)AppAutoFeatures.this.getResources().getDrawable(2131230887);
      ((GradientDrawable)paramVoid.findDrawableByLayerId(2131296312)).setColor(0);
      Iterator localIterator = new ArrayList(AppAutoFeatures.this.mapIndex.keySet()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        TextView localTextView = (TextView)AppAutoFeatures.this.getLayoutInflater().inflate(2131427467, null);
        localTextView.setBackground(paramVoid);
        localTextView.setText(str.toUpperCase());
        localTextView.setTextColor(PublicVariable.colorLightDarkOpposite);
        localTextView.setOnClickListener(AppAutoFeatures.this);
        AppAutoFeatures.this.indexView.addView(localTextView);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      AppAutoFeatures.this.indexView.removeAllViews();
    }
  }
  
  private class LoadAutoApplications
    extends AsyncTask<Void, Void, Void>
  {
    private LoadAutoApplications() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      for (;;)
      {
        int i;
        try
        {
          paramVarArgs = AppAutoFeatures.this;
          Object localObject = AppAutoFeatures.this.getApplicationContext().getPackageManager();
          i = 0;
          paramVarArgs.applicationInfoList = ((PackageManager)localObject).getInstalledApplications(0);
          Collections.sort(AppAutoFeatures.this.applicationInfoList, new ApplicationInfo.DisplayNameComparator(AppAutoFeatures.this.getPackageManager()));
          AppAutoFeatures.this.navDrawerItems = new ArrayList();
          AppAutoFeatures.this.mapIndex = new LinkedHashMap();
          if (i < AppAutoFeatures.this.applicationInfoList.size())
          {
            paramVarArgs = AppAutoFeatures.this.getApplicationContext().getPackageManager().getLaunchIntentForPackage(((ApplicationInfo)AppAutoFeatures.this.applicationInfoList.get(i)).packageName);
            if (paramVarArgs == null) {
              break label410;
            }
            try
            {
              AppAutoFeatures.this.PackageName = ((ApplicationInfo)AppAutoFeatures.this.applicationInfoList.get(i)).packageName;
              AppAutoFeatures.this.AppName = AppAutoFeatures.this.functionsClass.appName(AppAutoFeatures.this.PackageName);
              AppAutoFeatures.this.AppIcon = AppAutoFeatures.this.functionsClass.shapedAppIcon(AppAutoFeatures.this.PackageName);
              paramVarArgs = AppAutoFeatures.this;
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append(AppAutoFeatures.this.PackageName);
              ((StringBuilder)localObject).append(".Time");
              if (paramVarArgs.getFileStreamPath(((StringBuilder)localObject).toString()).exists())
              {
                paramVarArgs = AppAutoFeatures.this;
                localObject = AppAutoFeatures.this.functionsClass;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(AppAutoFeatures.this.PackageName);
                localStringBuilder.append(".Time");
                paramVarArgs.AppTime = ((FunctionsClass)localObject).readFile(localStringBuilder.toString());
              }
              AppAutoFeatures.this.navDrawerItems.add(new NavDrawerItem(AppAutoFeatures.this.AppName, AppAutoFeatures.this.PackageName, AppAutoFeatures.this.AppIcon, AppAutoFeatures.this.AppTime));
            }
            catch (Exception paramVarArgs)
            {
              paramVarArgs.printStackTrace();
            }
          }
          AppAutoFeatures.this.adapter = new AppAutoListAdapter(AppAutoFeatures.this.activity, AppAutoFeatures.this.getApplicationContext(), AppAutoFeatures.this.navDrawerItems);
        }
        catch (Exception paramVarArgs)
        {
          paramVarArgs.printStackTrace();
        }
        return null;
        label410:
        i += 1;
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      AppAutoFeatures.this.listView.setAdapter(AppAutoFeatures.this.adapter);
      AppAutoFeatures.this.registerForContextMenu(AppAutoFeatures.this.listView);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Animation localAnimation = AnimationUtils.loadAnimation(AppAutoFeatures.this.getApplicationContext(), 17432577);
          AppAutoFeatures.this.loadingSplash.setVisibility(4);
          AppAutoFeatures.this.loadingSplash.startAnimation(localAnimation);
          AppAutoFeatures.this.autoIdentifier.setVisibility(0);
        }
      }, 100L);
      new AppAutoFeatures.LoadApplicationsIndex(AppAutoFeatures.this, null).execute(new Void[0]);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      AppAutoFeatures.this.loadingSplash = ((RelativeLayout)AppAutoFeatures.this.findViewById(2131296482));
      AppAutoFeatures.this.loadingSplash.setVisibility(0);
      if (AppAutoFeatures.this.functionsClass.appThemeTransparent() == true) {
        AppAutoFeatures.this.loadingSplash.setBackgroundColor(0);
      } else {
        AppAutoFeatures.this.loadingSplash.setBackgroundColor(AppAutoFeatures.this.getWindow().getNavigationBarColor());
      }
      AppAutoFeatures.this.loadingBarLTR = ((ProgressBar)AppAutoFeatures.this.findViewById(2131296480));
      AppAutoFeatures.this.desc = ((TextView)AppAutoFeatures.this.findViewById(2131296381));
      Typeface localTypeface = Typeface.createFromAsset(AppAutoFeatures.this.getAssets(), "upcil.ttf");
      AppAutoFeatures.this.desc.setTypeface(localTypeface);
      if (PublicVariable.themeLightDark) {
        AppAutoFeatures.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeTextColor, PorterDuff.Mode.MULTIPLY);
      } else if (!PublicVariable.themeLightDark) {
        AppAutoFeatures.this.loadingBarLTR.getIndeterminateDrawable().setColorFilter(PublicVariable.themeColor, PorterDuff.Mode.MULTIPLY);
      }
      AppAutoFeatures.this.listView.clearChoices();
      AppAutoFeatures.this.indexView.removeAllViews();
    }
  }
}
