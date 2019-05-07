package com.antivirus.ui.backup.apps;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.avg.toolkit.g.a;
import com.avg.ui.general.common.d;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class i
  implements Runnable
{
  i(e paramE, LayoutInflater paramLayoutInflater, LinearLayout paramLinearLayout, View paramView) {}
  
  public void run()
  {
    int j = 0;
    boolean bool;
    ArrayList localArrayList;
    PackageManager localPackageManager;
    ListIterator localListIterator;
    Object localObject1;
    if (e.i(this.d) == null)
    {
      bool = false;
      localArrayList = new ArrayList();
      e.a(this.d, new ArrayList());
      e.b(this.d, new ArrayList());
      localPackageManager = this.d.getActivity().getPackageManager();
      localListIterator = localPackageManager.getInstalledApplications(0).listIterator();
      e.b(this.d, true);
      localObject1 = (ApplicationInfo)localListIterator.next();
    }
    d localD1;
    for (;;)
    {
      if ((!e.j(this.d)) || (localObject1 == null)) {
        break label475;
      }
      try
      {
        if ((!((ApplicationInfo)localObject1).sourceDir.contains("/system")) && (!((ApplicationInfo)localObject1).sourceDir.contains("/vendor")))
        {
          d localD2 = new d();
          localD2.c = ((ApplicationInfo)localObject1).packageName;
          Object localObject2 = localPackageManager.getPackageInfo(localD2.c, 0);
          localD2.a = (this.d.getActivity().getPackageManager().getApplicationLabel((ApplicationInfo)localObject1) + " " + ((PackageInfo)localObject2).versionName);
          localD2.c = ((ApplicationInfo)localObject1).packageName;
          localD2.b = ((ApplicationInfo)localObject1).sourceDir;
          e.e(this.d).add(localD2);
          localObject1 = this.a.inflate(2130903063, null);
          localD2.e = ((View)localObject1);
          localD2.f = this.b;
          ((View)localObject1).setTag(localD2);
          ((TextView)((View)localObject1).findViewById(2131361905)).setText(localD2.a);
          localObject2 = (CheckBox)((View)localObject1).findViewById(2131361906);
          ((CheckBox)localObject2).setChecked(bool);
          ((CheckBox)localObject2).setFocusable(false);
          ((CheckBox)localObject2).setClickable(false);
          e.c(this.d).add(localObject2);
          localArrayList.add((ImageView)((View)localObject1).findViewById(2131361904));
          ((View)localObject1).setOnClickListener(new j(this, (CheckBox)localObject2, localD2));
          if (e.j(this.d)) {
            this.d.getActivity().runOnUiThread(new k(this, (View)localObject1));
          }
        }
        if ((localListIterator.hasNext()) && (e.j(this.d)))
        {
          localObject1 = (ApplicationInfo)localListIterator.next();
          continue;
          bool = e.i(this.d).isChecked();
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          a.a(localException);
        }
        localD1 = null;
      }
    }
    label475:
    int i = j;
    if (e.j(this.d))
    {
      this.d.getActivity().runOnUiThread(new l(this));
      i = j;
    }
    for (;;)
    {
      if ((e.j(this.d)) && (i < e.e(this.d).size()))
      {
        localD1 = (d)e.e(this.d).get(i);
        try
        {
          localD1.d = this.d.getActivity().getPackageManager().getApplicationIcon(localD1.c);
          if (e.j(this.d)) {
            this.d.getActivity().runOnUiThread(new m(this, localArrayList, i, localD1));
          }
          i += 1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            a.a(localNameNotFoundException);
          }
        }
      }
    }
  }
}
