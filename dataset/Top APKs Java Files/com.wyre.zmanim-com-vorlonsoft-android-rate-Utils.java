package com.vorlonsoft.android.rate;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class Utils
{
  private Utils()
  {
    throw new AssertionError();
  }
  
  @SuppressLint({"ObsoleteSdkInt"})
  @Nullable
  static AlertDialog.Builder getDialogBuilder(@NonNull Context paramContext, int paramInt)
  {
    if (paramContext == null)
    {
      Log.i("ANDROIDRATE", "Failed to create AlertDialog.Builder");
      return null;
    }
    if (Build.VERSION.SDK_INT < 11) {
      return new AlertDialog.Builder(paramContext);
    }
    int i = paramInt;
    if (paramInt == 0) {
      i = getDialogTheme();
    }
    return new AlertDialog.Builder(paramContext, i);
  }
  
  private static int getDialogTheme()
  {
    if (isLollipop()) {
      return R.style.CustomLollipopDialogStyle;
    }
    return 0;
  }
  
  private static boolean isLollipop()
  {
    return (Build.VERSION.SDK_INT == 21) || (Build.VERSION.SDK_INT == 22);
  }
  
  @Nullable
  static String[] isPackagesExists(@NonNull Context paramContext, @NonNull String[] paramArrayOfString)
  {
    Object localObject2 = new String[0];
    if (paramContext == null)
    {
      Log.i("ANDROIDRATE", "Failed to get installed applications");
      localObject1 = null;
    }
    do
    {
      do
      {
        do
        {
          return localObject1;
          if (paramArrayOfString == null)
          {
            Log.i("ANDROIDRATE", "Null pointer to an array of target packages");
            return null;
          }
          localObject1 = localObject2;
        } while (paramArrayOfString.length == 0);
        paramContext = paramContext.getPackageManager().getInstalledApplications(0);
        if (paramArrayOfString.length != 1) {
          break;
        }
        localObject1 = localObject2;
      } while (paramArrayOfString[0] == null);
      localObject1 = localObject2;
    } while (paramArrayOfString[0].hashCode() == "".hashCode());
    paramContext = paramContext.iterator();
    do
    {
      localObject1 = localObject2;
      if (!paramContext.hasNext()) {
        break;
      }
      localObject1 = (ApplicationInfo)paramContext.next();
    } while (!paramArrayOfString[0].equals(((ApplicationInfo)localObject1).packageName));
    return new String[] { paramArrayOfString[0] };
    Object localObject1 = new ArrayList();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localObject2 = paramArrayOfString[i];
      if ((localObject2 != null) && (((String)localObject2).hashCode() != "".hashCode()))
      {
        Iterator localIterator = paramContext.iterator();
        while (localIterator.hasNext()) {
          if (((String)localObject2).equals(((ApplicationInfo)localIterator.next()).packageName)) {
            ((ArrayList)localObject1).add(localObject2);
          }
        }
      }
      i += 1;
    }
    return (String[])((ArrayList)localObject1).toArray(new String[0]);
  }
}
