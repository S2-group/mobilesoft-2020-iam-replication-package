package com.babybus.i;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import com.babybus.app.App;
import com.babybus.bean.ADMediaBean;
import com.babybus.f.g;
import java.util.ArrayList;
import java.util.List;

public class a
{
  public static int jdField_byte = 7;
  public static int jdField_case = 8;
  public static int jdField_char = 9;
  public static int jdField_do = 1;
  public static int jdField_else = 10;
  public static int jdField_for = 3;
  private static String jdField_goto = "\\|";
  public static int jdField_if = 2;
  public static int jdField_int = 4;
  public static int jdField_new = 5;
  public static int jdField_try = 6;
  
  public a() {}
  
  private static boolean jdMethod_boolean(String paramString)
  {
    return "0".equals(at.jdMethod_if(paramString, App.jdMethod_do().protected)) ^ true;
  }
  
  public static String jdMethod_break(String paramString)
  {
    if (App.goto) {
      return x.jdMethod_do().jdMethod_do(paramString);
    }
    return at.jdMethod_if(paramString, "");
  }
  
  public static boolean jdMethod_break()
  {
    return (jdMethod_boolean("welcome_re_state")) && ("2".equals(at.jdMethod_if("welcome_re_state", "")));
  }
  
  public static void jdMethod_byte(String paramString)
  {
    switch (jdMethod_new(paramString))
    {
    default: 
      break;
    case 31: 
      jdMethod_case(paramString);
      break;
    case 30: 
      jdMethod_if("请求安装", paramString);
    }
    if (!TextUtils.isEmpty("")) {
      jdMethod_int("", paramString);
    }
  }
  
  public static boolean jdMethod_byte()
  {
    return (ag.jdMethod_do()) || (("2".equals(at.jdMethod_if("third_network_state", "1"))) && (ag.jdMethod_int()));
  }
  
  public static void jdMethod_case(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("sendUM4Uninstall = ");
    localStringBuilder.append(paramString);
    z.jdMethod_for(localStringBuilder.toString());
    if (!TextUtils.isEmpty(goto))
    {
      if (paramString.split(goto).length != 4) {
        return;
      }
      paramString = paramString.split(goto);
      com.babybus.h.a.jdMethod_do().jdMethod_do("FB92BCE53C8C91F11B1F12FE511D4766", paramString[1], paramString[3]);
      com.babybus.h.a.jdMethod_do().jdMethod_do("C605B8D0B7AF03E42976957DFEC6A07E", paramString[2]);
      return;
    }
  }
  
  public static boolean jdMethod_case()
  {
    return (jdMethod_int()) || (jdMethod_new());
  }
  
  public static long jdMethod_catch(String paramString)
  {
    paramString = jdMethod_break(paramString);
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
  
  public static boolean jdMethod_catch()
  {
    return jdMethod_boolean("push_state");
  }
  
  public static void jdMethod_char(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("sendUM4UninstallComplete = ");
    localStringBuilder.append(paramString);
    z.jdMethod_for(localStringBuilder.toString());
    if (!TextUtils.isEmpty(goto))
    {
      if (paramString.split(goto).length != 4) {
        return;
      }
      paramString = paramString.split(goto);
      com.babybus.h.a.jdMethod_do().jdMethod_do("73710E6383909444E1212D14505493E7", paramString[1], paramString[3]);
      com.babybus.h.a.jdMethod_do().jdMethod_do("6DF74A629FBBC344AB25D3E690E054F5", paramString[2]);
      return;
    }
  }
  
  public static boolean jdMethod_char()
  {
    return jdMethod_boolean("shutdown_state");
  }
  
  public static int jdMethod_class(String paramString)
  {
    paramString = jdMethod_break(paramString);
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
  
  public static boolean jdMethod_class()
  {
    return (g.jdMethod_do().jdMethod_do("VideoOl")) && (jdMethod_boolean("mv_re_state"));
  }
  
  public static boolean const()
  {
    return jdMethod_default("banner");
  }
  
  public static boolean const(String paramString)
  {
    return "thirdad".equals(paramString);
  }
  
  private static boolean jdMethod_default(String paramString)
  {
    return "0".equals(at.jdMethod_if(paramString, App.jdMethod_do().transient)) ^ true;
  }
  
  public static FrameLayout.LayoutParams jdMethod_do(Integer paramInteger)
  {
    if (paramInteger.intValue() == do)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 51;
      return paramInteger;
    }
    if (paramInteger.intValue() == if)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 49;
      return paramInteger;
    }
    if (paramInteger.intValue() == for)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 53;
      return paramInteger;
    }
    if (paramInteger.intValue() == int)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 83;
      return paramInteger;
    }
    if (paramInteger.intValue() == new)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 81;
      return paramInteger;
    }
    if (paramInteger.intValue() == try)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 85;
      return paramInteger;
    }
    if (paramInteger.intValue() == byte)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 19;
      return paramInteger;
    }
    if (paramInteger.intValue() == case)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 21;
      return paramInteger;
    }
    if (paramInteger.intValue() == char)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 51;
      paramInteger.topMargin = ay.jdMethod_try((int)(App.jdMethod_do().jdField_catch * 0.6F));
      return paramInteger;
    }
    if (paramInteger.intValue() == else)
    {
      paramInteger = new FrameLayout.LayoutParams(-2, -2);
      paramInteger.gravity = 53;
      paramInteger.topMargin = ay.jdMethod_try((int)(App.jdMethod_do().jdField_catch * 0.6F));
      return paramInteger;
    }
    return null;
  }
  
  public static String jdMethod_do()
  {
    if ((!"A023_SDK".equals(App.jdMethod_do().jdField_else)) && (!"A017".equals(App.jdMethod_do().jdField_else)) && (!"A016".equals(App.jdMethod_do().jdField_else))) {
      return "1";
    }
    return "0";
  }
  
  public static void jdMethod_do(int paramInt)
  {
    com.babybus.h.a.jdMethod_do().jdMethod_do("638B09EBED74EC990B04258AB7E0DA8F", jdMethod_new(paramInt));
  }
  
  public static void jdMethod_do(int paramInt, String paramString)
  {
    com.babybus.h.a.jdMethod_do().jdMethod_do("792C32929B70085C8C82D9C6183B7120", jdMethod_new(paramInt), paramString);
  }
  
  private static void jdMethod_do(int paramInt, String paramString1, String paramString2)
  {
    String str1 = "";
    String str2 = "";
    int i = 0;
    if (paramInt != 4)
    {
      if (paramInt != 8)
      {
        switch (paramInt)
        {
        default: 
          paramInt = i;
          break;
        case 2: 
          str1 = "ZMT002";
          str2 = "BC681938-802A-A877-4978-F95274ED829A";
          paramInt = 1;
          break;
        case 1: 
          str1 = "ZMT001";
          paramInt = i;
          break;
        }
      }
      else
      {
        str1 = "ZMT006";
        paramInt = i;
      }
    }
    else
    {
      str1 = "ZMT003";
      paramInt = i;
    }
    if (TextUtils.isEmpty(str1)) {
      return;
    }
    String str3 = jdMethod_public(paramString2);
    if (TextUtils.isEmpty(paramString1)) {
      com.babybus.h.a.jdMethod_do().jdMethod_do(str1, paramString2, str3, "");
    } else {
      com.babybus.h.a.jdMethod_do().jdMethod_do(str1, paramString1, paramString2, str3);
    }
    if (paramInt != 0) {
      com.babybus.h.a.jdMethod_do().jdMethod_if(str2, str3, "");
    }
  }
  
  public static void jdMethod_do(ADMediaBean paramADMediaBean, String paramString)
  {
    if ((paramADMediaBean != null) && (!TextUtils.isEmpty(paramADMediaBean.getBlockInfo())))
    {
      if (TextUtils.isEmpty(paramADMediaBean.getPosition())) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramADMediaBean.getBlockInfo());
      localStringBuilder.append("_");
      localStringBuilder.append(paramADMediaBean.getPosition());
      paramADMediaBean = localStringBuilder.toString();
      com.babybus.h.a.jdMethod_do().jdMethod_if("2415A004-712F-D8D5-3BB2-0C1F40F3F3E3", paramADMediaBean, paramString);
      return;
    }
  }
  
  public static void jdMethod_do(String paramString, ADMediaBean paramADMediaBean)
  {
    if (paramADMediaBean != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return;
      }
      com.babybus.h.a localA = com.babybus.h.a.jdMethod_do();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramADMediaBean.getBlockInfo());
      localStringBuilder.append("_");
      localStringBuilder.append(paramADMediaBean.getPosition());
      localA.jdMethod_if(paramString, localStringBuilder.toString(), paramADMediaBean.getAppKey());
      return;
    }
  }
  
  public static void jdMethod_do(String paramString1, ADMediaBean paramADMediaBean, String paramString2)
  {
    if ((paramADMediaBean != null) && (!TextUtils.isEmpty(paramADMediaBean.getBlockInfo())))
    {
      if (TextUtils.isEmpty(paramADMediaBean.getAppKey())) {
        return;
      }
      com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, paramADMediaBean.getBlockInfo(), paramADMediaBean.getAppKey(), paramString2);
      return;
    }
  }
  
  public static void jdMethod_do(String paramString1, String paramString2, String paramString3)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    String str = "";
    if (paramString2.contains("m.baidu.com")) {
      str = "百度";
    } else if (paramString2.contains("openbox.mobilem.360.cn")) {
      str = "360";
    }
    com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, str, paramString3);
  }
  
  public static boolean jdMethod_do(String paramString)
  {
    long l = System.currentTimeMillis() / 1000L;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("startTime = ");
    localStringBuilder.append(paramString);
    z.jdMethod_for(localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("currentTime = ");
    localStringBuilder.append(l);
    z.jdMethod_for(localStringBuilder.toString());
    boolean bool;
    if (l < Long.parseLong(paramString)) {
      bool = true;
    } else {
      bool = false;
    }
    paramString = new StringBuilder();
    paramString.append("notStarted = ");
    paramString.append(bool);
    z.jdMethod_for(paramString.toString());
    return bool;
  }
  
  public static boolean jdMethod_do(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    try
    {
      long l1 = System.currentTimeMillis() / 1000L;
      boolean bool1 = bool2;
      if (Long.parseLong(paramString1) < l1)
      {
        long l2 = Long.parseLong(paramString2);
        bool1 = bool2;
        if (l1 < l2) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (NumberFormatException paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static boolean jdMethod_double()
  {
    return jdMethod_boolean("ad_paster_state");
  }
  
  public static boolean jdMethod_double(String paramString)
  {
    return "2".equals(paramString);
  }
  
  public static String jdMethod_else(String paramString)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 54: 
      if (paramString.equals("6")) {
        i = 4;
      }
      break;
    case 53: 
      if (paramString.equals("5")) {
        i = 3;
      }
      break;
    case 52: 
      if (paramString.equals("4")) {
        i = 2;
      }
      break;
    case 51: 
      if (paramString.equals("3")) {
        i = 1;
      }
      break;
    case 50: 
      if (paramString.equals("2")) {
        i = 0;
      }
      break;
    }
    int i = -1;
    switch (i)
    {
    default: 
      return "";
    case 4: 
      return "5+岁";
    case 3: 
      return "4-5岁";
    case 2: 
      return "3-4岁";
    case 1: 
      return "2-3岁";
    }
    return "1-2岁";
  }
  
  public static boolean jdMethod_else()
  {
    return jdMethod_boolean("infix_state");
  }
  
  private static boolean jdMethod_extends(String paramString)
  {
    return "0".equals(at.jdMethod_if(paramString, App.jdMethod_do().jdField_implements)) ^ true;
  }
  
  public static boolean jdMethod_final()
  {
    return jdMethod_boolean("ad_wall_state");
  }
  
  public static boolean jdMethod_final(String paramString)
  {
    return "ad".equals(paramString);
  }
  
  public static boolean jdMethod_float()
  {
    return jdMethod_default("ad_banner");
  }
  
  public static boolean jdMethod_float(String paramString)
  {
    return "selfad".equals(paramString);
  }
  
  public static List<String> jdMethod_for()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = App.jdMethod_do().getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    int j = ((List)localObject).size();
    while (i < j)
    {
      String str = ((PackageInfo)((List)localObject).get(i)).packageName;
      if (str.contains("com.sinyee")) {
        localArrayList.add(str);
      }
      i += 1;
    }
    if (e.jdMethod_do("com.mampod.ergedd")) {
      localArrayList.add("com.mampod.ergedd");
    }
    return localArrayList;
  }
  
  public static void jdMethod_for(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    }
    com.babybus.h.a.jdMethod_do().jdMethod_do("7801865ED51C26915AD95DBF252DE4D0", jdMethod_new(paramInt));
  }
  
  public static void jdMethod_for(String paramString1, String paramString2)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString2))
      {
        String[] arrayOfString = paramString2.trim().split(goto);
        String str = arrayOfString[0];
        paramString2 = "";
        if (arrayOfString.length == 3) {
          paramString2 = arrayOfString[2];
        }
        if ("2".equals(str))
        {
          com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, "退屏", paramString2);
          return;
        }
        if ("3".equals(str))
        {
          com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, "插屏", paramString2);
          return;
        }
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean jdMethod_for(String paramString)
  {
    return false;
  }
  
  public static String jdMethod_goto(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = jdMethod_void(paramString.trim());
      if (paramString.length > 1) {
        return paramString[1];
      }
    }
    return "";
  }
  
  public static boolean jdMethod_goto()
  {
    return jdMethod_boolean("ad_infix_state");
  }
  
  public static WindowManager.LayoutParams jdMethod_if(Integer paramInteger)
  {
    if (paramInteger.intValue() == do)
    {
      paramInteger = new WindowManager.LayoutParams();
      paramInteger.gravity = 51;
    }
    else if (paramInteger.intValue() == if)
    {
      paramInteger = new WindowManager.LayoutParams();
      paramInteger.gravity = 49;
    }
    else if (paramInteger.intValue() == for)
    {
      paramInteger = new WindowManager.LayoutParams();
      paramInteger.gravity = 53;
    }
    else if (paramInteger.intValue() == int)
    {
      paramInteger = new WindowManager.LayoutParams();
      paramInteger.gravity = 83;
    }
    else if (paramInteger.intValue() == new)
    {
      paramInteger = new WindowManager.LayoutParams();
      paramInteger.gravity = 81;
    }
    else if (paramInteger.intValue() == try)
    {
      paramInteger = new WindowManager.LayoutParams();
      paramInteger.gravity = 85;
    }
    else
    {
      paramInteger = null;
    }
    paramInteger.flags = 552;
    paramInteger.width = -2;
    paramInteger.height = -2;
    return paramInteger;
  }
  
  public static String jdMethod_if()
  {
    if ((!"A023_SDK".equals(App.jdMethod_do().jdField_else)) && (!"A017".equals(App.jdMethod_do().jdField_else)) && (!"A016".equals(App.jdMethod_do().jdField_else)))
    {
      if ("A023".equals(App.jdMethod_do().jdField_else)) {
        return "12";
      }
      return "2";
    }
    return "0";
  }
  
  public static void jdMethod_if(int paramInt)
  {
    com.babybus.h.a.jdMethod_do().jdMethod_do("1412D5C906AC3C4A0B8F0C8DE2CB92E8", jdMethod_new(paramInt));
  }
  
  public static void jdMethod_if(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    default: 
      return;
    }
    com.babybus.h.a.jdMethod_do().jdMethod_do("5E36BB93D5ABD0C078B6609FE341FF91", jdMethod_new(paramInt), paramString);
  }
  
  public static void jdMethod_if(String paramString1, String paramString2)
  {
    paramString2 = jdMethod_try(paramString2);
    com.babybus.h.a.jdMethod_do().jdMethod_do("9E7F27AA000DFCA27D886F652232293D", paramString1, paramString2);
  }
  
  public static boolean jdMethod_if(String paramString)
  {
    int i = paramString.indexOf("selfad");
    boolean bool = false;
    if (i > 0) {
      return false;
    }
    if (paramString.indexOf("|") > 0)
    {
      if (paramString.indexOf("ad") > 0) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  public static boolean jdMethod_import()
  {
    return jdMethod_boolean("ad_rest_state");
  }
  
  public static boolean jdMethod_import(String paramString)
  {
    return "3".equals(paramString);
  }
  
  public static void jdMethod_int(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    }
    com.babybus.h.a.jdMethod_do().jdMethod_do("966B60711E1BBFBE56CCA8C2D814EE3A", jdMethod_new(paramInt));
  }
  
  public static void jdMethod_int(String paramString1, String paramString2)
  {
    try
    {
      if ((TextUtils.isEmpty(paramString2)) || (jdMethod_static(paramString2))) {
        return;
      }
      String str2 = paramString2.trim();
      try
      {
        String[] arrayOfString = str2.split(goto);
        String str3 = arrayOfString[0];
        String str1 = arrayOfString[1];
        paramString2 = "";
        if (arrayOfString.length == 3)
        {
          paramString2 = arrayOfString[2];
        }
        else if (arrayOfString.length == 4)
        {
          paramString2 = arrayOfString[3];
          str1 = arrayOfString[2];
        }
        if ("selfad".equals(str1))
        {
          com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, paramString2);
          return;
        }
        if ((String.valueOf(8).equals(str3)) && ("大全".equals(str1)))
        {
          com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1);
          return;
        }
        if (TextUtils.isEmpty(paramString2))
        {
          com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, str1);
          return;
        }
        if ("福利".equals(str1))
        {
          com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, paramString2);
          return;
        }
        com.babybus.h.a.jdMethod_do().jdMethod_do(paramString1, str1, paramString2);
        return;
      }
      catch (Exception paramString2)
      {
        paramString1 = str2;
      }
      paramString2.printStackTrace();
    }
    catch (Exception localException)
    {
      paramString1 = paramString2;
      paramString2 = localException;
    }
    paramString2 = new StringBuilder();
    paramString2.append("adInfo error = ");
    paramString2.append(paramString1);
    z.jdMethod_new(paramString2.toString());
  }
  
  public static boolean jdMethod_int()
  {
    return jdMethod_boolean("startup_state");
  }
  
  public static boolean jdMethod_int(String paramString)
  {
    int i = paramString.indexOf(goto);
    boolean bool = false;
    if (i > 0)
    {
      if (paramString.indexOf("默认图") > 0) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  public static boolean jdMethod_long()
  {
    return (jdMethod_else()) || (jdMethod_goto());
  }
  
  public static boolean jdMethod_long(String paramString)
  {
    return "通龄".equals(jdMethod_goto(paramString));
  }
  
  public static String native(String paramString)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 50: 
      if (paramString.equals("2")) {
        i = 2;
      }
      break;
    case 49: 
      if (paramString.equals("1")) {
        i = 1;
      }
      break;
    case 48: 
      if (paramString.equals("0")) {
        i = 0;
      }
      break;
    }
    int i = -1;
    switch (i)
    {
    default: 
      return "";
    case 2: 
      return "打开网页";
    case 1: 
      return "直接下载";
    }
    return "不操作";
  }
  
  public static boolean native()
  {
    return jdMethod_boolean("ad_unlock_state");
  }
  
  public static int jdMethod_new(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return 0;
    }
    if (paramString.indexOf("|") > 0) {
      return Integer.parseInt(paramString.split(goto)[0]);
    }
    return 0;
  }
  
  public static String jdMethod_new(int paramInt)
  {
    if (paramInt != 14)
    {
      switch (paramInt)
      {
      default: 
        return "";
      case 11: 
        return "欢迎界面定制广告开关";
      case 10: 
        return "休息界面";
      case 9: 
        return "解锁";
      case 8: 
        return "博士帽";
      case 7: 
        return "巴士车盒子";
      case 6: 
        return "通知栏推送";
      case 5: 
        return "游戏内互推";
      case 4: 
        return "左下角";
      case 3: 
        return "家长中心插屏";
      case 2: 
        return "退屏";
      }
      return "开屏";
    }
    return "福利时间";
  }
  
  public static void jdMethod_new(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("AioloInfo == ");
    localStringBuilder.append(paramString2);
    z.jdMethod_for(localStringBuilder.toString());
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)) && (!jdMethod_static(paramString2)))
      {
        paramString2 = paramString2.trim().split(goto);
        if (paramString2.length == 3)
        {
          localStringBuilder = paramString2[0];
          localStringBuilder = paramString2[1];
          paramString2 = paramString2[2];
          com.babybus.h.a.jdMethod_do().jdMethod_if(paramString1, localStringBuilder, paramString2);
          return;
        }
        if (paramString2.length == 4)
        {
          localStringBuilder = paramString2[0];
          localStringBuilder = paramString2[1];
          Object localObject = paramString2[2];
          paramString2 = paramString2[3];
          com.babybus.h.a.jdMethod_do().jdMethod_if(paramString1, localStringBuilder, paramString2);
          return;
        }
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean jdMethod_new()
  {
    return jdMethod_boolean("ad_startup_state");
  }
  
  public static String jdMethod_public(String paramString)
  {
    if (e.jdMethod_do(paramString)) {
      return "打开";
    }
    if (e.jdMethod_goto(paramString)) {
      return "安装";
    }
    if (ad.jdMethod_case()) {
      return "跳转渠道";
    }
    return "下载";
  }
  
  public static boolean jdMethod_public()
  {
    return jdMethod_boolean("ad_custom_state");
  }
  
  public static boolean jdMethod_return()
  {
    return (TextUtils.equals("A005", App.jdMethod_do().jdField_else)) || (TextUtils.equals("A030", App.jdMethod_do().jdField_else));
  }
  
  public static boolean jdMethod_return(String paramString)
  {
    return "1".equals(paramString);
  }
  
  public static boolean jdMethod_short()
  {
    return jdMethod_default("third_banner_state");
  }
  
  public static boolean jdMethod_short(String paramString)
  {
    return "1".equals(paramString);
  }
  
  public static boolean jdMethod_static()
  {
    return (TextUtils.equals("A023", App.jdMethod_do().jdField_else)) || (TextUtils.equals("A023_SDK", App.jdMethod_do().jdField_else));
  }
  
  public static boolean jdMethod_static(String paramString)
  {
    return paramString.startsWith("{");
  }
  
  public static boolean jdMethod_super()
  {
    return (jdMethod_short()) || (jdMethod_float()) || (const());
  }
  
  public static boolean jdMethod_super(String paramString)
  {
    return "2".equals(paramString);
  }
  
  public static void jdMethod_switch(String paramString)
  {
    String str;
    if (ad.jdMethod_case()) {
      str = "850859751F51C1E717DB09B81A563F91";
    } else {
      str = "D76AD032E88F1E410DE5EEF9217E7586";
    }
    com.babybus.h.a.jdMethod_do().jdMethod_do(str, paramString);
  }
  
  public static boolean jdMethod_this()
  {
    return jdMethod_boolean("welcome_re_state");
  }
  
  public static boolean jdMethod_this(String paramString)
  {
    return "默认图".equals(jdMethod_goto(paramString));
  }
  
  public static boolean jdMethod_throw()
  {
    return "1".equals(at.jdMethod_if("pay4ad_state", "0"));
  }
  
  public static boolean jdMethod_throw(String paramString)
  {
    return "0".equals(paramString);
  }
  
  public static void throws(String paramString)
  {
    if (ag.jdMethod_new()) {
      str = "数据网络";
    } else if (ag.jdMethod_do()) {
      str = "WIFI";
    } else {
      str = "无网络";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("非跳转渠道_");
    localStringBuilder.append(str);
    String str = localStringBuilder.toString();
    com.babybus.h.a.jdMethod_do().jdMethod_do("5512C49E64752F0BC98DB17C014EB623", str, paramString);
  }
  
  public static String jdMethod_try(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramString = paramString.substring(paramString.lastIndexOf("|") + 1);
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    paramString = "";
    if (!TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    return "";
  }
  
  public static void jdMethod_try(String paramString1, String paramString2)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("aiolos adInfo == ");
    ((StringBuilder)localObject).append(paramString1);
    z.jdMethod_for(((StringBuilder)localObject).toString());
    try
    {
      if ((TextUtils.isEmpty(paramString1)) || (jdMethod_static(paramString1))) {
        return;
      }
      localObject = paramString1.trim();
      try
      {
        paramString1 = ((String)localObject).split(goto);
        int i = jdMethod_new((String)localObject);
        if (paramString1.length == 3)
        {
          if (jdMethod_final(paramString1[1])) {
            return;
          }
          jdMethod_do(i, "", paramString2);
          return;
        }
        if (paramString1.length == 4)
        {
          if (jdMethod_final(paramString1[2])) {
            return;
          }
          jdMethod_do(i, paramString1[1], paramString2);
          return;
        }
        if (paramString1.length != 2) {
          return;
        }
        if ("大全".equals(paramString1[1]))
        {
          jdMethod_do(i, "博士帽大全", paramString2);
          return;
        }
        if (!"selfad".equals(paramString1[1])) {
          return;
        }
        jdMethod_do(i, "下一个学习目标", paramString2);
        return;
      }
      catch (Exception paramString2)
      {
        paramString1 = (String)localObject;
      }
      paramString2.printStackTrace();
    }
    catch (Exception paramString2) {}
    paramString2 = new StringBuilder();
    paramString2.append("adInfo error = ");
    paramString2.append(paramString1);
    z.jdMethod_new(paramString2.toString());
  }
  
  public static boolean jdMethod_try()
  {
    return (jdMethod_extends("third_startup_state")) && (jdMethod_byte());
  }
  
  public static boolean jdMethod_void()
  {
    return (jdMethod_boolean("welcome_re_state")) && ("1".equals(at.jdMethod_if("welcome_re_state", "")));
  }
  
  public static String[] jdMethod_void(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString.split(goto);
    }
    return new String[0];
  }
  
  public static boolean jdMethod_while()
  {
    return jdMethod_boolean("ad_parent_banner_state");
  }
  
  public static boolean jdMethod_while(String paramString)
  {
    return "1".equals(paramString);
  }
}
