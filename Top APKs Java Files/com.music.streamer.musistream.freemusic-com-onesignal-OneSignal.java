package com.onesignal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import android.util.Log;
import bul;
import bum;
import bun;
import buo;
import buq;
import buu;
import buz;
import bve;
import bvg;
import bvh;
import bvi;
import bvj;
import bvm;
import bvm.a;
import bvn;
import bvo;
import bvq;
import bvr;
import bvs;
import bvt;
import bvu;
import bvv;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneSignal
{
  private static bvv A;
  private static bvt B;
  private static bvu C;
  private static buo D;
  private static int E = 0;
  private static bvi F;
  private static String G;
  private static boolean H = false;
  private static boolean I = false;
  private static boolean J = false;
  private static boolean K = false;
  private static LocationGMS.e L;
  private static Collection<JSONArray> M;
  private static HashSet<String> N;
  private static ArrayList<OneSignal.GetTagsHandler> O;
  private static boolean P = false;
  private static boolean Q = false;
  private static boolean R = false;
  private static JSONObject S;
  private static boolean T = false;
  private static OSPermissionState U;
  private static bvh<OSPermissionObserver, OSPermissionStateChanges> V;
  public static final String VERSION = "031006";
  private static OSSubscriptionState W;
  private static bvh<OSSubscriptionObserver, OSSubscriptionStateChanges> X;
  private static OSEmailSubscriptionState Y;
  private static bvh<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> Z;
  public static String a;
  private static OneSignal.a aa;
  private static PushRegistrator ab;
  private static int ac = 0;
  public static Context b;
  static boolean c;
  static ExecutorService d;
  static AtomicLong e;
  static boolean f;
  public static Builder g;
  public static boolean h;
  static boolean i;
  static buu j;
  static OSPermissionState k;
  static OSSubscriptionState l;
  public static OSEmailSubscriptionState m;
  private static OneSignal.EmailUpdateHandler n;
  private static OneSignal.EmailUpdateHandler o;
  private static String p;
  private static boolean q;
  private static OneSignal.LOG_LEVEL r = OneSignal.LOG_LEVEL.NONE;
  private static OneSignal.LOG_LEVEL s = OneSignal.LOG_LEVEL.WARN;
  public static String sdkType;
  private static String t = null;
  public static ConcurrentLinkedQueue<Runnable> taskQueueWaitingForInit;
  private static String u = null;
  private static int v;
  private static boolean w;
  private static OneSignal.IdsAvailableHandler x;
  private static long y;
  private static long z;
  
  static
  {
    taskQueueWaitingForInit = new ConcurrentLinkedQueue();
    e = new AtomicLong();
    y = 1L;
    z = -1L;
    D = new bun();
    sdkType = "native";
    f = true;
    M = new ArrayList();
    N = new HashSet();
    O = new ArrayList();
    i = false;
  }
  
  public OneSignal() {}
  
  private static void I()
  {
    if (!taskQueueWaitingForInit.isEmpty())
    {
      d = Executors.newSingleThreadExecutor(new OneSignal.1());
      while (!taskQueueWaitingForInit.isEmpty()) {
        d.submit((Runnable)taskQueueWaitingForInit.poll());
      }
    }
  }
  
  private static boolean J()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    ExecutorService localExecutorService = d;
    return (localExecutorService != null) && (!localExecutorService.isShutdown());
  }
  
  private static void K()
  {
    if (Q) {
      return;
    }
    boolean bool2 = true;
    Q = true;
    H = false;
    if (R) {
      I = false;
    }
    L();
    O();
    boolean bool1 = bool2;
    if (!K) {
      if (g.d) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    K = bool1;
  }
  
  private static void L()
  {
    OneSignal.12 local12 = new OneSignal.12();
    boolean bool;
    if ((g.d) && (!K)) {
      bool = true;
    } else {
      bool = false;
    }
    LocationGMS.a(b, bool, local12);
  }
  
  private static PushRegistrator M()
  {
    PushRegistrator localPushRegistrator = ab;
    if (localPushRegistrator != null) {
      return localPushRegistrator;
    }
    if (E == 2) {
      ab = new PushRegistratorADM();
    } else if (bvi.a()) {
      ab = new bvq();
    } else {
      ab = new bvr();
    }
    return ab;
  }
  
  private static void N()
  {
    M().registerForPush(b, p, new OneSignal.18());
  }
  
  private static void O()
  {
    if (J)
    {
      N();
      return;
    }
    OneSignal.19 local19 = new OneSignal.19();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = k();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    a(OneSignal.LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
    bvm.a((String)localObject, local19);
  }
  
  private static void P()
  {
    Iterator localIterator = M.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    M.clear();
  }
  
  private static int Q()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void R()
  {
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(H);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(I);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(J);
    a(localLOG_LEVEL, localStringBuilder.toString());
    if ((H) && (I))
    {
      if (!J) {
        return;
      }
      new Thread(new OneSignal.22(), "OS_REG_USER").start();
      return;
    }
  }
  
  private static void S()
  {
    Object localObject3 = b.getPackageName();
    Object localObject2 = b.getPackageManager();
    Object localObject1 = new JSONObject();
    ((JSONObject)localObject1).put("app_id", a);
    Object localObject4 = D.getIdentifier(b);
    if (localObject4 != null) {
      ((JSONObject)localObject1).put("ad_id", localObject4);
    }
    ((JSONObject)localObject1).put("device_os", Build.VERSION.RELEASE);
    ((JSONObject)localObject1).put("timezone", Q());
    ((JSONObject)localObject1).put("language", bvi.f());
    ((JSONObject)localObject1).put("sdk", "031006");
    ((JSONObject)localObject1).put("sdk_type", sdkType);
    ((JSONObject)localObject1).put("android_package", localObject3);
    ((JSONObject)localObject1).put("device_model", Build.MODEL);
    try
    {
      ((JSONObject)localObject1).put("game_version", ((PackageManager)localObject2).getPackageInfo((String)localObject3, 0).versionCode);
      try
      {
        localObject2 = ((PackageManager)localObject2).getInstalledPackages(0);
        localObject3 = new JSONArray();
        localObject4 = MessageDigest.getInstance("SHA-256");
        i1 = 0;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          int i1;
          String str;
          continue;
          i1 += 1;
        }
      }
      if (i1 < ((List)localObject2).size())
      {
        ((MessageDigest)localObject4).update(((PackageInfo)((List)localObject2).get(i1)).packageName.getBytes());
        str = Base64.encodeToString(((MessageDigest)localObject4).digest(), 2);
        if (S.has(str)) {
          ((JSONArray)localObject3).put(str);
        }
      }
      else
      {
        ((JSONObject)localObject1).put("pkgs", localObject3);
        ((JSONObject)localObject1).put("net_type", F.d());
        ((JSONObject)localObject1).put("carrier", F.e());
        ((JSONObject)localObject1).put("rooted", bvs.a());
        bvn.a((JSONObject)localObject1);
        localObject1 = new JSONObject();
        ((JSONObject)localObject1).put("identifier", G);
        ((JSONObject)localObject1).put("subscribableStatus", v);
        ((JSONObject)localObject1).put("androidPermission", r());
        ((JSONObject)localObject1).put("device_type", E);
        bvn.b((JSONObject)localObject1);
        if (f)
        {
          localObject1 = L;
          if (localObject1 != null) {
            bvn.a((LocationGMS.e)localObject1);
          }
        }
        if (R) {
          bvn.k();
        }
        Q = false;
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
  }
  
  private static void T()
  {
    synchronized (O)
    {
      if (O.size() == 0) {
        return;
      }
      new Thread(new OneSignal.7(), "OS_GETTAGS_CALLBACK").start();
      return;
    }
  }
  
  private static void U()
  {
    try
    {
      Object localObject1 = x;
      if (localObject1 == null) {
        return;
      }
      localObject1 = bvn.h();
      if (!bvn.g()) {
        localObject1 = null;
      }
      String str = k();
      if (str == null) {
        return;
      }
      x.idsAvailable(str, (String)localObject1);
      if (localObject1 != null) {
        x = null;
      }
      return;
    }
    finally {}
  }
  
  private static boolean V()
  {
    if (R) {
      return true;
    }
    return (System.currentTimeMillis() - p(b)) / 1000L >= 30L;
  }
  
  static bvh<OSPermissionObserver, OSPermissionStateChanges> a()
  {
    if (V == null) {
      V = new bvh("onOSPermissionChanged", true);
    }
    return V;
  }
  
  public static String a(Bundle paramBundle)
  {
    if (paramBundle.isEmpty()) {
      return null;
    }
    try
    {
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        a(OneSignal.LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
      a(OneSignal.LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(OneSignal.LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
    return null;
  }
  
  public static void a(long paramLong)
  {
    OneSignalPrefs.saveLong(OneSignalPrefs.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  public static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      b(localJSONObject);
      a(k(), localJSONObject, paramBoolean);
      String str = l();
      if (str != null)
      {
        a(str, localJSONObject, paramBoolean);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  private static void a(OSNotificationOpenResult paramOSNotificationOpenResult)
  {
    bvi.a(new OneSignal.10(paramOSNotificationOpenResult));
  }
  
  public static void a(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    a(paramLOG_LEVEL, paramString, null);
  }
  
  public static void a(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString, Throwable paramThrowable)
  {
    if (paramLOG_LEVEL.compareTo(s) < 1) {
      if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.VERBOSE) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.DEBUG) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.INFO) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.WARN) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramLOG_LEVEL == OneSignal.LOG_LEVEL.ERROR) || (paramLOG_LEVEL == OneSignal.LOG_LEVEL.FATAL)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramLOG_LEVEL.compareTo(r) < 1) && (bul.b != null)) {
      try
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append("\n");
        localObject = ((StringBuilder)localObject).toString();
        paramString = (String)localObject;
        if (paramThrowable != null)
        {
          paramString = new StringBuilder();
          paramString.append((String)localObject);
          paramString.append(paramThrowable.getMessage());
          paramString = paramString.toString();
          localObject = new StringWriter();
          paramThrowable.printStackTrace(new PrintWriter((Writer)localObject));
          paramThrowable = new StringBuilder();
          paramThrowable.append(paramString);
          paramThrowable.append(((StringWriter)localObject).toString());
          paramString = paramThrowable.toString();
        }
        bvi.a(new OneSignal.20(paramLOG_LEVEL, paramString));
        return;
      }
      catch (Throwable paramLOG_LEVEL)
      {
        Log.e("OneSignal", "Error showing logging message.", paramLOG_LEVEL);
      }
    }
  }
  
  private static void a(OneSignal.b paramB)
  {
    OneSignal.b.a(paramB, e.incrementAndGet());
    Object localObject = d;
    StringBuilder localStringBuilder;
    if (localObject == null)
    {
      localObject = OneSignal.LOG_LEVEL.INFO;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(OneSignal.b.a(paramB));
      a((OneSignal.LOG_LEVEL)localObject, localStringBuilder.toString());
      taskQueueWaitingForInit.add(paramB);
      return;
    }
    if (!((ExecutorService)localObject).isShutdown())
    {
      localObject = OneSignal.LOG_LEVEL.INFO;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Executor is still running, add to the executor with ID: ");
      localStringBuilder.append(OneSignal.b.a(paramB));
      a((OneSignal.LOG_LEVEL)localObject, localStringBuilder.toString());
      d.submit(paramB);
    }
  }
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new OneSignal.21();
    if (paramBoolean)
    {
      bvm.d(paramString, paramJSONObject, (bvm.a)localObject);
      return;
    }
    bvm.b(paramString, paramJSONObject, (bvm.a)localObject);
  }
  
  public static void a(JSONArray paramJSONArray, boolean paramBoolean, bvm.a paramA)
  {
    if (a("sendPurchases()")) {
      return;
    }
    if (k() == null)
    {
      aa = new OneSignal.a(paramJSONArray);
      paramJSONArray = aa;
      paramJSONArray.b = paramBoolean;
      paramJSONArray.c = paramA;
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("app_id", a);
      if (paramBoolean) {
        localJSONObject.put("existing", true);
      }
      localJSONObject.put("purchases", paramJSONArray);
      paramJSONArray = new StringBuilder();
      paramJSONArray.append("players/");
      paramJSONArray.append(k());
      paramJSONArray.append("/on_purchase");
      bvm.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (l() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(l());
        paramJSONArray.append("/on_purchase");
        bvm.b(paramJSONArray.toString(), localJSONObject, null);
        return;
      }
    }
    catch (Throwable paramJSONArray)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  public static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((C != null) && (b(b))) {
      C.b(paramJSONArray);
    }
    Builder localBuilder = g;
    if (localBuilder != null)
    {
      if (localBuilder.c == null) {
        return;
      }
      g.c.notificationReceived(paramJSONArray.notification);
      return;
    }
  }
  
  static void a(boolean paramBoolean)
  {
    OneSignalPrefs.saveBool(OneSignalPrefs.a, "ONESIGNAL_USER_PROVIDED_CONSENT", paramBoolean);
  }
  
  static boolean a(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  private static boolean a(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool1 = a(null);
    int i1 = 0;
    if (bool1) {
      return false;
    }
    int i2 = paramJSONArray.length();
    boolean bool2;
    for (bool1 = false; i1 < i2; bool1 = bool2)
    {
      try
      {
        Object localObject1 = paramJSONArray.getJSONObject(i1);
        if (!((JSONObject)localObject1).has("custom"))
        {
          bool2 = bool1;
        }
        else
        {
          localObject1 = new JSONObject(((JSONObject)localObject1).optString("custom"));
          bool2 = bool1;
          if (((JSONObject)localObject1).has("u"))
          {
            localObject2 = ((JSONObject)localObject1).optString("u", null);
            localObject1 = localObject2;
            if (!((String)localObject2).contains("://"))
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("http://");
              ((StringBuilder)localObject1).append((String)localObject2);
              localObject1 = ((StringBuilder)localObject1).toString();
            }
            localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(((String)localObject1).trim()));
            ((Intent)localObject1).addFlags(1476919296);
            paramContext.startActivity((Intent)localObject1);
            bool2 = true;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject2 = OneSignal.LOG_LEVEL.ERROR;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((OneSignal.LOG_LEVEL)localObject2, localStringBuilder.toString(), localThrowable);
        bool2 = bool1;
      }
      i1 += 1;
    }
    return bool1;
  }
  
  public static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = c(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(OneSignal.LOG_LEVEL paramLOG_LEVEL)
  {
    int i1 = paramLOG_LEVEL.compareTo(r);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramLOG_LEVEL.compareTo(s) < 1) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  public static boolean a(String paramString)
  {
    if ((i) && (!userProvidedPrivacyConsent()))
    {
      if (paramString != null)
      {
        OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.WARN;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Method ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
        a(localLOG_LEVEL, localStringBuilder.toString());
      }
      return true;
    }
    return false;
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +189 -> 190
    //   4: ldc_w 924
    //   7: aload_0
    //   8: invokevirtual 927	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 932	com/onesignal/OneSignalDbHelper:getInstance	(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
    //   20: astore 5
    //   22: aconst_null
    //   23: astore 4
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 5
    //   29: invokevirtual 935	com/onesignal/OneSignalDbHelper:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 936
    //   35: iconst_1
    //   36: anewarray 506	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 938
    //   44: aastore
    //   45: ldc_w 940
    //   48: iconst_1
    //   49: anewarray 506	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 946	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 5
    //   64: aload 5
    //   66: astore_1
    //   67: aload 5
    //   69: astore 4
    //   71: aload 5
    //   73: invokeinterface 951 1 0
    //   78: istore_3
    //   79: iload_3
    //   80: istore_2
    //   81: aload 5
    //   83: ifnull +49 -> 132
    //   86: aload 5
    //   88: invokeinterface 954 1 0
    //   93: iload_3
    //   94: istore_2
    //   95: goto +37 -> 132
    //   98: astore_0
    //   99: goto +79 -> 178
    //   102: astore 5
    //   104: aload 4
    //   106: astore_1
    //   107: getstatic 685	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   110: ldc_w 956
    //   113: aload 5
    //   115: invokestatic 653	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   118: aload 4
    //   120: ifnull +10 -> 130
    //   123: aload 4
    //   125: invokeinterface 954 1 0
    //   130: iconst_0
    //   131: istore_2
    //   132: iload_2
    //   133: ifeq +43 -> 176
    //   136: getstatic 326	com/onesignal/OneSignal$LOG_LEVEL:DEBUG	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   139: astore_1
    //   140: new 305	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 306	java/lang/StringBuilder:<init>	()V
    //   147: astore 4
    //   149: aload 4
    //   151: ldc_w 958
    //   154: invokevirtual 312	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: aload 4
    //   160: aload_0
    //   161: invokevirtual 312	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload_1
    //   166: aload 4
    //   168: invokevirtual 319	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokestatic 331	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   174: iconst_1
    //   175: ireturn
    //   176: iconst_0
    //   177: ireturn
    //   178: aload_1
    //   179: ifnull +9 -> 188
    //   182: aload_1
    //   183: invokeinterface 954 1 0
    //   188: aload_0
    //   189: athrow
    //   190: iconst_0
    //   191: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	paramString	String
    //   0	192	1	paramContext	Context
    //   80	53	2	bool1	boolean
    //   78	16	3	bool2	boolean
    //   23	144	4	localObject1	Object
    //   20	67	5	localObject2	Object
    //   102	12	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   27	64	98	finally
    //   71	79	98	finally
    //   107	118	98	finally
    //   27	64	102	java/lang/Throwable
    //   71	79	102	java/lang/Throwable
  }
  
  public static void addEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add email subscription observer");
      return;
    }
    c().a(paramOSEmailSubscriptionObserver);
    if (k(b).a(l(b))) {
      bvg.a(k(b));
    }
  }
  
  public static void addPermissionObserver(OSPermissionObserver paramOSPermissionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add permission observer");
      return;
    }
    a().a(paramOSPermissionObserver);
    if (g(b).a(h(b))) {
      OSPermissionChangedInternalObserver.b(g(b));
    }
  }
  
  public static void addSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add subscription observer");
      return;
    }
    b().a(paramOSSubscriptionObserver);
    if (i(b).a(j(b))) {
      OSSubscriptionChangedInternalObserver.a(i(b));
    }
  }
  
  static bvh<OSSubscriptionObserver, OSSubscriptionStateChanges> b()
  {
    if (X == null) {
      X = new bvh("onOSSubscriptionChanged", true);
    }
    return X;
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    while (i1 < paramJSONArray.length())
    {
      try
      {
        String str = new JSONObject(paramJSONArray.getJSONObject(i1).optString("custom", null)).optString("i", null);
        if (!N.contains(str))
        {
          N.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", n(paramContext));
          localJSONObject.put("player_id", o(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          bvm.a(localStringBuilder.toString(), localJSONObject, new OneSignal.11());
        }
      }
      catch (Throwable localThrowable)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i1 += 1;
    }
  }
  
  private static void b(Builder paramBuilder)
  {
    if (getCurrentOrNewInitBuilder().h) {
      paramBuilder.i = getCurrentOrNewInitBuilder().i;
    }
    g = paramBuilder;
    Context localContext = g.a;
    g.a = null;
    try
    {
      Bundle localBundle = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 128).metaData;
      String str = localBundle.getString("onesignal_google_project_number");
      paramBuilder = str;
      if (str != null)
      {
        paramBuilder = str;
        if (str.length() > 4) {
          paramBuilder = str.substring(4);
        }
      }
      setRequiresUserPrivacyConsent("ENABLE".equalsIgnoreCase(localBundle.getString("com.onesignal.PrivacyConsent")));
      init(localContext, paramBuilder, localBundle.getString("onesignal_app_id"), g.b, g.c);
      return;
    }
    catch (Throwable paramBuilder)
    {
      paramBuilder.printStackTrace();
    }
  }
  
  public static void b(String paramString)
  {
    t = paramString;
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveString(OneSignalPrefs.a, "GT_PLAYER_ID", t);
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(OneSignal.LOG_LEVEL.INFO))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = OneSignal.LOG_LEVEL.WARN;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("HTTP code: ");
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append(" ");
    ((StringBuilder)localObject2).append(paramString1);
    ((StringBuilder)localObject2).append((String)localObject1);
    a(paramString2, ((StringBuilder)localObject2).toString(), paramThrowable);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Builder localBuilder = g;
    if ((localBuilder != null) && (localBuilder.b != null))
    {
      a(c(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    M.add(paramJSONArray);
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", F.d());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  static boolean b(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  public static bvh<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> c()
  {
    if (Z == null) {
      Z = new bvh("onOSEmailSubscriptionChanged", true);
    }
    return Z;
  }
  
  @NonNull
  private static OSNotificationOpenResult c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    OSNotificationOpenResult localOSNotificationOpenResult = new OSNotificationOpenResult();
    OSNotification localOSNotification = new OSNotification();
    localOSNotification.isAppInFocus = p();
    localOSNotification.shown = paramBoolean1;
    localOSNotification.androidNotificationId = paramJSONArray.optJSONObject(0).optInt("notificationId");
    Object localObject1 = null;
    int i1 = 0;
    int i2 = 1;
    Object localObject4;
    Object localObject3;
    if (i1 < i3)
    {
      localObject4 = localObject1;
      try
      {
        localObject5 = paramJSONArray.getJSONObject(i1);
        localObject4 = localObject1;
        localOSNotification.payload = buz.a((JSONObject)localObject5);
        localObject3 = localObject1;
        if (localObject1 != null) {
          break label365;
        }
        localObject4 = localObject1;
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("actionSelected")) {
          break label365;
        }
        localObject4 = localObject1;
        localObject3 = ((JSONObject)localObject5).optString("actionSelected", null);
      }
      catch (Throwable localThrowable)
      {
        label139:
        localObject3 = OneSignal.LOG_LEVEL.ERROR;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((OneSignal.LOG_LEVEL)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localOSNotification.groupedNotifications == null)
      {
        localObject4 = localObject3;
        localOSNotification.groupedNotifications = new ArrayList();
      }
      localObject4 = localObject3;
      localOSNotification.groupedNotifications.add(localOSNotification.payload);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localOSNotificationOpenResult.notification = localOSNotification;
      localOSNotificationOpenResult.action = new OSNotificationAction();
      localOSNotificationOpenResult.action.actionID = localObject2;
      localObject3 = localOSNotificationOpenResult.action;
      if (localObject2 != null) {
        paramJSONArray = OSNotificationAction.ActionType.ActionTaken;
      } else {
        paramJSONArray = OSNotificationAction.ActionType.Opened;
      }
      ((OSNotificationAction)localObject3).type = paramJSONArray;
      if (paramBoolean2)
      {
        localOSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.InAppAlert;
        return localOSNotificationOpenResult;
      }
      localOSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.Notification;
      return localOSNotificationOpenResult;
      label365:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
    }
  }
  
  private static String c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject.optString("custom")).optString("i", null);
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject) {}
    return null;
  }
  
  public static void c(String paramString)
  {
    u = paramString;
    if (b == null) {
      return;
    }
    String str = OneSignalPrefs.a;
    if ("".equals(u)) {
      paramString = null;
    } else {
      paramString = u;
    }
    OneSignalPrefs.saveString(str, "OS_EMAIL_ID", paramString);
  }
  
  private static boolean c(int paramInt)
  {
    return paramInt < -6;
  }
  
  public static boolean c(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.a, "GT_VIBRATE_ENABLED", true);
  }
  
  public static void cancelGroupedNotifications(String paramString)
  {
    if (a("cancelGroupedNotifications()")) {
      return;
    }
    OneSignal.17 local17 = new OneSignal.17(paramString);
    if ((b != null) && (!J()))
    {
      local17.run();
      return;
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.ERROR;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notifications part of group ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" - movingthis operation to a waiting task queue.");
    a(localLOG_LEVEL, localStringBuilder.toString());
    a(new OneSignal.b(local17));
  }
  
  public static void cancelNotification(int paramInt)
  {
    OneSignal.16 local16 = new OneSignal.16(paramInt);
    if ((b != null) && (!J()))
    {
      local16.run();
      return;
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.ERROR;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notification id: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
    a(localLOG_LEVEL, localStringBuilder.toString());
    taskQueueWaitingForInit.add(local16);
  }
  
  public static void clearOneSignalNotifications()
  {
    OneSignal.15 local15 = new OneSignal.15();
    if ((b != null) && (!J()))
    {
      local15.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
    a(new OneSignal.b(local15));
  }
  
  public static OSInFocusDisplayOption currentInFocusDisplayOption()
  {
    return getCurrentOrNewInitBuilder().i;
  }
  
  private static OneSignal.LOG_LEVEL d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return OneSignal.LOG_LEVEL.NONE;
      }
      break;
    case 6: 
      return OneSignal.LOG_LEVEL.VERBOSE;
    case 5: 
      return OneSignal.LOG_LEVEL.DEBUG;
    case 4: 
      return OneSignal.LOG_LEVEL.INFO;
    case 3: 
      return OneSignal.LOG_LEVEL.WARN;
    case 2: 
      return OneSignal.LOG_LEVEL.ERROR;
    case 1: 
      return OneSignal.LOG_LEVEL.FATAL;
    case 0: 
      return OneSignal.LOG_LEVEL.NONE;
    }
    return OneSignal.LOG_LEVEL.VERBOSE;
  }
  
  private static void d(long paramLong)
  {
    if (e.get() == paramLong)
    {
      a(OneSignal.LOG_LEVEL.INFO, "Last Pending Task has ran, shutting down");
      d.shutdown();
    }
  }
  
  public static void d(String paramString)
  {
    b(paramString);
    h();
    T();
    i(b).a(paramString);
    OneSignal.a localA = aa;
    if (localA != null)
    {
      a(localA.a, aa.b, aa.c);
      aa = null;
    }
    bvn.j();
    bvj.a(b, a, paramString, bun.a());
  }
  
  @WorkerThread
  public static boolean d()
  {
    boolean bool1 = false;
    w = false;
    LocationGMS.c();
    if (!c) {
      return false;
    }
    bvt localBvt = B;
    if (localBvt != null) {
      localBvt.a();
    }
    if (y == -1L) {
      return false;
    }
    double d1 = SystemClock.elapsedRealtime() - y;
    Double.isNaN(d1);
    long l1 = (d1 / 1000.0D + 0.5D);
    y = SystemClock.elapsedRealtime();
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (b == null)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      boolean bool2 = e();
      a(System.currentTimeMillis());
      l1 = o() + l1;
      e(l1);
      if ((l1 >= 60L) && (k() != null))
      {
        if (!bool2) {
          bvo.a(b);
        }
        bvo.a();
        return false;
      }
      if (l1 >= 60L) {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  public static boolean d(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.a, "GT_SOUND_ENABLED", true);
  }
  
  public static void deleteTag(String paramString)
  {
    deleteTag(paramString, null);
  }
  
  public static void deleteTag(String paramString, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (a("deleteTag()")) {
      return;
    }
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramString);
    deleteTags(localArrayList, paramChangeTagsUpdateHandler);
  }
  
  public static void deleteTags(String paramString)
  {
    deleteTags(paramString, null);
  }
  
  public static void deleteTags(String paramString, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (a("deleteTags()")) {
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramString = new JSONArray(paramString);
      int i1 = 0;
      while (i1 < paramString.length())
      {
        localJSONObject.put(paramString.getString(i1), "");
        i1 += 1;
      }
      sendTags(localJSONObject, paramChangeTagsUpdateHandler);
      return;
    }
    catch (Throwable paramString)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramString);
    }
  }
  
  public static void deleteTags(Collection<String> paramCollection)
  {
    deleteTags(paramCollection, null);
  }
  
  public static void deleteTags(Collection<String> paramCollection, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (a("deleteTags()")) {
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        localJSONObject.put((String)paramCollection.next(), "");
      }
      sendTags(localJSONObject, paramChangeTagsUpdateHandler);
      return;
    }
    catch (Throwable paramCollection)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramCollection);
    }
  }
  
  private static OSInFocusDisplayOption e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return OSInFocusDisplayOption.None;
      }
      break;
    case 2: 
      return OSInFocusDisplayOption.Notification;
    case 1: 
      return OSInFocusDisplayOption.InAppAlert;
    case 0: 
      return OSInFocusDisplayOption.None;
    }
    return OSInFocusDisplayOption.Notification;
  }
  
  private static void e(long paramLong)
  {
    z = paramLong;
    if (b == null) {
      return;
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(z);
    a(localLOG_LEVEL, localStringBuilder.toString());
    OneSignalPrefs.saveLong(OneSignalPrefs.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  public static void e(String paramString)
  {
    c(paramString);
    k(b).a(paramString);
    try
    {
      bvn.b(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static boolean e()
  {
    boolean bool = bvn.c();
    if (bool) {
      bvo.a(b);
    }
    return (LocationGMS.a(b)) || (bool);
  }
  
  public static void enableSound(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.a, "GT_SOUND_ENABLED", paramBoolean);
  }
  
  public static void enableVibrate(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.a, "GT_VIBRATE_ENABLED", paramBoolean);
  }
  
  public static void f()
  {
    w = true;
    LocationGMS.c();
    y = SystemClock.elapsedRealtime();
    R = V();
    a(System.currentTimeMillis());
    K();
    bvv localBvv = A;
    if (localBvv != null) {
      localBvv.a();
    }
    bve.a(b);
    g(b).a();
    if ((C != null) && (b(b))) {
      C.b();
    }
    bvo.b(b);
  }
  
  private static OSPermissionState g(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (U == null)
    {
      U = new OSPermissionState(false);
      U.a.b(new OSPermissionChangedInternalObserver());
    }
    return U;
  }
  
  static boolean g()
  {
    return w;
  }
  
  public static Builder getCurrentOrNewInitBuilder()
  {
    if (g == null) {
      g = new Builder(null);
    }
    return g;
  }
  
  public static OSPermissionSubscriptionState getPermissionSubscriptionState()
  {
    if (a("getPermissionSubscriptionState()")) {
      return null;
    }
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
      return null;
    }
    OSPermissionSubscriptionState localOSPermissionSubscriptionState = new OSPermissionSubscriptionState();
    localOSPermissionSubscriptionState.a = i(b);
    localOSPermissionSubscriptionState.b = g(b);
    localOSPermissionSubscriptionState.c = k(b);
    return localOSPermissionSubscriptionState;
  }
  
  public static void getTags(OneSignal.GetTagsHandler paramGetTagsHandler)
  {
    if (a("getTags()")) {
      return;
    }
    synchronized (O)
    {
      O.add(paramGetTagsHandler);
      if (O.size() > 1) {
        return;
      }
      paramGetTagsHandler = new OneSignal.6(paramGetTagsHandler);
      if (b == null)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
        taskQueueWaitingForInit.add(paramGetTagsHandler);
        return;
      }
      paramGetTagsHandler.run();
      return;
    }
  }
  
  private static OSPermissionState h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (k == null) {
      k = new OSPermissionState(true);
    }
    return k;
  }
  
  public static void h()
  {
    if (x != null) {
      bvi.a(new OneSignal.9());
    }
  }
  
  private static void h(String paramString)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveString(OneSignalPrefs.a, "GT_APP_ID", paramString);
  }
  
  public static void handleNotificationOpen(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    if (a(null)) {
      return;
    }
    b(paramContext, paramJSONArray);
    if ((C != null) && (b(b))) {
      C.a(c(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(bvi.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      m(paramContext);
    }
  }
  
  private static OSSubscriptionState i(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (W == null)
    {
      W = new OSSubscriptionState(false, g(paramContext).getEnabled());
      g(paramContext).a.a(W);
      W.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return W;
  }
  
  public static String i()
  {
    return n(b);
  }
  
  public static void idsAvailable(OneSignal.IdsAvailableHandler paramIdsAvailableHandler)
  {
    if (a("idsAvailable()")) {
      return;
    }
    x = paramIdsAvailableHandler;
    paramIdsAvailableHandler = new OneSignal.8();
    if ((b != null) && (!J()))
    {
      paramIdsAvailableHandler.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    a(new OneSignal.b(paramIdsAvailableHandler));
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2)
  {
    init(paramContext, paramString1, paramString2, null, null);
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, NotificationOpenedHandler paramNotificationOpenedHandler)
  {
    init(paramContext, paramString1, paramString2, paramNotificationOpenedHandler, null);
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, NotificationOpenedHandler paramNotificationOpenedHandler, NotificationReceivedHandler paramNotificationReceivedHandler)
  {
    setAppContext(paramContext);
    if ((i) && (!userProvidedPrivacyConsent()))
    {
      a(OneSignal.LOG_LEVEL.VERBOSE, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      j = new buu(paramContext, paramString1, paramString2, paramNotificationOpenedHandler, paramNotificationReceivedHandler);
      return;
    }
    g = getCurrentOrNewInitBuilder();
    Builder localBuilder = g;
    localBuilder.h = false;
    localBuilder.b = paramNotificationOpenedHandler;
    localBuilder.c = paramNotificationReceivedHandler;
    if (!q) {
      p = paramString1;
    }
    F = new bvi();
    E = F.c();
    v = F.a(paramContext, E, paramString2);
    if (v == 64537) {
      return;
    }
    if (c)
    {
      if (g.b != null) {
        P();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    w = bool;
    a = paramString2;
    b(g.g);
    if (bool)
    {
      bul.b = (Activity)paramContext;
      bve.a(b);
    }
    else
    {
      bul.a = true;
    }
    y = SystemClock.elapsedRealtime();
    bvn.e();
    ((Application)b).registerActivityLifecycleCallbacks(new bum());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      B = new bvt(b);
      paramContext = i();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(OneSignal.LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
          h(a);
          bvn.i();
        }
      }
      else
      {
        buq.a(0, b);
        h(a);
      }
      OSPermissionChangedInternalObserver.a(g(b));
      if ((w) || (k() == null))
      {
        R = V();
        a(System.currentTimeMillis());
        K();
      }
      if (g.b != null) {
        P();
      }
      if (bvv.a(b)) {
        A = new bvv(b);
      }
      if (bvu.a()) {
        C = new bvu(b);
      }
      bvq.a(b);
      c = true;
      I();
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  private static OSSubscriptionState j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (l == null) {
      l = new OSSubscriptionState(true, false);
    }
    return l;
  }
  
  static boolean j()
  {
    return OneSignalPrefs.a(OneSignalPrefs.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  private static OSEmailSubscriptionState k(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (Y == null)
    {
      Y = new OSEmailSubscriptionState(false);
      Y.a.b(new bvg());
    }
    return Y;
  }
  
  public static String k()
  {
    if ((t == null) && (b != null)) {
      t = OneSignalPrefs.a(OneSignalPrefs.a, "GT_PLAYER_ID", null);
    }
    return t;
  }
  
  private static OSEmailSubscriptionState l(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (m == null) {
      m = new OSEmailSubscriptionState(true);
    }
    return m;
  }
  
  public static String l()
  {
    if ("".equals(u)) {
      return null;
    }
    if ((u == null) && (b != null)) {
      u = OneSignalPrefs.a(OneSignalPrefs.a, "OS_EMAIL_ID", null);
    }
    return u;
  }
  
  public static void logoutEmail()
  {
    logoutEmail(null);
  }
  
  public static void logoutEmail(@Nullable OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
    if (a("logoutEmail()")) {
      return;
    }
    if (l() == null)
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.INVALID_OPERATION, "logoutEmail not valid as email was not set or already logged out!"));
      }
      a(OneSignal.LOG_LEVEL.ERROR, "logoutEmail not valid as email was not set or already logged out!");
      return;
    }
    o = paramEmailUpdateHandler;
    paramEmailUpdateHandler = new OneSignal.2();
    if ((b != null) && (!J()))
    {
      paramEmailUpdateHandler.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling logoutEmail! Moving this operation to a pending task queue.");
    a(new OneSignal.b(paramEmailUpdateHandler));
  }
  
  private static void m(Context paramContext)
  {
    if (a(null)) {
      return;
    }
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  public static boolean m()
  {
    Builder localBuilder = g;
    if (localBuilder == null) {
      return true;
    }
    return localBuilder.i == OSInFocusDisplayOption.Notification;
  }
  
  private static String n(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return OneSignalPrefs.a(OneSignalPrefs.a, "GT_APP_ID", null);
  }
  
  public static boolean n()
  {
    Builder localBuilder = g;
    boolean bool = false;
    if (localBuilder == null) {
      return false;
    }
    if (localBuilder.i == OSInFocusDisplayOption.InAppAlert) {
      bool = true;
    }
    return bool;
  }
  
  public static long o()
  {
    if ((z == -1L) && (b != null)) {
      z = OneSignalPrefs.a(OneSignalPrefs.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(z);
    a(localLOG_LEVEL, localStringBuilder.toString());
    return z;
  }
  
  private static String o(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return OneSignalPrefs.a(OneSignalPrefs.a, "GT_PLAYER_ID", null);
  }
  
  public static void onesignalLog(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    a(paramLOG_LEVEL, paramString);
  }
  
  private static long p(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  public static boolean p()
  {
    return (c) && (g());
  }
  
  public static void postNotification(String paramString, OneSignal.PostNotificationResponseHandler paramPostNotificationResponseHandler)
  {
    try
    {
      postNotification(new JSONObject(paramString), paramPostNotificationResponseHandler);
      return;
    }
    catch (JSONException paramPostNotificationResponseHandler)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    paramPostNotificationResponseHandler = OneSignal.LOG_LEVEL.ERROR;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid postNotification JSON format: ");
    localStringBuilder.append(paramString);
    a(paramPostNotificationResponseHandler, localStringBuilder.toString());
  }
  
  public static void postNotification(JSONObject paramJSONObject, OneSignal.PostNotificationResponseHandler paramPostNotificationResponseHandler)
  {
    if (a("postNotification()")) {
      return;
    }
    try
    {
      if (!paramJSONObject.has("app_id")) {
        paramJSONObject.put("app_id", i());
      }
      bvm.b("notifications/", paramJSONObject, new OneSignal.5(paramPostNotificationResponseHandler));
      return;
    }
    catch (JSONException paramJSONObject)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "HTTP create notification json exception!", paramJSONObject);
      if (paramPostNotificationResponseHandler != null) {
        try
        {
          paramPostNotificationResponseHandler.onFailure(new JSONObject("{'error': 'HTTP create notification json exception!'}"));
          return;
        }
        catch (JSONException paramJSONObject)
        {
          paramJSONObject.printStackTrace();
        }
      }
    }
  }
  
  public static void promptLocation()
  {
    if (a("promptLocation()")) {
      return;
    }
    OneSignal.14 local14 = new OneSignal.14();
    if ((b != null) && (!J()))
    {
      local14.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
    a(new OneSignal.b(local14));
  }
  
  public static void provideUserConsent(boolean paramBoolean)
  {
    boolean bool = userProvidedPrivacyConsent();
    a(paramBoolean);
    if ((!bool) && (paramBoolean))
    {
      buu localBuu = j;
      if (localBuu != null)
      {
        init(localBuu.a, j.b, j.c, j.d, j.e);
        j = null;
      }
    }
  }
  
  public static void q()
  {
    R = false;
    a(System.currentTimeMillis());
  }
  
  static boolean r()
  {
    if (g.f) {
      return bvi.a(b);
    }
    return true;
  }
  
  public static void removeEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify email subscription observer");
      return;
    }
    c().c(paramOSEmailSubscriptionObserver);
  }
  
  public static void removeExternalUserId()
  {
    if (a("removeExternalUserId()")) {
      return;
    }
    setExternalUserId("");
  }
  
  public static void removeNotificationOpenedHandler()
  {
    getCurrentOrNewInitBuilder().b = null;
  }
  
  public static void removeNotificationReceivedHandler()
  {
    getCurrentOrNewInitBuilder().c = null;
  }
  
  public static void removePermissionObserver(OSPermissionObserver paramOSPermissionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify permission observer");
      return;
    }
    a().c(paramOSPermissionObserver);
  }
  
  public static void removeSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify subscription observer");
      return;
    }
    b().c(paramOSSubscriptionObserver);
  }
  
  public static boolean requiresUserPrivacyConsent()
  {
    return (i) && (!userProvidedPrivacyConsent());
  }
  
  public static void s()
  {
    OneSignal.EmailUpdateHandler localEmailUpdateHandler = o;
    if (localEmailUpdateHandler != null)
    {
      localEmailUpdateHandler.onSuccess();
      o = null;
    }
  }
  
  public static void sendTag(String paramString1, String paramString2)
  {
    if (a("sendTag()")) {
      return;
    }
    try
    {
      sendTags(new JSONObject().put(paramString1, paramString2));
      return;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void sendTags(String paramString)
  {
    try
    {
      sendTags(new JSONObject(paramString));
      return;
    }
    catch (JSONException paramString)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Generating JSONObject for sendTags failed!", paramString);
    }
  }
  
  public static void sendTags(JSONObject paramJSONObject)
  {
    sendTags(paramJSONObject, null);
  }
  
  public static void sendTags(JSONObject paramJSONObject, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (a("sendTags()")) {
      return;
    }
    paramJSONObject = new OneSignal.4(paramJSONObject, paramChangeTagsUpdateHandler);
    if ((b != null) && (!J()))
    {
      paramJSONObject.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
    if (paramChangeTagsUpdateHandler != null) {
      paramChangeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue."));
    }
    a(new OneSignal.b(paramJSONObject));
  }
  
  public static void setAppContext(@NonNull Context paramContext)
  {
    if (paramContext == null)
    {
      a(OneSignal.LOG_LEVEL.WARN, "setAppContext(null) is not valid, ignoring!");
      return;
    }
    int i1;
    if (b == null) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    b = paramContext.getApplicationContext();
    if (i1 != 0) {
      OneSignalPrefs.startDelayedWrite();
    }
  }
  
  public static void setEmail(@NonNull String paramString)
  {
    setEmail(paramString, null, null);
  }
  
  public static void setEmail(@NonNull String paramString, OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
    setEmail(paramString, null, paramEmailUpdateHandler);
  }
  
  public static void setEmail(@NonNull String paramString1, @Nullable String paramString2)
  {
    setEmail(paramString1, paramString2, null);
  }
  
  public static void setEmail(@NonNull String paramString1, @Nullable String paramString2, @Nullable OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
    if (a("setEmail()")) {
      return;
    }
    if (!bvi.a(paramString1))
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.VALIDATION, "Email is invalid"));
      }
      a(OneSignal.LOG_LEVEL.ERROR, "Email is invalid");
      return;
    }
    if ((T) && (paramString2 == null))
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.REQUIRES_EMAIL_AUTH, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard."));
      }
      a(OneSignal.LOG_LEVEL.ERROR, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard.");
      return;
    }
    n = paramEmailUpdateHandler;
    paramString1 = new OneSignal.24(paramString1, paramString2);
    if ((b != null) && (!J()))
    {
      paramString1.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
    a(new OneSignal.b(paramString1));
  }
  
  public static void setExternalUserId(String paramString)
  {
    if (a("setExternalId()")) {
      return;
    }
    paramString = new OneSignal.3(paramString);
    if ((b != null) && (!J()))
    {
      paramString.run();
      return;
    }
    a(new OneSignal.b(paramString));
  }
  
  public static void setInFocusDisplaying(int paramInt)
  {
    setInFocusDisplaying(e(paramInt));
  }
  
  public static void setInFocusDisplaying(OSInFocusDisplayOption paramOSInFocusDisplayOption)
  {
    getCurrentOrNewInitBuilder().h = true;
    getCurrentOrNewInitBuilder().i = paramOSInFocusDisplayOption;
  }
  
  public static void setLocationShared(boolean paramBoolean)
  {
    if (a("setLocationShared()")) {
      return;
    }
    f = paramBoolean;
    if (!paramBoolean) {
      bvn.d();
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("shareLocation:");
    localStringBuilder.append(f);
    a(localLOG_LEVEL, localStringBuilder.toString());
  }
  
  public static void setLogLevel(int paramInt1, int paramInt2)
  {
    setLogLevel(d(paramInt1), d(paramInt2));
  }
  
  public static void setLogLevel(OneSignal.LOG_LEVEL paramLOG_LEVEL1, OneSignal.LOG_LEVEL paramLOG_LEVEL2)
  {
    s = paramLOG_LEVEL1;
    r = paramLOG_LEVEL2;
  }
  
  public static void setRequiresUserPrivacyConsent(boolean paramBoolean)
  {
    if ((i) && (!paramBoolean))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
      return;
    }
    i = paramBoolean;
  }
  
  public static void setSubscription(boolean paramBoolean)
  {
    if (a("setSubscription()")) {
      return;
    }
    OneSignal.13 local13 = new OneSignal.13(paramBoolean);
    if ((b != null) && (!J()))
    {
      local13.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
    a(new OneSignal.b(local13));
  }
  
  public static Builder startInit(Context paramContext)
  {
    return new Builder(paramContext, null);
  }
  
  @Deprecated
  public static void syncHashedEmail(String paramString)
  {
    if (a("SyncHashedEmail()")) {
      return;
    }
    if (!bvi.a(paramString)) {
      return;
    }
    paramString = new OneSignal.23(paramString);
    if ((b != null) && (!J()))
    {
      paramString.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
    a(new OneSignal.b(paramString));
  }
  
  public static void t()
  {
    OneSignal.EmailUpdateHandler localEmailUpdateHandler = o;
    if (localEmailUpdateHandler != null)
    {
      localEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  public static void u()
  {
    OneSignal.EmailUpdateHandler localEmailUpdateHandler = n;
    if (localEmailUpdateHandler != null)
    {
      localEmailUpdateHandler.onSuccess();
      n = null;
    }
  }
  
  public static boolean userProvidedPrivacyConsent()
  {
    return j();
  }
  
  public static void v()
  {
    OneSignal.EmailUpdateHandler localEmailUpdateHandler = n;
    if (localEmailUpdateHandler != null)
    {
      localEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
      n = null;
    }
  }
  
  public static class Builder
  {
    Context a;
    OneSignal.NotificationOpenedHandler b;
    OneSignal.NotificationReceivedHandler c;
    boolean d;
    public boolean e;
    boolean f;
    boolean g;
    boolean h;
    OneSignal.OSInFocusDisplayOption i = OneSignal.OSInFocusDisplayOption.InAppAlert;
    
    private Builder() {}
    
    private Builder(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public Builder autoPromptLocation(boolean paramBoolean)
    {
      this.d = paramBoolean;
      return this;
    }
    
    public Builder disableGmsMissingPrompt(boolean paramBoolean)
    {
      this.e = paramBoolean;
      return this;
    }
    
    public Builder filterOtherGCMReceivers(boolean paramBoolean)
    {
      this.g = paramBoolean;
      return this;
    }
    
    public Builder inFocusDisplaying(OneSignal.OSInFocusDisplayOption paramOSInFocusDisplayOption)
    {
      OneSignal.getCurrentOrNewInitBuilder().h = false;
      this.i = paramOSInFocusDisplayOption;
      return this;
    }
    
    public void init()
    {
      OneSignal.a(this);
    }
    
    public Builder setNotificationOpenedHandler(OneSignal.NotificationOpenedHandler paramNotificationOpenedHandler)
    {
      this.b = paramNotificationOpenedHandler;
      return this;
    }
    
    public Builder setNotificationReceivedHandler(OneSignal.NotificationReceivedHandler paramNotificationReceivedHandler)
    {
      this.c = paramNotificationReceivedHandler;
      return this;
    }
    
    public Builder unsubscribeWhenNotificationsAreDisabled(boolean paramBoolean)
    {
      this.f = paramBoolean;
      return this;
    }
  }
  
  public static abstract interface NotificationOpenedHandler
  {
    public abstract void notificationOpened(OSNotificationOpenResult paramOSNotificationOpenResult);
  }
  
  public static abstract interface NotificationReceivedHandler
  {
    public abstract void notificationReceived(OSNotification paramOSNotification);
  }
  
  public static enum OSInFocusDisplayOption
  {
    private OSInFocusDisplayOption() {}
  }
}
