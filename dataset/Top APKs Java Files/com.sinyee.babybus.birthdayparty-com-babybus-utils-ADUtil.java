package com.babybus.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import com.babybus.app.App;
import com.babybus.umeng.BBUmengAnalytics;
import java.util.ArrayList;
import java.util.List;

public class ADUtil
{
  public static int BOTTOM_CENTER;
  public static int BOTTOM_LEFT;
  public static int BOTTOM_RIGHT;
  public static int LEFT_CENTER;
  public static int LEFT_UNDER_TOP = 9;
  public static int RIGHT_CENTER;
  public static int RIGHT_UNDER_TOP = 10;
  public static int TOP_CENTER;
  public static int TOP_LEFT = 1;
  public static int TOP_RIGHT;
  private static String tag = "\\|";
  
  static
  {
    TOP_CENTER = 2;
    TOP_RIGHT = 3;
    BOTTOM_LEFT = 4;
    BOTTOM_CENTER = 5;
    BOTTOM_RIGHT = 6;
    LEFT_CENTER = 7;
    RIGHT_CENTER = 8;
  }
  
  public ADUtil() {}
  
  private static String[] getADInfo(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString.split(tag);
    }
    return new String[0];
  }
  
  public static FrameLayout.LayoutParams getADLayoutParams(Integer paramInteger)
  {
    FrameLayout.LayoutParams localLayoutParams = null;
    if (paramInteger.intValue() == TOP_LEFT)
    {
      localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
      localLayoutParams.gravity = 51;
    }
    do
    {
      return localLayoutParams;
      if (paramInteger.intValue() == TOP_CENTER)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 49;
        return paramInteger;
      }
      if (paramInteger.intValue() == TOP_RIGHT)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 53;
        return paramInteger;
      }
      if (paramInteger.intValue() == BOTTOM_LEFT)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 83;
        return paramInteger;
      }
      if (paramInteger.intValue() == BOTTOM_CENTER)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 81;
        return paramInteger;
      }
      if (paramInteger.intValue() == BOTTOM_RIGHT)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 85;
        return paramInteger;
      }
      if (paramInteger.intValue() == LEFT_CENTER)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 19;
        return paramInteger;
      }
      if (paramInteger.intValue() == RIGHT_CENTER)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 21;
        return paramInteger;
      }
      if (paramInteger.intValue() == LEFT_UNDER_TOP)
      {
        paramInteger = new FrameLayout.LayoutParams(-2, -2);
        paramInteger.gravity = 51;
        paramInteger.topMargin = UIUtil.px2Dip((int)(App.get().gameHight * 0.6F));
        return paramInteger;
      }
    } while (paramInteger.intValue() != RIGHT_UNDER_TOP);
    paramInteger = new FrameLayout.LayoutParams(-2, -2);
    paramInteger.gravity = 53;
    paramInteger.topMargin = UIUtil.px2Dip((int)(App.get().gameHight * 0.6F));
    return paramInteger;
  }
  
  public static int getADType(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (paramString.indexOf("|") <= 0) {
      return 0;
    }
    return Integer.parseInt(paramString.split(tag)[0]);
  }
  
  public static String getAdId(String paramString)
  {
    String str;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    for (;;)
    {
      return str;
      try
      {
        paramString = paramString.substring(paramString.lastIndexOf("|") + 1);
        str = paramString;
        if (!TextUtils.isEmpty(paramString)) {
          continue;
        }
        return "";
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          paramString = "";
        }
      }
    }
  }
  
  public static String getBBAdType(int paramInt)
  {
    switch (paramInt)
    {
    case 12: 
    case 13: 
    default: 
      return "";
    case 1: 
      return "开屏";
    case 2: 
      return "退屏";
    case 3: 
      return "插屏";
    case 4: 
      return "左下角互推";
    case 7: 
      return "巴士车盒子";
    case 5: 
      return "游戏内互推";
    case 6: 
      return "通知栏推送";
    case 8: 
      return "博士帽";
    case 10: 
      return "休息界面";
    case 9: 
      return "解锁";
    case 11: 
      return "欢迎界面定制广告开关";
    }
    return "福利时间";
  }
  
  public static int getDefaultBanner()
  {
    if (("A023".equals(App.get().channel)) || ("A017".equals(App.get().channel)) || ("A016".equals(App.get().channel))) {
      return 0;
    }
    return 2;
  }
  
  public static String getDefaultState()
  {
    if (("A023".equals(App.get().channel)) || ("A017".equals(App.get().channel)) || ("A016".equals(App.get().channel))) {
      return "0";
    }
    return "1";
  }
  
  public static String getInfoWithStr(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = getADInfo(paramString.trim());
      if (paramString.length > 1) {
        return paramString[1];
      }
    }
    return "";
  }
  
  public static List<String> getInstalledList()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = App.get().getPackageManager().getInstalledPackages(0);
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      String str = ((PackageInfo)localList.get(i)).packageName;
      if (str.contains("com.sinyee")) {
        localArrayList.add(str);
      }
      i += 1;
    }
    if (ApkUtil.isInstalled("com.mampod.ergedd")) {
      localArrayList.add("com.mampod.ergedd");
    }
    return localArrayList;
  }
  
  public static String getLocalData(String paramString)
  {
    if (App.writeSDCard) {
      return KeyChainUtil.get().getKeyChain(paramString);
    }
    return SpUtil.getString(paramString, "");
  }
  
  public static int getLocalData4Integer(String paramString)
  {
    paramString = getLocalData(paramString);
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return 0;
      }
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static long getLocalData4Long(String paramString)
  {
    paramString = getLocalData(paramString);
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return 0L;
      }
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0L;
  }
  
  public static String getStrFromMediaAge(String paramString)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return "";
        if (paramString.equals("2"))
        {
          i = 0;
          continue;
          if (paramString.equals("3"))
          {
            i = 1;
            continue;
            if (paramString.equals("4"))
            {
              i = 2;
              continue;
              if (paramString.equals("5"))
              {
                i = 3;
                continue;
                if (paramString.equals("6")) {
                  i = 4;
                }
              }
            }
          }
        }
        break;
      }
    }
    return "1-2岁";
    return "2-3岁";
    return "3-4岁";
    return "4-5岁";
    return "5+岁";
  }
  
  public static WindowManager.LayoutParams getWindowManagerADLayoutParams(Integer paramInteger)
  {
    WindowManager.LayoutParams localLayoutParams = null;
    if (paramInteger.intValue() == TOP_LEFT)
    {
      localLayoutParams = new WindowManager.LayoutParams();
      localLayoutParams.gravity = 51;
    }
    for (;;)
    {
      localLayoutParams.flags = 552;
      localLayoutParams.width = -2;
      localLayoutParams.height = -2;
      return localLayoutParams;
      if (paramInteger.intValue() == TOP_CENTER)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 49;
      }
      else if (paramInteger.intValue() == TOP_RIGHT)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 53;
      }
      else if (paramInteger.intValue() == BOTTOM_LEFT)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 83;
      }
      else if (paramInteger.intValue() == BOTTOM_CENTER)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 81;
      }
      else if (paramInteger.intValue() == BOTTOM_RIGHT)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 85;
      }
    }
  }
  
  public static boolean inTime(String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis() / 1000L;
    return (Long.parseLong(paramString1) < l) && (l < Long.parseLong(paramString2));
  }
  
  public static boolean isAd(String paramString)
  {
    if (paramString.indexOf("selfad") > 0) {}
    while ((paramString.indexOf("|") <= 0) || (paramString.indexOf("ad") <= 0)) {
      return false;
    }
    return true;
  }
  
  public static boolean isCommonSelfAd(String paramString)
  {
    return "通龄".equals(getInfoWithStr(paramString));
  }
  
  public static boolean isDefalutAd(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString.indexOf(tag) > 0)
    {
      bool1 = bool2;
      if (paramString.indexOf("默认图") > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isDefaultShutDown(String paramString)
  {
    return "默认图".equals(getInfoWithStr(paramString));
  }
  
  public static boolean isIndependentAd(String paramString)
  {
    return false;
  }
  
  public static boolean isInternationalApp()
  {
    return ("A005".equals(App.get().channel)) || ("A030".equals(App.get().channel));
  }
  
  public static boolean isThirdAd(String paramString)
  {
    return "thirdad".equals(paramString);
  }
  
  public static boolean notStarted(String paramString)
  {
    long l = System.currentTimeMillis() / 1000L;
    LogUtil.e("startTime = " + paramString);
    LogUtil.e("currentTime = " + l);
    if (l < Long.parseLong(paramString)) {}
    for (boolean bool = true;; bool = false)
    {
      LogUtil.e("notStarted = " + bool);
      return bool;
    }
  }
  
  public static void saveLocalData(String paramString1, String paramString2)
  {
    if (App.writeSDCard)
    {
      KeyChainUtil.get().setKeyChain(paramString1, paramString2);
      return;
    }
    SpUtil.putString(paramString1, paramString2);
  }
  
  public static void sendUM4Install(String paramString)
  {
    int i = getADType(paramString);
    String str = "";
    switch (i)
    {
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(str)) {
        sendUmeng4AdInfo(str, paramString);
      }
      return;
      str = "51EB88C50238E99EB60CCE90DF1C4BF4";
      continue;
      sendUM4Uninstall(paramString);
    }
  }
  
  public static void sendUM4Uninstall(String paramString)
  {
    LogUtil.e("sendUM4Uninstall = " + paramString);
    if ((TextUtils.isEmpty(tag)) || (paramString.split(tag).length != 4)) {
      return;
    }
    paramString = paramString.split(tag);
    BBUmengAnalytics.get().sendEventWithMap("FB92BCE53C8C91F11B1F12FE511D4766", paramString[1], paramString[3]);
    BBUmengAnalytics.get().sendEvent("C605B8D0B7AF03E42976957DFEC6A07E", paramString[2]);
  }
  
  public static void sendUM4UninstallComplete(String paramString)
  {
    LogUtil.e("sendUM4UninstallComplete = " + paramString);
    if ((TextUtils.isEmpty(tag)) || (paramString.split(tag).length != 4)) {
      return;
    }
    paramString = paramString.split(tag);
    BBUmengAnalytics.get().sendEventWithMap("73710E6383909444E1212D14505493E7", paramString[1], paramString[3]);
    BBUmengAnalytics.get().sendEvent("6DF74A629FBBC344AB25D3E690E054F5", paramString[2]);
  }
  
  public static void sendUmeng4AdInfo(String paramString1, String paramString2)
  {
    LogUtil.e("adInfo == " + paramString2);
    String str1;
    String str2;
    if (!TextUtils.isEmpty(paramString2))
    {
      String[] arrayOfString = paramString2.trim().split(tag);
      str1 = arrayOfString[0];
      str2 = arrayOfString[1];
      paramString2 = "";
      if (arrayOfString.length == 3) {
        paramString2 = arrayOfString[2];
      }
      LogUtil.e("adType =" + str1 + "info =" + str2 + "id =" + paramString2);
      if ("selfad".equals(str2)) {
        BBUmengAnalytics.get().sendEvent(paramString1, paramString2);
      }
    }
    else
    {
      return;
    }
    if ((String.valueOf(8).equals(str1)) && ("大全".equals(str2)))
    {
      BBUmengAnalytics.get().sendEvent(paramString1);
      return;
    }
    if (TextUtils.isEmpty(paramString2))
    {
      BBUmengAnalytics.get().sendEvent(paramString1, str2);
      return;
    }
    if ("福利".equals(str2))
    {
      BBUmengAnalytics.get().sendEvent(paramString1, paramString2);
      return;
    }
    LogUtil.t("eventName:" + paramString1 + "== info:" + str2 + "== id:" + paramString2);
    BBUmengAnalytics.get().sendEventWithMap(paramString1, str2, paramString2);
  }
  
  public static void sendUmeng4BList(int paramInt, long paramLong)
  {
    String str2 = "";
    String str1;
    if (1 == paramInt)
    {
      str2 = "ae9eecd99c0d46cea8dcf9156b75df61";
      str1 = "";
      if (paramLong > 86400L) {
        break label93;
      }
      str1 = "0-24小时";
    }
    for (;;)
    {
      if ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(str1))) {
        BBUmengAnalytics.get().sendEvent(str2, str1);
      }
      return;
      if (2 == paramInt)
      {
        str2 = "0b1d6c21dbf248e8b91de4dfad024b7d";
        break;
      }
      if (4 == paramInt)
      {
        str2 = "37c43dbef013446ead498fd285871455";
        break;
      }
      if (3 != paramInt) {
        break;
      }
      str2 = "1528544a222d4ddaa2cd9346acba09d9";
      break;
      label93:
      if (paramLong < 172800L) {
        str1 = "1-2天";
      } else if (paramLong < 345600L) {
        str1 = "3-4天";
      } else if (paramLong < 518400L) {
        str1 = "5-6天";
      } else if (paramLong < 604800L) {
        str1 = "6-7天";
      } else if (paramLong < 777600L) {
        str1 = "8-9天";
      } else if (paramLong < 950400L) {
        str1 = "10-11天";
      } else if (paramLong < 1123200L) {
        str1 = "12-13天";
      } else if (paramLong < 1296000L) {
        str1 = "14-15天";
      } else if (paramLong < 1468800L) {
        str1 = "16-17天";
      } else if (paramLong < 1641600L) {
        str1 = "18-19天";
      } else if (paramLong < 1814400L) {
        str1 = "20-21天";
      } else if (paramLong < 1987200L) {
        str1 = "22-23天";
      } else if (paramLong < 2160000L) {
        str1 = "24-25天";
      } else if (paramLong < 2332800L) {
        str1 = "26-27天";
      } else if (paramLong < 2505600L) {
        str1 = "28-29天";
      } else if (paramLong < 2678400L) {
        str1 = "30-31天";
      } else if (paramLong < 3888000L) {
        str1 = "1个月-1.5个月";
      } else if (paramLong < 5356800L) {
        str1 = "1.6个月-2个月";
      } else if (paramLong < 8035200L) {
        str1 = "2个月-3个月";
      } else if (paramLong < 10713600L) {
        str1 = "3个月-4个月";
      } else if (paramLong < 13392000L) {
        str1 = "4个月-5个月";
      } else if (paramLong < 16070400L) {
        str1 = "5个月-6个月";
      }
    }
  }
  
  public static void sendUmeng4DownloadImageFauilt(String paramString)
  {
    paramString = UIUtil.getLanguageChinese() + "_" + paramString;
    BBUmengAnalytics.get().sendEvent("c0a2c8ff8cd84dd38a0f0edb7f3fc08a", paramString);
  }
  
  public static void sendUmeng4Market(String paramString1, String paramString2)
  {
    LogUtil.e("adInfo == " + paramString2);
    if (!TextUtils.isEmpty(paramString2))
    {
      String[] arrayOfString = paramString2.trim().split(tag);
      String str = arrayOfString[0];
      paramString2 = arrayOfString[1];
      paramString2 = "";
      if (arrayOfString.length == 3) {
        paramString2 = arrayOfString[2];
      }
      if ("2".equals(str)) {
        BBUmengAnalytics.get().sendEventWithMap(paramString1, "退屏", paramString2);
      }
    }
  }
  
  public static void sendUmeng4RequestJsonFauilt(String paramString)
  {
    paramString = UIUtil.getLanguageChinese() + "_" + paramString;
    BBUmengAnalytics.get().sendEvent("907490f773b446078dd6825811be5dfd", paramString);
  }
  
  public static void sendUmengDownladFail(String paramString1, String paramString2, String paramString3)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    String str = "";
    if (paramString2.contains("m.baidu.com")) {
      str = "百度";
    }
    for (;;)
    {
      BBUmengAnalytics.get().sendEventWithMap(paramString1, str, paramString3);
      return;
      if (paramString2.contains("openbox.mobilem.360.cn")) {
        str = "360";
      }
    }
  }
}
