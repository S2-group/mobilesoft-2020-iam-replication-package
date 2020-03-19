import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import com.onesignal.SyncService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class cbc
{
  public static String Qd;
  static cbd bmA;
  static Collection<JSONArray> bmB = new ArrayList();
  private static cbe bmC;
  private static boolean bmD;
  private static boolean bmE;
  private static boolean bmF;
  private static JSONObject bmG;
  public static boolean bmH;
  static String bmc;
  public static Context bmd;
  private static cbg bme = cbg.bmR;
  private static cbg bmf = cbg.bmU;
  private static int bmg;
  static boolean bmh;
  private static cbf bmi;
  private static long bmj;
  private static long bmk;
  private static cby bml;
  private static cbw bmm;
  private static bzz bmn;
  private static int bmo;
  public static String bmp;
  private static cbb bmq;
  private static String bmr;
  private static boolean bms;
  private static boolean bmt;
  private static boolean bmu;
  private static boolean bmv;
  private static Double bmw;
  private static Double bmx;
  private static Float bmy;
  private static Integer bmz;
  private static boolean foreground;
  private static String userId = null;
  
  static
  {
    bmj = 1L;
    bmk = -1L;
    bmn = new bzy();
    bmp = "native";
  }
  
  public cbc() {}
  
  static String L(Bundle paramBundle)
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
        a(cbg.bmW, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(cbg.bmW, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(cbg.bmW, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return null;
  }
  
  private static void NA()
  {
    boolean bool2 = false;
    if (bmE) {
      return;
    }
    bmE = true;
    Object localObject;
    if (bmo == 2)
    {
      localObject = new cbt();
      bms = false;
      if (bmF) {
        bmt = false;
      }
      ((cbr)localObject).a(bmd, bmc, new cbs()
      {
        public void o(String paramAnonymousString, int paramAnonymousInt)
        {
          if (paramAnonymousInt < 1) {
            if ((cbn.Ok() == null) || (cbc.NQ() < -6)) {
              cbc.gX(paramAnonymousInt);
            }
          }
          for (;;)
          {
            cbc.gJ(paramAnonymousString);
            cbc.bA(true);
            cbc.NR();
            return;
            if (cbc.NQ() < -6) {
              cbc.gX(paramAnonymousInt);
            }
          }
        }
      });
      localObject = bmd;
      if ((!bmA.bmO) || (bmv)) {
        break label214;
      }
    }
    label214:
    for (boolean bool1 = true;; bool1 = false)
    {
      cai.a((Context)localObject, bool1, new cak()
      {
        public void c(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
        {
          cbc.a(paramAnonymousDouble1);
          cbc.b(paramAnonymousDouble2);
          cbc.a(paramAnonymousFloat);
          cbc.g(paramAnonymousInteger);
          cbc.bB(true);
          cbc.NR();
        }
      });
      if (!bmu)
      {
        cbm local5 = new cbm()
        {
          void T(String paramAnonymousString)
          {
            try
            {
              cbc.w(new JSONObject(paramAnonymousString).getJSONObject("awl_list"));
              cbc.bC(true);
              cbc.NR();
              return;
            }
            catch (Throwable paramAnonymousString)
            {
              for (;;)
              {
                paramAnonymousString.printStackTrace();
              }
            }
          }
          
          void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
          {
            cbc.bC(true);
            cbc.NR();
          }
        };
        String str1 = "apps/" + Qd + "/awl";
        String str2 = lu();
        localObject = str1;
        if (str2 != null) {
          localObject = str1 + "?player_id=" + str2;
        }
        cbl.a((String)localObject, local5);
      }
      if (!bmv)
      {
        bool1 = bool2;
        if (!bmA.bmO) {}
      }
      else
      {
        bool1 = true;
      }
      bmv = bool1;
      return;
      localObject = new cbu();
      break;
    }
  }
  
  private static void NB()
  {
    Iterator localIterator = bmB.iterator();
    while (localIterator.hasNext()) {
      a((JSONArray)localIterator.next(), true, false);
    }
    bmB.clear();
  }
  
  static void NC()
  {
    NP();
    foreground = true;
    bmj = SystemClock.elapsedRealtime();
    bmF = NO();
    bw(System.currentTimeMillis());
    NA();
    if (bml != null) {
      bml.OL();
    }
    caq.bK(bmd);
  }
  
  static boolean ND()
  {
    return foreground;
  }
  
  private static int NE()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int j = localTimeZone.getRawOffset();
    int i = j;
    if (localTimeZone.inDaylightTime(new Date())) {
      i = j + localTimeZone.getDSTSavings();
    }
    return i / 1000;
  }
  
  private static void NF()
  {
    a(cbg.bmW, "registerUser: registerForPushFired:" + bms + ", locationFired: " + bmt + ", awlFired: " + bmu);
    if ((!bms) || (!bmt) || (!bmu)) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        cbq localCbq = cbn.Og();
        String str = cbc.bmd.getPackageName();
        Object localObject3 = cbc.bmd.getPackageManager();
        localCbq.t("app_id", cbc.Qd);
        localCbq.t("identifier", cbc.NS());
        Object localObject2 = cbc.NT().bE(cbc.bmd);
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new bzx().bE(cbc.bmd);
        }
        localCbq.t("ad_id", localObject1);
        localCbq.t("device_os", Build.VERSION.RELEASE);
        localCbq.t("timezone", Integer.valueOf(cbc.NU()));
        localCbq.t("language", cbb.Nz());
        localCbq.t("sdk", "030100");
        localCbq.t("sdk_type", cbc.bmp);
        localCbq.t("android_package", str);
        localCbq.t("device_model", Build.MODEL);
        localCbq.t("device_type", Integer.valueOf(cbc.NV()));
        localCbq.u("subscribableStatus", Integer.valueOf(cbc.NQ()));
        try
        {
          localCbq.t("game_version", Integer.valueOf(((PackageManager)localObject3).getPackageInfo(str, 0).versionCode));
          try
          {
            localObject1 = ((PackageManager)localObject3).getInstalledPackages(0);
            localObject2 = new JSONArray();
            localObject3 = MessageDigest.getInstance("SHA-256");
            i = 0;
            if (i < ((List)localObject1).size())
            {
              ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
              str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
              if (!cbc.NW().has(str)) {
                break label395;
              }
              ((JSONArray)localObject2).put(str);
              break label395;
            }
            localCbq.t("pkgs", localObject2);
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          localCbq.t("net_type", cbc.NX().Nx());
          localCbq.t("carrier", cbc.NX().Ny());
          localCbq.t("rooted", Boolean.valueOf(cbv.OG()));
          localCbq.t("lat", cbc.NY());
          localCbq.t("long", cbc.NZ());
          localCbq.t("loc_acc", cbc.Oa());
          localCbq.t("loc_type", cbc.Ob());
          cbn.a(localCbq, cbc.Oc());
          cbc.bD(false);
          return;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            int i;
            continue;
            label395:
            i += 1;
          }
        }
      }
    }).start();
  }
  
  static void NG()
  {
    if (bmi != null) {
      runOnUiThread(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void NH()
  {
    if (bmi == null) {}
    String str1;
    do
    {
      String str2;
      do
      {
        return;
        str1 = cbn.Ok();
        if (!cbn.Oj()) {
          str1 = null;
        }
        str2 = lu();
      } while (str2 == null);
      bmi.b(str2, str1);
    } while (str1 == null);
    bmi = null;
  }
  
  public static String NI()
  {
    return bO(bmd);
  }
  
  static boolean NJ()
  {
    if (bmA == null) {}
    while (bmA.bmQ == cbj.bnb) {
      return true;
    }
    return false;
  }
  
  static boolean NK()
  {
    if (bmA == null) {}
    while (bmA.bmQ != cbj.bna) {
      return false;
    }
    return true;
  }
  
  public static long NL()
  {
    if ((bmk == -1L) && (bmd != null)) {
      bmk = bT(bmd).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(cbg.bmV, "GetUnsentActiveTime: " + bmk);
    return bmk;
  }
  
  static boolean NM()
  {
    return (bmh) && (ND());
  }
  
  static void NN()
  {
    bmF = false;
    bw(System.currentTimeMillis());
  }
  
  private static boolean NO()
  {
    return (System.currentTimeMillis() - bS(bmd)) / 1000L >= 30L;
  }
  
  private static void NP()
  {
    if (bmH) {
      return;
    }
    bmH = true;
    bmd.startService(new Intent(bmd, SyncService.class));
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, cbh paramCbh, cbi paramCbi)
  {
    if (bmA == null) {
      bmA = new cbd(null);
    }
    bmA.bmM = paramCbh;
    bmA.bmN = paramCbi;
    bmc = paramString1;
    bmq = new cbb();
    bmo = bmq.getDeviceType();
    bmg = cbb.b(bmo, paramString1, paramString2);
    if (bmg == 64537) {}
    do
    {
      return;
      if (!bmh) {
        break;
      }
      if (paramContext != null) {
        bmd = paramContext.getApplicationContext();
      }
    } while (bmA.bmM == null);
    NB();
    return;
    boolean bool = paramContext instanceof Activity;
    foreground = bool;
    Qd = paramString2;
    bmd = paramContext.getApplicationContext();
    if (bool)
    {
      bzr.bkH = (Activity)paramContext;
      caq.bK(bmd);
      NP();
    }
    for (;;)
    {
      bmj = SystemClock.elapsedRealtime();
      cbn.bV(bmd);
      if (Build.VERSION.SDK_INT > 13) {
        ((Application)bmd).registerActivityLifecycleCallbacks(new bzv());
      }
      try
      {
        label185:
        Class.forName("com.amazon.device.iap.PurchasingListener");
        bmm = new cbw(bmd);
        paramContext = NI();
        if (paramContext != null) {
          if (!paramContext.equals(Qd))
          {
            a(cbg.bmW, "APP ID changed, clearing user id as it is no longer valid.");
            gG(Qd);
            cbn.Ol();
          }
        }
        for (;;)
        {
          if ((foreground) || (lu() == null))
          {
            bmF = NO();
            bw(System.currentTimeMillis());
            NA();
          }
          if (bmA.bmM != null) {
            NB();
          }
          if (cby.bW(bmd)) {
            bml = new cby(bmd);
          }
          bmh = true;
          return;
          bzr.bkG = true;
          break;
          bzw.Np();
          break label185;
          caf.c(0, bmd);
          gG(Qd);
        }
      }
      catch (ClassNotFoundException paramContext)
      {
        for (;;) {}
      }
    }
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    b(paramContext, paramJSONArray);
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(cbb.G(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    a(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      bN(paramContext);
    }
  }
  
  private static void a(caw paramCaw)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
    {
      bmA.bmM.b(paramCaw);
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        cbc.bmA.bmM.b(cbc.this);
      }
    });
  }
  
  private static void a(cbd paramCbd)
  {
    bmA = paramCbd;
    paramCbd = bmA.mContext;
    bmA.mContext = null;
    try
    {
      Bundle localBundle = paramCbd.getPackageManager().getApplicationInfo(paramCbd.getPackageName(), 128).metaData;
      a(paramCbd, localBundle.getString("onesignal_google_project_number").substring(4), localBundle.getString("onesignal_app_id"), bmA.bmM, bmA.bmN);
      return;
    }
    catch (Throwable paramCbd)
    {
      paramCbd.printStackTrace();
    }
  }
  
  private static void a(cbe paramCbe)
  {
    if (paramCbe == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        if (!cbc.Od()) {}
        cbo localCbo;
        for (boolean bool = true;; bool = false)
        {
          localCbo = cbn.bG(bool);
          if (localCbo.bnr) {
            cbc.bE(true);
          }
          if ((localCbo.bns != null) && (!localCbo.toString().equals("{}"))) {
            break;
          }
          cbc.this.x(null);
          return;
        }
        cbc.this.x(localCbo.bns);
      }
    }).start();
  }
  
  public static void a(cbf paramCbf)
  {
    bmi = paramCbf;
    if (lu() != null) {
      NH();
    }
  }
  
  static void a(cbg paramCbg, String paramString)
  {
    a(paramCbg, paramString, null);
  }
  
  static void a(cbg paramCbg, final String paramString, Throwable paramThrowable)
  {
    if (paramCbg.compareTo(bmf) < 1)
    {
      if (paramCbg != cbg.bmX) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramCbg.compareTo(bme) < 1) && (bzr.bkH != null)) {}
      try
      {
        Object localObject = paramString + "\n";
        paramString = (String)localObject;
        if (paramThrowable != null)
        {
          paramString = (String)localObject + paramThrowable.getMessage();
          localObject = new StringWriter();
          paramThrowable.printStackTrace(new PrintWriter((Writer)localObject));
          paramString = paramString + ((StringWriter)localObject).toString();
        }
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (bzr.bkH != null) {
              new AlertDialog.Builder(bzr.bkH).setTitle(cbc.this.toString()).setMessage(paramString).show();
            }
          }
        });
        return;
      }
      catch (Throwable paramCbg)
      {
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramCbg);
      }
      if (paramCbg == cbg.bmW) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramCbg == cbg.bmV) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramCbg == cbg.bmU) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramCbg == cbg.bmT) || (paramCbg == cbg.bmS)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
  }
  
  private static void a(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (a(cbg.bmV)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(cbg.bmU, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, cbm paramCbm)
  {
    if (lu() == null) {
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("app_id", Qd);
      if (paramBoolean) {
        localJSONObject.put("existing", true);
      }
      localJSONObject.put("purchases", paramJSONArray);
      cbl.b("players/" + lu() + "/on_purchase", localJSONObject, paramCbm);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(cbg.bmT, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  private static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((bmA == null) || (bmA.bmM == null))
    {
      bmB.add(paramJSONArray);
      return;
    }
    a(b(paramJSONArray, paramBoolean1, paramBoolean2));
  }
  
  private static boolean a(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool1 = false;
    int j = paramJSONArray.length();
    int i = 0;
    for (;;)
    {
      boolean bool2;
      if (i < j) {
        try
        {
          Object localObject = paramJSONArray.getJSONObject(i);
          if (!((JSONObject)localObject).has("custom"))
          {
            bool2 = bool1;
          }
          else
          {
            localObject = new JSONObject(((JSONObject)localObject).optString("custom"));
            bool2 = bool1;
            if (((JSONObject)localObject).has("u"))
            {
              String str = ((JSONObject)localObject).optString("u", null);
              localObject = str;
              if (!str.contains("://")) {
                localObject = "http://" + str;
              }
              localObject = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject));
              ((Intent)localObject).addFlags(1476919296);
              paramContext.startActivity((Intent)localObject);
              bool2 = true;
            }
          }
        }
        catch (Throwable localThrowable)
        {
          a(cbg.bmT, "Error parsing JSON item " + i + "/" + j + " for launching a web URL.", localThrowable);
          bool2 = bool1;
        }
      }
      return bool1;
      i += 1;
      bool1 = bool2;
    }
  }
  
  static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = v(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(cbg paramCbg)
  {
    return (paramCbg.compareTo(bme) < 1) || (paramCbg.compareTo(bmf) < 1);
  }
  
  static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    paramContext = cbk.bU(paramContext).getReadableDatabase().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
    boolean bool = paramContext.moveToFirst();
    paramContext.close();
    if (bool)
    {
      a(cbg.bmW, "Duplicate GCM message received, skip processing of " + paramString);
      return true;
    }
    return false;
  }
  
  @NonNull
  private static caw b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int k = paramJSONArray.length();
    int i = 1;
    caw localCaw = new caw();
    car localCar = new car();
    localCar.blq = NM();
    localCar.blr = paramBoolean1;
    localCar.bls = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int j = 0;
    if (j < k) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(j);
      if (!((JSONObject)localObject2).has("custom")) {
        break label328;
      }
      localCar.blt = cal.s((JSONObject)localObject2);
      if ((localObject1 != null) || (!((JSONObject)localObject2).has("actionSelected"))) {
        break label328;
      }
      localObject2 = ((JSONObject)localObject2).optString("actionSelected", null);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        cat localCat;
      }
    }
    if (i != 0) {
      i = 0;
    }
    for (;;)
    {
      j += 1;
      break;
      try
      {
        if (localCar.blv == null) {
          localCar.blv = new ArrayList();
        }
        localCar.blv.add(localCar.blt);
      }
      catch (Throwable localThrowable1) {}
      a(cbg.bmT, "Error parsing JSON item " + j + "/" + k + " for callback.", localThrowable1);
    }
    localCaw.blF = localCar;
    localCaw.blG = new cat();
    localCaw.blG.blB = localObject1;
    localCat = localCaw.blG;
    if (localObject1 != null) {}
    for (paramJSONArray = cau.blD;; paramJSONArray = cau.blC)
    {
      localCat.blA = paramJSONArray;
      if (!paramBoolean2) {
        break;
      }
      localCaw.blF.blu = cas.blx;
      return localCaw;
    }
    localCaw.blF.blu = cas.blw;
    return localCaw;
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i = 0;
    for (;;)
    {
      if (i < paramJSONArray.length()) {
        try
        {
          Object localObject = paramJSONArray.getJSONObject(i);
          if (!((JSONObject)localObject).has("custom")) {
            break label155;
          }
          localObject = new JSONObject(((JSONObject)localObject).optString("custom", null));
          if (!((JSONObject)localObject).has("i")) {
            break label155;
          }
          localObject = ((JSONObject)localObject).optString("i", null);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", bO(paramContext));
          localJSONObject.put("player_id", bP(paramContext));
          localJSONObject.put("opened", true);
          cbl.a("notifications/" + (String)localObject, localJSONObject, new cbm()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              cbc.b("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(cbg.bmT, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label155:
      i += 1;
    }
  }
  
  public static cbd bM(Context paramContext)
  {
    return new cbd(paramContext, null);
  }
  
  private static void bN(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  private static String bO(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return bT(paramContext).getString("GT_APP_ID", null);
  }
  
  private static String bP(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return bT(paramContext).getString("GT_PLAYER_ID", null);
  }
  
  static boolean bQ(Context paramContext)
  {
    return bT(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  static boolean bR(Context paramContext)
  {
    return bT(paramContext).getBoolean("GT_SOUND_ENABLED", true);
  }
  
  static long bS(Context paramContext)
  {
    return bT(paramContext).getLong("OS_LAST_SESSION_TIME", -31000L);
  }
  
  static SharedPreferences bT(Context paramContext)
  {
    return paramContext.getSharedPreferences(cbc.class.getSimpleName(), 0);
  }
  
  static void bw(long paramLong)
  {
    SharedPreferences.Editor localEditor = bT(bmd).edit();
    localEditor.putLong("OS_LAST_SESSION_TIME", paramLong);
    localEditor.apply();
  }
  
  private static void bx(long paramLong)
  {
    bmk = paramLong;
    if (bmd == null) {
      return;
    }
    a(cbg.bmV, "SaveUnsentActiveTime: " + bmk);
    SharedPreferences.Editor localEditor = bT(bmd).edit();
    localEditor.putLong("GT_UNSENT_ACTIVE_TIME", paramLong);
    localEditor.commit();
  }
  
  public static void bz(boolean paramBoolean)
  {
    foreground = false;
    if (!bmh) {}
    long l;
    do
    {
      do
      {
        return;
        if (bmm != null) {
          bmm.OK();
        }
      } while (bmj == -1L);
      l = ((SystemClock.elapsedRealtime() - bmj) / 1000.0D + 0.5D);
      bmj = SystemClock.elapsedRealtime();
    } while ((l < 0L) || (l > 86400L));
    if (bmd == null)
    {
      a(cbg.bmT, "Android Context not found, please call OneSignal.init when your app starts.");
      return;
    }
    bw(System.currentTimeMillis());
    l += NL();
    if ((paramBoolean) || (l < 60L) || (lu() == null))
    {
      bx(l);
      return;
    }
    c(l, true);
  }
  
  public static void c(long paramLong, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("app_id", Qd);
      localJSONObject.put("type", 1);
      localJSONObject.put("state", "ping");
      localJSONObject.put("active_time", paramLong);
      u(localJSONObject);
      String str = "players/" + lu() + "/on_focus";
      cbm local7 = new cbm()
      {
        void T(String paramAnonymousString)
        {
          cbc.by(0L);
        }
        
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          cbc.b("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
      };
      if (paramBoolean)
      {
        cbl.d(str, localJSONObject, local7);
        return;
      }
      cbl.b(str, localJSONObject, local7);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(cbg.bmT, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  static void c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((bmA == null) || (bmA.bmN == null)) {
      return;
    }
    paramJSONArray = b(paramJSONArray, paramBoolean1, paramBoolean2);
    bmA.bmN.a(paramJSONArray.blF);
  }
  
  private static void gG(String paramString)
  {
    if (bmd == null) {
      return;
    }
    SharedPreferences.Editor localEditor = bT(bmd).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  static void gH(String paramString)
  {
    userId = paramString;
    if (bmd == null) {
      return;
    }
    paramString = bT(bmd).edit();
    paramString.putString("GT_PLAYER_ID", userId);
    paramString.commit();
  }
  
  static void gI(String paramString)
  {
    gH(paramString);
    NG();
    a(bmC);
  }
  
  public static String lu()
  {
    if ((userId == null) && (bmd != null)) {
      userId = bT(bmd).getString("GT_PLAYER_ID", null);
    }
    return userId;
  }
  
  static void runOnUiThread(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }
  
  private static void u(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", bmq.Nx());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static String v(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject.optString("custom")).optString("i", null);
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject) {}
    return null;
  }
}
