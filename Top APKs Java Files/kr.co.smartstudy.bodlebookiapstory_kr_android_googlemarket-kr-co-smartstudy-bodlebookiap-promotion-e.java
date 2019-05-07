package kr.co.smartstudy.bodlebookiap.promotion;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kr.co.smartstudy.sspatcher.ba;

public final class e
{
  public static final int a = 31;
  public static final int b = 8;
  private final String c = "SharePromotion";
  private final int d = 3;
  private final int e = 5;
  private final int f = 15;
  private final int g = 9;
  private String h = "com.kakao.story";
  private j[] i = new j[8];
  private i[] j = new i[15];
  
  public e()
  {
    int k = 0;
    k[] arrayOfK;
    if (k >= 8)
    {
      arrayOfK = new k[15];
      k = 0;
      if (k < 3) {
        break label113;
      }
      k = n;
    }
    for (;;)
    {
      if (k >= 15)
      {
        return;
        this.i[k] = new j(k + 31);
        k += 1;
        break;
        label113:
        int m = 0;
        for (;;)
        {
          if (m >= 5)
          {
            k += 1;
            break;
          }
          arrayOfK[(k * 5 + m)] = new k(Long.parseLong(String.format("201408%d%02d00", new Object[] { Integer.valueOf(k + 15), Integer.valueOf(m * 2 + 9) })), Long.parseLong(String.format("201408%d%02d59", new Object[] { Integer.valueOf(k + 15), Integer.valueOf(m * 2 + 9 + 1) })));
          m += 1;
        }
      }
      this.j[k] = new i(arrayOfK[k], this.i[(k % 8)]);
      k += 1;
    }
  }
  
  public final i a()
  {
    long l = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(Long.valueOf(System.currentTimeMillis())));
    int k = 0;
    if (k >= 3) {
      return null;
    }
    int m = 0;
    for (;;)
    {
      if (m >= 5)
      {
        k += 1;
        break;
      }
      if (this.j[(k * 5 + m)].a(l)) {
        return this.j[(m + k * 5)];
      }
      m += 1;
    }
  }
  
  public final void a(Context paramContext)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    long l1 = Long.parseLong(localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
    if (201408171900L < l1) {
      return;
    }
    paramContext = new f(paramContext);
    int k = 0;
    while (k < 15)
    {
      long l2 = this.j[k].b().a();
      Calendar localCalendar;
      if (l1 < l2) {
        localCalendar = Calendar.getInstance();
      }
      try
      {
        localCalendar.setTime(localSimpleDateFormat.parse(String.valueOf(l2)));
        paramContext.a(localCalendar, this.j[k].a().a(), k + 100);
        new Date().setTime(localCalendar.getTimeInMillis());
        k += 1;
      }
      catch (ParseException localParseException)
      {
        for (;;)
        {
          ba.b("SharePromotion", "SharePromotion Alarm setting error", localParseException);
        }
      }
    }
  }
  
  public final boolean a(Activity paramActivity)
  {
    paramActivity = paramActivity.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramActivity.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)paramActivity.next();
    } while (!this.h.equals(localPackageInfo.packageName));
    return true;
  }
  
  public final String b()
  {
    return this.h;
  }
  
  public final Set<Integer> c()
  {
    HashSet localHashSet = new HashSet();
    int k = 0;
    for (;;)
    {
      if (k >= 8) {
        return localHashSet;
      }
      if (this.i[k].b()) {
        localHashSet.add(Integer.valueOf(this.i[k].a()));
      }
      k += 1;
    }
  }
}
