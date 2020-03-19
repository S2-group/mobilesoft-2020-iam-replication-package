package com.ximalaya.ting.himalaya.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.a.a.a.a.a.a;
import com.himalaya.ting.a.c;
import com.ximalaya.ting.e.h;
import com.ximalaya.ting.himalaya.common.HiApplication;
import com.ximalaya.ting.himalaya.data.TrackM;
import com.ximalaya.ting.himalaya.manager.ShareManager.ShareToType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ToolUtils
{
  public ToolUtils() {}
  
  public static void addShortcut(Activity paramActivity)
  {
    if (h.getInstance().getBoolean("key_isCreatedShortcut", false)) {
      return;
    }
    Intent localIntent1 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    Intent localIntent2 = new Intent("android.intent.action.MAIN");
    String str = paramActivity.getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramActivity.getPackageName());
    localStringBuilder.append(".");
    localStringBuilder.append(paramActivity.getClass().getSimpleName());
    localIntent2.setComponent(new ComponentName(str, localStringBuilder.toString()));
    localIntent2.setClass(paramActivity.getApplicationContext(), paramActivity.getClass());
    localIntent2.setAction("android.intent.action.MAIN");
    localIntent2.addCategory("android.intent.category.LAUNCHER");
    localIntent2.setFlags(2097152);
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramActivity.getString(2131820588));
    localIntent1.putExtra("duplicate", false);
    localIntent1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramActivity.getApplicationContext(), 2131624092));
    paramActivity.sendBroadcast(localIntent1);
    h.getInstance().putBoolean("key_isCreatedShortcut", true);
  }
  
  public static boolean areNotificationsEnabled(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return NotificationManagerCompat.from(paramContext).areNotificationsEnabled();
    }
    return true;
  }
  
  public static void clearTextLineCache()
  {
    try
    {
      Object localObject = Class.forName("android.text.TextLine").getDeclaredField("sCached");
      ((Field)localObject).setAccessible(true);
      if (localObject == null) {
        return;
      }
      localObject = ((Field)localObject).get(null);
      if (localObject != null)
      {
        int i = 0;
        int j = Array.getLength(localObject);
        while (i < j)
        {
          Array.set(localObject, i, null);
          i += 1;
        }
      }
      return;
    }
    catch (Exception localException)
    {
      a.a(localException);
    }
  }
  
  @Nullable
  public static Notification createNotification(Context paramContext, String paramString1, String paramString2, String paramString3, PendingIntent paramPendingIntent)
  {
    String str = paramString1;
    if (TextUtils.isEmpty(paramString1)) {
      str = paramContext.getResources().getString(2131820870);
    }
    paramContext = new NotificationCompat.Builder(paramContext, "push");
    int j = 2131230948;
    int i = j;
    if (Build.VERSION.SDK_INT >= 21)
    {
      i = j;
      if (!"vivo".equalsIgnoreCase(c.r()))
      {
        i = 2131230949;
        paramContext.setColor(-239566);
      }
    }
    paramContext.setWhen(System.currentTimeMillis()).setSmallIcon(i).setTicker(paramString2).setContentTitle(str).setContentText(paramString3).setContentIntent(paramPendingIntent).setAutoCancel(true).setDefaults(1).setStyle(new NotificationCompat.BigTextStyle().bigText(paramString3));
    if (Build.VERSION.SDK_INT >= 16) {
      paramContext.setPriority(1);
    }
    try
    {
      paramContext = paramContext.build();
      paramContext.when = System.currentTimeMillis();
      return paramContext;
    }
    catch (NullPointerException paramContext)
    {
      a.a(paramContext);
    }
    return null;
  }
  
  public static void fixInputMethodManagerLeak(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    InputMethodManager localInputMethodManager = (InputMethodManager)paramContext.getSystemService("input_method");
    if (localInputMethodManager == null) {
      return;
    }
    String[] arrayOfString = new String[3];
    int i = 0;
    arrayOfString[0] = "mCurRootView";
    arrayOfString[1] = "mServedView";
    arrayOfString[2] = "mNextServedView";
    try
    {
      while (i < arrayOfString.length)
      {
        Object localObject1 = arrayOfString[i];
        localObject1 = localInputMethodManager.getClass().getDeclaredField((String)localObject1);
        if (!((Field)localObject1).isAccessible()) {
          ((Field)localObject1).setAccessible(true);
        }
        Object localObject2 = ((Field)localObject1).get(localInputMethodManager);
        if ((localObject2 != null) && ((localObject2 instanceof View)))
        {
          localObject2 = (View)localObject2;
          if ((((View)localObject2).getContext() != paramContext) && ((!(((View)localObject2).getContext() instanceof ContextThemeWrapper)) || (((ContextThemeWrapper)((View)localObject2).getContext()).getBaseContext() != paramContext))) {
            break;
          }
          ((Field)localObject1).set(localInputMethodManager, null);
        }
        i += 1;
      }
      return;
    }
    catch (Exception paramContext)
    {
      a.a(paramContext);
    }
  }
  
  public static String getMetaData(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString(paramString);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      a.a(paramContext);
    }
    return null;
  }
  
  public static String getRandomUUID()
  {
    return UUID.randomUUID().toString();
  }
  
  public static int getSubscriptionAlbumRequestPageId(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    int i = 1;
    if (paramInt1 > 0)
    {
      if (paramInt2 <= 0) {
        return 1;
      }
      if (!paramBoolean)
      {
        if (paramInt1 % paramInt3 != 0) {
          paramInt1 = paramInt1 / paramInt3 + 1;
        } else {
          paramInt1 /= paramInt3;
        }
        if (paramInt2 % paramInt3 != 0) {
          paramInt2 = paramInt2 / paramInt3 + 1;
        } else {
          paramInt2 /= paramInt3;
        }
        if (paramInt1 <= paramInt2) {
          return 1;
        }
        i = 1 + (paramInt1 - paramInt2);
      }
      return i;
    }
    return 1;
  }
  
  public static <T> boolean isEmptyCollects(Collection<T> paramCollection)
  {
    return (paramCollection == null) || (paramCollection.isEmpty());
  }
  
  public static boolean isWebviewAvailable()
  {
    Object localObject = HiApplication.a().getApplicationContext();
    if (localObject == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT > 20)
    {
      localObject = ((Context)localObject).getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < ((List)localObject).size())
      {
        if (((PackageInfo)((List)localObject).get(i)).packageName.equalsIgnoreCase("com.google.android.webview")) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    return true;
  }
  
  public static void showInstalledAppDetails(Context paramContext)
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      Uri localUri = Uri.fromParts("package", paramContext.getPackageName(), null);
      localIntent.setFlags(268435456);
      localIntent.setData(localUri);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void sortArrayListByIndex(List<ShareManager.ShareToType> paramList)
  {
    Collections.sort(paramList, new ToolUtils.1());
  }
  
  public static void stampNewFlag(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, List<TrackM> paramList, int paramInt5)
  {
    int j;
    int k;
    int i;
    if ((paramList != null) && (!paramList.isEmpty()) && (paramInt1 > 0))
    {
      if (paramInt1 > paramInt3) {
        return;
      }
      j = 0;
      k = 0;
      i = 0;
      if (!paramBoolean) {
        break label325;
      }
      if (paramInt1 % paramInt5 == 0) {}
    }
    try
    {
      paramInt3 = paramInt1 / paramInt5 + 1;
    }
    catch (Exception paramList)
    {
      label108:
      label140:
      label170:
      label211:
      return;
    }
    paramInt3 = paramInt1 / paramInt5;
    break label287;
    if ((paramInt1 > 0) && (paramList.size() >= paramInt1))
    {
      paramInt2 = i;
      while (paramInt2 < paramInt1)
      {
        ((TrackM)paramList.get(paramInt2)).setNewTrack(true);
        paramInt2 += 1;
        continue;
        paramInt3 = paramInt1 - i;
        if (paramInt3 % paramInt5 != 0)
        {
          paramInt3 = paramInt3 / paramInt5 + 2;
          break label339;
        }
        paramInt3 = paramInt3 / paramInt5 + 1;
        break label339;
        while (paramInt2 >= i - paramInt1)
        {
          ((TrackM)paramList.get(paramInt2)).setNewTrack(true);
          paramInt2 -= 1;
          continue;
          if (paramInt2 >= paramInt5 - paramInt1)
          {
            if ((paramInt2 < 0) || (paramInt2 >= paramList.size())) {
              break label401;
            }
            ((TrackM)paramList.get(paramInt2)).setNewTrack(true);
            break label401;
            while (paramInt1 < i)
            {
              ((TrackM)paramList.get(paramInt1)).setNewTrack(true);
              paramInt1 += 1;
            }
          }
        }
      }
    }
    for (;;)
    {
      if (paramInt1 < paramInt5)
      {
        if ((paramInt1 >= 0) && (paramInt1 < paramList.size())) {
          ((TrackM)paramList.get(paramInt1)).setNewTrack(true);
        }
        paramInt1 += 1;
      }
      else
      {
        label287:
        label325:
        label339:
        label358:
        label401:
        do
        {
          do
          {
            return;
            return;
            if (paramInt2 < paramInt3)
            {
              paramInt1 = paramInt5;
              break;
            }
            if (paramInt2 == paramInt3)
            {
              if (paramInt1 <= paramInt5) {
                break;
              }
              paramInt1 %= paramInt5;
              break;
            }
            paramInt1 = 0;
            break;
            i = paramInt3 % paramInt5;
            if (i < paramInt1) {
              break label108;
            }
            paramInt3 = 1;
            if (paramInt3 != 1) {
              break label358;
            }
          } while (paramInt2 != paramInt4);
          paramInt2 = i - 1;
          break label140;
          paramInt3 = paramInt4 - paramInt3 + 1;
          if (paramInt2 == paramInt3)
          {
            paramInt1 -= i;
            if (paramInt1 % paramInt5 != 0) {
              paramInt1 %= paramInt5;
            } else {
              paramInt1 = paramInt5;
            }
            paramInt2 = paramInt5 - 1;
            break label170;
            paramInt2 -= 1;
            break label170;
          }
          if (paramInt2 == paramInt4)
          {
            paramInt1 = j;
            break label211;
          }
        } while (paramInt2 <= paramInt3);
        paramInt1 = k;
      }
    }
  }
}
