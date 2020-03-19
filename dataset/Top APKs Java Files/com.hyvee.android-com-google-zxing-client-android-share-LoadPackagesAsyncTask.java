package com.google.zxing.client.android.share;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

final class LoadPackagesAsyncTask
  extends AsyncTask<List<String[]>, Void, List<String[]>>
{
  private static final String[] PKG_PREFIX_BLACKLIST = { "com.android.", "android", "com.google.android.", "com.htc" };
  private static final String[] PKG_PREFIX_WHITELIST = { "com.google.android.apps." };
  private final AppPickerActivity activity;
  
  LoadPackagesAsyncTask(AppPickerActivity paramAppPickerActivity)
  {
    this.activity = paramAppPickerActivity;
  }
  
  private static boolean isHidden(String paramString)
  {
    if (paramString == null) {
      return true;
    }
    String[] arrayOfString = PKG_PREFIX_WHITELIST;
    int j = arrayOfString.length;
    int i = 0;
    label15:
    if (i >= j)
    {
      arrayOfString = PKG_PREFIX_BLACKLIST;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        return false;
        if (paramString.startsWith(arrayOfString[i])) {
          return false;
        }
        i += 1;
        break label15;
      }
      if (paramString.startsWith(arrayOfString[i])) {
        break;
      }
      i += 1;
    }
  }
  
  protected List<String[]> doInBackground(List<String[]>... paramVarArgs)
  {
    paramVarArgs = paramVarArgs[0];
    PackageManager localPackageManager = this.activity.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        Collections.sort(paramVarArgs, new ByFirstStringComparator(null));
        return paramVarArgs;
      }
      Object localObject = (ApplicationInfo)localIterator.next();
      CharSequence localCharSequence = ((ApplicationInfo)localObject).loadLabel(localPackageManager);
      if (localCharSequence != null)
      {
        localObject = ((ApplicationInfo)localObject).packageName;
        if (!isHidden((String)localObject)) {
          paramVarArgs.add(new String[] { localCharSequence.toString(), localObject });
        }
      }
    }
  }
  
  /* Error */
  protected void onPostExecute(List<String[]> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 118	java/util/ArrayList
    //   5: dup
    //   6: aload_1
    //   7: invokeinterface 122 1 0
    //   12: invokespecial 125	java/util/ArrayList:<init>	(I)V
    //   15: astore_2
    //   16: aload_1
    //   17: invokeinterface 68 1 0
    //   22: astore_1
    //   23: aload_1
    //   24: invokeinterface 74 1 0
    //   29: ifne +29 -> 58
    //   32: new 127	android/widget/ArrayAdapter
    //   35: dup
    //   36: aload_0
    //   37: getfield 38	com/google/zxing/client/android/share/LoadPackagesAsyncTask:activity	Lcom/google/zxing/client/android/share/AppPickerActivity;
    //   40: ldc -128
    //   42: aload_2
    //   43: invokespecial 131	android/widget/ArrayAdapter:<init>	(Landroid/content/Context;ILjava/util/List;)V
    //   46: astore_1
    //   47: aload_0
    //   48: getfield 38	com/google/zxing/client/android/share/LoadPackagesAsyncTask:activity	Lcom/google/zxing/client/android/share/AppPickerActivity;
    //   51: aload_1
    //   52: invokevirtual 135	com/google/zxing/client/android/share/AppPickerActivity:setListAdapter	(Landroid/widget/ListAdapter;)V
    //   55: aload_0
    //   56: monitorexit
    //   57: return
    //   58: aload_2
    //   59: aload_1
    //   60: invokeinterface 87 1 0
    //   65: checkcast 136	[Ljava/lang/String;
    //   68: iconst_0
    //   69: aaload
    //   70: invokeinterface 109 2 0
    //   75: pop
    //   76: goto -53 -> 23
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	LoadPackagesAsyncTask
    //   0	84	1	paramList	List<String[]>
    //   15	44	2	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   2	23	79	finally
    //   23	55	79	finally
    //   58	76	79	finally
  }
  
  private static class ByFirstStringComparator
    implements Comparator<String[]>, Serializable
  {
    private ByFirstStringComparator() {}
    
    public int compare(String[] paramArrayOfString1, String[] paramArrayOfString2)
    {
      return paramArrayOfString1[0].compareTo(paramArrayOfString2[0]);
    }
  }
}
