package com.babybus.i;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.j.aq;
import com.babybus.j.ax;
import java.util.List;

public class c
{
  public c() {}
  
  public static void jdMethod_do()
  {
    long l1 = aq.jdMethod_if("END_TIME", -1L);
    long l2 = System.currentTimeMillis();
    if (l1 < 0L)
    {
      aq.jdMethod_do("START_TIME", l2);
      return;
    }
    l1 = l2 - l1;
    if (l1 < 0L)
    {
      aq.jdMethod_if("END_TIME");
      aq.jdMethod_if("GAME_TIME");
      return;
    }
    if (l1 < 30000L)
    {
      aq.jdMethod_if("END_TIME");
      aq.jdMethod_if("GAME_TIME");
      return;
    }
    jdMethod_for();
  }
  
  public static void jdMethod_do(long paramLong)
  {
    String str = ax.jdMethod_if(paramLong);
    if (!TextUtils.isEmpty(str)) {
      a.jdMethod_do().jdMethod_do("ac0847f904a742ed89b375230d8302fd", ax.jdMethod_try(), str);
    }
    aq.jdMethod_if("START_TIME");
    aq.jdMethod_if("END_TIME");
    aq.jdMethod_if("GAME_TIME");
  }
  
  public static void jdMethod_for()
  {
    long l = aq.jdMethod_if("GAME_TIME", -1L);
    if (l > 0L) {
      jdMethod_do(l);
    }
    a.jdMethod_do().jdMethod_do("5cb278c2f71c490ea8657eae43590796", ax.jdMethod_try());
    aq.jdMethod_do("START_TIME", System.currentTimeMillis());
    aq.jdMethod_if("END_TIME");
  }
  
  public static void jdMethod_if()
  {
    long l1 = System.currentTimeMillis();
    long l2 = aq.jdMethod_if("START_TIME", -1L);
    if ((l2 > 0L) && (l1 - l2 > 0L))
    {
      aq.jdMethod_do("END_TIME", l1);
      aq.jdMethod_do("GAME_TIME", l1 - l2);
      return;
    }
    aq.jdMethod_if("START_TIME");
    aq.jdMethod_if("END_TIME");
    aq.jdMethod_if("GAME_TIME");
  }
  
  public static void jdMethod_int()
  {
    List localList = App.jdMethod_do().getPackageManager().getInstalledPackages(0);
    int i = 0;
    int k;
    for (int j = 0; i < localList.size(); j = k)
    {
      k = j;
      if (((PackageInfo)localList.get(i)).packageName.contains("com.sinyee.babybus"))
      {
        k = j;
        if (!TextUtils.equals("com.sinyee.babybus.recommendapp", App.jdMethod_do().jdField_try))
        {
          k = j;
          if (!TextUtils.equals("com.sinyee.babybus.chants", App.jdMethod_do().jdField_try))
          {
            k = j;
            if (!TextUtils.equals("com.sinyee.babybus.bbtime.android", App.jdMethod_do().jdField_try)) {
              k = j + 1;
            }
          }
        }
      }
      i += 1;
    }
    a.jdMethod_do().jdMethod_do("2143F24D68D47C232CC89B67B610EA89", j + "");
  }
}
