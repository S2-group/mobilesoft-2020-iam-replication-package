package com.babybus.j;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.k.al;
import com.babybus.k.as;
import java.util.List;

public class b
{
  public b() {}
  
  public static void jdMethod_do()
  {
    long l1 = al.jdMethod_if("END_TIME", -1L);
    long l2 = System.currentTimeMillis();
    if (l1 < 0L)
    {
      al.jdMethod_do("START_TIME", l2);
      return;
    }
    l1 = l2 - l1;
    if (l1 < 0L)
    {
      al.jdMethod_if("END_TIME");
      al.jdMethod_if("GAME_TIME");
      return;
    }
    if (l1 < 30000L)
    {
      al.jdMethod_if("END_TIME");
      al.jdMethod_if("GAME_TIME");
      return;
    }
    jdMethod_for();
  }
  
  public static void jdMethod_do(long paramLong)
  {
    String str = as.jdMethod_if(paramLong);
    if (!TextUtils.isEmpty(str)) {
      a.jdMethod_do().jdMethod_do("ac0847f904a742ed89b375230d8302fd", as.jdMethod_try(), str);
    }
    al.jdMethod_if("START_TIME");
    al.jdMethod_if("END_TIME");
    al.jdMethod_if("GAME_TIME");
  }
  
  public static void jdMethod_for()
  {
    long l = al.jdMethod_if("GAME_TIME", -1L);
    if (l > 0L) {
      jdMethod_do(l);
    }
    a.jdMethod_do().jdMethod_do("5cb278c2f71c490ea8657eae43590796", as.jdMethod_try());
    al.jdMethod_do("START_TIME", System.currentTimeMillis());
    al.jdMethod_if("END_TIME");
  }
  
  public static void jdMethod_if()
  {
    long l1 = System.currentTimeMillis();
    long l2 = al.jdMethod_if("START_TIME", -1L);
    if ((l2 > 0L) && (l1 - l2 > 0L))
    {
      al.jdMethod_do("END_TIME", l1);
      al.jdMethod_do("GAME_TIME", l1 - l2);
      return;
    }
    al.jdMethod_if("START_TIME");
    al.jdMethod_if("END_TIME");
    al.jdMethod_if("GAME_TIME");
  }
  
  public static void jdMethod_int()
  {
    Object localObject = App.jdMethod_do().getPackageManager().getInstalledPackages(0);
    int i = 0;
    int k;
    for (int j = 0; i < ((List)localObject).size(); j = k)
    {
      k = j;
      if (((PackageInfo)((List)localObject).get(i)).packageName.contains("com.sinyee.babybus")) {
        k = j + 1;
      }
      i += 1;
    }
    localObject = "";
    if ((1 <= j) && (j <= 3)) {
      localObject = "1~3款";
    }
    for (;;)
    {
      a.jdMethod_do().jdMethod_do("33a963ff9c784d7a9e6de9cc738ff6de", (String)localObject);
      return;
      if ((4 <= j) && (j <= 7)) {
        localObject = "4~7款";
      } else if ((8 <= j) && (j <= 10)) {
        localObject = "8~10款";
      } else if ((11 <= j) && (j <= 15)) {
        localObject = "11~15款";
      } else if ((16 <= j) && (j <= 20)) {
        localObject = "16~20款";
      } else if (j > 20) {
        localObject = "20+款";
      }
    }
  }
}
