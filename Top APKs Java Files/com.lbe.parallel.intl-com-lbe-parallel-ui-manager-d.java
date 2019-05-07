package com.lbe.parallel.ui.manager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.s;
import android.support.v4.app.s.a;
import android.support.v4.content.e;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import com.lbe.multidroid.service.f;
import com.lbe.parallel.DAApp;
import com.lbe.parallel.jo.b;
import com.lbe.parallel.jv;
import com.lbe.parallel.ui.loader.ReferredAppLoader.AppComparatorInter;
import com.lbe.parallel.utility.ad;
import com.lbe.parallel.utility.c;
import com.lbe.parallel.widgets.ParallelIconView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class d
  extends com.lbe.parallel.base.b
{
  PopupWindow a;
  a b = null;
  b c = null;
  View.OnClickListener d = new View.OnClickListener()
  {
    public final void onClick(View paramAnonymousView)
    {
      if ((d.this.b == null) || (d.this.c == null))
      {
        d.this.a.dismiss();
        return;
      }
      if (paramAnonymousView.getId() == 2131558945)
      {
        if (d.this.c.d == 0)
        {
          d.this.a.dismiss();
          return;
        }
        d.this.c.d = 0;
        paramAnonymousView = "notificationNormal";
      }
      for (;;)
      {
        d.this.b.d.setImageBitmap((Bitmap)d.a(d.this).get(d.this.c.d));
        jv.a(paramAnonymousView, d.this.c.b);
        d.b(d.this).a(DAApp.o().q(), d.this.c.b, d.this.c.d);
        d.this.a.dismiss();
        return;
        if (paramAnonymousView.getId() == 2131558946)
        {
          if (d.this.c.d == 1)
          {
            d.this.a.dismiss();
            return;
          }
          d.this.c.d = 1;
          paramAnonymousView = "notificationNotDisturb";
        }
        else
        {
          if (d.this.c.d == 2)
          {
            d.this.a.dismiss();
            return;
          }
          d.this.c.d = 2;
          paramAnonymousView = "notificationForbidden";
        }
      }
    }
  };
  private RecyclerView e;
  private e f;
  private ProgressBar g;
  private TextView h;
  private View i;
  private f j;
  private List<Bitmap> k;
  
  public d() {}
  
  public static d b()
  {
    d localD = new d();
    localD.setArguments(null);
    return localD;
  }
  
  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.i = paramLayoutInflater.inflate(2130903213, null, false);
    this.e = ((RecyclerView)this.i.findViewById(2131558972));
    this.e.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
    this.j = com.lbe.multidroid.service.b.a(getActivity()).g();
    this.k = new ArrayList(3);
    this.k.add(BitmapFactory.decodeResource(getResources(), 2130837885));
    this.k.add(BitmapFactory.decodeResource(getResources(), 2130837886));
    this.k.add(BitmapFactory.decodeResource(getResources(), 2130837884));
    this.g = ((ProgressBar)this.i.findViewById(2131558667));
    this.g.setVisibility(0);
    this.f = new e(getActivity());
    this.e.setAdapter(this.f);
    getLoaderManager().a(0, null, new d(getActivity()));
    return this.i;
  }
  
  public final void onResume()
  {
    super.onResume();
    e localE = getLoaderManager().b(0);
    if (localE != null) {
      localE.r();
    }
  }
  
  static final class a
    extends RecyclerView.ViewHolder
  {
    public ParallelIconView a;
    public TextView b;
    public View c;
    public ImageView d;
    public View e;
    
    public a(View paramView)
    {
      super();
      this.a = ((ParallelIconView)paramView.findViewById(2131558659));
      this.b = ((TextView)paramView.findViewById(2131558535));
      this.c = paramView.findViewById(2131558930);
      this.d = ((ImageView)paramView.findViewById(2131558949));
      this.e = paramView.findViewById(2131558948);
    }
  }
  
  static final class b
    implements ReferredAppLoader.AppComparatorInter
  {
    public Drawable a;
    public String b;
    public CharSequence c;
    public int d;
    
    private b() {}
    
    public final String getName()
    {
      return this.c.toString();
    }
    
    public final String getPackageName()
    {
      return this.b;
    }
  }
  
  static final class c
    extends c
  {
    private int d = 0;
    private com.lbe.multidroid.service.b e = null;
    private PackageManagerWrapper f = null;
    
    public c(Context paramContext)
    {
      super();
      this.e = com.lbe.multidroid.service.b.a(paramContext);
      this.f = new PackageManagerWrapper(paramContext);
      paramContext.getPackageManager();
    }
    
    private List<d.b> s()
    {
      ArrayList localArrayList = new ArrayList();
      this.f.getInstalledPackages(0);
      Map localMap = this.e.g().a(this.d);
      Iterator localIterator = localMap.keySet().iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          d.b localB = new d.b((byte)0);
          localB.b = str;
          localB.d = ((Integer)localMap.get(str)).intValue();
          try
          {
            localB.c = this.f.getApplicationLabel(this.f.getApplicationInfo(str, 0));
            localB.a = this.f.getApplicationIcon(this.f.getApplicationInfo(str, 0));
            localArrayList.add(localB);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;)
            {
              localNameNotFoundException.printStackTrace();
            }
          }
        }
      }
      Collections.sort(localArrayList, new jo.b());
      return localArrayList;
    }
  }
  
  final class d
    implements s.a<List<d.b>>
  {
    private Context a;
    
    public d(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public final e<List<d.b>> a(Bundle paramBundle)
    {
      return new d.c(this.a);
    }
    
    public final void a()
    {
      d.c(d.this).a(null);
    }
  }
  
  public final class e
    extends RecyclerView.Adapter<d.a>
  {
    private final Context b;
    private List<d.b> c;
    
    public e(Context paramContext)
    {
      this.b = paramContext;
      this.c = new ArrayList();
    }
    
    public final void a(List<d.b> paramList)
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
  }
}
