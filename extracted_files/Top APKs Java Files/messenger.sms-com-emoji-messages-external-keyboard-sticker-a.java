package com.emoji.messages.external.keyboard.sticker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.emoji.messages.external.entity.StickerInfo;
import com.emoji.messages.sms.ui.p;
import com.google.a.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static Drawable a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = null;
    try
    {
      Resources localResources = paramContext.getPackageManager().getResourcesForApplication(paramString1);
      int i = localResources.getIdentifier(paramString1 + ":drawable/" + paramString2, null, null);
      paramContext = localObject;
      if (i != 0) {
        paramContext = localResources.getDrawable(i);
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (OutOfMemoryError paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void a(Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
        if (TextUtils.isEmpty(localSharedPreferences.getString("pref_sticker_installed_list", "")))
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = this.a.getPackageManager().getInstalledPackages(0).iterator();
          while (localIterator.hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
            if (a.a(localPackageInfo.packageName))
            {
              StickerInfo localStickerInfo = new StickerInfo();
              localStickerInfo.setTitle(p.b(this.a, localPackageInfo.packageName));
              localStickerInfo.setPackage_name(localPackageInfo.packageName);
              localStickerInfo.isInstalled = true;
              localArrayList.add(localStickerInfo);
            }
          }
          localSharedPreferences.edit().putString("pref_sticker_installed_list", new f().a(localArrayList)).apply();
        }
      }
    }).start();
  }
  
  public static boolean a(String paramString)
  {
    return paramString.contains("com.emojifamily.emoji.keyboard.sticker");
  }
}
