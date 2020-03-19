package br.com.easytaxi;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import br.com.easytaxi.data.Address;
import br.com.easytaxi.data.Area;
import br.com.easytaxi.data.Customer;
import br.com.easytaxi.data.Promotion;
import br.com.easytaxi.data.RideRequest.PayType;
import br.com.easytaxi.data.TariffNode;
import br.com.easytaxi.db.AreaInfoRecord;
import br.com.easytaxi.db.CreditCardRecord;
import br.com.easytaxi.db.DbManager;
import br.com.easytaxi.db.FavoriteRecord;
import br.com.easytaxi.db.GeohashMapRecord;
import br.com.easytaxi.request.AreaCodeRetryHandler;
import br.com.easytaxi.request.EasyRequest;
import br.com.easytaxi.util.CreditCardManager;
import br.com.easytaxi.util.FavoriteManager;
import br.com.easytaxi.util.RingCaptchaManager;
import com.AdX.tag.AdXConnect;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public class App
  extends Application
{
  public final ArrayList<TariffNode> allElementsTariff = new ArrayList();
  public final Map<String, Promotion> allPromotions = new HashMap();
  public CreditCardManager creditCardManager;
  public Area currentArea;
  public FavoriteManager favoriteManager;
  public boolean isAreaInfoUpdated;
  public boolean isPromotionMessageDismissed = false;
  public RideRequest.PayType lastPayType;
  public LocationHandler locationHandler;
  private SQLiteDatabase mDb;
  private DbManager mDbManager;
  private String mIMEI;
  private final AsyncTask<Customer, Void, Void> mInstalledAppsTask = new AsyncTask()
  {
    protected Void doInBackground(Customer... paramAnonymousVarArgs)
    {
      paramAnonymousVarArgs = paramAnonymousVarArgs[0];
      Object localObject = App.this.getPackageManager().getInstalledApplications(128);
      JSONArray localJSONArray = new JSONArray();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        int i = 0;
        while (i < S.PACKAGES_TO_MONITOR.length)
        {
          if (S.PACKAGES_TO_MONITOR[i].equalsIgnoreCase(localApplicationInfo.packageName)) {
            localJSONArray.put("P" + (i + 1));
          }
          i += 1;
        }
      }
      localObject = new JSONObject();
      try
      {
        ((JSONObject)localObject).put("info", localJSONArray);
        App.this.requestHandler.sendInfoReport(paramAnonymousVarArgs, (JSONObject)localObject);
        return null;
      }
      catch (Exception paramAnonymousVarArgs)
      {
        for (;;)
        {
          Log.e("easytaxi", "Error sending report");
        }
      }
    }
  };
  private boolean mIsActivityOpened = false;
  public EasyNotificationManager notificationManager;
  public EasyRequest requestHandler;
  public RideManager rideManager;
  private RingCaptchaManager ringCaptchaManager;
  public String versionProvider;
  
  public App() {}
  
  public String getIMEI()
  {
    if (this.mIMEI != null) {
      return this.mIMEI;
    }
    this.mIMEI = ((TelephonyManager)getSystemService("phone")).getDeviceId();
    return this.mIMEI;
  }
  
  public Promotion getMostPriorityPromotion()
  {
    Object localObject1 = null;
    Iterator localIterator = this.allPromotions.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (String)localIterator.next();
      localObject2 = (Promotion)this.allPromotions.get(localObject2);
      if ((localObject1 == null) || (((Promotion)localObject2).order < localObject1.order)) {
        localObject1 = localObject2;
      }
    }
    return localObject1;
  }
  
  public Promotion getPromotionWithCode(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    Iterator localIterator = this.allPromotions.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      localObject = (Promotion)this.allPromotions.get(localObject);
      if (paramString.equalsIgnoreCase(((Promotion)localObject).easyCode)) {
        return localObject;
      }
    }
    return null;
  }
  
  public RingCaptchaManager getRingCaptchaManager()
  {
    if (this.ringCaptchaManager != null) {
      return this.ringCaptchaManager;
    }
    this.ringCaptchaManager = new RingCaptchaManager(this);
    return this.ringCaptchaManager;
  }
  
  public boolean isActivityOpened()
  {
    return this.mIsActivityOpened;
  }
  
  public boolean isPaymentEnabled(boolean paramBoolean)
  {
    if ((!paramBoolean) && ((this.lastPayType == RideRequest.PayType.WALLET) || (this.lastPayType == RideRequest.PayType.PROMOTION) || (this.lastPayType == RideRequest.PayType.CORPORATE))) {}
    do
    {
      do
      {
        return true;
        if (!this.isAreaInfoUpdated) {
          break;
        }
        if (this.currentArea == null) {
          return false;
        }
      } while (this.currentArea.paymentInfo != null);
      return false;
    } while ((this.locationHandler.address != null) && ("BR".equals(this.locationHandler.address.country)));
    return false;
  }
  
  public void onActivityPause()
  {
    Log.d("easytaxi", "onActivityPause");
    this.mIsActivityOpened = false;
    this.notificationManager.onActivityChanged(this.mIsActivityOpened);
  }
  
  public void onActivityResume()
  {
    Log.d("easytaxi", "onActivityResume");
    this.mIsActivityOpened = true;
    this.notificationManager.onActivityChanged(this.mIsActivityOpened);
  }
  
  public void onCreate()
  {
    super.onCreate();
    SharedPreferences localSharedPreferences = getSharedPreferences("prefs", 0);
    Object localObject1 = localSharedPreferences.getString("id", null);
    this.versionProvider = localSharedPreferences.getString("dist", null);
    Object localObject2 = localSharedPreferences.getString("lastPaymentMethod", null);
    if (localObject2 != null) {
      this.lastPayType = RideRequest.PayType.valueOf((String)localObject2);
    }
    if (localObject1 == null)
    {
      localObject1 = UUID.randomUUID().toString();
      localObject2 = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject2).putString("id", (String)localObject1);
      ((SharedPreferences.Editor)localObject2).commit();
      AdXConnect.getAdXConnectInstance(getApplicationContext(), false, 6);
    }
    for (;;)
    {
      int i = 0;
      try
      {
        int j = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        i = j;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          long l;
          Log.e("easytaxi", "Error getting app version");
        }
      }
      this.mDbManager = new DbManager(this);
      this.mDb = this.mDbManager.open();
      FavoriteRecord.init(this.mDb);
      CreditCardRecord.init(this.mDb);
      GeohashMapRecord.init(this.mDb);
      AreaInfoRecord.init(this.mDb);
      if (this.mDbManager.isUpgrade())
      {
        localObject1 = localSharedPreferences.edit();
        ((SharedPreferences.Editor)localObject1).putLong("areaCodeLastUpdateTs", 0L);
        ((SharedPreferences.Editor)localObject1).commit();
      }
      this.notificationManager = new EasyNotificationManager(this);
      this.requestHandler = new EasyRequest(i, this);
      this.rideManager = new RideManager(this, this.notificationManager);
      this.favoriteManager = new FavoriteManager(this);
      this.creditCardManager = new CreditCardManager();
      this.locationHandler = new LocationHandler(this);
      localObject1 = Customer.getFromPreferences(this);
      this.mInstalledAppsTask.execute(new Customer[] { localObject1 });
      localObject1 = AirshipConfigOptions.loadDefaultOptions(this);
      ((AirshipConfigOptions)localObject1).developmentAppKey = "Wb-rYHqfQ8SfYpvD1zVOfg";
      ((AirshipConfigOptions)localObject1).developmentAppSecret = "OA6oTU1jQ225pzAouwlsmw";
      ((AirshipConfigOptions)localObject1).productionAppKey = "GmdZMVaaQwK82seqoQgk7Q";
      ((AirshipConfigOptions)localObject1).productionAppSecret = "pB11Y1oPQ-SpJbO-M6wmrg";
      ((AirshipConfigOptions)localObject1).transport = "gcm";
      ((AirshipConfigOptions)localObject1).gcmSender = "934680140589";
      ((AirshipConfigOptions)localObject1).inProduction = true;
      UAirship.takeOff(this, (AirshipConfigOptions)localObject1);
      PushManager.enablePush();
      PushManager.shared().setIntentReceiver(UrbanPushReceiver.class);
      l = localSharedPreferences.getLong("areaCodeLastUpdateTs", 0L);
      if (l != 0L) {
        this.isAreaInfoUpdated = true;
      }
      new AreaCodeRetryHandler(this, l).run();
      return;
      AdXConnect.getAdXConnectInstance(getApplicationContext(), true, 6);
    }
  }
  
  public void onTerminate()
  {
    super.onTerminate();
    if (this.mDbManager != null) {
      this.mDbManager.close();
    }
  }
}
