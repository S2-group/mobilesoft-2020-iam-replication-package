package ws.coverme.im.ui.wx;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;

public class WhatsAppShare
{
  public static final String WeChatPackageName = "com.tencent.mm";
  public static final String WhatsAppPackageName = "com.whatsapp";
  public static boolean installedWeChat = false;
  public static boolean installedWhatsApp = false;
  
  public WhatsAppShare() {}
  
  public static void checkWhatsAppAndWeChatInstall(Context paramContext)
  {
    installedWhatsApp = false;
    installedWeChat = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      String str = ((PackageInfo)paramContext.next()).applicationInfo.packageName;
      if ("com.whatsapp".equals(str)) {
        installedWhatsApp = true;
      }
      if ("com.tencent.mm".equals(str)) {
        installedWeChat = true;
      }
    }
  }
  
  public static void sharePic(String paramString1, String paramString2, Context paramContext)
  {
    paramString1 = Uri.parse(paramString1);
    Intent localIntent = new Intent();
    localIntent.setPackage("com.whatsapp");
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.STREAM", paramString1);
    if ("1".equals(paramString2)) {
      localIntent.setType("image/*");
    }
    for (;;)
    {
      try
      {
        paramContext.startActivity(localIntent);
        return;
      }
      catch (ActivityNotFoundException paramString1)
      {
        Toast.makeText(paramContext, 2131167348, 1).show();
      }
      if ("4".equals(paramString2)) {
        localIntent.setType("video/*");
      }
    }
  }
  
  public static void shareText(String paramString, Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage("com.whatsapp");
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.setType("text/plain");
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Toast.makeText(paramContext, 2131167348, 1).show();
    }
  }
}
