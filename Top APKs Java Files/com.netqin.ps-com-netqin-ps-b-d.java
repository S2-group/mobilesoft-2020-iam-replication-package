package com.netqin.ps.b;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.provider.Settings.System;
import android.support.v4.app.g;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import com.netqin.exception.NqApplication;
import com.netqin.m;
import com.netqin.n;
import com.netqin.p;
import com.netqin.ps.config.Preferences;
import com.netqin.ps.protocol.pointcard.ActivationHelper;
import com.netqin.t;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class d
{
  public static int a(long paramLong)
  {
    long l = System.currentTimeMillis();
    if (paramLong >= l)
    {
      int i = (int)Math.ceil((paramLong - l) / 8.64E7D);
      bool = t.g;
      return i;
    }
    boolean bool = t.g;
    return -1;
  }
  
  public static String a(String paramString)
  {
    return "md5-" + paramString;
  }
  
  private static String a(String paramString, int paramInt1, int paramInt2)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramInt1 < 0) || (paramInt2 < 0)) {
      return null;
    }
    return paramString.substring(paramInt1, paramInt2);
  }
  
  public static void a(Activity paramActivity)
  {
    if (paramActivity == null) {}
    View localView;
    do
    {
      return;
      localView = paramActivity.getWindow().peekDecorView();
    } while ((localView == null) || (localView.getWindowToken() == null));
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
  }
  
  public static void a(final Activity paramActivity, final Dialog paramDialog)
  {
    int j = 0;
    localD = com.netqin.ps.d.d.a();
    localD.n();
    localD.n();
    localD.j();
    localObject1 = com.netqin.ps.d.d.b();
    localP = new p(paramActivity);
    localP.b = ((String)localObject1);
    localObject1 = paramActivity.getString(2131493612);
    localObject2 = new StringBuffer();
    ((StringBuffer)localObject2).append((String)localObject1).append("(");
    ((StringBuffer)localObject2).append("Vault version:6.7.50.22、");
    ((StringBuffer)localObject2).append("Android version:").append(Build.VERSION.RELEASE).append("、");
    ((StringBuffer)localObject2).append("Phone Model:").append(Build.MODEL).append("、");
    ((StringBuffer)localObject2).append("deviceId").append(com.netqin.localInfo.a.b.d.a().b()).append("、");
    ((StringBuffer)localObject2).append(paramActivity.getString(2131493614)).append(Preferences.getInstance().getUID()).append("、");
    ((StringBuffer)localObject2).append("Country Code:").append(f()).append(")");
    localP.c = ((StringBuffer)localObject2).toString();
    localP.a(paramActivity.getString(2131493611));
    localObject1 = t.u;
    if (a()) {
      localObject1 = t.v;
    }
    for (;;)
    {
      localP.a = ((String)localObject1);
      localP.b("/data/data/com.netqin.ps/databases/Provider_DB");
      localP.b(com.netqin.ps.d.d.b() + "322w465ay423xy11");
      try
      {
        localObject2 = new com.netqin.ps.db.b.e().a();
        localObject1 = "";
        localD.k();
        i = 0;
        for (;;)
        {
          if (i < localObject2.length)
          {
            localObject1 = localD.a(new File(localObject2[i])).toString();
            i += 1;
            continue;
            localObject1 = t.u;
            break;
          }
        }
        localObject2 = new StringBuffer();
        ((StringBuffer)localObject2).append((String)localObject1);
        com.netqin.ps.applock.a.a.a();
        localObject1 = com.netqin.ps.applock.a.a.c();
        int k = ((List)localObject1).size();
        i = 0;
        while (i < k)
        {
          ((StringBuffer)localObject2).append((String)((List)localObject1).get(i));
          ((StringBuffer)localObject2).append("\r\n");
          i += 1;
        }
        localObject1 = NqApplication.b().getPackageManager().getInstalledPackages(0);
        i = j;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          int i;
          try
          {
            PackageInfo localPackageInfo;
            com.netqin.logmanager.a localA;
            boolean bool;
            com.netqin.ps.encrypt.f.a(com.netqin.localInfo.a.b.d.a().b());
            localP.b(com.netqin.logmanager.f.a().a.a);
            localP.b(((File)localObject2).getPath());
            com.netqin.localInfo.a.a.e.a(new com.netqin.localInfo.a.a.a((ArrayList)localObject1)
            {
              public final void a(ArrayList<File> paramAnonymousArrayList)
              {
                int j = paramAnonymousArrayList.size();
                int i = 0;
                while (i < j)
                {
                  File localFile = (File)paramAnonymousArrayList.get(i);
                  if ((localFile != null) && (localFile.isFile())) {
                    localP.b(localFile.getPath());
                  }
                  i += 1;
                }
                paramActivity.runOnUiThread(new Runnable()
                {
                  public final void run()
                  {
                    try
                    {
                      d.2.this.d.dismiss();
                      return;
                    }
                    catch (Exception localException) {}
                  }
                });
                localP.a();
                localD.n();
                localD.j();
                localD.i();
              }
            });
            return;
            localException1 = localException1;
            localException1.printStackTrace();
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
            continue;
          }
          i += 1;
        }
      }
    }
    if (i < ((List)localObject1).size())
    {
      localPackageInfo = (PackageInfo)((List)localObject1).get(i);
      localA = new com.netqin.logmanager.a();
      localA.a = localPackageInfo.applicationInfo.loadLabel(NqApplication.b().getPackageManager()).toString();
      localA.b = localPackageInfo.packageName;
      localA.c = localPackageInfo.versionName;
      localA.d = localPackageInfo.versionCode;
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        ((StringBuffer)localObject2).append(localA.a + "(" + localA.c + ")");
        ((StringBuffer)localObject2).append("\r\n");
        new StringBuilder("tmpInfo.appName:").append(localA.a);
        bool = t.g;
        new StringBuilder("tmpInfo.packageName:").append(localA.b);
        bool = t.g;
        new StringBuilder("tmpInfo.versionName:").append(localA.c);
        bool = t.g;
        new StringBuilder("tmpInfo.versionCode:").append(localA.d);
        bool = t.g;
      }
    }
    else
    {
      localD.e(((StringBuffer)localObject2).toString());
      localObject1 = new ArrayList();
      if (localD.g()) {
        ((ArrayList)localObject1).add(new File(com.netqin.ps.d.d.h()));
      }
      ((ArrayList)localObject1).add(new File(localD.m()));
      ((ArrayList)localObject1).add(new File(localD.l()));
      localObject2 = new File(com.netqin.ps.encrypt.f.a());
      if (((File)localObject2).exists()) {}
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      localIntent1.setFlags(268435456);
      if (Build.VERSION.SDK_INT > 13)
      {
        localIntent1.setClassName("com.google.android.browser", "com.android.browser.BrowserActivity");
        if (paramContext.getPackageManager().resolveActivity(localIntent1, 0) == null) {
          localIntent1.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }
      }
      for (;;)
      {
        paramContext.startActivity(localIntent1);
        return;
        localIntent1.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
      }
      Intent localIntent2;
      return;
    }
    catch (Exception localException)
    {
      localIntent2 = new Intent();
      localIntent2.setAction("android.intent.action.VIEW");
      localIntent2.setData(Uri.parse(paramString));
      localIntent2.setFlags(268435456);
      paramContext.startActivity(localIntent2);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool = b(paramContext, "com.android.vending");
    StringBuffer localStringBuffer = new StringBuffer();
    if (bool)
    {
      localStringBuffer.append("market://details?id=").append(paramString1).append(paramString2);
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuffer.toString()));
      paramString1.setPackage("com.android.vending");
      paramString1.addFlags(268435456);
      paramContext.startActivity(paramString1);
      return;
    }
    localStringBuffer.append("https://play.google.com/store/apps/details?id=").append(paramString1).append(paramString2);
    a(paramContext, localStringBuffer.toString());
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    int j = 0;
    localD = com.netqin.ps.d.d.a();
    localD.n();
    localD.n();
    localD.j();
    localObject1 = com.netqin.ps.d.d.b();
    localP = new p(paramContext);
    localP.b = ((String)localObject1);
    if (paramBoolean) {
      localObject1 = paramContext.getString(2131493613);
    }
    for (;;)
    {
      Object localObject2 = new StringBuffer();
      ((StringBuffer)localObject2).append((String)localObject1).append("(");
      ((StringBuffer)localObject2).append("Vault version:6.7.50.22、");
      ((StringBuffer)localObject2).append("Android version:").append(Build.VERSION.RELEASE).append("、");
      ((StringBuffer)localObject2).append("Phone Model:").append(Build.MODEL).append("、");
      ((StringBuffer)localObject2).append("deviceId").append(com.netqin.localInfo.a.b.d.a().b()).append("、");
      ((StringBuffer)localObject2).append(paramContext.getString(2131493614)).append(Preferences.getInstance().getUID()).append("、");
      ((StringBuffer)localObject2).append("Country Code:").append(f()).append(")");
      localP.c = ((StringBuffer)localObject2).toString();
      localP.a(paramContext.getString(2131493611));
      localP.a = "vault@nq.com";
      localP.b("/data/data/com.netqin.ps/databases/Provider_DB");
      localP.b(com.netqin.ps.d.d.b() + "322w465ay423xy11");
      try
      {
        localObject1 = new com.netqin.ps.db.b.e().a();
        paramContext = "";
        localD.k();
        i = 0;
        for (;;)
        {
          if (i < localObject1.length)
          {
            paramContext = localD.a(new File(localObject1[i])).toString();
            i += 1;
            continue;
            localObject1 = paramContext.getString(2131493612);
            break;
          }
        }
        localObject1 = new StringBuffer();
        ((StringBuffer)localObject1).append(paramContext);
        com.netqin.ps.applock.a.a.a();
        paramContext = com.netqin.ps.applock.a.a.c();
        int k = paramContext.size();
        i = 0;
        while (i < k)
        {
          ((StringBuffer)localObject1).append((String)paramContext.get(i));
          ((StringBuffer)localObject1).append("\r\n");
          i += 1;
        }
        paramContext = NqApplication.b().getPackageManager().getInstalledPackages(0);
        i = j;
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          int i;
          try
          {
            com.netqin.logmanager.a localA;
            com.netqin.ps.encrypt.f.a(com.netqin.localInfo.a.b.d.a().b());
            localP.b(com.netqin.logmanager.f.a().a.a);
            localP.b(((File)localObject1).getPath());
            com.netqin.localInfo.a.a.e.a(new com.netqin.localInfo.a.a.a(paramContext)
            {
              public final void a(ArrayList<File> paramAnonymousArrayList)
              {
                int j = paramAnonymousArrayList.size();
                int i = 0;
                while (i < j)
                {
                  File localFile = (File)paramAnonymousArrayList.get(i);
                  if ((localFile != null) && (localFile.isFile())) {
                    localP.b(localFile.getPath());
                  }
                  i += 1;
                }
                localP.a();
                localD.n();
                localD.j();
                localD.i();
              }
            });
            return;
            paramContext = paramContext;
            paramContext.printStackTrace();
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            continue;
          }
          i += 1;
        }
      }
    }
    if (i < paramContext.size())
    {
      localObject2 = (PackageInfo)paramContext.get(i);
      localA = new com.netqin.logmanager.a();
      localA.a = ((PackageInfo)localObject2).applicationInfo.loadLabel(NqApplication.b().getPackageManager()).toString();
      localA.b = ((PackageInfo)localObject2).packageName;
      localA.c = ((PackageInfo)localObject2).versionName;
      localA.d = ((PackageInfo)localObject2).versionCode;
      if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0)
      {
        ((StringBuffer)localObject1).append(localA.a + "(" + localA.c + ")");
        ((StringBuffer)localObject1).append("\r\n");
        new StringBuilder("tmpInfo.appName:").append(localA.a);
        paramBoolean = t.g;
        new StringBuilder("tmpInfo.packageName:").append(localA.b);
        paramBoolean = t.g;
        new StringBuilder("tmpInfo.versionName:").append(localA.c);
        paramBoolean = t.g;
        new StringBuilder("tmpInfo.versionCode:").append(localA.d);
        paramBoolean = t.g;
      }
    }
    else
    {
      localD.e(((StringBuffer)localObject1).toString());
      paramContext = new ArrayList();
      if (localD.g()) {
        paramContext.add(new File(com.netqin.ps.d.d.h()));
      }
      paramContext.add(new File(localD.m()));
      paramContext.add(new File(localD.l()));
      localObject1 = new File(com.netqin.ps.encrypt.f.a());
      if (((File)localObject1).exists()) {}
    }
  }
  
  private static void a(com.netqin.ps.net.a.b paramB)
  {
    if (!paramB.e("FreeSpace")) {}
    for (;;)
    {
      return;
      paramB = paramB.c("FreeSpace");
      if (!TextUtils.isEmpty(paramB)) {
        try
        {
          float f = Float.parseFloat(paramB);
          if (f >= 0.0F)
          {
            Preferences.getInstance().setFreeSpace(f);
            return;
          }
        }
        catch (NumberFormatException paramB) {}
      }
    }
  }
  
  public static void a(com.netqin.ps.net.a.b paramB, int paramInt)
  {
    Object localObject1 = NqApplication.b();
    Preferences localPreferences = Preferences.getInstance();
    Object localObject2 = String.valueOf(paramInt);
    if (t.g) {
      m.c(new Exception(), " command :" + paramB.b.getAsString("Command"));
    }
    if ((paramB.e("UID")) && (!TextUtils.isEmpty(paramB.c("UID")))) {
      localPreferences.setUID(paramB.c("UID"));
    }
    if (paramB.e("Balance")) {
      localPreferences.setBalance(paramB.c("Balance"));
    }
    if ((paramB.e("Status")) && (localPreferences.getUserStatus() != 2) && ("4102".equals(localObject2)))
    {
      localPreferences.setUserStatus(Integer.parseInt(paramB.c("Status")));
      if (localPreferences.getUserStatus() == 2) {
        localPreferences.setGAReferrer("");
      }
    }
    if (paramB.e("UserPoint")) {
      localPreferences.setUserPoint(paramB.c("UserPoint"));
    }
    if (paramB.e("SecretSmsCount")) {
      localPreferences.setSecretSmsCount(paramB.c("SecretSmsCount"));
    }
    if ("4103".equals(localObject2)) {
      a(paramB);
    }
    String str1;
    String str2;
    long l2;
    long l1;
    if ("4121".equals(localObject2))
    {
      a(paramB);
      if ((paramB.e("NextLinkTime")) && (!TextUtils.isEmpty(paramB.c("NextLinkTime"))))
      {
        localPreferences.setNextLinkInterval(paramB.c("NextLinkTime"));
        str1 = localPreferences.getNextLinkInterval();
        if (t.g) {
          m.a(new Exception(), "Regular Next Link Time :" + str1);
        }
        if (!TextUtils.isEmpty(str1))
        {
          if (str1.indexOf("-") <= 0) {
            break label662;
          }
          paramInt = Integer.parseInt(str1.split("-")[0]);
          String str3 = str1.split("-")[1];
          str1 = a(str3, 0, 2);
          str2 = a(str3, 2, 4);
          str3 = a(str3, 4, 6);
          l2 = n.a(paramInt, str1 + ":" + str2 + ":" + str3);
          l1 = l2;
          if (t.g)
          {
            m.a(new Exception(), "Get Next Link Time :" + l2);
            l1 = l2;
          }
          localPreferences.setNextLinkTimeMillisRegular(l1);
          b.a((Context)localObject1, "android.intent.action.REGULAR", l1);
        }
      }
    }
    if (paramB.e("SecretSpaceUsable"))
    {
      if (!paramB.c("SecretSpaceUsable").equals("1")) {
        break label719;
      }
      localPreferences.setPrivateSpaceUsable(true);
    }
    for (;;)
    {
      if (!paramB.e("Module")) {
        break label728;
      }
      if (t.g) {
        m.c(new Exception(), " save file");
      }
      paramInt = 0;
      while (paramInt < paramB.d("Module"))
      {
        str1 = paramB.a("Module", paramInt, "version");
        str2 = paramB.a("Module", paramInt, "name");
        m.c(new Exception(), " save file");
        if ((!"itp".equals(str2)) && (!"rn".equals(str2)) && ("os".equals(str2))) {
          localPreferences.setOSVersion(str1);
        }
        paramInt += 1;
      }
      label662:
      l2 = n.a(Integer.parseInt(str1), n.e());
      l1 = l2;
      if (!t.g) {
        break;
      }
      m.a(new Exception(), "Get Next Link Time 2 :" + l2);
      l1 = l2;
      break;
      label719:
      localPreferences.setPrivateSpaceUsable(false);
    }
    label728:
    if ((paramB.e("Level")) && (!TextUtils.isEmpty(paramB.c("Level"))) && (!"4100".equals(localObject2)))
    {
      paramInt = Integer.parseInt(paramB.c("Level"));
      if ((paramInt == 32) || (paramInt == 1) || (paramInt == 4))
      {
        if (paramInt != 32) {
          break label1293;
        }
        if (localPreferences.getNewUserLevel() != 32)
        {
          if (localPreferences.getFreeSpace() > 0.0F) {
            Integer.parseInt(localPreferences.getUID());
          }
          localPreferences.setIsRemindOutOfDate(true);
          localPreferences.setPremiumWarningClicked(false);
          new ActivationHelper();
          if (!ActivationHelper.b()) {
            break label1048;
          }
          if (!com.netqin.ps.xp.a.a(paramB)) {
            break label1029;
          }
          ActivationHelper.b((Context)localObject1);
          ActivationHelper.g();
        }
      }
    }
    for (;;)
    {
      if ((paramB.e("LevelName")) && (!TextUtils.isEmpty(paramB.c("LevelName")))) {
        localPreferences.setUserLevelName(paramB.c("LevelName"));
      }
      localPreferences.setNewUserLevel(paramInt);
      if (t.g) {
        m.a(new Exception(), "Set UserLevel :" + paramInt);
      }
      if ((paramB.e("SecretSmsInfo")) && (!TextUtils.isEmpty(paramB.c("SecretSmsInfo")))) {
        localPreferences.setSecretSmsCount(Integer.parseInt(paramB.c("SecretSmsInfo")));
      }
      new ActivationHelper();
      if (paramB.e("VipInfo")) {
        ActivationHelper.a(paramB, "ExpiredDate", "PayUrl");
      }
      if (paramB.e("Binding")) {
        localPreferences.setMemberMoveBinding(paramB.c("Binding"));
      }
      return;
      label1029:
      ActivationHelper.b((Context)localObject1);
      ActivationHelper.a((Context)localObject1, ActivationHelper.a, 2131494174);
      break;
      label1048:
      if (com.netqin.ps.xp.a.a(paramB))
      {
        Preferences.getInstance().setServiceExpired(-1);
        localObject1 = (NotificationManager)((Context)localObject1).getSystemService("notification");
        ((NotificationManager)localObject1).cancel(2131494114);
        ((NotificationManager)localObject1).cancel(2131493947);
      }
      else
      {
        localObject2 = new Preferences();
        if (((Preferences)localObject2).getNewUserLevel() == 1) {
          ((Preferences)localObject2).setServiceExpired(0);
        }
        for (;;)
        {
          if (t.g) {
            m.b(new Exception(), "mPref.getServiceExpired():" + ((Preferences)localObject2).getServiceExpired());
          }
          if (((Preferences)localObject2).getServiceExpired() == -1) {
            break;
          }
          ((Preferences)localObject2).getFreeSpace();
          ((NotificationManager)((Context)localObject1).getSystemService("notification")).cancel(2131494114);
          localObject2 = new Intent();
          ((Intent)localObject2).putExtra("for.publicdata.receiver", "android.intent.action.public.serviceexpired");
          ((Intent)localObject2).putExtra("for_vip_activity", 2131494113);
          ((Intent)localObject2).putExtra("BACKGUARD", true);
          ((Intent)localObject2).setAction("android.intent.action.public");
          localObject1 = PendingIntent.getBroadcast((Context)localObject1, 0, (Intent)localObject2, 268435456);
          com.netqin.ps.h.a.c(NqApplication.b().getString(2131493947), (PendingIntent)localObject1);
          t.R = 1;
          break;
          if (((Preferences)localObject2).getNewUserLevel() == 4) {
            ((Preferences)localObject2).setServiceExpired(1);
          }
        }
        label1293:
        localPreferences.setServiceExpired(-1);
      }
    }
  }
  
  public static boolean a()
  {
    return Preferences.getInstance().getNewUserLevel() != 32;
  }
  
  public static boolean a(int paramInt)
  {
    return paramInt >= 108;
  }
  
  public static boolean a(Context paramContext)
  {
    return b(paramContext) == 1;
  }
  
  public static int b(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0);
  }
  
  public static String b(String paramString)
  {
    int i = paramString.indexOf("md5-");
    if (i < 0) {
      return paramString;
    }
    return paramString.substring(i + 4, paramString.length());
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    if (!b(paramContext, paramString1))
    {
      boolean bool = b(paramContext, "com.android.vending");
      StringBuffer localStringBuffer = new StringBuffer();
      if (bool)
      {
        localStringBuffer.append("market://details?id=").append(paramString1).append(paramString2);
        paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuffer.toString()));
        paramString1.setPackage("com.android.vending");
        paramString1.addFlags(268435456);
        paramContext.startActivity(paramString1);
        return;
      }
      localStringBuffer.append("https://play.google.com/store/apps/details?id=").append(paramString1).append(paramString2);
      a(paramContext, localStringBuffer.toString());
      return;
    }
    paramString1 = paramContext.getPackageManager().getLaunchIntentForPackage(paramString1);
    paramString1.setFlags(337641472);
    paramContext.startActivity(paramString1);
  }
  
  public static boolean b()
  {
    return (Preferences.getInstance().isUseCalculateTheme()) && (i());
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
        new StringBuilder("appInfo.processName =  ").append(paramContext.processName);
        boolean bool = t.g;
        if (paramContext != null) {
          return true;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
    }
    return false;
  }
  
  public static boolean c()
  {
    return (b(NqApplication.b(), "com.android.vending")) && (!i()) && (!Preferences.getInstance().isClickRemindCalculator()) && (Build.VERSION.SDK_INT >= 15);
  }
  
  public static boolean c(Context paramContext)
  {
    int i = d(paramContext);
    return Preferences.getInstance().getShowedWhatsNewVersion() < i;
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 16384).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static boolean d()
  {
    String str = e();
    return (str.startsWith("417")) || (str.startsWith("634")) || (str.startsWith("368")) || (str.startsWith("432")) || (str.startsWith("605")) || (str.startsWith("619")) || (str.startsWith("645")) || (str.startsWith("629")) || (str.startsWith("630"));
  }
  
  public static String e()
  {
    if (t.f)
    {
      if (android.support.v4.content.a.a(NqApplication.b(), (String)com.netqin.utility.c.b.get(0)) != 0) {
        boolean bool = t.g;
      }
      for (String str1 = null;; str1 = n.b(NqApplication.b()))
      {
        String str2 = str1;
        if (str1 == null) {
          str2 = "";
        }
        return str2;
      }
    }
    return t.L;
  }
  
  public static String f()
  {
    String str = e();
    if (TextUtils.isEmpty(str)) {
      return "";
    }
    return str.substring(0, 3);
  }
  
  public static boolean g()
  {
    return "37".equals(n.b());
  }
  
  public static boolean h()
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return (com.swipe.b.a().e()) && (Settings.canDrawOverlays(NqApplication.b()));
    }
    return com.swipe.b.a().e();
  }
  
  private static boolean i()
  {
    return b(NqApplication.b(), "com.nqmobile.calculator");
  }
}
