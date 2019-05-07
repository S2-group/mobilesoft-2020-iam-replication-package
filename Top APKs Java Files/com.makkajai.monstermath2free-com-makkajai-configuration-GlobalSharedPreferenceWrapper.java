package com.makkajai.configuration;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class GlobalSharedPreferenceWrapper
{
  private static final String PACKAGE_INITIALS = "com.makkajai";
  private static final String TAG = "com.makkajai.tag";
  
  public GlobalSharedPreferenceWrapper() {}
  
  private static String formatKey(String paramString)
  {
    return paramString.replace("com.makkajai", "").replaceAll("\\.", "/");
  }
  
  public static String getItemFromGlobalStore(Context paramContext, String paramString)
  {
    if (paramContext == null)
    {
      Log.d("com.makkajai.tag", "Context is null returning and not doing anything!");
      localObject = "";
      return localObject;
    }
    Log.d("com.makkajai.tag", "getting value from globaldb with!: key before formatting::" + paramString);
    String str = formatKey(paramString);
    Log.d("com.makkajai.tag", "getting value from globaldb with!: key::" + str);
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramString = "";
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      localObject = paramString;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (ApplicationInfo)localIterator.next();
      if ((((ApplicationInfo)localObject).packageName.contains("com.makkajai")) && (!paramContext.getApplicationContext().getPackageName().equals(((ApplicationInfo)localObject).packageName))) {
        try
        {
          Cursor localCursor = paramContext.getContentResolver().query(Uri.parse("content://" + ((ApplicationInfo)localObject).packageName + str), null, null, null, null);
          Log.d("com.makkajai.tag", localCursor.getCount() + "value received " + localCursor.getColumnName(0) + " :: " + localCursor.getColumnName(1) + " from the package " + ((ApplicationInfo)localObject).packageName);
          localObject = "";
          if (localCursor.moveToFirst()) {
            do
            {
              localObject = localCursor.getString(localCursor.getColumnIndex("value"));
              Log.d("com.makkajai.tag", "email: " + (String)localObject);
            } while (localCursor.moveToNext());
          }
          boolean bool = ((String)localObject).isEmpty();
          if (!bool) {
            paramString = (String)localObject;
          }
        }
        catch (Exception localException)
        {
          Log.d("com.makkajai.tag", "Error thrown while setting value globally " + localException.getMessage());
        }
      }
    }
  }
  
  public static void setItemInGlobalStore(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null) {
      Log.d("com.makkajai.tag", "Context is null returning and not doing anything!");
    }
    for (;;)
    {
      return;
      Log.d("com.makkajai.tag", "storing in globaldb with values!: key::" + paramString1 + " value::" + paramString2);
      paramString1 = formatKey(paramString1);
      Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.packageName.contains("com.makkajai"))
        {
          Log.d("com.makkajai.tag", "Installed package :" + localApplicationInfo.packageName);
          try
          {
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("value", paramString2);
            paramContext.getContentResolver().update(Uri.parse("content://" + localApplicationInfo.packageName + paramString1), localContentValues, null, null);
          }
          catch (Exception localException)
          {
            Log.d("com.makkajai.tag", "Error thrown while setting value globally " + localException.getMessage());
          }
        }
      }
    }
  }
}
