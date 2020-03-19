package com.fotoable.adbuttonlib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import com.fotoable.comlib.util.AsyncTask;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TadvertiseUtil
{
  static final String AD_PROVIDER_TEXT = "ad by madvertise";
  static final String ANIMATION_TYPE_DEFAULT = "fade";
  static final String ANIMATION_TYPE_FADE = "fade";
  static final String ANIMATION_TYPE_LEFT_TO_RIGHT = "left_to_right";
  static final String ANIMATION_TYPE_TOP_DOWN = "top_down";
  static final int BACKGROUND_COLOR_DEFAULT = 0;
  static final int BANNER_HEIGHT_DEFAULT = 53;
  static final String BANNER_TYPE_ALL = "all";
  static final String BANNER_TYPE_DEFAULT = "mma";
  static final String BANNER_TYPE_FULLSCREEN = "fullscreen";
  static final String BANNER_TYPE_LANDSCAPE = "landscape";
  static final String BANNER_TYPE_LEADERBOARD = "leaderboard";
  static final String BANNER_TYPE_MEDIUM_RECTANGLE = "medium_rectangle";
  static final String BANNER_TYPE_MMA = "mma";
  static final String BANNER_TYPE_PORTRAIT = "portrait";
  static final String BANNER_TYPE_RICH_MEDIA = "rich_media";
  static final String BANNER_TYPE_RICH_MEDIA_SHORT = "rm";
  static final int BANNER_WIDTH_DEFAULT = 320;
  static final int CONNECTION_TIMEOUT = 5000;
  static final boolean DELIVER_ONLY_TEXT_DEFAULT = false;
  static final String ENCODING = "UTF-8";
  static final int FULLSCREEN_BANNER_HEIGHT = 768;
  static final int FULLSCREEN_BANNER_WIDTH = 768;
  static final int LANDSCAPE_BANNER_HEIGHT = 66;
  static final int LANDSCAPE_BANNER_WIDTH = 1024;
  static final int LEADERBOARD_BANNER_HEIGHT = 90;
  static final int LEADERBOARD_BANNER_WIDTH = 728;
  static final String LOG = "MAD_LOG";
  private static final String MADVERTISE_SITE_TOKEN = "madvertise_site_token";
  static final String MAD_SERVER = "http://fotorus.fotoable.com";
  static final int MEDIUM_RECTANGLE_BANNER_HEIGHT = 250;
  static final int MEDIUM_RECTANGLE_BANNER_WIDTH = 300;
  static final int MMA_BANNER_HEIGHT = 53;
  static final int MMA_BANNER_WIDTH = 320;
  public static final int PLACEMENT_TYPE_INLINE = 0;
  public static final int PLACEMENT_TYPE_INTERSTITIAL = 1;
  static final int PORTRAIT_BANNER_HEIGHT = 66;
  static final int PORTRAIT_BANNER_WIDTH = 766;
  static final boolean PRINT_LOG = true;
  static final int SECONDS_TO_REFRESH_AD_DEFAULT = 45;
  static final int SECONDS_TO_REFRESH_LOCATION = 900;
  static final int TEXT_COLOR_DEFAULT = -1;
  static final int TEXT_SIZE_DEFAULT = 18;
  static final int TEXT_SIZE_PROVIDER = 10;
  private static Location sCurrentLocation;
  private static long sLocationUpdateTimestamp;
  private static String sUA;
  
  public TadvertiseUtil() {}
  
  private static void checkEmptyJson(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    if ((paramJSONObject != null) && (!paramString.equals(""))) {
      return;
    }
    throw new JSONException("Empty JSON or key");
  }
  
  public static boolean checkPermissionGranted(String paramString, Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  /* Error */
  public static String convertStreamToString(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 165	java/io/BufferedReader
    //   3: dup
    //   4: new 167	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 170	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: invokespecial 173	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_2
    //   16: new 175	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_2
    //   25: invokevirtual 180	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +40 -> 70
    //   33: new 175	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   40: astore 4
    //   42: aload 4
    //   44: aload_3
    //   45: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: aload 4
    //   51: ldc -70
    //   53: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload_1
    //   58: aload 4
    //   60: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: goto -43 -> 24
    //   70: aload_0
    //   71: invokevirtual 194	java/io/InputStream:close	()V
    //   74: goto +24 -> 98
    //   77: astore_0
    //   78: aload_0
    //   79: invokevirtual 197	java/io/IOException:printStackTrace	()V
    //   82: goto +16 -> 98
    //   85: astore_1
    //   86: goto +17 -> 103
    //   89: astore_2
    //   90: aload_2
    //   91: invokevirtual 197	java/io/IOException:printStackTrace	()V
    //   94: aload_0
    //   95: invokevirtual 194	java/io/InputStream:close	()V
    //   98: aload_1
    //   99: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: areturn
    //   103: aload_0
    //   104: invokevirtual 194	java/io/InputStream:close	()V
    //   107: goto +8 -> 115
    //   110: astore_0
    //   111: aload_0
    //   112: invokevirtual 197	java/io/IOException:printStackTrace	()V
    //   115: aload_1
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	paramInputStream	java.io.InputStream
    //   23	35	1	localStringBuilder1	StringBuilder
    //   85	31	1	localObject	Object
    //   15	10	2	localBufferedReader	java.io.BufferedReader
    //   89	2	2	localIOException	java.io.IOException
    //   28	17	3	str	String
    //   40	19	4	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   70	74	77	java/io/IOException
    //   94	98	77	java/io/IOException
    //   24	29	85	finally
    //   33	67	85	finally
    //   90	94	85	finally
    //   24	29	89	java/io/IOException
    //   33	67	89	java/io/IOException
    //   103	107	110	java/io/IOException
  }
  
  public static String getAllHeadersAsString(Header[] paramArrayOfHeader)
  {
    String str = "";
    int i = 0;
    while (i < paramArrayOfHeader.length)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("<< ");
      localStringBuilder.append(paramArrayOfHeader[i].getName());
      localStringBuilder.append(" : ");
      localStringBuilder.append(paramArrayOfHeader[i].getValue());
      localStringBuilder.append(" >>");
      str = localStringBuilder.toString();
      i += 1;
    }
    return str;
  }
  
  public static String getApplicationName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128)).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String getApplicationVersion(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public static String getHash(String paramString, HashType paramHashType)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_1
    //   4: invokevirtual 253	com/fotoable/adbuttonlib/TadvertiseUtil$HashType:toString	()Ljava/lang/String;
    //   7: invokestatic 259	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   10: astore_1
    //   11: aload_1
    //   12: aload_0
    //   13: invokevirtual 263	java/lang/String:getBytes	()[B
    //   16: invokevirtual 267	java/security/MessageDigest:update	([B)V
    //   19: aload_1
    //   20: invokevirtual 270	java/security/MessageDigest:digest	()[B
    //   23: astore_3
    //   24: new 272	java/lang/StringBuffer
    //   27: dup
    //   28: invokespecial 273	java/lang/StringBuffer:<init>	()V
    //   31: astore 4
    //   33: iconst_0
    //   34: istore_2
    //   35: iload_2
    //   36: aload_3
    //   37: arraylength
    //   38: if_icmpge +65 -> 103
    //   41: sipush 255
    //   44: aload_3
    //   45: iload_2
    //   46: baload
    //   47: iand
    //   48: invokestatic 279	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   51: astore_1
    //   52: aload_1
    //   53: astore_0
    //   54: aload_1
    //   55: invokevirtual 283	java/lang/String:length	()I
    //   58: iconst_2
    //   59: if_icmpge +30 -> 89
    //   62: new 175	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   69: astore_0
    //   70: aload_0
    //   71: ldc_w 285
    //   74: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload_0
    //   79: aload_1
    //   80: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload_0
    //   85: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: astore_0
    //   89: aload 4
    //   91: aload_0
    //   92: invokevirtual 288	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   95: pop
    //   96: iload_2
    //   97: iconst_1
    //   98: iadd
    //   99: istore_2
    //   100: goto -65 -> 35
    //   103: aload 4
    //   105: invokevirtual 289	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   108: astore_0
    //   109: ldc 2
    //   111: monitorexit
    //   112: aload_0
    //   113: areturn
    //   114: astore_0
    //   115: goto +22 -> 137
    //   118: astore_0
    //   119: aload_0
    //   120: invokevirtual 290	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   123: aconst_null
    //   124: iconst_3
    //   125: ldc_w 292
    //   128: invokestatic 296	com/fotoable/adbuttonlib/TadvertiseUtil:logMessage	(Ljava/lang/String;ILjava/lang/String;)V
    //   131: ldc 2
    //   133: monitorexit
    //   134: ldc -117
    //   136: areturn
    //   137: ldc 2
    //   139: monitorexit
    //   140: aload_0
    //   141: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	paramString	String
    //   0	142	1	paramHashType	HashType
    //   34	66	2	i	int
    //   23	22	3	arrayOfByte	byte[]
    //   31	73	4	localStringBuffer	StringBuffer
    // Exception table:
    //   from	to	target	type
    //   3	11	114	finally
    //   11	33	114	finally
    //   35	52	114	finally
    //   54	89	114	finally
    //   89	96	114	finally
    //   103	109	114	finally
    //   119	131	114	finally
    //   3	11	118	java/security/NoSuchAlgorithmException
  }
  
  public static String getHashedAndroidID(Context paramContext, HashType paramHashType)
  {
    paramContext = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "android_id");
    if (paramContext == null) {
      return "";
    }
    return getHash(paramContext, paramHashType);
  }
  
  public static String getHashedMacAddress(Context paramContext, HashType paramHashType)
  {
    if (checkPermissionGranted("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    } else {
      paramContext = null;
    }
    if (paramContext == null) {
      return "";
    }
    return getHash(paramContext, paramHashType);
  }
  
  private static ArrayList<PInfo> getInstalledApps(boolean paramBoolean, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      if ((paramBoolean) || (localPackageInfo.versionName != null))
      {
        PInfo localPInfo = new PInfo();
        PInfo.access$302(localPInfo, localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString());
        PInfo.access$402(localPInfo, localPackageInfo.packageName);
        PInfo.access$502(localPInfo, localPackageInfo.versionName);
        PInfo.access$602(localPInfo, localPackageInfo.versionCode);
        PInfo.access$702(localPInfo, localPackageInfo.applicationInfo.loadIcon(paramContext.getPackageManager()));
        localArrayList.add(localPInfo);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static JSONArray getJSONArray(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getJSONArray(paramString);
    }
    return new JSONArray();
  }
  
  public static boolean getJSONBoolean(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getBoolean(paramString);
    }
    return false;
  }
  
  public static int getJSONInt(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getInt(paramString);
    }
    return 0;
  }
  
  public static JSONObject getJSONObject(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getJSONObject(paramString);
    }
    return null;
  }
  
  public static String getJSONValue(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getString(paramString);
    }
    return "";
  }
  
  public static String getLocalIpAddress(TAdButton.TAdButtonCallbackListener paramTAdButtonCallbackListener)
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      if (paramTAdButtonCallbackListener != null) {
        paramTAdButtonCallbackListener.onError(localSocketException);
      }
      localSocketException.printStackTrace();
      if (paramTAdButtonCallbackListener != null) {
        paramTAdButtonCallbackListener.onError(new IllegalArgumentException("Couldn't obtain the local ip address"));
      }
    }
    return "";
  }
  
  public static Location getLocation()
  {
    return sCurrentLocation;
  }
  
  public static ArrayList<PInfo> getPackages(Context paramContext)
  {
    int i = 0;
    paramContext = getInstalledApps(false, paramContext);
    int j = paramContext.size();
    while (i < j)
    {
      ((PInfo)paramContext.get(i)).prettyPrint();
      i += 1;
    }
    return paramContext;
  }
  
  public static String getToken(Context paramContext, TAdButton.TAdButtonCallbackListener paramTAdButtonCallbackListener)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("madvertise_site_token");
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    if (paramContext == null)
    {
      logMessage(null, 3, "Could not fetch \"madvertise_site_token\" from AndroidManifest.xml");
      if (paramTAdButtonCallbackListener != null) {
        paramTAdButtonCallbackListener.onError(new IllegalArgumentException("Could not fetch \"madvertise_site_token\" from AndroidManifest.xml"));
      }
    }
    return paramContext;
  }
  
  public static String getUA()
  {
    if (sUA != null) {
      return sUA;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = Build.VERSION.RELEASE;
    if (((String)localObject).length() > 0) {
      localStringBuffer.append((String)localObject);
    } else {
      localStringBuffer.append("1.0");
    }
    localStringBuffer.append("; ");
    localObject = Locale.getDefault();
    String str = ((Locale)localObject).getLanguage();
    if (str != null)
    {
      localStringBuffer.append(str.toLowerCase());
      localObject = ((Locale)localObject).getCountry();
      if (localObject != null)
      {
        localStringBuffer.append("-");
        localStringBuffer.append(((String)localObject).toLowerCase());
      }
    }
    else
    {
      localStringBuffer.append("de");
    }
    localObject = Build.MODEL;
    if (((String)localObject).length() > 0)
    {
      localStringBuffer.append("; ");
      localStringBuffer.append((String)localObject);
    }
    localObject = Build.ID;
    if (((String)localObject).length() > 0)
    {
      localStringBuffer.append(" Build/");
      localStringBuffer.append((String)localObject);
    }
    sUA = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/525.10+ (KHTML, like Gecko) Version/3.0.4 Mobile Safari/523.12.2", new Object[] { localStringBuffer });
    return sUA;
  }
  
  public static boolean isConnectionAvailable()
  {
    try
    {
      AsyncTask local1NetworkCheckTask = new AsyncTask()
      {
        protected Boolean doInBackground(Void... paramAnonymousVarArgs)
        {
          try
          {
            paramAnonymousVarArgs = (HttpURLConnection)new URL("http://fotorus.fotoable.com").openConnection();
            paramAnonymousVarArgs.setConnectTimeout(999);
            paramAnonymousVarArgs.setRequestMethod("GET");
            paramAnonymousVarArgs.setDoInput(true);
            paramAnonymousVarArgs.connect();
            if (paramAnonymousVarArgs.getResponseCode() == 200) {
              return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
          }
          catch (Exception paramAnonymousVarArgs)
          {
            for (;;) {}
          }
          return Boolean.valueOf(false);
        }
      };
      local1NetworkCheckTask.execute(new Void[0]);
      boolean bool = ((Boolean)local1NetworkCheckTask.get()).booleanValue();
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static void logMessage(String paramString1, int paramInt, String paramString2)
  {
    String str = paramString1;
    if (paramString1 == null) {
      str = "MAD_LOG";
    }
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    paramString2 = Thread.currentThread().getStackTrace()[3].getClassName();
    paramString2 = paramString2.substring(paramString2.lastIndexOf(".") + 1);
    int i = Thread.currentThread().getStackTrace()[3].getLineNumber();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("(");
    localStringBuilder.append(paramString2);
    localStringBuilder.append(":");
    localStringBuilder.append(i);
    localStringBuilder.append(") : ");
    localStringBuilder.append(paramString1);
    Log.println(paramInt, str, localStringBuilder.toString());
  }
  
  public static String printRequestParameters(List<NameValuePair> paramList)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)paramList.next();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(localNameValuePair.getName());
      localStringBuilder2.append("=");
      localStringBuilder2.append(localNameValuePair.getValue());
      localStringBuilder2.append("\n");
      localStringBuilder1.append(localStringBuilder2.toString());
    }
    return localStringBuilder1.toString();
  }
  
  public static void refreshCoordinates(Context paramContext)
  {
    logMessage(null, 3, "Trying to refresh location");
    if (paramContext == null)
    {
      logMessage(null, 3, "Context not set - quit location refresh");
      return;
    }
    if (sLocationUpdateTimestamp + 900000L > System.currentTimeMillis())
    {
      logMessage(null, 3, "It's not time yet for refreshing the location");
      return;
    }
    for (;;)
    {
      try
      {
        if (sLocationUpdateTimestamp + 900000L > System.currentTimeMillis())
        {
          logMessage(null, 3, "Another thread updated the loation already");
          return;
        }
        boolean bool1 = checkPermissionGranted("android.permission.ACCESS_COARSE_LOCATION", paramContext);
        boolean bool2 = checkPermissionGranted("android.permission.ACCESS_FINE_LOCATION", paramContext);
        if ((!bool1) && (!bool2))
        {
          logMessage(null, 3, "No permissions for requesting the location");
          return;
        }
        LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
        if (localLocationManager == null)
        {
          logMessage(null, 3, "Unable to fetch a location manger");
          return;
        }
        Criteria localCriteria = new Criteria();
        localCriteria.setCostAllowed(false);
        if (bool1)
        {
          localCriteria.setAccuracy(2);
          String str1 = localLocationManager.getBestProvider(localCriteria, true);
          String str2 = str1;
          if (str1 == null)
          {
            str2 = str1;
            if (bool2)
            {
              localCriteria.setAccuracy(1);
              str2 = localLocationManager.getBestProvider(localCriteria, true);
            }
          }
          if (str2 == null)
          {
            logMessage(null, 3, "Unable to fetch a location provider");
            return;
          }
          sLocationUpdateTimestamp = System.currentTimeMillis();
          localLocationManager.requestLocationUpdates(str2, 0L, 0.0F, new LocationListener()
          {
            public void onLocationChanged(Location paramAnonymousLocation)
            {
              TadvertiseUtil.logMessage(null, 3, "Refreshing location");
              TadvertiseUtil.access$002(paramAnonymousLocation);
              TadvertiseUtil.access$102(System.currentTimeMillis());
              this.val$finalizedLocationManager.removeUpdates(this);
            }
            
            public void onProviderDisabled(String paramAnonymousString) {}
            
            public void onProviderEnabled(String paramAnonymousString) {}
            
            public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
          }, paramContext.getMainLooper());
          return;
        }
      }
      finally {}
      Object localObject2 = null;
    }
  }
  
  public static enum HashType
  {
    MD5,  SHA1;
    
    private HashType() {}
  }
  
  static class PInfo
  {
    private String appname = "";
    private Drawable icon;
    private String pname = "";
    private int versionCode = 0;
    private String versionName = "";
    
    PInfo() {}
    
    private void prettyPrint()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.appname);
      localStringBuilder.append("\t ");
      localStringBuilder.append(this.pname);
      localStringBuilder.append("\t ");
      localStringBuilder.append(this.versionName);
      localStringBuilder.append("\t ");
      localStringBuilder.append(this.versionCode);
      TadvertiseUtil.logMessage(null, 3, localStringBuilder.toString());
    }
    
    public String getappname()
    {
      return this.appname;
    }
    
    public String getpname()
    {
      return this.pname;
    }
    
    public int getversionCode()
    {
      return this.versionCode;
    }
    
    public String getversionName()
    {
      return this.versionName;
    }
  }
}
