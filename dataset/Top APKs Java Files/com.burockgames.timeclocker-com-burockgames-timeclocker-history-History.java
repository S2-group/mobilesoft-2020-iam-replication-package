package com.burockgames.timeclocker.history;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;
import android.provider.MediaStore.Images.Media;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.b;
import android.support.design.widget.TabLayout.e;
import android.support.design.widget.TabLayout.f;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import c.b.n;
import c.d.i;
import c.d.k;
import c.d.l;
import c.d.o;
import c.d.p;
import c.u;
import c.w;
import c.x;
import com.burockgames.timeclocker.a.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class History
  extends android.support.v7.app.e
{
  public static List<a> a;
  public static boolean b;
  private final int c = 0;
  private final int d = 1;
  private g e;
  private com.burockgames.timeclocker.a.f f;
  private Dialog g;
  private LinearLayout h;
  private LinearLayout i;
  private LinearLayout j;
  private LinearLayout k;
  private ProgressBar l;
  private TextView m;
  
  public History() {}
  
  private void a()
  {
    int n = this.e.u();
    if (n == 0) {
      findViewById(2131296562).setBackgroundResource(2131230832);
    } else if (n == 1) {
      findViewById(2131296562).setBackgroundResource(2131230833);
    }
    setSupportActionBar((Toolbar)findViewById(2131296713));
    if (getSupportActionBar() != null)
    {
      getSupportActionBar().b(true);
      getSupportActionBar().a(true);
      getSupportActionBar().a(2131230926);
    }
    Object localObject1 = (TextView)findViewById(2131296689);
    Object localObject2 = new FrameLayout.LayoutParams(-1, -2);
    ((FrameLayout.LayoutParams)localObject2).setMargins(0, 0, 0, 0);
    ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((TextView)localObject1).setGravity(17);
    ((TextView)localObject1).setText(getResources().getString(2131623969));
    localObject1 = (TabLayout)findViewById(2131296622);
    ((TabLayout)localObject1).a(((TabLayout)localObject1).a().a(getResources().getString(2131624044)));
    ((TabLayout)localObject1).a(((TabLayout)localObject1).a().a(getResources().getString(2131624181)));
    ((TabLayout)localObject1).a(((TabLayout)localObject1).a().a(getResources().getString(2131624182)));
    ((TabLayout)localObject1).setTabGravity(0);
    localObject2 = (ViewPager)findViewById(2131296730);
    ((ViewPager)localObject2).setAdapter(new b(getSupportFragmentManager(), ((TabLayout)localObject1).getTabCount()));
    ((ViewPager)localObject2).addOnPageChangeListener(new TabLayout.f((TabLayout)localObject1));
    ((TabLayout)localObject1).a(new TabLayout.b()
    {
      public void a(TabLayout.e paramAnonymousE)
      {
        this.a.setCurrentItem(paramAnonymousE.c());
      }
      
      public void b(TabLayout.e paramAnonymousE) {}
      
      public void c(TabLayout.e paramAnonymousE) {}
    });
    this.h = ((LinearLayout)findViewById(2131296491));
    this.i = ((LinearLayout)findViewById(2131296496));
    new a(null).execute(new String[0]);
  }
  
  private void a(List<a> paramList)
  {
    int n = 0;
    while (n < paramList.size())
    {
      int i2;
      for (int i1 = 1; i1 < paramList.size() - 1; i1 = i2)
      {
        Object localObject = ((a)paramList.get(i1)).b();
        i2 = i1 + 1;
        if (((String)localObject).compareTo(((a)paramList.get(i2)).b()) > 0)
        {
          localObject = (a)paramList.get(i2);
          paramList.set(i2, paramList.get(i1));
          paramList.set(i1, localObject);
        }
      }
      n += 1;
    }
  }
  
  private ArrayList<a> b()
  {
    int n;
    if (Build.VERSION.SDK_INT >= 26) {
      n = 0;
    } else {
      n = 1;
    }
    ArrayList localArrayList1 = new ArrayList();
    Object localObject1 = getPackageManager().getInstalledApplications(128);
    ArrayList localArrayList2 = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
      try
      {
        if (getPackageManager().getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
          localArrayList2.add(localApplicationInfo);
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    int i1 = 0;
    for (;;)
    {
      int i2 = localArrayList2.size();
      Object localObject2 = null;
      if (i1 >= i2) {
        break;
      }
      localObject1 = (ApplicationInfo)localArrayList2.get(i1);
      String str1 = ((ApplicationInfo)localObject1).packageName;
      String str2 = ((ApplicationInfo)localObject1).loadLabel(getPackageManager()).toString();
      if (n != 0) {}
      try
      {
        localObject1 = ((ApplicationInfo)localObject1).loadIcon(getPackageManager());
      }
      catch (Exception localException1)
      {
        Bitmap localBitmap;
        label216:
        for (;;) {}
      }
      localBitmap = com.burockgames.timeclocker.a.d.a(getPackageManager(), str1);
      localObject1 = android.support.v4.a.a.a(this, 2131230892);
      localObject2 = localBitmap;
      break label216;
      localObject1 = android.support.v4.a.a.a(this, 2131230892);
      localArrayList1.add(new a(str1, str2, localObject2, (Drawable)localObject1));
      i1 += 1;
    }
    localArrayList1.add(0, new a("com.burockgames.to_tal", getResources().getString(2131624267), null, android.support.v4.a.a.a(this, 2131230889)));
    return localArrayList1;
  }
  
  private void c()
  {
    if ((Build.VERSION.SDK_INT >= 23) && (android.support.v4.a.a.a(this, "android.permission.READ_EXTERNAL_STORAGE") != 0))
    {
      android.support.v4.app.a.a(this, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" }, 0);
      return;
    }
    this.g = new Dialog(this);
    this.g.requestWindowFeature(1);
    this.g.setCancelable(false);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427391, (ViewGroup)findViewById(2131296566));
    if (this.g.getWindow() != null) {
      this.g.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
    this.j = ((LinearLayout)localView.findViewById(2131296474));
    this.k = ((LinearLayout)localView.findViewById(2131296473));
    this.l = ((ProgressBar)localView.findViewById(2131296549));
    this.m = ((TextView)localView.findViewById(2131296646));
    this.j.setVisibility(0);
    this.k.setVisibility(8);
    ((Button)localView.findViewById(2131296320)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        History.a(History.this).dismiss();
      }
    });
    ((Button)localView.findViewById(2131296321)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new History.b(History.this, null).execute(new String[0]);
      }
    });
    this.g.setContentView(localView);
    this.g.show();
  }
  
  private void d()
  {
    if ((Build.VERSION.SDK_INT >= 23) && (android.support.v4.a.a.a(this, "android.permission.READ_EXTERNAL_STORAGE") != 0))
    {
      android.support.v4.app.a.a(this, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
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
        History.b(History.this);
      }
    });
    localDialog.setContentView((View)localObject);
    localDialog.show();
  }
  
  private void e()
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
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(com.burockgames.timeclocker.a.f.a(paramContext, new g(paramContext).p()));
  }
  
  public void onBackPressed()
  {
    super.onStop();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this.e = new g(getApplicationContext());
    this.f = new com.burockgames.timeclocker.a.f(getApplicationContext());
    b = false;
    int n = this.e.u();
    if (n == 1) {
      setTheme(2131689764);
    } else if (n == 2) {
      setTheme(2131689765);
    } else if (n == 3) {
      setTheme(2131689766);
    } else if (n == 4) {
      setTheme(2131689767);
    }
    super.onCreate(paramBundle);
    setContentView(2131427389);
    a();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    ((Toolbar)findViewById(2131296713)).setOverflowIcon(android.support.v4.a.a.a(getApplicationContext(), 2131230933));
    getMenuInflater().inflate(2131492866, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (!b) {
      return true;
    }
    int n = paramMenuItem.getItemId();
    if (n == 16908332) {
      finish();
    } else if (n == 2131296390) {
      c();
    } else if (n == 2131296625) {
      d();
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
    Toast.makeText(getApplicationContext(), getResources().getString(2131624075), 0).show();
    return;
    if (paramInt == 1)
    {
      if (paramArrayOfInt[0] == 0)
      {
        d();
        return;
      }
      Toast.makeText(getApplicationContext(), getResources().getString(2131624256), 0).show();
      return;
      Toast.makeText(getApplicationContext(), getResources().getString(2131624186), 0).show();
    }
  }
  
  @SuppressLint({"StaticFieldLeak"})
  private class a
    extends AsyncTask<String, Integer, Boolean>
  {
    private a() {}
    
    protected Boolean a(String... paramVarArgs)
    {
      History.a = History.e(History.this);
      History.a(History.this, History.a);
      return null;
    }
    
    protected void a(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      History.c(History.this).setVisibility(8);
      History.d(History.this).setVisibility(0);
    }
    
    protected void onPreExecute()
    {
      History.c(History.this).setVisibility(0);
      History.d(History.this).setVisibility(8);
      super.onPreExecute();
    }
  }
  
  @SuppressLint({"StaticFieldLeak"})
  private class b
    extends AsyncTask<String, Integer, Boolean>
  {
    private b() {}
    
    protected Boolean a(String... paramVarArgs)
    {
      final ArrayList localArrayList = History.e(History.this);
      History.a(History.this, localArrayList);
      Object localObject2 = History.h(History.this).d(localArrayList);
      final int i;
      int j;
      try
      {
        paramVarArgs = new StringBuilder();
        paramVarArgs.append(Environment.getExternalStorageDirectory());
        paramVarArgs.append("/StayFree");
        paramVarArgs = new File(paramVarArgs.toString());
        if ((!paramVarArgs.isDirectory()) && (!paramVarArgs.mkdirs()))
        {
          History.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              Toast.makeText(History.this.getApplicationContext(), History.this.getResources().getString(2131624073), 0).show();
            }
          });
          return Boolean.valueOf(true);
        }
        localObject1 = Calendar.getInstance();
        localFile = new File(paramVarArgs, "HistoryReport.xls");
        paramVarArgs = new x();
        paramVarArgs.a(Locale.getDefault());
        if (localFile.exists()) {
          paramVarArgs = w.a(localFile, w.a(localFile));
        } else {
          paramVarArgs = w.b(localFile, paramVarArgs);
        }
        localObject3 = ((Calendar)localObject1).getTime();
        localO = paramVarArgs.a(new SimpleDateFormat("dd-MM-yyyy_kk-mm-ss", Locale.getDefault()).format((Date)localObject3), 0);
        localO.b(0, 30);
        localO.b(1, 15);
        localO.b(2, 15);
        localO.b(3, 15);
        localO.b(4, 15);
        localO.b(5, 15);
        localO.b(6, 15);
        localO.b(7, 15);
        localO.b(8, 15);
        localObject4 = localO.e();
        ((u)localObject4).k(1);
        ((u)localObject4).l(1);
        localObject4 = History.this.getResources().getString(2131624241);
        localObject5 = History.this.getResources().getString(2131624159);
        localObject6 = History.this.getResources().getString(2131624278);
        String str1 = History.this.getResources().getString(2131624289);
        String str2 = History.this.getResources().getString(2131624249);
        String str3 = History.this.getResources().getString(2131624083);
        String str4 = History.this.getResources().getString(2131624208);
        i = 1;
        while (i < 8)
        {
          ((Calendar)localObject1).add(5, 1);
          j = ((Calendar)localObject1).get(7);
          localO.a(new c.d.f(i, 0, new String[] { "", localObject4, localObject5, localObject6, str1, str2, str3, str4 }[j]));
          i += 1;
        }
        while (i <= localArrayList.size())
        {
          if (i == localArrayList.size())
          {
            localObject1 = History.h(History.this).c(localArrayList);
            localO.a(new c.d.f(0, i, History.this.getResources().getString(2131624267)));
          }
          else
          {
            localObject1 = (long[])((HashMap)localObject2).get(((a)localArrayList.get(i)).a());
            History.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                History.i(History.this).setProgress(i * 100 / localArrayList.size());
                TextView localTextView = History.j(History.this);
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(((a)localArrayList.get(i)).b());
                localStringBuilder.append("...");
                localTextView.setText(String.valueOf(localStringBuilder.toString()));
              }
            });
            localO.a(new c.d.f(0, i, ((a)localArrayList.get(i)).b()));
          }
          localO.a(new c.d.f(1, i, History.h(History.this).b(localObject1[0])));
          localO.a(new c.d.f(2, i, History.h(History.this).b(localObject1[1])));
          localO.a(new c.d.f(3, i, History.h(History.this).b(localObject1[2])));
          localO.a(new c.d.f(4, i, History.h(History.this).b(localObject1[3])));
          localO.a(new c.d.f(5, i, History.h(History.this).b(localObject1[4])));
          localO.a(new c.d.f(6, i, History.h(History.this).b(localObject1[5])));
          localO.a(new c.d.f(7, i, History.h(History.this).b(localObject1[6])));
          localO.a(new c.d.f(8, i, History.h(History.this).b(localObject1[0] + localObject1[1] + localObject1[2] + localObject1[3] + localObject1[4] + localObject1[5] + localObject1[6])));
          i += 1;
        }
        localO.a(new c.d.f(8, 0, History.this.getResources().getString(2131624267)));
        j = i + 2;
        localO.a(new c.d.f(0, j, History.this.getResources().getString(2131624077)));
        localObject1 = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss", Locale.getDefault());
        int k = i + 3;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(History.this.getResources().getString(2131624078));
        ((StringBuilder)localObject2).append(" ");
        ((StringBuilder)localObject2).append(((SimpleDateFormat)localObject1).format((Date)localObject3));
        localO.a(new c.d.f(0, k, ((StringBuilder)localObject2).toString()));
        localObject4 = new l(l.b("Arial"), 12, l.i, false, n.a, c.b.e.aa);
        localObject1 = new k();
        localObject2 = new k((l)localObject4);
        localObject3 = new k();
        localObject4 = new k((l)localObject4);
        ((k)localObject3).a(c.b.e.al);
        ((k)localObject4).a(c.b.e.al);
        localO.c(0, j).a((c.b.d)localObject4);
        localO.c(0, k).a((c.b.d)localObject4);
        localObject6 = new l(l.b("Arial"), 12, l.i, false, n.a, c.b.e.r);
        localObject5 = new k((l)localObject6);
        ((k)localObject5).a(c.b.e.s);
        localObject6 = new k((l)localObject6);
        j = 0;
        label1236:
        if (j >= 8) {
          break label1666;
        }
        k = i - 1;
        if (k % 2 == 0) {
          localO.c(j, k).a((c.b.d)localObject6);
        } else {
          localO.c(j, k).a((c.b.d)localObject5);
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject1;
        File localFile;
        Object localObject3;
        o localO;
        Object localObject4;
        Object localObject5;
        Object localObject6;
        label1294:
        label1349:
        paramVarArgs = null;
        History.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            Context localContext = History.this.getApplicationContext();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(History.this.getResources().getString(2131624073));
            localStringBuilder.append(": ");
            localStringBuilder.append(localThrowable.getMessage());
            Toast.makeText(localContext, localStringBuilder.toString(), 1).show();
          }
        });
      }
      if (i <= localArrayList.size()) {
        if (i % 2 == 0) {
          localO.c(8, i).a((c.b.d)localObject6);
        } else {
          localO.c(8, i).a((c.b.d)localObject5);
        }
      }
      for (;;)
      {
        if (i < localArrayList.size()) {
          j = 0;
        }
        for (;;)
        {
          if (j >= 8) {
            break label1690;
          }
          if (i == 0)
          {
            localO.c(j, i).a((c.b.d)localObject2);
          }
          else if ((i % 2 == 0) && (j == 0))
          {
            localO.c(j, i).a((c.b.d)localObject2);
          }
          else if ((i % 2 == 0) && (j != 0))
          {
            localO.c(j, i).a((c.b.d)localObject1);
          }
          else if ((i % 2 != 0) && (j == 0))
          {
            localO.c(j, i).a((c.b.d)localObject4);
          }
          else if ((i % 2 != 0) && (j != 0))
          {
            localO.c(j, i).a((c.b.d)localObject3);
            break label1683;
            paramVarArgs.c();
            paramVarArgs.b();
            History.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                Toast.makeText(History.this.getApplicationContext(), History.this.getResources().getString(2131624076), 1).show();
              }
            });
            paramVarArgs = localFile;
            if (paramVarArgs == null) {}
            try
            {
              return Boolean.valueOf(true);
            }
            catch (Throwable paramVarArgs)
            {
              Intent localIntent;
              for (;;) {}
            }
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
            localIntent = new Intent("android.intent.action.VIEW");
            localIntent.setDataAndType(Uri.fromFile(paramVarArgs), "application/vnd.ms-excel");
            localIntent.setFlags(67108864);
            localIntent.addFlags(1);
            History.this.startActivity(localIntent);
            break label1645;
            History.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                Toast.makeText(History.this.getApplicationContext(), History.this.getResources().getString(2131624074), 1).show();
              }
            });
            label1645:
            return Boolean.valueOf(true);
            i = 1;
            break;
            j += 1;
            break label1236;
            label1666:
            i = 0;
            break label1294;
            i += 1;
            break label1294;
            i = 0;
            break label1349;
          }
          label1683:
          j += 1;
        }
        label1690:
        i += 1;
      }
    }
    
    protected void a(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      History.a(History.this).dismiss();
    }
    
    protected void onPreExecute()
    {
      History.f(History.this).setVisibility(0);
      History.g(History.this).setVisibility(8);
      super.onPreExecute();
    }
  }
}
