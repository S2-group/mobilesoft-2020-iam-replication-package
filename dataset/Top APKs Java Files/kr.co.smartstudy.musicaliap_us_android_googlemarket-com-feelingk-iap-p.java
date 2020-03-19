package com.feelingk.iap;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.feelingk.iap.b.ai;
import java.util.List;

final class p
  implements View.OnClickListener
{
  p(IAPActivity paramIAPActivity) {}
  
  public final void onClick(View paramView)
  {
    IAPActivity.K(this.a);
    Log.i("IAPActivity", "DlgType" + an.s());
    if (an.s() == 103)
    {
      IAPActivity.K(this.a);
      paramView = new Intent("android.intent.action.VIEW", Uri.parse("http://helpdesk.nate.com/userinfo/exMemberInfo.asp?pgcode=my_phone"));
      this.a.startActivity(paramView);
      IAPActivity.o(this.a);
      an.a(125);
    }
    do
    {
      return;
      if (an.s() == 126)
      {
        IAPActivity.K(this.a);
        an.a(102);
        IAPActivity.k(this.a);
        an.b("D", IAPActivity.i(this.a).g());
        return;
      }
      if ((an.s() == 127) || (an.s() == 128) || (an.s() == 130))
      {
        an.a(103);
        IAPActivity.a(this.a, IAPActivity.i(this.a));
        return;
      }
    } while (an.s() != 114);
    paramView = this.a.getPackageManager().getInstalledApplications(0);
    int k = paramView.size();
    int j = 0;
    int i = 0;
    if (j >= k)
    {
      if (i != 0)
      {
        paramView = new Intent();
        paramView.setClassName("com.skt.skaf.A000Z00040", "com.skt.skaf.A000Z00040.A000Z00040");
        this.a.startActivity(paramView);
      }
    }
    else
    {
      if (((ApplicationInfo)paramView.get(j)).packageName.indexOf("com.skt.skaf.A000Z00040") != 0) {
        break label295;
      }
      i = 1;
      Log.i("IAPActivity", "티스토어 설치여부: true");
    }
    label295:
    for (;;)
    {
      j += 1;
      break;
      IAPActivity.K(this.a);
      return;
    }
  }
}
