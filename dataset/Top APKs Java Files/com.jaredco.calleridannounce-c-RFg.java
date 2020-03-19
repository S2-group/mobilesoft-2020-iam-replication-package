package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RFg
{
  private TelephonyManager ˊ;
  private Context ˋ;
  private RFX ˎ;
  
  public RFg(Context paramContext)
  {
    this.ˋ = paramContext;
    this.ˎ = new RFX(paramContext);
    this.ˊ = ((TelephonyManager)this.ˋ.getSystemService("phone"));
  }
  
  private String ʻ()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        Object localObject = this.ˋ.getPackageManager();
        String str = "{\"apps\":[";
        Iterator localIterator = ((PackageManager)localObject).getInstalledApplications(128).iterator();
        if (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          if (localApplicationInfo.sourceDir.startsWith("/data/app/"))
          {
            str = str + "{\"title\":\"" + localApplicationInfo.loadLabel((PackageManager)localObject) + "\",";
            str = str + "\"pakeagename\":\"" + localApplicationInfo.packageName + "\"}, ";
            i += 1;
          }
        }
        else
        {
          localObject = str;
          if (i > 0) {
            localObject = str.substring(0, str.length() - 2);
          }
          str = (String)localObject + "]}";
          return str;
        }
      }
      catch (Exception localException)
      {
        return "";
      }
    }
  }
  
  private String ˊ(String paramString)
  {
    try
    {
      long l = this.ˋ.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      paramString = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new Date(l));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return "";
  }
  
  private String ˎ()
  {
    try
    {
      String str = Settings.Secure.getString(this.ˋ.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private String ˏ()
  {
    if (this.ˋ.getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      try
      {
        String str = this.ˊ.getDeviceId();
        if (str != null) {
          return str;
        }
      }
      catch (Exception localException)
      {
        return "";
      }
    }
    return "";
  }
  
  /* Error */
  private String ᐝ()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 23	c/RFg:ˎ	Lc/RFX;
    //   4: getstatic 191	c/RFL:ˋ	Lc/RFL;
    //   7: invokevirtual 194	c/RFX:ˊ	(Ljava/lang/Enum;)Ljava/lang/String;
    //   10: ldc 122
    //   12: invokevirtual 198	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   15: ifne +14 -> 29
    //   18: aload_0
    //   19: getfield 23	c/RFg:ˎ	Lc/RFX;
    //   22: getstatic 191	c/RFL:ˋ	Lc/RFL;
    //   25: invokevirtual 194	c/RFX:ˊ	(Ljava/lang/Enum;)Ljava/lang/String;
    //   28: areturn
    //   29: aconst_null
    //   30: astore_2
    //   31: aconst_null
    //   32: astore_3
    //   33: aconst_null
    //   34: astore_1
    //   35: aload_0
    //   36: getfield 17	c/RFg:ˋ	Landroid/content/Context;
    //   39: invokevirtual 168	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   42: invokestatic 204	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   45: astore 4
    //   47: aload 4
    //   49: astore_1
    //   50: aload_1
    //   51: astore_2
    //   52: aload_1
    //   53: ifnonnull +11 -> 64
    //   56: aload_0
    //   57: getfield 17	c/RFg:ˋ	Landroid/content/Context;
    //   60: invokestatic 204	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   63: astore_2
    //   64: aload_2
    //   65: astore_1
    //   66: aload_1
    //   67: invokevirtual 209	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
    //   70: astore_1
    //   71: aload_0
    //   72: getfield 23	c/RFg:ˎ	Lc/RFX;
    //   75: getstatic 191	c/RFL:ˋ	Lc/RFL;
    //   78: aload_1
    //   79: invokevirtual 212	c/RFX:ˊ	(Ljava/lang/Object;Ljava/lang/String;)V
    //   82: aload_1
    //   83: areturn
    //   84: astore_1
    //   85: aload_1
    //   86: invokevirtual 215	java/lang/NullPointerException:printStackTrace	()V
    //   89: ldc 122
    //   91: areturn
    //   92: astore_2
    //   93: aload_2
    //   94: invokevirtual 216	com/google/android/gms/common/GooglePlayServicesNotAvailableException:printStackTrace	()V
    //   97: goto -31 -> 66
    //   100: astore_3
    //   101: aload_2
    //   102: astore_1
    //   103: aload_3
    //   104: astore_2
    //   105: aload_2
    //   106: invokevirtual 217	com/google/android/gms/common/GooglePlayServicesRepairableException:printStackTrace	()V
    //   109: goto -43 -> 66
    //   112: astore_2
    //   113: aload_3
    //   114: astore_1
    //   115: aload_2
    //   116: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   119: goto -53 -> 66
    //   122: astore_2
    //   123: goto -8 -> 115
    //   126: astore_2
    //   127: goto -22 -> 105
    //   130: astore_2
    //   131: goto -38 -> 93
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	RFg
    //   34	49	1	localObject1	Object
    //   84	2	1	localNullPointerException	NullPointerException
    //   102	13	1	localObject2	Object
    //   30	35	2	localObject3	Object
    //   92	10	2	localGooglePlayServicesNotAvailableException1	com.google.android.gms.common.GooglePlayServicesNotAvailableException
    //   104	2	2	localObject4	Object
    //   112	4	2	localIOException1	java.io.IOException
    //   122	1	2	localIOException2	java.io.IOException
    //   126	1	2	localGooglePlayServicesRepairableException1	com.google.android.gms.common.GooglePlayServicesRepairableException
    //   130	1	2	localGooglePlayServicesNotAvailableException2	com.google.android.gms.common.GooglePlayServicesNotAvailableException
    //   32	1	3	localObject5	Object
    //   100	14	3	localGooglePlayServicesRepairableException2	com.google.android.gms.common.GooglePlayServicesRepairableException
    //   45	3	4	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
    // Exception table:
    //   from	to	target	type
    //   66	82	84	java/lang/NullPointerException
    //   35	47	92	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   35	47	100	com/google/android/gms/common/GooglePlayServicesRepairableException
    //   35	47	112	java/io/IOException
    //   56	64	122	java/io/IOException
    //   56	64	126	com/google/android/gms/common/GooglePlayServicesRepairableException
    //   56	64	130	com/google/android/gms/common/GooglePlayServicesNotAvailableException
  }
  
  public String ˊ()
  {
    try
    {
      String str1;
      if (Looper.getMainLooper().equals(Looper.myLooper()))
      {
        String str3 = new WebView(this.ˋ).getSettings().getUserAgentString();
        str1 = str3;
        if (!str3.equals(""))
        {
          this.ˎ.ˊ(REZ.ﹳ, str3);
          return str3;
        }
      }
      else
      {
        str1 = this.ˎ.ˊ(REZ.ﹳ);
        return str1;
      }
    }
    catch (Exception localException)
    {
      String str2 = this.ˎ.ˊ(REZ.ﹳ);
      return str2;
    }
  }
  
  public Map<REZ, String> ˊ(Map<REZ, String> paramMap)
  {
    String str = ʻ();
    if (!str.equals("")) {
      paramMap.put(REZ.ᐨ, str);
    }
    return paramMap;
  }
  
  public String ˋ()
  {
    try
    {
      String str = this.ˋ.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public Map<REZ, String> ˋ(Map<REZ, String> paramMap)
  {
    Object localObject = ˋ();
    if (!((String)localObject).equals(""))
    {
      paramMap.put(REZ.ʽ, localObject);
      paramMap.put(REZ.ͺ, localObject);
    }
    localObject = ˊ((String)localObject);
    if (!((String)localObject).equals("")) {
      paramMap.put(REZ.ˈ, localObject);
    }
    if (!"7".equals("")) {
      paramMap.put(REZ.ـ, "7");
    }
    if (!"lite".equals("")) {
      paramMap.put(REZ.ᐧ, "lite");
    }
    localObject = ᐝ();
    if (!((String)localObject).equals("")) {}
    for (;;)
    {
      if (!((String)localObject).equals("")) {
        paramMap.put(REZ.ι, localObject);
      }
      localObject = ˎ();
      if (!((String)localObject).equals("")) {
        paramMap.put(REZ.ˏ, localObject);
      }
      localObject = ˏ();
      if (!((String)localObject).equals("")) {
        paramMap.put(REZ.ᐝ, localObject);
      }
      localObject = ᐝ();
      if (!((String)localObject).equals("")) {
        paramMap.put(REZ.ʻ, localObject);
      }
      return paramMap;
      String str = ˎ();
      localObject = str;
      if (str.equals(""))
      {
        str = ˏ();
        localObject = str;
        if (str.equals("")) {
          localObject = "";
        }
      }
    }
  }
}
