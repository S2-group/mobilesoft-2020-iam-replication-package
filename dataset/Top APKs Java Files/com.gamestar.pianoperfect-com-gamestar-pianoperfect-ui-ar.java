package com.gamestar.pianoperfect.ui;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gamestar.pianoperfect.du;
import java.util.Iterator;
import java.util.List;

final class ar
  extends BaseAdapter
{
  List<PackageInfo> a;
  
  private ar(ao paramAo)
  {
    this.a = ao.a(paramAo).getPackageManager().getInstalledPackages(0);
  }
  
  public final int getCount()
  {
    return ao.b(this.b) + 1;
  }
  
  public final Object getItem(int paramInt)
  {
    return ao.c(this.b).get(paramInt);
  }
  
  public final long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public final View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    du localDu;
    Bitmap localBitmap;
    label86:
    String str;
    Iterator localIterator;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(ao.a(this.b)).inflate(2130903073, null);
      paramViewGroup = new av(this.b, paramView);
      paramView.setTag(paramViewGroup);
      localDu = (du)ao.c(this.b).get(paramInt);
      localBitmap = localDu.b();
      if (ao.d(this.b) == 4095) {
        break label176;
      }
      paramViewGroup.b.setBackgroundResource(2130837794);
      str = ((du)ao.c(this.b).get(paramInt)).d();
      localIterator = this.a.iterator();
      label118:
      if (localIterator.hasNext()) {
        break label188;
      }
      paramInt = 0;
      label130:
      if (paramInt == 0) {
        break label214;
      }
      paramViewGroup.b.setVisibility(0);
    }
    for (;;)
    {
      paramViewGroup.a.setImageBitmap(localBitmap);
      paramViewGroup.c.setText(localDu.a());
      return paramView;
      paramViewGroup = (av)paramView.getTag();
      break;
      label176:
      paramViewGroup.b.setBackgroundResource(2130837512);
      break label86;
      label188:
      if (!((PackageInfo)localIterator.next()).packageName.equals(str)) {
        break label118;
      }
      paramInt = 1;
      break label130;
      label214:
      paramViewGroup.b.setVisibility(8);
    }
  }
}
