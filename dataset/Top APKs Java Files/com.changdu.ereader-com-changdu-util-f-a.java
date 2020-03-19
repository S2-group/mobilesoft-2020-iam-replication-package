package com.changdu.util.f;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.changdu.ApplicationInit;
import com.changdu.bookshelf.dc.a;
import com.changdu.bookshelf.do;
import com.changdu.util.z;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class a
{
  public a() {}
  
  public static void a(Context paramContext, dc.a paramA)
  {
    a(paramContext, paramA, null);
  }
  
  public static void a(Context paramContext, dc.a paramA, Bitmap paramBitmap)
  {
    Intent localIntent = do.a(paramContext, paramA, true);
    Object localObject2;
    if (Build.VERSION.SDK_INT >= 26) {
      localObject2 = null;
    }
    Object localObject1;
    do
    {
      try
      {
        Iterator localIterator = localIntent.getExtras().keySet().iterator();
        do
        {
          localObject1 = localObject2;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject1 = (String)localIterator.next();
          localObject1 = localIntent.getExtras().get((String)localObject1);
        } while (!(localObject1 instanceof Intent));
        localObject1 = (Intent)localObject1;
      }
      catch (Throwable paramContext)
      {
        long l2;
        long l1;
        paramContext.printStackTrace();
        return;
      }
      l2 = z.O(paramA.a);
      l1 = l2;
      if (l2 == 0L) {
        l1 = System.currentTimeMillis();
      }
      a(paramContext, String.valueOf(l1), (Intent)localObject1, localIntent.getExtras().getString("android.intent.extra.shortcut.NAME"), paramBitmap);
      return;
      paramContext.sendBroadcast(localIntent);
      return;
    } while (localObject1 != null);
  }
  
  public static void a(Context paramContext, File paramFile)
  {
    a(paramContext, paramFile, null);
  }
  
  public static void a(Context paramContext, File paramFile, Bitmap paramBitmap)
  {
    Intent localIntent = do.a(paramContext, paramFile, true);
    Object localObject2;
    if (Build.VERSION.SDK_INT >= 26) {
      localObject2 = null;
    }
    Object localObject1;
    do
    {
      try
      {
        Iterator localIterator = localIntent.getExtras().keySet().iterator();
        do
        {
          localObject1 = localObject2;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject1 = (String)localIterator.next();
          localObject1 = localIntent.getExtras().get((String)localObject1);
        } while (!(localObject1 instanceof Intent));
        localObject1 = (Intent)localObject1;
      }
      catch (Throwable paramContext)
      {
        long l2;
        long l1;
        paramContext.printStackTrace();
        return;
      }
      l2 = z.O(paramFile.getAbsolutePath());
      l1 = l2;
      if (l2 == 0L) {
        l1 = System.currentTimeMillis();
      }
      a(paramContext, String.valueOf(l1), (Intent)localObject1, do.l(paramFile.getName()), paramBitmap);
      return;
      paramContext.sendBroadcast(localIntent);
      return;
    } while (localObject1 != null);
  }
  
  public static void a(Context paramContext, String paramString, Intent paramIntent)
  {
    Intent localIntent = do.a(paramContext, true);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    long l2 = z.O(paramString);
    long l1 = l2;
    if (l2 == 0L)
    {
      l1 = System.currentTimeMillis();
      z.b(paramString, l1);
    }
    paramIntent.putExtra("time", l1);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    if (Build.VERSION.SDK_INT >= 26) {
      try
      {
        a(paramContext, String.valueOf(l1), paramIntent, do.l(paramString), null);
        return;
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
    }
    paramContext.sendBroadcast(localIntent);
  }
  
  @TargetApi(26)
  private static void a(Context paramContext, String paramString1, Intent paramIntent, String paramString2, Bitmap paramBitmap)
  {
    paramString1 = new ShortcutInfo.Builder(paramContext, paramString1);
    paramIntent = paramString1.setIntent(paramIntent);
    if (paramBitmap == null) {
      paramContext = Icon.createWithResource(paramContext, 2131166145);
    } else {
      paramContext = Icon.createWithBitmap(paramBitmap);
    }
    paramIntent.setIcon(paramContext).setShortLabel(paramString2);
    ((ShortcutManager)ApplicationInit.h.getSystemService("shortcut")).requestPinShortcut(paramString1.build(), null);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(8);
    boolean bool = true;
    if (localObject != null)
    {
      ContentResolver localContentResolver = paramContext.getContentResolver();
      paramContext = null;
      Iterator localIterator = ((List)localObject).iterator();
      label148:
      do
      {
        ProviderInfo[] arrayOfProviderInfo;
        do
        {
          localObject = paramContext;
          if (!localIterator.hasNext()) {
            break;
          }
          arrayOfProviderInfo = ((PackageInfo)localIterator.next()).providers;
        } while (arrayOfProviderInfo == null);
        int j = arrayOfProviderInfo.length;
        int i = 0;
        for (;;)
        {
          localObject = paramContext;
          if (i >= j) {
            break label148;
          }
          localObject = arrayOfProviderInfo[i];
          if (("com.android.launcher.permission.READ_SETTINGS".equals(((ProviderInfo)localObject).readPermission)) || ("com.android.launcher2.permission.READ_SETTINGS".equals(((ProviderInfo)localObject).readPermission)) || ("com.android.launcher3.permission.READ_SETTINGS".equals(((ProviderInfo)localObject).readPermission))) {
            break;
          }
          i += 1;
        }
        localObject = ((ProviderInfo)localObject).authority;
        paramContext = (Context)localObject;
      } while (TextUtils.isEmpty((CharSequence)localObject));
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        paramContext = new StringBuilder();
        paramContext.append("content://");
        paramContext.append((String)localObject);
        paramContext.append("/favorites?notify=true");
        paramContext = localContentResolver.query(Uri.parse(paramContext.toString()), null, "title=?", new String[] { paramString }, null);
        if ((paramContext != null) && (paramContext.getCount() > 0))
        {
          if (paramContext.isClosed()) {
            break label255;
          }
          paramContext.close();
          return true;
        }
      }
    }
    bool = false;
    label255:
    return bool;
  }
}
