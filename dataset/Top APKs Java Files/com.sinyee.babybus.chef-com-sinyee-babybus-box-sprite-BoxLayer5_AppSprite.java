package com.sinyee.babybus.box.sprite;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.widget.Toast;
import com.sinyee.babybus.base.Const;
import com.sinyee.babybus.base.SYSprite;
import com.sinyee.babybus.base.Textures;
import com.sinyee.babybus.base.util.AssetsUtil;
import com.sinyee.babybus.base.util.ImgUtil;
import com.sinyee.babybus.base.util.LanguageUtil;
import com.sinyee.babybus.base.util.NetUtil;
import com.sinyee.babybus.base.util.SDCardUtil;
import com.sinyee.babybus.box.BoxConst;
import com.sinyee.babybus.box.bo.BoxLayer3Bo;
import com.umeng.analytics.MobclickAgent;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYPoint;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class BoxLayer5_AppSprite
  extends SYSprite
  implements BoxConst
{
  public Texture2D appLogo;
  public boolean isInstalled;
  boolean isMoving = false;
  float touchy = 0.0F;
  public AppVo vo;
  
  public BoxLayer5_AppSprite(Texture2D paramTexture2D, int paramInt, AppVo paramAppVo)
  {
    super(paramTexture2D);
    setPosition(px("Boxlayer5", "appsprite"), py("Boxlayer5", "appsprite") - getHeight() * 1.2F * paramInt);
    addLabel(paramAppVo.getAppName(), paramAppVo.getAppInfo());
    addImg(paramAppVo);
    this.vo = paramAppVo;
    setTouchEnabled(true);
  }
  
  private void addLabel(String paramString1, String paramString2)
  {
    paramString1 = (Label)Label.make(paramString1, 18.0F).autoRelease();
    paramString1.setAnchor(0.0F, 0.5F);
    paramString1.setPosition(getWidth() * 28.0F / 100.0F, getHeight() * 80.0F / 100.0F);
    paramString1.setColor(WYColor3B.make(0, 0, 0));
    addChild(paramString1);
    if (StringUtils.isNotEmpty(paramString2))
    {
      if (!LanguageUtil.isEnglish()) {
        break label180;
      }
      paramString1 = paramString2;
      if (paramString2.length() > 75) {
        paramString1 = paramString2.substring(0, 75) + "...";
      }
    }
    for (;;)
    {
      paramString1 = (Label)Label.make(paramString1, 17.0F, 0, null, getWidth() * 7.0F / 10.0F).autoRelease();
      paramString1.setAnchor(0.0F, 1.0F);
      paramString1.setPosition(getWidth() * 28.0F / 100.0F, getHeight() * 60.0F / 100.0F);
      paramString1.setColor(WYColor3B.make(0, 0, 0));
      addChild(paramString1);
      return;
      label180:
      paramString1 = paramString2;
      if (paramString2.length() > 50) {
        paramString1 = paramString2.substring(0, 50) + "...";
      }
    }
  }
  
  private String getInfoByLanguage()
  {
    if (BoxConst.language.equals("zh")) {
      return "请先打开乐商店。";
    }
    if (BoxConst.language.equals("ja")) {
      return "レノボショップを開いてください。";
    }
    return "Please open Lenovo Market.";
  }
  
  private Texture2D getLogo(final String paramString)
  {
    Texture2D localTexture2D = null;
    final String str = SDCARD_IMG_PATH;
    if (SDCardUtil.checkFileExistAndNotEmpty(str, paramString)) {
      if (ImgUtil.isCompletePng(str + paramString)) {
        localTexture2D = Texture2D.makeFile(str + paramString);
      }
    }
    for (;;)
    {
      paramString = localTexture2D;
      if (localTexture2D == null) {
        paramString = Textures.box_defaultlogo;
      }
      return paramString;
      SDCardUtil.deleteFile4SDCard(str, paramString);
      localTexture2D = null;
      new Thread()
      {
        public void run()
        {
          File localFile = SDCardUtil.createFile2SDCard(str, paramString);
          if (localFile != null) {
            if (Const.BASE_WIDTH != 1280) {
              break label35;
            }
          }
          label35:
          for (String str = BoxLayer5_AppSprite.HOST_IMG_1280;; str = BoxLayer5_AppSprite.HOST_IMG + paramString)
          {
            NetUtil.downloadFile(str, localFile);
            return;
          }
        }
      }.start();
      continue;
      if (AssetsUtil.isAssetExistent(Const.PIX + "/box/logo/" + paramString)) {
        localTexture2D = Texture2D.make(Const.PIX + "/box/logo/" + paramString);
      }
    }
  }
  
  public void addImg(AppVo paramAppVo)
  {
    Texture2D localTexture2D = getLogo(paramAppVo.getAppLogo());
    this.appLogo = localTexture2D;
    if (BoxLayer3Bo.checkInstallApp(paramAppVo.getAppKey())) {}
    for (this.isInstalled = true;; this.isInstalled = false)
    {
      paramAppVo = new SYSprite(localTexture2D);
      paramAppVo.setScale(0.95F);
      paramAppVo.setPosition(getWidth() * 125.0F / 1000.0F, getHeight() / 2.0F);
      addChild(paramAppVo);
      return;
    }
  }
  
  public boolean hasInstalledAmazonMarket()
  {
    List localList = Director.getInstance().getContext().getPackageManager().getInstalledPackages(0);
    int i;
    if (localList != null) {
      i = 0;
    }
    for (;;)
    {
      if (i >= localList.size()) {
        return false;
      }
      if ("com.amazon.venezia".equals(((PackageInfo)localList.get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
  }
  
  public void launchAndroidMarket()
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    try
    {
      String str = StringUtils.substringAfter(this.vo.getAppUrl(), "details?id=");
      if ("LENOVO".equalsIgnoreCase(Build.BRAND))
      {
        launchLenovoMarket(str);
        return;
      }
      if (hasInstalledAmazonMarket())
      {
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + str.trim())));
        return;
      }
    }
    catch (Exception localException)
    {
      localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.vo.getAppUrl())));
      return;
    }
    localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + localException.trim())));
  }
  
  public void launchLenovoMarket(String paramString)
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    Iterator localIterator = ((ActivityManager)localActivity.getSystemService("activity")).getRunningTasks(500).iterator();
    for (;;)
    {
      int i = 0;
      if (!localIterator.hasNext()) {}
      for (;;)
      {
        if (i == 0) {
          break label135;
        }
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("leapp://ptn/appinfo.do?service=ptn&packagename=" + paramString.trim())));
        return;
        String str = ((ActivityManager.RunningTaskInfo)localIterator.next()).baseActivity.getPackageName();
        if ((!"com.lenovo.leos.appstore.pad".equals(str)) && (!"com.lenovo.leos.appstore".equals(str))) {
          break;
        }
        i = 1;
      }
    }
    label135:
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Director.getInstance().getContext(), BoxLayer5_AppSprite.this.getInfoByLanguage(), 1).show();
      }
    });
  }
  
  public boolean wyTouchesBegan(MotionEvent paramMotionEvent)
  {
    this.isMoving = false;
    this.touchy = Director.getInstance().convertToGL(paramMotionEvent.getX(), paramMotionEvent.getY()).y;
    return false;
  }
  
  public boolean wyTouchesEnded(MotionEvent paramMotionEvent)
  {
    if ((Director.getInstance().convertToGL(paramMotionEvent.getX(), paramMotionEvent.getY()).y > 50.0F) && (!this.isMoving))
    {
      paramMotionEvent = new HashMap();
      paramMotionEvent.put("appName", this.vo.getAppName());
      paramMotionEvent.put("appKey", this.vo.getAppKey());
      MobclickAgent.onEvent(Director.getInstance().getContext(), "hotapp_click_time", paramMotionEvent);
      AudioManager.playEffect("box_music/raw/box_click.ogg");
      launchAndroidMarket();
    }
    return false;
  }
  
  public boolean wyTouchesMoved(MotionEvent paramMotionEvent)
  {
    if (Math.abs(Director.getInstance().convertToGL(paramMotionEvent.getX(), paramMotionEvent.getY()).y - this.touchy) > 10.0F) {
      this.isMoving = true;
    }
    return false;
  }
}
