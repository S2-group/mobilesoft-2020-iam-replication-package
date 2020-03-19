package com.handcent.common;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import com.handcent.nextsms.views.hcautz;
import com.handcent.o.a;
import com.handcent.o.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class eo
{
  private static ArrayList<String> aJq = null;
  private static ArrayList<String> aJr = null;
  private static HashMap<String, ep> aJs = new HashMap();
  public static String aJt = a.Rp();
  private static eo aJu = null;
  public static Uri aJv = Uri.parse(hcautz.getInstance().a1("BCD3504F2E015E1C9C631E45AB4CC06167C1669CF8F6205736839AC7825BE820628BB73C7018599BCD415DE62B85B2DE4F7A4C3C70896995C46BC948A9B33212"));
  private static long aJw = 3600000L;
  private static int aJx = 100;
  private static Context mContext;
  private String[] aJy = { hcautz.getInstance().a1("990AA75244586969"), hcautz.getInstance().a1("2F2EC5C7050356B3D8EB1AF2E63AA9ED"), hcautz.getInstance().a1("B0C4B4C88936F179E0BEE0E15E1C119B"), hcautz.getInstance().a1("C537A6C5AA503FDF") };
  
  private eo(Context paramContext)
  {
    if (m.So())
    {
      aJw = 1800000L;
      aJx = 30;
    }
    aU(paramContext);
    aW(paramContext);
  }
  
  public static eo aT(Context paramContext)
  {
    if (aJu == null)
    {
      mContext = paramContext.getApplicationContext();
      aJu = new eo(paramContext);
    }
    return aJu;
  }
  
  private void aU(Context paramContext)
  {
    aJq = new ArrayList();
    aJr = new ArrayList();
    String str = hcautz.getInstance().a1("580F15E2D8289CF654470D2B09D3F54FC116E1D23A6594F0D40F76255AEF28BC");
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    dd.d("", "pack avai=" + paramContext.size());
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      int i = 0;
      while (i < paramContext.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
        if (localPackageInfo.packageName.startsWith(str))
        {
          if (cY(localPackageInfo.packageName)) {
            aJq.add(localPackageInfo.packageName);
          }
          aJr.add(localPackageInfo.packageName);
        }
        i += 1;
      }
    }
  }
  
  private void aW(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(aJv, this.aJy, null, null, hcautz.getInstance().a1("B0C4B4C88936F179E0BEE0E15E1C119B"));
      String str;
      ep localEp;
      boolean bool;
      if (paramContext == null) {
        break label141;
      }
    }
    finally
    {
      try
      {
        if (paramContext.moveToFirst()) {
          do
          {
            str = paramContext.getString(1);
            localEp = (ep)aJs.get(str);
            if (aJs.get(str) == null)
            {
              localEp = new ep(this, str);
              aJs.put(str, localEp);
            }
            localEp.b(paramContext.getLong(2), paramContext.getInt(3), false);
            bool = paramContext.moveToNext();
          } while (bool);
        }
        if (paramContext != null) {
          paramContext.close();
        }
        return;
      }
      finally
      {
        for (;;) {}
      }
      localObject1 = finally;
      paramContext = null;
    }
    paramContext.close();
    label141:
    throw localObject1;
  }
  
  public int DH()
  {
    return aJr.size();
  }
  
  public int DI()
  {
    int i = 20;
    if (hcautz.getInstance().isGoldenVipMember(mContext)) {
      if (!m.So()) {}
    }
    for (;;)
    {
      if (DH() > i)
      {
        return i;
        if (hcautz.getInstance().isVipMember(mContext))
        {
          if (m.So())
          {
            i = 10;
            continue;
          }
          i = 5;
        }
      }
      else
      {
        return DH();
      }
      i = 2;
    }
  }
  
  public int DJ()
  {
    if (m.So()) {
      return (DI() + 1) * aJx * 2;
    }
    return (DI() + 1) * aJx;
  }
  
  public void a(String paramString1, String paramString2, String paramString3, ArrayList<String> paramArrayList, boolean paramBoolean, Uri paramUri, long paramLong1, long paramLong2, String paramString4, int paramInt1, int paramInt2, String paramString5, String paramString6, int paramInt3, long paramLong3)
  {
    String[] arrayOfString = new String[paramArrayList.size()];
    int i = 0;
    while (i < paramArrayList.size())
    {
      arrayOfString[i] = ((String)paramArrayList.get(i));
      i += 1;
    }
    paramArrayList = new Intent();
    paramArrayList.setClassName(paramString1, paramString1 + hcautz.getInstance().a1("F74485AE538FACC0C605544979E24EDD3349A6C1E56AFED9"));
    paramArrayList.putExtra("d1", paramString2);
    paramArrayList.putExtra("s1", paramString3);
    paramArrayList.putExtra("m1", arrayOfString);
    paramArrayList.putExtra("r1", paramBoolean);
    paramArrayList.setData(paramUri);
    paramArrayList.putExtra("a1", paramLong1);
    paramArrayList.putExtra("t1", paramLong2);
    paramArrayList.putExtra("s2", paramString4);
    paramArrayList.putExtra("p1", paramInt1);
    paramArrayList.putExtra("r2", paramInt2);
    paramArrayList.putExtra("r3", paramString5);
    paramArrayList.putExtra("m2", paramString6);
    paramArrayList.putExtra("p2", paramInt3);
    paramArrayList.putExtra("r4", paramLong3);
    paramArrayList.putExtra("s3", mContext.getPackageName());
    mContext.startService(paramArrayList);
  }
  
  public String aV(Context paramContext)
  {
    Cursor localCursor = paramContext.getContentResolver().query(aJv, this.aJy, null, null, null);
    if (localCursor != null) {}
    for (int i = 1; (i & localCursor.moveToNext()) != 0; i = 0)
    {
      paramContext = "";
      String str;
      do
      {
        str = paramContext + "pack=" + localCursor.getString(1) + ",counter=" + localCursor.getString(3) + "\n";
        paramContext = str;
      } while (localCursor.moveToNext());
      return str;
    }
    return "";
  }
  
  public boolean cY(String paramString)
  {
    if (hcautz.getInstance().a1("580F47E2D8289CF6EC68AC592CA984B609A008C6ECEF380F").equalsIgnoreCase(paramString)) {
      return true;
    }
    if (paramString != null) {}
    do
    {
      try
      {
        if (paramString.startsWith(hcautz.getInstance().a1("580FA6E2D8289CF654470D2B09D3F54FC116E1D23A6594F01DBE16A24B6A4B4C3ABEEC692DEF62B8")))
        {
          i = Integer.valueOf(paramString.substring(hcautz.getInstance().a1("580FA6E2D8289CF654470D2B09D3F54FC116E1D23A6594F01DBE16A24B6A4B4C3ABEEC692DEF62B8").length())).intValue();
          if (hcautz.getInstance().isGoldenVipMember(mContext)) {
            break;
          }
          if (!hcautz.getInstance().isVipMember(mContext)) {
            continue;
          }
          boolean bool = m.So();
          if ((bool) && (i <= 10)) {
            break;
          }
          if (i <= 5) {
            break;
          }
        }
      }
      catch (Exception paramString)
      {
        int i;
        for (;;) {}
      }
      return false;
    } while (i > 2);
    return true;
  }
  
  public String k(Context paramContext, int paramInt)
  {
    if (!m.Sq())
    {
      int i = 0;
      while (i < aJq.size())
      {
        ep localEp = (ep)aJs.get(aJq.get(i));
        paramContext = localEp;
        if (localEp == null)
        {
          paramContext = new ep(this, (String)aJq.get(i));
          aJs.put(aJq.get(i), paramContext);
        }
        dd.d("", "counterItem=" + paramContext);
        if (paramContext.fM(paramInt))
        {
          dd.d("", "isUnderLimit true" + ep.a(paramContext));
          return ep.a(paramContext);
        }
        i += 1;
      }
    }
    return aJt;
  }
}
