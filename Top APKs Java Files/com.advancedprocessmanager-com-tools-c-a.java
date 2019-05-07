package com.tools.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.advancedprocessmanager.bw;
import com.tools.tools.PagerSlidingTabStrip;
import com.tools.tools.l;
import com.tools.tools.m;
import com.tools.tools.r;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
  extends Fragment
  implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
{
  static int az = 0;
  PackageManager a;
  PagerSlidingTabStrip aA;
  Handler aB = new b(this);
  LinearLayout aj;
  LinearLayout ak;
  LinearLayout al;
  TextView am;
  TextView an;
  TextView ao;
  View ap;
  View aq;
  View ar;
  GridView as;
  GridView at;
  GridView au;
  ViewPager av;
  boolean aw;
  public LinearLayout ax;
  int ay;
  List b;
  List c;
  List d;
  LayoutInflater e;
  f f;
  f g;
  f h;
  Resources i;
  
  public a() {}
  
  public static boolean O()
  {
    if (Build.VERSION.SDK_INT >= 14) {
      try
      {
        Class localClass = Class.forName("android.os.Environment");
        boolean bool = ((Boolean)localClass.getMethod("isExternalStorageEmulated", new Class[0]).invoke(localClass, new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo, Context paramContext)
  {
    try
    {
      int j = paramApplicationInfo.getClass().getDeclaredField("installLocation").getInt(paramApplicationInfo);
      return (j == 0) || (j == 2);
    }
    catch (Exception localException)
    {
      System.out.println("----------: " + localException.getMessage());
    }
    return c(paramContext, paramApplicationInfo.packageName);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    for (;;)
    {
      int j;
      try
      {
        paramContext = paramContext.createPackageContext(paramString, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
        j = paramContext.getEventType();
        if (j != 1)
        {
          if ((j == 2) && (paramContext.getName().matches("manifest")))
          {
            int k = paramContext.getAttributeCount();
            j = 0;
            if (j < k)
            {
              if ((!paramContext.getAttributeName(j).matches("installLocation")) || (Integer.parseInt(paramContext.getAttributeValue(j)) != 0)) {
                break label131;
              }
              return true;
            }
          }
          j = paramContext.nextToken();
        }
        else
        {
          return false;
        }
      }
      catch (Exception paramContext)
      {
        System.out.println("App2SD:  " + paramContext.getMessage());
      }
      label131:
      j += 1;
    }
  }
  
  public void M()
  {
    this.aB.sendEmptyMessage(0);
    new c(this).start();
  }
  
  public void N()
  {
    for (;;)
    {
      try
      {
        this.c.clear();
        this.b.clear();
        this.d.clear();
        Object localObject3;
        try
        {
          Object localObject1 = System.out;
          localObject3 = new StringBuilder();
          if (!this.aw)
          {
            bool = true;
            ((PrintStream)localObject1).println(bool + "              " + bw.g());
            localObject1 = h().getPackageManager().getInstalledApplications(16384).iterator();
            if (((Iterator)localObject1).hasNext())
            {
              localObject3 = (ApplicationInfo)((Iterator)localObject1).next();
              if ((((ApplicationInfo)localObject3).flags & 0x1) != 0) {
                continue;
              }
              if ((((ApplicationInfo)localObject3).flags & 0x40000) == 0) {
                continue;
              }
              this.c.add(new e(this, (ApplicationInfo)localObject3));
              continue;
            }
          }
          boolean bool = false;
        }
        catch (Exception localException)
        {
          return;
        }
        continue;
        if (((!this.aw) || (bw.g())) && (a((ApplicationInfo)localObject3, h()))) {
          this.b.add(new e(this, (ApplicationInfo)localObject3));
        } else {
          this.d.add(new e(this, (ApplicationInfo)localObject3));
        }
      }
      finally {}
    }
  }
  
  public View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.ax = ((LinearLayout)paramLayoutInflater.inflate(2130968579, paramViewGroup, false));
    this.ax.setBackgroundColor(m.b(h(), 2130771969));
    this.e = paramLayoutInflater;
    return this.ax;
  }
  
  public void a()
  {
    int j = r.b(h()) / 160;
    if (j < 1) {
      j = 1;
    }
    for (;;)
    {
      this.ar = this.e.inflate(2130968580, null);
      this.al = ((LinearLayout)this.ar.findViewById(2131427334));
      this.au = ((GridView)this.ar.findViewById(2131427352));
      this.au.setNumColumns(j);
      this.au.setOnItemClickListener(this);
      this.au.setOnItemLongClickListener(this);
      this.au.setAdapter(this.h);
      this.ao = ((TextView)this.ar.findViewById(2131427353));
      this.aq = this.e.inflate(2130968580, null);
      this.ak = ((LinearLayout)this.aq.findViewById(2131427334));
      this.at = ((GridView)this.aq.findViewById(2131427352));
      this.at.setNumColumns(j);
      this.at.setOnItemClickListener(this);
      this.at.setOnItemLongClickListener(this);
      this.at.setAdapter(this.f);
      this.an = ((TextView)this.aq.findViewById(2131427353));
      this.ap = this.e.inflate(2130968580, null);
      this.aj = ((LinearLayout)this.ap.findViewById(2131427334));
      this.as = ((GridView)this.ap.findViewById(2131427352));
      this.as.setNumColumns(j);
      this.as.setOnItemClickListener(this);
      this.as.setOnItemLongClickListener(this);
      this.as.setAdapter(this.g);
      this.am = ((TextView)this.ap.findViewById(2131427353));
      return;
    }
  }
  
  public void d(Bundle paramBundle)
  {
    super.d(paramBundle);
    this.a = h().getPackageManager();
    this.b = new ArrayList();
    this.c = new ArrayList();
    this.d = new ArrayList();
    this.i = i();
    this.g = new f(this, h());
    this.f = new f(this, h());
    this.h = new f(this, h());
    this.ay = this.i.getDimensionPixelSize(2131296273);
    a();
    this.av = ((ViewPager)this.ax.findViewById(2131427351));
    paramBundle = a(2131099650);
    String str1 = a(2131099651);
    String str2 = a(2131099652);
    View localView1 = this.ap;
    View localView2 = this.aq;
    View localView3 = this.ar;
    paramBundle = new l(new CharSequence[] { paramBundle, str1, str2 }, new View[] { localView1, localView2, localView3 });
    this.av.setAdapter(paramBundle);
    this.aA = ((PagerSlidingTabStrip)h().findViewById(2131427422));
    this.aA.setViewPager(this.av);
    this.av.setCurrentItem(az);
    this.aw = O();
  }
  
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (f)paramAdapterView.getAdapter();
    m.c(h(), ((e)paramAdapterView.getItem(paramInt)).c);
  }
  
  public boolean onItemLongClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (e)((f)paramAdapterView.getAdapter()).getItem(paramInt);
    paramView = new PopupMenu(h(), paramView);
    Menu localMenu = paramView.getMenu();
    localMenu.add(0, 0, 0, 2131099879);
    localMenu.add(0, 1, 0, 2131099796);
    localMenu.add(0, 2, 0, 2131099672);
    paramView.setOnMenuItemClickListener(new d(this, paramAdapterView));
    paramView.show();
    return true;
  }
  
  public void q()
  {
    M();
    super.q();
  }
}
