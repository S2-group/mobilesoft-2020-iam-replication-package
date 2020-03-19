package com.skp.Tmap;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class TMapTapi
{
  private static final int GO_COMPANY = 2;
  private static final int GO_HOME = 1;
  private static final String INVOKE_EC = "TmapInvokeRo8627";
  private Context Tcontext;
  private String mPackageName = null;
  
  public TMapTapi(Context paramContext)
  {
    this.Tcontext = paramContext;
    TelephonyManager localTelephonyManager = (TelephonyManager)this.Tcontext.getSystemService("phone");
    String str = localTelephonyManager.getNetworkOperator();
    if (str != null)
    {
      paramContext = str;
      if (!str.equals("")) {}
    }
    else
    {
      paramContext = localTelephonyManager.getSimOperator();
    }
    if ((paramContext != null) && (!paramContext.equals("")) && (paramContext.subSequence(0, 3).equals("450")))
    {
      if ((paramContext.substring(3).equals("05")) || (paramContext.substring(3).equals("03")) || (paramContext.substring(3).equals("11"))) {
        this.mPackageName = "com.skt.skaf.l001mtm091";
      }
    }
    else {
      return;
    }
    this.mPackageName = "com.skt.skaf.l001mtm092";
  }
  
  private boolean checkTmapApplicationInstalled()
  {
    boolean bool2;
    if (this.mPackageName == null)
    {
      bool2 = false;
      return bool2;
    }
    boolean bool1 = false;
    Iterator localIterator = this.Tcontext.getPackageManager().getInstalledApplications(8192).iterator();
    for (;;)
    {
      bool2 = bool1;
      if (!localIterator.hasNext()) {
        break;
      }
      if (((ApplicationInfo)localIterator.next()).packageName.equals(this.mPackageName)) {
        bool1 = true;
      }
    }
  }
  
  private boolean invokeGo(String paramString, int paramInt)
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId)) {
      return false;
    }
    boolean bool;
    if (!checkTmapApplicationInstalled()) {
      bool = false;
    }
    for (;;)
    {
      TMapData.invokeStatistics(TMapView.bizAppId, paramString, bool);
      return bool;
      try
      {
        Object localObject = this.Tcontext.getPackageManager().getPackageInfo(this.mPackageName, 0).versionName.split("\\.");
        if (Integer.parseInt(localObject[0]) == 3) {
          return false;
        }
        int i = Integer.parseInt(localObject[0]);
        if (i == 4) {
          try
          {
            localObject = TmapCrypto.encrypt("TmapInvokeRo8627", ("tmap://goto?code=" + paramInt).getBytes());
            Intent localIntent = new Intent("android.intent.action.MAIN");
            localIntent.setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
            localIntent.putExtra("INVOKE_TMAP", (String)localObject);
            localIntent.setFlags(268435456);
            this.Tcontext.startActivity(localIntent);
            bool = true;
          }
          catch (Exception localException)
          {
            bool = false;
          }
        } else {
          bool = false;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        bool = false;
      }
    }
  }
  
  private boolean invokeRoute30(String paramString, float paramFloat1, float paramFloat2)
  {
    try
    {
      paramString = TmapCrypto.encrypt("TmapInvokeRo8627", ("A1,+" + Float.toString(paramFloat1) + ",+" + Float.toString(paramFloat2) + "," + paramString).getBytes());
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
      localIntent.putExtra("INVOKE_TMAP", paramString);
      localIntent.setFlags(268435456);
      this.Tcontext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private boolean invokeSafeDrive30()
  {
    try
    {
      String str = TmapCrypto.encrypt("TmapInvokeRo8627", "A3".getBytes());
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
      localIntent.putExtra("INVOKE_TMAP", str);
      localIntent.setFlags(268435456);
      this.Tcontext.startActivity(localIntent);
      return true;
    }
    catch (Exception localException) {}
    return false;
  }
  
  private boolean invokeSearchPortal30(String paramString)
  {
    try
    {
      paramString = TmapCrypto.encrypt("TmapInvokeRo8627", ("A4," + paramString).getBytes());
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
      localIntent.putExtra("INVOKE_TMAP", paramString);
      localIntent.setFlags(268435456);
      this.Tcontext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private boolean invokeSetLocation30(String paramString, float paramFloat1, float paramFloat2)
  {
    try
    {
      paramString = TmapCrypto.encrypt("TmapInvokeRo8627", ("A2,+" + Float.toString(paramFloat1) + ",+" + Float.toString(paramFloat2) + "," + paramString).getBytes());
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
      localIntent.putExtra("INVOKE_TMAP", paramString);
      localIntent.setFlags(268435456);
      this.Tcontext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public ArrayList<String> getTMapDownUrl()
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId) || (this.mPackageName == null)) {
      return null;
    }
    ArrayList localArrayList = null;
    if (this.mPackageName.equals("com.skt.skaf.l001mtm091"))
    {
      localArrayList = new ArrayList();
      localArrayList.add("http://m.tstore.co.kr/userpoc/mp.jsp?pid=0000163382");
    }
    for (;;)
    {
      TMapData.invokeStatistics(TMapView.bizAppId, "F1", true);
      return localArrayList;
      if (this.mPackageName.equals("com.skt.skaf.l001mtm092"))
      {
        localArrayList = new ArrayList();
        localArrayList.add("http://m.tstore.co.kr/userpoc/mp.jsp?pid=0000257761");
        localArrayList.add("market://details?id=com.skt.skaf.l001mtm092");
      }
    }
  }
  
  public boolean invokeGoCompany()
  {
    return invokeGo("D1", 2);
  }
  
  public boolean invokeGoHome()
  {
    return invokeGo("D0", 1);
  }
  
  public boolean invokeRoute(String paramString, float paramFloat1, float paramFloat2)
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId)) {
      return false;
    }
    boolean bool;
    if (!checkTmapApplicationInstalled()) {
      bool = false;
    }
    for (;;)
    {
      TMapData.invokeStatistics(TMapView.bizAppId, "A0", bool);
      return bool;
      try
      {
        Object localObject = this.Tcontext.getPackageManager().getPackageInfo(this.mPackageName, 0).versionName.split("\\.");
        if (Integer.parseInt(localObject[0]) == 3)
        {
          bool = invokeRoute30(paramString, paramFloat1, paramFloat2);
        }
        else
        {
          int i = Integer.parseInt(localObject[0]);
          if (i == 4) {
            try
            {
              paramString = TmapCrypto.encrypt("TmapInvokeRo8627", ("tmap://route?goalx=" + Float.toString(paramFloat1) + "&goaly=" + Float.toString(paramFloat2) + "&goalname=" + paramString).getBytes());
              localObject = new Intent("android.intent.action.MAIN");
              ((Intent)localObject).setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
              ((Intent)localObject).putExtra("INVOKE_TMAP", paramString);
              ((Intent)localObject).setFlags(268435456);
              this.Tcontext.startActivity((Intent)localObject);
              bool = true;
            }
            catch (Exception paramString)
            {
              bool = false;
            }
          } else {
            bool = false;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        bool = false;
      }
    }
  }
  
  public boolean invokeRoute(HashMap<String, String> paramHashMap)
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId)) {
      return false;
    }
    boolean bool;
    if (!checkTmapApplicationInstalled())
    {
      bool = false;
      TMapData.invokeStatistics(TMapView.bizAppId, "A1", bool);
      return bool;
    }
    String str9;
    String str10;
    do
    {
      try
      {
        Object localObject = this.Tcontext.getPackageManager().getPackageInfo(this.mPackageName, 0).versionName.split("\\.");
        if (Integer.parseInt(localObject[0]) == 3)
        {
          bool = false;
          break;
        }
        int i = Integer.parseInt(localObject[0]);
        if (i == 4)
        {
          str9 = null;
          Map.Entry localEntry = null;
          str10 = null;
          String str7 = null;
          localObject = null;
          String str8 = null;
          String str4 = null;
          String str6 = null;
          String str5 = null;
          String str1 = null;
          String str3 = null;
          String str2 = null;
          try
          {
            Iterator localIterator = paramHashMap.entrySet().iterator();
            paramHashMap = localEntry;
            while (localIterator.hasNext())
            {
              localEntry = (Map.Entry)localIterator.next();
              if (((String)localEntry.getKey()).contains("rGoName")) {
                str10 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rGoX")) {
                str9 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rGoY")) {
                paramHashMap = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rStName")) {
                str7 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rStX")) {
                localObject = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rStY")) {
                str8 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rV1Name")) {
                str4 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rV1X")) {
                str6 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rV1Y")) {
                str5 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rV2Name")) {
                str1 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rV2X")) {
                str3 = (String)localEntry.getValue();
              } else if (((String)localEntry.getKey()).contains("rV2Y")) {
                str2 = (String)localEntry.getValue();
              }
            }
            str9 = "tmap://route?goalx=" + str9 + "&goaly=" + paramHashMap + "&goalname=" + str10;
            paramHashMap = str9;
            if (localObject != null) {
              paramHashMap = str9 + "&startx=" + (String)localObject;
            }
            localObject = paramHashMap;
            if (str8 != null) {
              localObject = paramHashMap + "&starty= " + str8;
            }
            paramHashMap = (HashMap<String, String>)localObject;
            if (str7 != null) {
              paramHashMap = (String)localObject + "&startname=" + str7;
            }
            localObject = paramHashMap;
            if (str6 != null) {
              localObject = paramHashMap + "&via1x=" + str6;
            }
            paramHashMap = (HashMap<String, String>)localObject;
            if (str5 != null) {
              paramHashMap = (String)localObject + "&via1y= " + str5;
            }
            localObject = paramHashMap;
            if (str4 != null) {
              localObject = paramHashMap + "&via1name=" + str4;
            }
            paramHashMap = (HashMap<String, String>)localObject;
            if (str3 != null) {
              paramHashMap = (String)localObject + "&via2x=" + str3;
            }
            localObject = paramHashMap;
            if (str2 != null) {
              localObject = paramHashMap + "&via2y= " + str2;
            }
            paramHashMap = (HashMap<String, String>)localObject;
            if (str1 != null) {
              paramHashMap = (String)localObject + "&via2name=" + str1;
            }
            paramHashMap = TmapCrypto.encrypt("TmapInvokeRo8627", paramHashMap.getBytes());
            localObject = new Intent("android.intent.action.MAIN");
            ((Intent)localObject).setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
            ((Intent)localObject).putExtra("INVOKE_TMAP", paramHashMap);
            ((Intent)localObject).setFlags(268435456);
            this.Tcontext.startActivity((Intent)localObject);
            bool = true;
          }
          catch (Exception paramHashMap)
          {
            bool = false;
          }
          break;
        }
        bool = false;
      }
      catch (PackageManager.NameNotFoundException paramHashMap)
      {
        bool = false;
      }
      break;
    } while ((str10 != null) && (str9 != null) && (paramHashMap != null));
    return false;
  }
  
  public boolean invokeSafeDrive()
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId)) {
      return false;
    }
    boolean bool = false;
    if (!checkTmapApplicationInstalled()) {
      bool = false;
    }
    for (;;)
    {
      TMapData.invokeStatistics(TMapView.bizAppId, "E0", bool);
      return bool;
      try
      {
        String[] arrayOfString = this.Tcontext.getPackageManager().getPackageInfo(this.mPackageName, 0).versionName.split("\\.");
        if (Integer.parseInt(arrayOfString[0]) == 3)
        {
          bool = invokeSafeDrive30();
        }
        else
        {
          int i = Integer.parseInt(arrayOfString[0]);
          if (i == 4) {
            bool = false;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        bool = false;
      }
    }
  }
  
  public boolean invokeSearchPortal(String paramString)
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId)) {
      return false;
    }
    boolean bool = false;
    if (!checkTmapApplicationInstalled()) {
      bool = false;
    }
    for (;;)
    {
      TMapData.invokeStatistics(TMapView.bizAppId, "C0", bool);
      return bool;
      try
      {
        Object localObject = this.Tcontext.getPackageManager().getPackageInfo(this.mPackageName, 0).versionName.split("\\.");
        if (Integer.parseInt(localObject[0]) == 3)
        {
          bool = invokeSearchPortal30(paramString);
        }
        else
        {
          int i = Integer.parseInt(localObject[0]);
          if (i == 4) {
            try
            {
              paramString = TmapCrypto.encrypt("TmapInvokeRo8627", ("tmap://search?name=" + paramString).getBytes());
              localObject = new Intent("android.intent.action.MAIN");
              ((Intent)localObject).setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
              ((Intent)localObject).putExtra("INVOKE_TMAP", paramString);
              ((Intent)localObject).setFlags(268435456);
              this.Tcontext.startActivity((Intent)localObject);
              bool = true;
            }
            catch (Exception paramString)
            {
              bool = false;
            }
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        bool = false;
      }
    }
  }
  
  public boolean invokeSetLocation(String paramString, float paramFloat1, float paramFloat2)
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId)) {
      return false;
    }
    boolean bool = false;
    if (!checkTmapApplicationInstalled()) {
      bool = false;
    }
    for (;;)
    {
      TMapData.invokeStatistics(TMapView.bizAppId, "B0", bool);
      return bool;
      try
      {
        Object localObject = this.Tcontext.getPackageManager().getPackageInfo(this.mPackageName, 0).versionName.split("\\.");
        if (Integer.parseInt(localObject[0]) == 3)
        {
          bool = invokeSetLocation30(paramString, paramFloat1, paramFloat2);
        }
        else
        {
          int i = Integer.parseInt(localObject[0]);
          if (i == 4) {
            try
            {
              paramString = TmapCrypto.encrypt("TmapInvokeRo8627", ("tmap://viewmap?x=" + Float.toString(paramFloat1) + "&y=" + Float.toString(paramFloat2) + "&name=" + paramString).getBytes());
              localObject = new Intent("android.intent.action.MAIN");
              ((Intent)localObject).setClassName(this.mPackageName, this.mPackageName + ".IntroActivity");
              ((Intent)localObject).putExtra("INVOKE_TMAP", paramString);
              ((Intent)localObject).setFlags(268435456);
              this.Tcontext.startActivity((Intent)localObject);
              bool = true;
            }
            catch (Exception paramString)
            {
              bool = false;
            }
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        bool = false;
      }
    }
  }
  
  public boolean isTmapApplicationInstalled()
  {
    if ((!MapUtils.IsVerifyApiKey) || (!MapUtils.IsVerifyBizappId)) {
      return false;
    }
    boolean bool = checkTmapApplicationInstalled();
    TMapData.invokeStatistics(TMapView.bizAppId, "F0", true);
    return bool;
  }
}
