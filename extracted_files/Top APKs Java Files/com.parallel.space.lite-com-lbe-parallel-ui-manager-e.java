package com.lbe.parallel.ui.manager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.t;
import android.support.v4.app.t.a;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lbe.parallel.DAApp;
import com.lbe.parallel.f.a;
import com.lbe.parallel.ff;
import com.lbe.parallel.fn;
import com.lbe.parallel.fq;
import com.lbe.parallel.kp;
import com.lbe.parallel.utility.c.1;
import com.lbe.parallel.widgets.ParallelIconView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class e
  extends com.lbe.parallel.base.b
{
  private RecyclerView a;
  private d b;
  private ProgressBar c;
  
  public e() {}
  
  public static e a()
  {
    e localE = new e();
    localE.setArguments(null);
    return localE;
  }
  
  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130968798, null, false);
    this.a = ((RecyclerView)paramLayoutInflater.findViewById(2131624690));
    this.a.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
    this.c = ((ProgressBar)paramLayoutInflater.findViewById(2131624267));
    this.c.setVisibility(0);
    this.b = new d(getActivity());
    this.a.setAdapter(this.b);
    getLoaderManager().a(0, null, new c(getActivity()));
    return paramLayoutInflater;
  }
  
  public final void onResume()
  {
    super.onResume();
    android.support.v4.content.e localE = getLoaderManager().b(0);
    if (localE != null) {
      localE.r();
    }
  }
  
  static final class a
    extends RecyclerView.ViewHolder
  {
    public ParallelIconView a;
    public TextView b;
    public TextView c;
    public View d;
    public View e;
    
    public a(View paramView)
    {
      super();
      this.e = paramView;
      this.a = ((ParallelIconView)paramView.findViewById(2131624090));
      this.b = ((TextView)paramView.findViewById(2131624091));
      this.c = ((TextView)paramView.findViewById(2131624599));
      this.d = paramView.findViewById(2131624491);
    }
  }
  
  static final class b
    extends com.lbe.parallel.utility.b
  {
    private int d = 0;
    private Context e;
    private fq f = null;
    private PackageManager g = null;
    
    public b(Context paramContext)
    {
      super();
      this.e = paramContext;
      this.d = DAApp.a().c();
      ff.a(paramContext);
      this.f = new fq(paramContext);
      this.g = paramContext.getPackageManager();
    }
    
    private List<b> s()
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      Object localObject1 = this.f.getInstalledPackages(0);
      HashMap localHashMap = new HashMap();
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject1).next();
          localHashMap.put(((PackageInfo)localObject3).packageName, localObject3);
        }
      }
      Object localObject3 = fn.a(this.e).e(this.d);
      if ((localObject3 != null) && (localObject3.length > 0))
      {
        int j = localObject3.length;
        int i = 0;
        for (;;)
        {
          if (i < j)
          {
            localObject1 = localObject3[i];
            PackageInfo localPackageInfo = (PackageInfo)localHashMap.get(localObject1);
            b localB;
            if (localPackageInfo != null)
            {
              localB = new b();
              localB.b = localPackageInfo.applicationInfo.loadIcon(this.g);
              localB.c = localPackageInfo.applicationInfo.loadLabel(this.g);
              localB.d = ((String)localObject1);
            }
            try
            {
              localObject1 = fn.a(this.e).d(DAApp.a().c(), (String)localObject1);
              if (localObject1 != null)
              {
                localB.a = Math.max(0L, ((PackageStats)localObject1).dataSize + ((PackageStats)localObject1).externalDataSize + ((PackageStats)localObject1).cacheSize + ((PackageStats)localObject1).externalCacheSize + ((PackageStats)localObject1).codeSize + ((PackageStats)localObject1).externalObbSize);
                localArrayList2.add(localB);
              }
              i += 1;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                localException.printStackTrace();
                localObject2 = null;
              }
            }
          }
        }
      }
      Object localObject2 = f.a.v();
      long l2 = localObject2[0];
      long l3 = localObject2[1];
      long l4 = localObject2[1];
      localObject2 = this.e;
      long l5 = 0L + c.1.e(((Context)localObject2).getApplicationInfo().dataDir + "/") + c.1.e(Environment.getExternalStorageDirectory() + "/Android/data/" + ((Context)localObject2).getPackageName() + "/");
      localObject2 = localArrayList2.iterator();
      for (long l1 = 0L; ((Iterator)localObject2).hasNext(); l1 = ((b)((Iterator)localObject2).next()).a + l1) {}
      localObject2 = new b();
      ((b)localObject2).d = this.e.getPackageName();
      ((b)localObject2).b = this.e.getResources().getDrawable(2130837818);
      ((b)localObject2).c = this.e.getString(2131165511);
      ((b)localObject2).a = Math.max(0L, l5 - l1);
      localArrayList2.add(localObject2);
      localObject2 = new c();
      ((c)localObject2).f = new HashMap();
      ((c)localObject2).f.put(Integer.valueOf(1), Long.valueOf(l5));
      ((c)localObject2).f.put(Integer.valueOf(2), Long.valueOf(l2 - l3));
      ((c)localObject2).f.put(Integer.valueOf(0), Long.valueOf(l4 - l5));
      localArrayList1.add(localObject2);
      Collections.sort(localArrayList2, new a());
      localArrayList1.addAll(localArrayList2);
      return localArrayList1;
    }
    
    public final class a
      implements Comparator<b>
    {
      public a() {}
    }
  }
  
  final class c
    implements t.a<List<b>>
  {
    private Context a;
    
    public c(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public final android.support.v4.content.e<List<b>> a(Bundle paramBundle)
    {
      return new e.b(this.a);
    }
    
    public final void d_()
    {
      e.a(e.this).a(null);
    }
  }
  
  public final class d
    extends RecyclerView.Adapter
  {
    private final Context b;
    private List<b> c;
    private long d = -1L;
    
    public d(Context paramContext)
    {
      this.b = paramContext;
      this.c = new ArrayList();
    }
    
    private b a(int paramInt)
    {
      return (b)this.c.get(paramInt);
    }
    
    public final void a(List<b> paramList)
    {
      this.c.clear();
      if ((paramList != null) && (paramList.size() > 0)) {
        this.c.addAll(paramList);
      }
      notifyDataSetChanged();
    }
    
    public final int getItemCount()
    {
      return this.c.size();
    }
    
    public final long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public final int getItemViewType(int paramInt)
    {
      if (paramInt == 0) {
        return 0;
      }
      return 1;
    }
    
    public final void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt)
    {
      switch (getItemViewType(paramInt))
      {
      default: 
        return;
      case 0: 
        if (!(paramViewHolder instanceof e.e)) {
          break;
        }
      }
      for (paramViewHolder = (e.e)paramViewHolder; paramViewHolder != null; paramViewHolder = null)
      {
        Object localObject = (c)a(paramInt);
        long l1 = ((Long)((c)localObject).f.get(Integer.valueOf(2))).longValue();
        long l2 = ((Long)((c)localObject).f.get(Integer.valueOf(0))).longValue();
        long l3 = ((Long)((c)localObject).f.get(Integer.valueOf(1))).longValue();
        if (this.d == l3) {
          break;
        }
        paramViewHolder.a.showAnimation(new long[] { l3, l1, l2 });
        this.d = l3;
        return;
        if ((paramViewHolder instanceof e.a)) {}
        for (paramViewHolder = (e.a)paramViewHolder; paramViewHolder != null; paramViewHolder = null)
        {
          localObject = a(paramInt);
          View localView;
          if (TextUtils.equals(this.b.getPackageName(), ((b)localObject).d))
          {
            paramViewHolder.a.setImageResourceWithoutBorder(2130837818);
            paramViewHolder.a.setImageDrawable(e.this.getResources().getDrawable(2131558636));
            paramViewHolder.b.setText(((b)localObject).c);
            paramViewHolder.c.setText(Formatter.formatShortFileSize(this.b, ((b)localObject).a));
            localView = paramViewHolder.d;
            if (paramInt != this.c.size() - 1) {
              break label341;
            }
          }
          label341:
          for (paramInt = 4;; paramInt = 0)
          {
            localView.setVisibility(paramInt);
            if (!TextUtils.equals(this.b.getPackageName(), ((b)localObject).d)) {
              break label346;
            }
            paramViewHolder.e.setOnClickListener(null);
            return;
            paramViewHolder.a.setImageDrawable(((b)localObject).b);
            paramViewHolder.a.setBackgroundResource(2130837893);
            break;
          }
          label346:
          paramViewHolder.e.setOnClickListener(new View.OnClickListener()
          {
            public final void onClick(View paramAnonymousView)
            {
              kp.c("event_storage_manager_click_app", this.a.d, this.a.c.toString());
              paramAnonymousView = new Intent(e.d.a(e.d.this), AppInfoActivity.class);
              paramAnonymousView.putExtra("app_package_name", this.a.d);
              e.this.startActivity(paramAnonymousView);
            }
          });
          return;
        }
        break;
      }
    }
    
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return null;
      case 0: 
        paramViewGroup = LayoutInflater.from(this.b).inflate(2130968833, null, false);
        paramViewGroup.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        return new e.e(paramViewGroup);
      }
      return new e.a(LayoutInflater.from(this.b).inflate(2130968832, null, false));
    }
  }
  
  static final class e
    extends RecyclerView.ViewHolder
  {
    public CircleProgressView a;
    
    public e(View paramView)
    {
      super();
      this.a = ((CircleProgressView)paramView.findViewById(2131624601));
    }
  }
}
