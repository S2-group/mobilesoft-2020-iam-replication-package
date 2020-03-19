package com.kauf.marketing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.kauf.util.ServerComm;
import com.kauf.util.ServerComm.Method;
import com.kauf.util.ServerComm.Protocol;
import com.kauf.util.Store;
import java.util.Iterator;
import java.util.List;

public class WhatsApp
  implements View.OnClickListener
{
  private static final boolean SERVER_REPORT = true;
  private static final String URL = "http://www.kauf.com/wr.pl?app=";
  private static final String URL_REPORT = "https://android.maxpedia.org/android/ad/pread/wr.pl";
  private static final String WHATSAPP = "com.whatsapp";
  private String apk;
  private Context context;
  
  public WhatsApp(Context paramContext, ImageView paramImageView)
  {
    this.context = paramContext;
    this.apk = Store.getBasePackageId(paramContext.getPackageName());
    if (isWhatsAppInstalled())
    {
      paramImageView.setImageResource(R.drawable.whatsapp);
      paramImageView.setOnClickListener(this);
      paramImageView.setVisibility(0);
      return;
    }
    paramImageView.setVisibility(4);
  }
  
  private boolean isWhatsAppInstalled()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(128).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.startsWith("com.whatsapp"));
    return true;
  }
  
  private void reportToServer()
  {
    ServerComm localServerComm = new ServerComm((Activity)this.context);
    localServerComm.setServerUrl("https://android.maxpedia.org/android/ad/pread/wr.pl");
    localServerComm.setMethod(ServerComm.Method.METHOD_GET);
    localServerComm.setProtocol(ServerComm.Protocol.PROTOCOL_HTTPS);
    localServerComm.connect(UserInfos.UserParams((Activity)this.context).toString() + "&" + System.currentTimeMillis());
  }
  
  public void onClick(View paramView)
  {
    try
    {
      reportToServer();
      paramView = new Intent();
      paramView.setAction("android.intent.action.SEND");
      paramView.putExtra("android.intent.extra.TEXT", "http://www.kauf.com/wr.pl?app=" + this.apk);
      paramView.setType("text/plain");
      paramView.setPackage("com.whatsapp");
      this.context.startActivity(paramView);
      return;
    }
    catch (Exception paramView) {}
  }
}
