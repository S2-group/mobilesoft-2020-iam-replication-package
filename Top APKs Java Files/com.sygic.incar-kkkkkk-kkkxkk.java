package kkkkkk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class kkkxkk
  implements Enumeration<String>
{
  public static int b041E041EОООО = 2;
  public static int b041EООООО = 0;
  public static int bН041D041D041D041D041D = 13;
  public static int bО041EОООО = 1;
  private Iterator<PackageInfo> b041DН041D041D041D041D = null;
  private Context bНН041D041D041D041D;
  
  public kkkxkk(Context paramContext)
  {
    this.bНН041D041D041D041D = paramContext;
  }
  
  public static int b044D044Dээ044D044D()
  {
    return 13;
  }
  
  private void bэ044D044Dэ044D044D()
  {
    int i;
    if (this.b041DН041D041D041D041D == null)
    {
      i = bН041D041D041D041D041D;
      switch (i * (bО041EОООО + i) % b041E041EОООО)
      {
      default: 
        bН041D041D041D041D041D = 5;
        b041EООООО = b044D044Dээ044D044D();
      }
    }
    try
    {
      List localList = this.bНН041D041D041D041D.getPackageManager().getInstalledPackages(0);
      i = localList.size();
      if ((bН041D041D041D041D041D + bО041EОООО) * bН041D041D041D041D041D % b041E041EОООО != b041EООООО)
      {
        bН041D041D041D041D041D = 64;
        b041EООООО = 20;
      }
      if (localList == null) {
        throw new xkkxkk();
      }
      if (i == 0) {
        throw new xkkxkk();
      }
      this.b041DН041D041D041D041D = localList.iterator();
      return;
    }
    catch (Exception localException)
    {
      throw new xkkxkk();
    }
  }
  
  public static int bээ044Dэ044D044D()
  {
    return 1;
  }
  
  public String b044Dэ044Dэ044D044D()
  {
    bэ044D044Dэ044D044D();
    int i = bН041D041D041D041D041D;
    switch (i * (bО041EОООО + i) % b041E041EОООО)
    {
    default: 
      bН041D041D041D041D041D = 55;
      b041EООООО = b044D044Dээ044D044D();
    }
    PackageInfo localPackageInfo = (PackageInfo)this.b041DН041D041D041D041D.next();
    if ((bН041D041D041D041D041D + bО041EОООО) * bН041D041D041D041D041D % b041E041EОООО != b041EООООО)
    {
      bН041D041D041D041D041D = b044D044Dээ044D044D();
      b041EООООО = b044D044Dээ044D044D();
    }
    return localPackageInfo.packageName;
  }
  
  public boolean hasMoreElements()
  {
    if ((bН041D041D041D041D041D + bО041EОООО) * bН041D041D041D041D041D % b041E041EОООО != b041EООООО)
    {
      bН041D041D041D041D041D = 0;
      b041EООООО = 84;
    }
    bэ044D044Dэ044D044D();
    int i = bН041D041D041D041D041D;
    switch (i * (bО041EОООО + i) % b041E041EОООО)
    {
    default: 
      bН041D041D041D041D041D = 79;
      b041EООООО = 92;
    }
    return this.b041DН041D041D041D041D.hasNext();
  }
}
