package com.microsoft.office.docsui.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.microsoft.applauncher.AppInfo;
import com.microsoft.intune.mam.client.content.pm.MAMPackageManagement;
import com.microsoft.office.appidentifier.APKIdentifier;
import com.microsoft.office.officehub.OfficeFileContentProvider;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class QuickReplyToOutlookHelper
{
  private static final String OUTLOOK_REPLY_ALL = "replyAll";
  private static final String OUTLOOK_SEND_TYPE = "sendType";
  private static final int OUTLOOK_SEND_TYPE_VALUE = 2;
  
  public QuickReplyToOutlookHelper() {}
  
  public static AppInfo GetOutlookAppInfo(Context paramContext)
  {
    if (APKIdentifier.a()) {
      return new AppInfo(paramContext, "Reply To Outlook", "com.microsoft.office.outlook");
    }
    Iterator localIterator = MAMPackageManagement.getInstalledPackages(paramContext.getPackageManager(), 0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.startsWith("com.microsoft.office.outlook"))
      {
        Log.i("QuickReplyToOutlookHelper", "Sending QuickReply intent to package " + localPackageInfo.packageName);
        return new AppInfo(paramContext, "Reply To Outlook", localPackageInfo.packageName);
      }
    }
    return new AppInfo(paramContext, "Reply To Outlook", "com.microsoft.office.outlook");
  }
  
  public static Intent PrepareOutlookIntent(String paramString1, String paramString2)
  {
    paramString1 = OfficeFileContentProvider.a(UUID.randomUUID().toString(), paramString1, paramString2);
    paramString1.putExtra("replyAll", true);
    paramString1.putExtra("sendType", 2);
    paramString1.putExtra("com.microsoft.office.outlook.EXTRA_QUICK_REPLY_TOKEN", Utils.GetCurrentQuickReplyToken());
    return paramString1;
  }
}
