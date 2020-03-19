package com.lumaticsoft.watchdroidphone;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"StaticFieldLeak"})
final class an
  extends AsyncTask
{
  private an(am paramAm) {}
  
  private Boolean a()
  {
    int k = 0;
    for (;;)
    {
      try
      {
        localPackageManager = this.a.getPackageManager();
        localObject1 = localPackageManager.getInstalledApplications(0);
        Collections.sort((List)localObject1, new ApplicationInfo.DisplayNameComparator(localPackageManager));
        Iterator localIterator = ((List)localObject1).iterator();
        i = 0;
        if (localIterator.hasNext()) {
          localObject1 = (ApplicationInfo)localIterator.next();
        }
      }
      catch (Exception localException1)
      {
        PackageManager localPackageManager;
        Object localObject1;
        String str1;
        Object localObject2;
        boolean bool;
        continue;
      }
      try
      {
        str1 = ((ApplicationInfo)localObject1).packageName;
        j = i;
        if (str1 != null)
        {
          localObject1 = this.a.getPackageManager().getApplicationInfo(str1, 0);
          String str2 = (String)localPackageManager.getApplicationLabel((ApplicationInfo)localObject1);
          j = i;
          if (str2 != null)
          {
            localObject2 = localPackageManager.getApplicationIcon((ApplicationInfo)localObject1);
            localObject1 = localObject2;
            if (localObject2 == null) {
              localObject1 = this.a.getResources().getDrawable(2131558400);
            }
            am.c(this.a).put(Integer.valueOf(i), str1);
            am.d(this.a).put(Integer.valueOf(i), str2);
            am.e(this.a).put(Integer.valueOf(i), localObject1);
            publishProgress(new Integer[] { Integer.valueOf(i) });
            j = i + 1;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        j = i;
        continue;
      }
      bool = isCancelled();
      if (bool) {
        continue;
      }
      i = j;
    }
    try
    {
      am.a(this.a, new String[am.d(this.a).size()]);
      am.a(this.a, new Drawable[am.e(this.a).size()]);
      am.a(this.a, new Boolean[am.d(this.a).size()]);
      i = k;
      while (i < am.d(this.a).size())
      {
        am.f(this.a)[i] = ((String)am.d(this.a).get(Integer.valueOf(i)));
        am.g(this.a)[i] = ((Drawable)am.e(this.a).get(Integer.valueOf(i)));
        localObject2 = (Boolean)am.h(this.a).get(am.c(this.a).get(Integer.valueOf(i)));
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = Boolean.FALSE;
        }
        am.i(this.a)[i] = localObject1;
        bool = isCancelled();
        if (bool) {
          break;
        }
        i += 1;
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
    return Boolean.valueOf(true ^ isCancelled());
  }
  
  protected final void onCancelled() {}
  
  protected final void onPreExecute()
  {
    am localAm1 = this.a;
    am localAm2 = this.a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.a.getString(2131689583));
    localStringBuilder.append("...");
    am.a(localAm1, ProgressDialog.show(localAm2, null, localStringBuilder.toString(), true, true, new DialogInterface.OnCancelListener()
    {
      public final void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        try
        {
          an.this.cancel(true);
          an.this.a.finish();
          return;
        }
        catch (Exception paramAnonymousDialogInterface) {}
      }
    }));
  }
}
