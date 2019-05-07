package com.optimizer.test.module.gameboost;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.ihs.commons.e.i;
import com.optimizer.test.f.r;
import com.optimizer.test.f.u;
import com.optimizer.test.f.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class b
{
  public static final int a = com.ihs.app.framework.a.a().getResources().getColor(2131820858);
  public static final int b = z.a();
  public static final int c = com.ihs.app.framework.a.a().getResources().getColor(2131820857);
  
  private static String a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if ((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission)))
            {
              paramContext = localProviderInfo.authority;
              return paramContext;
            }
            i += 1;
          }
        }
      }
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static List<c> a()
  {
    Object localObject2 = GameBoostProvider.d();
    Object localObject1 = localObject2[0];
    localObject2 = localObject2[1];
    ArrayList localArrayList1 = new ArrayList();
    if ((localObject2 != null) && (localObject1 != null))
    {
      ArrayList localArrayList2 = new ArrayList();
      PackageManager localPackageManager = com.ihs.app.framework.a.a().getPackageManager();
      Iterator localIterator = localObject1.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        com.optimizer.test.d.a.a.b();
        String str2;
        try
        {
          Object localObject3 = localPackageManager.getApplicationInfo(str1, 128);
          if (localObject3 == null) {
            continue;
          }
          localObject3 = r.a(str1);
          str2 = com.optimizer.test.d.a.a.b(str1);
          if ((localObject3 != null) && (str2 != null)) {
            break label150;
          }
          localArrayList2.add(str1);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
        continue;
        label150:
        localArrayList1.add(new c(localNameNotFoundException, ((Integer)localObject1.get(localNameNotFoundException)).intValue(), ((Long)((Map)localObject2).get(localNameNotFoundException)).longValue(), str2));
      }
      GameBoostProvider.b(localArrayList2);
    }
    return localArrayList1;
  }
  
  public static void a(Context paramContext, Intent paramIntent, Bitmap paramBitmap, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      u.a(paramContext, "game_boost_shortcut", Icon.createWithBitmap(paramBitmap), paramString, paramIntent);
      return;
    }
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void a(Intent paramIntent, String paramString)
  {
    Intent localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    com.ihs.app.framework.a.a().sendBroadcast(localIntent);
  }
  
  public static boolean a(Context paramContext, String paramString, Intent paramIntent)
  {
    boolean bool;
    for (;;)
    {
      StringBuilder localStringBuilder;
      try
      {
        ContentResolver localContentResolver = paramContext.getContentResolver();
        localStringBuilder = new StringBuilder();
        Object localObject2 = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (!((String)localObject2).trim().equals("")) {}
        }
        else
        {
          localObject2 = new StringBuilder();
          localObject1 = new Intent("android.intent.action.MAIN");
          ((Intent)localObject1).addCategory("android.intent.category.HOME");
          localObject1 = paramContext.getPackageManager().resolveActivity((Intent)localObject1, 0);
          if (localObject1 == null) {
            break label351;
          }
          if (((ResolveInfo)localObject1).activityInfo != null) {
            continue;
          }
          break label351;
          localObject1 = a(paramContext, (String)localObject1 + ".permission.READ_SETTINGS");
        }
        localStringBuilder.append("content://");
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break label334;
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 8)
        {
          localStringBuilder.append("com.android.launcher.settings");
          localStringBuilder.append("/favorites?notify=true");
          paramContext = Uri.parse(localStringBuilder.toString());
          paramIntent = paramIntent.toUri(0);
          paramContext = localContentResolver.query(paramContext, new String[] { "title", "intent" }, "title=?  and intent=?", new String[] { paramString, paramIntent }, null);
          if ((paramContext == null) || (paramContext.getCount() <= 0)) {
            break label345;
          }
          bool = true;
          if ((paramContext == null) || (paramContext.isClosed())) {
            break;
          }
          paramContext.close();
          return bool;
          if (((ResolveInfo)localObject1).activityInfo.packageName.equals("android"))
          {
            localObject1 = "";
            continue;
          }
          localObject1 = ((ResolveInfo)localObject1).activityInfo.packageName;
          continue;
        }
        if (i < 19)
        {
          localStringBuilder.append("com.android.launcher2.settings");
          continue;
        }
        localStringBuilder.append("com.android.launcher3.settings");
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
      continue;
      label334:
      localStringBuilder.append((String)localObject1);
      continue;
      label345:
      bool = false;
      continue;
      label351:
      Object localObject1 = "";
    }
    return bool;
  }
  
  public static void b()
  {
    i.a(com.ihs.app.framework.a.a(), "optimizer_game_boost_ui").b("PREF_KEY_GAME_BOOST_BOOST_TIME", System.currentTimeMillis());
  }
  
  public static boolean c()
  {
    long l = i.a(com.ihs.app.framework.a.a(), "optimizer_game_boost_ui").a("PREF_KEY_GAME_BOOST_BOOST_TIME", 0L);
    return System.currentTimeMillis() - l < 10000L;
  }
}
