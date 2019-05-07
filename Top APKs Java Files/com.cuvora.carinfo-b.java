import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public final class b
{
  public b() {}
  
  public static String a(Map<String, String> paramMap)
  {
    String str1 = "{";
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str1);
      localStringBuilder.append("\"");
      localStringBuilder.append(str2);
      localStringBuilder.append("\":\"");
      localStringBuilder.append((String)paramMap.get(str2));
      localStringBuilder.append("\",");
      str1 = localStringBuilder.toString();
    }
    paramMap = new StringBuilder();
    paramMap.append(str1.substring(0, str1.length() - 1));
    paramMap.append("}");
    return paramMap.toString();
  }
  
  public static Map a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("com.survey.one.library.shared.preference", 0);
    localObject3 = "NOT_SET";
    try
    {
      localObject4 = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (((NetworkInfo)localObject4).getType() == 0)
      {
        localObject1 = "MOBILE";
      }
      else
      {
        localObject1 = localObject3;
        if (((NetworkInfo)localObject4).getType() == 1) {
          localObject1 = "WIFI";
        }
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        Object localObject1;
        localObject2 = localObject3;
      }
    }
    if (localSharedPreferences.getString("allowLocationDetection", "true").equalsIgnoreCase("true")) {}
    try
    {
      localObject7 = new c().a(paramContext);
      localObject8 = new Geocoder(paramContext, Locale.getDefault());
      localObject3 = "NOT_SET";
      localObject4 = "NOT_SET";
      str1 = "NOT_SET";
      localObject5 = "NOT_SET";
      localObject6 = "";
      localObject9 = new StringBuilder();
      ((StringBuilder)localObject9).append(((Location)localObject7).getLatitude());
      localObject11 = ((StringBuilder)localObject9).toString();
      localObject9 = new StringBuilder();
      ((StringBuilder)localObject9).append(((Location)localObject7).getLongitude());
      str3 = ((StringBuilder)localObject9).toString();
      localObject7 = ((Geocoder)localObject8).getFromLocation(((Location)localObject7).getLatitude(), ((Location)localObject7).getLongitude(), 1);
      if (((List)localObject7).size() <= 0) {
        break label1705;
      }
      localObject6 = ((Address)((List)localObject7).get(0)).getLocality();
      localObject4 = ((Address)((List)localObject7).get(0)).getAdminArea();
      str1 = ((Address)((List)localObject7).get(0)).getCountryCode();
      localObject5 = ((Address)((List)localObject7).get(0)).getPostalCode();
      localObject3 = "";
      int i = 0;
      while (i < ((Address)((List)localObject7).get(0)).getMaxAddressLineIndex())
      {
        localObject8 = new StringBuilder();
        ((StringBuilder)localObject8).append((String)localObject3);
        ((StringBuilder)localObject8).append(((Address)((List)localObject7).get(0)).getAddressLine(i));
        ((StringBuilder)localObject8).append(" | ");
        localObject3 = ((StringBuilder)localObject8).toString();
        i += 1;
      }
      localObject7 = localObject3;
      localObject8 = localObject6;
      localObject9 = localObject4;
      str2 = str1;
      localObject10 = localObject5;
      if (((String)localObject3).equalsIgnoreCase(""))
      {
        localObject7 = "NOT_SET";
        localObject3 = localObject6;
        localObject6 = localObject7;
      }
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        String str1;
        Object localObject5;
        Object localObject6;
        Object localObject11;
        String str3;
        continue;
        Object localObject7 = localObject6;
        Object localObject8 = localException3;
        Object localObject9 = localObject4;
        String str2 = str1;
        Object localObject10 = localObject5;
      }
    }
    localObject3 = localSharedPreferences.edit();
    ((SharedPreferences.Editor)localObject3).putString("country_code", str2);
    ((SharedPreferences.Editor)localObject3).putString("state", (String)localObject9);
    ((SharedPreferences.Editor)localObject3).putString("city", (String)localObject8);
    ((SharedPreferences.Editor)localObject3).putString("street", (String)localObject7);
    ((SharedPreferences.Editor)localObject3).putString("pincode", (String)localObject10);
    ((SharedPreferences.Editor)localObject3).putString("lat", (String)localObject11);
    ((SharedPreferences.Editor)localObject3).putString("long", str3);
    ((SharedPreferences.Editor)localObject3).commit();
    localObject7 = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
    localObject8 = Build.MANUFACTURER;
    localObject9 = Build.MODEL;
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append(Build.VERSION.SDK_INT);
    str2 = ((StringBuilder)localObject3).toString();
    localObject10 = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "android_id");
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3) {
      str1 = "Tablet";
    } else {
      str1 = "Phone";
    }
    localObject5 = paramContext.getPackageManager();
    localObject4 = "";
    localObject3 = "";
    try
    {
      localObject11 = ((PackageManager)localObject5).getInstalledApplications(128).iterator();
      for (;;)
      {
        localObject6 = localObject4;
        localObject5 = localObject3;
        if (!((Iterator)localObject11).hasNext()) {
          break;
        }
        localObject5 = (ApplicationInfo)((Iterator)localObject11).next();
        if ((((ApplicationInfo)localObject5).flags & 0x1) == 1)
        {
          localObject6 = new StringBuilder();
          ((StringBuilder)localObject6).append((String)localObject4);
          ((StringBuilder)localObject6).append(",");
          ((StringBuilder)localObject6).append(((ApplicationInfo)localObject5).packageName);
          localObject4 = ((StringBuilder)localObject6).toString();
        }
        else
        {
          localObject6 = new StringBuilder();
          ((StringBuilder)localObject6).append((String)localObject3);
          ((StringBuilder)localObject6).append(",");
          ((StringBuilder)localObject6).append(((ApplicationInfo)localObject5).packageName);
          localObject3 = ((StringBuilder)localObject6).toString();
        }
      }
    }
    catch (Exception localException3)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject6 = "TransactionTooLargeException";
    localObject5 = "TransactionTooLargeException";
    localObject3 = paramContext.getPackageName();
    paramContext = new HashMap();
    paramContext.put("last_displayed_survey_id", localSharedPreferences.getString("last_displayed_survey_id", "0000"));
    paramContext.put("app_id", localSharedPreferences.getString("app_id", ""));
    paramContext.put("publisher_key", localSharedPreferences.getString("publisher_key", ""));
    paramContext.put("survey_test_code", localSharedPreferences.getString("survey_test_code", ""));
    paramContext.put("device_id", localObject10);
    paramContext.put("google_ad_id", localSharedPreferences.getString("google_ad_id", "NOT_SET"));
    paramContext.put("app_package", localObject3);
    paramContext.put("language", Locale.getDefault().getDisplayLanguage());
    paramContext.put("end_user_email", "NOT_SET");
    paramContext.put("end_user_name", localSharedPreferences.getString("end_user_name", "NOT_SET"));
    paramContext.put("os_type", "Android");
    paramContext.put("device_type", str1);
    paramContext.put("age_by_app", localSharedPreferences.getString("age_by_app", "NOT_SET"));
    paramContext.put("gender_by_app", localSharedPreferences.getString("gender_by_app", "NOT_SET"));
    paramContext.put("email_by_app", localSharedPreferences.getString("email_by_app", "NOT_SET"));
    paramContext.put("os_version", str2);
    paramContext.put("model", localObject9);
    paramContext.put("manufacturer", localObject8);
    paramContext.put("network_carrier", localObject7);
    paramContext.put("network_type", localObject1);
    paramContext.put("age", localSharedPreferences.getString("age", "NOT_SET"));
    paramContext.put("gender", localSharedPreferences.getString("gender", "NOT_SET"));
    paramContext.put("marital", localSharedPreferences.getString("marital", "NOT_SET"));
    paramContext.put("vehicle", localSharedPreferences.getString("vehicle", "NOT_SET"));
    paramContext.put("customField", localSharedPreferences.getString("customField", "NOT_SET"));
    paramContext.put("qualification", localSharedPreferences.getString("qualification", "NOT_SET"));
    paramContext.put("cellID", localSharedPreferences.getString("cellID", "NOT_SET"));
    paramContext.put("cellLocationCode", localSharedPreferences.getString("cellLocationCode", "NOT_SET"));
    paramContext.put("cellMCC", localSharedPreferences.getString("cellMCC", "NOT_SET"));
    paramContext.put("cellMNC", localSharedPreferences.getString("cellMNC", "NOT_SET"));
    paramContext.put("street", localSharedPreferences.getString("street", "NOT_SET"));
    paramContext.put("city", localSharedPreferences.getString("city", "NOT_SET"));
    paramContext.put("state", localSharedPreferences.getString("state", "NOT_SET"));
    paramContext.put("country_code", localSharedPreferences.getString("country_code", "NOT_SET"));
    paramContext.put("pincode", localSharedPreferences.getString("pincode", "NOT_SET"));
    paramContext.put("lat", localSharedPreferences.getString("lat", "NOT_SET"));
    paramContext.put("long", localSharedPreferences.getString("long", "NOT_SET"));
    paramContext.put("backfill_by_app", localSharedPreferences.getString("backfill_by_app", "1"));
    paramContext.put("cadre_by_app", localSharedPreferences.getString("cadre_by_app", ""));
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(localSharedPreferences.getInt("user_counter", 0));
    paramContext.put("user_counter", ((StringBuilder)localObject1).toString());
    paramContext.put("geographics", "NOT_SET");
    paramContext.put("ignore_survey_ids", localSharedPreferences.getString("ignore_survey_ids", ""));
    paramContext.put("list_of_apps_installed", localObject6);
    paramContext.put("list_of_apps_installed_by_user", localObject5);
    paramContext.put("last_fetch_request_timestamp", "NOT_SET");
    if ("0".equalsIgnoreCase("1"))
    {
      paramContext.put("list_of_apps_installed", "NOT_SET");
      paramContext.put("list_of_apps_installed_by_user", "NOT_SET");
      paramContext.put("city", "Jaipur");
      paramContext.put("state", "Rajasthan");
      paramContext.put("country_code", "IN");
      paramContext.put("pincode", "302033");
    }
    return paramContext;
  }
  
  public static ArrayList<NameValuePair> b(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localArrayList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    return localArrayList;
  }
}
