package com.monotype.android.font.free;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.c.a;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.ads.AdView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class j
  extends android.support.v7.app.d
{
  private com.google.android.gms.ads.d A;
  private ProgressDialog B;
  private SharedPreferences C;
  LayoutInflater n;
  LinearLayout o;
  EditText p;
  Spinner q;
  ListView r;
  List<String> s;
  protected AdView t;
  WeakReference<Activity> u;
  private String v;
  private int w;
  private int x;
  private ArrayAdapter<String> y;
  private ProgressDialog z;
  
  public j() {}
  
  private String a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    try
    {
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      return localByteArrayOutputStream.toString();
    }
    catch (IOException paramInputStream) {}
    for (;;)
    {
      localByteArrayOutputStream.close();
      paramInputStream.close();
    }
  }
  
  protected void a(List<String> paramList)
  {
    if (this.y != null) {
      this.y.clear();
    }
    for (;;)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        this.y.add(str);
      }
      r();
    }
    this.y.notifyDataSetChanged();
    this.z.dismiss();
  }
  
  protected void a(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      this.w = Integer.valueOf(paramString).intValue();
      if (this.y != null) {
        this.y.notifyDataSetChanged();
      }
    }
  }
  
  protected void b(String paramString)
  {
    u();
  }
  
  protected void k()
  {
    try
    {
      Intent localIntent1 = new Intent();
      localIntent1.setClassName("com.android.settings", "com.android.settings.flipfont.FontListProgressActivity");
      localIntent1.setFlags(268435456);
      startActivity(localIntent1);
      return;
    }
    catch (Exception localException1)
    {
      try
      {
        Intent localIntent2 = new Intent();
        localIntent2.setClassName("com.sec.android.easysettings", "com.sec.android.easysettings.font.FontSetting");
        localIntent2.setFlags(268435456);
        startActivity(localIntent2);
        return;
      }
      catch (Exception localException2)
      {
        Intent localIntent3 = new Intent("android.settings.DISPLAY_SETTINGS");
        localIntent3.setFlags(268435456);
        startActivity(localIntent3);
      }
    }
  }
  
  protected void l()
  {
    MessageMakerActivity_.a(this).a();
  }
  
  protected void m()
  {
    SurveyActivity_.a(this).a();
  }
  
  protected void n()
  {
    v();
  }
  
  protected void o()
  {
    if (this.C.getBoolean("Subscribed", false)) {
      this.t.setVisibility(8);
    }
    g().a(2131165305);
    p();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.u = new WeakReference(this);
    this.C = getSharedPreferences("APP_PREFS", 0);
    this.A = new com.google.android.gms.ads.d(this);
    this.A.a(getString(2131165265));
    this.A.a(new a());
    this.v = getString(2131165328);
    this.w = 24;
    this.x = -16777216;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.t.a();
  }
  
  protected void onPause()
  {
    this.t.b();
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.t.c();
    this.t.a(f.a());
  }
  
  protected void p()
  {
    this.z = ProgressDialog.show(this, null, getString(2131165331));
    try
    {
      q();
      return;
    }
    catch (Exception localException)
    {
      this.z.dismiss();
      localException.printStackTrace();
    }
  }
  
  protected void q()
  {
    this.s = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject2 = (ApplicationInfo)localIterator.next();
        if (((ApplicationInfo)localObject2).packageName.indexOf(getPackageName()) < 0) {
          continue;
        }
        Object localObject3;
        Object localObject1;
        try
        {
          Resources localResources = getPackageManager().getResourcesForApplication(((ApplicationInfo)localObject2).packageName);
          localObject3 = Arrays.asList(localResources.getAssets().list("fonts"));
          localObject1 = new ArrayList();
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            String str = (String)((Iterator)localObject3).next();
            if (!str.contains("NUMERALS")) {
              this.s.add(((ApplicationInfo)localObject2).packageName + "~!~" + str);
            }
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        localObject2 = Arrays.asList(localException.getAssets().list("xml")).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          if (!((String)localObject3).contains("NUMERALS")) {
            ((List)localObject1).add(localObject3);
          }
        }
        try
        {
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localObject2 = a(localException.getAssets().open("xml/" + (String)localObject2));
            localArrayList.add(((String)localObject2).substring(((String)localObject2).indexOf("displayname=") + 13, ((String)localObject2).indexOf("\"", ((String)localObject2).indexOf("displayname=") + 13)));
          }
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
      }
    }
    a(localArrayList);
  }
  
  protected void r()
  {
    this.y = new ArrayAdapter(this, 0)
    {
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        if (paramAnonymousView == null)
        {
          paramAnonymousView = j.this.n.inflate(2130903083, null);
          paramAnonymousViewGroup = new j.b(j.this);
          paramAnonymousViewGroup.a = ((TextView)paramAnonymousView.findViewById(2131624033));
          paramAnonymousViewGroup.b = ((TextView)paramAnonymousView.findViewById(2131624030));
          paramAnonymousView.setTag(paramAnonymousViewGroup);
        }
        for (;;)
        {
          Object localObject = (String)getItem(paramAnonymousInt);
          paramAnonymousViewGroup.b.setText((CharSequence)localObject);
          try
          {
            localObject = ((String)j.this.s.get(paramAnonymousInt)).split("~!~");
            localObject = Typeface.createFromAsset(j.this.getPackageManager().getResourcesForApplication(localObject[0]).getAssets(), "fonts/" + localObject[1]);
            paramAnonymousViewGroup.a.setTypeface((Typeface)localObject);
            paramAnonymousViewGroup.a.setText(j.a(j.this));
            paramAnonymousViewGroup.a.setTextSize(j.b(j.this));
            paramAnonymousViewGroup.a.setTextColor(j.c(j.this));
            return paramAnonymousView;
            paramAnonymousViewGroup = (j.b)paramAnonymousView.getTag();
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
            }
          }
        }
      }
    };
    this.r.setAdapter(this.y);
  }
  
  protected void s()
  {
    if ((TextUtils.isEmpty(this.p.getText())) || (this.p.getText().toString().contains(getString(2131165329)))) {
      this.v = getString(2131165328);
    }
    for (;;)
    {
      if (this.y != null) {
        this.y.notifyDataSetChanged();
      }
      return;
      this.v = this.p.getText().toString();
      if (this.v.equalsIgnoreCase("blahblahblah")) {
        IconActivity_.a(this).a();
      } else if (this.v.equalsIgnoreCase("qwertyqwerty")) {
        ScreenActivity_.a(this).a();
      } else if (this.v.equalsIgnoreCase("asdfasdf")) {
        FeatureActivity_.a(this).a();
      } else if (this.v.equalsIgnoreCase("poiuy")) {
        LetterActivity_.a(this).a();
      }
    }
  }
  
  protected void t()
  {
    int i = this.x;
    this.x = (-1 - i - 16777216);
    this.o.setBackgroundColor(i);
    this.y.notifyDataSetChanged();
  }
  
  protected void u()
  {
    c.a localA;
    if ((this.u != null) && (this.u.get() != null) && (!((Activity)this.u.get()).isFinishing()))
    {
      localA = new c.a((Context)this.u.get());
      localA.a(2131165322);
      if (!this.C.getBoolean("Subscribed", false)) {
        break label146;
      }
      localA.b(getString(2131165320, new Object[] { getString(17039370) }));
    }
    for (;;)
    {
      localA.a(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (j.d(j.this) != null)
          {
            j.a(j.this, ProgressDialog.show(j.this, null, "Loading..."));
            j.d(j.this).a(f.a());
          }
        }
      });
      if (this.C.getBoolean("Subscribed", false)) {
        localA.b(17039360, null);
      }
      localA.c();
      return;
      label146:
      localA.b(getString(2131165318, new Object[] { getString(17039370) }));
    }
  }
  
  protected void v()
  {
    if ((this.u != null) && (this.u.get() != null) && (!((Activity)this.u.get()).isFinishing()))
    {
      c.a localA = new c.a((Context)this.u.get());
      localA.a(2131165239);
      View localView = this.n.inflate(2130903078, null);
      CheckBox localCheckBox = (CheckBox)localView.findViewById(2131624063);
      localCheckBox.setChecked(this.C.getBoolean("SettingsNotifications", true));
      localCheckBox.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          boolean bool = true;
          paramAnonymousView = j.e(j.this).edit();
          if (!j.e(j.this).getBoolean("SettingsNotifications", true)) {}
          for (;;)
          {
            paramAnonymousView.putBoolean("SettingsNotifications", bool).apply();
            return;
            bool = false;
          }
        }
      });
      localA.b(localView);
      localA.a(17039370, null);
      localA.c();
    }
  }
  
  class a
    extends com.google.android.gms.ads.a
  {
    a() {}
    
    public void onAdLoaded()
    {
      super.onAdLoaded();
      if (j.f(j.this) != null) {
        j.f(j.this).dismiss();
      }
      j.d(j.this).b();
    }
  }
  
  class b
  {
    public TextView a;
    public TextView b;
    
    b() {}
  }
}
