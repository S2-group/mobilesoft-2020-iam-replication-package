package kr.co.smartstudy.bodlebookiap.promotion;

import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kr.co.smartstudy.bodlebookiap.ar;
import kr.co.smartstudy.bodlebookiap.fb;
import kr.co.smartstudy.sspatcher.bn;

public class m
{
  private static final String a = "timetable_promotion";
  private static m d = null;
  private static final String e = "com.kakao.story";
  private List<e> b;
  private Context c;
  private int[] f = { 32, 100501, 28, 100801, 31, 217 };
  private int[] g = { fb.noti_kr_item_32, fb.noti_kr_item_100501, fb.noti_kr_item_28, fb.noti_kr_item_100801, fb.noti_kr_item_31, fb.noti_kr_item_217 };
  private String[] h = { "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요." };
  private String[] i = { "http://i.sstudy.kr/L/387", "http://i.sstudy.kr/L/388", "http://i.sstudy.kr/L/389", "http://i.sstudy.kr/L/390", "http://i.sstudy.kr/L/391", "http://i.sstudy.kr/L/392" };
  private final String j = "★설 맞이, 새해 첫 인기동요 무료 이벤트★\n\n" + "2월 18일부터 2월 20일 3일간.\n" + "[Play 스토어 핑크퐁! 인기동요]에서\n" + "3시간 간격으로 새로운 동요가 무료!\n\n" + "지금 핑크퐁! 인기동요 앱을 실행하세요~\n" + "이벤트 바로가기: %s\n";
  
  private m(Context paramContext)
  {
    this.c = paramContext;
    this.b = new ArrayList();
    for (;;)
    {
      int k;
      int n;
      try
      {
        paramContext = ar.a();
        k = 18;
      }
      catch (Exception paramContext)
      {
        e localE;
        bn.b("timetable_promotion", "date parse error", paramContext);
      }
      if (n <= 18)
      {
        localE = new g().a(paramContext.b(this.f[m])).a(String.format("201502%02d%02d00", new Object[] { Integer.valueOf(k), Integer.valueOf(n) })).b(String.format("201502%02d%02d59", new Object[] { Integer.valueOf(k), Integer.valueOf(n + 2) })).a("link", this.i[m]).a();
        m = (m + 1) % this.f.length;
        this.b.add(localE);
        n += 3;
      }
      else
      {
        k += 1;
        while (k > 20) {
          return;
        }
        n = 9;
      }
    }
  }
  
  public static m a(Context paramContext)
  {
    if (d == null) {
      d = new m(paramContext);
    }
    return d;
  }
  
  private void a(Context paramContext, e paramE)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/sharer/sharer.php?u=" + paramE.a("link"))));
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if ("com.kakao.story".equals(((PackageInfo)paramContext.next()).packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public void a()
  {
    Iterator localIterator = this.b.iterator();
    for (int k = 0; localIterator.hasNext(); k = (k + 1) % this.f.length) {
      ((e)localIterator.next()).a(this.c.getApplicationContext(), this.h[k], this.g[k]);
    }
  }
  
  public void a(Context paramContext, e paramE, DialogInterface.OnDismissListener paramOnDismissListener)
  {
    paramContext = new h(paramContext, new n(this, paramE, paramContext));
    paramContext.setOnDismissListener(paramOnDismissListener);
    paramContext.show();
  }
  
  public e b()
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      e localE = (e)localIterator.next();
      if (localE.a()) {
        return localE;
      }
    }
    return null;
  }
  
  public boolean c()
  {
    boolean bool = false;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    try
    {
      long l1 = localSimpleDateFormat.parse("201502202100").getTime();
      long l2 = System.currentTimeMillis();
      if (l1 <= l2) {
        bool = true;
      }
      return bool;
    }
    catch (ParseException localParseException)
    {
      bn.b("timetable_promotion", "date parse error", localParseException);
    }
    return false;
  }
}
