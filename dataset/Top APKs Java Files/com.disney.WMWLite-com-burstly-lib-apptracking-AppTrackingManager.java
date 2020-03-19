package com.burstly.lib.apptracking;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.burstly.lib.util.LoggerExt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class AppTrackingManager
{
  private static final LoggerExt LOG = ;
  private static final String SERVICE_URL = "http://an.appads.com/Info.svc/UrlSchemeGet/";
  private static final String TAG = "InstalledAppTracker";
  
  private AppTrackingManager() {}
  
  private static Map<String, AppInstallState> checkInstalledApps(Context paramContext, List<String> paramList)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0);
    LOG.logDebug("InstalledAppTracker", "All installed apps: {0}", new Object[] { localObject });
    paramContext = new HashMap((int)(paramList.size() / 0.75D));
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      paramContext.put((String)paramList.next(), AppInstallState.NOT_INSTALLED);
    }
    paramList = ((List)localObject).iterator();
    while (paramList.hasNext())
    {
      localObject = (ApplicationInfo)paramList.next();
      if (paramContext.get(((ApplicationInfo)localObject).packageName) != null) {
        paramContext.put(((ApplicationInfo)localObject).packageName, AppInstallState.INSTALLED);
      }
    }
    LOG.logDebug("InstalledAppTracker", "Detected app states: {0}", new Object[] { paramContext });
    return paramContext;
  }
  
  private static String createPubTargetingString(Map<String, AppInstallState> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append('=');
      localStringBuilder.append(((AppInstallState)localEntry.getValue()).getStateValue());
      if (paramMap.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    LOG.logDebug("InstalledAppTracker", "Constructed pub targeting string: {0}", new Object[] { localStringBuilder });
    return localStringBuilder.toString();
  }
  
  /* Error */
  private static List<String> downloadPackageNames(String paramString)
  {
    // Byte code:
    //   0: getstatic 25	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   3: ldc 15
    //   5: ldc -94
    //   7: iconst_2
    //   8: anewarray 4	java/lang/Object
    //   11: dup
    //   12: iconst_0
    //   13: ldc 12
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: aload_0
    //   19: aastore
    //   20: invokevirtual 55	com/burstly/lib/util/LoggerExt:logDebug	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   23: new 164	java/io/BufferedReader
    //   26: dup
    //   27: new 166	java/io/InputStreamReader
    //   30: dup
    //   31: new 168	java/net/URL
    //   34: dup
    //   35: new 116	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   42: ldc 12
    //   44: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_0
    //   48: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokespecial 171	java/net/URL:<init>	(Ljava/lang/String;)V
    //   57: invokevirtual 175	java/net/URL:openStream	()Ljava/io/InputStream;
    //   60: invokespecial 178	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   63: invokespecial 181	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   66: astore_0
    //   67: new 116	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   74: astore_1
    //   75: aload_0
    //   76: invokevirtual 184	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   79: astore_2
    //   80: aload_2
    //   81: ifnull +24 -> 105
    //   84: aload_1
    //   85: aload_2
    //   86: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: goto -15 -> 75
    //   93: astore_0
    //   94: getstatic 25	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   97: ldc 15
    //   99: aload_0
    //   100: invokevirtual 188	com/burstly/lib/util/LoggerExt:logThrowable	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   103: aconst_null
    //   104: areturn
    //   105: aload_0
    //   106: invokevirtual 191	java/io/BufferedReader:close	()V
    //   109: aload_1
    //   110: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: astore_0
    //   114: getstatic 25	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   117: ldc 15
    //   119: ldc -63
    //   121: iconst_1
    //   122: anewarray 4	java/lang/Object
    //   125: dup
    //   126: iconst_0
    //   127: aload_0
    //   128: aastore
    //   129: invokevirtual 55	com/burstly/lib/util/LoggerExt:logDebug	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   132: aload_0
    //   133: invokestatic 196	com/burstly/lib/apptracking/AppTrackingManager:parsePackageNames	(Ljava/lang/String;)Ljava/util/List;
    //   136: astore_0
    //   137: aload_0
    //   138: areturn
    //   139: astore_0
    //   140: getstatic 25	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   143: ldc 15
    //   145: aload_0
    //   146: invokevirtual 188	com/burstly/lib/util/LoggerExt:logThrowable	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   149: goto -46 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	152	0	paramString	String
    //   74	36	1	localStringBuilder	StringBuilder
    //   79	7	2	str	String
    // Exception table:
    //   from	to	target	type
    //   0	75	93	java/net/MalformedURLException
    //   75	80	93	java/net/MalformedURLException
    //   84	90	93	java/net/MalformedURLException
    //   105	137	93	java/net/MalformedURLException
    //   0	75	139	java/io/IOException
    //   75	80	139	java/io/IOException
    //   84	90	139	java/io/IOException
    //   105	137	139	java/io/IOException
  }
  
  private static String getPubTargetingString(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      LOG.logDebug("InstalledAppTracker", "Context is null. Cannot get installed apps.", new Object[0]);
    }
    do
    {
      for (;;)
      {
        return "";
        if ((paramString != null) && (paramString.length() != 0)) {
          break;
        }
        LOG.logDebug("InstalledAppTracker", "Organization ID is null or empty. Cannot get package names from server.", new Object[0]);
      }
      paramString = downloadPackageNames(paramString);
    } while ((paramString == null) || (paramString.size() <= 0));
    return createPubTargetingString(checkInstalledApps(paramContext, paramString));
  }
  
  public static AsyncTask<Void, Void, Void> getPubTargetingStringAsync(final Context paramContext, final String paramString, IAppTrackingListener paramIAppTrackingListener)
  {
    LOG.logDebug("InstalledAppTracker", "Starting new app tracking task...", new Object[0]);
    if (paramIAppTrackingListener == null)
    {
      LOG.logDebug("InstalledAppTracker", "Listener is null. No way to publish results.", new Object[0]);
      return null;
    }
    paramContext = new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        try
        {
          this.val$listener.getPubTargetingStringComplete(AppTrackingManager.getPubTargetingString(paramContext, paramString));
          return null;
        }
        catch (Exception paramAnonymousVarArgs)
        {
          for (;;)
          {
            AppTrackingManager.LOG.logThrowable("InstalledAppTracker", paramAnonymousVarArgs);
          }
        }
      }
    };
    paramContext.execute(new Void[0]);
    return paramContext;
  }
  
  private static List<String> parsePackageNames(String paramString)
  {
    paramString = paramString.split("[ ,;\\[\\]\t\r\n\"']");
    ArrayList localArrayList = new ArrayList();
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString[i];
      if (localObject.length() != 0) {
        localArrayList.add(localObject);
      }
      i += 1;
    }
    LOG.logDebug("InstalledAppTracker", "Splitted apps: {0}. Will check their installation state...", new Object[] { localArrayList });
    return localArrayList;
  }
}
