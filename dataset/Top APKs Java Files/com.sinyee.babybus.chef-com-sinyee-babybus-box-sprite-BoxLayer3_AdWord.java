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
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import com.sinyee.babybus.base.Const;
import com.sinyee.babybus.box.BoxConst;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYColor3B;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class BoxLayer3_AdWord
  extends Label
{
  String ad_word_url;
  String adword;
  
  protected BoxLayer3_AdWord(String paramString1, float paramFloat1, int paramInt, float paramFloat2, String paramString2)
  {
    super(paramString1, paramFloat1, paramInt, null, paramFloat2, 0);
    this.ad_word_url = paramString2;
    this.adword = paramString1;
    autoRelease();
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
  
  public static Label make(String paramString1, String paramString2, float paramFloat)
  {
    if (Const.BASE_WIDTH == 1280) {}
    for (float f = 22.0F;; f = 13.75F) {
      return new BoxLayer3_AdWord(paramString1, f, 0, paramFloat, paramString2);
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
        Toast.makeText(Director.getInstance().getContext(), BoxLayer3_AdWord.this.getInfoByLanguage(), 1).show();
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
    setColor(WYColor3B.make(0, 0, 0));
    Activity localActivity = (Activity)Director.getInstance().getContext();
    paramMotionEvent = new HashMap();
    paramMotionEvent.put("adword", this.adword);
    paramMotionEvent.put("adUrl", this.ad_word_url);
    MobclickAgent.onEvent(Director.getInstance().getContext(), "box_car_ad_click_time", paramMotionEvent);
    TCAgent.onEvent(Director.getInstance().getContext(), "Adword_click", this.adword);
    if (StringUtils.isNotEmpty(this.ad_word_url))
    {
      if (!this.ad_word_url.startsWith("http")) {
        break label121;
      }
      localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.ad_word_url)));
    }
    for (;;)
    {
      return true;
      label121:
      paramMotionEvent = this.ad_word_url;
      for (;;)
      {
        try
        {
          if (!"LENOVO".equalsIgnoreCase(Build.BRAND)) {
            break label160;
          }
          launchLenovoMarket(paramMotionEvent);
        }
        catch (Exception paramMotionEvent)
        {
          Log.e("babybus", paramMotionEvent.toString());
        }
        break;
        label160:
        if (!hasInstalledAmazonMarket()) {
          break label214;
        }
        localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + paramMotionEvent.trim()));
        paramMotionEvent = localIntent;
        try
        {
          localActivity.startActivity(localIntent);
        }
        catch (Exception paramMotionEvent) {}
      }
      label214:
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramMotionEvent.trim()));
      paramMotionEvent = localIntent;
      localActivity.startActivity(localIntent);
    }
  }
}
