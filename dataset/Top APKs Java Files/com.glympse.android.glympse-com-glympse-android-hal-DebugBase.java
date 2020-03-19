package com.glympse.android.hal;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.os.Process;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.glympse.android.lib.Debug;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class DebugBase
{
  private static boolean _debugMode;
  public static HashMap<String, FileOutputStream> _fileMap = new HashMap();
  
  public DebugBase() {}
  
  private static void B(String paramString)
  {
    paramString = (FileOutputStream)_fileMap.remove(paramString);
    if (paramString != null) {}
    try
    {
      paramString.flush();
      paramString.close();
      return;
    }
    catch (Throwable paramString) {}
  }
  
  private static void a(Throwable paramThrowable, StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append("\n\nThread Crashed:\n");
    for (int i = 1; paramThrowable != null; i = 0)
    {
      if (i == 0) {
        paramStringBuilder.append("Caused by: ");
      }
      paramStringBuilder.append(paramThrowable.toString());
      paramStringBuilder.append('\n');
      StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
      int j = arrayOfStackTraceElement.length;
      i = 0;
      while (i < j)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
        paramStringBuilder.append("\tat ");
        paramStringBuilder.append(localStackTraceElement.toString());
        paramStringBuilder.append('\n');
        i += 1;
      }
      paramThrowable = paramThrowable.getCause();
    }
  }
  
  public static void appendBinaryImages(StringBuilder paramStringBuilder) {}
  
  public static void appendCurrentStack(StringBuilder paramStringBuilder)
  {
    try
    {
      throw new Exception();
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable, paramStringBuilder);
    }
  }
  
  public static void appendPathToFile(Context paramContext, StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(w.o(t.n(paramContext)));
  }
  
  public static void appendReport(Context paramContext, StringBuilder paramStringBuilder)
  {
    Object localObject15 = null;
    Object localObject5 = t.n(paramContext);
    try
    {
      paramContext = ((Context)localObject5).getPackageManager().getPackageInfo(((Context)localObject5).getPackageName(), 0);
      paramStringBuilder.append("\nApp Package:       ");
      paramStringBuilder.append(paramContext.packageName);
      paramStringBuilder.append("\nApp Version:       ");
      paramStringBuilder.append(paramContext.versionName);
      paramStringBuilder.append("\nApp Build:         ");
      paramStringBuilder.append(paramContext.versionCode);
      try
      {
        localObject7 = Locale.getDefault();
        if (localObject7 != null) {
          break label731;
        }
        localObject1 = null;
      }
      catch (Throwable localThrowable3)
      {
        for (;;)
        {
          try
          {
            localObject9 = (TelephonyManager)((Context)localObject5).getSystemService("phone");
            if (localObject9 == null) {
              break label988;
            }
          }
          catch (Throwable paramContext)
          {
            Object localObject7;
            Object localObject1;
            Object localObject9;
            Object localObject3;
            Context localContext2;
            Object localObject16;
            localObject15 = null;
            localObject2 = null;
            localObject10 = null;
            localObject8 = null;
            localObject6 = null;
            localObject4 = null;
            paramContext = null;
            Object localObject11 = null;
            localObject13 = localObject4;
            localObject17 = null;
            localObject4 = localObject6;
            localObject6 = localObject13;
            localObject13 = localObject17;
            localObject17 = paramContext;
            continue;
          }
          try
          {
            i = ((TelephonyManager)localObject9).getPhoneType();
          }
          catch (Throwable localThrowable3)
          {
            try
            {
              j = ((TelephonyManager)localObject9).getNetworkType();
            }
            catch (Throwable localThrowable3)
            {
              try
              {
                k = ((TelephonyManager)localObject9).getSimState();
              }
              catch (Throwable localThrowable3)
              {
                try
                {
                  paramContext = ((TelephonyManager)localObject9).getSimCountryIso();
                }
                catch (Throwable localThrowable3)
                {
                  try
                  {
                    localObject1 = ((TelephonyManager)localObject9).getSimOperator();
                  }
                  catch (Throwable localThrowable3)
                  {
                    try
                    {
                      localObject3 = ((TelephonyManager)localObject9).getSimOperatorName();
                    }
                    catch (Throwable localThrowable3)
                    {
                      try
                      {
                        localObject5 = ((TelephonyManager)localObject9).getNetworkCountryIso();
                      }
                      catch (Throwable localThrowable3)
                      {
                        try
                        {
                          localObject7 = ((TelephonyManager)localObject9).getNetworkOperator();
                          try
                          {
                            localObject11 = ((TelephonyManager)localObject9).getNetworkOperatorName();
                            localObject13 = localObject7;
                            localObject7 = localObject3;
                            m = j;
                            localObject9 = localObject5;
                            j = i;
                            i = m;
                            localObject3 = paramContext;
                            localObject5 = localObject1;
                            localObject1 = localObject13;
                            paramContext = (Context)localObject11;
                          }
                          catch (Throwable localThrowable5)
                          {
                            localObject10 = null;
                            localObject11 = paramContext;
                            localObject13 = localObject2;
                            int m = i;
                            i = j;
                            paramContext = localObject10;
                            localObject2 = localObject8;
                            localObject10 = localObject6;
                            localObject8 = localObject4;
                            localObject6 = localObject13;
                            localObject4 = localObject11;
                            j = m;
                            continue;
                          }
                          if (-1 == j) {
                            break label982;
                          }
                          try
                          {
                            localObject11 = getTelephonyManagerPhoneTypeString(j);
                            if (-1 == i) {
                              break label976;
                            }
                          }
                          catch (Throwable localThrowable6)
                          {
                            Object localObject18;
                            Object localObject17;
                            localObject13 = null;
                            localObject12 = null;
                            localObject15 = paramContext;
                            paramContext = (Context)localObject13;
                            continue;
                          }
                          try
                          {
                            localObject13 = getTelephonyManagerNetworkTypeString(i);
                            if (-1 == k) {}
                          }
                          catch (Throwable localThrowable7)
                          {
                            localObject14 = null;
                            localObject15 = paramContext;
                            paramContext = localObject14;
                            continue;
                          }
                          try
                          {
                            localObject15 = getTelephonyManagerSimStateString(k);
                            localObject18 = localObject5;
                            localObject17 = localObject13;
                            localObject13 = localObject15;
                            localObject5 = localObject3;
                            localObject3 = localObject18;
                            localObject15 = paramContext;
                            paramStringBuilder.append("\nLocale Name:       " + Helpers.safeStr(localContext2) + "\nLocale Code:       " + Helpers.safeStr((String)localObject16) + "\nRelease Ver:       " + Helpers.safeStr(Build.VERSION.RELEASE) + "\nSDK Ver:           " + Helpers.safeStr(Build.VERSION.SDK) + "\nIncremental Ver:   " + Helpers.safeStr(Build.VERSION.INCREMENTAL) + "\nBuild Brand:       " + Helpers.safeStr(Build.BRAND) + "\nBuild Model:       " + Helpers.safeStr(Build.MODEL) + "\nBuild Board:       " + Helpers.safeStr(Build.BOARD) + "\nBuild Device:      " + Helpers.safeStr(Build.DEVICE) + "\nBuild Display:     " + Helpers.safeStr(Build.DISPLAY) + "\nBuild Fingerprint: " + Helpers.safeStr(Build.FINGERPRINT) + "\nBuild Host:        " + Helpers.safeStr(Build.HOST) + "\nBuild ID:          " + Helpers.safeStr(Build.ID) + "\nBuild Product:     " + Helpers.safeStr(Build.PRODUCT) + "\nBuild Tags:        " + Helpers.safeStr(Build.TAGS) + "\nBuild Time:        " + Build.TIME + "\nBuild Type:        " + Helpers.safeStr(Build.TYPE) + "\nBuild User:        " + Helpers.safeStr(Build.USER) + "\nPhone Type:        " + Helpers.safeStr((String)localObject11) + "\nNetwork Type:      " + Helpers.safeStr((String)localObject17) + "\nSim State:         " + Helpers.safeStr((String)localObject13) + "\nSim Country:       " + Helpers.safeStr((String)localObject5) + "\nSim Operator:      " + Helpers.safeStr((String)localObject3) + "\nSim Operator Name: " + Helpers.safeStr((String)localObject7) + "\nNet Country:       " + Helpers.safeStr((String)localObject9) + "\nNet Operator:      " + Helpers.safeStr((String)localObject1) + "\nNet Operator Name: " + Helpers.safeStr((String)localObject15));
                            return;
                          }
                          catch (Throwable localThrowable8)
                          {
                            localContext1 = paramContext;
                            paramContext = localObject14;
                            continue;
                          }
                          localObject1 = ((Locale)localObject7).getLanguage();
                          break label1015;
                          paramContext = ((Locale)localObject7).getCountry();
                          continue;
                          try
                          {
                            paramContext = ((Locale)localObject7).getDisplayName();
                          }
                          catch (Throwable paramContext)
                          {
                            Context localContext1;
                            paramContext = localObject2;
                            continue;
                          }
                          paramContext = paramContext;
                          paramContext = null;
                          localContext2 = null;
                          localObject16 = paramContext;
                          continue;
                          paramContext = paramContext;
                          i = -1;
                          continue;
                          paramContext = paramContext;
                          j = -1;
                          continue;
                          paramContext = paramContext;
                          k = -1;
                          continue;
                          paramContext = paramContext;
                          paramContext = null;
                          continue;
                          localThrowable1 = localThrowable1;
                          localObject2 = null;
                          continue;
                          localThrowable2 = localThrowable2;
                          localObject4 = null;
                          continue;
                          localThrowable3 = localThrowable3;
                          localObject6 = null;
                        }
                        catch (Throwable localThrowable4)
                        {
                          localObject8 = null;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      localObject9 = new StringBuilder();
      localObject3 = localObject1;
      if (Helpers.isEmpty((String)localObject1)) {
        localObject3 = "??";
      }
      localObject3 = ((StringBuilder)localObject9).append((String)localObject3).append("-");
      localObject1 = paramContext;
      if (Helpers.isEmpty(paramContext)) {
        localObject1 = "??";
      }
      localObject1 = (String)localObject1;
      if (localObject7 == null)
      {
        paramContext = null;
        localContext2 = paramContext;
        localObject16 = localObject1;
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        Object localObject13;
        label731:
        continue;
        label976:
        Object localObject14 = null;
        continue;
        label982:
        Object localObject12 = null;
        continue;
        label988:
        int k = -1;
        int i = -1;
        int j = -1;
        paramContext = null;
        Object localObject2 = null;
        Object localObject10 = null;
        Object localObject8 = null;
        Object localObject6 = null;
        Object localObject4 = null;
        continue;
        label1015:
        if (localObject8 == null) {
          paramContext = null;
        }
      }
    }
  }
  
  public static void closeAllFiles()
  {
    Iterator localIterator = Helpers.emptyIfNull(_fileMap.keySet()).iterator();
    while (localIterator.hasNext()) {
      B((String)localIterator.next());
    }
  }
  
  public static void deleteFile(Context paramContext, String paramString)
  {
    try
    {
      B(paramString);
      t.n(paramContext).deleteFile(paramString);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static void dumpBundle(Bundle paramBundle)
  {
    if (Debug.getLevel() > 2) {}
    for (;;)
    {
      return;
      Debug.log(2, "-=- BUNDLE -=-");
      if (paramBundle == null)
      {
        Debug.log(2, "Bundle: null");
        return;
      }
      Debug.log(2, "toString: " + paramBundle.toString());
      Debug.log(2, "describeContents: " + paramBundle.describeContents());
      Debug.log(2, "size: " + paramBundle.size());
      Debug.log(2, "hasFileDescriptors: " + paramBundle.hasFileDescriptors());
      Iterator localIterator = Helpers.emptyIfNull(paramBundle.keySet()).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject1 = paramBundle.get(str);
        if ((localObject1 instanceof Object[]))
        {
          localObject1 = (Object[])localObject1;
          int k = localObject1.length;
          int j = 0;
          int i = 0;
          while (j < k)
          {
            Object localObject2 = localObject1[j];
            Debug.log(2, "Key: " + str + "[" + i + "], Value: " + localObject2);
            j += 1;
            i += 1;
          }
        }
        else
        {
          Debug.log(2, "Key: " + str + ", Value: " + localObject1);
        }
      }
    }
  }
  
  public static void dumpCursor(Cursor paramCursor)
  {
    if (Debug.getLevel() > 2) {
      return;
    }
    Debug.log(2, "-=- CURSOR -=-");
    if (paramCursor == null)
    {
      Debug.log(2, "Cursor: null");
      return;
    }
    boolean bool = paramCursor.moveToFirst();
    while (bool)
    {
      int j = paramCursor.getPosition();
      int k = paramCursor.getColumnCount();
      int i = 0;
      label56:
      if (i < k) {}
      try
      {
        Debug.log(2, "[" + j + ", " + i + ", " + paramCursor.getColumnName(i) + "] " + paramCursor.getString(i));
        i += 1;
        break label56;
        Bundle localBundle = paramCursor.getExtras();
        if (localBundle != null) {
          dumpBundle(localBundle);
        }
        bool = paramCursor.moveToNext();
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  }
  
  public static void dumpIntent(Intent paramIntent)
  {
    if (Debug.getLevel() > 2) {
      return;
    }
    Debug.log(2, "-=- INTENT -=-");
    if (paramIntent == null)
    {
      Debug.log(2, "Intent: null");
      return;
    }
    Debug.log(2, "toString: " + paramIntent.toString());
    Debug.log(2, "toURI: " + paramIntent.toURI());
    Debug.log(2, "describeContents: " + paramIntent.describeContents());
    Debug.log(2, "getAction: " + paramIntent.getAction());
    Debug.log(2, "getDataString: " + paramIntent.getDataString());
    Debug.log(2, "getFlags: " + paramIntent.getFlags());
    Debug.log(2, "getScheme: " + paramIntent.getScheme());
    Debug.log(2, "getType: " + paramIntent.getType());
    Debug.log(2, "getComponent: " + paramIntent.getComponent());
    Debug.log(2, "getData: " + paramIntent.getData());
    Debug.log(2, "hasFileDescriptors: " + paramIntent.hasFileDescriptors());
    if (paramIntent.getComponent() != null)
    {
      Debug.log(2, "ComponentName.describeContents: " + paramIntent.getComponent().describeContents());
      Debug.log(2, "ComponentName.flattenToShortString: " + paramIntent.getComponent().flattenToShortString());
      Debug.log(2, "ComponentName.flattenToString: " + paramIntent.getComponent().flattenToString());
      Debug.log(2, "ComponentName.getClassName: " + paramIntent.getComponent().getClassName());
      Debug.log(2, "ComponentName.getPackageName: " + paramIntent.getComponent().getPackageName());
      Debug.log(2, "ComponentName.getShortClassName: " + paramIntent.getComponent().getShortClassName());
      Debug.log(2, "ComponentName.toShortString: " + paramIntent.getComponent().toShortString());
      Debug.log(2, "ComponentName.toString: " + paramIntent.getComponent().toString());
    }
    Iterator localIterator = Helpers.emptyIfNull(paramIntent.getCategories()).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Debug.log(2, "Category: " + str);
    }
    dumpBundle(paramIntent.getExtras());
  }
  
  public static void extractExceptionString(Throwable paramThrowable, StringBuilder paramStringBuilder)
  {
    String str3 = paramThrowable.toString();
    String str2 = paramThrowable.toString();
    paramStringBuilder.append("\nException name:    ");
    String str1 = str3;
    if (str3 == null) {
      str1 = "???";
    }
    paramStringBuilder.append(str1);
    paramStringBuilder.append("\nException what:    ");
    if (str2 == null) {}
    for (str1 = "???";; str1 = str2)
    {
      paramStringBuilder.append(str1);
      a(paramThrowable, paramStringBuilder);
      return;
    }
  }
  
  public static void flushAllLogs()
  {
    try
    {
      Iterator localIterator = _fileMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((FileOutputStream)_fileMap.get(str)).flush();
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public static long getCurrentTU()
  {
    return System.currentTimeMillis();
  }
  
  public static long getFileSize(Context paramContext, String paramString)
  {
    paramContext = w.o(paramContext) + "/" + paramString;
    B(paramContext);
    return new File(paramContext).length();
  }
  
  public static String getTelephonyManagerNetworkTypeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Integer.toString(paramInt);
    case 0: 
      return "UNKNOWN";
    case 1: 
      return "GPRS";
    case 2: 
      return "EDGE";
    case 3: 
      return "UMTS";
    case 4: 
      return "CDMA";
    case 5: 
      return "EVDO_0";
    case 6: 
      return "EVDO_A";
    case 7: 
      return "1xRTT";
    case 8: 
      return "HSDPA";
    case 9: 
      return "HSUPA";
    case 10: 
      return "HSPA";
    case 11: 
      return "IDEN";
    case 12: 
      return "EVDO_B";
    case 13: 
      return "LTE";
    case 14: 
      return "EHRPD";
    }
    return "HSPA+";
  }
  
  public static String getTelephonyManagerPhoneTypeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Integer.toString(paramInt);
    case 0: 
      return "None";
    case 1: 
      return "GSM";
    case 2: 
      return "CDMA";
    }
    return "SIP";
  }
  
  public static String getTelephonyManagerSimStateString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Integer.toString(paramInt);
    case 0: 
      return "UNKNOWN";
    case 1: 
      return "ABSENT";
    case 2: 
      return "PIN_REQUIRED";
    case 3: 
      return "PUK_REQUIRED";
    case 4: 
      return "NETWORK_LOCKED";
    }
    return "READY";
  }
  
  public static int httpUpload(Context paramContext, String paramString1, String paramString2)
  {
    int j = 0;
    i = j;
    try
    {
      paramContext = w.o(paramContext) + "/" + paramString2;
      i = j;
      B(paramContext);
      i = j;
      paramContext = new File(paramContext);
      i = j;
      paramString2 = new URL(paramString1);
      i = j;
      paramString1 = (HttpURLConnection)paramString2.openConnection();
      i = j;
      ae.a(paramString2, paramString1);
      i = j;
      paramString1.setFixedLengthStreamingMode((int)paramContext.length());
      i = j;
      paramString1.setConnectTimeout(180000);
      i = j;
      paramString1.setReadTimeout(180000);
      i = j;
      paramString1.setAllowUserInteraction(false);
      i = j;
      paramString1.setDoOutput(true);
      i = j;
      paramString1.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
      i = j;
      paramString2 = paramString1.getOutputStream();
      i = j;
      byte[] arrayOfByte = new byte[40960];
      i = j;
      paramContext = new FileInputStream(paramContext);
      for (;;)
      {
        i = j;
        int k;
        if (paramContext.available() > 0)
        {
          i = j;
          k = paramContext.read(arrayOfByte);
          if (k >= 0) {}
        }
        else
        {
          i = j;
          paramContext.close();
          i = j;
          paramString2.flush();
          i = j;
          paramString2.close();
          i = j;
          j = paramString1.getResponseCode();
          i = j;
          paramString1.disconnect();
          return j;
        }
        i = j;
        paramString2.write(arrayOfByte, 0, k);
      }
      return i;
    }
    catch (Throwable paramContext) {}
  }
  
  public static boolean isOnWifi(Context paramContext)
  {
    if (t.checkPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
      return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return true;
  }
  
  public static void logDiagnostics(Context paramContext)
  {
    Object localObject1 = t.n(paramContext);
    if (Debug.getLevel() > 1) {
      return;
    }
    for (;;)
    {
      try
      {
        paramContext = ((Context)localObject1).getContentResolver();
        if (paramContext != null)
        {
          logSpecial(1, "DIAG: Development Settings Enabled: " + Settings.System.getInt(paramContext, "development_settings_enabled", -1));
          logSpecial(1, "DIAG: Always finish activities: " + Settings.System.getInt(paramContext, "always_finish_activities", -1));
        }
        paramContext = (ActivityManager)((Context)localObject1).getSystemService("activity");
        if (paramContext != null)
        {
          localObject2 = paramContext.getDeviceConfigurationInfo();
          if (localObject2 != null) {
            logSpecial(1, "DIAG: reqGlEsVersion:0x" + Integer.toHexString(((ConfigurationInfo)localObject2).reqGlEsVersion) + ", reqInputFeatures:" + ((ConfigurationInfo)localObject2).reqInputFeatures + ", reqKeyboardType:" + ((ConfigurationInfo)localObject2).reqKeyboardType + ", reqNavigation:" + ((ConfigurationInfo)localObject2).reqNavigation + ", reqTouchScreen:" + ((ConfigurationInfo)localObject2).reqTouchScreen);
          }
          logSpecial(1, "DIAG: Memory classes: " + Reflection._ActivityManager.getMemoryClass(paramContext) + "M / " + Reflection._ActivityManager.getLargeMemoryClass(paramContext) + "M");
          localObject2 = paramContext.getRunningAppProcesses();
          if ((localObject2 != null) && (((List)localObject2).size() > 0))
          {
            paramContext = new HashMap();
            localObject2 = ((List)localObject2).iterator();
            if (!((Iterator)localObject2).hasNext()) {
              break label698;
            }
            localObject3 = (String[])Helpers.emptyIfNull(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next()).pkgList);
            j = localObject3.length;
            int i = 0;
            if (i < j)
            {
              paramContext.put(localObject3[i], null);
              i += 1;
              continue;
            }
            continue;
            localObject1 = ((Context)localObject1).getPackageManager().getInstalledPackages(4096);
            SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            StringBuilder localStringBuilder = new StringBuilder(4096);
            Iterator localIterator = Helpers.emptyIfNull((Iterable)localObject1).iterator();
            i = 0;
            if (!localIterator.hasNext()) {
              break;
            }
            PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
            localStringBuilder.setLength(0);
            long l1 = Reflection._PackageInfo.firstInstallTime(localPackageInfo);
            long l2 = Reflection._PackageInfo.lastUpdateTime(localPackageInfo);
            if ((paramContext == null) || (!paramContext.containsKey(localPackageInfo.packageName))) {
              break label701;
            }
            j = 1;
            int k = i + 1;
            if (j == 0) {
              break label706;
            }
            localObject1 = "Y";
            String str = localPackageInfo.packageName;
            i = localPackageInfo.versionCode;
            if (l1 <= 0L) {
              break label714;
            }
            localObject2 = localSimpleDateFormat.format(new Date(l1));
            if (l2 <= 0L) {
              break label722;
            }
            localObject3 = localSimpleDateFormat.format(new Date(l2));
            localStringBuilder.append(String.format("PKG[%04d] R: %s, N: %s, B: %d, I: %s, U: %s, P: ", new Object[] { Integer.valueOf(k), localObject1, str, Integer.valueOf(i), localObject2, localObject3 }));
            localObject1 = localPackageInfo.requestedPermissions;
            if (localObject1 != null)
            {
              j = 1;
              int m = localObject1.length;
              i = 0;
              if (i < m)
              {
                localObject2 = localObject1[i];
                if (j == 0) {
                  localStringBuilder.append(" | ");
                }
                localStringBuilder.append(((String)localObject2).replace("android.permission.", ""));
                j = 0;
                i += 1;
                continue;
              }
            }
            localStringBuilder.append(", V: ");
            localStringBuilder.append(localPackageInfo.versionName);
            logSpecial(1, localStringBuilder.toString());
            i = k;
            continue;
          }
        }
        paramContext = null;
      }
      catch (Throwable paramContext)
      {
        Debug.ex(paramContext, false);
        return;
      }
      continue;
      label698:
      continue;
      label701:
      int j = 0;
      continue;
      label706:
      localObject1 = "n";
      continue;
      label714:
      Object localObject2 = "unknown";
      continue;
      label722:
      Object localObject3 = "unknown";
    }
  }
  
  public static void logDiagnosticsOnBackgroundThread(Context paramContext)
  {
    t.n(paramContext);
    if (Debug.getLevel() <= 1) {
      runInBackground(0L, new Runnable()
      {
        public void run()
        {
          DebugBase.logDiagnostics(null);
        }
      });
    }
  }
  
  public static void logMemory(Context paramContext)
  {
    Object localObject2 = t.n(paramContext);
    if ((localObject2 != null) && (Debug.getLevel() <= 1)) {
      paramContext = new StringBuilder(320);
    }
    try
    {
      Object localObject1 = new ActivityManager.MemoryInfo();
      localObject2 = (ActivityManager)((Context)localObject2).getSystemService("activity");
      ((ActivityManager)localObject2).getMemoryInfo((ActivityManager.MemoryInfo)localObject1);
      paramContext.append("MEM: SysTotal: " + Reflection._ActivityManager_MemoryInfo.totalMem((ActivityManager.MemoryInfo)localObject1) / 1024L + "K, SysFree: " + ((ActivityManager.MemoryInfo)localObject1).availMem / 1024L + "K, SysThreshold: " + ((ActivityManager.MemoryInfo)localObject1).threshold / 1024L + "K, SysLow: " + ((ActivityManager.MemoryInfo)localObject1).lowMemory);
      localObject1 = Reflection._ActivityManager.getProcessMemoryInfo((ActivityManager)localObject2, new int[] { Process.myPid() });
      if ((localObject1 != null) && (localObject1.length > 0)) {
        paramContext.append(", Dalvik PD/PSS/SD: " + localObject1[0].dalvikPrivateDirty + "K / " + localObject1[0].dalvikPss + "K / " + localObject1[0].dalvikSharedDirty + "K, Native PD/PSS/SD: " + localObject1[0].nativePrivateDirty + "K / " + localObject1[0].nativePss + "K / " + localObject1[0].nativeSharedDirty + "K, Other PD/PSS/SD: " + localObject1[0].otherPrivateDirty + "K / " + localObject1[0].otherPss + "K / " + localObject1[0].otherSharedDirty + "K, Total PD/PSS/SD: " + Reflection._Debug_MemoryInfo.getTotalPrivateDirty(localObject1[0]) + "K / " + Reflection._Debug_MemoryInfo.getTotalPss(localObject1[0]) + "K / " + Reflection._Debug_MemoryInfo.getTotalSharedDirty(localObject1[0]) + "K");
      }
      localObject1 = Reflection._ActivityManager.getMyMemoryState((ActivityManager)localObject2);
      if (localObject1 != null) {
        paramContext.append(", Importance: " + ((ActivityManager.RunningAppProcessInfo)localObject1).importance + ", Reason: " + Reflection._RunningAppProcessInfo.importanceReasonCode((ActivityManager.RunningAppProcessInfo)localObject1) + ", LRU: " + ((ActivityManager.RunningAppProcessInfo)localObject1).lru);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Debug.ex(localThrowable, false);
      }
    }
    logSpecial(1, paramContext.toString());
  }
  
  public static void logSpecial(int paramInt, String paramString)
  {
    if (_debugMode) {}
    for (int i = 0;; i = 65536)
    {
      Debug.log(i | paramInt, paramString);
      return;
    }
  }
  
  public static boolean originInGlympseCode(String paramString)
  {
    return paramString.contains("com.glympse");
  }
  
  public static void putMetric(long paramLong1, long paramLong2, Object paramObject) {}
  
  public static void runInBackground(long paramLong, Runnable paramRunnable)
  {
    try
    {
      new v(paramLong, paramRunnable, null).start();
      return;
    }
    catch (Throwable paramRunnable) {}
  }
  
  public static void runInBackground(Runnable paramRunnable)
  {
    runInBackground(5000L, paramRunnable);
  }
  
  public static void setDebugMode(boolean paramBoolean)
  {
    _debugMode = paramBoolean;
  }
  
  public static void showDebugView(Object paramObject) {}
  
  public static void writeConsole(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      Log.e("GlympseAPI", paramString);
      return;
    }
    Log.i("GlympseAPI", paramString);
  }
  
  public static void writeTextFile(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    boolean bool = true;
    paramContext = null;
    if (!paramBoolean) {}
    label111:
    for (;;)
    {
      try
      {
        B(paramString1);
        Object localObject = paramContext;
        if (paramContext == null)
        {
          paramContext = new File(paramString1);
          if (paramContext.exists()) {
            break label111;
          }
          paramContext.createNewFile();
          i = 0;
          if ((i != 0) && (paramBoolean))
          {
            paramBoolean = bool;
            localObject = new FileOutputStream(paramContext, paramBoolean);
            _fileMap.put(paramString1, localObject);
          }
        }
        else
        {
          ((FileOutputStream)localObject).write(paramString2.getBytes("UTF-8"));
          return;
          paramContext = (FileOutputStream)_fileMap.get(paramString1);
          continue;
        }
        paramBoolean = false;
        continue;
        int i = 1;
      }
      catch (Throwable paramContext)
      {
        return;
      }
    }
  }
}
