package com.cmcm.swiper.notify;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cleanmaster.bitloader.BitmapLoader;
import com.cleanmaster.bitloader.BitmapLoader.TaskType;
import com.cleanmaster.configmanager.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SwipeNotificationGLayout
  extends LinearLayout
{
  public boolean a;
  private Context b;
  
  public SwipeNotificationGLayout(Context paramContext)
  {
    super(paramContext);
    this.b = paramContext;
  }
  
  public SwipeNotificationGLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.b = paramContext;
  }
  
  public SwipeNotificationGLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.b = paramContext;
  }
  
  public static boolean a(Context paramContext)
  {
    if (!com.cleanmaster.curlfloat.util.a.b.a()) {}
    while (com.cleanmaster.configmanager.b.a(paramContext).a("message_guide_count", 0) <= 0) {
      return false;
    }
    return true;
  }
  
  public static void f()
  {
    Object localObject = com.cleanmaster.ui.floatwindow.curlmanager.a.g().b.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((PackageInfo)((Iterator)localObject).next()).packageName;
      com.cleanmaster.ui.floatwindow.curlmanager.a.g();
      if (com.cleanmaster.ui.floatwindow.curlmanager.a.b(str)) {
        localArrayList.add(str);
      }
    }
    com.cleanmaster.configmanager.a.a().a.b(localArrayList);
  }
  
  public final void a()
  {
    removeAllViews();
    setPadding(0, LibcoreWrapper.a.i(this.b) / 7, 0, 0);
    View localView = LayoutInflater.from(this.b).inflate(2130903960, null);
    localView.setLayerType(0, null);
    LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(2131627992);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = com.cleanmaster.func.a.a.a().b();
    Object localObject2;
    if (localObject1 != null)
    {
      i = 0;
      while (i < ((List)localObject1).size())
      {
        localObject2 = (String)((List)localObject1).get(i);
        com.cleanmaster.ui.floatwindow.curlmanager.a.g();
        if (com.cleanmaster.ui.floatwindow.curlmanager.a.b((String)localObject2))
        {
          localArrayList.add(localObject2);
          if (localArrayList.size() >= 4) {
            break;
          }
        }
        i += 1;
      }
      if (localArrayList.size() > 0) {
        this.a = true;
      }
    }
    int i = 0;
    for (;;)
    {
      if (i < localArrayList.size())
      {
        localObject1 = LayoutInflater.from(this.b).inflate(2130904253, null);
        ((View)localObject1).setLayerType(0, null);
        localObject2 = (ImageView)((View)localObject1).findViewById(2131626918);
        ((ImageView)localObject2).setLayerType(0, null);
        BitmapLoader.b().b((ImageView)localObject2, (String)localArrayList.get(i), BitmapLoader.TaskType.INSTALLED_APK);
        localLinearLayout.addView((View)localObject1);
        if (LibcoreWrapper.a.h(this.b) < 540) {
          break label260;
        }
        if (i < 3) {
          break label265;
        }
      }
      label260:
      while (i >= 2)
      {
        addView(localView);
        return;
      }
      label265:
      i += 1;
    }
  }
  
  public final boolean b()
  {
    if (com.cleanmaster.configmanager.b.a(this.b).a("swipe_notification_guide_show_time", 0) < 8)
    {
      long l = com.cleanmaster.configmanager.b.a(this.b).a("swipe_notification_guide_show_current_time", 0L);
      if (l == 0L) {}
      while (System.currentTimeMillis() - l >= 86400000L) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  public final boolean c()
  {
    boolean bool = false;
    if (com.cleanmaster.configmanager.b.a(this.b).a("swipe_notification_guide_show_time", 0) >= 8) {
      bool = true;
    }
    return bool;
  }
  
  public final void d()
  {
    int i = com.cleanmaster.configmanager.b.a(this.b).a("swipe_notification_guide_show_time", 0);
    if (i < 8) {
      com.cleanmaster.configmanager.b.a(this.b).b("swipe_notification_guide_show_time", i + 1);
    }
  }
  
  public final void e()
  {
    com.cleanmaster.configmanager.b.a(this.b).a("swipe_notification_guide_show_current_time", Long.valueOf(System.currentTimeMillis()));
  }
}
