package com.inmobile;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.SparseArray;
import com.samsung.android.sdk.SsdkVendorCheck;
import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

class b
{
  /* Error */
  static ArrayList<Map<String, String>> a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 10	java/io/ObjectInputStream
    //   5: dup
    //   6: new 12	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 16	java/io/ByteArrayInputStream:<init>	([B)V
    //   14: invokespecial 19	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_0
    //   18: aload_0
    //   19: invokevirtual 23	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   22: checkcast 25	java/util/ArrayList
    //   25: astore_2
    //   26: aload_2
    //   27: astore_1
    //   28: aload_0
    //   29: ifnull +40 -> 69
    //   32: aload_0
    //   33: invokevirtual 29	java/io/ObjectInputStream:close	()V
    //   36: aload_2
    //   37: areturn
    //   38: astore_2
    //   39: aload_0
    //   40: astore_1
    //   41: aload_2
    //   42: astore_0
    //   43: goto +4 -> 47
    //   46: astore_0
    //   47: aload_1
    //   48: ifnull +7 -> 55
    //   51: aload_1
    //   52: invokevirtual 29	java/io/ObjectInputStream:close	()V
    //   55: aload_0
    //   56: athrow
    //   57: aconst_null
    //   58: astore_0
    //   59: aload_0
    //   60: ifnull +7 -> 67
    //   63: aload_0
    //   64: invokevirtual 29	java/io/ObjectInputStream:close	()V
    //   67: aconst_null
    //   68: astore_1
    //   69: aload_1
    //   70: areturn
    //   71: astore_0
    //   72: goto -15 -> 57
    //   75: astore_1
    //   76: goto -17 -> 59
    //   79: astore_0
    //   80: aload_2
    //   81: areturn
    //   82: astore_1
    //   83: goto -28 -> 55
    //   86: astore_0
    //   87: goto -20 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	paramArrayOfByte	byte[]
    //   1	69	1	localObject	Object
    //   75	1	1	localException1	Exception
    //   82	1	1	localException2	Exception
    //   25	12	2	localArrayList	ArrayList
    //   38	43	2	localArrayList1	ArrayList<Map<String, String>>
    // Exception table:
    //   from	to	target	type
    //   18	26	38	finally
    //   2	18	46	finally
    //   2	18	71	java/lang/Exception
    //   18	26	75	java/lang/Exception
    //   32	36	79	java/lang/Exception
    //   51	55	82	java/lang/Exception
    //   63	67	86	java/lang/Exception
  }
  
  static Map<String, Object> a(List<String> paramList, Application paramApplication)
  {
    HashMap localHashMap = new HashMap();
    if (paramList.contains("accelerometer_logs")) {
      localHashMap.put("accelerometer_logs", new c().m(paramApplication));
    }
    if (paramList.contains("android_accounts_logs")) {
      localHashMap.put("android_accounts_logs", new d().m(paramApplication));
    }
    if (paramList.contains("app_activity_history_logs")) {
      localHashMap.put("app_activity_history_logs", new e().m(paramApplication));
    }
    if (paramList.contains("app_data_usage_logs")) {
      localHashMap.put("app_data_usage_logs", new f().m(paramApplication));
    }
    if (paramList.contains("app_installs_logs")) {
      localHashMap.put("app_installs_logs", new g().m(paramApplication));
    }
    if (paramList.contains("augmented_device_info_logs")) {
      localHashMap.put("augmented_device_info_logs", new h().m(paramApplication));
    }
    if (paramList.contains("battery_info_logs")) {
      localHashMap.put("battery_info_logs", new i().m(paramApplication));
    }
    if (paramList.contains("bluetooth_info_logs")) {
      localHashMap.put("bluetooth_info_logs", new j().m(paramApplication));
    }
    if (paramList.contains("camera_info_logs")) {
      localHashMap.put("camera_info_logs", new k().m(paramApplication));
    }
    if (paramList.contains("device_access_logs")) {
      localHashMap.put("device_access_logs", new l().m(paramApplication));
    }
    if (paramList.contains("device_info_logs")) {
      localHashMap.put("device_info_logs", new m().m(paramApplication));
    }
    if (paramList.contains("gps_location_logs")) {
      localHashMap.put("gps_location_logs", new n().m(paramApplication));
    }
    if (paramList.contains("granted_permissions_logs")) {
      localHashMap.put("granted_permissions_logs", new p().m(paramApplication));
    }
    if (paramList.contains("polling_gps_location_logs")) {
      localHashMap.put("polling_gps_location_logs", new o().m(paramApplication));
    }
    if (paramList.contains("malware_info_logs")) {
      localHashMap.put("malware_info_logs", x.m(paramApplication));
    }
    if (paramList.contains("manifest_severity_logs")) {
      localHashMap.put("manifest_severity_logs", new q().m(paramApplication));
    }
    if (paramList.contains("net_data_usage_logs")) {
      localHashMap.put("net_data_usage_logs", new r().m(paramApplication));
    }
    if (paramList.contains("root_check_logs")) {
      localHashMap.put("root_check_logs", z.a(paramApplication, false));
    }
    if (paramList.contains("root_deep_check_logs")) {
      localHashMap.put("root_check_logs", z.a(paramApplication, true));
    }
    if (paramList.contains("spoof_history_logs")) {
      localHashMap.put("spoof_history_logs", new s().m(paramApplication));
    }
    if (paramList.contains("telephony_info_logs")) {
      localHashMap.put("telephony_info_logs", new t().m(paramApplication));
    }
    if (paramList.contains("whitebox_policy_logs")) {
      localHashMap.put("whitebox_policy_logs", af.ai());
    }
    if (paramList.contains("wifi_connection_logs")) {
      localHashMap.put("wifi_connection_logs", new u().m(paramApplication));
    }
    if (paramList.contains("wifi_neighbor_logs")) {
      localHashMap.put("wifi_neighbor_logs", new v().m(paramApplication));
    }
    return localHashMap;
  }
  
  static void a(String paramString, Application paramApplication)
  {
    if ((paramString == null) || (paramString.isEmpty()) || (aa.k("uuid"))) {}
    try
    {
      String str = new String(aa.l("uuid"));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("/?v=");
      localStringBuilder.append(str);
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
      paramString.addFlags(268435456);
      paramApplication.getApplicationContext().startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      for (;;) {}
    }
    throw new Exception("BROWSER_NOT_INSTALLED");
    w.G().bindBrowserGeneratePayload();
    w.G().bindBrowserOpen(paramString);
    return;
    throw new Exception("MISSING_PARAMETER : URL");
  }
  
  static byte[] a(String paramString, Cipher paramCipher, IvParameterSpec paramIvParameterSpec, SecretKey paramSecretKey)
  {
    SecureRandom localSecureRandom = new SecureRandom();
    paramString = paramString.getBytes("UTF-8");
    paramCipher.init(1, paramSecretKey, paramIvParameterSpec, localSecureRandom);
    return paramCipher.doFinal(paramString);
  }
  
  /* Error */
  private static byte[] a(ArrayList<Map<String, String>> paramArrayList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 302	java/io/ByteArrayOutputStream
    //   5: dup
    //   6: invokespecial 303	java/io/ByteArrayOutputStream:<init>	()V
    //   9: astore_3
    //   10: new 305	java/io/ObjectOutputStream
    //   13: dup
    //   14: aload_3
    //   15: invokespecial 308	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   18: astore_1
    //   19: aload_1
    //   20: aload_0
    //   21: invokevirtual 312	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   24: aload_1
    //   25: invokevirtual 315	java/io/ObjectOutputStream:flush	()V
    //   28: aload_3
    //   29: invokevirtual 318	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   32: astore_2
    //   33: aload_2
    //   34: astore_0
    //   35: aload_1
    //   36: ifnull +38 -> 74
    //   39: aload_1
    //   40: invokevirtual 319	java/io/ObjectOutputStream:close	()V
    //   43: aload_2
    //   44: areturn
    //   45: astore_0
    //   46: goto +6 -> 52
    //   49: astore_0
    //   50: aload_2
    //   51: astore_1
    //   52: aload_1
    //   53: ifnull +7 -> 60
    //   56: aload_1
    //   57: invokevirtual 319	java/io/ObjectOutputStream:close	()V
    //   60: aload_0
    //   61: athrow
    //   62: aconst_null
    //   63: astore_1
    //   64: aload_1
    //   65: ifnull +7 -> 72
    //   68: aload_1
    //   69: invokevirtual 319	java/io/ObjectOutputStream:close	()V
    //   72: aconst_null
    //   73: astore_0
    //   74: aload_0
    //   75: areturn
    //   76: astore_0
    //   77: goto -15 -> 62
    //   80: astore_0
    //   81: goto -17 -> 64
    //   84: astore_0
    //   85: aload_2
    //   86: areturn
    //   87: astore_1
    //   88: goto -28 -> 60
    //   91: astore_0
    //   92: goto -20 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	paramArrayList	ArrayList<Map<String, String>>
    //   18	51	1	localObject	Object
    //   87	1	1	localException	Exception
    //   1	85	2	arrayOfByte	byte[]
    //   9	20	3	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   19	33	45	finally
    //   2	19	49	finally
    //   2	19	76	java/lang/Exception
    //   19	33	80	java/lang/Exception
    //   39	43	84	java/lang/Exception
    //   56	60	87	java/lang/Exception
    //   68	72	91	java/lang/Exception
  }
  
  static byte[] b(byte[] paramArrayOfByte)
  {
    Object localObject = Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4GYzaWDChrqlzAFakGwoYcTJbef2ay+LSSwfHlRjR98nCqOjdKkFf4jS1yoRzov0KI1mj2a9NhKH3qY7idam4yBEh57syQ3HPi12LVGAAfjFaB6rvtzqaXzezSwKHl7awmU1jwhgTy8wistUb35XHW8z91YwCqFwLUPo2vxS63wIDAQAB".getBytes(), 0);
    try
    {
      localObject = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec((byte[])localObject));
      Cipher localCipher = Cipher.getInstance("RSA");
      localCipher.init(1, (Key)localObject);
      paramArrayOfByte = Base64.encode(localCipher.doFinal(paramArrayOfByte), 0);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      for (;;) {}
    }
    return null;
  }
  
  static String c(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      StringBuilder localStringBuilder = new StringBuilder(paramString.length());
      int i = 0;
      while (i < paramString.length())
      {
        char c = paramString.charAt(i);
        if ((c >= ' ') && (c <= '~')) {
          localStringBuilder.append(c);
        }
        i += 1;
      }
      if (localStringBuilder != null) {
        return localStringBuilder.toString();
      }
    }
    return null;
  }
  
  static void d(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.equals("API_LOCKED_OUT")) {
        w.G().cancelAuthentication();
      }
      Object localObject1 = aa.l("a_fpResult");
      Object localObject4 = null;
      Object localObject2;
      if (localObject1 != null)
      {
        localObject1 = a((byte[])localObject1);
        localObject3 = localObject1;
        localObject2 = localObject4;
        if (localObject1 == null) {
          break label102;
        }
        localObject3 = localObject1;
        localObject2 = localObject4;
        if (((ArrayList)localObject1).size() >= 20) {
          break label102;
        }
        localObject2 = new HashMap();
      }
      else
      {
        localObject1 = new ArrayList();
        localObject2 = new HashMap();
      }
      ((Map)localObject2).put(paramString, m());
      Object localObject3 = localObject1;
      label102:
      if ((localObject2 != null) && (!((Map)localObject2).isEmpty())) {
        localObject3.add(localObject2);
      }
      paramString = a(localObject3);
      if (paramString != null) {
        aa.b("a_fpResult", paramString);
      }
    }
  }
  
  static String[] e(String paramString)
  {
    String[] arrayOfString = new String[2];
    try
    {
      Object localObject2 = o();
      Object localObject1 = n();
      paramString = new String(Base64.encode(a(paramString, Cipher.getInstance("AES/CBC/PKCS5PADDING"), (IvParameterSpec)localObject1, (SecretKey)localObject2), 0));
      localObject2 = new String(b(((SecretKey)localObject2).getEncoded()));
      localObject1 = new String(b(((IvParameterSpec)localObject1).getIV()));
      arrayOfString[0] = paramString;
      paramString = new StringBuilder();
      paramString.append((String)localObject2);
      paramString.append("<><>");
      paramString.append((String)localObject1);
      arrayOfString[1] = paramString.toString();
      return arrayOfString;
    }
    catch (Exception paramString) {}
    return arrayOfString;
  }
  
  static String i(Application paramApplication)
  {
    Object localObject = null;
    try
    {
      String str = paramApplication.getApplicationContext().getPackageName();
      Iterator localIterator = paramApplication.getPackageManager().getInstalledApplications(128).iterator();
      do
      {
        paramApplication = localObject;
        if (!localIterator.hasNext()) {
          break;
        }
        paramApplication = (ApplicationInfo)localIterator.next();
      } while (!paramApplication.packageName.equals(str));
      paramApplication = paramApplication.sourceDir;
      return paramApplication;
    }
    catch (Exception paramApplication) {}
    return null;
  }
  
  static String j(Application paramApplication)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        localObject1 = localObject2;
        if (MMEUtilities.isPermissionEnabled(paramApplication, "android.permission.USE_FINGERPRINT"))
        {
          paramApplication = (FingerprintManager)paramApplication.getSystemService("fingerprint");
          localObject1 = localObject2;
          if (paramApplication != null)
          {
            localObject1 = localObject2;
            if (paramApplication.isHardwareDetected())
            {
              localObject1 = (List)paramApplication.getClass().getMethod("getEnrolledFingerprints", new Class[0]).invoke(paramApplication, new Object[0]);
              paramApplication = new ArrayList();
              int i = 0;
              while (i < ((List)localObject1).size())
              {
                Object localObject3 = ((List)localObject1).get(i);
                paramApplication.add((Integer)localObject3.getClass().getMethod("getFingerId", new Class[0]).invoke(localObject3, new Object[0]));
                i += 1;
              }
              localObject1 = localObject2;
              if (!paramApplication.isEmpty()) {
                localObject1 = MMEUtilities.stringToSHA256(paramApplication.toString());
              }
            }
          }
        }
      }
      return localObject1;
    }
    catch (Exception paramApplication) {}
    return null;
  }
  
  static boolean k(Application paramApplication)
  {
    boolean bool1 = p();
    int i = 1;
    boolean bool2 = false;
    Object localObject;
    if (bool1)
    {
      localObject = aa.m("a_isSamsungSdkSupported");
      if (localObject != null)
      {
        if (((String)localObject).equals("1")) {
          return true;
        }
        ((String)localObject).equals("0");
      }
      else if ((Build.VERSION.SDK_INT >= 23) && (MMEUtilities.isPermissionEnabled(paramApplication, "android.permission.USE_FINGERPRINT")))
      {
        localObject = (FingerprintManager)paramApplication.getSystemService("fingerprint");
        if ((localObject == null) || (!((FingerprintManager)localObject).isHardwareDetected())) {
          i = 0;
        }
        bool1 = bool2;
        if (i == 0)
        {
          bool1 = bool2;
          if (SsdkVendorCheck.isSamsungDevice())
          {
            bool1 = bool2;
            if (MMEUtilities.isPermissionEnabled(paramApplication, "com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY")) {
              localObject = new Spass();
            }
          }
        }
      }
    }
    try
    {
      ((Spass)localObject).initialize(paramApplication.getApplicationContext());
      bool1 = ((Spass)localObject).isFeatureEnabled(0);
      break label198;
      if ((SsdkVendorCheck.isSamsungDevice()) && (MMEUtilities.isPermissionEnabled(paramApplication, "com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY"))) {
        localObject = new Spass();
      }
      try
      {
        ((Spass)localObject).initialize(paramApplication.getApplicationContext());
        bool1 = ((Spass)localObject).isFeatureEnabled(0);
      }
      catch (Exception paramApplication)
      {
        label198:
        for (;;) {}
      }
      bool1 = false;
      if (bool1) {}
      for (paramApplication = "1";; paramApplication = "0")
      {
        aa.b("a_isSamsungSdkSupported", paramApplication.getBytes());
        return bool1;
      }
      return false;
    }
    catch (Exception paramApplication)
    {
      for (;;)
      {
        bool1 = bool2;
      }
    }
  }
  
  static String l(Application paramApplication)
  {
    boolean bool = p();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (bool) {}
    for (;;)
    {
      try
      {
        localSpass = new Spass();
        localSpass.initialize(paramApplication);
        j = 0;
      }
      catch (Exception paramApplication)
      {
        Spass localSpass;
        int j;
        int i;
        return null;
      }
      try
      {
        Class.forName("com.samsung.android.fingerprint.FingerprintManager").getMethod("isSupportFingerprintIds", new Class[0]);
        i = 1;
      }
      catch (Exception localException) {}
    }
    i = 0;
    localObject1 = localObject2;
    if (i != 0)
    {
      localObject1 = localObject2;
      if (localSpass.isFeatureEnabled(3))
      {
        localObject1 = new SpassFingerprint(paramApplication).getRegisteredFingerprintUniqueID();
        paramApplication = new ArrayList();
        i = j;
        while (i < ((SparseArray)localObject1).size())
        {
          paramApplication.add((String)((SparseArray)localObject1).valueAt(i));
          i += 1;
        }
        localObject1 = localObject2;
        if (!paramApplication.isEmpty()) {
          localObject1 = MMEUtilities.stringToSHA256(paramApplication.toString());
        }
      }
    }
    return localObject1;
  }
  
  static String m()
  {
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z", Locale.ENGLISH).format(new Date());
  }
  
  static IvParameterSpec n()
  {
    SecureRandom localSecureRandom = new SecureRandom();
    localSecureRandom.setSeed(localSecureRandom.generateSeed(16));
    byte[] arrayOfByte = new byte[16];
    localSecureRandom.nextBytes(arrayOfByte);
    return new IvParameterSpec(arrayOfByte);
  }
  
  static SecretKey o()
  {
    Object localObject = new SecureRandom();
    ((SecureRandom)localObject).setSeed(((SecureRandom)localObject).generateSeed(32));
    localObject = KeyGenerator.getInstance("AES");
    ((KeyGenerator)localObject).init(256, new SecureRandom());
    return ((KeyGenerator)localObject).generateKey();
  }
  
  private static boolean p()
  {
    boolean bool2 = false;
    try
    {
      Class localClass1 = Class.forName("com.samsung.android.sdk.pass.Spass");
      Class localClass2 = Class.forName("com.samsung.android.sdk.SsdkVendorCheck");
      boolean bool1 = bool2;
      if (localClass1 != null)
      {
        bool1 = bool2;
        if (localClass2 != null) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception localException) {}
    return false;
  }
}
