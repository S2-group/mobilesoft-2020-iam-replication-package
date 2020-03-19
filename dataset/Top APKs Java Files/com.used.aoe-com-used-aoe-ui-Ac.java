package com.used.aoe.ui;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment.ColorPickerDialogListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.c.a;
import com.used.aoe.models.app;
import com.used.aoe.utils.MultiprocessPreferences;
import com.used.aoe.utils.MultiprocessPreferences.a;
import com.used.aoe.utils.MultiprocessPreferences.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ac
  extends Activity
  implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, ColorPickerDialogFragment.ColorPickerDialogListener
{
  private List<app> a = new ArrayList();
  private com.used.aoe.a.a b;
  private CheckBox c;
  private CheckBox d;
  private CharSequence e;
  private TextView f;
  private AdView g;
  
  public Ac() {}
  
  private void a(boolean paramBoolean)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      String str = ((ApplicationInfo)localIterator.next()).packageName;
      MultiprocessPreferences.a localA = MultiprocessPreferences.a(a()).a();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("_enabled");
      localA.a(localStringBuilder.toString(), paramBoolean).a();
    }
    this.b.f();
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  private void b()
  {
    this.a.clear();
    this.b.f();
    PackageManager localPackageManager = getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      String str = ((ApplicationInfo)localObject).packageName;
      if (!a((ApplicationInfo)localObject))
      {
        localObject = ((ApplicationInfo)localObject).loadLabel(localPackageManager).toString();
        this.a.add(new app((String)localObject, str));
        this.b.f();
      }
    }
  }
  
  private void c()
  {
    PackageManager localPackageManager = getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      String str = ((ApplicationInfo)localObject).packageName;
      if (a((ApplicationInfo)localObject))
      {
        localObject = ((ApplicationInfo)localObject).loadLabel(localPackageManager).toString();
        this.a.add(new app((String)localObject, str));
        this.b.f();
      }
    }
  }
  
  protected Ac a()
  {
    return this;
  }
  
  public void a(int paramInt) {}
  
  public void a(String paramString, int paramInt1, int paramInt2)
  {
    MultiprocessPreferences.a(a()).a().a(paramString, paramInt2).a();
    this.b.f();
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (paramCompoundButton == this.c)
      {
        a(true);
        MultiprocessPreferences.a(a()).a().a("allenabled", true).a();
        return;
      }
      if (paramCompoundButton == this.d) {
        c();
      }
    }
    else
    {
      if (paramCompoundButton == this.c)
      {
        a(false);
        MultiprocessPreferences.a(a()).a().a("allenabled", false).a();
        return;
      }
      if (paramCompoundButton == this.d) {
        b();
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131492892);
    paramBundle = (RecyclerView)findViewById(2131296541);
    EditText localEditText = (EditText)findViewById(2131296560);
    this.d = ((CheckBox)findViewById(2131296580));
    this.c = ((CheckBox)findViewById(2131296398));
    SeekBar localSeekBar = (SeekBar)findViewById(2131296369);
    this.f = ((TextView)findViewById(2131296371));
    paramBundle.setLayoutManager(new LinearLayoutManager(a()));
    this.b = new com.used.aoe.a.a(a(), this.a, getFragmentManager());
    paramBundle.setAdapter(this.b);
    this.b.f();
    this.e = "0";
    localEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        Ac.a(Ac.this, paramAnonymousCharSequence);
        Ac.b(Ac.this).getFilter().filter(Ac.a(Ac.this));
      }
    });
    int i = MultiprocessPreferences.a(a()).a("default_time", 8);
    if (MultiprocessPreferences.a(a()).a("allenabled", true)) {
      this.c.setChecked(true);
    } else {
      this.c.setChecked(false);
    }
    if (i == 600)
    {
      paramBundle = new StringBuilder();
      paramBundle.append(getString(2131689564));
      paramBundle.append(" ");
      paramBundle.append(getString(2131689577));
      paramBundle = paramBundle.toString();
    }
    else
    {
      paramBundle = new StringBuilder();
      paramBundle.append(getString(2131689564));
      paramBundle.append(" ");
      paramBundle.append(i);
      paramBundle.append(" ");
      paramBundle.append(getString(2131689678));
      paramBundle = paramBundle.toString();
    }
    this.f.setText(paramBundle);
    localSeekBar.setProgress(i);
    this.c.setOnCheckedChangeListener(this);
    this.d.setOnCheckedChangeListener(this);
    localSeekBar.setOnSeekBarChangeListener(this);
    b();
    if (!MultiprocessPreferences.a(this).a("p", false))
    {
      this.g = ((AdView)findViewById(2131296606));
      paramBundle = new c.a().a();
      this.g.setAdListener(new com.google.android.gms.ads.a()
      {
        public void a()
        {
          Ac.c(Ac.this).setVisibility(0);
        }
        
        public void a(int paramAnonymousInt)
        {
          Ac.c(Ac.this).setVisibility(8);
        }
      });
      this.g.a(paramBundle);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int i = 2;
      if (paramInt < 2) {
        paramInt = i;
      }
      MultiprocessPreferences.a(a()).a().a("default_time", paramInt).a();
      if (paramInt < 8)
      {
        paramSeekBar = new StringBuilder();
        paramSeekBar.append(getString(2131689617));
        paramSeekBar.append(" Minimum");
        paramSeekBar = paramSeekBar.toString();
      }
      else if (paramInt == 600)
      {
        paramSeekBar = new StringBuilder();
        paramSeekBar.append(getString(2131689617));
        paramSeekBar.append(" ");
        paramSeekBar.append(getString(2131689577));
        paramSeekBar = paramSeekBar.toString();
      }
      else
      {
        paramSeekBar = new StringBuilder();
        paramSeekBar.append(getString(2131689617));
        paramSeekBar.append(" ");
        paramSeekBar.append(paramInt);
        paramSeekBar.append(" ");
        paramSeekBar.append(getString(2131689678));
        paramSeekBar = paramSeekBar.toString();
      }
      this.f.setText(paramSeekBar);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    try
    {
      this.b.f();
      return;
    }
    catch (Exception localException) {}
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar) {}
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    this.b.f();
  }
}
