package com.d.a;

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

public final class ba
{
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
  
  public static JSONArray a(Context paramContext)
  {
    Object localObject = d(paramContext);
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
        if (bb.a(17)) {
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
  public static JSONArray b(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 30	org/json/JSONArray
    //   5: dup
    //   6: invokespecial 34	org/json/JSONArray:<init>	()V
    //   9: astore 4
    //   11: aload_0
    //   12: invokestatic 139	com/d/a/ba:e	(Landroid/content/Context;)[Landroid/accounts/Account;
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
    //   39: new 54	org/json/JSONObject
    //   42: dup
    //   43: invokespecial 55	org/json/JSONObject:<init>	()V
    //   46: astore 7
    //   48: aload 7
    //   50: ldc -115
    //   52: aload 6
    //   54: getfield 146	android/accounts/Account:type	Ljava/lang/String;
    //   57: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   60: pop
    //   61: aload 7
    //   63: ldc -108
    //   65: aload 6
    //   67: getfield 150	android/accounts/Account:name	Ljava/lang/String;
    //   70: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   73: pop
    //   74: aload 4
    //   76: aload 7
    //   78: invokevirtual 134	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   81: pop
    //   82: iload_1
    //   83: iconst_1
    //   84: iadd
    //   85: istore_1
    //   86: goto -58 -> 28
    //   89: aload_0
    //   90: invokestatic 155	com/d/a/ar:i	(Landroid/content/Context;)Lorg/json/JSONArray;
    //   93: astore 5
    //   95: iload_2
    //   96: istore_1
    //   97: iload_1
    //   98: aload 5
    //   100: invokevirtual 156	org/json/JSONArray:length	()I
    //   103: if_icmpge +157 -> 260
    //   106: aload 5
    //   108: iload_1
    //   109: invokevirtual 160	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   112: astore 6
    //   114: aload 6
    //   116: ldc -94
    //   118: invokevirtual 166	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   121: astore 7
    //   123: aload 7
    //   125: ifnull +127 -> 252
    //   128: new 54	org/json/JSONObject
    //   131: dup
    //   132: invokespecial 55	org/json/JSONObject:<init>	()V
    //   135: astore 8
    //   137: aload 8
    //   139: ldc -115
    //   141: ldc -88
    //   143: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   146: pop
    //   147: aload 8
    //   149: ldc -108
    //   151: aload 7
    //   153: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   156: pop
    //   157: aload 8
    //   159: ldc -86
    //   161: aload 6
    //   163: ldc -84
    //   165: invokevirtual 175	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   168: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   171: pop
    //   172: aload 8
    //   174: ldc -79
    //   176: aload 6
    //   178: ldc -77
    //   180: invokevirtual 175	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   183: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   186: pop
    //   187: aload 8
    //   189: ldc -75
    //   191: aload 6
    //   193: ldc -73
    //   195: invokevirtual 175	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   198: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   201: pop
    //   202: aload 8
    //   204: ldc -71
    //   206: aload_0
    //   207: invokestatic 188	com/d/a/ar:d	(Landroid/content/Context;)Ljava/lang/String;
    //   210: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   213: pop
    //   214: aload 8
    //   216: ldc -66
    //   218: aload 6
    //   220: ldc -64
    //   222: invokevirtual 175	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   225: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   228: pop
    //   229: aload 8
    //   231: ldc -62
    //   233: aload 6
    //   235: ldc -60
    //   237: invokevirtual 175	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   240: invokevirtual 131	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   243: pop
    //   244: aload 4
    //   246: aload 8
    //   248: invokevirtual 134	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   251: pop
    //   252: iload_1
    //   253: iconst_1
    //   254: iadd
    //   255: istore_1
    //   256: goto -159 -> 97
    //   259: astore_0
    //   260: aload 4
    //   262: invokevirtual 156	org/json/JSONArray:length	()I
    //   265: ifle +6 -> 271
    //   268: aload 4
    //   270: areturn
    //   271: aconst_null
    //   272: areturn
    //   273: astore 6
    //   275: goto -193 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	278	0	paramContext	Context
    //   27	229	1	i	int
    //   1	95	2	j	int
    //   25	6	3	k	int
    //   9	260	4	localJSONArray	JSONArray
    //   15	92	5	localObject1	Object
    //   37	197	6	localJSONObject1	JSONObject
    //   273	1	6	localThrowable	Throwable
    //   46	106	7	localObject2	Object
    //   135	112	8	localJSONObject2	JSONObject
    // Exception table:
    //   from	to	target	type
    //   89	95	259	java/lang/Throwable
    //   97	123	259	java/lang/Throwable
    //   128	252	259	java/lang/Throwable
    //   39	82	273	java/lang/Throwable
  }
  
  public static Long[][] c(Context paramContext)
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
        if (bb.b(paramContext, "android.permission.GET_TASKS"))
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
  
  private static List d(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (!bb.a) {
      return localArrayList;
    }
    try
    {
      if (((bb.b(paramContext, "android.permission.ACCESS_FINE_LOCATION")) || (bb.b(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))) && ((bb.b) || (bb.c)))
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
  
  private static Account[] e(Context paramContext)
  {
    if (bb.b(paramContext, "android.permission.GET_ACCOUNTS")) {
      try
      {
        paramContext = AccountManager.get(paramContext).getAccounts();
        return paramContext;
      }
      catch (Throwable paramContext) {}
    }
    return null;
  }
}
