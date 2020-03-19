package managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import common.Alert;
import common.F;
import data.DataCollection.Record;
import engine.CustomExceptionHandler;
import engine.MetaData;
import engine.PricePoint;
import engine.SSActivity;
import engine.SSActivity.Platform;
import gui.AddCurrency;
import gui.ErrorMessage;
import gui.FriendReward;
import gui.MoneyReceived;
import gui.NewSocialGiftsList;
import gui.NewSocialMessagesList;
import interfaces.Game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONObject;
import ssp.SspAccount;

public class ApiManager
{
  private static ArrayList<PricePointReward> GOLDREWARDPRICEPOINTS;
  private static ArrayList<PricePoint> PRICEPOINTS;
  private static ArrayList<PricePoint> PRICEPOINTS_CASH;
  private static ArrayList<PricePoint> PRICEPOINTS_DIAMONDS;
  private static ArrayList<PricePoint> PRICEPOINTS_GOLD;
  private static final String PROPERTY_FRIENDS_LIST_KEY = "fb_APIFriendsListCache";
  private static final String PROPERTY_LAST_CHECK_KEY = "fb_lastCheckAPIFriendsList";
  private static PricePoint STARTERS_PACK;
  private static boolean checkPurchase = true;
  private static boolean isFetching;
  private static boolean isPrivateOfferwallSale;
  private static boolean isPrivateSale;
  private static ArrayList<String> itemsOnSale;
  private static int noInternetCount = 0;
  private static Alert ppAlert;
  private static int ppDelaySeconds = 43200;
  private static Alert saleAlert;
  
  static
  {
    itemsOnSale = null;
    isFetching = false;
    isPrivateSale = false;
    isPrivateOfferwallSale = false;
  }
  
  public ApiManager() {}
  
  public static void activatePrivateOfferwallSale(long paramLong)
  {
    if (SSActivity.disableSparkSocFeatures()) {
      return;
    }
    isPrivateOfferwallSale = true;
    saleAlert = Alert.setRelativeExpirationSeconds(paramLong);
  }
  
  public static void activatePrivateSale(long paramLong)
  {
    if (SSActivity.disableSparkSocFeatures()) {
      return;
    }
    isPrivateSale = true;
    saleAlert = Alert.setRelativeExpirationSeconds(paramLong);
  }
  
  public static void check(Context paramContext)
  {
    if (isFetching) {
      return;
    }
    isFetching = true;
    if (!MetaData.isNetworkAvailable())
    {
      if (noInternetCount < 10) {
        noInternetCount += 1;
      }
      if (noInternetCount == 2) {
        F.showToast(SSActivity.string(paramContext, "no_internet_connection"));
      }
    }
    SSActivity.executeOnUiThread(new Runnable()
    {
      public void run()
      {
        F.execute(new AsyncTask()
        {
          protected Void doInBackground(Void... paramAnonymous2VarArgs)
          {
            for (;;)
            {
              try
              {
                if ((SSActivity.instance == null) || (SSActivity.instance.isFinishing())) {
                  continue;
                }
                localObject1 = SSActivity.dcm.getGameStateProperty("userId", null);
                localObject2 = SSActivity.dcm.getGameStateProperty("userKey", null);
                localObject3 = SSActivity.dcm.getGameStateProperty("max_message_id", null);
                localObject4 = SSActivity.dcm.getGameStateProperty("max_gift_id", null);
                j = 0;
                long l1 = F.getYYYYMMDDHHSS();
                long l2 = F.toLong(SSActivity.dcm.getGameStateProperty("lastApiCall"), Integer.valueOf(0)).longValue();
                long l3 = F.getSecondsDiff(l2, l1);
                if ((l2 != 0L) && (l3 <= 600L) && (localObject2 != null))
                {
                  i = j;
                  if (localObject1 != null) {}
                }
                else
                {
                  F.debug("FETCH DATA FROM SERVER");
                  i = j;
                  if (MetaData.isNetworkAvailable())
                  {
                    paramAnonymous2VarArgs = new HashMap();
                    paramAnonymous2VarArgs.put("userId", localObject1);
                    paramAnonymous2VarArgs.put("userKey", localObject2);
                    if (localObject3 != null) {
                      paramAnonymous2VarArgs.put("max_message_id", localObject3);
                    }
                    if (localObject4 != null) {
                      paramAnonymous2VarArgs.put("max_gift_id", localObject4);
                    }
                    localObject1 = new Properties();
                    paramAnonymous2VarArgs = ApiManager.getApiResponseStream(this.val$context, SSActivity.string(this.val$context, "PROPS_URL"), paramAnonymous2VarArgs, true, true);
                    if (paramAnonymous2VarArgs == null) {
                      continue;
                    }
                    ((Properties)localObject1).load(paramAnonymous2VarArgs);
                    if ((localObject1 == null) || (((Properties)localObject1).size() <= 0)) {
                      continue;
                    }
                    SSActivity.dcm.saveApiProperties((Properties)localObject1);
                    SSActivity.dcm.setGameStateProperty("lastApiCall", Long.valueOf(l1));
                    SSActivity.dcm.setGameStateProperty("minutesPlayedOnLastSave", Long.valueOf(SSActivity.game.getTotalMinutesPlayed()));
                    i = 1;
                    SSActivity.game.islandSaved();
                  }
                }
              }
              catch (Exception paramAnonymous2VarArgs)
              {
                int m;
                F.debug(paramAnonymous2VarArgs);
                continue;
                F.debug("props == null || props.size() <= 0");
                int i = j;
                continue;
                if ((ApiManager.saleAlert != null) && (!ApiManager.saleAlert.isExpired())) {
                  continue;
                }
                paramAnonymous2VarArgs = F.toLong(SSActivity.dcm.getGameStateProperty("saleExpireStamp"), Integer.valueOf(0));
                if (paramAnonymous2VarArgs.longValue() <= 0L) {
                  continue;
                }
                ApiManager.saleAlert = new Alert(paramAnonymous2VarArgs.longValue());
                continue;
                SSActivity.dcm.setGameStateProperty("isCheater", Integer.valueOf(0));
                continue;
                Object localObject4 = paramAnonymous2VarArgs.getJSONObject(i);
                Object localObject1 = ((JSONObject)localObject4).get("id");
                Object localObject2 = ((JSONObject)localObject4).get("from_facebook_id");
                Object localObject3 = ((JSONObject)localObject4).get("from_name");
                localObject4 = ((JSONObject)localObject4).get("gift");
                int k = j;
                if (localObject1 == null) {
                  continue;
                }
                k = j;
                if (localObject2 == null) {
                  continue;
                }
                k = j;
                if (localObject3 == null) {
                  continue;
                }
                k = j;
                if (localObject4 == null) {
                  continue;
                }
                Bundle localBundle = new Bundle();
                localBundle.putString("id", localObject1.toString());
                localBundle.putString("fbid", localObject2.toString());
                localBundle.putString("name", localObject3.toString());
                localBundle.putString("gift", localObject4.toString());
                k = j;
                if (!SSActivity.dcm.storeGift(localBundle)) {
                  continue;
                }
                k = 1;
                continue;
                localObject4 = paramAnonymous2VarArgs.getJSONObject(i);
                localObject1 = ((JSONObject)localObject4).get("id");
                localObject2 = ((JSONObject)localObject4).get("from_facebook_id");
                localObject3 = ((JSONObject)localObject4).get("from_name");
                localObject4 = ((JSONObject)localObject4).get("message");
                k = j;
                if (localObject1 == null) {
                  continue;
                }
                k = j;
                if (localObject2 == null) {
                  continue;
                }
                k = j;
                if (localObject3 == null) {
                  continue;
                }
                k = j;
                if (localObject4 == null) {
                  continue;
                }
                localBundle = new Bundle();
                localBundle.putString("id", localObject1.toString());
                localBundle.putString("fbid", localObject2.toString());
                localBundle.putString("name", localObject3.toString());
                localBundle.putString("message", localObject4.toString());
                boolean bool = SSActivity.dcm.storeMessage(localBundle);
                k = j;
                if (!bool) {
                  continue;
                }
                k = 1;
                i += 1;
                int j = k;
                continue;
                return null;
                i += 1;
                j = k;
                continue;
              }
              if (!SSActivity.disableSparkSocFeatures())
              {
                if (i == 0) {
                  continue;
                }
                ApiManager.saleAlert = Alert.setRelativeExpirationSeconds(F.toLong(SSActivity.dcm.getApiProperty("saleseconds"), Integer.valueOf(0)).longValue());
                if (!ApiManager.saleAlert.isExpired()) {
                  SSActivity.dcm.setGameStateProperty("saleExpireStamp", ApiManager.saleAlert.getExpirationTimeStamp());
                }
              }
              SSActivity.dcm.setGameStateProperty("userId", SSActivity.dcm.getApiProperty("userId"));
              SSActivity.dcm.setGameStateProperty("userKey", SSActivity.dcm.getApiProperty("userKey"));
              paramAnonymous2VarArgs = SSActivity.dcm.getApiProperty("is_cheater");
              if ((paramAnonymous2VarArgs == null) || ((!paramAnonymous2VarArgs.trim().equals("1")) && (!paramAnonymous2VarArgs.trim().equalsIgnoreCase("true")))) {
                continue;
              }
              SSActivity.dcm.setGameStateProperty("isCheater", Integer.valueOf(1));
              paramAnonymous2VarArgs = SSActivity.dcm.getApiProperty("undelivered");
              if ((paramAnonymous2VarArgs != null) && (!paramAnonymous2VarArgs.trim().equals("")))
              {
                i = ApiManager.parseCash(paramAnonymous2VarArgs);
                j = ApiManager.parseGold(paramAnonymous2VarArgs);
                k = ApiManager.parseDiamonds(paramAnonymous2VarArgs);
                m = ApiManager.parseXP(paramAnonymous2VarArgs);
                if (i > 0) {
                  AddCurrency.cashPurchased(i);
                }
                if (j > 0) {
                  AddCurrency.goldPurchased(j);
                }
                if (k > 0)
                {
                  AddCurrency.diamondsPurchased(k);
                  SSActivity.trackReceivedDiamondsFromServerEventV2(k);
                }
                if (m > 0) {
                  AddCurrency.xpPurchased(m);
                }
                SSActivity.dcm.setApiProperty("undelivered", "");
              }
              paramAnonymous2VarArgs = SSActivity.dcm.getApiProperty("friendrewards");
              if ((paramAnonymous2VarArgs != null) && (!paramAnonymous2VarArgs.trim().equals("")))
              {
                i = ApiManager.parseCash(paramAnonymous2VarArgs);
                j = ApiManager.parseGold(paramAnonymous2VarArgs);
                if ((i > 0) || (j > 0)) {
                  FriendReward.addFriendReward(i, j);
                }
                SSActivity.dcm.setApiProperty("friendrewards", "");
              }
              paramAnonymous2VarArgs = SSActivity.dcm.getApiProperty("gifts");
              if ((paramAnonymous2VarArgs != null) && (!paramAnonymous2VarArgs.trim().isEmpty()))
              {
                j = 0;
                paramAnonymous2VarArgs = new JSONArray(paramAnonymous2VarArgs);
                i = 0;
                if (i < paramAnonymous2VarArgs.length()) {
                  continue;
                }
                SSActivity.dcm.setApiProperty("gifts", "");
                if (j != 0) {
                  NewSocialGiftsList.reloadItems(false);
                }
              }
              paramAnonymous2VarArgs = SSActivity.dcm.getApiProperty("messages");
              if ((paramAnonymous2VarArgs != null) && (!paramAnonymous2VarArgs.trim().isEmpty()))
              {
                j = 0;
                paramAnonymous2VarArgs = new JSONArray(paramAnonymous2VarArgs);
                i = 0;
                if (i < paramAnonymous2VarArgs.length()) {
                  continue;
                }
                SSActivity.dcm.setApiProperty("messages", "");
                if (j != 0) {
                  NewSocialMessagesList.reloadItems(false);
                }
              }
              paramAnonymous2VarArgs = SSActivity.dcm.getApiProperty("adjust_tokens");
              if (paramAnonymous2VarArgs != null) {
                AdjustManager.trackEvents(paramAnonymous2VarArgs.split(","));
              }
              paramAnonymous2VarArgs = SSActivity.dcm.getApiProperty("adwords_tokens");
              if (paramAnonymous2VarArgs != null) {
                AdWordsConversionManager.trackEvents(paramAnonymous2VarArgs.split(","));
              }
              SspAccount.init();
              ApiManager.getPricePoints(this.val$context);
              ApiManager.sendPurchaseSuccessToServer(this.val$context);
              ApiManager.isFetching = false;
              return null;
              F.debug("InputStream == null");
            }
          }
        });
      }
    });
  }
  
  public static Map<String, Integer> checkFriends(Context paramContext, String paramString, List<String> paramList)
  {
    long l1 = F.toLong(SSActivity.dcm.getGameStateProperty("fb_lastCheckAPIFriendsList", null), Integer.valueOf(0)).longValue();
    long l2 = F.getYYYYMMDDHHSS();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (l1 != 0L)
    {
      localObject1 = localObject2;
      if (F.getSecondsDiff(l1, l2) < 7200L) {
        localObject1 = SSActivity.dcm.getGameStateProperty("fb_APIFriendsListCache", null);
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      SSActivity.dcm.setGameStateProperty("fb_lastCheckAPIFriendsList", Long.valueOf(l2));
      localObject1 = new HashMap();
      ((HashMap)localObject1).put("fbid", paramString);
      paramString = "";
      localObject2 = paramList.iterator();
      if (!((Iterator)localObject2).hasNext())
      {
        ((HashMap)localObject1).put("friends", paramString);
        ((HashMap)localObject1).put("action", "check_friends");
        localObject2 = getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), (HashMap)localObject1, false, false);
        Log.i("NewSocial", "result = " + (String)localObject2);
        SSActivity.dcm.setGameStateProperty("fb_APIFriendsListCache", (String)localObject2);
      }
    }
    else
    {
      paramContext = new HashMap();
      if ((localObject2 != null) && (((String)localObject2).trim().length() != 0)) {
        break label288;
      }
    }
    for (;;)
    {
      return paramContext;
      String str = (String)((Iterator)localObject2).next();
      paramList = paramString;
      if (paramString.length() > 0) {
        paramList = paramString + ",";
      }
      paramString = paramList + str;
      break;
      label288:
      paramString = ((String)localObject2).split(",");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        paramList = paramString[i];
        int k = paramList.indexOf('(');
        int m = paramList.indexOf(')');
        if ((k >= 0) && (m >= 0)) {
          paramContext.put(paramList.substring(0, k), Integer.valueOf(paramList.substring(k + 1, m)));
        }
        i += 1;
      }
    }
  }
  
  public static void checkIfFriendExists(String paramString)
  {
    Object localObject1 = SSActivity.dcm.getGameStateProperty("fb_APIFriendsListCache", null);
    if ((localObject1 == null) || (((String)localObject1).equals("")))
    {
      forceFriendUpdate();
      return;
    }
    int j = 0;
    localObject1 = ((String)localObject1).split(",");
    int m = localObject1.length;
    int i = 0;
    for (;;)
    {
      if (i >= m)
      {
        if (j != 0) {
          break;
        }
        forceFriendUpdate();
        return;
      }
      Object localObject2 = localObject1[i];
      int k = j;
      if (localObject2 != null)
      {
        k = j;
        if (!localObject2.equals(""))
        {
          k = j;
          if (localObject2.contains("("))
          {
            k = j;
            if (localObject2.substring(0, localObject2.indexOf("(")).equals(paramString)) {
              k = 1;
            }
          }
        }
      }
      i += 1;
      j = k;
    }
  }
  
  public static boolean disableAds()
  {
    String str = SSActivity.dcm.getApiProperty("disable_ads");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }
  
  public static void forceFriendUpdate()
  {
    F.debug("forceFriendUpdate");
    SSActivity.dcm.setGameStateProperty("fb_lastCheckAPIFriendsList", Integer.valueOf(0));
  }
  
  public static void forceNextApiCallToServer()
  {
    F.debug("forceNextApiCallToServer");
    SSActivity.dcm.setGameStateProperty("lastApiCall", Integer.valueOf(0));
  }
  
  public static int getAmountOfPurchasesBySkuName(String paramString)
  {
    return F.toInt(SSActivity.dcm.getGameStateProperty("amountOfPurchases:" + paramString), Integer.valueOf(0)).intValue();
  }
  
  @SuppressLint({"NewApi"})
  private static InputStream getApiResponseStream(Context paramContext, String paramString, HashMap<String, String> paramHashMap, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    }
    localObject1 = null;
    Object localObject2 = null;
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    paramContext = SSActivity.getUID(paramContext);
    if (paramContext != null)
    {
      localArrayList2.add(new BasicNameValuePair("uid", paramContext));
      localArrayList1.add("uid=" + URLEncoder.encode(paramContext));
    }
    label167:
    label224:
    label281:
    String str;
    int i;
    if (SspAccount.instance == null)
    {
      paramContext = null;
      if (paramContext != null)
      {
        localArrayList2.add(new BasicNameValuePair("ssuname", paramContext));
        localArrayList1.add("ssuname=" + URLEncoder.encode(paramContext));
      }
      if (SspAccount.instance != null) {
        break label1136;
      }
      paramContext = null;
      if (paramContext != null)
      {
        localArrayList2.add(new BasicNameValuePair("sauid", paramContext));
        localArrayList1.add("sauid=" + URLEncoder.encode(paramContext));
      }
      if (SspAccount.instance != null) {
        break label1146;
      }
      paramContext = null;
      if (paramContext != null)
      {
        localArrayList2.add(new BasicNameValuePair("sadid", paramContext));
        localArrayList1.add("sadid=" + URLEncoder.encode(paramContext));
      }
      if (SspAccount.instance != null) {
        break label1156;
      }
      paramContext = null;
      if (paramContext != null)
      {
        localArrayList2.add(new BasicNameValuePair("sagid", paramContext));
        localArrayList1.add("sagid=" + URLEncoder.encode(paramContext));
      }
      if (SSActivity.instance != null)
      {
        paramContext = SSActivity.instance.getActiveLocale();
        localArrayList2.add(new BasicNameValuePair("locale", paramContext));
        localArrayList1.add("locale=" + URLEncoder.encode(paramContext));
      }
      str = SSActivity.getVersion();
      boolean bool1 = SSActivity.isPremium();
      boolean bool2 = SSActivity.isBeta();
      if (SSActivity.getPlatform() != SSActivity.Platform.AMAZON) {
        break label1166;
      }
      i = 1;
      label415:
      if (i == 0) {
        break label1172;
      }
      paramContext = "1";
      label424:
      localArrayList2.add(new BasicNameValuePair("amazon", paramContext));
      StringBuilder localStringBuilder = new StringBuilder("amazon=");
      if (i == 0) {
        break label1179;
      }
      paramContext = "1";
      label464:
      localArrayList1.add(paramContext);
      if (!bool1) {
        break label1186;
      }
      paramContext = "1";
      label488:
      localArrayList2.add(new BasicNameValuePair("premium", paramContext));
      localStringBuilder = new StringBuilder("premium=");
      if (!bool1) {
        break label1193;
      }
      paramContext = "1";
      label528:
      localArrayList1.add(paramContext);
      if (!bool2) {
        break label1200;
      }
      paramContext = "1";
      label552:
      localArrayList2.add(new BasicNameValuePair("beta", paramContext));
      localStringBuilder = new StringBuilder("beta=");
      if (!bool2) {
        break label1207;
      }
      paramContext = "1";
      label592:
      localArrayList1.add(paramContext);
      if (str != null)
      {
        localArrayList2.add(new BasicNameValuePair("game_version", str));
        localArrayList1.add("game_version=" + URLEncoder.encode(str));
      }
      if (SSActivity.instance != null)
      {
        paramContext = FacebookManager.getFacebookId();
        localArrayList2.add(new BasicNameValuePair("fbid", paramContext));
        localArrayList1.add("fbid=" + URLEncoder.encode(paramContext));
        paramContext = SSActivity.dcm.getGameStateProperty("fb_gcmRegistrationId", "");
        localArrayList2.add(new BasicNameValuePair("regid", paramContext));
        localArrayList1.add("regid=" + URLEncoder.encode(paramContext));
      }
      paramContext = paramHashMap.keySet().iterator();
      label780:
      if (paramContext.hasNext()) {
        break label1214;
      }
      if (paramBoolean2)
      {
        paramContext = MetaData.getInfo();
        paramHashMap = paramContext.keySet().iterator();
        label808:
        if (paramHashMap.hasNext()) {
          break label1323;
        }
        paramContext.clear();
      }
      paramContext = GCMManager.getRegistrationId(SSActivity.instance);
      if (paramContext != null)
      {
        localArrayList2.add(new BasicNameValuePair("gcmid", paramContext));
        localArrayList1.add("gcmid=" + URLEncoder.encode(paramContext));
      }
      paramContext = SSActivity.getGoogleAdvertisingId();
      if (paramContext != null)
      {
        localArrayList2.add(new BasicNameValuePair("gaid", paramContext));
        localArrayList1.add("gaid=" + URLEncoder.encode(paramContext));
      }
      if ((paramBoolean1) && (SSActivity.game != null))
      {
        paramContext = SSActivity.game.getGameStateAsHashMap();
        paramHashMap = paramContext.keySet().iterator();
        label959:
        if (paramHashMap.hasNext()) {
          break label1390;
        }
      }
      paramContext = localArrayList1.iterator();
      label974:
      if (paramContext.hasNext()) {
        break label1469;
      }
      paramContext = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout(paramContext, 20000);
      HttpConnectionParams.setSoTimeout(paramContext, 20000);
      paramContext = new DefaultHttpClient(paramContext);
      paramHashMap = new HttpPost(paramString);
    }
    for (;;)
    {
      try
      {
        F.debug(paramString + "?" + F.toString(localArrayList1, "&"));
        paramHashMap.setEntity(new UrlEncodedFormEntity(localArrayList2, "UTF-8"));
        paramContext = paramContext.execute(paramHashMap);
        if (paramContext == null) {
          continue;
        }
        i = paramContext.getStatusLine().getStatusCode();
        if (i != 200) {
          continue;
        }
        paramContext = paramContext.getEntity();
        if (paramContext != null) {
          continue;
        }
        F.debug("HttpEntity == null !!");
        paramContext = localObject2;
      }
      catch (Exception paramContext)
      {
        label1136:
        label1146:
        label1156:
        label1166:
        label1172:
        label1179:
        label1186:
        label1193:
        label1200:
        label1207:
        label1214:
        label1323:
        label1390:
        label1469:
        F.debug(paramContext);
        paramContext = localObject1;
        continue;
        F.debug("!! API RESPONSE == NULL !! ******************************");
        paramContext = localObject1;
        continue;
      }
      return paramContext;
      paramContext = SspAccount.instance.getUsername();
      break;
      paramContext = SspAccount.instance.getSAUID();
      break label167;
      paramContext = SspAccount.instance.getSADID();
      break label224;
      paramContext = SspAccount.instance.getSAGID();
      break label281;
      i = 0;
      break label415;
      paramContext = "0";
      break label424;
      paramContext = "0";
      break label464;
      paramContext = "0";
      break label488;
      paramContext = "0";
      break label528;
      paramContext = "0";
      break label552;
      paramContext = "0";
      break label592;
      str = (String)paramContext.next();
      if ((paramHashMap.get(str) == null) || (((String)paramHashMap.get(str)).equalsIgnoreCase("null"))) {
        break label780;
      }
      localArrayList2.add(new BasicNameValuePair(str, (String)paramHashMap.get(str)));
      localArrayList1.add(str + "=" + URLEncoder.encode((String)paramHashMap.get(str)));
      break label780;
      str = (String)paramHashMap.next();
      if ((paramContext.get(str) == null) || (((String)paramContext.get(str)).equalsIgnoreCase("null"))) {
        break label808;
      }
      localArrayList2.add(new BasicNameValuePair(str, (String)paramContext.get(str)));
      break label808;
      str = (String)paramHashMap.next();
      localArrayList2.add(new BasicNameValuePair(str, (String)paramContext.get(str)));
      localArrayList1.add(str + "=" + (String)paramContext.get(str));
      break label959;
      F.debug((String)paramContext.next());
      break label974;
      paramContext = paramContext.getContent();
      continue;
      F.debug("HTTP ERROR " + i + ": " + paramString + "?" + F.toString(localArrayList1, "&"));
      paramContext = localObject2;
    }
  }
  
  private static String getApiResponseString(Context paramContext, String paramString, HashMap<String, String> paramHashMap, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramContext = getApiResponseStream(paramContext, paramString, paramHashMap, paramBoolean1, paramBoolean2);
    if (paramContext != null)
    {
      paramContext = new InputStreamReader(paramContext);
      if (paramContext != null)
      {
        paramString = new BufferedReader(paramContext);
        paramContext = new StringBuilder();
        for (;;)
        {
          try
          {
            paramHashMap = paramString.readLine();
            if (paramHashMap != null) {
              continue;
            }
          }
          catch (IOException paramString)
          {
            CustomExceptionHandler.sendToServer(paramString.getStackTrace());
            paramString.printStackTrace();
            continue;
          }
          return paramContext.toString().trim();
          paramContext.append(paramHashMap);
        }
      }
    }
    return null;
  }
  
  public static PricePoint getCheapestCashPricePoint(Context paramContext)
  {
    paramContext = getPricePointsCash(paramContext);
    if ((paramContext == null) || (paramContext.size() == 0)) {
      return null;
    }
    return (PricePoint)paramContext.get(0);
  }
  
  public static PricePoint getCheapestDiamondsPricePoint(Context paramContext)
  {
    paramContext = getPricePointsDiamonds(paramContext);
    if ((paramContext == null) || (paramContext.size() == 0)) {
      return null;
    }
    return (PricePoint)paramContext.get(0);
  }
  
  public static PricePoint getCheapestGoldPricePoint(Context paramContext)
  {
    paramContext = getPricePointsGold(paramContext);
    if ((paramContext == null) || (paramContext.size() == 0)) {
      return null;
    }
    return (PricePoint)paramContext.get(0);
  }
  
  public static int getDaysPlayed()
  {
    return F.toInt(SSActivity.dcm.getApiProperty("daysPlayed"), Integer.valueOf(1)).intValue();
  }
  
  public static ArrayList<PricePointReward> getGoldRewardPricePoints(Context paramContext)
  {
    if ((GOLDREWARDPRICEPOINTS != null) && (GOLDREWARDPRICEPOINTS.size() > 0)) {
      return GOLDREWARDPRICEPOINTS;
    }
    GOLDREWARDPRICEPOINTS = new ArrayList();
    int i = SSActivity.instance.getResources().getIdentifier("pricepoint_gold_reward_names", "array", SSActivity.instance.getPackageName());
    String[] arrayOfString;
    int j;
    if (i > 0)
    {
      arrayOfString = SSActivity.instance.getResources().getStringArray(i);
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        if ((GOLDREWARDPRICEPOINTS != null) && (GOLDREWARDPRICEPOINTS.size() > 0))
        {
          paramContext = new Comparator()
          {
            public int compare(ApiManager.PricePointReward paramAnonymousPricePointReward1, ApiManager.PricePointReward paramAnonymousPricePointReward2)
            {
              if (paramAnonymousPricePointReward1.goldAmount < paramAnonymousPricePointReward2.goldAmount) {
                return -1;
              }
              if (paramAnonymousPricePointReward1.goldAmount > paramAnonymousPricePointReward2.goldAmount) {
                return 1;
              }
              return 0;
            }
          };
          Collections.sort(GOLDREWARDPRICEPOINTS, paramContext);
        }
        return GOLDREWARDPRICEPOINTS;
      }
      String str = arrayOfString[i];
      int k = F.toInt(str.substring(str.lastIndexOf(".") + 1), Integer.valueOf(0)).intValue();
      if (k > 0)
      {
        int m = k * 2 / 3;
        Object localObject2 = getNormalPricePointByGoldAmount(paramContext, m);
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = getNormalPricePointByGoldAmount(paramContext, m + 1);
        }
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = getNormalPricePointByGoldAmount(paramContext, m - 1);
        }
        if (localObject2 != null)
        {
          m = ((PricePoint)localObject2).goldAmount;
          localObject1 = new PricePointReward();
          ((PricePointReward)localObject1).name = str;
          ((PricePointReward)localObject1).baseGoldAmount = m;
          ((PricePointReward)localObject1).extraGoldAmount = (k - m);
          ((PricePointReward)localObject1).goldAmount = k;
          GOLDREWARDPRICEPOINTS.add(localObject1);
        }
      }
      i += 1;
    }
  }
  
  @SuppressLint({"DefaultLocale"})
  public static ArrayList<String> getItemsOnSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    do
    {
      do
      {
        do
        {
          return null;
        } while (!SSActivity.isLoadingCompleted());
        if (itemsOnSale == null) {
          break;
        }
      } while ((itemsOnSale == null) || (itemsOnSale.size() == 0));
      return itemsOnSale;
      itemsOnSale = new ArrayList();
      if (!saleAvailable()) {
        break;
      }
      localObject = saleType();
    } while ((localObject == null) || (!((String)localObject).trim().toLowerCase().startsWith("unit.")));
    Object localObject = ((String)localObject).trim().substring("unit.".length()).trim().split("\\.");
    int j = localObject.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return itemsOnSale;
      }
      String str1 = localObject[i];
      if (SSActivity.game.objectExists(str1))
      {
        String str2 = SSActivity.game.getObjectProperty(str1, "name");
        if ((str2 != null) && (!str2.equals(""))) {
          itemsOnSale.add(str1);
        }
      }
      i += 1;
    }
  }
  
  private static PricePoint getNormalPricePointByGoldAmount(Context paramContext, int paramInt)
  {
    paramContext = getPricePointsGold(paramContext).iterator();
    PricePoint localPricePoint;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localPricePoint = (PricePoint)paramContext.next();
    } while ((localPricePoint.goldAmount != paramInt) || (localPricePoint.isSale));
    return localPricePoint;
  }
  
  public static PricePoint getPricePointStartersPack(Context paramContext)
  {
    if (STARTERS_PACK == null)
    {
      paramContext = getPricePoints(paramContext);
      if ((paramContext == null) || (paramContext.size() == 0)) {
        return STARTERS_PACK;
      }
      paramContext = paramContext.iterator();
    }
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return STARTERS_PACK;
      }
      PricePoint localPricePoint = (PricePoint)paramContext.next();
      if ((STARTERS_PACK == null) && (isStartersPack(localPricePoint))) {
        STARTERS_PACK = localPricePoint;
      }
    }
  }
  
  public static ArrayList<PricePoint> getPricePoints(Context paramContext)
  {
    long l2 = F.getYYYYMMDDHHSS();
    Object localObject = SSActivity.dcm.getGameStateProperty("lastApiCallPricePointsGameVersion");
    long l1;
    if (ppAlert == null)
    {
      l1 = F.toLong(SSActivity.dcm.getGameStateProperty("lastApiCallPricePoints"), Integer.valueOf(0)).longValue();
      if (l1 != 0L) {
        break label334;
      }
      l1 = ppDelaySeconds + 1;
    }
    for (;;)
    {
      ppAlert = Alert.setRelativeExpirationSeconds(ppDelaySeconds - l1);
      if ((PRICEPOINTS == null) || (PRICEPOINTS.size() == 0)) {
        PRICEPOINTS = SSActivity.dcm.getPricePoints();
      }
      if ((ppAlert.isExpired()) || (PRICEPOINTS == null) || (PRICEPOINTS.size() == 0) || (localObject == null) || (!((String)localObject).equals(SSActivity.getVersion())))
      {
        ppAlert = null;
        localObject = new HashMap();
        ((HashMap)localObject).put("action", "getpricepoints");
        ((HashMap)localObject).put("level", SSActivity.game.getUserLevel());
        ((HashMap)localObject).put("userId", String.valueOf(SSActivity.game.getUserId()));
        ((HashMap)localObject).put("userKey", SSActivity.game.getUserKey());
        paramContext = getApiResponseStream(paramContext, SSActivity.string("API_URL"), (HashMap)localObject, false, true);
        localObject = new Properties();
        if ((paramContext == null) || (localObject == null)) {}
      }
      try
      {
        ((Properties)localObject).load(paramContext);
        SSActivity.dcm.savePricePoints((Properties)localObject);
        SSActivity.dcm.setGameStateProperty("lastApiCallPricePoints", l2);
        SSActivity.dcm.setGameStateProperty("lastApiCallPricePointsGameVersion", SSActivity.getVersion());
        PRICEPOINTS = null;
        if ((PRICEPOINTS == null) || (PRICEPOINTS.size() == 0)) {
          PRICEPOINTS = SSActivity.dcm.getPricePoints();
        }
        return PRICEPOINTS;
        label334:
        l1 = F.getSecondsDiff(l1, l2);
      }
      catch (IOException paramContext)
      {
        for (;;)
        {
          F.debug(paramContext);
        }
      }
    }
  }
  
  public static ArrayList<PricePoint> getPricePointsCash(Context paramContext)
  {
    if (PRICEPOINTS_CASH == null) {
      PRICEPOINTS_CASH = new ArrayList();
    }
    Object localObject;
    int i;
    if (PRICEPOINTS_CASH.size() == 0)
    {
      localObject = getPricePoints(paramContext);
      if ((localObject == null) || (((ArrayList)localObject).size() == 0)) {
        return PRICEPOINTS_CASH;
      }
      paramContext = new ArrayList();
      if ((isCashSale()) || (isPrivateSale())) {
        break label104;
      }
      i = 0;
      if (localObject != null) {
        localObject = ((ArrayList)localObject).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        Collections.sort(paramContext, new Comparator()
        {
          public int compare(PricePoint paramAnonymousPricePoint1, PricePoint paramAnonymousPricePoint2)
          {
            if (paramAnonymousPricePoint1.cashAmount < paramAnonymousPricePoint2.cashAmount) {
              return -1;
            }
            if (paramAnonymousPricePoint1.cashAmount > paramAnonymousPricePoint2.cashAmount) {
              return 1;
            }
            return 0;
          }
        });
        PRICEPOINTS_CASH = paramContext;
        return PRICEPOINTS_CASH;
        label104:
        i = 1;
        break;
      }
      PricePoint localPricePoint = (PricePoint)((Iterator)localObject).next();
      if ((localPricePoint.cashAmount > 0) && (((i != 0) && (localPricePoint.name.endsWith(PricePoint.PRICEPOINT_SALE_SUFFIX))) || ((i == 0) && (!localPricePoint.name.endsWith(PricePoint.PRICEPOINT_SALE_SUFFIX))))) {
        paramContext.add(localPricePoint);
      }
    }
  }
  
  public static ArrayList<PricePoint> getPricePointsDiamonds(Context paramContext)
  {
    if (PRICEPOINTS_DIAMONDS == null) {
      PRICEPOINTS_DIAMONDS = new ArrayList();
    }
    Object localObject;
    int i;
    if (PRICEPOINTS_DIAMONDS.size() == 0)
    {
      localObject = getPricePoints(paramContext);
      if ((localObject == null) || (((ArrayList)localObject).size() == 0)) {
        return PRICEPOINTS_DIAMONDS;
      }
      paramContext = new ArrayList();
      if ((isDiamondsSale()) || (isPrivateSale())) {
        break label104;
      }
      i = 0;
      if (localObject != null) {
        localObject = ((ArrayList)localObject).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        Collections.sort(paramContext, new Comparator()
        {
          public int compare(PricePoint paramAnonymousPricePoint1, PricePoint paramAnonymousPricePoint2)
          {
            if (paramAnonymousPricePoint1.diamondsAmount < paramAnonymousPricePoint2.diamondsAmount) {
              return -1;
            }
            if (paramAnonymousPricePoint1.diamondsAmount > paramAnonymousPricePoint2.diamondsAmount) {
              return 1;
            }
            return 0;
          }
        });
        PRICEPOINTS_DIAMONDS = paramContext;
        return PRICEPOINTS_DIAMONDS;
        label104:
        i = 1;
        break;
      }
      PricePoint localPricePoint = (PricePoint)((Iterator)localObject).next();
      if ((localPricePoint.diamondsAmount > 0) && (((i != 0) && (localPricePoint.name.endsWith(PricePoint.PRICEPOINT_SALE_SUFFIX))) || ((i == 0) && (!localPricePoint.name.endsWith(PricePoint.PRICEPOINT_SALE_SUFFIX))))) {
        paramContext.add(localPricePoint);
      }
    }
  }
  
  public static ArrayList<PricePoint> getPricePointsGold(Context paramContext)
  {
    if (PRICEPOINTS_GOLD == null) {
      PRICEPOINTS_GOLD = new ArrayList();
    }
    Object localObject;
    int i;
    if (PRICEPOINTS_GOLD.size() == 0)
    {
      localObject = getPricePoints(paramContext);
      if ((localObject == null) || (((ArrayList)localObject).size() == 0)) {
        return PRICEPOINTS_GOLD;
      }
      paramContext = new ArrayList();
      if ((isGoldSale()) || (isPrivateSale())) {
        break label104;
      }
      i = 0;
      if (localObject != null) {
        localObject = ((ArrayList)localObject).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        Collections.sort(paramContext, new Comparator()
        {
          public int compare(PricePoint paramAnonymousPricePoint1, PricePoint paramAnonymousPricePoint2)
          {
            if (paramAnonymousPricePoint1.goldAmount < paramAnonymousPricePoint2.goldAmount) {
              return -1;
            }
            if (paramAnonymousPricePoint1.goldAmount > paramAnonymousPricePoint2.goldAmount) {
              return 1;
            }
            return 0;
          }
        });
        PRICEPOINTS_GOLD = paramContext;
        return PRICEPOINTS_GOLD;
        label104:
        i = 1;
        break;
      }
      PricePoint localPricePoint = (PricePoint)((Iterator)localObject).next();
      if ((localPricePoint.goldAmount > 0) && (((i != 0) && (localPricePoint.name.endsWith(PricePoint.PRICEPOINT_SALE_SUFFIX))) || ((i == 0) && (!localPricePoint.name.endsWith(PricePoint.PRICEPOINT_SALE_SUFFIX))))) {
        paramContext.add(localPricePoint);
      }
    }
  }
  
  public static boolean getRedeemCodeReward(Context paramContext, String paramString)
  {
    String str = SSActivity.string(paramContext, "social_code_error");
    if (!MetaData.isNetworkAvailable()) {
      paramString = SSActivity.string(paramContext, "no_internet_connection");
    }
    int i;
    int j;
    int k;
    do
    {
      for (;;)
      {
        ErrorMessage.show(paramString);
        return false;
        if ((paramString == null) || (paramString.trim().equals("")))
        {
          paramString = SSActivity.string(paramContext, "social_empty_code");
        }
        else if (paramString.trim().equalsIgnoreCase(SSActivity.game.getUserKey()))
        {
          paramString = SSActivity.string(paramContext, "social_code_error_own_friendcode");
        }
        else
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("action", "redeemcode_extended");
          localHashMap.put("userId", String.valueOf(SSActivity.game.getUserId()));
          localHashMap.put("userKey", SSActivity.game.getUserKey());
          localHashMap.put("code", paramString);
          paramString = getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), localHashMap, false, true);
          if ((paramString == null) || (paramString.trim().equals("")))
          {
            paramString = SSActivity.string(paramContext, "social_code_error");
          }
          else if (paramString.trim().equalsIgnoreCase("FRIENDCODE_ALREADY_USED"))
          {
            paramString = SSActivity.string(paramContext, "social_code_already_used_friendcode");
          }
          else if (paramString.trim().equalsIgnoreCase("GIFTCODE_ALREADY_USED"))
          {
            paramString = SSActivity.string(paramContext, "social_code_already_used_giftcode");
          }
          else if (paramString.trim().equalsIgnoreCase("FRIENDCODE_LIMIT_REACHED"))
          {
            paramString = SSActivity.string(paramContext, "social_code_maximum_reached");
          }
          else if (paramString.trim().equalsIgnoreCase("GIFTCODE_EXPIRED"))
          {
            paramString = SSActivity.string(paramContext, "social_code_expired");
          }
          else
          {
            if (!paramString.trim().equalsIgnoreCase("UNKNOWN_CODE")) {
              break;
            }
            paramString = SSActivity.string(paramContext, "social_code_unknown");
          }
        }
      }
      i = parseCash(paramString);
      j = parseGold(paramString);
      k = parseDiamonds(paramString);
      if ((i > 0) || (j > 0)) {
        break;
      }
      paramString = str;
    } while (k <= 0);
    MoneyReceived.show(SSActivity.string(paramContext, "social_giftcode_accepted"), i, j, k);
    return true;
  }
  
  public static String getTapjoyCurrencyId(Context paramContext)
  {
    Object localObject2 = SSActivity.dcm.getApiProperty("tapjoy_currency_id");
    Object localObject1 = localObject2;
    if (isOfferwallSale())
    {
      String str = SSActivity.dcm.getApiProperty("tapjoy_sale_currency_id");
      localObject1 = localObject2;
      if (str != null)
      {
        localObject1 = localObject2;
        if (!str.equals("")) {
          localObject1 = str;
        }
      }
    }
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!localObject1.equals("")) {}
    }
    else
    {
      localObject2 = SSActivity.string(paramContext, "TAPJOY_APP_ID");
    }
    return localObject2;
  }
  
  public static long getTimeStampOfLastPurchase()
  {
    return F.toLong(SSActivity.dcm.getGameStateProperty("timestampOfLastPurchase"), Integer.valueOf(0)).longValue();
  }
  
  public static int getTotalAmountOfPurchases()
  {
    return F.toInt(SSActivity.dcm.getGameStateProperty("totalPurchases"), Integer.valueOf(0)).intValue();
  }
  
  public static boolean isBeta()
  {
    String str = SSActivity.dcm.getApiProperty("beta_active");
    return (str != null) && (str.trim().equalsIgnoreCase("true"));
  }
  
  public static boolean isCashSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    String str;
    do
    {
      do
      {
        return false;
      } while (!saleAvailable());
      str = saleType();
    } while ((str == null) || (!str.trim().equalsIgnoreCase("CASH")));
    return true;
  }
  
  public static boolean isCheater()
  {
    if (F.toInt(SSActivity.dcm.getGameStateProperty("isCheater"), Integer.valueOf(0)).intValue() == 1) {
      return true;
    }
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "com.dimonvideo.luckypatcher";
    arrayOfString[1] = "com.forpda.lp";
    List localList = SSActivity.instance.getPackageManager().getInstalledApplications(0);
    int i = 0;
    if (i >= localList.size()) {
      return false;
    }
    ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
    int k = arrayOfString.length;
    int j = 0;
    for (;;)
    {
      if (j >= k)
      {
        i += 1;
        break;
      }
      String str = arrayOfString[j];
      if (localApplicationInfo.packageName.equalsIgnoreCase(str))
      {
        SSActivity.dcm.setGameStateProperty("isCheater", Integer.valueOf(1));
        return true;
      }
      j += 1;
    }
  }
  
  public static boolean isDiamondsSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    String str;
    do
    {
      do
      {
        return false;
      } while (!saleAvailable());
      str = saleType();
    } while ((str == null) || (!str.trim().equalsIgnoreCase("DIAMONDS")));
    return true;
  }
  
  public static boolean isExpansionSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    String str;
    do
    {
      do
      {
        return false;
      } while (!saleAvailable());
      str = saleType();
    } while ((str == null) || (!str.trim().equalsIgnoreCase("EXPANSIONS")));
    return true;
  }
  
  public static boolean isGoldSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    String str;
    do
    {
      do
      {
        return false;
      } while (!saleAvailable());
      str = saleType();
    } while ((str == null) || (!str.trim().equalsIgnoreCase("GOLD")));
    return true;
  }
  
  public static boolean isItemOnSale(String paramString)
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    Object localObject;
    do
    {
      while (!((Iterator)localObject).hasNext())
      {
        do
        {
          return false;
          localObject = getItemsOnSale();
        } while ((localObject == null) || (((ArrayList)localObject).size() == 0));
        localObject = ((ArrayList)localObject).iterator();
      }
    } while (!paramString.equalsIgnoreCase((String)((Iterator)localObject).next()));
    return true;
  }
  
  public static boolean isItemSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    ArrayList localArrayList;
    do
    {
      return false;
      localArrayList = getItemsOnSale();
    } while ((localArrayList == null) || (localArrayList.size() <= 0));
    return true;
  }
  
  public static boolean isOfferwallSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    String str;
    do
    {
      do
      {
        return false;
      } while ((!saleAvailable()) || (!AdManager.isOfferwallEnabled()));
      str = saleType();
    } while ((str == null) || (!str.trim().equalsIgnoreCase("OFFERWALL")));
    return true;
  }
  
  public static boolean isPayingUser()
  {
    String str = SSActivity.dcm.getApiProperty("is_paying_user");
    if ((str != null) && (str.equalsIgnoreCase("true"))) {}
    while (getTimeStampOfLastPurchase() > 0L) {
      return true;
    }
    return false;
  }
  
  public static boolean isPrivateOfferwallSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    while ((!isPrivateOfferwallSale) || (saleSecondsLeft() <= 0L)) {
      return false;
    }
    return true;
  }
  
  public static boolean isPrivateSale()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    while ((!isPrivateSale) || (saleSecondsLeft() <= 0L)) {
      return false;
    }
    return true;
  }
  
  public static boolean isStartersPack(PricePoint paramPricePoint)
  {
    return (paramPricePoint.name != null) && (paramPricePoint.name.contains("starterspack"));
  }
  
  public static boolean isTransferred()
  {
    String str = SSActivity.dcm.getApiProperty("transferred");
    return (str != null) && (str.trim().equalsIgnoreCase("true"));
  }
  
  public static int parseCash(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return 0;
      paramString = F.toArray(paramString, ",");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        if (localObject.startsWith("C")) {
          return F.toInt(localObject.replace("C", ""), Integer.valueOf(0)).intValue();
        }
        i += 1;
      }
    }
  }
  
  public static int parseDiamonds(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return 0;
      paramString = F.toArray(paramString, ",");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        if (localObject.startsWith("D")) {
          return F.toInt(localObject.replace("D", ""), Integer.valueOf(0)).intValue();
        }
        i += 1;
      }
    }
  }
  
  public static int parseGold(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return 0;
      paramString = F.toArray(paramString, ",");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        if (localObject.startsWith("G")) {
          return F.toInt(localObject.replace("G", ""), Integer.valueOf(0)).intValue();
        }
        i += 1;
      }
    }
  }
  
  public static int parseXP(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return 0;
      paramString = F.toArray(paramString, ",");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        if (localObject.startsWith("XP")) {
          return F.toInt(localObject.replace("XP", ""), Integer.valueOf(0)).intValue();
        }
        i += 1;
      }
    }
  }
  
  public static void purchaseIntent(Context paramContext, final String paramString)
  {
    if (!MetaData.isNetworkAvailable()) {
      return;
    }
    F.execute(new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        if ((SSActivity.instance == null) || (SSActivity.instance.isFinishing())) {
          return null;
        }
        ApiManager.sendPurchaseSuccessToServer(ApiManager.this);
        paramAnonymousVarArgs = new HashMap();
        paramAnonymousVarArgs.put("action", "purchase_intent");
        paramAnonymousVarArgs.put("userId", String.valueOf(SSActivity.game.getUserId()));
        paramAnonymousVarArgs.put("userKey", SSActivity.game.getUserKey());
        paramAnonymousVarArgs.put("xp", SSActivity.game.getAmountXP());
        paramAnonymousVarArgs.put("level", SSActivity.game.getUserLevel());
        paramAnonymousVarArgs.put("minutesplayed", SSActivity.game.getTotalMinutesPlayed());
        paramAnonymousVarArgs.put("item", paramString);
        ApiManager.getApiResponseString(ApiManager.this, SSActivity.string(ApiManager.this, "API_URL"), paramAnonymousVarArgs, false, true);
        return null;
      }
    });
  }
  
  public static void purchaseSuccess(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    checkPurchase = true;
    sendPurchaseSuccessToServer(paramContext);
  }
  
  public static void purchaseSuccessGetJar(Context paramContext, long paramLong)
  {
    ArrayList localArrayList = SSActivity.dcm.getGameStatePropertyAsArrayList("successfullPurchasesWithGetJar", ",");
    localArrayList.add(String.valueOf(paramLong));
    SSActivity.dcm.setGameStateProperty("successfullPurchasesWithGetJar", F.toString(localArrayList, ","));
    checkPurchase = true;
    sendPurchaseSuccessToServer(paramContext);
  }
  
  public static boolean registerAweber(String paramString)
  {
    if (!MetaData.isNetworkAvailable()) {
      ErrorMessage.show(SSActivity.string("no_internet_connection"));
    }
    do
    {
      return false;
      HashMap localHashMap = new HashMap();
      localHashMap.put("action", "register");
      localHashMap.put("userId", String.valueOf(SSActivity.game.getUserId()));
      localHashMap.put("userKey", SSActivity.game.getUserKey());
      localHashMap.put("email", paramString);
      paramString = getApiResponseString(SSActivity.instance, SSActivity.string("API_URL"), localHashMap, false, true);
    } while ((paramString == null) || (!paramString.trim().equals("SUCCESS")));
    return true;
  }
  
  private static void registerVerifiedPurchase(Context paramContext, DataCollection.Record paramRecord)
  {
    if (SSActivity.instance == null) {
      return;
    }
    Object localObject2 = getPricePoints(paramContext);
    ((ArrayList)localObject2).addAll(getGoldRewardPricePoints(paramContext));
    String str2 = paramRecord.getValue("sku");
    String str3 = paramRecord.getValue("orderId");
    paramContext = "";
    paramRecord = "";
    Object localObject1 = "";
    int j = 0;
    double d1 = 0.0D;
    int i = 0;
    Iterator localIterator = ((ArrayList)localObject2).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        if ((paramContext == null) || (paramContext.equals(""))) {
          break;
        }
        SSActivity.dcm.setGameStateProperty("timestampOfLastPurchase", Long.valueOf(F.getYYYYMMDDHHSS()));
        i = F.toInt(SSActivity.dcm.getGameStateProperty("amountOfPurchases:" + str2), Integer.valueOf(0)).intValue();
        SSActivity.dcm.setGameStateProperty("amountOfPurchases:" + str2, Integer.valueOf(i + 1));
        i = F.toInt(SSActivity.dcm.getGameStateProperty("totalPurchases"), Integer.valueOf(0)).intValue();
        SSActivity.dcm.setGameStateProperty("totalPurchases", Integer.valueOf(i + 1));
        SSActivity.sendAnalyticsPurchase(paramRecord, str3, str2, d1 / 100.0D);
        AdjustManager.trackRevenue(d1);
        AdWordsConversionManager.trackRevenue((String)localObject1);
        SSActivity.trackPurchaseEvent(paramRecord, j);
        return;
      }
      Object localObject3 = (PricePoint)localIterator.next();
      if ((i == 0) && (((PricePoint)localObject3).name.trim().equalsIgnoreCase(str2.trim())))
      {
        label293:
        long l1;
        long l2;
        long l3;
        if (((PricePoint)localObject3).name.endsWith(PricePoint.PRICEPOINT_SALE_SUFFIX))
        {
          localObject2 = " (SALE)";
          if (!isStartersPack((PricePoint)localObject3)) {
            break label610;
          }
          l1 = SSActivity.game.getAmountDiamondsInStartersPack();
          l2 = SSActivity.game.getAmountGoldInStartersPack();
          l3 = SSActivity.game.getAmountCashInStartersPack();
          paramRecord = paramContext;
          if (l1 > 0L) {
            paramRecord = l1 + " diamonds";
          }
          paramContext = paramRecord;
          if (l2 > 0L)
          {
            if (!paramRecord.equals("")) {
              break label596;
            }
            paramContext = "";
            label383:
            paramContext = paramContext + l2 + " gold";
          }
          localObject2 = paramContext;
          if (l3 > 0L) {
            if (!paramContext.equals("")) {
              break label603;
            }
          }
        }
        label596:
        label603:
        for (paramContext = "";; paramContext = " + ")
        {
          localObject2 = paramContext + l3 + " cash";
          String str1 = "Starters Pack";
          long l4 = (F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 1000000.0D);
          double d2 = F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 100.0D;
          localObject3 = ((PricePoint)localObject3).euroPrice;
          int k = 1;
          if (l1 > 0L) {
            SSActivity.trackPurchaseDiamondsEventV2(l1);
          }
          if (l2 > 0L) {
            SSActivity.trackPurchaseGoldEventV2(l2);
          }
          paramRecord = str1;
          paramContext = (Context)localObject2;
          localObject1 = localObject3;
          d1 = d2;
          i = k;
          if (l3 <= 0L) {
            break;
          }
          SSActivity.trackPurchaseCashEventV2(l3);
          paramRecord = str1;
          paramContext = (Context)localObject2;
          localObject1 = localObject3;
          d1 = d2;
          i = k;
          break;
          localObject2 = "";
          break label293;
          paramContext = " + ";
          break label383;
        }
        label610:
        if (((PricePoint)localObject3).diamondsAmount > 0)
        {
          paramContext = ((PricePoint)localObject3).diamondsAmount + " diamonds";
          paramRecord = "Diamonds" + (String)localObject2;
          j = ((PricePoint)localObject3).diamondsAmount;
          l1 = (F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 1000000.0D);
          d1 = F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 100.0D;
          localObject1 = ((PricePoint)localObject3).euroPrice;
          i = 1;
          SSActivity.trackPurchaseDiamondsEventV2(j);
        }
        if (((PricePoint)localObject3).goldAmount > 0)
        {
          paramContext = ((PricePoint)localObject3).goldAmount + " gold";
          paramRecord = "Gold" + (String)localObject2;
          j = ((PricePoint)localObject3).goldAmount;
          l1 = (F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 1000000.0D);
          d1 = F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 100.0D;
          localObject1 = ((PricePoint)localObject3).euroPrice;
          i = 1;
        }
        if (((PricePoint)localObject3).cashAmount > 0)
        {
          paramContext = ((PricePoint)localObject3).cashAmount + " cash";
          paramRecord = "Cash" + (String)localObject2;
          j = ((PricePoint)localObject3).cashAmount;
          l1 = (F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 1000000.0D);
          d1 = F.toDouble(((PricePoint)localObject3).euroPrice, Integer.valueOf(0)).doubleValue() * 100.0D;
          localObject1 = ((PricePoint)localObject3).euroPrice;
          i = 1;
        }
      }
    }
  }
  
  public static boolean saleAvailable()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    while (saleSecondsLeft() <= 0L) {
      return false;
    }
    return true;
  }
  
  public static long saleSecondsLeft()
  {
    if (SSActivity.disableSparkSocFeatures()) {}
    while (saleAlert == null) {
      return 0L;
    }
    return Math.max(0L, saleAlert.getTimeLeftSeconds());
  }
  
  public static String saleTimeLeft()
  {
    if (SSActivity.disableSparkSocFeatures()) {
      return "";
    }
    return F.timeFormat(saleSecondsLeft());
  }
  
  public static String saleType()
  {
    if (SSActivity.disableSparkSocFeatures()) {
      return "";
    }
    return SSActivity.dcm.getApiProperty("saletype", "");
  }
  
  public static void saveGameToServer(Context paramContext)
  {
    saveGameToServer(paramContext, false);
  }
  
  private static void saveGameToServer(Context paramContext, boolean paramBoolean)
  {
    if (SSActivity.dcm == null)
    {
      SSActivity.dcm = new DataCollectionManager();
      SSActivity.dcm.init(paramContext);
    }
    for (;;)
    {
      int i;
      long l;
      try
      {
        i = F.toInt(SSActivity.dcm.getGameStateProperty("minutesPlayedOnLastSave", null), Integer.valueOf(0)).intValue();
        l = F.toLong(SSActivity.dcm.getGameStateProperty("minutesPlayed", null), Integer.valueOf(0)).longValue();
        if (!paramBoolean)
        {
          if (SSActivity.game == null) {
            break label174;
          }
          if (SSActivity.game.islandChangedSinceLastSave()) {
            break label174;
          }
        }
        else
        {
          F.debug("*** SAVING GAME TO SERVER ***");
          if (SSActivity.game != null) {
            SSActivity.game.islandSaved();
          }
          SSActivity.dcm.setGameStateProperty("minutesPlayedOnLastSave", l);
          HashMap localHashMap = new HashMap();
          localHashMap.put("action", "savegame");
          getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), localHashMap, true, false);
        }
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      label174:
      if (l - i < 10L) {}
    }
  }
  
  @SuppressLint({"DefaultLocale"})
  public static boolean sendGift(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null) || (paramString3 == null) || (paramString4 == null)) {}
    do
    {
      do
      {
        return false;
      } while (!GCMManager.checkPlayServices(true));
      HashMap localHashMap = new HashMap();
      localHashMap.put("to", paramString1);
      localHashMap.put("fbid", paramString2);
      localHashMap.put("name", paramString3);
      localHashMap.put("gift", paramString4);
      localHashMap.put("action", "send_gift");
      paramContext = getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), localHashMap, false, false);
      Log.i("NewSocial", "sendGift result is " + paramContext);
    } while (!"SUCCESS".equals(paramContext));
    SSActivity.trackSocialEvent("Send gift: " + paramString4.toLowerCase());
    return true;
  }
  
  public static boolean sendMessage(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {}
    do
    {
      do
      {
        return false;
      } while (!GCMManager.checkPlayServices(true));
      HashMap localHashMap = new HashMap();
      localHashMap.put("to", paramString1);
      localHashMap.put("fbid", paramString2);
      localHashMap.put("name", paramString3);
      localHashMap.put("message", paramString4);
      localHashMap.put("action", "send_message");
      paramContext = getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), localHashMap, false, false);
      Log.i("NewSocial", "sendMessage result is " + paramContext);
    } while (!"SUCCESS".equals(paramContext));
    SSActivity.trackSocialEvent("Send message to friend");
    return true;
  }
  
  public static void sendPurchaseSuccessToServer(Context paramContext)
  {
    if (!checkPurchase) {
      return;
    }
    int j = 0;
    checkPurchase = false;
    if (SSActivity.dcm == null)
    {
      SSActivity.dcm = new DataCollectionManager();
      SSActivity.dcm.init(paramContext);
    }
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = SSActivity.dcm.getPurchases();
    label64:
    int i;
    if (localObject1 != null)
    {
      localObject1 = ((ArrayList)localObject1).iterator();
      if (((Iterator)localObject1).hasNext()) {}
    }
    else
    {
      i = j;
      if (localArrayList.size() > 0)
      {
        i = 1;
        localObject1 = localArrayList.iterator();
        label91:
        if (((Iterator)localObject1).hasNext()) {
          break label681;
        }
      }
      localArrayList.clear();
      localArrayList = new ArrayList();
      localObject1 = SSActivity.dcm.getGameStatePropertyAsArrayList("successfullPurchasesWithGetJar", ",");
      j = i;
      if (localObject1 != null) {
        localObject1 = ((ArrayList)localObject1).iterator();
      }
    }
    for (;;)
    {
      Object localObject3;
      if (!((Iterator)localObject1).hasNext())
      {
        j = i;
        SSActivity.dcm.setGameStateProperty("successfullPurchasesWithGetJar", F.toString(localArrayList, ","));
        if (j == 0) {
          break;
        }
        saveGameToServer(paramContext, true);
        return;
        localObject2 = (DataCollection.Record)((Iterator)localObject1).next();
        if (localObject2 == null) {
          break label64;
        }
        localObject3 = new HashMap();
        if ((SSActivity.getPlatform() != SSActivity.Platform.AMAZON) && ((((DataCollection.Record)localObject2).getValue("orderId") == null) || (((DataCollection.Record)localObject2).getValue("orderId").equals("")) || (((DataCollection.Record)localObject2).getValue("sku") == null) || (((DataCollection.Record)localObject2).getValue("sku").equals("")) || (((DataCollection.Record)localObject2).getValue("purchaseData") == null) || (((DataCollection.Record)localObject2).getValue("purchaseData").equals("")) || (((DataCollection.Record)localObject2).getValue("signature") == null) || (((DataCollection.Record)localObject2).getValue("signature").equals("")))) {}
        for (i = 0;; i = 1)
        {
          if (i != 0) {
            break label345;
          }
          SSActivity.dcm.removePurchase(((DataCollection.Record)localObject2).getId());
          break;
        }
        label345:
        if (SSActivity.getPlatform() == SSActivity.Platform.AMAZON) {
          ((HashMap)localObject3).put("action", "purchase_success");
        }
        for (;;)
        {
          ((HashMap)localObject3).put("userId", String.valueOf(SSActivity.game.getUserId()));
          ((HashMap)localObject3).put("userKey", SSActivity.game.getUserKey());
          ((HashMap)localObject3).put("xp", SSActivity.game.getAmountXP());
          ((HashMap)localObject3).put("level", SSActivity.game.getUserLevel());
          ((HashMap)localObject3).put("minutesplayed", SSActivity.game.getTotalMinutesPlayed());
          ((HashMap)localObject3).put("sku", ((DataCollection.Record)localObject2).getValue("sku"));
          ((HashMap)localObject3).put("orderId", ((DataCollection.Record)localObject2).getValue("orderId"));
          ((HashMap)localObject3).put("purchaseData", ((DataCollection.Record)localObject2).getValue("purchaseData"));
          ((HashMap)localObject3).put("signature", ((DataCollection.Record)localObject2).getValue("signature"));
          localObject3 = getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), (HashMap)localObject3, false, true);
          if (localObject3 == null) {
            break;
          }
          if (!((String)localObject3).trim().equalsIgnoreCase("OK")) {
            break label653;
          }
          SSActivity.dcm.removePurchase(((DataCollection.Record)localObject2).getId());
          localArrayList.add(localObject2);
          break;
          if (SSActivity.getPlatform() == SSActivity.Platform.SFR) {
            ((HashMap)localObject3).put("action", "purchase_success");
          } else {
            ((HashMap)localObject3).put("action", "purchase_success_extended");
          }
        }
        label653:
        if (!((String)localObject3).trim().equalsIgnoreCase("ERROR")) {
          break label64;
        }
        SSActivity.dcm.removePurchase(((DataCollection.Record)localObject2).getId());
        break label64;
        label681:
        registerVerifiedPurchase(paramContext, (DataCollection.Record)((Iterator)localObject1).next());
        break label91;
      }
      Object localObject2 = (String)((Iterator)localObject1).next();
      if ((localObject2 != null) && (!((String)localObject2).trim().equals("")))
      {
        localObject3 = new HashMap();
        ((HashMap)localObject3).put("action", "purchase_success_getjar");
        ((HashMap)localObject3).put("userId", String.valueOf(SSActivity.game.getUserId()));
        ((HashMap)localObject3).put("userKey", SSActivity.game.getUserKey());
        ((HashMap)localObject3).put("xp", SSActivity.game.getAmountXP());
        ((HashMap)localObject3).put("level", SSActivity.game.getUserLevel());
        ((HashMap)localObject3).put("minutesplayed", SSActivity.game.getTotalMinutesPlayed());
        ((HashMap)localObject3).put("gold", localObject2);
        localObject3 = getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), (HashMap)localObject3, false, true);
        if ((localObject3 == null) || (!((String)localObject3).trim().equalsIgnoreCase("OK"))) {
          localArrayList.add(localObject2);
        } else {
          i = 1;
        }
      }
    }
  }
  
  public static boolean setFacebookId(Context paramContext, String paramString)
  {
    String str = SSActivity.dcm.getGameStateProperty("fb_gcmRegistrationId", "");
    HashMap localHashMap = new HashMap();
    localHashMap.put("userId", String.valueOf(SSActivity.game.getUserId()));
    localHashMap.put("userKey", SSActivity.game.getUserKey());
    localHashMap.put("fbid", paramString);
    localHashMap.put("regid", str);
    localHashMap.put("action", "set_facebook_id");
    paramContext = getApiResponseString(paramContext, SSActivity.string(paramContext, "API_URL"), localHashMap, false, false);
    boolean bool = "SUCCESS".equals(paramContext);
    if (!bool) {
      Log.e("NewSocial", "Server returned " + paramContext);
    }
    return bool;
  }
  
  public static boolean updateAvailable()
  {
    String str = SSActivity.dcm.getApiProperty("update_available");
    return (str != null) && (str.trim().equalsIgnoreCase("true"));
  }
  
  public static class PricePointReward
    extends PricePoint
  {
    public int baseGoldAmount;
    public int extraGoldAmount;
    
    public PricePointReward() {}
  }
}
