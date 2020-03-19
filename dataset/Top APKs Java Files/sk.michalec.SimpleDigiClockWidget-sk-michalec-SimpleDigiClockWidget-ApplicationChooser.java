package sk.michalec.SimpleDigiClockWidget;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicationChooser
{
  public String[] pckgAppName;
  public String[] pckgClassName;
  
  public ApplicationChooser(Context paramContext)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    int i;
    if (!localIterator.hasNext())
    {
      this.pckgClassName = new String[localArrayList2.size()];
      i = 0;
      label62:
      if (i < localArrayList2.size()) {
        break label183;
      }
      this.pckgAppName = new String[localArrayList1.size()];
      i = 0;
    }
    for (;;)
    {
      if (i >= localArrayList1.size())
      {
        return;
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (paramContext.getPackageManager().getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
          break;
        }
        try
        {
          localArrayList1.add(paramContext.getPackageManager().getApplicationLabel(localPackageInfo.applicationInfo).toString());
          localArrayList2.add(localPackageInfo.packageName);
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localArrayList1.add(localPackageInfo.packageName);
          }
        }
        label183:
        this.pckgClassName[i] = ((String)localArrayList2.get(i));
        i += 1;
        break label62;
      }
      this.pckgAppName[i] = ((String)localArrayList1.get(i));
      i += 1;
    }
  }
}
