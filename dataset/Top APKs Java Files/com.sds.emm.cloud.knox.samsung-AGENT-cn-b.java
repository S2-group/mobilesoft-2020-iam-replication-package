package AGENT.cn;

import AGENT.ch.a;

public class b
{
  public static String[] a(int paramInt)
  {
    boolean bool = a.g();
    String[] arrayOfString = null;
    Object localObject;
    if (bool)
    {
      localObject = a.a(paramInt);
      if (localObject != null) {
        return ((com.samsung.android.knox.container.KnoxContainerManager)localObject).getApplicationPolicy().getInstalledApplicationsIDList();
      }
    }
    else
    {
      localObject = a.b(paramInt);
      if (localObject != null) {
        arrayOfString = ((com.sec.enterprise.knox.container.KnoxContainerManager)localObject).getApplicationPolicy().getInstalledApplicationsIDList();
      }
    }
    return arrayOfString;
  }
}
