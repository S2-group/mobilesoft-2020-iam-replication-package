package com.babybus.k;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import com.babybus.app.App;
import com.babybus.h.h;
import java.util.ArrayList;
import java.util.List;

public class a
{
  public static int jdField_byte = 7;
  public static int jdField_case = 8;
  public static int jdField_char = 9;
  public static int jdField_do = 1;
  public static int jdField_else = 10;
  public static int jdField_for;
  private static String jdField_goto = "\\|";
  public static int jdField_if = 2;
  public static int jdField_int;
  public static int jdField_new;
  public static int jdField_try;
  
  static
  {
    for = 3;
    int = 4;
    new = 5;
    try = 6;
  }
  
  public a() {}
  
  public static boolean jdMethod_break(String paramString)
  {
    return "默认图".equals(jdMethod_this(paramString));
  }
  
  public static void jdMethod_byte(String paramString)
  {
    int i = jdMethod_new(paramString);
    String str = "";
    switch (i)
    {
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(str)) {
        jdMethod_for(str, paramString);
      }
      return;
      str = "51EB88C50238E99EB60CCE90DF1C4BF4";
      continue;
      jdMethod_case(paramString);
    }
  }
  
  public static boolean jdMethod_byte()
  {
    return !"0".equals(al.jdMethod_if("welcome_re_state", jdMethod_do()));
  }
  
  public static void jdMethod_case(String paramString)
  {
    u.jdMethod_for("sendUM4Uninstall = " + paramString);
    if ((TextUtils.isEmpty(goto)) || (paramString.split(goto).length != 4)) {
      return;
    }
    paramString = paramString.split(goto);
    com.babybus.j.a.jdMethod_do().jdMethod_do("FB92BCE53C8C91F11B1F12FE511D4766", paramString[1], paramString[3]);
    com.babybus.j.a.jdMethod_do().jdMethod_do("C605B8D0B7AF03E42976957DFEC6A07E", paramString[2]);
  }
  
  public static boolean jdMethod_case()
  {
    return (h.jdMethod_do().jdMethod_do("VideoOl")) && ("1".equals(al.jdMethod_if("mv_re_state", jdMethod_do())));
  }
  
  public static String[] jdMethod_catch(String paramString)
  {
    String[] arrayOfString = new String[0];
    if (!TextUtils.isEmpty(paramString)) {
      arrayOfString = paramString.split(goto);
    }
    return arrayOfString;
  }
  
  public static void jdMethod_char(String paramString)
  {
    u.jdMethod_for("sendUM4UninstallComplete = " + paramString);
    if ((TextUtils.isEmpty(goto)) || (paramString.split(goto).length != 4)) {
      return;
    }
    paramString = paramString.split(goto);
    com.babybus.j.a.jdMethod_do().jdMethod_do("73710E6383909444E1212D14505493E7", paramString[1], paramString[3]);
    com.babybus.j.a.jdMethod_do().jdMethod_do("6DF74A629FBBC344AB25D3E690E054F5", paramString[2]);
  }
  
  public static boolean jdMethod_char()
  {
    return !"0".equals(al.jdMethod_if("banner", jdMethod_if()));
  }
  
  public static String jdMethod_class(String paramString)
  {
    if (App.goto) {
      return s.jdMethod_do().jdMethod_do(paramString);
    }
    return al.jdMethod_if(paramString, "");
  }
  
  public static long const(String paramString)
  {
    paramString = jdMethod_class(paramString);
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
  
  public static FrameLayout.LayoutParams jdMethod_do(Integer paramInteger)
  {
    FrameLayout.LayoutParams localLayoutParams = null;
    if (paramInteger.intValue() == do)
    {
      localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
      localLayoutParams.gravity = 51;
    }
    do
    {
      return localLayoutParams;
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
        paramInteger.topMargin = aq.jdMethod_try((int)(App.jdMethod_do().jdField_catch * 0.6F));
        return paramInteger;
      }
    } while (paramInteger.intValue() != else);
    paramInteger = new FrameLayout.LayoutParams(-2, -2);
    paramInteger.gravity = 53;
    paramInteger.topMargin = aq.jdMethod_try((int)(App.jdMethod_do().jdField_catch * 0.6F));
    return paramInteger;
  }
  
  public static String jdMethod_do()
  {
    if (("A023".equals(App.jdMethod_do().jdField_else)) || ("A017".equals(App.jdMethod_do().jdField_else)) || ("A016".equals(App.jdMethod_do().jdField_else))) {
      return "0";
    }
    return "1";
  }
  
  public static String jdMethod_do(int paramInt)
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
  
  public static void jdMethod_do(int paramInt, long paramLong)
  {
    String str2 = "";
    String str1;
    if (1 == paramInt)
    {
      str2 = "ae9eecd99c0d46cea8dcf9156b75df61";
      str1 = "";
      if (paramLong > 86400L) {
        break label88;
      }
      str1 = "0-24小时";
    }
    for (;;)
    {
      if ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(str1))) {
        com.babybus.j.a.jdMethod_do().jdMethod_do(str2, str1);
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
      label88:
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
  
  private static void jdMethod_do(int paramInt, String paramString1, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    switch (paramInt)
    {
    default: 
      str1 = str2;
    }
    while (TextUtils.isEmpty(str1))
    {
      return;
      str1 = "ZMT001";
      continue;
      str1 = "ZMT002";
      continue;
      str1 = "ZMT003";
      continue;
      str1 = "ZMT006";
    }
    str2 = jdMethod_import(paramString2);
    if (TextUtils.isEmpty(paramString1))
    {
      com.babybus.j.a.jdMethod_do().jdMethod_do(str1, paramString2, str2, "");
      return;
    }
    com.babybus.j.a.jdMethod_do().jdMethod_do(str1, paramString1, paramString2, str2);
  }
  
  public static void jdMethod_do(String paramString1, String paramString2, String paramString3)
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
      com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, str, paramString3);
      return;
      if (paramString2.contains("openbox.mobilem.360.cn")) {
        str = "360";
      }
    }
  }
  
  public static boolean jdMethod_do(String paramString)
  {
    long l = System.currentTimeMillis() / 1000L;
    u.jdMethod_for("startTime = " + paramString);
    u.jdMethod_for("currentTime = " + l);
    if (l < Long.parseLong(paramString)) {}
    for (boolean bool = true;; bool = false)
    {
      u.jdMethod_for("notStarted = " + bool);
      return bool;
    }
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
  
  public static String jdMethod_double(String paramString)
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
        if (paramString.equals("0"))
        {
          i = 0;
          continue;
          if (paramString.equals("1"))
          {
            i = 1;
            continue;
            if (paramString.equals("2")) {
              i = 2;
            }
          }
        }
        break;
      }
    }
    return "不操作";
    return "直接下载";
    return "打开网页";
  }
  
  public static void jdMethod_else(String paramString)
  {
    paramString = aq.jdMethod_try() + "_" + paramString;
    com.babybus.j.a.jdMethod_do().jdMethod_do("907490f773b446078dd6825811be5dfd", paramString);
  }
  
  public static boolean jdMethod_else()
  {
    return "1".equals(al.jdMethod_if("pay4ad_state", "1"));
  }
  
  public static int jdMethod_final(String paramString)
  {
    paramString = jdMethod_class(paramString);
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
  
  public static boolean jdMethod_float(String paramString)
  {
    return "thirdad".equals(paramString);
  }
  
  public static List<String> jdMethod_for()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = App.jdMethod_do().getPackageManager().getInstalledPackages(0);
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
    if (d.jdMethod_do("com.mampod.ergedd")) {
      localArrayList.add("com.mampod.ergedd");
    }
    return localArrayList;
  }
  
  public static void jdMethod_for(String paramString1, String paramString2)
  {
    Object localObject = paramString2;
    String str2;
    String str1;
    try
    {
      if (TextUtils.isEmpty(paramString2)) {
        return;
      }
      localObject = paramString2;
      str2 = paramString2.trim();
      localObject = str2;
      String[] arrayOfString = str2.split(goto);
      String str3 = arrayOfString[0];
      str1 = arrayOfString[1];
      paramString2 = "";
      localObject = str2;
      if (arrayOfString.length == 3) {
        paramString2 = arrayOfString[2];
      }
      for (;;)
      {
        localObject = str2;
        if (!"selfad".equals(str1)) {
          break;
        }
        localObject = str2;
        com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, paramString2);
        return;
        localObject = str2;
        if (arrayOfString.length == 4)
        {
          paramString2 = arrayOfString[3];
          str1 = arrayOfString[2];
        }
      }
      localObject = str2;
      if (String.valueOf(8).equals(str3))
      {
        localObject = str2;
        if ("大全".equals(str1))
        {
          localObject = str2;
          com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1);
          return;
        }
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      u.jdMethod_new("adInfo error = " + (String)localObject);
      return;
    }
    localObject = str2;
    if (TextUtils.isEmpty(paramString2))
    {
      localObject = str2;
      com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, str1);
      return;
    }
    localObject = str2;
    if ("福利".equals(str1))
    {
      localObject = str2;
      com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, paramString2);
      return;
    }
    localObject = str2;
    com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, str1, paramString2);
  }
  
  public static boolean jdMethod_for(String paramString)
  {
    return false;
  }
  
  public static void jdMethod_goto(String paramString)
  {
    paramString = aq.jdMethod_try() + "_" + paramString;
    com.babybus.j.a.jdMethod_do().jdMethod_do("c0a2c8ff8cd84dd38a0f0edb7f3fc08a", paramString);
  }
  
  public static boolean jdMethod_goto()
  {
    return ("A005".equals(App.jdMethod_do().jdField_else)) || ("A030".equals(App.jdMethod_do().jdField_else));
  }
  
  public static WindowManager.LayoutParams jdMethod_if(Integer paramInteger)
  {
    WindowManager.LayoutParams localLayoutParams = null;
    if (paramInteger.intValue() == do)
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
      if (paramInteger.intValue() == if)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 49;
      }
      else if (paramInteger.intValue() == for)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 53;
      }
      else if (paramInteger.intValue() == int)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 83;
      }
      else if (paramInteger.intValue() == new)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 81;
      }
      else if (paramInteger.intValue() == try)
      {
        localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.gravity = 85;
      }
    }
  }
  
  public static String jdMethod_if()
  {
    if (("A023".equals(App.jdMethod_do().jdField_else)) || ("A017".equals(App.jdMethod_do().jdField_else)) || ("A016".equals(App.jdMethod_do().jdField_else))) {
      return "0";
    }
    return "2";
  }
  
  public static void jdMethod_if(String paramString1, String paramString2)
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
          com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, "退屏", paramString2);
          return;
        }
        if ("3".equals(str))
        {
          com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, "插屏", paramString2);
          return;
        }
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void jdMethod_if(String paramString1, String paramString2, String paramString3)
  {
    u.jdMethod_for("adInfo == " + paramString2);
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))
      {
        paramString2 = paramString2.trim().split(goto);
        if (paramString2.length == 4)
        {
          String str = paramString2[0];
          str = paramString2[1];
          Object localObject = paramString2[2];
          paramString2 = paramString2[3];
          com.babybus.j.a.jdMethod_do().jdMethod_do(paramString1, str, paramString2, paramString3);
        }
      }
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean jdMethod_if(String paramString)
  {
    if (paramString.indexOf("selfad") > 0) {}
    while ((paramString.indexOf("|") <= 0) || (paramString.indexOf("ad") <= 0)) {
      return false;
    }
    return true;
  }
  
  private static String jdMethod_import(String paramString)
  {
    if (d.jdMethod_do(paramString)) {
      return "打开";
    }
    if (d.jdMethod_else(paramString)) {
      return "安装";
    }
    if (x.jdMethod_byte()) {
      return "跳转渠道";
    }
    return "下载";
  }
  
  public static void jdMethod_int(String paramString1, String paramString2)
  {
    u.jdMethod_for("AioloInfo == " + paramString2);
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))
      {
        paramString2 = paramString2.trim().split(goto);
        String str;
        if (paramString2.length == 3)
        {
          str = paramString2[0];
          str = paramString2[1];
          paramString2 = paramString2[2];
          com.babybus.j.a.jdMethod_do().jdMethod_if(paramString1, str, paramString2);
          return;
        }
        if (paramString2.length == 4)
        {
          str = paramString2[0];
          str = paramString2[1];
          Object localObject = paramString2[2];
          paramString2 = paramString2[3];
          com.babybus.j.a.jdMethod_do().jdMethod_if(paramString1, str, paramString2);
          return;
        }
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static boolean jdMethod_int()
  {
    return "1".equals(al.jdMethod_if("startup_state", jdMethod_do()));
  }
  
  public static boolean jdMethod_int(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString.indexOf(goto) > 0)
    {
      bool1 = bool2;
      if (paramString.indexOf("默认图") > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static String jdMethod_long(String paramString)
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
  
  public static int jdMethod_new(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (paramString.indexOf("|") <= 0) {
      return 0;
    }
    return Integer.parseInt(paramString.split(goto)[0]);
  }
  
  public static void jdMethod_new(String paramString1, String paramString2)
  {
    u.jdMethod_for("aiolos adInfo == " + paramString1);
    String str = paramString1;
    String[] arrayOfString;
    int i;
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        return;
      }
      str = paramString1;
      paramString1 = paramString1.trim();
      str = paramString1;
      arrayOfString = paramString1.split(goto);
      str = paramString1;
      i = jdMethod_new(paramString1);
      str = paramString1;
      if (arrayOfString.length == 3)
      {
        str = paramString1;
        if (jdMethod_short(arrayOfString[1])) {
          return;
        }
        str = paramString1;
        jdMethod_do(i, "", paramString2);
        return;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      u.jdMethod_new("adInfo error = " + str);
      return;
    }
    str = paramString1;
    if (arrayOfString.length == 4)
    {
      str = paramString1;
      if (!jdMethod_short(arrayOfString[2]))
      {
        str = paramString1;
        jdMethod_do(i, arrayOfString[1], paramString2);
      }
    }
    else
    {
      str = paramString1;
      if (arrayOfString.length == 2)
      {
        str = paramString1;
        if ("大全".equals(arrayOfString[1]))
        {
          str = paramString1;
          jdMethod_do(i, "博士帽大全", paramString2);
          return;
        }
        str = paramString1;
        if ("selfad".equals(arrayOfString[1]))
        {
          str = paramString1;
          jdMethod_do(i, "下一个学习目标", paramString2);
        }
      }
    }
  }
  
  public static boolean jdMethod_new()
  {
    return "1".equals(al.jdMethod_if("shutdown_state", jdMethod_do()));
  }
  
  public static boolean jdMethod_short(String paramString)
  {
    return "ad".equals(paramString);
  }
  
  public static boolean jdMethod_super(String paramString)
  {
    return "selfad".equals(paramString);
  }
  
  public static String jdMethod_this(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = jdMethod_catch(paramString.trim());
      if (paramString.length > 1) {
        return paramString[1];
      }
    }
    return "";
  }
  
  public static boolean jdMethod_throw(String paramString)
  {
    return "1".equals(paramString);
  }
  
  public static String jdMethod_try(String paramString)
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
  
  public static boolean jdMethod_try()
  {
    return "1".equals(al.jdMethod_if("infix_state", jdMethod_do()));
  }
  
  public static boolean jdMethod_void(String paramString)
  {
    return "通龄".equals(jdMethod_this(paramString));
  }
  
  public static boolean jdMethod_while(String paramString)
  {
    return "2".equals(paramString);
  }
}
