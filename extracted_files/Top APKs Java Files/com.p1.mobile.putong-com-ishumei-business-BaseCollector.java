package com.ishumei.business;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.ishumei.common.CloudConfiguration;
import com.ishumei.common.CollectConfiguration;
import com.ishumei.common.CollectConfiguration.Hook;
import com.ishumei.common.CollectConfiguration.RiskApp;
import com.ishumei.common.CollectConfiguration.RiskDir;
import com.ishumei.common.GlobalEnvironment;
import com.ishumei.functionlality.AntiTamper;
import com.ishumei.functionlality.AppInfo;
import com.ishumei.functionlality.Camera;
import com.ishumei.functionlality.Cpu;
import com.ishumei.functionlality.Emulator;
import com.ishumei.functionlality.Gps;
import com.ishumei.functionlality.Network;
import com.ishumei.functionlality.Sensor;
import com.ishumei.functionlality.Settings;
import com.ishumei.functionlality.SystemProperties;
import com.ishumei.functionlality.Telephony;
import com.ishumei.functionlality.Virtual;
import com.ishumei.smantifraud.SmAntiFraud;
import com.ishumei.smantifraud.SmAntiFraud.SmOption;
import com.ishumei.utils.StrUtils;
import com.ishumei.utils.Utils;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BaseCollector
  implements IColloctor
{
  private static String bKA;
  private static String bKB;
  private static String bKC;
  private static String bKD;
  private static String bKE;
  private static String bKF;
  private static String bKG;
  private static String bKH;
  private static String bKI;
  private static String bKJ;
  private static String bKK;
  private static String bKL;
  private static String bKM;
  private static String bKN;
  private static String bKO;
  private static String bKP;
  private static String bKQ;
  private static String bKR;
  private static String bKS;
  private static String bKT;
  private static String bKU;
  private static String bKV;
  private static String bKW;
  private static String bKX;
  private static String bKY;
  private static String bKZ;
  private static String bKf;
  private static String bKh;
  private static String bKi;
  private static String bKj;
  private static String bKk;
  private static String bKl;
  private static String bKm;
  private static String bKn;
  private static String bKo;
  private static String bKp;
  private static String bKq;
  private static String bKr;
  private static String bKs;
  private static String bKt;
  private static String bKu;
  private static String bKv;
  private static String bKw;
  private static String bKx;
  private static String bKy;
  private static String bKz;
  private static String bLA;
  private static String bLB;
  private static String bLC;
  private static String bLD;
  private static String bLE;
  private static String bLF;
  private static String bLG;
  private static String bLH;
  private static String bLI;
  private static BaseCollector bLJ;
  private static String bLa;
  private static String bLb;
  private static String bLc;
  private static String bLd;
  private static String bLe;
  private static String bLf;
  private static String bLg;
  private static String bLh;
  private static String bLi;
  private static String bLj;
  private static String bLk;
  private static String bLl;
  private static String bLm;
  private static String bLn;
  private static String bLo;
  private static String bLp;
  private static String bLq;
  private static String bLr;
  private static String bLs;
  private static String bLt;
  private static String bLu;
  private static String bLv;
  private static String bLw;
  private static String bLx;
  private static String bLy;
  private static String bLz;
  private boolean bKg = false;
  
  static
  {
    try
    {
      bKh = StrUtils.ˋⁱ("908c");
      bKf = StrUtils.ˋⁱ("8b");
      bKi = StrUtils.ˋⁱ("908c899a8d");
      bKm = StrUtils.ˋⁱ("8c9b94899a8d");
      bKl = StrUtils.ˋⁱ("9e8f8f899a8d");
      bKk = StrUtils.ˋⁱ("9e8f8f8a8b92");
      bKj = StrUtils.ˋⁱ("9e8f8f919e929a");
      bKo = StrUtils.ˋⁱ("919e929a");
      bKr = StrUtils.ˋⁱ("8f8d909c");
      bKq = StrUtils.ˋⁱ("9d8d9e919b");
      bKn = StrUtils.ˋⁱ("92909b9a93");
      bKp = StrUtils.ˋⁱ("9d9e919b");
      bKu = StrUtils.ˋⁱ("919a8b88908d94");
      bKw = StrUtils.ˋⁱ("908f9a8d9e8b908d");
      bKv = StrUtils.ˋⁱ("929e9c");
      bKs = StrUtils.ˋⁱ("8f8d908786");
      bKt = StrUtils.ˋⁱ("8c8c969b");
      bKA = StrUtils.ˋⁱ("9d8c8c969b");
      bKB = StrUtils.ˋⁱ("88969996968f");
      bKz = StrUtils.ˋⁱ("9d8b929e9c");
      bKy = StrUtils.ˋⁱ("8c92969b");
      bKx = StrUtils.ˋⁱ("8c92969bb99e9693ac8b908d9a");
      bKE = StrUtils.ˋⁱ("8c92969bb99691968c97af979e8c9a");
      bKD = StrUtils.ˋⁱ("96929a96");
      bKF = StrUtils.ˋⁱ("96929a96ce");
      bKG = StrUtils.ˋⁱ("96929a96cd");
      bKC = StrUtils.ˋⁱ("96928c96");
      bKH = StrUtils.ˋⁱ("969c9c969b");
      bKJ = StrUtils.ˋⁱ("9e9b969b");
      bKI = StrUtils.ˋⁱ("8a9e");
      bKK = StrUtils.ˋⁱ("9d90908b");
      bKL = StrUtils.ˋⁱ("8c9c8d9a9a91");
      bKM = StrUtils.ˋⁱ("9d8d9698978b919a8c8c");
      bKO = StrUtils.ˋⁱ("9d9e8b8b9a8d86");
      bKN = StrUtils.ˋⁱ("908d969a918b9e8b969091");
      bKP = StrUtils.ˋⁱ("939698978b");
      bKQ = StrUtils.ˋⁱ("988d9e89968b86");
      bKS = StrUtils.ˋⁱ("9c8f8ab2909b9a93");
      bKR = StrUtils.ˋⁱ("9c8f8aa99a919b908d");
      bKT = StrUtils.ˋⁱ("9c8f8abc908a918b");
      bKU = StrUtils.ˋⁱ("9c8f8ab98d9a8e");
      bKV = StrUtils.ˋⁱ("988f8c");
      bLa = StrUtils.ˋⁱ("9e8f8c");
      bKY = StrUtils.ˋⁱ("9c9a9393");
      bKW = StrUtils.ˋⁱ("9e8f8f8c");
      bKZ = StrUtils.ˋⁱ("8c868c");
      bKX = StrUtils.ˋⁱ("919a8b");
      bLc = StrUtils.ˋⁱ("8f8d908f8c");
      bLd = StrUtils.ˋⁱ("8c9a918c908d");
      bLe = StrUtils.ˋⁱ("929a92");
      bLb = StrUtils.ˋⁱ("8c9692");
      bLf = StrUtils.ˋⁱ("8b9a93");
      bLk = StrUtils.ˋⁱ("9a928a");
      bLi = StrUtils.ˋⁱ("9c8787");
      bLj = StrUtils.ˋⁱ("9a878b8d9e");
      bLh = StrUtils.ˋⁱ("9c908c8b");
      bLg = StrUtils.ˋⁱ("8c929b918c968f");
      bLl = StrUtils.ˋⁱ("8f8d96899e9c86");
      bLm = StrUtils.ˋⁱ("8d8b868f9a");
      bLo = StrUtils.ˋⁱ("8b9e8d989a8ba08c9b94");
      bLn = StrUtils.ˋⁱ("9e9d8b929e9c");
      bLp = StrUtils.ˋⁱ("9e878f908c9a9b");
      bLq = StrUtils.ˋⁱ("929e8f8c");
      bLt = StrUtils.ˋⁱ("9e96919990");
      bLr = StrUtils.ˋⁱ("8c928c9a8e");
      bLu = StrUtils.ˋⁱ("8897968b9a9e8f8f");
      bLs = StrUtils.ˋⁱ("8d968c949e8f8f");
      bLx = StrUtils.ˋⁱ("8d968c949b968d");
      bLw = StrUtils.ˋⁱ("8d968c949996939a");
      bLv = StrUtils.ˋⁱ("97909094");
      bLz = StrUtils.ˋⁱ("8a8c8d9c918b");
      bLy = StrUtils.ˋⁱ("8c868c9c918b");
      bLA = StrUtils.ˋⁱ("9c9c929bca");
      bLB = StrUtils.ˋⁱ("8990938b9e989a");
      bLD = StrUtils.ˋⁱ("99968d8c8b");
      bLE = StrUtils.ˋⁱ("8c9698919b91");
      bLC = StrUtils.ˋⁱ("8c969891979e8c97");
      bLH = StrUtils.ˋⁱ("89968d8b8a9e93");
      bLG = StrUtils.ˋⁱ("9e8f8fb69b");
      bLI = StrUtils.ˋⁱ("9996939a8c");
      bLF = StrUtils.ˋⁱ("96918f8a8b");
      bLJ = null;
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  BaseCollector(boolean paramBoolean)
  {
    this.bKg = paramBoolean;
  }
  
  private static Map<String, Object> ʼॱ(Map<String, CollectConfiguration.RiskApp> paramMap)
  {
    HashMap localHashMap1 = new HashMap();
    if ((paramMap == null) || (paramMap.size() == 0)) {
      return localHashMap1;
    }
    if (GlobalEnvironment.mContext == null) {
      return localHashMap1;
    }
    Object localObject1 = GlobalEnvironment.mContext.getPackageManager();
    HashMap localHashMap2 = new HashMap();
    paramMap = paramMap.entrySet().iterator();
    Object localObject3;
    Object localObject2;
    if (paramMap.hasNext())
    {
      localObject3 = (Map.Entry)paramMap.next();
      localObject2 = (String)((Map.Entry)localObject3).getKey();
      localObject3 = (CollectConfiguration.RiskApp)((Map.Entry)localObject3).getValue();
      localHashMap2.put(((CollectConfiguration.RiskApp)localObject3).packageName, localObject2);
    }
    label434:
    for (;;)
    {
      try
      {
        if (((PackageManager)localObject1).getLaunchIntentForPackage(((CollectConfiguration.RiskApp)localObject3).packageName) != null) {
          localHashMap1.put(localObject2, Integer.valueOf(1));
        }
        if (((PackageManager)localObject1).getPackageInfo(((CollectConfiguration.RiskApp)localObject3).packageName, 0) != null) {
          localHashMap1.put(localObject2, Integer.valueOf(1));
        }
        if (new File("/data/app/" + ((CollectConfiguration.RiskApp)localObject3).packageName + "-1/").exists()) {
          localHashMap1.put(localObject2, Integer.valueOf(1));
        } else if (new File("/data/app/" + ((CollectConfiguration.RiskApp)localObject3).packageName + "-2/").exists()) {
          localHashMap1.put(localObject2, Integer.valueOf(1));
        }
      }
      catch (Exception localException)
      {
        try
        {
          paramMap = ((PackageManager)localObject1).getInstalledPackages(0);
          localObject1 = ((PackageManager)localObject1).getInstalledApplications(0).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
            if (!localHashMap2.containsKey(((ApplicationInfo)localObject2).packageName)) {
              break label434;
            }
            localHashMap1.put(localHashMap2.get(((ApplicationInfo)localObject2).packageName), Integer.valueOf(1));
            break label434;
          }
          paramMap = paramMap.iterator();
          if (paramMap.hasNext())
          {
            localObject1 = (PackageInfo)paramMap.next();
            if (localHashMap2.containsKey(((PackageInfo)localObject1).packageName)) {
              localHashMap1.put(localHashMap2.get(((PackageInfo)localObject1).packageName), Integer.valueOf(1));
            }
            continue;
          }
          return localHashMap1;
        }
        catch (Exception paramMap)
        {
          return localHashMap1;
        }
        localException = localException;
      }
    }
  }
  
  private static Map<String, Object> ˈ(Map<String, CollectConfiguration.RiskDir> paramMap)
  {
    HashMap localHashMap = new HashMap();
    if ((paramMap == null) || (paramMap.size() == 0)) {
      return localHashMap;
    }
    paramMap = paramMap.entrySet().iterator();
    for (;;)
    {
      Object localObject;
      if (paramMap.hasNext()) {
        localObject = (Map.Entry)paramMap.next();
      }
      try
      {
        String str = (String)((Map.Entry)localObject).getKey();
        localObject = (CollectConfiguration.RiskDir)((Map.Entry)localObject).getValue();
        if (((CollectConfiguration.RiskDir)localObject).type == 0)
        {
          if (Utils.ˋᶫ(((CollectConfiguration.RiskDir)localObject).bMB)) {
            localHashMap.put(str, Integer.valueOf(1));
          }
        }
        else if ((1 == ((CollectConfiguration.RiskDir)localObject).type) && (Utils.ˌʼ(((CollectConfiguration.RiskDir)localObject).bMB))) {
          localHashMap.put(str, Integer.valueOf(1));
        }
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    return localHashMap;
  }
  
  public static BaseCollector ˉˋ(boolean paramBoolean)
  {
    if (bLJ == null) {
      try
      {
        if (bLJ == null) {
          bLJ = new BaseCollector(paramBoolean);
        }
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
    return bLJ;
  }
  
  public static Map<String, Object> ˎˏ(List<CollectConfiguration.Hook> paramList)
  {
    HashMap localHashMap = new HashMap();
    if ((paramList == null) || (paramList.size() == 0)) {
      return localHashMap;
    }
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      CollectConfiguration.Hook localHook = (CollectConfiguration.Hook)localIterator.next();
      try
      {
        paramList = Class.forName(localHook.bMz.replace("/", "."));
        int i = localHook.type;
        Object localObject = localHook.bMy;
        if (i == 3)
        {
          if ((localObject == null) || (((List)localObject).size() == 0)) {
            paramList = paramList.getConstructor(null);
          } else {
            paramList = paramList.getConstructor(ॱʼ((List)localObject));
          }
          localHashMap.put(localHook.key, "" + Modifier.isNative(paramList.getModifiers()));
        }
        else
        {
          if ((localObject == null) || (((List)localObject).size() == 0))
          {
            paramList = paramList.getDeclaredMethod(localHook.method, new Class[0]);
          }
          else
          {
            localObject = ॱʼ((List)localObject);
            paramList = paramList.getDeclaredMethod(localHook.method, (Class[])localObject);
          }
          localHashMap.put(localHook.key, "" + Modifier.isNative(paramList.getModifiers()));
        }
      }
      catch (Exception paramList)
      {
        localHashMap.put(localHook.key, paramList.getClass().getSimpleName());
      }
    }
    return localHashMap;
  }
  
  private static Class[] ॱʼ(List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      int i = -1;
      switch (str.hashCode())
      {
      default: 
        break;
      case 104431: 
        if (str.equals("int")) {
          i = 0;
        }
        break;
      case 3327612: 
        if (str.equals("long")) {
          i = 1;
        }
        break;
      case 64711720: 
        if (str.equals("boolean")) {
          i = 2;
        }
        break;
      case -1325958191: 
        if (str.equals("double")) {
          i = 3;
        }
        break;
      case 97526364: 
        if (str.equals("float")) {
          i = 4;
        }
        break;
      case 3039496: 
        if (str.equals("byte")) {
          i = 5;
        }
        break;
      case 3052374: 
        if (str.equals("char")) {
          i = 6;
        }
        break;
      case 109413500: 
        if (str.equals("short")) {
          i = 7;
        }
        break;
      }
      switch (i)
      {
      default: 
        break;
      case 0: 
        localArrayList.add(Integer.TYPE);
        break;
      case 1: 
        localArrayList.add(Long.TYPE);
        break;
      case 2: 
        localArrayList.add(Boolean.TYPE);
        break;
      case 3: 
        localArrayList.add(Double.TYPE);
        break;
      case 4: 
        localArrayList.add(Float.TYPE);
        break;
      case 5: 
        localArrayList.add(Byte.TYPE);
        break;
      case 6: 
        localArrayList.add(Character.TYPE);
        break;
      case 7: 
        localArrayList.add(Short.TYPE);
        break;
      }
      localArrayList.add(Class.forName(str));
    }
    paramList = new Class[localArrayList.size()];
    localArrayList.toArray(paramList);
    return paramList;
  }
  
  public final Map<String, Object> ᐝᵢ(int paramInt)
  {
    CollectConfiguration localCollectConfiguration = CloudConfiguration.ꜜʾ().ꜜˈ();
    HashMap localHashMap = new HashMap();
    try
    {
      localObject1 = SmAntiFraud.bPt.bPS;
      if (localObject1 != null) {
        break label2656;
      }
      localObject1 = Collections.emptySet();
    }
    catch (Throwable localThrowable)
    {
      Object localObject1;
      Object localObject4;
      localHashMap.put("err", localThrowable.getMessage());
      return localHashMap;
    }
    Object localObject2 = localCollectConfiguration.bMo;
    label45:
    localHashMap.put(bLm, "all");
    localObject4 = bLl;
    Object localObject3;
    label79:
    boolean bool;
    if ((paramInt & 0x1) == 1)
    {
      localObject3 = "md5";
      localHashMap.put(localObject4, localObject3);
      localHashMap.put(bLr, SeqManager.丶().ꓺι());
      localHashMap.put(bKk, SmAntiFraud.bPt.bPM);
      localHashMap.put(bKh, "android");
      localHashMap.put(bKm, "2.6.6");
      localHashMap.put(bKf, Long.valueOf(System.currentTimeMillis()));
      localHashMap.put(bKi, Build.VERSION.RELEASE);
      localObject3 = bLk;
      localObject4 = Emulator.ﺑᐝ();
      localHashMap.put(localObject3, String.format(Locale.CHINA, "%d%d%d%d%d%d%d", new Object[] { Integer.valueOf(Emulator.ˈᐝ(((Emulator)localObject4).ﺑˏ())), Integer.valueOf(Emulator.ˈᐝ(((Emulator)localObject4).ﺩ())), Integer.valueOf(Emulator.ˈᐝ(((Emulator)localObject4).ﺛ())), Integer.valueOf(Emulator.ˈᐝ(((Emulator)localObject4).ﻧᐝ())), Integer.valueOf(Emulator.ˈᐝ(((Emulator)localObject4).ıʾ())), Integer.valueOf(Emulator.ˈᐝ(Emulator.ﻨ())), Integer.valueOf(Emulator.ˈᐝ(Emulator.ıʿ())) }));
      localHashMap.put(bLG, SmAntiFraud.bPt.appId);
      localHashMap.put(bLo, Integer.valueOf(AppInfo.ﯨι().mContext.getApplicationInfo().targetSdkVersion));
      if (!((Set)localObject1).contains(bLn))
      {
        localObject3 = bLn;
        AntiTamper.ﯨͺ();
        localHashMap.put(localObject3, AntiTamper.ﯨʻ());
      }
      if ((localObject2 != null) && (((Set)localObject2).contains("ainfo")))
      {
        localHashMap.put(bLp, "" + AntiTamper.ﯨͺ().ﯨʽ());
        localObject3 = bLq;
        AntiTamper.ﯨͺ();
        localHashMap.put(localObject3, AntiTamper.ﯨʼ());
        AntiTamper.ﯨͺ();
        localObject4 = bLt;
        if (localCollectConfiguration == null) {
          localObject3 = "";
        } else {
          localObject3 = localCollectConfiguration.content;
        }
        if ((localCollectConfiguration == null) || (!localCollectConfiguration.bMv)) {
          break label2675;
        }
        bool = true;
        label514:
        AntiTamper.ॱ(localHashMap, (String)localObject4, true, (String)localObject3, bool);
      }
      if (!((Set)localObject1).contains(bKX))
      {
        Network.ıˈ();
        localObject4 = Network.ɨ();
        if (localObject4 != null)
        {
          if ((paramInt & 0x1) == 1)
          {
            localObject3 = new ArrayList(((List)localObject4).size());
            localObject4 = ((List)localObject4).iterator();
            while (((Iterator)localObject4).hasNext()) {
              ((List)localObject3).add(Utils.md5((String)((Iterator)localObject4).next()));
            }
            localHashMap.put(bKX, localObject3);
          }
          else
          {
            localHashMap.put(bKX, localObject4);
          }
        }
        else {
          localHashMap.put(bKX, "null");
        }
      }
      SystemProperties.ʟ();
      localObject3 = SystemProperties.ʲˈ();
      if (localObject3 != null)
      {
        if ((paramInt & 0x1) == 1)
        {
          localObject4 = (String)((Map)localObject3).get("ro.serialno");
          if (localObject4 != null) {
            ((Map)localObject3).put("ro.serialno", Utils.md5((String)localObject4));
          }
        }
        localHashMap.put(bLc, localObject3);
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bKA)) && (!((Set)localObject1).contains(bKA))) {
        if ((paramInt & 0x1) == 1) {
          localHashMap.put(bKA, Utils.md5(Network.ıˈ().getBSSID()));
        } else {
          localHashMap.put(bKA, Network.ıˈ().getBSSID());
        }
      }
      if (!((Set)localObject1).contains(bKD))
      {
        if ((paramInt & 0x1) == 1) {
          localHashMap.put(bKD, Utils.md5(Telephony.ˉᐝ(this.bKg).getImei()));
        } else {
          localHashMap.put(bKD, Telephony.ˉᐝ(this.bKg).getImei());
        }
        if ((paramInt & 0x1) == 1) {
          localHashMap.put(bKF, Utils.md5(Telephony.ˉᐝ(this.bKg).ᐝߵ(1)));
        } else {
          localHashMap.put(bKF, Telephony.ˉᐝ(this.bKg).ᐝߵ(1));
        }
        if ((paramInt & 0x1) == 1) {
          localHashMap.put(bKG, Utils.md5(Telephony.ˉᐝ(this.bKg).ᐝߵ(2)));
        } else {
          localHashMap.put(bKG, Telephony.ˉᐝ(this.bKg).ᐝߵ(2));
        }
      }
      if ((paramInt & 0x1) == 1) {
        localHashMap.put(bKJ, Utils.md5(Settings.ɿ().ɾ()));
      } else {
        localHashMap.put(bKJ, Settings.ɿ().ɾ());
      }
      if (!((Set)localObject1).contains(bKz)) {
        if ((paramInt & 0x1) == 1)
        {
          localObject3 = bKz;
          Network.ıˈ();
          localHashMap.put(localObject3, Utils.md5(Network.Ȉ()));
        }
        else
        {
          localObject3 = bKz;
          Network.ıˈ();
          localHashMap.put(localObject3, Network.Ȉ());
        }
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bLf)) && (!((Set)localObject1).contains(bLf))) {
        if ((paramInt & 0x1) == 1) {
          localHashMap.put(bLf, Utils.md5(Telephony.ˉᐝ(this.bKg).ʿˌ()));
        } else {
          localHashMap.put(bLf, Telephony.ˉᐝ(this.bKg).ʿˌ());
        }
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bKC)) && (!((Set)localObject1).contains(bKC))) {
        if ((paramInt & 0x1) == 1) {
          localHashMap.put(bKC, Utils.md5(Telephony.ˉᐝ(this.bKg).getImsi()));
        } else {
          localHashMap.put(bKC, Telephony.ˉᐝ(this.bKg).getImsi());
        }
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bKv)) && (!((Set)localObject1).contains(bKv))) {
        if ((paramInt & 0x1) == 1) {
          localHashMap.put(bKv, Utils.md5(Network.ıˈ().ıˌ()));
        } else {
          localHashMap.put(bKv, Network.ıˈ().ıˌ());
        }
      }
      localHashMap.put(bKp, android.os.Build.getRadioVersion());
      if ((localObject2 != null) && (((Set)localObject2).contains(bKt)) && (!((Set)localObject1).contains(bKt))) {
        localHashMap.put(bKt, Network.ıˈ().getSSID());
      }
      if (!((Set)localObject1).contains(bKB)) {
        localHashMap.put(bKB, Network.ıˈ().ǃʿ());
      }
      localHashMap.put(bKT, Integer.valueOf(Cpu.ﺑˎ().ﹾʽ()));
      localHashMap.put(bKS, Cpu.ﺑˎ().bNY);
      localHashMap.put(bKU, Integer.valueOf(Cpu.ﺑˎ().ﺒ()));
      localHashMap.put(bKR, Cpu.ﺑˎ().bNX);
      localHashMap.put(bKn, android.os.Build.MODEL);
      localHashMap.put(bKL, Settings.ɿ().ɩʻ());
      localHashMap.put(bKM, Integer.valueOf(Settings.ɿ().ɩʼ()));
      localObject3 = bKK;
      Settings.ɿ();
      localHashMap.put(localObject3, Long.valueOf(Settings.ɪ()));
      localHashMap.put(bKl, AppInfo.ﯨι().getAppVersion());
      localHashMap.put(bKj, AppInfo.ﯨι().getAppName());
      localObject3 = SmAntiFraud.bPt;
      localHashMap.put(bKo, AppInfo.ﯨι().﹎ι());
      localHashMap.put(bKr, Settings.ɿ().getProcessName());
      localHashMap.put(bKq, android.os.Build.BRAND);
      if (!((Set)localObject1).contains(bKu)) {
        localHashMap.put(bKu, Network.ıˈ().getNetworkType());
      }
      if (!((Set)localObject1).contains(bKw)) {
        localHashMap.put(bKw, Telephony.ˉᐝ(this.bKg).getNetworkOperator());
      }
      localObject3 = bKs;
      SystemProperties.ʟ();
      localHashMap.put(localObject3, SystemProperties.ˋߴ("http.proxy"));
      localObject3 = bKI;
      SystemProperties.ʟ();
      localHashMap.put(localObject3, SystemProperties.ˋߴ("http.agent"));
      localObject3 = bKZ;
      com.ishumei.functionlality.Build.ﹴͺ();
      localHashMap.put(localObject3, com.ishumei.functionlality.Build.ﹲᐝ());
      if (!((Set)localObject1).contains(bLd)) {
        localHashMap.put(bLd, Sensor.ȋ().ǃˌ());
      }
      localHashMap.put(bLe, Long.valueOf(Cpu.ﺑˎ().getTotalMemory()));
      localHashMap.put(bLb, Integer.valueOf(Telephony.ˉᐝ(this.bKg).ʲʾ()));
      if ((!this.bKg) && (localObject2 != null) && (((Set)localObject2).contains(bKV)) && (!((Set)localObject1).contains(bKV))) {
        localHashMap.put(bKV, Gps.ﻧˏ().ǃˈ());
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bKH)) && (!((Set)localObject1).contains(bKH))) {
        localHashMap.put(bKH, Telephony.ˉᐝ(this.bKg).getIccId());
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bKY)) && (!((Set)localObject1).contains(bKY))) {
        localHashMap.put(bKY, Telephony.ˉᐝ(this.bKg).ʲˌ());
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bLa)) && (!((Set)localObject1).contains(bLa)))
      {
        localObject3 = Network.ıˈ().ǃʾ();
        localHashMap.put(bLa, localObject3);
      }
      if ((localObject2 != null) && (((Set)localObject2).contains(bKW)) && (!((Set)localObject1).contains(bKW)))
      {
        if (localCollectConfiguration == null) {
          localObject3 = null;
        } else {
          localObject3 = localCollectConfiguration.bMr;
        }
        localObject4 = AppInfo.ﯨι();
        if (localCollectConfiguration == null)
        {
          paramInt = 0;
          break label2680;
        }
        paramInt = localCollectConfiguration.bMw;
        break label2680;
      }
    }
    label2184:
    for (int i = localCollectConfiguration.bMu;; i = 0)
    {
      localObject3 = ((AppInfo)localObject4).ˏ((Map)localObject3, paramInt, i);
      localHashMap.put(bKW, ((Map)localObject3).get("apps"));
      localHashMap.put(bLu, ((Map)localObject3).get("whiteapps"));
      localHashMap.put(bLz, Integer.valueOf(AppInfo.ﯨι().ﹲˋ()));
      localHashMap.put(bLy, Integer.valueOf(AppInfo.ﯨι().﹎ͺ()));
      if (localCollectConfiguration == null) {
        localObject3 = null;
      } else {
        localObject3 = localCollectConfiguration.bMl;
      }
      localObject3 = ʼॱ((Map)localObject3);
      localHashMap.put(bLs, localObject3);
      if (localCollectConfiguration == null) {
        localObject3 = null;
      } else {
        localObject3 = localCollectConfiguration.bMk;
      }
      localObject3 = ˈ((Map)localObject3);
      localHashMap.put(bLx, localObject3);
      if ((localCollectConfiguration != null) && (localCollectConfiguration.bMC))
      {
        localObject3 = localCollectConfiguration.bMp;
        localHashMap.put(bLv, ˎˏ((List)localObject3));
      }
      SmidManager.ꓺͺ().Ꙇ();
      localHashMap.put(bKy, SmidManager.ꓺͺ().ꜛˈ());
      localHashMap.put("smidstat", SmidManager.ꓺͺ().ꜛʿ());
      if (localCollectConfiguration != null) {
        localHashMap.put(bLA, localCollectConfiguration.bMq);
      }
      if ((!this.bKg) && (localObject2 != null) && (((Set)localObject2).contains("camera")) && (!((Set)localObject1).contains("camera"))) {
        Camera.ﹴι().ʿ(localHashMap);
      }
      localHashMap.put(bLD, String.valueOf(SmAntiFraud.bPt.bQa));
      localObject1 = AppInfo.ﯨι().ﹲˊ();
      if (localObject1 != null)
      {
        localObject2 = bLE;
        AppInfo.ﯨι();
        localHashMap.put(localObject2, AppInfo.ʴ(localObject1));
        localHashMap.put(bLC, Integer.valueOf(localObject1.hashCode()));
      }
      Virtual.ʵʻ().ʽॱ(localHashMap);
      if (GlobalEnvironment.mContext != null) {
        localHashMap.put(bLI, GlobalEnvironment.mContext.getFilesDir());
      }
      localObject1 = bLF;
      AntiTamper.ﯨͺ();
      localHashMap.put(localObject1, AntiTamper.ﭘ());
      return localHashMap;
      label2656:
      if (localCollectConfiguration != null) {
        break;
      }
      localObject2 = null;
      break label45;
      localObject3 = "none";
      break label79;
      label2675:
      bool = false;
      break label514;
      label2680:
      if (localCollectConfiguration != null) {
        break label2184;
      }
    }
  }
}
