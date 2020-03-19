package com.dewmobile.kuaiya.act;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ModernAsyncTask;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.dewmobile.kuaiya.dialog.a.a;
import com.dewmobile.kuaiya.ui.CircleAngleTextView;
import com.dewmobile.kuaiya.util.ao;
import com.dewmobile.kuaiya.util.q;
import com.dewmobile.kuaiya.util.z;
import com.dewmobile.kuaiya.view.RoundScaleView;
import com.dewmobile.library.event.DmEventAdvert;
import com.dewmobile.library.j.f;
import com.dewmobile.library.j.l;
import com.dewmobile.library.m.n;
import com.dewmobile.transfer.storage.c;
import com.dewmobile.transfer.storage.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClearStorageActivity
  extends a
  implements View.OnClickListener
{
  private RoundScaleView a;
  private CircleAngleTextView b;
  private TextView c;
  private TextView d;
  private View e;
  private View f;
  private View g;
  private long h;
  private long i;
  private long j;
  private String k = "0.0 KB";
  private String l;
  private Animation m;
  
  public ClearStorageActivity() {}
  
  private void a()
  {
    findViewById(2131558549).setOnClickListener(this);
    ((TextView)findViewById(2131558554)).setText(2131230889);
    this.g = findViewById(2131558500);
    e();
    this.a = ((RoundScaleView)findViewById(2131558491));
    this.c = ((TextView)findViewById(2131558492));
    this.d = ((TextView)findViewById(2131558493));
    this.b = ((CircleAngleTextView)findViewById(2131558497));
    this.e = findViewById(2131558490);
    this.f = findViewById(2131558499);
    this.b.setOnClickListener(this);
    this.a.setStrokeWidth(z.a(this, 100.0F));
    new a(null).execute(new Void[0]);
    this.l = q.a("cm_url", null);
    if (!TextUtils.isEmpty(this.l)) {}
    findViewById(2131558496).setVisibility(8);
    findViewById(2131558498).setVisibility(8);
  }
  
  private void a(String paramString)
  {
    DmEventAdvert localDmEventAdvert = new DmEventAdvert("liebao");
    l localL = new l();
    localL.F = "com.cleanmaster.mguard_cn";
    localL.K = paramString;
    localL.G = "猎豹清理大师";
    localL.L = "http://downloadj.dewmobile.net/upload/web/pic/213bc45b92d2865d6fd62f4220e94537-103117.png";
    localL.P = paramString.hashCode();
    f.a().a(localL, false, localDmEventAdvert);
  }
  
  private void b()
  {
    this.f.setVisibility(8);
    this.e.setVisibility(0);
    List localList = c.a().f();
    if ((localList != null) && (!localList.isEmpty()))
    {
      this.i = ((d)localList.get(0)).e().a;
      this.j = ((d)localList.get(0)).e().b;
      this.h = (this.j - this.i);
      float f1 = (float)this.h;
      float f2 = (float)this.i;
      this.a.setDatas(new float[] { f1, f2 });
      if (TextUtils.isEmpty(this.k)) {
        this.k = "0.0 KB";
      }
    }
    try
    {
      this.c.setText(getString(2131230819, new Object[] { Formatter.formatFileSize(com.dewmobile.library.d.b.a, this.i) }));
      this.d.setText(getString(2131232149, new Object[] { this.k }) + "，" + getString(2131232150, new Object[] { Formatter.formatFileSize(com.dewmobile.library.d.b.a, this.j) }));
      this.a.setColors(new String[] { "#cc071136", "#FFE5E4" });
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private void c()
  {
    if (d())
    {
      n.b("com.cleanmaster.mguard_cn");
      com.dewmobile.kuaiya.g.a.a(com.dewmobile.library.d.b.a(), "z-500-0010", "2");
      return;
    }
    com.dewmobile.kuaiya.g.a.a(com.dewmobile.library.d.b.a(), "z-500-0010", "1");
    a(this.l);
  }
  
  private boolean d()
  {
    Object localObject1 = getPackageManager();
    try
    {
      localObject1 = ((PackageManager)localObject1).getInstalledPackages(128);
      localArrayList = new ArrayList();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext()) {
        localArrayList.add(((PackageInfo)((Iterator)localObject1).next()).packageName);
      }
    }
    catch (Exception localException)
    {
      ArrayList localArrayList;
      for (;;)
      {
        localObject2 = new ArrayList();
      }
      Object localObject2 = localArrayList.iterator();
      while (((Iterator)localObject2).hasNext()) {
        if ("com.cleanmaster.mguard_cn".equals((String)((Iterator)localObject2).next())) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void e()
  {
    this.m = AnimationUtils.loadAnimation(this, 2130968605);
    LinearInterpolator localLinearInterpolator = new LinearInterpolator();
    this.m.setInterpolator(localLinearInterpolator);
    if (this.m != null) {
      this.g.startAnimation(this.m);
    }
  }
  
  private void f()
  {
    if (this.m != null) {
      this.m.cancel();
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131558549: 
      finish();
      return;
    }
    com.dewmobile.kuaiya.g.a.a(com.dewmobile.library.d.b.a(), "z-500-0008");
    c();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    a();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    f();
  }
  
  private class a
    extends ModernAsyncTask<Void, Void, Void>
  {
    private a() {}
    
    protected Void a(Void... paramVarArgs)
    {
      paramVarArgs = z.c();
      try
      {
        ClearStorageActivity.b(ClearStorageActivity.this, z.a(paramVarArgs));
        paramVarArgs = paramVarArgs.iterator();
        while (paramVarArgs.hasNext()) {
          z.b((String)paramVarArgs.next());
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ClearStorageActivity.b(ClearStorageActivity.this);
          ClearStorageActivity.c(ClearStorageActivity.this);
          ao.a(ClearStorageActivity.this, 2131230883);
        }
      }, 2000L);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}
