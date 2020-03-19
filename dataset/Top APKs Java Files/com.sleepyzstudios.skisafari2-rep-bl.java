package rep;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.colortv.android.as;
import com.colortv.android.ui.u;
import java.util.List;

public class bl
{
  public static void a(Context paramContext, as paramAs, t paramT)
  {
    List localList = paramT.d();
    if ((localList == null) || (localList.size() == 0)) {}
    for (;;)
    {
      return;
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      int k;
      for (int i = 0; i < localList.size(); i = k + 1)
      {
        t.a localA = (t.a)localList.get(i);
        if (u.a == localA.e())
        {
          int j = 0;
          int m = 0;
          for (;;)
          {
            k = i;
            if (j >= paramContext.size()) {
              break;
            }
            k = i;
            if (m != 0) {
              break;
            }
            k = i;
            if (((PackageInfo)paramContext.get(j)).packageName.equals(localA.d()))
            {
              paramAs.a(localA, 4);
              paramT.a(localA);
              k = i - 1;
              m = 1;
            }
            j += 1;
            i = k;
          }
        }
        k = i;
      }
    }
  }
  
  public static boolean a(t paramT)
  {
    switch (bm.a[paramT.a().ordinal()])
    {
    default: 
      return true;
    case 1: 
      return b(paramT);
    case 2: 
      return e(paramT);
    }
    return f(paramT);
  }
  
  private static boolean b(t paramT)
  {
    c(paramT);
    d(paramT);
    return paramT.d().size() != 0;
  }
  
  private static void c(t paramT)
  {
    List localList = paramT.d();
    int j;
    for (int i = 0; i < localList.size(); i = j + 1)
    {
      t.a localA = (t.a)localList.get(i);
      j = i;
      if (localA.e() == null)
      {
        paramT.a(localA);
        j = i - 1;
      }
    }
  }
  
  private static void d(t paramT)
  {
    List localList = paramT.d();
    int j;
    for (int i = 0; i < localList.size(); i = j + 1)
    {
      t.a localA = (t.a)localList.get(i);
      j = i;
      if (TextUtils.isEmpty(localA.k()))
      {
        j = i;
        if (TextUtils.isEmpty(localA.r()))
        {
          j = i;
          if (TextUtils.isEmpty(localA.q()))
          {
            paramT.a(localA);
            j = i - 1;
          }
        }
      }
    }
  }
  
  private static boolean e(t paramT)
  {
    d(paramT);
    return paramT.d().size() > 0;
  }
  
  private static boolean f(t paramT)
  {
    d(paramT);
    return paramT.d().size() > 0;
  }
}
