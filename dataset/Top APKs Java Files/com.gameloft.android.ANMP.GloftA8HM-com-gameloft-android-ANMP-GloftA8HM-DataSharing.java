package com.gameloft.android.ANMP.GloftA8HM;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.gameloft.android.ANMP.GloftA8HM.GLUtils.SUtils;
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
      ContentResolver localContentResolver = SUtils.getApplicationContext().getContentResolver();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("key='");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("'");
      if (localContentResolver.update(paramUri, localContentValues, localStringBuilder.toString(), null) < 1)
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
      SUtils.getApplicationContext().getPackageManager().checkPermission("glshare.permission.ACCESS_SHARED_DATA", "com.gameloft.android.ANMP.GloftA8HM");
      Object localObject1 = SUtils.getApplicationContext().getPackageManager().getInstalledPackages(8).iterator();
      for (;;)
      {
        boolean bool = ((Iterator)localObject1).hasNext();
        i = 0;
        if (!bool) {
          break;
        }
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (((PackageInfo)localObject2).providers != null)
        {
          localObject2 = ((PackageInfo)localObject2).providers;
          int j = localObject2.length;
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
      while (i < a.size())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("content://");
        ((StringBuilder)localObject1).append((String)a.get(i));
        ((StringBuilder)localObject1).append("/key/");
        localObject1 = Uri.parse(((StringBuilder)localObject1).toString());
        if (((String)a.get(i)).contains(SUtils.getApplicationContext().getPackageName())) {
          fillDBArray((Uri)localObject1, true);
        } else {
          fillDBArray((Uri)localObject1, false);
        }
        i += 1;
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
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          int i;
          Object localObject1;
          synchronized (DataSharing.a)
          {
            int j = DataSharing.a.size();
            i = 0;
            if (i < j)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("content://");
              ((StringBuilder)localObject1).append((String)DataSharing.a.get(i));
              ((StringBuilder)localObject1).append("/key/");
              localObject1 = Uri.parse(((StringBuilder)localObject1).toString());
            }
          }
          try
          {
            ContentResolver localContentResolver = SUtils.getApplicationContext().getContentResolver();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("key='");
            localStringBuilder.append(this.a);
            localStringBuilder.append("'");
            localContentResolver.delete((Uri)localObject1, localStringBuilder.toString(), null);
            i += 1;
            continue;
            return;
            localObject2 = finally;
            throw localObject2;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
        }
      }
    }.start();
  }
  
  private static void fillDBArray(Uri paramUri, boolean paramBoolean)
  {
    for (;;)
    {
      int i;
      try
      {
        paramUri = SUtils.getApplicationContext().getContentResolver().query(paramUri, new String[] { "key", "value" }, null, null, null);
        if (paramUri != null)
        {
          paramUri.moveToFirst();
          i = 0;
          if (i < paramUri.getCount())
          {
            if (paramBoolean) {
              c.put(paramUri.getString(0), paramUri.getString(1));
            } else {
              b.put(paramUri.getString(0), paramUri.getString(1));
            }
            if (!paramUri.isLast()) {
              paramUri.moveToNext();
            }
          }
          else
          {
            paramUri.close();
          }
        }
        else
        {
          return;
        }
      }
      catch (Exception paramUri)
      {
        return;
      }
      i += 1;
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
      new Thread()
      {
        public void run()
        {
          synchronized (DataSharing.a)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("content://");
            localStringBuilder.append(SUtils.getApplicationContext().getPackageName());
            localStringBuilder.append(".KeyProvider/key/");
            DataSharing.AddOrUpdate(Uri.parse(localStringBuilder.toString()), this.a, this.b);
            return;
          }
        }
      }.start();
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
  
  public static void setSharedValue(String paramString1, final String paramString2)
  {
    LazyInit();
    boolean bool = b.containsKey(paramString1);
    int j = 1;
    int i;
    if ((bool) && (((String)b.get(paramString1)).equals(paramString2))) {
      i = 1;
    } else {
      i = 0;
    }
    if ((!c.containsKey(paramString1)) || (!((String)c.get(paramString1)).equals(paramString2))) {
      j = 0;
    }
    if ((j != 0) && (i != 0)) {
      return;
    }
    c.put(paramString1, paramString2);
    b.put(paramString1, paramString2);
    new Thread()
    {
      public void run()
      {
        synchronized (DataSharing.a)
        {
          int j = DataSharing.a.size();
          int i = 0;
          while (i < j)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("content://");
            localStringBuilder.append((String)DataSharing.a.get(i));
            localStringBuilder.append("/key/");
            DataSharing.AddOrUpdate(Uri.parse(localStringBuilder.toString()), this.a, paramString2);
            i += 1;
          }
          return;
        }
      }
    }.start();
  }
}
