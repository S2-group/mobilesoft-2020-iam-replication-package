package com.babybus.h;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.babybus.app.App;
import com.babybus.i.at;
import java.util.List;

public class c
{
  public c() {}
  
  public static void jdMethod_do()
  {
    long l1 = at.jdMethod_if("END_TIME", -1L);
    long l2 = System.currentTimeMillis();
    if (l1 < 0L)
    {
      at.jdMethod_do("START_TIME", l2);
      return;
    }
    l1 = l2 - l1;
    if (l1 < 0L)
    {
      at.jdMethod_if("END_TIME");
      at.jdMethod_if("GAME_TIME");
      return;
    }
    if (l1 < 30000L)
    {
      at.jdMethod_if("END_TIME");
      at.jdMethod_if("GAME_TIME");
      return;
    }
    jdMethod_for();
  }
  
  public static void jdMethod_do(long paramLong)
  {
    at.jdMethod_if("START_TIME");
    at.jdMethod_if("END_TIME");
    at.jdMethod_if("GAME_TIME");
  }
  
  public static void jdMethod_for()
  {
    long l = at.jdMethod_if("GAME_TIME", -1L);
    if (l > 0L) {
      jdMethod_do(l);
    }
    at.jdMethod_do("START_TIME", System.currentTimeMillis());
    at.jdMethod_if("END_TIME");
  }
  
  public static void jdMethod_if()
  {
    long l1 = System.currentTimeMillis();
    long l2 = at.jdMethod_if("START_TIME", -1L);
    long l3 = l1 - l2;
    if ((l2 > 0L) && (l3 > 0L))
    {
      at.jdMethod_do("END_TIME", l1);
      at.jdMethod_do("GAME_TIME", l3);
      return;
    }
    at.jdMethod_if("START_TIME");
    at.jdMethod_if("END_TIME");
    at.jdMethod_if("GAME_TIME");
  }
  
  public static void jdMethod_int()
  {
    Object localObject = App.jdMethod_do().getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    int k;
    for (int j = 0; i < ((List)localObject).size(); j = k)
    {
      k = j;
      if (((PackageInfo)((List)localObject).get(i)).packageName.contains("com.sinyee.babybus"))
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
    localObject = a.jdMethod_do();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(j);
    localStringBuilder.append("");
    ((a)localObject).jdMethod_do("2143F24D68D47C232CC89B67B610EA89", localStringBuilder.toString());
  }
}
