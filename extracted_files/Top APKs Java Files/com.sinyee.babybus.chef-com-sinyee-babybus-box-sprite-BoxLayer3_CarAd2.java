package com.sinyee.babybus.box.sprite;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import com.sinyee.babybus.base.Const;
import com.sinyee.babybus.base.SYSprite;
import com.sinyee.babybus.box.BoxConst;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYPoint;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class BoxLayer3_CarAd2
  extends SYSprite
{
  String ad_word_url;
  
  public BoxLayer3_CarAd2(Texture2D paramTexture2D, String paramString)
  {
    super(paramTexture2D);
    setAnchor(0.0F, 0.0F);
    if (Const.BASE_WIDTH == 1280) {
      setPosition(305.0F, 139.0F);
    }
    for (;;)
    {
      this.ad_word_url = paramString;
      setTouchEnabled(true);
      return;
      setPosition(191.0F, 87.0F);
    }
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
  
  public static void launchApp(String paramString)
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).setPackage(paramString);
    localObject = (ResolveInfo)localActivity.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
    if (localObject != null)
    {
      paramString = ((ResolveInfo)localObject).activityInfo.packageName;
      localObject = ((ResolveInfo)localObject).activityInfo.name;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(paramString, (String)localObject));
      localIntent.putExtra("launchFlag", true);
      localActivity.startActivity(localIntent);
      localActivity.finish();
      Process.killProcess(Process.myPid());
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
        Toast.makeText(Director.getInstance().getContext(), BoxLayer3_CarAd2.this.getInfoByLanguage(), 1).show();
      }
    });
  }
  
  public boolean wyTouchesBegan(MotionEvent paramMotionEvent)
  {
    AudioManager.playEffect("box_music/raw/box_click.ogg");
    return true;
  }
  
  public boolean wyTouchesEnded(MotionEvent paramMotionEvent)
  {
    paramMotionEvent = Director.getInstance().convertToGL(paramMotionEvent.getX(), paramMotionEvent.getY());
    Activity localActivity;
    if (hitTest(paramMotionEvent.x, paramMotionEvent.y))
    {
      localActivity = (Activity)Director.getInstance().getContext();
      paramMotionEvent = new HashMap();
      paramMotionEvent.put("ad2word", "车身上的图片广告");
      paramMotionEvent.put("ad2Url", this.ad_word_url);
      MobclickAgent.onEvent(Director.getInstance().getContext(), "box_car_ad2_click_time", paramMotionEvent);
      TCAgent.onEvent(Director.getInstance().getContext(), "Car_Ad2_click", this.ad_word_url);
      if (StringUtils.isNotEmpty(this.ad_word_url))
      {
        if (!this.ad_word_url.startsWith("http")) {
          break label145;
        }
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.ad_word_url)));
      }
    }
    for (;;)
    {
      return true;
      label145:
      paramMotionEvent = this.ad_word_url;
      if (!checkInstallApp(paramMotionEvent))
      {
        for (;;)
        {
          try
          {
            if (!"LENOVO".equalsIgnoreCase(Build.BRAND)) {
              break label192;
            }
            launchLenovoMarket(paramMotionEvent);
          }
          catch (Exception paramMotionEvent)
          {
            Log.e("babybus", paramMotionEvent.toString());
          }
          break;
          label192:
          if (!hasInstalledAmazonMarket()) {
            break label246;
          }
          localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + paramMotionEvent.trim()));
          paramMotionEvent = localIntent;
          try
          {
            localActivity.startActivity(localIntent);
          }
          catch (Exception paramMotionEvent) {}
        }
        label246:
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramMotionEvent.trim()));
        paramMotionEvent = localIntent;
        localActivity.startActivity(localIntent);
      }
      else
      {
        launchApp(paramMotionEvent);
      }
    }
  }
}
