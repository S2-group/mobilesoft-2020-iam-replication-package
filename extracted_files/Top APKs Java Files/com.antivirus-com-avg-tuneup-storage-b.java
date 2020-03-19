package com.avg.tuneup.storage;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.i;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.avg.ui.general.common.h;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class b
  extends com.avg.ui.general.fragments.a
{
  public int a = -1;
  public int b = -1;
  private boolean c;
  private com.avg.tuneup.a d;
  private com.avg.tuneup.b e;
  private HashMap f = new HashMap();
  private TextView g;
  private ProgressBar h;
  private ListView i;
  private BroadcastReceiver j = null;
  private e k;
  
  public b() {}
  
  private static int a(PackageInfo paramPackageInfo)
  {
    int m = -2;
    if (Build.VERSION.SDK_INT >= 8) {}
    try
    {
      Field localField = PackageInfo.class.getDeclaredField("installLocation");
      localField.setAccessible(true);
      m = localField.getInt(paramPackageInfo);
      return m;
    }
    catch (SecurityException paramPackageInfo)
    {
      com.avg.toolkit.g.a.a(paramPackageInfo);
      return -2;
    }
    catch (NoSuchFieldException paramPackageInfo)
    {
      com.avg.toolkit.g.a.a(paramPackageInfo);
      return -2;
    }
    catch (IllegalArgumentException paramPackageInfo)
    {
      com.avg.toolkit.g.a.a(paramPackageInfo);
      return -2;
    }
    catch (IllegalAccessException paramPackageInfo)
    {
      com.avg.toolkit.g.a.a(paramPackageInfo);
    }
    return -2;
  }
  
  private List a(PackageManager paramPackageManager, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    ListIterator localListIterator = paramPackageManager.getInstalledApplications(0).listIterator();
    ApplicationInfo localApplicationInfo = (ApplicationInfo)localListIterator.next();
    while (localApplicationInfo != null)
    {
      if (((!localApplicationInfo.sourceDir.contains("/system")) && (!localApplicationInfo.sourceDir.contains("/vendor")) && (this.f.get(localApplicationInfo.packageName) == null)) || (paramInt == 1))
      {
        com.avg.d.a.b.a localA = new com.avg.d.a.b.a();
        localA.c = localApplicationInfo.packageName.trim();
        localA.b = localApplicationInfo.sourceDir;
        localA.f = new File(localA.b).length();
        localA.k = localApplicationInfo.flags;
        localA.m = localApplicationInfo.uid;
        localA.a = paramPackageManager.getApplicationLabel(localApplicationInfo).toString().trim();
        if (localA.f > 0L) {
          localArrayList.add(localA);
        }
      }
      if (localListIterator.hasNext()) {
        localApplicationInfo = (ApplicationInfo)localListIterator.next();
      } else {
        localApplicationInfo = null;
      }
    }
    return localArrayList;
  }
  
  private void a()
  {
    b();
    this.k = new e(this, null);
    this.k.execute(new Void[0]);
  }
  
  private void a(PackageManager paramPackageManager, List paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.avg.d.a.b.a localA = (com.avg.d.a.b.a)paramList.next();
      try
      {
        localA.d = paramPackageManager.getApplicationIcon(localA.c);
      }
      catch (Exception localException)
      {
        com.avg.toolkit.g.a.a(localException);
      }
    }
  }
  
  private void a(View paramView)
  {
    a(paramView, Environment.getDataDirectory(), com.avg.c.e.tv_memory_internal, com.avg.c.e.tv_free_memory_internal, com.avg.c.e.pb_internal, false, com.avg.c.e.textView1);
    a(paramView, f.a(), com.avg.c.e.tv_memory_external, com.avg.c.e.tv_free_memory_external, com.avg.c.e.pb_external, true, com.avg.c.e.textView2);
  }
  
  private void a(View paramView, int paramInt, long paramLong1, long paramLong2)
  {
    paramView = (ProgressBar)paramView.findViewById(paramInt);
    paramView.setVisibility(0);
    if (paramLong1 > 0L)
    {
      a(paramView, (int)((paramLong1 - paramLong2) * 100L / paramLong1));
      return;
    }
    paramView.setProgress(0);
  }
  
  private void a(View paramView, File paramFile, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramFile != null) {
      localArrayList.add(paramFile.getPath());
    }
    a(paramView, localArrayList, paramInt1, paramInt2, paramInt3, paramBoolean, paramInt4);
  }
  
  private void a(View paramView, ArrayList paramArrayList, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
  {
    if ((paramBoolean) && ((paramArrayList == null) || (paramArrayList.isEmpty())))
    {
      paramView.findViewById(com.avg.c.e.tableRow2).setVisibility(8);
      paramView.findViewById(com.avg.c.e.tableRow1).setPadding(0, 0, 0, 0);
      return;
    }
    Object localObject1 = Environment.getExternalStorageState();
    if (("mounted".equals(localObject1)) || ("mounted_ro".equals(localObject1))) {}
    for (int m = 1;; m = 0)
    {
      long l3 = 0L;
      paramArrayList = paramArrayList.iterator();
      long l1 = 0L;
      long l2 = 0L;
      long l4;
      if (paramArrayList.hasNext())
      {
        localObject1 = (String)paramArrayList.next();
        if (((!paramBoolean) || (m == 0)) && (paramBoolean)) {
          break label383;
        }
        localObject1 = new StatFs((String)localObject1);
        long l5 = f.a((StatFs)localObject1);
        l4 = f.c((StatFs)localObject1) + l2;
        l3 += f.b((StatFs)localObject1);
        l2 = l5 + l1;
        l1 = l4;
      }
      for (;;)
      {
        l4 = l2;
        l2 = l1;
        l1 = l4;
        break;
        Object localObject2 = Formatter.formatFileSize(getActivity(), l1);
        localObject1 = Formatter.formatFileSize(getActivity(), l2);
        paramArrayList = Formatter.formatFileSize(getActivity(), l3);
        com.avg.toolkit.g.a.a("Total memory size: " + (String)localObject2 + "\nUsed memory size: " + (String)localObject1 + "\nAvailable memory size: " + paramArrayList);
        localObject2 = (TextView)paramView.findViewById(paramInt1);
        ((TextView)localObject2).setText(getActivity().getString(com.avg.c.g.used) + (String)localObject1);
        ((TextView)localObject2).setVisibility(0);
        localObject1 = (TextView)paramView.findViewById(paramInt2);
        ((TextView)localObject1).setText(getActivity().getString(com.avg.c.g.free) + paramArrayList);
        ((TextView)localObject1).setVisibility(0);
        a(paramView, paramInt3, l1, l3);
        paramView.findViewById(paramInt4).setVisibility(0);
        return;
        label383:
        l4 = l1;
        l1 = l2;
        l2 = l4;
      }
    }
  }
  
  private void a(ProgressBar paramProgressBar, int paramInt)
  {
    g localG = new g(paramProgressBar, 0.0F, paramInt);
    localG.setDuration((3000.0F * (paramInt / 100.0F)));
    paramProgressBar.startAnimation(localG);
  }
  
  private void a(com.avg.d.a.b.a paramA)
  {
    if (getActivity() == null) {
      return;
    }
    byte b1;
    if (Build.VERSION.SDK_INT >= 8) {
      if ((paramA.k & 0x40000) != 0) {
        b1 = 2;
      }
    }
    for (;;)
    {
      paramA.l = b1;
      return;
      Object localObject1 = null;
      Object localObject2 = getActivity().getPackageManager();
      try
      {
        localObject2 = ((PackageManager)localObject2).getPackageInfo(paramA.c, 0);
        localObject1 = localObject2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          int m;
          com.avg.toolkit.g.a.a(localException);
        }
      }
      if (((paramA.k & 0x20000000) == 0) && ((paramA.k & 0x1) == 0) && (localObject1 != null))
      {
        m = a(localObject1);
        if ((m == 2) || (m == 0))
        {
          b1 = 1;
          continue;
        }
      }
      b1 = 0;
    }
  }
  
  private void a(List paramList)
  {
    Collections.sort(paramList, this.d);
  }
  
  private void a(boolean paramBoolean)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    if (this.j == null)
    {
      this.j = new c(this);
      localIntentFilter.addAction("android.intent.action.MEDIA_EJECT");
      localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
      localIntentFilter.addDataScheme("file");
    }
    if (paramBoolean)
    {
      getActivity().registerReceiver(this.j, localIntentFilter);
      return;
    }
    getActivity().unregisterReceiver(this.j);
  }
  
  private void b()
  {
    if (this.k != null)
    {
      if (this.k.getStatus() == AsyncTask.Status.RUNNING) {
        this.k.cancel(true);
      }
      this.k = null;
    }
  }
  
  private void b(List paramList)
  {
    if (Build.VERSION.SDK_INT >= 8)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        a((com.avg.d.a.b.a)paramList.next());
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public ArrayList a(Object paramObject)
  {
    if ((paramObject instanceof Menu))
    {
      ((Menu)paramObject).add(0, 9, 0, getActivity().getString(com.avg.c.g.by_size));
      ((Menu)paramObject).add(0, 10, 0, getActivity().getString(com.avg.c.g.by_name));
      if ((Build.VERSION.SDK_INT >= 8) && ((Build.VERSION.SDK_INT < 11) || (!Environment.isExternalStorageEmulated()))) {
        ((Menu)paramObject).add(0, 11, 0, getActivity().getString(com.avg.c.g.by_location));
      }
    }
    for (;;)
    {
      com.avg.toolkit.d.a.a(getActivity(), "storage_usage", "menu_opened", null, 0);
      return null;
      if ((paramObject instanceof h))
      {
        ((h)paramObject).a(0, 9, 0, getActivity().getString(com.avg.c.g.by_size));
        ((h)paramObject).a(0, 10, 0, getActivity().getString(com.avg.c.g.by_name));
        if ((Build.VERSION.SDK_INT >= 8) && ((Build.VERSION.SDK_INT < 11) || (!Environment.isExternalStorageEmulated()))) {
          ((h)paramObject).a(0, 11, 0, getActivity().getString(com.avg.c.g.by_location));
        }
      }
    }
  }
  
  public boolean a(int paramInt)
  {
    if ((this.e == null) || (!this.c)) {
      return true;
    }
    switch (paramInt)
    {
    default: 
      com.avg.toolkit.g.a.a();
      return false;
    case 9: 
      this.d.a();
      Collections.sort(this.e.a(), this.d);
      this.e.notifyDataSetChanged();
      com.avg.toolkit.d.a.a(getActivity(), "storage_usage", "sort_by_size", null, 0);
      return true;
    case 10: 
      this.d.b();
      Collections.sort(this.e.a(), this.d);
      this.e.notifyDataSetChanged();
      com.avg.toolkit.d.a.a(getActivity(), "storage_usage", "sort_by_name", null, 0);
      return true;
    }
    this.d.c();
    Collections.sort(this.e.a(), this.d);
    this.e.notifyDataSetChanged();
    com.avg.toolkit.d.a.a(getActivity(), "storage_usage", "sort_by_location", null, 0);
    return true;
  }
  
  public boolean a(MenuItem paramMenuItem)
  {
    getActivity().closeOptionsMenu();
    return a(paramMenuItem.getItemId());
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    return a(paramMenuItem);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.f.put("com.android.launcher", Boolean.valueOf(true));
    this.f.put("com.android.launcher2", Boolean.valueOf(true));
    this.f.put("com.htc.launcher", Boolean.valueOf(true));
    this.f.put("com.htc.android.mail", Boolean.valueOf(true));
    this.f.put(getActivity().getPackageName(), Boolean.valueOf(true));
    this.d = new com.avg.tuneup.a(-1);
    this.c = false;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (paramViewGroup == null) {
      return null;
    }
    paramLayoutInflater = paramLayoutInflater.inflate(com.avg.c.f.memory_use_list, paramViewGroup, false);
    this.i = ((ListView)paramLayoutInflater.findViewById(com.avg.c.e.lv_apps));
    this.i.setCacheColorHint(0);
    this.e = new com.avg.tuneup.b(getActivity(), 0, null);
    this.i.setAdapter(this.e);
    this.h = ((ProgressBar)paramLayoutInflater.findViewById(com.avg.c.e.apps_progress_bar));
    this.g = ((TextView)paramLayoutInflater.findViewById(com.avg.c.e.tv_loading));
    paramLayoutInflater.findViewById(com.avg.c.e.bottom_buttons_bar).setVisibility(0);
    paramLayoutInflater.findViewById(com.avg.c.e.tv_title).setVisibility(8);
    if (((com.avg.ui.general.a.a)getActivity()).o())
    {
      a(getActivity().getString(com.avg.c.g.performance_storage), paramLayoutInflater);
      setHasOptionsMenu(true);
    }
    a(paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return a(paramMenuItem);
  }
  
  public void onPause()
  {
    super.onPause();
    a(false);
    b();
    this.c = false;
  }
  
  public void onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    a(paramMenu);
    super.onPrepareOptionsMenu(paramMenu);
  }
  
  public void onResume()
  {
    super.onResume();
    a(true);
    a();
  }
}
