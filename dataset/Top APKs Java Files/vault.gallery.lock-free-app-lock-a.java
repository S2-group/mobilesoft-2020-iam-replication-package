package free.app.lock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import vault.gallery.lock.ag;

public class a
  extends BaseAdapter
{
  private final LayoutInflater a;
  private final PackageManager b;
  private final Context c;
  private final Set d;
  private List e;
  private boolean f;
  private c g;
  private boolean h;
  
  public a(Context paramContext)
  {
    this.c = paramContext;
    this.a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.b = paramContext.getPackageManager();
    this.d = new HashSet();
    this.e = new ArrayList();
    new b(this, null).execute(null);
  }
  
  private View a(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    d localD = (d)this.e.get(paramInt);
    View localView = paramView;
    if (paramView == null) {
      localView = this.a.inflate(2130903079, paramViewGroup, false);
    }
    paramView = (TextView)localView.findViewById(2131493041);
    paramView.setText(localD.a);
    paramView.setTypeface(ag.a);
    return localView;
  }
  
  @SuppressLint({"NewApi"})
  private void a(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.setBackgroundDrawable(paramDrawable);
      return;
    }
    paramView.setBackground(paramDrawable);
  }
  
  private void a(Collection paramCollection, q paramQ)
  {
    List localList1 = Arrays.asList(new String[] { "com.android.vending", "com.android.settings" });
    List localList2 = Arrays.asList(new String[] { "com.android.dialer" });
    PackageManager localPackageManager = this.c.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    int j = 0;
    int i = 0;
    if (!localIterator.hasNext()) {
      return;
    }
    ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
    if ("com.android.packageinstaller".equals(localApplicationInfo.packageName))
    {
      paramCollection.add(new d(this.c.getString(2131558460), localApplicationInfo, 6));
      paramQ.a(localApplicationInfo.packageName, 0);
      j = 1;
    }
    if (localList1.contains(localApplicationInfo.packageName))
    {
      paramCollection.add(new d(localApplicationInfo.loadLabel(localPackageManager).toString(), localApplicationInfo, 6));
      j = 1;
    }
    if (localList2.contains(localApplicationInfo.packageName))
    {
      paramCollection.add(new d(localApplicationInfo.loadLabel(localPackageManager).toString(), localApplicationInfo, 4));
      i = 1;
    }
    for (;;)
    {
      paramCollection.add(new d(this.c.getString(2131558463), 3));
      if (j != 0) {
        paramCollection.add(new d(this.c.getString(2131558461), 7));
      }
      if (i != 0) {
        paramCollection.add(new d(this.c.getString(2131558462), 5));
      }
      break;
    }
  }
  
  private void a(boolean paramBoolean)
  {
    if (this.h != paramBoolean)
    {
      this.h = paramBoolean;
      if (this.g != null) {
        this.g.b(paramBoolean);
      }
    }
  }
  
  private View b(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    d localD = (d)this.e.get(paramInt);
    View localView = paramView;
    if (paramView == null) {
      localView = this.a.inflate(2130903078, paramViewGroup, false);
    }
    paramView = (ImageView)localView.findViewById(2131493042);
    if (localD.c) {}
    for (paramInt = 2130837588;; paramInt = 2130837589)
    {
      paramView.setBackgroundResource(paramInt);
      paramView = (TextView)localView.findViewById(2131493041);
      paramView.setText(localD.b(this.b));
      paramView.setTypeface(ag.a);
      paramView = (ImageView)localView.findViewById(2131493040);
      paramViewGroup = localD.a(this.b);
      if (paramViewGroup != null) {
        break;
      }
      paramView.setVisibility(8);
      return localView;
    }
    a(paramView, paramViewGroup);
    return localView;
  }
  
  void a()
  {
    Object localObject1 = new q(this.c);
    a(this.d, (q)localObject1);
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
    localObject2 = this.b.queryIntentActivities((Intent)localObject2, 0).iterator();
    if (!((Iterator)localObject2).hasNext())
    {
      localObject1 = ((q)localObject1).a();
      localObject2 = this.d.iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        this.e = new ArrayList(this.d);
        return;
        localObject3 = (ResolveInfo)((Iterator)localObject2).next();
        if (this.c.getPackageName().equals(((ResolveInfo)localObject3).activityInfo.packageName)) {
          break;
        }
        localObject3 = new d(((ResolveInfo)localObject3).loadLabel(this.b).toString(), ((ResolveInfo)localObject3).activityInfo, 1);
        this.d.add(localObject3);
        ((q)localObject1).a(((d)localObject3).b, 0);
        break;
      }
      Object localObject3 = (d)((Iterator)localObject2).next();
      ((d)localObject3).c = ((ArrayList)localObject1).contains(((d)localObject3).b);
    }
  }
  
  public void a(c paramC)
  {
    this.g = paramC;
  }
  
  public void b()
  {
    Collections.sort(this.e);
    notifyDataSetChanged();
    a(false);
  }
  
  public int getCount()
  {
    return this.e.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.e.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public int getItemViewType(int paramInt)
  {
    if (((d)this.e.get(paramInt)).a()) {
      return 0;
    }
    return 1;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (((d)this.e.get(paramInt)).a()) {
      return b(paramInt, paramView, paramViewGroup);
    }
    return a(paramInt, paramView, paramViewGroup);
  }
  
  public int getViewTypeCount()
  {
    return 2;
  }
}
