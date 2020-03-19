package com.yixia.zi.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class IntentHelper
{
  public static final String MEDIA_PATTERN = "(http[s]?://)+([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?";
  private static final Pattern mMediaPattern = Pattern.compile("(http[s]?://)+([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?");
  
  public IntentHelper() {}
  
  public static boolean existPackage(Context paramContext, String paramString)
  {
    if (!StringUtils.isBlank(paramString)) {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    }
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
    } while (!paramString.equals(((PackageInfo)paramContext.next()).packageName));
    return true;
  }
  
  public static Uri getIntentUri(Intent paramIntent)
  {
    Object localObject = null;
    Uri localUri;
    String str1;
    String str2;
    if (paramIntent != null)
    {
      localUri = paramIntent.getData();
      localObject = localUri;
      if (localUri == null)
      {
        str1 = paramIntent.getType();
        str2 = paramIntent.getStringExtra("android.intent.extra.TEXT");
        if (StringUtils.isBlank(str2)) {
          break label92;
        }
        if ((!"text/plain".equals(str1)) || (str2 == null)) {
          break label60;
        }
        localObject = getTextUri(str2);
      }
    }
    label60:
    label92:
    do
    {
      do
      {
        do
        {
          return localObject;
          localObject = localUri;
        } while (!"text/html".equals(str1));
        localObject = localUri;
      } while (str2 == null);
      return getTextUri(Html.fromHtml(str2).toString());
      paramIntent = paramIntent.getParcelableExtra("android.intent.extra.STREAM");
      localObject = localUri;
    } while (paramIntent == null);
    return (Uri)paramIntent;
  }
  
  private static Uri getTextUri(String paramString)
  {
    paramString = mMediaPattern.matcher(paramString);
    if (paramString.find())
    {
      paramString = paramString.group();
      if (!StringUtils.isBlank(paramString)) {
        return Uri.parse(paramString);
      }
    }
    return null;
  }
  
  public static void startApkActivity(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = ((PackageManager)localObject).getPackageInfo(paramString, 0);
      Intent localIntent = new Intent("android.intent.action.MAIN", null);
      localIntent.setPackage(localPackageInfo.packageName);
      localObject = (ResolveInfo)((PackageManager)localObject).queryIntentActivities(localIntent, 0).iterator().next();
      if (localObject != null)
      {
        localIntent.setComponent(new ComponentName(paramString, ((ResolveInfo)localObject).activityInfo.name));
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("startActivity", paramContext);
    }
  }
}
