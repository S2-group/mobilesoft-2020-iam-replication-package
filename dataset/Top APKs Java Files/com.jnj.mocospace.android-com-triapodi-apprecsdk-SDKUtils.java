package com.triapodi.apprecsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SDKUtils
{
  public static final long NUM_INVALID_VAL = -1L;
  public static final String PREF_KEY_APP_KEY = "asdk_app_key";
  public static final String PREF_KEY_DEVICE_UID = "asdk_device_uid";
  public static final String PREF_KEY_JSON_OFFERS = "asdk_json_offers";
  public static final String PREF_KEY_LAST_PHASHES = "asdk_last_phashes";
  public static final String PREF_KEY_LAST_REQUEST = "asdk_last_request";
  public static final String PREF_KEY_PHASHES = "asdk_phashes";
  public static final String PREF_KEY_SEND_PH = "asdk_send_ph";
  public static final String PREF_KEY_USER_ID = "asdk_user_id";
  private static final String PREF_USER_DEMO_ACCURACY = "asdk_accuracy";
  private static final String PREF_USER_DEMO_AGE = "asdk_age";
  private static final String PREF_USER_DEMO_ETHNICITY = "asdk_ethnicity";
  private static final String PREF_USER_DEMO_GENDER = "asdk_gender";
  public static final String SERVER_PARAM_DATA = "data";
  public static final String SERVER_PARAM_INSTALL_TIME = "it";
  public static final String SERVER_PARAM_OFFERS = "offers";
  public static final String SERVER_PARAM_PACKAGE_FLAG = "fl";
  public static final String SERVER_PARAM_PACKAGE_NAME = "pn";
  public static final String STR_EMPTY_VAL = "";
  public static final String STR_SHARED_PREF = "asdk_sp";
  private static final String TAG = "asdk";
  
  public SDKUtils() {}
  
  protected static Offer decodeOffer(JSONObject paramJSONObject)
    throws JSONException
  {
    String str1 = paramJSONObject.optString("id", "");
    String str2 = paramJSONObject.optString("name", "");
    int i = paramJSONObject.optInt("price", 0);
    String str3 = paramJSONObject.optString("description", "");
    String str4 = paramJSONObject.optString("url", "");
    return new Offer(str1, str2, i, str3, new AdImage(paramJSONObject.optString("icon", "")), str4);
  }
  
  /* Error */
  protected static JSONArray generatePkgs(Context paramContext)
  {
    // Byte code:
    //   0: new 117	org/json/JSONArray
    //   3: dup
    //   4: invokespecial 118	org/json/JSONArray:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokevirtual 124	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: astore_0
    //   13: aload_0
    //   14: iconst_0
    //   15: invokevirtual 130	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   18: invokeinterface 136 1 0
    //   23: astore_2
    //   24: aload_2
    //   25: invokeinterface 142 1 0
    //   30: ifne +5 -> 35
    //   33: aload_1
    //   34: areturn
    //   35: aload_2
    //   36: invokeinterface 146 1 0
    //   41: checkcast 148	android/content/pm/PackageInfo
    //   44: astore 4
    //   46: new 82	org/json/JSONObject
    //   49: dup
    //   50: invokespecial 149	org/json/JSONObject:<init>	()V
    //   53: astore_3
    //   54: aload 4
    //   56: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   59: astore 5
    //   61: aload_3
    //   62: ldc 60
    //   64: aload 5
    //   66: invokevirtual 156	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   69: pop
    //   70: aload_3
    //   71: ldc 57
    //   73: aload 4
    //   75: getfield 160	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   78: getfield 166	android/content/pm/ApplicationInfo:flags	I
    //   81: iconst_1
    //   82: iand
    //   83: invokevirtual 169	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   86: pop
    //   87: aload_3
    //   88: ldc 51
    //   90: aload_0
    //   91: aload 5
    //   93: invokestatic 173	com/triapodi/apprecsdk/SDKUtils:installTimeFromPackageManager	(Landroid/content/pm/PackageManager;Ljava/lang/String;)J
    //   96: invokevirtual 176	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   99: pop
    //   100: aload_1
    //   101: aload_3
    //   102: invokevirtual 179	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   105: pop
    //   106: goto -82 -> 24
    //   109: astore_3
    //   110: goto -86 -> 24
    //   113: astore 4
    //   115: goto -15 -> 100
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	118	0	paramContext	Context
    //   7	94	1	localJSONArray	JSONArray
    //   23	13	2	localIterator	java.util.Iterator
    //   53	49	3	localJSONObject	JSONObject
    //   109	1	3	localException1	Exception
    //   44	30	4	localPackageInfo	PackageInfo
    //   113	1	4	localException2	Exception
    //   59	33	5	str	String
    // Exception table:
    //   from	to	target	type
    //   46	54	109	java/lang/Exception
    //   100	106	109	java/lang/Exception
    //   54	100	113	java/lang/Exception
  }
  
  protected static String getAppKeyFromSP(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getSharedPreferences("asdk_sp", 0).getString("asdk_app_key", null);
  }
  
  protected static String getDeviceId(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("asdk_sp", 0);
    Object localObject = localSharedPreferences.getString("asdk_device_uid", null);
    paramContext = (Context)localObject;
    if (localObject == null)
    {
      paramContext = new SecureRandom();
      long l1 = paramContext.nextLong();
      long l2 = paramContext.nextLong();
      paramContext = Long.toHexString(l1) + Long.toHexString(l2);
      localObject = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject).putString("asdk_device_uid", paramContext);
      ((SharedPreferences.Editor)localObject).commit();
    }
    return paramContext;
  }
  
  protected static boolean getIsSendPHashes(Context paramContext)
  {
    return paramContext.getSharedPreferences("asdk_sp", 0).getBoolean("asdk_send_ph", true);
  }
  
  protected static long getLastOffersRequestTimestamp(Context paramContext)
  {
    if (paramContext == null) {
      return -1L;
    }
    return paramContext.getSharedPreferences("asdk_sp", 0).getLong("asdk_last_request", -1L);
  }
  
  protected static long getLastPHashesTimestamp(Context paramContext)
  {
    if (paramContext == null) {
      return -1L;
    }
    return paramContext.getSharedPreferences("asdk_sp", 0).getLong("asdk_last_phashes", -1L);
  }
  
  protected static String getOffersJsonFromSP(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getSharedPreferences("asdk_sp", 0).getString("asdk_json_offers", null);
  }
  
  protected static String getPHashes(Context paramContext)
  {
    return paramContext.getSharedPreferences("asdk_sp", 0).getString("asdk_phashes", null);
  }
  
  protected static UserDemographics getUserDemographicsFromSP(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("asdk_sp", 0);
    String str1 = paramContext.getString("asdk_gender", "");
    String str2 = paramContext.getString("asdk_age", "");
    String str3 = paramContext.getString("asdk_ethnicity", "");
    float f = paramContext.getFloat("asdk_accuracy", -1.0F);
    if ((str1 == "") && (str2 == "") && (str3 == "") && (f == -1.0F)) {
      return null;
    }
    return new UserDemographics(str1, str2, str3, f);
  }
  
  protected static String getUserIdFromSP(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getSharedPreferences("asdk_sp", 0).getString("asdk_user_id", null);
  }
  
  protected static long installTimeFromPackageManager(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 0);
      long l = PackageInfo.class.getField("firstInstallTime").getLong(paramPackageManager);
      return l;
    }
    catch (Exception paramPackageManager) {}
    return -1L;
  }
  
  protected static void logAllSharedPreferences(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("asdk_sp", 0);
    paramContext.getString("asdk_device_uid", null);
    paramContext.getBoolean("asdk_send_ph", true);
    String str = paramContext.getString("asdk_phashes", "");
    str.substring(0, Math.min(20, str.length()));
    str = paramContext.getString("asdk_json_offers", "");
    str.substring(0, Math.min(20, str.length()));
    paramContext.getString("asdk_user_id", "");
    paramContext.getString("asdk_app_key", "");
    paramContext.getLong("asdk_last_request", -1L);
    paramContext.getString("asdk_gender", "");
    paramContext.getString("asdk_age", "");
    paramContext.getString("asdk_ethnicity", "");
    paramContext.getFloat("asdk_accuracy", -1.0F);
  }
  
  protected static String milsToFormatStrDate(long paramLong)
  {
    Date localDate = new Date(paramLong);
    return new SimpleDateFormat("hh:mm 'o''clock on' MMMM dd yyyy").format(localDate);
  }
  
  protected static UserDemographics parseUserDemographics(JSONObject paramJSONObject)
    throws JSONException
  {
    paramJSONObject = paramJSONObject.optJSONObject("data");
    if (paramJSONObject == null) {
      return null;
    }
    return new UserDemographics(paramJSONObject.optString("gender", ""), paramJSONObject.optString("age", ""), paramJSONObject.optString("ethnicity", ""), (float)paramJSONObject.optDouble("accuracy", -1.0D));
  }
  
  protected static int randomRange(int paramInt1, int paramInt2)
  {
    return (int)(Math.random() * paramInt2) + paramInt1;
  }
  
  protected static void saveAppKeyInSP(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
      paramContext.putString("asdk_app_key", paramString);
      paramContext.commit();
    }
  }
  
  protected static void saveLastOffersRequestTimestampInSP(Context paramContext, long paramLong)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
      paramContext.putLong("asdk_last_request", paramLong);
      paramContext.commit();
    }
  }
  
  protected static void saveLastPHashesTimestamp(Context paramContext, long paramLong)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
      paramContext.putLong("asdk_last_phashes", paramLong);
      paramContext.commit();
    }
  }
  
  protected static void saveUserIDInSP(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
      paramContext.putString("asdk_user_id", paramString);
      paramContext.commit();
    }
  }
  
  protected static void setIsSendPHashes(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
    paramContext.putBoolean("asdk_send_ph", paramBoolean);
    paramContext.commit();
  }
  
  protected static void setOffersJSON(Context paramContext, JSONObject paramJSONObject)
    throws JSONException
  {
    paramJSONObject = paramJSONObject.getJSONObject("data").getJSONArray("offers");
    paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
    paramContext.putString("asdk_json_offers", paramJSONObject.toString());
    paramContext.commit();
  }
  
  protected static void setPHashes(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
    paramContext.putString("asdk_phashes", paramString);
    paramContext.commit();
  }
  
  protected static void setUserDemographicsToSP(Context paramContext, UserDemographics paramUserDemographics)
  {
    if (paramContext == null) {
      return;
    }
    paramContext = paramContext.getSharedPreferences("asdk_sp", 0).edit();
    paramContext.putString("asdk_gender", paramUserDemographics.getGender());
    paramContext.putString("asdk_age", paramUserDemographics.getAge());
    paramContext.putString("asdk_ethnicity", paramUserDemographics.getEthnicity());
    paramContext.putFloat("asdk_accuracy", paramUserDemographics.getAccuracy());
    paramContext.commit();
  }
}
