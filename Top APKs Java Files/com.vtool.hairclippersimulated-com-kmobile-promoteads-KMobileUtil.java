package com.kmobile.promoteads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.RelativeLayout;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class KMobileUtil
{
  public static int curentAge;
  public static String curentCatalog;
  public static boolean isRateDiaglogIsShow = false;
  public static boolean isShowSmallBanner;
  public static boolean isShowStartDialogAds;
  public static RelativeLayout kBanner;
  public static String linkRootListAppCSV = "http://kdata.tiasangmoi.com/kpromoteads/";
  public static int tagCatalog = 0;
  public static boolean testMode;
  public static Thread threadGetRandomBanner;
  
  static
  {
    isShowStartDialogAds = false;
    isShowSmallBanner = false;
  }
  
  public KMobileUtil() {}
  
  public static void clickAppAds(Context paramContext, KAppObject paramKAppObject)
  {
    if (KUtil.isConnectingToInternet(paramContext))
    {
      KMobileAds.goToWebLink(paramContext, paramKAppObject.getLinkTracking());
      return;
    }
    showClickAdsDiglog(paramContext, paramKAppObject);
  }
  
  public static KAppObject getAppObjectFromLine(String paramString)
  {
    paramString = paramString.split("\t");
    if (Integer.parseInt(paramString[6]) > curentAge) {
      return null;
    }
    return new KAppObject(paramString[0], paramString[1], paramString[2], paramString[3], paramString[4], paramString[5], paramString[6]);
  }
  
  public static ArrayList<KAppObject> getArrayListAppFromTSVFile(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    new KAppObject();
    int i = 0;
    List localList = paramContext.getPackageManager().getInstalledApplications(128);
    if (!KUtil.isConnectingToInternet(paramContext)) {
      i = 1;
    }
    for (;;)
    {
      paramString = localArrayList;
      Object localObject1;
      if (i == 1)
      {
        localObject1 = (ArrayList)KUtil.readObj(paramContext, "save.dat");
        paramString = localArrayList;
        if (localObject1 != null)
        {
          paramString = removeAppInstalled((ArrayList)localObject1, paramContext, localList);
          KUtil.writeObj(paramContext, paramString, "save.dat");
        }
      }
      tagCatalog = 0;
      return paramString;
      try
      {
        paramString = new BufferedInputStream(((HttpURLConnection)new URL(paramString).openConnection()).getInputStream());
      }
      catch (Exception paramString)
      {
        try
        {
          localObject1 = new BufferedReader(new InputStreamReader(paramString, "utf8"), 8192);
          ((BufferedReader)localObject1).readLine();
          for (;;)
          {
            Object localObject2 = ((BufferedReader)localObject1).readLine();
            if (localObject2 == null) {
              break;
            }
            localObject2 = getAppObjectFromLine((String)localObject2);
            if ((localObject2 != null) && (!isInstall(((KAppObject)localObject2).getIdApp(), paramContext, localList))) {
              if (((KAppObject)localObject2).getCatalog().contains(curentCatalog))
              {
                localArrayList.add(tagCatalog, localObject2);
                tagCatalog += 1;
              }
              else
              {
                localArrayList.add(localObject2);
              }
            }
          }
          if (localArrayList != null) {
            KUtil.writeObj(paramContext, localArrayList, "save.dat");
          }
          paramString.close();
        }
        catch (Exception paramString) {}
        paramString = paramString;
        i = 1;
      }
    }
  }
  
  public static boolean isInstall(String paramString, Context paramContext, List<ApplicationInfo> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if (((ApplicationInfo)paramList.get(i)).packageName.contains(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static ArrayList<KAppObject> removeAppInstalled(ArrayList<KAppObject> paramArrayList, Context paramContext, List<ApplicationInfo> paramList)
  {
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      int m = i;
      int k = j;
      if (isInstall(((KAppObject)paramArrayList.get(i)).getIdApp(), paramContext, paramList))
      {
        paramArrayList.remove(i);
        m = i - 1;
        k = j - 1;
      }
      i = m + 1;
      j = k;
    }
    return paramArrayList;
  }
  
  public static void showClickAdsDiglog(Context paramContext, KAppObject paramKAppObject)
  {
    KNotInternetAppShowActivity.appClickNotConnectInternet = paramKAppObject;
    paramContext.startActivity(new Intent(paramContext, KNotInternetAppShowActivity.class));
  }
  
  public static String upStringDes(String paramString)
  {
    String str = paramString.substring(0, 1).toUpperCase();
    return str + paramString.substring(1);
  }
  
  public static String upStringTitle(String paramString)
  {
    String[] arrayOfString = paramString.split(" ");
    paramString = "";
    int i = 0;
    if (i < arrayOfString.length)
    {
      if (i == 0) {}
      for (paramString = paramString + upStringDes(arrayOfString[i]);; paramString = paramString + " " + upStringDes(arrayOfString[i]))
      {
        i += 1;
        break;
      }
    }
    return paramString;
  }
}
