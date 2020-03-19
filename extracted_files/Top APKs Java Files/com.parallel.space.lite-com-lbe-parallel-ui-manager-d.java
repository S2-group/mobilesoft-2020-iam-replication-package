package com.lbe.parallel.ui.manager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.t;
import android.support.v4.app.t.a;
import android.support.v4.content.e;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnDismissListener;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.lbe.parallel.DAApp;
import com.lbe.parallel.ff;
import com.lbe.parallel.fk;
import com.lbe.parallel.fq;
import com.lbe.parallel.kh.b;
import com.lbe.parallel.kp;
import com.lbe.parallel.ui.loader.ReferredAppLoader.AppComparatorInter;
import com.lbe.parallel.utility.af;
import com.lbe.parallel.widgets.DividerItemDecoration;
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
  private RecyclerView a;
  private e b;
  private ProgressBar c;
  private TextView d;
  private View e;
  private fk f;
  private List<Bitmap> g;
  private PopupMenu h;
  private RecyclerView.ItemDecoration i;
  
  public d() {}
  
  public static d a()
  {
    d localD = new d();
    localD.setArguments(null);
    return localD;
  }
  
  private void a(RecyclerView paramRecyclerView)
  {
    if (this.i != null)
    {
      paramRecyclerView.removeItemDecoration(this.i);
      this.i = null;
    }
    Drawable localDrawable = getActivity().getResources().getDrawable(2130837943);
    int j = af.b(getActivity(), 2131296304);
    localDrawable.setBounds(0, 0, getResources().getDisplayMetrics().widthPixels, j);
    this.i = new DividerItemDecoration(localDrawable);
    paramRecyclerView.addItemDecoration(this.i);
  }
  
  public final void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    a(this.a);
  }
  
  public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.e = paramLayoutInflater.inflate(2130968798, null, false);
    this.a = ((RecyclerView)this.e.findViewById(2131624690));
    this.a.setHasFixedSize(true);
    this.a.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
    a(this.a);
    this.f = fk.a(getActivity());
    this.g = new ArrayList(3);
    this.g.add(BitmapFactory.decodeResource(getResources(), 2130837959));
    this.g.add(BitmapFactory.decodeResource(getResources(), 2130837960));
    this.g.add(BitmapFactory.decodeResource(getResources(), 2130837958));
    this.c = ((ProgressBar)this.e.findViewById(2131624267));
    this.c.setVisibility(0);
    this.b = new e(getActivity());
    this.a.setAdapter(this.b);
    getLoaderManager().a(0, null, new d(getActivity()));
    return this.e;
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
    public ImageView c;
    public View d;
    
    public a(View paramView)
    {
      super();
      this.a = ((ParallelIconView)paramView.findViewById(2131624090));
      this.b = ((TextView)paramView.findViewById(2131624091));
      this.c = ((ImageView)paramView.findViewById(2131624636));
      this.d = paramView.findViewById(2131624635);
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
    extends com.lbe.parallel.utility.b
  {
    private int d = 0;
    private Context e;
    private fq f = null;
    
    public c(Context paramContext)
    {
      super();
      this.e = paramContext;
      this.d = DAApp.a().c();
      ff.a(paramContext);
      this.f = new fq(paramContext);
      paramContext.getPackageManager();
    }
    
    private List<d.b> s()
    {
      ArrayList localArrayList = new ArrayList();
      this.f.getInstalledPackages(0);
      Map localMap = fk.a(this.e).a(this.d);
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        d.b localB = new d.b((byte)0);
        localB.b = str;
        localB.d = ((Integer)localMap.get(str)).intValue();
        try
        {
          CharSequence localCharSequence = this.f.getApplicationLabel(this.f.getApplicationInfo(str, 0));
          Object localObject = localCharSequence;
          if (TextUtils.isEmpty(localCharSequence)) {
            localObject = "";
          }
          localB.c = ((CharSequence)localObject);
          localB.a = this.f.getApplicationIcon(this.f.getApplicationInfo(str, 0));
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
        localArrayList.add(localB);
      }
      Collections.sort(localArrayList, new kh.b());
      return localArrayList;
    }
  }
  
  final class d
    implements t.a<List<d.b>>
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
    
    public final void d_()
    {
      d.d(d.this).a(null);
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
