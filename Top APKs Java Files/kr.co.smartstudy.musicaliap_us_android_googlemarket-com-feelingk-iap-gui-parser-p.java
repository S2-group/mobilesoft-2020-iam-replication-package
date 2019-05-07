package com.feelingk.iap.gui.parser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.List;

final class p
  implements View.OnClickListener
{
  p(ParserXML paramParserXML) {}
  
  public final void onClick(View paramView)
  {
    paramView = ParserXML.d(this.a).getPackageManager().getInstalledApplications(0);
    int j = paramView.size();
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        if (!ParserXML.w(this.a)) {
          break label275;
        }
        paramView = null;
      }
      try
      {
        localObject = ParserXML.d(this.a).getPackageManager().getPackageInfo("com.skt.skaf.A000Z00040", 0);
        paramView = (View)localObject;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Object localObject;
          localNameNotFoundException.printStackTrace();
        }
        paramView = new Intent();
        paramView.addFlags(536870912);
        paramView.setClassName("com.skt.skaf.A000Z00040", "com.skt.skaf.A000Z00040.A000Z00040");
        paramView.setAction("COLLAB_ACTION");
        paramView.putExtra("com.skt.skaf.COL.URI", "SETTING_VIEW".getBytes());
        paramView.putExtra("com.skt.skaf.COL.REQUESTER", "A000Z00040");
        ParserXML.d(this.a).startActivity(paramView);
        return;
      }
      localObject = paramView.versionName;
      i = paramView.versionCode;
      Log.i("ParserXML", "application versionName : " + (String)localObject);
      Log.i("ParserXML", "application versionCode : " + i);
      if (i >= 19) {
        break;
      }
      ParserXML.M(this.a).d();
      return;
      if (((ApplicationInfo)paramView.get(i)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0)
      {
        ParserXML.c(this.a, true);
        Log.i("ParserXML", "티스토어 설치여부: " + ParserXML.w(this.a));
      }
      i += 1;
    }
    label275:
    ParserXML.M(this.a).c();
  }
}
