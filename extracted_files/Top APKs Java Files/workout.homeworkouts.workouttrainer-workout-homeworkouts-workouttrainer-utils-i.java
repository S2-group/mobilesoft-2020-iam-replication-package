package workout.homeworkouts.workouttrainer.utils;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class i
{
  private static i a;
  
  private i() {}
  
  public static i a()
  {
    try
    {
      if (a == null) {
        a = new i();
      }
      i localI = a;
      return localI;
    }
    finally {}
  }
  
  public void a(Context paramContext, int paramInt)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getString(2131624460));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getString(paramInt));
      localStringBuilder.append("http://play.google.com/store/apps/details?id=workout.homeworkouts.workouttrainer");
      localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if (!TextUtils.isEmpty(localPackageInfo.packageName))
          {
            boolean bool = localPackageInfo.packageName.equals("com.google.android.gm");
            if (bool) {
              return true;
            }
          }
        }
      }
    }
    catch (Error paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          if (!TextUtils.isEmpty(localPackageInfo.packageName))
          {
            boolean bool = localPackageInfo.packageName.equals("com.android.email");
            if (bool) {
              return true;
            }
          }
        }
      }
    }
    catch (Error paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
