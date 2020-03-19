package com.oceanwing.a.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.List;

public class c
{
  public static String a = "";
  
  public static String a()
  {
    Object localObject2 = a;
    Object localObject1 = localObject2;
    Object localObject3 = localObject2;
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject1 = localObject2;
          localObject3 = ((String)localObject2).split("-");
          if (localObject3 != null)
          {
            localObject1 = localObject2;
            if (localObject3.length <= 0) {
              return localObject2;
            }
            int j = 0;
            localObject1 = localObject2;
            String[] arrayOfString = localObject3[0].split("[.]");
            localObject3 = localObject2;
            if (arrayOfString != null)
            {
              localObject1 = localObject2;
              int k = arrayOfString.length;
              int i = 3;
              if (k > 3) {
                break label268;
              }
              localObject1 = localObject2;
              i = arrayOfString.length;
              break label268;
              localObject2 = localObject1;
              if (j < i)
              {
                localObject1 = localObject2;
                localObject3 = new StringBuilder();
                localObject1 = localObject2;
                ((StringBuilder)localObject3).append((String)localObject2);
                localObject1 = localObject2;
                ((StringBuilder)localObject3).append(arrayOfString[j]);
                localObject1 = localObject2;
                localObject2 = ((StringBuilder)localObject3).toString();
                j += 1;
                localObject1 = localObject2;
                continue;
              }
              localObject1 = localObject2;
              localObject2 = ((String)localObject2).replaceAll("[^\\d]", "");
              localObject1 = localObject2;
              try
              {
                i = ((String)localObject2).length();
                if (i < 4)
                {
                  localObject1 = localObject2;
                  localObject3 = new StringBuilder();
                  localObject1 = localObject2;
                  ((StringBuilder)localObject3).append("0");
                  localObject1 = localObject2;
                  ((StringBuilder)localObject3).append((String)localObject2);
                  localObject1 = localObject2;
                  localObject2 = ((StringBuilder)localObject3).toString();
                  i += 1;
                  continue;
                }
                return localObject2;
              }
              catch (Exception localException1) {}
            }
          }
          else
          {
            return localException1;
          }
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        localObject3 = localObject1;
      }
      return localObject3;
      label268:
      localObject1 = "";
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (paramString.equals(((PackageInfo)paramContext.get(i)).packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static void b(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }
}
