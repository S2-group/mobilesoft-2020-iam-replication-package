package com.rsupport.rs.activity.edit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.rsupport.rs.activity.RCAbstractActivity;
import da;
import db;
import dc;
import dd;
import de;
import df;
import dg;
import dh;
import di;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import ml;
import mm;
import na;
import org.json.JSONArray;
import org.json.JSONObject;
import qq;
import rl;
import vf;
import vh;
import vs;

public class AgreePage
  extends RCAbstractActivity
  implements View.OnClickListener
{
  AlertDialog jdField_a_of_type_AndroidAppAlertDialog;
  public Handler a;
  private Button jdField_a_of_type_AndroidWidgetButton;
  private CheckBox jdField_a_of_type_AndroidWidgetCheckBox;
  private LinearLayout jdField_a_of_type_AndroidWidgetLinearLayout;
  private ProgressBar jdField_a_of_type_AndroidWidgetProgressBar;
  private ScrollView jdField_a_of_type_AndroidWidgetScrollView;
  private Spinner jdField_a_of_type_AndroidWidgetSpinner;
  private TextView jdField_a_of_type_AndroidWidgetTextView;
  private JSONObject jdField_a_of_type_OrgJsonJSONObject;
  private boolean jdField_a_of_type_Boolean = false;
  private String[] jdField_a_of_type_ArrayOfJavaLangString;
  public Handler b;
  private Button jdField_b_of_type_AndroidWidgetButton;
  private CheckBox jdField_b_of_type_AndroidWidgetCheckBox;
  private LinearLayout jdField_b_of_type_AndroidWidgetLinearLayout;
  private ScrollView jdField_b_of_type_AndroidWidgetScrollView;
  private TextView jdField_b_of_type_AndroidWidgetTextView;
  private String jdField_b_of_type_JavaLangString;
  private JSONObject jdField_b_of_type_OrgJsonJSONObject;
  public Handler c;
  private Button jdField_c_of_type_AndroidWidgetButton;
  private TextView jdField_c_of_type_AndroidWidgetTextView;
  public Handler d;
  private Button d;
  
  public AgreePage()
  {
    this.jdField_a_of_type_AndroidOsHandler = new da(this);
    this.jdField_b_of_type_AndroidOsHandler = new db(this);
    this.jdField_c_of_type_AndroidOsHandler = new dc(this);
    this.jdField_d_of_type_AndroidOsHandler = new dd(this);
  }
  
  private int a()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  private Animation a(int paramInt)
  {
    Animation localAnimation = AnimationUtils.loadAnimation(this, paramInt);
    localAnimation.setRepeatMode(1);
    return localAnimation;
  }
  
  private boolean a()
  {
    Object localObject = getSharedPreferences("firstrun", 0);
    boolean bool2 = ((SharedPreferences)localObject).getBoolean("firstrun", true);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putBoolean("firstrun", false);
    ((SharedPreferences.Editor)localObject).commit();
    int i;
    if (bool2)
    {
      localObject = getPackageManager().getInstalledApplications(8192).iterator();
      i = 0;
      if (!((Iterator)localObject).hasNext()) {
        if (i == 0) {
          break label121;
        }
      }
    }
    label121:
    for (boolean bool1 = true;; bool1 = false)
    {
      if (!bool2)
      {
        return false;
        if (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.rsupport.rs.activity.rsupport")) {
          break;
        }
        i = 1;
        break;
      }
      return bool1;
    }
  }
  
  private boolean a(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(8192).iterator();
    boolean bool = false;
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return bool;
      }
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        bool = true;
      }
    }
  }
  
  private void b()
  {
    if (vh.c())
    {
      if (Build.VERSION.SDK_INT >= 11)
      {
        setContentView(2130903057);
        ActionBar localActionBar = getActionBar();
        localActionBar.setTitle(2131296448);
        localActionBar.setDisplayHomeAsUpEnabled(true);
      }
      for (;;)
      {
        this.jdField_a_of_type_AndroidWidgetScrollView = ((ScrollView)findViewById(2131492927));
        this.jdField_b_of_type_AndroidWidgetScrollView = ((ScrollView)findViewById(2131492935));
        this.jdField_b_of_type_AndroidWidgetLinearLayout = ((LinearLayout)findViewById(2131492941));
        this.jdField_a_of_type_AndroidWidgetLinearLayout = ((LinearLayout)findViewById(2131492936));
        this.jdField_a_of_type_AndroidWidgetButton = ((Button)findViewById(2131492948));
        this.jdField_b_of_type_AndroidWidgetButton = ((Button)findViewById(2131492947));
        if (Build.VERSION.SDK_INT >= 20)
        {
          this.jdField_a_of_type_AndroidWidgetButton.setTextColor(-16777216);
          this.jdField_b_of_type_AndroidWidgetButton.setTextColor(-16777216);
          ((TextView)findViewById(2131492929)).setBackgroundColor(Color.parseColor("#dcdcdc"));
          ((TextView)findViewById(2131492934)).setBackgroundColor(Color.parseColor("#dcdcdc"));
          ((TextView)findViewById(2131492939)).setBackgroundColor(Color.parseColor("#dcdcdc"));
          ((TextView)findViewById(2131492939)).setTextColor(Color.parseColor("#333333"));
          ((TextView)findViewById(2131492944)).setBackgroundColor(Color.parseColor("#dcdcdc"));
          ((TextView)findViewById(2131492944)).setTextColor(Color.parseColor("#333333"));
          ((Button)findViewById(2131492943)).setBackgroundResource(2130837591);
          ((Button)findViewById(2131492938)).setBackgroundResource(2130837591);
        }
        this.jdField_c_of_type_AndroidWidgetButton = ((Button)findViewById(2131492938));
        this.jdField_d_of_type_AndroidWidgetButton = ((Button)findViewById(2131492943));
        this.jdField_a_of_type_AndroidWidgetTextView = ((TextView)findViewById(2131492939));
        this.jdField_b_of_type_AndroidWidgetTextView = ((TextView)findViewById(2131492944));
        this.jdField_c_of_type_AndroidWidgetTextView = ((TextView)findViewById(2131492928));
        this.jdField_a_of_type_AndroidWidgetCheckBox = ((CheckBox)findViewById(2131492940));
        this.jdField_b_of_type_AndroidWidgetCheckBox = ((CheckBox)findViewById(2131492945));
        this.jdField_a_of_type_AndroidWidgetSpinner = ((Spinner)findViewById(2131492932));
        this.jdField_a_of_type_AndroidWidgetProgressBar = ((ProgressBar)findViewById(2131492886));
        this.jdField_a_of_type_AndroidWidgetButton.setOnClickListener(this);
        this.jdField_b_of_type_AndroidWidgetButton.setOnClickListener(this);
        this.jdField_a_of_type_AndroidWidgetCheckBox.setOnClickListener(this);
        this.jdField_b_of_type_AndroidWidgetCheckBox.setOnClickListener(this);
        this.jdField_c_of_type_AndroidWidgetButton.setOnClickListener(this);
        this.jdField_d_of_type_AndroidWidgetButton.setOnClickListener(this);
        vf.c(this.jdField_a_of_type_JavaLangString, "processCountryCode");
        this.jdField_d_of_type_AndroidOsHandler.sendEmptyMessage(0);
        new Thread(new de(this)).start();
        return;
        requestWindowFeature(1);
        setContentView(2130903057);
      }
    }
    this.jdField_a_of_type_AndroidOsHandler.sendEmptyMessage(1);
  }
  
  private void b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.jdField_a_of_type_AndroidWidgetScrollView.setVisibility(0);
      this.jdField_a_of_type_AndroidWidgetScrollView.setAnimation(a(2130968579));
      this.jdField_b_of_type_AndroidWidgetScrollView.setVisibility(8);
      ((TextView)findViewById(2131492929)).setVisibility(8);
      ((TextView)findViewById(2131492930)).setVisibility(8);
      ((TextView)findViewById(2131492934)).setVisibility(8);
      ((LinearLayout)findViewById(2131492931)).setVisibility(8);
      ((RelativeLayout)findViewById(2131492946)).setVisibility(8);
    }
    for (;;)
    {
      this.jdField_a_of_type_AndroidWidgetCheckBox.setText(2131296452);
      this.jdField_b_of_type_AndroidWidgetCheckBox.setText(2131296452);
      return;
      this.jdField_a_of_type_AndroidWidgetScrollView.setVisibility(8);
      this.jdField_b_of_type_AndroidWidgetScrollView.setVisibility(0);
      this.jdField_b_of_type_AndroidWidgetScrollView.setAnimation(a(2130968578));
      ((TextView)findViewById(2131492929)).setVisibility(0);
      ((TextView)findViewById(2131492930)).setVisibility(0);
      ((TextView)findViewById(2131492934)).setVisibility(0);
      ((LinearLayout)findViewById(2131492931)).setVisibility(0);
      ((RelativeLayout)findViewById(2131492946)).setVisibility(0);
    }
  }
  
  private void c()
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      setContentView(2130903057);
      ActionBar localActionBar = getActionBar();
      localActionBar.setTitle(2131296448);
      localActionBar.setDisplayHomeAsUpEnabled(true);
    }
    for (;;)
    {
      this.jdField_a_of_type_AndroidWidgetScrollView = ((ScrollView)findViewById(2131492927));
      this.jdField_b_of_type_AndroidWidgetScrollView = ((ScrollView)findViewById(2131492935));
      this.jdField_b_of_type_AndroidWidgetLinearLayout = ((LinearLayout)findViewById(2131492941));
      this.jdField_a_of_type_AndroidWidgetLinearLayout = ((LinearLayout)findViewById(2131492936));
      this.jdField_a_of_type_AndroidWidgetButton = ((Button)findViewById(2131492948));
      this.jdField_b_of_type_AndroidWidgetButton = ((Button)findViewById(2131492947));
      if (Build.VERSION.SDK_INT >= 20)
      {
        this.jdField_a_of_type_AndroidWidgetButton.setTextColor(-16777216);
        this.jdField_b_of_type_AndroidWidgetButton.setTextColor(-16777216);
        ((TextView)findViewById(2131492929)).setBackgroundColor(Color.parseColor("#dcdcdc"));
        ((TextView)findViewById(2131492934)).setBackgroundColor(Color.parseColor("#dcdcdc"));
        ((TextView)findViewById(2131492939)).setBackgroundColor(Color.parseColor("#dcdcdc"));
        ((TextView)findViewById(2131492939)).setTextColor(Color.parseColor("#333333"));
        ((TextView)findViewById(2131492944)).setBackgroundColor(Color.parseColor("#dcdcdc"));
        ((TextView)findViewById(2131492944)).setTextColor(Color.parseColor("#333333"));
        ((Button)findViewById(2131492943)).setBackgroundResource(2130837591);
        ((Button)findViewById(2131492938)).setBackgroundResource(2130837591);
      }
      this.jdField_c_of_type_AndroidWidgetButton = ((Button)findViewById(2131492938));
      this.jdField_d_of_type_AndroidWidgetButton = ((Button)findViewById(2131492943));
      this.jdField_a_of_type_AndroidWidgetTextView = ((TextView)findViewById(2131492939));
      this.jdField_b_of_type_AndroidWidgetTextView = ((TextView)findViewById(2131492944));
      this.jdField_c_of_type_AndroidWidgetTextView = ((TextView)findViewById(2131492928));
      this.jdField_a_of_type_AndroidWidgetCheckBox = ((CheckBox)findViewById(2131492940));
      this.jdField_b_of_type_AndroidWidgetCheckBox = ((CheckBox)findViewById(2131492945));
      this.jdField_a_of_type_AndroidWidgetSpinner = ((Spinner)findViewById(2131492932));
      this.jdField_a_of_type_AndroidWidgetProgressBar = ((ProgressBar)findViewById(2131492886));
      this.jdField_a_of_type_AndroidWidgetButton.setOnClickListener(this);
      this.jdField_b_of_type_AndroidWidgetButton.setOnClickListener(this);
      this.jdField_a_of_type_AndroidWidgetCheckBox.setOnClickListener(this);
      this.jdField_b_of_type_AndroidWidgetCheckBox.setOnClickListener(this);
      this.jdField_c_of_type_AndroidWidgetButton.setOnClickListener(this);
      this.jdField_d_of_type_AndroidWidgetButton.setOnClickListener(this);
      vf.c(this.jdField_a_of_type_JavaLangString, "processCountryCode");
      this.jdField_d_of_type_AndroidOsHandler.sendEmptyMessage(0);
      new Thread(new de(this)).start();
      return;
      requestWindowFeature(1);
      setContentView(2130903057);
    }
  }
  
  private void c(int paramInt)
  {
    if (isFinishing()) {}
    do
    {
      return;
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setMessage(getResources().getString(paramInt)).setCancelable(false).setPositiveButton(getResources().getString(2131296270), new df(this, paramInt));
      this.jdField_a_of_type_AndroidAppAlertDialog = localBuilder.create();
    } while (this.jdField_a_of_type_AndroidAppAlertDialog.isShowing());
    this.jdField_a_of_type_AndroidAppAlertDialog.show();
  }
  
  private void c(String paramString)
  {
    vf.c(this.jdField_a_of_type_JavaLangString, "locale : " + paramString);
    na localNa = na.a();
    ml.e = localNa.a(paramString);
    vf.c(this.jdField_a_of_type_JavaLangString, "serverAddress : " + localNa.a(paramString));
  }
  
  private void d()
  {
    vf.c(this.jdField_a_of_type_JavaLangString, "processCountryCode");
    this.jdField_d_of_type_AndroidOsHandler.sendEmptyMessage(0);
    new Thread(new de(this)).start();
  }
  
  private void e()
  {
    ActionBar localActionBar = getActionBar();
    localActionBar.setTitle(2131296448);
    localActionBar.setDisplayHomeAsUpEnabled(true);
  }
  
  private void f()
  {
    vh.a = getApplicationContext();
  }
  
  private void g()
  {
    Locale localLocale = getResources().getConfiguration().locale;
    c(localLocale.getCountry() + "-" + localLocale.getLanguage());
  }
  
  private void h()
  {
    int k = 0;
    ArrayList localArrayList = new ArrayList();
    rl.a();
    this.jdField_a_of_type_ArrayOfJavaLangString = rl.b(this.jdField_a_of_type_OrgJsonJSONObject);
    rl.a();
    String[] arrayOfString = rl.a(this.jdField_a_of_type_OrgJsonJSONObject);
    String str2 = na.a().a(arrayOfString, this);
    vf.c(this.jdField_a_of_type_JavaLangString, "Locale : " + str2);
    Object localObject = str2;
    boolean bool;
    if (mm.a(getApplicationContext()))
    {
      String str1 = mm.a(getApplicationContext());
      localObject = str2;
      if (!str1.equals(""))
      {
        localObject = this.jdField_a_of_type_AndroidWidgetSpinner;
        if (!getApplicationContext().getSharedPreferences("PREF_SETUP_CONCIERGE", 0).getBoolean("KEY_SETUP_CONCIERGE_LOCALE_ISFIXED", false)) {
          break label240;
        }
        bool = false;
        ((Spinner)localObject).setEnabled(bool);
        localObject = str1;
      }
    }
    int i = 0;
    label158:
    int j;
    if (this.jdField_a_of_type_ArrayOfJavaLangString.length <= i)
    {
      j = 0;
      i = k;
    }
    for (;;)
    {
      if (this.jdField_a_of_type_ArrayOfJavaLangString.length <= i) {
        k = j;
      }
      label240:
      do
      {
        localObject = new ArrayAdapter(this, 17367048, localArrayList);
        ((ArrayAdapter)localObject).setDropDownViewResource(17367049);
        this.jdField_a_of_type_AndroidWidgetSpinner.setAdapter((SpinnerAdapter)localObject);
        this.jdField_a_of_type_AndroidWidgetSpinner.setSelection(k);
        this.jdField_a_of_type_AndroidWidgetSpinner.setOnItemSelectedListener(new dg(this, arrayOfString));
        return;
        bool = true;
        break;
        localArrayList.add(this.jdField_a_of_type_ArrayOfJavaLangString[i]);
        i += 1;
        break label158;
        if (arrayOfString[i].equals("US-en")) {
          j = i;
        }
        k = i;
      } while (arrayOfString[i].equals(localObject));
      i += 1;
    }
  }
  
  private void i()
  {
    if (Build.VERSION.SDK_INT >= 20)
    {
      if ((ml.jdField_a_of_type_JavaLangString.equals("KR-ko")) || (ml.jdField_a_of_type_JavaLangString.equals("DE-de")))
      {
        this.jdField_a_of_type_AndroidWidgetTextView.setLinkTextColor(Color.parseColor("#999933"));
        this.jdField_c_of_type_AndroidWidgetTextView.setLinkTextColor(Color.parseColor("#999933"));
      }
    }
    else {
      return;
    }
    this.jdField_a_of_type_AndroidWidgetTextView.setLinkTextColor(-16776961);
    this.jdField_c_of_type_AndroidWidgetTextView.setLinkTextColor(-16776961);
  }
  
  private void j()
  {
    for (;;)
    {
      try
      {
        if (this.jdField_b_of_type_OrgJsonJSONObject.getJSONArray("contents").length() > 1)
        {
          this.jdField_a_of_type_AndroidWidgetButton.setEnabled(false);
          this.jdField_a_of_type_AndroidWidgetCheckBox.setChecked(false);
          this.jdField_b_of_type_AndroidWidgetCheckBox.setChecked(false);
          this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(5);
          this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(5);
          this.jdField_b_of_type_AndroidWidgetTextView.setMaxLines(5);
          this.jdField_b_of_type_AndroidWidgetTextView.setMinLines(5);
          this.jdField_b_of_type_AndroidWidgetLinearLayout.setVisibility(0);
          this.jdField_a_of_type_AndroidWidgetLinearLayout.setVisibility(0);
          this.jdField_a_of_type_AndroidWidgetLinearLayout.setAnimation(a(2130968579));
          this.jdField_b_of_type_AndroidWidgetLinearLayout.setAnimation(a(2130968579));
          this.jdField_a_of_type_AndroidWidgetTextView.setText(this.jdField_b_of_type_OrgJsonJSONObject.getJSONArray("contents").getJSONObject(0).getString("content"));
          this.jdField_b_of_type_AndroidWidgetTextView.setText(this.jdField_b_of_type_OrgJsonJSONObject.getJSONArray("contents").getJSONObject(1).getString("content"));
          return;
        }
        this.jdField_a_of_type_AndroidWidgetButton.setEnabled(false);
        this.jdField_a_of_type_AndroidWidgetCheckBox.setChecked(false);
        this.jdField_b_of_type_AndroidWidgetCheckBox.setChecked(true);
        if (a() <= 480)
        {
          this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(6);
          this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(6);
          this.jdField_a_of_type_AndroidWidgetLinearLayout.setVisibility(0);
          this.jdField_b_of_type_AndroidWidgetLinearLayout.setVisibility(8);
          this.jdField_a_of_type_AndroidWidgetLinearLayout.setAnimation(a(2130968579));
          this.jdField_a_of_type_AndroidWidgetTextView.setText(Html.fromHtml(this.jdField_b_of_type_OrgJsonJSONObject.getJSONArray("contents").getJSONObject(0).getString("content")));
          this.jdField_a_of_type_AndroidWidgetTextView.setMovementMethod(LinkMovementMethod.getInstance());
          this.jdField_a_of_type_AndroidWidgetTextView.setLinkTextColor(65280);
          i();
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      if (a() <= 800)
      {
        this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(8);
        this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(8);
      }
      else
      {
        this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(15);
        this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(15);
      }
    }
  }
  
  private void k()
  {
    this.jdField_a_of_type_AndroidWidgetButton.setEnabled(false);
    this.jdField_a_of_type_AndroidWidgetCheckBox.setChecked(false);
    this.jdField_b_of_type_AndroidWidgetCheckBox.setChecked(false);
    this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(5);
    this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(5);
    this.jdField_b_of_type_AndroidWidgetTextView.setMaxLines(5);
    this.jdField_b_of_type_AndroidWidgetTextView.setMinLines(5);
    this.jdField_b_of_type_AndroidWidgetLinearLayout.setVisibility(0);
    this.jdField_a_of_type_AndroidWidgetLinearLayout.setVisibility(0);
    this.jdField_a_of_type_AndroidWidgetLinearLayout.setAnimation(a(2130968579));
    this.jdField_b_of_type_AndroidWidgetLinearLayout.setAnimation(a(2130968579));
  }
  
  private void l()
  {
    this.jdField_a_of_type_AndroidWidgetButton.setEnabled(false);
    this.jdField_a_of_type_AndroidWidgetCheckBox.setChecked(false);
    this.jdField_b_of_type_AndroidWidgetCheckBox.setChecked(true);
    if (a() <= 480)
    {
      this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(6);
      this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(6);
    }
    for (;;)
    {
      this.jdField_a_of_type_AndroidWidgetLinearLayout.setVisibility(0);
      this.jdField_b_of_type_AndroidWidgetLinearLayout.setVisibility(8);
      this.jdField_a_of_type_AndroidWidgetLinearLayout.setAnimation(a(2130968579));
      return;
      if (a() <= 800)
      {
        this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(8);
        this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(8);
      }
      else
      {
        this.jdField_a_of_type_AndroidWidgetTextView.setMaxLines(15);
        this.jdField_a_of_type_AndroidWidgetTextView.setMinLines(15);
      }
    }
  }
  
  private void m()
  {
    if (mm.a(getApplicationContext()))
    {
      if (qq.b)
      {
        a();
        return;
      }
      int i = 0;
      for (;;)
      {
        if (i >= ml.jdField_a_of_type_JavaUtilArrayList.size())
        {
          ml.jdField_a_of_type_JavaUtilArrayList.clear();
          finish();
          return;
        }
        vf.c(this.jdField_a_of_type_JavaLangString, "finish : " + ((Activity)ml.jdField_a_of_type_JavaUtilArrayList.get(i)).getLocalClassName());
        ((Activity)ml.jdField_a_of_type_JavaUtilArrayList.get(i)).finish();
        i += 1;
      }
    }
    startActivity(new Intent(this, AutoConnActivity.class));
    finish();
  }
  
  public final void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.jdField_a_of_type_AndroidWidgetProgressBar.setVisibility(0);
      return;
    }
    this.jdField_a_of_type_AndroidWidgetProgressBar.setVisibility(4);
  }
  
  public final void b(String paramString)
  {
    vf.c(this.jdField_a_of_type_JavaLangString, "processLegalNotices");
    this.jdField_d_of_type_AndroidOsHandler.sendEmptyMessage(0);
    new Thread(new dh(this, paramString)).start();
  }
  
  public void onBackPressed()
  {
    vf.a(this.jdField_a_of_type_JavaLangString, "onBackPressed");
    if (this.jdField_a_of_type_AndroidWidgetScrollView.isShown())
    {
      b(false);
      return;
    }
    m();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131492948)
    {
      a(true);
      new Thread(new di(this)).start();
    }
    do
    {
      return;
      if (paramView.getId() == 2131492947)
      {
        m();
        return;
      }
      if (paramView.getId() == 2131492940)
      {
        if ((this.jdField_a_of_type_AndroidWidgetCheckBox.isChecked()) && (this.jdField_b_of_type_AndroidWidgetCheckBox.isChecked()))
        {
          this.jdField_a_of_type_AndroidWidgetButton.setEnabled(true);
          return;
        }
        this.jdField_a_of_type_AndroidWidgetButton.setEnabled(false);
        return;
      }
      if (paramView.getId() == 2131492945)
      {
        if ((this.jdField_b_of_type_AndroidWidgetCheckBox.isChecked()) && (this.jdField_a_of_type_AndroidWidgetCheckBox.isChecked()))
        {
          this.jdField_a_of_type_AndroidWidgetButton.setEnabled(true);
          return;
        }
        this.jdField_a_of_type_AndroidWidgetButton.setEnabled(false);
        return;
      }
      if (paramView.getId() == 2131492938)
      {
        b(true);
        this.jdField_c_of_type_AndroidWidgetTextView.setText(this.jdField_a_of_type_AndroidWidgetTextView.getText());
        this.jdField_c_of_type_AndroidWidgetTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.jdField_c_of_type_AndroidWidgetTextView.setLinkTextColor(65280);
        i();
        return;
      }
    } while (paramView.getId() != 2131492943);
    b(true);
    this.jdField_c_of_type_AndroidWidgetTextView.setText(this.jdField_b_of_type_AndroidWidgetTextView.getText());
    i();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    int i = 1;
    int j = 0;
    super.onCreate(paramBundle);
    if (vs.a(this))
    {
      finish();
      return;
    }
    vh.a = getApplicationContext();
    paramBundle = getResources().getConfiguration().locale;
    c(paramBundle.getCountry() + "-" + paramBundle.getLanguage());
    paramBundle = getSharedPreferences("firstrun", 0);
    boolean bool = paramBundle.getBoolean("firstrun", true);
    paramBundle = paramBundle.edit();
    paramBundle.putBoolean("firstrun", false);
    paramBundle.commit();
    if ((bool) && (a("com.rsupport.rs.activity.rsupport"))) {}
    for (;;)
    {
      if (!bool) {
        i = j;
      }
      for (;;)
      {
        if (i != 0)
        {
          c(2131296480);
          return;
        }
        b();
        return;
      }
      i = 0;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    vf.c(this.jdField_a_of_type_JavaLangString, "onOptionsItemSelected");
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      onBackPressed();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}
