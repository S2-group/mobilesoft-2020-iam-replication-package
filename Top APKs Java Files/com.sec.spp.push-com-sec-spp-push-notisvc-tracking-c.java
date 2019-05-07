package com.sec.spp.push.notisvc.tracking;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.sec.spp.push.PushClientApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class c
{
  public static final String a = c.class.getSimpleName();
  
  public c() {}
  
  private static ArrayList a()
  {
    Object localObject = com.sec.spp.push.notisvc.e.d.y(PushClientApplication.b());
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      com.sec.spp.push.notisvc.e.b.b("Empty exclude list", a);
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      localObject = new JSONArray((String)localObject);
      int i = 0;
      while (i < ((JSONArray)localObject).length())
      {
        com.sec.spp.push.notisvc.e.b.b("Exclude App : " + ((JSONArray)localObject).getString(i).replace("*", ""), a);
        localArrayList.add(((JSONArray)localObject).getString(i).replace("*", ""));
        i += 1;
      }
      return localArrayList;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public static ArrayList a(ArrayList paramArrayList)
  {
    ArrayList localArrayList1 = a();
    ArrayList localArrayList2 = b();
    ArrayList localArrayList3 = new ArrayList();
    if ((localArrayList1 == null) && (localArrayList2 == null))
    {
      com.sec.spp.push.notisvc.e.b.b("in/exclude list both null", a);
      return paramArrayList;
    }
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      b localB = (b)paramArrayList.next();
      String str = localB.a();
      if ((b(localArrayList2, str)) || (!c(localArrayList1, str))) {
        localArrayList3.add(localB);
      }
    }
    return localArrayList3;
  }
  
  private static void a(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    if ((paramArrayList2 == null) || (paramArrayList2.size() == 0)) {
      return;
    }
    Iterator localIterator1 = PushClientApplication.b().getPackageManager().getInstalledPackages(128).iterator();
    while (localIterator1.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator1.next();
      Iterator localIterator2 = paramArrayList2.iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        if ((!TextUtils.isEmpty(str)) && (localPackageInfo.packageName.startsWith(str)) && (!a(paramArrayList1, localPackageInfo.packageName))) {
          paramArrayList1.add(new d(localPackageInfo.packageName, localPackageInfo.firstInstallTime, e.a));
        }
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    ArrayList localArrayList = a();
    if (b(b(), paramString)) {}
    while (!c(localArrayList, paramString)) {
      return true;
    }
    return false;
  }
  
  private static boolean a(ArrayList paramArrayList, String paramString)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      d localD = (d)paramArrayList.next();
      if ((paramString != null) && (paramString.equals(localD.a()))) {
        return true;
      }
    }
    return false;
  }
  
  private static ArrayList b()
  {
    Object localObject = com.sec.spp.push.notisvc.e.d.z(PushClientApplication.b());
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      com.sec.spp.push.notisvc.e.b.b("Empty include list", a);
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      localObject = new JSONArray((String)localObject);
      int i = 0;
      while (i < ((JSONArray)localObject).length())
      {
        localArrayList.add(((JSONArray)localObject).getString(i));
        i += 1;
      }
      return localArrayList;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public static ArrayList b(ArrayList paramArrayList)
  {
    ArrayList localArrayList1 = a();
    ArrayList localArrayList2 = b();
    ArrayList localArrayList3 = new ArrayList();
    if ((localArrayList1 == null) && (localArrayList2 == null))
    {
      com.sec.spp.push.notisvc.e.b.b("in/exclude list both null", a);
      return paramArrayList;
    }
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      d localD = (d)paramArrayList.next();
      String str = localD.a();
      if ((b(localArrayList2, str)) || (!c(localArrayList1, str))) {
        localArrayList3.add(localD);
      }
    }
    if (com.sec.spp.push.notisvc.e.d.A(PushClientApplication.b()) == true) {
      a(localArrayList3, localArrayList2);
    }
    return localArrayList3;
  }
  
  private static boolean b(ArrayList paramArrayList, String paramString)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {
      return false;
    }
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (paramString.startsWith((String)paramArrayList.next())) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean c(ArrayList paramArrayList, String paramString)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {
      return false;
    }
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (paramString.startsWith((String)paramArrayList.next())) {
        return true;
      }
    }
    return false;
  }
}
