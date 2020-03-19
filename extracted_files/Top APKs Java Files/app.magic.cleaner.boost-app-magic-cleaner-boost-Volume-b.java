package app.magic.cleaner.boost.Volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.g;
import android.support.v4.app.h;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.f;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import app.magic.cleaner.boost.BCJ.Cpu_Scanner;
import app.magic.cleaner.boost.MainActivity;
import app.magic.cleaner.boost.a.a;
import app.magic.cleaner.boost.d;
import b.a.a.a.c;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class b
  extends g
{
  public static List<a> ag;
  TextView a;
  List<a> ah;
  int ai = 0;
  BroadcastReceiver aj = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      try
      {
        paramAnonymousIntent.getIntExtra("level", 0);
        b.this.e = (paramAnonymousIntent.getIntExtra("temperature", 0) / 10.0F);
        paramAnonymousIntent = b.this.a;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(b.this.e);
        localStringBuilder.append("°C");
        paramAnonymousIntent.setText(localStringBuilder.toString());
        if (b.this.e >= 30.0D)
        {
          b.ag = new ArrayList();
          b.this.ah = new ArrayList();
          b.this.g.setImageResource(2131230947);
          b.this.b.setText(2131689616);
          b.this.b.setTextColor(Color.parseColor("#F22938"));
          b.this.c.setText(2131689521);
          b.this.d.setText("");
          b.this.f.setText(2131689564);
          b.this.f.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              paramAnonymous2View = new Intent(b.this.n(), Cpu_Scanner.class);
              b.this.a(paramAnonymous2View);
              new Handler().postDelayed(new Runnable()
              {
                public void run()
                {
                  b.this.d.setText(2131689576);
                  b.this.b.setText(2131689611);
                  b.this.b.setTextColor(Color.parseColor("#24D149"));
                  b.this.c.setText(2131689574);
                  b.this.c.setTextColor(Color.parseColor("#24D149"));
                  b.this.f.setText(2131689565);
                  b.this.g.setImageResource(2131230825);
                  b.this.a.setText("25.3°C");
                  b.this.h.setAdapter(null);
                }
              }, 2000L);
              b.this.f.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous3View)
                {
                  paramAnonymous3View = b.this.i(b.this.j()).inflate(2131492931, null);
                  Object localObject = (ImageView)paramAnonymous3View.findViewById(2131296424);
                  ((TextView)paramAnonymous3View.findViewById(2131296578)).setText(2131689573);
                  localObject = new Toast(b.this.n());
                  ((Toast)localObject).setGravity(16, 0, 70);
                  ((Toast)localObject).setDuration(1);
                  ((Toast)localObject).setView(paramAnonymous3View);
                  ((Toast)localObject).show();
                }
              });
            }
          });
          if (Build.VERSION.SDK_INT < 23) {
            b.this.c.setTextAppearance(paramAnonymousContext, 16973892);
          }
          for (paramAnonymousContext = b.this.c;; paramAnonymousContext = b.this.c)
          {
            paramAnonymousContext.setTextColor(Color.parseColor("#F22938"));
            break;
            b.this.c.setTextAppearance(16973894);
          }
          b.this.h.setItemAnimator(new b.a.a.a.b());
          b.this.h.getItemAnimator().a(10000L);
          b.this.i = new d(b.ag);
          paramAnonymousContext = new LinearLayoutManager(b.this.n().getApplicationContext(), 0, false);
          b.this.h.setLayoutManager(paramAnonymousContext);
          b.this.h.setItemAnimator(new c(new OvershootInterpolator(1.0F)));
          b.this.h.computeHorizontalScrollExtent();
          b.this.h.setAdapter(b.this.i);
          b.this.c();
        }
        return;
      }
      catch (Exception paramAnonymousContext) {}
    }
  };
  View ak;
  TextView b;
  TextView c;
  TextView d;
  float e;
  Button f;
  ImageView g;
  RecyclerView h;
  d i;
  
  public b() {}
  
  public View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.ak = paramLayoutInflater.inflate(2131492902, paramViewGroup, false);
    try
    {
      n().registerReceiver(this.aj, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      this.h = ((RecyclerView)this.ak.findViewById(2131296494));
      this.g = ((ImageView)this.ak.findViewById(2131296570));
      this.b = ((TextView)this.ak.findViewById(2131296538));
      this.c = ((TextView)this.ak.findViewById(2131296539));
      this.f = ((Button)this.ak.findViewById(2131296370));
      this.d = ((TextView)this.ak.findViewById(2131296468));
      this.d.setText(2131689576);
      this.b.setText(2131689611);
      this.c.setText(2131689574);
      this.f.setText(2131689565);
      this.f.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = b.this.i(b.this.j()).inflate(2131492931, null);
          Object localObject = (ImageView)paramAnonymousView.findViewById(2131296424);
          ((TextView)paramAnonymousView.findViewById(2131296578)).setText(2131689573);
          localObject = new Toast(b.this.n());
          ((Toast)localObject).setGravity(16, 0, 70);
          ((Toast)localObject).setDuration(1);
          ((Toast)localObject).setView(paramAnonymousView);
          ((Toast)localObject).show();
        }
      });
      this.g.setImageResource(2131230825);
      this.a = ((TextView)this.ak.findViewById(2131296328));
      paramLayoutInflater = new StringBuilder();
      paramLayoutInflater.append(this.e);
      paramLayoutInflater.append("");
      Log.e("Temperrature", paramLayoutInflater.toString());
      return this.ak;
    }
    catch (Exception paramLayoutInflater)
    {
      for (;;) {}
    }
  }
  
  public void c()
  {
    PackageManager localPackageManager = n().getPackageManager();
    List localList = localPackageManager.getInstalledApplications(128);
    if (localList != null)
    {
      int j = 0;
      while (j < localList.size())
      {
        String str = ((ApplicationInfo)localList.get(j)).packageName;
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("");
        ((StringBuilder)localObject1).append(str);
        Log.e("packageName-->", ((StringBuilder)localObject1).toString());
        if (!str.equals("app.magic.cleaner.booost")) {
          try
          {
            localObject1 = (String)localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(str, 128));
            localObject1 = new a();
            long l = new File(localPackageManager.getApplicationInfo(str, 128).publicSourceDir).length();
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(l / 1000000L);
            ((StringBuilder)localObject2).append("");
            Log.e("SIZE", ((StringBuilder)localObject2).toString());
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(l / 1000000L + 20L);
            ((StringBuilder)localObject2).append("MB");
            ((a)localObject1).a(((StringBuilder)localObject2).toString());
            localObject2 = localPackageManager.getApplicationInfo(str, 128);
            Drawable localDrawable = n().getPackageManager().getApplicationIcon(((ApplicationInfo)localList.get(j)).packageName);
            ((a)localObject1).a(localDrawable);
            n().getPackageManager();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("");
            localStringBuilder.append(localDrawable);
            Log.e("ico-->", localStringBuilder.toString());
            if ((((ApplicationInfo)localObject2).flags & 0x1) == 0) {
              if (this.ai <= 5)
              {
                this.ai += 1;
                ag.add(localObject1);
              }
              else
              {
                n().unregisterReceiver(this.aj);
                break;
              }
            }
            this.i.c();
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Unable to find icon for package '");
            ((StringBuilder)localObject2).append(str);
            ((StringBuilder)localObject2).append("': ");
            ((StringBuilder)localObject2).append(localNameNotFoundException.getMessage());
            Log.e("ERROR", ((StringBuilder)localObject2).toString());
          }
        }
        j += 1;
      }
    }
    if (ag.size() > 1)
    {
      this.i = new d(ag);
      this.i.c();
    }
  }
  
  public void f(boolean paramBoolean)
  {
    super.f(paramBoolean);
    if (paramBoolean) {
      MainActivity.k.setText(2131689568);
    }
  }
  
  public void z()
  {
    super.z();
    try
    {
      n().unregisterReceiver(this.aj);
      return;
    }
    catch (Exception localException) {}
  }
}
