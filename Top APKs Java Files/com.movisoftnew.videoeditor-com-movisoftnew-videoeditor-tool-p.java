package com.movisoftnew.videoeditor.tool;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.meitu.meipaimv.sdk.b.e;
import com.meitu.meipaimv.sdk.modelmsg.MeipaiVideoObject;
import com.movisoftnew.videoeditor.VideoEditorApplication;
import java.util.Iterator;
import java.util.List;

public class p
{
  private static p b;
  public e a = com.meitu.meipaimv.sdk.b.d.a(VideoEditorApplication.a, "1089867410");
  private com.meitu.meipaimv.sdk.b.c c = new com.meitu.meipaimv.sdk.b.c()
  {
    public void a(com.meitu.meipaimv.sdk.a.c paramAnonymousC)
    {
      switch (paramAnonymousC.c)
      {
      case -1: 
      default: 
        return;
      case 0: 
        i.d("cpy", "onResponse ERR_OK");
        new StringBuilder().append("onResponse ERR_OK ").append(paramAnonymousC.b).toString();
        return;
      case -2: 
        i.d("cpy", "onResponse ERR_USER_CANCEL");
        new StringBuilder().append("onResponse ERR_USER_CANCEL ").append(paramAnonymousC.b).toString();
        return;
      case -4: 
        i.d("cpy", "onResponse ERR_AUTH_DENIED");
        new StringBuilder().append("onResponse ERR_AUTH_DENIED ").append(paramAnonymousC.b).toString();
        return;
      case -3: 
        i.d("cpy", "onResponse ERR_SENT_FAILED");
        new StringBuilder().append("onResponse ERR_SENT_FAILED ").append(paramAnonymousC.b).toString();
        return;
      }
      i.d("cpy", "onResponse ERR_UNSUPPORT");
      new StringBuilder().append("onResponse ERR_UNSUPPORT ").append(paramAnonymousC.b).toString();
    }
  };
  
  private p() {}
  
  public static p a()
  {
    if (b == null) {}
    try
    {
      if (b == null) {
        b = new p();
      }
      return b;
    }
    finally {}
  }
  
  public static void a(Context paramContext, a paramA, String paramString)
  {
    Object localObject = new com.meitu.meipaimv.sdk.modelmsg.a();
    MeipaiVideoObject localMeipaiVideoObject = new MeipaiVideoObject();
    localMeipaiVideoObject.a = paramString;
    ((com.meitu.meipaimv.sdk.modelmsg.a)localObject).a(localMeipaiVideoObject);
    paramString = new com.meitu.meipaimv.sdk.modelmsg.b();
    paramString.a((com.meitu.meipaimv.sdk.modelmsg.a)localObject);
    paramString.a(String.valueOf(System.currentTimeMillis()));
    paramString.b(0);
    localObject = com.meitu.meipaimv.sdk.b.d.a(paramContext, "1089867410");
    ((com.meitu.meipaimv.sdk.b.b)localObject).a(new com.meitu.meipaimv.sdk.b.a()
    {
      public void a(String paramAnonymousString)
      {
        i.d("cpy", "errorCall:" + paramAnonymousString);
      }
    });
    if (!((com.meitu.meipaimv.sdk.b.b)localObject).a(com.meitu.meipaimv.sdk.a.d.TYPE_VIDEO))
    {
      paramA.a();
      return;
    }
    ((com.meitu.meipaimv.sdk.b.b)localObject).a((Activity)paramContext, paramString);
  }
  
  public static boolean a(Context paramContext)
  {
    if (paramContext == null) {
      return true;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.equalsIgnoreCase("com.meitu.meipaimv")) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext == null) {
      return true;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equalsIgnoreCase("com.meitu.meipaimv")) && (localPackageInfo.versionCode > 4050)) {
        return true;
      }
    }
    return false;
  }
  
  public static abstract interface a
  {
    public abstract void a();
  }
}
