package pl.solidexplorer.files.attributes;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import pl.solidexplorer.SEApp;

public class ChownHelper
{
  private static final int[] ids = { 0, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 2000, 2001, 2002, 3001, 3002, 3003, 3004, 3005, 9998, 9999 };
  private static final String[] names = { "root", "system", "radio", "bluetooth", "graphics", "input", "audio", "camera", "log", "compass", "mount", "wifi", "adb", "install", "media", "dhcp", "sdcard_rw", "vpn", "keystore", "shell", "cache", "diag", "net_bt_admin", "net_bt", "inet", "net_raw", "net_admin", "misc", "nobody" };
  private static SparseArray<String> sMapping;
  
  public ChownHelper()
  {
    ensureMapping();
  }
  
  static void ensureMapping()
  {
    if (sMapping == null)
    {
      sMapping = new SparseArray();
      int i = 0;
      while (i < ids.length)
      {
        sMapping.put(ids[i], names[i]);
        i += 1;
      }
    }
  }
  
  public static String getOwnerName(int paramInt)
  {
    
    if (sMapping.indexOfKey(paramInt) >= 0) {
      return (String)sMapping.get(paramInt);
    }
    if (paramInt >= 10000) {
      return String.format("app_%d", new Object[] { Integer.valueOf(paramInt) });
    }
    return "unknown";
  }
  
  public ChownHelper.Owner fromId(int paramInt)
  {
    return new ChownHelper.Owner(this, paramInt, getOwnerName(paramInt));
  }
  
  public List<ChownHelper.Owner> getList()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < sMapping.size())
    {
      int j = sMapping.keyAt(i);
      localArrayList.add(new ChownHelper.Owner(this, j, (String)sMapping.get(j)));
      i += 1;
    }
    Object localObject = SEApp.get().getPackageManager().getInstalledApplications(0);
    Collections.sort((List)localObject, new ChownHelper.1(this));
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.uid >= 10000) {
        localArrayList.add(new ChownHelper.Owner(this, localApplicationInfo.uid, getOwnerName(localApplicationInfo.uid), localApplicationInfo));
      }
    }
    return localArrayList;
  }
}
