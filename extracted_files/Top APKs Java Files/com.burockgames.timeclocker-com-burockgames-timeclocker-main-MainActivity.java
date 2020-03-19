package com.burockgames.timeclocker.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.a;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.c;
import android.support.v7.app.e;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import com.b.a.a.a.c;
import com.b.a.a.a.c.b;
import com.burockgames.timeclocker.a.d;
import com.burockgames.timeclocker.a.f;
import com.burockgames.timeclocker.a.g;
import com.burockgames.timeclocker.about.About;
import com.burockgames.timeclocker.alarm.Alarm;
import com.burockgames.timeclocker.applist.AppList;
import com.burockgames.timeclocker.help.Help;
import com.burockgames.timeclocker.history.History;
import com.burockgames.timeclocker.service.RestartedService;
import com.burockgames.timeclocker.settings.Settings;
import com.burockgames.timeclocker.shop.Shop;
import com.burockgames.timeclocker.support.Support;
import com.google.android.gms.ads.i;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MainActivity
  extends e
  implements NavigationView.a
{
  private final int a = 0;
  private g b;
  private PackageManager c = null;
  private NavigationView d;
  private LinearLayout e;
  private ListView f;
  private f g;
  private a h;
  private List<b> i = null;
  private List<b> j = null;
  private int k;
  private long l;
  private int m = 1;
  private int n = 0;
  private int o = 1;
  private boolean p = false;
  private int q;
  private int r;
  
  public MainActivity() {}
  
  private void a()
  {
    i.a(this, "ca-app-pub-2348383263850497~4777854318");
    i.a(0.1F);
    if (c.a(getApplicationContext()))
    {
      localObject1 = new com.burockgames.timeclocker.a.a(this, getApplicationContext(), this.b);
      localObject2 = new c(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj/VXs36Pu/9ou7c6/lH567rEYMFbCv2mQM26OaXSrzqnFwlRMgODWTn39Rk3YY7gBzsP2xWdhixtH3nAeRwylUdR+QHK3pVfMeZbiOq5wlbPXPoV1P7ZhCjjNv16AA5SSiP8G9rrS7jYqZIOEmWZf1vEdzYUhAr3ca2/dHvQ1G+lyTJ+9aP5sRCc1h8EqvIGFRiC31QN6ICKfaReeZKP89ktI+bjgs248I3Tu7ru1je1uLobMcGqp2OQ11AefRHOeDdWCoPY+W+dmSVKcuwsEusKOcZI0QuR5z5+8rsvVXHA+M5xKezuxBegPJioxVNUCzEA06eXTn5+piU7e1zM1wIDAQAB", (c.b)localObject1);
      ((com.burockgames.timeclocker.a.a)localObject1).a((c)localObject2);
      ((c)localObject2).c();
    }
    int i1 = this.r;
    if (i1 == 0) {
      findViewById(2131296563).setBackgroundResource(2131230832);
    } else if (i1 == 1) {
      findViewById(2131296563).setBackgroundResource(2131230833);
    }
    this.e = ((LinearLayout)findViewById(2131296497));
    this.f = ((ListView)findViewById(2131296516));
    this.f.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (((b)MainActivity.a(MainActivity.this).get(paramAnonymousInt)).a().equals("com.burockgames.ot_her"))
        {
          Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getResources().getString(2131624155), 1).show();
          return;
        }
        MainActivity.a(MainActivity.this, paramAnonymousInt);
        paramAnonymousAdapterView = new int[2];
        paramAnonymousView.getLocationInWindow(paramAnonymousAdapterView);
        MainActivity.b(MainActivity.this, paramAnonymousAdapterView[1]);
      }
    });
    Object localObject2 = (Toolbar)findViewById(2131296714);
    setSupportActionBar((Toolbar)localObject2);
    ((FloatingActionButton)findViewById(2131296395)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(MainActivity.this.getApplicationContext(), AppList.class);
        MainActivity.this.startActivity(paramAnonymousView);
      }
    });
    Object localObject1 = (DrawerLayout)findViewById(2131296373);
    localObject2 = new android.support.v7.app.b(this, (DrawerLayout)localObject1, (Toolbar)localObject2, 2131624154, 2131624153);
    ((DrawerLayout)localObject1).a((DrawerLayout.c)localObject2);
    ((android.support.v7.app.b)localObject2).a();
    this.d = ((NavigationView)findViewById(2131296528));
    this.d.setNavigationItemSelectedListener(this);
    ((TextView)findViewById(2131296668)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!MainActivity.b(MainActivity.this)) {
          return;
        }
        MainActivity.c(MainActivity.this, 0);
        if (MainActivity.c(MainActivity.this) == 0)
        {
          paramAnonymousView = MainActivity.this;
          MainActivity.a(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        else
        {
          paramAnonymousView = MainActivity.this;
          MainActivity.b(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        paramAnonymousView = MainActivity.this;
        WindowManager localWindowManager = paramAnonymousView.getWindowManager();
        MainActivity localMainActivity = MainActivity.this;
        MainActivity.a(paramAnonymousView, new a(localWindowManager, localMainActivity, MainActivity.d(localMainActivity), MainActivity.a(MainActivity.this), MainActivity.e(MainActivity.this), MainActivity.f(MainActivity.this).c()));
        MainActivity.h(MainActivity.this).setAdapter(MainActivity.g(MainActivity.this));
      }
    });
    ((TextView)findViewById(2131296669)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!MainActivity.b(MainActivity.this)) {
          return;
        }
        MainActivity.c(MainActivity.this, 1);
        if (MainActivity.i(MainActivity.this) == 0)
        {
          paramAnonymousView = MainActivity.this;
          MainActivity.c(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        else
        {
          paramAnonymousView = MainActivity.this;
          MainActivity.d(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        paramAnonymousView = MainActivity.this;
        WindowManager localWindowManager = paramAnonymousView.getWindowManager();
        MainActivity localMainActivity = MainActivity.this;
        MainActivity.a(paramAnonymousView, new a(localWindowManager, localMainActivity, MainActivity.d(localMainActivity), MainActivity.a(MainActivity.this), MainActivity.e(MainActivity.this), MainActivity.f(MainActivity.this).c()));
        MainActivity.h(MainActivity.this).setAdapter(MainActivity.g(MainActivity.this));
      }
    });
    localObject1 = (ImageView)findViewById(2131296427);
    ((ImageView)localObject1).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!MainActivity.b(MainActivity.this)) {
          return;
        }
        MainActivity.c(MainActivity.this, 0);
        if (MainActivity.c(MainActivity.this) == 0)
        {
          MainActivity.d(MainActivity.this, 1);
          this.a.setImageResource(2131230928);
          paramAnonymousView = MainActivity.this;
          MainActivity.b(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        else
        {
          MainActivity.d(MainActivity.this, 0);
          this.a.setImageResource(2131230927);
          paramAnonymousView = MainActivity.this;
          MainActivity.a(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        paramAnonymousView = MainActivity.this;
        WindowManager localWindowManager = paramAnonymousView.getWindowManager();
        MainActivity localMainActivity = MainActivity.this;
        MainActivity.a(paramAnonymousView, new a(localWindowManager, localMainActivity, MainActivity.d(localMainActivity), MainActivity.a(MainActivity.this), MainActivity.e(MainActivity.this), MainActivity.f(MainActivity.this).c()));
        MainActivity.h(MainActivity.this).setAdapter(MainActivity.g(MainActivity.this));
      }
    });
    localObject1 = (ImageView)findViewById(2131296428);
    ((ImageView)localObject1).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!MainActivity.b(MainActivity.this)) {
          return;
        }
        MainActivity.c(MainActivity.this, 1);
        if (MainActivity.i(MainActivity.this) == 0)
        {
          MainActivity.e(MainActivity.this, 1);
          this.a.setImageResource(2131230928);
          paramAnonymousView = MainActivity.this;
          MainActivity.d(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        else
        {
          MainActivity.e(MainActivity.this, 0);
          this.a.setImageResource(2131230927);
          paramAnonymousView = MainActivity.this;
          MainActivity.c(paramAnonymousView, MainActivity.a(paramAnonymousView));
        }
        paramAnonymousView = MainActivity.this;
        WindowManager localWindowManager = paramAnonymousView.getWindowManager();
        MainActivity localMainActivity = MainActivity.this;
        MainActivity.a(paramAnonymousView, new a(localWindowManager, localMainActivity, MainActivity.d(localMainActivity), MainActivity.a(MainActivity.this), MainActivity.e(MainActivity.this), MainActivity.f(MainActivity.this).c()));
        MainActivity.h(MainActivity.this).setAdapter(MainActivity.g(MainActivity.this));
      }
    });
    if ((!this.b.l().equals("--")) && (!this.b.l().equals(new SimpleDateFormat("dd", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()))))) {
      l();
    }
    f.a(this.b.m(), getApplicationContext());
    if (com.burockgames.timeclocker.a.b.a == 0L) {
      com.burockgames.timeclocker.a.b.a = System.currentTimeMillis() - 300000L;
    }
  }
  
  private void a(int paramInt)
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427407, (ViewGroup)findViewById(2131296493));
    ((TextView)localView.findViewById(2131296702)).setText(((b)this.j.get(this.k)).b());
    Object localObject1 = this.b.c();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((b)this.j.get(this.k)).a());
    ((StringBuilder)localObject2).append("---");
    final boolean bool = ((String)localObject1).contains(((StringBuilder)localObject2).toString());
    ((LinearLayout)localView.findViewById(2131296505)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (bool) {
          MainActivity.a(MainActivity.this, true);
        } else {
          MainActivity.k(MainActivity.this);
        }
        localDialog.dismiss();
      }
    });
    localObject1 = (LinearLayout)localView.findViewById(2131296501);
    ((LinearLayout)localObject1).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.l(MainActivity.this);
        localDialog.dismiss();
      }
    });
    ((LinearLayout)localView.findViewById(2131296480)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        MainActivity.m(MainActivity.this);
      }
    });
    ((LinearLayout)localView.findViewById(2131296468)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        MainActivity.n(MainActivity.this);
      }
    });
    if (localDialog.getWindow() != null)
    {
      localObject2 = localDialog.getWindow().getAttributes();
      ((WindowManager.LayoutParams)localObject2).gravity = 49;
      ((WindowManager.LayoutParams)localObject2).y = paramInt;
    }
    if (((b)this.j.get(this.k)).a().equals("com.burockgames.to_tal"))
    {
      ((LinearLayout)localObject1).setVisibility(8);
      localView.findViewById(2131296734).setVisibility(8);
    }
    localObject1 = (ImageView)localView.findViewById(2131296416);
    localObject2 = (ImageView)localView.findViewById(2131296430);
    ImageView localImageView1 = (ImageView)localView.findViewById(2131296423);
    ImageView localImageView2 = (ImageView)localView.findViewById(2131296420);
    if (bool)
    {
      ((ImageView)localObject1).setImageResource(2131230850);
      ((TextView)localView.findViewById(2131296701)).setText(getResources().getString(2131624189));
    }
    paramInt = android.support.v4.a.a.c(getApplicationContext(), 2131099725);
    ((ImageView)localObject1).setColorFilter(paramInt);
    ((ImageView)localObject2).setColorFilter(paramInt);
    localImageView1.setColorFilter(paramInt);
    localImageView2.setColorFilter(paramInt);
    localDialog.setContentView(localView);
    localDialog.show();
  }
  
  private void a(List<b> paramList)
  {
    int i1 = 0;
    while (i1 < paramList.size() - 2)
    {
      int i3;
      for (int i2 = 1; i2 < paramList.size() - 3; i2 = i3)
      {
        Object localObject = ((b)paramList.get(i2)).b();
        i3 = i2 + 1;
        if (((String)localObject).compareTo(((b)paramList.get(i3)).b()) > 0)
        {
          localObject = (b)paramList.get(i3);
          paramList.set(i3, paramList.get(i2));
          paramList.set(i2, localObject);
        }
      }
      i1 += 1;
    }
  }
  
  private void a(boolean paramBoolean)
  {
    this.g.c(((b)this.j.get(this.k)).a());
    if (paramBoolean) {
      Toast.makeText(getApplicationContext(), getResources().getString(2131623978), 0).show();
    }
    b();
  }
  
  private void b()
  {
    this.d = ((NavigationView)findViewById(2131296528));
    this.d.getMenu().getItem(0).setChecked(true);
    this.m = this.b.e();
    this.n = this.b.f();
    this.o = this.b.g();
    new a(null).execute(new String[0]);
    this.g.j();
  }
  
  private void b(List<b> paramList)
  {
    int i1 = 0;
    while (i1 < paramList.size() - 2)
    {
      int i3;
      for (int i2 = 1; i2 < paramList.size() - 3; i2 = i3)
      {
        Object localObject = ((b)paramList.get(i2)).b();
        i3 = i2 + 1;
        if (((String)localObject).compareTo(((b)paramList.get(i3)).b()) < 0)
        {
          localObject = (b)paramList.get(i3);
          paramList.set(i3, paramList.get(i2));
          paramList.set(i2, localObject);
        }
      }
      i1 += 1;
    }
  }
  
  private void c()
  {
    if ((Build.VERSION.SDK_INT >= 23) && (android.support.v4.a.a.a(this, "android.permission.READ_EXTERNAL_STORAGE") != 0))
    {
      android.support.v4.app.a.a(this, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" }, 0);
      return;
    }
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    Object localObject = (LayoutInflater)getSystemService("layout_inflater");
    if (localObject == null) {
      return;
    }
    localObject = ((LayoutInflater)localObject).inflate(2131427435, (ViewGroup)findViewById(2131296506));
    ((TextView)((View)localObject).findViewById(2131296681)).setText(getResources().getString(2131624068));
    Button localButton = (Button)((View)localObject).findViewById(2131296339);
    localButton.setText(getResources().getString(2131624049));
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localButton = (Button)((View)localObject).findViewById(2131296340);
    localButton.setText(getResources().getString(2131624169));
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        MainActivity.j(MainActivity.this);
      }
    });
    localDialog.setContentView((View)localObject);
    localDialog.show();
  }
  
  private void c(List<b> paramList)
  {
    int i1 = 0;
    while (i1 < paramList.size() - 2)
    {
      int i3;
      for (int i2 = 1; i2 < paramList.size() - 3; i2 = i3)
      {
        long l1 = ((b)paramList.get(i2)).e();
        i3 = i2 + 1;
        if (l1 > ((b)paramList.get(i3)).e())
        {
          b localB = (b)paramList.get(i3);
          paramList.set(i3, paramList.get(i2));
          paramList.set(i2, localB);
        }
      }
      i1 += 1;
    }
  }
  
  private void d()
  {
    try
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(Environment.getExternalStorageDirectory());
      ((StringBuilder)localObject1).append("/StayFree");
      localObject1 = new File(((StringBuilder)localObject1).toString());
      if ((!((File)localObject1).isDirectory()) && (!((File)localObject1).mkdirs()))
      {
        Toast.makeText(getApplicationContext(), getResources().getString(2131624073), 0).show();
        return;
      }
      Object localObject2 = getWindow().getDecorView().getRootView();
      ((View)localObject2).setDrawingCacheEnabled(true);
      localObject1 = Bitmap.createBitmap(((View)localObject2).getDrawingCache());
      ((View)localObject2).setDrawingCacheEnabled(false);
      localObject2 = new Date();
      DateFormat.format("yyyy-MM-dd_hh:mm:ss", (Date)localObject2);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Environment.getExternalStorageDirectory().toString());
      localStringBuilder.append("/StayFree/");
      localStringBuilder.append(localObject2);
      localStringBuilder.append(".jpg");
      localObject2 = new FileOutputStream(new File(localStringBuilder.toString()));
      ((Bitmap)localObject1).compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject2);
      ((FileOutputStream)localObject2).flush();
      ((FileOutputStream)localObject2).close();
      Toast.makeText(getApplicationContext(), getResources().getString(2131624304), 0).show();
      localObject1 = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), (Bitmap)localObject1, "Image", null));
      localObject2 = new Intent();
      ((Intent)localObject2).setAction("android.intent.action.SEND");
      ((Intent)localObject2).putExtra("android.intent.extra.STREAM", (Parcelable)localObject1);
      ((Intent)localObject2).setType("image/*");
      startActivity(Intent.createChooser((Intent)localObject2, "Share Image"));
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    Toast.makeText(getApplicationContext(), getResources().getString(2131624186), 0).show();
  }
  
  private void d(List<b> paramList)
  {
    int i1 = 0;
    while (i1 < paramList.size() - 2)
    {
      int i3;
      for (int i2 = 1; i2 < paramList.size() - 3; i2 = i3)
      {
        long l1 = ((b)paramList.get(i2)).e();
        i3 = i2 + 1;
        if (l1 < ((b)paramList.get(i3)).e())
        {
          b localB = (b)paramList.get(i3);
          paramList.set(i3, paramList.get(i2));
          paramList.set(i2, localB);
        }
      }
      i1 += 1;
    }
  }
  
  private void e()
  {
    if (((b)this.j.get(this.k)).a().equals("com.burockgames.to_tal")) {
      localObject1 = this.g.a();
    } else {
      localObject1 = this.g.a(((b)this.j.get(this.k)).a());
    }
    if (((String)localObject1).equals(""))
    {
      Toast.makeText(getApplicationContext(), getResources().getString(2131624164), 0).show();
      return;
    }
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427405, (ViewGroup)findViewById(2131296468));
    if (localDialog.getWindow() != null) {
      localDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    ((TextView)localView.findViewById(2131296686)).setText(((b)this.j.get(this.k)).b());
    ((TextView)localView.findViewById(2131296643)).setText((CharSequence)localObject1);
    Object localObject1 = (ImageView)localView.findViewById(2131296421);
    try
    {
      localObject2 = ((b)this.j.get(this.k)).c();
      if (localObject2 != null) {
        ((ImageView)localObject1).setImageBitmap((Bitmap)localObject2);
      } else {
        ((ImageView)localObject1).setBackground(((b)this.j.get(this.k)).d());
      }
    }
    catch (Exception localException)
    {
      Object localObject2;
      int i1;
      for (;;) {}
    }
    ((ImageView)localObject1).setBackground(android.support.v4.a.a.a(this, 2131230892));
    localObject1 = getWindowManager().getDefaultDisplay();
    localObject2 = new Point();
    ((Display)localObject1).getSize((Point)localObject2);
    i1 = ((Point)localObject2).y / 2;
    ((ScrollView)localView.findViewById(2131296578)).setLayoutParams(new LinearLayout.LayoutParams((int)(((Point)localObject2).x / 1.5D), i1));
    ((Button)localView.findViewById(2131296317)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localDialog.setContentView(localView);
    localDialog.show();
  }
  
  private void f()
  {
    this.b.a("lastChosenHistory", ((b)this.j.get(this.k)).a());
    startActivity(new Intent(getApplicationContext(), History.class));
  }
  
  private void g()
  {
    final Dialog localDialog = new Dialog(this);
    final boolean bool = true;
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427358, (ViewGroup)findViewById(2131296466));
    Object localObject9 = (TextView)localView.findViewById(2131296637);
    Object localObject8 = (Button)localView.findViewById(2131296304);
    Object localObject7 = (Button)localView.findViewById(2131296308);
    Object localObject6 = (Button)localView.findViewById(2131296305);
    Object localObject5 = (Button)localView.findViewById(2131296306);
    Object localObject4 = (Button)localView.findViewById(2131296307);
    Object localObject3 = (Button)localView.findViewById(2131296309);
    Object localObject2 = (ImageButton)localView.findViewById(2131296310);
    if (((b)this.j.get(this.k)).a().equals("com.burockgames.to_tal"))
    {
      ((ImageButton)localObject2).setImageResource(2131230893);
    }
    else
    {
      i1 = this.b.d();
      if (i1 == 1) {
        ((ImageButton)localObject2).setImageResource(2131230810);
      } else if (i1 == 2) {
        ((ImageButton)localObject2).setImageResource(2131230834);
      } else {
        ((ImageButton)localObject2).setImageResource(2131230893);
      }
      bool = false;
    }
    Object localObject1 = (Button)localView.findViewById(2131296312);
    Object localObject10 = (Button)localView.findViewById(2131296311);
    final SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
    ((Button)localObject8).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText());
          paramAnonymousView.append("");
          paramAnonymousView = paramAnonymousView.toString();
          paramAnonymousView = localSimpleDateFormat.parse(paramAnonymousView);
          paramAnonymousView.setTime(paramAnonymousView.getTime() + 60000L);
          this.a.setText(localSimpleDateFormat.format(paramAnonymousView));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    ((Button)localObject7).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText());
          paramAnonymousView.append("");
          paramAnonymousView = paramAnonymousView.toString();
          paramAnonymousView = localSimpleDateFormat.parse(paramAnonymousView);
          paramAnonymousView.setTime(paramAnonymousView.getTime() + 300000L);
          this.a.setText(localSimpleDateFormat.format(paramAnonymousView));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    ((Button)localObject6).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText());
          paramAnonymousView.append("");
          paramAnonymousView = paramAnonymousView.toString();
          paramAnonymousView = localSimpleDateFormat.parse(paramAnonymousView);
          paramAnonymousView.setTime(paramAnonymousView.getTime() + 600000L);
          this.a.setText(localSimpleDateFormat.format(paramAnonymousView));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    ((Button)localObject5).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText());
          paramAnonymousView.append("");
          paramAnonymousView = paramAnonymousView.toString();
          paramAnonymousView = localSimpleDateFormat.parse(paramAnonymousView);
          paramAnonymousView.setTime(paramAnonymousView.getTime() + 900000L);
          this.a.setText(localSimpleDateFormat.format(paramAnonymousView));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    ((Button)localObject4).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText());
          paramAnonymousView.append("");
          paramAnonymousView = paramAnonymousView.toString();
          paramAnonymousView = localSimpleDateFormat.parse(paramAnonymousView);
          paramAnonymousView.setTime(paramAnonymousView.getTime() + 1800000L);
          this.a.setText(localSimpleDateFormat.format(paramAnonymousView));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    ((Button)localObject3).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText());
          paramAnonymousView.append("");
          paramAnonymousView = paramAnonymousView.toString();
          paramAnonymousView = localSimpleDateFormat.parse(paramAnonymousView);
          paramAnonymousView.setTime(paramAnonymousView.getTime() + 3600000L);
          this.a.setText(localSimpleDateFormat.format(paramAnonymousView));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    ((ImageButton)localObject2).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (bool)
        {
          Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getResources().getString(2131624156), 0).show();
          return;
        }
        int i = MainActivity.f(MainActivity.this).d();
        if (i == 0)
        {
          MainActivity.f(MainActivity.this).a("lastAlarmType", 1);
          this.b.setImageResource(2131230810);
          Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getResources().getString(2131623977), 0).show();
          return;
        }
        if (i == 1)
        {
          MainActivity.f(MainActivity.this).a("lastAlarmType", 2);
          this.b.setImageResource(2131230834);
          Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getResources().getString(2131623975), 0).show();
          return;
        }
        MainActivity.f(MainActivity.this).a("lastAlarmType", 0);
        this.b.setImageResource(2131230893);
        Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getResources().getString(2131623976), 0).show();
      }
    });
    ((Button)localObject1).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          paramAnonymousView = localSimpleDateFormat.parse("00:00");
          this.b.setText(localSimpleDateFormat.format(paramAnonymousView));
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
      }
    });
    ((Button)localObject10).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (this.a.getText().equals("00:00"))
        {
          Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getResources().getString(2131624070), 0).show();
          return;
        }
        try
        {
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText().charAt(4));
          paramAnonymousView.append("");
          int i = Integer.parseInt(paramAnonymousView.toString());
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText().charAt(3));
          paramAnonymousView.append("");
          int j = Integer.parseInt(paramAnonymousView.toString());
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText().charAt(1));
          paramAnonymousView.append("");
          int k = Integer.parseInt(paramAnonymousView.toString());
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(this.a.getText().charAt(0));
          paramAnonymousView.append("");
          long l = i * 1000 * 60 + j * 1000 * 60 * 10 + k * 1000 * 60 * 60 + Integer.parseInt(paramAnonymousView.toString()) * 1000 * 60 * 60 * 10;
          i = MainActivity.f(MainActivity.this).d();
          if (bool) {
            i = 0;
          }
          if ((i == 2) && (!MainActivity.f(MainActivity.this).m()))
          {
            Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.this.getResources().getString(2131624257), 0).show();
            return;
          }
          if (l < ((b)MainActivity.a(MainActivity.this).get(MainActivity.o(MainActivity.this))).e())
          {
            MainActivity.d(MainActivity.this).a(((b)MainActivity.a(MainActivity.this).get(MainActivity.o(MainActivity.this))).a(), l, i, true);
            paramAnonymousView = "";
            if (i == 0) {
              paramAnonymousView = MainActivity.this.getResources().getString(2131624254);
            } else if (i == 1) {
              paramAnonymousView = MainActivity.this.getResources().getString(2131624255);
            } else if (i == 2) {
              paramAnonymousView = MainActivity.this.getResources().getString(2131624253);
            }
            Toast.makeText(MainActivity.this.getApplicationContext(), paramAnonymousView, 1).show();
          }
          else
          {
            MainActivity.d(MainActivity.this).a(((b)MainActivity.a(MainActivity.this).get(MainActivity.o(MainActivity.this))).a(), l, i, false);
            paramAnonymousView = "";
            if (i == 0) {
              paramAnonymousView = MainActivity.this.getString(2131624167);
            } else if (i == 1) {
              paramAnonymousView = MainActivity.this.getResources().getString(2131624187);
            } else if (i == 2) {
              paramAnonymousView = MainActivity.this.getResources().getString(2131624046);
            }
            Toast.makeText(MainActivity.this.getApplicationContext(), paramAnonymousView, 1).show();
          }
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
        }
        MainActivity.p(MainActivity.this);
        localDialog.dismiss();
      }
    });
    localObject9 = getWindowManager().getDefaultDisplay();
    localObject10 = new Point();
    ((Display)localObject9).getSize((Point)localObject10);
    int i1 = (int)(((Point)localObject10).x / 6.5D);
    ((Button)localObject8).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    ((Button)localObject7).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    ((Button)localObject6).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    ((Button)localObject5).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    ((Button)localObject4).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    ((Button)localObject3).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    ((ImageButton)localObject2).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    ((Button)localObject1).setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
    localObject8 = ((Button)localObject8).getLayoutParams();
    if ((localObject8 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject8).setMargins(10, 10, 10, 10);
    }
    localObject7 = ((Button)localObject7).getLayoutParams();
    if ((localObject7 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject7).setMargins(10, 10, 10, 10);
    }
    localObject6 = ((Button)localObject6).getLayoutParams();
    if ((localObject6 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject6).setMargins(10, 10, 10, 10);
    }
    localObject5 = ((Button)localObject5).getLayoutParams();
    if ((localObject5 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject5).setMargins(10, 10, 10, 10);
    }
    localObject4 = ((Button)localObject4).getLayoutParams();
    if ((localObject4 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject4).setMargins(10, 10, 10, 10);
    }
    localObject3 = ((Button)localObject3).getLayoutParams();
    if ((localObject3 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject3).setMargins(10, 10, 10, 10);
    }
    localObject2 = ((ImageButton)localObject2).getLayoutParams();
    if ((localObject2 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject2).setMargins(10, 10, 10, 10);
    }
    localObject1 = ((Button)localObject1).getLayoutParams();
    if ((localObject1 instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)localObject1).setMargins(10, 10, 10, 10);
    }
    localDialog.setContentView(localView);
    localDialog.show();
  }
  
  private void h()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427404, (ViewGroup)findViewById(2131296467));
    Object localObject1 = localDialog.getWindow();
    int i1 = 0;
    if (localObject1 != null) {
      localDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    localObject1 = this.b.c();
    int i3;
    for (int i2 = 0; i1 < this.j.size(); i2 = i3)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((b)this.j.get(i1)).a());
      ((StringBuilder)localObject2).append("---");
      i3 = i2;
      if (((String)localObject1).contains(((StringBuilder)localObject2).toString())) {
        i3 = i2 + 1;
      }
      i1 += 1;
    }
    Object localObject2 = (TextView)localView.findViewById(2131296683);
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(i2);
    ((StringBuilder)localObject1).append(" ");
    ((StringBuilder)localObject1).append(getResources().getString(2131624063));
    localObject1 = ((StringBuilder)localObject1).toString();
    if (i2 == 1)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(i2);
      ((StringBuilder)localObject1).append(" ");
      ((StringBuilder)localObject1).append(getResources().getString(2131624064));
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    ((TextView)localObject2).setText((CharSequence)localObject1);
    ((Button)localView.findViewById(2131296315)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    ((Button)localView.findViewById(2131296316)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.f(MainActivity.this).a("chosenApps", "");
        MainActivity.f(MainActivity.this).a("alarmApps", "");
        MainActivity.a(MainActivity.this).clear();
        long l = 0L;
        int i = 0;
        while (i < MainActivity.q(MainActivity.this).size())
        {
          l += ((b)MainActivity.q(MainActivity.this).get(i)).e();
          i += 1;
        }
        paramAnonymousView = new b("com.burockgames.to_tal", MainActivity.this.getResources().getString(2131624268), null, android.support.v4.a.a.a(MainActivity.this, 2131230889), l, true);
        MainActivity.a(MainActivity.this).add(paramAnonymousView);
        paramAnonymousView = new b("com.burockgames.in_vi_sib_le", MainActivity.this.getResources().getString(2131624166), null, android.support.v4.a.a.a(MainActivity.this, 2131230892), 0L, true);
        MainActivity.a(MainActivity.this).add(paramAnonymousView);
        MainActivity.h(MainActivity.this).addFooterView(new View(MainActivity.this.getApplicationContext()), null, false);
        paramAnonymousView = MainActivity.this;
        WindowManager localWindowManager = paramAnonymousView.getWindowManager();
        MainActivity localMainActivity = MainActivity.this;
        MainActivity.a(paramAnonymousView, new a(localWindowManager, localMainActivity, MainActivity.d(localMainActivity), MainActivity.a(MainActivity.this), MainActivity.e(MainActivity.this), MainActivity.f(MainActivity.this).c()));
        paramAnonymousView = MainActivity.this;
        MainActivity.a(paramAnonymousView, (ListView)paramAnonymousView.findViewById(2131296516));
        MainActivity.h(MainActivity.this).setAdapter(MainActivity.g(MainActivity.this));
        localDialog.dismiss();
      }
    });
    localDialog.setContentView(localView);
    localDialog.show();
  }
  
  private void i()
  {
    Object localObject1 = this.b.c();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((b)this.j.get(this.k)).a());
    ((StringBuilder)localObject2).append("---");
    if (!((String)localObject1).contains(((StringBuilder)localObject2).toString()))
    {
      localObject1 = this.b.a();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((b)this.j.get(this.k)).a());
      ((StringBuilder)localObject2).append("(&)");
      localObject1 = ((String)localObject1).replace(((StringBuilder)localObject2).toString(), "");
      this.b.a("chosenApps", (String)localObject1);
      localObject1 = this.j;
      localObject1 = (b)((List)localObject1).get(((List)localObject1).size() - 2);
      ((b)localObject1).a(((b)localObject1).e() + ((b)this.j.get(this.k)).e());
      this.j.remove(this.k);
      if (this.j.size() == 3) {
        this.j.remove(1);
      }
      this.h = new a(getWindowManager(), this, this.g, this.j, this.l, this.b.c());
      this.f = ((ListView)findViewById(2131296516));
      this.f.setAdapter(this.h);
      return;
    }
    localObject1 = new Dialog(this);
    ((Dialog)localObject1).requestWindowFeature(1);
    ((Dialog)localObject1).setCancelable(true);
    localObject2 = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427404, (ViewGroup)findViewById(2131296467));
    if (((Dialog)localObject1).getWindow() != null) {
      ((Dialog)localObject1).getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    ((TextView)((View)localObject2).findViewById(2131296683)).setText(getResources().getString(2131624064));
    ((Button)((View)localObject2).findViewById(2131296315)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.a.dismiss();
      }
    });
    ((Button)((View)localObject2).findViewById(2131296316)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = MainActivity.f(MainActivity.this).a();
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(((b)MainActivity.a(MainActivity.this).get(MainActivity.o(MainActivity.this))).a());
        ((StringBuilder)localObject).append("(&)");
        paramAnonymousView = paramAnonymousView.replace(((StringBuilder)localObject).toString(), "");
        MainActivity.f(MainActivity.this).a("chosenApps", paramAnonymousView);
        MainActivity.a(MainActivity.this, false);
        paramAnonymousView = (b)MainActivity.a(MainActivity.this).get(MainActivity.a(MainActivity.this).size() - 2);
        paramAnonymousView.a(paramAnonymousView.e() + ((b)MainActivity.a(MainActivity.this).get(MainActivity.o(MainActivity.this))).e());
        MainActivity.a(MainActivity.this).remove(MainActivity.o(MainActivity.this));
        if (MainActivity.a(MainActivity.this).size() == 3) {
          MainActivity.a(MainActivity.this).remove(1);
        }
        paramAnonymousView = MainActivity.this;
        localObject = paramAnonymousView.getWindowManager();
        MainActivity localMainActivity = MainActivity.this;
        MainActivity.a(paramAnonymousView, new a((WindowManager)localObject, localMainActivity, MainActivity.d(localMainActivity), MainActivity.a(MainActivity.this), MainActivity.e(MainActivity.this), MainActivity.f(MainActivity.this).c()));
        paramAnonymousView = MainActivity.this;
        MainActivity.a(paramAnonymousView, (ListView)paramAnonymousView.findViewById(2131296516));
        MainActivity.h(MainActivity.this).setAdapter(MainActivity.g(MainActivity.this));
        this.a.dismiss();
      }
    });
    ((Dialog)localObject1).setContentView((View)localObject2);
    ((Dialog)localObject1).show();
  }
  
  private void j()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427436, (ViewGroup)findViewById(2131296481));
    ((Button)localView.findViewById(2131296322)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    int i1 = android.support.v4.a.a.c(getApplicationContext(), 2131099675);
    int i2 = android.support.v4.a.a.c(getApplicationContext(), 2131099676);
    int i3 = android.support.v4.a.a.c(getApplicationContext(), 2131099674);
    int i4 = android.support.v4.a.a.c(getApplicationContext(), 2131099725);
    Object localObject1 = (ImageView)localView.findViewById(2131296431);
    Object localObject2 = (ImageView)localView.findViewById(2131296432);
    ImageView localImageView1 = (ImageView)localView.findViewById(2131296433);
    ((ImageView)localObject1).setColorFilter(i1);
    ((ImageView)localObject2).setColorFilter(i1);
    localImageView1.setColorFilter(i1);
    localObject1 = (ImageView)localView.findViewById(2131296434);
    localObject2 = (ImageView)localView.findViewById(2131296435);
    localImageView1 = (ImageView)localView.findViewById(2131296436);
    ImageView localImageView2 = (ImageView)localView.findViewById(2131296437);
    ImageView localImageView3 = (ImageView)localView.findViewById(2131296438);
    ImageView localImageView4 = (ImageView)localView.findViewById(2131296439);
    ImageView localImageView5 = (ImageView)localView.findViewById(2131296440);
    ImageView localImageView6 = (ImageView)localView.findViewById(2131296441);
    ImageView localImageView7 = (ImageView)localView.findViewById(2131296442);
    ImageView localImageView8 = (ImageView)localView.findViewById(2131296443);
    ImageView localImageView9 = (ImageView)localView.findViewById(2131296444);
    ImageView localImageView10 = (ImageView)localView.findViewById(2131296445);
    ((ImageView)localObject1).setColorFilter(i1);
    ((ImageView)localObject2).setColorFilter(i1);
    localImageView1.setColorFilter(i1);
    localImageView2.setColorFilter(i2);
    localImageView3.setColorFilter(i2);
    localImageView4.setColorFilter(i2);
    localImageView5.setColorFilter(i3);
    localImageView6.setColorFilter(i3);
    localImageView7.setColorFilter(i3);
    localImageView8.setColorFilter(i4);
    localImageView9.setColorFilter(i4);
    localImageView10.setColorFilter(i4);
    localObject1 = (TextView)localView.findViewById(2131296675);
    localObject2 = new SpannableString(getResources().getString(2131624114));
    ((Spannable)localObject2).setSpan(new ForegroundColorSpan(Color.parseColor("#CCF50057")), 0, 1, 33);
    ((TextView)localObject1).setText((CharSequence)localObject2, TextView.BufferType.SPANNABLE);
    localObject1 = (TextView)localView.findViewById(2131296676);
    localObject2 = new SpannableString(getResources().getString(2131624115));
    ((Spannable)localObject2).setSpan(new ForegroundColorSpan(Color.parseColor("#CCF50057")), 0, 1, 33);
    ((TextView)localObject1).setText((CharSequence)localObject2, TextView.BufferType.SPANNABLE);
    localObject1 = (TextView)localView.findViewById(2131296677);
    localObject2 = new SpannableString(getResources().getString(2131624119));
    ((Spannable)localObject2).setSpan(new ForegroundColorSpan(Color.parseColor("#CCF50057")), 0, 1, 33);
    ((TextView)localObject1).setText((CharSequence)localObject2, TextView.BufferType.SPANNABLE);
    localObject1 = (TextView)localView.findViewById(2131296678);
    localObject2 = new SpannableString(getResources().getString(2131624124));
    ((Spannable)localObject2).setSpan(new ForegroundColorSpan(Color.parseColor("#CCF50057")), 0, 1, 33);
    ((TextView)localObject1).setText((CharSequence)localObject2, TextView.BufferType.SPANNABLE);
    localObject1 = getWindowManager().getDefaultDisplay();
    localObject2 = new Point();
    ((Display)localObject1).getSize((Point)localObject2);
    ((ScrollView)localView.findViewById(2131296581)).setLayoutParams(new LinearLayout.LayoutParams(-2, (int)(((Point)localObject2).y / 1.75D)));
    localDialog.setContentView(localView);
    localDialog.show();
  }
  
  private void k()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427438, (ViewGroup)findViewById(2131296510));
    ((Button)localView.findViewById(2131296341)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    ((TextView)localView.findViewById(2131296691)).setMovementMethod(LinkMovementMethod.getInstance());
    Display localDisplay = getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    ((ScrollView)localView.findViewById(2131296582)).setLayoutParams(new LinearLayout.LayoutParams(-2, (int)(localPoint.y / 1.75D)));
    localDialog.setContentView(localView);
    localDialog.show();
  }
  
  private void l()
  {
    this.b.a("lastEvaluateShow", new SimpleDateFormat("dd", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis())));
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setCancelable(true);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427406, (ViewGroup)findViewById(2131296472));
    final CheckBox localCheckBox = (CheckBox)localView.findViewById(2131296347);
    ((Button)localView.findViewById(2131296318)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (localCheckBox.isChecked()) {
          MainActivity.f(MainActivity.this).a("lastEvaluateShow", "--");
        }
        localDialog.dismiss();
      }
    });
    ((Button)localView.findViewById(2131296319)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.f(MainActivity.this).a("lastEvaluateShow", "--");
        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.burockgames.timeclocker")));
        localDialog.dismiss();
      }
    });
    localDialog.setContentView(localView);
    localDialog.show();
  }
  
  private List<b> m()
  {
    int i1;
    if (Build.VERSION.SDK_INT >= 26) {
      i1 = 0;
    } else {
      i1 = 1;
    }
    ArrayList localArrayList1 = new ArrayList();
    Object localObject1 = this.c.getInstalledApplications(128);
    ArrayList localArrayList2 = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
      try
      {
        if (this.c.getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
          localArrayList2.add(localApplicationInfo);
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    this.j = new ArrayList();
    localObject1 = new b("com.burockgames.to_tal", getResources().getString(2131624268), null, android.support.v4.a.a.a(this, 2131230889), 0L, true);
    this.j.add(localObject1);
    String str1 = this.b.a();
    int i2 = 0;
    while (i2 < localArrayList2.size())
    {
      localObject1 = (ApplicationInfo)localArrayList2.get(i2);
      String str2 = ((ApplicationInfo)localObject1).packageName;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(str2);
      ((StringBuilder)localObject2).append("(&)");
      boolean bool = str1.contains(((StringBuilder)localObject2).toString());
      Object localObject3 = null;
      localObject2 = null;
      if (bool)
      {
        localObject3 = ((ApplicationInfo)localObject1).loadLabel(getPackageManager()).toString();
        if (i1 == 0) {}
      }
      try
      {
        localObject1 = ((ApplicationInfo)localObject1).loadIcon(this.c);
      }
      catch (Exception localException1)
      {
        Object localObject4;
        for (;;) {}
      }
      localObject4 = d.a(getPackageManager(), str2);
      localObject1 = android.support.v4.a.a.a(this, 2131230892);
      localObject2 = localObject4;
      break label322;
      localObject1 = android.support.v4.a.a.a(this, 2131230892);
      label322:
      bool = true;
      break label345;
      localObject4 = "";
      localObject1 = null;
      bool = false;
      localObject2 = localObject3;
      localObject3 = localObject4;
      label345:
      localObject1 = new b(str2, (String)localObject3, (Bitmap)localObject2, (Drawable)localObject1, 0L, bool);
      localArrayList1.add(localObject1);
      if (bool) {
        this.j.add(localObject1);
      }
      i2 += 1;
    }
    this.g.b(localArrayList1);
    this.g.a(localArrayList1);
    return localArrayList1;
  }
  
  public boolean a(MenuItem paramMenuItem)
  {
    int i1 = paramMenuItem.getItemId();
    if (i1 == 2131296283) {
      startActivity(new Intent(getApplicationContext(), Alarm.class));
    } else if (i1 == 2131296407) {
      startActivity(new Intent(getApplicationContext(), History.class));
    } else if (i1 == 2131296595) {
      startActivity(new Intent(getApplicationContext(), Settings.class));
    } else if (i1 == 2131296406) {
      startActivity(new Intent(getApplicationContext(), Help.class));
    } else if (i1 == 2131296619) {
      startActivity(new Intent(getApplicationContext(), Support.class));
    } else if (i1 == 2131296596) {
      startActivity(new Intent(getApplicationContext(), Shop.class));
    } else if (i1 == 2131296357) {
      startActivity(new Intent(getApplicationContext(), About.class));
    }
    ((DrawerLayout)findViewById(2131296373)).f(8388611);
    return true;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(f.a(paramContext, new g(paramContext).p()));
  }
  
  public void onBackPressed()
  {
    Object localObject = (DrawerLayout)findViewById(2131296373);
    if (((DrawerLayout)localObject).g(8388611))
    {
      ((DrawerLayout)localObject).f(8388611);
      return;
    }
    try
    {
      localObject = new Intent("android.intent.action.MAIN");
      ((Intent)localObject).addCategory("android.intent.category.HOME");
      ((Intent)localObject).setFlags(268435456);
      startActivity((Intent)localObject);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this.b = new g(getApplicationContext());
    this.g = new f(getApplicationContext());
    this.q = this.b.p();
    this.r = this.b.u();
    int i1 = this.r;
    if (i1 == 1) {
      setTheme(2131689764);
    } else if (i1 == 2) {
      setTheme(2131689765);
    } else if (i1 == 3) {
      setTheme(2131689766);
    } else if (i1 == 4) {
      setTheme(2131689767);
    }
    super.onCreate(paramBundle);
    setContentView(2131427401);
    a();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    Toolbar localToolbar = (Toolbar)findViewById(2131296714);
    localToolbar.setOverflowIcon(android.support.v4.a.a.a(getApplicationContext(), 2131230933));
    localToolbar.setNavigationIcon(android.support.v4.a.a.a(getApplicationContext(), 2131230930));
    getMenuInflater().inflate(2131492868, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (!this.p) {
      return true;
    }
    int i1 = paramMenuItem.getItemId();
    if (i1 == 2131296362)
    {
      if (this.b.c().equals(""))
      {
        this.b.a("chosenApps", "");
        this.b.a("alarmApps", "");
        this.j.clear();
        long l1 = 0L;
        i1 = 0;
        while (i1 < this.i.size())
        {
          l1 += ((b)this.i.get(i1)).e();
          i1 += 1;
        }
        paramMenuItem = new b("com.burockgames.to_tal", getResources().getString(2131624268), null, android.support.v4.a.a.a(this, 2131230889), l1, true);
        this.j.add(paramMenuItem);
        paramMenuItem = new b("com.burockgames.in_vi_sib_le", getResources().getString(2131624166), null, android.support.v4.a.a.a(this, 2131230892), 0L, true);
        this.j.add(paramMenuItem);
        this.f.addFooterView(new View(getApplicationContext()), null, false);
        this.f = ((ListView)findViewById(2131296516));
        this.h = new a(getWindowManager(), this, this.g, this.j, this.l, this.b.c());
        this.f.setAdapter(this.h);
        return true;
      }
      h();
      return true;
    }
    if (i1 == 2131296625) {
      c();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if ((paramInt != 0) || (paramArrayOfInt[0] == 0)) {}
    try
    {
      c();
      return;
    }
    catch (Exception paramArrayOfString)
    {
      for (;;) {}
    }
    Toast.makeText(getApplicationContext(), getResources().getString(2131624256), 0).show();
    return;
    Toast.makeText(getApplicationContext(), getResources().getString(2131624186), 0).show();
  }
  
  protected void onResume()
  {
    super.onResume();
    if ((this.r == this.b.u()) && (this.q == this.b.p()))
    {
      if (this.b.i())
      {
        this.b.a("isFirstTimeNotes", false);
        this.b.a("whatIsNewUpdate", 39);
        j();
      }
      else if (this.b.j() != 39)
      {
        this.b.a("whatIsNewUpdate", 39);
        k();
      }
      b();
      return;
    }
    recreate();
  }
  
  protected void onStop()
  {
    super.onStop();
    this.b.a("whichKind", this.m);
    this.b.a("nameDirection", this.n);
    this.b.a("timeDirection", this.o);
    try
    {
      startService(new Intent(this, RestartedService.class));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  @SuppressLint({"StaticFieldLeak"})
  private class a
    extends AsyncTask<String, Integer, Boolean>
  {
    private a() {}
    
    protected Boolean a(String... paramVarArgs)
    {
      paramVarArgs = MainActivity.this;
      MainActivity.a(paramVarArgs, paramVarArgs.getPackageManager());
      paramVarArgs = MainActivity.this;
      MainActivity.e(paramVarArgs, MainActivity.t(paramVarArgs));
      return null;
    }
    
    protected void a(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      long l1 = 0L;
      int i = 0;
      while (i < MainActivity.q(MainActivity.this).size())
      {
        l1 += ((b)MainActivity.q(MainActivity.this).get(i)).e();
        i += 1;
      }
      MainActivity.a(MainActivity.this, l1);
      long l2 = 0L;
      l1 = l2;
      i = 0;
      long l3 = l2;
      while (i < MainActivity.q(MainActivity.this).size())
      {
        l3 += ((b)MainActivity.q(MainActivity.this).get(i)).e();
        l2 = l1;
        if (!((b)MainActivity.q(MainActivity.this).get(i)).g()) {
          l2 = l1 + ((b)MainActivity.q(MainActivity.this).get(i)).e();
        }
        i += 1;
        l1 = l2;
      }
      ((b)MainActivity.a(MainActivity.this).get(0)).a(l3);
      if (MainActivity.a(MainActivity.this).size() != 1)
      {
        paramBoolean = new b("com.burockgames.ot_her", MainActivity.this.getResources().getString(2131624171), null, android.support.v4.a.a.a(MainActivity.this, 2131230887), l1, true);
        MainActivity.a(MainActivity.this).add(paramBoolean);
      }
      paramBoolean = new b("com.burockgames.in_vi_sib_le", MainActivity.this.getResources().getString(2131624171), null, android.support.v4.a.a.a(MainActivity.this, 2131230887), 0L, true);
      MainActivity.a(MainActivity.this).add(paramBoolean);
      MainActivity.h(MainActivity.this).addFooterView(new View(MainActivity.this.getApplicationContext()), null, false);
      paramBoolean = (ImageView)MainActivity.this.findViewById(2131296427);
      if (MainActivity.c(MainActivity.this) == 0) {
        paramBoolean.setImageResource(2131230927);
      } else {
        paramBoolean.setImageResource(2131230928);
      }
      paramBoolean = (ImageView)MainActivity.this.findViewById(2131296428);
      if (MainActivity.i(MainActivity.this) == 0) {
        paramBoolean.setImageResource(2131230927);
      } else {
        paramBoolean.setImageResource(2131230928);
      }
      if (MainActivity.s(MainActivity.this) == 0)
      {
        if (MainActivity.c(MainActivity.this) == 0)
        {
          paramBoolean = MainActivity.this;
          MainActivity.a(paramBoolean, MainActivity.a(paramBoolean));
        }
        else
        {
          paramBoolean = MainActivity.this;
          MainActivity.b(paramBoolean, MainActivity.a(paramBoolean));
        }
      }
      else if (MainActivity.i(MainActivity.this) == 0)
      {
        paramBoolean = MainActivity.this;
        MainActivity.a(paramBoolean, MainActivity.a(paramBoolean));
        paramBoolean = MainActivity.this;
        MainActivity.c(paramBoolean, MainActivity.a(paramBoolean));
      }
      else
      {
        paramBoolean = MainActivity.this;
        MainActivity.b(paramBoolean, MainActivity.a(paramBoolean));
        paramBoolean = MainActivity.this;
        MainActivity.d(paramBoolean, MainActivity.a(paramBoolean));
      }
      paramBoolean = MainActivity.this;
      WindowManager localWindowManager = paramBoolean.getWindowManager();
      MainActivity localMainActivity = MainActivity.this;
      MainActivity.a(paramBoolean, new a(localWindowManager, localMainActivity, MainActivity.d(localMainActivity), MainActivity.a(MainActivity.this), MainActivity.e(MainActivity.this), MainActivity.f(MainActivity.this).c()));
      MainActivity.h(MainActivity.this).setAdapter(MainActivity.g(MainActivity.this));
      MainActivity.r(MainActivity.this).setVisibility(8);
      MainActivity.h(MainActivity.this).setVisibility(0);
      MainActivity.b(MainActivity.this, true);
    }
    
    protected void onPreExecute()
    {
      MainActivity.r(MainActivity.this).setVisibility(0);
      MainActivity.h(MainActivity.this).setVisibility(8);
      MainActivity.b(MainActivity.this, false);
      super.onPreExecute();
    }
  }
}
