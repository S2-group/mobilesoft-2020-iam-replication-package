package com.hyperspeed.rocketclean.pro;

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
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class dga
{
  public static final int m = cep.m().getResources().getColor(2131100090);
  public static final int mn = cep.m().getResources().getColor(2131100089);
  public static final int n = dym.m();
  
  private static String m(Context paramContext, String paramString)
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
  
  public static List<dgb> m()
  {
    Object localObject2 = dfy.b();
    Object localObject1 = localObject2[0];
    localObject2 = localObject2[1];
    ArrayList localArrayList1 = new ArrayList();
    if ((localObject2 != null) && (localObject1 != null))
    {
      ArrayList localArrayList2 = new ArrayList();
      PackageManager localPackageManager = cep.m().getPackageManager();
      Iterator localIterator = localObject1.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        dvv.a.m().m();
        String str2;
        try
        {
          Object localObject3 = localPackageManager.getApplicationInfo(str1, 128);
          if (localObject3 == null) {
            continue;
          }
          localObject3 = dyf.m(str1);
          str2 = dvv.a.m().n(str1);
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
        localArrayList1.add(new dgb(localNameNotFoundException, ((Integer)localObject1.get(localNameNotFoundException)).intValue(), ((Long)((Map)localObject2).get(localNameNotFoundException)).longValue(), str2));
      }
      dfy.n(localArrayList2);
    }
    return localArrayList1;
  }
  
  public static void m(Context paramContext, Intent paramIntent, Bitmap paramBitmap, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      dyi.m(paramContext, "game_boost_shortcut", paramBitmap, paramString, paramIntent);
      return;
    }
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void m(Intent paramIntent, String paramString)
  {
    Intent localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    cep.m().sendBroadcast(localIntent);
  }
  
  public static boolean m(Context paramContext, String paramString, Intent paramIntent)
  {
    boolean bool;
    for (;;)
    {
      StringBuilder localStringBuilder;
      try
      {
        ContentResolver localContentResolver = paramContext.getContentResolver();
        localStringBuilder = new StringBuilder();
        Object localObject2 = m(paramContext, "com.android.launcher.permission.READ_SETTINGS");
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
          localObject1 = m(paramContext, (String)localObject1 + ".permission.READ_SETTINGS");
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
  
  public static boolean mn()
  {
    long l = cgh.m(cep.m(), "optimizer_game_boost_ui").m("PREF_KEY_GAME_BOOST_BOOST_TIME", 0L);
    return System.currentTimeMillis() - l < 10000L;
  }
  
  public static void n()
  {
    cgh.m(cep.m(), "optimizer_game_boost_ui").n("PREF_KEY_GAME_BOOST_BOOST_TIME", System.currentTimeMillis());
  }
}
