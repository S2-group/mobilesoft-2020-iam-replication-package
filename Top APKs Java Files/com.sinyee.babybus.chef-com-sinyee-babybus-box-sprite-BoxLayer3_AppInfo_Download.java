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
import com.sinyee.babybus.base.SYSprite;
import com.sinyee.babybus.base.Textures;
import com.sinyee.babybus.box.BoxConst;
import com.tendcloud.tenddata.TCAgent;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYColor3B;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class BoxLayer3_AppInfo_Download
  extends SYSprite
{
  String url;
  
  public BoxLayer3_AppInfo_Download(String paramString, float paramFloat1, float paramFloat2)
  {
    super(Textures.box_download);
    setPosition(paramFloat1, paramFloat2);
    setTouchEnabled(true);
    this.url = paramString;
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
      String str = StringUtils.substringAfter(this.url, "details?id=");
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
      localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.url)));
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
          break label130;
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
    label130:
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Director.getInstance().getContext(), BoxLayer3_AppInfo_Download.this.getInfoByLanguage(), 1).show();
      }
    });
  }
  
  public boolean wyTouchesBegan(MotionEvent paramMotionEvent)
  {
    AudioManager.playEffect("box_music/raw/box_click.ogg");
    setColor(WYColor3B.make(150, 150, 150));
    return true;
  }
  
  public boolean wyTouchesEnded(MotionEvent paramMotionEvent)
  {
    setColor(WYColor3B.make(255, 255, 255));
    TCAgent.onEvent(Director.getInstance().getContext(), "Download_Click");
    launchAndroidMarket();
    return true;
  }
}
