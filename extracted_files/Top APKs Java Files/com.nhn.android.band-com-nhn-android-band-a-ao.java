package com.nhn.android.band.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v4.app.ShareCompat.IntentBuilder;
import android.util.Base64;
import com.nhn.android.band.base.BandApplication;
import com.nhn.android.band.customview.customdialog.g;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.a.c.e;

public class ao
{
  private static final ag a = ag.getLogger("PackageUtility");
  
  private static void a(Context paramContext, String paramString1, String paramString2)
  {
    if (as.isNullOrEmpty(paramString2)) {
      a(paramString1);
    }
    do
    {
      return;
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      paramString1.addFlags(268435456);
    } while (!(paramContext instanceof Activity));
    ((Activity)paramContext).startActivity(paramString1);
  }
  
  private static void a(String paramString)
  {
    try
    {
      paramString = BandApplication.getCurrentApplication().getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.addFlags(268435456);
      BandApplication.getCurrentApplication().startActivity(paramString);
      return;
    }
    catch (Exception paramString) {}
  }
  
  private static void b(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      ((Activity)paramContext).finish();
    }
  }
  
  public static String getPackageHash(String paramString)
  {
    try
    {
      Object localObject = BandApplication.getCurrentApplication().getPackageManager().getPackageInfo(paramString, 64).signatures;
      if (localObject.length < 0)
      {
        localObject = localObject[0];
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
        localMessageDigest.update(((Signature)localObject).toByteArray());
        localObject = Base64.encodeToString(localMessageDigest.digest(), 0);
        a.d("getPackageHash() packageName(%s), hash(%s)", new Object[] { paramString, localObject });
        return localObject;
      }
    }
    catch (Exception paramString)
    {
      a.e(paramString);
    }
    return null;
  }
  
  public static String getVersionName(String paramString)
  {
    try
    {
      paramString = BandApplication.getCurrentApplication().getPackageManager().getPackageInfo(paramString, 0).versionName;
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static void getfile(Activity paramActivity, String paramString, int paramInt)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      String str = "file/*, image/*, video/*";
      if (as.equalsIgnoreCase(q.getRegionCode(), Locale.KOREA.getCountry())) {}
      for (;;)
      {
        localIntent.setType(str);
        localIntent.setPackage(paramString);
        paramActivity.startActivityForResult(localIntent, paramInt);
        return;
        str = "file/*, image/*, audio/* ,video/*";
      }
      return;
    }
    catch (Exception paramActivity)
    {
      a.e(paramActivity);
    }
  }
  
  public static void install(Context paramContext, String paramString1, String paramString2)
  {
    String str = paramString2;
    if (as.isNullOrEmpty(paramString2)) {
      str = "market://details?id=" + paramString1;
    }
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(str));
    paramString1.addFlags(268435456);
    paramContext.startActivity(paramString1);
  }
  
  public static void installAndLaunch(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (isPackageInstalled(paramString1))
    {
      a(paramContext, paramString1, paramString3);
      if ((paramBoolean1) && ((paramContext instanceof Activity))) {
        ((Activity)paramContext).finish();
      }
    }
    do
    {
      return;
      Object localObject = new ArrayList();
      if (as.isNotNullOrEmpty(paramString4)) {
        localObject = Arrays.asList(e.split(paramString4, ','));
      }
      paramString4 = ((List)localObject).iterator();
      do
      {
        if (!paramString4.hasNext()) {
          break;
        }
        localObject = (String)paramString4.next();
      } while (!isPackageInstalled((String)localObject));
      a(paramContext, (String)localObject, paramString3);
    } while ((!paramBoolean1) || (!(paramContext instanceof Activity)));
    ((Activity)paramContext).finish();
    return;
    installPackage(paramContext, paramString1, paramString2, paramBoolean1, paramBoolean2);
  }
  
  public static void installPackage(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!paramBoolean2)
    {
      install(paramContext, paramString1, paramString2);
      if (paramBoolean1) {
        b(paramContext);
      }
      return;
    }
    new g(paramContext).title(2131232021).content(2131232721).positiveText(2131232903).negativeText(2131232009).callback(new ap(paramContext, paramString1, paramString2, paramBoolean1)).show();
  }
  
  public static boolean isExistFileOpenableMimetype(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.parse("file://"), paramString);
    return paramContext.getPackageManager().queryIntentActivities(localIntent, 128).size() > 0;
  }
  
  public static boolean isLineInstalled()
  {
    return isPackageInstalled("jp.naver.line.android");
  }
  
  public static boolean isLinePlayInstalled()
  {
    return isPackageInstalled("jp.naver.lineplay.android");
  }
  
  public static boolean isNaverAddressAppInstalled()
  {
    return isPackageInstalled("com.nhn.android.addressbookbackup");
  }
  
  public static boolean isNaverCalendarAppInstalled()
  {
    return isPackageInstalled("com.nhn.android.calendar");
  }
  
  public static boolean isNaverInstalled()
  {
    return isPackageInstalled("com.nhn.android.search");
  }
  
  public static boolean isOpenablePackage(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setPackage(paramString);
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 128);
    return (paramContext != null) && (paramContext.size() > 0);
  }
  
  public static boolean isPackageInstalled(String paramString)
  {
    try
    {
      BandApplication.getCurrentApplication().getPackageManager().getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isPackageInstalled(String paramString, int paramInt)
  {
    try
    {
      paramString = BandApplication.getCurrentApplication().getPackageManager().getPackageInfo(paramString, 1);
      a.d("packageInfo.versionCode : " + paramString.versionCode, new Object[0]);
      int i = paramString.versionCode;
      return i >= paramInt;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isPackageInstalled(List<String> paramList)
  {
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        if (isPackageInstalled((String)paramList.next())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean isTargetAppInstalled(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equals(paramString1)) && ((paramString2 == null) || (localPackageInfo.versionName.compareTo(paramString2) >= 0))) {
        return true;
      }
    }
    return false;
  }
  
  public static void sendfile(Activity paramActivity, String paramString1, File paramFile, String paramString2)
  {
    try
    {
      Uri localUri = Uri.fromFile(paramFile);
      paramActivity = ShareCompat.IntentBuilder.from(paramActivity).setText(paramFile.getName()).setType(paramString2).setStream(localUri).getIntent().addFlags(268435456).setPackage(paramString1);
      BandApplication.getCurrentApplication().startActivity(paramActivity);
      return;
    }
    catch (Exception paramActivity)
    {
      a.e(paramActivity);
    }
  }
}
