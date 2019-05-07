package com.lemon.faceu.common.compatibility;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.af;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import com.lemon.faceu.common.aa.aa;
import com.lemon.faceu.common.aa.ab;
import com.lemon.faceu.common.aa.d;
import com.lemon.faceu.common.aa.x;
import com.lemon.faceu.common.d.b;
import com.lemon.faceu.common.j.h;
import com.lemon.faceu.common.j.t;
import com.lemon.faceu.sdk.utils.g;
import com.lemon.faceu.sdk.utils.m;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class c
{
  private static final String TAG = "DeviceInfo";
  private static String djS = null;
  static String djT = "unknown_";
  private static boolean djU = false;
  private static boolean djV = false;
  private static final int djW = 1;
  private static final int djX = 2;
  private static final int djY = -1;
  private static int djZ = -1;
  private static final String[] dka = { "zh", "en", "ja", "ko", "vi", "th", "id" };
  private static final String dkb = "in";
  private static final String dkc = "id";
  private static i dkd;
  private static long dke;
  private static String dkf;
  private static String dkg = "";
  private static int level = 0;
  
  public c() {}
  
  public static JSONObject aiX()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("cpu", aiZ());
      localJSONObject.put("radio", ajb());
      localJSONObject.put("os_version", Build.VERSION.SDK_INT);
      localJSONObject.put("imei", cu(com.lemon.faceu.common.e.c.akA().getContext()));
      localJSONObject.put("imsi", ajc());
      localJSONObject.put("iccid", aje());
      localJSONObject.put("android_id", aja());
      localJSONObject.put("model", Build.MODEL);
      localJSONObject.put("core_count", ajf());
      localJSONObject.put("wifi", aiY());
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      g.e("DeviceInfo", "jsonexception, " + localJSONException.getMessage());
    }
    return localJSONObject;
  }
  
  public static String aiY()
  {
    return cw(com.lemon.faceu.common.e.c.akA().getContext());
  }
  
  /* Error */
  public static String aiZ()
  {
    // Byte code:
    //   0: iconst_2
    //   1: istore_0
    //   2: iconst_2
    //   3: anewarray 49	java/lang/String
    //   6: astore 4
    //   8: aload 4
    //   10: iconst_0
    //   11: ldc 65
    //   13: aastore
    //   14: aload 4
    //   16: iconst_1
    //   17: ldc -74
    //   19: aastore
    //   20: new 184	java/io/BufferedReader
    //   23: dup
    //   24: new 186	java/io/FileReader
    //   27: dup
    //   28: ldc -68
    //   30: invokespecial 191	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   33: sipush 8192
    //   36: invokespecial 194	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   39: astore_2
    //   40: aload_2
    //   41: astore_1
    //   42: aload_2
    //   43: invokevirtual 197	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: invokestatic 203	com/lemon/faceu/sdk/utils/i:lD	(Ljava/lang/String;)Ljava/lang/String;
    //   49: ldc -51
    //   51: invokevirtual 209	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   54: astore_3
    //   55: aload_2
    //   56: astore_1
    //   57: iload_0
    //   58: aload_3
    //   59: arraylength
    //   60: if_icmpge +44 -> 104
    //   63: aload_2
    //   64: astore_1
    //   65: aload 4
    //   67: iconst_0
    //   68: new 156	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 157	java/lang/StringBuilder:<init>	()V
    //   75: aload 4
    //   77: iconst_0
    //   78: aaload
    //   79: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: aload_3
    //   83: iload_0
    //   84: aaload
    //   85: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: ldc -45
    //   90: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: aastore
    //   97: iload_0
    //   98: iconst_1
    //   99: iadd
    //   100: istore_0
    //   101: goto -46 -> 55
    //   104: aload_2
    //   105: astore_1
    //   106: aload 4
    //   108: iconst_1
    //   109: aload_2
    //   110: invokevirtual 197	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   113: invokestatic 203	com/lemon/faceu/sdk/utils/i:lD	(Ljava/lang/String;)Ljava/lang/String;
    //   116: ldc -51
    //   118: invokevirtual 209	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   121: iconst_2
    //   122: aaload
    //   123: aastore
    //   124: aload_2
    //   125: invokestatic 215	com/lemon/faceu/sdk/utils/i:d	(Ljava/io/Closeable;)Z
    //   128: pop
    //   129: aload 4
    //   131: iconst_0
    //   132: aaload
    //   133: areturn
    //   134: astore_3
    //   135: aconst_null
    //   136: astore_2
    //   137: aload_2
    //   138: astore_1
    //   139: ldc 8
    //   141: new 156	java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial 157	java/lang/StringBuilder:<init>	()V
    //   148: ldc -39
    //   150: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: aload_3
    //   154: invokevirtual 218	java/io/IOException:getMessage	()Ljava/lang/String;
    //   157: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 175	com/lemon/faceu/sdk/utils/g:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: aload_2
    //   167: invokestatic 215	com/lemon/faceu/sdk/utils/i:d	(Ljava/io/Closeable;)Z
    //   170: pop
    //   171: goto -42 -> 129
    //   174: astore_2
    //   175: aconst_null
    //   176: astore_1
    //   177: aload_1
    //   178: invokestatic 215	com/lemon/faceu/sdk/utils/i:d	(Ljava/io/Closeable;)Z
    //   181: pop
    //   182: aload_2
    //   183: athrow
    //   184: astore_2
    //   185: goto -8 -> 177
    //   188: astore_3
    //   189: goto -52 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	100	0	i	int
    //   41	137	1	localBufferedReader1	java.io.BufferedReader
    //   39	128	2	localBufferedReader2	java.io.BufferedReader
    //   174	9	2	localObject1	Object
    //   184	1	2	localObject2	Object
    //   54	29	3	arrayOfString1	String[]
    //   134	20	3	localIOException1	java.io.IOException
    //   188	1	3	localIOException2	java.io.IOException
    //   6	124	4	arrayOfString2	String[]
    // Exception table:
    //   from	to	target	type
    //   20	40	134	java/io/IOException
    //   20	40	174	finally
    //   42	55	184	finally
    //   57	63	184	finally
    //   65	97	184	finally
    //   106	124	184	finally
    //   139	166	184	finally
    //   42	55	188	java/io/IOException
    //   57	63	188	java/io/IOException
    //   65	97	188	java/io/IOException
    //   106	124	188	java/io/IOException
  }
  
  public static String ajA()
  {
    if (!com.lemon.faceu.common.e.c.akz()) {
      return "";
    }
    com.lemon.faceu.common.aa.a localA = com.lemon.faceu.common.e.c.akA().akK();
    if (localA != null)
    {
      if (TextUtils.isEmpty(localA.getPhone())) {
        return "no";
      }
      return "yes";
    }
    return "";
  }
  
  public static void ajB()
  {
    djZ = ajC();
  }
  
  /* Error */
  private static int ajC()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: getstatic 47	com/lemon/faceu/common/compatibility/c:djZ	I
    //   5: iconst_m1
    //   6: if_icmpeq +7 -> 13
    //   9: getstatic 47	com/lemon/faceu/common/compatibility/c:djZ	I
    //   12: ireturn
    //   13: invokestatic 113	com/lemon/faceu/common/e/c:akA	()Lcom/lemon/faceu/common/e/c;
    //   16: invokevirtual 117	com/lemon/faceu/common/e/c:getContext	()Landroid/content/Context;
    //   19: invokevirtual 254	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   22: astore_1
    //   23: aload_1
    //   24: getstatic 260	android/provider/ContactsContract$CommonDataKinds$Phone:CONTENT_URI	Landroid/net/Uri;
    //   27: iconst_1
    //   28: anewarray 49	java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: ldc_w 262
    //   36: aastore
    //   37: aconst_null
    //   38: aconst_null
    //   39: ldc_w 264
    //   42: invokevirtual 270	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   45: astore_1
    //   46: aload_1
    //   47: astore_2
    //   48: aload_2
    //   49: ifnull +40 -> 89
    //   52: aload_2
    //   53: astore_1
    //   54: aload_2
    //   55: invokeinterface 275 1 0
    //   60: istore_0
    //   61: iload_0
    //   62: ifeq +15 -> 77
    //   65: aload_2
    //   66: ifnull +9 -> 75
    //   69: aload_2
    //   70: invokeinterface 278 1 0
    //   75: iconst_1
    //   76: ireturn
    //   77: aload_2
    //   78: ifnull +9 -> 87
    //   81: aload_2
    //   82: invokeinterface 278 1 0
    //   87: iconst_2
    //   88: ireturn
    //   89: aload_2
    //   90: ifnull +9 -> 99
    //   93: aload_2
    //   94: invokeinterface 278 1 0
    //   99: iconst_2
    //   100: ireturn
    //   101: astore_3
    //   102: aconst_null
    //   103: astore_2
    //   104: aload_2
    //   105: astore_1
    //   106: ldc 8
    //   108: aload_3
    //   109: invokevirtual 279	java/lang/Exception:toString	()Ljava/lang/String;
    //   112: invokestatic 281	com/lemon/faceu/sdk/utils/g:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: aload_2
    //   116: ifnull -17 -> 99
    //   119: aload_2
    //   120: invokeinterface 278 1 0
    //   125: goto -26 -> 99
    //   128: astore_1
    //   129: aload_2
    //   130: ifnull +9 -> 139
    //   133: aload_2
    //   134: invokeinterface 278 1 0
    //   139: aload_1
    //   140: athrow
    //   141: astore_3
    //   142: aload_1
    //   143: astore_2
    //   144: aload_3
    //   145: astore_1
    //   146: goto -17 -> 129
    //   149: astore_3
    //   150: goto -46 -> 104
    // Local variable table:
    //   start	length	slot	name	signature
    //   60	2	0	bool	boolean
    //   22	84	1	localObject1	Object
    //   128	15	1	localObject2	Object
    //   145	1	1	localObject3	Object
    //   1	143	2	localObject4	Object
    //   101	8	3	localException1	Exception
    //   141	4	3	localObject5	Object
    //   149	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   23	46	101	java/lang/Exception
    //   23	46	128	finally
    //   54	61	141	finally
    //   106	115	141	finally
    //   54	61	149	java/lang/Exception
  }
  
  public static String ajD()
  {
    if (!com.lemon.faceu.common.e.c.akz()) {
      return "";
    }
    if (com.lemon.faceu.common.e.c.akA().akN().getInt(52, 0) == 1) {
      return "yes";
    }
    return "no";
  }
  
  public static String ajE()
  {
    return ((TelephonyManager)com.lemon.faceu.common.e.c.akA().getContext().getSystemService("phone")).getNetworkOperatorName();
  }
  
  public static String ajF()
  {
    if (!com.lemon.faceu.common.e.c.akz()) {
      return "";
    }
    return com.lemon.faceu.common.e.c.akA().alq();
  }
  
  public static String ajG()
  {
    if (!com.lemon.faceu.common.e.c.akz()) {
      return "";
    }
    Object localObject = com.lemon.faceu.common.e.c.akA().akK();
    if (localObject != null)
    {
      localObject = ((com.lemon.faceu.common.aa.a)localObject).arG();
      if (localObject != null) {
        return ((d)localObject).getString(20157, "");
      }
      return "";
    }
    return "";
  }
  
  public static ArrayList<String> ajH()
  {
    List localList = com.lemon.faceu.common.e.c.akA().getContext().getPackageManager().getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    if (localList == null) {
      return null;
    }
    int i = 0;
    if (i < localList.size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
      if (localApplicationInfo.packageName.startsWith("com.android")) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(localApplicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static boolean ajI()
  {
    return Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).contains("xiaomi");
  }
  
  /* Error */
  public static boolean ajJ()
  {
    // Byte code:
    //   0: getstatic 69	com/lemon/faceu/common/compatibility/c:level	I
    //   3: ifle +5 -> 8
    //   6: iconst_1
    //   7: ireturn
    //   8: new 380	java/util/Properties
    //   11: dup
    //   12: invokespecial 381	java/util/Properties:<init>	()V
    //   15: astore_3
    //   16: new 383	java/io/File
    //   19: dup
    //   20: invokestatic 389	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   23: ldc_w 391
    //   26: invokespecial 394	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   29: astore_0
    //   30: aload_0
    //   31: ifnull +44 -> 75
    //   34: aload_0
    //   35: invokevirtual 397	java/io/File:exists	()Z
    //   38: ifeq +37 -> 75
    //   41: new 399	java/io/FileInputStream
    //   44: dup
    //   45: aload_0
    //   46: invokespecial 402	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   49: astore_1
    //   50: aload_1
    //   51: astore_0
    //   52: aload_3
    //   53: aload_1
    //   54: invokevirtual 406	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   57: aload_1
    //   58: astore_0
    //   59: aload_1
    //   60: invokevirtual 407	java/io/FileInputStream:close	()V
    //   63: iconst_0
    //   64: ifeq +11 -> 75
    //   67: new 409	java/lang/NullPointerException
    //   70: dup
    //   71: invokespecial 410	java/lang/NullPointerException:<init>	()V
    //   74: athrow
    //   75: aload_3
    //   76: ldc_w 412
    //   79: invokevirtual 415	java/util/Properties:containsKey	(Ljava/lang/Object;)Z
    //   82: ifne +13 -> 95
    //   85: aload_3
    //   86: ldc_w 417
    //   89: invokevirtual 415	java/util/Properties:containsKey	(Ljava/lang/Object;)Z
    //   92: ifeq +88 -> 180
    //   95: aload_3
    //   96: ldc_w 412
    //   99: invokevirtual 420	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   102: astore_0
    //   103: aload_0
    //   104: invokestatic 238	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   107: ifeq +66 -> 173
    //   110: iconst_1
    //   111: putstatic 69	com/lemon/faceu/common/compatibility/c:level	I
    //   114: iconst_1
    //   115: ireturn
    //   116: astore_0
    //   117: aload_0
    //   118: invokevirtual 423	java/lang/Exception:printStackTrace	()V
    //   121: goto -46 -> 75
    //   124: astore_2
    //   125: aconst_null
    //   126: astore_1
    //   127: aload_1
    //   128: astore_0
    //   129: aload_2
    //   130: invokevirtual 423	java/lang/Exception:printStackTrace	()V
    //   133: aload_1
    //   134: ifnull -59 -> 75
    //   137: aload_1
    //   138: invokevirtual 407	java/io/FileInputStream:close	()V
    //   141: goto -66 -> 75
    //   144: astore_0
    //   145: aload_0
    //   146: invokevirtual 423	java/lang/Exception:printStackTrace	()V
    //   149: goto -74 -> 75
    //   152: astore_1
    //   153: aconst_null
    //   154: astore_0
    //   155: aload_0
    //   156: ifnull +7 -> 163
    //   159: aload_0
    //   160: invokevirtual 407	java/io/FileInputStream:close	()V
    //   163: aload_1
    //   164: athrow
    //   165: astore_0
    //   166: aload_0
    //   167: invokevirtual 423	java/lang/Exception:printStackTrace	()V
    //   170: goto -7 -> 163
    //   173: aload_0
    //   174: invokestatic 429	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   177: putstatic 69	com/lemon/faceu/common/compatibility/c:level	I
    //   180: getstatic 69	com/lemon/faceu/common/compatibility/c:level	I
    //   183: ifgt -177 -> 6
    //   186: iconst_0
    //   187: ireturn
    //   188: astore_0
    //   189: aload_0
    //   190: invokevirtual 423	java/lang/Exception:printStackTrace	()V
    //   193: goto -13 -> 180
    //   196: astore_1
    //   197: goto -42 -> 155
    //   200: astore_2
    //   201: goto -74 -> 127
    // Local variable table:
    //   start	length	slot	name	signature
    //   29	75	0	localObject1	Object
    //   116	2	0	localException1	Exception
    //   128	1	0	localObject2	Object
    //   144	2	0	localException2	Exception
    //   154	6	0	localObject3	Object
    //   165	9	0	localException3	Exception
    //   188	2	0	localException4	Exception
    //   49	89	1	localFileInputStream	java.io.FileInputStream
    //   152	12	1	localObject4	Object
    //   196	1	1	localObject5	Object
    //   124	6	2	localException5	Exception
    //   200	1	2	localException6	Exception
    //   15	81	3	localProperties	java.util.Properties
    // Exception table:
    //   from	to	target	type
    //   67	75	116	java/lang/Exception
    //   41	50	124	java/lang/Exception
    //   137	141	144	java/lang/Exception
    //   41	50	152	finally
    //   159	163	165	java/lang/Exception
    //   173	180	188	java/lang/Exception
    //   52	57	196	finally
    //   59	63	196	finally
    //   129	133	196	finally
    //   52	57	200	java/lang/Exception
    //   59	63	200	java/lang/Exception
  }
  
  private static String ajK()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      try
      {
        Object localObject = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[] { String.class });
        ((Method)localObject).setAccessible(true);
        localObject = (String)((Method)localObject).invoke(null, new Object[] { "qemu.hw.mainkeys" });
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        return null;
      }
    }
    return null;
  }
  
  public static String ajL()
  {
    Object localObject = com.lemon.faceu.common.e.c.akA().getContext().getResources().getConfiguration().locale;
    String str2 = ((Locale)localObject).getLanguage();
    if ("in".equals(str2))
    {
      localObject = "id";
      return localObject;
    }
    if (((Locale)localObject).toString().contains("zh_CN")) {
      return "zh";
    }
    String[] arrayOfString = dka;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label92;
      }
      String str1 = arrayOfString[i];
      localObject = str1;
      if (str1.equalsIgnoreCase(str2)) {
        break;
      }
      i += 1;
    }
    label92:
    return "en";
  }
  
  public static String ajM()
  {
    String str2 = com.lemon.faceu.common.e.c.akA().getContext().getResources().getConfiguration().locale.getLanguage();
    String str1 = str2;
    if ("in".equals(str2)) {
      str1 = "id";
    }
    return str1;
  }
  
  public static String ajN()
  {
    if ("unknown_".equals(djT)) {
      djT = h.V(com.lemon.faceu.common.e.c.akA().getContext(), "beauty_pref_location_base_language_config");
    }
    Object localObject;
    if ("true".equals(djT))
    {
      localObject = Locale.getDefault().getCountry();
      g.i("DeviceInfo", "Debug local:" + (String)localObject);
    }
    for (;;)
    {
      com.lemon.faceu.common.e.c.akA().akO().setString("sys_location_info_cache", (String)localObject);
      return localObject;
      String str = k.getCountryCode();
      localObject = str;
      if (com.lemon.faceu.sdk.utils.i.lC(str)) {
        localObject = Locale.getDefault().getCountry();
      }
    }
  }
  
  public static boolean ajO()
  {
    return cx(com.lemon.faceu.common.e.c.akA().getContext()).equalsIgnoreCase("zh");
  }
  
  public static boolean ajP()
  {
    return cx(com.lemon.faceu.common.e.c.akA().getContext()).equalsIgnoreCase("ja");
  }
  
  public static String aja()
  {
    return Settings.Secure.getString(com.lemon.faceu.common.e.c.akA().getContext().getContentResolver(), "android_id");
  }
  
  public static String ajb()
  {
    for (Object localObject = null;; localObject = str)
    {
      try
      {
        if (Build.VERSION.SDK_INT < 14) {
          break label21;
        }
        str = Build.getRadioVersion();
        localObject = str;
      }
      catch (IncompatibleClassChangeError localIncompatibleClassChangeError)
      {
        String str;
        g.e("DeviceInfo", "May cause dvmFindCatchBlock crash!" + localIncompatibleClassChangeError.getMessage());
        throw ((IncompatibleClassChangeError)new IncompatibleClassChangeError("May cause dvmFindCatchBlock crash!").initCause(localIncompatibleClassChangeError));
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          label21:
          g.e("DeviceInfo", "unknown exception, " + localThrowable.getMessage());
        }
      }
      return com.lemon.faceu.sdk.utils.i.lD(localObject);
      str = Build.RADIO;
    }
  }
  
  public static String ajc()
  {
    return "";
  }
  
  public static String ajd()
  {
    String str1 = null;
    String str2 = ajc();
    if ((str2.startsWith("46000")) || (str2.startsWith("46002"))) {
      str1 = "中国移动";
    }
    do
    {
      return str1;
      if (str2.startsWith("46001")) {
        return "中国联通";
      }
    } while (!str2.startsWith("46003"));
    return "中国电信";
  }
  
  public static String aje()
  {
    return "";
  }
  
  public static int ajf()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu").listFiles(new c.a()).length;
      return i;
    }
    catch (Exception localException) {}
    return 1;
  }
  
  public static String ajg()
  {
    DisplayMetrics localDisplayMetrics = com.lemon.faceu.common.e.c.akA().getContext().getResources().getDisplayMetrics();
    return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
  }
  
  public static String ajh()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String aji()
  {
    return Build.MODEL;
  }
  
  public static String ajj()
  {
    if (!TextUtils.isEmpty(djS)) {
      return djS;
    }
    String str1 = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
    try
    {
      String str2 = Build.class.getField("SERIAL").get(null).toString();
      djS = new UUID(str1.hashCode(), str2.hashCode()).toString();
      str2 = djS;
      return str2;
    }
    catch (Exception localException)
    {
      djS = new UUID(str1.hashCode(), "serial".hashCode()).toString();
    }
    return djS;
  }
  
  public static String ajk()
  {
    return "26";
  }
  
  public static int ajl()
  {
    return 26;
  }
  
  public static String ajm()
  {
    return Build.DISPLAY;
  }
  
  public static String ajn()
  {
    return "1.7.8";
  }
  
  public static String ajo()
  {
    PackageManager localPackageManager = com.lemon.faceu.common.e.c.akA().getContext().getPackageManager();
    return com.lemon.faceu.common.e.c.akA().getContext().getApplicationInfo().loadLabel(localPackageManager).toString();
  }
  
  public static long ajp()
  {
    try
    {
      for (long l = System.currentTimeMillis(); dke >= l; l += 1L) {}
      dke = l;
      return l;
    }
    finally {}
  }
  
  public static int ajq()
  {
    return (int)(System.currentTimeMillis() / 1000L);
  }
  
  public static String ajr()
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
      } while ((!(localInetAddress instanceof Inet4Address)) || (localInetAddress.isLoopbackAddress()));
      Object localObject = localInetAddress.getHostAddress();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
    return "";
  }
  
  public static String ajs()
  {
    return Build.BRAND;
  }
  
  public static String ajt()
  {
    return Build.CPU_ABI;
  }
  
  public static String aju()
  {
    switch (t.getNetworkType(com.lemon.faceu.common.e.c.akA().getContext()))
    {
    default: 
      return "";
    case 2: 
      return "2g";
    case 3: 
      return "3g";
    case 4: 
      return "4g";
    }
    return "wifi";
  }
  
  public static int ajv()
  {
    switch (t.getNetworkType(com.lemon.faceu.common.e.c.akA().getContext()))
    {
    default: 
      return 0;
    case 2: 
      return 2;
    case 3: 
      return 3;
    case 4: 
      return 4;
    case 1: 
      return 1;
    }
    return 5;
  }
  
  public static String ajw()
  {
    return Build.SERIAL;
  }
  
  public static String ajx()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public static String ajy()
  {
    if (!com.lemon.faceu.common.e.c.akz()) {
      return "";
    }
    com.lemon.faceu.common.aa.a localA = com.lemon.faceu.common.e.c.akA().akK();
    if ((localA != null) && (localA.arO() == 2)) {
      return "female";
    }
    return "male";
  }
  
  @af
  public static String ajz()
  {
    Object localObject2;
    if (!com.lemon.faceu.common.e.c.akz()) {
      localObject2 = "";
    }
    Object localObject1;
    label92:
    ab localAb;
    do
    {
      return localObject2;
      int i = com.lemon.faceu.common.e.c.akA().alp();
      localObject1 = null;
      switch (i)
      {
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break;
        }
        localObject2 = com.lemon.faceu.common.e.c.akA().akK();
        if (localObject2 != null) {
          break label92;
        }
        return "";
        localObject1 = "wechat";
        continue;
        localObject1 = "qq";
        continue;
        localObject1 = "mobile";
      }
      localAb = aa.jl(((com.lemon.faceu.common.aa.a)localObject2).getUid());
      localObject2 = localObject1;
    } while (localAb == null);
    switch (localAb.atg())
    {
    default: 
      return localObject1;
    case 1: 
      return "third_party";
    }
    return "mobile";
  }
  
  public static String cA(Context paramContext)
  {
    String str = "";
    Object localObject = str;
    if (Build.VERSION.SDK_INT >= 3) {}
    try
    {
      paramContext = paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
      if (paramContext != null)
      {
        localObject = paramContext;
        if (paramContext.length() != 0) {}
      }
      else
      {
        localObject = "";
        g.i("DeviceInfo", "No mFaceUStatisticsStore found");
      }
      return localObject;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        g.i("DeviceInfo", "Can't get Installer package");
        paramContext = str;
      }
    }
  }
  
  public static String cB(Context paramContext)
  {
    return "26";
  }
  
  public static String cC(Context paramContext)
  {
    return String.valueOf((int)(paramContext.getResources().getDisplayMetrics().density * 160.0F));
  }
  
  public static String cD(Context paramContext)
  {
    switch (t.getNetworkType(paramContext))
    {
    default: 
      return "";
    case 2: 
      return "2g";
    case 3: 
      return "3g";
    case 4: 
      return "4g";
    }
    return "wifi";
  }
  
  /* Error */
  public static String cE(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokevirtual 814	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   6: invokevirtual 323	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   9: astore_1
    //   10: aload_1
    //   11: aload_0
    //   12: invokevirtual 793	android/content/Context:getPackageName	()Ljava/lang/String;
    //   15: iconst_0
    //   16: invokevirtual 817	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   19: astore_0
    //   20: aload_1
    //   21: aload_0
    //   22: invokevirtual 821	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   25: checkcast 49	java/lang/String
    //   28: areturn
    //   29: astore_0
    //   30: aconst_null
    //   31: astore_1
    //   32: aload_2
    //   33: astore_0
    //   34: goto -14 -> 20
    //   37: astore_0
    //   38: aload_2
    //   39: astore_0
    //   40: goto -20 -> 20
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	paramContext	Context
    //   9	23	1	localPackageManager	PackageManager
    //   1	38	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	29	android/content/pm/PackageManager$NameNotFoundException
    //   10	20	37	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static String cF(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
  }
  
  @Deprecated
  public static String cG(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }
  
  public static String cH(Context paramContext)
  {
    if (!TextUtils.isEmpty(dkf)) {
      return dkf;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64);
      if ((paramContext == null) || (paramContext.signatures == null) || (paramContext.signatures.length < 1)) {
        return dkf;
      }
      paramContext = paramContext.signatures[0];
      if (paramContext == null) {
        return dkf;
      }
      dkf = m.K(paramContext.toByteArray());
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        g.w("AppLog", "failed to get package sianature: " + paramContext);
      }
    }
    return dkf;
  }
  
  public static String cI(Context paramContext)
  {
    if (!TextUtils.isEmpty(dkg)) {
      return dkg;
    }
    com.lemon.faceu.common.s.a.db(paramContext);
    if (com.lemon.faceu.common.s.a.isInitialized()) {
      dkg = com.lemon.faceu.common.s.a.apW();
    }
    return dkg;
  }
  
  public static i cJ(Context paramContext)
  {
    if (dkd == null) {
      dkd = new i(paramContext);
    }
    return dkd;
  }
  
  public static int cK(Context paramContext)
  {
    int j = 0;
    int i = j;
    if (cL(paramContext))
    {
      paramContext = paramContext.getResources();
      int k = paramContext.getIdentifier("navigation_bar_height", "dimen", "android");
      i = j;
      if (k > 0) {
        i = paramContext.getDimensionPixelSize(k);
      }
    }
    return i;
  }
  
  @TargetApi(14)
  public static boolean cL(Context paramContext)
  {
    boolean bool = true;
    Resources localResources = paramContext.getResources();
    int i = localResources.getIdentifier("config_showNavigationBar", "bool", "android");
    if (i != 0)
    {
      bool = localResources.getBoolean(i);
      paramContext = ajK();
      if ("1".equals(paramContext)) {
        return false;
      }
      if ("0".equals(paramContext)) {
        return true;
      }
    }
    else
    {
      if (!ViewConfiguration.get(paramContext).hasPermanentMenuKey()) {}
      for (;;)
      {
        return bool;
        bool = false;
      }
    }
    return bool;
  }
  
  public static String cu(Context paramContext)
  {
    String str = com.lemon.faceu.common.e.c.akA().akN().getString(5);
    if (str != null) {
      return str;
    }
    str = cv(paramContext);
    paramContext = str;
    if (str == null) {
      paramContext = "1234567890ABCDEF";
    }
    com.lemon.faceu.common.e.c.akA().akN().setString(5, paramContext);
    return paramContext;
  }
  
  public static String cv(Context paramContext)
  {
    return null;
  }
  
  public static String cw(Context paramContext)
  {
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if (paramContext == null) {}
    for (paramContext = null; paramContext != null; paramContext = paramContext.getConnectionInfo()) {
      return paramContext.getMacAddress();
    }
    return null;
  }
  
  public static String cx(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.getLanguage();
  }
  
  public static String cy(Context paramContext)
  {
    switch (paramContext.getResources().getDisplayMetrics().densityDpi)
    {
    default: 
      return "other";
    case 120: 
      return "LDPI";
    case 160: 
      return "MDPI";
    case 213: 
      return "TVDPI";
    case 240: 
      return "HDPI";
    case 280: 
      return "XHDPI";
    case 320: 
      return "XHDPI";
    case 360: 
      return "XXHDPI";
    case 400: 
      return "XXHDPI";
    case 420: 
      return "XXHDPI";
    case 480: 
      return "XXHDPI";
    case 560: 
      return "XXXHDPI";
    }
    return "XXXHDPI";
  }
  
  public static String cz(Context paramContext)
  {
    return "1.7.8";
  }
  
  public static String getAppVersion()
  {
    return "1.7.8";
  }
  
  public static String getChannel()
  {
    return b.CHANNEL;
  }
  
  public static String getDeviceId()
  {
    if (!TextUtils.isEmpty(djS)) {
      return djS;
    }
    return ajj();
  }
  
  public static String getLocale()
  {
    Locale localLocale = Locale.getDefault();
    return localLocale.getLanguage() + "_" + localLocale.getCountry();
  }
  
  public static String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public static String getModel()
  {
    return Build.MODEL;
  }
  
  public static String getOS()
  {
    return "Android";
  }
  
  public static String getResolution(Context paramContext)
  {
    try
    {
      paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramContext.getMetrics(localDisplayMetrics);
      paramContext = localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      g.i("DeviceInfo", "Device resolution cannot be determined");
    }
    return "";
  }
  
  public static int getTimezone()
  {
    return Calendar.getInstance(Locale.getDefault()).get(15) / 60 / 60 / 1000;
  }
  
  public static String getUserId()
  {
    if (!com.lemon.faceu.common.e.c.akz()) {
      return "";
    }
    com.lemon.faceu.common.aa.a localA = com.lemon.faceu.common.e.c.akA().akK();
    if (localA == null) {
      return "";
    }
    return localA.getUid();
  }
  
  public static void gn(String paramString)
  {
    djS = paramString;
  }
}
