package com.ss.iconpack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ss.f.r;
import com.ss.view.a;
import com.ss.view.a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class PickIconActivity
  extends Activity
  implements c.a
{
  private String a;
  private ImageView b;
  private CheckBox c;
  private a d;
  private ListView e;
  private EditText f;
  private GridView g;
  private ArrayAdapter<PackageInfo> h;
  private ArrayList<String> i = new ArrayList();
  private ArrayList<String> j = new ArrayList();
  private Resources k;
  private Thread l;
  private Thread m;
  private Toast n;
  private ArrayList<PackageInfo> o = new ArrayList();
  private LinkedList<Runnable> p = new LinkedList();
  private Thread q;
  
  public PickIconActivity() {}
  
  private void a(final CharSequence paramCharSequence)
  {
    this.m = new Thread()
    {
      public void run()
      {
        boolean bool;
        PackageManager localPackageManager;
        int i;
        if (TextUtils.isEmpty(paramCharSequence))
        {
          PickIconActivity.f(PickIconActivity.this).addAll(PickIconActivity.i(PickIconActivity.this));
        }
        else
        {
          bool = "com.ss.iconpack.APPLICATION".equals(PickIconActivity.d(PickIconActivity.this));
          localPackageManager = PickIconActivity.this.getPackageManager();
          i = 0;
        }
        for (;;)
        {
          String str3;
          String str1;
          if (i < PickIconActivity.i(PickIconActivity.this).size())
          {
            if (this != PickIconActivity.l(PickIconActivity.this)) {
              return;
            }
            str3 = (String)PickIconActivity.i(PickIconActivity.this).get(i);
            str1 = str3;
            if (!bool) {}
          }
          try
          {
            str1 = localPackageManager.getActivityInfo(ComponentName.unflattenFromString((String)PickIconActivity.i(PickIconActivity.this).get(i)), 0).loadLabel(localPackageManager).toString().toLowerCase(Locale.getDefault());
            if ((!str1.startsWith("c:")) && ((paramCharSequence.length() <= 0) || (str1.contains(paramCharSequence.toString().toLowerCase(Locale.getDefault()))))) {
              PickIconActivity.f(PickIconActivity.this).add(PickIconActivity.i(PickIconActivity.this).get(i));
            }
            i += 1;
            continue;
            if ((this == PickIconActivity.l(PickIconActivity.this)) && (PickIconActivity.j(PickIconActivity.this) != null)) {
              PickIconActivity.j(PickIconActivity.this).post(new Runnable()
              {
                public void run()
                {
                  ((ArrayAdapter)PickIconActivity.j(PickIconActivity.this).getAdapter()).notifyDataSetChanged();
                }
              });
            }
            return;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;)
            {
              String str2 = str3;
            }
          }
        }
      }
    };
    this.j.clear();
    this.m.start();
  }
  
  private void a(Runnable paramRunnable)
  {
    try
    {
      this.p.add(paramRunnable);
      return;
    }
    finally
    {
      paramRunnable = finally;
      throw paramRunnable;
    }
  }
  
  private void a(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      if (this.d.getStatus() == 0)
      {
        if (this.b.getRotation() == 0.0F)
        {
          this.b.setRotation(90.0F);
          if (!paramBoolean) {}
        }
      }
      else {
        for (RotateAnimation localRotateAnimation = new RotateAnimation(-90.0F, 0.0F, this.b.getWidth() / 2, this.b.getHeight() / 2);; localRotateAnimation = new RotateAnimation(90.0F, 0.0F, this.b.getWidth() / 2, this.b.getHeight() / 2))
        {
          localRotateAnimation.setDuration(r.a(this, 250L));
          this.b.startAnimation(localRotateAnimation);
          return;
          if (this.b.getRotation() != 90.0F) {
            break;
          }
          this.b.setRotation(0.0F);
          if (!paramBoolean) {
            break;
          }
        }
      }
    }
  }
  
  private void b()
  {
    int i1 = getResources().getDimensionPixelSize(e.a.l_ip_icon_size);
    int i2 = getResources().getDimensionPixelSize(e.a.l_ip_icon_padding);
    i1 = (getResources().getDisplayMetrics().widthPixels - getResources().getDimensionPixelSize(e.a.l_ip_grid_side_margin) * 2) / (i1 + 2 * i2);
    this.g.setNumColumns(i1);
  }
  
  private void c()
  {
    this.i.clear();
    this.j.clear();
    ((ArrayAdapter)this.g.getAdapter()).notifyDataSetChanged();
    if ("com.ss.iconpack.APPLICATION".equals(this.a)) {
      this.k = null;
    }
    Thread local15;
    for (Thread local14 = new Thread()
        {
          public void run()
          {
            Intent localIntent = new Intent("android.intent.action.MAIN", null);
            localIntent.addCategory("android.intent.category.LAUNCHER");
            PackageManager localPackageManager = PickIconActivity.this.getPackageManager();
            Iterator localIterator = localPackageManager.getInstalledPackages(128).iterator();
            while (localIterator.hasNext())
            {
              Object localObject1 = (PackageInfo)localIterator.next();
              if (this != PickIconActivity.h(PickIconActivity.this)) {
                return;
              }
              localIntent.setPackage(((PackageInfo)localObject1).packageName);
              localObject1 = localPackageManager.queryIntentActivities(localIntent, 0).iterator();
              while (((Iterator)localObject1).hasNext())
              {
                Object localObject2 = (ResolveInfo)((Iterator)localObject1).next();
                String str = ((ResolveInfo)localObject2).activityInfo.applicationInfo.packageName;
                localObject2 = ((ResolveInfo)localObject2).activityInfo.name;
                PickIconActivity.i(PickIconActivity.this).add(new ComponentName(str, (String)localObject2).flattenToShortString());
              }
            }
            if ((this == PickIconActivity.h(PickIconActivity.this)) && (PickIconActivity.j(PickIconActivity.this) != null)) {
              PickIconActivity.j(PickIconActivity.this).post(new Runnable()
              {
                public void run()
                {
                  PickIconActivity.a(PickIconActivity.this, PickIconActivity.k(PickIconActivity.this).getText().toString());
                  PickIconActivity.j(PickIconActivity.this).setSelection(0);
                  try
                  {
                    PickIconActivity.14.this.a.dismiss();
                    return;
                  }
                  catch (Exception localException)
                  {
                    localException.printStackTrace();
                  }
                }
              });
            }
          }
        };; local15 = new Thread()
        {
          public void run()
          {
            b.a(PickIconActivity.this.getApplicationContext(), PickIconActivity.d(PickIconActivity.this), PickIconActivity.i(PickIconActivity.this));
            if ((PickIconActivity.h(PickIconActivity.this) == this) && (PickIconActivity.j(PickIconActivity.this) != null)) {
              PickIconActivity.j(PickIconActivity.this).post(new Runnable()
              {
                public void run()
                {
                  PickIconActivity.a(PickIconActivity.this, PickIconActivity.k(PickIconActivity.this).getText().toString());
                  PickIconActivity.j(PickIconActivity.this).setSelection(0);
                  try
                  {
                    PickIconActivity.15.this.a.dismiss();
                    return;
                  }
                  catch (Exception localException)
                  {
                    localException.printStackTrace();
                  }
                }
              });
            }
          }
        })
    {
      this.l = local14;
      this.l.start();
      return;
      try
      {
        this.k = getPackageManager().getResourcesForApplication(this.a);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        this.k = null;
      }
      if (this.k == null) {
        break;
      }
    }
  }
  
  private void d()
  {
    this.o.clear();
    this.o.add(null);
    PackageManager localPackageManager = getPackageManager();
    String[] arrayOfString = getIntent().getStringArrayExtra("com.ss.iconpack.PickIconActivity.extra.ADDITIONS");
    if (arrayOfString != null)
    {
      int i1 = 0;
      while (i1 < arrayOfString.length)
      {
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(arrayOfString[i1], 0);
          if (localPackageInfo != null) {
            this.o.add(localPackageInfo);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;) {}
        }
        i1 += 1;
      }
    }
    c.a(this.o);
  }
  
  private ArrayAdapter<PackageInfo> e()
  {
    new ArrayAdapter(this, 0, this.o)
    {
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        paramAnonymousViewGroup = paramAnonymousView;
        if (paramAnonymousView == null) {
          paramAnonymousViewGroup = View.inflate(PickIconActivity.this.getApplicationContext(), e.d.l_ip_item_icon_pack, null);
        }
        paramAnonymousView = (ImageView)paramAnonymousViewGroup.findViewById(e.c.image1);
        TextView localTextView = (TextView)paramAnonymousViewGroup.findViewById(e.c.text1);
        if (paramAnonymousInt == 0)
        {
          paramAnonymousView.setImageResource(e.e.l_ip_ic_apps);
          localTextView.setText(e.f.l_ip_app_icons);
          if (!"com.ss.iconpack.APPLICATION".equals(PickIconActivity.d(PickIconActivity.this))) {}
        }
        for (;;)
        {
          paramAnonymousViewGroup.findViewById(e.c.check).setBackgroundColor(838860800);
          return paramAnonymousViewGroup;
          PackageInfo localPackageInfo;
          do
          {
            paramAnonymousViewGroup.findViewById(e.c.check).setBackgroundColor(0);
            return paramAnonymousViewGroup;
            localPackageInfo = (PackageInfo)getItem(paramAnonymousInt);
            try
            {
              if ((!a) && (localPackageInfo == null)) {
                throw new AssertionError();
              }
              paramAnonymousView.setImageDrawable(PickIconActivity.this.getPackageManager().getApplicationIcon(localPackageInfo.packageName));
            }
            catch (Exception localException)
            {
              for (;;) {}
            }
            paramAnonymousView.setImageDrawable(null);
            localTextView.setText(PickIconActivity.this.getPackageManager().getApplicationLabel(localPackageInfo.applicationInfo));
          } while (!localPackageInfo.packageName.equals(PickIconActivity.d(PickIconActivity.this)));
        }
      }
    };
  }
  
  private Runnable f()
  {
    try
    {
      Runnable localRunnable = (Runnable)this.p.removeFirst();
      return localRunnable;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void g()
  {
    if ((this.q != null) && (this.q.isAlive())) {
      return;
    }
    this.q = new Thread()
    {
      public void run()
      {
        while ((PickIconActivity.o(PickIconActivity.this).size() > 0) && (PickIconActivity.p(PickIconActivity.this) == this)) {
          PickIconActivity.q(PickIconActivity.this).run();
        }
      }
    };
    this.q.start();
  }
  
  private ArrayAdapter<String> h()
  {
    new ArrayAdapter(this, 0, this.j)
    {
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        paramAnonymousViewGroup = paramAnonymousView;
        if (paramAnonymousView == null)
        {
          paramAnonymousViewGroup = View.inflate(getContext(), e.d.l_ip_item_pick_icon, null);
          paramAnonymousView = new a();
          paramAnonymousView.a = ((ImageView)paramAnonymousViewGroup.findViewById(e.c.icon));
          paramAnonymousView.b = ((TextView)paramAnonymousViewGroup.findViewById(e.c.text));
          paramAnonymousView.e = AnimationUtils.loadAnimation(PickIconActivity.this.getApplicationContext(), 17432576);
          paramAnonymousView.e.setDuration(200L);
          paramAnonymousViewGroup.setTag(paramAnonymousView);
        }
        a localA = (a)paramAnonymousViewGroup.getTag();
        localA.c = ((String)getItem(paramAnonymousInt));
        localA.d = null;
        localA.a.clearAnimation();
        if (localA.c.startsWith("c:"))
        {
          localA.a.setImageDrawable(null);
          paramAnonymousView = localA.c.substring(2);
          localA.b.setText(paramAnonymousView);
          if (paramAnonymousView.length() == 1) {
            paramAnonymousView = localA.b;
          }
          for (paramAnonymousInt = PickIconActivity.this.getResources().getDimensionPixelSize(e.a.l_ip_icon_size);; paramAnonymousInt = PickIconActivity.this.getResources().getDimensionPixelSize(e.a.l_ip_icon_size) / 3)
          {
            paramAnonymousView.setTextSize(0, paramAnonymousInt * 8 / 10);
            break;
            paramAnonymousView = localA.b;
          }
          localA.b.setVisibility(0);
          if (PickIconActivity.s(PickIconActivity.this)) {
            paramAnonymousView = localA.b;
          }
          for (paramAnonymousInt = -16777216;; paramAnonymousInt = -1)
          {
            paramAnonymousView.setTextColor(paramAnonymousInt);
            return paramAnonymousViewGroup;
            paramAnonymousView = localA.b;
          }
        }
        localA.a.setImageDrawable(null);
        localA.b.setVisibility(4);
        if (!localA.f)
        {
          PickIconActivity.a(PickIconActivity.this, localA.g);
          localA.f = true;
        }
        PickIconActivity.t(PickIconActivity.this);
        return paramAnonymousViewGroup;
      }
      
      class a
      {
        ImageView a;
        TextView b;
        String c;
        Drawable d;
        Animation e;
        boolean f = false;
        Runnable g = new Runnable()
        {
          public void run()
          {
            PickIconActivity.7.a.this.f = false;
            Object localObject;
            if ("com.ss.iconpack.APPLICATION".equals(PickIconActivity.d(PickIconActivity.this))) {
              localObject = ComponentName.unflattenFromString(PickIconActivity.7.a.this.c);
            }
            try
            {
              int i = PickIconActivity.this.getPackageManager().getActivityInfo((ComponentName)localObject, 0).getIconResource();
              if (i != 0) {
                PickIconActivity.7.a.this.d = b.a(PickIconActivity.this, PickIconActivity.this.getPackageManager().getResourcesForApplication(((ComponentName)localObject).getPackageName()), i);
              }
              if (PickIconActivity.7.a.this.d != null) {
                break label266;
              }
              PickIconActivity.7.a.this.d = PickIconActivity.this.getPackageManager().getActivityIcon((ComponentName)localObject);
            }
            catch (Resources.NotFoundException|PackageManager.NameNotFoundException|OutOfMemoryError localNotFoundException)
            {
              Drawable localDrawable;
              label266:
              for (;;) {}
            }
            if (PickIconActivity.7.a.this.c.startsWith("c:")) {
              localObject = PickIconActivity.7.a.this;
            }
            for (localDrawable = null;; localDrawable = PickIconActivity.this.getResources().getDrawable(e.b.l_ip_ic_pick_icon))
            {
              ((PickIconActivity.7.a)localObject).d = localDrawable;
              try
              {
                PickIconActivity.7.a.this.d = PickIconActivity.r(PickIconActivity.this).getDrawable(PickIconActivity.r(PickIconActivity.this).getIdentifier(PickIconActivity.7.a.this.c, "drawable", PickIconActivity.d(PickIconActivity.this)));
              }
              catch (Exception localException)
              {
                for (;;) {}
              }
              localObject = PickIconActivity.7.a.this;
            }
            PickIconActivity.j(PickIconActivity.this).post(PickIconActivity.7.a.this.h);
          }
        };
        Runnable h = new Runnable()
        {
          public void run()
          {
            PickIconActivity.7.a.this.a.setImageDrawable(PickIconActivity.7.a.this.d);
            PickIconActivity.7.a.this.a.startAnimation(PickIconActivity.7.a.this.e);
          }
        };
        
        a() {}
      }
    };
  }
  
  private void i()
  {
    View localView;
    if (j()) {
      localView = findViewById(e.c.root);
    }
    for (int i1 = -573780788;; i1 = -587202560)
    {
      localView.setBackgroundColor(i1);
      break;
      localView = findViewById(e.c.root);
    }
    ((ArrayAdapter)this.g.getAdapter()).notifyDataSetChanged();
  }
  
  @SuppressLint({"NewApi"})
  private boolean j()
  {
    return this.c.isChecked();
  }
  
  public void a()
  {
    d();
    this.h.notifyDataSetChanged();
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!c.a()) {
      c.a(getApplicationContext(), new b.c()new Runnable
      {
        public void a(int paramAnonymousInt)
        {
          if (PickIconActivity.m(PickIconActivity.this) != null)
          {
            PickIconActivity.m(PickIconActivity.this).setText(PickIconActivity.this.getString(e.f.l_ip_format_loading, new Object[] { Integer.valueOf(paramAnonymousInt) }));
            return;
          }
          PickIconActivity.a(PickIconActivity.this, Toast.makeText(PickIconActivity.this, PickIconActivity.this.getString(e.f.l_ip_format_loading, new Object[] { Integer.valueOf(paramAnonymousInt) }), 1));
          PickIconActivity.m(PickIconActivity.this).show();
        }
      }, new Runnable()
      {
        public void run()
        {
          PickIconActivity.n(PickIconActivity.this);
          PickIconActivity.b(PickIconActivity.this).notifyDataSetChanged();
          if (PickIconActivity.m(PickIconActivity.this) != null)
          {
            PickIconActivity.m(PickIconActivity.this).cancel();
            PickIconActivity.a(PickIconActivity.this, null);
          }
        }
      });
    }
  }
  
  public void onBackPressed()
  {
    if (this.d.getStatus() == 0)
    {
      this.d.b(true);
      return;
    }
    super.onBackPressed();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    b();
  }
  
  @SuppressLint({"NewApi", "ShowToast", "ClickableViewAccessibility"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(e.d.l_ip_layout_pick_icon);
    this.b = ((ImageView)findViewById(e.c.imageMenuIcon));
    this.c = ((CheckBox)findViewById(e.c.checkWhiteBg));
    if (paramBundle != null)
    {
      this.a = paramBundle.getString("currentPack", "");
      this.c.setChecked(paramBundle.getBoolean("whiteBg", false));
    }
    else
    {
      this.a = "";
      this.c.setChecked(false);
    }
    this.d = ((a)findViewById(e.c.layoutDrawer));
    this.e = ((ListView)findViewById(e.c.listView));
    this.e.setVerticalFadingEdgeEnabled(true);
    if (getIntent().getBooleanExtra("com.ss.iconpack.PickIconActivity.extra.RESET", false))
    {
      paramBundle = View.inflate(getApplicationContext(), e.d.l_ip_item_icon_pack, null);
      ((TextView)paramBundle.findViewById(e.c.text1)).setText(e.f.l_ip_reset);
      ((ImageView)paramBundle.findViewById(e.c.image1)).setImageResource(e.e.l_ip_ic_reset);
      this.e.addHeaderView(paramBundle);
    }
    paramBundle = View.inflate(getApplicationContext(), e.d.l_ip_item_icon_pack, null);
    ((TextView)paramBundle.findViewById(e.c.text1)).setText(e.f.l_ip_download);
    ((ImageView)paramBundle.findViewById(e.c.image1)).setImageResource(e.e.l_ip_ic_download);
    this.e.addFooterView(paramBundle);
    d();
    paramBundle = this.e;
    ArrayAdapter localArrayAdapter = e();
    this.h = localArrayAdapter;
    paramBundle.setAdapter(localArrayAdapter);
    this.e.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt < PickIconActivity.a(PickIconActivity.this).getHeaderViewsCount())
        {
          paramAnonymousAdapterView = new Intent();
          PickIconActivity.this.setResult(-1, paramAnonymousAdapterView);
          PickIconActivity.this.finish();
          return;
        }
        if (paramAnonymousInt == PickIconActivity.a(PickIconActivity.this).getHeaderViewsCount()) {
          PickIconActivity.a(PickIconActivity.this, "com.ss.iconpack.APPLICATION");
        }
        for (;;)
        {
          PickIconActivity.b(PickIconActivity.this).notifyDataSetChanged();
          PickIconActivity.c(PickIconActivity.this);
          return;
          paramAnonymousAdapterView = (PackageInfo)PickIconActivity.a(PickIconActivity.this).getItemAtPosition(paramAnonymousInt);
          if (paramAnonymousAdapterView == null) {
            try
            {
              PickIconActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(b.a())));
              return;
            }
            catch (Exception paramAnonymousAdapterView)
            {
              Toast.makeText(PickIconActivity.this.getApplicationContext(), paramAnonymousAdapterView.getMessage(), 1).show();
              return;
            }
          }
          if (paramAnonymousAdapterView.packageName.equals(PickIconActivity.d(PickIconActivity.this))) {
            break;
          }
          PickIconActivity.a(PickIconActivity.this, paramAnonymousAdapterView.packageName);
        }
      }
    });
    ((ImageView)findViewById(e.c.imageSearch)).setColorFilter(-12303292);
    this.f = ((EditText)findViewById(e.c.editSearch));
    this.f.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        PickIconActivity.a(PickIconActivity.this, paramAnonymousCharSequence);
      }
    });
    paramBundle = new View.OnTouchListener()
    {
      @SuppressLint({"ClickableViewAccessibility"})
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if ((paramAnonymousMotionEvent.getAction() == 0) && (PickIconActivity.e(PickIconActivity.this) != null) && (PickIconActivity.e(PickIconActivity.this).getStatus() == 0)) {
          PickIconActivity.e(PickIconActivity.this).b(true);
        }
        return false;
      }
    };
    this.f.setOnTouchListener(paramBundle);
    this.g = ((GridView)findViewById(e.c.gridView));
    this.g.setOnTouchListener(paramBundle);
    this.g.setAdapter(h());
    this.g.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if ("com.ss.iconpack.APPLICATION".equals(PickIconActivity.d(PickIconActivity.this))) {
          paramAnonymousAdapterView = ComponentName.unflattenFromString((String)PickIconActivity.f(PickIconActivity.this).get(paramAnonymousInt));
        }
        try
        {
          paramAnonymousInt = PickIconActivity.this.getPackageManager().getActivityInfo(paramAnonymousAdapterView, 0).getIconResource();
          if ((!a) && (paramAnonymousAdapterView == null)) {
            throw new AssertionError();
          }
          paramAnonymousView = PickIconActivity.this.getPackageManager().getResourcesForApplication(paramAnonymousAdapterView.getPackageName());
          Intent localIntent = new Intent();
          localIntent.putExtra("com.ss.iconpack.PickIconActivity.extra.ICON_PACK", paramAnonymousAdapterView.getPackageName());
          localIntent.putExtra("com.ss.iconpack.PickIconActivity.extra.ICON", paramAnonymousView.getResourceName(paramAnonymousInt));
          PickIconActivity.this.setResult(-1, localIntent);
          break label193;
          paramAnonymousAdapterView = (String)PickIconActivity.f(PickIconActivity.this).get(paramAnonymousInt);
          if (paramAnonymousAdapterView.startsWith("c:")) {
            return;
          }
          paramAnonymousView = new Intent();
          paramAnonymousView.putExtra("com.ss.iconpack.PickIconActivity.extra.ICON_PACK", PickIconActivity.d(PickIconActivity.this));
          paramAnonymousView.putExtra("com.ss.iconpack.PickIconActivity.extra.ICON", paramAnonymousAdapterView);
          PickIconActivity.this.setResult(-1, paramAnonymousView);
          label193:
          PickIconActivity.this.finish();
          return;
        }
        catch (PackageManager.NameNotFoundException paramAnonymousAdapterView) {}
      }
    });
    b();
    a(false);
    this.b.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (PickIconActivity.e(PickIconActivity.this).getStatus() == 0)
        {
          PickIconActivity.e(PickIconActivity.this).b(true);
          return;
        }
        PickIconActivity.e(PickIconActivity.this).a(true);
      }
    });
    this.d.setCallback(new a.a()
    {
      public void a()
      {
        PickIconActivity.a(PickIconActivity.this, true);
      }
      
      public void b()
      {
        PickIconActivity.a(PickIconActivity.this, true);
      }
    });
    i();
    this.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        PickIconActivity.g(PickIconActivity.this);
      }
    });
    c();
    c.a(this);
  }
  
  protected void onDestroy()
  {
    c.b(this);
    super.onDestroy();
    this.l = null;
    this.m = null;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("currentPack", this.a);
    paramBundle.putBoolean("whiteBg", ((CheckBox)findViewById(e.c.checkWhiteBg)).isChecked());
  }
}
