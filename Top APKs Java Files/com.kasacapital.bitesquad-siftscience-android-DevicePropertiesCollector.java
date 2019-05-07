package siftscience.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.sift.api.representations.AndroidDevicePropertiesJson;
import com.sift.api.representations.AndroidDevicePropertiesJson.Builder;
import com.sift.api.representations.AndroidInstalledAppJson;
import com.sift.api.representations.AndroidInstalledAppJson.Builder;
import com.sift.api.representations.MobileEventJson;
import com.sift.api.representations.MobileEventJson.Builder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

public class DevicePropertiesCollector
{
  private static final Map<String, String> DANGEROUS_PROPERTIES = new HashMap() {};
  private static final String[] KNOWN_DANGEROUS_APPS_PACKAGES;
  private static final String[] KNOWN_ROOT_APPS_PACKAGES;
  private static final String[] KNOWN_ROOT_CLOAKING_PACKAGES;
  private static final String[] PATHS_THAT_SHOULD_NOT_BE_WRITABLE;
  private static final String[] SU_PATHS;
  private static final String TAG = DevicePropertiesCollector.class.getName();
  private final Context context;
  private final Sift sift;
  private long timestamp;
  
  static
  {
    SU_PATHS = new String[] { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su" };
    KNOWN_ROOT_APPS_PACKAGES = new String[] { "com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su" };
    KNOWN_DANGEROUS_APPS_PACKAGES = new String[] { "com.koushikdutta.rommanager", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine" };
    KNOWN_ROOT_CLOAKING_PACKAGES = new String[] { "com.devadvance.rootcloak", "de.robv.android.xposed.installer", "com.saurik.substrate", "com.devadvance.rootcloakplus", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot", "com.formyhm.hideroot" };
    PATHS_THAT_SHOULD_NOT_BE_WRITABLE = new String[] { "/system", "/system/bin", "/system/sbin", "/system/xbin", "/vendor/bin", "/sbin", "/etc" };
  }
  
  public DevicePropertiesCollector(Sift paramSift, Context paramContext)
  {
    this.sift = paramSift;
    this.context = paramContext.getApplicationContext();
    this.timestamp = Time.now();
  }
  
  private List<String> existingDangerousProperties()
  {
    String[] arrayOfString = propertiesReader();
    ArrayList localArrayList = new ArrayList();
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str1 = arrayOfString[i];
      Iterator localIterator = DANGEROUS_PROPERTIES.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        if ((str1.contains(str2)) && (str1.contains((CharSequence)DANGEROUS_PROPERTIES.get(str2)))) {
          localArrayList.add(str1);
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private List<String> existingRWPaths()
  {
    String[] arrayOfString1 = mountReader();
    ArrayList localArrayList = new ArrayList();
    int m = arrayOfString1.length;
    int i = 0;
    while (i < m)
    {
      String str1 = arrayOfString1[i];
      Object localObject = str1.split(" ");
      if (localObject.length < 4)
      {
        Log.e(TAG, String.format("Error formatting mount: %s", new Object[] { str1 }));
        i += 1;
      }
      else
      {
        str1 = localObject[1];
        localObject = localObject[3];
        String[] arrayOfString2 = PATHS_THAT_SHOULD_NOT_BE_WRITABLE;
        int n = arrayOfString2.length;
        int j = 0;
        label102:
        String str2;
        String[] arrayOfString3;
        int i1;
        int k;
        if (j < n)
        {
          str2 = arrayOfString2[j];
          if (str1.equalsIgnoreCase(str2))
          {
            arrayOfString3 = ((String)localObject).split(",");
            i1 = arrayOfString3.length;
            k = 0;
          }
        }
        for (;;)
        {
          if (k < i1)
          {
            if (arrayOfString3[k].equalsIgnoreCase("rw")) {
              localArrayList.add(str2);
            }
          }
          else
          {
            j += 1;
            break label102;
            break;
          }
          k += 1;
        }
      }
    }
    return localArrayList;
  }
  
  private List<String> existingRootFiles()
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = SU_PATHS;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (new File(str).exists()) {
        localArrayList.add(str);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  private List<String> existingRootPackages()
  {
    Object localObject = new ArrayList();
    ((ArrayList)localObject).addAll(Arrays.asList(KNOWN_ROOT_APPS_PACKAGES));
    ((ArrayList)localObject).addAll(Arrays.asList(KNOWN_DANGEROUS_APPS_PACKAGES));
    ((ArrayList)localObject).addAll(Arrays.asList(KNOWN_ROOT_CLOAKING_PACKAGES));
    PackageManager localPackageManager = this.context.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      try
      {
        localPackageManager.getPackageInfo(str, 0);
        localArrayList.add(str);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
    return localArrayList;
  }
  
  private AndroidDevicePropertiesJson get()
  {
    Object localObject3 = this.context.getPackageManager();
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      str1 = (String)((PackageManager)localObject3).getApplicationLabel(((PackageManager)localObject3).getApplicationInfo(this.context.getPackageName(), 0));
      localObject1 = str1;
      localObject3 = ((PackageManager)localObject3).getPackageInfo(this.context.getPackageName(), 0).versionName;
      localObject2 = localObject3;
      localObject1 = str1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        String str1;
        String str2;
        String str3;
        List localList1;
        List localList2;
        List localList3;
        List localList4;
        List localList5;
        Log.e(TAG, "Encountered NameNotFoundException in get", localNameNotFoundException);
      }
    }
    localObject3 = (TelephonyManager)this.context.getSystemService("phone");
    str1 = ((TelephonyManager)localObject3).getNetworkOperatorName();
    localObject3 = ((TelephonyManager)localObject3).getSimCountryIso();
    str2 = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    str3 = Build.VERSION.RELEASE;
    localList1 = existingRootFiles();
    localList2 = existingRootPackages();
    localList3 = existingDangerousProperties();
    localList4 = existingRWPaths();
    localList5 = getInstalledApps();
    return AndroidDevicePropertiesJson.newBuilder().withAppName(localObject1).withAppVersion(localObject2).withSdkVersion("0.9.12").withAndroidId(str2).withDeviceManufacturer(Build.MANUFACTURER).withDeviceModel(Build.MODEL).withMobileCarrierName(str1).withMobileIsoCountryCode((String)localObject3).withDeviceSystemVersion(str3).withBuildBrand(Build.BRAND).withBuildDevice(Build.DEVICE).withBuildFingerprint(Build.FINGERPRINT).withBuildHardware(Build.HARDWARE).withBuildProduct(Build.PRODUCT).withBuildTags(Build.TAGS).withEvidenceFilesPresent(localList1).withEvidencePackagesPresent(localList2).withEvidenceProperties(localList3).withEvidenceDirectoriesWritable(localList4).withInstalledApps(localList5).build();
  }
  
  private List<AndroidInstalledAppJson> getInstalledApps()
  {
    PackageManager localPackageManager = this.context.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 0)
      {
        String str = ((PackageInfo)localObject).applicationInfo.loadLabel(localPackageManager).toString();
        localObject = ((PackageInfo)localObject).packageName;
        localArrayList.add(AndroidInstalledAppJson.newBuilder().withAppName(str).withPackageName((String)localObject).build());
      }
    }
    return localArrayList;
  }
  
  private String[] mountReader()
  {
    Object localObject1 = null;
    try
    {
      InputStream localInputStream = Runtime.getRuntime().exec("mount").getInputStream();
      localObject1 = localInputStream;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.e(TAG, "Error reading mount", localIOException);
      }
      str = "";
    }
    if (localObject1 == null) {
      return ArrayUtils.EMPTY_STRING_ARRAY;
    }
    try
    {
      localObject1 = new Scanner((InputStream)localObject1).useDelimiter("\\A").next();
      return ((String)localObject1).split("\n");
    }
    catch (NoSuchElementException localNoSuchElementException)
    {
      for (;;)
      {
        String str;
        Log.e(TAG, "Error reading mount", localNoSuchElementException);
        Object localObject2 = str;
      }
    }
  }
  
  private String[] propertiesReader()
  {
    Object localObject1 = null;
    try
    {
      InputStream localInputStream = Runtime.getRuntime().exec("getprop").getInputStream();
      localObject1 = localInputStream;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.e(TAG, "Error reading properties", localIOException);
      }
      str = "";
    }
    if (localObject1 == null) {
      return ArrayUtils.EMPTY_STRING_ARRAY;
    }
    try
    {
      localObject1 = new Scanner((InputStream)localObject1).useDelimiter("\\A").next();
      return ((String)localObject1).split("\n");
    }
    catch (NoSuchElementException localNoSuchElementException)
    {
      for (;;)
      {
        String str;
        Log.e(TAG, "Error reading properties", localNoSuchElementException);
        Object localObject2 = str;
      }
    }
  }
  
  public void collect()
  {
    AndroidDevicePropertiesJson localAndroidDevicePropertiesJson = get();
    this.sift.getQueue("siftscience.android.device").append(MobileEventJson.newBuilder().withAndroidDeviceProperties(localAndroidDevicePropertiesJson).withInstallationId(localAndroidDevicePropertiesJson.androidId).withTime(Long.valueOf(this.timestamp)).build());
  }
}
