package com.gameloft.android.ANMP.GloftA9HM;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.gameloft.android.ANMP.GloftA9HM.GLUtils.SUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataSharing
{
  private static ArrayList<String> a = new ArrayList();
  private static HashMap<String, String> b = new HashMap();
  private static HashMap<String, String> c = new HashMap();
  
  public DataSharing() {}
  
  public static void AddOrUpdate(Uri paramUri, String paramString1, String paramString2)
  {
    LazyInit();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("value", paramString2);
    try
    {
      if (SUtils.getApplicationContext().getContentResolver().update(paramUri, localContentValues, "key='" + paramString1 + "'", null) < 1)
      {
        localContentValues.put("key", paramString1);
        localContentValues.put("value", paramString2);
        SUtils.getApplicationContext().getContentResolver().insert(paramUri, localContentValues);
      }
      return;
    }
    catch (Exception paramUri) {}
  }
  
  public static void LazyInit()
  {
    if (a.isEmpty())
    {
      SUtils.getApplicationContext().getPackageManager().checkPermission("glshare.permission.ACCESS_SHARED_DATA", "com.gameloft.android.ANMP.GloftA9HM");
      Object localObject1 = SUtils.getApplicationContext().getPackageManager().getInstalledPackages(8).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (((PackageInfo)localObject2).providers != null)
        {
          localObject2 = ((PackageInfo)localObject2).providers;
          int j = localObject2.length;
          i = 0;
          while (i < j)
          {
            Object localObject3 = localObject2[i];
            if ((localObject3 != null) && (localObject3.authority != null) && (localObject3.authority.contains("com.gameloft"))) {
              a.add(localObject3.authority);
            }
            i += 1;
          }
        }
      }
      int i = 0;
      if (i < a.size())
      {
        localObject1 = Uri.parse("content://" + (String)a.get(i) + "/key/");
        if (((String)a.get(i)).contains(SUtils.getApplicationContext().getPackageName())) {
          fillDBArray((Uri)localObject1, true);
        }
        for (;;)
        {
          i += 1;
          break;
          fillDBArray((Uri)localObject1, false);
        }
      }
    }
  }
  
  public static void deleteSharedValue(String paramString)
  {
    
    if ((!b.containsKey(paramString)) && (!c.containsKey(paramString))) {
      return;
    }
    c.remove(paramString);
    b.remove(paramString);
    new c(paramString).start();
  }
  
  private static void fillDBArray(Uri paramUri, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      paramUri = SUtils.getApplicationContext().getContentResolver().query(paramUri, new String[] { "key", "value" }, null, null, null);
      if (paramUri != null)
      {
        paramUri.moveToFirst();
        int i = 0;
        for (;;)
        {
          if (i < paramUri.getCount())
          {
            if (paramBoolean) {
              c.put(paramUri.getString(0), paramUri.getString(1));
            }
            while (!paramUri.isLast())
            {
              paramUri.moveToNext();
              break;
              b.put(paramUri.getString(0), paramUri.getString(1));
            }
          }
          paramUri.close();
          break;
          i += 1;
        }
      }
      return;
    }
    catch (Exception paramUri)
    {
      if (!paramBoolean) {}
    }
  }
  
  public static String getSharedValue(String paramString)
  {
    
    Object localObject;
    if (c.containsKey(paramString))
    {
      localObject = (String)c.get(paramString);
      paramString = (String)localObject;
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        paramString = "";
      }
      return paramString;
    }
    if (b.containsKey(paramString))
    {
      String str = (String)b.get(paramString);
      localObject = str;
      if (TextUtils.isEmpty(str)) {
        localObject = "";
      }
      c.put(paramString, localObject);
      new a(paramString, (String)localObject).start();
      return localObject;
    }
    return "";
  }
  
  public static boolean isSharedValue(String paramString)
  {
    LazyInit();
    paramString = getSharedValue(paramString);
    return (paramString != null) && (!paramString.equals(""));
  }
  
  public static void setSharedValue(String paramString1, String paramString2)
  {
    int k = 0;
    LazyInit();
    if ((b.containsKey(paramString1)) && (((String)b.get(paramString1)).equals(paramString2))) {}
    for (int i = 1;; i = 0)
    {
      int j = k;
      if (c.containsKey(paramString1))
      {
        j = k;
        if (((String)c.get(paramString1)).equals(paramString2)) {
          j = 1;
        }
      }
      if ((j != 0) && (i != 0)) {
        return;
      }
      c.put(paramString1, paramString2);
      b.put(paramString1, paramString2);
      new b(paramString1, paramString2).start();
      return;
    }
  }
}
