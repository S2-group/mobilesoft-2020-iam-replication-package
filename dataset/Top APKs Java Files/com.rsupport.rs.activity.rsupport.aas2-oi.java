import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils.SimpleStringSplitter;
import android.text.format.DateFormat;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class oi
  implements pj
{
  public static int a;
  private static pj jdField_a_of_type_Pj;
  private BroadcastReceiver jdField_a_of_type_AndroidContentBroadcastReceiver;
  private Context jdField_a_of_type_AndroidContentContext;
  private AudioManager jdField_a_of_type_AndroidMediaAudioManager;
  private WifiManager jdField_a_of_type_AndroidNetWifiWifiManager;
  private final String jdField_a_of_type_JavaLangString = "QuickSetting4";
  private HashMap jdField_a_of_type_JavaUtilHashMap;
  private float[] jdField_a_of_type_ArrayOfFloat;
  private int[] jdField_a_of_type_ArrayOfInt;
  private String[] jdField_a_of_type_ArrayOfJavaLangString;
  private BroadcastReceiver b;
  
  static
  {
    jdField_a_of_type_Int = 2;
  }
  
  public oi(Context paramContext)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[1] = "MM-dd-yyyy";
    arrayOfString[2] = "dd-MM-yyyy";
    arrayOfString[3] = "yyyy-MM-dd";
    this.jdField_a_of_type_ArrayOfJavaLangString = arrayOfString;
    this.jdField_a_of_type_ArrayOfInt = new int[] { 15000, 30000, 60000, 120000, 300000, 600000 };
    this.jdField_a_of_type_AndroidContentBroadcastReceiver = new oj(this);
    this.b = new ok(this);
    this.jdField_a_of_type_AndroidContentContext = paramContext;
    this.jdField_a_of_type_AndroidNetWifiWifiManager = ((WifiManager)paramContext.getSystemService("wifi"));
    this.jdField_a_of_type_AndroidMediaAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
    cp.a.a((ConnectivityManager)paramContext.getSystemService("connectivity"));
    paramContext = cy.a;
    if (!cy.a())
    {
      paramContext = cy.a;
      this.jdField_a_of_type_ArrayOfFloat = cy.a();
    }
    this.jdField_a_of_type_AndroidContentContext.registerReceiver(this.jdField_a_of_type_AndroidContentBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    this.jdField_a_of_type_AndroidContentContext.registerReceiver(this.b, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
    this.jdField_a_of_type_JavaUtilHashMap = new HashMap();
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)1000), new Short[] { Short.valueOf(1100), Short.valueOf(1200), Short.valueOf(1210) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)2000), new Short[] { Short.valueOf(2100), Short.valueOf(2200), Short.valueOf(2210) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)3000), new Short[] { Short.valueOf(3100) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)4000), new Short[] { Short.valueOf(4100), Short.valueOf(4200), Short.valueOf(4210), Short.valueOf(4220), Short.valueOf(4230), Short.valueOf(4300), Short.valueOf(4500), Short.valueOf(4510), Short.valueOf(4520) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)5000), new Short[] { Short.valueOf(5100), Short.valueOf(5200), Short.valueOf(5300), Short.valueOf(5400), Short.valueOf(5500), Short.valueOf(5600) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)6000), new Short[] { Short.valueOf(6100), Short.valueOf(6110), Short.valueOf(6200), Short.valueOf(6300), Short.valueOf(6400) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)7000), new Short[] { Short.valueOf(7000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)8000), new Short[] { Short.valueOf(8000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)9000), new Short[] { Short.valueOf(9000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)10000), new Short[] { Short.valueOf(10100) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)11000), new Short[] { Short.valueOf(11100), Short.valueOf(11200), Short.valueOf(11300) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)12000), new Short[] { Short.valueOf(12100), Short.valueOf(12200) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)13000), new Short[] { Short.valueOf(13100), Short.valueOf(13200) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)14000), new Short[] { Short.valueOf(14000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)15000), new Short[] { Short.valueOf(15100), Short.valueOf(15200), Short.valueOf(15300) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)16000), new Short[] { Short.valueOf(16100) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)17000), new Short[] { Short.valueOf(17010), Short.valueOf(17100), Short.valueOf(17200), Short.valueOf(17300), Short.valueOf(17500), Short.valueOf(17600), Short.valueOf(17700), Short.valueOf(17800), Short.valueOf(17900), Short.valueOf(17920), Short.valueOf(17930), Short.valueOf(17940), Short.valueOf(17950), Short.valueOf(17960), Short.valueOf(17970), Short.valueOf(17980), Short.valueOf(17990) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)18000), new Short[] { Short.valueOf(18000) });
  }
  
  private boolean A()
  {
    try
    {
      boolean bool = DateFormat.is24HourFormat(this.jdField_a_of_type_AndroidContentContext);
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean B()
  {
    Object localObject2 = Settings.Secure.getString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "enabled_accessibility_services");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "";
    }
    new HashSet();
    localObject2 = new TextUtils.SimpleStringSplitter(':');
    ((TextUtils.SimpleStringSplitter)localObject2).setString((String)localObject1);
    do
    {
      if (!((TextUtils.SimpleStringSplitter)localObject2).hasNext()) {
        return false;
      }
    } while (!ComponentName.unflattenFromString(((TextUtils.SimpleStringSplitter)localObject2).next()).toString().contains("com.google.android.marvin.talkback.TalkBackService"));
    return true;
  }
  
  private boolean C()
  {
    boolean bool = false;
    try
    {
      int i = Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "adb_enabled", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean D()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "stay_on_while_plugged_in", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean E()
  {
    boolean bool = false;
    try
    {
      int i = Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "mock_location", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static boolean F()
  {
    try
    {
      cx localCx = cx.a;
      boolean bool = cx.a("persist.sys.strictmode.visual");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean G()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "pointer_location", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean H()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "show_touches", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean I()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "show_processes", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static boolean J()
  {
    try
    {
      cx localCx = cx.a;
      cx.a("persist.sys.ui.hw");
      return false;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private boolean K()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "always_finish_activities", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean L()
  {
    boolean bool = false;
    try
    {
      int i = Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "development_settings_enabled", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static boolean M()
  {
    try
    {
      cx localCx = cx.a;
      boolean bool = cx.a("debug.layout");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static boolean N()
  {
    try
    {
      cx localCx = cx.a;
      boolean bool = cx.a("debug.hwui.show_dirty_regions");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static boolean O()
  {
    try
    {
      cx localCx = cx.a;
      boolean bool = cx.a("debug.hwui.profile");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private byte a(short paramShort)
  {
    short s2 = 2;
    short s3 = 1;
    short s4 = 0;
    short s7 = 0;
    short s8 = 0;
    short s5 = 0;
    short s6 = 0;
    short s1 = 0;
    byte b1 = 0;
    switch (paramShort)
    {
    case 2200: 
    case 4200: 
    case 5100: 
    case 5101: 
    case 5200: 
    case 5201: 
    case 7000: 
    case 8000: 
    case 9000: 
    case 13100: 
    case 13101: 
    case 13310: 
    case 13311: 
    case 14000: 
    case 15301: 
    default: 
      b1 = -1;
    case 1100: 
    case 1210: 
    case 2100: 
      do
      {
        do
        {
          return b1;
          if (((WifiManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("wifi")).isWifiEnabled()) {}
          for (paramShort = 1; paramShort != 0; paramShort = 0) {
            return 1;
          }
        } while (!b());
        return 1;
      } while (!BluetoothAdapter.getDefaultAdapter().isEnabled());
      return 1;
    case 2210: 
      s1 = co.a.a();
      if ((s1 > 0) && (s1 >= 120)) {
        paramShort = 0;
      }
      break;
    }
    for (;;)
    {
      return (byte)paramShort;
      if (s1 >= 300)
      {
        paramShort = 1;
      }
      else
      {
        paramShort = s2;
        if (s1 < 3600)
        {
          if (s1 == 0)
          {
            paramShort = 3;
            continue;
            if (!d()) {
              break;
            }
            return 1;
            if (!e()) {
              break;
            }
            return 1;
            if (!f()) {
              break;
            }
            return 1;
            if (!g()) {
              break;
            }
            return 1;
            if (!h()) {
              break;
            }
            return 1;
            if (!i()) {
              break;
            }
            return 1;
            if (!j()) {
              break;
            }
            return 1;
            if (!k()) {
              break;
            }
            return 1;
            if (!l()) {
              break;
            }
            return 1;
            if (!m()) {
              break;
            }
            return 1;
            if (!n()) {
              break;
            }
            return 1;
            if (!o()) {
              break;
            }
            return 1;
            if (!p()) {
              break;
            }
            return 1;
            return (byte)c();
            if (!q()) {
              break;
            }
            return 1;
            if (!r()) {
              break;
            }
            return 1;
            s1 = e();
            if ((s1 > 0) && (s1 <= 15000)) {
              paramShort = s4;
            }
            for (;;)
            {
              return (byte)paramShort;
              if (s1 <= 30000)
              {
                paramShort = 1;
              }
              else if (s1 <= 60000)
              {
                paramShort = 2;
              }
              else if (s1 <= 120000)
              {
                paramShort = 3;
              }
              else if (s1 <= 300000)
              {
                paramShort = 4;
              }
              else
              {
                paramShort = s4;
                if (s1 <= 600000) {
                  paramShort = 5;
                }
              }
            }
            float f = a();
            if (f == 0.85F) {
              paramShort = s7;
            }
            for (;;)
            {
              return (byte)paramShort;
              if (f == 1.0F)
              {
                paramShort = 1;
              }
              else if (f == 1.15F)
              {
                paramShort = 2;
              }
              else
              {
                paramShort = s7;
                if (f == 1.3F) {
                  paramShort = 3;
                }
              }
            }
            if (!ContentResolver.getMasterSyncAutomatically()) {
              break;
            }
            return 1;
            if (!t()) {
              break;
            }
            return 1;
            if (!u()) {
              break;
            }
            return 1;
            if (!v()) {
              break;
            }
            return 1;
            if (!w()) {
              break;
            }
            return 1;
            if (Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "install_non_market_apps", 0) > 0) {}
            for (paramShort = 1; paramShort != 0; paramShort = 0) {
              return 1;
            }
            if (!y()) {
              break;
            }
            return 1;
            if (!z()) {
              break;
            }
            return 1;
            if (!A()) {
              break;
            }
            return 1;
            Object localObject1 = Settings.System.getString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "date_format");
            if (((String)localObject1).equals("MM-dd-yyyy")) {
              paramShort = s8;
            }
            for (;;)
            {
              return (byte)paramShort;
              if (((String)localObject1).equals("dd-MM-yyyy"))
              {
                paramShort = 1;
              }
              else
              {
                paramShort = s8;
                if (((String)localObject1).equals("yyyy-MM-dd")) {
                  paramShort = 2;
                }
              }
            }
            Object localObject2 = Settings.Secure.getString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "enabled_accessibility_services");
            localObject1 = localObject2;
            if (localObject2 == null) {
              localObject1 = "";
            }
            new HashSet();
            localObject2 = new TextUtils.SimpleStringSplitter(':');
            ((TextUtils.SimpleStringSplitter)localObject2).setString((String)localObject1);
            label1175:
            if (!((TextUtils.SimpleStringSplitter)localObject2).hasNext()) {}
            for (paramShort = 0; paramShort != 0; paramShort = 1)
            {
              return 1;
              if (!ComponentName.unflattenFromString(((TextUtils.SimpleStringSplitter)localObject2).next()).toString().contains("com.google.android.marvin.talkback.TalkBackService")) {
                break label1175;
              }
            }
            if (!C()) {
              break;
            }
            return 1;
            if (!D()) {
              break;
            }
            return 1;
            if (!E()) {
              break;
            }
            return 1;
            if (!F()) {
              break;
            }
            return 1;
            if (!G()) {
              break;
            }
            return 1;
            if (!H()) {
              break;
            }
            return 1;
            if (!I()) {
              break;
            }
            return 1;
            J();
            return 0;
            f = b();
            if (f == 0.0F) {
              paramShort = s5;
            }
            for (;;)
            {
              return (byte)paramShort;
              paramShort = s5;
              if (f >= 0.0F) {
                if (f <= 0.5F)
                {
                  paramShort = 1;
                }
                else if (f <= 1.0F)
                {
                  paramShort = 2;
                }
                else if (f <= 1.5F)
                {
                  paramShort = 3;
                }
                else if (f <= 2.0F)
                {
                  paramShort = 4;
                }
                else if (f <= 5.0F)
                {
                  paramShort = 5;
                }
                else
                {
                  paramShort = s5;
                  if (f <= 10.0F) {
                    paramShort = 6;
                  }
                }
              }
            }
            f = c();
            if (f == 0.0F) {
              paramShort = s6;
            }
            for (;;)
            {
              return (byte)paramShort;
              paramShort = s6;
              if (f >= 0.0F) {
                if (f <= 0.5F)
                {
                  paramShort = 1;
                }
                else if (f <= 1.0F)
                {
                  paramShort = 2;
                }
                else if (f <= 1.5F)
                {
                  paramShort = 3;
                }
                else if (f <= 2.0F)
                {
                  paramShort = 4;
                }
                else if (f <= 5.0F)
                {
                  paramShort = 5;
                }
                else
                {
                  paramShort = s6;
                  if (f <= 10.0F) {
                    paramShort = 6;
                  }
                }
              }
            }
            if (!K()) {
              break;
            }
            return 1;
            s2 = k();
            if (s2 == -1) {
              paramShort = s1;
            }
            for (;;)
            {
              return (byte)paramShort;
              paramShort = s1;
              if (s2 >= -1) {
                if (s2 == 0)
                {
                  paramShort = 1;
                }
                else if (s2 == 1)
                {
                  paramShort = 2;
                }
                else if (s2 == 2)
                {
                  paramShort = 3;
                }
                else if (s2 == 3)
                {
                  paramShort = 4;
                }
                else
                {
                  paramShort = s1;
                  if (s2 == 4) {
                    paramShort = 5;
                  }
                }
              }
            }
            if (!L()) {
              break;
            }
            return 1;
            localObject1 = g();
            System.out.println("hdcp 정보 : " + (String)localObject1);
            if (((String)localObject1).contains("never")) {
              paramShort = 0;
            }
            for (;;)
            {
              return (byte)paramShort;
              paramShort = s3;
              if (!((String)localObject1).equals("only"))
              {
                paramShort = s3;
                if (((String)localObject1).equals("always")) {
                  paramShort = 2;
                }
              }
            }
            if (!M()) {
              break;
            }
            return 1;
            if (!N()) {
              break;
            }
            return 1;
            if (!O()) {
              break;
            }
            return 1;
          }
          paramShort = 0;
        }
      }
    }
  }
  
  private static float a()
  {
    try
    {
      Object localObject = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
      float f = ((Configuration)((Class)localObject.getClass()).getMethod("getConfiguration", new Class[0]).invoke(localObject, new Object[0])).fontScale;
      return (int)f;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1.0F;
  }
  
  private static int a()
  {
    int i = co.a.a();
    if ((i > 0) && (i >= 120)) {}
    do
    {
      return 0;
      if (i >= 300) {
        return 1;
      }
      if (i >= 3600) {
        return 2;
      }
    } while (i != 0);
    return 3;
  }
  
  private String a()
  {
    Object localObject1 = "";
    Object localObject2 = new RingtoneManager(this.jdField_a_of_type_AndroidContentContext);
    ((RingtoneManager)localObject2).setType(1);
    Cursor localCursor = ((RingtoneManager)localObject2).getCursor();
    localCursor.moveToFirst();
    do
    {
      localCursor.getString(0);
      localObject2 = localCursor.getString(1);
      localCursor.getString(2);
      localObject2 = localObject1 + (String)localObject2 + ",";
      localObject1 = localObject2;
    } while (localCursor.moveToNext());
    localObject1 = localObject2;
    if (((String)localObject2).substring(((String)localObject2).length() - 1).equals(",")) {
      localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
    }
    localCursor.close();
    return localObject1;
  }
  
  private String a(int paramInt)
  {
    new RingtoneManager(this.jdField_a_of_type_AndroidContentContext);
    Uri localUri = RingtoneManager.getActualDefaultRingtoneUri(this.jdField_a_of_type_AndroidContentContext, paramInt);
    return RingtoneManager.getRingtone(this.jdField_a_of_type_AndroidContentContext, localUri).getTitle(this.jdField_a_of_type_AndroidContentContext);
  }
  
  private String a(int paramInt, String paramString)
  {
    RingtoneManager localRingtoneManager = new RingtoneManager(this.jdField_a_of_type_AndroidContentContext);
    localRingtoneManager.setType(paramInt);
    Cursor localCursor = localRingtoneManager.getCursor();
    localCursor.moveToFirst();
    int i = 0;
    localCursor.getString(0);
    String str = localCursor.getString(1);
    localCursor.getString(2);
    if (str.equals(paramString))
    {
      paramString = localRingtoneManager.getRingtoneUri(i);
      RingtoneManager.setActualDefaultRingtoneUri(this.jdField_a_of_type_AndroidContentContext, paramInt, paramString);
    }
    for (;;)
    {
      localCursor.close();
      return "";
      i += 1;
      if (localCursor.moveToNext()) {
        break;
      }
    }
  }
  
  private static String a(String paramString)
  {
    if (paramString.length() == 0) {
      return paramString;
    }
    return Character.toUpperCase(paramString.charAt(0)) + paramString.substring(1);
  }
  
  private static void a(float paramFloat)
  {
    try
    {
      Object localObject = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
      Class localClass = localObject.getClass();
      Configuration localConfiguration = new Configuration();
      localConfiguration.fontScale = paramFloat;
      ((Class)localClass).getMethod("updatePersistentConfiguration", new Class[] { Configuration.class }).invoke(localObject, new Object[] { localConfiguration });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void a(int paramInt)
  {
    try
    {
      Settings.System.putInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "screen_brightness", paramInt);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void a(String paramString1, String paramString2)
  {
    a("android.intent.action.MAIN", paramString1, paramString2);
  }
  
  private void a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = new Intent(paramString1);
      if ((paramString2 != null) && (paramString3 != null)) {
        paramString1.setComponent(new ComponentName(paramString2, paramString3));
      }
      paramString1.setFlags(268435456);
      this.jdField_a_of_type_AndroidContentContext.getApplicationContext().startActivity(paramString1);
      return;
    }
    catch (Exception paramString1)
    {
      System.out.println("The page doesn't exist.");
      paramString1.printStackTrace();
    }
  }
  
  private void a(boolean paramBoolean)
  {
    ((WifiManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("wifi")).setWifiEnabled(paramBoolean);
  }
  
  private boolean a()
  {
    return ((WifiManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("wifi")).isWifiEnabled();
  }
  
  private boolean a(String paramString)
  {
    Object localObject2 = Settings.Secure.getString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "enabled_accessibility_services");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "";
    }
    new HashSet();
    localObject2 = new TextUtils.SimpleStringSplitter(':');
    ((TextUtils.SimpleStringSplitter)localObject2).setString((String)localObject1);
    do
    {
      if (!((TextUtils.SimpleStringSplitter)localObject2).hasNext()) {
        return false;
      }
    } while (!ComponentName.unflattenFromString(((TextUtils.SimpleStringSplitter)localObject2).next()).toString().contains(paramString));
    return true;
  }
  
  private static boolean a(short paramShort)
  {
    boolean bool = false;
    if ((paramShort == 5101) || (paramShort == 5201) || (paramShort == 13101) || (paramShort == 13311)) {
      bool = true;
    }
    return bool;
  }
  
  private boolean a(boolean paramBoolean)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("wifi");
      Method localMethod = WifiManager.class.getDeclaredMethod("setWifiApEnabled", new Class[] { WifiConfiguration.class, Boolean.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(localWifiManager, new Object[] { null, Boolean.valueOf(paramBoolean) });
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  /* Error */
  private byte[] a(int paramInt, short paramShort)
  {
    // Byte code:
    //   0: new 459	android/media/RingtoneManager
    //   3: dup
    //   4: aload_0
    //   5: getfield 62	oi:jdField_a_of_type_AndroidContentContext	Landroid/content/Context;
    //   8: invokespecial 461	android/media/RingtoneManager:<init>	(Landroid/content/Context;)V
    //   11: pop
    //   12: aload_0
    //   13: getfield 62	oi:jdField_a_of_type_AndroidContentContext	Landroid/content/Context;
    //   16: iload_1
    //   17: invokestatic 501	android/media/RingtoneManager:getActualDefaultRingtoneUri	(Landroid/content/Context;I)Landroid/net/Uri;
    //   20: astore_3
    //   21: aload_0
    //   22: getfield 62	oi:jdField_a_of_type_AndroidContentContext	Landroid/content/Context;
    //   25: aload_3
    //   26: invokestatic 505	android/media/RingtoneManager:getRingtone	(Landroid/content/Context;Landroid/net/Uri;)Landroid/media/Ringtone;
    //   29: aload_0
    //   30: getfield 62	oi:jdField_a_of_type_AndroidContentContext	Landroid/content/Context;
    //   33: invokevirtual 511	android/media/Ringtone:getTitle	(Landroid/content/Context;)Ljava/lang/String;
    //   36: ldc_w 627
    //   39: invokevirtual 631	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   42: astore 4
    //   44: aload 4
    //   46: arraylength
    //   47: iconst_2
    //   48: iadd
    //   49: iconst_2
    //   50: iadd
    //   51: newarray byte
    //   53: astore_3
    //   54: iload_2
    //   55: invokestatic 636	ve:a	(S)[B
    //   58: astore 5
    //   60: aload 5
    //   62: iconst_0
    //   63: aload_3
    //   64: iconst_0
    //   65: aload 5
    //   67: arraylength
    //   68: invokestatic 640	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   71: aload 4
    //   73: iconst_0
    //   74: aload_3
    //   75: iconst_2
    //   76: aload 4
    //   78: arraylength
    //   79: invokestatic 640	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   82: aload_3
    //   83: areturn
    //   84: astore 4
    //   86: aconst_null
    //   87: astore_3
    //   88: aload 4
    //   90: invokevirtual 145	java/lang/Exception:printStackTrace	()V
    //   93: aload_3
    //   94: areturn
    //   95: astore 4
    //   97: goto -9 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	oi
    //   0	100	1	paramInt	int
    //   0	100	2	paramShort	short
    //   20	74	3	localObject	Object
    //   42	35	4	arrayOfByte1	byte[]
    //   84	5	4	localException1	Exception
    //   95	1	4	localException2	Exception
    //   58	8	5	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   0	54	84	java/lang/Exception
    //   54	82	95	java/lang/Exception
  }
  
  private static float b()
  {
    try
    {
      Class localClass = Class.forName("android.view.IWindowManager");
      Object localObject = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("window") });
      if (localObject != null)
      {
        float f = ((Float)localClass.getMethod("getAnimationScale", new Class[] { Integer.TYPE }).invoke(localObject, new Object[] { Integer.valueOf(0) })).floatValue();
        return f;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1.0F;
  }
  
  private static int b()
  {
    return co.a.a();
  }
  
  private String b()
  {
    Object localObject1 = "";
    Object localObject2 = new RingtoneManager(this.jdField_a_of_type_AndroidContentContext);
    ((RingtoneManager)localObject2).setType(2);
    Cursor localCursor = ((RingtoneManager)localObject2).getCursor();
    localCursor.moveToFirst();
    do
    {
      localCursor.getString(0);
      localObject2 = localCursor.getString(1);
      localCursor.getString(2);
      localObject2 = localObject1 + (String)localObject2 + ",";
      localObject1 = localObject2;
    } while (localCursor.moveToNext());
    localObject1 = localObject2;
    if (((String)localObject2).substring(((String)localObject2).length() - 1).equals(",")) {
      localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
    }
    localCursor.close();
    return localObject1;
  }
  
  private void b()
  {
    this.jdField_a_of_type_JavaUtilHashMap = new HashMap();
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)1000), new Short[] { Short.valueOf(1100), Short.valueOf(1200), Short.valueOf(1210) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)2000), new Short[] { Short.valueOf(2100), Short.valueOf(2200), Short.valueOf(2210) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)3000), new Short[] { Short.valueOf(3100) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)4000), new Short[] { Short.valueOf(4100), Short.valueOf(4200), Short.valueOf(4210), Short.valueOf(4220), Short.valueOf(4230), Short.valueOf(4300), Short.valueOf(4500), Short.valueOf(4510), Short.valueOf(4520) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)5000), new Short[] { Short.valueOf(5100), Short.valueOf(5200), Short.valueOf(5300), Short.valueOf(5400), Short.valueOf(5500), Short.valueOf(5600) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)6000), new Short[] { Short.valueOf(6100), Short.valueOf(6110), Short.valueOf(6200), Short.valueOf(6300), Short.valueOf(6400) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)7000), new Short[] { Short.valueOf(7000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)8000), new Short[] { Short.valueOf(8000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)9000), new Short[] { Short.valueOf(9000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)10000), new Short[] { Short.valueOf(10100) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)11000), new Short[] { Short.valueOf(11100), Short.valueOf(11200), Short.valueOf(11300) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)12000), new Short[] { Short.valueOf(12100), Short.valueOf(12200) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)13000), new Short[] { Short.valueOf(13100), Short.valueOf(13200) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)14000), new Short[] { Short.valueOf(14000) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)15000), new Short[] { Short.valueOf(15100), Short.valueOf(15200), Short.valueOf(15300) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)16000), new Short[] { Short.valueOf(16100) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)17000), new Short[] { Short.valueOf(17010), Short.valueOf(17100), Short.valueOf(17200), Short.valueOf(17300), Short.valueOf(17500), Short.valueOf(17600), Short.valueOf(17700), Short.valueOf(17800), Short.valueOf(17900), Short.valueOf(17920), Short.valueOf(17930), Short.valueOf(17940), Short.valueOf(17950), Short.valueOf(17960), Short.valueOf(17970), Short.valueOf(17980), Short.valueOf(17990) });
    this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf((short)18000), new Short[] { Short.valueOf(18000) });
  }
  
  private static void b(float paramFloat)
  {
    try
    {
      Class localClass = Class.forName("android.view.IWindowManager");
      Object localObject = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("window") });
      if (localObject != null) {
        localClass.getMethod("setAnimationScale", new Class[] { Integer.TYPE, Float.TYPE }).invoke(localObject, new Object[] { Integer.valueOf(0), Float.valueOf(paramFloat) });
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void b(int paramInt)
  {
    if (paramInt == 0) {
      c(15000);
    }
    do
    {
      return;
      if (paramInt <= 1)
      {
        c(30000);
        return;
      }
      if (paramInt <= 2)
      {
        c(60000);
        return;
      }
      if (paramInt <= 3)
      {
        c(120000);
        return;
      }
      if (paramInt <= 4)
      {
        c(300000);
        return;
      }
    } while (paramInt > 5);
    c(600000);
  }
  
  private void b(String paramString)
  {
    a(paramString, null, null);
  }
  
  private void b(short paramShort)
  {
    byte[] arrayOfByte = null;
    vf.c("QuickSetting4", "sendListValue : " + paramShort);
    switch (paramShort)
    {
    }
    for (;;)
    {
      if ((arrayOfByte != null) && (qq.b)) {
        qq.a().a.a(231, 216, arrayOfByte, arrayOfByte.length);
      }
      return;
      arrayOfByte = a(1, (short)5100);
      continue;
      arrayOfByte = a(2, (short)5200);
      continue;
      arrayOfByte = f();
    }
  }
  
  private static void b(boolean paramBoolean)
  {
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (paramBoolean)
    {
      localBluetoothAdapter.enable();
      return;
    }
    localBluetoothAdapter.disable();
  }
  
  private boolean b()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "wifi_networks_available_notification_on");
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static boolean b(short paramShort)
  {
    boolean bool = false;
    if ((paramShort == 5100) || (paramShort == 5200) || (paramShort == 13100) || (paramShort == 13310)) {
      bool = true;
    }
    return bool;
  }
  
  private boolean b(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "screen_brightness_mode", i);
        return false;
      }
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private byte[] b(short paramShort)
  {
    if (paramShort == 5100) {
      return a(1, (short)5100);
    }
    if (paramShort == 5200) {
      return a(2, (short)5200);
    }
    if (paramShort == 13100) {
      return f();
    }
    return null;
  }
  
  private static float c()
  {
    try
    {
      Class localClass = Class.forName("android.view.IWindowManager");
      Object localObject = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("window") });
      if (localObject != null)
      {
        float f = ((Float)localClass.getMethod("getAnimationScale", new Class[] { Integer.TYPE }).invoke(localObject, new Object[] { Integer.valueOf(1) })).floatValue();
        return f;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1.0F;
  }
  
  private int c()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "screen_brightness");
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  private String c()
  {
    Iterator localIterator = ((ActivityManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return "";
      }
      localIterator.next();
    }
  }
  
  private void c()
  {
    vf.c("QuickSetting4", "sendSelectedLang start");
    byte[] arrayOfByte = f();
    if ((arrayOfByte != null) && (qq.b)) {
      qq.a().a.a(231, 216, arrayOfByte, arrayOfByte.length);
    }
  }
  
  private static void c(float paramFloat)
  {
    try
    {
      Class localClass = Class.forName("android.view.IWindowManager");
      Object localObject = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("window") });
      if (localObject != null) {
        localClass.getMethod("setAnimationScale", new Class[] { Integer.TYPE, Float.TYPE }).invoke(localObject, new Object[] { Integer.valueOf(1), Float.valueOf(paramFloat) });
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void c(int paramInt)
  {
    try
    {
      Settings.System.putInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "screen_off_timeout", paramInt);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static void c(String paramString)
  {
    vf.b("doring", "received loc : " + paramString);
    Locale.getAvailableLocales();
    String[] arrayOfString = Resources.getSystem().getAssets().getLocales();
    int j = arrayOfString.length;
    Arrays.sort(arrayOfString);
    new StringBuffer();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        localObject1 = null;
      }
      do
      {
        if (localObject1 != null) {
          break label130;
        }
        return;
        localObject1 = arrayOfString[i];
        if (((String)localObject1).length() != 5) {
          break;
        }
        localObject2 = new Locale(((String)localObject1).substring(0, 2), ((String)localObject1).substring(3, 5));
        localObject1 = localObject2;
      } while (a(((Locale)localObject2).getDisplayName((Locale)localObject2)).equals(paramString));
      i += 1;
    }
    label130:
    paramString = cn.a;
    paramString = cn.a();
    Object localObject2 = cn.a;
    cn.a(paramString, (Locale)localObject1);
    Object localObject1 = cn.a;
    cn.a(paramString);
  }
  
  private void c(boolean paramBoolean)
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("connectivity");
      Method localMethod = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(localConnectivityManager, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static boolean c()
  {
    return BluetoothAdapter.getDefaultAdapter().isEnabled();
  }
  
  private static byte[] c()
  {
    return null;
  }
  
  private byte[] c(short paramShort)
  {
    if (paramShort == 5101) {
      return g();
    }
    if (paramShort == 5201) {
      return h();
    }
    if (paramShort == 13101) {
      return d();
    }
    if (paramShort == 13311) {
      return e();
    }
    return null;
  }
  
  private int d()
  {
    int i = e();
    if ((i > 0) && (i <= 15000)) {}
    do
    {
      return 0;
      if (i <= 30000) {
        return 1;
      }
      if (i <= 60000) {
        return 2;
      }
      if (i <= 120000) {
        return 3;
      }
      if (i <= 300000) {
        return 4;
      }
    } while (i > 600000);
    return 5;
  }
  
  private String d()
  {
    String str1 = "";
    String str2 = str1;
    try
    {
      List localList = this.jdField_a_of_type_AndroidContentContext.getPackageManager().getInstalledApplications(0);
      int i = 0;
      str2 = str1;
      if (i >= localList.size()) {
        return str1;
      }
      str2 = str1;
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
      str2 = str1;
      if (localApplicationInfo.sourceDir.contains("/data/"))
      {
        str2 = str1;
        str1 = localApplicationInfo.packageName + ";";
      }
      for (;;)
      {
        i += 1;
        break;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return str2;
    }
  }
  
  private static void d()
  {
    BluetoothAdapter.getDefaultAdapter().startDiscovery();
  }
  
  private static void d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      a(0.85F);
      return;
    case 1: 
      a(1.0F);
      return;
    case 2: 
      a(1.15F);
      return;
    }
    a(1.3F);
  }
  
  private void d(String paramString)
  {
    System.out.println("---------------------------------- setDateFormat : " + paramString);
    Settings.System.putString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "date_format", paramString);
  }
  
  private void d(boolean paramBoolean)
  {
    ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      Settings.System.putInt(localContentResolver, "airplane_mode_on", i);
      return;
    }
  }
  
  /* Error */
  private boolean d()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 62	oi:jdField_a_of_type_AndroidContentContext	Landroid/content/Context;
    //   4: ldc 87
    //   6: invokevirtual 70	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 89	android/net/ConnectivityManager
    //   12: astore_2
    //   13: ldc 89
    //   15: ldc_w 830
    //   18: iconst_0
    //   19: anewarray 428	java/lang/Class
    //   22: invokevirtual 618	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   25: astore_3
    //   26: aload_3
    //   27: iconst_1
    //   28: invokevirtual 621	java/lang/reflect/Method:setAccessible	(Z)V
    //   31: aload_3
    //   32: aload_2
    //   33: iconst_0
    //   34: anewarray 4	java/lang/Object
    //   37: invokevirtual 444	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   40: checkcast 611	java/lang/Boolean
    //   43: invokevirtual 833	java/lang/Boolean:booleanValue	()Z
    //   46: istore_1
    //   47: ldc 30
    //   49: new 399	java/lang/StringBuilder
    //   52: dup
    //   53: ldc_w 835
    //   56: invokespecial 402	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   59: iload_1
    //   60: invokevirtual 838	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   63: invokevirtual 407	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokestatic 580	vf:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   69: iload_1
    //   70: ireturn
    //   71: astore_2
    //   72: iconst_0
    //   73: istore_1
    //   74: aload_2
    //   75: invokevirtual 145	java/lang/Exception:printStackTrace	()V
    //   78: iload_1
    //   79: ireturn
    //   80: astore_2
    //   81: goto -7 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	oi
    //   46	33	1	bool	boolean
    //   12	21	2	localConnectivityManager	ConnectivityManager
    //   71	4	2	localException1	Exception
    //   80	1	2	localException2	Exception
    //   25	7	3	localMethod	Method
    // Exception table:
    //   from	to	target	type
    //   0	47	71	java/lang/Exception
    //   47	69	80	java/lang/Exception
  }
  
  private static byte[] d()
  {
    Object localObject3 = Resources.getSystem().getAssets().getLocales();
    int j = localObject3.length;
    Arrays.sort((Object[])localObject3);
    Object localObject1 = "";
    new StringBuffer();
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        localObject2 = localObject1;
        if (((String)localObject1).substring(((String)localObject1).length() - 1).equals(",")) {
          localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
        }
      }
      try
      {
        localObject1 = ((String)localObject2).getBytes("UTF-16LE");
        localObject2 = new byte[localObject1.length + 2 + 2];
        localObject3 = ve.a((short)13101);
        System.arraycopy(localObject3, 0, localObject2, 0, localObject3.length);
        System.arraycopy(localObject1, 0, localObject2, 2, localObject1.length);
        return localObject2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Object localObject4;
        localUnsupportedEncodingException.printStackTrace();
      }
      localObject4 = localObject3[i];
      Object localObject2 = localObject1;
      if (localObject4.length() == 5)
      {
        localObject2 = new Locale(localObject4.substring(0, 2), localObject4.substring(3, 5));
        localObject2 = localObject1 + a(((Locale)localObject2).getDisplayName((Locale)localObject2)) + ",";
      }
      i += 1;
      localObject1 = localObject2;
    }
    return null;
  }
  
  private int e()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "screen_off_timeout", 0);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  private String e()
  {
    String str1 = "";
    str2 = str1;
    try
    {
      List localList = this.jdField_a_of_type_AndroidContentContext.getPackageManager().getInstalledApplications(0);
      int i = 0;
      for (;;)
      {
        str2 = str1;
        if (i >= localList.size()) {
          return str1;
        }
        str2 = str1;
        str1 = ((ApplicationInfo)localList.get(i)).packageName + ";";
        i += 1;
      }
      return str2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void e()
  {
    Intent localIntent = new Intent("android.settings.ADD_ACCOUNT_SETTINGS");
    localIntent.putExtra("authorities", ((Activity)this.jdField_a_of_type_AndroidContentContext).getIntent().getStringArrayExtra("authorities"));
    this.jdField_a_of_type_AndroidContentContext.startActivity(localIntent);
  }
  
  private void e(int paramInt)
  {
    String str = null;
    if (paramInt == 0) {
      str = "MM-dd-yyyy";
    }
    for (;;)
    {
      if (str != null)
      {
        System.out.println("---------------------------------- setDateFormat : " + str);
        Settings.System.putString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "date_format", str);
      }
      return;
      if (paramInt == 1) {
        str = "dd-MM-yyyy";
      } else if (paramInt == 2) {
        str = "yyyy-MM-dd";
      }
    }
  }
  
  private static void e(boolean paramBoolean)
  {
    try
    {
      Class localClass = Class.forName("android.bluetooth.IBluetooth");
      Object localObject = Class.forName("android.bluetooth.IBluetooth$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("bluetooth") });
      if (localObject != null) {
        localClass.getMethod("setBluetoothTethering", new Class[] { Boolean.TYPE }).invoke(localObject, new Object[] { Boolean.valueOf(paramBoolean) });
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean e()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "airplane_mode_on");
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static byte[] e()
  {
    Object localObject3 = Resources.getSystem().getAssets().getLocales();
    int j = localObject3.length;
    Arrays.sort((Object[])localObject3);
    Object localObject1 = "";
    new StringBuffer();
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        localObject2 = localObject1;
        if (((String)localObject1).substring(((String)localObject1).length() - 1).equals(",")) {
          localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
        }
      }
      try
      {
        localObject1 = ((String)localObject2).getBytes("UTF-16LE");
        localObject2 = new byte[localObject1.length + 2];
        localObject3 = ve.a((short)13311);
        System.arraycopy(localObject3, 0, localObject2, 0, localObject3.length);
        System.arraycopy(localObject1, 0, localObject2, 2, localObject1.length);
        return localObject2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Object localObject4;
        localUnsupportedEncodingException.printStackTrace();
      }
      localObject4 = localObject3[i];
      Object localObject2 = localObject1;
      if (localObject4.length() == 5)
      {
        localObject2 = new Locale(localObject4.substring(0, 2), localObject4.substring(3, 5));
        localObject2 = localObject1 + a(((Locale)localObject2).getDisplayName((Locale)localObject2)) + ",";
      }
      i += 1;
      localObject1 = localObject2;
    }
    return null;
  }
  
  private static int f()
  {
    float f = a();
    if (f == 0.85F) {}
    do
    {
      return 0;
      if (f == 1.0F) {
        return 1;
      }
      if (f == 1.15F) {
        return 2;
      }
    } while (f != 1.3F);
    return 3;
  }
  
  private String f()
  {
    return Settings.System.getString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "date_format");
  }
  
  private static void f(int paramInt)
  {
    if (paramInt == 0) {
      b(0.0F);
    }
    do
    {
      return;
      if (paramInt == 1)
      {
        b(0.5F);
        return;
      }
      if (paramInt == 2)
      {
        b(1.0F);
        return;
      }
      if (paramInt == 3)
      {
        b(1.5F);
        return;
      }
      if (paramInt == 4)
      {
        b(2.0F);
        return;
      }
      if (paramInt == 5)
      {
        b(5.0F);
        return;
      }
    } while (paramInt != 6);
    b(10.0F);
  }
  
  private void f(boolean paramBoolean)
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("connectivity");
      Method localMethod = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(localConnectivityManager, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean f()
  {
    int k;
    int i;
    try
    {
      localObject2 = (ConnectivityManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("connectivity");
      localObject1 = ConnectivityManager.class.getDeclaredMethod("getTetherableUsbRegexs", new Class[0]);
      ((Method)localObject1).setAccessible(true);
      localObject1 = (String[])((Method)localObject1).invoke(localObject2, new Object[0]);
      if (localObject1 == null) {
        return false;
      }
      localMethod = ConnectivityManager.class.getDeclaredMethod("getTetheredIfaces", new Class[0]);
      localMethod.setAccessible(true);
      localObject2 = (String[])localMethod.invoke(localObject2, new Object[0]);
      k = localObject2.length;
      i = 0;
    }
    catch (Exception localException)
    {
      Object localObject2;
      Object localObject1;
      Method localMethod;
      boolean bool;
      localException.printStackTrace();
      break label158;
    }
    localMethod = localObject2[i];
    int m = localObject1.length;
    int j = 0;
    break label160;
    label118:
    bool = localMethod.matches(localObject1[j]);
    if (bool) {
      return true;
    }
    j += 1;
    while (i >= k)
    {
      label158:
      return false;
      label160:
      if (j < m) {
        break label118;
      }
      i += 1;
    }
  }
  
  /* Error */
  private byte[] f()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 62	oi:jdField_a_of_type_AndroidContentContext	Landroid/content/Context;
    //   4: invokevirtual 880	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: invokevirtual 882	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   10: astore_1
    //   11: aload_1
    //   12: getfield 886	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   15: aload_1
    //   16: getfield 886	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   19: invokevirtual 761	java/util/Locale:getDisplayName	(Ljava/util/Locale;)Ljava/lang/String;
    //   22: invokestatic 763	oi:a	(Ljava/lang/String;)Ljava/lang/String;
    //   25: ldc_w 627
    //   28: invokevirtual 631	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   31: astore_2
    //   32: aload_2
    //   33: arraylength
    //   34: iconst_2
    //   35: iadd
    //   36: iconst_2
    //   37: iadd
    //   38: newarray byte
    //   40: astore_1
    //   41: sipush 13100
    //   44: invokestatic 636	ve:a	(S)[B
    //   47: astore_3
    //   48: aload_3
    //   49: iconst_0
    //   50: aload_1
    //   51: iconst_0
    //   52: aload_3
    //   53: arraylength
    //   54: invokestatic 640	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   57: aload_2
    //   58: iconst_0
    //   59: aload_1
    //   60: iconst_2
    //   61: aload_2
    //   62: arraylength
    //   63: invokestatic 640	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   66: aload_1
    //   67: areturn
    //   68: astore_2
    //   69: aconst_null
    //   70: astore_1
    //   71: aload_2
    //   72: invokevirtual 145	java/lang/Exception:printStackTrace	()V
    //   75: aload_1
    //   76: areturn
    //   77: astore_2
    //   78: goto -7 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	this	oi
    //   10	66	1	localObject	Object
    //   31	31	2	arrayOfByte1	byte[]
    //   68	4	2	localException1	Exception
    //   77	1	2	localException2	Exception
    //   47	6	3	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   0	41	68	java/lang/Exception
    //   41	66	77	java/lang/Exception
  }
  
  private int g()
  {
    String str = Settings.System.getString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "date_format");
    if (str.equals("MM-dd-yyyy")) {}
    do
    {
      return 0;
      if (str.equals("dd-MM-yyyy")) {
        return 1;
      }
    } while (!str.equals("yyyy-MM-dd"));
    return 2;
  }
  
  private static String g()
  {
    try
    {
      Object localObject = cx.a;
      localObject = cx.a("persist.sys.hdcp_checking");
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  private static void g(int paramInt)
  {
    if (paramInt == 0) {
      c(0.0F);
    }
    do
    {
      return;
      if (paramInt == 1)
      {
        c(0.5F);
        return;
      }
      if (paramInt == 2)
      {
        c(1.0F);
        return;
      }
      if (paramInt == 3)
      {
        c(1.5F);
        return;
      }
      if (paramInt == 4)
      {
        c(2.0F);
        return;
      }
      if (paramInt == 5)
      {
        c(5.0F);
        return;
      }
    } while (paramInt != 6);
    c(10.0F);
  }
  
  private void g(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "dtmf_tone", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean g()
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("wifi");
      Method localMethod = WifiManager.class.getDeclaredMethod("isWifiApEnabled", new Class[0]);
      localMethod.setAccessible(true);
      boolean bool = ((Boolean)localMethod.invoke(localWifiManager, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private byte[] g()
  {
    Object localObject1 = "";
    try
    {
      Object localObject2 = new RingtoneManager(this.jdField_a_of_type_AndroidContentContext);
      ((RingtoneManager)localObject2).setType(1);
      Object localObject3 = ((RingtoneManager)localObject2).getCursor();
      ((Cursor)localObject3).moveToFirst();
      do
      {
        ((Cursor)localObject3).getString(0);
        localObject2 = ((Cursor)localObject3).getString(1);
        ((Cursor)localObject3).getString(2);
        localObject2 = localObject1 + (String)localObject2 + ",";
        localObject1 = localObject2;
      } while (((Cursor)localObject3).moveToNext());
      localObject1 = localObject2;
      if (((String)localObject2).substring(((String)localObject2).length() - 1).equals(",")) {
        localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
      }
      ((Cursor)localObject3).close();
      localObject1 = ((String)localObject1).getBytes("UTF-16LE");
      localObject2 = new byte[localObject1.length + 2 + 2];
      localObject3 = ve.a((short)5101);
      System.arraycopy(localObject3, 0, localObject2, 0, localObject3.length);
      System.arraycopy(localObject1, 0, localObject2, 2, localObject1.length);
      return localObject2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }
  
  private static int h()
  {
    float f = b();
    if (f == 0.0F) {}
    do
    {
      do
      {
        return 0;
      } while (f < 0.0F);
      if (f <= 0.5F) {
        return 1;
      }
      if (f <= 1.0F) {
        return 2;
      }
      if (f <= 1.5F) {
        return 3;
      }
      if (f <= 2.0F) {
        return 4;
      }
      if (f <= 5.0F) {
        return 5;
      }
    } while (f > 10.0F);
    return 6;
  }
  
  private static void h(int paramInt)
  {
    if (paramInt == 0) {
      i(-1);
    }
    do
    {
      return;
      if (paramInt == 1)
      {
        i(0);
        return;
      }
      if (paramInt == 2)
      {
        i(1);
        return;
      }
      if (paramInt == 3)
      {
        i(2);
        return;
      }
      if (paramInt == 4)
      {
        i(3);
        return;
      }
    } while (paramInt != 5);
    i(4);
  }
  
  private void h(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "sound_effects_enabled", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static boolean h()
  {
    try
    {
      Class localClass = Class.forName("android.bluetooth.IBluetooth");
      Object localObject = Class.forName("android.bluetooth.IBluetooth$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("bluetooth") });
      if (localObject != null)
      {
        boolean bool = ((Boolean)localClass.getMethod("isTetheringOn", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
        return bool;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private byte[] h()
  {
    Object localObject1 = "";
    try
    {
      Object localObject2 = new RingtoneManager(this.jdField_a_of_type_AndroidContentContext);
      ((RingtoneManager)localObject2).setType(2);
      Object localObject3 = ((RingtoneManager)localObject2).getCursor();
      ((Cursor)localObject3).moveToFirst();
      do
      {
        ((Cursor)localObject3).getString(0);
        localObject2 = ((Cursor)localObject3).getString(1);
        ((Cursor)localObject3).getString(2);
        localObject2 = localObject1 + (String)localObject2 + ",";
        localObject1 = localObject2;
      } while (((Cursor)localObject3).moveToNext());
      localObject1 = localObject2;
      if (((String)localObject2).substring(((String)localObject2).length() - 1).equals(",")) {
        localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
      }
      ((Cursor)localObject3).close();
      localObject1 = ((String)localObject1).getBytes("UTF-16LE");
      localObject2 = new byte[localObject1.length + 2 + 2];
      localObject3 = ve.a((short)5201);
      System.arraycopy(localObject3, 0, localObject2, 0, localObject3.length);
      System.arraycopy(localObject1, 0, localObject2, 2, localObject1.length);
      return localObject2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }
  
  private static int i()
  {
    float f = c();
    if (f == 0.0F) {}
    do
    {
      do
      {
        return 0;
      } while (f < 0.0F);
      if (f <= 0.5F) {
        return 1;
      }
      if (f <= 1.0F) {
        return 2;
      }
      if (f <= 1.5F) {
        return 3;
      }
      if (f <= 2.0F) {
        return 4;
      }
      if (f <= 5.0F) {
        return 5;
      }
    } while (f > 10.0F);
    return 6;
  }
  
  private static void i(int paramInt)
  {
    try
    {
      cn localCn = cn.a;
      cn.a(paramInt);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void i(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "lockscreen_sounds_enabled", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean i()
  {
    try
    {
      Class localClass = Class.forName("android.nfc.NfcAdapter");
      Object localObject = localClass.getMethod("getDefaultAdapter", new Class[] { Context.class }).invoke(null, new Object[] { this.jdField_a_of_type_AndroidContentContext });
      boolean bool = ((Boolean)localClass.getMethod("isEnabled", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static int j()
  {
    int i = k();
    if (i == -1) {}
    do
    {
      do
      {
        return 0;
      } while (i < -1);
      if (i == 0) {
        return 1;
      }
      if (i == 1) {
        return 2;
      }
      if (i == 2) {
        return 3;
      }
      if (i == 3) {
        return 4;
      }
    } while (i != 4);
    return 5;
  }
  
  private void j(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "haptic_feedback_enabled", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean j()
  {
    if (!i()) {
      return false;
    }
    try
    {
      Class localClass = Class.forName("android.nfc.NfcAdapter");
      Object localObject = localClass.getMethod("getDefaultAdapter", new Class[] { Context.class }).invoke(null, new Object[] { this.jdField_a_of_type_AndroidContentContext });
      bool = ((Boolean)localClass.getMethod("isNdefPushEnabled", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        boolean bool = false;
      }
    }
  }
  
  private static int k()
  {
    try
    {
      cn localCn = cn.a;
      int i = cn.a();
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  private void k(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "accelerometer_rotation", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean k()
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("connectivity");
      Method localMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
      localMethod.setAccessible(true);
      boolean bool = ((Boolean)localMethod.invoke(localConnectivityManager, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static int l()
  {
    int j = 1;
    String str = g();
    System.out.println("hdcp 정보 : " + str);
    int i;
    if (str.contains("never")) {
      i = 0;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (str.equals("only"));
      i = j;
    } while (!str.equals("always"));
    return 2;
  }
  
  private static void l(boolean paramBoolean)
  {
    ContentResolver.setMasterSyncAutomatically(paramBoolean);
  }
  
  private boolean l()
  {
    boolean bool = false;
    try
    {
      int i = Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "data_roaming");
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private void m(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "show_password", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean m()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "dtmf_tone", 1);
      return i != 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private void n(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "auto_time", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean n()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "sound_effects_enabled", 1);
      return i != 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private void o(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (String str = "24";; str = "12")
      {
        Settings.System.putString(localContentResolver, "time_12_24", str);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean o()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "lockscreen_sounds_enabled", 1);
      return i != 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private void p(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 3;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "stay_on_while_plugged_in", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean p()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "haptic_feedback_enabled", 1);
      return i != 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static void q(boolean paramBoolean)
  {
    try
    {
      Object localObject1 = Class.forName("android.view.IWindowManager");
      Object localObject2 = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("window") });
      if (localObject2 != null)
      {
        Method localMethod = ((Class)localObject1).getMethod("setStrictModeVisualIndicatorPreference", new Class[] { String.class });
        if (paramBoolean) {}
        for (localObject1 = "1";; localObject1 = "0")
        {
          localMethod.invoke(localObject2, new Object[] { localObject1 });
          return;
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean q()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "screen_brightness_mode", 1);
      return i != 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private void r(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "pointer_location", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean r()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "accelerometer_rotation", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private void s(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "show_touches", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static boolean s()
  {
    return ContentResolver.getMasterSyncAutomatically();
  }
  
  private void t(boolean paramBoolean)
  {
    try
    {
      ContentResolver localContentResolver = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        Settings.System.putInt(localContentResolver, "show_processes", i);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean t()
  {
    try
    {
      boolean bool = Settings.Secure.isLocationProviderEnabled(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "network");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static void u(boolean paramBoolean)
  {
    try
    {
      Object localObject = cx.a;
      if (paramBoolean) {}
      for (localObject = "true";; localObject = "false")
      {
        cx.a("persist.sys.ui.hw", (String)localObject);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean u()
  {
    try
    {
      boolean bool = Settings.Secure.isLocationProviderEnabled(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "gps");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static void v(boolean paramBoolean)
  {
    try
    {
      cn localCn = cn.a;
      cn.a(paramBoolean);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean v()
  {
    try
    {
      int i = Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "assisted_gps_enabled", 2);
      return i == 1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean w()
  {
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "show_password", 1);
      return i != 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean x()
  {
    boolean bool = false;
    if (Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "install_non_market_apps", 0) > 0) {
      bool = true;
    }
    return bool;
  }
  
  private boolean y()
  {
    try
    {
      Object localObject = Class.forName("android.content.Context").getMethod("getSystemService", new Class[] { String.class }).invoke(this.jdField_a_of_type_AndroidContentContext, new Object[] { "textservices" });
      boolean bool = ((Boolean)Class.forName("android.view.textservice.TextServicesManager").getMethod("isSpellCheckerEnabled", new Class[0]).invoke(localObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private boolean z()
  {
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "auto_time");
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public final pj a(Context paramContext)
  {
    if (jdField_a_of_type_Pj == null) {
      jdField_a_of_type_Pj = new of(paramContext);
    }
    return jdField_a_of_type_Pj;
  }
  
  public final void a()
  {
    try
    {
      this.jdField_a_of_type_AndroidContentContext.unregisterReceiver(this.jdField_a_of_type_AndroidContentBroadcastReceiver);
      this.jdField_a_of_type_AndroidContentContext.unregisterReceiver(this.b);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final void a(String paramString) {}
  
  public final void a(short paramShort)
  {
    vf.c("QuickSetting4", "openMenu : " + paramShort);
    if (this.jdField_a_of_type_AndroidContentContext == null) {
      return;
    }
    switch (paramShort)
    {
    default: 
      a("com.android.settings", "com.android.settings.Settings");
      return;
    case 100: 
      a("android.settings.SETTINGS", null, null);
      return;
    case 1000: 
      a("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
      return;
    case 1200: 
      a("com.android.settings", "com.android.settings.Settings$AdvancedWifiSettingsActivity");
      return;
    case 2000: 
      a("com.android.settings", "com.android.settings.Settings$BluetoothSettingsActivity");
      return;
    case 2200: 
      a("com.android.settings", "com.android.settings.Settings$BluetoothSettingsActivity");
      BluetoothAdapter.getDefaultAdapter().startDiscovery();
      return;
    case 3000: 
      a("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity");
      return;
    case 4000: 
      a("com.android.settings", "com.android.settings.Settings$WirelessSettingsActivity");
      return;
    case 4400: 
      a("com.android.settings", "com.android.settings.Settings$AndroidBeamSettingsActivity");
      return;
    case 4200: 
      a("com.android.settings", "com.android.settings.Settings$TetherSettingsActivity");
      return;
    case 4500: 
      a("com.android.phone", "com.android.phone.Settings");
      return;
    case 5000: 
      a("com.android.settings", "com.android.settings.Settings$SoundSettingsActivity");
      return;
    case 6000: 
      a("com.android.settings", "com.android.settings.Settings$DisplaySettingsActivity");
      return;
    case 7000: 
      a("com.android.settings", "com.android.settings.Settings$StorageSettingsActivity");
      return;
    case 8000: 
      a("com.android.settings", "com.android.settings.Settings$PowerUsageSummaryActivity");
      return;
    case 9000: 
      a("com.android.settings", "com.android.settings.Settings$ManageApplicationsActivity");
      return;
    case 10000: 
      a("android.settings.ADD_ACCOUNT_SETTINGS", null, null);
      return;
    case 11000: 
      a("com.android.settings", "com.android.settings.Settings$LocationSettingsActivity");
      return;
    case 12000: 
      a("com.android.settings", "com.android.settings.Settings$SecuritySettingsActivity");
      return;
    case 13000: 
      a("com.android.settings", "com.android.settings.Settings$InputMethodAndLanguageSettingsActivity");
      return;
    case 14000: 
      a("com.android.settings", "com.android.settings.Settings$PrivacySettingsActivity");
      return;
    case 15000: 
      a("com.android.settings", "com.android.settings.Settings$DateTimeSettingsActivity");
      return;
    case 16000: 
      a("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");
      return;
    case 17000: 
      a("com.android.settings", "com.android.settings.Settings$DevelopmentSettingsActivity");
      return;
    }
    a("com.android.settings", "com.android.settings.Settings$DeviceInfoSettingsActivity");
  }
  
  public final void a(short paramShort, String paramString)
  {
    vf.c("QuickSetting4", "setSettingListValue : " + paramShort + " : value : " + paramString);
    switch (paramShort)
    {
    default: 
      return;
    case 5100: 
      a(1, paramString);
      return;
    case 5200: 
      a(2, paramString);
      return;
    }
    c(paramString);
  }
  
  public final boolean a(short paramShort, byte paramByte)
  {
    short s1 = 1;
    boolean bool1 = true;
    boolean bool2 = true;
    short s2 = 1;
    short s3 = 1;
    short s4 = 1;
    short s5 = 1;
    boolean bool3 = true;
    short s6 = 1;
    boolean bool4 = true;
    short s7 = 1;
    short s8 = 1;
    short s13 = 1;
    short s14 = 1;
    short s15 = 1;
    short s9 = 1;
    short s10 = 1;
    short s11 = 1;
    boolean bool5 = true;
    short s12 = 1;
    vf.c("QuickSetting4", "setSetting : " + paramShort + " : value : " + paramByte);
    Object localObject7;
    label784:
    label1049:
    label1102:
    label1155:
    label1208:
    label1281:
    label1494:
    label1547:
    Object localObject3;
    switch (paramShort)
    {
    case 1210: 
    case 2210: 
    case 4200: 
    case 4210: 
    case 4300: 
    case 4400: 
    case 4520: 
    case 5100: 
    case 5101: 
    case 5200: 
    case 5201: 
    case 6100: 
    case 7000: 
    case 8000: 
    case 9000: 
    case 11100: 
    case 11200: 
    case 11300: 
    case 12200: 
    case 13100: 
    case 13101: 
    case 13200: 
    case 13310: 
    case 13311: 
    case 14000: 
    case 15301: 
    case 16100: 
    case 17100: 
    case 17300: 
    case 17900: 
    default: 
    case 1100: 
    case 2100: 
    case 2200: 
    case 3100: 
    case 4100: 
    case 4220: 
    case 4230: 
    case 4510: 
    case 5300: 
    case 5400: 
    case 5500: 
    case 5600: 
    case 6110: 
    case 6200: 
    case 6300: 
      do
      {
        for (;;)
        {
          return false;
          if (paramByte == 0) {}
          for (bool1 = false;; bool1 = true)
          {
            ((WifiManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("wifi")).setWifiEnabled(bool1);
            return false;
          }
          paramShort = s12;
          if (paramByte == 0) {
            paramShort = 0;
          }
          Object localObject1 = BluetoothAdapter.getDefaultAdapter();
          if (paramShort != 0)
          {
            ((BluetoothAdapter)localObject1).enable();
            return false;
          }
          ((BluetoothAdapter)localObject1).disable();
          return false;
          BluetoothAdapter.getDefaultAdapter().startDiscovery();
          return false;
          if (paramByte == 0) {}
          for (bool1 = false;; bool1 = true) {
            try
            {
              localObject1 = (ConnectivityManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("connectivity");
              localObject7 = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE });
              ((Method)localObject7).setAccessible(true);
              ((Method)localObject7).invoke(localObject1, new Object[] { Boolean.valueOf(bool1) });
              return false;
            }
            catch (Exception localException1)
            {
              localException1.printStackTrace();
              return false;
            }
          }
          Object localObject2;
          if (paramByte == 0)
          {
            paramShort = 0;
            localObject2 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
            if (paramShort == 0) {
              break label784;
            }
          }
          for (paramShort = s1;; paramShort = 0)
          {
            Settings.System.putInt((ContentResolver)localObject2, "airplane_mode_on", paramShort);
            return false;
            paramShort = 1;
            break;
          }
          if (paramByte == 0) {
            bool1 = false;
          }
          a(bool1);
          return false;
          bool1 = bool2;
          if (paramByte == 0) {
            bool1 = false;
          }
          try
          {
            localObject2 = Class.forName("android.bluetooth.IBluetooth");
            localObject7 = Class.forName("android.bluetooth.IBluetooth$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("bluetooth") });
            if (localObject7 != null)
            {
              ((Class)localObject2).getMethod("setBluetoothTethering", new Class[] { Boolean.TYPE }).invoke(localObject7, new Object[] { Boolean.valueOf(bool1) });
              return false;
            }
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
            return false;
          }
        }
        if (paramByte == 0) {}
        for (bool1 = false;; bool1 = true) {
          try
          {
            ConnectivityManager localConnectivityManager = (ConnectivityManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("connectivity");
            localObject7 = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE });
            ((Method)localObject7).setAccessible(true);
            ((Method)localObject7).invoke(localConnectivityManager, new Object[] { Boolean.valueOf(bool1) });
            return false;
          }
          catch (Exception localException3)
          {
            localException3.printStackTrace();
            return false;
          }
        }
        if (paramByte == 0) {
          paramShort = 0;
        }
        for (;;)
        {
          try
          {
            ContentResolver localContentResolver1 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
            if (paramShort == 0) {
              break label1049;
            }
            paramShort = s2;
            Settings.System.putInt(localContentResolver1, "dtmf_tone", paramShort);
            return false;
          }
          catch (Exception localException4)
          {
            localException4.printStackTrace();
            return false;
          }
          paramShort = 1;
          continue;
          paramShort = 0;
        }
        if (paramByte == 0) {
          paramShort = 0;
        }
        for (;;)
        {
          try
          {
            ContentResolver localContentResolver2 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
            if (paramShort == 0) {
              break label1102;
            }
            paramShort = s3;
            Settings.System.putInt(localContentResolver2, "sound_effects_enabled", paramShort);
            return false;
          }
          catch (Exception localException5)
          {
            localException5.printStackTrace();
            return false;
          }
          paramShort = 1;
          continue;
          paramShort = 0;
        }
        if (paramByte == 0) {
          paramShort = 0;
        }
        for (;;)
        {
          try
          {
            ContentResolver localContentResolver3 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
            if (paramShort == 0) {
              break label1155;
            }
            paramShort = s4;
            Settings.System.putInt(localContentResolver3, "lockscreen_sounds_enabled", paramShort);
            return false;
          }
          catch (Exception localException6)
          {
            localException6.printStackTrace();
            return false;
          }
          paramShort = 1;
          continue;
          paramShort = 0;
        }
        if (paramByte == 0) {
          paramShort = 0;
        }
        for (;;)
        {
          try
          {
            ContentResolver localContentResolver4 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
            if (paramShort == 0) {
              break label1208;
            }
            paramShort = s5;
            Settings.System.putInt(localContentResolver4, "haptic_feedback_enabled", paramShort);
            return false;
          }
          catch (Exception localException7)
          {
            localException7.printStackTrace();
            return false;
          }
          paramShort = 1;
          continue;
          paramShort = 0;
        }
        bool1 = bool3;
        if (paramByte == 0) {
          bool1 = false;
        }
        b(bool1);
        return false;
        if (paramByte == 0) {
          paramShort = 0;
        }
        for (;;)
        {
          try
          {
            ContentResolver localContentResolver5 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
            if (paramShort == 0) {
              break label1281;
            }
            paramShort = s6;
            Settings.System.putInt(localContentResolver5, "accelerometer_rotation", paramShort);
            return false;
          }
          catch (Exception localException8)
          {
            localException8.printStackTrace();
            return false;
          }
          paramShort = 1;
          continue;
          paramShort = 0;
        }
        if (paramByte == 0)
        {
          c(15000);
          return false;
        }
        if (paramByte <= 1)
        {
          c(30000);
          return false;
        }
        if (paramByte <= 2)
        {
          c(60000);
          return false;
        }
        if (paramByte <= 3)
        {
          c(120000);
          return false;
        }
        if (paramByte <= 4)
        {
          c(300000);
          return false;
        }
      } while (paramByte > 5);
      c(600000);
      return false;
    case 6400: 
      switch (paramByte)
      {
      default: 
        return false;
      case 0: 
        a(0.85F);
        return false;
      case 1: 
        a(1.0F);
        return false;
      case 2: 
        a(1.15F);
        return false;
      }
      a(1.3F);
      return false;
    case 10100: 
      bool1 = bool4;
      if (paramByte == 0) {
        bool1 = false;
      }
      ContentResolver.setMasterSyncAutomatically(bool1);
      return false;
    case 12100: 
      if (paramByte == 0) {
        paramShort = 0;
      }
      for (;;)
      {
        try
        {
          ContentResolver localContentResolver6 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
          if (paramShort == 0) {
            break label1494;
          }
          paramShort = s7;
          Settings.System.putInt(localContentResolver6, "show_password", paramShort);
          return false;
        }
        catch (Exception localException9)
        {
          localException9.printStackTrace();
          return false;
        }
        paramShort = 1;
        continue;
        paramShort = 0;
      }
    case 15100: 
      if (paramByte == 0) {
        paramShort = 0;
      }
      for (;;)
      {
        try
        {
          ContentResolver localContentResolver7 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
          if (paramShort == 0) {
            break label1547;
          }
          paramShort = s8;
          Settings.System.putInt(localContentResolver7, "auto_time", paramShort);
          return false;
        }
        catch (Exception localException10)
        {
          localException10.printStackTrace();
          return false;
        }
        paramShort = 1;
        continue;
        paramShort = 0;
      }
    case 15200: 
      paramShort = s13;
      if (paramByte == 0) {
        paramShort = 0;
      }
      for (;;)
      {
        try
        {
          localObject7 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
          if (paramShort != 0)
          {
            String str = "24";
            Settings.System.putString((ContentResolver)localObject7, "time_12_24", str);
            return false;
          }
        }
        catch (Exception localException11)
        {
          localException11.printStackTrace();
          return false;
        }
        localObject3 = "12";
      }
    case 15300: 
      if (paramByte == 0) {
        localObject3 = "MM-dd-yyyy";
      }
      break;
    }
    while (localObject3 != null)
    {
      System.out.println("---------------------------------- setDateFormat : " + (String)localObject3);
      Settings.System.putString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "date_format", (String)localObject3);
      return false;
      if (paramByte == 1)
      {
        localObject3 = "dd-MM-yyyy";
      }
      else
      {
        if (paramByte == 2)
        {
          localObject3 = "yyyy-MM-dd";
          continue;
          paramShort = s14;
          if (paramByte == 0) {}
          for (paramShort = 0;; paramShort = 0) {
            try
            {
              localObject3 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
              if (paramShort != 0)
              {
                paramShort = 3;
                Settings.System.putInt((ContentResolver)localObject3, "stay_on_while_plugged_in", paramShort);
                return false;
              }
            }
            catch (Exception localException12)
            {
              localException12.printStackTrace();
              return false;
            }
          }
          paramShort = s15;
          if (paramByte == 0) {
            paramShort = 0;
          }
          Object localObject5;
          for (;;)
          {
            try
            {
              Object localObject4 = Class.forName("android.view.IWindowManager");
              localObject7 = Class.forName("android.view.IWindowManager$Stub").getMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { cv.a("window") });
              if (localObject7 == null) {
                break;
              }
              Method localMethod = ((Class)localObject4).getMethod("setStrictModeVisualIndicatorPreference", new Class[] { String.class });
              if (paramShort != 0)
              {
                localObject4 = "1";
                localMethod.invoke(localObject7, new Object[] { localObject4 });
                return false;
              }
            }
            catch (Exception localException13)
            {
              localException13.printStackTrace();
              return false;
            }
            localObject5 = "0";
          }
          if (paramByte == 0) {
            paramShort = 0;
          }
          for (;;)
          {
            try
            {
              localObject5 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
              if (paramShort == 0) {
                break label1911;
              }
              paramShort = s9;
              Settings.System.putInt((ContentResolver)localObject5, "pointer_location", paramShort);
              return false;
            }
            catch (Exception localException14)
            {
              localException14.printStackTrace();
              return false;
            }
            paramShort = 1;
            continue;
            label1911:
            paramShort = 0;
          }
          if (paramByte == 0) {
            paramShort = 0;
          }
          for (;;)
          {
            try
            {
              ContentResolver localContentResolver8 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
              if (paramShort == 0) {
                break label1963;
              }
              paramShort = s10;
              Settings.System.putInt(localContentResolver8, "show_touches", paramShort);
              return false;
            }
            catch (Exception localException15)
            {
              localException15.printStackTrace();
              return false;
            }
            paramShort = 1;
            continue;
            label1963:
            paramShort = 0;
          }
          if (paramByte == 0) {
            paramShort = 0;
          }
          for (;;)
          {
            try
            {
              ContentResolver localContentResolver9 = this.jdField_a_of_type_AndroidContentContext.getContentResolver();
              if (paramShort == 0) {
                break label2015;
              }
              paramShort = s11;
              Settings.System.putInt(localContentResolver9, "show_processes", paramShort);
              return false;
            }
            catch (Exception localException16)
            {
              localException16.printStackTrace();
              return false;
            }
            paramShort = 1;
            continue;
            label2015:
            paramShort = 0;
          }
          if (paramByte == 0)
          {
            b(0.0F);
            return false;
          }
          if (paramByte == 1)
          {
            b(0.5F);
            return false;
          }
          if (paramByte == 2)
          {
            b(1.0F);
            return false;
          }
          if (paramByte == 3)
          {
            b(1.5F);
            return false;
          }
          if (paramByte == 4)
          {
            b(2.0F);
            return false;
          }
          if (paramByte == 5)
          {
            b(5.0F);
            return false;
          }
          if (paramByte != 6) {
            break;
          }
          b(10.0F);
          return false;
          if (paramByte == 0)
          {
            c(0.0F);
            return false;
          }
          if (paramByte == 1)
          {
            c(0.5F);
            return false;
          }
          if (paramByte == 2)
          {
            c(1.0F);
            return false;
          }
          if (paramByte == 3)
          {
            c(1.5F);
            return false;
          }
          if (paramByte == 4)
          {
            c(2.0F);
            return false;
          }
          if (paramByte == 5)
          {
            c(5.0F);
            return false;
          }
          if (paramByte != 6) {
            break;
          }
          c(10.0F);
          return false;
          bool1 = bool5;
          if (paramByte == 0) {
            bool1 = false;
          }
          try
          {
            cn localCn = cn.a;
            cn.a(bool1);
            return false;
          }
          catch (Exception localException17)
          {
            localException17.printStackTrace();
            return false;
          }
          if (paramByte == 0)
          {
            i(-1);
            return false;
          }
          if (paramByte == 1)
          {
            i(0);
            return false;
          }
          if (paramByte == 2)
          {
            i(1);
            return false;
          }
          if (paramByte == 3)
          {
            i(2);
            return false;
          }
          if (paramByte == 4)
          {
            i(3);
            return false;
          }
          if (paramByte != 5) {
            break;
          }
          i(4);
          return false;
        }
        Object localObject6 = null;
      }
    }
  }
  
  public final byte[] a()
  {
    return null;
  }
  
  public final byte[] a(short paramShort)
  {
    int j = 0;
    if ((paramShort == 5101) || (paramShort == 5201) || (paramShort == 13101) || (paramShort == 13311)) {
      j = 1;
    }
    if (j != 0)
    {
      if (paramShort == 5101) {
        return g();
      }
      if (paramShort == 5201) {
        return h();
      }
      if (paramShort == 13101) {
        return d();
      }
      if (paramShort == 13311) {
        return e();
      }
      return null;
    }
    if (b(paramShort))
    {
      if (paramShort == 5100) {
        return a(1, (short)5100);
      }
      if (paramShort == 5200) {
        return a(2, (short)5200);
      }
      if (paramShort == 13100) {
        return f();
      }
      return null;
    }
    Short[] arrayOfShort = (Short[])this.jdField_a_of_type_JavaUtilHashMap.get(Short.valueOf(paramShort));
    if ((arrayOfShort == null) || (arrayOfShort.length == 0)) {
      return null;
    }
    byte[] arrayOfByte = new byte[arrayOfShort.length * 2 + arrayOfShort.length];
    int n = arrayOfShort.length;
    int m = 0;
    int k = 0;
    if (k >= n) {
      return arrayOfByte;
    }
    paramShort = arrayOfShort[k].shortValue();
    if (b(paramShort))
    {
      localObject1 = null;
      vf.c("QuickSetting4", "sendListValue : " + paramShort);
      switch (paramShort)
      {
      }
      for (;;)
      {
        j = m;
        if (localObject1 != null)
        {
          j = m;
          if (qq.b)
          {
            qq.a().a.a(231, 216, (byte[])localObject1, localObject1.length);
            j = m;
          }
        }
        k += 1;
        m = j;
        break;
        localObject1 = a(1, (short)5100);
        continue;
        localObject1 = a(2, (short)5200);
        continue;
        localObject1 = f();
      }
    }
    Object localObject1 = ve.a(paramShort);
    System.arraycopy(localObject1, 0, arrayOfByte, m, localObject1.length);
    m = localObject1.length + m;
    int i;
    switch (paramShort)
    {
    case 2200: 
    case 4200: 
    case 5100: 
    case 5101: 
    case 5200: 
    case 5201: 
    case 7000: 
    case 8000: 
    case 9000: 
    case 13100: 
    case 13101: 
    case 13310: 
    case 13311: 
    case 14000: 
    case 15301: 
    default: 
      i = -1;
    }
    for (;;)
    {
      arrayOfByte[m] = i;
      j = m + 1;
      break;
      int i1;
      float f;
      try
      {
        if (((WifiManager)this.jdField_a_of_type_AndroidContentContext.getSystemService("wifi")).isWifiEnabled())
        {
          j = 1;
          break label1784;
          if (!b()) {
            break label1805;
          }
          i = 1;
          continue;
          if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            break label1810;
          }
          i = 1;
          continue;
          j = 0;
          i1 = co.a.a();
          if ((i1 <= 0) || (i1 < 120)) {
            break label1822;
          }
          j = 0;
          break label1815;
          if (!d()) {
            break label1861;
          }
          i = 1;
          continue;
          if (!e()) {
            break label1866;
          }
          i = 1;
          continue;
          if (!f()) {
            break label1871;
          }
          i = 1;
          continue;
          if (!g()) {
            break label1876;
          }
          i = 1;
          continue;
          if (!h()) {
            break label1881;
          }
          i = 1;
          continue;
          if (!i()) {
            break label1886;
          }
          i = 1;
          continue;
          if (!j()) {
            break label1891;
          }
          i = 1;
          continue;
          if (!k()) {
            break label1896;
          }
          i = 1;
          continue;
          if (!l()) {
            break label1901;
          }
          i = 1;
          continue;
          if (!m()) {
            break label1906;
          }
          i = 1;
          continue;
          if (!n()) {
            break label1911;
          }
          i = 1;
          continue;
          if (!o()) {
            break label1916;
          }
          i = 1;
          continue;
          if (!p()) {
            break label1921;
          }
          i = 1;
          continue;
          i = (byte)c();
          continue;
          if (!q()) {
            break label1926;
          }
          i = 1;
          continue;
          if (!r()) {
            break label1931;
          }
          i = 1;
          continue;
          j = 0;
          i1 = e();
          if ((i1 <= 0) || (i1 > 15000)) {
            break label1943;
          }
          j = 0;
          break label1936;
          j = 0;
          f = a();
          if (f != 0.85F) {
            break label2016;
          }
          j = 0;
          break label2009;
          if (!ContentResolver.getMasterSyncAutomatically()) {
            break label2056;
          }
          i = 1;
          continue;
          if (!t()) {
            break label2061;
          }
          i = 1;
          continue;
          if (!u()) {
            break label2066;
          }
          i = 1;
          continue;
          if (!v()) {
            break label2071;
          }
          i = 1;
          continue;
          if (!w()) {
            break label2076;
          }
          i = 1;
          continue;
          if (Settings.Secure.getInt(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "install_non_market_apps", 0) <= 0) {
            break label2091;
          }
          j = 1;
          break label2081;
          if (!y()) {
            break label2102;
          }
          i = 1;
          continue;
          if (!z()) {
            break label2107;
          }
          i = 1;
          continue;
          if (!A()) {
            break label2112;
          }
          i = 1;
          continue;
          j = 0;
          localObject1 = Settings.System.getString(this.jdField_a_of_type_AndroidContentContext.getContentResolver(), "date_format");
          if (((String)localObject1).equals("MM-dd-yyyy"))
          {
            j = 0;
            break label2117;
          }
          if (((String)localObject1).equals("dd-MM-yyyy"))
          {
            j = 1;
            break label2117;
          }
          if (!((String)localObject1).equals("yyyy-MM-dd")) {
            break label2117;
          }
          j = 2;
          break label2117;
          if (!a("com.google.android.marvin.talkback.TalkBackService")) {
            break label2124;
          }
          i = 1;
          continue;
          if (!C()) {
            break label2129;
          }
          i = 1;
          continue;
          if (!D()) {
            break label2134;
          }
          i = 1;
          continue;
          if (!E()) {
            break label2139;
          }
          i = 1;
          continue;
          if (!F()) {
            break label2144;
          }
          i = 1;
          continue;
          if (!G()) {
            break label2149;
          }
          i = 1;
          continue;
          if (!H()) {
            break label2154;
          }
          i = 1;
          continue;
          if (!I()) {
            break label2159;
          }
          i = 1;
          continue;
          J();
          i = 0;
          continue;
          j = 0;
          f = b();
          if (f != 0.0F) {
            break label2171;
          }
          j = 0;
          break label2164;
          j = 0;
          f = c();
          if (f != 0.0F) {
            break label2271;
          }
          j = 0;
          break label2264;
          if (!K()) {
            break label2364;
          }
          i = 1;
          continue;
          j = 0;
          i1 = k();
          if (i1 != -1) {
            break label2376;
          }
          j = 0;
          break label2369;
          if (!L()) {
            break label2447;
          }
          i = 1;
          continue;
          j = 1;
          localObject1 = g();
          System.out.println("hdcp 정보 : " + (String)localObject1);
          if (((String)localObject1).contains("never"))
          {
            j = 0;
            break label2452;
          }
          if (((String)localObject1).equals("only"))
          {
            j = 1;
            break label2452;
          }
          if (!((String)localObject1).equals("always")) {
            break label2452;
          }
          j = 2;
          break label2452;
          if (!M()) {
            break label2459;
          }
          i = 1;
          continue;
          if (!N()) {
            break label2464;
          }
          i = 1;
          continue;
          boolean bool = O();
          if (bool)
          {
            i = 1;
            continue;
          }
          i = 0;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException = localException;
        arrayOfByte[m] = -1;
        j = m + 1;
        break;
      }
      finally {}
      for (;;)
      {
        label1784:
        if (j == 0) {
          break label1800;
        }
        i = 1;
        break;
        j = 0;
      }
      label1800:
      i = 0;
      continue;
      label1805:
      i = 0;
      continue;
      label1810:
      i = 0;
      continue;
      for (;;)
      {
        label1815:
        i = (byte)j;
        break;
        label1822:
        if (i1 >= 300) {
          j = 1;
        } else if (i1 >= 3600) {
          j = 2;
        } else if (i1 == 0) {
          j = 3;
        }
      }
      label1861:
      i = 0;
      continue;
      label1866:
      i = 0;
      continue;
      label1871:
      i = 0;
      continue;
      label1876:
      i = 0;
      continue;
      label1881:
      i = 0;
      continue;
      label1886:
      i = 0;
      continue;
      label1891:
      i = 0;
      continue;
      label1896:
      i = 0;
      continue;
      label1901:
      i = 0;
      continue;
      label1906:
      i = 0;
      continue;
      label1911:
      i = 0;
      continue;
      label1916:
      i = 0;
      continue;
      label1921:
      i = 0;
      continue;
      label1926:
      i = 0;
      continue;
      label1931:
      i = 0;
      continue;
      for (;;)
      {
        label1936:
        i = (byte)j;
        break;
        label1943:
        if (i1 <= 30000) {
          j = 1;
        } else if (i1 <= 60000) {
          j = 2;
        } else if (i1 <= 120000) {
          j = 3;
        } else if (i1 <= 300000) {
          j = 4;
        } else if (i1 <= 600000) {
          j = 5;
        }
      }
      for (;;)
      {
        label2009:
        i = (byte)j;
        break;
        label2016:
        if (f == 1.0F) {
          j = 1;
        } else if (f == 1.15F) {
          j = 2;
        } else if (f == 1.3F) {
          j = 3;
        }
      }
      label2056:
      i = 0;
      continue;
      label2061:
      i = 0;
      continue;
      label2066:
      i = 0;
      continue;
      label2071:
      i = 0;
      continue;
      label2076:
      i = 0;
      continue;
      for (;;)
      {
        label2081:
        if (j == 0) {
          break label2097;
        }
        i = 1;
        break;
        label2091:
        j = 0;
      }
      label2097:
      i = 0;
      continue;
      label2102:
      i = 0;
      continue;
      label2107:
      i = 0;
      continue;
      label2112:
      i = 0;
      continue;
      label2117:
      i = (byte)j;
      continue;
      label2124:
      i = 0;
      continue;
      label2129:
      i = 0;
      continue;
      label2134:
      i = 0;
      continue;
      label2139:
      i = 0;
      continue;
      label2144:
      i = 0;
      continue;
      label2149:
      i = 0;
      continue;
      label2154:
      i = 0;
      continue;
      label2159:
      i = 0;
      continue;
      for (;;)
      {
        label2164:
        i = (byte)j;
        break;
        label2171:
        if (f < 0.0F) {
          j = 0;
        } else if (f <= 0.5F) {
          j = 1;
        } else if (f <= 1.0F) {
          j = 2;
        } else if (f <= 1.5F) {
          j = 3;
        } else if (f <= 2.0F) {
          j = 4;
        } else if (f <= 5.0F) {
          j = 5;
        } else if (f <= 10.0F) {
          j = 6;
        }
      }
      for (;;)
      {
        label2264:
        i = (byte)j;
        break;
        label2271:
        if (f < 0.0F) {
          j = 0;
        } else if (f <= 0.5F) {
          j = 1;
        } else if (f <= 1.0F) {
          j = 2;
        } else if (f <= 1.5F) {
          j = 3;
        } else if (f <= 2.0F) {
          j = 4;
        } else if (f <= 5.0F) {
          j = 5;
        } else if (f <= 10.0F) {
          j = 6;
        }
      }
      label2364:
      i = 0;
      continue;
      for (;;)
      {
        label2369:
        i = (byte)j;
        break;
        label2376:
        if (i1 < -1) {
          j = 0;
        } else if (i1 == 0) {
          j = 1;
        } else if (i1 == 1) {
          j = 2;
        } else if (i1 == 2) {
          j = 3;
        } else if (i1 == 3) {
          j = 4;
        } else if (i1 == 4) {
          j = 5;
        }
      }
      label2447:
      i = 0;
      continue;
      label2452:
      i = (byte)j;
      continue;
      label2459:
      i = 0;
      continue;
      label2464:
      i = 0;
    }
  }
  
  public final byte[] b()
  {
    Object localObject = Locale.getDefault();
    try
    {
      localObject = ((Locale)localObject).getDisplayLanguage().getBytes("UTF-16LE");
      byte[] arrayOfByte1 = new byte[localObject.length + 2 + 2];
      byte[] arrayOfByte2 = ve.a((short)342);
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, arrayOfByte2.length);
      System.arraycopy(localObject, 0, arrayOfByte1, 2, localObject.length);
      return arrayOfByte1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }
}
