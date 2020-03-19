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
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
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
  public static HashMap<String, FileOutputStream> _fileMap = new HashMap();
  private static final String aM = "GlympseAPI";
  private static boolean aN;
  
  public DebugBase() {}
  
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
    paramStringBuilder.append(i.b(h.a(paramContext)));
  }
  
  public static void appendReport(Context paramContext, StringBuilder paramStringBuilder)
  {
    localObject4 = h.a(paramContext);
    try
    {
      paramContext = ((Context)localObject4).getPackageManager().getPackageInfo(((Context)localObject4).getPackageName(), 0);
      paramStringBuilder.append("\nApp Package:       ");
      paramStringBuilder.append(paramContext.packageName);
      paramStringBuilder.append("\nApp Version:       ");
      paramStringBuilder.append(paramContext.versionName);
      paramStringBuilder.append("\nApp Build:         ");
      paramStringBuilder.append(paramContext.versionCode);
      localObject15 = null;
      try
      {
        localObject5 = Locale.getDefault();
        if (localObject5 == null) {
          localObject1 = null;
        } else {
          localObject1 = ((Locale)localObject5).getLanguage();
        }
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          Object localObject5;
          Object localObject1;
          Object localObject6;
          Object localObject3;
          Object localObject7;
          String str1;
          Object localObject8;
          Object localObject12;
          Object localObject14;
          Object localObject13;
          Object localObject11;
          Object localObject10;
          Object localObject9;
          Context localContext2;
          String str2;
          continue;
          if (localObject5 == null) {
            paramContext = null;
          }
        }
      }
      paramContext = ((Locale)localObject5).getCountry();
      localObject6 = new StringBuilder();
      localObject3 = localObject1;
      if (Helpers.isEmpty((String)localObject1)) {
        localObject3 = "??";
      }
      ((StringBuilder)localObject6).append((String)localObject3);
      ((StringBuilder)localObject6).append("-");
      localObject1 = paramContext;
      if (Helpers.isEmpty(paramContext)) {
        localObject1 = "??";
      }
      ((StringBuilder)localObject6).append((String)localObject1);
      paramContext = ((StringBuilder)localObject6).toString();
      if (localObject5 != null) {}
      try
      {
        str3 = ((Locale)localObject5).getDisplayName();
        localContext1 = paramContext;
      }
      catch (Throwable localThrowable1)
      {
        String str3;
        Context localContext1;
        for (;;) {}
      }
      paramContext = null;
      str3 = null;
      localContext1 = paramContext;
      for (;;)
      {
        try
        {
          paramContext = (TelephonyManager)((Context)localObject4).getSystemService("phone");
          if (paramContext == null) {}
        }
        catch (Throwable paramContext)
        {
          int i;
          int j;
          int k;
          continue;
        }
        try
        {
          i = paramContext.getPhoneType();
        }
        catch (Throwable localThrowable2) {}
      }
      i = -1;
      try
      {
        j = paramContext.getNetworkType();
      }
      catch (Throwable localThrowable3)
      {
        for (;;) {}
      }
      j = -1;
      try
      {
        k = paramContext.getSimState();
      }
      catch (Throwable localThrowable4)
      {
        for (;;) {}
      }
      k = -1;
      try
      {
        localObject3 = paramContext.getSimCountryIso();
      }
      catch (Throwable localThrowable5)
      {
        for (;;) {}
      }
      localObject3 = null;
      try
      {
        localObject4 = paramContext.getSimOperator();
      }
      catch (Throwable localThrowable6)
      {
        for (;;) {}
      }
      localObject4 = null;
      try
      {
        localObject5 = paramContext.getSimOperatorName();
      }
      catch (Throwable localThrowable7)
      {
        for (;;) {}
      }
      localObject5 = null;
      try
      {
        localObject6 = paramContext.getNetworkCountryIso();
      }
      catch (Throwable localThrowable8)
      {
        for (;;) {}
      }
      localObject6 = null;
      try
      {
        localObject7 = paramContext.getNetworkOperator();
      }
      catch (Throwable localThrowable9)
      {
        for (;;) {}
      }
      localObject7 = null;
      try
      {
        paramContext = paramContext.getNetworkOperatorName();
      }
      catch (Throwable paramContext)
      {
        for (;;) {}
      }
      paramContext = null;
      break label350;
      paramContext = null;
      localObject3 = paramContext;
      localObject4 = localObject3;
      localObject5 = localObject4;
      localObject6 = localObject5;
      localObject7 = localObject6;
      i = -1;
      j = -1;
      k = -1;
      label350:
      if (-1 != i) {}
      try
      {
        str1 = getTelephonyManagerPhoneTypeString(i);
      }
      catch (Throwable localThrowable10)
      {
        for (;;) {}
      }
      localObject8 = null;
      localObject1 = localObject8;
      localObject12 = localObject15;
      localObject14 = localObject3;
      localObject13 = localObject4;
      localObject11 = localObject5;
      localObject10 = localObject6;
      localObject9 = localObject7;
      localContext2 = paramContext;
      break label588;
      str1 = null;
      if (-1 != j) {}
      try
      {
        str2 = getTelephonyManagerNetworkTypeString(j);
      }
      catch (Throwable localThrowable11)
      {
        for (;;) {}
      }
      localObject1 = null;
      localObject12 = localObject15;
      localObject8 = str1;
      localObject14 = localObject3;
      localObject13 = localObject4;
      localObject11 = localObject5;
      localObject10 = localObject6;
      localObject9 = localObject7;
      localContext2 = paramContext;
      break label588;
      str2 = null;
      localObject12 = localObject15;
      localObject8 = str1;
      localObject1 = str2;
      localObject14 = localObject3;
      localObject13 = localObject4;
      localObject11 = localObject5;
      localObject10 = localObject6;
      localObject9 = localObject7;
      localContext2 = paramContext;
      if (-1 != k) {}
      try
      {
        localObject12 = getTelephonyManagerSimStateString(k);
        localObject8 = str1;
        localObject1 = str2;
        localObject14 = localObject3;
        localObject13 = localObject4;
        localObject11 = localObject5;
        localObject10 = localObject6;
        localObject9 = localObject7;
        localContext2 = paramContext;
      }
      catch (Throwable localThrowable12)
      {
        for (;;)
        {
          localObject12 = localObject15;
          localObject8 = str1;
          Object localObject2 = str2;
          localObject14 = localObject3;
          localObject13 = localObject4;
          localObject11 = localObject5;
          localObject10 = localObject6;
          localObject9 = localObject7;
          localContext2 = paramContext;
        }
      }
      localContext2 = null;
      localObject8 = localContext2;
      localObject1 = localObject8;
      paramContext = (Context)localObject1;
      localObject3 = paramContext;
      localObject4 = localObject3;
      localObject5 = localObject4;
      localObject9 = localObject5;
      localObject10 = localObject5;
      localObject11 = localObject4;
      localObject13 = localObject3;
      localObject14 = paramContext;
      localObject12 = localObject15;
      label588:
      paramContext = new StringBuilder();
      paramContext.append("\nLocale Name:       ");
      paramContext.append(Helpers.safeStr(str3));
      paramContext.append("\nLocale Code:       ");
      paramContext.append(Helpers.safeStr(localContext1));
      paramContext.append("\nRelease Ver:       ");
      paramContext.append(Helpers.safeStr(Build.VERSION.RELEASE));
      paramContext.append("\nSDK Ver:           ");
      paramContext.append(Helpers.safeStr(Build.VERSION.SDK));
      paramContext.append("\nIncremental Ver:   ");
      paramContext.append(Helpers.safeStr(Build.VERSION.INCREMENTAL));
      paramContext.append("\nBuild Brand:       ");
      paramContext.append(Helpers.safeStr(Build.BRAND));
      paramContext.append("\nBuild Model:       ");
      paramContext.append(Helpers.safeStr(Build.MODEL));
      paramContext.append("\nBuild Board:       ");
      paramContext.append(Helpers.safeStr(Build.BOARD));
      paramContext.append("\nBuild Device:      ");
      paramContext.append(Helpers.safeStr(Build.DEVICE));
      paramContext.append("\nBuild Display:     ");
      paramContext.append(Helpers.safeStr(Build.DISPLAY));
      paramContext.append("\nBuild Fingerprint: ");
      paramContext.append(Helpers.safeStr(Build.FINGERPRINT));
      paramContext.append("\nBuild Host:        ");
      paramContext.append(Helpers.safeStr(Build.HOST));
      paramContext.append("\nBuild ID:          ");
      paramContext.append(Helpers.safeStr(Build.ID));
      paramContext.append("\nBuild Product:     ");
      paramContext.append(Helpers.safeStr(Build.PRODUCT));
      paramContext.append("\nBuild Tags:        ");
      paramContext.append(Helpers.safeStr(Build.TAGS));
      paramContext.append("\nBuild Time:        ");
      paramContext.append(Build.TIME);
      paramContext.append("\nBuild Type:        ");
      paramContext.append(Helpers.safeStr(Build.TYPE));
      paramContext.append("\nBuild User:        ");
      paramContext.append(Helpers.safeStr(Build.USER));
      paramContext.append("\nPhone Type:        ");
      paramContext.append(Helpers.safeStr((String)localObject8));
      paramContext.append("\nNetwork Type:      ");
      paramContext.append(Helpers.safeStr((String)localObject1));
      paramContext.append("\nSim State:         ");
      paramContext.append(Helpers.safeStr((String)localObject12));
      paramContext.append("\nSim Country:       ");
      paramContext.append(Helpers.safeStr((String)localObject14));
      paramContext.append("\nSim Operator:      ");
      paramContext.append(Helpers.safeStr(localObject13));
      paramContext.append("\nSim Operator Name: ");
      paramContext.append(Helpers.safeStr(localObject11));
      paramContext.append("\nNet Country:       ");
      paramContext.append(Helpers.safeStr(localObject10));
      paramContext.append("\nNet Operator:      ");
      paramContext.append(Helpers.safeStr(localObject9));
      paramContext.append("\nNet Operator Name: ");
      paramContext.append(Helpers.safeStr(localContext2));
      paramStringBuilder.append(paramContext.toString());
      return;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  private static void b(String paramString)
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
  
  public static void closeAllFiles()
  {
    Iterator localIterator = Helpers.emptyIfNull(_fileMap.keySet()).iterator();
    while (localIterator.hasNext()) {
      b((String)localIterator.next());
    }
  }
  
  public static void deleteFile(Context paramContext, String paramString)
  {
    try
    {
      b(paramString);
      h.a(paramContext).deleteFile(paramString);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static void dumpBundle(Bundle paramBundle)
  {
    if (Debug.getLevel() > 2) {
      return;
    }
    Debug.log(2, "-=- BUNDLE -=-");
    if (paramBundle == null)
    {
      Debug.log(2, "Bundle: null");
      return;
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("toString: ");
    ((StringBuilder)localObject1).append(paramBundle.toString());
    Debug.log(2, ((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("describeContents: ");
    ((StringBuilder)localObject1).append(paramBundle.describeContents());
    Debug.log(2, ((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("size: ");
    ((StringBuilder)localObject1).append(paramBundle.size());
    Debug.log(2, ((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("hasFileDescriptors: ");
    ((StringBuilder)localObject1).append(paramBundle.hasFileDescriptors());
    Debug.log(2, ((StringBuilder)localObject1).toString());
    localObject1 = Helpers.emptyIfNull(paramBundle.keySet()).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      String str = (String)((Iterator)localObject1).next();
      Object localObject2 = paramBundle.get(str);
      Object localObject3;
      if ((localObject2 instanceof Object[]))
      {
        localObject2 = (Object[])localObject2;
        int k = localObject2.length;
        int j = 0;
        int i = 0;
        while (j < k)
        {
          localObject3 = localObject2[j];
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Key: ");
          localStringBuilder.append(str);
          localStringBuilder.append("[");
          localStringBuilder.append(i);
          localStringBuilder.append("], Value: ");
          localStringBuilder.append(localObject3);
          Debug.log(2, localStringBuilder.toString());
          j += 1;
          i += 1;
        }
      }
      else
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Key: ");
        ((StringBuilder)localObject3).append(str);
        ((StringBuilder)localObject3).append(", Value: ");
        ((StringBuilder)localObject3).append(localObject2);
        Debug.log(2, ((StringBuilder)localObject3).toString());
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
    for (;;)
    {
      int j;
      int i;
      if (bool)
      {
        j = paramCursor.getPosition();
        int k = paramCursor.getColumnCount();
        i = 0;
        label56:
        if (i >= k) {}
      }
      try
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("[");
        ((StringBuilder)localObject).append(j);
        ((StringBuilder)localObject).append(", ");
        ((StringBuilder)localObject).append(i);
        ((StringBuilder)localObject).append(", ");
        ((StringBuilder)localObject).append(paramCursor.getColumnName(i));
        ((StringBuilder)localObject).append("] ");
        ((StringBuilder)localObject).append(paramCursor.getString(i));
        Debug.log(2, ((StringBuilder)localObject).toString());
        i += 1;
        break label56;
        localObject = paramCursor.getExtras();
        if (localObject != null) {
          dumpBundle((Bundle)localObject);
        }
        bool = paramCursor.moveToNext();
        continue;
        return;
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
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("toString: ");
    ((StringBuilder)localObject).append(paramIntent.toString());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("toURI: ");
    ((StringBuilder)localObject).append(paramIntent.toURI());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("describeContents: ");
    ((StringBuilder)localObject).append(paramIntent.describeContents());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getAction: ");
    ((StringBuilder)localObject).append(paramIntent.getAction());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getDataString: ");
    ((StringBuilder)localObject).append(paramIntent.getDataString());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getFlags: ");
    ((StringBuilder)localObject).append(paramIntent.getFlags());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getScheme: ");
    ((StringBuilder)localObject).append(paramIntent.getScheme());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getType: ");
    ((StringBuilder)localObject).append(paramIntent.getType());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getComponent: ");
    ((StringBuilder)localObject).append(paramIntent.getComponent());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getData: ");
    ((StringBuilder)localObject).append(paramIntent.getData());
    Debug.log(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("hasFileDescriptors: ");
    ((StringBuilder)localObject).append(paramIntent.hasFileDescriptors());
    Debug.log(2, ((StringBuilder)localObject).toString());
    if (paramIntent.getComponent() != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.describeContents: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().describeContents());
      Debug.log(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.flattenToShortString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().flattenToShortString());
      Debug.log(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.flattenToString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().flattenToString());
      Debug.log(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.getClassName: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().getClassName());
      Debug.log(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.getPackageName: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().getPackageName());
      Debug.log(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.getShortClassName: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().getShortClassName());
      Debug.log(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.toShortString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().toShortString());
      Debug.log(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ComponentName.toString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().toString());
      Debug.log(2, ((StringBuilder)localObject).toString());
    }
    localObject = Helpers.emptyIfNull(paramIntent.getCategories()).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Category: ");
      localStringBuilder.append(str);
      Debug.log(2, localStringBuilder.toString());
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
    str1 = str2;
    if (str2 == null) {
      str1 = "???";
    }
    paramStringBuilder.append(str1);
    a(paramThrowable, paramStringBuilder);
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(i.b(paramContext));
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    paramContext = localStringBuilder.toString();
    b(paramContext);
    return new File(paramContext).length();
  }
  
  public static String getTelephonyManagerNetworkTypeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Integer.toString(paramInt);
    case 15: 
      return "HSPA+";
    case 14: 
      return "EHRPD";
    case 13: 
      return "LTE";
    case 12: 
      return "EVDO_B";
    case 11: 
      return "IDEN";
    case 10: 
      return "HSPA";
    case 9: 
      return "HSUPA";
    case 8: 
      return "HSDPA";
    case 7: 
      return "1xRTT";
    case 6: 
      return "EVDO_A";
    case 5: 
      return "EVDO_0";
    case 4: 
      return "CDMA";
    case 3: 
      return "UMTS";
    case 2: 
      return "EDGE";
    case 1: 
      return "GPRS";
    }
    return "UNKNOWN";
  }
  
  public static String getTelephonyManagerPhoneTypeString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Integer.toString(paramInt);
    case 3: 
      return "SIP";
    case 2: 
      return "CDMA";
    case 1: 
      return "GSM";
    }
    return "None";
  }
  
  public static String getTelephonyManagerSimStateString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Integer.toString(paramInt);
    case 5: 
      return "READY";
    case 4: 
      return "NETWORK_LOCKED";
    case 3: 
      return "PUK_REQUIRED";
    case 2: 
      return "PIN_REQUIRED";
    case 1: 
      return "ABSENT";
    }
    return "UNKNOWN";
  }
  
  public static int httpUpload(Context paramContext, String paramString1, String paramString2)
  {
    int i;
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(i.b(paramContext));
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(paramString2);
      paramContext = ((StringBuilder)localObject).toString();
      b(paramContext);
      paramContext = new File(paramContext);
      paramString2 = new URL(paramString1);
      paramString1 = (HttpURLConnection)FirebasePerfUrlConnection.instrument(paramString2.openConnection());
      m.a(paramString2, paramString1);
      paramString1.setFixedLengthStreamingMode((int)paramContext.length());
      paramString1.setConnectTimeout(180000);
      paramString1.setReadTimeout(180000);
      paramString1.setAllowUserInteraction(false);
      paramString1.setDoOutput(true);
      paramString1.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
      paramString2 = paramString1.getOutputStream();
      localObject = new byte[40960];
      paramContext = new FileInputStream(paramContext);
      while (paramContext.available() > 0)
      {
        i = paramContext.read((byte[])localObject);
        if (i < 0) {
          break;
        }
        paramString2.write((byte[])localObject, 0, i);
      }
      paramContext.close();
      paramString2.flush();
      paramString2.close();
      i = paramString1.getResponseCode();
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    try
    {
      paramString1.disconnect();
      return i;
    }
    catch (Throwable paramContext) {}
    return 0;
    return i;
  }
  
  public static boolean isOnWifi(Context paramContext)
  {
    if (h.b(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
      return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return true;
  }
  
  public static void logDiagnostics(Context paramContext)
  {
    Object localObject2 = h.a(paramContext);
    if (Debug.getLevel() > 1) {
      return;
    }
    for (;;)
    {
      try
      {
        paramContext = ((Context)localObject2).getContentResolver();
        if (paramContext != null)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("DIAG: Development Settings Enabled: ");
          ((StringBuilder)localObject1).append(Settings.System.getInt(paramContext, "development_settings_enabled", -1));
          logSpecial(1, ((StringBuilder)localObject1).toString());
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("DIAG: Always finish activities: ");
          ((StringBuilder)localObject1).append(Settings.System.getInt(paramContext, "always_finish_activities", -1));
          logSpecial(1, ((StringBuilder)localObject1).toString());
        }
        localObject3 = (ActivityManager)((Context)localObject2).getSystemService("activity");
        localObject1 = null;
        paramContext = (Context)localObject1;
        if (localObject3 != null)
        {
          paramContext = ((ActivityManager)localObject3).getDeviceConfigurationInfo();
          if (paramContext != null)
          {
            localObject4 = new StringBuilder();
            ((StringBuilder)localObject4).append("DIAG: reqGlEsVersion:0x");
            ((StringBuilder)localObject4).append(Integer.toHexString(paramContext.reqGlEsVersion));
            ((StringBuilder)localObject4).append(", reqInputFeatures:");
            ((StringBuilder)localObject4).append(paramContext.reqInputFeatures);
            ((StringBuilder)localObject4).append(", reqKeyboardType:");
            ((StringBuilder)localObject4).append(paramContext.reqKeyboardType);
            ((StringBuilder)localObject4).append(", reqNavigation:");
            ((StringBuilder)localObject4).append(paramContext.reqNavigation);
            ((StringBuilder)localObject4).append(", reqTouchScreen:");
            ((StringBuilder)localObject4).append(paramContext.reqTouchScreen);
            logSpecial(1, ((StringBuilder)localObject4).toString());
          }
          paramContext = new StringBuilder();
          paramContext.append("DIAG: Memory classes: ");
          paramContext.append(Reflection._ActivityManager.getMemoryClass((ActivityManager)localObject3));
          paramContext.append("M / ");
          paramContext.append(Reflection._ActivityManager.getLargeMemoryClass((ActivityManager)localObject3));
          paramContext.append("M");
          logSpecial(1, paramContext.toString());
          localObject3 = ((ActivityManager)localObject3).getRunningAppProcesses();
          paramContext = (Context)localObject1;
          if (localObject3 != null)
          {
            paramContext = (Context)localObject1;
            if (((List)localObject3).size() > 0)
            {
              paramContext = new HashMap();
              localObject1 = ((List)localObject3).iterator();
              if (!((Iterator)localObject1).hasNext()) {
                break label786;
              }
              localObject3 = (String[])Helpers.emptyIfNull(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next()).pkgList);
              j = localObject3.length;
              i = 0;
              if (i < j)
              {
                paramContext.put(localObject3[i], null);
                i += 1;
                continue;
              }
              continue;
            }
          }
        }
        localObject1 = ((Context)localObject2).getPackageManager().getInstalledPackages(4096);
        Object localObject4 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        StringBuilder localStringBuilder = new StringBuilder(4096);
        Iterator localIterator = Helpers.emptyIfNull((Iterable)localObject1).iterator();
        int i = 0;
        if (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          localStringBuilder.setLength(0);
          long l1 = Reflection._PackageInfo.firstInstallTime(localPackageInfo);
          long l2 = Reflection._PackageInfo.lastUpdateTime(localPackageInfo);
          if ((paramContext == null) || (!paramContext.containsKey(localPackageInfo.packageName))) {
            break label789;
          }
          j = 1;
          int k = i + 1;
          if (j == 0) {
            break label794;
          }
          localObject1 = "Y";
          try
          {
            String str = localPackageInfo.packageName;
            i = localPackageInfo.versionCode;
            if (l1 <= 0L) {
              break label802;
            }
            localObject2 = ((SimpleDateFormat)localObject4).format(new Date(l1));
            if (l2 <= 0L) {
              break label810;
            }
            localObject3 = ((SimpleDateFormat)localObject4).format(new Date(l2));
            localStringBuilder.append(String.format("PKG[%04d] R: %s, N: %s, B: %d, I: %s, U: %s, P: ", new Object[] { Integer.valueOf(k), localObject1, str, Integer.valueOf(i), localObject2, localObject3 }));
            localObject1 = localPackageInfo.requestedPermissions;
            if (localObject1 != null)
            {
              int m = localObject1.length;
              i = 0;
              j = 1;
              if (i < m)
              {
                localObject2 = localObject1[i];
                if (j == 0) {
                  localStringBuilder.append(" | ");
                }
                localStringBuilder.append(((String)localObject2).replace("android.permission.", ""));
                i += 1;
                j = 0;
                continue;
              }
            }
            localStringBuilder.append(", V: ");
            localStringBuilder.append(localPackageInfo.versionName);
            logSpecial(1, localStringBuilder.toString());
            i = k;
          }
          catch (Throwable paramContext) {}
          Debug.ex(paramContext, false);
        }
      }
      catch (Throwable paramContext) {}
      return;
      label786:
      continue;
      label789:
      int j = 0;
      continue;
      label794:
      Object localObject1 = "n";
      continue;
      label802:
      localObject2 = "unknown";
      continue;
      label810:
      Object localObject3 = "unknown";
    }
  }
  
  public static void logDiagnosticsOnBackgroundThread(Context paramContext)
  {
    h.a(paramContext);
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
    Object localObject1 = h.a(paramContext);
    if ((localObject1 != null) && (Debug.getLevel() <= 1))
    {
      paramContext = new StringBuilder(320);
      try
      {
        Object localObject2 = new ActivityManager.MemoryInfo();
        localObject1 = (ActivityManager)((Context)localObject1).getSystemService("activity");
        ((ActivityManager)localObject1).getMemoryInfo((ActivityManager.MemoryInfo)localObject2);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("MEM: SysTotal: ");
        localStringBuilder.append(Reflection._ActivityManager_MemoryInfo.totalMem((ActivityManager.MemoryInfo)localObject2) / 1024L);
        localStringBuilder.append("K, SysFree: ");
        localStringBuilder.append(((ActivityManager.MemoryInfo)localObject2).availMem / 1024L);
        localStringBuilder.append("K, SysThreshold: ");
        localStringBuilder.append(((ActivityManager.MemoryInfo)localObject2).threshold / 1024L);
        localStringBuilder.append("K, SysLow: ");
        localStringBuilder.append(((ActivityManager.MemoryInfo)localObject2).lowMemory);
        paramContext.append(localStringBuilder.toString());
        localObject2 = Reflection._ActivityManager.getProcessMemoryInfo((ActivityManager)localObject1, new int[] { Process.myPid() });
        if ((localObject2 != null) && (localObject2.length > 0))
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(", Dalvik PD/PSS/SD: ");
          localStringBuilder.append(localObject2[0].dalvikPrivateDirty);
          localStringBuilder.append("K / ");
          localStringBuilder.append(localObject2[0].dalvikPss);
          localStringBuilder.append("K / ");
          localStringBuilder.append(localObject2[0].dalvikSharedDirty);
          localStringBuilder.append("K, Native PD/PSS/SD: ");
          localStringBuilder.append(localObject2[0].nativePrivateDirty);
          localStringBuilder.append("K / ");
          localStringBuilder.append(localObject2[0].nativePss);
          localStringBuilder.append("K / ");
          localStringBuilder.append(localObject2[0].nativeSharedDirty);
          localStringBuilder.append("K, Other PD/PSS/SD: ");
          localStringBuilder.append(localObject2[0].otherPrivateDirty);
          localStringBuilder.append("K / ");
          localStringBuilder.append(localObject2[0].otherPss);
          localStringBuilder.append("K / ");
          localStringBuilder.append(localObject2[0].otherSharedDirty);
          localStringBuilder.append("K, Total PD/PSS/SD: ");
          localStringBuilder.append(Reflection._Debug_MemoryInfo.getTotalPrivateDirty(localObject2[0]));
          localStringBuilder.append("K / ");
          localStringBuilder.append(Reflection._Debug_MemoryInfo.getTotalPss(localObject2[0]));
          localStringBuilder.append("K / ");
          localStringBuilder.append(Reflection._Debug_MemoryInfo.getTotalSharedDirty(localObject2[0]));
          localStringBuilder.append("K");
          paramContext.append(localStringBuilder.toString());
        }
        localObject1 = Reflection._ActivityManager.getMyMemoryState((ActivityManager)localObject1);
        if (localObject1 != null)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(", Importance: ");
          ((StringBuilder)localObject2).append(((ActivityManager.RunningAppProcessInfo)localObject1).importance);
          ((StringBuilder)localObject2).append(", Reason: ");
          ((StringBuilder)localObject2).append(Reflection._RunningAppProcessInfo.importanceReasonCode((ActivityManager.RunningAppProcessInfo)localObject1));
          ((StringBuilder)localObject2).append(", LRU: ");
          ((StringBuilder)localObject2).append(((ActivityManager.RunningAppProcessInfo)localObject1).lru);
          paramContext.append(((StringBuilder)localObject2).toString());
        }
      }
      catch (Throwable localThrowable)
      {
        Debug.ex(localThrowable, false);
      }
      logSpecial(1, paramContext.toString());
    }
  }
  
  public static void logSpecial(int paramInt, String paramString)
  {
    int i;
    if (aN) {
      i = 0;
    } else {
      i = 65536;
    }
    Debug.log(paramInt | i, paramString);
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
      new a(paramLong, paramRunnable, null).start();
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
    aN = paramBoolean;
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
    paramContext = null;
    if (!paramBoolean) {}
    for (;;)
    {
      try
      {
        b(paramString1);
        continue;
        paramContext = (FileOutputStream)_fileMap.get(paramString1);
        Object localObject = paramContext;
        if (paramContext == null)
        {
          paramContext = new File(paramString1);
          boolean bool1 = paramContext.exists();
          boolean bool2 = false;
          if (!bool1)
          {
            paramContext.createNewFile();
            i = 0;
            bool1 = bool2;
            if (i != 0)
            {
              bool1 = bool2;
              if (paramBoolean) {
                bool1 = true;
              }
            }
            localObject = new FileOutputStream(paramContext, bool1);
            _fileMap.put(paramString1, localObject);
          }
        }
        else
        {
          ((FileOutputStream)localObject).write(paramString2.getBytes("UTF-8"));
          return;
        }
      }
      catch (Throwable paramContext)
      {
        return;
      }
      int i = 1;
    }
  }
  
  private static class a
    extends Thread
  {
    private long aO;
    private Runnable aP;
    
    private a(long paramLong, Runnable paramRunnable)
    {
      this.aO = paramLong;
      this.aP = paramRunnable;
    }
    
    public void run()
    {
      try
      {
        Thread.sleep(this.aO);
        Thread.currentThread().setPriority(1);
        this.aP.run();
        return;
      }
      catch (Throwable localThrowable) {}
    }
  }
}
