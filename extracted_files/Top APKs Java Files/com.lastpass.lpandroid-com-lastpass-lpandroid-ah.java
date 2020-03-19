package com.lastpass.lpandroid;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

final class ah
  extends AsyncTask
{
  ah(EditAppAssocActivity paramEditAppAssocActivity) {}
  
  private ArrayList a()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.a.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledApplications(128);
    if (localObject1 != null) {
      localObject1 = ((List)localObject1).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        Collections.sort(localArrayList);
        return localArrayList;
      }
      Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      if (f.a(((ApplicationInfo)localObject2).packageName)) {
        continue;
      }
      try
      {
        Object localObject3 = localPackageManager.getApplicationLabel((ApplicationInfo)localObject2).toString();
        if (this.a.a((String)localObject3)) {
          continue;
        }
        al localAl = new al(this.a);
        localAl.b = ((String)localObject3);
        localAl.c = os.a(localPackageManager.getApplicationIcon((ApplicationInfo)localObject2), 48);
        localAl.a = ((ApplicationInfo)localObject2).packageName;
        int i;
        if (this.a.b.containsKey(localAl.a))
        {
          localObject2 = (List)this.a.b.get(localAl.a);
          localObject3 = new StringBuilder();
          i = 0;
          label198:
          if (i >= ((List)localObject2).size()) {
            localAl.d = ((StringBuilder)localObject3).toString();
          }
        }
        for (;;)
        {
          localArrayList.add(localAl);
          break;
          if (i > 0) {
            ((StringBuilder)localObject3).append(", ");
          }
          ((StringBuilder)localObject3).append((String)((List)localObject2).get(i));
          i += 1;
          break label198;
          if (this.a.c.contains(localAl.a)) {
            localAl.e = true;
          }
        }
      }
      catch (Exception localException) {}
    }
  }
  
  private void a(ArrayList paramArrayList)
  {
    this.a.a = paramArrayList;
    this.a.findViewById(2131230825).setVisibility(0);
    this.a.findViewById(2131230824).setVisibility(8);
    this.a.e.a.addAll(this.a.a);
    this.a.e.notifyDataSetChanged();
    paramArrayList = (EditText)this.a.findViewById(2131230823);
    if (paramArrayList.getText().length() > 0)
    {
      this.a.f.a = paramArrayList.getText().toString().trim();
      this.a.g.post(this.a.f);
    }
  }
}
