package com.aita.tracking;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.view.MenuItem;
import com.aita.c;
import com.aita.g;
import com.aita.helpers.i;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class b
  extends AsyncTask<Void, Void, Integer>
{
  private MenuItem YE;
  private Context context;
  
  public b(WearableActivity paramWearableActivity, Context paramContext, MenuItem paramMenuItem)
  {
    this.context = paramContext;
    this.YE = paramMenuItem;
  }
  
  private void nO()
  {
    Object localObject11;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    Object localObject6;
    Object localObject8;
    Object localObject10;
    Object localObject9;
    Object localObject7;
    Object localObject5;
    String str4;
    label604:
    Object localObject12;
    Object localObject13;
    Object localObject14;
    try
    {
      Object localObject1 = this.context.getPackageManager();
      String str2;
      if (localObject1 != null)
      {
        localObject11 = ((PackageManager)localObject1).getInstalledApplications(0);
        localObject2 = "0";
        localObject3 = "0";
        localObject4 = "0";
        localObject6 = "0";
        localObject8 = "0";
        localObject10 = "0";
        localObject9 = "0";
        localObject7 = "0";
        localObject5 = "0";
        str4 = "0";
        str2 = "0";
        localObject1 = "0";
        Iterator localIterator = ((List)localObject11).iterator();
        if (localIterator.hasNext())
        {
          localObject11 = (ApplicationInfo)localIterator.next();
          if (localObject11 == null) {
            break label604;
          }
          localObject11 = ((ApplicationInfo)localObject11).packageName;
          i = -1;
        }
      }
      switch (((String)localObject11).hashCode())
      {
      case -734795781: 
        if (((String)localObject11).equals("com.mobiata.flighttrack.five")) {
          i = 0;
        }
        break;
      case -2007622504: 
        if (((String)localObject11).equals("io.wifimap.wifimap")) {
          i = 1;
        }
        break;
      case -1592565600: 
        if (((String)localObject11).equals("com.mobiata.flightboard")) {
          i = 2;
        }
        break;
      case 1253813533: 
        if (((String)localObject11).equals("com.tripit")) {
          i = 3;
        }
        break;
      case -1665033987: 
        if (((String)localObject11).equals("com.tripit.paid")) {
          i = 4;
        }
        break;
      case 540848490: 
        if (((String)localObject11).equals("com.worldmate")) {
          i = 5;
        }
        break;
      case 1199242018: 
        if (((String)localObject11).equals("com.flightradar24pro")) {
          i = 6;
        }
        break;
      case -854928308: 
        if (((String)localObject11).equals("com.flightradar24premium")) {
          i = 7;
        }
        break;
      case -1478501225: 
        if (((String)localObject11).equals("com.flightradar24free")) {
          i = 8;
        }
        break;
      case 578428293: 
        if (((String)localObject11).equals("com.google.android.calendar")) {
          i = 9;
        }
        break;
      case 1036481368: 
        if (((String)localObject11).equals("com.getpebble.android")) {
          i = 10;
        }
        break;
      case 252630123: 
        if (((String)localObject11).equals("com.getpebble.android.basalt"))
        {
          i = 11;
          break;
          g.d(this.context, "option13", String.format(Locale.US, "%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", new Object[] { localObject2, localObject3, localObject4, localObject6, localObject8, localObject10, localObject9, localObject7, localObject5, str4, str2, localObject1 }));
          return;
        }
        break;
      }
    }
    catch (Exception localException1)
    {
      int i;
      try
      {
        i.logException(localException1);
        return;
      }
      catch (Exception localException2)
      {
        c.c(this.context, "wear_task_sendCrashlyticsError", localException2.getMessage() + " base error:" + localException1.getMessage());
        return;
      }
      switch (i)
      {
      default: 
        localObject11 = localObject5;
        localObject12 = localObject7;
        localObject13 = localObject9;
        localObject5 = localObject10;
        localObject14 = localObject8;
        localObject10 = localObject2;
        localObject9 = localObject3;
        localObject8 = localObject4;
        localObject7 = localObject6;
        localObject6 = localObject14;
        localObject4 = localObject13;
        localObject3 = localObject12;
        localObject2 = localObject11;
      }
    }
    for (;;)
    {
      localObject11 = localObject10;
      localObject12 = localObject9;
      localObject13 = localObject8;
      localObject14 = localObject7;
      localObject10 = localObject5;
      localObject5 = localObject2;
      localObject7 = localObject3;
      localObject9 = localObject4;
      localObject8 = localObject6;
      localObject6 = localObject14;
      localObject4 = localObject13;
      localObject3 = localObject12;
      localObject2 = localObject11;
      break;
      localObject2 = localObject5;
      localObject5 = localObject10;
      localObject10 = localObject6;
      localObject11 = localObject4;
      localObject12 = localObject3;
      localObject13 = "1";
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject6 = localObject8;
      localObject7 = localObject10;
      localObject8 = localObject11;
      localObject9 = localObject12;
      localObject10 = localObject13;
      continue;
      localObject11 = localObject2;
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject5 = localObject10;
      localObject7 = localObject6;
      localObject10 = localObject4;
      localObject12 = "1";
      localObject4 = localObject9;
      localObject6 = localObject8;
      localObject8 = localObject10;
      localObject9 = localObject12;
      localObject10 = localObject11;
      continue;
      localObject11 = localObject3;
      localObject12 = localObject2;
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject5 = localObject10;
      localObject7 = localObject6;
      localObject9 = "1";
      localObject6 = localObject8;
      localObject8 = localObject9;
      localObject9 = localObject11;
      localObject10 = localObject12;
      continue;
      localObject11 = localObject4;
      localObject12 = localObject3;
      localObject13 = localObject2;
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject6 = localObject8;
      localObject7 = "1";
      localObject2 = localObject5;
      localObject5 = localObject10;
      localObject8 = localObject11;
      localObject9 = localObject12;
      localObject10 = localObject13;
      continue;
      localObject8 = localObject6;
      localObject11 = localObject4;
      localObject12 = localObject3;
      localObject13 = localObject2;
      localObject4 = localObject9;
      localObject6 = "1";
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject5 = localObject10;
      localObject7 = localObject8;
      localObject8 = localObject11;
      localObject9 = localObject12;
      localObject10 = localObject13;
      continue;
      localObject10 = localObject6;
      localObject11 = localObject4;
      localObject12 = localObject3;
      localObject13 = localObject2;
      localObject6 = "1";
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject5 = localObject6;
      localObject6 = localObject8;
      localObject7 = localObject10;
      localObject8 = localObject11;
      localObject9 = localObject12;
      localObject10 = localObject13;
      continue;
      localObject9 = localObject6;
      localObject11 = localObject4;
      localObject12 = localObject3;
      localObject13 = localObject2;
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject4 = "1";
      localObject5 = localObject10;
      localObject6 = localObject8;
      localObject7 = localObject9;
      localObject8 = localObject11;
      localObject9 = localObject12;
      localObject10 = localObject13;
      continue;
      localObject7 = localObject6;
      localObject11 = localObject4;
      localObject12 = localObject3;
      localObject13 = localObject2;
      localObject2 = localObject5;
      localObject3 = "1";
      localObject4 = localObject9;
      localObject5 = localObject10;
      localObject6 = localObject8;
      localObject8 = localObject11;
      localObject9 = localObject12;
      localObject10 = localObject13;
      continue;
      localObject5 = localObject10;
      localObject10 = localObject6;
      localObject11 = localObject4;
      localObject12 = localObject3;
      localObject13 = localObject2;
      localObject2 = "1";
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject6 = localObject8;
      localObject7 = localObject10;
      localObject8 = localObject11;
      localObject9 = localObject12;
      localObject10 = localObject13;
      continue;
      localObject11 = localObject6;
      localObject12 = localObject4;
      localObject13 = localObject3;
      localObject14 = localObject2;
      str4 = "1";
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject5 = localObject10;
      localObject6 = localObject8;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      localObject10 = localObject14;
      continue;
      localObject11 = localObject6;
      localObject12 = localObject4;
      localObject13 = localObject3;
      localObject14 = localObject2;
      String str3 = "1";
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject5 = localObject10;
      localObject6 = localObject8;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      localObject10 = localObject14;
      continue;
      String str1 = "1";
      localObject11 = localObject6;
      localObject12 = localObject4;
      localObject13 = localObject3;
      localObject14 = localObject2;
      localObject2 = localObject5;
      localObject3 = localObject7;
      localObject4 = localObject9;
      localObject5 = localObject10;
      localObject6 = localObject8;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      localObject10 = localObject14;
    }
  }
  
  protected Integer f(Void... paramVarArgs)
  {
    nO();
    return Integer.valueOf(0);
  }
}
