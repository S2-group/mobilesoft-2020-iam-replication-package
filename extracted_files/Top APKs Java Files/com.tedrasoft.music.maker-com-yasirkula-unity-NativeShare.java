package com.yasirkula.unity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NativeShare
{
  private static String authority = null;
  
  public NativeShare() {}
  
  public static String FindMatchingTarget(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(1);
    if (localObject != null)
    {
      paramString1 = Pattern.compile(paramString1);
      if (paramString2.length() > 0)
      {
        paramContext = Pattern.compile(paramString2);
        paramString2 = ((List)localObject).iterator();
      }
      label167:
      for (;;)
      {
        if (!paramString2.hasNext()) {
          break label169;
        }
        localObject = (PackageInfo)paramString2.next();
        if (paramString1.matcher(((PackageInfo)localObject).packageName).find())
        {
          ActivityInfo[] arrayOfActivityInfo = ((PackageInfo)localObject).activities;
          if (arrayOfActivityInfo != null)
          {
            int j = arrayOfActivityInfo.length;
            int i = 0;
            for (;;)
            {
              if (i >= j) {
                break label167;
              }
              ActivityInfo localActivityInfo = arrayOfActivityInfo[i];
              if ((paramContext == null) || (paramContext.matcher(localActivityInfo.name).find()))
              {
                return ((PackageInfo)localObject).packageName + ">" + localActivityInfo.name;
                paramContext = null;
                break;
              }
              i += 1;
            }
          }
        }
      }
    }
    label169:
    return "";
  }
  
  private static String GetAuthority(Context paramContext)
  {
    if (authority == null) {}
    for (;;)
    {
      try
      {
        ProviderInfo[] arrayOfProviderInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 8).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          i = 0;
          if (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if ((!localProviderInfo.name.equals(UnitySSContentProvider.class.getName())) || (!localProviderInfo.packageName.equals(paramContext.getPackageName())) || (localProviderInfo.authority.length() <= 0)) {
              continue;
            }
            authority = localProviderInfo.authority;
          }
        }
      }
      catch (Exception paramContext)
      {
        int i;
        Log.e("Unity", "Exception:", paramContext);
        continue;
      }
      return authority;
      i += 1;
    }
  }
  
  public static void Share(Context paramContext, String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5)
  {
    if ((paramArrayOfString1.length > 0) && (GetAuthority(paramContext) == null))
    {
      Log.e("Unity", "Can't find ContentProvider, share not possible!");
      return;
    }
    Intent localIntent = new Intent();
    if (paramString3.length() > 0) {
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString3);
    }
    if (paramString4.length() > 0) {
      localIntent.putExtra("android.intent.extra.TEXT", paramString4);
    }
    String str2;
    String str1;
    int i;
    if (paramArrayOfString1.length > 0)
    {
      str2 = null;
      str1 = null;
      i = 0;
      paramString3 = str1;
      paramString4 = str2;
      if (i < paramArrayOfString1.length)
      {
        if (paramArrayOfString2[i].length() > 0)
        {
          paramString3 = paramArrayOfString2[i];
          label113:
          if ((paramString3 != null) && (paramString3.length() != 0)) {
            break label355;
          }
          paramString3 = "*";
          paramString4 = "*";
        }
      }
      else
      {
        label134:
        paramArrayOfString2 = paramString4 + "/" + paramString3;
        if (paramArrayOfString1.length != 1) {
          break label494;
        }
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.STREAM", UnitySSContentProvider.getUriForFile(paramContext, authority, new File(paramArrayOfString1[0])));
        paramArrayOfString1 = paramArrayOfString2;
      }
    }
    for (;;)
    {
      if (paramString5.length() > 0) {
        localIntent.putExtra("android.intent.extra.TITLE", paramString5);
      }
      localIntent.setType(paramArrayOfString1);
      localIntent.setFlags(1);
      if (paramString1.length() > 0)
      {
        localIntent.setPackage(paramString1);
        if (paramString2.length() > 0) {
          localIntent.setClassName(paramString1, paramString2);
        }
      }
      if (paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() != 1) {
        break label587;
      }
      paramContext.startActivity(localIntent);
      return;
      int j = paramArrayOfString1[i].lastIndexOf('.');
      if ((j < 0) || (j == paramArrayOfString1.length - 1))
      {
        paramString3 = "*";
        paramString4 = "*";
        break label134;
      }
      paramString3 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramArrayOfString1[i].substring(j + 1).toLowerCase(Locale.ENGLISH));
      break label113;
      label355:
      j = paramString3.indexOf('/');
      if ((j <= 0) || (j == paramString3.length() - 1))
      {
        paramString3 = "*";
        paramString4 = "*";
        break label134;
      }
      String str4 = paramString3.substring(0, j);
      String str3 = paramString3.substring(j + 1);
      if (str2 == null)
      {
        paramString4 = str4;
        label422:
        if (str1 != null) {
          break label473;
        }
        paramString3 = str3;
      }
      for (;;)
      {
        i += 1;
        str1 = paramString3;
        str2 = paramString4;
        break;
        paramString4 = str2;
        if (str2.equals(str4)) {
          break label422;
        }
        paramString3 = "*";
        paramString4 = "*";
        break label134;
        label473:
        paramString3 = str1;
        if (!str1.equals(str3)) {
          paramString3 = "*";
        }
      }
      label494:
      localIntent.setAction("android.intent.action.SEND_MULTIPLE");
      paramString3 = new ArrayList(paramArrayOfString1.length);
      i = 0;
      while (i < paramArrayOfString1.length)
      {
        paramString3.add(UnitySSContentProvider.getUriForFile(paramContext, authority, new File(paramArrayOfString1[i])));
        i += 1;
      }
      localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", paramString3);
      paramArrayOfString1 = paramArrayOfString2;
      continue;
      paramArrayOfString1 = "text/plain";
      localIntent.setAction("android.intent.action.SEND");
    }
    label587:
    paramContext.startActivity(Intent.createChooser(localIntent, paramString5));
  }
  
  public static boolean TargetExists(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = true;
    boolean bool1;
    try
    {
      if (paramString2.length() == 0)
      {
        paramContext.getPackageManager().getPackageInfo(paramString1, 0);
        return true;
      }
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString1, 1).activities;
      if (paramContext != null)
      {
        int j = paramContext.length;
        int i = 0;
        while (i < j)
        {
          boolean bool3 = paramContext[i].name.equals(paramString2);
          bool1 = bool2;
          if (bool3) {
            break label85;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      bool1 = false;
    }
    label85:
    return bool1;
  }
}
