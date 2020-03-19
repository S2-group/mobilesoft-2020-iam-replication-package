package com.jb.gosms.transaction.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.jb.gosms.MmsApp;
import com.jb.gosms.util.Loger;
import com.jb.gosms.util.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static a Code;
  
  private a() {}
  
  private int B(Context paramContext)
  {
    int j = V();
    int i;
    if (j < 0) {
      i = V(paramContext);
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (j == 0);
      i = j;
    } while (Code(paramContext, j));
    return V(paramContext);
  }
  
  private int Code(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString.substring("com.jb.gosms.groupsms.helper".length()));
      return i + 1;
    }
    catch (Throwable paramString) {}
    return -1;
  }
  
  public static a Code()
  {
    try
    {
      if (Code == null) {
        Code = new a();
      }
      a localA = Code;
      return localA;
    }
    finally {}
  }
  
  private void Code(int paramInt)
  {
    MmsApp.getApplication().getSharedPreferences("groupsms_plugin_preference", 0).edit().putInt("pre_key_current_plugin_id", paramInt).commit();
  }
  
  private boolean Code(Context paramContext, int paramInt)
  {
    return w.Code(paramContext, "com.jb.gosms.groupsms.helper" + (paramInt - 1));
  }
  
  private int V()
  {
    return MmsApp.getApplication().getSharedPreferences("groupsms_plugin_preference", 0).getInt("pre_key_current_plugin_id", -1);
  }
  
  /* Error */
  public b Code(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial 103	com/jb/gosms/transaction/c/a:B	(Landroid/content/Context;)I
    //   7: istore_2
    //   8: iload_2
    //   9: ifge +9 -> 18
    //   12: aconst_null
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: new 105	com/jb/gosms/transaction/c/b
    //   21: dup
    //   22: iload_2
    //   23: invokespecial 107	com/jb/gosms/transaction/c/b:<init>	(I)V
    //   26: astore_1
    //   27: goto -13 -> 14
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	this	a
    //   0	35	1	paramContext	Context
    //   7	16	2	i	int
    // Exception table:
    //   from	to	target	type
    //   2	8	30	finally
    //   18	27	30	finally
  }
  
  public List I(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {}
    ArrayList localArrayList;
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getInstalledPackages(0);
        if (paramContext != null)
        {
          localArrayList = new ArrayList();
          paramContext = paramContext.iterator();
          while (paramContext.hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
            if ((localPackageInfo != null) && (localPackageInfo.packageName != null) && (localPackageInfo.sharedUserId != null) && (localPackageInfo.sharedUserId.contains("com.jb.gosms")) && (localPackageInfo.packageName.contains("com.jb.gosms.groupsms.helper")))
            {
              int i = Code(localPackageInfo.packageName);
              if (i > 0) {
                localArrayList.add(Integer.valueOf(i));
              }
            }
          }
        }
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          Loger.e("GroupSmsPluginEngine", "", paramContext);
          paramContext = null;
        }
        Collections.sort(localArrayList);
      }
    }
    return localArrayList;
  }
  
  public int V(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        paramContext = I(paramContext);
        if (paramContext != null)
        {
          i = paramContext.size();
          if (i != 0) {}
        }
        else
        {
          i = 0;
          return i;
        }
        int j = V();
        if ((j == -1) || (j == 0))
        {
          Code(((Integer)paramContext.get(0)).intValue());
          i = ((Integer)paramContext.get(0)).intValue();
          continue;
          if (i < paramContext.size())
          {
            if (((Integer)paramContext.get(i)).intValue() <= j) {
              break label158;
            }
            Code(((Integer)paramContext.get(i)).intValue());
            i = ((Integer)paramContext.get(i)).intValue();
            continue;
          }
          Code(0);
          i = 0;
          continue;
        }
        i = 0;
      }
      finally {}
      continue;
      label158:
      i += 1;
    }
  }
  
  public int Z(Context paramContext)
  {
    paramContext = I(paramContext);
    if (paramContext == null) {
      return 0;
    }
    return paramContext.size();
  }
}
