package com.good.gd.ndkproxy.enterprise.policy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.good.gd.c.a.a;
import com.good.gd.c.a.b;
import com.good.gd.c.a.d;
import com.good.gd.c.a.e;
import com.good.gd.client.GDClient;
import com.good.gd.ndkproxy.GDLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class PolicyComplianceApplicationInfo
{
  private static PolicyComplianceApplicationInfo a;
  
  /* Error */
  private PolicyComplianceApplicationInfo()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 12	java/lang/Object:<init>	()V
    //   4: bipush 16
    //   6: ldc 14
    //   8: invokestatic 19	com/good/gd/ndkproxy/GDLog:a	(ILjava/lang/String;)V
    //   11: getstatic 24	com/good/gd/ndkproxy/NativeExecutionHandler:a	Ljava/lang/Object;
    //   14: astore_1
    //   15: aload_1
    //   16: monitorenter
    //   17: aload_0
    //   18: invokevirtual 27	com/good/gd/ndkproxy/enterprise/policy/PolicyComplianceApplicationInfo:ndkInit	()V
    //   21: aload_1
    //   22: monitorexit
    //   23: return
    //   24: astore_2
    //   25: aload_1
    //   26: monitorexit
    //   27: aload_2
    //   28: athrow
    //   29: astore_1
    //   30: new 10	java/lang/Exception
    //   33: dup
    //   34: ldc 29
    //   36: aload_1
    //   37: invokespecial 32	java/lang/Exception:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	PolicyComplianceApplicationInfo
    //   29	8	1	localException	Exception
    //   24	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   17	23	24	finally
    //   11	17	29	java/lang/Exception
    //   25	29	29	java/lang/Exception
  }
  
  public static PolicyComplianceApplicationInfo a()
  {
    if (a == null) {
      a = new PolicyComplianceApplicationInfo();
    }
    return a;
  }
  
  private String checkApplicationsForMalware(byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2)
  {
    int j = 0;
    GDLog.a(19, "PolicyComplianceApplicationInfo::checkApplicationsForMalware() IN\n");
    Object localObject1 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    int i;
    if (paramArrayOfByte1 != null)
    {
      i = 0;
      while (i < paramArrayOfByte1.length)
      {
        localArrayList2 = new ArrayList();
        localObject2 = new String(paramArrayOfByte1[i]);
        Object localObject3 = getNamePattern((String)localObject2);
        if (localObject3 != null) {
          localArrayList2.add(localObject3);
        }
        localObject3 = new d(d.a);
        ((d)localObject3).a(localArrayList2);
        ((d)localObject3).a((String)localObject2);
        localArrayList1.add(localObject3);
        i += 1;
      }
    }
    ArrayList localArrayList2 = new ArrayList();
    if (paramArrayOfByte2 != null)
    {
      i = j;
      while (i < paramArrayOfByte2.length)
      {
        paramArrayOfByte1 = new ArrayList();
        localObject2 = getNamePattern(new String(paramArrayOfByte2[i]));
        if (localObject2 != null) {
          paramArrayOfByte1.add(localObject2);
        }
        localObject2 = new d(d.b);
        ((d)localObject2).a(paramArrayOfByte1);
        localArrayList2.add(localObject2);
        i += 1;
      }
    }
    paramArrayOfByte2 = "";
    Object localObject2 = getInstalledProcesses(GDClient.a().j());
    paramArrayOfByte1 = paramArrayOfByte2;
    if (localObject2 != null)
    {
      localObject2 = ((List)localObject2).iterator();
      do
      {
        paramArrayOfByte1 = paramArrayOfByte2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        paramArrayOfByte1 = (ApplicationInfo)((Iterator)localObject2).next();
        GDLog.a(19, "PolicyComplianceApplicationInfo::checkApplicationsForMalware(): checking: [" + paramArrayOfByte1.uid + "] " + paramArrayOfByte1.processName + "\n");
        paramArrayOfByte1 = a.a(localArrayList1, localArrayList2, paramArrayOfByte1);
      } while ((paramArrayOfByte1 == null) || (!paramArrayOfByte1.a()));
      ((List)localObject1).add(paramArrayOfByte1);
      paramArrayOfByte1 = paramArrayOfByte1.b();
      GDLog.a(19, "PolicyComplianceApplicationInfo::checkApplicationsForMalware() - match:" + paramArrayOfByte1 + "\n");
    }
    paramArrayOfByte2 = ((List)localObject1).iterator();
    while (paramArrayOfByte2.hasNext())
    {
      localObject1 = (e)paramArrayOfByte2.next();
      GDLog.a(19, "PolicyComplianceApplicationInfo::checkApplicationsForMalware(): watch result:" + ((e)localObject1).b() + "\n");
    }
    GDLog.a(19, "PolicyComplianceApplicationInfo::checkApplicationsForMalware() OUT\n");
    return paramArrayOfByte1;
  }
  
  private String checkPlatformIntegrity()
  {
    return b.a().d();
  }
  
  private static List<ApplicationInfo> getInstalledProcesses(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledApplications(0);
  }
  
  private static Pattern getNamePattern(String paramString)
  {
    try
    {
      paramString = Pattern.compile(paramString, 2);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  final native void ndkInit();
}
