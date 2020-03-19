package app.homehabit.view.presentation.apppicker;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.View;
import app.homehabit.view.presentation.component.AppView;
import app.homehabit.view.support.view.RecyclerViewAdapter;
import app.homehabit.view.support.view.RecyclerViewAdapter.d;
import b.b.r;
import butterknife.BindView;
import butterknife.OnClick;
import com.b.a.k.e;
import com.jakewharton.a.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class AppPickerAdapter
  extends RecyclerViewAdapter<a>
{
  private final PackageManager a;
  private final app.homehabit.view.c.a b;
  private final c<e> c = c.a();
  
  AppPickerAdapter(PackageManager paramPackageManager, app.homehabit.view.c.a paramA)
  {
    this.a = paramPackageManager;
    this.b = paramA;
    a(a.class, 2131558434, new a(this));
    g();
  }
  
  private void g()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.a.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      if (this.a.getLaunchIntentForPackage(((ApplicationInfo)localObject).packageName) != null)
      {
        localObject = e.a(((ApplicationInfo)localObject).packageName);
        localArrayList.add(new a((e)localObject, this.b.b((e)localObject), this.b.c((e)localObject)));
      }
    }
    Collections.sort(localArrayList, b.a);
    a(localArrayList);
  }
  
  public r<e> a()
  {
    return this.c;
  }
  
  final class ViewHolder
    extends RecyclerViewAdapter.d<AppPickerAdapter.a>
  {
    @BindView
    AppView appView;
    
    ViewHolder(View paramView)
    {
      super();
    }
    
    protected void a(AppPickerAdapter.a paramA)
    {
      this.appView.setName(paramA.b);
      this.appView.setIcon(paramA.c);
    }
    
    @OnClick
    void onClick()
    {
      if (a())
      {
        AppPickerAdapter.a localA = (AppPickerAdapter.a)AppPickerAdapter.a(AppPickerAdapter.this, g());
        AppPickerAdapter.a(AppPickerAdapter.this).a(localA.a);
      }
    }
  }
  
  static final class a
  {
    final e a;
    final String b;
    final Drawable c;
    
    a(e paramE, String paramString, Drawable paramDrawable)
    {
      this.a = paramE;
      this.b = paramString;
      this.c = paramDrawable;
    }
  }
}
