package com.motorola.loop.touchTargets;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import com.motorola.loop.Constants.Complication;
import com.motorola.loop.utils.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TouchTargetManager
{
  private static TouchTargetManager sTouchTargetManager = null;
  private HashMap<String, TouchTargetPkgInfo> mAllWhiteListPackages;
  private WeakReference<Context> mContextWRef;
  private ArrayList<TouchTargetPkgInfo> mInstalledWhiteListPackages;
  private PackageManager mPackageManager;
  private TouchTargetPkgInfo mTopComplication;
  
  private TouchTargetManager(Context paramContext)
  {
    this.mContextWRef = new WeakReference(paramContext);
    this.mPackageManager = paramContext.getPackageManager();
    this.mInstalledWhiteListPackages = new ArrayList();
    this.mAllWhiteListPackages = new HashMap();
    new PackageLoadTask().execute(new ArrayList[] { this.mInstalledWhiteListPackages });
  }
  
  private void addNativeComplications(Context paramContext)
  {
    int j = NativeComplications.motoComplicationTextArrays.length;
    if (j != NativeComplications.motoComplicationIconArray.length)
    {
      Log.e("TouchTargetManager", "in-correct name/icon native complication array");
      throw new AssertionError();
    }
    Set localSet = PreferenceManager.getDefaultSharedPreferences(paramContext).getStringSet("disabled_complications", null);
    int i = 0;
    if (i < j)
    {
      if ((localSet != null) && (localSet.contains(NativeComplications.motoComplicationTypeArray[i].toString()))) {}
      for (;;)
      {
        i += 1;
        break;
        String str = paramContext.getResources().getString(NativeComplications.motoComplicationTextArrays[i]);
        TouchTargetPkgInfo localTouchTargetPkgInfo = new TouchTargetPkgInfo(str, NativeComplications.motoComplicationTextArrays[i], str, NativeComplications.motoComplicationIconArray[i], Integer.MAX_VALUE, NativeComplications.motoComplicationTypeArray[i]);
        if (i == 0)
        {
          this.mTopComplication = localTouchTargetPkgInfo;
        }
        else
        {
          this.mAllWhiteListPackages.put(str, localTouchTargetPkgInfo);
          this.mInstalledWhiteListPackages.add(localTouchTargetPkgInfo);
        }
      }
    }
    sortAndTopComplication(this.mInstalledWhiteListPackages);
  }
  
  public static void destroy()
  {
    try
    {
      sTouchTargetManager = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static int getComplicationIndexS(Constants.Complication paramComplication, String paramString)
  {
    if ((sTouchTargetManager == null) || (paramComplication == null)) {
      return 0;
    }
    return sTouchTargetManager.getComplicationIndex(paramComplication, paramString);
  }
  
  public static TouchTargetManager getInstance(Context paramContext)
  {
    try
    {
      if (sTouchTargetManager == null) {
        sTouchTargetManager = new TouchTargetManager(paramContext);
      }
      paramContext = sTouchTargetManager;
      return paramContext;
    }
    finally {}
  }
  
  private void loadThirdPartyPackage(Context paramContext)
  {
    this.mAllWhiteListPackages = TouchTargetConstants.getTPartyWhiteListPackages(paramContext);
    paramContext = this.mAllWhiteListPackages.values().iterator();
    while (paramContext.hasNext())
    {
      TouchTargetPkgInfo localTouchTargetPkgInfo = (TouchTargetPkgInfo)paramContext.next();
      this.mInstalledWhiteListPackages.add(localTouchTargetPkgInfo);
    }
  }
  
  private void sortAndTopComplication(ArrayList<TouchTargetPkgInfo> paramArrayList)
  {
    Collections.sort(this.mInstalledWhiteListPackages, new AppNameComparator(null));
    this.mInstalledWhiteListPackages.add(0, this.mTopComplication);
  }
  
  private void sortRemoveAddTopComplication(ArrayList<TouchTargetPkgInfo> paramArrayList)
  {
    this.mInstalledWhiteListPackages.remove(0);
    Collections.sort(this.mInstalledWhiteListPackages, new AppNameComparator(null));
    this.mInstalledWhiteListPackages.add(0, this.mTopComplication);
  }
  
  public void addPackageName(String paramString)
  {
    if (this.mAllWhiteListPackages.containsKey(paramString))
    {
      this.mInstalledWhiteListPackages.add(this.mAllWhiteListPackages.get(paramString));
      sortRemoveAddTopComplication(this.mInstalledWhiteListPackages);
      Log.d("TouchTargetManager", "added package : " + paramString);
    }
  }
  
  public ArrayList<TouchTargetPkgInfo> getAllInstalledComplications()
  {
    return this.mInstalledWhiteListPackages;
  }
  
  public String getAppName(String paramString)
  {
    paramString = (TouchTargetPkgInfo)this.mAllWhiteListPackages.get(paramString);
    if (paramString == null) {
      return null;
    }
    return paramString.name;
  }
  
  public int getComplicationIndex(Constants.Complication paramComplication, String paramString)
  {
    int j;
    if (paramComplication == null)
    {
      j = 0;
      return j;
    }
    int i = 0;
    for (;;)
    {
      if (i >= this.mInstalledWhiteListPackages.size()) {
        break label93;
      }
      if (paramComplication == ((TouchTargetPkgInfo)this.mInstalledWhiteListPackages.get(i)).compType)
      {
        j = i;
        if (paramComplication != Constants.Complication.APP_LAUNCH) {
          break;
        }
        if ((paramComplication == Constants.Complication.APP_LAUNCH) && (paramString != null))
        {
          j = i;
          if (paramString.contentEquals(((TouchTargetPkgInfo)this.mInstalledWhiteListPackages.get(i)).packageName)) {
            break;
          }
        }
      }
      i += 1;
    }
    label93:
    return 0;
  }
  
  public boolean isPacakgeInstalled(String paramString)
  {
    if ((sTouchTargetManager == null) || (paramString == null)) {
      return false;
    }
    int i = 0;
    while (i < this.mInstalledWhiteListPackages.size())
    {
      if (paramString.contentEquals(((TouchTargetPkgInfo)this.mInstalledWhiteListPackages.get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean isWhiteListPackage(String paramString)
  {
    return this.mAllWhiteListPackages.containsKey(paramString);
  }
  
  public void removePackage(String paramString)
  {
    if (this.mAllWhiteListPackages.containsKey(paramString))
    {
      Iterator localIterator = this.mInstalledWhiteListPackages.iterator();
      while (localIterator.hasNext()) {
        if (((TouchTargetPkgInfo)localIterator.next()).packageName.equalsIgnoreCase(paramString))
        {
          Log.d("TouchTargetManager", "removed package : " + paramString);
          localIterator.remove();
        }
      }
    }
  }
  
  private static class AppNameComparator
    implements Comparator<TouchTargetPkgInfo>
  {
    private AppNameComparator() {}
    
    public int compare(TouchTargetPkgInfo paramTouchTargetPkgInfo1, TouchTargetPkgInfo paramTouchTargetPkgInfo2)
    {
      return paramTouchTargetPkgInfo1.name.compareTo(paramTouchTargetPkgInfo2.name);
    }
  }
  
  public class PackageLoadTask
    extends AsyncTask<ArrayList<TouchTargetPkgInfo>, Void, Void>
  {
    public PackageLoadTask() {}
    
    public Void doInBackground(ArrayList<TouchTargetPkgInfo>... paramVarArgs)
    {
      TouchTargetConstants.prepareTPartyWhiteListPackages((Context)TouchTargetManager.this.mContextWRef.get());
      Object localObject1 = TouchTargetManager.this.mPackageManager.getInstalledPackages(1);
      paramVarArgs = new HashMap();
      localObject1 = ((List)localObject1).iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        paramVarArgs.put(((PackageInfo)localObject2).packageName, localObject2);
      }
      TouchTargetManager.this.loadThirdPartyPackage((Context)TouchTargetManager.this.mContextWRef.get());
      localObject1 = TouchTargetManager.this.mInstalledWhiteListPackages.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (TouchTargetPkgInfo)((Iterator)localObject1).next();
        if (!paramVarArgs.containsKey(((TouchTargetPkgInfo)localObject2).packageName))
        {
          ((Iterator)localObject1).remove();
        }
        else
        {
          PackageInfo localPackageInfo = (PackageInfo)paramVarArgs.get(((TouchTargetPkgInfo)localObject2).packageName);
          ((TouchTargetPkgInfo)localObject2).launchIntent = TouchTargetManager.this.mPackageManager.getLaunchIntentForPackage(((TouchTargetPkgInfo)localObject2).packageName);
          if (((TouchTargetPkgInfo)localObject2).launchIntent == null) {
            Log.e("TouchTargetManager", "No launch intent found..");
          }
        }
      }
      TouchTargetManager.this.addNativeComplications((Context)TouchTargetManager.this.mContextWRef.get());
      return null;
    }
  }
}
