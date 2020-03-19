package net.easyconn.carman.common.stats;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.a.a.b;
import com.tencent.bugly.crashreport.CrashReport;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.easyconn.carman.a;
import net.easyconn.carman.common.e.t;
import net.easyconn.carman.common.f;
import net.easyconn.carman.common.httpapi.SystemProp;
import net.easyconn.carman.common.httpapi.api.RecordeGather;
import net.easyconn.carman.common.httpapi.request.RecordeGatherRequest;
import net.easyconn.carman.common.httpapi.request.RecordeGatherRequest.Action;
import net.easyconn.carman.common.stats.field.NewMotion;
import net.easyconn.carman.utils.L;
import net.easyconn.carman.utils.NetUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatsUtils
{
  public static final String INVALID = "INVALID";
  public static final int NETWORKTYPE_2G = 2;
  public static final int NETWORKTYPE_3G = 3;
  public static final int NETWORKTYPE_4G = 5;
  public static final int NETWORKTYPE_INVALID = 0;
  public static final int NETWORKTYPE_WAP = 1;
  public static final int NETWORKTYPE_WIFI = 4;
  public static final String TAG = "StatsUtils";
  public static final String TYPE2G = "2G";
  public static final String TYPE3G = "3G";
  public static final String TYPE4G = "4G";
  public static final String WAP = "WAP";
  public static final String WIFI = "WIFI";
  private static final String[] actionColumns = { "_id", "name", "data", "time", "uploadedTime", "uploaded" };
  private static volatile String currPage;
  private static final String[] errorColumns = { "_id", "data", "time", "uploadedTime", "uploaded" };
  private static double latitude;
  private static double longitude;
  public static String mChannel;
  public static DateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static int mNetWorkType;
  private static PackageManager mPackageManager;
  private static Map<String, Long> map = new HashMap();
  private static DBManager mgr;
  private static final String[] servActColumns = { "_id", "name", "value", "lastExeTime" };
  private static final String[] thirdAppColumns = { "_id", "name", "packageName", "versionName", "versionCode", "position", "uploadedTime", "uploaded" };
  
  static
  {
    mChannel = null;
    longitude = 0.0D;
    latitude = 0.0D;
    mgr = null;
  }
  
  private StatsUtils() {}
  
  public static void addApp(Context paramContext, String paramString)
  {
    paramContext.getApplicationContext();
    Object localObject1 = null;
    String str3 = null;
    Object localObject2 = null;
    String str1 = str3;
    try
    {
      String str2 = getPackageManager(paramContext).getPackageInfo(paramString, 1).applicationInfo.loadLabel(getPackageManager(paramContext)).toString();
      localObject1 = str2;
      str1 = str3;
      str3 = getPackageManager(paramContext).getPackageInfo(paramString, 1).versionName;
      localObject1 = str2;
      str1 = str3;
      String str4 = "" + getPackageManager(paramContext).getPackageInfo(paramString, 1).versionCode;
      localObject2 = str4;
      str1 = str3;
      localObject1 = str2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
    save3rdAppAction(paramContext, "add", localObject1, paramString, str1, localObject2, 0L, null);
    recordsUpload(paramContext, 3);
  }
  
  public static void appListUpload(Context paramContext)
  {
    a.a().execute(new Runnable()
    {
      public void run()
      {
        StatsUtils.appListUploadRun(this.val$context);
      }
    });
  }
  
  private static void appListUploadRun(Context paramContext)
  {
    Object localObject1 = null;
    try
    {
      List localList = getPackageManager(paramContext).getInstalledPackages(0);
      localObject1 = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        CrashReport.postCatchedException(localException);
      }
    }
    finally {}
    if (localObject1 == null) {
      return;
    }
    Object localObject2 = new JSONArray();
    JSONObject localJSONObject = new JSONObject();
    int i = 0;
    for (;;)
    {
      int j;
      if (i < ((List)localObject1).size())
      {
        Object localObject3 = (PackageInfo)((List)localObject1).get(i);
        String str1 = ((PackageInfo)localObject3).applicationInfo.loadLabel(getPackageManager(paramContext)).toString();
        String str2 = ((PackageInfo)localObject3).packageName;
        String str3 = ((PackageInfo)localObject3).versionName;
        j = ((PackageInfo)localObject3).versionCode;
        if ((!str2.contains("com.android")) && (!str2.contains("com.google")) && (!str2.contains("com.example")) && (!str1.equals("OpenWnn")) && (!str1.equals("Pico TTS")) && (!str1.equals("Android System")) && (!str2.equals(paramContext.getPackageName())))
        {
          localObject3 = new JSONObject();
          try
          {
            ((JSONObject)localObject3).put("name", str1);
            ((JSONObject)localObject3).put("package_name", str2);
            ((JSONObject)localObject3).put("version_name", str3);
            ((JSONObject)localObject3).put("version_code", j);
            ((JSONArray)localObject2).put(localObject3);
            localJSONObject.put("apps", localObject2);
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      }
      else
      {
        i = 0;
        mgr = getDBManager(paramContext);
        if (mgr.queryTheCursorByCondtions("server_action", servActColumns, "type=? and name = ? and  lastExeTime is null", new String[] { "freq", "applist" }).getCount() != 0) {
          i = 1;
        }
        for (;;)
        {
          if (i == 0) {
            break label581;
          }
          if (getMsgCode(HttpUtils.doPostRequest(paramContext, "http://api.carbit.com.cn/v3.0/app-list.json", createJson(new String[] { "context" }, new Object[] { localJSONObject }))) != 0) {
            break;
          }
          paramContext = new ContentValues();
          paramContext.put("lastExeTime", mFormat.format(new Date()));
          mgr.update("server_action", paramContext, "type=? and name=? ", new String[] { "freq", "applist" });
          break;
          localObject2 = mgr.queryConfigs("server_action", servActColumns, "type=? and name=?", new String[] { "freq", "applist" });
          localObject1 = "0";
          localObject2 = ((List)localObject2).iterator();
          while (((Iterator)localObject2).hasNext()) {
            localObject1 = ((ConfigProp)((Iterator)localObject2).next()).getValue();
          }
          j = mgr.queryTheCursorByCondtions("server_action", servActColumns, "type=? and name = ? and datetime(lastExeTime) < datetime('now',?) ", new String[] { "freq", "applist", "-" + (String)localObject1 + " day" }).getCount();
          if (j != 0) {
            i = 1;
          }
        }
        label581:
        break;
      }
      i += 1;
    }
  }
  
  private static void clientUseFinish(Context paramContext)
  {
    long l = Thread.currentThread().getId();
    map.put("client_use_duration_timeEnd" + l, Long.valueOf(System.currentTimeMillis()));
    l = getDuration("client_use_duration");
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("duration", l);
      saveAction(paramContext, "client_use_duration", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  private static void clientUseStart()
  {
    long l = Thread.currentThread().getId();
    map.put("client_use_duration_timeStart" + l, Long.valueOf(System.currentTimeMillis()));
  }
  
  private static JSONArray convertActionList2JSONArray(List<Action> paramList)
  {
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    for (;;)
    {
      if (paramList.hasNext())
      {
        Action localAction = (Action)paramList.next();
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("name", localAction.name);
          localJSONObject.put("data", localAction.data);
          localJSONObject.put("time", localAction.time);
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
        }
      }
    }
    return localJSONArray;
  }
  
  private static JSONArray convertErrorList2JSONArray(List<Error> paramList)
  {
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Error localError = (Error)paramList.next();
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("data", localError.getData());
        localJSONObject.put("time", localError.getTime());
        localJSONArray.put(localJSONObject);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localJSONArray;
  }
  
  private static JSONArray convertThirdAppList2JSONArray(List<ThirdApp> paramList)
  {
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    for (;;)
    {
      if (paramList.hasNext())
      {
        ThirdApp localThirdApp = (ThirdApp)paramList.next();
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("name", localThirdApp.getName());
          localJSONObject.put("package_name", localThirdApp.getPackageName());
          localJSONObject.put("version_name", localThirdApp.getVersionName());
          localJSONObject.put("version_code", localThirdApp.getVersionCode());
          localJSONObject.put("position", localThirdApp.getPosition());
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
        }
      }
    }
    return localJSONArray;
  }
  
  public static JSONObject createJson(String[] paramArrayOfString, Object[] paramArrayOfObject)
  {
    localJSONObject = new JSONObject();
    int i = 0;
    try
    {
      while (i < paramArrayOfString.length)
      {
        localJSONObject.put(paramArrayOfString[i], paramArrayOfObject[i]);
        i += 1;
      }
      return localJSONObject;
    }
    catch (JSONException paramArrayOfString)
    {
      paramArrayOfString.printStackTrace();
    }
  }
  
  private static void deleteLocalLog(Context paramContext, final int paramInt)
  {
    a.a().execute(new Runnable()
    {
      public void run()
      {
        StatsUtils.getDBManager(this.val$context);
        StatsUtils.mgr.deleteActions("action", "uploaded=1 and datetime(uploadedTime) < datetime('now',?) ", new String[] { "-" + paramInt + " hour" });
        StatsUtils.mgr.deleteErrors("error", "uploaded=1 and datetime(uploadedTime) < datetime('now',?) ", new String[] { "-" + paramInt + " hour" });
        StatsUtils.mgr.deleteThirdApps("third_app", "uploaded=1 and datetime(uploadedTime) < datetime('now',?) ", new String[] { "-" + paramInt + " hour" });
      }
    });
  }
  
  private static void fetchConfigs(Context paramContext)
  {
    a.a().execute(new Runnable()
    {
      public void run()
      {
        StatsUtils.setConfigsRun(this.val$context);
      }
    });
  }
  
  private static String getActionName2(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString;
  }
  
  public static String getChannelName(Context paramContext)
  {
    Object localObject1;
    if (!TextUtils.isEmpty(mChannel)) {
      localObject1 = mChannel;
    }
    for (;;)
    {
      return localObject1;
      if (paramContext == null) {
        return null;
      }
      Object localObject2 = null;
      try
      {
        PackageManager localPackageManager = getPackageManager(paramContext);
        localObject1 = localObject2;
        if (localPackageManager != null)
        {
          paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
          localObject1 = localObject2;
          if (paramContext != null)
          {
            localObject1 = localObject2;
            if (paramContext.metaData != null)
            {
              localObject1 = localObject2;
              if (paramContext.metaData.containsKey("CARBIT_CHANNEL"))
              {
                paramContext = paramContext.metaData.get("CARBIT_CHANNEL").toString();
                return paramContext;
              }
            }
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        CrashReport.postCatchedException(paramContext);
      }
    }
    return null;
  }
  
  public static JSONObject getClientInfo(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject();
    String str2 = paramContext.getPackageName();
    String str3 = getChannelName(paramContext);
    Object localObject = null;
    int i = 0;
    for (;;)
    {
      try
      {
        String str1 = getPackageManager(paramContext).getPackageInfo(str2, 1).versionName;
        localObject = str1;
        int j = getPackageManager(paramContext).getPackageInfo(str2, 1).versionCode;
        localObject = str1;
        i = j;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        CrashReport.postCatchedException(paramContext);
        continue;
      }
      try
      {
        localJSONObject.put("channel", str3);
        localJSONObject.put("package_name", str2);
        localJSONObject.put("version_name", localObject);
        localJSONObject.put("version_code", i);
        return localJSONObject;
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        return localJSONObject;
      }
    }
  }
  
  private static JSONObject getConfigs(Context paramContext)
  {
    JSONObject localJSONObject1 = getDeviceInfo(paramContext);
    JSONObject localJSONObject2 = getClientInfo(paramContext);
    paramContext = HttpUtils.doPostRequest(paramContext, "http://api.carbit.com.cn/v3.0/check-for-updates.json", createJson(new String[] { "device", "client" }, new Object[] { localJSONObject1, localJSONObject2 }));
    if (!TextUtils.isEmpty(paramContext)) {
      try
      {
        paramContext = new JSONObject(paramContext);
        if (paramContext.getInt("code") == 0)
        {
          localJSONObject1 = (JSONObject)paramContext.get("context");
          paramContext = null;
          if (localJSONObject1 != null) {
            paramContext = (JSONObject)localJSONObject1.get("client_settings");
          }
          return paramContext;
        }
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        CrashReport.postCatchedException(paramContext);
      }
    }
    return null;
  }
  
  public static String getCurrPage()
  {
    if (currPage == null) {
      currPage = "";
    }
    return currPage;
  }
  
  private static DBManager getDBManager(Context paramContext)
  {
    if (mgr == null) {}
    try
    {
      if (mgr == null) {
        mgr = new DBManager(paramContext);
      }
      return mgr;
    }
    finally {}
  }
  
  public static JSONObject getDeviceInfo(Context paramContext)
  {
    try
    {
      paramContext = new JSONObject();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("latitude", latitude);
      localJSONObject.put("longitude", longitude);
      paramContext.put("resolution", FixedParam.SCREEN_WIDTH + " X " + FixedParam.SCREEN_HEIGHT);
      paramContext.put("mac", FixedParam.MAC);
      paramContext.put("imei", FixedParam.IMEI);
      paramContext.put("os", FixedParam.SDK_VERSION);
      paramContext.put("model", FixedParam.PHONE_NAME);
      paramContext.put("ppi", FixedParam.PPI);
      paramContext.put("imsi", FixedParam.IMSI);
      paramContext.put("phone_num", FixedParam.PHONENUM);
      paramContext.put("carrier", FixedParam.CARRIER);
      paramContext.put("network", FixedParam.NETWOKR);
      paramContext.put("area", "");
      paramContext.put("location", localJSONObject);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static long getDuration(String paramString)
  {
    long l1 = Thread.currentThread().getId();
    if ((map == null) || (!map.containsKey(paramString + "_timeStart" + l1)) || (!map.containsKey(paramString + "_timeEnd" + l1))) {
      return -1L;
    }
    long l2 = ((Long)map.get(paramString + "_timeStart" + l1)).longValue();
    long l3 = ((Long)map.get(paramString + "_timeEnd" + l1)).longValue();
    map.remove(paramString + "_timeStart" + l1);
    map.remove(paramString + "_timeEnd" + l1);
    return (l3 - l2) / 1000L;
  }
  
  private static JSONObject getLocInfo(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    paramContext = new JSONObject();
    try
    {
      localJSONObject.put("longitude", longitude);
      localJSONObject.put("latitude", latitude);
      paramContext.put("location", localJSONObject);
      return paramContext;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return paramContext;
  }
  
  private static int getMsgCode(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        int i = ((Integer)new JSONObject(paramString).get("code")).intValue();
        return i;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return -1;
  }
  
  private static PackageManager getPackageManager(Context paramContext)
  {
    if (paramContext != null)
    {
      if (mPackageManager == null) {
        mPackageManager = paramContext.getPackageManager();
      }
      return mPackageManager;
    }
    return null;
  }
  
  private static String getPosition(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString;
  }
  
  public static String getStackTrace(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    try
    {
      paramThrowable.printStackTrace(localPrintWriter);
      paramThrowable = localStringWriter.toString();
      return paramThrowable;
    }
    finally
    {
      localPrintWriter.close();
    }
  }
  
  public static void initFixedParam(Context paramContext)
  {
    FixedParam.IMEI = SystemProp.getImei(paramContext);
    FixedParam.MAC = SystemProp.getMac(paramContext);
    FixedParam.IMSI = SystemProp.getImsi(paramContext);
    FixedParam.PPI = SystemProp.getPPI(paramContext);
    FixedParam.SCREEN_WIDTH = String.valueOf(f.b());
    FixedParam.SCREEN_HEIGHT = String.valueOf(f.a());
    FixedParam.PHONENUM = SystemProp.getPhoneNumber(paramContext);
    FixedParam.CARRIER = SystemProp.getCarrier(paramContext);
    FixedParam.NETWOKR = SystemProp.getNetWorkType2String(paramContext);
    FixedParam.PHONE_NAME = SystemProp.getPhoneModel();
    FixedParam.SDK_VERSION = SystemProp.getAndroidSDKVersion();
  }
  
  private static boolean is4G(Context paramContext)
  {
    switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
    {
    default: 
      return false;
    }
    return true;
  }
  
  private static boolean isFastMobileNetwork(Context paramContext)
  {
    switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
    {
    case 0: 
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
    default: 
      return false;
    case 5: 
      return true;
    case 6: 
      return true;
    case 8: 
      return true;
    case 10: 
      return true;
    case 9: 
      return true;
    case 3: 
      return true;
    case 14: 
      return true;
    case 12: 
      return true;
    case 15: 
      return true;
    }
    return true;
  }
  
  public static void markActionUploadingStatus(Context paramContext, List<Action> paramList, int paramInt)
  {
    getDBManager(paramContext);
    paramContext = paramList.iterator();
    while (paramContext.hasNext())
    {
      paramList = (Action)paramContext.next();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("uploaded", Integer.valueOf(paramInt));
      localContentValues.put("uploadedTime", mFormat.format(new Date()));
      mgr.update("action", localContentValues, "_id=?", new String[] { paramList._id + "" });
    }
  }
  
  private static void markErrorUploadingStatus(Context paramContext, List<Error> paramList, int paramInt)
  {
    getDBManager(paramContext);
    paramContext = paramList.iterator();
    while (paramContext.hasNext())
    {
      paramList = (Error)paramContext.next();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("uploaded", Integer.valueOf(paramInt));
      localContentValues.put("uploadedTime", mFormat.format(new Date()));
      mgr.update("error", localContentValues, "_id=?", new String[] { paramList.getId() + "" });
    }
  }
  
  private static void markThirdAppUploadingStatus(Context paramContext, List<ThirdApp> paramList, int paramInt)
  {
    getDBManager(paramContext);
    paramContext = paramList.iterator();
    while (paramContext.hasNext())
    {
      paramList = (ThirdApp)paramContext.next();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("uploaded", Integer.valueOf(paramInt));
      localContentValues.put("uploadedTime", mFormat.format(new Date()));
      mgr.update("third_app", localContentValues, "_id=?", new String[] { String.valueOf(paramList.get_id()) });
    }
  }
  
  public static void onAction(Context paramContext, NewMotion paramNewMotion, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    Context localContext = paramContext.getApplicationContext();
    JSONObject localJSONObject;
    if (paramNewMotion != NewMotion.GLOBAL_CLICK) {
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.put("activity", paramNewMotion.value);
      localJSONObject.put("position", paramString);
      if ((paramNewMotion != null) && (paramNewMotion.value.endsWith(":install"))) {
        localJSONObject = new JSONObject();
      }
    }
    catch (JSONException localJSONException1)
    {
      for (;;)
      {
        try
        {
          localJSONObject.put("activity", paramNewMotion);
          localJSONObject.put("duration", 0);
          saveAction(localContext, "app_down", localJSONObject);
          b.a(paramContext, paramNewMotion.value, paramString);
          L.w("MobclickAgent", "---------");
          L.w("MobclickAgent", " onAction  " + paramNewMotion.value + "=" + paramString);
          recordsUpload(localContext, 3);
          return;
          localJSONException1 = localJSONException1;
          localJSONException1.printStackTrace();
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
          continue;
        }
        saveAction(localContext, "switch", localJSONObject);
      }
    }
  }
  
  public static void onActionAndValue(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (paramContext == null) {
      return;
    }
    paramContext = paramContext.getApplicationContext();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("value", paramString3);
      localJSONObject.put("activity", getActionName2(paramString1));
      localJSONObject.put("position", getPosition(paramString2));
      localJSONObject.put("duration", 0);
      if ((paramString1 != null) && (paramString1.endsWith(":install"))) {
        paramString2 = new JSONObject();
      }
    }
    catch (JSONException paramString2)
    {
      for (;;)
      {
        try
        {
          paramString2.put("activity", paramString1);
          paramString2.put("duration", 0);
          saveAction(paramContext, "app_down", paramString2);
          L.w("MobclickAgent", "---------");
          L.w("MobclickAgent", " onAction = " + localJSONObject.toString());
          recordsUpload(paramContext, 3);
          return;
          paramString2 = paramString2;
          paramString2.printStackTrace();
        }
        catch (JSONException paramString1)
        {
          paramString1.printStackTrace();
          continue;
        }
        saveAction(paramContext, "switch", localJSONObject);
      }
    }
  }
  
  public static void onActionWithoutUmeng(Context paramContext, NewMotion paramNewMotion, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    paramContext = paramContext.getApplicationContext();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("activity", paramNewMotion.value);
      localJSONObject.put("position", paramString);
      if ((paramNewMotion != null) && (paramNewMotion.value.endsWith(":install"))) {
        localJSONObject = new JSONObject();
      }
    }
    catch (JSONException localJSONException1)
    {
      for (;;)
      {
        try
        {
          localJSONObject.put("activity", paramNewMotion);
          localJSONObject.put("duration", 0);
          saveAction(paramContext, "app_down", localJSONObject);
          L.w("MobclickAgent", "---------");
          L.w("MobclickAgent", " onActionWithoutUmeng  " + paramNewMotion.value + "=" + paramString);
          recordsUpload(paramContext, 3);
          return;
          localJSONException1 = localJSONException1;
          localJSONException1.printStackTrace();
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
          continue;
        }
        saveAction(paramContext, "switch", localJSONObject);
      }
    }
  }
  
  public static void onCreate(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    initFixedParam(paramContext);
    revertUploading(paramContext);
    getLocInfo(paramContext);
    saveStartup(paramContext);
    fetchConfigs(paramContext);
    thirdAppsUpload(paramContext);
    recordsUpload(paramContext, 0);
    deleteLocalLog(paramContext, 24);
    clientUseStart();
  }
  
  public static void onDestroy(Context paramContext)
  {
    clientUseFinish(paramContext);
    map.clear();
  }
  
  public static void onPause(Context paramContext, String paramString)
  {
    if (paramContext == null) {}
    long l;
    do
    {
      return;
      paramContext = paramContext.getApplicationContext();
      l = Thread.currentThread().getId();
      map.put(paramString + "_timeEnd" + l, Long.valueOf(System.currentTimeMillis()));
      l = getDuration(paramString);
    } while (l == -1L);
    saveUseDur(paramContext, paramString, String.valueOf(l));
    recordsUpload(paramContext, 3);
  }
  
  public static void onResume(Context paramContext, String paramString)
  {
    if (paramContext == null) {}
    while (!TextUtils.equals(currPage, paramString)) {
      return;
    }
    long l = Thread.currentThread().getId();
    map.put(paramString + "_timeStart" + l, Long.valueOf(System.currentTimeMillis()));
  }
  
  public static void onWRConectAction(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (paramContext == null) {
      return;
    }
    paramContext = paramContext.getApplicationContext();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("wrc_mac", paramString3);
      localJSONObject.put("imei", FixedParam.IMEI);
      localJSONObject.put("activity", getActionName2(paramString1));
      localJSONObject.put("position", getPosition(paramString2));
      localJSONObject.put("duration", 0);
      if ((paramString1 != null) && (paramString1.endsWith(":install"))) {
        paramString2 = new JSONObject();
      }
    }
    catch (JSONException paramString2)
    {
      for (;;)
      {
        try
        {
          paramString2.put("activity", paramString1);
          paramString2.put("duration", 0);
          saveAction(paramContext, "app_down", paramString2);
          L.i("StatsUtils", "onAction = " + localJSONObject.toString());
          recordsUpload(paramContext, 3);
          return;
          paramString2 = paramString2;
          paramString2.printStackTrace();
        }
        catch (JSONException paramString1)
        {
          paramString1.printStackTrace();
          continue;
        }
        saveAction(paramContext, "switch", localJSONObject);
      }
    }
  }
  
  public static void recordeGatherStatus(Context paramContext, int paramInt1, int paramInt2)
  {
    if (!TextUtils.isEmpty(t.b(paramContext)))
    {
      Object localObject = new ArrayList();
      ((List)localObject).add(new RecordeGatherRequest.Action("add_contacts_count", paramInt1));
      ((List)localObject).add(new RecordeGatherRequest.Action("add_favorites_count", paramInt2));
      paramContext = new RecordeGatherRequest();
      paramContext.setActions((List)localObject);
      localObject = new RecordeGather();
      ((RecordeGather)localObject).setBody(paramContext);
      ((RecordeGather)localObject).post();
    }
  }
  
  public static void recordsUpload(Context paramContext, final int paramInt)
  {
    a.a().execute(new Runnable()
    {
      public void run()
      {
        try
        {
          StatsUtils.recordsUploadRun(this.val$context, paramInt);
          return;
        }
        catch (Throwable localThrowable)
        {
          CrashReport.postCatchedException(localThrowable);
        }
      }
    });
  }
  
  private static void recordsUploadRun(Context paramContext, int paramInt)
  {
    if (!NetUtils.isNetworkConnected(paramContext)) {}
    List localList;
    do
    {
      return;
      getDBManager(paramContext);
      localList = mgr.queryActionsByConditions("action", actionColumns, "uploaded=?", new String[] { "0" });
    } while ((localList == null) || (localList.size() < paramInt));
    Object localObject = convertActionList2JSONArray(localList);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("actions", localObject);
      localObject = createJson(new String[] { "context" }, new Object[] { localJSONObject });
      markActionUploadingStatus(paramContext, localList, 2);
      L.i("StatsUtils", "------");
      L.i("StatsUtils", "actionUpload:\n" + localJSONObject.toString());
      paramInt = getMsgCode(HttpUtils.doPostRequest(paramContext, "http://api.carbit.com.cn/v3.0/record.json", (JSONObject)localObject));
      L.i("StatsUtils", "recordsUploadRun code=" + paramInt);
      if (paramInt == 0)
      {
        markActionUploadingStatus(paramContext, localList, 1);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
      markActionUploadingStatus(paramContext, localList, 0);
    }
  }
  
  private static void revertUploading(Context paramContext)
  {
    a.a().execute(new Runnable()
    {
      public void run()
      {
        StatsUtils.getDBManager(this.val$context);
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("uploaded", Integer.valueOf(0));
        StatsUtils.mgr.update("action", localContentValues, "uploaded=?", new String[] { "2" });
        StatsUtils.mgr.update("error", localContentValues, "uploaded=?", new String[] { "2" });
        StatsUtils.mgr.update("third_app", localContentValues, "uploaded=?", new String[] { "2" });
      }
    });
  }
  
  private static void save3rdAppAction(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong, String paramString6)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("activity", paramString1);
      localJSONObject.put("name", paramString2);
      localJSONObject.put("package_name", paramString3);
      localJSONObject.put("version_name", paramString4);
      localJSONObject.put("version_code", paramString5);
      localJSONObject.put("duration", paramLong);
      localJSONObject.put("launch_type", paramString6);
      paramString1 = new Action("3rd_apps", localJSONObject, mFormat.format(new Date()));
      paramString2 = new ArrayList();
      paramString2.add(paramString1);
      getDBManager(paramContext);
      mgr.addActions(paramString2);
      return;
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  private static void saveAction(Context paramContext, String paramString, JSONObject paramJSONObject)
  {
    paramString = new Action(paramString, paramJSONObject, mFormat.format(new Date()));
    paramJSONObject = new ArrayList();
    paramJSONObject.add(paramString);
    getDBManager(paramContext);
    mgr.addActions(paramJSONObject);
  }
  
  private static void saveStartup(Context paramContext)
  {
    a.a().execute(new Runnable()
    {
      public void run()
      {
        JSONObject localJSONObject = new JSONObject();
        String str = SystemProp.getNetWorkType2String(this.val$context);
        try
        {
          localJSONObject.put("network", str);
          localJSONObject.put("location", StatsUtils.getLocInfo(this.val$context).get("location"));
          StatsUtils.saveAction(this.val$context, "startup", localJSONObject);
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
        }
      }
    });
  }
  
  public static void saveThirdApps(Context paramContext, List<String> paramList)
  {
    getDBManager(paramContext);
    Iterator localIterator = paramList.iterator();
    Object localObject3;
    Object localObject4;
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      String str = (String)localIterator.next();
      paramList = mgr.queryThirdAppsByConditions(thirdAppColumns, "packageName=?", new String[] { str });
      Object localObject1;
      if ((paramList == null) || (paramList.size() == 0))
      {
        localObject1 = null;
        localObject3 = null;
        localObject4 = null;
        paramList = (List<String>)localObject3;
      }
      try
      {
        localObject2 = getPackageManager(paramContext).getPackageInfo(str, 1).applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
        localObject1 = localObject2;
        paramList = (List<String>)localObject3;
        localObject3 = getPackageManager(paramContext).getPackageInfo(str, 1).versionName;
        localObject1 = localObject2;
        paramList = (List<String>)localObject3;
        int i = getPackageManager(paramContext).getPackageInfo(str, 1).versionCode;
        localObject1 = String.valueOf(i);
        paramList = (List<String>)localObject3;
        localObject3 = localObject1;
        localObject1 = localObject2;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          Object localObject2;
          localThrowable.printStackTrace();
          CrashReport.postCatchedException(localThrowable);
          localObject3 = localObject4;
        }
      }
      localObject2 = new ThirdApp();
      ((ThirdApp)localObject2).setName((String)localObject1);
      ((ThirdApp)localObject2).setPackageName(str);
      ((ThirdApp)localObject2).setVersionName(paramList);
      ((ThirdApp)localObject2).setVersionCode((String)localObject3);
      mgr.addThirdApp((ThirdApp)localObject2);
    }
  }
  
  private static void saveUseDur(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("activity", getActionName2(paramString1));
      localJSONObject.put("position", getPosition(paramString1));
      localJSONObject.put("duration", paramString2);
      L.i("StatsUtils", " saveUseDur " + localJSONObject.toString());
      saveAction(paramContext, "use_duration", localJSONObject);
      return;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void setChannel(String paramString)
  {
    mChannel = paramString;
  }
  
  private static void setConfigsRun(Context paramContext)
  {
    JSONObject localJSONObject = getConfigs(paramContext);
    if (localJSONObject == null) {}
    for (;;)
    {
      return;
      Object localObject1 = null;
      Iterator localIterator = localJSONObject.keys();
      String str1;
      while (localIterator.hasNext())
      {
        str1 = (String)localIterator.next();
        try
        {
          localObject2 = localJSONObject.getJSONArray(str1);
          localObject1 = localObject2;
        }
        catch (JSONException localJSONException1)
        {
          try
          {
            Object localObject2;
            int j;
            int i;
            Object localObject3 = localObject1.getJSONObject(i);
            String str2 = ((JSONObject)localObject3).get("name").toString();
            localObject3 = ((JSONObject)localObject3).get("value").toString();
            mgr = getDBManager(paramContext);
            if (mgr.queryTheCursorByCondtions("server_action", servActColumns, "name=?", new String[] { str2 }).getCount() == 0) {
              ((List)localObject2).add(new ConfigProp(str1, str2, (String)localObject3));
            }
            for (;;)
            {
              i += 1;
              break label71;
              localJSONException1 = localJSONException1;
              localJSONException1.printStackTrace();
              break;
              if (mgr.queryTheCursorByCondtions("server_action", servActColumns, "name=? and value=?", new String[] { str2, localObject3 }).getCount() == 0)
              {
                ContentValues localContentValues = new ContentValues();
                localContentValues.put("value", (String)localObject3);
                mgr.update("server_action", localContentValues, "name=?", new String[] { str2 });
              }
            }
          }
          catch (JSONException localJSONException2)
          {
            for (;;)
            {
              localJSONException2.printStackTrace();
            }
          }
          mgr.addConfigProps(localJSONException1);
        }
        localObject2 = new ArrayList();
        j = localObject1.length();
        i = 0;
        label71:
        if (i >= j) {}
      }
    }
  }
  
  private static void thirdAppsUpload(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    a.a().execute(new Runnable()
    {
      public void run()
      {
        StatsUtils.thirdAppsUploadRun(this.val$ctx);
      }
    });
  }
  
  private static void thirdAppsUploadRun(Context paramContext)
  {
    for (;;)
    {
      try
      {
        getDBManager(paramContext);
        List localList = mgr.queryThirdAppsByConditions(thirdAppColumns, "uploaded=?", new String[] { "0" });
        JSONArray localJSONArray;
        JSONObject localJSONObject;
        if ((localList != null) && (localList.size() > 0))
        {
          localJSONArray = convertThirdAppList2JSONArray(localList);
          localJSONObject = new JSONObject();
        }
        try
        {
          localJSONObject.put("apps", localJSONArray);
          localJSONObject = createJson(new String[] { "context" }, new Object[] { localJSONObject });
          markThirdAppUploadingStatus(paramContext, localList, 2);
          if (getMsgCode(HttpUtils.doPostRequest(paramContext, "http://api.carbit.com.cn/v3.0/app-list-3rd.json", localJSONObject)) == 0)
          {
            markThirdAppUploadingStatus(paramContext, localList, 1);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          continue;
        }
        markThirdAppUploadingStatus(paramContext, localList, 0);
      }
      finally {}
    }
  }
}
