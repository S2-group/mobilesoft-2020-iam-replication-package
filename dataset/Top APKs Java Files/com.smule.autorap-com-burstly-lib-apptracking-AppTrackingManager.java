package com.burstly.lib.apptracking;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.burstly.lib.feature.apptracker.IAppTracker;
import com.burstly.lib.feature.apptracker.IAppTrackingListener;
import com.burstly.lib.util.LoggerExt;
import com.burstly.lib.util.PrioritizedAsyncTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class AppTrackingManager
  implements IAppTracker
{
  private static final LoggerExt LOG = ;
  private static final String SERVICE_URL = "http://an.appads.com/Info.svc/UrlSchemeGet/";
  private static final String TAG = "InstalledAppTracker";
  
  public AppTrackingManager() {}
  
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
    //   0: getstatic 27	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   3: ldc 17
    //   5: ldc -92
    //   7: iconst_2
    //   8: anewarray 4	java/lang/Object
    //   11: dup
    //   12: iconst_0
    //   13: ldc 14
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: aload_0
    //   19: aastore
    //   20: invokevirtual 57	com/burstly/lib/util/LoggerExt:logDebug	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   23: pop
    //   24: new 166	java/io/BufferedReader
    //   27: dup
    //   28: new 168	java/io/InputStreamReader
    //   31: dup
    //   32: new 170	java/net/URL
    //   35: dup
    //   36: new 118	java/lang/StringBuilder
    //   39: dup
    //   40: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   43: ldc 14
    //   45: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: aload_0
    //   49: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokespecial 173	java/net/URL:<init>	(Ljava/lang/String;)V
    //   58: invokevirtual 177	java/net/URL:openStream	()Ljava/io/InputStream;
    //   61: invokespecial 180	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   64: invokespecial 183	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   67: astore_0
    //   68: new 118	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   75: astore_1
    //   76: aload_0
    //   77: invokevirtual 186	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   80: astore_2
    //   81: aload_2
    //   82: ifnull +24 -> 106
    //   85: aload_1
    //   86: aload_2
    //   87: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: goto -15 -> 76
    //   94: astore_0
    //   95: getstatic 27	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   98: ldc 17
    //   100: aload_0
    //   101: invokevirtual 190	com/burstly/lib/util/LoggerExt:logThrowable	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   104: aconst_null
    //   105: areturn
    //   106: aload_0
    //   107: invokevirtual 193	java/io/BufferedReader:close	()V
    //   110: aload_1
    //   111: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: astore_0
    //   115: getstatic 27	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   118: ldc 17
    //   120: ldc -61
    //   122: iconst_1
    //   123: anewarray 4	java/lang/Object
    //   126: dup
    //   127: iconst_0
    //   128: aload_0
    //   129: aastore
    //   130: invokevirtual 57	com/burstly/lib/util/LoggerExt:logDebug	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   133: pop
    //   134: aload_0
    //   135: invokestatic 198	com/burstly/lib/apptracking/AppTrackingManager:parsePackageNames	(Ljava/lang/String;)Ljava/util/List;
    //   138: astore_0
    //   139: aload_0
    //   140: areturn
    //   141: astore_0
    //   142: getstatic 27	com/burstly/lib/apptracking/AppTrackingManager:LOG	Lcom/burstly/lib/util/LoggerExt;
    //   145: ldc 17
    //   147: aload_0
    //   148: invokevirtual 190	com/burstly/lib/util/LoggerExt:logThrowable	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   151: goto -47 -> 104
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	paramString	String
    //   75	36	1	localStringBuilder	StringBuilder
    //   80	7	2	str	String
    // Exception table:
    //   from	to	target	type
    //   0	76	94	java/net/MalformedURLException
    //   76	81	94	java/net/MalformedURLException
    //   85	91	94	java/net/MalformedURLException
    //   106	139	94	java/net/MalformedURLException
    //   0	76	141	java/io/IOException
    //   76	81	141	java/io/IOException
    //   85	91	141	java/io/IOException
    //   106	139	141	java/io/IOException
  }
  
  private static String getPubTargetingString(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      LOG.logDebug("InstalledAppTracker", "Context is null. Cannot get the installed apps.", new Object[0]);
    }
    do
    {
      for (;;)
      {
        return "";
        if ((paramString != null) && (paramString.length() != 0)) {
          break;
        }
        LOG.logDebug("InstalledAppTracker", "Organization ID is null or empty. Cannot get the package names from server.", new Object[0]);
      }
      paramString = downloadPackageNames(paramString);
    } while ((paramString == null) || (paramString.size() <= 0));
    return createPubTargetingString(checkInstalledApps(paramContext, paramString));
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
  
  public AsyncTask<Void, Void, Void> getPubTargetingStringAsync(final Context paramContext, final String paramString, final IAppTrackingListener paramIAppTrackingListener)
  {
    LOG.logDebug("InstalledAppTracker", "Starting new app tracking task...", new Object[0]);
    if (paramIAppTrackingListener == null)
    {
      LOG.logDebug("InstalledAppTracker", "Listener is null. No way to publish results.", new Object[0]);
      return null;
    }
    new PrioritizedAsyncTask()
    {
      protected Void performInBackground()
      {
        try
        {
          paramIAppTrackingListener.getPubTargetingStringComplete(AppTrackingManager.getPubTargetingString(paramContext, paramString));
          return null;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            AppTrackingManager.LOG.logThrowable("InstalledAppTracker", localException);
          }
        }
      }
    }.execute(new Void[0]);
  }
}
