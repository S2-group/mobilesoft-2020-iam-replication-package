package de.madvertise.android.sdk;

import android.content.Context;
import android.content.Intent;
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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

public class MadvertiseUtil
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
  static final String MAD_SERVER = "http://ad.madvertise.de";
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
  
  public MadvertiseUtil() {}
  
  private static void checkEmptyJson(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    if ((paramJSONObject == null) || (paramString.equals(""))) {
      throw new JSONException("Empty JSON or key");
    }
  }
  
  public static boolean checkForBrowserDeclaration(Context paramContext)
  {
    boolean bool = false;
    if (paramContext.getPackageManager().queryIntentActivities(new Intent(paramContext, MadvertiseActivity.class), 0).size() > 0) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean checkPermissionGranted(String paramString, Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  /* Error */
  public static String convertStreamToString(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 187	java/io/BufferedReader
    //   3: dup
    //   4: new 189	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 192	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: invokespecial 195	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_2
    //   16: new 197	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 198	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_2
    //   25: invokevirtual 202	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnonnull +12 -> 42
    //   33: aload_0
    //   34: invokevirtual 207	java/io/InputStream:close	()V
    //   37: aload_1
    //   38: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: areturn
    //   42: aload_1
    //   43: new 197	java/lang/StringBuilder
    //   46: dup
    //   47: aload_3
    //   48: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   51: invokespecial 215	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   54: ldc -39
    //   56: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: goto -42 -> 24
    //   69: astore_2
    //   70: aload_2
    //   71: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   74: aload_0
    //   75: invokevirtual 207	java/io/InputStream:close	()V
    //   78: goto -41 -> 37
    //   81: astore_0
    //   82: aload_0
    //   83: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   86: goto -49 -> 37
    //   89: astore_1
    //   90: aload_0
    //   91: invokevirtual 207	java/io/InputStream:close	()V
    //   94: aload_1
    //   95: athrow
    //   96: astore_0
    //   97: aload_0
    //   98: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   101: goto -7 -> 94
    //   104: astore_0
    //   105: aload_0
    //   106: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   109: goto -72 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramInputStream	java.io.InputStream
    //   23	20	1	localStringBuilder	StringBuilder
    //   89	6	1	localObject	Object
    //   15	10	2	localBufferedReader	java.io.BufferedReader
    //   69	2	2	localIOException	java.io.IOException
    //   28	20	3	str	String
    // Exception table:
    //   from	to	target	type
    //   24	29	69	java/io/IOException
    //   42	66	69	java/io/IOException
    //   74	78	81	java/io/IOException
    //   24	29	89	finally
    //   42	66	89	finally
    //   70	74	89	finally
    //   90	94	96	java/io/IOException
    //   33	37	104	java/io/IOException
  }
  
  public static String getAllHeadersAsString(Header[] paramArrayOfHeader)
  {
    String str = "";
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfHeader.length) {
        return str;
      }
      str = str + "<< " + paramArrayOfHeader[i].getName() + " : " + paramArrayOfHeader[i].getValue() + " >>";
      i += 1;
    }
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
  
  public static String getHash(String paramString, HashType paramHashType)
  {
    try
    {
      paramHashType = MessageDigest.getInstance(paramHashType.toString());
      paramHashType.update(paramString.getBytes());
      arrayOfByte = paramHashType.digest();
      localStringBuffer = new StringBuffer();
      i = 0;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;)
      {
        byte[] arrayOfByte;
        StringBuffer localStringBuffer;
        int i;
        paramString.printStackTrace();
        logMessage(null, 3, "Could not create hash value");
        paramString = "";
        continue;
        paramHashType = Integer.toHexString(arrayOfByte[i] & 0xFF);
        paramString = paramHashType;
        if (paramHashType.length() < 2) {
          paramString = "0" + paramHashType;
        }
        localStringBuffer.append(paramString);
        i += 1;
      }
    }
    finally {}
    if (i >= arrayOfByte.length)
    {
      paramString = localStringBuffer.toString();
      return paramString;
    }
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
    String str = null;
    if (checkPermissionGranted("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
    if (str == null) {
      return "";
    }
    return getHash(str, paramHashType);
  }
  
  private static ArrayList<PInfo> getInstalledApps(boolean paramBoolean, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i >= localList.size()) {
      return localArrayList;
    }
    PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
    if ((!paramBoolean) && (localPackageInfo.versionName == null)) {}
    for (;;)
    {
      i += 1;
      break;
      PInfo localPInfo = new PInfo();
      localPInfo.appname = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localPInfo.pname = localPackageInfo.packageName;
      localPInfo.versionName = localPackageInfo.versionName;
      localPInfo.versionCode = localPackageInfo.versionCode;
      localPInfo.icon = localPackageInfo.applicationInfo.loadIcon(paramContext.getPackageManager());
      localArrayList.add(localPInfo);
    }
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
  
  public static String getLocalIpAddress(MadvertiseView.MadvertiseViewCallbackListener paramMadvertiseViewCallbackListener)
  {
    try
    {
      localObject = NetworkInterface.getNetworkInterfaces();
      boolean bool = ((Enumeration)localObject).hasMoreElements();
      if (bool) {
        break label38;
      }
    }
    catch (SocketException localSocketException)
    {
      for (;;)
      {
        Object localObject;
        label38:
        Enumeration localEnumeration;
        InetAddress localInetAddress;
        if (paramMadvertiseViewCallbackListener != null) {
          paramMadvertiseViewCallbackListener.onError(localSocketException);
        }
        localSocketException.printStackTrace();
      }
    }
    if (paramMadvertiseViewCallbackListener != null) {
      paramMadvertiseViewCallbackListener.onError(new IllegalArgumentException("Couldn't obtain the local ip address"));
    }
    return "";
    localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
    do
    {
      if (!localEnumeration.hasMoreElements()) {
        break;
      }
      localInetAddress = (InetAddress)localEnumeration.nextElement();
    } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
    localObject = localInetAddress.getHostAddress().toString();
    return localObject;
  }
  
  public static Location getLocation()
  {
    return sCurrentLocation;
  }
  
  public static ArrayList<PInfo> getPackages(Context paramContext)
  {
    paramContext = getInstalledApps(false, paramContext);
    int j = paramContext.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return paramContext;
      }
      ((PInfo)paramContext.get(i)).prettyPrint();
      i += 1;
    }
  }
  
  public static String getToken(Context paramContext, MadvertiseView.MadvertiseViewCallbackListener paramMadvertiseViewCallbackListener)
  {
    Object localObject = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("madvertise_site_token");
      if (paramContext == null)
      {
        logMessage(null, 3, "Could not fetch \"madvertise_site_token\" from AndroidManifest.xml");
        if (paramMadvertiseViewCallbackListener != null) {
          paramMadvertiseViewCallbackListener.onError(new IllegalArgumentException("Could not fetch \"madvertise_site_token\" from AndroidManifest.xml"));
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
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
    for (;;)
    {
      if (!paramList.hasNext()) {
        return localStringBuilder.toString();
      }
      NameValuePair localNameValuePair = (NameValuePair)paramList.next();
      localStringBuilder.append(localNameValuePair.getName() + "=" + localNameValuePair.getValue() + "\n");
    }
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
    String str1 = null;
    Criteria localCriteria = new Criteria();
    localCriteria.setCostAllowed(false);
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
    localLocationManager.requestLocationUpdates(str2, 0L, 0.0F, new LocationListener()
    {
      public void onLocationChanged(Location paramAnonymousLocation)
      {
        MadvertiseUtil.logMessage(null, 3, "Refreshing location");
        MadvertiseUtil.sCurrentLocation = paramAnonymousLocation;
        MadvertiseUtil.sLocationUpdateTimestamp = System.currentTimeMillis();
        MadvertiseUtil.this.removeUpdates(this);
      }
      
      public void onProviderDisabled(String paramAnonymousString) {}
      
      public void onProviderEnabled(String paramAnonymousString) {}
      
      public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
    }, paramContext.getMainLooper());
  }
  
  public static enum HashType
  {
    MD5,  SHA1;
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
      MadvertiseUtil.logMessage(null, 3, this.appname + "\t " + this.pname + "\t " + this.versionName + "\t " + this.versionCode);
    }
  }
}
