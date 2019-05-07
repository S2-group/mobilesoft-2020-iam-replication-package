package com.glympse.android.hal;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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

public final class DebugBase
{
  public static HashMap<String, FileOutputStream> a = new HashMap();
  private static boolean b;
  
  public static int a(Context paramContext, String paramString1, String paramString2)
  {
    int i;
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(bu.a(paramContext));
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(paramString2);
      paramContext = ((StringBuilder)localObject).toString();
      c(paramContext);
      paramContext = new File(paramContext);
      paramString2 = new URL(paramString1);
      paramString1 = (HttpURLConnection)paramString2.openConnection();
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
  
  public static long a(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(bu.a(paramContext));
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    paramContext = localStringBuilder.toString();
    c(paramContext);
    return new File(paramContext).length();
  }
  
  public static void a()
  {
    try
    {
      Iterator localIterator = a.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((FileOutputStream)a.get(str)).flush();
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public static void a(Context paramContext, StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(bu.a(bt.a(paramContext)));
  }
  
  public static void a(Intent paramIntent)
  {
    if (Debug.a() > 2) {
      return;
    }
    Debug.a(2, "-=- INTENT -=-");
    if (paramIntent == null)
    {
      Debug.a(2, "Intent: null");
      return;
    }
    Object localObject = new StringBuilder("toString: ");
    ((StringBuilder)localObject).append(paramIntent.toString());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("toURI: ");
    ((StringBuilder)localObject).append(paramIntent.toURI());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("describeContents: ");
    ((StringBuilder)localObject).append(paramIntent.describeContents());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("getAction: ");
    ((StringBuilder)localObject).append(paramIntent.getAction());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("getDataString: ");
    ((StringBuilder)localObject).append(paramIntent.getDataString());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("getFlags: ");
    ((StringBuilder)localObject).append(paramIntent.getFlags());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("getScheme: ");
    ((StringBuilder)localObject).append(paramIntent.getScheme());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("getType: ");
    ((StringBuilder)localObject).append(paramIntent.getType());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("getComponent: ");
    ((StringBuilder)localObject).append(paramIntent.getComponent());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("getData: ");
    ((StringBuilder)localObject).append(paramIntent.getData());
    Debug.a(2, ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("hasFileDescriptors: ");
    ((StringBuilder)localObject).append(paramIntent.hasFileDescriptors());
    Debug.a(2, ((StringBuilder)localObject).toString());
    if (paramIntent.getComponent() != null)
    {
      localObject = new StringBuilder("ComponentName.describeContents: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().describeContents());
      Debug.a(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder("ComponentName.flattenToShortString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().flattenToShortString());
      Debug.a(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder("ComponentName.flattenToString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().flattenToString());
      Debug.a(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder("ComponentName.getClassName: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().getClassName());
      Debug.a(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder("ComponentName.getPackageName: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().getPackageName());
      Debug.a(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder("ComponentName.getShortClassName: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().getShortClassName());
      Debug.a(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder("ComponentName.toShortString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().toShortString());
      Debug.a(2, ((StringBuilder)localObject).toString());
      localObject = new StringBuilder("ComponentName.toString: ");
      ((StringBuilder)localObject).append(paramIntent.getComponent().toString());
      Debug.a(2, ((StringBuilder)localObject).toString());
    }
    localObject = bf.a(paramIntent.getCategories()).iterator();
    while (((Iterator)localObject).hasNext()) {
      Debug.a(2, "Category: ".concat(String.valueOf((String)((Iterator)localObject).next())));
    }
    a(paramIntent.getExtras());
  }
  
  private static void a(Bundle paramBundle)
  {
    if (Debug.a() > 2) {
      return;
    }
    Debug.a(2, "-=- BUNDLE -=-");
    if (paramBundle == null)
    {
      Debug.a(2, "Bundle: null");
      return;
    }
    Object localObject1 = new StringBuilder("toString: ");
    ((StringBuilder)localObject1).append(paramBundle.toString());
    Debug.a(2, ((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder("describeContents: ");
    ((StringBuilder)localObject1).append(paramBundle.describeContents());
    Debug.a(2, ((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder("size: ");
    ((StringBuilder)localObject1).append(paramBundle.size());
    Debug.a(2, ((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder("hasFileDescriptors: ");
    ((StringBuilder)localObject1).append(paramBundle.hasFileDescriptors());
    Debug.a(2, ((StringBuilder)localObject1).toString());
    localObject1 = bf.a(paramBundle.keySet()).iterator();
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
          StringBuilder localStringBuilder = new StringBuilder("Key: ");
          localStringBuilder.append(str);
          localStringBuilder.append("[");
          localStringBuilder.append(i);
          localStringBuilder.append("], Value: ");
          localStringBuilder.append(localObject3);
          Debug.a(2, localStringBuilder.toString());
          j += 1;
          i += 1;
        }
      }
      else
      {
        localObject3 = new StringBuilder("Key: ");
        ((StringBuilder)localObject3).append(str);
        ((StringBuilder)localObject3).append(", Value: ");
        ((StringBuilder)localObject3).append(localObject2);
        Debug.a(2, ((StringBuilder)localObject3).toString());
      }
    }
  }
  
  public static void a(Runnable paramRunnable)
  {
    try
    {
      new a(paramRunnable, (byte)0).start();
      return;
    }
    catch (Throwable paramRunnable) {}
  }
  
  public static void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    Object localObject1 = null;
    if (!paramBoolean) {}
    for (;;)
    {
      try
      {
        c(paramString1);
        continue;
        localObject1 = (FileOutputStream)a.get(paramString1);
        Object localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject1 = new File(paramString1);
          boolean bool1 = ((File)localObject1).exists();
          boolean bool2 = false;
          if (!bool1)
          {
            ((File)localObject1).createNewFile();
            i = 0;
            bool1 = bool2;
            if (i != 0)
            {
              bool1 = bool2;
              if (paramBoolean) {
                bool1 = true;
              }
            }
            localObject2 = new FileOutputStream((File)localObject1, bool1);
            a.put(paramString1, localObject2);
          }
        }
        else
        {
          ((FileOutputStream)localObject2).write(paramString2.getBytes("UTF-8"));
          return;
        }
      }
      catch (Throwable paramString1)
      {
        return;
      }
      int i = 1;
    }
  }
  
  public static void a(Throwable paramThrowable, StringBuilder paramStringBuilder)
  {
    String str2 = paramThrowable.toString();
    String str1 = paramThrowable.toString();
    paramStringBuilder.append("\nException name:    ");
    Object localObject = str2;
    if (str2 == null) {
      localObject = "???";
    }
    paramStringBuilder.append((String)localObject);
    paramStringBuilder.append("\nException what:    ");
    localObject = str1;
    if (str1 == null) {
      localObject = "???";
    }
    paramStringBuilder.append((String)localObject);
    paramStringBuilder.append("\n\nThread Crashed:\n");
    for (int i = 1; paramThrowable != null; i = 0)
    {
      if (i == 0) {
        paramStringBuilder.append("Caused by: ");
      }
      paramStringBuilder.append(paramThrowable.toString());
      paramStringBuilder.append('\n');
      localObject = paramThrowable.getStackTrace();
      int j = localObject.length;
      i = 0;
      while (i < j)
      {
        str1 = localObject[i];
        paramStringBuilder.append("\tat ");
        paramStringBuilder.append(str1.toString());
        paramStringBuilder.append('\n');
        i += 1;
      }
      paramThrowable = paramThrowable.getCause();
    }
  }
  
  public static void a(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
    {
      Log.e("GlympseAPI", paramString);
      return;
    }
    Log.i("GlympseAPI", paramString);
  }
  
  public static boolean a(Context paramContext)
  {
    if (bt.a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
      return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return true;
  }
  
  public static boolean a(String paramString)
  {
    return paramString.contains("com.glympse");
  }
  
  public static void b()
  {
    Object localObject2 = null;
    Object localObject3 = bt.a(null);
    if (Debug.a() > 1) {
      return;
    }
    for (;;)
    {
      try
      {
        Object localObject1 = ((Context)localObject3).getContentResolver();
        if (localObject1 != null)
        {
          localObject4 = new StringBuilder("DIAG: Development Settings Enabled: ");
          ((StringBuilder)localObject4).append(Settings.System.getInt((ContentResolver)localObject1, "development_settings_enabled", -1));
          b(((StringBuilder)localObject4).toString());
          localObject4 = new StringBuilder("DIAG: Always finish activities: ");
          ((StringBuilder)localObject4).append(Settings.System.getInt((ContentResolver)localObject1, "always_finish_activities", -1));
          b(((StringBuilder)localObject4).toString());
        }
        localObject4 = (ActivityManager)((Context)localObject3).getSystemService("activity");
        localObject1 = localObject2;
        if (localObject4 != null)
        {
          localObject1 = ((ActivityManager)localObject4).getDeviceConfigurationInfo();
          if (localObject1 != null)
          {
            localObject5 = new StringBuilder("DIAG: reqGlEsVersion:0x");
            ((StringBuilder)localObject5).append(Integer.toHexString(((ConfigurationInfo)localObject1).reqGlEsVersion));
            ((StringBuilder)localObject5).append(", reqInputFeatures:");
            ((StringBuilder)localObject5).append(((ConfigurationInfo)localObject1).reqInputFeatures);
            ((StringBuilder)localObject5).append(", reqKeyboardType:");
            ((StringBuilder)localObject5).append(((ConfigurationInfo)localObject1).reqKeyboardType);
            ((StringBuilder)localObject5).append(", reqNavigation:");
            ((StringBuilder)localObject5).append(((ConfigurationInfo)localObject1).reqNavigation);
            ((StringBuilder)localObject5).append(", reqTouchScreen:");
            ((StringBuilder)localObject5).append(((ConfigurationInfo)localObject1).reqTouchScreen);
            b(((StringBuilder)localObject5).toString());
          }
          localObject1 = new StringBuilder("DIAG: Memory classes: ");
          ((StringBuilder)localObject1).append(bj.a.a((ActivityManager)localObject4));
          ((StringBuilder)localObject1).append("M / ");
          ((StringBuilder)localObject1).append(bj.a.b((ActivityManager)localObject4));
          ((StringBuilder)localObject1).append("M");
          b(((StringBuilder)localObject1).toString());
          localObject4 = ((ActivityManager)localObject4).getRunningAppProcesses();
          localObject1 = localObject2;
          if (localObject4 != null)
          {
            localObject1 = localObject2;
            if (((List)localObject4).size() > 0)
            {
              localObject1 = new HashMap();
              localObject2 = ((List)localObject4).iterator();
              if (!((Iterator)localObject2).hasNext()) {
                break label779;
              }
              localObject4 = (String[])bf.a(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next()).pkgList);
              j = localObject4.length;
              i = 0;
              if (i < j)
              {
                ((HashMap)localObject1).put(localObject4[i], null);
                i += 1;
                continue;
              }
              continue;
            }
          }
        }
        localObject2 = ((Context)localObject3).getPackageManager().getInstalledPackages(4096);
        Object localObject5 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        StringBuilder localStringBuilder = new StringBuilder(4096);
        Iterator localIterator = bf.a((Iterable)localObject2).iterator();
        int i = 0;
        if (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          localStringBuilder.setLength(0);
          long l1 = bj.g.a(localPackageInfo);
          long l2 = bj.g.b(localPackageInfo);
          if ((localObject1 == null) || (!((HashMap)localObject1).containsKey(localPackageInfo.packageName))) {
            break label782;
          }
          j = 1;
          int k = i + 1;
          if (j == 0) {
            break label787;
          }
          localObject2 = "Y";
          String str = localPackageInfo.packageName;
          i = localPackageInfo.versionCode;
          if (l1 <= 0L) {
            break label795;
          }
          localObject3 = ((SimpleDateFormat)localObject5).format(new Date(l1));
          if (l2 <= 0L) {
            break label803;
          }
          localObject4 = ((SimpleDateFormat)localObject5).format(new Date(l2));
          localStringBuilder.append(String.format("PKG[%04d] R: %s, N: %s, B: %d, I: %s, U: %s, P: ", new Object[] { Integer.valueOf(k), localObject2, str, Integer.valueOf(i), localObject3, localObject4 }));
          localObject2 = localPackageInfo.requestedPermissions;
          if (localObject2 != null)
          {
            int m = localObject2.length;
            i = 0;
            j = 1;
            if (i < m)
            {
              localObject3 = localObject2[i];
              if (j == 0) {
                localStringBuilder.append(" | ");
              }
              localStringBuilder.append(((String)localObject3).replace("android.permission.", ""));
              i += 1;
              j = 0;
              continue;
            }
          }
          localStringBuilder.append(", V: ");
          localStringBuilder.append(localPackageInfo.versionName);
          b(localStringBuilder.toString());
          i = k;
          continue;
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        Debug.a(localThrowable, false);
        return;
      }
      label779:
      continue;
      label782:
      int j = 0;
      continue;
      label787:
      localObject2 = "n";
      continue;
      label795:
      localObject3 = "unknown";
      continue;
      label803:
      Object localObject4 = "unknown";
    }
  }
  
  public static void b(Context paramContext, StringBuilder paramStringBuilder)
  {
    localObject4 = bt.a(paramContext);
    try
    {
      paramContext = ((Context)localObject4).getPackageManager().getPackageInfo(((Context)localObject4).getPackageName(), 0);
      paramStringBuilder.append("\nApp Package:       ");
      paramStringBuilder.append(paramContext.packageName);
      paramStringBuilder.append("\nApp Version:       ");
      paramStringBuilder.append(paramContext.versionName);
      paramStringBuilder.append("\nApp Build:         ");
      paramStringBuilder.append(paramContext.versionCode);
      localObject18 = null;
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
          Object localObject8;
          Object localObject9;
          Object localObject10;
          Object localObject15;
          Object localObject17;
          Object localObject16;
          Object localObject14;
          Object localObject13;
          Object localObject12;
          Object localObject11;
          continue;
          Object localObject2;
          if (localObject5 == null)
          {
            paramContext = null;
            continue;
            localObject3 = "SIP";
            continue;
            localObject3 = "CDMA";
            continue;
            localObject3 = "GSM";
            continue;
            paramContext = "HSPA+";
            continue;
            paramContext = "EHRPD";
            continue;
            paramContext = "LTE";
            continue;
            paramContext = "EVDO_B";
            continue;
            paramContext = "IDEN";
            continue;
            paramContext = "HSPA";
            continue;
            paramContext = "HSUPA";
            continue;
            paramContext = "HSDPA";
            continue;
            paramContext = "1xRTT";
            continue;
            paramContext = "EVDO_A";
            continue;
            paramContext = "EVDO_0";
            continue;
            paramContext = "CDMA";
            continue;
            paramContext = "UMTS";
            continue;
            paramContext = "EDGE";
            continue;
            paramContext = "GPRS";
            continue;
            localObject2 = "READY";
            continue;
            localObject2 = "NETWORK_LOCKED";
            continue;
            localObject2 = "PUK_REQUIRED";
            continue;
            localObject2 = "PIN_REQUIRED";
            continue;
            localObject2 = "ABSENT";
          }
        }
      }
      paramContext = ((Locale)localObject5).getCountry();
      localObject6 = new StringBuilder();
      localObject3 = localObject1;
      if (bf.m((String)localObject1)) {
        localObject3 = "??";
      }
      ((StringBuilder)localObject6).append((String)localObject3);
      ((StringBuilder)localObject6).append("-");
      localObject1 = paramContext;
      if (bf.m(paramContext)) {
        localObject1 = "??";
      }
      ((StringBuilder)localObject6).append((String)localObject1);
      paramContext = ((StringBuilder)localObject6).toString();
      if (localObject5 != null) {}
      try
      {
        str = ((Locale)localObject5).getDisplayName();
        localContext = paramContext;
      }
      catch (Throwable localThrowable1)
      {
        String str;
        Context localContext;
        for (;;) {}
      }
      paramContext = null;
      str = null;
      localContext = paramContext;
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
        label361:
        label454:
        label595:
        label691:
        label777:
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
        localObject5 = paramContext.getSimCountryIso();
      }
      catch (Throwable localThrowable5)
      {
        for (;;) {}
      }
      localObject5 = null;
      try
      {
        localObject6 = paramContext.getSimOperator();
      }
      catch (Throwable localThrowable6)
      {
        for (;;) {}
      }
      localObject6 = null;
      try
      {
        localObject7 = paramContext.getSimOperatorName();
      }
      catch (Throwable localThrowable7)
      {
        for (;;) {}
      }
      localObject7 = null;
      try
      {
        localObject8 = paramContext.getNetworkCountryIso();
      }
      catch (Throwable localThrowable8)
      {
        for (;;) {}
      }
      localObject8 = null;
      try
      {
        localObject9 = paramContext.getNetworkOperator();
      }
      catch (Throwable localThrowable9)
      {
        for (;;) {}
      }
      localObject9 = null;
      try
      {
        localObject4 = paramContext.getNetworkOperatorName();
      }
      catch (Throwable paramContext)
      {
        for (;;) {}
      }
      localObject4 = null;
      break label361;
      localObject4 = null;
      localObject5 = localObject4;
      localObject6 = localObject5;
      localObject7 = localObject6;
      localObject8 = localObject7;
      localObject9 = localObject8;
      i = -1;
      j = -1;
      k = -1;
      if (-1 != i) {}
      switch (i)
      {
      default: 
      case 0: 
        try
        {
          localObject3 = Integer.toString(i);
        }
        catch (Throwable paramContext)
        {
          for (;;) {}
        }
        localObject3 = "None";
        break label454;
        localObject10 = null;
        localObject1 = localObject10;
        localObject15 = localObject18;
        localObject17 = localObject5;
        localObject16 = localObject6;
        localObject14 = localObject7;
        localObject13 = localObject8;
        localObject12 = localObject9;
        localObject11 = localObject4;
        break label777;
        localObject3 = null;
        if (-1 != j) {}
        switch (j)
        {
        default: 
        case 0: 
          try
          {
            paramContext = Integer.toString(j);
          }
          catch (Throwable paramContext)
          {
            for (;;) {}
          }
          paramContext = "UNKNOWN";
          break label595;
          localObject1 = null;
          localObject15 = localObject18;
          localObject10 = localObject3;
          localObject17 = localObject5;
          localObject16 = localObject6;
          localObject14 = localObject7;
          localObject13 = localObject8;
          localObject12 = localObject9;
          localObject11 = localObject4;
          break label777;
          paramContext = null;
          localObject15 = localObject18;
          localObject10 = localObject3;
          localObject1 = paramContext;
          localObject17 = localObject5;
          localObject16 = localObject6;
          localObject14 = localObject7;
          localObject13 = localObject8;
          localObject12 = localObject9;
          localObject11 = localObject4;
          if (-1 != k) {}
          switch (k)
          {
          default: 
          case 0: 
            try
            {
              localObject1 = Integer.toString(k);
              break label691;
              localObject1 = "UNKNOWN";
              localObject15 = localObject1;
              localObject10 = localObject3;
              localObject1 = paramContext;
              localObject17 = localObject5;
              localObject16 = localObject6;
              localObject14 = localObject7;
              localObject13 = localObject8;
              localObject12 = localObject9;
              localObject11 = localObject4;
            }
            catch (Throwable localThrowable10)
            {
              for (;;)
              {
                localObject15 = localObject18;
                localObject10 = localObject3;
                localObject2 = paramContext;
                localObject17 = localObject5;
                localObject16 = localObject6;
                localObject14 = localObject7;
                localObject13 = localObject8;
                localObject12 = localObject9;
                localObject11 = localObject4;
              }
            }
            localObject11 = null;
            localObject10 = localObject11;
            localObject1 = localObject10;
            paramContext = (Context)localObject1;
            localObject3 = paramContext;
            localObject4 = localObject3;
            localObject5 = localObject4;
            localObject12 = localObject5;
            localObject13 = localObject5;
            localObject14 = localObject4;
            localObject16 = localObject3;
            localObject17 = paramContext;
            localObject15 = localObject18;
            paramContext = new StringBuilder("\nLocale Name:       ");
            paramContext.append(bf.a(str));
            paramContext.append("\nLocale Code:       ");
            paramContext.append(bf.a(localContext));
            paramContext.append("\nRelease Ver:       ");
            paramContext.append(bf.a(Build.VERSION.RELEASE));
            paramContext.append("\nSDK Ver:           ");
            paramContext.append(bf.a(Build.VERSION.SDK));
            paramContext.append("\nIncremental Ver:   ");
            paramContext.append(bf.a(Build.VERSION.INCREMENTAL));
            paramContext.append("\nBuild Brand:       ");
            paramContext.append(bf.a(Build.BRAND));
            paramContext.append("\nBuild Model:       ");
            paramContext.append(bf.a(Build.MODEL));
            paramContext.append("\nBuild Board:       ");
            paramContext.append(bf.a(Build.BOARD));
            paramContext.append("\nBuild Device:      ");
            paramContext.append(bf.a(Build.DEVICE));
            paramContext.append("\nBuild Display:     ");
            paramContext.append(bf.a(Build.DISPLAY));
            paramContext.append("\nBuild Fingerprint: ");
            paramContext.append(bf.a(Build.FINGERPRINT));
            paramContext.append("\nBuild Host:        ");
            paramContext.append(bf.a(Build.HOST));
            paramContext.append("\nBuild ID:          ");
            paramContext.append(bf.a(Build.ID));
            paramContext.append("\nBuild Product:     ");
            paramContext.append(bf.a(Build.PRODUCT));
            paramContext.append("\nBuild Tags:        ");
            paramContext.append(bf.a(Build.TAGS));
            paramContext.append("\nBuild Time:        ");
            paramContext.append(Build.TIME);
            paramContext.append("\nBuild Type:        ");
            paramContext.append(bf.a(Build.TYPE));
            paramContext.append("\nBuild User:        ");
            paramContext.append(bf.a(Build.USER));
            paramContext.append("\nPhone Type:        ");
            paramContext.append(bf.a(localObject10));
            paramContext.append("\nNetwork Type:      ");
            paramContext.append(bf.a((String)localObject1));
            paramContext.append("\nSim State:         ");
            paramContext.append(bf.a(localObject15));
            paramContext.append("\nSim Country:       ");
            paramContext.append(bf.a((String)localObject17));
            paramContext.append("\nSim Operator:      ");
            paramContext.append(bf.a(localObject16));
            paramContext.append("\nSim Operator Name: ");
            paramContext.append(bf.a(localObject14));
            paramContext.append("\nNet Country:       ");
            paramContext.append(bf.a(localObject13));
            paramContext.append("\nNet Operator:      ");
            paramContext.append(bf.a(localObject12));
            paramContext.append("\nNet Operator Name: ");
            paramContext.append(bf.a(localObject11));
            paramStringBuilder.append(paramContext.toString());
            return;
          }
          break;
        }
        break;
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  private static void b(String paramString)
  {
    int i;
    if (b) {
      i = 0;
    } else {
      i = 65536;
    }
    Debug.a(i | 0x1, paramString);
  }
  
  public static void c()
  {
    Object localObject1 = bt.a(null);
    if ((localObject1 != null) && (Debug.a() <= 1))
    {
      StringBuilder localStringBuilder1 = new StringBuilder(320);
      try
      {
        Object localObject2 = new ActivityManager.MemoryInfo();
        localObject1 = (ActivityManager)((Context)localObject1).getSystemService("activity");
        ((ActivityManager)localObject1).getMemoryInfo((ActivityManager.MemoryInfo)localObject2);
        StringBuilder localStringBuilder2 = new StringBuilder("MEM: SysTotal: ");
        localStringBuilder2.append(bj.b.a((ActivityManager.MemoryInfo)localObject2) / 1024L);
        localStringBuilder2.append("K, SysFree: ");
        localStringBuilder2.append(((ActivityManager.MemoryInfo)localObject2).availMem / 1024L);
        localStringBuilder2.append("K, SysThreshold: ");
        localStringBuilder2.append(((ActivityManager.MemoryInfo)localObject2).threshold / 1024L);
        localStringBuilder2.append("K, SysLow: ");
        localStringBuilder2.append(((ActivityManager.MemoryInfo)localObject2).lowMemory);
        localStringBuilder1.append(localStringBuilder2.toString());
        localObject2 = bj.a.a((ActivityManager)localObject1, new int[] { Process.myPid() });
        if ((localObject2 != null) && (localObject2.length > 0))
        {
          localStringBuilder2 = new StringBuilder(", Dalvik PD/PSS/SD: ");
          localStringBuilder2.append(localObject2[0].dalvikPrivateDirty);
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(localObject2[0].dalvikPss);
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(localObject2[0].dalvikSharedDirty);
          localStringBuilder2.append("K, Native PD/PSS/SD: ");
          localStringBuilder2.append(localObject2[0].nativePrivateDirty);
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(localObject2[0].nativePss);
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(localObject2[0].nativeSharedDirty);
          localStringBuilder2.append("K, Other PD/PSS/SD: ");
          localStringBuilder2.append(localObject2[0].otherPrivateDirty);
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(localObject2[0].otherPss);
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(localObject2[0].otherSharedDirty);
          localStringBuilder2.append("K, Total PD/PSS/SD: ");
          localStringBuilder2.append(bj.e.b(localObject2[0]));
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(bj.e.a(localObject2[0]));
          localStringBuilder2.append("K / ");
          localStringBuilder2.append(bj.e.c(localObject2[0]));
          localStringBuilder2.append("K");
          localStringBuilder1.append(localStringBuilder2.toString());
        }
        localObject1 = bj.a.c((ActivityManager)localObject1);
        if (localObject1 != null)
        {
          localObject2 = new StringBuilder(", Importance: ");
          ((StringBuilder)localObject2).append(((ActivityManager.RunningAppProcessInfo)localObject1).importance);
          ((StringBuilder)localObject2).append(", Reason: ");
          ((StringBuilder)localObject2).append(bj.h.a((ActivityManager.RunningAppProcessInfo)localObject1));
          ((StringBuilder)localObject2).append(", LRU: ");
          ((StringBuilder)localObject2).append(((ActivityManager.RunningAppProcessInfo)localObject1).lru);
          localStringBuilder1.append(((StringBuilder)localObject2).toString());
        }
      }
      catch (Throwable localThrowable)
      {
        Debug.a(localThrowable, false);
      }
      b(localStringBuilder1.toString());
    }
  }
  
  private static void c(String paramString)
  {
    paramString = (FileOutputStream)a.remove(paramString);
    if (paramString != null) {}
    try
    {
      paramString.flush();
      paramString.close();
      return;
    }
    catch (Throwable paramString) {}
    return;
  }
  
  private static class a
    extends Thread
  {
    private long a = 5000L;
    private Runnable b;
    
    private a(Runnable paramRunnable)
    {
      this.b = paramRunnable;
    }
    
    public void run()
    {
      try
      {
        Thread.sleep(this.a);
        Thread.currentThread().setPriority(1);
        this.b.run();
        return;
      }
      catch (Throwable localThrowable) {}
    }
  }
}
