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
import com.sinyee.babybus.base.util.SharedPreUtil;
import com.sinyee.babybus.box.BoxConst;
import com.sinyee.babybus.box.bo.DataBaseBo;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import com.wiyun.engine.actions.CallFunc;
import com.wiyun.engine.actions.FiniteTimeAction;
import com.wiyun.engine.actions.MoveBy;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.RotateBy;
import com.wiyun.engine.actions.Sequence;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.types.WYPoint;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class BoxLayer3_BabyMall
  extends SYSprite
{
  String mall_url;
  
  public BoxLayer3_BabyMall(Texture2D paramTexture2D, String paramString)
  {
    super(paramTexture2D);
    this.mall_url = paramString;
    setAnchor(0.5F, 1.0F);
    if (Const.BASE_WIDTH == 1280) {
      setPosition(924.0F, 949.0F);
    }
    for (;;)
    {
      setTouchEnabled(true);
      dropDown();
      return;
      setPosition(578.0F, 593.0F);
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
  
  public void dropDown()
  {
    if (Const.BASE_WIDTH == 1280) {}
    for (int i = 160;; i = 100)
    {
      runAction((Sequence)Sequence.make((MoveBy)MoveBy.make(0.6F, 0.0F, -i).autoRelease(), new FiniteTimeAction[] { (RotateBy)RotateBy.make(4.0F, 10.0F).autoRelease(), (CallFunc)CallFunc.make(this, "shakeForever").autoRelease() }).autoRelease());
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
          break label134;
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
    label134:
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Director.getInstance().getContext(), BoxLayer3_BabyMall.this.getInfoByLanguage(), 1).show();
      }
    });
  }
  
  public void shakeForever()
  {
    RotateBy localRotateBy = (RotateBy)RotateBy.make(8.0F, -20.0F).autoRelease();
    runAction((RepeatForever)RepeatForever.make((Sequence)Sequence.make(localRotateBy, new FiniteTimeAction[] { (RotateBy)localRotateBy.reverse() }).autoRelease()).autoRelease());
  }
  
  public boolean wyTouchesBegan(MotionEvent paramMotionEvent)
  {
    AudioManager.playEffect("box_music/raw/box_click.ogg");
    return true;
  }
  
  public boolean wyTouchesEnded(MotionEvent paramMotionEvent)
  {
    paramMotionEvent = Director.getInstance().convertToGL(paramMotionEvent.getX(), paramMotionEvent.getY());
    if (StringUtils.isEmpty(this.mall_url)) {
      this.mall_url = SharedPreUtil.getValue("show_baby_mall_url_" + DataBaseBo.getLanguage());
    }
    Activity localActivity;
    if (hitTest(paramMotionEvent.x, paramMotionEvent.y))
    {
      localActivity = (Activity)Director.getInstance().getContext();
      if (StringUtils.isNotEmpty(this.mall_url))
      {
        paramMotionEvent = new HashMap();
        paramMotionEvent.put("babyMall", "宝宝商城");
        paramMotionEvent.put("babyMallUrl", this.mall_url);
        MobclickAgent.onEvent(Director.getInstance().getContext(), "baby_mall", paramMotionEvent);
        TCAgent.onEvent(Director.getInstance().getContext(), "baby_mall", this.mall_url);
        if (!this.mall_url.startsWith("http")) {
          break label182;
        }
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.mall_url)));
      }
    }
    for (;;)
    {
      return true;
      label182:
      paramMotionEvent = this.mall_url;
      if (!checkInstallApp(paramMotionEvent))
      {
        for (;;)
        {
          try
          {
            if (!"LENOVO".equalsIgnoreCase(Build.BRAND)) {
              break label229;
            }
            launchLenovoMarket(paramMotionEvent);
          }
          catch (Exception paramMotionEvent)
          {
            Log.e("babybus", paramMotionEvent.toString());
          }
          break;
          label229:
          if (!hasInstalledAmazonMarket()) {
            break label284;
          }
          localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + paramMotionEvent.trim()));
          paramMotionEvent = localIntent;
          try
          {
            localActivity.startActivity(localIntent);
          }
          catch (Exception paramMotionEvent) {}
        }
        label284:
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
