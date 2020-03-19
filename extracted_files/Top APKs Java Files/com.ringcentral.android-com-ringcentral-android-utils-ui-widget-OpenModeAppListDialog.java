package com.ringcentral.android.utils.ui.widget;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.rcbase.android.logging.e;
import com.ringcentral.android.contacts.ah;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class OpenModeAppListDialog
  implements View.OnClickListener
{
  DialogInterface.OnDismissListener a;
  private Dialog b;
  private Activity c;
  private LayoutInflater d;
  private ListView e;
  private OpenModeAppListDialog.AppListAdapter f;
  private PackageManager g;
  private Intent h;
  private Button i;
  private Button j;
  private View k;
  private int l = -1;
  private boolean m = false;
  private String n;
  
  public OpenModeAppListDialog() {}
  
  public OpenModeAppListDialog(Activity paramActivity, Intent paramIntent, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.c = paramActivity;
    this.d = LayoutInflater.from(paramActivity);
    this.g = paramActivity.getPackageManager();
    this.h = paramIntent;
    this.n = paramString;
    c();
    if (paramBoolean2) {
      a(false, paramBoolean1);
    }
  }
  
  private List<OpenModeAppListDialog.DisplayResolveInfo> c(List<ResolveInfo> paramList)
  {
    if (paramList != null)
    {
      int i1 = paramList.size();
      if (i1 > 0)
      {
        Object localObject = (ResolveInfo)paramList.get(0);
        int i3 = 1;
        ResolveInfo localResolveInfo;
        while (i3 < i1)
        {
          localResolveInfo = (ResolveInfo)paramList.get(i3);
          if ((((ResolveInfo)localObject).priority != localResolveInfo.priority) || (((ResolveInfo)localObject).isDefault != localResolveInfo.isDefault))
          {
            int i2 = i1;
            for (;;)
            {
              i1 = i2;
              if (i3 >= i2) {
                break;
              }
              paramList.remove(i3);
              i2 -= 1;
            }
          }
          i3 += 1;
        }
        if (i1 > 1) {
          Collections.sort(paramList, new ResolveInfo.DisplayNameComparator(this.g));
        }
        localObject = new ArrayList();
        i1 = 0;
        while (i1 < paramList.size())
        {
          localResolveInfo = (ResolveInfo)paramList.get(i1);
          Intent localIntent = new Intent(this.h);
          localIntent.setComponent(new ComponentName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name));
          ((List)localObject).add(new OpenModeAppListDialog.DisplayResolveInfo(this, localResolveInfo, localResolveInfo.loadLabel(this.g), localResolveInfo.loadIcon(this.g), localIntent, false));
          i1 += 1;
        }
        return localObject;
      }
    }
    return null;
  }
  
  private void c()
  {
    this.b = new Dialog(this.c, 2131624119);
    this.k = this.d.inflate(2131427601, null);
    this.i = ((Button)this.k.findViewById(2131296347));
    this.j = ((Button)this.k.findViewById(2131296348));
    this.i.setOnClickListener(this);
    this.j.setOnClickListener(this);
    this.e = ((ListView)this.k.findViewById(2131296927));
    this.e.setSelector(2131231826);
    this.b.setContentView(this.k);
    this.e.setOnItemClickListener(new OpenModeAppListDialog.1(this));
  }
  
  private void d()
  {
    LinearLayout localLinearLayout = (LinearLayout)this.k.findViewById(2131296901);
    localLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OpenModeAppListDialog.2(this, localLinearLayout));
  }
  
  void a(int paramInt, boolean paramBoolean)
  {
    ResolveInfo localResolveInfo = this.f.c(paramInt);
    Intent localIntent = this.f.b(paramInt);
    String str2 = localIntent.getData().toString();
    String str1 = "";
    paramInt = str2.lastIndexOf('.');
    if (paramInt >= 0) {
      str1 = str2.substring(paramInt + 1).toLowerCase();
    }
    a(localResolveInfo, localIntent, paramBoolean, str1);
  }
  
  public void a(DialogInterface.OnDismissListener paramOnDismissListener)
  {
    this.a = paramOnDismissListener;
    this.b.setOnDismissListener(paramOnDismissListener);
  }
  
  protected void a(ResolveInfo paramResolveInfo, Intent paramIntent, boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      this.c.getSharedPreferences("default_app", 0).edit().putString(paramString, paramResolveInfo.activityInfo.packageName).apply();
    }
    if (paramIntent != null) {}
    try
    {
      this.c.startActivity(paramIntent);
      return;
    }
    catch (Exception paramResolveInfo)
    {
      e.b("[RC]OpenModeAppListDialog", "onIntentSelected(): startActivity() failed: ", paramResolveInfo);
    }
  }
  
  public void a(List<ResolveInfo> paramList)
  {
    List localList;
    if (paramList != null)
    {
      localList = c(paramList);
      if (!this.m) {
        break label197;
      }
      if (localList.size() != 1) {
        break label82;
      }
      a(((OpenModeAppListDialog.DisplayResolveInfo)localList.get(0)).a, ((OpenModeAppListDialog.DisplayResolveInfo)localList.get(0)).d, false, "");
      if (this.a != null) {
        this.a.onDismiss(this.b);
      }
    }
    label82:
    label195:
    for (;;)
    {
      return;
      int i1 = 0;
      for (;;)
      {
        if (i1 >= localList.size()) {
          break label195;
        }
        if (StringUtils.equals(((OpenModeAppListDialog.DisplayResolveInfo)localList.get(i1)).a.activityInfo.packageName, ((ResolveInfo)paramList.get(0)).activityInfo.packageName))
        {
          a(((OpenModeAppListDialog.DisplayResolveInfo)localList.get(i1)).a, ((OpenModeAppListDialog.DisplayResolveInfo)localList.get(i1)).d, false, "");
          if (this.a == null) {
            break;
          }
          this.a.onDismiss(this.b);
          return;
        }
        i1 += 1;
      }
    }
    label197:
    this.f = new OpenModeAppListDialog.AppListAdapter(this, localList);
    this.e.setAdapter(this.f);
    d();
    this.b.show();
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = 0;
    List localList = this.g.queryIntentActivities(this.h, 65536);
    ArrayList localArrayList = new ArrayList();
    Object localObject2;
    if (localList.size() > 0)
    {
      int i2;
      for (i1 = 0; i1 < localList.size(); i1 = i2 + 1)
      {
        localObject1 = (ResolveInfo)localList.get(i1);
        localObject2 = ((ResolveInfo)localObject1).activityInfo.packageName;
        if ((!((String)localObject2).toLowerCase().contains("ringcentral")) && (!((String)localObject2).toLowerCase().contains("attofficeathand")) && (!((String)localObject2).toLowerCase().contains("ringcentralapp")) && (!((String)localObject2).toLowerCase().contains("telusvoip")))
        {
          i2 = i1;
          if (!((String)localObject2).toLowerCase().contains("bt")) {}
        }
        else
        {
          localList.remove(localObject1);
          i2 = i1 - 1;
        }
      }
    }
    if (localList.size() == 0)
    {
      this.b.dismiss();
      if (paramBoolean2)
      {
        ah.a(this.c).setPositiveButton(2131559205, new OpenModeAppListDialog.3(this)).setIcon(2131231868).setTitle(2131559924).setMessage(2131559923).show();
        return;
      }
      ah.a(this.c).setIcon(2131231868).setNegativeButton(2131559205, new OpenModeAppListDialog.4(this)).setTitle(2131558406).setMessage(2131558405).show();
      return;
    }
    if (paramBoolean1)
    {
      b(c(localList));
      return;
    }
    Object localObject1 = this.c.getSharedPreferences("default_app", 0).getString(this.n, "");
    int i1 = i3;
    for (;;)
    {
      if (i1 < localList.size())
      {
        localObject2 = (ResolveInfo)localList.get(i1);
        if ((StringUtils.equals(((ResolveInfo)localObject2).activityInfo.packageName, (String)localObject1)) || (localList.size() == 1))
        {
          this.m = true;
          localArrayList.add(localObject2);
        }
      }
      else
      {
        if (!this.m) {
          break;
        }
        a(localArrayList);
        return;
      }
      i1 += 1;
    }
    a(localList);
  }
  
  public boolean a()
  {
    return this.b.isShowing();
  }
  
  public boolean a(String paramString)
  {
    boolean bool2 = false;
    List localList = this.g.getInstalledPackages(0);
    boolean bool1 = bool2;
    int i1;
    if (localList != null) {
      i1 = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i1 < localList.size())
      {
        String str = ((PackageInfo)localList.get(i1)).packageName;
        if ((str != null) && (str.equals(paramString))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  public void b(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((!paramBoolean1) || (!paramBoolean2))
    {
      this.l = -1;
      this.i.setEnabled(false);
      this.j.setEnabled(false);
      this.f.a();
    }
  }
  
  public boolean b()
  {
    boolean bool = false;
    List localList = this.g.queryIntentActivities(this.h, 65536);
    Log.d("starng", "" + localList.size());
    if (localList.size() > 0)
    {
      int i2;
      for (int i1 = 0; i1 < localList.size(); i1 = i2 + 1)
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i1);
        String str = localResolveInfo.activityInfo.packageName;
        if ((!str.toLowerCase().contains("ringcentral")) && (!str.toLowerCase().contains("attofficeathand")) && (!str.toLowerCase().contains("ringcentralapp")) && (!str.toLowerCase().contains("telusvoip")))
        {
          i2 = i1;
          if (!str.toLowerCase().contains("bt")) {}
        }
        else
        {
          localList.remove(localResolveInfo);
          i2 = i1 - 1;
        }
      }
    }
    if (localList.size() > 0) {
      bool = true;
    }
    return bool;
  }
  
  public boolean b(List<OpenModeAppListDialog.DisplayResolveInfo> paramList)
  {
    boolean bool3 = false;
    Object localObject;
    int i1;
    boolean bool1;
    if (this.f != null)
    {
      localObject = this.f.b();
      i1 = this.e.getCheckedItemPosition();
      if (i1 == -1) {
        break label164;
      }
      localObject = ((OpenModeAppListDialog.DisplayResolveInfo)((List)localObject).get(i1)).a.activityInfo.packageName;
      if (!a((String)localObject)) {
        break label159;
      }
      bool1 = true;
    }
    for (;;)
    {
      i1 = 0;
      for (;;)
      {
        boolean bool2 = bool3;
        if (i1 < paramList.size())
        {
          String str = ((OpenModeAppListDialog.DisplayResolveInfo)paramList.get(i1)).a.activityInfo.packageName;
          if ((bool1) && (localObject != null) && (StringUtils.equals((String)localObject, str)))
          {
            this.l = i1;
            bool2 = true;
          }
        }
        else
        {
          b(bool1, bool2);
          this.f.a(paramList, this.l);
          return true;
        }
        i1 += 1;
      }
      return false;
      label159:
      bool1 = false;
      continue;
      label164:
      localObject = null;
      bool1 = false;
    }
  }
  
  public void onClick(View paramView)
  {
    boolean bool = true;
    int i1;
    if (this.l != -1)
    {
      i1 = 1;
      if (i1 != 0)
      {
        this.e.smoothScrollToPosition(this.l);
        i1 = this.l;
        if (paramView.getId() != 2131296347) {
          break label60;
        }
      }
    }
    for (;;)
    {
      a(i1, bool);
      this.b.dismiss();
      return;
      i1 = 0;
      break;
      label60:
      bool = false;
    }
  }
}
