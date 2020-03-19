package com.tendcloud.tenddata;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class bb
{
  public bb() {}
  
  private static long a(String paramString)
  {
    long l = 1125899906842597L;
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      l = l * 131L + paramString.charAt(i);
      i += 1;
    }
    return l;
  }
  
  public static List a(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (!an.a) {
      return localArrayList;
    }
    try
    {
      if (((an.b(paramContext, "android.permission.ACCESS_FINE_LOCATION")) || (an.b(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))) && ((an.c) || (an.d)))
      {
        LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
        Iterator localIterator = localLocationManager.getProviders(true).iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          try
          {
            Object localObject = localLocationManager.getLastKnownLocation(str);
            if (localObject != null) {
              localArrayList.add(localObject);
            }
            localObject = PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0);
            localLocationManager.requestLocationUpdates(str, 300000L, 0.0F, (PendingIntent)localObject);
            localLocationManager.removeUpdates((PendingIntent)localObject);
          }
          catch (Throwable localThrowable) {}
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext) {}
  }
  
  public static String b(Context paramContext)
  {
    paramContext = a(paramContext);
    StringBuffer localStringBuffer1 = new StringBuffer();
    Iterator localIterator = paramContext.iterator();
    if (localIterator.hasNext())
    {
      Location localLocation = (Location)localIterator.next();
      StringBuffer localStringBuffer2 = localStringBuffer1.append(localLocation.getLatitude()).append(',').append(localLocation.getLongitude()).append(',');
      if (localLocation.hasAltitude())
      {
        paramContext = Double.valueOf(localLocation.getAltitude());
        label81:
        localStringBuffer2 = localStringBuffer2.append(paramContext).append(',').append(localLocation.getTime()).append(',');
        if (!localLocation.hasAccuracy()) {
          break label210;
        }
        paramContext = Float.valueOf(localLocation.getAccuracy());
        label121:
        localStringBuffer2 = localStringBuffer2.append(paramContext).append(',');
        if (!localLocation.hasBearing()) {
          break label216;
        }
        paramContext = Float.valueOf(localLocation.getBearing());
        label149:
        localStringBuffer2 = localStringBuffer2.append(paramContext).append(',');
        if (!localLocation.hasSpeed()) {
          break label222;
        }
      }
      label210:
      label216:
      label222:
      for (paramContext = Float.valueOf(localLocation.getSpeed());; paramContext = "")
      {
        localStringBuffer2.append(paramContext).append(',').append(localLocation.getProvider()).append(':');
        break;
        paramContext = "";
        break label81;
        paramContext = "";
        break label121;
        paramContext = "";
        break label149;
      }
    }
    return localStringBuffer1.toString();
  }
  
  public static JSONArray c(Context paramContext)
  {
    Object localObject = a(paramContext);
    paramContext = new JSONArray();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Location localLocation = (Location)((Iterator)localObject).next();
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("lat", localLocation.getLatitude());
        localJSONObject.put("lng", localLocation.getLongitude());
        localJSONObject.put("ts", localLocation.getTime());
        if (an.a(17)) {
          localJSONObject.put("elapsed", localLocation.getElapsedRealtimeNanos());
        }
        if (localLocation.hasAltitude()) {
          localJSONObject.put("altitude", localLocation.getAltitude());
        }
        if (localLocation.hasAccuracy()) {
          localJSONObject.put("accurate", localLocation.getAccuracy());
        }
        if (localLocation.hasBearing()) {
          localJSONObject.put("bearing", localLocation.getBearing());
        }
        if (localLocation.hasSpeed()) {
          localJSONObject.put("speed", localLocation.getSpeed());
        }
        localJSONObject.put("provider", localLocation.getProvider());
        paramContext.put(localJSONObject);
      }
      catch (Throwable localThrowable) {}
    }
    return paramContext;
  }
  
  /* Error */
  public static JSONArray d(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 190	org/json/JSONArray
    //   5: dup
    //   6: invokespecial 191	org/json/JSONArray:<init>	()V
    //   9: astore 4
    //   11: aload_0
    //   12: invokestatic 235	com/tendcloud/tenddata/bb:e	(Landroid/content/Context;)[Landroid/accounts/Account;
    //   15: astore 5
    //   17: aload 5
    //   19: ifnull +70 -> 89
    //   22: aload 5
    //   24: arraylength
    //   25: istore_3
    //   26: iconst_0
    //   27: istore_1
    //   28: iload_1
    //   29: iload_3
    //   30: if_icmpge +59 -> 89
    //   33: aload 5
    //   35: iload_1
    //   36: aaload
    //   37: astore 6
    //   39: new 193	org/json/JSONObject
    //   42: dup
    //   43: invokespecial 194	org/json/JSONObject:<init>	()V
    //   46: astore 7
    //   48: aload 7
    //   50: ldc -19
    //   52: aload 6
    //   54: getfield 242	android/accounts/Account:type	Ljava/lang/String;
    //   57: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   60: pop
    //   61: aload 7
    //   63: ldc -12
    //   65: aload 6
    //   67: getfield 246	android/accounts/Account:name	Ljava/lang/String;
    //   70: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   73: pop
    //   74: aload 4
    //   76: aload 7
    //   78: invokevirtual 231	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   81: pop
    //   82: iload_1
    //   83: iconst_1
    //   84: iadd
    //   85: istore_1
    //   86: goto -58 -> 28
    //   89: aload_0
    //   90: invokestatic 251	com/tendcloud/tenddata/ba:x	(Landroid/content/Context;)Lorg/json/JSONArray;
    //   93: astore 5
    //   95: iload_2
    //   96: istore_1
    //   97: iload_1
    //   98: aload 5
    //   100: invokevirtual 252	org/json/JSONArray:length	()I
    //   103: if_icmpge +170 -> 273
    //   106: aload 5
    //   108: iload_1
    //   109: invokevirtual 256	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   112: astore 6
    //   114: aload 6
    //   116: ldc_w 258
    //   119: invokevirtual 262	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   122: astore 7
    //   124: aload 7
    //   126: ifnull +139 -> 265
    //   129: new 193	org/json/JSONObject
    //   132: dup
    //   133: invokespecial 194	org/json/JSONObject:<init>	()V
    //   136: astore 8
    //   138: aload 8
    //   140: ldc -19
    //   142: ldc_w 264
    //   145: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   148: pop
    //   149: aload 8
    //   151: ldc -12
    //   153: aload 7
    //   155: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   158: pop
    //   159: aload 8
    //   161: ldc_w 266
    //   164: aload 6
    //   166: ldc_w 268
    //   169: invokevirtual 271	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   172: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   175: pop
    //   176: aload 8
    //   178: ldc_w 273
    //   181: aload 6
    //   183: ldc_w 275
    //   186: invokevirtual 271	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   189: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   192: pop
    //   193: aload 8
    //   195: ldc_w 277
    //   198: aload 6
    //   200: ldc_w 279
    //   203: invokevirtual 271	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   206: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   209: pop
    //   210: aload 8
    //   212: ldc_w 281
    //   215: aload_0
    //   216: invokestatic 284	com/tendcloud/tenddata/ba:o	(Landroid/content/Context;)Ljava/lang/String;
    //   219: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   222: pop
    //   223: aload 8
    //   225: ldc_w 286
    //   228: aload 6
    //   230: ldc_w 288
    //   233: invokevirtual 271	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   236: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   239: pop
    //   240: aload 8
    //   242: ldc_w 290
    //   245: aload 6
    //   247: ldc_w 292
    //   250: invokevirtual 271	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   253: invokevirtual 228	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   256: pop
    //   257: aload 4
    //   259: aload 8
    //   261: invokevirtual 231	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   264: pop
    //   265: iload_1
    //   266: iconst_1
    //   267: iadd
    //   268: istore_1
    //   269: goto -172 -> 97
    //   272: astore_0
    //   273: aload 4
    //   275: invokevirtual 252	org/json/JSONArray:length	()I
    //   278: ifle +6 -> 284
    //   281: aload 4
    //   283: areturn
    //   284: aconst_null
    //   285: areturn
    //   286: astore 6
    //   288: goto -206 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	291	0	paramContext	Context
    //   27	242	1	i	int
    //   1	95	2	j	int
    //   25	6	3	k	int
    //   9	273	4	localJSONArray	JSONArray
    //   15	92	5	localObject1	Object
    //   37	209	6	localJSONObject1	JSONObject
    //   286	1	6	localThrowable	Throwable
    //   46	108	7	localObject2	Object
    //   136	124	8	localJSONObject2	JSONObject
    // Exception table:
    //   from	to	target	type
    //   89	95	272	java/lang/Throwable
    //   97	124	272	java/lang/Throwable
    //   129	265	272	java/lang/Throwable
    //   39	82	286	java/lang/Throwable
  }
  
  public static Account[] e(Context paramContext)
  {
    if (an.b(paramContext, "android.permission.GET_ACCOUNTS")) {
      try
      {
        paramContext = AccountManager.get(paramContext).getAccounts();
        return paramContext;
      }
      catch (Throwable paramContext) {}
    }
    return null;
  }
  
  public static Long[][] f(Context paramContext)
  {
    arrayOfLong; = new Long[3][];
    try
    {
      Object localObject2 = (ActivityManager)paramContext.getSystemService("activity");
      Object localObject1 = paramContext.getPackageManager();
      HashSet localHashSet = new HashSet();
      localHashSet.add(paramContext.getPackageName());
      try
      {
        Object localObject3 = new HashSet();
        if (an.b(paramContext, "android.permission.GET_TASKS"))
        {
          paramContext = ((ActivityManager)localObject2).getRecentTasks(10, 1);
          if (paramContext != null)
          {
            paramContext = paramContext.iterator();
            while (paramContext.hasNext())
            {
              Object localObject4 = ((ActivityManager.RecentTaskInfo)paramContext.next()).baseIntent.getComponent();
              if (localObject4 != null)
              {
                localObject4 = ((ComponentName)localObject4).getPackageName();
                if (localHashSet.add(localObject4)) {
                  ((Set)localObject3).add(Long.valueOf(a((String)localObject4)));
                }
              }
            }
          }
        }
        try
        {
          for (;;)
          {
            localObject2 = ((ActivityManager)localObject2).getRunningAppProcesses();
            paramContext = new HashSet();
            if (localObject2 == null) {
              break;
            }
            localObject2 = ((List)localObject2).iterator();
            while (((Iterator)localObject2).hasNext())
            {
              localObject3 = ((ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next()).processName;
              try
              {
                if (((PackageManager)localObject1).getLaunchIntentForPackage((String)localObject3) != null) {
                  paramContext.add(Long.valueOf(a((String)localObject3)));
                }
              }
              catch (Throwable localThrowable) {}
            }
            arrayOfLong;[0] = new Long[localThrowable.size()];
            arrayOfLong;[0] = ((Long[])localThrowable.toArray(arrayOfLong;[0]));
          }
          arrayOfLong;[1] = new Long[paramContext.size()];
          arrayOfLong;[1] = ((Long[])paramContext.toArray(arrayOfLong;[1]));
        }
        catch (Throwable paramContext)
        {
          for (;;) {}
        }
      }
      catch (Throwable paramContext) {}
      return arrayOfLong;;
    }
    catch (Throwable paramContext)
    {
      try
      {
        localObject1 = ((PackageManager)localObject1).getInstalledApplications(0);
        paramContext = new HashSet();
        if (localObject1 != null)
        {
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
            if (((((ApplicationInfo)localObject2).flags & 0x1) <= 0) && (!localHashSet.contains(((ApplicationInfo)localObject2).packageName))) {
              paramContext.add(Long.valueOf(a(((ApplicationInfo)localObject2).packageName)));
            }
          }
        }
        arrayOfLong;[2] = new Long[paramContext.size()];
        arrayOfLong;[2] = ((Long[])paramContext.toArray(arrayOfLong;[2]));
        return arrayOfLong;;
      }
      catch (Throwable paramContext) {}
      paramContext = paramContext;
      return arrayOfLong;;
    }
  }
  
  public static List g(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      Object localObject = paramContext.getInstalledApplications(0);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          localArrayList.add(localApplicationInfo.packageName);
          try
          {
            localArrayList.add(an.b(paramContext.getApplicationLabel(localApplicationInfo).toString().getBytes()));
            if ((localApplicationInfo.flags & 0x1) > 0) {
              localArrayList.add("1");
            }
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              localArrayList.add("");
            }
            localArrayList.add("0");
          }
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext) {}
  }
}
