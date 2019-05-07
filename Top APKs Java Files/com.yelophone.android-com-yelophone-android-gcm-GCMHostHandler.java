package com.yelophone.android.gcm;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.util.Base64;
import android.util.Log;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.yelophone.android.YeloPhoneClient;
import com.yelophone.android.YeloPhoneIntentService;
import com.yelophone.android.YeloPhoneService;
import com.yelophone.android.data.ConversationItem;
import com.yelophone.android.phone.ContactDetails;
import com.yelophone.android.phone.ContactsManager;
import com.yelophone.android.phone.ContactsManagerFactory;
import com.yelophone.android.phone.call.CallLogManager;
import com.yelophone.android.phone.call.CallLogManagerFactory;
import com.yelophone.android.phone.location.LocationManager;
import com.yelophone.android.phone.location.LocationResult;
import com.yelophone.android.phone.location.SingleUpdateLocationManagerImpl;
import com.yelophone.android.phone.sms.SmsManager;
import com.yelophone.android.phone.sms.SmsManagerFactory;
import com.yelophone.android.twilio.TwilioPhone;
import com.yelophone.android.twilio.TwilioService;
import com.yelophone.android.ui.VoicemailFragment;
import com.yelophone.android.ui.YeloPhoneMainActivity;
import com.yelophone.android.utils.Events;
import com.yelophone.android.utils.Notifications;
import com.yelophone.android.utils.Preferences;
import com.yelophone.android.utils.Sound;
import com.yelophone.android.utils.UI;
import com.yelophone.android.utils.Utils;
import com.yelophone.android.voicemail.VoicemailManager;
import com.yelophone.android.voicemail.VoicemailManagerFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GCMHostHandler
  extends GCMHandlerAbstract
{
  private static final String TAG = "GCMHostHandler";
  private static final PhoneNumberUtil phoneNumberUtil = ;
  private List<ApplicationInfo> PACKAGES = null;
  private final CallLogManager callLogManager = CallLogManagerFactory.getCallLogManager();
  private final LocationManager locationManager = new SingleUpdateLocationManagerImpl();
  private final SmsManager smsManager = SmsManagerFactory.getSmsManager();
  private int smsNotification = 0;
  
  public GCMHostHandler() {}
  
  private void handleAddToFavorites(JSONObject paramJSONObject)
  {
    for (;;)
    {
      try
      {
        str1 = paramJSONObject.optString("request_id");
        try
        {
          String str2 = paramJSONObject.optString("address");
          boolean bool1 = paramJSONObject.getBoolean("add");
          boolean bool2 = ContactsManagerFactory.getContactsManager().addToFavorites(getContext(), str2, bool1);
          JSONObject localJSONObject = new JSONObject();
          if (!bool2) {
            continue;
          }
          paramJSONObject = "OK";
          localJSONObject.put("status", paramJSONObject);
          localJSONObject.put("type", "favorites");
          localJSONObject.put("address", str2);
          localJSONObject.put("request_id", str1);
          localJSONObject.put("starred", bool1);
          YeloPhoneClient.getInstance().sendChannel(localJSONObject);
        }
        catch (Exception paramJSONObject)
        {
          Log.w("GCMHostHandler", "add to favorites error", paramJSONObject);
          YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str1);
          continue;
        }
        return;
      }
      finally {}
      paramJSONObject = "KO";
    }
  }
  
  /* Error */
  private void handleAlarm(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 86
    //   5: invokevirtual 92	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_1
    //   9: new 88	org/json/JSONObject
    //   12: dup
    //   13: invokespecial 117	org/json/JSONObject:<init>	()V
    //   16: astore_2
    //   17: aload_2
    //   18: ldc -92
    //   20: aload_0
    //   21: invokevirtual 110	com/yelophone/android/gcm/GCMHostHandler:getContext	()Landroid/content/Context;
    //   24: invokestatic 170	com/yelophone/android/utils/Utils:toogleAlarm	(Landroid/content/Context;)Z
    //   27: invokevirtual 134	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   30: pop
    //   31: aload_2
    //   32: ldc 121
    //   34: ldc 119
    //   36: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   39: pop
    //   40: aload_2
    //   41: ldc 127
    //   43: ldc -84
    //   45: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   48: pop
    //   49: aload_2
    //   50: ldc 86
    //   52: aload_1
    //   53: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   56: pop
    //   57: invokestatic 139	com/yelophone/android/YeloPhoneClient:getInstance	()Lcom/yelophone/android/YeloPhoneClient;
    //   60: aload_2
    //   61: invokevirtual 143	com/yelophone/android/YeloPhoneClient:sendChannel	(Lorg/json/JSONObject;)Lorg/json/JSONObject;
    //   64: pop
    //   65: invokestatic 139	com/yelophone/android/YeloPhoneClient:getInstance	()Lcom/yelophone/android/YeloPhoneClient;
    //   68: aload_1
    //   69: iconst_0
    //   70: invokevirtual 175	com/yelophone/android/YeloPhoneClient:sendDeviceStatus	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   73: pop
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_2
    //   78: ldc 21
    //   80: ldc -109
    //   82: aload_2
    //   83: invokestatic 153	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   86: pop
    //   87: invokestatic 139	com/yelophone/android/YeloPhoneClient:getInstance	()Lcom/yelophone/android/YeloPhoneClient;
    //   90: aload_2
    //   91: invokevirtual 157	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   94: aload_1
    //   95: invokevirtual 161	com/yelophone/android/YeloPhoneClient:sendError	(Ljava/lang/String;Ljava/lang/String;)V
    //   98: goto -24 -> 74
    //   101: astore_1
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_1
    //   105: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	this	GCMHostHandler
    //   0	106	1	paramJSONObject	JSONObject
    //   16	45	2	localJSONObject	JSONObject
    //   77	14	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   9	74	77	java/lang/Exception
    //   2	9	101	finally
    //   9	74	101	finally
    //   78	98	101	finally
  }
  
  private void handleApplications(JSONObject paramJSONObject)
  {
    for (;;)
    {
      String str;
      Object localObject;
      ArrayList localArrayList;
      try
      {
        str = paramJSONObject.optString("request_id");
        try
        {
          localObject = ((ActivityManager)getContext().getSystemService("activity")).getRunningAppProcesses();
          localArrayList = new ArrayList();
          localObject = ((List)localObject).iterator();
          if (!((Iterator)localObject).hasNext()) {
            continue;
          }
          localArrayList.add(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next()).processName);
          continue;
        }
        catch (Exception paramJSONObject)
        {
          Log.w("GCMHostHandler", "files error", paramJSONObject);
          YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str);
        }
        return;
      }
      finally {}
      PackageManager localPackageManager = getContext().getPackageManager();
      JSONObject localJSONObject1;
      JSONArray localJSONArray;
      if (this.PACKAGES != null)
      {
        localObject = this.PACKAGES;
        this.PACKAGES = ((List)localObject);
        localJSONObject1 = new JSONObject();
        localJSONObject1.put("request_id", str);
        localJSONObject1.put("type", "applications");
        localJSONArray = new JSONArray();
        localObject = ((List)localObject).iterator();
        if (((Iterator)localObject).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
          Log.d("GCMHostHandler", "Installed package :" + localApplicationInfo.packageName);
          Log.d("GCMHostHandler", "Launch Activity :" + localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName));
          CharSequence localCharSequence = localApplicationInfo.loadLabel(localPackageManager);
          JSONObject localJSONObject2 = new JSONObject();
          localJSONObject2.put("package", localApplicationInfo.packageName);
          localJSONObject2.put("name", localCharSequence);
          localJSONObject2.put("running", localArrayList.contains(localApplicationInfo.processName));
          localJSONArray.put(localJSONObject2);
          continue;
        }
      }
      else
      {
        localObject = localPackageManager.getInstalledApplications(128);
        continue;
      }
      localJSONObject1.put("applications", localJSONArray);
      localJSONObject1.put("status", "OK");
      YeloPhoneClient.getInstance().sendChannel(localJSONObject1, paramJSONObject.optString("client_id"));
    }
  }
  
  private void handleAvatar(JSONObject paramJSONObject)
  {
    String str1 = paramJSONObject.optString("request_id");
    try
    {
      JSONObject localJSONObject = new JSONObject();
      String str2 = paramJSONObject.optString("address");
      byte[] arrayOfByte = ContactsManagerFactory.getContactsManager().getContactPicture(getContext(), str2);
      if ((arrayOfByte != null) && (arrayOfByte.length > 0)) {
        localJSONObject.put("data", Base64.encodeToString(arrayOfByte, 2));
      }
      for (;;)
      {
        localJSONObject.put("request_id", str1);
        localJSONObject.put("type", "avatar");
        localJSONObject.put("address", str2);
        localJSONObject.put("status", "OK");
        YeloPhoneClient.getInstance().sendChannel(localJSONObject, paramJSONObject.optString("client_id"));
        return;
        localJSONObject.put("data", "n/a");
      }
      return;
    }
    catch (Exception paramJSONObject)
    {
      Log.e("GCMHostHandler", "Avatar error", paramJSONObject);
      YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str1);
    }
  }
  
  private void handleCallForwardingPassword(JSONObject paramJSONObject)
  {
    String str = paramJSONObject.optString("request_id");
    paramJSONObject = paramJSONObject.optString("password", null);
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("status", "OK");
      localJSONObject.put("request_id", str);
      localJSONObject.put("type", "call_forwarding_password");
      if ((paramJSONObject == null) || (paramJSONObject.length() <= 5)) {
        localJSONObject.put("status", "KO");
      }
      for (;;)
      {
        YeloPhoneClient.getInstance().sendChannel(localJSONObject);
        return;
        Preferences.setSharedPreference(getContext(), "call_forwarding_password", paramJSONObject);
      }
      return;
    }
    catch (Exception paramJSONObject)
    {
      Log.w("GCMHostHandler", "add to favorites error", paramJSONObject);
      YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str);
    }
  }
  
  private void handleCallLog(JSONObject paramJSONObject)
  {
    try
    {
      String str1 = paramJSONObject.optString("address");
      String str2 = paramJSONObject.optString("call_type");
      String str3 = paramJSONObject.optString("call_sid");
      long l = paramJSONObject.optLong("date", System.currentTimeMillis());
      int i = paramJSONObject.optInt("duration", 0);
      this.callLogManager.addCallLog(getContext(), str1, i, str2, l, paramJSONObject.optBoolean("yelo", false), str3);
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        Log.w("GCMHostHandler", "call log error", paramJSONObject);
      }
    }
    finally {}
  }
  
  private void handleContacts(JSONObject paramJSONObject)
  {
    for (;;)
    {
      String str;
      Collection localCollection;
      boolean bool;
      Object localObject1;
      Object localObject2;
      Object localObject4;
      try
      {
        str = paramJSONObject.optString("request_id");
        try
        {
          localCollection = ContactsManagerFactory.getContactsManager().getContactDetails(getContext());
          bool = paramJSONObject.optBoolean("full", false);
          paramJSONObject = new JSONArray();
          localObject1 = localCollection.iterator();
          if (!((Iterator)localObject1).hasNext()) {
            break label302;
          }
          localObject2 = (ContactDetails)((Iterator)localObject1).next();
          if (!bool) {
            break label212;
          }
          localObject3 = new JSONObject();
          ((JSONObject)localObject3).put("name", ((ContactDetails)localObject2).getName());
          ((JSONObject)localObject3).put("starred", ((ContactDetails)localObject2).getStared());
          localObject4 = new JSONArray();
          localObject2 = ((ContactDetails)localObject2).getNumbers().iterator();
          if (!((Iterator)localObject2).hasNext()) {
            continue;
          }
          ((JSONArray)localObject4).put((String)((Iterator)localObject2).next());
          continue;
        }
        catch (Exception paramJSONObject)
        {
          Log.w("GCMHostHandler", "load contacts error", paramJSONObject);
          YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str);
        }
        return;
      }
      finally {}
      ((JSONObject)localObject3).put("numbers", localObject4);
      paramJSONObject.put(localObject3);
      continue;
      label212:
      Object localObject3 = ((ContactDetails)localObject2).getNumbers().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (String)((Iterator)localObject3).next();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("name", ((ContactDetails)localObject2).getName());
        localJSONObject.put("address", localObject4);
        localJSONObject.put("starred", ((ContactDetails)localObject2).getStared());
        paramJSONObject.put(localJSONObject);
      }
      continue;
      label302:
      if (paramJSONObject.length() > 0)
      {
        localObject1 = new JSONObject();
        ((JSONObject)localObject1).put("status", "OK");
        ((JSONObject)localObject1).put("request_id", str);
        ((JSONObject)localObject1).put("batch", 1);
        ((JSONObject)localObject1).put("total_batches", 1);
        ((JSONObject)localObject1).put("contacts", paramJSONObject);
        ((JSONObject)localObject1).put("type", "contacts");
        ((JSONObject)localObject1).put("full", bool);
        YeloPhoneClient.getInstance().sendChannel((JSONObject)localObject1);
      }
      Log.d("GCMHostHandler", "Contacts size " + localCollection.size());
    }
  }
  
  private void handleConversation(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject;
    Object localObject1;
    Object localObject2;
    int i;
    label121:
    try
    {
      String str1 = paramJSONObject.optString("request_id");
      try
      {
        localJSONObject = new JSONObject();
        localObject1 = paramJSONObject.getString("action");
        localObject2 = paramJSONObject.optString("thread_id", null);
        i = paramJSONObject.optInt("limit", -1);
        localJSONObject.put("request_id", str1);
        localJSONObject.put("thread_id", localObject2);
        if (!"delete".equals(localObject1)) {
          break label202;
        }
        if (localObject2 != null) {
          break label121;
        }
        throw new IllegalArgumentException("invalid thread id");
      }
      catch (Exception paramJSONObject)
      {
        Log.w("GCMHostHandler", "load conversation", paramJSONObject);
        YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str1);
      }
      return;
    }
    finally {}
    if (this.smsManager.deleteConversation(getContext(), (String)localObject2)) {
      localJSONObject.put("status", "OK");
    }
    label202:
    String str2;
    long l;
    for (;;)
    {
      localJSONObject.put("type", "conversation");
      YeloPhoneClient.getInstance().sendChannel(localJSONObject);
      break;
      localJSONObject.put("status", "KO");
      localJSONObject.put("error", "Message not deleted");
      continue;
      str2 = paramJSONObject.getString("address");
      Object localObject3 = "address = ?";
      Object localObject4 = "number = ?";
      l = paramJSONObject.optLong("since", 0L);
      Boolean localBoolean = Boolean.valueOf(paramJSONObject.optBoolean("mark_as_read", false));
      localObject2 = new String[1];
      localObject2[0] = str2;
      localObject1 = localObject3;
      paramJSONObject = (JSONObject)localObject4;
      try
      {
        Object localObject5 = phoneNumberUtil.parseAndKeepRawInput(str2, Utils.getNetworkCountry(getContext()));
        localObject1 = localObject3;
        paramJSONObject = (JSONObject)localObject4;
        String str3 = phoneNumberUtil.format((Phonenumber.PhoneNumber)localObject5, PhoneNumberUtil.PhoneNumberFormat.E164);
        localObject1 = localObject3;
        paramJSONObject = (JSONObject)localObject4;
        localObject5 = phoneNumberUtil.format((Phonenumber.PhoneNumber)localObject5, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
        localObject1 = localObject3;
        paramJSONObject = (JSONObject)localObject4;
        String str4 = ((String)localObject5).replace(" ", "");
        localObject1 = localObject3;
        paramJSONObject = (JSONObject)localObject4;
        localObject3 = str3.replace(" ", "");
        localObject1 = "(address = ? or address = ? or address = ? or address = ?)";
        paramJSONObject = "(number = ? or number = ? or number = ? or number = ?)";
        localObject2 = new String[] { str3, localObject5, str4, localObject3 };
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("GCMHostHandler", "Pasrsing error", localException);
        }
        localJSONObject.put("address", str2);
        localJSONObject.put("since", l);
        localJSONObject.put("conversation", localException);
        localJSONObject.put("status", "OK");
      }
      localObject4 = this.smsManager.loadConversation(getContext(), (String)localObject1, (String[])localObject2, l, localBoolean.booleanValue());
      localObject3 = localObject4;
      if (localObject4 == null) {
        localObject3 = new JSONArray();
      }
      new StringBuilder().append((String)localObject1).append(" DATE > ").append(l).toString();
      paramJSONObject = this.callLogManager.loadCallLog(getContext(), paramJSONObject, (String[])localObject2, localBoolean.booleanValue(), i).iterator();
      while (paramJSONObject.hasNext()) {
        ((JSONArray)localObject3).put(((ConversationItem)paramJSONObject.next()).toJSON());
      }
    }
  }
  
  /* Error */
  private void handleConversations(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 86
    //   5: invokevirtual 92	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   8: astore 8
    //   10: new 521	java/util/HashMap
    //   13: dup
    //   14: invokespecial 522	java/util/HashMap:<init>	()V
    //   17: astore 9
    //   19: aload_1
    //   20: ldc_w 450
    //   23: lconst_0
    //   24: invokevirtual 345	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
    //   27: lstore_3
    //   28: aload_1
    //   29: ldc_w 524
    //   32: lconst_0
    //   33: invokevirtual 345	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
    //   36: lstore 5
    //   38: lload 5
    //   40: lconst_0
    //   41: lcmp
    //   42: ifle +172 -> 214
    //   45: aload_0
    //   46: getfield 56	com/yelophone/android/gcm/GCMHostHandler:smsManager	Lcom/yelophone/android/phone/sms/SmsManager;
    //   49: aload_0
    //   50: invokevirtual 110	com/yelophone/android/gcm/GCMHostHandler:getContext	()Landroid/content/Context;
    //   53: lload 5
    //   55: iconst_m1
    //   56: invokeinterface 528 5 0
    //   61: astore 7
    //   63: aload_0
    //   64: aload 9
    //   66: aload 7
    //   68: invokespecial 532	com/yelophone/android/gcm/GCMHostHandler:processConversationItems	(Ljava/util/Map;Ljava/util/Collection;)V
    //   71: aload_1
    //   72: ldc_w 418
    //   75: bipush 100
    //   77: invokevirtual 351	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
    //   80: istore_2
    //   81: lload 5
    //   83: lconst_0
    //   84: lcmp
    //   85: ifle +149 -> 234
    //   88: aload_0
    //   89: getfield 69	com/yelophone/android/gcm/GCMHostHandler:callLogManager	Lcom/yelophone/android/phone/call/CallLogManager;
    //   92: aload_0
    //   93: invokevirtual 110	com/yelophone/android/gcm/GCMHostHandler:getContext	()Landroid/content/Context;
    //   96: new 232	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 534
    //   106: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: lload 5
    //   111: invokevirtual 503	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   114: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: aconst_null
    //   118: iconst_0
    //   119: iload_2
    //   120: invokeinterface 507 6 0
    //   125: astore_1
    //   126: aload_0
    //   127: aload 9
    //   129: aload_1
    //   130: invokespecial 532	com/yelophone/android/gcm/GCMHostHandler:processConversationItems	(Ljava/util/Map;Ljava/util/Collection;)V
    //   133: new 227	org/json/JSONArray
    //   136: dup
    //   137: invokespecial 228	org/json/JSONArray:<init>	()V
    //   140: astore_1
    //   141: aload 9
    //   143: invokeinterface 540 1 0
    //   148: invokeinterface 375 1 0
    //   153: astore 7
    //   155: aload 7
    //   157: invokeinterface 205 1 0
    //   162: ifeq +112 -> 274
    //   165: aload_1
    //   166: aload 7
    //   168: invokeinterface 209 1 0
    //   173: checkcast 509	com/yelophone/android/data/ConversationItem
    //   176: invokeinterface 513 1 0
    //   181: invokevirtual 277	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   184: pop
    //   185: goto -30 -> 155
    //   188: astore_1
    //   189: ldc 21
    //   191: ldc_w 542
    //   194: aload_1
    //   195: invokestatic 153	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   198: pop
    //   199: invokestatic 139	com/yelophone/android/YeloPhoneClient:getInstance	()Lcom/yelophone/android/YeloPhoneClient;
    //   202: aload_1
    //   203: invokevirtual 157	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   206: aload 8
    //   208: invokevirtual 161	com/yelophone/android/YeloPhoneClient:sendError	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: aload_0
    //   212: monitorexit
    //   213: return
    //   214: aload_0
    //   215: getfield 56	com/yelophone/android/gcm/GCMHostHandler:smsManager	Lcom/yelophone/android/phone/sms/SmsManager;
    //   218: aload_0
    //   219: invokevirtual 110	com/yelophone/android/gcm/GCMHostHandler:getContext	()Landroid/content/Context;
    //   222: lload_3
    //   223: iconst_m1
    //   224: invokeinterface 545 5 0
    //   229: astore 7
    //   231: goto -168 -> 63
    //   234: aload_0
    //   235: getfield 69	com/yelophone/android/gcm/GCMHostHandler:callLogManager	Lcom/yelophone/android/phone/call/CallLogManager;
    //   238: aload_0
    //   239: invokevirtual 110	com/yelophone/android/gcm/GCMHostHandler:getContext	()Landroid/content/Context;
    //   242: new 232	java/lang/StringBuilder
    //   245: dup
    //   246: invokespecial 233	java/lang/StringBuilder:<init>	()V
    //   249: ldc_w 547
    //   252: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: lload_3
    //   256: invokevirtual 503	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   259: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   262: aconst_null
    //   263: iconst_0
    //   264: iload_2
    //   265: invokeinterface 507 6 0
    //   270: astore_1
    //   271: goto -145 -> 126
    //   274: new 88	org/json/JSONObject
    //   277: dup
    //   278: invokespecial 117	org/json/JSONObject:<init>	()V
    //   281: astore 7
    //   283: aload 7
    //   285: ldc 121
    //   287: ldc 119
    //   289: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   292: pop
    //   293: aload 7
    //   295: ldc_w 549
    //   298: aload_1
    //   299: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   302: pop
    //   303: aload 7
    //   305: ldc_w 450
    //   308: lload_3
    //   309: invokevirtual 518	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   312: pop
    //   313: aload 7
    //   315: ldc 86
    //   317: aload 8
    //   319: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   322: pop
    //   323: aload 7
    //   325: ldc 127
    //   327: ldc_w 549
    //   330: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   333: pop
    //   334: invokestatic 139	com/yelophone/android/YeloPhoneClient:getInstance	()Lcom/yelophone/android/YeloPhoneClient;
    //   337: aload 7
    //   339: invokevirtual 143	com/yelophone/android/YeloPhoneClient:sendChannel	(Lorg/json/JSONObject;)Lorg/json/JSONObject;
    //   342: pop
    //   343: goto -132 -> 211
    //   346: astore_1
    //   347: aload_0
    //   348: monitorexit
    //   349: aload_1
    //   350: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	351	0	this	GCMHostHandler
    //   0	351	1	paramJSONObject	JSONObject
    //   80	185	2	i	int
    //   27	282	3	l1	long
    //   36	74	5	l2	long
    //   61	277	7	localObject	Object
    //   8	310	8	str	String
    //   17	125	9	localHashMap	java.util.HashMap
    // Exception table:
    //   from	to	target	type
    //   10	38	188	java/lang/Exception
    //   45	63	188	java/lang/Exception
    //   63	81	188	java/lang/Exception
    //   88	126	188	java/lang/Exception
    //   126	155	188	java/lang/Exception
    //   155	185	188	java/lang/Exception
    //   214	231	188	java/lang/Exception
    //   234	271	188	java/lang/Exception
    //   274	343	188	java/lang/Exception
    //   2	10	346	finally
    //   10	38	346	finally
    //   45	63	346	finally
    //   63	81	346	finally
    //   88	126	346	finally
    //   126	155	346	finally
    //   155	185	346	finally
    //   189	211	346	finally
    //   214	231	346	finally
    //   234	271	346	finally
    //   274	343	346	finally
  }
  
  private void handleLocation(Handler paramHandler, final JSONObject paramJSONObject)
  {
    try
    {
      paramHandler.post(new Runnable()
      {
        public void run()
        {
          GCMHostHandler.LocationResultHandler localLocationResultHandler = new GCMHostHandler.LocationResultHandler(GCMHostHandler.this, paramJSONObject, null);
          if (!GCMHostHandler.this.locationManager.getLocation(GCMHostHandler.this.getContext(), localLocationResultHandler)) {
            Utils.startThread(new Runnable()
            {
              public void run()
              {
                String str = GCMHostHandler.3.this.val$message.optString("request_id");
                YeloPhoneClient.getInstance().sendError("Location error", str);
              }
            });
          }
        }
      });
      return;
    }
    finally
    {
      paramHandler = finally;
      throw paramHandler;
    }
  }
  
  private void handleSendSMS(JSONObject paramJSONObject)
    throws JSONException
  {
    String str = paramJSONObject.optString("request_id", null);
    try
    {
      this.smsManager.sendSms(getContext(), paramJSONObject.getString("address"), paramJSONObject.getString("text"), paramJSONObject.optString("thread_id", null), str);
      return;
    }
    catch (Exception paramJSONObject)
    {
      Log.w("GCMHostHandler", "Send SMS Error error", paramJSONObject);
      YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str);
    }
  }
  
  private void handleSms(JSONObject paramJSONObject)
    throws JSONException
  {
    Object localObject2 = paramJSONObject.getString("address");
    Object localObject1;
    Object localObject3;
    Object localObject4;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      localObject3 = paramJSONObject.getString("msg");
      localObject4 = ContactsManagerFactory.getContactsManager().getContactDetails(getContext(), (String)localObject2);
      if (localObject4 != null) {
        localObject1 = ((ContactDetails)localObject4).getName();
      }
      if (!paramJSONObject.optBoolean("sent", false)) {
        break label135;
      }
      paramJSONObject = paramJSONObject.optString("error", null);
      if ((paramJSONObject == null) || (paramJSONObject.length() <= 0)) {
        break label124;
      }
      UI.showShortToast(getContext(), getContext().getString(2131231373) + ": " + paramJSONObject);
    }
    label124:
    label135:
    do
    {
      return;
      UI.showShortToast(getContext(), 2131231377);
      return;
      localObject4 = new Intent(getContext(), YeloPhoneMainActivity.class);
      ((Intent)localObject4).setFlags(268435456);
      localObject1 = getContext().getString(2131230965) + " - " + String.format(getContext().getString(2131231257), new Object[] { localObject1 });
      localObject1 = Notifications.getNotificationBuilder(getContext(), (String)localObject1, (String)localObject3, (Intent)localObject4);
      localObject4 = ContactsManagerFactory.getContactsManager().getContactBitmap(getContext(), (String)localObject2, false, false, 0);
      if (localObject4 != null)
      {
        ((NotificationCompat.Builder)localObject1).extend(new NotificationCompat.WearableExtender().setHintHideIcon(false).setBackground((Bitmap)localObject4));
        localObject4 = ContactsManagerFactory.getContactsManager().getContactBitmap(getContext(), (String)localObject2, true, false, 0);
        if (localObject4 != null) {
          ((NotificationCompat.Builder)localObject1).setLargeIcon((Bitmap)localObject4);
        }
      }
      ((NotificationCompat.Builder)localObject1).setWhen(paramJSONObject.optLong("time", System.currentTimeMillis()));
      ((NotificationCompat.Builder)localObject1).setStyle(new NotificationCompat.BigTextStyle().bigText((CharSequence)localObject3));
      ((NotificationCompat.Builder)localObject1).setAutoCancel(false);
      paramJSONObject = new Intent(getContext(), TwilioService.class);
      paramJSONObject.putExtra("notification", this.smsNotification + 1000);
      localObject3 = new Intent(getContext(), TwilioService.class);
      ((Intent)localObject3).putExtra("yelo_call", true);
      ((Intent)localObject3).putExtra("yelo_number", (String)localObject2);
      ((Intent)localObject3).putExtra("notification", this.smsNotification + 1000);
      localObject4 = new Intent(getContext(), TwilioService.class);
      ((Intent)localObject4).putExtra("number_call", true);
      ((Intent)localObject4).putExtra("number", (String)localObject2);
      localObject2 = PendingIntent.getService(getContext(), Utils.getNewIntentId(), (Intent)localObject4, 134217728);
      localObject3 = PendingIntent.getService(getContext(), Utils.getNewIntentId(), (Intent)localObject3, 134217728);
      paramJSONObject = PendingIntent.getService(getContext(), Utils.getNewIntentId(), paramJSONObject, 134217728);
      ((NotificationCompat.Builder)localObject1).addAction(2130837803, getContext().getString(2131231034), (PendingIntent)localObject2);
      ((NotificationCompat.Builder)localObject1).addAction(2130837806, getContext().getString(2131231035), (PendingIntent)localObject3);
      ((NotificationCompat.Builder)localObject1).addAction(2130837804, getContext().getString(17039360), paramJSONObject);
      paramJSONObject = getContext();
      localObject1 = ((NotificationCompat.Builder)localObject1).build();
      int i = this.smsNotification;
      this.smsNotification = (i + 1);
      Notifications.showNotification(paramJSONObject, (Notification)localObject1, i + 1000, true);
    } while (!Preferences.getBooleanSharedPreference(getContext(), "play_sms_sound", true).booleanValue());
    Sound.playNotification(getContext());
  }
  
  private void handleStatus(JSONObject paramJSONObject)
  {
    String str;
    try
    {
      str = paramJSONObject.optString("request_id");
      YeloPhoneClient.LAST_STATUS_MESSAGE = System.currentTimeMillis();
      try
      {
        WifiManager localWifiManager = (WifiManager)getContext().getSystemService("wifi");
        if (paramJSONObject.has("wifi")) {
          localWifiManager.setWifiEnabled(paramJSONObject.optBoolean("wifi"));
        }
        if (paramJSONObject.has("mobile")) {
          setMobileDataEnabled(paramJSONObject.optBoolean("mobile"));
        }
        if (paramJSONObject.has("email_notifications")) {
          Preferences.setSharedPreference(getContext(), "sms_notifications", Boolean.valueOf(paramJSONObject.optBoolean("email_notifications")));
        }
        if (paramJSONObject.has("call_notifications")) {
          Preferences.setSharedPreference(getContext(), "call_notifications", Boolean.valueOf(paramJSONObject.optBoolean("call_notifications")));
        }
        YeloPhoneClient.getInstance().sendDeviceStatus(str, paramJSONObject.optBoolean("wallpaper", true));
      }
      catch (Exception paramJSONObject)
      {
        for (;;)
        {
          Log.w("GCMHostHandler", "status error", paramJSONObject);
          YeloPhoneClient.getInstance().sendError(paramJSONObject.getMessage(), str);
        }
      }
      return;
    }
    finally {}
  }
  
  private void handleVerifyNumber(Handler paramHandler, JSONObject paramJSONObject)
  {
    for (;;)
    {
      try
      {
        str1 = paramJSONObject.optString("status");
        if ("success".equals(str1))
        {
          Events.logEvent("Number Verification Success", new Object[0]);
          String str2 = paramJSONObject.optString("number");
          if ((str2 == null) || (str2.length() <= 0)) {
            continue;
          }
          Preferences.setSharedPreference(getContext(), "phone_number", str2);
          int i = paramJSONObject.optInt("credit", 0);
          if (i > 0) {
            Preferences.setSharedPreference(getContext(), "credit", i);
          }
          paramHandler.post(new Runnable()
          {
            public void run()
            {
              UI.showLongToast(GCMHostHandler.this.getContext(), 2131231282);
            }
          });
        }
      }
      catch (Exception paramHandler)
      {
        String str1;
        Log.w("GCMHostHandler", "call log error", paramHandler);
        continue;
      }
      finally {}
      return;
      Events.logEvent("Number Verification Error", new Object[] { "status", str1 });
    }
  }
  
  private void handleVoicemail(JSONObject paramJSONObject)
  {
    boolean bool = false;
    try
    {
      if (YeloPhoneMainActivity.INSTANCE != null) {
        bool = YeloPhoneMainActivity.INSTANCE.getVoicemailFragment().checkVoicemails();
      }
      if (!bool) {
        VoicemailManagerFactory.getVoicemailManager().checkVoicemails();
      }
      if ((!Utils.isICS()) || (!Utils.hasPermissionGranted("com.android.voicemail.permission.ADD_VOICEMAIL")))
      {
        Object localObject = new Intent(getContext(), Utils.getMainActivityClass(getContext()));
        ((Intent)localObject).putExtra("voicemail", true);
        ((Intent)localObject).setFlags(268435456);
        localObject = Notifications.getNotification(getContext(), null, getContext().getString(2131231260), (Intent)localObject, true);
        Notifications.showNotification(getContext(), (Notification)localObject, 14, true);
      }
      paramJSONObject = paramJSONObject.optJSONObject("voicemail");
      if (paramJSONObject != null)
      {
        paramJSONObject = paramJSONObject.optString("id");
        if ((paramJSONObject != null) && (!paramJSONObject.isEmpty()) && (VoicemailManagerFactory.getVoicemailManager().ackVoicemail(paramJSONObject))) {
          Log.i("GCMHostHandler", "Voicemail acknowledged successfully");
        }
      }
      return;
    }
    finally {}
  }
  
  private void handleVoicemailTest(JSONObject paramJSONObject)
  {
    boolean bool = paramJSONObject.optBoolean("success", false);
    Intent localIntent = new Intent(getContext(), Utils.getMainActivityClass(getContext()));
    localIntent.setFlags(268435456);
    localIntent.putExtra("voicemail", true);
    if (bool)
    {
      Events.logEvent("Voicemail Test Result", new Object[] { "success", Boolean.valueOf(true) });
      UI.showLongToast(getContext(), 2131231403);
      Utils.playSonar(getContext());
      if (YeloPhoneIntentService.VOICEMAIL_DLG != null)
      {
        YeloPhoneIntentService.VOICEMAIL_DLG.dismiss();
        YeloPhoneIntentService.VOICEMAIL_DLG = null;
      }
      Preferences.setSharedPreference(getContext(), "voicemail", Boolean.valueOf(true));
      Notifications.showNotification(1010, getContext(), null, getContext().getString(2131231403), localIntent, true, true);
      Preferences.setSharedPreference(getContext(), "voicemail", Boolean.valueOf(bool));
      return;
    }
    paramJSONObject = paramJSONObject.optString("error", "unknown");
    Events.logEvent("Voicemail Test Result", new Object[] { "success", Boolean.valueOf(false), "error", paramJSONObject });
    if ("forwarded_from".equals(paramJSONObject)) {
      UI.showLongToast(getContext(), 2131231400);
    }
    for (;;)
    {
      Utils.playError(getContext());
      Notifications.showNotification(1010, getContext(), null, getContext().getString(2131231401), localIntent, true, true);
      YeloPhoneClient.getInstance().disableVoicemail();
      break;
      UI.showLongToast(getContext(), 2131231401);
    }
  }
  
  /* Error */
  private void handleWifi(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 86
    //   5: invokevirtual 92	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_2
    //   9: aload_0
    //   10: invokevirtual 110	com/yelophone/android/gcm/GCMHostHandler:getContext	()Landroid/content/Context;
    //   13: aload_1
    //   14: ldc_w 888
    //   17: invokevirtual 100	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   20: invokestatic 892	com/yelophone/android/utils/Utils:enableWifi	(Landroid/content/Context;Z)V
    //   23: invokestatic 139	com/yelophone/android/YeloPhoneClient:getInstance	()Lcom/yelophone/android/YeloPhoneClient;
    //   26: aload_2
    //   27: iconst_0
    //   28: invokevirtual 175	com/yelophone/android/YeloPhoneClient:sendDeviceStatus	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   31: pop
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_1
    //   36: ldc 21
    //   38: ldc -109
    //   40: aload_1
    //   41: invokestatic 153	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   44: pop
    //   45: invokestatic 139	com/yelophone/android/YeloPhoneClient:getInstance	()Lcom/yelophone/android/YeloPhoneClient;
    //   48: aload_1
    //   49: invokevirtual 157	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   52: aload_2
    //   53: invokevirtual 161	com/yelophone/android/YeloPhoneClient:sendError	(Ljava/lang/String;Ljava/lang/String;)V
    //   56: goto -24 -> 32
    //   59: astore_1
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_1
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	GCMHostHandler
    //   0	64	1	paramJSONObject	JSONObject
    //   8	45	2	str	String
    // Exception table:
    //   from	to	target	type
    //   9	32	35	java/lang/Exception
    //   2	9	59	finally
    //   9	32	59	finally
    //   36	56	59	finally
  }
  
  private void processConversationItems(Map<String, ConversationItem> paramMap, Collection<ConversationItem> paramCollection)
    throws JSONException
  {
    if ((paramCollection == null) || (paramCollection.size() == 0)) {}
    for (;;)
    {
      return;
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        ConversationItem localConversationItem1 = (ConversationItem)paramCollection.next();
        String str = localConversationItem1.getAddress();
        if (str != null)
        {
          ConversationItem localConversationItem2 = (ConversationItem)paramMap.get(str);
          if (localConversationItem2 != null)
          {
            if (localConversationItem1.getDate().longValue() > localConversationItem2.getDate().longValue()) {
              paramMap.put(str, localConversationItem1);
            }
          }
          else {
            paramMap.put(str, localConversationItem1);
          }
        }
      }
    }
  }
  
  private void setMobileDataEnabled(boolean paramBoolean)
    throws Exception
  {
    Object localObject1 = (ConnectivityManager)getContext().getSystemService("connectivity");
    Object localObject2 = Class.forName(localObject1.getClass().getName()).getDeclaredField("mService");
    ((Field)localObject2).setAccessible(true);
    localObject1 = ((Field)localObject2).get(localObject1);
    localObject2 = Class.forName(localObject1.getClass().getName()).getDeclaredMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE });
    ((Method)localObject2).setAccessible(true);
    ((Method)localObject2).invoke(localObject1, new Object[] { Boolean.valueOf(paramBoolean) });
  }
  
  public void handleMessage(Handler paramHandler, final JSONObject paramJSONObject)
    throws JSONException
  {
    String str1 = paramJSONObject.optString("action", null);
    if (str1 == null)
    {
      Events.logEvent("GCM No Action", new Object[0]);
      Log.w("GCMHostHandler", "Message without action: " + paramJSONObject.toString());
    }
    do
    {
      return;
      if ((Utils.getAccountId() == null) || (Utils.getAccountToken() == null))
      {
        Events.logEvent("GCM Host Invalid Account", new Object[0]);
        return;
      }
      long l = System.currentTimeMillis();
      Events.logEvent("GCM Message", new Object[] { "action", str1 });
      if ("status".equals(str1))
      {
        YeloPhoneClient.LAST_HOST_MESSAGE = l;
        handleStatus(paramJSONObject);
        return;
      }
      if ("call_log".equals(str1))
      {
        handleCallLog(paramJSONObject);
        return;
      }
      if ("verify_number".equals(str1))
      {
        YeloPhoneClient.LAST_HOST_MESSAGE = l;
        handleVerifyNumber(paramHandler, paramJSONObject);
        return;
      }
      if ("notification".equals(str1)) {
        handleNotification(paramJSONObject);
      }
      for (;;)
      {
        String str2 = paramJSONObject.optString("request_id", null);
        if (Utils.isHostMode(getContext()).booleanValue()) {
          break;
        }
        YeloPhoneClient.getInstance().sendError("not_initialized", str2);
        return;
        if ("invalid_handle".equals(str1)) {
          handleInvalidHandle(paramJSONObject);
        }
      }
      TwilioPhone.getInstance().checkDevice();
      getContext().startService(new Intent(getContext(), YeloPhoneService.class));
      if ("conversations".equals(str1))
      {
        YeloPhoneClient.LAST_HOST_MESSAGE = l;
        handleConversations(paramJSONObject);
        return;
      }
      if ("avatar".equals(str1))
      {
        YeloPhoneClient.LAST_HOST_MESSAGE = l;
        handleAvatar(paramJSONObject);
        return;
      }
      if ("conversation".equals(str1))
      {
        YeloPhoneClient.LAST_HOST_MESSAGE = l;
        handleConversation(paramJSONObject);
        return;
      }
      if ("contacts".equals(str1))
      {
        handleContacts(paramJSONObject);
        return;
      }
      if ("location".equals(str1))
      {
        handleLocation(paramHandler, paramJSONObject);
        return;
      }
      if ("call_forwarding".equals(str1))
      {
        handleCallForwarding(paramJSONObject);
        return;
      }
      if ("status".equals(str1))
      {
        YeloPhoneClient.LAST_HOST_MESSAGE = l;
        handleStatus(paramJSONObject);
        return;
      }
      if ("favorites".equals(str1))
      {
        handleAddToFavorites(paramJSONObject);
        return;
      }
      if ("send_sms".equals(str1))
      {
        handleSendSMS(paramJSONObject);
        return;
      }
      if ("alarm".equals(str1))
      {
        handleAlarm(paramJSONObject);
        return;
      }
      if ("voicemail".equals(str1))
      {
        handleVoicemail(paramJSONObject);
        return;
      }
      if ("credit".equals(str1))
      {
        handleCredit(paramJSONObject);
        return;
      }
      if ("call_forwarding_password".equals(str1))
      {
        handleCallForwardingPassword(paramJSONObject);
        return;
      }
      if ("wifi".equals(str1))
      {
        handleWifi(paramJSONObject);
        return;
      }
      if ("sms".equals(str1))
      {
        handleSms(paramJSONObject);
        return;
      }
      if ("device".equals(str1))
      {
        handleDevice(paramJSONObject);
        return;
      }
      if ("insufficient_credits".equals(str1))
      {
        handleInsufficientCredits(paramJSONObject);
        return;
      }
      if ("insufficient_credits_forward".equals(str1))
      {
        handleInsufficientCreditsForward(paramJSONObject);
        return;
      }
      if ("voicemail_test".equals(str1))
      {
        paramHandler.post(new Runnable()
        {
          public void run()
          {
            GCMHostHandler.this.handleVoicemailTest(paramJSONObject);
          }
        });
        return;
      }
      if ("pause".equals(str1))
      {
        handlePause(paramJSONObject);
        return;
      }
      if ("continue".equals(str1))
      {
        handleContinue(paramJSONObject);
        return;
      }
      if ("new_contact".equals(str1))
      {
        handleNewYeloContact(paramJSONObject);
        return;
      }
      if ("reset".equals(str1))
      {
        Utils.reset(getContext());
        return;
      }
      if ("transcription".equals(str1))
      {
        handleTranscription(paramJSONObject);
        return;
      }
    } while (!"update".equals(str1));
    handleUpdate(paramJSONObject);
  }
  
  private final class LocationResultHandler
    implements LocationResult
  {
    private final JSONObject message;
    
    private LocationResultHandler(JSONObject paramJSONObject)
    {
      this.message = paramJSONObject;
    }
    
    public void onLocation(final Location paramLocation)
    {
      if (paramLocation == null) {
        return;
      }
      Utils.startThread(new Runnable()
      {
        public void run()
        {
          Log.d("GCMHostHandler", "location update " + paramLocation);
          String str = GCMHostHandler.LocationResultHandler.this.message.optString("request_id");
          try
          {
            JSONObject localJSONObject1 = new JSONObject();
            localJSONObject1.put("status", "OK");
            localJSONObject1.put("request_id", str);
            localJSONObject1.put("type", "location");
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("longitude", paramLocation.getLongitude());
            localJSONObject2.put("latitude", paramLocation.getLatitude());
            localJSONObject2.put("altitude", paramLocation.getAltitude());
            localJSONObject1.put("location", localJSONObject2);
            YeloPhoneClient.getInstance().sendChannel(localJSONObject1);
            return;
          }
          catch (Exception localException)
          {
            Log.w("GCMHostHandler", "location error", localException);
            YeloPhoneClient.getInstance().sendError(localException.getMessage(), str);
          }
        }
      });
    }
  }
}
