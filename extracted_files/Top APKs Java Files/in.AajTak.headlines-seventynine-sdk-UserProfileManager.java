package seventynine.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileManager
  extends Thread
{
  public static ArrayList<HashMap<String, String>> arrayAppList = new ArrayList();
  public static UserProfile profile = new UserProfile();
  public static String updatedb = "false";
  String AdId = "";
  String DeviceId = "";
  String Speed = "";
  String Type = "";
  String android_id = "";
  String api_version_code = "";
  AppLocationService appLocationService;
  String appname = "";
  int byteResponse;
  String city = "";
  String className = this.fullClassName.substring(this.fullClassName.lastIndexOf(".") + 1);
  Context con;
  String country = "";
  String devicebrand = "";
  String devicemanufacturer;
  String devicemodel = "";
  String fb_birthday = "";
  String fb_email = "";
  String fb_first_name = "";
  String fb_gender = "";
  String fb_id = "";
  String fb_last_name = "";
  String fb_locale = "";
  String fb_name = "";
  String fb_updated_time = "";
  boolean flagCollectionData = true;
  boolean flagFbData;
  boolean flagGmailData;
  String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
  String geoInfo = "";
  String gmail_family_name = "";
  String gmail_gender = "";
  String gmail_given_name = "";
  String gmail_locale = "";
  String gmail_name = "";
  String gmail_picture = "";
  String gmail_plus_link = "";
  String gmailid = "";
  String gprs_mac_id = "";
  String imsi = "";
  String latitude = "";
  int lineNumber;
  String longitude = "";
  String macAddress = "";
  HashMap<String, String> map = null;
  String mcc = "";
  String mnc = "";
  String msisdn = "";
  String osname = "Android";
  String osversion = "";
  String phone_no = "";
  String pkgName = "";
  String possibleEmail = "";
  String postal_code = "";
  SharedPreferences prefsApplist;
  SharedPreferences prefsApplistFirstTime = PreferenceManager.getDefaultSharedPreferences(SeventynineConstants.appContext);
  SharedPreferences prefsfirst = PreferenceManager.getDefaultSharedPreferences(SeventynineConstants.appContext);
  boolean profilerowexit = false;
  String region = "";
  boolean retryFlag = false;
  int retrycounter;
  String street = "";
  String telco_code = "";
  String telco_name = "";
  TelephonyManager telephonyManager;
  Timer timer;
  TimerTask timerTask;
  boolean timestampcheckflag = true;
  long timetorun;
  Long tsLong;
  long updatedvalueindb;
  String urlStr;
  String version_name = "";
  
  public UserProfileManager(Context paramContext)
  {
    this.con = paramContext;
  }
  
  public static String convertInputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    String str1 = "";
    str2 = str1;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
      for (;;)
      {
        str2 = str1;
        String str3 = localBufferedReader.readLine();
        if (str3 == null)
        {
          str2 = str1;
          paramInputStream.close();
          return str1;
        }
        str2 = str1;
        str1 = str1 + str3;
      }
      return str2;
    }
    catch (Exception paramInputStream)
    {
      DebugTrack.SendExceptionReport(paramInputStream.toString(), "convertInputStreamToString()", "", "", "", "", "UserProfileManager", 0);
      DebugTrack.log("e", paramInputStream.toString(), "UserProfileManager", 0, "convertInputStreamToString()");
    }
  }
  
  private void initLocationValue(double paramDouble1, double paramDouble2, Address paramAddress, String paramString)
  {
    try
    {
      this.latitude = paramDouble1;
      if (this.latitude == null) {
        this.latitude = "";
      }
      this.longitude = paramDouble2;
      if (this.longitude == null) {
        this.longitude = "";
      }
      SeventynineConstants.strlat = this.latitude;
      SeventynineConstants.strlon = this.longitude;
      this.country = paramAddress.getCountryName();
      if (this.country == null) {
        this.country = "";
      }
      SeventynineConstants.strCountry = this.country;
      this.city = paramAddress.getLocality();
      if (this.city == null) {
        this.city = "";
      }
      SeventynineConstants.strCity = this.city;
      this.region = paramAddress.getAdminArea();
      if (this.region == null) {
        this.region = "";
      }
      this.street = paramString;
      if (this.street == null) {
        this.street = "";
      }
      SeventynineConstants.strStreet = this.street;
      this.postal_code = paramAddress.getPostalCode();
      if (this.postal_code == null) {
        this.postal_code = "";
      }
      this.phone_no = paramAddress.getPhone();
      if (this.phone_no == null) {
        this.phone_no = "";
      }
      return;
    }
    catch (Exception paramAddress)
    {
      DebugTrack.SendExceptionReport(paramAddress.toString(), "initLocationValue()", "", "", "", "", "UserProfileManager", 0);
      DebugTrack.log("e", paramAddress.toString(), "UserProfileManager", 0, "initLocationValue()");
    }
  }
  
  private static String readResponse(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    for (;;)
    {
      try
      {
        arrayOfByte = new byte['ࠀ'];
        i = paramInputStream.read(arrayOfByte, 0, arrayOfByte.length);
        if (i >= 0) {
          continue;
        }
      }
      catch (Exception paramInputStream)
      {
        byte[] arrayOfByte;
        int i;
        DebugTrack.SendExceptionReport(paramInputStream.toString(), "readResponse()", "", "", "", "", "UserProfileManager", 0);
        DebugTrack.log("e", paramInputStream.toString(), "UserProfileManager", 0, "readResponse()");
        continue;
      }
      return new String(localByteArrayOutputStream.toByteArray(), "UTF-8");
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  public void Showresult()
  {
    try
    {
      insertInDb();
      return;
    }
    catch (Exception localException)
    {
      DebugTrack.SendExceptionReport(localException.toString(), "Showresult()", "", "", "", "", "UserProfileManager", 0);
      DebugTrack.log("e", localException.toString(), "UserProfileManager", 0, "Showresult()");
    }
  }
  
  /* Error */
  public void getDevicedatawithoutasync()
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 422	seventynine/sdk/SeventynineConstants:strAdId	Ljava/lang/String;
    //   4: putfield 133	seventynine/sdk/UserProfileManager:AdId	Ljava/lang/String;
    //   7: aload_0
    //   8: getfield 133	seventynine/sdk/UserProfileManager:AdId	Ljava/lang/String;
    //   11: ifnonnull +9 -> 20
    //   14: aload_0
    //   15: ldc -125
    //   17: putfield 133	seventynine/sdk/UserProfileManager:AdId	Ljava/lang/String;
    //   20: aload_0
    //   21: aload_0
    //   22: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   25: invokestatic 428	seventynine/sdk/NetworkUtil:getConnectivityStatusStringSecond	(Landroid/content/Context;)Ljava/lang/String;
    //   28: putfield 189	seventynine/sdk/UserProfileManager:Type	Ljava/lang/String;
    //   31: aload_0
    //   32: getfield 189	seventynine/sdk/UserProfileManager:Type	Ljava/lang/String;
    //   35: ifnonnull +9 -> 44
    //   38: aload_0
    //   39: ldc -125
    //   41: putfield 189	seventynine/sdk/UserProfileManager:Type	Ljava/lang/String;
    //   44: aload_0
    //   45: aload_0
    //   46: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   49: invokestatic 431	seventynine/sdk/NetworkUtil:get_network	(Landroid/content/Context;)Ljava/lang/String;
    //   52: putfield 191	seventynine/sdk/UserProfileManager:Speed	Ljava/lang/String;
    //   55: aload_0
    //   56: getfield 191	seventynine/sdk/UserProfileManager:Speed	Ljava/lang/String;
    //   59: ifnonnull +9 -> 68
    //   62: aload_0
    //   63: ldc -125
    //   65: putfield 191	seventynine/sdk/UserProfileManager:Speed	Ljava/lang/String;
    //   68: aload_0
    //   69: aload_0
    //   70: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   73: invokevirtual 437	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   76: ldc_w 438
    //   79: invokestatic 444	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   82: putfield 145	seventynine/sdk/UserProfileManager:android_id	Ljava/lang/String;
    //   85: aload_0
    //   86: getfield 145	seventynine/sdk/UserProfileManager:android_id	Ljava/lang/String;
    //   89: ifnonnull +9 -> 98
    //   92: aload_0
    //   93: ldc -125
    //   95: putfield 145	seventynine/sdk/UserProfileManager:android_id	Ljava/lang/String;
    //   98: aload_0
    //   99: aload_0
    //   100: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   103: ldc_w 446
    //   106: invokevirtual 450	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   109: checkcast 452	android/telephony/TelephonyManager
    //   112: putfield 454	seventynine/sdk/UserProfileManager:telephonyManager	Landroid/telephony/TelephonyManager;
    //   115: aload_0
    //   116: aload_0
    //   117: getfield 454	seventynine/sdk/UserProfileManager:telephonyManager	Landroid/telephony/TelephonyManager;
    //   120: invokevirtual 457	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   123: putfield 181	seventynine/sdk/UserProfileManager:DeviceId	Ljava/lang/String;
    //   126: aload_0
    //   127: getfield 181	seventynine/sdk/UserProfileManager:DeviceId	Ljava/lang/String;
    //   130: ifnonnull +9 -> 139
    //   133: aload_0
    //   134: ldc -125
    //   136: putfield 181	seventynine/sdk/UserProfileManager:DeviceId	Ljava/lang/String;
    //   139: aload_0
    //   140: getfield 454	seventynine/sdk/UserProfileManager:telephonyManager	Landroid/telephony/TelephonyManager;
    //   143: invokevirtual 460	android/telephony/TelephonyManager:getSimSerialNumber	()Ljava/lang/String;
    //   146: pop
    //   147: aload_0
    //   148: aload_0
    //   149: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   152: invokevirtual 463	android/content/Context:getPackageName	()Ljava/lang/String;
    //   155: putfield 183	seventynine/sdk/UserProfileManager:pkgName	Ljava/lang/String;
    //   158: aload_0
    //   159: getfield 183	seventynine/sdk/UserProfileManager:pkgName	Ljava/lang/String;
    //   162: ifnonnull +9 -> 171
    //   165: aload_0
    //   166: ldc -125
    //   168: putfield 183	seventynine/sdk/UserProfileManager:pkgName	Ljava/lang/String;
    //   171: aload_0
    //   172: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   175: invokevirtual 467	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   178: astore_1
    //   179: aload_0
    //   180: aload_1
    //   181: aload_1
    //   182: ldc_w 469
    //   185: ldc_w 471
    //   188: aload_0
    //   189: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   192: invokevirtual 463	android/content/Context:getPackageName	()Ljava/lang/String;
    //   195: invokevirtual 477	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   198: invokevirtual 481	android/content/res/Resources:getText	(I)Ljava/lang/CharSequence;
    //   201: invokeinterface 484 1 0
    //   206: putfield 185	seventynine/sdk/UserProfileManager:appname	Ljava/lang/String;
    //   209: aload_0
    //   210: getfield 185	seventynine/sdk/UserProfileManager:appname	Ljava/lang/String;
    //   213: ifnonnull +9 -> 222
    //   216: aload_0
    //   217: ldc -125
    //   219: putfield 185	seventynine/sdk/UserProfileManager:appname	Ljava/lang/String;
    //   222: aload_0
    //   223: aload_0
    //   224: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   227: ldc_w 486
    //   230: invokevirtual 450	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   233: checkcast 488	android/net/wifi/WifiManager
    //   236: invokevirtual 492	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   239: invokevirtual 497	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   242: putfield 147	seventynine/sdk/UserProfileManager:macAddress	Ljava/lang/String;
    //   245: aload_0
    //   246: getfield 147	seventynine/sdk/UserProfileManager:macAddress	Ljava/lang/String;
    //   249: ifnonnull +9 -> 258
    //   252: aload_0
    //   253: ldc -125
    //   255: putfield 147	seventynine/sdk/UserProfileManager:macAddress	Ljava/lang/String;
    //   258: aload_0
    //   259: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   262: invokevirtual 501	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   265: aload_0
    //   266: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   269: invokevirtual 463	android/content/Context:getPackageName	()Ljava/lang/String;
    //   272: iconst_0
    //   273: invokevirtual 507	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   276: pop
    //   277: aload_0
    //   278: getstatic 510	seventynine/sdk/SeventynineConstants:ajv	Ljava/lang/String;
    //   281: putfield 151	seventynine/sdk/UserProfileManager:version_name	Ljava/lang/String;
    //   284: aload_0
    //   285: getfield 151	seventynine/sdk/UserProfileManager:version_name	Ljava/lang/String;
    //   288: ifnonnull +9 -> 297
    //   291: aload_0
    //   292: ldc -125
    //   294: putfield 151	seventynine/sdk/UserProfileManager:version_name	Ljava/lang/String;
    //   297: aload_0
    //   298: getstatic 513	seventynine/sdk/SeventynineConstants:v	Ljava/lang/String;
    //   301: putfield 187	seventynine/sdk/UserProfileManager:api_version_code	Ljava/lang/String;
    //   304: aload_0
    //   305: getfield 187	seventynine/sdk/UserProfileManager:api_version_code	Ljava/lang/String;
    //   308: ifnonnull +9 -> 317
    //   311: aload_0
    //   312: ldc -125
    //   314: putfield 187	seventynine/sdk/UserProfileManager:api_version_code	Ljava/lang/String;
    //   317: getstatic 519	android/util/Patterns:EMAIL_ADDRESS	Ljava/util/regex/Pattern;
    //   320: astore_1
    //   321: aload_0
    //   322: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   325: invokestatic 525	android/accounts/AccountManager:get	(Landroid/content/Context;)Landroid/accounts/AccountManager;
    //   328: ldc_w 527
    //   331: invokevirtual 531	android/accounts/AccountManager:getAccountsByType	(Ljava/lang/String;)[Landroid/accounts/Account;
    //   334: iconst_0
    //   335: aaload
    //   336: astore_2
    //   337: aload_1
    //   338: aload_2
    //   339: getfield 536	android/accounts/Account:name	Ljava/lang/String;
    //   342: invokevirtual 542	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   345: invokevirtual 548	java/util/regex/Matcher:matches	()Z
    //   348: ifeq +30 -> 378
    //   351: getstatic 551	seventynine/sdk/SeventynineConstants:isGetEmailId	Z
    //   354: ifeq +76 -> 430
    //   357: aload_0
    //   358: aload_2
    //   359: getfield 536	android/accounts/Account:name	Ljava/lang/String;
    //   362: putfield 153	seventynine/sdk/UserProfileManager:possibleEmail	Ljava/lang/String;
    //   365: aload_0
    //   366: getfield 153	seventynine/sdk/UserProfileManager:possibleEmail	Ljava/lang/String;
    //   369: ifnonnull +9 -> 378
    //   372: aload_0
    //   373: ldc -125
    //   375: putfield 153	seventynine/sdk/UserProfileManager:possibleEmail	Ljava/lang/String;
    //   378: return
    //   379: astore_1
    //   380: aload_0
    //   381: ldc -125
    //   383: putfield 147	seventynine/sdk/UserProfileManager:macAddress	Ljava/lang/String;
    //   386: goto -141 -> 245
    //   389: astore_1
    //   390: aload_1
    //   391: invokevirtual 332	java/lang/Exception:toString	()Ljava/lang/String;
    //   394: ldc_w 553
    //   397: ldc -125
    //   399: ldc -125
    //   401: ldc -125
    //   403: ldc -125
    //   405: ldc_w 336
    //   408: iconst_0
    //   409: invokestatic 342	seventynine/sdk/DebugTrack:SendExceptionReport	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   412: ldc_w 344
    //   415: aload_1
    //   416: invokevirtual 332	java/lang/Exception:toString	()Ljava/lang/String;
    //   419: ldc_w 336
    //   422: iconst_0
    //   423: ldc_w 553
    //   426: invokestatic 348	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   429: return
    //   430: aload_0
    //   431: ldc -125
    //   433: putfield 153	seventynine/sdk/UserProfileManager:possibleEmail	Ljava/lang/String;
    //   436: goto -71 -> 365
    //   439: astore_1
    //   440: return
    //   441: astore_1
    //   442: goto -165 -> 277
    //   445: astore_1
    //   446: goto -224 -> 222
    //   449: astore_1
    //   450: goto -279 -> 171
    //   453: astore_1
    //   454: goto -307 -> 147
    //   457: astore_1
    //   458: goto -343 -> 115
    //   461: astore_1
    //   462: goto -364 -> 98
    //   465: astore_1
    //   466: goto -398 -> 68
    //   469: astore_1
    //   470: goto -463 -> 7
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	473	0	this	UserProfileManager
    //   178	160	1	localObject	Object
    //   379	1	1	localException1	Exception
    //   389	27	1	localException2	Exception
    //   439	1	1	localException3	Exception
    //   441	1	1	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   445	1	1	localException4	Exception
    //   449	1	1	localException5	Exception
    //   453	1	1	localException6	Exception
    //   457	1	1	localException7	Exception
    //   461	1	1	localException8	Exception
    //   465	1	1	localException9	Exception
    //   469	1	1	localException10	Exception
    //   336	23	2	localAccount	android.accounts.Account
    // Exception table:
    //   from	to	target	type
    //   222	245	379	java/lang/Exception
    //   7	20	389	java/lang/Exception
    //   245	258	389	java/lang/Exception
    //   258	277	389	java/lang/Exception
    //   277	297	389	java/lang/Exception
    //   297	317	389	java/lang/Exception
    //   380	386	389	java/lang/Exception
    //   317	365	439	java/lang/Exception
    //   365	378	439	java/lang/Exception
    //   430	436	439	java/lang/Exception
    //   258	277	441	android/content/pm/PackageManager$NameNotFoundException
    //   171	222	445	java/lang/Exception
    //   147	171	449	java/lang/Exception
    //   115	139	453	java/lang/Exception
    //   139	147	453	java/lang/Exception
    //   98	115	457	java/lang/Exception
    //   68	98	461	java/lang/Exception
    //   20	44	465	java/lang/Exception
    //   44	68	465	java/lang/Exception
    //   0	7	469	java/lang/Exception
  }
  
  public void getListOfApp()
  {
    try
    {
      this.prefsApplist = PreferenceManager.getDefaultSharedPreferences(SeventynineConstants.appContext);
      if (SeventynineConstants.isSendAppList)
      {
        this.prefsApplist.getBoolean("AppList", false);
        if (!this.prefsApplist.getBoolean("AppList", false))
        {
          Object localObject = this.con.getPackageManager().getInstalledPackages(0);
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          if (i >= ((List)localObject).size()) {
            i = 0;
          }
          for (;;)
          {
            if (i >= localArrayList.size())
            {
              localObject = this.prefsApplist.edit();
              ((SharedPreferences.Editor)localObject).putBoolean("AppList", true);
              ((SharedPreferences.Editor)localObject).commit();
              return;
              PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
              AppInfo localAppInfo = new AppInfo();
              localAppInfo.pname = localPackageInfo.packageName;
              localAppInfo.lastupdatedtime = localPackageInfo.lastUpdateTime;
              localArrayList.add(localAppInfo);
              i += 1;
              break;
            }
            this.map = new HashMap();
            this.map.put(((AppInfo)localArrayList.get(i)).pname, ((AppInfo)localArrayList.get(i)).lastupdatedtime + "||ADD");
            arrayAppList.add(this.map);
            i += 1;
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      DebugTrack.SendExceptionReport(localException.toString(), "getListOfApp()", "", "", "", "", "UserProfileManager", 0);
      DebugTrack.log("e", localException.toString(), "UserProfileManager", 0, "getListOfApp()");
    }
  }
  
  /* Error */
  public void getLocation()
  {
    // Byte code:
    //   0: invokestatic 637	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: ifnonnull +6 -> 9
    //   6: invokestatic 640	android/os/Looper:prepare	()V
    //   9: getstatic 357	seventynine/sdk/SeventynineConstants:strlat	Ljava/lang/String;
    //   12: invokevirtual 643	java/lang/String:length	()I
    //   15: ifgt +16 -> 31
    //   18: getstatic 360	seventynine/sdk/SeventynineConstants:strlon	Ljava/lang/String;
    //   21: invokevirtual 643	java/lang/String:length	()I
    //   24: istore 5
    //   26: iload 5
    //   28: ifle +238 -> 266
    //   31: getstatic 357	seventynine/sdk/SeventynineConstants:strlat	Ljava/lang/String;
    //   34: invokestatic 649	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   37: dstore_1
    //   38: getstatic 360	seventynine/sdk/SeventynineConstants:strlon	Ljava/lang/String;
    //   41: invokestatic 649	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   44: dstore_3
    //   45: new 651	android/location/Geocoder
    //   48: dup
    //   49: aload_0
    //   50: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   53: getstatic 657	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   56: invokespecial 660	android/location/Geocoder:<init>	(Landroid/content/Context;Ljava/util/Locale;)V
    //   59: dload_1
    //   60: dload_3
    //   61: iconst_1
    //   62: invokevirtual 664	android/location/Geocoder:getFromLocation	(DDI)Ljava/util/List;
    //   65: astore 8
    //   67: aload 8
    //   69: iconst_0
    //   70: invokeinterface 594 2 0
    //   75: checkcast 362	android/location/Address
    //   78: astore 9
    //   80: aconst_null
    //   81: astore 6
    //   83: aconst_null
    //   84: astore 7
    //   86: aload 8
    //   88: invokeinterface 577 1 0
    //   93: ifle +29 -> 122
    //   96: iconst_0
    //   97: istore 5
    //   99: aload 7
    //   101: astore 6
    //   103: iload 5
    //   105: aload 8
    //   107: iconst_0
    //   108: invokeinterface 594 2 0
    //   113: checkcast 362	android/location/Address
    //   116: invokevirtual 667	android/location/Address:getMaxAddressLineIndex	()I
    //   119: if_icmplt +38 -> 157
    //   122: aload_0
    //   123: dload_1
    //   124: dload_3
    //   125: aload 9
    //   127: aload 6
    //   129: invokespecial 669	seventynine/sdk/UserProfileManager:initLocationValue	(DDLandroid/location/Address;Ljava/lang/String;)V
    //   132: ldc2_w 670
    //   135: invokestatic 675	seventynine/sdk/UserProfileManager:sleep	(J)V
    //   138: aload_0
    //   139: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   142: aload_0
    //   143: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   146: invokevirtual 684	seventynine/sdk/Database:FetchGeoData	(Landroid/content/Context;)Ljava/lang/String;
    //   149: putfield 227	seventynine/sdk/UserProfileManager:geoInfo	Ljava/lang/String;
    //   152: aload_0
    //   153: invokevirtual 686	seventynine/sdk/UserProfileManager:Showresult	()V
    //   156: return
    //   157: new 317	java/lang/StringBuilder
    //   160: dup
    //   161: aload 6
    //   163: invokestatic 321	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   166: invokespecial 324	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   169: aload 8
    //   171: iconst_0
    //   172: invokeinterface 594 2 0
    //   177: checkcast 362	android/location/Address
    //   180: iload 5
    //   182: invokevirtual 689	android/location/Address:getAddressLine	(I)Ljava/lang/String;
    //   185: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: ldc_w 691
    //   191: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: astore 6
    //   199: iload 5
    //   201: iconst_1
    //   202: iadd
    //   203: istore 5
    //   205: goto -102 -> 103
    //   208: astore 6
    //   210: aload_0
    //   211: invokevirtual 686	seventynine/sdk/UserProfileManager:Showresult	()V
    //   214: return
    //   215: astore 6
    //   217: aload 6
    //   219: invokevirtual 332	java/lang/Exception:toString	()Ljava/lang/String;
    //   222: ldc_w 693
    //   225: ldc -125
    //   227: ldc -125
    //   229: ldc -125
    //   231: ldc -125
    //   233: ldc_w 336
    //   236: iconst_0
    //   237: invokestatic 342	seventynine/sdk/DebugTrack:SendExceptionReport	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   240: ldc_w 344
    //   243: aload 6
    //   245: invokevirtual 332	java/lang/Exception:toString	()Ljava/lang/String;
    //   248: ldc_w 336
    //   251: iconst_0
    //   252: ldc_w 693
    //   255: invokestatic 348	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   258: return
    //   259: astore 6
    //   261: aload_0
    //   262: invokevirtual 686	seventynine/sdk/UserProfileManager:Showresult	()V
    //   265: return
    //   266: aload_0
    //   267: invokevirtual 686	seventynine/sdk/UserProfileManager:Showresult	()V
    //   270: return
    //   271: astore 6
    //   273: goto -121 -> 152
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	276	0	this	UserProfileManager
    //   37	87	1	d1	double
    //   44	81	3	d2	double
    //   24	180	5	i	int
    //   81	117	6	localObject1	Object
    //   208	1	6	localIOException	IOException
    //   215	29	6	localException1	Exception
    //   259	1	6	localException2	Exception
    //   271	1	6	localException3	Exception
    //   84	16	7	localObject2	Object
    //   65	105	8	localList	List
    //   78	48	9	localAddress	Address
    // Exception table:
    //   from	to	target	type
    //   31	80	208	java/io/IOException
    //   86	96	208	java/io/IOException
    //   103	122	208	java/io/IOException
    //   122	138	208	java/io/IOException
    //   138	152	208	java/io/IOException
    //   152	156	208	java/io/IOException
    //   157	199	208	java/io/IOException
    //   0	9	215	java/lang/Exception
    //   9	26	215	java/lang/Exception
    //   210	214	215	java/lang/Exception
    //   261	265	215	java/lang/Exception
    //   266	270	215	java/lang/Exception
    //   31	80	259	java/lang/Exception
    //   86	96	259	java/lang/Exception
    //   103	122	259	java/lang/Exception
    //   122	138	259	java/lang/Exception
    //   152	156	259	java/lang/Exception
    //   157	199	259	java/lang/Exception
    //   138	152	271	java/lang/Exception
  }
  
  public void getTelecomInfo()
  {
    this.osversion = Build.VERSION.RELEASE;
    this.devicebrand = Build.BRAND;
    this.devicemodel = Build.MODEL;
    this.devicemanufacturer = Build.MANUFACTURER;
    try
    {
      String str = this.telephonyManager.getNetworkOperator();
      if ((str != null) && (!str.equalsIgnoreCase("")))
      {
        int i = Integer.parseInt(str.substring(0, 3));
        int j = Integer.parseInt(str.substring(3));
        this.telco_name = this.telephonyManager.getNetworkOperatorName().toString();
        this.telco_code = this.telephonyManager.getNetworkOperator().toString();
        this.mnc = j;
        this.mcc = i;
        try
        {
          this.imsi = this.telephonyManager.getSubscriberId();
          if (this.imsi == null)
          {
            this.imsi = "";
            return;
          }
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            if (Boolean.parseBoolean(SeventynineConstants.strDebugMode)) {
              localException1.printStackTrace();
            }
          }
        }
      }
      return;
    }
    catch (Exception localException2)
    {
      DebugTrack.SendExceptionReport(localException2.toString(), "getTelecomInfo()", "", "", "", "", "UserProfileManager", 0);
      DebugTrack.log("e", localException2.toString(), "UserProfileManager", 0, "getTelecomInfo()");
      if (Boolean.parseBoolean(SeventynineConstants.strDebugMode)) {
        localException2.printStackTrace();
      }
    }
  }
  
  /* Error */
  public void insertInDb()
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   5: invokestatic 278	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   8: ldc_w 751
    //   11: ldc -125
    //   13: invokeinterface 754 3 0
    //   18: putfield 229	seventynine/sdk/UserProfileManager:msisdn	Ljava/lang/String;
    //   21: aload_0
    //   22: getfield 280	seventynine/sdk/UserProfileManager:prefsfirst	Landroid/content/SharedPreferences;
    //   25: ldc_w 756
    //   28: iconst_0
    //   29: invokeinterface 567 3 0
    //   34: ifne +633 -> 667
    //   37: new 15	seventynine/sdk/UserProfileManager$HttpAsyncTask
    //   40: dup
    //   41: aload_0
    //   42: aconst_null
    //   43: invokespecial 759	seventynine/sdk/UserProfileManager$HttpAsyncTask:<init>	(Lseventynine/sdk/UserProfileManager;Lseventynine/sdk/UserProfileManager$HttpAsyncTask;)V
    //   46: astore_3
    //   47: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   50: aload_0
    //   51: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   54: invokevirtual 762	seventynine/sdk/Database:deleteAllProfileData	(Landroid/content/Context;)V
    //   57: aload_0
    //   58: invokestatic 768	java/lang/System:currentTimeMillis	()J
    //   61: ldc2_w 769
    //   64: ldiv
    //   65: invokestatic 775	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   68: putfield 777	seventynine/sdk/UserProfileManager:tsLong	Ljava/lang/Long;
    //   71: aload_0
    //   72: getfield 777	seventynine/sdk/UserProfileManager:tsLong	Ljava/lang/Long;
    //   75: invokevirtual 778	java/lang/Long:toString	()Ljava/lang/String;
    //   78: astore 4
    //   80: aload_3
    //   81: invokevirtual 782	seventynine/sdk/UserProfileManager$HttpAsyncTask:getStatus	()Landroid/os/AsyncTask$Status;
    //   84: getstatic 788	android/os/AsyncTask$Status:RUNNING	Landroid/os/AsyncTask$Status;
    //   87: if_acmpeq +579 -> 666
    //   90: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   93: ldc_w 790
    //   96: ldc -125
    //   98: ldc_w 792
    //   101: aload_0
    //   102: getfield 181	seventynine/sdk/UserProfileManager:DeviceId	Ljava/lang/String;
    //   105: ldc_w 438
    //   108: aload_0
    //   109: getfield 145	seventynine/sdk/UserProfileManager:android_id	Ljava/lang/String;
    //   112: ldc_w 794
    //   115: aload_0
    //   116: getfield 133	seventynine/sdk/UserProfileManager:AdId	Ljava/lang/String;
    //   119: ldc_w 796
    //   122: aload_0
    //   123: getfield 153	seventynine/sdk/UserProfileManager:possibleEmail	Ljava/lang/String;
    //   126: ldc_w 797
    //   129: ldc_w 797
    //   132: ldc_w 799
    //   135: aload_0
    //   136: getfield 147	seventynine/sdk/UserProfileManager:macAddress	Ljava/lang/String;
    //   139: ldc_w 801
    //   142: aload_0
    //   143: getfield 137	seventynine/sdk/UserProfileManager:osname	Ljava/lang/String;
    //   146: ldc_w 803
    //   149: aload_0
    //   150: getfield 139	seventynine/sdk/UserProfileManager:osversion	Ljava/lang/String;
    //   153: ldc_w 805
    //   156: aload_0
    //   157: getfield 141	seventynine/sdk/UserProfileManager:devicebrand	Ljava/lang/String;
    //   160: ldc_w 807
    //   163: aload_0
    //   164: getfield 143	seventynine/sdk/UserProfileManager:devicemodel	Ljava/lang/String;
    //   167: ldc_w 809
    //   170: aload_0
    //   171: getfield 712	seventynine/sdk/UserProfileManager:devicemanufacturer	Ljava/lang/String;
    //   174: ldc_w 469
    //   177: aload_0
    //   178: getfield 185	seventynine/sdk/UserProfileManager:appname	Ljava/lang/String;
    //   181: ldc_w 811
    //   184: aload_0
    //   185: getfield 183	seventynine/sdk/UserProfileManager:pkgName	Ljava/lang/String;
    //   188: ldc_w 813
    //   191: aload_0
    //   192: getfield 187	seventynine/sdk/UserProfileManager:api_version_code	Ljava/lang/String;
    //   195: ldc_w 815
    //   198: aload_0
    //   199: getfield 151	seventynine/sdk/UserProfileManager:version_name	Ljava/lang/String;
    //   202: ldc_w 816
    //   205: aload_0
    //   206: getfield 155	seventynine/sdk/UserProfileManager:latitude	Ljava/lang/String;
    //   209: ldc_w 817
    //   212: aload_0
    //   213: getfield 157	seventynine/sdk/UserProfileManager:longitude	Ljava/lang/String;
    //   216: ldc_w 818
    //   219: aload_0
    //   220: getfield 159	seventynine/sdk/UserProfileManager:country	Ljava/lang/String;
    //   223: ldc_w 819
    //   226: aload_0
    //   227: getfield 161	seventynine/sdk/UserProfileManager:city	Ljava/lang/String;
    //   230: ldc_w 820
    //   233: aload_0
    //   234: getfield 163	seventynine/sdk/UserProfileManager:region	Ljava/lang/String;
    //   237: ldc_w 821
    //   240: aload_0
    //   241: getfield 165	seventynine/sdk/UserProfileManager:street	Ljava/lang/String;
    //   244: ldc_w 822
    //   247: ldc -125
    //   249: ldc_w 823
    //   252: aload_0
    //   253: getfield 169	seventynine/sdk/UserProfileManager:phone_no	Ljava/lang/String;
    //   256: ldc_w 824
    //   259: aload_0
    //   260: getfield 171	seventynine/sdk/UserProfileManager:telco_name	Ljava/lang/String;
    //   263: ldc_w 825
    //   266: aload_0
    //   267: getfield 173	seventynine/sdk/UserProfileManager:telco_code	Ljava/lang/String;
    //   270: ldc_w 826
    //   273: aload_0
    //   274: getfield 175	seventynine/sdk/UserProfileManager:mnc	Ljava/lang/String;
    //   277: ldc_w 827
    //   280: aload_0
    //   281: getfield 177	seventynine/sdk/UserProfileManager:mcc	Ljava/lang/String;
    //   284: ldc_w 828
    //   287: aload_0
    //   288: getfield 179	seventynine/sdk/UserProfileManager:imsi	Ljava/lang/String;
    //   291: ldc_w 829
    //   294: aload_0
    //   295: getfield 189	seventynine/sdk/UserProfileManager:Type	Ljava/lang/String;
    //   298: ldc_w 830
    //   301: aload_0
    //   302: getfield 191	seventynine/sdk/UserProfileManager:Speed	Ljava/lang/String;
    //   305: ldc_w 832
    //   308: aload_0
    //   309: getfield 193	seventynine/sdk/UserProfileManager:gmailid	Ljava/lang/String;
    //   312: ldc_w 834
    //   315: aload_0
    //   316: getfield 195	seventynine/sdk/UserProfileManager:gmail_name	Ljava/lang/String;
    //   319: ldc_w 836
    //   322: aload_0
    //   323: getfield 197	seventynine/sdk/UserProfileManager:gmail_given_name	Ljava/lang/String;
    //   326: ldc_w 838
    //   329: aload_0
    //   330: getfield 199	seventynine/sdk/UserProfileManager:gmail_family_name	Ljava/lang/String;
    //   333: ldc_w 840
    //   336: aload_0
    //   337: getfield 201	seventynine/sdk/UserProfileManager:gmail_plus_link	Ljava/lang/String;
    //   340: ldc_w 842
    //   343: aload_0
    //   344: getfield 203	seventynine/sdk/UserProfileManager:gmail_picture	Ljava/lang/String;
    //   347: ldc_w 844
    //   350: aload_0
    //   351: getfield 205	seventynine/sdk/UserProfileManager:gmail_gender	Ljava/lang/String;
    //   354: ldc_w 846
    //   357: aload_0
    //   358: getfield 207	seventynine/sdk/UserProfileManager:gmail_locale	Ljava/lang/String;
    //   361: ldc_w 848
    //   364: aload_0
    //   365: getfield 209	seventynine/sdk/UserProfileManager:fb_id	Ljava/lang/String;
    //   368: ldc_w 850
    //   371: aload_0
    //   372: getfield 211	seventynine/sdk/UserProfileManager:fb_birthday	Ljava/lang/String;
    //   375: ldc_w 852
    //   378: aload_0
    //   379: getfield 213	seventynine/sdk/UserProfileManager:fb_email	Ljava/lang/String;
    //   382: ldc_w 854
    //   385: aload_0
    //   386: getfield 215	seventynine/sdk/UserProfileManager:fb_first_name	Ljava/lang/String;
    //   389: ldc_w 856
    //   392: aload_0
    //   393: getfield 217	seventynine/sdk/UserProfileManager:fb_gender	Ljava/lang/String;
    //   396: ldc_w 858
    //   399: aload_0
    //   400: getfield 219	seventynine/sdk/UserProfileManager:fb_last_name	Ljava/lang/String;
    //   403: ldc_w 860
    //   406: aload_0
    //   407: getfield 221	seventynine/sdk/UserProfileManager:fb_locale	Ljava/lang/String;
    //   410: ldc_w 862
    //   413: aload_0
    //   414: getfield 223	seventynine/sdk/UserProfileManager:fb_name	Ljava/lang/String;
    //   417: ldc_w 864
    //   420: aload_0
    //   421: getfield 225	seventynine/sdk/UserProfileManager:fb_updated_time	Ljava/lang/String;
    //   424: ldc_w 866
    //   427: new 317	java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   434: getstatic 869	seventynine/sdk/SeventynineConstants:age	Ljava/lang/String;
    //   437: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   443: ldc_w 871
    //   446: new 317	java/lang/StringBuilder
    //   449: dup
    //   450: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   453: getstatic 874	seventynine/sdk/SeventynineConstants:dob	Ljava/lang/String;
    //   456: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: ldc_w 876
    //   465: new 317	java/lang/StringBuilder
    //   468: dup
    //   469: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   472: getstatic 879	seventynine/sdk/SeventynineConstants:language	Ljava/lang/String;
    //   475: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   481: ldc_w 881
    //   484: new 317	java/lang/StringBuilder
    //   487: dup
    //   488: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   491: getstatic 884	seventynine/sdk/SeventynineConstants:gender	Ljava/lang/String;
    //   494: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   500: ldc_w 886
    //   503: new 317	java/lang/StringBuilder
    //   506: dup
    //   507: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   510: getstatic 889	seventynine/sdk/SeventynineConstants:email	Ljava/lang/String;
    //   513: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   516: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   519: ldc_w 891
    //   522: new 317	java/lang/StringBuilder
    //   525: dup
    //   526: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   529: getstatic 894	seventynine/sdk/SeventynineConstants:complilationId	Ljava/lang/String;
    //   532: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   535: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   538: ldc_w 896
    //   541: new 317	java/lang/StringBuilder
    //   544: dup
    //   545: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   548: getstatic 899	seventynine/sdk/SeventynineConstants:contentLanguage	Ljava/lang/String;
    //   551: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   557: ldc_w 901
    //   560: new 317	java/lang/StringBuilder
    //   563: dup
    //   564: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   567: getstatic 904	seventynine/sdk/SeventynineConstants:customVaraible	Ljava/lang/String;
    //   570: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   576: aload 4
    //   578: ldc_w 906
    //   581: aload_0
    //   582: getfield 229	seventynine/sdk/UserProfileManager:msisdn	Ljava/lang/String;
    //   585: invokevirtual 910	seventynine/sdk/Database:insertProfileData	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   588: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   591: aload_0
    //   592: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   595: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   598: invokevirtual 914	seventynine/sdk/Database:fetchMyRowid	(Landroid/content/Context;Lseventynine/sdk/UserProfile;)Ljava/lang/String;
    //   601: putstatic 121	seventynine/sdk/UserProfileManager:updatedb	Ljava/lang/String;
    //   604: getstatic 121	seventynine/sdk/UserProfileManager:updatedb	Ljava/lang/String;
    //   607: ldc_w 916
    //   610: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   613: ifeq +53 -> 666
    //   616: aload_0
    //   617: new 317	java/lang/StringBuilder
    //   620: dup
    //   621: getstatic 919	seventynine/sdk/SeventynineConstants:strProfileUrl	Ljava/lang/String;
    //   624: ldc_w 921
    //   627: ldc -125
    //   629: invokevirtual 925	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   632: invokestatic 321	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   635: invokespecial 324	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   638: invokestatic 768	java/lang/System:currentTimeMillis	()J
    //   641: invokevirtual 928	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   644: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   647: putfield 930	seventynine/sdk/UserProfileManager:urlStr	Ljava/lang/String;
    //   650: aload_3
    //   651: iconst_1
    //   652: anewarray 255	java/lang/String
    //   655: dup
    //   656: iconst_0
    //   657: aload_0
    //   658: getfield 930	seventynine/sdk/UserProfileManager:urlStr	Ljava/lang/String;
    //   661: aastore
    //   662: invokevirtual 934	seventynine/sdk/UserProfileManager$HttpAsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   665: pop
    //   666: return
    //   667: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   670: aload_0
    //   671: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   674: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   677: invokevirtual 914	seventynine/sdk/Database:fetchMyRowid	(Landroid/content/Context;Lseventynine/sdk/UserProfile;)Ljava/lang/String;
    //   680: putstatic 121	seventynine/sdk/UserProfileManager:updatedb	Ljava/lang/String;
    //   683: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   686: invokevirtual 937	seventynine/sdk/UserProfile:getDevice_id	()Ljava/lang/String;
    //   689: astore 77
    //   691: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   694: invokevirtual 940	seventynine/sdk/UserProfile:getOs_id	()Ljava/lang/String;
    //   697: astore 78
    //   699: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   702: invokevirtual 943	seventynine/sdk/UserProfile:getAdvertisement_id	()Ljava/lang/String;
    //   705: astore 76
    //   707: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   710: invokevirtual 946	seventynine/sdk/UserProfile:getDefault_account_id	()Ljava/lang/String;
    //   713: astore 75
    //   715: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   718: invokevirtual 949	seventynine/sdk/UserProfile:getWifi_mac_id	()Ljava/lang/String;
    //   721: astore 74
    //   723: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   726: invokevirtual 952	seventynine/sdk/UserProfile:getOs_name	()Ljava/lang/String;
    //   729: astore 73
    //   731: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   734: invokevirtual 955	seventynine/sdk/UserProfile:getOs_version	()Ljava/lang/String;
    //   737: astore 72
    //   739: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   742: invokevirtual 958	seventynine/sdk/UserProfile:getBrand	()Ljava/lang/String;
    //   745: astore 71
    //   747: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   750: invokevirtual 961	seventynine/sdk/UserProfile:getModel	()Ljava/lang/String;
    //   753: astore 70
    //   755: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   758: invokevirtual 964	seventynine/sdk/UserProfile:getManufacturer	()Ljava/lang/String;
    //   761: astore 69
    //   763: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   766: invokevirtual 967	seventynine/sdk/UserProfile:getApp_name	()Ljava/lang/String;
    //   769: astore 68
    //   771: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   774: invokevirtual 970	seventynine/sdk/UserProfile:getPackage_name	()Ljava/lang/String;
    //   777: astore 67
    //   779: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   782: invokevirtual 973	seventynine/sdk/UserProfile:getApi_version	()Ljava/lang/String;
    //   785: astore 66
    //   787: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   790: invokevirtual 976	seventynine/sdk/UserProfile:getSdk_version_name	()Ljava/lang/String;
    //   793: astore 65
    //   795: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   798: invokevirtual 979	seventynine/sdk/UserProfile:getLatitude	()Ljava/lang/String;
    //   801: astore 64
    //   803: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   806: invokevirtual 982	seventynine/sdk/UserProfile:getLongitude	()Ljava/lang/String;
    //   809: astore 63
    //   811: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   814: invokevirtual 985	seventynine/sdk/UserProfile:getCountry	()Ljava/lang/String;
    //   817: astore 62
    //   819: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   822: invokevirtual 988	seventynine/sdk/UserProfile:getCity	()Ljava/lang/String;
    //   825: astore 61
    //   827: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   830: invokevirtual 991	seventynine/sdk/UserProfile:getRegion	()Ljava/lang/String;
    //   833: astore 60
    //   835: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   838: invokevirtual 994	seventynine/sdk/UserProfile:getStreet	()Ljava/lang/String;
    //   841: astore 59
    //   843: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   846: invokevirtual 997	seventynine/sdk/UserProfile:getPostal_code	()Ljava/lang/String;
    //   849: astore 58
    //   851: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   854: invokevirtual 1000	seventynine/sdk/UserProfile:getPhone_no	()Ljava/lang/String;
    //   857: astore 57
    //   859: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   862: invokevirtual 1003	seventynine/sdk/UserProfile:getTelco_name	()Ljava/lang/String;
    //   865: astore 56
    //   867: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   870: invokevirtual 1006	seventynine/sdk/UserProfile:getCode	()Ljava/lang/String;
    //   873: astore 55
    //   875: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   878: invokevirtual 1009	seventynine/sdk/UserProfile:getMnc	()Ljava/lang/String;
    //   881: astore 54
    //   883: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   886: invokevirtual 1012	seventynine/sdk/UserProfile:getMcc	()Ljava/lang/String;
    //   889: astore 53
    //   891: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   894: invokevirtual 1015	seventynine/sdk/UserProfile:getImsi	()Ljava/lang/String;
    //   897: astore 52
    //   899: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   902: invokevirtual 1018	seventynine/sdk/UserProfile:getType	()Ljava/lang/String;
    //   905: astore 51
    //   907: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   910: invokevirtual 1021	seventynine/sdk/UserProfile:getSpeed	()Ljava/lang/String;
    //   913: astore 50
    //   915: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   918: invokevirtual 1024	seventynine/sdk/UserProfile:getUpdate_flag	()Ljava/lang/String;
    //   921: pop
    //   922: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   925: invokevirtual 1027	seventynine/sdk/UserProfile:getGmail_id	()Ljava/lang/String;
    //   928: astore 49
    //   930: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   933: invokevirtual 1030	seventynine/sdk/UserProfile:getGmail_name	()Ljava/lang/String;
    //   936: astore 48
    //   938: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   941: invokevirtual 1033	seventynine/sdk/UserProfile:getGmail_given_name	()Ljava/lang/String;
    //   944: astore 47
    //   946: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   949: invokevirtual 1036	seventynine/sdk/UserProfile:getGmail_family_name	()Ljava/lang/String;
    //   952: astore 46
    //   954: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   957: invokevirtual 1039	seventynine/sdk/UserProfile:getGmail_plus_link	()Ljava/lang/String;
    //   960: astore 45
    //   962: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   965: invokevirtual 1042	seventynine/sdk/UserProfile:getGmail_picture	()Ljava/lang/String;
    //   968: astore 44
    //   970: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   973: invokevirtual 1045	seventynine/sdk/UserProfile:getGmail_gender	()Ljava/lang/String;
    //   976: astore 43
    //   978: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   981: invokevirtual 1048	seventynine/sdk/UserProfile:getGmail_locale	()Ljava/lang/String;
    //   984: astore 42
    //   986: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   989: invokevirtual 1051	seventynine/sdk/UserProfile:getFb_id	()Ljava/lang/String;
    //   992: astore 41
    //   994: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   997: invokevirtual 1054	seventynine/sdk/UserProfile:getFb_birthday	()Ljava/lang/String;
    //   1000: astore 40
    //   1002: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1005: invokevirtual 1057	seventynine/sdk/UserProfile:getFb_email	()Ljava/lang/String;
    //   1008: astore 39
    //   1010: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1013: invokevirtual 1060	seventynine/sdk/UserProfile:getFb_first_name	()Ljava/lang/String;
    //   1016: astore 38
    //   1018: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1021: invokevirtual 1063	seventynine/sdk/UserProfile:getFb_gender	()Ljava/lang/String;
    //   1024: astore 37
    //   1026: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1029: invokevirtual 1066	seventynine/sdk/UserProfile:getFb_last_name	()Ljava/lang/String;
    //   1032: astore 36
    //   1034: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1037: invokevirtual 1069	seventynine/sdk/UserProfile:getFb_locale	()Ljava/lang/String;
    //   1040: astore 35
    //   1042: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1045: invokevirtual 1072	seventynine/sdk/UserProfile:getFb_name	()Ljava/lang/String;
    //   1048: astore 34
    //   1050: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1053: invokevirtual 1075	seventynine/sdk/UserProfile:getFb_updated_time	()Ljava/lang/String;
    //   1056: pop
    //   1057: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1060: invokevirtual 1078	seventynine/sdk/UserProfile:getAge	()Ljava/lang/String;
    //   1063: astore 85
    //   1065: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1068: invokevirtual 1081	seventynine/sdk/UserProfile:getDob	()Ljava/lang/String;
    //   1071: astore 84
    //   1073: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1076: invokevirtual 1084	seventynine/sdk/UserProfile:getLanguage	()Ljava/lang/String;
    //   1079: astore 83
    //   1081: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1084: invokevirtual 1087	seventynine/sdk/UserProfile:getGender	()Ljava/lang/String;
    //   1087: astore 82
    //   1089: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1092: invokevirtual 1090	seventynine/sdk/UserProfile:getEmail	()Ljava/lang/String;
    //   1095: astore 81
    //   1097: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1100: invokevirtual 1093	seventynine/sdk/UserProfile:getCompilationid	()Ljava/lang/String;
    //   1103: astore 80
    //   1105: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1108: invokevirtual 1096	seventynine/sdk/UserProfile:getContentLanguage	()Ljava/lang/String;
    //   1111: astore 79
    //   1113: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1116: invokevirtual 1099	seventynine/sdk/UserProfile:getCustom	()Ljava/lang/String;
    //   1119: astore 86
    //   1121: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   1124: invokevirtual 1102	seventynine/sdk/UserProfile:getMsisdn	()Ljava/lang/String;
    //   1127: astore 33
    //   1129: ldc -125
    //   1131: astore 5
    //   1133: ldc -121
    //   1135: astore_3
    //   1136: ldc -125
    //   1138: astore 6
    //   1140: ldc -125
    //   1142: astore 7
    //   1144: ldc -125
    //   1146: astore 8
    //   1148: ldc -125
    //   1150: astore 9
    //   1152: ldc -125
    //   1154: astore 4
    //   1156: ldc -125
    //   1158: astore 15
    //   1160: ldc -125
    //   1162: astore 10
    //   1164: ldc -125
    //   1166: astore 23
    //   1168: ldc -125
    //   1170: astore 11
    //   1172: ldc -125
    //   1174: astore 12
    //   1176: ldc -125
    //   1178: astore 13
    //   1180: ldc -125
    //   1182: astore 14
    //   1184: ldc -125
    //   1186: astore 16
    //   1188: ldc -125
    //   1190: astore 18
    //   1192: ldc -125
    //   1194: astore 20
    //   1196: ldc -125
    //   1198: astore 21
    //   1200: ldc -125
    //   1202: astore 22
    //   1204: ldc -125
    //   1206: astore 24
    //   1208: ldc -125
    //   1210: astore 25
    //   1212: ldc -125
    //   1214: astore 26
    //   1216: ldc -125
    //   1218: astore 27
    //   1220: ldc -125
    //   1222: astore 30
    //   1224: ldc -125
    //   1226: astore 32
    //   1228: ldc -125
    //   1230: astore 31
    //   1232: ldc -125
    //   1234: astore 17
    //   1236: ldc -125
    //   1238: astore 28
    //   1240: ldc -125
    //   1242: astore 29
    //   1244: ldc -125
    //   1246: astore 19
    //   1248: iconst_0
    //   1249: istore_2
    //   1250: aload_0
    //   1251: getfield 145	seventynine/sdk/UserProfileManager:android_id	Ljava/lang/String;
    //   1254: ifnull +9 -> 1263
    //   1257: aload_0
    //   1258: getfield 145	seventynine/sdk/UserProfileManager:android_id	Ljava/lang/String;
    //   1261: astore 4
    //   1263: aload_0
    //   1264: getfield 133	seventynine/sdk/UserProfileManager:AdId	Ljava/lang/String;
    //   1267: ifnull +9 -> 1276
    //   1270: aload_0
    //   1271: getfield 133	seventynine/sdk/UserProfileManager:AdId	Ljava/lang/String;
    //   1274: astore 5
    //   1276: aload_0
    //   1277: getfield 137	seventynine/sdk/UserProfileManager:osname	Ljava/lang/String;
    //   1280: ifnull +8 -> 1288
    //   1283: aload_0
    //   1284: getfield 137	seventynine/sdk/UserProfileManager:osname	Ljava/lang/String;
    //   1287: astore_3
    //   1288: aload_0
    //   1289: getfield 139	seventynine/sdk/UserProfileManager:osversion	Ljava/lang/String;
    //   1292: ifnull +9 -> 1301
    //   1295: aload_0
    //   1296: getfield 139	seventynine/sdk/UserProfileManager:osversion	Ljava/lang/String;
    //   1299: astore 6
    //   1301: aload_0
    //   1302: getfield 141	seventynine/sdk/UserProfileManager:devicebrand	Ljava/lang/String;
    //   1305: ifnull +9 -> 1314
    //   1308: aload_0
    //   1309: getfield 141	seventynine/sdk/UserProfileManager:devicebrand	Ljava/lang/String;
    //   1312: astore 7
    //   1314: aload_0
    //   1315: getfield 143	seventynine/sdk/UserProfileManager:devicemodel	Ljava/lang/String;
    //   1318: ifnull +9 -> 1327
    //   1321: aload_0
    //   1322: getfield 143	seventynine/sdk/UserProfileManager:devicemodel	Ljava/lang/String;
    //   1325: astore 8
    //   1327: aload_0
    //   1328: getfield 712	seventynine/sdk/UserProfileManager:devicemanufacturer	Ljava/lang/String;
    //   1331: ifnull +9 -> 1340
    //   1334: aload_0
    //   1335: getfield 712	seventynine/sdk/UserProfileManager:devicemanufacturer	Ljava/lang/String;
    //   1338: astore 9
    //   1340: aload_0
    //   1341: getfield 181	seventynine/sdk/UserProfileManager:DeviceId	Ljava/lang/String;
    //   1344: ifnull +9 -> 1353
    //   1347: aload_0
    //   1348: getfield 181	seventynine/sdk/UserProfileManager:DeviceId	Ljava/lang/String;
    //   1351: astore 17
    //   1353: aload_0
    //   1354: getfield 147	seventynine/sdk/UserProfileManager:macAddress	Ljava/lang/String;
    //   1357: ifnull +9 -> 1366
    //   1360: aload_0
    //   1361: getfield 147	seventynine/sdk/UserProfileManager:macAddress	Ljava/lang/String;
    //   1364: astore 15
    //   1366: aload_0
    //   1367: getfield 151	seventynine/sdk/UserProfileManager:version_name	Ljava/lang/String;
    //   1370: ifnull +9 -> 1379
    //   1373: aload_0
    //   1374: getfield 151	seventynine/sdk/UserProfileManager:version_name	Ljava/lang/String;
    //   1377: astore 10
    //   1379: aload_0
    //   1380: getfield 153	seventynine/sdk/UserProfileManager:possibleEmail	Ljava/lang/String;
    //   1383: ifnull +9 -> 1392
    //   1386: aload_0
    //   1387: getfield 153	seventynine/sdk/UserProfileManager:possibleEmail	Ljava/lang/String;
    //   1390: astore 23
    //   1392: aload_0
    //   1393: getfield 155	seventynine/sdk/UserProfileManager:latitude	Ljava/lang/String;
    //   1396: ifnull +9 -> 1405
    //   1399: aload_0
    //   1400: getfield 155	seventynine/sdk/UserProfileManager:latitude	Ljava/lang/String;
    //   1403: astore 11
    //   1405: aload_0
    //   1406: getfield 157	seventynine/sdk/UserProfileManager:longitude	Ljava/lang/String;
    //   1409: ifnull +9 -> 1418
    //   1412: aload_0
    //   1413: getfield 157	seventynine/sdk/UserProfileManager:longitude	Ljava/lang/String;
    //   1416: astore 12
    //   1418: aload_0
    //   1419: getfield 159	seventynine/sdk/UserProfileManager:country	Ljava/lang/String;
    //   1422: ifnull +9 -> 1431
    //   1425: aload_0
    //   1426: getfield 159	seventynine/sdk/UserProfileManager:country	Ljava/lang/String;
    //   1429: astore 13
    //   1431: aload_0
    //   1432: getfield 161	seventynine/sdk/UserProfileManager:city	Ljava/lang/String;
    //   1435: ifnull +9 -> 1444
    //   1438: aload_0
    //   1439: getfield 161	seventynine/sdk/UserProfileManager:city	Ljava/lang/String;
    //   1442: astore 14
    //   1444: aload_0
    //   1445: getfield 163	seventynine/sdk/UserProfileManager:region	Ljava/lang/String;
    //   1448: ifnull +9 -> 1457
    //   1451: aload_0
    //   1452: getfield 163	seventynine/sdk/UserProfileManager:region	Ljava/lang/String;
    //   1455: astore 16
    //   1457: aload_0
    //   1458: getfield 165	seventynine/sdk/UserProfileManager:street	Ljava/lang/String;
    //   1461: ifnull +9 -> 1470
    //   1464: aload_0
    //   1465: getfield 165	seventynine/sdk/UserProfileManager:street	Ljava/lang/String;
    //   1468: astore 18
    //   1470: aload_0
    //   1471: getfield 167	seventynine/sdk/UserProfileManager:postal_code	Ljava/lang/String;
    //   1474: ifnull +9 -> 1483
    //   1477: aload_0
    //   1478: getfield 167	seventynine/sdk/UserProfileManager:postal_code	Ljava/lang/String;
    //   1481: astore 20
    //   1483: aload_0
    //   1484: getfield 169	seventynine/sdk/UserProfileManager:phone_no	Ljava/lang/String;
    //   1487: ifnull +9 -> 1496
    //   1490: aload_0
    //   1491: getfield 169	seventynine/sdk/UserProfileManager:phone_no	Ljava/lang/String;
    //   1494: astore 21
    //   1496: aload_0
    //   1497: getfield 171	seventynine/sdk/UserProfileManager:telco_name	Ljava/lang/String;
    //   1500: ifnull +9 -> 1509
    //   1503: aload_0
    //   1504: getfield 171	seventynine/sdk/UserProfileManager:telco_name	Ljava/lang/String;
    //   1507: astore 22
    //   1509: aload_0
    //   1510: getfield 173	seventynine/sdk/UserProfileManager:telco_code	Ljava/lang/String;
    //   1513: ifnull +9 -> 1522
    //   1516: aload_0
    //   1517: getfield 173	seventynine/sdk/UserProfileManager:telco_code	Ljava/lang/String;
    //   1520: astore 24
    //   1522: aload_0
    //   1523: getfield 175	seventynine/sdk/UserProfileManager:mnc	Ljava/lang/String;
    //   1526: ifnull +9 -> 1535
    //   1529: aload_0
    //   1530: getfield 175	seventynine/sdk/UserProfileManager:mnc	Ljava/lang/String;
    //   1533: astore 25
    //   1535: aload_0
    //   1536: getfield 177	seventynine/sdk/UserProfileManager:mcc	Ljava/lang/String;
    //   1539: ifnull +9 -> 1548
    //   1542: aload_0
    //   1543: getfield 177	seventynine/sdk/UserProfileManager:mcc	Ljava/lang/String;
    //   1546: astore 26
    //   1548: aload_0
    //   1549: getfield 179	seventynine/sdk/UserProfileManager:imsi	Ljava/lang/String;
    //   1552: ifnull +9 -> 1561
    //   1555: aload_0
    //   1556: getfield 179	seventynine/sdk/UserProfileManager:imsi	Ljava/lang/String;
    //   1559: astore 27
    //   1561: aload_0
    //   1562: getfield 185	seventynine/sdk/UserProfileManager:appname	Ljava/lang/String;
    //   1565: ifnull +9 -> 1574
    //   1568: aload_0
    //   1569: getfield 185	seventynine/sdk/UserProfileManager:appname	Ljava/lang/String;
    //   1572: astore 30
    //   1574: aload_0
    //   1575: getfield 187	seventynine/sdk/UserProfileManager:api_version_code	Ljava/lang/String;
    //   1578: ifnull +9 -> 1587
    //   1581: aload_0
    //   1582: getfield 187	seventynine/sdk/UserProfileManager:api_version_code	Ljava/lang/String;
    //   1585: astore 31
    //   1587: aload_0
    //   1588: getfield 183	seventynine/sdk/UserProfileManager:pkgName	Ljava/lang/String;
    //   1591: ifnull +9 -> 1600
    //   1594: aload_0
    //   1595: getfield 183	seventynine/sdk/UserProfileManager:pkgName	Ljava/lang/String;
    //   1598: astore 32
    //   1600: aload_0
    //   1601: getfield 189	seventynine/sdk/UserProfileManager:Type	Ljava/lang/String;
    //   1604: ifnull +9 -> 1613
    //   1607: aload_0
    //   1608: getfield 189	seventynine/sdk/UserProfileManager:Type	Ljava/lang/String;
    //   1611: astore 28
    //   1613: aload_0
    //   1614: getfield 191	seventynine/sdk/UserProfileManager:Speed	Ljava/lang/String;
    //   1617: ifnull +9 -> 1626
    //   1620: aload_0
    //   1621: getfield 191	seventynine/sdk/UserProfileManager:Speed	Ljava/lang/String;
    //   1624: astore 29
    //   1626: aload_0
    //   1627: getfield 229	seventynine/sdk/UserProfileManager:msisdn	Ljava/lang/String;
    //   1630: ifnull +9 -> 1639
    //   1633: aload_0
    //   1634: getfield 229	seventynine/sdk/UserProfileManager:msisdn	Ljava/lang/String;
    //   1637: astore 19
    //   1639: aload_0
    //   1640: invokestatic 768	java/lang/System:currentTimeMillis	()J
    //   1643: ldc2_w 769
    //   1646: ldiv
    //   1647: invokestatic 775	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1650: putfield 777	seventynine/sdk/UserProfileManager:tsLong	Ljava/lang/Long;
    //   1653: aload_0
    //   1654: getfield 777	seventynine/sdk/UserProfileManager:tsLong	Ljava/lang/Long;
    //   1657: invokevirtual 778	java/lang/Long:toString	()Ljava/lang/String;
    //   1660: astore 87
    //   1662: getstatic 272	seventynine/sdk/SeventynineConstants:appContext	Landroid/content/Context;
    //   1665: ldc_w 1104
    //   1668: iconst_0
    //   1669: invokevirtual 1108	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1672: ldc_w 1110
    //   1675: aconst_null
    //   1676: invokeinterface 754 3 0
    //   1681: astore 88
    //   1683: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   1686: aload_0
    //   1687: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   1690: ldc_w 790
    //   1693: aload 88
    //   1695: ldc_w 916
    //   1698: ldc -125
    //   1700: new 317	java/lang/StringBuilder
    //   1703: dup
    //   1704: invokespecial 351	java/lang/StringBuilder:<init>	()V
    //   1707: invokestatic 768	java/lang/System:currentTimeMillis	()J
    //   1710: invokevirtual 928	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1713: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1716: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   1719: pop
    //   1720: iload_2
    //   1721: istore_1
    //   1722: getstatic 904	seventynine/sdk/SeventynineConstants:customVaraible	Ljava/lang/String;
    //   1725: aload 86
    //   1727: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1730: ifne +54 -> 1784
    //   1733: iload_2
    //   1734: istore_1
    //   1735: getstatic 904	seventynine/sdk/SeventynineConstants:customVaraible	Ljava/lang/String;
    //   1738: ldc -125
    //   1740: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1743: ifne +41 -> 1784
    //   1746: iload_2
    //   1747: istore_1
    //   1748: getstatic 904	seventynine/sdk/SeventynineConstants:customVaraible	Ljava/lang/String;
    //   1751: ldc_w 1116
    //   1754: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1757: ifne +27 -> 1784
    //   1760: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   1763: aload_0
    //   1764: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   1767: ldc_w 901
    //   1770: getstatic 904	seventynine/sdk/SeventynineConstants:customVaraible	Ljava/lang/String;
    //   1773: ldc_w 916
    //   1776: aload 87
    //   1778: ldc -125
    //   1780: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   1783: istore_1
    //   1784: iload_1
    //   1785: istore_2
    //   1786: getstatic 869	seventynine/sdk/SeventynineConstants:age	Ljava/lang/String;
    //   1789: aload 85
    //   1791: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1794: ifne +54 -> 1848
    //   1797: iload_1
    //   1798: istore_2
    //   1799: getstatic 869	seventynine/sdk/SeventynineConstants:age	Ljava/lang/String;
    //   1802: ldc -125
    //   1804: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1807: ifne +41 -> 1848
    //   1810: iload_1
    //   1811: istore_2
    //   1812: getstatic 869	seventynine/sdk/SeventynineConstants:age	Ljava/lang/String;
    //   1815: ldc_w 1116
    //   1818: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1821: ifne +27 -> 1848
    //   1824: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   1827: aload_0
    //   1828: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   1831: ldc_w 866
    //   1834: getstatic 869	seventynine/sdk/SeventynineConstants:age	Ljava/lang/String;
    //   1837: ldc_w 916
    //   1840: aload 87
    //   1842: ldc -125
    //   1844: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   1847: istore_2
    //   1848: iload_2
    //   1849: istore_1
    //   1850: getstatic 874	seventynine/sdk/SeventynineConstants:dob	Ljava/lang/String;
    //   1853: aload 84
    //   1855: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1858: ifne +54 -> 1912
    //   1861: iload_2
    //   1862: istore_1
    //   1863: getstatic 874	seventynine/sdk/SeventynineConstants:dob	Ljava/lang/String;
    //   1866: ldc -125
    //   1868: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1871: ifne +41 -> 1912
    //   1874: iload_2
    //   1875: istore_1
    //   1876: getstatic 874	seventynine/sdk/SeventynineConstants:dob	Ljava/lang/String;
    //   1879: ldc_w 1116
    //   1882: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1885: ifne +27 -> 1912
    //   1888: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   1891: aload_0
    //   1892: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   1895: ldc_w 871
    //   1898: getstatic 874	seventynine/sdk/SeventynineConstants:dob	Ljava/lang/String;
    //   1901: ldc_w 916
    //   1904: aload 87
    //   1906: ldc -125
    //   1908: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   1911: istore_1
    //   1912: iload_1
    //   1913: istore_2
    //   1914: getstatic 879	seventynine/sdk/SeventynineConstants:language	Ljava/lang/String;
    //   1917: aload 83
    //   1919: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1922: ifne +54 -> 1976
    //   1925: iload_1
    //   1926: istore_2
    //   1927: getstatic 879	seventynine/sdk/SeventynineConstants:language	Ljava/lang/String;
    //   1930: ldc -125
    //   1932: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1935: ifne +41 -> 1976
    //   1938: iload_1
    //   1939: istore_2
    //   1940: getstatic 879	seventynine/sdk/SeventynineConstants:language	Ljava/lang/String;
    //   1943: ldc_w 1116
    //   1946: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1949: ifne +27 -> 1976
    //   1952: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   1955: aload_0
    //   1956: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   1959: ldc_w 876
    //   1962: getstatic 879	seventynine/sdk/SeventynineConstants:language	Ljava/lang/String;
    //   1965: ldc_w 916
    //   1968: aload 87
    //   1970: ldc -125
    //   1972: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   1975: istore_2
    //   1976: iload_2
    //   1977: istore_1
    //   1978: getstatic 884	seventynine/sdk/SeventynineConstants:gender	Ljava/lang/String;
    //   1981: aload 82
    //   1983: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1986: ifne +54 -> 2040
    //   1989: iload_2
    //   1990: istore_1
    //   1991: getstatic 884	seventynine/sdk/SeventynineConstants:gender	Ljava/lang/String;
    //   1994: ldc -125
    //   1996: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1999: ifne +41 -> 2040
    //   2002: iload_2
    //   2003: istore_1
    //   2004: getstatic 884	seventynine/sdk/SeventynineConstants:gender	Ljava/lang/String;
    //   2007: ldc_w 1116
    //   2010: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2013: ifne +27 -> 2040
    //   2016: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2019: aload_0
    //   2020: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2023: ldc_w 881
    //   2026: getstatic 884	seventynine/sdk/SeventynineConstants:gender	Ljava/lang/String;
    //   2029: ldc_w 916
    //   2032: aload 87
    //   2034: ldc -125
    //   2036: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2039: istore_1
    //   2040: iload_1
    //   2041: istore_2
    //   2042: getstatic 889	seventynine/sdk/SeventynineConstants:email	Ljava/lang/String;
    //   2045: aload 81
    //   2047: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2050: ifne +54 -> 2104
    //   2053: iload_1
    //   2054: istore_2
    //   2055: getstatic 889	seventynine/sdk/SeventynineConstants:email	Ljava/lang/String;
    //   2058: ldc -125
    //   2060: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2063: ifne +41 -> 2104
    //   2066: iload_1
    //   2067: istore_2
    //   2068: getstatic 889	seventynine/sdk/SeventynineConstants:email	Ljava/lang/String;
    //   2071: ldc_w 1116
    //   2074: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2077: ifne +27 -> 2104
    //   2080: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2083: aload_0
    //   2084: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2087: ldc_w 886
    //   2090: getstatic 889	seventynine/sdk/SeventynineConstants:email	Ljava/lang/String;
    //   2093: ldc_w 916
    //   2096: aload 87
    //   2098: ldc -125
    //   2100: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2103: istore_2
    //   2104: iload_2
    //   2105: istore_1
    //   2106: getstatic 894	seventynine/sdk/SeventynineConstants:complilationId	Ljava/lang/String;
    //   2109: aload 80
    //   2111: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2114: ifne +54 -> 2168
    //   2117: iload_2
    //   2118: istore_1
    //   2119: getstatic 894	seventynine/sdk/SeventynineConstants:complilationId	Ljava/lang/String;
    //   2122: ldc -125
    //   2124: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2127: ifne +41 -> 2168
    //   2130: iload_2
    //   2131: istore_1
    //   2132: getstatic 894	seventynine/sdk/SeventynineConstants:complilationId	Ljava/lang/String;
    //   2135: ldc_w 1116
    //   2138: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2141: ifne +27 -> 2168
    //   2144: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2147: aload_0
    //   2148: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2151: ldc_w 891
    //   2154: getstatic 894	seventynine/sdk/SeventynineConstants:complilationId	Ljava/lang/String;
    //   2157: ldc_w 916
    //   2160: aload 87
    //   2162: ldc -125
    //   2164: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2167: istore_1
    //   2168: iload_1
    //   2169: istore_2
    //   2170: getstatic 899	seventynine/sdk/SeventynineConstants:contentLanguage	Ljava/lang/String;
    //   2173: aload 79
    //   2175: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2178: ifne +54 -> 2232
    //   2181: iload_1
    //   2182: istore_2
    //   2183: getstatic 899	seventynine/sdk/SeventynineConstants:contentLanguage	Ljava/lang/String;
    //   2186: ldc -125
    //   2188: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2191: ifne +41 -> 2232
    //   2194: iload_1
    //   2195: istore_2
    //   2196: getstatic 899	seventynine/sdk/SeventynineConstants:contentLanguage	Ljava/lang/String;
    //   2199: ldc_w 1116
    //   2202: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2205: ifne +27 -> 2232
    //   2208: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2211: aload_0
    //   2212: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2215: ldc_w 896
    //   2218: getstatic 899	seventynine/sdk/SeventynineConstants:contentLanguage	Ljava/lang/String;
    //   2221: ldc_w 916
    //   2224: aload 87
    //   2226: ldc -125
    //   2228: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2231: istore_2
    //   2232: iload_2
    //   2233: istore_1
    //   2234: aload 4
    //   2236: aload 78
    //   2238: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2241: ifne +45 -> 2286
    //   2244: iload_2
    //   2245: istore_1
    //   2246: aload 4
    //   2248: ldc -125
    //   2250: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2253: ifne +33 -> 2286
    //   2256: iload_2
    //   2257: istore_1
    //   2258: aload 4
    //   2260: ifnull +26 -> 2286
    //   2263: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2266: aload_0
    //   2267: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2270: ldc_w 438
    //   2273: aload 4
    //   2275: ldc_w 916
    //   2278: aload 87
    //   2280: ldc -125
    //   2282: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2285: istore_1
    //   2286: iload_1
    //   2287: istore_2
    //   2288: aload 17
    //   2290: aload 77
    //   2292: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2295: ifne +45 -> 2340
    //   2298: iload_1
    //   2299: istore_2
    //   2300: aload 17
    //   2302: ldc -125
    //   2304: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2307: ifne +33 -> 2340
    //   2310: iload_1
    //   2311: istore_2
    //   2312: aload 17
    //   2314: ifnull +26 -> 2340
    //   2317: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2320: aload_0
    //   2321: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2324: ldc_w 792
    //   2327: aload 17
    //   2329: ldc_w 916
    //   2332: aload 87
    //   2334: ldc -125
    //   2336: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2339: istore_2
    //   2340: iload_2
    //   2341: istore_1
    //   2342: aload 5
    //   2344: ldc -125
    //   2346: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2349: ifne +57 -> 2406
    //   2352: iload_2
    //   2353: istore_1
    //   2354: aload 5
    //   2356: ldc -125
    //   2358: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2361: ifne +45 -> 2406
    //   2364: iload_2
    //   2365: istore_1
    //   2366: aload 5
    //   2368: ifnull +38 -> 2406
    //   2371: iload_2
    //   2372: istore_1
    //   2373: aload 5
    //   2375: aload 76
    //   2377: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2380: ifne +26 -> 2406
    //   2383: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2386: aload_0
    //   2387: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2390: ldc_w 794
    //   2393: aload 5
    //   2395: ldc_w 916
    //   2398: aload 87
    //   2400: ldc -125
    //   2402: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2405: istore_1
    //   2406: iload_1
    //   2407: istore_2
    //   2408: aload 23
    //   2410: aload 75
    //   2412: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2415: ifne +45 -> 2460
    //   2418: iload_1
    //   2419: istore_2
    //   2420: aload 23
    //   2422: ldc -125
    //   2424: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2427: ifne +33 -> 2460
    //   2430: iload_1
    //   2431: istore_2
    //   2432: aload 23
    //   2434: ifnull +26 -> 2460
    //   2437: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2440: aload_0
    //   2441: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2444: ldc_w 796
    //   2447: aload 23
    //   2449: ldc_w 916
    //   2452: aload 87
    //   2454: ldc -125
    //   2456: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2459: istore_2
    //   2460: iload_2
    //   2461: istore_1
    //   2462: aload 15
    //   2464: aload 74
    //   2466: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2469: ifne +45 -> 2514
    //   2472: iload_2
    //   2473: istore_1
    //   2474: aload 15
    //   2476: ldc -125
    //   2478: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2481: ifne +33 -> 2514
    //   2484: iload_2
    //   2485: istore_1
    //   2486: aload 15
    //   2488: ifnull +26 -> 2514
    //   2491: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2494: aload_0
    //   2495: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2498: ldc_w 799
    //   2501: aload 15
    //   2503: ldc_w 916
    //   2506: aload 87
    //   2508: ldc -125
    //   2510: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2513: istore_1
    //   2514: iload_1
    //   2515: istore_2
    //   2516: aload_3
    //   2517: aload 73
    //   2519: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2522: ifne +42 -> 2564
    //   2525: iload_1
    //   2526: istore_2
    //   2527: aload_3
    //   2528: ldc -125
    //   2530: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2533: ifne +31 -> 2564
    //   2536: iload_1
    //   2537: istore_2
    //   2538: aload_3
    //   2539: ifnull +25 -> 2564
    //   2542: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2545: aload_0
    //   2546: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2549: ldc_w 801
    //   2552: aload_3
    //   2553: ldc_w 916
    //   2556: aload 87
    //   2558: ldc -125
    //   2560: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2563: istore_2
    //   2564: iload_2
    //   2565: istore_1
    //   2566: aload 6
    //   2568: aload 72
    //   2570: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2573: ifne +45 -> 2618
    //   2576: iload_2
    //   2577: istore_1
    //   2578: aload 6
    //   2580: ldc -125
    //   2582: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2585: ifne +33 -> 2618
    //   2588: iload_2
    //   2589: istore_1
    //   2590: aload 6
    //   2592: ifnull +26 -> 2618
    //   2595: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2598: aload_0
    //   2599: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2602: ldc_w 803
    //   2605: aload 6
    //   2607: ldc_w 916
    //   2610: aload 87
    //   2612: ldc -125
    //   2614: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2617: istore_1
    //   2618: iload_1
    //   2619: istore_2
    //   2620: aload 7
    //   2622: aload 71
    //   2624: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2627: ifne +45 -> 2672
    //   2630: iload_1
    //   2631: istore_2
    //   2632: aload 7
    //   2634: ldc -125
    //   2636: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2639: ifne +33 -> 2672
    //   2642: iload_1
    //   2643: istore_2
    //   2644: aload 7
    //   2646: ifnull +26 -> 2672
    //   2649: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2652: aload_0
    //   2653: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2656: ldc_w 805
    //   2659: aload 7
    //   2661: ldc_w 916
    //   2664: aload 87
    //   2666: ldc -125
    //   2668: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2671: istore_2
    //   2672: iload_2
    //   2673: istore_1
    //   2674: aload 8
    //   2676: aload 70
    //   2678: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2681: ifne +45 -> 2726
    //   2684: iload_2
    //   2685: istore_1
    //   2686: aload 8
    //   2688: ldc -125
    //   2690: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2693: ifne +33 -> 2726
    //   2696: iload_2
    //   2697: istore_1
    //   2698: aload 8
    //   2700: ifnull +26 -> 2726
    //   2703: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2706: aload_0
    //   2707: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2710: ldc_w 807
    //   2713: aload 8
    //   2715: ldc_w 916
    //   2718: aload 87
    //   2720: ldc -125
    //   2722: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2725: istore_1
    //   2726: iload_1
    //   2727: istore_2
    //   2728: aload 9
    //   2730: aload 69
    //   2732: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2735: ifne +45 -> 2780
    //   2738: iload_1
    //   2739: istore_2
    //   2740: aload 9
    //   2742: ldc -125
    //   2744: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2747: ifne +33 -> 2780
    //   2750: iload_1
    //   2751: istore_2
    //   2752: aload 9
    //   2754: ifnull +26 -> 2780
    //   2757: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2760: aload_0
    //   2761: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2764: ldc_w 809
    //   2767: aload 9
    //   2769: ldc_w 916
    //   2772: aload 87
    //   2774: ldc -125
    //   2776: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2779: istore_2
    //   2780: iload_2
    //   2781: istore_1
    //   2782: aload 30
    //   2784: aload 68
    //   2786: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2789: ifne +45 -> 2834
    //   2792: iload_2
    //   2793: istore_1
    //   2794: aload 30
    //   2796: ldc -125
    //   2798: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2801: ifne +33 -> 2834
    //   2804: iload_2
    //   2805: istore_1
    //   2806: aload 30
    //   2808: ifnull +26 -> 2834
    //   2811: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2814: aload_0
    //   2815: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2818: ldc_w 469
    //   2821: aload 30
    //   2823: ldc_w 916
    //   2826: aload 87
    //   2828: ldc -125
    //   2830: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2833: istore_1
    //   2834: iload_1
    //   2835: istore_2
    //   2836: aload 32
    //   2838: aload 67
    //   2840: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2843: ifne +45 -> 2888
    //   2846: iload_1
    //   2847: istore_2
    //   2848: aload 32
    //   2850: ldc -125
    //   2852: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2855: ifne +33 -> 2888
    //   2858: iload_1
    //   2859: istore_2
    //   2860: aload 32
    //   2862: ifnull +26 -> 2888
    //   2865: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2868: aload_0
    //   2869: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2872: ldc_w 811
    //   2875: aload 32
    //   2877: ldc_w 916
    //   2880: aload 87
    //   2882: ldc -125
    //   2884: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2887: istore_2
    //   2888: iload_2
    //   2889: istore_1
    //   2890: aload 31
    //   2892: aload 66
    //   2894: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2897: ifne +45 -> 2942
    //   2900: iload_2
    //   2901: istore_1
    //   2902: aload 31
    //   2904: ldc -125
    //   2906: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2909: ifne +33 -> 2942
    //   2912: iload_2
    //   2913: istore_1
    //   2914: aload 31
    //   2916: ifnull +26 -> 2942
    //   2919: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2922: aload_0
    //   2923: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2926: ldc_w 813
    //   2929: aload 31
    //   2931: ldc_w 916
    //   2934: aload 87
    //   2936: ldc -125
    //   2938: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2941: istore_1
    //   2942: iload_1
    //   2943: istore_2
    //   2944: aload 10
    //   2946: aload 65
    //   2948: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2951: ifne +45 -> 2996
    //   2954: iload_1
    //   2955: istore_2
    //   2956: aload 10
    //   2958: ldc -125
    //   2960: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2963: ifne +33 -> 2996
    //   2966: iload_1
    //   2967: istore_2
    //   2968: aload 10
    //   2970: ifnull +26 -> 2996
    //   2973: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   2976: aload_0
    //   2977: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   2980: ldc_w 815
    //   2983: aload 10
    //   2985: ldc_w 916
    //   2988: aload 87
    //   2990: ldc -125
    //   2992: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2995: istore_2
    //   2996: iload_2
    //   2997: istore_1
    //   2998: aload 11
    //   3000: aload 64
    //   3002: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3005: ifne +45 -> 3050
    //   3008: iload_2
    //   3009: istore_1
    //   3010: aload 11
    //   3012: ldc -125
    //   3014: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3017: ifne +33 -> 3050
    //   3020: iload_2
    //   3021: istore_1
    //   3022: aload 11
    //   3024: ifnull +26 -> 3050
    //   3027: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3030: aload_0
    //   3031: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3034: ldc_w 816
    //   3037: aload 11
    //   3039: ldc_w 916
    //   3042: aload 87
    //   3044: ldc -125
    //   3046: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3049: istore_1
    //   3050: iload_1
    //   3051: istore_2
    //   3052: aload 12
    //   3054: aload 63
    //   3056: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3059: ifne +45 -> 3104
    //   3062: iload_1
    //   3063: istore_2
    //   3064: aload 12
    //   3066: ldc -125
    //   3068: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3071: ifne +33 -> 3104
    //   3074: iload_1
    //   3075: istore_2
    //   3076: aload 12
    //   3078: ifnull +26 -> 3104
    //   3081: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3084: aload_0
    //   3085: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3088: ldc_w 817
    //   3091: aload 12
    //   3093: ldc_w 916
    //   3096: aload 87
    //   3098: ldc -125
    //   3100: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3103: istore_2
    //   3104: iload_2
    //   3105: istore_1
    //   3106: aload 13
    //   3108: aload 62
    //   3110: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3113: ifne +45 -> 3158
    //   3116: iload_2
    //   3117: istore_1
    //   3118: aload 13
    //   3120: ldc -125
    //   3122: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3125: ifne +33 -> 3158
    //   3128: iload_2
    //   3129: istore_1
    //   3130: aload 13
    //   3132: ifnull +26 -> 3158
    //   3135: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3138: aload_0
    //   3139: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3142: ldc_w 818
    //   3145: aload 13
    //   3147: ldc_w 916
    //   3150: aload 87
    //   3152: ldc -125
    //   3154: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3157: istore_1
    //   3158: iload_1
    //   3159: istore_2
    //   3160: aload 14
    //   3162: aload 61
    //   3164: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3167: ifne +45 -> 3212
    //   3170: iload_1
    //   3171: istore_2
    //   3172: aload 14
    //   3174: ldc -125
    //   3176: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3179: ifne +33 -> 3212
    //   3182: iload_1
    //   3183: istore_2
    //   3184: aload 14
    //   3186: ifnull +26 -> 3212
    //   3189: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3192: aload_0
    //   3193: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3196: ldc_w 819
    //   3199: aload 14
    //   3201: ldc_w 916
    //   3204: aload 87
    //   3206: ldc -125
    //   3208: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3211: istore_2
    //   3212: iload_2
    //   3213: istore_1
    //   3214: aload 16
    //   3216: aload 60
    //   3218: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3221: ifne +45 -> 3266
    //   3224: iload_2
    //   3225: istore_1
    //   3226: aload 16
    //   3228: ldc -125
    //   3230: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3233: ifne +33 -> 3266
    //   3236: iload_2
    //   3237: istore_1
    //   3238: aload 16
    //   3240: ifnull +26 -> 3266
    //   3243: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3246: aload_0
    //   3247: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3250: ldc_w 820
    //   3253: aload 16
    //   3255: ldc_w 916
    //   3258: aload 87
    //   3260: ldc -125
    //   3262: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3265: istore_1
    //   3266: iload_1
    //   3267: istore_2
    //   3268: aload 18
    //   3270: aload 59
    //   3272: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3275: ifne +45 -> 3320
    //   3278: iload_1
    //   3279: istore_2
    //   3280: aload 18
    //   3282: ldc -125
    //   3284: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3287: ifne +33 -> 3320
    //   3290: iload_1
    //   3291: istore_2
    //   3292: aload 18
    //   3294: ifnull +26 -> 3320
    //   3297: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3300: aload_0
    //   3301: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3304: ldc_w 821
    //   3307: aload 18
    //   3309: ldc_w 916
    //   3312: aload 87
    //   3314: ldc -125
    //   3316: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3319: istore_2
    //   3320: iload_2
    //   3321: istore_1
    //   3322: aload 20
    //   3324: aload 58
    //   3326: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3329: ifne +45 -> 3374
    //   3332: iload_2
    //   3333: istore_1
    //   3334: aload 20
    //   3336: ldc -125
    //   3338: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3341: ifne +33 -> 3374
    //   3344: iload_2
    //   3345: istore_1
    //   3346: aload 20
    //   3348: ifnull +26 -> 3374
    //   3351: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3354: aload_0
    //   3355: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3358: ldc_w 822
    //   3361: aload 20
    //   3363: ldc_w 916
    //   3366: aload 87
    //   3368: ldc -125
    //   3370: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3373: istore_1
    //   3374: iload_1
    //   3375: istore_2
    //   3376: aload 21
    //   3378: aload 57
    //   3380: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3383: ifne +45 -> 3428
    //   3386: iload_1
    //   3387: istore_2
    //   3388: aload 21
    //   3390: ldc -125
    //   3392: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3395: ifne +33 -> 3428
    //   3398: iload_1
    //   3399: istore_2
    //   3400: aload 21
    //   3402: ifnull +26 -> 3428
    //   3405: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3408: aload_0
    //   3409: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3412: ldc_w 823
    //   3415: aload 21
    //   3417: ldc_w 916
    //   3420: aload 87
    //   3422: ldc -125
    //   3424: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3427: istore_2
    //   3428: iload_2
    //   3429: istore_1
    //   3430: aload 22
    //   3432: aload 56
    //   3434: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3437: ifne +45 -> 3482
    //   3440: iload_2
    //   3441: istore_1
    //   3442: aload 22
    //   3444: ldc -125
    //   3446: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3449: ifne +33 -> 3482
    //   3452: iload_2
    //   3453: istore_1
    //   3454: aload 22
    //   3456: ifnull +26 -> 3482
    //   3459: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3462: aload_0
    //   3463: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3466: ldc_w 824
    //   3469: aload 22
    //   3471: ldc_w 916
    //   3474: aload 87
    //   3476: ldc -125
    //   3478: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3481: istore_1
    //   3482: iload_1
    //   3483: istore_2
    //   3484: aload 24
    //   3486: aload 55
    //   3488: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3491: ifne +45 -> 3536
    //   3494: iload_1
    //   3495: istore_2
    //   3496: aload 24
    //   3498: ldc -125
    //   3500: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3503: ifne +33 -> 3536
    //   3506: iload_1
    //   3507: istore_2
    //   3508: aload 24
    //   3510: ifnull +26 -> 3536
    //   3513: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3516: aload_0
    //   3517: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3520: ldc_w 825
    //   3523: aload 24
    //   3525: ldc_w 916
    //   3528: aload 87
    //   3530: ldc -125
    //   3532: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3535: istore_2
    //   3536: iload_2
    //   3537: istore_1
    //   3538: aload 25
    //   3540: aload 54
    //   3542: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3545: ifne +45 -> 3590
    //   3548: iload_2
    //   3549: istore_1
    //   3550: aload 25
    //   3552: ldc -125
    //   3554: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3557: ifne +33 -> 3590
    //   3560: iload_2
    //   3561: istore_1
    //   3562: aload 25
    //   3564: ifnull +26 -> 3590
    //   3567: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3570: aload_0
    //   3571: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3574: ldc_w 826
    //   3577: aload 25
    //   3579: ldc_w 916
    //   3582: aload 87
    //   3584: ldc -125
    //   3586: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3589: istore_1
    //   3590: iload_1
    //   3591: istore_2
    //   3592: aload 26
    //   3594: aload 53
    //   3596: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3599: ifne +45 -> 3644
    //   3602: iload_1
    //   3603: istore_2
    //   3604: aload 26
    //   3606: ldc -125
    //   3608: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3611: ifne +33 -> 3644
    //   3614: iload_1
    //   3615: istore_2
    //   3616: aload 26
    //   3618: ifnull +26 -> 3644
    //   3621: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3624: aload_0
    //   3625: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3628: ldc_w 827
    //   3631: aload 26
    //   3633: ldc_w 916
    //   3636: aload 87
    //   3638: ldc -125
    //   3640: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3643: istore_2
    //   3644: iload_2
    //   3645: istore_1
    //   3646: aload 27
    //   3648: aload 52
    //   3650: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3653: ifne +45 -> 3698
    //   3656: iload_2
    //   3657: istore_1
    //   3658: aload 27
    //   3660: ldc -125
    //   3662: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3665: ifne +33 -> 3698
    //   3668: iload_2
    //   3669: istore_1
    //   3670: aload 27
    //   3672: ifnull +26 -> 3698
    //   3675: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3678: aload_0
    //   3679: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3682: ldc_w 828
    //   3685: aload 27
    //   3687: ldc_w 916
    //   3690: aload 87
    //   3692: ldc -125
    //   3694: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3697: istore_1
    //   3698: iload_1
    //   3699: istore_2
    //   3700: aload 28
    //   3702: aload 51
    //   3704: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3707: ifne +45 -> 3752
    //   3710: iload_1
    //   3711: istore_2
    //   3712: aload 28
    //   3714: ldc -125
    //   3716: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3719: ifne +33 -> 3752
    //   3722: iload_1
    //   3723: istore_2
    //   3724: aload 28
    //   3726: ifnull +26 -> 3752
    //   3729: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3732: aload_0
    //   3733: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3736: ldc_w 829
    //   3739: aload 28
    //   3741: ldc_w 916
    //   3744: aload 87
    //   3746: ldc -125
    //   3748: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3751: istore_2
    //   3752: iload_2
    //   3753: istore_1
    //   3754: aload 29
    //   3756: aload 50
    //   3758: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3761: ifne +45 -> 3806
    //   3764: iload_2
    //   3765: istore_1
    //   3766: aload 29
    //   3768: ldc -125
    //   3770: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3773: ifne +33 -> 3806
    //   3776: iload_2
    //   3777: istore_1
    //   3778: aload 29
    //   3780: ifnull +26 -> 3806
    //   3783: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3786: aload_0
    //   3787: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3790: ldc_w 830
    //   3793: aload 29
    //   3795: ldc_w 916
    //   3798: aload 87
    //   3800: ldc -125
    //   3802: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3805: istore_1
    //   3806: iload_1
    //   3807: istore_2
    //   3808: aload_0
    //   3809: getfield 193	seventynine/sdk/UserProfileManager:gmailid	Ljava/lang/String;
    //   3812: aload 49
    //   3814: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3817: ifne +51 -> 3868
    //   3820: iload_1
    //   3821: istore_2
    //   3822: aload_0
    //   3823: getfield 193	seventynine/sdk/UserProfileManager:gmailid	Ljava/lang/String;
    //   3826: ldc -125
    //   3828: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3831: ifne +37 -> 3868
    //   3834: iload_1
    //   3835: istore_2
    //   3836: aload_0
    //   3837: getfield 193	seventynine/sdk/UserProfileManager:gmailid	Ljava/lang/String;
    //   3840: ifnull +28 -> 3868
    //   3843: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3846: aload_0
    //   3847: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3850: ldc_w 832
    //   3853: aload_0
    //   3854: getfield 193	seventynine/sdk/UserProfileManager:gmailid	Ljava/lang/String;
    //   3857: ldc_w 916
    //   3860: aload 87
    //   3862: ldc -125
    //   3864: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3867: istore_2
    //   3868: iload_2
    //   3869: istore_1
    //   3870: aload_0
    //   3871: getfield 195	seventynine/sdk/UserProfileManager:gmail_name	Ljava/lang/String;
    //   3874: aload 48
    //   3876: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3879: ifne +51 -> 3930
    //   3882: iload_2
    //   3883: istore_1
    //   3884: aload_0
    //   3885: getfield 195	seventynine/sdk/UserProfileManager:gmail_name	Ljava/lang/String;
    //   3888: ldc -125
    //   3890: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3893: ifne +37 -> 3930
    //   3896: iload_2
    //   3897: istore_1
    //   3898: aload_0
    //   3899: getfield 195	seventynine/sdk/UserProfileManager:gmail_name	Ljava/lang/String;
    //   3902: ifnull +28 -> 3930
    //   3905: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3908: aload_0
    //   3909: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3912: ldc_w 834
    //   3915: aload_0
    //   3916: getfield 195	seventynine/sdk/UserProfileManager:gmail_name	Ljava/lang/String;
    //   3919: ldc_w 916
    //   3922: aload 87
    //   3924: ldc -125
    //   3926: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3929: istore_1
    //   3930: iload_1
    //   3931: istore_2
    //   3932: aload_0
    //   3933: getfield 197	seventynine/sdk/UserProfileManager:gmail_given_name	Ljava/lang/String;
    //   3936: aload 47
    //   3938: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3941: ifne +51 -> 3992
    //   3944: iload_1
    //   3945: istore_2
    //   3946: aload_0
    //   3947: getfield 197	seventynine/sdk/UserProfileManager:gmail_given_name	Ljava/lang/String;
    //   3950: ldc -125
    //   3952: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3955: ifne +37 -> 3992
    //   3958: iload_1
    //   3959: istore_2
    //   3960: aload_0
    //   3961: getfield 197	seventynine/sdk/UserProfileManager:gmail_given_name	Ljava/lang/String;
    //   3964: ifnull +28 -> 3992
    //   3967: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   3970: aload_0
    //   3971: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   3974: ldc_w 836
    //   3977: aload_0
    //   3978: getfield 197	seventynine/sdk/UserProfileManager:gmail_given_name	Ljava/lang/String;
    //   3981: ldc_w 916
    //   3984: aload 87
    //   3986: ldc -125
    //   3988: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   3991: istore_2
    //   3992: iload_2
    //   3993: istore_1
    //   3994: aload_0
    //   3995: getfield 199	seventynine/sdk/UserProfileManager:gmail_family_name	Ljava/lang/String;
    //   3998: aload 46
    //   4000: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4003: ifne +51 -> 4054
    //   4006: iload_2
    //   4007: istore_1
    //   4008: aload_0
    //   4009: getfield 199	seventynine/sdk/UserProfileManager:gmail_family_name	Ljava/lang/String;
    //   4012: ldc -125
    //   4014: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4017: ifne +37 -> 4054
    //   4020: iload_2
    //   4021: istore_1
    //   4022: aload_0
    //   4023: getfield 199	seventynine/sdk/UserProfileManager:gmail_family_name	Ljava/lang/String;
    //   4026: ifnull +28 -> 4054
    //   4029: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4032: aload_0
    //   4033: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4036: ldc_w 838
    //   4039: aload_0
    //   4040: getfield 199	seventynine/sdk/UserProfileManager:gmail_family_name	Ljava/lang/String;
    //   4043: ldc_w 916
    //   4046: aload 87
    //   4048: ldc -125
    //   4050: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4053: istore_1
    //   4054: iload_1
    //   4055: istore_2
    //   4056: aload_0
    //   4057: getfield 201	seventynine/sdk/UserProfileManager:gmail_plus_link	Ljava/lang/String;
    //   4060: aload 45
    //   4062: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4065: ifne +51 -> 4116
    //   4068: iload_1
    //   4069: istore_2
    //   4070: aload_0
    //   4071: getfield 201	seventynine/sdk/UserProfileManager:gmail_plus_link	Ljava/lang/String;
    //   4074: ldc -125
    //   4076: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4079: ifne +37 -> 4116
    //   4082: iload_1
    //   4083: istore_2
    //   4084: aload_0
    //   4085: getfield 201	seventynine/sdk/UserProfileManager:gmail_plus_link	Ljava/lang/String;
    //   4088: ifnull +28 -> 4116
    //   4091: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4094: aload_0
    //   4095: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4098: ldc_w 840
    //   4101: aload_0
    //   4102: getfield 201	seventynine/sdk/UserProfileManager:gmail_plus_link	Ljava/lang/String;
    //   4105: ldc_w 916
    //   4108: aload 87
    //   4110: ldc -125
    //   4112: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4115: istore_2
    //   4116: iload_2
    //   4117: istore_1
    //   4118: aload_0
    //   4119: getfield 203	seventynine/sdk/UserProfileManager:gmail_picture	Ljava/lang/String;
    //   4122: aload 44
    //   4124: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4127: ifne +51 -> 4178
    //   4130: iload_2
    //   4131: istore_1
    //   4132: aload_0
    //   4133: getfield 203	seventynine/sdk/UserProfileManager:gmail_picture	Ljava/lang/String;
    //   4136: ldc -125
    //   4138: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4141: ifne +37 -> 4178
    //   4144: iload_2
    //   4145: istore_1
    //   4146: aload_0
    //   4147: getfield 203	seventynine/sdk/UserProfileManager:gmail_picture	Ljava/lang/String;
    //   4150: ifnull +28 -> 4178
    //   4153: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4156: aload_0
    //   4157: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4160: ldc_w 842
    //   4163: aload_0
    //   4164: getfield 203	seventynine/sdk/UserProfileManager:gmail_picture	Ljava/lang/String;
    //   4167: ldc_w 916
    //   4170: aload 87
    //   4172: ldc -125
    //   4174: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4177: istore_1
    //   4178: iload_1
    //   4179: istore_2
    //   4180: aload_0
    //   4181: getfield 205	seventynine/sdk/UserProfileManager:gmail_gender	Ljava/lang/String;
    //   4184: aload 43
    //   4186: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4189: ifne +51 -> 4240
    //   4192: iload_1
    //   4193: istore_2
    //   4194: aload_0
    //   4195: getfield 205	seventynine/sdk/UserProfileManager:gmail_gender	Ljava/lang/String;
    //   4198: ldc -125
    //   4200: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4203: ifne +37 -> 4240
    //   4206: iload_1
    //   4207: istore_2
    //   4208: aload_0
    //   4209: getfield 205	seventynine/sdk/UserProfileManager:gmail_gender	Ljava/lang/String;
    //   4212: ifnull +28 -> 4240
    //   4215: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4218: aload_0
    //   4219: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4222: ldc_w 844
    //   4225: aload_0
    //   4226: getfield 205	seventynine/sdk/UserProfileManager:gmail_gender	Ljava/lang/String;
    //   4229: ldc_w 916
    //   4232: aload 87
    //   4234: ldc -125
    //   4236: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4239: istore_2
    //   4240: iload_2
    //   4241: istore_1
    //   4242: aload_0
    //   4243: getfield 207	seventynine/sdk/UserProfileManager:gmail_locale	Ljava/lang/String;
    //   4246: aload 42
    //   4248: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4251: ifne +51 -> 4302
    //   4254: iload_2
    //   4255: istore_1
    //   4256: aload_0
    //   4257: getfield 207	seventynine/sdk/UserProfileManager:gmail_locale	Ljava/lang/String;
    //   4260: ldc -125
    //   4262: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4265: ifne +37 -> 4302
    //   4268: iload_2
    //   4269: istore_1
    //   4270: aload_0
    //   4271: getfield 207	seventynine/sdk/UserProfileManager:gmail_locale	Ljava/lang/String;
    //   4274: ifnull +28 -> 4302
    //   4277: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4280: aload_0
    //   4281: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4284: ldc_w 846
    //   4287: aload_0
    //   4288: getfield 207	seventynine/sdk/UserProfileManager:gmail_locale	Ljava/lang/String;
    //   4291: ldc_w 916
    //   4294: aload 87
    //   4296: ldc -125
    //   4298: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4301: istore_1
    //   4302: iload_1
    //   4303: istore_2
    //   4304: aload_0
    //   4305: getfield 209	seventynine/sdk/UserProfileManager:fb_id	Ljava/lang/String;
    //   4308: aload 41
    //   4310: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4313: ifne +51 -> 4364
    //   4316: iload_1
    //   4317: istore_2
    //   4318: aload_0
    //   4319: getfield 209	seventynine/sdk/UserProfileManager:fb_id	Ljava/lang/String;
    //   4322: ldc -125
    //   4324: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4327: ifne +37 -> 4364
    //   4330: iload_1
    //   4331: istore_2
    //   4332: aload_0
    //   4333: getfield 209	seventynine/sdk/UserProfileManager:fb_id	Ljava/lang/String;
    //   4336: ifnull +28 -> 4364
    //   4339: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4342: aload_0
    //   4343: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4346: ldc_w 848
    //   4349: aload_0
    //   4350: getfield 209	seventynine/sdk/UserProfileManager:fb_id	Ljava/lang/String;
    //   4353: ldc_w 916
    //   4356: aload 87
    //   4358: ldc -125
    //   4360: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4363: istore_2
    //   4364: iload_2
    //   4365: istore_1
    //   4366: aload_0
    //   4367: getfield 211	seventynine/sdk/UserProfileManager:fb_birthday	Ljava/lang/String;
    //   4370: aload 40
    //   4372: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4375: ifne +51 -> 4426
    //   4378: iload_2
    //   4379: istore_1
    //   4380: aload_0
    //   4381: getfield 211	seventynine/sdk/UserProfileManager:fb_birthday	Ljava/lang/String;
    //   4384: ldc -125
    //   4386: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4389: ifne +37 -> 4426
    //   4392: iload_2
    //   4393: istore_1
    //   4394: aload_0
    //   4395: getfield 211	seventynine/sdk/UserProfileManager:fb_birthday	Ljava/lang/String;
    //   4398: ifnull +28 -> 4426
    //   4401: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4404: aload_0
    //   4405: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4408: ldc_w 850
    //   4411: aload_0
    //   4412: getfield 211	seventynine/sdk/UserProfileManager:fb_birthday	Ljava/lang/String;
    //   4415: ldc_w 916
    //   4418: aload 87
    //   4420: ldc -125
    //   4422: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4425: istore_1
    //   4426: iload_1
    //   4427: istore_2
    //   4428: aload_0
    //   4429: getfield 213	seventynine/sdk/UserProfileManager:fb_email	Ljava/lang/String;
    //   4432: aload 39
    //   4434: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4437: ifne +51 -> 4488
    //   4440: iload_1
    //   4441: istore_2
    //   4442: aload_0
    //   4443: getfield 213	seventynine/sdk/UserProfileManager:fb_email	Ljava/lang/String;
    //   4446: ldc -125
    //   4448: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4451: ifne +37 -> 4488
    //   4454: iload_1
    //   4455: istore_2
    //   4456: aload_0
    //   4457: getfield 213	seventynine/sdk/UserProfileManager:fb_email	Ljava/lang/String;
    //   4460: ifnull +28 -> 4488
    //   4463: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4466: aload_0
    //   4467: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4470: ldc_w 852
    //   4473: aload_0
    //   4474: getfield 213	seventynine/sdk/UserProfileManager:fb_email	Ljava/lang/String;
    //   4477: ldc_w 916
    //   4480: aload 87
    //   4482: ldc -125
    //   4484: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4487: istore_2
    //   4488: iload_2
    //   4489: istore_1
    //   4490: aload_0
    //   4491: getfield 215	seventynine/sdk/UserProfileManager:fb_first_name	Ljava/lang/String;
    //   4494: aload 38
    //   4496: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4499: ifne +51 -> 4550
    //   4502: iload_2
    //   4503: istore_1
    //   4504: aload_0
    //   4505: getfield 215	seventynine/sdk/UserProfileManager:fb_first_name	Ljava/lang/String;
    //   4508: ldc -125
    //   4510: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4513: ifne +37 -> 4550
    //   4516: iload_2
    //   4517: istore_1
    //   4518: aload_0
    //   4519: getfield 215	seventynine/sdk/UserProfileManager:fb_first_name	Ljava/lang/String;
    //   4522: ifnull +28 -> 4550
    //   4525: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4528: aload_0
    //   4529: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4532: ldc_w 854
    //   4535: aload_0
    //   4536: getfield 215	seventynine/sdk/UserProfileManager:fb_first_name	Ljava/lang/String;
    //   4539: ldc_w 916
    //   4542: aload 87
    //   4544: ldc -125
    //   4546: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4549: istore_1
    //   4550: iload_1
    //   4551: istore_2
    //   4552: aload_0
    //   4553: getfield 217	seventynine/sdk/UserProfileManager:fb_gender	Ljava/lang/String;
    //   4556: aload 37
    //   4558: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4561: ifne +51 -> 4612
    //   4564: iload_1
    //   4565: istore_2
    //   4566: aload_0
    //   4567: getfield 217	seventynine/sdk/UserProfileManager:fb_gender	Ljava/lang/String;
    //   4570: ldc -125
    //   4572: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4575: ifne +37 -> 4612
    //   4578: iload_1
    //   4579: istore_2
    //   4580: aload_0
    //   4581: getfield 217	seventynine/sdk/UserProfileManager:fb_gender	Ljava/lang/String;
    //   4584: ifnull +28 -> 4612
    //   4587: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4590: aload_0
    //   4591: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4594: ldc_w 856
    //   4597: aload_0
    //   4598: getfield 217	seventynine/sdk/UserProfileManager:fb_gender	Ljava/lang/String;
    //   4601: ldc_w 916
    //   4604: aload 87
    //   4606: ldc -125
    //   4608: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4611: istore_2
    //   4612: iload_2
    //   4613: istore_1
    //   4614: aload_0
    //   4615: getfield 219	seventynine/sdk/UserProfileManager:fb_last_name	Ljava/lang/String;
    //   4618: aload 36
    //   4620: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4623: ifne +51 -> 4674
    //   4626: iload_2
    //   4627: istore_1
    //   4628: aload_0
    //   4629: getfield 219	seventynine/sdk/UserProfileManager:fb_last_name	Ljava/lang/String;
    //   4632: ldc -125
    //   4634: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4637: ifne +37 -> 4674
    //   4640: iload_2
    //   4641: istore_1
    //   4642: aload_0
    //   4643: getfield 219	seventynine/sdk/UserProfileManager:fb_last_name	Ljava/lang/String;
    //   4646: ifnull +28 -> 4674
    //   4649: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4652: aload_0
    //   4653: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4656: ldc_w 858
    //   4659: aload_0
    //   4660: getfield 219	seventynine/sdk/UserProfileManager:fb_last_name	Ljava/lang/String;
    //   4663: ldc_w 916
    //   4666: aload 87
    //   4668: ldc -125
    //   4670: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4673: istore_1
    //   4674: iload_1
    //   4675: istore_2
    //   4676: aload_0
    //   4677: getfield 221	seventynine/sdk/UserProfileManager:fb_locale	Ljava/lang/String;
    //   4680: aload 35
    //   4682: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4685: ifne +51 -> 4736
    //   4688: iload_1
    //   4689: istore_2
    //   4690: aload_0
    //   4691: getfield 221	seventynine/sdk/UserProfileManager:fb_locale	Ljava/lang/String;
    //   4694: ldc -125
    //   4696: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4699: ifne +37 -> 4736
    //   4702: iload_1
    //   4703: istore_2
    //   4704: aload_0
    //   4705: getfield 221	seventynine/sdk/UserProfileManager:fb_locale	Ljava/lang/String;
    //   4708: ifnull +28 -> 4736
    //   4711: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4714: aload_0
    //   4715: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4718: ldc_w 860
    //   4721: aload_0
    //   4722: getfield 221	seventynine/sdk/UserProfileManager:fb_locale	Ljava/lang/String;
    //   4725: ldc_w 916
    //   4728: aload 87
    //   4730: ldc -125
    //   4732: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4735: istore_2
    //   4736: iload_2
    //   4737: istore_1
    //   4738: aload_0
    //   4739: getfield 223	seventynine/sdk/UserProfileManager:fb_name	Ljava/lang/String;
    //   4742: aload 34
    //   4744: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4747: ifne +51 -> 4798
    //   4750: iload_2
    //   4751: istore_1
    //   4752: aload_0
    //   4753: getfield 223	seventynine/sdk/UserProfileManager:fb_name	Ljava/lang/String;
    //   4756: ldc -125
    //   4758: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4761: ifne +37 -> 4798
    //   4764: iload_2
    //   4765: istore_1
    //   4766: aload_0
    //   4767: getfield 223	seventynine/sdk/UserProfileManager:fb_name	Ljava/lang/String;
    //   4770: ifnull +28 -> 4798
    //   4773: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4776: aload_0
    //   4777: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4780: ldc_w 862
    //   4783: aload_0
    //   4784: getfield 223	seventynine/sdk/UserProfileManager:fb_name	Ljava/lang/String;
    //   4787: ldc_w 916
    //   4790: aload 87
    //   4792: ldc -125
    //   4794: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4797: istore_1
    //   4798: iload_1
    //   4799: istore_2
    //   4800: aload 19
    //   4802: aload 33
    //   4804: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4807: ifne +45 -> 4852
    //   4810: iload_1
    //   4811: istore_2
    //   4812: aload 19
    //   4814: ldc -125
    //   4816: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4819: ifne +33 -> 4852
    //   4822: iload_1
    //   4823: istore_2
    //   4824: aload 19
    //   4826: ifnull +26 -> 4852
    //   4829: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4832: aload_0
    //   4833: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4836: ldc_w 906
    //   4839: aload 19
    //   4841: ldc_w 916
    //   4844: aload 87
    //   4846: ldc -125
    //   4848: invokevirtual 1114	seventynine/sdk/Database:UpdateConfigurationTableNewStructure	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   4851: istore_2
    //   4852: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4855: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   4858: invokevirtual 1120	seventynine/sdk/Database:fetchAppListFlagtrue	(Lseventynine/sdk/UserProfile;)Ljava/lang/String;
    //   4861: astore 4
    //   4863: aload 4
    //   4865: astore_3
    //   4866: getstatic 559	seventynine/sdk/SeventynineConstants:isSendAppList	Z
    //   4869: ifeq +19 -> 4888
    //   4872: aload 4
    //   4874: astore_3
    //   4875: getstatic 126	seventynine/sdk/UserProfileManager:arrayAppList	Ljava/util/ArrayList;
    //   4878: invokevirtual 578	java/util/ArrayList:size	()I
    //   4881: ifle +7 -> 4888
    //   4884: ldc_w 916
    //   4887: astore_3
    //   4888: iconst_1
    //   4889: putstatic 1125	seventynine/sdk/SeventynineAdSDK:startProfileThread	Z
    //   4892: iload_2
    //   4893: ifne +13 -> 4906
    //   4896: aload_3
    //   4897: ldc_w 916
    //   4900: invokevirtual 719	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   4903: ifeq +126 -> 5029
    //   4906: invokestatic 681	seventynine/sdk/Database:getInstance	()Lseventynine/sdk/Database;
    //   4909: aload_0
    //   4910: getfield 286	seventynine/sdk/UserProfileManager:con	Landroid/content/Context;
    //   4913: getstatic 117	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
    //   4916: invokevirtual 914	seventynine/sdk/Database:fetchMyRowid	(Landroid/content/Context;Lseventynine/sdk/UserProfile;)Ljava/lang/String;
    //   4919: putstatic 121	seventynine/sdk/UserProfileManager:updatedb	Ljava/lang/String;
    //   4922: aload_0
    //   4923: new 317	java/lang/StringBuilder
    //   4926: dup
    //   4927: getstatic 919	seventynine/sdk/SeventynineConstants:strProfileUrl	Ljava/lang/String;
    //   4930: ldc_w 921
    //   4933: ldc -125
    //   4935: invokevirtual 925	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   4938: invokestatic 321	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   4941: invokespecial 324	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   4944: invokestatic 768	java/lang/System:currentTimeMillis	()J
    //   4947: invokevirtual 928	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   4950: invokevirtual 331	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4953: putfield 930	seventynine/sdk/UserProfileManager:urlStr	Ljava/lang/String;
    //   4956: new 15	seventynine/sdk/UserProfileManager$HttpAsyncTask
    //   4959: dup
    //   4960: aload_0
    //   4961: aconst_null
    //   4962: invokespecial 759	seventynine/sdk/UserProfileManager$HttpAsyncTask:<init>	(Lseventynine/sdk/UserProfileManager;Lseventynine/sdk/UserProfileManager$HttpAsyncTask;)V
    //   4965: iconst_1
    //   4966: anewarray 255	java/lang/String
    //   4969: dup
    //   4970: iconst_0
    //   4971: aload_0
    //   4972: getfield 930	seventynine/sdk/UserProfileManager:urlStr	Ljava/lang/String;
    //   4975: aastore
    //   4976: invokevirtual 934	seventynine/sdk/UserProfileManager$HttpAsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   4979: pop
    //   4980: return
    //   4981: astore_3
    //   4982: aload_0
    //   4983: iconst_1
    //   4984: putfield 267	seventynine/sdk/UserProfileManager:flagCollectionData	Z
    //   4987: return
    //   4988: astore_3
    //   4989: aload_3
    //   4990: invokevirtual 332	java/lang/Exception:toString	()Ljava/lang/String;
    //   4993: ldc_w 1127
    //   4996: ldc -125
    //   4998: ldc -125
    //   5000: ldc -125
    //   5002: ldc -125
    //   5004: ldc_w 336
    //   5007: iconst_0
    //   5008: invokestatic 342	seventynine/sdk/DebugTrack:SendExceptionReport	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   5011: ldc_w 344
    //   5014: aload_3
    //   5015: invokevirtual 332	java/lang/Exception:toString	()Ljava/lang/String;
    //   5018: ldc_w 336
    //   5021: iconst_0
    //   5022: ldc_w 1127
    //   5025: invokestatic 348	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   5028: return
    //   5029: aload_0
    //   5030: iconst_1
    //   5031: putfield 267	seventynine/sdk/UserProfileManager:flagCollectionData	Z
    //   5034: return
    //   5035: astore_3
    //   5036: iload_1
    //   5037: istore_2
    //   5038: goto -186 -> 4852
    //   5041: astore_3
    //   5042: iload_2
    //   5043: istore_1
    //   5044: goto -246 -> 4798
    //   5047: astore_3
    //   5048: iload_1
    //   5049: istore_2
    //   5050: goto -314 -> 4736
    //   5053: astore_3
    //   5054: iload_2
    //   5055: istore_1
    //   5056: goto -382 -> 4674
    //   5059: astore_3
    //   5060: iload_1
    //   5061: istore_2
    //   5062: goto -450 -> 4612
    //   5065: astore_3
    //   5066: iload_2
    //   5067: istore_1
    //   5068: goto -518 -> 4550
    //   5071: astore_3
    //   5072: iload_1
    //   5073: istore_2
    //   5074: goto -586 -> 4488
    //   5077: astore_3
    //   5078: iload_2
    //   5079: istore_1
    //   5080: goto -654 -> 4426
    //   5083: astore_3
    //   5084: iload_1
    //   5085: istore_2
    //   5086: goto -722 -> 4364
    //   5089: astore_3
    //   5090: iload_2
    //   5091: istore_1
    //   5092: goto -790 -> 4302
    //   5095: astore_3
    //   5096: iload_1
    //   5097: istore_2
    //   5098: goto -858 -> 4240
    //   5101: astore_3
    //   5102: iload_2
    //   5103: istore_1
    //   5104: goto -926 -> 4178
    //   5107: astore_3
    //   5108: iload_1
    //   5109: istore_2
    //   5110: goto -994 -> 4116
    //   5113: astore_3
    //   5114: iload_2
    //   5115: istore_1
    //   5116: goto -1062 -> 4054
    //   5119: astore_3
    //   5120: iload_1
    //   5121: istore_2
    //   5122: goto -1130 -> 3992
    //   5125: astore_3
    //   5126: iload_2
    //   5127: istore_1
    //   5128: goto -1198 -> 3930
    //   5131: astore_3
    //   5132: iload_1
    //   5133: istore_2
    //   5134: goto -1266 -> 3868
    //   5137: astore_3
    //   5138: iload_2
    //   5139: istore_1
    //   5140: goto -1334 -> 3806
    //   5143: astore_3
    //   5144: iload_1
    //   5145: istore_2
    //   5146: goto -1394 -> 3752
    //   5149: astore_3
    //   5150: iload_2
    //   5151: istore_1
    //   5152: goto -1454 -> 3698
    //   5155: astore_3
    //   5156: iload_1
    //   5157: istore_2
    //   5158: goto -1514 -> 3644
    //   5161: astore_3
    //   5162: iload_2
    //   5163: istore_1
    //   5164: goto -1574 -> 3590
    //   5167: astore_3
    //   5168: iload_1
    //   5169: istore_2
    //   5170: goto -1634 -> 3536
    //   5173: astore_3
    //   5174: iload_2
    //   5175: istore_1
    //   5176: goto -1694 -> 3482
    //   5179: astore_3
    //   5180: iload_1
    //   5181: istore_2
    //   5182: goto -1754 -> 3428
    //   5185: astore_3
    //   5186: iload_2
    //   5187: istore_1
    //   5188: goto -1814 -> 3374
    //   5191: astore_3
    //   5192: iload_1
    //   5193: istore_2
    //   5194: goto -1874 -> 3320
    //   5197: astore_3
    //   5198: iload_2
    //   5199: istore_1
    //   5200: goto -1934 -> 3266
    //   5203: astore_3
    //   5204: iload_1
    //   5205: istore_2
    //   5206: goto -1994 -> 3212
    //   5209: astore_3
    //   5210: iload_2
    //   5211: istore_1
    //   5212: goto -2054 -> 3158
    //   5215: astore_3
    //   5216: iload_1
    //   5217: istore_2
    //   5218: goto -2114 -> 3104
    //   5221: astore_3
    //   5222: iload_2
    //   5223: istore_1
    //   5224: goto -2174 -> 3050
    //   5227: astore_3
    //   5228: iload_1
    //   5229: istore_2
    //   5230: goto -2234 -> 2996
    //   5233: astore_3
    //   5234: iload_2
    //   5235: istore_1
    //   5236: goto -2294 -> 2942
    //   5239: astore_3
    //   5240: iload_1
    //   5241: istore_2
    //   5242: goto -2354 -> 2888
    //   5245: astore_3
    //   5246: iload_2
    //   5247: istore_1
    //   5248: goto -2414 -> 2834
    //   5251: astore_3
    //   5252: iload_1
    //   5253: istore_2
    //   5254: goto -2474 -> 2780
    //   5257: astore_3
    //   5258: iload_2
    //   5259: istore_1
    //   5260: goto -2534 -> 2726
    //   5263: astore_3
    //   5264: iload_1
    //   5265: istore_2
    //   5266: goto -2594 -> 2672
    //   5269: astore_3
    //   5270: iload_2
    //   5271: istore_1
    //   5272: goto -2654 -> 2618
    //   5275: astore_3
    //   5276: iload_1
    //   5277: istore_2
    //   5278: goto -2714 -> 2564
    //   5281: astore 4
    //   5283: iload_2
    //   5284: istore_1
    //   5285: goto -2771 -> 2514
    //   5288: astore 4
    //   5290: iload_1
    //   5291: istore_2
    //   5292: goto -2832 -> 2460
    //   5295: astore 4
    //   5297: iload_2
    //   5298: istore_1
    //   5299: goto -2893 -> 2406
    //   5302: astore 4
    //   5304: iload_1
    //   5305: istore_2
    //   5306: goto -2966 -> 2340
    //   5309: astore 4
    //   5311: iload_2
    //   5312: istore_1
    //   5313: goto -3027 -> 2286
    //   5316: astore 79
    //   5318: iload_1
    //   5319: istore_2
    //   5320: goto -3088 -> 2232
    //   5323: astore 80
    //   5325: iload_2
    //   5326: istore_1
    //   5327: goto -3159 -> 2168
    //   5330: astore 81
    //   5332: iload_1
    //   5333: istore_2
    //   5334: goto -3230 -> 2104
    //   5337: astore 82
    //   5339: iload_2
    //   5340: istore_1
    //   5341: goto -3301 -> 2040
    //   5344: astore 83
    //   5346: iload_1
    //   5347: istore_2
    //   5348: goto -3372 -> 1976
    //   5351: astore 84
    //   5353: iload_2
    //   5354: istore_1
    //   5355: goto -3443 -> 1912
    //   5358: astore 85
    //   5360: iload_1
    //   5361: istore_2
    //   5362: goto -3514 -> 1848
    //   5365: astore 86
    //   5367: iload_2
    //   5368: istore_1
    //   5369: goto -3585 -> 1784
    //   5372: astore 88
    //   5374: goto -3654 -> 1720
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	5377	0	this	UserProfileManager
    //   1721	3648	1	bool1	boolean
    //   1249	4119	2	bool2	boolean
    //   46	4851	3	localObject	Object
    //   4981	1	3	localException1	Exception
    //   4988	27	3	localException2	Exception
    //   5035	1	3	localException3	Exception
    //   5041	1	3	localException4	Exception
    //   5047	1	3	localException5	Exception
    //   5053	1	3	localException6	Exception
    //   5059	1	3	localException7	Exception
    //   5065	1	3	localException8	Exception
    //   5071	1	3	localException9	Exception
    //   5077	1	3	localException10	Exception
    //   5083	1	3	localException11	Exception
    //   5089	1	3	localException12	Exception
    //   5095	1	3	localException13	Exception
    //   5101	1	3	localException14	Exception
    //   5107	1	3	localException15	Exception
    //   5113	1	3	localException16	Exception
    //   5119	1	3	localException17	Exception
    //   5125	1	3	localException18	Exception
    //   5131	1	3	localException19	Exception
    //   5137	1	3	localException20	Exception
    //   5143	1	3	localException21	Exception
    //   5149	1	3	localException22	Exception
    //   5155	1	3	localException23	Exception
    //   5161	1	3	localException24	Exception
    //   5167	1	3	localException25	Exception
    //   5173	1	3	localException26	Exception
    //   5179	1	3	localException27	Exception
    //   5185	1	3	localException28	Exception
    //   5191	1	3	localException29	Exception
    //   5197	1	3	localException30	Exception
    //   5203	1	3	localException31	Exception
    //   5209	1	3	localException32	Exception
    //   5215	1	3	localException33	Exception
    //   5221	1	3	localException34	Exception
    //   5227	1	3	localException35	Exception
    //   5233	1	3	localException36	Exception
    //   5239	1	3	localException37	Exception
    //   5245	1	3	localException38	Exception
    //   5251	1	3	localException39	Exception
    //   5257	1	3	localException40	Exception
    //   5263	1	3	localException41	Exception
    //   5269	1	3	localException42	Exception
    //   5275	1	3	localException43	Exception
    //   78	4795	4	str1	String
    //   5281	1	4	localException44	Exception
    //   5288	1	4	localException45	Exception
    //   5295	1	4	localException46	Exception
    //   5302	1	4	localException47	Exception
    //   5309	1	4	localException48	Exception
    //   1131	1263	5	str2	String
    //   1138	1468	6	str3	String
    //   1142	1518	7	str4	String
    //   1146	1568	8	str5	String
    //   1150	1618	9	str6	String
    //   1162	1822	10	str7	String
    //   1170	1868	11	str8	String
    //   1174	1918	12	str9	String
    //   1178	1968	13	str10	String
    //   1182	2018	14	str11	String
    //   1158	1344	15	str12	String
    //   1186	2068	16	str13	String
    //   1234	1094	17	str14	String
    //   1190	2118	18	str15	String
    //   1246	3594	19	str16	String
    //   1194	2168	20	str17	String
    //   1198	2218	21	str18	String
    //   1202	2268	22	str19	String
    //   1166	1282	23	str20	String
    //   1206	2318	24	str21	String
    //   1210	2368	25	str22	String
    //   1214	2418	26	str23	String
    //   1218	2468	27	str24	String
    //   1238	2502	28	str25	String
    //   1242	2552	29	str26	String
    //   1222	1600	30	str27	String
    //   1230	1700	31	str28	String
    //   1226	1650	32	str29	String
    //   1127	3676	33	str30	String
    //   1048	3695	34	str31	String
    //   1040	3641	35	str32	String
    //   1032	3587	36	str33	String
    //   1024	3533	37	str34	String
    //   1016	3479	38	str35	String
    //   1008	3425	39	str36	String
    //   1000	3371	40	str37	String
    //   992	3317	41	str38	String
    //   984	3263	42	str39	String
    //   976	3209	43	str40	String
    //   968	3155	44	str41	String
    //   960	3101	45	str42	String
    //   952	3047	46	str43	String
    //   944	2993	47	str44	String
    //   936	2939	48	str45	String
    //   928	2885	49	str46	String
    //   913	2844	50	str47	String
    //   905	2798	51	str48	String
    //   897	2752	52	str49	String
    //   889	2706	53	str50	String
    //   881	2660	54	str51	String
    //   873	2614	55	str52	String
    //   865	2568	56	str53	String
    //   857	2522	57	str54	String
    //   849	2476	58	str55	String
    //   841	2430	59	str56	String
    //   833	2384	60	str57	String
    //   825	2338	61	str58	String
    //   817	2292	62	str59	String
    //   809	2246	63	str60	String
    //   801	2200	64	str61	String
    //   793	2154	65	str62	String
    //   785	2108	66	str63	String
    //   777	2062	67	str64	String
    //   769	2016	68	str65	String
    //   761	1970	69	str66	String
    //   753	1924	70	str67	String
    //   745	1878	71	str68	String
    //   737	1832	72	str69	String
    //   729	1789	73	str70	String
    //   721	1744	74	str71	String
    //   713	1698	75	str72	String
    //   705	1671	76	str73	String
    //   689	1602	77	str74	String
    //   697	1540	78	str75	String
    //   1111	1063	79	str76	String
    //   5316	1	79	localException49	Exception
    //   1103	1007	80	str77	String
    //   5323	1	80	localException50	Exception
    //   1095	951	81	str78	String
    //   5330	1	81	localException51	Exception
    //   1087	895	82	str79	String
    //   5337	1	82	localException52	Exception
    //   1079	839	83	str80	String
    //   5344	1	83	localException53	Exception
    //   1071	783	84	str81	String
    //   5351	1	84	localException54	Exception
    //   1063	727	85	str82	String
    //   5358	1	85	localException55	Exception
    //   1119	607	86	str83	String
    //   5365	1	86	localException56	Exception
    //   1660	3185	87	str84	String
    //   1681	13	88	str85	String
    //   5372	1	88	localException57	Exception
    // Exception table:
    //   from	to	target	type
    //   667	1129	4981	java/lang/Exception
    //   1250	1263	4981	java/lang/Exception
    //   1263	1276	4981	java/lang/Exception
    //   1276	1288	4981	java/lang/Exception
    //   1288	1301	4981	java/lang/Exception
    //   1301	1314	4981	java/lang/Exception
    //   1314	1327	4981	java/lang/Exception
    //   1327	1340	4981	java/lang/Exception
    //   1340	1353	4981	java/lang/Exception
    //   1353	1366	4981	java/lang/Exception
    //   1366	1379	4981	java/lang/Exception
    //   1379	1392	4981	java/lang/Exception
    //   1392	1405	4981	java/lang/Exception
    //   1405	1418	4981	java/lang/Exception
    //   1418	1431	4981	java/lang/Exception
    //   1431	1444	4981	java/lang/Exception
    //   1444	1457	4981	java/lang/Exception
    //   1457	1470	4981	java/lang/Exception
    //   1470	1483	4981	java/lang/Exception
    //   1483	1496	4981	java/lang/Exception
    //   1496	1509	4981	java/lang/Exception
    //   1509	1522	4981	java/lang/Exception
    //   1522	1535	4981	java/lang/Exception
    //   1535	1548	4981	java/lang/Exception
    //   1548	1561	4981	java/lang/Exception
    //   1561	1574	4981	java/lang/Exception
    //   1574	1587	4981	java/lang/Exception
    //   1587	1600	4981	java/lang/Exception
    //   1600	1613	4981	java/lang/Exception
    //   1613	1626	4981	java/lang/Exception
    //   1626	1639	4981	java/lang/Exception
    //   1639	1662	4981	java/lang/Exception
    //   4852	4863	4981	java/lang/Exception
    //   4866	4872	4981	java/lang/Exception
    //   4875	4884	4981	java/lang/Exception
    //   4888	4892	4981	java/lang/Exception
    //   4896	4906	4981	java/lang/Exception
    //   4906	4980	4981	java/lang/Exception
    //   5029	5034	4981	java/lang/Exception
    //   0	666	4988	java/lang/Exception
    //   4982	4987	4988	java/lang/Exception
    //   4800	4810	5035	java/lang/Exception
    //   4812	4822	5035	java/lang/Exception
    //   4829	4852	5035	java/lang/Exception
    //   4738	4750	5041	java/lang/Exception
    //   4752	4764	5041	java/lang/Exception
    //   4766	4798	5041	java/lang/Exception
    //   4676	4688	5047	java/lang/Exception
    //   4690	4702	5047	java/lang/Exception
    //   4704	4736	5047	java/lang/Exception
    //   4614	4626	5053	java/lang/Exception
    //   4628	4640	5053	java/lang/Exception
    //   4642	4674	5053	java/lang/Exception
    //   4552	4564	5059	java/lang/Exception
    //   4566	4578	5059	java/lang/Exception
    //   4580	4612	5059	java/lang/Exception
    //   4490	4502	5065	java/lang/Exception
    //   4504	4516	5065	java/lang/Exception
    //   4518	4550	5065	java/lang/Exception
    //   4428	4440	5071	java/lang/Exception
    //   4442	4454	5071	java/lang/Exception
    //   4456	4488	5071	java/lang/Exception
    //   4366	4378	5077	java/lang/Exception
    //   4380	4392	5077	java/lang/Exception
    //   4394	4426	5077	java/lang/Exception
    //   4304	4316	5083	java/lang/Exception
    //   4318	4330	5083	java/lang/Exception
    //   4332	4364	5083	java/lang/Exception
    //   4242	4254	5089	java/lang/Exception
    //   4256	4268	5089	java/lang/Exception
    //   4270	4302	5089	java/lang/Exception
    //   4180	4192	5095	java/lang/Exception
    //   4194	4206	5095	java/lang/Exception
    //   4208	4240	5095	java/lang/Exception
    //   4118	4130	5101	java/lang/Exception
    //   4132	4144	5101	java/lang/Exception
    //   4146	4178	5101	java/lang/Exception
    //   4056	4068	5107	java/lang/Exception
    //   4070	4082	5107	java/lang/Exception
    //   4084	4116	5107	java/lang/Exception
    //   3994	4006	5113	java/lang/Exception
    //   4008	4020	5113	java/lang/Exception
    //   4022	4054	5113	java/lang/Exception
    //   3932	3944	5119	java/lang/Exception
    //   3946	3958	5119	java/lang/Exception
    //   3960	3992	5119	java/lang/Exception
    //   3870	3882	5125	java/lang/Exception
    //   3884	3896	5125	java/lang/Exception
    //   3898	3930	5125	java/lang/Exception
    //   3808	3820	5131	java/lang/Exception
    //   3822	3834	5131	java/lang/Exception
    //   3836	3868	5131	java/lang/Exception
    //   3754	3764	5137	java/lang/Exception
    //   3766	3776	5137	java/lang/Exception
    //   3783	3806	5137	java/lang/Exception
    //   3700	3710	5143	java/lang/Exception
    //   3712	3722	5143	java/lang/Exception
    //   3729	3752	5143	java/lang/Exception
    //   3646	3656	5149	java/lang/Exception
    //   3658	3668	5149	java/lang/Exception
    //   3675	3698	5149	java/lang/Exception
    //   3592	3602	5155	java/lang/Exception
    //   3604	3614	5155	java/lang/Exception
    //   3621	3644	5155	java/lang/Exception
    //   3538	3548	5161	java/lang/Exception
    //   3550	3560	5161	java/lang/Exception
    //   3567	3590	5161	java/lang/Exception
    //   3484	3494	5167	java/lang/Exception
    //   3496	3506	5167	java/lang/Exception
    //   3513	3536	5167	java/lang/Exception
    //   3430	3440	5173	java/lang/Exception
    //   3442	3452	5173	java/lang/Exception
    //   3459	3482	5173	java/lang/Exception
    //   3376	3386	5179	java/lang/Exception
    //   3388	3398	5179	java/lang/Exception
    //   3405	3428	5179	java/lang/Exception
    //   3322	3332	5185	java/lang/Exception
    //   3334	3344	5185	java/lang/Exception
    //   3351	3374	5185	java/lang/Exception
    //   3268	3278	5191	java/lang/Exception
    //   3280	3290	5191	java/lang/Exception
    //   3297	3320	5191	java/lang/Exception
    //   3214	3224	5197	java/lang/Exception
    //   3226	3236	5197	java/lang/Exception
    //   3243	3266	5197	java/lang/Exception
    //   3160	3170	5203	java/lang/Exception
    //   3172	3182	5203	java/lang/Exception
    //   3189	3212	5203	java/lang/Exception
    //   3106	3116	5209	java/lang/Exception
    //   3118	3128	5209	java/lang/Exception
    //   3135	3158	5209	java/lang/Exception
    //   3052	3062	5215	java/lang/Exception
    //   3064	3074	5215	java/lang/Exception
    //   3081	3104	5215	java/lang/Exception
    //   2998	3008	5221	java/lang/Exception
    //   3010	3020	5221	java/lang/Exception
    //   3027	3050	5221	java/lang/Exception
    //   2944	2954	5227	java/lang/Exception
    //   2956	2966	5227	java/lang/Exception
    //   2973	2996	5227	java/lang/Exception
    //   2890	2900	5233	java/lang/Exception
    //   2902	2912	5233	java/lang/Exception
    //   2919	2942	5233	java/lang/Exception
    //   2836	2846	5239	java/lang/Exception
    //   2848	2858	5239	java/lang/Exception
    //   2865	2888	5239	java/lang/Exception
    //   2782	2792	5245	java/lang/Exception
    //   2794	2804	5245	java/lang/Exception
    //   2811	2834	5245	java/lang/Exception
    //   2728	2738	5251	java/lang/Exception
    //   2740	2750	5251	java/lang/Exception
    //   2757	2780	5251	java/lang/Exception
    //   2674	2684	5257	java/lang/Exception
    //   2686	2696	5257	java/lang/Exception
    //   2703	2726	5257	java/lang/Exception
    //   2620	2630	5263	java/lang/Exception
    //   2632	2642	5263	java/lang/Exception
    //   2649	2672	5263	java/lang/Exception
    //   2566	2576	5269	java/lang/Exception
    //   2578	2588	5269	java/lang/Exception
    //   2595	2618	5269	java/lang/Exception
    //   2516	2525	5275	java/lang/Exception
    //   2527	2536	5275	java/lang/Exception
    //   2542	2564	5275	java/lang/Exception
    //   2462	2472	5281	java/lang/Exception
    //   2474	2484	5281	java/lang/Exception
    //   2491	2514	5281	java/lang/Exception
    //   2408	2418	5288	java/lang/Exception
    //   2420	2430	5288	java/lang/Exception
    //   2437	2460	5288	java/lang/Exception
    //   2342	2352	5295	java/lang/Exception
    //   2354	2364	5295	java/lang/Exception
    //   2373	2406	5295	java/lang/Exception
    //   2288	2298	5302	java/lang/Exception
    //   2300	2310	5302	java/lang/Exception
    //   2317	2340	5302	java/lang/Exception
    //   2234	2244	5309	java/lang/Exception
    //   2246	2256	5309	java/lang/Exception
    //   2263	2286	5309	java/lang/Exception
    //   2170	2181	5316	java/lang/Exception
    //   2183	2194	5316	java/lang/Exception
    //   2196	2232	5316	java/lang/Exception
    //   2106	2117	5323	java/lang/Exception
    //   2119	2130	5323	java/lang/Exception
    //   2132	2168	5323	java/lang/Exception
    //   2042	2053	5330	java/lang/Exception
    //   2055	2066	5330	java/lang/Exception
    //   2068	2104	5330	java/lang/Exception
    //   1978	1989	5337	java/lang/Exception
    //   1991	2002	5337	java/lang/Exception
    //   2004	2040	5337	java/lang/Exception
    //   1914	1925	5344	java/lang/Exception
    //   1927	1938	5344	java/lang/Exception
    //   1940	1976	5344	java/lang/Exception
    //   1850	1861	5351	java/lang/Exception
    //   1863	1874	5351	java/lang/Exception
    //   1876	1912	5351	java/lang/Exception
    //   1786	1797	5358	java/lang/Exception
    //   1799	1810	5358	java/lang/Exception
    //   1812	1848	5358	java/lang/Exception
    //   1722	1733	5365	java/lang/Exception
    //   1735	1746	5365	java/lang/Exception
    //   1748	1784	5365	java/lang/Exception
    //   1662	1720	5372	java/lang/Exception
  }
  
  public void run()
  {
    super.run();
    try
    {
      DebugTrack.logThread(this.className, "run ProfileThread");
      Thread.sleep(10000L);
    }
    catch (InterruptedException localInterruptedException1)
    {
      for (;;)
      {
        try
        {
          this.flagGmailData = true;
          this.flagFbData = true;
          if (this.flagCollectionData)
          {
            this.retrycounter = Integer.parseInt(SeventynineConstants.strProfileRetrycount);
            this.flagCollectionData = false;
            if ((SeventynineAdSDK.gmailToken != null) && (!SeventynineAdSDK.gmailToken.isEmpty()))
            {
              if (Looper.myLooper() == null) {
                Looper.prepare();
              }
              new GetGmailData(null).execute(new Void[0]);
            }
          }
          else
          {
            if (Looper.myLooper() == null) {
              Looper.prepare();
            }
            Thread.sleep(this.timetorun);
            return;
            localInterruptedException1 = localInterruptedException1;
            DebugTrack.SendExceptionReport(localInterruptedException1.toString(), "run()", "", "", "", "", "UserProfileManager", 0);
            DebugTrack.log("e", localInterruptedException1.toString(), "UserProfileManager", 0, "run()");
            continue;
          }
          if ((SeventynineAdSDK.facebookToken != null) && (!SeventynineAdSDK.facebookToken.isEmpty()))
          {
            if (Looper.myLooper() == null) {
              Looper.prepare();
            }
            new GetFbData(null).execute(new Void[0]);
            continue;
          }
          getDevicedatawithoutasync();
        }
        catch (InterruptedException localInterruptedException2)
        {
          DebugTrack.SendExceptionReport(localInterruptedException2.toString(), "run()", "", "", "", "", "UserProfileManager", 0);
          DebugTrack.log("e", localInterruptedException2.toString(), "UserProfileManager", 0, "run()");
          return;
        }
        catch (Exception localException)
        {
          DebugTrack.SendExceptionReport(localException.toString(), "run()", "", "", "", "", "UserProfileManager", 0);
          DebugTrack.log("e", localException.toString(), "UserProfileManager", 0, "run()");
          return;
        }
        getListOfApp();
        getTelecomInfo();
        getLocation();
      }
    }
  }
  
  class AppInfo
  {
    long lastupdatedtime = 0L;
    String pname = "";
    
    AppInfo() {}
  }
  
  private class GetFbData
    extends AsyncTask<Void, Void, String>
  {
    private GetFbData() {}
    
    protected String doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = "No_data";
      for (;;)
      {
        try
        {
          Object localObject = (HttpURLConnection)new URL("https://graph.facebook.com/me?access_token=" + SeventynineAdSDK.facebookToken).openConnection();
          if (((HttpURLConnection)localObject).getResponseCode() == 200)
          {
            localObject = ((HttpURLConnection)localObject).getInputStream();
            paramVarArgs = UserProfileManager.readResponse((InputStream)localObject);
            ((InputStream)localObject).close();
          }
        }
        catch (Exception paramVarArgs)
        {
          UserProfileManager.this.flagFbData = false;
          DebugTrack.SendExceptionReport(paramVarArgs.toString(), "GetFbData()", "", "", "", "", "UserProfileManager", 0);
          DebugTrack.log("e", paramVarArgs.toString(), "UserProfileManager", 0, "GetFbData()");
          continue;
        }
        try
        {
          paramVarArgs = new JSONObject(paramVarArgs);
          if (paramVarArgs.has("id"))
          {
            UserProfileManager.this.fb_id = paramVarArgs.getString("id");
            if (UserProfileManager.this.fb_id == null) {
              UserProfileManager.this.fb_id = "";
            }
          }
          if (paramVarArgs.has("birthday"))
          {
            UserProfileManager.this.fb_birthday = paramVarArgs.getString("birthday");
            if (UserProfileManager.this.fb_birthday == null) {
              UserProfileManager.this.fb_birthday = "";
            }
          }
          if (paramVarArgs.has("email"))
          {
            UserProfileManager.this.fb_email = paramVarArgs.getString("email");
            if (UserProfileManager.this.fb_email == null) {
              UserProfileManager.this.fb_email = "";
            }
          }
          if (paramVarArgs.has("first_name"))
          {
            UserProfileManager.this.fb_first_name = paramVarArgs.getString("first_name");
            if (UserProfileManager.this.fb_first_name == null) {
              UserProfileManager.this.fb_first_name = "";
            }
          }
          if (paramVarArgs.has("gender"))
          {
            UserProfileManager.this.fb_gender = paramVarArgs.getString("gender");
            if (UserProfileManager.this.fb_gender == null) {
              UserProfileManager.this.fb_gender = "";
            }
          }
          if (paramVarArgs.has("last_name"))
          {
            UserProfileManager.this.fb_last_name = paramVarArgs.getString("last_name");
            if (UserProfileManager.this.fb_last_name == null) {
              UserProfileManager.this.fb_last_name = "";
            }
          }
          if (paramVarArgs.has("locale"))
          {
            UserProfileManager.this.fb_locale = paramVarArgs.getString("locale");
            if (UserProfileManager.this.fb_locale == null) {
              UserProfileManager.this.fb_locale = "";
            }
          }
          if (paramVarArgs.has("name"))
          {
            UserProfileManager.this.fb_name = paramVarArgs.getString("name");
            if (UserProfileManager.this.fb_name == null) {
              UserProfileManager.this.fb_name = "";
            }
          }
          if (paramVarArgs.has("updated_time"))
          {
            UserProfileManager.this.fb_updated_time = paramVarArgs.getString("updated_time");
            if (UserProfileManager.this.fb_updated_time == null) {
              UserProfileManager.this.fb_updated_time = "";
            }
          }
        }
        catch (JSONException paramVarArgs)
        {
          UserProfileManager.this.flagFbData = false;
          UserProfileManager.this.getDevicedatawithoutasync();
          UserProfileManager.this.getListOfApp();
          UserProfileManager.this.getLocation();
          UserProfileManager.this.getTelecomInfo();
          UserProfileManager.this.Showresult();
        }
      }
      return UserProfileManager.this.fb_id;
    }
    
    protected void onPostExecute(String paramString)
    {
      try
      {
        if (UserProfileManager.this.flagFbData)
        {
          UserProfileManager.this.getDevicedatawithoutasync();
          UserProfileManager.this.getListOfApp();
          UserProfileManager.this.getLocation();
          UserProfileManager.this.getTelecomInfo();
          UserProfileManager.this.Showresult();
        }
        return;
      }
      catch (Exception paramString)
      {
        DebugTrack.SendExceptionReport(paramString.toString(), "onPostExecute()", "", "", "", "", "UserProfileManager", 0);
        DebugTrack.log("e", paramString.toString(), "UserProfileManager", 0, "onPostExecute()");
      }
    }
  }
  
  private class GetGmailData
    extends AsyncTask<Void, Void, String>
  {
    private GetGmailData() {}
    
    protected String doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = "No_data";
      for (;;)
      {
        try
        {
          Object localObject = (HttpURLConnection)new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + SeventynineAdSDK.gmailToken).openConnection();
          if (((HttpURLConnection)localObject).getResponseCode() == 200)
          {
            localObject = ((HttpURLConnection)localObject).getInputStream();
            paramVarArgs = UserProfileManager.readResponse((InputStream)localObject);
            ((InputStream)localObject).close();
          }
        }
        catch (Exception paramVarArgs)
        {
          UserProfileManager.this.flagGmailData = false;
          DebugTrack.SendExceptionReport(paramVarArgs.toString(), "GetGmailData()", "", "", "", "", "UserProfileManager", 0);
          DebugTrack.log("e", paramVarArgs.toString(), "UserProfileManager", 0, "GetGmailData()");
          continue;
          UserProfileManager.this.getDevicedatawithoutasync();
          UserProfileManager.this.getListOfApp();
          UserProfileManager.this.getLocation();
          UserProfileManager.this.getTelecomInfo();
          continue;
        }
        try
        {
          paramVarArgs = new JSONObject(paramVarArgs);
          if (paramVarArgs.has("id")) {
            UserProfileManager.this.gmailid = paramVarArgs.getString("id");
          }
          if (paramVarArgs.has("name")) {
            UserProfileManager.this.gmail_name = paramVarArgs.getString("name");
          }
          if (paramVarArgs.has("given_name")) {
            UserProfileManager.this.gmail_given_name = paramVarArgs.getString("given_name");
          }
          if (paramVarArgs.has("family_name")) {
            UserProfileManager.this.gmail_family_name = paramVarArgs.getString("family_name");
          }
          if (paramVarArgs.has("link")) {
            UserProfileManager.this.gmail_plus_link = paramVarArgs.getString("link");
          }
          if (paramVarArgs.has("picture")) {
            UserProfileManager.this.gmail_picture = paramVarArgs.getString("picture");
          }
          if (paramVarArgs.has("gender")) {
            UserProfileManager.this.gmail_gender = paramVarArgs.getString("gender");
          }
          if (paramVarArgs.has("locale")) {
            UserProfileManager.this.gmail_locale = paramVarArgs.getString("locale");
          }
          return UserProfileManager.this.gmailid;
        }
        catch (JSONException paramVarArgs)
        {
          UserProfileManager.this.flagGmailData = false;
          if (SeventynineAdSDK.facebookToken == null) {
            continue;
          }
        }
        if (SeventynineAdSDK.facebookToken.isEmpty()) {
          continue;
        }
        if (Looper.myLooper() == null) {
          Looper.prepare();
        }
        new UserProfileManager.GetFbData(UserProfileManager.this, null).execute(new Void[0]);
      }
    }
    
    protected void onPostExecute(String paramString)
    {
      try
      {
        if (UserProfileManager.this.flagGmailData)
        {
          if ((SeventynineAdSDK.facebookToken != null) && (!SeventynineAdSDK.facebookToken.isEmpty()))
          {
            new UserProfileManager.GetFbData(UserProfileManager.this, null).execute(new Void[0]);
            return;
          }
          UserProfileManager.this.getDevicedatawithoutasync();
          UserProfileManager.this.getListOfApp();
          UserProfileManager.this.getLocation();
          UserProfileManager.this.getTelecomInfo();
          return;
        }
      }
      catch (Exception paramString)
      {
        DebugTrack.SendExceptionReport(paramString.toString(), "GetGmailData-onPostExecute()", "", "", "", "", "UserProfileManager", 0);
        DebugTrack.log("e", paramString.toString(), "UserProfileManager", 0, "GetGmailData-onPostExecute()");
      }
    }
  }
  
  private class HttpAsyncTask
    extends AsyncTask<String, Void, String>
  {
    private HttpAsyncTask() {}
    
    private void UpdateUI(String paramString)
    {
      Object localObject = "";
      new HttpAsyncTask(UserProfileManager.this);
      if (paramString != null) {
        for (;;)
        {
          JSONObject localJSONObject;
          HashMap localHashMap;
          try
          {
            localJSONObject = new JSONObject(paramString);
            Iterator localIterator = localJSONObject.keys();
            localHashMap = new HashMap();
            paramString = (String)localObject;
            if (!localIterator.hasNext())
            {
              if (!localIterator.hasNext())
              {
                UserProfileManager.this.tsLong = Long.valueOf(System.currentTimeMillis());
                localObject = UserProfileManager.this.tsLong.toString();
                Database.getInstance().UpdateConfigurationTableNewStructure(UserProfileManager.this.con, "profile_id", paramString, "true", "", (String)localObject);
                Database.getInstance().UpdateDirtyFlagInProfileTable(UserProfileManager.this.con, "false");
                Database.getInstance().UpdateFlagInAppListData(UserProfileManager.this.con, "false");
                Database.getInstance().deleteallRecordInHourTableAndLatLngTable();
                UserProfileManager.this.flagCollectionData = true;
                DebugTrack.log("d", "profile all value inserted ", UserProfileManager.this.className, UserProfileManager.this.lineNumber);
                localObject = UserProfileManager.this.prefsfirst.edit();
                ((SharedPreferences.Editor)localObject).putBoolean("insert data in Db firstTime", true);
                ((SharedPreferences.Editor)localObject).commit();
                localObject = SeventynineConstants.appContext.getSharedPreferences("Profile_id", 0).edit();
                ((SharedPreferences.Editor)localObject).putString("pid", paramString);
                ((SharedPreferences.Editor)localObject).commit();
                SeventynineAdSDK.startProfileThread = true;
              }
            }
            else
            {
              localObject = (String)localIterator.next();
              if (((String)localObject).equalsIgnoreCase("id")) {
                paramString = localJSONObject.getString((String)localObject);
              }
              localHashMap.put(localObject, localJSONObject.getString((String)localObject));
              continue;
            }
            localObject = (String)localIterator.next();
          }
          catch (Exception paramString)
          {
            DebugTrack.SendExceptionReport(paramString.toString(), "UpdateUI()", "", "", "", "", "UserProfileManager", 0);
            DebugTrack.log("e", paramString.toString(), "UserProfileManager", 0, "UpdateUI()");
            return;
          }
          if (((String)localObject).equalsIgnoreCase("status")) {
            localJSONObject.getString((String)localObject);
          }
          localHashMap.put(localObject, localJSONObject.getString((String)localObject));
        }
      }
      boolean bool;
      do
      {
        if (UserProfileManager.this.retrycounter >= 1)
        {
          UserProfileManager.this.retryFlag = false;
          paramString = UserProfileManager.this;
          paramString.retrycounter -= 1;
          Thread.sleep(Long.parseLong(SeventynineConstants.strProfileRetryInterval));
        }
        bool = UserProfileManager.this.retryFlag;
      } while (bool);
    }
    
    /* Error */
    public String POST(String paramString)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: new 230	org/apache/http/client/methods/HttpPost
      //   5: dup
      //   6: aload_1
      //   7: invokespecial 231	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
      //   10: astore_1
      //   11: new 29	org/json/JSONObject
      //   14: dup
      //   15: invokespecial 232	org/json/JSONObject:<init>	()V
      //   18: astore 5
      //   20: aload 5
      //   22: ldc -78
      //   24: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   27: invokevirtual 241	seventynine/sdk/UserProfile:getProject_id	()Ljava/lang/String;
      //   30: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   33: pop
      //   34: iload_3
      //   35: istore_2
      //   36: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   39: invokevirtual 248	seventynine/sdk/UserProfile:getDevice_id	()Ljava/lang/String;
      //   42: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   45: ldc -5
      //   47: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   50: ifne +50 -> 100
      //   53: iload_3
      //   54: istore_2
      //   55: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   58: invokevirtual 248	seventynine/sdk/UserProfile:getDevice_id	()Ljava/lang/String;
      //   61: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   64: ifne +36 -> 100
      //   67: iload_3
      //   68: istore_2
      //   69: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   72: invokevirtual 257	seventynine/sdk/UserProfile:getDevice_id_flag	()Ljava/lang/String;
      //   75: ldc 84
      //   77: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   80: ifne +20 -> 100
      //   83: aload 5
      //   85: ldc_w 259
      //   88: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   91: invokevirtual 248	seventynine/sdk/UserProfile:getDevice_id	()Ljava/lang/String;
      //   94: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   97: pop
      //   98: iconst_1
      //   99: istore_2
      //   100: iload_2
      //   101: istore_3
      //   102: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   105: invokevirtual 262	seventynine/sdk/UserProfile:getOs_id	()Ljava/lang/String;
      //   108: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   111: ldc -5
      //   113: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   116: ifne +50 -> 166
      //   119: iload_2
      //   120: istore_3
      //   121: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   124: invokevirtual 262	seventynine/sdk/UserProfile:getOs_id	()Ljava/lang/String;
      //   127: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   130: ifne +36 -> 166
      //   133: iload_2
      //   134: istore_3
      //   135: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   138: invokevirtual 265	seventynine/sdk/UserProfile:getOs_id_flag	()Ljava/lang/String;
      //   141: ldc 84
      //   143: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   146: ifne +20 -> 166
      //   149: aload 5
      //   151: ldc_w 267
      //   154: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   157: invokevirtual 262	seventynine/sdk/UserProfile:getOs_id	()Ljava/lang/String;
      //   160: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   163: pop
      //   164: iconst_1
      //   165: istore_3
      //   166: iload_3
      //   167: istore_2
      //   168: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   171: invokevirtual 270	seventynine/sdk/UserProfile:getAdvertisement_id	()Ljava/lang/String;
      //   174: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   177: ldc -5
      //   179: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   182: ifne +50 -> 232
      //   185: iload_3
      //   186: istore_2
      //   187: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   190: invokevirtual 270	seventynine/sdk/UserProfile:getAdvertisement_id	()Ljava/lang/String;
      //   193: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   196: ifne +36 -> 232
      //   199: iload_3
      //   200: istore_2
      //   201: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   204: invokevirtual 273	seventynine/sdk/UserProfile:getAdvertisement_id_flag	()Ljava/lang/String;
      //   207: ldc 84
      //   209: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   212: ifne +20 -> 232
      //   215: aload 5
      //   217: ldc_w 275
      //   220: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   223: invokevirtual 270	seventynine/sdk/UserProfile:getAdvertisement_id	()Ljava/lang/String;
      //   226: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   229: pop
      //   230: iconst_1
      //   231: istore_2
      //   232: iload_2
      //   233: istore_3
      //   234: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   237: invokevirtual 278	seventynine/sdk/UserProfile:getDefault_account_id	()Ljava/lang/String;
      //   240: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   243: ldc -5
      //   245: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   248: ifne +50 -> 298
      //   251: iload_2
      //   252: istore_3
      //   253: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   256: invokevirtual 278	seventynine/sdk/UserProfile:getDefault_account_id	()Ljava/lang/String;
      //   259: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   262: ifne +36 -> 298
      //   265: iload_2
      //   266: istore_3
      //   267: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   270: invokevirtual 281	seventynine/sdk/UserProfile:getDefault_account_id_flag	()Ljava/lang/String;
      //   273: ldc 84
      //   275: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   278: ifne +20 -> 298
      //   281: aload 5
      //   283: ldc_w 283
      //   286: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   289: invokevirtual 278	seventynine/sdk/UserProfile:getDefault_account_id	()Ljava/lang/String;
      //   292: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   295: pop
      //   296: iconst_1
      //   297: istore_3
      //   298: iload_3
      //   299: istore_2
      //   300: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   303: invokevirtual 286	seventynine/sdk/UserProfile:getWifi_mac_id	()Ljava/lang/String;
      //   306: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   309: ldc -5
      //   311: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   314: ifne +50 -> 364
      //   317: iload_3
      //   318: istore_2
      //   319: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   322: invokevirtual 286	seventynine/sdk/UserProfile:getWifi_mac_id	()Ljava/lang/String;
      //   325: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   328: ifne +36 -> 364
      //   331: iload_3
      //   332: istore_2
      //   333: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   336: invokevirtual 289	seventynine/sdk/UserProfile:getWifi_mac_id_flag	()Ljava/lang/String;
      //   339: ldc 84
      //   341: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   344: ifne +20 -> 364
      //   347: aload 5
      //   349: ldc_w 291
      //   352: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   355: invokevirtual 286	seventynine/sdk/UserProfile:getWifi_mac_id	()Ljava/lang/String;
      //   358: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   361: pop
      //   362: iconst_1
      //   363: istore_2
      //   364: new 29	org/json/JSONObject
      //   367: dup
      //   368: invokespecial 232	org/json/JSONObject:<init>	()V
      //   371: astore 6
      //   373: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   376: invokevirtual 294	seventynine/sdk/UserProfile:getOs_name	()Ljava/lang/String;
      //   379: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   382: ldc -5
      //   384: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   387: ifne +44 -> 431
      //   390: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   393: invokevirtual 294	seventynine/sdk/UserProfile:getOs_name	()Ljava/lang/String;
      //   396: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   399: ifne +32 -> 431
      //   402: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   405: invokevirtual 297	seventynine/sdk/UserProfile:getOs_name_flag	()Ljava/lang/String;
      //   408: ldc 84
      //   410: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   413: ifne +18 -> 431
      //   416: aload 6
      //   418: ldc_w 299
      //   421: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   424: invokevirtual 294	seventynine/sdk/UserProfile:getOs_name	()Ljava/lang/String;
      //   427: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   430: pop
      //   431: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   434: invokevirtual 302	seventynine/sdk/UserProfile:getOs_version	()Ljava/lang/String;
      //   437: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   440: ldc -5
      //   442: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   445: ifne +44 -> 489
      //   448: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   451: invokevirtual 302	seventynine/sdk/UserProfile:getOs_version	()Ljava/lang/String;
      //   454: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   457: ifne +32 -> 489
      //   460: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   463: invokevirtual 305	seventynine/sdk/UserProfile:getOs_version_flag	()Ljava/lang/String;
      //   466: ldc 84
      //   468: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   471: ifne +18 -> 489
      //   474: aload 6
      //   476: ldc_w 307
      //   479: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   482: invokevirtual 302	seventynine/sdk/UserProfile:getOs_version	()Ljava/lang/String;
      //   485: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   488: pop
      //   489: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   492: invokevirtual 310	seventynine/sdk/UserProfile:getBrand	()Ljava/lang/String;
      //   495: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   498: ldc -5
      //   500: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   503: ifne +44 -> 547
      //   506: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   509: invokevirtual 310	seventynine/sdk/UserProfile:getBrand	()Ljava/lang/String;
      //   512: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   515: ifne +32 -> 547
      //   518: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   521: invokevirtual 313	seventynine/sdk/UserProfile:getBrand_flag	()Ljava/lang/String;
      //   524: ldc 84
      //   526: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   529: ifne +18 -> 547
      //   532: aload 6
      //   534: ldc_w 315
      //   537: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   540: invokevirtual 310	seventynine/sdk/UserProfile:getBrand	()Ljava/lang/String;
      //   543: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   546: pop
      //   547: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   550: invokevirtual 318	seventynine/sdk/UserProfile:getModel	()Ljava/lang/String;
      //   553: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   556: ldc -5
      //   558: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   561: ifne +44 -> 605
      //   564: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   567: invokevirtual 318	seventynine/sdk/UserProfile:getModel	()Ljava/lang/String;
      //   570: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   573: ifne +32 -> 605
      //   576: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   579: invokevirtual 321	seventynine/sdk/UserProfile:getModel_flag	()Ljava/lang/String;
      //   582: ldc 84
      //   584: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   587: ifne +18 -> 605
      //   590: aload 6
      //   592: ldc_w 323
      //   595: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   598: invokevirtual 318	seventynine/sdk/UserProfile:getModel	()Ljava/lang/String;
      //   601: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   604: pop
      //   605: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   608: invokevirtual 326	seventynine/sdk/UserProfile:getManufacturer	()Ljava/lang/String;
      //   611: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   614: ldc -5
      //   616: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   619: ifne +44 -> 663
      //   622: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   625: invokevirtual 326	seventynine/sdk/UserProfile:getManufacturer	()Ljava/lang/String;
      //   628: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   631: ifne +32 -> 663
      //   634: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   637: invokevirtual 329	seventynine/sdk/UserProfile:getManufacturer_flag	()Ljava/lang/String;
      //   640: ldc 84
      //   642: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   645: ifne +18 -> 663
      //   648: aload 6
      //   650: ldc_w 331
      //   653: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   656: invokevirtual 326	seventynine/sdk/UserProfile:getManufacturer	()Ljava/lang/String;
      //   659: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   662: pop
      //   663: new 29	org/json/JSONObject
      //   666: dup
      //   667: invokespecial 232	org/json/JSONObject:<init>	()V
      //   670: astore 7
      //   672: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   675: invokevirtual 334	seventynine/sdk/UserProfile:getApp_name	()Ljava/lang/String;
      //   678: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   681: ldc -5
      //   683: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   686: ifne +44 -> 730
      //   689: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   692: invokevirtual 334	seventynine/sdk/UserProfile:getApp_name	()Ljava/lang/String;
      //   695: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   698: ifne +32 -> 730
      //   701: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   704: invokevirtual 337	seventynine/sdk/UserProfile:getApp_name_flag	()Ljava/lang/String;
      //   707: ldc 84
      //   709: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   712: ifne +18 -> 730
      //   715: aload 7
      //   717: ldc_w 339
      //   720: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   723: invokevirtual 334	seventynine/sdk/UserProfile:getApp_name	()Ljava/lang/String;
      //   726: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   729: pop
      //   730: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   733: invokevirtual 342	seventynine/sdk/UserProfile:getPackage_name	()Ljava/lang/String;
      //   736: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   739: ldc -5
      //   741: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   744: ifne +44 -> 788
      //   747: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   750: invokevirtual 342	seventynine/sdk/UserProfile:getPackage_name	()Ljava/lang/String;
      //   753: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   756: ifne +32 -> 788
      //   759: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   762: invokevirtual 345	seventynine/sdk/UserProfile:getPackage_name_flag	()Ljava/lang/String;
      //   765: ldc 84
      //   767: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   770: ifne +18 -> 788
      //   773: aload 7
      //   775: ldc_w 347
      //   778: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   781: invokevirtual 342	seventynine/sdk/UserProfile:getPackage_name	()Ljava/lang/String;
      //   784: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   787: pop
      //   788: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   791: invokevirtual 350	seventynine/sdk/UserProfile:getApi_version	()Ljava/lang/String;
      //   794: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   797: ldc -5
      //   799: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   802: ifne +44 -> 846
      //   805: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   808: invokevirtual 350	seventynine/sdk/UserProfile:getApi_version	()Ljava/lang/String;
      //   811: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   814: ifne +32 -> 846
      //   817: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   820: invokevirtual 353	seventynine/sdk/UserProfile:getApi_version_flag	()Ljava/lang/String;
      //   823: ldc 84
      //   825: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   828: ifne +18 -> 846
      //   831: aload 7
      //   833: ldc_w 355
      //   836: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   839: invokevirtual 350	seventynine/sdk/UserProfile:getApi_version	()Ljava/lang/String;
      //   842: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   845: pop
      //   846: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   849: invokevirtual 358	seventynine/sdk/UserProfile:getSdk_version_name	()Ljava/lang/String;
      //   852: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   855: ldc -5
      //   857: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   860: ifne +44 -> 904
      //   863: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   866: invokevirtual 358	seventynine/sdk/UserProfile:getSdk_version_name	()Ljava/lang/String;
      //   869: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   872: ifne +32 -> 904
      //   875: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   878: invokevirtual 361	seventynine/sdk/UserProfile:getSdk_version_name_flag	()Ljava/lang/String;
      //   881: ldc 84
      //   883: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   886: ifne +18 -> 904
      //   889: aload 7
      //   891: ldc_w 363
      //   894: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   897: invokevirtual 358	seventynine/sdk/UserProfile:getSdk_version_name	()Ljava/lang/String;
      //   900: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   903: pop
      //   904: new 29	org/json/JSONObject
      //   907: dup
      //   908: invokespecial 232	org/json/JSONObject:<init>	()V
      //   911: astore 8
      //   913: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   916: invokevirtual 366	seventynine/sdk/UserProfile:getLatitude	()Ljava/lang/String;
      //   919: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   922: ldc -5
      //   924: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   927: ifne +44 -> 971
      //   930: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   933: invokevirtual 366	seventynine/sdk/UserProfile:getLatitude	()Ljava/lang/String;
      //   936: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   939: ifne +32 -> 971
      //   942: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   945: invokevirtual 369	seventynine/sdk/UserProfile:getLatitude_flag	()Ljava/lang/String;
      //   948: ldc 84
      //   950: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   953: ifne +18 -> 971
      //   956: aload 8
      //   958: ldc_w 371
      //   961: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   964: invokevirtual 366	seventynine/sdk/UserProfile:getLatitude	()Ljava/lang/String;
      //   967: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   970: pop
      //   971: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   974: invokevirtual 374	seventynine/sdk/UserProfile:getLongitude	()Ljava/lang/String;
      //   977: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   980: ldc -5
      //   982: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   985: ifne +44 -> 1029
      //   988: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   991: invokevirtual 374	seventynine/sdk/UserProfile:getLongitude	()Ljava/lang/String;
      //   994: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   997: ifne +32 -> 1029
      //   1000: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1003: invokevirtual 377	seventynine/sdk/UserProfile:getLongitude_flag	()Ljava/lang/String;
      //   1006: ldc 84
      //   1008: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1011: ifne +18 -> 1029
      //   1014: aload 8
      //   1016: ldc_w 379
      //   1019: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1022: invokevirtual 374	seventynine/sdk/UserProfile:getLongitude	()Ljava/lang/String;
      //   1025: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1028: pop
      //   1029: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1032: invokevirtual 382	seventynine/sdk/UserProfile:getCountry	()Ljava/lang/String;
      //   1035: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1038: ldc -5
      //   1040: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1043: ifne +44 -> 1087
      //   1046: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1049: invokevirtual 382	seventynine/sdk/UserProfile:getCountry	()Ljava/lang/String;
      //   1052: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1055: ifne +32 -> 1087
      //   1058: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1061: invokevirtual 385	seventynine/sdk/UserProfile:getCountry_flag	()Ljava/lang/String;
      //   1064: ldc 84
      //   1066: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1069: ifne +18 -> 1087
      //   1072: aload 8
      //   1074: ldc_w 387
      //   1077: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1080: invokevirtual 382	seventynine/sdk/UserProfile:getCountry	()Ljava/lang/String;
      //   1083: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1086: pop
      //   1087: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1090: invokevirtual 390	seventynine/sdk/UserProfile:getCity	()Ljava/lang/String;
      //   1093: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1096: ldc -5
      //   1098: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1101: ifne +44 -> 1145
      //   1104: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1107: invokevirtual 390	seventynine/sdk/UserProfile:getCity	()Ljava/lang/String;
      //   1110: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1113: ifne +32 -> 1145
      //   1116: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1119: invokevirtual 393	seventynine/sdk/UserProfile:getCity_flag	()Ljava/lang/String;
      //   1122: ldc 84
      //   1124: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1127: ifne +18 -> 1145
      //   1130: aload 8
      //   1132: ldc_w 395
      //   1135: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1138: invokevirtual 390	seventynine/sdk/UserProfile:getCity	()Ljava/lang/String;
      //   1141: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1144: pop
      //   1145: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1148: invokevirtual 398	seventynine/sdk/UserProfile:getRegion	()Ljava/lang/String;
      //   1151: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1154: ldc -5
      //   1156: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1159: ifne +44 -> 1203
      //   1162: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1165: invokevirtual 398	seventynine/sdk/UserProfile:getRegion	()Ljava/lang/String;
      //   1168: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1171: ifne +32 -> 1203
      //   1174: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1177: invokevirtual 401	seventynine/sdk/UserProfile:getRegion_flag	()Ljava/lang/String;
      //   1180: ldc 84
      //   1182: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1185: ifne +18 -> 1203
      //   1188: aload 8
      //   1190: ldc_w 403
      //   1193: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1196: invokevirtual 398	seventynine/sdk/UserProfile:getRegion	()Ljava/lang/String;
      //   1199: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1202: pop
      //   1203: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1206: invokevirtual 406	seventynine/sdk/UserProfile:getStreet	()Ljava/lang/String;
      //   1209: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1212: ldc -5
      //   1214: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1217: ifne +44 -> 1261
      //   1220: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1223: invokevirtual 406	seventynine/sdk/UserProfile:getStreet	()Ljava/lang/String;
      //   1226: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1229: ifne +32 -> 1261
      //   1232: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1235: invokevirtual 409	seventynine/sdk/UserProfile:getStreet_flag	()Ljava/lang/String;
      //   1238: ldc 84
      //   1240: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1243: ifne +18 -> 1261
      //   1246: aload 8
      //   1248: ldc_w 411
      //   1251: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1254: invokevirtual 406	seventynine/sdk/UserProfile:getStreet	()Ljava/lang/String;
      //   1257: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1260: pop
      //   1261: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1264: invokevirtual 414	seventynine/sdk/UserProfile:getPostal_code	()Ljava/lang/String;
      //   1267: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1270: ldc -5
      //   1272: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1275: ifne +44 -> 1319
      //   1278: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1281: invokevirtual 414	seventynine/sdk/UserProfile:getPostal_code	()Ljava/lang/String;
      //   1284: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1287: ifne +32 -> 1319
      //   1290: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1293: invokevirtual 417	seventynine/sdk/UserProfile:getPostal_code_flag	()Ljava/lang/String;
      //   1296: ldc 84
      //   1298: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1301: ifne +18 -> 1319
      //   1304: aload 8
      //   1306: ldc_w 419
      //   1309: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1312: invokevirtual 414	seventynine/sdk/UserProfile:getPostal_code	()Ljava/lang/String;
      //   1315: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1318: pop
      //   1319: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1322: invokevirtual 422	seventynine/sdk/UserProfile:getPhone_no	()Ljava/lang/String;
      //   1325: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1328: ldc -5
      //   1330: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1333: ifne +44 -> 1377
      //   1336: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1339: invokevirtual 422	seventynine/sdk/UserProfile:getPhone_no	()Ljava/lang/String;
      //   1342: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1345: ifne +32 -> 1377
      //   1348: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1351: invokevirtual 425	seventynine/sdk/UserProfile:getPhone_no_flag	()Ljava/lang/String;
      //   1354: ldc 84
      //   1356: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1359: ifne +18 -> 1377
      //   1362: aload 8
      //   1364: ldc_w 427
      //   1367: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1370: invokevirtual 422	seventynine/sdk/UserProfile:getPhone_no	()Ljava/lang/String;
      //   1373: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1376: pop
      //   1377: new 29	org/json/JSONObject
      //   1380: dup
      //   1381: invokespecial 232	org/json/JSONObject:<init>	()V
      //   1384: astore 9
      //   1386: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1389: invokevirtual 430	seventynine/sdk/UserProfile:getTelco_name	()Ljava/lang/String;
      //   1392: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1395: ldc -5
      //   1397: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1400: ifne +44 -> 1444
      //   1403: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1406: invokevirtual 430	seventynine/sdk/UserProfile:getTelco_name	()Ljava/lang/String;
      //   1409: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1412: ifne +32 -> 1444
      //   1415: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1418: invokevirtual 433	seventynine/sdk/UserProfile:getTelco_name_flag	()Ljava/lang/String;
      //   1421: ldc 84
      //   1423: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1426: ifne +18 -> 1444
      //   1429: aload 9
      //   1431: ldc_w 339
      //   1434: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1437: invokevirtual 430	seventynine/sdk/UserProfile:getTelco_name	()Ljava/lang/String;
      //   1440: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1443: pop
      //   1444: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1447: invokevirtual 436	seventynine/sdk/UserProfile:getCode	()Ljava/lang/String;
      //   1450: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1453: ldc -5
      //   1455: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1458: ifne +44 -> 1502
      //   1461: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1464: invokevirtual 436	seventynine/sdk/UserProfile:getCode	()Ljava/lang/String;
      //   1467: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1470: ifne +32 -> 1502
      //   1473: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1476: invokevirtual 439	seventynine/sdk/UserProfile:getCode_flag	()Ljava/lang/String;
      //   1479: ldc 84
      //   1481: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1484: ifne +18 -> 1502
      //   1487: aload 9
      //   1489: ldc_w 441
      //   1492: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1495: invokevirtual 436	seventynine/sdk/UserProfile:getCode	()Ljava/lang/String;
      //   1498: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1501: pop
      //   1502: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1505: invokevirtual 444	seventynine/sdk/UserProfile:getMnc	()Ljava/lang/String;
      //   1508: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1511: ldc -5
      //   1513: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1516: ifne +44 -> 1560
      //   1519: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1522: invokevirtual 444	seventynine/sdk/UserProfile:getMnc	()Ljava/lang/String;
      //   1525: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1528: ifne +32 -> 1560
      //   1531: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1534: invokevirtual 447	seventynine/sdk/UserProfile:getMnc_flag	()Ljava/lang/String;
      //   1537: ldc 84
      //   1539: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1542: ifne +18 -> 1560
      //   1545: aload 9
      //   1547: ldc_w 449
      //   1550: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1553: invokevirtual 444	seventynine/sdk/UserProfile:getMnc	()Ljava/lang/String;
      //   1556: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1559: pop
      //   1560: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1563: invokevirtual 452	seventynine/sdk/UserProfile:getMcc	()Ljava/lang/String;
      //   1566: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1569: ldc -5
      //   1571: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1574: ifne +44 -> 1618
      //   1577: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1580: invokevirtual 452	seventynine/sdk/UserProfile:getMcc	()Ljava/lang/String;
      //   1583: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1586: ifne +32 -> 1618
      //   1589: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1592: invokevirtual 455	seventynine/sdk/UserProfile:getMcc_flag	()Ljava/lang/String;
      //   1595: ldc 84
      //   1597: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1600: ifne +18 -> 1618
      //   1603: aload 9
      //   1605: ldc_w 457
      //   1608: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1611: invokevirtual 452	seventynine/sdk/UserProfile:getMcc	()Ljava/lang/String;
      //   1614: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1617: pop
      //   1618: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1621: invokevirtual 460	seventynine/sdk/UserProfile:getImsi	()Ljava/lang/String;
      //   1624: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1627: ldc -5
      //   1629: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1632: ifne +44 -> 1676
      //   1635: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1638: invokevirtual 460	seventynine/sdk/UserProfile:getImsi	()Ljava/lang/String;
      //   1641: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1644: ifne +32 -> 1676
      //   1647: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1650: invokevirtual 463	seventynine/sdk/UserProfile:getImsi_flag	()Ljava/lang/String;
      //   1653: ldc 84
      //   1655: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1658: ifne +18 -> 1676
      //   1661: aload 9
      //   1663: ldc_w 465
      //   1666: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1669: invokevirtual 460	seventynine/sdk/UserProfile:getImsi	()Ljava/lang/String;
      //   1672: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1675: pop
      //   1676: new 29	org/json/JSONObject
      //   1679: dup
      //   1680: invokespecial 232	org/json/JSONObject:<init>	()V
      //   1683: astore 10
      //   1685: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1688: invokevirtual 468	seventynine/sdk/UserProfile:getType	()Ljava/lang/String;
      //   1691: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1694: ldc -5
      //   1696: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1699: ifne +44 -> 1743
      //   1702: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1705: invokevirtual 468	seventynine/sdk/UserProfile:getType	()Ljava/lang/String;
      //   1708: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1711: ifne +32 -> 1743
      //   1714: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1717: invokevirtual 471	seventynine/sdk/UserProfile:getType_flag	()Ljava/lang/String;
      //   1720: ldc 84
      //   1722: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1725: ifne +18 -> 1743
      //   1728: aload 10
      //   1730: ldc_w 473
      //   1733: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1736: invokevirtual 468	seventynine/sdk/UserProfile:getType	()Ljava/lang/String;
      //   1739: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1742: pop
      //   1743: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1746: invokevirtual 476	seventynine/sdk/UserProfile:getSpeed	()Ljava/lang/String;
      //   1749: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1752: ldc -5
      //   1754: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1757: ifne +44 -> 1801
      //   1760: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1763: invokevirtual 476	seventynine/sdk/UserProfile:getSpeed	()Ljava/lang/String;
      //   1766: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1769: ifne +32 -> 1801
      //   1772: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1775: invokevirtual 479	seventynine/sdk/UserProfile:getSpeed_flag	()Ljava/lang/String;
      //   1778: ldc 84
      //   1780: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1783: ifne +18 -> 1801
      //   1786: aload 10
      //   1788: ldc_w 481
      //   1791: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1794: invokevirtual 476	seventynine/sdk/UserProfile:getSpeed	()Ljava/lang/String;
      //   1797: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1800: pop
      //   1801: new 29	org/json/JSONObject
      //   1804: dup
      //   1805: invokespecial 232	org/json/JSONObject:<init>	()V
      //   1808: astore 11
      //   1810: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1813: invokevirtual 484	seventynine/sdk/UserProfile:getMsisdn	()Ljava/lang/String;
      //   1816: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1819: ldc -5
      //   1821: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1824: ifne +44 -> 1868
      //   1827: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1830: invokevirtual 484	seventynine/sdk/UserProfile:getMsisdn	()Ljava/lang/String;
      //   1833: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1836: ifne +32 -> 1868
      //   1839: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1842: invokevirtual 487	seventynine/sdk/UserProfile:getMsisdn_flag	()Ljava/lang/String;
      //   1845: ldc 84
      //   1847: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1850: ifne +18 -> 1868
      //   1853: aload 11
      //   1855: ldc_w 489
      //   1858: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1861: invokevirtual 484	seventynine/sdk/UserProfile:getMsisdn	()Ljava/lang/String;
      //   1864: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1867: pop
      //   1868: new 29	org/json/JSONObject
      //   1871: dup
      //   1872: invokespecial 232	org/json/JSONObject:<init>	()V
      //   1875: astore 12
      //   1877: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1880: invokevirtual 492	seventynine/sdk/UserProfile:getGmail_id	()Ljava/lang/String;
      //   1883: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1886: ldc -5
      //   1888: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1891: ifne +44 -> 1935
      //   1894: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1897: invokevirtual 492	seventynine/sdk/UserProfile:getGmail_id	()Ljava/lang/String;
      //   1900: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1903: ifne +32 -> 1935
      //   1906: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1909: invokevirtual 495	seventynine/sdk/UserProfile:getGmail_id_flag	()Ljava/lang/String;
      //   1912: ldc 84
      //   1914: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1917: ifne +18 -> 1935
      //   1920: aload 12
      //   1922: ldc_w 497
      //   1925: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1928: invokevirtual 492	seventynine/sdk/UserProfile:getGmail_id	()Ljava/lang/String;
      //   1931: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1934: pop
      //   1935: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1938: invokevirtual 500	seventynine/sdk/UserProfile:getGmail_name	()Ljava/lang/String;
      //   1941: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   1944: ldc -5
      //   1946: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1949: ifne +44 -> 1993
      //   1952: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1955: invokevirtual 500	seventynine/sdk/UserProfile:getGmail_name	()Ljava/lang/String;
      //   1958: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   1961: ifne +32 -> 1993
      //   1964: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1967: invokevirtual 503	seventynine/sdk/UserProfile:getGmail_name_flag	()Ljava/lang/String;
      //   1970: ldc 84
      //   1972: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   1975: ifne +18 -> 1993
      //   1978: aload 12
      //   1980: ldc_w 505
      //   1983: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1986: invokevirtual 500	seventynine/sdk/UserProfile:getGmail_name	()Ljava/lang/String;
      //   1989: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1992: pop
      //   1993: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   1996: invokevirtual 508	seventynine/sdk/UserProfile:getGmail_given_name	()Ljava/lang/String;
      //   1999: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2002: ldc -5
      //   2004: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2007: ifne +44 -> 2051
      //   2010: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2013: invokevirtual 508	seventynine/sdk/UserProfile:getGmail_given_name	()Ljava/lang/String;
      //   2016: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2019: ifne +32 -> 2051
      //   2022: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2025: invokevirtual 511	seventynine/sdk/UserProfile:getGmail_given_name_flag	()Ljava/lang/String;
      //   2028: ldc 84
      //   2030: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2033: ifne +18 -> 2051
      //   2036: aload 12
      //   2038: ldc_w 513
      //   2041: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2044: invokevirtual 508	seventynine/sdk/UserProfile:getGmail_given_name	()Ljava/lang/String;
      //   2047: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2050: pop
      //   2051: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2054: invokevirtual 516	seventynine/sdk/UserProfile:getGmail_family_name	()Ljava/lang/String;
      //   2057: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2060: ldc -5
      //   2062: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2065: ifne +44 -> 2109
      //   2068: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2071: invokevirtual 516	seventynine/sdk/UserProfile:getGmail_family_name	()Ljava/lang/String;
      //   2074: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2077: ifne +32 -> 2109
      //   2080: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2083: invokevirtual 519	seventynine/sdk/UserProfile:getGmail_family_name_flag	()Ljava/lang/String;
      //   2086: ldc 84
      //   2088: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2091: ifne +18 -> 2109
      //   2094: aload 12
      //   2096: ldc_w 521
      //   2099: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2102: invokevirtual 516	seventynine/sdk/UserProfile:getGmail_family_name	()Ljava/lang/String;
      //   2105: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2108: pop
      //   2109: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2112: invokevirtual 524	seventynine/sdk/UserProfile:getGmail_plus_link	()Ljava/lang/String;
      //   2115: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2118: ldc -5
      //   2120: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2123: ifne +44 -> 2167
      //   2126: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2129: invokevirtual 524	seventynine/sdk/UserProfile:getGmail_plus_link	()Ljava/lang/String;
      //   2132: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2135: ifne +32 -> 2167
      //   2138: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2141: invokevirtual 527	seventynine/sdk/UserProfile:getGmail_plus_link_flag	()Ljava/lang/String;
      //   2144: ldc 84
      //   2146: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2149: ifne +18 -> 2167
      //   2152: aload 12
      //   2154: ldc_w 529
      //   2157: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2160: invokevirtual 524	seventynine/sdk/UserProfile:getGmail_plus_link	()Ljava/lang/String;
      //   2163: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2166: pop
      //   2167: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2170: invokevirtual 532	seventynine/sdk/UserProfile:getGmail_picture	()Ljava/lang/String;
      //   2173: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2176: ldc -5
      //   2178: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2181: ifne +44 -> 2225
      //   2184: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2187: invokevirtual 532	seventynine/sdk/UserProfile:getGmail_picture	()Ljava/lang/String;
      //   2190: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2193: ifne +32 -> 2225
      //   2196: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2199: invokevirtual 535	seventynine/sdk/UserProfile:getGmail_picture_flag	()Ljava/lang/String;
      //   2202: ldc 84
      //   2204: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2207: ifne +18 -> 2225
      //   2210: aload 12
      //   2212: ldc_w 537
      //   2215: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2218: invokevirtual 532	seventynine/sdk/UserProfile:getGmail_picture	()Ljava/lang/String;
      //   2221: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2224: pop
      //   2225: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2228: invokevirtual 540	seventynine/sdk/UserProfile:getGmail_gender	()Ljava/lang/String;
      //   2231: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2234: ldc -5
      //   2236: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2239: ifne +44 -> 2283
      //   2242: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2245: invokevirtual 540	seventynine/sdk/UserProfile:getGmail_gender	()Ljava/lang/String;
      //   2248: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2251: ifne +32 -> 2283
      //   2254: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2257: invokevirtual 543	seventynine/sdk/UserProfile:getGmail_gender_flag	()Ljava/lang/String;
      //   2260: ldc 84
      //   2262: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2265: ifne +18 -> 2283
      //   2268: aload 12
      //   2270: ldc_w 545
      //   2273: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2276: invokevirtual 540	seventynine/sdk/UserProfile:getGmail_gender	()Ljava/lang/String;
      //   2279: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2282: pop
      //   2283: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2286: invokevirtual 548	seventynine/sdk/UserProfile:getGmail_locale	()Ljava/lang/String;
      //   2289: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2292: ldc -5
      //   2294: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2297: ifne +44 -> 2341
      //   2300: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2303: invokevirtual 548	seventynine/sdk/UserProfile:getGmail_locale	()Ljava/lang/String;
      //   2306: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2309: ifne +32 -> 2341
      //   2312: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2315: invokevirtual 551	seventynine/sdk/UserProfile:getGmail_locale_flag	()Ljava/lang/String;
      //   2318: ldc 84
      //   2320: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2323: ifne +18 -> 2341
      //   2326: aload 12
      //   2328: ldc_w 553
      //   2331: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2334: invokevirtual 548	seventynine/sdk/UserProfile:getGmail_locale	()Ljava/lang/String;
      //   2337: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2340: pop
      //   2341: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2344: invokevirtual 548	seventynine/sdk/UserProfile:getGmail_locale	()Ljava/lang/String;
      //   2347: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2350: ldc -5
      //   2352: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2355: ifne +44 -> 2399
      //   2358: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2361: invokevirtual 548	seventynine/sdk/UserProfile:getGmail_locale	()Ljava/lang/String;
      //   2364: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2367: ifne +32 -> 2399
      //   2370: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2373: invokevirtual 551	seventynine/sdk/UserProfile:getGmail_locale_flag	()Ljava/lang/String;
      //   2376: ldc 84
      //   2378: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2381: ifne +18 -> 2399
      //   2384: aload 12
      //   2386: ldc_w 553
      //   2389: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2392: invokevirtual 548	seventynine/sdk/UserProfile:getGmail_locale	()Ljava/lang/String;
      //   2395: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2398: pop
      //   2399: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2402: invokevirtual 556	seventynine/sdk/UserProfile:getFb_id	()Ljava/lang/String;
      //   2405: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2408: ldc -5
      //   2410: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2413: ifne +44 -> 2457
      //   2416: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2419: invokevirtual 556	seventynine/sdk/UserProfile:getFb_id	()Ljava/lang/String;
      //   2422: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2425: ifne +32 -> 2457
      //   2428: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2431: invokevirtual 559	seventynine/sdk/UserProfile:getFb_id_flag	()Ljava/lang/String;
      //   2434: ldc 84
      //   2436: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2439: ifne +18 -> 2457
      //   2442: aload 12
      //   2444: ldc_w 561
      //   2447: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2450: invokevirtual 556	seventynine/sdk/UserProfile:getFb_id	()Ljava/lang/String;
      //   2453: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2456: pop
      //   2457: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2460: invokevirtual 564	seventynine/sdk/UserProfile:getFb_birthday	()Ljava/lang/String;
      //   2463: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2466: ldc -5
      //   2468: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2471: ifne +44 -> 2515
      //   2474: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2477: invokevirtual 564	seventynine/sdk/UserProfile:getFb_birthday	()Ljava/lang/String;
      //   2480: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2483: ifne +32 -> 2515
      //   2486: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2489: invokevirtual 567	seventynine/sdk/UserProfile:getFb_birthday_flag	()Ljava/lang/String;
      //   2492: ldc 84
      //   2494: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2497: ifne +18 -> 2515
      //   2500: aload 12
      //   2502: ldc_w 569
      //   2505: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2508: invokevirtual 564	seventynine/sdk/UserProfile:getFb_birthday	()Ljava/lang/String;
      //   2511: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2514: pop
      //   2515: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2518: invokevirtual 572	seventynine/sdk/UserProfile:getFb_email	()Ljava/lang/String;
      //   2521: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2524: ldc -5
      //   2526: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2529: ifne +44 -> 2573
      //   2532: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2535: invokevirtual 572	seventynine/sdk/UserProfile:getFb_email	()Ljava/lang/String;
      //   2538: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2541: ifne +32 -> 2573
      //   2544: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2547: invokevirtual 575	seventynine/sdk/UserProfile:getFb_email_flag	()Ljava/lang/String;
      //   2550: ldc 84
      //   2552: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2555: ifne +18 -> 2573
      //   2558: aload 12
      //   2560: ldc_w 577
      //   2563: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2566: invokevirtual 572	seventynine/sdk/UserProfile:getFb_email	()Ljava/lang/String;
      //   2569: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2572: pop
      //   2573: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2576: invokevirtual 580	seventynine/sdk/UserProfile:getFb_first_name	()Ljava/lang/String;
      //   2579: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2582: ldc -5
      //   2584: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2587: ifne +44 -> 2631
      //   2590: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2593: invokevirtual 580	seventynine/sdk/UserProfile:getFb_first_name	()Ljava/lang/String;
      //   2596: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2599: ifne +32 -> 2631
      //   2602: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2605: invokevirtual 583	seventynine/sdk/UserProfile:getFb_first_name_flag	()Ljava/lang/String;
      //   2608: ldc 84
      //   2610: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2613: ifne +18 -> 2631
      //   2616: aload 12
      //   2618: ldc_w 585
      //   2621: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2624: invokevirtual 580	seventynine/sdk/UserProfile:getFb_first_name	()Ljava/lang/String;
      //   2627: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2630: pop
      //   2631: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2634: invokevirtual 588	seventynine/sdk/UserProfile:getFb_gender	()Ljava/lang/String;
      //   2637: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2640: ldc -5
      //   2642: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2645: ifne +44 -> 2689
      //   2648: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2651: invokevirtual 588	seventynine/sdk/UserProfile:getFb_gender	()Ljava/lang/String;
      //   2654: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2657: ifne +32 -> 2689
      //   2660: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2663: invokevirtual 591	seventynine/sdk/UserProfile:getFb_gender_flag	()Ljava/lang/String;
      //   2666: ldc 84
      //   2668: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2671: ifne +18 -> 2689
      //   2674: aload 12
      //   2676: ldc_w 593
      //   2679: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2682: invokevirtual 588	seventynine/sdk/UserProfile:getFb_gender	()Ljava/lang/String;
      //   2685: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2688: pop
      //   2689: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2692: invokevirtual 596	seventynine/sdk/UserProfile:getFb_last_name	()Ljava/lang/String;
      //   2695: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2698: ldc -5
      //   2700: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2703: ifne +44 -> 2747
      //   2706: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2709: invokevirtual 596	seventynine/sdk/UserProfile:getFb_last_name	()Ljava/lang/String;
      //   2712: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2715: ifne +32 -> 2747
      //   2718: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2721: invokevirtual 599	seventynine/sdk/UserProfile:getFb_last_name_flag	()Ljava/lang/String;
      //   2724: ldc 84
      //   2726: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2729: ifne +18 -> 2747
      //   2732: aload 12
      //   2734: ldc_w 601
      //   2737: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2740: invokevirtual 596	seventynine/sdk/UserProfile:getFb_last_name	()Ljava/lang/String;
      //   2743: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2746: pop
      //   2747: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2750: invokevirtual 604	seventynine/sdk/UserProfile:getFb_locale	()Ljava/lang/String;
      //   2753: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2756: ldc -5
      //   2758: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2761: ifne +44 -> 2805
      //   2764: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2767: invokevirtual 604	seventynine/sdk/UserProfile:getFb_locale	()Ljava/lang/String;
      //   2770: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2773: ifne +32 -> 2805
      //   2776: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2779: invokevirtual 607	seventynine/sdk/UserProfile:getFb_locale_flag	()Ljava/lang/String;
      //   2782: ldc 84
      //   2784: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2787: ifne +18 -> 2805
      //   2790: aload 12
      //   2792: ldc_w 609
      //   2795: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2798: invokevirtual 604	seventynine/sdk/UserProfile:getFb_locale	()Ljava/lang/String;
      //   2801: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2804: pop
      //   2805: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2808: invokevirtual 612	seventynine/sdk/UserProfile:getFb_name	()Ljava/lang/String;
      //   2811: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2814: ldc -5
      //   2816: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2819: ifne +44 -> 2863
      //   2822: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2825: invokevirtual 612	seventynine/sdk/UserProfile:getFb_name	()Ljava/lang/String;
      //   2828: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2831: ifne +32 -> 2863
      //   2834: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2837: invokevirtual 615	seventynine/sdk/UserProfile:getFb_name_flag	()Ljava/lang/String;
      //   2840: ldc 84
      //   2842: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2845: ifne +18 -> 2863
      //   2848: aload 12
      //   2850: ldc_w 617
      //   2853: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2856: invokevirtual 612	seventynine/sdk/UserProfile:getFb_name	()Ljava/lang/String;
      //   2859: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2862: pop
      //   2863: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2866: invokevirtual 620	seventynine/sdk/UserProfile:getFb_updated_time	()Ljava/lang/String;
      //   2869: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2872: ldc -5
      //   2874: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2877: ifne +44 -> 2921
      //   2880: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2883: invokevirtual 620	seventynine/sdk/UserProfile:getFb_updated_time	()Ljava/lang/String;
      //   2886: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2889: ifne +32 -> 2921
      //   2892: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2895: invokevirtual 623	seventynine/sdk/UserProfile:getFb_updated_time_flag	()Ljava/lang/String;
      //   2898: ldc 84
      //   2900: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2903: ifne +18 -> 2921
      //   2906: aload 12
      //   2908: ldc_w 625
      //   2911: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2914: invokevirtual 620	seventynine/sdk/UserProfile:getFb_updated_time	()Ljava/lang/String;
      //   2917: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2920: pop
      //   2921: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2924: invokevirtual 628	seventynine/sdk/UserProfile:getCustom	()Ljava/lang/String;
      //   2927: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   2930: ldc -5
      //   2932: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2935: ifne +56 -> 2991
      //   2938: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2941: invokevirtual 628	seventynine/sdk/UserProfile:getCustom	()Ljava/lang/String;
      //   2944: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   2947: ifne +44 -> 2991
      //   2950: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2953: invokevirtual 631	seventynine/sdk/UserProfile:getCustom_flag	()Ljava/lang/String;
      //   2956: ldc 84
      //   2958: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   2961: ifne +30 -> 2991
      //   2964: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   2967: invokevirtual 628	seventynine/sdk/UserProfile:getCustom	()Ljava/lang/String;
      //   2970: ldc_w 633
      //   2973: invokevirtual 637	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   2976: astore 13
      //   2978: iconst_0
      //   2979: istore_3
      //   2980: aload 13
      //   2982: arraylength
      //   2983: istore 4
      //   2985: iload_3
      //   2986: iload 4
      //   2988: if_icmplt +1296 -> 4284
      //   2991: new 29	org/json/JSONObject
      //   2994: dup
      //   2995: invokespecial 232	org/json/JSONObject:<init>	()V
      //   2998: astore 13
      //   3000: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3003: invokevirtual 640	seventynine/sdk/UserProfile:getAge	()Ljava/lang/String;
      //   3006: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   3009: ldc -5
      //   3011: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3014: ifne +44 -> 3058
      //   3017: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3020: invokevirtual 640	seventynine/sdk/UserProfile:getAge	()Ljava/lang/String;
      //   3023: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   3026: ifne +32 -> 3058
      //   3029: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3032: invokevirtual 643	seventynine/sdk/UserProfile:getAge_flag	()Ljava/lang/String;
      //   3035: ldc 84
      //   3037: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3040: ifne +18 -> 3058
      //   3043: aload 13
      //   3045: ldc_w 645
      //   3048: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3051: invokevirtual 640	seventynine/sdk/UserProfile:getAge	()Ljava/lang/String;
      //   3054: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3057: pop
      //   3058: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3061: invokevirtual 648	seventynine/sdk/UserProfile:getDob	()Ljava/lang/String;
      //   3064: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   3067: ldc -5
      //   3069: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3072: ifne +44 -> 3116
      //   3075: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3078: invokevirtual 648	seventynine/sdk/UserProfile:getDob	()Ljava/lang/String;
      //   3081: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   3084: ifne +32 -> 3116
      //   3087: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3090: invokevirtual 651	seventynine/sdk/UserProfile:getDob_flag	()Ljava/lang/String;
      //   3093: ldc 84
      //   3095: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3098: ifne +18 -> 3116
      //   3101: aload 13
      //   3103: ldc_w 653
      //   3106: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3109: invokevirtual 648	seventynine/sdk/UserProfile:getDob	()Ljava/lang/String;
      //   3112: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3115: pop
      //   3116: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3119: invokevirtual 656	seventynine/sdk/UserProfile:getLanguage	()Ljava/lang/String;
      //   3122: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   3125: ldc -5
      //   3127: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3130: ifne +44 -> 3174
      //   3133: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3136: invokevirtual 656	seventynine/sdk/UserProfile:getLanguage	()Ljava/lang/String;
      //   3139: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   3142: ifne +32 -> 3174
      //   3145: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3148: invokevirtual 659	seventynine/sdk/UserProfile:getLanguage_flag	()Ljava/lang/String;
      //   3151: ldc 84
      //   3153: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3156: ifne +18 -> 3174
      //   3159: aload 13
      //   3161: ldc_w 661
      //   3164: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3167: invokevirtual 656	seventynine/sdk/UserProfile:getLanguage	()Ljava/lang/String;
      //   3170: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3173: pop
      //   3174: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3177: invokevirtual 664	seventynine/sdk/UserProfile:getGender	()Ljava/lang/String;
      //   3180: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   3183: ldc -5
      //   3185: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3188: ifne +44 -> 3232
      //   3191: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3194: invokevirtual 664	seventynine/sdk/UserProfile:getGender	()Ljava/lang/String;
      //   3197: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   3200: ifne +32 -> 3232
      //   3203: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3206: invokevirtual 667	seventynine/sdk/UserProfile:getGender_flag	()Ljava/lang/String;
      //   3209: ldc 84
      //   3211: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3214: ifne +18 -> 3232
      //   3217: aload 13
      //   3219: ldc_w 669
      //   3222: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3225: invokevirtual 664	seventynine/sdk/UserProfile:getGender	()Ljava/lang/String;
      //   3228: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3231: pop
      //   3232: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3235: invokevirtual 672	seventynine/sdk/UserProfile:getEmail	()Ljava/lang/String;
      //   3238: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   3241: ldc -5
      //   3243: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3246: ifne +44 -> 3290
      //   3249: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3252: invokevirtual 672	seventynine/sdk/UserProfile:getEmail	()Ljava/lang/String;
      //   3255: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   3258: ifne +32 -> 3290
      //   3261: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3264: invokevirtual 675	seventynine/sdk/UserProfile:getEmail_flag	()Ljava/lang/String;
      //   3267: ldc 84
      //   3269: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3272: ifne +18 -> 3290
      //   3275: aload 13
      //   3277: ldc_w 677
      //   3280: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3283: invokevirtual 672	seventynine/sdk/UserProfile:getEmail	()Ljava/lang/String;
      //   3286: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3289: pop
      //   3290: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3293: invokevirtual 680	seventynine/sdk/UserProfile:getCompilationid	()Ljava/lang/String;
      //   3296: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   3299: ldc -5
      //   3301: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3304: ifne +44 -> 3348
      //   3307: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3310: invokevirtual 680	seventynine/sdk/UserProfile:getCompilationid	()Ljava/lang/String;
      //   3313: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   3316: ifne +32 -> 3348
      //   3319: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3322: invokevirtual 683	seventynine/sdk/UserProfile:getCompilationid_flag	()Ljava/lang/String;
      //   3325: ldc 84
      //   3327: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3330: ifne +18 -> 3348
      //   3333: aload 13
      //   3335: ldc_w 685
      //   3338: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3341: invokevirtual 680	seventynine/sdk/UserProfile:getCompilationid	()Ljava/lang/String;
      //   3344: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3347: pop
      //   3348: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3351: invokevirtual 688	seventynine/sdk/UserProfile:getContentLanguage	()Ljava/lang/String;
      //   3354: invokevirtual 249	java/lang/String:toString	()Ljava/lang/String;
      //   3357: ldc -5
      //   3359: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3362: ifne +44 -> 3406
      //   3365: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3368: invokevirtual 688	seventynine/sdk/UserProfile:getContentLanguage	()Ljava/lang/String;
      //   3371: invokevirtual 254	java/lang/String:isEmpty	()Z
      //   3374: ifne +32 -> 3406
      //   3377: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3380: invokevirtual 691	seventynine/sdk/UserProfile:getContentLanguage_flag	()Ljava/lang/String;
      //   3383: ldc 84
      //   3385: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   3388: ifne +18 -> 3406
      //   3391: aload 13
      //   3393: ldc_w 693
      //   3396: getstatic 236	seventynine/sdk/UserProfileManager:profile	Lseventynine/sdk/UserProfile;
      //   3399: invokevirtual 688	seventynine/sdk/UserProfile:getContentLanguage	()Ljava/lang/String;
      //   3402: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3405: pop
      //   3406: new 29	org/json/JSONObject
      //   3409: dup
      //   3410: invokespecial 232	org/json/JSONObject:<init>	()V
      //   3413: pop
      //   3414: new 29	org/json/JSONObject
      //   3417: dup
      //   3418: invokespecial 232	org/json/JSONObject:<init>	()V
      //   3421: astore 14
      //   3423: new 29	org/json/JSONObject
      //   3426: dup
      //   3427: invokespecial 232	org/json/JSONObject:<init>	()V
      //   3430: astore 15
      //   3432: getstatic 696	seventynine/sdk/SeventynineConstants:isSendAppList	Z
      //   3435: ifeq +1051 -> 4486
      //   3438: aload_0
      //   3439: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3442: getfield 699	seventynine/sdk/UserProfileManager:prefsApplistFirstTime	Landroid/content/SharedPreferences;
      //   3445: ldc_w 701
      //   3448: iconst_0
      //   3449: invokeinterface 705 3 0
      //   3454: ifne +931 -> 4385
      //   3457: getstatic 709	seventynine/sdk/UserProfileManager:arrayAppList	Ljava/util/ArrayList;
      //   3460: invokevirtual 714	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   3463: astore 16
      //   3465: aload 16
      //   3467: invokeinterface 44 1 0
      //   3472: ifne +849 -> 4321
      //   3475: getstatic 709	seventynine/sdk/UserProfileManager:arrayAppList	Ljava/util/ArrayList;
      //   3478: invokevirtual 717	java/util/ArrayList:clear	()V
      //   3481: aload_0
      //   3482: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3485: getfield 699	seventynine/sdk/UserProfileManager:prefsApplistFirstTime	Landroid/content/SharedPreferences;
      //   3488: invokeinterface 127 1 0
      //   3493: astore 16
      //   3495: aload 16
      //   3497: ldc_w 701
      //   3500: iconst_1
      //   3501: invokeinterface 135 3 0
      //   3506: pop
      //   3507: aload 16
      //   3509: invokeinterface 138 1 0
      //   3514: pop
      //   3515: aload 5
      //   3517: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3520: ldc_w 720
      //   3523: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3526: ifne +14 -> 3540
      //   3529: aload 15
      //   3531: ldc_w 725
      //   3534: aload 5
      //   3536: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3539: pop
      //   3540: aload 6
      //   3542: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3545: ldc_w 720
      //   3548: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3551: ifne +16 -> 3567
      //   3554: aload 15
      //   3556: ldc_w 727
      //   3559: aload 6
      //   3561: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3564: pop
      //   3565: iconst_1
      //   3566: istore_2
      //   3567: aload 7
      //   3569: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3572: ldc_w 720
      //   3575: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3578: ifne +16 -> 3594
      //   3581: aload 15
      //   3583: ldc_w 729
      //   3586: aload 7
      //   3588: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3591: pop
      //   3592: iconst_1
      //   3593: istore_2
      //   3594: aload 8
      //   3596: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3599: ldc_w 720
      //   3602: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3605: ifne +16 -> 3621
      //   3608: aload 15
      //   3610: ldc_w 731
      //   3613: aload 8
      //   3615: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3618: pop
      //   3619: iconst_1
      //   3620: istore_2
      //   3621: aload 9
      //   3623: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3626: ldc_w 720
      //   3629: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3632: ifne +14 -> 3646
      //   3635: aload 15
      //   3637: ldc_w 733
      //   3640: aload 9
      //   3642: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3645: pop
      //   3646: aload 10
      //   3648: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3651: ldc_w 720
      //   3654: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3657: ifne +14 -> 3671
      //   3660: aload 15
      //   3662: ldc_w 735
      //   3665: aload 10
      //   3667: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3670: pop
      //   3671: aload 12
      //   3673: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3676: ldc_w 720
      //   3679: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3682: ifne +16 -> 3698
      //   3685: aload 15
      //   3687: ldc_w 737
      //   3690: aload 12
      //   3692: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3695: pop
      //   3696: iconst_1
      //   3697: istore_2
      //   3698: aload 13
      //   3700: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3703: ldc_w 720
      //   3706: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3709: ifne +16 -> 3725
      //   3712: aload 15
      //   3714: ldc_w 739
      //   3717: aload 13
      //   3719: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3722: pop
      //   3723: iconst_1
      //   3724: istore_2
      //   3725: aload 11
      //   3727: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3730: ldc_w 720
      //   3733: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3736: ifne +16 -> 3752
      //   3739: aload 15
      //   3741: ldc_w 489
      //   3744: aload 11
      //   3746: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3749: pop
      //   3750: iconst_1
      //   3751: istore_2
      //   3752: aload_0
      //   3753: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3756: getfield 742	seventynine/sdk/UserProfileManager:geoInfo	Ljava/lang/String;
      //   3759: invokevirtual 746	java/lang/String:length	()I
      //   3762: bipush 10
      //   3764: if_icmple +21 -> 3785
      //   3767: aload 15
      //   3769: ldc_w 748
      //   3772: aload_0
      //   3773: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3776: getfield 742	seventynine/sdk/UserProfileManager:geoInfo	Ljava/lang/String;
      //   3779: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3782: pop
      //   3783: iconst_1
      //   3784: istore_2
      //   3785: aload 14
      //   3787: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3790: ldc_w 720
      //   3793: invokevirtual 724	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3796: ifne +16 -> 3812
      //   3799: aload 15
      //   3801: ldc_w 750
      //   3804: aload 14
      //   3806: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3809: pop
      //   3810: iconst_1
      //   3811: istore_2
      //   3812: aload_0
      //   3813: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3816: iconst_1
      //   3817: putfield 99	seventynine/sdk/UserProfileManager:flagCollectionData	Z
      //   3820: iload_2
      //   3821: ifeq +1112 -> 4933
      //   3824: aload 15
      //   3826: invokevirtual 718	org/json/JSONObject:toString	()Ljava/lang/String;
      //   3829: astore 5
      //   3831: new 711	java/util/ArrayList
      //   3834: dup
      //   3835: iconst_1
      //   3836: invokespecial 753	java/util/ArrayList:<init>	(I)V
      //   3839: astore 6
      //   3841: aload 6
      //   3843: new 755	org/apache/http/message/BasicNameValuePair
      //   3846: dup
      //   3847: ldc 101
      //   3849: aload 5
      //   3851: invokestatic 760	seventynine/sdk/Parameter:doubleBase64	(Ljava/lang/String;)Ljava/lang/String;
      //   3854: invokespecial 763	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   3857: invokeinterface 768 2 0
      //   3862: pop
      //   3863: aload_1
      //   3864: new 770	org/apache/http/client/entity/UrlEncodedFormEntity
      //   3867: dup
      //   3868: aload 6
      //   3870: invokespecial 773	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;)V
      //   3873: invokevirtual 777	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
      //   3876: aload_1
      //   3877: ldc_w 779
      //   3880: ldc_w 781
      //   3883: invokestatic 784	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   3886: invokevirtual 787	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
      //   3889: new 789	org/apache/http/params/BasicHttpParams
      //   3892: dup
      //   3893: invokespecial 790	org/apache/http/params/BasicHttpParams:<init>	()V
      //   3896: astore 6
      //   3898: aload 6
      //   3900: sipush 3000
      //   3903: invokestatic 796	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
      //   3906: aload 6
      //   3908: sipush 3000
      //   3911: invokestatic 799	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
      //   3914: new 801	org/apache/http/impl/client/DefaultHttpClient
      //   3917: dup
      //   3918: aload 6
      //   3920: invokespecial 804	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
      //   3923: aload_1
      //   3924: invokevirtual 808	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
      //   3927: checkcast 810	org/apache/http/message/BasicHttpResponse
      //   3930: astore_1
      //   3931: aload_0
      //   3932: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3935: aload_1
      //   3936: invokevirtual 814	org/apache/http/message/BasicHttpResponse:getStatusLine	()Lorg/apache/http/StatusLine;
      //   3939: invokeinterface 819 1 0
      //   3944: putfield 822	seventynine/sdk/UserProfileManager:byteResponse	I
      //   3947: aload_0
      //   3948: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3951: iconst_1
      //   3952: putfield 99	seventynine/sdk/UserProfileManager:flagCollectionData	Z
      //   3955: aload_0
      //   3956: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3959: getfield 822	seventynine/sdk/UserProfileManager:byteResponse	I
      //   3962: sipush 200
      //   3965: if_icmpne +640 -> 4605
      //   3968: aload_1
      //   3969: invokevirtual 826	org/apache/http/message/BasicHttpResponse:getEntity	()Lorg/apache/http/HttpEntity;
      //   3972: invokeinterface 832 1 0
      //   3977: astore_1
      //   3978: aload_1
      //   3979: ifnull +589 -> 4568
      //   3982: aload_1
      //   3983: invokestatic 836	seventynine/sdk/UserProfileManager:convertInputStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
      //   3986: astore_1
      //   3987: aload_0
      //   3988: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   3991: invokestatic 840	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   3994: invokevirtual 844	java/lang/Thread:getStackTrace	()[Ljava/lang/StackTraceElement;
      //   3997: iconst_2
      //   3998: aaload
      //   3999: invokevirtual 849	java/lang/StackTraceElement:getLineNumber	()I
      //   4002: putfield 111	seventynine/sdk/UserProfileManager:lineNumber	I
      //   4005: ldc 101
      //   4007: new 155	java/lang/StringBuilder
      //   4010: dup
      //   4011: ldc_w 851
      //   4014: invokespecial 852	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   4017: aload 5
      //   4019: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4022: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4025: aload_0
      //   4026: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4029: getfield 107	seventynine/sdk/UserProfileManager:className	Ljava/lang/String;
      //   4032: aload_0
      //   4033: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4036: getfield 111	seventynine/sdk/UserProfileManager:lineNumber	I
      //   4039: invokestatic 117	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
      //   4042: ldc 101
      //   4044: new 155	java/lang/StringBuilder
      //   4047: dup
      //   4048: ldc_w 854
      //   4051: invokespecial 852	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   4054: aload_1
      //   4055: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   4058: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4061: aload_0
      //   4062: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4065: getfield 107	seventynine/sdk/UserProfileManager:className	Ljava/lang/String;
      //   4068: aload_0
      //   4069: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4072: getfield 111	seventynine/sdk/UserProfileManager:lineNumber	I
      //   4075: invokestatic 117	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
      //   4078: aload_0
      //   4079: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4082: iconst_0
      //   4083: putfield 211	seventynine/sdk/UserProfileManager:retrycounter	I
      //   4086: aload_0
      //   4087: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4090: iconst_0
      //   4091: putfield 214	seventynine/sdk/UserProfileManager:retryFlag	Z
      //   4094: aload_0
      //   4095: aload_1
      //   4096: invokespecial 856	seventynine/sdk/UserProfileManager$HttpAsyncTask:UpdateUI	(Ljava/lang/String;)V
      //   4099: aload_1
      //   4100: areturn
      //   4101: astore 9
      //   4103: aload_0
      //   4104: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4107: invokestatic 840	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   4110: invokevirtual 844	java/lang/Thread:getStackTrace	()[Ljava/lang/StackTraceElement;
      //   4113: iconst_2
      //   4114: aaload
      //   4115: invokevirtual 849	java/lang/StackTraceElement:getLineNumber	()I
      //   4118: putfield 111	seventynine/sdk/UserProfileManager:lineNumber	I
      //   4121: ldc -53
      //   4123: new 155	java/lang/StringBuilder
      //   4126: dup
      //   4127: ldc_w 858
      //   4130: invokespecial 852	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   4133: aload 9
      //   4135: invokevirtual 861	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4138: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4141: aload_0
      //   4142: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4145: getfield 107	seventynine/sdk/UserProfileManager:className	Ljava/lang/String;
      //   4148: aload_0
      //   4149: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4152: getfield 111	seventynine/sdk/UserProfileManager:lineNumber	I
      //   4155: invokestatic 117	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
      //   4158: goto -3187 -> 971
      //   4161: astore_1
      //   4162: aload_0
      //   4163: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4166: getfield 211	seventynine/sdk/UserProfileManager:retrycounter	I
      //   4169: iconst_1
      //   4170: if_icmpge +472 -> 4642
      //   4173: aload_0
      //   4174: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4177: iconst_0
      //   4178: putfield 214	seventynine/sdk/UserProfileManager:retryFlag	Z
      //   4181: aload_0
      //   4182: aconst_null
      //   4183: invokespecial 856	seventynine/sdk/UserProfileManager$HttpAsyncTask:UpdateUI	(Ljava/lang/String;)V
      //   4186: aload_1
      //   4187: invokevirtual 193	java/lang/Exception:toString	()Ljava/lang/String;
      //   4190: ldc_w 863
      //   4193: ldc 27
      //   4195: ldc 27
      //   4197: ldc 27
      //   4199: ldc 27
      //   4201: ldc -59
      //   4203: iconst_0
      //   4204: invokestatic 201	seventynine/sdk/DebugTrack:SendExceptionReport	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
      //   4207: ldc -53
      //   4209: aload_1
      //   4210: invokevirtual 193	java/lang/Exception:toString	()Ljava/lang/String;
      //   4213: ldc -59
      //   4215: iconst_0
      //   4216: ldc_w 863
      //   4219: invokestatic 206	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
      //   4222: aconst_null
      //   4223: areturn
      //   4224: astore 9
      //   4226: aload_0
      //   4227: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4230: invokestatic 840	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   4233: invokevirtual 844	java/lang/Thread:getStackTrace	()[Ljava/lang/StackTraceElement;
      //   4236: iconst_2
      //   4237: aaload
      //   4238: invokevirtual 849	java/lang/StackTraceElement:getLineNumber	()I
      //   4241: putfield 111	seventynine/sdk/UserProfileManager:lineNumber	I
      //   4244: ldc -53
      //   4246: new 155	java/lang/StringBuilder
      //   4249: dup
      //   4250: ldc_w 858
      //   4253: invokespecial 852	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   4256: aload 9
      //   4258: invokevirtual 861	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   4261: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   4264: aload_0
      //   4265: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4268: getfield 107	seventynine/sdk/UserProfileManager:className	Ljava/lang/String;
      //   4271: aload_0
      //   4272: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4275: getfield 111	seventynine/sdk/UserProfileManager:lineNumber	I
      //   4278: invokestatic 117	seventynine/sdk/DebugTrack:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
      //   4281: goto -3252 -> 1029
      //   4284: aload 12
      //   4286: aload 13
      //   4288: iload_3
      //   4289: aaload
      //   4290: ldc_w 865
      //   4293: invokevirtual 637	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   4296: iconst_0
      //   4297: aaload
      //   4298: aload 13
      //   4300: iload_3
      //   4301: aaload
      //   4302: ldc_w 865
      //   4305: invokevirtual 637	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   4308: iconst_1
      //   4309: aaload
      //   4310: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4313: pop
      //   4314: iload_3
      //   4315: iconst_1
      //   4316: iadd
      //   4317: istore_3
      //   4318: goto -1338 -> 2980
      //   4321: aload 16
      //   4323: invokeinterface 174 1 0
      //   4328: checkcast 37	java/util/HashMap
      //   4331: astore 17
      //   4333: aload 17
      //   4335: invokevirtual 869	java/util/HashMap:keySet	()Ljava/util/Set;
      //   4338: invokeinterface 872 1 0
      //   4343: astore 18
      //   4345: aload 18
      //   4347: invokeinterface 44 1 0
      //   4352: ifeq -887 -> 3465
      //   4355: aload 18
      //   4357: invokeinterface 174 1 0
      //   4362: checkcast 176	java/lang/String
      //   4365: astore 19
      //   4367: aload 14
      //   4369: aload 19
      //   4371: aload 17
      //   4373: aload 19
      //   4375: invokevirtual 876	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   4378: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4381: pop
      //   4382: goto -37 -> 4345
      //   4385: aload_0
      //   4386: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4389: getfield 699	seventynine/sdk/UserProfileManager:prefsApplistFirstTime	Landroid/content/SharedPreferences;
      //   4392: ldc_w 701
      //   4395: iconst_1
      //   4396: invokeinterface 705 3 0
      //   4401: ifeq -886 -> 3515
      //   4404: getstatic 879	seventynine/sdk/UserProfile:arrayList	Ljava/util/ArrayList;
      //   4407: invokevirtual 714	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   4410: astore 16
      //   4412: aload 16
      //   4414: invokeinterface 44 1 0
      //   4419: ifeq -904 -> 3515
      //   4422: aload 16
      //   4424: invokeinterface 174 1 0
      //   4429: checkcast 37	java/util/HashMap
      //   4432: astore 17
      //   4434: aload 17
      //   4436: invokevirtual 869	java/util/HashMap:keySet	()Ljava/util/Set;
      //   4439: invokeinterface 872 1 0
      //   4444: astore 18
      //   4446: aload 18
      //   4448: invokeinterface 44 1 0
      //   4453: ifeq -41 -> 4412
      //   4456: aload 18
      //   4458: invokeinterface 174 1 0
      //   4463: checkcast 176	java/lang/String
      //   4466: astore 19
      //   4468: aload 14
      //   4470: aload 19
      //   4472: aload 17
      //   4474: aload 19
      //   4476: invokevirtual 876	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   4479: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4482: pop
      //   4483: goto -37 -> 4446
      //   4486: getstatic 879	seventynine/sdk/UserProfile:arrayList	Ljava/util/ArrayList;
      //   4489: invokevirtual 714	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   4492: astore 16
      //   4494: aload 16
      //   4496: invokeinterface 44 1 0
      //   4501: ifeq -986 -> 3515
      //   4504: aload 16
      //   4506: invokeinterface 174 1 0
      //   4511: checkcast 37	java/util/HashMap
      //   4514: astore 17
      //   4516: aload 17
      //   4518: invokevirtual 869	java/util/HashMap:keySet	()Ljava/util/Set;
      //   4521: invokeinterface 872 1 0
      //   4526: astore 18
      //   4528: aload 18
      //   4530: invokeinterface 44 1 0
      //   4535: ifeq -41 -> 4494
      //   4538: aload 18
      //   4540: invokeinterface 174 1 0
      //   4545: checkcast 176	java/lang/String
      //   4548: astore 19
      //   4550: aload 14
      //   4552: aload 19
      //   4554: aload 17
      //   4556: aload 19
      //   4558: invokevirtual 876	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   4561: invokevirtual 245	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4564: pop
      //   4565: goto -37 -> 4528
      //   4568: aload_0
      //   4569: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4572: getfield 211	seventynine/sdk/UserProfileManager:retrycounter	I
      //   4575: iconst_1
      //   4576: if_icmpge +18 -> 4594
      //   4579: aload_0
      //   4580: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4583: iconst_0
      //   4584: putfield 214	seventynine/sdk/UserProfileManager:retryFlag	Z
      //   4587: aload_0
      //   4588: aconst_null
      //   4589: invokespecial 856	seventynine/sdk/UserProfileManager$HttpAsyncTask:UpdateUI	(Ljava/lang/String;)V
      //   4592: aconst_null
      //   4593: areturn
      //   4594: aload_0
      //   4595: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4598: iconst_1
      //   4599: putfield 214	seventynine/sdk/UserProfileManager:retryFlag	Z
      //   4602: goto -15 -> 4587
      //   4605: aload_0
      //   4606: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4609: getfield 211	seventynine/sdk/UserProfileManager:retrycounter	I
      //   4612: iconst_1
      //   4613: if_icmpge +18 -> 4631
      //   4616: aload_0
      //   4617: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4620: iconst_0
      //   4621: putfield 214	seventynine/sdk/UserProfileManager:retryFlag	Z
      //   4624: aload_0
      //   4625: aconst_null
      //   4626: invokespecial 856	seventynine/sdk/UserProfileManager$HttpAsyncTask:UpdateUI	(Ljava/lang/String;)V
      //   4629: aconst_null
      //   4630: areturn
      //   4631: aload_0
      //   4632: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4635: iconst_1
      //   4636: putfield 214	seventynine/sdk/UserProfileManager:retryFlag	Z
      //   4639: goto -15 -> 4624
      //   4642: aload_0
      //   4643: getfield 14	seventynine/sdk/UserProfileManager$HttpAsyncTask:this$0	Lseventynine/sdk/UserProfileManager;
      //   4646: iconst_1
      //   4647: putfield 214	seventynine/sdk/UserProfileManager:retryFlag	Z
      //   4650: goto -469 -> 4181
      //   4653: astore 14
      //   4655: goto -1249 -> 3406
      //   4658: astore 14
      //   4660: goto -1312 -> 3348
      //   4663: astore 14
      //   4665: goto -1375 -> 3290
      //   4668: astore 14
      //   4670: goto -1438 -> 3232
      //   4673: astore 14
      //   4675: goto -1501 -> 3174
      //   4678: astore 14
      //   4680: goto -1564 -> 3116
      //   4683: astore 14
      //   4685: goto -1627 -> 3058
      //   4688: astore 13
      //   4690: goto -1699 -> 2991
      //   4693: astore 13
      //   4695: goto -1774 -> 2921
      //   4698: astore 13
      //   4700: goto -1837 -> 2863
      //   4703: astore 13
      //   4705: goto -1900 -> 2805
      //   4708: astore 13
      //   4710: goto -1963 -> 2747
      //   4713: astore 13
      //   4715: goto -2026 -> 2689
      //   4718: astore 13
      //   4720: goto -2089 -> 2631
      //   4723: astore 13
      //   4725: goto -2152 -> 2573
      //   4728: astore 13
      //   4730: goto -2215 -> 2515
      //   4733: astore 13
      //   4735: goto -2278 -> 2457
      //   4738: astore 13
      //   4740: goto -2341 -> 2399
      //   4743: astore 13
      //   4745: goto -2404 -> 2341
      //   4748: astore 13
      //   4750: goto -2467 -> 2283
      //   4753: astore 13
      //   4755: goto -2530 -> 2225
      //   4758: astore 13
      //   4760: goto -2593 -> 2167
      //   4763: astore 13
      //   4765: goto -2656 -> 2109
      //   4768: astore 13
      //   4770: goto -2719 -> 2051
      //   4773: astore 13
      //   4775: goto -2782 -> 1993
      //   4778: astore 13
      //   4780: goto -2845 -> 1935
      //   4783: astore 12
      //   4785: goto -2917 -> 1868
      //   4788: astore 11
      //   4790: goto -2989 -> 1801
      //   4793: astore 11
      //   4795: goto -3052 -> 1743
      //   4798: astore 10
      //   4800: goto -3124 -> 1676
      //   4803: astore 10
      //   4805: goto -3187 -> 1618
      //   4808: astore 10
      //   4810: goto -3250 -> 1560
      //   4813: astore 10
      //   4815: goto -3313 -> 1502
      //   4818: astore 10
      //   4820: goto -3376 -> 1444
      //   4823: astore 9
      //   4825: goto -3448 -> 1377
      //   4828: astore 9
      //   4830: goto -3511 -> 1319
      //   4833: astore 9
      //   4835: goto -3574 -> 1261
      //   4838: astore 9
      //   4840: goto -3637 -> 1203
      //   4843: astore 9
      //   4845: goto -3700 -> 1145
      //   4848: astore 9
      //   4850: goto -3763 -> 1087
      //   4853: astore 8
      //   4855: goto -3951 -> 904
      //   4858: astore 8
      //   4860: goto -4014 -> 846
      //   4863: astore 8
      //   4865: goto -4077 -> 788
      //   4868: astore 8
      //   4870: goto -4140 -> 730
      //   4873: astore 7
      //   4875: goto -4212 -> 663
      //   4878: astore 7
      //   4880: goto -4275 -> 605
      //   4883: astore 7
      //   4885: goto -4338 -> 547
      //   4888: astore 7
      //   4890: goto -4401 -> 489
      //   4893: astore 7
      //   4895: goto -4464 -> 431
      //   4898: astore 6
      //   4900: iload_3
      //   4901: istore_2
      //   4902: goto -4538 -> 364
      //   4905: astore 6
      //   4907: iload_2
      //   4908: istore_3
      //   4909: goto -4611 -> 298
      //   4912: astore 6
      //   4914: iload_3
      //   4915: istore_2
      //   4916: goto -4684 -> 232
      //   4919: astore 6
      //   4921: iload_2
      //   4922: istore_3
      //   4923: goto -4757 -> 166
      //   4926: astore 6
      //   4928: iload_3
      //   4929: istore_2
      //   4930: goto -4830 -> 100
      //   4933: ldc 27
      //   4935: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4936	0	this	HttpAsyncTask
      //   0	4936	1	paramString	String
      //   35	4895	2	i	int
      //   1	4928	3	j	int
      //   2983	6	4	k	int
      //   18	4000	5	localObject1	Object
      //   371	3548	6	localObject2	Object
      //   4898	1	6	localException1	Exception
      //   4905	1	6	localException2	Exception
      //   4912	1	6	localException3	Exception
      //   4919	1	6	localException4	Exception
      //   4926	1	6	localException5	Exception
      //   670	2917	7	localJSONObject1	JSONObject
      //   4873	1	7	localException6	Exception
      //   4878	1	7	localException7	Exception
      //   4883	1	7	localException8	Exception
      //   4888	1	7	localException9	Exception
      //   4893	1	7	localException10	Exception
      //   911	2703	8	localJSONObject2	JSONObject
      //   4853	1	8	localException11	Exception
      //   4858	1	8	localException12	Exception
      //   4863	1	8	localException13	Exception
      //   4868	1	8	localException14	Exception
      //   1384	2257	9	localJSONObject3	JSONObject
      //   4101	33	9	localException15	Exception
      //   4224	33	9	localException16	Exception
      //   4823	1	9	localException17	Exception
      //   4828	1	9	localException18	Exception
      //   4833	1	9	localException19	Exception
      //   4838	1	9	localException20	Exception
      //   4843	1	9	localException21	Exception
      //   4848	1	9	localException22	Exception
      //   1683	1983	10	localJSONObject4	JSONObject
      //   4798	1	10	localException23	Exception
      //   4803	1	10	localException24	Exception
      //   4808	1	10	localException25	Exception
      //   4813	1	10	localException26	Exception
      //   4818	1	10	localException27	Exception
      //   1808	1937	11	localJSONObject5	JSONObject
      //   4788	1	11	localException28	Exception
      //   4793	1	11	localException29	Exception
      //   1875	2410	12	localJSONObject6	JSONObject
      //   4783	1	12	localException30	Exception
      //   2976	1323	13	localObject3	Object
      //   4688	1	13	localException31	Exception
      //   4693	1	13	localException32	Exception
      //   4698	1	13	localException33	Exception
      //   4703	1	13	localException34	Exception
      //   4708	1	13	localException35	Exception
      //   4713	1	13	localException36	Exception
      //   4718	1	13	localException37	Exception
      //   4723	1	13	localException38	Exception
      //   4728	1	13	localException39	Exception
      //   4733	1	13	localException40	Exception
      //   4738	1	13	localException41	Exception
      //   4743	1	13	localException42	Exception
      //   4748	1	13	localException43	Exception
      //   4753	1	13	localException44	Exception
      //   4758	1	13	localException45	Exception
      //   4763	1	13	localException46	Exception
      //   4768	1	13	localException47	Exception
      //   4773	1	13	localException48	Exception
      //   4778	1	13	localException49	Exception
      //   3421	1130	14	localJSONObject7	JSONObject
      //   4653	1	14	localException50	Exception
      //   4658	1	14	localException51	Exception
      //   4663	1	14	localException52	Exception
      //   4668	1	14	localException53	Exception
      //   4673	1	14	localException54	Exception
      //   4678	1	14	localException55	Exception
      //   4683	1	14	localException56	Exception
      //   3430	395	15	localJSONObject8	JSONObject
      //   3463	1042	16	localObject4	Object
      //   4331	224	17	localHashMap	HashMap
      //   4343	196	18	localIterator	Iterator
      //   4365	192	19	str	String
      // Exception table:
      //   from	to	target	type
      //   913	971	4101	java/lang/Exception
      //   2	34	4161	java/lang/Exception
      //   364	373	4161	java/lang/Exception
      //   663	672	4161	java/lang/Exception
      //   904	913	4161	java/lang/Exception
      //   1377	1386	4161	java/lang/Exception
      //   1676	1685	4161	java/lang/Exception
      //   1801	1810	4161	java/lang/Exception
      //   1868	1877	4161	java/lang/Exception
      //   2991	3000	4161	java/lang/Exception
      //   3406	3465	4161	java/lang/Exception
      //   3465	3515	4161	java/lang/Exception
      //   3515	3540	4161	java/lang/Exception
      //   3540	3565	4161	java/lang/Exception
      //   3567	3592	4161	java/lang/Exception
      //   3594	3619	4161	java/lang/Exception
      //   3621	3646	4161	java/lang/Exception
      //   3646	3671	4161	java/lang/Exception
      //   3671	3696	4161	java/lang/Exception
      //   3698	3723	4161	java/lang/Exception
      //   3725	3750	4161	java/lang/Exception
      //   3752	3783	4161	java/lang/Exception
      //   3785	3810	4161	java/lang/Exception
      //   3812	3820	4161	java/lang/Exception
      //   3824	3978	4161	java/lang/Exception
      //   3982	4099	4161	java/lang/Exception
      //   4103	4158	4161	java/lang/Exception
      //   4226	4281	4161	java/lang/Exception
      //   4321	4345	4161	java/lang/Exception
      //   4345	4382	4161	java/lang/Exception
      //   4385	4412	4161	java/lang/Exception
      //   4412	4446	4161	java/lang/Exception
      //   4446	4483	4161	java/lang/Exception
      //   4486	4494	4161	java/lang/Exception
      //   4494	4528	4161	java/lang/Exception
      //   4528	4565	4161	java/lang/Exception
      //   4568	4587	4161	java/lang/Exception
      //   4587	4592	4161	java/lang/Exception
      //   4594	4602	4161	java/lang/Exception
      //   4605	4624	4161	java/lang/Exception
      //   4624	4629	4161	java/lang/Exception
      //   4631	4639	4161	java/lang/Exception
      //   971	1029	4224	java/lang/Exception
      //   3348	3406	4653	java/lang/Exception
      //   3290	3348	4658	java/lang/Exception
      //   3232	3290	4663	java/lang/Exception
      //   3174	3232	4668	java/lang/Exception
      //   3116	3174	4673	java/lang/Exception
      //   3058	3116	4678	java/lang/Exception
      //   3000	3058	4683	java/lang/Exception
      //   2921	2978	4688	java/lang/Exception
      //   2980	2985	4688	java/lang/Exception
      //   4284	4314	4688	java/lang/Exception
      //   2863	2921	4693	java/lang/Exception
      //   2805	2863	4698	java/lang/Exception
      //   2747	2805	4703	java/lang/Exception
      //   2689	2747	4708	java/lang/Exception
      //   2631	2689	4713	java/lang/Exception
      //   2573	2631	4718	java/lang/Exception
      //   2515	2573	4723	java/lang/Exception
      //   2457	2515	4728	java/lang/Exception
      //   2399	2457	4733	java/lang/Exception
      //   2341	2399	4738	java/lang/Exception
      //   2283	2341	4743	java/lang/Exception
      //   2225	2283	4748	java/lang/Exception
      //   2167	2225	4753	java/lang/Exception
      //   2109	2167	4758	java/lang/Exception
      //   2051	2109	4763	java/lang/Exception
      //   1993	2051	4768	java/lang/Exception
      //   1935	1993	4773	java/lang/Exception
      //   1877	1935	4778	java/lang/Exception
      //   1810	1868	4783	java/lang/Exception
      //   1743	1801	4788	java/lang/Exception
      //   1685	1743	4793	java/lang/Exception
      //   1618	1676	4798	java/lang/Exception
      //   1560	1618	4803	java/lang/Exception
      //   1502	1560	4808	java/lang/Exception
      //   1444	1502	4813	java/lang/Exception
      //   1386	1444	4818	java/lang/Exception
      //   1319	1377	4823	java/lang/Exception
      //   1261	1319	4828	java/lang/Exception
      //   1203	1261	4833	java/lang/Exception
      //   1145	1203	4838	java/lang/Exception
      //   1087	1145	4843	java/lang/Exception
      //   1029	1087	4848	java/lang/Exception
      //   846	904	4853	java/lang/Exception
      //   788	846	4858	java/lang/Exception
      //   730	788	4863	java/lang/Exception
      //   672	730	4868	java/lang/Exception
      //   605	663	4873	java/lang/Exception
      //   547	605	4878	java/lang/Exception
      //   489	547	4883	java/lang/Exception
      //   431	489	4888	java/lang/Exception
      //   373	431	4893	java/lang/Exception
      //   300	317	4898	java/lang/Exception
      //   319	331	4898	java/lang/Exception
      //   333	362	4898	java/lang/Exception
      //   234	251	4905	java/lang/Exception
      //   253	265	4905	java/lang/Exception
      //   267	296	4905	java/lang/Exception
      //   168	185	4912	java/lang/Exception
      //   187	199	4912	java/lang/Exception
      //   201	230	4912	java/lang/Exception
      //   102	119	4919	java/lang/Exception
      //   121	133	4919	java/lang/Exception
      //   135	164	4919	java/lang/Exception
      //   36	53	4926	java/lang/Exception
      //   55	67	4926	java/lang/Exception
      //   69	98	4926	java/lang/Exception
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      return POST(paramVarArgs[0]);
    }
    
    protected void onPostExecute(String paramString) {}
  }
}
