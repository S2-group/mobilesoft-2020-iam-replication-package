package xyz.klinker.android.article;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

final class Utils
{
  Utils() {}
  
  static void changeTextSelectionHandleColors(Context paramContext, TextView paramTextView, int paramInt)
  {
    paramTextView.setHighlightColor(Color.argb(40, Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt)));
    try
    {
      Object localObject1 = TextView.class.getDeclaredField("mEditor");
      if (!((Field)localObject1).isAccessible()) {
        ((Field)localObject1).setAccessible(true);
      }
      Object localObject2 = ((Field)localObject1).get(paramTextView);
      Class localClass = localObject2.getClass();
      int i = 0;
      while (i < 3)
      {
        Field localField = localClass.getDeclaredField(new String[] { "mSelectHandleLeft", "mSelectHandleRight", "mSelectHandleCenter" }[i]);
        if (!localField.isAccessible()) {
          localField.setAccessible(true);
        }
        Drawable localDrawable = (Drawable)localField.get(localObject2);
        localObject1 = localDrawable;
        if (localDrawable == null)
        {
          localObject1 = TextView.class.getDeclaredField(new String[] { "mTextSelectHandleLeftRes", "mTextSelectHandleRightRes", "mTextSelectHandleRes" }[i]);
          if (!((Field)localObject1).isAccessible()) {
            ((Field)localObject1).setAccessible(true);
          }
          int j = ((Field)localObject1).getInt(paramTextView);
          if (Build.VERSION.SDK_INT >= 21) {
            localObject1 = paramContext.getDrawable(j);
          } else {
            localObject1 = paramContext.getResources().getDrawable(j);
          }
        }
        if (localObject1 != null)
        {
          localObject1 = ((Drawable)localObject1).mutate();
          ((Drawable)localObject1).setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
          localField.set(localObject2, localObject1);
        }
        i += 1;
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  static boolean saveArticlePermissionAvailable(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
    for (;;)
    {
      boolean bool = localIterator.hasNext();
      int i = 0;
      if (bool)
      {
        Object localObject = (ApplicationInfo)localIterator.next();
        try
        {
          localObject = paramContext.getPackageInfo(((ApplicationInfo)localObject).packageName, 4096).requestedPermissions;
          if (localObject != null)
          {
            int j = localObject.length;
            while (i < j)
            {
              bool = localObject[i].equals("xyz.klinker.android.article.SAVED_ARTICLE");
              if (bool) {
                return true;
              }
              i += 1;
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
      }
    }
    return false;
  }
}
