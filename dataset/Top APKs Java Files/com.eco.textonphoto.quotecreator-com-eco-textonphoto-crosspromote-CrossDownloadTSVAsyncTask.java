package com.eco.textonphoto.crosspromote;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossDownloadTSVAsyncTask
  extends AsyncTask<Void, Void, Void>
{
  public static int curentAge = 0;
  public static String linkRootListAppCSV = "http://kdata.tiasangmoi.com/eco_cross_promote/";
  private ArrayList<CrossAppObject> arrayListAll;
  private Context context;
  private CrossIconCache crossIconCache;
  private String language;
  private CrossAppObject resultpAppShow;
  private int selectIndexShow = 0;
  
  public CrossDownloadTSVAsyncTask(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.language = paramString;
    this.crossIconCache = new CrossIconCache();
  }
  
  public static CrossAppObject getAppObjectFromLine(String paramString)
  {
    try
    {
      paramString = paramString.split("\t");
      if (Integer.parseInt(paramString[5]) > curentAge) {
        return null;
      }
      paramString = new CrossAppObject(paramString[0], paramString[1], paramString[2], paramString[3], paramString[4]);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static ArrayList<CrossAppObject> getArrayListAppFromTSVFile(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    new CrossAppObject();
    List localList = paramContext.getPackageManager().getInstalledApplications(128);
    if (!isConnectingToInternet(paramContext)) {}
    for (;;)
    {
      int i = 1;
      try
      {
        paramString = new BufferedInputStream(((HttpURLConnection)new URL(paramString).openConnection()).getInputStream());
        Object localObject1 = new BufferedReader(new InputStreamReader(paramString, "utf8"), 8192);
        ((BufferedReader)localObject1).readLine();
        for (;;)
        {
          Object localObject2 = ((BufferedReader)localObject1).readLine();
          if (localObject2 == null) {
            break;
          }
          localObject2 = getAppObjectFromLine((String)localObject2);
          if ((localObject2 != null) && (!isInstall(((CrossAppObject)localObject2).getIdApp(), paramContext, localList))) {
            localArrayList.add(localObject2);
          }
        }
        if (localArrayList != null) {
          writeObj(paramContext, localArrayList, "savedata.dat");
        }
        paramString.close();
        i = 0;
        paramString = localArrayList;
        if (i == 1)
        {
          localObject1 = (ArrayList)readObj(paramContext, "savedata.dat");
          paramString = localArrayList;
          if (localObject1 != null)
          {
            paramString = removeAppInstalled((ArrayList)localObject1, paramContext, localList);
            writeObj(paramContext, paramString, "savedata.dat");
          }
        }
        return paramString;
      }
      catch (Exception paramString) {}
    }
  }
  
  public static int getRandomNumber(int paramInt1, int paramInt2)
  {
    return new Random().nextInt(paramInt2 - paramInt1 + 1) + paramInt1;
  }
  
  public static boolean isConnectingToInternet(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  public static boolean isInstall(String paramString, Context paramContext, List<ApplicationInfo> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if (((ApplicationInfo)paramList.get(i)).packageName.contains(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static Object readObj(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new ObjectInputStream(paramContext.openFileInput(paramString));
      paramString = paramContext.readObject();
      paramContext.close();
      return paramString;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static ArrayList<CrossAppObject> removeAppInstalled(ArrayList<CrossAppObject> paramArrayList, Context paramContext, List<ApplicationInfo> paramList)
  {
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      int k = j;
      int m = i;
      if (isInstall(((CrossAppObject)paramArrayList.get(i)).getIdApp(), paramContext, paramList))
      {
        paramArrayList.remove(i);
        m = i - 1;
        k = j - 1;
      }
      i = m + 1;
      j = k;
    }
    return paramArrayList;
  }
  
  public static String upStringDes(String paramString)
  {
    String str = paramString.substring(0, 1).toUpperCase();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append(paramString.substring(1));
    return localStringBuilder.toString();
  }
  
  public static String upStringTitle(String paramString)
  {
    String[] arrayOfString = paramString.split(" ");
    paramString = "";
    int i = 0;
    while (i < arrayOfString.length)
    {
      StringBuilder localStringBuilder;
      if (i == 0)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(upStringDes(arrayOfString[i]));
        paramString = localStringBuilder.toString();
      }
      else
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(" ");
        localStringBuilder.append(upStringDes(arrayOfString[i]));
        paramString = localStringBuilder.toString();
      }
      i += 1;
    }
    return paramString;
  }
  
  public static void writeObj(Context paramContext, Object paramObject, String paramString)
  {
    try
    {
      paramContext = new ObjectOutputStream(paramContext.openFileOutput(paramString, 0));
      paramContext.writeObject(paramObject);
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    paramVarArgs = this.context;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(linkRootListAppCSV);
    localStringBuilder.append(this.language);
    localStringBuilder.append(".tsv");
    this.arrayListAll = getArrayListAppFromTSVFile(paramVarArgs, localStringBuilder.toString());
    int i = 0;
    while (i < this.arrayListAll.size())
    {
      if (this.arrayListAll != null) {
        this.crossIconCache.fetch(((CrossAppObject)this.arrayListAll.get(i)).getLinkIcon(), null);
      }
      i += 1;
    }
    return null;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    if (this.arrayListAll.size() > 0)
    {
      this.selectIndexShow = getRandomNumber(0, this.arrayListAll.size() - 1);
      this.resultpAppShow = ((CrossAppObject)this.arrayListAll.get(this.selectIndexShow));
    }
  }
  
  protected void onPreExecute()
  {
    super.onPreExecute();
  }
}
