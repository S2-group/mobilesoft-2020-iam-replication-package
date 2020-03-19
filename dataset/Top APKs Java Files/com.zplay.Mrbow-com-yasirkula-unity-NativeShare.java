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
  private static String authority;
  
  public NativeShare() {}
  
  public static String FindMatchingTarget(Context paramContext, String paramString1, String paramString2)
  {
    List localList = paramContext.getPackageManager().getInstalledPackages(1);
    if (localList != null)
    {
      paramString1 = Pattern.compile(paramString1);
      if (paramString2.length() > 0) {
        paramContext = Pattern.compile(paramString2);
      } else {
        paramContext = null;
      }
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        paramString2 = (PackageInfo)localIterator.next();
        if (paramString1.matcher(paramString2.packageName).find())
        {
          ActivityInfo[] arrayOfActivityInfo = paramString2.activities;
          if (arrayOfActivityInfo != null)
          {
            int j = arrayOfActivityInfo.length;
            int i = 0;
            while (i < j)
            {
              localList = arrayOfActivityInfo[i];
              if ((paramContext == null) || (paramContext.matcher(localList.name).find())) {
                break label140;
              }
              i += 1;
            }
            continue;
            label140:
            paramContext = new StringBuilder();
            paramContext.append(paramString2.packageName);
            paramContext.append(">");
            paramContext.append(localList.name);
            return paramContext.toString();
          }
        }
      }
    }
    return "";
  }
  
  private static String GetAuthority(Context paramContext)
  {
    if (authority == null) {
      try
      {
        ProviderInfo[] arrayOfProviderInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 8).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if ((localProviderInfo.name.equals(UnitySSContentProvider.class.getName())) && (localProviderInfo.packageName.equals(paramContext.getPackageName())) && (localProviderInfo.authority.length() > 0))
            {
              authority = localProviderInfo.authority;
              break;
            }
            i += 1;
          }
        }
        return authority;
      }
      catch (Exception paramContext)
      {
        Log.e("Unity", "Exception:", paramContext);
      }
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
    if (paramArrayOfString1.length > 0)
    {
      int j = 0;
      paramString4 = null;
      paramString3 = paramString4;
      int i = 0;
      for (;;)
      {
        localObject2 = paramString4;
        localObject1 = paramString3;
        if (i >= paramArrayOfString1.length) {
          break label329;
        }
        if (paramArrayOfString2[i].length() > 0)
        {
          localObject1 = paramArrayOfString2[i];
        }
        else
        {
          k = paramArrayOfString1[i].lastIndexOf('.');
          if ((k < 0) || (k == paramArrayOfString1.length - 1)) {
            break;
          }
          localObject1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramArrayOfString1[i].substring(k + 1).toLowerCase(Locale.ENGLISH));
        }
        if ((localObject1 == null) || (((String)localObject1).length() == 0)) {
          break label318;
        }
        int k = ((String)localObject1).indexOf('/');
        if ((k <= 0) || (k == ((String)localObject1).length() - 1)) {
          break;
        }
        String str2 = ((String)localObject1).substring(0, k);
        String str1 = ((String)localObject1).substring(k + 1);
        if (paramString4 == null)
        {
          localObject2 = str2;
        }
        else
        {
          localObject2 = paramString4;
          if (!paramString4.equals(str2)) {
            break;
          }
        }
        if (paramString3 == null)
        {
          localObject1 = str1;
        }
        else
        {
          localObject1 = paramString3;
          if (!paramString3.equals(str1)) {
            localObject1 = "*";
          }
        }
        i += 1;
        paramString4 = (String)localObject2;
        paramString3 = (String)localObject1;
      }
      label318:
      Object localObject2 = "*";
      Object localObject1 = "*";
      label329:
      paramArrayOfString2 = new StringBuilder();
      paramArrayOfString2.append((String)localObject2);
      paramArrayOfString2.append("/");
      paramArrayOfString2.append((String)localObject1);
      paramArrayOfString2 = paramArrayOfString2.toString();
      if (paramArrayOfString1.length == 1)
      {
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.STREAM", UnitySSContentProvider.getUriForFile(paramContext, authority, new File(paramArrayOfString1[0])));
        paramArrayOfString1 = paramArrayOfString2;
      }
      else
      {
        localIntent.setAction("android.intent.action.SEND_MULTIPLE");
        paramString3 = new ArrayList(paramArrayOfString1.length);
        i = j;
        while (i < paramArrayOfString1.length)
        {
          paramString3.add(UnitySSContentProvider.getUriForFile(paramContext, authority, new File(paramArrayOfString1[i])));
          i += 1;
        }
        localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", paramString3);
        paramArrayOfString1 = paramArrayOfString2;
      }
    }
    else
    {
      paramArrayOfString1 = "text/plain";
      localIntent.setAction("android.intent.action.SEND");
    }
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
    if (paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() == 1)
    {
      paramContext.startActivity(localIntent);
      return;
    }
    paramContext.startActivity(Intent.createChooser(localIntent, paramString5));
  }
  
  public static boolean TargetExists(Context paramContext, String paramString1, String paramString2)
  {
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
          boolean bool = paramContext[i].name.equals(paramString2);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
}
