package com.forter.mobile.fortersdk.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import com.forter.mobile.fortersdk.api.ForterClient;
import com.forter.mobile.fortersdk.models.e;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.json.JSONObject;

public abstract class m
{
  public static final String a = "FAILURE";
  
  public m() {}
  
  @TargetApi(18)
  @NonNull
  public static e a(@NonNull Context paramContext, long paramLong, int paramInt)
  {
    if (k.j() < 18) {
      paramContext = new e();
    }
    for (Object localObject3 = "ANDROID_TOO_OLD";; localObject3 = "NO_PERMISSION")
    {
      paramContext.a((String)localObject3);
      return paramContext;
      long l5 = System.currentTimeMillis();
      localObject3 = new JSONObject();
      try
      {
        localCursor = paramContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (localCursor != null) {
          break;
        }
        paramContext = new e();
        paramContext.a("NO_PERMISSION");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          Cursor localCursor;
          int j;
          String str;
          long l1;
          Object localObject1;
          Object localObject2;
          continue;
          long l3 = -1L;
          long l4 = l3;
          int i = 0;
          continue;
          paramContext = "contact_status_ts";
          continue;
          long l2 = l3;
          if (l3 == -1L) {
            l2 = l1;
          }
          l3 = l4;
          if (l4 == -1L) {
            l3 = l1;
          }
          l4 = l2;
          if (l2 > l1) {
            l4 = l1;
          }
          l2 = l3;
          if (l3 < l1)
          {
            l2 = l1;
            continue;
            continue;
            j = 0;
            continue;
            l3 = l4;
            l4 = l2;
          }
        }
      }
      j = i;
      if (localCursor.moveToNext())
      {
        i += 1;
        if ((System.currentTimeMillis() <= l5 + paramLong) && (((JSONObject)localObject3).length() != paramInt))
        {
          str = localCursor.getString(localCursor.getColumnIndex("data1"));
          if (k.j() < 18) {
            break label396;
          }
          paramContext = "contact_last_updated_timestamp";
          l1 = localCursor.getLong(localCursor.getColumnIndex(paramContext));
          break label402;
          if ((str == null) || (str.equals("")) || (str.length() < 7)) {
            break label480;
          }
          paramContext = str.replaceAll("[^0-9+]+", "");
          if (paramContext.substring(0, 1).equals("+"))
          {
            paramContext = paramContext.substring(1, 7);
            break label471;
          }
          if (paramContext.substring(0, 2).equals("00"))
          {
            paramContext = paramContext.substring(2, 8);
            break label471;
          }
          paramContext = paramContext.substring(0, 3);
          if (!((JSONObject)localObject3).has(paramContext)) {
            break label474;
          }
          j = ((JSONObject)localObject3).getInt(paramContext) + 1;
          ((JSONObject)localObject3).put(paramContext, j);
          break label480;
        }
        Log.d("Utils", "TOOOOOO SLOW!");
        j = i;
      }
      localCursor.close();
      paramContext = new e();
      paramContext.a(Integer.toString(j));
      paramContext.b(Long.toString(localObject1));
      paramContext.c(Long.toString(localObject2));
      paramContext.a((JSONObject)localObject3);
      return paramContext;
      paramContext = new e();
    }
  }
  
  public static String a()
  {
    return UUID.randomUUID().toString();
  }
  
  /* Error */
  @NonNull
  public static String a(@NonNull Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 178	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   7: astore_2
    //   8: new 180	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 181	java/lang/StringBuilder:<init>	()V
    //   15: astore_1
    //   16: aload_1
    //   17: aload_0
    //   18: invokestatic 184	com/forter/mobile/fortersdk/utils/m:f	(Landroid/content/Context;)Ljava/lang/String;
    //   21: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload_1
    //   26: ldc -66
    //   28: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload_1
    //   33: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: astore_3
    //   37: aload_0
    //   38: invokestatic 193	com/forter/mobile/fortersdk/utils/m:b	(Landroid/content/Context;)Ljava/lang/String;
    //   41: astore_1
    //   42: aload_1
    //   43: astore_0
    //   44: aload_1
    //   45: ifnonnull +32 -> 77
    //   48: invokestatic 169	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   51: invokevirtual 171	java/util/UUID:toString	()Ljava/lang/String;
    //   54: astore_0
    //   55: aload_2
    //   56: invokeinterface 199 1 0
    //   61: astore_1
    //   62: aload_1
    //   63: aload_3
    //   64: aload_0
    //   65: invokeinterface 205 3 0
    //   70: pop
    //   71: aload_1
    //   72: invokeinterface 208 1 0
    //   77: ldc 2
    //   79: monitorexit
    //   80: aload_0
    //   81: areturn
    //   82: astore_0
    //   83: goto +9 -> 92
    //   86: ldc 2
    //   88: monitorexit
    //   89: ldc 10
    //   91: areturn
    //   92: ldc 2
    //   94: monitorexit
    //   95: aload_0
    //   96: athrow
    //   97: astore_0
    //   98: goto -12 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	paramContext	Context
    //   15	57	1	localObject	Object
    //   7	49	2	localSharedPreferences	SharedPreferences
    //   36	28	3	str	String
    // Exception table:
    //   from	to	target	type
    //   3	42	82	finally
    //   48	77	82	finally
    //   3	42	97	java/lang/Exception
    //   48	77	97	java/lang/Exception
  }
  
  @Nullable
  public static String a(@Nullable Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return paramObject.toString();
  }
  
  @NonNull
  public static String a(@NonNull String paramString)
  {
    return paramString.toLowerCase(Locale.ROOT);
  }
  
  public static String a(@NonNull String paramString, int paramInt)
  {
    String str = paramString;
    if (paramString.length() > paramInt) {
      str = paramString.substring(0, paramInt);
    }
    return str;
  }
  
  @SuppressLint({"PrivateApi"})
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      Method localMethod = Build.class.getDeclaredMethod("getString", new Class[] { String.class });
      localMethod.setAccessible(true);
      paramString1 = localMethod.invoke(null, new Object[] { paramString1 }).toString();
      return paramString1;
    }
    catch (Exception paramString1) {}
    return paramString2;
  }
  
  @Nullable
  public static String a(@NonNull String paramString, @NonNull JSONObject paramJSONObject)
  {
    try
    {
      Uri.Builder localBuilder = Uri.parse(paramString).buildUpon();
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localBuilder.appendQueryParameter(str, paramJSONObject.getString(str));
      }
      paramJSONObject = localBuilder.build().toString();
      return paramJSONObject;
    }
    catch (Exception paramJSONObject)
    {
      SDKLogger.a("Utils", "Failed to parse JSON as querystring ", paramJSONObject);
    }
    return paramString;
  }
  
  @NonNull
  public static String a(@NonNull String[] paramArrayOfString, char paramChar)
  {
    if (paramArrayOfString.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfString[0]);
    int i = 1;
    while (i < paramArrayOfString.length)
    {
      localStringBuilder.append(paramChar);
      localStringBuilder.append(paramArrayOfString[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  @NonNull
  public static Object b(@Nullable Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = JSONObject.NULL;
    }
    return localObject;
  }
  
  @Nullable
  public static String b(@NonNull Context paramContext)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(f(paramContext));
    ((StringBuilder)localObject).append("installation_guid");
    localObject = ((StringBuilder)localObject).toString();
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString((String)localObject, null);
  }
  
  @NonNull
  public static String b(@NonNull String paramString)
  {
    return paramString.toUpperCase(Locale.ROOT);
  }
  
  @NonNull
  public static ExecutorService b()
  {
    Executors.newSingleThreadExecutor(new ThreadFactory()
    {
      public Thread newThread(@NonNull Runnable paramAnonymousRunnable)
      {
        paramAnonymousRunnable = Executors.defaultThreadFactory().newThread(paramAnonymousRunnable);
        paramAnonymousRunnable.setDaemon(true);
        return paramAnonymousRunnable;
      }
    });
  }
  
  @NonNull
  public static String c()
  {
    Date localDate = Calendar.getInstance().getTime();
    try
    {
      localObject = DateFormat.getDateTimeInstance(0, 0).format(localDate);
      return localObject;
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    catch (AssertionError localAssertionError)
    {
      for (;;) {}
    }
    for (localObject = new StringBuilder();; localObject = new StringBuilder())
    {
      ((StringBuilder)localObject).append(localDate.toString());
      ((StringBuilder)localObject).append("(FAILURE)");
      return ((StringBuilder)localObject).toString();
    }
  }
  
  @Nullable
  public static String c(@NonNull String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("SHA-1");
      ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuilder();
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        ((StringBuilder)localObject).append(Integer.toString((paramString[i] & 0xFF) + 256, 16).substring(1));
        i += 1;
      }
      paramString = ((StringBuilder)localObject).toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean c(@NonNull Context paramContext)
  {
    return b(paramContext) == null;
  }
  
  @NonNull
  public static String d()
  {
    Object localObject = Calendar.getInstance().getTime();
    try
    {
      localObject = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss 'GMT'Z (z)", Locale.ENGLISH).format((Date)localObject);
      return localObject;
    }
    catch (AssertionError localAssertionError)
    {
      localForterClient = ForterClient.getInstance();
      str3 = "Failed formatting local time (AssertionErr)";
      String str1 = localAssertionError.getMessage();
      localForterClient.sendError(str3, str1);
      return "FAILURE";
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ForterClient localForterClient = ForterClient.getInstance();
        String str3 = "Failed formatting local time";
        String str2 = localException.getMessage();
      }
    }
  }
  
  @NonNull
  public static String d(@NonNull Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "FAILURE";
  }
  
  @Nullable
  public static String d(@NonNull String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes("UTF-8"));
      paramString = new String(Base64.encode(localMessageDigest.digest(), 2));
      return paramString;
    }
    catch (Exception paramString)
    {
      SDKLogger.a("Utils", "Sha1Base64 failure", paramString);
    }
    return null;
  }
  
  public static String e()
  {
    if (Build.VERSION.SDK_INT < 26) {
      return a("ro.runtime.firstboot", "FAILURE");
    }
    return "FAILURE";
  }
  
  public static List<PackageInfo> e(@NonNull Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(128);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return Collections.emptyList();
  }
  
  public static String f()
  {
    return String.format(Locale.ENGLISH, "%s(%d)", new Object[] { "1.9.9", Integer.valueOf(44) });
  }
  
  @NonNull
  private static String f(@NonNull Context paramContext)
  {
    try
    {
      paramContext = Integer.toString(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode);
    }
    catch (Exception paramContext)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    paramContext = "0";
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("forter_sdk_prefs_");
    localStringBuilder.append(paramContext);
    localStringBuilder.append("_");
    return localStringBuilder.toString();
  }
}
