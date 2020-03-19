package com.netqin.antivirus.scan.ui;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.netqin.antivirus.BaseActivity;
import com.netqin.antivirus.scan.ScanCommon;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class ScanResultItemDetailActivity
  extends BaseActivity
{
  private final int A = 1;
  private boolean a;
  private boolean b;
  private int c;
  private String d;
  private String e;
  private String f;
  private String g;
  private int h;
  private String i;
  private String j;
  private String k;
  private String l;
  private String m;
  private ImageView n;
  private TextView o;
  private TextView p;
  private TextView q;
  private TextView r;
  private TextView s;
  private TextView t;
  private TextView u;
  private LinearLayout v;
  private LinearLayout w;
  private TextView x;
  private PackageManager y;
  private boolean z;
  
  public ScanResultItemDetailActivity() {}
  
  private void a()
  {
    ((TextView)findViewById(2131493058)).setText(2131361810);
  }
  
  private void b()
  {
    if (this.c == 1) {
      if (ScanCommon.a(this.j, this.mContext)) {
        this.x.setText(2131361812);
      }
    }
    for (;;)
    {
      this.z = false;
      return;
      if (this.c == 2) {
        ScanCommon.a(this.d, this.mContext, this);
      }
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903098);
    setRequestedOrientation(1);
    this.y = getPackageManager();
    paramBundle = getIntent().getExtras();
    this.a = paramBundle.getBoolean("isNativeEngineVirus");
    this.b = paramBundle.getBoolean("isDeleted");
    this.c = paramBundle.getInt("type");
    this.d = paramBundle.getString("packageName");
    this.e = paramBundle.getString("category");
    this.f = paramBundle.getString("description");
    this.g = paramBundle.getString("desc");
    this.h = paramBundle.getInt("resultType");
    this.i = paramBundle.getString("cloudsecurityDesc");
    this.j = paramBundle.getString("fullPath");
    this.k = paramBundle.getString("fileName");
    this.l = paramBundle.getString("virusName");
    this.m = paramBundle.getString("programName");
    findViewById(2131493183).setBackgroundColor(getResources().getColor(2131230758));
    this.n = ((ImageView)findViewById(2131493186));
    this.o = ((TextView)findViewById(2131493187));
    this.p = ((TextView)findViewById(2131493188));
    this.q = ((TextView)findViewById(2131493046));
    this.r = ((TextView)findViewById(2131493045));
    this.t = ((TextView)findViewById(2131493190));
    this.u = ((TextView)findViewById(2131493189));
    this.s = ((TextView)findViewById(2131493044));
    this.s.setVisibility(8);
    findViewById(2131493043).setVisibility(8);
    this.v = ((LinearLayout)findViewById(2131493193));
    this.w = ((LinearLayout)findViewById(2131493191));
    this.x = ((TextView)findViewById(2131493194));
    this.v.setOnClickListener(new h(this, null));
    this.w.setOnClickListener(new h(this, null));
    a();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.b) {
      this.v.setVisibility(0);
    }
    this.z = false;
    if (this.c == 1) {
      if (new File(this.j).exists()) {
        this.z = true;
      }
    }
    for (;;)
    {
      if (!this.z) {
        finish();
      }
      if (this.c == 2) {
        this.o.setText(this.m);
      }
      try
      {
        Object localObject = this.y.getApplicationInfo(this.d, 1);
        this.n.setImageDrawable(((ApplicationInfo)localObject).loadIcon(this.y));
        if (TextUtils.isEmpty(this.l))
        {
          this.q.setVisibility(8);
          this.r.setVisibility(8);
          if ((TextUtils.isEmpty(this.i)) || (this.i.equalsIgnoreCase("null"))) {
            break label302;
          }
          this.t.setText(this.i);
          return;
          if (this.c != 2) {
            continue;
          }
          localObject = this.y.getInstalledApplications(8192).iterator();
          do
          {
            if (!((Iterator)localObject).hasNext()) {
              break;
            }
          } while (((ApplicationInfo)((Iterator)localObject).next()).packageName.compareToIgnoreCase(this.d) != 0);
          this.z = true;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          this.n.setImageResource(2130837585);
          continue;
          if ((!TextUtils.isEmpty(this.g)) && (!this.g.equalsIgnoreCase("null"))) {
            this.q.setText(this.g);
          } else {
            this.q.setText(2131361838);
          }
        }
        label302:
        this.u.setVisibility(8);
      }
    }
  }
  
  public void onStop()
  {
    super.onStop();
  }
}
