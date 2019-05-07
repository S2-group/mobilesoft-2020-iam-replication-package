package com.zing.zalo.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.zing.zalo.app.MainApplication;
import com.zing.zalo.b.r;
import com.zing.zalo.utils.as;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class l
{
  private static int aAM = -1;
  private static int aAN = -1;
  private static int aAO = -1;
  private static volatile l aAR;
  private double aAH = 0.0D;
  private double aAI = 0.0D;
  private int aAJ = 0;
  private int aAK = 0;
  private String aAL = "";
  private int aAP = 0;
  private long aAQ = 0L;
  private final String aAS = "use_gps";
  private final String aAT = "has_gps_status_changed";
  private final String aAU = "allow_mock_location";
  private final String aAV = "mock_applications";
  private final String aAW = "package";
  private final String aAX = "running_service";
  private final String aAY = "from_mock_provider";
  public String aAZ = null;
  private int mcc = 0;
  private int mnc = 0;
  
  private l() {}
  
  private ArrayList<HashMap<String, Integer>> aY(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      if (localIterator.hasNext()) {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
      }
      try
      {
        String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
        if (arrayOfString != null)
        {
          int i = 0;
          if (i < arrayOfString.length)
          {
            HashMap localHashMap;
            String str;
            if ((arrayOfString[i].equals("android.permission.ACCESS_MOCK_LOCATION")) && (!localApplicationInfo.packageName.equals(paramContext.getPackageName())))
            {
              localHashMap = new HashMap();
              str = localApplicationInfo.packageName;
              if (!dl(localApplicationInfo.packageName)) {
                break label168;
              }
            }
            label168:
            for (int j = 1;; j = 0)
            {
              localHashMap.put(str, Integer.valueOf(j));
              localArrayList.add(localHashMap);
              i += 1;
              break;
            }
            return localArrayList;
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  private int aZ(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return -1;
    }
    if (Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0")) {
      return 0;
    }
    return 1;
  }
  
  private boolean dl(String paramString)
  {
    boolean bool2 = false;
    List localList = ((ActivityManager)MainApplication.uw().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < localList.size())
      {
        if (((ActivityManager.RunningServiceInfo)localList.get(i)).process.equals(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  /* Error */
  public static l xC()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 237	com/zing/zalo/d/l:aAR	Lcom/zing/zalo/d/l;
    //   6: ifnonnull +25 -> 31
    //   9: ldc 2
    //   11: monitorenter
    //   12: getstatic 237	com/zing/zalo/d/l:aAR	Lcom/zing/zalo/d/l;
    //   15: ifnonnull +13 -> 28
    //   18: new 2	com/zing/zalo/d/l
    //   21: dup
    //   22: invokespecial 238	com/zing/zalo/d/l:<init>	()V
    //   25: putstatic 237	com/zing/zalo/d/l:aAR	Lcom/zing/zalo/d/l;
    //   28: ldc 2
    //   30: monitorexit
    //   31: getstatic 237	com/zing/zalo/d/l:aAR	Lcom/zing/zalo/d/l;
    //   34: astore_0
    //   35: ldc 2
    //   37: monitorexit
    //   38: aload_0
    //   39: areturn
    //   40: astore_0
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_0
    //   45: athrow
    //   46: astore_0
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload_0
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   34	5	0	localL	l
    //   40	5	0	localObject1	Object
    //   46	5	0	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   12	28	40	finally
    //   28	31	40	finally
    //   41	44	40	finally
    //   3	12	46	finally
    //   31	35	46	finally
    //   44	46	46	finally
  }
  
  private void xO()
  {
    try
    {
      if (as.fP(false))
      {
        r localR = new r();
        localR.a(new n(this));
        if ((this.aAH != 0.0D) && (this.aAI != 0.0D)) {
          localR.a(this.aAH, this.aAI, "" + this.aAJ, this.aAL, "0", "" + this.aAK, "" + this.mcc, "" + this.mnc, xM());
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void a(double paramDouble1, double paramDouble2, int paramInt, long paramLong)
  {
    this.aAH = paramDouble1;
    this.aAI = paramDouble2;
    this.aAP = paramInt;
    this.aAQ = paramLong;
  }
  
  public void dk(String paramString)
  {
    this.aAZ = paramString;
  }
  
  public void eG(int paramInt)
  {
    aAM = paramInt;
  }
  
  public void eH(int paramInt)
  {
    aAN = paramInt;
  }
  
  public void eI(int paramInt)
  {
    aAO = paramInt;
  }
  
  public int getLac()
  {
    return this.aAK;
  }
  
  public double getLatitude()
  {
    return this.aAI;
  }
  
  public int getMcc()
  {
    return this.mcc;
  }
  
  public int getMnc()
  {
    return this.mnc;
  }
  
  /* Error */
  public int xD()
  {
    // Byte code:
    //   0: invokestatic 208	com/zing/zalo/app/MainApplication:uw	()Landroid/content/Context;
    //   3: ldc_w 293
    //   6: invokevirtual 214	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 295	android/telephony/TelephonyManager
    //   12: astore_3
    //   13: aload_3
    //   14: invokevirtual 299	android/telephony/TelephonyManager:getCellLocation	()Landroid/telephony/CellLocation;
    //   17: checkcast 301	android/telephony/gsm/GsmCellLocation
    //   20: astore 4
    //   22: aload 4
    //   24: invokevirtual 304	android/telephony/gsm/GsmCellLocation:getCid	()I
    //   27: ldc_w 305
    //   30: iand
    //   31: istore_1
    //   32: aload_0
    //   33: iload_1
    //   34: putfield 48	com/zing/zalo/d/l:aAJ	I
    //   37: aload_0
    //   38: aload 4
    //   40: invokevirtual 307	android/telephony/gsm/GsmCellLocation:getLac	()I
    //   43: putfield 50	com/zing/zalo/d/l:aAK	I
    //   46: aload_3
    //   47: invokevirtual 310	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   50: astore_3
    //   51: aload_3
    //   52: ifnull +33 -> 85
    //   55: aload_3
    //   56: invokevirtual 313	java/lang/String:length	()I
    //   59: istore_2
    //   60: iload_2
    //   61: ifle +24 -> 85
    //   64: aload_0
    //   65: aload_3
    //   66: iconst_0
    //   67: iconst_3
    //   68: invokevirtual 317	java/lang/String:substring	(II)Ljava/lang/String;
    //   71: invokestatic 321	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   74: putfield 52	com/zing/zalo/d/l:mcc	I
    //   77: aload_0
    //   78: aload_3
    //   79: invokestatic 321	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   82: putfield 54	com/zing/zalo/d/l:mnc	I
    //   85: iload_1
    //   86: ireturn
    //   87: astore_3
    //   88: iconst_m1
    //   89: ireturn
    //   90: astore_3
    //   91: goto -6 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	l
    //   31	55	1	i	int
    //   59	2	2	j	int
    //   12	67	3	localObject	Object
    //   87	1	3	localException1	Exception
    //   90	1	3	localException2	Exception
    //   20	19	4	localGsmCellLocation	android.telephony.gsm.GsmCellLocation
    // Exception table:
    //   from	to	target	type
    //   0	51	87	java/lang/Exception
    //   55	60	87	java/lang/Exception
    //   64	85	90	java/lang/Exception
  }
  
  public int xE()
  {
    return this.aAP;
  }
  
  public long xF()
  {
    return this.aAQ;
  }
  
  public double xG()
  {
    return this.aAH;
  }
  
  public int xH()
  {
    return this.aAJ;
  }
  
  public String xI()
  {
    try
    {
      this.aAL = ((WifiManager)MainApplication.uw().getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (this.aAL == null) {
        this.aAL = "";
      }
      String str = this.aAL;
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public int xJ()
  {
    return aAM;
  }
  
  public int xK()
  {
    return aAN;
  }
  
  public int xL()
  {
    return aAO;
  }
  
  public String xM()
  {
    if (this.aAZ != null) {
      return this.aAZ;
    }
    return "";
  }
  
  public void xN()
  {
    new w().a(MainApplication.uw(), new m(this));
  }
  
  public void xP()
  {
    aAM = -1;
    aAN = -1;
    aAO = -1;
    this.aAZ = null;
  }
  
  public JSONObject xQ()
  {
    JSONArray localJSONArray;
    for (;;)
    {
      try
      {
        JSONObject localJSONObject1 = new JSONObject();
        localJSONArray = new JSONArray();
        ArrayList localArrayList = aY(MainApplication.uw());
        int i = 0;
        if (i >= localArrayList.size()) {
          break;
        }
        JSONObject localJSONObject2 = new JSONObject();
        Iterator localIterator = ((HashMap)localArrayList.get(i)).keySet().iterator();
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localJSONObject2.put("package", str);
          localJSONObject2.put("running_service", ((HashMap)localArrayList.get(i)).get(str));
          localJSONArray.put(localJSONObject2);
        }
        else
        {
          i += 1;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
    }
    localException.put("allow_mock_location", aZ(MainApplication.uw()));
    localException.put("use_gps", xK());
    localException.put("has_gps_status_changed", xJ());
    localException.put("from_mock_provider", xL());
    localException.put("mock_applications", localJSONArray);
    return localException;
  }
}
