package com.microsoft.office.BackgroundTasks;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.microsoft.intune.mam.client.content.pm.MAMPackageManagement;
import com.microsoft.office.loggingapi.Logging;
import com.microsoft.office.loggingapi.Severity;
import com.microsoft.office.loggingapi.StructuredObject;
import com.microsoft.office.loggingapi.StructuredString;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class c
{
  public static void a(Context paramContext)
  {
    try
    {
      Object localObject = MAMPackageManagement.getInstalledApplications(paramContext.getPackageManager(), 128);
      paramContext = Pattern.compile("^com\\.android\\..*");
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if (!paramContext.matcher(localApplicationInfo.packageName).matches()) {
          Logging.a(20471947L, 1838, Severity.Verbose, "Other App Info", new StructuredObject[] { new StructuredString("PackageName", localApplicationInfo.packageName) });
        }
      }
      return;
    }
    catch (Exception paramContext)
    {
      Logging.a(20471948L, 1838, Severity.Error, "Exception in LogTelemetryInfo", new StructuredObject[] { new StructuredString("Exception", paramContext.toString()) });
      Logging.a(20471949L, 1838, Severity.Info, "Ending LogTelemetryInfo", new StructuredObject[0]);
    }
  }
}
