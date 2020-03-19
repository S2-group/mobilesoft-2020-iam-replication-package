package com.sinyee.babybus.box.bo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;
import com.sinyee.babybus.box.BoxConst;
import com.sinyee.babybus.box.BoxLayer3;
import com.sinyee.babybus.box.vo.AppInfo;
import com.sinyee.babybus.box.vo.AppSprite;
import com.sinyee.babybus.box.vo.BoxLabel;
import com.sinyee.babybus.box.vo.CountLabel;
import com.sinyee.babybus.findnumber.util.SDCardUtil;
import com.wiyun.engine.actions.Action;
import com.wiyun.engine.actions.FiniteTimeAction;
import com.wiyun.engine.actions.IntervalAction;
import com.wiyun.engine.actions.MoveBy;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.Sequence;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Layer;
import com.wiyun.engine.nodes.Node;
import com.wiyun.engine.nodes.ScrollableLayer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYColor4B;
import com.wiyun.engine.utils.TargetSelector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class BoxLayer3Bo
  extends BoxBo
{
  BoxLayer3 layer;
  
  public BoxLayer3Bo(BoxLayer3 paramBoxLayer3)
  {
    this.layer = paramBoxLayer3;
  }
  
  public static boolean checkInstallApp(String paramString)
  {
    Context localContext = Director.getInstance().getContext();
    Object localObject = null;
    try
    {
      paramString = localContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
    return true;
  }
  
  private String getInfoByLanguage(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      if (language.equals("zh")) {
        return "版本:";
      }
      if (language.equals("ja")) {
        return "バージョン:";
      }
      return "Version:";
    case 2: 
      if (language.equals("zh")) {
        return "价格:";
      }
      if (language.equals("ja")) {
        return "価格:";
      }
      return "Price:";
    case 3: 
      if (language.equals("zh")) {
        return "大小:";
      }
      if (language.equals("ja")) {
        return "サイズ:";
      }
      return "Size:";
    }
    if (language.equals("zh")) {
      return "请先打开乐商店。";
    }
    if (language.equals("ja")) {
      return "レノボショップを開いてください。";
    }
    return "Please open Lenovo Market.";
  }
  
  public int addAppList(int paramInt)
  {
    int j = 0;
    int i = 0;
    if (this.layer.slayer != null)
    {
      this.layer.removeChild(this.layer.slayer, true);
      this.layer.spritelist = new ArrayList();
    }
    this.layer.slayer = ScrollableLayer.make(WYColor4B.make(0, 0, 0, 0));
    this.layer.slayer.setContentSize(425.0F * scale_x, 140.0F * scale_y);
    this.layer.slayer.setRightMargin(50.0F * scale_x);
    this.layer.slayer.setPosition(65.0F * scale_x, 158.0F * scale_y);
    this.layer.slayer.setVertical(false);
    this.layer.slayer.setHorizontal(true);
    this.layer.addChild(this.layer.slayer);
    Object localObject = new DataBaseBo().getAppList(paramInt);
    Texture2D localTexture2D = (Texture2D)Texture2D.makePNG("box/singleAppBg.png").autoRelease();
    paramInt = j;
    if (localObject != null)
    {
      paramInt = j;
      if (((List)localObject).size() > 0)
      {
        j = 0;
        paramInt = i;
        i = j;
        if (i < ((List)localObject).size()) {
          break label242;
        }
      }
    }
    j = ((List)localObject).size();
    if (j < 3) {
      i = 0;
    }
    for (;;)
    {
      if (i >= 3 - j)
      {
        return paramInt;
        label242:
        AppInfo localAppInfo = (AppInfo)((List)localObject).get(i);
        AppSprite localAppSprite = new AppSprite(localTexture2D, i);
        localAppSprite.addLabel(localAppInfo.getAppShortName());
        localAppSprite.addImg(localAppInfo);
        this.layer.slayer.addScrollableChild(localAppSprite);
        this.layer.spritelist.add(localAppSprite);
        j = paramInt;
        if (!localAppSprite.isInstalled) {
          j = paramInt + 1;
        }
        i += 1;
        paramInt = j;
        break;
      }
      localObject = new AppSprite(localTexture2D, i + j);
      ((AppSprite)localObject).addDefaultLabel();
      ((AppSprite)localObject).addDefaultImg();
      this.layer.slayer.addScrollableChild((Node)localObject);
      i += 1;
    }
  }
  
  public void addBackGround()
  {
    Sprite localSprite = (Sprite)Sprite.make(getLocalADNo()).autoRelease();
    localSprite.setDither(true);
    localSprite.setScale(scale_x, scale_y);
    localSprite.setPosition(screen_w / 2.0F, screen_h / 2.0F);
    this.layer.addChild(localSprite);
  }
  
  public void addBoxSmoke()
  {
    Sprite localSprite = (Sprite)Sprite.make((Texture2D)Texture2D.makePNG("box/boxSmoke.png").autoRelease()).autoRelease();
    localSprite.setScale(scale_x, scale_y);
    localSprite.setPosition(45.0F * scale_x, 35.0F * scale_y);
    localSprite.runAction((RepeatForever)RepeatForever.make((Sequence)Sequence.make((MoveBy)MoveBy.make(0.5F, scale_x * -10.0F, scale_y * -10.0F).autoRelease(), new FiniteTimeAction[] { (MoveBy)MoveBy.make(0.5F, scale_x * 10.0F, scale_y * 10.0F).autoRelease() }).autoRelease()).autoRelease());
    this.layer.addChild(localSprite);
  }
  
  public void addCloud1()
  {
    Sprite localSprite = (Sprite)Sprite.make((Texture2D)Texture2D.makePNG("box/boxCloud1.png").autoRelease()).autoRelease();
    localSprite.setScale(scale_x, scale_y);
    localSprite.setPosition(376.0F * scale_x, 380.0F * scale_y);
    localSprite.runAction((RepeatForever)RepeatForever.make((Sequence)Sequence.make((MoveBy)MoveBy.make(11.0F, 250.0F * scale_x, 0.0F).autoRelease(), new FiniteTimeAction[] { (MoveBy)MoveBy.make(11.0F, -250.0F * scale_x, 0.0F).autoRelease() }).autoRelease()).autoRelease());
    this.layer.addChild(localSprite);
  }
  
  public void addCloud2()
  {
    Sprite localSprite = (Sprite)Sprite.make((Texture2D)Texture2D.makePNG("box/boxCloud2.png").autoRelease()).autoRelease();
    localSprite.setScale(scale_x, scale_y);
    localSprite.setPosition(426.0F * scale_x, 450.0F * scale_y);
    localSprite.runAction((RepeatForever)RepeatForever.make((Sequence)Sequence.make((MoveBy)MoveBy.make(9.0F, 70.0F * scale_x, 0.0F).autoRelease(), new FiniteTimeAction[] { (MoveBy)MoveBy.make(9.0F, -70.0F * scale_x, 0.0F).autoRelease() }).autoRelease()).autoRelease());
    this.layer.addChild(localSprite);
  }
  
  public void addCloud3()
  {
    Sprite localSprite = (Sprite)Sprite.make((Texture2D)Texture2D.makePNG("box/boxCloud3.png").autoRelease()).autoRelease();
    localSprite.setScale(scale_x, scale_y);
    localSprite.setPosition(626.0F * scale_x, 440.0F * scale_y);
    localSprite.runAction((RepeatForever)RepeatForever.make((Sequence)Sequence.make((MoveBy)MoveBy.make(9.0F, 100.0F * scale_x, 0.0F).autoRelease(), new FiniteTimeAction[] { (MoveBy)MoveBy.make(9.0F, -100.0F * scale_x, 0.0F).autoRelease() }).autoRelease()).autoRelease());
    this.layer.addChild(localSprite);
  }
  
  public void addGoImage()
  {
    Object localObject = (Texture2D)Texture2D.makePNG("box/goSelf.png").autoRelease();
    this.layer.goSelfSprite = ((Sprite)Sprite.make((Texture2D)localObject).autoRelease());
    this.layer.goSelfSprite.setScale(scale_x, scale_y);
    this.layer.goSelfSprite.setPosition(288.0F * scale_x, 400.0F * scale_y);
    localObject = (Sequence)Sequence.make((MoveBy)MoveBy.make(0.5F, 0.0F, 8.0F * scale_y).autoRelease(), new FiniteTimeAction[] { (MoveBy)MoveBy.make(0.5F, 0.0F, -8.0F * scale_y).autoRelease() }).autoRelease();
    this.layer.goSelfSprite.runAction((RepeatForever)RepeatForever.make((IntervalAction)localObject).autoRelease());
    this.layer.addChild(this.layer.goSelfSprite);
  }
  
  public void addGuidepost()
  {
    Texture2D localTexture2D6 = (Texture2D)Texture2D.makePNG("box/I.png").autoRelease();
    Texture2D localTexture2D2;
    Texture2D localTexture2D1;
    Texture2D localTexture2D3;
    Texture2D localTexture2D5;
    Texture2D localTexture2D4;
    if (language.equals("zh"))
    {
      localTexture2D2 = (Texture2D)Texture2D.makePNG("box/know_zh.png").autoRelease();
      localTexture2D1 = (Texture2D)Texture2D.makePNG("box/game_zh.png").autoRelease();
      localTexture2D3 = (Texture2D)Texture2D.makePNG("box/music_zh.png").autoRelease();
      localTexture2D5 = (Texture2D)Texture2D.makePNG("box/share_zh.png").autoRelease();
      localTexture2D4 = (Texture2D)Texture2D.makePNG("box/other_zh.png").autoRelease();
    }
    for (;;)
    {
      this.layer.knows = ((Sprite)Sprite.make(localTexture2D2).autoRelease());
      this.layer.knows.setScale(scale_x, scale_y);
      this.layer.knows.setPosition(scale_x * 718.0F, 315.0F * scale_y);
      this.layer.addChild(this.layer.knows);
      this.layer.games = ((Sprite)Sprite.make(localTexture2D1).autoRelease());
      this.layer.games.setScale(scale_x, scale_y);
      this.layer.games.setPosition(scale_x * 718.0F, 260.0F * scale_y);
      this.layer.addChild(this.layer.games);
      this.layer.musics = ((Sprite)Sprite.make(localTexture2D3).autoRelease());
      this.layer.musics.setScale(scale_x, scale_y);
      this.layer.musics.setPosition(scale_x * 718.0F, 205.0F * scale_y);
      this.layer.addChild(this.layer.musics);
      this.layer.abouts = ((Sprite)Sprite.make(localTexture2D6).autoRelease());
      this.layer.abouts.setScale(scale_x * 1.2F, scale_y * 1.2F);
      this.layer.abouts.setPosition(screen_w - 30.0F * scale_x, 20.0F * scale_y);
      this.layer.addChild(this.layer.abouts);
      this.layer.other = ((Sprite)Sprite.make(localTexture2D4).autoRelease());
      this.layer.other.setScale(scale_x, scale_y);
      this.layer.other.setPosition(scale_x * 718.0F, 150.0F * scale_y);
      this.layer.addChild(this.layer.other);
      this.layer.share = ((Sprite)Sprite.make(localTexture2D5).autoRelease());
      this.layer.share.setScale(scale_x, scale_y);
      this.layer.share.setPosition(scale_x * 718.0F, 95.0F * scale_y);
      this.layer.addChild(this.layer.share);
      return;
      if (language.equals("ja"))
      {
        localTexture2D2 = (Texture2D)Texture2D.makePNG("box/know_ja.png").autoRelease();
        localTexture2D1 = (Texture2D)Texture2D.makePNG("box/game_ja.png").autoRelease();
        localTexture2D3 = (Texture2D)Texture2D.makePNG("box/music_ja.png").autoRelease();
        localTexture2D5 = (Texture2D)Texture2D.makePNG("box/share_ja.png").autoRelease();
        localTexture2D4 = (Texture2D)Texture2D.makePNG("box/other_ja.png").autoRelease();
      }
      else
      {
        localTexture2D2 = (Texture2D)Texture2D.makePNG("box/know_en.png").autoRelease();
        localTexture2D1 = (Texture2D)Texture2D.makePNG("box/game_en.png").autoRelease();
        localTexture2D3 = (Texture2D)Texture2D.makePNG("box/music_en.png").autoRelease();
        localTexture2D5 = (Texture2D)Texture2D.makePNG("box/share_en.png").autoRelease();
        localTexture2D4 = (Texture2D)Texture2D.makePNG("box/other_en.png").autoRelease();
      }
    }
  }
  
  public void addHandNotification(float paramFloat)
  {
    this.layer.showTime = System.currentTimeMillis();
    Object localObject = (Texture2D)Texture2D.makePNG("box/hand.png").autoRelease();
    this.layer.hands = ((Sprite)Sprite.make((Texture2D)localObject).autoRelease());
    this.layer.hands.setScale(scale_x * 0.7F, scale_y * 0.7F);
    this.layer.hands.setPosition(300.0F * scale_x, 450.0F * scale_y);
    this.layer.addChild(this.layer.hands);
    localObject = (IntervalAction)MoveBy.make(0.4F, scale_x * -10.0F, scale_y * -10.0F).autoRelease();
    localObject = (IntervalAction)Sequence.make((FiniteTimeAction)localObject, new FiniteTimeAction[] { (IntervalAction)((IntervalAction)localObject).reverse().autoRelease() }).autoRelease();
    this.layer.hands.runAction((Action)RepeatForever.make((IntervalAction)localObject).autoRelease());
  }
  
  public void changeTouchStatus(boolean paramBoolean)
  {
    this.layer.goSelfSprite.setEnabled(paramBoolean);
    this.layer.slayer.setEnabled(paramBoolean);
    this.layer.knows.setEnabled(paramBoolean);
    this.layer.games.setEnabled(paramBoolean);
    this.layer.musics.setEnabled(paramBoolean);
    this.layer.abouts.setEnabled(paramBoolean);
    this.layer.share.setEnabled(paramBoolean);
    this.layer.other.setEnabled(paramBoolean);
  }
  
  public Texture2D getLocalADNo()
  {
    String str = SharedPreBo.getValue((Activity)Director.getInstance().getContext(), "ad_" + DataBaseBo.getLanguage());
    if ((StringUtils.isEmpty(str)) || ("0".equals(str))) {
      return (Texture2D)Texture2D.makePNG("box/frame.png").autoRelease();
    }
    if ("1".equals(str)) {
      return (Texture2D)Texture2D.makePNG("box/frame1.png").autoRelease();
    }
    if (SDCardUtil.checkFileExistAndNotEmpty(SDCARD_AD_PATH, str)) {
      return Texture2D.makeFilePNG(BoxConst.SDCARD_AD_PATH + str);
    }
    return (Texture2D)Texture2D.makePNG("box/frame.png").autoRelease();
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
      String str = StringUtils.substringAfter(this.layer.url, "details?id=");
      if ("LENOVO".equalsIgnoreCase(Build.BRAND)) {
        launchLenovoMarket(str);
      }
      for (;;)
      {
        return;
        if (!hasInstalledAmazonMarket()) {
          break;
        }
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + str.trim())));
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.layer.url)));
        return;
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + localException.trim())));
      }
    }
    finally
    {
      this.layer.url = null;
    }
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
        Toast.makeText(Director.getInstance().getContext(), BoxLayer3Bo.this.getInfoByLanguage(4), 1).show();
      }
    });
  }
  
  public void reShowHand(float paramFloat)
  {
    if ((this.layer.hands != null) && (System.currentTimeMillis() - this.layer.showTime > 10000L))
    {
      this.layer.removeChild(this.layer.hands, true);
      this.layer.hands = null;
      this.layer.scheduleOnce(new TargetSelector(this, "addHandNotification(float)", new Object[] { Float.valueOf(0.0F) }), 10.0F);
    }
  }
  
  public void showAppInfo(AppSprite paramAppSprite)
  {
    this.layer.url = paramAppSprite.url;
    this.layer.applayer = ((Layer)Layer.make().autoRelease());
    this.layer.applayer.setContentSize(336.0F * scale_x, 252.0F * scale_y);
    this.layer.applayer.setPosition(screen_w / 2.0F - this.layer.applayer.getWidth() / 2.0F, screen_h / 2.0F - this.layer.applayer.getHeight() / 2.0F + 50.0F * scale_y);
    Object localObject = (Sprite)Sprite.make((Texture2D)Texture2D.makePNG("box/appinfobg.png").autoRelease()).autoRelease();
    ((Sprite)localObject).setScale(0.7F * scale_x, 0.7F * scale_y);
    ((Sprite)localObject).setPosition(this.layer.applayer.getWidth() / 2.0F, this.layer.applayer.getHeight() / 2.0F);
    this.layer.applayer.addChild((Node)localObject);
    localObject = (Texture2D)Texture2D.makePNG("box/close.png").autoRelease();
    this.layer.closes = ((Sprite)Sprite.make((Texture2D)localObject).autoRelease());
    this.layer.closes.setScale(scale_x, scale_y);
    this.layer.closes.setPosition(this.layer.applayer.getWidth() - 40.0F * scale_x, this.layer.applayer.getHeight() - 40.0F * scale_y);
    this.layer.applayer.addChild(this.layer.closes);
    localObject = (Sprite)Sprite.make(paramAppSprite.logo).autoRelease();
    ((Sprite)localObject).setScale(scale_x, scale_y);
    ((Sprite)localObject).setPosition(90.0F * scale_x, this.layer.applayer.getHeight() - 90.0F * scale_y);
    this.layer.applayer.addChild((Node)localObject);
    localObject = BoxLabel.make(paramAppSprite.appName, 16.0F * scale_x, 1);
    ((Label)localObject).setPosition(200.0F * scale_y, this.layer.applayer.getHeight() - 60.0F * scale_y);
    ((Label)localObject).setColor(WYColor3B.make(0, 0, 0));
    this.layer.applayer.addChild((Node)localObject);
    localObject = BoxLabel.make(getInfoByLanguage(1) + " " + paramAppSprite.version, 12.0F * scale_x, 1);
    ((Label)localObject).setAnchorPercent(0.0F, 0.5F);
    ((Label)localObject).setPosition(170.0F * scale_y, this.layer.applayer.getHeight() - 110.0F * scale_y);
    ((Label)localObject).setColor(WYColor3B.make(0, 0, 0));
    this.layer.applayer.addChild((Node)localObject);
    localObject = BoxLabel.make(getInfoByLanguage(2) + " " + paramAppSprite.price, 12.0F * scale_x, 1);
    ((Label)localObject).setAnchorPercent(0.0F, 0.5F);
    ((Label)localObject).setPosition(170.0F * scale_y, this.layer.applayer.getHeight() - 140.0F * scale_y);
    ((Label)localObject).setColor(WYColor3B.make(0, 0, 0));
    this.layer.applayer.addChild((Node)localObject);
    paramAppSprite = BoxLabel.make(getInfoByLanguage(3) + " " + paramAppSprite.size, 12.0F * scale_x, 1);
    paramAppSprite.setAnchorPercent(0.0F, 0.5F);
    paramAppSprite.setPosition(170.0F * scale_y, this.layer.applayer.getHeight() - 170.0F * scale_y);
    paramAppSprite.setColor(WYColor3B.make(0, 0, 0));
    this.layer.applayer.addChild(paramAppSprite);
    if (language.equals("zh")) {
      paramAppSprite = (Texture2D)Texture2D.makePNG("box/down_zh.png").autoRelease();
    }
    for (;;)
    {
      this.layer.downloads = ((Sprite)Sprite.make(paramAppSprite).autoRelease());
      this.layer.downloads.setScale(scale_x, scale_y);
      this.layer.downloads.setPosition(90.0F * scale_x, this.layer.applayer.getHeight() - 170.0F * scale_y);
      this.layer.applayer.addChild(this.layer.downloads);
      this.layer.addChild(this.layer.applayer);
      changeTouchStatus(false);
      return;
      if (language.equals("ja")) {
        paramAppSprite = (Texture2D)Texture2D.makePNG("box/down_ja.png").autoRelease();
      } else {
        paramAppSprite = (Texture2D)Texture2D.makePNG("box/down_en.png").autoRelease();
      }
    }
  }
  
  public void showNotInstallIcon()
  {
    int i4 = 0;
    int i1 = 0;
    int i5 = 0;
    int n = 0;
    int i6 = 0;
    int i2 = 0;
    int i7 = 0;
    int i3 = 0;
    Object localObject = new DataBaseBo().getAppList();
    int i = i5;
    int j = i4;
    int k = i6;
    int m = i7;
    if (localObject != null)
    {
      i = i5;
      j = i4;
      k = i6;
      m = i7;
      if (((List)localObject).size() > 0)
      {
        localObject = ((List)localObject).iterator();
        m = i3;
        k = i2;
        j = i1;
        i = n;
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        this.layer.knowsLabel = CountLabel.make(scale_x * 770.0F, 325.0F * scale_y, j);
        this.layer.addChild(this.layer.knowsLabel);
        this.layer.gamesLabel = CountLabel.make(scale_x * 770.0F, 270.0F * scale_y, i);
        this.layer.addChild(this.layer.gamesLabel);
        this.layer.musicsLabel = CountLabel.make(scale_x * 770.0F, 215.0F * scale_y, k);
        this.layer.addChild(this.layer.musicsLabel);
        this.layer.otherLabel = CountLabel.make(scale_x * 770.0F, 160.0F * scale_y, m);
        this.layer.addChild(this.layer.otherLabel);
        if (j <= 0) {
          this.layer.knowsLabel.setVisible(false);
        }
        if (i <= 0) {
          this.layer.gamesLabel.setVisible(false);
        }
        if (k <= 0) {
          this.layer.musicsLabel.setVisible(false);
        }
        if (m <= 0) {
          this.layer.otherLabel.setVisible(false);
        }
        return;
      }
      AppInfo localAppInfo = (AppInfo)((Iterator)localObject).next();
      if (!checkInstallApp(localAppInfo.getAppKey())) {
        if (String.valueOf(2).equals(localAppInfo.getAppType())) {
          j += 1;
        } else if (String.valueOf(3).equals(localAppInfo.getAppType())) {
          i += 1;
        } else if (String.valueOf(4).equals(localAppInfo.getAppType())) {
          k += 1;
        } else if (String.valueOf(5).equals(localAppInfo.getAppType())) {
          m += 1;
        }
      }
    }
  }
}
