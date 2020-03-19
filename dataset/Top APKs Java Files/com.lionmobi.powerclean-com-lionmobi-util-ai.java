package com.lionmobi.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.lionmobi.powerclean.c.b;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ai
{
  public static Set getIgnoreSysPkg(Context paramContext)
  {
    localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(0);
      int j = paramContext.size();
      List localList = b.getIgnoreSysProcessList();
      int i = 0;
      while (i < j)
      {
        ApplicationInfo localApplicationInfo = ((PackageInfo)paramContext.get(i)).applicationInfo;
        if (((localApplicationInfo.flags & 0x1) > 0) && (!localList.contains(localApplicationInfo.packageName))) {
          localHashSet.add(localApplicationInfo.packageName);
        }
        i += 1;
      }
      return localHashSet;
    }
    catch (Exception paramContext) {}
  }
  
  public static Set getIgnore_NoShow_Pkg()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("com.android.MtpApplication");
    localHashSet.add("com.android.bluetooth");
    localHashSet.add("com.android.contacts");
    localHashSet.add("com.android.dolbymobileaudioeffect");
    localHashSet.add("com.android.inputmethod.latin");
    localHashSet.add("com.android.keyguard");
    localHashSet.add("com.android.launcher");
    localHashSet.add("com.android.mms");
    localHashSet.add("com.android.nfc");
    localHashSet.add("com.android.providers.applications");
    localHashSet.add("com.android.providers.security");
    localHashSet.add("com.android.providers.telephony");
    localHashSet.add("com.android.server.device.enterprise");
    localHashSet.add("com.android.server.vpn.enterprise");
    localHashSet.add("com.android.smspush");
    localHashSet.add("com.android.sr");
    localHashSet.add("com.android.stk");
    localHashSet.add("com.android.systemui");
    localHashSet.add("com.baidu.input");
    localHashSet.add("com.bel.android.dspmanager");
    localHashSet.add("com.broadcom.bt.app.system");
    localHashSet.add("com.ctc.epush");
    localHashSet.add("com.cyanogenmod.cmparts");
    localHashSet.add("com.cyanogenmod.trebuchet");
    localHashSet.add("com.dolby");
    localHashSet.add("com.dsi.ant.server");
    localHashSet.add("com.google.android.inputmethod.pinyin");
    localHashSet.add("com.google.newbeetouch");
    localHashSet.add("com.htc.android.htcime");
    localHashSet.add("com.htc.contacts");
    localHashSet.add("com.huawei.android.launcher");
    localHashSet.add("com.huawei.android.remotecontrol");
    localHashSet.add("com.huawei.pmqos");
    localHashSet.add("com.huawei.powersavingmode");
    localHashSet.add("com.iflytek.inputmethod.pad");
    localHashSet.add("com.lbe.security.miui");
    localHashSet.add("com.lewa.birdview");
    localHashSet.add("com.lewa.launcher5");
    localHashSet.add("com.lewa.lipservice");
    localHashSet.add("com.lewa.providers.sensor");
    localHashSet.add("com.mediatek.bluetooth");
    localHashSet.add("com.mediatek.voicecommand");
    localHashSet.add("com.miui.antispam");
    localHashSet.add("com.miui.home");
    localHashSet.add("com.miui.mihome2");
    localHashSet.add("com.miui.whetstone");
    localHashSet.add("com.newbee.datausage");
    localHashSet.add("com.newbee.optimize");
    localHashSet.add("com.newbee.settings");
    localHashSet.add("com.samsung.android.app.galaxyfinder");
    localHashSet.add("com.samsung.android.providers.context");
    localHashSet.add("com.samsung.android.writingbuddyservice");
    localHashSet.add("com.samsung.indexservice");
    localHashSet.add("com.samsung.inputmethod");
    localHashSet.add("com.sec.android.Kies");
    localHashSet.add("com.sec.android.app.FlashBarService");
    localHashSet.add("com.sec.android.app.bluetoothtest");
    localHashSet.add("com.sec.android.app.clockpackage");
    localHashSet.add("com.sec.android.app.keyguard");
    localHashSet.add("com.sec.android.app.launcher");
    localHashSet.add("com.sec.android.app.phoneutil");
    localHashSet.add("com.sec.android.app.popupuireceiver");
    localHashSet.add("com.sec.android.app.servicemodeapp");
    localHashSet.add("com.sec.android.app.tmserver");
    localHashSet.add("com.sec.android.app.twdvfs");
    localHashSet.add("com.sec.android.daemonapp.ap.sinastock.stockclock");
    localHashSet.add("com.sec.android.inputmethod");
    localHashSet.add("com.sec.android.pagebuddynotisvc");
    localHashSet.add("com.sec.android.provider.logsprovider");
    localHashSet.add("com.sec.android.providers.downloads");
    localHashSet.add("com.sec.android.sCloudRelayData");
    localHashSet.add("com.sec.android.signaturelock");
    localHashSet.add("com.sec.android.widgetapp.alarmclock");
    localHashSet.add("com.sec.android.widgetapp.ap.hero.sinaweather");
    localHashSet.add("com.sec.android.widgetapp.ap.hero.sinaweather.widget");
    localHashSet.add("com.sec.android.widgetapp.dualclockanalog");
    localHashSet.add("com.sec.ccl.csp.app.secretwallpaper.themetwo");
    localHashSet.add("com.sec.factory");
    localHashSet.add("com.sec.knox.containeragent");
    localHashSet.add("com.sec.knox.eventsmanager");
    localHashSet.add("com.sec.knox.seandroid");
    localHashSet.add("com.sec.msc.nts.android.proxy");
    localHashSet.add("com.sec.phone");
    localHashSet.add("com.sec.setdefaultbrowser");
    localHashSet.add("com.sec.spp.push");
    localHashSet.add("com.sohu.inputmethod.sogou");
    localHashSet.add("com.sohu.inputmethod.sogouoem");
    localHashSet.add("com.tencent.qqpinyin");
    localHashSet.add("com.tencent.qrom.power");
    localHashSet.add("com.teslacoilsw.launcher");
    localHashSet.add("com.tgrape.android.radar");
    localHashSet.add("com.tmobile.themechooser");
    localHashSet.add("com.wssnps");
    localHashSet.add("com.xiaomi.mitunes");
    localHashSet.add("com.xiaomi.xmsf");
    localHashSet.add("com.yulong.android.contacts");
    localHashSet.add("com.yulong.android.coolpadime");
    localHashSet.add("com.yulong.android.dev.gcoption");
    localHashSet.add("com.yulong.android.launcher3");
    localHashSet.add("org.codeaurora.bluetooth");
    localHashSet.add("org.simalliance.openmobileapi.service");
    localHashSet.add("com.bullmobi.powerscan");
    return localHashSet;
  }
  
  public static Set getMustIgnoreSysPkg()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("android");
    localHashSet.add("system");
    localHashSet.add("com.android.phone");
    localHashSet.add("com.android.smspush");
    return localHashSet;
  }
}
