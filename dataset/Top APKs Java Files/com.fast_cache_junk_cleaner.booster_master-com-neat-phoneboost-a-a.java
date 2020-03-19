package com.neat.phoneboost.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a
{
  private static Context a;
  private static a b;
  private Set<String> c = new HashSet();
  private Set<String> d = new HashSet();
  
  private a() {}
  
  public static a a(Context paramContext)
  {
    try
    {
      if (b == null)
      {
        a = paramContext;
        b = new a();
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  public static Set<String> b()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("android");
    localHashSet.add("system");
    localHashSet.add("com.android.phone");
    localHashSet.add("com.android.smspush");
    localHashSet.add("com.fast_cache_junk_cleaner.booster_master");
    return localHashSet;
  }
  
  public static Set<String> b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager();
    int i = 0;
    try
    {
      paramContext = paramContext.getInstalledPackages(0);
      List localList = d();
      while (i < paramContext.size())
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
    return localHashSet;
  }
  
  public static Set<String> c()
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
    localHashSet.add("com.google.android.gms");
    return localHashSet;
  }
  
  private static List<String> d()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("com.kingroot.kinguser");
    localArrayList.add("com.lbe.security.miui");
    localArrayList.add("com.miui.networkassistant");
    localArrayList.add("com.svox.pico");
    localArrayList.add("com.miui.bugreport");
    localArrayList.add("com.android.providers.media");
    localArrayList.add("com.android.fileexplorer");
    localArrayList.add("com.android.calendar");
    localArrayList.add("com.android.providers.calendar");
    localArrayList.add("com.android.quicksearchbox");
    localArrayList.add("com.xiaomi.gamecenter");
    localArrayList.add("com.android.musicfx");
    localArrayList.add("com.android.vending");
    localArrayList.add("com.google.android.gms");
    localArrayList.add("com.android.defcontainer");
    localArrayList.add("com.google.android.partnersetup");
    localArrayList.add("com.android.cellbroadcastreceiver");
    localArrayList.add("com.miui.video");
    localArrayList.add("com.android.updater");
    localArrayList.add("com.miui.gallery");
    localArrayList.add("com.xiaomi.market");
    localArrayList.add("com.miui.cloudservice");
    localArrayList.add("com.android.settings");
    localArrayList.add("com.android.browser");
    localArrayList.add("com.miui.player");
    localArrayList.add("com.miui.guardprovider");
    localArrayList.add("org.cyanogenmod.theme.chooser");
    localArrayList.add("org.cyanogenmod.themes.provider");
    localArrayList.add("org.cyanogenmod.voiceplus");
    localArrayList.add("com.android.camera2");
    localArrayList.add("com.cyanogenmod.lockclock");
    localArrayList.add("com.android.deskclock");
    localArrayList.add("com.android.gallery3d");
    localArrayList.add("ch.smalltech.battery.free");
    localArrayList.add("com.android.keychain");
    localArrayList.add("com.android.chrome");
    localArrayList.add("com.qualcomm.qcrilmsgtunnel");
    localArrayList.add("com.sina.VDisk");
    localArrayList.add("com.google.android.apps.genie.geniewidget");
    localArrayList.add("com.miui.providers.datahub");
    localArrayList.add("com.lewa.virusdefense");
    localArrayList.add("com.lewa.permmanager");
    localArrayList.add("com.lewa.PIM");
    localArrayList.add("com.lewa.player");
    localArrayList.add("com.lewa.spm");
    localArrayList.add("com.lewa.updater");
    localArrayList.add("com.lewa.gellary3d");
    localArrayList.add("com.lewa.netmgr");
    localArrayList.add("com.lewa.antitheft");
    localArrayList.add("com.android.providers.drm");
    localArrayList.add("com.google.android.gsf.login");
    localArrayList.add("com.google.android.apps.uploader");
    localArrayList.add("com.lewa.systemclean");
    localArrayList.add("com.lewa.thememanager");
    localArrayList.add("com.google.android.inputmethod.latin.dictionarypack");
    localArrayList.add("com.mediatek.atci.service");
    localArrayList.add("com.mediatek.batterywarning");
    localArrayList.add("com.huawei.systemmanager");
    localArrayList.add("com.baidu.BaiduMap");
    localArrayList.add("com.android.mediacenter");
    localArrayList.add("com.huawei.appmarket");
    localArrayList.add("com.baidu.map.location");
    localArrayList.add("com.huawei.lcagent");
    localArrayList.add("com.huawei.android.multiscreen");
    localArrayList.add("org.simalliance.openmobileapi.service");
    localArrayList.add("com.huawei.bd");
    localArrayList.add("com.huawei.android.mewidget");
    localArrayList.add("com.akazam.android.wlandialer");
    localArrayList.add("com.huawei.motionservice");
    localArrayList.add("com.huawei.numberlocation");
    localArrayList.add("com.gsma.services.nfc");
    localArrayList.add("com.huawei.DiagReboot");
    localArrayList.add("com.huawei.android.thememanager");
    localArrayList.add("com.instagram.android");
    localArrayList.add("com.trafficctr.miui");
    localArrayList.add("com.xiaomi.gamecenter.pad");
    localArrayList.add("com.xiaomi.dm");
    localArrayList.add("com.miui.securitycenter");
    localArrayList.add("eu.chainfire.supersu");
    localArrayList.add("com.android.thememanager");
    localArrayList.add("com.nvidia.NvWFDSvc");
    localArrayList.add("com.nvwfd.server.internal.protocols");
    localArrayList.add("de.robv.android.xposed.installer");
    localArrayList.add("com.sec.android.app.sbrowser");
    localArrayList.add("com.sec.android.gallery3d");
    localArrayList.add("tv.peel.samsung.app");
    localArrayList.add("com.samsung.android.MtpApplication");
    localArrayList.add("com.sec.factory");
    localArrayList.add("com.sec.pcw");
    localArrayList.add("com.samsung.android.app.pinboard");
    localArrayList.add("com.samsung.android.snote");
    localArrayList.add("com.sec.android.daemonapp");
    localArrayList.add("com.sec.android.app.shealth");
    localArrayList.add("com.sec.android.service.health.sensor");
    localArrayList.add("com.sec.android.fotaclient");
    localArrayList.add("com.policydm");
    localArrayList.add("com.sec.android.app.videoplayer");
    localArrayList.add("com.visionobjects.resourcemanager");
    localArrayList.add("com.sec.android.app.controlpanel");
    localArrayList.add("com.sec.android.app.music");
    localArrayList.add("com.android.exchange");
    localArrayList.add("com.samsung.android.scloud.backup");
    localArrayList.add("com.sec.android.app.myfiles");
    localArrayList.add("com.sec.android.app.parser");
    localArrayList.add("com.samsung.helphub");
    localArrayList.add("com.samsung.indexservice");
    localArrayList.add("com.samsung.android.app.filterinstaller");
    localArrayList.add("com.sec.spp.push");
    localArrayList.add("com.sec.android.cloudagent");
    localArrayList.add("com.samsung.android.app.gestureservice");
    localArrayList.add("com.sec.android.app.popupuireceiver");
    localArrayList.add("com.android.browser.provider");
    localArrayList.add("com.sec.android.app.SPenKeeper");
    localArrayList.add("com.sec.kidsplat.installer");
    localArrayList.add("com.sec.android.Kies");
    localArrayList.add("sstream.app");
    localArrayList.add("com.google.android.apps.maps");
    localArrayList.add("com.birdstep.android.cm");
    localArrayList.add("com.google.android.googlequicksearchbox");
    localArrayList.add("com.lge.smartshare");
    localArrayList.add("com.lge.appbox.client");
    localArrayList.add("com.lge.email");
    localArrayList.add("com.leg.music");
    localArrayList.add("com.lgespellcheckerjb.inputmethod.latin");
    localArrayList.add("com.lge.sizechangable.musicwidget.widget");
    localArrayList.add("com.lge.dmf.admin");
    localArrayList.add("com.locationlabs.v3client");
    localArrayList.add("com.google.android.apps.sem");
    localArrayList.add("com.lge.lockscreensettings");
    localArrayList.add("com.android.email");
    localArrayList.add("com.lbe.security.miui");
    localArrayList.add("com.miui.cloudappbackup");
    localArrayList.add("com.android.packageinstaller");
    localArrayList.add("com.cleanmaster.sdk");
    localArrayList.add("com.xiaomi.account");
    localArrayList.add("com.android.documentsui");
    localArrayList.add("com.android.externalstorage");
    localArrayList.add("com.osp.app.signin");
    localArrayList.add("com.android.ota");
    localArrayList.add("com.autonavi.xmgd.navigator.keyboard");
    localArrayList.add("com.fmm.dm");
    localArrayList.add("com.fmm.ds");
    localArrayList.add("com.sec.android.app.voicerecorder");
    localArrayList.add("com.sec.android.daemonapp.ap.sinanews");
    localArrayList.add("com.android.simcardmanagement");
    localArrayList.add("com.baidu.searchbox_samsung");
    localArrayList.add("com.sec.android.app.FileShareServer");
    localArrayList.add("com.diotek.mini_penmemo");
    localArrayList.add("com.sec.android.provider.badge");
    localArrayList.add("com.sec.android.sCloudBackupApp");
    localArrayList.add("com.sec.android.favoriteappwidget");
    localArrayList.add("com.sec.android.sCloudSync");
    localArrayList.add("com.sec.android.app.camera");
    localArrayList.add("com.sec.android.sCloudRelayData");
    localArrayList.add("com.samsung.map");
    localArrayList.add("com.samsung.shareshot");
    localArrayList.add("com.sec.pcw.device");
    localArrayList.add("com.sec.android.app.GlanceView");
    localArrayList.add("com.sec.android.service.cm");
    localArrayList.add("com.samsung.scrc.idi.server");
    localArrayList.add("com.google.android.music");
    localArrayList.add("com.google.android.apps.docs");
    localArrayList.add("com.google.android.keep");
    localArrayList.add("com.taobao.trip");
    localArrayList.add("com.yulong.android.savepowermanager");
    localArrayList.add("com.yulong.android.seccenter");
    localArrayList.add("com.android.settings.wifi");
    localArrayList.add("com.yulong.android.blacklist");
    localArrayList.add("com.yulong.ttwindow");
    localArrayList.add("com.yulong.android.coolshow");
    localArrayList.add("com.yulong.android.coolyou");
    localArrayList.add("com.icoolme.android.upgrade");
    localArrayList.add("com.yulong.android.softmanager");
    localArrayList.add("com.yulong.android.calendar.widget");
    localArrayList.add("com.android.coolwind");
    localArrayList.add("com.yulong.android.network.widget");
    localArrayList.add("com.yulong.android.bugreport.client");
    localArrayList.add("com.yulong.android.showwidget");
    localArrayList.add("com.yulong.android.xtimewidget");
    localArrayList.add("com.android.usbui");
    localArrayList.add("com.sonyericsson.music");
    localArrayList.add("com.sonymobile.dlna");
    localArrayList.add("com.sonyericsson.android.camera");
    localArrayList.add("com.sonymobile.playanywhere");
    localArrayList.add("com.sonymobile.photoanalyzer");
    localArrayList.add("com.sonyericsson.usbux");
    localArrayList.add("com.sonymobile.phoneusage");
    localArrayList.add("com.qualcomm.svi");
    localArrayList.add("com.google.android.tts");
    localArrayList.add("com.miui.notes");
    localArrayList.add("com.google.android.location");
    localArrayList.add("com.google.android.youtube");
    localArrayList.add("com.google.android.gm");
    localArrayList.add("com.sec.android.provider.snote");
    localArrayList.add("com.baidu.browser.apps");
    localArrayList.add("com.android.pagebuddynotisvc");
    localArrayList.add("com.htc.sense.hsp");
    localArrayList.add("com.tencent.qqlive");
    localArrayList.add("com.htc.album");
    localArrayList.add("com.htc.cs.dm");
    localArrayList.add("com.htc.china.location.service");
    localArrayList.add("com.htc.backup");
    localArrayList.add("com.htc.sense.weiboplugin");
    localArrayList.add("com.htcmarket");
    localArrayList.add("com.htc.providers.settings");
    localArrayList.add("com.htc.htccnfilemanager");
    localArrayList.add("com.htc.musicenhancer");
    localArrayList.add("com.htc.lockscreen");
    localArrayList.add("com.sina.weibo");
    localArrayList.add("com.huawei.floatMms");
    localArrayList.add("com.cootek.smartdialer_oem_module");
    localArrayList.add("com.huawei.android.pushagent");
    localArrayList.add("com.vlife.huawei.wallpaper");
    localArrayList.add("com.iflytek.speechcloud");
    localArrayList.add("com.huawei.camera");
    localArrayList.add("com.nvidia.NvCPLSvc");
    return localArrayList;
  }
  
  /* Error */
  public List<String> a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 310	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 311	java/util/ArrayList:<init>	()V
    //   9: astore_1
    //   10: aload_1
    //   11: aload_0
    //   12: getfield 23	com/neat/phoneboost/a/a:c	Ljava/util/Set;
    //   15: invokeinterface 729 2 0
    //   20: pop
    //   21: aload_1
    //   22: aload_0
    //   23: getfield 25	com/neat/phoneboost/a/a:d	Ljava/util/Set;
    //   26: invokeinterface 729 2 0
    //   31: pop
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: areturn
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    //   41: astore_2
    //   42: goto -10 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	a
    //   9	26	1	localArrayList	ArrayList
    //   36	4	1	localObject	Object
    //   41	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	10	36	finally
    //   10	32	36	finally
    //   10	32	41	java/lang/Exception
  }
}
