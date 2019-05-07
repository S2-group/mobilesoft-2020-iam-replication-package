import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.rsupport.common.log.a;
import com.rsupport.mvagent.MVApplicationEx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public final class ku
  implements kq
{
  private Context Zc = null;
  private Object Zf = null;
  private final String aHc = "com.android.settings";
  private int[] aHe = null;
  private ArrayList<String> aHf = null;
  private Hashtable<String, kw> aHg = null;
  BroadcastReceiver fx = new kv(this);
  
  public ku(Context paramContext)
  {
    this.Zc = paramContext;
    this.Zf = new Object();
    this.aHf = new ArrayList();
    this.aHg = new Hashtable();
    this.aHe = dH("com.android.settings");
    Object localObject = this.aHf;
    if (kb.getUXStyle() == 1) {
      ((ArrayList)localObject).add("com.rsupport.rsperm.ntt");
    }
    ((ArrayList)localObject).add("com.rsupport.rspermission");
    ((ArrayList)localObject).add("com.rsupport.rsperm.aa.first");
    ((ArrayList)localObject).add("com.rsupport.rsperm.aa.second");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ab");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ab.second");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ac");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ac.first");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ac.second");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ad");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ae");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ae.a2th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.second");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.third");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.fourth");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.fifth");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.sixth");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.seventh");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.eighth");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.nineth");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a10th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a11th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a12th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a13th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a14th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a15th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a16th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a17th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a18th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a19th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a20th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.af.a21th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.doro");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ag");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ag.second");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ah");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ah.second");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ah.third");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ah.fourth");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ah.fifth");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ai.first");
    ((ArrayList)localObject).add("com.rsupport.rsperm.aj.first");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ak.first");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ak.a2th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ak.a3th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.al.first");
    ((ArrayList)localObject).add("com.rsupport.rsperm.am.first");
    ((ArrayList)localObject).add("com.rsupport.rsperm.an.a1th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ao.a1th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ap.a1th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.aq.a1th");
    ((ArrayList)localObject).add("com.rsupport.rsperm");
    ((ArrayList)localObject).add("com.rsupport.rsperm.lge");
    ((ArrayList)localObject).add("com.rsupport.engine.input2");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ab.a1th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ab.a2th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ab.a3th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ab.a4th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ab.a5th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.ay.a1th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.bh.a1th");
    ((ArrayList)localObject).add("com.rsupport.rsperm.az.a1th");
    a(paramContext, this.aHg);
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject).addDataScheme("package");
    paramContext.registerReceiver(this.fx, (IntentFilter)localObject);
  }
  
  private void CF()
  {
    this.Zc.unregisterReceiver(this.fx);
  }
  
  private void a(Context paramContext, Hashtable<String, kw> paramHashtable)
  {
    for (;;)
    {
      Object localObject2;
      synchronized (this.Zf)
      {
        a.cA("entert setInstalledRspermPackageName");
        Iterator localIterator = aI(paramContext).iterator();
        if (!localIterator.hasNext())
        {
          a.cA("exit setInstalledRspermPackageName");
          return;
        }
        localObject2 = localIterator.next();
        paramContext = "";
        if ((localObject2 instanceof PackageInfo))
        {
          paramContext = ((PackageInfo)localObject2).packageName;
          if (!dI(paramContext)) {
            continue;
          }
          a(aD(localObject2), paramHashtable);
        }
      }
      if ((localObject2 instanceof ApplicationInfo)) {
        paramContext = ((ApplicationInfo)localObject2).packageName;
      }
    }
  }
  
  private static boolean a(String paramString, Hashtable<String, kw> paramHashtable)
  {
    if ((paramHashtable != null) && (paramHashtable.containsKey(paramString)))
    {
      paramHashtable.remove(paramString);
      return true;
    }
    return false;
  }
  
  private static boolean a(kw paramKw, Hashtable<String, kw> paramHashtable)
  {
    if ((paramHashtable != null) && (!paramHashtable.containsKey(paramKw.packageName)))
    {
      paramHashtable.put(paramKw.packageName, paramKw);
      return true;
    }
    return false;
  }
  
  private boolean a(int[] paramArrayOfInt, String paramString)
  {
    paramString = dH(paramString);
    int k = paramString.length;
    int i = 0;
    if (i >= k) {
      return false;
    }
    int m = paramString[i];
    int n = paramArrayOfInt.length;
    int j = 0;
    for (;;)
    {
      if (j >= n)
      {
        i += 1;
        break;
      }
      if (paramArrayOfInt[j] == m) {
        return true;
      }
      j += 1;
    }
  }
  
  private static int[] a(Signature[] paramArrayOfSignature)
  {
    int j = 0;
    int i = paramArrayOfSignature.length;
    if (i > 0)
    {
      int[] arrayOfInt = new int[i];
      int k = paramArrayOfSignature.length;
      i = 0;
      for (;;)
      {
        if (j >= k) {
          return arrayOfInt;
        }
        arrayOfInt[i] = paramArrayOfSignature[j].hashCode();
        j += 1;
        i += 1;
      }
    }
    return null;
  }
  
  private kw aD(Object paramObject)
  {
    kw localKw = new kw(this);
    if ((paramObject instanceof PackageInfo))
    {
      paramObject = (PackageInfo)paramObject;
      localKw.packageName = paramObject.packageName;
      localKw.versionCode = paramObject.versionCode;
      localKw.versionName = paramObject.versionName;
      return localKw;
    }
    if ((paramObject instanceof ApplicationInfo)) {
      try
      {
        localKw.packageName = ((ApplicationInfo)paramObject).packageName;
        paramObject = this.Zc.getPackageManager().getPackageInfo(localKw.packageName, 64);
        localKw.versionCode = paramObject.versionCode;
        localKw.versionName = paramObject.versionName;
        return localKw;
      }
      catch (Exception paramObject)
      {
        a.e(paramObject);
      }
    }
    return null;
  }
  
  private static String aE(Object paramObject)
  {
    String str = "";
    if ((paramObject instanceof PackageInfo)) {
      str = ((PackageInfo)paramObject).packageName;
    }
    while (!(paramObject instanceof ApplicationInfo)) {
      return str;
    }
    return ((ApplicationInfo)paramObject).packageName;
  }
  
  private static List<?> aI(Context paramContext)
  {
    try
    {
      localList3 = paramContext.getPackageManager().getInstalledPackages(1);
      localList1 = localList3;
      if (localList3 != null)
      {
        localList1 = localList3;
        if (localList3.size() <= 0) {
          localList1 = paramContext.getPackageManager().getInstalledPackages(4);
        }
      }
    }
    catch (Exception localException)
    {
      try
      {
        List localList3;
        do
        {
          do
          {
            List localList1;
            localList3 = paramContext.getPackageManager().getInstalledApplications(1);
            localList2 = localList3;
          } while (localList3 == null);
          List localList2 = localList3;
        } while (localList3.size() > 0);
        paramContext = paramContext.getPackageManager().getInstalledApplications(4);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        a.e(paramContext);
      }
    }
    return localList1;
    return null;
  }
  
  private void aJ(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addDataScheme("package");
    paramContext.registerReceiver(this.fx, localIntentFilter);
  }
  
  private int[] dH(String paramString)
  {
    int i;
    int[] arrayOfInt;
    int k;
    do
    {
      try
      {
        Signature[] arrayOfSignature = this.Zc.getPackageManager().getPackageInfo(paramString, 64).signatures;
        i = arrayOfSignature.length;
        if (i > 0)
        {
          arrayOfInt = new int[i];
          k = arrayOfSignature.length;
          i = 0;
          int j = 0;
          continue;
          arrayOfInt[j] = arrayOfSignature[i].hashCode();
          i += 1;
          j += 1;
        }
        else
        {
          return null;
        }
      }
      catch (Exception localException)
      {
        a.cB("sign_check_fail : (" + paramString + "), " + Log.getStackTraceString(localException));
        return new int[0];
      }
    } while (i < k);
    return arrayOfInt;
  }
  
  private boolean dI(String paramString)
  {
    Iterator localIterator;
    if (paramString != null) {
      localIterator = this.aHf.iterator();
    }
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!paramString.equals((String)localIterator.next()));
    if (MVApplicationEx.zP()) {
      return true;
    }
    int[] arrayOfInt1 = this.aHe;
    int[] arrayOfInt2 = dH(paramString);
    int k = arrayOfInt2.length;
    int i = 0;
    label70:
    label78:
    int m;
    int n;
    int j;
    if (i >= k)
    {
      i = 0;
      if (i != 0) {
        return true;
      }
    }
    else
    {
      m = arrayOfInt2[i];
      n = arrayOfInt1.length;
      j = 0;
    }
    for (;;)
    {
      if (j >= n)
      {
        i += 1;
        break label70;
      }
      if (arrayOfInt1[j] == m)
      {
        i = 1;
        break label78;
        break;
      }
      j += 1;
    }
  }
  
  private static void e(ArrayList<String> paramArrayList)
  {
    if (kb.getUXStyle() == 1) {
      paramArrayList.add("com.rsupport.rsperm.ntt");
    }
    paramArrayList.add("com.rsupport.rspermission");
    paramArrayList.add("com.rsupport.rsperm.aa.first");
    paramArrayList.add("com.rsupport.rsperm.aa.second");
    paramArrayList.add("com.rsupport.rsperm.ab");
    paramArrayList.add("com.rsupport.rsperm.ab.second");
    paramArrayList.add("com.rsupport.rsperm.ac");
    paramArrayList.add("com.rsupport.rsperm.ac.first");
    paramArrayList.add("com.rsupport.rsperm.ac.second");
    paramArrayList.add("com.rsupport.rsperm.ad");
    paramArrayList.add("com.rsupport.rsperm.ae");
    paramArrayList.add("com.rsupport.rsperm.ae.a2th");
    paramArrayList.add("com.rsupport.rsperm.af");
    paramArrayList.add("com.rsupport.rsperm.af.second");
    paramArrayList.add("com.rsupport.rsperm.af.third");
    paramArrayList.add("com.rsupport.rsperm.af.fourth");
    paramArrayList.add("com.rsupport.rsperm.af.fifth");
    paramArrayList.add("com.rsupport.rsperm.af.sixth");
    paramArrayList.add("com.rsupport.rsperm.af.seventh");
    paramArrayList.add("com.rsupport.rsperm.af.eighth");
    paramArrayList.add("com.rsupport.rsperm.af.nineth");
    paramArrayList.add("com.rsupport.rsperm.af.a10th");
    paramArrayList.add("com.rsupport.rsperm.af.a11th");
    paramArrayList.add("com.rsupport.rsperm.af.a12th");
    paramArrayList.add("com.rsupport.rsperm.af.a13th");
    paramArrayList.add("com.rsupport.rsperm.af.a14th");
    paramArrayList.add("com.rsupport.rsperm.af.a15th");
    paramArrayList.add("com.rsupport.rsperm.af.a16th");
    paramArrayList.add("com.rsupport.rsperm.af.a17th");
    paramArrayList.add("com.rsupport.rsperm.af.a18th");
    paramArrayList.add("com.rsupport.rsperm.af.a19th");
    paramArrayList.add("com.rsupport.rsperm.af.a20th");
    paramArrayList.add("com.rsupport.rsperm.af.a21th");
    paramArrayList.add("com.rsupport.rsperm.doro");
    paramArrayList.add("com.rsupport.rsperm.ag");
    paramArrayList.add("com.rsupport.rsperm.ag.second");
    paramArrayList.add("com.rsupport.rsperm.ah");
    paramArrayList.add("com.rsupport.rsperm.ah.second");
    paramArrayList.add("com.rsupport.rsperm.ah.third");
    paramArrayList.add("com.rsupport.rsperm.ah.fourth");
    paramArrayList.add("com.rsupport.rsperm.ah.fifth");
    paramArrayList.add("com.rsupport.rsperm.ai.first");
    paramArrayList.add("com.rsupport.rsperm.aj.first");
    paramArrayList.add("com.rsupport.rsperm.ak.first");
    paramArrayList.add("com.rsupport.rsperm.ak.a2th");
    paramArrayList.add("com.rsupport.rsperm.ak.a3th");
    paramArrayList.add("com.rsupport.rsperm.al.first");
    paramArrayList.add("com.rsupport.rsperm.am.first");
    paramArrayList.add("com.rsupport.rsperm.an.a1th");
    paramArrayList.add("com.rsupport.rsperm.ao.a1th");
    paramArrayList.add("com.rsupport.rsperm.ap.a1th");
    paramArrayList.add("com.rsupport.rsperm.aq.a1th");
    paramArrayList.add("com.rsupport.rsperm");
    paramArrayList.add("com.rsupport.rsperm.lge");
    paramArrayList.add("com.rsupport.engine.input2");
    paramArrayList.add("com.rsupport.rsperm.ab.a1th");
    paramArrayList.add("com.rsupport.rsperm.ab.a2th");
    paramArrayList.add("com.rsupport.rsperm.ab.a3th");
    paramArrayList.add("com.rsupport.rsperm.ab.a4th");
    paramArrayList.add("com.rsupport.rsperm.ab.a5th");
    paramArrayList.add("com.rsupport.rsperm.ay.a1th");
    paramArrayList.add("com.rsupport.rsperm.bh.a1th");
    paramArrayList.add("com.rsupport.rsperm.az.a1th");
  }
  
  private void zW()
  {
    this.Zc.unregisterReceiver(this.fx);
    this.Zc = null;
    this.Zf = null;
  }
  
  public final String[] CB()
  {
    synchronized (this.Zf)
    {
      Object localObject3 = Collections.list(this.aHg.elements());
      Collections.sort((List)localObject3, new kx(this));
      String[] arrayOfString = new String[((ArrayList)localObject3).size()];
      localObject3 = ((ArrayList)localObject3).iterator();
      int i = 0;
      if (!((Iterator)localObject3).hasNext()) {
        return arrayOfString;
      }
      arrayOfString[i] = ((kw)((Iterator)localObject3).next()).packageName;
      i += 1;
    }
  }
  
  public final kr Co()
  {
    return new ky(this.Zc);
  }
}
