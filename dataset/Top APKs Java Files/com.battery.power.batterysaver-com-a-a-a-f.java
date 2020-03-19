package com.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f
{
  public static final String a = f.class.getSimpleName();
  
  public f() {}
  
  public static List<String> a(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((PackageInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  public static List<b> a(File paramFile, Context paramContext)
  {
    if (paramFile.isFile()) {
      paramFile = null;
    }
    File[] arrayOfFile;
    int k;
    int i;
    do
    {
      do
      {
        do
        {
          return paramFile;
          paramContext = new ArrayList();
          arrayOfFile = paramFile.listFiles();
          paramFile = paramContext;
        } while (arrayOfFile == null);
        paramFile = paramContext;
      } while (arrayOfFile.length == 0);
      k = arrayOfFile.length;
      i = 0;
      paramFile = paramContext;
    } while (i >= k);
    Object localObject1 = arrayOfFile[i];
    paramFile = new b();
    if (((File)localObject1).isFile()) {}
    for (;;)
    {
      i += 1;
      break;
      paramFile.a = ((File)localObject1).getName();
      localObject1 = ((File)localObject1).listFiles();
      if ((localObject1 != null) && (localObject1.length != 0))
      {
        int m = localObject1.length;
        int j = 0;
        while (j < m)
        {
          Object localObject2 = localObject1[j];
          if (localObject2.isDirectory()) {
            paramFile.b.add(localObject2.getName());
          }
          j += 1;
        }
      }
      paramContext.add(paramFile);
    }
  }
  
  public static JSONObject a(List<b> paramList)
  {
    JSONArray localJSONArray1 = new JSONArray();
    localJSONArray1.put("");
    JSONObject localJSONObject1 = new JSONObject();
    JSONArray localJSONArray2 = new JSONArray();
    paramList = paramList.iterator();
    JSONArray localJSONArray3;
    for (;;)
    {
      if (!paramList.hasNext()) {
        break label180;
      }
      Object localObject = (b)paramList.next();
      JSONObject localJSONObject2 = new JSONObject();
      try
      {
        localJSONObject2.put("dir", ((b)localObject).a);
        localJSONArray3 = new JSONArray();
        localObject = ((b)localObject).b.iterator();
        while (((Iterator)localObject).hasNext()) {
          localJSONArray3.put((String)((Iterator)localObject).next());
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    if (localJSONArray3.length() != 0) {
      localJSONException.put("subDir", localJSONArray3);
    }
    for (;;)
    {
      localJSONArray2.put(localJSONException);
      break;
      localJSONException.put("subDir", localJSONArray1);
    }
    try
    {
      label180:
      localJSONObject1.put("data", localJSONArray2);
      return localJSONObject1;
    }
    catch (JSONException paramList)
    {
      paramList.printStackTrace();
    }
    return localJSONObject1;
  }
}
