package com.jb.gosms.themeplay.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.jb.gosms.themeplay.datas.c;
import com.jb.gosms.themeplay.datas.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static a V;
  private Context Code;
  private String I;
  private Comparator Z = new Comparator()
  {
    public int Code(c paramAnonymousC1, c paramAnonymousC2)
    {
      long l2 = 0L;
      if (paramAnonymousC1 == null) {}
      label96:
      do
      {
        return -1;
        if (paramAnonymousC2 == null) {
          return 1;
        }
        if ((a.Code(a.this).equals(paramAnonymousC1.V())) || (a.Code(a.this).equals(paramAnonymousC2.V()))) {
          return 1;
        }
        PackageManager localPackageManager = a.V(a.this).getPackageManager();
        try
        {
          l1 = localPackageManager.getPackageInfo(paramAnonymousC1.V(), 0).firstInstallTime;
        }
        catch (Throwable paramAnonymousC1)
        {
          for (;;)
          {
            long l3;
            long l1 = 0L;
          }
        }
        try
        {
          l3 = localPackageManager.getPackageInfo(paramAnonymousC2.V(), 0).firstInstallTime;
          l2 = l3;
        }
        catch (Throwable paramAnonymousC1)
        {
          break label96;
        }
      } while (l1 > l2);
      return 1;
    }
  };
  
  public a(Context paramContext)
  {
    this.Code = paramContext;
  }
  
  public static a Code(Context paramContext)
  {
    try
    {
      if (V == null) {
        V = new a(paramContext);
      }
      paramContext = V;
      return paramContext;
    }
    finally {}
  }
  
  private void Code(ArrayList paramArrayList)
  {
    localPackageManager = this.Code.getPackageManager();
    Object localObject1 = null;
    try
    {
      localObject2 = localPackageManager.getInstalledApplications(0);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        Object localObject2 = new e();
        Object localObject3 = ((ApplicationInfo)localObject3).packageName;
        ((e)localObject2).Code((String)localObject3);
        Object localObject4 = ((String)localObject3).substring(((String)localObject3).lastIndexOf(".") + 1, ((String)localObject3).length());
        ((e)localObject2).S((String)localObject4);
        ((e)localObject2).C((String)localObject4);
        ((e)localObject2).B(true);
        localObject4 = localPackageManager.getResourcesForApplication((String)localObject3);
        int i = ((Resources)localObject4).getIdentifier("isGetjar", "bool", (String)localObject3);
        if (i == 0) {
          break label351;
        }
        for (boolean bool = ((Resources)localObject4).getBoolean(i);; bool = false)
        {
          ((e)localObject2).S(bool);
          i = ((Resources)localObject4).getIdentifier("isGosmsInApp", "bool", (String)localObject3);
          if (i != 0) {}
          for (bool = ((Resources)localObject4).getBoolean(i);; bool = false)
          {
            ((e)localObject2).L(bool);
            int j = ((Resources)localObject4).getIdentifier("price_other", "integer", (String)localObject3);
            i = 50;
            if (j != 0) {
              i = ((Resources)localObject4).getInteger(j);
            }
            ((e)localObject2).C(i);
            if (this.I.equals(((e)localObject2).V()))
            {
              paramArrayList.add(0, localObject2);
              break label116;
            }
            paramArrayList.add(localObject2);
            break label116;
            localThrowable1 = localThrowable1;
            break;
          }
        }
      }
      catch (Throwable localThrowable2) {}
    }
    localObject2 = new ArrayList();
    if (localObject1 == null) {}
    label116:
    do
    {
      do
      {
        return;
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = (ApplicationInfo)((Iterator)localObject1).next();
          if (((ApplicationInfo)localObject3).packageName.startsWith(com.jb.gosms.font.a.D)) {
            ((List)localObject2).add(localObject3);
          }
        }
      } while (((List)localObject2).size() <= 0);
      localObject1 = ((List)localObject2).iterator();
    } while (!((Iterator)localObject1).hasNext());
    localObject3 = (ApplicationInfo)((Iterator)localObject1).next();
  }
  
  public ArrayList Code()
  {
    label240:
    for (;;)
    {
      try
      {
        ArrayList localArrayList = new ArrayList();
        this.I = this.Code.getSharedPreferences("typeface", 0).getString("fontname", com.jb.gosms.font.a.I);
        Code(localArrayList);
        Collections.sort(localArrayList, this.Z);
        e localE;
        if (com.jb.gosms.font.a.V())
        {
          localE = new e();
          localE.Code(com.jb.gosms.font.a.Z());
          localE.S(com.jb.gosms.font.a.Z());
          localE.F(com.jb.gosms.font.a.Z());
          localE.C("Roboto");
          localE.B(true);
          localE.V(true);
          if ((this.I.equals(localE.V())) || (this.I.equals(com.jb.gosms.font.a.I)) || (localArrayList.size() < 1)) {
            localArrayList.add(0, localE);
          }
        }
        else
        {
          localE = new e();
          localE.Code(com.jb.gosms.font.a.I);
          localE.S(com.jb.gosms.font.a.I);
          localE.F(com.jb.gosms.font.a.I);
          localE.C(this.Code.getResources().getString(2131297042));
          localE.B(true);
          localE.V(true);
          if ((!this.I.equals(localE.V())) && (localArrayList.size() >= 1)) {
            break label240;
          }
          localArrayList.add(0, localE);
          return localArrayList;
        }
        localArrayList.add(1, localE);
        continue;
        localObject.add(1, localE);
      }
      finally {}
    }
  }
}
