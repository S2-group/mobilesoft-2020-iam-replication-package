package com.babybus.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.PatchProxyResult;
import java.util.List;

public class WindowUtil
{
  public static ChangeQuickRedirect changeQuickRedirect;
  
  public WindowUtil() {}
  
  public static boolean hasAnyMarketInstalled(Context paramContext)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramContext }, null, (ChangeQuickRedirect)localObject, true, "hasAnyMarketInstalled(Context)", new Class[] { Context.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    localObject = new Intent();
    ((Intent)localObject).setData(Uri.parse("market://details?id=android.browser"));
    return paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 65536).size() != 0;
  }
  
  public static boolean isAppInstalled(Activity paramActivity, String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramActivity, paramString }, null, (ChangeQuickRedirect)localObject, true, "isAppInstalled(Activity,String)", new Class[] { Activity.class, String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    if ((paramString != null) && (!"".equals(paramString)))
    {
      paramActivity = paramActivity.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < paramActivity.size())
      {
        if (paramString.endsWith(((PackageInfo)paramActivity.get(i)).packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static void launchApp(Activity paramActivity, String paramString)
  {
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramActivity, paramString }, null, localChangeQuickRedirect, true, "launchApp(Activity,String)", new Class[] { Activity.class, String.class }, localClass).isSupported) {
      return;
    }
    launchApp(paramActivity, paramString, true);
  }
  
  public static void launchApp(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static void openMarketToDownload(final Activity paramActivity, String paramString)
  {
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramActivity, paramString }, null, localChangeQuickRedirect, true, "openMarketToDownload(Activity,String)", new Class[] { Activity.class, String.class }, localClass).isSupported) {
      return;
    }
    try
    {
      paramActivity.runOnUiThread(new Runnable()
      {
        public static ChangeQuickRedirect changeQuickRedirect;
        
        public void run()
        {
          Object localObject1 = changeQuickRedirect;
          Object localObject2 = Void.TYPE;
          if (PatchProxy.proxy(new Object[0], this, (ChangeQuickRedirect)localObject1, false, "run()", new Class[0], (Class)localObject2).isSupported) {
            return;
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("market://details?id=");
          ((StringBuilder)localObject1).append(this.val$appKey.trim());
          localObject1 = Uri.parse(((StringBuilder)localObject1).toString());
          if (WindowUtil.hasAnyMarketInstalled(paramActivity))
          {
            if (ShellCmdBuilder.isCmdStartActivity())
            {
              ShellCmdBuilder.get().setAction("android.intent.action.VIEW").addFlags(268435456).setDataUri(((Uri)localObject1).toString()).execShellCmd();
              return;
            }
            localObject1 = new Intent("android.intent.action.VIEW", (Uri)localObject1);
            ((Intent)localObject1).setFlags(268435456);
            paramActivity.startActivity((Intent)localObject1);
            return;
          }
          try
          {
            if (ShellCmdBuilder.isCmdStartActivity())
            {
              ShellCmdBuilder.get().setAction("android.intent.action.VIEW").addFlags(268435456).setDataUri(((Uri)localObject1).toString()).execShellCmd();
              return;
            }
            localObject2 = new Intent();
            ((Intent)localObject2).setAction("android.intent.action.VIEW");
            ((Intent)localObject2).setFlags(268435456);
            ((Intent)localObject2).setData((Uri)localObject1);
            paramActivity.startActivity((Intent)localObject2);
            return;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
          Toast.makeText(paramActivity, "Browser does not open.", 0).show();
        }
      });
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
}
