package co.madseven.launcher.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import co.madseven.launcher.http.ads.AdvertisedAppsServices;
import co.madseven.launcher.http.ads.AppsInventoryService;
import co.madseven.launcher.http.ads.IAdvertisedApps;
import co.madseven.launcher.http.ads.IAppsInventory;
import co.madseven.launcher.http.ads.beans.AdvertisedAppItem;
import co.madseven.launcher.http.ads.beans.AdvertisedAppsResponse;
import co.madseven.launcher.http.ads.beans.AppsInventoryBody;
import co.madseven.launcher.settings.preferences.Preferences;
import co.madseven.launcher.tracking.ApoloTracker;
import co.madseven.launcher.utils.DispatchGroup;
import com.android.launcher3.Utilities;
import com.appnext.core.AppnextError;
import com.appnext.nativeads.NativeAd;
import com.appnext.nativeads.NativeAdListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsManager
{
  private static final long ADS_DISPLAY_PERIOD_MS = 86400000L;
  private static final long ADS_MAX_BAN_PERIOD_MS = 432000000L;
  private static AdsManager _instance;
  private List<AdvertisedAppItem> mAppsOfTheDay = new ArrayList();
  private HashMap<String, Long> mBanAdsCandidates = null;
  private HashMap<String, Long> mBannedAds = null;
  private DispatchGroup mDispatchGroup = new DispatchGroup();
  private final Gson mGson = new GsonBuilder().create();
  private ArrayList<NativeAd> mNativeAds = new ArrayList();
  private HashMap<String, NativeAd> mNativeAdsBuffer = new HashMap();
  private List<AdvertisedAppItem> mRecommendedApps = new ArrayList();
  
  private AdsManager() {}
  
  public static AdsManager getInstance()
  {
    if (_instance == null) {
      _instance = new AdsManager();
    }
    return _instance;
  }
  
  private boolean isAppNextAdBanned(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Preferences.getInstance().getPrefs(paramContext);
      loadLoggedAds(paramContext);
      if ((this.mBannedAds != null) && (paramString != null))
      {
        long l1;
        if (this.mBannedAds.containsKey(paramString))
        {
          l1 = ((Long)this.mBannedAds.get(paramString)).longValue();
          if (System.currentTimeMillis() - l1 <= 432000000L) {
            break label322;
          }
          this.mBannedAds.remove(paramString);
          paramContext.edit().putString("pref_ads_banned_times", this.mGson.toJson(this.mBannedAds)).apply();
          this.mBanAdsCandidates.remove(paramString);
          paramContext.edit().putString("pref_ads_ban_candidates_times", this.mGson.toJson(this.mBanAdsCandidates)).apply();
          return false;
        }
        if (this.mBanAdsCandidates.containsKey(paramString))
        {
          l1 = ((Long)this.mBanAdsCandidates.get(paramString)).longValue();
          long l2 = System.currentTimeMillis();
          Calendar localCalendar = Calendar.getInstance();
          int i = localCalendar.get(7);
          localCalendar.setTimeInMillis(l1);
          int j = localCalendar.get(7);
          if ((l2 - l1 > 86400000L) || (i != j))
          {
            this.mBanAdsCandidates.remove(paramString);
            paramContext.edit().putString("pref_ads_ban_candidates_times", this.mGson.toJson(this.mBanAdsCandidates)).apply();
            if (!this.mBannedAds.containsKey(paramString))
            {
              this.mBannedAds.put(paramString, Long.valueOf(l1));
              paramContext.edit().putString("pref_ads_banned_times", this.mGson.toJson(this.mBannedAds)).apply();
            }
            return true;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
    label322:
    return true;
  }
  
  private void loadLoggedAds(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences != null) {
      try
      {
        if (this.mBanAdsCandidates == null)
        {
          String str = paramSharedPreferences.getString("pref_ads_ban_candidates_times", null);
          if ((str != null) && (str.length() > 0)) {
            this.mBanAdsCandidates = ((HashMap)this.mGson.fromJson(str, new TypeToken() {}.getType()));
          } else {
            this.mBanAdsCandidates = new HashMap();
          }
        }
        if (this.mBannedAds == null)
        {
          paramSharedPreferences = paramSharedPreferences.getString("pref_ads_banned_times", null);
          if ((paramSharedPreferences != null) && (paramSharedPreferences.length() > 0))
          {
            this.mBannedAds = ((HashMap)this.mGson.fromJson(paramSharedPreferences, new TypeToken() {}.getType()));
            return;
          }
          this.mBannedAds = new HashMap();
          return;
        }
      }
      catch (Throwable paramSharedPreferences)
      {
        paramSharedPreferences.printStackTrace();
      }
    }
  }
  
  public void fetchAdvertisedApps(final Context paramContext, final DispatchGroup paramDispatchGroup, Locale paramLocale, String paramString)
  {
    if (paramContext != null) {
      try
      {
        Object localObject = Preferences.getInstance().getPrefs(paramContext);
        String str = ((SharedPreferences)localObject).getString("PREF_USER_UUID", null);
        localObject = ((SharedPreferences)localObject).getString("PREF_USER_GAID", null);
        if (paramDispatchGroup != null) {
          paramDispatchGroup.enter();
        }
        AdvertisedAppsServices.getInstance(paramContext).fetchAdvertisedApps(str, (String)localObject, String.valueOf(Build.VERSION.SDK_INT), paramLocale.getLanguage(), paramString, Boolean.valueOf(true)).enqueue(new Callback()
        {
          public void onFailure(Call<AdvertisedAppsResponse> paramAnonymousCall, Throwable paramAnonymousThrowable)
          {
            try
            {
              ApoloTracker.getInstance(paramContext).sentEvent("Erreur WS", "api/inapp", paramAnonymousThrowable.getLocalizedMessage());
            }
            catch (Throwable paramAnonymousCall)
            {
              paramAnonymousCall.printStackTrace();
            }
            if (paramDispatchGroup != null) {
              paramDispatchGroup.leave();
            }
          }
          
          public void onResponse(Call<AdvertisedAppsResponse> paramAnonymousCall, Response<AdvertisedAppsResponse> paramAnonymousResponse)
          {
            AdsManager.this.mRecommendedApps.clear();
            AdsManager.this.mAppsOfTheDay.clear();
            if (paramAnonymousResponse.body() != null)
            {
              paramAnonymousCall = (AdvertisedAppsResponse)paramAnonymousResponse.body();
              if (paramAnonymousCall != null)
              {
                Object localObject1;
                if (paramAnonymousCall.getRecommendedApps() != null)
                {
                  paramAnonymousResponse = paramAnonymousCall.getRecommendedApps().iterator();
                  while (paramAnonymousResponse.hasNext())
                  {
                    localObject1 = (AdvertisedAppItem)paramAnonymousResponse.next();
                    Object localObject2 = ((AdvertisedAppItem)localObject1).getPackageName();
                    if (localObject2 != null) {}
                    try
                    {
                      localObject2 = paramContext.getPackageManager().getPackageInfo((String)localObject2, 0);
                      if (localObject2 != null) {
                        i = 1;
                      }
                    }
                    catch (Exception localException2)
                    {
                      int i;
                      for (;;) {}
                    }
                    i = 0;
                    if (i == 0) {
                      AdsManager.this.mRecommendedApps.add(localObject1);
                    }
                  }
                }
                if (paramAnonymousCall.getAppsOfTheDay() != null)
                {
                  paramAnonymousCall = paramAnonymousCall.getAppsOfTheDay().iterator();
                  while (paramAnonymousCall.hasNext())
                  {
                    paramAnonymousResponse = (AdvertisedAppItem)paramAnonymousCall.next();
                    localObject1 = paramAnonymousResponse.getPackageName();
                    if (localObject1 != null) {}
                    try
                    {
                      localObject1 = paramContext.getPackageManager().getPackageInfo((String)localObject1, 0);
                      if (localObject1 != null) {
                        i = 1;
                      }
                    }
                    catch (Exception localException1)
                    {
                      for (;;) {}
                    }
                    i = 0;
                    if (i == 0) {
                      AdsManager.this.mAppsOfTheDay.add(paramAnonymousResponse);
                    }
                  }
                }
                if (AdsManager.this.mAppsOfTheDay.isEmpty()) {
                  AdsManager.this.mAppsOfTheDay.addAll(AdsManager.this.mRecommendedApps);
                }
              }
            }
            if (AdsManager.this.mRecommendedApps.size() > 0)
            {
              paramAnonymousCall = new AdvertisedAppItem();
              paramAnonymousCall.setId("id_more");
              AdsManager.this.mRecommendedApps.add(paramAnonymousCall);
            }
            if (paramDispatchGroup != null) {
              paramDispatchGroup.leave();
            }
          }
        });
        return;
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  /* Error */
  public void fetchAppNextAds(final Context paramContext, String[] paramArrayOfString, final NativeAdsListener paramNativeAdsListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +210 -> 213
    //   6: aload_1
    //   7: invokestatic 280	co/madseven/launcher/LauncherExtension:isAppnextEnabled	(Landroid/content/Context;)Z
    //   10: ifeq +203 -> 213
    //   13: aload_0
    //   14: getfield 76	co/madseven/launcher/ads/AdsManager:mDispatchGroup	Lco/madseven/launcher/utils/DispatchGroup;
    //   17: invokevirtual 283	co/madseven/launcher/utils/DispatchGroup:getCount	()I
    //   20: ifne +193 -> 213
    //   23: aload_1
    //   24: invokestatic 288	com/android/launcher3/LauncherAppState:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/LauncherAppState;
    //   27: invokevirtual 292	com/android/launcher3/LauncherAppState:getInvariantDeviceProfile	()Lcom/android/launcher3/InvariantDeviceProfile;
    //   30: astore 7
    //   32: aload_2
    //   33: ifnull +11 -> 44
    //   36: aload_2
    //   37: astore 6
    //   39: aload_2
    //   40: arraylength
    //   41: ifne +8 -> 49
    //   44: getstatic 298	com/android/launcher3/config/Constants$SDK:APPNEXT_PLACEMENT_IDS	[Ljava/lang/String;
    //   47: astore 6
    //   49: aload 7
    //   51: getfield 303	com/android/launcher3/InvariantDeviceProfile:numColumns	I
    //   54: aload 6
    //   56: arraylength
    //   57: invokestatic 309	java/lang/Math:min	(II)I
    //   60: istore 5
    //   62: aload_0
    //   63: getfield 76	co/madseven/launcher/ads/AdsManager:mDispatchGroup	Lco/madseven/launcher/utils/DispatchGroup;
    //   66: new 14	co/madseven/launcher/ads/AdsManager$5
    //   69: dup
    //   70: aload_0
    //   71: aload_1
    //   72: aload_3
    //   73: invokespecial 312	co/madseven/launcher/ads/AdsManager$5:<init>	(Lco/madseven/launcher/ads/AdsManager;Landroid/content/Context;Lco/madseven/launcher/ads/AdsManager$NativeAdsListener;)V
    //   76: invokevirtual 316	co/madseven/launcher/utils/DispatchGroup:notify	(Ljava/lang/Runnable;)V
    //   79: iconst_0
    //   80: istore 4
    //   82: iload 4
    //   84: iload 5
    //   86: if_icmpge +127 -> 213
    //   89: new 318	com/appnext/nativeads/NativeAd
    //   92: dup
    //   93: aload_1
    //   94: aload 6
    //   96: iload 4
    //   98: aaload
    //   99: invokespecial 321	com/appnext/nativeads/NativeAd:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   102: astore_2
    //   103: aload_2
    //   104: new 18	co/madseven/launcher/ads/AdsManager$6
    //   107: dup
    //   108: aload_0
    //   109: aload_1
    //   110: invokespecial 324	co/madseven/launcher/ads/AdsManager$6:<init>	(Lco/madseven/launcher/ads/AdsManager;Landroid/content/Context;)V
    //   113: invokevirtual 328	com/appnext/nativeads/NativeAd:setAdListener	(Lcom/appnext/nativeads/NativeAdListener;)V
    //   116: new 330	com/appnext/nativeads/NativeAdRequest
    //   119: dup
    //   120: invokespecial 331	com/appnext/nativeads/NativeAdRequest:<init>	()V
    //   123: astore_3
    //   124: aload_3
    //   125: getstatic 337	com/appnext/nativeads/NativeAdRequest$CreativeType:STATIC_ONLY	Lcom/appnext/nativeads/NativeAdRequest$CreativeType;
    //   128: invokevirtual 341	com/appnext/nativeads/NativeAdRequest:setCreativeType	(Lcom/appnext/nativeads/NativeAdRequest$CreativeType;)Lcom/appnext/nativeads/NativeAdRequest;
    //   131: pop
    //   132: invokestatic 127	co/madseven/launcher/settings/preferences/Preferences:getInstance	()Lco/madseven/launcher/settings/preferences/Preferences;
    //   135: aload_1
    //   136: invokevirtual 345	co/madseven/launcher/settings/preferences/Preferences:getAppCategoriesInterests	(Landroid/content/Context;)Ljava/lang/String;
    //   139: astore 7
    //   141: aload 7
    //   143: ifnull +33 -> 176
    //   146: aload 7
    //   148: invokevirtual 211	java/lang/String:length	()I
    //   151: ifle +25 -> 176
    //   154: aload_3
    //   155: ldc_w 347
    //   158: aload 7
    //   160: invokevirtual 350	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   163: ldc_w 352
    //   166: invokevirtual 356	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   169: invokestatic 362	android/text/TextUtils:join	(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;
    //   172: invokevirtual 366	com/appnext/nativeads/NativeAdRequest:setCategories	(Ljava/lang/String;)Lcom/appnext/nativeads/NativeAdRequest;
    //   175: pop
    //   176: aload_0
    //   177: getfield 76	co/madseven/launcher/ads/AdsManager:mDispatchGroup	Lco/madseven/launcher/utils/DispatchGroup;
    //   180: invokevirtual 233	co/madseven/launcher/utils/DispatchGroup:enter	()V
    //   183: aload_2
    //   184: aload_3
    //   185: invokevirtual 370	com/appnext/nativeads/NativeAd:loadAd	(Lcom/appnext/nativeads/NativeAdRequest;)V
    //   188: iload 4
    //   190: iconst_1
    //   191: iadd
    //   192: istore 4
    //   194: goto -112 -> 82
    //   197: astore_1
    //   198: goto +11 -> 209
    //   201: astore_1
    //   202: aload_1
    //   203: invokevirtual 201	java/lang/Throwable:printStackTrace	()V
    //   206: goto +7 -> 213
    //   209: aload_0
    //   210: monitorexit
    //   211: aload_1
    //   212: athrow
    //   213: aload_0
    //   214: monitorexit
    //   215: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	216	0	this	AdsManager
    //   0	216	1	paramContext	Context
    //   0	216	2	paramArrayOfString	String[]
    //   0	216	3	paramNativeAdsListener	NativeAdsListener
    //   80	113	4	i	int
    //   60	27	5	j	int
    //   37	58	6	arrayOfString	String[]
    //   30	129	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   6	32	197	finally
    //   39	44	197	finally
    //   44	49	197	finally
    //   49	79	197	finally
    //   89	141	197	finally
    //   146	176	197	finally
    //   176	188	197	finally
    //   202	206	197	finally
    //   6	32	201	java/lang/Throwable
    //   39	44	201	java/lang/Throwable
    //   44	49	201	java/lang/Throwable
    //   49	79	201	java/lang/Throwable
    //   89	141	201	java/lang/Throwable
    //   146	176	201	java/lang/Throwable
    //   176	188	201	java/lang/Throwable
  }
  
  public ArrayList<NativeAd> getAppNextAds()
  {
    return this.mNativeAds;
  }
  
  public List<AdvertisedAppItem> getAppsOfTheDay()
  {
    return this.mAppsOfTheDay;
  }
  
  public List<AdvertisedAppItem> getRecommendedApps()
  {
    return this.mRecommendedApps;
  }
  
  @SuppressLint({"StaticFieldLeak"})
  public void logAppNextAdDisplay(final Context paramContext, final String paramString)
  {
    new AsyncTask()
    {
      public Void doInBackground(Void... paramAnonymousVarArgs)
      {
        try
        {
          paramAnonymousVarArgs = Preferences.getInstance().getPrefs(paramContext);
          AdsManager.this.loadLoggedAds(paramAnonymousVarArgs);
          if ((AdsManager.this.mBanAdsCandidates != null) && (paramString != null) && (!AdsManager.this.mBanAdsCandidates.keySet().contains(paramString)) && (!AdsManager.this.mBannedAds.keySet().contains(paramString)) && (AdsManager.this.mBanAdsCandidates.keySet().size() < 2))
          {
            AdsManager.this.mBanAdsCandidates.put(paramString, Long.valueOf(System.currentTimeMillis()));
            paramAnonymousVarArgs.edit().putString("pref_ads_ban_candidates_times", AdsManager.this.mGson.toJson(AdsManager.this.mBanAdsCandidates)).apply();
          }
        }
        catch (Throwable paramAnonymousVarArgs)
        {
          paramAnonymousVarArgs.printStackTrace();
        }
        return null;
      }
    }.executeOnExecutor(Utilities.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  /* Error */
  public void trackInstall(final Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +171 -> 174
    //   6: aload_2
    //   7: ifnull +167 -> 174
    //   10: invokestatic 127	co/madseven/launcher/settings/preferences/Preferences:getInstance	()Lco/madseven/launcher/settings/preferences/Preferences;
    //   13: aload_1
    //   14: invokevirtual 131	co/madseven/launcher/settings/preferences/Preferences:getPrefs	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   17: astore 5
    //   19: new 163	com/google/gson/Gson
    //   22: dup
    //   23: invokespecial 400	com/google/gson/Gson:<init>	()V
    //   26: astore_3
    //   27: aload 5
    //   29: ldc -28
    //   31: aconst_null
    //   32: invokeinterface 205 3 0
    //   37: astore 4
    //   39: aload 5
    //   41: ldc -26
    //   43: aconst_null
    //   44: invokeinterface 205 3 0
    //   49: astore 5
    //   51: aload_1
    //   52: invokevirtual 406	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   55: invokevirtual 412	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   58: getfield 418	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   61: astore 6
    //   63: new 59	java/util/ArrayList
    //   66: dup
    //   67: invokespecial 60	java/util/ArrayList:<init>	()V
    //   70: astore 7
    //   72: aload 7
    //   74: aload_2
    //   75: invokeinterface 423 2 0
    //   80: pop
    //   81: new 425	co/madseven/launcher/http/ads/beans/AppsInventoryBody
    //   84: dup
    //   85: aload 4
    //   87: aload 5
    //   89: aload 6
    //   91: invokevirtual 428	java/util/Locale:toString	()Ljava/lang/String;
    //   94: aload 7
    //   96: invokespecial 431	co/madseven/launcher/http/ads/beans/AppsInventoryBody:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V
    //   99: astore 4
    //   101: ldc_w 433
    //   104: invokestatic 439	okhttp3/MediaType:parse	(Ljava/lang/String;)Lokhttp3/MediaType;
    //   107: aload_3
    //   108: aload 4
    //   110: invokevirtual 167	com/google/gson/Gson:toJson	(Ljava/lang/Object;)Ljava/lang/String;
    //   113: invokestatic 444	okhttp3/RequestBody:create	(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;
    //   116: astore_3
    //   117: aload_1
    //   118: invokestatic 449	co/madseven/launcher/http/ads/AppsInventoryService:getInstance	(Landroid/content/Context;)Lco/madseven/launcher/http/ads/IAppsInventory;
    //   121: aload_3
    //   122: invokeinterface 454 2 0
    //   127: new 6	co/madseven/launcher/ads/AdsManager$1
    //   130: dup
    //   131: aload_0
    //   132: aload_1
    //   133: invokespecial 455	co/madseven/launcher/ads/AdsManager$1:<init>	(Lco/madseven/launcher/ads/AdsManager;Landroid/content/Context;)V
    //   136: invokeinterface 272 2 0
    //   141: goto +12 -> 153
    //   144: astore_1
    //   145: goto +25 -> 170
    //   148: astore_3
    //   149: aload_3
    //   150: invokevirtual 201	java/lang/Throwable:printStackTrace	()V
    //   153: aload_1
    //   154: invokestatic 460	co/madseven/launcher/tracking/ApoloTracker:getInstance	(Landroid/content/Context;)Lco/madseven/launcher/tracking/ApoloTracker;
    //   157: ldc_w 462
    //   160: ldc_w 464
    //   163: aload_2
    //   164: invokevirtual 468	co/madseven/launcher/tracking/ApoloTracker:sentEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   167: goto +7 -> 174
    //   170: aload_0
    //   171: monitorexit
    //   172: aload_1
    //   173: athrow
    //   174: aload_0
    //   175: monitorexit
    //   176: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	177	0	this	AdsManager
    //   0	177	1	paramContext	Context
    //   0	177	2	paramString	String
    //   26	96	3	localObject1	Object
    //   148	2	3	localThrowable	Throwable
    //   37	72	4	localObject2	Object
    //   17	71	5	localObject3	Object
    //   61	29	6	localLocale	Locale
    //   70	25	7	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   10	141	144	finally
    //   149	153	144	finally
    //   153	167	144	finally
    //   10	141	148	java/lang/Throwable
  }
  
  /* Error */
  public void trackUninstall(final Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +171 -> 174
    //   6: aload_2
    //   7: ifnull +167 -> 174
    //   10: invokestatic 127	co/madseven/launcher/settings/preferences/Preferences:getInstance	()Lco/madseven/launcher/settings/preferences/Preferences;
    //   13: aload_1
    //   14: invokevirtual 131	co/madseven/launcher/settings/preferences/Preferences:getPrefs	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   17: astore 5
    //   19: new 163	com/google/gson/Gson
    //   22: dup
    //   23: invokespecial 400	com/google/gson/Gson:<init>	()V
    //   26: astore_3
    //   27: aload 5
    //   29: ldc -28
    //   31: aconst_null
    //   32: invokeinterface 205 3 0
    //   37: astore 4
    //   39: aload 5
    //   41: ldc -26
    //   43: aconst_null
    //   44: invokeinterface 205 3 0
    //   49: astore 5
    //   51: aload_1
    //   52: invokevirtual 406	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   55: invokevirtual 412	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   58: getfield 418	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   61: astore 6
    //   63: new 59	java/util/ArrayList
    //   66: dup
    //   67: invokespecial 60	java/util/ArrayList:<init>	()V
    //   70: astore 7
    //   72: aload 7
    //   74: aload_2
    //   75: invokeinterface 423 2 0
    //   80: pop
    //   81: new 425	co/madseven/launcher/http/ads/beans/AppsInventoryBody
    //   84: dup
    //   85: aload 4
    //   87: aload 5
    //   89: aload 6
    //   91: invokevirtual 428	java/util/Locale:toString	()Ljava/lang/String;
    //   94: aload 7
    //   96: invokespecial 431	co/madseven/launcher/http/ads/beans/AppsInventoryBody:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V
    //   99: astore 4
    //   101: ldc_w 433
    //   104: invokestatic 439	okhttp3/MediaType:parse	(Ljava/lang/String;)Lokhttp3/MediaType;
    //   107: aload_3
    //   108: aload 4
    //   110: invokevirtual 167	com/google/gson/Gson:toJson	(Ljava/lang/Object;)Ljava/lang/String;
    //   113: invokestatic 444	okhttp3/RequestBody:create	(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;
    //   116: astore_3
    //   117: aload_1
    //   118: invokestatic 449	co/madseven/launcher/http/ads/AppsInventoryService:getInstance	(Landroid/content/Context;)Lco/madseven/launcher/http/ads/IAppsInventory;
    //   121: aload_3
    //   122: invokeinterface 471 2 0
    //   127: new 8	co/madseven/launcher/ads/AdsManager$2
    //   130: dup
    //   131: aload_0
    //   132: aload_1
    //   133: invokespecial 472	co/madseven/launcher/ads/AdsManager$2:<init>	(Lco/madseven/launcher/ads/AdsManager;Landroid/content/Context;)V
    //   136: invokeinterface 272 2 0
    //   141: goto +12 -> 153
    //   144: astore_1
    //   145: goto +25 -> 170
    //   148: astore_3
    //   149: aload_3
    //   150: invokevirtual 201	java/lang/Throwable:printStackTrace	()V
    //   153: aload_1
    //   154: invokestatic 460	co/madseven/launcher/tracking/ApoloTracker:getInstance	(Landroid/content/Context;)Lco/madseven/launcher/tracking/ApoloTracker;
    //   157: ldc_w 462
    //   160: ldc_w 474
    //   163: aload_2
    //   164: invokevirtual 468	co/madseven/launcher/tracking/ApoloTracker:sentEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   167: goto +7 -> 174
    //   170: aload_0
    //   171: monitorexit
    //   172: aload_1
    //   173: athrow
    //   174: aload_0
    //   175: monitorexit
    //   176: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	177	0	this	AdsManager
    //   0	177	1	paramContext	Context
    //   0	177	2	paramString	String
    //   26	96	3	localObject1	Object
    //   148	2	3	localThrowable	Throwable
    //   37	72	4	localObject2	Object
    //   17	71	5	localObject3	Object
    //   61	29	6	localLocale	Locale
    //   70	25	7	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   10	141	144	finally
    //   149	153	144	finally
    //   153	167	144	finally
    //   10	141	148	java/lang/Throwable
  }
  
  public void uploadAppsInventory(final Context paramContext, final Runnable paramRunnable)
  {
    if (paramContext != null) {}
    try
    {
      SharedPreferences localSharedPreferences = Preferences.getInstance().getPrefs(paramContext);
      if (localSharedPreferences == null) {
        break label299;
      }
      boolean bool = localSharedPreferences.getBoolean("pref_inventory_done", false);
      if (bool) {
        break label299;
      }
      try
      {
        Object localObject1 = new Gson();
        Object localObject2 = localSharedPreferences.getString("PREF_USER_UUID", null);
        String str = localSharedPreferences.getString("PREF_USER_GAID", null);
        Locale localLocale = paramContext.getResources().getConfiguration().locale;
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        while (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo1 = (ApplicationInfo)localIterator.next();
          ApplicationInfo localApplicationInfo2 = paramContext.getPackageManager().getApplicationInfo(localApplicationInfo1.packageName, 0);
          if ((localApplicationInfo2 != null) && ((localApplicationInfo2.flags & 0x1) == 0)) {
            localArrayList.add(localApplicationInfo1.packageName);
          }
        }
        localObject2 = new AppsInventoryBody((String)localObject2, str, localLocale.toString(), localArrayList);
        localObject1 = RequestBody.create(MediaType.parse("text/plain"), ((Gson)localObject1).toJson(localObject2));
        AppsInventoryService.getInstance(paramContext).uploadAppsInventory((RequestBody)localObject1).enqueue(new Callback()
        {
          public void onFailure(Call<Object> paramAnonymousCall, Throwable paramAnonymousThrowable)
          {
            try
            {
              ApoloTracker.getInstance(paramContext).sentEvent("Erreur WS", "api/inventory", paramAnonymousThrowable.getLocalizedMessage());
            }
            catch (Throwable paramAnonymousCall)
            {
              paramAnonymousCall.printStackTrace();
            }
            if (paramRunnable != null) {
              paramRunnable.run();
            }
          }
          
          public void onResponse(Call<Object> paramAnonymousCall, Response<Object> paramAnonymousResponse)
          {
            if (paramRunnable != null) {
              paramRunnable.run();
            }
          }
        });
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
      localSharedPreferences.edit().putBoolean("pref_inventory_done", true).commit();
    }
    finally
    {
      label299:
      for (;;) {}
    }
    if (paramRunnable != null)
    {
      paramRunnable.run();
      break label299;
      throw paramContext;
    }
  }
  
  public static abstract interface NativeAdsListener
  {
    public abstract void onAdsLoaded();
  }
}
