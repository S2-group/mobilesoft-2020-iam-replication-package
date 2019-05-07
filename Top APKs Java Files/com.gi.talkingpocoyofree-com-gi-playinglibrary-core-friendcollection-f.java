package com.gi.playinglibrary.core.friendcollection;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

public class f
{
  public String a = "";
  public String b = "";
  public String c = "";
  public int d = 0;
  public Object e;
  
  public f()
  {
    this("", "", "", 0, null);
  }
  
  public f(String paramString1, String paramString2, String paramString3, int paramInt, Object paramObject)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
    this.d = paramInt;
    this.e = paramObject;
  }
  
  public static ArrayList<f> a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((!paramBoolean) && (localPackageInfo.versionName == null)) {}
      for (;;)
      {
        i += 1;
        break;
        f localF = new f();
        localF.b = localPackageInfo.packageName;
        localArrayList.add(localF);
      }
    }
    return localArrayList;
  }
}
