package com.google.zxing.client.android.e;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

final class d
  extends AsyncTask<List<String[]>, Object, List<String[]>>
{
  private static final String[] a = { "com.google.android.apps." };
  private static final String[] b = { "com.android.", "android", "com.google.android.", "com.htc" };
  private final a c;
  
  d(a paramA)
  {
    this.c = paramA;
  }
  
  private static boolean a(String paramString)
  {
    if (paramString == null) {
      return true;
    }
    String[] arrayOfString = a;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramString.startsWith(arrayOfString[i])) {
        return false;
      }
      i += 1;
    }
    arrayOfString = b;
    j = arrayOfString.length;
    i = 0;
    for (;;)
    {
      if (i >= j) {
        break label70;
      }
      if (paramString.startsWith(arrayOfString[i])) {
        break;
      }
      i += 1;
    }
    label70:
    return false;
  }
  
  protected List<String[]> a(List<String[]>... paramVarArgs)
  {
    paramVarArgs = paramVarArgs[0];
    PackageManager localPackageManager = this.c.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      CharSequence localCharSequence = ((ApplicationInfo)localObject).loadLabel(localPackageManager);
      if (localCharSequence != null)
      {
        localObject = ((ApplicationInfo)localObject).packageName;
        if (!a((String)localObject)) {
          paramVarArgs.add(new String[] { localCharSequence.toString(), localObject });
        }
      }
    }
    Collections.sort(paramVarArgs, new a(null));
    return paramVarArgs;
  }
  
  protected void a(List<String[]> paramList)
  {
    try
    {
      ArrayList localArrayList = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localArrayList.add(((String[])paramList.next())[0]);
      }
      paramList = new ArrayAdapter(this.c, 17367043, localArrayList);
    }
    finally {}
    this.c.setListAdapter(paramList);
  }
  
  private static class a
    implements Serializable, Comparator<String[]>
  {
    private a() {}
    
    public int a(String[] paramArrayOfString1, String[] paramArrayOfString2)
    {
      return paramArrayOfString1[0].compareTo(paramArrayOfString2[0]);
    }
  }
}
