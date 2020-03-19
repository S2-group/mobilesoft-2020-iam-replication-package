package kr.co.smartstudy.bodlebookiap.promotion;

import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kr.co.smartstudy.bodlebookiap.k;
import kr.co.smartstudy.bodlebookiap.x.g;
import kr.co.smartstudy.sspatcher.m;

public class g
{
  private static final String a = "timetable_promotion";
  private static g d;
  private static final String e = "com.kakao.story";
  private List<d> b;
  private Context c;
  private int[] f = { 32, 100501, 28, 100801, 31, 217 };
  private int[] g = { x.g.noti_kr_item_32, x.g.noti_kr_item_100501, x.g.noti_kr_item_28, x.g.noti_kr_item_100801, x.g.noti_kr_item_31, x.g.noti_kr_item_217 };
  private String[] h = { "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요.", "[설맞이 EVENT] 지금 무료 동요 이벤트에 참여하세요." };
  private String[] i = { "http://i.sstudy.kr/L/387", "http://i.sstudy.kr/L/388", "http://i.sstudy.kr/L/389", "http://i.sstudy.kr/L/390", "http://i.sstudy.kr/L/391", "http://i.sstudy.kr/L/392" };
  private final String j;
  
  private g(Context paramContext)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("★설 맞이, 새해 첫 인기동요 무료 이벤트★\n\n");
    ((StringBuilder)localObject).append("2월 18일부터 2월 20일 3일간.\n");
    ((StringBuilder)localObject).append("[Play 스토어 핑크퐁! 인기동요]에서\n");
    ((StringBuilder)localObject).append("3시간 간격으로 새로운 동요가 무료!\n\n");
    ((StringBuilder)localObject).append("지금 핑크퐁! 인기동요 앱을 실행하세요~\n");
    ((StringBuilder)localObject).append("이벤트 바로가기: %s\n");
    this.j = ((StringBuilder)localObject).toString();
    this.c = paramContext;
    this.b = new ArrayList();
    for (;;)
    {
      int k;
      int n;
      try
      {
        paramContext = k.a();
        k = 18;
        m = 0;
      }
      catch (Exception paramContext)
      {
        int m;
        m.b("timetable_promotion", "date parse error", paramContext);
      }
      if (n <= 18)
      {
        localObject = new d.a().a(paramContext.b(this.f[m])).a(String.format("201502%02d%02d00", new Object[] { Integer.valueOf(k), Integer.valueOf(n) })).b(String.format("201502%02d%02d59", new Object[] { Integer.valueOf(k), Integer.valueOf(n + 2) })).a("link", this.i[m]).a();
        m = (m + 1) % this.f.length;
        this.b.add(localObject);
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
  
  public static g a(Context paramContext)
  {
    if (d == null) {
      d = new g(paramContext);
    }
    return d;
  }
  
  private void a(Context paramContext, d paramD)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://www.facebook.com/sharer/sharer.php?u=");
    localStringBuilder.append(paramD.a("link"));
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
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
      ((d)localIterator.next()).a(this.c.getApplicationContext(), this.h[k], this.g[k]);
    }
  }
  
  public void a(final Context paramContext, final d paramD, DialogInterface.OnDismissListener paramOnDismissListener)
  {
    paramContext = new e(paramContext, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramD.b();
        paramD.c();
        if (!g.b(paramContext)) {
          return;
        }
        Intent localIntent = new Intent();
        try
        {
          localIntent.setAction("android.intent.action.SEND");
          localIntent.setPackage("com.kakao.story");
          localIntent.setType("text/plain");
          localIntent.setFlags(337641472);
          localIntent.putExtra("android.intent.extra.TEXT", String.format(g.a(g.this), new Object[] { paramD.a("link") }));
          paramAnonymousView.getContext().startActivity(localIntent);
          return;
        }
        catch (Exception paramAnonymousView) {}
      }
    });
    paramContext.setOnDismissListener(paramOnDismissListener);
    paramContext.show();
  }
  
  public d b()
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      d localD = (d)localIterator.next();
      if (localD.a()) {
        return localD;
      }
    }
    return null;
  }
  
  public boolean c()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    try
    {
      long l1 = localSimpleDateFormat.parse("201502202100").getTime();
      long l2 = System.currentTimeMillis();
      if (l1 <= l2) {
        return true;
      }
    }
    catch (ParseException localParseException)
    {
      m.b("timetable_promotion", "date parse error", localParseException);
    }
    return false;
  }
}
