package com.rsupport.rs.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;
import od;
import pl;
import qq;
import sj;
import sk;
import sl;
import sm;
import vf;

public class SystemEventReceiver
  extends BroadcastReceiver
{
  private static Hashtable jdField_a_of_type_JavaUtilHashtable = null;
  private Handler jdField_a_of_type_AndroidOsHandler = new sj(this);
  private final String jdField_a_of_type_JavaLangString = "SystemEventReceiver";
  
  public SystemEventReceiver() {}
  
  private static void a(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.putExtra("REBOOT", true);
    localIntent.addFlags(329252864);
    localIntent.setComponent(new ComponentName(paramContext.getPackageName(), "com.rsupport.rs.activity.edit.AutoConnActivity"));
    paramContext.startActivity(localIntent);
  }
  
  private static void a(PackageManager paramPackageManager, Hashtable paramHashtable)
  {
    vf.c("SystemEventReceiver", "reloadApplicationInfo");
    List localList;
    int j;
    int i;
    if (paramHashtable != null)
    {
      paramHashtable.clear();
      localList = paramPackageManager.getInstalledApplications(128);
      j = localList.size();
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
      paramHashtable.put(localApplicationInfo.packageName, localApplicationInfo.loadLabel(paramPackageManager).toString());
      i += 1;
    }
  }
  
  private static void a(String paramString)
  {
    if (paramString != null) {}
    for (;;)
    {
      try
      {
        paramString = paramString.getBytes("UTF-16LE");
        byte[] arrayOfByte = new byte[paramString.length + 2];
        System.arraycopy(paramString, 0, arrayOfByte, 0, paramString.length);
        paramString = arrayOfByte;
        qq.a(null).a.a(231, 25, paramString, paramString.length);
        return;
      }
      catch (UnsupportedEncodingException paramString)
      {
        paramString.printStackTrace();
        paramString = null;
        continue;
      }
      paramString = null;
    }
  }
  
  private static boolean a(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("reconnect.info", 0);
    String str = paramContext.getString("connurl", "");
    long l = paramContext.getLong("timelife", 0L);
    if (System.currentTimeMillis() - l > 1800000L) {}
    while (str.equals("")) {
      return false;
    }
    return true;
  }
  
  private static boolean a(Intent paramIntent)
  {
    boolean bool = false;
    if (("android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_REMOVED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_CHANGED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_RESTARTED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_INSTALL".equals(paramIntent.getAction())) || (paramIntent.getAction().equals("android.intent.action.rsupport.connected"))) {
      bool = true;
    }
    return bool;
  }
  
  private void b(Context paramContext, Intent paramIntent)
  {
    new sm(this, paramContext, paramIntent).start();
  }
  
  private void b(boolean paramBoolean)
  {
    new sl(this, paramBoolean).start();
  }
  
  private static void c(Context paramContext, Intent paramIntent)
  {
    vf.c("SystemEventReceiver", "packageMonitor @ intent : " + paramIntent.getAction());
    if (qq.a(null) == null) {}
    int i;
    do
    {
      do
      {
        return;
      } while ((qq.a(null).a == null) || (!qq.b));
      i = 0;
      if (("android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_REMOVED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_CHANGED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_RESTARTED".equals(paramIntent.getAction())) || ("android.intent.action.PACKAGE_INSTALL".equals(paramIntent.getAction())) || (paramIntent.getAction().equals("android.intent.action.rsupport.connected"))) {
        i = 1;
      }
    } while ((i == 0) || (qq.a()));
    if (jdField_a_of_type_JavaUtilHashtable == null) {
      jdField_a_of_type_JavaUtilHashtable = new Hashtable();
    }
    if ("android.intent.action.PACKAGE_REMOVED".equals(paramIntent.getAction()))
    {
      paramContext = paramIntent.getData().getSchemeSpecificPart();
      if (jdField_a_of_type_JavaUtilHashtable.containsKey(paramContext.trim()))
      {
        paramIntent = (String)jdField_a_of_type_JavaUtilHashtable.remove(paramContext);
        a(paramContext);
        od.a().a(4, paramIntent);
        return;
      }
      a(paramContext);
      od.a().a(4, paramContext);
      return;
    }
    a(paramContext.getPackageManager(), jdField_a_of_type_JavaUtilHashtable);
  }
  
  private static void c(boolean paramBoolean)
  {
    if (!qq.b) {
      return;
    }
    if (paramBoolean)
    {
      qq.a(null).a.a(231, 208);
      return;
    }
    qq.a(null).a.a(231, 209);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = 1;
    int j = 0;
    try
    {
      vf.c("SystemEventReceiver", paramIntent.getAction());
      new sm(this, paramContext, paramIntent).start();
      if (("android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction())) && (qq.a(null) != null) && (qq.a(null).a != null) && (qq.b))
      {
        if (qq.a()) {
          return;
        }
        vf.c("SystemEventReceiver", "ACTION_PACKAGE_ADDED : " + paramIntent.getData().getSchemeSpecificPart());
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    String str;
    if (("android.intent.action.PACKAGE_INSTALL".equals(paramIntent.getAction())) && (qq.a(null) != null) && (qq.a(null).a != null) && (qq.b))
    {
      if (!qq.a()) {
        vf.c("SystemEventReceiver", "ACTION_PACKAGE_INSTALL : " + paramIntent.getData().getSchemeSpecificPart());
      }
    }
    else if (!"android.intent.action.INPUT_METHOD_CHANGED".equals(paramIntent.getAction())) {
      if (("android.intent.action.LOCALE_CHANGED".equals(paramIntent.getAction())) && (qq.a(null) != null) && (qq.a(null).a != null))
      {
        vf.c("SystemEventReceiver", "ACTION_LOCALE_CHANGED");
        if (!qq.a()) {
          new Thread(new sk(this)).start();
        }
      }
      else if (("android.intent.action.PACKAGE_RESTARTED".equals(paramIntent.getAction())) && (qq.a(null) != null) && (qq.a(null).a != null) && (qq.b))
      {
        if (!qq.a()) {
          vf.c("SystemEventReceiver", "ACTION_PACKAGE_RESTARTED : " + paramIntent.getData().getSchemeSpecificPart());
        }
      }
      else if (paramIntent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
      {
        paramIntent = paramContext.getSharedPreferences("reconnect.info", 0);
        str = paramIntent.getString("connurl", "");
        long l = paramIntent.getLong("timelife", 0L);
        if (System.currentTimeMillis() - l > 1800000L) {
          i = j;
        }
      }
    }
    label551:
    for (;;)
    {
      if (i != 0)
      {
        paramIntent = new Intent("android.intent.action.MAIN");
        paramIntent.setAction("android.intent.action.MAIN");
        paramIntent.putExtra("REBOOT", true);
        paramIntent.addFlags(329252864);
        paramIntent.setComponent(new ComponentName(paramContext.getPackageName(), "com.rsupport.rs.activity.edit.AutoConnActivity"));
        paramContext.startActivity(paramIntent);
        return;
        if (!str.equals(""))
        {
          break label551;
          if ((paramIntent.getAction().equals("android.intent.action.ACTION_SHUTDOWN")) && (qq.a(null) != null) && (qq.a(null).a != null))
          {
            paramContext = qq.a(null).a;
            pl.e();
            return;
          }
          if (paramIntent.getAction().equals("android.intent.action.SCREEN_ON"))
          {
            b(true);
            return;
          }
          if (paramIntent.getAction().equals("android.intent.action.SCREEN_OFF"))
          {
            b(false);
            return;
          }
          paramIntent.getAction().equals("android.intent.action.BATTERY_CHANGED");
        }
        else
        {
          i = 0;
        }
      }
      else
      {
        return;
      }
    }
  }
}
