package com.yasirkula.unity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class NativeShare
{
  private static String authority = null;
  
  public NativeShare() {}
  
  public static void Share(Context paramContext, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2, String paramString3)
  {
    Object localObject1;
    Object localObject2;
    int j;
    int i;
    String str1;
    if ((paramArrayOfString1.length > 0) && (authority == null))
    {
      localObject1 = paramContext.getPackageManager().getInstalledPackages(8).iterator();
      label158:
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (((PackageInfo)localObject2).packageName.equals(paramContext.getPackageName()))
        {
          localObject2 = ((PackageInfo)localObject2).providers;
          if (localObject2 != null)
          {
            j = localObject2.length;
            i = 0;
            for (;;)
            {
              if (i >= j) {
                break label158;
              }
              str1 = localObject2[i];
              if ((str1.name.equals(UnitySSContentProvider.class.getName())) && (str1.packageName.equals(paramContext.getPackageName())) && (str1.authority.length() > 0))
              {
                authority = str1.authority;
                break;
              }
              i += 1;
            }
          }
        }
      }
      if (authority == null)
      {
        Log.e("Unity", "Can't file ContentProvider, share not possible!");
        return;
      }
    }
    Intent localIntent = new Intent();
    if (paramString1.length() > 0) {
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    }
    if (paramString2.length() > 0) {
      localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    }
    if (paramArrayOfString1.length > 0)
    {
      localObject2 = null;
      localObject1 = null;
      i = 0;
      paramString1 = (String)localObject1;
      paramString2 = (String)localObject2;
      if (i < paramArrayOfString1.length)
      {
        if (paramArrayOfString2[i].length() > 0)
        {
          paramString1 = paramArrayOfString2[i];
          label261:
          if ((paramString1 != null) && (paramString1.length() != 0)) {
            break label451;
          }
          paramString1 = "*";
          paramString2 = "*";
        }
      }
      else
      {
        label279:
        paramArrayOfString2 = paramString2 + "/" + paramString1;
        if (paramArrayOfString1.length != 1) {
          break label580;
        }
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.STREAM", UnitySSContentProvider.getUriForFile(paramContext, authority, new File(paramArrayOfString1[0])));
        paramArrayOfString1 = paramArrayOfString2;
      }
    }
    for (;;)
    {
      if (paramString3.length() > 0) {
        localIntent.putExtra("android.intent.extra.TITLE", paramString3);
      }
      localIntent.setType(paramArrayOfString1);
      localIntent.setFlags(1);
      paramContext.startActivity(Intent.createChooser(localIntent, paramString3));
      return;
      j = paramArrayOfString1[i].lastIndexOf('.');
      if ((j < 0) || (j == paramArrayOfString1.length - 1))
      {
        paramString1 = "*";
        paramString2 = "*";
        break label279;
      }
      paramString1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramArrayOfString1[i].substring(j + 1).toLowerCase(Locale.ENGLISH));
      break label261;
      label451:
      j = paramString1.indexOf('/');
      if ((j <= 0) || (j == paramString1.length() - 1))
      {
        paramString1 = "*";
        paramString2 = "*";
        break label279;
      }
      String str2 = paramString1.substring(0, j);
      str1 = paramString1.substring(j + 1);
      if (localObject2 == null)
      {
        paramString2 = str2;
        label513:
        if (localObject1 != null) {
          break label561;
        }
        paramString1 = str1;
      }
      for (;;)
      {
        i += 1;
        localObject1 = paramString1;
        localObject2 = paramString2;
        break;
        paramString2 = (String)localObject2;
        if (((String)localObject2).equals(str2)) {
          break label513;
        }
        paramString1 = "*";
        paramString2 = "*";
        break label279;
        label561:
        paramString1 = (String)localObject1;
        if (!((String)localObject1).equals(str1)) {
          paramString1 = "*";
        }
      }
      label580:
      localIntent.setAction("android.intent.action.SEND_MULTIPLE");
      paramString1 = new ArrayList(paramArrayOfString1.length);
      i = 0;
      while (i < paramArrayOfString1.length)
      {
        paramString1.add(UnitySSContentProvider.getUriForFile(paramContext, authority, new File(paramArrayOfString1[i])));
        i += 1;
      }
      localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", paramString1);
      paramArrayOfString1 = paramArrayOfString2;
      continue;
      paramArrayOfString1 = "text/plain";
      localIntent.setAction("android.intent.action.SEND");
    }
  }
}
