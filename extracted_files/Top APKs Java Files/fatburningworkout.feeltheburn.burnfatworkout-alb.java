import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.zjlib.thirtydaylib.R.string;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class alb
{
  private static alb a;
  
  private alb() {}
  
  public static alb a()
  {
    try
    {
      if (a == null) {
        a = new alb();
      }
      alb localAlb = a;
      return localAlb;
    }
    finally {}
  }
  
  public void a(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.SUBJECT", String.format(paramContext.getString(R.string.td_share_title), new Object[] { paramContext.getString(R.string.app_name) }));
      localIntent.putExtra("android.intent.extra.TEXT", paramContext.getString(R.string.td_share_text, new Object[] { paramString, "https://fatburningworkout.page.link/share" }));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      alj.a(paramContext, "EmailUtils-4", paramString, false);
      paramString.printStackTrace();
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
