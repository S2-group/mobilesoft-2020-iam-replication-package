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
import com.sinyee.babybus.box.BoxConst;
import com.sinyee.babybus.box.bo.IoBo;
import com.tendcloud.tenddata.TCAgent;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYColor3B;
import java.util.Iterator;
import java.util.List;

public class BoxLayer3_AppInfo_DownloadAll
  extends SYSprite
{
  public BoxLayer3_AppInfo_DownloadAll(Texture2D paramTexture2D, float paramFloat1, float paramFloat2)
  {
    super(paramTexture2D, paramFloat1, paramFloat2);
    setTouchEnabled(true);
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
    Object localObject1 = IoBo.PACKAGE_NAME;
    try
    {
      if ("LENOVO".equalsIgnoreCase(Build.BRAND))
      {
        launchLenovoMarket((String)localObject1);
        return;
      }
      if (!hasInstalledAmazonMarket()) {
        break label98;
      }
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + ((String)localObject1).trim()));
      localObject1 = localIntent;
      try
      {
        localActivity.startActivity(localIntent);
        return;
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      Intent localIntent;
      Object localObject2;
      for (;;) {}
    }
    localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/search?q=sinyee")));
    return;
    label98:
    localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=sinyee"));
    localObject2 = localIntent;
    localActivity.startActivity(localIntent);
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
        Toast.makeText(Director.getInstance().getContext(), BoxLayer3_AppInfo_DownloadAll.this.getInfoByLanguage(), 1).show();
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
    TCAgent.onEvent(Director.getInstance().getContext(), "DownloadAll_Click");
    launchAndroidMarket();
    return true;
  }
}
