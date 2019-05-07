package com.gameloft.android.ANMP.GloftPEHM;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.gameloft.android.ANMP.GloftPEHM.GLUtils.SUtils;
import com.gameloft.android.ANMP.GloftPEHM.installer.GameInstaller;
import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SendInfo
{
  public static Context a = null;
  
  public SendInfo() {}
  
  private static String generateRandomNumber()
  {
    Object localObject1 = new byte[16];
    Object localObject2 = getRandomFromOtherApps();
    if (!((String)localObject2).equals("")) {
      return localObject2;
    }
    localObject2 = new Random(System.currentTimeMillis());
    int i = 0;
    if (i < 16)
    {
      int k = ((Random)localObject2).nextInt() % 16;
      int j = k;
      if (k < 0) {
        j = -k;
      }
      if (j < 10) {
        localObject1[i] = ((byte)(j + 48));
      }
      for (;;)
      {
        i += 1;
        break;
        localObject1[i] = ((byte)(j - 10 + 65));
      }
    }
    localObject1 = new String((byte[])localObject1);
    savePreference("GLU", (String)localObject1, "GLDID");
    return localObject1;
  }
  
  public static String getAndroidID()
  {
    String str2 = Settings.Secure.getString(a.getApplicationContext().getContentResolver(), "android_id");
    String str1;
    if ((str2 == null) || (str2.equals("9774d56d682e549c"))) {
      str1 = null;
    }
    do
    {
      return str1;
      str1 = str2;
    } while (str2.length() >= 15);
    return null;
  }
  
  private static String getAndroidIndentification()
  {
    Object localObject = getIMEI();
    if (localObject != null) {}
    String str;
    do
    {
      do
      {
        do
        {
          do
          {
            return localObject;
            str = getAndroidID();
            localObject = str;
          } while (str != null);
          str = getSerial();
          localObject = str;
        } while (str != null);
        str = getSerialNo();
        localObject = str;
      } while (str != null);
      str = getCPUSerial();
      localObject = str;
    } while (str != null);
    return generateRandomNumber();
  }
  
  /* Error */
  public static String getCPUSerial()
  {
    // Byte code:
    //   0: new 98	java/lang/ProcessBuilder
    //   3: dup
    //   4: iconst_2
    //   5: anewarray 23	java/lang/String
    //   8: dup
    //   9: iconst_0
    //   10: ldc 100
    //   12: aastore
    //   13: dup
    //   14: iconst_1
    //   15: ldc 102
    //   17: aastore
    //   18: invokespecial 105	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
    //   21: invokevirtual 109	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
    //   24: invokevirtual 115	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   27: astore_1
    //   28: sipush 1024
    //   31: newarray byte
    //   33: astore_2
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 121	java/io/InputStream:read	([B)I
    //   39: iconst_m1
    //   40: if_icmpeq +76 -> 116
    //   43: new 23	java/lang/String
    //   46: dup
    //   47: aload_2
    //   48: invokespecial 45	java/lang/String:<init>	([B)V
    //   51: ldc 123
    //   53: invokevirtual 127	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   56: astore_3
    //   57: iconst_0
    //   58: istore_0
    //   59: iload_0
    //   60: aload_3
    //   61: arraylength
    //   62: if_icmpge -28 -> 34
    //   65: aload_3
    //   66: iload_0
    //   67: aaload
    //   68: invokevirtual 130	java/lang/String:trim	()Ljava/lang/String;
    //   71: ldc -124
    //   73: invokevirtual 27	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   76: ifeq +54 -> 130
    //   79: aload_3
    //   80: iload_0
    //   81: iconst_1
    //   82: iadd
    //   83: aaload
    //   84: ifnull +46 -> 130
    //   87: aload_3
    //   88: iload_0
    //   89: iconst_1
    //   90: iadd
    //   91: aaload
    //   92: ldc -122
    //   94: ldc 21
    //   96: invokevirtual 138	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   99: invokevirtual 130	java/lang/String:trim	()Ljava/lang/String;
    //   102: ldc 21
    //   104: invokevirtual 27	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   107: ifne +23 -> 130
    //   110: aload_3
    //   111: iload_0
    //   112: iconst_1
    //   113: iadd
    //   114: aaload
    //   115: areturn
    //   116: aload_1
    //   117: invokevirtual 141	java/io/InputStream:close	()V
    //   120: aconst_null
    //   121: areturn
    //   122: astore_1
    //   123: aload_1
    //   124: invokevirtual 144	java/lang/Exception:printStackTrace	()V
    //   127: goto -7 -> 120
    //   130: iload_0
    //   131: iconst_1
    //   132: iadd
    //   133: istore_0
    //   134: goto -75 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   58	76	0	i	int
    //   27	90	1	localInputStream	java.io.InputStream
    //   122	2	1	localException	Exception
    //   33	15	2	arrayOfByte	byte[]
    //   56	55	3	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   0	34	122	java/lang/Exception
    //   34	57	122	java/lang/Exception
    //   59	79	122	java/lang/Exception
    //   87	110	122	java/lang/Exception
    //   116	120	122	java/lang/Exception
  }
  
  public static String getGLDID()
  {
    return getAndroidIndentification();
  }
  
  public static int[] getGLUID()
  {
    int[] arrayOfInt = new int[4];
    try
    {
      Object localObject = getGLDID() + SendInfo.class.getPackage().getName();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(((String)localObject).getBytes());
      localObject = localMessageDigest.digest();
      arrayOfInt[0] = getInt((byte[])localObject, 0);
      arrayOfInt[1] = getInt((byte[])localObject, 4);
      arrayOfInt[2] = getInt((byte[])localObject, 8);
      arrayOfInt[3] = getInt((byte[])localObject, 12);
      return arrayOfInt;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return arrayOfInt;
  }
  
  public static String getIMEI()
  {
    try
    {
      str = ((TelephonyManager)a.getApplicationContext().getSystemService("phone")).getDeviceId();
      try
      {
        i = Integer.parseInt(str);
        if (i != 0) {
          break label32;
        }
        str = null;
      }
      catch (Exception localException2)
      {
        int i;
        do
        {
          i = str.length();
        } while (i >= 5);
      }
      return str;
    }
    catch (Exception localException1)
    {
      String str;
      label32:
      for (;;) {}
    }
    return null;
  }
  
  public static int getInt(byte[] paramArrayOfByte, int paramInt)
  {
    return ((((paramArrayOfByte[paramInt] & 0xFF) + 0 << 8) + (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8) + (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8) + (paramArrayOfByte[(paramInt + 3)] & 0xFF);
  }
  
  public static String getLocaleCountry()
  {
    if (a != null) {}
    try
    {
      localObject = ((TelephonyManager)a.getSystemService("phone")).getSimCountryIso();
      if ((localObject != null) && (!((String)localObject).isEmpty())) {
        return localObject;
      }
      str = Locale.getDefault().getCountry();
      localObject = str;
      if (str != null) {
        return localObject;
      }
    }
    catch (Exception localException)
    {
      Object localObject;
      String str;
      for (;;) {}
      return localException;
    }
    str = Locale.getDefault().getCountry();
    localObject = str;
    if (str == null) {
      return "";
    }
  }
  
  public static String getLocaleLanguage()
  {
    if (a != null) {
      return Locale.getDefault().getLanguage();
    }
    return "";
  }
  
  public static String getMacAddress()
  {
    if (a != null) {
      try
      {
        Object localObject = (WifiManager)a.getApplicationContext().getSystemService("wifi");
        if (localObject == null) {
          return "";
        }
        localObject = ((WifiManager)localObject).getConnectionInfo();
        if (localObject == null) {
          return "";
        }
        String str2 = ((WifiInfo)localObject).getMacAddress();
        localObject = str2;
        if (str2 != null) {
          return ???;
        }
        return "";
      }
      catch (Exception localException)
      {
        return "";
      }
    }
    String str1 = "";
    return str1;
  }
  
  public static String getManufacturerModel()
  {
    return Build.MANUFACTURER + "_" + Build.MODEL;
  }
  
  private static ArrayList<String> getPackages()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = a.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      Object localObject = (PackageInfo)localList.get(i);
      if (((PackageInfo)localObject).versionName != null)
      {
        localObject = ((PackageInfo)localObject).packageName;
        if (((String)localObject).startsWith("com.gameloft")) {
          localArrayList.add(localObject);
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static String getPhoneCarrier()
  {
    try
    {
      if (a != null)
      {
        String str = ((TelephonyManager)a.getSystemService("phone")).getNetworkOperatorName();
        return str;
      }
    }
    catch (Exception localException) {}
    return "";
  }
  
  private static int getPreferenceInt(String paramString1, String paramString2, int paramInt)
  {
    return a.getSharedPreferences(paramString1, 1).getInt(paramString2, paramInt);
  }
  
  private static long getPreferenceLong(String paramString1, String paramString2, long paramLong)
  {
    return a.getSharedPreferences(paramString1, 1).getLong(paramString2, paramLong);
  }
  
  private static String getPreferenceString(String paramString1, String paramString2, String paramString3)
  {
    return a.getSharedPreferences(paramString1, 1).getString(paramString2, paramString3);
  }
  
  private static String getRandomFromOtherApps()
  {
    Object localObject = getPackages();
    a.getSharedPreferences("GLU", 1);
    String str = getPreferenceString("GLU", "GLDID", "");
    if (!str.equals("")) {
      return str;
    }
    localObject = getSavedInOtherPreferences("GLU", (ArrayList)localObject, "GLDID");
    if (!((String)localObject).equals(""))
    {
      savePreference("GLU", (String)localObject, "GLDID");
      return localObject;
    }
    return "";
  }
  
  public static String getSDFolder()
  {
    String str = SUtils.getPreferenceString("SDFolder", GameInstaller.mPreferencesName);
    if (str != "") {
      return str;
    }
    return "/sdcard/gameloft/games/GloftPEHM";
  }
  
  public static String getSaveFolder()
  {
    try
    {
      String str = a.getFilesDir().toString();
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private static String getSavedInOtherPreferences(String paramString1, ArrayList<String> paramArrayList, String paramString2)
  {
    Object localObject1 = "";
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      try
      {
        Object localObject2 = a.createPackageContext((String)paramArrayList.get(i), 2).getSharedPreferences(paramString1, 1).getString(paramString2, "");
        localObject1 = localObject2;
        try
        {
          boolean bool = ((String)localObject1).equals("");
          localObject2 = localObject1;
          if (bool) {
            break label84;
          }
          return localObject1;
        }
        catch (Exception localException1) {}
      }
      catch (Exception localException2)
      {
        Object localObject3;
        label84:
        for (;;) {}
      }
      localException1.printStackTrace();
      localObject3 = localObject1;
      i += 1;
      localObject1 = localObject3;
    }
    return localObject1;
  }
  
  public static String getSerial()
  {
    if (Build.VERSION.SDK_INT >= 9) {}
    for (String str1 = Build.SERIAL;; str1 = null)
    {
      String str2;
      if ((str1 == null) || (str1.length() < 5)) {
        str2 = null;
      }
      do
      {
        return str2;
        str2 = str1;
      } while (!"unknown".equals(str1));
      return null;
    }
  }
  
  public static String getSerialNo()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class }).invoke(localObject, new Object[] { "ro.serialno" });
      if ((localObject == null) || (((String)localObject).length() < 5)) {
        break label74;
      }
      boolean bool = "unknown".equals(localObject);
      if (!bool) {
        return ???;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
      label74:
      String str = null;
      return str;
    }
    return null;
  }
  
  public static native void initMethods();
  
  private static void savePreference(String paramString1, String paramString2, Object paramObject)
  {
    paramString1 = a.getSharedPreferences(paramString1, 1).edit();
    if ((paramObject instanceof String)) {
      paramString1.putString(paramString2, (String)paramObject);
    }
    for (;;)
    {
      paramString1.commit();
      return;
      if ((paramObject instanceof Integer)) {
        paramString1.putInt(paramString2, ((Integer)paramObject).intValue());
      } else if ((paramObject instanceof Boolean)) {
        paramString1.putBoolean(paramString2, ((Boolean)paramObject).booleanValue());
      } else if ((paramObject instanceof Long)) {
        paramString1.putLong(paramString2, ((Long)paramObject).longValue());
      }
    }
  }
  
  public static void setContext(Context paramContext)
  {
    a = paramContext;
    initMethods();
  }
}
