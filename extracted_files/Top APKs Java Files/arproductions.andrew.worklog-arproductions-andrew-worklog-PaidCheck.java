package arproductions.andrew.worklog;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.TransactionTooLargeException;
import java.util.List;

class PaidCheck
{
  static boolean paidCheck(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(128);
        i = 0;
        if (i >= paramContext.size()) {
          break;
        }
        boolean bool = ((ApplicationInfo)paramContext.get(i)).packageName.equals("arproductions.andrew.worklogkey");
        if (!bool) {
          continue;
        }
      }
      catch (Exception paramContext)
      {
        int i;
        if ((paramContext instanceof TransactionTooLargeException)) {
          continue;
        }
      }
      return true;
      i += 1;
    }
    return false;
  }
}
