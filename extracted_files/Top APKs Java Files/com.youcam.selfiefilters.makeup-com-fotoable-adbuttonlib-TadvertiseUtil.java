package com.fotoable.adbuttonlib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
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
  private static Location sCurrentLocation = null;
  private static long sLocationUpdateTimestamp = 0L;
  private static String sUA;
  
  public TadvertiseUtil() {}
  
  private static void checkEmptyJson(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramString.equals(""))) {
      throw new JSONException("Empty JSON or key");
    }
  }
  
  public static boolean checkPermissionGranted(String paramString, Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static String convertStreamToString(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        localStringBuilder.append(str + "\n");
      }
      try
      {
        paramInputStream.close();
        throw localObject;
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          paramInputStream.printStackTrace();
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException = localIOException;
      localIOException.printStackTrace();
      try
      {
        paramInputStream.close();
        for (;;)
        {
          return localStringBuilder.toString();
          try
          {
            paramInputStream.close();
          }
          catch (IOException paramInputStream)
          {
            paramInputStream.printStackTrace();
          }
        }
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          paramInputStream.printStackTrace();
        }
      }
    }
    finally {}
  }
  
  public static String getAllHeadersAsString(Header[] paramArrayOfHeader)
  {
    String str = "";
    int i = 0;
    while (i < paramArrayOfHeader.length)
    {
      str = str + "<< " + paramArrayOfHeader[i].getName() + " : " + paramArrayOfHeader[i].getValue() + " >>";
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
  public static String getHash(String paramString, TadvertiseUtil.HashType paramHashType)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_1
    //   4: invokevirtual 243	com/fotoable/adbuttonlib/TadvertiseUtil$HashType:toString	()Ljava/lang/String;
    //   7: invokestatic 249	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   10: astore_1
    //   11: aload_1
    //   12: aload_0
    //   13: invokevirtual 253	java/lang/String:getBytes	()[B
    //   16: invokevirtual 257	java/security/MessageDigest:update	([B)V
    //   19: aload_1
    //   20: invokevirtual 260	java/security/MessageDigest:digest	()[B
    //   23: astore_3
    //   24: new 262	java/lang/StringBuffer
    //   27: dup
    //   28: invokespecial 263	java/lang/StringBuffer:<init>	()V
    //   31: astore 4
    //   33: iconst_0
    //   34: istore_2
    //   35: iload_2
    //   36: aload_3
    //   37: arraylength
    //   38: if_icmpge +80 -> 118
    //   41: aload_3
    //   42: iload_2
    //   43: baload
    //   44: sipush 255
    //   47: iand
    //   48: invokestatic 269	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   51: astore_1
    //   52: aload_1
    //   53: astore_0
    //   54: aload_1
    //   55: invokevirtual 273	java/lang/String:length	()I
    //   58: iconst_2
    //   59: if_icmpge +24 -> 83
    //   62: new 163	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   69: ldc_w 275
    //   72: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload_1
    //   76: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore_0
    //   83: aload 4
    //   85: aload_0
    //   86: invokevirtual 278	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   89: pop
    //   90: iload_2
    //   91: iconst_1
    //   92: iadd
    //   93: istore_2
    //   94: goto -59 -> 35
    //   97: astore_0
    //   98: aload_0
    //   99: invokevirtual 279	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   102: aconst_null
    //   103: iconst_3
    //   104: ldc_w 281
    //   107: invokestatic 285	com/fotoable/adbuttonlib/TadvertiseUtil:logMessage	(Ljava/lang/String;ILjava/lang/String;)V
    //   110: ldc 126
    //   112: astore_0
    //   113: ldc 2
    //   115: monitorexit
    //   116: aload_0
    //   117: areturn
    //   118: aload 4
    //   120: invokevirtual 286	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   123: astore_0
    //   124: goto -11 -> 113
    //   127: astore_0
    //   128: ldc 2
    //   130: monitorexit
    //   131: aload_0
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	paramString	String
    //   0	133	1	paramHashType	TadvertiseUtil.HashType
    //   34	60	2	i	int
    //   23	19	3	arrayOfByte	byte[]
    //   31	88	4	localStringBuffer	StringBuffer
    // Exception table:
    //   from	to	target	type
    //   3	11	97	java/security/NoSuchAlgorithmException
    //   3	11	127	finally
    //   11	33	127	finally
    //   35	52	127	finally
    //   54	83	127	finally
    //   83	90	127	finally
    //   98	110	127	finally
    //   118	124	127	finally
  }
  
  public static String getHashedAndroidID(Context paramContext, TadvertiseUtil.HashType paramHashType)
  {
    paramContext = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "android_id");
    if (paramContext == null) {
      return "";
    }
    return getHash(paramContext, paramHashType);
  }
  
  public static String getHashedMacAddress(Context paramContext, TadvertiseUtil.HashType paramHashType)
  {
    String str = null;
    if (checkPermissionGranted("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
    if (str == null) {
      return "";
    }
    return getHash(str, paramHashType);
  }
  
  private static ArrayList<TadvertiseUtil.PInfo> getInstalledApps(boolean paramBoolean, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((!paramBoolean) && (localPackageInfo.versionName == null)) {}
      for (;;)
      {
        i += 1;
        break;
        TadvertiseUtil.PInfo localPInfo = new TadvertiseUtil.PInfo();
        TadvertiseUtil.PInfo.access$302(localPInfo, localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString());
        TadvertiseUtil.PInfo.access$402(localPInfo, localPackageInfo.packageName);
        TadvertiseUtil.PInfo.access$502(localPInfo, localPackageInfo.versionName);
        TadvertiseUtil.PInfo.access$602(localPInfo, localPackageInfo.versionCode);
        TadvertiseUtil.PInfo.access$702(localPInfo, localPackageInfo.applicationInfo.loadIcon(paramContext.getPackageManager()));
        localArrayList.add(localPInfo);
      }
    }
    return localArrayList;
  }
  
  public static JSONArray getJSONArray(JSONObject paramJSONObject, String paramString)
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getJSONArray(paramString);
    }
    return new JSONArray();
  }
  
  public static boolean getJSONBoolean(JSONObject paramJSONObject, String paramString)
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getBoolean(paramString);
    }
    return false;
  }
  
  public static int getJSONInt(JSONObject paramJSONObject, String paramString)
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getInt(paramString);
    }
    return 0;
  }
  
  public static JSONObject getJSONObject(JSONObject paramJSONObject, String paramString)
  {
    checkEmptyJson(paramJSONObject, paramString);
    if (paramJSONObject.has(paramString)) {
      return paramJSONObject.getJSONObject(paramString);
    }
    return null;
  }
  
  public static String getJSONValue(JSONObject paramJSONObject, String paramString)
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
  
  public static ArrayList<TadvertiseUtil.PInfo> getPackages(Context paramContext)
  {
    paramContext = getInstalledApps(false, paramContext);
    int j = paramContext.size();
    int i = 0;
    while (i < j)
    {
      TadvertiseUtil.PInfo.access$200((TadvertiseUtil.PInfo)paramContext.get(i));
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
      if (paramContext == null)
      {
        logMessage(null, 3, "Could not fetch \"madvertise_site_token\" from AndroidManifest.xml");
        if (paramTAdButtonCallbackListener != null) {
          paramTAdButtonCallbackListener.onError(new IllegalArgumentException("Could not fetch \"madvertise_site_token\" from AndroidManifest.xml"));
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public static String getUA()
  {
    if (sUA != null) {
      return sUA;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = Build.VERSION.RELEASE;
    if (((String)localObject).length() > 0)
    {
      localStringBuffer.append((String)localObject);
      localStringBuffer.append("; ");
      localObject = Locale.getDefault();
      String str = ((Locale)localObject).getLanguage();
      if (str == null) {
        break label173;
      }
      localStringBuffer.append(str.toLowerCase());
      localObject = ((Locale)localObject).getCountry();
      if (localObject != null)
      {
        localStringBuffer.append("-");
        localStringBuffer.append(((String)localObject).toLowerCase());
      }
    }
    for (;;)
    {
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
      localStringBuffer.append("1.0");
      break;
      label173:
      localStringBuffer.append("de");
    }
  }
  
  public static boolean isConnectionAvailable()
  {
    try
    {
      TadvertiseUtil.1NetworkCheckTask local1NetworkCheckTask = new TadvertiseUtil.1NetworkCheckTask();
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
    Log.println(paramInt, str, "(" + paramString2 + ":" + i + ") : " + paramString1);
  }
  
  public static String printRequestParameters(List<NameValuePair> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)paramList.next();
      localStringBuilder.append(localNameValuePair.getName() + "=" + localNameValuePair.getValue() + "\n");
    }
    return localStringBuilder.toString();
  }
  
  public static void refreshCoordinates(Context paramContext)
  {
    Object localObject1 = null;
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
    try
    {
      if (sLocationUpdateTimestamp + 900000L > System.currentTimeMillis())
      {
        logMessage(null, 3, "Another thread updated the loation already");
        return;
      }
    }
    finally {}
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
    String str1;
    if (bool1)
    {
      localCriteria.setAccuracy(2);
      str1 = localLocationManager.getBestProvider(localCriteria, true);
    }
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
    localLocationManager.requestLocationUpdates(str2, 0L, 0.0F, new TadvertiseUtil.1(localLocationManager), paramContext.getMainLooper());
  }
}
