package o;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import com.teslacoilsw.launcher.ActivitiesShortcutActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class bip
  extends AsyncTask
{
  public bip(ActivitiesShortcutActivity paramActivitiesShortcutActivity) {}
  
  private List eN()
  {
    ArrayList localArrayList1 = new ArrayList();
    PackageManager localPackageManager = this.eN.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        ArrayList localArrayList2;
        Object localObject2;
        Object localObject5;
        try
        {
          ActivityInfo[] arrayOfActivityInfo = this.eN.getPackageManager().getPackageInfo(localPackageInfo.packageName, 1).activities;
          if (arrayOfActivityInfo == null) {
            continue;
          }
          localArrayList2 = new ArrayList();
          int m = arrayOfActivityInfo.length;
          int i = 0;
          while (i < m)
          {
            ActivityInfo localActivityInfo = arrayOfActivityInfo[i];
            if ((localActivityInfo.isEnabled()) && ((localActivityInfo.exported) || (("com.teslacoilsw.launcher".equals(localPackageInfo.packageName)) && ("com.teslacoilsw.launcher.WidgetManagerActivity".equals(localActivityInfo.name))))) {}
            try
            {
              localObject4 = this.eN;
              int k = localActivityInfo.icon;
              int j = k;
              if (k == 0) {
                j = localActivityInfo.applicationInfo.icon;
              }
              localObject4 = ((ActivitiesShortcutActivity)localObject4).eN(localPackageManager, localActivityInfo.applicationInfo, j);
            }
            catch (Throwable localThrowable2)
            {
              for (;;)
              {
                Object localObject4;
                Object localObject1;
                localObject5 = null;
                System.gc();
              }
            }
            localObject6 = localObject4;
            if (localObject4 == null) {
              localObject6 = this.eN.getResources().getDrawable(2130903044);
            }
            localArrayList2.add(new biu(localActivityInfo.loadLabel(localPackageManager).toString(), (Drawable)localObject6, localActivityInfo));
            i += 1;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
            localObject1 = null;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            cvu.eN("Nova.Activities", "Could not load activities for " + localPackageInfo.packageName, localException);
            localObject2 = null;
          }
          localArrayList2.size();
        }
        if (localArrayList2.size() <= 0) {
          continue;
        }
        Collections.sort(localArrayList2, this.eN.De);
        Object localObject6 = ActivitiesShortcutActivity.eN(localPackageManager, localPackageInfo);
        try
        {
          localObject2 = this.eN;
          localObject5 = localPackageInfo.applicationInfo;
          localObject2 = ((ActivitiesShortcutActivity)localObject2).eN(localPackageManager, (ApplicationInfo)localObject5, ((ApplicationInfo)localObject5).icon);
          localObject5 = localObject2;
          if (localObject2 == null) {
            localObject5 = this.eN.getResources().getDrawable(2130903044);
          }
          localArrayList1.add(new biw((String)localObject6, (Drawable)localObject5, localArrayList2));
        }
        catch (Throwable localThrowable1)
        {
          for (;;)
          {
            Object localObject3 = null;
            System.gc();
          }
        }
      }
    }
    Collections.sort(localArrayList1, this.eN.De);
    return localArrayList1;
  }
}
